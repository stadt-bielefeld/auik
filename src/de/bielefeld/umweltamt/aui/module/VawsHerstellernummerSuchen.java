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

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFachdaten;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.editors.VawsEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.HerstellNrSuchenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches Such-Modul für Vaws-Anlagen anhand der Herstellnummer.
 * @author Sebastian Geller
 */
public class VawsHerstellernummerSuchen extends AbstractQueryModul {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Das obere Panel mit den Such-Optionen */
    private JPanel queryPanel;
    private String iconPath = "filefind32.png";  //Das Icon für Suchen
    // Widgets für die Suche
    private JTextField herstellFeld;
    private JButton suchenButton;

    /** Das TableModel für die Ergebnis-Tabelle */
    private HerstellNrSuchenModel ergebnisModel;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    // Der Name mit dem das Modul im AUIK angezeigt wird
    public String getName() {
        return "Herstellnr. suchen";
    }
    // Legt fest in welchen Menü das Modul im AUIK zu finden ist
    public String getCategory() {
        return "VAwS";
    }

    public Icon getIcon() {
        return super.getIcon(iconPath);
    }
    //Aufruf der eigentlichen Suchfunktion in VawsFachdaten
    public void SuchStart()
    {
        String herstellnr = herstellFeld.getText();
        log.debug(" Suche nach Herstellnummer " + herstellnr);
        ((HerstellNrSuchenModel)getTableModel()).setList(
                VawsFachdaten.findherstellnr(herstellnr));// Aufruf der Suchfunktion. Startet eine Query in der Datenbank
        ((HerstellNrSuchenModel)getTableModel()).fireTableDataChanged();
        frame.changeStatus("" + getTableModel().getRowCount() + " Objekte gefunden"); // Anzeige über Anzahl der gefundenen Objekte
        log.debug(getTableModel().getRowCount()
        		+ " Objekt(e) mit Herstellnummer " + herstellnr + " gefunden");
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
     */
    public JPanel getQueryOptionsPanel() {
        if (queryPanel == null) {
            // Die Widgets initialisieren:
            herstellFeld = new JTextField("", 12);


            suchenButton = new JButton("Suchen");
            suchenButton.setToolTipText("Herstellnummer suchen");


//             Ein KeyListener für das Textfeld(herstellFeld),
            // der die eigentliche Suche auslöst:

            herstellFeld.addKeyListener(new KeyAdapter() {
                // Suche wird mit Return oder Enter ausgelöst
                public void keyPressed(KeyEvent e) {

                    if (e.getKeyCode() == 10) {
                        SuchStart();

                    }
                }
            });

            // Ein ActionListener für den Button(suchenButton),
            // der die eigentliche Suche auslöst:
            suchenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    SuchStart();
                }
            });


            // Noch etwas Layout...
            FormLayout layout = new FormLayout(
                    "pref, 3dlu, pref, 3dlu, pref, 20dlu, pref, 3dlu, pref, 3dlu, pref, 20dlu, pref"
                    );
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);

            // Für Darstellung der Suchoptionen im oberen Panel
            builder.append("Herstellnr.:", herstellFeld, suchenButton);
            builder.nextLine();
            builder.append("");


            queryPanel = builder.getPanel();
        }

        return queryPanel;
    }

    // Öffnet durch Aufruf des VawsEditors ein Fenster um ein Objekt bearbeiten zu können
    protected void editObject(int row) {
        if (row != -1) {
            VawsFachdaten fachdaten = ((VawsFachdaten)ergebnisModel.getObjectAtRow(row));

            VawsEditor editor = new VawsEditor(fachdaten, frame, "Herstellnummer");

            editor.setVisible(true);

        }
    }

    //Aufruf des Modells für die Ergebnis-Tabelle
    public ListTableModel getTableModel() {
        if (ergebnisModel == null) {
            ergebnisModel = new HerstellNrSuchenModel();
        }
        return ergebnisModel;
    }
}
