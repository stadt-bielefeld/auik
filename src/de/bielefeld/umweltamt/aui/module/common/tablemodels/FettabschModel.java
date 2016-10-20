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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
/**
 * Ein einfaches TableModel für Fettabscheider.
 * @author Sebastian Geller
 */
public class FettabschModel extends ListTableModel {
    private static final long serialVersionUID = 6474195217246081874L;

    public FettabschModel() {
        super(new String[]{
                "Betreiber",
                "Standort",
                "Bemerkungen",
                "Genehmigungsdatum",
                "Abfuhrdatum",
                "nächste Abfuhr",
                "letztes Chrono-Datum"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
	@Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Anh49Fachdaten fd = (Anh49Fachdaten) objectAtRow;
		Object tmp = null;
		switch (columnIndex) {
    		case 0:
    			tmp = fd.getBasisObjekt().getBasisAdresse();
    			break;
    		case 1:
    			tmp = DatabaseQuery.getStandortString(fd.getBasisObjekt().getBasisStandort());
    			break;
    		case 2:
    			tmp = fd.getBemerkungen();
    			break;
    		case 3:
    			tmp = fd.getGenehmigung();
    			break;
    		case 4:
    			tmp = DatabaseQuery.getLastAbfuhrDateForObjekt(
    		    		fd);
    			break;
    		case 5:
    			tmp = DatabaseQuery.getNextAbfuhrDateForObjekt(
    		    		fd);
    			break;
    		case 6:
    		    tmp = DatabaseQuery.getLastChronoDateForObjekt(
    		    		fd.getBasisObjekt());
    		    break;
    		default:
    			tmp = "ERROR";
		}

		if (tmp != null &&
			fd.getBasisObjekt().isInaktiv()) {
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
