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

package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import org.ehcache.core.statistics.LowerCachingTierOperationsOutcome.GetAndRemoveOutcome;
import org.hibernate.criterion.MatchMode;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.Lage;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.Orte;
import de.bielefeld.umweltamt.aui.mappings.basis.Strassen;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.mappings.basis.Wirtschaftszweig;
import de.bielefeld.umweltamt.aui.module.BasisAdresseNeu;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStandorteModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.LongNameComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Dialog zum Bearbeiten eines Betreibers.
 * 
 * @author David Klotz
 */
public class BetreiberEditor extends AbstractBaseEditor {
	private static final long serialVersionUID = -7058333439142990179L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	// Für die Comboboxen beim Bearbeiten

	private JLabel handzeichenLabel;
	private JLabel namenLabel;

	private JTextField anredeFeld;
	private JTextField vornamenFeld;
	private JTextField namenFeld;
	private JTextField nameZusFeld;
	private JTextField kassenzeichenFeld;
	private JTextField strasseFeld;
	private JFormattedTextField hausnrFeld;
	private JTextField hausnrZusFeld;
	private JTextField plzZsFeld;
	private JTextField plzFeld;
	private JTextField ortFeld;
	private JTextField telefonFeld;
	private JTextField telefaxFeld;
	private JTextField emailFeld;
	private JTextField betrBeaufVornameFeld;
	private JTextField betrBeaufNachnameFeld;
	private JTextField revdatumsFeld;
	private JTextField handzeichenAltFeld;
	private JTextField handzeichenNeuFeld;
	private JTextField flurFeld;
	private JTextField flurStkFeld;
	private DoubleField e32Feld;
	private DoubleField n32Feld;
	private JButton ausAblageButton;
	private JComboBox gemarkungBox;
	private JComboBox entwGebBox;
	private JComboBox standortGgBox;
	private JComboBox wEinzugsGebBox;
	private JCheckBox daten_awsvCheck;
	private JCheckBox daten_esatzungCheck;
	private JCheckBox daten_whgCheck;
	private JCheckBox ueberschgebCheck;

	private Lage lage = null;
	private Standort standort = null;
	private Gemarkung[] gemarkungen = null;
	private String[] entwgebiete = null;
	private Standortgghwsg[] standortggs = null;
	private Wassereinzugsgebiet[] wEinzugsgebiete = null;

	private JTextArea bemerkungsArea;

	private JComboBox strassenBox;
	private JTable standorteTabelle;
	private BasisStandorteModel standorteModel;
	private JComboBox wirtschaftszweigBox;

	private Orte[] orte = null;
	private Wirtschaftszweig[] wirtschaftszweige = null;
	private String[] tabstreets = null;
	private String street = null;

	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten eines Betreibers.
	 */
	public BetreiberEditor(Adresse betr, HauptFrame owner) {
		super("Betreiber (" + betr.toString() + ")", betr, owner);
	}

