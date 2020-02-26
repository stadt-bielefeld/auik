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

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Anhang;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.AnhBwkModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
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
    private String anlagenart;

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
    private JTextArea bemerkungenArea = null;
    private JButton saveAnfallstelleButton = null;
    
    // Daten
    private Anfallstelle anfallstelle = null;
    private Anh40Panel anhang40Tab;
    private Anh49Panel anhang49Tab;
    private Anh49AbfuhrenPanel anh49abfuhrTab;
    private Anh49AnalysenPanel anh49analyseTab;
    private Anh50Panel anhang50Tab;
    private Anh52Panel anhang52Tab;
    private Anh55Panel anhang55Tab;
    private Anh56Panel anhang56Tab;
    private BWKPanel bwkTab;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

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
        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 6);
        builder.nextLine();
        
        builder.appendSeparator("Verknüpfte Objekte");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane objektverknuepfungScroller = new JScrollPane(
            getObjektverknuepungTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 6);
        builder.nextLine();
        
        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
        		getSelectObjektButton(), getSaveAnfallstelleButton());
        builder.append(buttonBar,6);
        
        this.anhangBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Anhang  anh = (Anhang) anhangBox.getSelectedItem();
            	anlagenart = anlagenartBox.getSelectedItem().toString();
                switchAnhangPanel(anh.getAnhangId(), anlagenart);
            }
        });
        
        this.anlagenartBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Anhang  anh = (Anhang) anhangBox.getSelectedItem();
            	anlagenart = anlagenartBox.getSelectedItem().toString();
