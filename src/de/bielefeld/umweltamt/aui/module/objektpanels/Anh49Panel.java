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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

import org.hibernate.Session;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 * Das "Anhang 49"-Tab des BasisObjektBearbeiten-Moduls.
 * @author Gerd Genuit
 */
public class Anh49Panel extends AbstractAnhangPanel {
    private static final long serialVersionUID = 2262140075740338093L;

    /* Note: As these strings are used as keys in the underlying HashMap,     *
     * they should be unique.                                                 */
    /* Widgets - left */
    private final String ANSPRECHPARTNER = "Ansprechpartner:";
    private final String SACHKUNDE_LFA = "Sachkunde LFA:";
    private final String ANALYSEMONAT = "Analysemonat:";
    private final String BEMERKUNGEN = "Bemerkungen";
    /* Widgets - right */
    private final String GENEHMIGUNGSDATUM = "Genehmigungsdatum:";
    private final String AENDERUNGSGENEHMIGUNGSDATUM = "Änderungsgen.-datum:";
    private final String ABGEMELDET = "abgemeldet";
    private final String ABWASSERFREI = "abwasserfrei";
    private final String E_SATZUNG = "E-Satzung";
    private final String WIEDERVORLAGEDATUM = "Wiedervorlagedatum:";
    /* Widgets - bottom */
    private final String SPEICHERN = "Speichern";

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


        super.addComponent(this.ANSPRECHPARTNER, new LimitedTextField(50));
        super.addComponent(this.SACHKUNDE_LFA, new LimitedTextField(50));
        super.addComponent(this.ANALYSEMONAT, new LimitedTextField(50));
        JTextArea textArea = new LimitedTextArea(150);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        super.addComponent(this.BEMERKUNGEN, textArea);

        super.addComponent(this.GENEHMIGUNGSDATUM, new TextFieldDateChooser());
        super.addComponent(this.AENDERUNGSGENEHMIGUNGSDATUM,
            new TextFieldDateChooser());
        super.addComponent(this.ABGEMELDET, new JCheckBox(this.ABGEMELDET));
        super.addComponent(this.ABWASSERFREI, new JCheckBox(this.ABWASSERFREI));
        super.addComponent(this.E_SATZUNG, new JCheckBox(this.E_SATZUNG));
        super.addComponent(this.WIEDERVORLAGEDATUM, new TextFieldDateChooser());
        super.addComponent(this.SPEICHERN, getSaveAnh49Button());

        PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 1, 0, 1, 1,
                0, 0, 5, 5);

        PanelBuilder bearbeitung = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                0, 0, 5, 5);
        bearbeitung.addComponent(super.getComponent(this.ANSPRECHPARTNER), this.ANSPRECHPARTNER, true);
        bearbeitung.addComponent(super.getComponent(this.SACHKUNDE_LFA), this.SACHKUNDE_LFA, true);

        PanelBuilder erfassung = new PanelBuilder(bearbeitung);
        erfassung.addComponents(true, super.getComponent(this.ABGEMELDET), super.getComponent(this.E_SATZUNG));
        erfassung.addComponent(super.getComponent(this.ABWASSERFREI), true, true);

        builder.setEmptyBorder(15);
        builder.addSeparator("Bearbeitung");
        builder.addSeparator("Erfassung", true);
        builder.addComponent(bearbeitung.getPanel());
        builder.addComponent(erfassung.getPanel(), true);
        builder.addSeparator("Analyse");
        builder.addSeparator("Wiedervorlage", true);
        builder.addComponent(super.getComponent(this.ANALYSEMONAT), this.ANALYSEMONAT);
        builder.addComponent(super.getComponent(this.WIEDERVORLAGEDATUM), this.WIEDERVORLAGEDATUM, true);
        builder.addSeparator("Bemerkungen", true);
        builder.setWeightY(0.4);
        builder.addComponent(new JScrollPane(super.getComponent(this.BEMERKUNGEN),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), true);
        builder.setWeightY(0);
        builder.addSeparator("Veknüpfte Objekte", true);
        builder.setWeightY(0.6);
        builder.addComponent(new JScrollPane(getObjektverknuepungTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), true);
        builder.setWeight(0, 0);
        builder.fillRow();
        builder.fillRow();
        builder.setInsets(0, 0, 0, 5);
        builder.addComponent(PanelBuilder.buildRightAlignedButtonToolbar(getSelectObjektButton(), getSaveAnh49Button()), true);

        builder.setPreferedSize(750, 500);

        PanelBuilder content = new PanelBuilder(PanelBuilder.NORTHWEST, true, true, 0, 0, 1, 1,
                0, 0, 5, 5);
        content.addComponent(builder.getPanel());
        content.fillRow(true);
        content.fillColumn();

        this.setLayout(new BorderLayout());
        this.add(content.getPanel());
    }

    public void fetchFormData() {
        this.fachdaten = Anh49Fachdaten.findByObjektId(
            this.hauptModul.getObjekt().getId());
        this.log.debug("Anhang 49 Objekt aus DB geholt: " + this.fachdaten);
    }

    public void updateForm() {
        if (this.fachdaten != null) {
            super.setComponentValue(this.ANSPRECHPARTNER,
                this.fachdaten.getAnsprechpartnerIn());
            super.setComponentValue(this.SACHKUNDE_LFA,
                this.fachdaten.getSachkundelfa());
            super.setComponentValue(this.ANALYSEMONAT,
                this.fachdaten.getAnalysemonat());
            super.setComponentValue(this.BEMERKUNGEN,
                this.fachdaten.getBemerkungen());

            super.setComponentValue(this.GENEHMIGUNGSDATUM,
                this.fachdaten.getGenehmigung());
            super.setComponentValue(this.AENDERUNGSGENEHMIGUNGSDATUM,
                this.fachdaten.getAenderungsgenehmigung());
            super.setComponentValue(this.ABGEMELDET,
                this.fachdaten.getAbgemeldet());
            super.setComponentValue(this.ABWASSERFREI,
                this.fachdaten.getAbwasserfrei());
            super.setComponentValue(this.E_SATZUNG,
                this.fachdaten.getESatzung());
            super.setComponentValue(this.WIEDERVORLAGEDATUM,
                this.fachdaten.getWiedervorlage());

            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
        } else {
            enableAll(false);
            this.hauptModul.getFrame().changeStatus(
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
        if (!(enabled && (this.fachdaten == null))) {
            super.setAllComponentsEnabled(enabled);
        }
    }

    public Anh49Fachdaten getFachdaten() {
        return this.fachdaten;
    }

    private boolean saveAnh49Daten() {
        boolean success;

        this.fachdaten.setAnsprechpartnerIn((String) super
            .getComponentValue(this.ANSPRECHPARTNER));
        this.fachdaten.setSachkundelfa((String) super
            .getComponentValue(this.SACHKUNDE_LFA));
        this.fachdaten.setAnalysemonat((String) super
            .getComponentValue(this.ANALYSEMONAT));
        this.fachdaten.setBemerkungen((String) super
            .getComponentValue(this.BEMERKUNGEN));

        this.fachdaten.setGenehmigung((Date) super
            .getComponentValue(this.GENEHMIGUNGSDATUM));
        this.fachdaten.setAenderungsgenehmigung((Date) super
            .getComponentValue(this.AENDERUNGSGENEHMIGUNGSDATUM));
        this.fachdaten.setAbgemeldet((Boolean) super
            .getComponentValue(this.ABGEMELDET));
        this.fachdaten.setAbwasserfrei((Boolean) super
            .getComponentValue(this.ABWASSERFREI));
        this.fachdaten.setESatzung((Boolean) super
            .getComponentValue(this.E_SATZUNG));
        this.fachdaten.setWiedervorlage((Date) super
            .getComponentValue(this.WIEDERVORLAGEDATUM));

        success = this.fachdaten.merge();

        if (!success) {
            this.log.debug("Anh49 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }

        return success;
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neues Anhang49-Objekt erzeugen
            this.fachdaten = new Anh49Fachdaten();
            // Objekt_Id setzen
            this.fachdaten.setObjekt(this.hauptModul.getObjekt());

            // Anhang49-Objekt speichern
            Anh49Fachdaten.merge(this.fachdaten);
            this.log.debug("Neues Anh49 Objekt " + this.fachdaten
                + " gespeichert.");
        }
    }

    private JButton getSaveAnh49Button() {
        if (this.saveAnh49Button == null) {
            this.saveAnh49Button = new JButton("Speichern");

            this.saveAnh49Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh49Daten()) {
                        Anh49Panel.this.hauptModul.getFrame().changeStatus(
                            "Anhang 49-Objekt erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh49Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Objekts!",
                            HauptFrame.ERROR_COLOR);
                    }

                    Anh49Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh49Button;
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
                                Objektverknuepfung obj = Anh49Panel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != Anh49Panel.this.hauptModul
                                    .getObjekt().getId().intValue())
                                    Anh49Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                else
                                    Anh49Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                Anh49Panel.this.hauptModul.getManager()
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
                private static final long serialVersionUID = 3694639072102209194L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung = Anh49Panel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (Anh49Panel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                Anh49Panel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                Anh49Panel.this.log
                                    .debug("Objekt " + verknuepfung.getId()
                                        + " wurde gelöscht!");
                            } else {
                                Anh49Panel.this.hauptModul.getFrame()
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
                    if(Anh49Panel.this.fachdaten == null){
                        Anh49Panel.this.log.debug("fachdaten null");
                    }
                    if(Anh49Panel.this.fachdaten.getObjekt() == null){
                        Anh49Panel.this.log.debug("fachdaten.getBasisObjekt null");
                    }
                    if(Anh49Panel.this.objektVerknuepfungModel == null){
                        Anh49Panel.this.log.debug("objektVerknuepfungsmodel null");
                    }
                    ObjektChooser chooser = new ObjektChooser(
                        Anh49Panel.this.hauptModul.getFrame(),
                        Anh49Panel.this.fachdaten.getObjekt(),
                        Anh49Panel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }
}
