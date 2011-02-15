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
 * Created on 28.01.2005
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTable;

/**
 * Ein FocusListener, um an den Anfang einer Tabelle zu springen,
 * wenn diese den Fokus bekommt.
 * @author David Klotz
 */
public class TableFocusListener extends FocusAdapter {
    private static TableFocusListener tflInstance = null;

    /**
     * Liefert eine Instanz des TableFocusListeners.
     * Diese kann von mehreren Tabellen gemeinsam benutzt werden.
     */
    public static TableFocusListener getInstance() {
        if (tflInstance == null) {
            tflInstance = new TableFocusListener();
        }
        return tflInstance;
    }

    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTable) {
            JTable table = (JTable) e.getSource();

            // Nur wenn die Tabelle Zeilen hat und noch keine ausgewählt ist
            if (table.getRowCount() > 0 && table.getSelectedRow() == -1) {
                // Die erste Zeile anwählen...
                table.setRowSelectionInterval(0,0);
                // ... und zu ihr hochscrollen
                table.scrollRectToVisible(table.getCellRect(0, 0, true));
            }
        }
    }
}
