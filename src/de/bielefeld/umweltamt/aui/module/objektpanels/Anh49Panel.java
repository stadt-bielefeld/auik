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
 * $Id: Anh49Panel.java,v 1.1.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 27.04.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.3  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/07/23 06:55:22  u633d
 * Anh49 Auswertung und Sielhautimport neu
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.4  2005/06/13 14:02:16  u633z
 * - Nicht vorhandenes Fachdaten-Objekt wird besser behandelt
 *
 * Revision 1.3  2005/06/10 11:06:52  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.2  2005/06/03 11:05:05  u633z
 * - Datenbank-Zugriffe (speziell Speichern-Methoden) in Mappings verlagert.
 *
 * Revision 1.1  2005/05/30 08:35:42  u633z
 * - Aufgeräumt, mehrere neue Packages, Klassen sortiert
 *
 * Revision 1.6  2005/05/20 09:00:54  u633z
 * Anhang 49 und Anh49-Abscheidetail-Tabs verbessert und verschönert
 *
 * Revision 1.5  2005/05/19 15:37:51  u633d
 * Abscheiderdetails-Zwischenstand
 *
 * Revision 1.4  2005/05/19 12:53:44  u633d
 * Anhang 49 neue Mapings
 *
 * Revision 1.3  2005/05/17 07:53:43  u633d
 * Anhang 49 Ergänzungen
 *
 * Revision 1.2  2005/05/12 06:39:18  u633z
 * JavaDoc Kommentar verbessert, Header angepasst
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Anhang 49"-Tab des BasisObjektBearbeiten-Moduls.
 * @author Gerd Genuit
 */
public class Anh49Panel extends AbstractAnhangPanel {
	private static final long serialVersionUID = 2262140075740338093L;

	/* Note: As these strings are used as keys in the underlying HashMap,     *
	 * they should be unique.                                                 */
	/* Widgets - left */
	private final String SACHBEARBEITER              = "Sachbearbeiter:";
	private final String ANSPRECHPARTNER             = "Ansprechpartner:";
	private final String SACHKUNDE_LFA               = "Sachkunde LFA:";
	private final String ANALYSEMONAT                = "Analysemonat:";
	private final String BEMERKUNGEN                 = "Bemerkungen";
	/* Widgets - right */
	private final String GENEHMIGUNGSDATUM           = "Genehmigungsdatum:";
    private final String AENDERUNGSGENEHMIGUNGSDATUM = "Änderungsgen.-datum:";
	private final String ABGEMELDET                  = "abgemeldet";
	private final String ABWASSERFREI                = "abwasserfrei";
	private final String E_SATZUNG                   = "E-Satzung";
	private final String WIEDERVORLAGEDATUM          = "Wiedervorlagedatum:";
	/* Widgets - bottom */
	private final String SPEICHERN                   = "Speichern";

    private JButton saveAnh49Button = null;