	@Override
	protected JComponent buildContentArea() {

		anredeFeld = new LimitedTextField(100);
		vornamenFeld = new LimitedTextField(100);
		namenFeld = new LimitedTextField(100);
		nameZusFeld = new LimitedTextField(50);
		kassenzeichenFeld = new LimitedTextField(50);
		hausnrFeld = new IntegerField();
		hausnrZusFeld = new LimitedTextField(10);
		plzZsFeld = new LimitedTextField(3, "");
		plzFeld = new JTextField();
		strasseFeld = new JTextField();
		ortFeld = new JTextField();
		telefonFeld = new LimitedTextField(50);
		telefaxFeld = new LimitedTextField(50);
		emailFeld = new LimitedTextField(50);
		betrBeaufVornameFeld = new LimitedTextField(50);
		betrBeaufNachnameFeld = new LimitedTextField(50);
		flurFeld = new LimitedTextField(50);
		flurStkFeld = new LimitedTextField(50);

		e32Feld = new DoubleField(1);
		e32Feld.setValue(new Float(0.0f));
		n32Feld = new DoubleField(1);
		n32Feld.setValue(new Float(0.0f));
		gemarkungBox = new JComboBox();
		entwGebBox = new JComboBox();
		entwGebBox.setEditable(true);
		standortGgBox = new JComboBox();
		wEinzugsGebBox = new JComboBox();

		daten_awsvCheck = new JCheckBox("AwSV");
		daten_esatzungCheck = new JCheckBox("E-Satzung");
		daten_whgCheck = new JCheckBox("WHG");
		ueberschgebCheck = new JCheckBox("");

		revdatumsFeld = new JTextField();
		revdatumsFeld.setEditable(false);
		revdatumsFeld.setFocusable(false);
		revdatumsFeld.setToolTipText("Wird automatisch gesetzt.");
		handzeichenAltFeld = new LimitedTextField(10, "");
		handzeichenAltFeld.setEditable(false);
		handzeichenAltFeld.setFocusable(false);

		handzeichenNeuFeld = new LimitedTextField(10, "");
		handzeichenNeuFeld.setToolTipText("Handzeichen obligatorisch!");

		bemerkungsArea = new LimitedTextArea(2000);
		bemerkungsArea.setLineWrap(true);
		bemerkungsArea.setWrapStyleWord(true);
		JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		wirtschaftszweigBox = new JComboBox();
		wirtschaftszweigBox.setRenderer(new LongNameComboBoxRenderer());

		// Der folgende KeyListener wird benutzt um bei Enter
		// im Handzeichen-Feld (wenn das Feld nicht leer ist)
		// zum Speichern-Button zu springen.
		handzeichenNeuFeld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getSource().equals(handzeichenNeuFeld)) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (handzeichenNeuFeld.getText().equals("")) {
							handzeichenLabel.setForeground(Color.RED);
							handzeichenNeuFeld.requestFocus();
						} else {
							// speichernButton.requestFocus();
						}
					}
				}
			}
		});

		// Ermögliche TAB aus dem Bemerkungs-Feld zu springen
		bemerkungsScroller.getVerticalScrollBar().setFocusable(false);
		bemerkungsScroller.getHorizontalScrollBar().setFocusable(false);
		// This was not used:
		// TabAction tac = new TabAction(bemerkungsArea, handzeichenNeuFeld);

		FormLayout layout = new FormLayout(
				"right:pref, 3dlu, 20dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 10dlu, right:pref, 5dlu, 50dlu, 40dlu", //Spalten
				"pref, 3dlu, " + //-Adresse 
				"pref, 3dlu, " + //3
				"pref, 3dlu, " + //5
				"pref, 3dlu, " + //7
				"pref, 3dlu, " + //9
				"pref, 3dlu, " + //11
				"pref, 3dlu, " + //13
				"pref, 3dlu, " + //15 -Ansprechpartner 
				"pref, 3dlu, " + //17
				"pref, 3dlu, " + //19 -DSGVO
				"pref, 3dlu, " + //21
				"pref, 3dlu, " + //23
				"pref, 3dlu, " + //25
				"pref, 3dlu, " + //27 -Lage
				"pref, 3dlu, " + //29
				"pref, 3dlu, " + //31 
				"pref, 3dlu, " + //33
				"pref, 3dlu, " + //35
				"pref, 3dlu, " + //37 -Bermerkung
				"pref, 3dlu, " + //39
				"pref, 3dlu, " + //41
				"pref, 3dlu, " + //43- Button
				"pref, 3dlu, " + //45 
				"pref, 3dlu, " ); //47 - Button
		 
layout.setRowGroups(new int[][] { { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35,
							37, 39, 41, 43 } });

					PanelBuilder builder = new PanelBuilder(layout);
					CellConstraints cc = new CellConstraints();

