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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.component.Factory;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisAdresse;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisObjektModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.NamedObject;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Ein Modul zum Suchen und Bearbeiten eines Betreibers.
 * @author David Klotz
 */
public class BasisBetreiberSuchen extends AbstractModul {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

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
    private Action objektBetreiberEditAction;
    private Action objektStandortEditAction;
    private Action objektLoeschAction;
    private JPopupMenu objektPopup;

    private BasisAdresseModel betreiberModel;
    private BasisObjektModel objektModel;

    /**
     * Wird benutzt, um nach dem Bearbeiten etc. wieder den selben Betreiber in
     * der Liste auszuwählen.
     */
    private BasisAdresse lastBetreiber;

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Betreiber-Adresse suchen";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * @return "m_betreiber_suchen"
     */
    @Override
    public String getIdentifier() {
        return "m_betreiber_suchen";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory() {
        return "Betriebe";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
     * @see de.bielefeld.umweltamt.aui.AbstractModul#getIcon(String)
     */
    @Override
    public Icon getIcon() {
        return super.getIcon(this.iconPath);
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
    public JPanel getPanel() {
        if (this.panel == null) {
            this.betreiberModel = new BasisAdresseModel();
            this.objektModel = new BasisObjektModel("Standort", this.manager
                .getSettingsManager().getSetting("auik.prefs.abteilungsfilter"));

            TableFocusListener tfl = TableFocusListener.getInstance();
            getBetreiberTabelle().addFocusListener(tfl);
            getObjektTabelle().addFocusListener(tfl);

            JScrollPane betreiberScroller = new JScrollPane(
                getBetreiberTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            JScrollPane objektScroller = new JScrollPane(getObjektTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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

            this.tabellenSplit = Factory.createStrippedSplitPane(
                JSplitPane.VERTICAL_SPLIT, betreiberScroller, objektScroller,
                0.7);

            FormLayout layout = new FormLayout(
                "50dlu, 4dlu, pref:grow, 3dlu, min(16dlu;p)", // spalten
                "pref, 3dlu, 150dlu:grow(1.0)"); // zeilen

            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            builder.add(getSuchBox(), cc.xy(1, 1));
            builder.add(getSuchFeld(), cc.xy(3, 1));
            builder.add(submitToolBar, cc.xy(5, 1));
            builder.add(this.tabellenSplit, cc.xyw(1, 3, 5));

            this.panel = builder.getPanel();
        }
        return this.panel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#show()
     */
    @Override
    public void show() {
        super.show();

        // Gespeicherte Position des Dividers setzen
        if (SettingsManager.getInstance().getSetting(
            "auik.prefs.divloc_betreiber") != null) {
            double divloc = Double.parseDouble(SettingsManager.getInstance()
                .getSetting("auik.prefs.divloc_betreiber"));
            this.tabellenSplit.setDividerLocation(divloc);
        }

        this.lastBetreiber = null;
        updateBetreiberListe();
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#hide()
     */
    @Override
    public void hide() {
        super.hide();

        // Position des Dividers des SplitPanes speichern
        if ((this.tabellenSplit != null)
            && (this.tabellenSplit.getDividerLocation() != -1)) {
            double divloc = (float) (this.tabellenSplit.getDividerLocation())
                / (this.tabellenSplit.getHeight());
            if (divloc >= 0.0 && divloc <= 1.0) {
                SettingsManager.getInstance().setSetting(
                    "auik.prefs.divloc_betreiber", Double.toString(divloc),
                    true);
            }
        }
    }

    public void updateBetreiberListe() {
        log.debug("Start updateBetreiberListe()");
        SwingWorkerVariant worker = new SwingWorkerVariant(getSuchFeld()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                BasisBetreiberSuchen.this.betreiberModel.updateList();
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                BasisBetreiberSuchen.this.betreiberModel.fireTableDataChanged();

                if (BasisBetreiberSuchen.this.lastBetreiber != null) {
                    // Wenn der Betreiber noch in der Liste ist, wird er
                    // ausgewählt.
                    int row = BasisBetreiberSuchen.this.betreiberModel
                        .getList().indexOf(
                            BasisBetreiberSuchen.this.lastBetreiber);
                    if (row != -1) {
                        getBetreiberTabelle().setRowSelectionInterval(row, row);
                        getBetreiberTabelle().scrollRectToVisible(
                            getBetreiberTabelle().getCellRect(row, 0, true));
                        getBetreiberTabelle().requestFocus();
                    }
                } else {
                    int betreiberCount = BasisBetreiberSuchen.this.betreiberModel
                        .getRowCount();
                    if (betreiberCount > 0) {
                        String statusMsg = "Suche: " + betreiberCount
                            + " Ergebnis";
                        if (betreiberCount != 1) {
                            statusMsg += "se";
                        }
                        statusMsg += ".";
                        BasisBetreiberSuchen.this.frame.changeStatus(statusMsg);
                    }
                }

                updateObjekte();
            }
        };

        worker.start();
        log.debug("End updateBetreiberListe()");
    }

    public void updateObjekte() {
        log.debug("Start updateObjekte()");
        ListSelectionModel lsm = getBetreiberTabelle().getSelectionModel();
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            BasisAdresse betr = this.betreiberModel.getRow(selectedRow);
            log.debug("Betreiber " + betr.getBetrname() + " (ID"
                + betr.getId() + ") angewählt.");
            searchObjekteByBetreiber(betr);
        }
        log.debug("End updateObjekte()");
    }

    /**
     * öffnet einen Dialog um einen Betreiber-Datensatz zu bearbeiten.
     * @param betr Der Betreiber
     */
    public void editBetreiber(BasisAdresse betr) {
        BetreiberEditor editDialog = new BetreiberEditor(betr, this.frame);
        editDialog.setLocationRelativeTo(this.frame);

        editDialog.setVisible(true);

        this.lastBetreiber = betr;

        // Nach dem Bearbeiten die Liste updaten, damit unsere Änderungen auch
        // angezeigt werden.
        updateBetreiberListe();
    }

    /**
     * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines
     * Betreibers.
     * @param betreiberid Die Betreiber-Id
     */
    public void searchObjekteByBetreiber(final BasisAdresse betreiber) {
        // ... siehe show()
        SwingWorkerVariant worker = new SwingWorkerVariant(
            getBetreiberTabelle()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                BasisBetreiberSuchen.this.objektModel
                    .searchByBetreiber(betreiber);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                BasisBetreiberSuchen.this.objektModel.fireTableDataChanged();
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
        log.debug("Start filterBetreiberListe()");
        SwingWorkerVariant worker = new SwingWorkerVariant(
            getBetreiberTabelle()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                BasisBetreiberSuchen.this.betreiberModel.filterList(suche,
                    column);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                getBetreiberTabelle().clearSelection();

                BasisBetreiberSuchen.this.betreiberModel.fireTableDataChanged();
                String statusMsg = "Suche: "
                    + BasisBetreiberSuchen.this.betreiberModel.getRowCount()
                    + " Ergebnis";
                if (BasisBetreiberSuchen.this.betreiberModel.getRowCount() != 1) {
                    statusMsg += "se";
                }
                statusMsg += ".";
                BasisBetreiberSuchen.this.frame.changeStatus(statusMsg);
            }
        };

        this.frame.changeStatus("Suche...");
        worker.start();
        log.debug("End filterBetreiberListe()");
    }

    private Action getBetreiberEditAction() {
        if (this.betreiberEditAction == null) {
            this.betreiberEditAction = new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID = 5689189314194296978L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getBetreiberTabelle().getSelectedRow();

                    // Natürlich nur editieren, wenn wirklich eine Zeile
                    // ausgewählt ist
                    if (row != -1) {
                        BasisAdresse betr = BasisBetreiberSuchen.this.betreiberModel
                            .getRow(row);
                        editBetreiber(betr);
                    }
                }
            };
            this.betreiberEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_B));
            this.betreiberEditAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
        }

        return this.betreiberEditAction;
    }

    private Action getBetreiberLoeschAction() {
        if (this.betreiberLoeschAction == null) {
            this.betreiberLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 6709934716520847123L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getBetreiberTabelle().getSelectedRow();
                    if (row != -1
                        && getBetreiberTabelle().getEditingRow() == -1) {
                        if (BasisBetreiberSuchen.this.objektModel.getRowCount() != 0) {
                            BasisBetreiberSuchen.this.frame
                                .changeStatus(
                                    "Kann Betreiber nicht löschen: Zu erst alle zugehörigen Objekte löschen!",
                                    HauptFrame.ERROR_COLOR);
                        } else {
                            BasisAdresse betr = BasisBetreiberSuchen.this.betreiberModel
                                .getRow(row);

                            if (GUIManager.getInstance().showQuestion(
                                "Soll der Betreiber '" + betr
                                    + "' wirklich gelöscht werden?",
                                "Löschen bestätigen")) {
                                if (BasisBetreiberSuchen.this.betreiberModel
                                    .removeRow(row)) {
                                    BasisBetreiberSuchen.this.frame
                                        .changeStatus("Betreiber gelöscht.",
                                            HauptFrame.SUCCESS_COLOR);
                                    log.debug("Betreiber "
                                        + betr.getId()
                                        + " wurde gelöscht!");
                                } else {
                                    BasisBetreiberSuchen.this.frame
                                        .changeStatus(
                                            "Konnte den Betreiber nicht löschen!",
                                            HauptFrame.ERROR_COLOR);
                                }
                            }
                        }
                    }
                }
            };
            this.betreiberLoeschAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_L));
            this.betreiberLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.betreiberLoeschAction;
    }

    private Action getObjektNeuAction() {
        if (this.objektNeuAction == null) {
            this.objektNeuAction = new AbstractAction("Neues Objekt") {
                private static final long serialVersionUID = 1922038365500278302L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = BasisBetreiberSuchen.this.betreiberTabelle
                        .getSelectedRow();

                    if (row != -1) {
                        BasisAdresse betr = BasisBetreiberSuchen.this.betreiberModel
                            .getRow(row);
                        BasisBetreiberSuchen.this.manager.getSettingsManager()
                            .setSetting("auik.imc.use_betreiber",
                                betr.getId().intValue(), false);
                        BasisBetreiberSuchen.this.manager
                            .switchModul("m_objekt_bearbeiten");
                    }
                }
            };
            this.objektNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_O));
        }

        return this.objektNeuAction;
    }

    private Action getObjektEditAction() {
        if (this.objektEditAction == null) {
            this.objektEditAction = new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID = 374432667200396085L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = BasisBetreiberSuchen.this.objektTabelle
                        .getSelectedRow();
                    BasisObjekt obj = BasisBetreiberSuchen.this.objektModel
                        .getRow(row);
                    if (row != -1
                        || (!(obj.getBasisObjektarten().getId().equals(
                            DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)))) {
                        BasisBetreiberSuchen.this.manager.getSettingsManager()
                            .setSetting("auik.imc.edit_object",
                                obj.getObjektid().intValue(), false);
                        BasisBetreiberSuchen.this.manager
                            .switchModul("m_objekt_bearbeiten");
                    } else if (row != -1
                        || obj.getBasisObjektarten().getId().equals(
                            DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)) {
                        BasisBetreiberSuchen.this.manager.getSettingsManager()
                            .setSetting("auik.imc.edit_object",
                                obj.getObjektid().intValue(), false);
                        BasisBetreiberSuchen.this.manager
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
                        BasisObjekt obj = BasisBetreiberSuchen.this.objektModel
                            .getRow(row);
                        editBetreiber(obj.getBasisAdresse());
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
                        BasisObjekt obj = BasisBetreiberSuchen.this.objektModel
                            .getRow(row);
                        editBetreiber(obj.getBasisStandort());
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

    private Action getObjektLoeschAction() {
        if (this.objektLoeschAction == null) {
            this.objektLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 5285618973743780113L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle().getSelectedRow();
                    if (row != -1 && getObjektTabelle().getEditingRow() == -1) {
                        BasisObjekt objekt = BasisBetreiberSuchen.this.objektModel
                            .getRow(row);

                        if (GUIManager.getInstance().showQuestion(
                            "Soll das Objekt " + objekt.getObjektid()
                                + " und alle seine Fachdaten wirklich "
                                + "gelöscht werden?\n"
                                + "Hinweis: Manche Objekte können auch erst"
                                + " gelöscht werden, wenn für sie\n"
                                + "keine Fachdaten mehr existieren.",
                            "Löschen bestätigen")) {
                            if (BasisBetreiberSuchen.this.objektModel
                                .removeRow(row)) {
                                BasisBetreiberSuchen.this.frame.changeStatus(
                                    "Objekt gelöscht.",
                                    HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + objekt.getObjektid()
                                    + " wurde gelöscht!");
                            } else {
                                BasisBetreiberSuchen.this.frame.changeStatus(
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

    private void showBetreiberPopup(MouseEvent e) {
        if (this.betreiberPopup == null) {
            this.betreiberPopup = new JPopupMenu("Betreiber");
            JMenuItem bearbItem = new JMenuItem(getBetreiberEditAction());
            JMenuItem loeschItem = new JMenuItem(getBetreiberLoeschAction());
            JMenuItem neuItem = new JMenuItem(getObjektNeuAction());
            this.betreiberPopup.add(bearbItem);
            this.betreiberPopup.add(loeschItem);
            this.betreiberPopup.add(neuItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.betreiberTabelle.rowAtPoint(origin);

            if (row != -1) {
                this.betreiberTabelle.setRowSelectionInterval(row, row);
                this.betreiberPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private void showObjektPopup(MouseEvent e) {
        if (this.objektPopup == null) {
            this.objektPopup = new JPopupMenu("Objekt");
            JMenuItem bearbItem = new JMenuItem(getObjektEditAction());
            JMenuItem objbtrItem = new JMenuItem(getObjektBetreiberEditAction());
            JMenuItem objstdItem = new JMenuItem(getObjektStandortEditAction());
            JMenuItem loeschItem = new JMenuItem(getObjektLoeschAction());
            this.objektPopup.add(bearbItem);
            this.objektPopup.add(objbtrItem);
            this.objektPopup.add(objstdItem);
            this.objektPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektTabelle.rowAtPoint(origin);

            if (row != -1) {
                this.objektTabelle.setRowSelectionInterval(row, row);
                this.objektPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private JTable getBetreiberTabelle() {
        if (this.betreiberTabelle == null) {
            this.betreiberTabelle = new JTable(this.betreiberModel);

            // Wir wollen wissen, wenn eine andere Zeile ausgewählt wurde
            ListSelectionModel rowSM = this.betreiberTabelle
                .getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    // überzählige Events ignorieren
                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    updateObjekte();
                }
            });

            this.betreiberTabelle.getColumnModel().getColumn(0)
                .setPreferredWidth(200);
            this.betreiberTabelle.getColumnModel().getColumn(1)
                .setPreferredWidth(60);
            this.betreiberTabelle.getColumnModel().getColumn(2)
                .setPreferredWidth(70);
            this.betreiberTabelle.getColumnModel().getColumn(3)
            	.setPreferredWidth(70);
            this.betreiberTabelle.getColumnModel().getColumn(4)
            	.setPreferredWidth(70);
            this.betreiberTabelle.getColumnModel().getColumn(5)
        		.setPreferredWidth(70);
            this.betreiberTabelle.getColumnModel().getColumn(6)
            	.setPreferredWidth(30);

            this.betreiberTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.betreiberTabelle.setColumnSelectionAllowed(false);
            this.betreiberTabelle.setRowSelectionAllowed(true);

            this.betreiberTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getBetreiberTabelle().rowAtPoint(origin);

                            BasisAdresse betr = BasisBetreiberSuchen.this.betreiberModel
                                .getRow(row);
                            log.debug("Doppelklick auf Zeile " + row);
                            editBetreiber(betr);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        showBetreiberPopup(e);
                    }

                    @Override
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
            this.betreiberTabelle.getInputMap().put(
                (KeyStroke) getBetreiberEditAction().getValue(
                    Action.ACCELERATOR_KEY),
                getBetreiberEditAction().getValue(Action.NAME));
            // Die Action dem "Enter"-KeyStroke zuweisen
            this.betreiberTabelle.getActionMap().put(
                getBetreiberEditAction().getValue(Action.NAME),
                getBetreiberEditAction());

            this.betreiberTabelle.getInputMap().put(
                (KeyStroke) getBetreiberLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getBetreiberLoeschAction().getValue(Action.NAME));
            this.betreiberTabelle.getActionMap().put(
                getBetreiberLoeschAction().getValue(Action.NAME),
                getBetreiberLoeschAction());
        }
        return this.betreiberTabelle;
    }

    private JTable getObjektTabelle() {
        if (this.objektTabelle == null) {
            this.objektTabelle = new JTable(this.objektModel);
            this.objektTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            this.objektTabelle.getColumnModel().getColumn(0).setMaxWidth(60);
            this.objektTabelle
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                    this.objektTabelle.getColumnModel().getColumn(0)
                        .getMaxWidth() - 10);

            this.objektTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getObjektTabelle().rowAtPoint(origin);
                            BasisObjekt obj =
                                BasisBetreiberSuchen.this.objektModel.getRow(row);
                            if (row != -1
                                && (!(obj.getBasisObjektarten().getId().equals(
                                    DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)))) {
                                BasisBetreiberSuchen.this.manager
                                    .getSettingsManager().setSetting(
                                        "auik.imc.edit_object",
                                        obj.getObjektid().intValue(), false);
                                BasisBetreiberSuchen.this.manager
                                    .switchModul("m_objekt_bearbeiten");
                            } else if (row != -1
                                && obj.getBasisObjektarten().getId().equals(
                                    DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)) {
                                BasisBetreiberSuchen.this.manager
                                    .getSettingsManager().setSetting(
                                        "auik.imc.edit_object",
                                        obj.getObjektid().intValue(), false);
                                BasisBetreiberSuchen.this.manager
                                    .switchModul("m_sielhaut1");
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        showObjektPopup(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
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

    private JComboBox getSuchBox() {
        if (this.suchBox == null) {
            this.suchBox = new JComboBox(new NamedObject[] {
                    new NamedObject("Name:", "name"),
                    new NamedObject("Anrede:", "anrede"),
                    new NamedObject("Zusatz:", "zusatz"),
                    new NamedObject("Irgendwo", null)});
        }
        return this.suchBox;
    }

    private JTextField getSuchFeld() {
        if (this.suchFeld == null) {
            this.suchFeld = new JTextField();

            this.suchFeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String suche = getSuchFeld().getText();
                    String spalte = (String) ((NamedObject) getSuchBox()
                        .getSelectedItem()).getValue();
                    filterBetreiberListe(suche, spalte);
                }
            });
            this.suchFeld.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                Collections.EMPTY_SET);

            this.suchFeld.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_TAB) {
                        String suche = getSuchFeld().getText();
                        String spalte = (String) ((NamedObject) getSuchBox()
                            .getSelectedItem()).getValue();
                        filterBetreiberListe(suche, spalte);
                    }
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
                    String suche = getSuchFeld().getText();
                    String spalte = (String) ((NamedObject) getSuchBox()
                        .getSelectedItem()).getValue();
                    filterBetreiberListe(suche, spalte);
                }
            });
        }

        return this.submitButton;
    }
}
