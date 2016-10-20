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

import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh53Fachdaten;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Anhang 53-Fachdaten.
 * @author Gerd Genuit
 */
public class Anh53Model extends ListTableModel {
    private static final long serialVersionUID = 2547591753806408790L;

    public Anh53Model() {
        super(new String[]{
                "Betreiber",
                "Standort",
                "Bemerkungen"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Anh53Fachdaten fd = (Anh53Fachdaten) objectAtRow;
        Object tmp;

        switch (columnIndex) {
            case 0:
                tmp = fd.getBasisObjekt().getBasisAdresse();
                break;
            case 1:
                tmp = DatabaseQuery.getStandortString(fd.getBasisObjekt().getBasisStandort());
                break;
            case 2:
                if (fd.getBasisObjekt().getBasisObjektarten().getId().equals(
                    DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_KLEIN)) {
                    tmp = "kleiner 3000";
                } else {
                    tmp = "größer 3000";
                }
                break;
            case 3:
                tmp = fd.getBasisObjekt().getBeschreibung();
                break;

            default:
                tmp = "ERROR";
        }
        if (fd.getBasisObjekt().isInaktiv()) {
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
