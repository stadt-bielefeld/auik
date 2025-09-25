/**
 * Copyright 2005-2011, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */

/*
 * Datei:
 * $Id: SielhautBearbeiten.java,v 1.1.2.1 2010-11-23 10:25:55 u633d Exp $
 *
 * Erstellt am 14.06.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2009/12/17 11:39:46  u633d
 * Verh�ltnis zum Hintergrundwert
 *
 * Revision 1.8  2009/12/11 07:44:16  u633d
 * AuswertungSielhaut
 *
 * Revision 1.7  2009/12/01 14:42:35  u633d
 * SielhautBearbeiten Gis Funktion
 *
 * Revision 1.6  2009/11/12 06:24:45  u633d
 * *** empty log message ***
 *
 * Revision 1.5  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.4  2008/09/01 07:03:46  u633d
 * *** empty log message ***
 *
 * Revision 1.2  2008/06/24 11:24:08  u633d
 * Version 0.3
 *
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
 * - Neues SielhautBearbeiten-Modul angefangen
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.atl.Sielhaut;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.SielhautProbeModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.RetractablePanel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.charts.APosDataItem;
import de.bielefeld.umweltamt.aui.utils.charts.ChartDataSets;
import de.bielefeld.umweltamt.aui.utils.charts.Charts;
import de.bielefeld.umweltamt.aui.utils.PDFExporter;

/**
 * Ein Modul zum Anzeigen und Bearbeiten von SielhautBearbeiten-Punkten.
 *
 * @author David Klotz
 */
public class SielhautBearbeiten extends ObjectModule {
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private JTextField punktFeld;
	private JToolBar punktToolBar;
	private JButton punktChooseButton;
	private JButton punktEditButton;
	private JButton punktPrintButton;
	private JButton punktNeuButton;
	private JButton punktSaveButton;

	private JPanel datenPanel;
	private RetractablePanel probenRtPanel;
	private RetractablePanel fotoRtPanel;
	private RetractablePanel kartenRtPanel;
	private JPanel auswertungPanel;
	private JPanel neuProbPanel;

	// Widgets für Datenpanel
	private JTextField spNamenFeld;
	private JTextField spEntgebFeld;
	private JTextField spLageFeld;
	private JTextArea spBemerkungsArea;
	private DoubleField spE32Feld;
	private DoubleField spN32Feld;
	private JTextField spHaltungsnrFeld;
	private JTextField spAlarmplannrFeld;
	private JButton ausAblageButton;
	private JCheckBox spSielhautCheck;
	private JCheckBox spNachprobeCheck;
	private JCheckBox spFirmenprobeCheck;

	// Widgets für Probenpanel
	private JTable prTabelle;
	private JTextField prNummerFeld;
	private JDateChooser prDateChooser;
	private JButton prAnlegenButton;
	private JButton tabelleExportButton;

	private JPopupMenu probePopup;
	private Action probeEditAction;
	private Action probeLoeschAction;

	// Widgets für Fotopanel
	private JLabel fotoLabel;
	// private ImageIcon fotoBild;

	// Widgets für Kartenpanel
	private JLabel kartenLabel;
	// private ImageIcon kartenBild;

	private Sielhaut spunkt;
	private Messstelle sprobePkt;
	private Objekt objekt;
	private Inhaber betreiber;
	private Standort standort;
	private Objektarten art;
	private SielhautProbeModel probeModel;

	// Auswertung
	private JDateChooser vonDateChooser;
	private JDateChooser bisDateChooser;
	private JCheckBox BleiCheck;
	private JCheckBox CadmiumCheck;
	private JCheckBox ChromCheck;
	private JCheckBox KupferCheck;
	private JCheckBox NickelCheck;
	private JCheckBox QuecksilberCheck;
	private JCheckBox ZinkCheck;
	private JButton submitButton;
	private TimeSeriesCollection dataSet1;

	@Override
	public void show() {
		super.show();

		if (this.manager.getSettingsManager().getSetting("auik.imc.edit_object") != null) {
			this.objekt = Objekt
					.findById(new Integer(this.manager.getSettingsManager().getIntSetting("auik.imc.edit_object")));
			this.manager.getSettingsManager().removeSetting("auik.imc.edit_object");
			this.sprobePkt = Messstelle.findByObjektId(this.objekt.getId());
			this.spunkt = Sielhaut.findById(this.objekt.getId());
			setSielhautPunkt(this.spunkt);
		}

//		else {
//			List<Sielhaut> list1 = DatabaseQuery.findSielhaut("101AP");
//			if (list1.isEmpty() != true) {
//				sprobePkt = list1.get(0).getMessstelle();
//				spunkt = list1.get(0);
//				objekt = sprobePkt.getObjekt();
//				setSielhautPunkt(this.spunkt);
//			}
//
//		}
	}

	public void setSielhautPunkt(Sielhaut sp) {
		this.spunkt = sp;
		if (spunkt.getId() != null) {
			sprobePkt = spunkt.getMessstelle();
			objekt = sprobePkt.getObjekt();
			getPrAnlegenButton().setEnabled(true);
			getTabelleExportButton().setEnabled(true);
		} else {
			objekt = new Objekt();
			standort = new Standort();
			betreiber = Inhaber.findById(DatabaseConstants.BASIS_BETREIBER_ID_Umweltamt_360x33);
			art = Objektarten.findById(DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE);
			objekt.setBetreiberid(this.betreiber);
			objekt.setStandortid(this.standort);
			objekt.setObjektarten(this.art);
			objekt.setInaktiv(false);
			objekt.setAbwasserfrei(true);
			sprobePkt = new Messstelle();
			sprobePkt.setProbeart(Probeart.findById(DatabaseConstants.ATL_PROBEART_ID_SIELHAUT));
			getPrAnlegenButton().setEnabled(false);
			getTabelleExportButton().setEnabled(false);

			getFotoLabel().setIcon(null);
			getFotoLabel().setText("<html><b>- Kein Foto verfügbar! -</b></html>");
			getKartenLabel().setIcon(null);
			getKartenLabel().setText("<html><b>- Keine Karte verfügbar! -</b></html>");
		}

		String titel = spunkt.getBezeichnung();
		if (spunkt.getLage() != null) {
			titel += " \"" + spunkt.getLage() + "\"";
		}
		getPunktFeld().setText(titel);

		getSpNamenFeld().setText(spunkt.getBezeichnung());
		getSpEntgebFeld().setText(spunkt.getEntgeb());
		getSpLageFeld().setText(spunkt.getLage());

		getSpBemerkungsArea().setText(spunkt.getBemerkungen());

		if (spunkt.getId() != null) {
			getSpE32Feld().setValue(spunkt.getMessstelle().getObjekt().getStandortid().getE32());
		}else {
			getSpE32Feld().setValue(0);
		}

		if (spunkt.getId() != null) {
			getSpN32Feld().setValue(spunkt.getMessstelle().getObjekt().getStandortid().getN32());
		}else {
			getSpN32Feld().setValue(0);
		}

		getSpHaltungsnrFeld().setText(spunkt.getHaltungsnr());
		getSpAlarmplannrFeld().setText(spunkt.getAlarmplannr());

		if (spunkt.getPSielhaut() == null) {
			getSpSielhautCheck().setSelected(false);
		} else
			getSpSielhautCheck().setSelected(spunkt.getPSielhaut());

		if (spunkt.getPNachprobe() == null) {
			getSpNachprobeCheck().setSelected(false);
		} else
			getSpNachprobeCheck().setSelected(spunkt.getPNachprobe());

		if (spunkt.getPFirmenprobe() == null) {
			getSpFirmenprobeCheck().setSelected(false);
		} else
			getSpFirmenprobeCheck().setSelected(spunkt.getPFirmenprobe());

		this.probeModel.setMessstelle(this.sprobePkt);

		// Ist eins der einklappbaren Panels offen,
		// wird es (noch einmal) aufgeklappt, um
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
		getPunktEditButton().setEnabled(true);
		punktPrintButton.setEnabled(true);
		setDirty(false);
	}

