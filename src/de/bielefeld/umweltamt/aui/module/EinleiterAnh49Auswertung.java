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
 * $Id: EinleiterAnh49Auswertung.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 *
 * Erstellt am 15.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2010/01/12 09:02:32  u633d
 * Fettabscheider-Auswertung
 *
 * Revision 1.3  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/07/23 06:55:22  u633d
 * Anh49 Auswertung und Sielhautimport neu
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.4  2005/09/14 11:25:38  u633d
 * - Version vom 14.9.
 *
 * Revision 1.3  2005/09/07 05:56:14  u633d
 * - Anh 49 ergänzt und neue Mappings
 *
 * Revision 1.2  2005/08/31 06:25:12  u633d
 * - kleine Ergänzungen
 *
 * Revision 1.1  2005/08/24 08:42:51  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh49Model;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Auswertungs-Modul für Anhang 49-Datensätze(ausgenommen
 * Fettabscheider).
 * @author David Klotz
 */
public class EinleiterAnh49Auswertung extends AbstractQueryModul {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;

    // Widgets für die Abfrage
    private JCheckBox abgemeldetCheck;
    private JCheckBox abwasserfreiCheck;
    private JCheckBox aktivCheck;
    private JCheckBox wiedervorlageCheck;
    private JComboBox sachbBox;
    private JComboBox dekraTuevBox;
    private JButton auswahlButton;
    private JButton tabelleExportButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private Anh49Model tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Anhang 49";
    }

    @Override
    public String getIdentifier() {
        return "m_auswertung_anh49";
    }

    public HauptFrame getFrame() {
        return frame;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:
            wiedervorlageCheck = new JCheckBox("Nur abgelaufene Wiedervorlage");
            abgemeldetCheck = new JCheckBox("Abgemeldet");
            abwasserfreiCheck = new JCheckBox("Abwasserfrei");
            aktivCheck = new JCheckBox("Aktiv");
            abgemeldetCheck.setSelected(false);
            abwasserfreiCheck.setSelected(false);
            aktivCheck.setSelected(true);
            sachbBox = new JComboBox();
            sachbBox.setModel(new DefaultComboBoxModel(Anh49Fachdaten
                .getAllSachbearbeiter()));
//            sachbBox.setEditable(true);
            sachbBox.setSelectedItem(BasisSachbearbeiter
                .getCurrentSachbearbeiter());
            dekraTuevBox = new JComboBox();
            dekraTuevBox.setModel(new DefaultComboBoxModel(Anh49Fachdaten
                .getAllDekraTuevYears()));
            dekraTuevBox
                .setSelectedIndex(dekraTuevBox.getModel().getSize() - 1);

            auswahlButton = new JButton("Auswahl anwenden");

            auswahlButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Anh49Model model = (Anh49Model) getTableModel();
                    model.setList(DatabaseQuery.getAnh49FachdatenAuswahl(
                        aktivCheck.isSelected(),
                        abgemeldetCheck.isSelected(),
                        abwasserfreiCheck.isSelected(),
                        wiedervorlageCheck.isSelected(),
                        (Integer) dekraTuevBox.getSelectedItem(),
                        (BasisSachbearbeiter) sachbBox.getSelectedItem()));
                    model.fireTableDataChanged();
                    frame.changeStatus("" + model.getRowCount()
                        + " Objekte gefunden");
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout(
                "pref, 20dlu, pref, 20dlu, pref, 3dlu, pref, 20dlu, pref");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append(abgemeldetCheck);
            builder.append(aktivCheck);
            builder.append("SachbearbeiterIn:", sachbBox);
            builder.append(auswahlButton);
            builder.nextLine();
            builder.append(abwasserfreiCheck);
            builder.append(wiedervorlageCheck);
            builder.append("TÜV/DEKRA Termin:", dekraTuevBox);
            builder.append(getTabelleExportButton());

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
            tmodel = new Anh49Model();
        }
        return tmodel;
    }

    private JButton getTabelleExportButton() {
        if (tabelleExportButton == null) {
            tabelleExportButton = new JButton("Tabelle speichern");
            tabelleExportButton.setEnabled(true);

            tabelleExportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveTabelle();
                }
            });
        }

        return tabelleExportButton;
    }

    /**
     * Speichert eine ProbenTabelle.
     */
    public void saveTabelle() {
        File exportDatei = getFrame().saveFile(new String[] {"csv"});
        if (exportDatei != null) {
            String ext = AuikUtils.getExtension(exportDatei);

            if (ext == null) {
                String newExt;
                if (exportDatei.getName().endsWith(".")) {
                    newExt = "csv";
                } else {
                    newExt = ".csv";
                }
                exportDatei = new File(exportDatei.getParent(),
                    exportDatei.getName() + newExt);
            }

            boolean doIt = false;
            if (exportDatei.exists()) {
                boolean answer = GUIManager.getInstance().showQuestion(
                    "Soll die vorhandene Datei " + exportDatei.getName()
                        + " wirklich überschrieben werden?",
                    "Datei bereits vorhanden!");
                if (answer && exportDatei.canWrite()) {
                    doIt = true;
                }
            } else if (exportDatei.getParentFile().canWrite()) {
                doIt = true;
            }

            if (doIt) {
                log.debug("Speichere nach '" + exportDatei.getName()
                    + "' (Ext: '" + ext + "') in '" + exportDatei.getParent()
                    + "' !");
                if (AuikUtils.exportTableDataToCVS(getResultTable(),
                    exportDatei)) {
                    log.debug("Speichern erfolgreich!");
                } else {
                    log.debug("Fehler beim Speichern!");
                    GUIManager.getInstance().showErrorMessage(
                        "Beim Speichern der Datei '" + exportDatei
                            + "' trat ein Fehler auf!");
                }
            }
        }
    }
}
