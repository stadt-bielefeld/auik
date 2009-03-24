/*
 * Datei:
 * $Id: ObjektAnh55Panel.java,v 1.2 2009-03-24 12:35:22 u633d Exp $
 * 
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.10  2006/10/17 07:54:29  u633d
 * Anhang 52 (Chemische Wäschereien) haben nun einen eigenen Tab.
 *
 * Revision 1.9  2006/09/28 07:31:34  u633d
 * Bei dem Verwaltungsverfahren eine Bemerkungs-spalte hinzugefuegt
 *
 * Revision 1.8  2006/09/26 09:48:38  u633d
 * Anh 55 bereinigt; settings wieder auf UTEDB2 gesetzt
 *
 * Revision 1.7  2006/09/25 12:17:17  u633d
 * Wäscherei funzt
 *
 * Revision 1.6  2006/09/25 09:36:59  u633d
 * Waescherei Panel
 *
 * Revision 1.5  2006/09/25 08:22:25  u633d
 * *** empty log message ***
 *
 * Revision 1.4  2006/09/21 11:52:56  u633d
 * *** empty log message ***
 *
 * Revision 1.3  2006/09/21 07:47:30  u633d
 * Oberfläche erstellt
 *
 * Revision 1.2  2006/09/20 06:45:42  u633d
 * *** empty log message ***
 *
 * Revision 1.1  2006/09/13 11:06:21  u633d
 * *** empty log message ***
 *
 * Revision 1.1  2005/08/17 05:45:31  u633d
 * - Anhang 55 erstellt
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import sun.misc.FormattedFloatingDecimal;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh55Fachdaten;
import de.bielefeld.umweltamt.aui.module.ObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;

/**
 * Das Panel zum Bearbeiten von Druckereien
 * @author u633d
 */
public class ObjektAnh55Panel extends JPanel{
	private String name;
	private ObjektBearbeiten hauptModul;
	
	// Widgets
	private JTextField sachbearbeiterFeld = null;
	private JTextField entgebIdFeld = null;//
	private JTextField mengewaescheFeld = null;//
	private JTextField sonsttexFeld = null;//
	private DoubleField monatwasserverbFeld = null;//
	
	private JTextArea waschsituationArea = null;
	
	private JTextField ansprechpartnerFeld = null;//
	private JTextField brancheFeld = null;//
	
	private JTextArea BemerkungenArea = null;//
	
	private JCheckBox abgemeldetCheck = null;//
	private JCheckBox putztuecherCheck = null;//
	private JCheckBox teppichCheck = null;//
	private JCheckBox mattenCheck = null;//
	private JCheckBox haushaltstexCheck = null;//
	private JCheckBox berufsklCheck = null;//
	private JCheckBox gasthotelCheck = null;//
	private JCheckBox krankenhausCheck = null;
	private JCheckBox heimwaescheCheck = null;
	private JFormattedTextField anteilwaschgutFeld = null;
	private JFormattedTextField anteilgesamtwaschgutFeld = null;
	private JCheckBox betrwasseraufberCheck = null;
	private JCheckBox chlorCheck = null;
	private JCheckBox aktivchlorCheck = null;
	private JCheckBox vliesCheck = null;
	private JCheckBox fischCheck = null;
	private JCheckBox loesungsmittelCheck = null;
	private JButton saveAnh55Button = null;
	
	// Daten
	private Anh55Fachdaten fachdaten = null;
	
	//Listener
	private ActionListener editButtonListener;

