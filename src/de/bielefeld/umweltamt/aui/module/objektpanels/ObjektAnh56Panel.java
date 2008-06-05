/*
 * Datei:
 * $Id: ObjektAnh56Panel.java,v 1.1 2008-06-05 11:38:39 u633d Exp $
 * 
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/08/17 05:45:31  u633d
 * - Anhang 56 erstellt
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.module.ObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das Panel zum Bearbeiten von Druckereien
 * @author u633d
 */
public class ObjektAnh56Panel extends JPanel{
	private String name;
	private ObjektBearbeiten hauptModul;
	
	// Widgets
	
	private JTextField druckverfahrenFeld = null;
	private JTextField verbrauchFeld = null;
	private JTextField entsorgungFeld = null;
	private TextFieldDateChooser gen59Datum = null;
	private TextFieldDateChooser gen58Datum = null;
	private JCheckBox abaCheck = null;
	private JCheckBox genpflichtCheck = null;
	private JCheckBox abwasseranfallCheck = null;
	private JTextArea BemerkungenArea = null;
	
	private JButton saveAnh56Button = null;
	
	// Daten
	private Anh56Fachdaten fachdaten = null;
	
	//Listener
	private ActionListener editButtonListener;

	public ObjektAnh56Panel(ObjektBearbeiten hauptModul) {
		name = "Druckerei";
		this.hauptModul = hauptModul;
	
		FormLayout layout = new FormLayout (
				"r:90dlu, 5dlu, 95dlu, 5dlu, r:0dlu, 0dlu, 90dlu", // Spalten
				"");
		
		
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.setDefaultDialogBorder();
		
		builder.appendSeparator("Fachdaten");
		builder.append("Druckverfahren:", getDruckverfahrenFeld());
		builder.append("", getAbwasseranfallCheck());
		builder.nextLine();
		builder.append("Wasserverbrauch:", getVerbrauchFeld());
		builder.append("", getAbaCheck());
		builder.nextLine();
		builder.append("Entsorgung:", getEntsorgungFeld());
		builder.append("", getGenpflichtCheck());
		builder.nextLine();
		builder.append("Datum 58er Genehmigung:", getGen58Datum());
		builder.nextLine();
		builder.append("Datum 59er Genehmigung:", getGen59Datum());
		builder.nextLine();
		
		builder.appendSeparator("Bemerkungen");
		builder.appendRow("3dlu");
		builder.nextLine(2);
		JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:30dlu");
		builder.append(bemerkungsScroller, 7);
		
		JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveAnh56Button());
		builder.append(buttonBar, 7);
		
	}
	
	public void completeObjekt() {
		if (hauptModul.isNew() || fachdaten == null) {
			// Neues Anhang 56 Objekt erzeugen
			fachdaten = new Anh56Fachdaten();
			// Objekt_Id setzen
			fachdaten.setBasisObjekt(hauptModul.getObjekt());
			
			// Anhang 56 Objekt speichern
			Anh56Fachdaten.saveFachdaten(fachdaten);
			AUIKataster.debugOutput("Neues Anh 56 Objekt "+fachdaten+" gespeichert.", "ObjektBearbeiten.completeObjekt");
		}
	}

	private boolean saveAnh56Daten() {
		boolean success;
		
		String bemerkungen = BemerkungenArea.getText();
		if ("".equals(bemerkungen)) {
			fachdaten.setBemerkungen(null);
		} else {
			fachdaten.setBemerkungen(bemerkungen);
		}
		
		String druckverf = druckverfahrenFeld.getText();
		if ("".equals(druckverf)) {
			fachdaten.setDruckverfahren(null);
		} else {
			fachdaten.setDruckverfahren(druckverf);
		}
		
		String verbrauch = verbrauchFeld.getText();
		if ("".equals(verbrauch)) {
			fachdaten.setVerbrauch(null);
		} else {
			fachdaten.setVerbrauch(verbrauch);
		}
		
		String entsorgung = entsorgungFeld.getText();
		if ("".equals(entsorgung)) {
			fachdaten.setEntsorgung(null);
		} else {
			fachdaten.setEntsorgung(entsorgung);
		}
		
		Date gen58 = gen58Datum.getDate();
		fachdaten.setGen58(gen58);	
		
		Date gen59 = gen59Datum.getDate();
		fachdaten.setGen59(gen59);
		
		
		if (getAbaCheck().isSelected())  {
			fachdaten.setAba(true);
		} else {
			fachdaten.setAba(false);
		}

		if (getGenpflichtCheck().isSelected())  {
			fachdaten.setGenpflicht(true);
		} else {
			fachdaten.setGenpflicht(false);
		}
		
		if (getAbwasseranfallCheck().isSelected())  {
			fachdaten.setAbwasseranfall(true);
		} else {
			fachdaten.setAbwasseranfall(false);
		}
		
	

		
		success = Anh56Fachdaten.saveFachdaten(fachdaten);
		if (success) {
			AUIKataster.debugOutput("Anh 56 Objekt " + fachdaten.getObjektid() + " gespeichert.",
			"ObjektAnh56Panel.saveFachdaten");
		} else {
			AUIKataster.debugOutput("Anh 56 Objekt " + fachdaten
					+ " konnte nicht gespeichert werden!",
			"ObjektAnh56Panel.saveFachdaten");
		}
		return success;
	}

	public void enableAll(boolean enabled) {
		
		getBemerkungenArea().setEnabled(enabled);
		getDruckverfahrenFeld().setEnabled(enabled);
		getVerbrauchFeld().setEnabled(enabled);
		getEntsorgungFeld().setEnabled(enabled);

		getGen58Datum().setEnabled(enabled);
		getGen59Datum().setEnabled(enabled);
		
		getAbaCheck().setEnabled(enabled);
		getGenpflichtCheck().setEnabled(enabled);
		getAbwasseranfallCheck().setEnabled(enabled);
	
	}

	public void clearForm() {
		
		getBemerkungenArea().setText(null);
		getDruckverfahrenFeld().setText(null);
		getVerbrauchFeld().setText(null);
		getEntsorgungFeld().setText(null);	
		
		getGen58Datum().setDate(null);
		getGen59Datum().setDate(null);
		
		getAbaCheck().setSelected(false);
		getGenpflichtCheck().setSelected(false);
		getAbwasseranfallCheck().setSelected(false);
	
	}

	public void updateForm() throws RuntimeException {
	
	if (fachdaten != null) {
		if (fachdaten.getBemerkungen() != null) {
			getBemerkungenArea().setText(fachdaten.getBemerkungen());
		}
		if (fachdaten.getDruckverfahren() != null) {
			getDruckverfahrenFeld().setText(fachdaten.getDruckverfahren());
		}
		if (fachdaten.getVerbrauch() != null) {
			getVerbrauchFeld().setText(fachdaten.getVerbrauch());
		}
		if (fachdaten.getEntsorgung() != null) {
			getEntsorgungFeld().setText(fachdaten.getEntsorgung());
		}
		
		if (fachdaten.getGen58() != null) {
			getGen58Datum().setDate(fachdaten.getGen58());
		}
		if (fachdaten.getGen59() != null) {
			getGen59Datum().setDate(fachdaten.getGen59());
		}


		if (fachdaten.getAba() != null) {
			if (fachdaten.getAba() == true) {
				getAbaCheck().setSelected(true);
			}
			else {
				getAbaCheck().setSelected(false);
			}
		}
		if (fachdaten.getGenpflicht() != null) {
			if (fachdaten.getGenpflicht() == true) {
				getGenpflichtCheck().setSelected(true);
			}
			else {
				getGenpflichtCheck().setSelected(false);
			}
		}
		if (fachdaten.getAbwasseranfall() != null) {
			if (fachdaten.getAbwasseranfall() == true) {
				getAbwasseranfallCheck().setSelected(true);
			}
			else {
				getAbwasseranfallCheck().setSelected(false);
			}
		}
	}
	
	}

	public void fetchFormData() throws RuntimeException {
		fachdaten = Anh56Fachdaten.getAnh56ByObjekt(hauptModul.getObjekt());
		AUIKataster.debugOutput("Anhang 56 Objekt aus DB geholt: ID" + fachdaten, "ObjektAnh56Panel.fetchFormData");
	}

	private JButton getSaveAnh56Button() {
		if (saveAnh56Button == null) {
			saveAnh56Button = new JButton("Speichern");
			
			saveAnh56Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enableAll(false);
					if (saveAnh56Daten()) {
						hauptModul.getFrame().changeStatus("Anh 56 Objekt "+fachdaten.getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
					} else {
						hauptModul.getFrame().changeStatus("Fehler beim Speichern des Anh 56 Objekt!", HauptFrame.ERROR_COLOR);
					}
					
					hauptModul.fillForm();
				}
			});
		}
		return saveAnh56Button;
	}

	public String getName() {
		return name;
	}

	private JCheckBox getAbaCheck() {
		if (abaCheck == null) {
			abaCheck = new JCheckBox("Abwasserbehandlung");
		}
		return abaCheck;
	}

	private JCheckBox getAbwasseranfallCheck() {
		if (abwasseranfallCheck == null) {
			abwasseranfallCheck = new JCheckBox("Abwasseranfall");
		}
		return abwasseranfallCheck;
	}
	private JTextField getDruckverfahrenFeld() {
		if (druckverfahrenFeld == null) {			
			druckverfahrenFeld = new LimitedTextField(150);
		}
		return druckverfahrenFeld;
	}
	private JTextField getEntsorgungFeld() {
		if (entsorgungFeld == null) {			
			entsorgungFeld = new LimitedTextField(150);
		}
		return entsorgungFeld;
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
	private JCheckBox getGenpflichtCheck() {
		if (genpflichtCheck == null) {
			genpflichtCheck = new JCheckBox("Genehmigungspflicht");
		}
		return genpflichtCheck;
	}
	private JTextField getVerbrauchFeld() {
		if (verbrauchFeld == null) {			
			verbrauchFeld = new LimitedTextField(150);
		}
		return verbrauchFeld;
	}
	private JTextArea getBemerkungenArea() {
		if (BemerkungenArea == null) {			
			BemerkungenArea = new LimitedTextArea(255);
			BemerkungenArea.setLineWrap(true);
			BemerkungenArea.setWrapStyleWord(true);
		}
		return BemerkungenArea;
	}
}

