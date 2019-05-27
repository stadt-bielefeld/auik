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
 * $Id: BasisLageSuchen.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
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
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Strassen;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.module.common.editors.StandortEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisObjektModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisLageModel;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.BasicEntryField;
import de.bielefeld.umweltamt.aui.utils.PDFExporter;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Ein Modul zum Suchen und Bearbeiten eines Standorts.
 * 
 * @author David Klotz, Thomas Friebe
 */
public class BasisStandortSuchen extends AbstractModul
{
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private JTextField strassenFeld;
    private JTextField hausnrFeld;
    private JTextField ortFeld;
    private JButton submitButton;
    private JButton dreiButton;
    private JButton vierButton;
    private JButton probepktButton;
    private JTable standortTabelle;
    private JTable objektTabelle;
    private JSplitPane tabellenSplit;

//	private Action standortEditAction;
    private Action standortLoeschAction;
    private Action objektNeuAction;
    private JPopupMenu standortPopup;

    private Action objektEditAction;
    private Action objektBetreiberEditAction;
    private Action objektStandortEditAction;
    private Action objektLoeschAction;
    private Action gisAction;
    private JPopupMenu objektPopup;

    private BasisLageModel standortModel;
    private BasisObjektModel objektModel;

    /**
     * Wird benutzt, um nach dem Bearbeiten etc. wieder den selben Standort in
     * der Liste auszuwählen.
     */
    private Standort lastStandort;

    private Timer suchTimer;

