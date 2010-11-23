/*
 * Datei:
 * $Id: BasisBetreiberSuchen.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
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
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.23  2005/11/02 13:49:16  u633d
 * - Version vom 2.11.
 *
 * Revision 1.22  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.21  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.component.Factory;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisBetreiberModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisObjektModel;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.NamedObject;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
;

/**
 * Ein Modul zum Suchen und Bearbeiten eines Betreibers.
 * @author David Klotz
 */
public class BasisBetreiberSuchen extends AbstractModul {
	private String iconPath = "filefind32.png";
	
	private JComboBox suchBox;
	private JTextField suchFeld;
	private JButton submitButton;
	private JSplitPane tabellenSplit;
	private JTable betreiberTabelle;
	private JTable objektTabelle;
	
	private Action betreiberEditAction;
	private Action betreiberLoeschAction;
	private Action objektNeuAction;
	private JPopupMenu betreiberPopup;
	
	private Action objektEditAction;
	private Action objektLoeschAction;
	private JPopupMenu objektPopup;
	
	private BasisBetreiberModel betreiberModel;
	private BasisObjektModel objektModel;
	
	/** Wird benutzt, um nach dem Bearbeiten etc. wieder den 
	selben Betreiber in der Liste auszuwählen. */
	private BasisBetreiber lastBetreiber;
	
	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Betreiber suchen";
	}

	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 * @return "m_betreiber_suchen"
	 */
	public String getIdentifier() {
		return "m_betreiber_suchen";
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	public String getCategory() {
		return "Betriebe";
	}

	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
	 * @see de.bielefeld.umweltamt.aui.AbstractModul#getIcon(String)
	 */
	public Icon getIcon() {
		return super.getIcon(iconPath);
	}
	
	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	public JPanel getPanel() {
		if (panel == null) {
			betreiberModel = new BasisBetreiberModel();
			objektModel = new BasisObjektModel("Standort", manager.getSettingsManager().getSetting("auik.prefs.abteilungsfilter"));
			
			TableFocusListener tfl = TableFocusListener.getInstance();
			getBetreiberTabelle().addFocusListener(tfl);
			getObjektTabelle().addFocusListener(tfl);

			
			JScrollPane betreiberScroller = new JScrollPane(getBetreiberTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			JScrollPane objektScroller = new JScrollPane(getObjektTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			JToolBar submitToolBar = new JToolBar();
			submitToolBar.setFloatable(false);
			submitToolBar.setRollover(true);
			submitToolBar.add(getSubmitButton());
			
			// Die Tab-Action ist in eine neue Klasse ausgelagert, 
			// weil man sie evtl. öfters brauchen wird. 
			TabAction ta = new TabAction();
			ta.addComp(getSuchFeld());
			ta.addComp(getBetreiberTabelle());
			ta.addComp(getObjektTabelle());
			
			tabellenSplit = 
				Factory.createStrippedSplitPane(
						JSplitPane.VERTICAL_SPLIT, 
						betreiberScroller, 
						objektScroller, 
						0.7
				);
			
			FormLayout layout = new FormLayout(
					"50dlu, 4dlu, pref:grow, 3dlu, min(16dlu;p)",	// spalten 
					"pref, 3dlu, 150dlu:grow(1.0)"); 		// zeilen
			
			PanelBuilder builder = new PanelBuilder(layout);
			builder.setDefaultDialogBorder();
			CellConstraints cc = new CellConstraints();
			
			builder.add(getSuchBox(),	cc.xy( 1, 1));
			builder.add(getSuchFeld(),	cc.xy( 3, 1));
			builder.add(submitToolBar,	cc.xy( 5, 1));
			builder.add(tabellenSplit,	cc.xyw(1, 3, 5));
			
			panel = builder.getPanel();
		}
		return panel;
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#show()
	 */
	public void show() {
		super.show();
		
		// Gespeicherte Position des Dividers setzen
		if (SettingsManager.getInstance().getSetting("auik.prefs.divloc_betreiber") != null) {
			double divloc = Double.parseDouble(SettingsManager.getInstance().getSetting("auik.prefs.divloc_betreiber"));
			tabellenSplit.setDividerLocation(divloc);
		}
		
		lastBetreiber = null;
		updateBetreiberListe();
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#hide()
	 */
	public void hide() {
		super.hide();
		
		// Position des Dividers des SplitPanes speichern
		if ((tabellenSplit != null) && (tabellenSplit.getDividerLocation() != -1)) {
			double divloc = (float)(tabellenSplit.getDividerLocation()) / (tabellenSplit.getHeight());
			if (divloc >= 0.0 && divloc <= 1.0) {
				SettingsManager.getInstance().setSetting("auik.prefs.divloc_betreiber", Double.toString(divloc), true);
			}
		}
	}
	
	public void updateBetreiberListe() {
		SwingWorkerVariant worker = new SwingWorkerVariant(getSuchFeld()) {
			protected void doNonUILogic() throws RuntimeException {
				betreiberModel.updateList();
			}

			protected void doUIUpdateLogic() throws RuntimeException {
				betreiberModel.fireTableDataChanged();
				
				if (lastBetreiber != null) {
					// Wenn der Betreiber noch in der Liste ist, wird er ausgewählt.
					int row = betreiberModel.getList().indexOf(lastBetreiber);
					if (row != -1) {
						getBetreiberTabelle().setRowSelectionInterval(row, row);
						getBetreiberTabelle().scrollRectToVisible(getBetreiberTabelle().getCellRect(row, 0, true));
						getBetreiberTabelle().requestFocus();
					}
				} else {	
					int betreiberCount = betreiberModel.getRowCount();
					if (betreiberCount > 0) {
						String statusMsg = "Suche: " + betreiberCount + " Ergebnis";
						if (betreiberCount != 1) {
							statusMsg += "se";
						}
						statusMsg += ".";
						frame.changeStatus(statusMsg);
					}
				}
				
				updateObjekte();
			}
		};
		
		worker.start();
	}
	
	public void updateObjekte() {
		ListSelectionModel lsm = getBetreiberTabelle().getSelectionModel();
		if (!lsm.isSelectionEmpty()) {
			int selectedRow = lsm.getMinSelectionIndex();
			BasisBetreiber betr = betreiberModel.getRow(selectedRow);
			AUIKataster.debugOutput("Betreiber " + betr.getBetrname() + " (ID" + betr.getBetreiberid() + ") angewählt.", "BasisBetreiberSuchen.updateObjekte");
			searchObjekteByBetreiber(betr);
		}
	}
	
	/**
	 * öffnet einen Dialog um einen Betreiber-Datensatz zu bearbeiten.
	 * @param betr Der Betreiber
	 */
	public void editBetreiber(BasisBetreiber betr) {
		BetreiberEditor editDialog = new BetreiberEditor(betr, frame);
		editDialog.setLocationRelativeTo(frame);
		
		editDialog.setVisible(true);
		
		lastBetreiber = betr;
		
		// Nach dem Bearbeiten die Liste updaten, damit unsere Änderungen auch angezeigt werden.
		updateBetreiberListe();
	}
	
	/** 
	 * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines Betreibers.
	 * @param betreiberid Die Betreiber-Id
	 */
	public void searchObjekteByBetreiber(final BasisBetreiber betreiber) {
		// ... siehe show()
		SwingWorkerVariant worker = new SwingWorkerVariant(getBetreiberTabelle()) {
			protected void doNonUILogic() throws RuntimeException {
				objektModel.searchByBetreiber(betreiber);
			}
			
			protected void doUIUpdateLogic() throws RuntimeException {
				objektModel.fireTableDataChanged();
			}
		};
		worker.start();	
	}
	
	/**
	 * Filtert / Durchsucht die Betreiber-Liste. 
	 * @param suche Wonach soll gesucht werden?
	 * @param column Nach welcher Eigenschaft des Betreiber soll gesucht werden?
	 */
	public void filterBetreiberListe(final String suche, final String column) {
			SwingWorkerVariant worker = new SwingWorkerVariant(getBetreiberTabelle()) {
				protected void doNonUILogic() throws RuntimeException {
					betreiberModel.filterList(suche, column);
				}

				protected void doUIUpdateLogic() throws RuntimeException {
					getBetreiberTabelle().clearSelection();
					
					betreiberModel.fireTableDataChanged();
					String statusMsg = "Suche: " + betreiberModel.getRowCount() + " Ergebnis";
					if (betreiberModel.getRowCount() != 1) {
						statusMsg += "se";
					}
					statusMsg += ".";
					frame.changeStatus(statusMsg);
				}
			};
			
			frame.changeStatus("Suche...");
			worker.start();
	}
	
	private Action getBetreiberEditAction() {
		if (betreiberEditAction == null) {
			betreiberEditAction = new AbstractAction("Bearbeiten") {
				public void actionPerformed(ActionEvent e) {
					int row = getBetreiberTabelle().getSelectedRow();
					
					// Natürlich nur editieren, wenn wirklich eine Zeile ausgewählt ist
					if (row != -1) {
						BasisBetreiber betr = betreiberModel.getRow(row);
						editBetreiber(betr);
					}
				}
			};
			betreiberEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
			betreiberEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
		}
		
		return betreiberEditAction;
	}
	
	private Action getBetreiberLoeschAction() {
		if (betreiberLoeschAction == null) {
			betreiberLoeschAction = new AbstractAction("Löschen") {
				public void actionPerformed(ActionEvent e) {
					int row = getBetreiberTabelle().getSelectedRow();
					if (row != -1 && getBetreiberTabelle().getEditingRow() == -1) {
						if (objektModel.getRowCount() != 0) {
							frame.changeStatus("Kann Betreiber nicht löschen: Zu erst alle zugehörigen Objekte löschen!", HauptFrame.ERROR_COLOR);
						} else {
							BasisBetreiber betr = betreiberModel.getRow(row);
							int answer = JOptionPane.showConfirmDialog(panel, "Soll der Betreiber '"+ betr +"' wirklich gelöscht werden?", "Löschen bestätigen", JOptionPane.YES_NO_OPTION);
							if (answer == JOptionPane.YES_OPTION) {
								if (betreiberModel.removeRow(row)) {
									frame.changeStatus("Betreiber gelöscht.", HauptFrame.SUCCESS_COLOR);
									AUIKataster.debugOutput("Betreiber " + betr.getBetreiberid() + " wurde gelöscht!", "BasisBetreiberSuchen.removeAction");
								} else {
									frame.changeStatus("Konnte den Betreiber nicht löschen!", HauptFrame.ERROR_COLOR);
								}
							}
						}
					}
				}
			};
			betreiberLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
			betreiberLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
		}
		
		return betreiberLoeschAction;
	}
	
	private Action getObjektNeuAction() {
		if (objektNeuAction == null) {
			objektNeuAction = new AbstractAction("Neues Objekt") {
				public void actionPerformed(ActionEvent e) {
					int row = betreiberTabelle.getSelectedRow();
					
					if (row != -1) {
						BasisBetreiber betr = betreiberModel.getRow(row);
						manager.getSettingsManager().setSetting("auik.imc.use_betreiber", betr.getBetreiberid().intValue(), false);
						manager.switchModul("m_objekt_bearbeiten");
					}
				}
			};
			objektNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_O));
		}
		
		return objektNeuAction;
	}
	
	private Action getObjektEditAction() {
		if (objektEditAction == null) {
			objektEditAction = new AbstractAction("Bearbeiten") {
				public void actionPerformed(ActionEvent e) {
					int row = objektTabelle.getSelectedRow();
					BasisObjekt obj = objektModel.getRow(row);
					if (row != -1 || obj.getBasisObjektarten().getObjektartid() != 40) {
						manager.getSettingsManager().setSetting("auik.imc.edit_object", obj.getObjektid().intValue(), false);
						manager.switchModul("m_objekt_bearbeiten");
					}
					else if (row != -1 || obj.getBasisObjektarten().getObjektartid() == 40) {
						manager.getSettingsManager().setSetting("auik.imc.edit_object", obj.getObjektid().intValue(), false);
						manager.switchModul("m_sielhaut1");
					}
				}
			};
			objektEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
			objektEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
		}
		
		return objektEditAction;
	}
	
	private Action getObjektLoeschAction() {
		if (objektLoeschAction == null) {
			objektLoeschAction = new AbstractAction("Löschen") {
				public void actionPerformed(ActionEvent e) {
					int row = getObjektTabelle().getSelectedRow();
					if (row != -1 && getObjektTabelle().getEditingRow() == -1) {
						BasisObjekt objekt = objektModel.getRow(row);
						int answer = JOptionPane.showConfirmDialog(panel, 
								"Soll das Objekt "+ objekt.getObjektid() +" und alle seine Fachdaten wirklich gelöscht werden?\n" +
								"Hinweis: Manche Objekte können auch erst gelöscht werden, wenn für sie\n" +
								"keine Fachdaten mehr existieren.",
								"Löschen bestätigen", JOptionPane.YES_NO_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							if (objektModel.removeRow(row)) {
								frame.changeStatus("Objekt gelöscht.", HauptFrame.SUCCESS_COLOR);
								AUIKataster.debugOutput("Objekt " + objekt.getObjektid() + " wurde gelöscht!", "BasisBetreiberSuchen.removeAction");
							} else {
								frame.changeStatus("Konnte das Objekt nicht löschen!", HauptFrame.ERROR_COLOR);
							}
						}
					}
				}
			};
			objektLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
			objektLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
		}
		
		return objektLoeschAction;
	}
	
	private void showBetreiberPopup(MouseEvent e) {
		if (betreiberPopup == null) {
			betreiberPopup = new JPopupMenu("Betreiber");
			JMenuItem bearbItem = new JMenuItem(getBetreiberEditAction());
			JMenuItem loeschItem = new JMenuItem(getBetreiberLoeschAction());
			JMenuItem neuItem = new JMenuItem(getObjektNeuAction());
			betreiberPopup.add(bearbItem);
			betreiberPopup.add(loeschItem);
			betreiberPopup.add(neuItem);
		}
		
		if (e.isPopupTrigger()) {
			Point origin = e.getPoint();
			int row = betreiberTabelle.rowAtPoint(origin);
			
			if (row != -1) {
				betreiberTabelle.setRowSelectionInterval(row, row);
				betreiberPopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	private void showObjektPopup(MouseEvent e) {
		if (objektPopup == null) {
			objektPopup = new JPopupMenu("Objekt");
			JMenuItem bearbItem = new JMenuItem(getObjektEditAction());
			JMenuItem loeschItem = new JMenuItem(getObjektLoeschAction());
			objektPopup.add(bearbItem);
			objektPopup.add(loeschItem);
		}
		
		if (e.isPopupTrigger()) {
			Point origin = e.getPoint();
			int row = objektTabelle.rowAtPoint(origin);
			
			if (row != -1) {
				objektTabelle.setRowSelectionInterval(row, row);
				objektPopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	private JTable getBetreiberTabelle() {
		if (betreiberTabelle == null) {
			betreiberTabelle = new JTable(betreiberModel);
			
			// Wir wollen wissen, wenn eine andere Zeile ausgewählt wurde
			ListSelectionModel rowSM = betreiberTabelle.getSelectionModel();
			rowSM.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					// überzählige Events ignorieren
					if (e.getValueIsAdjusting()) {
						return;
					}
					
					updateObjekte();
				}
			});

			betreiberTabelle.getColumnModel().getColumn(0).setPreferredWidth(300);
			betreiberTabelle.getColumnModel().getColumn(1).setPreferredWidth(60);
			betreiberTabelle.getColumnModel().getColumn(2).setPreferredWidth(70);
			
			betreiberTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			betreiberTabelle.setColumnSelectionAllowed(false);
			betreiberTabelle.setRowSelectionAllowed(true);
			
			betreiberTabelle.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = getBetreiberTabelle().rowAtPoint(origin);
						
						BasisBetreiber betr = betreiberModel.getRow(row);
						AUIKataster.debugOutput("Doppelklick auf Zeile " + row, "BasisBetreiberSuchen");
						editBetreiber(betr);
					}
				}
				
				public void mousePressed(MouseEvent e) {
					showBetreiberPopup(e);
				}
				
				public void mouseReleased(MouseEvent e) {
					showBetreiberPopup(e);
				}
			});
			
			// Bei Enter in der Tabelle wird der Betreiber auch bearbeitet
			// und mit TAB soll man zwischen den Tabellen springen können.
			// Dafür benutzen wir das (seit 1.3) neue Swing-Konzept
			// der Action- und InputMaps mit dem bestimmte Aktionen ("Actions")
			// bestimmten Tastendrücken ("Inputs")zugewiesen werden können.
			
	        // Den "Enter"-KeyStroke in die InputMap der Tabelle einfügen
	        betreiberTabelle.getInputMap().put((KeyStroke)getBetreiberEditAction().getValue(Action.ACCELERATOR_KEY), getBetreiberEditAction().getValue(Action.NAME));
	        // Die Action dem "Enter"-KeyStroke zuweisen
			betreiberTabelle.getActionMap().put(getBetreiberEditAction().getValue(Action.NAME), getBetreiberEditAction());
			
			
			betreiberTabelle.getInputMap().put((KeyStroke)getBetreiberLoeschAction().getValue(Action.ACCELERATOR_KEY), getBetreiberLoeschAction().getValue(Action.NAME));
			betreiberTabelle.getActionMap().put(getBetreiberLoeschAction().getValue(Action.NAME), getBetreiberLoeschAction());
		}
		return betreiberTabelle;
	}
	
	private JTable getObjektTabelle() {
		if (objektTabelle == null) {
			objektTabelle = new JTable(objektModel);
			objektTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			objektTabelle.getColumnModel().getColumn(0).setMaxWidth(60);
			objektTabelle.getColumnModel().getColumn(0).setPreferredWidth(objektTabelle.getColumnModel().getColumn(0).getMaxWidth()-10);
			
			objektTabelle.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = getObjektTabelle().rowAtPoint(origin);
						BasisObjekt obj = objektModel.getRow(row);
						if (row != -1 && obj.getBasisObjektarten().getObjektartid().intValue() != 40) {
							manager.getSettingsManager().setSetting("auik.imc.edit_object", obj.getObjektid().intValue(), false);
							manager.switchModul("m_objekt_bearbeiten");
						}
						else if (row != -1 && obj.getBasisObjektarten().getObjektartid().intValue() == 40) {
							manager.getSettingsManager().setSetting("auik.imc.edit_object", obj.getObjektid().intValue(), false);
							manager.switchModul("m_sielhaut1");
						}
					}
				}
				
				public void mousePressed(MouseEvent e) {
					showObjektPopup(e);
				}
				
				public void mouseReleased(MouseEvent e) {
					showObjektPopup(e);
				}
			});
			
			objektTabelle.getInputMap().put((KeyStroke)getObjektEditAction().getValue(Action.ACCELERATOR_KEY), getObjektEditAction().getValue(Action.NAME));
			objektTabelle.getActionMap().put(getObjektEditAction().getValue(Action.NAME), getObjektEditAction());
			
			objektTabelle.getInputMap().put((KeyStroke)getObjektLoeschAction().getValue(Action.ACCELERATOR_KEY), getObjektLoeschAction().getValue(Action.NAME));
			objektTabelle.getActionMap().put(getObjektLoeschAction().getValue(Action.NAME), getObjektLoeschAction());
		}
		return objektTabelle;
	}
	private JComboBox getSuchBox() {
		if (suchBox == null) {
			suchBox = new JComboBox(new NamedObject[]{new NamedObject("Name:","name"), new NamedObject("Anrede:","anrede"), new NamedObject("Zusatz:","zusatz"), new NamedObject("Irgendwo",null)});
		}
		return suchBox;
	}
	private JTextField getSuchFeld() {
		if (suchFeld == null) {
			suchFeld = new JTextField();
			
			suchFeld.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String suche = getSuchFeld().getText();
					String spalte = (String) ((NamedObject) getSuchBox().getSelectedItem()).getValue();
					filterBetreiberListe(suche, spalte);
				}
			});
			suchFeld.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
			
			suchFeld.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_TAB) {
						String suche = getSuchFeld().getText();
						String spalte = (String) ((NamedObject) getSuchBox().getSelectedItem()).getValue();
						filterBetreiberListe(suche, spalte);
					}
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
					String suche = getSuchFeld().getText();
					String spalte = (String) ((NamedObject) getSuchBox().getSelectedItem()).getValue();
					filterBetreiberListe(suche, spalte);
				}
			});
		}
		
		return submitButton;
	}
}
