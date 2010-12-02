/*
 * Datei:
 * $Id: EinleiterUebergabestellenAuswertung.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 * 
 * Erstellt am 03.05.2006 von Gerd Genuit
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2010/02/04 14:23:57  u633d
 * EinleiterUebergabestellenAuswertung
 *
 * Revision 1.3  2010/01/12 09:02:20  u633d
 * Fettabscheider-Auswertung
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.2  2006/08/10 14:07:53  u633d
 * Anzahl der Datenaetze bei Auswertung anzeigen
 *
 * Revision 1.1  2006/05/03 09:01:54  u633d
 * Anhang 40 und 56 ergänzt
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


import de.bielefeld.umweltamt.aui.mappings.indeinl.IndeinlUebergabestelle;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.UebergabeModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für Übergabestellen.
 * @author Sebastian Geller
 */
public class EinleiterUebergabestellenAuswertung extends AbstractQueryModul {
	/** Das obere Panel mit den Abfrage-Optionen */
	private JPanel queryPanel;
	
	// Widgets für die Abfrage
	private JButton submitButton;
	
	/** Das TableModel für die Ergebnis-Tabelle */
	private UebergabeModel tmodel;

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Übergabestellen";
	}

	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 * @return "m_auswertung_anh40"
	 */
	public String getIdentifier() {
		return "m_auswertung_uebergabe";
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
							((UebergabeModel)getTableModel()).setList(IndeinlUebergabestelle.getAuswertungsListe());
						}

						protected void doUIUpdateLogic(){
							((UebergabeModel)getTableModel()).fireTableDataChanged();
							frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
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
			tmodel = new UebergabeModel();
		}
		return tmodel;
	}
}
