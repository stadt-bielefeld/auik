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
 * Created on 19.04.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Color;
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
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.atl.Sielhaut;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ProbenahmenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.PDFExporter;

/**
 * Das "Probepunkt"-Tab des BasisObjektBearbeiten-Moduls
 * @author David Klotz
 */
public class ProbepunktPanel extends ObjectPanel {
    private static final long serialVersionUID = 3663375435585578751L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JComboBox<Probeart> probePktArtBox;
    private JComboBox<Klaeranlage> probeKABox;
    private JComboBox<Sachbearbeiter> sachbearbeiterBox;
    private JTextField brancheFeld = null;
    private JFormattedTextField probePktNrFeld = null;
    private JTextArea probePktBeschreibungsArea = null;
    private JTable probenahmeTabelle = null;
    private JPanel neueProbePanel = null;
    private JTextField kennummerFeld = null;
    private JDateChooser datumsChooser = null;
    private JButton anlegenButton = null;
    private JButton savePktButton = null;
    private ProbenahmenModel probenahmenModel = null;

    // Daten
    private Messstelle probepkt = null;
    private Sielhaut sielhaut = null;
    private Probeart[] probearten = null;
    private Klaeranlage[] klaeranlagen = null;
    private Sachbearbeiter[] sachbearbeiter = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;
    private JButton printDeckblattButton = null;

    // ── Konstruktor ────────────────────────────────────────
    public ProbepunktPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Probenahmepunkt";
        this.hauptModul = hauptModul;
        this.probenahmenModel = new ProbenahmenModel();

