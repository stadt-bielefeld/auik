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
 * $Id: EinleiterAnh50Auswertung.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 *
 * Erstellt am 15.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2010/01/12 09:02:44  u633d
 * Fettabscheider-Auswertung
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.2  2005/09/14 11:25:37  u633d
 * - Version vom 14.9.
 *
 * Revision 1.1  2005/08/24 08:42:51  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenchrono;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.editors.AwsvEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.AwsvAnlagenchronoModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für Anhang 50-Datensätze.
 * @author David Klotz
 */
public class AwsvChronologieAuswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JCheckBox wiedervorlageCheck;
    private JCheckBox abgeschlossenCheck;
    private JButton submitButton;


    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#editObject(int)
     */
    @Override
    protected void editObject(int row) {
        if (row != -1) {
            Fachdaten fd = ((Anlagenchrono)tmodel.getObjectAtRow(row)).getFachdaten();

            AwsvEditor editor = new AwsvEditor(fd, frame, "Daten");

            editor.setVisible(true);

            if (editor.wasSaved()) {
                // Nach dem Bearbeiten die Liste updaten,
                // damit unsere Änderungen auch angezeigt werden.
                updateListe();
            }
        }
    }

    /** Das TableModel für die Ergebnis-Tabelle */
    private AwsvAnlagenchronoModel tmodel;

    public void updateListe() {
        SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable(250, 250, 50, 100, 50)) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                ((AwsvAnlagenchronoModel)getTableModel()).updateList();
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                getTableModel().fireTableDataChanged();
                frame.changeStatus(+ getTableModel().getRowCount() + " Objekte gefunden");
            }
        };
        worker.start();
    }


    @Override
    public void show() {
        super.show();

        updateListe();
    }

    private JTable getResultTable(int a, int b, int c, int d, int e) {

        JTable resultTable = getResultTable();

        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        TableColumnModel model = resultTable.getColumnModel();
        model.getColumn(0).setPreferredWidth(a);
        model.getColumn(1).setPreferredWidth(b);
        model.getColumn(2).setPreferredWidth(c);
        model.getColumn(3).setPreferredWidth(d);
        model.getColumn(4).setPreferredWidth(e);

        return resultTable;
    }



    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Anlagenchronologien";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory() {
        return "AwSV";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:
            wiedervorlageCheck = new JCheckBox("Nur abgelaufene Wiedervorlage", true);
            abgeschlossenCheck = new JCheckBox("Nur nicht abgeschlossen", true);

            submitButton = new JButton("Suchen");

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((AwsvAnlagenchronoModel)getTableModel()).setList(
                        DatabaseQuery.getAnlagenchrono(
                                wiedervorlageCheck.isSelected(),
                                abgeschlossenCheck.isSelected()));
                    ((AwsvAnlagenchronoModel)getTableModel()).fireTableDataChanged();
                    frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout("pref, 3dlu, pref");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append(wiedervorlageCheck, abgeschlossenCheck);
            builder.append(submitButton, createExportButton());

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
            tmodel = new AwsvAnlagenchronoModel();
        }
        return tmodel;
    }
}
