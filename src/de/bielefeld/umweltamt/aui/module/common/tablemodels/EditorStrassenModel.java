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
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein einfaches TableModel für die Straßentabelle.
 * @author Gerd Genuit
 */
public class EditorStrassenModel extends EditableListTableModel {

	private static final long serialVersionUID = 372516341448962983L;

	public EditorStrassenModel() {
        super(new String[]{
                "ID",
                "Strassenname",
                "Postleitzahl",
                "Ort"
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
            case 2: return String.class;
            case 3: return String.class;
            default: return null;
        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        BasisStrassen str = (BasisStrassen) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = str.getId();
            break;
        case 1:
            tmp = str.getStrasse();
            break;
        case 2:
            tmp = str.getPlz();
            break;
        case 3:
            tmp = str.getOrt();
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
	public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
		
		BasisStrassen tmp = (BasisStrassen) objectAtRow;
		switch (columnIndex) {
        case 0:
        	Integer tmpID = (Integer) newValue;
        	tmp.setId(tmpID);
        	break;
        	
        case 1:
        	String tmpStrasse = (String) newValue;
        	tmp.setStrasse(tmpStrasse);
        	break;
        	
        case 2:
        	String tmpPLZ = (String) newValue;
        	tmp.setPlz(tmpPLZ);
        	break;
        	
        case 3:
        	String tmpOrt = (String) newValue;
        	tmp.setOrt(tmpOrt);
        	break;

        default:
            break;        	
        	
		}
		BasisStrassen.merge(tmp);
	}

	@Override
	public Object newObject() {
		BasisStrassen tmp = new BasisStrassen();
		tmp.setId(DatabaseQuery.newStrassenID());
		return tmp;
	}

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        BasisStrassen removedStrasse = (BasisStrassen) objectAtRow;
        return BasisStrassen.delete(removedStrasse);
    }

    /**
     * Liefert die Strasse einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public BasisStrassen getRow(int rowIndex) {
        return (BasisStrassen) getObjectAtRow(rowIndex);
    }
}
