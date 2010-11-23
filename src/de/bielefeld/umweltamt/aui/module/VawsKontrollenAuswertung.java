/*
 * Datei:
 * $Id: VawsKontrollenAuswertung.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 * 
 * Erstellt am 27.09.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.2  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.1  2005/09/28 11:11:14  u633d
 * - Version vom 28.9.
 *
 *
 */
package de.bielefeld.umweltamt.aui.module;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFachdaten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsKontrollen;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.editors.VawsEditor;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein Modul um noch ausstehende Prüfungen anzeigen zu lassen.
 * @author David Klotz
 */
public class VawsKontrollenAuswertung extends AbstractQueryModul {
	private WiedervorlageSVModel model;

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#editObject(int)
	 */
	protected void editObject(int row) {
		if (row != -1) {
			VawsFachdaten fd = ((VawsKontrollen)model.getObjectAtRow(row)).getVawsFachdaten();
			
			VawsEditor editor = new VawsEditor(fd, frame, "Sachverständigenprüfung");
			
			editor.setVisible(true);
			
			if (editor.wasSaved()) {
				// Nach dem Bearbeiten die Liste updaten, 
				// damit unsere Änderungen auch angezeigt werden.
				updateListe();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
	 */
	public JPanel getQueryOptionsPanel() {
		return new JPanel();
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
	 */
	public ListTableModel getTableModel() {
		if (model == null) {
			model = new WiedervorlageSVModel();
		}
		return model;
	}
	
	public void updateListe() {
		SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable(10, 10, 15, 250, 250)) {
			protected void doNonUILogic() throws RuntimeException {
				((WiedervorlageSVModel)getTableModel()).updateList();
			}

			protected void doUIUpdateLogic() throws RuntimeException {
				getTableModel().fireTableDataChanged();
				frame.changeStatus(+ getTableModel().getRowCount() + " Objekte gefunden");
			}
		};
		worker.start();
	}
	
	private JTable getResultTable(int a, int b, int c, int d, int e) {
		
		JTable resultTable = getResultTable();
		
		resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		TableColumn column = null;

		for (int i = 0; i < 5; i++) {
			column = resultTable.getColumnModel().getColumn(i);

			if (i == 0) {
				column.setPreferredWidth(a);
			} else if (i == 1) {
				column.setPreferredWidth(b);
			} else if (i == 2) {
				column.setPreferredWidth(c);
			} else if (i == 3) {
				column.setPreferredWidth(d);
			} else if (i == 4) {
				column.setPreferredWidth(e);
			}
		}
		
		return resultTable;
	}

	
	public void show() {
		super.show();
		
		updateListe();
	}
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Wiedervorlage Sachverständigen - Prüfung";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	public String getCategory() {
		return "VAwS";
	}
}

class WiedervorlageSVModel extends ListTableModel {
	public WiedervorlageSVModel() {
		super(
				new String[]{
						"Behälter",
						"Nächste Prüfung", 
						"Anlagenart", 
						"Betreiber", 
						"Standort"
				},
				false
		);
	}
	
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Object tmp;
		VawsKontrollen vk = (VawsKontrollen) objectAtRow;
		
		switch (columnIndex) {
		case 0:
			tmp = vk.getVawsFachdaten().getBehaelterId();
			break;
		case 1:
			tmp = AuikUtils.getStringFromDate(vk.getNaechstepruefung());
			break;
		case 2:
			tmp = vk.getVawsFachdaten().getAnlagenart();
			break;
		case 3:
			tmp = vk.getVawsFachdaten().getBasisObjekt().getBasisBetreiber();
			break;
		case 4:
			tmp = vk.getVawsFachdaten().getBasisObjekt().getBasisStandort();
			break;

		default:
			tmp = "FEHLER!";
			break;
		}
		
		return tmp;
	}
	
	public void updateList() {
		setList(VawsKontrollen.getAuswertung());
//		HibernateSessionFactory.closeSession();
//		fireTableDataChanged();
	}
}