	/**
	 * Legt einen neuen Sielhaut-Punkt an.
	 */
	public void neuerSielhautPunkt() {
		Sielhaut neuerPunkt = new Sielhaut();
		neuerPunkt.setBezeichnung("Neuer Sielhaut-Punkt");
		setSielhautPunkt(neuerPunkt);

	}

	/**
	 * Speichert ein neu angelegtes Probenahmepunkt-Objekt.
	 */
	public boolean saveObjekt() {
		boolean saved = false;

		this.objekt = Objekt.merge(this.objekt);

		saved = true;

		return saved;
	}

	/**
	 * Speichert einen neu angelegten Probenahmepunkt.
	 */
	public boolean saveNeuenProbepunkt() {
		boolean saved = false;

		if (standort.getId() == null) {
			standort.setE32(spE32Feld.getFloatValue());
			standort.setN32(spN32Feld.getFloatValue());
			standort.setBezeichnung("Sielhautmessstelle");
			standort = Standort.merge(standort);
			objekt.setStandortid(standort);
		}
		if (objekt.getId() == null) {
			objekt.setElkarelevant(false);
			objekt = Objekt.merge(objekt);
			sprobePkt.setObjekt(this.objekt);
		}
		sprobePkt = Messstelle.merge(sprobePkt);
		spunkt.setMessstelle(sprobePkt);

		saved = true;

		return saved;
	}

