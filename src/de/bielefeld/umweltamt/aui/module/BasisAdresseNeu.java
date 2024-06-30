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
 * Datei:
 * $Id: BasisAdresseNeu.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 12.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/12/01 14:39:05  u633d
 * kleine Korrenkturen
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.11  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.Orte;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.mappings.basis.Wirtschaftszweig;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.module.common.editors.StandortEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStdModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisTabStreetsModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.LongNameComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Modul zum neuen Anlegen eines Betreibers.
 * 
 * @author David Klotz
 */
public class BasisAdresseNeu extends AbstractModul {
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private JButton speichernButton;
	private Standort standort = new Standort();
	private Inhaber inh = new Inhaber();
	private Adresse adrn = new Adresse();

	private JLabel handzeichenLabel;
	private JLabel namenLabel;
	private JLabel kassenzeichenLabel;

	private JTextField anredeFeld;
	private JTextField vornamenFeld;
	private JTextField namenFeld;
	private JTextField nameZusFeld;
	private JTextField kassenzeichenFeld;
	private JTextField strasseFeld;
	private IntegerField hausnrFeld;
	private JTextField hausnrZusFeld;
	private JTextField plzZsFeld;
	private JTextField plzFeld;
	private JTextField ortFeld;
	private JTextField telefonFeld;
	private JTextField telefaxFeld;
	private JTextField emailFeld;
	private JFormattedTextField wassermengeFeld;
	private JTextField betrBeaufVornameFeld;
	private JTextField betrBeaufNachnameFeld;
	private JTextField revdatumsFeld;
	private JTextField handzeichenNeuFeld;
	private JCheckBox daten_awsvCheck = null;
	private JCheckBox daten_whgCheck = null;
	private JCheckBox daten_esatzungCheck = null;
	private JCheckBox ueberschgebCheck;
	private JTextField flurFeld;
	private JTextField flurStkFeld;
	private JButton ausAblageButton;
	private JComboBox gemarkungBox;
	private JComboBox entwGebBox;
	private JComboBox standortGgBox;
	private JComboBox wEinzugsGebBox;
	
	private Gemarkung[] gemarkungen = null;
	private String[] entwgebiete = null;
	private Standortgghwsg[] standortggs = null;
	private Wassereinzugsgebiet[] wEinzugsgebiete = null;

	private JTextArea bemerkungsArea;

	private JComboBox strassenBox;
	private JComboBox wirtschaftszweigBox;

	private Orte[] orte = null;
	private Wirtschaftszweig[] wirtschaftszweige = null;
	private String[] tabstreets = null;
	private String street = null;

	private Action standortLoeschAction;
	private Action standortNeuAction;
	private JPopupMenu standortPopup;

