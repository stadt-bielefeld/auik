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

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Einleitungsstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaGewkennz;
import de.bielefeld.umweltamt.aui.mappings.elka.Referenz;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.GermanDouble;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.MyKeySelectionManager;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Einleitungsstelle"-Tab des BasisObjektBearbeiten-Moduls
 * 
 * @author Tobias Kaps
 * @date 15.01.2018
 */
public class EinleitungsstellePanel extends JPanel {
	private static final long serialVersionUID = 7997458251785488488L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private String name;
	private BasisObjektBearbeiten hauptModul;

	// Tabs
//	private GewaesserdatenPanel gewaesserdatenTab;

	// Allgemeine Felder
	private JTextField bezeichnungFeld = null;
	private JComboBox einleitungsartBox = null;
	private TextFieldDateChooser erstellDatDatum = null;
	private TextFieldDateChooser stillgelegtAmDatum = null;
	private JTextArea bemerkungenArea = null;

	// Indirekteinleitung
	private Klaeranlage[] klaeranlagen = null;
	private JComboBox<Klaeranlage> klaeranlageBox = null;
	private JComboBox kanalArtOptBox = null;

	// Direkteinleitung

	private JComboBox abgaberelEinlBox = null;
	private JTextField abwAgEinlFeld = null;


	private JButton saveElkaEinleitungsstelleButton = null;

	// Daten
	private GewaesserdatenPanel gewaesserTab;

	// Labels:

	private JLabel bezeichnungLb = new JLabel("Bezeichnung");
	private JLabel einlArtLb = new JLabel("Einleitungsart");
	private JLabel erstellDatLb = new JLabel("Erstelldatum");
	private JLabel stillDatLb = new JLabel("Stillgelegt am");
	private JLabel klaeranlageLb = new JLabel("Kläranlage");
	private JLabel kanalArtLb = new JLabel("Kanalart");
	private JLabel abgabeLb = new JLabel("Abgabepflicht");
	private JLabel abwAgLb = new JLabel("AbwAG-Einl.-Stelle");

	// Daten
	private Einleitungsstelle einleitungsstelle = null;
	private Referenz referenz = null;

	// Objektverknuepfer
	private ObjektVerknuepfungModel objektVerknuepfungModel;
	private JTable objektverknuepfungTabelle = null;
	private JButton selectObjektButton = null;
	private Action verknuepfungLoeschAction;
	private JPopupMenu verknuepfungPopup;

