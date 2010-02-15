/*
 * Datei:
 * $Id: ObjektBasisPanel.java,v 1.5 2010-02-15 09:24:09 u633d Exp $
 * 
 * Erstellt am 19.04.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.3  2009/04/28 06:59:43  u633d
 * Anh 50 und Standort Tabelle bearbeitet
 *
 * Revision 1.2  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.10  2005/06/09 13:39:42  u633z
 * - Objektart-Box verbreitert (dadurch entfällt auch eine Layout-Spalte)
 *
 * Revision 1.9  2005/06/08 08:35:47  u633z
 * - überflüssigen ModulManager aus Betreiber- und Standort-Editor entfernt
 *
 * Revision 1.8  2005/06/08 06:47:11  u633z
 * - Tooltips für Betreiber und Standort-Felder verbessert
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import org.hibernate.HibernateException;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.module.ObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.module.common.editors.StandortEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisBetreiberModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStandortModel;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.MyKeySelectionManager;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Das "Objekt"-Tab des ObjektBearbeiten-Moduls 
 * @author David Klotz
 */

public class ObjektBasisPanel  extends JPanel {
	private class ChooseDialog extends JDialog {
		private HauptFrame frame;
		private BasisBetreiber betreiber;
		private BasisStandort standort;
		
		private BasisBetreiberModel betreiberModel;
		private BasisStandortModel standortModel;
		
		private JTextField suchFeld;
		private JButton submitButton;
		private JTable ergebnisTabelle;
		
		private JButton okButton;
		private JButton abbrechenButton;
		
		
		public ChooseDialog(Object initial, HauptFrame frame) {
			super(frame, true);
			this.frame = frame;
			
			List initialList = new ArrayList();
			initialList.add(initial);
			
			if (initial instanceof BasisBetreiber) {
				setTitle("Betreiber auswählen");
				betreiber = (BasisBetreiber) initial;
				betreiberModel = new BasisBetreiberModel(false);
				if (betreiber.getBetreiberid() != null) {
					betreiberModel.setList(initialList);
				}
			} else if (initial instanceof BasisStandort) {
				setTitle("Standort auswählen");
				standort = (BasisStandort) initial;
				standortModel = new BasisStandortModel();
				if (standort.getStandortid() != null) {
					standortModel.setList(initialList);
				}
			} else {
				throw new IllegalArgumentException("intial muss ein BasisBetreiber oder BasisStandort sein!");
			}
			
			setContentPane(initializeContentPane());
			
			pack();
			setResizable(false);
			setLocationRelativeTo(frame);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		
		
		
		
		public BasisBetreiber getChosenBetreiber() {
			if (betreiber.getBetreiberid() != null) {
				return betreiber;
			} else {
				return null;
			}
		}
		
		public BasisStandort getChosenStandort() {
			if (standort.getStandortid() != null) {
				return standort;
			} else {
				return null;
			}
		}
		
		private JPanel initializeContentPane() {
			JScrollPane tabellenScroller = new JScrollPane(getErgebnisTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			TabAction ta = new TabAction();
			ta.addComp(getErgebnisTabelle());
			ta.addComp(getOkButton());
			ta.addComp(getAbbrechenButton());
			
			JPanel buttonBar = ButtonBarFactory.buildOKCancelBar(
					getOkButton(),
					getAbbrechenButton()
			);
			
			JToolBar submitToolBar = new JToolBar();
			submitToolBar.setFloatable(false);
			submitToolBar.setRollover(true);
			submitToolBar.add(getSubmitButton());
			
			FormLayout layout = new FormLayout(
					"180dlu, 3dlu, min(16dlu;p)",		// spalten 
					"pref, 3dlu, 100dlu, 3dlu, pref"); 	// zeilen
			PanelBuilder builder = new PanelBuilder(layout);
			builder.setDefaultDialogBorder();
			CellConstraints cc = new CellConstraints();
			
			builder.add(getSuchFeld(),		cc.xy(1, 1));
			builder.add(submitToolBar,		cc.xy(3, 1));
			builder.add(tabellenScroller,	cc.xyw(1, 3, 3));
			builder.add(buttonBar, 			cc.xyw(1, 5, 3));
			
			return(builder.getPanel());
		}
		
		private void choose(int row) {
			if (row != -1) {
				if (betreiber != null) {
					betreiber = betreiberModel.getRow(row);
				} else if (standort != null) {
					standort = standortModel.getRow(row);
				}
				dispose();
			}
		}
		
		private void doSearch() {
			final String suche = getSuchFeld().getText();
			
			if (betreiber != null) {
				SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
					protected void doNonUILogic() throws RuntimeException {
						betreiberModel.filterList(suche, null);
					}

					protected void doUIUpdateLogic() throws RuntimeException {
						betreiberModel.fireTableDataChanged();
					}
				};
				worker.start();
			} else if (standort != null) {
				SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
					protected void doNonUILogic() throws RuntimeException {
						String[] test = suche.split(" ");
						String last = test[test.length-1];
						int nr;
						String first;
						try {
							nr = Integer.parseInt(last);
							first = suche.replaceAll(last, "");
						} catch (NumberFormatException e) {
							first = suche;
							nr = -1;
						}
						
						standortModel.filterList(first, nr);
					}

					protected void doUIUpdateLogic() throws RuntimeException {
						standortModel.fireTableDataChanged();
					}
				};
				worker.start();
			}
		}
		
