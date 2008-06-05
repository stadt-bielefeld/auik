/*
 * Datei:
 * $Id: AnhBwkAuswertung.java,v 1.1 2008-06-05 11:38:32 u633d Exp $
 * 
 * Erstellt am 24.08.2005 von David Klotz
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/09/14 11:25:38  u633d
 * - Version vom 14.9.
 *
 * Revision 1.1  2005/08/25 14:46:59  u633d
 * - zu viel ;)
 *
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.indeinl.ViewBwk;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.AnhBwkModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für BWK-Datensätze.
 * @author David Klotz
 */
public class AnhBwkAuswertung extends AbstractQueryModul {
	/** Das obere Panel mit den Abfrage-Optionen */
	private JPanel queryPanel;
	
	// Widgets für die Abfrage
	private JComboBox jahrBox;
	private JButton submitButton;
	
	/** Das TableModel für die Ergebnis-Tabelle */
	private AnhBwkModel tmodel;

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Auswertung BWK";
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
	 */
	public JPanel getQueryOptionsPanel() {
		if (queryPanel == null) {
			// Die Widgets initialisieren:
			
			// Die jahrBox wird mit Werten von thisYear - range bis 
			// thisYear + range gefüllt:
			int range = 15;
			Calendar cal = Calendar.getInstance();
			int thisYear = cal.get(Calendar.YEAR);
			String[] years = new String[2*range + 1];
			for (int i = 0; i < years.length; i++) {
				int y = thisYear + (i-range);
				years[i] = Integer.toString(y);
			}
			jahrBox = new JComboBox(years);
			jahrBox.setSelectedItem(Integer.toString(thisYear));
			jahrBox.setEditable(true);
			
			submitButton = new JButton("Suchen");
			
			// Ein ActionListener für den Button, 
			// der die eigentliche Suche auslöst: 
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int jahrTMP;
					try {
						jahrTMP = Integer.parseInt((String)jahrBox.getSelectedItem());
					} catch (NumberFormatException e1) {
						jahrTMP = -1;
					}
					final int jahr = jahrTMP;
					
					SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
						protected void doNonUILogic() {
							((AnhBwkModel)getTableModel()).setList(ViewBwk.findByErfassungsjahr(jahr));
						}

						protected void doUIUpdateLogic(){
							((AnhBwkModel)getTableModel()).fireTableDataChanged();
							frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
						}
					};
					worker.start();
				}
			});
			
			// Noch etwas Layout...
			FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu, pref");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			
			builder.append("Erfassungsjahr:", jahrBox, submitButton);
			
			queryPanel = builder.getPanel();
		}
		
		return queryPanel;
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
	 */
	public ListTableModel getTableModel() {
		if (tmodel == null) {
			tmodel = new AnhBwkModel();
		}
		return tmodel;
	}
}
