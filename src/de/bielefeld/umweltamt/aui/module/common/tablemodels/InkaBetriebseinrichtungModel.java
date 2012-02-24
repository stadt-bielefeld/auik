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

import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetriebseinrichtung;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung;

public class InkaBetriebseinrichtungModel extends ListTableModel {
    private static final long serialVersionUID = 4204170860624126450L;

    public InkaBetriebseinrichtungModel() {
        super(new String[]{
                "Betriebseinrichtung Nr.",
                "Betrieb Nr.",
                "Adresse Betreiber Nr.",
                "Genehmigung Nr."
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
        if (objectAtRow instanceof InkaBetriebseinrichtung) {
            return getInkaBetriebseinrichtungFromDB(objectAtRow, columnIndex);
        }
        else if (objectAtRow instanceof Inka_Betriebseinrichtung) {
            return getInkaBetriebseinrichtungFromService(objectAtRow,
                                                         columnIndex);
        }
        else {
            return "Error";
        }
    }

    public Object getInkaBetriebseinrichtungFromDB (Object obj, int ndx) {
        InkaBetriebseinrichtung fd = (InkaBetriebseinrichtung) obj;
        Object tmp;

        switch (ndx) {
        case 0:
            tmp = fd.getId().getBetriebseinrichtungNr();
            break;
        case 1:
            tmp = fd.getBetriebNr();
            break;
        case 2:
            tmp = fd.getAdresseBetreibNr();
            break;
        case 3:
            tmp = fd.getGenehmigungNr();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        return tmp;
    }


    public Object getInkaBetriebseinrichtungFromService (Object obj, int ndx) {
        Inka_Betriebseinrichtung fd = (Inka_Betriebseinrichtung) obj;
        Object tmp;

        switch (ndx) {
        case 0:
            tmp = fd.getBetriebseinrichtung_nr();
            break;
        case 1:
            tmp = fd.getBetrieb_nr();
            break;
        case 2:
            tmp = fd.getAdresse_betreib_nr();
            break;
        case 3:
            tmp = fd.getGenehmigung_nr();
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
