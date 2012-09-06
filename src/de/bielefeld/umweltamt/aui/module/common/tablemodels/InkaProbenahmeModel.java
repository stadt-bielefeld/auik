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

import de.bielefeld.umweltamt.aui.mappings.tipi.InkaProbenahme;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.nrw.lds.tipi.inka.Inka_Probenahme;

public class InkaProbenahmeModel extends ListTableModel {
    private static final long serialVersionUID = 3913291284739009733L;

    public InkaProbenahmeModel() {
        super(new String[]{
            "Probenahme Nr.",
            "Gemeindekennzahl",
            "Übergabestelle Nr.",
            "Messstelle Nr.",
            "Datum Analyse",
            "Selbstüberwachung",
            "Probe Nr."
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
        if (objectAtRow instanceof InkaProbenahme) {
            return getFromDB(objectAtRow, columnIndex);
        }
        else if (objectAtRow instanceof Inka_Probenahme) {
            return getFromService(objectAtRow, columnIndex);
        }
        else {
            return "Error";
        }
    }

    public Object getFromDB(Object obj, int index) {
        switch (index) {
            case 0: return ((InkaProbenahme) obj).getProbenahmeNr();
            case 1: return ((InkaProbenahme) obj).getGemeindekennzahl();
            case 2: return ((InkaProbenahme) obj).getUebergabestelleLfdNr();
            case 3: return ((InkaProbenahme) obj).getMessstelleLfdNr();
            case 4:
                return DateUtils.format(
                    ((InkaProbenahme) obj).getDatumAnalyse().getTime(),
                    DateUtils.FORMAT_DATE);
            case 5: return ((InkaProbenahme) obj).getSelbstueberwJn();
            case 6: return ((InkaProbenahme) obj).getProbeNr();
            default: return "ERROR";
        }
    }

    public Object getFromService(Object obj, int index) {
        switch (index) {
            case 0: return ((Inka_Probenahme) obj).getProbenahme_nr();
            case 1: return ((Inka_Probenahme) obj).getGemeindekennzahl();
            case 2: return ((Inka_Probenahme) obj).getUebergabestelle_lfd_nr();
            case 3: return ((Inka_Probenahme) obj).getMessstelle_lfd_nr();
            case 4: return ((Inka_Probenahme) obj).getDatum_analyse().getTime();
            case 5: return ((Inka_Probenahme) obj).getSelbstueberw_jn();
            case 6: return ((Inka_Probenahme) obj).getProbe_nr();
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
