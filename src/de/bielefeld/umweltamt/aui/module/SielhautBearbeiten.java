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
import java.awt.event.KeyAdapter;
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

import org.eclipse.birt.report.engine.api.EngineException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ReportManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlSielhaut;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.RetractablePanel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.charts.APosDataItem;
import de.bielefeld.umweltamt.aui.utils.charts.ChartDataSets;
import de.bielefeld.umweltamt.aui.utils.charts.Charts;
import de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

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
    private DoubleField spRechtsWertFeld;
    private DoubleField spHochWertFeld;
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

    private AtlSielhaut spunkt;
    private AtlProbepkt sprobePkt;
    private BasisObjekt objekt;
    private BasisStandort standort;
    private BasisBetreiber betreiber;
    private BasisObjektarten art;
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
            this.objekt = BasisObjekt.findById(new Integer(this.manager
                .getSettingsManager().getIntSetting("auik.imc.edit_object")));
            this.manager.getSettingsManager().removeSetting(
                "auik.imc.edit_object");
            this.sprobePkt = AtlProbepkt.findById(this.objekt.getObjektid());
            this.spunkt = AtlSielhaut.findById(this.sprobePkt
                .getAtlSielhaut().getId());
            setSielhautPunkt(this.spunkt);
        } else if (BasisObjekt.findById(24856) != null) {
            //FIXME: A constant id? In the code? -.-
            this.objekt = BasisObjekt.findById(24856);
            this.sprobePkt = AtlProbepkt.findById(this.objekt.getObjektid());
            this.spunkt = AtlSielhaut.findById(this.sprobePkt
                .getAtlSielhaut().getId());
            setSielhautPunkt(this.spunkt);
        }

    }

    public void setSielhautPunkt(AtlSielhaut sp) {
        this.spunkt = sp;
        if (this.spunkt.getId() != null) {
            this.sprobePkt = AtlProbepkt.findById(
                this.spunkt.getBasisObjekt().getObjektid());
            getPrAnlegenButton().setEnabled(true);
            getTabelleExportButton().setEnabled(true);
        } else {
            this.objekt = new BasisObjekt();
            this.standort = BasisStandort.findById(
                DatabaseConstants.BASIS_STANDORT_KEIN_STANDORT);
            this.betreiber = BasisBetreiber.findById(
                DatabaseConstants.BASIS_BETREIBER_ID_KEINE_BETREIBER);
            this.art = BasisObjektarten.findById(
                DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT);
            this.objekt.setBasisStandort(this.standort);
            this.objekt.setBasisBetreiber(this.betreiber);
            this.objekt.setBasisObjektarten(this.art);
            this.objekt.setInaktiv(false);
            this.sprobePkt = new AtlProbepkt();
            this.sprobePkt.setAtlProbeart(
                AtlProbeart.findById(
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

        getSpRechtsWertFeld().setValue(this.spunkt.getRechtswert());
        getSpHochWertFeld().setValue(this.spunkt.getHochwert());

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

        this.probeModel.setProbepunkt(this.sprobePkt);

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
        AtlSielhaut neuerPunkt = new AtlSielhaut();
        neuerPunkt.setRechtswert(new Double(0.0));
        neuerPunkt.setHochwert(new Double(0.0));
        neuerPunkt.setBezeichnung("Neuer Sielhaut-Punkt");
        setSielhautPunkt(neuerPunkt);
    }

    /**
     * Speichert ein neu angelegtes Probenahmepunkt-Objekt.
     */
    public boolean saveObjekt() {
        boolean saved = false;

        this.objekt = BasisObjekt.merge(this.objekt);

        saved = true;

        return saved;
    }

    /**
     * Speichert einen neu angelegten Probenahmepunkt.
     */
    public boolean saveProbepunkt(BasisObjekt objekt) {
        boolean saved = false;

        objekt = BasisObjekt.findById(objekt.getObjektid());
        this.sprobePkt.setBasisObjekt(objekt);
//        this.spunkt = AtlSielhaut.getSielhaut(this.spunkt.getId());
//        this.sprobePkt.setAtlSielhaut(this.spunkt);

        this.sprobePkt = AtlProbepkt.merge(this.sprobePkt);

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
            this.spunkt.setRechtswert(getSpRechtsWertFeld().getDoubleValue());
            this.spunkt.setHochwert(getSpHochWertFeld().getDoubleValue());

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

            if (saveObjekt()) {
                if (saveProbepunkt(this.objekt)) {

                    this.spunkt.setBasisObjekt(this.objekt);
                    this.spunkt.setAtlProbepkt(this.sprobePkt);

                    this.spunkt = AtlSielhaut.merge(this.spunkt);

                    if (this.spunkt != null) {
                        // TODO: This needs to be changed more consistent...
                        this.spunkt = AtlSielhaut.findById(this.spunkt.getId());
                        this.sprobePkt.setAtlSielhaut(this.spunkt);
                        this.sprobePkt.merge();

                        this.frame.changeStatus(
                            "Sielhaut-Messpunkt erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                        setSielhautPunkt(this.spunkt);
                    }
                }
            } else {
                this.frame.changeStatus(
                    "Sielhaut-Messpunkt konnte nicht gespeichert werden!",
                    HauptFrame.ERROR_COLOR);
            }
        }
    }

    public void showReport() throws EngineException {
        if (this.spunkt.getId() != null || this.spunkt.getHaltungsnr() != null) {
            ReportManager.getInstance().startReportWorker("SielhautBearbeiten",
                this.spunkt.getId(), this.spunkt.getBezeichnung(),
                this.punktPrintButton);
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
                    AtlProbenahmen probe = new AtlProbenahmen();
                    probe.setKennummer(kennNummer);
                    probe.setDatumDerEntnahme((Timestamp) datum);
                    probe
                        .setAtlAnalysepositions(new HashSet<AtlAnalyseposition>());
                    probe.setAtlProbepkt(this.sprobePkt);
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
    public void editProbenahme(AtlProbenahmen probe) {
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
                        AtlProbenahmen probe = SielhautBearbeiten.this.probeModel
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
                        AtlProbenahmen probe = SielhautBearbeiten.this.probeModel
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

            RetractablePanel datenRP = new RetractablePanel(
                DefaultComponentFactory.getInstance().createSeparator(
                    "Stammdaten"), getDatenPanel(), true, null);

            FormLayout layout = new FormLayout(
                "pref, 5dlu, 100dlu:g, 3dlu, l:p",
                "p, 3dlu, t:p, 10dlu, t:p, 10dlu, t:p, 10dlu, t:p");
            PanelBuilder builder = new PanelBuilder(layout);
            CellConstraints cc = new CellConstraints();

            builder.setDefaultDialogBorder();

            builder.addLabel("Messstelle:", cc.xy(1, 1));
            builder.add(getPunktFeld(), cc.xy(3, 1));
            builder.add(getPunktToolBar(), cc.xy(5, 1));
            builder.add(datenRP, cc.xyw(1, 3, 5, "f, f"));
            builder.add(getProbenRtPanel(), cc.xyw(1, 5, 5, "f, f"));
            builder.add(getFotoRtPanel(), cc.xyw(1, 7, 5, "f, f"));
            builder.add(getKartenRtPanel(), cc.xyw(1, 9, 5, "f, f"));

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

                    AtlSielhaut tmp = chooser.getChosenSielhaut();

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
                            SielhautBearbeiten.this.spunkt
                                .getBasisObjekt().getObjektid().intValue()
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
                    } catch (EngineException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
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
                    neuerSielhautPunkt();
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
            FormLayout layout = new FormLayout(
                "r:p, 3dlu, 150dlu, 10dlu, 70dlu, 10dlu, 100dlu", "pref, " + // 1
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
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();
            JScrollPane bemerkungsScroller = new JScrollPane(
                getSpBemerkungsArea(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            builder.addLabel("<html><b>Name:</b></html>", cc.xy(1, 1));
            builder.add(getSpNamenFeld(), cc.xy(3, 1));
            builder.addLabel("Entwässerungsgebiet:", cc.xy(1, 3));
            builder.add(getSpEntgebFeld(), cc.xy(3, 3));
            builder.addLabel("Lage:", cc.xy(1, 5));
            builder.add(getSpLageFeld(), cc.xy(3, 5));
            builder.addLabel("Bemerkungen:", cc.xy(1, 7));
            builder.add(bemerkungsScroller, cc.xyw(3, 7, 5));
            builder.addLabel("Rechtswert:", cc.xy(1, 9));
            builder.add(getSpRechtsWertFeld(), cc.xy(3, 9));
            builder.add(getAusAblageButton(), cc.xywh(5, 9, 1, 3));
            builder.add(getSpSielhautCheck(), cc.xy(7, 9));
            builder.addLabel("Hochwert:", cc.xy(1, 11));
            builder.add(getSpHochWertFeld(), cc.xy(3, 11));
            builder.add(getSpFirmenprobeCheck(), cc.xy(7, 11));
            builder.addLabel("Schacht-Nr.:", cc.xy(1, 13));
            builder.add(getSpHaltungsnrFeld(), cc.xyw(3, 13, 3));
            builder.add(getSpNachprobeCheck(), cc.xy(7, 13));
            builder.addLabel("Alarmplan-Nr.:", cc.xy(1, 15));
            builder.add(getSpAlarmplannrFeld(), cc.xyw(3, 15, 3));

            // builder.getPanel().setBackground(Color.WHITE);
            // builder.getPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.datenPanel = builder.getPanel();
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

    private DoubleField getSpHochWertFeld() {
        if (this.spHochWertFeld == null) {
            this.spHochWertFeld = new DoubleField(1);
        }
        return this.spHochWertFeld;
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

    private DoubleField getSpRechtsWertFeld() {
        if (this.spRechtsWertFeld == null) {
            this.spRechtsWertFeld = new DoubleField(1);
        }
        return this.spRechtsWertFeld;
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
            FormLayout layout = new FormLayout(

                "p, 4dlu, p:g, 7dlu, p, 4dlu, max(60dlu;p), 7dlu,max(60dlu;p),7dlu,max(60dlu;p) ");
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();

            builder.appendRow("f:65dlu:g");
            builder.append(new JScrollPane(getPrTabelle()), 11);

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
            this.probenRtPanel = new RetractablePanel(DefaultComponentFactory
                .getInstance().createSeparator("Probenahmen"), probenPanel,
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
            FormLayout layout = new FormLayout(
                "pref, 5dlu,  pref, 5dlu, pref, 5dlu,  pref, 5dlu, pref, 5dlu,pref, 5dlu, pref, 5dlu,pref, 5dlu,pref, 5dlu,  pref, 5dlu, pref, 5dlu,  "
                    + "pref, 5dlu, pref, 5dlu,pref,pref, 5dlu, pref, 5dlu,pref",

                "pref, 3dlu" + ", " + "pref");
            CellConstraints cc = new CellConstraints();
            CellConstraints cc2 = (CellConstraints) cc.clone();

            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();

            builder.add(new JLabel("Kennummer:"), cc.xy(1, 1),
                getPrNummerFeld(), cc2.xyw(3, 1, 20));
            builder.add(new JLabel("Datum:"), cc.xy(25, 1), getPrDateChooser(),
                cc2.xy(28, 1));

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
            builder.setDefaultDialogBorder();

            builder.add(new JLabel("Von:"), cc.xy(1, 1), getVonDateChooser(),
                cc2.xy(3, 1));
            builder.add(new JLabel("Bis:"), cc.xy(5, 1), getBisDateChooser(),
                cc2.xy(7, 1));

            builder.add(getBleiCheck(), cc.xy(11, 1));
            builder.add(getCadmiumCheck(), cc.xy(13, 1));
            builder.add(getChromCheck(), cc.xy(15, 1));
            builder.add(getKupferCheck(), cc.xy(17, 1));
            builder.add(getNickelCheck(), cc.xy(19, 1));
            builder.add(getQuecksilberCheck(), cc.xy(21, 1));
            builder.add(getZinkCheck(), cc.xy(23, 1));
            builder.add(getSubmitButton(), cc.xy(27, 1));

            this.auswertungPanel = builder.getPanel();

        }
        return this.auswertungPanel;
    }

    private JTable getPrTabelle() {
        if (this.prTabelle == null) {
            this.prTabelle = new JTable(this.probeModel);
            this.prTabelle.getColumnModel().getColumn(0).setWidth(40);
            this.prTabelle.getColumnModel().getColumn(1).setWidth(75);

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

                        AtlProbenahmen probe = SielhautBearbeiten.this.probeModel
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

            private void initializeData() {
                TimeSeries series;
                APosDataItem item;

                for (int i = 0; i < this.col1.getSeriesCount(); i++) {
                    series = this.col1.getSeries(i);
                    for (int j = 0; j < series.getItemCount(); j++) {
                        item = (APosDataItem) series.getDataItem(j);

                        if (!this.dateList.contains(item.getMinute())) {
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
                        tmp = this.col1.getSeriesName(seriesIndex)
                            + ", "
                            + this.col1.getSeries(seriesIndex)
                                .getRangeDescription();
                    } else if (this.col2 != null) {
                        tmp = this.col2.getSeriesName(series2Index)
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
            JPanel buttonBar = ButtonBarFactory.buildOKCancelBar(
                this.speichernButton, this.abbrechenButton);
            tmp.add(buttonBar, BorderLayout.SOUTH);
            tmp.setBorder(Borders.TABBED_DIALOG_BORDER);

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
            this.chartPanel.setBorder(Borders.DIALOG_BORDER);

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
            tabellenScroller.setBorder(Borders.DIALOG_BORDER);

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
        List<AtlParameter> selectedParams = new ArrayList<AtlParameter>();

        if (getBleiCheck().isSelected()) {
            selectedParams.add(AtlParameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_BLEI));
        }
        if (getCadmiumCheck().isSelected()) {
            selectedParams.add(AtlParameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_CADMIUM));
        }
        if (getChromCheck().isSelected()) {
            selectedParams.add(AtlParameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_CHROM));
        }
        if (getKupferCheck().isSelected()) {
            selectedParams.add(AtlParameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_KUPFER));
        }
        if (getNickelCheck().isSelected()) {
            selectedParams.add(AtlParameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_NICKEL));
        }
        if (getQuecksilberCheck().isSelected()) {
            selectedParams.add(AtlParameter.findById(
                DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER));
        }
        if (getZinkCheck().isSelected()) {
            selectedParams.add(AtlParameter.findById(
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
                selectedParams.add(AtlParameter.findById(paramID));
            }
        }

        createSeries(selectedParams, this.sprobePkt, von, bis, col);
        selectedParams.clear();

        return col;
    }

    private void createSeries(
        List<AtlParameter> params, AtlProbepkt pkt, Date von, Date bis,
        TimeSeriesCollection col) {
        if (pkt != null) {
            for (AtlParameter param : params) {
                List<AtlAnalyseposition> list =
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
            this.fotoRtPanel = new RetractablePanel(DefaultComponentFactory
                .getInstance().createSeparator("Foto"), fotoPanel, false, null) {
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

            this.kartenRtPanel = new RetractablePanel(DefaultComponentFactory
                .getInstance().createSeparator("Kartenausschnitt"),
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
                    String rechtswertAusZeile = tmp[2];
                    String hochwertAusZeile = tmp[3];
                    this.spRechtsWertFeld.setText(rechtswertAusZeile.substring(
                        0, 7));
                    this.spHochWertFeld.setText(hochwertAusZeile
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

/**
 * Ein TableModel für eine Tabelle mit Probenahmen zu einem Sielhautpunkt.
 * @author David Klotz
 */
class SielhautProbeModel extends ListTableModel {
    private static final long serialVersionUID = -7308141358160583962L;
    private AtlProbepkt probepkt;
    private Map<AtlProbenahmen, List<AtlAnalyseposition>> wertMap;
    private AtlParameter[] params;

    public SielhautProbeModel() {
        super(new String[] {"Kennnummer", "Datum"}, false, true);

        String[] paramIDs = new String[] {
            DatabaseConstants.ATL_PARAMETER_ID_BLEI,
            DatabaseConstants.ATL_PARAMETER_ID_CADMIUM,
            DatabaseConstants.ATL_PARAMETER_ID_CHROM,
            DatabaseConstants.ATL_PARAMETER_ID_KUPFER,
            DatabaseConstants.ATL_PARAMETER_ID_NICKEL,
            DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER,
            DatabaseConstants.ATL_PARAMETER_ID_ZINK
        };
        this.params = new AtlParameter[paramIDs.length];
        for (int i = 0; i < paramIDs.length; i++) {
            this.params[i] = AtlParameter.findById(paramIDs[i]);
        }

        this.columns = new String[this.params.length + 2];
        this.columns[0] = "Kennnummer";
        this.columns[1] = "Datum";
        for (int i = 0; i < this.params.length; i++) {
            if (this.params[i] != null) {
                this.columns[i + 2] = this.params[i].getBezeichnung();
            }
        }

        this.wertMap = new HashMap<AtlProbenahmen, List<AtlAnalyseposition>>();
    }

    public void setProbepunkt(AtlProbepkt probepkt) {
        this.probepkt = probepkt;
    }

    @Override
    public void updateList() {
        if (this.probepkt != null) {
            List<AtlProbenahmen> proben =
                DatabaseQuery.findProbenahmen(this.probepkt);
            setList(proben);

            // Do all the database stuff first...
            Map<AtlProbenahmen, Map<AtlParameter, AtlAnalyseposition>> bigMap =
                DatabaseQuery.getAnalysepositionen(this.probepkt);

            this.wertMap.clear();
            for (int i = 0; i < getList().size(); i++) {
                AtlProbenahmen probe = getRow(i);
                List<AtlAnalyseposition> wertList =
                    new ArrayList<AtlAnalyseposition>(this.params.length);

                for (int j = 0; j < this.params.length; j++) {
                    wertList.add(j, bigMap.get(probe).get(this.params[j]));
                }

                this.wertMap.put((AtlProbenahmen) getList().get(i), wertList);
            }

            // fireTableDataChanged();
        }
    }

    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;
        AtlProbenahmen probe = (AtlProbenahmen) objectAtRow;

        if (columnIndex == 0) {
            value = probe.getKennummer();
        } else if (columnIndex == 1) {
            value = AuikUtils.getStringFromDate(probe.getDatumDerEntnahme());
        } else {
            List<AtlAnalyseposition> wertList = this.wertMap.get(probe);
            AtlAnalyseposition pos = wertList.get(columnIndex - 2);
            if (pos != null) {
                String tmp = pos.getWert().toString().replace(".", ",");
                value = tmp;
            } else {
                value = "-";
            }
        }

        return value;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        AtlProbenahmen removedProbe = (AtlProbenahmen) objectAtRow;
        boolean removed;

        if (removedProbe.getKennummer() != null) {
            removed = removedProbe.delete();
        } else {
            removed = true;
        }

        return removed;
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex oder <code>null</code>, falls die Zeile
     *         nicht existiert
     */
    public AtlProbenahmen getRow(int rowIndex) {
        return (AtlProbenahmen) getObjectAtRow(rowIndex);
    }
}

class SielhautChooser extends OkCancelDialog {
    private static final long serialVersionUID = -8611205076943773598L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private JTextField suchFeld;
    private JButton submitButton;
    private JTable ergebnisTabelle;

    private SielhautModel sielhautModel;
    private AtlSielhaut chosenSielhaut = null;

    public SielhautChooser(HauptFrame owner) {
        super("Sielhautpunkt auswählen", owner);

        this.sielhautModel = new SielhautModel();
        getErgebnisTabelle().setModel(this.sielhautModel);

        this.ergebnisTabelle.getColumnModel().getColumn(0)
            .setPreferredWidth(80);
        this.ergebnisTabelle.getColumnModel().getColumn(1)
            .setPreferredWidth(230);
        this.ergebnisTabelle.getColumnModel().getColumn(2).setPreferredWidth(8);
        this.ergebnisTabelle.getColumnModel().getColumn(3).setPreferredWidth(8);
        this.ergebnisTabelle.getColumnModel().getColumn(4).setPreferredWidth(8);

        setResizable(true);

        this.sielhautModel.filterList("");
        this.sielhautModel.fireTableDataChanged();
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#doOk()
     */
    @Override
    protected void doOk() {
        int row = getErgebnisTabelle().getSelectedRow();
        choose(row);
    }

    protected void doSearch() {
        final String suche = getSuchFeld().getText();

        SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                SielhautChooser.this.sielhautModel.filterList(suche);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                SielhautChooser.this.sielhautModel.fireTableDataChanged();
            }
        };

        worker.start();
    }

    private void choose(int row) {
        if (row != -1) {
            this.chosenSielhaut = (AtlSielhaut) this.sielhautModel
                .getObjectAtRow(row);
            dispose();
        }
    }

    public AtlSielhaut getChosenSielhaut() {
        return this.chosenSielhaut;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea()
     */
    @Override
    protected JComponent buildContentArea() {
        JScrollPane tabellenScroller = new JScrollPane(getErgebnisTabelle(),
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        TabAction ta = new TabAction();
        ta.addComp(this.ergebnisTabelle);
        ta.addComp(this.button1);
        // ta.addComp(button2);
        JToolBar submitToolBar = new JToolBar();
        submitToolBar.setFloatable(false);
        submitToolBar.setRollover(true);
        submitToolBar.add(getSubmitButton());

        FormLayout layout = new FormLayout("180dlu:g, 3dlu, min(16dlu;p)", // spalten
            "20dlu, 3dlu, 300dlu:g"); // zeilen
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        builder.add(getSuchFeld(), cc.xy(1, 1));
        builder.add(submitToolBar, cc.xy(3, 1));
        builder.add(tabellenScroller, cc.xyw(1, 3, 3));

        return builder.getPanel();
    }

    private JTextField getSuchFeld() {
        if (this.suchFeld == null) {
            this.suchFeld = new JTextField();

            this.suchFeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doSearch();
                }
            });

            this.suchFeld.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    String text = SielhautChooser.this.suchFeld.getText();
                    log.debug("(SielhautChooser) " + "keyChar: "
                        + e.getKeyChar() + ", Text: " + text);
                    if (Character.isLetterOrDigit(e.getKeyChar())) {
                        text = text + e.getKeyChar();
                    }
                    SielhautChooser.this.sielhautModel.filterList(text);
                    SielhautChooser.this.sielhautModel.fireTableDataChanged();
                }
            });
        }

        return this.suchFeld;
    }

    private JButton getSubmitButton() {
        if (this.submitButton == null) {
            this.submitButton = new JButton(AuikUtils.getIcon(16,
                "key_enter.png"));
            this.submitButton.setToolTipText("Suche starten");
            this.submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doSearch();
                }
            });
        }

        return this.submitButton;
    }

    private JTable getErgebnisTabelle() {
        if (this.ergebnisTabelle == null) {
            this.ergebnisTabelle = new JTable();

            Action submitAction = new AbstractAction("Auswählen") {
                private static final long serialVersionUID = 5609569229635452436L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    doOk();
                }
            };
            submitAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));

            this.ergebnisTabelle.getInputMap().put(
                (KeyStroke) submitAction.getValue(Action.ACCELERATOR_KEY),
                submitAction.getValue(Action.NAME));
            this.ergebnisTabelle.getActionMap().put(
                submitAction.getValue(Action.NAME), submitAction);

            this.ergebnisTabelle.addFocusListener(TableFocusListener
                .getInstance());
            this.ergebnisTabelle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = SielhautChooser.this.ergebnisTabelle
                            .rowAtPoint(origin);
                        choose(row);
                    }
                }
            });
        }

        return this.ergebnisTabelle;
    }

}

class SielhautModel extends ListTableModel {
    private static final long serialVersionUID = -5313844117284881446L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public SielhautModel() {
        super(new String[] {"Bezeichnung", "Lage", "R", "F", "N"}, false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
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
                if (spunkt.getPSielhaut() == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean(spunkt.getPSielhaut());
                }
                break;
            case 3:
                if (spunkt.getPFirmenprobe() == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean(spunkt.getPFirmenprobe());
                }
                break;
            case 4:
                if (spunkt.getPNachprobe() == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean(spunkt.getPNachprobe());
                }
                break;
            default:
                tmp = "FEHLER!";
                break;
        }

        // This is too slooow... :(
//        if (tmp instanceof String && spunkt.getBasisObjekt().getInaktiv()) {
//            tmp = StringUtils.setStrike((String) tmp);
//        }

        return tmp;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > 1) {
            return Boolean.class;
        } else {
            return super.getColumnClass(columnIndex);
        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#updateList()
     */
    @Override
    public void updateList() {
    }

    public void filterList(String suche) {
        setList(DatabaseQuery.findSielhaut(suche));
        log.debug("Suche nach '" + suche + "' (" + getList().size()
            + " Ergebnisse)");
    }
}
