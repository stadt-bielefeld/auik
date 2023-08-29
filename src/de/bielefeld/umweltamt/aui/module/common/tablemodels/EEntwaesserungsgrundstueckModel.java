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
                "Betreiber",
                "Standort",
                "Wasserrecht",
                "Beschreibung",
                "Entwässerungsgebiet",
                "Erstellt-Datum"
                },
                false,
                true);
    }

	@Override
	public void updateList() throws Exception {
		// TODO Auto-generated method stub

	}

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            default:
                return String.class;
        }
    }

	@Override
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;

        EEntwaesserungsgrundstueck ewg= (EEntwaesserungsgrundstueck) objectAtRow;
        switch(columnIndex) {
            case 0: 
                return ewg.getNr();
            case 1:
                return ewg.getAdresse().getName1();
            case 2:
                return ewg.getStandort().getNr();
            case 3:
                return ewg.getWasserrecht() != null ? ewg.getWasserrecht().getNr(): null;
            case 4:
                return ewg.getBemerkung();
            case 5:
                return ewg.getNameEtwGebiet();
            case 6:
                return ewg.getErstellDat();

            default:
                value = null;
        }

        return value;
	}

}
