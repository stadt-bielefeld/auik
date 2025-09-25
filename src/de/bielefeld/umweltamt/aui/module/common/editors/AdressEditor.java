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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Dialog zum Bearbeiten eines Standorts.
 *
 * @author David Klotz
 */
public class AdressEditor extends AbstractBaseEditor
{
	/**
	 *
	 */
	private static final long serialVersionUID = 3366212357256622461L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();
	private Adresse adresse = null;

    private JTextField strasseFeld;
    private JFormattedTextField hausnrFeld;
    private JTextField hausnrZusFeld;
    private JTextField plzZsFeld;
    private JTextField plzFeld;
    private JTextField ortFeld;
	private JTextField revidatumFeld;
    private JTextField revihandzFeld;
	private JCheckBox ueberschgebCheck;
	private JTextField flurFeld;
	private JTextField flurStkFeld;
	private JTextField strasseeigentFeld;

	private Gemarkung[] gemarkungen = null;
	private String[] entwgebiete = null;
	private Standortgghwsg[] standortggs = null;
	private Wassereinzugsgebiet[] wEinzugsgebiete = null;

	private JComboBox gemarkungBox;
	private JComboBox entwGebBox;
	private JComboBox standortGgBox;
	private JComboBox wEinzugsGebBox;

	private JTextArea bemerkungsArea;

	private JButton sapeichernButton;

	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten eines Standorts.
	 */
	public AdressEditor(Adresse adr, HauptFrame owner)
	{
		super("Adresse (" + adr.getId() + ")", adr, owner);
		this.adresse = adr;
	}

	@Override
	protected JComponent buildContentArea()
	{


		hausnrFeld = new IntegerField();
		hausnrZusFeld = new LimitedTextField(10);
		plzZsFeld = new LimitedTextField(3, "");
		plzFeld = new JTextField();
		strasseFeld = new JTextField();
		ortFeld = new JTextField();
		flurFeld = new LimitedTextField(50);
		flurStkFeld = new LimitedTextField(50);
		ueberschgebCheck = new JCheckBox("Überschwemm.-gebiet");
		strasseeigentFeld = new JTextField();

		gemarkungBox = new JComboBox();
		entwGebBox = new JComboBox();
		standortGgBox = new JComboBox();
		wEinzugsGebBox = new JComboBox();

		revidatumFeld = new JTextField();
		revidatumFeld.setEditable(false);
		revidatumFeld.setFocusable(false);
		revidatumFeld.setToolTipText("Wird automatisch gesetzt.");

		revihandzFeld = new LimitedTextField(10, "");
		revihandzFeld.setToolTipText("Handzeichen obligatorisch!");

		bemerkungsArea = new LimitedTextArea(2000);
		bemerkungsArea.setLineWrap(true);
		bemerkungsArea.setWrapStyleWord(true);
		JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);



		String linkeSpalten = "r:p, 3dlu, 50dlu:g, 3dlu, 50dlu:g, 5dlu, 20dlu:g(0.2), 3dlu, 15dlu:g(0.2)";
		String rechteSpalten = "r:p, 3dlu, 50dlu:g, 3dlu, 50dlu:g";
		int rS = 10;

		FormLayout layout = new FormLayout(
				"right:pref, 3dlu, 20dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu", // Spalten
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
						"50dlu, 3dlu, " + // 21
						"pref, 3dlu, "); // 23

		layout.setRowGroups(new int[][] {
				{ 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 } });

		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addSeparator("Adresse", cc.xyw(1, 1, 8));
		builder.addLabel("Stadt", cc.xy(1, 3));
		builder.add(plzZsFeld, cc.xy(3, 3)); // PLZ-Zusatz
		builder.add(plzFeld, cc.xy(4, 3)); // PLZ
		builder.add(ortFeld, cc.xyw(6, 3, 3)); // Stadt
		builder.addLabel("Straße", cc.xy(1, 5)); // Straße
		builder.add(strasseFeld, cc.xyw(3, 5, 2));
		builder.add(hausnrFeld, cc.xy(6, 5)); // Hausnr
		builder.add(hausnrZusFeld, cc.xy(8, 5)); // HausnrZusatz
		builder.addLabel("Standortgegebenheit", cc.xy(1, 7));
		builder.add(standortGgBox, cc.xyw(3, 7, 6));
		builder.addLabel("W.-Einzugsgebiet", cc.xy(1, 9));
		builder.add(wEinzugsGebBox, cc.xyw(3, 9, 6));
		builder.addLabel("Entwässerungsgebiet", cc.xy(1, 11));
		builder.add(entwGebBox, cc.xyw(3, 11, 2));
		builder.add(ueberschgebCheck, cc.xyw(6, 11, 3)); // Ueberschgeb Check
		builder.addLabel("Gemarkung", cc.xy(1, 13));
		builder.add(gemarkungBox, cc.xyw(3, 13, 6));
		builder.addLabel("Flur/Flurstücke", cc.xy(1, 15));
		builder.add(flurFeld, cc.xy(3, 15));
		builder.add(flurStkFeld, cc.xyw(4, 15, 5));
		builder.addLabel("Standorteigentümer", cc.xy(1, 17));
		builder.add(strasseeigentFeld, cc.xyw(3, 17, 6));

