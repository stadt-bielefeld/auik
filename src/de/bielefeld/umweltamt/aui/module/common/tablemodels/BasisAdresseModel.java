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
import de.bielefeld.umweltamt.aui.mappings.basis.BasisAdresse;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
/**
 * Ein TableModel für die Basis-Betreiberdaten.
 * @author David Klotz
 */
public class BasisAdresseModel extends ListTableModel {
    private static final long serialVersionUID = -1943023265274962194L;
    private String lastSuchWort = null;
    private String lastProperty = null;

    public BasisAdresseModel() {
        this(true);
    }

    public BasisAdresseModel(boolean zeigeAdresse) {
        super(new String[]{
                "Name",
                "Anrede",
                "Vorname",
                "Zusatz",
                "Ort",
                "Straße"}, false, true);

        if (zeigeAdresse) {
            columns = new String[]{
                    "Name",
                    "Anrede",
                    "Vorname",
                    "Zusatz",
                    "Ort",
                    "Straße",
                    "Nr."
            };
        }
    }

    /**
     * Aktualisiert die aktuell angezeigte Liste in dem die letzte Suche wiederholt wird.
     */
    @Override
    public void updateList() {
        if (lastSuchWort != null) {
            filterList(lastSuchWort, lastProperty);
        }
    }

    /**
     * Liefert den Inhalt der Zelle mit den gegebenen Koordinaten.
     * @param objectAtRow Das Objekt in dieser Zeile der Tabelle
     * @param columnIndex Die Spalte der Tabelle
     * @return Den Wert der Zelle oder null (falls die Zelle nicht existiert)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;

        BasisAdresse betr = (BasisAdresse) objectAtRow;
        switch(columnIndex) {
            case 0:
            	if (betr.getKassenzeichen() != null) {
            		String tmp = betr.getBetrname() + " (" + betr.getKassenzeichen() + ")";
            		value = tmp;
            	} else {
            		value = betr.getBetrname();
            	}
                break;
            case 1:
                value = betr.getBetranrede();
                break;
            case 2:
                value = betr.getBetrvorname();
                break;
            case 3:
                value = betr.getBetrnamezus();
                break;
            case 4:
                value = betr.getOrt();
                break;
            case 5:
                value = betr.getStrasse();
                break;
            case 6:
                if (betr.getHausnrzus() != null) {
                    String tmp = betr.getHausnr() + betr.getHausnrzus();
                    value = tmp;
                } else {
                    value = betr.getHausnr();
                }
                break;
            default:
                value = null;
        }

        return value;
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public BasisAdresse getRow(int rowIndex) {
        return (BasisAdresse) super.getObjectAtRow(rowIndex);
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        BasisAdresse removedBetreiber = (BasisAdresse) objectAtRow;
        return BasisAdresse.delete(removedBetreiber);
    }

    /**
     * Filtert den Tabelleninhalt nach Anrede, Name oder Zusatz.
     * Zu den möglichen Werten von <code>property</code>, siehe {@link BasisAdresse#findBetreiber(String, String)}.
     * @param suche Der Such-String
     * @param property Die Eigenschaft, nach der Gesucht werden soll, oder <code>null</code>.
     */
    public void filterList(String suche, String property) {
        setList(DatabaseQuery.getBasisAdresse(property, suche));
        lastSuchWort = suche;
        lastProperty = property;
    }
}
