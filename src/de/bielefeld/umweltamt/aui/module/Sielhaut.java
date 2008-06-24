/*
 * Datei:
 * $Id: Sielhaut.java,v 1.2 2008-06-24 11:24:08 u633d Exp $
 * 
 * Erstellt am 14.06.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.9  2005/11/02 13:49:16  u633d
 * - Version vom 2.11.
 *
 * Revision 1.8  2005/09/07 10:48:22  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.7  2005/08/31 06:25:12  u633d
 * - kleine Ergänzungen
 *
 * Revision 1.6  2005/08/25 14:46:59  u633d
 * - zu viel ;)
 *
 * Revision 1.5  2005/08/24 08:42:51  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.4  2005/08/17 05:46:00  u633d
 * - Anhang 56 erstellt
 *
 * Revision 1.3  2005/07/06 09:37:47  u633z
 * - Liste mit Probenahmen
 *
 * Revision 1.2  2005/06/30 11:43:34  u633z
 * - Sielhautdaten können bearbeitet werden
 *
 * Revision 1.1  2005/06/14 13:33:58  u633z
 * - Neues Sielhaut-Modul angefangen
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import org.eclipse.birt.report.engine.api.EngineException;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ReportManager;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlSielhaut;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.RetractablePanel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein Modul zum Anzeigen und Bearbeiten von Sielhaut-Punkten. 
 * @author David Klotz
 */
public class Sielhaut extends AbstractModul {
	private JTextField punktFeld;
	private JToolBar punktToolBar;
	private JButton punktChooseButton;
	private JButton punktPrintButton;
	private JButton punktNeuButton;
	private JButton punktSaveButton;
	
	private JPanel datenPanel;
	private RetractablePanel probenRtPanel;
	private RetractablePanel fotoRtPanel;
	private RetractablePanel kartenRtPanel;
	
	// Widgets für Datenpanel
	private JTextField spNamenFeld;
	private JTextField spEntgebFeld;
	private JTextField spLageFeld;
	private JTextArea spBemerkungsArea;
	private DoubleField spRechtsWertFeld;
	private DoubleField spHochWertFeld;
	private JTextField spHaltungsnrFeld;
	private JTextField spAlarmplannrFeld;
	private JCheckBox spSielhautCheck;
	private JCheckBox spNachprobeCheck;
	private JCheckBox spAlarmplanCheck;
	
	// Widgets für Probenpanel
	private JTable prTabelle;
	private JTextField prNummerFeld;
	private JDateChooser prDateChooser;
	private JButton prAnlegenButton;
	
	private JPopupMenu probePopup;
	private Action probeEditAction;
	private Action probeLoeschAction;
	
	// Widgets für Fotopanel
	private JLabel fotoLabel;
	//private ImageIcon fotoBild;
	
	// Widgets für Kartenpanel
	private JLabel kartenLabel; 
	//private ImageIcon kartenBild;
	
	private AtlSielhaut spunkt;
	private AtlProbepkt sprobePkt;
	private SielhautProbeModel probeModel;
	
	public void setSielhautPunkt(AtlSielhaut sp) {
		spunkt = sp;
		if (spunkt.getId() != null) {
			sprobePkt = AtlProbepkt.getSielhautProbepunkt(spunkt);
			getPrAnlegenButton().setEnabled(true);
		} else {
			sprobePkt = new AtlProbepkt();
			sprobePkt.setAtlProbeart(AtlProbeart.getProbeart(AtlProbeart.SIELHAUT));
			sprobePkt.setAtlSielhaut(spunkt);
			getPrAnlegenButton().setEnabled(false);
			
			getFotoLabel().setIcon(null);
			getFotoLabel().setText("<html><b>- Kein Foto verfügbar! -</b></html>");
			getKartenLabel().setIcon(null);
			getKartenLabel().setText("<html><b>- Keine Karte verfügbar! -</b></html>");
		}
		
		String titel = spunkt.getBezeichnung();
		if (spunkt.getEntgeb() != null) {
			titel += " (" + spunkt.getEntgeb() + ")";
		}
		getPunktFeld().setText(titel);
		
		getSpNamenFeld().setText(spunkt.getBezeichnung());
		getSpEntgebFeld().setText(spunkt.getEntgeb());
		getSpLageFeld().setText(spunkt.getLage());
		
		getSpBemerkungsArea().setText(spunkt.getBemerkungen());
		
		getSpRechtsWertFeld().setValue(spunkt.getRechtswert());
		getSpHochWertFeld().setValue(spunkt.getHochwert());
		
		getSpHaltungsnrFeld().setText(spunkt.getHaltungsnr());
		getSpAlarmplannrFeld().setText(spunkt.getAlarmplannr());
		
		getSpSielhautCheck().setSelected(spunkt.getPsielhaut());
		getSpNachprobeCheck().setSelected(spunkt.getPnachprobe());
		getSpAlarmplanCheck().setSelected(spunkt.getPalarmplan());
		
		probeModel.setProbepunkt(sprobePkt);
		
		// Ist eins der einklappbaren Panels offen,
		// wird es _noch einmal_ aufgeklappt, um 
		// seinen Inhalt aufzufrischen:
		if (getProbenRtPanel().isOpen()) {
			getProbenRtPanel().setOpen(true);
		}
		
		if (getFotoRtPanel().isOpen()) {
			getFotoRtPanel().setOpen(true);
		}
		
		if (getKartenRtPanel().isOpen()) {
			getKartenRtPanel().setOpen(true);
		}
		
		getPunktSaveButton().setEnabled(true);
		punktPrintButton.setEnabled(true);
	}
	
