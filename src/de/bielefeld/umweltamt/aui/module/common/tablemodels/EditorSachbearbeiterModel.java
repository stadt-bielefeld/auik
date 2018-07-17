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

import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein einfaches TableModel für die Sachbearbeitertabelle.
 * @author Gerd Genuit
 */
public class EditorSachbearbeiterModel extends EditableListTableModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 99251332998082843L;

	public EditorSachbearbeiterModel() {
        super(new String[]{
                "Kennummer",
                "Name",
                "Zeichen",
                "Zimmer",
                "Telefon",
                "E-Mail",
                "Gruppe"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
    	Sachbearbeiter bearbeiter = (Sachbearbeiter) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = bearbeiter.getKennummer();
            break;
        case 1:
            tmp = bearbeiter.getName();
            break;
        case 2:
            tmp = bearbeiter.getZeichen();
            break;
        case 3:
            tmp = bearbeiter.getZimmer();
            break;
        case 4:
            tmp = bearbeiter.getTelefon();
            break;
        case 5:
            tmp = bearbeiter.getEmail();
            break;
        case 6:
            tmp = bearbeiter.getGehoertzuarbeitsgr();
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
		
		Sachbearbeiter tmp = (Sachbearbeiter) objectAtRow;
		switch (columnIndex) {
        case 0:
        	String tmpID = (String) newValue;
        	tmp.setKennummer(tmpID);
        	break;
        	
        case 1:
        	String tmpName = (String) newValue;
        	tmp.setName(tmpName);
        	break;
        	
        case 2:
        	String tmpZeichen = (String) newValue;
        	tmp.setZeichen(tmpZeichen);
        	break;
        	
        case 3:
        	String tmpZimmer = (String) newValue;
        	tmp.setZimmer(tmpZimmer);
        	break;
        	
        case 4:
        	String tmpTelefon = (String) newValue;
        	tmp.setTelefon(tmpTelefon);
        	break;
        	
        case 5:
        	String tmpEmail = (String) newValue;
        	tmp.setEmail(tmpEmail);
        	break;
        	
        case 6:
        	String tmpGruppe = (String) newValue;
        	tmp.setGehoertzuarbeitsgr(tmpGruppe);
        	break;

        default:
            break;        	
        	
		}
		Sachbearbeiter.merge(tmp);
	}

	@Override
	public Object newObject() {
		Sachbearbeiter tmp = new Sachbearbeiter();
		return tmp;
	}

    @Override
    public boolean objectRemoved(Object objectAtRow) {
    	Sachbearbeiter removedSachbearbieter = (Sachbearbeiter) objectAtRow;
        return Sachbearbeiter.delete(removedSachbearbieter);
    }

    /**
     * Liefert den Sachbearbeiter einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public Sachbearbeiter getRow(int rowIndex) {
        return (Sachbearbeiter) getObjectAtRow(rowIndex);
    }
}
