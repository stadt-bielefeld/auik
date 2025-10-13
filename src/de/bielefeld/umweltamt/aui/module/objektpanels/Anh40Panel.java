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
 * Created on 29.06.2005 by u633d
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Anhang 40"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class Anh40Panel extends ObjectPanel {
    private static final long serialVersionUID = -8519254704315572879L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JTextArea anh40BemerkungArea = null;
    private JTextField ansprechpartnerFeld = null;
    private JTextField sachbearbeiterravFeld = null;
    private JTextField sachbearbeiterheepenFeld = null;
    private JTextField klaeranlageFeld = null;
    private JTextField herkunftsbereichFeld = null;
    private JCheckBox wsgCheck = null;
    private JCheckBox genehmigungspflichtCheck = null;
    private JCheckBox nachtragCheck = null;
    private JCheckBox bimschCheck = null;
    private JFormattedTextField abwmengegenehmigtFeld = null;
    private JFormattedTextField abwmengeprodspezFeld = null;
    private JFormattedTextField abwmengegesamtFeld = null;
    private TextFieldDateChooser gen58Datum = null;
    private TextFieldDateChooser gen59Datum = null;

    private JButton saveAnh40Button = null;

    // Daten
    private Anh40Fachdaten fachdaten = null;

    @SuppressWarnings("deprecation")
    public Anh40Panel(BasisObjektBearbeiten hauptModul) {
        this.name = "Anhang 40";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:120dlu, 5dlu, 80dlu, 5dlu, r:65dlu, 5dlu, 100dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.appendSeparator("Fachdaten");
        builder.append("SachbearbeiterIn ABS:",
            getSachbearbeiterravFeld());
        builder.append("", getWsgCheck());
        builder.nextLine();
        builder.append("SachbearbeiterIn ATL.:",
            getSachbearbeiterheepenFeld());
        builder.append("", getGenehmigungspflichtCheck());
        builder.nextLine();
        builder.append("AnsprechpartnerIn:", getAnsprechpartnerFeld());
        builder.append("Genehmigung §58:", getGen58Datum());
        builder.nextLine();
        builder.append("Herkunftsbereich:", getHerkunftsbereichFeld());
        builder.append("Genehmigung §59:", getGen59Datum());
        builder.nextLine();
        builder.append("Kläranlage:", getKlaeranlageFeld());
        builder.append("", getNachtragCheck());
        builder.nextLine();
        builder.append("Abwassermenge genehmigt [m³/a]:", getAbwmengegenehmigtFeld());
        builder.append("", getBimschCheck());
        builder.nextLine();
        builder.append("Abwassermenge prod.-spez. [m³/a]:",
            getAbwmengeprodspezFeld());
        builder.nextLine();
        builder.append("Abwassermenge gesamt [m³/a]:", getAbwmengegesamtFeld());
        builder.nextLine();
        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(
            getAnh40BemerkungArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);
        builder.nextLine();

        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
                getSaveAnh40Button());
        builder.append(buttonBar,7);
        addChangeListeners(getAnh40BemerkungArea(),
            getAnsprechpartnerFeld(), getSachbearbeiterravFeld(),
            getSachbearbeiterheepenFeld(), getKlaeranlageFeld(),
            getHerkunftsbereichFeld(), getWsgCheck(),
            getGenehmigungspflichtCheck(), getNachtragCheck(),
            getBimschCheck(), getAbwmengegenehmigtFeld(),
            getAbwmengeprodspezFeld(), getAbwmengegesamtFeld(),
            getGen58Datum(), getGen59Datum());
    }

    public void fetchFormData() throws RuntimeException {
        Set<Anfallstelle> list = this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh40Fachdaten.findByAnfallstelleId(
                list.iterator().next().getId());
        log.debug("Anhang 40 Objekt aus DB geholt: ID" + this.fachdaten);
    }

    public void updateForm(Anfallstelle anfallstelle) throws RuntimeException {
        this.fachdaten = anfallstelle.getAnh40Fachdatens().iterator().next();
        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null) {
                getAnh40BemerkungArea()
                    .setText(this.fachdaten.getBemerkungen());
            }
            if (this.fachdaten.getAnsprechpartner() != null) {
                getAnsprechpartnerFeld().setText(
                    this.fachdaten.getAnsprechpartner());
            }
            if (this.fachdaten.getHerkunftsbereich() != null) {
                getHerkunftsbereichFeld().setText(
                    this.fachdaten.getHerkunftsbereich());
            }
            if (this.fachdaten.getSachbearbeiterrav() != null) {
                getSachbearbeiterravFeld().setText(
                    this.fachdaten.getSachbearbeiterrav());
            }
            if (this.fachdaten.getSachbearbeiterheepen() != null) {
                getSachbearbeiterheepenFeld().setText(
                    this.fachdaten.getSachbearbeiterheepen());
            }
            if (this.fachdaten.getKlaeranlage() != null) {
                getKlaeranlageFeld().setText(this.fachdaten.getKlaeranlage());
            }
            if (this.fachdaten.getWsg() != null) {
                if (this.fachdaten.getWsg() == true) {
                    getWsgCheck().setSelected(true);
                } else {
                    getWsgCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getGenehmigungspflicht() != null) {
                if (this.fachdaten.getGenehmigungspflicht() == true) {
                    getGenehmigungspflichtCheck().setSelected(true);
                } else {
                    getGenehmigungspflichtCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getNachtrag() != null) {
                if (this.fachdaten.getNachtrag() == true) {
                    getNachtragCheck().setSelected(true);
                } else {
                    getNachtragCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getBimsch() != null) {
                if (this.fachdaten.getBimsch() == true) {
                    getBimschCheck().setSelected(true);
                } else {
                    getBimschCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getAbwmengegenehmigt() != null) {
                getAbwmengegenehmigtFeld().setText(
                    this.fachdaten.getAbwmengegenehmigt().toString());
            }
            if (this.fachdaten.getAbwmengeprodspez() != null) {
                getAbwmengeprodspezFeld().setText(
                    this.fachdaten.getAbwmengeprodspez().toString());
            }
            if (this.fachdaten.getAbwmengegesamt() != null) {
                getAbwmengegesamtFeld().setText(
                    this.fachdaten.getAbwmengegesamt().toString());
            }
            if (this.fachdaten.getGen58() != null) {
                getGen58Datum().setDate(this.fachdaten.getGen58());
            }
            if (this.fachdaten.getGen59() != null) {
                getGen59Datum().setDate(this.fachdaten.getGen59());
            }
        }
        setDirty(false);
    }

    public void clearForm() {
        getAnh40BemerkungArea().setText(null);
        getAnsprechpartnerFeld().setText(null);
        getSachbearbeiterravFeld().setText(null);
        getSachbearbeiterheepenFeld().setText(null);
        getKlaeranlageFeld().setText(null);
        getHerkunftsbereichFeld().setText(null);
        getWsgCheck().setSelected(false);
        getGenehmigungspflichtCheck().setSelected(false);
        getNachtragCheck().setSelected(false);
        getBimschCheck().setSelected(false);
        getAbwmengegenehmigtFeld().setText(null);
        getAbwmengeprodspezFeld().setText(null);
        getAbwmengegesamtFeld().setText(null);
        getGen58Datum().setDate(null);
        getGen59Datum().setDate(null);
    }

    public void enableAll(boolean enabled) {
        getAnh40BemerkungArea().setEnabled(enabled);
        getAnsprechpartnerFeld().setEnabled(enabled);
        getSachbearbeiterravFeld().setEnabled(enabled);
        getSachbearbeiterheepenFeld().setEnabled(enabled);
        getKlaeranlageFeld().setEnabled(enabled);
        getHerkunftsbereichFeld().setEnabled(enabled);
        getWsgCheck().setEnabled(enabled);
        getGenehmigungspflichtCheck().setEnabled(enabled);
        getNachtragCheck().setEnabled(enabled);
        getBimschCheck().setEnabled(enabled);
        getAbwmengegenehmigtFeld().setEnabled(enabled);
        getAbwmengeprodspezFeld().setEnabled(enabled);
        getAbwmengegesamtFeld().setEnabled(enabled);
        getGen58Datum().setEnabled(enabled);
        getGen59Datum().setEnabled(enabled);
    }

    @Override
    protected boolean doSavePanelData() {
        boolean success;

        String bemerkungen = this.anh40BemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            this.fachdaten.setBemerkungen(null);
        } else {
            this.fachdaten.setBemerkungen(bemerkungen);
        }

        String ansprechp = this.ansprechpartnerFeld.getText();
        if ("".equals(ansprechp)) {
            this.fachdaten.setAnsprechpartner(null);
        } else {
            this.fachdaten.setAnsprechpartner(ansprechp);
        }

        String sachbearbrav = this.sachbearbeiterravFeld.getText();
        if ("".equals(sachbearbrav)) {
            this.fachdaten.setSachbearbeiterrav(null);
        } else {
            this.fachdaten.setSachbearbeiterrav(sachbearbrav);
        }

        String sachbearbheepen = this.sachbearbeiterheepenFeld.getText();
        if ("".equals(sachbearbheepen)) {
            this.fachdaten.setSachbearbeiterheepen(null);
        } else {
            this.fachdaten.setSachbearbeiterheepen(sachbearbheepen);
        }

        String klaeranlage = this.klaeranlageFeld.getText();
        if ("".equals(klaeranlage)) {
            this.fachdaten.setKlaeranlage(null);
        } else {
            this.fachdaten.setKlaeranlage(klaeranlage);
        }

        String herkunft = this.herkunftsbereichFeld.getText();
        if ("".equals(herkunft)) {
            this.fachdaten.setHerkunftsbereich(null);
        } else {
            this.fachdaten.setHerkunftsbereich(herkunft);
        }

        if (getWsgCheck().isSelected()) {
            this.fachdaten.setWsg(true);
        } else {
            this.fachdaten.setWsg(false);
        }

        if (getGenehmigungspflichtCheck().isSelected()) {
            this.fachdaten.setGenehmigungspflicht(true);
        } else {
            this.fachdaten.setGenehmigungspflicht(false);
        }

        if (getNachtragCheck().isSelected()) {
            this.fachdaten.setNachtrag(true);
        } else {
            this.fachdaten.setNachtrag(false);
        }

        if (getBimschCheck().isSelected()) {
            this.fachdaten.setBimsch(true);
        } else {
            this.fachdaten.setBimsch(false);
        }

        Integer abwgen = ((IntegerField) this.abwmengegenehmigtFeld)
            .getIntValue();
        this.fachdaten.setAbwmengegenehmigt(abwgen);

        Integer abwprod = ((IntegerField) this.abwmengeprodspezFeld)
            .getIntValue();
        this.fachdaten.setAbwmengeprodspez(abwprod);

        Integer abwges = ((IntegerField) this.abwmengegesamtFeld).getIntValue();
        this.fachdaten.setAbwmengegesamt(abwges);

        Date gen58 = this.gen58Datum.getDate();
        this.fachdaten.setGen58(gen58);

        Date gen59 = this.gen59Datum.getDate();
        this.fachdaten.setGen59(gen59);

        success = this.fachdaten.merge();
        if (success) {
            log.debug("Anh 40 Objekt " + this.fachdaten.getId()
                + " gespeichert.");
        } else {
            log.debug("Anh 40 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt(Anfallstelle anfallstelle) {

        if (anfallstelle.getAnh40Fachdatens().size() == 0) {
            // Neues Anhang 40 Objekt erzeugen
            this.fachdaten = new Anh40Fachdaten();
            // Anfallstelle setzen
            this.fachdaten.setAnfallstelle(anfallstelle);

        }
    }

    private JButton getSaveAnh40Button() {
        if (this.saveAnh40Button == null) {
            this.saveAnh40Button = new JButton("Speichern");

            this.saveAnh40Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (hauptModul.saveAllTabs()) {
                        Anh40Panel.this.hauptModul.getFrame().changeStatus(
                            "Anh 40 Objekt "
                                + Anh40Panel.this.fachdaten.getId()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh40Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Anh 40 Objekt!",
                            HauptFrame.ERROR_COLOR);
                    }


                    Anh40Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh40Button;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private JTextArea getAnh40BemerkungArea() {
        if (this.anh40BemerkungArea == null) {
            this.anh40BemerkungArea = new LimitedTextArea(150);
            this.anh40BemerkungArea.setLineWrap(true);
            this.anh40BemerkungArea.setWrapStyleWord(true);
        }
        return this.anh40BemerkungArea;
    }

    private JTextField getAnsprechpartnerFeld() {
        if (this.ansprechpartnerFeld == null) {
            this.ansprechpartnerFeld = new LimitedTextField(50);
        }
        return this.ansprechpartnerFeld;
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

    private JTextField getHerkunftsbereichFeld() {
        if (this.herkunftsbereichFeld == null) {
            this.herkunftsbereichFeld = new LimitedTextField(50);
        }
        return this.herkunftsbereichFeld;
    }

    private JTextField getSachbearbeiterravFeld() {
        if (this.sachbearbeiterravFeld == null) {
            this.sachbearbeiterravFeld = new LimitedTextField(50);
        }
        return this.sachbearbeiterravFeld;
    }

    private JTextField getSachbearbeiterheepenFeld() {
        if (this.sachbearbeiterheepenFeld == null) {
            this.sachbearbeiterheepenFeld = new LimitedTextField(50);
        }
        return this.sachbearbeiterheepenFeld;
    }

    private JCheckBox getWsgCheck() {
        if (this.wsgCheck == null) {
            this.wsgCheck = new JCheckBox("Wasserschutzgebiet");
        }
        return this.wsgCheck;
    }

    private JCheckBox getGenehmigungspflichtCheck() {
        if (this.genehmigungspflichtCheck == null) {
            this.genehmigungspflichtCheck = new JCheckBox("Genehmigungspflicht");
        }
        return this.genehmigungspflichtCheck;
    }

    private JCheckBox getNachtragCheck() {
        if (this.nachtragCheck == null) {
            this.nachtragCheck = new JCheckBox("Nachtragsgenehmigung");
        }
        return this.nachtragCheck;
    }

    private JCheckBox getBimschCheck() {
        if (this.bimschCheck == null) {
            this.bimschCheck = new JCheckBox("BImSch-Genehmigung");
        }
        return this.bimschCheck;
    }

    private JFormattedTextField getAbwmengegenehmigtFeld() {
        if (this.abwmengegenehmigtFeld == null) {
            this.abwmengegenehmigtFeld = new IntegerField();
        }
        return this.abwmengegenehmigtFeld;
    }

    private JFormattedTextField getAbwmengeprodspezFeld() {
        if (this.abwmengeprodspezFeld == null) {
            this.abwmengeprodspezFeld = new IntegerField();
        }
        return this.abwmengeprodspezFeld;
    }

    private JFormattedTextField getAbwmengegesamtFeld() {
        if (this.abwmengegesamtFeld == null) {
            this.abwmengegesamtFeld = new IntegerField();
        }
        return this.abwmengegesamtFeld;
    }

    private JTextField getKlaeranlageFeld() {
        if (this.klaeranlageFeld == null) {
            this.klaeranlageFeld = new LimitedTextField(50);
        }
        return this.klaeranlageFeld;
    }
}

