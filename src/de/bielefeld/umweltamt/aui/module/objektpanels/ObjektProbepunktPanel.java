/*
 * Created on 19.04.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.module.ObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ProbenahmenModel;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;

/**
 * Das "Probepunkt"-Tab des ObjektBearbeiten-Moduls 
 * @author David Klotz
 */
public class ObjektProbepunktPanel extends JPanel {
	private String name;
	private ObjektBearbeiten hauptModul;
	
	// Widgets
	private JComboBox probePktArtBox = null;
	private JComboBox probeKABox = null;
	private JFormattedTextField probePktNrFeld = null;
	private JTextArea probePktBeschreibungsArea = null;
	private JTable probenahmeTabelle = null;
	private JPanel neueProbePanel = null;
	private JTextField kennummerFeld = null;
	private JDateChooser datumsChooser = null;
	private JButton anlegenButton = null;
	private JButton savePktButton = null;
	
	private ProbenahmenModel probenahmenModel = null;
	
	// Daten
	private AtlProbepkt probepkt = null;
	private AtlProbeart[] probearten = null;
	private AtlKlaeranlagen[] klaeranlagen = null;
	
	public ObjektProbepunktPanel(ObjektBearbeiten hauptModul) {
		name = "Probenahmepunkt";
		this.hauptModul = hauptModul;
		this.probenahmenModel = new ProbenahmenModel();
		
		FormLayout layout = new FormLayout (
				"r:50dlu, 5dlu, 150dlu, r:45dlu, 5dlu, 25dlu", // Spalten
				"pref, " +  //1
				"3dlu, " +  //2
				"pref, " +	//1
				"3dlu, " +	//2
				"pref, " +	//3
				"3dlu, " +  //4
				"pref, " +  //5
				"5dlu, " +	//6
				"pref, " +	//7
				"3dlu, " +	//8
				"fill:23dlu:g(0.2), "+	//9
				"3dlu, " +	//10
				"pref, " +	//11
				"3dlu, " +	//12
				"fill:40dlu:g, "+ //13
				"3dlu, " +	//14
				"pref"); // 15
		
		PanelBuilder builder = new PanelBuilder(layout, this);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();
		
		builder.addSeparator("Eigenschaften", cc.xyw( 1, 1, 6));
		builder.addLabel("Art:", cc.xy( 1, 3));
		builder.add(getProbePktArtBox(), cc.xy( 3, 3));
		builder.addLabel("Nr:", cc.xy( 4, 3));
		builder.add(getProbePktNrFeld(), cc.xy( 6, 3));
		builder.addLabel("Kläranlage:", cc.xy( 1, 5));
		builder.add(getProbeKABox(), cc.xy( 3, 5));
		
		JPanel buttonBar = ButtonBarFactory.buildOKBar(getSavePktButton());
		builder.add(buttonBar, cc.xyw( 1, 7, 6));
		
		builder.addSeparator("Beschreibung", cc.xyw( 1, 9, 6));
		JScrollPane beschScroller = new JScrollPane(getProbePktBeschreibungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		beschScroller.setBorder(null);
		builder.add(beschScroller, cc.xyw( 1, 11, 6));
		
		builder.addSeparator("Probenahmen", cc.xyw( 1, 13, 6));
		JScrollPane tabellenScroller = new JScrollPane(getProbenahmeTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.add(tabellenScroller, cc.xyw( 1, 15, 6));
		//builder.addSeparator("Neue Probenahme", cc.xyw( 1, 13, 7));
		builder.add(getNeueProbePanel(), cc.xyw( 1, 17, 6));
	}
	
	public void fetchFormData() throws RuntimeException {
		probepkt = AtlProbepkt.getProbepunktByObjekt(hauptModul.getObjekt());
		AUIKataster.debugOutput("Probepunkt aus DB geholt: " + probepkt, "ObjektProbepunktPanel.fetchFormData");
		
		if (probearten == null) {
			probearten = AtlProbeart.getProbearten();
		}
		if (klaeranlagen == null) {
			klaeranlagen = AtlKlaeranlagen.getKlaeranlagen();
		}
	}
	
	public void updateForm() throws RuntimeException {
		if (probearten != null) {
			getProbePktArtBox().setModel(new DefaultComboBoxModel(probearten));
		}
		if (klaeranlagen != null) {
			getProbeKABox().setModel(new DefaultComboBoxModel(klaeranlagen));
		}
		
		if (probepkt != null) {
			getProbePktArtBox().setSelectedItem(probepkt.getAtlProbeart());
			getProbeKABox().setSelectedItem(probepkt.getAtlKlaeranlagen());
			
			if (probepkt.getNummer() != null) {
				getProbePktNrFeld().setValue(probepkt.getNummer());
			}
			
			getProbePktBeschreibungsArea().setText(hauptModul.getObjekt().getBeschreibung());
			
			probenahmenModel.setProbepunkt(probepkt);
			probenahmenModel.updateList();
		}
	}
	
	public void clearForm() {
		getProbePktNrFeld().setText(null);
		getProbePktBeschreibungsArea().setText(null);
		getKennummerFeld().setText("");
	}
	
	public void enableAll(boolean enabled) {
		getProbePktArtBox().setEnabled(enabled);
		getProbeKABox().setEnabled(enabled);
		getProbePktNrFeld().setEnabled(enabled);
		getProbePktBeschreibungsArea().setEnabled(enabled);
		
		getProbenahmeTabelle().setEnabled(enabled);
		getKennummerFeld().setEnabled(enabled);
		getDatumsChooser().setEnabled(enabled);
		getAnlegenButton().setEnabled(enabled);
		
		getSavePktButton().setEnabled(enabled);
	}
	
	
	public String getName() {
		return name;
	}
	
	/**
	 * Bearbeitet eine Probenahme.
	 */
	public void editProbenahme(AtlProbenahmen probe) {
		ProbenEditor editDialog = new ProbenEditor(probe, hauptModul.getFrame(), false);
		editDialog.setLocationRelativeTo(hauptModul.getFrame());
		
		editDialog.setVisible(true);
		
		probenahmenModel.updateList();
	}
	
	/**
	 * Legt eine neue Probenahme an.
	 */
	public void neueProbenahme(String kennNummer, Date datum) {
		if (probepkt != null) {
			boolean exists = AtlProbenahmen.probenahmeExists(kennNummer);
			if (!exists) {
				AtlProbenahmen probe = new AtlProbenahmen();
				probe.setKennummer(kennNummer);
				probe.setDatumDerEntnahme(datum);
				probe.setAtlAnalysepositionen(new HashSet());
				probe.setAtlProbepkt(probepkt);
				
				ProbenEditor editDialog = new ProbenEditor(probe, hauptModul.getFrame(), true);
				editDialog.setVisible(true);
				
				probenahmenModel.updateList();
			} else {
				hauptModul.getFrame().changeStatus("Eine Probenahme mit dieser Kennnummer existiert schon!", HauptFrame.ERROR_COLOR);
			}
		} else {
			hauptModul.getFrame().changeStatus("Fehler beim Anlegen: Kein Probepunkt!", HauptFrame.ERROR_COLOR);
		}
	}
	
	private boolean saveProbepunktDaten() {
		boolean success;
		
		if (probepkt != null) {
			// Eingegebene Daten für den Probepunkt übernehmen
			if (getProbePktArtBox().getSelectedItem() != null) {
				probepkt.setAtlProbeart((AtlProbeart) getProbePktArtBox().getSelectedItem());
			}
			if (getProbeKABox().getSelectedItem() != null) {
				probepkt.setAtlKlaeranlagen((AtlKlaeranlagen) getProbeKABox().getSelectedItem());
			}
			
			if (getProbePktNrFeld().getValue() != null) {
				probepkt.setNummer(((IntegerField) getProbePktNrFeld()).getIntValue());
			}
			
			success = AtlProbepkt.removeProbepunkt(probepkt);
			
		} else {
			success = false;
		}
		
		return success;
	}
	
	public void completeObjekt() {
		if (hauptModul.isNew() || probepkt == null) {
			// Neuen Probepunkt erzeugen
			probepkt = new AtlProbepkt();
			// Objekt_Id setzen
			probepkt.setBasisObjekt(hauptModul.getObjekt());
			
			// Probepunkt speichern
			if (AtlProbepkt.saveProbepunkt(probepkt)) {
				AUIKataster.debugOutput("Neuer Probepunkt "+probepkt+" gespeichert.", "ObjektBearbeiten.completeObjekt");
			}
		}
	}
	
	
	private JComboBox getProbePktArtBox() {
		if (probePktArtBox == null) {
			probePktArtBox = new JComboBox();
		}
		return probePktArtBox;
	}
	private JComboBox getProbeKABox() {
		if (probeKABox == null) {
			probeKABox = new JComboBox();
		}
		return probeKABox;
	}
	private JFormattedTextField getProbePktNrFeld() {
		if (probePktNrFeld == null) {
			probePktNrFeld = new IntegerField();
		}
		return probePktNrFeld;
	}
	private JTextArea getProbePktBeschreibungsArea() {
		if (probePktBeschreibungsArea == null) {
			probePktBeschreibungsArea = new JTextArea();
			probePktBeschreibungsArea.setLineWrap(true);
			probePktBeschreibungsArea.setWrapStyleWord(true);
			probePktBeschreibungsArea.setEditable(false);
			probePktBeschreibungsArea.setToolTipText("Diese Beschreibung kann unter \"Objekt\" geändert werden.");
			probePktBeschreibungsArea.setBackground(this.getBackground());
		}
		return probePktBeschreibungsArea;
	}
	
	private JTable getProbenahmeTabelle() {
		if (probenahmeTabelle == null) {
			probenahmeTabelle = new JTable(probenahmenModel);
			//probenahmeTabelle.setBackground(Color.BLUE);
			probenahmeTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			probenahmeTabelle.setColumnSelectionAllowed(false);
			probenahmeTabelle.setRowSelectionAllowed(true);
			probenahmeTabelle.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = probenahmeTabelle.rowAtPoint(origin);
						
						AtlProbenahmen probe = probenahmenModel.getRow(row);
						editProbenahme(probe);
					}
				}
			});
			
