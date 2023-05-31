/**
 * Copyright 2005-2011, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */

/*
 * Datei:
 * $Id: EinleiterBrennwertAuswertung.java,v 1.1.2.1 2010-11-23 10:25:55 u633d Exp $
 *
 * Erstellt am 24.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2010/01/12 09:03:55  u633d
 * Fettabscheider-Auswertung
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
 *
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.PrioritaetModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul zur Abfrage der Prioritäten.
 * @author Gerhard Genuit
 */
public class EinleiterPrioritaetAuswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JButton submitButton;
    private JComboBox sachbearbeiterBox;
    private JComboBox prioritaetBox;

    /** Das TableModel für die Ergebnis-Tabelle */
    private PrioritaetModel tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Priorität";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:

            sachbearbeiterBox = new JComboBox();
            sachbearbeiterBox.setModel(new DefaultComboBoxModel(
                    DatabaseQuery.getOrderedAll(new Sachbearbeiter(), "name")
                        .toArray(new Sachbearbeiter[0])));
            sachbearbeiterBox.insertItemAt(null, 0);
            sachbearbeiterBox.setSelectedItem(DatabaseQuery.getCurrentSachbearbeiter());
            sachbearbeiterBox.setSelectedItem(-1);
            sachbearbeiterBox.setEditable(true);

            prioritaetBox = new JComboBox();
            String[] items = {"-","1","2","3", "4"};
            prioritaetBox = new JComboBox(items);
            prioritaetBox.setSelectedItem(-1);
            prioritaetBox.setEditable(true);

            submitButton = new JButton("suchen");

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                	SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
                        @Override
                        protected void doNonUILogic() {
                            String prio = (String) prioritaetBox.getSelectedItem();
                            ((PrioritaetModel)getTableModel()).setList(
                                DatabaseQuery.getObjektsWithPriority(prio, (Sachbearbeiter) sachbearbeiterBox.getSelectedItem()));
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            ((PrioritaetModel)getTableModel()).fireTableDataChanged();
                            frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu, pref, 3dlu, pref");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append("SachbearbeiterIn:", sachbearbeiterBox, prioritaetBox, submitButton);

            queryPanel = builder.getPanel();
        }

        return queryPanel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
     */
    @Override
    public ListTableModel getTableModel() {
        if (tmodel == null) {
            tmodel = new PrioritaetModel();
        }
        return tmodel;
    }

	/**
	 * Schaltet zum "Standort suchen"-Modul um, wenn zu einer Zeile in der
	 * Ergebnis-Tabelle ein Lage vorhanden ist.
	 *
	 * @param row
	 *            Die Zeile der Tabelle.
	 */
	@Override
    protected void editObject(int row) {

	        if (row != -1) {
	            Object obj = getTableModel().getObjectAtRow(row);
            	Object[] fd = (Object[])obj;
            	Object standort = fd[0];

	            if (standort != null) {
	            	manager.getSettingsManager().setStandort((Standort) standort);
	                manager.switchModul("m_standort_suchen");
	            }

	        }
	    }
}
