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
 * $Id: Anh50Model.java,v 1.3 2010-01-20 12:55:43 u633d Exp $
 *
 * Erstellt am 15.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import java.util.Date;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Anhang 50-Fachdaten.
 * @author David Klotz
 */
public class Anh50Model extends ListTableModel {
	private static final long serialVersionUID = 5854006303057335338L;

	public Anh50Model() {
        super(new String[]{
                "Betreiber",
                "Standort",
                "Wiedervorl.",
                "Bemerkungen"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Anh50Fachdaten fd = (Anh50Fachdaten) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = fd.getAnfallstelle().getObjekt().getBetreiberid().toString();
            break;
        case 1:
            tmp = DatabaseQuery.getStandortString(fd.getAnfallstelle().getObjekt().getStandortid());
            break;
        case 2:
            tmp = fd.getWiedervorlage();
            break;
        case 3:
            tmp = fd.getBemerkungen();
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
