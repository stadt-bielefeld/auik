/**
 * Copyright 2005-2042, Stadt Bielefeld
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
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Verwaltungsverfahren;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.AbscheiderEditor;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * A panel for "Anhang 49" with "Anschreiben und Verwaltungsverfahren"
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class Anh49VerwaltungsverfahrenPanel extends JPanel {
    private static final long serialVersionUID = -35108917703012183L;

    /** Logging */
    protected final AuikLogger log = AuikLogger.getLogger();

    /**
     * Ein TableModel für eine Tabelle mit Abscheider-Ortsterminen.
     *
     * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce
     *         (u633z)</a>
     */
    private class Anh49VerwaltungsverfahrenModel extends EditableListTableModel {
        private static final long serialVersionUID = -7162535797673486082L;

        /** The viewing frame */
        private BasisObjektBearbeiten hauptModul;
        /** The underlying data */
        private Anh49Fachdaten fachdaten;

        /**
         * The underlying model for the table in the panel with the colunms
         * "Datum", "Maßnahme", "SachbearbeiterIn", "Wiedervorlage" and
         * "abgeschlossen".
         */
        public Anh49VerwaltungsverfahrenModel(BasisObjektBearbeiten hauptModul) {
            super(new String[] {"Datum", "Maßnahme", "SachbearbeiterIn",
                    "Wiedervorlage", "abgeschlossen"}, false, true);
            this.hauptModul = hauptModul;
        }

        /**
         * Set the data object and update the list
         *
         * @param fachdaten The data object
         */
        private void setFachdaten(Anh49Fachdaten fachdaten) {
            this.fachdaten = fachdaten;
            updateList();
        }

        /**
         * Get the value of a cell
         *
         * @param objectAtRow The data object
         * @param columnIndex The requested column
         * @return The value of the cell
         */
        @Override
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Anh49Verwaltungsverfahren verwaltungsverfahren = (Anh49Verwaltungsverfahren) objectAtRow;

            switch (columnIndex) {
                case 0:
                    return verwaltungsverfahren.getDatum();
                case 1:
                    return verwaltungsverfahren.getMassnahme();
                case 2:
                    return verwaltungsverfahren.getSachbearbeiterIn();
                case 3:
                    return verwaltungsverfahren.getWiedervorlage();
                case 4:
                    return verwaltungsverfahren.isAbgeschlossen();
                default:
                    return null;
            }
        }

        /**
         * When a row is removed in the view, remove it from the model/database
         *
         * @param The row which was removed
         * @return If the data was removed
         */
        @Override
        public boolean objectRemoved(Object objectAtRow) {
            return Anh49Verwaltungsverfahren
                    .removeVerwaltungsverfahren((Anh49Verwaltungsverfahren) objectAtRow);
        }

        /** Update the table and fire a changed event. */
        @Override
        public void updateList() {
            if (fachdaten != null) {
                setList(Anh49Verwaltungsverfahren
                        .getVerwaltungsverfahren(fachdaten));
            }
            fireTableDataChanged();
        }

        /**
         * Edit a "Verwaltungsverfahren"
         */
        @Override
        public void editObject(Object objectAtRow, int columnIndex,
                Object newValue) {
            Anh49Verwaltungsverfahren verwaltungsverfahren = (Anh49Verwaltungsverfahren) objectAtRow;

            switch (columnIndex) {
                case 0:
                    try {
                        verwaltungsverfahren.setDatum(new SimpleDateFormat(
                                AuikUtils.DATUMSFORMAT)
                                .parse((String) newValue));
                    } catch (ParseException parseException) {
//                        parseException.printStackTrace();
                        this.hauptModul.getFrame().changeStatus(
                                "Bitte geben Sie das Datum in der "
                                        + "Form TT.MM.JJJJ ein!",
                                HauptFrame.ERROR_COLOR);
                    }
                    break;
                case 1:
                    // TODO: This shall be a dropdown thingy
                    verwaltungsverfahren.setMassnahme((String) newValue);
                    break;
                case 2:
                    // TODO: This shall be a dropdown thingy
                    verwaltungsverfahren.setSachbearbeiterIn((String) newValue);
                    break;
                case 3:
                    // TODO: The parsing to date should go somewhere central
                    // (s.a)
                    verwaltungsverfahren.setWiedervorlage((Date) newValue);
                    break;
                case 4:
                    verwaltungsverfahren.setAbgeschlossen((Boolean) newValue);
                    break;
                default:
                    break;
            }
        }

        /**
         * Create a new "Verwaltungsverfahren"
         *
         * @return The new "Verwaltungsverfahren" object
         */
        @Override
        public Object newObject() {
            Anh49Verwaltungsverfahren verwaltungsverfahren = new Anh49Verwaltungsverfahren();
            verwaltungsverfahren.setAnh49Fachdaten(fachdaten);
            return verwaltungsverfahren;
        }

        /**
         * Get the "Verwaltungsverfahren" at row rowIndex
         *
         * @param rowIndex The index of the row
         * @return The "Verwaltungsverfahren" at the given row index
         */
        public Anh49Verwaltungsverfahren getRow(int rowIndex) {
            return (Anh49Verwaltungsverfahren) getObjectAtRow(rowIndex);
        }
    }

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private Anh49Fachdaten fachdaten;
    private Anh49VerwaltungsverfahrenModel verwaltungsverfahrenModel;

    private JTable verwaltungsverfahrenTabelle;

    private Action verwaltungsverfahrenNeuAction;
    private Action verwaltungsverfahrenLoeschenAction;

    private JButton speichernButton;

    public Anh49VerwaltungsverfahrenPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Anschreiben und Verwaltungsverfahren";
        this.hauptModul = hauptModul;

        this.verwaltungsverfahrenModel = new Anh49VerwaltungsverfahrenModel(
                this.hauptModul);

        TableFocusListener tfl = TableFocusListener.getInstance();
        getVerwaltungsverfahrenTabelle().addFocusListener(tfl);

        JScrollPane verwaltungsverfahrenScroller = new JScrollPane(
                getVerwaltungsverfahrenTabelle(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        FormLayout layout = new FormLayout("150dlu:grow, 100dlu", // Spalten
                "100dlu:grow, 3dlu, pref"); // Zeilen

        PanelBuilder builder = new PanelBuilder(layout, this);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        builder.add(verwaltungsverfahrenScroller, cc.xyw(1, 1, 2));
        builder.add(getSpeichernButton(), cc.xy(2, 3));
    }

    private Action getVerwaltungsverfahrenLoeschAction() {
        if (verwaltungsverfahrenLoeschenAction == null) {
            verwaltungsverfahrenLoeschenAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = -5091242167116909387L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getVerwaltungsverfahrenTabelle().getSelectedRow();
                    if (row != -1
                            && getVerwaltungsverfahrenTabelle().getEditingRow() == -1) {
                        Anh49Verwaltungsverfahren verwaltungsverfahren = verwaltungsverfahrenModel
                                .getRow(row);

                        if (hauptModul
                                .getFrame()
                                .showQuestion(
                                        "Soll das Verwaltungsverf "
                                                + verwaltungsverfahren
                                                + " wirklich inkl. aller Detailinformationen gelöscht werden?",
                                        "Löschen bestätigen")) {
                            verwaltungsverfahrenModel.removeRow(row);
                            log.debug("Verwaltungsverf " + verwaltungsverfahren
                                    + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von " + verwaltungsverfahren
                                    + " wurde abgebrochen!");
                        }
                    }
                }
            };
            verwaltungsverfahrenLoeschenAction.putValue(Action.MNEMONIC_KEY,
                    new Integer(KeyEvent.VK_L));
            verwaltungsverfahrenLoeschenAction.putValue(Action.ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return verwaltungsverfahrenLoeschenAction;
    }

    private Action getVerwaltungsverfahrenNeuAction() {
        if (verwaltungsverfahrenNeuAction == null) {
            verwaltungsverfahrenNeuAction = new AbstractAction(
                    "Neues Verwaltungsverf") {
                private static final long serialVersionUID = 2151766435098484413L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    Anh49Abscheiderdetails neuerAbscheider = new Anh49Abscheiderdetails();
                    neuerAbscheider.setAnh49Fachdaten(fachdaten);
                    editVerwaltungsverfahren(neuerAbscheider);
                }
            };
            verwaltungsverfahrenNeuAction.putValue(Action.MNEMONIC_KEY,
                    new Integer(KeyEvent.VK_N));
            // abscheiderNeuAction.putValue(Action.ACCELERATOR_KEY,
            // KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return verwaltungsverfahrenNeuAction;
    }

    /**
     * öffnet einen Dialog um einen Abscheider-Datensatz zu bearbeiten.
     *
     * @param absch Der Abscheider
     */
    public void editVerwaltungsverfahren(Anh49Abscheiderdetails absch) {

        AbscheiderEditor editDialog = new AbscheiderEditor(absch,
                hauptModul.getFrame());
        editDialog.setLocationRelativeTo(hauptModul.getFrame());

        editDialog.setVisible(true);

        if (editDialog.wasSaved() && (editDialog.getDetails() != null)) {
            // Die Liste updaten, damit unsere Änderungen auch angezeigt werden
            verwaltungsverfahrenModel.updateList();

            // Den bearbeiteten Abscheider wieder in der Tabelle auswählen
            Anh49Abscheiderdetails details = editDialog.getDetails();
            int row = verwaltungsverfahrenModel.getList().indexOf(details);
            if (row != -1) {
                getVerwaltungsverfahrenTabelle().setRowSelectionInterval(row,
                        row);
                getVerwaltungsverfahrenTabelle().scrollRectToVisible(
                        getVerwaltungsverfahrenTabelle().getCellRect(row, 0,
                                true));
            }
        }
    }

    private JTable getVerwaltungsverfahrenTabelle() {
        if (verwaltungsverfahrenTabelle == null) {
            verwaltungsverfahrenTabelle = new JTable(verwaltungsverfahrenModel);

            // Wenn die Spaltengröße sich verändert, verändert sich nur die
            // Nachbarspalte mit
            verwaltungsverfahrenTabelle
                    .setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

            // Es darf immer nur eine Zeile ausgewählt werden
            verwaltungsverfahrenTabelle
                    .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            verwaltungsverfahrenTabelle.setColumnSelectionAllowed(false);

            // Mal als Beispiel, wie der Text in einigen Spalten zentriert
            // werden kann
            DefaultTableCellRenderer centeredRenderer = new DefaultTableCellRenderer();
            centeredRenderer
                    .setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            // Die Tabellen-Spalten werden angepasst
            TableColumn column = null;
            for (int i = 0; i < verwaltungsverfahrenTabelle.getColumnCount(); i++) {
                column = verwaltungsverfahrenTabelle.getColumnModel()
                        .getColumn(i);
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
            verwaltungsverfahrenTabelle
                    .addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            if ((e.getClickCount() == 2)
                                    && (e.getButton() == 1)) {
                                Point origin = e.getPoint();
                                int row = verwaltungsverfahrenTabelle
                                        .rowAtPoint(origin);

                                if (row != -1) {
                                    Anh49Abscheiderdetails absch = (Anh49Abscheiderdetails) verwaltungsverfahrenModel
                                            .getObjectAtRow(row);
                                    log.debug("(Anh49DetailsPanel.abscheiderTabelle) "
                                            + "Doppelklick auf: " + absch);
                                    editVerwaltungsverfahren(absch);
                                }
                            }
                        }
                    });

            verwaltungsverfahrenTabelle.getInputMap()
                    .put((KeyStroke) getVerwaltungsverfahrenLoeschAction()
                            .getValue(Action.ACCELERATOR_KEY),
                            getVerwaltungsverfahrenLoeschAction().getValue(
                                    Action.NAME));
            verwaltungsverfahrenTabelle
                    .getActionMap()
                    .put(getVerwaltungsverfahrenLoeschAction().getValue(
                            Action.NAME), getVerwaltungsverfahrenLoeschAction());
        }
        return verwaltungsverfahrenTabelle;
    }

    public void speichernVerwaltungsverfahren() {
        List<Anh49Verwaltungsverfahren> verwaltungsverfahrenListe =
            (List<Anh49Verwaltungsverfahren>) verwaltungsverfahrenModel.getList();
        for (Anh49Verwaltungsverfahren verwaltungsverfahren :
                verwaltungsverfahrenListe) {
            Anh49Verwaltungsverfahren
                    .saveOrUpdateVerwaltungsverfahren(verwaltungsverfahren);
        }
        verwaltungsverfahrenModel.updateList();
    }

    public void setFachdaten(Anh49Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
    }

    public void updateForm() {
        if (fachdaten != null) {
            verwaltungsverfahrenModel.setFachdaten(fachdaten);
        }
    }

    public void clearForm() {
        verwaltungsverfahrenModel
                .setList(new ArrayList<Anh49Verwaltungsverfahren>());
    }

    public void enableAll(boolean enabled) {
        if (!(enabled && (fachdaten == null))) {
            getVerwaltungsverfahrenTabelle().setEnabled(enabled);
            getVerwaltungsverfahrenNeuAction().setEnabled(enabled);
            getVerwaltungsverfahrenLoeschAction().setEnabled(enabled);
            getSpeichernButton().setEnabled(enabled);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    private JButton getSpeichernButton() {
        if (speichernButton == null) {
            speichernButton = new JButton("Verwaltungsverf speichern");

            speichernButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    speichernVerwaltungsverfahren();
                }
            });
        }

        return speichernButton;
    }
}
