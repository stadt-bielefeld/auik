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

import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnlage;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.nrw.lds.tipi.inka.Inka_Anlage;

public class InkaAnlageModel extends ListTableModel {
    private static final long serialVersionUID = 3913291284739009733L;

    public InkaAnlageModel() {
        super(new String[]{
            "Anlage Nr.",
            "Betriebseinrichtung Nr.",
            "Gemeindekennzahl",
            "Übergabestelle Lfd. Nr.",
            "Genehmigung Nr.",
            "Aktenzeichen"
        }, false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        if (objectAtRow == null) {
            return "error";
        } else if (objectAtRow instanceof InkaAnlage) {
            return getInkaAnlageFromDB(objectAtRow, columnIndex);
        } else if (objectAtRow instanceof Inka_Anlage) {
            return getInkaAnlageFromService(objectAtRow, columnIndex);
        } else {
            return "Error";
        }
    }

    public Object getInkaAnlageFromDB(Object obj, int index) {
        switch (index) {
            case 0: return ((InkaAnlage) obj).getAnlageNr();
            case 1: return ((InkaAnlage) obj).getBetriebseinrichtungNr();
            case 2: return ((InkaAnlage) obj).getGemeindekennzahl();
            case 3: return ((InkaAnlage) obj).getUebergabestelleLfdNr();
            case 4: return ((InkaAnlage) obj).getGenehmigungNr();
            case 5: return ((InkaAnlage) obj).getAktenzeichen();
            default: return "ERROR";
        }
    }

    public Object getInkaAnlageFromService(Object obj, int index) {
        switch (index) {
            case 0: return ((Inka_Anlage) obj).getAnlage_nr();
            case 1: return ((Inka_Anlage) obj).getBetriebseinrichtung_nr();
            case 2: return ((Inka_Anlage) obj).getGemeindekennzahl();
            case 3: return ((Inka_Anlage) obj).getUebergabestelle_lfd_nr();
            case 4: return ((Inka_Anlage) obj).getGenehmigung_nr();
            case 5: return ((Inka_Anlage) obj).getAktenzeichen();
            default: return "ERROR";
        }
    }

    @Override
    public void updateList() {
        // This is intentionally left blank.
    }
}