	/**
	 * Legt einen neuen Sielhaut-Punkt an.
	 */
	public void neuerSielhautPunkt() {
		// Einige sinnvolle Default-Werte werdem im Konstruktor gesetzt
		AtlSielhaut neuerPunkt = new AtlSielhaut();
		neuerPunkt.setBezeichnung("Neuer Sielhaut-Punkt");
		setSielhautPunkt(neuerPunkt);
	}
	
	/**
	 * Speichert den aktuellen Sielhaut-Punkt.
	 */
	public void saveSielhautPunkt() {
		// Nur Speichern, wenn der Name nicht leer ist
		if (getSpNamenFeld().getText() == null || getSpNamenFeld().getText().equals("")) {
			frame.showErrorMessage("Der Name darf nicht leer sein!");
			getSpNamenFeld().requestFocus();
		} else {
			// Bezeichnung
			spunkt.setBezeichnung(getSpNamenFeld().getText());
			
			// Entwässerungsgebiet
			if ("".equals(getSpEntgebFeld().getText())) {
				spunkt.setEntgeb(null);
			} else {
				spunkt.setEntgeb(getSpEntgebFeld().getText());
			}
			
			// Lage
			if ("".equals(getSpLageFeld().getText())) {
				spunkt.setLage(null);
			} else {
				spunkt.setLage(getSpLageFeld().getText());
			}
			
			// Bemerkungen
			if ("".equals(getSpBemerkungsArea().getText())) {
				spunkt.setBemerkungen(null);
			} else {
				spunkt.setBemerkungen(getSpBemerkungsArea().getText());
			}
			
			// Rechts- und Hochwert
			spunkt.setRechtswert(getSpRechtsWertFeld().getDoubleValue());
			spunkt.setHochwert(getSpHochWertFeld().getDoubleValue());
			
			// Haltungs-Nr.
			if ("".equals(getSpHaltungsnrFeld().getText())) {
				spunkt.setHaltungsnr(null);
			} else {
				spunkt.setHaltungsnr(getSpHaltungsnrFeld().getText());
			}
			
			// Alarmplan-Nr.
			if ("".equals(getSpAlarmplannrFeld().getText())) {
				spunkt.setAlarmplannr(null);
			} else {
				spunkt.setAlarmplannr(getSpAlarmplannrFeld().getText());
			}
			
			// Sielhaut, Nachprobe & Alarmplan
			spunkt.setPsielhaut(getSpSielhautCheck().isSelected());
			spunkt.setPnachprobe(getSpNachprobeCheck().isSelected());
			spunkt.setPalarmplan(getSpAlarmplanCheck().isSelected());
			
			if (AtlSielhaut.saveSielhautPunkt(spunkt) && AtlProbepkt.saveProbepunkt(sprobePkt)) {
				frame.changeStatus("Sielhaut-Messpunkt erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
				setSielhautPunkt(spunkt);
			} else {
				frame.changeStatus("Sielhaut-Messpunkt konnte nicht gespeichert werden!", HauptFrame.ERROR_COLOR);
			}
		}
	}
	
	public void showReport() throws EngineException {
		if (spunkt.getId() != null || spunkt.getHaltungsnr() != null) {
			ReportManager.getInstance().startReportWorker("Sielhaut", spunkt.getId(), spunkt.getBezeichnung(), punktPrintButton);
		}
		else
		{
			AUIKataster.debugOutput("Dem zu druckenden Sielhaut-Probenahmepunkt fehlen Eingaben!");
		}
	}
	
	/**
	 * Legt eine neue Probenahme an.
	 */
	public void neueProbenahme() {
		if (sprobePkt != null) {
			if (getPrNummerFeld().getText().trim().equals("")) {
				getPrNummerFeld().requestFocus();
				frame.changeStatus("Leere Kennummer!", HauptFrame.ERROR_COLOR);
			} else {
				String kennNummer = getPrNummerFeld().getText().trim().replaceAll(" ", "");
				Date datum = getPrDateChooser().getDate();
				
				boolean exists = AtlProbenahmen.probenahmeExists(kennNummer);
				
				if (!exists) {
					AtlProbenahmen probe = new AtlProbenahmen();
					probe.setKennummer(kennNummer);
					probe.setDatumDerEntnahme(datum);
					probe.setAtlAnalysepositionen(new HashSet());
					probe.setAtlProbepkt(sprobePkt);
					
					ProbenEditor editDialog = new ProbenEditor(probe, frame, true);
					editDialog.setVisible(true);
					
					//probeModel.updateList();
					updateProbeListe();
				} else {
					frame.changeStatus("Eine Probenahme mit dieser Kennnummer existiert schon!", HauptFrame.ERROR_COLOR);
				}
			}
		} else {
			frame.changeStatus("Fehler beim Anlegen: Kein Probepunkt!", HauptFrame.ERROR_COLOR);
		}
	}
	
	/**
	 * Bearbeitet eine Probenahme.
	 */
	public void editProbenahme(AtlProbenahmen probe) {
		ProbenEditor editDialog = new ProbenEditor(probe, frame, false);
		
		editDialog.setVisible(true);
		
		//lastProbe = probe;
		if (editDialog.wasSaved()) {
			updateProbeListe();
		}
		//probeModel.updateList();
	}
	
	private void updateProbeListe() {
		SwingWorkerVariant worker = new SwingWorkerVariant(getPrTabelle()) {
			protected void doNonUILogic() throws RuntimeException {
				probeModel.updateList();
				AUIKataster.debugOutput("Liste geupdatet!");
			}

			protected void doUIUpdateLogic() throws RuntimeException {
				probeModel.fireTableDataChanged();
				AUIKataster.debugOutput("Tabelle geupdatet!");
			}
		};
		worker.start();
	}
	
	private Action getProbeEditAction() {
		if (probeEditAction == null) {
			probeEditAction = new AbstractAction("Bearbeiten") {
				public void actionPerformed(ActionEvent e) {
					int row = getPrTabelle().getSelectedRow();
					// Natürlich nur editieren, wenn wirklich eine Zeile ausgewählt ist
					if (row != -1) {
						AtlProbenahmen probe = probeModel.getRow(row);
						editProbenahme(probe);
					}
				}
			};
			probeEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
			probeEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
		}
		
		return probeEditAction;
	}
	
	private Action getProbeLoeschAction() {
		if (probeLoeschAction == null) {
			probeLoeschAction = new AbstractAction("Löschen") {
				public void actionPerformed(ActionEvent e) {
					int row = getPrTabelle().getSelectedRow();
					if (row != -1 && getPrTabelle().getEditingRow() == -1) {
						AtlProbenahmen probe = probeModel.getRow(row);
						if (frame.showQuestion("Soll die Probenahme '"+ probe.getKennummer() +"' wirklich inkl. aller Analysen gelöscht werden?", "Löschen bestätigen")) {
							if (probeModel.removeRow(row)) {
								frame.changeStatus("Probenahme gelöscht!", HauptFrame.SUCCESS_COLOR);
								AUIKataster.debugOutput("Probe " + probe.getKennummer() + " wurde gelöscht!", "SchlammPanel.removeAction");
							} else {
								frame.changeStatus("Konnte Probenahme nicht löschen!", HauptFrame.ERROR_COLOR);
							}
						}
					}
				}
			};
			probeLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
			probeLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
		}
		
		return probeLoeschAction;
	}
	
	private void showProbePopup(MouseEvent e) {
		if (probePopup == null) {
			probePopup = new JPopupMenu("Probe");
			JMenuItem bearbItem = new JMenuItem(getProbeEditAction());
			JMenuItem loeschItem = new JMenuItem(getProbeLoeschAction());
			probePopup.add(bearbItem);
			probePopup.add(loeschItem);
		}
		
		if (e.isPopupTrigger()) {
			Point origin = e.getPoint();
			int row = getPrTabelle().rowAtPoint(origin);
			
			if (row != -1) {
				getPrTabelle().setRowSelectionInterval(row, row);
				probePopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Sielhautpunkte";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	public String getIdentifier() {
		return "m_sielhaut1";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	public String getCategory() {
		return "Sielhaut";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	public JPanel getPanel() {
		if (panel == null) {
			probeModel = new SielhautProbeModel();
			
			RetractablePanel datenRP = new RetractablePanel(
					DefaultComponentFactory.getInstance()
					.createSeparator("Stammdaten"), 
					getDatenPanel(), true, null);
			
			
			
			
			FormLayout layout = new FormLayout(
					"pref, 5dlu, 100dlu:g, 3dlu, l:p",
					"p, 3dlu, t:p, 10dlu, t:p, 10dlu, t:p, 10dlu, t:p");
			PanelBuilder builder = new PanelBuilder(layout);
			CellConstraints cc = new CellConstraints();
			
			builder.setDefaultDialogBorder();
			
			builder.addLabel("Messstelle:",	cc.xy(1,1));
			builder.add(getPunktFeld(),		cc.xy(3,1));
			builder.add(getPunktToolBar(),	cc.xy(5,1));
			builder.add(datenRP,			cc.xyw(1,3,5, "f, f"));
			builder.add(getProbenRtPanel(),	cc.xyw(1,5,5, "f, f"));
			builder.add(getFotoRtPanel(),	cc.xyw(1,7,5, "f, f"));
			builder.add(getKartenRtPanel(),	cc.xyw(1,9,5, "f, f"));
			
			panel = builder.getPanel();
		}
		
		return panel;
	}
	
	private JTextField getPunktFeld() {
		if (punktFeld == null) {
			punktFeld = new JTextField();
			punktFeld.setEditable(false);
		}
		return punktFeld;
	}
	
	private JToolBar getPunktToolBar() {
		if (punktToolBar == null) {
			punktToolBar = new JToolBar();
			punktToolBar.setFloatable(false);
			punktToolBar.setRollover(true);
			
			punktToolBar.add(getPunktChooseButton());
			punktToolBar.add(getPunktPrintButton());
			punktToolBar.add(getPunktNeuButton());
			punktToolBar.add(getPunktSaveButton());
		}
		return punktToolBar;
	}
	
	private JButton getPunktChooseButton() {
		if (punktChooseButton == null) {
			punktChooseButton = new JButton(AuikUtils.getIcon(16, "reload.png"));
			//punktChooseButton.setHorizontalAlignment(JButton.CENTER);
			punktChooseButton.setToolTipText("Messpunkt auswählen");
			
			punktChooseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SielhautChooser chooser = new SielhautChooser(frame);
					chooser.setVisible(true);
					
					AtlSielhaut tmp = chooser.getChosenSielhaut();
					
					if (tmp != null) {
						setSielhautPunkt(tmp);
					}
				}
			});
		}
		return punktChooseButton;
	}
	
	private JButton getPunktPrintButton() {
		if (punktPrintButton == null) {
			punktPrintButton = new JButton(AuikUtils.getIcon(16, "fileprint.png"));
			//punktChooseButton.setHorizontalAlignment(JButton.CENTER);
			punktPrintButton.setToolTipText("Drucken");
			punktPrintButton.setEnabled(false);
			
			punktPrintButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						showReport();
					} catch (EngineException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return punktPrintButton;
	}
	
	private JButton getPunktNeuButton() {
		if (punktNeuButton == null) {
			punktNeuButton = new JButton(AuikUtils.getIcon(16, "filenew.png"));
			//punktNeuButton.setHorizontalAlignment(JButton.CENTER);
			punktNeuButton.setToolTipText("Neuer Messpunkt");
			punktNeuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					neuerSielhautPunkt();
				}
			});
		}
		return punktNeuButton;
	}
	
