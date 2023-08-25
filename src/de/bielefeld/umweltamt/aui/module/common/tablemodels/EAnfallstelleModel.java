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

import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAnfallstelle;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class EAnfallstelleModel extends ListTableModel {

    public EAnfallstelleModel() {
        super(new String[]{
                "Betreiber",
                "Bemerkung",
                "Bezeichnung",
                "Objekt-ID",
                "Adresse"},
                false,
                true);
    }

	@Override
	public void updateList() throws Exception {
		// TODO Auto-generated method stub

	}

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 3:
            case 4:
                return Integer.class;
            default:
                return String.class;
        }
    }

	@Override
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;

        EAnfallstelle anlage = (EAnfallstelle) objectAtRow;
        switch(columnIndex) {
            case 0:
          		value = anlage.getStandort().getAdresse().getName1();
                break;
            case 1:
                value = anlage.getBezeichnung();
                break;
            case 2:
                value = anlage.getBemerkung();
                break;
            case 3:
                value = anlage.getNr();
                break;
            case 4:
                value = anlage.getAdresse().getNr();
                break;
            default:
                value = null;
        }

        return value;
	}

}