    private JButton reportStandortListeButton;
    protected String betreiber;
    protected String standort;
    protected int standortID;


    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName()
    {
        return "Standort suchen";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * 
     * @return "m_standort_suchen"
     */
    @Override
    public String getIdentifier()
    {
        return "m_standort_suchen";
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory()
    {
        return "Betriebe";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
     */
    @Override
    public Icon getIcon()
    {
        return super.getIcon("filefind32.png");
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
    public JPanel getPanel()
    {
        if (this.panel == null) {
            this.standortModel = new BasisLageModel();
            this.objektModel = new BasisObjektModel("Betreiber", this.manager
                    .getSettingsManager().getSetting(
                                                        "auik.prefs.abteilungsfilter"));

            TableFocusListener tfl = TableFocusListener.getInstance();
            getStandortTabelle().addFocusListener(tfl);
            getObjektTabelle().addFocusListener(tfl);

            JScrollPane standortScroller = new JScrollPane(
                    getStandortTabelle(),
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JScrollPane objektScroller = new JScrollPane(getObjektTabelle(),
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JToolBar submitToolBar = new JToolBar();
            submitToolBar.setFloatable(false);
            submitToolBar.setRollover(true);
            submitToolBar.add(getSubmitButton());

            PanelBuilder restrictBuilder = new PanelBuilder();
            restrictBuilder.setFill(GridBagConstraints.HORIZONTAL);
            restrictBuilder.setAnchor(GridBagConstraints.WEST);
            restrictBuilder.setInsets(new Insets(0, 0, 0, 15));
            restrictBuilder.setWeightX(0);
            restrictBuilder.addComponent(new JLabel("Objekte einschränken:"));
            restrictBuilder.addComponents(getDreiButton(), getVierButton(), getProbepktButton());
            restrictBuilder.setWeightX(1);
            restrictBuilder.addComponent(new JPanel());
            restrictBuilder.setWeightX(0);
            restrictBuilder.setAnchor(GridBagConstraints.EAST);
            restrictBuilder.setInsets(new Insets(0, 0, 0, 0));
            restrictBuilder.addComponent(getReportListeButton(), true);
            JPanel restrictPanel = restrictBuilder.getPanel();

            // Die Tab-Action ist in eine neue Klasse ausgelagert,
            // weil man sie evtl. öfters brauchen wird.
            TabAction ta = new TabAction();
            ta.addComp(getStrassenFeld());
            ta.addComp(getHausnrFeld());
            ta.addComp(getStandortTabelle());
            ta.addComp(getObjektTabelle());

            this.tabellenSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                    standortScroller, objektScroller);
            this.tabellenSplit.setDividerLocation(0.6);

            PanelBuilder builder = new PanelBuilder();
            builder.setBorder(new EmptyBorder(10, 15, 15, 15));
            builder.setInsets(new Insets(10, 0, 0, 10));
            builder.setFill(GridBagConstraints.HORIZONTAL);
            builder.setAnchor(GridBagConstraints.NORTHWEST);
            builder.setWeightY(0.1);
            builder.setWeightX(0.35);
            builder.addComponent(getStrassenFeld(), "Straße:");
            builder.setWeightX(0.2);
            builder.addComponent(getHausnrFeld(), "Haus-Nr:");
            builder.setWeightX(0.35);
            builder.addComponent(getOrtFeld(), "Ort:");
            builder.setWeightX(0);
            builder.setAnchor(GridBagConstraints.EAST);
            builder.setInsets(new Insets(0, 0, 5, 0));
            builder.addComponent(submitToolBar, true);
            builder.setWeightY(0);
            builder.addComponent(this.tabellenSplit, true);
            builder.setWeightY(0.1);
            builder.addComponent(restrictPanel, true);
            this.panel = builder.getPanel();
        }
        return this.panel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.bielefeld.umweltamt.aui.Modul#show()
     */

    @Override
    public void show()
    {
        super.show();

        // Gespeicherte Position des Dividers setzen
        if (SettingsManager.getInstance().getSetting(
                                                        "auik.prefs.divloc_standort") != null)
        {
            double divloc = Double.parseDouble(SettingsManager.getInstance()
                    .getSetting("auik.prefs.divloc_standort"));
            // AUIKataster.debugOutput("Lese divloc_standort als: " + divloc,
            // "BasisLageSuchen.DIVIDER");
            this.tabellenSplit.setDividerLocation(divloc);
        }

        this.lastStandort = null;
        updateStandortListe();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.bielefeld.umweltamt.aui.Modul#hide()
     */
    @Override
    public void hide()
    {
        super.hide();
        if (this.suchTimer != null)
        {
            getSuchTimer().stop();
        }

        // Position des Dividers des SplitPanes speichern
        if ((this.tabellenSplit != null)
                && (this.tabellenSplit.getDividerLocation() != -1))
        {
            double divloc = (float) (this.tabellenSplit.getDividerLocation())
                    / (this.tabellenSplit.getHeight());
            // AUIKataster.debugOutput("Setze divloc_standort auf: ("+
            // tabellenSplit.getDividerLocation() +"/"+
            // tabellenSplit.getHeight() +") = " + divloc,
            // "BasisLageSuchen.DIVIDER");
            if (divloc >= 0.0 && divloc <= 1.0)
            {
                SettingsManager.getInstance().setSetting(
                                                            "auik.prefs.divloc_standort", Double.toString(divloc),
                                                            true);
            }
        }
    }

    public void updateStandortListe()
    {
        log.debug("Start updateStandortListe()");
        SwingWorkerVariant worker = new SwingWorkerVariant(getStrassenFeld())
        {
            @Override
            protected void doNonUILogic()
            {
                BasisStandortSuchen.this.standortModel.updateList();
            }

            @Override
            protected void doUIUpdateLogic()
            {
                BasisStandortSuchen.this.standortModel.fireTableDataChanged();

                if (SettingsManager.getInstance().getStandort() != null)
                {
                    filterStandortListe(getStandortTabelle());
                }
                else if (BasisStandortSuchen.this.lastStandort != null)
                {
                    // Wenn der Standort noch in der Liste ist, wird er
                    // ausgewählt.
                    int row = BasisStandortSuchen.this.standortModel.getList()
                            .indexOf(BasisStandortSuchen.this.lastStandort);
                    if (row != -1)
                    {
                        getStandortTabelle().setRowSelectionInterval(row, row);
                        getStandortTabelle().scrollRectToVisible(
                        getStandortTabelle().getCellRect(row, 0, true));
                        getStandortTabelle().requestFocus();
                    }
                }
                else
                {
                    int standortCount = BasisStandortSuchen.this.standortModel
                            .getRowCount();
                    if (standortCount > 0)
                    {
                        String statusMsg = "Suche: " + standortCount
                                + " Ergebnis";
                        if (standortCount != 1)
                        {
                            statusMsg += "se";
                        }
                        statusMsg += ".";
                        BasisStandortSuchen.this.frame.changeStatus(statusMsg);
                    }
                }

                updateObjekte();
            }
        };

        worker.start();
        log.debug("End updateStandortListe()");
    }

    public void updateObjekte()
    {
        log.debug("Start updateObjekte()");
        ListSelectionModel lsm = getStandortTabelle().getSelectionModel();
        if (!lsm.isSelectionEmpty())
        {
            int selectedRow = lsm.getMinSelectionIndex();
            Standort standort = this.standortModel.getRow(selectedRow);
            log.debug("Standort " + standort + " angewählt.");
            searchObjekteByStandort(standort);
        }
        log.debug("End updateObjekte()");
    }

    /**
     * öffnet einen Dialog um einen Standort-Datensatz zu bearbeiten.
     * 
     * @param standort
     *            Der Standort
     */
    public void editStandort(Standort standort)
    {
        StandortEditor editDialog = null;

        editDialog = new StandortEditor(standort, this.frame);
        editDialog.setLocationRelativeTo(this.frame);

        editDialog.setVisible(true);

        this.lastStandort = standort;

        if (editDialog.wasSaved())
        {
            // Nach dem Bearbeiten die Liste updaten, damit unsere Änderungen
            // auch angezeigt werden.
            updateStandortListe();
        }
    }

    /**
     * öffnet einen Dialog um einen Betreiber-Datensatz zu bearbeiten.
     * @param betr Der Betreiber
     */
    public void editBetreiber(Adresse betr) {
        BetreiberEditor editDialog = new BetreiberEditor(betr, this.frame);
        editDialog.setLocationRelativeTo(this.frame);

        editDialog.setVisible(true);

    }

    /**
     * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines
     * Standorts.
     * 
     * @param standortid
     *            Die Standort-Id
     * @param abteilung
     *            33 oder 34
     */
    public void searchObjekteByStandort(final Standort standort,
        final String abteilung, final Integer nichtartid)
    {

        // ... siehe show()
        SwingWorkerVariant worker = new SwingWorkerVariant(getStandortTabelle())
        {
            @Override
            protected void doNonUILogic()
            {
                BasisStandortSuchen.this.objektModel.searchByStandort(standort,
                                                                        abteilung, nichtartid);
            }

            @Override
            protected void doUIUpdateLogic()
            {
                BasisStandortSuchen.this.objektModel.fireTableDataChanged();
            }
        };
        worker.start();
    }

    /**
     * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines
     * Standorts.
     * 
     * @param standortid
     *            Die Standort-Id
     */
    public void searchObjekteByStandort(final Standort standort)
    {

        // ... siehe show()
        SwingWorkerVariant worker = new SwingWorkerVariant(getStandortTabelle())
        {
            @Override
            protected void doNonUILogic()
            {
                BasisStandortSuchen.this.objektModel.searchByStandort(standort);
            }

            @Override
            protected void doUIUpdateLogic()
            {
                BasisStandortSuchen.this.objektModel.fireTableDataChanged();
            }
        };
        worker.start();
    }

    /**
     * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines
     * Standorts.
     * 
     * @param standortid
     *            Die Standort-Id
     */
    public void searchObjekteByStandort(final Standort standort,
        final Integer istartid)
    {

        // ... siehe show()
        SwingWorkerVariant worker = new SwingWorkerVariant(getStandortTabelle())
        {
            @Override
            protected void doNonUILogic()
            {
                BasisStandortSuchen.this.objektModel.searchByStandort(standort,
                                                                        istartid);
            }

            @Override
            protected void doUIUpdateLogic()
            {
                BasisStandortSuchen.this.objektModel.fireTableDataChanged();
            }
        };
        worker.start();
    }

    /**
     * Filtert die Standort-Liste nach Straße und Hausnummer.
     * 
     * @param focusComp
     *            Welche Komponente soll nach der Suche den Fokus bekommen.
     */
    public void filterStandortListe(Component focusComp)
    {
        log.debug("Start filterStandortListe()");
        int hausnr;
        try
        {
            hausnr = Integer.parseInt(getHausnrFeld().getText());
        }
        catch (NumberFormatException e1)
        {
            hausnr = -1;
        }
        final int fhausnr = hausnr;

        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp)
        {

            @Override
            protected void doNonUILogic()
            {
                if (SettingsManager.getInstance().getStandort() == null)
                {
                    BasisStandortSuchen.this.standortModel.filterList(
                                                                        getStrassenFeld().getText(),
                                                                        fhausnr,
                                                                        getOrtFeld()
                                                                                .getText());
                }
                else
                {
                    BasisStandortSuchen.this.standortModel
                            .filterList(Standort.findByLageId(SettingsManager.getInstance()
                                    .getStandort().getId()));
                    SettingsManager.getInstance().setStandort(null);
                    getStrassenFeld().setText("");
                    getHausnrFeld().setText("");
                }
            }

            @Override
            protected void doUIUpdateLogic()
            {
                getStandortTabelle().clearSelection();

                BasisStandortSuchen.this.standortModel.fireTableDataChanged();
                String statusMsg = "Suche: "
                        + BasisStandortSuchen.this.standortModel.getRowCount()
                        + " Ergebnis";
                if (BasisStandortSuchen.this.standortModel.getRowCount() != 1)
                {
                    statusMsg += "se";
                }
                statusMsg += ".";
                BasisStandortSuchen.this.frame.changeStatus(statusMsg);
            }
        };

        this.frame.changeStatus("Suche...");
        worker.start();
        log.debug("End filterStandortListe()");
    }

    public void showReportListe()
    {

        ListSelectionModel lsm = getStandortTabelle().getSelectionModel();
        int selectedRow = lsm.getMinSelectionIndex();

        Standort standort = this.standortModel.getRow(selectedRow);

        String adresse = "" + standort.getAdresse();

        if (standort == null)
        {
            this.frame.changeStatus("Bitte einen Standort markieren");
        }
        else
        {
            this.frame.changeStatus("PDF-Datei wird erstellt");
        }

        this.standortID = standort.getId();

        log.debug(adresse + " mit ID: " + this.standortID + " ausgewaehlt");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("Standort", adresse);
        params.put("StandortId", standort.getId());
        try
        {
            File pdfFile = File.createTempFile("VAwS_StandortListe", ".pdf");
            pdfFile.deleteOnExit();
            PDFExporter.getInstance().exportReport(params,
                                                    PDFExporter.VAWS_STANDORTLISTE, pdfFile.getAbsolutePath());
        }
        catch (Exception ex)
        {
            GUIManager.getInstance().showErrorMessage(
                                                        "Generieren der Standort Liste fehlgeschlagen." + "\n"
                                                                + ex.getLocalizedMessage(),
                                                        "Generieren der Standort Liste fehlgeschlagen");
        }
    }

    private Timer getSuchTimer()
    {
        if (this.suchTimer == null)
        {
            this.suchTimer = new Timer(900, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {

                    // Was diese ganze "SwingWorkerVariant"-Geschichte
                    // soll, steht unter
                    // http://www.javaworld.com/javaworld/jw-06-2003/jw-0606-swingworker.html
                    // Ist auch ausgedruckt im Ordner im Regal. -DK
                    SwingWorkerVariant worker = new SwingWorkerVariant(
                            getStrassenFeld())
                    {
                        protected String oldText = "";
                        private String newText = "";

                        @Override
                        protected void doNonUILogic()
                        {
                            this.oldText = getStrassenFeld().getText();
                            if (this.oldText.equals(""))
                            {
                                this.newText = "";
                            }
                            else
                            {
                                String suchText = AuikUtils
                                        .sanitizeQueryInput(this.oldText);
                                Strassen str = DatabaseQuery
                                        .findStrasse(suchText);

                                if (str != null)
                                {
                                    this.newText = str.getStrasse();
                                }
                                else
                                {
                                    this.newText = this.oldText;
                                }
                            }
                        }

                        @Override
                        protected void doUIUpdateLogic()
                        {
                            getStrassenFeld().setText(this.newText);
                            getStrassenFeld().setSelectionStart(
                                                                this.oldText.length());
                            getStrassenFeld().setSelectionEnd(
                                                                this.newText.length());
                        }
                    };
                    worker.start();
                }
            });
            this.suchTimer.setRepeats(false);
        }

        return this.suchTimer;
    }

    private JTextField getStrassenFeld()
    {
        if (this.strassenFeld == null)
        {
            this.strassenFeld = new JTextField("");
            this.strassenFeld.setFocusTraversalKeys(
                                                    KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                                                    Collections.EMPTY_SET);

            this.strassenFeld.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    getSuchTimer().stop();
                    filterStandortListe(getStandortTabelle());
                }
            });

            this.strassenFeld.addKeyListener(new KeyAdapter()
            {
                @Override
                public void keyPressed(KeyEvent e)
                {
                    if (e.getKeyCode() == KeyEvent.VK_TAB)
                    {
                        getSuchTimer().stop();
                        filterStandortListe(getHausnrFeld());
                    }
                }

                @Override
                public void keyTyped(KeyEvent e)
                {
                    if (Character.isLetterOrDigit(e.getKeyChar()))
                    {
                        if (getSuchTimer().isRunning())
                        {
                            getSuchTimer().restart();
                        }
                        else
                        {
                            getSuchTimer().start();
                        }
                    }
                }
            });
        }
        return this.strassenFeld;
    }

    private JTextField getOrtFeld()
    {
        if (this.ortFeld == null)
        {
            this.ortFeld = new JTextField("");
            this.ortFeld.setFocusTraversalKeys(
                                                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                                                Collections.EMPTY_SET);

            this.ortFeld.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    filterStandortListe(getStandortTabelle());
                }
            });
        }
        return this.ortFeld;
    }

    private JTextField getHausnrFeld()
    {
        if (this.hausnrFeld == null)
        {
            this.hausnrFeld = new BasicEntryField();

            this.hausnrFeld.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    filterStandortListe(getStandortTabelle());
                }
            });
        }
        return this.hausnrFeld;
    }

    private JButton getSubmitButton()
    {
        if (this.submitButton == null)
        {
            this.submitButton = new JButton(AuikUtils.getIcon(16,
                                                                "key_enter.png"));
            this.submitButton.setToolTipText("Suche starten");
            this.submitButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    getSuchTimer().stop();
                    filterStandortListe(getStandortTabelle());
                }
            });
        }

        return this.submitButton;
    }

    private JButton getDreiButton()
    {
        if (this.dreiButton == null)
        {
            this.dreiButton = new JButton("360.33");
            this.dreiButton.setToolTipText("nur 33er Objekt");
            this.dreiButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ListSelectionModel lsm = getStandortTabelle()
                            .getSelectionModel();
                    if (!lsm.isSelectionEmpty())
                    {
                        int selectedRow = lsm.getMinSelectionIndex();
                        Standort standort = BasisStandortSuchen.this.standortModel
                                .getRow(selectedRow);
                        log.debug("Standort " + standort + " angewählt.");
                        searchObjekteByStandort(standort,
                                                DatabaseConstants.BASIS_OBJEKTART_ABTEILUNG_33,
                                                DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT);
                    }
                }
            });
        }

        return this.dreiButton;
    }

    private JButton getVierButton()
    {
        if (this.vierButton == null)
        {
            this.vierButton = new JButton("360.34");
            this.vierButton.setToolTipText("nur 34er Objekte");
            this.vierButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ListSelectionModel lsm = getStandortTabelle()
                            .getSelectionModel();
                    if (!lsm.isSelectionEmpty())
                    {
                        int selectedRow = lsm.getMinSelectionIndex();
                        Standort standort = BasisStandortSuchen.this.standortModel
                                .getRow(selectedRow);
                        log.debug("Standort " + standort + " angewählt.");
                        searchObjekteByStandort(standort,
                                                DatabaseConstants.BASIS_OBJEKTART_ABTEILUNG_34,
                                                DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT);
                    }
                }
            });
        }

        return this.vierButton;
    }

    private JButton getProbepktButton()
    {
        if (this.probepktButton == null)
        {
            this.probepktButton = new JButton("Probepunkte");
            this.probepktButton
                    .setToolTipText("nur die Probenahmepunkte anzeigen");
            this.probepktButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ListSelectionModel lsm = getStandortTabelle()
                            .getSelectionModel();
                    if (!lsm.isSelectionEmpty())
                    {
                        int selectedRow = lsm.getMinSelectionIndex();
                        Standort standort = BasisStandortSuchen.this.standortModel
                                .getRow(selectedRow);
                        log.debug("Standort " + standort + " angewählt.");
                        searchObjekteByStandort(standort,
                                                DatabaseConstants.BASIS_OBJEKTART_ID_PROBEPUNKT);
                    }
                }
            });
        }

        return this.probepktButton;
    }

    private JButton getReportListeButton()
    {
        if (this.reportStandortListeButton == null)
        {

            this.reportStandortListeButton = new JButton("PDF-Liste generieren");
            this.reportStandortListeButton
                    .setToolTipText("Liste der VAwS-Objekte am Standort");
            this.reportStandortListeButton
                    .addActionListener(new ActionListener()
                    {

                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            showReportListe();
                        }
                        // }
                    });
        }
        return this.reportStandortListeButton;
    }

    private JTable getStandortTabelle()
    {
        if (this.standortTabelle == null)
        {
            this.standortTabelle = new JTable(this.standortModel);

            // Wir wollen wissen, wenn eine andere Zeile ausgewählt wurde
            ListSelectionModel rowSM = this.standortTabelle.getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener()
            {
                @Override
                public void valueChanged(ListSelectionEvent e)
                {
                    // überzählige Events ignorieren
                    if (e.getValueIsAdjusting())
                    {
                        return;
                    }

                    updateObjekte();
                }
            });

            this.standortTabelle
                    .setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
            TableColumn column = null;

            for (int i = 0; i < this.standortModel.getColumnCount(); i++)
            {
                column = this.standortTabelle.getColumnModel().getColumn(i);

                if (i == 0)
                {
                    column.setPreferredWidth(120);
                    // column.setCellRenderer(centerRenderer);
                }
                else if (i == 1)
                {
                    // column.setMaxWidth(70);
                    column.setPreferredWidth(80);
                }
                else if (i == 2)
                {
                    // column.setMaxWidth(70);
                    column.setPreferredWidth(80);
                }
                else if (i == 3)
                {
                    // column.setMaxWidth(70);
                    column.setPreferredWidth(200);
                }
            }

            this.standortTabelle
                    .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.standortTabelle.setColumnSelectionAllowed(false);
            this.standortTabelle.setRowSelectionAllowed(true);

            this.standortTabelle.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    Point origin = e.getPoint();
                    int row = BasisStandortSuchen.this.standortTabelle
                            .rowAtPoint(origin);

                    if (row != -1)
                    {
                        if ((e.getButton() == MouseEvent.BUTTON1)
                                && (e.getClickCount() == 2))
                        {
                            Standort bsta = BasisStandortSuchen.this.standortModel
                                    .getRow(row);
//							editStandort(bsta);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e)
                {
                    showStandortPopup(e);
                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                    showStandortPopup(e);
                }
            });

        }

        return this.standortTabelle;
    }

    private Action getObjektNeuAction()
    {
        if (this.objektNeuAction == null)
        {
            this.objektNeuAction = new AbstractAction("Neues Objekt")
            {
                private static final long serialVersionUID = 7043267119780363332L;

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int row = BasisStandortSuchen.this.standortTabelle
                            .getSelectedRow();

                    if (row != -1)
                    {
                        Standort bsta = BasisStandortSuchen.this.standortModel
                                .getRow(row);
                        BasisStandortSuchen.this.manager.getSettingsManager()
                                .setSetting("auik.imc.use_standort",
                                            bsta.getId().intValue(), false);
                        BasisStandortSuchen.this.manager
                                .switchModul("m_objekt_bearbeiten");
                    }
                }
            };
            this.objektNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(
                    KeyEvent.VK_O));
        }

        return this.objektNeuAction;
    }

    private void showStandortPopup(MouseEvent e)
    {
        if (this.standortPopup == null)
        {
            this.standortPopup = new JPopupMenu("Standort");
            
            JMenuItem gisItem = new JMenuItem(getGisAction());
            this.standortPopup.add(gisItem);
        }

        if (e.isPopupTrigger())
        {
            Point origin = e.getPoint();
            int row = this.standortTabelle.rowAtPoint(origin);

            if (row != -1)
            {
                this.standortTabelle.setRowSelectionInterval(row, row);
                this.standortPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private Action getObjektEditAction()
    {
        if (this.objektEditAction == null)
        {
            this.objektEditAction = new AbstractAction("Bearbeiten")
            {
                private static final long serialVersionUID = -3064610048336306709L;

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int row = BasisStandortSuchen.this.objektTabelle
                            .getSelectedRow();
                    Objekt obj = BasisStandortSuchen.this.objektModel
                            .getRow(row);
                    if ((row != -1)
                            || (!(obj.getObjektarten().getId()
                                    .equals(DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE))))
                    {
                        BasisStandortSuchen.this.manager.getSettingsManager()
                                .setSetting("auik.imc.edit_object",
                                            obj.getId().intValue(), false);

                        BasisStandortSuchen.this.manager
                                .switchModul("m_objekt_bearbeiten");
                    }
                    else if (row != -1
                            || obj.getObjektarten()
                                    .getId()
                                    .equals(DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE))
                    {
                        BasisStandortSuchen.this.manager.getSettingsManager()
                                .setSetting("auik.imc.edit_object",
                                            obj.getId().intValue(), false);
                        BasisStandortSuchen.this.manager
                                .switchModul("m_sielhaut1");
                    }
                }
            };
            this.objektEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                    KeyEvent.VK_B));
            this.objektEditAction.putValue(Action.ACCELERATOR_KEY,
                                            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
        }

        return this.objektEditAction;
    }

    private Action getObjektBetreiberEditAction() {
        if (this.objektBetreiberEditAction == null) {
            this.objektBetreiberEditAction = new AbstractAction("Betreiber") {
    
                private static final long serialVersionUID = 9162213813094661474L;
    
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle().getSelectedRow();
    
                    if (row != -1) {
                        Objekt obj = BasisStandortSuchen.this.objektModel
                            .getRow(row);
                        editBetreiber(obj.getBetreiberid());
                    }
                }
            };
            this.objektBetreiberEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_F1));
            this.objektBetreiberEditAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false));
        }
    
        return this.objektBetreiberEditAction;
    }

    private Action getObjektStandortEditAction() {
        if (this.objektStandortEditAction == null) {
            this.objektStandortEditAction = new AbstractAction("Standort") {
    
                private static final long serialVersionUID = 8864140472500015190L;
    
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle().getSelectedRow();
    
                    // Natürlich nur editieren, wenn wirklich eine Zeile
                    // ausgewählt ist
                    if (row != -1) {
                        Objekt obj = BasisStandortSuchen.this.objektModel
                            .getRow(row);
                        editBetreiber(obj.getStandortid().getAdresse());
                    }
                }
            };
            this.objektStandortEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_F2));
            this.objektStandortEditAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false));
        }
    
        return this.objektStandortEditAction;
    }

    private Action getObjektLoeschAction()
    {
        if (this.objektLoeschAction == null)
        {
            this.objektLoeschAction = new AbstractAction("Löschen")
            {
                private static final long serialVersionUID = 2332382771711375896L;

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int row = getObjektTabelle().getSelectedRow();
                    if (row != -1 && getObjektTabelle().getEditingRow() == -1)
                    {
                        Objekt objekt = BasisStandortSuchen.this.objektModel
                                .getRow(row);
                        if (GUIManager
                                .getInstance()
                                .showQuestion(
                                                "Soll das Objekt "
                                                        + objekt.getId()
                                                        + " und alle seine Fachdaten wirklich "
                                                        + "gelöscht werden?\n"
                                                        + "Hinweis: Manche Objekte können auch erst"
                                                        + " gelöscht werden, wenn für sie\n"
                                                        + "keine Fachdaten mehr existieren.",
                                                "Löschen bestätigen"))
                        {
                            if (BasisStandortSuchen.this.objektModel
                                    .removeRow(row))
                            {
                                BasisStandortSuchen.this.frame.changeStatus(
                                                                            "Objekt gelöscht.",
                                                                            HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + objekt.getId()
                                        + " wurde gelöscht!");
                            }
                            else
                            {
                                BasisStandortSuchen.this.frame.changeStatus(
                                                                            "Konnte das Objekt nicht löschen!",
                                                                            HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.objektLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
                    KeyEvent.VK_L));
            this.objektLoeschAction.putValue(Action.ACCELERATOR_KEY,
                                                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.objektLoeschAction;
    }

    class StreamGobbler extends Thread
    {
        InputStream is;

        // reads everything from is until empty.
        StreamGobbler(InputStream is)
        {
            this.is = is;
        }

        @Override
        public void run()
        {
            try
            {
                InputStreamReader isr = new InputStreamReader(this.is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null)
                    log.debug(line);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }

    private Action getGisAction()
    {

        if (this.gisAction == null)
        {

            this.gisAction = new AbstractAction("GIS öffnen")
            {

                private static final long serialVersionUID = 9117497990586218808L;

                @Override
                public void actionPerformed(ActionEvent e)
                {

                    String prog = BasisStandortSuchen.this.manager
                            .getSettingsManager().getSetting(
                                                                "auik.gis.programmpath");
                    String proj = BasisStandortSuchen.this.manager
                            .getSettingsManager().getSetting(
                                                                "auik.gis.projectpath");

                    int row = BasisStandortSuchen.this.standortTabelle
                            .getSelectedRow();
                    Standort bsta = BasisStandortSuchen.this.standortModel
                            .getRow(row);

                    ProcessBuilder pb = new ProcessBuilder("cmd", "/C", prog,
                            proj);

                    Map<String, String> env = pb.environment();
                    env.put("RECHTS", bsta.getLage().getE32().toString());
                    env.put("HOCH", bsta.getLage().getN32().toString());

                    try
                    {

                        Process process = pb.start();
                        StreamGobbler errorGobbler = new StreamGobbler(
                                process.getErrorStream());
                        StreamGobbler outputGobbler = new StreamGobbler(
                                process.getInputStream());
                        errorGobbler.start();
                        outputGobbler.start();

                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            };
        }

        return this.gisAction;
    }

    private void showObjektPopup(MouseEvent e)
    {
        if (this.objektPopup == null)
        {
            this.objektPopup = new JPopupMenu("Objekt");
            JMenuItem bearbItem = new JMenuItem(getObjektEditAction());
            JMenuItem objbetrItem = new JMenuItem(getObjektBetreiberEditAction());
            JMenuItem objstdItem = new JMenuItem(getObjektStandortEditAction());
            JMenuItem loeschItem = new JMenuItem(getObjektLoeschAction());
            this.objektPopup.add(bearbItem);
            this.objektPopup.add(loeschItem);
        }

        if (e.isPopupTrigger())
        {
            Point origin = e.getPoint();
            int row = this.objektTabelle.rowAtPoint(origin);

            if (row != -1)
            {
                this.objektTabelle.setRowSelectionInterval(row, row);
                this.objektPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private JTable getObjektTabelle()
    {
        if (this.objektTabelle == null)
        {
            this.objektTabelle = new JTable(this.objektModel);
            this.objektTabelle
                    .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            objektTabelle.setAutoCreateRowSorter(true);

            TableColumn column = null;
            for (int i = 0; i < this.objektModel.getColumnCount(); i++)
            {
                column = this.objektTabelle.getColumnModel().getColumn(i);
                /*
                 * if (i == 0) { column.setMaxWidth(60);
                 * column.setPreferredWidth(column.getMaxWidth()-10); } else
                 */
                if (i == 0)
                {
                    column.setMaxWidth(60);
                    // column.setCellRenderer(centerRenderer);
                }
                else if (i == 1)
                {
                    // column.setMaxWidth(70);
                    column.setPreferredWidth(300);
                }
                else if (i == 2)
                {
                    // column.setMaxWidth(70);
                    column.setPreferredWidth(50);
                }
                else if (i == 3)
                {
                    // column.setMaxWidth(70);
                    column.setPreferredWidth(100);
                }
            }

            this.objektTabelle
                    .addMouseListener(new java.awt.event.MouseAdapter()
                    {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e)
                        {
                            if ((e.getClickCount() == 2)
                                    && (e.getButton() == 1))
                            {
                                Point origin = e.getPoint();
                                int row = getObjektTabelle().rowAtPoint(origin);
                                Objekt obj = BasisStandortSuchen.this.objektModel
                                        .getRow(objektTabelle.convertRowIndexToModel(row));
                                if ((row != -1)
                                        && (!(obj.getObjektarten().getId()
                                                .equals(DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE))))
                                {
                                    BasisStandortSuchen.this.manager
                                            .getSettingsManager().setSetting(
                                                                                "auik.imc.edit_object",
                                                                                obj.getId()
                                                                                        .intValue(), false);
                                    BasisStandortSuchen.this.manager
                                            .switchModul("m_objekt_bearbeiten");
                                }
                                else if ((row != -1)
                                        && (obj.getObjektarten().getId()
                                                .equals(DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)))
                                {
                                    BasisStandortSuchen.this.manager
                                            .getSettingsManager().setSetting(
                                                                                "auik.imc.edit_object",
                                                                                obj.getId()
                                                                                        .intValue(), false);
                                    BasisStandortSuchen.this.manager
                                            .switchModul("m_sielhaut1");
                                }
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e)
                        {
                            showObjektPopup(e);
                        }

                        @Override
                        public void mouseReleased(MouseEvent e)
                        {
                            showObjektPopup(e);
                        }
                    });

            this.objektTabelle.getInputMap().put(
                    (KeyStroke) getObjektEditAction().getValue(
                            Action.ACCELERATOR_KEY),
                    getObjektEditAction().getValue(Action.NAME));
            this.objektTabelle.getActionMap().put(
                    getObjektEditAction().getValue(Action.NAME),
                    getObjektEditAction());

            this.objektTabelle.getInputMap().put(
                    (KeyStroke) getObjektBetreiberEditAction().getValue(
                            Action.ACCELERATOR_KEY),
                    getObjektBetreiberEditAction().getValue(Action.NAME));
            this.objektTabelle.getActionMap().put(
                    getObjektBetreiberEditAction().getValue(Action.NAME),
                    getObjektBetreiberEditAction());

            this.objektTabelle.getInputMap().put(
                    (KeyStroke) getObjektStandortEditAction().getValue(
                            Action.ACCELERATOR_KEY),
                    getObjektStandortEditAction().getValue(Action.NAME));
            this.objektTabelle.getActionMap().put(
                    getObjektStandortEditAction().getValue(Action.NAME),
                    getObjektStandortEditAction());

            this.objektTabelle.getInputMap().put(
                    (KeyStroke) getObjektLoeschAction().getValue(
                            Action.ACCELERATOR_KEY),
                    getObjektLoeschAction().getValue(Action.NAME));
            this.objektTabelle.getActionMap().put(
                    getObjektLoeschAction().getValue(Action.NAME),
                    getObjektLoeschAction());
        }
        return this.objektTabelle;
    }

    private Action getStandortLoeschAction()
    {
        if (this.standortLoeschAction == null)
        {
            this.standortLoeschAction = new AbstractAction("Löschen")
            {
                private static final long serialVersionUID = 6709934716520847123L;

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int row = getStandortTabelle().getSelectedRow();
                    if (row != -1 && getStandortTabelle().getEditingRow() == -1)
                    {
                        if (BasisStandortSuchen.this.objektModel.getRowCount() != 0)
                        {
                            BasisStandortSuchen.this.frame
                                    .changeStatus(
                                                    "Kann Standort nicht löschen: Zu erst alle zugehörigen Objekte löschen!",
                                                    HauptFrame.ERROR_COLOR);
                        }
                        else
                        {
                            Standort str = BasisStandortSuchen.this.standortModel
                                    .getRow(row);

                            if (GUIManager.getInstance().showQuestion(
                                                                        "Soll der Standort '" + str
                                                                                + "' wirklich gelöscht werden?",
                                                                        "Löschen bestätigen"))
                            {
                                if (BasisStandortSuchen.this.standortModel
                                        .removeRow(row))
                                {
                                    BasisStandortSuchen.this.frame
                                            .changeStatus("Standort gelöscht.",
                                                            HauptFrame.SUCCESS_COLOR);
                                    log.debug("Standort " + str.getId()
                                            + " wurde gelöscht!");
                                }
                                else
                                {
                                    BasisStandortSuchen.this.frame
                                            .changeStatus(
                                                            "Konnte den Standort nicht löschen!",
                                                            HauptFrame.ERROR_COLOR);
                                }
                            }
                        }
                    }
                }
            };
            this.standortLoeschAction.putValue(Action.MNEMONIC_KEY,
                                                new Integer(KeyEvent.VK_L));
            this.standortLoeschAction.putValue(Action.ACCELERATOR_KEY,
                                                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.standortLoeschAction;
    }
}
