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

import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAdresse;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EEntwaesserungsgrundstueck;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EStandort;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class EEntwaesserungsgrundstueckModel extends ListTableModel {

    public EEntwaesserungsgrundstueckModel() {
        super(new String[]{
                "Nr",
                "erl_frei_el_tog",
                "Regenspende",
                "Regenhäufigkeit",
                "Beschreibung",
                "Regendauer",
                "gr_entw_gebiet",
                "Standort",
                "dtv_wert",
                "wasserabtleitungsstrecke_opt",
                "Entwässerungsgebiet",
                "Erstellt-Datum",
                "einl_bereich_opt",
                "abwbeskon_nr",
                "einbauart_opt",
                "Aktualisierungsdatum",
                "Betreiber",
                "Wasserrecht",
                "Herkunft",
                "external_nr"
                },
                false,
                true);
    }

	@Override
	public void updateList() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;

        EEntwaesserungsgrundstueck ewg= (EEntwaesserungsgrundstueck) objectAtRow;
        switch(columnIndex) {
            case 0: 
                return ewg.getNr();
            case 1:
                return ewg.getErlFreiElTog();
            case 2:
                return ewg.getRegenspende();
            case 3:
                return ewg.getRegenhaeufigkeit();
            case 4:
                return ewg.getBemerkung();
            case 5:
                return ewg.getRegendauer();
            case 6:
                return ewg.getGrEntwGebiet();
            case 7:
                return ewg.getStandort().getNr();
            case 8:
                return ewg.getDtvWert();
            case 9:
                return ewg.getWasserableitungsstreckeOpt();
            case 10:
                return ewg.getNameEtwGebiet();
            case 11:
                return ewg.getErstellDat();
            case 12:
                return ewg.getEinlBereichOpt();
            case 13:
                return ewg.getAbwbeskonnr();
            case 14:
                return ewg.getEinbauartOpt();
            case 15:
                return ewg.getAktualDat();
            case 16:
                return ewg.getAdresse().getName1();
            case 17:
                return ewg.getWasserrecht() != null ? ewg.getWasserrecht().getNr(): null;
            case 18:
                return ewg.getHerkunft();
            case 19:
                return ewg.getExternalNr();
            default:
                value = null;
        }

        return value;
	}

}
