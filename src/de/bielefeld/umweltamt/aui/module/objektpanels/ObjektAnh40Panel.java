/*
 * Created on 29.06.2005 by u633d
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

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.module.ObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Anhang 40"-Tab des ObjektBearbeiten-Moduls 
 * @author Gerd Genuit
 */
public class ObjektAnh40Panel extends JPanel {
	private String name;
	private ObjektBearbeiten hauptModul;
	
	// Widgets

	private JTextArea anh40BemerkungArea = null;
	private JTextField ansprechpartnerFeld = null;
	private JTextField sachbearbeiterravFeld = null;
	private JTextField sachbearbeiterheepenFeld = null;
	private JTextField klaeranlageFeld = null;
	private JTextField herkunftsbereichFeld = null;
	private JTextField prioritaetFeld = null;
	private JCheckBox wsgCheck = null;
	private JCheckBox genehmigungspflichtCheck = null;
	private JCheckBox nachtragCheck = null;
	private JCheckBox bimschCheck = null;
	private JFormattedTextField abwmengegenehmigtFeld = null;
	private JFormattedTextField abwmengeprodspezFeld = null;
	private JFormattedTextField abwmengegesamtFeld = null;
	private TextFieldDateChooser gen58Datum = null;
	private TextFieldDateChooser gen59Datum = null;
	
	private JButton saveAnh40Button = null;
	


	
	// Daten
	private Anh40Fachdaten fachdaten = null;
	
	//Listener
	private ActionListener editButtonListener;
	
	
	public ObjektAnh40Panel(ObjektBearbeiten hauptModul) {
		name = "Anhang 40";
		this.hauptModul = hauptModul;
		
		FormLayout layout = new FormLayout (
				"r:80dlu, 5dlu, 80dlu, 5dlu, r:65dlu, 5dlu, 80dlu", // Spalten
				"");
		
		
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.setDefaultDialogBorder();
		
		builder.appendSeparator("Fachdaten");
		builder.append("SachbearbeiterIn Rav.-Str.:", getSachbearbeiterravFeld());
		builder.append("", getWsgCheck());
		builder.nextLine();
		builder.append("SachbearbeiterIn Heepen.:", getSachbearbeiterheepenFeld());
		builder.append("", getGenehmigungspflichtCheck());
		builder.nextLine();
		builder.append("AnsprechpartnerIn:", getAnsprechpartnerFeld());
		builder.append("Genehmigung §58:", getGen58Datum());
		builder.nextLine();
		builder.append("Herkunftsbereich:", getHerkunftsbereichFeld());
		builder.append("Genehmigung §59:", getGen59Datum());
		builder.nextLine();
		builder.append("Kläranlage:", getKlaeranlageFeld());
		builder.append("Priorität:", getPrioritaetFeld());
		builder.nextLine();
		builder.append("Abwassermenge genehmigt:", getAbwmengegenehmigtFeld());
		builder.append("", getNachtragCheck());
		builder.nextLine();
		builder.append("Abwassermenge produktionsspez.:", getAbwmengeprodspezFeld());
		builder.append("", getBimschCheck());
		builder.nextLine();
		builder.append("Abwassermenge gesamt:", getAbwmengegesamtFeld());
		builder.nextLine();
		/*
		builder.appendSeparator("Lagerung");
		builder.nextLine();
		builder.append("Laugen:", getLaugenFeld());
		builder.append("Säuren:", getSaeurenFeld());
		builder.nextLine();
		builder.append("Frischöl:", getFrischoelFeld());
		builder.append("Altöl:", getAltoelFeld());
		builder.nextLine();
		builder.append("Lösungsmittel:", getLoesemittelFeld());
		builder.append("Lacke:", getLackeFeld());
		*/
		builder.appendSeparator("Bemerkungen");
		builder.appendRow("3dlu");
		builder.nextLine(2);
		JScrollPane bemerkungsScroller = new JScrollPane(getAnh40BemerkungArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:30dlu");
		builder.append(bemerkungsScroller, 7);
		
		JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveAnh40Button());
		builder.append(buttonBar, 7);
	}
	
