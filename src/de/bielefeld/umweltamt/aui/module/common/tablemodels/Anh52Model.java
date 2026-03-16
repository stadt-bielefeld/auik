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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Anhang 52-Fachdaten.
 * @author Gerd Genuit
 */
public class Anh52Model extends ListTableModel<Anh52Fachdaten> {
    private static final long serialVersionUID = 4210501678157015654L;

    public Anh52Model() {
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
    public Object getColumnValue(Anh52Fachdaten fd, int columnIndex) {
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = fd.getAnfallstelle().getObjekt().getBetreiberid();
            break;
        case 1:
            tmp = DatabaseQuery.getStandortString(fd.getAnfallstelle().getObjekt().getStandortid());
            break;
        case 2:
            tmp = fd.getBemerkungen();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        if (tmp != null && fd.getAnfallstelle().getObjekt().isInaktiv()) {
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
