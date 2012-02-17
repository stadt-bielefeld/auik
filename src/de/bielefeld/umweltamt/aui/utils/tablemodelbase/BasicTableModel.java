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
 * Created on 23.02.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils.tablemodelbase;

import javax.swing.table.AbstractTableModel;

/**
 * Die Grundlage für weitere TableModels.
 * Die Namen der Spalten werden als String-Array im Konstruktor übergeben.
 * @author David Klotz
 */
public abstract class BasicTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 4900667181862129165L;
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
    @Override
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
    @Override
    public int getColumnCount() {
        return columns.length;
    }
}
