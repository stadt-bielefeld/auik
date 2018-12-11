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

/*
 * Datei:
 * $Id: SielhautImport.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 04.07.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/07/23 06:55:22  u633d
 * Anh49 Auswertung und Sielhautimport neu
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.3  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.2  2005/08/24 08:42:51  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.1  2005/07/06 09:36:58  u633z
 * - Neuer SielhautBearbeiten-Import
 *
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein Modul um SielhautBearbeiten-Messwerte zu importieren.
 * @author David Klotz
 */
public class SielhautImport extends AbstractModul {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private class FileImporter extends ListTableModel {
        private static final long serialVersionUID = 1477729486817801731L;

        // Die Datei, die Importiert werden soll
        private File importFile = null;

        // Als Cache
        private Map<String[], Boolean> importableRows = null;

        public FileImporter() {
            super(new String[] {"Probe", "Parameter", "Wert", "Einheit"}, false);
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
                    String nr = kennummerAusZeile(tmpArr);
                    value = tmp + ">" + nr + "</font> ";
                    break;
                case 1:
                    value = paramAusZeile(tmpArr);
                    break;
                case 2:
                    value = wertAusZeile(tmpArr);
                    break;
                case 3:
                    value = einheitAusZeile(tmpArr);
                    break;

                default:
                    value = null;
                    break;
            }
            return value;
        }

        @Override
        public void updateList() throws Exception {
            BufferedReader in = new BufferedReader(new FileReader(
                this.importFile));
            String line;
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (count == 0 && !line.startsWith("PROBENAHMEDATUM")) {
                    throw new IOException(
                        "Datei ist kein SielhautBearbeiten-CSV!");
                }
                String[] tmp = line.split(";");
                if (tmp.length != 8) {
                    throw new IOException("Datei ist beschädigt!");
                }
                // log.debug(count + ": " + line);

                if (!tmp[0].startsWith("PROBENAHMEDATUM")) {
                    getList().add(tmp);
                }

                count++;
            }
            // log.debug(getList().size() + " Zeilen gelesen!");
            in.close();