	/**
	 * Speichert den aktuellen SielhautBearbeiten-Punkt.
	 */
	protected void doSave() {
		// Nur Speichern, wenn der Name nicht leer ist
		if (getSpNamenFeld().getText() == null || getSpNamenFeld().getText().equals("")) {
			GUIManager.getInstance().showErrorMessage("Der Name darf nicht leer sein!");
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
			if (spunkt.getId() != null) {
				spunkt.getMessstelle().getObjekt().getStandortid().setE32(getSpE32Feld().getFloatValue());
				spunkt.getMessstelle().getObjekt().getStandortid().setN32(getSpN32Feld().getFloatValue());
			}
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

			// SielhautBearbeiten, Nachprobe & Alarmplan
			spunkt.setPSielhaut(getSpSielhautCheck().isSelected());
			spunkt.setPNachprobe(getSpNachprobeCheck().isSelected());
			spunkt.setPFirmenprobe(getSpFirmenprobeCheck().isSelected());

			spunkt.setMessstelle(sprobePkt);

			Standort std = spunkt.getMessstelle().getObjekt().getStandortid();
			std = Standort.merge(std);
			spunkt = Sielhaut.merge(spunkt);

			if (spunkt != null) {
				spunkt = Sielhaut.findById(spunkt.getId());

				frame.changeStatus("Sielhaut-Messpunkt erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
				setSielhautPunkt(spunkt);
			}
		}

	}

	public void showReport() {
		if (spunkt.getId() != null) {
			SettingsManager sm = SettingsManager.getInstance();
			String fotoPath = sm.getSetting("auik.system.spath_fotos");
			String mapPath = sm.getSetting("auik.system.spath_karten");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", spunkt.getId());

			String bezeichnung = spunkt.getBezeichnung();
			if (bezeichnung != null && new File(fotoPath + bezeichnung + ".jpg").canRead()) {
				params.put("foto", new String(fotoPath + bezeichnung + ".jpg"));
			} else {
				params.put("foto", new String(fotoPath + "kein_foto.jpg"));
			}

			if (bezeichnung != null && new File(mapPath + bezeichnung + ".jpg").canRead()) {
				params.put("karte", new String(mapPath + bezeichnung + ".jpg"));
			} else {
				params.put("karte", new String(mapPath + "keine_karte.jpg"));
			}
			try {
				File pdfFile = File.createTempFile(bezeichnung, ".pdf");
				pdfFile.deleteOnExit();
				PDFExporter.getInstance().exportReport(params, PDFExporter.SIELHAUT_BEARBEITEN,
						pdfFile.getAbsolutePath());
			} catch (Exception ex) {
				GUIManager.getInstance().showErrorMessage(
						"PDF generieren fehlgeschlagen." + "\n" + ex.getLocalizedMessage(),
						"PDF generieren fehlgeschlagen");
			}
		} else {
			log.debug("Dem zu druckenden Sielhaut-Probenahmepunkt fehlen Eingaben!");
		}
	}

	/**
	 * Legt eine neue Probenahme an.
	 */
	public void neueProbenahme() {
		if (this.sprobePkt != null) {
			if (getPrNummerFeld().getText().trim().equals("")) {
				getPrNummerFeld().requestFocus();
				this.frame.changeStatus("Leere Kennummer!", HauptFrame.ERROR_COLOR);
			} else {
				String kennNummer = getPrNummerFeld().getText().trim().replaceAll(" ", "");
				Timestamp datum = (new Timestamp(getPrDateChooser().getDate().getTime()));

				boolean exists = DatabaseQuery.probenahmeExists(kennNummer);

				if (!exists) {
					Probenahme probe = new Probenahme();
					probe.setKennummer(kennNummer);
					probe.setDatumDerEntnahme((Timestamp) datum);
					probe.setAnalysepositions(new HashSet<Analyseposition>());
					probe.setMessstelle(this.sprobePkt);
					probe.setArt("Sielhaut");

					ProbenEditor editDialog = new ProbenEditor(probe, this.frame, true);
					editDialog.setVisible(true);

					// probeModel.updateList();
					updateProbeListe();
				} else {
					this.frame.changeStatus("Eine Probenahme mit dieser Kennnummer existiert schon!",
							HauptFrame.ERROR_COLOR);
				}
			}
		} else {
			this.frame.changeStatus("Fehler beim Anlegen: Kein Probepunkt!", HauptFrame.ERROR_COLOR);
		}
	}

	/**
	 * Bearbeitet eine Probenahme.
	 */
	public void editProbenahme(Probenahme probe) {
		ProbenEditor editDialog = new ProbenEditor(probe, this.frame, false);

		editDialog.setVisible(true);

		// lastProbe = probe;
		if (editDialog.wasSaved()) {
			updateProbeListe();
		}
		// probeModel.updateList();
	}

	/**
	 * Speichert eine ProbenTabelle.
	 */
	public void saveTabelle() {
		File exportDatei = getFrame().saveFile(new String[] { "csv" });
		if (exportDatei != null) {
			String ext = AuikUtils.getExtension(exportDatei);

			if (ext == null) {
				String newExt;
				if (exportDatei.getName().endsWith(".")) {
					newExt = "csv";
				} else {
					newExt = ".csv";
				}
				exportDatei = new File(exportDatei.getParent(), exportDatei.getName() + newExt);
			}

			boolean doIt = false;
			if (exportDatei.exists()) {
				boolean answer = GUIManager.getInstance().showQuestion(
						"Soll die vorhandene Datei " + exportDatei.getName() + " wirklich überschrieben werden?",
						"Datei bereits vorhanden!");
				if (answer && exportDatei.canWrite()) {
					doIt = true;
				}
			} else if (exportDatei.getParentFile().canWrite()) {
				doIt = true;
			}

			if (doIt) {
				log.debug("Speichere nach '" + exportDatei.getName() + "' (Ext: '" + ext + "') in '"
						+ exportDatei.getParent() + "' !");
				if (AuikUtils.exportTableDataToCVS(getPrTabelle(), exportDatei)) {
					log.debug("Speichern erfolgreich!");
				} else {
					log.debug("Fehler beim Speichern!");
					GUIManager.getInstance()
							.showErrorMessage("Beim Speichern der Datei '" + exportDatei + "' trat ein Fehler auf!");
				}
			}
		}
	}

	private void updateProbeListe() {
		SwingWorkerVariant worker = new SwingWorkerVariant(getPrTabelle()) {
			@Override
			protected void doNonUILogic() throws RuntimeException {
				SielhautBearbeiten.this.probeModel.updateList();
				log.debug("Liste geupdatet!");
			}

			@Override
			protected void doUIUpdateLogic() throws RuntimeException {
				SielhautBearbeiten.this.probeModel.fireTableDataChanged();
				log.debug("Tabelle geupdatet!");
			}
		};
		worker.start();
	}

	private Action getProbeEditAction() {
		if (probeEditAction == null) {
			probeEditAction = new AbstractAction("Bearbeiten") {
				private static final long serialVersionUID = -4363530282004004696L;

				@Override
				public void actionPerformed(ActionEvent e) {
					int row = getPrTabelle().getSelectedRow();
					// Natürlich nur editieren, wenn wirklich eine Zeile
					// ausgewählt ist
					if (row != -1) {
						Probenahme probe = SielhautBearbeiten.this.probeModel.getRow(row);
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
				private static final long serialVersionUID = -3208582919995701684L;

				@Override
				public void actionPerformed(ActionEvent e) {
					int row = getPrTabelle().getSelectedRow();
					if (row != -1 && getPrTabelle().getEditingRow() == -1) {
						Probenahme probe = SielhautBearbeiten.this.probeModel.getRow(row);
						if (GUIManager.getInstance().showQuestion("Soll die Probenahme '" + probe.getKennummer()
								+ "' wirklich inkl. aller Analysen gelöscht werden?", "Löschen bestätigen")) {
							if (SielhautBearbeiten.this.probeModel.removeRow(row)) {
								SielhautBearbeiten.this.frame.changeStatus("Probenahme gelöscht!",
										HauptFrame.SUCCESS_COLOR);
								log.debug("Probe " + probe.getKennummer() + " wurde gelöscht!");
							} else {
								SielhautBearbeiten.this.frame.changeStatus("Konnte Probenahme nicht löschen!",
										HauptFrame.ERROR_COLOR);
							}
						}
					}
				}
			};
			probeLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
			probeLoeschAction.putValue(Action.ACCELERATOR_KEY,
					KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
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

	/*
	 * (non-Javadoc)
	 *
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	@Override
	public String getName() {
		return "Sielhautpunkte";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "m_sielhaut1";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	@Override
	public String getCategory() {
		return "Sielhaut";
	}

	public HauptFrame getFrame() {
		return frame;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	@Override
	public JPanel getPanel() {
		if (this.panel == null) {
			probeModel = new SielhautProbeModel();

			RetractablePanel datenRP = new RetractablePanel(
					DefaultComponentFactory.getInstance().createSeparator("Stammdaten"), getDatenPanel(), true, null);

			FormLayout layout = new FormLayout("pref, 5dlu, 100dlu:g, 3dlu, l:p",
					"p, 3dlu, t:p, 10dlu, t:p, 10dlu, t:p, 10dlu, t:p");
			PanelBuilder builder = new PanelBuilder(layout);
			CellConstraints cc = new CellConstraints();

			builder.addLabel("Messstelle:", cc.xy(1, 1));
			builder.add(getPunktFeld(), cc.xy(3, 1));
			builder.add(getPunktToolBar(), cc.xy(5, 1));
			builder.add(datenRP, cc.xyw(1, 3, 5, "f, f"));
			builder.add(getProbenRtPanel(), cc.xyw(1, 5, 5, "f, f"));
			builder.add(getFotoRtPanel(), cc.xyw(1, 7, 5, "f, f"));
			builder.add(getKartenRtPanel(), cc.xyw(1, 9, 5, "f, f"));

			panel = builder.getPanel();
			panel.setBorder(Paddings.DIALOG);
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
			punktToolBar.add(getPunktEditButton());
			punktToolBar.add(getPunktNeuButton());
			punktToolBar.add(getPunktSaveButton());
			punktToolBar.add(getPunktPrintButton());
		}
		return punktToolBar;
	}

	private JButton getPunktChooseButton() {
		if (punktChooseButton == null) {
			punktChooseButton = new JButton(AuikUtils.getIcon(16, "reload.png"));
			// punktChooseButton.setHorizontalAlignment(JButton.CENTER);
			punktChooseButton.setToolTipText("Messpunkt auswählen");

			punktChooseButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SielhautChooser chooser = new SielhautChooser(SielhautBearbeiten.this.frame);
					chooser.setVisible(true);

					Sielhaut tmp = chooser.getChosenSielhaut();

					if (tmp != null) {
						setSielhautPunkt(tmp);
					}
				}
			});
		}
		return punktChooseButton;
	}

	private JButton getPunktEditButton() {
		if (punktEditButton == null) {
			punktEditButton = new JButton(AuikUtils.getIcon(16, "edit.png"));
			punktEditButton.setToolTipText("Bearbeiten");
			punktEditButton.setEnabled(false);

			punktEditButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SielhautBearbeiten.this.manager.getSettingsManager().setSetting("auik.imc.edit_object",
							SielhautBearbeiten.this.spunkt.getMessstelle().getObjekt().getId().intValue(), false);
					SielhautBearbeiten.this.manager.switchModul("m_objekt_bearbeiten");
				}
			});
		}
		return punktEditButton;
	}

	private JButton getPunktPrintButton() {
		if (punktPrintButton == null) {
			punktPrintButton = new JButton(AuikUtils.getIcon(16, "fileprint.png"));
			// punktChooseButton.setHorizontalAlignment(JButton.CENTER);
			punktPrintButton.setToolTipText("Drucken");
			punktPrintButton.setEnabled(false);

			punktPrintButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						showReport();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					}
				}
			});
		}
		return punktPrintButton;
	}

	private JButton getPunktNeuButton() {
		if (punktNeuButton == null) {
			punktNeuButton = new JButton(AuikUtils.getIcon(16, "filenew.png"));
			// punktNeuButton.setHorizontalAlignment(JButton.CENTER);
			punktNeuButton.setToolTipText("Neuer Messpunkt");
			punktNeuButton.addActionListener(new ActionListener() {
				@Override
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
			// punktSaveButton.setHorizontalAlignment(JButton.CENTER);
			punktSaveButton.setToolTipText("Speichern");
			punktSaveButton.setEnabled(false);
			punktSaveButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (spunkt.getMessstelle() == null) {
						saveNeuenProbepunkt();
					}

					save();

				}
			});
		}
		return this.punktSaveButton;
	}

	// Daten
	private JPanel getDatenPanel() {
		if (this.datenPanel == null) {
			FormLayout layout = new FormLayout("r:p, 3dlu, 150dlu, 10dlu, 70dlu, 10dlu, 100dlu", "pref, " + // 1
					"3dlu, " + // 2
					"pref, " + // 3
					"3dlu, " + // 4
					"pref, " + // 5
					"3dlu, " + // 6
					"fill:30dlu, " + // 7
					"10dlu, " + // 8
					"pref, " + // 9
					"3dlu, " + // 10
					"pref, " + // 11
					"3dlu, " + // 12
					"pref, " + // 13
					"3dlu, " + // 14
					"pref, " // 15
			);
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			CellConstraints cc = new CellConstraints();
			JScrollPane bemerkungsScroller = new JScrollPane(getSpBemerkungsArea(),
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			builder.addLabel("<html><b>Name:</b></html>", cc.xy(1, 1));
			builder.add(getSpNamenFeld(), cc.xy(3, 1));
			builder.addLabel("Entwässerungsgebiet:", cc.xy(1, 3));
			builder.add(getSpEntgebFeld(), cc.xy(3, 3));
			builder.addLabel("Lage:", cc.xy(1, 5));
			builder.add(getSpLageFeld(), cc.xy(3, 5));
			builder.addLabel("Bemerkungen:", cc.xy(1, 7));
			builder.add(bemerkungsScroller, cc.xyw(3, 7, 5));
			builder.addLabel("E32:", cc.xy(1, 9));
			builder.add(getSpE32Feld(), cc.xy(3, 9));
			builder.add(getAusAblageButton(), cc.xywh(5, 9, 1, 3));
			builder.add(getSpSielhautCheck(), cc.xy(7, 9));
			builder.addLabel("N32:", cc.xy(1, 11));
			builder.add(getSpN32Feld(), cc.xy(3, 11));
			builder.add(getSpFirmenprobeCheck(), cc.xy(7, 11));
			builder.addLabel("Schacht-Nr.:", cc.xy(1, 13));
			builder.add(getSpHaltungsnrFeld(), cc.xyw(3, 13, 3));
			builder.add(getSpNachprobeCheck(), cc.xy(7, 13));
			builder.addLabel("Alarmplan-Nr.:", cc.xy(1, 15));
			builder.add(getSpAlarmplannrFeld(), cc.xyw(3, 15, 3));

			// builder.getPanel().setBackground(Color.WHITE);
			// builder.getPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.datenPanel = builder.getPanel();
			this.datenPanel.setBorder(Paddings.DIALOG);

			//Add change listeners
			addChangeListeners(getSpNamenFeld(), getSpEntgebFeld(),
				getSpBemerkungsArea(), getSpLageFeld(), getSpE32Feld(),
				getSpN32Feld(), getSpHaltungsnrFeld(), getSpFirmenprobeCheck(),
				getSpNachprobeCheck(), getSpSielhautCheck(), getSpAlarmplannrFeld());
		}
		return this.datenPanel;
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
		return this.spEntgebFeld;
	}

	private JTextField getSpHaltungsnrFeld() {
		if (spHaltungsnrFeld == null) {
			spHaltungsnrFeld = new LimitedTextField(50);
		}
		return spHaltungsnrFeld;
	}

	private DoubleField getSpN32Feld() {
		if (spN32Feld == null) {
			spN32Feld = new DoubleField(0, 0);
		}
		return spN32Feld;
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

	private JCheckBox getSpFirmenprobeCheck() {
		if (spFirmenprobeCheck == null) {
			spFirmenprobeCheck = new JCheckBox("Firmenprobe");
		}
		return spFirmenprobeCheck;
	}

	private JTextField getSpNamenFeld() {
		if (spNamenFeld == null) {
			spNamenFeld = new LimitedTextField(50);
		}
		return spNamenFeld;
	}

	private DoubleField getSpE32Feld() {
		if (spE32Feld == null) {
			spE32Feld = new DoubleField(0, 0);
		}
		return spE32Feld;
	}

	private JCheckBox getSpSielhautCheck() {
		if (spSielhautCheck == null) {
			spSielhautCheck = new JCheckBox("Routinekontrolle");
		}
		return spSielhautCheck;
	}

	// Proben
	private RetractablePanel getProbenRtPanel() {
		if (this.probenRtPanel == null) {
			FormLayout layout = new FormLayout("p:g");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.appendRow("f:65dlu:g");
			builder.append(new JScrollPane(getPrTabelle()));

			builder.appendSeparator("Neue Probenahme");
			builder.append(getNeuProbPanel());
			// builder.append("Kennummer:", getPrNummerFeld());
			// builder.append("Datum:", getPrDateChooser());
			// builder.append(getPrAnlegenButton());
			// builder.append(getTabelleExportButton());
			builder.nextLine();
			builder.appendSeparator("Auswertung");
			builder.append(getAuswertungPanel());

			JPanel probenPanel = builder.getPanel();
			probenPanel.setBorder(Paddings.DIALOG);
			this.probenRtPanel = new RetractablePanel(
					DefaultComponentFactory.getInstance().createSeparator("Probenahmen"), probenPanel, false, null) {
				private static final long serialVersionUID = -6231371376662899465L;

				@Override
				public void opening() {
					SwingWorkerVariant worker = new SwingWorkerVariant(getSpNamenFeld()) {
						@Override
						protected void doNonUILogic() throws RuntimeException {
							SielhautBearbeiten.this.probeModel.updateList();
						}

						@Override
						protected void doUIUpdateLogic() throws RuntimeException {
							SielhautBearbeiten.this.probeModel.fireTableDataChanged();

						}
					};
					worker.start();
				}
			};
		}
		return this.probenRtPanel;
	}

	private JPanel getNeuProbPanel() {
		if (this.neuProbPanel == null) {
			FormLayout layout = new FormLayout(
					"pref, 5dlu,  pref, 5dlu, pref, 5dlu,  pref, 5dlu, pref, 5dlu,pref, 5dlu, pref, 5dlu,pref, 5dlu,pref, 5dlu,  pref, 5dlu, pref, 5dlu,  "
							+ "pref, 5dlu, pref, 5dlu,pref,pref, 5dlu, pref, 5dlu,pref",

					"pref, 3dlu" + ", " + "pref");
			CellConstraints cc = new CellConstraints();
			CellConstraints cc2 = (CellConstraints) cc.clone();

			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.add(new JLabel("Kennummer:"), cc.xy(1, 1), getPrNummerFeld(), cc2.xyw(3, 1, 20));
			builder.add(new JLabel("Datum:"), cc.xy(25, 1), getPrDateChooser(), cc2.xy(28, 1));

			builder.add(getPrAnlegenButton(), cc.xy(30, 1));
			builder.add(getTabelleExportButton(), cc.xy(32, 1));

			this.neuProbPanel = builder.getPanel();

		}
		return this.neuProbPanel;
	}

	private JPanel getAuswertungPanel() {
		if (this.auswertungPanel == null) {
			FormLayout layout = new FormLayout(
					"pref, 5dlu,  pref, 5dlu, pref, 5dlu,  pref, 5dlu, pref, 5dlu,pref, 5dlu, pref, 5dlu,pref, 5dlu,pref, 5dlu,  pref, 5dlu, pref, 5dlu,  "
							+ "pref, 5dlu, pref, 5dlu,pref",

					"pref, 3dlu" + ", " + "pref");
			CellConstraints cc = new CellConstraints();
			CellConstraints cc2 = (CellConstraints) cc.clone();

			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			builder.add(new JLabel("Von:"), cc.xy(1, 1), getVonDateChooser(), cc2.xy(3, 1));
			builder.add(new JLabel("Bis:"), cc.xy(5, 1), getBisDateChooser(), cc2.xy(7, 1));

			builder.add(getBleiCheck(), cc.xy(11, 1));
			builder.add(getCadmiumCheck(), cc.xy(13, 1));
			builder.add(getChromCheck(), cc.xy(15, 1));
			builder.add(getKupferCheck(), cc.xy(17, 1));
			builder.add(getNickelCheck(), cc.xy(19, 1));
			builder.add(getQuecksilberCheck(), cc.xy(21, 1));
			builder.add(getZinkCheck(), cc.xy(23, 1));
			builder.add(getSubmitButton(), cc.xy(27, 1));

			this.auswertungPanel = builder.getPanel();
			this.auswertungPanel.setBorder(Paddings.DIALOG);
		}
		return this.auswertungPanel;
	}

	private JTable getPrTabelle() {
		if (prTabelle == null) {
			prTabelle = new JTable(this.probeModel);
			prTabelle.getColumnModel().getColumn(0).setWidth(10);

			prTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			prTabelle.getInputMap().put((KeyStroke) getProbeEditAction().getValue(Action.ACCELERATOR_KEY),
					getProbeEditAction().getValue(Action.NAME));
			prTabelle.getActionMap().put(getProbeEditAction().getValue(Action.NAME), getProbeEditAction());

			prTabelle.getInputMap().put((KeyStroke) getProbeLoeschAction().getValue(Action.ACCELERATOR_KEY),
					getProbeLoeschAction().getValue(Action.NAME));
			prTabelle.getActionMap().put(getProbeLoeschAction().getValue(Action.NAME), getProbeLoeschAction());

			prTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = getPrTabelle().rowAtPoint(origin);

						Probenahme probe = SielhautBearbeiten.this.probeModel.getRow(row);
						editProbenahme(probe);
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					showProbePopup(e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					showProbePopup(e);
				}
			});
		}
		return prTabelle;
	}

	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton("Abschicken");

			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showResultOneAxis();
				}
			});
		}

		return submitButton;
	}

	private class AuswertungsDialog extends JDialog {
		private static final long serialVersionUID = 3892351392140673333L;

		/**
		 * Ein Listener für die Events des Dialogs.
		 *
		 * @author David Klotz
		 */
		private class DialogListener extends WindowAdapter implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == AuswertungsDialog.this.abbrechenButton) {
					doAbbrechen();
				} else if (e.getSource() == AuswertungsDialog.this.speichernButton) {
					doSpeichern();
				}
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// Wenn der Dialog geschlossen wird, wird das Bearbeiten
				// abgebrochen
				SielhautBearbeiten.this.frame.clearStatus();
				doAbbrechen();

			}
		}

		/**
		 * Ein Tablemodel
		 *
		 * @author David Klotz
		 */
		private class ExportTableModel extends AbstractTableModel {
			private static final long serialVersionUID = -2296763811705380082L;
			private TimeSeriesCollection col1, col2;
			private List<Minute> dateList;

			public ExportTableModel(TimeSeriesCollection col1, TimeSeriesCollection col2) {
				this.col1 = col1;
				this.col2 = col2;
				dateList = new ArrayList<Minute>();

				initializeData();
			}

			private boolean istVorhanden(List<Minute> liste, Minute neu) {
				boolean x = false;
				ArrayList<Minute> array = (ArrayList<Minute>) liste;
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i).getDay().hashCode() == neu.getDay().hashCode()) {
						x = true;
					}
				}
				return x;
			}

			private void initializeData() {
				TimeSeries series;
				APosDataItem item;

				for (int i = 0; i < this.col1.getSeriesCount(); i++) {
					series = this.col1.getSeries(i);
					for (int j = 0; j < series.getItemCount(); j++) {
						item = (APosDataItem) series.getDataItem(j);
						Minute v = item.getMinute();
						if (!istVorhanden(this.dateList, v)) {
							this.dateList.add(item.getMinute());
						}
					}
				}

				if (this.col2 != null) {
					for (int i = 0; i < this.col2.getSeriesCount(); i++) {
						series = this.col2.getSeries(i);
						for (int j = 0; j < series.getItemCount(); j++) {
							item = (APosDataItem) series.getDataItem(j);
							// count++;
							if (!this.dateList.contains(item.getMinute())) {
								this.dateList.add(item.getMinute());
							}
						}
					}
				}

				Collections.sort(this.dateList);
			}

			@Override
			public int getColumnCount() {
				return col1.getSeriesCount() + ((col2 != null) ? col2.getSeriesCount() : 0) + 1;// 2;
			}

			@Override
			public int getRowCount() {
				return dateList.size();// + 1;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				String tmp = "!OOB!";

				NumberFormat kommaFormat = NumberFormat.getNumberInstance();
				kommaFormat.setGroupingUsed(false);
				kommaFormat.setMinimumFractionDigits(1);

				int seriesIndex = columnIndex - 1;
				int series2Index = seriesIndex - this.col1.getSeriesCount();
				int itemIndex = rowIndex;// - 1;

				Minute min = this.dateList.get(itemIndex);
				if (columnIndex == 0) {
					Date date = new Date(min.getFirstMillisecond());
					tmp = AuikUtils.getStringFromDate(date);
				} else {
					APosDataItem item = null;
					if (seriesIndex < this.col1.getSeriesCount()) {
						item = (APosDataItem) this.col1.getSeries(seriesIndex).getDataItem(min);
					} else if (this.col2 != null) {
						item = (APosDataItem) this.col2.getSeries(series2Index).getDataItem(min);
					}
					if (item != null) {
						tmp = kommaFormat.format(item.getValue());
					} else {
						tmp = "";
					}
				}

				return tmp;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			@Override
			public String getColumnName(int column) {
				String tmp = "!OOB!";

				int seriesIndex = column - 1;
				int series2Index = seriesIndex - this.col1.getSeriesCount();

				if (column == 0) {
					tmp = "Datum";
				} else {
					if (seriesIndex < this.col1.getSeriesCount()) {
						// TODO: key == seriesName?
						// tmp = this.col1.getSeriesName(seriesIndex)
						tmp = this.col1.getSeries(seriesIndex).getKey().toString() + ", "
								+ this.col1.getSeries(seriesIndex).getRangeDescription();
					} else if (this.col2 != null) {
						// tmp = this.col2.getSeriesName(series2Index)
						tmp = this.col2.getSeries(series2Index).getKey().toString() + ", "
								+ this.col2.getSeries(series2Index).getRangeDescription();
					}
				}

				return tmp;
			}
		}

		private JButton speichernButton;
		private JButton abbrechenButton;

		private JTable exportTable;
		private JPopupMenu tabellenMenu;

		private JTabbedPane tabbedPane;
		private ChartPanel chartPanel;

		private DialogListener listener;
		private String title;

		private TimeSeriesCollection leftDataset;
		private TimeSeriesCollection rightDataset;
		private HauptFrame owner;

		public AuswertungsDialog(String title, TimeSeriesCollection leftDataset, TimeSeriesCollection rightDataset,
				HauptFrame owner) {
			super(owner, title + "-Auswertung", true);
			this.owner = owner;
			this.title = title;

			this.leftDataset = leftDataset;
			this.rightDataset = rightDataset;

			listener = new DialogListener();

			setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			addWindowListener(listener);

			speichernButton = new JButton("Speichern");
			speichernButton.addActionListener(listener);
			abbrechenButton = new JButton("Schließen");
			abbrechenButton.addActionListener(listener);

			JPanel tmp = new JPanel(new BorderLayout(0, 7));

			tmp.add(initializeContent(), BorderLayout.CENTER);
			JComponent buttonBar = ComponentFactory.buildOKCancelBar(this.speichernButton, this.abbrechenButton);
			tmp.add(buttonBar, BorderLayout.SOUTH);
			tmp.setBorder(Paddings.TABBED_DIALOG);

			this.setContentPane(tmp);
			this.pack();
			this.setLocationRelativeTo(SielhautBearbeiten.this.frame);
		}

		private JComponent initializeContent() {
			tabbedPane = new JTabbedPane();

			tabbedPane.addTab("Diagramm", createDiagrammPanel());
			tabbedPane.addTab("Tabelle", createTabellenPanel());

			return tabbedPane;
		}

		private JPanel createDiagrammPanel() {
			JFreeChart chart;
			if (rightDataset == null) {
				chart = Charts.createDefaultTimeSeriesChart(title, leftDataset);
			} else {
				chart = Charts.createDefaultTimeSeriesChart(title, leftDataset, rightDataset);
			}

			chartPanel = new ChartPanel(chart, false);
			chartPanel.setBorder(Paddings.DIALOG);

			return this.chartPanel;
		}

		private JComponent createTabellenPanel() {
			exportTable = new JTable(new ExportTableModel(leftDataset, rightDataset));
			exportTable.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			exportTable.setColumnSelectionAllowed(true);
			exportTable.setRowSelectionAllowed(true);
			exportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			this.exportTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					showTabellenPopup(e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					showTabellenPopup(e);
				}
			});