	public void fetchFormData() throws RuntimeException {
		fachdaten = Anh40Fachdaten.getAnh40ByObjekt(hauptModul.getObjekt());
		AUIKataster.debugOutput("Anhang 40 Objekt aus DB geholt: ID" + fachdaten, "ObjektAnh50Panel.fetchFormData");
	}
	
	
	public void updateForm() throws RuntimeException {
		
		if (fachdaten != null) {
			if (fachdaten.getBemerkungen() != null) {
				getAnh40BemerkungArea().setText(fachdaten.getBemerkungen());
			}
			if (fachdaten.getAnsprechpartner() != null) {
				getAnsprechpartnerFeld().setText(fachdaten.getAnsprechpartner());
			}
			if (fachdaten.getHerkunftsbereich() != null) {
				getHerkunftsbereichFeld().setText(fachdaten.getHerkunftsbereich());
			}
			if (fachdaten.getSachbearbeiterrav() != null) {
				getSachbearbeiterravFeld().setText(fachdaten.getSachbearbeiterrav());
			}
			if (fachdaten.getSachbearbeiterheepen() != null) {
				getSachbearbeiterheepenFeld().setText(fachdaten.getSachbearbeiterheepen());
			}
			if (fachdaten.getKlaeranlage() != null) {
				getKlaeranlageFeld().setText(fachdaten.getKlaeranlage());
			}
			if (fachdaten.getWsg() != null) {
				if (fachdaten.getWsg() == true) {
					getWsgCheck().setSelected(true);
				}
				else {
					getWsgCheck().setSelected(false);
				}
			}
			if (fachdaten.getGenehmigungspflicht() != null) {
				if (fachdaten.getGenehmigungspflicht() == true) {
					getGenehmigungspflichtCheck().setSelected(true);
				}
				else {
					getGenehmigungspflichtCheck().setSelected(false);
				}
			}
			if (fachdaten.getNachtrag() != null) {
				if (fachdaten.getNachtrag() == true) {
					getNachtragCheck().setSelected(true);
				}
				else {
					getNachtragCheck().setSelected(false);
				}
			}
			if (fachdaten.getBimsch() != null) {
				if (fachdaten.getBimsch() == true) {
					getBimschCheck().setSelected(true);
				}
				else {
					getBimschCheck().setSelected(false);
				}
			}
			if (fachdaten.getAbwmengegenehmigt() != null) {
				getAbwmengegenehmigtFeld().setText(fachdaten.getAbwmengegenehmigt().toString());
			}
			if (fachdaten.getAbwmengeprodspez() != null) {
				getAbwmengeprodspezFeld().setText(fachdaten.getAbwmengeprodspez().toString());
			}
			if (fachdaten.getAbwmengegesamt() != null) {
				getAbwmengegesamtFeld().setText(fachdaten.getAbwmengegesamt().toString());
			}
			if (fachdaten.getGen58() != null) {
				getGen58Datum().setDate(fachdaten.getGen58());
			}
			if (fachdaten.getGen59() != null) {
				getGen59Datum().setDate(fachdaten.getGen59());
			}
		}

		}
	
	
	public void clearForm() {
		
		getAnh40BemerkungArea().setText(null);
		getAnsprechpartnerFeld().setText(null);
		getSachbearbeiterravFeld().setText(null);
		getSachbearbeiterheepenFeld().setText(null);
		getKlaeranlageFeld().setText(null);
		getHerkunftsbereichFeld().setText(null);
		getWsgCheck().setSelected(false);
		getGenehmigungspflichtCheck().setSelected(false);
		getNachtragCheck().setSelected(false);
		getBimschCheck().setSelected(false);
		getAbwmengegenehmigtFeld().setText(null);
		getAbwmengeprodspezFeld().setText(null);
		getAbwmengegesamtFeld().setText(null);
		getGen58Datum().setDate(null);
		getGen59Datum().setDate(null);
	}
	