	private JButton getPunktSaveButton() {
		if (punktSaveButton == null) {
			punktSaveButton = new JButton(AuikUtils.getIcon(16, "filesave.png"));
			//punktSaveButton.setHorizontalAlignment(JButton.CENTER);
			punktSaveButton.setToolTipText("Speichern");
			punktSaveButton.setEnabled(false);
			punktSaveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (spunkt != null) {
						saveSielhautPunkt();
					}
				}
			});
		}
		return punktSaveButton;
	}
	
	// Daten
	private JPanel getDatenPanel() {
		if (datenPanel == null) {
			FormLayout layout = new FormLayout(
					"r:p, 3dlu, p:g, 10dlu, p:g"
			);
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.append("<html><b>Name:</b></html>", getSpNamenFeld());
			builder.nextLine();
			builder.append("Entwässerungsgebiet:", getSpEntgebFeld());
			builder.nextLine();
			
			builder.append("Lage:", getSpLageFeld(), 3);
			
			builder.appendRelatedComponentsGapRow();
			builder.appendRow("f:30dlu");
			builder.nextLine(2);
			builder.append("Bemerkungen:", new JScrollPane(
					getSpBemerkungsArea()), 3);
			builder.appendUnrelatedComponentsGapRow();
			builder.nextLine(2);
			
			builder.append("Rechtswert:", getSpRechtsWertFeld());
			builder.nextLine();
			builder.append("Hochwert:", getSpHochWertFeld(), getSpSielhautCheck());
			builder.nextLine();
			
			builder.append("Haltungs-Nr.:", getSpHaltungsnrFeld(), getSpNachprobeCheck());
			builder.nextLine();
			
			builder.append("Alarmplan-Nr.:", getSpAlarmplannrFeld(), getSpAlarmplanCheck());
			builder.nextLine();
			
			//builder.getPanel().setBackground(Color.WHITE);
			//builder.getPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			datenPanel = builder.getPanel();
		}
		return datenPanel;
	}
	
	private JCheckBox getSpAlarmplanCheck() {
		if (spAlarmplanCheck == null) {
			spAlarmplanCheck = new JCheckBox("Alarmplan");
		}
		return spAlarmplanCheck;
	}
	private JTextField getSpAlarmplannrFeld() {
		if (spAlarmplannrFeld == null) {
			spAlarmplannrFeld = new LimitedTextField(50);
		}
		return spAlarmplannrFeld;
	}
	private JTextArea getSpBemerkungsArea() {
		if (spBemerkungsArea == null) {
			spBemerkungsArea = new LimitedTextArea(255);
			spBemerkungsArea.setLineWrap(true);
			spBemerkungsArea.setWrapStyleWord(true);
		}
		return spBemerkungsArea;
	}
	private JTextField getSpEntgebFeld() {
		if (spEntgebFeld == null) {
			spEntgebFeld = new LimitedTextField(50);
		}
		return spEntgebFeld;
	}
	private JTextField getSpHaltungsnrFeld() {
		if (spHaltungsnrFeld == null) {
			spHaltungsnrFeld = new LimitedTextField(50);
		}
		return spHaltungsnrFeld;
	}
	private DoubleField getSpHochWertFeld() {
		if (spHochWertFeld == null) {
			spHochWertFeld = new DoubleField(1);
		}
		return spHochWertFeld;
	}
	private JTextField getSpLageFeld() {
		if (spLageFeld == null) {
			spLageFeld = new LimitedTextField(50);
		}
		return spLageFeld;
	}
	private JCheckBox getSpNachprobeCheck() {
		if (spNachprobeCheck == null) {
			spNachprobeCheck = new JCheckBox("Nachprobe");
		}
		return spNachprobeCheck;
	}
	private JTextField getSpNamenFeld() {
		if (spNamenFeld == null) {
			spNamenFeld = new LimitedTextField(50);
		}
		return spNamenFeld;
	}
	private DoubleField getSpRechtsWertFeld() {
		if (spRechtsWertFeld == null) {
			spRechtsWertFeld = new DoubleField(1);
		}
		return spRechtsWertFeld;
	}
	private JCheckBox getSpSielhautCheck() {
		if (spSielhautCheck == null) {
			spSielhautCheck = new JCheckBox("Sielhaut");
		}
		return spSielhautCheck;
	}
	
	// Proben
	private RetractablePanel getProbenRtPanel() {
		if (probenRtPanel == null) {
			FormLayout layout = new FormLayout(
					"p, 4dlu, p:g, 7dlu, p, 4dlu, max(60dlu;p), 7dlu, max(60dlu;p)"
			);
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.setDefaultDialogBorder();
			
			builder.appendRow("f:65dlu:g");
			builder.append(new JScrollPane(getPrTabelle()), 9);
			builder.appendSeparator("Neue Probenahme");
			builder.append("Kennummer:", getPrNummerFeld());
			builder.append("Datum:", getPrDateChooser());
			builder.append(getPrAnlegenButton());
			
			JPanel probenPanel = builder.getPanel();
			probenRtPanel = new RetractablePanel(
					DefaultComponentFactory.getInstance()
					.createSeparator("Probenahmen"), 
					probenPanel, false, null) {
				
				public void opening() {
					SwingWorkerVariant worker = new SwingWorkerVariant(getSpNamenFeld()) {
						protected void doNonUILogic() throws RuntimeException {
							probeModel.updateList();
						}
						
						protected void doUIUpdateLogic() throws RuntimeException {
							probeModel.fireTableDataChanged();
							
						}
					};
					worker.start();
				}
			};
		}
		return probenRtPanel;
	}
	
	private JTable getPrTabelle() {
		if (prTabelle == null) {
			prTabelle = new JTable(probeModel);
			prTabelle.getColumnModel().getColumn(0).setWidth(40);
			prTabelle.getColumnModel().getColumn(1).setWidth(75);
			
			prTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			prTabelle.getInputMap().put((KeyStroke)getProbeEditAction().getValue(Action.ACCELERATOR_KEY), getProbeEditAction().getValue(Action.NAME));
			prTabelle.getActionMap().put(getProbeEditAction().getValue(Action.NAME), getProbeEditAction());
			
			prTabelle.getInputMap().put((KeyStroke)getProbeLoeschAction().getValue(Action.ACCELERATOR_KEY), getProbeLoeschAction().getValue(Action.NAME));
			prTabelle.getActionMap().put(getProbeLoeschAction().getValue(Action.NAME), getProbeLoeschAction());
			
			prTabelle.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = getPrTabelle().rowAtPoint(origin);
						
						AtlProbenahmen probe = probeModel.getRow(row);
						editProbenahme(probe);
					}
				}
				
				public void mousePressed(MouseEvent e) {
					showProbePopup(e);
				}
				
				public void mouseReleased(MouseEvent e) {
					showProbePopup(e);
				}
			});
		}
		return prTabelle;
	}
	
	private JTextField getPrNummerFeld() {
		if (prNummerFeld == null) {
			prNummerFeld = new LimitedTextField(50, "");
		}
		return prNummerFeld;
	}
	
	private JDateChooser getPrDateChooser() {
		if (prDateChooser == null) {
			prDateChooser = new JDateChooser(AuikUtils.DATUMSFORMAT, false);
		}
		return prDateChooser;
	}
	
	private JButton getPrAnlegenButton() {
		if (prAnlegenButton == null) {
			prAnlegenButton = new JButton("Anlegen");
			prAnlegenButton.setEnabled(false);
			
			prAnlegenButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					neueProbenahme();
				}
			});
		}
		
		return prAnlegenButton;
	}
	
	// Foto
	private RetractablePanel getFotoRtPanel() {
		if (fotoRtPanel == null) {
			JPanel fotoPanel = new JPanel();
			
			fotoPanel.add(getFotoLabel());
			fotoPanel.setBackground(Color.WHITE);
			fotoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			fotoRtPanel = new RetractablePanel(
					DefaultComponentFactory.getInstance()
					.createSeparator("Foto"), 
					fotoPanel, false, null) {
				public void opening() {
					if (spunkt != null && spunkt.getId() != null) {
						String imgPath = manager.getSettingsManager().getSetting("auik.system.spath_fotos") + spunkt.getBezeichnung() + ".jpg";
						File imgFile = new File(imgPath);
						if (imgFile.canRead()) {
							ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
							int panelWidth = getPanel().getWidth() - 55;
							if (imgIcon.getIconWidth() > panelWidth) {
								imgIcon.setImage(imgIcon.getImage().getScaledInstance(panelWidth,-1,Image.SCALE_FAST));
							}
							getFotoLabel().setIcon(imgIcon);
							getFotoLabel().setText(null);
						} else {
							getFotoLabel().setIcon(null);
							getFotoLabel().setText("<html><b>-  Foto "+spunkt.getBezeichnung()+".jpg nicht gefunden!  -</b></html>");
						}
					}
				}
			};
		}
		return fotoRtPanel;
	}
	
	private JLabel getFotoLabel() {
		if (fotoLabel == null) {
			fotoLabel = new JLabel("<html><b>- Kein Foto verfügbar! -</b></html>");
		}
		
		return fotoLabel;
	}
	
	// Kartenausschnitt
	private RetractablePanel getKartenRtPanel() {
		if (kartenRtPanel == null) {
			JPanel kartenPanel = new JPanel();
			kartenPanel.add(getKartenLabel());
			kartenPanel.setBackground(Color.WHITE);
			kartenPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			kartenRtPanel = new RetractablePanel(
					DefaultComponentFactory.getInstance()
					.createSeparator("Kartenausschnitt"), 
					kartenPanel, false, null) {
				public void opening() {
					if (spunkt != null && spunkt.getId() != null) {
						String imgPath = manager.getSettingsManager().getSetting("auik.system.spath_karten") + spunkt.getBezeichnung() + ".jpg";
						File imgFile = new File(imgPath);
						if (imgFile.canRead()) {
							ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
							int panelWidth = getPanel().getWidth() - 55;
							if (imgIcon.getIconWidth() > panelWidth) {
								imgIcon.setImage(imgIcon.getImage().getScaledInstance(panelWidth,-1,Image.SCALE_FAST));
							}
							getKartenLabel().setIcon(imgIcon);
							getKartenLabel().setText(null);
						} else {
							getKartenLabel().setIcon(null);
							getKartenLabel().setText("<html><b>-  Karte "+spunkt.getBezeichnung()+".jpg nicht gefunden!  -</b></html>");
						}
					}
				}
			};
		}
		return kartenRtPanel;
	}
	
	private JLabel getKartenLabel() {
		if (kartenLabel == null) {
			kartenLabel = new JLabel("<html><b>- Keine Karte verfügbar -</b></html>");
		}
		
		return kartenLabel;
	}
}