			DefaultTableCellRenderer zentrierterRenderer = new DefaultTableCellRenderer();
			zentrierterRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

			DefaultTableCellRenderer rechtsBuendigRenderer = new DefaultTableCellRenderer();
			rechtsBuendigRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

			TableColumn column = null;
			for (int i = 0; i < exportTable.getColumnCount(); i++) {
				column = exportTable.getColumnModel().getColumn(i);
				if (i == 0) {// || i == 1) {
					column.setCellRenderer(zentrierterRenderer);
					column.setPreferredWidth(75);
				} else {
					column.setCellRenderer(rechtsBuendigRenderer);
					column.setPreferredWidth(90);
				}
			}

			JScrollPane tabellenScroller = new JScrollPane(exportTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			tabellenScroller.setBorder(Paddings.DIALOG);

			return tabellenScroller;
		}

		public void saveTabelle() {
			SielhautBearbeiten.this.frame.clearStatus();
			File exportDatei;
			String[] csv = new String[] { "csv" };

			exportDatei = owner.saveFile(csv);

			if (exportDatei != null) {
				String ext = AuikUtils.getExtension(exportDatei);

				if (ext == null) {
					String newExt;
					if (exportDatei.getName().endsWith(".")) {
						newExt = "csv";
					} else {
						newExt = ".csv";
					}
					exportDatei = new File(exportDatei.getParent(), exportDatei.getName() + newExt);
				}

				boolean doIt = false;
				if (exportDatei.exists()) {
					boolean answer = GUIManager.getInstance().showQuestion(
							"Soll die vorhandene Datei " + exportDatei.getName() + " wirklich überschrieben werden?",
							"Datei bereits vorhanden!");
					if (answer && exportDatei.canWrite()) {
						doIt = true;
					}
				} else if (exportDatei.getParentFile().canWrite()) {
					doIt = true;
				}

				if (doIt) {
					log.debug("Speichere nach '" + exportDatei.getName() + "' (Ext: '" + ext + "') in '"
							+ exportDatei.getParent() + "' !");
					if (AuikUtils.exportTableDataToCVS(this.exportTable, exportDatei)) {
						GUIManager.getInstance().showInfoMessage("Speichern der CSV-Datei erfolgreich!",
								"Speichern erfolgreich");
					} else {
						log.debug("Beim Speichern der Datei '" + exportDatei + "' trat ein Fehler auf!");
						GUIManager.getInstance().showErrorMessage(
								"Beim Speichern der Datei '" + exportDatei + "' trat ein Fehler auf!");
					}
				}
			}
		}

