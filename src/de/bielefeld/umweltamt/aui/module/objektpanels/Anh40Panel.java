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

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Anhang 40"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class Anh40Panel extends JPanel {
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

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public Anh40Panel(BasisObjektBearbeiten hauptModul) {
        this.name = "Anhang 40";
        this.hauptModul = hauptModul;
        /*
        FormLayout layout = new FormLayout(
            "r:120dlu, 5dlu, 80dlu, 5dlu, r:65dlu, 5dlu, 100dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.appendSeparator("Fachdaten");
        builder.append("SachbearbeiterIn Rav.-Str.:",
            getSachbearbeiterravFeld());
        builder.append("", getWsgCheck());
        builder.nextLine();
        builder.append("SachbearbeiterIn Heepen.:",
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
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);
        builder.nextLine();

        builder.appendSeparator("Verknüpfte Objekte");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 7);
        builder.nextLine();

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(
            getSelectObjektButton(), getSaveAnh40Button());

        builder.append(buttonBar, 7);
        */

        JScrollPane bemerkungsScroller = new JScrollPane(
            getAnh40BemerkungArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane objektverknuepfungScroller = new JScrollPane(
            getObjektverknuepungTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    
        PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                0, 0, 0, 0);

        PanelBuilder fachdaten = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 0.5, 0, 1, 1,
                0, 0, 5, 5);
        fachdaten.addComponent(getSachbearbeiterravFeld(), "SachbearbeiterIn Rav.-Str.:");
        fachdaten.addComponent(getWsgCheck(), true);
        fachdaten.addComponent(getSachbearbeiterheepenFeld(), "SachbearbeiterIn Heepen.:");
        fachdaten.addComponent(getGenehmigungspflichtCheck(), true);
        fachdaten.addComponent(getAnsprechpartnerFeld(), "AnsprechpartnerIn:");
        fachdaten.addComponent(getGen58Datum(), "Genehmigung §58:");
        fachdaten.fillRow(true);
        fachdaten.addComponent(getHerkunftsbereichFeld(), "Herkunftsbereich:");
        fachdaten.addComponent(getGen59Datum(), "Genehmigung §59:");
        fachdaten.fillRow(true);
        fachdaten.addComponent(getKlaeranlageFeld(), "Kläranlage:");
        fachdaten.addComponent(getNachtragCheck(), true);
        fachdaten.addComponent(getAbwmengegenehmigtFeld(), "Abwassermenge genehmigt [m³/a]:");
        fachdaten.addComponent(getBimschCheck(), true);
        fachdaten.addComponent(getAbwmengeprodspezFeld(), "Abwassermenge prod.-spez. [m³/a]:");
        fachdaten.fillRow(true);
        fachdaten.addComponent(getAbwmengegesamtFeld(), "Abwassermenge gesamt [m³/a]:");
        fachdaten.fillRow(true);

        PanelBuilder buttons = new PanelBuilder(PanelBuilder.NORTHEAST, true, false, 0, 0, 1, 1,
                5, 5, 0, 0);
        buttons.fillRow();
        buttons.addComponents(getSelectObjektButton(), getSaveAnh40Button());

        builder.setEmptyBorder(15);
        builder.addSeparator("Fachdaten", true);
        builder.addComponent(fachdaten.getPanel(), true);
        builder.addSeparator("Bemerkungen", true);
        builder.setFill(true, true);
        builder.setWeight(1, 1);
        builder.addComponent(bemerkungsScroller, true);
        builder.setWeight(1, 0);
        builder.addSeparator("Verknüpfte Objekte", true);
        builder.setFill(true, true);
        builder.setWeight(1, 1);
        builder.addComponent(objektverknuepfungScroller, true);
        builder.setWeight(1, 0);
        builder.addComponent(buttons.getPanel());

        this.setLayout(new BorderLayout());
        this.add(builder.getPanel());
    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = Anh40Fachdaten.findByObjektId(
            this.hauptModul.getObjekt().getId());
        log.debug("Anhang 40 Objekt aus DB geholt: ID" + this.fachdaten);
    }

    public void updateForm() throws RuntimeException {
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
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
        }
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

    private boolean saveAnh40Daten() {
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

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neues Anhang 40 Objekt erzeugen
            this.fachdaten = new Anh40Fachdaten();
            // Objekt_Id setzen
            this.fachdaten.setObjekt(this.hauptModul.getObjekt());

            // Anhang 40 Objekt speichern
            Anh40Fachdaten.merge(this.fachdaten);
            log.debug("Neues Anh 40 Objekt " + this.fachdaten + " gespeichert.");
        }
    }

    private JButton getSaveAnh40Button() {
        if (this.saveAnh40Button == null) {
            this.saveAnh40Button = new JButton("Speichern");

            this.saveAnh40Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh40Daten()) {
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

    private JTable getObjektverknuepungTabelle() {
        if (this.objektVerknuepfungModel == null) {
            this.objektVerknuepfungModel = new ObjektVerknuepfungModel(
                this.hauptModul.getObjekt());

            if (this.objektverknuepfungTabelle == null) {
                this.objektverknuepfungTabelle = new JTable(
                    this.objektVerknuepfungModel);
            } else {
                this.objektverknuepfungTabelle
                    .setModel(this.objektVerknuepfungModel);
            }
            this.objektverknuepfungTabelle.getColumnModel().getColumn(0)
                .setPreferredWidth(5);
            this.objektverknuepfungTabelle.getColumnModel().getColumn(1)
                .setPreferredWidth(100);
            this.objektverknuepfungTabelle.getColumnModel().getColumn(2)
                .setPreferredWidth(250);

            this.objektverknuepfungTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getObjektverknuepungTabelle().rowAtPoint(
                                origin);

                            if (row != -1) {
                                Objektverknuepfung obj = Anh40Panel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != Anh40Panel.this.hauptModul
                                    .getObjekt().getId().intValue()) {
                                    Anh40Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                } else {
                                    Anh40Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                }
                                Anh40Panel.this.hauptModul.getManager()
                                    .switchModul("m_objekt_bearbeiten");
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        showVerknuepfungPopup(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showVerknuepfungPopup(e);
                    }
                });

            this.objektverknuepfungTabelle.getInputMap().put(
                (KeyStroke) getVerknuepfungLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getVerknuepfungLoeschAction().getValue(Action.NAME));
            this.objektverknuepfungTabelle.getActionMap().put(
                getVerknuepfungLoeschAction().getValue(Action.NAME),
                getVerknuepfungLoeschAction());
        }

        return this.objektverknuepfungTabelle;
    }

    private void showVerknuepfungPopup(MouseEvent e) {
        if (this.verknuepfungPopup == null) {
            this.verknuepfungPopup = new JPopupMenu("Objekt");
            JMenuItem loeschItem = new JMenuItem(getVerknuepfungLoeschAction());
            this.verknuepfungPopup.add(loeschItem);
        }
        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektverknuepfungTabelle.rowAtPoint(origin);

            if (row != -1) {
                this.objektverknuepfungTabelle
                    .setRowSelectionInterval(row, row);
                this.verknuepfungPopup.show(e.getComponent(), e.getX(),
                    e.getY());
            }
        }
    }

    private Action getVerknuepfungLoeschAction() {
        if (this.verknuepfungLoeschAction == null) {
            this.verknuepfungLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 3271109789818663661L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung = Anh40Panel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (Anh40Panel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                Anh40Panel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                Anh40Panel.this.hauptModul.getFrame()
                                    .changeStatus(
                                        "Konnte das Objekt nicht löschen!",
                                        HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.verknuepfungLoeschAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_L));
            this.verknuepfungLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.verknuepfungLoeschAction;
    }

    private JButton getSelectObjektButton() {
        if (this.selectObjektButton == null) {
            this.selectObjektButton = new JButton("Objekt auswählen");

            this.selectObjektButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ObjektChooser chooser = new ObjektChooser(
                        Anh40Panel.this.hauptModul.getFrame(),
                        Anh40Panel.this.fachdaten.getObjekt(),
                        Anh40Panel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }
}
