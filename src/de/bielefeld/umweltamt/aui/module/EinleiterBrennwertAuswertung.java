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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.indeinl.BwkFachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.AnhBwkModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für BWK-Datensätze.
 * @author David Klotz
 */
public class EinleiterBrennwertAuswertung
    extends AbstractQueryModul<BwkFachdaten> {

    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;
    // Widgets für die Abfrage
    private JComboBox jahrBox;
    private JComboBox<String> typBox;
    private JButton submitButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private AnhBwkModel tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "BWK";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:

            // Die jahrBox mit "Alle", allen existierenden Werten
            // und "keine Angabe" füllen
            Integer[] iJahre = DatabaseQuery.getBwkErfassungsjahre();
            String[] jahrBoxValues = new String[iJahre.length+1];
            jahrBoxValues[0] = "Alle";
            for (int i = 0; i < iJahre.length; i++) {
                jahrBoxValues[i+1] =
                    (iJahre[i] != null? iJahre[i].toString() : "keine Angabe");
            }
            jahrBox = new JComboBox(jahrBoxValues);
            jahrBox.setSelectedItem(
                Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
//            jahrBox.setEditable(true);

            submitButton = new JButton("Suchen");
            typBox = new JComboBox<>(new String[]{"BWK", "BHKW", "ABA"});

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String value = (String)jahrBox.getSelectedItem();
                    Integer jahr = null;
                    if ("Alle".equals(value)) {
                        jahr = -1;
                    } else if ("keine Angabe".equals(value)) {
                        jahr = null;
                    } else {
                        try {
                            jahr = Integer.parseInt(value);
                        } catch (NumberFormatException e1) {
                            jahr = -1;
                        }
                    }

                    final Integer fJahr = jahr;
                    final String typ = (String) typBox.getSelectedItem();

                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
                        @Override
                        protected void doNonUILogic() {
                            List<BwkFachdaten> result = null;
                            switch (typ) {
                                case "BWK":
                                    result = DatabaseQuery.getBwkByYear(fJahr);
                                    break;
                                case "BHKW":
                                    result = DatabaseQuery.getBHKW(fJahr);
                                case "ABA":
                                    result = DatabaseQuery.getABA(fJahr);
                            }
                            getTableModel().setList(result);
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            getTableModel().fireTableDataChanged();
                            frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append("Erfassungsjahr:", jahrBox);
            builder.append("Typ:", typBox);
            builder.append(submitButton, createExportButton());

            queryPanel = builder.getPanel();
        }

        return queryPanel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
     */
    @Override
    public ListTableModel<BwkFachdaten> getTableModel() {
        if (tmodel == null) {
            tmodel = new AnhBwkModel();
        }
        return tmodel;
    }
}
