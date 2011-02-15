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
 * $Id: EinleiterSuevkanAuswertung.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 *
 * Erstellt am 24.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2010/01/12 09:05:11  u633d
 * Fettabscheider-Auswertung
 *
 * Revision 1.3  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/06/24 11:24:08  u633d
 * Version 0.3
 *
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
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

import org.eclipse.birt.report.engine.api.EngineException;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.ReportManager;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhSuevFachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.SuevModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für SUEV-KAN-Datensätze.
 * @author David Klotz
 */
public class EinleiterSuevkanAuswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JButton submitButton;
    private JButton printButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private SuevModel tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    public String getName() {
        return "SUEV-KAN";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren
            submitButton = new JButton("Alle Objekte anzeigen");
            printButton = new JButton("Liste drucken");

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
                        protected void doNonUILogic() {
                            ((SuevModel)getTableModel()).setList(AnhSuevFachdaten.getAuswertungsListe());
                        }

                        protected void doUIUpdateLogic(){
                            ((SuevModel)getTableModel()).fireTableDataChanged();
                            frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

            printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showReportListe();
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout("pref, 3dlu, pref");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append(submitButton, printButton);

            queryPanel = builder.getPanel();
        }

        return queryPanel;
    }


    public void showReportListe() {

            try {
                ReportManager.getInstance().startReportWorker("Suev-Kan", printButton);
            } catch (EngineException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
     */
    public ListTableModel getTableModel() {
        if (tmodel == null) {
            tmodel = new SuevModel();
        }
        return tmodel;
    }
}
