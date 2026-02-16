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

import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Ein TableModel für die Basis-Standortdaten.
 *
 * @author David Klotz
 */
public class BasisLageModel extends ListTableModel<Adresse> {
	private static final long serialVersionUID = 3532697905957103920L;
	private String lastStrasse = null;
	private String lastOrt = null;
	private int lastHausNr = -1;
    private static final AuikLogger log = AuikLogger.getLogger();

	public BasisLageModel()
	{
		super(new String[] {
				"St.ID",
				"Straße",
				"Hausnr.",
				"PLZ",
				"Ort",
				"Entw.-Gebiet",
				"Übersch.-Gebiet",
				"Wasserschutzgebiet",
				"AwSV-Gebiet" }, true, true);
        log.debug("Creating new BasisLagemodel");
	}


	/**
	 * Aktualisiert die aktuell angezeigte Liste.
	 * Falls noch keine Suche durchgeführt wurde, werden die
	 * ersten 30 Einträge angezeigt.
	 */
	@Override
	public void updateList()
	{
		if (lastStrasse != null)
		{
			filterList(lastStrasse, lastHausNr, lastOrt);
		}
	}

	/**
	 * Liefert das Objekt aus einer bestimmten Zeile.
	 *
	 * @param rowIndex
	 *            Die Zeile
	 * @return Das Objekt bei rowIndex
	 */
	public Adresse getRow(int rowIndex)
	{
		return (Adresse) getObjectAtRow(rowIndex);
	}

	/**
	 * Filtert den Tabelleninhalt nach der Straße und der Hausnr.
	 *
	 * @param strasse
	 *            Der Straßenname
	 * @param hausnr
	 *            Die Hausnr (oder -1 falls alle Standorte in dieser Straßen
	 *            gesucht werden sollen)
	 */
	public void filterList(String strasse, int hausnr, String ort)
	{
        log.debug("Fetching Adresses");
        //Fetch all BasisAdresse Objects
        List<Adresse> list = DatabaseQuery.findStandorte(strasse, hausnr, ort);
        log.debug("Fetched " + list.size() + " Objects");


        setList(list);
        log.debug("Created list");
		lastOrt = ort;
		lastStrasse = strasse;
		lastHausNr = hausnr;

	}

	/**
	 * Liefert den Inhalt der Spalte mit den gegebenen Koordinaten.
	 *
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(Object,
	 *      int)
	 * @param objectAtRow Das Object in dieser Zeile
	 * @param columnIndex Die Spalte der Tabelle
	 * @return Den Wert der Zelle oder null (falls die Zelle nicht existiert)
	 */
	@Override
	public Object getColumnValue(Adresse bsta, int columnIndex) {
		Object value = null;
		if (bsta != null) {
			switch (columnIndex) {

			case 0:
				value = bsta.getId();
				break;

			case 1:
				value = bsta.getStrasse();
				break;
			case 2:
				if (bsta.getHausnrzus() != null) {
					String tmp = bsta.getHausnr() + bsta.getHausnrzus();
					value = tmp;
				} else {
					value = bsta.getHausnr();
				}
				break;
			case 3:
				value = bsta.getPlz();
				break;
			case 4:
				value = bsta.getOrt();
				break;
			case 5:
				if (bsta.getEntgebid() != null) {
					value = bsta.getEntgebid();
				} else
					value = "";
				break;
			case 6:
				if (bsta.isUeberschgeb() == true) {
					value = Boolean.TRUE;
				} else {
					value = Boolean.FALSE;
				}
				break;
			case 7:
				if (bsta.getStandortgghwsg() != null) {
					Integer sggh = bsta.getStandortgghwsg().getId();
					if (sggh.equals(1)) {
						value = new String("Zone I");
					} else if (sggh.equals(2)) {
						value = new String("Zone II");
					} else if (sggh.equals(3)) {
						value = new String("Zone III/III A");
					} else if (sggh.equals(4)) {
						value = new String("Zone III B");
					}
				} else {
					value = new String("");
				}
				break;
			case 8:
				if (bsta.getWassereinzugsgebiet() != null) {
					value = bsta.getWassereinzugsgebiet();
				}
				break;
			default:
				value = null;
			}
		}
		return value;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		switch (columnIndex)
		{
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			case 5:
				return String.class;
			case 6:
				return Boolean.class;
			case 7:
				return String.class;
			case 8:
				return String.class;
			default:
				return null;
		}
	}
}
