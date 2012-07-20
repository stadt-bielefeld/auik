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
 * Created on 28.06.2005 by u633d
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
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhSuevFachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Zahnarzt"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class SuevPanel extends JPanel {
    private static final long serialVersionUID = -6379153046356849276L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JCheckBox groesser3haCheck = null;
    private JFormattedTextField versFlaecheFeld = null;
    private JCheckBox suevkanPflichtCheck = null;
    private JCheckBox indirektswCheck = null;
    private JCheckBox indirektrwCheck = null;
    private JCheckBox direktswCheck = null;
    private JCheckBox direktrwCheck = null;
    private JCheckBox datumAnzeige58Check = null;
    private JCheckBox sanierungErfolgtCheck = null;
    private JCheckBox sanierungskonzeptCheck = null;
    private JCheckBox keineAngabenCheck = null;
    private TextFieldDateChooser datAnzeige58Datum = null;
    private TextFieldDateChooser datAnschreibenDatum = null;
    private JButton saveSuevButton = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    // Daten
    private AnhSuevFachdaten fachdaten = null;

    public SuevPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Suev-Kan Verfahren";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:70dlu, 5dlu, 90dlu, 5dlu, 10dlu, 5dlu, 90dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.appendSeparator("Fachdaten");
        builder.append("Datum Anschreiben:", getDatAnschreibenDatum());
        builder.append("", getSanierungskonzeptCheck());
        builder.nextLine();
        builder.append("Datum Anzeige:", getDatAnzeige58Datum());
        builder.append("", getSanierungErfolgtCheck());
        builder.nextLine();
        builder.append("versiegelte Fläche:", getVersFlaecheFeld());
        builder.append("", getDirektrwCheck());
        builder.nextLine();
        builder.append("", getGroesser3haCheck());
        builder.append("", getDirektswCheck());
        builder.nextLine();
        builder.append("", getSuevkanPflichtCheck());
        builder.append("", getIndirektrwCheck());
        builder.nextLine();
        builder.append("", getKeineAngabenCheck());
        builder.append("", getIndirektswCheck());
        builder.nextLine();

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
            getSelectObjektButton(), getSaveSuevButton());

        // JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveSuevButton());
        builder.append(buttonBar, 7);
    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = AnhSuevFachdaten.getSuevByObjekt(this.hauptModul
            .getObjekt());
        log.debug("SuevKan-Verfahren aus DB geholt: " + this.fachdaten);
    }

    public void updateForm() throws RuntimeException {

        if (this.fachdaten != null) {
            if (this.fachdaten.getVersFlaeche() != null) {
                getVersFlaecheFeld().setText(
                    this.fachdaten.getVersFlaeche().toString());
            }
            if (this.fachdaten.getDatAnschreiben() != null) {
                getDatAnschreibenDatum().setDate(
                    this.fachdaten.getDatAnschreiben());
            }
            if (this.fachdaten.getDatAnzeige58() != null) {
                getDatAnzeige58Datum()
                    .setDate(this.fachdaten.getDatAnzeige58());
            }
            if (this.fachdaten.getDatumAnzeige58() != null) {
                if (this.fachdaten.getDatumAnzeige58() == true) {
                    getDatumAnzeige58Check().setSelected(true);
                } else {
                    getDatumAnzeige58Check().setSelected(false);
                }
            }
            if (this.fachdaten.getDirektrw() != null) {
                if (this.fachdaten.getDirektrw() == true) {
                    getDirektrwCheck().setSelected(true);
                } else {
                    getDirektrwCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getDirektsw() != null) {
                if (this.fachdaten.getDirektsw() == true) {
                    getDirektswCheck().setSelected(true);
                } else {
                    getDirektswCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getGroesser3ha() != null) {
                if (this.fachdaten.getGroesser3ha() == true) {
                    getGroesser3haCheck().setSelected(true);
                } else {
                    getGroesser3haCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getIndirektrw() != null) {
                if (this.fachdaten.getIndirektrw() == true) {
                    getIndirektrwCheck().setSelected(true);
                } else {
                    getIndirektrwCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getIndirektsw() != null) {
                if (this.fachdaten.getIndirektsw() == true) {
                    getIndirektswCheck().setSelected(true);
                } else {
                    getIndirektswCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getKeineAngaben() != null) {
                if (this.fachdaten.getKeineAngaben() == true) {
                    getKeineAngabenCheck().setSelected(true);
                } else {
                    getKeineAngabenCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getSanierungErfolgt() != null) {
                if (this.fachdaten.getSanierungErfolgt() == true) {
                    getSanierungErfolgtCheck().setSelected(true);
                } else {
                    getSanierungErfolgtCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getSanierungskonzept() != null) {
                if (this.fachdaten.getSanierungskonzept() == true) {
                    getSanierungskonzeptCheck().setSelected(true);
                } else {
                    getSanierungskonzeptCheck().setSelected(false);
                }
            }
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
        }

    }

    public void clearForm() {
        getVersFlaecheFeld().setText(null);
        getDatAnschreibenDatum().setDate(null);
        getDatAnzeige58Datum().setDate(null);
        getDatumAnzeige58Check().setSelected(false);
        getDirektrwCheck().setSelected(false);
        getDirektswCheck().setSelected(false);
        getGroesser3haCheck().setSelected(false);
        getIndirektrwCheck().setSelected(false);
        getIndirektswCheck().setSelected(false);
        getKeineAngabenCheck().setSelected(false);
        getSanierungErfolgtCheck().setSelected(false);
        getSanierungskonzeptCheck().setSelected(false);
        getSuevkanPflichtCheck().setSelected(false);
    }

    public void enableAll(boolean enabled) {
        // This is intentionally left blank
    }

    @Override
    public String getName() {
        return this.name;
    }

    private boolean saveSuevDaten() {
        boolean success;

        Integer versfl = ((IntegerField) this.versFlaecheFeld).getIntValue();
        this.fachdaten.setVersFlaeche(versfl);

        Date anschreiben = this.datAnschreibenDatum.getDate();
        this.fachdaten.setDatAnschreiben(anschreiben);

        Date anzeigedatum = this.datAnzeige58Datum.getDate();
        this.fachdaten.setDatAnzeige58(anzeigedatum);

        if (getDatumAnzeige58Check().isSelected()) {
            this.fachdaten.setDatumAnzeige58(true);
        } else {
            this.fachdaten.setDatumAnzeige58(false);
        }

        if (getDirektrwCheck().isSelected()) {
            this.fachdaten.setDirektrw(true);
        } else {
            this.fachdaten.setDirektrw(false);
        }

        if (getDirektswCheck().isSelected()) {
            this.fachdaten.setDirektsw(true);
        } else {
            this.fachdaten.setDirektsw(false);
        }

        if (getGroesser3haCheck().isSelected()) {
            this.fachdaten.setGroesser3ha(true);
        } else {
            this.fachdaten.setGroesser3ha(false);
        }

        if (getIndirektrwCheck().isSelected()) {
            this.fachdaten.setIndirektrw(true);
        } else {
            this.fachdaten.setIndirektrw(false);
        }

        if (getIndirektswCheck().isSelected()) {
            this.fachdaten.setIndirektsw(true);
        } else {
            this.fachdaten.setIndirektsw(false);
        }

        if (getKeineAngabenCheck().isSelected()) {
            this.fachdaten.setKeineAngaben(true);
        } else {
            this.fachdaten.setKeineAngaben(false);
        }

        if (getSanierungErfolgtCheck().isSelected()) {
            this.fachdaten.setSanierungErfolgt(true);
        } else {
            this.fachdaten.setSanierungErfolgt(false);
        }

        if (getSanierungskonzeptCheck().isSelected()) {
            this.fachdaten.setSanierungskonzept(true);
        } else {
            this.fachdaten.setSanierungskonzept(false);
        }

        if (getSuevkanPflichtCheck().isSelected()) {
            this.fachdaten.setSuevkanPflicht(true);
        } else {
            this.fachdaten.setSuevkanPflicht(false);
        }

        success = AnhSuevFachdaten.saveFachdaten(this.fachdaten);
        if (success) {
            log.debug("SuevKan Verfahren " + this.fachdaten.getObjektid()
                + " gespeichert.");
        } else {
            log.debug("SuevKan Verfahren " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neues SuevKan Verfahren erzeugen
            this.fachdaten = new AnhSuevFachdaten();
            // Objekt_Id setzen
            this.fachdaten.setBasisObjekt(this.hauptModul.getObjekt());

            // SuevKan speichern
            AnhSuevFachdaten.saveFachdaten(this.fachdaten);
            log.debug("Neues SuevKan Verfahren " + this.fachdaten
                + " gespeichert.");
        }
    }

    private TextFieldDateChooser getDatAnschreibenDatum() {
        if (this.datAnschreibenDatum == null) {
            this.datAnschreibenDatum = new TextFieldDateChooser();
        }
        return this.datAnschreibenDatum;
    }

    private TextFieldDateChooser getDatAnzeige58Datum() {
        if (this.datAnzeige58Datum == null) {
            this.datAnzeige58Datum = new TextFieldDateChooser();
        }
        return this.datAnzeige58Datum;
    }

    private JCheckBox getDatumAnzeige58Check() {
        if (this.datumAnzeige58Check == null) {
            this.datumAnzeige58Check = new JCheckBox("Datum Anzeige");
        }
        return this.datumAnzeige58Check;
    }

    private JCheckBox getDirektrwCheck() {
        if (this.direktrwCheck == null) {
            this.direktrwCheck = new JCheckBox("Direkt RW");
        }
        return this.direktrwCheck;
    }

    private JCheckBox getDirektswCheck() {
        if (this.direktswCheck == null) {
            this.direktswCheck = new JCheckBox("Direkt SW");
        }
        return this.direktswCheck;
    }

    private JCheckBox getGroesser3haCheck() {
        if (this.groesser3haCheck == null) {
            this.groesser3haCheck = new JCheckBox("Größer 3 ha");
        }
        return this.groesser3haCheck;
    }

    private JCheckBox getIndirektrwCheck() {
        if (this.indirektrwCheck == null) {
            this.indirektrwCheck = new JCheckBox("Indirekt RW");
        }
        return this.indirektrwCheck;
    }

    private JCheckBox getIndirektswCheck() {
        if (this.indirektswCheck == null) {
            this.indirektswCheck = new JCheckBox("Indirekt SW");
        }
        return this.indirektswCheck;
    }

    private JCheckBox getKeineAngabenCheck() {
        if (this.keineAngabenCheck == null) {
            this.keineAngabenCheck = new JCheckBox("Keine Angaben");
        }
        return this.keineAngabenCheck;
    }

    private JCheckBox getSanierungErfolgtCheck() {
        if (this.sanierungErfolgtCheck == null) {
            this.sanierungErfolgtCheck = new JCheckBox("Sanierung erfolgt");
        }
        return this.sanierungErfolgtCheck;
    }

    private JCheckBox getSanierungskonzeptCheck() {
        if (this.sanierungskonzeptCheck == null) {
            this.sanierungskonzeptCheck = new JCheckBox("Sanierungskonzept");
        }
        return this.sanierungskonzeptCheck;
    }

    private JCheckBox getSuevkanPflichtCheck() {
        if (this.suevkanPflichtCheck == null) {
            this.suevkanPflichtCheck = new JCheckBox("SuevKan pflichtig");
        }
        return this.suevkanPflichtCheck;
    }

    private JFormattedTextField getVersFlaecheFeld() {
        if (this.versFlaecheFeld == null) {
            this.versFlaecheFeld = new IntegerField();
        }
        return this.versFlaecheFeld;
    }

    private JButton getSaveSuevButton() {
        if (this.saveSuevButton == null) {
            this.saveSuevButton = new JButton("Speichern");

            this.saveSuevButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveSuevDaten()) {
                        SuevPanel.this.hauptModul.getFrame().changeStatus(
                            "SuevKan Verfahren "
                                + SuevPanel.this.fachdaten.getObjektid()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        SuevPanel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des SuevKan Verfahrens!",
                            HauptFrame.ERROR_COLOR);
                    }

                    SuevPanel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveSuevButton;
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
                                BasisObjektverknuepfung obj = SuevPanel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getBasisObjektByIstVerknuepftMit()
                                    .getObjektid().intValue() != SuevPanel.this.hauptModul
                                    .getObjekt().getObjektid().intValue()) {
                                    SuevPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getBasisObjektByIstVerknuepftMit()
                                                .getObjektid().intValue(),
                                            false);
                                } else {
                                    SuevPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getBasisObjektByObjekt()
                                                .getObjektid().intValue(),
                                            false);
                                }
                                SuevPanel.this.hauptModul.getManager()
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
                private static final long serialVersionUID = 5629438504708021409L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        BasisObjektverknuepfung verknuepfung = SuevPanel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (SuevPanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                SuevPanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                SuevPanel.this.hauptModul.getFrame()
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
                        SuevPanel.this.hauptModul.getFrame(),
                        SuevPanel.this.fachdaten.getBasisObjekt(),
                        SuevPanel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }

}