		private void showTabellenPopup(MouseEvent e) {
			if (tabellenMenu == null) {
				tabellenMenu = new JPopupMenu("Tabelle");
				JMenuItem speichernItem = new JMenuItem(new AbstractAction("Speichern") {
					private static final long serialVersionUID = 2096747421254651035L;

					@Override
					public void actionPerformed(ActionEvent e) {
						saveTabelle();
					}
				});
				tabellenMenu.add(speichernItem);
			}

			if (e.isPopupTrigger()) {
				Point origin = e.getPoint();
				int row = exportTable.rowAtPoint(origin);
				int col = exportTable.columnAtPoint(origin);

				if (row != -1) {
					exportTable.setRowSelectionInterval(row, row);
					exportTable.setColumnSelectionInterval(col, col);
					tabellenMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}

		public void doAbbrechen() {
			SielhautBearbeiten.this.frame.clearStatus();
			dispose();
		}

		public void doSpeichern() {
			SielhautBearbeiten.this.frame.clearStatus();
			if (tabbedPane.getSelectedIndex() == 0) {
				try {
					chartPanel.doSaveAs();
				} catch (IOException e) {
					log.debug("Konnte Datei nicht speichern!");
				}
			} else if (tabbedPane.getSelectedIndex() == 1) {
				saveTabelle();
			}
		}
	}

	public void showResultOneAxis() {
		SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton()) {
			@Override
			protected void doNonUILogic() throws RuntimeException {
				SielhautBearbeiten.this.dataSet1 = createDataset();
			}

			@Override
			protected void doUIUpdateLogic() throws RuntimeException {

				if (SielhautBearbeiten.this.dataSet1.getSeriesCount() > 0) {

					AuswertungsDialog dialog = new AuswertungsDialog(SielhautBearbeiten.this.getPunktFeld().getText(),
							SielhautBearbeiten.this.dataSet1, null, SielhautBearbeiten.this.frame);

					dialog.setVisible(true);
				} else {
					SielhautBearbeiten.this.frame.changeStatus("Keine Parameter ausgewählt!");
				}
			}

		};

		this.frame.changeStatus("Bereite Auswertung vor...");
		worker.start();

	}

