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
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Verwaltungsverf;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh49VerwaltungsverfahrenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * A panel for "Anhang 49" with "Anschreiben und Verwaltungsverfahren"
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public class Anh49VerwaltungsverfahrenPanel extends JPanel {
    private static final long serialVersionUID = -35108917703012183L;

    /** Logging */
    protected final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private Anh49Fachdaten fachdaten;
    private Anh49VerwaltungsverfahrenModel verwaltungsverfahrenModel;

    private JTable verwaltungsverfahrenTabelle;

    private Action verwaltungsverfahrenLoeschenAction;
    private JPopupMenu verwaltungsverfahrenPopup;

    private JButton speichernButton;

    public Anh49VerwaltungsverfahrenPanel() {
        this.name = "Anschreiben und Verwaltungsverfahren";

        this.verwaltungsverfahrenModel =
            new Anh49VerwaltungsverfahrenModel();

        getVerwaltungsverfahrenTabelle()
                .addFocusListener(TableFocusListener.getInstance());

        JScrollPane verwaltungsverfahrenScroller = new JScrollPane(
                getVerwaltungsverfahrenTabelle(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        FormLayout layout = new FormLayout("pref:grow, pref", // Spalten
                "f:100dlu:grow, 3dlu, pref"); // Zeilen

        PanelBuilder builder = new PanelBuilder(layout, this);

        CellConstraints cc = new CellConstraints();

        builder.add(verwaltungsverfahrenScroller, cc.xyw(1, 1, 2));
        builder.add(getSpeichernButton(), cc.xy(2, 3));
    }

    private Action getVerwaltungsverfahrenLoeschAction() {
        if (verwaltungsverfahrenLoeschenAction == null) {
            verwaltungsverfahrenLoeschenAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID =
                    -5091242167116909387L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow =
                        getVerwaltungsverfahrenTabelle().getSelectedRow();
                    int editingRow  =
                        getVerwaltungsverfahrenTabelle().getEditingRow();
                    if (selectedRow != -1 && editingRow == -1) {
                        Anh49Verwaltungsverf verwaltungsverfahren =
                            verwaltungsverfahrenModel.getRow(selectedRow);

                        if (GUIManager.getInstance().showQuestion(
                                "Soll das Verwaltungsverfahren "
                                    + verwaltungsverfahren
                                    + " wirklich gelöscht werden?",
                                "Löschen bestätigen")) {
                            verwaltungsverfahrenModel.removeRow(selectedRow);
                            log.debug("Verwaltungsverfahren "
                                    + verwaltungsverfahren
                                    + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von " + verwaltungsverfahren
                                    + " wurde abgebrochen!");
                        }
                    }
                }
            };
            verwaltungsverfahrenLoeschenAction.putValue(
                    Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
            verwaltungsverfahrenLoeschenAction.putValue(Action.ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return verwaltungsverfahrenLoeschenAction;
    }

    private void showVerwaltungsverfahrenPopup (MouseEvent e) {
        if (verwaltungsverfahrenPopup == null) {
            verwaltungsverfahrenPopup = new JPopupMenu();
            JMenuItem loeschItem = new JMenuItem(
                    getVerwaltungsverfahrenLoeschAction());
            verwaltungsverfahrenPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = verwaltungsverfahrenTabelle.rowAtPoint(origin);

            if (row != -1) {
                verwaltungsverfahrenTabelle.setRowSelectionInterval(row, row);
                // Die letzte (leere) Zeile kann natürlich nicht gelöscht werden:
                if (row < verwaltungsverfahrenModel.getList().size()) {
                    getVerwaltungsverfahrenLoeschAction().setEnabled(true);
                } else {
                    getVerwaltungsverfahrenLoeschAction().setEnabled(false);
                }
                verwaltungsverfahrenPopup.show(
                        e.getComponent(), e.getX(), e.getY());
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
            DefaultTableCellRenderer centeredRenderer =
                new DefaultTableCellRenderer();
            centeredRenderer
                    .setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            // Die Tabellen-Spalten werden angepasst
            // TODO: Die anderen Panels auch korrigieren...
            // Eine for-Schleife und darin nach dem Index abfragen...
            // #facepalm
//            TableColumn column = null;
//            for (int i = 0; i < verwaltungsverfahrenTabelle.getColumnCount(); i++) {
//                column = verwaltungsverfahrenTabelle.getColumnModel()
//                        .getColumn(i);
//                if (i == 0) {
//                    // Die erste Spalte soll zentriert sein...
//                    column.setCellRenderer(centeredRenderer);
//
//                    column.setMaxWidth(100);
//                    column.setPreferredWidth(75);
//                } else if (i == 1) {
//                    // ... die zweite auch
//                    column.setCellRenderer(centeredRenderer);
//
//                    column.setMaxWidth(80);
//                    column.setPreferredWidth(60);
//                }
//            }
            // Formatting columns
            TableColumnModel tcm = verwaltungsverfahrenTabelle.getColumnModel();
            tcm.getColumn(0).setMaxWidth(100);
            tcm.getColumn(0).setPreferredWidth(80);

            /* Set up the JComboBoxes with the default values */
            JComboBox defaultMassnahmenBox = new JComboBox();
            defaultMassnahmenBox.addItem("Anhörung");
            defaultMassnahmenBox.addItem("Ordnungsverfügung");
            defaultMassnahmenBox.setEditable(true);
            defaultMassnahmenBox.setBorder(BorderFactory.createEmptyBorder());
            tcm.getColumn(1).setCellEditor(
                    new DefaultCellEditor(defaultMassnahmenBox));

            JComboBox defaultSachbearbeiterBox = new JComboBox();
            defaultSachbearbeiterBox.addItem("360.12");
            defaultSachbearbeiterBox.addItem("360.33");
            defaultSachbearbeiterBox.setEditable(true);
            defaultSachbearbeiterBox.setBorder(
                    BorderFactory.createEmptyBorder());
            tcm.getColumn(2).setCellEditor(
                    new DefaultCellEditor(defaultSachbearbeiterBox));

            tcm.getColumn(3).setMaxWidth(100);
            tcm.getColumn(3).setPreferredWidth(80);
            tcm.getColumn(4).setMaxWidth(100);
            tcm.getColumn(4).setPreferredWidth(80);

            // MouseListener für Rechtsklick
            verwaltungsverfahrenTabelle
                    .addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            showVerwaltungsverfahrenPopup(e);
                        }
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            showVerwaltungsverfahrenPopup(e);
                        }
                    });

            verwaltungsverfahrenTabelle.getInputMap().put(
                    (KeyStroke) getVerwaltungsverfahrenLoeschAction()
                            .getValue(Action.ACCELERATOR_KEY),
                    getVerwaltungsverfahrenLoeschAction().getValue(
                            Action.NAME));
            verwaltungsverfahrenTabelle.getActionMap().put(
                    getVerwaltungsverfahrenLoeschAction().getValue(Action.NAME),
                    getVerwaltungsverfahrenLoeschAction());
        }
        return verwaltungsverfahrenTabelle;
    }

    public void speichernVerwaltungsverfahren() {
        List<?> liste = verwaltungsverfahrenModel.getList();
        for (Object verwaltungsverfahren : liste) {
            Anh49Verwaltungsverf.merge(
                (Anh49Verwaltungsverf)verwaltungsverfahren);
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
                .setList(new ArrayList<Anh49Verwaltungsverf>());
    }

    public void enableAll(boolean enabled) {
        if (!(enabled && (fachdaten == null))) {
            getVerwaltungsverfahrenTabelle().setEnabled(enabled);
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
            speichernButton = new JButton("Änderungen speichern");

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
