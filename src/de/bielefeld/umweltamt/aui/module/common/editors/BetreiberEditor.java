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
package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.mappings.basis.Wirtschaftszweig;
import de.bielefeld.umweltamt.aui.module.common.AdresseChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStdModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisTabStreetsModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.LongNameComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Dialog zum Bearbeiten eines Betreibers.
 * @author David Klotz
 */
public class BetreiberEditor extends AbstractApplyEditor {
    private static final long serialVersionUID = -7058333439142990179L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private Adresse adresse = null;
    private Inhaber inhaber = null;

    private JLabel handzeichenLabel;
    private JLabel namenLabel;
    private JLabel kassenzeichenLabel;

    private JTextField anredeFeld;
    private JTextField vornamenFeld;
    private JTextField namenFeld;
    private JTextField nameZusFeld;
    private JTextField kassenzeichenFeld;
    private JTextField strasseFeld;
    private JFormattedTextField hausnrFeld;
    private JTextField hausnrZusFeld;
    private JTextField plzZsFeld;
    private JTextField plzFeld;
    private JTextField ortFeld;
    private JTextField telefonFeld;
    private JTextField telefaxFeld;
    private JTextField emailFeld;
    private JTextField betrBeaufVornameFeld;
    private JTextField betrBeaufNachnameFeld;
    private JTextField revdatumsFeld;
    private JTextField handzeichenAltFeld;
    private JTextField handzeichenNeuFeld;
    private JTextField flurFeld;
    private JTextField flurStkFeld;
    private JFormattedTextField wassermengeFeld;
    private JTextArea bemerkungsArea;

    private JCheckBox daten_awsvCheck;
    private JCheckBox daten_esatzungCheck;
    private JCheckBox daten_whgCheck;
    private JCheckBox ueberschgebCheck;

    private JComboBox<String> strassenBox;
    private JComboBox<Wirtschaftszweig> wirtschaftszweigBox;
    private JComboBox<Gemarkung> gemarkungBox;
    private JComboBox<String> entwGebBox;
    private JComboBox<Standortgghwsg> standortGgBox;
    private JComboBox<Wassereinzugsgebiet> wEinzugsGebBox;

    private Gemarkung[] gemarkungen = null;
    private String[] entwgebiete = null;
    private Standortgghwsg[] standortggs = null;
    private Wassereinzugsgebiet[] wEinzugsgebiete = null;
    private Wirtschaftszweig[] wirtschaftszweige = null;
    private String[] tabstreets = null;

    private Action standortLoeschAction;
    private Action standortNeuAction;
    private JPopupMenu standortPopup;

    private JTable adressenTabelle;
    private BasisTabStreetsModel adressenModel;
    private JTable standorteTabelle;
    private BasisStdModel standorteModel;

    // ── Konstruktoren ──────────────────────────────────────
    public BetreiberEditor(Inhaber betreiber, HauptFrame owner) {
        super("Betreiber (" + betreiber.toString() + ")", betreiber, owner);
        this.inhaber = betreiber;
        this.adresse = betreiber.getAdresse();
    }

    public BetreiberEditor(Standort standort, Inhaber inhaber, HauptFrame owner) {
        super("Standort (" + standort.toString() + ")", standort, owner);
        this.inhaber = inhaber;
        this.adresse = inhaber.getAdresse();
    }

