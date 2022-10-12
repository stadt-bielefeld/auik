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
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        String[] tmpArr = (String[]) objectAtRow;
        Object value;
        switch (columnIndex) {
            case 0:
                String tmp;
                tmp = "<html><font color=";
                if (isPositionImportable(tmpArr)) {
                    tmp += "00cc00";
                } else {
                    tmp += "red";
                }
                String nr = getIdNumberFromLine(tmpArr);
                value = tmp + ">" + nr + "</font> ";
                break;
            case 1:
                value = getParamFromLine(tmpArr);
                break;
            case 2:
                value = getValueFromLine(tmpArr);
                break;
            case 3:
                value = getUnitFromLine(tmpArr);
                break;

            default:
                value = null;
                break;
        }
        return value;
    }

    @Override
    public void parseFile(File file) {
        this.importFile = file;
        try {
            updateList();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void doImport() throws ImporterException{
        int importCount = 0;
        int[] selectedRows = parentTable.getSelectedRows();
        NumberFormat decform = DecimalFormat.getInstance();
        String problemMessage = "";

        for (int i = 0; i < selectedRows.length; i++) {
            boolean problem = false;
            String[] current = (String[]) getObjectAtRow(selectedRows[i]);
            if (isPositionImportable(current)) {
                Analyseposition pos = new Analyseposition();

                String kennumer = getIdNumberFromLine(current);

                Probenahme probe = DatabaseQuery.findProbenahme(
                    kennumer);
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
                try {
                    wert = Float.valueOf(decform.parse(strWert).floatValue());

                    pos.setWert(wert);

                    // Normwert
                    Double dwert;
                    Double normwert;
                    Double sielhautgw = null;
                    String sPara = getParamFromLine(current);
                    if (sPara != null) {
                        sielhautgw = DatabaseQuery
                            .getParameterByDescription(sPara)
                                .getSielhautGw();
                    }
                    dwert = Double.valueOf(decform.parse(strWert).doubleValue());
                    normwert = dwert / sielhautgw;
                    pos.setNormwert(normwert);

                } catch (ParseException e) {
                    continue;
                }

                // Parameter
                String sParam = getParamFromLine(current);
                if (sParam != null) {
                    Parameter para = DatabaseQuery
                        .getParameterByDescription(sParam);

                    if (para != null) {
                        pos.setParameter(para);
                    } else {
                        problem = true;
                        if (!problemMessage.equals("")) {
                            problemMessage += "<br>";
                        }
                        problemMessage += "Unbekannter Parameter: "
                            + sParam;
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
                    if (!problemMessage.equals("")) {
                        problemMessage += "<br>";
                    }
                    problemMessage += "Unbekannte Einheit: " +
                        getUnitFromLine(current);
                }

                // Analyse von
                pos.setAnalyseVon(SettingsManager.getInstance()
                    .getSetting("auik.prefs.sielhaut_labor"));

                if (!problem) {
                    // Speichern...
                    if (pos.merge()) {
                        importCount++;
                        log.debug("Habe " + pos + " gespeichert!");
                    } else {
                        throw new ImporterException(
                            "Konnte Analyseposition nicht in der Datenbank speichern!");
                    }
                }
            }
        }

        if (!problemMessage.equals("")) {
            throw new ImporterException(problemMessage);
        }
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

}
