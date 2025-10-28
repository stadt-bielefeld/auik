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

import de.bielefeld.umweltamt.aui.mappings.elka_sync.ESonderbauwerk;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class ESonderbauwerkModel extends ListTableModel<ESonderbauwerk> {

    public ESonderbauwerkModel() {
        super(new String[]{
                "Nr",
                "Bezeichnung",
                "Kurzbeschreibung",
                "Inbetriebnahme",
                "Stillgelegt am",
                "Standort",
                "Betreiber"
                },
                false,
                true);
    }

	@Override
	public void updateList() throws Exception {
	}

	@Override
	public Object getColumnValue(ESonderbauwerk sb, int columnIndex) {
        Object value;

        switch(columnIndex) {
            case 0: return sb.getNr();
            case 1: return sb.getBezeichnung();
            case 2: return sb.getInbetriebnahme();
            case 3: return sb.getStillgelegtAm();
            case 4: return sb.getWiederinbetrDat();
            case 5: return sb.getStandort().getNr();
            case 6: return sb.getAdresseByBetreibAdrNr() != null ? sb.getAdresseByBetreibAdrNr().getName1() : null;
                        default:
                value = null;
        }

        return value;
	}

}
