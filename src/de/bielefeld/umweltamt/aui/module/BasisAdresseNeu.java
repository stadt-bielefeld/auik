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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisAdresse;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisGemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisLage;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisMapAdresseLage;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisOrte;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisTabStreets;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsStandortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWassereinzugsgebiete;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWirtschaftszweige;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStandorteModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
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
public class BasisAdresseNeu extends AbstractModul
{
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private JButton speichernButton;
	private BasisMapAdresseLage mapLage;
	private BasisLage lage;

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
	private JTextField handzeichenNeuFeld;
	private JTextField flurFeld;
	private JTextField flurStkFeld;
	private JFormattedTextField e32Feld;
	private JFormattedTextField n32Feld;
	private JComboBox gemarkungBox;
	private JComboBox entwGebBox;
	private JComboBox standortGgBox;
	private JComboBox wEinzugsGebBox;
	
	private BasisGemarkung[] gemarkungen = null;
	private String[] entwgebiete = null;
	private VawsStandortgghwsg[] standortggs = null;
	private VawsWassereinzugsgebiete[] wEinzugsgebiete = null;

	private JTextArea bemerkungsArea;

	private JComboBox strassenBox;
	private JTable standorteTabelle;
	private BasisStandorteModel standorteModel;
	private JComboBox wirtschaftszweigBox;

