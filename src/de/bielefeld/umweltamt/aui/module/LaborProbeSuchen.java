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
 * $Id: LaborProbeSuchen.java,v 1.1.2.1 2010-11-23 10:25:53 u633d Exp $
 *
 * Erstellt am 23.05.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.9  2005/09/07 10:48:22  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.8  2005/08/24 08:42:51  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.7  2005/06/02 15:18:40  u633z
 * - Submit-Buttons bei Suchen hinzugefügt
 *
 * Revision 1.6  2005/05/30 08:35:42  u633z
 * - Aufgeräumt, mehrere neue Packages, Klassen sortiert
 *
 * Revision 1.5  2005/05/24 14:46:06  u633z
 * - Kontext-Menüs der Tabellen
 * - Updaten der Tabellen und Auswahl nach dem Bearbeiten verbessert
 *
 * Revision 1.4  2005/05/24 11:03:22  u633z
 * ProbenahmenModel kann jetzt auch Probeart als zweite Spalte anzeigen
 *
 * Revision 1.3  2005/05/23 15:48:57  u633z
 * - clearForm eingebaut, um die Liste bei show() zu leeren
 *
 * Revision 1.2  2005/05/23 10:17:12  u633z
 * - Neues Modul: LaborProbeSuchen für die direkte Suche nach einer Probenahme (nach Kennnummer o. Bemerkung)
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ProbenahmenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.NamedObject;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Ein Modul zum Suchen und Bearbeiten eines Betreibers.
 * @author David Klotz
 */
public class LaborProbeSuchen extends AbstractModul {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String iconPath = "filefind32.png";

    private JComboBox suchBox;
    private JTextField suchFeld;
    private JButton submitButton;
    private JTable probeTabelle;

    private Action probeEditAction;
    private Action probeLoeschAction;
    private JPopupMenu probePopup;

    private ProbenahmenModel probeModel;