	private TimeSeriesCollection createDataset() {
		TimeSeriesCollection col = new TimeSeriesCollection();
		Date von = getVonDateChooser().getDate();
		Date bis = getBisDateChooser().getDate();
		List<Parameter> selectedParams = new ArrayList<Parameter>();

		if (getBleiCheck().isSelected()) {
			selectedParams.add(Parameter.findById(DatabaseConstants.ATL_PARAMETER_ID_BLEI));
		}
		if (getCadmiumCheck().isSelected()) {
			selectedParams.add(Parameter.findById(DatabaseConstants.ATL_PARAMETER_ID_CADMIUM));
		}
		if (getChromCheck().isSelected()) {
			selectedParams.add(Parameter.findById(DatabaseConstants.ATL_PARAMETER_ID_CHROM));
		}
		if (getKupferCheck().isSelected()) {
			selectedParams.add(Parameter.findById(DatabaseConstants.ATL_PARAMETER_ID_KUPFER));
		}
		if (getNickelCheck().isSelected()) {
			selectedParams.add(Parameter.findById(DatabaseConstants.ATL_PARAMETER_ID_NICKEL));
		}
		if (getQuecksilberCheck().isSelected()) {
			selectedParams.add(Parameter.findById(DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER));
		}
		if (getZinkCheck().isSelected()) {
			selectedParams.add(Parameter.findById(DatabaseConstants.ATL_PARAMETER_ID_ZINK));
		}

		// Wenn keine Check Box angeklickt wurde sollen alle Paramater
		// berücksichtig werden
		if (selectedParams.isEmpty()) {
			String[] paramIDs = { DatabaseConstants.ATL_PARAMETER_ID_BLEI, DatabaseConstants.ATL_PARAMETER_ID_CADMIUM,
					DatabaseConstants.ATL_PARAMETER_ID_CHROM, DatabaseConstants.ATL_PARAMETER_ID_KUPFER,
					DatabaseConstants.ATL_PARAMETER_ID_NICKEL, DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER,
					DatabaseConstants.ATL_PARAMETER_ID_ZINK };
			for (String paramID : paramIDs) {
				selectedParams.add(Parameter.findById(paramID));
			}
		}

		createSeries(selectedParams, this.sprobePkt, von, bis, col);
		selectedParams.clear();

		return col;
	}

