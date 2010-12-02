/*
 * Datei:
 * $Id: BasisStandortSuchen.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 * 
 * Erstellt am 12.01.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2010/02/24 10:45:53  u633d
 * Gis Pfade ueber Settings
 *
 * Revision 1.9  2009/12/14 10:36:50  u633d
 * *** empty log message ***
 *
 * Revision 1.8  2009/12/10 10:25:14  u633d
 * GIS oeffnen
 *
 * Revision 1.7  2009/12/02 06:31:41  u633d
 * Verbesserung Aufruf designs
 *
 * Revision 1.6  2009/11/23 06:52:53  u633d
 * VAwS-StandortListe
 *
 * Revision 1.4  2009/09/21 11:14:51  u633d
 * GIS oeffnen
 *
 * Revision 1.3  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.27  2005/11/02 13:49:16  u633d
 * - Version vom 2.11.
 *
 * Revision 1.26  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.25  2005/09/28 11:11:14  u633d
 * - Version vom 28.9.
 *
 * Revision 1.24  2005/09/07 10:48:23  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.23  2005/08/24 08:42:51  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.22  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
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
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import org.eclipse.birt.report.engine.api.EngineException;
import de.bielefeld.umweltamt.aui.ReportManager;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.component.Factory;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.module.common.editors.StandortEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisObjektModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStandortModel;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.BasicEntryField;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Ein Modul zum Suchen und Bearbeiten eines Standorts.
 * @author David Klotz
 */
public class BasisStandortSuchen extends AbstractModul {
	private JTextField strassenFeld;
	private JTextField hausnrFeld;
	private JButton submitButton;
	private JButton dreiButton;
	private JButton vierButton;
	private JButton probepktButton;
	private JTable standortTabelle;
	private JTable objektTabelle;
	private JSplitPane tabellenSplit;
	
	private Action standortEditAction;
	private Action objektNeuAction;
	private JPopupMenu standortPopup;
	
	private Action objektEditAction;
	private Action objektLoeschAction;
	private Action gisAction;
	private JPopupMenu objektPopup;
	
	private BasisStandortModel standortModel;
	private BasisObjektModel objektModel;
	
	/** Wird benutzt, um nach dem Bearbeiten etc. wieder den 
	selben Standort in der Liste auszuwählen. */
	private BasisStandort lastStandort;
	
	private Timer suchTimer;
	
