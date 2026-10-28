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
 * $Id: BasisAdresseNeu.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 12.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/12/01 14:39:05  u633d
 * kleine Korrenkturen
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.11  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
/**
 * Copyright 2005-2011, Stadt Bielefeld
 * [License header unchanged...]
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import de.bielefeld.umweltamt.aui.AbstractModul;
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
import de.bielefeld.umweltamt.aui.module.common.editors.StandortEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStdModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisTabStreetsModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.LongNameComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Modul zum neuen Anlegen eines Betreibers.
 * @author David Klotz
 */
public class BasisAdresseNeu extends AbstractModul {
    private static final AuikLogger log =
        AuikLogger.getLogger();

    private JButton speichernButton;
    private Standort standort = new Standort();
    private Inhaber inh = new Inhaber();
    private Adresse adrn = new Adresse();

    private JLabel handzeichenLabel;
    private JLabel namenLabel;
    private JTextField anredeFeld;
    private JTextField vornamenFeld;
    private JTextField namenFeld;
    private JTextField nameZusFeld;
    private JTextField kassenzeichenFeld;
    private JTextField strasseFeld;
    private IntegerField hausnrFeld;
    private JTextField hausnrZusFeld;
    private JTextField plzZsFeld;
    private JTextField plzFeld;
    private JTextField ortFeld;
    private JTextField telefonFeld;
    private JTextField telefaxFeld;
    private JTextField emailFeld;
    private JFormattedTextField wassermengeFeld;
    private JTextField betrBeaufVornameFeld;
    private JTextField betrBeaufNachnameFeld;
    private JTextField revdatumsFeld;
    private JTextField handzeichenNeuFeld;
    private JCheckBox daten_awsvCheck = null;
    private JCheckBox daten_whgCheck = null;
    private JCheckBox daten_esatzungCheck = null;
    private JCheckBox ueberschgebCheck;
    private JTextField flurFeld;
    private JTextField flurStkFeld;
    private JComboBox<Gemarkung> gemarkungBox;
    private JComboBox<String> entwGebBox;
    private JComboBox<Standortgghwsg> standortGgBox;
    private JComboBox<Wassereinzugsgebiet> wEinzugsGebBox;
    private Gemarkung[] gemarkungen = null;
    private String[] entwgebiete = null;
    private Standortgghwsg[] standortggs = null;
    private Wassereinzugsgebiet[] wEinzugsgebiete = null;
    private JTextArea bemerkungsArea;
    private JComboBox<String> strassenBox;
    private JComboBox<Wirtschaftszweig> wirtschaftszweigBox;
    private Wirtschaftszweig[] wirtschaftszweige = null;
    private String[] tabstreets = null;
    private Action standortLoeschAction;
    private Action standortNeuAction;
    private JPopupMenu standortPopup;
    private JTable adressenTabelle;
    private BasisTabStreetsModel adressenModel;
    private JTable standorteTabelle;
    private BasisStdModel standorteModel;

    @Override
    public String getName() { return "Neue Adresse"; }

    @Override
    public String getIdentifier() {
        return "m_betreiber_neu";
    }

    @Override
    public String getCategory() { return "Betriebe"; }

    @Override
    public Icon getIcon() {
        return super.getIcon("filenew32.png");
    }

    // ── Separator Hilfsmethode ─────────────────────────────
    private JPanel createSeparator(String title) {
        JPanel sepPanel = new JPanel(
            new GridBagLayout());
        GridBagConstraints gbc =
            new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel lbl = new JLabel(title);
        lbl.setFont(lbl.getFont()
            .deriveFont(Font.BOLD));
        sepPanel.add(lbl, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0);
        sepPanel.add(new JSeparator(
            SwingConstants.HORIZONTAL), gbc);
        return sepPanel;
    }

