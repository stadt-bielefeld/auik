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
 * $Id: BasisStandortNeu.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 12.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.15  2005/09/14 11:25:37  u633d
 * - Version vom 14.9.
 *
 * Revision 1.14  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.criterion.MatchMode;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisGemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisOrte;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsStandortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWassereinzugsgebiete;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.SearchBox;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Modul zum neuen Anlegen eines Standorts.
 * 
 * @author David Klotz
 */
public class BasisStandortNeu extends AbstractModul
{
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private JButton speichernButton;

	private JFormattedTextField hausnrEditFeld;
	private JTextField hausnrZusFeld;
    private JTextField plzFeld;
	private JTextField flurFeld;
	private JTextField flurStkFeld;
	private JFormattedTextField e32Feld;
	private JFormattedTextField n32Feld;
	private JTextField datumFeld;
	private JLabel handzeichenLabel;
	private JTextField handzeichenNeuFeld;

	private JComboBox strassenBox;
	private JComboBox orteBox;
	private JComboBox gemarkungBox;
	private JComboBox standortGgBox;
	private JComboBox entwGebBox;
	private JComboBox wEinzugsGebBox;

	private BasisOrte[] orte = null;
	private BasisGemarkung[] gemarkungen = null;
	private VawsStandortgghwsg[] standortggs = null;
	private String[] entwgebiete = null;
	private VawsWassereinzugsgebiete[] wEinzugsgebiete = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	@Override
	public String getName()
	{
		return "Neuer Standort";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	@Override
	public String getIdentifier()
	{
		return "m_standort_neu";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	@Override
	public String getCategory()
	{
		return "Betriebe";
	}

	/**
	 * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
	 */
	@Override
	public Icon getIcon()
	{
		return super.getIcon("filenew32.png");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	@Override
	public JPanel getPanel()
	{
		if (panel == null)
		{
			speichernButton = new JButton("Speichern");

			JPanel buttonBar = ButtonBarFactory.buildOKBar(speichernButton);

			strassenBox = new SearchBox();
			orteBox = new SearchBox();
			// strassenBox.setKeySelectionManager(new MyKeySelectionManager());

			hausnrEditFeld = new IntegerField();
			hausnrZusFeld = new JTextField();
            plzFeld = new LimitedTextField(10);
			flurFeld = new LimitedTextField(50);
			flurStkFeld = new LimitedTextField(50);

			e32Feld = new DoubleField(1);
			e32Feld.setValue(new Float(0.0f));
			n32Feld = new DoubleField(1);
			n32Feld.setValue(new Float(0.0f));

			datumFeld = new JTextField();
			datumFeld.setEditable(false);
			datumFeld.setFocusable(false);

			datumFeld.setToolTipText("Wird automatisch gesetzt.");

			handzeichenLabel = new JLabel("Handzeichen:");
			handzeichenNeuFeld = new LimitedTextField(10);
			handzeichenNeuFeld.setToolTipText("Handzeichen obligatorisch!");

			gemarkungBox = new JComboBox();
			standortGgBox = new JComboBox();
			entwGebBox = new JComboBox();
			entwGebBox.setEditable(true);
			wEinzugsGebBox = new JComboBox();

			// Der folgende KeyListener wird benutzt um mit
			// Enter im Handzeichen-Feld (wenn das Feld nicht
			// leer ist) zum Speichern-Button zu springen.
			handzeichenNeuFeld.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						if (handzeichenNeuFeld.getText().equals(""))
						{
							handzeichenLabel.setForeground(Color.RED);
							handzeichenNeuFeld.requestFocus();
						}
						else
						{
							speichernButton.requestFocus();
						}
					}
				}
			});

			FormLayout layout = new FormLayout(
					"right:pref, 3dlu, 50dlu:grow(0.5), 3dlu, 50dlu:grow(1.0), 5dlu, 20dlu:grow(0.2), 3dlu, 15dlu:grow(0.2), 20dlu:grow(2.5)", // Spalten
					"pref, " + // 1
							"3dlu, " + // 2
							"pref, " + // 3
							"3dlu, " + // 4
							"pref, " + // 5
							"3dlu, " + // 6
							"pref, " + // 7
							"10dlu, " + // 8
							"pref, " + // 9
							"3dlu, " + // 10
							"pref, " + // 11
							"10dlu, " + // 12
							"pref, " + // 13
							"3dlu, " + // 14
							"pref, " + // 15
							"10dlu, " + // 16
							"pref, " + // 17
							"3dlu, " + // 18
							"pref, " + // 19
							"10dlu, " + // 20
							"pref, " + // 21
							"3dlu, " + // 22
							"pref, " + // 23
							"3dlu, " + // 24
							"pref, " + // 25
							"10dlu, " + // 26
							"pref, " + // 27
							"3dlu, " + // 28
							"pref, " + // 29
							"3dlu, " + // 30
							"pref, " + // 31
							"10dlu, " + // 32
							"bottom:pref:grow"); // 33
			// layout.setRowGroups(new
			// int[][]{{1,3,5,7,9,11,13,15,17,19,21,23}});

			PanelBuilder builder = new PanelBuilder(layout);
			builder.setDefaultDialogBorder();
			CellConstraints cc = new CellConstraints();

			// Adresse
			builder.addSeparator("Stammdaten", cc.xyw(1, 1, 9));
			// Strasse, HausNr
			builder.addLabel("Straße:", cc.xy(1, 3));
			builder.add(strassenBox, cc.xyw(3, 3, 3));
			builder.add(hausnrEditFeld, cc.xy(7, 3));
			builder.add(hausnrZusFeld, cc.xy(9, 3));
			// PLZ
            builder.addLabel("PLZ:", cc.xy(  1, 5 ));
            builder.add(plzFeld, cc.xy(  3, 5 ));
			// Ort
			builder.addLabel("Ort:", cc.xy(1, 7));
			builder.add(orteBox, cc.xyw(3, 7, 3));

			// Koordinaten
			builder.addLabel("E32:", cc.xy(1, 9));
			builder.add(e32Feld, cc.xyw(3, 9, 3));
			builder.addLabel("N32:", cc.xy(1, 11));
			builder.add(n32Feld, cc.xyw(3, 11, 3));

			//
			builder.addLabel("Gemarkung:", cc.xy(1, 13));
			builder.add(gemarkungBox, cc.xyw(3, 13, 3));
			builder.addLabel("Entwässerungsgebiet:", cc.xy(1, 15));
			builder.add(entwGebBox, cc.xyw(3, 15, 3));

			// Flur
			builder.addLabel("Flur:", cc.xy(1, 17));
			builder.add(flurFeld, cc.xy(3, 17));
			builder.addLabel("Flurstück:", cc.xy(1, 19));
			builder.add(flurStkFeld, cc.xy(3, 19));

			// VAWS
			builder.addSeparator("VAWS", cc.xyw(1, 21, 9));
			builder.addLabel("Standortgegebenheit:", cc.xy(1, 23));
			builder.add(standortGgBox, cc.xyw(3, 23, 3));
			builder.addLabel("W.Einzugsgebiet:", cc.xy(1, 25));
			builder.add(wEinzugsGebBox, cc.xyw(3, 25, 3));

			// Revision
			builder.addSeparator("Revision", cc.xyw(1, 27, 9));
			builder.addLabel("Datum:", cc.xy(1, 29));
			builder.add(datumFeld, cc.xyw(3, 29, 3));
			builder.add(handzeichenLabel, cc.xy(1, 31));
			builder.add(handzeichenNeuFeld, cc.xyw(3, 31, 3));

			builder.add(buttonBar, cc.xyw(1, 33, 9));

			StandortNeuListener listener = new StandortNeuListener();

			speichernButton.addActionListener(listener);

			strassenBox.addActionListener(listener);
//			strassenBox.getEditor().getEditorComponent().addKeyListener((new KeyAdapter() {
//				public void keyPressed(final KeyEvent arg0) {
//					if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
//						reloadOrte();
//					} else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
//						System.out.println("VK_ESCAPE");
//					}
//				}
//			}));
			orteBox.addActionListener(listener);

			panel = builder.getPanel();
		}
		return panel;
	}

	@Override
	public void show()
	{
		super.show();
		clearForm();
	}

	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
	 * Speichern die Werte des Formulars in einen neuen Standort.
	 */
	private void doSave()
	{
		// Eingaben überprüfen:
		// Das Handzeichen darf nicht leer sein
		if (handzeichenNeuFeld.getText().equals(""))
		{
			handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
			handzeichenNeuFeld.requestFocus();
			log.debug("Neues Handzeichen erforderlich!");
		}
		else
		{
			// Wenn die Eingaben korrekt sind

			setAllEnabled(false);

			// Neues Standortobjekt erzeugen
			BasisStandort bsta = new BasisStandort();

			// Hausnummer:
			Integer hausnr = ((IntegerField) hausnrEditFeld).getIntValue();
			bsta.setHausnr(hausnr);

			// Hausnummer-Zusatz:
			String hausnrZus = hausnrZusFeld.getText();
			if (hausnrZus.equals(""))
			{
				bsta.setHausnrzus(null);
			}
			else
			{
				bsta.setHausnrzus(hausnrZus);
			}

			// Straße, PLZ, Ort:
			BasisStrassen strasse = ((BasisStrassen) strassenBox.getSelectedItem());
			if (strasse == null)
			{
				bsta.setStrasse(null);
				bsta.setPlz(null);
				bsta.setOrt(null);
			}
			else
			{
				bsta.setStrasse(strasse.getStrasse());
				bsta.setPlz(strasse.getPlz());
				bsta.setOrt(strasse.getOrt());
			}

			// Gemarkung
			BasisGemarkung bgem = (BasisGemarkung) gemarkungBox
					.getSelectedItem();
			bsta.setBasisGemarkung(bgem);

			// Standortgg
			VawsStandortgghwsg stgg = (VawsStandortgghwsg) standortGgBox
					.getSelectedItem();
			bsta.setVawsStandortgghwsg(stgg);

			// Einzugsgebiet
			String ezgb = (String) entwGebBox.getSelectedItem();
			// Nötig, weil getSelectedItem bei editierbarer ComboBox auch NULL
			// liefern kann
			if (ezgb != null)
			{
				// Weil ich bis jetzt noch keine LimitedComboBox oder so habe...
				if (ezgb.length() > 10)
				{
					// ... kürze ich hier den String auf 10 Zeichen
					ezgb = ezgb.substring(0, 10);
				}
				ezgb = ezgb.trim();
			}
			bsta.setEntgebid(ezgb);

			// VAWS-Einzugsgebiet
			VawsWassereinzugsgebiete wezg = (VawsWassereinzugsgebiete) wEinzugsGebBox
					.getSelectedItem();
			bsta.setVawsWassereinzugsgebiete(wezg);

			// Flur
			String flur = flurFeld.getText().trim();
			if (flur.equals(""))
			{
				bsta.setFlur(null);
			}
			else
			{
				bsta.setFlur(flur);
			}

			// Flurstueck
			String flurstk = flurStkFeld.getText().trim();
			if (flurstk.equals(""))
			{
				bsta.setFlurstueck(null);
			}
			else
			{
				bsta.setFlurstueck(flurstk);
			}

			// Rechtswert
			Float e32Wert = ((DoubleField) e32Feld).getFloatValue();
			bsta.setE32(e32Wert);

			// Hochwert
			Float n32Wert = ((DoubleField) n32Feld).getFloatValue();
			bsta.setN32(n32Wert);

			bsta.setRevidatum(new Date());
			bsta.setRevihandz(handzeichenNeuFeld.getText().trim());

			bsta = BasisStandort.merge(bsta);

			if (bsta != null)
			{
				frame.changeStatus("Neuer Standort " + bsta.getId()
						+ " erfolgreich gespeichert!", HauptFrame.SUCCESS_COLOR);

				// Wenn wir vom Objekt anlegen kommen,
				if (manager.getSettingsManager().getBoolSetting(
																"auik.imc.return_to_objekt"))
				{
					manager.getSettingsManager().setSetting(
															"auik.imc.use_standort", bsta.getId().intValue(),
															false);
					manager.getSettingsManager().removeSetting(
																"auik.imc.return_to_objekt");
					// ... kehren wir direkt dorthin zurück:
					manager.switchModul("m_objekt_bearbeiten");
				}
				else
				{
					// Sonst einfach das Formular zurücksetzen
					clearForm();
				}
			}
			else
			{
				frame.changeStatus("Konnte Standort nicht speichern!",
									Color.RED);
				log.debug("Konnte nicht speichern");
			}
		}
	}

	private void clearForm()
	{
		setAllEnabled(false);
		// frame.changeStatus("Beschäftigt...");

		SwingWorkerVariant worker = new SwingWorkerVariant(strassenBox)
		{

			@Override
			protected void doNonUILogic() throws RuntimeException
			{
				if (orte == null)
				{
					orte = DatabaseQuery.getOrte();
				}
				if (gemarkungen == null)
				{
					gemarkungen = DatabaseQuery.getBasisGemarkungen();
				}
				if (standortggs == null)
				{
					standortggs = DatabaseQuery.getVawsStandortgghwsg();
				}
				if (entwgebiete == null)
				{
					entwgebiete = DatabaseQuery.getEntwaesserungsgebiete();
				}
				if (wEinzugsgebiete == null)
				{
					wEinzugsgebiete = DatabaseQuery.getWassereinzugsgebiete();
				}
			}

			@Override
			protected void doUIUpdateLogic() throws RuntimeException
			{
				strassenBox.setModel(new DefaultComboBoxModel());

				if (orte != null)
				{
					orteBox.setModel(new DefaultComboBoxModel(orte));
					orteBox.setSelectedItem(null);
				}

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

				hausnrEditFeld.setValue(new Integer(0));
				hausnrZusFeld.setText("");
				flurFeld.setText("");
				flurStkFeld.setText("");
				e32Feld.setValue(new Float(0.0f));
				n32Feld.setValue(new Float(0.0f));
				datumFeld.setText(DateUtils.getCurrentDateString());
				handzeichenNeuFeld.setText("");
				handzeichenLabel.setForeground(panel.getForeground());

				setAllEnabled(true);
			}
		};
		worker.start();

		log.debug("(" + this.getIdentifier() + ".clearForm )"
				+ "Formular zurückgesetzt");
	}

	/**
	 * Aktiviert oder deaktiviert alle Felder im Formular.
	 * 
	 * @param enabled
	 *            <code>true</true>, wenn die Felder aktiviert werden sollen, sonst <code>false</code>
	 */
	private void setAllEnabled(boolean enabled)
	{
		speichernButton.setEnabled(enabled);
		orteBox.setEnabled(enabled);
		strassenBox.setEnabled(enabled);
		hausnrEditFeld.setEditable(enabled);
		hausnrZusFeld.setEditable(enabled);
		orteBox.setEditable(enabled);
		gemarkungBox.setEnabled(enabled);
		standortGgBox.setEnabled(enabled);
		entwGebBox.setEnabled(enabled);
		entwGebBox.setEditable(enabled);
		wEinzugsGebBox.setEnabled(enabled);
		flurFeld.setEditable(enabled);
		flurStkFeld.setEditable(enabled);
		e32Feld.setEditable(enabled);
		n32Feld.setEditable(enabled);
		handzeichenNeuFeld.setEditable(enabled);
	}

	/**
	 * Check if a location already exists
	 * 
	 * @return true, if the given location exists, false otherwise
	 */
	private boolean standortExists()
	{
		return DatabaseQuery
				.basisStandortExists(((BasisStrassen) strassenBox
						.getSelectedItem()).getStrasse(),
										((IntegerField) hausnrEditFeld).getIntValue(),
										(hausnrZusFeld.getText().equals("") ? null
												: hausnrZusFeld.getText()));
	}

	/**
	 * Method reloads the streetlist for a given city
	 */
	private void reloadStrassen()
	{
		BasisOrte selort = (BasisOrte) orteBox.getSelectedItem();
		if (selort != null)
		{
			// Wenn wir einen Ort auswählen, aktualisieren wir die
			// Straßenliste
			BasisStrassen[] strassen = DatabaseQuery.getStrassen(selort.getOrt(), MatchMode.EXACT);
			if (strassen != null)
			{
				strassenBox.setModel(new DefaultComboBoxModel(strassen));

				BasisStrassen selstrasse = (BasisStrassen) strassenBox.getSelectedItem();
				if (selstrasse != null)
				{
					// Ort hat sich geändert => Strasse zurücksetzen
					if (!StringUtils.equals(selstrasse.getOrt(), selort.getOrt(), true))
					{
						strassenBox.setSelectedItem(null);
					}
				}

				strassenBox.setEnabled(true);
			}
			else
			{
				// ohne gültigen Ort gibt's alle Strasse
				strassenBox.setModel(new DefaultComboBoxModel(DatabaseQuery.getAllStrassen()));
			}
		}
		else
		{
			// ohne gültigen Ort gibt's alle Strasse
			strassenBox.setModel(new DefaultComboBoxModel(DatabaseQuery.getAllStrassen()));
		}
	}

	/**
	 * Method reloads the ortelist for a given street
	 */
	private void reloadOrte()
	{
		BasisStrassen selstrasse = (BasisStrassen) strassenBox.getSelectedItem();
		
		if (selstrasse != null) {
			// Wenn wir eine Starsse auswählen, aktualisieren wir die
			// Orteliste
			BasisStrassen stra = DatabaseQuery.findStrasse(strassenBox
					.getSelectedItem().toString());
			if (stra != null) {
				// Natürlich nur, wenn die Straße eine eindeutige PLZ hat
				if (stra.getPlz() != null) {
					frame.clearStatus();
					orteBox.setSelectedItem(DatabaseQuery.getOrt(stra
							.toString()));
					strassenBox.setSelectedItem(stra);
				} else {
					frame.changeStatus("Die Straße '"
							+ stra
							+ "' hat keine eindeutige PLZ, bitte selbst eintragen!");
				}
			}

		}

	}

	/**
	 * Ein Listener für die Events des "Neuer Standort"-Moduls.
	 * 
	 * @author David Klotz
	 */
	private final class StandortNeuListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == speichernButton)
			{
				log.debug("(" + BasisStandortNeu.this.getIdentifier() + ") "
						+ "Speichern gedrückt!");
				// Check if we already have this location
				if (standortExists())
				{
					frame.changeStatus("Standort existiert bereits!", Color.RED);
					log.debug("Standort existiert bereits und wurde nicht gespeichert.");
				}
				else
				{
					doSave();
				}
			}
			else if (e.getSource() == orteBox)
			{
				reloadStrassen();
			}
			else if (e.getSource() == strassenBox)
			{
				reloadOrte();
				
              // Wenn wir eine Straße auswählen, wird die PLZ upgedatet
              BasisStrassen stra = DatabaseQuery.findStrasse(
                  strassenBox.getSelectedItem().toString());
              if (stra != null) {
              }
              // Natürlich nur, wenn die Straße eine eindeutige PLZ hat
              if (stra.getPlz() != null) {
                  frame.clearStatus();
                  plzFeld.setText(stra.getPlz().toString());
              } else {
                  frame.changeStatus("Die Straße '"+stra+"' hat keine eindeutige PLZ, bitte selbst eintragen!");
                  plzFeld.setText("");
              }
            }
		}
	}
