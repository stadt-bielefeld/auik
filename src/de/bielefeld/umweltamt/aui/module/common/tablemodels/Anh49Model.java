/*
 * Datei:
 * $Id: Anh49Model.java,v 1.4 2010-01-20 12:55:20 u633d Exp $
 * 
 * Erstellt am 15.08.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/07/23 06:55:22  u633d
 * Anh49 Auswertung und Sielhautimport neu
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Anhang 49-Fachdaten.
 * @author David Klotz
 */
public class Anh49Model extends ListTableModel {
	public Anh49Model() {
		super(new String[]{
				"Betreiber", 
				"Standort", 
				"Wiedervorl.", 
				"Sonstiges Technik",
				"SachbearbeiterIn"
		}, 
		false);
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Anh49Fachdaten fd = (Anh49Fachdaten) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		case 0:
			tmp = fd.getBasisObjekt().getBasisBetreiber();
			break;
		case 1:
			tmp = fd.getBasisObjekt().getBasisStandort();
			break;
		case 2:
			tmp = AuikUtils.getStringFromDate(fd.getWiedervorlage());
			break;
		case 3:
			tmp = fd.getSonstigestechnik();
			break;
		case 4:
			tmp = fd.getSachbearbeiterIn();
			break;

		default:
			tmp = "ERROR";
			break;
		}
		if (fd.getBasisObjekt().getInaktiv() == true)
		{
			tmp = "<html><strike>" + tmp + "</strike></html>";	
		}
		return tmp;
	}
	
	/* 
	 * Leer, da kein Updaten der Liste nötig/möglich.
	 */
	public void updateList() {
	}
}