	public void enableAll(boolean enabled) {
		
		getAnh40BemerkungArea().setEnabled(enabled);
		getAnsprechpartnerFeld().setEnabled(enabled);
		getSachbearbeiterravFeld().setEnabled(enabled);
		getSachbearbeiterheepenFeld().setEnabled(enabled);
		getKlaeranlageFeld().setEnabled(enabled);
		getHerkunftsbereichFeld().setEnabled(enabled);
		getWsgCheck().setEnabled(enabled);
		getGenehmigungspflichtCheck().setEnabled(enabled);
		getNachtragCheck().setEnabled(enabled);
		getBimschCheck().setEnabled(enabled);
		getAbwmengegenehmigtFeld().setEnabled(enabled);
		getAbwmengeprodspezFeld().setEnabled(enabled);
		getAbwmengegesamtFeld().setEnabled(enabled);
		getGen58Datum().setEnabled(enabled);
		getGen59Datum().setEnabled(enabled);

	}

	
	private boolean saveAnh40Daten() {
		boolean success;
		
		String bemerkungen = anh40BemerkungArea.getText();
		if ("".equals(bemerkungen)) {
			fachdaten.setBemerkungen(null);
		} else {
			fachdaten.setBemerkungen(bemerkungen);
		}
		
		String ansprechp = ansprechpartnerFeld.getText();
		if ("".equals(ansprechp)) {
			fachdaten.setAnsprechpartner(null);
		} else {
			fachdaten.setAnsprechpartner(ansprechp);
		}
		
		String sachbearbrav = sachbearbeiterravFeld.getText();
		if ("".equals(sachbearbrav)) {
			fachdaten.setSachbearbeiterrav(null);
		} else {
			fachdaten.setSachbearbeiterrav(sachbearbrav);
		}
		
		String sachbearbheepen = sachbearbeiterheepenFeld.getText();
		if ("".equals(sachbearbheepen)) {
			fachdaten.setSachbearbeiterheepen(null);
		} else {
			fachdaten.setSachbearbeiterheepen(sachbearbheepen);
		}
		
		String klaeranlage = klaeranlageFeld.getText();
		if ("".equals(klaeranlage)) {
			fachdaten.setKlaeranlage(null);
		} else {
			fachdaten.setKlaeranlage(klaeranlage);
		}
		
		String herkunft = herkunftsbereichFeld.getText();
		if ("".equals(herkunft)) {
			fachdaten.setHerkunftsbereich(null);
		} else {
			fachdaten.setHerkunftsbereich(herkunft);
		}

		if (getWsgCheck().isSelected())  {
			fachdaten.setWsg(true);
		} else {
			fachdaten.setWsg(false);
		}		

		if (getGenehmigungspflichtCheck().isSelected())  {
			fachdaten.setGenehmigungspflicht(true);
		} else {
			fachdaten.setGenehmigungspflicht(false);
		}		

		if (getNachtragCheck().isSelected())  {
			fachdaten.setNachtrag(true);
		} else {
			fachdaten.setNachtrag(false);
		}
		
		if (getBimschCheck().isSelected())  {
			fachdaten.setBimsch(true);
		} else {
			fachdaten.setBimsch(false);
		}
		
		
		Integer abwgen = ((IntegerField)abwmengegenehmigtFeld).getIntValue();
		fachdaten.setAbwmengegenehmigt(abwgen);
		
		Integer abwprod = ((IntegerField)abwmengeprodspezFeld).getIntValue();
		fachdaten.setAbwmengeprodspez(abwprod);
		
		Integer abwges = ((IntegerField)abwmengegesamtFeld).getIntValue();
		fachdaten.setAbwmengegesamt(abwges);
		
		Date gen58 = gen58Datum.getDate();
		fachdaten.setGen58(gen58);
		
		Date gen59 = gen59Datum.getDate();
		fachdaten.setGen59(gen59);
		
		
		success = Anh40Fachdaten.saveAnh40(fachdaten);
		if (success) {
			AUIKataster.debugOutput("Anh 40 Objekt " + fachdaten.getObjektid() + " gespeichert.",
			"ObjektAnh40Panel.saveAnh40Daten");
		} else {
			AUIKataster.debugOutput("Anh 40 Objekt " + fachdaten
					+ " konnte nicht gespeichert werden!",
			"ObjektAnh40Panel.saveAnh40Daten");
		}
		return success;
	}
	
	public void completeObjekt() {
		if (hauptModul.isNew() || fachdaten == null) {
			// Neues Anhang 40 Objekt erzeugen
			fachdaten = new Anh40Fachdaten();
			// Objekt_Id setzen
			fachdaten.setBasisObjekt(hauptModul.getObjekt());
			
			// Anhang 40 Objekt speichern
			Anh40Fachdaten.saveAnh40(fachdaten);
			AUIKataster.debugOutput("Neues Anh 40 Objekt "+fachdaten+" gespeichert.", "ObjektBearbeiten.completeObjekt");
		}
	}
	


