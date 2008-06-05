/*
 * Created on 23.02.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import javax.swing.table.AbstractTableModel;

/**
 * Die Grundlage für weitere TableModels.
 * Die Namen der Spalten werden als String-Array im Konstruktor übergeben.
 * @author David Klotz
 */
public abstract class BasicTableModel extends AbstractTableModel {
	protected String[] columns;
	
	/**
	 * Erzeugt eine neue TableModel-Grundlage mit den gegebenen Spalten.
	 * @param columns Ein String-Array mit den Namen der Spalten der Tabelle
	 */
	public BasicTableModel(String[] columns) {
		super();
		this.columns = columns;
	}
	
	/**
	 * Liefert den Namen der jeweiligen Tabellen-Spalte.
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		if (column < columns.length) {
			return columns[column];
		} else {
			return null;
		}
	}
	
	/** 
	 * Liefert die Anzahl der Spalten dieser Tabelle.
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columns.length;
	}
}
