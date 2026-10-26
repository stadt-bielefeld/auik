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
 * $Id: Anh55Panel.java,v 1.1.2.1 2010-11-23 10:25:57 u633d Exp $
 *
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2010/02/04 13:34:29  u633d
 * Verkn�pfte Objekte
 *
 * Revision 1.3  2010/02/04 13:09:38  u633d
 * Verkn�pfte Objekte
 *
 * Revision 1.2  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.10  2006/10/17 07:54:29  u633d
 * Anhang 52 (Chemische Wäschereien) haben nun einen eigenen Tab.
 *
 * Revision 1.9  2006/09/28 07:31:34  u633d
 * Bei dem Verwaltungsverfahren eine Bemerkungs-spalte hinzugefuegt
 *
 * Revision 1.8  2006/09/26 09:48:38  u633d
 * Anh 55 bereinigt; settings wieder auf UTEDB2 gesetzt
 *
 * Revision 1.7  2006/09/25 12:17:17  u633d
 * Wäscherei funzt
 *
 * Revision 1.6  2006/09/25 09:36:59  u633d
 * Waescherei Panel
 *
 * Revision 1.5  2006/09/25 08:22:25  u633d
 * *** empty log message ***
 *
 * Revision 1.4  2006/09/21 11:52:56  u633d
 * *** empty log message ***
 *
 * Revision 1.3  2006/09/21 07:47:30  u633d
 * Oberfläche erstellt
 *
 * Revision 1.2  2006/09/20 06:45:42  u633d
 * *** empty log message ***
 *
 * Revision 1.1  2006/09/13 11:06:21  u633d
 * *** empty log message ***
 *
 * Revision 1.1  2005/08/17 05:45:31  u633d
 * - Anhang 55 erstellt
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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh55Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;

/**
 * Das Panel zum Bearbeiten von Wäschereien
 * @author u633d
 */
public class Anh55Panel extends ObjectPanel {
    private static final long serialVersionUID = 3345458422378912073L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private Anfallstelle anfallstelle;

    // Widgets
    private JTextField sachbearbeiterFeld = null;
    private JTextField entgebIdFeld = null;
    private JTextField mengewaescheFeld = null;
    private JTextField sonsttexFeld = null;
    private DoubleField monatwasserverbFeld = null;
    private JTextArea waschsituationArea = null;
    private JTextField ansprechpartnerFeld = null;
    private JTextField brancheFeld = null;
    private JTextArea BemerkungenArea = null;
    private JCheckBox abgemeldetCheck = null;
    private JCheckBox putztuecherCheck = null;
    private JCheckBox teppichCheck = null;
    private JCheckBox mattenCheck = null;
    private JCheckBox haushaltstexCheck = null;
    private JCheckBox berufsklCheck = null;
    private JCheckBox gasthotelCheck = null;
    private JCheckBox krankenhausCheck = null;
    private JCheckBox heimwaescheCheck = null;
    private JFormattedTextField anteilwaschgutFeld = null;
    private JFormattedTextField anteilgesamtwaschgutFeld = null;
    private JCheckBox betrwasseraufberCheck = null;
    private JCheckBox chlorCheck = null;
    private JCheckBox aktivchlorCheck = null;
    private JCheckBox vliesCheck = null;
    private JCheckBox fischCheck = null;
    private JCheckBox loesungsmittelCheck = null;
    private JButton saveAnh55Button = null;

    // Daten
    private Anh55Fachdaten fachdaten = null;

    @SuppressWarnings("deprecation")
    public Anh55Panel(BasisObjektBearbeiten hauptModul,
        Anfallstelle anfallstelle) {
        this.name = "Wäscherei";
        this.hauptModul = hauptModul;
        this.anfallstelle = anfallstelle;

        // ── InnerPanel ────────────────────────────────────
        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);

        // ── SEPARATOR: Fachdaten ──────────────────────────
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 4; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Fachdaten"), gbc);

