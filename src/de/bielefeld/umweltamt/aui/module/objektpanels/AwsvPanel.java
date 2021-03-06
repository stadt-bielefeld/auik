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
 * $Id: awsvPanel.java,v 1.3.2.1 2010-11-23 10:25:57 u633d Exp $
 *
 * Erstellt am 25.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/12/02 06:29:47  u633d
 * Verbesserung Aufruf designs
 *
 * Revision 1.2  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.10  2006/09/19 12:23:07  u633d
 * awsvAbscheider nun moeglich
 *
 * Revision 1.9  2006/09/19 10:31:22  u633d
 * Abscheider bearbeitet
 *
 * Revision 1.8  2006/09/19 08:09:02  u633d
 * awsvAbscheider
 *
 * Revision 1.5.4.2  2006/09/06 08:11:44  u633d
 * awsv-Anlage hinzugekommen, allerdings noch nicht ganz fertig
 *
 * Revision 1.5.4.1  2006/09/06 06:45:08  u633d
 * Sielhautpunkte funktionieren als PDF
 * awsv-Listen auch
 * für awsv-Anlagen muss ich nur noch das Java schreiben
 *
 * Revision 1.5  2005/11/02 13:56:03  u633d
 * - Version vom 2.11.
 *
 * Revision 1.4  2005/10/13 13:00:27  u633d
 * Version vom 13.10.
 *
 * Revision 1.3  2005/09/28 11:11:14  u633d
 * - Version vom 28.9.
 *
 * Revision 1.2  2005/09/07 10:48:23  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.1  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenarten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.AwsvEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.AwsvModel;

import de.bielefeld.umweltamt.aui.utils.PDFExporter;
/**
 * Das "awsv"-Panel des Objekt-Bearbeiten-Moduls.
 * @author David Klotz
 */
public class AwsvPanel extends JPanel {
	private static final long serialVersionUID = -6308803486523520730L;
	private String name;
    private BasisObjektBearbeiten hauptModul;

    private AwsvModel awsvModel;

    private Action awsvEditAction;
    private Action awsvLoeschAction;
    private Action reportAnlageAction;
    private JPopupMenu awsvPopup;
    private JTable awsvTable;

    protected Integer objektid;
    protected String betreiber;
    protected String standort;
    protected String art;
    protected Integer behaelterid;

    private JComboBox anlagenartBox;
    private JButton anlegenButton;
    private JButton reportListeButton;
    private JButton reportAnlageButton;

