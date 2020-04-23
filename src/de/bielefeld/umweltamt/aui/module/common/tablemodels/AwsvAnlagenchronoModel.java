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
 * Datei:
 * $Id: VawsAnlagenchronoModel.java,v 1.3 2010-01-20 12:55:43 u633d Exp $
 *
 * Erstellt am 20.07.2016 von Gerd Genuit
 *
 * CVS-Log:
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import java.util.Date;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenchrono;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Vaws Anlagenchronologien.
 * @author Gerd Genuit
 */
public class AwsvAnlagenchronoModel extends ListTableModel {
	private static final long serialVersionUID = 5854006303057335338L;

	public AwsvAnlagenchronoModel() {
        super(new String[]{
                "Betreiber",
                "Standort",
                "Wiedervorl.",
                "Sachverhalt",
                "abgeschlossen"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
    	Anlagenchrono ac = (Anlagenchrono) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = ac.getFachdaten().getObjekt().getBetreiberid().toString();
            break;
        case 1:
            tmp = DatabaseQuery.getStandortString(ac.getFachdaten().getObjekt().getStandortid());
            break;
        case 2:
            tmp = ac.getWv();
            break;
        case 3:
            tmp = ac.getSachverhalt();
            break;
        case 4:
            tmp = ac.getAbgeschlossen();
            break;

        default:
            tmp = "ERROR";
            break;
        }
//        if (fd.getBasisObjekt().getInaktiv() == true) {
//            tmp = StringUtils.setStrike(tmp.toString());
//        }
        return tmp;
    }

	  @Override
	public Class<?> getColumnClass( int columnIndex ){
		switch( columnIndex ){
			case 0: return String.class;
			case 1: return String.class;
			case 2: return Date.class;
			case 3: return String.class;
			case 4: return Boolean.class;
			default: return null;
		}
	}

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     */
    @Override
    public void updateList() {
    	// This is intentionally left blank.
    }
}