//                switchAnhangPanel(anh.getAnhangId(), anlagenart);
            }
        });
  
    }

    /**
     * Methode verknüpft das lokal erstellte Objekt
     * mit der Anfallstelle aus der Datenbank.
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
            
            String[] arten = {"-", "Aufbereitung Medizinprodukte", "Brennwertkessel", "Blockheizkraftwerk", 
            		"Fettabscheider", "Gentechnikanlage", "Kompressorenanlage", "KWK Anlage", "Labor", 
            		"RLT Anlagen", "Schrottplatz", "Wärmetauscher"};
            getAnlagenartBox().setModel(new DefaultComboBoxModel(arten));
            
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
    		
    		if(this.anfallstelle.getBemerkungen() != null) {
    			getBemerkungenArea().setText(this.anfallstelle.getBemerkungen());
    		}
    		
    		if(this.anfallstelle.getBetriebsweiseOpt() != null) {
    			getBetriebsweiseOptFeld().setText(this.anfallstelle.getBetriebsweiseOpt().toString());
    		}

            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());

        	Anhang  anh = (Anhang) anhangBox.getSelectedItem();
        	anlagenart = anlagenartBox.getSelectedItem().toString();
            switchAnhangPanel(anh.getAnhangId(), anlagenart);

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
		getBemerkungenArea().setText(null);

		if (anfallstelle != null) {
			switch (anfallstelle.getAnhangId()) {
			case "40":
				getAnh40Tab().clearForm();
				break;
			case "49":
				getAnh49Tab().clearForm();
				getAnh49AnalyseTab().clearForm();
				break;
			case "50":
				getAnh50Tab().clearForm();
				break;
			case "52":
				getAnh52Tab().clearForm();
				break;
			case "55":
				getAnh55Tab().clearForm();
				break;
			case "56":
				getAnh56Tab().clearForm();
				break;
			case "99":
				if (anlagenart != null) {
					switch ((String) anlagenart) {
					case "Brennwertkessel":
						getBWKTab().clearForm();
						break;
					case "BHKW":
						getBWKTab().clearForm();
						break;
					case "Fettabscheider":
						getAnh49Tab().clearForm();
						getAnh49AbfuhrTab().clearForm();
						break;
					}
				}
			default:
				log.debug("Unknown Anfallstelle: " + anfallstelle);
			}
		}
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
        getBemerkungenArea().setEnabled(enabled);
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
                Anfallstelle.getBeschaffenheitIdFromDescription((String) getBeschaffenheitBox().getSelectedItem()));
        
        this.anfallstelle.setBetriebsweiseOpt(
                Anfallstelle.getBetriebsweiseIdFromDescription((String) getBetriebsweiseBox().getSelectedItem()));
  
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
        
        String bemerkungen = this.bemerkungenArea.getText();
        if("".equals(bemerkungen)) {
        	this.anfallstelle.setBemerkungen(null);
        } else {
        	this.anfallstelle.setBemerkungen(bemerkungen);
        }

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
    		this.anfallstelle.setAnhangId("99");
    		this.anfallstelle.setErstellDat(new Date());
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
     * @return the anlagenartBox
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
     * Get-Methode die das bemerkungenFeld des Panels zurückgibt:
     * @return {@link JTextField}
     */
    private JTextArea getBemerkungenArea() {
    	if (this.bemerkungenArea == null) {
    		this.bemerkungenArea = new LimitedTextArea(150);
            this.bemerkungenArea.setLineWrap(true);
            this.bemerkungenArea.setWrapStyleWord(true);
    	}
    	return this.bemerkungenArea;
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
                                Objektverknuepfung obj =
                                	AnfallstellePanel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != AnfallstellePanel.this.hauptModul
                                    .getObjekt().getId().intValue())
                                	AnfallstellePanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                else
                                	AnfallstellePanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                	AnfallstellePanel.this.hauptModul.getManager()
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

    private Action getVerknuepfungLoeschAction() {
        if (this.verknuepfungLoeschAction == null) {
            this.verknuepfungLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 2886609709202711593L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung =
                        		AnfallstellePanel.this.objektVerknuepfungModel.getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (AnfallstellePanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                            	AnfallstellePanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                            	AnfallstellePanel.this.hauptModul.getFrame()
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
                    		AnfallstellePanel.this.hauptModul.getFrame(),
                    		AnfallstellePanel.this.anfallstelle.getObjekt(),
                    		AnfallstellePanel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }

	/**
	 * Switch the panel content according to the given type and anlagenart string
	 * 
	 * @param type       New panel content type
	 * @param anlagenart New panel content anlagenart
	 */
	public void switchAnhangPanel(String type, String anlagenart) {
		if (type == null) {
			return;
		}

		int i = hauptModul.getTabbedPane().getTabCount();
		if (i > 5) {
			hauptModul.getTabbedPane().removeTabAt(5);
		}
		if (i > 4) {
			hauptModul.getTabbedPane().removeTabAt(4);
		}
		if (i > 3) {
			hauptModul.getTabbedPane().removeTabAt(3);
		}

		this.anlagenartBox.enable(false);

		switch ((String) type) {
		case "40":
			hauptModul.getTabbedPane().addTab(getAnh40Tab().getName(), getAnh40Tab());
			if (anfallstelle.getAnh40Fachdatens().size() > 0) {
				getAnh40Tab().enableAll(true);
				getAnh40Tab().updateForm(anfallstelle);
			} else {
				anfallstelle.setAnhangId("40");
				getAnh40Tab().clearForm();
				getAnh40Tab().completeObjekt(anfallstelle);
				hauptModul.getTabbedPane().setSelectedIndex(3);
			}
			break;
		case "49":
			hauptModul.getTabbedPane().addTab(getAnh49Tab().getName(), getAnh49Tab());
			hauptModul.getTabbedPane().addTab(getAnh49AnalyseTab().getName(), getAnh49AnalyseTab());
			if (anfallstelle.getAnh49Fachdatens().size() > 0) {
				getAnh49Tab().enableAll(true);
				getAnh49Tab().updateForm(anfallstelle);
				getAnh49AnalyseTab().clearForm();
				getAnh49AnalyseTab().updateForm(getAnh49Tab().getFachdaten());
			} else {
				anfallstelle.setAnhangId("49");
				getAnh49Tab().clearForm();
				getAnh49Tab().completeObjekt(anfallstelle);
				getAnh49AnalyseTab().clearForm();
				hauptModul.getTabbedPane().setSelectedIndex(3);
			}
			break;
		case "50":
			hauptModul.getTabbedPane().addTab(getAnh50Tab().getName(), getAnh50Tab());
			if (anfallstelle.getAnh50Fachdatens().size() > 0) {
				getAnh50Tab().enableAll(true);
				getAnh50Tab().updateForm(anfallstelle);
			} else {
				anfallstelle.setAnhangId("50");
				getAnh50Tab().clearForm();
				getAnh50Tab().completeObjekt(anfallstelle);
				hauptModul.getTabbedPane().setSelectedIndex(3);
			}
			break;
		case "52":
			hauptModul.getTabbedPane().addTab(getAnh52Tab().getName(), getAnh52Tab());
			if (anfallstelle.getAnh52Fachdatens().size() > 0) {
				getAnh52Tab().enableAll(true);
				getAnh52Tab().updateForm(anfallstelle);
			} else {
				anfallstelle.setAnhangId("52");
				getAnh52Tab().clearForm();
				getAnh52Tab().completeObjekt(anfallstelle);
				hauptModul.getTabbedPane().setSelectedIndex(3);
			}
			break;
		case "55":
			hauptModul.getTabbedPane().addTab(getAnh55Tab().getName(), getAnh55Tab());
			if (anfallstelle.getAnh55Fachdatens().size() > 0) {
				getAnh55Tab().enableAll(true);
				getAnh55Tab().updateForm(anfallstelle);
			} else {
				anfallstelle.setAnhangId("55");
				getAnh55Tab().clearForm();
				getAnh55Tab().completeObjekt(anfallstelle);
				hauptModul.getTabbedPane().setSelectedIndex(3);
			}
			break;
		case "56":
			hauptModul.getTabbedPane().addTab(getAnh56Tab().getName(), getAnh56Tab());
			if (anfallstelle.getAnh56Fachdatens().size() > 0) {
				getAnh56Tab().enableAll(true);
				getAnh56Tab().updateForm(anfallstelle);
			} else {
				anfallstelle.setAnhangId("56");
				getAnh56Tab().clearForm();
				getAnh56Tab().completeObjekt(anfallstelle);
				hauptModul.getTabbedPane().setSelectedIndex(3);
			}
			break;
		case "99":
			this.anlagenartBox.enable(true);
			switch ((String) anlagenart) {
			case "Brennwertkessel":
				hauptModul.getTabbedPane().addTab(getBWKTab().getName(), getBWKTab());
				if (anfallstelle.getBwkFachdatens().size() > 0) {
					getBWKTab().enableAll(true);
					getBWKTab().updateForm(anfallstelle);
				} else {
					anfallstelle.setAnhangId("99");
					anfallstelle.setAnlagenart("Brennwertkessel");
					getBWKTab().clearForm();
					getBWKTab().completeObjekt(anfallstelle);
					hauptModul.getTabbedPane().setSelectedIndex(3);
				}
				break;
			case "Blockheizkraftwerk":
				hauptModul.getTabbedPane().addTab("Blockheizkraftwerk", getBWKTab());
				if (anfallstelle.getBwkFachdatens().size() > 0) {
					getBWKTab().enableAll(true);
					getBWKTab().updateForm(anfallstelle);
				} else {
					anfallstelle.setAnhangId("99");
					anfallstelle.setAnlagenart("Blockheizkraftwerk");
					getBWKTab().clearForm();
					getBWKTab().completeObjekt(anfallstelle);
					hauptModul.getTabbedPane().setSelectedIndex(3);
				}
				break;
			case "Fettabscheider":
				hauptModul.getTabbedPane().addTab("Fettabscheider", getAnh49Tab());
				hauptModul.getTabbedPane().addTab(getAnh49AbfuhrTab().getName(), getAnh49AbfuhrTab());
				if (anfallstelle.getAnh49Fachdatens().size() > 0) {
					getAnh49Tab().enableAll(true);
					getAnh49Tab().updateForm(anfallstelle);
					getAnh49AbfuhrTab().clearForm();
					getAnh49AbfuhrTab().updateForm(getAnh49Tab().getFachdaten());
				} else {
					anfallstelle.setAnhangId("99");
					anfallstelle.setAnlagenart("Fettabscheider");
					getAnh49Tab().clearForm();
					getAnh49Tab().enableAll(true);
					getAnh49Tab().completeObjekt(anfallstelle);
					hauptModul.getTabbedPane().setSelectedIndex(3);
				}
				break;
			case "Abscheider":
				hauptModul.getTabbedPane().addTab("Abscheider", getAnh49Tab());
				hauptModul.getTabbedPane().addTab(getAnh49AbfuhrTab().getName(), getAnh49AbfuhrTab());
				if (anfallstelle.getAnh49Fachdatens().size() > 0) {
					getAnh49Tab().fetchFormData();
					getAnh49Tab().enableAll(true);
					getAnh49Tab().updateForm(anfallstelle);
					getAnh49AbfuhrTab().clearForm();
					getAnh49AbfuhrTab().updateForm(getAnh49Tab().getFachdaten());
				} else {
					anfallstelle.setAnhangId("99");
					anfallstelle.setAnlagenart("Abscheider");
					getBWKTab().clearForm();
					getAnh49Tab().completeObjekt(anfallstelle);
					hauptModul.getTabbedPane().setSelectedIndex(3);
				}
				break;
			}
		}
	}
	


    public Anh40Panel getAnh40Tab() {
        if (anhang40Tab == null) {
            anhang40Tab = new Anh40Panel(hauptModul, anfallstelle);
            anhang40Tab.setBorder(Paddings.DIALOG);
        }
        return anhang40Tab;
    }

    public Anh49Panel getAnh49Tab() {
		if (anhang49Tab == null) {
            anhang49Tab = new Anh49Panel(hauptModul, anfallstelle);
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

    public Anh49AbfuhrenPanel getAnh49AbfuhrTab() {
        if (anh49abfuhrTab == null) {
            anh49abfuhrTab = new Anh49AbfuhrenPanel(hauptModul);
            anh49abfuhrTab.setBorder(Paddings.DIALOG);
        }
        return anh49abfuhrTab;
    }

    public Anh50Panel getAnh50Tab() {
        if (anhang50Tab == null) {
            anhang50Tab = new Anh50Panel(hauptModul, anfallstelle);
            anhang50Tab.setBorder(Paddings.DIALOG);
        }
        return anhang50Tab;
    }

    public Anh52Panel getAnh52Tab() {
        if (anhang52Tab == null) {
            anhang52Tab = new Anh52Panel(hauptModul, anfallstelle);
            anhang52Tab.setBorder(Paddings.DIALOG);
        }
        return anhang52Tab;
    }

    public Anh55Panel getAnh55Tab() {
        if (anhang55Tab == null) {
            anhang55Tab = new Anh55Panel(hauptModul, anfallstelle);
            anhang55Tab.setBorder(Paddings.DIALOG);
        }
        return anhang55Tab;
    }

    public Anh56Panel getAnh56Tab() {
        if (anhang56Tab == null) {
            anhang56Tab = new Anh56Panel(hauptModul, anfallstelle);
            anhang56Tab.setBorder(Paddings.DIALOG);
        }
        return anhang56Tab;
    }

    public BWKPanel getBWKTab() {
        if (bwkTab == null) {
        	bwkTab = new BWKPanel(hauptModul, anfallstelle);
        	bwkTab.setBorder(Paddings.DIALOG);
        }
        return bwkTab;
    }


}
