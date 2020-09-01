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
 * $Id: VawsHerstellernummerSuchen.java,v 1.0 2009/09/28 13:36:20 u633z
 *
 * Erstellt am 28.09.2009 von Sebastian Geller

 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.elka.Wasserrecht;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.editors.AwsvEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.DirekteinleiterModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.HerstellNrSuchenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Such-Modul für Direkteinleiter anhand des Aktenzeichens.
 * @author Gerhard Genuit
 */
public class DirekteinleiterSuchen extends AbstractQueryModul {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Das obere Panel mit den Such-Optionen */
    private JPanel queryPanel;
    private String iconPath = "filefind32.png";  //Das Icon für Suchen
    // Widgets für die Suche
    private JTextField azFeld;
    private JButton suchenButton;

    /** Das TableModel fÃ¼r die Ergebnis-Tabelle */
    private DirekteinleiterModel ergebnisModel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    // Der Name mit dem das Modul im AUIK angezeigt wird
    @Override
    public String getName() {
        return "Aktenzeichen suchen";
    }

    @Override
    public String getIdentifier() {
        return "m_auswertung_direkteinleiter";
    }

    public HauptFrame getFrame() {
        return frame;
    }

    @Override
    public Icon getIcon() {
        return super.getIcon(iconPath);
    }
    //Aufruf der eigentlichen Suchfunktion in VawsFachdaten
    public void SuchStart() {
        String aktenzeichen = azFeld.getText();
        log.debug(" Suche nach Aktenzeichen " + aktenzeichen);
        ((DirekteinleiterModel)getTableModel()).setList(
            DatabaseQuery.findWasserrechtAktenzeichen(aktenzeichen));// Aufruf der Suchfunktion. Startet eine Query in der Datenbank
        ((DirekteinleiterModel)getTableModel()).fireTableDataChanged();
        frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden"); // Anzeige Ã¼ber Anzahl der gefundenen Objekte
        log.debug(getTableModel().getRowCount()
        		+ " Objekt(e) mit Aktenzeichen " + aktenzeichen + " gefunden");
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    @Override
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:
            azFeld = new JTextField("", 12);

            suchenButton = new JButton("Suchen");
            suchenButton.setToolTipText("Aktenzeichen suchen");

//             Ein KeyListener fÃ¼r das Textfeld(herstellFeld),
            // der die eigentliche Suche auslÃ¶st:

            azFeld.addKeyListener(new KeyAdapter() {
                // Suche wird mit Return oder Enter ausgelÃ¶st
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == 10) {
                        SuchStart();
                    }
                }
            });

            // Ein ActionListener fÃ¼r den Button(suchenButton),
            // der die eigentliche Suche auslÃ¶st:
            suchenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SuchStart();
                }
            });

            // Noch etwas Layout...
            FormLayout layout = new FormLayout(
                    "pref, 3dlu, pref, 3dlu, pref, 20dlu, pref, 3dlu, pref, 3dlu, pref, 20dlu, pref"
                    );
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            // FÃ¼r Darstellung der Suchoptionen im oberen Panel
            builder.append("Aktenzeichen:", azFeld, suchenButton);
            builder.nextLine();
            builder.append("");

            queryPanel = builder.getPanel();
        }

        return queryPanel;
    }

    //Aufruf des Modells fÃ¼r die Ergebnis-Tabelle
    @Override
    public ListTableModel getTableModel() {
        if (ergebnisModel == null) {
            ergebnisModel = new DirekteinleiterModel();
        }
        return ergebnisModel;
    }
}
