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


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.ElkaEinleitungsstelle;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.GermanDouble;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Einleitungsstelle"-Tab des BasisObjektBearbeiten-Moduls
 * @author Tobias Kaps
 * @date 29.09.2017
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
    private JButton saveElkaEinleitungsstelleButton = null;
    // Daten
    private ElkaEinleitungsstelle  einleitungstelle = null;

    public EinleitungsstellePanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Einleitungstelle";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:70dlu, 5dlu, 90dlu, r:90dlu, 5dlu, 20dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();
        
        builder.appendSeparator("ELKA");
        builder.append("Erstellung:", getErstellDatDatum());
        builder.nextLine();
        builder.append("Herkunft:", getHerkunftFeld());
        builder.nextLine();
        builder.append("Bezeichnung:", getBezeichnungFeld());
        builder.nextLine();
        builder.append("Gewässernamealias3:", getGewaessernameAlias3Feld());
        builder.nextLine();
        builder.append("GewässernameNs: ", getGewaessernameNsFeld());
        builder.nextLine();
        builder.append("NadiaId:", getNadiaIdFeld());
        builder.nextLine();
        builder.append("Datum:", getStillgelegtAmDatum());
        builder.nextLine();
        builder.append("", getTypIndirektCheck());
        builder.nextLine();
        builder.append("", getTypIndGewDirektCheck());
        builder.nextLine();
        builder.append("", getTypKommTrennCheck());
        builder.nextLine();
        builder.append("", getTypPrivatTrennCheck());
        builder.nextLine();
        builder.append("", getTypSonstigeCheck());
        builder.nextLine();
        builder.append("", getTypAusserortStrasseneinlCheck());
        builder.nextLine();
        builder.append("Stationierungns3: ", getStationierungNs3Feld());
        builder.nextLine();
        builder.append("Einzugsgebiet:", getEinzugsgebietFeld());
        builder.nextLine();
        builder.append("Stationierungst3", getStationierungSt3Feld());
        builder.nextLine();
        builder.append("Abgabe einleitung: ", getAbgaberelEinlFeld());
        builder.nextLine();
        builder.append("E32:", getE32Feld());
        builder.nextLine();
        builder.append("N32:",  getN32Feld());
        builder.nextLine();
        builder.append("Kanal Art Opt:", getKanalArtOptFeld());
        builder.nextLine();
        builder.append("Stationierung:", getStationierung3OptFeld());
        builder.nextLine();
        builder.append("Schutzzone:", getSchutzzoneOptFeld());
        builder.nextLine();
        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(
        getSaveElkaEinleitungsstelleButton());
        builder.append(buttonBar, 6);

    }
    
	/**
     * Methode verknüpft das lokal erstelle Objekt einleitungstelle
     * mit der ElkaEinleitungsstelle der Datenbank.
     * @throws RuntimeException
     */
    public void fetchFormData() throws RuntimeException {
    	this.einleitungstelle = ElkaEinleitungsstelle.findByObjektId(
    			this.hauptModul.getObjekt().getId());
    	log.debug("Einleitungsstelle aus DB geholt: " + this.einleitungstelle);
    }
    
    /**
     * Methode setzt die Attribute der Einleitungsstelle aus der Datenbank 
     * auf die der lokalen Einleitungstelle.
     * @throws RuntimeException
     */
    public void updateForm() throws RuntimeException {
    	if (this.einleitungstelle != null) {
    		if (this.einleitungstelle.getErstellDat() != null) {
    			getErstellDatDatum().setDate(this.einleitungstelle.getErstellDat());
    		}
    		
    		if(this.einleitungstelle.getHerkunft() != null) {
    			getHerkunftFeld().setText(this.einleitungstelle.getHerkunft());
    		}
    	
    		if (this.einleitungstelle.getBezeichnung() != null) {
    			getBezeichnungFeld().setText(this.einleitungstelle.getBezeichnung());
    		}
    		
    		if (this.einleitungstelle.getGewaessernameAlias3() != null) {
    			getGewaessernameAlias3Feld().setText(this.einleitungstelle.getGewaessernameAlias3());
    		}
    		
    		if (this.einleitungstelle.getGewaessernameNs() != null) {
    			getGewaessernameNsFeld().setText(this.einleitungstelle.getGewaessernameNs());
    		}
    	
    		if (this.einleitungstelle.getNadiaId() != null) {
    			getNadiaIdFeld().setText(this.einleitungstelle.getNadiaId());
    		}
    		
    		if (this.einleitungstelle.getStillgelegtAm() != null) {
    			getStillgelegtAmDatum().setDate(this.einleitungstelle.getStillgelegtAm());
    		}
    		
    		if (this.einleitungstelle.getTypIndirekt() != null) {
    			getTypIndirektCheck().setSelected(this.einleitungstelle.getTypIndirekt());
    		}
    		
    		if (this.einleitungstelle.getTypIndGewDirekt() != null) {
    			getTypIndGewDirektCheck().setSelected(this.einleitungstelle.getTypIndGewDirekt());
    		}
    		
    		if (this.einleitungstelle.getTypKommTrenn() != null) {
    			getTypKommTrennCheck().setSelected(this.einleitungstelle.getTypKommTrenn());
    		}
    		
    		
    		if (this.einleitungstelle.getTypPrivatTrenn() != null) {
    			getTypPrivatTrennCheck().setSelected(this.einleitungstelle.getTypPrivatTrenn());
    		}
    		
    		if (this.einleitungstelle.getTypSonstige() != null) {
    			getTypSonstigeCheck().setSelected(this.einleitungstelle.getTypSonstige());
    		}
    		
    		if (this.einleitungstelle.getTypAusserortStrasseneinl() != null) {
    			getTypAusserortStrasseneinlCheck().setSelected(this.einleitungstelle.getTypAusserortStrasseneinl());
    		}
    		
    		if (this.einleitungstelle.getStationierungNs3() != null) {
    			getStationierungNs3Feld().setText(new GermanDouble(this.einleitungstelle.getStationierungNs3()).toString());
    		}
    		
    		if (this.einleitungstelle.getEinzugsgebiet() != null) {
    			getEinzugsgebietFeld().setText(new GermanDouble(this.einleitungstelle.getEinzugsgebiet()).toString());
    		}
    		
    		if (this.einleitungstelle.getStationierungSt3() != null) {
    			getStationierungSt3Feld().setText(new GermanDouble(this.einleitungstelle.getStationierungSt3()).toString());
    		}
    		
    		if (this.einleitungstelle.getAbgaberelEinl() != null) {
    			getAbgaberelEinlFeld().setText(this.einleitungstelle.getAbgaberelEinl().toString());
    		}
    		
    		if (this.einleitungstelle.getE32() != null) {
    			getE32Feld().setText(this.einleitungstelle.getE32().toString());
    		}
    		
    		if (this.einleitungstelle.getN32() != null) {
    			getN32Feld().setText(this.einleitungstelle.getN32().toString());
    		}
    		
    		if (this.einleitungstelle.getKanalArtOpt() != null) {
    			getKanalArtOptFeld().setText(this.einleitungstelle.getKanalArtOpt().toString());
    		}
    		
    		if (this.einleitungstelle.getStationierung3Opt() != null) {
    			getStationierung3OptFeld().setText(this.einleitungstelle.getStationierung3Opt().toString());
    		}
    		
    		if (this.einleitungstelle.getSchutzzoneOpt() != null) {
    			getSchutzzoneOptFeld().setText(this.einleitungstelle.getSchutzzoneOpt().toString());
    		} 		
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
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * Methode die, die Eingabefelder des Panels welche einen Wert haben
     * in die Einleitungsstelle der Datenbank schreibt.
     * @return {@link boolean}
     */
    private boolean saveElkaEinleitungsstelleDaten() {
    	boolean success;
    	
    	this.einleitungstelle.setAktualDat(new Date());
    	
    	Date erstellDat = this.erstellDatDatum.getDate();
    	this.einleitungstelle.setErstellDat(erstellDat);
    	
    	String herkunft = this.herkunftFeld.getText();
    	if ("".equals(herkunft)) {
    		this.einleitungstelle.setHerkunft(null);
    	} else {
    		this.einleitungstelle.setHerkunft(herkunft);
    	}
    	
    	String bezeichnung = this.bezeichnungFeld.getText();
    	if("".equals(bezeichnung)) {
    		this.einleitungstelle.setBezeichnung(null);
    	} else {
    		this.einleitungstelle.setBezeichnung(bezeichnung);
    	}
    	
    	String gewaessernameAlias3 = this.gewaessernameAlias3Feld.getText();
    	if("".equals(gewaessernameAlias3)) {
    		this.einleitungstelle.setGewaessernameAlias3(null);
    	} else {
    		this.einleitungstelle.setGewaessernameAlias3(gewaessernameAlias3);
    	}
    	
    	String gewaessernameNs = this.gewaessernameNsFeld.getText();
    	if("".equals(gewaessernameNs)) {
    		this.einleitungstelle.setGewaessernameNs(null);
    	} else {
    		this.einleitungstelle.setGewaessernameNs(gewaessernameNs);
    	}
    	
    	String nadiaId = this.nadiaIdFeld.getText();
    	if("".equals(nadiaId)) {
    		this.einleitungstelle.setNadiaId(null);
    	} else {
    		this.einleitungstelle.setNadiaId(nadiaId);
    	}
    	
    	Date stillgelegtAm = this.stillgelegtAmDatum.getDate();
    	this.einleitungstelle.setStillgelegtAm(stillgelegtAm);
    	
    	if(getTypIndirektCheck().isSelected()) {
    		this.einleitungstelle.setTypIndirekt(true);
    	} else {
    		this.einleitungstelle.setTypIndirekt(false);
    	}
    	
    	if(getTypIndGewDirektCheck().isSelected()) {
    		this.einleitungstelle.setTypIndGewDirekt(true);
    	} else {
    		this.einleitungstelle.setTypIndGewDirekt(false);
    	}
    	
    	if(getTypKommTrennCheck().isSelected()) {
    		this.einleitungstelle.setTypKommTrenn(true);
    	} else {
    		this.einleitungstelle.setTypKommTrenn(false);
    	}
    	
    	if(getTypPrivatTrennCheck().isSelected()) {
    		this.einleitungstelle.setTypPrivatTrenn(true);
    	} else {
    		this.einleitungstelle.setTypPrivatTrenn(false);
    	}
    	
    	if(getTypSonstigeCheck().isSelected()) {
    		this.einleitungstelle.setTypSonstige(true);
    	} else {		
    		this.einleitungstelle.setTypSonstige(false);
    	}
    	
    	if(getTypAusserortStrasseneinlCheck().isSelected()) {
    		this.einleitungstelle.setTypAusserortStrasseneinl(true);
    	} else {
    		this.einleitungstelle.setTypAusserortStrasseneinl(false);
    	}
    	
    	Double stationierungNs3 = this.stationierungNs3Feld.getDoubleValue();
    	this.einleitungstelle.setStationierungNs3(stationierungNs3);
    		
    	Double einzugsgebiet = this.einzugsgebietFeld.getDoubleValue();
    	this.einleitungstelle.setEinzugsgebiet(einzugsgebiet);
    	
    	Double stationierungSt3 = this.stationierungSt3Feld.getDoubleValue();
    	this.einleitungstelle.setStationierungSt3(stationierungSt3);
    	
    	Integer abgaberelEinl = ((IntegerField)this.abgaberelEinlFeld).getIntValue();
    	this.einleitungstelle.setAbgaberelEinl(abgaberelEinl);
    	
    	Integer e32 = ((IntegerField)this.e32Feld).getIntValue();
    	this.einleitungstelle.setE32(e32);
    	
    	Integer n32 = ((IntegerField)this.n32Feld).getIntValue();
    	this.einleitungstelle.setN32(n32);
    	
    	Integer kanalArtOpt = ((IntegerField)this.kanalArtOptFeld).getIntValue();
    	this.einleitungstelle.setKanalArtOpt(kanalArtOpt);
    	
    	Integer stationierung3Opt = ((IntegerField)this.stationierung3OptFeld).getIntValue();
    	this.einleitungstelle.setStationierung3Opt(stationierung3Opt);
    	
    	Integer schutzzoneOpt = ((IntegerField)this.schutzzoneOptFeld).getIntValue();
    	this.einleitungstelle.setSchutzzoneOpt(schutzzoneOpt);
    	  	
    	success = this.einleitungstelle.merge();
    	if (success) {
    		log.debug("Einleitungsstelle"
    				+ this.einleitungstelle.getBasisObjekt().getBasisAdresse()
    				.getBetrname() + " gespeichert.");
    	} else {
    		log.debug("Einleitungsstelle" + this.einleitungstelle
    				+ " konnte nicht gespeichert werden!");
    	}
    	return success;
    }

    /**
     * Methode die eine neue Datenbank Einleitungsstelle erzeugt,
     * sofern diese noch nicht existiert oder das Hauptmodul neu ist.
     */
    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.einleitungstelle == null) {
        	// Neue EinleitungstellePanel erzeugen
        	this.einleitungstelle = new ElkaEinleitungsstelle();
        	//Objekt_Id setzen
        	this.einleitungstelle.setBasisObjekt(this.hauptModul.getObjekt());
        	this.einleitungstelle.merge();
        	//ElkaEinleitungsstelle.merge(this.einleitungstelle);
        	log.debug("Neue ElkaEinleitungsstelle " + this.einleitungstelle + " gespeichert.");
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
     * Get-Methode die das SchutzzoneOptFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getSchutzzoneOptFeld() {
    	if (this.schutzzoneOptFeld == null) {
    		this.schutzzoneOptFeld = new IntegerField();
    		}
    	return this.schutzzoneOptFeld;
    }
    
    /**
     * Methode die den SaveElkaEinleitungsstelleButton zurückgibt sofern er existiert,
     * ansonsten wird ein neuer erstellt und diesem einen {@link ActionListener} hinzugefügt,
     * der bei einem Klick versucht die Methode {@link saveElkaEinleitungsstelleDaten}
     * auzuführen.
     * @return {@link JButton}
     */
    private JButton getSaveElkaEinleitungsstelleButton() {
    	if (this.saveElkaEinleitungsstelleButton == null) {
    		this.saveElkaEinleitungsstelleButton = new JButton("Speichern");
    		
    		this.saveElkaEinleitungsstelleButton.addActionListener(new ActionListener() {
			@Override
    		public void actionPerformed(ActionEvent e) {
	    			enableAll(false);
	    			if(saveElkaEinleitungsstelleDaten()) {
	    				EinleitungsstellePanel.this.hauptModul.getFrame().changeStatus(
	    						"Einleitungsstelle"
	    						+ EinleitungsstellePanel.this.einleitungstelle.getId()
	    						+ " erfolgreich gespeichert.",
	    						HauptFrame.SUCCESS_COLOR);
	    			} else {
	    				EinleitungsstellePanel.this.hauptModul.getFrame().changeStatus(
	    						"Fehler beim Speichern der Einleitungsstelle!",
	    						HauptFrame.ERROR_COLOR);  				
	    			}
	    			EinleitungsstellePanel.this.hauptModul.fillForm();
    		}	
    		});		
    	}
    	return this.saveElkaEinleitungsstelleButton;
    }    
    
}