/**
 * Ein TableModel für eine Tabelle mit Probenahmen zu einem Sielhautpunkt.
 * @author David Klotz
 */
class SielhautProbeModel extends ListTableModel {
	private AtlProbepkt probepkt;
	private Map wertMap;
	private AtlParameter[] params;
	
	public SielhautProbeModel() {
		super(new String[]{"Kennnummer", "Datum"}, false, true);
		
		params = new AtlParameter[] {
				AtlParameter.getParameter(AtlParameter.BLEI_ID),
				AtlParameter.getParameter(AtlParameter.CADMIUM_ID),
				AtlParameter.getParameter(AtlParameter.CHROM_ID),
				AtlParameter.getParameter(AtlParameter.KUPFER_ID),
				AtlParameter.getParameter(AtlParameter.NICKEL_ID),
				AtlParameter.getParameter(AtlParameter.QUECKSILBER_ID),
				AtlParameter.getParameter(AtlParameter.ZINK_ID)
		};
		
		columns = new String[params.length + 2];
		columns[0] = "Kennnummer";
		columns[1] = "Datum";
		for (int i = 0; i < params.length; i++) {
			columns[i+2] = params[i].toString();
		}
		
		wertMap = new HashMap();
	}
	
	public void setProbepunkt(AtlProbepkt probepkt) {
		this.probepkt = probepkt;
	}
	
