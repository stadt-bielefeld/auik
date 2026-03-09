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
 * $Id: Anh56Model.java,v 1.3 2010-01-20 12:56:53 u633d Exp $
 *
 * Erstellt am 03.05.2006 von Gerd Genuit
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2006/05/03 09:01:54  u633d
 * Anhang 40 und 56 ergänzt
 *
 * Revision 1.1  2006/05/03 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für eine Standortliste einer Adresse.
 * @author Gerd Genuit
 */
public class BasisStdModel extends ListTableModel<Standort> {
    private Inhaber inhaber = null;

    public BasisStdModel() {
        super(new String[]{
                "Bezeichnung",
                "E32",
                "N32"
        },
        false, true);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Standort std, int columnIndex) {
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = std.getBezeichnung();
            break;
        case 1:
            tmp = std.getE32();
            break;
        case 2:
            tmp = std.getN32();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        return tmp;
    }


    public void setInhaber(Inhaber inhaber) {
        this.inhaber = inhaber;
    }

    @Override
    public void updateList() {

        if (inhaber != null) {
            setList(Standort.findByAdresse(inhaber));

            fireTableDataChanged();
        }
        else setList(null);

    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public Standort getRow(int rowIndex) {
        return (Standort) super.getObjectAtRow(rowIndex);
    }

    /**
     * Remove Standort from the model
     * @param standort
     */
    public boolean objectRemoved(Standort removedObjekt) {
        return Standort.delete(removedObjekt);
    }

}
