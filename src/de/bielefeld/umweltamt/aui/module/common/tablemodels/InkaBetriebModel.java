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

import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetrieb;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

import de.nrw.lds.tipi.inka.Inka_Betrieb;

public class InkaBetriebModel extends ListTableModel {
    /** Logging */
    private static final AuikLogger logger = AuikLogger.getLogger();

    public InkaBetriebModel() {
        super(new String[]{
                "Betrieb Nr.",
                "Adresse-Standort Nr.",
                "Adresse-Einleiter Nr.",
                "Gemeindekennzahl"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        if (objectAtRow == null) {
            return "error";
        }
        if (objectAtRow instanceof InkaBetrieb) {
            return getInkaBetriebFromDB(objectAtRow, columnIndex);
        }
        else if (objectAtRow instanceof Inka_Betrieb) {
            return getInkaBetriebFromService(objectAtRow, columnIndex);
        }
        else {
            return "Error";
        }
    }

    public Object getInkaBetriebFromDB(Object obj, int ndx) {
        InkaBetrieb fd = (InkaBetrieb) obj;
        Object tmp;

        switch (ndx) {
        case 0:
            tmp = fd.getId().getBetriebNr();
        case 1:
            tmp = fd.getAdresseStandNr();
            break;
        case 2:
            tmp = fd.getAdresseEinleitNr();
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


    public Object getInkaBetriebFromService(Object obj, int ndx) {
        Inka_Betrieb fd = (Inka_Betrieb) obj;
        Object tmp;

        switch (ndx) {
        case 0:
            tmp = fd.getBetrieb_nr();
        case 1:
            tmp = fd.getAdresse_stand_nr();
            break;
        case 2:
            tmp = fd.getAdresse_einleit_nr();
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
    public void updateList() {
    }
}
