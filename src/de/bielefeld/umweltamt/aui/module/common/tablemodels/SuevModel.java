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

/*
 * Datei:
 * $Id: SuevModel.java,v 1.3 2010-01-20 12:58:19 u633d Exp $
 *
 * Erstellt am 24.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.SuevFachdaten;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für SUEV-Fachdaten.
 * @author David Klotz
 */
public class SuevModel extends ListTableModel {
    private static final long serialVersionUID = -9088369986542868203L;

    /**
     * Erzeugt ein einfaches TableModel für
     * SUEV-Fachdaten.
     */
    public SuevModel() {
        super(new String[]{
                "Betreiber",
                "Standort",
                "vers. Fläche"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        SuevFachdaten fd = (SuevFachdaten) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            if (fd.getObjekt() != null) {
                tmp = fd.getObjekt().getBetreiberid();
            } else {
                tmp = "<html><font color=red>KEIN BASISOBJEKT!</font></html>";
            }
            break;
        case 1:
            if (fd.getObjekt() != null) {
                tmp = fd.getObjekt().getStandortid();
            } else {
                tmp = "<html><font color=red>KEIN BASISOBJEKT!</font></html>";
            }
            break;
        case 2:
            tmp = fd.getVersFlaeche();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        if (fd.getObjekt().isInaktiv()) {
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
