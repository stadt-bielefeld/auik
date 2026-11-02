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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Entsorger;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.EntsorgerEditor;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Zahnarzt"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class Anh50Panel extends ObjectPanel {
    private static final long serialVersionUID = 7997458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private Anfallstelle anfallstelle;

    // Widgets
    private JTextField gefaehrdungsklasseFeld = null;
    private JCheckBox erloschenCheck = null;
    private JTextArea anh50BemerkungArea = null;
    private TextFieldDateChooser antragDatum = null;
    private TextFieldDateChooser genehmigungDatum = null;
    private JToolBar entsorBearbToolBar = null;
    private JButton entsorgBearbButton = null;
    private JButton saveAnh50Button = null;
    private JComboBox<Entsorger> entsorgerBox;

    // Daten
    private Anh50Fachdaten fachdaten = null;
    private Entsorger[] entsorg = null;

    // Listener
    private ActionListener editButtonListener;

    @SuppressWarnings("deprecation")
    public Anh50Panel(BasisObjektBearbeiten hauptModul,
        Anfallstelle anfallstelle) {
        this.name = "Zahnarzt";
        this.hauptModul = hauptModul;
        this.anfallstelle = anfallstelle;

        // ── InnerPanel ────────────────────────────────────
        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 5);

        // ── SEPARATOR: Fachdaten ──────────────────────────
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Fachdaten"), gbc);

        // Antrag
        gbc.gridy = 1; gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Antrag"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getAntragDatum().setPreferredSize(
            new java.awt.Dimension(200,
                getAntragDatum().getPreferredSize().height));
        innerPanel.add(getAntragDatum(), gbc);

        // Genehmigung
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Genehmigung"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getGenehmigungDatum().setPreferredSize(
            new java.awt.Dimension(200,
                getGenehmigungDatum().getPreferredSize().height));
        innerPanel.add(getGenehmigungDatum(), gbc);

        // Gefährdungsklasse
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Gefährdungsklasse"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getGefaehrdungsklasseFeld().setPreferredSize(
            new java.awt.Dimension(200,
                getGefaehrdungsklasseFeld().getPreferredSize().height));
        innerPanel.add(getGefaehrdungsklasseFeld(), gbc);

        // Erloschen Checkbox
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        innerPanel.add(getErloschenCheck(), gbc);

        // ── SEPARATOR: Entsorger ──────────────────────────
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Entsorger"), gbc);

        // EntsorgerBox + BearbToolBar
        gbc.gridy = 6; gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Entsorger"), gbc);

        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getEntsorgerBox().setPreferredSize(
            new java.awt.Dimension(200,
                getEntsorgerBox().getPreferredSize().height));
        innerPanel.add(getEntsorgerBox(), gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        innerPanel.add(getEntsorBearbToolBar(), gbc);

        // ── SEPARATOR: Bemerkungen ────────────────────────
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 3; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Bemerkungen"), gbc);

        // BemerkungsArea
        JScrollPane bemerkungsScroller = new JScrollPane(
            getAnh50BemerkungArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bemerkungsScroller.setPreferredSize(
            new java.awt.Dimension(0, 80));
        bemerkungsScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 80));
        gbc.gridy = 8; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(bemerkungsScroller, gbc);

        // ── ButtonBar ─────────────────────────────────────
        JPanel buttonBar = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonBar.add(getSaveAnh50Button());
        gbc.gridy = 9; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(buttonBar, gbc);

        // ── Wrapper ───────────────────────────────────────
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(innerPanel, BorderLayout.WEST);
        this.setLayout(new BorderLayout());
        this.add(wrapperPanel, BorderLayout.NORTH);

        addChangeListeners(
            getGefaehrdungsklasseFeld(),
            getAnh50BemerkungArea(),
            getAntragDatum(),
            getGenehmigungDatum(),
            getErloschenCheck());
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
        sepPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        return sepPanel;
    }

    // ── fetchFormData() ────────────────────────────────────
    public void fetchFormData() throws RuntimeException {
        Set<Anfallstelle> list =
            this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh50Fachdaten.findByAnfallstelleId(
            list.iterator().next().getId());
        log.debug("Zahnarzt aus DB geholt: " + this.fachdaten);
        if (this.entsorg == null || this.entsorg.length == 0) {
            this.entsorg = DatabaseQuery.getEntsorger();
        }
    }

    // ── updateForm(Anfallstelle) ───────────────────────────
    public void updateForm(Anfallstelle anfallstelle)
        throws RuntimeException {
        if (this.entsorg != null) {
            getEntsorgerBox().setModel(
                new DefaultComboBoxModel<>(this.entsorg));
        }
        this.fachdaten =
            anfallstelle.getAnh50Fachdatens().iterator().next();
        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null)
                getAnh50BemerkungArea().setText(
                    this.fachdaten.getBemerkungen());
            if (this.fachdaten.getDatumantrag() != null)
                getAntragDatum().setDate(
                    this.fachdaten.getDatumantrag());
            if (this.fachdaten.getGenehmigung() != null)
                getGenehmigungDatum().setDate(
                    this.fachdaten.getGenehmigung());
            if (this.fachdaten.getGefaehrdungsklasse() != null)
                getGefaehrdungsklasseFeld().setText(
                    this.fachdaten.getGefaehrdungsklasse()
                        .toString());
            if (this.fachdaten.getEntsorger() != null)
                getEntsorgerBox().setSelectedItem(
                    this.fachdaten.getEntsorger());
            if (this.fachdaten.getErloschen() != null)
                getErloschenCheck().setSelected(
                    this.fachdaten.getErloschen());
        }
        setDirty(false);
    }

    // ── clearForm() ────────────────────────────────────────
    public void clearForm() {
        getGefaehrdungsklasseFeld().setText(null);
        getAnh50BemerkungArea().setText(null);
        getAntragDatum().setDate(null);
        getGenehmigungDatum().setDate(null);
        getErloschenCheck().setSelected(false);
    }

    // ── enableAll() ────────────────────────────────────────
    public void enableAll(boolean enabled) {
        // intentionally left blank
    }

    // ── getName() ──────────────────────────────────────────
    @Override
    public String getName() {
        return this.name;
    }

    // ── doSavePanelData() ──────────────────────────────────
    protected boolean doSavePanelData() {
        boolean success;
        String bemerkungen = this.anh50BemerkungArea.getText();
        this.fachdaten.setBemerkungen(
            "".equals(bemerkungen) ? null : bemerkungen);

        Date antrag = this.antragDatum.getDate();
        this.fachdaten.setDatumantrag(antrag);

        this.fachdaten.setErloschen(
            getErloschenCheck().isSelected());

        String gefaehrdungsklasse =
            this.gefaehrdungsklasseFeld.getText();
        this.fachdaten.setGefaehrdungsklasse(
            "".equals(gefaehrdungsklasse)
                ? null : gefaehrdungsklasse);

        Date genehmigung = this.genehmigungDatum.getDate();
        this.fachdaten.setGenehmigung(genehmigung);

        if (getEntsorgerBox().getSelectedItem() != null) {
            this.fachdaten.setEntsorger(
                (Entsorger) getEntsorgerBox().getSelectedItem());
            log.debug("Entsorger " + this.fachdaten.getEntsorger()
                + " zugeordnet.");
        } else {
            getEntsorgerBox().setSelectedIndex(1);
        }
        this.fachdaten.setEntsorger(
            (Entsorger) getEntsorgerBox().getSelectedItem());

        success = this.fachdaten.merge();
        if (success) {
            log.debug("Zahnarzt "
                + this.fachdaten.getAnfallstelle()
                    .getObjekt().getBetreiberid().getName()
                + " gespeichert.");
        } else {
            log.debug("Zahnarzt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    // ── completeObjekt(Anfallstelle) ───────────────────────
    public void completeObjekt(Anfallstelle anfallstelle) {
        if (this.fachdaten == null) {
            this.fachdaten = new Anh50Fachdaten();
        }
        this.anfallstelle = anfallstelle;
        this.fachdaten.setAnfallstelle(this.anfallstelle);
        if (this.entsorg == null || this.entsorg.length == 0) {
            this.entsorg = DatabaseQuery.getEntsorger();
            getEntsorgerBox().setModel(
                new DefaultComboBoxModel<>(this.entsorg));
        }
        Entsorger entsorger = Entsorger.findById(
            DatabaseConstants.ANH_ENTSORGER_ID_UNBEKANNT);
        this.fachdaten.setEntsorger(entsorger);
    }

    // ── Getter Methoden ────────────────────────────────────
    private JTextArea getAnh50BemerkungArea() {
        if (this.anh50BemerkungArea == null) {
            this.anh50BemerkungArea = new LimitedTextArea(255);
            this.anh50BemerkungArea.setLineWrap(true);
            this.anh50BemerkungArea.setWrapStyleWord(true);
        }
        return this.anh50BemerkungArea;
    }

    private TextFieldDateChooser getAntragDatum() {
        if (this.antragDatum == null)
            this.antragDatum = new TextFieldDateChooser();
        return this.antragDatum;
    }

    private JCheckBox getErloschenCheck() {
        if (this.erloschenCheck == null)
            this.erloschenCheck = new JCheckBox("Erloschen");
        return this.erloschenCheck;
    }

    private JTextField getGefaehrdungsklasseFeld() {
        if (this.gefaehrdungsklasseFeld == null)
            this.gefaehrdungsklasseFeld = new LimitedTextField(50);
        return this.gefaehrdungsklasseFeld;
    }

    private TextFieldDateChooser getGenehmigungDatum() {
        if (this.genehmigungDatum == null)
            this.genehmigungDatum = new TextFieldDateChooser();
        return this.genehmigungDatum;
    }

    private JComboBox<Entsorger> getEntsorgerBox() {
        if (this.entsorgerBox == null)
            this.entsorgerBox = new JComboBox<>();
        return this.entsorgerBox;
    }

    // ── getSaveAnh50Button() ───────────────────────────────
    private JButton getSaveAnh50Button() {
        if (this.saveAnh50Button == null) {
            this.saveAnh50Button = new JButton("Speichern");
            this.saveAnh50Button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableAll(false);
                        if (hauptModul.saveAllTabs()) {
                            Anh50Panel.this.hauptModul.getFrame()
                                .changeStatus(
                                "Zahnarzt "
                                + Anh50Panel.this.fachdaten.getId()
                                + " erfolgreich gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                        } else {
                            Anh50Panel.this.hauptModul.getFrame()
                                .changeStatus(
                                "Fehler beim Speichern"
                                + " des Zahnarztes!",
                                HauptFrame.ERROR_COLOR);
                        }
                        Anh50Panel.this.hauptModul.fillForm();
                    }
                });
        }
        return this.saveAnh50Button;
    }

    // ── getEntsorgBearbButton() ────────────────────────────
    private JButton getEntsorgBearbButton() {
        if (this.entsorgBearbButton == null) {
            this.entsorgBearbButton = new JButton(
                AuikUtils.getIcon(16, "edit.png", ""));
            this.entsorgBearbButton.setHorizontalAlignment(
                SwingConstants.CENTER);
            this.entsorgBearbButton.setToolTipText(
                "Entsorger bearbeiten");
            this.entsorgBearbButton.setActionCommand(
                "entsorger_edit");
            this.entsorgBearbButton.addActionListener(
                getEditButtonListener());
        }
        return this.entsorgBearbButton;
    }

    // ── getEntsorBearbToolBar() ────────────────────────────
    private JToolBar getEntsorBearbToolBar() {
        if (this.entsorBearbToolBar == null) {
            this.entsorBearbToolBar = new JToolBar();
            this.entsorBearbToolBar.setFloatable(false);
            this.entsorBearbToolBar.putClientProperty(
                "JToolBar.isRollover", Boolean.TRUE);
            this.entsorBearbToolBar.add(getEntsorgBearbButton());
        }
        return this.entsorBearbToolBar;
    }

    // ── getEditButtonListener() ────────────────────────────
    private ActionListener getEditButtonListener() {
        if (this.editButtonListener == null) {
            this.editButtonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String action = e.getActionCommand();
                    Entsorger entsorg =
                        (Entsorger) getEntsorgerBox()
                            .getSelectedItem();
                    if ("entsorger_edit".equals(action)
                        && entsorg != null) {
                        EntsorgerEditor editDialog =
                            new EntsorgerEditor(entsorg,
                                Anh50Panel.this.hauptModul
                                    .getFrame());
                        editDialog.setLocationRelativeTo(
                            Anh50Panel.this.hauptModul
                                .getFrame());
                        editDialog.setVisible(true);
                        editDialog.setEnabled(true);
                        entsorg = editDialog.getEntsorger();
                    }
                }
            };
        }
        return this.editButtonListener;
    }
}