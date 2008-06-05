/*
 * Created on 27.04.2005 by u633d
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Entsorger;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.module.ObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.EntsorgerEditor;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Zahnarzt"-Tab des ObjektBearbeiten-Moduls 
 * @author Gerd Genuit
 */
public class ObjektAnh50Panel extends JPanel {
	private String name;
	private ObjektBearbeiten hauptModul;
	
	// Widgets
	private JTextField gefaehrdungsklasseFeld = null;
	private JCheckBox erloschenCheck = null;
	private JTextArea anh50BemerkungArea = null;
	private TextFieldDateChooser antragDatum = null;
	private TextFieldDateChooser genehmigungDatum = null;
	private TextFieldDateChooser wiedervorlageDatum = null;
	private JToolBar entsorBearbToolBar = null;
	private JButton entsorgBearbButton = null;
	private JButton saveAnh50Button = null;
	private JComboBox entsorgerBox = null;


	
	// Daten
	private Anh50Fachdaten fachdaten = null;
	private Anh50Entsorger[] entsorg = null;
	
	//Listener
	private ActionListener editButtonListener;
	
	
	public ObjektAnh50Panel(ObjektBearbeiten hauptModul) {
		name = "Zahnarzt";
		this.hauptModul = hauptModul;
		
		FormLayout layout = new FormLayout (
				"r:70dlu, 5dlu, 90dlu, r:90dlu, 5dlu, 20dlu", // Spalten
				"");
		
		
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.setDefaultDialogBorder();
		
		builder.appendSeparator("Fachdaten");
		builder.append("Antrag:", getAntragDatum());
		builder.nextLine();
		builder.append("Genehmigung:", getGenehmigungDatum());
		builder.nextLine();
		builder.append("Wiedervorlagen:", getWiedervorlageDatum());
		builder.nextLine();
		builder.append("Gefährdungsklasse:", getGefaehrdungsklasseFeld());
		builder.nextLine();
		builder.append("", getErloschenCheck());
		builder.nextLine();

		builder.appendSeparator("Entsorger");
		builder.append("Entsorger:", getEntsorgerBox(), 2);
		builder.append(getEntsorBearbToolBar());
		
		builder.appendSeparator("Bemerkungen");
		builder.appendRow("3dlu");
		builder.nextLine(2);
		JScrollPane bemerkungsScroller = new JScrollPane(getAnh50BemerkungArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:30dlu");
		builder.append(bemerkungsScroller, 6);
		
		JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveAnh50Button());
		builder.append(buttonBar, 6);
	}
	
	public void fetchFormData() throws RuntimeException {
		fachdaten = Anh50Fachdaten.getAnh50ByObjekt(hauptModul.getObjekt());
		AUIKataster.debugOutput("Zahnarzt aus DB geholt: " + fachdaten, "ObjektAnh50Panel.fetchFormData");
		
		if (entsorg == null || entsorg.length == 0) {
			entsorg = Anh50Entsorger.getEntsorg();
		}
	}
	
	
	public void updateForm() throws RuntimeException {
		if (entsorg != null) {
			getEntsorgerBox().setModel(new DefaultComboBoxModel(entsorg));
		}
		
		if (fachdaten != null) {
			if (fachdaten.getBemerkungen() != null) {
				getAnh50BemerkungArea().setText(fachdaten.getBemerkungen());
			}
			if (fachdaten.getDatumantrag() != null) {
				getAntragDatum().setDate(fachdaten.getDatumantrag());
			}
			if (fachdaten.getGenehmigung() != null) {
				getGenehmigungDatum().setDate(fachdaten.getGenehmigung());
			}
			if (fachdaten.getWiedervorlage() != null) {
				getWiedervorlageDatum().setDate(fachdaten.getWiedervorlage());
			}
			if (fachdaten.getGefaehrdungsklasse() != null) {
				getGefaehrdungsklasseFeld().setText(fachdaten.getGefaehrdungsklasse().toString());
			}
			if (fachdaten.getAnh50Entsorger() != null) {
				getEntsorgerBox().setSelectedItem(fachdaten.getAnh50Entsorger());
			}
			if (fachdaten.getErloschen() != null) {
				if (fachdaten.getErloschen() == true) {
					getErloschenCheck().setSelected(true);
				}
				else {
					getErloschenCheck().setSelected(false);
				}
			}
		}

		}
	
	
	public void clearForm() {
		
		getGefaehrdungsklasseFeld().setText(null);
		getAnh50BemerkungArea().setText(null);
		getAntragDatum().setDate(null);
		getGenehmigungDatum().setDate(null);
		getWiedervorlageDatum().setDate(null);
		getErloschenCheck().setSelected(false);

	}
	
	public void enableAll(boolean enabled) {

	}

	
	public String getName() {
		return name;
	}
	
