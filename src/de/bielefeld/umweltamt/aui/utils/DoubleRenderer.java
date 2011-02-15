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
