/*
 * Datei:
 * $Id: SelectTable.java,v 1.1 2008-06-05 11:38:34 u633d Exp $
 * 
 * Erstellt am 16.03.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/06/13 14:03:45  u633z
 * - Überflüssige Debug-Ausgabe entfernt
 *
 * Revision 1.2  2005/06/09 13:42:33  u633z
 * - Konstruktor ohne Parameter benutzt jetzt anderen vorhandenen Konstruktor (um Dopplung zu vermeiden)
 *
 */
package de.bielefeld.umweltamt.aui.utils;

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
	}

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