	private BasisOrte[] orte = null;
	private VawsWirtschaftszweige[] wirtschaftszweige = null;
	private String[] tabstreets = null;
	private String street = null;


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	@Override
	public String getName()
	{
		return "Neue Adresse";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	@Override
	public String getIdentifier()
	{
		return "m_betreiber_neu";
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

			JPanel buttonBar = ButtonBarFactory.buildOKBar(speichernButton);

			// Der folgende KeyListener wird benutzt um bei Enter
			// im Handzeichen-Feld (wenn das Feld nicht leer ist)
			// zum Speichern-Button zu springen.
			handzeichenNeuFeld.addKeyListener(new KeyAdapter()
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
			//            TabAction tac = new TabAction(bemerkungsArea, handzeichenNeuFeld);

			FormLayout layout = new FormLayout(
					"right:pref, 3dlu, 20dlu, 70dlu, 5dlu, right:pref, 3dlu, 27dlu, 3dlu, 30dlu, 10dlu, 60dlu, 3dlu, 60dlu, 3dlu, 20dlu", // Spalten
					"pref, 3dlu, " + //1 - Stammdaten
							"pref, 3dlu, " + //3
							"pref, 3dlu, " + //5
							"pref, 3dlu, " + //7
							"pref, 3dlu, " + //9
							"pref, 3dlu, " + //11
							"pref, 3dlu, " + //13

							"pref, 3dlu, " + //15 - Adresse
							"pref, 3dlu, " + //17
							"pref, 3dlu, " + //19
							"pref, 3dlu, " + //21
							"pref, 3dlu, " + //23
							"pref, 3dlu, " + //25

							"pref, 3dlu, " + //27 - Betriebsbeauftrager
							"pref, 3dlu, " + //29

							"pref, 3dlu, " + //31 - Bemerkungen
							"pref, 3dlu, " + //33
							"pref, 3dlu, " + //35

							"pref, 3dlu, " + //37 - Revision
							"pref, 3dlu, " + //39
							"pref, 10dlu, " + //41

							"top:pref:grow");//43 - Buttons
			layout.setRowGroups(new int[][] { { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35,
					37 } });

			PanelBuilder builder = new PanelBuilder(layout);
			builder.setDefaultDialogBorder();
			CellConstraints cc = new CellConstraints();

			// Stamdaten ------------------------------------
			builder.addSeparator("Stammdaten", cc.xyw(1, 1, 16));
			// Anrede
			builder.addLabel("Anrede:", cc.xy(1, 3));
			builder.add(anredeFeld, cc.xyw(3, 3, 6));
			// Telefon
			builder.addLabel("Telefon:", cc.xy(10, 3));
			builder.add(telefonFeld, cc.xyw(12, 3, 5));
			// Vorname
			builder.addLabel("Vorname:", cc.xy(1, 5));
			builder.add(vornamenFeld, cc.xyw(3, 5, 6));
			// Telefax
			builder.addLabel("Telefax:", cc.xy(10, 5));
			builder.add(telefaxFeld, cc.xyw(12, 5, 5));
			// Name
			namenLabel = builder.addLabel("Name:", cc.xy(1, 7));
			builder.add(namenFeld, cc.xyw(3, 7, 6));
			// eMail
			builder.addLabel("E-Mail:", cc.xy(10, 7));
			builder.add(emailFeld, cc.xyw(12, 7, 5));
			// Zusatz
			builder.addLabel("Zusatz:", cc.xy(1, 9));
			builder.add(nameZusFeld, cc.xyw(3, 9, 6));
			
			// Ansprechpartner -------------------------
			builder.addSeparator("Ansprechpartner", cc.xyw(10, 9, 7));
			// Vorname
			builder.addLabel("Vorname:", cc.xy(10, 11));
			builder.add(betrBeaufVornameFeld, cc.xyw(12, 11, 5));
			// Nachname
			builder.addLabel("Name:", cc.xy(10, 13));
			builder.add(betrBeaufNachnameFeld, cc.xyw(12, 13, 5));
			// Kassenzeichen
			builder.addLabel("Kassenzeichen:", cc.xy(1, 11));
			builder.add(kassenzeichenFeld, cc.xyw(3, 11, 6));
			// Wirtschaftszweig
			builder.addLabel("Wirtschaftszweig:", cc.xy(1, 13));
			builder.add(wirtschaftszweigBox, cc.xyw(3, 13, 6));

			// Lage --------------------------------------
			builder.addSeparator("Lage", cc.xyw(1, 15, 10));
			// auswählen --------------------------------------
			builder.addSeparator("auswählen", cc.xyw(12, 15, 5));
			// Ort
			builder.addLabel("Ort:", cc.xy(1, 17));
			builder.add(plzZsFeld, cc.xy(3, 17));
			builder.add(plzFeld, cc.xy(4, 17));
			builder.add(ortFeld, cc.xyw(6, 17, 5));
			// Straße
			builder.addLabel("Straße:", cc.xy(1, 19));
			builder.add(strasseFeld, cc.xyw(3, 19, 4));
			builder.add(hausnrFeld, cc.xy(8, 19));
			builder.add(hausnrZusFeld, cc.xy(10, 19));
			builder.add(getStrassenBox(), cc.xyw(12, 17, 5));

			builder.add(getStandorteScroller(), cc.xywh(12, 19, 5, 13));

			// Koordinaten
			builder.addLabel("E32:", cc.xy(1, 21));
			builder.add(e32Feld, cc.xyw(3, 21, 3));
			builder.addLabel("N32:", cc.xy(1, 23));
			builder.add(n32Feld, cc.xyw(3, 23, 3));
			builder.addLabel("Entwässerungsgebiet:", cc.xy(1, 25));
			builder.add(entwGebBox, cc.xyw(3, 25, 3));
			
			//
			builder.addLabel("Gemarkung:", cc.xy(1, 27));
			builder.add(gemarkungBox, cc.xyw(3, 27, 8));
			
			//VAwS
			builder.addLabel("Standortgegebenheit:", cc.xy(1, 29));
			builder.add(standortGgBox, cc.xyw(3, 29, 8));
			builder.addLabel("W.Einzugsgebiet:", cc.xy(1, 31));
			builder.add(wEinzugsGebBox, cc.xyw(3, 31, 8));

			

			// Bemerkungen ----------------------------------
			builder.addSeparator("Bemerkungen", cc.xyw(1, 33, 10));
			builder.add(bemerkungsScroller, cc.xywh(1, 35, 10, 3));

			// Revision -------------------------------------
			builder.addSeparator("Revision", cc.xyw(12, 33, 5));
			// Datum
			builder.addLabel("Datum:", cc.xy(12, 35));
			builder.add(revdatumsFeld, cc.xyw(14, 35, 3));
			// Handzeichen
			handzeichenLabel = builder.addLabel("Handzeichen:", cc.xy(12, 37));
			builder.add(handzeichenNeuFeld, cc.xyw(14, 37, 3));

			// Buttons
			builder.add(buttonBar, cc.xyw(14, 43, 3));

			BetreiberNeuListener dialogListener = new BetreiberNeuListener();

			speichernButton.addActionListener(dialogListener);
			plzFeld.addActionListener(dialogListener);
			ortFeld.addActionListener(dialogListener);
			strasseFeld.addActionListener(dialogListener);
			strassenBox.addActionListener(dialogListener);

			panel = builder.getPanel();
		}
		return panel;
	}

	private JTable getStandorteTabelle() {
	
		if (this.standorteModel == null) {
			this.standorteModel = new BasisStandorteModel();
	
			if (this.standorteTabelle == null) {
				this.standorteTabelle = new JTable(this.standorteModel);
	
				this.standorteTabelle.getColumnModel().getColumn(0)
						.setPreferredWidth(10);
				this.standorteTabelle.getColumnModel().getColumn(1)
						.setPreferredWidth(100);
				this.standorteTabelle.getColumnModel().getColumn(2)
						.setPreferredWidth(10);
				this.standorteTabelle.getColumnModel().getColumn(3)
						.setPreferredWidth(7);
	
				this.standorteTabelle
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				this.standorteTabelle.setColumnSelectionAllowed(false);
				this.standorteTabelle.setRowSelectionAllowed(true);
	
				this.standorteTabelle
						.addMouseListener(new java.awt.event.MouseAdapter() {
							@Override
							public void mouseClicked(java.awt.event.MouseEvent e) {
								if ((e.getClickCount() == 1)
										&& (e.getButton() == 1)) {
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

        JScrollPane standorteScroller = new JScrollPane(
            getStandorteTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return standorteScroller;
	}

	private Component getStrassenBox() {

		strassenBox = new JComboBox();
		strassenBox.setRenderer(new LongNameComboBoxRenderer());
		
		return strassenBox;
	}

	@Override
	public void show()
	{
		super.show();
		clearForm();
	}
	
//	public void updateStandorteListe() {
//		
//		SwingWorkerVariant worker = new SwingWorkerVariant(strassenBox) {
//
//			private BasisStandorteModel standorteModel;
//
//			@Override
//			protected void doNonUILogic() throws RuntimeException {
//				
//				String strasse = strassenBox.getSelectedItem().toString();
//		        this.standorteModel.setStrasse(strasse);
//		        this.standorteModel.updateList();
//				
//			}
//
//			@Override
//			protected void doUIUpdateLogic() throws RuntimeException {
//				
//				
//				
//			}
//			
//		};
//	}

    public void updateAdresse() {
        
    	log.debug("Start updateAdresse()");
        ListSelectionModel lsm = getStandorteTabelle().getSelectionModel();
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            BasisTabStreets bts = this.standorteModel.getRow(selectedRow);
            log.debug("Standort " + bts.getName() + " (ID"
                + bts.getAbgleich() + ") angewählt.");
            plzFeld.setText(bts.getPlz());
            strasseFeld.setText(bts.getName());
            hausnrFeld.setValue(bts.getHausnr());
            hausnrZusFeld.setText(bts.getHausnrZusatz());
            e32Feld.setValue(bts.getX());
            n32Feld.setValue(bts.getY());
            BasisStrassen stra = DatabaseQuery.findStrasse(strassenBox
					.getSelectedItem().toString());
            if (stra.getPlz() != null) {
            	plzFeld.setText(stra.getPlz());
            }
            
            BasisLage vorhandeneLage = null;
            String i;
            if (bts.getHausnrZusatz() == null) {
        	i = "";
            } else  {
        	i = bts.getHausnrZusatz();
            }
            List<BasisAdresse> firmen = DatabaseQuery.findStandorte(strasseFeld.getText(),bts.getHausnr() , stra.getOrt());
            for(BasisAdresse firma : firmen) {
        	String f;
                if (firma.getHausnrzus() == null) {
            	f = "";
                } else  {
            	f = firma.getHausnrzus();
                }
        	if (i.equals(f) || f.contains("-") ) {
            	Set<BasisMapAdresseLage> verk = firma.getBasisMapAdresseLages();
            	for (BasisMapAdresseLage ver : verk) {
            	    vorhandeneLage = ver.getBasisLage();
            	}   
        	}
            }
            if (vorhandeneLage != null) {
        	List<BasisGemarkung> gemarkungen = DatabaseQuery.getGemarkungenlist();
        	for (BasisGemarkung gemarkung : gemarkungen) {
        	  if (gemarkung.getGemarkung().equals(vorhandeneLage.getBasisGemarkung().getGemarkung())) {
        	      this.gemarkungBox.setSelectedItem(gemarkung);
        	  }
        	}
        	for (VawsStandortgghwsg standort : standortggs) {
        	    if (standort.getStandortgg().equals(vorhandeneLage.getVawsStandortgghwsg().getStandortgg())) {
        		this.standortGgBox.setSelectedItem(standort);
        	    }
        	}
        	for (VawsWassereinzugsgebiete wEinzugsgebiet : wEinzugsgebiete) {
        	    if (wEinzugsgebiet.getEzgbname().equals(vorhandeneLage.getVawsWassereinzugsgebiete().getEzgbname())) {
        		this.wEinzugsGebBox.setSelectedItem(wEinzugsgebiet);
        	    }
        	}
        	this.entwGebBox.setSelectedItem(vorhandeneLage.getEntgebid());
            } else {
        	this.gemarkungBox.setSelectedIndex(0);
        	this.standortGgBox.setSelectedIndex(0);
        	this.wEinzugsGebBox.setSelectedIndex(0);
        	this.entwGebBox.setSelectedIndex(0);
            }
        }
        log.debug("End updateAdresse()");
    }


	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
	 * Speichern die Werte des Formulars in einen neuen Standort.
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

			// Neues Standortobjekt erzeugen
			BasisAdresse adrn = new BasisAdresse();
            
			/*if (standorteTabelle.getSelectedRowCount() > 0) {
				mapLage = new BasisMapAdresseLage();
				lage = new BasisLage();
				mapLage.setBasisAdresse(adrn);
				mapLage.setBasisLage(lage);
			}*/
            //Vermeidet fehler beim merge, wenn eine eigene Adresse eingeben wurde anstatt eine aus der Liste auszuwählen
            mapLage = new BasisMapAdresseLage();
			lage = new BasisLage();
			mapLage.setBasisAdresse(adrn);
			mapLage.setBasisLage(lage);

			// Anrede
			String anrede = anredeFeld.getText();
			if (anrede.equals("")) {
				adrn.setBetranrede(null);
			} else {
				adrn.setBetranrede(anrede);
			}
			// Vorame
			String vorname = vornamenFeld.getText();
			if (vorname.equals("")) {
				adrn.setBetrvorname(null);
			} else {
				adrn.setBetrvorname(vorname);
			}
			// Name
			String name = namenFeld.getText();
			if (name.equals("")) {
				adrn.setBetrname(null);
			} else {
				adrn.setBetrname(name);
			}
			// Zusatz
			String nameZusatz = nameZusFeld.getText();
			if (nameZusatz.equals("")) {
				adrn.setBetrnamezus(null);
			} else {
				adrn.setBetrnamezus(nameZusatz);
			}
			// Kassenzeichen
			String kassenzeichen = kassenzeichenFeld.getText();
			if (kassenzeichen.equals("")) {
				adrn.setKassenzeichen(null);
			} else {
				adrn.setKassenzeichen(kassenzeichen);
			}

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
			// Telefon
			String telefon = telefonFeld.getText().trim();
			if (telefon.equals("")) {
				adrn.setTelefon(null);
			} else {
				adrn.setTelefon(telefon);
			}
			// Telefax
			String telefax = telefaxFeld.getText().trim();
			if (telefax.equals("")) {
				adrn.setTelefax(null);
			} else {
				adrn.setTelefax(telefax);
			}
			// eMail
			String email = emailFeld.getText().trim();
			if (email.equals("")) {
				adrn.setEmail(null);
			} else {
				adrn.setEmail(email);
			}
			// Betriebsbeauftragter-Vorname
			String betrBeaufVorname = betrBeaufVornameFeld.getText().trim();
			if (betrBeaufVorname.equals("")) {
				adrn.setVornamebetrbeauf(null);
			} else {
				adrn.setVornamebetrbeauf(betrBeaufVorname);
			}
			// Betriebsbeauftragter-Nachname
			String betrBeaufNachname = betrBeaufNachnameFeld.getText().trim();
			if (betrBeaufNachname.equals("")) {
				adrn.setNamebetrbeauf(null);
			} else {
				adrn.setNamebetrbeauf(betrBeaufNachname);
			}
			// Wirtschaftszweig
			VawsWirtschaftszweige wizw = (VawsWirtschaftszweige) wirtschaftszweigBox
					.getSelectedItem();
			adrn.setVawsWirtschaftszweige(wizw);

			if (lage != null) {
				// Gemarkung
				BasisGemarkung bgem = (BasisGemarkung) gemarkungBox
						.getSelectedItem();
				mapLage.getBasisLage().setBasisGemarkung(bgem);

				// Standortgg
				VawsStandortgghwsg stgg = (VawsStandortgghwsg) standortGgBox
						.getSelectedItem();
				mapLage.getBasisLage().setVawsStandortgghwsg(stgg);

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
				mapLage.getBasisLage().setEntgebid(ezgb);

				// VAWS-Einzugsgebiet
				VawsWassereinzugsgebiete wezg = (VawsWassereinzugsgebiete) wEinzugsGebBox
						.getSelectedItem();
				mapLage.getBasisLage().setVawsWassereinzugsgebiete(wezg);

				// Flur
				String flur = flurFeld.getText().trim();
				if (flur.equals("")) {
					mapLage.getBasisLage().setFlur(null);
				} else {
					mapLage.getBasisLage().setFlur(flur);
				}

				// Flurstueck
				String flurstk = flurStkFeld.getText().trim();
				if (flurstk.equals("")) {
					mapLage.getBasisLage().setFlurstueck(null);
				} else {
					mapLage.getBasisLage().setFlurstueck(flurstk);
				}

				// Rechtswert
				Float e32Wert = ((DoubleField) e32Feld).getFloatValue();
				mapLage.getBasisLage().setE32(e32Wert);

				// Hochwert
				Float n32Wert = ((DoubleField) n32Feld).getFloatValue();
				mapLage.getBasisLage().setN32(n32Wert);
			}
			// Bemerkungen
			String bemerkungen = bemerkungsArea.getText().trim();
			if (bemerkungen.equals("")) {
				adrn.setBemerkungen(null);
			} else {
				adrn.setBemerkungen(bemerkungen);
			}

			adrn.setRevidatum(Calendar.getInstance().getTime());
			adrn.setRevihandz(handzeichenNeuFeld.getText().trim());
			
			/* Wir brauchen hier nur BasisMapAdresseLage mergen, da Hibernate 
			 * mit cascade=All so konfiguriert ist, dass die Tabellen Basis_Adresse
			 * und Basis_Lage ebenfalls gespeichert werden.
			 */

			BasisMapAdresseLage persistentAL = null;
			persistentAL = BasisMapAdresseLage.merge(mapLage);

			if (persistentAL != null) {
				frame.changeStatus("Neuer Betreiber " + persistentAL.getId()
						+ " erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);

				// Wenn wir vom Objekt anlegen kommen,
				if (manager.getSettingsManager().getBoolSetting(
						"auik.imc.return_to_objekt_betreiber")) {
					manager.getSettingsManager().setSetting(
							"auik.imc.use_betreiber",
							persistentAL.getBasisAdresse().getId().intValue(),
							false);
					manager.getSettingsManager().removeSetting(
							"auik.imc.return_to_objekt_betreiber");
					// ... kehren wir direkt dorthin zurück:
					manager.switchModul("m_objekt_bearbeiten");
				}
				else if (manager.getSettingsManager().getBoolSetting(
							"auik.imc.return_to_objekt_standort")) {
					manager.getSettingsManager().setSetting(
							"auik.imc.use_standort",
							persistentAL.getBasisAdresse().getId().intValue(),
							false);
					manager.getSettingsManager().setSetting(
							"auik.imc.use_lage",
							persistentAL.getBasisLage().getId().intValue(),
							false);
					manager.getSettingsManager().removeSetting(
							"auik.imc.return_to_objekt_standort");
					manager.getSettingsManager().removeSetting(
							"auik.imc.return_to_objekt_lage");
					// ... kehren wir direkt dorthin zurück:
					manager.switchModul("m_objekt_bearbeiten");
					}
				else {
					// Sonst einfach das Formular zurücksetzen
					clearForm();
				}
			} else {
				frame.changeStatus("Konnte Betreiber nicht speichern!",
						Color.RED);
				log.debug("Konnte nicht speichern");
			}

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
			if (strassenBox.getSelectedItem().getClass() == BasisTabStreets.class)
			{
				BasisTabStreets selstrasse = (BasisTabStreets) strassenBox.getSelectedItem();
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

	private void clearForm()
	{
		setAllEnabled(false);
		//frame.changeStatus("Beschäftigt...");

		SwingWorkerVariant worker = new SwingWorkerVariant(panel)
		{

			@Override
			protected void doNonUILogic() throws RuntimeException
			{
				if (orte == null)
				{
					orte = DatabaseQuery.getOrte();
				}
				if (wirtschaftszweige == null)
				{
					wirtschaftszweige = DatabaseQuery.getVawsWirtschaftszweige();
				}
				if (tabstreets == null)
				{
					tabstreets = DatabaseQuery.getTabStreets();
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
				if (wirtschaftszweige != null)
				{
					wirtschaftszweigBox.setModel(new DefaultComboBoxModel(wirtschaftszweige));
				}
				if (tabstreets != null)
				{
					strassenBox.setModel(new DefaultComboBoxModel(tabstreets));
				}
				if (standorteTabelle != null) {

			        standorteModel.setStrasse(null);
			        standorteModel.updateList();
					standorteTabelle.setModel(standorteModel);
					
					standorteTabelle.getColumnModel().getColumn(0)
							.setPreferredWidth(10);
					standorteTabelle.getColumnModel().getColumn(1)
							.setPreferredWidth(100);
					standorteTabelle.getColumnModel().getColumn(2)
							.setPreferredWidth(10);
					standorteTabelle.getColumnModel().getColumn(3)
							.setPreferredWidth(7);

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
				

				ortFeld.setText("Bielefeld");
				hausnrFeld.setValue(null);
				hausnrZusFeld.setText("");
				plzZsFeld.setText("D");
				plzFeld.setText("");
				anredeFeld.setText("");
				vornamenFeld.setText("");
				namenFeld.setText("");
				namenLabel.setForeground(panel.getForeground());
				nameZusFeld.setText("");
				kassenzeichenFeld.setText("");
				telefonFeld.setText("");
				telefaxFeld.setText("");
				emailFeld.setText("");
				betrBeaufNachnameFeld.setText("");
				betrBeaufVornameFeld.setText("");
				bemerkungsArea.setText("");

				revdatumsFeld.setText(DateUtils.getCurrentDateString());
				handzeichenNeuFeld.setText("");
				handzeichenLabel.setForeground(panel.getForeground());

				setAllEnabled(true);
				//frame.clearStatus();
				log.debug("(" + getIdentifier() + ".clearForm) "
						+ "Formular zurückgesetzt");
			}
		};
		worker.start();
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
	private final class BetreiberNeuListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == speichernButton)
			{
				log.debug("(" + BasisAdresseNeu.this.getIdentifier() + ") "
						+ "Speichern gedrückt!");
				doSave();
			}

//			else if (e.getSource() == orteBox)
//			{
//				reloadStrassen();
//			}
			else if (e.getSource() == strassenBox)
			{
		        standorteModel.setStrasse(strassenBox.getSelectedItem().toString());
		        standorteModel.updateList();
				
            }
		}
	}
}
