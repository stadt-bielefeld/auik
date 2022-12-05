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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
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

    @Override
    public String getName() {
        return "Sielhautsteckbriefe drucken";
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
    }

    private void loadTableData() {
        this.sielhautModel.filterList("");
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
