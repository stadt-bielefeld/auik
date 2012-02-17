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
 * $Id: ProbenehmerAuswertung.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 03.05.2006 von Gerd Genuit
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
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
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ProbepunkteModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für Probenehmereinsaetze.
 * @author Gerd Genuit
 */
public class ProbepunkteAuswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JButton probenehmerButton;
    private JButton eSatzungButton;
    private JButton uwbButton;
    private JButton inaktivButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private ProbepunkteModel tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Probepunkte";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * @return "m_auswertung_probenehmer"
     */
    @Override
    public String getIdentifier() {
        return "m_auswertung_probepunkte";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren
        	probenehmerButton = new JButton("Probenehmereinsätze");
        	inaktivButton = new JButton("inaktive Probepunkte");
        	uwbButton = new JButton("UWB-Punkte");
        	eSatzungButton = new JButton("E-Satzungspunkte");

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
        	probenehmerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable(200, 10, 200, 50, 200, 100, 100)) {
                        @Override
                        protected void doNonUILogic() {
                            ((ProbepunkteModel)getTableModel()).setList(AtlProbepkt.getProbenehmerpunkte());
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            ((ProbepunkteModel)getTableModel()).fireTableDataChanged();
                            frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

        	eSatzungButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable(200, 10, 200, 50, 200, 100, 100)) {
                        @Override
                        protected void doNonUILogic() {
                            ((ProbepunkteModel)getTableModel()).setList(AtlProbepkt.getESatzung());
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            ((ProbepunkteModel)getTableModel()).fireTableDataChanged();

                            frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

        	uwbButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable(200, 10, 200, 50, 200, 100, 100)) {
                        @Override
                        protected void doNonUILogic() {
                            ((ProbepunkteModel)getTableModel()).setList(AtlProbepkt.getUWB());
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            ((ProbepunkteModel)getTableModel()).fireTableDataChanged();

                            frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

        	inaktivButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable(200, 10, 200, 50, 200, 100, 100)) {
                        @Override
                        protected void doNonUILogic() {
                            ((ProbepunkteModel)getTableModel()).setList(AtlProbepkt.getInaktiv());
                        }

                        @Override
                        protected void doUIUpdateLogic(){
                            ((ProbepunkteModel)getTableModel()).fireTableDataChanged();

                            frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                        }
                    };
                    worker.start();
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append(eSatzungButton, uwbButton);
            builder.append(inaktivButton, probenehmerButton);

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
            tmodel = new ProbepunkteModel();
        }
        return tmodel;
    }

    private JTable getResultTable(int a, int b, int c, int d, int e, int f, int g) {

        JTable resultTable = getResultTable();

        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumn column = null;

        for (int i = 0; i < 5; i++) {
            column = resultTable.getColumnModel().getColumn(i);

            if (i == 0) {
                column.setPreferredWidth(a);
            } else if (i == 1) {
                column.setPreferredWidth(b);
            } else if (i == 2) {
                column.setPreferredWidth(c);
            } else if (i == 3) {
                column.setPreferredWidth(d);
            } else if (i == 4) {
                column.setPreferredWidth(e);
            } else if (i == 5) {
                column.setPreferredWidth(e);
            } else if (i == 6) {
                column.setPreferredWidth(e);
            }
        }

        return resultTable;
    }
}
