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

import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
/**
 * Ein TableModel für die Basis-Objektdaten bei der Betreiber/Standort-Suche.
 * @author David Klotz
 */
public class BasisObjektModel extends ListTableModel {
    private String secondColumn;
    private String abteilung;

    /**
     * Erzeugt ein TableModel für BasisObjekte. Der Parameter
     * secondColumn entscheidet, was in der zweiten Tabellen-Spalte
     * angezeigt wird.
     * @param secondColumn Entweder "Standort" oder "Betreiber"
     * @param abteilung Die Abteilung ("360.33" oder "360.34"), wenn die Objekte
     * nach ihr gefiltert werden sollen, sonst <code>null</code>.
     */
    public BasisObjektModel(String secondColumn, String abteilung) {
        super(new String[]{
                "Obj.ID",
                secondColumn,
                "Objektart",
                "Beschreibung"},
                false, true);

        this.secondColumn = secondColumn;
        this.abteilung = abteilung;
    }

    public void updateList() throws Exception {
    }

    /**
     * Liefert den Inhalt einer Zelle.
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     * @param objectAtRow Das Objekt in dieser Zeile
     * @param columnIndex Die Spalte der Tabelle
     */
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object tmp;

        BasisObjekt bo = (BasisObjekt) objectAtRow;
        switch(columnIndex) {
            case 0:
                tmp = bo.getObjektid();
                break;
            case 1:
                if ("Standort".equals(secondColumn)) {
                    tmp = bo.getBasisStandort() + " (" + bo.getBasisStandort().getEntgebid() + ")";
                } else if ("Betreiber".equals(secondColumn)) {
                    tmp = bo.getBasisBetreiber();
                } else {
                    tmp = secondColumn;
                }

                break;
            case 2:
                BasisObjektarten boa = bo.getBasisObjektarten();
                tmp = boa;

                break;
            case 3:
                tmp = bo.getBeschreibung();
                break;
            default:
                tmp = null;
        }

    if (tmp != bo.getBeschreibung() && bo.getInaktiv().booleanValue() != false  )
        {
                tmp = "<html><strike>" + tmp + "</strike></html>";
        }

        return tmp;
    }

    public boolean objectRemoved(Object objectAtRow) {
        BasisObjekt removedObjekt = (BasisObjekt) objectAtRow;
        boolean removed;

        if (removedObjekt.getObjektid() != null) {
            removed = BasisObjekt.removeBasisObjekt(removedObjekt);
        } else {
            removed = true;
        }

        return removed;
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public BasisObjekt getRow(int rowIndex) {
        return (BasisObjekt) getObjectAtRow(rowIndex);
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Betreiber-Id.
     * @param betreiberId Die Betreiber-Id
     */
    public void searchByBetreiber(BasisBetreiber betr) {
        setList(BasisObjekt.getObjekteByBetreiber(betr, abteilung));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(BasisStandort standort) {
        setList(BasisObjekt.getObjekteByStandort(standort, abteilung, null));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(BasisStandort standort, String abteilung) {
        setList(BasisObjekt.getObjekteByStandort(standort, abteilung, null));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(BasisStandort standort, String abteilung, Integer nichtartid) {
        setList(BasisObjekt.getObjekteByStandort(standort, abteilung, nichtartid));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(BasisStandort standort, Integer istartid) {
        setList(BasisObjekt.getObjekteByStandort(standort, istartid));
    }
}