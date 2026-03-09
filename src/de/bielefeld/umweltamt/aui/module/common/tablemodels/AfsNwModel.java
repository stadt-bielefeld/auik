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

/*
 * Datei:
 * $Id: ProbenehmerModel.java,v 1.1.2.1 2010-11-23 10:25:58 u633d Exp $
 *
 * Erstellt am 03.05.2006 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2010/01/20 12:55:00  u633d
 * Auswertungen order by inaktiv
 *
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.2  2006/05/23 05:29:42  u633d
 * Objektchronologie für alle Objekte verfügbar gemacht
 *
 * Revision 1.1  2006/05/03 09:01:54  u633d
 * Anhang 40 und 56 ergänzt
 *
 * Revision 1.1  2006/05/03 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.AfsNiederschlagswasser;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Entwaesserungsgrundstueck;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Probenehmereinsaetze.
 * @author Gerd Genuit
 */
public class AfsNwModel extends ListTableModel<AfsNiederschlagswasser> {
	private static final long serialVersionUID = 8683461766128779141L;
	private Anfallstelle anfallstelle;
	private Entwaesserungsgrundstueck grundstueck;

	public AfsNwModel() {
		super(new String[] { "Anfallstelle/Grundstück", "Befestigte Fläche", "Abflussmenge", "Herkunftsbereich" },
				false);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue
	 * (java.lang.Object, int)
	 */
	@Override
	public Object getColumnValue(AfsNiederschlagswasser fd, int columnIndex) {
		Object tmp;

		switch (columnIndex) {
		case 0:
			if (fd.getAnfallstelle() != null)
				tmp = fd.getAnfallstelle().getAnlagenart();
			else if (fd.getEntwaesserungsgrundstueck() != null)
				tmp = fd.getEntwaesserungsgrundstueck().getAdrNr();
			else
				tmp = "";
			break;
		case 1:
			tmp = fd.getBefFlaeche();
			break;
		case 2:
			tmp = fd.getAbflussmenge();
			break;
		case 3:
			tmp = fd.getNwHerBereichOpt();
			break;
		default:
			tmp = "ERROR";
			break;
		}
		return tmp;
	}

	public void setAnfallstelle(Anfallstelle anfallstelle) {
		this.anfallstelle = anfallstelle;
	}

	public void setEntwaesserungsgrundstueck(Entwaesserungsgrundstueck grundstueck) {
		this.grundstueck = grundstueck;
	}

	/*
	 * Leer, da kein Updaten der Liste nötig/möglich.
	 */
	@Override
	public void updateList() {
		if (anfallstelle != null) {
			setList(DatabaseQuery.findAfsNw(anfallstelle));

			fireTableDataChanged();
		}

		if (grundstueck != null) {
			setList(DatabaseQuery.findAfsNw(grundstueck));

			fireTableDataChanged();
		}
	}

	/**
	 * Liefert das Objekt aus einer bestimmten Zeile.
	 * @param rowIndex Die Zeile
	 * @return Das Objekt bei rowIndex oder <code>null</code>, falls die Zeile nicht
	 *         existiert
	 */
	public AfsNiederschlagswasser getRow(int rowIndex) {
		return (AfsNiederschlagswasser) getObjectAtRow(rowIndex);
	}
}
