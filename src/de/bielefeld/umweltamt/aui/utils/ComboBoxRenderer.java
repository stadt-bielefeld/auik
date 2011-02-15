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
 * Created on 15.03.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Ein CellRenderer der eine ComboBox mit dem aktuellen Wert der Zelle anzeigt.
 * Dient dazu deutlich zu machen, dass sich hinter einer Zelle eine ComboBox versteckt.
 * @author David Klotz
 */
public class ComboBoxRenderer extends JComboBox implements TableCellRenderer {
    public ComboBoxRenderer() {
        super();
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        removeAllItems();
        addItem(value);
        if (isSelected && !hasFocus) {
            this.setBackground(table.getSelectionBackground());
            this.setForeground(table.getSelectionForeground());
        } else {
            this.setBackground(table.getBackground());
            this.setForeground(table.getForeground());
        }
        return this;
    }
}
