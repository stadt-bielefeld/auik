/**
 * Copyright 2025, Stadt Bielefeld
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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class Anh49AbscheiderModel extends ListTableModel {
    private static final long serialVersionUID = 6154019963876247085L;
    private Anh49Fachdaten fachdaten;

    /**
     * Erzeugt ein neues Abscheider-TableModel. Dieses hat die Spalten
     * "Abscheider", "Von", "Lage" und "Bemerkung".
     */
    public Anh49AbscheiderModel() {
        super(new String[] {
                "Abscheider",
                "Von",
                "Lage",
                "Nenngröße",
                "Hersteller",
                "Bemerkung"},
            false, true);
    }

    /**
     * Setzt das Fachdatenobjekt, nach dessen Abscheider-Details gesucht
     * werden soll.
     * @param fachdaten Das Anhang49-Fachdatenobjekt
     */
    public void setFachdaten(Anh49Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
        updateList();
    }

    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Anh49Abscheiderdetails details = (Anh49Abscheiderdetails) objectAtRow;

        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = details.getAbscheidernr();
            break;
        case 1:
            tmp = details.getVon();
            break;
        case 2:
            tmp = details.getLage();
            break;
        case 3:
            tmp = details.getNenngroesse();
            break;
        case 4:
            tmp = details.getHersteller();
            break;
        case 5:
            tmp = details.getBemerkung();
            break;
        default:
            tmp = null;
        }

        return tmp;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Anh49Abscheiderdetails removedAbsch = (Anh49Abscheiderdetails) objectAtRow;
        if (removedAbsch.getId() != null) {
            return Anh49Abscheiderdetails.delete(removedAbsch);
        }
        return true;
    }

    @Override
    public void updateList() {
        if (fachdaten != null) {
            setList(DatabaseQuery.getAbscheiderDetails(fachdaten));
        }
        fireTableDataChanged();
    }

    public Anh49Abscheiderdetails getRow(int rowIndex) {
        return (Anh49Abscheiderdetails) getObjectAtRow(rowIndex);
    }
}
