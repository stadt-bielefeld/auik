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
 * $Id: Anh49Panel.java,v 1.1.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 27.04.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.3  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/07/23 06:55:22  u633d
 * Anh49 Auswertung und Sielhautimport neu
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.4  2005/06/13 14:02:16  u633z
 * - Nicht vorhandenes Fachdaten-Objekt wird besser behandelt
 *
 * Revision 1.3  2005/06/10 11:06:52  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.2  2005/06/03 11:05:05  u633z
 * - Datenbank-Zugriffe (speziell Speichern-Methoden) in Mappings verlagert.
 *
 * Revision 1.1  2005/05/30 08:35:42  u633z
 * - Aufgeräumt, mehrere neue Packages, Klassen sortiert
 *
 * Revision 1.6  2005/05/20 09:00:54  u633z
 * Anhang 49 und Anh49-Abscheidetail-Tabs verbessert und verschönert
 *
 * Revision 1.5  2005/05/19 15:37:51  u633d
 * Abscheiderdetails-Zwischenstand
 *
 * Revision 1.4  2005/05/19 12:53:44  u633d
 * Anhang 49 neue Mapings
 *
 * Revision 1.3  2005/05/17 07:53:43  u633d
 * Anhang 49 Ergänzungen
 *
 * Revision 1.2  2005/05/12 06:39:18  u633z
 * JavaDoc Kommentar verbessert, Header angepasst
 *
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.AbscheiderEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.Anh49AbscheiderModel;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Anhang 49"-Tab des BasisObjektBearbeiten-Moduls.
 * @author Gerd Genuit
 */
public class Anh49Panel extends AbstractAnhangPanel {
    private static final long serialVersionUID = 2262140075740338093L;

    private final String ANSPRECHPARTNER = "Ansprechpartner:in";
    private final String SACHKUNDE_LFA = "Sachkunde LFA";
    private final String ANALYSEMONAT = "Analysemonat";
    private final String BEMERKUNGEN = "Bemerkungen";
    private final String GENEHMIGUNGSDATUM = "Genehmigungsdatum";
    private final String AENDERUNGSGENEHMIGUNGSDATUM = "Änderungsgen.-datum";
    private final String ABGEMELDET = "abgemeldet";
    private final String ABWASSERFREI = "abwasserfrei";
    private final String E_SATZUNG = "Überwachung nach E-Satzung";
    private final String SICHERHEITSABSCHEIDER = "Sicherheitsabscheider";
    private final String WIEDERVORLAGEDATUM = "Wiedervorlagedatum";
    private final String SPEICHERN = "Speichern";

    private JButton neuerAbscheider = null;
    private JButton saveAnh49Button = null;

    // Daten
    private Anh49Fachdaten fachdaten = null;
    private Anh49AbscheiderModel abscheiderModel;
    private JTable abscheiderTabelle;
    private Action abscheiderLoeschAction;
    private Action abscheiderNeuAction;
    private JPopupMenu abscheiderPopup;