        // ── Hilfsmethode für Zeile: Label + Feld + Checkbox
        // Zeile 1: Entwässerungsgebiet | Abgemeldet
        addRow(innerPanel, gbc, 1,
            "Entwässerungsgebiet", getEntgebIdFeld(),
            getAbgemeldetCheck());

        // Zeile 2: Branche | Putztücher
        addRow(innerPanel, gbc, 2,
            "Branche", getBrancheFeld(),
            getPutztuecherCheck());

        // Zeile 3: Ansprechpartner | Matten
        addRow(innerPanel, gbc, 3,
            "Ansprechpartner", getAnsprechpartnerFeld(),
            getMattenCheck());

        // Zeile 4: Menge | Teppiche
        addRow(innerPanel, gbc, 4,
            "Menge", getMengeFeld(),
            getTeppichCheck());

        // Zeile 5: Sonstige Textilien | Berufskleidung
        addRow(innerPanel, gbc, 5,
            "Sonstige Textilien", getSonsttexFeld(),
            getBerufsklCheck());

        // Zeile 6: Monatl. Wasserverbrauch | Haushaltstextilien
        addRow(innerPanel, gbc, 6,
            "Monatl. Wasserverbrauch",
            getMonatwasserverbFeld(),
            getHaushaltstexCheck());

        // Zeile 7: Anteil am Waschgut | Gaststätten
        addRow(innerPanel, gbc, 7,
            "Anteil am Waschgut",
            getAnteilwaschgutFeld(),
            getGasthotelCheck());

        // Zeile 8: Anteil am Gesamtwaschgut | Krankenhaus
        addRow(innerPanel, gbc, 8,
            "Anteil am Gesamtwaschgut",
            getGesamtwaschgutFeld(),
            getKrankenhausCheck());

        // Zeile 9: Sachbearbeiter | Heimwäsche
        addRow(innerPanel, gbc, 9,
            "Sachbearbeiter:in", getSachbearbeiterFeld(),
            getHeimwaescheCheck());

        // Zeile 10: Vlies | Fisch
        addRowTwoChecks(innerPanel, gbc, 10,
            getVliesCheck(), getFischCheck());

        // Zeile 11: Lösungsmittel | Betriebswasseraufbereitung
        addRowTwoChecks(innerPanel, gbc, 11,
            getLoesungsmittelCheck(),
            getBetrwasseraufberCheck());

        // Zeile 12: Chlor | Aktiv-Chlor
        addRowTwoChecks(innerPanel, gbc, 12,
            getChlorCheck(), getAktivchlorCheck());

        // ── SEPARATOR: Wasch-Situation ────────────────────
        gbc.gridx = 0; gbc.gridy = 13;
        gbc.gridwidth = 4; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(createSeparator("Wasch-Situation"), gbc);

        JScrollPane waschsituationScroller = new JScrollPane(
            getWaschsituationArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        waschsituationScroller.setPreferredSize(
            new java.awt.Dimension(0, 60));
        waschsituationScroller.setMaximumSize(
            new java.awt.Dimension(Integer.MAX_VALUE, 60));
        gbc.gridy = 14; gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(waschsituationScroller, gbc);

        // ── SEPARATOR: Bemerkungen ────────────────────────
        gbc.gridy = 15; gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0;
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
        gbc.gridy = 16; gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(bemerkungsScroller, gbc);

        // ── ButtonBar ─────────────────────────────────────
        JPanel buttonBar = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonBar.add(getSaveAnh55Button());
        gbc.gridy = 17; gbc.gridwidth = 4;
        gbc.weightx = 1.0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerPanel.add(buttonBar, gbc);

        // ── Wrapper ───────────────────────────────────────
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(innerPanel, BorderLayout.WEST);
        this.setLayout(new BorderLayout());
        this.add(wrapperPanel, BorderLayout.NORTH);

        addChangeListeners(
            getAbgemeldetCheck(),
            getSachbearbeiterFeld(),
            getEntgebIdFeld(),
            getMengeFeld(),
            getSonsttexFeld(),
            getMonatwasserverbFeld(),
            getWaschsituationArea(),
            getAnsprechpartnerFeld(),
            getBrancheFeld(),
            getPutztuecherCheck(),
            getTeppichCheck(),
            getMattenCheck(),
            getHaushaltstexCheck(),
            getBerufsklCheck(),
            getGasthotelCheck(),
            getKrankenhausCheck(),
            getHeimwaescheCheck(),
            getAnteilwaschgutFeld(),
            getGesamtwaschgutFeld(),
            getBetrwasseraufberCheck(),
            getChlorCheck(),
            getAktivchlorCheck(),
            getVliesCheck(),
            getFischCheck(),
            getBemerkungenArea());
    }

