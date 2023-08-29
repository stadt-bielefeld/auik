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
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAdresse;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EMessstelle;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.StringUtils;


public class EMessstelleModel extends ListTableModel {

    AuikLogger log = AuikLogger.getLogger();

	public EMessstelleModel() {
        super(new String[]{
                "Standort-Nr",
                "Bezeichnung",
                "Beschreibung",
                "Betreiber",
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
            case 0:
            case 4:
                return Integer.class;
            default:
                return String.class;
        }
    }

	@Override
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;

        EMessstelle stelle = (EMessstelle) objectAtRow;
        switch(columnIndex) {
            case 0:
          		//value = stelle.getStandortNr().getAdresse().getName1();
                //value = EAdresse.findById(stelle.getNr()).getName1();
				value = stelle.getStandort().getAdresse().getNr();
                break;
            case 1:
                value = stelle.getBezeichnung();
                break;
            case 2:
                value = stelle.getBemerkung();
                break;
            case 3:
                value = stelle.getStandort().getAdresse().getName1();
                break;
            case 4:
                value = stelle.getObjektId();
                break;
            case 5:
                value = StringUtils.createAddressString(
                    stelle.getStandort().getAdresse());
                break;
            default:
                value = null;
        }

        return value;
	}

}