    // ── Separator Hilfsmethode ─────────────────────────────
    private JPanel createSeparator(String title) {
        JPanel sepPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel lbl = new JLabel(title);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
        sepPanel.add(lbl, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0);
        sepPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        return sepPanel;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected JComponent buildContentArea() {
        ResourceBundle i18n = SettingsManager.getInstance().getI18nBundle();

        // ── Felder initialisieren ──────────────────────────
        anredeFeld           = new LimitedTextField(100);
        vornamenFeld         = new LimitedTextField(100);
        namenFeld            = new LimitedTextField(100);
        nameZusFeld          = new LimitedTextField(50);
        kassenzeichenFeld    = new LimitedTextField(50);
        hausnrFeld           = new IntegerField();
        hausnrFeld.setEnabled(false);
        hausnrZusFeld        = new LimitedTextField(10);
        hausnrZusFeld.setEnabled(false);
        plzZsFeld            = new LimitedTextField(3, "");
        plzZsFeld.setEnabled(false);
        plzFeld              = new JTextField();
        plzFeld.setEnabled(false);
        strasseFeld          = new JTextField();
        strasseFeld.setEnabled(false);
        ortFeld              = new JTextField();
        ortFeld.setEnabled(false);
        telefonFeld          = new LimitedTextField(50);
        telefaxFeld          = new LimitedTextField(50);
        emailFeld            = new LimitedTextField(50);
        wassermengeFeld      = new IntegerField();
        betrBeaufVornameFeld = new LimitedTextField(50);
        betrBeaufNachnameFeld= new LimitedTextField(50);

        daten_awsvCheck    = new JCheckBox(i18n.getString("editor.betreiber.awsv"));
        daten_esatzungCheck= new JCheckBox(i18n.getString("editor.betreiber.eSatzung"));
        daten_whgCheck     = new JCheckBox(i18n.getString("editor.betreiber.whg"));
        ueberschgebCheck   = new JCheckBox(i18n.getString("editor.betreiber.uberschwemmGeb"));

        revdatumsFeld = new JTextField(8);
        revdatumsFeld.setEditable(false);
        revdatumsFeld.setFocusable(false);
        revdatumsFeld.setToolTipText(i18n.getString("tooltip.autoSet"));

        handzeichenAltFeld = new LimitedTextField(10, "");
        handzeichenAltFeld.setEditable(false);
        handzeichenAltFeld.setFocusable(false);

        handzeichenNeuFeld = new LimitedTextField(10, "");
        handzeichenNeuFeld.setToolTipText(i18n.getString("tooltip.sig_mandatory"));

        flurFeld    = new LimitedTextField(50);
        flurStkFeld = new LimitedTextField(50);

        gemarkungBox   = new JComboBox<Gemarkung>();
        entwGebBox     = new JComboBox<String>();
        standortGgBox  = new JComboBox<Standortgghwsg>();
        wEinzugsGebBox = new JComboBox<Wassereinzugsgebiet>();

        bemerkungsArea = new LimitedTextArea(2000);
        bemerkungsArea.setLineWrap(true);
        bemerkungsArea.setWrapStyleWord(true);
        JScrollPane bemerkungsScroller = new JScrollPane(
            bemerkungsArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        wirtschaftszweigBox = new JComboBox<Wirtschaftszweig>();
        wirtschaftszweigBox.setRenderer(new LongNameComboBoxRenderer());

        // ── KeyListener ────────────────────────────────────
        handzeichenNeuFeld.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getSource().equals(handzeichenNeuFeld)) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (handzeichenNeuFeld.getText().equals("")) {
                            handzeichenLabel.setForeground(Color.RED);
                            handzeichenNeuFeld.requestFocus();
                        }
                    }
                }
            }
        });

        bemerkungsScroller.getVerticalScrollBar().setFocusable(false);
        bemerkungsScroller.getHorizontalScrollBar().setFocusable(false);

        // ══════════════════════════════════════════════════
        // Linkes Panel: Inhaber (schmaler, ~45%)
        // ══════════════════════════════════════════════════
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints lc = new GridBagConstraints();
        lc.insets = new Insets(1, 2, 1, 2);

        // SEPARATOR: Inhaber
        lc.gridx = 0; lc.gridy = 0;
        lc.gridwidth = 4; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(createSeparator(i18n.getString("editor.betreiber.separator.owner")), lc);

        // Firma/Name
        lc.gridy = 1; lc.gridwidth = 1;
        lc.weightx = 0; lc.fill = GridBagConstraints.NONE;
        lc.anchor = GridBagConstraints.EAST;
        namenLabel = new JLabel(i18n.getString("editor.betreiber.company_name"));
        leftPanel.add(namenLabel, lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(namenFeld, lc);

        // Anrede
        lc.gridx = 0; lc.gridy = 2;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.salutation")), lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(anredeFeld, lc);

        // Vorname
        lc.gridx = 0; lc.gridy = 3;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.first_name")), lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(vornamenFeld, lc);

        // Zusatz
        lc.gridx = 0; lc.gridy = 4;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.addition")), lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(nameZusFeld, lc);

        // Wirtschaftszweig
        lc.gridx = 0; lc.gridy = 5;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.industry")), lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(wirtschaftszweigBox, lc);

        // Kassenzeichen
        lc.gridx = 0; lc.gridy = 6;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        kassenzeichenLabel = new JLabel(i18n.getString("editor.betreiber.ref_number"));
        leftPanel.add(kassenzeichenLabel, lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(kassenzeichenFeld, lc);

        // SEPARATOR: Adresse
        lc.gridx = 0; lc.gridy = 7;
        lc.gridwidth = 4; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(createSeparator(i18n.getString("editor.betreiber.separator.address")), lc);

     // Straße + Hausnr + HausnrZus
        lc.gridx = 0; lc.gridy = 8;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE;
        lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString(
            "editor.betreiber.street")), lc);

        JPanel strassePanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 2, 0));
        strasseFeld.setPreferredSize(new java.awt.Dimension(150,
            strasseFeld.getPreferredSize().height));
        hausnrFeld.setPreferredSize(new java.awt.Dimension(45,
            hausnrFeld.getPreferredSize().height));
        hausnrZusFeld.setPreferredSize(new java.awt.Dimension(35,
            hausnrZusFeld.getPreferredSize().height));
        strassePanel.add(strasseFeld);
        strassePanel.add(hausnrFeld);
        strassePanel.add(hausnrZusFeld);
        lc.gridx = 1; lc.gridy = 8;
        lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        lc.anchor = GridBagConstraints.WEST;
        leftPanel.add(strassePanel, lc);
        lc.gridwidth = 1;

        // Stadt: PLZ-Zus + PLZ + Ort
        lc.gridx = 0; lc.gridy = 9;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE;
        lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString(
            "editor.betreiber.city")), lc);

        JPanel stadtPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 2, 0));
        plzZsFeld.setPreferredSize(new java.awt.Dimension(22,
            plzZsFeld.getPreferredSize().height));
        plzFeld.setPreferredSize(new java.awt.Dimension(55,
            plzFeld.getPreferredSize().height));
        ortFeld.setPreferredSize(new java.awt.Dimension(150,
            ortFeld.getPreferredSize().height));
        stadtPanel.add(plzZsFeld);
        stadtPanel.add(plzFeld);
        stadtPanel.add(ortFeld);
        lc.gridx = 1; lc.gridy = 9;
        lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        lc.anchor = GridBagConstraints.WEST;
        leftPanel.add(stadtPanel, lc);
        lc.gridwidth = 1;

        // Standortgegebenheit
        lc.gridx = 0; lc.gridy = 10;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.location_cond")), lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(standortGgBox, lc);

        // W. Einzugsgebiet
        lc.gridx = 0; lc.gridy = 11;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.area")), lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(wEinzugsGebBox, lc);

        // Entwässerungsgebiet + Überschwemmungsgebiet
        lc.gridx = 0; lc.gridy = 12;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.drain_area")), lc);
        lc.gridx = 1; lc.gridwidth = 1; lc.weightx = 0.4;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(entwGebBox, lc);
        lc.gridx = 2; lc.gridwidth = 2; lc.weightx = 0.6;
        leftPanel.add(ueberschgebCheck, lc);

        // Gemarkung
        lc.gridx = 0; lc.gridy = 13;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.marking")), lc);
        lc.gridx = 1; lc.gridwidth = 3; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(gemarkungBox, lc);

        // Flur/Flurstücke
        lc.gridx = 0; lc.gridy = 14;
        lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE; lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(i18n.getString("editor.betreiber.parcel")), lc);
        lc.gridx = 1; lc.gridwidth = 1; lc.weightx = 0.35;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(flurFeld, lc);
        lc.gridx = 2; lc.gridwidth = 2; lc.weightx = 0.65;
        leftPanel.add(flurStkFeld, lc);

        // ══════════════════════════════════════════════════
        // Rechtes Panel: Ansprechpartner + auswählen (55%)
        // ══════════════════════════════════════════════════
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rc = new GridBagConstraints();
        rc.insets = new Insets(1, 2, 1, 2);

        // SEPARATOR: Ansprechpartner
        rc.gridx = 0; rc.gridy = 0;
        rc.gridwidth = 3; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(createSeparator(i18n.getString("editor.betreiber.separator.contact")), rc);

        // Vorname
        rc.gridy = 1; rc.gridwidth = 1; rc.weightx = 0;
        rc.fill = GridBagConstraints.NONE; rc.anchor = GridBagConstraints.EAST;
        rightPanel.add(new JLabel(i18n.getString("editor.betreiber.contact_first_name")), rc);
        rc.gridx = 1; rc.gridwidth = 2; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(betrBeaufVornameFeld, rc);

        // Nachname
        rc.gridx = 0; rc.gridy = 2;
        rc.gridwidth = 1; rc.weightx = 0;
        rc.fill = GridBagConstraints.NONE; rc.anchor = GridBagConstraints.EAST;
        rightPanel.add(new JLabel(i18n.getString("editor.betreiber.contact_last_name")), rc);
        rc.gridx = 1; rc.gridwidth = 2; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(betrBeaufNachnameFeld, rc);

        // Telefon
        rc.gridx = 0; rc.gridy = 3;
        rc.gridwidth = 1; rc.weightx = 0;
        rc.fill = GridBagConstraints.NONE; rc.anchor = GridBagConstraints.EAST;
        rightPanel.add(new JLabel(i18n.getString("editor.betreiber.telephone")), rc);
        rc.gridx = 1; rc.gridwidth = 2; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(telefonFeld, rc);

        // Telefax
        rc.gridx = 0; rc.gridy = 4;
        rc.gridwidth = 1; rc.weightx = 0;
        rc.fill = GridBagConstraints.NONE; rc.anchor = GridBagConstraints.EAST;
        rightPanel.add(new JLabel(i18n.getString("editor.betreiber.fax")), rc);
        rc.gridx = 1; rc.gridwidth = 2; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(telefaxFeld, rc);

        // E-Mail
        rc.gridx = 0; rc.gridy = 5;
        rc.gridwidth = 1; rc.weightx = 0;
        rc.fill = GridBagConstraints.NONE; rc.anchor = GridBagConstraints.EAST;
        rightPanel.add(new JLabel(i18n.getString("editor.betreiber.mail")), rc);
        rc.gridx = 1; rc.gridwidth = 2; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(emailFeld, rc);

        // Wassermenge
        rc.gridx = 0; rc.gridy = 6;
        rc.gridwidth = 1; rc.weightx = 0;
        rc.fill = GridBagConstraints.NONE; rc.anchor = GridBagConstraints.EAST;
        rightPanel.add(new JLabel(i18n.getString("editor.betreiber.water_amount")), rc);
        rc.gridx = 1; rc.gridwidth = 2; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(wassermengeFeld, rc);

        // SEPARATOR: auswählen
        rc.gridx = 0; rc.gridy = 7;
        rc.gridwidth = 3; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(createSeparator(i18n.getString("label.select")), rc);

        // Strassenbox
        rc.gridy = 8; rc.gridwidth = 3; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(getStrassenBox(), rc);

        // Adressentabelle – etwas mehr Platz (weighty = 0.5)
        rc.gridy = 9; rc.gridwidth = 3;
        rc.gridheight = 6;
        rc.weightx = 1.0; rc.weighty = 0.5;
        rc.fill = GridBagConstraints.BOTH;
        rightPanel.add(getAdressenScroller(), rc);
        rc.gridheight = 1; rc.weighty = 0;

        // ══════════════════════════════════════════════════
        // Haupt Panel
        // ══════════════════════════════════════════════════
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints mc = new GridBagConstraints();
        mc.insets = new Insets(2, 3, 2, 3);

     //  linker und rechter Block gleich breit
        JPanel leftWrapper = new JPanel(new BorderLayout());
        leftWrapper.add(leftPanel, BorderLayout.NORTH);   // oben ausrichten
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.add(rightPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        topPanel.add(leftWrapper);
        topPanel.add(rightWrapper);

        mc.gridx = 0; mc.gridy = 0;
        mc.gridwidth = 2; mc.weightx = 1.0; mc.weighty = 0;
        mc.fill = GridBagConstraints.BOTH;
        mc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(topPanel, mc);
        mc.gridwidth = 1;

        // SEPARATOR: Standort / Lage
        mc.gridx = 0; mc.gridy = 1;
        mc.gridwidth = 2; mc.weightx = 1.0;
        mc.weighty = 0;
        mc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(createSeparator(i18n.getString("editor.betreiber.separator.location")), mc);

        // Standortetabelle – deutlich mehr Platz (weighty = 0.35)
        mc.gridy = 2; mc.gridwidth = 2;
        mc.weightx = 1.0; mc.weighty = 0.35;
        mc.fill = GridBagConstraints.BOTH;
        mainPanel.add(getStandorteScroller(), mc);
        mc.weighty = 0;

        // SEPARATOR: Datenschutzhinweis
        mc.gridy = 3; mc.gridwidth = 2;
        mc.weightx = 1.0; mc.weighty = 0;
        mc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(createSeparator(
            i18n.getString("editor.betreiber.separator.data_prot_notice")), mc);

        // DSGVO Checkboxen
        JPanel dsgvoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 2));
        dsgvoPanel.add(daten_awsvCheck);
        dsgvoPanel.add(daten_esatzungCheck);
        dsgvoPanel.add(daten_whgCheck);
        mc.gridy = 4; mc.gridwidth = 2;
        mc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(dsgvoPanel, mc);

        // SEPARATOR: Bemerkungen
        mc.gridy = 5; mc.gridwidth = 2;
        mc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(createSeparator(i18n.getString("editor.betreiber.remark")), mc);

        // Bemerkungen –
        mc.gridy = 6; mc.gridwidth = 2;
        mc.weightx = 1.0; mc.weighty = 0.2;
        mc.fill = GridBagConstraints.BOTH;
        mainPanel.add(bemerkungsScroller, mc);
        mc.weighty = 0;

        // ── Revision: Links + Rechts ───────────────────────
        // Revision Links
        JPanel revLeftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rlc = new GridBagConstraints();
        rlc.insets = new Insets(1, 2, 1, 2);

        rlc.gridx = 0; rlc.gridy = 0;
        rlc.gridwidth = 2; rlc.weightx = 1.0;
        rlc.fill = GridBagConstraints.HORIZONTAL;
        revLeftPanel.add(createSeparator(i18n.getString("editor.betreiber.last_rev")), rlc);

        // Datum
        rlc.gridy = 1; rlc.gridwidth = 1; rlc.weightx = 0;
        rlc.fill = GridBagConstraints.NONE; rlc.anchor = GridBagConstraints.EAST;
        revLeftPanel.add(new JLabel(i18n.getString("editor.betreiber.date")), rlc);
        rlc.gridx = 1; rlc.weightx = 1.0;
        rlc.fill = GridBagConstraints.NONE; rlc.anchor = GridBagConstraints.WEST;
        revdatumsFeld.setPreferredSize(new java.awt.Dimension(90, revdatumsFeld.getPreferredSize().height));
        revLeftPanel.add(revdatumsFeld, rlc);

        // Handzeichen (alt) – normales Label
        rlc.gridx = 0; rlc.gridy = 2; rlc.weightx = 0;
        rlc.fill = GridBagConstraints.NONE; rlc.anchor = GridBagConstraints.EAST;
        revLeftPanel.add(new JLabel(i18n.getString("editor.betreiber.sign")), rlc);
        rlc.gridx = 1; rlc.weightx = 1.0;
        rlc.fill = GridBagConstraints.NONE; rlc.anchor = GridBagConstraints.WEST;
        handzeichenAltFeld.setPreferredSize(new java.awt.Dimension(90, handzeichenAltFeld.getPreferredSize().height));
        revLeftPanel.add(handzeichenAltFeld, rlc);

        // Revision Rechts
        JPanel revRightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rrc = new GridBagConstraints();
        rrc.insets = new Insets(1, 2, 1, 2);

        rrc.gridx = 0; rrc.gridy = 0;
        rrc.gridwidth = 2; rrc.weightx = 1.0;
        rrc.fill = GridBagConstraints.HORIZONTAL;
        revRightPanel.add(createSeparator(i18n.getString("editor.betreiber.separator.new_rev")), rrc);

        // Handzeichen (neu) – dieses Label wird bei fehlender Eingabe rot
        rrc.gridy = 1; rrc.gridwidth = 1; rrc.weightx = 0;
        rrc.fill = GridBagConstraints.NONE; rrc.anchor = GridBagConstraints.EAST;
        handzeichenLabel = new JLabel(i18n.getString("editor.betreiber.sign"));
        revRightPanel.add(handzeichenLabel, rrc);
        rrc.gridx = 1; rrc.weightx = 1.0;
        rrc.fill = GridBagConstraints.NONE; rrc.anchor = GridBagConstraints.WEST;
        handzeichenNeuFeld.setPreferredSize(new java.awt.Dimension(90, handzeichenNeuFeld.getPreferredSize().height));
        revRightPanel.add(handzeichenNeuFeld, rrc);

        // Beide Revision-Blöcke 50/50 nebeneinander, oben ausgerichtet
        JPanel revLeftWrapper = new JPanel(new BorderLayout());
        revLeftWrapper.add(revLeftPanel, BorderLayout.NORTH);
        JPanel revRightWrapper = new JPanel(new BorderLayout());
        revRightWrapper.add(revRightPanel, BorderLayout.NORTH);

        JPanel revPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        revPanel.add(revLeftWrapper);
        revPanel.add(revRightWrapper);

        mc.gridx = 0; mc.gridy = 7;
        mc.gridwidth = 2; mc.weightx = 1.0;
        mc.weighty = 0;
        mc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(revPanel, mc);
        mc.gridwidth = 1;

        // ── Button + Listener ─────────────────────────────
        button3.setText(i18n.getString("editor.betreiber.button.change_address"));
        BetreiberListener dialogListener = new BetreiberListener();
        strasseFeld.addActionListener(dialogListener);
        strassenBox.addActionListener(dialogListener);

        return mainPanel;
    }

    // ── getStandortLoeschAction() ──────────────────────────
    private Action getStandortLoeschAction() {
        if (standortLoeschAction == null) {
            standortLoeschAction = new AbstractAction("Löschen") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getStandorteTabelle().getSelectedRow();
                    if (row != -1 && getStandorteTabelle().getEditingRow() == -1) {
                        Standort standort = standorteModel.getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll der Standort " + standort + " wirklich gelöscht werden?",
                            "Löschen bestätigen")) {
                            standorteModel.removeRow(row);
                            log.debug("Standort " + standort + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von " + standort + " wurde abgebrochen!");
                        }
                    }
                }
            };
            standortLoeschAction.putValue(Action.MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_L));
            standortLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }
        return standortLoeschAction;
    }

    // ── getStandortNeuAction() ─────────────────────────────
    private Action getStandortNeuAction() {
        if (standortNeuAction == null) {
            standortNeuAction = new AbstractAction("Neuer Standort") {
                private static final long serialVersionUID = 4388335905488653435L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    Standort neuerStandort = new Standort();
                    neuerStandort.setInhaber(inhaber);
                    editStandort(neuerStandort);
                }
            };
            standortNeuAction.putValue(Action.MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_N));
        }
        return standortNeuAction;
    }

    // ── fillForm() ────────────────────────────────────────
    @Override
    protected void fillForm() {
        SwingWorkerVariant worker = new SwingWorkerVariant(this) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                if (wirtschaftszweige == null)
                    wirtschaftszweige = DatabaseQuery.getWirtschaftszweig();
                if (tabstreets == null)
                    tabstreets = DatabaseQuery.getTabStreets();
                if (gemarkungen == null)
                    gemarkungen = DatabaseQuery.getGemarkungen();
                if (standortggs == null)
                    standortggs = DatabaseQuery.getStandortgghwsg();
                if (entwgebiete == null)
                    entwgebiete = DatabaseQuery.getEntwaesserungsgebiete();
                if (wEinzugsgebiete == null)
                    wEinzugsgebiete = DatabaseQuery.getWassereinzugsgebiet();
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                if (tabstreets != null)
                    strassenBox.setModel(new DefaultComboBoxModel<String>(tabstreets));

                if (adressenTabelle != null) {
                    adressenModel.setStrasse(null);
                    adressenModel.updateList();
                    adressenTabelle.setModel(adressenModel);
                    adressenTabelle.setEnabled(false);
                    getAdressenTabelle();
                }
                if (standorteTabelle != null) {
                    standorteModel.setInhaber(inhaber);
                    standorteModel.updateList();
                    standorteTabelle.setModel(standorteModel);
                    getStandorteTabelle();
                }
                if (wirtschaftszweige != null) {
                    wirtschaftszweigBox.setModel(
                        new DefaultComboBoxModel<Wirtschaftszweig>(wirtschaftszweige));
                    wirtschaftszweigBox.setSelectedItem(getBetreiber().getWirtschaftszweig());
                }
                if (gemarkungen != null) {
                    gemarkungBox.setModel(new DefaultComboBoxModel<Gemarkung>(gemarkungen));
                    gemarkungBox.setSelectedItem(adresse.getGemarkung());
                }
                if (standortggs != null) {
                    standortGgBox.setModel(new DefaultComboBoxModel<Standortgghwsg>(standortggs));
                    standortGgBox.setSelectedItem(adresse.getStandortgghwsg());
                }
                if (entwgebiete != null) {
                    entwGebBox.setModel(new DefaultComboBoxModel<String>(entwgebiete));
                    entwGebBox.setSelectedItem(adresse.getEntgebid());
                }
                if (wEinzugsgebiete != null) {
                    wEinzugsGebBox.setModel(
                        new DefaultComboBoxModel<Wassereinzugsgebiet>(wEinzugsgebiete));
                    wEinzugsGebBox.setSelectedItem(adresse.getWassereinzugsgebiet());
                }

                anredeFeld.setText(getBetreiber().getAnrede());
                vornamenFeld.setText(getBetreiber().getVorname());
                namenFeld.setText(getBetreiber().getName());
                namenLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
                namenFeld.setFont(new Font("SansSerif", Font.BOLD, 12));
                nameZusFeld.setText(getBetreiber().getNamezus());
                kassenzeichenFeld.setText(getBetreiber().getKassenzeichen());
                kassenzeichenFeld.setFont(new Font("SansSerif", Font.BOLD, 12));
                kassenzeichenLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

                strasseFeld.setText(getBetreiber().getAdresse().getStrasse());
                hausnrFeld.setValue(getBetreiber().getAdresse().getHausnr());
                hausnrZusFeld.setText(getBetreiber().getAdresse().getHausnrzus());
                String plzZs = getBetreiber().getAdresse().getPlzzs();
                if (plzZs != null) plzZs = plzZs.trim();
                plzZsFeld.setText(plzZs);
                plzFeld.setText(getBetreiber().getAdresse().getPlz());
                ortFeld.setText(getBetreiber().getAdresse().getOrt());

                telefonFeld.setText(getBetreiber().getTelefon());
                telefaxFeld.setText(getBetreiber().getTelefax());
                emailFeld.setText(getBetreiber().getEmail());
                wassermengeFeld.setValue(getBetreiber().getWassermenge());
                strassenBox.setSelectedItem(getBetreiber().getAdresse().getStrasse());

                flurFeld.setText(adresse.getFlur());
                flurStkFeld.setText(adresse.getFlurstueck());

                if (getBetreiber().getDatenschutzAwsv() != null)
                    daten_awsvCheck.setSelected(getBetreiber().getDatenschutzAwsv());
                else
                    daten_awsvCheck.setSelected(false);
                if (getBetreiber().getDatenschutzEsatzung() != null)
                    daten_esatzungCheck.setSelected(getBetreiber().getDatenschutzEsatzung());
                else
                    daten_esatzungCheck.setSelected(false);
                if (getBetreiber().getDatenschutzWhg() != null)
                    daten_whgCheck.setSelected(getBetreiber().getDatenschutzWhg());
                else
                    daten_whgCheck.setSelected(false);
                if (adresse.isUeberschgeb() != null)
                    ueberschgebCheck.setSelected(adresse.isUeberschgeb());
                else
                    ueberschgebCheck.setSelected(false);

                betrBeaufVornameFeld.setText(getBetreiber().getVornamebetrbeauf());
                betrBeaufNachnameFeld.setText(getBetreiber().getNamebetrbeauf());
                handzeichenAltFeld.setText(getBetreiber().getRevihandz());
                bemerkungsArea.setText(getBetreiber().getBemerkungen());

                Date datum = getBetreiber().getRevidatum();
                revdatumsFeld.setText(AuikUtils.getStringFromDate(datum));

                frame.clearStatus();
            }
        };
        frame.changeStatus("Beschäftigt...");
        worker.start();
    }

    // ── canSave() ─────────────────────────────────────────
    @Override
    protected boolean canSave() {
        if (namenFeld.getText().equals("")) {
            namenLabel.setForeground(HauptFrame.ERROR_COLOR);
            namenFeld.requestFocus();
            String nameErr = "Der Name darf nicht leer sein!";
            frame.changeStatus(nameErr, HauptFrame.ERROR_COLOR);
            log.debug(nameErr);
            return false;
        } else if (handzeichenNeuFeld.getText().equals("")) {
            handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
            handzeichenNeuFeld.requestFocus();
            String handzErr = "Neues Handzeichen erforderlich!";
            frame.changeStatus(handzErr, HauptFrame.ERROR_COLOR);
            log.debug(handzErr);
            return false;
        } else {
            return true;
        }
    }

    // ── doSave() ──────────────────────────────────────────
    @Override
    protected boolean doSave() {
        String anrede = anredeFeld.getText();
        getBetreiber().setAnrede("".equals(anrede) ? null : anrede);

        String vorname = vornamenFeld.getText();
        getBetreiber().setVorname("".equals(vorname) ? null : vorname);

        String name = namenFeld.getText();
        getBetreiber().setName("".equals(name) ? null : name);

        String nameZusatz = nameZusFeld.getText();
        getBetreiber().setNamezus("".equals(nameZusatz) ? null : nameZusatz);

        String kasse = kassenzeichenFeld.getText();
        getBetreiber().setKassenzeichen("".equals(kasse) ? null : kasse);

        String strasse = strasseFeld.getText();
        getBetreiber().getAdresse().setStrasse("".equals(strasse) ? null : strasse);

        Integer hausnr = ((IntegerField) hausnrFeld).getIntValue();
        getBetreiber().getAdresse().setHausnr(hausnr);

        String hausnrZus = hausnrZusFeld.getText();
        getBetreiber().getAdresse().setHausnrzus("".equals(hausnrZus) ? null : hausnrZus);

        String plzZs = plzZsFeld.getText();
        if ("".equals(plzZs)) {
            getBetreiber().getAdresse().setPlzzs(null);
        } else {
            getBetreiber().getAdresse().setPlzzs(plzZs.toUpperCase().trim());
        }

        String plz = plzFeld.getText();
        if (plz != null) {
            plz = plz.trim();
            getBetreiber().getAdresse().setPlz("".equals(plz) ? null : plz);
        }

        String ort = ortFeld.getText();
        if (ort != null) {
            ort = ort.trim();
            getBetreiber().getAdresse().setOrt("".equals(ort) ? null : ort);
        }

        String telefon = telefonFeld.getText();
        if (telefon != null) {
            telefon = telefon.trim();
            getBetreiber().setTelefon("".equals(telefon) ? null : telefon);
        }

        String telefax = telefaxFeld.getText();
        if (telefax != null) {
            telefax = telefax.trim();
            getBetreiber().setTelefax("".equals(telefax) ? null : telefax);
        }

        String email = emailFeld.getText();
        if (email != null) {
            email = email.trim();
            getBetreiber().setEmail("".equals(email) ? null : email);
        }

        adresse.setUeberschgeb(ueberschgebCheck.isSelected());

        Integer wassermenge = ((IntegerField) wassermengeFeld).getIntValue();
        getBetreiber().setWassermenge(wassermenge);

        String betrBeaufVorname = betrBeaufVornameFeld.getText();
        if (betrBeaufVorname != null) {
            betrBeaufVorname = betrBeaufVorname.trim();
            getBetreiber().setVornamebetrbeauf("".equals(betrBeaufVorname) ? null : betrBeaufVorname);
        }

        String betrBeaufNachname = betrBeaufNachnameFeld.getText();
        if (betrBeaufNachname != null) {
            betrBeaufNachname = betrBeaufNachname.trim();
            getBetreiber().setNamebetrbeauf("".equals(betrBeaufNachname) ? null : betrBeaufNachname);
        }

        getBetreiber().setDatenschutzAwsv(daten_awsvCheck.isSelected());
        getBetreiber().setDatenschutzEsatzung(daten_esatzungCheck.isSelected());
        getBetreiber().setDatenschutzWhg(daten_whgCheck.isSelected());

        Wirtschaftszweig wizw = (Wirtschaftszweig) wirtschaftszweigBox.getSelectedItem();
        getBetreiber().setWirtschaftszweig(wizw);

        if (adresse == null) adresse = new Adresse();

        Gemarkung bgem = (Gemarkung) gemarkungBox.getSelectedItem();
        adresse.setGemarkung(bgem);

        Standortgghwsg stgg = (Standortgghwsg) standortGgBox.getSelectedItem();
        adresse.setStandortgghwsg(stgg);

        String ezgb = (String) entwGebBox.getSelectedItem();
        if (ezgb != null) {
            if (ezgb.length() > 10) ezgb = ezgb.substring(0, 10);
            ezgb = ezgb.trim();
        }
        adresse.setEntgebid(ezgb);

        Wassereinzugsgebiet wezg = (Wassereinzugsgebiet) wEinzugsGebBox.getSelectedItem();
        adresse.setWassereinzugsgebiet(wezg);

        String flur = flurFeld.getText().trim();
        adresse.setFlur("".equals(flur) ? null : flur);

        String flurstk = flurStkFeld.getText().trim();
        adresse.setFlurstueck("".equals(flurstk) ? null : flurstk);

        String bemerkungen = bemerkungsArea.getText();
        if (bemerkungen != null) {
            bemerkungen = bemerkungen.trim();
            getBetreiber().setBemerkungen("".equals(bemerkungen) ? null : bemerkungen);
        }

        String handzeichen = handzeichenNeuFeld.getText().trim();
        getBetreiber().setRevihandz(handzeichen);
        getBetreiber().setRevidatum(Calendar.getInstance().getTime());

        List<?> adrStdListe = this.standorteModel.getList();
        for (int i = 0; i < adrStdListe.size(); i++) {
            Standort std = (Standort) adrStdListe.get(i);
            std.merge();
        }
        getBetreiber().merge();
        return true;
    }

    // ── getBetreiber() ────────────────────────────────────
    public Inhaber getBetreiber() {
        return (Inhaber) getEditedObject();
    }

    // ── getAdressenTabelle() ──────────────────────────────
    private JTable getAdressenTabelle() {
        if (this.adressenModel == null) {
            this.adressenModel = new BasisTabStreetsModel();
            if (this.adressenTabelle == null) {
                this.adressenTabelle = new JTable(this.adressenModel);
                this.adressenTabelle.getColumnModel().getColumn(0).setPreferredWidth(10);
                this.adressenTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
                this.adressenTabelle.getColumnModel().getColumn(2).setPreferredWidth(10);
                this.adressenTabelle.getColumnModel().getColumn(3).setPreferredWidth(7);
                this.adressenTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                this.adressenTabelle.setColumnSelectionAllowed(false);
                this.adressenTabelle.setRowSelectionAllowed(true);
                this.adressenTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 1) && (e.getButton() == 1)) {
                            updateAdresse();
                        }
                    }
                    @Override public void mousePressed(MouseEvent e) {}
                    @Override public void mouseReleased(MouseEvent e) {}
                });
            }
        }
        return this.adressenTabelle;
    }

    // ── getStandorteTabelle() ─────────────────────────────
    private JTable getStandorteTabelle() {
        if (this.standorteModel == null) {
            this.standorteModel = new BasisStdModel();
            if (this.standorteTabelle == null) {
                this.standorteTabelle = new JTable(this.standorteModel);
                this.standorteTabelle.getColumnModel().getColumn(0).setPreferredWidth(60);
                this.standorteTabelle.getColumnModel().getColumn(1).setPreferredWidth(60);
                this.standorteTabelle.getColumnModel().getColumn(2).setPreferredWidth(60);
                this.standorteTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                this.standorteTabelle.setColumnSelectionAllowed(false);
                this.standorteTabelle.setRowSelectionAllowed(true);
                this.standorteTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getStandorteTabelle().rowAtPoint(origin);
                            Standort std = BetreiberEditor.this.standorteModel.getRow(row);
                            log.debug("Doppelklick auf Zeile " + row);
                            editStandort(std);
                        } else if (e.getButton() == 3) {
                            showStandortPopup(e);
                        }
                    }
                    @Override public void mouseReleased(MouseEvent e) {}
                });
            }
        }
        return this.standorteTabelle;
    }

    // ── Scroller ──────────────────────────────────────────
    private JScrollPane getAdressenScroller() {
        return new JScrollPane(
            getAdressenTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JScrollPane getStandorteScroller() {
        return new JScrollPane(
            getStandorteTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    // ── updateAdresse() ───────────────────────────────────
    public void updateAdresse() {
        log.debug("Start updateAdresse()");
        ListSelectionModel lsm = getAdressenTabelle().getSelectionModel();
        if (!lsm.isSelectionEmpty() && ortFeld.getText().equals("Bielefeld")) {
            int selectedRow = lsm.getMinSelectionIndex();
            TabStreets bts = this.adressenModel.getRow(selectedRow);
            log.debug("Standort " + bts.getName() + " (ID" + bts.getAbgleich() + ") angewählt.");
            strasseFeld.setText(bts.getName());
            hausnrFeld.setValue(bts.getHausnr());
            hausnrZusFeld.setText(bts.getHausnrZusatz());
            int adr = getAdressenTabelle().getSelectedRow();
            TabStreets stra = (TabStreets) adressenModel.getObjectAtRow(adr);
            if (stra.getPlz() != null) plzFeld.setText(stra.getPlz());

            int row = BetreiberEditor.this.standorteTabelle.getSelectedRow();
            if (row != -1) {
                Standort standort = BetreiberEditor.this.standorteModel.getRow(row);
                standort.setE32(bts.getX());
                standort.setN32(bts.getY());
            } else if (inhaber.getStandorts().size() == 0) {
                Standort standort = new Standort();
                standort.setInhaber(inhaber);
                Set<Standort> standorts = new HashSet<Standort>();
                standorts.add(standort);
                inhaber.setStandorts(standorts);
                standort.setE32(bts.getX());
                standort.setN32(bts.getY());
                List<Standort> std = new ArrayList<Standort>();
                for (Standort x : standorts) std.add(x);
                standorteModel.setList(std);
            }
            this.standorteModel.fireTableDataChanged();
        }
        log.debug("End updateAdresse()");
    }

    // ── getStrassenBox() ──────────────────────────────────
    private Component getStrassenBox() {
        strassenBox = new JComboBox<String>();
        strassenBox.setRenderer(new LongNameComboBoxRenderer());
        strassenBox.setEnabled(false);
        return strassenBox;
    }

    // ── BetreiberListener ─────────────────────────────────
    private final class BetreiberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == strassenBox) {
                adressenModel.setStrasse((String) strassenBox.getSelectedItem());
                adressenModel.updateList();
            }
        }
    }

    // ── editStandort() ────────────────────────────────────
    public void editStandort(Standort std) {
        StandortEditor editDialog = new StandortEditor(std, this.frame);
        editDialog.setLocationRelativeTo(this.frame);
        editDialog.setVisible(true);
        if (editDialog.wasSaved()) {
            standorteModel.updateList();
        }
    }

    // ── showStandortPopup() ───────────────────────────────
    private void showStandortPopup(MouseEvent e) {
        if (standortPopup == null) {
            standortPopup = new JPopupMenu("Standort");
            standortPopup.add(new JMenuItem(getStandortLoeschAction()));
            standortPopup.add(new JMenuItem(getStandortNeuAction()));
        }
        if (e != null) {
            Point origin = e.getPoint();
            int row = standorteTabelle.rowAtPoint(origin);
            if (row != -1) {
                getStandortLoeschAction().setEnabled(true);
                standorteTabelle.setRowSelectionInterval(row, row);
            } else {
                getStandortLoeschAction().setEnabled(false);
            }
            standortPopup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    // ── doApply() ─────────────────────────────────────────
    @Override
    protected void doApply() {
        AdresseChooser chooser = new AdresseChooser(
            inhaber.getAdresse(), BetreiberEditor.this.frame, "adresse");
        chooser.setVisible(true);
        Adresse adr = chooser.getChosenAdresse();
        if (adr != null) {
            inhaber.setAdresse(adr);
            inhaber.merge();
            fillForm();
        }
    }

}