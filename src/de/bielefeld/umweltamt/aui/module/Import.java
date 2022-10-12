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

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.module.importer.AbstractImporter;
import de.bielefeld.umweltamt.aui.module.importer.AbwasserImporter;
import de.bielefeld.umweltamt.aui.module.importer.SielhautImporter;
import de.bielefeld.umweltamt.aui.module.importer.AbstractImporter.ImporterException;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;

/**
 * Generic importer module
 */
public class Import extends AbstractModul {

    private JComboBox<ImportType> typeCBox;
    private JLabel typeLabel;
    protected JButton fileButton;
    protected JButton importButton;
    protected JLabel fileLabel;
    protected JLabel chooseFileStepLabel;
    protected JLabel importStepLabel;
    protected JLabel descriptionLabel;
    protected JScrollPane listScroller;
    protected JLabel chooseRecordStepLabel;
    protected JTable table;
    private AbstractImporter importer;

    private File importFile;

    private enum ImportType {
        SIELHAUT("Sielhaut"),
        ABWASSER("Abwasser");
        private final String text;
        ImportType(final String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }

    private enum ImportStep {CHOOSE_FILE, CHOOSE_RECORDS, IMPORT, DONE}

    public Import() {
        this.fileButton = new JButton("Datei wählen");
        this.fileLabel = new JLabel();
        this.chooseFileStepLabel = new JLabel();
        this.typeCBox = new JComboBox<ImportType>(ImportType.values());
        this.switchImporter((ImportType)typeCBox.getSelectedItem());
        this.listScroller = new JScrollPane(this.table);
        this.chooseRecordStepLabel = new JLabel();
        this.typeLabel = new JLabel("Typ");

        this.descriptionLabel = new JLabel("<html><table width='100%'>"
            + "<tr><td style='color: green;'>Grün:</td>"
            + "<td>Import m&ouml;glich: Kennnummer und Parameter "
            + "vorhanden.</td></tr>"
            + "<tr><td style='color: FF8200;'>Orange:</td>"
            + "<td>Import m&ouml;glich: Kennnummer vorhanden, "
            + "Parameter wird angelegt.</td></tr>"
            + "<tr><td style='color: red;'>Rot:</td>"
            + "<td>Zeile nicht importierbar.</td></tr>" + "</table></html>");

        this.importButton = new JButton("Importieren");
        this.importStepLabel = new JLabel(AuikUtils.getIcon("step3_grey.png",
            "Schritt Drei"));

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
                    parseFile(file);
                }
            }
        });

        this.importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Import.this.importer.doImport();
                    setImportStep(ImportStep.DONE);
                } catch (ImporterException ie) {
                    GUIManager.getInstance().showErrorMessage(
                        String.format("Beim Import ist ein Fehler aufgetreten: %s", ie.getMsg()),
                        "Import fehlgeschlagen");
                }
            }
        });

        //Update number labels
        setImportStep(ImportStep.CHOOSE_FILE);

    }

    /**
     * Create import table with action listener
     * @param importer Importer to use
     * @return New table
     */
    private JTable createImportTable(AbstractImporter importer) {
        JTable table = new JTable(importer);
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
                        //If rows are still selected, update labels
                        if (table.getSelectedRowCount() > 0) {
                            setImportStep(ImportStep.IMPORT);
                        } else {
                            setImportStep(ImportStep.CHOOSE_RECORDS);
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
    @SuppressWarnings("deprecation")
    public JPanel getPanel() {
        if (this.panel != null) {
            return this.panel;
        }

        FormLayout layout = new FormLayout("40px,5dlu,65dlu,5dlu,175dlu:g", "");
        DefaultFormBuilder b = new DefaultFormBuilder(layout);

        b.append(chooseFileStepLabel, fileButton, fileLabel);
        b.append("", typeLabel, typeCBox);
        b.appendRelatedComponentsGapRow();
        b.appendRow("f:50dlu:g");
        b.nextLine(2);

        b.append(chooseRecordStepLabel);
        b.append(listScroller, 3);
        b.appendRelatedComponentsGapRow();
        b.nextLine(2);

        b.append(""); // no label in front of the descriptions
        b.append(descriptionLabel, 3);
        b.appendRelatedComponentsGapRow();
        b.nextLine(2);

        b.append(importStepLabel, importButton);

        this.panel = b.getPanel();
        this.panel.setBorder(Paddings.DIALOG);

        return this.panel;
    }

    /**
     * Parse the given file using the current importer.
     * @param file File to parse
     */
    private void parseFile(File file) {
        importFile = file;
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        fileLabel.setText(file.getName());
        importer.parseFile(file);
        setImportStep(ImportStep.CHOOSE_RECORDS);
        table.selectAll();
        panel.setCursor(Cursor.getDefaultCursor());
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
        if (this.listScroller != null) {
            this.listScroller.setViewportView(this.table);
        } else {
            this.listScroller= new JScrollPane(this.table);
        }
    }

    private void setImportStep(ImportStep step) {
        switch(step) {
            case CHOOSE_FILE:
                this.chooseFileStepLabel.setIcon(
                    AuikUtils.getIcon("step1_w.png",
                    "Schritt Eins"));
                this.chooseRecordStepLabel.setIcon(
                    AuikUtils.getIcon("step2_grey.png",
                    "Schritt Zwei"));
                this.importStepLabel.setIcon(
                    AuikUtils.getIcon("step3_grey.png",
                    "Schritt Drei"));
                break;
            case CHOOSE_RECORDS:
                this.chooseFileStepLabel.setIcon(
                    AuikUtils.getIcon("step1_g.png",
                    "Schritt Eins"));
                this.chooseRecordStepLabel.setIcon(
                    AuikUtils.getIcon("step2_w.png",
                    "Schritt Zwei"));
                this.importStepLabel.setIcon(
                    AuikUtils.getIcon("step3_grey.png",
                    "Schritt Drei"));
                break;
            case IMPORT:
                this.chooseFileStepLabel.setIcon(
                    AuikUtils.getIcon("step1_g.png",
                    "Schritt Eins"));
                this.chooseRecordStepLabel.setIcon(
                    AuikUtils.getIcon("step2_g.png",
                    "Schritt Zwei"));
                this.importStepLabel.setIcon(
                    AuikUtils.getIcon("step3_w.png",
                    "Schritt Drei"));
                break;
            case DONE:
                this.chooseFileStepLabel.setIcon(
                    AuikUtils.getIcon("step1_g.png",
                    "Schritt Eins"));
                this.chooseRecordStepLabel.setIcon(
                    AuikUtils.getIcon("step2_g.png",
                    "Schritt Zwei"));
                this.importStepLabel.setIcon(
                    AuikUtils.getIcon("step3_g.png",
                    "Schritt Drei"));
                break;
            default:
                break;
        }

    }
}
