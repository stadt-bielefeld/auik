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
 * $Id: VawsModel.java,v 1.4 2009-03-24 12:35:23 u633d Exp $
 *
 * Erstellt am 03.09.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2008/06/24 11:24:09  u633d
 * Version 0.3
 *
 * Revision 1.2  2008/06/12 10:21:42  u633d
 * diverse Bugfixes
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.3  2005/11/02 13:53:24  u633d
 * - Version vom 2.11.
 *
 * Revision 1.2  2005/09/28 11:11:14  u633d
 * - Version vom 28.9.
 *
 * Revision 1.1  2005/09/07 10:48:23  u633d
 * - Version vom 7.9.05
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFachdaten;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Vaws-Fachdaten.
 * @author David Klotz
 */
public class VawsModel extends ListTableModel {
    private static final long serialVersionUID = -2723711304275935781L;

    /**
     * Erzeugt ein einfaches TableModel für Vaws-Fachdaten.
     */
    public VawsModel() {
        super(new String[]{
                "Anlagenart",
                "Bezeichnung/Herstellnr.",
                "Flüssigkeit",
                "Menge",
                "Stillgelegt"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        VawsFachdaten fd = (VawsFachdaten) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        // Anlagenart:
        case 0:
            tmp = fd.getAnlagenart();
            break;
        // Herstellnummer:
        case 1:
            tmp = fd.getHerstellnr();
            break;
        // Flüssigkeit:
        case 2:
            tmp = fd.getFluessigkeit();
            break;
        // Menge:
        case 3:
            if(fd.getMenge()!=null)
                tmp = Math.round(fd.getMenge()*100.)/100.;
            else
                tmp = 0;
            break;
        // Stillgelegt:
        case 4:
            tmp = DateUtils.format(
                fd.getStillegungsdatum(), DateUtils.FORMAT_DATE);
            break;

        // Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
        default:
            tmp = "ERROR";
            break;
        }

        if ((tmp != null) && (fd.getStillegungsdatum() != null)) {
            tmp = StringUtils.setStrike(tmp.toString());
        }

        return tmp;
    }

    /**
     * Liefert einen Datensatz in einer bestimmten Zeile.
     * @param row Die Zeile der Tabelle.
     * @return Den Datensatz, der in dieser Zeile angezeigt wird.
     */
    public VawsFachdaten getDatenSatz(int row) {
        return (VawsFachdaten) getObjectAtRow(row);
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     * Die Liste wird direkt mittels setList "befüllt".
     */
    @Override
    public void updateList() {
    }
}
