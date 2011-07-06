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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ProbenahmenModel;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.NamedObject;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
;

/**
 * Ein Modul zum Suchen und Bearbeiten eines Betreibers.
 * @author David Klotz
 */
public class LaborProbeSuchen extends AbstractModul {
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
    private AtlProbenahmen lastProbe;

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    public String getName() {
        return "Probenahme suchen";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * @return "m_probe_suchen"
     */
    public String getIdentifier() {
        return "m_probe_suchen";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    public String getCategory() {
        return "Labor";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
     * @see de.bielefeld.umweltamt.aui.AbstractModul#getIcon(String)
     */
    public Icon getIcon() {
        return super.getIcon(iconPath);
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    public JPanel getPanel() {
        if (panel == null) {
            probeModel = new ProbenahmenModel("Art");

            TableFocusListener tfl = TableFocusListener.getInstance();
            getProbeTabelle().addFocusListener(tfl);


            JScrollPane probeScroller = new JScrollPane(getProbeTabelle(),
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            JToolBar submitToolBar = new JToolBar();
            submitToolBar.setFloatable(false);
            submitToolBar.setRollover(true);
            submitToolBar.add(getSubmitButton());

            TabAction ta = new TabAction();
            ta.addComp(getSuchFeld());
            ta.addComp(getProbeTabelle());

            FormLayout layout = new FormLayout(
                    "65dlu, 4dlu, pref:grow, 3dlu, min(16dlu;p)",        // spalten
                    "pref, 3dlu, f:150dlu:grow");     // zeilen

            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            builder.add(getSuchBox(),    cc.xy(1, 1));
            builder.add(getSuchFeld(),    cc.xy(3, 1));
            builder.add(submitToolBar,    cc.xy(5, 1));//, "r, d"));
            builder.add(probeScroller,    cc.xyw(1, 3, 5));

            panel = builder.getPanel();
        }
        return panel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#show()
     */
    public void show() {
        super.show();

        clearForm();
    }

    /**
     * Leert die Ergebnis-Liste und wählt allen Text (falls vorhanden) im Suchfeld aus.
     */
    public void clearForm() {
        probeModel.setList(new ArrayList());
        probeModel.fireTableDataChanged();

        getSuchFeld().selectAll();
        getSuchFeld().requestFocus();
    }

    /**
     * öffnet einen Dialog um einen Probenahmen-Datensatz zu bearbeiten.
     * @param probe Die Probe.
     */
    public void editProbe(AtlProbenahmen probe) {
        //BetreiberEditor editDialog = new BetreiberEditor(betr, frame, manager);
        ProbenEditor editor = new ProbenEditor(probe, frame, false);

        editor.setVisible(true);

        lastProbe = probe;

        if (editor.wasSaved()) {
            // Nach dem Bearbeiten die Liste updaten, damit unsere Änderungen auch angezeigt werden.
            updateProbeListe();
        }
    }

    public void updateProbeListe() {
        if (lastSuche != null && lastProperty != null) {
            filterProbeListe(lastSuche, lastProperty);
        }
    }

    /**
     * Filtert / Durchsucht die Proben-Liste.
     * @param suche Wonach soll gesucht werden?
     * @param column Nach welcher Eigenschaft der Probe soll gesucht werden?
     */
    public void filterProbeListe(final String suche, final String column) {
            SwingWorkerVariant worker = new SwingWorkerVariant(getProbeTabelle()) {
                protected void doNonUILogic() throws RuntimeException {
                    probeModel.findByProperty(suche, column);
                    lastSuche = suche;
                    lastProperty = column;
                }

                protected void doUIUpdateLogic() throws RuntimeException {
                    getProbeTabelle().clearSelection();

                    probeModel.fireTableDataChanged();

                    if (lastProbe != null) {
                        // Wenn die zuletzt bearbeitete Probe noch in der Liste ist,
                        // wird sie ausgewählt.
                        int row = probeModel.getList().indexOf(lastProbe);
                        if (row != -1) {
                            getProbeTabelle().setRowSelectionInterval(row, row);
                            getProbeTabelle().scrollRectToVisible(getProbeTabelle().getCellRect(row, 0, true));
                        }
                    } else {
                        String statusMsg = "Suche: " + probeModel.getRowCount() + " Ergebnis";
                        if (probeModel.getRowCount() != 1) {
                            statusMsg += "se";
                        }
                        statusMsg += ".";
                        frame.changeStatus(statusMsg);
                    }
                }
            };

            if (lastProbe == null) {
                frame.changeStatus("Suche...");
            }

            worker.start();
    }

    private Action getProbeEditAction() {
        if (probeEditAction == null) {
            probeEditAction = new AbstractAction("Bearbeiten") {
                public void actionPerformed(ActionEvent e) {
                    int row = getProbeTabelle().getSelectedRow();
                    AUIKataster.debugOutput("Enter in Zeile " + row, "probeTabelle");

                    // Natürlich nur editieren, wenn wirklich eine Zeile ausgewählt ist
                    if (row != -1) {
                        AtlProbenahmen probe = probeModel.getRow(row);
                        editProbe(probe);
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
                    int row = getProbeTabelle().getSelectedRow();
                    if (row != -1 && getProbeTabelle().getEditingRow() == -1) {
                        AtlProbenahmen probe = probeModel.getRow(row);
                        int answer = JOptionPane.showConfirmDialog(panel, "Soll die Probenahme '"+ probe.getKennummer() +"' wirklich inkl. aller Analysen gelöscht werden?", "Löschen bestätigen", JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            if (probeModel.removeRow(row)) {
                                frame.changeStatus("Probenahme gelöscht.", HauptFrame.SUCCESS_COLOR);
                                AUIKataster.debugOutput("Probe " + probe + " wurde gelöscht!", "BasisBetreiberSuchen.removeAction");
                            } else {
                                frame.changeStatus("Konnte die Probenahme nicht löschen!", HauptFrame.ERROR_COLOR);
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
            int row = getProbeTabelle().rowAtPoint(origin);

            if (row != -1) {
                getProbeTabelle().setRowSelectionInterval(row, row);
                probePopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private JTable getProbeTabelle() {
        if (probeTabelle == null) {
            probeTabelle = new JTable(probeModel);

            probeTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
            probeTabelle.getColumnModel().getColumn(0).setPreferredWidth(15);
            probeTabelle.getColumnModel().getColumn(1).setPreferredWidth(45);
            probeTabelle.getColumnModel().getColumn(2).setPreferredWidth(15);
            probeTabelle.getColumnModel().getColumn(3).setPreferredWidth(100);
            probeTabelle.getColumnModel().getColumn(4).setPreferredWidth(200);
            probeTabelle.getColumnModel().getColumn(5).setPreferredWidth(200);

            probeTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            probeTabelle.setColumnSelectionAllowed(false);
            probeTabelle.setRowSelectionAllowed(true);

            probeTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = getProbeTabelle().rowAtPoint(origin);

                        AtlProbenahmen probe = probeModel.getRow(row);
                        AUIKataster.debugOutput("Doppelklick auf Zeile " + row, "BasisBetreiberSuchen");
                        editProbe(probe);
                    }
                }

                public void mousePressed(MouseEvent e) {
                    showProbePopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    showProbePopup(e);
                }
            });

            probeTabelle.getInputMap().put((KeyStroke)getProbeEditAction().getValue(Action.ACCELERATOR_KEY), getProbeEditAction().getValue(Action.NAME));
            probeTabelle.getActionMap().put(getProbeEditAction().getValue(Action.NAME), getProbeEditAction());

            probeTabelle.getInputMap().put((KeyStroke)getProbeLoeschAction().getValue(Action.ACCELERATOR_KEY), getProbeLoeschAction().getValue(Action.NAME));
            probeTabelle.getActionMap().put(getProbeLoeschAction().getValue(Action.NAME), getProbeLoeschAction());
        }
        return probeTabelle;
    }

    private JComboBox getSuchBox() {
        if (suchBox == null) {
            suchBox = new JComboBox(
                    new NamedObject[]{
                            new NamedObject("Kennnummer:","kennummer"),
                            new NamedObject("Bemerkung:","bemerkung")
                            });
        }
        return suchBox;
    }

    private JTextField getSuchFeld() {
        if (suchFeld == null) {
            suchFeld = new JTextField("");

            suchFeld.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String suche = getSuchFeld().getText();
                    String spalte = (String) ((NamedObject) getSuchBox().getSelectedItem()).getValue();
                    lastProbe = null;
                    filterProbeListe(suche, spalte);
                }
            });
            suchFeld.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

            suchFeld.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_TAB) {
                        String suche = getSuchFeld().getText();
                        String spalte = (String) ((NamedObject) getSuchBox().getSelectedItem()).getValue();
                        filterProbeListe(suche, spalte);
                    }
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
                    String suche = getSuchFeld().getText();
                    String spalte = (String) ((NamedObject) getSuchBox().getSelectedItem()).getValue();
                    lastProbe = null;
                    filterProbeListe(suche, spalte);
                }
            });
        }

        return submitButton;
    }
}
