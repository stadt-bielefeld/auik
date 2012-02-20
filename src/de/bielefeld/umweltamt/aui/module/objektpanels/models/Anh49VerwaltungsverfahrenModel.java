/**
 * Copyright 2005-2042, Stadt Bielefeld
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
package de.bielefeld.umweltamt.aui.module.objektpanels.models;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Verwaltungsverfahren;
import de.bielefeld.umweltamt.aui.utils.FormattedDate;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein TableModel für eine Tabelle mit Abscheider-Ortsterminen.
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class Anh49VerwaltungsverfahrenModel extends EditableListTableModel {
    private static final long serialVersionUID = -7162535797673486082L;

    /** The underlying data */
    private Anh49Fachdaten fachdaten;

    /**
     * The underlying model for the table in the panel with the colunms "Datum",
     * "Maßnahme", "SachbearbeiterIn", "Wiedervorlage" and "abgeschlossen".
     */
    public Anh49VerwaltungsverfahrenModel() {
        super(new String[] {"Datum", "Maßnahme", "SachbearbeiterIn",
                "Wiedervorlage", "abgeschlossen"}, false, true);
    }

    /**
     * Set the data object and update the list
     * @param fachdaten The data object
     */
    public void setFachdaten(Anh49Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
        updateList();
    }

    /**
     * Tell the model which column is of which class type.<br>
     * (Until we find a way to handle editing of date fields properly, use
     * String instead of Date.)
     * @param columnIndex The index of the requested column
     */
    /* Well, well, well, this got lost in the copy-pasting, didn't it... */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class; // Date.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class; // Date.class;
            case 4: return Boolean.class;
            default: return null;
        }
    }

    /**
     * Get the value of a cell
     * @param objectAtRow The data object
     * @param columnIndex The requested column
     * @return The value of the cell
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Anh49Verwaltungsverfahren verwaltungsverfahren =
            (Anh49Verwaltungsverfahren) objectAtRow;

        String result = null;
        switch (columnIndex) {
            case 0:
                result = new FormattedDate(verwaltungsverfahren.getDatum()).toString();
                break;
            case 1:
                result = verwaltungsverfahren.getMassnahme();
                break;
            case 2:
                result = verwaltungsverfahren.getSachbearbeiterIn();
                break;
            case 3:
                result = new FormattedDate(verwaltungsverfahren.getWiedervorlage()).toString();
                break;
            case 4:
                return verwaltungsverfahren.isAbgeschlossen();
            default:
                return null;
        }

        if (!(verwaltungsverfahren.is_enabled())) {
            result = this.setStrike(result);
        }

        return result;
    }

    /**
     * Little helper method to set a strike through the text via HTML.<br>
     * TODO: This wants to move to a util class
     * @param text
     * @return String The text with HTML formatting for a strike
     */
    private String setStrike(String text) {
        return ("<html><strike>" + text + "</strike></html>");
    }

    /**
     * When a row is removed in the view, remove it from the model/database
     * @param The row which was removed
     * @return If the data was removed
     */
    @Override
    public boolean objectRemoved(Object objectAtRow) {
        return Anh49Verwaltungsverfahren.removeVerwaltungsverfahren(
            (Anh49Verwaltungsverfahren) objectAtRow);
    }

    /** Update the table and fire a changed event. */
    @Override
    public void updateList() {
        if (fachdaten != null) {
            setList(Anh49Verwaltungsverfahren
                .getVerwaltungsverfahren(fachdaten));
        }
        fireTableDataChanged();
    }

    /** Edit a "Verwaltungsverfahren" */
    @Override
    public void editObject(
        Object objectAtRow, int columnIndex, Object newValue) {
        Anh49Verwaltungsverfahren verwaltungsverfahren =
            (Anh49Verwaltungsverfahren) objectAtRow;

        FormattedDate fDate = null;
        boolean success = false;

        switch (columnIndex) {
            case 0:
                fDate = new FormattedDate();
                success = fDate.setDate((String)newValue);
                if (success) {
                    verwaltungsverfahren.setDatum(fDate);
                } else {
                    GUIManager.getInstance().changeStatus(
                        "Bitte geben Sie das Datum in der "
                            + "Form TT.MM.JJJJ ein!", HauptFrame.ERROR_COLOR);
                }
                break;
            case 1:
                verwaltungsverfahren.setMassnahme((String) newValue);
                break;
            case 2:
                verwaltungsverfahren.setSachbearbeiterIn((String) newValue);
                break;
            case 3:
                fDate = new FormattedDate();
                success = fDate.setDate((String)newValue);
                if (success) {
                    verwaltungsverfahren.setWiedervorlage(fDate);
                } else {
                    GUIManager.getInstance().changeStatus(
                        "Bitte geben Sie das Datum in der "
                            + "Form TT.MM.JJJJ ein!", HauptFrame.ERROR_COLOR);
                }
                break;
            case 4:
                verwaltungsverfahren.setAbgeschlossen((Boolean) newValue);
                break;
            default:
                break;
        }
    }

    /**
     * Create a new "Verwaltungsverfahren"
     * @return The new "Verwaltungsverfahren" object
     */
    @Override
    public Object newObject() {
        Anh49Verwaltungsverfahren verwaltungsverfahren =
            new Anh49Verwaltungsverfahren();
        verwaltungsverfahren.setAnh49Fachdaten(fachdaten);
        verwaltungsverfahren.setAbgeschlossen(false);
        return verwaltungsverfahren;
    }

    /**
     * Get the "Verwaltungsverfahren" at row rowIndex
     * @param rowIndex The index of the row
     * @return The "Verwaltungsverfahren" at the given row index
     */
    public Anh49Verwaltungsverfahren getRow(int rowIndex) {
        return (Anh49Verwaltungsverfahren) getObjectAtRow(rowIndex);
    }
}