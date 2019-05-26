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
 * $Id: EntsorgerEditor.java,v 1.3 2009-07-30 05:31:22 u633d Exp $
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.4  2005/06/10 11:07:09  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.3  2005/06/09 10:17:00  u633z
 * - doSave verbessert
 *
 * Revision 1.2  2005/06/08 08:36:59  u633z
 * - überflüssigen ModulManager und Exception aus Editoren entfernt
 *
 * Revision 1.1  2005/05/30 08:35:41  u633z
 * - Aufgeräumt, mehrere neue Packages, Klassen sortiert
 *
 * Revision 1.2  2005/05/24 15:19:21  u633z
 * - Unnötiges neu laden des Betreiber-Caches entfernt
 * - Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.GridBagConstraints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Entsorger;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
/**
 * Ein Dialog zum Bearbeiten eines Zahnarztentsorgers.
 * @author Gerhard Genuit
 */
public class EntsorgerEditor extends AbstractBaseEditor {
    private static final long serialVersionUID = -225497689116093559L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private JTextField namenFeld;
    private JTextField strassenFeld;
    private JFormattedTextField hausnrFeld;
    private JTextField plzFeld;
    private JTextField ortsFeld;
    private JTextField ansprechpartnerFeld;
    private JTextField telefonFeld;

    /**
     * Erzeugt einen neuen Dialog zum Bearbeiten eines Zahnarztentsorgers.
     */
    public EntsorgerEditor(Entsorger entsorg, HauptFrame owner) {
        super("Entsorger ("+ entsorg.getEntsorger() +")", entsorg, owner);
    }

    @Override
    protected JComponent buildContentArea() {
        namenFeld = new LimitedTextField(100, "");
        strassenFeld = new LimitedTextField(100, "");
        hausnrFeld = new IntegerField();
        plzFeld = new LimitedTextField(5, "");
        ortsFeld = new LimitedTextField(50, "");
        ansprechpartnerFeld = new LimitedTextField(50, "");
        telefonFeld = new LimitedTextField(25, "");

        // Der folgende KeyListener wird benutzt um mit Escape
        // das Bearbeiten abzubrechen.
        KeyListener escListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    doCancel();
                }
            }
        };
        namenFeld.addKeyListener(escListener);
        hausnrFeld.addKeyListener(escListener);
        plzFeld.addKeyListener(escListener);
        ortsFeld.addKeyListener(escListener);

        PanelBuilder builder = new PanelBuilder();
        builder.setAnchor(GridBagConstraints.NORTHWEST);
        builder.setFill(true, false);
        builder.setWeight(0.5, 0);
        builder.setEmptyBorder(10);
        builder.setInsets(0, 0, 5, 5);

        PanelBuilder strasse = new PanelBuilder(builder);
        strasse.setEmptyBorder(0);
        PanelBuilder ort = new PanelBuilder(strasse);

        builder.addSeparator("Stammdaten:", true);
        builder.addComponent(namenFeld, "Name:", true);
        strasse.setWeightX(0.8);
        strasse.addComponent(strassenFeld);
        strasse.setWeightX(0.2);
        strasse.setInsets(0, 0, 0, 0);
        strasse.addComponent(hausnrFeld, true);
        ort.setWeightX(.25);
        ort.addComponent(plzFeld);
        ort.setWeightX(0.75);
        ort.setInsets(0, 0, 0, 0);
        ort.addComponent(ortsFeld, true);
        builder.addComponent(strasse.getPanel(), "Straße:", true);
        builder.addComponent(ort.getPanel(), "Ort:", true);
        builder.addComponent(telefonFeld, "Telefon:", true);
        return builder.getPanel();
    }



    @Override
    protected void fillForm() {
        namenFeld.setText(getEntsorger().getEntsorger());
        strassenFeld.setText(getEntsorger().getStrasse());
        hausnrFeld.setValue(getEntsorger().getHausnr());
        plzFeld.setText(getEntsorger().getPlz());
        ortsFeld.setText(getEntsorger().getOrt());
        ansprechpartnerFeld.setText(getEntsorger().getAnsprechpartner());
        telefonFeld.setText(getEntsorger().getTelefon());
    }

    @Override
    protected boolean canSave() {
        return true;
    }

    /**
     * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
     */
    @Override
    protected boolean doSave() {

        // Name
        String name = namenFeld.getText();
        if ("".equals(name)) {
            getEntsorger().setEntsorger(null);
        } else {
            getEntsorger().setEntsorger(name);
        }
        // Straße
        String stra = strassenFeld.getText();
        if ("".equals(stra)) {
            getEntsorger().setStrasse(null);
        } else {
            getEntsorger().setStrasse(stra);
        }
        // Hausnummer:
        Integer hausnr = ((IntegerField) hausnrFeld).getIntValue();
        getEntsorger().setHausnr(hausnr);

        // PLZ:
        String plz = plzFeld.getText();
        if (plz.equals("")) {
            getEntsorger().setPlz(null);
        } else {
            getEntsorger().setPlz(plz);
        }

        // Ort
        String ort = ortsFeld.getText();

        if ("".equals(ort)) {
            getEntsorger().setOrt(null);
        } else {
            getEntsorger().setOrt(ort);
        }
        // Telefon
        String telefon = telefonFeld.getText();
        if (telefon != null) {
            telefon = telefon.trim();
            if (telefon.equals("")) {
                getEntsorger().setTelefon(null);
            } else {
                getEntsorger().setTelefon(telefon);
            }
        }

        Entsorger persistentEntsorger = null;
        persistentEntsorger = Entsorger.merge(getEntsorger());
        boolean success = (persistentEntsorger != null);

        if (success) {
            setEditedObject(persistentEntsorger);
            log.debug("Änderungen gespeichert!");
        }

        return success;
    }

    public Entsorger getEntsorger() {
        return (Entsorger) getEditedObject();
    }
}