	public void updateList() {
		if (probepkt != null) {
			setList(AtlProbenahmen.getProbenahmen(probepkt, true, 5));
			
			wertMap.clear();
			for (int i = 0; i < getList().size(); i++) {
				AtlProbenahmen probe = getRow(i);
				List wertList = new ArrayList(params.length);
				
				for (int j = 0; j < params.length; j++) {
					AtlParameter param = params[j];
					List posList = AtlAnalyseposition.getAnalysepositionen(probe, param);
					AtlAnalyseposition pos;
					if (posList.size() > 0) {
						pos = (AtlAnalyseposition) posList.get(0);
					} else {
						pos = null;
					}
					wertList.add(j, pos);
				}
				
				wertMap.put(getList().get(i), wertList);
			}
			
			//fireTableDataChanged();
		}
	}
	
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		Object value;
		AtlProbenahmen probe = (AtlProbenahmen) objectAtRow;
		
		if (columnIndex == 0) {
			value = probe.getKennummer();
		} else if (columnIndex == 1) {
			value = AuikUtils.getStringFromDate(probe.getDatumDerEntnahme());
		} else {
			List wertList = (List) wertMap.get(probe);
			AtlAnalyseposition pos = (AtlAnalyseposition) wertList.get(columnIndex-2);
			if (pos != null) {
				value = pos.getWert() + " " + pos.getAtlEinheiten();
			} else {
				value = "-";
			}
		}
		