//Links oben

		builder.addSeparator("Adresse", cc.xyw(1,1,13));				//Adresse----
		namenLabel = builder.addLabel("Firma/Name:", cc.xy(1, 3));		//Name
					builder.add(namenFeld, cc.xyw(3, 3, 6));
		builder.addLabel("Anrede:", cc.xy(1,5));						//Anrede
					builder.add(anredeFeld, cc.xyw(3, 5, 6));
		builder.addLabel("Vorname:", cc.xy(1,7));						//Vorname
					builder.add(vornamenFeld, cc.xyw(3, 7, 6));		
		builder.addLabel("Zusatz", cc.xy(1,9));							//Zusatz
					builder.add(nameZusFeld, cc.xyw(3, 9, 6));					
		builder.addLabel("Stadt", cc.xy(1,11));
					builder.add(plzZsFeld, cc.xy(3, 11));				//PLZ-Zusatz
					builder.add(plzFeld, cc.xy(4, 11));					//PLZ
					builder.add(ortFeld, cc.xyw(6, 11, 3));				//Stadt					
		builder.addLabel("Straße", cc.xy(1,13));						//Straße
					builder.add(strasseFeld, cc.xyw(3, 13, 2));		
					builder.add(hausnrFeld, cc.xy(6, 13));				//Hausnr
					builder.add(hausnrZusFeld, cc.xy(8, 13));			//HausnrZusatz
		builder.addSeparator("Ansprechpartner", cc.xyw(1,15,8));		//Ansprechpartner--					
		builder.addLabel("Vorname", cc.xy(1,17));						//Vorname
					builder.add(betrBeaufVornameFeld, cc.xyw(3, 17, 6));		
		builder.addLabel("Nachname", cc.xy(1,19));						//Nachname
					builder.add(betrBeaufNachnameFeld, cc.xyw(3, 19, 6));		
		builder.addLabel("Telefon", cc.xy(1, 21));						//Telefon
					builder.add(telefonFeld, cc.xyw(3, 21, 6));				
		builder.addLabel("Telefax", cc.xy(1,23));						//Telefax
					builder.add(telefaxFeld, cc.xyw(3, 23, 6));		
		builder.addLabel("Email", cc.xy(1,25));							//Email
					builder.add(emailFeld, cc.xyw(3, 25, 6));	

//Rechts oben
					
		builder.addLabel("Kassenzeichen", cc.xy(10,3));					//Kassenzeichen
					builder.add(kassenzeichenFeld, cc.xyw(12, 3, 2));	
		builder.addLabel("Wirtschaftszweig", cc.xy(10, 5));				//Wirtscahftszweig
					builder.add(wirtschaftszweigBox, cc.xyw(12, 5, 2));	
		builder.addSeparator("Adresse auswählen", cc.xyw(10, 7, 4)); 	//Adresse auswählen--
		builder.add(getStrassenBox(), cc.xyw(10, 9, 4));				//Drop Straßen
		builder.add(getStandorteScroller(), cc.xywh(10, 11, 4, 7)); 	//Straßen Scroller		
		builder.addSeparator("Datenschutzhinweis", cc.xyw(10, 19, 4));	//DSGVO
		builder.add(daten_awsvCheck, cc.xyw(10, 21, 2));				//AWSV Check
		builder.add(daten_esatzungCheck, cc.xyw(10, 23, 2));			//ESatzung Check
		builder.add(daten_whgCheck, cc.xyw(10, 25, 2));					//WHG Check
			
					
//Links unten

		builder.addSeparator("Lage", cc.xyw(1, 27, 13));				//Lage----
		builder.addLabel("E32", cc.xy(1,29));							//Ostwert
					builder.add(e32Feld, cc.xyw(3, 29, 2));		
		builder.addLabel("N32", cc.xy(1,31));							//Nordwert
					builder.add(n32Feld, cc.xyw(3, 31, 2));							
		builder.add(getAusAblageButton(), cc.xywh(6, 29, 3, 3));		//GISButton						
		builder.addLabel("Standortgegebenheit:", cc.xy(1, 33));			//Satndortgegebenheiten
					builder.add(standortGgBox, cc.xyw(3, 33, 6));		
		builder.addLabel("W.Einzugsgebiet:", cc.xy(1, 35));				//W._Einzugsgebiet
					builder.add(wEinzugsGebBox, cc.xyw(3, 35, 6));
		builder.addSeparator("Bemerkungen", cc.xyw(1, 37, 8));			//BemerkungenScroller
					builder.add(bemerkungsScroller, cc.xywh(1, 39, 8, 3));


