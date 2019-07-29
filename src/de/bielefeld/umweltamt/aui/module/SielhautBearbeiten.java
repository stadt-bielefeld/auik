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

import static org.junit.Assert.assertTrue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
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
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.AbstractModul;
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
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.Lage;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.SielhautModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.SielhautProbeModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
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
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;

/**
 * Ein Modul zum Anzeigen und Bearbeiten von SielhautBearbeiten-Punkten.
 * @author David Klotz
 */
public class SielhautBearbeiten extends AbstractModul {
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
    private Lage lage;
    private Adresse betreiber;
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

        if (this.manager.getSettingsManager()
            .getSetting("auik.imc.edit_object") != null) {
            this.objekt = Objekt.findById(new Integer(this.manager
                .getSettingsManager().getIntSetting("auik.imc.edit_object")));
            this.manager.getSettingsManager().removeSetting(
                "auik.imc.edit_object");
            this.sprobePkt = Messstelle.findByObjektId(this.objekt.getId());
            this.spunkt = Sielhaut.findByObjektId(this.objekt.getId());
            setSielhautPunkt(this.spunkt);
        }	
        
        else{
        	List<Sielhaut> list1 = Sielhaut.getAll();
        	if(list1.isEmpty() != true){
        		sprobePkt = list1.get(0).getMessstelle();
        		spunkt = list1.get(0);
        		objekt = sprobePkt.getObjekt();
                setSielhautPunkt(this.spunkt);
        	}
        	
        }
        /*
        else if (BasisObjekt.findById(24856) != null) {
            //FIXME: A constant id? In the code? -.-
            this.objekt = BasisObjekt.findById(24856);
            this.sprobePkt = AtlProbepkt.findById(this.objekt.getObjektid());
            this.spunkt = AtlSielhaut.findById(this.objekt.getObjektid());
            setSielhautPunkt(this.spunkt);
        }
		*/
    }
    
    private Objekt getB1(){
    	SielhautModel tmp = new SielhautModel();
    	Sielhaut t1 =(Sielhaut) tmp.getObjectAtRow(0);
    	return t1.getMessstelle().getObjekt();
    }

    public void setSielhautPunkt(Sielhaut sp) {
        this.spunkt = sp;
        if (this.spunkt.getId() != null) {
            this.sprobePkt = this.spunkt.getMessstelle();
            this.objekt = sprobePkt.getObjekt();
            getPrAnlegenButton().setEnabled(true);
            getTabelleExportButton().setEnabled(true);
        } else {
            this.objekt = new Objekt();
            this.lage = new Lage();
            this.betreiber = Adresse.findById(
                DatabaseConstants.BASIS_BETREIBER_ID_Umweltamt_360x33);
            this.standort = Standort.findById(
                DatabaseConstants.BASIS_STANDORT_KEIN_STANDORT);
            this.art = Objektarten.findById(
                DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE);
            this.objekt.setBetreiberid(this.betreiber);
            this.objekt.setStandortid(this.standort);
            this.objekt.setObjektarten(this.art);
            this.objekt.setInaktiv(false);
            this.objekt.setAbwasserfrei(true);
            this.sprobePkt = new Messstelle();
            this.sprobePkt.setProbeart(
                Probeart.findById(
                    DatabaseConstants.ATL_PROBEART_ID_SIELHAUT));
            getPrAnlegenButton().setEnabled(false);
            getTabelleExportButton().setEnabled(false);

            getFotoLabel().setIcon(null);
            getFotoLabel().setText(
                "<html><b>- Kein Foto verfügbar! -</b></html>");
            getKartenLabel().setIcon(null);
            getKartenLabel().setText(
                "<html><b>- Keine Karte verfügbar! -</b></html>");
        }

        String titel = this.spunkt.getBezeichnung();
        if (this.spunkt.getLage() != null) {
            titel += " \"" + this.spunkt.getLage() + "\"";
        }
        getPunktFeld().setText(titel);

        getSpNamenFeld().setText(this.spunkt.getBezeichnung());
        getSpEntgebFeld().setText(this.spunkt.getEntgeb());
        getSpLageFeld().setText(this.spunkt.getLage());

        getSpBemerkungsArea().setText(this.spunkt.getBemerkungen());

        getSpE32Feld().setValue(this.spunkt.getE32());
        getSpN32Feld().setValue(this.spunkt.getN32());

        getSpHaltungsnrFeld().setText(this.spunkt.getHaltungsnr());
        getSpAlarmplannrFeld().setText(this.spunkt.getAlarmplannr());

        if (this.spunkt.getPSielhaut() == null) {
            getSpSielhautCheck().setSelected(false);
        } else
            getSpSielhautCheck().setSelected(this.spunkt.getPSielhaut());

        if (this.spunkt.getPNachprobe() == null) {
            getSpNachprobeCheck().setSelected(false);
        } else
            getSpNachprobeCheck().setSelected(this.spunkt.getPNachprobe());

        if (this.spunkt.getPFirmenprobe() == null) {
            getSpFirmenprobeCheck().setSelected(false);
        } else
            getSpFirmenprobeCheck().setSelected(this.spunkt.getPFirmenprobe());

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
        this.punktPrintButton.setEnabled(true);
    }

    /**
     * Legt einen neuen Sielhaut-Punkt an.
     */
    public void neuerSielhautPunkt() {
        Sielhaut neuerPunkt = new Sielhaut();
        neuerPunkt.setE32(new Double(0.0));
        neuerPunkt.setN32(new Double(0.0));
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
	public boolean saveProbepunkt(Objekt objekt) {
		boolean saved = false;

		// objekt = BasisObjekt.findById(objekt.getObjektid());
		this.sprobePkt.setObjekt(objekt);

		this.objekt.setStandortid(standort);
		this.objekt = Objekt.merge(this.objekt);
		this.sprobePkt.setObjekt(this.objekt);
		this.sprobePkt = Messstelle.merge(this.sprobePkt);

        saved = true;

        return saved;
    }

    /**
     * Speichert den aktuellen SielhautBearbeiten-Punkt.
     */
    public void saveSielhautPunkt() {
        // Nur Speichern, wenn der Name nicht leer ist
        if (getSpNamenFeld().getText() == null
            || getSpNamenFeld().getText().equals("")) {
            GUIManager.getInstance().showErrorMessage(
                "Der Name darf nicht leer sein!");
            getSpNamenFeld().requestFocus();
        } else {
            // Bezeichnung
            this.spunkt.setBezeichnung(getSpNamenFeld().getText());

            // Entwässerungsgebiet
            if ("".equals(getSpEntgebFeld().getText())) {
                this.spunkt.setEntgeb(null);
            } else {
                this.spunkt.setEntgeb(getSpEntgebFeld().getText());
            }

            // Lage
            if ("".equals(getSpLageFeld().getText())) {
                this.spunkt.setLage(null);
            } else {
                this.spunkt.setLage(getSpLageFeld().getText());
            }

            // Bemerkungen
            if ("".equals(getSpBemerkungsArea().getText())) {
                this.spunkt.setBemerkungen(null);
            } else {
                this.spunkt.setBemerkungen(getSpBemerkungsArea().getText());
            }

            // Rechts- und Hochwert
            this.spunkt.setE32(getSpE32Feld().getDoubleValue());
            this.spunkt.setN32(getSpN32Feld().getDoubleValue());

            // Haltungs-Nr.
            if ("".equals(getSpHaltungsnrFeld().getText())) {
                this.spunkt.setHaltungsnr(null);
            } else {
                this.spunkt.setHaltungsnr(getSpHaltungsnrFeld().getText());
            }

            // Alarmplan-Nr.
            if ("".equals(getSpAlarmplannrFeld().getText())) {
                this.spunkt.setAlarmplannr(null);
            } else {
                this.spunkt.setAlarmplannr(getSpAlarmplannrFeld().getText());
            }

            // SielhautBearbeiten, Nachprobe & Alarmplan
            this.spunkt.setPSielhaut(getSpSielhautCheck().isSelected());
            this.spunkt.setPNachprobe(getSpNachprobeCheck().isSelected());
            this.spunkt.setPFirmenprobe(getSpFirmenprobeCheck().isSelected());

            
                if (saveProbepunkt(this.objekt)) {

                	this.spunkt.setMessstelle(this.sprobePkt);
                    this.spunkt.getMessstelle().setObjekt(this.objekt);
                    this.spunkt.setMessstelle(this.sprobePkt);
                    this.spunkt.setId(objekt.getId());

                    this.spunkt = Sielhaut.merge(this.spunkt);

                    if (this.spunkt != null) {
                        this.spunkt = Sielhaut.findById(this.spunkt.getId());

                        this.frame.changeStatus(
                            "Sielhaut-Messpunkt erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                        setSielhautPunkt(this.spunkt);
                    }
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

            String bezeichnung=spunkt.getBezeichnung();
            if (bezeichnung != null
                    && new File(fotoPath + bezeichnung + ".jpg").canRead()) {
                params.put("foto", new String(fotoPath + bezeichnung + ".jpg"));
            } else {
                params.put("foto", new String(fotoPath + "kein_foto.jpg"));
            }

            if (bezeichnung != null
                    && new File(mapPath + bezeichnung + ".jpg").canRead()) {
                params.put("karte", new String(mapPath + bezeichnung + ".jpg"));
            } else {
                params.put("karte", new String(mapPath + "keine_karte.jpg"));
            }
            try {
                File pdfFile = File.createTempFile(bezeichnung, ".pdf");
                pdfFile.deleteOnExit();
                PDFExporter.getInstance().exportReport(params,
                        PDFExporter.SIELHAUT_BEARBEITEN, pdfFile.getAbsolutePath());
            } catch (Exception ex) {
                GUIManager.getInstance().showErrorMessage(
                        "PDF generieren fehlgeschlagen."
                        + "\n" + ex.getLocalizedMessage(),
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
                this.frame.changeStatus("Leere Kennummer!",
                    HauptFrame.ERROR_COLOR);
            } else {
                String kennNummer = getPrNummerFeld().getText().trim()
                    .replaceAll(" ", "");
                Timestamp datum = (new Timestamp(getPrDateChooser().getDate()
                    .getTime()));

                boolean exists = DatabaseQuery.probenahmeExists(kennNummer);

                if (!exists) {
                    Probenahme probe = new Probenahme();
                    probe.setKennummer(kennNummer);
                    probe.setDatumDerEntnahme((Timestamp) datum);
                    probe
                        .setAnalysepositions(new HashSet<Analyseposition>());
                    probe.setMessstelle(this.sprobePkt);
                    probe.setArt("Sielhaut");

                    ProbenEditor editDialog = new ProbenEditor(probe,
                        this.frame, true);
                    editDialog.setVisible(true);

                    // probeModel.updateList();
                    updateProbeListe();
                } else {
                    this.frame
                        .changeStatus(
                            "Eine Probenahme mit dieser Kennnummer existiert schon!",
                            HauptFrame.ERROR_COLOR);
                }
            }
        } else {
            this.frame.changeStatus("Fehler beim Anlegen: Kein Probepunkt!",
                HauptFrame.ERROR_COLOR);
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
        File exportDatei = getFrame().saveFile(new String[] {"csv"});
        if (exportDatei != null) {
            String ext = AuikUtils.getExtension(exportDatei);

            if (ext == null) {
                String newExt;
                if (exportDatei.getName().endsWith(".")) {
                    newExt = "csv";
                } else {
                    newExt = ".csv";
                }
                exportDatei = new File(exportDatei.getParent(),
                    exportDatei.getName() + newExt);
            }

            boolean doIt = false;
            if (exportDatei.exists()) {
                boolean answer = GUIManager.getInstance().showQuestion(
                    "Soll die vorhandene Datei " + exportDatei.getName()
                        + " wirklich überschrieben werden?",
                    "Datei bereits vorhanden!");
                if (answer && exportDatei.canWrite()) {
                    doIt = true;
                }
            } else if (exportDatei.getParentFile().canWrite()) {
                doIt = true;
            }

            if (doIt) {
                log.debug("Speichere nach '" + exportDatei.getName()
                    + "' (Ext: '" + ext + "') in '" + exportDatei.getParent()
                    + "' !");
                if (AuikUtils.exportTableDataToCVS(getPrTabelle(), exportDatei)) {
                    log.debug("Speichern erfolgreich!");
                } else {
                    log.debug("Fehler beim Speichern!");
                    GUIManager.getInstance().showErrorMessage(
                        "Beim Speichern der Datei '" + exportDatei
                            + "' trat ein Fehler auf!");
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
        if (this.probeEditAction == null) {
            this.probeEditAction = new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID = -4363530282004004696L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getPrTabelle().getSelectedRow();
                    // Natürlich nur editieren, wenn wirklich eine Zeile
                    // ausgewählt ist
                    if (row != -1) {
                        Probenahme probe = SielhautBearbeiten.this.probeModel
                            .getRow(row);
                        editProbenahme(probe);
                    }
                }
            };
            this.probeEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_B));
            this.probeEditAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
        }

        return this.probeEditAction;
    }

    private Action getProbeLoeschAction() {
        if (this.probeLoeschAction == null) {
            this.probeLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = -3208582919995701684L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getPrTabelle().getSelectedRow();
                    if (row != -1 && getPrTabelle().getEditingRow() == -1) {
                        Probenahme probe = SielhautBearbeiten.this.probeModel
                            .getRow(row);
                        if (GUIManager
                            .getInstance()
                            .showQuestion(
                                "Soll die Probenahme '"
                                    + probe.getKennummer()
                                    + "' wirklich inkl. aller Analysen gelöscht werden?",
                                "Löschen bestätigen")) {
                            if (SielhautBearbeiten.this.probeModel
                                .removeRow(row)) {
                                SielhautBearbeiten.this.frame.changeStatus(
                                    "Probenahme gelöscht!",
                                    HauptFrame.SUCCESS_COLOR);
                                log.debug("Probe " + probe.getKennummer()
                                    + " wurde gelöscht!");
                            } else {
                                SielhautBearbeiten.this.frame.changeStatus(
                                    "Konnte Probenahme nicht löschen!",
                                    HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.probeLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_L));
            this.probeLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.probeLoeschAction;
    }

    private void showProbePopup(MouseEvent e) {
        if (this.probePopup == null) {
            this.probePopup = new JPopupMenu("Probe");
            JMenuItem bearbItem = new JMenuItem(getProbeEditAction());
            JMenuItem loeschItem = new JMenuItem(getProbeLoeschAction());
            this.probePopup.add(bearbItem);
            this.probePopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = getPrTabelle().rowAtPoint(origin);

            if (row != -1) {
                getPrTabelle().setRowSelectionInterval(row, row);
                this.probePopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Sielhautpunkte";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "m_sielhaut1";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory() {
        return "Sielhaut";
    }

    public HauptFrame getFrame() {
        return this.frame;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
    public JPanel getPanel() {
        if (this.panel == null) {
            this.probeModel = new SielhautProbeModel();

            PanelBuilder sep = new PanelBuilder();
            sep.setAnchor(GridBagConstraints.WEST);
            sep.setFill(GridBagConstraints.HORIZONTAL);
            sep.setWeightX(1);
            sep.addSeparator(JSeparator.HORIZONTAL, "Stammdaten");

            RetractablePanel datenRP = new RetractablePanel(
                sep.getPanel(), getDatenPanel(), true, null);

            PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 1, 0, 1, 1,
                    5, 5, 5, 5);
            builder.addComponent(getPunktFeld(), "Messstelle:");
            builder.setWeightX(0);
            builder.addComponent(getPunktToolBar(), true);
            builder.setWeightX(1);
            builder.addComponent(datenRP, true);
            builder.addComponent(getProbenRtPanel(), true);
            builder.addComponent(getFotoRtPanel(), true);
            builder.addComponent(getKartenRtPanel(), true);
            builder.fillColumn();

            this.panel = builder.getPanel();
        }

        return this.panel;
    }

    private JTextField getPunktFeld() {
        if (this.punktFeld == null) {
            this.punktFeld = new JTextField();
            this.punktFeld.setEditable(false);
        }
        return this.punktFeld;
    }

    private JToolBar getPunktToolBar() {
        if (this.punktToolBar == null) {
            this.punktToolBar = new JToolBar();
            this.punktToolBar.setFloatable(false);
            this.punktToolBar.setRollover(true);

            this.punktToolBar.add(getPunktChooseButton());
            this.punktToolBar.add(getPunktEditButton());
            this.punktToolBar.add(getPunktNeuButton());
            this.punktToolBar.add(getPunktSaveButton());
            this.punktToolBar.add(getPunktPrintButton());
        }
        return this.punktToolBar;
    }

    private JButton getPunktChooseButton() {
        if (this.punktChooseButton == null) {
            this.punktChooseButton = new JButton(AuikUtils.getIcon(16,
                "reload.png"));
            // punktChooseButton.setHorizontalAlignment(JButton.CENTER);
            this.punktChooseButton.setToolTipText("Messpunkt auswählen");

            this.punktChooseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SielhautChooser chooser = new SielhautChooser(
                        SielhautBearbeiten.this.frame);
                    chooser.setVisible(true);

                    Sielhaut tmp = chooser.getChosenSielhaut();

                    if (tmp != null) {
                        setSielhautPunkt(tmp);
                    }
                }
            });
        }
        return this.punktChooseButton;
    }

    private JButton getPunktEditButton() {
        if (this.punktEditButton == null) {
            this.punktEditButton = new JButton(
                AuikUtils.getIcon(16, "edit.png"));
            this.punktEditButton.setToolTipText("Bearbeiten");
            this.punktEditButton.setEnabled(false);

            this.punktEditButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SielhautBearbeiten.this.manager.getSettingsManager()
                        .setSetting("auik.imc.edit_object",
                            SielhautBearbeiten.this.spunkt.getMessstelle()
                                .getObjekt().getId().intValue()
                            , false);
                    SielhautBearbeiten.this.manager
                        .switchModul("m_objekt_bearbeiten");
                }
            });
        }
        return this.punktEditButton;
    }

    private JButton getPunktPrintButton() {
        if (this.punktPrintButton == null) {
            this.punktPrintButton = new JButton(AuikUtils.getIcon(16,
                "fileprint.png"));
            // punktChooseButton.setHorizontalAlignment(JButton.CENTER);
            this.punktPrintButton.setToolTipText("Drucken");
            this.punktPrintButton.setEnabled(false);

            this.punktPrintButton.addActionListener(new ActionListener() {
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
        return this.punktPrintButton;
    }

    private JButton getPunktNeuButton() {
        if (this.punktNeuButton == null) {
            this.punktNeuButton = new JButton(AuikUtils.getIcon(16,
                "filenew.png"));
            // punktNeuButton.setHorizontalAlignment(JButton.CENTER);
            this.punktNeuButton.setToolTipText("Neuer Messpunkt");
            this.punktNeuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SielhautBearbeiten.this.manager
                    .switchModul("m_objekt_bearbeiten");
                }
            });
        }
        return this.punktNeuButton;
    }

    private JButton getPunktSaveButton() {
        if (this.punktSaveButton == null) {
            this.punktSaveButton = new JButton(AuikUtils.getIcon(16,
                "filesave.png"));
            // punktSaveButton.setHorizontalAlignment(JButton.CENTER);
            this.punktSaveButton.setToolTipText("Speichern");
            this.punktSaveButton.setEnabled(false);
            this.punktSaveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (SielhautBearbeiten.this.spunkt != null) {
                        saveSielhautPunkt();
                    }
                }
            });
        }
        return this.punktSaveButton;
    }

    // Daten
    private JPanel getDatenPanel() {
        if (this.datenPanel == null) {

            JScrollPane bemerkungsScroller = new JScrollPane(
                getSpBemerkungsArea(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            PanelBuilder topPanel = new PanelBuilder(PanelBuilder.NORTHEAST, true, true, 1, 0, 1, 1,
                    0, 0, 5, 5);
            topPanel.addComponent(getSpNamenFeld(), "Name: ", true, true);
            topPanel.addComponent(getSpEntgebFeld(), "Entwässerungsgebiet:", true, true);
            topPanel.addComponent(getSpLageFeld(), "Lage:", true, true);
            topPanel.setWeightY(1);
            topPanel.addComponent(bemerkungsScroller, "Bemerkungen:", true);
            topPanel.setWeightY(0);
        
            PanelBuilder lagePanel = new PanelBuilder(PanelBuilder.NORTHEAST, true, true, 1, 0, 1, 1,
                    0, 0, 5, 5);
            lagePanel.addComponent(getSpE32Feld(), "E32:", true);
            lagePanel.addComponent(getSpN32Feld(), "N32:", true);
            lagePanel.addComponent(getSpHaltungsnrFeld(), "Schacht-Nr.:", true);
            lagePanel.addComponent(getSpAlarmplannrFeld(), "Alarmplan-Nr.:", true);

            PanelBuilder checkboxes = new PanelBuilder(PanelBuilder.NORTHEAST, true, true, 1, 0, 1, 1,
                    0, 0, 5, 5);
            checkboxes.addComponent(getSpSielhautCheck(), true);
            checkboxes.addComponent(getSpFirmenprobeCheck(), true);
            checkboxes.addComponent(getSpNachprobeCheck(), true);
            checkboxes.fillColumn();

            PanelBuilder button = new PanelBuilder(PanelBuilder.NORTHEAST, true, true, 1, 1, 1, 1,
                    0, 0, 5, 5);
                    button.addComponent(getAusAblageButton(), true, true);
                    button.fillRows(2);

            PanelBuilder bottomContent = new PanelBuilder(PanelBuilder.NORTHEAST, true, true, 1, 0, 1, 1,
                    0, 0, 5, 5);
            bottomContent.setBorder(new EmptyBorder(0, 60, 0, 0));
            bottomContent.setWeightX(1);
            bottomContent.addComponents(true, lagePanel.getPanel(), button.getPanel());
            bottomContent.setWeight(0, 1);

            PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 1, 0, 1, 1,
                    0, 0, 5, 5);
            builder.setPreferedSize(800, 250);
            builder.setEmptyBorder(5);
            builder.setWeightY(1);
            builder.addComponent(topPanel.getPanel(), true);
            builder.setWeightY(0);
            builder.setWeightX(1);
            builder.addComponent(bottomContent.getPanel());
            builder.setWeightX(0);
            builder.addComponent(checkboxes.getPanel(), true);

            PanelBuilder content = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 0, 1, 1, 1,
                    0, 0, 5, 5);
            content.setEmptyBorder(15);
            content.addComponent(builder.getPanel());
            content.fillRow(true);
            this.datenPanel = content.getPanel();
        }
        return this.datenPanel;
    }

    private JTextField getSpAlarmplannrFeld() {
        if (this.spAlarmplannrFeld == null) {
            this.spAlarmplannrFeld = new LimitedTextField(50);
        }
        return this.spAlarmplannrFeld;
    }

    private JTextArea getSpBemerkungsArea() {
        if (this.spBemerkungsArea == null) {
            this.spBemerkungsArea = new LimitedTextArea(255);
            this.spBemerkungsArea.setLineWrap(true);
            this.spBemerkungsArea.setWrapStyleWord(true);
        }
        return this.spBemerkungsArea;
    }

    private JTextField getSpEntgebFeld() {
        if (this.spEntgebFeld == null) {
            this.spEntgebFeld = new LimitedTextField(50);
        }
        return this.spEntgebFeld;
    }

    private JTextField getSpHaltungsnrFeld() {
        if (this.spHaltungsnrFeld == null) {
            this.spHaltungsnrFeld = new LimitedTextField(50);
        }
        return this.spHaltungsnrFeld;
    }

    private DoubleField getSpN32Feld() {
        if (this.spN32Feld == null) {
            this.spN32Feld = new DoubleField(0, 0);
        }
        return this.spN32Feld;
    }

    private JTextField getSpLageFeld() {
        if (this.spLageFeld == null) {
            this.spLageFeld = new LimitedTextField(50);
        }
        return this.spLageFeld;
    }

    private JCheckBox getSpNachprobeCheck() {
        if (this.spNachprobeCheck == null) {
            this.spNachprobeCheck = new JCheckBox("Nachprobe");
        }
        return this.spNachprobeCheck;
    }

    private JCheckBox getSpFirmenprobeCheck() {
        if (this.spFirmenprobeCheck == null) {
            this.spFirmenprobeCheck = new JCheckBox("Firmenprobe");
        }
        return this.spFirmenprobeCheck;
    }

    private JTextField getSpNamenFeld() {
        if (this.spNamenFeld == null) {
            this.spNamenFeld = new LimitedTextField(50);
        }
        return this.spNamenFeld;
    }

    private DoubleField getSpE32Feld() {
        if (this.spE32Feld == null) {
            this.spE32Feld = new DoubleField(0, 0);
        }
        return this.spE32Feld;
    }

    private JCheckBox getSpSielhautCheck() {
        if (this.spSielhautCheck == null) {
            this.spSielhautCheck = new JCheckBox("Routinekontrolle");
        }
        return this.spSielhautCheck;
    }

    // Proben
    private RetractablePanel getProbenRtPanel() {
        if (this.probenRtPanel == null) {
            PanelBuilder builder = new PanelBuilder();

            builder.setAnchor(GridBagConstraints.NORTHWEST);
            builder.setFill(GridBagConstraints.BOTH);
            builder.setInsets(5, 5, 0, 5);
            builder.setWeightX(1);
            builder.setWeightY(0.8);
            builder.addComponent(new JScrollPane(getPrTabelle()), true);
            builder.setWeightY(0);
            builder.addSeparator("Neue Probenahme", true);
            builder.addComponent(getNeuProbPanel(), true);
            builder.addSeparator("Auswertung", true);
            builder.addComponent(getAuswertungPanel(), true);

            JPanel probenPanel = builder.getPanel();

            PanelBuilder sep = new PanelBuilder();
            sep.setAnchor(GridBagConstraints.WEST);
            sep.setFill(GridBagConstraints.HORIZONTAL);
            sep.setWeightX(1);
            sep.addSeparator("Probenahmen");
            this.probenRtPanel = new RetractablePanel(sep.getPanel(), probenPanel,
                false, null) {
                private static final long serialVersionUID = -6231371376662899465L;

                @Override
                public void opening() {
                    SwingWorkerVariant worker = new SwingWorkerVariant(
                        getSpNamenFeld()) {
                        @Override
                        protected void doNonUILogic() throws RuntimeException {
                            SielhautBearbeiten.this.probeModel.updateList();
                        }

                        @Override
                        protected void doUIUpdateLogic()
                            throws RuntimeException {
                            SielhautBearbeiten.this.probeModel
                                .fireTableDataChanged();

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
            PanelBuilder builder = new PanelBuilder();
            builder.setInsets(0, 0, 0, 5);
            builder.setBorder(new EmptyBorder(5, 5, 5, 5));
            builder.setAnchor(GridBagConstraints.WEST);
            builder.setWeightX(0.5);
            builder.setFill(GridBagConstraints.HORIZONTAL);
            builder.addComponent(getPrNummerFeld(), "Kennummer:");
            builder.setWeightX(0);
            builder.addComponent(getPrDateChooser(), "Datum:");
            builder.addComponent(getPrAnlegenButton());
            builder.addComponent(getTabelleExportButton(), true);
            this.neuProbPanel = builder.getPanel();
        }
        return this.neuProbPanel;
    }

    private JPanel getAuswertungPanel() {
        if (this.auswertungPanel == null) {
            PanelBuilder builder = new PanelBuilder();

            builder.setInsets(0, 0, 0, 5);
            builder.setBorder(new EmptyBorder(5, 5, 5, 5));
            builder.setAnchor(GridBagConstraints.WEST);
            builder.addComponent(getVonDateChooser(), "Von:");
            builder.addComponent(getBisDateChooser(), "Bis:");
            builder.addComponents(getBleiCheck(), getCadmiumCheck(),
                    getChromCheck(), getKupferCheck(), getNickelCheck(),
                    getQuecksilberCheck(), getZinkCheck(), getSubmitButton());
            builder.fillRow(true);
            this.auswertungPanel = builder.getPanel();

        }
        return this.auswertungPanel;
    }

    private JTable getPrTabelle() {
        if (this.prTabelle == null) {
            this.prTabelle = new JTable(this.probeModel);
            this.prTabelle.getColumnModel().getColumn(0).setWidth(10);

            this.prTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            this.prTabelle.getInputMap().put(
                (KeyStroke) getProbeEditAction().getValue(
                    Action.ACCELERATOR_KEY),
                getProbeEditAction().getValue(Action.NAME));
            this.prTabelle.getActionMap().put(
                getProbeEditAction().getValue(Action.NAME),
                getProbeEditAction());

            this.prTabelle.getInputMap().put(
                (KeyStroke) getProbeLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getProbeLoeschAction().getValue(Action.NAME));
            this.prTabelle.getActionMap().put(
                getProbeLoeschAction().getValue(Action.NAME),
                getProbeLoeschAction());

            this.prTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = getPrTabelle().rowAtPoint(origin);

                        Probenahme probe = SielhautBearbeiten.this.probeModel
                            .getRow(row);
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
        return this.prTabelle;
    }

    private JButton getSubmitButton() {
        if (this.submitButton == null) {
            this.submitButton = new JButton("Abschicken");

            this.submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showResultOneAxis();
                }
            });
        }

        return this.submitButton;
    }

    private class AuswertungsDialog extends JDialog {
        private static final long serialVersionUID = 3892351392140673333L;

        /**
         * Ein Listener für die Events des Dialogs.
         * @author David Klotz
         */
        private class DialogListener extends WindowAdapter implements
            ActionListener {
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
         * @author David Klotz
         */
        private class ExportTableModel extends AbstractTableModel {
            private static final long serialVersionUID = -2296763811705380082L;
            private TimeSeriesCollection col1, col2;
            private List<Minute> dateList;

            public ExportTableModel(TimeSeriesCollection col1,
                TimeSeriesCollection col2) {
                this.col1 = col1;
                this.col2 = col2;
                this.dateList = new ArrayList<Minute>();

                initializeData();
            }
            
            private boolean istVorhanden (List<Minute> liste, Minute neu) {
        	boolean  x = false;
        	ArrayList<Minute> array = (ArrayList<Minute>)liste;
        	for (int i =0; i < array.size();i++) {
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
                        if (!istVorhanden(this.dateList,v)) {
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
                return this.col1.getSeriesCount()
                    + ((this.col2 != null) ? this.col2.getSeriesCount() : 0)
                    + 1;// 2;
            }

            @Override
            public int getRowCount() {
                return this.dateList.size();// + 1;
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
                        item = (APosDataItem) this.col1.getSeries(seriesIndex)
                            .getDataItem(min);
                    } else if (this.col2 != null) {
                        item = (APosDataItem) this.col2.getSeries(series2Index)
                            .getDataItem(min);
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
                        //TODO: key == seriesName?
						//tmp = this.col1.getSeriesName(seriesIndex)
                        tmp = this.col1.getSeries(seriesIndex).getKey().toString()  
							+ ", "
                            + this.col1.getSeries(seriesIndex)
                                .getRangeDescription();
                    } else if (this.col2 != null) {
                        //tmp = this.col2.getSeriesName(series2Index)
                        tmp = this.col2.getSeries(series2Index).getKey().toString()  
							+ ", "
                            + this.col2.getSeries(series2Index)
                                .getRangeDescription();
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

        public AuswertungsDialog(String title,
            TimeSeriesCollection leftDataset,
            TimeSeriesCollection rightDataset, HauptFrame owner) {
            super(owner, title + "-Auswertung", true);
            this.owner = owner;
            this.title = title;

            this.leftDataset = leftDataset;
            this.rightDataset = rightDataset;

            this.listener = new DialogListener();

            this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(this.listener);

            this.speichernButton = new JButton("Speichern");
            this.speichernButton.addActionListener(this.listener);
            this.abbrechenButton = new JButton("Schließen");
            this.abbrechenButton.addActionListener(this.listener);

            JPanel tmp = new JPanel(new BorderLayout(0, 7));

            tmp.add(initializeContent(), BorderLayout.CENTER);
            PanelBuilder bbar = new PanelBuilder();
            bbar.addComponents(this.speichernButton, this.abbrechenButton);
            JPanel buttonBar = bbar.getPanel();
            tmp.add(buttonBar, BorderLayout.SOUTH);

            this.setContentPane(tmp);
            this.pack();
            this.setLocationRelativeTo(SielhautBearbeiten.this.frame);
        }

        private JComponent initializeContent() {
            this.tabbedPane = new JTabbedPane();

            this.tabbedPane.addTab("Diagramm", createDiagrammPanel());
            this.tabbedPane.addTab("Tabelle", createTabellenPanel());

            return this.tabbedPane;
        }

        private JPanel createDiagrammPanel() {
            JFreeChart chart;
            if (this.rightDataset == null) {
                chart = Charts.createDefaultTimeSeriesChart(this.title,
                    this.leftDataset);
            } else {
                chart = Charts.createDefaultTimeSeriesChart(this.title,
                    this.leftDataset, this.rightDataset);
            }

            this.chartPanel = new ChartPanel(chart, false);

            return this.chartPanel;
        }

        private JComponent createTabellenPanel() {
            this.exportTable = new JTable(new ExportTableModel(
                this.leftDataset, this.rightDataset));
            this.exportTable.setBorder(BorderFactory
                .createBevelBorder(BevelBorder.RAISED));
            this.exportTable.setColumnSelectionAllowed(true);
            this.exportTable.setRowSelectionAllowed(true);
            this.exportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.exportTable.setGridColor(new Color(230, 230, 230));

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
            zentrierterRenderer
                .setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            DefaultTableCellRenderer rechtsBuendigRenderer = new DefaultTableCellRenderer();
            rechtsBuendigRenderer
                .setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

            TableColumn column = null;
            for (int i = 0; i < this.exportTable.getColumnCount(); i++) {
                column = this.exportTable.getColumnModel().getColumn(i);
                if (i == 0) {// || i == 1) {
                    column.setCellRenderer(zentrierterRenderer);
                    column.setPreferredWidth(75);
                } else {
                    column.setCellRenderer(rechtsBuendigRenderer);
                    column.setPreferredWidth(90);
                }
            }

            JScrollPane tabellenScroller = new JScrollPane(this.exportTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            return tabellenScroller;
        }

        public void saveTabelle() {
            SielhautBearbeiten.this.frame.clearStatus();
            File exportDatei;
            String[] csv = new String[] {"csv"};

            exportDatei = this.owner.saveFile(csv);

            if (exportDatei != null) {
                String ext = AuikUtils.getExtension(exportDatei);

                if (ext == null) {
                    String newExt;
                    if (exportDatei.getName().endsWith(".")) {
                        newExt = "csv";
                    } else {
                        newExt = ".csv";
                    }
                    exportDatei = new File(exportDatei.getParent(),
                        exportDatei.getName() + newExt);
                }

                boolean doIt = false;
                if (exportDatei.exists()) {
                    boolean answer = GUIManager.getInstance().showQuestion(
                        "Soll die vorhandene Datei " + exportDatei.getName()
                            + " wirklich überschrieben werden?",
                        "Datei bereits vorhanden!");
                    if (answer && exportDatei.canWrite()) {
                        doIt = true;
                    }
                } else if (exportDatei.getParentFile().canWrite()) {
                    doIt = true;
                }

                if (doIt) {
                    log.debug("Speichere nach '" + exportDatei.getName()
                        + "' (Ext: '" + ext + "') in '"
                        + exportDatei.getParent() + "' !");
                    if (AuikUtils.exportTableDataToCVS(this.exportTable,
                        exportDatei)) {
                        GUIManager.getInstance().showInfoMessage(
                            "Speichern der CSV-Datei erfolgreich!",
                            "Speichern erfolgreich");
                    } else {
                        log.debug("Beim Speichern der Datei '" + exportDatei
                            + "' trat ein Fehler auf!");
                        GUIManager.getInstance().showErrorMessage(
                            "Beim Speichern der Datei '" + exportDatei
                                + "' trat ein Fehler auf!");
                    }
                }
            }
        }

        private void showTabellenPopup(MouseEvent e) {
            if (this.tabellenMenu == null) {
                this.tabellenMenu = new JPopupMenu("Tabelle");
                JMenuItem speichernItem = new JMenuItem(new AbstractAction(
                    "Speichern") {
                    private static final long serialVersionUID = 2096747421254651035L;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveTabelle();
                    }
                });
                this.tabellenMenu.add(speichernItem);
            }

            if (e.isPopupTrigger()) {
                Point origin = e.getPoint();
                int row = this.exportTable.rowAtPoint(origin);
                int col = this.exportTable.columnAtPoint(origin);

                if (row != -1) {
                    this.exportTable.setRowSelectionInterval(row, row);
                    this.exportTable.setColumnSelectionInterval(col, col);
                    this.tabellenMenu
                        .show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }

        public void doAbbrechen() {
            SielhautBearbeiten.this.frame.clearStatus();
            this.dispose();
        }

        public void doSpeichern() {
            SielhautBearbeiten.this.frame.clearStatus();
            if (this.tabbedPane.getSelectedIndex() == 0) {
                try {
                    this.chartPanel.doSaveAs();
                } catch (IOException e) {
                    log.debug("Konnte Datei nicht speichern!");
                }
            } else if (this.tabbedPane.getSelectedIndex() == 1) {
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

                    AuswertungsDialog dialog = new AuswertungsDialog(
                        SielhautBearbeiten.this.getPunktFeld().getText(),
                        SielhautBearbeiten.this.dataSet1,
                        null, SielhautBearbeiten.this.frame);

                    dialog.setVisible(true);
                } else {
                    SielhautBearbeiten.this.frame
                        .changeStatus("Keine Parameter ausgewählt!");
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
            selectedParams.add(Parameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_BLEI));
        }
        if (getCadmiumCheck().isSelected()) {
            selectedParams.add(Parameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_CADMIUM));
        }
        if (getChromCheck().isSelected()) {
            selectedParams.add(Parameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_CHROM));
        }
        if (getKupferCheck().isSelected()) {
            selectedParams.add(Parameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_KUPFER));
        }
        if (getNickelCheck().isSelected()) {
            selectedParams.add(Parameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_NICKEL));
        }
        if (getQuecksilberCheck().isSelected()) {
            selectedParams.add(Parameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER));
        }
        if (getZinkCheck().isSelected()) {
            selectedParams.add(Parameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_ZINK));
        }

        // Wenn keine Check Box angeklickt wurde sollen alle Paramater
        // berücksichtig werden
        if (selectedParams.isEmpty()) {
            String[] paramIDs = {
                DatabaseConstants.ATL_PARAMETER_ID_BLEI,
                DatabaseConstants.ATL_PARAMETER_ID_CADMIUM,
                DatabaseConstants.ATL_PARAMETER_ID_CHROM,
                DatabaseConstants.ATL_PARAMETER_ID_KUPFER,
                DatabaseConstants.ATL_PARAMETER_ID_NICKEL,
                DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER,
                DatabaseConstants.ATL_PARAMETER_ID_ZINK
            };
            for (String paramID : paramIDs) {
                selectedParams.add(Parameter.findById(paramID));
            }
        }

        createSeries(selectedParams, this.sprobePkt, von, bis, col);
        selectedParams.clear();

        return col;
    }

    private void createSeries(
        List<Parameter> params, Messstelle pkt, Date von, Date bis,
        TimeSeriesCollection col) {
        if (pkt != null) {
            for (Parameter param : params) {
                List<Analyseposition> list =
                    DatabaseQuery.getAnalysepositionen(param, pkt, von, bis);

                TimeSeries series = ChartDataSets
                    .createAnalysePositionenSielhautSeries(
                        list,
                        param.getBezeichnung() + " ",
                        "Verhältnis zum Hintergrundwert");
                col.addSeries(series);
            }
        }
        this.frame.changeStatus("Auswertung abgeschlossen");
    }

    private JDateChooser getVonDateChooser() {
        if (this.vonDateChooser == null) {
            this.vonDateChooser = new JDateChooser(DateUtils.FORMAT_DEFAULT,
                false);
        }

        return this.vonDateChooser;
    }

    private JDateChooser getBisDateChooser() {

        if (this.bisDateChooser == null) {
            this.bisDateChooser = new JDateChooser(DateUtils.FORMAT_DEFAULT,
                false);
        }

        return this.bisDateChooser;
    }

    private JCheckBox getBleiCheck() {
        if (this.BleiCheck == null) {
            this.BleiCheck = new JCheckBox("Blei", false);
        }
        return this.BleiCheck;
    }

    private JCheckBox getCadmiumCheck() {
        if (this.CadmiumCheck == null) {
            this.CadmiumCheck = new JCheckBox("Cadmium", false);
        }
        return this.CadmiumCheck;
    }

    private JCheckBox getChromCheck() {
        if (this.ChromCheck == null) {
            this.ChromCheck = new JCheckBox("Chrom", false);
        }
        return this.ChromCheck;
    }

    private JCheckBox getKupferCheck() {
        if (this.KupferCheck == null) {
            this.KupferCheck = new JCheckBox("Kupfer", false);
        }
        return this.KupferCheck;
    }

    private JCheckBox getNickelCheck() {
        if (this.NickelCheck == null) {
            this.NickelCheck = new JCheckBox("Nickel", false);
        }
        return this.NickelCheck;
    }

    private JCheckBox getQuecksilberCheck() {
        if (this.QuecksilberCheck == null) {
            this.QuecksilberCheck = new JCheckBox("Quecksilber", false);
        }
        return this.QuecksilberCheck;
    }

    private JCheckBox getZinkCheck() {
        if (this.ZinkCheck == null) {
            this.ZinkCheck = new JCheckBox("Zink", false);
        }
        return this.ZinkCheck;
    }

    private JTextField getPrNummerFeld() {
        if (this.prNummerFeld == null) {
            this.prNummerFeld = new LimitedTextField(50, "");
        }
        return this.prNummerFeld;
    }

    private JDateChooser getPrDateChooser() {
        if (this.prDateChooser == null) {
            this.prDateChooser = new JDateChooser(DateUtils.FORMAT_DEFAULT,
                false);
        }
        return this.prDateChooser;
    }

    private JButton getPrAnlegenButton() {
        if (this.prAnlegenButton == null) {
            this.prAnlegenButton = new JButton("Anlegen");
            this.prAnlegenButton.setEnabled(false);

            this.prAnlegenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    neueProbenahme();
                }
            });
        }

        return this.prAnlegenButton;
    }

    private JButton getTabelleExportButton() {
        if (this.tabelleExportButton == null) {
            this.tabelleExportButton = new JButton("Tabelle speichern");
            this.tabelleExportButton.setEnabled(false);

            this.tabelleExportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveTabelle();
                }
            });
        }

        return this.tabelleExportButton;
    }

    // Foto
    private RetractablePanel getFotoRtPanel() {
        if (this.fotoRtPanel == null) {
            JPanel fotoPanel = new JPanel();

            fotoPanel.add(getFotoLabel());
            fotoPanel.setBackground(Color.WHITE);
            fotoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            PanelBuilder sep = new PanelBuilder();
            sep.setAnchor(GridBagConstraints.WEST);
            sep.setFill(GridBagConstraints.HORIZONTAL);
            sep.setWeightX(1);
            sep.addSeparator(JSeparator.HORIZONTAL, "Foto");
            this.fotoRtPanel = new RetractablePanel(sep.getPanel(), fotoPanel, false, null) {
                private static final long serialVersionUID = 6505102322099919490L;

                @Override
                public void opening() {
                    if (SielhautBearbeiten.this.spunkt != null
                        && SielhautBearbeiten.this.spunkt.getId() != null) {
                        String imgPath = SielhautBearbeiten.this.manager
                            .getSettingsManager().getSetting(
                                "auik.system.spath_fotos")
                            + SielhautBearbeiten.this.spunkt.getBezeichnung()
                            + ".jpg";
                        File imgFile = new File(imgPath);
                        if (imgFile.canRead()) {
                            ImageIcon imgIcon = new ImageIcon(
                                imgFile.getAbsolutePath());
                            int panelWidth = getPanel().getWidth() - 50;
                            if (imgIcon.getIconWidth() > panelWidth) {
                                imgIcon.setImage(imgIcon.getImage()
                                    .getScaledInstance(panelWidth, -1,
                                        Image.SCALE_FAST));
                            }
                            getFotoLabel().setIcon(null);
                            getFotoLabel().setIcon(imgIcon);
                            getFotoLabel().setText(null);
                        } else {
                            getFotoLabel().setIcon(null);
                            getFotoLabel().setText(
                                "<html><b>-  Foto "
                                    + SielhautBearbeiten.this.spunkt
                                        .getBezeichnung()
                                    + ".jpg nicht gefunden!  -</b></html>");
                        }
                    }
                }
            };
        }
        return this.fotoRtPanel;
    }

    private JLabel getFotoLabel() {
        if (this.fotoLabel == null) {
            this.fotoLabel = new JLabel(
                "<html><b>- Kein Foto verfügbar! -</b></html>");
        }

        return this.fotoLabel;
    }

    // Kartenausschnitt
    private RetractablePanel getKartenRtPanel() {
        if (this.kartenRtPanel == null) {
            JPanel kartenPanel = new JPanel();
            kartenPanel.add(getKartenLabel());
            kartenPanel.setBackground(Color.WHITE);
            kartenPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            PanelBuilder sep = new PanelBuilder();
            sep.setAnchor(GridBagConstraints.WEST);
            sep.setFill(GridBagConstraints.HORIZONTAL);
            sep.setWeightX(1);
            sep.addSeparator(JSeparator.HORIZONTAL, "Kartenausschnitt");
            this.kartenRtPanel = new RetractablePanel(sep.getPanel(),
                kartenPanel, false, null) {
                private static final long serialVersionUID = 1276454146798307743L;

                @Override
                public void opening() {
                    if (SielhautBearbeiten.this.spunkt != null
                        && SielhautBearbeiten.this.spunkt.getId() != null) {
                        String imgPath = SielhautBearbeiten.this.manager
                            .getSettingsManager().getSetting(
                                "auik.system.spath_karten")
                            + SielhautBearbeiten.this.spunkt.getBezeichnung()
                            + ".jpg";
                        File imgFile = new File(imgPath);
                        if (imgFile.canRead()) {
                            ImageIcon imgIcon = new ImageIcon(
                                imgFile.getAbsolutePath());
                            int panelWidth = getPanel().getWidth() - 55;
                            if (imgIcon.getIconWidth() > panelWidth) {
                                imgIcon.setImage(imgIcon.getImage()
                                    .getScaledInstance(panelWidth, -1,
                                        Image.SCALE_FAST));
                            }
                            getKartenLabel().setIcon(imgIcon);
                            getKartenLabel().setText(null);
                        } else {
                            getKartenLabel().setIcon(null);
                            getKartenLabel().setText(
                                "<html><b>-  Karte "
                                    + SielhautBearbeiten.this.spunkt
                                        .getBezeichnung()
                                    + ".jpg nicht gefunden!  -</b></html>");
                        }
                    }
                }
            };
        }
        this.kartenRtPanel.repaint();
        return this.kartenRtPanel;
    }

    private JLabel getKartenLabel() {
        if (this.kartenLabel == null) {
            this.kartenLabel = new JLabel(
                "<html><b>- Keine Karte verfügbar -</b></html>");
        }

        return this.kartenLabel;
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
                    this.spE32Feld.setText(e32AusZeile.substring(
                        0, 7));
                    this.spN32Feld.setText(n32AusZeile
                        .substring(0, 7));
                    this.frame.changeStatus("Rechts- und Hochwert eingetragen",
                        HauptFrame.SUCCESS_COLOR);
                } else {
                    this.frame.changeStatus(
                        "Zwischenablage enthält keine verwertbaren Daten",
                        HauptFrame.ERROR_COLOR);
                }
                break;
            }
        }
    }

    public JButton getAusAblageButton() {
        if (this.ausAblageButton == null) {

            this.ausAblageButton = new JButton("aus QGis");
            this.ausAblageButton
                .setToolTipText("Rechts- und Hochwert aus Zwischenablage einfügen");
            this.ausAblageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    readClipboard();
                }
            });
        }

        return this.ausAblageButton;
    }
}
