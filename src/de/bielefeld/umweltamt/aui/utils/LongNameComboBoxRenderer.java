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
 * Created on 08.02.2005
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Ein ListCellRenderer um den einzelnen Items in Listen (besonders
 * in ComboBoxen) einen Tooltip zuzuweisen, wenn ihr Name besonders
 * lang ist.
 * @author David Klotz
 */
public class LongNameComboBoxRenderer extends JLabel implements
        ListCellRenderer {
    private static final long serialVersionUID = -6509345893092864888L;

    /**
     * Erzeugt einen ListCellRenderer, der den einzelnen Items in Listen
     * (besonders in ComboBoxen) einen Tooltip zuweist, wenn ihr Name
     * besonders lang ist.
     */
    public LongNameComboBoxRenderer() {
        setOpaque(true);
    }

    /**
     * Liefert das Label zum Rendern der ListCell
     * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        String tmp = null;
        if (value != null) {
            tmp = value.toString();
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        String toolTip = null;

        if ((tmp != null) && (tmp.length() > 15)) {
            toolTip = tmp;
        }

        setText(tmp);
        setToolTipText(toolTip);

        return this;
    }

}
