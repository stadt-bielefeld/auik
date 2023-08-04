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

import java.util.Date;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * TableModel used in the BasisAbfrage module.
 * @author Alexander Woestmann
 */
public class BasisAbfrageModel extends ListTableModel {

    private final int ADRESS_COLUMN_INDEX = 1;
    public BasisAbfrageModel() {
        super(new String[]{
            "Betreibername",
            "Standort",
            "Entwässerungsgebiet",
            "SB",
            "E-Satzungsüberwachung vorhanden",
            "Anhang",
            "Anlagenart",
            "Bemekerung (Objekt)",
            "Bemerkung (Anhang)",
            "Wiedervorlage",
            "Priorität"
        }, false);
    }

    @Override
    public void updateList() throws Exception {
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 4: return Boolean.class;
            case 9: return Date.class;
            default: return String.class;
        }
    }

    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object[] obj = (Object[]) objectAtRow;
        Object val = obj[columnIndex];
        //If column contains address: format
        if (columnIndex == ADRESS_COLUMN_INDEX) {
            Object[] parts = ((String) val).split(DatabaseQuery.ADDRESS_SEPARATOR);
            if (parts.length == 5) {
                val = String.format("%s %s%s, %s %s", parts);
            }
        }
        return val;
    }
}
