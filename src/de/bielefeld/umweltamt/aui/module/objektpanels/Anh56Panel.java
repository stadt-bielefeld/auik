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
 * $Id: Anh56Panel.java,v 1.1.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2010/02/04 13:34:38  u633d
 * Verkn�pfte Objekte
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/08/17 05:45:31  u633d
 * - Anhang 56 erstellt
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das Panel zum Bearbeiten von Druckereien
 * @author u633d
 */
public class Anh56Panel extends JPanel {
    private static final long serialVersionUID = -6981678796941528077L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JTextField druckverfahrenFeld = null;
    private JTextField verbrauchFeld = null;
    private JTextField entsorgungFeld = null;
    private TextFieldDateChooser gen59Datum = null;
    private TextFieldDateChooser gen58Datum = null;
    private JCheckBox abaCheck = null;
    private JCheckBox genpflichtCheck = null;
    private JCheckBox abwasseranfallCheck = null;
    private JTextArea BemerkungenArea = null;

    private JButton saveAnh56Button = null;

    // Daten
    private Anh56Fachdaten fachdaten = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public Anh56Panel(BasisObjektBearbeiten hauptModul) {
        this.name = "Druckerei";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:90dlu, 5dlu, 95dlu, 5dlu, r:0dlu, 0dlu, 90dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.appendSeparator("Fachdaten");
        builder.append("Druckverfahren:", getDruckverfahrenFeld());
        builder.append("", getAbwasseranfallCheck());
        builder.nextLine();
        builder.append("Wasserverbrauch:", getVerbrauchFeld());
        builder.append("", getAbaCheck());
        builder.nextLine();
        builder.append("Entsorgung:", getEntsorgungFeld());
        builder.append("", getGenpflichtCheck());
        builder.nextLine();
        builder.append("Datum 58er Genehmigung:", getGen58Datum());
        builder.nextLine();
        builder.append("Datum 59er Genehmigung:", getGen59Datum());
        builder.nextLine();

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);

        builder.appendSeparator("Verknüpfte Objekte");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane objektverknuepfungScroller = new JScrollPane(
            getObjektverknuepungTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 7);
        builder.nextLine();

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(
            getSelectObjektButton(), getSaveAnh56Button());

        // JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveAnh56Button());
        builder.append(buttonBar, 7);

    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neues Anhang 56 Objekt erzeugen
            this.fachdaten = new Anh56Fachdaten();
            // Objekt_Id setzen
            this.fachdaten.setBasisObjekt(this.hauptModul.getObjekt());

