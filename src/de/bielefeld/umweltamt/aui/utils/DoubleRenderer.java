/*
 * Created on 16.03.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.text.NumberFormat;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * Ein TableCellRenderer um Doubles und KommaDoubles gerundet anzuzeigen.
 * @author David Klotz
 */
public class DoubleRenderer extends DefaultTableCellRenderer {
    private NumberFormat formatter;

    public DoubleRenderer() {
        super();
    }

    public void setValue(Object value) {
        setText((value == null) ? "" : formatObject(value));
    }

    private String formatObject(Object value) {
        if (formatter==null) {
            formatter = NumberFormat.getNumberInstance();
            formatter.setGroupingUsed(false);
            //formatter.setMaximumFractionDigits(1);
        }

        String tmp;
        if (value instanceof Number) {
            tmp = formatter.format(value);
        } else if (value instanceof KommaDouble) {
            tmp = formatter.format(((KommaDouble)value).getValue());
        } else {
            tmp = "";
        }

        return tmp;
    }
}