		return value;
	}
	
	public boolean objectRemoved(Object objectAtRow) {
		AtlProbenahmen removedProbe = (AtlProbenahmen) objectAtRow;
		boolean removed;
		
		if (removedProbe.getKennummer() != null) {
			removed = AtlProbenahmen.removeProbenahme(removedProbe);
		} else {
			removed = true;
		}
		
		return removed;
	}
	
	/**
	 * Liefert das Objekt aus einer bestimmten Zeile.
	 * @param rowIndex Die Zeile
	 * @return Das Objekt bei rowIndex oder <code>null</code>, falls die Zeile nicht existiert
	 */
	public AtlProbenahmen getRow(int rowIndex) {
		return (AtlProbenahmen) getObjectAtRow(rowIndex);
	}
}

class SielhautChooser extends OkCancelDialog {
	private JTextField suchFeld;
	private JButton submitButton;
	private JTable ergebnisTabelle;
	
	private SielhautModel sielhautModel;
	private AtlSielhaut chosenSielhaut = null;
	
	public SielhautChooser(HauptFrame owner) {
		super("Sielhautpunkt auswählen", owner);
		
		sielhautModel = new SielhautModel();
		getErgebnisTabelle().setModel(sielhautModel);
		
		ergebnisTabelle.getColumnModel().getColumn(0).setPreferredWidth(40);
		ergebnisTabelle.getColumnModel().getColumn(1).setPreferredWidth(270);
		ergebnisTabelle.getColumnModel().getColumn(2).setPreferredWidth(10);
		ergebnisTabelle.getColumnModel().getColumn(3).setPreferredWidth(10);
		ergebnisTabelle.getColumnModel().getColumn(4).setPreferredWidth(10);
		
		setResizable(true);
		
		sielhautModel.filterList("");
		sielhautModel.fireTableDataChanged();
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#doOk()
	 */
	protected void doOk() {
		int row = getErgebnisTabelle().getSelectedRow();
		choose(row);
	}
	
	protected void doSearch() {
		final String suche = getSuchFeld().getText();
		
		SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
			protected void doNonUILogic() throws RuntimeException {
				sielhautModel.filterList(suche);
			}

			protected void doUIUpdateLogic() throws RuntimeException {
				sielhautModel.fireTableDataChanged();
			}
		};
		
		worker.start();
	}
	
