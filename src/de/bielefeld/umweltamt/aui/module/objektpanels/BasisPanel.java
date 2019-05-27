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
 * $Id: BasisPanel.java,v 1.1.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 19.04.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2010/02/24 10:46:35  u633d
 * Objektverknuepfung im Objekt-Panel
 *
 * Revision 1.6  2010/02/23 13:28:10  u633d
 * Objektverknuepfung im Objekt-Panel
 *
 * Revision 1.5  2010/02/15 09:24:09  u633d
 * Objektart nicht �nderbar
 *
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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Lage;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.Strassen;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.BasicEntryField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.MyKeySelectionManager;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Objekt"-Tab des ObjektBearbeiten-Moduls
 * @author David Klotz
 */

public class BasisPanel extends JPanel {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    private static final long serialVersionUID = 2520878475016486007L;

    private class ChooseDialog extends JDialog {
        private static final long serialVersionUID = 6320119317944629431L;
        private HauptFrame frame;
        private Adresse betreiber;
        private Adresse standort;
        private String caller;

        private BasisAdresseModel betreiberModel;
        private BasisAdresseModel standortModel;

        private JTextField suchFeld;
        private JTextField strasseFeld;
        private JTextField hausnrFeld;
        private JButton submitButton;
        private JButton submitButtonStrassen;
        private JTable ergebnisTabelle;

        private JButton okButton;
        private JButton abbrechenButton;

        private Timer suchTimer;


        public ChooseDialog(Object initial, HauptFrame frame, String caller) {
            super(frame, true);
            this.frame = frame;
            this.caller = caller;

            List<Object> initialList = new ArrayList<Object>();
            initialList.add(initial);

            if (initial instanceof Adresse) {
                setTitle("Adresse auswählen");
                this.betreiber = (Adresse) initial;
                this.betreiberModel = new BasisAdresseModel(true);
                if (this.betreiber.getId() != null) {
                    this.betreiberModel.setList(initialList);
                }
            } else {
                throw new IllegalArgumentException(
                    "intial muss eine Adresse sein!");
            }

            setContentPane(initializeContentPane());

            pack();
            setResizable(true);
            setLocationRelativeTo(this.frame);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }

        public Adresse getChosenBetreiber() {
            if (this.betreiber.getId() != null) {
                return this.betreiber;
            } else {
                return null;
            }
        }

        private JPanel initializeContentPane() {
            JScrollPane tabellenScroller = new JScrollPane(
                getErgebnisTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            TabAction ta = new TabAction();
            ta.addComp(getErgebnisTabelle());
            ta.addComp(getOkButton());
            ta.addComp(getAbbrechenButton());

            PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHEAST, true, false, 1, 0, 1, 1,
                    0, 0, 0, 0);

            PanelBuilder top = new PanelBuilder(PanelBuilder.NORTHEAST, true, false, 1, 0, 1, 1,
                    0, 0, 5, 5);
            top.setWrapLabelComponents(false);
            top.addComponent(getSuchFeld(), "Name:", false, true);
            top.setWeightX(0);
            top.addComponent(getSubmitButton(), true);
            top.setWeightX(1);
            top.addComponent(getStrassenFeld(), "Straße:");
            top.setWrapLabelComponents(true);
            top.addComponent(getHausnrFeld(), "Hausnr.:", false);
            top.setWeightX(0);
            top.addComponent(getSubmitButtonStrassen(), true);

            builder.setEmptyBorder(15);
            builder.setPreferedSize(1200, 750);

            builder.addComponent(top.getPanel(), true);
            builder.setFill(true, true);
            builder.setWeight(1, 1);
            builder.addComponent(tabellenScroller, true);
            builder.setFill(true, false);
            builder.setWeight(1, 0);
            builder.setAnchor(PanelBuilder.NORTHEAST);
            builder.fillRow();
            builder.setInsets(5, 0, 0, 0);
            builder.addComponent(
                    PanelBuilder.buildRightAlignedButtonToolbar(getOkButton(), getAbbrechenButton()));

            return builder.getPanel();
        }

        private void choose(int row) {
            if (row != -1) {
                if (this.betreiber != null) {
                    this.betreiber = this.betreiberModel.getRow(row);
                } else if (this.standort != null) {
                    this.standort = this.standortModel.getRow(row);
                }
                dispose();
            }
        }

        private void doSearch() {
            final String suche = getSuchFeld().getText();

            if (caller == "betreiber") {
                SwingWorkerVariant worker = new SwingWorkerVariant(
                    getErgebnisTabelle()) {
                    @Override
                    protected void doNonUILogic() throws RuntimeException {
                        ChooseDialog.this.betreiberModel
                            .filterAllList(suche, null);
                    }

                    @Override
                    protected void doUIUpdateLogic() throws RuntimeException {
                        ChooseDialog.this.betreiberModel.fireTableDataChanged();
                    }
                };
                worker.start();

                getStrassenFeld().setText("");
                getHausnrFeld().setText("");
                
            } else if (caller == "standort") {
                SwingWorkerVariant worker = new SwingWorkerVariant(
                        getErgebnisTabelle()) {
                        @Override
                        protected void doNonUILogic() throws RuntimeException {
                            ChooseDialog.this.betreiberModel
                                .filterStandort(suche, null);
                        }

                        @Override
                        protected void doUIUpdateLogic() throws RuntimeException {
                            ChooseDialog.this.betreiberModel.fireTableDataChanged();
                        }
                };
                worker.start();

                getStrassenFeld().setText("");
                getHausnrFeld().setText("");
            }
        } 
        
