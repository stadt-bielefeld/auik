/*
 * Datei:
 * $Id: ObjektAnh49Panel.java,v 1.3 2009-03-24 12:35:22 u633d Exp $
 * 
 * Erstellt am 27.04.2005 von Gerhard Genuit (u633d)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2008/07/23 06:55:22  u633d
 * Anh49 Auswertung und Sielhautimport neu
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.4  2005/06/13 14:02:16  u633z
 * - Nicht vorhandenes Fachdaten-Objekt wird besser behandelt
 *
 * Revision 1.3  2005/06/10 11:06:52  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.2  2005/06/03 11:05:05  u633z
 * - Datenbank-Zugriffe (speziell Speichern-Methoden) in Mappings verlagert.
 *
 * Revision 1.1  2005/05/30 08:35:42  u633z
 * - Aufgeräumt, mehrere neue Packages, Klassen sortiert
 *
 * Revision 1.6  2005/05/20 09:00:54  u633z
 * Anhang 49 und Anh49-Abscheidetail-Tabs verbessert und verschönert
 *
 * Revision 1.5  2005/05/19 15:37:51  u633d
 * Abscheiderdetails-Zwischenstand
 *
 * Revision 1.4  2005/05/19 12:53:44  u633d
 * Anhang 49 neue Mapings
 *
 * Revision 1.3  2005/05/17 07:53:43  u633d
 * Anhang 49 Ergänzungen
 *
 * Revision 1.2  2005/05/12 06:39:18  u633z
 * JavaDoc Kommentar verbessert, Header angepasst
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.ObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Anhang 49"-Tab des ObjektBearbeiten-Moduls.
 * @author Gerd Genuit
 */
public class ObjektAnh49Panel extends JPanel {
	private String name;
	private ObjektBearbeiten hauptModul;
	
	// Widgets
	private JTextField sachbearbeiterFeld = null;
	private JTextField ansprechpartnerFeld = null;
	private JTextField sachkundelfaFeld = null;
	private JFormattedTextField durchgefuehrtFeld = null;
	private TextFieldDateChooser wannDatum = null;
	private JTextField analyseMonatFeld = null;
	private JFormattedTextField dekraTuevFeld = null;
	private JCheckBox mangelCheck = null;
	private JCheckBox behobenCheck = null;
	private TextFieldDateChooser fristDatum = null;
	private TextFieldDateChooser antragDatum = null;
	private TextFieldDateChooser genehmigungDatum = null;
	private TextFieldDateChooser aenderungDatum = null;
	private TextFieldDateChooser letzteAenderDatum = null;
	private JTextField anschreibenFeld = null;
	private TextFieldDateChooser wiedervorlageDatum = null;
	private JCheckBox abgemeldetCheck = null;
	private JCheckBox abwasserfreiCheck = null;
	private JCheckBox waschanlageCheck = null;
	private JCheckBox eSatzungCheck = null;
	private JTextArea anh49BemerkungArea = null;
	private JButton saveAnh49Button = null;
	
	
	
	// Daten
	private Anh49Fachdaten fachdaten = null;
	
