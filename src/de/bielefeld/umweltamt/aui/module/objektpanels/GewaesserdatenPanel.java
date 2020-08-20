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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.codehaus.jackson.map.ext.JodaDeserializers.DateMidnightDeserializer;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import com.ibm.icu.math.BigDecimal;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.jdi.IntegerValue;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Einleitungsstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Versickerungsanlage;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaGewkennz;
/**
 * DAs "GewaesserdatenTab" des Einleitungsstelle-Panels
 * d
 * @author Lisa Fuchs
 * @date 27.07.2020
 */

public class GewaesserdatenPanel extends JPanel {
	private static final long serialVersionUID = 7997458251785488488L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private String name;
	private BasisObjektBearbeiten hauptModul;
	private Einleitungsstelle einleitungsstelle;
	private Versickerungsanlage versickerungsanlage;
    private EinleitungsstellePanel einleitungsstelleTab;
   

	// Widgets allgemein
	private JButton saveGewDatenButton;
	private JComboBox stationierung3OptBox = null; // Typ Gewässer Bezeichnung
	
	// Widgets nicht stationiertes & stat. Gewässer
	
	private DoubleField einzugsgebietFeld = null; // Einzugsgebiet des Gewässers bis zur Einleitung
	private JTextField gewnameStatFeld = null; // Gewässername stat Gewässer in welches nstat Gew mündet
	private JComboBox gewkennzStatBox =null; // Gewässerkennzahl stat Gewässer in welches nstat Gew. mündet
	
	
// Widgets nur nicht stationiertes Gewässer
	
	private JTextField gewaessernameNs3Feld = null; // Gewässername nicht stat. Gewässer
	private DoubleField stationierungNs3Feld = null; // Stationierung der Mündung in das stat. Gewässer
	
	
	// Widgets nur stat Gewässer

	private JTextField gewaessernameAlias3Feld = null; // Aliasname stat Gewässer
	private DoubleField stationierungSt3Feld = null; // Stationierung stat Gewässer

	// Widgets Grundwasser
	private JComboBox verAnlageOptBox = null; /// AnlagenTyp
	private JTextField sonstigesVersFeld = null; // sonstige Anlage
	private JTextField bauartzulIdFeld = null;
	private JCheckBox landesfoerderungTogBox = null;
	private JCheckBox notueberlaufTogBox = null;
	private JTextField notueberlaufZielFeld = null;
	private DoubleField durchlaessigkeitFeld = null;
	private DoubleField flurabstandFeld = null;
	private DoubleField gelaendeVerAnlageFeld = null; // Geländehöhe im Bereich der Versickerungsanlagen
	private DoubleField abstGrGrenzeFeld = null;
	private DoubleField abstUnterkGebaeudeFeld = null;
	private DoubleField abstVerAnlageFeld = null;

	// Labels
	private JLabel stationierung3OptLb = new JLabel("Typ Gewässerbezug");
	private JLabel gewnameStat1Lb = new JLabel("Gewässername des (zgh) stat. Gewäsers");
	private JLabel gewkennzStat1Lb = new JLabel("Gewässerkennzahl des (zgh) stat. Gewässers");
	private JLabel einzugsgebietLb = new JLabel("Einzugsgebiet des Gewässers bis zur Einleitung [km²]");
	
	//nicht stat Gew
	private JLabel gewaessernameNs3Lb = new JLabel("Gewässername des n stat. Gewässers");
	private JLabel stationierungNs3Lb = new JLabel("Stationierung der Mündung in das stat. Gewässer [km]");
	
	
	//Stat Gew
	private JLabel gewaessernameAlias3Lb = new JLabel("Aliasname");
	private JLabel stationierungSt3Lb = new JLabel("Stationierung [km]");

	private JLabel verAnlageOptLb = new JLabel("Anlagentyp");
	private JLabel sonstigesVersLb = new JLabel("Sonstiger Typ");
	private JLabel bauartzulIdLb = new JLabel("Bauartzulassungs-Nr.");
	private JLabel landesfoerderungTogLb = new JLabel("Untergrundart");
	private JLabel notueberlaufTogLb = new JLabel("Notüberlauf");
	private JLabel notueberlaufZielLb = new JLabel("Ziel des Notüberlaufs");
	private JLabel durchlaessigkeitLb = new JLabel("Durchlässigkeitsbeiwert");
	private JLabel flurabstandLb = new JLabel("Flurabstand des Grundwassers [m]");
	private JLabel gelaendeVerAnlageLb = new JLabel("Geländehöhe [m ü. NHN]");
	private JLabel abstGrGrenzeLb = new JLabel("Abstand zur Grundstücksgrenze [m]");
	private JLabel abstUnterkGebaeudeLb = new JLabel("Abstand zum nächsten unterkellerten Gebäude [m]");
	private JLabel abstVerAnlageLb = new JLabel("Abstand zur nächstenVersickerungsanlage [m]");

	
	