	private JTable adressenTabelle;
	private BasisTabStreetsModel adressenModel;
	private JTable standorteTabelle;
	private BasisStdModel standorteModel;


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	@Override
	public String getName() {
		return "Neue Adresse";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "m_betreiber_neu";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	@Override
	public String getCategory() {
		return "Betriebe";
	}

	/**
	 * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return super.getIcon("filenew32.png");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	@Override
	public JPanel getPanel() {
		if (panel == null) {

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
			wassermengeFeld = new IntegerField();
			betrBeaufVornameFeld = new LimitedTextField(50);
			betrBeaufNachnameFeld = new LimitedTextField(50);
			daten_awsvCheck = new JCheckBox("AwSV");
			daten_esatzungCheck = new JCheckBox("E-Satzung");
			daten_whgCheck = new JCheckBox("WHG");
			ueberschgebCheck = new JCheckBox("Überschwemm.-gebiet");

			flurFeld = new LimitedTextField(50);
			flurStkFeld = new LimitedTextField(50);
			gemarkungBox = new JComboBox();
			entwGebBox = new JComboBox();
//			entwGebBox.setEditable(true);
			standortGgBox = new JComboBox();
			wEinzugsGebBox = new JComboBox();
			
			revdatumsFeld = new JTextField();
			revdatumsFeld.setEditable(false);
			revdatumsFeld.setFocusable(false);
			revdatumsFeld.setToolTipText("Wird automatisch gesetzt.");

			handzeichenNeuFeld = new LimitedTextField(10, "");
			handzeichenNeuFeld.setToolTipText("Handzeichen obligatorisch!");

			bemerkungsArea = new LimitedTextArea(2000);
			bemerkungsArea.setLineWrap(true);
			bemerkungsArea.setWrapStyleWord(true);
			JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			wirtschaftszweigBox = new JComboBox();
			wirtschaftszweigBox.setRenderer(new LongNameComboBoxRenderer());

			JComponent buttonBar = ComponentFactory.buildOKBar(getSpeichernButton());

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
								speichernButton.requestFocus();
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
					"right:pref, 3dlu, 20dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 10dlu, right:pref, 5dlu, 100dlu, 40dlu", // Spalten
					"pref, 3dlu, " + // -Adresse
							"pref, 3dlu, " + // 3
							"pref, 3dlu, " + // 5
							"pref, 3dlu, " + // 7
							"pref, 3dlu, " + // 9
							"pref, 3dlu, " + // 11
							"pref, 3dlu, " + // 13
							"pref, 3dlu, " + // 15 -Ansprechpartner
							"pref, 3dlu, " + // 17
							"pref, 3dlu, " + // 19 -DSGVO
							"pref, 3dlu, " + // 21
							"pref, 3dlu, " + // 23
							"pref, 3dlu, " + // 25
							"pref, 3dlu, " + // 27 -Lage
							"pref, 3dlu, " + // 29
							"pref, 3dlu, " + // 31
							"pref, 3dlu, " + // 33
							"pref, 3dlu, " + // 35
							"pref, 3dlu, " + // 37 -Bermerkung
							"pref, 3dlu, " + // 39
							"pref, 3dlu, " + // 41
							"pref, 3dlu, " + // 43
							"pref, 3dlu, " + // 45
							"pref, 3dlu, " + // 47
							"pref, 3dlu, " + // 49
							"pref, 3dlu, "); // 51 - Button

			layout.setRowGroups(new int[][] {
					{ 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43 } });

			PanelBuilder builder = new PanelBuilder(layout);
			CellConstraints cc = new CellConstraints();

			// Inhaber
			// Links oben

			builder.addSeparator("Inhaber", cc.xyw(1, 1, 8)); // Inhaber----
			namenLabel = builder.addLabel("Firma/Name:", cc.xy(1, 3)); // Name
			builder.add(namenFeld, cc.xyw(3, 3, 6));
			builder.addLabel("Anrede:", cc.xy(1, 5)); // Anrede
			builder.add(anredeFeld, cc.xyw(3, 5, 6));
			builder.addLabel("Vorname:", cc.xy(1, 7)); // Vorname
			builder.add(vornamenFeld, cc.xyw(3, 7, 6));
			builder.addLabel("Zusatz", cc.xy(1, 9)); // Zusatz
			builder.add(nameZusFeld, cc.xyw(3, 9, 6));
			builder.addLabel("Wirtschaftszweig", cc.xy(1, 11)); // Wirtscahftszweig
			builder.add(wirtschaftszweigBox, cc.xyw(3, 11, 6));
			kassenzeichenLabel = builder.addLabel("Kassenzeichen", cc.xy(1, 13)); // Kassenzeichen
			builder.add(kassenzeichenFeld, cc.xyw(3, 13, 6));
			
			// Rechts oben
			
			builder.addSeparator("Ansprechpartner", cc.xyw(10, 1, 4)); // Ansprechpartner--
			builder.addLabel("Vorname", cc.xy(10, 3)); // Vorname
			builder.add(betrBeaufVornameFeld, cc.xyw(12, 3, 2));
			builder.addLabel("Nachname", cc.xy(10, 5)); // Nachname
			builder.add(betrBeaufNachnameFeld, cc.xyw(12, 5, 2));
			builder.addLabel("Telefon", cc.xy(10, 7)); // Telefon
			builder.add(telefonFeld, cc.xyw(12, 7, 2));
			builder.addLabel("Telefax", cc.xy(10, 9)); // Telefax
			builder.add(telefaxFeld, cc.xyw(12, 9, 2));
			builder.addLabel("Email", cc.xy(10, 11)); // Email
			builder.add(emailFeld, cc.xyw(12, 11, 2));
			builder.addLabel("Wassermenge", cc.xy(10, 13)); // Wassermenge
			builder.add(wassermengeFeld, cc.xyw(12, 13, 2));
			
			
			// Adresse
			// Links
			builder.addSeparator("Adresse", cc.xyw(1, 15, 8));
			builder.addLabel("Straße", cc.xy(1, 17)); // Straße
			builder.add(strasseFeld, cc.xyw(3, 17, 2));
			builder.add(hausnrFeld, cc.xy(6, 17)); // Hausnr
			builder.add(hausnrZusFeld, cc.xy(8, 17)); // HausnrZusatz
			builder.addLabel("Stadt", cc.xy(1, 19));
			builder.add(plzZsFeld, cc.xy(3, 19)); // PLZ-Zusatz
			builder.add(plzFeld, cc.xy(4, 19)); // PLZ
			builder.add(ortFeld, cc.xyw(6, 19, 3)); // Stadt
			builder.addLabel("Standortgegebenheit", cc.xy(1, 21));
			builder.add(standortGgBox, cc.xyw(3, 21, 6));
			builder.addLabel("W.-Einzugsgebiet", cc.xy(1, 23));
			builder.add(wEinzugsGebBox, cc.xyw(3, 23, 6));
			builder.addLabel("Entwässerungsgebiet", cc.xy(1, 25));
			builder.add(entwGebBox, cc.xyw(3, 25, 2));
			builder.add(ueberschgebCheck, cc.xyw(6, 25, 3)); // Ueberschgeb Check
			builder.addLabel("Gemarkung", cc.xy(1, 27));
			builder.add(gemarkungBox, cc.xyw(3, 27, 6));
			builder.addLabel("Flur/Flurstücke", cc.xy(1, 29));
			builder.add(flurFeld, cc.xy(3, 29));
			builder.add(flurStkFeld, cc.xyw(4, 29, 5));


			// Rechts

			builder.addSeparator("auswählen", cc.xyw(10, 15, 4)); // Adresse auswählen--
			builder.add(getStrassenBox(), cc.xyw(10, 17, 4)); // Drop Straßen
			builder.add(getAdressenScroller(), cc.xywh(10, 19, 4, 11)); // Straßen Scroller

			// Standort/Lage

			builder.addSeparator("Standort / Lage", cc.xyw(1, 31, 13)); // Standorte----
			builder.add(getStandorteScroller(), cc.xywh(1, 33, 13, 7)); // Standortetabelle

			// DSGVO

			builder.addSeparator("Datenschutzhinweis", cc.xyw(1, 41, 13)); // DSGVO
			builder.add(daten_awsvCheck, cc.xyw(1, 43, 2)); // AWSV Check
			builder.add(daten_esatzungCheck, cc.xyw(5, 43, 2)); // ESatzung Check
			builder.add(daten_whgCheck, cc.xyw(9, 43, 2)); // WHG Check
			
			builder.addSeparator("Bemerkungen", cc.xyw(1, 45, 8)); // Bemerkungen ---
			builder.add(bemerkungsScroller, cc.xywh(1, 47, 8, 3)); // Bemerkungen Scroller
			
			
			
			// Revision
			builder.addSeparator("Revision", cc.xyw(10, 45, 4));
			builder.addLabel("Datum:", cc.xy(10, 47)); // Datum
			builder.add(revdatumsFeld, cc.xyw(12, 47, 2));
			handzeichenLabel = builder.addLabel("Handzeichen:", cc.xy(10, 49));
			builder.add(handzeichenNeuFeld, cc.xyw(12, 49, 2));

			// Buttons
			builder.add(buttonBar, cc.xyw(12, 51, 2));

			BetreiberListener dialogListener = new BetreiberListener();

			strasseFeld.addActionListener(dialogListener);
			strassenBox.addActionListener(dialogListener);

			panel = builder.getPanel();
			panel.setBorder(Paddings.DIALOG);
		}
		return panel;
	}

	private Component getStrassenBox() {

		strassenBox = new JComboBox();
		strassenBox.setRenderer(new LongNameComboBoxRenderer());

		return strassenBox;
	}

	/**
	 * Methode liefert die eingegebene oder ausgewählte Straße
	 * 
	 * @return
	 */
	private String getStrasse() {
		String str = "";
	
		if (strassenBox.getSelectedItem() != null) {
			if (strassenBox.getSelectedItem().getClass() == TabStreets.class) {
				TabStreets selstrasse = (TabStreets) strassenBox.getSelectedItem();
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

	/**
	 * öffnet einen Dialog um einen Betreiber-Datensatz zu bearbeiten.
	 * 
	 * @param betr Der Betreiber
	 */
	public void editStandort(Standort std) {
		StandortEditor editDialog = new StandortEditor(std, this.frame);
		editDialog.setLocationRelativeTo(this.frame);

		editDialog.setVisible(true);

	}

	private JTable getAdressenTabelle() {

		if (this.adressenModel == null) {
			this.adressenModel = new BasisTabStreetsModel();

			if (this.adressenTabelle == null) {
				this.adressenTabelle = new JTable(this.adressenModel);

				this.adressenTabelle.getColumnModel().getColumn(0).setPreferredWidth(10);
				this.adressenTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
				this.adressenTabelle.getColumnModel().getColumn(2).setPreferredWidth(10);
				this.adressenTabelle.getColumnModel().getColumn(3).setPreferredWidth(7);

				this.adressenTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				this.adressenTabelle.setColumnSelectionAllowed(false);
				this.adressenTabelle.setRowSelectionAllowed(true);

				this.adressenTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
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
		return this.adressenTabelle;
	}

	private JTable getStandorteTabelle() {

		if (this.standorteModel == null) {
			this.standorteModel = new BasisStdModel();

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
						if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
							Point origin = e.getPoint();
							int row = getStandorteTabelle().rowAtPoint(origin);

							Standort std = BasisAdresseNeu.this.standorteModel.getRow(row);
							log.debug("Doppelklick auf Zeile " + row);
							editStandort(std);
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {

						if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
							Point origin = e.getPoint();
							int row = getStandorteTabelle().rowAtPoint(origin);
						}else if ((e.getButton() == 3)){
							showStandortPopup(e);
						}
					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}
				});

			}
		}
		return this.standorteTabelle;
	}

	private JScrollPane getAdressenScroller() {

		JScrollPane adressenScroller = new JScrollPane(getAdressenTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return adressenScroller;
	}

	private JScrollPane getStandorteScroller() {

		JScrollPane standorteScroller = new JScrollPane(getStandorteTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return standorteScroller;
	}

    private JButton getSpeichernButton() {
        if (this.speichernButton == null) {
            this.speichernButton = new JButton("Speichern");
            // punktSaveButton.setHorizontalAlignment(JButton.CENTER);
            this.speichernButton.setToolTipText("Speichern");
            this.speichernButton.setEnabled(false);
            this.speichernButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!namenFeld.getText().equals("")) {
                        doSave();
                    }
                }
            });
        }
        return this.speichernButton;
    }

