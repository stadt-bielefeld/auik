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

import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaAnalysemethode;
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
                "Analysemethode",
                "wird gemessen in",
                "Abwasser-Grenzwert",
                "Sielhaut-Grenzwert"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
    	Parameter para = (Parameter) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = para.getOrdnungsbegriff();
            break;
        case 1:
            tmp = para.getBezeichnung();
            break;
        case 2:
            tmp = para.getMapElkaAnalysemethode();
            break;
        case 3:
            tmp = para.getEinheiten();
            break;
        case 4:
        	return para.getGrenzwert();
        case 5:
        	return para.getSielhautGw();

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

		Parameter tmp = (Parameter) objectAtRow;
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
        	MapElkaAnalysemethode tmpMethode = (MapElkaAnalysemethode) newValue;
            tmp.setMapElkaAnalysemethode(tmpMethode);
            break;

        case 3:
        	Einheiten tmpEinheiten = (Einheiten) newValue;
            tmp.setEinheiten(tmpEinheiten);
            break;

        case 4:
        	Double tmpGW = (Double) newValue;
        	tmp.setGrenzwert(tmpGW);
        	break;

        case 5:
        	Double tmpSielGW = (Double) newValue;
        	tmp.setSielhautGw(tmpSielGW);
        	break;

        default:
            break;

		}

		if (tmp.getOrdnungsbegriff() != null) {
			Parameter.merge(tmp);
		}

	}

	@Override
	public Object newObject() {
		Parameter tmp = new Parameter();
		return tmp;
	}

    @Override
    public boolean objectRemoved(Object objectAtRow) {
    	Parameter removedPara = (Parameter) objectAtRow;
        return Parameter.delete(removedPara);
    }

    /**
     * Liefert den Parameter einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public Parameter getRow(int rowIndex) {
        return (Parameter) getObjectAtRow(rowIndex);
    }

	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    Class<?> tmp;
	    switch (columnIndex) {
	        case 0:
	            tmp = String.class;
	            break;

	        case 1:
	            tmp = String.class;
	            break;

	        case 2:
	        	tmp = MapElkaAnalysemethode.class;
	            break;

	        case 3:
	            tmp = Double.class;
	            break;

	        case 4:
	            tmp = Double.class;
	            break;

	        default:
	            tmp = super.getColumnClass(columnIndex);
	            break;
	    }
	    return tmp;
	}
}