	public ObjektAnh55Panel(ObjektBearbeiten hauptModul) {
		name = "Wäscherei";
		this.hauptModul = hauptModul;
	
		FormLayout layout = new FormLayout (
				"r:90dlu, 5dlu, 95dlu, 5dlu, r:0dlu, 0dlu, 90dlu", // Spalten
				"");
		
		
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.setDefaultDialogBorder();
		
		builder.appendSeparator("Fachdaten");
		builder.append("Entwässerungsgebiet:", getEntgebIdFeld());
		builder.append("", getAbgemeldetCheck());
		builder.nextLine();
		builder.append("Branche:", getBrancheFeld());
		builder.append("", getPutztuecherCheck());
		builder.nextLine();
		builder.append("Ansprechpartner:",  getAnsprechpartnerFeld());
		builder.append("", getMattenCheck());
		builder.nextLine();
		builder.append("Menge:", getMengeFeld());
		builder.append("", getTeppichCheck());
		builder.nextLine();
		builder.append("Sonstige Textilien:", getSonsttexFeld());
		builder.append("", getBerufsklCheck());
		builder.nextLine();
		builder.append("Monatl. Wasserverbrauch:", getMonatwasserverbFeld());
		builder.append("", getHaushaltstexCheck());
		builder.nextLine();
		builder.append("Anteil am Waschgut:", getAnteilwaschgutFeld());
		builder.append("", getGasthotelCheck());
		builder.nextLine();
		builder.append("Anteil am Gesamtwaschgut:", getGesamtwaschgutFeld());
		builder.append("", getKrankenhausCheck());
		builder.nextLine();
		builder.append("Sachbearbeiter/in:", getSachbearbeiterFeld());
		builder.append("", getHeimwaescheCheck());
		builder.nextLine();
		builder.append("", getVliesCheck());
		builder.append("", getFischCheck());
		builder.nextLine();
		builder.append("", getLoesungsmittelCheck());
		builder.append("", getBetrwasseraufberCheck());
		builder.nextLine();
		builder.append("", getChlorCheck());
		builder.append("", getAktivchlorCheck());
		builder.nextLine();
		
		builder.appendSeparator("Wasch-Situation");
		builder.appendRow("3dlu");
		builder.nextLine(2);
		JScrollPane waschsituationScroller = new JScrollPane(getWaschsituationArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:30dlu");
		builder.append(waschsituationScroller, 7);
		
		builder.appendSeparator("Bemerkungen");
		builder.appendRow("3dlu");
		builder.nextLine(2);
		JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:30dlu");
		builder.append(bemerkungsScroller, 7);
		
		JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveAnh55Button());
		builder.append(buttonBar, 7);
		
	}
	
	public void completeObjekt() {
		if (hauptModul.isNew() || fachdaten == null) {
			// Neues Anhang 55 Objekt erzeugen
			fachdaten = new Anh55Fachdaten();
			// Objekt_Id setzen
			fachdaten.setBasisObjekt(hauptModul.getObjekt());
			
			// Anhang 55 Objekt speichern
			Anh55Fachdaten.saveFachdaten(fachdaten);
			AUIKataster.debugOutput("Neues Anh 55 Objekt "+fachdaten+" gespeichert.", "ObjektBearbeiten.completeObjekt");
		}
	}

