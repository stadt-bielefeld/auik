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
 * $Id: SchlammPanel.java,v 1.2.2.1 2010-11-23 10:25:57 u633d Exp $
 *
 * Erstellt am 17.02.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.18  2005/07/06 09:38:37  u633z
 * *** empty log message ***
 *
 * Revision 1.17  2005/06/30 11:45:23  u633z
 * Nachfrage beim Löschen
 *
 * Revision 1.16  2005/05/31 11:11:08  u633z
 * - ProbenEditor / AtlProbenahmen: Datenbank-Zugriff in Mapping ausgelagert
 *
 * Revision 1.15  2005/05/30 15:44:17  u633z
 * - Kontextmenüs zur Tabelle hinzugefügt
 * - Aufgeräumt, Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module.common;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;



import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.module.common.editors.ProbenEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ProbenahmenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Eine Maske um die Probenahmen der Klärschlämme anzuzeigen.
 *
 * @author David Klotz
 */
public class SchlammPanel extends JPanel {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private AtlProbeart art;

    private HauptFrame frame;

    private ProbenahmenModel probeModel;

    /**
     * Wird benutzt, um nach dem Bearbeiten etc. wieder die selbe Probe in der
     * Liste auszuwählen.
     */
    private AtlProbenahmen lastProbe;

    private Action probeEditAction;
    private Action probeLoeschAction;

    private JComboBox anlageBox;
    private JTable probeTabelle;

    private JPanel anlegenPanel;
    private JTextField kennummerFeld;
    private JDateChooser datumsChooser;
    private JButton anlegenButton;

    private JPopupMenu probePopup;

    public SchlammPanel(AtlProbeart art, HauptFrame frame) {
        super(new FormLayout("pref, 4dlu, pref:grow", // spalten
                "pref, 3dlu, 140dlu:grow, 3dlu, pref"));// zeilen

        this.art = art;
        this.frame = frame;

        setBorder(Borders.DIALOG_BORDER);

        probeModel = new ProbenahmenModel();

        TableFocusListener tfl = TableFocusListener.getInstance();
        getProbeTabelle().addFocusListener(tfl);

        JScrollPane probeScroller = new JScrollPane(getProbeTabelle());
        JLabel anlageLabel = new JLabel("Kläranlage:");

        FormLayout anlegenLayout = new FormLayout(
                "pref, 4dlu, max(60dlu;pref), 7dlu, pref, 4dlu, max(60dlu;pref), 7dlu, max(60dlu;pref)", // spalten
                ""); // zeilen werden automatisch erzeugt

        DefaultFormBuilder anlPanelBuilder = new DefaultFormBuilder(
                anlegenLayout);

        anlPanelBuilder.appendSeparator("Neue Probenahme");

        anlPanelBuilder.append("Kennummer:", getKennummerFeld());
        anlPanelBuilder.append("Datum:", getDatumsChooser());
        anlPanelBuilder.append(getAnlegenButton());

        anlegenPanel = anlPanelBuilder.getPanel();

        CellConstraints cc = new CellConstraints();

        this.add(anlageLabel, cc.xy(1, 1));
        this.add(getAnlageBox(), cc.xy(3, 1));
        this.add(probeScroller, cc.xyw(1, 3, 3, "f, f"));
        this.add(anlegenPanel, cc.xyw(1, 5, 3));
    }

    public void updateProbeListe() {
        AtlKlaeranlagen ka = (AtlKlaeranlagen) getAnlageBox().getSelectedItem();
        setKlaeranlage(ka, art);
    }

    public void setKlaeranlage(final AtlKlaeranlagen ka, final AtlProbeart art) {
        SwingWorkerVariant worker = new SwingWorkerVariant(getProbeTabelle()) {
            protected void doNonUILogic() throws RuntimeException {
                probeModel.findByKA(art, ka);
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                probeModel.fireTableDataChanged();

                if (lastProbe != null) {
                    // Wenn die Probe noch in der Liste ist, wird sie
                    // ausgewählt.
                    int row = probeModel.getList().indexOf(lastProbe);
                    if (row != -1) {
                        getProbeTabelle().setRowSelectionInterval(row, row);
                        getProbeTabelle().scrollRectToVisible(
                                getProbeTabelle().getCellRect(row, 0, true));
                    }
                } else {
                    String statusMsg = "Suche: " + probeModel.getRowCount()
                            + " " + art + "-Probenahme";
                    if (probeModel.getRowCount() != 1) {
                        statusMsg += "n";
                    }
                    statusMsg += " in der KA " + ka + " gefunden.";
                    frame.changeStatus(statusMsg);
                }
            }
        };

        if (lastProbe == null) {
            frame.changeStatus("Suche...");
        }

        worker.start();
    }

    /**
     * Bearbeitet eine Probenahme.
     */
    public void editProbenahme(AtlProbenahmen probe) {
        ProbenEditor editDialog = new ProbenEditor(probe, frame, false);

        editDialog.setVisible(true);

        lastProbe = probe;

        updateProbeListe();
    }

