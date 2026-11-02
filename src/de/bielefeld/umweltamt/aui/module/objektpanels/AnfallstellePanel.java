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
 * Created on 27.04.2005 by u633d
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
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Anhang;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Anfallstelle"-Tab des ObjektBearbeiten-Moduls
 */
public class AnfallstellePanel extends ObjectPanel {
    private static final long serialVersionUID = 7997458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private Anhang anhang;
    private String anlagenart;

    // Widgets
    private TextFieldDateChooser erstellDatDatum = null;
    private JComboBox<String> betriebsweiseBox;
    private JComboBox<String> beschaffenheitBox;
    private JComboBox<String> anlagenartBox;
    private JComboBox<Anhang> anhangBox;
    private JTextField anhangIdFeld = null;
    private JTextField anwendungsbereichFeld = null;
    private JComboBox<String> herkunftBox;
    private IntegerField befestFlaecheFeld = null;
    private DoubleField lProSFeld = null;
    private DoubleField qmProHFeld = null;
    private DoubleField qmProDFeld = null;
    private IntegerField qmProAFeld = null;
    private JFormattedTextField bezeichnungFeld = null;
    private TextFieldDateChooser stillgelegtAmDatum = null;
    private JFormattedTextField abwaBeschaffOptFeld = null;
    private JFormattedTextField betriebsweiseOptFeld = null;
    private JTextArea bemerkungenArea = null;
    private JButton saveAnfallstelleButton = null;

    // Daten
    private Anfallstelle anfallstelle = null;
    private Anh40Panel anhang40Tab;
    private Anh49Panel anhang49Tab;
    private Anh49AbfuhrenPanel anh49abfuhrTab;
    private Anh49AnalysenPanel anh49analyseTab;
    private Anh50Panel anhang50Tab;
    private Anh52Panel anhang52Tab;
    private Anh55Panel anhang55Tab;
    private Anh56Panel anhang56Tab;
    private BWKPanel bwkTab;

    // Labels
    private JLabel anhangLb = new JLabel("Anhang AbwV");
    private JLabel anlagenartLb = new JLabel("Anlagenart");
    private JLabel anwendungsbereichLb =
        new JLabel("Anwendungsbereich");
    private JLabel herkunftsbereichLb =
        new JLabel("Herkunftsbereich");
    private JLabel befestFlaecheLb =
        new JLabel("befestigte Fläche [m²]:");
    private JLabel maxAbflussmengeLb =
        new JLabel("maximale Abflussmenge");
    private JLabel lProSLb = new JLabel("l/s");
    private JLabel qmProHLb = new JLabel("m³/h");
    private JLabel qmProDLb = new JLabel("m³/d");
    private JLabel abwasserProALb =
        new JLabel("Abwasseranfall/Jahr [m³/a]");

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    @SuppressWarnings("deprecation")
    public AnfallstellePanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Anfallstelle";
        this.hauptModul = hauptModul;

