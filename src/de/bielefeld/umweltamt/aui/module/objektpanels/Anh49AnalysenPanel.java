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
 * $Id: Anh49AnalysenPanel.java,v 1.2.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 01.06.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.11  2005/09/07 10:48:23  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.10  2005/06/13 14:01:52  u633z
 * - Nicht vorhandenes Fachdaten-Objekt wird besser behandelt
 *
 * Revision 1.9  2005/06/10 11:06:52  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.8  2005/06/09 14:38:00  u633d
 * - Anhang 49 Pflegearbeiten
 *
 * Revision 1.7  2005/06/09 13:40:19  u633z
 * - Ortstermin-Tabelle auf SelectTable umgestellt (bzw. für diese überflüssiges entfernt)
 *
 * Revision 1.6  2005/06/09 06:01:25  u633d
 * Delete von Tabellenzeilen fast fertig
 *
 * Revision 1.5  2005/06/09 05:19:21  u633d
 * Delete von Tabellenzeilen angefangen
 *
 * Revision 1.4  2005/06/08 07:10:41  u633d
 * Abscheider Editor angefangen
 *
 * Revision 1.3  2005/06/03 11:04:42  u633z
 * - Schönheitsfehler...
 *
 * Revision 1.2  2005/06/01 15:15:14  u633z
 * - TableModel-Grundlagen in eigenes Package verschoben
 *
 * Revision 1.1  2005/06/01 12:36:55  u633d
 * Anhang 49 Analysentab ergänzt
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.component.Factory;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Analysen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Kontrollen;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.SelectTable;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Eine Tabelle zum Anzeigen und Erfassen von Anhang 49 Analysen
 * @author Gerd Genuit
 */
public class Anh49AnalysenPanel extends JPanel {
    /**
     * Ein TableModel für eine Tabelle mit Analysen.
     * @author Gerhard Genuit
     */

    private static final long serialVersionUID = 8803365595755140899L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private class Anh49AnalysenModel extends EditableListTableModel {
        private static final long serialVersionUID = 7176011901204094377L;
        private Anh49Fachdaten fachdaten;

        /**
         * Erzeugt ein neues Analysen-TableModel. Dieses hat die Spalten
         * "Datum", "Institut", "CSB", "pH", "KW", "Zink", "BSB5", "BIK" und
         * "Bemerkung".
         */
        public Anh49AnalysenModel() {
            super(new String[] {"Datum", "Institut", "CSB", "pH", "KW", "Zink",
                    "BSB5", "BIK", "Bemerkung"}, false, true);
        }

        /**
         * Setzt das Fachdatenobjekt, nach dessen Abscheider-Details gesucht
         * werden soll.
         * @param fachdaten Das Anhang49-Fachdatenobjekt
         */
        public void setFachdaten(Anh49Fachdaten fachdaten) {
            this.fachdaten = fachdaten;
            updateList();
        }

        @Override
        public boolean objectRemoved(Object objectAtRow) {
            Anh49Analysen removedAna = (Anh49Analysen) objectAtRow;
            boolean removed;

            if (removedAna.getId() != null) {
                removed = Anh49Analysen.delete(removedAna);
            } else {
                removed = true;
            }

            return removed;
        }

        @Override
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Anh49Analysen analysen = (Anh49Analysen) objectAtRow;

            Object tmp;

            switch (columnIndex) {
                case 0:
                    tmp = AuikUtils.getStringFromDate(analysen.getDatum());
                    break;
                case 1:
                    tmp = analysen.getInstitut();
                    break;
                case 2:
                    tmp = analysen.getCsbWert();
                    break;
                case 3:
                    tmp = analysen.getPhWert();
                    break;
                case 4:
                    tmp = analysen.getKwWert();
                    break;
                case 5:
                    tmp = analysen.getZinkWert();
                    break;
                case 6:
                    tmp = analysen.getBsb5Wert();
                    break;
                case 7:
                    tmp = analysen.getBikWert();
                    break;
                case 8:
                    tmp = analysen.getBemerkungen();
                    break;
                default:
                    tmp = null;
                    break;
            }

            return tmp;
        }

