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
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhEntsorger;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein einfaches TableModel für die Entsorgertabelle.
 * @author Gerd Genuit
 */
public class EditorEntsorgerModel extends EditableListTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1744282792751909508L;

	public EditorEntsorgerModel() {
        super(new String[]{
                "ID",
                "Name",
                "Straße",
                "Nr.",
                "PLZ",
                "Ort",
                "Ansprechpartner",
                "Telefon"
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
            case 3: return Integer.class;
            case 4: return String.class;
            case 5: return String.class;
            case 6: return String.class;
            case 7: return String.class;
            default: return null;
        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
    	AnhEntsorger entsorger = (AnhEntsorger) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = entsorger.getId();
            break;
        case 1:
            tmp = entsorger.getEntsorger();
            break;
        case 2:
            tmp = entsorger.getStrasse();
            break;
        case 3:
            tmp = entsorger.getHausnr();
            break;
        case 4:
            tmp = entsorger.getPlz();
            break;
        case 5:
            tmp = entsorger.getOrt();
            break;
        case 6:
            tmp = entsorger.getAnsprechpartner();
            break;
        case 7:
            tmp = entsorger.getTelefon();
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
		
		AnhEntsorger tmp = (AnhEntsorger) objectAtRow;
		switch (columnIndex) {
        case 0:
        	Integer tmpID = (Integer) newValue;
        	tmp.setId(tmpID);
        	break;
        	
        case 1:
        	String tmpName = (String) newValue;
        	tmp.setEntsorger(tmpName);
        	break;
        case 2:
        	String tmpstrasse = (String) newValue;
        	tmp.setStrasse(tmpstrasse);
        	break;
        	
        case 3:
        	Integer tmpNr = (Integer) newValue;
        	tmp.setHausnr(tmpNr);
        	break;
        case 4:
        	String tmpPLZ = (String) newValue;
        	tmp.setPlz(tmpPLZ);
        	break;
        	
        case 5:
        	String tmpOrt = (String) newValue;
        	tmp.setOrt(tmpOrt);
        	break;
        case 6:
        	String tmpAnsprech = (String) newValue;
        	tmp.setAnsprechpartner(tmpAnsprech);
        	break;
        	
        case 7:
        	String tmpTel = (String) newValue;
        	tmp.setTelefon(tmpTel);
        	break;

        default:
            break;        	
        	
		}
		AnhEntsorger.merge(tmp);
	}

	@Override
	public Object newObject() {
		AnhEntsorger tmp = new AnhEntsorger();
		tmp.setId(DatabaseQuery.newEntsorgerID());
		return tmp;
	}

    @Override
    public boolean objectRemoved(Object objectAtRow) {
    	AnhEntsorger removedEntsorger = (AnhEntsorger) objectAtRow;
        return AnhEntsorger.delete(removedEntsorger);
    }

    /**
     * Liefert den Entsorger einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public AnhEntsorger getRow(int rowIndex) {
        return (AnhEntsorger) getObjectAtRow(rowIndex);
    }
}
