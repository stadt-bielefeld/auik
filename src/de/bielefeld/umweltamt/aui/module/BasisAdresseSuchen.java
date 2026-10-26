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
 * $Id: BasisAdresseSuchen.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 12.01.2005 von David Klotz (u633z)
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

// ── ENTFERNT: JGoodies Imports [1] ───────────────────
// import com.jgoodies.forms.builder.PanelBuilder;
// import com.jgoodies.forms.factories.Paddings;
// import com.jgoodies.forms.layout.CellConstraints;
// import com.jgoodies.forms.layout.FormLayout;
// import java.awt.Label;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisInhaberModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisObjektModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.BasicEntryField;
import de.bielefeld.umweltamt.aui.utils.NamedObject;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Ein Modul zum Suchen und Bearbeiten eines Betreibers.
 * @author David Klotz
 */
public class BasisAdresseSuchen extends AbstractModul {
    /** Logging */
    private static final AuikLogger log =
        AuikLogger.getLogger();

    private String iconPath = "filefind32.png";
    private JComboBox<NamedObject> suchBox;
    private JTextField suchFeld;
    private JTextField strassenFeld;
    private JTextField hausnrFeld;
    private JTextField ortFeld;
    private JButton submitButton;
    private JButton indirektButton;
    private JButton awsvButton;
    private JButton probepktButton;
    private JButton anfallButton;
    private JButton genButton;
    private JButton submitButtonBetreiber;
    private JSplitPane tabellenSplit;
    private JTable betreiberTabelle;
    private JTable objektTabelle;
    private Action betreiberEditAction;
    private Action betreiberLoeschAction;
    private Action objektNeuAction;
    private JPopupMenu betreiberPopup;
    private Action objektEditAction;
    private Action objektBetreiberEditAction;
    private Action objektStandortEditAction;
    private Action objektLoeschAction;
    private JPopupMenu objektPopup;
    private BasisInhaberModel inhaberModel;
    private BasisObjektModel objektModel;
    public Inhaber inhaber;
    private Timer suchTimer;

    @Override
    public String getName() {
        return "Betrieb suchen";
    }

    @Override
    public String getIdentifier() {
        return "m_adresse_suchen";
    }

    @Override
    public String getCategory() {
        return "Betriebe";
    }

    @Override
    public Icon getIcon() {
        return super.getIcon(this.iconPath);
    }

    // ── GEÄNDERT: getPanel() (JGoodies → Swing) [1] ───────
    @Override
    public JPanel getPanel() {
        if (this.panel == null) {
            this.inhaberModel = new BasisInhaberModel();
            this.objektModel = new BasisObjektModel(
                "Standort", this.manager
                .getSettingsManager().getSetting(
                    "auik.prefs.abteilungsfilter"));

            TableFocusListener tfl =
                TableFocusListener.getInstance();
            getBetreiberTabelle().addFocusListener(tfl);
            getObjektTabelle().addFocusListener(tfl);

            JScrollPane betreiberScroller = new JScrollPane(
                getBetreiberTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JScrollPane objektScroller = new JScrollPane(
                getObjektTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JToolBar submitToolBar = new JToolBar();
            submitToolBar.setFloatable(false);
            submitToolBar.setRollover(true);
            submitToolBar.add(getSubmitButton());

            // ── GEÄNDERT: ComponentFactory → Swing [1] ────
            // BorderLayout wie Original [1]
            // JLabel statt AWT Label [1]
            JPanel restrictPanel =
                new JPanel(new BorderLayout());
            JPanel restrictButtonBar = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 3, 0));
            restrictButtonBar.add(getAwsvButton());
            restrictButtonBar.add(getIndirektButton());
            restrictButtonBar.add(getProbepktButton());
            restrictButtonBar.add(getAnfallButton());
            restrictButtonBar.add(getGenButton());
            restrictPanel.add(
                new JLabel("Objekte einschränken"),
                BorderLayout.WEST);
            restrictPanel.add(
                restrictButtonBar, BorderLayout.CENTER);

            // ── Unveränderter TabAction Code [1] ──────────
            TabAction ta = new TabAction();
            ta.addComp(getSuchFeld());
            ta.addComp(getHausnrFeld());
            ta.addComp(getOrtFeld());
            ta.addComp(getBetreiberTabelle());
            ta.addComp(getObjektTabelle());

            // ── GEÄNDERT: ComponentFactory → JSplitPane [1]
            this.tabellenSplit = new JSplitPane(
            	    JSplitPane.VERTICAL_SPLIT,
            	    betreiberScroller,
            	    objektScroller);
            	this.tabellenSplit.setResizeWeight(0.6);
            	this.tabellenSplit.setMinimumSize(
            	    new java.awt.Dimension(0, 100));
            	objektScroller.setPreferredSize(
            	    new java.awt.Dimension(0, 150));

            // ── GEÄNDERT: FormLayout → GridBagLayout [1] ──
            JPanel mainPanel = new JPanel(
                new GridBagLayout());
            GridBagConstraints gbc =
                new GridBagConstraints();
            gbc.insets = new Insets(3, 3, 3, 3);

            // ── ZEILE 1: Suche ────────────────────────────
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            mainPanel.add(getSuchBox(), gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(getSuchFeld(), gbc);

         // SubmitButtonBetreiber rechts ausgerichtet
         // wie Original cc.xyw(13, 1, 3) [1]
         gbc.gridx = 2;
         gbc.weightx = 0;
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         gbc.fill = GridBagConstraints.NONE;
         gbc.anchor = GridBagConstraints.EAST;
         mainPanel.add(getSubmitButtonBetreiber(), gbc);
         gbc.gridwidth = 1;
         gbc.anchor = GridBagConstraints.WEST;

            // ── ZEILE 2: Adresse ──────────────────────────
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            mainPanel.add(new JLabel("Straße"), gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(getStrassenFeld(), gbc);

            gbc.gridx = 2;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            mainPanel.add(new JLabel("Haus-Nr."), gbc);

            gbc.gridx = 3;
            gbc.weightx = 0;
            gbc.ipadx = 40;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(getHausnrFeld(), gbc);
            gbc.ipadx = 0;

            gbc.gridx = 4;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            mainPanel.add(new JLabel("Ort"), gbc);

            gbc.gridx = 5;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(getOrtFeld(), gbc);

            gbc.gridx = 6;
            gbc.weightx = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainPanel.add(getSubmitButton(), gbc);
            gbc.gridwidth = 1;

            // ── ZEILE 3: TabellenSplit (wächst) ───────────
            // Entspricht "150dlu:grow" aus Original [1]
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            mainPanel.add(this.tabellenSplit, gbc);

            // ── ZURÜCKSETZEN nach tabellenSplit ───────────
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridx = 0;

            // ── ZEILE 4: RestrictPanel ────────────────────
            // Entspricht "30" aus Original [1]
            gbc.gridy = 3;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(3, 3, 3, 3);
            mainPanel.add(restrictPanel, gbc);

            // ── GEÄNDERT: Paddings.DIALOG → EmptyBorder [1]
            mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                    7, 7, 7, 7));

            this.panel = mainPanel;
        }
        return this.panel;
    }

