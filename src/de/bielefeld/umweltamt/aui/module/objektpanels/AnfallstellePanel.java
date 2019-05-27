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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Anfallstelle"-Tab des ObjektBearbeiten-Moduls
 * @author Tobias Kaps
 * @date 29.09.2017
 */
public class AnfallstellePanel extends JPanel {
    private static final long serialVersionUID = 7997458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JFormattedTextField seqIdFeld = null;
    private TextFieldDateChooser erstellDatDatum = null;
    private JTextField anhangIdFeld = null;
    private JTextField herkunftFeld = null;
    private JTextField anwendungsbereichFeld = null;
    private JTextField bezeichnungFeld = null;
    private TextFieldDateChooser stillgelegtAmDatum = null;
    private JFormattedTextField abwaBeschaffOptFeld = null;
    private JFormattedTextField betriebsweiseOptFeld = null;
    private JButton saveAnfallstelleButton = null;
    
    // Daten
    private Anfallstelle anfallstelle = null;

    public AnfallstellePanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Anfallstelle";
        this.hauptModul = hauptModul;

        PanelBuilder content = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                0, 0, 5, 0);

        content.addComponent(getSeqIdFeld(), "SeqId:", true);
        content.addComponent(getErstellDatDatum(), "erstellDat:", true);
        content.addComponent(getAnhangIdFeld(), "anhangId", true);
        content.addComponent(getHerkunftFeld(), "Herkunft:", true);
        content.addComponent(getAnwendungsbereichFeld(), "Anwendungsbereich:", true);
        content.addComponent(getBezeichnungFeld(), "Bezeichung:", true);
        content.addComponent(getStillgelegtAmDatum(), "Still gelegt am:", true);
        content.addComponent(getAbwaBeschaffOptFeld(), "abwaBeschaffOpt", true);
        content.addComponent(getBetriebsweiseOptFeld(), "betriebsweiseOpt", true);
        content.addComponent(PanelBuilder.buildRightAlignedButtonToolbar(getSaveAnfallstelleButton()), true);
        content.fillColumn();
        content.setPreferedSize(500, 500);

        PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, false, true, 0, 0, 1, 1,
                0, 0, 5, 0);

        builder.setEmptyBorder(15);
        builder.addSeparator("Anfallstelle", true);
        builder.addComponent(content.getPanel(), true);
        builder.fillRow(true);
        builder.fillColumn();

        this.setLayout(new BorderLayout());
        this.add(builder.getPanel());
    }

    /**
     * Methode verknüpft das lokal erstellte Objekt Anfallstelle
     * mit der ElkaAnfallstelle aus der Datenbank.
     * @throws RuntimeException
     */
    public void fetchFormData() throws RuntimeException {
    	this.anfallstelle = Anfallstelle.findByObjektId(
    			this.hauptModul.getObjekt().getId());
    	log.debug("Anfallstelle aus DB geholt: " + this.anfallstelle);
    }

    /**
     * Methode setzt die Attribute der Anfallstelle aus der Datenbank
     * auf die der lokalen Anfallstelle.
     * @throws RuntimeException
     */
    public void updateForm() throws RuntimeException {
    	if(this.anfallstelle != null) {
    		if(this.anfallstelle.getSeqId() != null) {
    			getSeqIdFeld().setText(this.anfallstelle.getSeqId().toString());
    		}
    		
    		if(this.anfallstelle.getErstellDat() != null) {
    			getErstellDatDatum().setDate(this.anfallstelle.getErstellDat());
    		}
    		
    		if(this.anfallstelle.getAnhangId() != null) {
    			getAnhangIdFeld().setText(this.anfallstelle.getAnhangId());
    		}
    		
    		if(this.anfallstelle.getHerkunft() != null) {
    			getHerkunftFeld().setText(this.anfallstelle.getHerkunft());
    		}
    		
    		if(this.anfallstelle.getAnwendungsbereich() != null) {
    			getAnwendungsbereichFeld().setText(this.anfallstelle.getAnwendungsbereich());
    		}
    		
    		if(this.anfallstelle.getBezeichnung() != null) {
    			getBezeichnungFeld().setText(this.anfallstelle.getBezeichnung());
    		}
    		
    		if(this.anfallstelle.getStillgelegtAm() != null) {
    			getStillgelegtAmDatum().setDate(this.anfallstelle.getStillgelegtAm());
    		}
    		
    		if(this.anfallstelle.getAbwaBeschaffOpt() != null) {
    			getAbwaBeschaffOptFeld().setText(this.anfallstelle.getAbwaBeschaffOpt().toString());
    		}
    		
    		if(this.anfallstelle.getBetriebsweiseOpt() != null) {
    			getBetriebsweiseOptFeld().setText(this.anfallstelle.getBetriebsweiseOpt().toString());
    		}
    	}
    }

    /**
     * Methode die alle Eingabefelder des Panels auf den Standard zurücksetzt.
     */
    public void clearForm() {
    	getSeqIdFeld().setText(null);
    	getErstellDatDatum().setDate(null);
    	getAnhangIdFeld().setText(null);
    	getHerkunftFeld().setText(null);
    	getAnwendungsbereichFeld().setText(null);
    	getBezeichnungFeld().setText(null);
    	getStillgelegtAmDatum().setDate(null);
    	getAbwaBeschaffOptFeld().setText(null);
    	getBetriebsweiseOptFeld().setText(null);
    }

    /**
     * Methode die je nach Eingabewert alle Eingabefelder des Panels aktiviert
     * oder deaktiviert.
     * @param enabled
     */
    public void enableAll(boolean enabled) {
        getSeqIdFeld().setEnabled(enabled);
        getErstellDatDatum().setEnabled(enabled);
        getAnhangIdFeld().setEnabled(enabled);
        getHerkunftFeld().setEnabled(enabled);
        getAnwendungsbereichFeld().setEnabled(enabled);
        getBezeichnungFeld().setEnabled(enabled);
        getStillgelegtAmDatum().setEnabled(enabled);
        getAbwaBeschaffOptFeld().setEnabled(enabled);
        getBetriebsweiseOptFeld().setEnabled(enabled);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private boolean saveAnfallstelleDaten() {
        boolean success;
        
        this.anfallstelle.setAktualDat(new Date());
        
        Integer seqId = ((IntegerField)this.seqIdFeld).getIntValue();
        this.anfallstelle.setSeqId(seqId);
        
        Date erstellDat = this.erstellDatDatum.getDate();
        this.anfallstelle.setErstellDat(erstellDat);
        
        String anhangId = this.anhangIdFeld.getText();
        if("".equals(anhangId)) {
        	this.anfallstelle.setAnhangId(null);
        } else {
        	this.anfallstelle.setAnhangId(anhangId);
        }
        
        String herkunft = this.herkunftFeld.getText();
        if("".equals(herkunft)) {
        	this.anfallstelle.setHerkunft(null);
        } else {
        	this.anfallstelle.setHerkunft(herkunft);
        }
        
        String anwendungsbereich = this.anwendungsbereichFeld.getText();
        if("".equals(anwendungsbereich)) {
        	this.anfallstelle.setAnwendungsbereich(null);
        } else {
        	this.anfallstelle.setAnwendungsbereich(anwendungsbereich);
        }
        
        String bezeichnung = this.bezeichnungFeld.getText();
        if("".equals(bezeichnung)) {
        	this.anfallstelle.setBezeichnung(null);
        } else {
        	this.anfallstelle.setBezeichnung(bezeichnung);
        }
        
        Date stillgelegtAm = this.stillgelegtAmDatum.getDate();
        this.anfallstelle.setStillgelegtAm(stillgelegtAm);
        
        Integer abwaBeschaffOpt = ((IntegerField)this.abwaBeschaffOptFeld).getIntValue();
        this.anfallstelle.setAbwaBeschaffOpt(abwaBeschaffOpt);
        
        Integer betriebsweiseOpt = ((IntegerField)this.betriebsweiseOptFeld).getIntValue();
        this.anfallstelle.setBetriebsweiseOpt(betriebsweiseOpt);

        success = this.anfallstelle.merge();
        
        if(success) {
        	log.debug("Anfallstelle "
                  + this.anfallstelle.getObjekt().getBetreiberid()
                      .getBetrname() + " gespeichert.");     	
        } else {
        	log.debug("Anfallstelle " + this.anfallstelle
        			+ " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
    	if (this.hauptModul.isNew() || this.anfallstelle == null) {
    		// Neue Einleitungsstelle erzeugen
    		this.anfallstelle = new Anfallstelle();
    		// Objekt_Id setzen
    		this.anfallstelle.setObjekt(this.hauptModul.getObjekt());
    		// Einleitungsstelle speichern
    		this.anfallstelle.merge();
    		log.debug("Neue Anfallstelle " + this.anfallstelle + " gespeichert.");
    	}
    }
    
    /**
     * Get-Methode die das seqIdFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getSeqIdFeld() {
    	if (this.seqIdFeld == null) {
    		this.seqIdFeld = new IntegerField();
    	}
    	return this.seqIdFeld;
    }
    
    /**
     * Get-Methode die das erstellDatDatum des Panels zurückgibt:
     * @return {@link TextFieldDateChooser}
     */
    private TextFieldDateChooser getErstellDatDatum() {
    	if (this.erstellDatDatum == null) {
    		this.erstellDatDatum = new TextFieldDateChooser();
    	}
    	return this.erstellDatDatum;
    }
    
    /**
     * Get-Methode die das anhangIdFeld des Panels zurückgibt:
     * @return {@link JTextField}
     */
    private JTextField getAnhangIdFeld() {
    	if (this.anhangIdFeld == null) {
    		this.anhangIdFeld = new LimitedTextField(50);
    	}
    	return this.anhangIdFeld;
    }
    
    /**
     * Get-Methode die das herkunftFeld des Panels zurückgibt:
     * @return {@link JTextField}
     */
    private JTextField getHerkunftFeld() {
    	if (this.herkunftFeld == null) {
    		this.herkunftFeld = new LimitedTextField(50);
    	}
    	return this.herkunftFeld;
    }
    
    private JTextField getAnwendungsbereichFeld() {
    	if (this.anwendungsbereichFeld == null) {
    		this.anwendungsbereichFeld = new LimitedTextField(50);
    	}
    	return this.anwendungsbereichFeld;
    }
    
    /**
     * Get-Methode die das bezeichnungFeld des Panels zurückgibt:
     * @return {@link JTextField}
     */
    private JTextField getBezeichnungFeld() {
    	if (this.bezeichnungFeld == null) {
    		this.bezeichnungFeld = new LimitedTextField(50);
    	}
    	return this.bezeichnungFeld;
    }
    
    /**
     * Get-Methode die das stillgelegtAmDatum des Panels zurückgibt:
     * @return {@link TextFieldDateChooser}
     */
    private TextFieldDateChooser getStillgelegtAmDatum() {
    	if (this.stillgelegtAmDatum == null) {
    		this.stillgelegtAmDatum = new TextFieldDateChooser();
    	}
    	return this.stillgelegtAmDatum;
    }
    
    /**
     * Get-Methode die das abwaBeschaffOptFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getAbwaBeschaffOptFeld() {
    	if (this.abwaBeschaffOptFeld == null) {
    		this.abwaBeschaffOptFeld = new IntegerField();
    	}
    	return this.abwaBeschaffOptFeld;
    }
    
    /**
     * Get-Methode die das betriebsweiseOptFeld des Panels zurückgibt:
     * @return {@link JFormattedTextField}
     */
    private JFormattedTextField getBetriebsweiseOptFeld() {
    	if (this.betriebsweiseOptFeld == null) {
    		this.betriebsweiseOptFeld = new IntegerField();
    	}
    	return this.betriebsweiseOptFeld;
    }
    
    /**
     * Methode die den SaveAnfallstelleButton zurückgibt sofern er existiert,
     * ansonsten wird ein neuer erstellt und diesem einen {@link ActionListener} hinzugefügt,
     * der bei einem Klick versucht die Methode {@link saveAnfallstelleDaten}
     * auzuführen.
     * @return {@link JButton}
     */
    private JButton getSaveAnfallstelleButton() {
    	if (this.saveAnfallstelleButton == null) {
    		this.saveAnfallstelleButton = new JButton("Speichern");
    		
    		this.saveAnfallstelleButton.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				enableAll(false);;
    				if(saveAnfallstelleDaten()) {
    					AnfallstellePanel.this.hauptModul.getFrame().changeStatus(
    							"Anfallstelle "
    							+ AnfallstellePanel.this.anfallstelle.getId()
    							+ " erfolgreich gespeichert.",
    							HauptFrame.SUCCESS_COLOR);
    				} else {
    					AnfallstellePanel.this.hauptModul.getFrame().changeStatus(
    							"Fehler beim Speichern der Anfallstelle!",
    							HauptFrame.ERROR_COLOR);
    				}
    				AnfallstellePanel.this.hauptModul.fillForm();
    			}
    		});
    	}
    	return this.saveAnfallstelleButton;
    }

}