    /**
     * Erzeugt das awsv-Panel für das BasisObjektBearbeiten-Modul.
     * @param hauptModul Das BasisObjektBearbeiten-Hauptmodul.
     */
    public AwsvPanel(BasisObjektBearbeiten hauptModul) {
        name = "AwSV";
        this.hauptModul = hauptModul;
        awsvModel = new AwsvModel();

        JScrollPane awsvScroller = new JScrollPane(getAwsvTable());

        anlagenartBox = new JComboBox(DatabaseQuery.getAnlagenarten());
        anlegenButton = new JButton("Anlegen");
        anlegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                neuerDatensatz();
            }
        });

        reportListeButton = new JButton("PDF-Liste generieren");
        reportListeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReportListe();
            }
        });

        reportAnlageButton = new JButton("PDF-Datenblatt generieren");
        reportAnlageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReportAnlage();
            }
        });

        FormLayout layout = new FormLayout(
                "pref, 3dlu, pref, 3dlu, pref, 10dlu:g, pref, 3dlu, pref",
                "f:100dlu:g, 3dlu, pref"
        );
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.append(awsvScroller,9);
        builder.nextLine(2);
        builder.append("Neue(n)", anlagenartBox, anlegenButton);
        builder.append(reportListeButton, reportAnlageButton);
    }

    /**
     * Liefert den Namen dieses Panels.
     * @return "AwSV"
     */
    @Override
    public String getName() {
        return name;
    }

    // Funktionalität:

    /**
     * Holt die Liste mit Fachdatensätzen aus der Datenbank.
     */
    public void fetchFormData() {
        awsvModel.setList(
            DatabaseQuery.getFachdatenByObjekt(
                hauptModul.getObjekt())
        );
    }

    /**
     * Erneuert die Anzeige der Tabelle.
     */
    public void updateForm() {
        // TODO: Evtl. bei allen Tabellen nur fireTableDataChanged nach Editor?
        // Zumindest so lange kein neues Objekt hinzugekommen ist?
        awsvModel.fireTableDataChanged();
    }

    /**
     * Legt einen neuen awsv-Datensatz zu diesem Objekt an.
     */
    public void neuerDatensatz() {
        Fachdaten neu = new Fachdaten();
        neu.setObjekt(hauptModul.getObjekt());
        neu.setAnlagenart(
            ((Anlagenarten)anlagenartBox.getSelectedItem())
                .getAnlagenart());
// TODO: This would be even better: (add foreign keys...)
//        neu.setawsvAnlagenarten(
//            (awsvAnlagenarten)anlagenartBox.getSelectedItem());

        neu = Fachdaten.merge(neu);
        editDatensatz(neu);
    }

    /**
     * öffnet einen Dialog um einen awsv-Datensatz zu bearbeiten.
     * @param probe Der Datensatz.
     */
    public void editDatensatz(Fachdaten fd) {
        //AUIKataster.debugOutput("Bearbeite '" + fd + "' !", "awsvPanel.editDatensatz");
        AwsvEditor editor = new AwsvEditor(fd, hauptModul.getFrame());

        editor.setVisible(true);

        if (editor.wasSaved()) {
            // Nach dem Bearbeiten die Liste updaten,
            // damit unsere Änderungen auch angezeigt werden.
            fetchFormData();
            updateForm();
        }
    }

    /**
     * Löscht einen awsv-Datensatz (mit Nachfrage).
     * @param fachdaten Der Datensatz.
     */
    public void loescheDatensatz(Fachdaten fachdaten) {
        String art = fachdaten.getAnlagenart();
        if (DatabaseQuery.isLageranlage(fachdaten)) {
            art = "Lageranlage";
        }

        if (GUIManager.getInstance().showQuestion(
                "Soll die ausgewählte "+art+" wirklich gelöscht werden?",
                "Löschen bestätigen")) {
            if (Fachdaten.delete(fachdaten)) {
                hauptModul.getFrame().changeStatus("Fachdatensatz '" + fachdaten + "' erfolgreich gelöscht!", HauptFrame.SUCCESS_COLOR);

                // Tabelle updaten:
                fetchFormData();
                updateForm();
            } else {
                GUIManager.getInstance().showErrorMessage("Konnte Fachdatensatz '" + fachdaten + "' nicht löschen!");
            }
        }
    }

    public void showReportListe() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("Betreiber", hauptModul.getObjekt().getBetreiberid().toString());
        params.put("Standort", DatabaseQuery.getStandortString(hauptModul.getObjekt().getStandortid()));
        params.put("Art", hauptModul.getObjekt().getObjektarten().getObjektart());
        params.put("ObjektId", hauptModul.getObjekt().getId());
        try {
            File pdfFile = File.createTempFile("awsv_liste", ".pdf");
            pdfFile.deleteOnExit();
            PDFExporter.getInstance().exportReport(params,
                    PDFExporter.AWSV_LISTE, pdfFile.getAbsolutePath());
        } catch (Exception ex) {
            GUIManager.getInstance().showErrorMessage(
                "PDF-Liste generieren fehlgeschlagen."
                    + "\n" + ex,
                "PDF-Liste generieren fehlgeschlagen");
        }
    }

    public void showReportAnlage() {
        int row = getAwsvTable().getSelectedRow();
        if (row != -1) {
            Map<String, Object> params = new HashMap<String, Object>();
            Fachdaten anlage = awsvModel.getDatenSatz(row);
            params.put("Betreiber", hauptModul.getObjekt().getBetreiberid().toString());
            params.put("Standort", DatabaseQuery.getStandortString(hauptModul.getObjekt().getStandortid()));
            params.put("Objektart", hauptModul.getObjekt().getObjektarten().getObjektart());
            params.put("BehaelterId", anlage.getBehaelterid());

            try {
                File pdfFile = File.createTempFile("awsv_anlage", ".pdf");
                pdfFile.deleteOnExit();
                PDFExporter.getInstance().exportReport(params,
                        PDFExporter.AWSV_ANLAGEN, pdfFile.getAbsolutePath());
            } catch (Exception ex) {
                GUIManager.getInstance().showErrorMessage(
                    "PDF-Datenblatt generieren fehlgeschlagen."
                        + "\n" + ex.getLocalizedMessage(),
                    "PDF-Datenblatt generieren fehlgeschlagen");
            }
        } else {
            hauptModul.getFrame().changeStatus("Keine Anlage ausgewählt!", HauptFrame.ERROR_COLOR);
        }
    }

    /**
     * Liefert die Action um einen Datensatz zu bearbeiten.
     */
    private Action getAwsvEditAction() {
        if (awsvEditAction == null) {
            awsvEditAction = new AbstractAction("Bearbeiten") {
				private static final long serialVersionUID = 4646432365839296566L;

				@Override
                public void actionPerformed(ActionEvent e) {
                    int row = getAwsvTable().getSelectedRow();

                    // Natürlich nur editieren, wenn wirklich eine Zeile ausgewählt ist
                    if (row != -1) {
                        Fachdaten fd = awsvModel.getDatenSatz(row);
                        editDatensatz(fd);
                    }
                }
            };
            awsvEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
            awsvEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
        }

        return awsvEditAction;
    }

    /**
     * Liefert die Action um einen Datensatz zu löschen.
     */
    private Action getAwsvLoeschAction() {
        if (awsvLoeschAction == null) {
            awsvLoeschAction = new AbstractAction("Löschen") {
				private static final long serialVersionUID = -1355761143104436343L;

				@Override
                public void actionPerformed(ActionEvent e) {
                    int row = getAwsvTable().getSelectedRow();

                    // Natürlich nur, wenn wirklich eine Zeile ausgewählt ist
                    if (row != -1) {
                        Fachdaten fd = awsvModel.getDatenSatz(row);
                        loescheDatensatz(fd);
                    }
                }
            };
            awsvLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
            awsvLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return awsvLoeschAction;
    }

    /**
     * Liefert die Action um einen Datensatz zu löschen.
     */
    private Action getReportAnlageAction() {
        if (reportAnlageAction == null) {
            reportAnlageAction = new AbstractAction("PDF-Datenblatt generieren") {
				private static final long serialVersionUID = -4668237100126864516L;

				@Override
                public void actionPerformed(ActionEvent e) {
                    showReportAnlage();
                }
            };
            reportAnlageAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_D));
//            reportAnlageAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_DOWN_MASK, false));
        }

        return reportAnlageAction;
    }

    /**
     * Zeigt ein Kontextmenü an, wenn ein entsprechendes
     * MouseEvent vorliegt.
     * @param e Das MouseEvent.
     */
    private void showAwsvPopup(MouseEvent e) {
        if (awsvPopup == null) {
            awsvPopup = new JPopupMenu("AwSV");
            awsvPopup.add(new JMenuItem(getAwsvEditAction()));
            awsvPopup.add(new JMenuItem(getAwsvLoeschAction()));
            awsvPopup.add(new JMenuItem(getReportAnlageAction()));
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = getAwsvTable().rowAtPoint(origin);

            if (row != -1) {
                getAwsvTable().setRowSelectionInterval(row, row);
                awsvPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    // Widget-Getter:

    private JTable getAwsvTable() {
        if (awsvTable == null) {
            awsvTable = new JTable(awsvModel);
            awsvTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            awsvTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = awsvTable.rowAtPoint(origin);

                        Fachdaten fd = awsvModel.getDatenSatz(row);
                        editDatensatz(fd);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    showAwsvPopup(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    showAwsvPopup(e);
                }
            });

            awsvTable.getInputMap().put((KeyStroke)getAwsvEditAction().getValue(Action.ACCELERATOR_KEY), getAwsvEditAction().getValue(Action.NAME));
            awsvTable.getActionMap().put(getAwsvEditAction().getValue(Action.NAME), getAwsvEditAction());

            awsvTable.getInputMap().put((KeyStroke)getAwsvLoeschAction().getValue(Action.ACCELERATOR_KEY), getAwsvLoeschAction().getValue(Action.NAME));
            awsvTable.getActionMap().put(getAwsvLoeschAction().getValue(Action.NAME), getAwsvLoeschAction());

//            awsvTable.getInputMap().put((KeyStroke)getReportAnlageAction().getValue(Action.ACCELERATOR_KEY), getReportAnlageAction().getValue(Action.NAME));
//            awsvTable.getActionMap().put(getReportAnlageAction().getValue(Action.NAME), getReportAnlageAction());
        }
        return awsvTable;
    }
}
