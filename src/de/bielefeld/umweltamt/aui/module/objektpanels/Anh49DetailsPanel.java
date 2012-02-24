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
 * $Id: Anh49DetailsPanel.java,v 1.2.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 18.05.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.16  2005/09/14 11:25:37  u633d
 * - Version vom 14.9.
 *
 * Revision 1.15  2005/06/13 14:03:20  u633z
 * - Nicht vorhandenes Fachdaten-Objekt wird besser behandelt
 * - Neue Abscheiderdetails können hinzugefügt werden
 *
 * Revision 1.14  2005/06/10 11:06:52  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.13  2005/06/09 14:38:00  u633d
 * - Anhang 49 Pflegearbeiten
 *
 * Revision 1.12  2005/06/09 13:39:06  u633z
 * - Ortstermin-Tabelle auf SelectTable umgestellt
 *
 * Revision 1.11  2005/06/09 09:16:37  u633z
 * - Lösch-Action für die Abscheider-Tabelle repariert
 *
 * Revision 1.10  2005/06/09 06:01:24  u633d
 * Delete von Tabellenzeilen fast fertig
 *
 * Revision 1.9  2005/06/08 12:03:48  u633d
 * Abscheidereditor fertig
 *
 * Revision 1.7  2005/06/08 07:10:41  u633d
 * Abscheider Editor angefangen
 *
 * Revision 1.6  2005/06/01 15:15:14  u633z
 * - TableModel-Grundlagen in eigenes Package verschoben
 *
 * Revision 1.5  2005/06/01 12:36:55  u633d
 * Anhang 49 Analysentab ergänzt
 *
 * Revision 1.4  2005/05/31 10:22:48  u633z
 * - Ortstermin-Tablemodel: Verknüpfung zum BasisObjekt für neue Ortstermine richtig gesetzt
 *
 * Revision 1.3  2005/05/31 10:11:29  u633z
 * Ortstermin-Tabelle:
 * - Datum wird jetzt beim Bearbeiten korrekt geparset.
 * - Bearbeiter / Bemerkung wird auf maximale Anzahl Zeichen in der Tabelle gekürzt
 *
 * Revision 1.2  2005/05/31 07:32:20  u633d
 * Anhang49 Ergänzungen
 *
 * Revision 1.1  2005/05/30 08:35:42  u633z
 * - Aufgeräumt, mehrere neue Packages, Klassen sortiert
 *
 * Revision 1.6  2005/05/25 12:53:25  u633d
 * Anhang49 Ortstermin Tabel-Model
 *
 * Revision 1.5  2005/05/23 10:12:35  u633z
 * überflüssige Exceptions gefangen
 *
 * Revision 1.4  2005/05/20 09:29:33  u633z
 * Anhang 49 und Anh49-Abscheidetail-Tabs verbessert und verschönert
 *
 *
 * Revision 1.2  2005/05/19 15:37:51  u633d
 * Abscheiderdetails-Zwischenstand
 *
 * Revision 1.1  2005/05/19 12:53:44  u633d
 * Anhang 49 neue Mapings
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.component.Factory;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Ortstermine;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.AbscheiderEditor;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.SelectTable;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Das Abscheiderdetails-Tab beim Objekt bearbeiten
 * @author Gerhard Genuit
 */
public class Anh49DetailsPanel extends JPanel{
    private static final long serialVersionUID = -800584079054813622L;

    /**
     * Ein TableModel für eine Tabelle mit Abscheider-Details.
     * @author David Klotz, Gerhard Genuit
     */
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private class Anh49AbscheiderModel extends ListTableModel {
        private static final long serialVersionUID = 6154019963876247085L;
        private Anh49Fachdaten fachdaten;

        /**
         * Erzeugt ein neues Abscheider-TableModel.
         * Dieses hat die Spalten "Abscheider", "Von", "Lage" und "Bemerkung".
         */
        public Anh49AbscheiderModel() {
            super(new String[]{"Abscheider", "Von", "Lage", "Bemerkung"}, false, true);
        }

        /**
         * Setzt das Fachdatenobjekt, nach dessen Abscheider-Details gesucht werden soll.
         * @param fachdaten Das Anhang49-Fachdatenobjekt
         */
        public void setFachdaten(Anh49Fachdaten fachdaten) {
            this.fachdaten = fachdaten;
            updateList();
        }

        @Override
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Anh49Abscheiderdetails details = (Anh49Abscheiderdetails) objectAtRow;

            Object tmp;

            switch (columnIndex) {
                case 0:
                    tmp = details.getAbscheidernr();
                    break;
                case 1:
                    tmp = details.getVon();
                    break;
                case 2:
                    tmp = details.getLage();
                    break;
                case 3:
                    tmp = details.getBemerkung();
                    break;
                default:
                    tmp = null;
            }

            return tmp;
        }

