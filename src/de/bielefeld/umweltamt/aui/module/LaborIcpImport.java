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
 * $Id: LaborIcpImport.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 *
 * Erstellt am 01.03.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.16  2005/07/06 09:36:42  u633z
 * - Import verbessert
 *
 * Revision 1.15  2005/06/01 15:14:45  u633z
 * - TableModel-Grundlagen in eigenes Package verschoben
 *
 * Revision 1.14  2005/05/30 15:41:50  u633z
 * - jsddevelop.jar entfernt, überflüssig, da StringFunc.explode() durch String.split() ersetzt.
 *
 * Revision 1.13  2005/05/25 15:45:25  u633z
 * - Messagebox-Methoden des Hauptframes umbenannt
 *
 * Revision 1.12  2005/05/23 13:45:34  u633z
 * Datenbank-Zugriff in DB-Klassen ausgelagert
 *
 * Revision 1.11  2005/05/12 14:04:44  u633z
 * Datei-öffnen Dialog anzeigen generalisiert und
 * in das HauptFrame ausgelagert.
 *
 * Revision 1.10  2005/05/11 09:14:33  u633z
 * *** empty log message ***
 *
 * Revision 1.9  2005/05/11 09:13:09  u633z
 * Positionen mit Flags werden jetzt standardmäßig nicht ausgewählt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein Modul um ICP-Messwerte zu importieren.
 * @author David Klotz
 */
public class LaborIcpImport extends AbstractModul {
	/** Logging */
    private static final Logger log = AuikLogger.getLogger();

    private class FileImporter extends ListTableModel {
        // Die Datei, die Importiert werden soll
        private File importFile = null;

        // Als Cache
        private Map perfectRows = null;
        private Map importableRows = null;

        public FileImporter() {
            super(new String[]{"Probe", "Parameter", "Wert", "Einheit", "Flags"}, false);
        }

        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            String[] tmpArr = (String[]) objectAtRow;
            Object value;
            switch (columnIndex) {
                case 0:
                    String tmp;
                    tmp = "<html><font color=";
                    if (isPositionPerfect(tmpArr)) {
                        tmp += "00cc00";
                    } else if (isPositionImportable(tmpArr)) {
                        tmp += "ff8200";
                    } else {
                        tmp += "red";
                    }
                    String nr = kennummerAusZeile(tmpArr);
                    value = tmp + ">"+ nr + "</font> "+ tmpArr[0].replaceFirst(nr, "").trim() + "</html>";
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
                case 4:
                    value = flagsAusZeile(tmpArr);
                    break;

                default:
                    value = null;
                break;
            }
            return value;
        }

        public void updateList() throws Exception {
            BufferedReader in = new BufferedReader(new FileReader(importFile));
            String line;
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (count == 0 && !line.startsWith("Solution Label")) {
                    throw new IOException("Datei ist kein ICP-Export!");
                }
                String[] tmp = line.split("\t");
                if (!(tmp.length == 18 || tmp.length == 15)) {
                    throw new IOException("Datei ist beschädigt!");
                }
                //System.out.println(count + ": " + line);

                if (!tmp[0].startsWith("Standard")
                        && tmp[1].equals("Samp")
                        && !tmp[2].startsWith("Ar"))
                {
                    getList().add(tmp);
                }
                count++;
            }
            System.out.println(getList().size() + " Zeilen gelesen!");
            in.close();

