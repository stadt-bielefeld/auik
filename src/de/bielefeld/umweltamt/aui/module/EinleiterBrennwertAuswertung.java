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
public class EinleiterBrennwertAuswertung extends AbstractQueryModul {
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
        return "BWK";
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