	private boolean saveAnh55Daten() {
		boolean success;
		
		String bemerkungen = BemerkungenArea.getText();
		if ("".equals(bemerkungen)) {
			fachdaten.setBemerkungen(null);
		} else {
			fachdaten.setBemerkungen(bemerkungen);
		}
		
		String mengewaescheString = mengewaescheFeld.getText();
		if ("".equals(mengewaescheString)) {
			fachdaten.setMengewaesche(null);
		} else {
			fachdaten.setMengewaesche(mengewaescheString);
		}
		
		String sachbearbeiterString = sachbearbeiterFeld.getText();
		if ("".equals(sachbearbeiterString)) {
			fachdaten.setSachbearbeiter(null);
		} else {
			fachdaten.setSachbearbeiter(sachbearbeiterString);
		}
		
		String entgebIdString = entgebIdFeld.getText();
		if ("".equals(entgebIdString)) {
			fachdaten.setEntgebId(null);
		} else {
			fachdaten.setEntgebId(entgebIdString);
		}
		
		String monatwasserverbString = monatwasserverbFeld.getText();
		if ("".equals(monatwasserverbString)) {
			fachdaten.setMonatwasserverb(null);
		} else {
			fachdaten.setMonatwasserverb(monatwasserverbString);
		}
		
		String sonsttextString = sonsttexFeld.getText();
		if ("".equals(sonsttextString)) {
			fachdaten.setSonsttex(null);
		} else {
			fachdaten.setSonsttex(sonsttextString);
		}
		
		String waschsituationString = waschsituationArea.getText();
		if ("".equals(waschsituationString)) {
			fachdaten.setWaschsituation(null);
		} else {
			fachdaten.setWaschsituation(waschsituationString);
		}
		
		String ansprechpartnerString = ansprechpartnerFeld.getText();
		if ("".equals(ansprechpartnerString)) {
			fachdaten.setAnsprechpartner(null);
		} else {
			fachdaten.setAnsprechpartner(ansprechpartnerString);
		}
		
		String brancheString = brancheFeld.getText();
		if ("".equals(brancheString)) {
			fachdaten.setBranche(null);
		} else {
			fachdaten.setBranche(brancheString);
		}
		
		Integer anteilwaschgut = ((IntegerField)anteilwaschgutFeld).getIntValue();
		fachdaten.setAnteilwaschgut(anteilwaschgut);

		Integer anteilgesamtwg = ((IntegerField)anteilgesamtwaschgutFeld).getIntValue();
		fachdaten.setAnteilgesamtgut(anteilgesamtwg);
		
		if (getAbgemeldetCheck().isSelected())  {
			fachdaten.setAbgemeldet(true);
		} else {
			fachdaten.setAbgemeldet(false);
		}
		
		if (getPutztuecherCheck().isSelected())  {
			fachdaten.setPutztuecher(true);
		} else {
			fachdaten.setPutztuecher(false);
		}
		
		if (getTeppichCheck().isSelected())  {
			fachdaten.setTeppich(true);
		} else {
			fachdaten.setTeppich(false);;
		}
		
		if (getMattenCheck().isSelected())  {
			fachdaten.setMatten(true);
		} else {
			fachdaten.setMatten(false);
		}
		
		if (getHaushaltstexCheck().isSelected())  {
			fachdaten.setHaushaltstex(true);
		} else {
			fachdaten.setHaushaltstex(false);
		}
		
		if (getBerufsklCheck().isSelected())  {
			fachdaten.setBerufskl(true);
		} else {
			fachdaten.setBerufskl(false);
		}
		
		if (getGasthotelCheck().isSelected())  {
			fachdaten.setGasthotel(true);
		} else {
			fachdaten.setGasthotel(false);
		}
		
		if (getKrankenhausCheck().isSelected())  {
			fachdaten.setKrankenhaus(true);
		} else {
			fachdaten.setKrankenhaus(false);
		}
		
		if (getHeimwaescheCheck().isSelected())  {
			fachdaten.setHeimwaesche(true);
		} else {
			fachdaten.setHeimwaesche(false);
		}
		
		if (getBetrwasseraufberCheck().isSelected())  {
			fachdaten.setBetrwasseraufber(true);
		} else {
			fachdaten.setBetrwasseraufber(false);
		}
		
		if (getChlorCheck().isSelected())  {
			fachdaten.setChlor(true);
		} else {
			fachdaten.setChlor(false);
		}
		
		if (getAktivchlorCheck().isSelected())  {
			fachdaten.setAktivchlor(true);
		} else {
			fachdaten.setAktivchlor(false);
		}
		
		if (getVliesCheck().isSelected())  {
			fachdaten.setVlies(true);
		} else {
			fachdaten.setVlies(true);
		}
		
		if (getFischCheck().isSelected())  {
			fachdaten.setFischfleisch(true);
		} else {
			fachdaten.setFischfleisch(false);
		}
		
		if (getLoesungsmittelCheck().isSelected())  {
			fachdaten.setLoesungsmittel(true);
		} else {
			fachdaten.setLoesungsmittel(false);
		}
		
		success = Anh55Fachdaten.saveFachdaten(fachdaten);
		if (success) {
			AUIKataster.debugOutput("Anh 55 Objekt " + fachdaten.getId() + " gespeichert.",
			"ObjektAnh55Panel.saveFachdaten");
		} else {
			AUIKataster.debugOutput("Anh 55 Objekt " + fachdaten
					+ " konnte nicht gespeichert werden!",
			"ObjektAnh55Panel.saveFachdaten");
		}
		return success;
	}

