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
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
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

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.mappings.basis.Wirtschaftszweig;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStdModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisTabStreetsModel;
import de.bielefeld.umweltamt.aui.module.common.AdresseChooser;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.LongNameComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Dialog zum Bearbeiten eines Betreibers.
 *
 * @author David Klotz
 */
public class BetreiberEditor extends AbstractApplyEditor {
	private static final long serialVersionUID = -7058333439142990179L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private Adresse adresse = null;
	private Inhaber inhaber = null;

	// Für die Comboboxen beim Bearbeiten

	private JLabel handzeichenLabel;
	private JLabel namenLabel;
	private JLabel kassenzeichenLabel;

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
	private JFormattedTextField wassermengeFeld;
	private JTextArea bemerkungsArea;
	private JCheckBox daten_awsvCheck;
	private JCheckBox daten_esatzungCheck;
	private JCheckBox daten_whgCheck;
	private JCheckBox ueberschgebCheck;
	private JComboBox<String> strassenBox;
	private JComboBox<Wirtschaftszweig> wirtschaftszweigBox;
	private JComboBox<Gemarkung> gemarkungBox;
	private JComboBox<String> entwGebBox;
	private JComboBox<Standortgghwsg> standortGgBox;
	private JComboBox<Wassereinzugsgebiet> wEinzugsGebBox;


	private Gemarkung[] gemarkungen = null;
	private String[] entwgebiete = null;
	private Standortgghwsg[] standortggs = null;
	private Wassereinzugsgebiet[] wEinzugsgebiete = null;
	private Wirtschaftszweig[] wirtschaftszweige = null;
	private String[] tabstreets = null;

    private Action standortLoeschAction;
    private Action standortNeuAction;
    private JPopupMenu standortPopup;

	private JTable adressenTabelle;
	private BasisTabStreetsModel adressenModel;
	private JTable standorteTabelle;
	private BasisStdModel standorteModel;
	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten eines Betreibers.
	 */
	public BetreiberEditor(Inhaber betreiber, HauptFrame owner) {
		super("Betreiber (" + betreiber.toString() + ")", betreiber, owner);
		this.inhaber = betreiber;
		this.adresse = betreiber.getAdresse();
	}

	public BetreiberEditor(Standort standort, Inhaber inhaber, HauptFrame owner) {
		super("Standort (" + standort.toString() + ")", standort, owner);
		this.inhaber = inhaber;
		this.adresse = inhaber.getAdresse();

	}