	private void choose(int row) {
		if (row != -1) {
			chosenSielhaut = (AtlSielhaut) sielhautModel.getObjectAtRow(row);
			dispose();
		}
	}
	
	public AtlSielhaut getChosenSielhaut() {
		return chosenSielhaut;
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea()
	 */
	protected JComponent buildContentArea() {
		JScrollPane tabellenScroller = new JScrollPane(
				getErgebnisTabelle(),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		TabAction ta = new TabAction();
		ta.addComp(ergebnisTabelle);
		ta.addComp(button1);
		//ta.addComp(button2);
		JToolBar submitToolBar = new JToolBar();
		submitToolBar.setFloatable(false);
		submitToolBar.setRollover(true);
		submitToolBar.add(getSubmitButton());
		
		FormLayout layout = new FormLayout(
				"180dlu:g, 3dlu, min(16dlu;p)",		// spalten 
				"20dlu, 3dlu, 300dlu:g"); 	// zeilen
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		builder.add(getSuchFeld(),		cc.xy(1, 1));
		builder.add(submitToolBar,		cc.xy(3, 1));
		builder.add(tabellenScroller,	cc.xyw(1, 3, 3));
		
		return builder.getPanel();
	}
	
	private JTextField getSuchFeld() {
		if (suchFeld == null) {
			suchFeld = new JTextField();
			
			suchFeld.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doSearch();
				}
			});
			
			suchFeld.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					String text = suchFeld.getText();
					AUIKataster.debugOutput("keyChar: " + e.getKeyChar() + ", Text: " + text, "SielhautChooser");
					if (Character.isLetterOrDigit(e.getKeyChar())) {
						text = text + e.getKeyChar();
					}
					sielhautModel.filterList(text);
					sielhautModel.fireTableDataChanged();
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
			ergebnisTabelle = new JTable();

			
			Action submitAction = new AbstractAction("Auswählen") {
				public void actionPerformed(ActionEvent e) {
					doOk();
				}
			};
			submitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
			
			ergebnisTabelle.getInputMap().put((KeyStroke)submitAction.getValue(Action.ACCELERATOR_KEY), submitAction.getValue(Action.NAME));
			ergebnisTabelle.getActionMap().put(submitAction.getValue(Action.NAME), submitAction);
			
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
		}
		