    private String lastSuche;
    private String lastProperty;
    private Probenahme lastProbe;

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Probenahme suchen";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * @return "m_probe_suchen"
     */
    @Override
    public String getIdentifier() {
        return "m_probe_suchen";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory() {
        return "Labor";
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
            this.probeModel = new ProbenahmenModel("Art");

            TableFocusListener tfl = TableFocusListener.getInstance();
            getProbeTabelle().addFocusListener(tfl);

            JScrollPane probeScroller = new JScrollPane(getProbeTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JToolBar submitToolBar = new JToolBar();
            submitToolBar.setFloatable(false);
            submitToolBar.setRollover(true);
            submitToolBar.add(getSubmitButton());

            TabAction ta = new TabAction();
            ta.addComp(getSuchFeld());
            ta.addComp(getProbeTabelle());

            PanelBuilder builder = new PanelBuilder();
            builder.setAnchor(GridBagConstraints.WEST);
            builder.setInsets(5, 5, 0, 0);
            builder.setWeightX(0);
            builder.addComponents(getSuchBox());
            builder.setWeightX(1);
            builder.setFill(GridBagConstraints.HORIZONTAL);
            builder.addComponent(getSuchFeld());
            builder.setFill(GridBagConstraints.NONE);
            builder.setWeightX(0);
            builder.addComponent(getSubmitButton(), true);
            builder.setWeight(1, 1);
            builder.setFill(GridBagConstraints.BOTH);
            builder.addComponent(probeScroller, true);

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

        clearForm();
    }

    /**
     * Leert die Ergebnis-Liste und wählt allen Text (falls vorhanden) im
     * Suchfeld aus.
     */
    public void clearForm() {
        this.probeModel.setList(new ArrayList<Probenahme>());
        this.probeModel.fireTableDataChanged();

        getSuchFeld().selectAll();
        getSuchFeld().requestFocus();
    }

    /**
     * öffnet einen Dialog um einen Probenahmen-Datensatz zu bearbeiten.
     * @param probe Die Probe.
     */
    public void editProbe(Probenahme probe) {
        // BetreiberEditor editDialog = new BetreiberEditor(betr, frame,
        // manager);
        ProbenEditor editor = new ProbenEditor(probe, this.frame, false);

        editor.setVisible(true);

        this.lastProbe = probe;

        if (editor.wasSaved()) {
            // Nach dem Bearbeiten die Liste updaten, damit unsere Änderungen
            // auch angezeigt werden.
            updateProbeListe();
        }
    }

    public void updateProbeListe() {
        if (this.lastSuche != null && this.lastProperty != null) {
            filterProbeListe(this.lastSuche, this.lastProperty);
        }
    }

    /**
     * Filtert / Durchsucht die Proben-Liste.
     * @param suche Wonach soll gesucht werden?
     * @param column Nach welcher Eigenschaft der Probe soll gesucht werden?
     */
    public void filterProbeListe(final String suche, final String column) {
        SwingWorkerVariant worker = new SwingWorkerVariant(getProbeTabelle()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                LaborProbeSuchen.this.probeModel.findByProperty(column, suche);
                LaborProbeSuchen.this.lastSuche = suche;
                LaborProbeSuchen.this.lastProperty = column;
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                getProbeTabelle().clearSelection();

                LaborProbeSuchen.this.probeModel.fireTableDataChanged();

                if (LaborProbeSuchen.this.lastProbe != null) {
                    // Wenn die zuletzt bearbeitete Probe noch in der Liste ist,
                    // wird sie ausgewählt.
                    int row = LaborProbeSuchen.this.probeModel.getList()
                        .indexOf(LaborProbeSuchen.this.lastProbe);
                    if (row != -1) {
                        getProbeTabelle().setRowSelectionInterval(row, row);
                        getProbeTabelle().scrollRectToVisible(
                            getProbeTabelle().getCellRect(row, 0, true));
                    }
                } else {
                    String statusMsg = "Suche: "
                        + LaborProbeSuchen.this.probeModel.getRowCount()
                        + " Ergebnis";
                    if (LaborProbeSuchen.this.probeModel.getRowCount() != 1) {
                        statusMsg += "se";
                    }
                    statusMsg += ".";
                    LaborProbeSuchen.this.frame.changeStatus(statusMsg);
                }
            }
        };

        if (this.lastProbe == null) {
            this.frame.changeStatus("Suche...");
        }

        worker.start();
    }

    private Action getProbeEditAction() {
        if (this.probeEditAction == null) {
            this.probeEditAction = new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID = 5010453878974070301L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getProbeTabelle().getSelectedRow();
                    log.debug("Enter in Zeile " + row);

                    // Natürlich nur editieren, wenn wirklich eine Zeile
                    // ausgewählt ist
                    if (row != -1) {
                        Probenahme probe = LaborProbeSuchen.this.probeModel
                            .getRow(row);
                        editProbe(probe);
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
                private static final long serialVersionUID = -5527830509453388049L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getProbeTabelle().getSelectedRow();
                    if (row != -1 && getProbeTabelle().getEditingRow() == -1) {
                        Probenahme probe = LaborProbeSuchen.this.probeModel
                            .getRow(row);

                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Probenahme '" + probe.getKennummer()
                                + "' wirklich inkl. aller Analysen gelöscht"
                                + " werden?", "Löschen bestätigen")) {
                            if (LaborProbeSuchen.this.probeModel.removeRow(row)) {
                                LaborProbeSuchen.this.frame.changeStatus(
                                    "Probenahme gelöscht.",
                                    HauptFrame.SUCCESS_COLOR);
                                log.debug("Probe " + probe + " wurde gelöscht!");
                            } else {
                                LaborProbeSuchen.this.frame.changeStatus(
                                    "Konnte die Probenahme nicht löschen!",
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
            int row = getProbeTabelle().rowAtPoint(origin);

            if (row != -1) {
                getProbeTabelle().setRowSelectionInterval(row, row);
                this.probePopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private JTable getProbeTabelle() {
        if (this.probeTabelle == null) {
            this.probeTabelle = new JTable(this.probeModel);
            this.probeTabelle.setGridColor(new Color(230, 230, 230));

            this.probeTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
            this.probeTabelle.getColumnModel().getColumn(0)
                .setPreferredWidth(15);
            this.probeTabelle.getColumnModel().getColumn(1)
                .setPreferredWidth(45);
            this.probeTabelle.getColumnModel().getColumn(2)
                .setPreferredWidth(15);
            this.probeTabelle.getColumnModel().getColumn(3)
                .setPreferredWidth(100);
            this.probeTabelle.getColumnModel().getColumn(4)
                .setPreferredWidth(200);
            this.probeTabelle.getColumnModel().getColumn(5)
                .setPreferredWidth(200);

            this.probeTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.probeTabelle.setColumnSelectionAllowed(false);
            this.probeTabelle.setRowSelectionAllowed(true);

            this.probeTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getProbeTabelle().rowAtPoint(origin);

                            Probenahme probe = LaborProbeSuchen.this.probeModel
                                .getRow(row);
                            log.debug("Doppelklick auf Zeile " + row);
                            editProbe(probe);
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

            this.probeTabelle.getInputMap().put(
                (KeyStroke) getProbeEditAction().getValue(
                    Action.ACCELERATOR_KEY),
                getProbeEditAction().getValue(Action.NAME));
            this.probeTabelle.getActionMap().put(
                getProbeEditAction().getValue(Action.NAME),
                getProbeEditAction());

            this.probeTabelle.getInputMap().put(
                (KeyStroke) getProbeLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getProbeLoeschAction().getValue(Action.NAME));
            this.probeTabelle.getActionMap().put(
                getProbeLoeschAction().getValue(Action.NAME),
                getProbeLoeschAction());
        }
        return this.probeTabelle;
    }

    private JComboBox getSuchBox() {
        if (this.suchBox == null) {
            this.suchBox = new JComboBox(new NamedObject[] {
                    new NamedObject("Kennnummer:", "kennummer"),
                    new NamedObject("Bemerkung:", "bemerkung")});
        }
        return this.suchBox;
    }

    private JTextField getSuchFeld() {
        if (this.suchFeld == null) {
            this.suchFeld = new JTextField("");

            this.suchFeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String suche = getSuchFeld().getText();
                    String spalte = (String) ((NamedObject) getSuchBox()
                        .getSelectedItem()).getValue();
                    LaborProbeSuchen.this.lastProbe = null;
                    filterProbeListe(suche, spalte);
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
                        filterProbeListe(suche, spalte);
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
                    LaborProbeSuchen.this.lastProbe = null;
                    filterProbeListe(suche, spalte);
                }
            });
        }

        return this.submitButton;
    }
}
