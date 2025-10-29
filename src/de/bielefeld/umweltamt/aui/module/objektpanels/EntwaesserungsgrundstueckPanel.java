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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
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
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Entwaesserungsgrundstueck;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.ZuordnungChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "EntwaesserungsgrundstueckPanel"-Tab des ObjektBearbeiten-Moduls
 *
 * @author Gerd Genuit
 * @date 15.01.2018
 */
public class EntwaesserungsgrundstueckPanel extends ObjectPanel {
	private static final long serialVersionUID = 7997458251785488488L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private String name;
	private BasisObjektBearbeiten hauptModul;

	// Widgets
	private TextFieldDateChooser erstellDatDatum = null;
	private JTextField bezeichnungFeld = null;
	// Daten
	private Entwaesserungsgrundstueck entwaesserungsgrundstueck = null;
	private JButton saveEntwaesserungsgrundstueckButton = null;

	// Objektverknuepfer
	private ObjektVerknuepfungModel objektVerknuepfungModel;
	private JTable objektverknuepfungTabelle = null;
	private JButton selectObjektButton = null;
	private Action verknuepfungLoeschAction;
	private JPopupMenu verknuepfungPopup;

	// Fields and Labels and Models
	private JComboBox<String> einleitungsbereichBox;
	private JTextField gebNameFeld = null;
	private JTextField konzeptNrFeld = null;
	private JTextField gebGroesseFeld = null;
	private JTextField regenspendeFeld = null;
	private JTextField regenhaufigkeitFeld = null;
	private JTextField regendauerFeld = null;
	private JCheckBox erlaubnisfreiBox = null;
	private JComboBox<String> einbauartBox;
	private JTextField dtvWertFeld = null;
	private JCheckBox woTog;
	private JCheckBox miTog;
	private JCheckBox geTog;
	private JCheckBox giTog;
	private JCheckBox gemTog;
	private JCheckBox strTog;
	private JCheckBox parkplatzTog;
	private JComboBox<String> wasserableitungsstreckeOptBox;
	private ZuordnungChooser<Abaverfahren> abaverfahrens;

	public void createFields() {

		abaverfahrens = new ZuordnungChooser<Abaverfahren>();

		abaverfahrens.setSort(true);
		abaverfahrens.setSortComparator((v1, v2) -> {
			return v1.getNr() - v2.getNr();
		});

	}

	private JLabel einleitungsbereichLabel = new JLabel("Einleitungsbereich");
	private JLabel gebNameLabel = new JLabel("Name des Entwässerungsgebiet");
	private JLabel konzeptNrLabel = new JLabel("Abwasserbeseitigungskonzeptnr.");
	private JLabel gebGroesseLabel = new JLabel("Größe des Entwässerungsgebiet [m²]");
	private JLabel regenspendeLabel = new JLabel("Regenspende[l/s*ha)]");
	private JLabel regenhaufigkeitLabel = new JLabel("Regenhäufigkeit[1/a]");
	private JLabel regendauerLabel = new JLabel("Regendauer [min]");
	private JLabel erlaubnisfreiLabel = new JLabel("Erlaubnisfreie Einleitung");
	private JLabel einbauartLabel = new JLabel("Einbauart");
	private JLabel abaverfahrensLabel = new JLabel("Abwasserbehandlungsverfahren");
	private JLabel dtvWertLabel = new JLabel("DTV-Wert[Kfz/24h]");
	private JLabel wasserableitungsstreckeOptLabel = new JLabel("Wasserableitungsstrecke");

	private String[] einlBereichItems = { "Einleitung aus nicht öffentlichem Bereich",
			"Einleitung aus öffentlichem Bereich", "Einleitung aus außerörtlichen Straßen" };

	private String[] einbauartItems = { "nicht zugeordnet", "Einbau im bestehenden Straßenablauf",
			"baulicher Ersatz des Straßenablaufs", "separates Schachtbauwerk" };

	private String[] streckeItems = { "nicht definiert", "Rohrleitung mit Hochbord",
			"Mulde mit Muldeneinläufen und Rohrleitung", "Mulde / Graben", "Mulde / Graben befestigt",
			"Rückhaltegraben", "sonstiges" };