	@Override
	public void show() {
		super.show();
		clearForm();
	}

	public void updateAdresse() {

		log.debug("Start updateAdresse()");
		ListSelectionModel lsm = getAdressenTabelle().getSelectionModel();
		if (!lsm.isSelectionEmpty()) {
			int selectedRow = lsm.getMinSelectionIndex();
			TabStreets bts = this.adressenModel.getRow(selectedRow);
			log.debug("Standort " + bts.getName() + " (ID" + bts.getAbgleich() + ") angewählt.");
			plzFeld.setText(bts.getPlz());
			strasseFeld.setText(bts.getName());
			hausnrFeld.setValue(bts.getHausnr());
			hausnrZusFeld.setText(bts.getHausnrZusatz());
			int adr = getAdressenTabelle().getSelectedRow();
			TabStreets stra = (TabStreets) adressenModel.getObjectAtRow(adr);
			if (stra.getPlz() != null) {
				plzFeld.setText(stra.getPlz());
			}

			Set<Standort> standorts = new HashSet<Standort>();
			standorts.add(standort);
			inh.setStandorts(standorts);
			standort.setE32(bts.getX());
			standort.setN32(bts.getY());
			standort.setBezeichnung("Adresse");
			List std = new ArrayList<Standort>();
			for (Standort x : standorts)
				std.add(x);
			standorteModel.setList(std);

          	this.gemarkungBox.setSelectedIndex(0);
          	this.standortGgBox.setSelectedIndex(0);
          	this.wEinzugsGebBox.setSelectedIndex(0);
          	this.entwGebBox.setSelectedIndex(0);
		}

		this.standorteModel.fireTableDataChanged();

		log.debug("End updateAdresse()");
	}

	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat. Speichern
	 * die Werte des Formulars in einen neuen Standort.
	 */
	private void doSave() {
		// Eingaben überprüfen:
		// Der Name darf nicht leer sein
		if (namenFeld.getText().equals("")) {
			namenLabel.setForeground(HauptFrame.ERROR_COLOR);
			namenFeld.requestFocus();
			String nameErr = "Der Name darf nicht leer sein!";
			frame.changeStatus(nameErr, HauptFrame.ERROR_COLOR);
			log.debug(nameErr);
			// Das Handzeichen darf nicht leer sein
		} else if (handzeichenNeuFeld.getText().equals("")) {
			handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
			handzeichenNeuFeld.requestFocus();
			String handzErr = "Neues Handzeichen erforderlich!";
			frame.changeStatus(handzErr, HauptFrame.ERROR_COLOR);
			log.debug(handzErr);
		} else {
			// Wenn die Eingaben korrekt sind

			setAllEnabled(false);

			// Anrede
			String anrede = anredeFeld.getText();
			if (anrede.equals("")) {
				inh.setAnrede(null);
			} else {
				inh.setAnrede(anrede);
			}
			// Vorame
			String vorname = vornamenFeld.getText();
			if (vorname.equals("")) {
				inh.setVorname(null);
			} else {
				inh.setVorname(vorname);
			}
			// Name
			String name = namenFeld.getText();
			if (name.equals("")) {
				inh.setName(null);
			} else {
				inh.setName(name);
			}
			// Zusatz
			String nameZusatz = nameZusFeld.getText();
			if (nameZusatz.equals("")) {
				inh.setNamezus(null);
			} else {
				inh.setNamezus(nameZusatz);
			}
			// Kassenzeichen
			String kassenzeichen = kassenzeichenFeld.getText();
			if (kassenzeichen.equals("")) {
				inh.setKassenzeichen(null);
			} else {
				inh.setKassenzeichen(kassenzeichen);
			}
		
			// Telefon
			String telefon = telefonFeld.getText().trim();
			if (telefon.equals("")) {
				inh.setTelefon(null);
			} else {
				inh.setTelefon(telefon);
			}
			// Telefax
			String telefax = telefaxFeld.getText().trim();
			if (telefax.equals("")) {
				inh.setTelefax(null);
			} else {
				inh.setTelefax(telefax);
			}
			// eMail
			String email = emailFeld.getText().trim();
			if (email.equals("")) {
				inh.setEmail(null);
			} else {
				inh.setEmail(email);
			}
			// Betriebsbeauftragter-Vorname
			String betrBeaufVorname = betrBeaufVornameFeld.getText().trim();
			if (betrBeaufVorname.equals("")) {
				inh.setVornamebetrbeauf(null);
			} else {
				inh.setVornamebetrbeauf(betrBeaufVorname);
			}
			// Betriebsbeauftragter-Nachname
			String betrBeaufNachname = betrBeaufNachnameFeld.getText().trim();
			if (betrBeaufNachname.equals("")) {
				inh.setNamebetrbeauf(null);
			} else {
				inh.setNamebetrbeauf(betrBeaufNachname);
			}
			// Wassermenge:
			Integer wassermenge = ((IntegerField) wassermengeFeld).getIntValue();
			inh.setWassermenge(wassermenge);
			// Wirtschaftszweig
			Wirtschaftszweig wizw = (Wirtschaftszweig) wirtschaftszweigBox.getSelectedItem();
			inh.setWirtschaftszweig(wizw);

			// Bemerkungen
			String bemerkungen = bemerkungsArea.getText().trim();
			if (bemerkungen.equals("")) {
				inh.setBemerkungen(null);
			} else {
				inh.setBemerkungen(bemerkungen);
			}
			// Datenschutzhinweise
			inh.setDatenschutzAwsv(daten_awsvCheck.isSelected());
			inh.setDatenschutzEsatzung(daten_esatzungCheck.isSelected());
			inh.setDatenschutzWhg(daten_whgCheck.isSelected());
			
			// Zuerst pruefen, ob es schon eine Adesse mit der gleichen
			// strasse, hausnr, hausnrzus und plz gibt
			
			if (DatabaseQuery.findAdressen(strasseFeld.getText(), hausnrFeld.getIntValue(), 
					hausnrZusFeld.getText(), plzFeld.getText()).iterator().hasNext()) {
				adrn = DatabaseQuery.findAdressen(strasseFeld.getText(), hausnrFeld.getIntValue(), 
						hausnrZusFeld.getText(), plzFeld.getText()).iterator().next();
			} else {
				
				// Strasse:
				String strasse = strasseFeld.getText();
				if ("".equals(strasse)) {
					adrn.setStrasse(null);
				} else {
					adrn.setStrasse(strasse);
				}
				// Hausnummer:
				Integer hausnr;
				try {
					hausnrFeld.commitEdit();
				} catch (ParseException e1) {
					hausnrFeld.setValue(new Integer(0));
				}
				if (hausnrFeld.getValue() instanceof Long) {
					hausnr = new Integer(((Long) hausnrFeld.getValue()).intValue());
				} else {
					hausnr = (Integer) hausnrFeld.getValue();
				}
				adrn.setHausnr(hausnr);
				// Hausnummer-Zusatz:
				String hausnrZus = hausnrZusFeld.getText();
				if (hausnrZus.equals("")) {
					adrn.setHausnrzus(null);
				} else {
					adrn.setHausnrzus(hausnrZus);
				}
				// PLZ-Zusatz
				String plzZs = plzZsFeld.getText();
				if (plzZs.equals("")) {
					adrn.setPlzzs(null);
				} else {
					adrn.setPlzzs(plzZs.toUpperCase().trim());
				}
				// PLZ:
				String plz = plzFeld.getText().trim();
				if (plz.equals("")) {
					adrn.setPlz(null);
				} else {
					adrn.setPlz(plz);
				}
				// Orte
				String ort = ortFeld.getText().trim();
				if (ort.equals("")) {
					adrn.setOrt(null);
				} else {
					adrn.setOrt(ort);
				}
				adrn.setUeberschgeb(ueberschgebCheck.isSelected());
				
				// Gemarkung
				Gemarkung bgem = (Gemarkung) gemarkungBox.getSelectedItem();
				adrn.setGemarkung(bgem);
				
				// Flur
				String flur = flurFeld.getText().trim();
				if (flur.equals("")) {
					adrn.setFlur(null);
				} else {
					adrn.setFlur(flur);
				}

				// Flurstueck
				String flurstk = flurStkFeld.getText().trim();
				if (flurstk.equals("")) {
					adrn.setFlurstueck(null);
				} else {
					adrn.setFlurstueck(flurstk);
				}

				// Standortgg
				Standortgghwsg stgg = (Standortgghwsg) standortGgBox.getSelectedItem();
				adrn.setStandortgghwsg(stgg);

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
				adrn.setEntgebid(ezgb);
				adrn.setUeberschgeb(ueberschgebCheck.isSelected());

				// VAWS-Einzugsgebiet
				Wassereinzugsgebiet wezg = (Wassereinzugsgebiet) wEinzugsGebBox.getSelectedItem();
				adrn.setWassereinzugsgebiet(wezg);

				
			}

			inh.setRevidatum(Calendar.getInstance().getTime());
			inh.setRevihandz(handzeichenNeuFeld.getText().trim());

			adrn = Adresse.merge(adrn);
			inh.setAdresse(adrn);
			inh = Inhaber.merge(inh);
			standort.setInhaber(inh);
			standort = Standort.merge(standort);

			if (standort != null) {
				frame.changeStatus("Neuer Betreiber " + standort.getId() + " erfolgreich gespeichert.",
						HauptFrame.SUCCESS_COLOR);

				// Wenn wir vom Objekt anlegen kommen,
				if (manager.getSettingsManager().getBoolSetting("auik.imc.return_to_objekt_betreiber")) {
					manager.getSettingsManager().setSetting("auik.imc.use_betreiber",
							standort.getInhaber().getId().intValue(), false);
					manager.getSettingsManager().removeSetting("auik.imc.return_to_objekt_betreiber");
					// ... kehren wir direkt dorthin zurück:
					manager.switchModul("m_objekt_bearbeiten");
				} else if (manager.getSettingsManager().getBoolSetting("auik.imc.return_to_objekt_standort")) {
					manager.getSettingsManager().setSetting("auik.imc.use_standort", standort.getId().intValue(),
							false);
					manager.getSettingsManager().removeSetting("auik.imc.return_to_objekt_standort");
					// ... kehren wir direkt dorthin zurück:
					manager.switchModul("m_objekt_bearbeiten");
				} else {
					// Sonst einfach das Formular zurücksetzen
					clearForm();
				}
			}

			if (inh != null) {
				frame.changeStatus("Neuer Inhaber " + inh.getId() + " erfolgreich gespeichert.",
						HauptFrame.SUCCESS_COLOR);

				// Wenn wir vom Objekt anlegen kommen,
				if (manager.getSettingsManager().getBoolSetting("auik.imc.return_to_objekt_betreiber")) {
					manager.getSettingsManager().setSetting("auik.imc.use_betreiber", inh.getId().intValue(), false);
					manager.getSettingsManager().removeSetting("auik.imc.return_to_objekt_betreiber");
					// ... kehren wir direkt dorthin zurück:
					manager.switchModul("m_objekt_bearbeiten");
				} else if (manager.getSettingsManager().getBoolSetting("auik.imc.return_to_objekt_standort")) {
					manager.getSettingsManager().setSetting("auik.imc.use_standort", standort.getId().intValue(), false);
					manager.getSettingsManager().removeSetting("auik.imc.return_to_objekt_standort");
					// ... kehren wir direkt dorthin zurück:
					manager.switchModul("m_objekt_bearbeiten");
				} else {
					// Sonst einfach das Formular zurücksetzen
					clearForm();
				}
			}

			else {
				frame.changeStatus("Konnte Betreiber nicht speichern!", Color.RED);
				log.debug("Konnte nicht speichern");
			}

		}
	}