	private boolean saveAnh50Daten() {
		boolean success;
		
		String bemerkungen = anh50BemerkungArea.getText();
		if ("".equals(bemerkungen)) {
			fachdaten.setBemerkungen(null);
		} else {
			fachdaten.setBemerkungen(bemerkungen);
		}
		
		Date antrag = antragDatum.getDate();
		fachdaten.setDatumantrag(antrag);				
		
		if (getErloschenCheck().isSelected())  {
			fachdaten.setErloschen(true);
		} else {
			fachdaten.setErloschen(false);
		}
		
		String gefaehrdungsklasse = gefaehrdungsklasseFeld.getText();
		if ("".equals(gefaehrdungsklasse)) {
			fachdaten.setGefaehrdungsklasse(null);
		} else {
			fachdaten.setGefaehrdungsklasse(gefaehrdungsklasse);
		}
		
		Date genehmigung = genehmigungDatum.getDate();
		fachdaten.setGenehmigung(genehmigung);	
		
		Date wiedervorlage = wiedervorlageDatum.getDate();
		fachdaten.setWiedervorlage(wiedervorlage);
		
		if (getEntsorgerBox().getSelectedItem() != null) {
			fachdaten.setAnh50Entsorger((Anh50Entsorger) getEntsorgerBox()
					.getSelectedItem());
			AUIKataster.debugOutput("Entsorger "
					+ fachdaten.getAnh50Entsorger() + " zugeordnet.",
					"ObjektAnh50Panel.saveAnh50Daten");
		} else
			getEntsorgerBox().setSelectedIndex(1);
			fachdaten.setAnh50Entsorger((Anh50Entsorger) getEntsorgerBox()
					.getSelectedItem());
			
		success = Anh50Fachdaten.saveFachdaten(fachdaten);
		if (success) {
			AUIKataster.debugOutput("Zahnarzt " + fachdaten.getName() + " gespeichert.",
			"ObjektAnh50Panel.saveAnh50Daten");
		} else {
			AUIKataster.debugOutput("Zahnarzt " + fachdaten
					+ " konnte nicht gespeichert werden!",
			"ObjektAnh50Panel.saveAnh50Daten");
		}
		return success;
	}
	
	public void completeObjekt() {
		if (hauptModul.isNew() || fachdaten == null) {
			// Neuen Zahnarzt erzeugen
			fachdaten = new Anh50Fachdaten();
			// Objekt_Id setzen
			fachdaten.setBasisObjekt(hauptModul.getObjekt());
			// Entsorger auf "unbekannt" setzen
			Anh50Entsorger entsorg = new Anh50Entsorger();
			entsorg.setEntsorgerid(1);
			fachdaten.setAnh50Entsorger(entsorg);
			
			// Zahnarzt speichern
			Anh50Fachdaten.saveFachdaten(fachdaten);
			AUIKataster.debugOutput("Neuer Zahnarzt "+fachdaten+" gespeichert.", "ObjektBearbeiten.completeObjekt");
		}
	}
	


	private JTextArea getAnh50BemerkungArea() {
		if (anh50BemerkungArea == null) {
			anh50BemerkungArea = new LimitedTextArea(255);
			anh50BemerkungArea.setLineWrap(true);
			anh50BemerkungArea.setWrapStyleWord(true);
		}
		return anh50BemerkungArea;
	}
	private TextFieldDateChooser getAntragDatum() {
		if (antragDatum == null) {			
			antragDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return antragDatum;
	}
	private JCheckBox getErloschenCheck() {
		if (erloschenCheck == null) {
			erloschenCheck = new JCheckBox("Erloschen");
		}
		return erloschenCheck;
	}
	private JTextField getGefaehrdungsklasseFeld() {
		if (gefaehrdungsklasseFeld == null) {
			gefaehrdungsklasseFeld = new LimitedTextField(50);
		}
		return gefaehrdungsklasseFeld;
	}
	private TextFieldDateChooser getGenehmigungDatum() {
		if (genehmigungDatum == null) {			
			genehmigungDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return genehmigungDatum;
	}
	private TextFieldDateChooser getWiedervorlageDatum() {
		if (wiedervorlageDatum == null) {			
			wiedervorlageDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		}
		return wiedervorlageDatum;
	}
	private JComboBox getEntsorgerBox() {
		if (entsorgerBox == null) {
			entsorgerBox = new JComboBox();
		}
		return entsorgerBox;
	}
private JButton getSaveAnh50Button() {
		if (saveAnh50Button == null) {
			saveAnh50Button = new JButton("Speichern");
			
			saveAnh50Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enableAll(false);
					if (saveAnh50Daten()) {
						hauptModul.getFrame().changeStatus("Zahnarzt "+fachdaten.getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
					} else {
						hauptModul.getFrame().changeStatus("Fehler beim Speichern des Zahnarztes!", HauptFrame.ERROR_COLOR);
					}
					
					hauptModul.fillForm();
				}
			});
		}
		return saveAnh50Button;
	}
	private JButton getEntsorgBearbButton() {
		if (entsorgBearbButton == null) {
			entsorgBearbButton = new JButton(AuikUtils.getIcon(16, "edit.png", ""));
			entsorgBearbButton.setHorizontalAlignment(JButton.CENTER);
			entsorgBearbButton.setToolTipText("Entsorger bearbeiten");
			entsorgBearbButton.setActionCommand("entsorger_edit");
			
			entsorgBearbButton.addActionListener(getEditButtonListener());
		}
		return entsorgBearbButton;
	}
	
	private JToolBar getEntsorBearbToolBar() {
		if (entsorBearbToolBar == null) {
			entsorBearbToolBar = new JToolBar();
			entsorBearbToolBar.setFloatable(false);
			entsorBearbToolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);;
			
			entsorBearbToolBar.add(getEntsorgBearbButton());
			}
		return entsorBearbToolBar;
	}

	private ActionListener getEditButtonListener() {
		if (editButtonListener == null) {
			editButtonListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					Anh50Entsorger entsorg = (Anh50Entsorger) getEntsorgerBox().getSelectedItem();
					
					if ("entsorger_edit".equals(action) && entsorg != null) {
						EntsorgerEditor editDialog = new EntsorgerEditor(entsorg, hauptModul.getFrame());
						editDialog.setLocationRelativeTo(hauptModul.getFrame());
						
						editDialog.setVisible(true);
						editDialog.setEnabled(true);
						
						entsorg = editDialog.getEntsorger();
					}
				}
			};
		}
		
		return editButtonListener;
	}
}

