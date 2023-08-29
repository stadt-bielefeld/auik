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
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class EAnfallstelleModel extends ListTableModel {

    private static final long serialVersionUID = -4682165276528632515L;

    public EAnfallstelleModel() {
        super(new String[]{
                "Betreiber",
                "Adresse",
                "Bemerkung",
                "Bezeichnung",
                "Objekt-ID"},
                false,
                true);
    }

    @Override
    public void updateList() throws Exception {
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
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
                value = StringUtils.createAddressString(anlage.getAdresse());
                break;
            case 2:
                value = anlage.getBezeichnung();
                break;
            case 3:
                value = anlage.getBemerkung();
                break;
            case 4:
                value = anlage.getNr();
                break;
            default:
                value = null;
        }
        return value;
    }

}
