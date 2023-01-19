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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.atl.Sielhaut;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.selectable.SelectableSielhautModel;
import de.bielefeld.umweltamt.aui.utils.CheckBoxTableHeader;
import de.bielefeld.umweltamt.aui.utils.PDFExporter;

public class SielhautPrinter extends AbstractModul {

    private JPanel contentPanel;
    private JScrollPane tableScrollPane;

    private JTable sielhautTable;
    private SelectableSielhautModel sielhautModel;
    private TableColumn checkBoxColumn;

    private JButton printButton;

    private String outputPath = "./reports";

    @Override
    public String getName() {
        return "Sielhautsteckbriefe erstellen";
    }

    @Override
    public String getCategory() {
        return "Sielhaut";
    }

    @Override
    public JPanel getPanel() {
        return contentPanel;
    }

    @SuppressWarnings("deprecation")
    public SielhautPrinter() {
        //Create components
        //Result table
        this.sielhautTable = new JTable();
        this.sielhautModel = new SelectableSielhautModel();
        this.sielhautTable.setModel(this.sielhautModel);
        this.tableScrollPane = new JScrollPane(this.sielhautTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.checkBoxColumn
            = this.sielhautTable.getColumnModel().getColumn(0);
        this.checkBoxColumn.setCellEditor(
            this.sielhautTable.getDefaultEditor(Boolean.class));
        this.checkBoxColumn.setCellRenderer(
            this.sielhautTable.getDefaultRenderer(Boolean.class));
        this.checkBoxColumn.setHeaderRenderer(
            new CheckBoxTableHeader(new CheckBoxHeaderClickListener()));
        this.checkBoxColumn.setMaxWidth(25);
        this.checkBoxColumn.setResizable(false);
        //Checkbox column click listener
        this.sielhautTable.addMouseListener(new CheckboxColumnClickListener());

        //Toolbar
        this.printButton = new JButton("Drucken");
        this.printButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (sielhautModel.getSelected().length > 0) {
                    createPrintDialog().setVisible(true);
                } else {
                    GUIManager.getInstance()
                        .setInfoStatus("Bitte wählen sie einen Sielhautpunkt aus");
                }
            }
        });

        //Content panel
        FormLayout layout = new FormLayout(
            "3dlu, 180dlu:g, 3dlu, min(36dlu;p), 3dlu",
            "3dlu, 400dlu:g, 3dlu, 20dlu,");
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        builder.add(printButton, cc.xy(4, 4));
        builder.add(tableScrollPane, cc.xyw(2, 2, 3));
        this.contentPanel = builder.build();

        this.loadTableData();

        //Get output path
        SettingsManager sm = SettingsManager.getInstance();
        this.outputPath = sm.getSetting("auik.system.spath_steckbriefe");
    }

    private void loadTableData() {
        this.sielhautModel.filterList("");
    }

    @SuppressWarnings("deprecation")
    private JDialog createPrintDialog() {
        JDialog dialog = new JDialog();

        JLabel countLabel = new JLabel("Sielhautpunkte ausgewählt:");
        JLabel count = new JLabel(
            String.valueOf(sielhautModel.getSelectedCount()));
        JLabel pathLabel = new JLabel("Dateipfad:");
        JTextArea path = new JTextArea(this.outputPath);
        JButton pathButton = new JButton("Pfad wählen");
        pathButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.showSaveDialog(null);
                path.setText(f.getSelectedFile().getAbsolutePath());
            }
        });

        JButton cancelButton = new JButton("abbrechen");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.setVisible(false);
            }
        });
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //Check if target dir exists. If not, try to create it
                String targetDir = path.getText();
                Path path = Paths.get(targetDir);
                if (!Files.exists(path)) {
                    boolean created = new File(targetDir).mkdirs();
                    if (!created) {
                        GUIManager.getInstance().showErrorMessage(
                            String.format("Das Zielverzeichnis '%s' konnte nicht erstellt werden", targetDir),
                            "Erstellen der Sielhautsteckbriefe fehlgeschlagen");
                        GUIManager.getInstance().setErrorStatus(
                            "Erstellen der Sielhautsteckbriefe fehlgeschlagen");
                        return;
                    }
                }

                Map<String, String> exportResult = createReports(targetDir);
                JDialog resultDialog = createResultDialog(
                    createResultHtml(exportResult, targetDir),
                    targetDir, count.getText());
                dialog.setVisible(false);
                resultDialog.setVisible(true);
                GUIManager.getInstance().setInfoStatus(
                    String.format("%s Sielhautsteckbrief(e) erstellt", count.getText()));
            }
        });

        FormLayout layout = new FormLayout(
            "5dlu, 100dlu, 75dlu:g, 25dlu, 5dlu, 25dlu, 3dlu, 25dlu, 5dlu",
            "5dlu, 35dlu, 35dlu, 3dlu:g, 35dlu, 5dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();
        builder.add(countLabel, cc.xy(2, 2));
        builder.add(count, cc.xyw(3, 2, 5));
        builder.add(pathLabel, cc.xy(2, 3));
        builder.add(path, cc.xyw(3, 3, 2));
        builder.add(pathButton, cc.xyw(6, 3, 3));
        builder.add(cancelButton, cc.xyw(4, 5, 3));
        builder.add(okButton, cc.xy(8, 5));

        JPanel dialogContent = builder.build();

        dialog.setTitle("Steckbriefe erstellen");
        dialog.setModal(true);
        dialog.setSize(600, 250);

        dialog.add(dialogContent);
        return dialog;
    }

    private Map<String, String> createReports(String path) {
        Map<String, String> result = new HashMap<String, String>();

        SettingsManager sm = SettingsManager.getInstance();
        String fotoPath = sm.getSetting("auik.system.spath_fotos");
        String mapPath = sm.getSetting("auik.system.spath_karten");

        for (Object rowObj: sielhautModel.getSelected()) {
            Object[] row = (Object[]) rowObj;
            Sielhaut spunkt = sielhautModel.getModelFromRow(row);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", spunkt.getId());
            String bezeichnung = spunkt.getBezeichnung();
            if (bezeichnung != null && new File(fotoPath + bezeichnung + ".jpg").canRead()) {
                params.put("foto", new String(fotoPath + bezeichnung + ".jpg"));
            } else {
                params.put("foto", new String(fotoPath + "kein_foto.jpg"));
            }

            if (bezeichnung != null && new File(mapPath + bezeichnung + ".jpg").canRead()) {
                params.put("karte", new String(mapPath + bezeichnung + ".jpg"));
            } else {
                params.put("karte", new String(mapPath + "keine_karte.jpg"));
            }
            String fileSeparator = FileSystems.getDefault().getSeparator();
            StringBuilder pathBuilder = new StringBuilder(path);
            if (!path.endsWith(fileSeparator)) {
                pathBuilder.append(fileSeparator);
            }
            if (spunkt.getBezeichnung() != null && !spunkt.getBezeichnung().isEmpty()) {
                pathBuilder
                    .append(spunkt.getBezeichnung())
                    .append(".pdf");
            } else {
                pathBuilder
                    .append("Sielhautsteckbrief-")
                    .append(spunkt.getId())
                    .append(".pdf");
            }

            try {
                File export = new File(pathBuilder.toString());
                PDFExporter.getInstance().exportReport(params, PDFExporter.SIELHAUT_BEARBEITEN,
                    export.getAbsolutePath(), false);
                result.put(spunkt.getId().toString(), "erfolgreich erstellt");
            } catch (Exception ex) {
                result.put(spunkt.getId().toString(), ex.getLocalizedMessage());
            }
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    private JDialog createResultDialog(String resultString, String targetDir, String count) {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle(String.format("%s Sielhautsteckbrief(e) erstellt", count));
        dialog.setSize(400, 600);

        JLabel resultText = new JLabel(resultString);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.setVisible(false);
            }
        });
        JButton openButton = new JButton("Ordner öffnen");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    java.awt.Desktop.getDesktop().open(new File(targetDir));
                } catch (IOException e) {
                    GUIManager.getInstance().setErrorStatus(
                        "Zielpfad konnte nicht geöffnet werden");
                    e.printStackTrace();
                }
            }
        });

        JScrollPane scroller = new JScrollPane(resultText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        FormLayout layout = new FormLayout(
            "5dlu, 100dlu:g, 65dlu, 2dlu, 35dlu, 5dlu",
            "5dlu, 100dlu:g, 2dlu, 35dlu, 5dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        builder.add(scroller, cc.xyw(2,2, 4));
        builder.add(openButton, cc.xy(3, 4));
        builder.add(okButton, cc.xy(5, 4));

        dialog.add(builder.build());
        return dialog;
    }

    private String createResultHtml(Map<String, String> result, String path) {
        StringBuilder resultBuilder = new StringBuilder("<html>");
        resultBuilder.append(String.format("<b>Pfad:</b> %s <br>", path))
        .append("<ul>");

        result.forEach((id, resultString) -> {
            resultBuilder
                .append("<li>")
                .append(
                    String.format("<b>Sielhautpunkt %s</b>: %s <br>", id, resultString))
                .append("</li>");

        });
        resultBuilder.append("</html>");
        return resultBuilder.toString();
    }

    /**
     * Listener handling clicks on the header checkbox
     */
    class CheckBoxHeaderClickListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for(int x = 0, y = sielhautTable.getRowCount(); x < y; x++) {
                sielhautTable.setValueAt(Boolean.valueOf(checked),x,0);
            }
        }
    }

    /**
     * Handler for clicks on the checkbox column
     */
    class CheckboxColumnClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent evt) {
            int row = sielhautTable.rowAtPoint(evt.getPoint());
            int column = sielhautTable.columnAtPoint(evt.getPoint());
            if (column == 0) {
                Boolean curVal = (Boolean) sielhautTable.getValueAt(row, column);
                sielhautTable.setValueAt(!curVal, row, column);
            }
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
        }
    }
}
