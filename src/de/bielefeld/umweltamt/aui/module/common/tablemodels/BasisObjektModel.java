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

import java.util.Set;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;

/**
 * Ein TableModel für die Basis-Objektdaten bei der Betreiber/Standort-Suche.
 * @author David Klotz
 */
public class BasisObjektModel extends ListTableModel {
    private static final long serialVersionUID = -4928147488267472682L;
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

    @Override
    public void updateList() throws Exception {
    }

    /**
     * Liefert den Inhalt einer Zelle.
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     * @param objectAtRow Das Objekt in dieser Zeile
     * @param columnIndex Die Spalte der Tabelle
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object tmp;

        Objekt bo = (Objekt) objectAtRow;
        switch(columnIndex) {
            case 0:
                tmp = bo.getId();
                break;
            case 1:
                if ("Standort".equals(secondColumn)) {
                    tmp = DatabaseQuery.getStandortString(bo.getStandortid());
                } else if ("Betreiber".equals(secondColumn)) {
                    tmp = bo.getBetreiberid();
                } else {
                    tmp = secondColumn;
                }
                break;
			case 2:
				if (bo.getObjektarten().getAbteilung() == "AwSV") {
					tmp = bo.getObjektarten();
				} else if (bo.getAnfallstelles().size() > 0) {
					Set<Anfallstelle> list = bo.getAnfallstelles();
					Anfallstelle anfallstelle = list.iterator().next();
					if (!(anfallstelle.getAnhangId() == null) && !anfallstelle.getAnhangId().equals("99")) {
						tmp = "Anfallstelle (Anh " + anfallstelle.getAnhangId() + ")";
					} else if (anfallstelle.getAbwaBeschaffOpt() != null && anfallstelle.getAbwaBeschaffOpt() == 3){
						tmp = "Anfallstelle (" + "Niederschlagswasser" + ")";
					} else if (anfallstelle.getAnhangId().equals("99")){
						tmp = "Anfallstelle (" + anfallstelle.getAnlagenart() + ")";
					} else {
						tmp = "Anfallstelle";
					}
				} else if (bo.getSonderbauwerks().size() > 0) {
					Sonderbauwerk sonderbauwerk = bo.getSonderbauwerks().iterator().next();
					if (sonderbauwerk.getTypOpt() == 1) {
						tmp = "Sonderbauwerk (RRB)";
						}
					else if (sonderbauwerk.getTypOpt() == 2) {
						tmp = "Sonderbauwerk (RKB)";
						}
					else if (sonderbauwerk.getTypOpt() == 3) {
						tmp = "Sonderbauwerk (RBF)";
						}
					else if (sonderbauwerk.getTypOpt() == 4) {
						tmp = "Sonderbauwerk (BF)";
						}
					else if (sonderbauwerk.getTypOpt() == 5) {
						tmp = "Sonderbauwerk (RÜT)";
						}
					else if (sonderbauwerk.getTypOpt() == 6) {
						tmp = "Sonderbauwerk (RÜM)";
						}
					else if (sonderbauwerk.getTypOpt() == 7) {
						tmp = "Sonderbauwerk (RÜB)";
						}
					else if (sonderbauwerk.getTypOpt() == 8) {
						tmp = "Sonderbauwerk (SK)";
						}
					else if (sonderbauwerk.getTypOpt() == 9) {
						tmp = "Sonderbauwerk (RST)";
						}
					else {
						tmp = "Sonderbauwerk (AL)";
						}
				} else {
					tmp = bo.getObjektarten();
				}
                break;
            case 3:
                tmp = bo.getBeschreibung();
                break;
            default:
                tmp = null;
        }

        if (tmp != bo.getBeschreibung() && bo.isInaktiv()) {
            tmp = StringUtils.setStrike(tmp.toString());
        }

        return tmp;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Objekt removedObjekt = (Objekt) objectAtRow;
        boolean removed;

        if (removedObjekt.getId() != null) {
            removed = Objekt.delete(removedObjekt);
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
    public Objekt getRow(int rowIndex) {
        return (Objekt) getObjectAtRow(rowIndex);
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Betreiber-Id.
     * @param betreiberId Die Betreiber-Id
     */
    public void searchByInhaber(Inhaber betr) {
        setList(DatabaseQuery.getObjekteByInhaber(betr, abteilung, null, false));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Betreiber-Id.
     * @param betreiberId Die Betreiber-Id
     */
    public void searchByInhaber(Inhaber betr, Integer istartid) {
        setList(DatabaseQuery.getObjekteByInhaber(betr, null, istartid, true));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Betreiber-Id.
     * @param betreiberId Die Betreiber-Id
     */
    public void searchByInhaber(Inhaber betr, String abteilung) {
        setList(DatabaseQuery.getObjekteByInhaber(betr, abteilung, null, false));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(Adresse adr, String abteilung) {
        setList(DatabaseQuery.getObjekteByStandort(
        		adr, abteilung, null, null));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(Objekt obj) {
        setList(DatabaseQuery.getObjekteByStandort(obj));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(Adresse adr) {
        setList(DatabaseQuery.getObjekteByStandort(
        		adr, null, null, null));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(
        Adresse adr, String abteilung, Integer nichtartid) {
        setList(DatabaseQuery.getObjekteByStandort(
        		adr, abteilung, nichtartid, false));
    }

    /**
     * Durchsucht den Tabelleninhalt nach der Standort-Id.
     * @param standortId Die Standort-Id
     */
    public void searchByStandort(Adresse adr, Integer istartid) {
        setList(DatabaseQuery.getObjekteByStandort(
        		adr, null, istartid, true));
    }

    /**
     * Remove a BasisObjekt from the model
     * @param objekt
     */
    public void removeFromList(Objekt objekt) {
        this.getList().remove(objekt);
    }
}
