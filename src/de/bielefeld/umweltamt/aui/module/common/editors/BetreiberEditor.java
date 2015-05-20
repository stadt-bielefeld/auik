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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.criterion.MatchMode;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisOrte;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWirtschaftszweige;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
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
public class BetreiberEditor extends AbstractBaseEditor
{
	private static final long serialVersionUID = -7058333439142990179L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	// Für die Comboboxen beim Bearbeiten
	private static BasisOrte[] orte = null;
	private static VawsWirtschaftszweige[] wirtschaftszweige = null;

	private JLabel handzeichenLabel;
	private JLabel namenLabel;

	private JTextField anredeFeld;
	private JTextField vornamenFeld;
	private JTextField namenFeld;
	private JTextField nameZusFeld;
	private JTextField kassenzeichenFeld;
	private JFormattedTextField hausnrFeld;
	private JTextField hausnrZusFeld;
	private JTextField plzZsFeld;
	private JTextField plzFeld;
	private JTextField ortsFeld;
	private JTextField telefonFeld;
	private JTextField telefaxFeld;
	private JTextField emailFeld;
	private JTextField betrBeaufVornameFeld;
	private JTextField betrBeaufNachnameFeld;
	private JTextField revdatumsFeld;
	private JTextField handzeichenAltFeld;
	private JTextField handzeichenNeuFeld;

	private JTextArea bemerkungsArea;

	private JComboBox strassenBox;
	private JComboBox wirtschaftszweigBox;
	private JComboBox wzCodeBox;


	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten eines Betreibers.
	 */
	public BetreiberEditor(BasisBetreiber betr, HauptFrame owner)
	{
		super("Betreiber (" + betr.getId() + ")", betr, owner);
	}

