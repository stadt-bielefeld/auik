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

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh50Model;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.WiedervorlageModel;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für Anhang 50-Datensätze.
 * @author David Klotz
 */
public class WiedervorlageAuswertung extends AbstractQueryModul {
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JCheckBox wiedervorlageCheck;
    private JButton submitButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private WiedervorlageModel tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Wiedervorlage für " + DatabaseQuery.getCurrentSachbearbeiter();
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:
            wiedervorlageCheck = new JCheckBox("Nur abgelaufene Wiedervorlage", true);

            submitButton = new JButton("Suchen");

            // Ein ActionListener für den Button,
            // der die eigentliche Suche auslöst:
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((WiedervorlageModel)getTableModel()).setList(
                        DatabaseQuery.getBasisObjektByWiedervorlage(
                        		wiedervorlageCheck.isSelected()));
                    ((WiedervorlageModel)getTableModel()).fireTableDataChanged();
                    frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden");
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout("pref, 3dlu, pref");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append(wiedervorlageCheck, submitButton);

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
            tmodel = new WiedervorlageModel();
        }
        return tmodel;
    }
}