	private void clearForm() {
		setAllEnabled(false);
		// frame.changeStatus("Beschäftigt...");

		SwingWorkerVariant worker = new SwingWorkerVariant(panel) {

			@Override
			protected void doNonUILogic() throws RuntimeException {

				if (wirtschaftszweige == null) {
					wirtschaftszweige = DatabaseQuery.getWirtschaftszweig();
				}
				if (tabstreets == null) {
					tabstreets = DatabaseQuery.getTabStreets();
				}
				if (gemarkungen == null)
				{
					gemarkungen = DatabaseQuery.getGemarkungen();
				}
				if (standortggs == null)
				{
					standortggs = DatabaseQuery.getStandortgghwsg();
				}
				if (entwgebiete == null)
				{
					entwgebiete = DatabaseQuery.getEntwaesserungsgebiete();
				}
				if (wEinzugsgebiete == null)
				{
					wEinzugsgebiete = DatabaseQuery.getWassereinzugsgebiet();
				}
				
			}

			@Override
			protected void doUIUpdateLogic() throws RuntimeException {
				
				adrn = new Adresse();
				inh = new Inhaber();
				standort = new Standort();
				
				if (wirtschaftszweige != null) {
					wirtschaftszweigBox.setModel(new DefaultComboBoxModel(wirtschaftszweige));
				}
				if (tabstreets != null) {
					strassenBox.setModel(new DefaultComboBoxModel(tabstreets));
				}
				if (standorteTabelle != null) {

					standorteModel.updateList();
					standorteTabelle.setModel(standorteModel);

				if (gemarkungen != null)
				{
					gemarkungBox
							.setModel(new DefaultComboBoxModel(gemarkungen));
				}
				if (standortggs != null)
				{
					standortGgBox
							.setModel(new DefaultComboBoxModel(standortggs));
				}

				if (entwgebiete != null)
				{
					entwGebBox.setModel(new DefaultComboBoxModel(entwgebiete));
				}

				if (wEinzugsgebiete != null)
				{
					wEinzugsGebBox.setModel(new DefaultComboBoxModel(
							wEinzugsgebiete));
				}

				ortFeld.setText("Bielefeld");
				strasseFeld.setText("");
				hausnrFeld.setValue(null);
				hausnrZusFeld.setText("");
				plzZsFeld.setText("D");
				plzFeld.setText("");
				anredeFeld.setText("");
				vornamenFeld.setText("");
				namenFeld.setText("");
				namenFeld.setFont(new Font("SansSerif", Font.BOLD, 12));
				namenLabel.setForeground(panel.getForeground());
				namenLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
				nameZusFeld.setText("");
				kassenzeichenFeld.setText("");
				telefonFeld.setText("");
				telefaxFeld.setText("");
				emailFeld.setText("");
				daten_awsvCheck.setSelected(false);
				daten_esatzungCheck.setSelected(false);
				daten_whgCheck.setSelected(false);
				ueberschgebCheck.setSelected(false);
				wassermengeFeld.setValue(null);
				betrBeaufNachnameFeld.setText("");
				betrBeaufVornameFeld.setText("");
				bemerkungsArea.setText("");

				revdatumsFeld.setText(DateUtils.getCurrentDateString());
				handzeichenNeuFeld.setText("");
				handzeichenLabel.setForeground(panel.getForeground());

				setAllEnabled(true);
				// frame.clearStatus();
				log.debug("(" + getIdentifier() + ".clearForm) " + "Formular zurückgesetzt");
				}
			}
		};
		worker.start();
	}

