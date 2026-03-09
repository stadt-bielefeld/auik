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

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Ein TableModel für die Basis-Betreiberdaten.
 * @author David Klotz
 */
public class BasisInhaberModel extends ListTableModel<Inhaber> {
    private static final long serialVersionUID = -1943023265274962194L;
    private AuikLogger log = AuikLogger.getLogger();
    private static final ResourceBundle I18N =
        SettingsManager.getInstance().getI18nBundle();

    public BasisInhaberModel() {
        super(new String[]{
                I18N.getString("fields.id"),
                I18N.getString("editor.betreiber.company_name"),
                I18N.getString("editor.betreiber.first_name"),
                I18N.getString("fields.city"),
                I18N.getString("fields.street"),
                I18N.getString("fields.nr")}, false, true);
    }

    @Override
    public void updateList() {
        // Do nothing. Never called.
    }

    /**
     * Liefert den Inhalt der Zelle mit den gegebenen Koordinaten.
     * @param objectAtRow Das Objekt in dieser Zeile der Tabelle
     * @param columnIndex Die Spalte der Tabelle
     * @return Den Wert der Zelle oder null (falls die Zelle nicht existiert)
     */
    @Override
    public Object getColumnValue(Inhaber betr, int columnIndex) {
        Object tmp = null;

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
    public boolean objectRemoved(Inhaber removedBetreiber) {
        return Inhaber.delete(removedBetreiber);
    }

    public void filterAllList(String suche, String property) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findAdressen(suche, property));
        log.debug("End filterList");
    }

    public void filterAllList(String suche, String strasse, Integer hausnr, String ort, String property) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findAdressen(suche, strasse, hausnr, ort, property));
        log.debug("End filterList");
    }

    public void filterStandort(String strasse, Integer hausnr, String ort) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findInhaber(strasse, hausnr, ort));
        log.debug("End filterList");
    }

    public void filterBetreiber(String name, String strasse, Integer hausnr, String ort) {
        log.debug("Start filterList");
        setList(DatabaseQuery.findBetreiber(name, strasse, hausnr, ort));
        log.debug("End filterList");
    }
}
