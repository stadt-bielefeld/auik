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
package de.bielefeld.umweltamt.aui.module.objektpanels;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Einleitungsstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Referenz;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.GermanDouble;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.MyKeySelectionManager;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Einleitungsstelle"-Tab des BasisObjektBearbeiten-Moduls
 * @author Tobias Kaps
 * @date 15.01.2018
 */
public class EinleitungsstellePanel extends JPanel {
    private static final long serialVersionUID = 7997458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private TextFieldDateChooser erstellDatDatum = null;
    private JTextField herkunftFeld = null;
    private JTextField bezeichnungFeld = null;
    private JTextField gewaessernameAlias3Feld = null;
    private JTextField gewaessernameNsFeld = null;
    private JTextField nadiaIdFeld = null;
    private TextFieldDateChooser stillgelegtAmDatum = null;
    private JCheckBox typIndirektCheck = null;
    private JCheckBox typIndGewDirektCheck = null;
    private JCheckBox typKommTrennCheck = null;
    private JCheckBox typPrivatTrennCheck = null;
    private JCheckBox typSonstigeCheck = null;
    private JCheckBox typAusserortStrasseneinlCheck = null;
    private DoubleField stationierungNs3Feld = null;
    private DoubleField einzugsgebietFeld = null;
    private DoubleField stationierungSt3Feld = null;
    private JFormattedTextField abgaberelEinlFeld = null;
    private JFormattedTextField e32Feld = null;
    private JFormattedTextField n32Feld = null;
    private JFormattedTextField kanalArtOptFeld = null;
    private JFormattedTextField stationierung3OptFeld = null;
    private JFormattedTextField schutzzoneOptFeld = null;
    //Kläranlage
    private Klaeranlage[] klaeranlagen = null;
    private JComboBox<Klaeranlage> klaeranlageBox = null;
    private JButton saveElkaEinleitungsstelleButton = null;
    // Daten
    private Einleitungsstelle  einleitungsstelle = null;
    private Referenz referenz = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    private JCheckBox indCheck;
    private List<JCheckBox> otherChecks;

