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
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EStandort;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class EStandortModel extends ListTableModel {

    public EStandortModel() {
        super(new String[]{
                "ID",
                "Strasse",
                "Hausnummer",
                "PLZ",
                "Ort",
                "Flur",
                "Flurstücke"},
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

        EStandort standort = (EStandort) objectAtRow;
        switch(columnIndex) {
            case 0:
                value = standort.getNr();
                break;
            case 1:
            	value = standort.getAdresse().getStrasse();
            	break;
            case 2:
            	value = standort.getAdresse().getHausnr();
            	break;
            case 3:
            	value = standort.getAdresse().getPlzZst();
            	break;
            case 4:
            	value = standort.getAdresse().getOrtZst();
            	break;
            case 5:
            	value = standort.getFlur();
            	break;
            case 6:
            	value = standort.getFlurstuecke();
            	break;
            default:
                value = null;
        }

        return value;
	}

}
