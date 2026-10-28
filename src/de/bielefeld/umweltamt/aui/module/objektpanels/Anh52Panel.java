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
 * $Id: Anh52Panel.java,v 1.1.2.1 2010-11-23 10:25:57 u633d Exp $
 *
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.4  2006/10/17 09:10:59  u633d
 * small changeZ!
 *
 * Revision 1.3  2006/10/17 09:09:13  u633d
 * small changeZ!
 *
 * Revision 1.2  2006/10/17 09:00:36  u633d
 * Anhang 52
 *
 * Revision 1.1  2006/10/17 07:54:29  u633d
 * Anhang 52 (Chemische Wäschereien) haben nun einen eigenen Tab.
 *
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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das Panel zum Bearbeiten von Chemischen Wäschereien
 * @author BI018711
 */
public class Anh52Panel extends ObjectPanel {
    private static final long serialVersionUID = -1240254202213465142L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private Anfallstelle anfallstelle;

    // Widgets
    private JFormattedTextField nrbetriebsstaetteFeld = null;
    private JTextField firmennameFeld = null;
    private JTextField telefonFeld = null;
    private JTextField telefaxFeld = null;
    private JTextField ansprechpartnerFeld = null;
    private TextFieldDateChooser datumGenChooser = null;
    private JTextArea BemerkungenArea = null;
    private JButton saveAnh52Button = null;

    // Daten
    private Anh52Fachdaten fachdaten = null;

    @SuppressWarnings("deprecation")
    public Anh52Panel(BasisObjektBearbeiten hauptModul,
        Anfallstelle anfallstelle) {
        this.name = "Chemische Wäscherei";
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

        // Nr. der Betriebsstätte
        gbc.gridy = 1; gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(
            new JLabel("Nr. der Betriebsstätte"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getNrBetriebsstaette().setPreferredSize(
            new java.awt.Dimension(200,
                getNrBetriebsstaette()
                    .getPreferredSize().height));
        innerPanel.add(getNrBetriebsstaette(), gbc);

        // Firmenname
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Firmenname"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getFirmennameFeld().setPreferredSize(
            new java.awt.Dimension(200,
                getFirmennameFeld()
                    .getPreferredSize().height));
        innerPanel.add(getFirmennameFeld(), gbc);

        // Ansprechpartner
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Ansprechpartner"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getAnsprechpartnerFeld().setPreferredSize(
            new java.awt.Dimension(200,
                getAnsprechpartnerFeld()
                    .getPreferredSize().height));
        innerPanel.add(getAnsprechpartnerFeld(), gbc);

        // Telefon
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Telefon"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getTelefonFeld().setPreferredSize(
            new java.awt.Dimension(200,
                getTelefonFeld()
                    .getPreferredSize().height));
        innerPanel.add(getTelefonFeld(), gbc);