	//Listener
	private ActionListener editButtonListener;
	
	
	public ObjektAnh49Panel(ObjektBearbeiten hauptModul) {
		name = "Anhang 49";
		this.hauptModul = hauptModul;
		
		FormLayout layout = new FormLayout (
				"r:50dlu, 5dlu, 90dlu, 10dlu, r:50dlu, 5dlu, 70dlu", // Spalten
				"pref, " +	//1
				"3dlu, " +	//2
				"pref, " +	//3
				"3dlu, " +	//4
				"pref, " +	//5
				"3dlu, " +	//6
				"pref, " +	//7
				"3dlu, " +	//8
				"pref, " +	//9
				"3dlu, " +	//10
				"pref, " +  //11
				"3dlu, " +	//12
				"pref, " +	//13
				"3dlu, " +	//14
				"pref, " +  //15
				"3dlu, " +  //16
				"pref, " +  //17
				"3dlu, " +  //18
				"pref, " +  //19
				"3dlu, " +  //20
				"pref, " +	//21
				"3dlu, " +  //22
				"pref, " +	//23
				"3dlu, " +  //24
				"pref, " +	//25
				"5dlu, " +  //26
				"pref"); 	//27
		
		PanelBuilder builder = new PanelBuilder(layout, this);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();
		
		//linke Seite
		builder.addSeparator("Bearbeitung", cc.xyw( 1, 1, 3));
		builder.addLabel("Sachbearb.:", cc.xy( 1, 3));
		builder.add(getSachbearbeiterFeld(), cc.xy( 3, 3));
		builder.addLabel("Ansprechpar.:", cc.xy( 1, 5));
		builder.add(getAnsprechpartnerFeld(), cc.xy( 3, 5));
		builder.addLabel("Sachkde LFA:", cc.xy( 1, 7));
		builder.add(getSachkundelfaFeld(), cc.xy( 3, 7));
		builder.addSeparator("Analyse", cc.xyw( 1, 9, 3));
		builder.addLabel("Analyse dgft:", cc.xy( 1, 11));
		builder.add(getDurchgefuehrtFeld(), cc.xy( 3, 11));
		builder.addLabel("wann:", cc.xy( 1, 13));
		builder.add(getWannDatum(), cc.xy( 3, 13));
		builder.addLabel("Analysemonat:", cc.xy( 1, 15));
		builder.add(getAnalyseMonatFeld(), cc.xy( 3, 15));
		builder.addSeparator("Kontrolle", cc.xyw( 1, 17, 3));
		builder.addLabel("Dekra/TÜV-T.:", cc.xy( 1, 19));
		builder.add(getDekraTuevFeld(), cc.xy( 3, 19));
		builder.addLabel("Wiedervorlage:", cc.xy( 1, 21));
		builder.add(getWiedervorlageDatum(), cc.xy(3, 21));
		builder.add(getMangelCheck(), cc.xy( 3, 23));
		builder.addLabel("Frist:", cc.xy( 1, 25));
		builder.add(getFristDatum(), cc.xy( 3, 25));
		
		//rechte Seite
		builder.addSeparator("Erfassung", cc.xyw( 5, 1, 3));
		builder.addLabel("Antrag vom:", cc.xy( 5, 3));
		builder.add(getAntragDatum(), cc.xy( 7, 3));
		builder.addLabel("Genehmigung:", cc.xy( 5, 5));
		builder.add(getGenehmigungDatum(), cc.xy( 7, 5));
		builder.addLabel("Änderungsgen.:", cc.xy( 5, 7));
		builder.add(getAenderungDatum(), cc.xy( 7, 7));
		builder.addLabel("letzte Änder.:", cc.xy( 5, 9));
		builder.add(getLetzteAenderDatum(), cc.xy( 7, 9));
		builder.addLabel("Anschreiben:", cc.xy( 5, 11));
		builder.add(getAnschreibenFeld(), cc.xy( 7, 11));
		
		builder.add(getAbgemeldetCheck(), cc.xy( 5, 13, "l,d"));
		builder.add(getAbwasserfreiCheck(), cc.xy( 7, 13));
		builder.add(getWaschanlageCheck(), cc.xy( 5, 15, "l,d"));
		builder.add(getESatzungCheck(), cc.xy( 7, 15));
		
		builder.addSeparator("Bemerkungen", cc.xyw( 5, 17, 3));
		JScrollPane bemerkungsScroller = new JScrollPane(getAnh49BemerkungArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.add(bemerkungsScroller, cc.xywh( 5, 19, 3, 7));
		
		// unten
		JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveAnh49Button());
		builder.add(buttonBar, cc.xyw( 1, 27, 7));
		
		
	}
	public void fetchFormData() {
		fachdaten = Anh49Fachdaten.getAnh49ByObjekt(hauptModul.getObjekt());
		AUIKataster.debugOutput("Anhang 49 Objekt aus DB geholt: " + fachdaten, "ObjektAnh49Panel.fetchFormData");	
	}
	
