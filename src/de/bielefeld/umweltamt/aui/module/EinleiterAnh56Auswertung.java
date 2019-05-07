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
 * $Id: EinleiterAnh56Auswertung.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 03.05.2006 von Gerd Genuit
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2010/01/12 09:03:42  u633d
 * Fettabscheider-Auswertung
 *
 * Revision 1.4  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.3  2008/09/01 07:03:46  u633d
 * *** empty log message ***
 *
 * Revision 1.1  2008/06/05 11:38:32  u633d
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

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh56Model;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für Anhang40-Datensätze.
 * @author Gerd Genuit
 */
public class EinleiterAnh56Auswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JButton submitButton;
    private JButton abwasserButton;
    private JButton genehmigungButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private Anh56Model tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Anhang 56";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * @return "m_auswertung_anh56"
     */
    @Override
    public String getIdentifier() {
        return "m_auswertung_anh56";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren
            submitButton = new JButton("Alle Objekte anzeigen");
            abwasserButton = new JButton("Abwasseranfall");
            genehmigungButton = new JButton("Genehmigungspflicht");

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
                        @Override
                        protected void doNonUILogic() {
                            ((Anh56Model)getTableModel()).setList(
                                DatabaseQuery.getAnhang56(null, null));
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            ((Anh56Model)getTableModel()).fireTableDataChanged();
                            frame.changeStatus(+ getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            abwasserButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
                        @Override
                        protected void doNonUILogic() {
                            ((Anh56Model)getTableModel()).setList(
                                DatabaseQuery.getAnhang56(true, null));
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            ((Anh56Model)getTableModel()).fireTableDataChanged();
                            frame.changeStatus(+ getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            genehmigungButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
                        @Override
                        protected void doNonUILogic() {
                            ((Anh56Model)getTableModel()).setList(
                                DatabaseQuery.getAnhang56(null, true));
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            ((Anh56Model)getTableModel()).fireTableDataChanged();
                            frame.changeStatus(+ getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

            PanelBuilder builder = new PanelBuilder();
            builder.setAnchor(GridBagConstraints.WEST);
            builder.setWeightX(0);
            builder.setInsets(new Insets(0, 0, 0, 10));
            builder.addComponents(submitButton, abwasserButton, genehmigungButton);
            builder.fillRow(true);

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
            tmodel = new Anh56Model();
        }
        return tmodel;
    }
}
