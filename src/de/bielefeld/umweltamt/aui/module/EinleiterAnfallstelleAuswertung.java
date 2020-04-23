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
import java.util.List;

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
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.elka.Anhang;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.AnfallstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh49Model;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein Auswertungs-Modul für Anfallstelle-Datensätze.
 * @author Gerd Genuit
 */
public class EinleiterAnfallstelleAuswertung extends AbstractQueryModul {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    /** Das obere Panel mit den Abfrage-Optionen */
    private JPanel queryPanel;
    private Anhang anhang;
    private String anlagenart;

    // Widgets für die Abfrage
    private JComboBox sachbearbeiterBox;
    private JComboBox anhangBox;
    private JComboBox anlagenartBox;
    private JButton auswahlButton;
    private JButton tabelleExportButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private AnfallstelleModel tmodel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Anfallstelle";
    }

    @Override
    public String getIdentifier() {
        return "m_auswertung_anh49";
    }

    public HauptFrame getFrame() {
        return frame;
    }

    /**
     * @return the sachbearbeiterBox
     */
    private JComboBox getSachbearbeiterBox() {
        if (this.sachbearbeiterBox == null) {
            this.sachbearbeiterBox = new JComboBox();
        }
        return sachbearbeiterBox;
    }


    /**
     * @return the anhangBox
     */
    private JComboBox getAnhangBox() {
        if (this.anhangBox == null) {
            this.anhangBox = new JComboBox();
        }
        return anhangBox;
    }

    /**
     * @return the anlagenartBox
     */
    private JComboBox getAnlagenartBox() {
        if (this.anlagenartBox == null) {
            this.anlagenartBox = new JComboBox();
        }
        return anlagenartBox;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:
            
        	sachbearbeiterBox = new JComboBox();
        	List<Sachbearbeiter> sachbearbeiter = Sachbearbeiter.getOrderedAll();
            getSachbearbeiterBox().setModel(new DefaultComboBoxModel(sachbearbeiter.toArray()));
            getSachbearbeiterBox().setSelectedItem(null);
            
            anhangBox = new JComboBox();
            List<Anhang> anhang = Anhang.getAll();
            getAnhangBox().setModel(new DefaultComboBoxModel(anhang.toArray()));
            
            String[] arten = {"-", "Aufbereitung Medizinprodukte", "Blockheizkraftwerk", 
            		"Fettabscheider", "Gentechnikanlage", "Kompressorenanlage", "KWK Anlage", "Labor", 
            		"RLT Anlagen", "Schrottplatz", "Wärmetauscher"};
            getAnlagenartBox().setModel(new DefaultComboBoxModel(arten));

            auswahlButton = new JButton("Auswahl anwenden");

            auswahlButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AnfallstelleModel model = (AnfallstelleModel) getTableModel();
                    Sachbearbeiter sachbe = (Sachbearbeiter) sachbearbeiterBox.getSelectedItem();
                    Anhang anh = (Anhang) anhangBox.getSelectedItem();
                    String art = (String) anlagenartBox.getSelectedItem();
                    model.setList(DatabaseQuery.getAnfallstelle(anh.getAnhangId(), art, sachbe));
                    model.fireTableDataChanged();
                    frame.changeStatus("" + model.getRowCount()
                        + " Objekte gefunden");
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout(
                "50dlu, 20dlu, 200dlu, 20dlu, pref, 3dlu, pref, 20dlu, pref");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            builder.append("Anhang:", anhangBox);
            builder.append("Sachbearbeiter:", sachbearbeiterBox);
            builder.append(auswahlButton);
            builder.nextLine();
            builder.append("Anlagenart:", anlagenartBox);
            builder.append("");
            builder.append("");
            builder.append(getTabelleExportButton());

            queryPanel = builder.getPanel();
            
            
            this.anhangBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Anhang  anh = (Anhang) anhangBox.getSelectedItem();
                	if (! anh.getAnhangId().equals("99")) {
                		anlagenartBox.setEnabled(false);
                	}else {
                		anlagenartBox.setEnabled(true);
                	}
                	anlagenart = anlagenartBox.getSelectedItem().toString();
                }
            });
            
            this.anlagenartBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Anhang  anh = (Anhang) anhangBox.getSelectedItem();
                	anlagenart = anlagenartBox.getSelectedItem().toString();
                }
            });
        }

        return queryPanel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
     */
    @Override
    public ListTableModel getTableModel() {
        if (tmodel == null) {
            tmodel = new AnfallstelleModel();
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
