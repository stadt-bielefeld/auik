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
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.AbscheiderEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh49AbscheiderModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Das Abscheiderdetails-Tab beim Objekt bearbeiten
 * @author Gerhard Genuit
 */
public class Anh49DetailsPanel extends JPanel {
    private static final long serialVersionUID = -800584079054813622L;

    /**
     * Ein TableModel für eine Tabelle mit Abscheider-Details.
     * @author David Klotz, Gerhard Genuit
     */
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;

    private BasisObjektBearbeiten hauptModul;

    private Anh49Fachdaten fachdaten;
    private Anh49AbscheiderModel abscheiderModel;

    private JTable abscheiderTabelle;
    private Action abscheiderLoeschAction;
    private Action abscheiderNeuAction;
    private JPopupMenu abscheiderPopup;

    public Anh49DetailsPanel(BasisObjektBearbeiten hauptModul) {
        name = "Details und Ortstermine";
        this.hauptModul = hauptModul;

        abscheiderModel = new Anh49AbscheiderModel();

        TableFocusListener tfl = TableFocusListener.getInstance();
        getAbscheiderTabelle().addFocusListener(tfl);

        JScrollPane abscheiderScroller = new JScrollPane(
            getAbscheiderTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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

        FormLayout layout = new FormLayout("150dlu:grow, 100dlu", // Spalten
            "100dlu:grow, 3dlu, pref"); // Zeilen

        PanelBuilder builder = new PanelBuilder(layout, this);
        CellConstraints cc = new CellConstraints();

        builder.add(abscheiderScroller, cc.xyw(1, 1, 2));
    }

    private Action getAbscheiderLoeschAction() {
        if (abscheiderLoeschAction == null) {
            abscheiderLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = -4757595254932715764L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getAbscheiderTabelle().getSelectedRow();
                    if (row != -1
                        && getAbscheiderTabelle().getEditingRow() == -1) {
                        Anh49Abscheiderdetails abscheider = abscheiderModel
                            .getObjectAtRow(row);

                        if (GUIManager
                            .getInstance()
                            .showQuestion(
                                "Soll der Abscheider "
                                    + abscheider
                                    + " wirklich inkl. aller Detailinformationen gelöscht werden?",
                                "Löschen bestätigen")) {
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
            abscheiderLoeschAction.putValue(Action.MNEMONIC_KEY, Integer.valueOf(
                KeyEvent.VK_L));
            abscheiderLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
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
            abscheiderNeuAction.putValue(Action.MNEMONIC_KEY, Integer.valueOf(
                KeyEvent.VK_N));
            // abscheiderNeuAction.putValue(Action.ACCELERATOR_KEY,
            // KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return abscheiderNeuAction;
    }

    /**
     * öffnet einen Dialog um einen Abscheider-Datensatz zu bearbeiten.
     * @param absch Der Abscheider
     */
    public void editAbscheider(Anh49Abscheiderdetails absch) {

        AbscheiderEditor editDialog = new AbscheiderEditor(absch,
            hauptModul.getFrame());
        editDialog.setLocationRelativeTo(hauptModul.getFrame());

        editDialog.setVisible(true);

        if (editDialog.wasSaved() && (editDialog.getDetails() != null)) {
            // Die Liste updaten, damit unsere Änderungen auch angezeigt werden
            abscheiderModel.updateList();

            // Den bearbeiteten Abscheider wieder in der Tabelle auswählen
//            Anh49Abscheiderdetails details = editDialog.getDetails();
//            int row = abscheiderModel.getList().indexOf(details);
//            if (row != -1) {
//                getAbscheiderTabelle().setRowSelectionInterval(row, row);
//                getAbscheiderTabelle().scrollRectToVisible(
//                    getAbscheiderTabelle().getCellRect(row, 0, true));
//            }
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

            // Das Menü zeigen wir aber immer an, zum neu Anlegen eines
            // Abscheiders
            abscheiderPopup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private JTable getAbscheiderTabelle() {
        if (abscheiderTabelle == null) {
            abscheiderTabelle = new JTable(abscheiderModel);

            // Wenn die Spaltengröße sich verändert, verändert sich nur die
            // Nachbarspalte mit
            abscheiderTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

            // Es darf immer nur eine Zeile ausgewählt werden
            abscheiderTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            abscheiderTabelle.setColumnSelectionAllowed(false);

            // Mal als Beispiel, wie der Text in einigen Spalten zentriert
            // werden kann
            DefaultTableCellRenderer centeredRenderer = new DefaultTableCellRenderer();
            centeredRenderer
                .setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

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
            abscheiderTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = abscheiderTabelle.rowAtPoint(origin);

                            if (row != -1) {
                                Anh49Abscheiderdetails absch = (Anh49Abscheiderdetails) abscheiderModel
                                    .getObjectAtRow(row);
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

            abscheiderTabelle.getInputMap().put(
                (KeyStroke) getAbscheiderLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getAbscheiderLoeschAction().getValue(Action.NAME));
            abscheiderTabelle.getActionMap().put(
                getAbscheiderLoeschAction().getValue(Action.NAME),
                getAbscheiderLoeschAction());
        }
        return abscheiderTabelle;
    }

    public void setFachdaten(Anh49Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
    }

    public void updateForm(Anh49Fachdaten fachdaten) {
        if (fachdaten != null) {
            abscheiderModel.setFachdaten(fachdaten);
        }
    }

    public void clearForm() {
        // Hier füllen wir das Abscheider-TableModel mit einer leeren Liste
        abscheiderModel.setList(new ArrayList<Anh49Abscheiderdetails>());
    }

    public void enableAll(boolean enabled) {
        if (!(enabled && (fachdaten == null))) {
            getAbscheiderTabelle().setEnabled(enabled);
            getAbscheiderLoeschAction().setEnabled(enabled);
            getAbscheiderNeuAction().setEnabled(enabled);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