	private void createSeries(List<Parameter> params, Messstelle pkt, Date von, Date bis, TimeSeriesCollection col) {
		if (pkt != null) {
			for (Parameter param : params) {
				List<Analyseposition> list = DatabaseQuery.getAnalysepositionen(param, pkt, von, bis);

				TimeSeries series = ChartDataSets.createAnalysePositionenSielhautSeries(list,
						param.getBezeichnung() + " ", "Verhältnis zum Hintergrundwert");
				col.addSeries(series);
			}
		}
		this.frame.changeStatus("Auswertung abgeschlossen");
	}

	private JDateChooser getVonDateChooser() {
		if (vonDateChooser == null) {
			vonDateChooser = new JDateChooser(null, DateUtils.FORMAT_DEFAULT);
		}

		return vonDateChooser;
	}

	private JDateChooser getBisDateChooser() {

		if (bisDateChooser == null) {
			bisDateChooser = new JDateChooser(new Date(), DateUtils.FORMAT_DEFAULT);
		}

		return bisDateChooser;
	}

	private JCheckBox getBleiCheck() {
		if (BleiCheck == null) {
			BleiCheck = new JCheckBox("Blei", false);
		}
		return BleiCheck;
	}

	private JCheckBox getCadmiumCheck() {
		if (CadmiumCheck == null) {
			CadmiumCheck = new JCheckBox("Cadmium", false);
		}
		return CadmiumCheck;
	}