            fireTableDataChanged();
        }

        public void reset() {
            setList(new ArrayList<String[]>());
            this.importFile = null;
            this.importableRows = null;
            fireTableDataChanged();
        }

        public void selectAllImportableRows() {
            if (this.importFile != null) {
                for (int i = 0; i < getList().size(); i++) {
                    if (isRowImportable(i)) {
                        getImportTabelle().addRowSelectionInterval(i, i);
                    }
                }
                int count = getImportTabelle().getSelectedRowCount();
                SielhautImport.this.frame.changeStatus(count
                    + " Analysen direkt importierbar!");
            }
        }

        private String einheitAusZeile(String[] zeile) {
            return zeile[5].trim();
        }

        private String wertAusZeile(String[] zeile) {
            // log.debug("WERT: '" + zeile[6].trim() + "'");
            return zeile[4].trim();
        }

        private String kennummerAusZeile(String[] zeile) {
            String tmp = zeile[2].trim().substring(0, 7);

            // Für neueres Format, bei dem die Kennnummer in der
            // Form "Sielhautprobe 5071 (Adenauer)" angegeben ist:
//            if (tmp.indexOf("(") != -1) {
//                tmp = tmp.replaceFirst(" \\(.*\\)", "");
//            }

            return tmp.trim();
        }

        private String paramAusZeile(String[] zeile) {
//            return zeile[5].replaceFirst(" \\(.*\\)", "");
            return zeile[3].trim();
        }

        // Schneidet einen String (aus einem Array) vor dem ersten Leerzeichen
        // ab
        /*private String spalteAusZeile(String[] zeile, int spalte) {
            if (zeile != null && zeile.length > spalte) {
                return zeile[spalte].replaceFirst(" .*", "");
            } else {
                return null;
            }
        }*/

        public boolean isRowImportable(int rowIndex) {
            return isPositionImportable((String[]) getObjectAtRow(rowIndex));
        }

        private boolean isPositionImportable(String[] pos) {
            if (this.importableRows == null) {
                this.importableRows = new HashMap<String[], Boolean>(getList()
                    .size());
            }

            if (!this.importableRows.containsKey(pos)) {
                this.importableRows.put(pos, new Boolean(
                    // Check the parameter
                    DatabaseQuery.parameterExists(paramAusZeile(pos)) &&
                    // Check the unit
                    DatabaseQuery.einheitExists(einheitAusZeile(pos)) &&
                    // Check the sample
                    DatabaseQuery.probenahmeExists(kennummerAusZeile(pos))));
            }

            return ((Boolean) this.importableRows.get(pos)).booleanValue();
        }

        public void openFile(File file) {
            if (file.isFile() && file.canRead()) {
            	
                getDateiLabel().setText("Datei: " + file.getName());
                this.importFile = file;
                try {
                    updateList();
                } catch (Exception e) {
                    GUIManager.getInstance().showErrorMessage(
                        "<html>Konnte Datei nicht öffnen: <br>"
                            + e.getLocalizedMessage() + "</html>");
                    switchToStep(1);
                    return;
                }
                if (getList() != null && getList().size() > 0) {
                    switchToStep(2);
                    selectAllImportableRows();
                } else {
                    GUIManager
                        .getInstance()
                        .showInfoMessage(
                            "Die Datei enthält keine importierbaren Analysepositionen!",
                            "Import");
                }
            } else {
                GUIManager.getInstance().showErrorMessage(
                    "Konnte die angegebene Datei nicht öffnen!",
                    "Fehler beim öffnen");
            }
        }

        public void doImport() throws Exception {
            if (SielhautImport.this.step == 3 && this.importFile != null
                && getList() != null) {
                int importCount = 0;
                int[] selectedRows = SielhautImport.this.importTabelle
                    .getSelectedRows();
                NumberFormat decform = DecimalFormat.getInstance();
                String problemMessage = "";

                for (int i = 0; i < selectedRows.length; i++) {
                    boolean problem = false;
                    String[] current = (String[]) getObjectAtRow(selectedRows[i]);
                    if (isPositionImportable(current)) {
                        Analyseposition pos = new Analyseposition();

                        // Kennnummer
                        String kennumer = kennummerAusZeile(current);

                        // Probenahme
                        Probenahme probe = DatabaseQuery.findProbenahme(
                            kennumer);
                        if (probe == null) {
                            // Sollte eigentlich nicht vorkommen, nötig?
                            throw new Exception("Probenahme nicht gefunden!");
                        }
                        pos.setProbenahme(probe);

                        // Wert
                        Float wert;
                        String strWert = wertAusZeile(current);
                        if (strWert.startsWith("<")) {
                            pos.setGrkl("<");
                            strWert = strWert.replaceFirst("< *", "");
                        }
                        wert = new Float(decform.parse(strWert).floatValue());
                        pos.setWert(wert);

                        // Normwert
                        Double dwert;
                        Double normwert;
                        Double sielhautgw = null;
                        String sPara = paramAusZeile(current);
                        if (sPara != null) {
                            sielhautgw = DatabaseQuery
                                .getParameterByDescription(sPara)
                                    .getSielhautGw();
                        }
                        dwert = new Double(decform.parse(strWert).doubleValue());
                        normwert = dwert / sielhautgw;
                        pos.setNormwert(normwert);

                        // Parameter
                        String sParam = paramAusZeile(current);
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
                            // Sollte eigentlich auch nicht vorkommen, nötig?
                            throw new Exception("Importdatei beschädigt!");
                        }

                        // Einheit
                        Einheiten einheit =
                            DatabaseQuery.getEinheitByDescription(
                                einheitAusZeile(current));

                        if (einheit != null) {
                            pos.setEinheiten(einheit);
                        } else {
                            problem = true;
                            if (!problemMessage.equals("")) {
                                problemMessage += "<br>";
                            }
                            problemMessage += "Unbekannte Einheit: " +
                                einheitAusZeile(current);
                        }

                        // Analyse von
                        pos.setAnalyseVon(SielhautImport.this.manager
                            .getSettingsManager().getSetting(
                                "auik.prefs.sielhaut_labor"));

                        if (!problem) {
                            // Speichern...
                            if (pos.merge()) {
                                importCount++;
                                log.debug("Habe " + pos + " gespeichert!");
                            } else {
                                throw new Exception(
                                    "Konnte Analyseposition nicht in der Datenbank speichern!");
                            }
                        }
                    }
                }

                SielhautImport.this.frame.changeStatus(importCount
                    + " Datensätze importiert!");
                switchToStep(1);
                if (!problemMessage.equals("")) {
                    throw new Exception(problemMessage);
                }
            }
        }
    }

    private JLabel stepOneLabel = null;
    private JLabel stepTwoLabel = null;
    private JLabel stepThreeLabel = null;

    private Icon stepOneW;
    private Icon stepOneG;
    private Icon stepOneGrey;
    private Icon stepTwoW;
    private Icon stepTwoG;
    private Icon stepTwoGrey;
    private Icon stepThreeW;
    private Icon stepThreeGrey;

    private JButton dateiButton, importButton;
    private JLabel dateiLabel;
    private JTable importTabelle;
    private JScrollPane importScroller;
    private JLabel erklaerungsLabel;

    private FileImporter fileImporter;

    private int step;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Sielhaut-Import";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "m_sielhaut_import";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory() {
        return "Sielhaut";
    }

    @Override
    public Icon getIcon() {
        // return super.getIcon("1leftarrow.png");
        return super.getIcon("ksysguard.png");
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
    public JPanel getPanel() {
        if (this.panel == null) {
            initIcons();

            FormLayout layout = new FormLayout(
                "40px, 5dlu, 65dlu, 5dlu, 175dlu:g", // Spalten
                ""); // Zeilen werden dynamisch erzeugt

            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();

            builder
                .append(getStepOneLabel(), getDateiButton(), getDateiLabel());
            builder.appendRelatedComponentsGapRow();
            builder.appendRow("f:50dlu:g");
            builder.nextLine(2);

            builder.append(getStepTwoLabel());
            builder.append(getImportScroller(), 3);
            builder.appendRelatedComponentsGapRow();
            builder.nextLine(2);

            builder.append("");
            builder.append(getErklaerungsLabel(), 3);
            builder.appendRelatedComponentsGapRow();
            builder.nextLine(2);

            builder.append(getStepThreeLabel(), getImportButton());

            this.panel = builder.getPanel();
        }
        return this.panel;
    }

    @Override
    public void show() {
        super.show();
        switchToStep(1);
    }

    private void initIcons() {
        this.stepOneW = AuikUtils.getIcon("step1_w.png", "Schritt Eins");
        this.stepOneG = AuikUtils.getIcon("step1_g.png", "Schritt Eins");
        this.stepOneGrey = AuikUtils.getIcon("step1_grey.png", "Schritt Eins");
        this.stepTwoW = AuikUtils.getIcon("step2_w.png", "Schritt Zwei");
        this.stepTwoG = AuikUtils.getIcon("step2_g.png", "Schritt Zwei");
        this.stepTwoGrey = AuikUtils.getIcon("step2_grey.png", "Schritt Zwei");
        this.stepThreeW = AuikUtils.getIcon("step3_w.png", "Schritt Drei");
        this.stepThreeGrey = AuikUtils
            .getIcon("step3_grey.png", "Schritt Drei");
    }

    private JLabel getStepOneLabel() {
        if (this.stepOneLabel == null) {
            this.stepOneLabel = new JLabel(this.stepOneGrey);
        }
        return this.stepOneLabel;
    }

    private JLabel getStepTwoLabel() {
        if (this.stepTwoLabel == null) {
            this.stepTwoLabel = new JLabel(this.stepTwoGrey);
        }
        return this.stepTwoLabel;
    }

    private JLabel getStepThreeLabel() {
        if (this.stepThreeLabel == null) {
            this.stepThreeLabel = new JLabel(this.stepThreeGrey);
        }
        return this.stepThreeLabel;
    }

    private JButton getDateiButton() {
        if (this.dateiButton == null) {
            this.dateiButton = new JButton("Datei wählen");
            this.dateiButton
                .setToolTipText("Wählt eine Datei zum Importieren aus");
            this.dateiButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    File file = SielhautImport.this.frame
                        .openFile(new String[] {"csv"});
                    if (file != null) {
                    	panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        switchToStep(1);
                        SielhautImport.this.fileImporter.openFile(file);
                    	panel.setCursor(Cursor.getDefaultCursor());
                    }
                }
            });
        }
        return this.dateiButton;
    }

    private JLabel getDateiLabel() {
        if (this.dateiLabel == null) {
            this.dateiLabel = new JLabel();
        }
        return this.dateiLabel;
    }

    private JButton getImportButton() {
        if (this.importButton == null) {
            this.importButton = new JButton("Importieren");
            this.importButton
                .setToolTipText("Importiert die gewählten Analysepositionen");
            this.importButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    	panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        SielhautImport.this.fileImporter.doImport();
                    	panel.setCursor(Cursor.getDefaultCursor());
                    } catch (Exception e1) {
                        GUIManager.getInstance().showErrorMessage(
                            "<html>Beim importieren ist ein Fehler aufgetreten:<br>"
                                + e1.getMessage() + "</html>");
                    }
                }
            });
        }
        return this.importButton;
    }

    private JLabel getErklaerungsLabel() {
        if (this.erklaerungsLabel == null) {
            String sErklaerung = "<html>"
                + "<font color=green>Grün:</font> Position kann problemlos importiert werden.<br>"
                + "<font color=red>Rot:</font> Probenahme nicht gefunden oder Parameter/Einheit unbekannt."
                + "</html>";
            this.erklaerungsLabel = new JLabel(sErklaerung);
        }
        return this.erklaerungsLabel;
    }

    private JTable getImportTabelle() {
        if (this.importTabelle == null) {
            if (this.fileImporter == null) {
                this.fileImporter = new FileImporter();
            }
            this.importTabelle = new JTable(this.fileImporter);
            // Wir wollen wissen, wenn eine andere Zeile ausgewählt wurde
            this.importTabelle.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        // überzählige Events ignorieren
                        if (e.getValueIsAdjusting()) {
                            return;
                        }

                        if (SielhautImport.this.importTabelle
                            .getSelectedRowCount() != 0) {
                            int[] selectedRows = SielhautImport.this.importTabelle
                                .getSelectedRows();

                            for (int i = 0; i < selectedRows.length; i++) {
                                if (!SielhautImport.this.fileImporter
                                    .isRowImportable(selectedRows[i])) {
                                    SielhautImport.this.importTabelle
                                        .removeRowSelectionInterval(
                                            selectedRows[i], selectedRows[i]);
                                } else {
                                    if (SielhautImport.this.step != 3) {
                                        switchToStep(3);
                                    }
                                }
                            }
                        } else {
                            if (SielhautImport.this.step != 2) {
                                switchToStep(2);
                            }
                        }
                    }
                });
        }
        return this.importTabelle;
    }

    private JScrollPane getImportScroller() {
        if (this.importScroller == null) {
            this.importScroller = new JScrollPane(getImportTabelle());
        }
        return this.importScroller;
    }

    public void switchToStep(int step) {
        this.step = step;

        switch (step) {
            case 1:
                this.fileImporter.reset();
                getDateiLabel().setText("");
                getStepOneLabel().setIcon(this.stepOneW);
                getStepTwoLabel().setIcon(this.stepTwoGrey);
                getStepThreeLabel().setIcon(this.stepThreeGrey);
                enableStepComponents(1, true);
                enableStepComponents(2, false);
                enableStepComponents(3, false);
                getDateiButton().requestFocus();
                break;
            case 2:
                getStepOneLabel().setIcon(this.stepOneG);
                getStepTwoLabel().setIcon(this.stepTwoW);
                getStepThreeLabel().setIcon(this.stepThreeGrey);
                enableStepComponents(1, true);
                enableStepComponents(2, true);
                enableStepComponents(3, false);
                getImportTabelle().requestFocus();
                break;
            case 3:
                getStepOneLabel().setIcon(this.stepOneG);
                getStepTwoLabel().setIcon(this.stepTwoG);
                getStepThreeLabel().setIcon(this.stepThreeW);
                enableStepComponents(1, true);
                enableStepComponents(2, true);
                enableStepComponents(3, true);
                getImportTabelle().requestFocus();
                break;

            default:
                break;
        }
    }

    private void enableStepComponents(int step, boolean enabled) {
        switch (step) {
            case 1:
                getDateiButton().setEnabled(enabled);
                getDateiLabel().setEnabled(enabled);
                break;
            case 2:
                getImportTabelle().setEnabled(enabled);
                getErklaerungsLabel().setEnabled(enabled);
                break;
            case 3:
                getImportButton().setEnabled(enabled);
                break;

            default:
                break;
        }
    }
}