	/**
	 * Aktiviert oder deaktiviert alle Felder im Formular.
	 * 
	 * @param enabled <code>true</true>, wenn die Felder aktiviert werden sollen, sonst <code>false</code>
	 */
	private void setAllEnabled(boolean enabled) {
		speichernButton.setEnabled(enabled);

		strassenBox.setEnabled(enabled);
		strasseFeld.setEnabled(enabled);
		ortFeld.setEnabled(enabled);
		wirtschaftszweigBox.setEnabled(enabled);

		hausnrFeld.setEditable(enabled);
		hausnrZusFeld.setEditable(enabled);
		plzFeld.setEditable(enabled);
		plzZsFeld.setEditable(enabled);
		anredeFeld.setEditable(enabled);
		vornamenFeld.setEditable(enabled);
		namenFeld.setEditable(enabled);
		nameZusFeld.setEditable(enabled);
		kassenzeichenFeld.setEditable(enabled);
		ortFeld.setEditable(enabled);
		telefonFeld.setEditable(enabled);
		telefaxFeld.setEditable(enabled);
		emailFeld.setEditable(enabled);
		wassermengeFeld.setEnabled(enabled);
		betrBeaufVornameFeld.setEditable(enabled);
		betrBeaufNachnameFeld.setEditable(enabled);

		bemerkungsArea.setEnabled(enabled);
		bemerkungsArea.setEditable(enabled);

		handzeichenNeuFeld.setEditable(enabled);
	}