			// Den KeyStroke holen, der "Enter" repräsentiert
	        KeyStroke enterKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
	        // Den "Enter"-KeyStroke in die InputMap der Tabelle einfügen
	        probenahmeTabelle.getInputMap().put(enterKeyStroke, "ENTER");
	        // Eine neue Action fürs editieren erzeugen
	        Action editAction = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					int row = probenahmeTabelle.getSelectedRow();
					// Natürlich nur editieren, wenn wirklich eine Zeile ausgewählt ist
					if (row != -1) {
						AtlProbenahmen probe = probenahmenModel.getRow(row);
						editProbenahme(probe);
					}
				}
			};
	        // Diese Action dem "Enter"-KeyStroke zuweisen
			probenahmeTabelle.getActionMap().put("ENTER", editAction);
			
			KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false);
			probenahmeTabelle.getInputMap().put(deleteKeyStroke, "DEL");
	        Action removeAction = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					int row = probenahmeTabelle.getSelectedRow();
					if (row != -1 && probenahmeTabelle.getEditingRow() == -1) {
						AtlProbenahmen probe = probenahmenModel.getRow(row);
						int answer = JOptionPane.showConfirmDialog(ObjektProbepunktPanel.this, "Soll die Probenahme "+ probe.getKennummer() +" wirklich inkl. aller Analysen gelöscht werden?", "Löschen bestätigen", JOptionPane.YES_NO_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							probenahmenModel.removeRow(row);
							AUIKataster.debugOutput("Probe " + probe.getKennummer() + " wurde gelöscht!", "SchlammPanel.removeAction");
						}
					}
				}
			};
			probenahmeTabelle.getActionMap().put("DEL", removeAction);
		}
		return probenahmeTabelle;
	}
	
	private JPanel getNeueProbePanel() {
		if (neueProbePanel == null) {
			//neueProbePanel = new JPanel();
			
			FormLayout anlegenLayout = new FormLayout(
					"pref, 4dlu, max(60dlu;pref), 7dlu, pref, 4dlu, max(60dlu;pref), 7dlu, max(60dlu;pref)"); // spalten, nur eine zeile 
			
			DefaultFormBuilder builder = new DefaultFormBuilder(anlegenLayout);
			
			//builder.appendSeparator("Neue Probenahme");
			builder.append("Kennummer:",getKennummerFeld());
			builder.append("Datum:",	getDatumsChooser());
			builder.append(getAnlegenButton());
			neueProbePanel = builder.getPanel();
		}
		return neueProbePanel;
	}
	
	private JTextField getKennummerFeld() {
		if (kennummerFeld == null) {
			kennummerFeld = new LimitedTextField(50, "");
		}
		
		return kennummerFeld;
	}
	private JDateChooser getDatumsChooser() {
		if (datumsChooser == null) {
			datumsChooser = new JDateChooser(AuikUtils.DATUMSFORMAT, false);
		}
		
		return datumsChooser;
	}
	private JButton getAnlegenButton() {
		if (anlegenButton == null) {
			anlegenButton = new JButton("Anlegen");
			
			anlegenButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getKennummerFeld().getText().trim().equals("")) {
						getKennummerFeld().requestFocus();
						hauptModul.getFrame().changeStatus("Leere Kennummer!", HauptFrame.ERROR_COLOR);
					} else {
						String kennNummer = getKennummerFeld().getText().trim().replaceAll(" ", "");
						Date datum = getDatumsChooser().getDate();
						neueProbenahme(kennNummer, datum);
					}
				}
			});
		}
		
		return anlegenButton;
	}
	private JButton getSavePktButton() {
		if (savePktButton == null) {
			savePktButton = new JButton("Probepunkt speichern");
			
			savePktButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enableAll(false);
					if (saveProbepunktDaten()) {
						hauptModul.getFrame().changeStatus("Probepunkt "+probepkt.getPktId()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
						//hauptModul.setNew(false);
					} else {
						hauptModul.getFrame().changeStatus("Fehler beim Speichern von Probepunkt!", HauptFrame.ERROR_COLOR);
					}
					
					hauptModul.fillForm();
				}
			});
		}
		
		return savePktButton;
	}
}
