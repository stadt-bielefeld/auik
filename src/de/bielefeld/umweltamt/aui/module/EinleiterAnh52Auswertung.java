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

package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh52Model;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class EinleiterAnh52Auswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JButton submitButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private Anh52Model tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    public String getName() {
        return "Anhang 52";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * @return "m_auswertung_anh52"
     */
    public String getIdentifier() {
        return "m_auswertung_anh52";
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
                            ((Anh52Model)getTableModel()).setList(Anh52Fachdaten.getAuswertungsListe());
                        }

                        protected void doUIUpdateLogic(){
                            ((Anh52Model)getTableModel()).fireTableDataChanged();
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
            tmodel = new Anh52Model();
        }
        return tmodel;
    }
}
