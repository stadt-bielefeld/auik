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

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.selectable.SelectableSielhautModel;
import de.bielefeld.umweltamt.aui.utils.CheckBoxTableHeader;

public class SielhautPrinter extends AbstractModul {

    private JPanel contentPanel;
    private JScrollPane tableScrollPane;

    private JTable sielhautTable;
    private SelectableSielhautModel sielhautModel;
    private TableColumn checkBoxColumn;

    private JToolBar toolbar;
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
        this.toolbar = new JToolBar();
        this.toolbar.setFloatable(false);
        this.toolbar.setRollover(true);
        this.toolbar.add(this.printButton);

        //Content panel
        FormLayout layout = new FormLayout(
            "3dlu, 180dlu:g, 3dlu, min(36dlu;p), 3dlu",
            "3dlu, 400dlu:g, 3dlu, 20dlu,");
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        builder.add(toolbar, cc.xy(4, 4));
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


        JToolBar tb = new JToolBar();
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
            }
        });
        tb.add(Box.createHorizontalGlue());
        tb.add(okButton);
        tb.addSeparator();
        tb.add(cancelButton);

        FormLayout layout = new FormLayout(
            "5dlu, 100dlu, 75dlu:g, 5dlu, 50dlu, 5dlu",
            "5dlu, 35dlu, 35dlu, 3dlu:g, 35dlu, 5dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();
        builder.add(countLabel, cc.xy(2, 2));
        builder.add(count, cc.xy(3, 2));
        builder.add(pathLabel, cc.xy(2, 3));
        builder.add(path, cc.xy(3, 3));
        builder.add(pathButton, cc.xy(5, 3));
        builder.add(tb, cc.xyw(2, 5, 4));

        JPanel dialogContent = builder.build();

        dialog.setTitle("Steckbriefe erstellen");
        dialog.setModal(true);
        dialog.setSize(600, 250);

        dialog.add(dialogContent);
        return dialog;
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
