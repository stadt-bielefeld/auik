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
package de.bielefeld.umweltamt.aui.module;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.module.importer.AbstractImporter;
import de.bielefeld.umweltamt.aui.module.importer.AbstractImporter.ImporterException;
import de.bielefeld.umweltamt.aui.module.importer.AbwasserImporter;
import de.bielefeld.umweltamt.aui.module.importer.SielhautImporter;
import de.bielefeld.umweltamt.aui.utils.FormHelper;

/**
 * Generic importer module
 */
public class Import extends AbstractModul {

    private JComboBox<ImportType> typeCBox;
    private JLabel typeLabel;
    protected JButton fileButton;
    protected JButton importButton;
    protected JLabel fileLabel;
    protected JLabel descriptionLabel;
    protected JScrollPane listScroller;
    protected JTable table;
    private AbstractImporter importer;

    private File importFile;

    private enum ImportType {
        ABWASSER("Abwasser"),
        SIELHAUT("Sielhaut");
        private final String text;
        ImportType(final String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }

    public Import() {
        this.fileButton = new JButton("Datei wählen");
        this.fileLabel = new JLabel();
        this.descriptionLabel = new JLabel();
        this.typeCBox = new JComboBox<ImportType>(ImportType.values());
        this.switchImporter((ImportType)typeCBox.getSelectedItem());
        this.listScroller = new JScrollPane(this.table);
        this.typeLabel = new JLabel("Typ");

        this.importButton = new JButton("Importieren");

        //Action listeners
        this.typeCBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                switchImporter((ImportType)typeCBox.getSelectedItem());
                if (Import.this.importFile != null) {
                    parseFile(Import.this.importFile);
                }
            }
        });
        this.fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = Import.this.frame
                    .openFile(new String[] {"txt"});

                if (file != null) {
                    importer.reset();
                    parseFile(file);
                    Import.this.table.selectAll();
                }
            }
        });

        this.importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Import.this.importer.doImport();
                    String importResult = importer.createResultHtml();
                    GUIManager.getInstance().showInfoMessage(
                            importResult, "Importergebnis");
                    Import.this.frame.changeStatus(
                        String.format("%d Datensätze importiert!",
                        importer.getResultCount())
                    );
                } catch (ImporterException ie) {
                    GUIManager.getInstance().showErrorMessage(
                        String.format("Beim Import ist ein Fehler aufgetreten: %s", ie.getMsg()),
                        "Import fehlgeschlagen");
                }
            }
        });
    }

    /**
     * Create import table with action listener
     * @param importer Importer to use
     * @return New table
     */
    private JTable createImportTable(AbstractImporter importer) {
        JTable table = new JTable(importer) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row)) {
                    c.setBackground(new Color(228, 244, 253));
                } else {
                    c.setBackground(new Color(0, 0, 0, 0));
                }
                return c;
            }
        };
        table.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting()) {
                        return;
                    }
                    if (table.getSelectedRowCount() > 0) {
                        int[] selectedRowIndices = table.getSelectedRows();
                        for (int index: selectedRowIndices) {
                            if (!importer.isRowSelectable(index)) {
                                table.removeRowSelectionInterval(index, index);
                            }
                        }
                    }
                }

        });
        return table;
    }

    @Override
    public String getName() {
        return "Import";
    }

    @Override
    public String getCategory() {
        return "Labor";
    }

    @Override
    public Icon getIcon() {
        return super.getIcon("ksysguard.png");
    }

    @Override
    public JPanel getPanel() {
        if (this.panel != null) {
            return this.panel;
        }

        JPanel innerPanel = FormHelper.createDialogPanel();
        int row = 0;

        // Zeile 1: Datei wählen + Dateiname
        FormHelper.add(innerPanel, fileButton, 0, row, 1);
        FormHelper.add(innerPanel, fileLabel, 1, row++, 1,
            GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        // Zeile 2: Typ
        FormHelper.addLabelField(innerPanel, row++, 0, typeLabel, typeCBox);

        // Tabelle mit den Datensätzen (wächst mit dem Fenster)
        listScroller.setPreferredSize(new Dimension(0, 400));
        GridBagConstraints gbc = FormHelper.constraints(0, row++,
            GridBagConstraints.REMAINDER);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        innerPanel.add(listScroller, gbc);

        // Beschreibung
        FormHelper.addFull(innerPanel, row++, descriptionLabel);

        // Importieren
        FormHelper.add(innerPanel, importButton, 0, row++, 1);

        this.panel = innerPanel;

        return this.panel;
    }

    /**
     * Parse the given file using the current importer.
     * @param file File to parse
     */
    private void parseFile(File file) {
        importFile = file;
        try {
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            fileLabel.setText(file.getName());
            importer.parseFile(file);
            table.selectAll();
        } catch (ImporterException e) {
            GUIManager.getInstance()
                .showErrorMessage(e.getMsg(), "Einlesen der Importdatei fehlgeschlagen");
        } finally {
            panel.setCursor(Cursor.getDefaultCursor());
        }

    }

    private void switchImporter(ImportType type) {
        switch (type) {
            case ABWASSER:
                this.importer = new AbwasserImporter();
                break;
            case SIELHAUT:
                this.importer = new SielhautImporter();
                break;
            default:
                return;
        }
        this.table = createImportTable(this.importer);
        this.importer.setParentTable(this.table);
        this.descriptionLabel.setText(this.importer.getDescriptionString());
        if (this.listScroller != null) {
            this.listScroller.setViewportView(this.table);
        } else {
            this.listScroller= new JScrollPane(this.table);
        }
    }
}