    /**
     * Legt eine neue Probenahme an.
     */
    public void neueProbenahme(String kennNummer, Timestamp datum) {
        boolean exists = AtlProbenahmen.probenahmeExists(kennNummer);
        if (!exists) {
            AtlProbenahmen probe = new AtlProbenahmen();
            probe.setKennummer(kennNummer);
            probe.setDatumDerEntnahme(datum);
            probe.setAtlAnalysepositionen(new HashSet());
            AtlProbepkt pkt;
            AtlKlaeranlagen ka = (AtlKlaeranlagen) getAnlageBox()
                    .getSelectedItem();
            pkt = AtlProbepkt.getKlaerschlammProbepunkt(art, ka);

            if (pkt != null) {
                probe.setAtlProbepkt(pkt);
                ProbenEditor editDialog = new ProbenEditor(probe, frame, true);
                editDialog.setVisible(true);

                lastProbe = probe;

                updateProbeListe();
            } else {
                frame.changeStatus("Für " + art + " existiert in der KA " + ka
                        + " kein Probepunkt!", HauptFrame.ERROR_COLOR);
            }
        } else {
            frame.changeStatus(
                    "Eine Probenahme mit dieser Kennnummer existiert schon!",
                    HauptFrame.ERROR_COLOR);
        }
    }

    public void showContent() {
        lastProbe = null;

        // Standardmäßig wird Heepen ausgewählt (-1 weil der Index
        // der Combobox bei 0 anfängt)
        if (AtlKlaeranlagen.getKlaeranlagen().length != 0) {
            getAnlageBox().setSelectedIndex(
                    AtlKlaeranlagen.HEEPEN.intValue() - 1);
        }
    }

    private Action getProbeEditAction() {
        if (probeEditAction == null) {
            probeEditAction = new AbstractAction("Bearbeiten") {
                public void actionPerformed(ActionEvent e) {
                    int row = getProbeTabelle().getSelectedRow();
                    // Natürlich nur editieren, wenn wirklich eine Zeile
                    // ausgewählt ist
                    if (row != -1) {
                        AtlProbenahmen probe = probeModel.getRow(row);
                        editProbenahme(probe);
                    }
                }
            };
            probeEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                    KeyEvent.VK_B));
            probeEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke
                    .getKeyStroke(KeyEvent.VK_ENTER, 0, false));
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
                        if (frame
                                .showQuestion(
                                        "Soll die Probenahme '"
                                                + probe.getKennummer()
                                                + "' wirklich inkl. aller Analysen gelöscht werden?",
                                        "Löschen bestätigen")) {
                            if (probeModel.removeRow(row)) {
                                frame.changeStatus("Probenahme gelöscht!",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Probe " + probe.getKennummer()
                                        + " wurde gelöscht!");
                            } else {
                                frame.changeStatus(
                                        "Konnte Probenahme nicht löschen!",
                                        HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            probeLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
                    KeyEvent.VK_L));
            probeLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke
                    .getKeyStroke(KeyEvent.VK_DELETE, 0, false));
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

    private JComboBox getAnlageBox() {
        if (anlageBox == null) {
            anlageBox = new JComboBox(AtlKlaeranlagen.getKlaeranlagen());

            anlageBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lastProbe = null;

                    updateProbeListe();
                }
            });
        }

        return anlageBox;
    }

    private JTable getProbeTabelle() {
        if (probeTabelle == null) {
            probeTabelle = new JTable(probeModel);

            probeTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            probeTabelle.setColumnSelectionAllowed(false);
            probeTabelle.setRowSelectionAllowed(true);

            probeTabelle.getColumnModel().getColumn(0).setPreferredWidth(30);
            // probeTabelle.getColumnModel().getColumn(1).setPreferredWidth(30);
            probeTabelle.getColumnModel().getColumn(1).setPreferredWidth(30);
            probeTabelle.getColumnModel().getColumn(2).setPreferredWidth(200);

            probeTabelle.getInputMap().put(
                    (KeyStroke) getProbeEditAction().getValue(
                            Action.ACCELERATOR_KEY),
                    getProbeEditAction().getValue(Action.NAME));
            probeTabelle.getActionMap().put(
                    getProbeEditAction().getValue(Action.NAME),
                    getProbeEditAction());

            probeTabelle.getInputMap().put(
                    (KeyStroke) getProbeLoeschAction().getValue(
                            Action.ACCELERATOR_KEY),
                    getProbeLoeschAction().getValue(Action.NAME));
            probeTabelle.getActionMap().put(
                    getProbeLoeschAction().getValue(Action.NAME),
                    getProbeLoeschAction());

            probeTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = getProbeTabelle().rowAtPoint(origin);

                        AtlProbenahmen probe = probeModel.getRow(row);
                        editProbenahme(probe);
                    }
                }

                public void mousePressed(MouseEvent e) {
                    showProbePopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    showProbePopup(e);
                }
            });
        }

        return probeTabelle;
    }

    private JTextField getKennummerFeld() {
        if (kennummerFeld == null) {
            kennummerFeld = new LimitedTextField(50, "");
        }

        return kennummerFeld;
    }

    private JDateChooser getDatumsChooser() {
        if (datumsChooser == null) {
            datumsChooser = new JDateChooser(AuikUtils.DATUMSFORMAT, false);
        }

        return datumsChooser;
    }

    private JButton getAnlegenButton() {
        if (anlegenButton == null) {
            anlegenButton = new JButton("Anlegen");

            anlegenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (getKennummerFeld().getText().trim().equals("")) {
                        getKennummerFeld().requestFocus();
                        SchlammPanel.this.frame.changeStatus(
                                "Leere Kennummer!", HauptFrame.ERROR_COLOR);
                    } else {
                        String kennNummer = getKennummerFeld().getText().trim()
                                .replaceAll(" ", "");
                        Timestamp datum = new Timestamp(getDatumsChooser().getDate().getTime());
                        neueProbenahme(kennNummer, datum);
                    }
                }
            });
        }

        return anlegenButton;
    }
}