    // ── Unveränderter show() [1] ───────────────────────────
    @Override
    public void show() {
        super.show();
        if (SettingsManager.getInstance().getSetting(
            "auik.prefs.divloc_betreiber") != null) {
            double divloc = Double.parseDouble(
                SettingsManager.getInstance()
                .getSetting("auik.prefs.divloc_betreiber"));
            this.tabellenSplit.setDividerLocation(divloc);
        }
        filterBetreiberListe(suchFeld);
    }

    // ── Unveränderter hide() [1] ───────────────────────────
    @Override
    public void hide() {
        super.hide();
        if ((this.tabellenSplit != null)
            && (this.tabellenSplit.getDividerLocation()
                != -1)) {
            double divloc =
                (float) (this.tabellenSplit
                    .getDividerLocation())
                / (this.tabellenSplit.getHeight());
            if (divloc >= 0.0 && divloc <= 1.0) {
                SettingsManager.getInstance().setSetting(
                    "auik.prefs.divloc_betreiber",
                    Double.toString(divloc), true);
            }
        }
    }

    // ── Unveränderter doSearch() [1] ──────────────────────
    private void doSearch() {
        final String name = getSuchFeld().getText();
        final String strasse = getStrassenFeld().getText();
        int hausnr;
        try {
            hausnr = Integer.parseInt(
                getHausnrFeld().getText());
        } catch (NumberFormatException e1) {
            hausnr = -1;
        }
        final int fhausnr = hausnr;
        final String ort = getOrtFeld().getText();
        final String property = (String) ((NamedObject)
            getSuchBox().getSelectedItem()).getValue();
        SwingWorkerVariant worker = new SwingWorkerVariant(
            getBetreiberTabelle()) {
            @Override
            protected void doNonUILogic()
                throws RuntimeException {
                BasisAdresseSuchen.this.inhaberModel
                    .filterAllList(
                        name, strasse, fhausnr,
                        ort, property);
            }
            @Override
            protected void doUIUpdateLogic()
                throws RuntimeException {
                getBetreiberTabelle().clearSelection();
                BasisAdresseSuchen.this.inhaberModel
                    .fireTableDataChanged();
                String statusMsg = "Suche: "
                    + BasisAdresseSuchen.this.inhaberModel
                        .getRowCount()
                    + " Ergebnis";
                if (BasisAdresseSuchen.this.inhaberModel
                    .getRowCount() != 1) {
                    statusMsg += "se";
                }
                statusMsg += ".";
                BasisAdresseSuchen.this.frame
                    .changeStatus(statusMsg);
                BasisAdresseSuchen.this.inhaberModel
                    .fireTableDataChanged();
            }
        };
        worker.start();
    }

