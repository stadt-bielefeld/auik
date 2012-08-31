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

import de.bielefeld.umweltamt.aui.mappings.tipi.InkaMessstelle;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.nrw.lds.tipi.inka.Inka_Messstelle;

public class InkaMessstelleModel extends ListTableModel {
    private static final long serialVersionUID = 3913291284739009733L;

    public InkaMessstelleModel() {
        super(new String[]{
                "Beschreibung",
                "Messstellen Nr.",
                "Erfassungsdatum",
                "Gemeindekennzahl"
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
        if (objectAtRow instanceof InkaMessstelle) {
            return getInkaMessstelleFromDB(objectAtRow, columnIndex);
        }
        else if (objectAtRow instanceof Inka_Messstelle) {
            return getInkaMessstelleFromService(objectAtRow, columnIndex);
        }
        else {
            return "Error";
        }
    }

    public Object getInkaMessstelleFromDB(Object obj, int index) {
        switch (index) {
            case 0: return ((InkaMessstelle) obj).getBeschrMesspunkt();
            case 1: return ((InkaMessstelle) obj).getMessstelleLfdNr();
            case 2:
                return DateUtils.format(
                    ((InkaMessstelle) obj).getErfassungsDatum().getTime(),
                    DateUtils.FORMAT_DATE);
            case 3: return ((InkaMessstelle) obj).getGemeindekennzahl();
            default: return "ERROR";
        }
    }


    public Object getInkaMessstelleFromService(Object obj, int ndx) {
    	Inka_Messstelle fd = (Inka_Messstelle) obj;
        Object tmp;

        switch (ndx) {
        case 0:
            tmp = fd.getBeschr_messpunkt();
        case 1:
            tmp = fd.getMessstelle_lfd_nr();
            break;
        case 2:
            tmp = fd.getErfassungs_datum();
            break;
        case 3:
            tmp = fd.getGemeindekennzahl();
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
