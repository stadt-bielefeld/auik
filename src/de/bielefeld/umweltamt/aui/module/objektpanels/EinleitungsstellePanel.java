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
import javax.swing.JLabel;
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
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
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
    private JTextField bezeichnungFeld = null;
    private JTextField gewaessernameAlias3Feld = null;
    private JTextField gewaessernameNsFeld = null;
    private JTextField nadiaIdFeld = null;
    private TextFieldDateChooser stillgelegtAmDatum = null;
    private JCheckBox typIndirektCheck = null;
    private JCheckBox typIndGewDirektCheck = null;
    private JCheckBox typKommTrennCheck = null;
    private JCheckBox typKommMischCheck = null;
    private JCheckBox typIndGewTrennCheck = null;
    private JCheckBox typIndGewMischCheck = null;
    private JCheckBox typNwPrivatTrennCheck = null;
    private JCheckBox typAusserortStrasseneinlCheck = null;
    private JCheckBox typSonstigeCheck = null;
    private DoubleField stationierungNs3Feld = null;
    private DoubleField einzugsgebietFeld = null;
    private DoubleField stationierungSt3Feld = null;
    private JFormattedTextField stationierung3OptFeld = null;
    private JFormattedTextField schutzzoneOptFeld = null;

    private JComboBox abgaberelEinlBox = null;
    private JComboBox kanalArtOptFeld = null;
    
    //Kläranlage
    private Klaeranlage[] klaeranlagen = null;
    private JComboBox<Klaeranlage> klaeranlageBox = null;
    private JButton saveElkaEinleitungsstelleButton = null;
    
    // Daten
    private Einleitungsstelle  einleitungsstelle = null;
    private Referenz referenz = null;
    
    //Label
    private JLabel abgabepflichtLb = new JLabel("Abgabepflichtige Einleitung: ");
    private JLabel kanalartLb = new JLabel("Kanalart: ");
    private JLabel klaeranlageLb = new JLabel("Kläranlage: ");

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
                "r:100dlu, 5dlu, 100dlu, 5dlu, r:80dlu, 5dlu, 100dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
    builder.nextLine();
    builder.appendRow("fill:15dlu");
        
    builder.append("Bezeichnung:", getBezeichnungFeld(), 5);
    
    builder.nextLine();
	builder.appendSeparator("Art der Einleitung");

    builder.nextLine();
    builder.append(getTypIndirektCheck(), 3);

    builder.nextLine();
    builder.append(getTypIndGewDirektCheck(), 3);
    builder.append(getTypNwPrivatTrennCheck(), 3);

    builder.nextLine();
	builder.append(getTypIndGewTrennCheck(), 3);
    builder.append(getTypKommTrennCheck(), 3);

    builder.nextLine();
    builder.append(getTypIndGewMischCheck(), 3);
    builder.append(getTypKommMischCheck(), 3);

    builder.nextLine();
	builder.append(getTypAusserortStrasseneinlCheck(), 3);
    builder.append(getTypSonstigeCheck(), 3);
    builder.appendRow("fill:20dlu");

    builder.nextLine();
    builder.appendSeparator("Details");
    builder.append("Erstellung:", getErstellDatDatum());
    builder.append("Stilllegedatum:", getStillgelegtAmDatum());

    builder.nextLine();
    builder.append(abgabepflichtLb);
    builder.append(getAbgaberelEinlBox());


    builder.nextLine();
    builder.append(kanalartLb);
    builder.append(getKanalArtBox());
    builder.append(klaeranlageLb);
    builder.append(getKlaeranlageBox());

    builder.nextLine();
    builder.appendRow("fill:15dlu");
    builder.appendSeparator("Verknüpfungen");

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
        otherChecks.add(getTypKommMischCheck());
        otherChecks.add(getTypIndGewTrennCheck());
        otherChecks.add(getTypIndGewMischCheck());
        otherChecks.add(getTypNwPrivatTrennCheck());
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
                    switchArtEinleitung("Indirekteinleiter");
                } else {
                    otherChecks.forEach((check) -> {
                        check.setEnabled(true);
                        indCheck.setEnabled(false);
                    });
                    switchArtEinleitung("Direkteinleiter");
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

            if (this.einleitungsstelle.getBezeichnung() != null) {
                getBezeichnungFeld().setText(this.einleitungsstelle.getBezeichnung());
            }

            if (this.einleitungsstelle.getStillgelegtAm() != null) {
                getStillgelegtAmDatum().setDate(this.einleitungsstelle.getStillgelegtAm());
            }

            getTypIndirektCheck().setSelected(this.einleitungsstelle.isTypIndirekteinleitungTog());

            getTypIndGewDirektCheck().setSelected(this.einleitungsstelle.isTypIndusGewerbDirekteinleitungTog());

            getTypKommMischCheck().setSelected(this.einleitungsstelle.isTypKommNwMischTog());

            getTypKommTrennCheck().setSelected(this.einleitungsstelle.isTypKommNwTrennTog());

            getTypNwPrivatTrennCheck().setSelected(this.einleitungsstelle.isTypNwPrivatTrennTog());

            getTypIndGewMischCheck().setSelected(this.einleitungsstelle.isTypIndusGewerbNwMischTog());

            getTypIndGewTrennCheck().setSelected(this.einleitungsstelle.isTypIndusGewerbNwTrennTog());

            getTypAusserortStrasseneinlCheck().setSelected(this.einleitungsstelle.isTypAusseroertlicheStrasseneinleitungTog());

            getTypSonstigeCheck().setSelected(this.einleitungsstelle.isTypSonstigeTog());
            
            String[] abgabe = {"-",  "abgabepflichtig", "nicht abgabepflichtig"};
            getAbgaberelEinlBox().setModel(new DefaultComboBoxModel(abgabe));
            
            getAbgaberelEinlBox().setSelectedItem(einleitungsstelle.getAbgaberelDescriptionFromId(this.einleitungsstelle.getAbgaberelevanteEltOpt()));
            
            String[] kanalart = {"-",  "Schmutzwasser", "Mischwasser", 
            		"Niederschlagswasserk"};
            getKanalArtBox().setModel(new DefaultComboBoxModel(kanalart));
            
            getKanalArtBox().setSelectedItem(einleitungsstelle.getKanalartDescriptionFromId(this.einleitungsstelle.getKanalArtOpt()));

            if (this.klaeranlagen != null) {
                getKlaeranlageBox().setModel(new DefaultComboBoxModel<Klaeranlage>(this.klaeranlagen));
                getKlaeranlageBox().setSelectedIndex(-1);
            }

            if (this.referenz != null) {
                getKlaeranlageBox().setSelectedItem(this.referenz.getKlaeranlageByZKaNr());
            }
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
            
            if (typIndirektCheck.isSelected()) {
            	switchArtEinleitung("Indirekteinleiter");
            } else {
            	switchArtEinleitung("Direkteinleite");
            }
            
            
        }
    }

    /**
     * Methode die alle Eingabefelder des Panels auf den Standard zurücksetzt.
     */
    public void clearForm() {
        getErstellDatDatum().setDate(null);
        getBezeichnungFeld().setText(null);
        getStillgelegtAmDatum().setDate(null);
        getTypIndirektCheck().setSelected(false);
        getTypIndGewDirektCheck().setSelected(false);
        getTypKommTrennCheck().setSelected(false);
        getTypKommMischCheck().setSelected(false);
        getTypNwPrivatTrennCheck().setSelected(false);
        getTypIndGewTrennCheck().setSelected(false);
        getTypIndGewMischCheck().setSelected(false);
        getTypAusserortStrasseneinlCheck().setSelected(false);
        getTypSonstigeCheck().setSelected(false);
        getAbgaberelEinlBox().setSelectedIndex(-1);
        getKanalArtBox().setSelectedIndex(-1);
        getKlaeranlageBox().setSelectedIndex(-1);

    }

    /**
     * Methode die je nach Eingabewert alles Eingabefelder des Panels aktiviert oder
     * deaktiviert.
     * @param enabled
     */
    public void enableAll(boolean enabled) {
        getErstellDatDatum().setEnabled(enabled);
        getBezeichnungFeld().setEnabled(enabled);
        getStillgelegtAmDatum().setEnabled(enabled);
        getTypIndirektCheck().setEnabled(enabled);
        getTypIndGewDirektCheck().setEnabled(enabled);
        getTypKommTrennCheck().setEnabled(enabled);
        getTypKommMischCheck().setEnabled(enabled);
        getTypNwPrivatTrennCheck().setEnabled(enabled);
        getTypIndGewTrennCheck().setEnabled(enabled);
        getTypIndGewMischCheck().setEnabled(enabled);
        getTypSonstigeCheck().setEnabled(enabled);
        getTypAusserortStrasseneinlCheck().setEnabled(enabled);
        getAbgaberelEinlBox().setEnabled(enabled);
        getKanalArtBox().setEnabled(enabled);
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

        String bezeichnung = this.bezeichnungFeld.getText();
        if("".equals(bezeichnung)) {
            this.einleitungsstelle.setBezeichnung(null);
        } else {
            this.einleitungsstelle.setBezeichnung(bezeichnung);
        }

        Date stillgelegtAm = this.stillgelegtAmDatum.getDate();
        this.einleitungsstelle.setStillgelegtAm(stillgelegtAm);

        if(getTypIndirektCheck().isSelected()) {
            this.einleitungsstelle.setTypIndirekteinleitungTog(true);
        } else {
            this.einleitungsstelle.setTypIndirekteinleitungTog(false);
        }

        if(getTypIndGewDirektCheck().isSelected()) {
            this.einleitungsstelle.setTypIndusGewerbDirekteinleitungTog(true);
        } else {
            this.einleitungsstelle.setTypIndusGewerbDirekteinleitungTog(false);
        }

        if(getTypKommTrennCheck().isSelected()) {
            this.einleitungsstelle.setTypKommNwTrennTog(true);
        } else {
            this.einleitungsstelle.setTypKommNwTrennTog(false);
        }

        if(getTypKommMischCheck().isSelected()) {
            this.einleitungsstelle.setTypKommNwMischTog(true);
        } else {
            this.einleitungsstelle.setTypKommNwMischTog(false);
        }

        if(getTypIndGewTrennCheck().isSelected()) {
            this.einleitungsstelle.setTypIndusGewerbNwTrennTog(true);
        } else {
            this.einleitungsstelle.setTypIndusGewerbNwTrennTog(false);
        }

        if(getTypIndGewMischCheck().isSelected()) {
            this.einleitungsstelle.setTypIndusGewerbNwMischTog(true);
        } else {
            this.einleitungsstelle.setTypIndusGewerbNwMischTog(false);
        }

        if(getTypNwPrivatTrennCheck().isSelected()) {
            this.einleitungsstelle.setTypNwPrivatTrennTog(true);
        } else {
            this.einleitungsstelle.setTypNwPrivatTrennTog(false);
        }

        if(getTypAusserortStrasseneinlCheck().isSelected()) {
            this.einleitungsstelle.setTypAusseroertlicheStrasseneinleitungTog(true);
        } else {
            this.einleitungsstelle.setTypAusseroertlicheStrasseneinleitungTog(false);
        }
        
        if(getTypSonstigeCheck().isSelected()) {
            this.einleitungsstelle.setTypSonstigeTog(true);
        } else {
            this.einleitungsstelle.setTypSonstigeTog(false);
        }

        this.einleitungsstelle.setAbgaberelevanteEltOpt(
                Einleitungsstelle.getAbgaberelIdFromDescription((String) getAbgaberelEinlBox().getSelectedItem()));

        this.einleitungsstelle.setKanalArtOpt(
                Einleitungsstelle.getKanalartIdFromDescription((String) getKanalArtBox().getSelectedItem()));

        success = this.einleitungsstelle.merge();
        if (success) {
            log.debug("Einleitungsstelle"
                    + this.einleitungsstelle.getObjekt().getBetreiberid()
                    .getName() + " gespeichert.");
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
            this.typIndirektCheck = new JCheckBox("Indirekteinleitung");
        }
        return this.typIndirektCheck;
    }

    /**
     * Get-Methode die das TypIndGewDirektCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypIndGewDirektCheck() {
        if (this.typIndGewDirektCheck == null) {
            this.typIndGewDirektCheck = new JCheckBox("indus./gew. Direkteinleitung");
        }
        return this.typIndGewDirektCheck;
    }

    /**
     * Get-Methode die das TypKommTrennCheck des Panels zurückgibt:
     * @return {@link JcheckBox}
     */
    private JCheckBox getTypKommTrennCheck() {
        if (this.typKommTrennCheck == null) {
            this.typKommTrennCheck =  new JCheckBox("kommunales NW (Trennverfahren)");
        }
        return this.typKommTrennCheck;
    }

    /**
     * Get-Methode die das TypPrivatTrennCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypIndGewTrennCheck() {
        if (this.typIndGewTrennCheck == null) {
            this.typIndGewTrennCheck = new JCheckBox("indus./gew. NW (Trennverfahren)");
        }
        return this.typIndGewTrennCheck;
    }

    /**
     * Get-Methode die das TypKommTrennCheck des Panels zurückgibt:
     * @return {@link JcheckBox}
     */
    private JCheckBox getTypKommMischCheck() {
        if (this.typKommMischCheck == null) {
            this.typKommMischCheck =  new JCheckBox("kommunales NW (Mischverfahren)");
        }
        return this.typKommMischCheck;
    }

    /**
     * Get-Methode die das TypPrivatTrennCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypIndGewMischCheck() {
        if (this.typIndGewMischCheck == null) {
            this.typIndGewMischCheck = new JCheckBox("indus./gew. NW (Mischverfahren)");
        }
        return this.typIndGewMischCheck;
    }

    /**
     * Get-Methode die das TypSonstigeCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypSonstigeCheck() {
        if (this.typSonstigeCheck == null) {
            this.typSonstigeCheck = new JCheckBox("sonstige Direkteinleitung");
        }
        return this.typSonstigeCheck;
    }

    /**
     * Get-Methode die das TypSonstigeCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypNwPrivatTrennCheck() {
        if (this.typNwPrivatTrennCheck == null) {
            this.typNwPrivatTrennCheck = new JCheckBox("NW aus privatem Bereich (Trennverfahren)");
        }
        return this.typNwPrivatTrennCheck;
    }

    /**
     * Get-Methode die das TypAusserortStrasseneinlCheck des Panels zurückgibt:
     * @return {@link JCheckBox}
     */
    private JCheckBox getTypAusserortStrasseneinlCheck() {
        if (this.typAusserortStrasseneinlCheck  == null) {
            this.typAusserortStrasseneinlCheck = new JCheckBox("außerörtliche Straßeneinleitung");
        }
        return this.typAusserortStrasseneinlCheck;
    }

    /**
     * Get-Methode die das AbgaberelEinlFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JComboBox getAbgaberelEinlBox() {
        if (this.abgaberelEinlBox == null) {
            this.abgaberelEinlBox = new JComboBox();
        }
        return this.abgaberelEinlBox;
    }

    /**
     * Get-Methode die das KanalArtOptFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JComboBox getKanalArtBox() {
        if (this.kanalArtOptFeld == null) {
            this.kanalArtOptFeld = new JComboBox();
        }
        return this.kanalArtOptFeld;
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

	/**
	 * Switch the panel content according to the given type
	 * 
	 * @param type       New panel content type
	 */
	public void switchArtEinleitung(String type) {
		if (type == null) {
			return;
		}

		if (type.equals("Indirekteinleiter")) {

			abgabepflichtLb.setVisible(false);
            getAbgaberelEinlBox().setVisible(false);
            kanalartLb.setVisible(true);
            getKanalArtBox().setVisible(true);
            klaeranlageLb.setVisible(true);
            klaeranlageBox.setVisible(true);
            
		} else {

			abgabepflichtLb.setVisible(true);
            getAbgaberelEinlBox().setVisible(true);
            kanalartLb.setVisible(false);
            getKanalArtBox().setVisible(false);
            klaeranlageLb.setVisible(false);
            klaeranlageBox.setVisible(false);
			
		}

	}

}