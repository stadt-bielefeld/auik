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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.module.AnalyseProcessor;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class AbwasserImporter extends AbstractImporter {

    private static final long serialVersionUID = 1L;

    private static final AuikLogger log = AuikLogger.getLogger();

    protected int[] status;
    protected boolean[] selection;

    /**
     * Constructor.
     *
     * Creates instance with default columns
     */
    public AbwasserImporter() {
        this(new String[] {"Kennnummer", "Ordnungsbegriff", "Parameter",
                    "GrKl", "Wert", "Einheit","Analysemethode", "Plausibel"}, false);
    }

    public AbwasserImporter(String[] columns, boolean updateAtInit) {
        super(columns, updateAtInit);
    }

    @Override
    public void updateList() throws Exception {
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(this.importFile), "UTF-8"));
            List<String[]> dataList = getList();
            String line = null;
            int count = 0;
            int bad = 0;

            while ((line = in.readLine()) != null) {
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                if (line.startsWith(".")) {
                    continue;
                }

                String[] columns = line.split("','");

                if (columns == null) {
                    log.error("Fehler beim Lesen einer Analyse-Zeile: "
                        + "Es konnte keine komma-serparierten Spalten "
                        + "gefunden werden!");
                    bad++;
                } else if (columns.length < 9) {
                    log.error("Fehler beim Lesen einer Analyse-Zeile: "
                        + "Es wurden eine kaputte Analyse-Zeile "
                        + "gefunden!");
                    bad++;
                } else {
                    dataList.add(columns);
                }

                count++;
            }
            if (bad == count) {
                throw new IOException("Es wurden keine gültigen Analyse-Zeilen gefunden.");
            }

            if (bad > 0) {
                GUIManager
                    .getInstance()
                    .showInfoMessage(
                        "Beim Lesen des Analyse-Imports war/en "
                            + bad
                            + " kaputte "
                            + "Zeile/n enthalten. Diese wurde/n ignoriert.\n"
                            + "Weitere Informationen sind im Logfile enthalten.",
                        "Ungültige Zeilen im Analyse-Import");
            }

            log.debug(count + " Zeilen eingelesen.");

            fireTableDataChanged();

            this.status = new int[dataList.size()];
            initSelection();
        } catch (FileNotFoundException fnfe) {
            log.error("Fehler beim Lesen der Probenahme-Analyseergebnisse: "
                + fnfe.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }
    }


    protected String getColoredColumn(int status, String txt) {
        StringBuilder sb = new StringBuilder("<html>");

        switch (status) {
            case -1:
                sb.append("<font color='red'>");
                sb.append(txt);
                sb.append("</font>");
                break;
            case 1:
                sb.append("<font color='green'>");
                sb.append(txt);
                sb.append("</font>");
                break;
            case 2:
                sb.append("<font color='FF8200'>");
                sb.append(txt);
                sb.append("</font>");
                break;
            default:
                sb.append(txt);
                break;
        }
        sb.append("</html>");
        return sb.toString();
    }

    public String getDescriptionString() {
        return
            "<html><table width='100%'>"
            + "<tr><td style='color: green;'>Grün:</td>"
            + "<td>Import m&ouml;glich: Kennnummer und Parameter "
            + "vorhanden.</td></tr>"
            + "<tr><td style='color: FF8200;'>Orange:</td>"
            + "<td>Import m&ouml;glich: Kennnummer vorhanden, "
            + "Parameter wird angelegt.</td></tr>"
            + "<tr><td style='color: red;'>Rot:</td>"
            + "<td>Zeile nicht importierbar.</td></tr>" + "</table></html>";
    }

    protected int getRowStatus(int row) {
        if (this.status[row] == 0) {
            String[] columns = (String[]) getObjectAtRow(row);

            this.status[row] = (columns.length < 8) ? -1 :
                AnalyseProcessor.importStatus(
                    AnalyseProcessor.unquote(columns[0]),
                    AnalyseProcessor.unquote(columns[2]),
                    AnalyseProcessor.unquote(columns[6]));
        }

        return this.status[row];
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (!rowExists(row)) {
            return null;
        }

        String[] columns = (String[]) getObjectAtRow(row);

        int status = getRowStatus(row);

        switch (col) {
            case 0:
                return getColoredColumn(status,
                    AnalyseProcessor.unquote(columns[0]));
            case 1:
                return getColoredColumn(status,
                    AnalyseProcessor.unquote(columns[2]));
            case 2:
                return getColoredColumn(status,
                    AnalyseProcessor.unquote(columns[3]));
            case 3:
                return getColoredColumn(status,
                    AnalyseProcessor.unquote(columns[4]));
            case 4:
                return getColoredColumn(status,
                    AnalyseProcessor.unquote(columns[5]));
            case 5:
                return getColoredColumn(status,
                    AnalyseProcessor.unquote(columns[7]));
            case 6:
                return getColoredColumn(status,
                    AnalyseProcessor.unquote(columns[8]));
            case 7:
                return this.selection[row];
        }

        return null;
    }


    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        return null;
    }

    public List<String[]> getSelectedRows() {
        List<String[]> selected = new ArrayList<String[]>();
        int[] selectedRowIndices = this.parentTable.getSelectedRows();
        for (int index: selectedRowIndices) {
            selected.add((String[]) getObjectAtRow(index));
        }

        return selected;
    }

    protected void initSelection() {
        int rows = getRowCount();
        this.selection = new boolean[rows];

        for (int i = 0; i < rows; i++) {
            this.selection[i] = getRowStatus(i) > 0 ? true : false;
        }
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
    public void doImport() {
        log.debug("Speichere die importieren Daten.");
        importResults = new HashMap<String, ImportResult>();
        List<?> data = this.getSelectedRows();
        int size = data.size();

        for (int i = 0; i < size; i++) {
            String[] row = (String[]) data.get(i);
            String idNumber = AnalyseProcessor.getIdNumberOfRow(row);
            String param = AnalyseProcessor.getParamOfRow(row);
            ImportResult result;
            if (importResults.containsKey(idNumber)) {
                result = importResults.get(idNumber);
            } else {
                result = new ImportResult(idNumber);
                importResults.put(idNumber, result);
            }
            if (AnalyseProcessor.process(row)) {
                result.addSuccess(param);
            } else {
                result.addSkipped(param);
            }
        }
    }

    @Override
    public boolean isRowSelectable(int rowIndex) {
        return Boolean.TRUE.equals(getValueAt(rowIndex, 7));
    }

    @Override
    public void reset() {
        setList(new ArrayList<String[]>());
        this.importFile = null;

        fireTableDataChanged();
    }
}
