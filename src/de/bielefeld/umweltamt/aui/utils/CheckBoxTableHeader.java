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
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * TableCellRender displaying a checkbox to use as "select all" button in table headers.
 *
 * @author Alexander Woestmann <awoestmann@intevation.de>
 */
public class CheckBoxTableHeader extends JCheckBox implements TableCellRenderer, MouseInputListener{

    private static final long serialVersionUID = 1L;

    protected CheckBoxTableHeader rendererComponent;
    protected int column;
    protected boolean mousePressed = true;

    public CheckBoxTableHeader(ItemListener itemListener) {
        rendererComponent = this;
        rendererComponent.addItemListener(itemListener);
        setHorizontalAlignment(SwingConstants.CENTER);
      }

    protected void handleClickEvent(MouseEvent e) {
        if (mousePressed) {
            mousePressed=false;
            JTableHeader header = (JTableHeader)(e.getSource());
            JTable tableView = header.getTable();
            TableColumnModel columnModel = tableView.getColumnModel();
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            int column = tableView.convertColumnIndexToModel(viewColumn);

            if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
              doClick();
            }
          }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object arg1, boolean arg2, boolean arg3, int arg4,
            int arg5) {
        if (table != null) {
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                rendererComponent.setForeground(header.getForeground());
                rendererComponent.setBackground(header.getBackground());
                rendererComponent.setFont(header.getFont());
                header.addMouseListener(rendererComponent);
            }
        }
        setColumn(column);
        //setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return rendererComponent;
    }

    protected void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        handleClickEvent(e);
        ((JTableHeader)e.getSource()).repaint();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }
}
