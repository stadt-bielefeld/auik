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
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein TableModel für eine Tabelle mit Probenahmen zu einem Probepunkt.
 * @author David Klotz
 */
public class ProbenahmenModel extends ListTableModel {
    private static final long serialVersionUID = 5732041657532396538L;
    private Messstelle probepkt = null;
    private String secondColumn = null;

    public ProbenahmenModel(String secondColumn) {
        super(new String[]{
                "Kennnummer",
                secondColumn,
                "Datum",
                "Uhrzeit",
                "Status",
                "Firma",
                "Bemerkung"}, false, true);
        this.secondColumn = secondColumn;
    }

    public ProbenahmenModel() {
        super(new String[]{
                "Kennnummer",
                "Wochentag",
                "Datum",
                "Uhrzeit",
                "Status",
                "Bemerkung"}, false, true);
    }

    public void setProbepunkt(Messstelle probepkt) {
        this.probepkt = probepkt;
    }

    @Override
    public void updateList() {
        if (probepkt != null) {
            setList(DatabaseQuery.findProbenahme(probepkt));

            fireTableDataChanged();
        }
    }

    public void findByProperty(String property, String suche) {
        setList(DatabaseQuery.findProbenahme(property, suche));
    }

    public void findByKA(Probeart art, Klaeranlage ka) {
        setList(DatabaseQuery.findProbenahme(art, ka));
    }

    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;
        Probenahme probe = (Probenahme) objectAtRow;
        if (secondColumn == null) {
            switch(columnIndex) {
                case 0:
                    value = probe.getKennummer();
                    break;
                case 1:
                    value = AuikUtils.getDayOfWeekFromDate(probe.getDatumDerEntnahme());
                    break;
                case 2:
                    value = AuikUtils.getStringFromDate(probe.getDatumDerEntnahme());
                    break;
                case 3:
                    value = probe.getUhrzeitbeginn() + " - " + probe.getUhrzeitende();
                    break;
                case 4:
                	if (probe.getStatus() != null)
                		value = probe.getStatus().getBezeichnung();
                	else
                		value = "";
                    break;
                case 5:
                    value = probe.getBemerkung();
                    break;
                default:
                    value = null;
            }
        } else {
            switch(columnIndex) {
                case 0:
                    value = probe.getKennummer();
                    break;
                case 1:
                    if (secondColumn.equals("Art")) {
                        value = probe.getMessstelle().getProbeart();
                    } else if (secondColumn.equals("Pkt-ID")) {
                        value = probe.getMessstelle().getId();
                    } else {
                        value = "";
                    }
                    break;
                case 2:
                    value = AuikUtils.getStringFromDate(probe.getDatumDerEntnahme());
                    break;
                case 3:
                    value = probe.getUhrzeitbeginn() + " - " + probe.getUhrzeitende();
                    break;
                case 4:
                	if (probe.getStatus() != null)
                		value = probe.getStatus().getBezeichnung();
                	else
                		value = "";
                    break;
                case 5:
                    value = probe.getMessstelle().getObjekt()
                        .getAdresseByBetreiberid();
                    break;
                case 6:
                    value = probe.getBemerkung();
                    break;
                default:
                    value = null;
            }
        }
        return value;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        Probenahme removedProbe = (Probenahme) objectAtRow;
        boolean removed;

        if (removedProbe.getKennummer() != null) {
            removed = removedProbe.delete();
        } else {
            removed = true;
        }

        return removed;
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex oder <code>null</code>, falls die Zeile nicht existiert
     */
    public Probenahme getRow(int rowIndex) {
        return (Probenahme) getObjectAtRow(rowIndex);
    }
}