//Rechts unten

		builder.addLabel("Entw.-gebiet:", cc.xy(10, 29));				//Entwässerungsgebiet
					builder.add(entwGebBox, cc.xyw(12, 29, 2));
		builder.addLabel("Gemarkung:", cc.xy(10, 31));					//Gemarkung	
					builder.add(gemarkungBox, cc.xyw(12, 31, 2));
		builder.add(ueberschgebCheck, cc.xyw(10, 33, 3));				//Überschwemmungsgebiet
		builder.addSeparator("Letzte Revision", cc.xyw(10, 37, 4));		//Letzte Revision--
		builder.addLabel("Datum:", cc.xy(10, 39));						//Datum
					builder.add(revdatumsFeld, cc.xy(12, 39));
handzeichenLabel = builder.addLabel("Handzeichen:", cc.xy(10, 41));		//Handzeichen
		builder.add(handzeichenAltFeld, cc.xy(12, 41));
		builder.addSeparator("Neue Revision", cc.xyw(10, 43, 4));		//Neue Revison--
handzeichenLabel = builder.addLabel("Handzeichen:", cc.xy(10, 45));
				builder.add(handzeichenNeuFeld, cc.xy(12, 45));					
		


		BetreiberListener dialogListener = new BetreiberListener();

		strasseFeld.addActionListener(dialogListener);
		strassenBox.addActionListener(dialogListener);

		JPanel panel =  builder.getPanel();
		panel.setBorder(Paddings.DIALOG);
		return panel;
	}

	@Override
	protected void fillForm() {
		SwingWorkerVariant worker = new SwingWorkerVariant(this) {

			@Override
			protected void doNonUILogic() throws RuntimeException {
				if (wirtschaftszweige == null) {
					wirtschaftszweige = DatabaseQuery.getWirtschaftszweig();
				}
				if (tabstreets == null) {
					tabstreets = DatabaseQuery.getTabStreets();
				}
				if (gemarkungen == null) {
					gemarkungen = DatabaseQuery.getGemarkungen();
				}
				if (standortggs == null) {
					standortggs = DatabaseQuery.getStandortgghwsg();
				}
				if (entwgebiete == null) {
					entwgebiete = DatabaseQuery.getEntwaesserungsgebiete();
				}
				if (wEinzugsgebiete == null) {
					wEinzugsgebiete = DatabaseQuery.getWassereinzugsgebiet();
				}
			}

			@Override
			protected void doUIUpdateLogic() throws RuntimeException {

				if (tabstreets != null) {
					strassenBox.setModel(new DefaultComboBoxModel(tabstreets));
				}
				if (standorteTabelle != null) {

					standorteModel.setStrasse(null);
					standorteModel.updateList();
					standorteTabelle.setModel(standorteModel);

					standorteTabelle.getColumnModel().getColumn(0).setPreferredWidth(10);
					standorteTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
					standorteTabelle.getColumnModel().getColumn(2).setPreferredWidth(10);
					standorteTabelle.getColumnModel().getColumn(3).setPreferredWidth(7);

				}

				if (wirtschaftszweige != null) {
					wirtschaftszweigBox.setModel(new DefaultComboBoxModel(wirtschaftszweige));
					wirtschaftszweigBox.setSelectedItem(getBetreiber().getWirtschaftszweig());
				}

				anredeFeld.setText(getBetreiber().getBetranrede());
				vornamenFeld.setText(getBetreiber().getBetrvorname());
				namenFeld.setText(getBetreiber().getBetrname());
				namenLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
				namenFeld.setFont(new Font("SansSerif", Font.BOLD, 12));
				nameZusFeld.setText(getBetreiber().getBetrnamezus());
				kassenzeichenFeld.setText(getBetreiber().getKassenzeichen());
				strasseFeld.setText(getBetreiber().getStrasse());
				hausnrFeld.setValue(getBetreiber().getHausnr());
				hausnrZusFeld.setText(getBetreiber().getHausnrzus());
				String plzZs = getBetreiber().getPlzzs();
				if (plzZs != null) {
					plzZs = plzZs.trim();
				}
				plzZsFeld.setText(plzZs);
				plzFeld.setText(getBetreiber().getPlz());
				ortFeld.setText(getBetreiber().getOrt());
				telefonFeld.setText(getBetreiber().getTelefon());
				telefaxFeld.setText(getBetreiber().getTelefax());
				emailFeld.setText(getBetreiber().getEmail());
				strassenBox.setSelectedItem(getBetreiber().getStrasse());

				if (getBetreiber().getDatenschutzAwsv() != null)
					daten_awsvCheck.setSelected(getBetreiber().getDatenschutzAwsv());
				else
					daten_awsvCheck.setSelected(false);

				if (getBetreiber().getDatenschutzEsatzung() != null)
					daten_esatzungCheck.setSelected(getBetreiber().getDatenschutzEsatzung());
				else
					daten_esatzungCheck.setSelected(false);

				if (getBetreiber().getDatenschutzWhg() != null)
					daten_whgCheck.setSelected(getBetreiber().getDatenschutzWhg());
				else
					daten_whgCheck.setSelected(false);

				betrBeaufVornameFeld.setText(getBetreiber().getVornamebetrbeauf());
				betrBeaufNachnameFeld.setText(getBetreiber().getNamebetrbeauf());

				handzeichenAltFeld.setText(getBetreiber().getRevihandz());
				bemerkungsArea.setText(getBetreiber().getBemerkungen());
				
				gemarkungBox.setModel(new DefaultComboBoxModel(gemarkungen));
				standortGgBox.setModel(new DefaultComboBoxModel(standortggs));
				entwGebBox.setModel(new DefaultComboBoxModel(entwgebiete));
				wEinzugsGebBox.setModel(new DefaultComboBoxModel(wEinzugsgebiete));

				Date datum = getBetreiber().getRevidatum();
				revdatumsFeld.setText(AuikUtils.getStringFromDate(datum));

				if (Standort.findByAdresse(getBetreiber()) != null) {
					standort = Standort.findByAdresse(getBetreiber());
				}

				if (standort != null) {
					lage = standort.getLage();
					e32Feld.setValue(standort.getLage().getE32());
					n32Feld.setValue(standort.getLage().getN32());

					if (standort.getLage().getGemarkung() != null) {
						gemarkungBox.setSelectedItem(lage.getGemarkung());
					}
					if (standort.getLage().getStandortgghwsg() != null) {
						standortGgBox.setSelectedItem(lage.getStandortgghwsg());
					}

					if (standort.getLage().getEntgebid() != null) {
						entwGebBox.setSelectedItem(lage.getEntgebid());
					}

					if (standort.getLage().getWassereinzugsgebiet() != null) {
						wEinzugsGebBox.setSelectedItem(lage.getWassereinzugsgebiet());
					}

					if (standort.getLage().isUeberschgeb() != null)
						ueberschgebCheck.setSelected(standort.getLage().isUeberschgeb());
					else
						ueberschgebCheck.setSelected(false);
				}

				frame.clearStatus();
			}
		};
		frame.changeStatus("Beschäftigt...");
		worker.start();
	}

	@Override
	protected boolean canSave() {
		// Eingaben überprüfen:
		if (namenFeld.getText().equals("")) {
			// Der Name darf nicht leer sein
			namenLabel.setForeground(HauptFrame.ERROR_COLOR);
			namenFeld.requestFocus();
			String nameErr = "Der Name darf nicht leer sein!";
			frame.changeStatus(nameErr, HauptFrame.ERROR_COLOR);
			log.debug(nameErr);
			return false;
		} else if (handzeichenNeuFeld.getText().equals("")) {
			// Das Handzeichen darf nicht leer sein
			handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
			handzeichenNeuFeld.requestFocus();
			String handzErr = "Neues Handzeichen erforderlich!";
			frame.changeStatus(handzErr, HauptFrame.ERROR_COLOR);
			log.debug(handzErr);
			return false;
		} else {
			// Wenn die Eingaben korrekt sind
			return true;
		}
	}

	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
	 */
	@Override
	protected boolean doSave() {
		// Anrede
		String anrede = anredeFeld.getText();
		if ("".equals(anrede)) {
			getBetreiber().setBetranrede(null);
		} else {
			getBetreiber().setBetranrede(anrede);
		}
		// Vorname
		String vorname = vornamenFeld.getText();
		if ("".equals(vorname)) {
			getBetreiber().setBetrvorname(null);
		} else {
			getBetreiber().setBetrvorname(vorname);
		}
		// Name
		String name = namenFeld.getText();
		if ("".equals(name)) {
			getBetreiber().setBetrname(null);
		} else {
			getBetreiber().setBetrname(name);
		}
		// Zusatz
		String nameZusatz = nameZusFeld.getText();
		if ("".equals(nameZusatz)) {
			getBetreiber().setBetrnamezus(null);
		} else {
			getBetreiber().setBetrnamezus(nameZusatz);
		}
		// kassenzeichen
		String kasse = kassenzeichenFeld.getText();
		if ("".equals(kasse)) {
			getBetreiber().setKassenzeichen(null);
		} else {
			getBetreiber().setKassenzeichen(kasse);
		}

		// Strasse:
		String strasse = strasseFeld.getText();
		if ("".equals(strasse)) {
			getBetreiber().setStrasse(null);
		} else {
			getBetreiber().setStrasse(strasse);
		}

		// Hausnummer:
		Integer hausnr = ((IntegerField) hausnrFeld).getIntValue();
		getBetreiber().setHausnr(hausnr);

		// Hausnummer-Zusatz:
		String hausnrZus = hausnrZusFeld.getText();
		if ("".equals(hausnrZus)) {
			getBetreiber().setHausnrzus(null);
		} else {
			getBetreiber().setHausnrzus(hausnrZus);
		}

		// PLZ-Zusatz
		String plzZs = plzZsFeld.getText();
		if ("".equals(plzZs)) {
			getBetreiber().setPlzzs(null);
		} else {
			getBetreiber().setPlzzs(plzZs.toUpperCase().trim());
		}

		// PLZ:
		String plz = plzFeld.getText();
		if (plz != null) {
			plz = plz.trim();
			if (plz.equals("")) {
				getBetreiber().setPlz(null);
			} else {
				getBetreiber().setPlz(plz);
			}
		}
		// Ort
		String ort = ortFeld.getText();
		if (ort != null) {
			ort = ort.trim();
			if (ort.equals("")) {
				getBetreiber().setOrt(null);
			} else {
				getBetreiber().setOrt(ort);
			}
		}
		// Telefon
		String telefon = telefonFeld.getText();
		if (telefon != null) {
			telefon = telefon.trim();
			if (telefon.equals("")) {
				getBetreiber().setTelefon(null);
			} else {
				getBetreiber().setTelefon(telefon);
			}
		}
		// Telefax
		String telefax = telefaxFeld.getText();
		if (telefax != null) {
			telefax = telefax.trim();
			if (telefax.equals("")) {
				getBetreiber().setTelefax(null);
			} else {
				getBetreiber().setTelefax(telefax);
			}
		}
		// eMail
		String email = emailFeld.getText();
		if (email != null) {
			email = email.trim();
			if (email.equals("")) {
				getBetreiber().setEmail(null);
			} else {
				getBetreiber().setEmail(email);
			}
		}
		// Betriebsbeauftragter-Vorname
		String betrBeaufVorname = betrBeaufVornameFeld.getText();
		if (betrBeaufVorname != null) {
			betrBeaufVorname = betrBeaufVorname.trim();
			if (betrBeaufVorname.equals("")) {
				getBetreiber().setVornamebetrbeauf(null);
			} else {
				getBetreiber().setVornamebetrbeauf(betrBeaufVorname);
			}
		}
		// Betriebsbeauftragter-Nachname
		String betrBeaufNachname = betrBeaufNachnameFeld.getText();
		if (betrBeaufNachname != null) {
			betrBeaufNachname = betrBeaufNachname.trim();
			if (betrBeaufNachname.equals("")) {
				getBetreiber().setNamebetrbeauf(null);
			} else {
				getBetreiber().setNamebetrbeauf(betrBeaufNachname);
			}
		}
		// Datenschutzhinweise
		getBetreiber().setDatenschutzAwsv(daten_awsvCheck.isSelected());
		getBetreiber().setDatenschutzEsatzung(daten_esatzungCheck.isSelected());
		getBetreiber().setDatenschutzWhg(daten_whgCheck.isSelected());
		// Wirtschaftszweig
		Wirtschaftszweig wizw = (Wirtschaftszweig) wirtschaftszweigBox.getSelectedItem();
		getBetreiber().setWirtschaftszweig(wizw);

		if (Double.parseDouble(e32Feld.getValue().toString()) != 0.0 && Double.parseDouble(n32Feld.getValue().toString()) != 0.0) {
			
			if (standort == null) {
			standort = new Standort();
			lage = new Lage();
			standort.setAdresse(getBetreiber());
			standort.setLage(lage);
			}
			// Gemarkung
			Gemarkung bgem = (Gemarkung) gemarkungBox.getSelectedItem();
			lage.setGemarkung(bgem);

			// Standortgg
			Standortgghwsg stgg = (Standortgghwsg) standortGgBox.getSelectedItem();
			lage.setStandortgghwsg(stgg);

			// Einzugsgebiet
			String ezgb = (String) entwGebBox.getSelectedItem();
			// Nötig, weil getSelectedItem bei editierbarer ComboBox auch
			// NULL
			// liefern kann
			if (ezgb != null) {
				// Weil ich bis jetzt noch keine LimitedComboBox oder so
				// habe...
				if (ezgb.length() > 10) {
					// ... kürze ich hier den String auf 10 Zeichen
					ezgb = ezgb.substring(0, 10);
				}
				ezgb = ezgb.trim();
			}
			lage.setEntgebid(ezgb);

			// Überschwemmungsgebiet
			lage.setUeberschgeb(ueberschgebCheck.isSelected());

			// VAWS-Einzugsgebiet
			Wassereinzugsgebiet wezg = (Wassereinzugsgebiet) wEinzugsGebBox.getSelectedItem();
			lage.setWassereinzugsgebiet(wezg);

			// Flur
			String flur = flurFeld.getText().trim();
			if (flur.equals("")) {
				lage.setFlur(null);
			} else {
				lage.setFlur(flur);
			}

			// Flurstueck
			String flurstk = flurStkFeld.getText().trim();
			if (flurstk.equals("")) {
				lage.setFlurstueck(null);
			} else {
				lage.setFlurstueck(flurstk);
			}

			// Rechtswert
			Float e32Wert = ((DoubleField) e32Feld).getFloatValue();
			lage.setE32(e32Wert);

			// Hochwert
			Float n32Wert = ((DoubleField) n32Feld).getFloatValue();
			lage.setN32(n32Wert);
		}

		// Bemerkungen
		String bemerkungen = bemerkungsArea.getText();
		if (bemerkungen != null) {
			bemerkungen = bemerkungen.trim();
			if (bemerkungen.equals("")) {
				getBetreiber().setBemerkungen(null);
			} else {
				getBetreiber().setBemerkungen(bemerkungen);
			}
		}
		// Handzeichen
		String handzeichen = handzeichenNeuFeld.getText().trim();
		getBetreiber().setRevihandz(handzeichen);

		getBetreiber().setRevidatum(Calendar.getInstance().getTime());

		// frame.changeStatus("Keine Änderungen an Betreiber "+betr.getBetreiberid()+"
		// vorgenommen.");

		if (standort != null) {
			Standort persistentAL = null;
			standort.setAdresse(getBetreiber());
			persistentAL = Standort.merge(standort);

			if (persistentAL != null) {
				// setEditedObject(persistentAL);
				log.debug("Änderungen gespeichert!");
				return true;
			} else {
				return false;
			}
		} else {
			getBetreiber().merge();
		}
		return true;
	}

	/**
	 * Methode liefert die eingegebene oder ausgewählte Straße
	 * 
	 * @return
	 */
	private String getStrasse() {
		String str = "";

		if (strassenBox.getSelectedItem() != null) {
			if (strassenBox.getSelectedItem().getClass() == Strassen.class) {
				Strassen selstrasse = (Strassen) strassenBox.getSelectedItem();
				if (selstrasse != null) {
					str = selstrasse.getStrasse();
				}
			} else if (strassenBox.getSelectedItem().getClass() == String.class) {
				str = (String) strassenBox.getSelectedItem();
			}
		}
		str = str.trim();

		// Weil ich bis jetzt noch keine LimitedComboBox oder so habe...
		if (str.length() > 50) {
			// ... kürze ich hier den String auf 50 Zeichen
			str = str.substring(0, 50);
		}

		return str;
	}

	public Adresse getBetreiber() {
		return (Adresse) getEditedObject();
	}

	private JTable getStandorteTabelle() {

		if (this.standorteModel == null) {
			this.standorteModel = new BasisStandorteModel();

			if (this.standorteTabelle == null) {
				this.standorteTabelle = new JTable(this.standorteModel);

				this.standorteTabelle.getColumnModel().getColumn(0).setPreferredWidth(100);
				this.standorteTabelle.getColumnModel().getColumn(1).setPreferredWidth(10);
				this.standorteTabelle.getColumnModel().getColumn(2).setPreferredWidth(7);

				this.standorteTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				this.standorteTabelle.setColumnSelectionAllowed(false);
				this.standorteTabelle.setRowSelectionAllowed(true);

				this.standorteTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if ((e.getClickCount() == 1) && (e.getButton() == 1)) {
							updateAdresse();
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}
				});

			}
		}
		return this.standorteTabelle;
	}

	private JScrollPane getStandorteScroller() {

		JScrollPane standorteScroller = new JScrollPane(getStandorteTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return standorteScroller;
	}

	public void updateAdresse() {

		log.debug("Start updateAdresse()");
		ListSelectionModel lsm = getStandorteTabelle().getSelectionModel();
		if (!lsm.isSelectionEmpty()) {
			if (lage == null) {
				lage = new Lage();
				standort = new Standort();
				standort.setAdresse(getBetreiber());
				standort.setLage(lage);

				gemarkungBox.setModel(new DefaultComboBoxModel(gemarkungen));
				standortGgBox.setModel(new DefaultComboBoxModel(standortggs));
				entwGebBox.setModel(new DefaultComboBoxModel(entwgebiete));
				wEinzugsGebBox.setModel(new DefaultComboBoxModel(wEinzugsgebiete));
			}

			int selectedRow = lsm.getMinSelectionIndex();
			TabStreets bts = this.standorteModel.getRow(selectedRow);
			log.debug("Standort " + bts.getName() + " (ID" + bts.getAbgleich() + ") angewählt.");
			strasseFeld.setText(bts.getName());
			hausnrFeld.setValue(bts.getHausnr());
			hausnrZusFeld.setText(bts.getHausnrZusatz());
			e32Feld.setValue(bts.getX());
			n32Feld.setValue(bts.getY());
			Strassen stra = DatabaseQuery.findStrasse(strassenBox.getSelectedItem().toString());
			if (stra.getPlz() != null) {
				plzFeld.setText(stra.getPlz());
			}
		}
		log.debug("End updateAdresse()");
	}

	private Component getStrassenBox() {

		strassenBox = new JComboBox();
		strassenBox.setRenderer(new LongNameComboBoxRenderer());

		return strassenBox;
	}

	/**
	 * Ein Listener für die Events des "Betreiber Editor"-Moduls.
	 * 
	 * @author Gerhard Genuit
	 */
	private final class BetreiberListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == strassenBox) {
				standorteModel.setStrasse(strassenBox.getSelectedItem().toString());
				standorteModel.updateList();

			}
		}
	}

	private void readClipboard() {

		Clipboard systemClipboard;
		systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferData = systemClipboard.getContents(null);
		for (DataFlavor dataFlavor : transferData.getTransferDataFlavors()) {
			Object content = null;
			try {
				content = transferData.getTransferData(dataFlavor);
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (content instanceof String) {

				String[] tmp = content.toString().split(",");
				if (tmp.length == 4) {
					String e32AusZeile = tmp[2];
					String n32AusZeile = tmp[3];
					this.e32Feld.setValue(new Float(e32AusZeile.substring(0, 6)));
					this.n32Feld.setValue(new Float(n32AusZeile.substring(0, 7)));
					this.frame.changeStatus("Rechts- und Hochwert eingetragen", HauptFrame.SUCCESS_COLOR);
				} else {
					this.frame.changeStatus("Zwischenablage enthält keine verwertbaren Daten", HauptFrame.ERROR_COLOR);
				}
				break;
			}
		}
	}

	public JButton getAusAblageButton() {
		if (this.ausAblageButton == null) {

			this.ausAblageButton = new JButton("aus QGis");
			this.ausAblageButton.setToolTipText("Rechts- und Hochwert aus Zwischenablage einfügen");
			this.ausAblageButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					readClipboard();
				}
			});
		}

		return this.ausAblageButton;
	}
}