    // ── Unveränderter filterBetreiberListe() [1] ──────────
    public void filterBetreiberListe(
        Component focusComp) {
        log.debug("Start filterStandortListe()");
        int hausnr;
        try {
            hausnr = Integer.parseInt(
                getHausnrFeld().getText());
        } catch (NumberFormatException e1) {
            hausnr = -1;
        }
        final int fhausnr = hausnr;
        String name = getSuchFeld().getText();
        String str = getStrassenFeld().getText();
        final String ort = getOrtFeld().getText();
        SwingWorkerVariant worker =
            new SwingWorkerVariant(focusComp) {
            @Override
            protected void doNonUILogic() {
                if (!name.isEmpty()
                    || !str.isEmpty()
                    || !ort.isEmpty()) {
                    BasisAdresseSuchen.this.inhaberModel
                        .filterBetreiber(
                            getSuchFeld().getText(),
                            getStrassenFeld().getText(),
                            fhausnr, ort);
                }
            }
            @Override
            protected void doUIUpdateLogic() {
                getBetreiberTabelle().clearSelection();
                BasisAdresseSuchen.this.inhaberModel
                    .fireTableDataChanged();
                String statusMsg = "Suche: "
                    + BasisAdresseSuchen.this.inhaberModel
                        .getRowCount()
                    + " Ergebnis";
                if (BasisAdresseSuchen.this.inhaberModel
                    .getRowCount() != 1) {
                    statusMsg += "se";
                }
                statusMsg += ".";
                BasisAdresseSuchen.this.frame
                    .changeStatus(statusMsg);
            }
        };
        this.frame.changeStatus("Suche...");
        worker.start();
        log.debug("End filterStandortListe()");
    }

