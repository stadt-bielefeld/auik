/**
 * Copyright 2005-2011, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */
package de.bielefeld.umweltamt.aui.module.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaAnalysemethode;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class SielhautImporter extends AbstractImporter {

    private static final AuikLogger log = AuikLogger.getLogger();

    private static final long serialVersionUID = 1L;

    private Map<String[], Boolean> importableRows = null;

    /**
     * Constructor.
     *
     * Creates instance with default columns
     */
    public SielhautImporter() {
        this(new String[] {"Probe", "Parameter", "Wert", "Einheit"}, false);
    }

    public SielhautImporter(String[] columns, boolean updateAtInit) {
        super(columns, updateAtInit);
    }

    @Override
    public void updateList() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(
            this.importFile));
        try {
            String line;
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (count == 0 && !line.startsWith("PROBENAHMEDATUM")) {
                    throw new IOException(
                        "Datei ist kein SielhautBearbeiten-CSV!");
                }
                String[] tmp = line.split(";", -1);
                if (tmp.length != 9) {
                    throw new IOException("Datei ist beschädigt!");
                }

                if (!tmp[0].startsWith("PROBENAHMEDATUM") && !tmp[3].contains("PFO")) {
                    getList().add(tmp);
                }
                count++;
            }
            fireTableDataChanged();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    @Override
    public Object getColumnValue(String[] tmpArr, int columnIndex) {
        StringBuilder builder = new StringBuilder();
        String tmp = "";
        builder.append("<html><font color=");
        if (isPositionImportable(tmpArr)) {
            builder.append(COLOR_GREEN);
        } else {
            builder.append(COLOR_RED);
        }
        builder.append(">");
        switch (columnIndex) {
            case 0:
                tmp = getIdNumberFromLine(tmpArr);
                break;
            case 1:
                tmp = getParamFromLine(tmpArr);
                break;
            case 2:
                tmp = getValueFromLine(tmpArr);
                break;
            case 3:
                tmp = getUnitFromLine(tmpArr);
                break;
            default:
                return null;
        }
        builder.append(tmp)
            .append("</font></html>");
        return (Object) builder.toString();
    }

    @Override
    public void parseFile(File file) throws ImporterException {
        this.importFile = file;
        try {
            updateList();
        } catch (Exception e) {
            throw new ImporterException(e.getMessage());
        }
    }

    @Override
    public void doImport() throws ImporterException{
        int importCount = 0;
        int[] selectedRows = parentTable.getSelectedRows();
        NumberFormat decform = DecimalFormat.getInstance();
        String problemMessage = "";
        importResults = new HashMap<String, ImportResult>();

        for (int i = 0; i < selectedRows.length; i++) {
            boolean problem = false;
            String[] current = (String[]) getObjectAtRow(selectedRows[i]);
            if (isPositionImportable(current)) {
                Analyseposition pos = new Analyseposition();
                ImportResult result;

                String idNumber = getIdNumberFromLine(current);
                if (importResults.containsKey(idNumber)) {
                    result = importResults.get(idNumber);
                } else {
                    result = new ImportResult(idNumber);
                    importResults.put(idNumber, result);
                }

                Probenahme probe = DatabaseQuery.findProbenahme(
                    idNumber);
                if (probe == null) {
                    // Sollte eigentlich nicht vorkommen, nötig?
                    throw new ImporterException("Probenahme nicht gefunden!");
                }
                pos.setProbenahme(probe);

                // Wert
                Float wert;
                String strWert = getValueFromLine(current);
                if (strWert.startsWith("<")) {
                    pos.setGrkl("<");
                    strWert = strWert.replaceFirst("< *", "");
                }

                // Normwert
                Double dwert;
                Double normwert;
                Double sielhautgw = null;
                String sPara = getParamFromLine(current);

                try {
                    wert = Float.valueOf(decform.parse(strWert).floatValue());
                    dwert = Double.valueOf(decform.parse(strWert).doubleValue());
                } catch(ParseException pe) {
                    result.addError(sPara,
                        String.format("Ungültiger Wert: %s", strWert));
                    continue;
                }
                pos.setWert(wert);

                if (sPara != null) {
                    sielhautgw = DatabaseQuery
                        .getParameterByDescription(sPara)
                            .getSielhautGw();
                }
                if (sielhautgw == null) {
                    result.addSkipped(sPara);
                    continue;
                }
                normwert = dwert / sielhautgw;
                pos.setNormwert(normwert);

                // Parameter
                String sParam = getParamFromLine(current);
                if (sParam != null) {
                    Parameter para = DatabaseQuery
                        .getParameterByDescription(sParam);

                    if (para != null) {
                        pos.setParameter(para);
                    } else {
                        problem = true;
                        result.addError(sParam,
                            String.format("Unbekannter Parameter: %s", sParam));
                        continue;
                    }
                } else {
                    throw new ImporterException("Importdatei beschädigt!");
                }

                // Analysemethode

                if (MapElkaAnalysemethode.findByMethoden(getMethodFromLine(current)) != null) {
                    pos.setMapElkaAnalysemethode(MapElkaAnalysemethode.findByMethoden(getMethodFromLine(current)));
                } else {
                    MapElkaAnalysemethode mapAna = new MapElkaAnalysemethode();
                    mapAna.setMethode(getMethodFromLine(current));
                    mapAna.setGruppeDevId("000");
                    mapAna.setRegelwerkId("00");
                    mapAna.setVariantenId("0");
                    mapAna.merge();
                    pos.setMapElkaAnalysemethode(mapAna);
                }

                // Einheit
                Einheiten einheit =
                    DatabaseQuery.getEinheitByDescription(
                        getUnitFromLine(current));

                if (einheit != null) {
                    pos.setEinheiten(einheit);
                } else {
                    problem = true;
                    result.addError(sParam,
                        String.format("Unbekannte Einheit: %s", getUnitFromLine(current)));
                    continue;
                }

                // Analyse von
                pos.setAnalyseVon(SettingsManager.getInstance()
                    .getSetting("auik.prefs.sielhaut_labor"));

                if (!problem) {
                    if (pos.merge()) {
                        importCount++;
                        log.debug("Habe " + pos + " gespeichert!");
                        result.addSuccess(sPara);
                    } else {
                        throw new ImporterException(
                            "Konnte Analyseposition nicht in der Datenbank speichern!");
                    }
                } else {
                    result.getSkipped().add(sPara);
                }
                this.resultCount = importCount;
            }
        }

        if (!problemMessage.equals("")) {
            throw new ImporterException(problemMessage);
        }
    }

    public String getDescriptionString() {
        return
            "<html>"
            + "<font color=" + COLOR_GREEN + ">Grün:</font> Position kann problemlos importiert werden.<br>"
            + "<font color=" + COLOR_RED + ">Rot:</font> Probenahme nicht gefunden oder Parameter/Einheit unbekannt."
            + "</html>";
    }

    private boolean isPositionImportable(String[] pos) {
        if (this.importableRows == null) {
            this.importableRows = new HashMap<String[], Boolean>(getList()
                .size());
        }

        if (!this.importableRows.containsKey(pos)) {
            this.importableRows.put(pos, Boolean.valueOf(
                // Check the parameter
                DatabaseQuery.parameterExists(getParamFromLine(pos)) &&
                // Check the unit
                DatabaseQuery.einheitExists(getUnitFromLine(pos)) &&
                // Check the sample
                DatabaseQuery.probenahmeExists(getIdNumberFromLine(pos))));
        }

        return ((Boolean) this.importableRows.get(pos)).booleanValue();
    }

    @Override
    public boolean isRowSelectable(int rowIndex) {
        Object objectAtRow = getObjectAtRow(rowIndex);
        return isPositionImportable((String[]) objectAtRow);
    }

    private String getUnitFromLine(String[] line) {
        return line[5].trim();
    }

    private String getValueFromLine(String[] line) {
        return line[4].trim();
    }

    private String getIdNumberFromLine(String[] line) {
        String tmp = line[2].trim().substring(0, 7);
        return tmp.trim();
    }

    private String getParamFromLine(String[] line) {
        return line[3].trim();
    }

    private String getMethodFromLine(String[] line) {
        return line[6].trim();
    }

    public void reset() {
        setList(new ArrayList<String[]>());
        this.importFile = null;
        this.importableRows = null;
        fireTableDataChanged();
    }

}