	@SuppressWarnings("deprecation")
	public GewaesserdatenPanel(BasisObjektBearbeiten hauptModul, Einleitungsstelle einleitungsstelle) {

		this.name = "Gewaesserdaten";
		this.hauptModul = hauptModul;
		this.einleitungsstelle = einleitungsstelle;

		FormLayout layout = new FormLayout("r:pref, 5dlu, 100dlu, 200dlu", // Spalten
				"");

		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.nextLine();
		builder.appendRow("fill:5dlu");
		builder.appendSeparator("Gewässerdaten");

		builder.nextLine();
		builder.append(stationierung3OptLb);
		builder.append(getStationierung3OptBox());

		builder.nextLine();
		builder.append(gewnameStat1Lb);
		builder.append(getGewnameStatFeld());

		builder.nextLine();
		builder.append(gewkennzStat1Lb);
		builder.append(getGewkennzStatBox());
		
		builder.nextLine();
		builder.append(einzugsgebietLb);
		builder.append(getEinzugsgebietFeld());
		
		builder.nextLine();
		builder.append(gewaessernameNs3Lb);
		builder.append(getGewaessernameNs3Feld());

		builder.nextLine();
		builder.append(stationierungNs3Lb);
		builder.append(getStationierungsNs3Feld());

		builder.nextLine();
		builder.append(gewaessernameAlias3Lb);
		builder.append(getGewaessernameAlias3Feld());

		builder.nextLine();
		builder.append(stationierungSt3Lb);
		builder.append(getStationierungSt3Feld());

		builder.nextLine();
		builder.append(verAnlageOptLb);
		builder.append(getVerAnlageOptBox());

		builder.nextLine();
		builder.append(sonstigesVersLb);
		builder.append(getSonstigesVersFeld());

		builder.nextLine();
		builder.append(bauartzulIdLb);
		builder.append(getBauartzulIdFeld());

		builder.nextLine();
		builder.append(landesfoerderungTogLb);
		builder.append(isLandesfoerderungTogBox());

		builder.nextLine();
		builder.append(notueberlaufTogLb);
		builder.append(isNotueberlaufTogBox());

		builder.nextLine();
		builder.append(notueberlaufZielLb);
		builder.append(getNotueberlaufZielFeld());

		builder.nextLine();
		builder.append(durchlaessigkeitLb);
		builder.append(getDurchlaessigkeitFeld());

		builder.nextLine();
		builder.append(flurabstandLb);
		builder.append(getFlurabstandFeld());

		builder.nextLine();
		builder.append(gelaendeVerAnlageLb);
		builder.append(getGelaendeVerAnlageFeld());

		builder.nextLine();
		builder.append(abstGrGrenzeLb);
		builder.append(getAbstGrGrenzeFeld());

		builder.nextLine();
		builder.append(abstUnterkGebaeudeLb);
		builder.append(getAbstUnterkGebaeudeFeld());

		builder.nextLine();
		builder.append(abstVerAnlageLb);
		builder.append(getAbstVerAnlageFeld());

		builder.nextLine();

		JComponent buttonBar = ComponentFactory.buildRightAlignedBar(getSaveGewDatenButton());
		builder.append(buttonBar, 2);

		// SaveButton!

		// Action Listener Typ Gewässerbezug
		this.stationierung3OptBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String typGew = (String) stationierung3OptBox.getSelectedItem();
				switchTypGewBezItems(typGew);
			}

		});

		this.verAnlageOptBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String verAnlOpt = (String) verAnlageOptBox.getSelectedItem();
				switchAnlagenItems(verAnlOpt);
			}
		});

		this.gewkennzStatBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer gewKennz = (Integer) gewkennzStatBox.getSelectedItem();
				switchGewKennz(gewKennz);;
			}
		});

	}

	private String[] typGewBezItems = { "nicht stationiertes Gewässer", "stationiertes Gewässer", "Grundwasser",
			"keine Angabe" };

	private String[] anlagenItems = { "-", "Flächenversickerung", "Muldenversickerung", "Rigolenversickerung",
			"Mulden-Rigolenvescikerung", "Schachtversickerung", "Versickerungsbecken", "Sonstiges" };

	
	private Integer[] mapElkaGewkennz = 	DatabaseQuery.getMapElkaGewkennzArray();


	

	public void fetchFormData() throws RuntimeException {
		
		this.versickerungsanlage = einleitungsstelle.getVersickerungsanlages().iterator().next();
		log.debug("Gewässerdaten werden geholt: ID" + this.versickerungsanlage);
	}

	public void updateForm(Einleitungsstelle einleitungsstelle) throws RuntimeException {
		this.einleitungsstelle = einleitungsstelle;

		if (this.einleitungsstelle != null) {
			if (this.einleitungsstelle.getStationierung3Opt() != null) {
				getStationierung3OptBox().setSelectedIndex(this.einleitungsstelle.getStationierung3Opt());
				switchTypGewBezItems((String) getStationierung3OptBox().getSelectedItem());
			} else {
				getStationierung3OptBox().setSelectedIndex(-1);
			}

			if (this.einleitungsstelle.getGewaessernameNs3() != null) {
				getGewaessernameNs3Feld().setText(this.einleitungsstelle.getGewaessernameNs3());
			}

			if (this.einleitungsstelle.getStationierungNs3() != null) {
				getStationierungsNs3Feld().setValue(this.einleitungsstelle.getStationierungNs3());
			}

			if (this.einleitungsstelle.getEinzugsgebiet() != null) {
				getEinzugsgebietFeld().setValue(this.einleitungsstelle.getEinzugsgebiet());
			}

			if (this.einleitungsstelle.getGewnameStat() != null) {
				getGewnameStatFeld().setText(this.einleitungsstelle.getGewnameStat());
			}

			if (this.einleitungsstelle.getGewkennzStat() != null) {
				getGewkennzStatBox().setSelectedItem(Integer.valueOf(einleitungsstelle.getGewkennzStat()));
			} else {
				getGewkennzStatBox().setSelectedIndex(-1);
			}


			if (this.einleitungsstelle.getGewaessernameAlias3() != null) {
				getGewaessernameAlias3Feld().setText(this.einleitungsstelle.getGewaessernameAlias3());
			}

			if (this.einleitungsstelle.getStationierungSt3() != null) {
				getStationierungSt3Feld().setValue(this.einleitungsstelle.getStationierungSt3());
			}

			if (this.einleitungsstelle.getVersickerungsanlages().iterator().hasNext()) {
				this.versickerungsanlage = this.einleitungsstelle.getVersickerungsanlages().iterator().next();

				if (this.versickerungsanlage.getVerAnlageOpt() != null) {
					getVerAnlageOptBox().setSelectedIndex(this.versickerungsanlage.getVerAnlageOpt());
				} else {
					getVerAnlageOptBox().setSelectedIndex(-1);
				}

				if (this.versickerungsanlage.getSonstigesVers() != null) {
					getSonstigesVersFeld().setText(this.versickerungsanlage.getSonstigesVers());

				}

				if (this.versickerungsanlage.getBauartzulId() != null) {
					getBauartzulIdFeld().setText(this.versickerungsanlage.getBauartzulId());
				}

				if (this.versickerungsanlage.isLandesfoerderungTog() != null) {
					if (this.versickerungsanlage.isLandesfoerderungTog() == true) {
						isLandesfoerderungTogBox().setSelected(true);
					} else {
						isLandesfoerderungTogBox().setSelected(false);
					}
				}

				if (this.versickerungsanlage.isNotueberlaufTog() != null) {
					if (this.versickerungsanlage.isNotueberlaufTog() == true) {
						isNotueberlaufTogBox().setSelected(true);
					} else {
						isNotueberlaufTogBox().setSelected(false);
					}
				}

				if (this.versickerungsanlage.getNotueberlaufZiel() != null) {
					getNotueberlaufZielFeld().setText(this.versickerungsanlage.getNotueberlaufZiel());
				}

				if (this.versickerungsanlage.getDurchlaessigkeit() != null) {
					getDurchlaessigkeitFeld().setValue(this.versickerungsanlage.getDurchlaessigkeit());
				}

				if (this.versickerungsanlage.getFlurabstand() != null) {
					getFlurabstandFeld().setValue(this.versickerungsanlage.getFlurabstand());
				}

				if (this.versickerungsanlage.getGelaendeVerAnlage() != null) {
					getGelaendeVerAnlageFeld().setValue(this.versickerungsanlage.getGelaendeVerAnlage());
				}

				if (this.versickerungsanlage.getAbstGrGrenze() != null) {
					getAbstGrGrenzeFeld().setValue(this.versickerungsanlage.getAbstGrGrenze());
				}

				if (this.versickerungsanlage.getAbstUnterkGebaeude() != null) {
					getAbstUnterkGebaeudeFeld().setValue(this.versickerungsanlage.getAbstUnterkGebaeude());
				}
				if (this.versickerungsanlage.getAbstVerAnlage() != null) {
					getAbstVerAnlageFeld().setValue(this.versickerungsanlage.getAbstVerAnlage());
				}

				switchTypGewBezItems((String) getStationierung3OptBox().getSelectedItem());

				switchAnlagenItems((String) getVerAnlageOptBox().getSelectedItem());

			}
		}
	}

	public void clearForm() {
		getStationierung3OptBox().setSelectedIndex(-1);
		getGewaessernameNs3Feld().setText(null);
		getStationierungsNs3Feld().setValue(null);
		getEinzugsgebietFeld().setValue(null);
		getGewnameStatFeld().setText(null);
		getGewkennzStatBox().setSelectedIndex(-1);
		getGewaessernameAlias3Feld().setText(null);
		getStationierungSt3Feld().setValue(null);
		getVerAnlageOptBox().setSelectedIndex(-1);
		getSonstigesVersFeld().setText(null);
		getBauartzulIdFeld().setText(null);
		isLandesfoerderungTogBox().setSelected(false);
		isNotueberlaufTogBox().setSelected(false);
		getNotueberlaufZielFeld().setText(null);
		getDurchlaessigkeitFeld().setValue(null);
		getFlurabstandFeld().setValue(null);
		getGelaendeVerAnlageFeld().setValue(null);
		getAbstGrGrenzeFeld().setValue(null);
		getAbstUnterkGebaeudeFeld().setValue(null);
		getAbstVerAnlageFeld().setValue(null);

	}

	public void enableAll(boolean enabled) {
		getStationierung3OptBox().setEnabled(enabled);
		getGewaessernameNs3Feld().setEnabled(enabled);
		getStationierungsNs3Feld().setEnabled(enabled);

		getEinzugsgebietFeld().setEnabled(enabled);
		getGewnameStatFeld().setEnabled(enabled);
		getGewkennzStatBox().setEnabled(enabled);
		getGewaessernameAlias3Feld().setEnabled(enabled);
		getStationierungSt3Feld().setEnabled(enabled);
		getVerAnlageOptBox().setEnabled(enabled);
		getSonstigesVersFeld().setEnabled(enabled);
		getBauartzulIdFeld().setEnabled(enabled);
		isLandesfoerderungTogBox().setEnabled(enabled);

		isNotueberlaufTogBox().setEnabled(enabled);
		getNotueberlaufZielFeld().setEnabled(enabled);
		getDurchlaessigkeitFeld().setEnabled(enabled);
		getFlurabstandFeld().setEnabled(enabled);
		getGelaendeVerAnlageFeld().setEnabled(enabled);
		getAbstGrGrenzeFeld().setEnabled(enabled);
		getAbstUnterkGebaeudeFeld().setEnabled(enabled);
		getAbstVerAnlageFeld().setEnabled(enabled);
	}

	private boolean saveGewDaten() {
		boolean success = false;

//		getEinleitungsstelleTab().setObjektValues(this.einleitungsstelle);
		
		if (getStationierung3OptBox().getSelectedItem() == null) {
			this.einleitungsstelle.setStationierung3Opt(null);
		} else {
			String type = getStationierung3OptBox().getSelectedItem().toString();
			switch ((String) type) {
			case "nicht stationiertes Gewässer":
				this.einleitungsstelle.setStationierung3Opt(0);
				break;

			case "stationiertes Gewässer":
				this.einleitungsstelle.setStationierung3Opt(1);
				break;

			case "Grundwasser":
				this.einleitungsstelle.setStationierung3Opt(2);
				if (einleitungsstelle.getVersickerungsanlages().size() == 0) {
					this.versickerungsanlage = new Versickerungsanlage();
					this.versickerungsanlage.setEinleitungsstelle(this.einleitungsstelle);
				} else {
					this.versickerungsanlage = this.einleitungsstelle.getVersickerungsanlages().iterator().next();
				}
				break;

			case "keine Angabe":
				this.einleitungsstelle.setStationierung3Opt(99);
				break;
			}
		}

		String gewaessernameNs3 = this.gewaessernameNs3Feld.getText();
		if ("".equals(gewaessernameNs3)) {
			this.einleitungsstelle.setGewaessernameNs3(null);
		} else {
			this.einleitungsstelle.setGewaessernameNs3(gewaessernameNs3);
		}

		Double stationierungsNs3 = (Double) this.stationierungNs3Feld.getDoubleValue();
		this.einleitungsstelle.setStationierungNs3(stationierungsNs3);

		Double einzugsgebiet = (Double) this.einzugsgebietFeld.getDoubleValue();
		this.einleitungsstelle.setEinzugsgebiet(einzugsgebiet);

		String gewnameStat = this.gewnameStatFeld.getText();
		if ("".equals(gewnameStat)) {
			this.einleitungsstelle.setGewnameStat(null);
		} else {
			this.einleitungsstelle.setGewnameStat(gewnameStat);
		}

		if (gewkennzStatBox.getSelectedItem() == null) {
			this.einleitungsstelle.setGewkennzStat(null);
		} else {
			this.einleitungsstelle.setGewkennzStat(gewkennzStatBox.getSelectedItem().toString());
		}
		
		String gewaessernameAlias3 = this.getGewaessernameAlias3Feld().getText();
		if ("".equals(gewaessernameAlias3)) {
			this.einleitungsstelle.setGewaessernameAlias3(null);
		} else {
			this.einleitungsstelle.setGewaessernameAlias3(gewaessernameAlias3);
		}

		Double stationierungSt3 = (Double) this.stationierungSt3Feld.getDoubleValue();
		this.einleitungsstelle.setStationierungSt3(stationierungSt3);

		if (getVerAnlageOptBox().getSelectedItem() != null)  {
			String type = getVerAnlageOptBox().getSelectedItem().toString();
			switch ((String) type) {
			case "-":
				this.versickerungsanlage.setVerAnlageOpt(null);

			case "Flächenversickerung":
				this.versickerungsanlage.setVerAnlageOpt(1);
				break;

			case "Muldenversickerung":
				this.versickerungsanlage.setVerAnlageOpt(2);
				break;

			case "Rigolenversickerung":
				this.versickerungsanlage.setVerAnlageOpt(3);
				break;

			case "Mulden-Rigolenversickerung":
				this.versickerungsanlage.setVerAnlageOpt(4);
				break;

			case "Schachtversickerung":
				this.versickerungsanlage.setVerAnlageOpt(5);
				break;

			case "Versickerungsbecken":
				this.versickerungsanlage.setVerAnlageOpt(6);
				break;

			case "Sonstiges":
				this.versickerungsanlage.setVerAnlageOpt(7);
				break;
			}

			String sonstigesVers = this.getSonstigesVersFeld().getText();
			if ("".equals(sonstigesVers)) {
				this.versickerungsanlage.setSonstigesVers(null);
			} else {
				this.versickerungsanlage.setSonstigesVers(sonstigesVers);
			}

			String bauartzulId = this.getBauartzulIdFeld().getText();
			if ("".equals(bauartzulId)) {
				this.versickerungsanlage.setBauartzulId(null);
			} else {
				this.versickerungsanlage.setBauartzulId(bauartzulId);
			}

			if (isLandesfoerderungTogBox().isSelected()) {
				this.versickerungsanlage.setLandesfoerderungTog(true);
			} else {
				this.versickerungsanlage.setLandesfoerderungTog(false);
			}

			if (isNotueberlaufTogBox().isSelected()) {
				this.versickerungsanlage.setNotueberlaufTog(true);
			} else {
				this.versickerungsanlage.setNotueberlaufTog(false);
			}

			String notueberlaufZiel = this.getNotueberlaufZielFeld().getText();
			if ("".equals(notueberlaufZiel)) {
				this.versickerungsanlage.setNotueberlaufZiel(null);
			} else {
				this.versickerungsanlage.setNotueberlaufZiel(notueberlaufZiel);
			}

			Double durchlaessigkeit = (Double) this.durchlaessigkeitFeld.getDoubleValue();
			this.versickerungsanlage.setDurchlaessigkeit(durchlaessigkeit);

			Double flurabstand = (Double) this.flurabstandFeld.getDoubleValue();
			this.versickerungsanlage.setFlurabstand(flurabstand);

			Double gelaendeVerAnlage = (Double) this.gelaendeVerAnlageFeld.getDoubleValue();
			this.versickerungsanlage.setGelaendeVerAnlage(gelaendeVerAnlage);

			Double abstGrGrenze = (Double) this.abstGrGrenzeFeld.getDoubleValue();
			this.versickerungsanlage.setAbstGrGrenze(abstGrGrenze);

			Double abstUnterkGebaeude = (Double) this.abstUnterkGebaeudeFeld.getDoubleValue();
			this.versickerungsanlage.setAbstUnterkGebaeude(abstUnterkGebaeude);

			Double abstVerAnlage = (Double) this.abstVerAnlageFeld.getDoubleValue();
			this.versickerungsanlage.setAbstVerAnlage(abstVerAnlage);

			if (this.versickerungsanlage.getAktualDat() != null) {
				this.versickerungsanlage.setAktualDat(this.versickerungsanlage.getAktualDat());
			} else {
				this.versickerungsanlage.setAktualDat(new Date());
			}

			if (this.versickerungsanlage.getErstellDat() != null) {
				this.versickerungsanlage.setErstellDat(this.versickerungsanlage.getErstellDat());
			} else {
				this.versickerungsanlage.setErstellDat(new Date());
			}
			
			versickerungsanlage.merge();

		}

		Set<Versickerungsanlage> anlagen = new HashSet<Versickerungsanlage>();
		anlagen.add(versickerungsanlage);
//		getEinleitungsstelleTab().setObjektValues(einleitungsstelle);
		this.einleitungsstelle.setVersickerungsanlages(anlagen);
		
		if (einleitungsstelle.merge()) {
			success = true;
		}

		return success;
	}

	public void completeObjekt(Einleitungsstelle einleitungsstelle) {

		if (einleitungsstelle.getVersickerungsanlages().size() == 0) {

			this.versickerungsanlage = new Versickerungsanlage();

			this.einleitungsstelle = einleitungsstelle;
			this.versickerungsanlage.setEinleitungsstelle(this.einleitungsstelle);

		}
	}

	private JButton getSaveGewDatenButton() {
		if (this.saveGewDatenButton == null) {
			this.saveGewDatenButton = new JButton("Speichern");

			this.saveGewDatenButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					enableAll(false);
					;
					if (saveGewDaten()) {
						GewaesserdatenPanel.this.hauptModul.getFrame().changeStatus("Gewaessserdaten "
								+ GewaesserdatenPanel.this.einleitungsstelle + " erfolgreich gespeichert.",
								HauptFrame.SUCCESS_COLOR);
					} else {
						GewaesserdatenPanel.this.hauptModul.getFrame()
								.changeStatus("Fehler beim Speichern der Gewaesserdaten!", HauptFrame.ERROR_COLOR);
					}
					GewaesserdatenPanel.this.hauptModul.fillForm();
				}
			});
		}
		return this.saveGewDatenButton;

	}

	public void switchGewKennz(Integer type) {
		if (type == null) {
			return;
		}
		getGewnameStatFeld().setText(MapElkaGewkennz.findByGewkz((Integer) getGewkennzStatBox().getSelectedItem()).getGewname());
	}

	public void switchAnlagenItems(String type) {
		if (type == null) {
			return;
		}
		if (type == "Sonstiges") {
			getSonstigesVersFeld().setEnabled(true);
		} else {
			getSonstigesVersFeld().setEnabled(false);
		}
	}

	public void switchTypGewBezItems(String type) {
		if (type == null) {
			return;
		}

		if (type == "nicht stationiertes Gewässer") {
			// nicht stat. und stat Gew
			gewnameStat1Lb.setVisible(true);
			getGewnameStatFeld().setVisible(true);
			gewkennzStat1Lb.setVisible(true);
			getGewkennzStatBox().setVisible(true);
			einzugsgebietLb.setVisible(true);
			getEinzugsgebietFeld().setVisible(true);
			
			//nicht stat. Gew			
			gewaessernameNs3Lb.setVisible(true);
			getGewaessernameNs3Feld().setVisible(true);
			stationierungNs3Lb.setVisible(true);
			getStationierungsNs3Feld().setVisible(true);
			
			//stat Gew.
			gewaessernameAlias3Lb.setVisible(false);
			getGewaessernameAlias3Feld().setVisible(false);
			stationierungSt3Lb.setVisible(false);
			getStationierungSt3Feld().setVisible(false);

			//Grundwasser
			verAnlageOptLb.setVisible(false);
			getVerAnlageOptBox().setVisible(false);
			sonstigesVersLb.setVisible(false);
			getSonstigesVersFeld().setVisible(false);
			bauartzulIdLb.setVisible(false);
			getBauartzulIdFeld().setVisible(false);
			landesfoerderungTogLb.setVisible(false);
			isLandesfoerderungTogBox().setVisible(false);
			notueberlaufTogLb.setVisible(false);
			isNotueberlaufTogBox().setVisible(false);
			notueberlaufZielLb.setVisible(false);
			getNotueberlaufZielFeld().setVisible(false);
			durchlaessigkeitLb.setVisible(false);
			getDurchlaessigkeitFeld().setVisible(false);
			flurabstandLb.setVisible(false);
			getFlurabstandFeld().setVisible(false);
			gelaendeVerAnlageLb.setVisible(false);
			getGelaendeVerAnlageFeld().setVisible(false);
			abstGrGrenzeLb.setVisible(false);
			getAbstGrGrenzeFeld().setVisible(false);
			abstUnterkGebaeudeLb.setVisible(false);
			getAbstUnterkGebaeudeFeld().setVisible(false);
			abstVerAnlageLb.setVisible(false);
			getAbstVerAnlageFeld().setVisible(false);

		} else if (type == "stationiertes Gewässer") {

			// nicht stat. und stat Gew
			gewnameStat1Lb.setVisible(true);
			getGewnameStatFeld().setVisible(true);
			gewkennzStat1Lb.setVisible(true);
			getGewkennzStatBox().setVisible(true);
			einzugsgebietLb.setVisible(true);
			getEinzugsgebietFeld().setVisible(true);

			// nicht stat. Gew
			gewaessernameNs3Lb.setVisible(false);
			getGewaessernameNs3Feld().setVisible(false);
			stationierungNs3Lb.setVisible(false);
			getStationierungsNs3Feld().setVisible(false);
			
			// stat Gew.
			gewaessernameAlias3Lb.setVisible(true);
			getGewaessernameAlias3Feld().setVisible(true);
			stationierungSt3Lb.setVisible(true);
			getStationierungSt3Feld().setVisible(true);

			verAnlageOptLb.setVisible(false);
			getVerAnlageOptBox().setVisible(false);
			sonstigesVersLb.setVisible(false);
			getSonstigesVersFeld().setVisible(false);
			bauartzulIdLb.setVisible(false);
			getBauartzulIdFeld().setVisible(false);
			landesfoerderungTogLb.setVisible(false);
			isLandesfoerderungTogBox().setVisible(false);
			notueberlaufTogLb.setVisible(false);
			isNotueberlaufTogBox().setVisible(false);
			notueberlaufZielLb.setVisible(false);
			getNotueberlaufZielFeld().setVisible(false);
			durchlaessigkeitLb.setVisible(false);
			getDurchlaessigkeitFeld().setVisible(false);
			flurabstandLb.setVisible(false);
			getFlurabstandFeld().setVisible(false);
			gelaendeVerAnlageLb.setVisible(false);
			getGelaendeVerAnlageFeld().setVisible(false);
			abstGrGrenzeLb.setVisible(false);
			getAbstGrGrenzeFeld().setVisible(false);
			abstUnterkGebaeudeLb.setVisible(false);
			getAbstUnterkGebaeudeFeld().setVisible(false);
			abstVerAnlageLb.setVisible(false);
			getAbstVerAnlageFeld().setVisible(false);

		} else if (type == "Grundwasser") {
			// nicht stat. und stat Gew
			gewnameStat1Lb.setVisible(false);
			getGewnameStatFeld().setVisible(false);
			gewkennzStat1Lb.setVisible(false);
			getGewkennzStatBox().setVisible(false);
			einzugsgebietLb.setVisible(false);
			getEinzugsgebietFeld().setVisible(false);

			//nicht stat. Gew			
			gewaessernameNs3Lb.setVisible(false);
			getGewaessernameNs3Feld().setVisible(false);
			stationierungNs3Lb.setVisible(false);
			getStationierungsNs3Feld().setVisible(false);
			
			//stat Gew.
			gewaessernameAlias3Lb.setVisible(false);
			getGewaessernameAlias3Feld().setVisible(false);
			stationierungSt3Lb.setVisible(false);
			getStationierungSt3Feld().setVisible(false);

			// Grundwasser
			verAnlageOptLb.setVisible(true);
			getVerAnlageOptBox().setVisible(true);
			sonstigesVersLb.setVisible(true);
			getSonstigesVersFeld().setVisible(true);
			bauartzulIdLb.setVisible(true);
			getBauartzulIdFeld().setVisible(true);
			landesfoerderungTogLb.setVisible(true);
			isLandesfoerderungTogBox().setVisible(true);
			notueberlaufTogLb.setVisible(true);
			isNotueberlaufTogBox().setVisible(true);
			notueberlaufZielLb.setVisible(true);
			getNotueberlaufZielFeld().setVisible(true);
			durchlaessigkeitLb.setVisible(true);
			getDurchlaessigkeitFeld().setVisible(true);
			flurabstandLb.setVisible(true);
			getFlurabstandFeld().setVisible(true);
			gelaendeVerAnlageLb.setVisible(true);
			getGelaendeVerAnlageFeld().setVisible(true);
			abstGrGrenzeLb.setVisible(true);
			getAbstGrGrenzeFeld().setVisible(true);
			abstUnterkGebaeudeLb.setVisible(true);
			getAbstUnterkGebaeudeFeld().setVisible(true);
			abstVerAnlageLb.setVisible(true);
			getAbstVerAnlageFeld().setVisible(true);

		} else {
			// nicht stat. und stat Gew
			gewnameStat1Lb.setVisible(false);
			getGewnameStatFeld().setVisible(false);
			gewkennzStat1Lb.setVisible(false);
			getGewkennzStatBox().setVisible(false);

			//nicht stat. Gew			
			gewaessernameNs3Lb.setVisible(false);
			getGewaessernameNs3Feld().setVisible(false);
			stationierungNs3Lb.setVisible(false);
			getStationierungsNs3Feld().setVisible(false);
			einzugsgebietLb.setVisible(false);
			getEinzugsgebietFeld().setVisible(false);

			//stat Gew.
			gewaessernameAlias3Lb.setVisible(false);
			getGewaessernameAlias3Feld().setVisible(false);
			stationierungSt3Lb.setVisible(false);
			getStationierungSt3Feld().setVisible(false);

			verAnlageOptLb.setVisible(false);
			getVerAnlageOptBox().setVisible(false);
			sonstigesVersLb.setVisible(false);
			getSonstigesVersFeld().setVisible(false);
			bauartzulIdLb.setVisible(false);
			getBauartzulIdFeld().setVisible(false);
			landesfoerderungTogLb.setVisible(false);
			isLandesfoerderungTogBox().setVisible(false);
			notueberlaufTogLb.setVisible(false);
			isNotueberlaufTogBox().setVisible(false);
			notueberlaufZielLb.setVisible(false);
			getNotueberlaufZielFeld().setVisible(false);
			durchlaessigkeitLb.setVisible(false);
			getDurchlaessigkeitFeld().setVisible(false);
			flurabstandLb.setVisible(false);
			getFlurabstandFeld().setVisible(false);
			gelaendeVerAnlageLb.setVisible(false);
			getGelaendeVerAnlageFeld().setVisible(false);
			abstGrGrenzeLb.setVisible(false);
			getAbstGrGrenzeFeld().setVisible(false);
			abstUnterkGebaeudeLb.setVisible(false);
			getAbstUnterkGebaeudeFeld().setVisible(false);
			abstVerAnlageLb.setVisible(false);
			getAbstVerAnlageFeld().setVisible(false);
		}

	}