            // Anhang 56 Objekt speichern
            Anh56Fachdaten.merge(this.fachdaten);
            log.debug("Neues Anh 56 Objekt " + this.fachdaten + " gespeichert.");
        }
    }

    private boolean saveAnh56Daten() {
        boolean success;

        String bemerkungen = this.BemerkungenArea.getText();
        if ("".equals(bemerkungen)) {
            this.fachdaten.setBemerkungen(null);
        } else {
            this.fachdaten.setBemerkungen(bemerkungen);
        }

        String druckverf = this.druckverfahrenFeld.getText();
        if ("".equals(druckverf)) {
            this.fachdaten.setDruckverfahren(null);
        } else {
            this.fachdaten.setDruckverfahren(druckverf);
        }

        String verbrauch = this.verbrauchFeld.getText();
        if ("".equals(verbrauch)) {
            this.fachdaten.setVerbrauch(null);
        } else {
            this.fachdaten.setVerbrauch(verbrauch);
        }

        String entsorgung = this.entsorgungFeld.getText();
        if ("".equals(entsorgung)) {
            this.fachdaten.setEntsorgung(null);
        } else {
            this.fachdaten.setEntsorgung(entsorgung);
        }

        Date gen58 = this.gen58Datum.getDate();
        this.fachdaten.setGen58(gen58);

        Date gen59 = this.gen59Datum.getDate();
        this.fachdaten.setGen59(gen59);

        if (getAbaCheck().isSelected()) {
            this.fachdaten.setAba(true);
        } else {
            this.fachdaten.setAba(false);
        }

        if (getGenpflichtCheck().isSelected()) {
            this.fachdaten.setGenpflicht(true);
        } else {
            this.fachdaten.setGenpflicht(false);
        }

        if (getAbwasseranfallCheck().isSelected()) {
            this.fachdaten.setAbwasseranfall(true);
        } else {
            this.fachdaten.setAbwasseranfall(false);
        }

        success = fachdaten.merge();
        if (success) {
            log.debug("Anh 56 Objekt " + this.fachdaten.getId()
                + " gespeichert.");
        } else {
            log.debug("Anh 56 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void enableAll(boolean enabled) {
        getBemerkungenArea().setEnabled(enabled);
        getDruckverfahrenFeld().setEnabled(enabled);
        getVerbrauchFeld().setEnabled(enabled);
        getEntsorgungFeld().setEnabled(enabled);

        getGen58Datum().setEnabled(enabled);
        getGen59Datum().setEnabled(enabled);

        getAbaCheck().setEnabled(enabled);
        getGenpflichtCheck().setEnabled(enabled);
        getAbwasseranfallCheck().setEnabled(enabled);
    }

    public void clearForm() {
        getBemerkungenArea().setText(null);
        getDruckverfahrenFeld().setText(null);
        getVerbrauchFeld().setText(null);
        getEntsorgungFeld().setText(null);

        getGen58Datum().setDate(null);
        getGen59Datum().setDate(null);

        getAbaCheck().setSelected(false);
        getGenpflichtCheck().setSelected(false);
        getAbwasseranfallCheck().setSelected(false);
    }

    public void updateForm() throws RuntimeException {
        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null) {
                getBemerkungenArea().setText(this.fachdaten.getBemerkungen());
            }
            if (this.fachdaten.getDruckverfahren() != null) {
                getDruckverfahrenFeld().setText(
                    this.fachdaten.getDruckverfahren());
            }
            if (this.fachdaten.getVerbrauch() != null) {
                getVerbrauchFeld().setText(this.fachdaten.getVerbrauch());
            }
            if (this.fachdaten.getEntsorgung() != null) {
                getEntsorgungFeld().setText(this.fachdaten.getEntsorgung());
            }

            if (this.fachdaten.getGen58() != null) {
                getGen58Datum().setDate(this.fachdaten.getGen58());
            }
            if (this.fachdaten.getGen59() != null) {
                getGen59Datum().setDate(this.fachdaten.getGen59());
            }

            if (this.fachdaten.getAba() != null) {
                if (this.fachdaten.getAba() == true) {
                    getAbaCheck().setSelected(true);
                } else {
                    getAbaCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getGenpflicht() != null) {
                if (this.fachdaten.getGenpflicht() == true) {
                    getGenpflichtCheck().setSelected(true);
                } else {
                    getGenpflichtCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getAbwasseranfall() != null) {
                if (this.fachdaten.getAbwasseranfall() == true) {
                    getAbwasseranfallCheck().setSelected(true);
                } else {
                    getAbwasseranfallCheck().setSelected(false);
                }
                this.objektVerknuepfungModel.setObjekt(this.hauptModul
                    .getObjekt());
            }
        }
    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = Anh56Fachdaten.findByObjektId(
            this.hauptModul.getObjekt().getObjektid());
        log.debug("Anhang 56 Objekt aus DB geholt: ID" + this.fachdaten);
    }

    private JButton getSaveAnh56Button() {
        if (this.saveAnh56Button == null) {
            this.saveAnh56Button = new JButton("Speichern");

            this.saveAnh56Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh56Daten()) {
                        Anh56Panel.this.hauptModul.getFrame().changeStatus(
                            "Anh 56 Objekt "
                                + Anh56Panel.this.fachdaten.getId()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh56Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Anh 56 Objekt!",
                            HauptFrame.ERROR_COLOR);
                    }

                    Anh56Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh56Button;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private JCheckBox getAbaCheck() {
        if (this.abaCheck == null) {
            this.abaCheck = new JCheckBox("Abwasserbehandlung");
        }
        return this.abaCheck;
    }

    private JCheckBox getAbwasseranfallCheck() {
        if (this.abwasseranfallCheck == null) {
            this.abwasseranfallCheck = new JCheckBox("Abwasseranfall");
        }
        return this.abwasseranfallCheck;
    }

    private JTextField getDruckverfahrenFeld() {
        if (this.druckverfahrenFeld == null) {
            this.druckverfahrenFeld = new LimitedTextField(150);
        }
        return this.druckverfahrenFeld;
    }

    private JTextField getEntsorgungFeld() {
        if (this.entsorgungFeld == null) {
            this.entsorgungFeld = new LimitedTextField(150);
        }
        return this.entsorgungFeld;
    }

    private TextFieldDateChooser getGen58Datum() {
        if (this.gen58Datum == null) {
            this.gen58Datum = new TextFieldDateChooser();
        }
        return this.gen58Datum;
    }

    private TextFieldDateChooser getGen59Datum() {
        if (this.gen59Datum == null) {
            this.gen59Datum = new TextFieldDateChooser();
        }
        return this.gen59Datum;
    }

    private JCheckBox getGenpflichtCheck() {
        if (this.genpflichtCheck == null) {
            this.genpflichtCheck = new JCheckBox("Genehmigungspflicht");
        }
        return this.genpflichtCheck;
    }

    private JTextField getVerbrauchFeld() {
        if (this.verbrauchFeld == null) {
            this.verbrauchFeld = new LimitedTextField(150);
        }
        return this.verbrauchFeld;
    }

    private JTextArea getBemerkungenArea() {
        if (this.BemerkungenArea == null) {
            this.BemerkungenArea = new LimitedTextArea(255);
            this.BemerkungenArea.setLineWrap(true);
            this.BemerkungenArea.setWrapStyleWord(true);
        }
        return this.BemerkungenArea;
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
                                BasisObjektverknuepfung obj = Anh56Panel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getBasisObjektByIstVerknuepftMit()
                                    .getObjektid().intValue() != Anh56Panel.this.hauptModul
                                    .getObjekt().getObjektid().intValue())
                                    Anh56Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getBasisObjektByIstVerknuepftMit()
                                                .getObjektid().intValue(),
                                            false);
                                else
                                    Anh56Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getBasisObjektByObjekt()
                                                .getObjektid().intValue(),
                                            false);
                                Anh56Panel.this.hauptModul.getManager()
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
                private static final long serialVersionUID = -7858293832318327844L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        BasisObjektverknuepfung verknuepfung = Anh56Panel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (Anh56Panel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                Anh56Panel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                Anh56Panel.this.hauptModul.getFrame()
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
                    ObjektChooser chooser = new ObjektChooser(
                        Anh56Panel.this.hauptModul.getFrame(),
                        Anh56Panel.this.fachdaten.getBasisObjekt(),
                        Anh56Panel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }

}