	/**
	 * Ein Listener für die Events des "Neuer Betreiber"-Moduls.
	 * 
	 * @author David Klotz
	 */
	private final class BetreiberNeuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == speichernButton) {
				log.debug("(" + BasisAdresseNeu.this.getIdentifier() + ") " + "Speichern gedrückt!");
				doSave();
			}

//			else if (e.getSource() == orteBox)
//			{
//				reloadStrassen();
//			}
			else if (e.getSource() == strassenBox) {
//				standorteModel.setStrasse(strassenBox.getSelectedItem().toString());
				standorteModel.updateList();

			}
		}

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
				adressenModel.setStrasse(strassenBox.getSelectedItem().toString());
				adressenModel.updateList();

			}
		}

	}
	

	private void showStandortPopup(MouseEvent e) {
	    if (standortPopup == null) {
	    	standortPopup = new JPopupMenu("Standort");
	        JMenuItem loeschItem = new JMenuItem(getStandortLoeschAction());
	        standortPopup.add(loeschItem);
	        JMenuItem neuItem = new JMenuItem(getStandortNeuAction());
	        standortPopup.add(neuItem);
	    }
	
	    if (e != null) {
	        Point origin = e.getPoint();
	        int row = standorteTabelle.rowAtPoint(origin);
	
	        // Löschen ist natürlich nur möglich,
	        // wenn wirklich eine Zeile ausgewählt ist:
	        if (row != -1) {
	            getStandortLoeschAction().setEnabled(true);
	            standorteTabelle.setRowSelectionInterval(row, row);
	        } else {
	        	getStandortLoeschAction().setEnabled(false);
	        }
	
	        // Das Menü zeigen wir aber immer an, zum neu Anlegen eines
	        // Abscheiders
	        standortPopup.show(e.getComponent(), e.getX(), e.getY());
	    }
	}

    private Action getStandortLoeschAction() {
        if (standortLoeschAction == null) {
        	standortLoeschAction = new AbstractAction("Löschen") {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getStandorteTabelle().getSelectedRow();
                    if (row != -1
                        && getStandorteTabelle().getEditingRow() == -1) {
                    	Standort standort = standorteModel
                            .getRow(row);

                        if (GUIManager
                            .getInstance()
                            .showQuestion(
                                "Soll der Standort "
                                    + standort
                                    + " wirklich gelöscht werden?",
                                "Löschen bestätigen")) {
                        	standorteModel.removeRow(row);
                            log.debug("Standort " + standort
                                + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von " + standort
                                + " wurde abgebrochen!");
                        }
                    }
                }
            };
            standortLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_L));
            standortLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return standortLoeschAction;
    }

    private Action getStandortNeuAction() {
        if (standortNeuAction == null) {
        	standortNeuAction = new AbstractAction("Neuer Standort") {
                private static final long serialVersionUID = 4388335905488653435L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    Standort neuerStandort = new Standort();
                    neuerStandort.setInhaber(inh);
                    editStandort(neuerStandort);
                }
            };
            standortNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_N));
            // abscheiderNeuAction.putValue(Action.ACCELERATOR_KEY,
            // KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return standortNeuAction;
    }
	
	
}