        @Override
        public void updateList() {
            if (fachdaten != null) {
                setList(DatabaseQuery.getAnalysen(fachdaten));
            }
            fireTableDataChanged();
        }

        /* (non-Javadoc)
         * @see de.bielefeld.umweltamt.aui.utils.EditableListTableModel#editObject(java.lang.Object, int, java.lang.Object)
         */
        @Override
        public void editObject(Object objectAtRow, int columnIndex,
            Object newValue) {
            Anh49Analysen analyse = (Anh49Analysen) objectAtRow;

            switch (columnIndex) {
                case 0:
                    DateFormat format = DateFormat
                        .getDateInstance(DateFormat.SHORT);
                    try {
                        Date tmpDate = format.parse((String) newValue);
                        analyse.setDatum(tmpDate);
                    } catch (ParseException e) {
                        hauptModul
                            .getFrame()
                            .changeStatus(
                                "Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
                                HauptFrame.ERROR_COLOR);
                    }
                    break;
                case 1:
                    String tmpInst = (String) newValue;
                    // Auf 50 Zeichen kürzen, da die Datenbank-Spalte nur 50
                    // Zeichen breit ist
                    if (tmpInst.length() > 50) {
                        tmpInst = tmpInst.substring(0, 50);
                    }
                    analyse.setInstitut(tmpInst);
                    break;
                case 2:
                    String tmpCsb = (String) newValue;
                    // Auf 50 Zeichen kürzen, da die Datenbank-Spalte nur 50
                    // Zeichen breit ist
                    if (tmpCsb.length() > 50) {
                        tmpCsb = tmpCsb.substring(0, 50);
                    }
                    analyse.setCsbWert(tmpCsb);
                    break;
                case 3:
                    String tmpPh = (String) newValue;
                    // Auf 50 Zeichen kürzen, da die Datenbank-Spalte nur 50
                    // Zeichen breit ist
                    if (tmpPh.length() > 50) {
                        tmpPh = tmpPh.substring(0, 50);
                    }
                    analyse.setPhWert(tmpPh);
                    break;
                case 4:
                    String tmpKw = (String) newValue;
                    // Auf 50 Zeichen kürzen, da die Datenbank-Spalte nur 50
                    // Zeichen breit ist
                    if (tmpKw.length() > 50) {
                        tmpKw = tmpKw.substring(0, 50);
                    }
                    analyse.setKwWert(tmpKw);
                    break;
                case 5:
                    String tmpZn = (String) newValue;
                    // Auf 50 Zeichen kürzen, da die Datenbank-Spalte nur 50
                    // Zeichen breit ist
                    if (tmpZn.length() > 50) {
                        tmpZn = tmpZn.substring(0, 50);
                    }
                    analyse.setZinkWert(tmpZn);
                    break;
                case 6:
                    String tmpBSB = (String) newValue;
                    // Auf 50 Zeichen kürzen, da die Datenbank-Spalte nur 50
                    // Zeichen breit ist
                    if (tmpBSB.length() > 50) {
                        tmpBSB = tmpBSB.substring(0, 50);
                    }
                    analyse.setBsb5Wert(tmpBSB);
                    break;
                case 7:
                    String tmpBik = (String) newValue;
                    // Auf 50 Zeichen kürzen, da die Datenbank-Spalte nur 50
                    // Zeichen breit ist
                    if (tmpBik.length() > 50) {
                        tmpBik = tmpBik.substring(0, 50);
                    }
                    analyse.setBikWert(tmpBik);
                    break;
                case 8:
                    String tmpBem = (String) newValue;
                    // Auf 255 Zeichen kürzen, da die Datenbank-Spalte nur 255
                    // Zeichen breit ist
                    if (tmpBem.length() > 255) {
                        tmpBem = tmpBem.substring(0, 255);
                    }
                    analyse.setBemerkungen(tmpBem);
                    break;

                default:
                    break;
            }
        }

