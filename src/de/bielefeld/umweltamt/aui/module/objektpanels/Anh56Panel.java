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
 * $Id: Anh56Panel.java,v 1.1.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2010/02/04 13:34:38  u633d
 * Verkn�pfte Objekte
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/08/17 05:45:31  u633d
 * - Anhang 56 erstellt
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das Panel zum Bearbeiten von Druckereien
 * @author u633d
 */
public class Anh56Panel extends ObjectPanel {
    private static final long serialVersionUID = -6981678796941528077L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private Anfallstelle anfallstelle;

    // Widgets
    private JTextField druckverfahrenFeld = null;
    private JTextField verbrauchFeld = null;
    private JTextField entsorgungFeld = null;
    private TextFieldDateChooser gen59Datum = null;
    private TextFieldDateChooser gen58Datum = null;
    private JCheckBox abaCheck = null;
    private JCheckBox genpflichtCheck = null;
    private JCheckBox abwasseranfallCheck = null;
    private JTextArea BemerkungenArea = null;

    private JButton saveAnh56Button = null;

    // Daten
    private Anh56Fachdaten fachdaten = null;

    @SuppressWarnings("deprecation")
    public Anh56Panel(BasisObjektBearbeiten hauptModul, Anfallstelle anfallstelle) {
        this.name = "Druckerei";
        this.hauptModul = hauptModul;
        this.anfallstelle = anfallstelle;

        FormLayout layout = new FormLayout(
            "r:90dlu, 5dlu, 95dlu, 5dlu, r:0dlu, 0dlu, 90dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.appendSeparator("Fachdaten");
        builder.append("Druckverfahren:", getDruckverfahrenFeld());
        builder.append("", getAbwasseranfallCheck());
        builder.nextLine();
        builder.append("Wasserverbrauch:", getVerbrauchFeld());
        builder.append("", getAbaCheck());
        builder.nextLine();
        builder.append("Entsorgung:", getEntsorgungFeld());
        builder.append("", getGenpflichtCheck());
        builder.nextLine();
        builder.append("Datum 58er Genehmigung:", getGen58Datum());
        builder.nextLine();
        builder.append("Datum 59er Genehmigung:", getGen59Datum());
        builder.nextLine();

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);

        builder.appendRow("3dlu");
        builder.nextLine(2);

        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
            getSaveAnh56Button());

