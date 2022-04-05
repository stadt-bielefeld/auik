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
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein einfaches TableModel für die Straßentabelle.
 * @author Gerd Genuit
 */
public class EditorTabStreetsModel extends EditableListTableModel {

	private static final long serialVersionUID = -4403670639078583652L;
	boolean isnew = new Boolean(false);

	public EditorTabStreetsModel() {
        super(new String[]{
        		"ID",
                "Strassenname",
                "Nummer",
                "Zusatz",
                "X",
                "Y"
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
        	case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return Float.class;
            case 5: return Float.class;
            default: return null;
        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        TabStreets str = (TabStreets) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = str.getAbgleich();
            break;
        case 1:
            tmp = str.getName();
            break;
        case 2:
            tmp = str.getHausnr();
            break;
        case 3:
            tmp = str.getHausnrZusatz();
            break;
        case 4:
            tmp = str.getX();
            break;
        case 5:
            tmp = str.getY();
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
		
		TabStreets tmp = (TabStreets) objectAtRow;
		switch (columnIndex) {
        case 0:
        	String tmpAbgleich = (String) newValue;
        	tmp.setAbgleich(tmpAbgleich);
        	break;
        	
        case 1:
        	String tmpStrasse = (String) newValue;
        	tmp.setName(tmpStrasse);
        	break;
        	
        case 2:
        	String tmpNr = (String) newValue;
        	tmp.setHausnr(null);
        	break;
        	
        case 3:
        	String tmpZus = (String) newValue;
        	tmp.setHausnrZusatz(tmpZus);
        	break;
        	
        case 4:
        	Float x = (Float) newValue;
        	tmp.setX(x);
        	break;
        	
        case 5:
        	Float y = (Float) newValue;
        	tmp.setY(y);
        	break;

        default:
            break;        	
        	
		}
		TabStreets.merge(tmp);
	}

    /**
     * Liefert die Strasse einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public TabStreets getRow(int rowIndex) {
        return (TabStreets) getObjectAtRow(rowIndex);
    }

	@Override
	public Object newObject() {
		TabStreets tmp = new TabStreets();
        isnew = true;
		return tmp;
	}
}