	public EinleitungsstellePanel(BasisObjektBearbeiten hauptModul) {
		this.name = "Einleitungsstelle";
		this.hauptModul = hauptModul;

		FormLayout layout = new FormLayout("r:80dlu, 5dlu, 80dlu, 100dlu, 40dlu", // Spalten
				"");

		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.nextLine();
		builder.appendRow("fill:5dlu");
		builder.appendSeparator("Einleitungsstelle");

		builder.nextLine();
		builder.append(bezeichnungLb);
		builder.append(getBezeichnungFeld(), 2);

		builder.nextLine();
		builder.append(einlArtLb);
		builder.append(getEinleitungsartBox(), 2);

		builder.nextLine();
		builder.append(erstellDatLb);
		builder.append(getErstellDatDatum());

		builder.nextLine();
		builder.append(stillDatLb);
		builder.append(getStillgelegtAmDatum());

		builder.nextLine();
		builder.appendSeparator("Details");

		builder.nextLine();
		builder.append(klaeranlageLb);
		builder.append(getKlaeranlageBox());

		builder.nextLine();
		builder.append(kanalArtLb);
		builder.append(getKanalArtOptBox());

		builder.nextLine();
		builder.append(abgabeLb);
		builder.append(getAbgaberelEinlBox(), 2);

		builder.nextLine();
		builder.append(abwAgLb);
		builder.append(getAbwAgEinlFeld(), 2);

		builder.appendSeparator("Bemerkungen");
		builder.appendRow("3dlu");
		builder.nextLine(2);
		JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:30dlu");
		builder.append(bemerkungsScroller, 5);
		builder.nextLine();

		builder.nextLine();
		builder.appendRow("fill:15dlu");
		builder.appendSeparator("Verknüpfungen");

		builder.nextLine();
		JScrollPane objektverknuepfungScroller = new JScrollPane(getObjektverknuepungTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:100dlu");
		builder.append(objektverknuepfungScroller, 5);
		builder.nextLine();
		JComponent buttonBar = ComponentFactory.buildRightAlignedBar(getSelectObjektButton(),
				getSaveElkaEinleitungsstelleButton());
		builder.append(buttonBar, 4);

		// Action Listener
		this.einleitungsartBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String einleit = (String) einleitungsartBox.getSelectedItem();
				switchEinleitungItems(einleit);
			}
		});

	};

	/**
	 * Methode verknüpft das lokal erstelle Objekt einleitungstelle mit der
	 * ElkaEinleitungsstelle der Datenbank und holt sich die Klaeranlagen aus der
	 * Datenbank
	 * 
	 * @throws RuntimeException
	 */
	public void fetchFormData() throws RuntimeException {
		this.einleitungsstelle = Einleitungsstelle.findByObjektId(this.hauptModul.getObjekt().getId());
		log.debug("Einleitungsstelle aus DB geholt: " + this.einleitungsstelle);

		List<Referenz> referenzen = Referenz.getAll();
		this.referenz = null;

		for (Referenz ref : referenzen) {
			if (ref.getqEl().getId() == this.einleitungsstelle.getId() && ref.getKlaeranlageByZKaNr() != null) {
				this.referenz = Referenz.findById(ref.getNr());
				log.debug("Referenz aus DB geholt: " + this.referenz);
			}
		}

		if (this.klaeranlagen == null) {
			this.klaeranlagen = DatabaseQuery.getKlaeranlage();
		}
	}

	/**
	 * Methode setzt die Attribute der Einleitungsstelle aus der Datenbank auf die
	 * der lokalen Einleitungstelle und die Verknüpfung mit der Kläranlage über die
	 * Tabelle referenz
	 * 
	 * @throws RuntimeException
	 */

	private String[] einleitungItems = { "Indirekteinleitung", "Indus./gew. Direkteinleitung",
			"Kommunales NW (Mischverfahren)", "Indus./gew. NW (Mischverfahren)",
			"NW aus privatem Bereich (Trennverfahren)", "Kleinkläranlage", "Kommunale kläranlage",
			"Kommunales NW (Trennverfahren)", "Indus./gew. NW (Trennverfahren)", "Grubenwasser",
			"Außerörtliche Straßeneinleitung", "Sonstige Direkteinleitung" };

	private String[] kanalArtItems = { "-", "Schmutzwasser", "Mischwasser", "Niederschlagswasser" };

	private String[] abgabeItems = { "-", "abgabepflichtig", "nicht abgabepflichtig" };

	public void updateForm() throws RuntimeException {
		if (this.einleitungsstelle != null) {
			if (this.einleitungsstelle.getErstellDat() != null) {
				getErstellDatDatum().setDate(this.einleitungsstelle.getErstellDat());
			}

			if (this.einleitungsstelle.getBezeichnung() != null) {
				getBezeichnungFeld().setText(this.einleitungsstelle.getBezeichnung());
			}

			if (this.einleitungsstelle.getAbwAgEinl() != null) {
				getAbwAgEinlFeld().setText(this.einleitungsstelle.getAbwAgEinl());
			}
			if (this.einleitungsstelle.getStillgelegtAm() != null) {
				getStillgelegtAmDatum().setDate(this.einleitungsstelle.getStillgelegtAm());
			}

			if (this.einleitungsstelle.getAbgaberelEinl() != null) {
				getAbgaberelEinlBox().setSelectedIndex(this.einleitungsstelle.getAbgaberelEinl());
			} else {
				getAbgaberelEinlBox().setSelectedIndex(-1);
			}

			if (this.einleitungsstelle.getEinleitungsart() != null) {
				getEinleitungsartBox().setSelectedIndex(this.einleitungsstelle.getEinleitungsart());
			} else {
				getEinleitungsartBox().setSelectedIndex(-1);
			}

			if (this.einleitungsstelle.getTypIndirekteinleitungTog() == true) {
				this.einleitungsartBox.setSelectedIndex(0);

			} else if (this.einleitungsstelle.getTypIndusGewerbDirekteinleitungTog() == true) {
				this.einleitungsartBox.setSelectedIndex(1);

			} else if (this.einleitungsstelle.getTypKommNwMischTog() == true) {
				this.einleitungsartBox.setSelectedIndex(2);

			} else if (this.einleitungsstelle.getTypIndusGewerbNwMischTog() == true) {
				this.einleitungsartBox.setSelectedIndex(3);

			} else if (this.einleitungsstelle.getTypNwPrivatTrennTog() == true) {
				this.einleitungsartBox.setSelectedIndex(4);

			} else if (this.einleitungsstelle.getTypKleinklaeranlageTog() == true) {
				this.einleitungsartBox.setSelectedIndex(5);

			} else if (this.einleitungsstelle.getTypKommKaTog() == true) {
				this.einleitungsartBox.setSelectedIndex(6);

			} else if (this.einleitungsstelle.getTypKommNwTrennTog() == true) {
				this.einleitungsartBox.setSelectedIndex(7);

			} else if (this.einleitungsstelle.getTypIndusGewerbNwTrennTog() == true) {
				this.einleitungsartBox.setSelectedIndex(8);

			} else if (this.einleitungsstelle.getTypGrubenwasserTog() == true) {
				this.einleitungsartBox.setSelectedIndex(9);

			} else if (this.einleitungsstelle.getTypAusseroertlicheStrasseneinleitungTog() == true) {
				this.einleitungsartBox.setSelectedIndex(10);

			} else if (this.einleitungsstelle.getTypSonstigeTog() == true) {
				this.einleitungsartBox.setSelectedIndex(11);
			}

			
			if (this.einleitungsstelle.getKanalArtOpt() != null) {
				getKanalArtOptBox().setSelectedIndex(this.einleitungsstelle.getKanalArtOpt());
			} else {
				getKanalArtOptBox().setSelectedIndex(-1);
			}

			if (this.klaeranlagen != null) {
				getKlaeranlageBox().setModel(new DefaultComboBoxModel<Klaeranlage>(this.klaeranlagen));

			}

			if (this.referenz != null) {
				getKlaeranlageBox().setSelectedItem(this.referenz.getKlaeranlageByZKaNr());
			}
			this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());

			if(this.einleitungsstelle.getBemerkung() != null) {
    			getBemerkungenArea().setText(this.einleitungsstelle.getBemerkung());
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
		getAbwAgEinlFeld().setText(null);
		getAbgaberelEinlBox().setSelectedIndex(-1);
		getKanalArtOptBox().setSelectedIndex(-1);
		getKlaeranlageBox().setSelectedIndex(-1);
		getEinleitungsartBox().setSelectedIndex(-1);
		getBemerkungenArea().setText(null);

	}

	/**
	 * Methode die je nach Eingabewert alles Eingabefelder des Panels aktiviert oder
	 * deaktiviert.
	 * 
	 * @param enabled
	 */
	public void enableAll(boolean enabled) {
		getErstellDatDatum().setEnabled(enabled);
		getBezeichnungFeld().setEnabled(enabled);
		getStillgelegtAmDatum().setEnabled(enabled);
		getAbwAgEinlFeld().setEnabled(enabled);
		getAbgaberelEinlBox().setEnabled(enabled);
		getKanalArtOptBox().setEnabled(enabled);
		getKlaeranlageBox().setEnabled(enabled);
		getEinleitungsartBox().setEnabled(enabled);
		getBemerkungenArea().setEnabled(enabled);

	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setObjektValues(Einleitungsstelle einleitungsstelle) {

		this.einleitungsstelle = einleitungsstelle;

		this.einleitungsstelle.setTypAusseroertlicheStrasseneinleitungTog(false);
		this.einleitungsstelle.setTypGrubenwasserTog(false);
		this.einleitungsstelle.setTypIndirekteinleitungTog(false);
		this.einleitungsstelle.setTypIndusGewerbDirekteinleitungTog(false);
		this.einleitungsstelle.setTypIndusGewerbNwMischTog(false);
		this.einleitungsstelle.setTypIndusGewerbNwTrennTog(false);
		this.einleitungsstelle.setTypKleinklaeranlageTog(false);
		this.einleitungsstelle.setTypKommKaTog(false);
		this.einleitungsstelle.setTypKommNwMischTog(false);
		this.einleitungsstelle.setTypKommNwTrennTog(false);
		this.einleitungsstelle.setTypNwPrivatTrennTog(false);
		this.einleitungsstelle.setTypSonstigeTog(false);

		this.einleitungsstelle.setAktualDat(new Date());

		Date erstellDat = this.erstellDatDatum.getDate();
		this.einleitungsstelle.setErstellDat(erstellDat);

		String bezeichnung = this.bezeichnungFeld.getText();
		if ("".equals(bezeichnung)) {
			this.einleitungsstelle.setBezeichnung(null);
		} else {
			this.einleitungsstelle.setBezeichnung(bezeichnung);
		}
		
		String bemerkung = this.bemerkungenArea.getText();
		if ("".equals(bemerkung)) {
			this.einleitungsstelle.setBemerkung(null);
		} else {
			this.einleitungsstelle.setBemerkung(bemerkung);
		}
		
		String abwAgEinl = this.abwAgEinlFeld.getText();
		if ("".equals(abwAgEinl)) {
			this.einleitungsstelle.setAbwAgEinl(null);
		} else {
			this.einleitungsstelle.setAbwAgEinl(abwAgEinl);
		}

		Date stillgelegtAm = this.stillgelegtAmDatum.getDate();
		this.einleitungsstelle.setStillgelegtAm(stillgelegtAm);

		Integer abgaberelEinl = ((JComboBox) this.abgaberelEinlBox).getSelectedIndex();
		this.einleitungsstelle.setAbgaberelEinl(abgaberelEinl);

		Integer einleitungsart = ((JComboBox) this.einleitungsartBox).getSelectedIndex();
		this.einleitungsstelle.setEinleitungsart(einleitungsart);

		if (this.einleitungsartBox.getSelectedIndex() == 0) {
			this.einleitungsstelle.setTypIndirekteinleitungTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 1) {
			this.einleitungsstelle.setTypIndusGewerbDirekteinleitungTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 2) {
			this.einleitungsstelle.setTypKommNwMischTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 3) {
			this.einleitungsstelle.setTypIndusGewerbNwMischTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 4) {
			this.einleitungsstelle.setTypNwPrivatTrennTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 5) {
			this.einleitungsstelle.setTypKleinklaeranlageTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 6) {
			this.einleitungsstelle.setTypKommKaTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 7) {
			this.einleitungsstelle.setTypKommNwTrennTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 8) {
			this.einleitungsstelle.setTypIndusGewerbNwTrennTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 9) {
			this.einleitungsstelle.setTypGrubenwasserTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 10) {
			this.einleitungsstelle.setTypAusseroertlicheStrasseneinleitungTog(true);

		} else if (this.einleitungsartBox.getSelectedIndex() == 11) {
			this.einleitungsstelle.setTypSonstigeTog(true);
		}

		Integer kanalArtOpt = ((JComboBox) this.kanalArtOptBox).getSelectedIndex();
		this.einleitungsstelle.setKanalArtOpt(kanalArtOpt);
	}

	/**
	 * Methode die, die Eingabefelder des Panels welche einen Wert haben in die
	 * Einleitungsstelle der Datenbank schreibt.
	 * 
	 * @return boolean
	 */
	public boolean saveElkaEinleitungsstelleDaten() {
		boolean success;

		setObjektValues(this.einleitungsstelle);
		String status = "";

		success = this.einleitungsstelle.merge();
		if (success) {
			log.debug("Einleitungsstelle" + this.einleitungsstelle.getObjekt().getBetreiberid().getName()
					+ " gespeichert.");
		} else {
			log.debug("Einleitungsstelle" + this.einleitungsstelle + " konnte nicht gespeichert werden!");
		}

		if (einleitungsartBox.getSelectedItem() == "Indirekteinleitung") {
			if (saveKlaeranlageDaten()) {
				status = status + " & Verknüpfung mit der Kläranlage "
						+ EinleitungsstellePanel.this.referenz.getKlaeranlageByZKaNr().getAnlage()
						+ " über die Referenz-Tabelle erfolgreich gespeichert.";
			} else {
				status = status + " & es wurde keine Verknüpfung mit einer Kläranlage gespeichert!";
			}
		}

		return success;

	}

	/**
	 * Methode, die die Verknüpfung der Einleitungsstelle mit der Kläranlage in der
	 * Relation elka_referenz herstellt
	 * 
	 * @return booleanS
	 */
	private boolean saveKlaeranlageDaten() {
		boolean success;
		if (getKlaeranlageBox().getSelectedItem() != null) {
			if (this.referenz == null) {
				this.referenz = new Referenz();
			}
			this.referenz.setKlaeranlageByZKaNr((Klaeranlage) getKlaeranlageBox().getSelectedItem());
			this.referenz.setqEl(this.einleitungsstelle);
			success = this.referenz.merge();
		} else {
			success = false;
		}
		return success;
	}

	/**
	 * Methode die eine neue Datenbank Einleitungsstelle erzeugt, sofern diese noch
	 * nicht existiert oder das Hauptmodul neu ist.
	 */
	public void completeObjekt() {
		if (this.hauptModul.isNew() || this.einleitungsstelle == null) {
			// Neue EinleitungstellePanel erzeugen
			this.einleitungsstelle = new Einleitungsstelle();
			// Objekt_Id setzen
			this.einleitungsstelle.setObjekt(this.hauptModul.getObjekt());
			this.einleitungsstelle.merge();
			// ElkaEinleitungsstelle.merge(this.einleitungstelle);
			log.debug("Neue ElkaEinleitungsstelle " + this.einleitungsstelle + " gespeichert.");
		}
	}

	/**
	 * Get-Methode die das ErstellDatDatum des Panels zurückgibt:
	 * 
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
	 * 
	 * @return {@link JTextField}
	 */
	private JTextField getBezeichnungFeld() {
		if (this.bezeichnungFeld == null) {
			this.bezeichnungFeld = new LimitedTextField(50);
		}
		return this.bezeichnungFeld;
	}

	/**
	 * Get-Methode die das ABW AG Einleitungsfeld des Panels zurückgibt:
	 * 
	 * @return {@link JTextField}
	 */
	private JTextField getAbwAgEinlFeld() {
		if (this.abwAgEinlFeld == null) {
			this.abwAgEinlFeld = new LimitedTextField(50);
		}
		return this.abwAgEinlFeld;
	}


	/**
	 * Get-Methode die das StillgelegtAmDattum des Panels zurückgibt:
	 * 
	 * @return {@link TextFieldDateChooser}
	 */
	private TextFieldDateChooser getStillgelegtAmDatum() {
		if (this.stillgelegtAmDatum == null) {
			this.stillgelegtAmDatum = new TextFieldDateChooser();
		}
		return this.stillgelegtAmDatum;
	}

	/**
	 * Get-Methode die das AbgaberelEinlFeld des Panels zurückgibt:
	 * 
	 * @return {@link JFormattedTextField}
	 */
	private JComboBox getAbgaberelEinlBox() {
		if (this.abgaberelEinlBox == null) {
			this.abgaberelEinlBox = new JComboBox();
			this.abgaberelEinlBox.setModel(new DefaultComboBoxModel(this.abgabeItems));
		}
		return this.abgaberelEinlBox;
	}

	/**
	 * Get-Methode die das KanalArtOptBox des Panels zurückgibt:
	 * 
	 * @return {@link JComboBox}
	 */
	private JComboBox getKanalArtOptBox() {
		if (this.kanalArtOptBox == null) {
			this.kanalArtOptBox = new JComboBox();
			this.kanalArtOptBox.setModel(new DefaultComboBoxModel(this.kanalArtItems));
		}
		return this.kanalArtOptBox;
	}

	/**
	 * Get-Methode die die Einleitungsart des Panels zurückgibt:
	 * 
	 * @return {@link JComboBox}
	 */
	private JComboBox getEinleitungsartBox() {
		if (this.einleitungsartBox == null) {
			this.einleitungsartBox = new JComboBox();
			this.einleitungsartBox.setModel(new DefaultComboBoxModel(this.einleitungItems));
		}
		return this.einleitungsartBox;
	}

	/**
	 * Get-Methode die die KlaeranlageBox des Panels zurückgibt
	 * 
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
	 * Get-Methode die das bemerkungenFeld des Panels zurückgibt:
	 * 
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

	private JTable getObjektverknuepungTabelle() {

		if (this.objektVerknuepfungModel == null) {
			this.objektVerknuepfungModel = new ObjektVerknuepfungModel(this.hauptModul.getObjekt());

			if (this.objektverknuepfungTabelle == null) {
				this.objektverknuepfungTabelle = new JTable(this.objektVerknuepfungModel);
			} else {
				this.objektverknuepfungTabelle.setModel(this.objektVerknuepfungModel);
			}
			this.objektverknuepfungTabelle.getColumnModel().getColumn(0).setPreferredWidth(5);
			this.objektverknuepfungTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
			this.objektverknuepfungTabelle.getColumnModel().getColumn(2).setPreferredWidth(250);

			this.objektverknuepfungTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = getObjektverknuepungTabelle().rowAtPoint(origin);

						if (row != -1) {
							Objektverknuepfung obj = EinleitungsstellePanel.this.objektVerknuepfungModel.getRow(row);
							if (obj.getObjektByIstVerknuepftMit().getId()
									.intValue() != EinleitungsstellePanel.this.hauptModul.getObjekt().getId()
											.intValue())
								EinleitungsstellePanel.this.hauptModul.getManager().getSettingsManager().setSetting(
										"auik.imc.edit_object", obj.getObjektByIstVerknuepftMit().getId().intValue(),
										false);
							else
								EinleitungsstellePanel.this.hauptModul.getManager().getSettingsManager().setSetting(
										"auik.imc.edit_object", obj.getObjektByObjekt().getId().intValue(), false);
							EinleitungsstellePanel.this.hauptModul.getManager().switchModul("m_objekt_bearbeiten");
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
					(KeyStroke) getVerknuepfungLoeschAction().getValue(Action.ACCELERATOR_KEY),
					getVerknuepfungLoeschAction().getValue(Action.NAME));
			this.objektverknuepfungTabelle.getActionMap().put(getVerknuepfungLoeschAction().getValue(Action.NAME),
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
				this.objektverknuepfungTabelle.setRowSelectionInterval(row, row);
				this.verknuepfungPopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	/**
	 * Switch the panel content according to the given type
	 * 
	 * @param type New panel content type
	 */

	public void switchEinleitungItems(String type) {
		if (type == null) {
			return;
		}

		if (type == "Indirekteinleitung") {
			
			klaeranlageLb.setVisible(true);
			klaeranlageBox.setVisible(true);
			kanalArtLb.setVisible(true);
			kanalArtOptBox.setVisible(true);
			abgabeLb.setVisible(false);
			abgaberelEinlBox.setVisible(false);
			abwAgLb.setVisible(false);
			abwAgEinlFeld.setVisible(false);

		} else {

			klaeranlageLb.setVisible(false);
			klaeranlageBox.setVisible(false);
			kanalArtLb.setVisible(false);
			kanalArtOptBox.setVisible(false);
			abgabeLb.setVisible(true);
			abgaberelEinlBox.setVisible(true);
			abwAgLb.setVisible(true);
			abwAgEinlFeld.setVisible(true);

			this.einleitungsstelle.setTypAusseroertlicheStrasseneinleitungTog(false);
			this.einleitungsstelle.setTypGrubenwasserTog(false);
			this.einleitungsstelle.setTypIndirekteinleitungTog(false);
			this.einleitungsstelle.setTypIndusGewerbDirekteinleitungTog(false);
			this.einleitungsstelle.setTypIndusGewerbNwMischTog(false);
			this.einleitungsstelle.setTypIndusGewerbNwTrennTog(false);
			this.einleitungsstelle.setTypKleinklaeranlageTog(false);
			this.einleitungsstelle.setTypKommKaTog(false);
			this.einleitungsstelle.setTypKommNwMischTog(false);
			this.einleitungsstelle.setTypKommNwTrennTog(false);
			this.einleitungsstelle.setTypNwPrivatTrennTog(false);
			this.einleitungsstelle.setTypSonstigeTog(false);

			if (this.einleitungsartBox.getSelectedIndex() == 0) {
				this.einleitungsstelle.setTypIndirekteinleitungTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 1) {
				this.einleitungsstelle.setTypIndusGewerbDirekteinleitungTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 2) {
				this.einleitungsstelle.setTypKommNwMischTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 3) {
				this.einleitungsstelle.setTypIndusGewerbNwMischTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 4) {
				this.einleitungsstelle.setTypNwPrivatTrennTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 5) {
				this.einleitungsstelle.setTypKleinklaeranlageTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 6) {
				this.einleitungsstelle.setTypKommKaTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 7) {
				this.einleitungsstelle.setTypKommNwTrennTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 8) {
				this.einleitungsstelle.setTypIndusGewerbNwTrennTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 9) {
				this.einleitungsstelle.setTypGrubenwasserTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 10) {
				this.einleitungsstelle.setTypAusseroertlicheStrasseneinleitungTog(true);

			} else if (this.einleitungsartBox.getSelectedIndex() == 11) {
				this.einleitungsstelle.setTypSonstigeTog(true);
			}

			hauptModul.getTabbedPane().addTab("Gewässerdaten", getGewaesserTab());
			getGewaesserTab().enableAll(true);
			getGewaesserTab().clearForm();
			getGewaesserTab().updateForm(einleitungsstelle);
			hauptModul.getTabbedPane().setSelectedIndex(2);

		}
	}

	public GewaesserdatenPanel getGewaesserTab() {
		if (gewaesserTab == null) {
			gewaesserTab = new GewaesserdatenPanel(hauptModul, einleitungsstelle);
			gewaesserTab.setBorder(Paddings.DIALOG);
		}
		return gewaesserTab;
	}

	/**
	 * Methode die den SaveElkaEinleitungsstelleButton zurückgibt sofern er
	 * existiert, ansonsten wird ein neuer erstellt und diesem einen
	 * {@link ActionListener} hinzugefügt, der bei einem Klick die Methoden
	 * <code>saveElkaEinleitungsstelleDaten</code> und
	 * <code>saveKlaeranlageDaten</code> ausführt.
	 * 
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
					if (saveElkaEinleitungsstelleDaten()) {
						status = "Einleitungsstelle " + EinleitungsstellePanel.this.einleitungsstelle.getId()
								+ " erfolgreich gespeichert.";
					} else {
						status = "Fehler beim Speichern der Einleitungsstelle!";
					}

					if (status.startsWith("Einleitungsstelle")) {
						EinleitungsstellePanel.this.hauptModul.getFrame().changeStatus(status,
								HauptFrame.SUCCESS_COLOR);
					} else {
						EinleitungsstellePanel.this.hauptModul.getFrame().changeStatus(status, HauptFrame.ERROR_COLOR);
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
					if (row != -1 && getObjektverknuepungTabelle().getEditingRow() == -1) {
						Objektverknuepfung verknuepfung = EinleitungsstellePanel.this.objektVerknuepfungModel
								.getRow(row);
						if (GUIManager.getInstance().showQuestion("Soll die Verknüpfung wirklich gelöscht werden?\n"
								+ "Hinweis: Die Aktion betrifft nur die " + "Verknüpfung, die Objekte bleiben erhalten "
								+ "und können jederzeit neu verknüpft werden.", "Löschen bestätigen")) {
							if (EinleitungsstellePanel.this.objektVerknuepfungModel.removeRow(row)) {
								EinleitungsstellePanel.this.hauptModul.getFrame().changeStatus("Objekt gelöscht.",
										HauptFrame.SUCCESS_COLOR);
								log.debug("Objekt " + verknuepfung.getId() + " wurde gelöscht!");
							} else {
								EinleitungsstellePanel.this.hauptModul.getFrame()
										.changeStatus("Konnte das Objekt nicht löschen!", HauptFrame.ERROR_COLOR);
							}
						}
					}
				}
			};
			this.verknuepfungLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
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
					ObjektChooser chooser = new ObjektChooser(EinleitungsstellePanel.this.hauptModul.getFrame(),
							EinleitungsstellePanel.this.einleitungsstelle.getObjekt(),
							EinleitungsstellePanel.this.objektVerknuepfungModel);
					chooser.setVisible(true);
				}
			});
		}
		return this.selectObjektButton;
	}

}