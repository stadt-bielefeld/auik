/*
 * Datei:
 * $Id: AbstractQueryModul.java,v 1.1 2008-06-05 11:38:41 u633d Exp $
 * 
 * Erstellt am 28.07.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/10/13 13:00:27  u633d
 * Version vom 13.10.
 *
 * Revision 1.4  2005/09/28 11:11:14  u633d
 * - Version vom 28.9.
 *
 * Revision 1.3  2005/09/14 11:25:38  u633d
 * - Version vom 14.9.
 *
 * Revision 1.2  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 * Revision 1.1  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Eine Grundlage für Module mit verschiedenen 
 * Auswertungs-Abfragen.
 * @author David Klotz
 */
public abstract class AbstractQueryModul extends AbstractModul {
//	private JSplitPane contentSplit;
	private JScrollPane tableScroller;
	private JTable resultTable;
	
	private Action objektEditAction;
	private Action saveAction;
	private JPopupMenu resultPopup;
	
	/**
	 * Liefert die Kategorie des Moduls. 
	 * Falls eine andere Kategorie als "Auswertung" gewünscht
	 * ist, muss diese Methode in implementierenden Klassen
	 * überschrieben werden.
	 * @return "Auswertung"
	 */
	public String getCategory() {
		return "Auswertung";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	public JPanel getPanel() {
		if (panel == null) {
			FormLayout layout = new FormLayout(
					"100dlu:g", 
					"pref, 3dlu, f:150dlu:grow"
			);
			PanelBuilder builder = new PanelBuilder(layout);
			builder.setDefaultDialogBorder();
			CellConstraints cc = new CellConstraints();
			
			builder.add(getQueryOptionsPanel(),	cc.xy(1,1));
			builder.add(getTableScroller(),		cc.xy(1,3));
			
			panel = builder.getPanel();
		}
		
		return panel;
	}

	/**
	 * @return Ein Panel, in dem Optionen für die Abfrage festgelegt werden können.
	 */
	public abstract JPanel getQueryOptionsPanel();
	
	/**
	 * @return Ein TableModel für die Ergebnis-Tabelle.
	 */
	public abstract ListTableModel getTableModel();
	
	/**
	 * Liefert das BasisObjekt zu einem Fachdaten-Objekt.
	 * @param objectAtRow Das Fachdaten-Objekt.
	 * @return Das zugehörige BasisObjekt (oder <code>null</code>, falls keins existiert).
	 */
	protected BasisObjekt getBasisObjektFromFachdaten(Object fachdaten) {
		BasisObjekt tmp;
		
		// Die "getBasisObjekt" Methode des jeweiligen
		// Fachdaten-Objekts wird jetzt, unabhängig von
		// seiner Klasse, mit Hilfe der Reflection-Methoden
		// nach ihrem Namen gesucht. Sollte keine Methode
		// diesen Namens existieren, wird null zurück geliefert.
		try {
			Method getBO = fachdaten.getClass().getMethod("getBasisObjekt", null);
			tmp = (BasisObjekt) getBO.invoke(fachdaten, null);
		} catch (Exception e) {
			//e.printStackTrace();
			tmp = null;
		}
		
//		if (fachdaten instanceof Anh50Fachdaten) {
//			tmp = ((Anh50Fachdaten) fachdaten).getBasisObjekt();
//		} else if (fachdaten instanceof Anh49Fachdaten) {
//			tmp = ((Anh49Fachdaten) fachdaten).getBasisObjekt();
//		} else {
//			tmp = null;
//		}
		
		return tmp;
	}
	
	/**
	 * @return Eine Tabelle für die Ergebnisse der Abfrage.
	 */
	protected JTable getResultTable() {
		if (resultTable == null) {
			resultTable = new JTable(getTableModel());
			
			resultTable.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = resultTable.rowAtPoint(origin);
						editObject(row);
					}
				}
				
				public void mousePressed(MouseEvent e) {
					showResultPopup(e);
				}
				
				public void mouseReleased(MouseEvent e) {
					showResultPopup(e);
				}
			});
			
			resultTable.getInputMap().put((KeyStroke)getObjektEditAction().getValue(Action.ACCELERATOR_KEY), getObjektEditAction().getValue(Action.NAME));
			resultTable.getActionMap().put(getObjektEditAction().getValue(Action.NAME), getObjektEditAction());
		}
		return resultTable;
	}
	
	/**
	 * @return Das ScrollPane für die Ergebnis-Tabelle.
	 */
	private JScrollPane getTableScroller() {
		if (tableScroller == null) {
			tableScroller = new JScrollPane(getResultTable());
		}
		return tableScroller;
	}
	
	/**
	 * @return Eine Action, um editObject() aufzurufen.
	 */
	protected Action getObjektEditAction() {
		if (objektEditAction == null) {
			objektEditAction = new AbstractAction("Bearbeiten") {
				public void actionPerformed(ActionEvent e) {
					int row = getResultTable().getSelectedRow();
					editObject(row);
				}
			};
			objektEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
			objektEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
		}
		
		return objektEditAction;
	}
	
	/**
	 * @return Eine Action, um saveTabelle() aufzurufen.
	 */
	protected Action getSaveAction() {
		if (saveAction == null) {
			saveAction = new AbstractAction("Tabelle exportieren") {
				public void actionPerformed(ActionEvent e) {
					AuikUtils.saveTabelle(getResultTable(), frame);
				}
			};
			saveAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_E));
			saveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK, false));
		}
		
		return saveAction;
	}
	
	/**
	 * Zeigt das Kontextmenü an, wenn entsprechende Events auftreten.
	 * @param e Das auslösende MouseEvent.
	 */
	private void showResultPopup(MouseEvent e) {
		if (resultPopup == null) {
			resultPopup = new JPopupMenu("Objekt");
			JMenuItem bearbItem = new JMenuItem(getObjektEditAction());
			JMenuItem saveItem = new JMenuItem(getSaveAction());
			resultPopup.add(bearbItem);
			resultPopup.add(saveItem);
		}
		
		if (e.isPopupTrigger()) {
			Point origin = e.getPoint();
			int row = getResultTable().rowAtPoint(origin);
			
			if (row != -1) {
				getResultTable().setRowSelectionInterval(row, row);
				resultPopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	/**
	 * Schaltet zum "Objekt Bearbeiten"-Modul um, wenn zu
	 * einem Objekt in der Ergebnis-Tabelle ein BasisObjekt
	 * vorhanden ist.
	 * @param row Die Zeile der Tabelle.
	 */
	protected void editObject(int row) {
		if (row != -1) {
			BasisObjekt obj = getBasisObjektFromFachdaten(getTableModel().getObjectAtRow(row));
			if (obj != null) {
//				AUIKataster.debugOutput("Bearbeite BO: " + obj, "AQM");
				manager.getSettingsManager().setSetting("auik.imc.edit_object", obj.getObjektid().intValue(), false);
				manager.switchModul("m_objekt_bearbeiten");
			}
		}
	}
}