	private JButton getSaveAnh40Button() {
		if (saveAnh40Button == null) {
			saveAnh40Button = new JButton("Speichern");
			
			saveAnh40Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enableAll(false);
					if (saveAnh40Daten()) {
						hauptModul.getFrame().changeStatus("Anh 40 Objekt "+fachdaten.getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
					} else {
						hauptModul.getFrame().changeStatus("Fehler beim Speichern des Anh 40 Objekt!", HauptFrame.ERROR_COLOR);
					}
					
					hauptModul.fillForm();
				}
			});
		}
		return saveAnh40Button;
	}
	public String getName() {
		return name;
	}
	private JTextArea getAnh40BemerkungArea() {
		if (anh40BemerkungArea == null) {			
			anh40BemerkungArea = new LimitedTextArea(150);
			anh40BemerkungArea.setLineWrap(true);
			anh40BemerkungArea.setWrapStyleWord(true);
		}
		return anh40BemerkungArea;
	}
	private JTextField getAnsprechpartnerFeld() {
		if (ansprechpartnerFeld == null) {			
			ansprechpartnerFeld = new LimitedTextField(50);
		}
		return ansprechpartnerFeld;
	}
	private TextFieldDateChooser getGen58Datum() {
		if (gen58Datum == null) {			
			gen58Datum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return gen58Datum;
	}
	private TextFieldDateChooser getGen59Datum() {
		if (gen59Datum == null) {			
			gen59Datum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return gen59Datum;
	}
	private JTextField getHerkunftsbereichFeld() {
		if (herkunftsbereichFeld == null) {			
			herkunftsbereichFeld = new IntegerField();
		}
		return herkunftsbereichFeld;
	}
	private JTextField getSachbearbeiterravFeld() {
		if (sachbearbeiterravFeld == null) {			
			sachbearbeiterravFeld = new LimitedTextField(50);
		}
		return sachbearbeiterravFeld;
	}
	private JTextField getSachbearbeiterheepenFeld() {
		if (sachbearbeiterheepenFeld == null) {			
			sachbearbeiterheepenFeld = new LimitedTextField(50);
		}
		return sachbearbeiterheepenFeld;
	}
	private JCheckBox getWsgCheck() {
		if (wsgCheck == null) {
			wsgCheck = new JCheckBox("Wasserschutzgebiet");
		}
		return wsgCheck;
	}
	private JCheckBox getGenehmigungspflichtCheck() {
		if (genehmigungspflichtCheck == null) {
			genehmigungspflichtCheck = new JCheckBox("Genehmigungspflicht");
		}
		return genehmigungspflichtCheck;
	}
	private JCheckBox getNachtragCheck() {
		if (nachtragCheck == null) {
			nachtragCheck = new JCheckBox("Nachtragsgenehmigung");
		}
		return nachtragCheck;
	}
	private JCheckBox getBimschCheck() {
		if (bimschCheck == null) {
			bimschCheck = new JCheckBox("BImSch-Genehmigung");
		}
		return bimschCheck;
	}
	
	private JFormattedTextField getAbwmengegenehmigtFeld() {
		if (abwmengegenehmigtFeld == null) {			
			abwmengegenehmigtFeld = new IntegerField();
		}
		return abwmengegenehmigtFeld;
	}
	
	private JFormattedTextField getAbwmengeprodspezFeld() {
		if (abwmengeprodspezFeld == null) {			
			abwmengeprodspezFeld = new IntegerField();
		}
		return abwmengeprodspezFeld;
	}
	
	private JFormattedTextField getAbwmengegesamtFeld() {
		if (abwmengegesamtFeld == null) {			
			abwmengegesamtFeld = new IntegerField();
		}
		return abwmengegesamtFeld;
	}
	private JTextField getKlaeranlageFeld() {
		if (klaeranlageFeld == null) {			
			klaeranlageFeld = new LimitedTextField(50);
		}
		return klaeranlageFeld;
	}
	private JTextField getPrioritaetFeld() {
		if (prioritaetFeld == null) {			
			prioritaetFeld = new IntegerField();
		}
		return prioritaetFeld;
	}
}