	private JCheckBox getChromCheck() {
		if (ChromCheck == null) {
			ChromCheck = new JCheckBox("Chrom", false);
		}
		return ChromCheck;
	}

	private JCheckBox getKupferCheck() {
		if (KupferCheck == null) {
			KupferCheck = new JCheckBox("Kupfer", false);
		}
		return KupferCheck;
	}

	private JCheckBox getNickelCheck() {
		if (NickelCheck == null) {
			NickelCheck = new JCheckBox("Nickel", false);
		}
		return NickelCheck;
	}

	private JCheckBox getQuecksilberCheck() {
		if (QuecksilberCheck == null) {
			QuecksilberCheck = new JCheckBox("Quecksilber", false);
		}
		return QuecksilberCheck;
	}

	private JCheckBox getZinkCheck() {
		if (ZinkCheck == null) {
			ZinkCheck = new JCheckBox("Zink", false);
		}
		return ZinkCheck;
	}

	private JTextField getPrNummerFeld() {
		if (prNummerFeld == null) {
			prNummerFeld = new LimitedTextField(50, "");
		}
		return prNummerFeld;
	}

	private JDateChooser getPrDateChooser() {
		if (prDateChooser == null) {
			prDateChooser = new JDateChooser(new Date(), DateUtils.FORMAT_DEFAULT);
		}
		return prDateChooser;
	}

	private JButton getPrAnlegenButton() {
		if (prAnlegenButton == null) {
			prAnlegenButton = new JButton("Anlegen");
			prAnlegenButton.setEnabled(false);

			prAnlegenButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					neueProbenahme();
				}
			});
		}

		return prAnlegenButton;
	}

	private JButton getTabelleExportButton() {
		if (tabelleExportButton == null) {
			tabelleExportButton = new JButton("Tabelle speichern");
			tabelleExportButton.setEnabled(false);

			tabelleExportButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					saveTabelle();
				}
			});
		}

		return tabelleExportButton;
	}

	// Foto
	private RetractablePanel getFotoRtPanel() {
		if (fotoRtPanel == null) {
			JPanel fotoPanel = new JPanel();

			fotoPanel.add(getFotoLabel());
			fotoPanel.setBackground(Color.WHITE);
			fotoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			fotoRtPanel = new RetractablePanel(DefaultComponentFactory.getInstance().createSeparator("Foto"),
					fotoPanel, false, null) {
				private static final long serialVersionUID = 6505102322099919490L;

				@Override
				public void opening() {
					if (SielhautBearbeiten.this.spunkt != null && SielhautBearbeiten.this.spunkt.getId() != null) {
						String imgPath = SielhautBearbeiten.this.manager.getSettingsManager().getSetting(
								"auik.system.spath_fotos") + SielhautBearbeiten.this.spunkt.getBezeichnung() + ".jpg";
						File imgFile = new File(imgPath);
						if (imgFile.canRead()) {
							ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
							int panelWidth = getPanel().getWidth() - 50;
							if (imgIcon.getIconWidth() > panelWidth) {
								imgIcon.setImage(
										imgIcon.getImage().getScaledInstance(panelWidth, -1, Image.SCALE_FAST));
							}
							getFotoLabel().setIcon(null);
							getFotoLabel().setIcon(imgIcon);
							getFotoLabel().setText(null);
						} else {
							getFotoLabel().setIcon(null);
							getFotoLabel().setText("<html><b>-  Foto " + SielhautBearbeiten.this.spunkt.getBezeichnung()
									+ ".jpg nicht gefunden!  -</b></html>");
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

			this.kartenRtPanel = new RetractablePanel(
					DefaultComponentFactory.getInstance().createSeparator("Kartenausschnitt"), kartenPanel, false,
					null) {
				private static final long serialVersionUID = 1276454146798307743L;

				@Override
				public void opening() {
					if (SielhautBearbeiten.this.spunkt != null && SielhautBearbeiten.this.spunkt.getId() != null) {
						String imgPath = SielhautBearbeiten.this.manager.getSettingsManager().getSetting(
								"auik.system.spath_karten") + SielhautBearbeiten.this.spunkt.getBezeichnung() + ".jpg";
						File imgFile = new File(imgPath);
						if (imgFile.canRead()) {
							ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
							int panelWidth = getPanel().getWidth() - 55;
							if (imgIcon.getIconWidth() > panelWidth) {
								imgIcon.setImage(
										imgIcon.getImage().getScaledInstance(panelWidth, -1, Image.SCALE_FAST));
							}
							getKartenLabel().setIcon(imgIcon);
							getKartenLabel().setText(null);
						} else {
							getKartenLabel().setIcon(null);
							getKartenLabel()
									.setText("<html><b>-  Karte " + SielhautBearbeiten.this.spunkt.getBezeichnung()
											+ ".jpg nicht gefunden!  -</b></html>");
						}
					}
				}
			};
		}
		kartenRtPanel.repaint();
		return kartenRtPanel;
	}

	private JLabel getKartenLabel() {
		if (kartenLabel == null) {
			kartenLabel = new JLabel("<html><b>- Keine Karte verfügbar -</b></html>");
		}

		return kartenLabel;
	}

	private void readClipboard() {

		Clipboard systemClipboard;
		systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferData = systemClipboard.getContents(null);
		for (DataFlavor dataFlavor : transferData.getTransferDataFlavors()) {
			Object content = null;
			try {
				content = transferData.getTransferData(dataFlavor);
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (content instanceof String) {

				String[] tmp = content.toString().split(",");
				if (tmp.length == 4) {
					String e32AusZeile = tmp[2];
					String n32AusZeile = tmp[3];
					spE32Feld.setText(e32AusZeile.substring(0, 7));
					spN32Feld.setText(n32AusZeile.substring(0, 7));
					this.frame.changeStatus("Rechts- und Hochwert eingetragen", HauptFrame.SUCCESS_COLOR);
				} else
					if (tmp.length == 2) {
						String e32AusZeile = tmp[0];
						String n32AusZeile = tmp[1];
						spE32Feld.setText(e32AusZeile.substring(0, 7));
						spN32Feld.setText(n32AusZeile.substring(0, 7));
						this.frame.changeStatus("Rechts- und Hochwert eingetragen", HauptFrame.SUCCESS_COLOR);
				}else
				{
					this.frame.changeStatus("Zwischenablage enthält keine verwertbaren Daten", HauptFrame.ERROR_COLOR);
				}
				break;
			}
		}
	}

	public JButton getAusAblageButton() {
		if (ausAblageButton == null) {

			ausAblageButton = new JButton("aus QGis");
			ausAblageButton.setToolTipText("Rechts- und Hochwert aus Zwischenablage einfügen");
			ausAblageButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					readClipboard();
				}
			});
		}

		return ausAblageButton;
	}
}