        /* (non-Javadoc)
         * @see de.bielefeld.umweltamt.aui.utils.EditableListTableModel#newObject()
         */
        @Override
        public Object newObject() {
            Anh49Analysen ana = new Anh49Analysen();
            ana.setAnh49Fachdaten(fachdaten);
            ana.setDatum(new Date());
            return ana;
        }

        public Anh49Analysen getRow(int rowIndex) {
            return (Anh49Analysen) getObjectAtRow(rowIndex);
        }
    }

    /**
     * Ein TableModel für eine Tabelle mit Abscheider-Kontrollen.
     * @author Gerhard Genuit
     */
    private class Anh49KontrollenModel extends EditableListTableModel {
        private static final long serialVersionUID = -1286333922211056915L;
        private Anh49Fachdaten fachdaten;

        /**
         * Erzeugt ein neues Ortstermin-TableModel. Dieses hat die Spalten
         * "Datum", "SachbearbeiterIn" und "Bemerkung".
         */
        public Anh49KontrollenModel() {
            super(
                new String[] {"Prüfdatum", "Prüfergebnis", "Nächste Prüfung"},
                false, true);
        }

        /**
         * Setzt das Fachdatenobjekt, nach dessen Abscheider-Details gesucht
         * werden soll.
         * @param fachdaten Das Anhang49-Fachdatenobjekt
         */
        private void setFachdaten(Anh49Fachdaten fachdaten) {
            this.fachdaten = fachdaten;
            updateList();
        }

        @Override
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Anh49Kontrollen kt = (Anh49Kontrollen) objectAtRow;

            Object tmp;

            switch (columnIndex) {
                case 0:
                    tmp = AuikUtils.getStringFromDate(kt.getPruefdatum());
                    break;
                case 1:
                    tmp = kt.getPruefergebnis();
                    break;
                case 2:
                    tmp = AuikUtils.getStringFromDate(kt.getNaechstepruefung());
                    break;
                default:
                    tmp = null;
            }

            return tmp;
        }

        @Override
        public boolean objectRemoved(Object objectAtRow) {
            Anh49Kontrollen removedOt = (Anh49Kontrollen) objectAtRow;
            boolean removed;

            if (removedOt.getId() != null) {
                removed = Anh49Kontrollen.delete(removedOt);
            } else {
                removed = true;
            }

            return removed;
        }

        @Override
        public void updateList() {
            if (fachdaten != null) {
                setList(DatabaseQuery.getKontrollen(fachdaten));
            }
            fireTableDataChanged();
        }

        @Override
        public void editObject(Object objectAtRow, int columnIndex,
            Object newValue) {
            Anh49Kontrollen kt = (Anh49Kontrollen) objectAtRow;

            String tmp = (String) newValue;

            switch (columnIndex) {
                case 0:
                    DateFormat format = DateFormat
                        .getDateInstance(DateFormat.SHORT);
                    try {
                        Date tmpDate = format.parse(tmp);
                        kt.setPruefdatum(tmpDate);
                    } catch (ParseException e) {
                        hauptModul
                            .getFrame()
                            .changeStatus(
                                "Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
                                HauptFrame.ERROR_COLOR);
                    }
                    break;
                case 1:
                    // Auf 255 Zeichen kürzen, da die Datenbank-Spalte nur 255
                    // Zeichen breit ist
                    if (tmp.length() > 255) {
                        tmp = tmp.substring(0, 255);
                    }
                    kt.setPruefergebnis(tmp);
                    break;
                case 2:
                    DateFormat formatNp = DateFormat
                        .getDateInstance(DateFormat.SHORT);
                    try {
                        Date tmpDate = formatNp.parse(tmp);
                        kt.setNaechstepruefung(tmpDate);
                    } catch (ParseException e) {
                        hauptModul
                            .getFrame()
                            .changeStatus(
                                "Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
                                HauptFrame.ERROR_COLOR);
                    }
                    break;

                default:
                    break;
            }
        }

        @Override
        public Object newObject() {
            Anh49Kontrollen kt = new Anh49Kontrollen();
            kt.setAnh49Fachdaten(fachdaten);
            kt.setPruefdatum(new Date());
            return kt;
        }

        public Anh49Kontrollen getRow(int rowIndex) {
            return (Anh49Kontrollen) getObjectAtRow(rowIndex);
        }
    }

    private String name;

    private BasisObjektBearbeiten hauptModul;

    private Anh49Fachdaten fachdaten;
    private Anh49AnalysenModel analysenModel;
    private Anh49KontrollenModel kontrollenModel;

    private JTable analysenTabelle;
    private JTable kontrollenTabelle;
    private JButton speichernButton;

    public Anh49AnalysenPanel(BasisObjektBearbeiten hauptModul) {
        name = "Analysen und Dichtheitsprüfungen";
        this.hauptModul = hauptModul;

        this.analysenModel = new Anh49AnalysenModel();
        this.kontrollenModel = new Anh49KontrollenModel();

        TableFocusListener tfl = TableFocusListener.getInstance();
        getAnalysenTabelle().addFocusListener(tfl);

        JScrollPane analysenScroller = new JScrollPane(getAnalysenTabelle(),
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane kontrollenScroller = new JScrollPane(
            getKontrollenTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JSplitPane tabellenSplit = Factory.createStrippedSplitPane(
            JSplitPane.VERTICAL_SPLIT, analysenScroller, kontrollenScroller,
            0.5);

        FormLayout layout = new FormLayout("150dlu:grow, 150dlu", // Spalten
            "f:100dlu:grow, 3dlu, pref"); // Zeilen

        PanelBuilder builder = new PanelBuilder(layout, this);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        builder.add(tabellenSplit, cc.xyw(1, 1, 2));
        builder.add(getSpeichernButton(), cc.xy(2, 3));
    }

    private JTable getAnalysenTabelle() {
        if (analysenTabelle == null) {
            analysenTabelle = new SelectTable(analysenModel);

            // Wenn die Spaltengröße sich verändert, verändert sich nur die
            // Nachbarspalte mit
            analysenTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

            KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, 0, false);
            analysenTabelle.getInputMap().put(deleteKeyStroke, "DEL");
            Action anaRemoveAction = new AbstractAction() {
                private static final long serialVersionUID = 4468129060263777129L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = analysenTabelle.getSelectedRow();
                    if (row != -1 && analysenTabelle.getEditingRow() == -1) {
                        Anh49Analysen analyse = analysenModel.getRow(row);

                        if (GUIManager
                            .getInstance()
                            .showQuestion(
                                "Soll die Analyse " + analyse.getId()
                                    + " wirklich inkl. aller untersuchten Parameter gelöscht werden?",
                                "Löschen bestätigen")) {
                            analysenModel.removeRow(row);
                            log.debug("Analyse " + analyse.getId()
                                + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von " + analyse.getId()
                                + " wurde abgebrochen!");
                        }
                    }
                }
            };
            analysenTabelle.getActionMap().put("DEL", anaRemoveAction);

            // Die Größen der Spalten werden angepasst
            TableColumn column = null;
            for (int i = 0; i < analysenTabelle.getColumnCount(); i++) {
                column = analysenTabelle.getColumnModel().getColumn(i);
                if (i == 0) {
                    column.setMaxWidth(100);
                    column.setPreferredWidth(75);
                } else if (i == 1) {
                    column.setMaxWidth(250);
                    column.setPreferredWidth(150);
                } else if (i == 8) {
                    column.setMaxWidth(400);
                    column.setPreferredWidth(300);
                } else {
                    column.setMaxWidth(80);
                    column.setPreferredWidth(50);
                }

            }
        }
        return analysenTabelle;
    }

    private JTable getKontrollenTabelle() {
        if (kontrollenTabelle == null) {
            kontrollenTabelle = new SelectTable(kontrollenModel);

            // Wenn die Spaltengröße sich verändert, verändert sich nur die
            // Nachbarspalte mit
            kontrollenTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

            KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, 0, false);
            kontrollenTabelle.getInputMap().put(deleteKeyStroke, "DEL");
            Action konRemoveAction = new AbstractAction() {
                private static final long serialVersionUID = -5878075809427207699L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = kontrollenTabelle.getSelectedRow();
                    if (row != -1 && kontrollenTabelle.getEditingRow() == -1) {
                        Anh49Kontrollen kontrollen = kontrollenModel
                            .getRow(row);

                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Dichtheitsprüfung vom "
                                + kontrollen.getPruefdatum()
                                + " wirklich gelöscht werden?",
                            "Löschen bestätigen")) {
                            kontrollenModel.removeRow(row);
                            log.debug("Dichtheitsprüfung vom "
                                + kontrollen.getPruefdatum()
                                + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen der Dichtheitsprüfung vom "
                                + kontrollen.getPruefergebnis()
                                + " wurde abgebrochen!");
                        }
                    }
                }
            };
            kontrollenTabelle.getActionMap().put("DEL", konRemoveAction);

            // Die Größen der Spalten werden angepasst
            TableColumn column = null;
            for (int i = 0; i < kontrollenTabelle.getColumnCount(); i++) {
                column = kontrollenTabelle.getColumnModel().getColumn(i);
                if (i == 0) {
                    column.setMaxWidth(100);
                    column.setPreferredWidth(75);
                } else if (i == 1) {
                    column.setMaxWidth(980);
                    column.setPreferredWidth(780);
                } else if (i == 2) {
                    column.setMaxWidth(100);
                    column.setPreferredWidth(75);
                }
            }
        }
        return kontrollenTabelle;
    }

    public void speichernAnalyse() {
        List<?> anaListe = analysenModel.getList();
        for (int i = 0; i < anaListe.size(); i++) {
            Anh49Analysen analyse = (Anh49Analysen) anaListe.get(i);
            Anh49Analysen.merge(analyse);
        }
        analysenModel.updateList();
    }

    public void speichernKontrollen() {
        List<?> konListe = kontrollenModel.getList();
        for (int i = 0; i < konListe.size(); i++) {
            Anh49Kontrollen kontrolle = (Anh49Kontrollen) konListe.get(i);
            Anh49Kontrollen.merge(kontrolle);
        }
        analysenModel.updateList();
    }

    public void setFachdaten(Anh49Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
    }

    public void updateForm() {
        if (fachdaten != null) {
            analysenModel.setFachdaten(fachdaten);
            analysenModel.updateList();
            kontrollenModel.setFachdaten(fachdaten);
            kontrollenModel.updateList();
        }
    }

    public void clearForm() {
        // Hier füllen wir das Analysen-TableModel mit einer leeren Liste
        analysenModel.setList(new ArrayList<Anh49Analysen>());
        kontrollenModel.setList(new ArrayList<Anh49Kontrollen>());
    }

    public void enableAll(boolean enabled) {
        if (!(enabled && (fachdaten == null))) {
            getAnalysenTabelle().setEnabled(enabled);
            getKontrollenTabelle().setEnabled(enabled);
            getSpeichernButton().setEnabled(enabled);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    private JButton getSpeichernButton() {
        if (speichernButton == null) {
            speichernButton = new JButton("Analysen und Kontrollen speichern");

            speichernButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    speichernAnalyse();
                    speichernKontrollen();
                }
            });
        }

        return speichernButton;
    }
}