        // ── Inneres Panel mit dem ganzen Inhalt ───────────
        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 5);

        // ── SEPARATOR: Eigenschaften ──────────────────────
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Eigenschaften"), gbc);

        // ── ZEILE 1: Art + Nr ─────────────────────────────
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Art"), gbc);

        gbc.gridx = 1; gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getProbePktArtBox().setPreferredSize(
            new java.awt.Dimension(300,
                getProbePktArtBox().getPreferredSize().height));
        innerPanel.add(getProbePktArtBox(), gbc);

        gbc.gridx = 2; gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Nr"), gbc);

        gbc.gridx = 3; gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.ipadx = 30;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        innerPanel.add(getProbePktNrFeld(), gbc);
        gbc.ipadx = 0;

        // ── ZEILE 2: Kläranlage ───────────────────────────
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Kläranlage"), gbc);

        gbc.gridx = 1; gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getProbeKABox().setPreferredSize(
            new java.awt.Dimension(300,
                getProbeKABox().getPreferredSize().height));
        innerPanel.add(getProbeKABox(), gbc);

        // ── ZEILE 3: Sachbearbeiter ───────────────────────
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Sachbearbeiter:in"), gbc);

        gbc.gridx = 1; gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getSachbearbeiterBox().setPreferredSize(
            new java.awt.Dimension(300,
                getSachbearbeiterBox().getPreferredSize().height));
        innerPanel.add(getSachbearbeiterBox(), gbc);

        // ── ZEILE 4: Branche ──────────────────────────────
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Branche"), gbc);

        gbc.gridx = 1; gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getBrancheFeld().setPreferredSize(
            new java.awt.Dimension(300,
                getBrancheFeld().getPreferredSize().height));
        innerPanel.add(getBrancheFeld(), gbc);

        // ── SEPARATOR: Beschreibung ───────────────────────
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Beschreibung"), gbc);

        // ── BeschreibungsArea ─────────────────────────────
        JScrollPane beschScroller = new JScrollPane(
            getProbePktBeschreibungsArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        beschScroller.setBorder(null);
        gbc.gridy = 6;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        innerPanel.add(beschScroller, gbc);
        gbc.weighty = 0;

        // ── SEPARATOR: Probenahmen ────────────────────────
        gbc.gridy = 7;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Probenahmen"), gbc);

        // ── ProbenahmeTabelle ─────────────────────────────
        JScrollPane tabellenScroller = new JScrollPane(
            getProbenahmeTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tabellenScroller.setPreferredSize(
            new java.awt.Dimension(0, 80));
        tabellenScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 80));
        gbc.gridy = 8;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        innerPanel.add(tabellenScroller, gbc);
        gbc.weighty = 0;

        // ── NeueProbePanel ────────────────────────────────
        gbc.gridy = 9;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(getNeueProbePanel(), gbc);

        // ── SEPARATOR: Verknüpfte Objekte ─────────────────
        gbc.gridy = 10;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Verknüpfte Objekte"), gbc);

        // ── ObjektverknuepfungTabelle ─────────────────────
        JScrollPane objektverknuepfungScroller = new JScrollPane(
            getObjektverknuepungTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        objektverknuepfungScroller.setPreferredSize(
            new java.awt.Dimension(0, 60));
        objektverknuepfungScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 60));
        gbc.gridy = 11;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        innerPanel.add(objektverknuepfungScroller, gbc);
        gbc.weighty = 0;

        // ── ButtonBar rechts ──────────────────────────────
        gbc.gridy = 12;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(buildButtonBar(), gbc);

     // NEU:
        JPanel wrapperPanel = new JPanel(new java.awt.BorderLayout());
        wrapperPanel.add(innerPanel, java.awt.BorderLayout.WEST);
        this.setLayout(new java.awt.BorderLayout());
        this.add(wrapperPanel, java.awt.BorderLayout.NORTH);

        addChangeListeners(
            getProbePktNrFeld(),
            getProbePktBeschreibungsArea(),
            getKennummerFeld(),
            getBrancheFeld(),
            getProbeKABox(),
            getProbePktArtBox(),
            getSachbearbeiterBox());
    }

    // ── Separator Hilfsmethode ─────────────────────────────
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
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0);
        sepPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        return sepPanel;
    }

    // ── ButtonBar ──────────────────────────────────────────
    private JPanel buildButtonBar() {
        JPanel buttonBar = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonBar.add(getPrintDeckblattButton());
        buttonBar.add(getSelectObjektButton());
        buttonBar.add(getSavePktButton());
        return buttonBar;
    }

    // ── NeueProbePanel ─────────────────────────────────────
    private JPanel getNeueProbePanel() {
        if (this.neueProbePanel == null) {
            this.neueProbePanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(2, 4, 2, 4);

            // Label "Kennummer"
            gbc.gridx = 0; gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            this.neueProbePanel.add(new JLabel("Kennummer"), gbc);

            // KennummerFeld feste Breite
            gbc.gridx = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            getKennummerFeld().setPreferredSize(
                new java.awt.Dimension(100,
                    getKennummerFeld().getPreferredSize().height));
            this.neueProbePanel.add(getKennummerFeld(), gbc);

            // Label "Datum"
            gbc.gridx = 2;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            this.neueProbePanel.add(new JLabel("Datum"), gbc);

            // DatumsChooser feste Breite
            gbc.gridx = 3;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            getDatumsChooser().setPreferredSize(
                new java.awt.Dimension(120,
                    getDatumsChooser().getPreferredSize().height));
            this.neueProbePanel.add(getDatumsChooser(), gbc);

            // AnlegenButton
            gbc.gridx = 4;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            this.neueProbePanel.add(getAnlegenButton(), gbc);

            // Füller
            gbc.gridx = 5;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            this.neueProbePanel.add(new JPanel(), gbc);
        }
        return this.neueProbePanel;
    }

    // ── fetchFormData() ────────────────────────────────────
    public void fetchFormData() throws RuntimeException {
        this.probepkt = Messstelle.findByObjektId(
            this.hauptModul.getObjekt().getId());
        log.debug("Probepunkt aus DB geholt: " + this.probepkt);
        if (this.probearten == null)
            this.probearten = DatabaseQuery.getProbearten();
        if (this.klaeranlagen == null)
            this.klaeranlagen = DatabaseQuery.getKlaeranlage();
        if (this.sachbearbeiter == null)
            this.sachbearbeiter = DatabaseQuery.getEnabledSachbearbeiter();
    }

    // ── updateForm() ───────────────────────────────────────
    public void updateForm() throws RuntimeException {
        if (this.probearten != null)
            getProbePktArtBox().setModel(
                new DefaultComboBoxModel<>(this.probearten));
        if (this.klaeranlagen != null)
            getProbeKABox().setModel(
                new DefaultComboBoxModel<>(this.klaeranlagen));
        if (this.sachbearbeiter != null) {
            getSachbearbeiterBox().setModel(
                new DefaultComboBoxModel<>(this.sachbearbeiter));
            sachbearbeiterBox.setBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            getSachbearbeiterBox().setEditable(true);
        }
        if (this.probepkt != null) {
            getProbePktArtBox().setSelectedItem(this.probepkt.getProbeart());
            getProbeKABox().setSelectedItem(this.probepkt.getKlaeranlage());
            getSachbearbeiterBox().setSelectedItem(this.probepkt.getSachbearbeiter());
            if (this.probepkt.getNrProbepkt() != null)
                getProbePktNrFeld().setValue(this.probepkt.getNrProbepkt());
            getBrancheFeld().setText(this.probepkt.getBranche());
            getProbePktBeschreibungsArea().setText(
                this.hauptModul.getObjekt().getBeschreibung());
            this.probenahmenModel.setProbepunkt(this.probepkt);
            this.probenahmenModel.updateList();
            this.objektVerknuepfungModel.setObjekt(
                this.hauptModul.getObjekt());
        }
        setDirty(false);
    }

    // ── clearForm() ────────────────────────────────────────
    public void clearForm() {
        getProbePktNrFeld().setText(null);
        getProbePktBeschreibungsArea().setText(null);
        getKennummerFeld().setText("");
        getBrancheFeld().setText("");
    }

    // ── enableAll() ────────────────────────────────────────
    public void enableAll(boolean enabled) {
        getProbePktArtBox().setEnabled(enabled);
        getProbeKABox().setEnabled(enabled);
        getProbePktNrFeld().setEnabled(enabled);
        getProbePktBeschreibungsArea().setEnabled(enabled);
        getBrancheFeld().setEnabled(enabled);
        getProbenahmeTabelle().setEnabled(enabled);
        getKennummerFeld().setEnabled(enabled);
        getDatumsChooser().setEnabled(enabled);
        getAnlegenButton().setEnabled(enabled);
        getSavePktButton().setEnabled(enabled);
    }

    // ── getName() ──────────────────────────────────────────
    @Override
    public String getName() {
        return this.name;
    }

    // ── editProbenahme() ───────────────────────────────────
    public void editProbenahme(Probenahme probe) {
        ProbenEditor editDialog = new ProbenEditor(
            probe, this.hauptModul.getFrame(), false);
        editDialog.setLocationRelativeTo(this.hauptModul.getFrame());
        editDialog.setVisible(true);
        this.probenahmenModel.updateList();
    }

    // ── neueProbenahme() ───────────────────────────────────
    public void neueProbenahme(String kennNummer, Timestamp datum) {
        if (this.probepkt != null) {
            boolean exists = DatabaseQuery.probenahmeExists(kennNummer);
            if (!exists) {
                Probenahme probe = new Probenahme();
                probe.setKennummer(kennNummer);
                probe.setDatumDerEntnahme(datum);
                probe.setAnalysepositions(new HashSet<Analyseposition>());
                probe.setMessstelle(this.probepkt);
                ProbenEditor editDialog = new ProbenEditor(
                    probe, this.hauptModul.getFrame(), true);
                editDialog.setVisible(true);
                this.probenahmenModel.updateList();
            } else {
                this.hauptModul.getFrame().changeStatus(
                    "Eine Probenahme mit dieser Kennnummer existiert schon!",
                    HauptFrame.ERROR_COLOR);
            }
        } else {
            this.hauptModul.getFrame().changeStatus(
                "Fehler beim Anlegen: Kein Probepunkt!",
                HauptFrame.ERROR_COLOR);
        }
    }

    // ── doSavePanelData() ──────────────────────────────────
    protected boolean doSavePanelData() {
        boolean success;
        if (this.probepkt != null) {
            if (getProbePktArtBox().getSelectedItem() != null)
                this.probepkt.setProbeart(
                    (Probeart) getProbePktArtBox().getSelectedItem());
            if (getProbeKABox().getSelectedItem() != null)
                this.probepkt.setKlaeranlage(
                    (Klaeranlage) getProbeKABox().getSelectedItem());
            if (getSachbearbeiterBox().getSelectedItem() != null)
                this.probepkt.setSachbearbeiter(
                    (Sachbearbeiter) getSachbearbeiterBox().getSelectedItem());
            String branche = this.brancheFeld.getText();
            this.probepkt.setBranche("".equals(branche) ? null : branche);
            if (getProbePktNrFeld().getValue() != null)
                this.probepkt.setNrProbepkt(
                    ((IntegerField) getProbePktNrFeld()).getIntValue());
            success = this.probepkt.merge();
        } else {
            success = false;
        }
        return success;
    }

    // ── completeObjekt() ───────────────────────────────────
    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.probepkt == null) {
            this.probepkt = new Messstelle();
            this.probepkt.setObjekt(this.hauptModul.getObjekt());
            if (this.probepkt.merge()) {
                log.debug("Neuer Probepunkt " + this.probepkt + " gespeichert.");
                if (this.probepkt.getObjekt().getObjektarten().getId() == 40) {
                    this.sielhaut = new Sielhaut();
                    this.sielhaut.setMessstelle(probepkt);
                    this.sielhaut.merge();
                }
            }
        }
    }

    // ── Getter Methoden ────────────────────────────────────
    private JComboBox<Probeart> getProbePktArtBox() {
        if (this.probePktArtBox == null)
            this.probePktArtBox = new JComboBox<>();
        return this.probePktArtBox;
    }

    private JComboBox<Klaeranlage> getProbeKABox() {
        if (this.probeKABox == null)
            this.probeKABox = new JComboBox<>();
        return this.probeKABox;
    }

    private JComboBox<Sachbearbeiter> getSachbearbeiterBox() {
        if (this.sachbearbeiterBox == null)
            this.sachbearbeiterBox = new JComboBox<>();
        return this.sachbearbeiterBox;
    }

    private JTextField getBrancheFeld() {
        if (this.brancheFeld == null)
            this.brancheFeld = new LimitedTextField(50, "");
        return this.brancheFeld;
    }

    private JFormattedTextField getProbePktNrFeld() {
        if (this.probePktNrFeld == null)
            this.probePktNrFeld = new IntegerField();
        return this.probePktNrFeld;
    }

    private JTextArea getProbePktBeschreibungsArea() {
        if (this.probePktBeschreibungsArea == null) {
            this.probePktBeschreibungsArea = new JTextArea();
            this.probePktBeschreibungsArea.setLineWrap(true);
            this.probePktBeschreibungsArea.setWrapStyleWord(true);
            this.probePktBeschreibungsArea.setEditable(false);
            this.probePktBeschreibungsArea.setToolTipText(
                "Diese Beschreibung kann unter \"Objekt\" geändert werden.");
            this.probePktBeschreibungsArea.setBackground(this.getBackground());
        }
        return this.probePktBeschreibungsArea;
    }

    // ── getProbenahmeTabelle() ─────────────────────────────
    private JTable getProbenahmeTabelle() {
        if (this.probenahmeTabelle == null) {
            this.probenahmeTabelle = new JTable(this.probenahmenModel);
            this.probenahmeTabelle.getColumnModel().getColumn(0).setMaxWidth(75);
            this.probenahmeTabelle.getColumnModel().getColumn(1).setMaxWidth(70);
            this.probenahmeTabelle.getColumnModel().getColumn(2).setMaxWidth(60);
            this.probenahmeTabelle.getColumnModel().getColumn(3).setPreferredWidth(100);
            this.probenahmeTabelle.getColumnModel().getColumn(4).setPreferredWidth(100);
            this.probenahmeTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.probenahmeTabelle.setColumnSelectionAllowed(false);
            this.probenahmeTabelle.setRowSelectionAllowed(true);
            this.probenahmeTabelle.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = ProbepunktPanel.this.probenahmeTabelle
                                .rowAtPoint(origin);
                            Probenahme probe = ProbepunktPanel.this
                                .probenahmenModel.getRow(row);
                            editProbenahme(probe);
                        }
                    }
                });

            KeyStroke enterKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
            this.probenahmeTabelle.getInputMap().put(enterKeyStroke, "ENTER");
            Action editAction = new AbstractAction() {
                private static final long serialVersionUID = -7537228135751378632L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = ProbepunktPanel.this.probenahmeTabelle.getSelectedRow();
                    if (row != -1) {
                        Probenahme probe = ProbepunktPanel.this
                            .probenahmenModel.getRow(row);
                        editProbenahme(probe);
                    }
                }
            };
            this.probenahmeTabelle.getActionMap().put("ENTER", editAction);

            KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false);
            this.probenahmeTabelle.getInputMap().put(deleteKeyStroke, "DEL");
            Action removeAction = new AbstractAction() {
                private static final long serialVersionUID = -4910733866626541511L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = ProbepunktPanel.this.probenahmeTabelle.getSelectedRow();
                    if (row != -1
                        && ProbepunktPanel.this.probenahmeTabelle.getEditingRow() == -1) {
                        Probenahme probe = ProbepunktPanel.this
                            .probenahmenModel.getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Probenahme " + probe.getKennummer()
                            + " wirklich inkl. aller Analysen gelöscht werden?",
                            "Löschen bestätigen")) {
                            ProbepunktPanel.this.probenahmenModel.removeRow(row);
                            log.debug("Probe " + probe.getKennummer() + " wurde gelöscht!");
                        }
                    }
                }
            };
            this.probenahmeTabelle.getActionMap().put("DEL", removeAction);
        }
        return this.probenahmeTabelle;
    }

    // ── getKennummerFeld() ─────────────────────────────────
    private JTextField getKennummerFeld() {
        if (this.kennummerFeld == null)
            this.kennummerFeld = new LimitedTextField(50, "");
        return this.kennummerFeld;
    }

    // ── getDatumsChooser() ─────────────────────────────────
    private JDateChooser getDatumsChooser() {
        if (this.datumsChooser == null)
            this.datumsChooser = new JDateChooser(
                new Date(), DateUtils.FORMAT_DEFAULT);
        return this.datumsChooser;
    }

    // ── getAnlegenButton() ─────────────────────────────────
    private JButton getAnlegenButton() {
        if (this.anlegenButton == null) {
            this.anlegenButton = new JButton("Anlegen");
            this.anlegenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (getKennummerFeld().getText().trim().equals("")) {
                        getKennummerFeld().requestFocus();
                        ProbepunktPanel.this.hauptModul.getFrame()
                            .changeStatus("Leere Kennummer!", HauptFrame.ERROR_COLOR);
                    } else {
                        String kennNummer = getKennummerFeld().getText()
                            .trim().replaceAll(" ", "");
                        Timestamp datum = new Timestamp(
                            getDatumsChooser().getDate().getTime());
                        neueProbenahme(kennNummer, datum);
                    }
                }
            });
        }
        return this.anlegenButton;
    }

    // ── getSavePktButton() ─────────────────────────────────
    private JButton getSavePktButton() {
        if (this.savePktButton == null) {
            this.savePktButton = new JButton("Objekt speichern");
            this.savePktButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (hauptModul.saveAllTabs()) {
                        ProbepunktPanel.this.hauptModul.getFrame()
                            .changeStatus(
                                "Probepunkt " + ProbepunktPanel.this.probepkt.getId()
                                + " erfolgreich gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                    } else {
                        ProbepunktPanel.this.hauptModul.getFrame()
                            .changeStatus(
                                "Fehler beim Speichern von Probepunkt!",
                                HauptFrame.ERROR_COLOR);
                    }
                    ProbepunktPanel.this.hauptModul.fillForm();
                }
            });
        }
        return this.savePktButton;
    }

    // ── getObjektverknuepungTabelle() ──────────────────────
    private JTable getObjektverknuepungTabelle() {
        if (this.objektVerknuepfungModel == null) {
            this.objektVerknuepfungModel = new ObjektVerknuepfungModel(
                this.hauptModul.getObjekt());
            if (this.objektverknuepfungTabelle == null) {
                this.objektverknuepfungTabelle = new JTable(
                    this.objektVerknuepfungModel);
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
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getObjektverknuepungTabelle()
                                .rowAtPoint(origin);
                            if (row != -1) {
                                Objektverknuepfung obj =
                                    ProbepunktPanel.this
                                    .objektVerknuepfungModel.getRow(row);
                                if (obj.getObjektByIstVerknuepftMit().getId()
                                    .intValue() != ProbepunktPanel.this
                                        .hauptModul.getObjekt().getId().intValue())
                                    ProbepunktPanel.this.hauptModul.getManager()
                                        .getSettingsManager().setSetting(
                                        "auik.imc.edit_object",
                                        obj.getObjektByIstVerknuepftMit()
                                            .getId().intValue(), false);
                                else
                                    ProbepunktPanel.this.hauptModul.getManager()
                                        .getSettingsManager().setSetting(
                                        "auik.imc.edit_object",
                                        obj.getObjektByObjekt()
                                            .getId().intValue(), false);
                                ProbepunktPanel.this.hauptModul.getManager()
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
                (KeyStroke) getVerknuepfungLoeschAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getVerknuepfungLoeschAction().getValue(Action.NAME));
            this.objektverknuepfungTabelle.getActionMap().put(
                getVerknuepfungLoeschAction().getValue(Action.NAME),
                getVerknuepfungLoeschAction());
        }
        return this.objektverknuepfungTabelle;
    }

    // ── showVerknuepfungPopup() ────────────────────────────
    private void showVerknuepfungPopup(MouseEvent e) {
        if (this.verknuepfungPopup == null) {
            this.verknuepfungPopup = new JPopupMenu("Objekt");
            this.verknuepfungPopup.add(
                new JMenuItem(getVerknuepfungLoeschAction()));
        }
        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektverknuepfungTabelle.rowAtPoint(origin);
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
            this.verknuepfungLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 2362803114601855889L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung =
                            ProbepunktPanel.this.objektVerknuepfungModel.getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                            + "Hinweis: Die Aktion betrifft nur die Verknüpfung,"
                            + " die Objekte bleiben erhalten und können jederzeit"
                            + " neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (ProbepunktPanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                ProbepunktPanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                ProbepunktPanel.this.hauptModul.getFrame()
                                    .changeStatus("Konnte das Objekt nicht löschen!",
                                        HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.verknuepfungLoeschAction.putValue(
                Action.MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_L));
            this.verknuepfungLoeschAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }
        return this.verknuepfungLoeschAction;
    }

    // ── getSelectObjektButton() ────────────────────────────
    private JButton getSelectObjektButton() {
        if (this.selectObjektButton == null) {
            this.selectObjektButton = new JButton("Objekt auswählen");
            this.selectObjektButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ObjektChooser chooser = new ObjektChooser(
                        ProbepunktPanel.this.hauptModul.getFrame(),
                        ProbepunktPanel.this.probepkt.getObjekt(),
                        ProbepunktPanel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }

    // ── getPrintDeckblattButton() ──────────────────────────
    private JButton getPrintDeckblattButton() {
        if (this.printDeckblattButton == null) {
            this.printDeckblattButton = new JButton("Deckblatt drucken");
            this.printDeckblattButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showReport();
                }
            });
        }
        return this.printDeckblattButton;
    }

    // ── showReport() ───────────────────────────────────────
    public void showReport() {
        if (hauptModul.getObjekt().getId() != null) {
            SettingsManager sm = SettingsManager.getInstance();
            String fotoPath = sm.getSetting("auik.system.probepktpath_fotos");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", hauptModul.getObjekt().getId());
            String file = fotoPath + hauptModul.getObjekt().getId() + ".jpg";
            String id = hauptModul.getObjekt().getId().toString();
            if (id != null && new File(file).canRead()) {
                params.put("foto", new String(fotoPath + id + ".jpg"));
            } else {
                params.put("foto", new String(fotoPath + "kein_foto.jpg"));
            }
            try {
                File pdfFile = File.createTempFile(id, ".pdf");
                pdfFile.deleteOnExit();
                PDFExporter.getInstance().exportReport(
                    params,
                    PDFExporter.HANDAKTE_DECKBLATT,
                    pdfFile.getAbsolutePath());
            } catch (Exception ex) {
                GUIManager.getInstance().showErrorMessage(
                    "PDF generieren fehlgeschlagen.\n" + ex.getLocalizedMessage(),
                    "PDF generieren fehlgeschlagen");
            }
        } else {
            log.debug("Dem zu druckenden Probenahmepunkt fehlen Eingaben!");
        }
    }
}