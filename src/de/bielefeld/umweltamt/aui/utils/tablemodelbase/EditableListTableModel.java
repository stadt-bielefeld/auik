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
 * Created on 25.02.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils.tablemodelbase;


/**
 * Eine TableModel-Grundlage, basierend auf einer <code>java.util.List</code>e .
 * Jede Zeile der Tabelle ist ein Objekt in der Liste.
 * Im EditableListTableModel ist die Liste zusätzlich noch editier- und erweiterbar.
 * @author David Klotz
 */
public abstract class EditableListTableModel<T> extends ListTableModel<T> {
    private static final long serialVersionUID = 5771782454750103490L;
    private boolean hasChanged;

    public EditableListTableModel(String[] columns, boolean updateAtInit) {
        this(columns, updateAtInit, true);
    }

    public EditableListTableModel(String[] columns, boolean updateAtInit, boolean removeAllowed) {
        super(columns, updateAtInit, removeAllowed);
        hasChanged = false;
    }

    /**
     * Liefert die Anzahl Zeilen in der Liste + 1. Damit wird
     * sichergestellt, dass immer eine Leerzeile zum anlegen
     * einer neuen Zeile zur Verfügung steht.
     * @return Die Anzahlt der Zeilen + 1
     */
    @Override
    public int getRowCount() {
        return super.getRowCount() + 1;
    }

    /**
     * @return <code>true</code>
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    /**
     * Leitet die Veränderung an das entsprechende Objekt in der Liste
     * weiter, bzw. erzeugt zu erst ein neues Objekt (wenn die Leerzeile
     * bearbeitet wird).
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (rowExists(rowIndex)) {
            //log.debug("%%% EDIT LISTE: col: " + columnIndex + ", newValue: " + aValue);
            editObject(getObjectAtRow(rowIndex), columnIndex, aValue);
        } else {
            //log.debug("%%% EDIT NEU! %%%");
            T tmp = newObject();
            getList().add(tmp);
            editObject(tmp, columnIndex, aValue);
            //log.debug("NEU: " + tmp);
            fireTableRowsInserted(rowIndex + 1, rowIndex + 1);
        }
        fireTableRowsUpdated(rowIndex, rowIndex);
        //fireTableCellUpdated(rowIndex, columnIndex);

        hasChanged = true;

        /*log.debug("%%% EDIT: row: " + rowIndex + ", " +
                "old.s: " + getList().size() + ", " +
                "new.s: " + getNewList().size() + ", " +
                "total.s: " + getTotalRowCount() + " %%%");*/

    }

    /**
     * Löscht eine Zeile der Tabelle, falls das erlaubt und implementiert ist.
     * @param rowIndex Die Zeile
     * @return <code>true</code>, wenn wirklich gelöscht wurde,
     * <code>false</code>, falls dabei ein Fehler auftrat oder es aus anderen Gründen
     * nicht möglich war
     */
    @Override
    public boolean removeRow(int rowIndex) {
        boolean tmp = super.removeRow(rowIndex);

        if (tmp) {
            hasChanged = true;
        }

        return tmp;
    }
    /**
     * überprüft, ob die Liste verändert wurde.
     * @return <code>true</code>, wenn die Liste verändert wurde, sonst <code>false</code>
     */
    public boolean hasChanged() {
        return this.hasChanged;
    }

    /**
     * Wird aufgerufen um das Attribut von <code>objectAtRow</code>
     * in der Zeile <code>columnIndex</code> auf <code>newValue</code>
     * zu ändern.
     * @param objectAtRow Das zu verändernde Objekt
     * @param columnIndex Welche Spalte verändert werden soll
     * @param newValue Der geänderte Wert
     */
    public abstract void editObject(T objectAtRow, int columnIndex, Object newValue);

    /**
     * Wird aufgerufen um ein neues (mit Standard-Werten initialisiertes)
     * Objekt der Liste hinzuzufügen. Wird benötigt, wenn der Benutzer
     * eine neue Zeile anlegt.
     * @return Ein neues Objekt, dass der Liste hinzugefügt wird
     */
    public abstract T newObject();
}