        /**
         * Filtert die Standort-Liste nach Straße und Hausnummer.
         * 
         * @param focusComp
         *            Welche Komponente soll nach der Suche den Fokus bekommen.
         */
        public void filterBetreiberListe(Component focusComp)
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
                        ChooseDialog.this.betreiberModel.filterStandort(
                                                                            getStrassenFeld().getText(),
                                                                            fhausnr, null);
                    }
                    getSuchFeld().setText("");
                }
        
                @Override
                protected void doUIUpdateLogic()
                {
                    getErgebnisTabelle().clearSelection();
        
                    ChooseDialog.this.betreiberModel.fireTableDataChanged();
                    String statusMsg = "Suche: "
                            + ChooseDialog.this.betreiberModel.getRowCount()
                            + " Ergebnis";
                    if (ChooseDialog.this.betreiberModel.getRowCount() != 1)
                    {
                        statusMsg += "se";
                    }
                    statusMsg += ".";
                    ChooseDialog.this.frame.changeStatus(statusMsg);
                }
            };
        
            this.frame.changeStatus("Suche...");
            worker.start();
            log.debug("End filterStandortListe()");
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
            }

            return this.suchFeld;
        }

        private JTextField getStrassenFeld() {
            
            if (this.strasseFeld == null) {
                
                this.strasseFeld = new JTextField();
                this.strasseFeld.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getSuchTimer().stop();
                        filterBetreiberListe(getErgebnisTabelle());
                    }
                });
                
                this.strasseFeld.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyPressed(KeyEvent e)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_TAB)
                        {
                            getSuchTimer().stop();
                            filterBetreiberListe(getHausnrFeld());
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

            return this.strasseFeld;
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
                        filterBetreiberListe(getErgebnisTabelle());
                    }
                });
            }
            return this.hausnrFeld;
        }

        private JButton getSubmitButton() {
            if (this.submitButton == null) {
                this.submitButton = new JButton("Name suchen", AuikUtils.getIcon(16,
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

        private JButton getSubmitButtonStrassen() {
            if (this.submitButtonStrassen == null) {
                this.submitButtonStrassen = new JButton("Standort suchen", AuikUtils.getIcon(16,
                    "key_enter.png"));
                this.submitButtonStrassen.setToolTipText("Suche starten");
                this.submitButtonStrassen.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        filterBetreiberListe(getErgebnisTabelle());;
                    }
                });
            }

            return this.submitButtonStrassen;
        }

        private JTable getErgebnisTabelle() {
            if (this.ergebnisTabelle == null) {
                if (this.betreiber != null) {
                    this.ergebnisTabelle = new JTable(this.betreiberModel);
                } else if (this.standort != null) {
                    this.ergebnisTabelle = new JTable(this.standortModel);
                    // ergebnisTabelle = new JTable(3, 3);
                }

                this.ergebnisTabelle.addFocusListener(TableFocusListener
                    .getInstance());
                this.ergebnisTabelle.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = ChooseDialog.this.ergebnisTabelle
                                .rowAtPoint(origin);

                            choose(row);
                        }
                    }
                });

                this.ergebnisTabelle.getColumnModel().getColumn(0)
                    .setPreferredWidth(130);
            }

            return this.ergebnisTabelle;
        }

        private JButton getOkButton() {
            if (this.okButton == null) {
                this.okButton = new JButton("Ok");
                this.okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row = getErgebnisTabelle().getSelectedRow();

                        choose(row);
                    }
                });
            }

            return this.okButton;
        }

        private JButton getAbbrechenButton() {
            if (this.abbrechenButton == null) {
                this.abbrechenButton = new JButton("Abbrechen");
                this.abbrechenButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
            }

            return this.abbrechenButton;
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
    }

    // Widgets
    // Betreiber
    private JTextField betreiberFeld;
    private JToolBar betreiberToolBar;
    private JButton betreiberChooseButton;
    private JButton betreiberEditButton;
    private JButton betreiberNewButton;
    private JButton betreiberDeleteButton;

    // Standort
    private JTextField standortFeld;
    private JToolBar standortToolBar;
    private JButton standortChooseButton;
    private JButton standortEditButton;
    private JButton standortNewButton;
    private JButton standortDeleteButton;
    
    // Lage
    private JTextField lageFeld;

    // Art, Sachbearbeiter, Inaktiv, Priorität, Beschreibung, Speichern
    private JComboBox artBox;
    private JComboBox sachbearbeiterBox;
    private TextFieldDateChooser wiedervorlageDatum = null;
    private JCheckBox inaktivBox;
    private JCheckBox elkarelevantBox;
    private JFormattedTextField prioritaetFeld;
    private JLabel prioritaetLabel;
    private JLabel elkarelevantLabel;
    private JTextArea beschreibungsArea;
    private JButton saveButton;

    // Normaler und kursiver Font für die Sachbearbeiter ComboBox
    // Normal: gespeichert, kursiv: nur angezeigt
    private Font normalFont;
    private Font italicFont;

    private ActionListener editButtonListener;

    // Daten
    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Fachdaten
    private Objektarten[] objektarten;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;
    
    
    private ActionListener deleteButtonListener;
    public BasisPanel(BasisObjektBearbeiten hauptModul) {

        this.name = "Objekt";
        this.hauptModul = hauptModul;

        JScrollPane objektverknuepfungScroller = new JScrollPane(
            getObjektverknuepungTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane beschreibungsScroller = new JScrollPane(
            getBeschreibungsArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    
        PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 1, 0, 1, 1,
                0, 0, 5, 5);

        PanelBuilder eigenschaften = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 0, 0, 1, 1,
                0, 0, 5, 5);
        eigenschaften.setWrapLabelComponents(false);
        eigenschaften.setPreferedSize(650, 500);
        eigenschaften.addComponent(getBetreiberFeld(), "Betreiber-Adresse:");
        eigenschaften.setWeightX(0);
        eigenschaften.addComponent(getBetreiberToolBar(), true);
        eigenschaften.setWeightX(1);
        eigenschaften.addComponent(getStandortFeld(), "Standort-Adresse:");
        eigenschaften.setWeightX(0);
        eigenschaften.addComponent(getStandortToolBar(), true);
        eigenschaften.setWeightX(1);
        eigenschaften.addComponent(getLageFeld(), "Lage:", true, true);
        eigenschaften.addComponent(getArtBox(), "Art:", true, true);
        eigenschaften.addComponent(getSachbearbeiterBox(), "SachbearbeiterIn:", true, true);
        eigenschaften.addComponent(getWiedervorlageDatum(), "Wiedervorlage:", true, true);
        eigenschaften.addComponent(getInaktivBox(), "Inaktiv:", true, true);
        eigenschaften.addComponent(getElkarelevantBox(), getElkarelevantLabel(), true, true);
        eigenschaften.addComponent(getPrioritaetFeld(), getPrioritaetLabel(), true, true);

        builder.setPreferedSize(650, 500);
        builder.addSeparator("Eigenschaften", true);
        builder.setAnchor(PanelBuilder.NORTHEAST);
        builder.addComponent(eigenschaften.getPanel(), true);
        builder.setAnchor(PanelBuilder.NORTHWEST);
        builder.addSeparator("Beschreibung", true);
        builder.setWeightY(0.4);
        builder.addComponent(beschreibungsScroller, true);
        builder.setWeightY(0);
        builder.addSeparator("Veknüpfte Objekte", true);
        builder.setWeightY(0.6);
        builder.addComponent(objektverknuepfungScroller, true);
        builder.setWeight(1, 0);
        builder.setInsets(0, 0, 0, 5);
        builder.addComponent(
                PanelBuilder.buildRightAlignedButtonToolbar(getSelectObjektButton(), getSaveButton()), true);


        PanelBuilder content = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 0, 0, 1, 1,
                0, 0, 5, 5);
        content.setEmptyBorder(15);
        content.addComponent(builder.getPanel());
        content.fillRow(true);
        content.fillColumn();

        this.setLayout(new BorderLayout());
        this.add(content.getPanel());
    }

    public void fetchFormData() {
        if (this.objektarten == null) {
            this.objektarten = DatabaseQuery.getObjektarten();
        }
    }

    public void updateForm() {
        boolean neu = false;
        log.debug("Updating BasisPanel");
        // Is this a new object?
        if (this.hauptModul.getObjekt() == null
            || this.hauptModul.getObjekt().getId() == null) {
            neu = true;
            log.debug("Neues Objekt");
        }

        if (neu == true) {
            // Create a new object
            log.debug("Creating new Objekt");
            //TODO: set new Objekt's elkarelevant-Field to a default value?
            hauptModul.getObjekt().setElkarelevant(new Boolean(false));
            // Only load enabled Sachbearbeiter
            getSachbearbeiterBox().setModel(new DefaultComboBoxModel(
                DatabaseQuery.getEnabledSachbearbeiter()));
            // Preset the current Sachbearbeiter
            getSachbearbeiterBox().setSelectedItem(
                DatabaseQuery.getCurrentSachbearbeiter());
            getSachbearbeiterBox().setFont(this.italicFont);

            if (this.objektarten != null
                && (this.objektarten.length != getArtBox().getItemCount())) {
                getArtBox()
                    .setModel(new DefaultComboBoxModel(this.objektarten));
            }
            // hauptModul.getObjekt().setPrioritaet(0);
        } else {
            // Show / edit an existing object
            getSachbearbeiterBox().setModel(new DefaultComboBoxModel(
                DatabaseQuery.getEnabledSachbearbeiter()));

            getArtBox().removeAllItems();
            // Ändern der Objektart von Anhang 53 (<3000) in Anhang 53 (>3000)
            // und umgekehrt ist weiterhin möglich
            Integer art = this.hauptModul.getObjekt().getObjektarten().getId();
            if (art == DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_KLEIN
                || art == DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_GROSS) {
                // Anhang 53 (<3000) (360.33)
                getArtBox().addItem(Objektarten.findById(
                    DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_KLEIN));
                // Anhang 53 (>3000) (360.33)
                getArtBox().addItem(Objektarten.findById(
                    DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_53_GROSS));
            }
            // Ändern der Objektarten Anhang 49, Abscheider und Fettabscheider
            // ist ebenfalls möglich
            else if (art == DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_49
                || art == DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER
                || art == DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER
                || art == DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER34) {
                // Anhang 49 (360.33)
                getArtBox().addItem(Objektarten.findById(
                    DatabaseConstants.BASIS_OBJEKTART_ID_ANHANG_49));
                // Abscheider (360.32)
                getArtBox().addItem(Objektarten.findById(
                    DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER));
                // Fettabscheider (360.33)
                getArtBox().addItem(Objektarten.findById(
                    DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER));
                // Abscheider (360.34)
                getArtBox().addItem(Objektarten.findById(
                    DatabaseConstants.BASIS_OBJEKTART_ID_ABSCHEIDER34));
            }
            // Objektart als einziges in die Liste eintragen
            else {
                getArtBox().addItem(
                    this.hauptModul.getObjekt().getObjektarten());
            }
        }

        if (this.hauptModul.getObjekt() != null) {
            log.debug("Updating Form with Objekt: " + hauptModul.getObjekt().getId() + " " + hauptModul.getObjekt().getBetreiberid() + " " + hauptModul.getObjekt().getStandortid());
            if (this.hauptModul.getObjekt().getBetreiberid() != null) {
                // TODO: Why are we using html here? :-/
                
                Adresse betr = this.hauptModul.getObjekt()
                    .getBetreiberid();
                log.debug("Set betreiber field to " + betr);
                getBetreiberFeld().setText(betr.toString());
                String toolTip = "<html><b>Anrede:</b> "
                    + ((betr.getBetranrede() != null) ? betr.getBetranrede()
                        : "") + "<br>" + "<b>Name:</b> " + betr.getBetrname()
                    + "<br>";
                if (betr.getBetrnamezus() != null) {
                    toolTip += "<b>Zusatz:</b> " + betr.getBetrnamezus()
                        + "<br><br>";
                }
                if (betr.getStrasse() != null) {
                    toolTip += "<b>Adresse:</b><br>" + betr.getStrasse() + " "
                        + betr.getHausnr();
                    if (betr.getHausnrzus() != null) {
                        toolTip += betr.getHausnrzus();
                    }
                    toolTip += "<br>";
                }
                toolTip += ((betr.getPlzzs() != null) ? betr.getPlzzs().trim()
                    + " - " : "")
                    + ((betr.getPlz() != null) ? betr.getPlz() + " " : "")
                    + ((betr.getOrt() != null) ? betr.getOrt() : "");
                if (betr.getTelefon() != null) {
                    toolTip += "<br><br><b>Telefon:</b> " + betr.getTelefon();
                }
                toolTip += "</html>";
                getBetreiberFeld().setToolTipText(toolTip);
            
            }
            if (this.hauptModul.getObjekt().getStandortid() != null) {

                Standort standort = (Standort) Standort
                        .findByAdresse(this.hauptModul.getObjekt()
                                .getStandortid().getAdresse());
                if (standort != null) {
                    Adresse adr = standort.getAdresse();
                    log.debug("Set standort field to: " + adr
                            + this.hauptModul.getObjekt().getStandortid()
                            + " " + this.hauptModul.getObjekt().getStandortid().getLage());
                    String toolTip = "<html>" + adr + "<br>";
                    if (adr.getPlz() != null) {
                        toolTip += "<b>PLZ:</b> " + adr.getPlz() + "<br>";
                    }
                    toolTip += "<b>Gemarkung:</b> "
                            + standort.getLage().getGemarkung()
                            + ((standort.getLage().getEntgebid() != null) ? "<br><b>Entw.gebiet:</b> "
                                    + standort.getLage().getEntgebid()
                                    : "") + "</html>";
                    getStandortFeld().setToolTipText(toolTip);
                    getStandortFeld().setText(
                            standort.getAdresse().toString());

                    if (this.hauptModul.getObjekt().getStandortid().getLage() == null) {
                        standort = (Standort) Standort
                                .findByAdresse(this.hauptModul.getObjekt()
                                        .getStandortid().getAdresse());
                        this.hauptModul.getObjekt().getStandortid().setLage(
                                standort.getLage());
                    }
                    getLageFeld().setText(standort.getLage().toString());
                }else {
                    getLageFeld().setText(this.hauptModul.getObjekt().getStandortid().getLage().toString());
                }
            }
            
//			if (this.hauptModul.getObjekt().getStandortid().getId() == 3) {
//
//				getLageFeld().setText(this.hauptModul.getObjekt().getStandortid().getLage().toString());
//			}

            if (this.hauptModul.getObjekt().getObjektarten() != null) {
                getArtBox().setSelectedItem(
                    this.hauptModul.getObjekt().getObjektarten());
            }

            if (this.hauptModul.getObjekt().getSachbearbeiter() != null) {
                getSachbearbeiterBox().setSelectedItem(
                    this.hauptModul.getObjekt().getSachbearbeiter());
                getSachbearbeiterBox().setFont(this.normalFont);
            } else {
                getSachbearbeiterBox().setSelectedItem(
                    DatabaseQuery.getCurrentSachbearbeiter());
                getSachbearbeiterBox().setFont(this.italicFont);
            }
            
            if (this.hauptModul.getObjekt().getWiedervorlage() != null) {
                getWiedervorlageDatum().setDate(this.hauptModul.getObjekt().getWiedervorlage());
            }

            getInaktivBox().setSelected(
                this.hauptModul.getObjekt().isInaktiv());
            
            if(this.hauptModul == null)
                log.debug("hauptModul null");
            if(this.hauptModul.getObjekt() == null)
                log.debug("hauptModul.getObjekt() null");
            if(this.hauptModul.getObjekt().getElkarelevant() == null)
                log.debug("hauptModul.getObjekt().getElkarelevant() null");
                
            log.debug("ELKA relevant: " + hauptModul.getObjekt().getElkarelevant().booleanValue() );
            log.debug("Objekt: " + hauptModul.getObjekt().toString());
            getElkarelevantBox().setSelected(
                    this.hauptModul.getObjekt().getElkarelevant());

            if (!neu) {
                if (this.hauptModul.getObjekt().getPrioritaet() != null) {
                    getPrioritaetFeld().setText(
                        this.hauptModul.getObjekt().getPrioritaet().toString());
                }
            }

            if (!neu) {
                if (this.hauptModul.getObjekt().getObjektarten()
                        .getAbteilung().equals("360.33")) {
                    getPrioritaetFeld().setVisible(true);
                    getPrioritaetLabel().setVisible(true);
                    getElkarelevantBox().setVisible(true);
                    getElkarelevantLabel().setVisible(true);
                } else {
                    getPrioritaetFeld().setVisible(false);
                    getPrioritaetLabel().setVisible(false);
                    getElkarelevantBox().setVisible(false);
                    getElkarelevantLabel().setVisible(false);
                }
            }

            if (this.hauptModul.getObjekt().getBeschreibung() != null) {
                getBeschreibungsArea().setText(
                    this.hauptModul.getObjekt().getBeschreibung());
            }

            if (this.hauptModul.getObjekt().getId() != null) {
                this.objektVerknuepfungModel.setObjekt(this.hauptModul
                    .getObjekt());
            } else {
                this.objektVerknuepfungModel.clearList();
            }
        }
    }

    public void clearForm() {
        getBetreiberFeld().setText("");
        getBetreiberFeld().setToolTipText(null);
        getStandortFeld().setText("");
        getStandortFeld().setToolTipText(null);
        getLageFeld().setText("");
        if (getArtBox().getItemCount() > 0) {
            getArtBox().setSelectedIndex(0);
        }
        if (getSachbearbeiterBox().getItemCount() > 0) {
            getSachbearbeiterBox().setSelectedItem(
                DatabaseQuery.getCurrentSachbearbeiter());
            getSachbearbeiterBox().setFont(this.italicFont);
        }
        getWiedervorlageDatum().setDate(null);
        getInaktivBox().setSelected(false);
        getPrioritaetFeld().setText("");
        getBeschreibungsArea().setText(null);
    }

    public void enableAll(boolean enabled) {
        getSaveButton().setEnabled(enabled);
        getBetreiberToolBar().setEnabled(enabled);
        getStandortToolBar().setEnabled(enabled);
        getArtBox().setEnabled(enabled);
        getSachbearbeiterBox().setEnabled(enabled);
                
        if (this.hauptModul.getObjekt() != null && this.hauptModul.getObjekt().getSachbearbeiter() != null){	
            log.debug("getObjekt: " + this.hauptModul.getObjekt());
            log.debug("Sachbearbeiter: " + this.hauptModul.getObjekt().getSachbearbeiter());
    
            if (DatabaseQuery.getCurrentSachbearbeiter() != null
                || this.hauptModul.getObjekt().getSachbearbeiter().equals(DatabaseQuery.getCurrentSachbearbeiter())) {
                getWiedervorlageDatum().setEnabled(enabled);
            } else
                getWiedervorlageDatum().setEnabled(false);
            getInaktivBox().setEnabled(enabled);
            getPrioritaetFeld().setEnabled(enabled);
            getBeschreibungsArea().setEnabled(enabled);
        }
        else
            log.debug("Objekt oder Sachbearbeiter null - Objekt: " + this.hauptModul.getObjekt() + ", Sachbearbeiter: " + this.hauptModul.getObjekt().getSachbearbeiter());
    }

    @Override
    public String getName() {
        return this.name;
    }

    private boolean saveObjektDaten() {
        boolean success;

        // Eingegebene Daten für das Objekt übernehmen
        // Betreiber / Standort werden schon nach der Auswahl durch die
        // chooseButtons gesetzt
        this.hauptModul.getObjekt().setObjektarten(
            (Objektarten) getArtBox().getSelectedItem());
        this.hauptModul.getObjekt().setBeschreibung(
            getBeschreibungsArea().getText());
        this.hauptModul.getObjekt().setSachbearbeiter(
            (Sachbearbeiter) getSachbearbeiterBox().getSelectedItem());
        Date wiedervorlage = this.wiedervorlageDatum.getDate();
        this.hauptModul.getObjekt().setWiedervorlage(wiedervorlage);
        this.hauptModul.getObjekt().setInaktiv(getInaktivBox().isSelected());
        this.hauptModul.getObjekt().setElkarelevant(getElkarelevantBox().isSelected());

//        Objekt tmp = Objekt.saveObjekt(
//            this.hauptModul.getObjekt(), prio);
        Objekt tmp = Objekt.merge(this.hauptModul.getObjekt());

        // Only cascade the priority, if one is set
        if (!getPrioritaetFeld().getText().equals("")) {
            DatabaseQuery.cascadePriority(getPrioritaetFeld().getText(), tmp);
        }

        if (tmp != null) {
            this.hauptModul.setObjekt(tmp);
            this.hauptModul.completeObjekt();
            success = true;
            log.debug("Objekt " + this.hauptModul.getObjekt() + " gespeichert.");
        } else {
            success = false;
            log.debug("Objekt " + this.hauptModul.getObjekt()
                + " konnte nicht gespeichert werden!");
        }

        return success;
    }

    private ActionListener getEditButtonListener() {
        if (this.editButtonListener == null) {
            this.editButtonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String action = e.getActionCommand();

                    Adresse betreiber = BasisPanel.this.hauptModul
                        .getObjekt().getBetreiberid();
                    Standort standort = (Standort) BasisPanel.this.hauptModul
                            .getObjekt().getStandortid();

                    if ("betreiber_edit".equals(action) && betreiber != null) {
                        BetreiberEditor editDialog = new BetreiberEditor(
                            betreiber, BasisPanel.this.hauptModul.getFrame());
                        editDialog
                            .setLocationRelativeTo(BasisPanel.this.hauptModul
                                .getFrame());

                        editDialog.setVisible(true);

                        BasisPanel.this.hauptModul.getObjekt()
                            .setBetreiberid(editDialog.getBetreiber());
                        
                    } else if ("standort_edit".equals(action)
                        && standort != null) {
                        BetreiberEditor editDialog = new BetreiberEditor(
                                standort.getAdresse(), BasisPanel.this.hauptModul.getFrame());
                            editDialog
                                .setLocationRelativeTo(BasisPanel.this.hauptModul
                                    .getFrame());

                        editDialog.setVisible(true);

                        BasisPanel.this.hauptModul.getObjekt()
                            .setStandortid(editDialog.getBetreiber().getStandort());
                    }

                    updateForm();
                }
            };
        }

        return this.editButtonListener;
    }

    public JTextField getBetreiberFeld() {
        if (this.betreiberFeld == null) {
            this.betreiberFeld = new JTextField("");
            this.betreiberFeld.setEditable(false);
        }
        return this.betreiberFeld;
    }

    private JToolBar getBetreiberToolBar() {
        if (this.betreiberToolBar == null) {
            this.betreiberToolBar = new JToolBar();
            this.betreiberToolBar.setFloatable(false);
            this.betreiberToolBar.setRollover(true);

            this.betreiberToolBar.add(getBetreiberChooseButton());
            this.betreiberToolBar.add(getBetreiberNewButton());
            this.betreiberToolBar.add(getBetreiberEditButton());
            this.betreiberToolBar.add(getBetreiberDeleteButton());
        }
        return this.betreiberToolBar;
    }

    private JButton getBetreiberChooseButton() {
        if (this.betreiberChooseButton == null) {
            this.betreiberChooseButton = new JButton(AuikUtils.getIcon(16,
                "reload.png", ""));
            this.betreiberChooseButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.betreiberChooseButton.setToolTipText("Adresse auswählen");

            this.betreiberChooseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Adresse betreiber = BasisPanel.this.hauptModul
                        .getObjekt().getBetreiberid();
                    if (betreiber == null) {
                        betreiber = new Adresse();
                    }
                    ChooseDialog chooser = new ChooseDialog(betreiber,
                        BasisPanel.this.hauptModul.getFrame(), "betreiber");
                    chooser.setVisible(true);

                    BasisPanel.this.hauptModul.getObjekt().setBetreiberid(
                        chooser.getChosenBetreiber());
                    updateForm();
                }
            });
        }

        return this.betreiberChooseButton;
    }

    private JButton getStandortChooseButton() {
        if (this.standortChooseButton == null) {
            this.standortChooseButton = new JButton(AuikUtils.getIcon(16,
                "reload.png", ""));
            this.standortChooseButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.standortChooseButton.setToolTipText("Adresse auswählen");
    
            this.standortChooseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Standort standort = BasisPanel.this.hauptModul
                            .getObjekt().getStandortid();
                    if (BasisPanel.this. hauptModul.getObjekt().getBetreiberid() != null &&
                            standort == null) {
                        standort = new Standort();
                        standort.setAdresse(BasisPanel.this. hauptModul.getObjekt().getBetreiberid());
                    }
                    if(standort == null){
                        standort = new Standort();
                        standort.setAdresse(new Adresse());
                    }
                    ChooseDialog chooser = new ChooseDialog(standort.getAdresse(),
                            BasisPanel.this.hauptModul.getFrame(), "standort");
                    chooser.setVisible(true);
    
                    if (chooser.getChosenBetreiber() != null) {
                        standortFeld.setText(chooser.getChosenBetreiber()
                                .toString());
                        BasisPanel.this.hauptModul.getObjekt()
                                .setStandortid(chooser.getChosenBetreiber().getStandort());
                    }
                    updateForm();
                }
            });
        }
    
        return this.standortChooseButton;
    }

    private JButton getBetreiberEditButton() {
        if (this.betreiberEditButton == null) {
            this.betreiberEditButton = new JButton(AuikUtils.getIcon(16,
                "edit.png", ""));
            this.betreiberEditButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.betreiberEditButton.setToolTipText("Betreiber bearbeiten");
            this.betreiberEditButton.setActionCommand("betreiber_edit");

            this.betreiberEditButton.addActionListener(getEditButtonListener());
        }

        return this.betreiberEditButton;
    }

    private JButton getBetreiberNewButton() {
        if (this.betreiberNewButton == null) {
            this.betreiberNewButton = new JButton(AuikUtils.getIcon(16,
                "filenew.png", ""));
            this.betreiberNewButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.betreiberNewButton.setToolTipText("Neuen Betreiber anlegen");

            this.betreiberNewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BasisPanel.this.hauptModul.getManager()
                        .getSettingsManager()
                        .setSetting("auik.imc.return_to_objekt_betreiber", true, false);
                    if (BasisPanel.this.hauptModul.getObjekt()
                        .getBetreiberid() != null) {
                        BasisPanel.this.hauptModul
                            .getManager()
                            .getSettingsManager()
                            .setSetting(
                                "auik.imc.use_betreiber",
                                BasisPanel.this.hauptModul.getObjekt()
                                    .getBetreiberid().getId()
                                    .intValue(), false);
                    }
                    if (BasisPanel.this.hauptModul.getObjekt()
                        .getStandortid() != null) {
                        BasisPanel.this.hauptModul
                            .getManager()
                            .getSettingsManager()
                            .setSetting(
                                "auik.imc.use_lage",
                                BasisPanel.this.hauptModul.getObjekt()
                                    .getStandortid().getId()
                                    .intValue(), false);
                    }
                    if (BasisPanel.this.hauptModul.getObjekt()
                            .getStandortid() != null) {
                            BasisPanel.this.hauptModul
                                .getManager()
                                .getSettingsManager()
                                .setSetting(
                                    "auik.imc.use_standort",
                                    BasisPanel.this.hauptModul.getObjekt()
                                        .getStandortid().getId()
                                        .intValue(), false);
                        }
                    BasisPanel.this.hauptModul.getManager().switchModul(
                        "m_betreiber_neu");
                }
            });
        }

        return this.betreiberNewButton;
    }

    public JTextField getStandortFeld() {
        if (this.standortFeld == null) {
            this.standortFeld = new JTextField("");
            this.standortFeld.setEditable(false);
        }
        return this.standortFeld;
    }

    public JTextField getLageFeld() {
        if (this.lageFeld == null) {
            this.lageFeld = new JTextField("");
            this.lageFeld.setEditable(false);
        }
        return this.lageFeld;
    }

    private JToolBar getStandortToolBar() {
        if (this.standortToolBar == null) {
            this.standortToolBar = new JToolBar();
            this.standortToolBar.setFloatable(false);
            this.standortToolBar.setRollover(true);

            this.standortToolBar.add(getStandortChooseButton());
            this.standortToolBar.add(getStandortNewButton());
            this.standortToolBar.add(getStandortEditButton());
            this.standortToolBar.add(getStandortDeleteButton());
        }
        return this.standortToolBar;
    }

    private JButton getStandortEditButton() {
        if (this.standortEditButton == null) {
            this.standortEditButton = new JButton(AuikUtils.getIcon(16,
                "edit.png", ""));
            this.standortEditButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.standortEditButton.setToolTipText("Standort bearbeiten");
            this.standortEditButton.setActionCommand("standort_edit");

            this.standortEditButton.addActionListener(getEditButtonListener());
        }

        return this.standortEditButton;
    }

    private JButton getStandortNewButton() {
        if (this.standortNewButton == null) {
            this.standortNewButton = new JButton(AuikUtils.getIcon(16,
                "filenew.png", ""));
            this.standortNewButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.standortNewButton.setToolTipText("Neuen Standort anlegen");

            this.standortNewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BasisPanel.this.hauptModul.getManager()
                        .getSettingsManager()
                        .setSetting("auik.imc.return_to_objekt_standort", true, false);
                    if (BasisPanel.this.hauptModul.getObjekt()
                        .getBetreiberid() != null) {
                        BasisPanel.this.hauptModul
                            .getManager()
                            .getSettingsManager()
                            .setSetting(
                                "auik.imc.use_betreiber",
                                BasisPanel.this.hauptModul.getObjekt()
                                    .getBetreiberid().getId()
                                    .intValue(), false);
                    }
                    if (BasisPanel.this.hauptModul.getObjekt()
                        .getStandortid() != null) {
                        BasisPanel.this.hauptModul
                            .getManager()
                            .getSettingsManager()
                            .setSetting(
                                "auik.imc.use_standort",
                                BasisPanel.this.hauptModul.getObjekt()
                                    .getStandortid().getId().intValue(),
                                false);
                    }
                    if (BasisPanel.this.hauptModul.getObjekt()
                            .getStandortid() != null) {
                            BasisPanel.this.hauptModul
                                .getManager()
                                .getSettingsManager()
                                .setSetting(
                                    "auik.imc.use_lage",
                                    BasisPanel.this.hauptModul.getObjekt()
                                        .getStandortid().getId().intValue(),
                                    false);
                        }
                    BasisPanel.this.hauptModul.getManager().switchModul(
                        "m_betreiber_neu");
                }
            });
        }

        return this.standortNewButton;
    }

    private JComboBox getArtBox() {
        if (this.artBox == null) {

            this.artBox = new JComboBox();
            this.artBox.setKeySelectionManager(new MyKeySelectionManager());

        }
        return this.artBox;
    }

    private JComboBox getSachbearbeiterBox() {
        if (this.sachbearbeiterBox == null) {

            this.sachbearbeiterBox = new JComboBox();
            this.sachbearbeiterBox
                .setKeySelectionManager(new MyKeySelectionManager());

            this.normalFont = getSachbearbeiterBox().getFont();
            this.italicFont = new Font(this.normalFont.getName(), Font.ITALIC,
                this.normalFont.getSize());

        }
        return this.sachbearbeiterBox;
    }

    private TextFieldDateChooser getWiedervorlageDatum() {
        if (this.wiedervorlageDatum == null) {
            this.wiedervorlageDatum = new TextFieldDateChooser();
        }
        return this.wiedervorlageDatum;
    }

    private JCheckBox getInaktivBox() {
        if (this.inaktivBox == null) {
            this.inaktivBox = new JCheckBox();
        }
        return this.inaktivBox;
    }

    private JCheckBox getElkarelevantBox() {
        if (this.elkarelevantBox == null) {
            this.elkarelevantBox = new JCheckBox();
            this.elkarelevantBox.setVisible(false);
        }
        return this.elkarelevantBox;
    }

    private JFormattedTextField getPrioritaetFeld() {
        if (this.prioritaetFeld == null) {
            this.prioritaetFeld = new JFormattedTextField();
            this.prioritaetFeld.setVisible(false);
        }
        return this.prioritaetFeld;
    }

    private JLabel getPrioritaetLabel() {
        if (this.prioritaetLabel == null) {
            this.prioritaetLabel = new JLabel("Priorität:");
            this.prioritaetLabel.setVisible(false);
        }
        return this.prioritaetLabel;
    }

    private JLabel getElkarelevantLabel() {
        if (this.elkarelevantLabel == null) {
            this.elkarelevantLabel = new JLabel("ELKA relevant:");
            this.elkarelevantLabel.setVisible(false);
        }
        return this.elkarelevantLabel;
    }

    public JTextArea getBeschreibungsArea() {
        if (this.beschreibungsArea == null) {
            this.beschreibungsArea = new LimitedTextArea(150);
            this.beschreibungsArea.setLineWrap(true);
            this.beschreibungsArea.setWrapStyleWord(true);
        }
        return this.beschreibungsArea;
    }

    private JButton getSaveButton() {
        if (this.saveButton == null) {
            this.saveButton = new JButton("Objekt speichern");
            this.saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ((BasisPanel.this.hauptModul.getObjekt()
                        .getBetreiberid() != null)
                        && (BasisPanel.this.hauptModul.getObjekt()
                            .getStandortid() != null)) {
                        enableAll(false);
                        if (saveObjektDaten()) {
                            BasisPanel.this.hauptModul.getFrame().changeStatus(
                                "Objekt "
                                    + BasisPanel.this.hauptModul.getObjekt()
                                        .getId()
                                    + " erfolgreich gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                            BasisPanel.this.hauptModul.setNew(false);
                        } else {
                            BasisPanel.this.hauptModul.getFrame().changeStatus(
                                "Konnte Objekt nicht speichern!",
                                HauptFrame.ERROR_COLOR);
                        }

                        BasisPanel.this.hauptModul.fillForm();
                    } else {
                        BasisPanel.this.hauptModul.getFrame().changeStatus(
                            "Kein Betreiber/Standort ausgewählt!",
                            HauptFrame.ERROR_COLOR);
                    }
                }
            });
        }
        return this.saveButton;
    }

    private JTable getObjektverknuepungTabelle() {

        if (this.objektVerknuepfungModel == null) {
            this.objektVerknuepfungModel = new ObjektVerknuepfungModel(
                this.hauptModul.getObjekt());

            if (this.objektverknuepfungTabelle == null) {
                this.objektverknuepfungTabelle = new JTable(
                    this.objektVerknuepfungModel);
            } else {
                this.objektverknuepfungTabelle
                    .setModel(this.objektVerknuepfungModel);
            }
            this.objektverknuepfungTabelle.getColumnModel().getColumn(0)
                .setPreferredWidth(5);
            this.objektverknuepfungTabelle.getColumnModel().getColumn(1)
                .setPreferredWidth(100);
            this.objektverknuepfungTabelle.getColumnModel().getColumn(2)
                .setPreferredWidth(250);

            this.objektverknuepfungTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getObjektverknuepungTabelle().rowAtPoint(
                                origin);

                            if (row != -1) {
                                Objektverknuepfung obj = BasisPanel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != BasisPanel.this.hauptModul
                                    .getObjekt().getId().intValue())
                                    BasisPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                else
                                    BasisPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                BasisPanel.this.hauptModul.getManager()
                                    .switchModul("m_objekt_bearbeiten");
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        showVerknuepfungPopup(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showVerknuepfungPopup(e);
                    }
                });

            this.objektverknuepfungTabelle.getInputMap().put(
                (KeyStroke) getVerknuepfungLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getVerknuepfungLoeschAction().getValue(Action.NAME));
            this.objektverknuepfungTabelle.getActionMap().put(
                getVerknuepfungLoeschAction().getValue(Action.NAME),
                getVerknuepfungLoeschAction());
        }

        return this.objektverknuepfungTabelle;
    }

    private void showVerknuepfungPopup(MouseEvent e) {
        if (this.verknuepfungPopup == null) {
            this.verknuepfungPopup = new JPopupMenu("Objekt");
            JMenuItem loeschItem = new JMenuItem(getVerknuepfungLoeschAction());
            this.verknuepfungPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektverknuepfungTabelle.rowAtPoint(origin);

            if (row != -1) {
                this.objektverknuepfungTabelle
                    .setRowSelectionInterval(row, row);
                this.verknuepfungPopup.show(e.getComponent(), e.getX(),
                    e.getY());
            }
        }
    }

    private Action getVerknuepfungLoeschAction() {
        if (this.verknuepfungLoeschAction == null) {
            this.verknuepfungLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 1214869561793347819L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung = BasisPanel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (BasisPanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                BasisPanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                BasisPanel.this.hauptModul.getFrame()
                                    .changeStatus(
                                        "Konnte das Objekt nicht löschen!",
                                        HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.verknuepfungLoeschAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_L));
            this.verknuepfungLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.verknuepfungLoeschAction;
    }

    private JButton getSelectObjektButton() {
        if (this.selectObjektButton == null) {
            this.selectObjektButton = new JButton("Objekt auswählen");

            this.selectObjektButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Save the objekt bevor we can link it to another
                    if (BasisPanel.this.hauptModul.getObjekt().getId() == null) {
                        BasisPanel.this.hauptModul
                            .getFrame()
                            .changeStatus(
                                "Das neue Objekt muss erst gespeichert werden, bevor es mit anderen verknüpft werden kann.",
                                HauptFrame.ERROR_COLOR);
                    } else {
                        ObjektChooser chooser = new ObjektChooser(
                            BasisPanel.this.hauptModul.getFrame(),
                            BasisPanel.this.hauptModul.getObjekt(),
                            BasisPanel.this.objektVerknuepfungModel);
                        chooser.setVisible(true);
                    }
                }
            });
        }
        return this.selectObjektButton;
    }



    public JButton getStandortDeleteButton() {
        if (this.standortDeleteButton == null) {
            this.standortDeleteButton = new JButton(AuikUtils.getIcon(16,"exit.png",""));
            this.standortDeleteButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.standortDeleteButton.setToolTipText("Standort löschen");
            this.standortDeleteButton.setActionCommand("standort_delete");

            this.standortDeleteButton.addActionListener(getDeleteButtonListener());
            
        }

        return this.standortDeleteButton;
    }
    
    



    private ActionListener getDeleteButtonListener() {
        if (this.deleteButtonListener == null) {
            this.deleteButtonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String action = e.getActionCommand();

                    Adresse betreiber = BasisPanel.this.hauptModul
                        .getObjekt().getBetreiberid();
                    Standort standort = BasisPanel.this.hauptModul
                        .getObjekt().getStandortid();

                    if ("betreiber_delete".equals(action) && betreiber != null) {
                        
                        boolean dCheck = delCheck(true);
                        if (dCheck == true){
                            deleteBetreiber();
                             getBetreiberFeld().setText("");
                             getBetreiberFeld().setToolTipText(null);
                        }
                        
                    } else if ("standort_delete".equals(action)
                        && standort != null) {
                        
                        boolean dCheck = delCheck(false);
                        if (dCheck == true){
                            deleteStandort();
                            getStandortFeld().setText("");
                            getStandortFeld().setToolTipText(null);
                        }
                     
                        
                    }

                    updateForm();
                }
            };
        }
        return deleteButtonListener;
    }

    

    public JButton getBetreiberDeleteButton() {
        if (this.betreiberDeleteButton == null) {
            this.betreiberDeleteButton = new JButton(AuikUtils.getIcon(16,"exit.png",""));
            this.betreiberDeleteButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.betreiberDeleteButton.setToolTipText("Betreiber löschen");
            this.betreiberDeleteButton.setActionCommand("betreiber_delete");

            this.betreiberDeleteButton.addActionListener(getDeleteButtonListener());
            
        }

        return this.betreiberDeleteButton;
    }
    
    private void deleteStandort(){
        
        Standort tmp = this.hauptModul.getObjekt().getStandortid();
        tmp.setDeleted(true);
        tmp.merge();
        this.hauptModul.getObjekt().setStandortid(null);
        
    }
    
    private void deleteBetreiber(){
        
        Adresse tmp = this.hauptModul.getObjekt().getBetreiberid();
        tmp.setDeleted(true);
        tmp.merge();
        this.hauptModul.getObjekt().setBetreiberid(null);
    }
    
    private boolean delCheck(boolean betr){
        
        if(betr == true){
            JOptionPane dcp = new JOptionPane ("Betreiber löschen");
            Object[] options = new String[]{"Ja", "Nein"};
            JDialog dia = dcp.createDialog("Wirklich löschen?");
            int a = JOptionPane.showOptionDialog(dia, "Soll der Betreiber gelöscht werden?",
                    "Wirklich löschen?", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
            if (a == JOptionPane.YES_OPTION) return true;
            else return false;
        }
        else{
        JOptionPane dcp = new JOptionPane ("Standort löschen");
        Object[] options = new String[]{"Ja", "Nein"};
        JDialog dia = dcp.createDialog("Wirklich löschen?");
        int a = JOptionPane.showOptionDialog(dia, "Soll der Standort gelöscht werden?",
                "Wirklich löschen?", JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
            if (a == JOptionPane.YES_OPTION) return true;
            else return false;
        }
                
    }
}
