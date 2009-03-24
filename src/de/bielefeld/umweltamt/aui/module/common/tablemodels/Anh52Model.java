package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Anhang 52-Fachdaten.
 * @author Gerd Genuit
 */
public class Anh52Model extends ListTableModel {
	public Anh52Model() {
		super(new String[]{
				"Betreiber", 
				"Standort", 
				"Druckverfahren", 
				"Bemerkungen"
		}, 
		false);
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Anh52Fachdaten fd = (Anh52Fachdaten) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		case 0:
			tmp = fd.getBasisObjekt().getBasisBetreiber();
			break;
		case 1:
			tmp = fd.getBasisObjekt().getBasisStandort();
			break;
		case 2:
			tmp = fd.getBemerkungen();
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
