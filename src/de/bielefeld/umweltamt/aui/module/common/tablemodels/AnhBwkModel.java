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
 * $Id: AnhBwkModel.java,v 1.4 2010-01-27 07:51:09 u633d Exp $
 *
 * Erstellt am 24.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2010/01/20 12:57:31  u633d
 * Auswertungen order by inaktiv
 *
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/08/25 14:46:59  u633d
 * - zu viel ;)
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import java.util.Date;

import de.bielefeld.umweltamt.aui.mappings.indeinl.ViewBwk;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Brennwertkessel-Fachdaten.
 * @author David Klotz
 */
public class AnhBwkModel extends ListTableModel {
    private static final long serialVersionUID = -1745320863047939661L;

    /**
     * Erzeugt ein einfaches TableModel für Brennwertkessel-Fachdaten.
     */
    public AnhBwkModel() {
        super(new String[] {"Betreiber", "Standort", "Hersteller",
                "Brennmittel", "Leistung", "Erfassung", "Anschreiben",
                "Genehmigung", "Beschreibung"}, false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        ViewBwk fd = (ViewBwk) objectAtRow;
        Object tmp;
        String anrede;
        String hausnr;
        String hausnrzus;

        if (fd.getBetranrede() != null) {
            anrede = fd.getBetranrede() + " ";
        } else {
            anrede = "";
        }

        if (fd.getHausnr() != null) {
            hausnr = fd.getHausnr().toString();
        } else {
            hausnr = "";
        }

        if (fd.getHausnrzus() != null) {
            hausnrzus = fd.getHausnrzus();
        } else {
            hausnrzus = "";
        }

        switch (columnIndex) {
            case 0:
                tmp = anrede + fd.getBetrname();
                break;
            case 1:
                tmp = fd.getStrasse() + " " + hausnr + hausnrzus;
                break;
            case 2:
                tmp = fd.getKHersteller();
                break;
            case 3:
                tmp = fd.getKBrennmittel();
                break;
            case 4:
                tmp = fd.getKLeistung();
                break;
            case 5:
                tmp = fd.getErfassung();
                break;
            case 6:
                tmp = fd.getAnschreiben();
                break;
            case 7:
                tmp = fd.getDatumG();
                break;
            case 8:
                tmp = fd.getBasisObjekt().getBeschreibung();
                break;

            default:
                tmp = "ERROR";
                break;
        }
        /* Do not try to paint a strike throw a date field... */
        if (columnIndex == 6 || columnIndex == 7) {
            return tmp;
        }
        if (fd.getBasisObjekt().getInaktiv()) {
            tmp = StringUtils.setStrike(tmp.toString());
        }
        return tmp;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return Integer.class;
            case 5: return Integer.class;
            case 6: return Date.class;
            case 7: return Date.class;
            case 8: return String.class;
            default: return null;
        }
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     */
    @Override
    public void updateList() {
    }
}