        @Override
        public boolean objectRemoved(Object objectAtRow) {
            Anh49Abscheiderdetails removedAbsch = (Anh49Abscheiderdetails) objectAtRow;
            boolean removed;

            if (removedAbsch.getAbscheiderid() != null) {
                removed = Anh49Abscheiderdetails.removeAbscheider(removedAbsch);
            } else {
                removed = true;
            }

            return removed;
        }

        @Override
        public void updateList() {
            if (fachdaten != null) {
                setList(Anh49Abscheiderdetails.getAbscheiderDetails(fachdaten));
            }
            fireTableDataChanged();
        }
        public Anh49Abscheiderdetails getRow(int rowIndex) {
            return (Anh49Abscheiderdetails) getObjectAtRow(rowIndex);
        }
    }

    /**
     * Ein TableModel für eine Tabelle mit Abscheider-Ortsterminen.
     * @author David Klotz, Gerhard Genuit
     */
    private class Anh49OrtsterminModel extends EditableListTableModel {
        private static final long serialVersionUID = -4508993239949998786L;
        private Anh49Fachdaten fachdaten;

        /**
         * Erzeugt ein neues Ortstermin-TableModel.
         * Dieses hat die Spalten "Datum", "SachbearbeiterIn" und "Bemerkung".
         */
        public Anh49OrtsterminModel() {
            super(new String[]{"Datum", "SachbearbeiterIn", "Bemerkung"}, false, true);
        }

        /**
         * Setzt das Fachdatenobjekt, nach dessen Abscheider-Details gesucht werden soll.
         * @param fachdaten Das Anhang49-Fachdatenobjekt
         */
        private void setFachdaten(Anh49Fachdaten fachdaten) {
            this.fachdaten = fachdaten;
            updateList();
        }

        @Override
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Anh49Ortstermine ot = (Anh49Ortstermine) objectAtRow;

            Object tmp;

            switch (columnIndex) {
                case 0:
                    tmp = AuikUtils.getStringFromDate(ot.getDatum());
                    break;
                case 1:
                    tmp = ot.getSachbearbeiterIn();
                    break;
                case 2:
                    tmp = ot.getBemerkungen();
                    break;
                default:
                    tmp = null;
            }

            return tmp;
        }

        @Override
        public boolean objectRemoved(Object objectAtRow) {
            Anh49Ortstermine removedOt = (Anh49Ortstermine) objectAtRow;
            boolean removed;

            if (removedOt.getOrtsterminid() != null) {
                removed = Anh49Ortstermine.removeOrtstermin(removedOt);
            } else {
                removed = true;
            }

            return removed;
        }

        @Override
        public void updateList() {
            if (fachdaten != null) {
                setList(Anh49Ortstermine.getOrtstermine(fachdaten));
            }
            fireTableDataChanged();
        }
        @Override
        public void editObject(Object objectAtRow, int columnIndex,
                Object newValue) {
            Anh49Ortstermine ot = (Anh49Ortstermine) objectAtRow;

            String tmp = (String) newValue;

            switch (columnIndex) {
                case 0:
                    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
                    try {
                        Date tmpDate = format.parse(tmp);
                        ot.setDatum(tmpDate);
                    } catch (ParseException e) {
                        hauptModul.getFrame().changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
                    }
                    break;
                case 1:
                    // Auf 50 Zeichen kürzen, da die Datenbank-Spalte nur 50 Zeichen breit ist
                    if (tmp.length() > 50) {
                        tmp = tmp.substring(0,50);
                    }
                    ot.setSachbearbeiterIn(tmp);
                    break;
                case 2:
                    // Auf 255 Zeichen kürzen, da die Datenbank-Spalte nur 255 Zeichen breit ist
                    if (tmp.length() > 255) {
                        tmp = tmp.substring(0,255);
                    }
                    ot.setBemerkungen(tmp);
                    break;

                default:
                    break;
            }
        }

        @Override
        public Object newObject() {
            Anh49Ortstermine ot = new Anh49Ortstermine();
            ot.setAnh49Fachdaten(fachdaten);
            ot.setDatum(new Date());
            return ot;
        }

        public Anh49Ortstermine getRow(int rowIndex) {
            return (Anh49Ortstermine) getObjectAtRow(rowIndex);
        }
    }

    private String name;

    private BasisObjektBearbeiten hauptModul;

    private Anh49Fachdaten fachdaten;
    private Anh49AbscheiderModel abscheiderModel;
    private Anh49OrtsterminModel ortsterminModel;

    private JTable abscheiderTabelle;
    private Action abscheiderLoeschAction;
    private Action abscheiderNeuAction;
    private JPopupMenu abscheiderPopup;

    private JTable ortsterminTabelle;
    private Action ortsterminLoeschenAction;
    private JPopupMenu ortsterminPopup;

    private JButton speichernButton;

    public Anh49DetailsPanel(BasisObjektBearbeiten hauptModul) {
        name = "Details und Ortstermine";
        this.hauptModul = hauptModul;

        abscheiderModel = new Anh49AbscheiderModel();
        ortsterminModel = new Anh49OrtsterminModel();

        TableFocusListener tfl = TableFocusListener.getInstance();
        getAbscheiderTabelle().addFocusListener(tfl);

        JScrollPane abscheiderScroller = new JScrollPane(getAbscheiderTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane ortsterminScroller = new JScrollPane(getOrtsterminTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        abscheiderScroller.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showAbscheiderPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showAbscheiderPopup(e);
            }
        });

        TabAction ta = new TabAction();
        ta.addComp(getAbscheiderTabelle());
        ta.addComp(getOrtsterminTabelle());

        JSplitPane tabellenSplit = Factory.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT, abscheiderScroller, ortsterminScroller, 0.5);

        FormLayout layout = new FormLayout(
                "150dlu:grow, 100dlu",        // Spalten
                "100dlu:grow, 3dlu, pref");     // Zeilen

        PanelBuilder builder = new PanelBuilder(layout, this);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        builder.add(tabellenSplit, cc.xyw( 1, 1, 2));
        builder.add(getSpeichernButton(), cc.xy( 2, 3));
    }


    private Action getAbscheiderLoeschAction() {
        if (abscheiderLoeschAction == null) {
            abscheiderLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = -4757595254932715764L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getAbscheiderTabelle().getSelectedRow();
                    if (row != -1 && getAbscheiderTabelle().getEditingRow() == -1) {
                        Anh49Abscheiderdetails abscheider = abscheiderModel.getRow(row);

                        if (hauptModul.getFrame().showQuestion("Soll der Abscheider "+ abscheider +" wirklich inkl. aller Detailinformationen gelöscht werden?", "Löschen bestätigen")) {
                            abscheiderModel.removeRow(row);
                            log.debug("Abscheider " + abscheider.getLage()
                            		+ " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von " + abscheider.getLage()
                            		+ " wurde abgebrochen!");
                        }
                    }
                }
            };
            abscheiderLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
            abscheiderLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return abscheiderLoeschAction;
    }

    private Action getAbscheiderNeuAction() {
        if (abscheiderNeuAction == null) {
            abscheiderNeuAction = new AbstractAction("Neuer Abscheider") {
                private static final long serialVersionUID = 4388335905488653435L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    Anh49Abscheiderdetails neuerAbscheider = new Anh49Abscheiderdetails();
                    neuerAbscheider.setAnh49Fachdaten(fachdaten);
                    editAbscheider(neuerAbscheider);
                }
            };
            abscheiderNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_N));
            //abscheiderNeuAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return abscheiderNeuAction;
    }


    /**
     * öffnet einen Dialog um einen Abscheider-Datensatz zu bearbeiten.
     * @param absch Der Abscheider
     */
    public void editAbscheider(Anh49Abscheiderdetails absch) {

        AbscheiderEditor editDialog = new AbscheiderEditor(absch, hauptModul.getFrame());
        editDialog.setLocationRelativeTo(hauptModul.getFrame());

        editDialog.setVisible(true);

        if (editDialog.wasSaved() && (editDialog.getDetails() != null)) {
            // Die Liste updaten, damit unsere Änderungen auch angezeigt werden
            abscheiderModel.updateList();

            // Den bearbeiteten Abscheider wieder in der Tabelle auswählen
            Anh49Abscheiderdetails details = editDialog.getDetails();
            int row = abscheiderModel.getList().indexOf(details);
            if (row != -1) {
                getAbscheiderTabelle().setRowSelectionInterval(row, row);
                getAbscheiderTabelle().scrollRectToVisible(getAbscheiderTabelle().getCellRect(row, 0, true));
            }
        }
    }

    private void showAbscheiderPopup(MouseEvent e) {
        if (abscheiderPopup == null) {
            abscheiderPopup = new JPopupMenu("Abscheider");
            JMenuItem loeschItem = new JMenuItem(getAbscheiderLoeschAction());
            abscheiderPopup.add(loeschItem);
            JMenuItem neuItem = new JMenuItem(getAbscheiderNeuAction());
            abscheiderPopup.add(neuItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = abscheiderTabelle.rowAtPoint(origin);

            // Löschen ist natürlich nur möglich,
            // wenn wirklich eine Zeile ausgewählt ist:
            if (row != -1) {
                getAbscheiderLoeschAction().setEnabled(true);
                abscheiderTabelle.setRowSelectionInterval(row, row);
            } else {
                getAbscheiderLoeschAction().setEnabled(false);
            }

            // Das Menü zeigen wir aber immer an, zum neu Anlegen eines Abscheiders
            abscheiderPopup.show(e.getComponent(), e.getX(), e.getY());
        }
    }


    private Action getOrtsterminLoeschAction() {
        if (ortsterminLoeschenAction == null) {
            ortsterminLoeschenAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = -7021406986316938685L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getOrtsterminTabelle().getSelectedRow();
                    if (row != -1 && getOrtsterminTabelle().getEditingRow() == -1) {
                        Anh49Ortstermine ot = ortsterminModel.getRow(row);

                        if (ot != null) {
                            if (hauptModul.getFrame().showQuestion("Soll der Ortstermin "+ ot +" gelöscht werden?", "Löschen bestätigen")) {
                                ortsterminModel.removeRow(row);
                                log.debug("Ortstermin " + ot.getOrtsterminid()
                                		+ " wurde gelöscht!");
                            } else {
                                log.debug("Löschen von " + ot.getOrtsterminid()
                                		+ " wurde abgebrochen!");
                            }
                        }
                    }
                }
            };
            ortsterminLoeschenAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
            ortsterminLoeschenAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return ortsterminLoeschenAction;
    }


    private void showOrtsterminPopup (MouseEvent e) {
        if (ortsterminPopup == null) {
            ortsterminPopup = new JPopupMenu("Ortstermin");
            JMenuItem loeschItem = new JMenuItem(getOrtsterminLoeschAction());
            ortsterminPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = ortsterminTabelle.rowAtPoint(origin);

            if (row != -1) {
                ortsterminTabelle.setRowSelectionInterval(row, row);
                // Die letzte (leere) Zeile kann natürlich nicht gelöscht werden:
                if (row < ortsterminModel.getList().size()) {
                    getOrtsterminLoeschAction().setEnabled(true);
                } else {
                    getOrtsterminLoeschAction().setEnabled(false);
                }
                ortsterminPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private JTable getAbscheiderTabelle() {
        if (abscheiderTabelle == null) {
            abscheiderTabelle = new JTable(abscheiderModel);

            // Wenn die Spaltengröße sich verändert, verändert sich nur die Nachbarspalte mit
            abscheiderTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

            // Es darf immer nur eine Zeile ausgewählt werden
            abscheiderTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            abscheiderTabelle.setColumnSelectionAllowed(false);

            // Mal als Beispiel, wie der Text in einigen Spalten zentriert werden kann
            DefaultTableCellRenderer centeredRenderer = new DefaultTableCellRenderer();
            centeredRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            // Die Tabellen-Spalten werden angepasst
            TableColumn column = null;
            for (int i = 0; i < abscheiderTabelle.getColumnCount(); i++) {
                column = abscheiderTabelle.getColumnModel().getColumn(i);
                if (i == 0) {
                    // Die erste Spalte soll zentriert sein...
                    column.setCellRenderer(centeredRenderer);

                    column.setMaxWidth(100);
                    column.setPreferredWidth(75);
                } else if (i == 1) {
                    // ... die zweite auch
                    column.setCellRenderer(centeredRenderer);

                    column.setMaxWidth(80);
                    column.setPreferredWidth(60);
                }
            }

            // MouseListener für Doppelklick und Rechtsklick
            abscheiderTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = abscheiderTabelle.rowAtPoint(origin);

                        if (row != -1) {
                            Anh49Abscheiderdetails absch = (Anh49Abscheiderdetails) abscheiderModel.getObjectAtRow(row);
                            log.debug("(Anh49DetailsPanel.abscheiderTabelle) "
                            		+ "Doppelklick auf: " + absch);
                            editAbscheider(absch);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    showAbscheiderPopup(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    showAbscheiderPopup(e);
                }
            });

            abscheiderTabelle.getInputMap().put((KeyStroke)getAbscheiderLoeschAction().getValue(Action.ACCELERATOR_KEY), getAbscheiderLoeschAction().getValue(Action.NAME));
            abscheiderTabelle.getActionMap().put(getAbscheiderLoeschAction().getValue(Action.NAME), getAbscheiderLoeschAction());
        }
        return abscheiderTabelle;
    }

    private JTable getOrtsterminTabelle() {
        if (ortsterminTabelle == null) {
            ortsterminTabelle = new SelectTable(ortsterminModel);

            // Wenn die Spaltengröße sich verändert, verändert sich nur die Nachbarspalte mit
            ortsterminTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

            // Die Größen der Spalten werden angepasst
            TableColumn column = null;
            for (int i = 0; i < ortsterminTabelle.getColumnCount(); i++) {
                column = ortsterminTabelle.getColumnModel().getColumn(i);
                if (i == 0) {
                    column.setMaxWidth(100);
                    column.setPreferredWidth(75);
                }
            }

            ortsterminTabelle.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    showOrtsterminPopup(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    showOrtsterminPopup(e);
                }
            });

            ortsterminTabelle.getInputMap().put((KeyStroke)getOrtsterminLoeschAction().getValue(Action.ACCELERATOR_KEY), getOrtsterminLoeschAction().getValue(Action.NAME));
            ortsterminTabelle.getActionMap().put(getOrtsterminLoeschAction().getValue(Action.NAME), getOrtsterminLoeschAction());

        }
        return ortsterminTabelle;
    }

    public void speichernOrtstermin() {
        List<?> otListe = ortsterminModel.getList();
        for (int i = 0; i < otListe.size(); i++) {
            Anh49Ortstermine ot = (Anh49Ortstermine) otListe.get(i);
            Anh49Ortstermine.saveOrUpdateOrtstermin((Anh49Ortstermine) ot);
        }
        ortsterminModel.updateList();
    }

    public void setFachdaten(Anh49Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
    }

    public void updateForm() {
        if (fachdaten != null) {
            abscheiderModel.setFachdaten(fachdaten);
            ortsterminModel.setFachdaten(fachdaten);
        }
    }

    public void clearForm() {
        // Hier füllen wir das Abscheider-TableModel mit einer leeren Liste
        abscheiderModel.setList(new ArrayList<Anh49Abscheiderdetails>());
        ortsterminModel.setList(new ArrayList<Anh49Ortstermine>());
    }

    public void enableAll(boolean enabled) {
        if (!(enabled && (fachdaten == null))) {
            getAbscheiderTabelle().setEnabled(enabled);
            getOrtsterminTabelle().setEnabled(enabled);
            getSpeichernButton().setEnabled(enabled);
            getAbscheiderLoeschAction().setEnabled(enabled);
            getAbscheiderNeuAction().setEnabled(enabled);
            getOrtsterminLoeschAction().setEnabled(enabled);
        }
    }

    @Override
    public String getName() {
        return name;
    }
    private JButton getSpeichernButton() {
        if (speichernButton == null) {
            speichernButton = new JButton("Ortstermine speichern");

            speichernButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    speichernOrtstermin();
                }
            });
        }

        return speichernButton;
    }
}
