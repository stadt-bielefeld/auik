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

package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein TableModel für eine Tabelle mit den verknuepften Objekten.
 *
 * @author Gerhard Genuit
 */
public class ObjektVerknuepfungModel extends ListTableModel {
    private static final long serialVersionUID = 3217003188537219019L;
    private Objekt objekt;

    /**
     * Erzeugt ein neues ObjektverknuepfungModel. Dieses hat die Spalten "ID",
     * "Objektart" und "Beschreibung".
     */
    public ObjektVerknuepfungModel(Objekt objekt) {
        super(new String[] { "Id", "Objektart", "Beschreibung" }, false, true);
    }

    /**
     * Setzt das Basisobjekt, nach dessen Verknuepfungen gesucht werden soll.
     *
     * @param objekt
     *            Das Basis-Objekt
     */
    public void setObjekt(Objekt objekt) {
        this.objekt = objekt;
        updateList();
    }

    /**
     * Setzt den Wert der gewuenschten Spalte eines Objektes.
     *
     * @param objectAtRow
     *            Das Verknuepfungsobjekt
     * @param columnIndex
     *            Index der Spalte
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Objektverknuepfung objektverk = (Objektverknuepfung) objectAtRow;

        Object tmp;

        switch (columnIndex) {

        case 0:
            if (objektverk.getObjektByObjekt().getId().intValue() == objekt
                    .getId().intValue())
                tmp = objektverk.getObjektByIstVerknuepftMit()
                        .getId();
            else
                tmp = objektverk.getObjektByObjekt().getId();
            break;

        case 1:
            if (objektverk.getObjektByObjekt().getId().intValue() == objekt
                    .getId().intValue())
                tmp = objektverk.getObjektByIstVerknuepftMit()
                        .getObjektarten();
            else
                tmp = objektverk.getObjektByObjekt().getObjektarten();
            break;

        case 2:
            if (objektverk.getObjektByObjekt().getId().intValue() == objekt
                    .getId().intValue())
                tmp = objektverk.getObjektByIstVerknuepftMit()
                        .getBeschreibung();
            else
                tmp = objektverk.getObjektByObjekt().getBeschreibung();
            break;

        default:
            tmp = null;
            break;
        }

        return tmp;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Objektverknuepfung removedObjekt = (Objektverknuepfung) objectAtRow;
        boolean removed;

        if (removedObjekt.getClass() != null) {
            removed = Objektverknuepfung.delete(removedObjekt);
        } else {
            removed = true;
        }

        return removed;
    }

    @Override
    public void updateList() {
        setList(DatabaseQuery.getLinkedObjekts(objekt));
        fireTableDataChanged();
    }

    public Objektverknuepfung getRow(int rowIndex) {
        return (Objektverknuepfung) getObjectAtRow(rowIndex);
    }

    public void clearList() {
        setList(null);
        fireTableDataChanged();
    }
}
