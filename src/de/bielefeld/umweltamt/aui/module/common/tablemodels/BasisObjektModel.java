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
					tmp = bo.getBasisStandort();
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