	@Override
	protected JComponent buildContentArea()
	{
		anredeFeld = new LimitedTextField(100);
		vornamenFeld = new LimitedTextField(100);
		namenFeld = new LimitedTextField(100);
		nameZusFeld = new LimitedTextField(50);
		kassenzeichenFeld = new LimitedTextField(50);
		hausnrFeld = new IntegerField();
		hausnrZusFeld = new LimitedTextField(10);
		plzZsFeld = new LimitedTextField(3);

		plzFeld = new LimitedTextField(10);
		plzFeld.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == plzFeld)
				{
					reloadStrassen(plzFeld.getText(), null);
				}
			}
		});

		ortsFeld = new LimitedTextField(50);
		ortsFeld.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == ortsFeld)
				{
					reloadStrassen(null, ortsFeld.getText());
				}
			}
		});

		telefonFeld = new LimitedTextField(50);
		telefaxFeld = new LimitedTextField(50);
		emailFeld = new LimitedTextField(50);
		betrBeaufVornameFeld = new LimitedTextField(50);
		betrBeaufNachnameFeld = new LimitedTextField(50);

		revdatumsFeld = new JTextField();
		revdatumsFeld.setEditable(false);
		revdatumsFeld.setFocusable(false);
		revdatumsFeld
				.setToolTipText("Wird bei Änderungen automatisch aktualisiert.");

		handzeichenAltFeld = new JTextField();
		handzeichenAltFeld.setEditable(false);
		handzeichenAltFeld.setFocusable(false);
		handzeichenAltFeld.setToolTipText("Handzeichen der letzten Revision");
		handzeichenNeuFeld = new LimitedTextField(10, "");
		handzeichenNeuFeld
				.setToolTipText("Neues Handzeichen bei Änderungen obligatorisch!");

		bemerkungsArea = new LimitedTextArea(2000);
		bemerkungsArea.setLineWrap(true);
		bemerkungsArea.setWrapStyleWord(true);
		JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		strassenBox = new JComboBox();
		strassenBox.setEditable(true);
		strassenBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == strassenBox)
				{
					if (strassenBox.getSelectedItem() != null)
					{
						if (strassenBox.getSelectedItem().getClass() == BasisStrassen.class)
						{
							BasisStrassen selstrasse = (BasisStrassen) strassenBox.getSelectedItem();
							if (selstrasse != null)
							{
								plzFeld.setText(selstrasse.getPlz());
								ortsFeld.setText(selstrasse.getOrt());
							}
						}
					}
				}
			}
		});

		wirtschaftszweigBox = new JComboBox();
		wirtschaftszweigBox.setRenderer(new LongNameComboBoxRenderer());


		// Der folgende KeyListener wird benutzt um mit Escape
		// das Bearbeiten abzubrechen und bei Enter im
		// Handzeichen-Feld (wenn das Feld nicht leer ist) zum
		// Speichern-Button zu springen.
		KeyListener escEnterListener = new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getSource().equals(handzeichenNeuFeld))
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
							button1.requestFocus();
						}
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					doCancel();
				}
			}
		};
		anredeFeld.addKeyListener(escEnterListener);
		vornamenFeld.addKeyListener(escEnterListener);
		namenFeld.addKeyListener(escEnterListener);
		nameZusFeld.addKeyListener(escEnterListener);
		kassenzeichenFeld.addKeyListener(escEnterListener);
		hausnrFeld.addKeyListener(escEnterListener);
		hausnrZusFeld.addKeyListener(escEnterListener);
		plzZsFeld.addKeyListener(escEnterListener);
		plzFeld.addKeyListener(escEnterListener);
		ortsFeld.addKeyListener(escEnterListener);
		telefonFeld.addKeyListener(escEnterListener);
		telefaxFeld.addKeyListener(escEnterListener);
		emailFeld.addKeyListener(escEnterListener);
		betrBeaufVornameFeld.addKeyListener(escEnterListener);
		betrBeaufNachnameFeld.addKeyListener(escEnterListener);
		handzeichenNeuFeld.addKeyListener(escEnterListener);
		bemerkungsArea.addKeyListener(escEnterListener);
		strassenBox.addKeyListener(escEnterListener);
		//strassenFeld.addKeyListener(escEnterListener);
		wirtschaftszweigBox.addKeyListener(escEnterListener);

		// Ermögliche TAB aus dem Bemerkungs-Feld zu springen
		bemerkungsScroller.getVerticalScrollBar().setFocusable(false);
		bemerkungsScroller.getHorizontalScrollBar().setFocusable(false);
		//        TabAction tac = new TabAction(bemerkungsArea, handzeichenNeuFeld);

		String columnString = "right:pref, 3dlu, 20dlu, 40dlu:grow(1.0), 5dlu, right:pref, 3dlu, 27dlu:grow(1.0), 3dlu, 30dlu:grow(1.0)";
		FormLayout layout = new FormLayout(
				// Spalten
				columnString + // Links
						", 10dlu, " + // Abstand
						columnString, // Rechts

				// Zeilen:
				"pref, 3dlu, " + //1 Stammdaten    - Betr.beauft.
						"pref, 3dlu, " + //3
						"pref, 3dlu, " + //5             - Bemerkungen
						"pref, 3dlu, " + //7
						"pref, 3dlu, " + //9
						"pref, 3dlu, " + //11
						"pref, 3dlu, " + //13

						"pref, 3dlu, " + //15 Adresse    - Alte Revision
						"pref, 3dlu, " + //17
						"pref, 3dlu, " + //19
						"pref, 3dlu, " + //21            - Neue Revision
						"pref, 3dlu, " + //23
						"pref, 3dlu, " + //25
						"pref, 3dlu, " + //27
						"pref, 3dlu, " + //29
						"pref, 3dlu, " + //31
						"pref"); //33
		layout.setRowGroups(new int[][] { { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25 } });

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		// Stamdaten ------------------------------------
		builder.addSeparator("Stammdaten", cc.xyw(1, 1, 10));
		// Anrede
		builder.addLabel("Anrede:", cc.xy(1, 3));
		builder.add(anredeFeld, cc.xyw(3, 3, 8));
		// Vorame
		namenLabel = builder.addLabel("Vorname:", cc.xy(1, 5));
		builder.add(vornamenFeld, cc.xyw(3, 5, 8));
		// Name
		namenLabel = builder.addLabel("Name:", cc.xy(1, 7));
		builder.add(namenFeld, cc.xyw(3, 7, 8));
		// Zusatz
		builder.addLabel("Zusatz:", cc.xy(1, 9));
		builder.add(nameZusFeld, cc.xyw(3, 9, 8));
		// Zusatz
		builder.addLabel("Kassenzeichen:", cc.xy(1, 11));
		builder.add(kassenzeichenFeld, cc.xyw(3, 11, 8));
		// Wirtschaftszweig
		builder.addLabel("Wirtschaftszweig:", cc.xy(1, 13));
		builder.add(wirtschaftszweigBox, cc.xyw(3, 13, 8));

		// Adresse --------------------------------------
		builder.addSeparator("Adresse", cc.xyw(1, 19, 10));
		// Ort
		builder.addLabel("Ort:", cc.xy(1, 21));
		builder.add(plzZsFeld, cc.xy(3, 21));
		builder.add(plzFeld, cc.xy(4, 21));
		builder.add(ortsFeld, cc.xyw(6, 21, 5));
		// Straße
		builder.addLabel("Straße:", cc.xy(1, 23));
		builder.add(strassenBox, cc.xyw(3, 23, 4));
		builder.add(hausnrFeld, cc.xy(8, 23));
		builder.add(hausnrZusFeld, cc.xy(10, 23));
		// Telefon
		builder.addLabel("Telefon:", cc.xy(1, 25));
		builder.add(telefonFeld, cc.xyw(3, 25, 4));
		// Telefax
		builder.addLabel("Telefax:", cc.xy(1, 27));
		builder.add(telefaxFeld, cc.xyw(3, 27, 4));
		// eMail
		builder.addLabel("E-Mail:", cc.xy(1, 29));
		builder.add(emailFeld, cc.xyw(3, 29, 4));

		// Betriebsbeauftragter -------------------------
		builder.addSeparator("Ansprechpartner", cc.xyw(1 + 11, 1, 10));
		// Vorname
		builder.addLabel("Vorname:", cc.xy(1 + 11, 3));
		builder.add(betrBeaufVornameFeld, cc.xyw(3 + 11, 3, 2));
		// Nachname
		builder.addLabel("Nachname:", cc.xy(6 + 11, 3));
		builder.add(betrBeaufNachnameFeld, cc.xyw(8 + 11, 3, 3));

		// Bemerkungen ----------------------------------
		builder.addSeparator("Bemerkungen", cc.xyw(1 + 11, 5, 10));
		builder.add(bemerkungsScroller, cc.xywh(1 + 11, 7, 10, 11));

		// Alte Revision --------------------------------
		builder.addSeparator("Letzte Revision", cc.xyw(1 + 11, 19, 10));
		// Datum
		builder.addLabel("Datum:", cc.xy(1 + 11, 21));
		builder.add(revdatumsFeld, cc.xyw(3 + 11, 21, 4));
		// Handzeichen (alt)
		builder.addLabel("Handzeichen:", cc.xy(1 + 11, 23));
		builder.add(handzeichenAltFeld, cc.xyw(3 + 11, 23, 4));

		// Neue Revision --------------------------------
		builder.addSeparator("Neue Revision", cc.xyw(1 + 11, 25, 10));
		// Handzeichen (neu)
		handzeichenLabel = builder.addLabel("Handzeichen:", cc.xy(1 + 11, 27));
		builder.add(handzeichenNeuFeld, cc.xyw(3 + 11, 27, 4));

		return builder.getPanel();
	}

	/**
	 * Method reloads the streetlist for a given city
	 */
	private void reloadStrassen(String plz, String ort)
	{
		BasisStrassen[] strassen;

		if (!StringUtils.isNullOrEmpty(plz))
		{
			// Wenn wir einen Ort auswählen, aktualisieren wir die
			// Straßenliste
			strassen = DatabaseQuery.getStrassen(plz, null, MatchMode.EXACT);
			if (strassen != null)
			{
				strassenBox.setModel(new DefaultComboBoxModel(strassen));
				plzFeld.setText(strassen[0].getPlz());
				ortsFeld.setText(strassen[0].getOrt());
			}
		}

		else if (!StringUtils.isNullOrEmpty(ort))
		{
			// Wenn wir einen Ort auswählen, aktualisieren wir die
			// Straßenliste
			strassen = DatabaseQuery.getStrassen(null, ort, MatchMode.ANYWHERE);
			if (strassen != null)
			{
				strassenBox.setModel(new DefaultComboBoxModel(strassen));
				plzFeld.setText(strassen[0].getPlz());
				ortsFeld.setText(strassen[0].getOrt());
			}
		}
	}

	@Override
	protected void fillForm()
	{
		SwingWorkerVariant worker = new SwingWorkerVariant(this)
		{

			@Override
			protected void doNonUILogic() throws RuntimeException
			{
				if (wirtschaftszweige == null)
				{
					wirtschaftszweige = DatabaseQuery.getVawsWirtschaftszweige();
				}
			}

			@Override
			protected void doUIUpdateLogic() throws RuntimeException
			{
				strassenBox.setModel(new DefaultComboBoxModel());

				if (wirtschaftszweige != null)
				{
					wirtschaftszweigBox.setModel(new DefaultComboBoxModel(wirtschaftszweige));
					wirtschaftszweigBox.setSelectedItem(getBetreiber().getVawsWirtschaftszweige());
				}
				wzCodeBox.setEditable(true);
				wzCodeBox.setEditable(false);
				anredeFeld.setText(getBetreiber().getBetranrede());
				vornamenFeld.setText(getBetreiber().getBetrvorname());
				namenFeld.setText(getBetreiber().getBetrname());
				nameZusFeld.setText(getBetreiber().getBetrnamezus());
				kassenzeichenFeld.setText(getBetreiber().getKassenzeichen());
				hausnrFeld.setValue(getBetreiber().getHausnr());
				hausnrZusFeld.setText(getBetreiber().getHausnrzus());
				String plzZs = getBetreiber().getPlzzs();
				if (plzZs != null)
				{
					plzZs = plzZs.trim();
				}
				plzZsFeld.setText(plzZs);
				plzFeld.setText(getBetreiber().getPlz());
				ortsFeld.setText(getBetreiber().getOrt());
				telefonFeld.setText(getBetreiber().getTelefon());
				telefaxFeld.setText(getBetreiber().getTelefax());
				emailFeld.setText(getBetreiber().getEmail());
				strassenBox.setSelectedItem(getBetreiber().getStrasse());

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
	protected boolean canSave()
	{
		// Eingaben überprüfen:
		if (namenFeld.getText().equals(""))
		{
			// Der Name darf nicht leer sein
			namenLabel.setForeground(HauptFrame.ERROR_COLOR);
			namenFeld.requestFocus();
			String nameErr = "Der Name darf nicht leer sein!";
			frame.changeStatus(nameErr, HauptFrame.ERROR_COLOR);
			log.debug(nameErr);
			return false;
		}
		else if (handzeichenNeuFeld.getText().equals(""))
		{
			// Das Handzeichen darf nicht leer sein
			handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
			handzeichenNeuFeld.requestFocus();
			String handzErr = "Neues Handzeichen erforderlich!";
			frame.changeStatus(handzErr, HauptFrame.ERROR_COLOR);
			log.debug(handzErr);
			return false;
		}
		else
		{
			// Wenn die Eingaben korrekt sind
			return true;
		}
	}

	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
	 */
	@Override
	protected boolean doSave()
	{
		// Anrede
		String anrede = anredeFeld.getText();
		if ("".equals(anrede))
		{
			getBetreiber().setBetranrede(null);
		}
		else
		{
			getBetreiber().setBetranrede(anrede);
		}
		// Vorname
		String vorname = vornamenFeld.getText();
		if ("".equals(vorname))
		{
			getBetreiber().setBetrvorname(null);
		}
		else
		{
			getBetreiber().setBetrvorname(vorname);
		}
		// Name
		String name = namenFeld.getText();
		if ("".equals(name))
		{
			getBetreiber().setBetrname(null);
		}
		else
		{
			getBetreiber().setBetrname(name);
		}
		// Zusatz
		String nameZusatz = nameZusFeld.getText();
		if ("".equals(nameZusatz))
		{
			getBetreiber().setBetrnamezus(null);
		}
		else
		{
			getBetreiber().setBetrnamezus(nameZusatz);
		}
		// kassenzeichen
		String kasse = kassenzeichenFeld.getText();
		if ("".equals(kasse))
		{
			getBetreiber().setKassenzeichen(null);
		}
		else
		{
			getBetreiber().setKassenzeichen(kasse);
		}

		getBetreiber().setStrasse(getStrasse());

		// Hausnummer:
		Integer hausnr = ((IntegerField) hausnrFeld).getIntValue();
		getBetreiber().setHausnr(hausnr);

		// Hausnummer-Zusatz:
		String hausnrZus = hausnrZusFeld.getText();
		if ("".equals(hausnrZus))
		{
			getBetreiber().setHausnrzus(null);
		}
		else
		{
			getBetreiber().setHausnrzus(hausnrZus);
		}

		// PLZ-Zusatz
		String plzZs = plzZsFeld.getText();
		if ("".equals(plzZs))
		{
			getBetreiber().setPlzzs(null);
		}
		else
		{
			getBetreiber().setPlzzs(plzZs.toUpperCase().trim());
		}

		// PLZ:
		String plz = plzFeld.getText();
		if (plz != null)
		{
			plz = plz.trim();
			if (plz.equals(""))
			{
				getBetreiber().setPlz(null);
			}
			else
			{
				getBetreiber().setPlz(plz);
			}
		}
		// Ort
		String ort = ortsFeld.getText();
		if (ort != null)
		{
			ort = ort.trim();
			if (ort.equals(""))
			{
				getBetreiber().setOrt(null);
			}
			else
			{
				getBetreiber().setOrt(ort);
			}
		}
		// Telefon
		String telefon = telefonFeld.getText();
		if (telefon != null)
		{
			telefon = telefon.trim();
			if (telefon.equals(""))
			{
				getBetreiber().setTelefon(null);
			}
			else
			{
				getBetreiber().setTelefon(telefon);
			}
		}
		// Telefax
		String telefax = telefaxFeld.getText();
		if (telefax != null)
		{
			telefax = telefax.trim();
			if (telefax.equals(""))
			{
				getBetreiber().setTelefax(null);
			}
			else
			{
				getBetreiber().setTelefax(telefax);
			}
		}
		// eMail
		String email = emailFeld.getText();
		if (email != null)
		{
			email = email.trim();
			if (email.equals(""))
			{
				getBetreiber().setEmail(null);
			}
			else
			{
				getBetreiber().setEmail(email);
			}
		}
		// Betriebsbeauftragter-Vorname
		String betrBeaufVorname = betrBeaufVornameFeld.getText();
		if (betrBeaufVorname != null)
		{
			betrBeaufVorname = betrBeaufVorname.trim();
			if (betrBeaufVorname.equals(""))
			{
				getBetreiber().setVornamebetrbeauf(null);
			}
			else
			{
				getBetreiber().setVornamebetrbeauf(betrBeaufVorname);
			}
		}
		// Betriebsbeauftragter-Nachname
		String betrBeaufNachname = betrBeaufNachnameFeld.getText();
		if (betrBeaufNachname != null)
		{
			betrBeaufNachname = betrBeaufNachname.trim();
			if (betrBeaufNachname.equals(""))
			{
				getBetreiber().setNamebetrbeauf(null);
			}
			else
			{
				getBetreiber().setNamebetrbeauf(betrBeaufNachname);
			}
		}
		// Wirtschaftszweig
		VawsWirtschaftszweige wizw = (VawsWirtschaftszweige) wirtschaftszweigBox.getSelectedItem();
		getBetreiber().setVawsWirtschaftszweige(wizw);

		// Bemerkungen
		String bemerkungen = bemerkungsArea.getText();
		if (bemerkungen != null)
		{
			bemerkungen = bemerkungen.trim();
			if (bemerkungen.equals(""))
			{
				getBetreiber().setBemerkungen(null);
			}
			else
			{
				getBetreiber().setBemerkungen(bemerkungen);
			}
		}
		// Handzeichen
		String handzeichen = handzeichenNeuFeld.getText().trim();
		getBetreiber().setRevihandz(handzeichen);

		getBetreiber().setRevidatum(Calendar.getInstance().getTime());

		// frame.changeStatus("Keine Änderungen an Betreiber "+betr.getBetreiberid()+" vorgenommen.");

		BasisBetreiber persistentBetreiber = null;
		persistentBetreiber = BasisBetreiber.merge(getBetreiber());

		if (persistentBetreiber != null)
		{
			setEditedObject(persistentBetreiber);
			log.debug("Änderungen gespeichert!");
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Methode liefert die eingegebene oder ausgewählte Straße
	 * 
	 * @return
	 */
	private String getStrasse()
	{
		String str = "";

		if (strassenBox.getSelectedItem() != null)
		{
			if (strassenBox.getSelectedItem().getClass() == BasisStrassen.class)
			{
				BasisStrassen selstrasse = (BasisStrassen) strassenBox.getSelectedItem();
				if (selstrasse != null)
				{
					str = selstrasse.getStrasse();
				}
			}
			else if (strassenBox.getSelectedItem().getClass() == String.class)
			{
				str = (String) strassenBox.getSelectedItem();
			}
		}
		str = str.trim();

		// Weil ich bis jetzt noch keine LimitedComboBox oder so habe...
		if (str.length() > 50)
		{
			// ... kürze ich hier den String auf 50 Zeichen
			str = str.substring(0, 50);
		}

		return str;
	}

	public BasisBetreiber getBetreiber()
	{
		return (BasisBetreiber) getEditedObject();
	}
}