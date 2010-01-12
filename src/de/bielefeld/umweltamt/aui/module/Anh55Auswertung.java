/*
 * Datei:
 * $Id: Anh55Auswertung.java,v 1.3 2010-01-12 09:03:30 u633d Exp $
 * 
 * Erstellt am 03.05.2006 von Gerd Genuit
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2006/09/13 11:06:21  u633d
 * *** empty log message ***
 *
 * Revision 1.2  2006/08/10 14:07:53  u633d
 * Anzahl der Datenaetze bei Auswertung anzeigen
 *
 * Revision 1.1  2006/05/03 09:01:54  u633d
 * Anhang 40 und 55 ergänzt
 *
 * Revision 1.1  2005/08/25 14:46:59  u633d
 * - zu viel ;)
 *
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh55Fachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh55Model;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für Anhang55-Datensätze.
 * @author Gerd Genuit
 */
public class Anh55Auswertung extends AbstractQueryModul {
	/** Das obere Panel mit den Abfrage-Optionen */
	private JPanel queryPanel;
	
	// Widgets für die Abfrage
	private JButton submitButton;
	
	/** Das TableModel für die Ergebnis-Tabelle */
	private Anh55Model tmodel;

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Anhang 55";
	}

	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 * @return "m_auswertung_anh55"
	 */
	public String getIdentifier() {
		return "m_auswertung_anh55";
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
	 */
	public JPanel getQueryOptionsPanel() {
		if (queryPanel == null) {
			// Die Widgets initialisieren
			submitButton = new JButton("Alle Objekte anzeigen");
			
			// Ein ActionListener für den Button, 
			// der die eigentliche Suche auslöst: 
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
						protected void doNonUILogic() {
							((Anh55Model)getTableModel()).setList(Anh55Fachdaten.getAuswertungsListe());
						}

						protected void doUIUpdateLogic(){
							((Anh55Model)getTableModel()).fireTableDataChanged();
							frame.changeStatus(+ getTableModel().getRowCount() + " Objekte gefunden");
						}
					};
					worker.start();
				}
			});
			
			// Noch etwas Layout...
			FormLayout layout = new FormLayout("pref");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			
			builder.append(submitButton);
			
			queryPanel = builder.getPanel();
		}
		
		return queryPanel;
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
	 */
	public ListTableModel getTableModel() {
		if (tmodel == null) {
			tmodel = new Anh55Model();
		}
		return tmodel;
	}
}