	public EntwaesserungsgrundstueckPanel(BasisObjektBearbeiten hauptModul) {
		this.name = "Entwässerungsgrundstück";
		this.hauptModul = hauptModul;

		FormLayout layout = new FormLayout("r:pref, 5dlu, 80dlu, 5dlu, 80dlu, 5dlu, 80dlu, 5dlu, 80dlu, 5dlu, 80dlu", // Spalten
				"");

		createFields();

		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

		builder.appendSeparator("ELKA");
		builder.append("Bezeichnung:", getBezeichnungFeld(), 4);
		builder.nextLine();
		builder.append("Erstellung:", getErstellDatDatum());
		builder.nextLine();
		builder.append(einleitungsbereichLabel);
		builder.append(getEinleitungsbereichBox(), 4);
		builder.nextLine();
		builder.append(gebNameLabel);
		builder.append(getGebNameFeld(), 3);
		builder.nextLine();
		builder.append(konzeptNrLabel);
		builder.append(getKonzeptNrFeld());
		builder.nextLine();
		builder.append(dtvWertLabel);
		builder.append(getDtvWertFeld());
		builder.nextLine();
		builder.append(wasserableitungsstreckeOptLabel);
		builder.append(getWasserableitungsstreckeOptBox(), 4);
		builder.nextLine();
		builder.append(gebGroesseLabel);
		builder.append(getGebGroesseFeld());
		builder.nextLine();
		builder.append(regenspendeLabel);
		builder.append(getRegenspendeFeld());
		builder.append(regenhaufigkeitLabel);
		builder.append(getRegenhaufigkeitFeld());
		builder.append(regendauerLabel);
		builder.append(getRegendauerFeld());
		builder.nextLine();
		builder.append(erlaubnisfreiLabel);
		builder.append(getErlaubnisfreiBox());
		builder.nextLine();
		builder.append(einbauartLabel);
		builder.append(getEinbauartBox(), 3);
		builder.nextLine();
		builder.appendSeparator("Gebietsart");
		builder.nextLine();
		builder.append("");
		builder.append(getWoTog());
		builder.append(getMiTog());
		builder.append(getGeTog());
		builder.append(getParkplatzTog());
		builder.nextLine();
		builder.append("");
		builder.append(getGiTog());
		builder.append(getGemTog());
		builder.append(getStrTog());
		builder.nextLine();
		builder.appendSeparator(abaverfahrensLabel.getText());
		builder.nextLine();
		builder.append(abaverfahrens, 11);

		builder.nextLine();
		JScrollPane objektverknuepfungScroller = new JScrollPane(getObjektverknuepungTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:100dlu");
		builder.append(objektverknuepfungScroller, 11);
		builder.nextLine();
		JComponent buttonBar = ComponentFactory.buildRightAlignedBar(getSelectObjektButton(),
				getSaveEntwaesserungsgrundstueckButton());
		builder.append(buttonBar, 11);

		// Action Listener Einleitungsbereich
		this.einleitungsbereichBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String einlBer = (String) einleitungsbereichBox.getSelectedItem();
				switchEinlBereichItems(einlBer);
			}
		});
		addChangeListeners(getErstellDatDatum(), getBezeichnungFeld(),
		getDtvWertFeld(), getEinleitungsbereichBox(), getGebNameFeld(),
		getKonzeptNrFeld(), getGebGroesseFeld(), getRegenspendeFeld(),
		getRegenhaufigkeitFeld(), getRegendauerFeld(), getErlaubnisfreiBox(),
		getEinbauartBox(), getWoTog(), getMiTog(), getGeTog(), getGiTog(),
		getGemTog(), getStrTog(), getParkplatzTog());

	}

	/**
	 * Methode verknüpft das lokal erstelle Objekt einleitungstelle mit der
	 * ElkaSonderbauwerk der Datenbank und holt sich die Klaeranlagen aus der
	 * Datenbank
	 *
	 * @throws RuntimeException
	 */
	public void fetchFormData() throws RuntimeException {
		this.entwaesserungsgrundstueck = Entwaesserungsgrundstueck.findByObjektId(this.hauptModul.getObjekt());
		log.debug("Entwaesserungsgrundstueck aus DB geholt: " + this.entwaesserungsgrundstueck.toString());
		this.abaverfahrens.setData(Abaverfahren.getNwBehandel());
		List<Abaverfahren> selected = new ArrayList<Abaverfahren>();
		if (this.entwaesserungsgrundstueck != null) {
			this.abaverfahrens.applyEntries(selected);
		}
		setDirty(false);
	}

	/**
	 * Methode setzt die Attribute des Entwaesserungsgrundstueckes aus der Datenbank
	 * auf die der lokalen Einleitungstelle und die Verknüpfung mit der Kläranlage
	 * über die Tabelle referenz
	 *
	 * @throws RuntimeException
	 */
	public void updateForm() throws RuntimeException {
		if (this.entwaesserungsgrundstueck != null) {
			if (this.entwaesserungsgrundstueck.getErstellDat() != null) {
				getErstellDatDatum().setDate(this.entwaesserungsgrundstueck.getErstellDat());
			}

			this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
			this.bezeichnungFeld.setText(entwaesserungsgrundstueck.getBemerkung());
			this.gebNameFeld.setText(entwaesserungsgrundstueck.getNameEtwGebiet());
			if (this.entwaesserungsgrundstueck.getRegenspende() != null) {
				this.regenspendeFeld.setText(entwaesserungsgrundstueck.getRegenspende().toString());
			}
			if (entwaesserungsgrundstueck.getRegenhaeufigkeit() != null) {
				this.regenhaufigkeitFeld.setText(entwaesserungsgrundstueck.getRegenhaeufigkeit().toString());
			}
			if (entwaesserungsgrundstueck.getRegendauer() != null) {
				this.regendauerFeld.setText(entwaesserungsgrundstueck.getRegendauer().toString());
			}
			if (entwaesserungsgrundstueck.getEinlBereichOpt() != null) {
				einleitungsbereichBox.setSelectedIndex(entwaesserungsgrundstueck.getEinlBereichOpt());
			}
			if (entwaesserungsgrundstueck.getEinbauartOpt() != null) {
				einbauartBox.setSelectedIndex(entwaesserungsgrundstueck.getEinbauartOpt());
			}
			if (this.entwaesserungsgrundstueck.getEinlBereichOpt() != null) {
				getEinleitungsbereichBox().setSelectedIndex(this.entwaesserungsgrundstueck.getEinlBereichOpt());
				switchEinlBereichItems((String) getEinleitungsbereichBox().getSelectedItem());
			} else {
				getEinleitungsbereichBox().setSelectedIndex(-1);
			}
			if (this.entwaesserungsgrundstueck.getNameEtwGebiet() != null) {
				this.gebNameFeld.setText(entwaesserungsgrundstueck.getNameEtwGebiet());
			}
			if (this.entwaesserungsgrundstueck.getAbwbeskonNr() != null) {
				this.konzeptNrFeld.setText(entwaesserungsgrundstueck.getAbwbeskonNr());
			}
			if (this.entwaesserungsgrundstueck.getGrEntwGebiet() !=null) {
				this.gebGroesseFeld.setText(entwaesserungsgrundstueck.getGrEntwGebiet().toString());
			}
			if (this.entwaesserungsgrundstueck.getRegenspende() !=null) {
				this.regenspendeFeld.setText(entwaesserungsgrundstueck.getRegenspende().toString());
			}
			if (this.entwaesserungsgrundstueck.getRegendauer() !=null) {
				this.regendauerFeld.setText(entwaesserungsgrundstueck.getRegendauer().toString());
			}
			if (this.entwaesserungsgrundstueck.getRegenhaeufigkeit() != null) {
				this.regenhaufigkeitFeld.setText(entwaesserungsgrundstueck.getRegenhaeufigkeit().toString());
			}
			if (this.entwaesserungsgrundstueck.isErlFreiElTog() != null) {
				if (this.entwaesserungsgrundstueck.isErlFreiElTog() == true) {
					getErlaubnisfreiBox().setSelected(true);
				}else {
					getErlaubnisfreiBox().setSelected(false);
				}
			}

			if( this.entwaesserungsgrundstueck.getEinbauartOpt() !=null) {
				getEinbauartBox().setSelectedIndex(this.entwaesserungsgrundstueck.getEinbauartOpt());
			}else {
			 getEinbauartBox().setSelectedIndex(-1);
			}

			if(this.entwaesserungsgrundstueck.getDtvWert() !=null) {
				this.dtvWertFeld.setText(entwaesserungsgrundstueck.getDtvWert().toString());
			}

			if(this.entwaesserungsgrundstueck.getWoTog() !=null) {
				if (this.entwaesserungsgrundstueck.getWoTog()== true) {
					getWoTog().setSelected(true);
				}else {
					getWoTog().setSelected(false);
				}
			}

			if(this.entwaesserungsgrundstueck.getMiTog() !=null) {
				if (this.entwaesserungsgrundstueck.getMiTog()== true) {
					getMiTog().setSelected(true);
				}else {
					getMiTog().setSelected(false);
				}
			}

			if(this.entwaesserungsgrundstueck.getGeTog() !=null) {
				if (this.entwaesserungsgrundstueck.getGeTog()== true) {
					getGeTog().setSelected(true);
				}else {
					getGeTog().setSelected(false);
				}
			}
			if(this.entwaesserungsgrundstueck.getGiTog() !=null) {
				if (this.entwaesserungsgrundstueck.getGiTog()== true) {
					getGiTog().setSelected(true);
				}else {
					getGiTog().setSelected(false);
				}
			}
			if(this.entwaesserungsgrundstueck.getGemTog() !=null) {
				if (this.entwaesserungsgrundstueck.getGemTog()== true) {
					getGemTog().setSelected(true);
				}else {
					getGemTog().setSelected(false);
				}
			}
			if(this.entwaesserungsgrundstueck.getStrTog() !=null) {
				if (this.entwaesserungsgrundstueck.getStrTog()== true) {
					getStrTog().setSelected(true);
				}else {
					getStrTog().setSelected(false);
				}
			}
			if(this.entwaesserungsgrundstueck.getParkplatzTog() !=null) {
				if (this.entwaesserungsgrundstueck.getParkplatzTog()== true) {
					getParkplatzTog().setSelected(true);
				}else {
					getParkplatzTog().setSelected(false);
				}
			}

			List list = new ArrayList(entwaesserungsgrundstueck.getAbaverfahrens());
			abaverfahrens.setListData(list);

			switchEinlBereichItems((String) getEinleitungsbereichBox().getSelectedItem());
			setDirty(false);
		}
	}

	/**
	 * Methode die alle Eingabefelder des Panels auf den Standard zurücksetzt.
	 */
	public void clearForm() {
		getErstellDatDatum().setDate(null);
		getBezeichnungFeld().setText(null);
		getDtvWertFeld().setText(null);
		getEinleitungsbereichBox().setSelectedIndex(-1);
		getGebNameFeld().setText(null);
		getKonzeptNrFeld().setText(null);
		getGebGroesseFeld().setText(null);
		getRegenspendeFeld().setText(null);
		getRegenhaufigkeitFeld().setText(null);
		getRegendauerFeld().setText(null);
		getErlaubnisfreiBox().setSelected(false);
		getEinbauartBox().setSelectedIndex(-1);
		getDtvWertFeld().setText(null);
		getWoTog().setSelected(false);
		getMiTog().setSelected(false);
		getGeTog().setSelected(false);
		getGiTog().setSelected(false);
		getGemTog().setSelected(false);
		getStrTog().setSelected(false);
		getParkplatzTog().setSelected(false);
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
		getDtvWertFeld().setEnabled(enabled);
		getEinleitungsbereichBox().setEnabled(enabled);
		getGebNameFeld().setEnabled(enabled);
		getKonzeptNrFeld().setEnabled(enabled);
		getGebGroesseFeld().setEnabled(enabled);
		getRegenspendeFeld().setEnabled(enabled);
		getRegenhaufigkeitFeld().setEnabled(enabled);
		getRegendauerFeld().setEnabled(enabled);
		getErlaubnisfreiBox().setEnabled(enabled);
		getEinbauartBox().setEnabled(enabled);
		getDtvWertFeld().setEnabled(enabled);
		getWoTog().setEnabled(enabled);
		getMiTog().setEnabled(enabled);
		getGeTog().setEnabled(enabled);
		getGiTog().setEnabled(enabled);
		getGemTog().setEnabled(enabled);
		getStrTog().setEnabled(enabled);
		getParkplatzTog().setEnabled(enabled);

	}

	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Methode die, die Eingabefelder des Panels welche einen Wert haben in die
	 * Einleitungsstelle der Datenbank schreibt.
	 *
	 * @return boolean
	 */
	protected boolean doSavePanelData() {
		boolean success;
		if (this.entwaesserungsgrundstueck == null) {
			this.entwaesserungsgrundstueck = new Entwaesserungsgrundstueck();
			this.entwaesserungsgrundstueck.setObjekt(this.hauptModul.getObjekt());

		}
		if (bezeichnungFeld.getText() != null && !bezeichnungFeld.getText().isEmpty()) {
			this.entwaesserungsgrundstueck.setBemerkung(bezeichnungFeld.getText());
		}
		if (gebNameFeld.getText() != null && !gebNameFeld.getText().isEmpty()) {
			this.entwaesserungsgrundstueck.setNameEtwGebiet(gebNameFeld.getText());
		}
		if (konzeptNrFeld.getText() != null && !konzeptNrFeld.getText().isEmpty()) {
			this.entwaesserungsgrundstueck.setAbwbeskonNr(konzeptNrFeld.getText());
		}
		if (gebGroesseFeld.getText() != null && !gebGroesseFeld.getText().isEmpty()) {
			this.entwaesserungsgrundstueck.setGrEntwGebiet(Integer.parseInt(gebGroesseFeld.getText()));
		}
		if (regenspendeFeld.getText() != null && !regenspendeFeld.getText().isEmpty()) {
			this.entwaesserungsgrundstueck.setRegenspende(new BigDecimal(regenspendeFeld.getText()));
		}
		if (regenhaufigkeitFeld.getText() != null && !regenhaufigkeitFeld.getText().isEmpty()) {
			this.entwaesserungsgrundstueck.setRegenhaeufigkeit(new BigDecimal(regenhaufigkeitFeld.getText()));
		}
		this.entwaesserungsgrundstueck.setErlFreiElTog(erlaubnisfreiBox.isSelected());

		this.entwaesserungsgrundstueck.setAktualDat(new Date());

		Date erstellDat = this.erstellDatDatum.getDate() != null ? this.erstellDatDatum.getDate() : new Date();
		this.entwaesserungsgrundstueck.setErstellDat(erstellDat);

		success = this.entwaesserungsgrundstueck.merge();
		if (success) {
			log.debug("Entwaesserungsgrundstueck"
					+ this.entwaesserungsgrundstueck.getObjekt().getBetreiberid().getName() + " gespeichert.");
		} else {
			log.debug(
					"Entwaesserungsgrundstueck" + this.entwaesserungsgrundstueck + " konnte nicht gespeichert werden!");
		}

		if (getEinleitungsbereichBox().getSelectedItem() == null) {
			this.entwaesserungsgrundstueck.setEinlBereichOpt(null);
		} else {
			String type = getEinleitungsbereichBox().getSelectedItem().toString();
			switch ((String) type) {
			case "Einleitung aus nicht öffentlichem Bereich":
				this.entwaesserungsgrundstueck.setEinlBereichOpt(0);
				break;
			case "Einleitung aus öffentlichem Bereich":
				this.entwaesserungsgrundstueck.setEinlBereichOpt(1);
				break;
			case "Einleitung aus außerörtlichen Straßen":
				this.entwaesserungsgrundstueck.setEinlBereichOpt(2);
				break;
			}
		}

		if (getEinbauartBox().getSelectedItem() == null) {
			this.entwaesserungsgrundstueck.setEinbauartOpt(null);
		} else {
			String type = getEinbauartBox().getSelectedItem().toString();
			switch ((String) type) {
			case "nicht zugeordnet":
				this.entwaesserungsgrundstueck.setEinbauartOpt(null);
				break;
			case "Einbau im bestehenden Straßenablauf":
				this.entwaesserungsgrundstueck.setEinbauartOpt(1);
				break;
			case "baulicher Ersatz des Straßenablaufs":
				this.entwaesserungsgrundstueck.setEinbauartOpt(2);
				break;
			case "separates Schachtbauwerk":
				this.entwaesserungsgrundstueck.setEinbauartOpt(3);
				break;
			}
		}

		if (getWasserableitungsstreckeOptBox().getSelectedItem() == null) {
			this.entwaesserungsgrundstueck.setWasserableitungsstreckeOpt(null);
		} else {
			String type = getWasserableitungsstreckeOptBox().getSelectedItem().toString();
			switch ((String) type) {
			case "nicht definiert":
				this.entwaesserungsgrundstueck.setWasserableitungsstreckeOpt(null);
				break;
			case "Rohrleitung mit Hochbord":
				this.entwaesserungsgrundstueck.setWasserableitungsstreckeOpt(1);
				break;
			case "Mulde mit Muldeneinläufen und Rohrleitung":
				this.entwaesserungsgrundstueck.setWasserableitungsstreckeOpt(2);
				break;
			case "Mulde / Graben":
				this.entwaesserungsgrundstueck.setWasserableitungsstreckeOpt(3);
				break;
			case "Mulde / Graben befestigt":
				this.entwaesserungsgrundstueck.setWasserableitungsstreckeOpt(4);
				break;
			case " Rückhaltegraben":
				this.entwaesserungsgrundstueck.setWasserableitungsstreckeOpt(5);
				break;
			case "sonstiges":
				this.entwaesserungsgrundstueck.setWasserableitungsstreckeOpt(6);
				break;
			}
		}

        List<Abaverfahren> selected = abaverfahrens.getSelected();
        List<Abaverfahren> removed = new ArrayList<>();
        Set set = new HashSet(selected);
        entwaesserungsgrundstueck.setAbaverfahrens(set);
        Set<Abaverfahren> verfahrens =
        		entwaesserungsgrundstueck.getAbaverfahrens();
        if (abaverfahrens != null) {
            verfahrens.forEach(item -> {
                if (!abaverfahrens.getSelected().contains(item)) {
                    removed.add(item);
                }
            });
            selected.forEach(item -> {
                if (!verfahrens.contains(item)) {
                    verfahrens.add(item);
                }
            });
        }
        verfahrens.removeAll(removed);

		if (this.entwaesserungsgrundstueck.merge()) {
			success = true;
		} else {
			success = false;
		}

		return success;
	}

	public void switchEinlBereichItems(String type) {
		if (type == null) {
			return;
		}

		if (type == "Einleitung aus nicht öffentlichem Bereich") {
			erstellDatDatum.setVisible(true);
			bezeichnungFeld.setVisible(true);
			saveEntwaesserungsgrundstueckButton.setVisible(true);
			objektverknuepfungTabelle.setVisible(true);
			selectObjektButton.setVisible(true);

			einleitungsbereichBox.setVisible(true);
			gebNameFeld.setVisible(false);
			konzeptNrFeld.setVisible(false);
			gebGroesseFeld.setVisible(true);
			regenspendeFeld.setVisible(true);
			regenhaufigkeitFeld.setVisible(true);
			regendauerFeld.setVisible(true);
			erlaubnisfreiBox.setVisible(true);
			einbauartBox.setVisible(true);
			dtvWertFeld.setVisible(false);
			woTog.setVisible(true);
			miTog.setVisible(true);
			geTog.setVisible(true);
			giTog.setVisible(true);
			gemTog.setVisible(true);
			strTog.setVisible(true);
			parkplatzTog.setVisible(true);
			wasserableitungsstreckeOptBox.setVisible(false);

			einleitungsbereichLabel.setVisible(true);
			gebNameLabel.setVisible(false);
			konzeptNrLabel.setVisible(false);
			gebGroesseLabel.setVisible(true);
			regenspendeLabel.setVisible(true);
			regenhaufigkeitLabel.setVisible(true);
			regendauerLabel.setVisible(true);
			erlaubnisfreiLabel.setVisible(true);
			einbauartLabel.setVisible(true);
			abaverfahrensLabel.setVisible(true);
			dtvWertLabel.setVisible(false);
			wasserableitungsstreckeOptLabel.setVisible(false);

		}

		if (type == "Einleitung aus öffentlichem Bereich") {
			erstellDatDatum.setVisible(true);
			bezeichnungFeld.setVisible(true);
			saveEntwaesserungsgrundstueckButton.setVisible(true);
			objektverknuepfungTabelle.setVisible(true);
			selectObjektButton.setVisible(true);

			einleitungsbereichBox.setVisible(true);
			gebNameFeld.setVisible(true);
			konzeptNrFeld.setVisible(true);
			gebGroesseFeld.setVisible(true);
			regenspendeFeld.setVisible(true);
			regenhaufigkeitFeld.setVisible(true);
			regendauerFeld.setVisible(true);
			erlaubnisfreiBox.setVisible(true);
			einbauartBox.setVisible(true);
			dtvWertFeld.setVisible(false);
			woTog.setVisible(true);
			miTog.setVisible(true);
			geTog.setVisible(true);
			giTog.setVisible(true);
			gemTog.setVisible(true);
			strTog.setVisible(true);
			parkplatzTog.setVisible(true);
			wasserableitungsstreckeOptBox.setVisible(false);

			einleitungsbereichLabel.setVisible(true);
			gebNameLabel.setVisible(true);
			konzeptNrLabel.setVisible(true);
			gebGroesseLabel.setVisible(true);
			regenspendeLabel.setVisible(true);
			regenhaufigkeitLabel.setVisible(true);
			regendauerLabel.setVisible(true);
			erlaubnisfreiLabel.setVisible(true);
			einbauartLabel.setVisible(true);
			abaverfahrensLabel.setVisible(true);
			dtvWertLabel.setVisible(false);
			wasserableitungsstreckeOptLabel.setVisible(false);
		}

		if (type == "Einleitung aus außerörtlichen Straßen") {
			erstellDatDatum.setVisible(true);
			bezeichnungFeld.setVisible(true);
			saveEntwaesserungsgrundstueckButton.setVisible(true);
			objektverknuepfungTabelle.setVisible(true);
			selectObjektButton.setVisible(true);

			einleitungsbereichBox.setVisible(true);
			gebNameFeld.setVisible(false);
			konzeptNrFeld.setVisible(false);
			gebGroesseFeld.setVisible(true);
			regenspendeFeld.setVisible(true);
			regenhaufigkeitFeld.setVisible(true);
			regendauerFeld.setVisible(true);
			erlaubnisfreiBox.setVisible(true);
			einbauartBox.setVisible(true);
			dtvWertFeld.setVisible(true);
			woTog.setVisible(true);
			miTog.setVisible(true);
			geTog.setVisible(true);
			giTog.setVisible(true);
			gemTog.setVisible(true);
			strTog.setVisible(true);
			parkplatzTog.setVisible(true);
			wasserableitungsstreckeOptBox.setVisible(true);

			einleitungsbereichLabel.setVisible(true);
			gebNameLabel.setVisible(false);
			konzeptNrLabel.setVisible(false);
			gebGroesseLabel.setVisible(true);
			regenspendeLabel.setVisible(true);
			regenhaufigkeitLabel.setVisible(true);
			regendauerLabel.setVisible(true);
			erlaubnisfreiLabel.setVisible(true);
			einbauartLabel.setVisible(true);
			abaverfahrensLabel.setVisible(true);
			dtvWertLabel.setVisible(true);
			wasserableitungsstreckeOptLabel.setVisible(true);
		}
	}

	/**
	 * Methode die eine neue Datenbank Entwaesserungsgrundstueck erzeugt, sofern
	 * diese noch nicht existiert oder das Hauptmodul neu ist.
	 */
	public void completeObjekt() {
		if (this.hauptModul.isNew() || this.entwaesserungsgrundstueck == null) {
			// Neue EinleitungstellePanel erzeugen
			this.entwaesserungsgrundstueck = new Entwaesserungsgrundstueck();
			this.entwaesserungsgrundstueck.setErstellDat(new Date());
			this.entwaesserungsgrundstueck.setAktualDat(new Date());
			// Objekt_Id setzen
			this.entwaesserungsgrundstueck.setObjekt(this.hauptModul.getObjekt());
			this.entwaesserungsgrundstueck.merge();
			// ElkaEinleitungsstelle.merge(this.einleitungstelle);
			log.debug("Neues Entwaesserungsgrundstueck " + this.entwaesserungsgrundstueck + " gespeichert.");
		}
	}