    // ── Unveränderter updateObjekte() [1] ─────────────────
    public void updateObjekte() {
        log.debug("Start updateObjekte()");
        ListSelectionModel lsm =
            getBetreiberTabelle().getSelectionModel();
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            Inhaber betr = (Inhaber)
                this.inhaberModel.getRow(selectedRow);
            log.debug("Betreiber (ID"
                + betr.getId() + ") angewählt.");
            this.inhaber = betr;
            searchObjekteByBetreiber(betr);
        }
        log.debug("End updateObjekte()");
    }

    // ── Unveränderter editBetreiber() [1] ─────────────────
    public void editBetreiber(Inhaber betr) {
        BetreiberEditor editDialog =
            new BetreiberEditor(betr, this.frame);
        editDialog.setLocationRelativeTo(this.frame);
        editDialog.setVisible(true);
        updateBetreiberListe();
    }

    private void updateBetreiberListe() {
        filterBetreiberListe(betreiberTabelle);
    }

    // ── Unveränderter searchObjekteByBetreiber() [1] ──────
    public void searchObjekteByBetreiber(
        final Inhaber inh) {
        SwingWorkerVariant worker = new SwingWorkerVariant(
            getBetreiberTabelle()) {
            @Override
            protected void doNonUILogic()
                throws RuntimeException {
                BasisAdresseSuchen.this.objektModel
                    .searchByInhaber(inh);
            }
            @Override
            protected void doUIUpdateLogic()
                throws RuntimeException {
                BasisAdresseSuchen.this.objektModel
                    .fireTableDataChanged();
            }
        };
        worker.start();
    }

    public void searchObjekteByBetreiber(
        final Inhaber inh,
        final Integer istartid) {
        SwingWorkerVariant worker = new SwingWorkerVariant(
            getBetreiberTabelle()) {
            @Override
            protected void doNonUILogic() {
                BasisAdresseSuchen.this.objektModel
                    .searchByInhaber(inh, istartid);
            }
            @Override
            protected void doUIUpdateLogic() {
                BasisAdresseSuchen.this.objektModel
                    .fireTableDataChanged();
            }
        };
        worker.start();
    }

    public void searchObjekteByBetreiber(
        final Inhaber inh,
        final String abteilung) {
        SwingWorkerVariant worker = new SwingWorkerVariant(
            getBetreiberTabelle()) {
            @Override
            protected void doNonUILogic()
                throws RuntimeException {
                BasisAdresseSuchen.this.objektModel
                    .searchByInhaber(inh, abteilung);
            }
            @Override
            protected void doUIUpdateLogic()
                throws RuntimeException {
                BasisAdresseSuchen.this.objektModel
                    .fireTableDataChanged();
            }
        };
        worker.start();
    }

    // ── Unveränderter Actions [1] ──────────────────────────
    private Action getBetreiberEditAction() {
        if (this.betreiberEditAction == null) {
            this.betreiberEditAction =
                new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID =
                    5689189314194296978L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getBetreiberTabelle()
                        .getSelectedRow();
                    if (row != -1) {
                        Inhaber betr = (Inhaber)
                            BasisAdresseSuchen.this
                            .inhaberModel.getRow(row);
                        editBetreiber(betr);
                    }
                }
            };
            this.betreiberEditAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_B));
            this.betreiberEditAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(
                    KeyEvent.VK_ENTER, 0, false));
        }
        return this.betreiberEditAction;
    }

    private Action getBetreiberLoeschAction() {
        if (this.betreiberLoeschAction == null) {
            this.betreiberLoeschAction =
                new AbstractAction("Löschen") {
                private static final long serialVersionUID =
                    6709934716520847123L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getBetreiberTabelle()
                        .getSelectedRow();
                    if (row != -1
                        && getBetreiberTabelle()
                            .getEditingRow() == -1) {
                        if (BasisAdresseSuchen.this
                            .objektModel
                            .getRowCount() != 0) {
                            BasisAdresseSuchen.this.frame
                                .changeStatus(
                                "Kann Betreiber nicht"
                                + " löschen: Zu erst alle"
                                + " zugehörigen Objekte"
                                + " löschen!",
                                HauptFrame.ERROR_COLOR);
                        } else {
                            Inhaber betr = (Inhaber)
                                BasisAdresseSuchen.this
                                .inhaberModel.getRow(row);
                            if (GUIManager.getInstance()
                                .showQuestion(
                                "Soll der/die Betreiber:in '"
                                + betr
                                + "' wirklich gelöscht"
                                + " werden?",
                                "Löschen bestätigen")) {
                                if (BasisAdresseSuchen.this
                                    .inhaberModel
                                    .removeRow(row)) {
                                    BasisAdresseSuchen.this
                                        .frame.changeStatus(
                                        "Betreiber:in gelöscht.",
                                        HauptFrame
                                            .SUCCESS_COLOR);
                                    log.debug("Betreiber "
                                        + betr.getId()
                                        + " wurde gelöscht!");
                                } else {
                                    BasisAdresseSuchen.this
                                        .frame.changeStatus(
                                        "Konnte den Betreiber"
                                        + " nicht löschen!",
                                        HauptFrame
                                            .ERROR_COLOR);
                                }
                            }
                        }
                    }
                }
            };
            this.betreiberLoeschAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_L));
            this.betreiberLoeschAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(
                    KeyEvent.VK_DELETE, 0, false));
        }
        return this.betreiberLoeschAction;
    }

    private Action getObjektNeuAction() {
        if (this.objektNeuAction == null) {
            this.objektNeuAction =
                new AbstractAction("Neues Objekt") {
                private static final long serialVersionUID =
                    1922038365500278302L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = BasisAdresseSuchen.this
                        .betreiberTabelle.getSelectedRow();
                    if (row != -1) {
                        Inhaber betr = (Inhaber)
                            BasisAdresseSuchen.this
                            .inhaberModel.getRow(row);
                        if (betr.getStandorts().size()
                            > 0) {
                            BasisAdresseSuchen.this.manager
                                .getSettingsManager()
                                .setSetting(
                                "auik.imc.use_standort",
                                betr.getStandort()
                                    .getId().intValue(),
                                false);
                        }
                        BasisAdresseSuchen.this.manager
                            .getSettingsManager()
                            .setSetting(
                            "auik.imc.use_betreiber",
                            betr.getId().intValue(),
                            false);
                        BasisAdresseSuchen.this.manager
                            .switchModul(
                                "m_objekt_bearbeiten");
                    }
                }
            };
            this.objektNeuAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_O));
        }
        return this.objektNeuAction;
    }

    private Action getObjektEditAction() {
        if (this.objektEditAction == null) {
            this.objektEditAction =
                new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID =
                    374432667200396085L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = BasisAdresseSuchen.this
                        .objektTabelle.getSelectedRow();
                    Objekt obj = BasisAdresseSuchen.this
                        .objektModel.getRow(row);
                    if (row != -1
                        || (!(obj.getObjektarten().getId()
                            .equals(DatabaseConstants
                            .BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)))) {
                        BasisAdresseSuchen.this.manager
                            .getSettingsManager()
                            .setSetting(
                            "auik.imc.edit_object",
                            obj.getId().intValue(), false);
                        BasisAdresseSuchen.this.manager
                            .switchModul(
                                "m_objekt_bearbeiten");
                    } else if (row != -1
                        || obj.getObjektarten().getId()
                            .equals(DatabaseConstants
                            .BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)) {
                        BasisAdresseSuchen.this.manager
                            .getSettingsManager()
                            .setSetting(
                            "auik.imc.edit_object",
                            obj.getId().intValue(), false);
                        BasisAdresseSuchen.this.manager
                            .switchModul("m_sielhaut1");
                    }
                }
            };
            this.objektEditAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_B));
            this.objektEditAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(
                    KeyEvent.VK_ENTER, 0, false));
        }
        return this.objektEditAction;
    }

    private Action getObjektBetreiberEditAction() {
        if (this.objektBetreiberEditAction == null) {
            this.objektBetreiberEditAction =
                new AbstractAction("Betreiber:in") {
                private static final long serialVersionUID =
                    9162213813094661474L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle()
                        .getSelectedRow();
                    if (row != -1) {
                        Objekt obj =
                            BasisAdresseSuchen.this
                            .objektModel.getRow(row);
                        editBetreiber(obj.getBetreiberid());
                    }
                }
            };
            this.objektBetreiberEditAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_F1));
            this.objektBetreiberEditAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(
                    KeyEvent.VK_F1, 0, false));
        }
        return this.objektBetreiberEditAction;
    }

    private Action getObjektStandortEditAction() {
        if (this.objektStandortEditAction == null) {
            this.objektStandortEditAction =
                new AbstractAction("Standort") {
                private static final long serialVersionUID =
                    8864140472500015190L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle()
                        .getSelectedRow();
                    if (row != -1) {
                        Objekt obj =
                            BasisAdresseSuchen.this
                            .objektModel.getRow(row);
                        editBetreiber(obj.getBetreiberid());
                    }
                }
            };
            this.objektStandortEditAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_F2));
            this.objektStandortEditAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(
                    KeyEvent.VK_F2, 0, false));
        }
        return this.objektStandortEditAction;
    }

    private Action getObjektLoeschAction() {
        if (this.objektLoeschAction == null) {
            this.objektLoeschAction =
                new AbstractAction("Löschen") {
                private static final long serialVersionUID =
                    5285618973743780113L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle()
                        .getSelectedRow();
                    if (row != -1
                        && getObjektTabelle()
                            .getEditingRow() == -1) {
                        Objekt objekt =
                            BasisAdresseSuchen.this
                            .objektModel.getRow(row);
                        if (GUIManager.getInstance()
                            .showQuestion(
                            "Soll das Objekt "
                            + objekt.getId()
                            + " und alle seine Fachdaten"
                            + " wirklich gelöscht werden?\n"
                            + "Hinweis: Manche Objekte"
                            + " können auch erst gelöscht"
                            + " werden, wenn für sie\n"
                            + "keine Fachdaten mehr"
                            + " existieren.",
                            "Löschen bestätigen")) {
                            if (BasisAdresseSuchen.this
                                .objektModel
                                .removeRow(row)) {
                                BasisAdresseSuchen.this
                                    .frame.changeStatus(
                                    "Objekt gelöscht.",
                                    HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt "
                                    + objekt.getId()
                                    + " wurde gelöscht!");
                            } else {
                                BasisAdresseSuchen.this
                                    .frame.changeStatus(
                                    "Konnte das Objekt"
                                    + " nicht löschen!",
                                    HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.objektLoeschAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_L));
            this.objektLoeschAction.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(
                    KeyEvent.VK_DELETE, 0, false));
        }
        return this.objektLoeschAction;
    }

    // ── Unveränderter Popup Methoden [1] ──────────────────
    private void showBetreiberPopup(MouseEvent e) {
        if (this.betreiberPopup == null) {
            this.betreiberPopup =
                new JPopupMenu("Betreiber:in");
            JMenuItem bearbItem = new JMenuItem(
                getBetreiberEditAction());
            JMenuItem loeschItem = new JMenuItem(
                getBetreiberLoeschAction());
            JMenuItem neuItem = new JMenuItem(
                getObjektNeuAction());
            this.betreiberPopup.add(bearbItem);
            this.betreiberPopup.add(loeschItem);
            this.betreiberPopup.add(neuItem);
        }
        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.betreiberTabelle
                .rowAtPoint(origin);
            if (row != -1) {
                this.betreiberTabelle
                    .setRowSelectionInterval(row, row);
                this.betreiberPopup.show(
                    e.getComponent(),
                    e.getX(), e.getY());
            }
        }
    }

    private void showObjektPopup(MouseEvent e) {
        if (this.objektPopup == null) {
            this.objektPopup = new JPopupMenu("Objekt");
            JMenuItem bearbItem = new JMenuItem(
                getObjektEditAction());
            JMenuItem objbtrItem = new JMenuItem(
                getObjektBetreiberEditAction());
            JMenuItem objstdItem = new JMenuItem(
                getObjektStandortEditAction());
            JMenuItem loeschItem = new JMenuItem(
                getObjektLoeschAction());
            this.objektPopup.add(bearbItem);
            this.objektPopup.add(objbtrItem);
            this.objektPopup.add(objstdItem);
            this.objektPopup.add(loeschItem);
        }
        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektTabelle
                .rowAtPoint(origin);
            if (row != -1) {
                this.objektTabelle
                    .setRowSelectionInterval(row, row);
                this.objektPopup.show(
                    e.getComponent(),
                    e.getX(), e.getY());
            }
        }
    }

    // ── Unveränderter Tabellen [1] ─────────────────────────
    private JTable getBetreiberTabelle() {
        if (this.betreiberTabelle == null) {
            this.betreiberTabelle =
                new JTable(this.inhaberModel);
            ListSelectionModel rowSM =
                this.betreiberTabelle.getSelectionModel();
            rowSM.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(
                        ListSelectionEvent e) {
                        if (e.getValueIsAdjusting()) {
                            return;
                        }
                        updateObjekte();
                    }
                });
            this.betreiberTabelle.getColumnModel()
                .getColumn(0).setPreferredWidth(-10);
            this.betreiberTabelle.getColumnModel()
                .getColumn(1).setPreferredWidth(200);
            this.betreiberTabelle.getColumnModel()
                .getColumn(2).setPreferredWidth(200);
            this.betreiberTabelle.getColumnModel()
                .getColumn(3).setPreferredWidth(200);
            this.betreiberTabelle.getColumnModel()
                .getColumn(4).setPreferredWidth(200);
            this.betreiberTabelle.getColumnModel()
                .getColumn(5).setPreferredWidth(20);
            this.betreiberTabelle.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
            this.betreiberTabelle
                .setColumnSelectionAllowed(false);
            this.betreiberTabelle
                .setRowSelectionAllowed(true);
            this.betreiberTabelle.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(
                        java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2)
                            && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getBetreiberTabelle()
                                .rowAtPoint(origin);
                            Inhaber betr = (Inhaber)
                                BasisAdresseSuchen.this
                                .inhaberModel.getRow(row);
                            log.debug(
                                "Doppelklick auf Zeile "
                                + row);
                            editBetreiber(betr);
                        }
                    }
                    @Override
                    public void mousePressed(
                        MouseEvent e) {
                        showBetreiberPopup(e);
                    }
                    @Override
                    public void mouseReleased(
                        MouseEvent e) {
                        showBetreiberPopup(e);
                    }
                });
            this.betreiberTabelle.getInputMap().put(
                (KeyStroke) getBetreiberEditAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getBetreiberEditAction()
                    .getValue(Action.NAME));
            this.betreiberTabelle.getActionMap().put(
                getBetreiberEditAction()
                    .getValue(Action.NAME),
                getBetreiberEditAction());
            this.betreiberTabelle.getInputMap().put(
                (KeyStroke) getBetreiberLoeschAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getBetreiberLoeschAction()
                    .getValue(Action.NAME));
            this.betreiberTabelle.getActionMap().put(
                getBetreiberLoeschAction()
                    .getValue(Action.NAME),
                getBetreiberLoeschAction());
        }
        return this.betreiberTabelle;
    }

    private JTable getObjektTabelle() {
        if (this.objektTabelle == null) {
            this.objektTabelle =
                new JTable(this.objektModel) {
                public Component prepareRenderer(
                    TableCellRenderer renderer,
                    int row, int column) {
                    Component c = super.prepareRenderer(
                        renderer, row, column);
                    return c;
                }
            };
            this.objektTabelle.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
            this.objektTabelle.getColumnModel()
                .getColumn(0).setMaxWidth(60);
            this.objektTabelle.getColumnModel()
                .getColumn(0).setPreferredWidth(
                    this.objektTabelle.getColumnModel()
                        .getColumn(0).getMaxWidth() - 10);
            this.objektTabelle.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(
                        java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2)
                            && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getObjektTabelle()
                                .rowAtPoint(origin);
                            Objekt obj =
                                BasisAdresseSuchen.this
                                .objektModel.getRow(row);
                            if (row != -1
                                && (!(obj.getObjektarten()
                                    .getId().equals(
                                    DatabaseConstants
                                    .BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)))) {
                                BasisAdresseSuchen.this
                                    .manager
                                    .getSettingsManager()
                                    .setSetting(
                                    "auik.imc.edit_object",
                                    obj.getId().intValue(),
                                    false);
                                BasisAdresseSuchen.this
                                    .manager.switchModul(
                                    "m_objekt_bearbeiten");
                            } else if (row != -1
                                && obj.getObjektarten()
                                    .getId().equals(
                                    DatabaseConstants
                                    .BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)) {
                                BasisAdresseSuchen.this
                                    .manager
                                    .getSettingsManager()
                                    .setSetting(
                                    "auik.imc.edit_object",
                                    obj.getId().intValue(),
                                    false);
                                BasisAdresseSuchen.this
                                    .manager.switchModul(
                                    "m_sielhaut1");
                            }
                        }
                    }
                    @Override
                    public void mousePressed(
                        MouseEvent e) {
                        showObjektPopup(e);
                    }
                    @Override
                    public void mouseReleased(
                        MouseEvent e) {
                        showObjektPopup(e);
                    }
                });
            this.objektTabelle.getInputMap().put(
                (KeyStroke) getObjektEditAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getObjektEditAction()
                    .getValue(Action.NAME));
            this.objektTabelle.getActionMap().put(
                getObjektEditAction()
                    .getValue(Action.NAME),
                getObjektEditAction());
            this.objektTabelle.getInputMap().put(
                (KeyStroke) getObjektBetreiberEditAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getObjektBetreiberEditAction()
                    .getValue(Action.NAME));
            this.objektTabelle.getActionMap().put(
                getObjektBetreiberEditAction()
                    .getValue(Action.NAME),
                getObjektBetreiberEditAction());
            this.objektTabelle.getInputMap().put(
                (KeyStroke) getObjektStandortEditAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getObjektStandortEditAction()
                    .getValue(Action.NAME));
            this.objektTabelle.getActionMap().put(
                getObjektStandortEditAction()
                    .getValue(Action.NAME),
                getObjektStandortEditAction());
            this.objektTabelle.getInputMap().put(
                (KeyStroke) getObjektLoeschAction()
                    .getValue(Action.ACCELERATOR_KEY),
                getObjektLoeschAction()
                    .getValue(Action.NAME));
            this.objektTabelle.getActionMap().put(
                getObjektLoeschAction()
                    .getValue(Action.NAME),
                getObjektLoeschAction());
        }
        return this.objektTabelle;
    }

    // ── Unveränderter Buttons [1] ──────────────────────────
    private JButton getSubmitButton() {
        if (this.submitButton == null) {
            this.submitButton =
                new JButton("Alle Adressen");
            this.submitButton.setToolTipText(
                "Suche starten");
            this.submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        getSuchTimer().stop();
                        doSearch();
                    }
                });
        }
        return this.submitButton;
    }

    private JButton getSubmitButtonBetreiber() {
        if (this.submitButtonBetreiber == null) {
            this.submitButtonBetreiber = new JButton(
                "Betreiber:in",
                AuikUtils.getIcon(16, "key_enter.png"));
            this.submitButtonBetreiber.setToolTipText(
                "Suche starten");
            this.submitButtonBetreiber.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        getSuchTimer().stop();
                        filterBetreiberListe(
                            getBetreiberTabelle());
                    }
                });
        }
        return this.submitButtonBetreiber;
    }

    private JTextField getSuchFeld() {
        if (this.suchFeld == null) {
            this.suchFeld = new JTextField();
            this.suchFeld.requestFocusInWindow();
            this.suchFeld.setFocusTraversalKeys(
                KeyboardFocusManager
                    .FORWARD_TRAVERSAL_KEYS,
                Set.of());
            this.suchFeld.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        filterBetreiberListe(
                            getBetreiberTabelle());
                    }
                });
        }
        return this.suchFeld;
    }

    private JTextField getStrassenFeld() {
        if (this.strassenFeld == null) {
            this.strassenFeld = new JTextField("");
            this.strassenFeld.setFocusTraversalKeys(
                KeyboardFocusManager
                    .FORWARD_TRAVERSAL_KEYS,
                Set.of());
            this.strassenFeld.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        getSuchTimer().stop();
                        filterBetreiberListe(
                            getBetreiberTabelle());
                    }
                });
            this.strassenFeld.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode()
                            == KeyEvent.VK_TAB) {
                            getSuchTimer().stop();
                            filterBetreiberListe(
                                getBetreiberTabelle());
                        }
                    }
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (Character.isLetterOrDigit(
                            e.getKeyChar())) {
                            if (getSuchTimer()
                                .isRunning()) {
                                getSuchTimer().restart();
                            } else {
                                getSuchTimer().start();
                            }
                        }
                    }
                });
        }
        return this.strassenFeld;
    }

    private JTextField getOrtFeld() {
        if (this.ortFeld == null) {
            this.ortFeld = new JTextField("");
            this.ortFeld.setFocusTraversalKeys(
                KeyboardFocusManager
                    .FORWARD_TRAVERSAL_KEYS,
                Set.of());
            this.ortFeld.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        getSuchTimer().stop();
                        filterBetreiberListe(
                            getBetreiberTabelle());
                    }
                });
            this.ortFeld.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode()
                            == KeyEvent.VK_TAB) {
                            getSuchTimer().stop();
                            doSearch();
                        }
                    }
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (Character.isLetterOrDigit(
                            e.getKeyChar())) {
                            if (getSuchTimer()
                                .isRunning()) {
                                getSuchTimer().restart();
                            } else {
                                getSuchTimer().start();
                            }
                        }
                    }
                });
        }
        return this.ortFeld;
    }

    private JTextField getHausnrFeld() {
        if (this.hausnrFeld == null) {
            this.hausnrFeld = new BasicEntryField();
            this.hausnrFeld.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        doSearch();
                    }
                });
        }
        return this.hausnrFeld;
    }

    // ── Unveränderter Filter Buttons [1] ──────────────────
    private JButton getAwsvButton() {
        if (this.awsvButton == null) {
            this.awsvButton = new JButton("AwSV");
            this.awsvButton.setToolTipText(
                "nur AwSV Objekte");
            this.awsvButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        ListSelectionModel lsm =
                            getBetreiberTabelle()
                            .getSelectionModel();
                        if (!lsm.isSelectionEmpty()) {
                            int selectedRow =
                                lsm.getMinSelectionIndex();
                            Inhaber inh =
                                BasisAdresseSuchen.this
                                .inhaberModel
                                .getRow(selectedRow);
                            log.debug("Standort "
                                + inh + " angewählt.");
                            searchObjekteByBetreiber(
                                inh,
                                DatabaseConstants
                                .BASIS_OBJEKTART_ABTEILUNG_AWSV);
                        }
                    }
                });
        }
        return this.awsvButton;
    }

    private JButton getProbepktButton() {
        if (this.probepktButton == null) {
            this.probepktButton =
                new JButton("Probepunkte");
            this.probepktButton.setToolTipText(
                "nur die Probenahmepunkte anzeigen");
            this.probepktButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        ListSelectionModel lsm =
                            getBetreiberTabelle()
                            .getSelectionModel();
                        if (!lsm.isSelectionEmpty()) {
                            int selectedRow =
                                lsm.getMinSelectionIndex();
                            Inhaber inh =
                                BasisAdresseSuchen.this
                                .inhaberModel
                                .getRow(selectedRow);
                            log.debug("Standort "
                                + inh + " angewählt.");
                            searchObjekteByBetreiber(
                                inh,
                                DatabaseConstants
                                .BASIS_OBJEKTART_ID_PROBEPUNKT);
                        }
                    }
                });
        }
        return this.probepktButton;
    }

    private JButton getIndirektButton() {
        if (this.indirektButton == null) {
            this.indirektButton =
                new JButton("Indirekt-Einl.");
            this.indirektButton.setToolTipText(
                "nur die Indirekteinleiter anzeigen");
            this.indirektButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        ListSelectionModel lsm =
                            getBetreiberTabelle()
                            .getSelectionModel();
                        if (!lsm.isSelectionEmpty()) {
                            int selectedRow =
                                lsm.getMinSelectionIndex();
                            Inhaber inh =
                                BasisAdresseSuchen.this
                                .inhaberModel
                                .getRow(selectedRow);
                            log.debug("Standort "
                                + inh + " angewählt.");
                            searchObjekteByBetreiber(
                                inh,
                                DatabaseConstants
                                .BASIS_OBJEKTART_ID_PROBEPUNKT);
                        }
                    }
                });
        }
        return this.indirektButton;
    }

    private JButton getAnfallButton() {
        if (this.anfallButton == null) {
            this.anfallButton =
                new JButton("Anfallstellen");
            this.anfallButton.setToolTipText(
                "nur die Anfallstellen anzeigen");
            this.anfallButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        ListSelectionModel lsm =
                            getBetreiberTabelle()
                            .getSelectionModel();
                        if (!lsm.isSelectionEmpty()) {
                            int selectedRow =
                                lsm.getMinSelectionIndex();
                            Inhaber inh =
                                BasisAdresseSuchen.this
                                .inhaberModel
                                .getRow(selectedRow);
                            log.debug("Standort "
                                + inh + " angewählt.");
                            searchObjekteByBetreiber(
                                inh,
                                DatabaseConstants
                                .BASIS_OBJEKTART_ID_ANFALLSTELLE);
                        }
                    }
                });
        }
        return this.anfallButton;
    }

    private JButton getGenButton() {
        if (this.genButton == null) {
            this.genButton =
                new JButton("Genehmigungen");
            this.genButton.setToolTipText(
                "nur die Genehmigungen anzeigen");
            this.genButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        ListSelectionModel lsm =
                            getBetreiberTabelle()
                            .getSelectionModel();
                        if (!lsm.isSelectionEmpty()) {
                            int selectedRow =
                                lsm.getMinSelectionIndex();
                            Inhaber inh =
                                BasisAdresseSuchen.this
                                .inhaberModel
                                .getRow(selectedRow);
                            log.debug("Standort "
                                + inh + " angewählt.");
                            searchObjekteByBetreiber(
                                inh,
                                DatabaseConstants
                                .BASIS_OBJEKTART_ID_GENEHMIGUNG);
                        }
                    }
                });
        }
        return this.genButton;
    }

    // ── Unveränderter Timer [1] ────────────────────────────
    private Timer getSuchTimer() {
        if (this.suchTimer == null) {
            this.suchTimer = new Timer(900,
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        SwingWorkerVariant worker =
                            new SwingWorkerVariant(
                                getStrassenFeld()) {
                            protected String oldText = "";
                            private String newText = "";
                            @Override
                            protected void doNonUILogic() {
                                this.oldText =
                                    getStrassenFeld()
                                    .getText();
                                if (this.oldText
                                    .equals("")) {
                                    this.newText = "";
                                } else {
                                    String suchText =
                                        AuikUtils
                                        .sanitizeQueryInput(
                                            this.oldText);
                                    String str =
                                        DatabaseQuery
                                        .getTabStreet(
                                            suchText);
                                    if (str != null) {
                                        this.newText = str;
                                    } else {
                                        this.newText =
                                            this.oldText;
                                    }
                                }
                            }
                            @Override
                            protected void
                                doUIUpdateLogic() {
                                getStrassenFeld().setText(
                                    this.newText);
                                getStrassenFeld()
                                    .setSelectionStart(
                                    this.oldText.length());
                                getStrassenFeld()
                                    .setSelectionEnd(
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

    // ── Unveränderter SuchBox [1] ──────────────────────────
    private JComboBox<NamedObject> getSuchBox() {
        if (this.suchBox == null) {
            this.suchBox = new JComboBox<>(
                new NamedObject[] {
                    new NamedObject("Anrede", "anrede"),
                    new NamedObject("Vorname", "vorname"),
                    new NamedObject("Name", "name"),
                    new NamedObject("Zusatz", "zusatz"),
                    new NamedObject("Irgendwo", null)});
            this.suchBox.setSelectedIndex(2);
        }
        return this.suchBox;
    }
}