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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Anhang;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
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
    private Anhang anhang;

    // Widgets
    private TextFieldDateChooser erstellDatDatum = null;
    private JComboBox betriebsweiseBox = null;
    private JComboBox beschaffenheitBox = null;
    private JComboBox anlagenartBox = null;
    private JComboBox anhangBox = null;
    private JTextField anhangIdFeld = null;
    private JTextField herkunftFeld = null;
    private JTextField anwendungsbereichFeld = null;
    private JFormattedTextField bezeichnungFeld = null;
    private TextFieldDateChooser stillgelegtAmDatum = null;
    private JFormattedTextField abwaBeschaffOptFeld = null;
    private JFormattedTextField betriebsweiseOptFeld = null;
    private JButton saveAnfallstelleButton = null;
    
    // Daten
    private Anfallstelle anfallstelle = null;
    private Anh40Panel anhang40Tab;
    private Anh49Panel anhang49Tab;
    private Anh49AbfuhrenPanel anh49abfuhrTab;
    private Anh49DetailsPanel anh49detailTab;
    private Anh49AnalysenPanel anh49analyseTab;
    private Anh49VerwaltungsverfahrenPanel anh49VerwaltungsverfahrenTab;
    private Anh50Panel anhang50Tab;
    private Anh52Panel anhang52Tab;
    private Anh53Panel anhang53Tab;
    private Anh55Panel anhang55Tab;
    private Anh56Panel anhang56Tab;
    private BWKPanel bwkTab;

    public AnfallstellePanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Anfallstelle";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:90dlu, 5dlu, 50dlu, 150dlu, 5dlu, 20dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.appendSeparator("Anfallstelle");
        builder.append("Bezeichnung:");
        builder.append(getBezeichnungFeld(), 4);
        builder.nextLine();
        builder.append("Betriebsweise:");
        builder.append(getBetriebsweiseBox(), 4);
        builder.nextLine();
        builder.append("Abwasserbeschaffenheit:");
        builder.append(getBeschaffenheitBox(), 4);
        builder.nextLine();
        builder.append("Anhang AbwV:");
        builder.append(getAnhangBox(), 4);
        builder.nextLine();
        builder.append("Anlagenart:");
        builder.append(getAnlagenartBox(), 4);
        builder.nextLine();
        builder.append("Anwendungssbereich:");
        builder.append(getAnwendungsbereichFeld(), 4);
        builder.nextLine();
        builder.append("Erstelldatum:", getErstellDatDatum());
        builder.nextLine();
        builder.append("Stilllegedatum:", getStillgelegtAmDatum());
        builder.nextLine();
        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(getSaveAnfallstelleButton());
        builder.append(buttonBar,2);
        
        this.anhangBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Anhang  anh = (Anhang) anhangBox.getSelectedItem();
            	String anlagenart = anlagenartBox.getSelectedItem().toString();
                switchAnhangPanel(anh.getAnhangId(), anlagenart);
            }
        });
  
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
    		if(this.anfallstelle.getErstellDat() != null) {
    			getErstellDatDatum().setDate(this.anfallstelle.getErstellDat());
    		}

            String[] betriebsweise = {"-", "Chargenbetrieb", "Durchlaufbetrieb", "Kampagnebetrieb"};
            getBetriebsweiseBox().setModel(new DefaultComboBoxModel(betriebsweise));
            
            getBetriebsweiseBox().setSelectedItem(anfallstelle.getBetriebsweiseDescriptionFromId(this.anfallstelle.getBetriebsweiseOpt()));
            
            String[] beschaffenheit = {"Produktionsabwasser",  "Kühlwasser", "Sanitärwasser", 
            		"Niederschlagswasser mit Sonderbauwerk", "Niederschlagswasser ohne Sonderbauwerk", 
            		"Grubenwasser", "Fischteiche", "sonstiges Wasser"};
            getBeschaffenheitBox().setModel(new DefaultComboBoxModel(beschaffenheit));
            
            getBeschaffenheitBox().setSelectedItem(anfallstelle.getBeschaffenheitDescriptionFromId(this.anfallstelle.getAbwaBeschaffOpt()));
            
            String[] anlagenart = {"-", "Aufbereitung Medizinprodukte", "Brennwertkessel", "Blockheizkraftwerk", 
            		"Fettabscheider", "Gentechnikanlage", "Kompressorenanlage", "KWK Anlage", "Labor", 
            		"RLT Anlagen", "Schrottplatz", "Wärmetauscher"};
            getAnlagenartBox().setModel(new DefaultComboBoxModel(anlagenart));
            
            if (this.anfallstelle.getAnlagenart() != null) {
            	getAnlagenartBox().setSelectedItem(this.anfallstelle.getAnlagenart());
            }
            
            List<Anhang> anhang = Anhang.getAll();
            getAnhangBox().setModel(new DefaultComboBoxModel(anhang.toArray()));
                        
    		if(this.anfallstelle.getAnhangId() != null) {
    			getAnhangBox().setSelectedItem(Anhang.findByAnhangId(this.anfallstelle.getAnhangId()));
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
        
        Date erstellDat = this.erstellDatDatum.getDate();
        this.anfallstelle.setErstellDat(erstellDat);
        
                
        anhang = (Anhang) this.anhangBox.getSelectedItem();
        if(getAnhangBox().getSelectedItem() == null) {
        	this.anfallstelle.setAnhangId(null);
        } else {
        	this.anfallstelle.setAnhangId(anhang.getAnhangId());
        }
        
        this.anfallstelle.setAbwaBeschaffOpt(
                anfallstelle.getBeschaffenheitIdFromDescription((String) getBeschaffenheitBox().getSelectedItem()));
        
        this.anfallstelle.setBetriebsweiseOpt(
                anfallstelle.getBetriebsweiseIdFromDescription((String) getBetriebsweiseBox().getSelectedItem()));
  
        if (getAnlagenartBox().getSelectedItem() == null) {
        	this.anfallstelle.setAnlagenart(null);
        } else {
        	this.anfallstelle.setAnlagenart((String) getAnlagenartBox().getSelectedItem());
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
    		// Neue Anfallstelle erzeugen
    		this.anfallstelle = new Anfallstelle();
    		// Objekt_Id setzen
    		this.anfallstelle.setObjekt(this.hauptModul.getObjekt());
    		// Anfallstelle speichern
    		this.anfallstelle.merge();
    		log.debug("Neue Anfallstelle " + this.anfallstelle + " gespeichert.");
    	}
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
     * @return the betriebsweiseBox
     */
    private JComboBox getBetriebsweiseBox() {
        if (this.betriebsweiseBox == null) {
            this.betriebsweiseBox = new JComboBox();
        }
        return betriebsweiseBox;
    }

    /**
     * @return the beschaffenheitBox
     */
    private JComboBox getBeschaffenheitBox() {
        if (this.beschaffenheitBox == null) {
            this.beschaffenheitBox = new JComboBox();
        }
        return beschaffenheitBox;
    }

    /**
     * @return the anhangBox
     */
    private JComboBox getAnhangBox() {
        if (this.anhangBox == null) {
            this.anhangBox = new JComboBox();
        }
        return anhangBox;
    }

    /**
     * @return the anhangBox
     */
    private JComboBox getAnlagenartBox() {
        if (this.anlagenartBox == null) {
            this.anlagenartBox = new JComboBox();
            this.anlagenartBox.setEditable(false);
        }
        return anlagenartBox;
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
    		this.bezeichnungFeld = new JFormattedTextField();
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

    /**
     * Switch the panel content according to the given type string
     * @param type New panel content type
     */
	public void switchAnhangPanel(String type, String anlagenart) {
		if (type == null) {
			return;
		}


		switch ((String) type) {
		case "40":
			hauptModul.getTabbedPane().addTab(getAnh40Tab().getName(), getAnh40Tab());
			getAnh40Tab().updateForm();
			break;
		case "49":
			hauptModul.getTabbedPane().addTab(getAnh49Tab().getName(), getAnh49Tab());
			hauptModul.getTabbedPane().addTab(getAnh49DetailTab().getName(), getAnh49DetailTab());
			hauptModul.getTabbedPane().addTab(getAnh49AnalyseTab().getName(), getAnh49AnalyseTab());
			hauptModul.getTabbedPane().addTab(getAnh49VerwaltungsverfahrenTab().getName(), getAnh49VerwaltungsverfahrenTab());
			getAnh49Tab().updateForm();
			getAnh49DetailTab().updateForm();
			getAnh49AnalyseTab().updateForm();
			getAnh49VerwaltungsverfahrenTab().updateForm();
			break;
		case "50":
			hauptModul.getTabbedPane().addTab(getAnh50Tab().getName(), getAnh50Tab());
			getAnh50Tab().updateForm();
			break;
		case "52":
			hauptModul.getTabbedPane().addTab(getAnh52Tab().getName(), getAnh52Tab());
			getAnh52Tab().updateForm();
			break;
		case "53":
			hauptModul.getTabbedPane().addTab(getAnh53Tab().getName(), getAnh53Tab());
			getAnh53Tab().updateForm();
			break;
		case "55":
			hauptModul.getTabbedPane().addTab(getAnh55Tab().getName(), getAnh55Tab());
			getAnh55Tab().updateForm();
			break;
		case "56":
			hauptModul.getTabbedPane().addTab(getAnh56Tab().getName(), getAnh56Tab());
			getAnh56Tab().updateForm();
			break;
		case "99":
			switch ((String) anlagenart) {
			case "Brennwertkessel":
				hauptModul.getTabbedPane().addTab(getBWKTab().getName(), getBWKTab());
				getBWKTab().updateForm();
			break;
			case "Blockheizkraftwerk":
				hauptModul.getTabbedPane().addTab(getBWKTab().getName(), getBWKTab());
				getBWKTab().updateForm();
			break;
			case "Fettabscheider":
				hauptModul.getTabbedPane().addTab(getAnh49AbfuhrTab().getName(), getAnh49AbfuhrTab());
				getAnh49AbfuhrTab().updateForm();
			break;
			}
		}
	}
	


    public Anh40Panel getAnh40Tab() {
        if (anhang40Tab == null) {
            anhang40Tab = new Anh40Panel(hauptModul);
            anhang40Tab.setBorder(Paddings.DIALOG);
        }
        return anhang40Tab;
    }

    public Anh49Panel getAnh49Tab() {
		if (anhang49Tab == null) {
            anhang49Tab = new Anh49Panel(hauptModul);
            anhang49Tab.setBorder(Paddings.DIALOG);
        }
        return anhang49Tab;
    }

    public Anh49AnalysenPanel getAnh49AnalyseTab() {
        if (anh49analyseTab == null) {
            anh49analyseTab = new Anh49AnalysenPanel(hauptModul);
            anh49analyseTab.setBorder(Paddings.DIALOG);
        }
        return anh49analyseTab;
    }

    public Anh49DetailsPanel getAnh49DetailTab() {
        if (anh49detailTab == null) {
            anh49detailTab = new Anh49DetailsPanel(hauptModul);
            anh49detailTab.setBorder(Paddings.DIALOG);
        }
        return anh49detailTab;
    }

    public Anh49AbfuhrenPanel getAnh49AbfuhrTab() {
        if (anh49abfuhrTab == null) {
            anh49abfuhrTab = new Anh49AbfuhrenPanel(hauptModul);
            anh49abfuhrTab.setBorder(Paddings.DIALOG);
        }
        return anh49abfuhrTab;
    }

    public Anh49VerwaltungsverfahrenPanel getAnh49VerwaltungsverfahrenTab() {
        if (anh49VerwaltungsverfahrenTab == null) {
            anh49VerwaltungsverfahrenTab =
                new Anh49VerwaltungsverfahrenPanel();
            anh49VerwaltungsverfahrenTab.setBorder(Paddings.DIALOG);
        }
        return anh49VerwaltungsverfahrenTab;
    }

    public Anh50Panel getAnh50Tab() {
        if (anhang50Tab == null) {
            anhang50Tab = new Anh50Panel(hauptModul);
            anhang50Tab.setBorder(Paddings.DIALOG);
        }
        return anhang50Tab;
    }

    public Anh52Panel getAnh52Tab() {
        if (anhang52Tab == null) {
            anhang52Tab = new Anh52Panel(hauptModul);
            anhang52Tab.setBorder(Paddings.DIALOG);
        }
        return anhang52Tab;
    }

    public Anh53Panel getAnh53Tab() {
        if (anhang53Tab == null) {
            anhang53Tab = new Anh53Panel(hauptModul);
            anhang53Tab.setBorder(Paddings.DIALOG);
        }
        return anhang53Tab;
    }

    public Anh55Panel getAnh55Tab() {
        if (anhang55Tab == null) {
            anhang55Tab = new Anh55Panel(hauptModul);
            anhang55Tab.setBorder(Paddings.DIALOG);
        }
        return anhang55Tab;
    }

    public Anh56Panel getAnh56Tab() {
        if (anhang56Tab == null) {
            anhang56Tab = new Anh56Panel(hauptModul);
            anhang56Tab.setBorder(Paddings.DIALOG);
        }
        return anhang56Tab;
    }

    public BWKPanel getBWKTab() {
        if (bwkTab == null) {
        	bwkTab = new BWKPanel(hauptModul);
        	bwkTab.setBorder(Paddings.DIALOG);
        }
        return bwkTab;
    }


}
