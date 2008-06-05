/*
 * Datei:
 * $Id: AnhBwkModel.java,v 1.1 2008-06-05 11:38:40 u633d Exp $
 * 
 * Erstellt am 24.08.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/08/25 14:46:59  u633d
 * - zu viel ;)
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.ViewBwk;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Brennwertkessel-Fachdaten.
 * @author David Klotz
 */
public class AnhBwkModel extends ListTableModel {
	/**
	 * Erzeugt ein einfaches TableModel für 
	 * Brennwertkessel-Fachdaten.
	 */
	public AnhBwkModel() {
		super(new String[]{
				"Betreiber", 
				"Standort", 
				"Hersteller",
				"Brennmittel",
				"Leistung", 
				"Erfassung"
		}, 
		false);
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		ViewBwk fd = (ViewBwk) objectAtRow;
		Object tmp;
		String anrede;
		String hausnr;
		String hausnrzus;
		
		if (fd.getBetranrede()!= null){
			anrede = fd.getBetranrede()+ " ";
		}
		else {
			anrede = "";
		}
		
		if (fd.getHausnr()!= null){
			hausnr = fd.getHausnr().toString();
		}
		else {
			hausnr = "";
		}
		
		if (fd.getHausnrzus()!= null){
			hausnrzus = fd.getHausnrzus();
		}
		else {
			hausnrzus = "";
		}
		
		switch (columnIndex) {
		case 0:
			tmp = anrede + fd.getBetrname();
			break;
		case 1:
			tmp = fd.getStrasse()+" "+hausnr+hausnrzus;
			break;
		case 2:
			tmp = fd.getKHersteller();
			break;
		case 3:
			tmp = fd.getKBrennmittel(); 
			break;
		case 4:
			tmp = fd.getKLeistung();
			break;
		case 5:
			tmp = fd.getErfassung();
			break;

		default:
			tmp = "ERROR";
			break;
		}
		
		return tmp;
	}
	
	/* 
	 * Leer, da kein Updaten der Liste nötig/möglich.
	 */
	public void updateList() {
	}
}
