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
 * Datei:
 * $Id: SelectTable.java,v 1.2 2009-03-24 12:35:21 u633d Exp $
 *
 * Erstellt am 16.03.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:34  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.3  2005/06/13 14:03:45  u633z
 * - überflüssige Debug-Ausgabe entfernt
 *
 * Revision 1.2  2005/06/09 13:42:33  u633z
 * - Konstruktor ohne Parameter benutzt jetzt anderen vorhandenen Konstruktor (um Dopplung zu vermeiden)
 *
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

/**
 * Eine verbesserte editierbare Tabelle.
 * Der gesamte Inhalt wird ausgewählt
 * @author David Klotz
 */
public class SelectTable extends JTable {
    private static final long serialVersionUID = 138172835260226476L;

    public SelectTable() {
        this(null);
    }

    //  Select the text when the cell starts editing
    //  a) text will be replaced when you start typing in a cell
    //  b) text will be selected when you use F2 to start editing
    //  c) caret is placed at end of text when double clicking to start editing

    public SelectTable(TableModel dm) {
        super(dm);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setColumnSelectionAllowed(false);
        this.setRowSelectionAllowed(true);
        this.setSurrendersFocusOnKeystroke(true);
        this.setGridColor(new Color(230, 230, 230));
    }

    @Override
    public Component prepareEditor(
    TableCellEditor editor, int row, int column)
    {
        Component c = super.prepareEditor(editor, row, column);

        if (c instanceof JTextComponent)
        {
            ((JTextComponent)c).selectAll();
        }

        return c;
    }
}