	public void enableAll(boolean enabled) {
		
		getBemerkungenArea().setEnabled(enabled);
		getAbgemeldetCheck().setEnabled(enabled);
		getSachbearbeiterFeld().setEnabled(enabled);
		getEntgebIdFeld().setEnabled(enabled);
		getMengeFeld().setEnabled(enabled);
		getSonsttexFeld().setEnabled(enabled);
		getMonatwasserverbFeld().setEnabled(enabled);
		getWaschsituationArea().setEnabled(enabled);
		getAnsprechpartnerFeld().setEnabled(enabled);
		getBrancheFeld().setEnabled(enabled);
		getPutztuecherCheck().setEnabled(enabled);
		getTeppichCheck().setEnabled(enabled);
		getMattenCheck().setEnabled(enabled);
		getHaushaltstexCheck().setEnabled(enabled);
		getBerufsklCheck().setEnabled(enabled);
		getGasthotelCheck().setEnabled(enabled);
		getKrankenhausCheck().setEnabled(enabled);
		getHeimwaescheCheck().setEnabled(enabled);
		getAnteilwaschgutFeld().setEnabled(enabled);
		getGesamtwaschgutFeld().setEnabled(enabled);
		getBetrwasseraufberCheck().setEnabled(enabled);
		getChlorCheck().setEnabled(enabled);
		getAktivchlorCheck().setEnabled(enabled);
		getVliesCheck().setEnabled(enabled);
		getFischCheck().setEnabled(enabled);
		getLoesungsmittelCheck().setEnabled(enabled);	
	}

	public void clearForm() {
		getAbgemeldetCheck().setSelected(false);
		getSachbearbeiterFeld().setText(null);
		getEntgebIdFeld().setText(null);
		getMengeFeld().setText(null);
		getSonsttexFeld().setText(null);
		getMonatwasserverbFeld().setText(null);
		getWaschsituationArea().setText(null);
		getAnsprechpartnerFeld().setText(null);
		getBrancheFeld().setText(null);
		getPutztuecherCheck().setSelected(false);
		getTeppichCheck().setSelected(false);
		getMattenCheck().setSelected(false);
		getHaushaltstexCheck().setSelected(false);
		getBerufsklCheck().setSelected(false);
		getGasthotelCheck().setSelected(false);
		getKrankenhausCheck().setSelected(false);
		getHeimwaescheCheck().setSelected(false);
		getAnteilwaschgutFeld().setText(null);
		getGesamtwaschgutFeld().setText(null);
		getBetrwasseraufberCheck().setSelected(false);
		getChlorCheck().setSelected(false);
		getAktivchlorCheck().setSelected(false);
		getVliesCheck().setSelected(false);
		getFischCheck().setSelected(false);
		getLoesungsmittelCheck().setSelected(false);
		getBemerkungenArea().setText(null);
		getAbgemeldetCheck().setSelected(false);	
	}