    // ── Hilfsmethoden für Zeilen ───────────────────────────

    /** Label + Textfeld links, Checkbox rechts */
    private void addRow(JPanel panel, GridBagConstraints gbc,
        int row, String label,
        java.awt.Component field,
        JCheckBox check) {
        gbc.gridy = row; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        if (field instanceof JTextField) {
            ((JTextField) field).setPreferredSize(
                new java.awt.Dimension(200,
                    field.getPreferredSize().height));
        } else if (field instanceof JFormattedTextField) {
            ((JFormattedTextField) field).setPreferredSize(
                new java.awt.Dimension(200,
                    field.getPreferredSize().height));
        } else if (field instanceof DoubleField) {
            field.setPreferredSize(
                new java.awt.Dimension(200,
                    field.getPreferredSize().height));
        }
        panel.add(field, gbc);

        gbc.gridx = 2; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(check, gbc);
    }

    /** Zwei Checkboxen nebeneinander (ohne Label/Feld) */
    private void addRowTwoChecks(JPanel panel,
        GridBagConstraints gbc,
        int row,
        JCheckBox check1, JCheckBox check2) {
        gbc.gridy = row; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        panel.add(check1, gbc);
        gbc.gridx = 2;
        panel.add(check2, gbc);
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
            this.fachdaten = new Anh55Fachdaten();
        this.anfallstelle = anfallstelle;
        this.fachdaten.setAnfallstelle(this.anfallstelle);
    }