        // ── InnerPanel ────────────────────────────────────
        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 5);

        // ── SEPARATOR: Anfallstelle ───────────────────────
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 4; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Anfallstelle"), gbc);

        // Bezeichnung
        addRowFull(innerPanel, gbc, 1,
            "Bezeichnung", getBezeichnungFeld());

        // Betriebsweise
        addRowFull(innerPanel, gbc, 2,
            "Betriebsweise", getBetriebsweiseBox());

        // Abwasserbeschaffenheit
        addRowFull(innerPanel, gbc, 3,
            "Abwasserbeschaffenheit", getBeschaffenheitBox());

        // Anhang AbwV
        addRowFull(innerPanel, gbc, 4,
            anhangLb, getAnhangBox());

        // Herkunftsbereich
        addRowFull(innerPanel, gbc, 5,
            herkunftsbereichLb, getHerkunftBox());

        // Anlagenart
        addRowFull(innerPanel, gbc, 6,
            anlagenartLb, getAnlagenartBox());

        // Befestigte Fläche
        addRowFull(innerPanel, gbc, 7,
            befestFlaecheLb, getBefestFlaecheFeld());

        // Anwendungsbereich
        addRowFull(innerPanel, gbc, 8,
            anwendungsbereichLb, getAnwendungsbereichFeld());

        // Maximale Abflussmenge: l/s + m³/h + m³/d
        gbc.gridy = 9; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        innerPanel.add(maxAbflussmengeLb, gbc);

        JPanel abflussPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 3, 0));
        getLProSFeld().setPreferredSize(
            new java.awt.Dimension(60,
                getLProSFeld().getPreferredSize().height));
        abflussPanel.add(getLProSFeld());
        abflussPanel.add(lProSLb);
        getQmProHFeld().setPreferredSize(
            new java.awt.Dimension(60,
                getQmProHFeld().getPreferredSize().height));
        abflussPanel.add(getQmProHFeld());
        abflussPanel.add(qmProHLb);
        getQmProDFeld().setPreferredSize(
            new java.awt.Dimension(60,
                getQmProDFeld().getPreferredSize().height));
        abflussPanel.add(getQmProDFeld());
        abflussPanel.add(qmProDLb);
        gbc.gridx = 1; gbc.gridwidth = 3;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        innerPanel.add(abflussPanel, gbc);

        // Abwasseranfall/Jahr
        gbc.gridy = 10; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        innerPanel.add(abwasserProALb, gbc);
        gbc.gridx = 1; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getQmProAFeld().setPreferredSize(
            new java.awt.Dimension(100,
                getQmProAFeld().getPreferredSize().height));
        innerPanel.add(getQmProAFeld(), gbc);

        // Erstelldatum
        addRowFull(innerPanel, gbc, 11,
            "Erstelldatum", getErstellDatDatum());

        // Stilllegedatum
        addRowFull(innerPanel, gbc, 12,
            "Stilllegedatum", getStillgelegtAmDatum());

        // ── SEPARATOR: Bemerkungen ────────────────────────
        gbc.gridx = 0; gbc.gridy = 13;
        gbc.gridwidth = 4; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Bemerkungen"), gbc);

        JScrollPane bemerkungsScroller = new JScrollPane(
            getBemerkungenArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bemerkungsScroller.setPreferredSize(
            new java.awt.Dimension(0, 60));
        bemerkungsScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 60));
        gbc.gridy = 14; gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(bemerkungsScroller, gbc);

        // ── SEPARATOR: Verknüpfte Objekte ─────────────────
        gbc.gridy = 15; gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(
            createSeparator("Verknüpfte Objekte"), gbc);

        JScrollPane objektverknuepfungScroller =
            new JScrollPane(
                getObjektverknuepungTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        objektverknuepfungScroller.setPreferredSize(
            new java.awt.Dimension(0, 100));
        objektverknuepfungScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 100));
        gbc.gridy = 16; gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(objektverknuepfungScroller, gbc);

        // ── ButtonBar ─────────────────────────────────────
        JPanel buttonBar = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonBar.add(getSelectObjektButton());
        buttonBar.add(getSaveAnfallstelleButton());
        gbc.gridy = 17; gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(buttonBar, gbc);

        // ── Wrapper ───────────────────────────────────────
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        wrapperPanel.add(innerPanel, BorderLayout.WEST);
        this.setLayout(new BorderLayout());
        this.add(wrapperPanel, BorderLayout.NORTH);

        // ── Listener ──────────────────────────────────────
        this.beschaffenheitBox.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String besch = (String)
                        beschaffenheitBox.getSelectedItem();
                    switchBeschaffenheit(besch);
                }
            });

        this.anhangBox.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Anhang anh = (Anhang)
                        anhangBox.getSelectedItem();
                    anlagenart = anlagenartBox
                        .getSelectedItem().toString();
                    switchAnhangPanel(
                        anh.getAnhangId(), anlagenart);
                }
            });
    }

    // ── Hilfsmethode: Label + Feld (volle Breite) ─────────
    private void addRowFull(JPanel panel,
        GridBagConstraints gbc,
        int row, String label,
        java.awt.Component field) {
        gbc.gridy = row; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1; gbc.gridwidth = 3;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        field.setPreferredSize(new java.awt.Dimension(400,
            field.getPreferredSize().height));
        panel.add(field, gbc);
    }

    // ── Hilfsmethode: JLabel + Feld ───────────────────────
    private void addRowFull(JPanel panel,
        GridBagConstraints gbc,
        int row, JLabel label,
        java.awt.Component field) {
        gbc.gridy = row; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        panel.add(label, gbc);

        gbc.gridx = 1; gbc.gridwidth = 3;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        field.setPreferredSize(new java.awt.Dimension(300,
            field.getPreferredSize().height));
        panel.add(field, gbc);
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
        this.anfallstelle = Anfallstelle.findByObjektId(
            this.hauptModul.getObjekt().getId());
        setDirty(false);
        log.debug("Anfallstelle aus DB geholt: "
            + this.anfallstelle);
    }

    // ── updateForm() ───────────────────────────────────────
    public void updateForm() throws RuntimeException {
        if (this.anfallstelle != null) {
            if (this.anfallstelle.getErstellDat() != null)
                getErstellDatDatum().setDate(
                    this.anfallstelle.getErstellDat());

            String[] betriebsweise = {"-", "Chargenbetrieb",
                "Durchlaufbetrieb", "Kampagnebetrieb"};
            getBetriebsweiseBox().setModel(
                new DefaultComboBoxModel<>(betriebsweise));
            getBetriebsweiseBox().setSelectedItem(
                Anfallstelle.getBetriebsweiseDescriptionFromId(
                    this.anfallstelle.getBetriebsweiseOpt()));

            String[] beschaffenheit = {
                "Produktionsabwasser", "Kühlwasser",
                "Sanitärwasser",
                "Niederschlagswasser mit Sonderbauwerk",
                "Niederschlagswasser ohne Sonderbauwerk",
                "Grubenwasser", "Fischteiche",
                "sonstiges Wasser"};
            getBeschaffenheitBox().setModel(
                new DefaultComboBoxModel<>(beschaffenheit));
            getBeschaffenheitBox().setSelectedItem(
                Anfallstelle.getBeschaffenheitDescriptionFromId(
                    this.anfallstelle.getAbwaBeschaffOpt()));

            String[] arten = {"-",
                "Aufbereitung Medizinprodukte",
                "Brennwertkessel", "Blockheizkraftwerk",
                "Fettabscheider", "Gentechnikanlage",
                "Kompressorenanlage", "KWK Anlage", "Labor",
                "RLT Anlagen", "Schrottplatz",
                "Wärmetauscher"};
            getAnlagenartBox().setModel(
                new DefaultComboBoxModel<>(arten));
            if (this.anfallstelle.getAnlagenart() != null)
                getAnlagenartBox().setSelectedItem(
                    this.anfallstelle.getAnlagenart());

            List<Anhang> anhang = Anhang.getAll();
            getAnhangBox().setModel(
                new DefaultComboBoxModel<>(
                    anhang.toArray(Anhang[]::new)));
            if (this.anfallstelle.getAnhangId() != null)
                getAnhangBox().setSelectedItem(
                    Anhang.findByAnhangId(
                        this.anfallstelle.getAnhangId()));

            String[] herkunft = {"-",
                "Kategorie I: Unbelastetes NW",
                "Kategorie IIa: Schwach belastetes NW"
                + " (nicht behandlungsbedürftig)",
                "Kategorie IIb: Schwach belastetes NW"
                + " (behandlungsbedürftig)",
                "Kategorie III: Stark belastetes NW"};
            getHerkunftBox().setModel(
                new DefaultComboBoxModel<>(herkunft));

            if (this.anfallstelle.getAnwendungsbereich()
                != null)
                getAnwendungsbereichFeld().setText(
                    this.anfallstelle.getAnwendungsbereich());
            if (this.anfallstelle.getBezeichnung() != null)
                getBezeichnungFeld().setText(
                    this.anfallstelle.getBezeichnung());
            if (this.anfallstelle.getVolJahr() != null)
                getQmProAFeld().setText(
                    this.anfallstelle.getVolJahr().toString());
            if (this.anfallstelle.getMaxVolStunde() != null)
                getQmProHFeld().setText(
                    this.anfallstelle.getMaxVolStunde()
                        .toString());
            if (this.anfallstelle.getMaxVolTag() != null)
                getQmProDFeld().setText(
                    this.anfallstelle.getMaxVolTag()
                        .toString());
            if (this.anfallstelle.getStillgelegtAm() != null)
                getStillgelegtAmDatum().setDate(
                    this.anfallstelle.getStillgelegtAm());
            if (this.anfallstelle.getAbwaBeschaffOpt()
                != null)
                getAbwaBeschaffOptFeld().setText(
                    this.anfallstelle.getAbwaBeschaffOpt()
                        .toString());
            if (this.anfallstelle.getBemerkungen() != null)
                getBemerkungenArea().setText(
                    this.anfallstelle.getBemerkungen());
            if (this.anfallstelle.getBetriebsweiseOpt()
                != null)
                getBetriebsweiseOptFeld().setText(
                    this.anfallstelle.getBetriebsweiseOpt()
                        .toString());

            this.objektVerknuepfungModel.setObjekt(
                this.hauptModul.getObjekt());

            Anhang anh = (Anhang) anhangBox.getSelectedItem();
            anlagenart =
                anlagenartBox.getSelectedItem().toString();
            switchAnhangPanel(anh.getAnhangId(), anlagenart);
            switchBeschaffenheit(
                (String) getBeschaffenheitBox()
                    .getSelectedItem());

            addChangeListeners(
                getErstellDatDatum(),
                getAnhangIdFeld(),
                getAnwendungsbereichFeld(),
                getBezeichnungFeld(),
                getStillgelegtAmDatum(),
                getAbwaBeschaffOptFeld(),
                getBetriebsweiseBox(),
                getBefestFlaecheFeld(),
                getLProSFeld(),
                getQmProHFeld(),
                getQmProDFeld(),
                getQmProAFeld(),
                getBemerkungenArea());
        }
        setDirty(false);
    }

    // ── clearForm() ────────────────────────────────────────
    public void clearForm() {
        getErstellDatDatum().setDate(null);
        getAnhangIdFeld().setText(null);
        getAnwendungsbereichFeld().setText(null);
        getBezeichnungFeld().setText(null);
        getStillgelegtAmDatum().setDate(null);
        getAbwaBeschaffOptFeld().setText(null);
        getBetriebsweiseOptFeld().setText(null);
        getBefestFlaecheFeld().setText(null);
        getLProSFeld().setText(null);
        getQmProHFeld().setText(null);
        getQmProDFeld().setText(null);
        getQmProAFeld().setText(null);
        getBemerkungenArea().setText(null);

        if (anfallstelle != null
            && anfallstelle.getAnhangId() != null) {
            switch (anfallstelle.getAnhangId()) {
            case "40":
                getAnh40Tab().clearForm();
                break;
            case "49":
                getAnh49Tab().clearForm();
                getAnh49AnalyseTab().clearForm();
                break;
            case "50":
                getAnh50Tab().clearForm();
                break;
            case "52":
                getAnh52Tab().clearForm();
                break;
            case "55":
                getAnh55Tab().clearForm();
                break;
            case "56":
                getAnh56Tab().clearForm();
                break;
            case "99":
                if (anlagenart != null) {
                    switch ((String) anlagenart) {
                    case "Brennwertkessel":
                        getBWKTab().clearForm();
                        break;
                    case "BHKW":
                        getBWKTab().clearForm();
                        break;
                    case "Fettabscheider":
                        getAnh49Tab().clearForm();
                        getAnh49AbfuhrTab().clearForm();
                        break;
                    }
                }
            default:
                log.debug("Unknown Anfallstelle: "
                    + anfallstelle);
            }
        }
        setDirty(false);
    }

    // ── enableAll() ────────────────────────────────────────
    public void enableAll(boolean enabled) {
        getErstellDatDatum().setEnabled(enabled);
        getAnhangIdFeld().setEnabled(enabled);
        getHerkunftBox().setEnabled(enabled);
        getAnwendungsbereichFeld().setEnabled(enabled);
        getBezeichnungFeld().setEnabled(enabled);
        getStillgelegtAmDatum().setEnabled(enabled);
        getAbwaBeschaffOptFeld().setEnabled(enabled);
        getBetriebsweiseOptFeld().setEnabled(enabled);
        getBefestFlaecheFeld().setEnabled(enabled);
        getLProSFeld().setEnabled(enabled);
        getQmProHFeld().setEnabled(enabled);
        getQmProDFeld().setEnabled(enabled);
        getQmProAFeld().setEnabled(enabled);
        getBemerkungenArea().setEnabled(enabled);
    }

    // ── getName() ──────────────────────────────────────────
    @Override
    public String getName() {
        return this.name;
    }

    // ── doSavePanelData() ──────────────────────────────────
    @Override
    protected boolean doSavePanelData() {
        boolean success;

        this.anfallstelle.setAktualDat(new Date());
        Date erstellDat = this.erstellDatDatum.getDate();
        this.anfallstelle.setErstellDat(erstellDat);

        anhang = (Anhang) this.anhangBox.getSelectedItem();
        if (getAnhangBox().getSelectedItem() == null)
            this.anfallstelle.setAnhangId(null);
        else
            this.anfallstelle.setAnhangId(
                anhang.getAnhangId());

        this.anfallstelle.setAbwaBeschaffOpt(
            Anfallstelle.getBeschaffenheitIdFromDescription(
                (String) getBeschaffenheitBox()
                    .getSelectedItem()));
        this.anfallstelle.setBetriebsweiseOpt(
            Anfallstelle.getBetriebsweiseIdFromDescription(
                (String) getBetriebsweiseBox()
                    .getSelectedItem()));

        if (getAnlagenartBox().getSelectedItem() == null)
            this.anfallstelle.setAnlagenart(null);
        else
            this.anfallstelle.setAnlagenart(
                (String) getAnlagenartBox().getSelectedItem());

        String anwendungsbereich =
            this.anwendungsbereichFeld.getText();
        this.anfallstelle.setAnwendungsbereich(
            "".equals(anwendungsbereich)
                ? null : anwendungsbereich);

        String bezeichnung = this.bezeichnungFeld.getText();
        this.anfallstelle.setBezeichnung(
            "".equals(bezeichnung) ? null : bezeichnung);

        Integer qmproa =
            ((IntegerField) this.qmProAFeld).getIntValue();
        this.anfallstelle.setVolJahr(qmproa);

        Float maxVolH =
            ((DoubleField) getQmProHFeld()).getFloatValue();
        this.anfallstelle.setMaxVolStunde(maxVolH);

        Float maxVolD =
            ((DoubleField) getQmProDFeld()).getFloatValue();
        this.anfallstelle.setMaxVolTag(maxVolD);

        Date stillgelegtAm = this.stillgelegtAmDatum.getDate();
        this.anfallstelle.setStillgelegtAm(stillgelegtAm);

        String bemerkungen = this.bemerkungenArea.getText();
        this.anfallstelle.setBemerkungen(
            "".equals(bemerkungen) ? null : bemerkungen);

        success = this.anfallstelle.merge();
        if (success) {
            log.debug("Anfallstelle "
                + this.anfallstelle.getObjekt()
                    .getBetreiberid().getName()
                + " gespeichert.");
        } else {
            log.debug("Anfallstelle " + this.anfallstelle
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    // ── completeObjekt() ───────────────────────────────────
    public void completeObjekt() {
        if (this.hauptModul.isNew()
            || this.anfallstelle == null) {
            this.anfallstelle = new Anfallstelle();
            this.anfallstelle.setAnhangId("99");
            this.anfallstelle.setErstellDat(new Date());
            this.anfallstelle.setObjekt(
                this.hauptModul.getObjekt());
            this.anfallstelle.merge();
            log.debug("Neue Anfallstelle "
                + this.anfallstelle + " gespeichert.");
        }
    }

    // ── Getter Methoden ────────────────────────────────────
    private TextFieldDateChooser getErstellDatDatum() {
        if (this.erstellDatDatum == null)
            this.erstellDatDatum = new TextFieldDateChooser();
        return this.erstellDatDatum;
    }

    private JComboBox<String> getBetriebsweiseBox() {
        if (this.betriebsweiseBox == null)
            this.betriebsweiseBox = new JComboBox<>();
        return betriebsweiseBox;
    }

    private JComboBox<String> getBeschaffenheitBox() {
        if (this.beschaffenheitBox == null)
            this.beschaffenheitBox = new JComboBox<>();
        return beschaffenheitBox;
    }

    private JComboBox<Anhang> getAnhangBox() {
        if (this.anhangBox == null)
            this.anhangBox = new JComboBox<>();
        return anhangBox;
    }

    private JComboBox<String> getAnlagenartBox() {
        if (this.anlagenartBox == null) {
            this.anlagenartBox = new JComboBox<>();
            this.anlagenartBox.setEditable(false);
        }
        return anlagenartBox;
    }

    private JTextField getAnhangIdFeld() {
        if (this.anhangIdFeld == null)
            this.anhangIdFeld = new LimitedTextField(50);
        return this.anhangIdFeld;
    }

    private JComboBox<String> getHerkunftBox() {
        if (this.herkunftBox == null) {
            this.herkunftBox = new JComboBox<>();
            this.herkunftBox.setEditable(false);
        }
        return herkunftBox;
    }

    private JTextField getAnwendungsbereichFeld() {
        if (this.anwendungsbereichFeld == null)
            this.anwendungsbereichFeld =
                new LimitedTextField(50);
        return this.anwendungsbereichFeld;
    }

    private IntegerField getBefestFlaecheFeld() {
        if (this.befestFlaecheFeld == null)
            this.befestFlaecheFeld = new IntegerField();
        return this.befestFlaecheFeld;
    }

    private JFormattedTextField getLProSFeld() {
        if (this.lProSFeld == null)
            this.lProSFeld = new DoubleField(1);
        return this.lProSFeld;
    }

    private JFormattedTextField getQmProHFeld() {
        if (this.qmProHFeld == null)
            this.qmProHFeld = new DoubleField(1);
        return this.qmProHFeld;
    }

    private JFormattedTextField getQmProDFeld() {
        if (this.qmProDFeld == null)
            this.qmProDFeld = new DoubleField(1);
        return this.qmProDFeld;
    }

    private IntegerField getQmProAFeld() {
        if (this.qmProAFeld == null)
            this.qmProAFeld = new IntegerField();
        return this.qmProAFeld;
    }

    private JTextField getBezeichnungFeld() {
        if (this.bezeichnungFeld == null)
            this.bezeichnungFeld = new JFormattedTextField();
        return this.bezeichnungFeld;
    }

    private TextFieldDateChooser getStillgelegtAmDatum() {
        if (this.stillgelegtAmDatum == null)
            this.stillgelegtAmDatum =
                new TextFieldDateChooser();
        return this.stillgelegtAmDatum;
    }

    private JFormattedTextField getAbwaBeschaffOptFeld() {
        if (this.abwaBeschaffOptFeld == null)
            this.abwaBeschaffOptFeld = new IntegerField();
        return this.abwaBeschaffOptFeld;
    }

    private JFormattedTextField getBetriebsweiseOptFeld() {
        if (this.betriebsweiseOptFeld == null)
            this.betriebsweiseOptFeld = new IntegerField();
        return this.betriebsweiseOptFeld;
    }

    private JTextArea getBemerkungenArea() {
        if (this.bemerkungenArea == null) {
            this.bemerkungenArea = new LimitedTextArea(150);
            this.bemerkungenArea.setLineWrap(true);
            this.bemerkungenArea.setWrapStyleWord(true);
        }
        return this.bemerkungenArea;
    }

    // ── getSaveAnfallstelleButton() ────────────────────────
    private JButton getSaveAnfallstelleButton() {
        if (this.saveAnfallstelleButton == null) {
            this.saveAnfallstelleButton =
                new JButton("Speichern");
            this.saveAnfallstelleButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableAll(false);
                        if (hauptModul.saveAllTabs()) {
                            AnfallstellePanel.this.hauptModul
                                .getFrame().changeStatus(
                                "Anfallstelle "
                                + AnfallstellePanel.this
                                    .anfallstelle.getId()
                                + " erfolgreich"
                                + " gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                        } else {
                            AnfallstellePanel.this.hauptModul
                                .getFrame().changeStatus(
                                "Fehler beim Speichern"
                                + " der Anfallstelle!",
                                HauptFrame.ERROR_COLOR);
                        }
                        AnfallstellePanel.this.hauptModul
                            .fillForm();
                    }
                });
        }
        return this.saveAnfallstelleButton;
    }

    // ── getObjektverknuepungTabelle() ──────────────────────
    private JTable getObjektverknuepungTabelle() {
        if (this.objektVerknuepfungModel == null) {
            this.objektVerknuepfungModel =
                new ObjektVerknuepfungModel(
                    this.hauptModul.getObjekt());
            if (this.objektverknuepfungTabelle == null) {
                this.objektverknuepfungTabelle =
                    new JTable(this.objektVerknuepfungModel);
            } else {
                this.objektverknuepfungTabelle.setModel(
                    this.objektVerknuepfungModel);
            }
            this.objektverknuepfungTabelle.getColumnModel()
                .getColumn(0).setPreferredWidth(5);
            this.objektverknuepfungTabelle.getColumnModel()
                .getColumn(1).setPreferredWidth(100);
            this.objektverknuepfungTabelle.getColumnModel()
                .getColumn(2).setPreferredWidth(250);
            this.objektverknuepfungTabelle.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(
                        java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2)
                            && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row =
                                getObjektverknuepungTabelle()
                                .rowAtPoint(origin);
                            if (row != -1) {
                                Objektverknuepfung obj =
                                    AnfallstellePanel.this
                                    .objektVerknuepfungModel
                                    .getRow(row);
                                if (obj
                                    .getObjektByIstVerknuepftMit()
                                    .getId().intValue()
                                    != AnfallstellePanel.this
                                        .hauptModul.getObjekt()
                                        .getId().intValue())
                                    AnfallstellePanel.this
                                        .hauptModul.getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                        "auik.imc.edit_object",
                                        obj.getObjektByIstVerknuepftMit()
                                            .getId().intValue(),
                                        false);
                                else
                                    AnfallstellePanel.this
                                        .hauptModul.getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                        "auik.imc.edit_object",
                                        obj.getObjektByObjekt()
                                            .getId().intValue(),
                                        false);
                                AnfallstellePanel.this
                                    .hauptModul.getManager()
                                    .switchModul(
                                    "m_objekt_bearbeiten");
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
                (KeyStroke) getVerknuepfungLoeschAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getVerknuepfungLoeschAction()
                    .getValue(Action.NAME));
            this.objektverknuepfungTabelle.getActionMap().put(
                getVerknuepfungLoeschAction()
                    .getValue(Action.NAME),
                getVerknuepfungLoeschAction());
        }
        return this.objektverknuepfungTabelle;
    }

    // ── showVerknuepfungPopup() ────────────────────────────
    private void showVerknuepfungPopup(MouseEvent e) {
        if (this.verknuepfungPopup == null) {
            this.verknuepfungPopup = new JPopupMenu("Objekt");
            this.verknuepfungPopup.add(new JMenuItem(
                getVerknuepfungLoeschAction()));
        }
        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektverknuepfungTabelle
                .rowAtPoint(origin);
            if (row != -1) {
                this.objektverknuepfungTabelle
                    .setRowSelectionInterval(row, row);
                this.verknuepfungPopup.show(
                    e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    // ── getVerknuepfungLoeschAction() ──────────────────────
    private Action getVerknuepfungLoeschAction() {
        if (this.verknuepfungLoeschAction == null) {
            this.verknuepfungLoeschAction =
                new AbstractAction("Löschen") {
                private static final long serialVersionUID =
                    2886609709202711593L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle()
                        .getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle()
                            .getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung =
                            AnfallstellePanel.this
                            .objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance()
                            .showQuestion(
                            "Soll die Verknüpfung wirklich"
                            + " gelöscht werden?\n"
                            + "Hinweis: Die Aktion betrifft"
                            + " nur die Verknüpfung, die"
                            + " Objekte bleiben erhalten"
                            + " und können jederzeit neu"
                            + " verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (AnfallstellePanel.this
                                .objektVerknuepfungModel
                                .removeRow(row)) {
                                AnfallstellePanel.this
                                    .hauptModul.getFrame()
                                    .changeStatus(
                                    "Objekt gelöscht.",
                                    HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt "
                                    + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                AnfallstellePanel.this
                                    .hauptModul.getFrame()
                                    .changeStatus(
                                    "Konnte das Objekt"
                                    + " nicht löschen!",
                                    HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.verknuepfungLoeschAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_L));
            this.verknuepfungLoeschAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(
                    KeyEvent.VK_DELETE, 0, false));
        }
        return this.verknuepfungLoeschAction;
    }

    // ── getSelectObjektButton() ────────────────────────────
    private JButton getSelectObjektButton() {
        if (this.selectObjektButton == null) {
            this.selectObjektButton =
                new JButton("Objekt auswählen");
            this.selectObjektButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ObjektChooser chooser =
                            new ObjektChooser(
                            AnfallstellePanel.this.hauptModul
                                .getFrame(),
                            AnfallstellePanel.this.anfallstelle
                                .getObjekt(),
                            AnfallstellePanel.this
                                .objektVerknuepfungModel);
                        chooser.setVisible(true);
                    }
                });
        }
        return this.selectObjektButton;
    }

    // ── switchAnhangPanel() ────────────────────────────────
    public void switchAnhangPanel(String type,
        String anlagenart) {
        if (type == null) return;

        int i = hauptModul.getTabCount();
        if (i > 5) hauptModul.removeTabAt(5);
        if (i > 4) hauptModul.removeTabAt(4);
        if (i > 3) hauptModul.removeTabAt(3);

        this.anlagenartBox.setEnabled(false);

        switch ((String) type) {
        case "40":
            hauptModul.addTab(
                getAnh40Tab().getName(), getAnh40Tab());
            if (anfallstelle.getAnh40Fachdatens().size() > 0) {
                getAnh40Tab().enableAll(true);
                getAnh40Tab().updateForm(anfallstelle);
            } else {
                anfallstelle.setAnhangId("40");
                getAnh40Tab().clearForm();
                getAnh40Tab().completeObjekt(anfallstelle);
                hauptModul.setSelectedIndex(3);
            }
            break;
        case "49":
            hauptModul.addTab(
                getAnh49Tab().getName(), getAnh49Tab());
            hauptModul.addTab(
                getAnh49AnalyseTab().getName(),
                getAnh49AnalyseTab());
            if (anfallstelle.getAnh49Fachdatens().size() > 0) {
                getAnh49Tab().enableAll(true);
                getAnh49Tab().updateForm(anfallstelle);
                getAnh49AnalyseTab().clearForm();
                getAnh49AnalyseTab().updateForm(
                    getAnh49Tab().getFachdaten());
            } else {
                anfallstelle.setAnhangId("49");
                getAnh49Tab().clearForm();
                getAnh49Tab().completeObjekt(anfallstelle);
                getAnh49AnalyseTab().clearForm();
                hauptModul.setSelectedIndex(3);
            }
            break;
        case "50":
            hauptModul.addTab(
                getAnh50Tab().getName(), getAnh50Tab());
            if (anfallstelle.getAnh50Fachdatens().size() > 0) {
                getAnh50Tab().enableAll(true);
                getAnh50Tab().fetchFormData();
                getAnh50Tab().updateForm(anfallstelle);
            } else {
                anfallstelle.setAnhangId("50");
                getAnh50Tab().clearForm();
                getAnh50Tab().completeObjekt(anfallstelle);
                hauptModul.setSelectedIndex(3);
            }
            break;
        case "52":
            hauptModul.addTab(
                getAnh52Tab().getName(), getAnh52Tab());
            if (anfallstelle.getAnh52Fachdatens().size() > 0) {
                getAnh52Tab().enableAll(true);
                getAnh52Tab().updateForm(anfallstelle);
            } else {
                anfallstelle.setAnhangId("52");
                getAnh52Tab().clearForm();
                getAnh52Tab().completeObjekt(anfallstelle);
                hauptModul.setSelectedIndex(3);
            }
            break;
        case "55":
            hauptModul.addTab(
                getAnh55Tab().getName(), getAnh55Tab());
            if (anfallstelle.getAnh55Fachdatens().size() > 0) {
                getAnh55Tab().enableAll(true);
                getAnh55Tab().updateForm(anfallstelle);
            } else {
                anfallstelle.setAnhangId("55");
                getAnh55Tab().clearForm();
                getAnh55Tab().completeObjekt(anfallstelle);
                hauptModul.setSelectedIndex(3);
            }
            break;
        case "56":
            hauptModul.addTab(
                getAnh56Tab().getName(), getAnh56Tab());
            if (anfallstelle.getAnh56Fachdatens().size() > 0) {
                getAnh56Tab().enableAll(true);
                getAnh56Tab().updateForm(anfallstelle);
            } else {
                anfallstelle.setAnhangId("56");
                getAnh56Tab().clearForm();
                getAnh56Tab().completeObjekt(anfallstelle);
                hauptModul.setSelectedIndex(3);
            }
            break;
        case "99":
            this.anlagenartBox.setEnabled(true);
            switch ((String) anlagenart) {
            case "Brennwertkessel":
                hauptModul.addTab(
                    getBWKTab().getName(), getBWKTab());
                if (anfallstelle.getBwkFachdatens()
                    .size() > 0) {
                    getBWKTab().enableAll(true);
                    getBWKTab().updateForm(anfallstelle);
                } else {
                    anfallstelle.setAnhangId("99");
                    anfallstelle.setAnlagenart(
                        "Brennwertkessel");
                    getBWKTab().clearForm();
                    getBWKTab().completeObjekt(anfallstelle);
                    hauptModul.setSelectedIndex(3);
                }
                break;
            case "Blockheizkraftwerk":
                hauptModul.addTab(
                    "Blockheizkraftwerk", getBWKTab());
                if (anfallstelle.getBwkFachdatens()
                    .size() > 0) {
                    getBWKTab().enableAll(true);
                    getBWKTab().updateForm(anfallstelle);
                } else {
                    anfallstelle.setAnhangId("99");
                    anfallstelle.setAnlagenart(
                        "Blockheizkraftwerk");
                    getBWKTab().clearForm();
                    getBWKTab().completeObjekt(anfallstelle);
                    hauptModul.setSelectedIndex(3);
                }
                break;
            case "Fettabscheider":
                hauptModul.addTab(
                    "Fettabscheider", getAnh49Tab());
                hauptModul.addTab(
                    getAnh49AbfuhrTab().getName(),
                    getAnh49AbfuhrTab());
                if (anfallstelle.getAnh49Fachdatens()
                    .size() > 0) {
                    getAnh49Tab().enableAll(true);
                    getAnh49Tab().updateForm(anfallstelle);
                    getAnh49AbfuhrTab().clearForm();
                    getAnh49AbfuhrTab().updateForm(
                        getAnh49Tab().getFachdaten());
                } else {
                    anfallstelle.setAnhangId("99");
                    anfallstelle.setAnlagenart(
                        "Fettabscheider");
                    getAnh49Tab().clearForm();
                    getAnh49Tab().enableAll(true);
                    getAnh49Tab().completeObjekt(anfallstelle);
                    hauptModul.setSelectedIndex(3);
                }
                break;
            case "Abscheider":
                hauptModul.addTab(
                    "Abscheider", getAnh49Tab());
                hauptModul.addTab(
                    getAnh49AbfuhrTab().getName(),
                    getAnh49AbfuhrTab());
                if (anfallstelle.getAnh49Fachdatens()
                    .size() > 0) {
                    getAnh49Tab().fetchFormData();
                    getAnh49Tab().enableAll(true);
                    getAnh49Tab().updateForm(anfallstelle);
                    getAnh49AbfuhrTab().clearForm();
                    getAnh49AbfuhrTab().updateForm(
                        getAnh49Tab().getFachdaten());
                } else {
                    anfallstelle.setAnhangId("99");
                    anfallstelle.setAnlagenart("Abscheider");
                    getBWKTab().clearForm();
                    getAnh49Tab().completeObjekt(anfallstelle);
                    hauptModul.setSelectedIndex(3);
                }
                break;
            }
        }
    }

    // ── switchBeschaffenheit() ─────────────────────────────
    public void switchBeschaffenheit(String type) {
        if (type == null) return;

        boolean isNiederschlag =
            type.equals(
                "Niederschlagswasser mit Sonderbauwerk")
            || type.equals(
                "Niederschlagswasser ohne Sonderbauwerk");

        anhangLb.setVisible(!isNiederschlag);
        getAnhangBox().setVisible(!isNiederschlag);
        anlagenartLb.setVisible(!isNiederschlag);
        getAnlagenartBox().setVisible(!isNiederschlag);
        anwendungsbereichLb.setVisible(!isNiederschlag);
        getAnwendungsbereichFeld().setVisible(!isNiederschlag);

        herkunftsbereichLb.setVisible(isNiederschlag);
        getHerkunftBox().setVisible(isNiederschlag);
        befestFlaecheLb.setVisible(isNiederschlag);
        getBefestFlaecheFeld().setVisible(isNiederschlag);
        maxAbflussmengeLb.setVisible(isNiederschlag);
        getLProSFeld().setVisible(isNiederschlag);
        lProSLb.setVisible(isNiederschlag);
        getQmProHFeld().setVisible(isNiederschlag);
        qmProHLb.setVisible(isNiederschlag);
        getQmProDFeld().setVisible(isNiederschlag);
        qmProDLb.setVisible(isNiederschlag);
        getQmProAFeld().setVisible(isNiederschlag);
        abwasserProALb.setVisible(isNiederschlag);
    }

    // ── Tab Getter ─────────────────────────────────────────
    public Anh40Panel getAnh40Tab() {
        if (anhang40Tab == null)
            anhang40Tab = new Anh40Panel(hauptModul);
        return anhang40Tab;
    }

    public Anh49Panel getAnh49Tab() {
        if (anhang49Tab == null)
            anhang49Tab = new Anh49Panel(hauptModul);
        return anhang49Tab;
    }

    public Anh49AnalysenPanel getAnh49AnalyseTab() {
        if (anh49analyseTab == null)
            anh49analyseTab =
                new Anh49AnalysenPanel(hauptModul);
        return anh49analyseTab;
    }

    public Anh49AbfuhrenPanel getAnh49AbfuhrTab() {
        if (anh49abfuhrTab == null)
            anh49abfuhrTab =
                new Anh49AbfuhrenPanel(hauptModul);
        return anh49abfuhrTab;
    }

    public Anh50Panel getAnh50Tab() {
        if (anhang50Tab == null)
            anhang50Tab =
                new Anh50Panel(hauptModul, anfallstelle);
        return anhang50Tab;
    }

    public Anh52Panel getAnh52Tab() {
        if (anhang52Tab == null)
            anhang52Tab =
                new Anh52Panel(hauptModul, anfallstelle);
        return anhang52Tab;
    }

    public Anh55Panel getAnh55Tab() {
        if (anhang55Tab == null)
            anhang55Tab =
                new Anh55Panel(hauptModul, anfallstelle);
        return anhang55Tab;
    }

    public Anh56Panel getAnh56Tab() {
        if (anhang56Tab == null)
            anhang56Tab =
                new Anh56Panel(hauptModul, anfallstelle);
        return anhang56Tab;
    }

    public BWKPanel getBWKTab() {
        if (bwkTab == null)
            bwkTab = new BWKPanel(hauptModul, anfallstelle);
        return bwkTab;
    }
}