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
