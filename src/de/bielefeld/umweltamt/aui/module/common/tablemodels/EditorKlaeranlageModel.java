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
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein einfaches TableModel für die Kläranlagentabelle.
 * @author Gerd Genuit
 */
public class EditorKlaeranlageModel
    extends EditableListTableModel<Klaeranlage> {

    /**
     *
     */
    private static final long serialVersionUID = -640754858163697830L;

    public EditorKlaeranlageModel() {
        super(new String[]{
                "ID",
                "Kläranlage"
        },
        false);
    }

    /**
     * Tell the model which column is of which class type.<br>
     * @param columnIndex The index of the requested column
     */

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 1: return String.class;
            default: return null;
        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Klaeranlage einheit, int columnIndex) {
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = einheit.getNr();
            break;
        case 1:
            tmp = einheit.getAnlage();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        return tmp;
    }


    @Override
    public void updateList() {
    }

    @Override
    public void editObject(Klaeranlage tmp, int columnIndex, Object newValue) {
        switch (columnIndex) {
        case 0:
            Integer tmpID = (Integer) newValue;
            tmp.setNr(tmpID);
            break;

        case 1:
            String tmpAnlage = (String) newValue;
            tmp.setAnlage(tmpAnlage);
            break;

        default:
            break;

        }
        Klaeranlage.merge(tmp);
    }

    @Override
    public Klaeranlage newObject() {
        Klaeranlage tmp = new Klaeranlage();
        tmp.setNr(DatabaseQuery.newKlaeranlageID());
        return tmp;
    }

    @Override
    public boolean objectRemoved(Klaeranlage removedklaeranlage) {
        return Klaeranlage.delete(removedklaeranlage);
    }

    /**
     * Liefert die Kläranlage einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public Klaeranlage getRow(int rowIndex) {
        return (Klaeranlage) getObjectAtRow(rowIndex);
    }
}
