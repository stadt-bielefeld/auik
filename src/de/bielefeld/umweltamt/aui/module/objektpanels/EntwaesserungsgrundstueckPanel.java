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
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
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
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Referenz;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Entwaesserungsgrundstueck;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "EntwaesserungsgrundstueckPanel"-Tab des ObjektBearbeiten-Moduls
 * @author Gerd Genuit
 * @date 15.01.2018
 */
public class EntwaesserungsgrundstueckPanel extends JPanel {
    private static final long serialVersionUID = 7997458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private TextFieldDateChooser erstellDatDatum = null;
    private JTextField bezeichnungFeld = null;
    // Daten
    private Entwaesserungsgrundstueck  entwaesserungsgrundstueck = null;
    private Referenz referenz = null;
    private JButton saveEntwaesserungsgrundstueckButton = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public EntwaesserungsgrundstueckPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Einleitungsstelle";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
        		"r:80dlu, 5dlu, 80dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.appendSeparator("ELKA");
        builder.append("Erstellung:", getErstellDatDatum());
        builder.append("Bezeichnung:", getBezeichnungFeld());
        builder.nextLine();
        JScrollPane objektverknuepfungScroller = new JScrollPane(
            getObjektverknuepungTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 7);
        builder.nextLine();
        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
        		getSelectObjektButton(), getSaveEntwaesserungsgrundstueckButton());
        builder.append(buttonBar, 7);

    }
    
	/**
     * Methode verknüpft das lokal erstelle Objekt einleitungstelle
     * mit der ElkaSonderbauwerk der Datenbank und holt sich die Klaeranlagen
     * aus der Datenbank
     * @throws RuntimeException
     */
    public void fetchFormData() throws RuntimeException {
    	this.entwaesserungsgrundstueck = Entwaesserungsgrundstueck.findByObjektId(
    			this.hauptModul.getObjekt().getId());
    	log.debug("Entwaesserungsgrundstueck aus DB geholt: " + this.entwaesserungsgrundstueck);
    	

    }
    
    /**
     * Methode setzt die Attribute des Entwaesserungsgrundstueckes aus der Datenbank 
     * auf die der lokalen Einleitungstelle und die Verknüpfung mit
     * der Kläranlage über die Tabelle referenz
     * @throws RuntimeException
     */
    public void updateForm() throws RuntimeException {
    	if (this.entwaesserungsgrundstueck != null) {
    		if (this.entwaesserungsgrundstueck.getErstellDat() != null) {
    			getErstellDatDatum().setDate(this.entwaesserungsgrundstueck.getErstellDat());
    		}

            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
    	}
    }
  
    /**
     * Methode die alle Eingabefelder des Panels auf den Standard zurücksetzt.
     */
    public void clearForm() {
    	getErstellDatDatum().setDate(null);
    	getBezeichnungFeld().setText(null);
    	
    }
    
    /**
     * Methode die je nach Eingabewert alles Eingabefelder des Panels aktiviert oder
     * deaktiviert.
     * @param enabled
     */
    public void enableAll(boolean enabled) {
    	getErstellDatDatum().setEnabled(enabled);
    	getBezeichnungFeld().setEnabled(enabled);
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
    private boolean saveEntwaesserungsgrundstueckDaten() {
    	boolean success;
    	
    	this.entwaesserungsgrundstueck.setAktualDat(new Date());
    	
    	Date erstellDat = this.erstellDatDatum.getDate();
    	this.entwaesserungsgrundstueck.setErstellDat(erstellDat);
    
    	success = this.entwaesserungsgrundstueck.merge();
    	if (success) {
    		log.debug("Entwaesserungsgrundstueck"
    				+ this.entwaesserungsgrundstueck.getObjekt().getBetreiberid()
    				.getName() + " gespeichert.");
    	} else {
    		log.debug("Entwaesserungsgrundstueck" + this.entwaesserungsgrundstueck
    				+ " konnte nicht gespeichert werden!");
    	}
    	return success;
    }

    /**
     * Methode die eine neue Datenbank Entwaesserungsgrundstueck erzeugt,
     * sofern diese noch nicht existiert oder das Hauptmodul neu ist.
     */
    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.entwaesserungsgrundstueck == null) {
        	// Neue EinleitungstellePanel erzeugen
        	this.entwaesserungsgrundstueck = new Entwaesserungsgrundstueck();
        	//Objekt_Id setzen
        	this.entwaesserungsgrundstueck.setObjekt(this.hauptModul.getObjekt());
        	this.entwaesserungsgrundstueck.merge();
        	//ElkaEinleitungsstelle.merge(this.einleitungstelle);
        	log.debug("Neues Entwaesserungsgrundstueck " + this.entwaesserungsgrundstueck + " gespeichert.");
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
	                            Objektverknuepfung obj = EntwaesserungsgrundstueckPanel.this.objektVerknuepfungModel
	                                .getRow(row);
	                            if (obj.getObjektByIstVerknuepftMit()
	                                .getId().intValue() != EntwaesserungsgrundstueckPanel.this.hauptModul
	                                .getObjekt().getId().intValue())
	                            	EntwaesserungsgrundstueckPanel.this.hauptModul
	                                    .getManager()
	                                    .getSettingsManager()
	                                    .setSetting(
	                                        "auik.imc.edit_object",
	                                        obj.getObjektByIstVerknuepftMit()
	                                            .getId().intValue(),
	                                        false);
	                            else
	                            	EntwaesserungsgrundstueckPanel.this.hauptModul
	                                    .getManager()
	                                    .getSettingsManager()
	                                    .setSetting(
	                                        "auik.imc.edit_object",
	                                        obj.getObjektByObjekt()
	                                            .getId().intValue(),
	                                        false);
	                            EntwaesserungsgrundstueckPanel.this.hauptModul.getManager()
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
     * Methode die den SaveEntwaesserungsgrundstueckButton zurückgibt sofern er existiert,
     * ansonsten wird ein neuer erstellt und diesem einen {@link ActionListener} hinzugefügt,
     * der bei einem Klick die Methoden <code>saveEntwaesserungsgrundstueckDaten</code>.
     * @see #saveEntwaesserungsgrundstueck() 
     * @return {@link JButton}
     */
    private JButton getSaveEntwaesserungsgrundstueckButton() {
    	if (this.saveEntwaesserungsgrundstueckButton == null) {
    		this.saveEntwaesserungsgrundstueckButton = new JButton("Speichern");
    		
    		this.saveEntwaesserungsgrundstueckButton.addActionListener(new ActionListener() {
			@Override
    		public void actionPerformed(ActionEvent e) {
	    			enableAll(false);
	    			String status = "";
	    			if(saveEntwaesserungsgrundstueckDaten()) {
	    			    status = "Einleitungsstelle " + 
	    			EntwaesserungsgrundstueckPanel.this.entwaesserungsgrundstueck.getNr()
	    			+ " erfolgreich gespeichert.";
	    			} else {
	    			    status = "Fehler beim Speichern der Einleitungsstelle!";				
	    			}
	    			if(status.startsWith("Sonderbauwerk")) {
	    			    EntwaesserungsgrundstueckPanel.this.hauptModul.getFrame().changeStatus(status,
	    				    HauptFrame.SUCCESS_COLOR);
	    			} else {
	    			    EntwaesserungsgrundstueckPanel.this.hauptModul.getFrame().changeStatus(status,
	    				    HauptFrame.ERROR_COLOR);
	    			}
	    			EntwaesserungsgrundstueckPanel.this.hauptModul.fillForm();
    		}	
    		});		
    	}
    	return this.saveEntwaesserungsgrundstueckButton;
    }

	private Action getVerknuepfungLoeschAction() {
	    if (this.verknuepfungLoeschAction == null) {
	        this.verknuepfungLoeschAction = new AbstractAction("Löschen") {
	
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int row = getObjektverknuepungTabelle().getSelectedRow();
	                if (row != -1
	                    && getObjektverknuepungTabelle().getEditingRow() == -1) {
	                    Objektverknuepfung verknuepfung = EntwaesserungsgrundstueckPanel.this.objektVerknuepfungModel
	                        .getRow(row);
	                    if (GUIManager.getInstance().showQuestion(
	                        "Soll die Verknüpfung wirklich gelöscht werden?\n"
	                            + "Hinweis: Die Aktion betrifft nur die "
	                            + "Verknüpfung, die Objekte bleiben erhalten "
	                            + "und können jederzeit neu verknüpft werden.",
	                        "Löschen bestätigen")) {
	                        if (EntwaesserungsgrundstueckPanel.this.objektVerknuepfungModel
	                            .removeRow(row)) {
	                        	EntwaesserungsgrundstueckPanel.this.hauptModul.getFrame()
	                                .changeStatus("Objekt gelöscht.",
	                                    HauptFrame.SUCCESS_COLOR);
	                            log.debug("Objekt " + verknuepfung.getId()
	                                + " wurde gelöscht!");
	                        } else {
	                        	EntwaesserungsgrundstueckPanel.this.hauptModul.getFrame()
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
	                		EntwaesserungsgrundstueckPanel.this.hauptModul.getFrame(),
	                		EntwaesserungsgrundstueckPanel.this.entwaesserungsgrundstueck.getObjekt(),
	                		EntwaesserungsgrundstueckPanel.this.objektVerknuepfungModel);
	                chooser.setVisible(true);
	            }
	        });
	    }
	    return this.selectObjektButton;
	}    
    
}