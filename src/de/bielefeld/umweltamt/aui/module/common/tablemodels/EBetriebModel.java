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

import de.bielefeld.umweltamt.aui.mappings.elka_sync.EBetrieb;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class EBetriebModel extends ListTableModel {

    private static final long serialVersionUID = -6814886893836025013L;

    public EBetriebModel() {
        super(new String[]{
                "Anrede",
                "Name",
                "Strasse",
                "Hausnummer",
                "PLZ",
                "Ort",
                "Bezeichnung",
                "selbstüberwachend",
                "Objekt-ID"},
                false,
                true);
    }

    @Override
    public void updateList() throws Exception {
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 8:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;

        EBetrieb betrieb = (EBetrieb) objectAtRow;
        switch(columnIndex) {
            case 0:
                value = betrieb.getStandort().getAdresse().getAnrede();
                break;
            case 1:
                value = betrieb.getStandort().getAdresse().getName1();
                break;
            case 2:
                value = betrieb.getStandort().getAdresse().getStrasse();
                break;
            case 3:
                value = betrieb.getStandort().getAdresse().getHausnr();
                break;
            case 4:
                value = betrieb.getStandort().getAdresse().getPlzZst();
                break;
            case 5:
                value = betrieb.getStandort().getAdresse().getOrtZst();
                break;
            case 6:
                value = betrieb.getBezeichnung();
                break;
            case 7:
                value = betrieb.getSuevkanTog() ? "ja" : "nein";
                break;
            case 8:
                value = betrieb.getObjektId();
                break;
            default:
                value = null;
        }
        return value;
    }
}
