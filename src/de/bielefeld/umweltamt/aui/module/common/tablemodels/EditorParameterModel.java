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

import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein einfaches TableModel für die Parametertabelle.
 * @author Gerd Genuit
 */
public class EditorParameterModel extends EditableListTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5048471149489876415L;

	public EditorParameterModel() {
        super(new String[]{
                "ID",
                "Parameter",
                "Grenzwert"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        AtlParameter para = (AtlParameter) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = para.getOrdnungsbegriff();
            break;
        case 1:
            tmp = para.getBezeichnung();
            break;
        case 2:
            tmp = para.getGrenzwert();
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
		
		AtlParameter tmp = (AtlParameter) objectAtRow;
		switch (columnIndex) {
        case 0:
        	String tmpID = (String) newValue;
        	tmp.setOrdnungsbegriff(tmpID);
        	break;
        	
        case 1:
        	String tmpArt = (String) newValue;
        	tmp.setBezeichnung(tmpArt);
        	break;
        	
        case 2:
        	Double tmpAbt = (Double) newValue;
        	tmp.setGrenzwert(tmpAbt);
        	break;

        default:
            break;        	
        	
		}
		AtlParameter.merge(tmp);
	}

	@Override
	public Object newObject() {
		AtlParameter tmp = new AtlParameter();
		return tmp;
	}

    @Override
    public boolean objectRemoved(Object objectAtRow) {
    	AtlParameter removedArt = (AtlParameter) objectAtRow;
        return AtlParameter.delete(removedArt);
    }

    /**
     * Liefert den Parameter einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public AtlParameter getRow(int rowIndex) {
        return (AtlParameter) getObjectAtRow(rowIndex);
    }
}