	public void updateForm() throws RuntimeException {
	
	if (fachdaten != null) {
		if (fachdaten.getBemerkungen() != null) {
			getBemerkungenArea().setText(fachdaten.getBemerkungen());
		}
		
		if (fachdaten.getSachbearbeiter() != null) {
			getSachbearbeiterFeld().setText(fachdaten.getSachbearbeiter());
		}
		
		if (fachdaten.getEntgebId() != null) {
			getEntgebIdFeld().setText(fachdaten.getEntgebId());
		}
		
		if (fachdaten.getMengewaesche() != null) {
			getMengeFeld().setText(fachdaten.getMengewaesche());
		}
		
		if (fachdaten.getSonsttex() != null) {
			getSonsttexFeld().setText(fachdaten.getSonsttex());
		}
		
		if (fachdaten.getMonatwasserverb() != null) {
			getMonatwasserverbFeld().setText(fachdaten.getMonatwasserverb());
		}
		
		if (fachdaten.getWaschsituation() != null) {
			getWaschsituationArea().setText(fachdaten.getWaschsituation());
		}
		
		if (fachdaten.getAnsprechpartner() != null) {
			getAnsprechpartnerFeld().setText(fachdaten.getAnsprechpartner());
		}
		
		if (fachdaten.getBranche() != null) {
			getBrancheFeld().setText(fachdaten.getBranche());
		}
		
		if (fachdaten.getAnteilwaschgut() != null) {
			getAnteilwaschgutFeld().setText(fachdaten.getAnteilwaschgut().toString());
		}
		
		if (fachdaten.getAnteilgesamtgut() != null) {
			getGesamtwaschgutFeld().setText(fachdaten.getAnteilgesamtgut().toString());
		}

		if (fachdaten.getAbgemeldet() != null) {
			if (fachdaten.getAbgemeldet() == true) {
				getAbgemeldetCheck().setSelected(true);
			}
			else {
				getAbgemeldetCheck().setSelected(false);
			}
		}

		if (fachdaten.getPutztuecher() != null) {
			if (fachdaten.getPutztuecher() == true) {
				getPutztuecherCheck().setSelected(true);
			}
			else {
				getPutztuecherCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getTeppich() != null) {
			if (fachdaten.getTeppich() == true) {
				getTeppichCheck().setSelected(true);
			}
			else {
				getTeppichCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getMatten() != null) {
			if (fachdaten.getMatten() == true) {
				getMattenCheck().setSelected(true);
			}
			else {
				getMattenCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getHaushaltstex() != null) {
			if (fachdaten.getHaushaltstex() == true) {
				getHaushaltstexCheck().setSelected(true);
			}
			else {
				getHaushaltstexCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getBerufskl() != null) {
			if (fachdaten.getBerufskl() == true) {
				getBerufsklCheck().setSelected(true);
			}
			else {
				getBerufsklCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getGasthotel() != null) {
			if (fachdaten.getGasthotel() == true) {
				getGasthotelCheck().setSelected(true);
			}
			else {
				getGasthotelCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getKrankenhaus() != null) {
			if (fachdaten.getKrankenhaus() == true) {
				getKrankenhausCheck().setSelected(true);
			}
			else {
				getKrankenhausCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getHeimwaesche() != null) {
			if (fachdaten.getHeimwaesche() == true) {
				getHeimwaescheCheck().setSelected(true);
			}
			else {
				getHeimwaescheCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getBetrwasseraufber() != null) {
			if (fachdaten.getBetrwasseraufber() == true) {
				getBetrwasseraufberCheck().setSelected(true);
			}
			else {
				getBetrwasseraufberCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getChlor() != null) {
			if (fachdaten.getChlor() == true) {
				getChlorCheck().setSelected(true);
			}
			else {
				getChlorCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getAktivchlor() != null) {
			if (fachdaten.getAktivchlor() == true) {
				getAktivchlorCheck().setSelected(true);
			}
			else {
				getAktivchlorCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getVlies() != null) {
			if (fachdaten.getVlies() == true) {
				getVliesCheck().setSelected(true);
			}
			else {
				getVliesCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getFischfleisch() != null) {
			if (fachdaten.getFischfleisch() == true) {
				getFischCheck().setSelected(true);
			}
			else {
				getFischCheck().setSelected(false);
			}
		}
		
		if (fachdaten.getLoesungsmittel() != null) {
			if (fachdaten.getLoesungsmittel() == true) {
				getLoesungsmittelCheck().setSelected(true);
			}
			else {
				getLoesungsmittelCheck().setSelected(false);
			}
		}
		
//		if (fachdaten.getGenpflicht() != null) {
//			if (fachdaten.getGenpflicht().intValue() == -1) {
//				getGenpflichtCheck().setSelected(true);
//			}
//			else {
//				getGenpflichtCheck().setSelected(false);
//			}
//		}
//		if (fachdaten.getAbwasseranfall() != null) {
//			if (fachdaten.getAbwasseranfall().intValue() == -1) {
//				getAbwasseranfallCheck().setSelected(true);
//			}
//			else {
//				getAbwasseranfallCheck().setSelected(false);
//			}
//		}
		
	}
	
	}



	public void fetchFormData() throws RuntimeException {
		fachdaten = Anh55Fachdaten.getAnh55ByObjekt(hauptModul.getObjekt());
		AUIKataster.debugOutput("Anhang 55 Objekt aus DB geholt: ID" + fachdaten, "ObjektAnh55Panel.fetchFormData");
	}

	private JButton getSaveAnh55Button() {
		if (saveAnh55Button == null) {
			saveAnh55Button = new JButton("Speichern");
			
			saveAnh55Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enableAll(false);
					if (saveAnh55Daten()) {
						hauptModul.getFrame().changeStatus("Anh 55 Objekt "+fachdaten.getId()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
					} else {
						hauptModul.getFrame().changeStatus("Fehler beim Speichern des Anh 55 Objekt!", HauptFrame.ERROR_COLOR);
					}
					
					hauptModul.fillForm();
				}
			});
		}
		return saveAnh55Button;
	}

	public String getName() {
		return name;
	}

	private JCheckBox getAbgemeldetCheck() {
		if (abgemeldetCheck == null) {
			abgemeldetCheck = new JCheckBox("Abgemeldet?");
		}
		return abgemeldetCheck;
	}
	
	private JTextField getEntgebIdFeld() {
		if (entgebIdFeld == null) {
			entgebIdFeld = new LimitedTextField(50);
		}
		return entgebIdFeld;
	}
	
	private JTextField getBrancheFeld() {
		if (brancheFeld == null) {
			brancheFeld = new LimitedTextField(50);
		}
		return brancheFeld;
	}
	
	private JCheckBox getPutztuecherCheck() {
		if (putztuecherCheck == null) {
			putztuecherCheck = new JCheckBox("Putztücher?");
		}
		return putztuecherCheck;
	}
	
	private JTextField getAnsprechpartnerFeld() {
		if (ansprechpartnerFeld == null) {
			ansprechpartnerFeld = new LimitedTextField(50);
		}
		return ansprechpartnerFeld;
	}
	
	private JCheckBox getMattenCheck() {
		if (mattenCheck == null) {
			mattenCheck = new JCheckBox("Matten?");
		}
		return mattenCheck;
	}
	
	private JTextField getMengeFeld() {
		if (mengewaescheFeld == null) {
			mengewaescheFeld = new LimitedTextField(50);
		}
		return mengewaescheFeld;
	}
	
	private JCheckBox getTeppichCheck() {
		if (teppichCheck == null) {
			teppichCheck = new JCheckBox("Teppiche?");
		}
		return teppichCheck;
	}
	
	private JTextField getSonsttexFeld() {
		if (sonsttexFeld == null) {
			sonsttexFeld = new LimitedTextField(50);
		}
		return sonsttexFeld;
	}
	
	private JCheckBox getBerufsklCheck() {
		if (berufsklCheck == null) {
			berufsklCheck = new JCheckBox("Berufskleidung?");
		}
		return berufsklCheck;
	}
	 
	private DoubleField getMonatwasserverbFeld() {
		if (monatwasserverbFeld == null) {
			monatwasserverbFeld = new DoubleField(50);
		}
		return monatwasserverbFeld;
	}
	
	private JCheckBox getHaushaltstexCheck() {
		if (haushaltstexCheck == null) {
			haushaltstexCheck = new JCheckBox("Haushaltstextilien?");
		}
		return haushaltstexCheck;
	}
	
	private JCheckBox getGasthotelCheck() {
		if (gasthotelCheck == null) {
			gasthotelCheck = new JCheckBox("Gaststätten-/Hoteltextilien?");
		}
		return gasthotelCheck;
	}
	
	private JCheckBox getKrankenhausCheck() {
		if (krankenhausCheck == null) {
			krankenhausCheck = new JCheckBox("Krankenhaus?");
		}
		return krankenhausCheck;
	}
	
	private JCheckBox getHeimwaescheCheck() {
		if (heimwaescheCheck == null) {
			heimwaescheCheck = new JCheckBox("Heimwäsche?");
		}
		return heimwaescheCheck;
	}
	
	private JCheckBox getVliesCheck() {
		if (vliesCheck == null) {
			vliesCheck = new JCheckBox("Vlies?");
		}
		return vliesCheck;
	}
	
	private JCheckBox getFischCheck() {
		if (fischCheck == null) {
			fischCheck = new JCheckBox("Fisch/Fleisch?");
		}
		return fischCheck;
	}
	
	private JCheckBox getLoesungsmittelCheck() {
		if (loesungsmittelCheck == null) {
			loesungsmittelCheck = new JCheckBox("Lösungsmittel?");
		}
		return loesungsmittelCheck;
	}
	
	private JFormattedTextField getAnteilwaschgutFeld() {
		if (anteilwaschgutFeld == null) {
			anteilwaschgutFeld = new IntegerField();
		}
		return anteilwaschgutFeld;
	}
	
	private JFormattedTextField getGesamtwaschgutFeld() {
		if (anteilgesamtwaschgutFeld == null) {
			anteilgesamtwaschgutFeld = new IntegerField();
		}
		return anteilgesamtwaschgutFeld;
	}
	private JTextField getSachbearbeiterFeld() {
		if (sachbearbeiterFeld == null) {
			sachbearbeiterFeld = new LimitedTextField(50);
		}
		return sachbearbeiterFeld;
	}
	
	private JCheckBox getBetrwasseraufberCheck() {
		if (betrwasseraufberCheck == null) {
			betrwasseraufberCheck = new JCheckBox("Betriebswasseraufbereitung?");
		}
		return betrwasseraufberCheck;
	}
	
	private JCheckBox getChlorCheck() {
		if (chlorCheck == null) {
			chlorCheck = new JCheckBox("Chlor?");
		}
		return chlorCheck;
	}
	
	private JCheckBox getAktivchlorCheck() {
		if (aktivchlorCheck == null) {
			aktivchlorCheck = new JCheckBox("Aktiv-Chlor?");
		}
		return aktivchlorCheck;
	}
//
//	private JCheckBox getAbwasseranfallCheck() {
//		if (abwasseranfallCheck == null) {
//			abwasseranfallCheck = new JCheckBox("Abwasseranfall");
//		}
//		return abwasseranfallCheck;
//	}
//	private JTextField getDruckverfahrenFeld() {
//		if (druckverfahrenFeld == null) {			
//			druckverfahrenFeld = new LimitedTextField(150);
//		}
//		return druckverfahrenFeld;
//	}
//	private JTextField getEntsorgungFeld() {
//		if (entsorgungFeld == null) {			
//			entsorgungFeld = new LimitedTextField(150);
//		}
//		return entsorgungFeld;
//	}
//	private TextFieldDateChooser getGen58Datum() {
//		if (gen58Datum == null) {			
//			gen58Datum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
//		}
//		return gen58Datum;
//	}
//	private TextFieldDateChooser getGen59Datum() {
//		if (gen59Datum == null) {			
//			gen59Datum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
//		}
//		return gen59Datum;
//	}
//	private JCheckBox getGenpflichtCheck() {
//		if (genpflichtCheck == null) {
//			genpflichtCheck = new JCheckBox("Genehmigungspflicht");
//		}
//		return genpflichtCheck;
//	}
//	private JTextField getVerbrauchFeld() {
//		if (verbrauchFeld == null) {			
//			verbrauchFeld = new LimitedTextField(150);
//		}
//		return verbrauchFeld;
//	}
	private JTextArea getBemerkungenArea() {
		if (BemerkungenArea == null) {			
			BemerkungenArea = new LimitedTextArea(255);
			BemerkungenArea.setLineWrap(true);
			BemerkungenArea.setWrapStyleWord(true);
		}
		return BemerkungenArea;
	}
	
	private JTextArea getWaschsituationArea() {
		if (waschsituationArea == null) {			
			waschsituationArea = new LimitedTextArea(255);
			waschsituationArea.setLineWrap(true);
			waschsituationArea.setWrapStyleWord(true);
		}
		return waschsituationArea;
	}
}