		return ergebnisTabelle;
	}
}

class SielhautModel extends ListTableModel {
	public SielhautModel() {
		super(new String[]{"Bezeichnung", "Lage", "S", "A", "N"}, false);
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
	 */
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
		AtlSielhaut spunkt = (AtlSielhaut) objectAtRow;
		Object tmp;
		
		switch (columnIndex) {
		case 0:
			tmp = spunkt.getBezeichnung();
			break;
		case 1:
			tmp = spunkt.getLage();
			break;
		case 2:
			tmp = new Boolean(spunkt.getPsielhaut());
			break;
		case 3:
			tmp = new Boolean(spunkt.getPalarmplan());
			break;
		case 4:
			tmp = new Boolean(spunkt.getPnachprobe());
			break;

		default:
			tmp = "FEHLER!";
			break;
		}
		
		return tmp;
	}
	
	
	public Class getColumnClass(int columnIndex) {
		if (columnIndex > 1) {
			return Boolean.class;
		} else {
			return super.getColumnClass(columnIndex);
		}
	}
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#updateList()
	 */
	public void updateList() {
	}
	
	public void filterList(String suche) {
		setList(AtlSielhaut.findPunkte(suche));
		AUIKataster.debugOutput("Suche nach '"+suche+"' ("+getList().size()+" Ergebnisse)", "SielhautModel.filterList()");
	}
}