            fireTableDataChanged();
        }

        public void reset() {
            setList(new ArrayList());
            importFile = null;
            perfectRows = null;
            importableRows = null;
            fireTableDataChanged();
        }

        public void selectAllImportableRows() {
            if (importFile != null) {
                for (int i = 0; i < getList().size(); i++) {
                    if (isRowPerfect(i)) {
                        getImportTabelle().addRowSelectionInterval(i, i);
                    }
                }
            }
        }

        private String datumAusZeile(String[] zeile) {
            return zeile[9].trim() + " " + zeile[10].trim();
        }

        private String einheitAusZeile(String [] zeile) {
            return zeile[7].trim();
        }

        private String wertAusZeile(String[] zeile) {
            return zeile[6].trim();
        }

        private String kennummerAusZeile(String[] zeile) {
            return spalteAusZeile(zeile, 0);
        }

        private String paramAusZeile(String[] zeile) {
            return spalteAusZeile(zeile, 2);
        }

        private String flagsAusZeile(String[] zeile) {
            String tmp = spalteAusZeile(zeile, 3);
            String result = null;
            if (tmp != null) {
                result = tmp.trim();
            }
            return result;
        }

        // Schneidet einen String (aus einem Array) vor dem ersten Leerzeichen ab
        private String spalteAusZeile(String[] zeile, int spalte) {
            if (zeile != null && zeile.length > spalte) {
                return zeile[spalte].replaceFirst(" .*", "");
            } else {
                return null;
            }
        }

        private boolean isRowPerfect(int rowIndex) {
            return isPositionPerfect((String[])getObjectAtRow(rowIndex));
        }

        private boolean isPositionPerfect(String[] pos) {
            if (perfectRows == null) {
                perfectRows = new HashMap(getList().size());
            }

            if (!perfectRows.containsKey(pos)) {
                if (!AtlParameter.isParameterSupported(paramAusZeile(pos))) {
                    perfectRows.put(pos, Boolean.FALSE);
                } else {
                    String kennummer = kennummerAusZeile(pos);
                    AtlProbenahmen probe = AtlProbenahmen.getProbenahme(kennummer, false);
                    if (probe != null) {
                        if (flagsAusZeile(pos).equals("")) {
                            if (probe.isKlaerschlammProbe())
                            {
                                if (probe.getEinwaage() == null || probe.getEinwaage().floatValue() == 0.0f) {
                                    perfectRows.put(pos, Boolean.FALSE);
                                } else {
                                    perfectRows.put(pos, Boolean.TRUE);
                                }
                            } else {
                                perfectRows.put(pos, Boolean.TRUE);
                            }
                        } else {
                            perfectRows.put(pos, Boolean.FALSE);
                        }
                    } else {
                        perfectRows.put(pos, Boolean.FALSE);
                    }
                }
            }

            return ((Boolean)perfectRows.get(pos)).booleanValue();
        }

        public boolean isRowImportable(int rowIndex) {
            return isPositionImportable((String[])getObjectAtRow(rowIndex));
        }

        private boolean isPositionImportable(String[] pos) {
            if (importableRows == null) {
                importableRows = new HashMap(getList().size());
            }

            if (!importableRows.containsKey(pos)) {
                if (isPositionPerfect(pos)) {
                    importableRows.put(pos, new Boolean(true));
                } else if (!AtlParameter.isParameterSupported(paramAusZeile(pos))) {
                    importableRows.put(pos, new Boolean(false));
                } else {
                    String kennummer = kennummerAusZeile(pos);
                    AtlProbenahmen probe = AtlProbenahmen.getProbenahme(kennummer, false);
                    if (probe != null) {
                        importableRows.put(pos, new Boolean(true));
                    } else {
                        importableRows.put(pos, new Boolean(false));
                    }
                }
            }

            return ((Boolean)importableRows.get(pos)).booleanValue();
        }

        public void openFile(File file) {
            if (file.isFile() && file.canRead()) {
                getDateiLabel().setText("Datei: " + file.getName());
                importFile = file;
                try {
                    updateList();
                } catch (Exception e) {
                    frame.showErrorMessage("<html>Konnte Datei nicht öffnen: <br>"+e.getLocalizedMessage()+"</html>");
                    switchToStep(1);
                    return;
                }
                if (getList() != null && getList().size() > 0) {
                    switchToStep(2);
                    selectAllImportableRows();
                } else {
                    frame.showInfoMessage("Die Datei enthält keine importierbaren Analysepositionen!", "ICP-Import");
                }
            } else {
                frame.showErrorMessage("Konnte die angegebene Datei nicht öffnen!", "Fehler beim Öffnen");
            }
        }

        public void doImport() throws Exception {
            if (step == 3 && importFile != null && getList() != null) {
                int importCount = 0;
                int[] selectedRows = importTabelle.getSelectedRows();
                DateFormat df = DateFormat.getDateTimeInstance();
                String problemMessage = "";

                for (int i = 0; i < selectedRows.length; i++) {
                    boolean problem = false;
                    String[] current = (String[]) getObjectAtRow(selectedRows[i]);
                    if (isPositionImportable(current)) {
                        //frame.changeStatus("Verarbeite Position "+ i+1 +"/" + selectedRows.length + " ...");
                        //Thread.yield();
                        AtlAnalyseposition pos = new AtlAnalyseposition();

                        // Kennnummer
                        String kennumer = kennummerAusZeile(current);

                        // Probenahme
                        AtlProbenahmen probe = AtlProbenahmen.getProbenahme(kennumer, true);
                        if (probe == null) {
                            // Sollte eigentlich nicht vorkommen, nötig?
                            throw new Exception("Probenahme nicht gefunden!");
                        }
                        probe.addAnalyseposition(pos);

                        // Wert
                        Float wert = new Float(wertAusZeile(current));
                          // Negative Werte auf 0 setzen:
                        if (wert.floatValue() < 0.0) {
                            wert = new Float(0.0);
                            pos.setGrkl("<");
                        }
                        pos.setWert(wert);

                        // Einwaage
                        if (probe.isKlaerschlammProbe() && (probe.getEinwaage() == null || probe.getEinwaage().floatValue() == 0.0f)) {
                            probe.setEinwaage(new Float(3.0f));
                            log.debug("Einwaage von " + probe
                            		+ " auf 3g korrigiert.");
                        }

                        // Parameter
                        String sParam = paramAusZeile(current);
                        if (sParam != null) {
                            AtlParameter para = AtlParameter.getParameter(AtlParameter.getOrdnungsbegriff(sParam));

                            if (para != null) {
                                pos.setAtlParameter(para);
                            } else {
                                problem = true;
                                if (!problemMessage.equals("")) {
                                    problemMessage += "<br>";
                                }
                                problemMessage += "Unbekannter Parameter: "+ sParam;
                            }
                        } else {
                            // Sollte eigentlich auch nicht vorkommen, nötig?
                            throw new Exception("Importdatei bescädigt!");
                        }

                        // Einheit
                        String sEinheit = einheitAusZeile(current);
                        AtlEinheiten einheit = null;

                        if (sEinheit.equals("mg/L")) {
                            if (probe.isKlaerschlammProbe()) {
                                Float einwaage = probe.getEinwaage();
                                log.debug("Klärschlamm-Umrechnung nötig, Einwaage: "
                                		+ einwaage);
                                if (einwaage != null) {
                                    BigDecimal rundWert = new BigDecimal((wert.doubleValue() * 100) / einwaage.doubleValue());
                                    rundWert = rundWert.setScale(1, BigDecimal.ROUND_UP);
                                    pos.setWert(new Float(rundWert.floatValue()));
                                    einheit = AtlEinheiten.getEinheit(AtlEinheiten.MG_KG_ID);
                                    log.debug("Klärschlamm-Umrechnung: (" + wert
                                    		+ " mg/L * 100) / "+einwaage+" = "
                                    		+ pos.getWert() + " mg/KG");
                                }
                            } else {
                                log.debug("Keine Klärschlamm-Umrechnung nötig.");
                                einheit = AtlEinheiten.getEinheit(AtlEinheiten.MG_L_ID);
                            }
                        }

                        if (einheit != null) {
                            pos.setAtlEinheiten(einheit);
                        } else {
                            problem = true;
                            if (!problemMessage.equals("")) {
                                problemMessage += "<br>";
                            }
                            problemMessage += "Unbekannte Einheit: "+ sEinheit;
                        }

                        // ICP-Datum
                        Date date;
                        try {
                            date = df.parse(datumAusZeile(current));
                        } catch (ParseException e2) {
                            date = null;
                            log.debug("Konnte ICP-Datum nicht parsen!");
                        }
                        if (date != null) {
                            log.debug("Setze ICP-Datum auf: " + df.format(date));
                            probe.setDatumIcp(date);
                        }


                        // Analyse von
                        pos.setAnalyseVon("360.33");

                        if (!problem) {
                            // Speichern...
                            if (AtlAnalyseposition.saveAnalyseposition(pos)) {
                                importCount++;
                                log.debug("Habe " + pos + " gespeichert!");
                            } else {
                                throw new Exception("Konnte Analyseposition nicht in der Datenbank speichern!");
                            }
                        }
                    }
                }

                frame.changeStatus(importCount + " Datensätze importiert!");
                switchToStep(1);
                if (!problemMessage.equals("")) {
                    throw new Exception(problemMessage);
                }
            }
        }
    }

    private JFileChooser fc = null;

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
    private Icon stepThreeG;
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
    public String getName() {
        return "ICP Import";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     */
    public String getIdentifier() {
        return "atl_icp_import";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    public String getCategory() {
        return "Labor";
    }

    public Icon getIcon() {
        //return super.getIcon("1leftarrow.png");
        return super.getIcon("ksysguard.png");
    }
    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    public JPanel getPanel() {
        if (panel == null) {
            initIcons();

            FormLayout layout = new FormLayout(
                    "40px, 5dlu, 65dlu, 5dlu, 175dlu:g",    // Spalten
                    "");    // Zeilen werden dynamisch erzeugt

            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();

            builder.append(getStepOneLabel(), getDateiButton(), getDateiLabel());
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

            panel = builder.getPanel();
        }
        return panel;
    }

    public void show() {
        super.show();
        switchToStep(1);
    }

    private void initIcons() {
        stepOneW = AuikUtils.getIcon("step1_w.png", "Schritt Eins");
        stepOneG = AuikUtils.getIcon("step1_g.png", "Schritt Eins");
        stepOneGrey = AuikUtils.getIcon("step1_grey.png", "Schritt Eins");
        stepTwoW = AuikUtils.getIcon("step2_w.png", "Schritt Zwei");
        stepTwoG = AuikUtils.getIcon("step2_g.png", "Schritt Zwei");
        stepTwoGrey = AuikUtils.getIcon("step2_grey.png", "Schritt Zwei");
        stepThreeW = AuikUtils.getIcon("step3_w.png", "Schritt Drei");
        stepThreeG = AuikUtils.getIcon("step3_g.png", "Schritt Drei");
        stepThreeGrey = AuikUtils.getIcon("step3_grey.png", "Schritt Drei");
    }

    private JLabel getStepOneLabel() {
        if (stepOneLabel == null) {
            stepOneLabel = new JLabel(stepOneGrey);
        }
        return stepOneLabel;
    }

    private JLabel getStepTwoLabel() {
        if (stepTwoLabel == null) {
            stepTwoLabel = new JLabel(stepTwoGrey);
        }
        return stepTwoLabel;
    }

    private JLabel getStepThreeLabel() {
        if (stepThreeLabel == null) {
            stepThreeLabel = new JLabel(stepThreeGrey);
        }
        return stepThreeLabel;
    }

    private JButton getDateiButton() {
        if (dateiButton == null) {
            dateiButton = new JButton("Datei wählen");
            dateiButton.setToolTipText("Wählt eine Datei zum Importieren aus");
            dateiButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    File file = frame.openFile(new String[]{"txt"});
                    if (file != null) {
                        switchToStep(1);
                        fileImporter.openFile(file);
                    }
                }
            });
        }
        return dateiButton;
    }

    private JLabel getDateiLabel() {
        if (dateiLabel == null) {
            dateiLabel = new JLabel();
        }
        return dateiLabel;
    }

    private JButton getImportButton() {
        if (importButton == null) {
            importButton = new JButton("Importieren");
            importButton.setToolTipText("Importiert die gewählten Analysepositionen");
            importButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        fileImporter.doImport();
                    } catch (Exception e1) {
                        frame.showErrorMessage("<html>Beim importieren ist ein Fehler aufgetreten:<br>" +
                                e1.getMessage() + "</html>");
                    }
                }
            });
        }
        return importButton;
    }

    private JLabel getErklaerungsLabel() {
        if (erklaerungsLabel == null) {
            String sErklaerung = "<html>" +
                    "<font color=green>Grün:</font> Position kann problemlos importiert werden.<br>" +
                    "<font color=#ff8200>Orange:</font> Wert aßerhalb des kalibrierten Bereichs oder KS-Einwaage nicht festgelegt.<br>" +    // Klärschlamm-Probenahme existiert, aber die Einwaage ist nicht festgelegt
                    "<font color=red>Rot:</font> Probenahme nicht gefunden oder Parameter unbekannt.</html>";
            erklaerungsLabel = new JLabel(sErklaerung);
        }
        return erklaerungsLabel;
    }

    private JTable getImportTabelle() {
        if (importTabelle == null) {
            if (fileImporter == null) {
                fileImporter = new FileImporter();
            }
            importTabelle = new JTable(fileImporter);
            // Wir wollen wissen, wenn eine andere Zeile ausgewählt wurde
            importTabelle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    // überzählige Events ignorieren
                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    if (importTabelle.getSelectedRowCount() != 0) {
                        int[] selectedRows = importTabelle.getSelectedRows();
                        //String tmp = "[ ";
                        for (int i = 0; i < selectedRows.length; i++) {
                            if (!fileImporter.isRowImportable(selectedRows[i])) {
                                importTabelle.removeRowSelectionInterval(selectedRows[i], selectedRows[i]);
                            } else {
                                //tmp += selectedRows[i] + " ";
                                if (step != 3) {
                                    switchToStep(3);
                                }
                            }
                        }
                        //tmp += "]";
                        //AUIKataster.debugOutput("Selection: " + tmp, "LaborIcpImport.ListSelectionListener");
                    } else {
                        if (step != 2) {
                            switchToStep(2);
                        }
                    }
                }
            });
        }
        return importTabelle;
    }

    private JScrollPane getImportScroller() {
        if (importScroller == null) {
            importScroller = new JScrollPane(getImportTabelle());
        }
        return importScroller;
    }

    public void switchToStep(int step) {
        this.step = step;

        switch (step) {
            case 1:
                fileImporter.reset();
                getDateiLabel().setText("");
                getStepOneLabel().setIcon(stepOneW);
                getStepTwoLabel().setIcon(stepTwoGrey);
                getStepThreeLabel().setIcon(stepThreeGrey);
                enableStepComponents(1, true);
                enableStepComponents(2, false);
                enableStepComponents(3, false);
                getDateiButton().requestFocus();
                break;
            case 2:
                getStepOneLabel().setIcon(stepOneG);
                getStepTwoLabel().setIcon(stepTwoW);
                getStepThreeLabel().setIcon(stepThreeGrey);
                enableStepComponents(1, true);
                enableStepComponents(2, true);
                enableStepComponents(3, false);
                getImportTabelle().requestFocus();
                break;
            case 3:
                getStepOneLabel().setIcon(stepOneG);
                getStepTwoLabel().setIcon(stepTwoG);
                getStepThreeLabel().setIcon(stepThreeW);
                enableStepComponents(1, true);
                enableStepComponents(2, true);
                enableStepComponents(3, true);
                getImportTabelle().requestFocus();
                break;
            /*case 4:
                getStepOneLabel().setIcon(stepOneG);
                getStepTwoLabel().setIcon(stepTwoG);
                getStepThreeLabel().setIcon(stepThreeG);
                enableStepComponents(1, true);
                enableStepComponents(2, true);
                enableStepComponents(3, true);
                break;*/

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