        // Telefax
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Telefax"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getTelefaxFeld().setPreferredSize(
            new java.awt.Dimension(200,
                getTelefaxFeld()
                    .getPreferredSize().height));
        innerPanel.add(getTelefaxFeld(), gbc);

        // Genehmigung
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        innerPanel.add(new JLabel("Genehmigung"), gbc);
        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        getGenehmigungDatum().setPreferredSize(
            new java.awt.Dimension(200,
                getGenehmigungDatum()
                    .getPreferredSize().height));
        innerPanel.add(getGenehmigungDatum(), gbc);

        // ── SEPARATOR: Bemerkungen ────────────────────────
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2; gbc.weightx = 1.0;
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
        gbc.gridy = 8; gbc.gridwidth = 2;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(bemerkungsScroller, gbc);

        // ── ButtonBar ─────────────────────────────────────
        JPanel buttonBar = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonBar.add(getSaveAnh52Button());
        gbc.gridy = 9; gbc.gridwidth = 2;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(buttonBar, gbc);

        // ── Wrapper ───────────────────────────────────────
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(innerPanel, BorderLayout.WEST);
        this.setLayout(new BorderLayout());
        this.add(wrapperPanel, BorderLayout.NORTH);

        addChangeListeners(
            getNrBetriebsstaette(),
            getFirmennameFeld(),
            getTelefonFeld(),
            getTelefaxFeld(),
            getAnsprechpartnerFeld(),
            getGenehmigungDatum(),
            getBemerkungenArea());
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
        if (fachdaten == null)
            this.fachdaten = new Anh52Fachdaten();
        this.anfallstelle = anfallstelle;
        this.fachdaten.setAnfallstelle(this.anfallstelle);
    }

    // ── doSavePanelData() ──────────────────────────────────
    protected boolean doSavePanelData() {
        boolean success;

        String bemerkungen = BemerkungenArea.getText();
        fachdaten.setBemerkungen(
            "".equals(bemerkungen) ? null : bemerkungen);

        Integer nrbetriebst =
            ((IntegerField) nrbetriebsstaetteFeld)
                .getIntValue();
        fachdaten.setNrbetriebsstaette(nrbetriebst);

        Date genehmigung = datumGenChooser.getDate();
        fachdaten.setDatumgenehmigung(genehmigung);

        String ansprechpartnerString =
            ansprechpartnerFeld.getText();
        fachdaten.setAnsprechpartner(
            "".equals(ansprechpartnerString)
                ? null : ansprechpartnerString);

        String telefonString = telefonFeld.getText();
        fachdaten.setTelefon(
            "".equals(telefonString) ? null : telefonString);

        String telefaxString = telefaxFeld.getText();
        fachdaten.setTelefax(
            "".equals(telefaxString) ? null : telefaxString);

        String firmennameString = firmennameFeld.getText();
        fachdaten.setFirmenname(
            "".equals(firmennameString)
                ? null : firmennameString);

        success = this.fachdaten.merge();
        if (success) {
            log.debug("Anh 52 Objekt "
                + fachdaten.getId() + " gespeichert.");
        } else {
            log.debug("Anh 52 Objekt " + fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    // ── enableAll() ────────────────────────────────────────
    public void enableAll(boolean enabled) {
        getBemerkungenArea().setEnabled(enabled);
        getNrBetriebsstaette().setEnabled(enabled);
        getFirmennameFeld().setEnabled(enabled);
        getTelefonFeld().setEnabled(enabled);
        getTelefaxFeld().setEnabled(enabled);
        getAnsprechpartnerFeld().setEnabled(enabled);
        getGenehmigungDatum().setEnabled(enabled);
    }

    // ── clearForm() ────────────────────────────────────────
    public void clearForm() {
        getNrBetriebsstaette().setText(null);
        getFirmennameFeld().setText(null);
        getTelefonFeld().setText(null);
        getTelefaxFeld().setText(null);
        getAnsprechpartnerFeld().setText(null);
        getGenehmigungDatum().setDate(null);
        getBemerkungenArea().setText(null);
    }

    // ── updateForm(Anfallstelle) ───────────────────────────
    public void updateForm(Anfallstelle anfallstelle)
        throws RuntimeException {
        this.fachdaten =
            anfallstelle.getAnh52Fachdatens()
                .iterator().next();
        if (fachdaten != null) {
            if (fachdaten.getBemerkungen() != null)
                getBemerkungenArea().setText(
                    fachdaten.getBemerkungen());
            if (fachdaten.getNrbetriebsstaette() != null)
                getNrBetriebsstaette().setText(
                    fachdaten.getNrbetriebsstaette()
                        .toString());
            if (fachdaten.getFirmenname() != null)
                getFirmennameFeld().setText(
                    fachdaten.getFirmenname());
            if (fachdaten.getTelefon() != null)
                getTelefonFeld().setText(
                    fachdaten.getTelefon());
            if (fachdaten.getTelefax() != null)
                getTelefaxFeld().setText(
                    fachdaten.getTelefax());
            if (fachdaten.getAnsprechpartner() != null)
                getAnsprechpartnerFeld().setText(
                    fachdaten.getAnsprechpartner());
            if (fachdaten.getDatumgenehmigung() != null)
                getGenehmigungDatum().setDate(
                    fachdaten.getDatumgenehmigung());
        }
        setDirty(false);
    }

    // ── fetchFormData() ────────────────────────────────────
    public void fetchFormData() throws RuntimeException {
        Set<Anfallstelle> list =
            this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh52Fachdaten.findByAnfallstelleId(
            list.iterator().next().getId());
        log.debug("Anhang 52 Objekt aus DB geholt: "
            + this.fachdaten);
    }

    // ── getName() ──────────────────────────────────────────
    @Override
    public String getName() {
        return name;
    }

    // ── getSaveAnh52Button() ───────────────────────────────
    private JButton getSaveAnh52Button() {
        if (saveAnh52Button == null) {
            saveAnh52Button = new JButton("Speichern");
            saveAnh52Button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (hauptModul.saveAllTabs()) {
                            hauptModul.getFrame()
                                .changeStatus(
                                "Anh 52 Objekt "
                                + fachdaten.getId()
                                + " erfolgreich"
                                + " gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                        } else {
                            hauptModul.getFrame()
                                .changeStatus(
                                "Fehler beim Speichern"
                                + " des Anh 52 Objekt!",
                                HauptFrame.ERROR_COLOR);
                        }
                        hauptModul.fillForm();
                    }
                });
        }
        return saveAnh52Button;
    }

    // ── getNrBetriebsstaette() ─────────────────────────────
    private JFormattedTextField getNrBetriebsstaette() {
        if (nrbetriebsstaetteFeld == null)
            nrbetriebsstaetteFeld = new IntegerField();
        return nrbetriebsstaetteFeld;
    }

    // ── getFirmennameFeld() ────────────────────────────────
    private JTextField getFirmennameFeld() {
        if (firmennameFeld == null)
            firmennameFeld = new LimitedTextField(50);
        return firmennameFeld;
    }

    // ── getTelefonFeld() ───────────────────────────────────
    private JTextField getTelefonFeld() {
        if (telefonFeld == null)
            telefonFeld = new LimitedTextField(50);
        return telefonFeld;
    }

    // ── getTelefaxFeld() ───────────────────────────────────
    private JTextField getTelefaxFeld() {
        if (telefaxFeld == null)
            telefaxFeld = new LimitedTextField(50);
        return telefaxFeld;
    }

    // ── getAnsprechpartnerFeld() ───────────────────────────
    private JTextField getAnsprechpartnerFeld() {
        if (ansprechpartnerFeld == null)
            ansprechpartnerFeld = new LimitedTextField(50);
        return ansprechpartnerFeld;
    }

    // ── getGenehmigungDatum() ──────────────────────────────
    private TextFieldDateChooser getGenehmigungDatum() {
        if (datumGenChooser == null)
            datumGenChooser = new TextFieldDateChooser();
        return datumGenChooser;
    }

    // ── getBemerkungenArea() ───────────────────────────────
    private JTextArea getBemerkungenArea() {
        if (BemerkungenArea == null) {
            BemerkungenArea = new LimitedTextArea(252);
            BemerkungenArea.setLineWrap(true);
            BemerkungenArea.setWrapStyleWord(true);
        }
        return BemerkungenArea;
    }
}