    @SuppressWarnings("deprecation")
    @Override
    public JPanel getPanel() {
        if (panel == null) {
            ResourceBundle i18n = SettingsManager
                .getInstance().getI18nBundle();

            // ── Felder initialisieren ──────────────────────
            anredeFeld = new LimitedTextField(100);
            vornamenFeld = new LimitedTextField(100);
            namenFeld = new LimitedTextField(100);
            nameZusFeld = new LimitedTextField(50);
            kassenzeichenFeld =
                new LimitedTextField(50);
            hausnrFeld = new IntegerField();
            hausnrZusFeld = new LimitedTextField(10);
            plzZsFeld = new LimitedTextField(3, "");
            plzFeld = new JTextField();
            strasseFeld = new JTextField();
            ortFeld = new JTextField();
            telefonFeld = new LimitedTextField(50);
            telefaxFeld = new LimitedTextField(50);
            emailFeld = new LimitedTextField(50);
            wassermengeFeld = new IntegerField();
            betrBeaufVornameFeld =
                new LimitedTextField(50);
            betrBeaufNachnameFeld =
                new LimitedTextField(50);
            daten_awsvCheck =
                new JCheckBox("AwSV");
            daten_esatzungCheck =
                new JCheckBox("E-Satzung");
            daten_whgCheck = new JCheckBox("WHG");
            ueberschgebCheck =
                new JCheckBox(
                "Überschwemm.-gebiet");
            flurFeld = new LimitedTextField(50);
            flurStkFeld = new LimitedTextField(50);
            gemarkungBox = new JComboBox<>();
            entwGebBox = new JComboBox<>();
            standortGgBox = new JComboBox<>();
            wEinzugsGebBox = new JComboBox<>();
            revdatumsFeld = new JTextField();
            revdatumsFeld.setEditable(false);
            revdatumsFeld.setFocusable(false);
            revdatumsFeld.setToolTipText(
                "Wird automatisch gesetzt.");
            handzeichenNeuFeld =
                new LimitedTextField(10, "");
            handzeichenNeuFeld.setToolTipText(
                "Handzeichen obligatorisch!");
            bemerkungsArea =
                new LimitedTextArea(2000);
            bemerkungsArea.setLineWrap(true);
            bemerkungsArea.setWrapStyleWord(true);
            JScrollPane bemerkungsScroller =
                new JScrollPane(bemerkungsArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            wirtschaftszweigBox = new JComboBox<>();
            wirtschaftszweigBox.setRenderer(
                new LongNameComboBoxRenderer());

            handzeichenNeuFeld.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getSource().equals(
                            handzeichenNeuFeld)) {
                            if (e.getKeyCode() ==
                                KeyEvent.VK_ENTER) {
                                if (handzeichenNeuFeld
                                    .getText()
                                    .equals("")) {
                                    handzeichenLabel
                                        .setForeground(
                                        Color.RED);
                                    handzeichenNeuFeld
                                        .requestFocus();
                                } else {
                                    speichernButton
                                        .requestFocus();
                                }
                            }
                        }
                    }
                });

            bemerkungsScroller.getVerticalScrollBar()
                .setFocusable(false);
            bemerkungsScroller.getHorizontalScrollBar()
                .setFocusable(false);

            // ══════════════════════════════════════════
            // Linkes Panel (Inhaber + Adresse)
            // ══════════════════════════════════════════
            JPanel leftPanel = new JPanel(
                new GridBagLayout());
            GridBagConstraints lc =
                new GridBagConstraints();
            lc.insets = new Insets(1, 2, 1, 2);

            // SEPARATOR: Inhaber
            lc.gridx = 0; lc.gridy = 0;
            lc.gridwidth = 4; lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(createSeparator(
                i18n.getString(
                "editor.betreiber.separator.owner")),
                lc);

            // Firma/Name
            lc.gridy = 1; lc.gridwidth = 1;
            lc.weightx = 0; lc.fill =
                GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            namenLabel = new JLabel(
                i18n.getString(
                "editor.betreiber.company_name"));
            leftPanel.add(namenLabel, lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(namenFeld, lc);

            // Anrede
            lc.gridx = 0; lc.gridy = 2;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.salutation")), lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(anredeFeld, lc);

            // Vorname
            lc.gridx = 0; lc.gridy = 3;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.first_name")), lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(vornamenFeld, lc);

            // Zusatz
            lc.gridx = 0; lc.gridy = 4;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.addition")), lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(nameZusFeld, lc);

            // Wirtschaftszweig
            lc.gridx = 0; lc.gridy = 5;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.industry")), lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(wirtschaftszweigBox, lc);

            // Kassenzeichen
            lc.gridx = 0; lc.gridy = 6;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.ref_number")), lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(kassenzeichenFeld, lc);

            // SEPARATOR: Adresse
            lc.gridx = 0; lc.gridy = 7;
            lc.gridwidth = 4; lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(createSeparator(
                i18n.getString(
                "editor.betreiber.separator.address")),
                lc);

         // Straße + Hausnr + HausnrZus
            lc.gridx = 0; lc.gridy = 8;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.street")), lc);

            JPanel strassePanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 2, 0));
            strasseFeld.setPreferredSize(
                new java.awt.Dimension(150,
                    strasseFeld.getPreferredSize().height));
            hausnrFeld.setPreferredSize(
                new java.awt.Dimension(45,
                    hausnrFeld.getPreferredSize().height));
            hausnrZusFeld.setPreferredSize(
                new java.awt.Dimension(35,
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
            plzZsFeld.setPreferredSize(
                new java.awt.Dimension(22,
                    plzZsFeld.getPreferredSize().height));
            plzFeld.setPreferredSize(
                new java.awt.Dimension(55,
                    plzFeld.getPreferredSize().height));
            ortFeld.setPreferredSize(
                new java.awt.Dimension(150,
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
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.location_cond")),
                lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(standortGgBox, lc);

            // W. Einzugsgebiet
            lc.gridx = 0; lc.gridy = 11;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.area")), lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(wEinzugsGebBox, lc);

            // Entwässerungsgebiet + Überschwemmung
            lc.gridx = 0; lc.gridy = 12;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.drain_area")), lc);
            lc.gridx = 1; lc.gridwidth = 1;
            lc.weightx = 0.4;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(entwGebBox, lc);
            lc.gridx = 2; lc.gridwidth = 2;
            lc.weightx = 0.6;
            leftPanel.add(ueberschgebCheck, lc);

            // Gemarkung
            lc.gridx = 0; lc.gridy = 13;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.marking")), lc);
            lc.gridx = 1; lc.gridwidth = 3;
            lc.weightx = 1.0;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(gemarkungBox, lc);

            // Flur/Flurstücke
            lc.gridx = 0; lc.gridy = 14;
            lc.gridwidth = 1; lc.weightx = 0;
            lc.fill = GridBagConstraints.NONE;
            lc.anchor = GridBagConstraints.EAST;
            leftPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.parcel")), lc);
            lc.gridx = 1; lc.gridwidth = 1;
            lc.weightx = 0.35;
            lc.fill = GridBagConstraints.HORIZONTAL;
            leftPanel.add(flurFeld, lc);
            lc.gridx = 2; lc.gridwidth = 2;
            lc.weightx = 0.65;
            leftPanel.add(flurStkFeld, lc);

            // ══════════════════════════════════════════
            // Rechtes Panel (Ansprechpartner + auswählen)
            // ══════════════════════════════════════════
            JPanel rightPanel = new JPanel(
                new GridBagLayout());
            GridBagConstraints rc =
                new GridBagConstraints();
            rc.insets = new Insets(1, 2, 1, 2);

            // SEPARATOR: Ansprechpartner
            rc.gridx = 0; rc.gridy = 0;
            rc.gridwidth = 2; rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(createSeparator(
                i18n.getString(
                "editor.betreiber.separator.contact")),
                rc);

            // Vorname
            rc.gridy = 1; rc.gridwidth = 1;
            rc.weightx = 0;
            rc.fill = GridBagConstraints.NONE;
            rc.anchor = GridBagConstraints.EAST;
            rightPanel.add(new JLabel(i18n.getString(
                "editor.betreiber"
                + ".contact_first_name")), rc);
            rc.gridx = 1; rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(betrBeaufVornameFeld, rc);

            // Nachname
            rc.gridx = 0; rc.gridy = 2;
            rc.gridwidth = 1; rc.weightx = 0;
            rc.fill = GridBagConstraints.NONE;
            rc.anchor = GridBagConstraints.EAST;
            rightPanel.add(new JLabel(i18n.getString(
                "editor.betreiber"
                + ".contact_last_name")), rc);
            rc.gridx = 1; rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(betrBeaufNachnameFeld, rc);

            // Telefon
            rc.gridx = 0; rc.gridy = 3;
            rc.gridwidth = 1; rc.weightx = 0;
            rc.fill = GridBagConstraints.NONE;
            rc.anchor = GridBagConstraints.EAST;
            rightPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.telephone")), rc);
            rc.gridx = 1; rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(telefonFeld, rc);

            // Telefax
            rc.gridx = 0; rc.gridy = 4;
            rc.gridwidth = 1; rc.weightx = 0;
            rc.fill = GridBagConstraints.NONE;
            rc.anchor = GridBagConstraints.EAST;
            rightPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.fax")), rc);
            rc.gridx = 1; rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(telefaxFeld, rc);

            // E-Mail
            rc.gridx = 0; rc.gridy = 5;
            rc.gridwidth = 1; rc.weightx = 0;
            rc.fill = GridBagConstraints.NONE;
            rc.anchor = GridBagConstraints.EAST;
            rightPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.mail")), rc);
            rc.gridx = 1; rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(emailFeld, rc);

            // Wassermenge
            rc.gridx = 0; rc.gridy = 6;
            rc.gridwidth = 1; rc.weightx = 0;
            rc.fill = GridBagConstraints.NONE;
            rc.anchor = GridBagConstraints.EAST;
            rightPanel.add(new JLabel(i18n.getString(
                "editor.betreiber.water_amount")), rc);
            rc.gridx = 1; rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(wassermengeFeld, rc);

            // SEPARATOR: auswählen
            rc.gridx = 0; rc.gridy = 7;
            rc.gridwidth = 2; rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(createSeparator(
                i18n.getString("label.select")), rc);

            // Strassenbox
            rc.gridy = 8; rc.gridwidth = 2;
            rc.weightx = 1.0;
            rc.fill = GridBagConstraints.HORIZONTAL;
            rightPanel.add(getStrassenBox(), rc);

            // Adressentabelle
            rc.gridy = 9; rc.gridwidth = 2;
            rc.gridheight = 6;
            rc.weightx = 1.0; rc.weighty = 0.5;
            rc.fill = GridBagConstraints.BOTH;
            rightPanel.add(getAdressenScroller(), rc);
            rc.gridheight = 1; rc.weighty = 0;

            // ══════════════════════════════════════════
            // Haupt Panel
            // ══════════════════════════════════════════
            JPanel mainPanel = new JPanel(
                new GridBagLayout());
            GridBagConstraints mc =
                new GridBagConstraints();
            mc.insets = new Insets(2, 3, 2, 3);

            // ZEILE 0:

            JPanel topPanel = new JPanel(new GridBagLayout());
            GridBagConstraints tc = new GridBagConstraints();
            tc.insets = new Insets(0, 0, 0, 0);

         // leftPanel in Wrapper
            JPanel leftWrapper = new JPanel(new BorderLayout());
            leftWrapper.add(leftPanel, BorderLayout.NORTH);
            leftWrapper.setPreferredSize(
                new java.awt.Dimension(250,
                    leftWrapper.getPreferredSize().height));

            // rightPanel in Wrapper
            JPanel rightWrapper = new JPanel(new BorderLayout());
            rightWrapper.add(rightPanel, BorderLayout.NORTH);

            tc.gridx = 0; tc.gridy = 0;
            tc.weightx = 0.55; tc.weighty = 1.0;
            tc.fill = GridBagConstraints.BOTH;
            tc.anchor = GridBagConstraints.NORTH;
            topPanel.add(leftWrapper, tc);

            tc.gridx = 1;
            tc.weightx = 0.45; tc.weighty = 1.0;
            tc.fill = GridBagConstraints.BOTH;
            tc.anchor = GridBagConstraints.NORTH;
            topPanel.add(rightWrapper, tc);

            // topPanel ins Hauptpanel
            mc.gridx = 0; mc.gridy = 0;
            mc.gridwidth = 2; mc.weightx = 1.0; mc.weighty = 0;
            mc.fill = GridBagConstraints.HORIZONTAL;
            mc.anchor = GridBagConstraints.NORTH;
            mainPanel.add(topPanel, mc);



            // SEPARATOR: Standort / Lage
            mc.gridx = 0; mc.gridy = 1;
            mc.gridwidth = 2; mc.weightx = 1.0;
            mc.weighty = 0;
            mc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(createSeparator(
                i18n.getString(
                "editor.betreiber.separator.location")),
                mc);

            // Standortetabelle
            mc.gridy = 2; mc.gridwidth = 2;
            mc.weightx = 1.0; mc.weighty = 0.2;
            mc.fill = GridBagConstraints.BOTH;
            mainPanel.add(getStandorteScroller(), mc);
            mc.weighty = 0;

            // SEPARATOR: Datenschutz
            mc.gridy = 3; mc.gridwidth = 2;
            mc.weightx = 1.0; mc.weighty = 0;
            mc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(createSeparator(
                i18n.getString(
                "editor.betreiber.separator"
                + ".data_prot_notice")), mc);

            // DSGVO Checkboxen
            JPanel dsgvoPanel = new JPanel(
                new FlowLayout(
                    FlowLayout.LEFT, 20, 2));
            dsgvoPanel.add(daten_awsvCheck);
            dsgvoPanel.add(daten_esatzungCheck);
            dsgvoPanel.add(daten_whgCheck);
            mc.gridy = 4; mc.gridwidth = 2;
            mc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(dsgvoPanel, mc);

            // ── Unten: Bemerkungen links | Revision rechts ────
            JPanel bottomPanel = new JPanel(new GridBagLayout());
            GridBagConstraints bc = new GridBagConstraints();
            bc.insets = new Insets(2, 3, 2, 3);

            // Bemerkungen (links)
            bc.gridx = 0; bc.gridy = 0;
            bc.weightx = 0.5; bc.weighty = 0;
            bc.fill = GridBagConstraints.HORIZONTAL;
            bottomPanel.add(createSeparator(
                i18n.getString("editor.betreiber.remark")), bc);

            bc.gridy = 1; bc.gridheight = 3;
            bc.fill = GridBagConstraints.BOTH;
            bemerkungsScroller.setPreferredSize(
                new java.awt.Dimension(300, 70));
            bottomPanel.add(bemerkungsScroller, bc);
            bc.gridheight = 1;

            // Revision (rechts)
            JPanel revPanel = new JPanel(new GridBagLayout());
            GridBagConstraints rc2 = new GridBagConstraints();
            rc2.insets = new Insets(2, 3, 2, 3);

            rc2.gridx = 0; rc2.gridy = 0;
            rc2.gridwidth = 2; rc2.weightx = 1.0;
            rc2.fill = GridBagConstraints.HORIZONTAL;
            revPanel.add(createSeparator(
                i18n.getString("editor.betreiber.separator.rev")), rc2);

            rc2.gridy = 1; rc2.gridwidth = 1; rc2.weightx = 0;
            rc2.fill = GridBagConstraints.NONE;
            rc2.anchor = GridBagConstraints.EAST;
            revPanel.add(new JLabel(
                i18n.getString("editor.betreiber.date")), rc2);
            rc2.gridx = 1; rc2.weightx = 1.0;
            rc2.fill = GridBagConstraints.HORIZONTAL;
            revPanel.add(revdatumsFeld, rc2);

            rc2.gridx = 0; rc2.gridy = 2; rc2.weightx = 0;
            rc2.fill = GridBagConstraints.NONE;
            handzeichenLabel = new JLabel(
                i18n.getString("editor.betreiber.sign"));
            revPanel.add(handzeichenLabel, rc2);
            rc2.gridx = 1; rc2.weightx = 1.0;
            rc2.fill = GridBagConstraints.HORIZONTAL;
            revPanel.add(handzeichenNeuFeld, rc2);

            rc2.gridx = 1; rc2.gridy = 3;
            rc2.fill = GridBagConstraints.NONE;
            rc2.anchor = GridBagConstraints.EAST;
            revPanel.add(getSpeichernButton(), rc2);

            bc.gridx = 1; bc.gridy = 0; bc.gridheight = 4;
            bc.weightx = 0.5;
            bc.fill = GridBagConstraints.HORIZONTAL;
            bc.anchor = GridBagConstraints.NORTH;
            bottomPanel.add(revPanel, bc);

            // ins Hauptpanel unter die Datenschutz-Checkboxen
            mc.gridx = 0; mc.gridy = 5;
            mc.gridwidth = 2; mc.gridheight = 1;
            mc.weightx = 1.0; mc.weighty = 0;
            mc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(bottomPanel, mc);
            BetreiberListener dialogListener =
                new BetreiberListener();
            strasseFeld.addActionListener(
                dialogListener);
            strassenBox.addActionListener(
                dialogListener);

            JPanel westPanel = new JPanel(new BorderLayout());
            mainPanel.setPreferredSize(new java.awt.Dimension(900,
            	    mainPanel.getPreferredSize().height));
            westPanel.add(mainPanel, BorderLayout.NORTH);

            JPanel wrapperPanel = new JPanel(new BorderLayout());
            wrapperPanel.add(westPanel, BorderLayout.WEST);

            panel = wrapperPanel;;
        }
        return panel;
    }

    private Component getStrassenBox() {
        strassenBox = new JComboBox<>();
        strassenBox.setRenderer(
            new LongNameComboBoxRenderer());
        return strassenBox;
    }

    public void editStandort(Standort std) {
        StandortEditor editDialog =
            new StandortEditor(std, this.frame);
        editDialog.setLocationRelativeTo(this.frame);
        editDialog.setVisible(true);
    }

    private JTable getAdressenTabelle() {
        if (this.adressenModel == null) {
            this.adressenModel =
                new BasisTabStreetsModel();
            if (this.adressenTabelle == null) {
                this.adressenTabelle =
                    new JTable(this.adressenModel);
                this.adressenTabelle.getColumnModel()
                    .getColumn(0).setPreferredWidth(10);
                this.adressenTabelle.getColumnModel()
                    .getColumn(1).setPreferredWidth(100);
                this.adressenTabelle.getColumnModel()
                    .getColumn(2).setPreferredWidth(10);
                this.adressenTabelle.getColumnModel()
                    .getColumn(3).setPreferredWidth(7);
                this.adressenTabelle.setSelectionMode(
                    ListSelectionModel
                        .SINGLE_SELECTION);
                this.adressenTabelle
                    .setColumnSelectionAllowed(false);
                this.adressenTabelle
                    .setRowSelectionAllowed(true);
                this.adressenTabelle.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(
                            java.awt.event.MouseEvent e) {
                            if ((e.getClickCount() == 1)
                                && (e.getButton() == 1))
                                updateAdresse();
                        }
                        @Override
                        public void mousePressed(
                            MouseEvent e) {}
                        @Override
                        public void mouseReleased(
                            MouseEvent e) {}
                    });
            }
        }
        return this.adressenTabelle;
    }

    private JTable getStandorteTabelle() {
        if (this.standorteModel == null) {
            this.standorteModel = new BasisStdModel();
            if (this.standorteTabelle == null) {
                this.standorteTabelle =
                    new JTable(this.standorteModel);
                this.standorteTabelle.getColumnModel()
                    .getColumn(0).setPreferredWidth(100);
                this.standorteTabelle.getColumnModel()
                    .getColumn(1).setPreferredWidth(10);
                this.standorteTabelle.getColumnModel()
                    .getColumn(2).setPreferredWidth(7);
                this.standorteTabelle.setSelectionMode(
                    ListSelectionModel
                        .SINGLE_SELECTION);
                this.standorteTabelle
                    .setColumnSelectionAllowed(false);
                this.standorteTabelle
                    .setRowSelectionAllowed(true);
                this.standorteTabelle.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(
                            java.awt.event.MouseEvent e) {
                            if ((e.getClickCount() == 2)
                                && (e.getButton() == 1)) {
                                Point origin =
                                    e.getPoint();
                                int row =
                                    getStandorteTabelle()
                                    .rowAtPoint(origin);
                                Standort std =
                                    BasisAdresseNeu.this
                                    .standorteModel
                                    .getRow(row);
                                log.debug(
                                    "Doppelklick auf"
                                    + " Zeile " + row);
                                editStandort(std);
                            }
                        }
                        @Override
                        public void mousePressed(
                            MouseEvent e) {
                            if (e.getButton() == 3)
                                showStandortPopup(e);
                        }
                        @Override
                        public void mouseReleased(
                            MouseEvent e) {}
                    });
            }
        }
        return this.standorteTabelle;
    }

    private JScrollPane getAdressenScroller() {
        JScrollPane s = new JScrollPane(
            getAdressenTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        s.setPreferredSize(
            new java.awt.Dimension(0, 120));
        s.setMaximumSize(
            new java.awt.Dimension(
                Integer.MAX_VALUE, 120));
        return s;
    }

    private JScrollPane getStandorteScroller() {
        JScrollPane s = new JScrollPane(
            getStandorteTabelle(),
            ScrollPaneConstants
                .VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants
                .HORIZONTAL_SCROLLBAR_NEVER);
        s.setPreferredSize(
            new java.awt.Dimension(0, 80));
        s.setMaximumSize(
            new java.awt.Dimension(
                Integer.MAX_VALUE, 80));
        return s;
    }

    private JButton getSpeichernButton() {
        if (this.speichernButton == null) {
            this.speichernButton =
                new JButton("Speichern");
            this.speichernButton.setToolTipText(
                "Speichern");
            this.speichernButton.setEnabled(false);
            this.speichernButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        if (!namenFeld.getText()
                            .equals(""))
                            doSave();
                    }
                });
        }
        return this.speichernButton;
    }

    @Override
    public void show() {
        super.show();
        clearForm();
    }

    public void updateAdresse() {
        log.debug("Start updateAdresse()");
        ListSelectionModel lsm =
            getAdressenTabelle().getSelectionModel();
        if (!lsm.isSelectionEmpty()) {
            int selectedRow =
                lsm.getMinSelectionIndex();
            TabStreets bts =
                this.adressenModel.getRow(selectedRow);
            log.debug("Standort " + bts.getName()
                + " (ID" + bts.getAbgleich()
                + ") angewählt.");
            plzFeld.setText(bts.getPlz());
            strasseFeld.setText(bts.getName());
            hausnrFeld.setValue(bts.getHausnr());
            hausnrZusFeld.setText(
                bts.getHausnrZusatz());
            int adr =
                getAdressenTabelle().getSelectedRow();
            TabStreets stra = (TabStreets)
                adressenModel.getObjectAtRow(adr);
            if (stra.getPlz() != null)
                plzFeld.setText(stra.getPlz());

            Set<Standort> standorts =
                new HashSet<Standort>();
            standorts.add(standort);
            inh.setStandorts(standorts);
            standort.setE32(bts.getX());
            standort.setN32(bts.getY());
            standort.setBezeichnung("Adresse");
            standorteModel.setList(
                new ArrayList<>(standorts));
            this.gemarkungBox.setSelectedIndex(0);
            this.standortGgBox.setSelectedIndex(0);
            this.wEinzugsGebBox.setSelectedIndex(0);
            this.entwGebBox.setSelectedIndex(0);
        }
        this.standorteModel.fireTableDataChanged();
        log.debug("End updateAdresse()");
    }

    private void doSave() {
        if (namenFeld.getText().equals("")) {
            namenLabel.setForeground(
                HauptFrame.ERROR_COLOR);
            namenFeld.requestFocus();
            String nameErr =
                "Der Name darf nicht leer sein!";
            frame.changeStatus(nameErr,
                HauptFrame.ERROR_COLOR);
            log.debug(nameErr);
        } else if (handzeichenNeuFeld.getText()
            .equals("")) {
            handzeichenLabel.setForeground(
                HauptFrame.ERROR_COLOR);
            handzeichenNeuFeld.requestFocus();
            String handzErr =
                "Neues Handzeichen erforderlich!";
            frame.changeStatus(handzErr,
                HauptFrame.ERROR_COLOR);
            log.debug(handzErr);
        } else {
            setAllEnabled(false);

            String anrede = anredeFeld.getText();
            inh.setAnrede(
                anrede.equals("") ? null : anrede);

            String vorname = vornamenFeld.getText();
            inh.setVorname(
                vorname.equals("") ? null : vorname);

            String name = namenFeld.getText();
            inh.setName(
                name.equals("") ? null : name);

            String nameZusatz = nameZusFeld.getText();
            inh.setNamezus(
                nameZusatz.equals("")
                    ? null : nameZusatz);

            String kassenzeichen =
                kassenzeichenFeld.getText();
            inh.setKassenzeichen(
                kassenzeichen.equals("")
                    ? null : kassenzeichen);

            String telefon =
                telefonFeld.getText().trim();
            inh.setTelefon(
                telefon.equals("") ? null : telefon);

            String telefax =
                telefaxFeld.getText().trim();
            inh.setTelefax(
                telefax.equals("") ? null : telefax);

            String email =
                emailFeld.getText().trim();
            inh.setEmail(
                email.equals("") ? null : email);

            String betrBeaufVorname =
                betrBeaufVornameFeld.getText().trim();
            inh.setVornamebetrbeauf(
                betrBeaufVorname.equals("")
                    ? null : betrBeaufVorname);

            String betrBeaufNachname =
                betrBeaufNachnameFeld.getText().trim();
            inh.setNamebetrbeauf(
                betrBeaufNachname.equals("")
                    ? null : betrBeaufNachname);

            Integer wassermenge =
                ((IntegerField) wassermengeFeld)
                    .getIntValue();
            inh.setWassermenge(wassermenge);

            Wirtschaftszweig wizw =
                (Wirtschaftszweig) wirtschaftszweigBox
                    .getSelectedItem();
            inh.setWirtschaftszweig(wizw);

            String bemerkungen =
                bemerkungsArea.getText().trim();
            inh.setBemerkungen(
                bemerkungen.equals("")
                    ? null : bemerkungen);

            inh.setDatenschutzAwsv(
                daten_awsvCheck.isSelected());
            inh.setDatenschutzEsatzung(
                daten_esatzungCheck.isSelected());
            inh.setDatenschutzWhg(
                daten_whgCheck.isSelected());

            if (DatabaseQuery.findAdressen(
                strasseFeld.getText(),
                hausnrFeld.getIntValue(),
                hausnrZusFeld.getText(),
                plzFeld.getText())
                .iterator().hasNext()) {
                adrn = DatabaseQuery.findAdressen(
                    strasseFeld.getText(),
                    hausnrFeld.getIntValue(),
                    hausnrZusFeld.getText(),
                    plzFeld.getText())
                    .iterator().next();
            } else {
                String strasse =
                    strasseFeld.getText();
                adrn.setStrasse(
                    "".equals(strasse)
                        ? null : strasse);

                Integer hausnr;
                try {
                    hausnrFeld.commitEdit();
                } catch (ParseException e1) {
                    hausnrFeld.setValue(
                        Integer.valueOf(0));
                }
                if (hausnrFeld.getValue()
                    instanceof Long)
                    hausnr = ((Long) hausnrFeld
                        .getValue()).intValue();
                else
                    hausnr = (Integer)
                        hausnrFeld.getValue();
                adrn.setHausnr(hausnr);

                String hausnrZus =
                    hausnrZusFeld.getText();
                adrn.setHausnrzus(
                    hausnrZus.equals("")
                        ? null : hausnrZus);

                String plzZs = plzZsFeld.getText();
                if (plzZs.equals(""))
                    adrn.setPlzzs(null);
                else
                    adrn.setPlzzs(
                        plzZs.toUpperCase().trim());

                String plz =
                    plzFeld.getText().trim();
                adrn.setPlz(
                    plz.equals("") ? null : plz);

                String ort =
                    ortFeld.getText().trim();
                adrn.setOrt(
                    ort.equals("") ? null : ort);

                adrn.setUeberschgeb(
                    ueberschgebCheck.isSelected());

                Gemarkung bgem = (Gemarkung)
                    gemarkungBox.getSelectedItem();
                adrn.setGemarkung(bgem);

                String flur =
                    flurFeld.getText().trim();
                adrn.setFlur(
                    flur.equals("") ? null : flur);

                String flurstk =
                    flurStkFeld.getText().trim();
                adrn.setFlurstueck(
                    flurstk.equals("")
                        ? null : flurstk);

                Standortgghwsg stgg =
                    (Standortgghwsg) standortGgBox
                        .getSelectedItem();
                adrn.setStandortgghwsg(stgg);

                String ezgb = (String)
                    entwGebBox.getSelectedItem();
                if (ezgb != null) {
                    if (ezgb.length() > 10)
                        ezgb = ezgb.substring(0, 10);
                    ezgb = ezgb.trim();
                }
                adrn.setEntgebid(ezgb);
                adrn.setUeberschgeb(
                    ueberschgebCheck.isSelected());

                Wassereinzugsgebiet wezg =
                    (Wassereinzugsgebiet)
                    wEinzugsGebBox.getSelectedItem();
                adrn.setWassereinzugsgebiet(wezg);
            }

            inh.setRevidatum(
                Calendar.getInstance().getTime());
            inh.setRevihandz(
                handzeichenNeuFeld.getText().trim());

            adrn = Adresse.merge(adrn);
            inh.setAdresse(adrn);
            inh = Inhaber.merge(inh);
            standort.setInhaber(inh);
            standort = Standort.merge(standort);

            if (standort != null) {
                frame.changeStatus(
                    "Neuer Betreiber "
                    + standort.getId()
                    + " erfolgreich gespeichert.",
                    HauptFrame.SUCCESS_COLOR);
                if (manager.getSettingsManager()
                    .getBoolSetting(
                    "auik.imc.return_to_objekt"
                    + "_betreiber")) {
                    manager.getSettingsManager()
                        .setSetting(
                        "auik.imc.use_betreiber",
                        standort.getInhaber()
                            .getId().intValue(),
                        false);
                    manager.getSettingsManager()
                        .removeSetting(
                        "auik.imc.return_to_objekt"
                        + "_betreiber");
                    manager.switchModul(
                        "m_objekt_bearbeiten");
                } else if (manager
                    .getSettingsManager()
                    .getBoolSetting(
                    "auik.imc.return_to_objekt"
                    + "_standort")) {
                    manager.getSettingsManager()
                        .setSetting(
                        "auik.imc.use_standort",
                        standort.getId().intValue(),
                        false);
                    manager.getSettingsManager()
                        .removeSetting(
                        "auik.imc.return_to_objekt"
                        + "_standort");
                    manager.switchModul(
                        "m_objekt_bearbeiten");
                } else {
                    clearForm();
                }
            }

            if (inh != null) {
                frame.changeStatus(
                    "Neue:r Inhaber:in " + inh.getId()
                    + " erfolgreich gespeichert.",
                    HauptFrame.SUCCESS_COLOR);
                if (manager.getSettingsManager()
                    .getBoolSetting(
                    "auik.imc.return_to_objekt"
                    + "_betreiber")) {
                    manager.getSettingsManager()
                        .setSetting(
                        "auik.imc.use_betreiber",
                        inh.getId().intValue(), false);
                    manager.getSettingsManager()
                        .removeSetting(
                        "auik.imc.return_to_objekt"
                        + "_betreiber");
                    manager.switchModul(
                        "m_objekt_bearbeiten");
                } else if (manager
                    .getSettingsManager()
                    .getBoolSetting(
                    "auik.imc.return_to_objekt"
                    + "_standort")) {
                    manager.getSettingsManager()
                        .setSetting(
                        "auik.imc.use_standort",
                        standort.getId().intValue(),
                        false);
                    manager.getSettingsManager()
                        .removeSetting(
                        "auik.imc.return_to_objekt"
                        + "_standort");
                    manager.switchModul(
                        "m_objekt_bearbeiten");
                } else {
                    clearForm();
                }
            } else {
                frame.changeStatus(
                    "Konnte Betreiber nicht"
                    + " speichern!", Color.RED);
                log.debug("Konnte nicht speichern");
            }
        }
    }

    private void clearForm() {
        setAllEnabled(false);
        SwingWorkerVariant worker =
            new SwingWorkerVariant(panel) {
            @Override
            protected void doNonUILogic()
                throws RuntimeException {
                if (wirtschaftszweige == null)
                    wirtschaftszweige =
                        DatabaseQuery
                            .getWirtschaftszweig();
                if (tabstreets == null)
                    tabstreets =
                        DatabaseQuery.getTabStreets();
                if (gemarkungen == null)
                    gemarkungen =
                        DatabaseQuery.getGemarkungen();
                if (standortggs == null)
                    standortggs =
                        DatabaseQuery
                            .getStandortgghwsg();
                if (entwgebiete == null)
                    entwgebiete = DatabaseQuery
                        .getEntwaesserungsgebiete();
                if (wEinzugsgebiete == null)
                    wEinzugsgebiete = DatabaseQuery
                        .getWassereinzugsgebiet();
            }

            @Override
            protected void doUIUpdateLogic()
                throws RuntimeException {
                adrn = new Adresse();
                inh = new Inhaber();
                standort = new Standort();
                if (wirtschaftszweige != null)
                    wirtschaftszweigBox.setModel(
                        new DefaultComboBoxModel<>(
                            wirtschaftszweige));
                if (tabstreets != null)
                    strassenBox.setModel(
                        new DefaultComboBoxModel<>(
                            tabstreets));
                if (standorteTabelle != null) {
                    standorteModel.updateList();
                    standorteTabelle.setModel(
                        standorteModel);
                    if (gemarkungen != null)
                        gemarkungBox.setModel(
                            new DefaultComboBoxModel<>(
                                gemarkungen));
                    if (standortggs != null)
                        standortGgBox.setModel(
                            new DefaultComboBoxModel<>(
                                standortggs));
                    if (entwgebiete != null)
                        entwGebBox.setModel(
                            new DefaultComboBoxModel<>(
                                entwgebiete));
                    if (wEinzugsgebiete != null)
                        wEinzugsGebBox.setModel(
                            new DefaultComboBoxModel<>(
                                wEinzugsgebiete));
                    ortFeld.setText("Bielefeld");
                    strasseFeld.setText("");
                    hausnrFeld.setValue(null);
                    hausnrZusFeld.setText("");
                    plzZsFeld.setText("D");
                    plzFeld.setText("");
                    anredeFeld.setText("");
                    vornamenFeld.setText("");
                    namenFeld.setText("");
                    namenFeld.setFont(new Font(
                        "SansSerif", Font.BOLD, 12));
                    namenLabel.setForeground(
                        panel.getForeground());
                    namenLabel.setFont(new Font(
                        "SansSerif", Font.BOLD, 12));
                    nameZusFeld.setText("");
                    kassenzeichenFeld.setText("");
                    telefonFeld.setText("");
                    telefaxFeld.setText("");
                    emailFeld.setText("");
                    daten_awsvCheck.setSelected(false);
                    daten_esatzungCheck
                        .setSelected(false);
                    daten_whgCheck.setSelected(false);
                    ueberschgebCheck.setSelected(false);
                    wassermengeFeld.setValue(null);
                    betrBeaufNachnameFeld.setText("");
                    betrBeaufVornameFeld.setText("");
                    bemerkungsArea.setText("");
                    revdatumsFeld.setText(
                        DateUtils.getCurrentDateString());
                    handzeichenNeuFeld.setText("");
                    handzeichenLabel.setForeground(
                        panel.getForeground());
                    setAllEnabled(true);
                    log.debug("(" + getIdentifier()
                        + ".clearForm) "
                        + "Formular zurückgesetzt");
                }
            }
        };
        worker.start();
    }

    private void setAllEnabled(boolean enabled) {
        speichernButton.setEnabled(enabled);
        strassenBox.setEnabled(enabled);
        strasseFeld.setEnabled(enabled);
        ortFeld.setEnabled(enabled);
        wirtschaftszweigBox.setEnabled(enabled);
        hausnrFeld.setEditable(enabled);
        hausnrZusFeld.setEditable(enabled);
        plzFeld.setEditable(enabled);
        plzZsFeld.setEditable(enabled);
        anredeFeld.setEditable(enabled);
        vornamenFeld.setEditable(enabled);
        namenFeld.setEditable(enabled);
        nameZusFeld.setEditable(enabled);
        kassenzeichenFeld.setEditable(enabled);
        ortFeld.setEditable(enabled);
        telefonFeld.setEditable(enabled);
        telefaxFeld.setEditable(enabled);
        emailFeld.setEditable(enabled);
        wassermengeFeld.setEnabled(enabled);
        betrBeaufVornameFeld.setEditable(enabled);
        betrBeaufNachnameFeld.setEditable(enabled);
        bemerkungsArea.setEnabled(enabled);
        bemerkungsArea.setEditable(enabled);
        handzeichenNeuFeld.setEditable(enabled);
    }

    private final class BetreiberListener
        implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == strassenBox) {
                adressenModel.setStrasse(
                    strassenBox.getSelectedItem()
                        .toString());
                adressenModel.updateList();
            }
        }
    }

    private void showStandortPopup(MouseEvent e) {
        if (standortPopup == null) {
            standortPopup =
                new JPopupMenu("Standort");
            standortPopup.add(new JMenuItem(
                getStandortLoeschAction()));
            standortPopup.add(new JMenuItem(
                getStandortNeuAction()));
        }
        if (e != null) {
            Point origin = e.getPoint();
            int row = standorteTabelle
                .rowAtPoint(origin);
            if (row != -1) {
                getStandortLoeschAction()
                    .setEnabled(true);
                standorteTabelle
                    .setRowSelectionInterval(row, row);
            } else {
                getStandortLoeschAction()
                    .setEnabled(false);
            }
            standortPopup.show(e.getComponent(),
                e.getX(), e.getY());
        }
    }

    private Action getStandortLoeschAction() {
        if (standortLoeschAction == null) {
            standortLoeschAction =
                new AbstractAction("Löschen") {
                @Override
                public void actionPerformed(
                    ActionEvent e) {
                    int row = getStandorteTabelle()
                        .getSelectedRow();
                    if (row != -1
                        && getStandorteTabelle()
                            .getEditingRow() == -1) {
                        Standort standort =
                            standorteModel.getRow(row);
                        if (GUIManager.getInstance()
                            .showQuestion(
                            "Soll der Standort "
                            + standort
                            + " wirklich gelöscht"
                            + " werden?",
                            "Löschen bestätigen")) {
                            standorteModel
                                .removeRow(row);
                            log.debug("Standort "
                                + standort
                                + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von "
                                + standort
                                + " wurde"
                                + " abgebrochen!");
                        }
                    }
                }
            };
            standortLoeschAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_L));
            standortLoeschAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(
                    KeyEvent.VK_DELETE, 0, false));
        }
        return standortLoeschAction;
    }

    private Action getStandortNeuAction() {
        if (standortNeuAction == null) {
            standortNeuAction =
                new AbstractAction("Neuer Standort") {
                private static final long
                    serialVersionUID =
                        4388335905488653435L;
                @Override
                public void actionPerformed(
                    ActionEvent e) {
                    Standort neuerStandort =
                        new Standort();
                    neuerStandort.setInhaber(inh);
                    editStandort(neuerStandort);
                }
            };
            standortNeuAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_N));
        }
        return standortNeuAction;
    }
}