//Getter
	private TextFieldDateChooser getErstellDatDatum() {
		if (this.erstellDatDatum == null) {
			this.erstellDatDatum = new TextFieldDateChooser();
		}
		return this.erstellDatDatum;
	}

	private JTextField getBezeichnungFeld() {
		if (this.bezeichnungFeld == null) {
			this.bezeichnungFeld = new LimitedTextField(50);
		}
		return this.bezeichnungFeld;
	}

	private JComboBox<String> getEinleitungsbereichBox() {
		if (this.einleitungsbereichBox == null) {
			this.einleitungsbereichBox = new JComboBox<>();
			this.einleitungsbereichBox.setModel(
                new DefaultComboBoxModel<>(this.einlBereichItems));
		}
		return this.einleitungsbereichBox;
	}

	private JComboBox<String> getWasserableitungsstreckeOptBox() {
		if (this.wasserableitungsstreckeOptBox == null) {
			this.wasserableitungsstreckeOptBox = new JComboBox<>();
			this.wasserableitungsstreckeOptBox.setModel(
                new DefaultComboBoxModel<>(this.streckeItems));
		}
		return this.wasserableitungsstreckeOptBox;
	}

	private JTextField getGebNameFeld() {
		if (this.gebNameFeld == null) {
			this.gebNameFeld = new LimitedTextField(50);
		}
		return this.gebNameFeld;
	}

	private JTextField getKonzeptNrFeld() {
		if (this.konzeptNrFeld == null) {
			this.konzeptNrFeld = new LimitedTextField(50);
		}
		return this.konzeptNrFeld;
	}

	private JTextField getGebGroesseFeld() {
		if (this.gebGroesseFeld == null) {
			this.gebGroesseFeld = new LimitedTextField(50);
		}
		return this.gebGroesseFeld;
	}

	private JTextField getDtvWertFeld() {
		if (this.dtvWertFeld == null) {
			this.dtvWertFeld = new LimitedTextField(50);
		}
		return this.dtvWertFeld;
	}

	private JTextField getRegenspendeFeld() {
		if (this.regenspendeFeld == null) {
			this.regenspendeFeld = new LimitedTextField(50);
		}
		return this.regenspendeFeld;
	}

	private JTextField getRegenhaufigkeitFeld() {
		if (this.regenhaufigkeitFeld == null) {
			this.regenhaufigkeitFeld = new LimitedTextField(50);
		}
		return this.regenhaufigkeitFeld;
	}

	private JTextField getRegendauerFeld() {
		if (this.regendauerFeld == null) {
			this.regendauerFeld = new LimitedTextField(50);
		}
		return this.regendauerFeld;
	}

	private JCheckBox getErlaubnisfreiBox() {
		if (this.erlaubnisfreiBox == null) {
			this.erlaubnisfreiBox = new JCheckBox();
		}
		return this.erlaubnisfreiBox;
	}

	private JComboBox<String> getEinbauartBox() {
		if (this.einbauartBox == null) {
			this.einbauartBox = new JComboBox<>();
			this.einbauartBox.setModel(
                new DefaultComboBoxModel<>(this.einbauartItems));
		}
		return this.einbauartBox;
	}

	private JCheckBox getWoTog() {
		if (woTog == null) {
			woTog = new JCheckBox("Wohngebiet");
		}
		return woTog;
	}

	private JCheckBox getMiTog() {
		if (miTog == null) {
			miTog = new JCheckBox("Mischgebiet");
		}
		return miTog;
	}

	private JCheckBox getGeTog() {
		if (geTog == null) {
			geTog = new JCheckBox("Gewerbegebiet");
		}
		return geTog;
	}

	private JCheckBox getGiTog() {
		if (giTog == null) {
			giTog = new JCheckBox("Industriegebiet");
		}
		return giTog;
	}

	private JCheckBox getGemTog() {
		if (gemTog == null) {
			gemTog = new JCheckBox("Gemeinbedarfsgeb.");
		}
		return gemTog;
	}

	private JCheckBox getStrTog() {
		if (strTog == null) {
			strTog = new JCheckBox("Straßenfläche");
		}
		return strTog;
	}

	private JCheckBox getParkplatzTog() {
		if (parkplatzTog == null) {
			parkplatzTog = new JCheckBox("Parkplatz");
		}
		return parkplatzTog;
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
							Objektverknuepfung obj = EntwaesserungsgrundstueckPanel.this.objektVerknuepfungModel
									.getRow(row);
							if (obj.getObjektByIstVerknuepftMit().getId()
									.intValue() != EntwaesserungsgrundstueckPanel.this.hauptModul.getObjekt().getId()
											.intValue())
								EntwaesserungsgrundstueckPanel.this.hauptModul.getManager().getSettingsManager()
										.setSetting("auik.imc.edit_object",
												obj.getObjektByIstVerknuepftMit().getId().intValue(), false);
							else
								EntwaesserungsgrundstueckPanel.this.hauptModul.getManager().getSettingsManager()
										.setSetting("auik.imc.edit_object", obj.getObjektByObjekt().getId().intValue(),
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
	 * Methode die den SaveEntwaesserungsgrundstueckButton zurückgibt sofern er
	 * existiert, ansonsten wird ein neuer erstellt und diesem einen
	 * {@link ActionListener} hinzugefügt, der bei einem Klick die Methoden
	 * <code>saveEntwaesserungsgrundstueckDaten</code>.
	 *
	 * @see #saveEntwaesserungsgrundstueck()
	 * @return {@link JButton}
	 */
	private JButton getSaveEntwaesserungsgrundstueckButton() {
		if (this.saveEntwaesserungsgrundstueckButton == null) {
			this.saveEntwaesserungsgrundstueckButton = new JButton("Speichern");

			this.saveEntwaesserungsgrundstueckButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					enableAll(true);
					String status = "";
					if (hauptModul.saveAllTabs()) {
						status = "Entwässerungsgrundstück "
								+ EntwaesserungsgrundstueckPanel.this.entwaesserungsgrundstueck.getNr()
								+ " erfolgreich gespeichert.";
					} else {
						status = "Fehler beim Speichern der Entwässerungsgrundstück!";
					}
					if (status.startsWith("Entwässerungsgrundstück")) {
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
					if (row != -1 && getObjektverknuepungTabelle().getEditingRow() == -1) {
						Objektverknuepfung verknuepfung = EntwaesserungsgrundstueckPanel.this.objektVerknuepfungModel
								.getRow(row);
						if (GUIManager.getInstance().showQuestion("Soll die Verknüpfung wirklich gelöscht werden?\n"
								+ "Hinweis: Die Aktion betrifft nur die " + "Verknüpfung, die Objekte bleiben erhalten "
								+ "und können jederzeit neu verknüpft werden.", "Löschen bestätigen")) {
							if (EntwaesserungsgrundstueckPanel.this.objektVerknuepfungModel.removeRow(row)) {
								EntwaesserungsgrundstueckPanel.this.hauptModul.getFrame()
										.changeStatus("Objekt gelöscht.", HauptFrame.SUCCESS_COLOR);
								log.debug("Objekt " + verknuepfung.getId() + " wurde gelöscht!");
							} else {
								EntwaesserungsgrundstueckPanel.this.hauptModul.getFrame()
										.changeStatus("Konnte das Objekt nicht löschen!", HauptFrame.ERROR_COLOR);
							}
						}
					}
				}
			};
			this.verknuepfungLoeschAction.putValue(Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_L));
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
					ObjektChooser chooser = new ObjektChooser(EntwaesserungsgrundstueckPanel.this.hauptModul.getFrame(),
							EntwaesserungsgrundstueckPanel.this.entwaesserungsgrundstueck.getObjekt(),
							EntwaesserungsgrundstueckPanel.this.objektVerknuepfungModel);
					chooser.setVisible(true);
				}
			});
		}
		return this.selectObjektButton;
	}

}