    // ── doSavePanelData() ──────────────────────────────────
    protected boolean doSavePanelData() {
        boolean success;

        String bemerkungen = this.BemerkungenArea.getText();
        this.fachdaten.setBemerkungen(
            "".equals(bemerkungen) ? null : bemerkungen);

        String mengewaescheString = this.mengewaescheFeld.getText();
        this.fachdaten.setMengewaesche(
            "".equals(mengewaescheString)
                ? null : mengewaescheString);

        String sachbearbeiterString =
            this.sachbearbeiterFeld.getText();
        this.fachdaten.setSachbearbeiter(
            "".equals(sachbearbeiterString)
                ? null : sachbearbeiterString);

        String entgebIdString = this.entgebIdFeld.getText();
        this.fachdaten.setEntgebId(
            "".equals(entgebIdString) ? null : entgebIdString);

        String monatwasserverbString =
            this.monatwasserverbFeld.getText();
        this.fachdaten.setMonatwasserverb(
            "".equals(monatwasserverbString)
                ? null : monatwasserverbString);

        String sonsttextString = this.sonsttexFeld.getText();
        this.fachdaten.setSonsttex(
            "".equals(sonsttextString)
                ? null : sonsttextString);

        String waschsituationString =
            this.waschsituationArea.getText();
        this.fachdaten.setWaschsituation(
            "".equals(waschsituationString)
                ? null : waschsituationString);

        String ansprechpartnerString =
            this.ansprechpartnerFeld.getText();
        this.fachdaten.setAnsprechpartner(
            "".equals(ansprechpartnerString)
                ? null : ansprechpartnerString);

        String brancheString = this.brancheFeld.getText();
        this.fachdaten.setBranche(
            "".equals(brancheString) ? null : brancheString);

        Integer anteilwaschgut =
            ((IntegerField) this.anteilwaschgutFeld)
                .getIntValue();
        this.fachdaten.setAnteilwaschgut(anteilwaschgut);

        Integer anteilgesamtwg =
            ((IntegerField) this.anteilgesamtwaschgutFeld)
                .getIntValue();
        this.fachdaten.setAnteilgesamtgut(anteilgesamtwg);

        this.fachdaten.setAbgemeldet(
            getAbgemeldetCheck().isSelected());
        this.fachdaten.setPutztuecher(
            getPutztuecherCheck().isSelected());
        this.fachdaten.setTeppich(
            getTeppichCheck().isSelected());
        this.fachdaten.setMatten(
            getMattenCheck().isSelected());
        this.fachdaten.setHaushaltstex(
            getHaushaltstexCheck().isSelected());
        this.fachdaten.setBerufskl(
            getBerufsklCheck().isSelected());
        this.fachdaten.setGaststhotel(
            getGasthotelCheck().isSelected());
        this.fachdaten.setKrankenhaus(
            getKrankenhausCheck().isSelected());
        this.fachdaten.setHeimwaesche(
            getHeimwaescheCheck().isSelected());
        this.fachdaten.setBetrwasseraufber(
            getBetrwasseraufberCheck().isSelected());
        this.fachdaten.setChlor(
            getChlorCheck().isSelected());
        this.fachdaten.setAktivchlor(
            getAktivchlorCheck().isSelected());
        this.fachdaten.setVlies(
            getVliesCheck().isSelected());
        this.fachdaten.setFischfleisch(
            getFischCheck().isSelected());
        this.fachdaten.setLoesungsmittel(
            getLoesungsmittelCheck().isSelected());

        success = fachdaten.merge();
        if (success) {
            log.debug("Anh 55 Objekt "
                + this.fachdaten.getId() + " gespeichert.");
        } else {
            log.debug("Anh 55 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    // ── enableAll() ────────────────────────────────────────
    public void enableAll(boolean enabled) {
        getBemerkungenArea().setEnabled(enabled);
        getAbgemeldetCheck().setEnabled(enabled);
        getSachbearbeiterFeld().setEnabled(enabled);
        getEntgebIdFeld().setEnabled(enabled);
        getMengeFeld().setEnabled(enabled);
        getSonsttexFeld().setEnabled(enabled);
        getMonatwasserverbFeld().setEnabled(enabled);
        getWaschsituationArea().setEnabled(enabled);
        getAnsprechpartnerFeld().setEnabled(enabled);
        getBrancheFeld().setEnabled(enabled);
        getPutztuecherCheck().setEnabled(enabled);
        getTeppichCheck().setEnabled(enabled);
        getMattenCheck().setEnabled(enabled);
        getHaushaltstexCheck().setEnabled(enabled);
        getBerufsklCheck().setEnabled(enabled);
        getGasthotelCheck().setEnabled(enabled);
        getKrankenhausCheck().setEnabled(enabled);
        getHeimwaescheCheck().setEnabled(enabled);
        getAnteilwaschgutFeld().setEnabled(enabled);
        getGesamtwaschgutFeld().setEnabled(enabled);
        getBetrwasseraufberCheck().setEnabled(enabled);
        getChlorCheck().setEnabled(enabled);
        getAktivchlorCheck().setEnabled(enabled);
        getVliesCheck().setEnabled(enabled);
        getFischCheck().setEnabled(enabled);
        getLoesungsmittelCheck().setEnabled(enabled);
    }

    // ── clearForm() ────────────────────────────────────────
    public void clearForm() {
        getAbgemeldetCheck().setSelected(false);
        getSachbearbeiterFeld().setText(null);
        getEntgebIdFeld().setText(null);
        getMengeFeld().setText(null);
        getSonsttexFeld().setText(null);
        getMonatwasserverbFeld().setText(null);
        getWaschsituationArea().setText(null);
        getAnsprechpartnerFeld().setText(null);
        getBrancheFeld().setText(null);
        getPutztuecherCheck().setSelected(false);
        getTeppichCheck().setSelected(false);
        getMattenCheck().setSelected(false);
        getHaushaltstexCheck().setSelected(false);
        getBerufsklCheck().setSelected(false);
        getGasthotelCheck().setSelected(false);
        getKrankenhausCheck().setSelected(false);
        getHeimwaescheCheck().setSelected(false);
        getAnteilwaschgutFeld().setText(null);
        getGesamtwaschgutFeld().setText(null);
        getBetrwasseraufberCheck().setSelected(false);
        getChlorCheck().setSelected(false);
        getAktivchlorCheck().setSelected(false);
        getVliesCheck().setSelected(false);
        getFischCheck().setSelected(false);
        getLoesungsmittelCheck().setSelected(false);
        getBemerkungenArea().setText(null);
    }

    // ── updateForm(Anfallstelle) ───────────────────────────
    public void updateForm(Anfallstelle anfallstelle)
        throws RuntimeException {
        this.fachdaten =
            anfallstelle.getAnh55Fachdatens()
                .iterator().next();
        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null)
                getBemerkungenArea().setText(
                    this.fachdaten.getBemerkungen());
            if (this.fachdaten.getSachbearbeiter() != null)
                getSachbearbeiterFeld().setText(
                    this.fachdaten.getSachbearbeiter());
            if (this.fachdaten.getEntgebId() != null)
                getEntgebIdFeld().setText(
                    this.fachdaten.getEntgebId());
            if (this.fachdaten.getMengewaesche() != null)
                getMengeFeld().setText(
                    this.fachdaten.getMengewaesche());
            if (this.fachdaten.getSonsttex() != null)
                getSonsttexFeld().setText(
                    this.fachdaten.getSonsttex());
            if (this.fachdaten.getMonatwasserverb() != null)
                getMonatwasserverbFeld().setText(
                    this.fachdaten.getMonatwasserverb());
            if (this.fachdaten.getWaschsituation() != null)
                getWaschsituationArea().setText(
                    this.fachdaten.getWaschsituation());
            if (this.fachdaten.getAnsprechpartner() != null)
                getAnsprechpartnerFeld().setText(
                    this.fachdaten.getAnsprechpartner());
            if (this.fachdaten.getBranche() != null)
                getBrancheFeld().setText(
                    this.fachdaten.getBranche());
            if (this.fachdaten.getAnteilwaschgut() != null)
                getAnteilwaschgutFeld().setText(
                    this.fachdaten.getAnteilwaschgut()
                        .toString());
            if (this.fachdaten.getAnteilgesamtgut() != null)
                getGesamtwaschgutFeld().setText(
                    this.fachdaten.getAnteilgesamtgut()
                        .toString());
            if (this.fachdaten.getAbgemeldet() != null)
                getAbgemeldetCheck().setSelected(
                    this.fachdaten.getAbgemeldet());
            if (this.fachdaten.getPutztuecher() != null)
                getPutztuecherCheck().setSelected(
                    this.fachdaten.getPutztuecher());
            if (this.fachdaten.getTeppich() != null)
                getTeppichCheck().setSelected(
                    this.fachdaten.getTeppich());
            if (this.fachdaten.getMatten() != null)
                getMattenCheck().setSelected(
                    this.fachdaten.getMatten());
            if (this.fachdaten.getHaushaltstex() != null)
                getHaushaltstexCheck().setSelected(
                    this.fachdaten.getHaushaltstex());
            if (this.fachdaten.getBerufskl() != null)
                getBerufsklCheck().setSelected(
                    this.fachdaten.getBerufskl());
            if (this.fachdaten.getGaststhotel() != null)
                getGasthotelCheck().setSelected(
                    this.fachdaten.getGaststhotel());
            if (this.fachdaten.getKrankenhaus() != null)
                getKrankenhausCheck().setSelected(
                    this.fachdaten.getKrankenhaus());
            if (this.fachdaten.getHeimwaesche() != null)
                getHeimwaescheCheck().setSelected(
                    this.fachdaten.getHeimwaesche());
            if (this.fachdaten.getBetrwasseraufber() != null)
                getBetrwasseraufberCheck().setSelected(
                    this.fachdaten.getBetrwasseraufber());
            if (this.fachdaten.getChlor() != null)
                getChlorCheck().setSelected(
                    this.fachdaten.getChlor());
            if (this.fachdaten.getAktivchlor() != null)
                getAktivchlorCheck().setSelected(
                    this.fachdaten.getAktivchlor());
            if (this.fachdaten.getVlies() != null)
                getVliesCheck().setSelected(
                    this.fachdaten.getVlies());
            if (this.fachdaten.getFischfleisch() != null)
                getFischCheck().setSelected(
                    this.fachdaten.getFischfleisch());
            if (this.fachdaten.getLoesungsmittel() != null)
                getLoesungsmittelCheck().setSelected(
                    this.fachdaten.getLoesungsmittel());
        }
        setDirty(false);
    }

    // ── fetchFormData() ────────────────────────────────────
    public void fetchFormData() throws RuntimeException {
        Set<Anfallstelle> list =
            this.hauptModul.getObjekt().getAnfallstelles();
        this.fachdaten = Anh55Fachdaten.findByAnfallstelleId(
            list.iterator().next().getId());
        log.debug("Anhang 55 Objekt aus DB geholt: ID"
            + this.fachdaten);
    }

    // ── getName() ──────────────────────────────────────────
    @Override
    public String getName() {
        return this.name;
    }

    // ── getSaveAnh55Button() ───────────────────────────────
    private JButton getSaveAnh55Button() {
        if (this.saveAnh55Button == null) {
            this.saveAnh55Button = new JButton("Speichern");
            this.saveAnh55Button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enableAll(false);
                        if (hauptModul.saveAllTabs()) {
                            Anh55Panel.this.hauptModul
                                .getFrame().changeStatus(
                                "Anh 55 Objekt "
                                + Anh55Panel.this.fachdaten
                                    .getId()
                                + " erfolgreich"
                                + " gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                        } else {
                            Anh55Panel.this.hauptModul
                                .getFrame().changeStatus(
                                "Fehler beim Speichern"
                                + " des Anh 55 Objekt!",
                                HauptFrame.ERROR_COLOR);
                        }
                        Anh55Panel.this.hauptModul.fillForm();
                    }
                });
        }
        return this.saveAnh55Button;
    }

    // ── Getter Methoden ────────────────────────────────────
    private JCheckBox getAbgemeldetCheck() {
        if (this.abgemeldetCheck == null)
            this.abgemeldetCheck = new JCheckBox("Abgemeldet?");
        return this.abgemeldetCheck;
    }

    private JTextField getEntgebIdFeld() {
        if (this.entgebIdFeld == null)
            this.entgebIdFeld = new LimitedTextField(50);
        return this.entgebIdFeld;
    }

    private JTextField getBrancheFeld() {
        if (this.brancheFeld == null)
            this.brancheFeld = new LimitedTextField(50);
        return this.brancheFeld;
    }

    private JCheckBox getPutztuecherCheck() {
        if (this.putztuecherCheck == null)
            this.putztuecherCheck =
                new JCheckBox("Putztücher?");
        return this.putztuecherCheck;
    }

    private JTextField getAnsprechpartnerFeld() {
        if (this.ansprechpartnerFeld == null)
            this.ansprechpartnerFeld =
                new LimitedTextField(50);
        return this.ansprechpartnerFeld;
    }

    private JCheckBox getMattenCheck() {
        if (this.mattenCheck == null)
            this.mattenCheck = new JCheckBox("Matten?");
        return this.mattenCheck;
    }

    private JTextField getMengeFeld() {
        if (this.mengewaescheFeld == null)
            this.mengewaescheFeld = new LimitedTextField(50);
        return this.mengewaescheFeld;
    }

    private JCheckBox getTeppichCheck() {
        if (this.teppichCheck == null)
            this.teppichCheck = new JCheckBox("Teppiche?");
        return this.teppichCheck;
    }

    private JTextField getSonsttexFeld() {
        if (this.sonsttexFeld == null)
            this.sonsttexFeld = new LimitedTextField(50);
        return this.sonsttexFeld;
    }

    private JCheckBox getBerufsklCheck() {
        if (this.berufsklCheck == null)
            this.berufsklCheck =
                new JCheckBox("Berufskleidung?");
        return this.berufsklCheck;
    }

    private DoubleField getMonatwasserverbFeld() {
        if (this.monatwasserverbFeld == null)
            this.monatwasserverbFeld = new DoubleField(50);
        return this.monatwasserverbFeld;
    }

    private JCheckBox getHaushaltstexCheck() {
        if (this.haushaltstexCheck == null)
            this.haushaltstexCheck =
                new JCheckBox("Haushaltstextilien?");
        return this.haushaltstexCheck;
    }

    private JCheckBox getGasthotelCheck() {
        if (this.gasthotelCheck == null)
            this.gasthotelCheck =
                new JCheckBox(
                    "Gaststätten-/Hoteltextilien?");
        return this.gasthotelCheck;
    }

    private JCheckBox getKrankenhausCheck() {
        if (this.krankenhausCheck == null)
            this.krankenhausCheck =
                new JCheckBox("Krankenhaus?");
        return this.krankenhausCheck;
    }

    private JCheckBox getHeimwaescheCheck() {
        if (this.heimwaescheCheck == null)
            this.heimwaescheCheck =
                new JCheckBox("Heimwäsche?");
        return this.heimwaescheCheck;
    }

    private JCheckBox getVliesCheck() {
        if (this.vliesCheck == null)
            this.vliesCheck = new JCheckBox("Vlies?");
        return this.vliesCheck;
    }

    private JCheckBox getFischCheck() {
        if (this.fischCheck == null)
            this.fischCheck = new JCheckBox("Fisch/Fleisch?");
        return this.fischCheck;
    }

    private JCheckBox getLoesungsmittelCheck() {
        if (this.loesungsmittelCheck == null)
            this.loesungsmittelCheck =
                new JCheckBox("Lösungsmittel?");
        return this.loesungsmittelCheck;
    }

    private JFormattedTextField getAnteilwaschgutFeld() {
        if (this.anteilwaschgutFeld == null)
            this.anteilwaschgutFeld = new IntegerField();
        return this.anteilwaschgutFeld;
    }

    private JFormattedTextField getGesamtwaschgutFeld() {
        if (this.anteilgesamtwaschgutFeld == null)
            this.anteilgesamtwaschgutFeld = new IntegerField();
        return this.anteilgesamtwaschgutFeld;
    }

    private JTextField getSachbearbeiterFeld() {
        if (this.sachbearbeiterFeld == null)
            this.sachbearbeiterFeld =
                new LimitedTextField(50);
        return this.sachbearbeiterFeld;
    }

    private JCheckBox getBetrwasseraufberCheck() {
        if (this.betrwasseraufberCheck == null)
            this.betrwasseraufberCheck =
                new JCheckBox(
                    "Betriebswasseraufbereitung?");
        return this.betrwasseraufberCheck;
    }

    private JCheckBox getChlorCheck() {
        if (this.chlorCheck == null)
            this.chlorCheck = new JCheckBox("Chlor?");
        return this.chlorCheck;
    }

    private JCheckBox getAktivchlorCheck() {
        if (this.aktivchlorCheck == null)
            this.aktivchlorCheck =
                new JCheckBox("Aktiv-Chlor?");
        return this.aktivchlorCheck;
    }

    private JTextArea getBemerkungenArea() {
        if (this.BemerkungenArea == null) {
            this.BemerkungenArea = new LimitedTextArea(255);
            this.BemerkungenArea.setLineWrap(true);
            this.BemerkungenArea.setWrapStyleWord(true);
        }
        return this.BemerkungenArea;
    }

    private JTextArea getWaschsituationArea() {
        if (this.waschsituationArea == null) {
            this.waschsituationArea = new LimitedTextArea(255);
            this.waschsituationArea.setLineWrap(true);
            this.waschsituationArea.setWrapStyleWord(true);
        }
        return this.waschsituationArea;
    }
}