	private JButton reportStandortListeButton;
	protected String betreiber;
	protected String standort;
	protected int standortID;
	
	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Standort suchen";
	}

	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 * @return "m_standort_suchen"
	 */
	public String getIdentifier() {
		return "m_standort_suchen";
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	public String getCategory() {
		return "Betriebe";
	}
	
	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
	 */
	public Icon getIcon() {
		return super.getIcon("filefind32.png");
	}
	
	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	public JPanel getPanel() {
		if (panel == null) {
			standortModel = new BasisStandortModel();
			objektModel = new BasisObjektModel("Betreiber", manager.getSettingsManager().getSetting("auik.prefs.abteilungsfilter"));
			
			TableFocusListener tfl = TableFocusListener.getInstance();
			getStandortTabelle().addFocusListener(tfl);
			getObjektTabelle().addFocusListener(tfl);
			
			JScrollPane standortScroller = new JScrollPane(getStandortTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			JScrollPane objektScroller = new JScrollPane(getObjektTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			JToolBar submitToolBar = new JToolBar();
			submitToolBar.setFloatable(false);
			submitToolBar.setRollover(true);
			submitToolBar.add(getSubmitButton());
			
			JPanel restrictPanel =new JPanel(new BorderLayout());
			
			JPanel restrictButtonBar = ButtonBarFactory.buildLeftAlignedBar(getDreiButton(), getVierButton(), getProbepktButton() );
			JPanel restrictButtonBar2 = ButtonBarFactory.buildRightAlignedBar( getReportListeButton() );
			
			restrictPanel.add(new Label("Objekte einschränken:"), BorderLayout.WEST);
			restrictPanel.add(restrictButtonBar, BorderLayout.CENTER);
			restrictPanel.add(restrictButtonBar2, BorderLayout.EAST);
			
			
			
			
			
	        
		
			
			// Die Tab-Action ist in eine neue Klasse ausgelagert, 
			// weil man sie evtl. öfters brauchen wird. 
			TabAction ta = new TabAction();
			ta.addComp(getStrassenFeld());
			ta.addComp(getHausnrFeld());
			ta.addComp(getStandortTabelle());
			ta.addComp(getObjektTabelle());
			
			tabellenSplit = 
				Factory.createStrippedSplitPane(
						JSplitPane.VERTICAL_SPLIT, 
						standortScroller, 
						objektScroller, 
						0.6
				);
			
			FormLayout layout = new FormLayout(
			"l:p, 4dlu, p:g, 10dlu, p, 4dlu, max(30dlu;p), 3dlu, min(16dlu;p)",	// spalten 
			"pref, 3dlu, 150dlu:grow, 3dlu, 30"); 	// zeilen
			layout.setColumnGroups(new int[][]{{1, 5}});
			
			PanelBuilder builder = new PanelBuilder(layout);
			builder.setDefaultDialogBorder();
			CellConstraints cc = new CellConstraints();
			
			builder.addLabel("Straße:",		cc.xy( 1, 1));
			builder.add(getStrassenFeld(),	cc.xy( 3, 1));
			builder.addLabel("Haus-Nr.:",	cc.xy( 5, 1));
			builder.add(getHausnrFeld(),	cc.xy( 7, 1));
			builder.add(submitToolBar,		cc.xy( 9, 1));
			builder.add(tabellenSplit,		cc.xyw(1, 3, 9));
			builder.add(restrictPanel,	cc.xyw(1, 5, 9));
			
			
			
			
			
			
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
		if (SettingsManager.getInstance().getSetting("auik.prefs.divloc_standort") != null) {
			double divloc = Double.parseDouble(SettingsManager.getInstance().getSetting("auik.prefs.divloc_standort"));
//			AUIKataster.debugOutput("Lese divloc_standort als: " + divloc, "BasisStandortSuchen.DIVIDER");
			tabellenSplit.setDividerLocation(divloc);
		}
		
		lastStandort = null;
		updateStandortListe();
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#hide()
	 */
	public void hide() {
		super.hide();
		if (suchTimer != null) {
			getSuchTimer().stop();
		}
		
		// Position des Dividers des SplitPanes speichern
		if ((tabellenSplit != null) && (tabellenSplit.getDividerLocation() != -1)) {
			double divloc = (float)(tabellenSplit.getDividerLocation()) / (tabellenSplit.getHeight());
//			AUIKataster.debugOutput("Setze divloc_standort auf: ("+ tabellenSplit.getDividerLocation() +"/"+ tabellenSplit.getHeight() +") = " + divloc, "BasisStandortSuchen.DIVIDER");
			if (divloc >= 0.0 && divloc <= 1.0) {
				SettingsManager.getInstance().setSetting("auik.prefs.divloc_standort", Double.toString(divloc), true);
			}
		}
	}
	
	public void updateStandortListe() {
		SwingWorkerVariant worker = new SwingWorkerVariant(getStrassenFeld()) {
			protected void doNonUILogic() {
				standortModel.updateList();
			}

			protected void doUIUpdateLogic() {
				standortModel.fireTableDataChanged();
				
				if (lastStandort != null) {
					// Wenn der Standort noch in der Liste ist, wird er ausgewählt.
					int row = standortModel.getList().indexOf(lastStandort);
					if (row != -1) {
						getStandortTabelle().setRowSelectionInterval(row, row);
						getStandortTabelle().scrollRectToVisible(getStandortTabelle().getCellRect(row, 0, true));
						getStandortTabelle().requestFocus();
					}
				} else {
					int standortCount = standortModel.getRowCount();
					if (standortCount > 0) {
						String statusMsg = "Suche: " + standortCount + " Ergebnis";
						if (standortCount != 1) {
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
		ListSelectionModel lsm = getStandortTabelle().getSelectionModel();
		if (!lsm.isSelectionEmpty()) {
			int selectedRow = lsm.getMinSelectionIndex();
			BasisStandort standort = standortModel.getRow(selectedRow);
			AUIKataster.debugOutput("Standort " + standort + " angewählt.", "BasisStandortSuchen.updateObjekte");
			searchObjekteByStandort(standort);
		}
	}
	
	/**
	 * öffnet einen Dialog um einen Standort-Datensatz zu bearbeiten.
	 * @param standort Der Standort
	 */
	public void editStandort(BasisStandort standort) {
		StandortEditor editDialog = null;
		
		editDialog = new StandortEditor(standort, frame);
		editDialog.setLocationRelativeTo(frame);
			
		editDialog.setVisible(true);
		
		lastStandort = standort;
		
		if (editDialog.wasSaved()) {
			// Nach dem Bearbeiten die Liste updaten, damit unsere Änderungen auch angezeigt werden.
			updateStandortListe();
		}
	}
	
	/** 
	 * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines Standorts.
	 * @param standortid Die Standort-Id
	 * @param abteilung 33 oder 34
	 */
	public void searchObjekteByStandort(final BasisStandort standort, final String abteilung, final Integer nichtartid) {

		// ... siehe show()
		SwingWorkerVariant worker = new SwingWorkerVariant(getStandortTabelle()) {
			protected void doNonUILogic() {
				objektModel.searchByStandort(standort,abteilung, nichtartid);
			}
			
			protected void doUIUpdateLogic() {
				objektModel.fireTableDataChanged();
			}
		};
		worker.start();	
	}
	
	/** 
	 * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines Standorts.
	 * @param standortid Die Standort-Id
	 */
	public void searchObjekteByStandort(final BasisStandort standort) {
		
		// ... siehe show()
		SwingWorkerVariant worker = new SwingWorkerVariant(getStandortTabelle()) {
			protected void doNonUILogic() {
				objektModel.searchByStandort(standort);
			}
			
			protected void doUIUpdateLogic() {
				objektModel.fireTableDataChanged();
			}
		};
		worker.start();	
	}
	
	/** 
	 * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines Standorts.
	 * @param standortid Die Standort-Id
	 */
	public void searchObjekteByStandort(final BasisStandort standort, final Integer istartid) {
		
		// ... siehe show()
		SwingWorkerVariant worker = new SwingWorkerVariant(getStandortTabelle()) {
			protected void doNonUILogic() {
				objektModel.searchByStandort(standort, istartid);
			}
			
			protected void doUIUpdateLogic() {
				objektModel.fireTableDataChanged();
			}
		};
		worker.start();	
	}
	
	/**
	 * Filtert die Standort-Liste nach Straße und Hausnummer.
	 * @param focusComp Welche Komponente soll nach der Suche den Fokus bekommen.
	 */
	public void filterStandortListe(Component focusComp) {
		int hausnr;
		try {
			hausnr = Integer.parseInt(getHausnrFeld().getText());
		} catch (NumberFormatException e1) {
			hausnr = -1;
		}
		final int fhausnr = hausnr;
		
		SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
			protected void doNonUILogic() {
				standortModel.filterList(getStrassenFeld().getText(), fhausnr);
			}
			
			protected void doUIUpdateLogic() {
				getStandortTabelle().clearSelection();
				
				standortModel.fireTableDataChanged();
				String statusMsg = "Suche: " + standortModel.getRowCount() + " Ergebnis";
				if (standortModel.getRowCount() != 1) {
					statusMsg += "se";
				}
				statusMsg += ".";
				frame.changeStatus(statusMsg);
			}
		};
		
		frame.changeStatus("Suche...");
		worker.start();
	}
	
	public void showReportListe() throws EngineException {
		
		ListSelectionModel lsm = getStandortTabelle()
		.getSelectionModel();
		int selectedRow = lsm.getMinSelectionIndex();
		
		BasisStandort standort = standortModel
		.getRow(selectedRow);
		
        String adresse = "" + standort;
        
        if (standort == null)
		{
			frame.changeStatus("Bitte einen Standort markieren");	
		}
		else
		{
			frame.changeStatus("PDF-Datei wird erstellt");	 	
		}
		
        standortID = standort.getStandortid();
		
		
		
		
	AUIKataster
	.debugOutput(adresse +" mit ID: "+ standortID + " ausgewaehlt", "BasisStandortSuchen.showReportListe");

	ReportManager.getInstance().startReportWorker("VAwS-StandortListe",adresse, standortID,   reportStandortListeButton);
	//ReportManager.getInstance().startReportWorker("Suev-Kan", standortID, reportStandortListeButton);
	
	}
	
	private Timer getSuchTimer() {
		if (suchTimer == null) {
			suchTimer = new Timer(900, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Was diese ganze "SwingWorkerVariant"-Geschichte 
					// soll, steht unter http://www.javaworld.com/javaworld/jw-06-2003/jw-0606-swingworker.html
					// Ist auch ausgedruckt im Ordner im Regal. -DK
					SwingWorkerVariant worker = new SwingWorkerVariant(getStrassenFeld()) {
						protected String oldText = "";
						private String newText = "";
						
						protected void doNonUILogic() {
							oldText = getStrassenFeld().getText();
							if (oldText.equals("")) {
								newText = "";
							} else {
								String suchText = AuikUtils.sanitizeQueryInput(oldText);
								BasisStrassen str = BasisStrassen.getStrasseByName(suchText);
								
								if (str != null) {
									newText = str.getStrasse();
								} else {
									newText = oldText;
								}
							}
						}
						
						protected void doUIUpdateLogic() {
							getStrassenFeld().setText(newText);
							getStrassenFeld().setSelectionStart(oldText.length());
							getStrassenFeld().setSelectionEnd(newText.length());
						}
					};
					worker.start();
				}
			});			
			suchTimer.setRepeats(false);
		}
		
		return suchTimer;
	}
	
	private JTextField getStrassenFeld() {
		if (strassenFeld == null) {
			strassenFeld = new JTextField("");
			strassenFeld.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
			
			strassenFeld.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSuchTimer().stop();
					filterStandortListe(getStandortTabelle());
				}
			});
			
			strassenFeld.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_TAB) {
						getSuchTimer().stop();
						filterStandortListe(getHausnrFeld());
					}
				}
				
				public void keyTyped(KeyEvent e) {
					if (Character.isLetterOrDigit(e.getKeyChar())) {
						if (getSuchTimer().isRunning()) {
							getSuchTimer().restart();
						} else {
							getSuchTimer().start();
						}
					}
				}
			});
		}
		return strassenFeld;
	}
	private JTextField getHausnrFeld() {
		if (hausnrFeld == null) {
			hausnrFeld = new BasicEntryField();
			
			hausnrFeld.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filterStandortListe(getStandortTabelle());
				}
			});
		}
		return hausnrFeld;
	}
	
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton(AuikUtils.getIcon(16, "key_enter.png"));
			submitButton.setToolTipText("Suche starten");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSuchTimer().stop();
					filterStandortListe(getStandortTabelle());
				}
			});
		}
		
		return submitButton;
	}
	
	private JButton getDreiButton() {
		if (dreiButton == null) {
			dreiButton = new JButton("360.33");
			dreiButton.setToolTipText("nur 33er Objekt");
			dreiButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListSelectionModel lsm = getStandortTabelle()
							.getSelectionModel();
					if (!lsm.isSelectionEmpty()) {
						int selectedRow = lsm.getMinSelectionIndex();
						BasisStandort standort = standortModel
								.getRow(selectedRow);
						AUIKataster
								.debugOutput("Standort " + standort
										+ " angewählt.",
										"BasisStandortSuchen.updateObjekte");
						searchObjekteByStandort(standort, "360.33", 32);
					}
				}
			});
		}
		
		return dreiButton;
	}
	
	private JButton getVierButton() {
		if (vierButton == null) {
			vierButton = new JButton("360.34");
			vierButton.setToolTipText("nur 34er Objekte");
			vierButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListSelectionModel lsm = getStandortTabelle()
							.getSelectionModel();
					if (!lsm.isSelectionEmpty()) {
						int selectedRow = lsm.getMinSelectionIndex();
						BasisStandort standort = standortModel
								.getRow(selectedRow);
						AUIKataster
								.debugOutput("Standort " + standort
										+ " angewählt.",
										"BasisStandortSuchen.updateObjekte");
						searchObjekteByStandort(standort, "360.34", 32);
					}
				}
			});
		}
		
		return vierButton;
	}
	
	private JButton getProbepktButton() {
		if (probepktButton == null) {
			probepktButton = new JButton("Probepunkte");
			probepktButton.setToolTipText("nur die Probenahmepunkte anzeigen");
			probepktButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ListSelectionModel lsm = getStandortTabelle()
							.getSelectionModel();
					if (!lsm.isSelectionEmpty()) {
						int selectedRow = lsm.getMinSelectionIndex();
						BasisStandort standort = standortModel
								.getRow(selectedRow);
						AUIKataster
								.debugOutput("Standort " + standort
										+ " angewählt.",
										"BasisStandortSuchen.updateObjekte");
						searchObjekteByStandort(standort, 32);
					}
				}
			});
		}
		
		return probepktButton;
	}
	
	private JButton getReportListeButton(){
	if(reportStandortListeButton == null){
		
		reportStandortListeButton = new JButton("PDF-Liste generieren");
		reportStandortListeButton.setToolTipText("Liste der VAwS-Objekte am Standort"); 
		reportStandortListeButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				try {
					showReportListe();
				} catch (EngineException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}		
		//}
	});
	}
	return reportStandortListeButton ;
	}
	
	
	private JTable getStandortTabelle() {
		if (standortTabelle == null) {
			standortTabelle = new JTable(standortModel);
			
			// Wir wollen wissen, wenn eine andere Zeile ausgewählt wurde
			ListSelectionModel rowSM = standortTabelle.getSelectionModel();
			rowSM.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					// überzählige Events ignorieren
					if (e.getValueIsAdjusting()) {
						return;
					}
					
					updateObjekte();
				}
			});
			
			standortTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			TableColumn column = null;