		private JTextField getSuchFeld() {
			if (suchFeld == null) {
				suchFeld = new JTextField();
				suchFeld.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						doSearch();
					}
				});
			}
			
			return suchFeld;
		}
		
		private JButton getSubmitButton() {
			if (submitButton == null) {
				submitButton = new JButton(AuikUtils.getIcon(16, "key_enter.png"));
				submitButton.setToolTipText("Suche starten");
				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						doSearch();
					}
				});
			}
			
			return submitButton;
		}
		
		private JTable getErgebnisTabelle() {
			if (ergebnisTabelle == null) {
				if (betreiber != null) {
					ergebnisTabelle = new JTable(betreiberModel);
				} else if (standort != null) {
					ergebnisTabelle = new JTable(standortModel);
					//ergebnisTabelle = new JTable(3, 3);
				}
				
				ergebnisTabelle.addFocusListener(TableFocusListener.getInstance());
				ergebnisTabelle.addMouseListener(new MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						if((e.getClickCount() == 2) && (e.getButton() == 1)) {
							Point origin = e.getPoint();
							int row = ergebnisTabelle.rowAtPoint(origin);
							
							choose(row);
						}
					}
				});
				
				ergebnisTabelle.getColumnModel().getColumn(0).setPreferredWidth(130);
			}
			
			return ergebnisTabelle;
		}
		
		private JButton getOkButton() {
			if (okButton == null) {
				okButton = new JButton("Ok");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = getErgebnisTabelle().getSelectedRow();
						
						choose(row);
					}
				});
			}
			
			return okButton;
		}
		
		private JButton getAbbrechenButton() {
			if (abbrechenButton == null) {
				abbrechenButton = new JButton("Abbrechen");
				abbrechenButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
			
			return abbrechenButton;
		}
	}
	
	// Widgets
	//   Betreiber
	private JTextField betreiberFeld;
	private JToolBar betreiberToolBar;
	private JButton betreiberChooseButton;
	private JButton betreiberEditButton;
	private JButton betreiberNewButton;
	
	//   Standort
	private JTextField standortFeld;
	private JToolBar standortToolBar;
	private JButton standortChooseButton;
	private JButton standortEditButton;
	private JButton standortNewButton;
	
	//   Art, Inaktiv, Beschreibung, Speichern
	private JComboBox artBox;
	private JCheckBox inaktivBox;
	private JTextArea beschreibungsArea;
	private JButton saveButton;
	
	private ActionListener editButtonListener;

	// Daten
	private String name;
	private ObjektBearbeiten hauptModul;
	
	// Fachdaten
	private BasisObjektarten[] objektarten;
	
	public ObjektBasisPanel(ObjektBearbeiten hauptModul) {
		
		name = "Objekt";
		this.hauptModul = hauptModul;

		FormLayout layout = new FormLayout (
				"r:50dlu, 5dlu, 180dlu, 3dlu, l:min(55dlu;p)",
				""		// Zeilen werden dynamisch erzeugt
		);
		
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.setDefaultDialogBorder();
		
		
		
		builder.appendSeparator("Eigenschaften");
		builder.append("Betreiber:", getBetreiberFeld());
		builder.append(getBetreiberToolBar());
		builder.nextLine();
		
		builder.append("Standort:", getStandortFeld());
		builder.append(getStandortToolBar());
		builder.nextLine();
		
		builder.append("Art:", getArtBox());
		builder.nextLine();
		
		builder.append("Inaktiv:", getInaktivBox());
		builder.nextLine();
		
		builder.appendSeparator("Beschreibung");
		builder.appendRow("3dlu");
		builder.nextLine(2);
		
		JScrollPane beschreibungsScroller = new JScrollPane(getBeschreibungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:25dlu");
		builder.append(beschreibungsScroller, 5);
		
		builder.appendRelatedComponentsGapRow();
		builder.nextLine(2);
		
		JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveButton());
		builder.append(buttonBar, 5);
	}
	

	public void fetchFormData() {
		if (objektarten == null) 
		{	
			
		
			objektarten = BasisObjektarten.getObjektarten();
		}	
	}
	
	public void updateForm() {
		boolean neu;
		

		try
		{
			// Nur wenn Objekte neu angelegt werden stehen alle Objektarten zur Auswahl.
			// Sobald eine Objet gespeichert wurde ist die Objektart nicht mehr veränderbar	
			int id  = hauptModul.getObjekt().getObjektid();
			neu = false;
		}
		catch (NullPointerException e) 
		{
			neu = true;
		}

		if (neu == true)
		{	
			if (objektarten != null && (objektarten.length != getArtBox().getItemCount())) 
			{
				getArtBox().setModel(new DefaultComboBoxModel(objektarten));				
			}
		}
		
		else
		{	
			getArtBox().removeAllItems();
			// Ändern der Objektart von Anhang 53 (<3000) in Anhang 53 (>3000) und umgekehrt ist weiterhin möglich
			if (hauptModul.getObjekt().getBasisObjektarten().isAnh53Kl() | hauptModul.getObjekt().getBasisObjektarten().isAnh53Gr())
			{
				getArtBox().addItem(BasisObjektarten.getObjektart(17)); //Anhang 53 (<3000) (360.33)
				getArtBox().addItem(BasisObjektarten.getObjektart(18));    // Anhang 53 (>3000) (360.33)
			}
			
			else
			{	
				getArtBox().addItem(hauptModul.getObjekt().getBasisObjektarten());	
			}
		} 	
		
		if (hauptModul.getObjekt() != null) {
			if (hauptModul.getObjekt().getBasisBetreiber() != null) {
				BasisBetreiber betr = hauptModul.getObjekt().getBasisBetreiber();
				getBetreiberFeld().setText(betr.toString());
				String toolTip = 
					"<html><b>Anrede:</b> "+((betr.getBetranrede() != null) ? betr.getBetranrede() : "")+"<br>" +
					"<b>Name:</b> "+betr.getBetrname()+"<br>";
				if (betr.getBetrnamezus() != null) {
					toolTip += "<b>Zusatz:</b> "+betr.getBetrnamezus()+"<br><br>"; 
				}
				if (betr.getStrasse() != null) {
					toolTip += "<b>Adresse:</b><br>"+ betr.getStrasse() +" "+ betr.getHausnr();
					if (betr.getHausnrzus() != null) {
						toolTip += betr.getHausnrzus();
					}
					toolTip += "<br>";
				}
				toolTip += ((betr.getPlzzs() != null) ? betr.getPlzzs().trim() + " - " : "") + ((betr.getPlz() != null) ? betr.getPlz() + " " : "") + ((betr.getOrt() != null) ? betr.getOrt() : "");
				if (betr.getTelefon() != null) {
					toolTip += "<br><br><b>Telefon:</b> " + betr.getTelefon();
				}
				toolTip += "</html>";
				getBetreiberFeld().setToolTipText(toolTip);
			}
			if (hauptModul.getObjekt().getBasisStandort() != null) {
				BasisStandort sta = hauptModul.getObjekt().getBasisStandort();
				String toolTip = "<html>"+ sta +"<br>";
				if (sta.getPlz() != null) {
					toolTip += "<b>PLZ:</b> "+sta.getPlz()+"<br>";
				}
				toolTip +=
					"<b>Gemarkung:</b> "+sta.getBasisGemarkung() +
					((sta.getEntgebid() != null) ? "<br><b>Entw.gebiet:</b> "+sta.getEntgebid() : "") +
					"</html>";
				getStandortFeld().setToolTipText(toolTip);
				getStandortFeld().setText(sta.toString());
			}
			
			if (hauptModul.getObjekt().getBasisObjektarten() != null) {
				getArtBox().setSelectedItem(hauptModul.getObjekt().getBasisObjektarten());
			}
			if (hauptModul.getObjekt().getInaktiv() != null) {
				if (hauptModul.getObjekt().getInaktiv() == true) {
					getInaktivBox().setSelected(true);
				} else {
					getInaktivBox().setSelected(false);
				}
			}
			
			if (hauptModul.getObjekt().getBeschreibung() != null) {
				getBeschreibungsArea().setText(hauptModul.getObjekt().getBeschreibung());
			}
		}
	}
	
	public void clearForm() {
		getBetreiberFeld().setText("");
		getBetreiberFeld().setToolTipText(null);
		getStandortFeld().setText("");
		getStandortFeld().setToolTipText(null);
		if (getArtBox().getItemCount() > 0) {
			getArtBox().setSelectedIndex(0);
		}
		getInaktivBox().setSelected(false);
		getBeschreibungsArea().setText(null);
	}
	
	public void enableAll(boolean enabled) {
		getSaveButton().setEnabled(enabled);
		getBetreiberToolBar().setEnabled(enabled);
		getStandortToolBar().setEnabled(enabled);
		getArtBox().setEnabled(enabled);
		getInaktivBox().setEnabled(enabled);
		getBeschreibungsArea().setEnabled(enabled);
	}
	
	public String getName() {
		return name;
	}
	
	private boolean saveObjektDaten() {
		boolean success;
		
		// Eingegebene Daten für das Objekt übernehmen
		// Betreiber / Standort werden schon nach der Auswahl durch die chooseButtons gesetzt
		hauptModul.getObjekt().setBasisObjektarten((BasisObjektarten)getArtBox().getSelectedItem());
		hauptModul.getObjekt().setBeschreibung(getBeschreibungsArea().getText());
		hauptModul.getObjekt().setInaktiv(getInaktivBox().isSelected());
		
		BasisObjekt tmp = BasisObjekt.saveBasisObjekt(hauptModul.getObjekt());
		
		if (tmp != null) {
			hauptModul.setObjekt(tmp);
			hauptModul.completeObjekt();
			success = true;
			AUIKataster.debugOutput("Objekt "+hauptModul.getObjekt()+" gespeichert.", "ObjektBearbeiten.saveObjektDaten");
		} else {
			success = false;
			AUIKataster.debugOutput("Objekt "+hauptModul.getObjekt()+" konnte nicht gespeichert werden!", "ObjektBearbeiten.saveObjektDaten");
		}
		
		return success;
	}
	
	private ActionListener getEditButtonListener() {
		if (editButtonListener == null) {
			editButtonListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String action = e.getActionCommand();
					
					BasisBetreiber betreiber = hauptModul.getObjekt().getBasisBetreiber();
					BasisStandort standort = hauptModul.getObjekt().getBasisStandort();
					
					if ("betreiber_edit".equals(action) && betreiber != null) {
						BetreiberEditor editDialog = new BetreiberEditor(betreiber, hauptModul.getFrame());
						editDialog.setLocationRelativeTo(hauptModul.getFrame());
						
						editDialog.setVisible(true);
						
						hauptModul.getObjekt().setBasisBetreiber(editDialog.getBetreiber());
					} else if ("standort_edit".equals(action) && standort != null) {
						StandortEditor editDialog = new StandortEditor(standort, hauptModul.getFrame());
						editDialog.setLocationRelativeTo(hauptModul.getFrame());
						
						editDialog.setVisible(true);
						
						hauptModul.getObjekt().setBasisStandort(editDialog.getStandort());
					}
					
					updateForm();
				}
			};
		}
		
		return editButtonListener;
	}
	
	public JTextField getBetreiberFeld() {
		if (betreiberFeld == null) {
			betreiberFeld = new JTextField("");
			betreiberFeld.setEditable(false);
		}
		return betreiberFeld;
	}
	
	private JToolBar getBetreiberToolBar() {
		if (betreiberToolBar == null) {
			betreiberToolBar = new JToolBar();
			betreiberToolBar.setFloatable(false);
			betreiberToolBar.setRollover(true);
			
			betreiberToolBar.add(getBetreiberChooseButton());
			betreiberToolBar.add(getBetreiberEditButton());
			betreiberToolBar.add(getBetreiberNewButton());
		}
		return betreiberToolBar;
	}
	
	private JButton getBetreiberChooseButton() {
		if (betreiberChooseButton == null) {
			betreiberChooseButton = new JButton(AuikUtils.getIcon(16, "reload.png", ""));
			betreiberChooseButton.setHorizontalAlignment(JButton.CENTER);
			betreiberChooseButton.setToolTipText("Betreiber auswählen");
			
			betreiberChooseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BasisBetreiber betreiber = hauptModul.getObjekt().getBasisBetreiber();
					if (betreiber == null) {
						betreiber = new BasisBetreiber();
					}
					ChooseDialog chooser = new ChooseDialog(betreiber, hauptModul.getFrame());
					chooser.setVisible(true);
					
					hauptModul.getObjekt().setBasisBetreiber(chooser.getChosenBetreiber());
					updateForm();
				}
			});
		}
		
		return betreiberChooseButton;
	}
	
	private JButton getBetreiberEditButton() {
		if (betreiberEditButton == null) {
			betreiberEditButton = new JButton(AuikUtils.getIcon(16, "edit.png", ""));
			betreiberEditButton.setHorizontalAlignment(JButton.CENTER);
			betreiberEditButton.setToolTipText("Betreiber bearbeiten");
			betreiberEditButton.setActionCommand("betreiber_edit");
			
			betreiberEditButton.addActionListener(getEditButtonListener());
		}
		
		return betreiberEditButton;
	}
	
	private JButton getBetreiberNewButton() {
		if (betreiberNewButton == null) {
			betreiberNewButton = new JButton(AuikUtils.getIcon(16, "filenew.png", ""));
			betreiberNewButton.setHorizontalAlignment(JButton.CENTER);
			betreiberNewButton.setToolTipText("Neuen Betreiber anlegen");
			
			betreiberNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hauptModul.getManager().getSettingsManager().setSetting("auik.imc.return_to_objekt", true, false);
					if (hauptModul.getObjekt().getBasisBetreiber() != null) {
						hauptModul.getManager().getSettingsManager().setSetting(
								"auik.imc.use_betreiber",
								hauptModul.getObjekt().getBasisBetreiber().getBetreiberid().intValue(), false);
					}
					if (hauptModul.getObjekt().getBasisStandort() != null) {
						hauptModul.getManager().getSettingsManager().setSetting(
								"auik.imc.use_standort",
								hauptModul.getObjekt().getBasisStandort().getStandortid().intValue(), false);
					}
					hauptModul.getManager().switchModul("m_betreiber_neu");
				}
			});
		}
		
		return betreiberNewButton;
	}
	
	public JTextField getStandortFeld() {
		if (standortFeld == null) {
			standortFeld = new JTextField("");
			standortFeld.setEditable(false);
		}
		return standortFeld;
	}
	
	private JToolBar getStandortToolBar() {
		if (standortToolBar == null) {
			standortToolBar = new JToolBar();
			standortToolBar.setFloatable(false);
			standortToolBar.setRollover(true);

			standortToolBar.add(getStandortChooseButton());
			standortToolBar.add(getStandortEditButton());
			standortToolBar.add(getStandortNewButton());
		}
		return standortToolBar;
	}
	
	private JButton getStandortChooseButton() {
		if (standortChooseButton == null) {
			standortChooseButton = new JButton(AuikUtils.getIcon(16, "reload.png", ""));
			standortChooseButton.setHorizontalAlignment(JButton.CENTER);
			standortChooseButton.setToolTipText("Standort auswählen");
			
			standortChooseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BasisStandort standort = hauptModul.getObjekt().getBasisStandort();
					if (standort == null) {
						standort = new BasisStandort();
					}
					ChooseDialog chooser = new ChooseDialog(standort, hauptModul.getFrame());
					chooser.setVisible(true);
					
					hauptModul.getObjekt().setBasisStandort(chooser.getChosenStandort());
					
					updateForm();
				}
			});
		}
		
		return standortChooseButton;
	}
	
	private JButton getStandortEditButton() {
		if (standortEditButton == null) {
			standortEditButton = new JButton(AuikUtils.getIcon(16, "edit.png", ""));
			standortEditButton.setHorizontalAlignment(JButton.CENTER);
			standortEditButton.setToolTipText("Standort bearbeiten");
			standortEditButton.setActionCommand("standort_edit");
			
			standortEditButton.addActionListener(getEditButtonListener());
		}
		
		return standortEditButton;
	}
	
	private JButton getStandortNewButton() {
		if (standortNewButton == null) {
			standortNewButton = new JButton(AuikUtils.getIcon(16, "filenew.png", ""));
			standortNewButton.setHorizontalAlignment(JButton.CENTER);
			standortNewButton.setToolTipText("Neuen Standort anlegen");
			
			standortNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hauptModul.getManager().getSettingsManager().setSetting("auik.imc.return_to_objekt", true, false);
					
					if (hauptModul.getObjekt().getBasisBetreiber() != null) {
						hauptModul.getManager().getSettingsManager().setSetting(
								"auik.imc.use_betreiber",
								hauptModul.getObjekt().getBasisBetreiber().getBetreiberid().intValue(), false);
					}
					if (hauptModul.getObjekt().getBasisStandort() != null) {
						hauptModul.getManager().getSettingsManager().setSetting(
								"auik.imc.use_standort",
								hauptModul.getObjekt().getBasisStandort().getStandortid().intValue(), false);
					}
					hauptModul.getManager().switchModul("m_standort_neu");
				}
			});
		}
		
		return standortNewButton;
	}
	
	private JComboBox getArtBox() {
		if (artBox == null) {
			
				artBox = new JComboBox();
				artBox.setKeySelectionManager(new MyKeySelectionManager());
		
		}
		return artBox;
	}
	
	private JCheckBox getInaktivBox() {
		if (inaktivBox == null) {
			inaktivBox = new JCheckBox();
		}
		return inaktivBox;
	}
	
	public JTextArea getBeschreibungsArea() {
		if (beschreibungsArea == null) {
			beschreibungsArea = new LimitedTextArea(150);
			beschreibungsArea.setLineWrap(true);
			beschreibungsArea.setWrapStyleWord(true);
		}
		return beschreibungsArea;
	}
	
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton("Objekt speichern");
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((hauptModul.getObjekt().getBasisBetreiber() != null) && (hauptModul.getObjekt().getBasisStandort() != null)) {
						enableAll(false);
						if (saveObjektDaten()) {
							hauptModul.getFrame().changeStatus("Objekt "+hauptModul.getObjekt().getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
							hauptModul.setNew(false);
						} else {
							hauptModul.getFrame().changeStatus("Konnte Objekt nicht speichern!", HauptFrame.ERROR_COLOR);
						}
						
						hauptModul.fillForm();
					} else {
						hauptModul.getFrame().changeStatus("Kein Betreiber/Standort ausgewählt!", HauptFrame.ERROR_COLOR);
					}
				}
			});
		}
		return saveButton;
	}
}
