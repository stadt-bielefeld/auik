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

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Fettabscheider.
 * @author Sebastian Geller
 */
public class FettabschModel extends ListTableModel<Anfallstelle> {
    private static final long serialVersionUID = 6474195217246081874L;

    public FettabschModel() {
        super(new String[]{
                "Betreiber",
                "Standort",
                "Entwässerungsgebiet",
                "Sachbearbeiter:in",
                "Bemerkungen (Fettabscheider)",
                "Beschreibung (Objekt)",
                "Wiedervorlage (Objekt)",
                "letzte Änderung (Chronologie)"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
	@Override
    public Object getColumnValue(Anfallstelle fd, int columnIndex) {
		Object tmp = null;
		Inhaber inh = fd.getObjekt().getStandortid().getInhaber();
		Adresse addr = inh != null ? inh.getAdresse() : null;
		switch (columnIndex) {
    		case 0:
    			tmp = fd.getObjekt().getBetreiberid();
    			break;
    		case 1:
    			tmp = DatabaseQuery.getStandortString(fd.getObjekt().getStandortid());
    			break;
    		case 2:
    			tmp= addr != null ? addr.getEntgebid() : "";
    			break;
    		case 3:
            	tmp= fd.getObjekt().getSachbearbeiter();
                break;
    		case 4:
    			tmp = fd.getAnh49Fachdatens().iterator().next().getBemerkungen();
    			break;
    		case 5:
    			tmp= fd.getObjekt().getBeschreibung();
    			break;
    		case 6:
    			tmp = fd.getObjekt().getWiedervorlage();
    			break;
    		case 7:
    		    tmp = DatabaseQuery.getLastChronoDateForObjekt(
    		    		fd.getObjekt());
    		    break;
    		default:
    			tmp = "ERROR";
		}

		if (tmp != null &&
			fd.getObjekt().isInaktiv()) {
            tmp = StringUtils.setStrike(tmp.toString());
		}
		return tmp;
	}

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     */
    @Override
    public void updateList() {

    }
}