    public EinleitungsstellePanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Einleitungsstelle";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
                "r:80dlu, 5dlu, 80dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.appendSeparator("ELKA");
        builder.append("Erstellung:", getErstellDatDatum());
        builder.append("Stationierungns3: ", getStationierungNs3Feld());
        builder.nextLine();
        builder.append("Herkunft:", getHerkunftFeld());
        builder.append("Einzugsgebiet:", getEinzugsgebietFeld());
        builder.nextLine();
        builder.append("Bezeichnung:", getBezeichnungFeld());
        builder.append("Stationierungst3", getStationierungSt3Feld());
        builder.nextLine();
        builder.append("Gewässernamealias3:", getGewaessernameAlias3Feld());
        builder.append("Abgabe einleitung: ", getAbgaberelEinlFeld());
        builder.nextLine();
        builder.append("GewässernameNs: ", getGewaessernameNsFeld());
        builder.append("Kanal Art Opt:", getKanalArtOptFeld());
        builder.nextLine();
        builder.append("NadiaId:", getNadiaIdFeld());
        builder.append("E32:", getE32Feld());
        builder.nextLine();
        builder.append("Datum:", getStillgelegtAmDatum());
        builder.append("N32:",  getN32Feld());
        builder.nextLine();
        builder.append("", getTypIndirektCheck());
        builder.append("Stationierung:", getStationierung3OptFeld());
        builder.nextLine();
        builder.append("", getTypIndGewDirektCheck());
        builder.append("Schutzzone:", getSchutzzoneOptFeld());
        builder.nextLine();
        builder.append("", getTypKommTrennCheck());
        builder.append("Kläranlage: ", getKlaeranlageBox());
        builder.nextLine();
        builder.append("", getTypPrivatTrennCheck());
        builder.nextLine();
        builder.append("", getTypSonstigeCheck());
        builder.nextLine();
        builder.append("", getTypAusserortStrasseneinlCheck());
        builder.nextLine();
        JScrollPane objektverknuepfungScroller = new JScrollPane(
            getObjektverknuepungTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 7);
        builder.nextLine();
        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
                getSelectObjektButton(), getSaveElkaEinleitungsstelleButton());
        builder.append(buttonBar, 7);

        otherChecks = new ArrayList<JCheckBox>();
        otherChecks.add(getTypIndGewDirektCheck());
        otherChecks.add(getTypKommTrennCheck());
        otherChecks.add(getTypPrivatTrennCheck());
        otherChecks.add(getTypSonstigeCheck());
        otherChecks.add(getTypAusserortStrasseneinlCheck());

        indCheck = getTypIndirektCheck();
        indCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indCheck.isSelected() == true) {
                    otherChecks.forEach((check) -> {
                        check.setSelected(false);
                        check.setEnabled(false);
                    });
                } else {
                    otherChecks.forEach((check) -> {
                        check.setEnabled(true);
                        indCheck.setEnabled(true);
                    });
                }
            }
        });

    }

    /**
     * Methode verknüpft das lokal erstelle Objekt einleitungstelle
     * mit der ElkaEinleitungsstelle der Datenbank und holt sich die Klaeranlagen
     * aus der Datenbank
     * @throws RuntimeException
     */
    public void fetchFormData() throws RuntimeException {
        this.einleitungsstelle = Einleitungsstelle.findByObjektId(
                this.hauptModul.getObjekt().getId());
        log.debug("Einleitungsstelle aus DB geholt: " + this.einleitungsstelle);

        List<Referenz> referenzen = Referenz.getAll();
        this.referenz = null;

        for (Referenz ref : referenzen) {
            if (ref.getqEl().getId() == this.einleitungsstelle.getId()
                && ref.getKlaeranlageByZKaNr() != null) {
            this.referenz = Referenz.findById(ref.getNr());
                log.debug("Referenz aus DB geholt: " + this.referenz);
            }
        }

        if (this.klaeranlagen == null) {
            this.klaeranlagen = DatabaseQuery.getKlaeranlage();
        }
    }

    /**
     * Methode setzt die Attribute der Einleitungsstelle aus der Datenbank
     * auf die der lokalen Einleitungstelle und die Verknüpfung mit
     * der Kläranlage über die Tabelle referenz
     * @throws RuntimeException
     */
    public void updateForm() throws RuntimeException {
        if (this.einleitungsstelle != null) {
            if (this.einleitungsstelle.getErstellDat() != null) {
                getErstellDatDatum().setDate(this.einleitungsstelle.getErstellDat());
            }

            if(this.einleitungsstelle.getHerkunft() != null) {
                getHerkunftFeld().setText(this.einleitungsstelle.getHerkunft());
            }

            if (this.einleitungsstelle.getBezeichnung() != null) {
                getBezeichnungFeld().setText(this.einleitungsstelle.getBezeichnung());
            }

            if (this.einleitungsstelle.getGewaessernameAlias3() != null) {
                getGewaessernameAlias3Feld().setText(this.einleitungsstelle.getGewaessernameAlias3());
            }

            if (this.einleitungsstelle.getGewaessernameNs() != null) {
                getGewaessernameNsFeld().setText(this.einleitungsstelle.getGewaessernameNs());
            }

            if (this.einleitungsstelle.getNadiaId() != null) {
                getNadiaIdFeld().setText(this.einleitungsstelle.getNadiaId());
            }

            if (this.einleitungsstelle.getStillgelegtAm() != null) {
                getStillgelegtAmDatum().setDate(this.einleitungsstelle.getStillgelegtAm());
            }

            if (this.einleitungsstelle.getTypIndirekt() != null) {
                getTypIndirektCheck().setSelected(this.einleitungsstelle.getTypIndirekt());
            }

            if (this.einleitungsstelle.getTypIndGewDirekt() != null) {
                getTypIndGewDirektCheck().setSelected(this.einleitungsstelle.getTypIndGewDirekt());
            }

            if (this.einleitungsstelle.getTypKommTrenn() != null) {
                getTypKommTrennCheck().setSelected(this.einleitungsstelle.getTypKommTrenn());
            }


            if (this.einleitungsstelle.getTypPrivatTrenn() != null) {
                getTypPrivatTrennCheck().setSelected(this.einleitungsstelle.getTypPrivatTrenn());
            }

            if (this.einleitungsstelle.getTypSonstige() != null) {
                getTypSonstigeCheck().setSelected(this.einleitungsstelle.getTypSonstige());
            }

            if (this.einleitungsstelle.getTypAusserortStrasseneinl() != null) {
                getTypAusserortStrasseneinlCheck().setSelected(this.einleitungsstelle.getTypAusserortStrasseneinl());
            }

            if (this.einleitungsstelle.getStationierungNs3() != null) {
                getStationierungNs3Feld().setText(new GermanDouble(this.einleitungsstelle.getStationierungNs3()).toString());
            }

            if (this.einleitungsstelle.getEinzugsgebiet() != null) {
                getEinzugsgebietFeld().setText(new GermanDouble(this.einleitungsstelle.getEinzugsgebiet()).toString());
            }

            if (this.einleitungsstelle.getStationierungSt3() != null) {
                getStationierungSt3Feld().setText(new GermanDouble(this.einleitungsstelle.getStationierungSt3()).toString());
            }

            if (this.einleitungsstelle.getAbgaberelEinl() != null) {
                getAbgaberelEinlFeld().setText(this.einleitungsstelle.getAbgaberelEinl().toString());
            }

            if (this.einleitungsstelle.getE32() != null) {
                getE32Feld().setText(this.einleitungsstelle.getE32().toString());
            }

            if (this.einleitungsstelle.getN32() != null) {
                getN32Feld().setText(this.einleitungsstelle.getN32().toString());
            }

            if (this.einleitungsstelle.getKanalArtOpt() != null) {
                getKanalArtOptFeld().setText(this.einleitungsstelle.getKanalArtOpt().toString());
            }

            if (this.einleitungsstelle.getStationierung3Opt() != null) {
                getStationierung3OptFeld().setText(this.einleitungsstelle.getStationierung3Opt().toString());
            }

            if (this.einleitungsstelle.getSchutzzoneOpt() != null) {
                getSchutzzoneOptFeld().setText(this.einleitungsstelle.getSchutzzoneOpt().toString());
            }

            if (this.klaeranlagen != null) {
                getKlaeranlageBox().setModel(new DefaultComboBoxModel<Klaeranlage>(this.klaeranlagen));
                getKlaeranlageBox().setSelectedIndex(-1);
            }

            if (this.referenz != null) {
                getKlaeranlageBox().setSelectedItem(this.referenz.getKlaeranlageByZKaNr());
            }
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
        }
    }

    /**
     * Methode die alle Eingabefelder des Panels auf den Standard zurücksetzt.
     */
    public void clearForm() {
        getErstellDatDatum().setDate(null);
        getHerkunftFeld().setText(null);
        getBezeichnungFeld().setText(null);
        getGewaessernameAlias3Feld().setText(null);
        getGewaessernameNsFeld().setText(null);
        getNadiaIdFeld().setText(null);
        getStillgelegtAmDatum().setDate(null);
        getTypIndirektCheck().setSelected(false);
        getTypIndGewDirektCheck().setSelected(false);
        getTypKommTrennCheck().setSelected(false);
        getTypPrivatTrennCheck().setSelected(false);
        getTypSonstigeCheck().setSelected(false);
        getTypAusserortStrasseneinlCheck().setSelected(false);
        getStationierungNs3Feld().setText(null);
        getEinzugsgebietFeld().setText(null);
        getStationierungSt3Feld().setText(null);
        getAbgaberelEinlFeld().setText(null);
        getE32Feld().setText(null);
        getN32Feld().setText(null);
        getKanalArtOptFeld().setText(null);
        getStationierung3OptFeld().setText(null);
        getSchutzzoneOptFeld().setText(null);
        getKlaeranlageBox().setSelectedIndex(-1);

    }

    /**
     * Methode die je nach Eingabewert alles Eingabefelder des Panels aktiviert oder
     * deaktiviert.
     * @param enabled
     */
    public void enableAll(boolean enabled) {
        getErstellDatDatum().setEnabled(enabled);
        getHerkunftFeld().setEnabled(enabled);
        getBezeichnungFeld().setEnabled(enabled);
        getGewaessernameAlias3Feld().setEnabled(enabled);
        getGewaessernameNsFeld().setEnabled(enabled);
        getNadiaIdFeld().setEnabled(enabled);
        getStillgelegtAmDatum().setEnabled(enabled);
        getTypIndirektCheck().setEnabled(enabled);
        getTypIndGewDirektCheck().setEnabled(enabled);
        getTypKommTrennCheck().setEnabled(enabled);
        getTypPrivatTrennCheck().setEnabled(enabled);
        getTypSonstigeCheck().setEnabled(enabled);
        getTypAusserortStrasseneinlCheck().setEnabled(enabled);
        getStationierungNs3Feld().setEnabled(enabled);
        getEinzugsgebietFeld().setEnabled(enabled);
        getStationierungSt3Feld().setEnabled(enabled);
        getAbgaberelEinlFeld().setEnabled(enabled);
        getE32Feld().setEnabled(enabled);
        getN32Feld().setEnabled(enabled);
        getKanalArtOptFeld().setEnabled(enabled);
        getStationierung3OptFeld().setEnabled(enabled);
        getSchutzzoneOptFeld().setEnabled(enabled);
        getKlaeranlageBox().setEnabled(enabled);
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Methode die, die Eingabefelder des Panels welche einen Wert haben
     * in die Einleitungsstelle der Datenbank schreibt.
     * @return boolean
     */
    private boolean saveElkaEinleitungsstelleDaten() {
        boolean success;

        this.einleitungsstelle.setAktualDat(new Date());

        Date erstellDat = this.erstellDatDatum.getDate();
        this.einleitungsstelle.setErstellDat(erstellDat);

        String herkunft = this.herkunftFeld.getText();
        if ("".equals(herkunft)) {
            this.einleitungsstelle.setHerkunft(null);
        } else {
            this.einleitungsstelle.setHerkunft(herkunft);
        }

        String bezeichnung = this.bezeichnungFeld.getText();
        if("".equals(bezeichnung)) {
            this.einleitungsstelle.setBezeichnung(null);
        } else {
            this.einleitungsstelle.setBezeichnung(bezeichnung);
        }

        String gewaessernameAlias3 = this.gewaessernameAlias3Feld.getText();
        if("".equals(gewaessernameAlias3)) {
            this.einleitungsstelle.setGewaessernameAlias3(null);
        } else {
            this.einleitungsstelle.setGewaessernameAlias3(gewaessernameAlias3);
        }

        String gewaessernameNs = this.gewaessernameNsFeld.getText();
        if("".equals(gewaessernameNs)) {
            this.einleitungsstelle.setGewaessernameNs(null);
        } else {
            this.einleitungsstelle.setGewaessernameNs(gewaessernameNs);
        }

        String nadiaId = this.nadiaIdFeld.getText();
        if("".equals(nadiaId)) {
            this.einleitungsstelle.setNadiaId(null);
        } else {
            this.einleitungsstelle.setNadiaId(nadiaId);
        }

        Date stillgelegtAm = this.stillgelegtAmDatum.getDate();
        this.einleitungsstelle.setStillgelegtAm(stillgelegtAm);

        if(getTypIndirektCheck().isSelected()) {
            this.einleitungsstelle.setTypIndirekt(true);
        } else {
            this.einleitungsstelle.setTypIndirekt(false);
        }

        if(getTypIndGewDirektCheck().isSelected()) {
            this.einleitungsstelle.setTypIndGewDirekt(true);
        } else {
            this.einleitungsstelle.setTypIndGewDirekt(false);
        }

        if(getTypKommTrennCheck().isSelected()) {
            this.einleitungsstelle.setTypKommTrenn(true);
        } else {
            this.einleitungsstelle.setTypKommTrenn(false);
        }

        if(getTypPrivatTrennCheck().isSelected()) {
            this.einleitungsstelle.setTypPrivatTrenn(true);
        } else {
            this.einleitungsstelle.setTypPrivatTrenn(false);
        }

        if(getTypSonstigeCheck().isSelected()) {
            this.einleitungsstelle.setTypSonstige(true);
        } else {
            this.einleitungsstelle.setTypSonstige(false);
        }

        if(getTypAusserortStrasseneinlCheck().isSelected()) {
            this.einleitungsstelle.setTypAusserortStrasseneinl(true);
        } else {
            this.einleitungsstelle.setTypAusserortStrasseneinl(false);
        }

        Double stationierungNs3 = this.stationierungNs3Feld.getDoubleValue();
        this.einleitungsstelle.setStationierungNs3(stationierungNs3);

        Double einzugsgebiet = this.einzugsgebietFeld.getDoubleValue();
        this.einleitungsstelle.setEinzugsgebiet(einzugsgebiet);

        Double stationierungSt3 = this.stationierungSt3Feld.getDoubleValue();
        this.einleitungsstelle.setStationierungSt3(stationierungSt3);

        Integer abgaberelEinl = ((IntegerField)this.abgaberelEinlFeld).getIntValue();
        this.einleitungsstelle.setAbgaberelevanteEltOpt(abgaberelEinl);

        Integer e32 = ((IntegerField)this.e32Feld).getIntValue();
        this.einleitungsstelle.setE32(e32);

        Integer n32 = ((IntegerField)this.n32Feld).getIntValue();
        this.einleitungsstelle.setN32(n32);

        Integer kanalArtOpt = ((IntegerField)this.kanalArtOptFeld).getIntValue();
        this.einleitungsstelle.setKanalArtOpt(kanalArtOpt);

        Integer stationierung3Opt = ((IntegerField)this.stationierung3OptFeld).getIntValue();
        this.einleitungsstelle.setStationierung3Opt(stationierung3Opt);

        Integer schutzzoneOpt = ((IntegerField)this.schutzzoneOptFeld).getIntValue();
        this.einleitungsstelle.setSchutzzoneOpt(schutzzoneOpt);

        success = this.einleitungsstelle.merge();
        if (success) {
            log.debug("Einleitungsstelle"
                    + this.einleitungsstelle.getObjekt().getBetreiberid()
                    .getBetrname() + " gespeichert.");
        } else {
            log.debug("Einleitungsstelle" + this.einleitungsstelle
                    + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    /**
     * Methode, die die Verknüpfung der Einleitungsstelle mit der Kläranlage
     * in der Relation elka_referenz herstellt
     * @return booleanS
     */
    private boolean saveKlaeranlageDaten() {
        boolean success;
        if (getKlaeranlageBox().getSelectedItem() != null) {
            if (this.referenz == null) {
            this.referenz = new Referenz();
            }
            this.referenz.setKlaeranlageByZKaNr((Klaeranlage)getKlaeranlageBox().getSelectedItem());
            this.referenz.setqEl(this.einleitungsstelle);
            success = this.referenz.merge();
        }
        else {
            success = false;
        }
        return success;
    }

    /**
     * Methode die eine neue Datenbank Einleitungsstelle erzeugt,
     * sofern diese noch nicht existiert oder das Hauptmodul neu ist.
     */
    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.einleitungsstelle == null) {
            // Neue EinleitungstellePanel erzeugen
            this.einleitungsstelle = new Einleitungsstelle();
            //Objekt_Id setzen
            this.einleitungsstelle.setObjekt(this.hauptModul.getObjekt());
            this.einleitungsstelle.merge();
            //ElkaEinleitungsstelle.merge(this.einleitungstelle);
            log.debug("Neue ElkaEinleitungsstelle " + this.einleitungsstelle + " gespeichert.");
        }
    }

    /**
     * Get-Methode die das ErstellDatDatum des Panels zurückgibt:
     * @return {@link TextFieldDateChooser}
     */
    private TextFieldDateChooser getErstellDatDatum() {
        if (this.erstellDatDatum == null) {
            this.erstellDatDatum = new TextFieldDateChooser();
        }
        return this.erstellDatDatum;
    }

    /**
     * Get-Methode die das Herkunftsfeld des Panels zurückgibt:
     * @return {@link JTextField}
     */
    private JTextField getHerkunftFeld() {
        if (this.herkunftFeld == null) {
            this.herkunftFeld = new LimitedTextField(50);
        }
        return this.herkunftFeld;
    }

    /**
     * Get-Methode die das Bezeichnungsfeld des Panels zurückgibt:
     * @return {@link JTextField}
     */
    private JTextField getBezeichnungFeld() {
        if (this.bezeichnungFeld == null) {
            this.bezeichnungFeld = new LimitedTextField(50);
        }
        return this.bezeichnungFeld;
    }

    /**
     * Get-Methode die das GewässernameAlias3Feld des Panels zurückgibt:
     * @return {@link JTextField}
     */
    private JTextField getGewaessernameAlias3Feld() {
        if (this.gewaessernameAlias3Feld == null) {
            this.gewaessernameAlias3Feld = new LimitedTextField(50);
        }
        return this.gewaessernameAlias3Feld;
    }

    /**
     * Get-Methode die das GewässsernameNsFeld des Panels zurückgibt:
     * @return {@link JTextField}
     */
    private JTextField getGewaessernameNsFeld() {
        if (this.gewaessernameNsFeld == null) {
            this.gewaessernameNsFeld = new LimitedTextField(50);
        }
        return this.gewaessernameNsFeld;
    }
     /**
      * Get-Methode die das NadiaIdFeld des Panels zurückgibt:
      * @return {@link JTextField}
      */
    private JTextField getNadiaIdFeld() {
        if (this.nadiaIdFeld == null) {
            this.nadiaIdFeld = new LimitedTextField(50);
        }
        return this.nadiaIdFeld;
    }

    /**
     * Get-Methode die das StillgelegtAmDattum des Panels zurückgibt:
     * @return {@link TextFieldDateChooser}
     */
    private TextFieldDateChooser getStillgelegtAmDatum() {
        if (this.stillgelegtAmDatum == null){
            this.stillgelegtAmDatum = new TextFieldDateChooser();
        }
        return this.stillgelegtAmDatum;
    }

    /**
     * Get-Methode die das TypIndirektCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypIndirektCheck() {
        if (this.typIndirektCheck == null) {
            this.typIndirektCheck = new JCheckBox("Typ");
        }
        return this.typIndirektCheck;
    }

    /**
     * Get-Methode die das TypIndGewDirektCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypIndGewDirektCheck() {
        if (this.typIndGewDirektCheck == null) {
            this.typIndGewDirektCheck = new JCheckBox("TypIndGewDirek");
        }
        return this.typIndGewDirektCheck;
    }

    /**
     * Get-Methode die das TypKommTrennCheck des Panels zurückgibt:
     * @return {@link JcheckBox}
     */
    private JCheckBox getTypKommTrennCheck() {
        if (this.typKommTrennCheck == null) {
            this.typKommTrennCheck =  new JCheckBox("TypKommTrenn");
        }
        return this.typKommTrennCheck;
    }

    /**
     * Get-Methode die das TypPrivatTrennCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypPrivatTrennCheck() {
        if (this.typPrivatTrennCheck == null) {
            this.typPrivatTrennCheck = new JCheckBox("TypPrivatTren");
        }
        return this.typPrivatTrennCheck;
    }

    /**
     * Get-Methode die das TypSonstigeCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypSonstigeCheck() {
        if (this.typSonstigeCheck == null) {
            this.typSonstigeCheck = new JCheckBox("TypSonstige");
        }
        return this.typSonstigeCheck;
    }

    /**
     * Get-Methode die das TypAusserortStrasseneinlCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypAusserortStrasseneinlCheck() {
        if (this.typAusserortStrasseneinlCheck  == null) {
            this.typAusserortStrasseneinlCheck = new JCheckBox("TypAusserortStrasseneinl");
        }
        return this.typAusserortStrasseneinlCheck;
    }

    /**
     * Get-Methode die das StationierungNs3Feld des Panels zurückgibt:
     * @return {@link DoubleField}
     */
    private DoubleField getStationierungNs3Feld() {
        if (this.stationierungNs3Feld == null) {
            this.stationierungNs3Feld = new DoubleField(50);
        }
        return this.stationierungNs3Feld;
    }

    /**
     * Get-Methode die das EinzugsgebietFeld des Panels zurückgibt:
     * @return {@link DoubleField}
     */
    private DoubleField getEinzugsgebietFeld() {
        if (this.einzugsgebietFeld == null) {
            this.einzugsgebietFeld = new DoubleField(50);
        }
        return this.einzugsgebietFeld;
    }

    /**
     * Get-Methode die das StationierungSt3Feld des Panels zurückgibt:
     * @return {@link DoubleField}
     */
    private DoubleField getStationierungSt3Feld() {
        if (this.stationierungSt3Feld == null) {
            this.stationierungSt3Feld = new DoubleField(50);
        }
        return this.stationierungSt3Feld;
    }

    /**
     * Get-Methode die das AbgaberelEinlFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getAbgaberelEinlFeld() {
        if (this.abgaberelEinlFeld == null) {
            this.abgaberelEinlFeld = new IntegerField();
        }
        return this.abgaberelEinlFeld;
    }

    /**
     * Get-Methode die das E32Feld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getE32Feld() {
        if (this.e32Feld == null) {
            this.e32Feld = new IntegerField();
        }
        return this.e32Feld;
    }

    /***
     * Get-Methode die das N32Feld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getN32Feld() {
        if (this.n32Feld == null) {
            this.n32Feld = new IntegerField();
        }
        return this.n32Feld;
    }

    /**
     * Get-Methode die das KanalArtOptFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getKanalArtOptFeld() {
        if (this.kanalArtOptFeld == null) {
            this.kanalArtOptFeld = new IntegerField();
        }
        return this.kanalArtOptFeld;
    }

    /**
     * Get-Methode die das Stationierung3OptFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getStationierung3OptFeld() {
        if (this.stationierung3OptFeld == null) {
            this.stationierung3OptFeld = new IntegerField();
        }
        return this.stationierung3OptFeld;
    }

    /**
     * Get-Methode die die KlaeranlageBox des Panels zurückgibt
     * @return {@link JComboBox}
     */
    private JComboBox<Klaeranlage> getKlaeranlageBox() {
        if (this.klaeranlageBox == null) {

            this.klaeranlageBox = new JComboBox<Klaeranlage>();
            this.klaeranlageBox.setKeySelectionManager(new MyKeySelectionManager());

        }
        return this.klaeranlageBox;
    }

    /**
     * Get-Methode die das SchutzzoneOptFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getSchutzzoneOptFeld() {
        if (this.schutzzoneOptFeld == null) {
            this.schutzzoneOptFeld = new IntegerField();
            }
        return this.schutzzoneOptFeld;
    }

    private JTable getObjektverknuepungTabelle() {

        if (this.objektVerknuepfungModel == null) {
            this.objektVerknuepfungModel = new ObjektVerknuepfungModel(
                this.hauptModul.getObjekt());

            if (this.objektverknuepfungTabelle == null) {
                this.objektverknuepfungTabelle = new JTable(
                    this.objektVerknuepfungModel);
            } else {
                this.objektverknuepfungTabelle
                    .setModel(this.objektVerknuepfungModel);
            }
            this.objektverknuepfungTabelle.getColumnModel().getColumn(0)
                .setPreferredWidth(5);
            this.objektverknuepfungTabelle.getColumnModel().getColumn(1)
                .setPreferredWidth(100);
            this.objektverknuepfungTabelle.getColumnModel().getColumn(2)
                .setPreferredWidth(250);

            this.objektverknuepfungTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getObjektverknuepungTabelle().rowAtPoint(
                                origin);

                            if (row != -1) {
                                Objektverknuepfung obj = EinleitungsstellePanel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != EinleitungsstellePanel.this.hauptModul
                                    .getObjekt().getId().intValue())
                                    EinleitungsstellePanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                else
                                    EinleitungsstellePanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                EinleitungsstellePanel.this.hauptModul.getManager()
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
                (KeyStroke) getVerknuepfungLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getVerknuepfungLoeschAction().getValue(Action.NAME));
            this.objektverknuepfungTabelle.getActionMap().put(
                getVerknuepfungLoeschAction().getValue(Action.NAME),
                getVerknuepfungLoeschAction());
        }

        return this.objektverknuepfungTabelle;

    }

    private void showVerknuepfungPopup(MouseEvent e) {
        if (this.verknuepfungPopup == null) {
            this.verknuepfungPopup = new JPopupMenu("Objekt");
            JMenuItem loeschItem = new JMenuItem(getVerknuepfungLoeschAction());
            this.verknuepfungPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektverknuepfungTabelle.rowAtPoint(origin);

            if (row != -1) {
                this.objektverknuepfungTabelle
                    .setRowSelectionInterval(row, row);
                this.verknuepfungPopup.show(e.getComponent(), e.getX(),
                    e.getY());
            }
        }
    }

    /**
     * Methode die den SaveElkaEinleitungsstelleButton zurückgibt sofern er existiert,
     * ansonsten wird ein neuer erstellt und diesem einen {@link ActionListener} hinzugefügt,
     * der bei einem Klick die Methoden <code>saveElkaEinleitungsstelleDaten</code> und
     *<code>saveKlaeranlageDaten</code> ausführt.
     * @see #saveElkaEinleitungsstelle()
     * @see #saveKlaeranlageDaten()
     * @return {@link JButton}
     */
    private JButton getSaveElkaEinleitungsstelleButton() {
        if (this.saveElkaEinleitungsstelleButton == null) {
            this.saveElkaEinleitungsstelleButton = new JButton("Speichern");

            this.saveElkaEinleitungsstelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    String status = "";
                    if(saveElkaEinleitungsstelleDaten()) {
                        status = "Einleitungsstelle " +
                    EinleitungsstellePanel.this.einleitungsstelle.getId()
                    + " erfolgreich gespeichert.";
                    } else {
                        status = "Fehler beim Speichern der Einleitungsstelle!";
                    }
                    if (saveKlaeranlageDaten()) {
                        status = status + " & Verknüpfung mit der Kläranlage "
                            + EinleitungsstellePanel.this.referenz.getKlaeranlageByZKaNr().getAnlage()
                                + " über die Referenz-Tabelle erfolgreich gespeichert.";
                    } else {
                        status = status + " & Verknüpfung konnte nicht gespeichert werden!";
                    }
                    if(status.startsWith("Einleitungsstelle")) {
                        EinleitungsstellePanel.this.hauptModul.getFrame().changeStatus(status,
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        EinleitungsstellePanel.this.hauptModul.getFrame().changeStatus(status,
                            HauptFrame.ERROR_COLOR);
                    }
                    EinleitungsstellePanel.this.hauptModul.fillForm();
            }
            });
        }
        return this.saveElkaEinleitungsstelleButton;
    }

    private Action getVerknuepfungLoeschAction() {
        if (this.verknuepfungLoeschAction == null) {
            this.verknuepfungLoeschAction = new AbstractAction("Löschen") {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung = EinleitungsstellePanel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (EinleitungsstellePanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                EinleitungsstellePanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                EinleitungsstellePanel.this.hauptModul.getFrame()
                                    .changeStatus(
                                        "Konnte das Objekt nicht löschen!",
                                        HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.verknuepfungLoeschAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_L));
            this.verknuepfungLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.verknuepfungLoeschAction;
    }

    private JButton getSelectObjektButton() {
        if (this.selectObjektButton == null) {
            this.selectObjektButton = new JButton("Objekt auswählen");

            this.selectObjektButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ObjektChooser chooser = new ObjektChooser(
                            EinleitungsstellePanel.this.hauptModul.getFrame(),
                            EinleitungsstellePanel.this.einleitungsstelle.getObjekt(),
                            EinleitungsstellePanel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }

}