	public void updateForm() {
		if (fachdaten != null) {
			if (fachdaten.getAbgemeldet() != null) {
				if (fachdaten.getAbgemeldet() == true) {
					getAbgemeldetCheck().setSelected(true);
				}
				else {
					getAbgemeldetCheck().setSelected(false);
				}
			}
//			getAbgemeldetCheck().setSelected(fachdaten.isAbgemeldet());
			
			if (fachdaten.getAbwasserfrei() != null) {
				if (fachdaten.getAbwasserfrei() == true) {
					getAbwasserfreiCheck().setSelected(true);
				}
				else {
					getAbwasserfreiCheck().setSelected(false);
				}
			}
			if (fachdaten.getAenderungsgenehmigung() != null) {
				getAenderungDatum().setDate(fachdaten.getAenderungsgenehmigung());
			}
			if (fachdaten.getAnalysemonat() != null) {
				getAnalyseMonatFeld().setText(fachdaten.getAnalysemonat());
			}
			if (fachdaten.getBemerkungen() != null) {
				getAnh49BemerkungArea().setText(fachdaten.getBemerkungen());
			}
			if (fachdaten.getAnschreiben() != null) {
				getAnschreibenFeld().setText(fachdaten.getAnschreiben());
			}
			if (fachdaten.getWiedervorlage() != null) {
				getWiedervorlageDatum().setDate(fachdaten.getWiedervorlage());
			}
			if (fachdaten.getAnsprechpartnerIn() != null) {
				getAnsprechpartnerFeld().setText(fachdaten.getAnsprechpartnerIn().toString());
			}
			if (fachdaten.getAntragvom() != null) {
				getAntragDatum().setDate(fachdaten.getAntragvom());
			}
			if (fachdaten.getBehoben() != null) {
				if (fachdaten.getBehoben() == true) {
					getBehobenCheck().setSelected(true);
				}
				else {
					getBehobenCheck().setSelected(false);
				}
			}
			if (fachdaten.getDekraTuevTermin() != null) {
				getDekraTuevFeld().setText(fachdaten.getDekraTuevTermin().toString());
			}
			if (fachdaten.getDurchgefuehrt() != null) {
				getDurchgefuehrtFeld().setText(fachdaten.getDurchgefuehrt().toString());
			}
			if (fachdaten.getESatzung() != null) {
				if (fachdaten.getESatzung() == true) {
					getESatzungCheck().setSelected(true);
				}
				else {
					getESatzungCheck().setSelected(false);
				}
			}
			if (fachdaten.getFrist() != null) {
				getFristDatum().setDate(fachdaten.getFrist());
			}
			if (fachdaten.getGenehmigung() != null) {
				getGenehmigungDatum().setDate(fachdaten.getGenehmigung());
			}
			if (fachdaten.getLetztesAnschreiben() != null) {
				getLetzteAenderDatum().setDate(fachdaten.getLetztesAnschreiben());
			}
			if (fachdaten.getMaengel() != null) {
				if (fachdaten.getMaengel() == true) {
					getMangelCheck().setSelected(true);
				}
				else {
					getMangelCheck().setSelected(false);
				}
			}
			if (fachdaten.getSachbearbeiterIn() != null) {
				getSachbearbeiterFeld().setText(fachdaten.getSachbearbeiterIn());
			}
			if (fachdaten.getSachkundelfa() != null) {
				getSachkundelfaFeld().setText(fachdaten.getSachkundelfa());
			}
			if (fachdaten.getSeitwann() != null) {
				getWannDatum().setDate(fachdaten.getSeitwann());
			}
			if (fachdaten.getWaschanlage() != null) {
				if (fachdaten.getWaschanlage() == true) {
					getWaschanlageCheck().setSelected(true);
				}
				else {
					getWaschanlageCheck().setSelected(false);
				}
			}
		} else {
			enableAll(false);
			hauptModul.getFrame().changeStatus("FEHLER: Kein Anhang 49 Objekt gefunden!", HauptFrame.ERROR_COLOR);
		}
	}
	
