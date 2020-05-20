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

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für eine Standortliste.
 * @author Gerd Genuit
 */
public class BasisAdresseModel extends ListTableModel {
    private AuikLogger log = AuikLogger.getLogger();
    private String strasse = null;
    private String lastSuchWort = null;
    private String lastProperty = null;
    private String lastStrasse = null;
    private Integer lastHausnr = null;
    private String LastZus = null;
    private String LastOrt = null;
//    private String secondColumn = null;

    //    private String secondColumn = null;
	


	public BasisAdresseModel() {
        super(new String[]{
                "Strasse",
                "Nr",
                "Zus",
                "Plz",
                "Entgeb.",
                "Standortggh.",
                "Wassereinzgeb.",
                "Bemerkungen"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
    	Inhaber inh = (Inhaber) objectAtRow;
//    	Adresse adr = (Adresse) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = inh.getAdresse().getStrasse();
            break;
        case 1:
            tmp = inh.getAdresse().getHausnr();
            break;
        case 2:
            tmp = inh.getAdresse().getHausnrzus();
            break;
        case 3:
            tmp = inh.getAdresse().getPlz();
            break;
        case 4:
            tmp = inh.getAdresse().getEntgebid();
            break;
        case 5:
            tmp = inh.getAdresse().getStandortgghwsg();
            break;
        case 6:
            tmp = inh.getAdresse().getWassereinzugsgebiet();
            break;
        case 7:
            tmp = inh.getAdresse().getBemerkungen();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        return tmp;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    @Override
    public void updateList() {
        if (strasse != null) {
            setList(DatabaseQuery.findStandorte(strasse));

            fireTableDataChanged();
        }
        else setList(null);
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public Inhaber getRow(int rowIndex) {
        return (Inhaber) super.getObjectAtRow(rowIndex);
    }

    /**
     * Filtert den Tabelleninhalt nach Anrede, Name oder Zusatz.
     * Zu den möglichen Werten von <code>property</code>, siehe {@link BasisAdresse#findBetreiber(String, String)}.
     * @param suche Der Such-String
     * @param property Die Eigenschaft, nach der Gesucht werden soll, oder <code>null</code>.
     */
    public void filterList(String suche, String property) {
        log.debug("Start filterList");
        setList(DatabaseQuery.getAdresse(property, suche));
        lastSuchWort = suche;
        lastProperty = property;
        log.debug("End filterList");
    }
    
    public void filterAllList(String suche, String property) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findAdressen(suche, property, null, null));
        lastSuchWort = suche;
        lastProperty = property;
        log.debug("End filterList");
    }
    
    public void filterStandort(String strasse, Integer hausnr, String ort) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findInhaber(strasse, hausnr, ort));
        lastStrasse = strasse;
        lastHausnr = hausnr;
        LastOrt = ort;
        log.debug("End filterList");
    }
}