//			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < standortModel.getColumnCount(); i++) {
				column = standortTabelle.getColumnModel().getColumn(i);
				/*if (i == 0) {
					column.setMaxWidth(60);
					column.setPreferredWidth(column.getMaxWidth()-10);
				} else*/
				if (i == 0) {
					column.setPreferredWidth(120);
					//column.setCellRenderer(centerRenderer);
				} else if (i == 1) {
					//column.setMaxWidth(70);
					column.setPreferredWidth(80);
				} else if (i == 2) {
					//column.setMaxWidth(70);
					column.setPreferredWidth(200);
				}
			}
			
			standortTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			standortTabelle.setColumnSelectionAllowed(false);
			standortTabelle.setRowSelectionAllowed(true);
			
			standortTabelle.addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent e) {
					Point origin = e.getPoint();
					int row = standortTabelle.rowAtPoint(origin);
					
					if (row != -1) {
						if((e.getButton() == MouseEvent.BUTTON1) && (e.getClickCount() == 2)) {	
							BasisStandort bsta = standortModel.getRow(row);
							editStandort(bsta);
						}
					}
				}
				
				public void mousePressed(MouseEvent e) {
					showStandortPopup(e);
				}
				
				public void mouseReleased(MouseEvent e) {
					showStandortPopup(e);
				}
			});
			
	        standortTabelle.getInputMap().put((KeyStroke)getStandortEditAction().getValue(Action.ACCELERATOR_KEY), getStandortEditAction().getValue(Action.NAME));
			standortTabelle.getActionMap().put(getStandortEditAction().getValue(Action.NAME), getStandortEditAction());
		}
		
		return standortTabelle;
	}
	
	private Action getStandortEditAction() {
		if (standortEditAction == null) {
			standortEditAction = new AbstractAction("Bearbeiten") {
				public void actionPerformed(ActionEvent e) {
					int row = standortTabelle.getSelectedRow();
					
					// Natürlich nur editieren, wenn wirklich eine Zeile ausgewählt ist
					if (row != -1) {
						BasisStandort bsta = standortModel.getRow(row);
						editStandort(bsta);
					}
				}
			};
			standortEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
			standortEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
		}
		
		return standortEditAction;
	}
	
	private Action getObjektNeuAction() {
		if (objektNeuAction == null) {
			objektNeuAction = new AbstractAction("Neues Objekt") {
				public void actionPerformed(ActionEvent e) {
					int row = standortTabelle.getSelectedRow();
					
					if (row != -1) {
						BasisStandort bsta = standortModel.getRow(row);
						manager.getSettingsManager().setSetting("auik.imc.use_standort", bsta.getStandortid().intValue(), false);
						manager.switchModul("m_objekt_bearbeiten");
					}
				}
			};
			objektNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_O));
		}
		
		return objektNeuAction;
	}
	
	private void showStandortPopup(MouseEvent e) {
		if (standortPopup == null) {
			standortPopup = new JPopupMenu("Standort");
			JMenuItem bearbItem = new JMenuItem(getStandortEditAction());
			JMenuItem neuItem = new JMenuItem(getObjektNeuAction());
			JMenuItem gisItem = new JMenuItem(getGisAction());
			standortPopup.add(bearbItem);
			standortPopup.add(neuItem);
			standortPopup.add(gisItem);
		}
		
		if (e.isPopupTrigger()) {
			Point origin = e.getPoint();
			int row = standortTabelle.rowAtPoint(origin);
			
			if (row != -1) {
				standortTabelle.setRowSelectionInterval(row, row);
				standortPopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
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
	
	class StreamGobbler extends Thread {
	    InputStream is;

	    // reads everything from is until empty. 
	    StreamGobbler(InputStream is) {
	        this.is = is;
	    }

	    public void run() {
	        try {
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String line=null;
	            while ( (line = br.readLine()) != null)
	                System.out.println(line);    
	        } catch (IOException ioe) {
	            ioe.printStackTrace();  
	        }
	    }
	}
	
	private Action getGisAction()   {
		
		if (gisAction == null) {	
			
			gisAction = new AbstractAction("GIS öffnen") {
			
				
				public void actionPerformed(ActionEvent e) {
						
					String prog = manager.getSettingsManager().getSetting("auik.gis.programmpath");
					String proj = manager.getSettingsManager().getSetting("auik.gis.projectpath");
					
					int row = standortTabelle.getSelectedRow();
					BasisStandort bsta = standortModel.getRow(row);
				
					ProcessBuilder pb = new ProcessBuilder(prog, proj);
					
					Map<String, String> env = pb.environment(); 
					env.put( "RECHTS", bsta.getRechtswert().toString() ); 
					env.put( "HOCH", bsta.getHochwert().toString() ); 
					
					try{
					
						Process process = pb.start();	
						StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream());
						StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream());
						errorGobbler.start();
						outputGobbler.start();

						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				};	
			};
		}
		 
		return gisAction;
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
	
	private JTable getObjektTabelle() {
		if (objektTabelle == null) {
			objektTabelle = new JTable(objektModel);
			objektTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			TableColumn column = objektTabelle.getColumnModel().getColumn(0);
			column.setMaxWidth(60);
			column.setPreferredWidth(column.getMaxWidth()-10);
			
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
}
