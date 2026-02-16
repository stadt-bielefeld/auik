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
package de.bielefeld.umweltamt.aui.module.common.tablemodels.selectable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Sielhaut;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Table model for Sielhaut objects adding a boolean to use for checkbox columns.
 *
 * @author Alexander Woestmann<awoestmann@intevation.de>
 */
public class SelectableSielhautModel extends ListTableModel<Object[]> {

    private static final long serialVersionUID = 1L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private static final int ID_INDEX = 1;

    public SelectableSielhautModel () {
        super(new String[] {"", "Bezeichnung", "Lage", "R", "F", "N", "I"}, false);
    }

    @Override
    public void setValueAt(Object val, int row, int col) {
        if (!rowExists(row) || col > this.getColumnCount()) {
            return;
        }
        Object[] rowObj = (Object[]) getObjectAtRow(row);
        rowObj[col] = val;
        fireTableDataChanged();
    }

    @Override
    public Object getColumnValue(Object[] obj, int columnIndex) {
        Object tmp;

        switch (columnIndex) {
            case 0:
                tmp = obj[0];
                break;
            case 1:
                tmp = obj[2];
                break;
            case 2:
                tmp = obj[3];
                break;
            case 3:
                if (obj[4] == null) {
                    tmp = Boolean.valueOf(false);
                } else {
                    tmp = Boolean.valueOf((Boolean) obj[4]);
                }
                break;
            case 4:
                if (obj[5] == null) {
                    tmp = Boolean.valueOf(false);
                } else {
                    tmp = Boolean.valueOf((Boolean) obj[5]);
                }
                break;
            case 5:
                if (obj[6] == null) {
                    tmp = Boolean.valueOf(false);
                } else {
                    tmp = Boolean.valueOf((Boolean) obj[6]);
                }
                break;
            case 6:
                if (obj[7] == null) {
                    tmp = Boolean.valueOf(false);
                } else {
                    tmp = Boolean.valueOf((Boolean) obj[7]);
                }
                break;
            default:
                tmp = "FEHLER!";
                break;
        }
        return tmp;
    }

    public int getSelectedCount() {
        return getSelected().length;
    }

    public Sielhaut getModelFromRow(Object[] row) {
        Integer id = (Integer) row[ID_INDEX];
        if (id == null) {
            throw new InvalidParameterException("Keine Sielhautpunkt ID gefunden");
        }
        Sielhaut sielhaut = Sielhaut.findById(id);
        if (sielhaut == null) {
            throw new InvalidParameterException(
                String.format("Sielhautpunkt %d nicht gefunden", id));
        }
        return sielhaut;
    }

    public Object[] getSelected() {
        List<Object> result = new ArrayList<>();
        List<Object[]> rows = (List<Object[]>) this.getList();
        rows.forEach(row -> {
            Boolean selected = (Boolean) row[0];
            if (selected) {
                result.add(row);
            }
        });
        return result.toArray();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0 || columnIndex > 2) {
            return Boolean.class;
        } else {
            return super.getColumnClass(columnIndex);
        }
    }

    @Override
    public void updateList() {
    }

    public void filterList(String suche) {
        //Get Sielhaut objects
        Object[] array = DatabaseQuery.findActiveSielhaut(suche);
        //Prepend selectable boolean
        List<Object[]> list = new ArrayList<>();
        for(Object rowObj: array) {
            Object[] row = (Object[]) rowObj;
            Object[] listEntry = new Object[row.length + 1];
            listEntry[0] = Boolean.valueOf(false);
            System.arraycopy(rowObj, 0, listEntry, 1, row.length - 1);
            list.add(listEntry);
        }
        setList(list);
        log.debug("Suche nach '" + suche + "' (" + getList().size()
            + " Ergebnisse)");
    }
}