        // JComponent buttonBar = ComponentFactory.buildOKBar(getSaveAnh56Button());
        builder.append(buttonBar, 7);
        addChangeListeners(getBemerkungenArea(), getDruckverfahrenFeld(),
            getVerbrauchFeld(), getEntsorgungFeld(), getGen58Datum(), getGen59Datum(),
            getAbaCheck(), getGenpflichtCheck(), getAbwasseranfallCheck());
    }

    public void completeObjekt(Anfallstelle anfallstelle) {
        if (this.fachdaten == null) {
            // Neues Anhang 56 Objekt erzeugen
            this.fachdaten = new Anh56Fachdaten();
        }
        // Anfallstelle setzen
            this.anfallstelle = anfallstelle;
            this.fachdaten.setAnfallstelle(this.anfallstelle);
    }

    protected boolean doSavePanelData() {
        boolean success;

        String bemerkungen = this.BemerkungenArea.getText();
        if ("".equals(bemerkungen)) {
            this.fachdaten.setBemerkungen(null);
        } else {
            this.fachdaten.setBemerkungen(bemerkungen);
        }

        String druckverf = this.druckverfahrenFeld.getText();
        if ("".equals(druckverf)) {
            this.fachdaten.setDruckverfahren(null);
        } else {
            this.fachdaten.setDruckverfahren(druckverf);
        }

        String verbrauch = this.verbrauchFeld.getText();
        if ("".equals(verbrauch)) {
            this.fachdaten.setVerbrauch(null);
        } else {
            this.fachdaten.setVerbrauch(verbrauch);
        }

        String entsorgung = this.entsorgungFeld.getText();
        if ("".equals(entsorgung)) {
            this.fachdaten.setEntsorgung(null);
        } else {
            this.fachdaten.setEntsorgung(entsorgung);
        }

        Date gen58 = this.gen58Datum.getDate();
        this.fachdaten.setGen58(gen58);

        Date gen59 = this.gen59Datum.getDate();
        this.fachdaten.setGen59(gen59);

        if (getAbaCheck().isSelected()) {
            this.fachdaten.setAba(true);
        } else {
            this.fachdaten.setAba(false);
        }

        if (getGenpflichtCheck().isSelected()) {
            this.fachdaten.setGenpflicht(true);
        } else {
            this.fachdaten.setGenpflicht(false);
        }

        if (getAbwasseranfallCheck().isSelected()) {
            this.fachdaten.setAbwasseranfall(true);
        } else {
            this.fachdaten.setAbwasseranfall(false);
        }

        success = fachdaten.merge();
        if (success) {
            log.debug("Anh 56 Objekt " + this.fachdaten.getId()
                + " gespeichert.");
        } else {
            log.debug("Anh 56 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void enableAll(boolean enabled) {
        // This is intentionally left blank
    }

    public void clearForm() {
        getBemerkungenArea().setText(null);
        getDruckverfahrenFeld().setText(null);
        getVerbrauchFeld().setText(null);
        getEntsorgungFeld().setText(null);

        getGen58Datum().setDate(null);
        getGen59Datum().setDate(null);

        getAbaCheck().setSelected(false);
        getGenpflichtCheck().setSelected(false);
        getAbwasseranfallCheck().setSelected(false);
    }

    public void updateForm(Anfallstelle anfallstelle) throws RuntimeException {

        this.fachdaten = anfallstelle.getAnh56Fachdatens().iterator().next();

        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null) {
                getBemerkungenArea().setText(this.fachdaten.getBemerkungen());
            }
            if (this.fachdaten.getDruckverfahren() != null) {
                getDruckverfahrenFeld().setText(
                    this.fachdaten.getDruckverfahren());
            }
            if (this.fachdaten.getVerbrauch() != null) {
                getVerbrauchFeld().setText(this.fachdaten.getVerbrauch());
            }
            if (this.fachdaten.getEntsorgung() != null) {
                getEntsorgungFeld().setText(this.fachdaten.getEntsorgung());
            }

            if (this.fachdaten.getGen58() != null) {
                getGen58Datum().setDate(this.fachdaten.getGen58());
            }
            if (this.fachdaten.getGen59() != null) {
                getGen59Datum().setDate(this.fachdaten.getGen59());
            }

            if (this.fachdaten.getAba() != null) {
                if (this.fachdaten.getAba() == true) {
                    getAbaCheck().setSelected(true);
                } else {
                    getAbaCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getGenpflicht() != null) {
                if (this.fachdaten.getGenpflicht() == true) {
                    getGenpflichtCheck().setSelected(true);
                } else {
                    getGenpflichtCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getAbwasseranfall() != null) {
                if (this.fachdaten.getAbwasseranfall() == true) {
                    getAbwasseranfallCheck().setSelected(true);
                } else {
                    getAbwasseranfallCheck().setSelected(false);
                }
            }
        }
        setDirty(false);
    }

    public void fetchFormData() throws RuntimeException {
        Set<Anfallstelle> list = this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh56Fachdaten.findByAnfallstelleId(
                list.iterator().next().getId());
        log.debug("Anhang 56 Objekt aus DB geholt: ID" + this.fachdaten);
    }

    private JButton getSaveAnh56Button() {
        if (this.saveAnh56Button == null) {
            this.saveAnh56Button = new JButton("Speichern");

            this.saveAnh56Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (hauptModul.saveAllTabs()) {
                        Anh56Panel.this.hauptModul.getFrame().changeStatus(
                            "Anh 56 Objekt "
                                + Anh56Panel.this.fachdaten.getId()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh56Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Anh 56 Objekt!",
                            HauptFrame.ERROR_COLOR);
                    }

                    Anh56Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh56Button;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private JCheckBox getAbaCheck() {
        if (this.abaCheck == null) {
            this.abaCheck = new JCheckBox("Abwasserbehandlung");
        }
        return this.abaCheck;
    }

    private JCheckBox getAbwasseranfallCheck() {
        if (this.abwasseranfallCheck == null) {
            this.abwasseranfallCheck = new JCheckBox("Abwasseranfall");
        }
        return this.abwasseranfallCheck;
    }

    private JTextField getDruckverfahrenFeld() {
        if (this.druckverfahrenFeld == null) {
            this.druckverfahrenFeld = new LimitedTextField(150);
        }
        return this.druckverfahrenFeld;
    }

    private JTextField getEntsorgungFeld() {
        if (this.entsorgungFeld == null) {
            this.entsorgungFeld = new LimitedTextField(150);
        }
        return this.entsorgungFeld;
    }

    private TextFieldDateChooser getGen58Datum() {
        if (this.gen58Datum == null) {
            this.gen58Datum = new TextFieldDateChooser();
        }
        return this.gen58Datum;
    }

    private TextFieldDateChooser getGen59Datum() {
        if (this.gen59Datum == null) {
            this.gen59Datum = new TextFieldDateChooser();
        }
        return this.gen59Datum;
    }

    private JCheckBox getGenpflichtCheck() {
        if (this.genpflichtCheck == null) {
            this.genpflichtCheck = new JCheckBox("Genehmigungspflicht");
        }
        return this.genpflichtCheck;
    }

    private JTextField getVerbrauchFeld() {
        if (this.verbrauchFeld == null) {
            this.verbrauchFeld = new LimitedTextField(150);
        }
        return this.verbrauchFeld;
    }

    private JTextArea getBemerkungenArea() {
        if (this.BemerkungenArea == null) {
            this.BemerkungenArea = new LimitedTextArea(255);
            this.BemerkungenArea.setLineWrap(true);
            this.BemerkungenArea.setWrapStyleWord(true);
        }
        return this.BemerkungenArea;
    }
}
