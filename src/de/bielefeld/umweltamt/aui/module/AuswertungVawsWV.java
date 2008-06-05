/*
 * Datei:
 * $Id: AuswertungVawsWV.java,v 1.1 2008-06-05 11:38:32 u633d Exp $
 * 
 * Erstellt am 27.09.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
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

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFachdaten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsVerwaltungsverf;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.editors.VawsEditor;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein Modul um noch ausstehende Prüfungen anzeigen zu lassen.
 * @author David Klotz
 */
public class AuswertungVawsWV extends AbstractQueryModul {
	private WiedervorlageVVModel model;

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getBasisObjektFromFachdaten(Object)
	 */
	protected void editObject(int row) {
		if (row != -1) {
			VawsFachdaten fd = ((VawsVerwaltungsverf)model.getObjectAtRow(row)).getVawsFachdaten();
			
			VawsEditor editor = new VawsEditor(fd, frame, "Verwaltungsverfahren");
			
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
			model = new WiedervorlageVVModel();
		}
		return model;
	}
	
	public void updateListe() {
		SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
			protected void doNonUILogic() throws RuntimeException {
				((WiedervorlageVVModel)getTableModel()).updateList();
			}

			protected void doUIUpdateLogic() throws RuntimeException {
				getTableModel().fireTableDataChanged();
				frame.changeStatus(+ getTableModel().getRowCount() + " Objekte gefunden");
			}
		};
		worker.start();
	}
	
	public void show() {
		super.show();
		
		updateListe();
	}
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Wiedervorlage Verwaltungs - Verfahren";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	public String getCategory() {
		return "VAwS";
	}
}

class WiedervorlageVVModel extends ListTableModel {
	public WiedervorlageVVModel() {
		super(
				new String[]{
						"Behälter", 
						"Betreiber", 
						"Standort",
						"Wiedervorlage",
						"abgeschl.?"
				},
				false
		);
	}
	
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Object tmp;
		VawsVerwaltungsverf vf = (VawsVerwaltungsverf) objectAtRow;
		
		switch (columnIndex) {
		case 0:
			tmp = vf.getVawsFachdaten().getBehaelterId();
			break;
		case 1:
			tmp = vf.getVawsFachdaten().getBasisObjekt().getBasisBetreiber();
			break;
		case 2:
			tmp = vf.getVawsFachdaten().getBasisObjekt().getBasisStandort();
			break;
		case 3:
			tmp = AuikUtils.getStringFromDate(vf.getWiedervorlage());
			break;
		case 4:
			tmp = new Boolean(vf.getWvverwverf());
			break;

		default:
			tmp = "FEHLER!";
			break;
		}
		
		return tmp;
	}
	
	public Class getColumnClass(int columnIndex) {
		if (columnIndex == 4) {
			return Boolean.class;
		} else {
			return super.getColumnClass(columnIndex);
		}
	}
	
	public void updateList() {
		setList(VawsVerwaltungsverf.getAuswertung());
//		HibernateSessionFactory.closeSession();
//		fireTableDataChanged();
	}
}