	@SuppressWarnings("deprecation")
	@Override
	protected JComponent buildContentArea() {
		ResourceBundle i18n = SettingsManager.getInstance().getI18nBundle();

		anredeFeld = new LimitedTextField(100);
		vornamenFeld = new LimitedTextField(100);
		namenFeld = new LimitedTextField(100);
		nameZusFeld = new LimitedTextField(50);
		kassenzeichenFeld = new LimitedTextField(50);
		hausnrFeld = new IntegerField();
		hausnrFeld.setEnabled(false);
		hausnrZusFeld = new LimitedTextField(10);
		hausnrZusFeld.setEnabled(false);
		plzZsFeld = new LimitedTextField(3, "");
		plzZsFeld.setEnabled(false);
		plzFeld = new JTextField();
		plzFeld.setEnabled(false);
		strasseFeld = new JTextField();
		strasseFeld.setEnabled(false);
		ortFeld = new JTextField();
		ortFeld.setEnabled(false);
		telefonFeld = new LimitedTextField(50);
		telefaxFeld = new LimitedTextField(50);
		emailFeld = new LimitedTextField(50);
		wassermengeFeld = new IntegerField();
		betrBeaufVornameFeld = new LimitedTextField(50);
		betrBeaufNachnameFeld = new LimitedTextField(50);

		daten_awsvCheck = new JCheckBox(i18n.getString("editor.betreiber.awsv"));
		daten_esatzungCheck = new JCheckBox(i18n.getString("editor.betreiber.eSatzung"));
		daten_whgCheck = new JCheckBox(i18n.getString("editor.betreiber.whg"));
		ueberschgebCheck = new JCheckBox(i18n.getString("editor.betreiber.uberschwemmGeb"));

		revdatumsFeld = new JTextField();
		revdatumsFeld.setEditable(false);
		revdatumsFeld.setFocusable(false);
		revdatumsFeld.setToolTipText(i18n.getString("tooltip.autoSet"));
		handzeichenAltFeld = new LimitedTextField(10, "");
		handzeichenAltFeld.setEditable(false);
		handzeichenAltFeld.setFocusable(false);

		handzeichenNeuFeld = new LimitedTextField(10, "");
		handzeichenNeuFeld.setToolTipText(i18n.getString("tooltip.sig_mandatory"));

		flurFeld = new LimitedTextField(50);
		flurStkFeld = new LimitedTextField(50);
		gemarkungBox = new JComboBox<Gemarkung>();
		entwGebBox = new JComboBox<String>();
		standortGgBox = new JComboBox<Standortgghwsg>();
		wEinzugsGebBox = new JComboBox<Wassereinzugsgebiet>();

		bemerkungsArea = new LimitedTextArea(2000);
		bemerkungsArea.setLineWrap(true);
		bemerkungsArea.setWrapStyleWord(true);
		JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		wirtschaftszweigBox = new JComboBox<Wirtschaftszweig>();
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
						}
					}
				}
			}
		});

		// Ermögliche TAB aus dem Bemerkungs-Feld zu springen
		bemerkungsScroller.getVerticalScrollBar().setFocusable(false);
		bemerkungsScroller.getHorizontalScrollBar().setFocusable(false);

		FormLayout layout = new FormLayout(
				"right:pref, 3dlu, 20dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 10dlu, right:pref, 5dlu, 100dlu, 40dlu", // Spalten
				"pref, 3dlu, " +
						"pref, 3dlu, " + // 3
						"pref, 3dlu, " + // 5
						"pref, 3dlu, " + // 7
						"pref, 3dlu, " + // 9
						"pref, 3dlu, " + // 11
						"pref, 3dlu, " + // 13
						"pref, 3dlu, " + // 15
						"pref, 3dlu, " + // 17
						"pref, 3dlu, " + // 19
						"pref, 3dlu, " + // 21
						"pref, 3dlu, " + // 23
						"pref, 3dlu, " + // 25
						"pref, 3dlu, " + // 27
						"pref, 3dlu, " + // 29
						"pref, 3dlu, " + // 31
						"pref, 3dlu, " + // 33
						"pref, 3dlu, " + // 35
						"pref, 3dlu, " + // 37
						"pref, 3dlu, " + // 39
						"pref, 3dlu, " + // 41
						"pref, 3dlu, " + // 43
						"pref, 3dlu, " + // 45
						"pref, 3dlu, " + // 47
						"pref, 3dlu, " + // 49
						"pref, 3dlu, " + // 51
						"pref, 3dlu, " + // 53
						"pref, 3dlu, " + // 55
						"pref, 3dlu, " + // 57
						"pref, 3dlu, "); // 59

		layout.setRowGroups(new int[][] {
				{ 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59 } });

		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		// Inhaber
		// Links oben

		builder.addSeparator(i18n.getString("editor.betreiber.separator.owner"), cc.xyw(1, 1, 8)); // Adresse----
		namenLabel = builder.addLabel(i18n.getString("editor.betreiber.company_name"), cc.xy(1, 3)); // Name
		builder.add(namenFeld, cc.xyw(3, 3, 6));
		builder.addLabel(i18n.getString("editor.betreiber.salutation"), cc.xy(1, 5)); // Anrede
		builder.add(anredeFeld, cc.xyw(3, 5, 6));
		builder.addLabel(i18n.getString("editor.betreiber.first_name"), cc.xy(1, 7)); // Vorname
		builder.add(vornamenFeld, cc.xyw(3, 7, 6));
		builder.addLabel(i18n.getString("editor.betreiber.addition"), cc.xy(1, 9)); // Zusatz
		builder.add(nameZusFeld, cc.xyw(3, 9, 6));
		builder.addLabel(i18n.getString("editor.betreiber.industry"), cc.xy(1, 11)); // Wirtscahftszweig
		builder.add(wirtschaftszweigBox, cc.xyw(3, 11, 6));
		kassenzeichenLabel = builder.addLabel(i18n.getString("editor.betreiber.ref_number"), cc.xy(1, 13)); // Kassenzeichen
		builder.add(kassenzeichenFeld, cc.xyw(3, 13, 6));

		// Rechts oben

		builder.addSeparator(i18n.getString("editor.betreiber.separator.contact"), cc.xyw(10, 1, 4)); // Ansprechpartner--
		builder.addLabel(i18n.getString("editor.betreiber.contact_first_name"), cc.xy(10, 3)); // Vorname
		builder.add(betrBeaufVornameFeld, cc.xyw(12, 3, 2));
		builder.addLabel(i18n.getString("editor.betreiber.contact_last_name"), cc.xy(10, 5)); // Nachname
		builder.add(betrBeaufNachnameFeld, cc.xyw(12, 5, 2));
		builder.addLabel(i18n.getString("editor.betreiber.telephone"), cc.xy(10, 7)); // Telefon
		builder.add(telefonFeld, cc.xyw(12, 7, 2));
		builder.addLabel(i18n.getString("editor.betreiber.fax"), cc.xy(10, 9)); // Telefax
		builder.add(telefaxFeld, cc.xyw(12, 9, 2));
		builder.addLabel(i18n.getString("editor.betreiber.mail"), cc.xy(10, 11)); // Email
		builder.add(emailFeld, cc.xyw(12, 11, 2));
		builder.addLabel(i18n.getString("editor.betreiber.water_amount"), cc.xy(10, 13)); // Wassermenge
		builder.add(wassermengeFeld, cc.xyw(12, 13, 2));


		// Adresse
		// Links
		builder.addSeparator(i18n.getString("editor.betreiber.separator.address"), cc.xyw(1, 15, 8));
		builder.addLabel(i18n.getString("editor.betreiber.street"), cc.xy(1, 17)); // Straße
		builder.add(strasseFeld, cc.xyw(3, 17, 2));
		builder.add(hausnrFeld, cc.xy(6, 17)); // Hausnr
		builder.add(hausnrZusFeld, cc.xy(8, 17)); // HausnrZusatz
		builder.addLabel(i18n.getString("editor.betreiber.city"), cc.xy(1, 19));
		builder.add(plzZsFeld, cc.xy(3, 19)); // PLZ-Zusatz
		builder.add(plzFeld, cc.xy(4, 19)); // PLZ
		builder.add(ortFeld, cc.xyw(6, 19, 3)); // Stadt
		builder.addLabel(i18n.getString("editor.betreiber.location_cond"), cc.xy(1, 21));
		builder.add(standortGgBox, cc.xyw(3, 21, 6));
		builder.addLabel(i18n.getString("editor.betreiber.area"), cc.xy(1, 23));
		builder.add(wEinzugsGebBox, cc.xyw(3, 23, 6));
		builder.addLabel(i18n.getString("editor.betreiber.drain_area"), cc.xy(1, 25));
		builder.add(entwGebBox, cc.xyw(3, 25, 2));
		builder.add(ueberschgebCheck, cc.xyw(6, 25, 3)); // Ueberschgeb Check
		builder.addLabel(i18n.getString("editor.betreiber.marking"), cc.xy(1, 27));
		builder.add(gemarkungBox, cc.xyw(3, 27, 6));
		builder.addLabel(i18n.getString("editor.betreiber.parcel"), cc.xy(1, 29));
		builder.add(flurFeld, cc.xy(3, 29));
		builder.add(flurStkFeld, cc.xyw(4, 29, 5));


		// Rechts

		builder.addSeparator(i18n.getString("label.select"), cc.xyw(10, 15, 4)); // Adresse auswählen--
		builder.add(getStrassenBox(), cc.xyw(10, 17, 4)); // Drop Straßen
		builder.add(getAdressenScroller(), cc.xywh(10, 19, 4, 11)); // Straßen Scroller

		// Standort/Lage

		builder.addSeparator(i18n.getString("editor.betreiber.separator.location"), cc.xyw(1, 31, 13)); // Standorte----
		builder.add(getStandorteScroller(), cc.xywh(1, 33, 13, 7)); // Standortetabelle

		// DSGVO

		builder.addSeparator(i18n.getString("editor.betreiber.separator.data_prot_notice"), cc.xyw(1, 41, 13)); // DSGVO
		builder.add(daten_awsvCheck, cc.xyw(1, 43, 2)); // AWSV Check
		builder.add(daten_esatzungCheck, cc.xyw(5, 43, 2)); // ESatzung Check
		builder.add(daten_whgCheck, cc.xyw(9, 43, 2)); // WHG Check

		builder.addSeparator(i18n.getString("editor.betreiber.remark"), cc.xyw(1, 45, 13)); // Bemerkungen ---
		builder.add(bemerkungsScroller, cc.xywh(1, 47, 13, 7)); // Bemerkungen Scroller

		builder.addSeparator(i18n.getString("editor.betreiber.last_rev"), cc.xyw(1, 55, 8));		//Letzte Revision--
		builder.addLabel(i18n.getString("editor.betreiber.date"), cc.xy(1, 57));						//Datum
		builder.add(revdatumsFeld, cc.xyw(3, 57, 4));
		handzeichenLabel = builder.addLabel(i18n.getString("editor.betreiber.sign"), cc.xy(1, 59));		//Handzeichen
		builder.add(handzeichenAltFeld, cc.xyw(3, 59, 4));
		builder.addSeparator(i18n.getString("editor.betreiber.separator.new_rev"), cc.xyw(10, 55, 4));		//Neue Revison--
		handzeichenLabel = builder.addLabel(i18n.getString("editor.betreiber.sign"), cc.xy(10, 57));
		builder.add(handzeichenNeuFeld, cc.xyw(12, 57, 2));

		button3.setText(i18n.getString("editor.betreiber.button.change_address"));

		BetreiberListener dialogListener = new BetreiberListener();

		strasseFeld.addActionListener(dialogListener);
		strassenBox.addActionListener(dialogListener);

		JPanel panel =  builder.getPanel();
		panel.setBorder(Paddings.DIALOG);
		return panel;
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
                    neuerStandort.setInhaber(inhaber);
                    editStandort(neuerStandort);
                }
            };
            standortNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_N));

        }

        return standortNeuAction;
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
					strassenBox.setModel(new DefaultComboBoxModel<String>(tabstreets));
				}
				if (adressenTabelle != null) {

					adressenModel.setStrasse(null);
					adressenModel.updateList();
					adressenTabelle.setModel(adressenModel);
					adressenTabelle.setEnabled(false);

					getAdressenTabelle();

				}
				if (standorteTabelle != null) {

					standorteModel.setInhaber(inhaber);
					standorteModel.updateList();
					standorteTabelle.setModel(standorteModel);

					getStandorteTabelle();

				}

				if (wirtschaftszweige != null) {
					wirtschaftszweigBox.setModel(new DefaultComboBoxModel<Wirtschaftszweig>(wirtschaftszweige));
					wirtschaftszweigBox.setSelectedItem(getBetreiber().getWirtschaftszweig());
				}
				if (gemarkungen != null)
				{
					gemarkungBox.setModel(new DefaultComboBoxModel<Gemarkung>(gemarkungen));
					gemarkungBox.setSelectedItem(adresse.getGemarkung());
				}
				if (standortggs != null)
				{
					standortGgBox.setModel(
						new DefaultComboBoxModel<Standortgghwsg>(standortggs));
					standortGgBox.setSelectedItem(adresse.getStandortgghwsg());
				}

				if (entwgebiete != null)
				{
					entwGebBox.setModel(
						new DefaultComboBoxModel<String>(entwgebiete));
					entwGebBox.setSelectedItem(adresse.getEntgebid());
				}

				if (wEinzugsgebiete != null)
				{
					wEinzugsGebBox.setModel(new DefaultComboBoxModel<Wassereinzugsgebiet>(wEinzugsgebiete));
					wEinzugsGebBox.setSelectedItem(adresse.getWassereinzugsgebiet());
				}

				anredeFeld.setText(getBetreiber().getAnrede());
				vornamenFeld.setText(getBetreiber().getVorname());
				namenFeld.setText(getBetreiber().getName());
				namenLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
				namenFeld.setFont(new Font("SansSerif", Font.BOLD, 12));
				nameZusFeld.setText(getBetreiber().getNamezus());
				kassenzeichenFeld.setText(getBetreiber().getKassenzeichen());
				kassenzeichenFeld.setFont(new Font("SansSerif", Font.BOLD, 12));
				kassenzeichenLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
				strasseFeld.setText(getBetreiber().getAdresse().getStrasse());
				hausnrFeld.setValue(getBetreiber().getAdresse().getHausnr());
				hausnrZusFeld.setText(getBetreiber().getAdresse().getHausnrzus());
				String plzZs = getBetreiber().getAdresse().getPlzzs();
				if (plzZs != null) {
					plzZs = plzZs.trim();
				}
				plzZsFeld.setText(plzZs);
				plzFeld.setText(getBetreiber().getAdresse().getPlz());
				ortFeld.setText(getBetreiber().getAdresse().getOrt());
				telefonFeld.setText(getBetreiber().getTelefon());
				telefaxFeld.setText(getBetreiber().getTelefax());
				emailFeld.setText(getBetreiber().getEmail());
				wassermengeFeld.setValue(getBetreiber().getWassermenge());
				strassenBox.setSelectedItem(getBetreiber().getAdresse().getStrasse());
				flurFeld.setText(adresse.getFlur());
				flurStkFeld.setText(adresse.getFlurstueck());

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

				if (adresse.isUeberschgeb() != null)
					ueberschgebCheck.setSelected(adresse.isUeberschgeb());
				else
					ueberschgebCheck.setSelected(false);


				betrBeaufVornameFeld.setText(getBetreiber().getVornamebetrbeauf());
				betrBeaufNachnameFeld.setText(getBetreiber().getNamebetrbeauf());

				handzeichenAltFeld.setText(getBetreiber().getRevihandz());
				bemerkungsArea.setText(getBetreiber().getBemerkungen());

				Date datum = getBetreiber().getRevidatum();
				revdatumsFeld.setText(AuikUtils.getStringFromDate(datum));

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
			getBetreiber().setAnrede(null);
		} else {
			getBetreiber().setAnrede(anrede);
		}
		// Vorname
		String vorname = vornamenFeld.getText();
		if ("".equals(vorname)) {
			getBetreiber().setVorname(null);
		} else {
			getBetreiber().setVorname(vorname);
		}
		// Name
		String name = namenFeld.getText();
		if ("".equals(name)) {
			getBetreiber().setName(null);
		} else {
			getBetreiber().setName(name);
		}
		// Zusatz
		String nameZusatz = nameZusFeld.getText();
		if ("".equals(nameZusatz)) {
			getBetreiber().setNamezus(null);
		} else {
			getBetreiber().setNamezus(nameZusatz);
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
			getBetreiber().getAdresse().setStrasse(null);
		} else {
			getBetreiber().getAdresse().setStrasse(strasse);
		}

		// Hausnummer:
		Integer hausnr = ((IntegerField) hausnrFeld).getIntValue();
		getBetreiber().getAdresse().setHausnr(hausnr);

		// Hausnummer-Zusatz:
		String hausnrZus = hausnrZusFeld.getText();
		if ("".equals(hausnrZus)) {
			getBetreiber().getAdresse().setHausnrzus(null);
		} else {
			getBetreiber().getAdresse().setHausnrzus(hausnrZus);
		}

		// PLZ-Zusatz
		String plzZs = plzZsFeld.getText();
		if ("".equals(plzZs)) {
			getBetreiber().getAdresse().setPlzzs(null);
		} else {
			getBetreiber().getAdresse().setPlzzs(plzZs.toUpperCase().trim());
		}

		// PLZ:
		String plz = plzFeld.getText();
		if (plz != null) {
			plz = plz.trim();
			if (plz.equals("")) {
				getBetreiber().getAdresse().setPlz(null);
			} else {
				getBetreiber().getAdresse().setPlz(plz);
			}
		}
		// Ort
		String ort = ortFeld.getText();
		if (ort != null) {
			ort = ort.trim();
			if (ort.equals("")) {
				getBetreiber().getAdresse().setOrt(null);
			} else {
				getBetreiber().getAdresse().setOrt(ort);
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

		adresse.setUeberschgeb(ueberschgebCheck.isSelected());

		// Wassermenge:
		Integer wassermenge = ((IntegerField) wassermengeFeld).getIntValue();
		getBetreiber().setWassermenge(wassermenge);

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

		if (adresse == null) {
		adresse = new Adresse();
		}
		// Gemarkung
		Gemarkung bgem = (Gemarkung) gemarkungBox.getSelectedItem();
		adresse.setGemarkung(bgem);

		// Standortgg
		Standortgghwsg stgg = (Standortgghwsg) standortGgBox.getSelectedItem();
		adresse.setStandortgghwsg(stgg);

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
		adresse.setEntgebid(ezgb);

		// VAWS-Einzugsgebiet
		Wassereinzugsgebiet wezg = (Wassereinzugsgebiet) wEinzugsGebBox.getSelectedItem();
		adresse.setWassereinzugsgebiet(wezg);

		// Flur
		String flur = flurFeld.getText().trim();
		if (flur.equals("")) {
			adresse.setFlur(null);
		} else {
			adresse.setFlur(flur);
		}

		// Flurstueck
		String flurstk = flurStkFeld.getText().trim();
		if (flurstk.equals("")) {
			adresse.setFlurstueck(null);
		} else {
			adresse.setFlurstueck(flurstk);
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

		List<?> adrStdListe = this.standorteModel.getList();
		for (int i = 0; i < adrStdListe.size(); i++) {

			Standort std = (Standort) adrStdListe.get(i);
			std.merge();

		}
		getBetreiber().merge();

		return true;
	}


	public Inhaber getBetreiber() {
		return (Inhaber) getEditedObject();
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

				this.standorteTabelle.getColumnModel().getColumn(0).setPreferredWidth(60);
				this.standorteTabelle.getColumnModel().getColumn(1).setPreferredWidth(60);
				this.standorteTabelle.getColumnModel().getColumn(2).setPreferredWidth(60);

				this.standorteTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				this.standorteTabelle.setColumnSelectionAllowed(false);
				this.standorteTabelle.setRowSelectionAllowed(true);

				this.standorteTabelle.addMouseListener(new java.awt.event.MouseAdapter() {

					@Override
					public void mouseClicked(java.awt.event.MouseEvent e) {

					}

					@Override
					public void mousePressed(MouseEvent e) {
						if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
							Point origin = e.getPoint();
							int row = getStandorteTabelle().rowAtPoint(origin);

							Standort std = BetreiberEditor.this.standorteModel.getRow(row);
							log.debug("Doppelklick auf Zeile " + row);
							editStandort(std);
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

	public void updateAdresse() {

		log.debug("Start updateAdresse()");
		ListSelectionModel lsm = getAdressenTabelle().getSelectionModel();
		if (!lsm.isSelectionEmpty() && ortFeld.getText().equals("Bielefeld")) {

			int selectedRow = lsm.getMinSelectionIndex();
			TabStreets bts = this.adressenModel.getRow(selectedRow);
			log.debug("Standort " + bts.getName() + " (ID" + bts.getAbgleich() + ") angewählt.");
			strasseFeld.setText(bts.getName());
			hausnrFeld.setValue(bts.getHausnr());
			hausnrZusFeld.setText(bts.getHausnrZusatz());
			int adr = getAdressenTabelle().getSelectedRow();
			TabStreets stra = (TabStreets) adressenModel.getObjectAtRow(adr);
			if (stra.getPlz() != null) {
				plzFeld.setText(stra.getPlz());
			}
			int row = BetreiberEditor.this.standorteTabelle
					.getSelectedRow();
			if (row != -1) {
			Standort standort = BetreiberEditor.this.standorteModel
					.getRow(row);
			standort.setE32(bts.getX());
			standort.setN32(bts.getY());
			} else if (inhaber.getStandorts().size() == 0) {
				Standort standort = new Standort();
				standort.setInhaber(inhaber);
				Set<Standort> standorts = new HashSet<Standort>();
				standorts.add(standort);
				inhaber.setStandorts(standorts);
				standort.setE32(bts.getX());
				standort.setN32(bts.getY());
				List<Standort> std = new ArrayList<Standort>();
				for (Standort x : standorts)
					std.add(x);
				standorteModel.setList(std);
			}

			this.standorteModel.fireTableDataChanged();

		}
		log.debug("End updateAdresse()");
	}

	private Component getStrassenBox() {

		strassenBox = new JComboBox<String>();
		strassenBox.setRenderer(new LongNameComboBoxRenderer());
		strassenBox.setEnabled(false);

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
				adressenModel.setStrasse((String) strassenBox.getSelectedItem());
				adressenModel.updateList();

			}
		}
	}

    /**
     * öffnet einen Dialog um einen Betreiber-Datensatz zu bearbeiten.
     * @param betr Der Betreiber
     */
    public void editStandort(Standort std) {
        StandortEditor editDialog = new StandortEditor(std, this.frame);
        editDialog.setLocationRelativeTo(this.frame);

        editDialog.setVisible(true);

        if (editDialog.wasSaved()) {
        	standorteModel.updateList();
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

	@Override
	protected void doApply() {

		AdresseChooser chooser = new AdresseChooser(inhaber.getAdresse(), BetreiberEditor.this.frame, "adresse");
		chooser.setVisible(true);
		Adresse adr = chooser.getChosenAdresse();
		if (adr != null) {
			inhaber.setAdresse(adr);
			inhaber.merge();
			fillForm();
		}

	}
}