// Getter

	private JComboBox getStationierung3OptBox() {
		if (this.stationierung3OptBox == null) {
			this.stationierung3OptBox = new JComboBox();
			this.stationierung3OptBox.setModel(new DefaultComboBoxModel(this.typGewBezItems));
		}
		return this.stationierung3OptBox;
	}

	private JTextField getGewaessernameNs3Feld() {
		if (this.gewaessernameNs3Feld == null) {
			this.gewaessernameNs3Feld = new LimitedTextField(50);
		}
		return this.gewaessernameNs3Feld;
	}

	private DoubleField getStationierungsNs3Feld() {
		if (this.stationierungNs3Feld == null) {
			this.stationierungNs3Feld = new DoubleField(10);
		}
		return this.stationierungNs3Feld;
	}

	private DoubleField getEinzugsgebietFeld() {
		if (this.einzugsgebietFeld == null) {
			this.einzugsgebietFeld = new DoubleField(10);
		}
		return this.einzugsgebietFeld;
	}

	private JTextField getGewnameStatFeld() {
		if (this.gewnameStatFeld == null) {
			this.gewnameStatFeld = new LimitedTextField(50);
		}
		return this.gewnameStatFeld;
	}

	private JComboBox getGewkennzStatBox() {
		if (this.gewkennzStatBox == null) {
			this.gewkennzStatBox = new JComboBox();
			this.gewkennzStatBox.setModel(new DefaultComboBoxModel(this.mapElkaGewkennz));
		}
		return this.gewkennzStatBox;
	}

	private JTextField getGewaessernameAlias3Feld() {
		if (this.gewaessernameAlias3Feld == null) {
			this.gewaessernameAlias3Feld = new LimitedTextField(50);
		}
		return this.gewaessernameAlias3Feld;
	}

	private DoubleField getStationierungSt3Feld() {
		if (this.stationierungSt3Feld == null) {
			this.stationierungSt3Feld = new DoubleField(10);
		}
		return this.stationierungSt3Feld;
	}

	private JComboBox getVerAnlageOptBox() {
		if (this.verAnlageOptBox == null) {
			this.verAnlageOptBox = new JComboBox();
			this.verAnlageOptBox.setModel(new DefaultComboBoxModel(this.anlagenItems));
		}
		return this.verAnlageOptBox;
	}

	private JTextField getSonstigesVersFeld() {
		if (this.sonstigesVersFeld == null) {
			this.sonstigesVersFeld = new LimitedTextField(50);
		}
		return this.sonstigesVersFeld;

	}

	private JTextField getBauartzulIdFeld() {
		if (this.bauartzulIdFeld == null) {
			this.bauartzulIdFeld = new LimitedTextField(50);
		}
		return this.bauartzulIdFeld;

	}

	private JCheckBox isLandesfoerderungTogBox() {
		if (this.landesfoerderungTogBox == null) {
			this.landesfoerderungTogBox = new JCheckBox();
		}
		return this.landesfoerderungTogBox;
	}

	private JCheckBox isNotueberlaufTogBox() {
		if (this.notueberlaufTogBox == null) {
			this.notueberlaufTogBox = new JCheckBox();
		}
		return this.notueberlaufTogBox;
	}

	private JTextField getNotueberlaufZielFeld() {
		if (this.notueberlaufZielFeld == null) {
			this.notueberlaufZielFeld = new JTextField();
		}
		return this.notueberlaufZielFeld;
	}

	private DoubleField getDurchlaessigkeitFeld() {
		if (this.durchlaessigkeitFeld == null) {
			this.durchlaessigkeitFeld = new DoubleField(10);
		}
		return this.durchlaessigkeitFeld;
	}

	private DoubleField getFlurabstandFeld() {
		if (this.flurabstandFeld == null) {
			this.flurabstandFeld = new DoubleField(10);
		}
		return this.flurabstandFeld;
	}

	private DoubleField getGelaendeVerAnlageFeld() {
		if (this.gelaendeVerAnlageFeld == null) {
			this.gelaendeVerAnlageFeld = new DoubleField(10);
		}
		return this.gelaendeVerAnlageFeld;
	}

	private DoubleField getAbstGrGrenzeFeld() {
		if (this.abstGrGrenzeFeld == null) {
			this.abstGrGrenzeFeld = new DoubleField(10);
		}
		return this.abstGrGrenzeFeld;
	}

	private DoubleField getAbstUnterkGebaeudeFeld() {
		if (this.abstUnterkGebaeudeFeld == null) {
			this.abstUnterkGebaeudeFeld = new DoubleField(10);
		}
		return this.abstUnterkGebaeudeFeld;
	}

	private DoubleField getAbstVerAnlageFeld() {
		if (this.abstVerAnlageFeld == null) {
			this.abstVerAnlageFeld = new DoubleField(10);
		}
		return this.abstVerAnlageFeld;
	}

    public EinleitungsstellePanel getEinleitungsstelleTab() {
        if (einleitungsstelleTab == null) {
            einleitungsstelleTab = new EinleitungsstellePanel(this.hauptModul);
            einleitungsstelleTab.setBorder(Paddings.DIALOG);
        }
        return einleitungsstelleTab;
    }
    
    


}