		builder.addSeparator("Bemerkungen", cc.xyw(1, 19, 8)); // Bemerkungen ---
		builder.add(bemerkungsScroller, cc.xywh(1, 21, 8, 2)); // Bemerkungen Scroller

		JPanel panel = builder.getPanel();
		panel.setBorder(Paddings.DIALOG);
		return panel;
	}


	@Override
	protected void fillForm()
	{
		frame.changeStatus("Beschäftigt...");

		SwingWorkerVariant worker = new SwingWorkerVariant(this)
		{

			@Override
			protected void doNonUILogic() throws RuntimeException {
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
			protected void doUIUpdateLogic() throws RuntimeException
			{

				if (gemarkungen != null)
				{
					gemarkungBox.setModel(new DefaultComboBoxModel(gemarkungen));
					gemarkungBox.setSelectedItem(adresse.getGemarkung());
				}
				if (standortggs != null)
				{
					standortGgBox.setModel(new DefaultComboBoxModel(standortggs));
					standortGgBox.setSelectedItem(adresse.getStandortgghwsg());
				}

				if (entwgebiete != null)
				{
					entwGebBox.setModel(new DefaultComboBoxModel(entwgebiete));
					entwGebBox.setSelectedItem(adresse.getEntgebid());
				}

				if (wEinzugsgebiete != null)
				{
					wEinzugsGebBox.setModel(new DefaultComboBoxModel(wEinzugsgebiete));
					wEinzugsGebBox.setSelectedItem(adresse.getWassereinzugsgebiet());
				}

				if (adresse.isUeberschgeb() != null)
					ueberschgebCheck.setSelected(adresse.isUeberschgeb());
				else
					ueberschgebCheck.setSelected(false);

				strasseFeld.setText(adresse.getStrasse());
				hausnrFeld.setValue(adresse.getHausnr());
				hausnrZusFeld.setText(adresse.getHausnrzus());
				String plzZs = adresse.getPlzzs();
				if (plzZs != null) {
					plzZs = plzZs.trim();
				}
				plzZsFeld.setText(plzZs);
				plzFeld.setText(adresse.getPlz());
				ortFeld.setText(adresse.getOrt());
				flurFeld.setText(adresse.getFlur());
				flurStkFeld.setText(adresse.getFlurstueck());
				strasseeigentFeld.setText(adresse.getStrasseeigent());

				bemerkungsArea.setText(getAdresse().getBemerkungen());

				frame.clearStatus();
			}
		};
		worker.start();
	}

	@Override
	protected boolean canSave()
	{
		return true;
		// Eingaben überprüfen:

	}

	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
	 */
	@Override
	protected boolean doSave()
	{


		// Strasse:
		String strasse = strasseFeld.getText();
		if ("".equals(strasse)) {
			adresse.setStrasse(null);
		} else {
			adresse.setStrasse(strasse);
		}

		// Hausnummer:
		Integer hausnr = ((IntegerField) hausnrFeld).getIntValue();
		adresse.setHausnr(hausnr);

		// Hausnummer-Zusatz:
		String hausnrZus = hausnrZusFeld.getText();
		if ("".equals(hausnrZus)) {
			adresse.setHausnrzus(null);
		} else {
			adresse.setHausnrzus(hausnrZus);
		}

		// PLZ-Zusatz
		String plzZs = plzZsFeld.getText();
		if ("".equals(plzZs)) {
			adresse.setPlzzs(null);
		} else {
			adresse.setPlzzs(plzZs.toUpperCase().trim());
		}

		// PLZ:
		String plz = plzFeld.getText();
		if (plz != null) {
			plz = plz.trim();
			if (plz.equals("")) {
				adresse.setPlz(null);
			} else {
				adresse.setPlz(plz);
			}
		}
		// Ort
		String ort = ortFeld.getText();
		if (ort != null) {
			ort = ort.trim();
			if (ort.equals("")) {
				adresse.setOrt(null);
			} else {
				adresse.setOrt(ort);
			}
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

		adresse.setUeberschgeb(ueberschgebCheck.isSelected());

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

		// Eigentümer
		String straString = strasseeigentFeld.getText().trim();
		if (flurstk.equals("")) {
			adresse.setStrasseeigent(null);
		} else {
			adresse.setStrasseeigent(straString);
		}

		//Bezeichnung
		String bezeichnung = bemerkungsArea.getText();
		if (bezeichnung != null)
		{
			bezeichnung = bezeichnung.trim();
			if (bezeichnung.equals(""))
			{
				getAdresse().setBemerkungen(null);
			}
			else
			{
				getAdresse().setBemerkungen(bezeichnung);
			}
		}

		Adresse adr = Adresse.merge(getAdresse());
		if (adr != null)
		{
			setEditedObject(adr);
			log.debug("Änderungen gespeichert!");
			return true;
		}
		else
		{
			return false;
		}
	}

	public Adresse getAdresse()
	{
		return (Adresse) getEditedObject();
	}
}
