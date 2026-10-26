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
/**
 * Copyright 2005-2011, Stadt Bielefeld
 * [License header unchanged...]
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
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

        // ── InnerPanel ────────────────────────────────────
        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 5);

        // ── SEPARATOR: Fachdaten ──────────────────────────
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 3; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Fachdaten"), gbc);

        // Zeile 1: SachbearbeiterIn ABS | Wasserschutzgebiet
        addRow(innerPanel, gbc, 1,
            "Sachbearbeiter:in ABS",
            getSachbearbeiterravFeld(),
            getWsgCheck());

        // Zeile 2: SachbearbeiterIn ATL | Genehmigungspflicht
        addRow(innerPanel, gbc, 2,
            "Sachbearbeiter:in ATL.",
            getSachbearbeiterheepenFeld(),
            getGenehmigungspflichtCheck());

        // Zeile 3: AnsprechpartnerIn | Genehmigung §58
        addRowFieldAndDateChooser(innerPanel, gbc, 3,
            "Ansprechpartner:in",
            getAnsprechpartnerFeld(),
            "Genehmigung §58",
            getGen58Datum());

        // Zeile 4: Herkunftsbereich | Genehmigung §59
        addRowFieldAndDateChooser(innerPanel, gbc, 4,
            "Herkunftsbereich",
            getHerkunftsbereichFeld(),
            "Genehmigung §59",
            getGen59Datum());

        // Zeile 5: Kläranlage | Nachtrag
        addRow(innerPanel, gbc, 5,
            "Kläranlage",
            getKlaeranlageFeld(),
            getNachtragCheck());

        // Zeile 6: Abwassermenge genehmigt | BImSch
        addRow(innerPanel, gbc, 6,
            "Abwassermenge genehmigt [m³/a]:",
            getAbwmengegenehmigtFeld(),
            getBimschCheck());

        // Zeile 7: Abwassermenge prod.-spez.
        addRowFieldOnly(innerPanel, gbc, 7,
            "Abwassermenge prod.-spez. [m³/a]:",
            getAbwmengeprodspezFeld());

        // Zeile 8: Abwassermenge gesamt
        addRowFieldOnly(innerPanel, gbc, 8,
            "Abwassermenge gesamt [m³/a]:",
            getAbwmengegesamtFeld());

        // ── SEPARATOR: Bemerkungen ────────────────────────
        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 3; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Bemerkungen"), gbc);

        // BemerkungsArea
        JScrollPane bemerkungsScroller = new JScrollPane(
            getAnh40BemerkungArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bemerkungsScroller.setPreferredSize(
            new java.awt.Dimension(0, 80));
        bemerkungsScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 80));
        gbc.gridy = 10; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(bemerkungsScroller, gbc);

        // ── ButtonBar ─────────────────────────────────────
        JPanel buttonBar = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonBar.add(getSaveAnh40Button());
        gbc.gridy = 11; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(buttonBar, gbc);

        // ── Wrapper ───────────────────────────────────────
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(innerPanel, BorderLayout.WEST);
        this.setLayout(new BorderLayout());
        this.add(wrapperPanel, BorderLayout.NORTH);

        addChangeListeners(
            getAnh40BemerkungArea(),
            getAnsprechpartnerFeld(),
            getSachbearbeiterravFeld(),
            getSachbearbeiterheepenFeld(),
            getKlaeranlageFeld(),
            getHerkunftsbereichFeld(),
            getWsgCheck(),
            getGenehmigungspflichtCheck(),
            getNachtragCheck(),
            getBimschCheck(),
            getAbwmengegenehmigtFeld(),
            getAbwmengeprodspezFeld(),
            getAbwmengegesamtFeld(),
            getGen58Datum(),
            getGen59Datum());
    }

    // ── Hilfsmethode: Label + Feld + Checkbox ─────────────
    private void addRow(JPanel panel, GridBagConstraints gbc,
        int row, String label,
        java.awt.Component field, JCheckBox check) {
        gbc.gridy = row; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        field.setPreferredSize(new java.awt.Dimension(200,
            field.getPreferredSize().height));
        panel.add(field, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(check, gbc);
    }

    // ── Hilfsmethode: Label + Feld (ohne Checkbox) ────────
    private void addRowFieldOnly(JPanel panel,
        GridBagConstraints gbc,
        int row, String label,
        java.awt.Component field) {
        gbc.gridy = row; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        field.setPreferredSize(new java.awt.Dimension(200,
            field.getPreferredSize().height));
        panel.add(field, gbc);
    }

    // ── Hilfsmethode: Feld links + Label+DateChooser rechts
    private void addRowFieldAndDateChooser(JPanel panel,
        GridBagConstraints gbc, int row,
        String label1, java.awt.Component field1,
        String label2, TextFieldDateChooser chooser) {
        gbc.gridy = row; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        panel.add(new JLabel(label1), gbc);

        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        field1.setPreferredSize(new java.awt.Dimension(200,
            field1.getPreferredSize().height));
        panel.add(field1, gbc);

        // Label2 + Chooser rechts
        JPanel rightPart = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 3, 0));
        rightPart.add(new JLabel(label2));
        chooser.setPreferredSize(new java.awt.Dimension(120,
            chooser.getPreferredSize().height));
        rightPart.add(chooser);
        gbc.gridx = 2; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(rightPart, gbc);
    }

    // ── createSeparator() ─────────────────────────────────
    private JPanel createSeparator(String title) {
        JPanel sepPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel lbl = new JLabel(title);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
        sepPanel.add(lbl, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0);
        sepPanel.add(
            new JSeparator(SwingConstants.HORIZONTAL), gbc);
        return sepPanel;
    }

    // ── fetchFormData() ────────────────────────────────────
    public void fetchFormData() throws RuntimeException {
        Set<Anfallstelle> list =
            this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh40Fachdaten.findByAnfallstelleId(
            list.iterator().next().getId());
        log.debug("Anhang 40 Objekt aus DB geholt: ID"
            + this.fachdaten);
    }

    // ── updateForm(Anfallstelle) ───────────────────────────
    public void updateForm(Anfallstelle anfallstelle)
        throws RuntimeException {
        this.fachdaten =
            anfallstelle.getAnh40Fachdatens()
                .iterator().next();
        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null)
                getAnh40BemerkungArea().setText(
                    this.fachdaten.getBemerkungen());
            if (this.fachdaten.getAnsprechpartner() != null)
                getAnsprechpartnerFeld().setText(
                    this.fachdaten.getAnsprechpartner());
            if (this.fachdaten.getHerkunftsbereich() != null)
                getHerkunftsbereichFeld().setText(
                    this.fachdaten.getHerkunftsbereich());
            if (this.fachdaten.getSachbearbeiterrav() != null)
                getSachbearbeiterravFeld().setText(
                    this.fachdaten.getSachbearbeiterrav());
            if (this.fachdaten.getSachbearbeiterheepen() != null)
                getSachbearbeiterheepenFeld().setText(
                    this.fachdaten.getSachbearbeiterheepen());
            if (this.fachdaten.getKlaeranlage() != null)
                getKlaeranlageFeld().setText(
                    this.fachdaten.getKlaeranlage());
            if (this.fachdaten.getWsg() != null)
                getWsgCheck().setSelected(
                    this.fachdaten.getWsg());
            if (this.fachdaten.getGenehmigungspflicht() != null)
                getGenehmigungspflichtCheck().setSelected(
                    this.fachdaten.getGenehmigungspflicht());
            if (this.fachdaten.getNachtrag() != null)
                getNachtragCheck().setSelected(
                    this.fachdaten.getNachtrag());
            if (this.fachdaten.getBimsch() != null)
                getBimschCheck().setSelected(
                    this.fachdaten.getBimsch());
            if (this.fachdaten.getAbwmengegenehmigt() != null)
                getAbwmengegenehmigtFeld().setText(
                    this.fachdaten.getAbwmengegenehmigt()
                        .toString());
            if (this.fachdaten.getAbwmengeprodspez() != null)
                getAbwmengeprodspezFeld().setText(
                    this.fachdaten.getAbwmengeprodspez()
                        .toString());
            if (this.fachdaten.getAbwmengegesamt() != null)
                getAbwmengegesamtFeld().setText(
                    this.fachdaten.getAbwmengegesamt()
                        .toString());
            if (this.fachdaten.getGen58() != null)
                getGen58Datum().setDate(
                    this.fachdaten.getGen58());
            if (this.fachdaten.getGen59() != null)
                getGen59Datum().setDate(
                    this.fachdaten.getGen59());
        }
        setDirty(false);
    }

    // ── clearForm() ────────────────────────────────────────
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

    // ── enableAll() ────────────────────────────────────────
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

    // ── doSavePanelData() ──────────────────────────────────
    @Override
    protected boolean doSavePanelData() {
        boolean success;

        String bemerkungen = this.anh40BemerkungArea.getText();
        this.fachdaten.setBemerkungen(
            "".equals(bemerkungen) ? null : bemerkungen);

        String ansprechp = this.ansprechpartnerFeld.getText();
        this.fachdaten.setAnsprechpartner(
            "".equals(ansprechp) ? null : ansprechp);

        String sachbearbrav =
            this.sachbearbeiterravFeld.getText();
        this.fachdaten.setSachbearbeiterrav(
            "".equals(sachbearbrav) ? null : sachbearbrav);

        String sachbearbheepen =
            this.sachbearbeiterheepenFeld.getText();
        this.fachdaten.setSachbearbeiterheepen(
            "".equals(sachbearbheepen)
                ? null : sachbearbheepen);

        String klaeranlage = this.klaeranlageFeld.getText();
        this.fachdaten.setKlaeranlage(
            "".equals(klaeranlage) ? null : klaeranlage);

        String herkunft = this.herkunftsbereichFeld.getText();
        this.fachdaten.setHerkunftsbereich(
            "".equals(herkunft) ? null : herkunft);

        this.fachdaten.setWsg(getWsgCheck().isSelected());
        this.fachdaten.setGenehmigungspflicht(
            getGenehmigungspflichtCheck().isSelected());
        this.fachdaten.setNachtrag(
            getNachtragCheck().isSelected());
        this.fachdaten.setBimsch(
            getBimschCheck().isSelected());

        Integer abwgen =
            ((IntegerField) this.abwmengegenehmigtFeld)
                .getIntValue();
        this.fachdaten.setAbwmengegenehmigt(abwgen);

        Integer abwprod =
            ((IntegerField) this.abwmengeprodspezFeld)
                .getIntValue();
        this.fachdaten.setAbwmengeprodspez(abwprod);

        Integer abwges =
            ((IntegerField) this.abwmengegesamtFeld)
                .getIntValue();
        this.fachdaten.setAbwmengegesamt(abwges);

        Date gen58 = this.gen58Datum.getDate();
        this.fachdaten.setGen58(gen58);

        Date gen59 = this.gen59Datum.getDate();
        this.fachdaten.setGen59(gen59);

        success = this.fachdaten.merge();
        if (success) {
            log.debug("Anh 40 Objekt "
                + this.fachdaten.getId() + " gespeichert.");
        } else {
            log.debug("Anh 40 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    // ── completeObjekt() ───────────────────────────────────
    public void completeObjekt(Anfallstelle anfallstelle) {
        if (anfallstelle.getAnh40Fachdatens().size() == 0) {
            this.fachdaten = new Anh40Fachdaten();
            this.fachdaten.setAnfallstelle(anfallstelle);
        }
    }

    // ── getSaveAnh40Button() ───────────────────────────────
    private JButton getSaveAnh40Button() {
        if (this.saveAnh40Button == null) {
            this.saveAnh40Button = new JButton("Speichern");
            this.saveAnh40Button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableAll(false);
                        if (hauptModul.saveAllTabs()) {
                            Anh40Panel.this.hauptModul
                                .getFrame().changeStatus(
                                "Anh 40 Objekt "
                                + Anh40Panel.this.fachdaten
                                    .getId()
                                + " erfolgreich"
                                + " gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                        } else {
                            Anh40Panel.this.hauptModul
                                .getFrame().changeStatus(
                                "Fehler beim Speichern"
                                + " des Anh 40 Objekt!",
                                HauptFrame.ERROR_COLOR);
                        }
                        Anh40Panel.this.hauptModul.fillForm();
                    }
                });
        }
        return this.saveAnh40Button;
    }

    // ── getName() ──────────────────────────────────────────
    @Override
    public String getName() {
        return this.name;
    }

    // ── Getter Methoden ────────────────────────────────────
    private JTextArea getAnh40BemerkungArea() {
        if (this.anh40BemerkungArea == null) {
            this.anh40BemerkungArea = new LimitedTextArea(150);
            this.anh40BemerkungArea.setLineWrap(true);
            this.anh40BemerkungArea.setWrapStyleWord(true);
        }
        return this.anh40BemerkungArea;
    }

    private JTextField getAnsprechpartnerFeld() {
        if (this.ansprechpartnerFeld == null)
            this.ansprechpartnerFeld =
                new LimitedTextField(50);
        return this.ansprechpartnerFeld;
    }

    private TextFieldDateChooser getGen58Datum() {
        if (this.gen58Datum == null)
            this.gen58Datum = new TextFieldDateChooser();
        return this.gen58Datum;
    }

    private TextFieldDateChooser getGen59Datum() {
        if (this.gen59Datum == null)
            this.gen59Datum = new TextFieldDateChooser();
        return this.gen59Datum;
    }

    private JTextField getHerkunftsbereichFeld() {
        if (this.herkunftsbereichFeld == null)
            this.herkunftsbereichFeld =
                new LimitedTextField(50);
        return this.herkunftsbereichFeld;
    }

    private JTextField getSachbearbeiterravFeld() {
        if (this.sachbearbeiterravFeld == null)
            this.sachbearbeiterravFeld =
                new LimitedTextField(50);
        return this.sachbearbeiterravFeld;
    }

    private JTextField getSachbearbeiterheepenFeld() {
        if (this.sachbearbeiterheepenFeld == null)
            this.sachbearbeiterheepenFeld =
                new LimitedTextField(50);
        return this.sachbearbeiterheepenFeld;
    }

    private JCheckBox getWsgCheck() {
        if (this.wsgCheck == null)
            this.wsgCheck =
                new JCheckBox("Wasserschutzgebiet");
        return this.wsgCheck;
    }

    private JCheckBox getGenehmigungspflichtCheck() {
        if (this.genehmigungspflichtCheck == null)
            this.genehmigungspflichtCheck =
                new JCheckBox("Genehmigungspflicht");
        return this.genehmigungspflichtCheck;
    }

    private JCheckBox getNachtragCheck() {
        if (this.nachtragCheck == null)
            this.nachtragCheck =
                new JCheckBox("Nachtragsgenehmigung");
        return this.nachtragCheck;
    }

    private JCheckBox getBimschCheck() {
        if (this.bimschCheck == null)
            this.bimschCheck =
                new JCheckBox("BImSch-Genehmigung");
        return this.bimschCheck;
    }

    private JFormattedTextField getAbwmengegenehmigtFeld() {
        if (this.abwmengegenehmigtFeld == null)
            this.abwmengegenehmigtFeld = new IntegerField();
        return this.abwmengegenehmigtFeld;
    }

    private JFormattedTextField getAbwmengeprodspezFeld() {
        if (this.abwmengeprodspezFeld == null)
            this.abwmengeprodspezFeld = new IntegerField();
        return this.abwmengeprodspezFeld;
    }

    private JFormattedTextField getAbwmengegesamtFeld() {
        if (this.abwmengegesamtFeld == null)
            this.abwmengegesamtFeld = new IntegerField();
        return this.abwmengegesamtFeld;
    }

    private JTextField getKlaeranlageFeld() {
        if (this.klaeranlageFeld == null)
            this.klaeranlageFeld = new LimitedTextField(50);
        return this.klaeranlageFeld;
    }
}