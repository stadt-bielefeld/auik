package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh53Fachdaten;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Anhang 53-Fachdaten.
 * @author Gerd Genuit
 */
public class Anh53Model extends ListTableModel {
	public Anh53Model() {
		super(new String[]{
				"Betreiber", 
				"Standort", 
				"Größe",
				"Beschreibung"
		}, 
		false);
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Anh53Fachdaten fd = (Anh53Fachdaten) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		case 0:
			tmp = fd.getBasisObjekt().getBasisBetreiber();
			break;
		case 1:
			tmp = fd.getBasisObjekt().getBasisStandort();
			break;
		case 2:
			if (fd.getBasisObjekt().getBasisObjektarten().getObjektartid()==17)
				tmp = "kleiner 3000";
			else
				tmp = "größer 3000";
			break;
		case 3:
			tmp = fd.getBasisObjekt().getBeschreibung();
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
