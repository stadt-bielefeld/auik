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

import java.sql.Date;

import de.bielefeld.umweltamt.aui.mappings.tipi.InkaGenehmigung;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.nrw.lds.tipi.inka.Inka_Genehmigung;

public class InkaGenehmigungModel extends ListTableModel {
    private static final long serialVersionUID = 4915399132867370442L;

    public InkaGenehmigungModel() {
        super(new String[]{
                "Genehmingung Nr",
                "Betrieb Nr.",
                "Behörden ID",
                "Datum",
                "befristet"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        if (objectAtRow == null) {
            return "error";
        }
        if (objectAtRow instanceof InkaGenehmigung) {
            return getInkaGenehmigungFromDB(objectAtRow, columnIndex);
        }
        else if (objectAtRow instanceof Inka_Genehmigung) {
            return getInkaGenehmigungFromService(objectAtRow, columnIndex);
        }
        else {
            return "Error";
        }
    }

    public Object getInkaGenehmigungFromDB (Object obj, int ndx) {
        InkaGenehmigung fd = (InkaGenehmigung) obj;
        Object tmp;

        switch (ndx) {
        case 0:
            tmp = fd.getId().getGenehmigungNr();
        case 1:
            tmp = fd.getBetriebNr();
            break;
        case 2:
            tmp = fd.getBehoerdenId();
            break;
        case 3:
            tmp = fd.getGenehmigungDatum();
            break;
        case 4:
            tmp = fd.getBefristetJn();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        return tmp;
    }

    public Object getInkaGenehmigungFromService (Object obj, int ndx) {
        Inka_Genehmigung fd = (Inka_Genehmigung) obj;
        Object tmp;

        switch (ndx) {
        case 0:
            tmp = fd.getGenehmigung_nr();
        case 1:
            tmp = fd.getBetrieb_nr();
            break;
        case 2:
            tmp = fd.getBehoerden_id();
            break;
        case 3:
            long millis = fd.getGenehmigung_datum().getTimeInMillis();
            Date d = new Date(millis);
            tmp = d.toString();
            break;
        case 4:
            tmp = fd.getBefristet_jn();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        return tmp;
    }


    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     */
    @Override
    public void updateList() {
    }
}