    // Daten
    private Anh49Fachdaten fachdaten = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public Anh49Panel(BasisObjektBearbeiten hauptModul) {
    	super("Anhang 49", hauptModul);

    	/* Add components to the "Anhang" panel */
        /* Left column */
     	super.addComponent(SACHBEARBEITER, new LimitedTextField(50));
    	super.addComponent(ANSPRECHPARTNER, new LimitedTextField(50));
    	super.addComponent(SACHKUNDE_LFA, new LimitedTextField(50));
    	super.addComponent(ANALYSEMONAT, new LimitedTextField(50));
    	JTextArea textArea = new LimitedTextArea(150);
    	textArea.setLineWrap(true);
    	textArea.setWrapStyleWord(true);
    	super.addComponent(BEMERKUNGEN, textArea);

    	/* Right column */
    	super.addComponent(GENEHMIGUNGSDATUM,
    			new TextFieldDateChooser());
    	super.addComponent(AENDERUNGSGENEHMIGUNGSDATUM,
    			new TextFieldDateChooser());
    	super.addComponent(ABGEMELDET, new JCheckBox(ABGEMELDET));
    	super.addComponent(ABWASSERFREI, new JCheckBox(ABWASSERFREI));
    	super.addComponent(E_SATZUNG, new JCheckBox(E_SATZUNG));
    	super.addComponent(WIEDERVORLAGEDATUM,
    			new TextFieldDateChooser());
    	super.addComponent(SPEICHERN, getSaveAnh49Button());

        FormLayout layout = new FormLayout (
                "pref, 5dlu, 100dlu, 10dlu, pref, 5dlu, 100dlu", // Spalten
                "pref, " +    // Bearbeitung     | Erfassung
                "3dlu, " +    //
                "pref, " +    // Sachbearbeiter  | Genehmigungsdatum
                "3dlu, " +    //
                "pref, " + // Ansprechpartner | Änderungsgenehmigungsdatum
                "3dlu, " +    //
                "pref, " +    // Sachkunde LFA   | abgemeldet
                "3dlu, " +    //
                "pref, " +    // Analyse         | abwasserfrei
                "3dlu, " +    //
                "pref, " +    // Analysemonat   | E-Satzung
                "3dlu, " +    //
                "pref, " +    // Bemerkung      | Kontrolle
                "3dlu, " +    //
                "pref, " +    // Bemerkung      | Wiedervorlage
                "30dlu, " +   //
                "3dlu, " +    //
                "pref, " + // Verknüpfte Objekte
                "5dlu, " +    //
                "fill:100dlu, " +    // Tabelle
                "5dlu, " +    //
                "pref");      // Buttons

        PanelBuilder builder = new PanelBuilder(layout, this);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        /* Left column */
        Integer row = 1;
        Integer labelCol = 1;
        Integer fieldCol = 3;
        Integer colWidth = 3;
        Integer cols = 7;

        builder.addSeparator("Bearbeitung", cc.xyw(labelCol, row, colWidth));
        row += 2;
        builder.addLabel(SACHBEARBEITER, cc.xy(labelCol, row));
        builder.add(super.getComponent(SACHBEARBEITER), cc.xy(fieldCol, row));
        row += 2;
        builder.addLabel(ANSPRECHPARTNER, cc.xy(labelCol, row));
        builder.add(super.getComponent(ANSPRECHPARTNER), cc.xy(fieldCol, row));
        row += 2;
        builder.addLabel(SACHKUNDE_LFA, cc.xy(labelCol, row));
        builder.add(super.getComponent(SACHKUNDE_LFA), cc.xy(fieldCol, row));
        row += 2;

        builder.addSeparator("Analyse", cc.xyw(labelCol, row, colWidth));
        row += 2;
        builder.addLabel(ANALYSEMONAT, cc.xy(labelCol, row));
        builder.add(super.getComponent(ANALYSEMONAT), cc.xy(fieldCol, row));
        row += 2;

        builder.addSeparator("Bemerkungen", cc.xyw(labelCol, row, colWidth));
        row += 2;
        builder.add(new JScrollPane(super.getComponent(BEMERKUNGEN),
        		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
        		cc.xywh(labelCol, row, colWidth, 2));
        row += 3;

        /* Bottom */
        builder.addSeparator("Verknüpfte Objekte", cc.xyw(labelCol, row, cols));
        row += 2;
        builder.add(
        		new JScrollPane(
        				getObjektverknuepungTabelle(),
        				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
                cc.xyw(labelCol, row, cols));
        row += 2;
        builder.add(
        		ButtonBarFactory.buildRightAlignedBar(getSelectObjektButton(),
        				getSaveAnh49Button()),
				cc.xyw(labelCol, row, cols));

        /* Right column */
        row = 1;
        labelCol = 5;
        fieldCol = 7;

        builder.appendRow("fill:100dlu");

        builder.addSeparator("Erfassung", cc.xyw(labelCol, row, colWidth));
        row += 2;
        builder.addLabel(GENEHMIGUNGSDATUM, cc.xy(labelCol, row));
        builder.add(
        		super.getComponent(GENEHMIGUNGSDATUM), cc.xy(fieldCol, row));
        row += 2;
        builder.addLabel(AENDERUNGSGENEHMIGUNGSDATUM, cc.xy(labelCol, row));
        builder.add(
        		super.getComponent(AENDERUNGSGENEHMIGUNGSDATUM),
        		cc.xy(fieldCol, row));
        row += 2;
        builder.add(
        		super.getComponent(ABGEMELDET),
        		cc.xy(fieldCol, row, "l,d"));
        row += 2;
        builder.add(
        		super.getComponent(ABWASSERFREI),
        		cc.xy(fieldCol, row, "l,d"));
        row += 2;
        builder.add(
        		super.getComponent(E_SATZUNG),
        		cc.xy(fieldCol, row, "l,d"));
        row += 2;

        builder.addSeparator(
        		"Wiedervorlage", cc.xyw(labelCol, row, colWidth));
        row += 2;
        builder.addLabel(WIEDERVORLAGEDATUM, cc.xy(labelCol, row));
        builder.add(
        		super.getComponent(WIEDERVORLAGEDATUM), cc.xy(fieldCol, row));

        builder.nextLine();
    }

    public void fetchFormData() {
        fachdaten = Anh49Fachdaten.getAnh49ByObjekt(hauptModul.getObjekt());
        log.debug("Anhang 49 Objekt aus DB geholt: " + fachdaten);
    }

    public void updateForm() {
        if (fachdaten != null) {
        	super.setComponentValue(SACHBEARBEITER,
        			fachdaten.getSachbearbeiterIn());
        	super.setComponentValue(ANSPRECHPARTNER,
        			fachdaten.getAnsprechpartnerIn());
        	super.setComponentValue(SACHKUNDE_LFA,
        			fachdaten.getSachkundelfa());
        	super.setComponentValue(ANALYSEMONAT,
        			fachdaten.getAnalysemonat());
        	super.setComponentValue(BEMERKUNGEN,
        			fachdaten.getBemerkungen());

        	super.setComponentValue(GENEHMIGUNGSDATUM,
        			fachdaten.getGenehmigung());
        	super.setComponentValue(AENDERUNGSGENEHMIGUNGSDATUM,
        			fachdaten.getAenderungsgenehmigung());
        	super.setComponentValue(ABGEMELDET,
        			fachdaten.getAbgemeldet());
        	super.setComponentValue(ABWASSERFREI,
        			fachdaten.getAbwasserfrei());
        	super.setComponentValue(E_SATZUNG,
        			fachdaten.getESatzung());
        	super.setComponentValue(WIEDERVORLAGEDATUM,
        			fachdaten.getWiedervorlage());

            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());
        } else {
            enableAll(false);
            hauptModul.getFrame().changeStatus(
                    "FEHLER: Kein Anhang 49 Objekt gefunden!",
                    HauptFrame.ERROR_COLOR);
        }
    }

    public void clearForm() {
    	super.clearAllComponents();
    }

    public void enableAll(boolean enabled) {
        // Wenn das Fachdaten-Objekt null ist,
        // können die Elemente nicht wieder aktiviert werden:
        if (!(enabled && (fachdaten == null))) {
        	super.setAllComponentsEnabled(enabled);
        }
    }

    public Anh49Fachdaten getFachdaten() {
        return fachdaten;
    }

    private boolean saveAnh49Daten() {
        boolean success;

        fachdaten.setSachbearbeiterIn(
        		(String)super.getComponentValue(SACHBEARBEITER));
        fachdaten.setAnsprechpartnerIn(
        		(String)super.getComponentValue(ANSPRECHPARTNER));
        fachdaten.setSachkundelfa(
        		(String)super.getComponentValue(SACHKUNDE_LFA));
        fachdaten.setAnalysemonat(
        		(String)super.getComponentValue(ANALYSEMONAT));
        fachdaten.setBemerkungen(
        		(String)super.getComponentValue(BEMERKUNGEN));

        fachdaten.setGenehmigung(
        		(Date)super.getComponentValue(GENEHMIGUNGSDATUM));
        fachdaten.setAenderungsgenehmigung(
        		(Date)super.getComponentValue(AENDERUNGSGENEHMIGUNGSDATUM));
        fachdaten.setAbgemeldet(
        		(Boolean)super.getComponentValue(ABGEMELDET));
        fachdaten.setAbwasserfrei(
        		(Boolean)super.getComponentValue(ABWASSERFREI));
        fachdaten.setESatzung(
        		(Boolean)super.getComponentValue(E_SATZUNG));
        fachdaten.setWiedervorlage(
        		(Date)super.getComponentValue(WIEDERVORLAGEDATUM));

        success = Anh49Fachdaten.saveFachdaten(fachdaten);

        if (!success) {
            log.debug("Anh49 Objekt " + fachdaten
                    + " konnte nicht gespeichert werden!");
        }

        return success;
    }

    public void completeObjekt() {
        if (hauptModul.isNew() || fachdaten == null) {
            // Neues Anhang49-Objekt erzeugen
            fachdaten = new Anh49Fachdaten();
            // Objekt_Id setzen
            fachdaten.setBasisObjekt(hauptModul.getObjekt());

            // Anhang49-Objekt speichern
            Anh49Fachdaten.saveFachdaten(fachdaten);
            log.debug("Neues Anh49 Objekt " + fachdaten + " gespeichert.");
        }
    }

    private JButton getSaveAnh49Button() {
        if (saveAnh49Button == null) {
            saveAnh49Button = new JButton("Speichern");

            saveAnh49Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh49Daten()) {
                        hauptModul.getFrame().changeStatus("Anhang 49-Objekt erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
                    } else {
                        hauptModul.getFrame().changeStatus("Fehler beim Speichern des Objekts!", HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }
        return saveAnh49Button;
    }

    private JTable getObjektverknuepungTabelle() {

        if (objektVerknuepfungModel == null) {
            objektVerknuepfungModel = new ObjektVerknuepfungModel(hauptModul
                    .getObjekt());

            if (objektverknuepfungTabelle == null) {
                objektverknuepfungTabelle = new JTable(objektVerknuepfungModel);
            } else {
                objektverknuepfungTabelle.setModel(objektVerknuepfungModel);
            }
            objektverknuepfungTabelle.getColumnModel().getColumn(0)
                    .setPreferredWidth(5);
            objektverknuepfungTabelle.getColumnModel().getColumn(1)
                    .setPreferredWidth(100);
            objektverknuepfungTabelle.getColumnModel().getColumn(2)
                    .setPreferredWidth(250);

            objektverknuepfungTabelle
                    .addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            if ((e.getClickCount() == 2)
                                    && (e.getButton() == 1)) {
                                Point origin = e.getPoint();
                                int row = getObjektverknuepungTabelle()
                                        .rowAtPoint(origin);

                                if (row != -1) {
                                    BasisObjektverknuepfung obj = objektVerknuepfungModel
                                            .getRow(row);
                                    if (obj.getBasisObjektByIstVerknuepftMit()
                                            .getObjektid().intValue() != hauptModul
                                            .getObjekt().getObjektid()
                                            .intValue())
                                        hauptModul
                                                .getManager()
                                                .getSettingsManager()
                                                .setSetting(
                                                        "auik.imc.edit_object",
                                                        obj
                                                                .getBasisObjektByIstVerknuepftMit()
                                                                .getObjektid()
                                                                .intValue(),
                                                        false);
                                    else
                                        hauptModul
                                                .getManager()
                                                .getSettingsManager()
                                                .setSetting(
                                                        "auik.imc.edit_object",
                                                        obj
                                                                .getBasisObjektByObjekt()
                                                                .getObjektid()
                                                                .intValue(),
                                                        false);
                                    hauptModul.getManager().switchModul(
                                            "m_objekt_bearbeiten");
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

            objektverknuepfungTabelle.getInputMap().put(
                    (KeyStroke) getVerknuepfungLoeschAction().getValue(
                            Action.ACCELERATOR_KEY),
                    getVerknuepfungLoeschAction().getValue(Action.NAME));
            objektverknuepfungTabelle.getActionMap().put(
                    getVerknuepfungLoeschAction().getValue(Action.NAME),
                    getVerknuepfungLoeschAction());
        }

        return objektverknuepfungTabelle;

    }

    private void showVerknuepfungPopup(MouseEvent e) {
        if (verknuepfungPopup == null) {
            verknuepfungPopup = new JPopupMenu("Objekt");
            JMenuItem loeschItem = new JMenuItem(getVerknuepfungLoeschAction());
            verknuepfungPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = objektverknuepfungTabelle.rowAtPoint(origin);

            if (row != -1) {
                objektverknuepfungTabelle.setRowSelectionInterval(row, row);
                verknuepfungPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private Action getVerknuepfungLoeschAction() {
        if (verknuepfungLoeschAction == null) {
            verknuepfungLoeschAction = new AbstractAction("Löschen") {
				private static final long serialVersionUID = 3694639072102209194L;

				@Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                            && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        BasisObjektverknuepfung verknuepfung = objektVerknuepfungModel
                                .getRow(row);
                        int answer = JOptionPane
                                .showConfirmDialog(
                                        hauptModul.getPanel(),
                                "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                    + "Hinweis: Die Aktion betrifft nur die Verknüpfung, die Objekte bleiben erhalten und können jederzeit neu verknüpft werden.",
                                "Löschen bestätigen",
                                        JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            if (objektVerknuepfungModel.removeRow(row)) {
                                hauptModul.getFrame().changeStatus(
                                    "Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                hauptModul.getFrame().changeStatus(
                                    "Konnte das Objekt nicht löschen!",
                                        HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            verknuepfungLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
                    KeyEvent.VK_L));
            verknuepfungLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke
                    .getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return verknuepfungLoeschAction;
    }

    private JButton getSelectObjektButton() {
        if (selectObjektButton == null) {
            selectObjektButton = new JButton("Objekt auswählen");

            selectObjektButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ObjektChooser chooser = new ObjektChooser(hauptModul
                            .getFrame(), fachdaten.getBasisObjekt(),
                            objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return selectObjektButton;
    }
}