	public void clearForm() {
		getAbgemeldetCheck().setSelected(false);
		getAbwasserfreiCheck().setSelected(false);
		getAenderungDatum().setDate(null);
		getAnalyseMonatFeld().setText(null);
		getAnh49BemerkungArea().setText(null);
		getAnschreibenFeld().setText(null);
		getWiedervorlageDatum().setDate(null);
		getAnsprechpartnerFeld().setText(null);
		getAntragDatum().setDate(null);
		getBehobenCheck().setSelected(false);
		getDekraTuevFeld().setText(null);
		getDurchgefuehrtFeld().setText(null);
		getESatzungCheck().setSelected(false);
		getFristDatum().setDate(null);
		getGenehmigungDatum().setDate(null);
		getLetzteAenderDatum().setDate(null);
		getMangelCheck().setSelected(false);
		getSachbearbeiterFeld().setText(null);
		getSachkundelfaFeld().setText(null);
		getWannDatum().setDate(null);
		getWaschanlageCheck().setSelected(false);
	}
	
	public void enableAll(boolean enabled) {
		// Wenn das Fachdaten-Objekt null ist, 
		// können die Elemente nicht wieder aktiviert werden:
		if (!(enabled && (fachdaten == null))) {
			getAbgemeldetCheck().setEnabled(enabled);
			getAbwasserfreiCheck().setEnabled(enabled);
			getAenderungDatum().setEnabled(enabled);
			getAnalyseMonatFeld().setEnabled(enabled);
			getAnh49BemerkungArea().setEnabled(enabled);
			getAnschreibenFeld().setEnabled(enabled);
			getWiedervorlageDatum().setEnabled(enabled);
			getAnsprechpartnerFeld().setEnabled(enabled);
			getAntragDatum().setEnabled(enabled);
			getBehobenCheck().setEnabled(enabled);
			getDekraTuevFeld().setEnabled(enabled);
			getDurchgefuehrtFeld().setEnabled(enabled);
			getESatzungCheck().setEnabled(enabled);
			getFristDatum().setEnabled(enabled);
			getGenehmigungDatum().setEnabled(enabled);
			getLetzteAenderDatum().setEnabled(enabled);
			getMangelCheck().setEnabled(enabled);
			getSachbearbeiterFeld().setEnabled(enabled);
			getSachkundelfaFeld().setEnabled(enabled);
			getWannDatum().setEnabled(enabled);
			getWaschanlageCheck().setEnabled(enabled);
			getSaveAnh49Button().setEnabled(enabled);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Anh49Fachdaten getFachdaten() {
		return fachdaten;
	}
	
	private boolean saveAnh49Daten() {
		boolean success;
		
		if (getAbgemeldetCheck().isSelected())  {
			fachdaten.setAbgemeldet(true);
		} else {
			fachdaten.setAbgemeldet(false);
		}

//		fachdaten.setAbgemeldet(getAbgemeldetCheck().isSelected());
		
		if (getAbwasserfreiCheck().isSelected())  {
			fachdaten.setAbwasserfrei(true);
		} else {
			fachdaten.setAbwasserfrei(false);
		}
		
		String analysemnt = analyseMonatFeld.getText();
		if ("".equals(analysemnt)) {
			fachdaten.setAnalysemonat(null);
		} else {
			fachdaten.setAnalysemonat(analysemnt);
		}
		
		String bemerkungen = anh49BemerkungArea.getText();
		if ("".equals(bemerkungen)) {
			fachdaten.setBemerkungen(null);
		} else {
			fachdaten.setBemerkungen(bemerkungen);
		}
		
		String anschreiben = anschreibenFeld.getText();
		if ("".equals(anschreiben)) {
			fachdaten.setAnschreiben(null);
		} else {
			fachdaten.setAnschreiben(anschreiben);
		}
		
		Date wiedervorlage = wiedervorlageDatum.getDate();
		fachdaten.setWiedervorlage(wiedervorlage);
		
		String ansprechpartner = ansprechpartnerFeld.getText();
		if ("".equals(ansprechpartner)) {
			fachdaten.setAnsprechpartnerIn(null);
		} else {
			fachdaten.setAnsprechpartnerIn(ansprechpartner);
		}
		
		if (getBehobenCheck().isSelected())  {
			fachdaten.setBehoben(true);
		} else {
			fachdaten.setBehoben(false);
		}
		
		Integer dekratuev = ((IntegerField)dekraTuevFeld).getIntValue();
		fachdaten.setDekraTuevTermin(dekratuev);
		
		Integer durchgefuehrt = ((IntegerField)durchgefuehrtFeld).getIntValue();
		fachdaten.setDurchgefuehrt(durchgefuehrt);
		
		if (getESatzungCheck().isSelected())  {
			fachdaten.setESatzung(true);
		} else {
			fachdaten.setESatzung(false);
		}
		
		Date frist = fristDatum.getDate();
		fachdaten.setFrist(frist);	
		
		Date antrag = antragDatum.getDate();
		fachdaten.setAntragvom(antrag);
		
		Date genehmigung = genehmigungDatum.getDate();
		fachdaten.setGenehmigung(genehmigung);	
		
		Date aenderung = aenderungDatum.getDate();
		fachdaten.setAenderungsgenehmigung(aenderung);				
		
		Date letzteaend = letzteAenderDatum.getDate();
		fachdaten.setLetztesAnschreiben(letzteaend);	
		
		if (getMangelCheck().isSelected())  {
			fachdaten.setMaengel(true);
		} else {
			fachdaten.setMaengel(false);
		}
		
		String sachbearbeiter = sachbearbeiterFeld.getText();
		if ("".equals(sachbearbeiter)) {
			fachdaten.setSachbearbeiterIn(null);
		} else {
			fachdaten.setSachbearbeiterIn(sachbearbeiter);
		}
		
		String sachkunde = sachkundelfaFeld.getText();
		if ("".equals(sachkunde)) {
			fachdaten.setSachkundelfa(null);
		} else {
			fachdaten.setSachkundelfa(sachkunde);
		}
		
		Date wann = wannDatum.getDate();
		fachdaten.setSeitwann(wann);	
		
		if (getWaschanlageCheck().isSelected())  {
			fachdaten.setWaschanlage(true);
		} else {
			fachdaten.setWaschanlage(false);
		}
		
		success = Anh49Fachdaten.saveFachdaten(fachdaten);
		
		if (!success) {
			AUIKataster.debugOutput("Anh49 Objekt " + fachdaten
					+ " konnte nicht gespeichert werden!",
			"ObjektAnh50Panel.saveAnh49Daten");
		}
		
		return success;
	}
	
	public void completeObjekt() {
		if (hauptModul.isNew() || fachdaten == null) {
			// Neues Anhang49-Objekt erzeugen
			fachdaten = new Anh49Fachdaten();
			// Objekt_Id setzen
			fachdaten.setBasisObjekt(hauptModul.getObjekt());
			
			// Anhang49-Objekt speichern
			Anh49Fachdaten.saveFachdaten(fachdaten);
			AUIKataster.debugOutput("Neues Anh49 Objekt "+fachdaten+" gespeichert.", "ObjektBearbeiten.completeObjekt");
		}
	}
	
	private JCheckBox getAbgemeldetCheck() {
		if (abgemeldetCheck == null) {
			abgemeldetCheck = new JCheckBox("abgemeldet");
		}
		return abgemeldetCheck;
	}
	
	private JCheckBox getAbwasserfreiCheck() {
		if (abwasserfreiCheck == null) {
			abwasserfreiCheck = new JCheckBox("abwasserfrei");
		}
		return abwasserfreiCheck;
	}
	
	private TextFieldDateChooser getAenderungDatum() {
		if (aenderungDatum == null) {			
			aenderungDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return aenderungDatum;
	}
	
	private JTextField getAnalyseMonatFeld() {
		if (analyseMonatFeld == null) {			
			analyseMonatFeld = new LimitedTextField(50);
		}
		return analyseMonatFeld;
	}
	
	private JTextArea getAnh49BemerkungArea() {
		if (anh49BemerkungArea == null) {			
			anh49BemerkungArea = new LimitedTextArea(150);
			anh49BemerkungArea.setLineWrap(true);
			anh49BemerkungArea.setWrapStyleWord(true);
		}
		return anh49BemerkungArea;
	}
	
	private JTextField getAnschreibenFeld() {
		if (anschreibenFeld == null) {			
			anschreibenFeld = new LimitedTextField(50);
		}
		return anschreibenFeld;
	}
	
	private TextFieldDateChooser getWiedervorlageDatum() {
		if (wiedervorlageDatum == null) {			
			wiedervorlageDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return wiedervorlageDatum;
	}
	
	private JTextField getAnsprechpartnerFeld() {
		if (ansprechpartnerFeld == null) {			
			ansprechpartnerFeld = new LimitedTextField(50);
		}
		return ansprechpartnerFeld;
	}
	
	private TextFieldDateChooser getAntragDatum() {
		if (antragDatum == null) {			
			antragDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return antragDatum;
	}
	
	private JCheckBox getBehobenCheck() {
		if (behobenCheck == null) {
			behobenCheck = new JCheckBox();
		}
		return behobenCheck;
	}
	
	private JFormattedTextField getDekraTuevFeld() {
		if (dekraTuevFeld == null) {			
			dekraTuevFeld = new IntegerField();
		}
		return dekraTuevFeld;
	}
	
	private JFormattedTextField getDurchgefuehrtFeld() {
		if (durchgefuehrtFeld == null) {			
			durchgefuehrtFeld = new IntegerField();
		}
		return durchgefuehrtFeld;
	}
	
	private JCheckBox getESatzungCheck() {
		if (eSatzungCheck == null) {
			eSatzungCheck = new JCheckBox("E-Satzung");
		}
		return eSatzungCheck;
	}
	
	private TextFieldDateChooser getFristDatum() {
		if (fristDatum == null) {			
			fristDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return fristDatum;
	}
	
	private TextFieldDateChooser getGenehmigungDatum() {
		if (genehmigungDatum == null) {			
			genehmigungDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return genehmigungDatum;
	}
	
	private TextFieldDateChooser getLetzteAenderDatum() {
		if (letzteAenderDatum == null) {			
			letzteAenderDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return letzteAenderDatum;
	}
	
	private JCheckBox getMangelCheck() {
		if (mangelCheck == null) {
			mangelCheck = new JCheckBox("Mangel");
		}
		return mangelCheck;
	}
	
	private JTextField getSachbearbeiterFeld() {
		if (sachbearbeiterFeld == null) {			
			sachbearbeiterFeld = new LimitedTextField(50);
		}
		return sachbearbeiterFeld;
	}
	
	private JTextField getSachkundelfaFeld() {
		if (sachkundelfaFeld == null) {			
			sachkundelfaFeld = new LimitedTextField(50);
		}
		return sachkundelfaFeld;
	}
	
	private TextFieldDateChooser getWannDatum() {
		if (wannDatum == null) {			
			wannDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return wannDatum;
	}
	
	private JCheckBox getWaschanlageCheck() {
		if (waschanlageCheck == null) {
			waschanlageCheck = new JCheckBox("Waschanlage");
		}
		return waschanlageCheck;
	}
	
	private JButton getSaveAnh49Button() {
		if (saveAnh49Button == null) {
			saveAnh49Button = new JButton("Speichern");
			
			saveAnh49Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enableAll(false);
					if (saveAnh49Daten()) {
						hauptModul.getFrame().changeStatus("Anhang 49-Objekt erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
					} else {
						hauptModul.getFrame().changeStatus("Fehler beim Speichern des Objekts!", HauptFrame.ERROR_COLOR);
					}
					
					hauptModul.fillForm();
				}
			});
		}
		return saveAnh49Button;
	}
}

