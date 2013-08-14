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
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein einfaches TableModel für die Objektartentabelle.
 * @author Gerd Genuit
 */
public class EditorObjektartenModel extends EditableListTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6481979000280099636L;

	public EditorObjektartenModel() {
        super(new String[]{
                "ID",
                "Objektart",
                "Abteilung"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        BasisObjektarten art = (BasisObjektarten) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = art.getId();
            break;
        case 1:
            tmp = art.getObjektart();
            break;
        case 2:
            tmp = art.getAbteilung();
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
		
		BasisObjektarten tmp = (BasisObjektarten) objectAtRow;
		switch (columnIndex) {
        case 0:
        	Integer tmpID = (Integer) newValue;
        	tmp.setId(tmpID);
        	break;
        	
        case 1:
        	String tmpArt = (String) newValue;
        	tmp.setObjektart(tmpArt);
        	break;
        	
        case 2:
        	String tmpAbt = (String) newValue;
        	tmp.setAbteilung(tmpAbt);
        	break;

        default:
            break;        	
        	
		}
		BasisObjektarten.merge(tmp);
	}

	@Override
	public Object newObject() {
		BasisObjektarten tmp = new BasisObjektarten();
		tmp.setId(DatabaseQuery.newObjektartenID());
		return tmp;
	}

    @Override
    public boolean objectRemoved(Object objectAtRow) {
    	BasisObjektarten removedArt = (BasisObjektarten) objectAtRow;
        return BasisObjektarten.delete(removedArt);
    }

    /**
     * Liefert die Objektart einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public BasisObjektarten getRow(int rowIndex) {
        return (BasisObjektarten) getObjectAtRow(rowIndex);
    }
}
