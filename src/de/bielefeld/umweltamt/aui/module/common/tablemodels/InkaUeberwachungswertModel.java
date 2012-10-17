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

import de.bielefeld.umweltamt.aui.mappings.tipi.InkaUeberwachungswert;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.nrw.lds.tipi.inka.Inka_Ueberwachungswert;

public class InkaUeberwachungswertModel extends ListTableModel {
    private static final long serialVersionUID = 3913291284739009733L;

    public InkaUeberwachungswertModel() {
        super(new String[]{
            "Parameter Nr.",
            "Übergabestelle Nr.",
            "Messstelle Nr.",
            "Genehmigung Nr.",
            "Überwachungswert"
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
        if (objectAtRow instanceof InkaUeberwachungswert) {
            return getFromDB(objectAtRow, columnIndex);
        }
        else if (objectAtRow instanceof Inka_Ueberwachungswert) {
            return getFromService(objectAtRow, columnIndex);
        }
        else {
            return "Error";
        }
    }

    public Object getFromDB(Object obj, int index) {
        switch (index) {
            case 0: return ((InkaUeberwachungswert) obj).getId().getParameterNr();
            case 1: return ((InkaUeberwachungswert) obj).getUebergabestelleLfdNr();
            case 2: return ((InkaUeberwachungswert) obj).getId().getMessstelleLfdNr();
            case 3: return ((InkaUeberwachungswert) obj).getGenehmigungNr();
            case 4: return ((InkaUeberwachungswert) obj).getUewert();
            default: return "ERROR";
        }
    }

    public Object getFromService(Object obj, int index) {
        switch (index) {
            case 0: return ((Inka_Ueberwachungswert) obj).getParameter_nr();
            case 1: return ((Inka_Ueberwachungswert) obj).getUebergabestelle_lfd_nr();
            case 2: return ((Inka_Ueberwachungswert) obj).getMessstelle_lfd_nr();
            case 3: return ((Inka_Ueberwachungswert) obj).getGenehmigung_nr();
            case 4: return ((Inka_Ueberwachungswert) obj).getUewert();
            default: return "ERROR";
        }
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     */
    @Override
    public void updateList() {
    }
}
