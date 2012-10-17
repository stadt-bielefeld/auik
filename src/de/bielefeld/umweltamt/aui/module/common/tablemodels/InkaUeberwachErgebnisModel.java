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

import de.bielefeld.umweltamt.aui.mappings.tipi.InkaUeberwachErgebnis;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis;

public class InkaUeberwachErgebnisModel extends ListTableModel {
    private static final long serialVersionUID = 3913291284739009733L;

    public InkaUeberwachErgebnisModel() {
        super(new String[]{
            "Parameter Nr.",
            "Probenahme Nr.",
            "Masseinheit Nr.",
            "Messergebnis Text",
            "Messergebnis"
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
        if (objectAtRow instanceof InkaUeberwachErgebnis) {
            return getFromDB(objectAtRow, columnIndex);
        }
        else if (objectAtRow instanceof Inka_Ueberwach_Ergebnis) {
            return getFromService(objectAtRow, columnIndex);
        }
        else {
            return "Error";
        }
    }

    public Object getFromDB(Object obj, int index) {
        switch (index) {
            case 0:
                return ((InkaUeberwachErgebnis) obj).getId().getParameterNr();
            case 1:
                return ((InkaUeberwachErgebnis) obj).getId().getProbenahmeNr();
            case 2: return ((InkaUeberwachErgebnis) obj).getEinMasseinheitNr();
            case 3: return ((InkaUeberwachErgebnis) obj).getMessergebnisText();
            case 4: return ((InkaUeberwachErgebnis) obj).getMessergebnis();
            default: return "ERROR";
        }
    }

    public Object getFromService(Object obj, int index) {
        switch (index) {
            case 0: return ((Inka_Ueberwach_Ergebnis) obj).getParameter_nr();
            case 1: return ((Inka_Ueberwach_Ergebnis) obj).getProbenahme_nr();
            case 2: return ((Inka_Ueberwach_Ergebnis) obj).getEin_masseinheit_nr();
            case 3: return ((Inka_Ueberwach_Ergebnis) obj).getMessergebnis_text();
            case 4: return ((Inka_Ueberwach_Ergebnis) obj).getMessergebnis();
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
