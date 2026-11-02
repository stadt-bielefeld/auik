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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
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
    public Anh56Panel(BasisObjektBearbeiten hauptModul,
        Anfallstelle anfallstelle) {
        this.name = "Druckerei";
        this.hauptModul = hauptModul;
        this.anfallstelle = anfallstelle;

        // ── InnerPanel ────────────────────────────────────
        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 5);

        // ── SEPARATOR: Fachdaten ──────────────────────────
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 3; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Fachdaten"), gbc);

        // Zeile 1: Druckverfahren | Abwasseranfall
        addRow(innerPanel, gbc, 1,
            "Druckverfahren", getDruckverfahrenFeld(),
            getAbwasseranfallCheck());

        // Zeile 2: Wasserverbrauch | ABA
        addRow(innerPanel, gbc, 2,
            "Wasserverbrauch", getVerbrauchFeld(),
            getAbaCheck());

        // Zeile 3: Entsorgung | Genehmigungspflicht
        addRow(innerPanel, gbc, 3,
            "Entsorgung", getEntsorgungFeld(),
            getGenpflichtCheck());

        // Zeile 4: Datum 58er Genehmigung
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(
            new JLabel("Datum 58er Genehmigung"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getGen58Datum().setPreferredSize(
            new java.awt.Dimension(200,
                getGen58Datum().getPreferredSize().height));
        innerPanel.add(getGen58Datum(), gbc);

        // Zeile 5: Datum 59er Genehmigung
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(
            new JLabel("Datum 59er Genehmigung"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getGen59Datum().setPreferredSize(
            new java.awt.Dimension(200,
                getGen59Datum().getPreferredSize().height));
        innerPanel.add(getGen59Datum(), gbc);

        // ── SEPARATOR: Bemerkungen ────────────────────────
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 3; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Bemerkungen"), gbc);

        // BemerkungsArea
        JScrollPane bemerkungsScroller = new JScrollPane(
            getBemerkungenArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bemerkungsScroller.setPreferredSize(
            new java.awt.Dimension(0, 80));
        bemerkungsScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 80));
        gbc.gridy = 7; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(bemerkungsScroller, gbc);

        // ── ButtonBar ─────────────────────────────────────
        JPanel buttonBar = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonBar.add(getSaveAnh56Button());
        gbc.gridy = 8; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(buttonBar, gbc);

        // ── Wrapper ───────────────────────────────────────
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(innerPanel, BorderLayout.WEST);
        this.setLayout(new BorderLayout());
        this.add(wrapperPanel, BorderLayout.NORTH);

        addChangeListeners(
            getBemerkungenArea(),
            getDruckverfahrenFeld(),
            getVerbrauchFeld(),
            getEntsorgungFeld(),
            getGen58Datum(),
            getGen59Datum(),
            getAbaCheck(),
            getGenpflichtCheck(),
            getAbwasseranfallCheck());
    }

    // ── Hilfsmethode: Label + Feld + Checkbox ─────────────
    private void addRow(JPanel panel, GridBagConstraints gbc,
        int row, String label,
        JTextField field, JCheckBox check) {
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

    // ── completeObjekt() ───────────────────────────────────
    public void completeObjekt(Anfallstelle anfallstelle) {
        if (this.fachdaten == null)
            this.fachdaten = new Anh56Fachdaten();
        this.anfallstelle = anfallstelle;
        this.fachdaten.setAnfallstelle(this.anfallstelle);
    }

    // ── doSavePanelData() ──────────────────────────────────
    protected boolean doSavePanelData() {
        boolean success;

        String bemerkungen = this.BemerkungenArea.getText();
        this.fachdaten.setBemerkungen(
            "".equals(bemerkungen) ? null : bemerkungen);

        String druckverf = this.druckverfahrenFeld.getText();
        this.fachdaten.setDruckverfahren(
            "".equals(druckverf) ? null : druckverf);

        String verbrauch = this.verbrauchFeld.getText();
        this.fachdaten.setVerbrauch(
            "".equals(verbrauch) ? null : verbrauch);

        String entsorgung = this.entsorgungFeld.getText();
        this.fachdaten.setEntsorgung(
            "".equals(entsorgung) ? null : entsorgung);

        Date gen58 = this.gen58Datum.getDate();
        this.fachdaten.setGen58(gen58);

        Date gen59 = this.gen59Datum.getDate();
        this.fachdaten.setGen59(gen59);

        this.fachdaten.setAba(getAbaCheck().isSelected());
        this.fachdaten.setGenpflicht(
            getGenpflichtCheck().isSelected());
        this.fachdaten.setAbwasseranfall(
            getAbwasseranfallCheck().isSelected());

        success = fachdaten.merge();
        if (success) {
            log.debug("Anh 56 Objekt "
                + this.fachdaten.getId() + " gespeichert.");
        } else {
            log.debug("Anh 56 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    // ── enableAll() ────────────────────────────────────────
    public void enableAll(boolean enabled) {
        // intentionally left blank
    }

    // ── clearForm() ────────────────────────────────────────
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

    // ── updateForm(Anfallstelle) ───────────────────────────
    public void updateForm(Anfallstelle anfallstelle)
        throws RuntimeException {
        this.fachdaten =
            anfallstelle.getAnh56Fachdatens()
                .iterator().next();
        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null)
                getBemerkungenArea().setText(
                    this.fachdaten.getBemerkungen());
            if (this.fachdaten.getDruckverfahren() != null)
                getDruckverfahrenFeld().setText(
                    this.fachdaten.getDruckverfahren());
            if (this.fachdaten.getVerbrauch() != null)
                getVerbrauchFeld().setText(
                    this.fachdaten.getVerbrauch());
            if (this.fachdaten.getEntsorgung() != null)
                getEntsorgungFeld().setText(
                    this.fachdaten.getEntsorgung());
            if (this.fachdaten.getGen58() != null)
                getGen58Datum().setDate(
                    this.fachdaten.getGen58());
            if (this.fachdaten.getGen59() != null)
                getGen59Datum().setDate(
                    this.fachdaten.getGen59());
            if (this.fachdaten.getAba() != null)
                getAbaCheck().setSelected(
                    this.fachdaten.getAba());
            if (this.fachdaten.getGenpflicht() != null)
                getGenpflichtCheck().setSelected(
                    this.fachdaten.getGenpflicht());
            if (this.fachdaten.getAbwasseranfall() != null)
                getAbwasseranfallCheck().setSelected(
                    this.fachdaten.getAbwasseranfall());
        }
        setDirty(false);
    }

    // ── fetchFormData() ────────────────────────────────────
    public void fetchFormData() throws RuntimeException {
        Set<Anfallstelle> list =
            this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh56Fachdaten.findByAnfallstelleId(
            list.iterator().next().getId());
        log.debug("Anhang 56 Objekt aus DB geholt: ID"
            + this.fachdaten);
    }

    // ── getName() ──────────────────────────────────────────
    @Override
    public String getName() {
        return this.name;
    }

    // ── getSaveAnh56Button() ───────────────────────────────
    private JButton getSaveAnh56Button() {
        if (this.saveAnh56Button == null) {
            this.saveAnh56Button = new JButton("Speichern");
            this.saveAnh56Button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableAll(false);
                        if (hauptModul.saveAllTabs()) {
                            Anh56Panel.this.hauptModul
                                .getFrame().changeStatus(
                                "Anh 56 Objekt "
                                + Anh56Panel.this.fachdaten
                                    .getId()
                                + " erfolgreich"
                                + " gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                        } else {
                            Anh56Panel.this.hauptModul
                                .getFrame().changeStatus(
                                "Fehler beim Speichern"
                                + " des Anh 56 Objekt!",
                                HauptFrame.ERROR_COLOR);
                        }
                        Anh56Panel.this.hauptModul.fillForm();
                    }
                });
        }
        return this.saveAnh56Button;
    }

    // ── Getter Methoden ────────────────────────────────────
    private JCheckBox getAbaCheck() {
        if (this.abaCheck == null)
            this.abaCheck =
                new JCheckBox("Abwasserbehandlung");
        return this.abaCheck;
    }

    private JCheckBox getAbwasseranfallCheck() {
        if (this.abwasseranfallCheck == null)
            this.abwasseranfallCheck =
                new JCheckBox("Abwasseranfall");
        return this.abwasseranfallCheck;
    }

    private JTextField getDruckverfahrenFeld() {
        if (this.druckverfahrenFeld == null)
            this.druckverfahrenFeld =
                new LimitedTextField(150);
        return this.druckverfahrenFeld;
    }

    private JTextField getEntsorgungFeld() {
        if (this.entsorgungFeld == null)
            this.entsorgungFeld = new LimitedTextField(150);
        return this.entsorgungFeld;
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

    private JCheckBox getGenpflichtCheck() {
        if (this.genpflichtCheck == null)
            this.genpflichtCheck =
                new JCheckBox("Genehmigungspflicht");
        return this.genpflichtCheck;
    }

    private JTextField getVerbrauchFeld() {
        if (this.verbrauchFeld == null)
            this.verbrauchFeld = new LimitedTextField(150);
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

