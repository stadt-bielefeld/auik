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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
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
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ReportManager;
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
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.FormattedDate;
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
    private Action probeSaveAction;

    // Widgets für Fotopanel
    private JLabel fotoLabel;
    //private ImageIcon fotoBild;

    // Widgets für Kartenpanel
    private JLabel kartenLabel;
    //private ImageIcon kartenBild;

    private AtlSielhaut spunkt;
    private AtlProbepkt sprobePkt;
    private BasisObjekt objekt;
    private BasisStandort standort;
    private BasisBetreiber betreiber;
    private BasisObjektarten art;
    private SielhautProbeModel probeModel;

    //Auswertung
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
    private int pkt;
    private TimeSeriesCollection dataSet1;
    private JList auswahlList;



    @Override
    public void show() {
        super.show();


        if (manager.getSettingsManager().getSetting("auik.imc.edit_object") != null) {
            objekt = BasisObjekt.getObjekt(new Integer(manager.getSettingsManager().getIntSetting("auik.imc.edit_object")));
            manager.getSettingsManager().removeSetting("auik.imc.edit_object");
            sprobePkt = AtlProbepkt.getProbepunktByObjekt(objekt);
            spunkt = AtlSielhaut.getSielhaut(sprobePkt.getAtlSielhaut().getId());
            setSielhautPunkt(spunkt);
        }
        else if (BasisObjekt.getObjekt(24856) != null){
            objekt = BasisObjekt.getObjekt(24856);
            sprobePkt = AtlProbepkt.getProbepunktByObjekt(objekt);
            spunkt = AtlSielhaut.getSielhaut(sprobePkt.getAtlSielhaut().getId());
            setSielhautPunkt(spunkt);
        }

    }

    public void setSielhautPunkt(AtlSielhaut sp) {
        spunkt = sp;
        if (spunkt.getId() != null) {
            sprobePkt = AtlProbepkt.getSielhautProbepunkt(spunkt);
            getPrAnlegenButton().setEnabled(true);
            getTabelleExportButton().setEnabled(true);
        } else {
            objekt = new BasisObjekt();
            standort = BasisStandort.getStandort(41);
            betreiber = BasisBetreiber.getBetreiber(3);
            art = BasisObjektarten.getObjektart(32);
            objekt.setBasisStandort(standort);
            objekt.setBasisBetreiber(betreiber);
            objekt.setBasisObjektarten(art);
            sprobePkt = new AtlProbepkt();
            sprobePkt.setAtlProbeart(AtlProbeart.getProbeart(AtlProbeart.SIELHAUT));
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

        getSpRechtsWertFeld().setValue(spunkt.getRechtswert());
        getSpHochWertFeld().setValue(spunkt.getHochwert());

        getSpHaltungsnrFeld().setText(spunkt.getHaltungsnr());
        getSpAlarmplannrFeld().setText(spunkt.getAlarmplannr());

        if (spunkt.getPsielhaut() == null){
            getSpSielhautCheck().setSelected(false);
        }
        else
        getSpSielhautCheck().setSelected(spunkt.getPsielhaut());

        if (spunkt.getPnachprobe() == null){
            getSpNachprobeCheck().setSelected(false);
        }
        else
        getSpNachprobeCheck().setSelected(spunkt.getPnachprobe());

        if (spunkt.getPfirmenprobe() == null){
            getSpFirmenprobeCheck().setSelected(false);
        }
        else
        getSpFirmenprobeCheck().setSelected(spunkt.getPfirmenprobe());

        probeModel.setProbepunkt(sprobePkt);

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
     * Speichert ein neu angelegtes Probenahmepunkt-Objekt.
     */
    public boolean saveObjekt()  {
        boolean saved = false;

        objekt = BasisObjekt.saveBasisObjekt(objekt);

        saved = true;

        return saved;
    }

    /**
     * Speichert einen neu angelegten Probenahmepunkt.
     */
    public boolean saveProbepunkt(BasisObjekt objekt)  {
        boolean saved = false;

        objekt = BasisObjekt.getObjekt(objekt.getObjektid());
        sprobePkt.setBasisObjekt(objekt);
        spunkt = AtlSielhaut.getSielhaut(spunkt.getId());
        sprobePkt.setAtlSielhaut(spunkt);
        AtlProbepkt.saveProbepunkt(sprobePkt);

        saved = true;

        return saved;
    }

    /**
     * Speichert den aktuellen SielhautBearbeiten-Punkt.
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

            // SielhautBearbeiten, Nachprobe & Alarmplan
            spunkt.setPsielhaut(getSpSielhautCheck().isSelected());
            spunkt.setPnachprobe(getSpNachprobeCheck().isSelected());
            spunkt.setPfirmenprobe(getSpFirmenprobeCheck().isSelected());

            if (saveObjekt()) {
                if (AtlSielhaut.saveSielhautPunkt(spunkt)) {
                    if (saveProbepunkt(objekt)) {
                        frame.changeStatus(
                                "Sielhaut-Messpunkt erfolgreich gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                        setSielhautPunkt(spunkt);
                    }
                }
            } else {
                frame.changeStatus(
                        "Sielhaut-Messpunkt konnte nicht gespeichert werden!",
                        HauptFrame.ERROR_COLOR);
            }
        }
    }

    public void showReport() throws EngineException {
        if (spunkt.getId() != null || spunkt.getHaltungsnr() != null) {
            ReportManager.getInstance().startReportWorker("SielhautBearbeiten", spunkt.getId(), spunkt.getBezeichnung(), punktPrintButton);
        } else {
            log.debug("Dem zu druckenden Sielhaut-Probenahmepunkt fehlen Eingaben!");
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
                Timestamp datum = (new Timestamp(getPrDateChooser().getDate().getTime()));

                boolean exists = AtlProbenahmen.probenahmeExists(kennNummer);

                if (!exists) {
                    AtlProbenahmen probe = new AtlProbenahmen();
                    probe.setKennummer(kennNummer);
                    probe.setDatumDerEntnahme((Timestamp) datum);
                    probe.setAtlAnalysepositionen(
                        new HashSet<AtlAnalyseposition>());
                    probe.setAtlProbepkt(sprobePkt);
                    probe.setArt("Sielhaut");

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


    /**
     * Speichert eine ProbenTabelle.
     */
    public void saveTabelle() {
        File exportDatei = getFrame().saveFile(new String[]{"csv"});
        if (exportDatei != null) {
            String ext = AuikUtils.getExtension(exportDatei);

            if (ext == null) {
                String newExt;
                if (exportDatei.getName().endsWith(".")) {
                    newExt = "csv";
                } else {
                    newExt = ".csv";
                }
                exportDatei = new File(exportDatei.getParent(), exportDatei.getName()+newExt);
            }

            boolean doIt = false;
            if (exportDatei.exists()) {
                boolean answer = getFrame().showQuestion(
                        "Soll die vorhandene Datei "+exportDatei.getName()+" wirklich überschrieben werden?",
                        "Datei bereits vorhanden!");
                if (answer && exportDatei.canWrite()) {
                    doIt = true;
                }
            } else if (exportDatei.getParentFile().canWrite()) {
                doIt = true;
            }

            if (doIt) {
                log.debug("Speichere nach '" + exportDatei.getName()
                		+ "' (Ext: '"+ext+"') in '" + exportDatei.getParent()
                		+ "' !");
                if (AuikUtils.exportTableDataToCVS(getPrTabelle(), exportDatei)) {
                    log.debug("Speichern erfolgreich!");
                } else {
                    log.debug("Fehler beim Speichern!");
                    getFrame().showErrorMessage("Beim Speichern der Datei '"+exportDatei+"' trat ein Fehler auf!");
                }
            }
        }
    }


    private void updateProbeListe() {
        SwingWorkerVariant worker = new SwingWorkerVariant(getPrTabelle()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                probeModel.updateList();
                log.debug("Liste geupdatet!");
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                probeModel.fireTableDataChanged();
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

    private Action getProbeSaveAction() {
        if (probeEditAction == null) {
            probeEditAction = new AbstractAction("Speichern") {
                private static final long serialVersionUID = 6708317220554908069L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    saveTabelle();
                }
            };
            probeEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
            probeEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
        }

        return probeSaveAction;
    }

    private Action getProbeLoeschAction() {
        if (probeLoeschAction == null) {
            probeLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = -3208582919995701684L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getPrTabelle().getSelectedRow();
                    if (row != -1 && getPrTabelle().getEditingRow() == -1) {
                        AtlProbenahmen probe = probeModel.getRow(row);
                        if (frame.showQuestion("Soll die Probenahme '"+ probe.getKennummer() +"' wirklich inkl. aller Analysen gelöscht werden?", "Löschen bestätigen")) {
                            if (probeModel.removeRow(row)) {
                                frame.changeStatus("Probenahme gelöscht!", HauptFrame.SUCCESS_COLOR);
                                log.debug("Probe " + probe.getKennummer()
                                		+ " wurde gelöscht!");
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
        return frame;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
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

            builder.addLabel("Messstelle:",    cc.xy(1,1));
            builder.add(getPunktFeld(),        cc.xy(3,1));
            builder.add(getPunktToolBar(),    cc.xy(5,1));
            builder.add(datenRP,            cc.xyw(1,3,5, "f, f"));
            builder.add(getProbenRtPanel(),    cc.xyw(1,5,5, "f, f"));
            builder.add(getFotoRtPanel(),    cc.xyw(1,7,5, "f, f"));
            builder.add(getKartenRtPanel(),    cc.xyw(1,9,5, "f, f"));

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
            //punktChooseButton.setHorizontalAlignment(JButton.CENTER);
            punktChooseButton.setToolTipText("Messpunkt auswählen");

            punktChooseButton.addActionListener(new ActionListener() {
                @Override
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

    private JButton getPunktEditButton() {
        if (punktEditButton == null) {
            punktEditButton = new JButton(AuikUtils.getIcon(16, "edit.png"));
            punktEditButton.setToolTipText("Bearbeiten");
            punktEditButton.setEnabled(false);

            punktEditButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AtlProbepkt sielprobepkt = AtlProbepkt.getSielhautProbepunkt(spunkt);
                    manager.getSettingsManager().setSetting("auik.imc.edit_object", sielprobepkt.getObjektid().intValue(), false);
                    manager.switchModul("m_objekt_bearbeiten");
                }
            });
        }
        return punktEditButton;
    }

    private JButton getPunktPrintButton() {
        if (punktPrintButton == null) {
            punktPrintButton = new JButton(AuikUtils.getIcon(16, "fileprint.png"));
            //punktChooseButton.setHorizontalAlignment(JButton.CENTER);
            punktPrintButton.setToolTipText("Drucken");
            punktPrintButton.setEnabled(false);

            punktPrintButton.addActionListener(new ActionListener() {
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
        return punktPrintButton;
    }

    private JButton getPunktNeuButton() {
        if (punktNeuButton == null) {
            punktNeuButton = new JButton(AuikUtils.getIcon(16, "filenew.png"));
            //punktNeuButton.setHorizontalAlignment(JButton.CENTER);
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
            //punktSaveButton.setHorizontalAlignment(JButton.CENTER);
            punktSaveButton.setToolTipText("Speichern");
            punktSaveButton.setEnabled(false);
            punktSaveButton.addActionListener(new ActionListener() {
                @Override
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
                    "r:p, 3dlu, 150dlu, 10dlu, 70dlu, 10dlu, 100dlu",
                    "pref, " +    //1
                    "3dlu, " +    //2
                    "pref, " +    //3
                    "3dlu, " +    //4
                    "pref, " +    //5
                    "3dlu, " +    //6
                    "fill:30dlu, " +    //7
                    "10dlu, " +    //8
                    "pref, " +    //9
                    "3dlu, " +    //10
                    "pref, " +    //11
                    "3dlu, " +    //12
                    "pref, " +    //13
                    "3dlu, " +    //14
                    "pref, "    //15
            );
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();
            JScrollPane bemerkungsScroller = new JScrollPane(getSpBemerkungsArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            builder.addLabel("<html><b>Name:</b></html>", cc.xy(  1, 1 ));
            builder.add(getSpNamenFeld(),                 cc.xy(  3, 1 ));
            builder.addLabel("Entwässerungsgebiet:",      cc.xy(  1, 3 ));
            builder.add(getSpEntgebFeld(),                cc.xy(  3, 3 ));
            builder.addLabel("Lage:",                     cc.xy(  1, 5 ));
            builder.add(getSpLageFeld(),                  cc.xy(  3, 5 ));
            builder.addLabel("Bemerkungen:",              cc.xy(  1, 7 ));
            builder.add(bemerkungsScroller,               cc.xyw(  3, 7, 5 ));
            builder.addLabel("Rechtswert:",               cc.xy(  1, 9 ));
            builder.add(getSpRechtsWertFeld(),            cc.xy(  3, 9 ));
            builder.add(getAusAblageButton(),             cc.xywh(  5, 9, 1, 3 ));
            builder.add(getSpSielhautCheck(),             cc.xy(  7, 9 ));
            builder.addLabel("Hochwert:",                 cc.xy(  1, 11 ));
            builder.add(getSpHochWertFeld(),              cc.xy(  3, 11 ));
            builder.add(getSpFirmenprobeCheck(),          cc.xy(  7, 11 ));
            builder.addLabel("Schacht-Nr.:",              cc.xy(  1, 13 ));
            builder.add(getSpHaltungsnrFeld(),            cc.xyw(  3, 13, 3 ));
            builder.add(getSpNachprobeCheck(),            cc.xy(  7, 13 ));
            builder.addLabel("Alarmplan-Nr.:",            cc.xy(  1, 15 ));
            builder.add(getSpAlarmplannrFeld(),           cc.xyw(  3, 15, 3 ));


            //builder.getPanel().setBackground(Color.WHITE);
            //builder.getPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));
            datenPanel = builder.getPanel();
        }
        return datenPanel;
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
    private DoubleField getSpRechtsWertFeld() {
        if (spRechtsWertFeld == null) {
            spRechtsWertFeld = new DoubleField(1);
        }
        return spRechtsWertFeld;
    }
    private JCheckBox getSpSielhautCheck() {
        if (spSielhautCheck == null) {
            spSielhautCheck = new JCheckBox("Routinekontrolle");
        }
        return spSielhautCheck;
    }

    // Proben
    private RetractablePanel getProbenRtPanel() {
        if (probenRtPanel == null) {
            FormLayout layout = new FormLayout(

                    "p, 4dlu, p:g, 7dlu, p, 4dlu, max(60dlu;p), 7dlu,max(60dlu;p),7dlu,max(60dlu;p) "
            );
            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();

            builder.appendRow("f:65dlu:g");
            builder.append(new JScrollPane(getPrTabelle()), 11);

            builder.appendSeparator("Neue Probenahme");
            builder.append(getNeuProbPanel());
            //builder.append("Kennummer:", getPrNummerFeld());
            //builder.append("Datum:", getPrDateChooser());
            //builder.append(getPrAnlegenButton());
            //builder.append(getTabelleExportButton());
            builder.nextLine();
            builder.appendSeparator("Auswertung");
            builder.append(getAuswertungPanel());



            JPanel probenPanel = builder.getPanel();
            probenRtPanel = new RetractablePanel(
                    DefaultComponentFactory.getInstance()
                        .createSeparator("Probenahmen"),
                    probenPanel, false, null) {
                private static final long serialVersionUID = -6231371376662899465L;

                @Override
                public void opening() {
                    SwingWorkerVariant worker = new SwingWorkerVariant(getSpNamenFeld()) {
                        @Override
                        protected void doNonUILogic() throws RuntimeException {
                            probeModel.updateList();
                        }

                        @Override
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
    private JPanel getNeuProbPanel() {
        if (neuProbPanel == null) {
            FormLayout layout = new FormLayout(
                    "pref, 5dlu,  pref, 5dlu, pref, 5dlu,  pref, 5dlu, pref, 5dlu,pref, 5dlu, pref, 5dlu,pref, 5dlu,pref, 5dlu,  pref, 5dlu, pref, 5dlu,  " +
                    "pref, 5dlu, pref, 5dlu,pref,pref, 5dlu, pref, 5dlu,pref",

                    "pref, 3dlu" +", "+
                            "pref");
            CellConstraints cc = new CellConstraints();
            CellConstraints cc2 = (CellConstraints) cc.clone();

            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();


            builder.add(new JLabel("Kennummer:"),        cc.xy(  1, 1),
                    getPrNummerFeld(),        cc2.xyw( 3, 1, 20));
            builder.add(new JLabel("Datum:"),        cc.xy(  25, 1),
                    getPrDateChooser(),        cc2.xy( 28, 1));

            builder.add(getPrAnlegenButton(),        cc.xy( 30, 1));
            builder.add(getTabelleExportButton(),        cc.xy( 32, 1));


                neuProbPanel = builder.getPanel();

        }
        return neuProbPanel;
    }

    private JPanel getAuswertungPanel() {
        if (auswertungPanel == null) {
            FormLayout layout = new FormLayout(
                    "pref, 5dlu,  pref, 5dlu, pref, 5dlu,  pref, 5dlu, pref, 5dlu,pref, 5dlu, pref, 5dlu,pref, 5dlu,pref, 5dlu,  pref, 5dlu, pref, 5dlu,  " +
                    "pref, 5dlu, pref, 5dlu,pref",

                    "pref, 3dlu" +", "+
                            "pref");
            CellConstraints cc = new CellConstraints();
            CellConstraints cc2 = (CellConstraints) cc.clone();

            DefaultFormBuilder builder = new DefaultFormBuilder(layout);
            builder.setDefaultDialogBorder();

            builder.add(new JLabel("Von:"),    cc.xy(  1, 1),
                    getVonDateChooser(),       cc2.xy( 3, 1));
            builder.add(new JLabel("Bis:"),    cc.xy(  5, 1),
                    getBisDateChooser(),       cc2.xy( 7, 1));

            builder.add(getBleiCheck(),        cc.xy( 11, 1));
            builder.add(getCadmiumCheck(),     cc.xy( 13, 1));
            builder.add(getChromCheck(),       cc.xy( 15, 1));
            builder.add(getKupferCheck(),      cc.xy( 17, 1));
            builder.add(getNickelCheck(),      cc.xy( 19, 1));
            builder.add(getQuecksilberCheck(), cc.xy( 21, 1));
            builder.add(getZinkCheck(),        cc.xy( 23, 1));
            builder.add(getSubmitButton(),     cc.xy( 27, 1));

                auswertungPanel = builder.getPanel();

        }
        return auswertungPanel;
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
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = getPrTabelle().rowAtPoint(origin);

                        AtlProbenahmen probe = probeModel.getRow(row);
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
         * @author David Klotz
         */
        private class DialogListener extends WindowAdapter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == abbrechenButton) {
                    doAbbrechen();
                } else if (e.getSource() == speichernButton) {
                    doSpeichern();
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // Wenn der Dialog geschlossen wird, wird das Bearbeiten abgebrochen
                frame.clearStatus();
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

            public ExportTableModel(TimeSeriesCollection col1, TimeSeriesCollection col2) {
                this.col1 = col1;
                this.col2 = col2;
                dateList = new ArrayList<Minute>();

                initializeData();
            }

            private void initializeData() {
                TimeSeries series;
                APosDataItem item;

                for (int i = 0; i < col1.getSeriesCount(); i++) {
                    series = col1.getSeries(i);
                    for (int j = 0; j < series.getItemCount(); j++) {
                        item = (APosDataItem) series.getDataItem(j);

                        if (!dateList.contains(item.getMinute())) {
                            dateList.add(item.getMinute());
                        }
                    }
                }

                if (col2 != null) {
                    for (int i = 0; i < col2.getSeriesCount(); i++) {
                        series = col2.getSeries(i);
                        for (int j = 0; j < series.getItemCount(); j++) {
                            item = (APosDataItem) series.getDataItem(j);
                            //count++;
                            if (!dateList.contains(item.getMinute())) {
                                dateList.add(item.getMinute());
                            }
                        }
                    }
                }

                Collections.sort(dateList);
            }

            @Override
            public int getColumnCount() {
                return col1.getSeriesCount() + ((col2 != null) ? col2.getSeriesCount() : 0) + 1;//2;
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
                int series2Index = seriesIndex - col1.getSeriesCount();
                int itemIndex = rowIndex;// - 1;

                Minute min = dateList.get(itemIndex);
                if (columnIndex == 0) {
                    Date date = new Date(min.getFirstMillisecond());
                    tmp = AuikUtils.getStringFromDate(date);
                } else {
                    APosDataItem item = null;
                    if (seriesIndex < col1.getSeriesCount()) {
                        item = (APosDataItem) col1.getSeries(seriesIndex).getDataItem(min);
                    } else if (col2 != null) {
                        item = (APosDataItem) col2.getSeries(series2Index).getDataItem(min);
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
                int series2Index = seriesIndex - col1.getSeriesCount();

                 if (column == 0) {
                    tmp = "Datum";
                } else {
                    if (seriesIndex < col1.getSeriesCount()) {
                        tmp = col1.getSeriesName(seriesIndex) + ", " + col1.getSeries(seriesIndex).getRangeDescription();
                    } else if (col2 != null) {
                        tmp = col2.getSeriesName(series2Index) + ", " + col2.getSeries(series2Index).getRangeDescription();
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

        public AuswertungsDialog  (String title, TimeSeriesCollection leftDataset, TimeSeriesCollection rightDataset, HauptFrame owner)  {
            super( owner, title + "-Auswertung", true);
            this.owner = owner;
            this.title = title;

            this.leftDataset = leftDataset;
            this.rightDataset = rightDataset;

            listener = new DialogListener();

            this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(listener);

            speichernButton = new JButton("Speichern");
            speichernButton.addActionListener(listener);
            abbrechenButton = new JButton("Schließen");
            abbrechenButton.addActionListener(listener);

            JPanel tmp = new JPanel(new BorderLayout(0,7));

            tmp.add(initializeContent(), BorderLayout.CENTER);
            JPanel buttonBar = ButtonBarFactory.buildOKCancelBar(speichernButton, abbrechenButton);
            tmp.add(buttonBar, BorderLayout.SOUTH);
            tmp.setBorder(Borders.TABBED_DIALOG_BORDER);

            this.setContentPane(tmp);
            this.pack();
            this.setLocationRelativeTo(frame);
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
            chartPanel.setBorder(Borders.DIALOG_BORDER);

            return chartPanel;
        }

        private JComponent createTabellenPanel() {
            exportTable = new JTable(new ExportTableModel(leftDataset, rightDataset));
            exportTable.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            exportTable.setColumnSelectionAllowed(true);
            exportTable.setRowSelectionAllowed(true);
            exportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            exportTable.addMouseListener(new MouseAdapter() {
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
                if (i == 0 ) {//|| i == 1) {
                    column.setCellRenderer(zentrierterRenderer);
                    column.setPreferredWidth(75);
                } else {
                    column.setCellRenderer(rechtsBuendigRenderer);
                    column.setPreferredWidth(90);
                }
            }

            JScrollPane tabellenScroller = new JScrollPane(exportTable,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            tabellenScroller.setBorder(Borders.DIALOG_BORDER);

            return tabellenScroller;
        }

        public void saveTabelle() {
            frame.clearStatus();
            File exportDatei;
            String[] csv = new String []{"csv"};

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
                    exportDatei = new File(exportDatei.getParent(), exportDatei.getName()+newExt);
                }

                boolean doIt = false;
                if (exportDatei.exists()) {
                    boolean answer = owner.showQuestion(
                            "Soll die vorhandene Datei "+exportDatei.getName()+" wirklich überschrieben werden?",
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
                    if (AuikUtils.exportTableDataToCVS(exportTable, exportDatei)) {
                        owner.showInfoMessage("Speichern der CSV-Datei erfolgreich!", "Speichern erfolgreich");
                    } else {
                        log.debug("Beim Speichern der Datei '" + exportDatei
                        		+ "' trat ein Fehler auf!");
                        owner.showErrorMessage("Beim Speichern der Datei '"+exportDatei+"' trat ein Fehler auf!");
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
            frame.clearStatus();
            this.dispose();
        }

        public void doSpeichern() {
            frame.clearStatus();
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
                dataSet1 = createDataset();
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {

                if (dataSet1.getSeriesCount() > 0) {

                    AuswertungsDialog dialog = new AuswertungsDialog("SielhautBearbeiten", dataSet1, null, frame );

                    dialog.setVisible(true);
                } else {
                    frame.changeStatus("Keine Parameter ausgewählt!");
                    }
            }

        };

        frame.changeStatus("Bereite Auswertung vor...");
        worker.start();

    }

    private TimeSeriesCollection createDataset() {
        TimeSeriesCollection col = new TimeSeriesCollection();

        int parameterAnzahl;

        JList paramList;
        Date von = getVonDateChooser().getDate();
        Date bis = getBisDateChooser().getDate();
        DefaultListModel leftModel = (DefaultListModel) getAuswahlList().getModel();

        if (getBleiCheck().isSelected()) {
            leftModel.addElement("Blei (Pb)");
        }
        if (getCadmiumCheck().isSelected()) {
            leftModel.addElement("Cadmium (Cd)");
        }
        if (getChromCheck().isSelected()) {
            leftModel.addElement("Chrom (Cr)");
        }
        if (getKupferCheck().isSelected()) {
            leftModel.addElement("Kupfer (Cu)");
        }
        if (getNickelCheck().isSelected()) {
            leftModel.addElement("Nickel (Ni)");
        }
        if (getQuecksilberCheck().isSelected()) {
            leftModel.addElement("Quecksilber (Hg)");
        }
        if (getZinkCheck().isSelected()) {
            leftModel.addElement("Zink (Zn)");
        }
        paramList = getAuswahlList();

        pkt = sprobePkt.getObjektid();
        parameterAnzahl = getAuswahlList().getModel().getSize();

        // Wenn keine Check Box angeklickt wurde sollen alle Paramater berücksichtig werden

        if (parameterAnzahl == 0) {
            leftModel.addElement("Blei (Pb)");
            leftModel.addElement("Cadmium (Cd)");
            leftModel.addElement("Chrom (Cr)");
            leftModel.addElement("Kupfer (Cu)");
            leftModel.addElement("Nickel (Ni)");
            leftModel.addElement("Quecksilber (Hg)");
            leftModel.addElement("Zink (Zn)");
        }

        createSeries(paramList, pkt , von, bis, col);
        leftModel.clear();

        return col;
    }

    private JList getAuswahlList() {
        if (auswahlList == null) {
            DefaultListModel listModel = new DefaultListModel();
            auswahlList = new JList(listModel);
            auswahlList.setPrototypeCellValue("Abcdefghij (Ab)");

            auswahlList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        return auswahlList;
    }



    private void createSeries( JList paramList, Integer pkt, Date von, Date bis,
        TimeSeriesCollection col) {
        String einheit;

        if (pkt != null) {

            for (int i = 0; i < paramList.getModel().getSize(); i++) {

                String p =(String) paramList.getModel().getElementAt(i);


//                AtlAnalyseposition position;
//                position = AtlAnalyseposition.getAnalysepositionObjekt(pkt);
                einheit =  "Verhältnis zum Hintergrundwert";


                List list = AtlAnalyseposition.getSielhautpos(p, pkt, von, bis);


                TimeSeries series = ChartDataSets
                        .createAnalysePositionenSielhautSeries(list, p+ " ",einheit);
                col.addSeries(series);
            }
        }
        frame.changeStatus("Auswertung abgeschlossen");
    }

    private JDateChooser getVonDateChooser() {
        if (vonDateChooser == null) {
            vonDateChooser = new JDateChooser(FormattedDate.DEFAULT, false);
        }

        return vonDateChooser;
    }

    private JDateChooser getBisDateChooser() {

        if (bisDateChooser == null) {
            bisDateChooser = new JDateChooser(FormattedDate.DEFAULT, false);
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
            prDateChooser = new JDateChooser(FormattedDate.DEFAULT, false);
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
            fotoRtPanel = new RetractablePanel(
                    DefaultComponentFactory.getInstance()
                        .createSeparator("Foto"),
                    fotoPanel, false, null) {
                private static final long serialVersionUID = 6505102322099919490L;

                @Override
                public void opening() {
                    if (spunkt != null && spunkt.getId() != null) {
                        String imgPath = manager.getSettingsManager().getSetting("auik.system.spath_fotos") + spunkt.getBezeichnung() + ".jpg";
                        File imgFile = new File(imgPath);
                        if (imgFile.canRead()) {
                            ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
                            int panelWidth = getPanel().getWidth() - 50;
                            if (imgIcon.getIconWidth() > panelWidth) {
                                imgIcon.setImage(imgIcon.getImage().getScaledInstance(panelWidth,-1,Image.SCALE_FAST));
                            }
                            getFotoLabel().setIcon(null);
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
                private static final long serialVersionUID = 1276454146798307743L;

                @Override
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
                    String rechtswertAusZeile = tmp[2];
                    String hochwertAusZeile = tmp[3];
                    spRechtsWertFeld.setText(rechtswertAusZeile.substring(0, 7));
                    spHochWertFeld.setText(hochwertAusZeile.substring(0, 7));
                    frame.changeStatus("Rechts- und Hochwert eingetragen",
                            HauptFrame.SUCCESS_COLOR);
                } else {
                    frame.changeStatus(
                            "Zwischenablage enthält keine verwertbaren Daten",
                            HauptFrame.ERROR_COLOR);
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

/**
 * Ein TableModel für eine Tabelle mit Probenahmen zu einem Sielhautpunkt.
 * @author David Klotz
 */
class SielhautProbeModel extends ListTableModel {
    private static final long serialVersionUID = -7308141358160583962L;
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
            if (params[i] != null) {
                columns[i + 2] = params[i].toString();
            }
        }

        wertMap = new HashMap();
    }

    public void setProbepunkt(AtlProbepkt probepkt) {
        this.probepkt = probepkt;
    }

    @Override
    public void updateList() {
        if (probepkt != null) {
            setList(AtlProbenahmen.getProbenahmen(probepkt, true, -1));

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

    @Override
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

        sielhautModel = new SielhautModel();
        getErgebnisTabelle().setModel(sielhautModel);


        ergebnisTabelle.getColumnModel().getColumn(0).setPreferredWidth(80);
        ergebnisTabelle.getColumnModel().getColumn(1).setPreferredWidth(230);
        ergebnisTabelle.getColumnModel().getColumn(2).setPreferredWidth(8);
        ergebnisTabelle.getColumnModel().getColumn(3).setPreferredWidth(8);
        ergebnisTabelle.getColumnModel().getColumn(4).setPreferredWidth(8);

        setResizable(true);

        sielhautModel.filterList("");
        sielhautModel.fireTableDataChanged();
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
                sielhautModel.filterList(suche);
            }

            @Override
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
    @Override
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
                "180dlu:g, 3dlu, min(16dlu;p)",        // spalten
                "20dlu, 3dlu, 300dlu:g");     // zeilen
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        builder.add(getSuchFeld(),        cc.xy(1, 1));
        builder.add(submitToolBar,        cc.xy(3, 1));
        builder.add(tabellenScroller,    cc.xyw(1, 3, 3));

        return builder.getPanel();
    }

    private JTextField getSuchFeld() {
        if (suchFeld == null) {
            suchFeld = new JTextField();

            suchFeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doSearch();
                }
            });

            suchFeld.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    String text = suchFeld.getText();
                    log.debug("(SielhautChooser) " + "keyChar: "
                    		+ e.getKeyChar() + ", Text: " + text);
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
                @Override
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
                private static final long serialVersionUID = 5609569229635452436L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    doOk();
                }
            };
            submitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));

            ergebnisTabelle.getInputMap().put((KeyStroke)submitAction.getValue(Action.ACCELERATOR_KEY), submitAction.getValue(Action.NAME));
            ergebnisTabelle.getActionMap().put(submitAction.getValue(Action.NAME), submitAction);

            ergebnisTabelle.addFocusListener(TableFocusListener.getInstance());
            ergebnisTabelle.addMouseListener(new MouseAdapter() {
                @Override
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
    private static final long serialVersionUID = -5313844117284881446L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public SielhautModel() {
        super(new String[]{"Bezeichnung", "Lage", "R", "F", "N"}, false);
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
            if (spunkt.getPsielhaut() == null) {
                tmp = new Boolean(false);
            } else {
                tmp = new Boolean(spunkt.getPsielhaut());
            }
            break;
        case 3:
            if (spunkt.getPfirmenprobe() == null) {
                tmp = new Boolean(false);
            } else {
                tmp = new Boolean(spunkt.getPfirmenprobe());
            }
            break;
        case 4:
            if (spunkt.getPnachprobe() == null) {
                tmp = new Boolean(false);
            } else {
                tmp = new Boolean(spunkt.getPnachprobe());
            }
            break;

        default:
            tmp = "FEHLER!";
            break;
        }

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
        setList(AtlSielhaut.findPunkte(suche));
        log.debug("Suche nach '" + suche + "' (" + getList().size()
        		+ " Ergebnisse)");
    }
}
