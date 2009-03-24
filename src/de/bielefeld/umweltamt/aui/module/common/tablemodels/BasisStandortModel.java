package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import org.hibernate.HibernateException;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
/**
 * Ein TableModel für die Basis-Standortdaten.
 * @author David Klotz
 */
public class BasisStandortModel extends ListTableModel {
	private String lastStrasse = null;
	private int lastHausNr = -1;
	
	public BasisStandortModel() {
		super(new String[]{
				/*"St.ID",*/
				"Straße",
				"Hausnr.",
		"PLZ"}, true);
	}
	
	/**
	 * Aktualisiert die aktuell angezeigte Liste.
	 * Falls noch keine Suche durchgeführt wurde, werden die 
	 * ersten 30 Einträge angezeigt.
	 * @throws HibernateException
	 */
	public void updateList() {
		if (lastStrasse != null) {
			filterList(lastStrasse, lastHausNr);
		}
	}
	
	/**
	 * Liefert das Objekt aus einer bestimmten Zeile.
	 * @param rowIndex Die Zeile
	 * @return Das Objekt bei rowIndex
	 */
	public BasisStandort getRow(int rowIndex) {
		return (BasisStandort) getObjectAtRow(rowIndex);
	}
	
	/** 
	 * Filtert den Tabelleninhalt nach der Straße und der Hausnr.
	 * @param strasse Der Straßenname
	 * @param hausnr Die Hausnr (oder -1 falls alle Standorte in dieser Straßen gesucht werden sollen)
	 */
	public void filterList(String strasse, int hausnr) {
		setList(BasisStandort.findStandorte(strasse, hausnr));
		lastStrasse = strasse;
		lastHausNr = hausnr;
	}
	
	/**
	 * Liefert den Inhalt der Spalte mit den gegebenen Koordinaten. 
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(Object, int)
	 * @param objectAtRow Das Object in dieser Zeile
	 * @param columnIndex Die Spalte der Tabelle
	 * @return Den Wert der Zelle oder null (falls die Zelle nicht existiert) 
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Object value;
		BasisStandort bsta = (BasisStandort) objectAtRow;
		switch(columnIndex) {
			/*case 0:
			 value = bsta.getStandortid();
			 break;*/
			case 0:
				value = bsta.getStrasse();
				break;
			case 1:
				if (bsta.getHausnrzus() != null) {
					String tmp = bsta.getHausnr() + bsta.getHausnrzus();
					value = tmp;
				} else {
					value = bsta.getHausnr();
				}
				break;
			case 2:
				value = bsta.getPlz();
				break;
			default:
				value = null;
		}
		return value;
	}
}