//    /**
//     * Ein Listener für die Events des "Neuer Standort"-Moduls.
//     * @author David Klotz
//     */
//    private final class StandortNeuListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == speichernButton) {
//                log.debug("(" + BasisStandortNeu.this.getIdentifier() + ") "
//                		+ "Speichern gedrückt!");
//                // Check if we already have this location
//                if (standortExists()) {
//                    frame.changeStatus("Standort existiert bereits!", Color.RED);
//                    log.debug("Standort existiert bereits und wurde nicht gespeichert.");
//                } else {
//                    doSave();
//                }
//            } else if (e.getSource() == strassenBox) {
//                // Wenn wir eine Straße auswählen, wird die PLZ upgedatet
//                BasisStrassen stra = DatabaseQuery.findStrasse(
//                    (String) strassenBox.getSelectedItem());
//                if (stra != null) {
//                    // Natürlich nur, wenn die Straße eine eindeutige PLZ hat
//                    if (stra.getPlz() != null) {
//                        frame.clearStatus();
//                        plzFeld.setText(stra.getPlz().toString());
//                    } else {
//                        frame.changeStatus("Die Straße '"+stra+"' hat keine eindeutige PLZ, bitte selbst eintragen!");
//                        plzFeld.setText("");
//                    }
//                }
//            }
//        }
//    }
}