    @SuppressWarnings("deprecation")
    public Anh49Panel(BasisObjektBearbeiten hauptModul) {
        super("Abscheider", hauptModul);

        abscheiderModel = new Anh49AbscheiderModel();
        abscheiderModel.setFachdaten(fachdaten);

        TableFocusListener tfl = TableFocusListener.getInstance();
        getAbscheiderTabelle().addFocusListener(tfl);

        // ── Widgets registrieren ──────────────────────────
        super.addComponent(this.ANSPRECHPARTNER, new LimitedTextField(50));
        super.addComponent(this.SACHKUNDE_LFA, new LimitedTextField(50));
        super.addComponent(this.ANALYSEMONAT, new LimitedTextField(50));
        JTextArea textArea = new LimitedTextArea(150);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        super.addComponent(this.BEMERKUNGEN, textArea);
        super.addComponent(this.GENEHMIGUNGSDATUM, new TextFieldDateChooser());
        super.addComponent(this.AENDERUNGSGENEHMIGUNGSDATUM, new TextFieldDateChooser());
        super.addComponent(this.ABGEMELDET, new JCheckBox(this.ABGEMELDET));
        super.addComponent(this.ABWASSERFREI, new JCheckBox(this.ABWASSERFREI));
        super.addComponent(this.E_SATZUNG, new JCheckBox(this.E_SATZUNG));
        super.addComponent(this.SICHERHEITSABSCHEIDER, new JCheckBox(this.SICHERHEITSABSCHEIDER));
        super.addComponent(this.WIEDERVORLAGEDATUM, new TextFieldDateChooser());
        super.addComponent(this.SPEICHERN, getSaveAnh49Button());

        // ── AbscheiderScroller ────────────────────────────
        JScrollPane abscheiderScroller = new JScrollPane(
            getAbscheiderTabelle(),
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        abscheiderScroller.setPreferredSize(
            new java.awt.Dimension(0, 120));
        abscheiderScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 120));
        abscheiderScroller.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { showAbscheiderPopup(e); }
            @Override
            public void mouseReleased(MouseEvent e) { showAbscheiderPopup(e); }
        });

        // ══════════════════════════════════════════════════
        // innerPanel mit GridBagLayout
        // ══════════════════════════════════════════════════
        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 5, 3, 5);

        // ── ZEILE 0: Zwei Spalten nebeneinander ───────────
        // Linke Spalte (Bearbeitung + Analyse)
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints lc = new GridBagConstraints();
        lc.insets = new Insets(2, 3, 2, 3);

        // SEPARATOR: Bearbeitung
        lc.gridx = 0; lc.gridy = 0;
        lc.gridwidth = 2; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(createSeparator("Bearbeitung"), lc);

        // Ansprechpartner
        lc.gridy = 1; lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE;
        lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(this.ANSPRECHPARTNER), lc);
        lc.gridx = 1; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        super.getComponent(this.ANSPRECHPARTNER)
            .setPreferredSize(new java.awt.Dimension(200,
                super.getComponent(this.ANSPRECHPARTNER).getPreferredSize().height));
        leftPanel.add(super.getComponent(this.ANSPRECHPARTNER), lc);

        // Sachkunde LFA
        lc.gridx = 0; lc.gridy = 2; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE;
        lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(this.SACHKUNDE_LFA), lc);
        lc.gridx = 1; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        super.getComponent(this.SACHKUNDE_LFA)
            .setPreferredSize(new java.awt.Dimension(200,
                super.getComponent(this.SACHKUNDE_LFA).getPreferredSize().height));
        leftPanel.add(super.getComponent(this.SACHKUNDE_LFA), lc);

        // SEPARATOR: Analyse
        lc.gridx = 0; lc.gridy = 3;
        lc.gridwidth = 2; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(createSeparator("Analyse"), lc);

        // Analysemonat
        lc.gridy = 4; lc.gridwidth = 1; lc.weightx = 0;
        lc.fill = GridBagConstraints.NONE;
        lc.anchor = GridBagConstraints.EAST;
        leftPanel.add(new JLabel(this.ANALYSEMONAT), lc);
        lc.gridx = 1; lc.weightx = 1.0;
        lc.fill = GridBagConstraints.HORIZONTAL;
        super.getComponent(this.ANALYSEMONAT)
            .setPreferredSize(new java.awt.Dimension(200,
                super.getComponent(this.ANALYSEMONAT).getPreferredSize().height));
        leftPanel.add(super.getComponent(this.ANALYSEMONAT), lc);

        // Rechte Spalte (Erfassung)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rc = new GridBagConstraints();
        rc.insets = new Insets(2, 3, 2, 3);

        // SEPARATOR: Erfassung
        rc.gridx = 0; rc.gridy = 0;
        rc.gridwidth = 2; rc.weightx = 1.0;
        rc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(createSeparator("Erfassung"), rc);

        // abgemeldet + E-Satzung
        rc.gridy = 1; rc.gridwidth = 1; rc.weightx = 0;
        rc.fill = GridBagConstraints.NONE;
        rc.anchor = GridBagConstraints.WEST;
        rightPanel.add(super.getComponent(this.ABGEMELDET), rc);
        rc.gridx = 1;
        rightPanel.add(super.getComponent(this.E_SATZUNG), rc);

        // abwasserfrei + Sicherheitsabscheider
        rc.gridx = 0; rc.gridy = 2;
        rightPanel.add(super.getComponent(this.ABWASSERFREI), rc);
        rc.gridx = 1;
        rightPanel.add(super.getComponent(this.SICHERHEITSABSCHEIDER), rc);

        // ── Zwei Spalten ins innerPanel ───────────────────
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        innerPanel.add(leftPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        innerPanel.add(rightPanel, gbc);

        // ── SEPARATOR: Bemerkungen ────────────────────────
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Bemerkungen"), gbc);

        // ── BemerkungsArea ────────────────────────────────
        JScrollPane bemerkungsScroller = new JScrollPane(
            super.getComponent(this.BEMERKUNGEN),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bemerkungsScroller.setPreferredSize(
            new java.awt.Dimension(0, 60));
        bemerkungsScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 60));
        gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(bemerkungsScroller, gbc);

        // ── SEPARATOR: Abscheiderdetails ──────────────────
        gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Abscheiderdetails"), gbc);

        // ── AbscheiderTabelle ─────────────────────────────
        gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(abscheiderScroller, gbc);

        // ── ButtonBar ─────────────────────────────────────
        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonBar.add(getNeuerAbscheiderButton());
        buttonBar.add(getSaveAnh49Button());
        gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(buttonBar, gbc);

        // ── Wrapper: links schmal, rechts leer ───────────
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(innerPanel, BorderLayout.WEST);

        this.setLayout(new BorderLayout());
        this.add(wrapperPanel, BorderLayout.NORTH);
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
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0);
        sepPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        return sepPanel;
    }

    // ── getAbscheiderLoeschAction() ────────────────────────
    private Action getAbscheiderLoeschAction() {
        if (abscheiderLoeschAction == null) {
            abscheiderLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = -4757595254932715764L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getAbscheiderTabelle().getSelectedRow();
                    if (row != -1 && getAbscheiderTabelle().getEditingRow() == -1) {
                        Anh49Abscheiderdetails abscheider =
                            abscheiderModel.getObjectAtRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll der Abscheider " + abscheider
                            + " wirklich inkl. aller Detailinformationen"
                            + " gelöscht werden?",
                            "Löschen bestätigen")) {
                            abscheiderModel.removeRow(row);
                            log.debug("Abscheider " + abscheider.getLage()
                                + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von " + abscheider.getLage()
                                + " wurde abgebrochen!");
                        }
                    }
                }
            };
            abscheiderLoeschAction.putValue(Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_L));
            abscheiderLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }
        return abscheiderLoeschAction;
    }

    // ── getAbscheiderNeuAction() ───────────────────────────
    private Action getAbscheiderNeuAction() {
        if (abscheiderNeuAction == null) {
            abscheiderNeuAction = new AbstractAction("Neuer Abscheider") {
                private static final long serialVersionUID = 4388335905488653435L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    Anh49Abscheiderdetails neuerAbscheider =
                        new Anh49Abscheiderdetails();
                    neuerAbscheider.setAnh49Fachdaten(fachdaten);
                    editAbscheider(neuerAbscheider);
                }
            };
            abscheiderNeuAction.putValue(Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_N));
        }
        return abscheiderNeuAction;
    }

    // ── editAbscheider() ───────────────────────────────────
    public void editAbscheider(Anh49Abscheiderdetails absch) {
        if (fachdaten.getId() == null) {
            JOptionPane.showMessageDialog(hauptModul.getFrame(),
                "Bitte zuerst das Anhangobjekt speichern",
                "Warnung", JOptionPane.WARNING_MESSAGE);
        } else {
            AbscheiderEditor editDialog = new AbscheiderEditor(
                absch, hauptModul.getFrame());
            editDialog.setLocationRelativeTo(hauptModul.getFrame());
            editDialog.setVisible(true);
            if (editDialog.wasSaved() && (editDialog.getDetails() != null)) {
                abscheiderModel.updateList();
                Anh49Abscheiderdetails details = editDialog.getDetails();
                int row = abscheiderModel.getList().indexOf(details);
                if (row != -1) {
                    getAbscheiderTabelle().setRowSelectionInterval(row, row);
                    getAbscheiderTabelle().scrollRectToVisible(
                        getAbscheiderTabelle().getCellRect(row, 0, true));
                }
            }
        }
    }

    // ── showAbscheiderPopup() ──────────────────────────────
    private void showAbscheiderPopup(MouseEvent e) {
        if (abscheiderPopup == null) {
            abscheiderPopup = new JPopupMenu("Abscheider");
            abscheiderPopup.add(new JMenuItem(getAbscheiderLoeschAction()));
            abscheiderPopup.add(new JMenuItem(getAbscheiderNeuAction()));
        }
        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = abscheiderTabelle.rowAtPoint(origin);
            if (row != -1) {
                getAbscheiderLoeschAction().setEnabled(true);
                abscheiderTabelle.setRowSelectionInterval(row, row);
            } else {
                getAbscheiderLoeschAction().setEnabled(false);
            }
            abscheiderPopup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    // ── getAbscheiderTabelle() ─────────────────────────────
    private JTable getAbscheiderTabelle() {
        if (abscheiderTabelle == null) {
            abscheiderTabelle = new JTable(abscheiderModel);
            abscheiderTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
            abscheiderTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            abscheiderTabelle.setColumnSelectionAllowed(false);

            DefaultTableCellRenderer centeredRenderer =
                new DefaultTableCellRenderer();
            centeredRenderer.setHorizontalAlignment(
                DefaultTableCellRenderer.CENTER);

            TableColumn column = null;
            for (int i = 0; i < abscheiderTabelle.getColumnCount(); i++) {
                column = abscheiderTabelle.getColumnModel().getColumn(i);
                if (i == 0) {
                    column.setCellRenderer(centeredRenderer);
                    column.setMaxWidth(50);
                    column.setPreferredWidth(30);
                } else if (i == 1) {
                    column.setPreferredWidth(100);
                } else if (i == 2) {
                    column.setPreferredWidth(60);
                } else if (i == 3) {
                    column.setPreferredWidth(100);
                } else if (i == 4) {
                    column.setPreferredWidth(100);
                }
            }

            abscheiderTabelle.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = abscheiderTabelle.rowAtPoint(origin);
                            if (row != -1) {
                                Anh49Abscheiderdetails absch =
                                    (Anh49Abscheiderdetails)
                                    abscheiderModel.getObjectAtRow(row);
                                log.debug("Doppelklick auf: " + absch);
                                editAbscheider(absch);
                            }
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        showAbscheiderPopup(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showAbscheiderPopup(e);
                    }
                });

            abscheiderTabelle.getInputMap().put(
                (KeyStroke) getAbscheiderLoeschAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getAbscheiderLoeschAction().getValue(Action.NAME));
            abscheiderTabelle.getActionMap().put(
                getAbscheiderLoeschAction().getValue(Action.NAME),
                getAbscheiderLoeschAction());
        }
        return abscheiderTabelle;
    }

    // ── fetchFormData() ────────────────────────────────────
    public void fetchFormData() {
        Set<Anfallstelle> list = this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh49Fachdaten.findByAnfallstelleId(
            list.iterator().next().getId());
        log.debug("Anhang 49 Objekt aus DB geholt: ID" + this.fachdaten);
    }

    // ── updateForm() ───────────────────────────────────────
    public void updateForm() {
        if (this.fachdaten != null) {
            super.setComponentValue(this.ANSPRECHPARTNER,
                this.fachdaten.getAnsprechpartnerIn());
            super.setComponentValue(this.SACHKUNDE_LFA,
                this.fachdaten.getSachkundelfa());
            super.setComponentValue(this.ANALYSEMONAT,
                this.fachdaten.getAnalysemonat());
            super.setComponentValue(this.BEMERKUNGEN,
                this.fachdaten.getBemerkungen());
            super.setComponentValue(this.GENEHMIGUNGSDATUM,
                this.fachdaten.getGenehmigung());
            super.setComponentValue(this.AENDERUNGSGENEHMIGUNGSDATUM,
                this.fachdaten.getAenderungsgenehmigung());
            super.setComponentValue(this.ABGEMELDET,
                this.fachdaten.getAbgemeldet());
            super.setComponentValue(this.ABWASSERFREI,
                this.fachdaten.getAbwasserfrei());
            super.setComponentValue(this.E_SATZUNG,
                this.fachdaten.getESatzung());
            super.setComponentValue(this.SICHERHEITSABSCHEIDER,
                this.fachdaten.getSicherheitsabscheider());
            super.setComponentValue(this.WIEDERVORLAGEDATUM,
                this.fachdaten.getWiedervorlage());
            abscheiderModel.setFachdaten(fachdaten);
        } else {
            enableAll(false);
            this.hauptModul.getFrame().changeStatus(
                "FEHLER: Kein Anhang 49 Objekt gefunden!",
                HauptFrame.ERROR_COLOR);
        }
        setDirty(false);
    }

    // ── updateForm(Anfallstelle) ───────────────────────────
    public void updateForm(Anfallstelle anfallstelle) {
        this.fachdaten = anfallstelle.getAnh49Fachdatens().iterator().next();
        if (this.fachdaten != null) {
            super.setComponentValue(this.ANSPRECHPARTNER,
                this.fachdaten.getAnsprechpartnerIn());
            super.setComponentValue(this.SACHKUNDE_LFA,
                this.fachdaten.getSachkundelfa());
            super.setComponentValue(this.ANALYSEMONAT,
                this.fachdaten.getAnalysemonat());
            super.setComponentValue(this.BEMERKUNGEN,
                this.fachdaten.getBemerkungen());
            super.setComponentValue(this.GENEHMIGUNGSDATUM,
                this.fachdaten.getGenehmigung());
            super.setComponentValue(this.AENDERUNGSGENEHMIGUNGSDATUM,
                this.fachdaten.getAenderungsgenehmigung());
            super.setComponentValue(this.ABGEMELDET,
                this.fachdaten.getAbgemeldet());
            super.setComponentValue(this.ABWASSERFREI,
                this.fachdaten.getAbwasserfrei());
            super.setComponentValue(this.E_SATZUNG,
                this.fachdaten.getESatzung());
            super.setComponentValue(this.SICHERHEITSABSCHEIDER,
                this.fachdaten.getSicherheitsabscheider());
            super.setComponentValue(this.WIEDERVORLAGEDATUM,
                this.fachdaten.getWiedervorlage());
            abscheiderModel.setFachdaten(fachdaten);
        } else {
            enableAll(false);
            this.hauptModul.getFrame().changeStatus(
                "FEHLER: Kein Anhang 49 Objekt gefunden!",
                HauptFrame.ERROR_COLOR);
        }
        setDirty(false);
    }

    // ── clearForm() ────────────────────────────────────────
    public void clearForm() {
        super.clearAllComponents();
        abscheiderModel.setList(new ArrayList<Anh49Abscheiderdetails>());
    }

    // ── enableAll() ────────────────────────────────────────
    public void enableAll(boolean enabled) {
        if (!(enabled && (this.fachdaten == null))) {
            super.setAllComponentsEnabled(enabled);
        }
    }

    // ── getFachdaten() ─────────────────────────────────────
    public Anh49Fachdaten getFachdaten() {
        return this.fachdaten;
    }

    // ── doSavePanelData() ──────────────────────────────────
    @Override
    protected boolean doSavePanelData() {
        boolean success;
        this.fachdaten.setAnsprechpartnerIn(
            (String) super.getComponentValue(this.ANSPRECHPARTNER));
        this.fachdaten.setSachkundelfa(
            (String) super.getComponentValue(this.SACHKUNDE_LFA));
        this.fachdaten.setAnalysemonat(
            (String) super.getComponentValue(this.ANALYSEMONAT));
        this.fachdaten.setBemerkungen(
            (String) super.getComponentValue(this.BEMERKUNGEN));
        this.fachdaten.setGenehmigung(
            (Date) super.getComponentValue(this.GENEHMIGUNGSDATUM));
        this.fachdaten.setAenderungsgenehmigung(
            (Date) super.getComponentValue(this.AENDERUNGSGENEHMIGUNGSDATUM));
        this.fachdaten.setAbgemeldet(
            (Boolean) super.getComponentValue(this.ABGEMELDET));
        this.fachdaten.setAbwasserfrei(
            (Boolean) super.getComponentValue(this.ABWASSERFREI));
        this.fachdaten.setESatzung(
            (Boolean) super.getComponentValue(this.E_SATZUNG));
        this.fachdaten.setWiedervorlage(
            (Date) super.getComponentValue(this.WIEDERVORLAGEDATUM));
        this.fachdaten.setSicherheitsabscheider(
            (Boolean) super.getComponentValue(this.SICHERHEITSABSCHEIDER));
        this.fachdaten.getAnfallstelle().setObjekt(
            this.hauptModul.getObjekt());
        success = this.fachdaten.merge();
        if (!success) {
            this.log.debug("Anh49 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    // ── completeObjekt(Anfallstelle) ───────────────────────
    public void completeObjekt(Anfallstelle anfallstelle) {
        if (anfallstelle.getAnh49Fachdatens().size() == 0) {
            this.fachdaten = new Anh49Fachdaten();
            this.fachdaten.setAnfallstelle(anfallstelle);
            this.fachdaten.merge();
        }
    }

    // ── completeObjekt() ───────────────────────────────────
    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            this.fachdaten = new Anh49Fachdaten();
        }
    }

    // ── getNeuerAbscheiderButton() ─────────────────────────
    private JButton getNeuerAbscheiderButton() {
        if (this.neuerAbscheider == null) {
            this.neuerAbscheider = new JButton("Neuer Abscheider");
            this.neuerAbscheider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Anh49Abscheiderdetails neuerAbscheider =
                        new Anh49Abscheiderdetails();
                    neuerAbscheider.setAnh49Fachdaten(fachdaten);
                    editAbscheider(neuerAbscheider);
                }
            });
        }
        return this.neuerAbscheider;
    }

    // ── getSaveAnh49Button() ───────────────────────────────
    private JButton getSaveAnh49Button() {
        if (this.saveAnh49Button == null) {
            this.saveAnh49Button = new JButton("Speichern");
            this.saveAnh49Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (hauptModul.saveAllTabs()) {
                        Anh49Panel.this.hauptModul.getFrame().changeStatus(
                            "Anhang 49-Objekt erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh49Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Objekts!",
                            HauptFrame.ERROR_COLOR);
                    }
                    Anh49Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh49Button;
    }
}