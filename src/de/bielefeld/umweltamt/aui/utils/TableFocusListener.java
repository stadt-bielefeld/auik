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
