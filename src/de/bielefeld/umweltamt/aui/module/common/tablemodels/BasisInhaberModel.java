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

import java.util.ResourceBundle;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Ein TableModel für die Basis-Betreiberdaten.
 * @author David Klotz
 */
public class BasisInhaberModel extends ListTableModel {
    private static final long serialVersionUID = -1943023265274962194L;
    private String lastSuchWort = null;
    private String lastProperty = null;
    private AuikLogger log = AuikLogger.getLogger();
    private static final ResourceBundle I18N =
        SettingsManager.getInstance().getI18nBundle();

    public BasisInhaberModel() {
        this(true);
    }

    public BasisInhaberModel(boolean zeigeAdresse) {
        super(new String[]{
                I18N.getString("fields.id"),
                I18N.getString("editor.betreiber.company_name"),
                I18N.getString("editor.betreiber.first_name"),
                I18N.getString("fields.city"),
                I18N.getString("fields.street"),
                I18N.getString("fields.nr")}, false, true);
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
        Object tmp = null;
        Inhaber betr = (Inhaber) objectAtRow;

        HibernateSessionFactory.currentSession().refresh(betr);

        switch (columnIndex) {
        case 0:
            tmp = betr.getId();
            break;
        case 1:
            if (betr.getKassenzeichen() != null) {
                String value = betr.getName() + " ("
                    + betr.getKassenzeichen()
                    + ")";
                tmp = value;
            } else {
                tmp = betr.getName();
            }
            break;
        case 2:
            tmp = betr.getVorname();
            break;
        case 3:
            tmp = betr.getAdresse().getOrt();
            break;
        case 4:
            tmp = betr.getAdresse().getStrasse();
            break;
        case 5:
            if (betr.getAdresse().getHausnrzus() != null) {
                String value = betr.getAdresse().getHausnr()
                    + betr.getAdresse().getHausnrzus();
                tmp = value;
            } else {
                tmp = betr.getAdresse().getHausnr();
            }
            break;
        default:
            tmp = null;
        }

        return tmp;
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public Inhaber getRow(int rowIndex) {
        return (Inhaber) super.getObjectAtRow(rowIndex);
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Object obj = objectAtRow;
        Inhaber removedBetreiber = (Inhaber) obj;
        return Inhaber.delete(removedBetreiber);
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
        setList(DatabaseQuery.findAdressen(suche, property));
        lastSuchWort = suche;
        lastProperty = property;
        log.debug("End filterList");
    }

    public void filterAllList(String suche, String strasse, Integer hausnr, String ort, String property) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findAdressen(suche, strasse, hausnr, ort, property));
        lastSuchWort = suche;
        lastProperty = property;
        log.debug("End filterList");
    }

    public void filterStandort(String strasse, Integer hausnr, String ort) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findInhaber(strasse, hausnr, ort));
        log.debug("End filterList");
    }

    public void filterStandort(String name, String strasse, Integer hausnr, String ort) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findAdressen(name, strasse, hausnr, ort));
        log.debug("End filterList");
    }

    public void filterBetreiber(String name, String strasse, Integer hausnr, String ort) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findBetreiber(name, strasse, hausnr, ort));
        log.debug("End filterList");
    }
}
