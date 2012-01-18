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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhSuevFachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Zahnarzt"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class SuevPanel extends JPanel {
	/** Logging */
    private static final Logger log = AuikLogger.getLogger();

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

    //Listener
    private ActionListener editButtonListener;


    public SuevPanel(BasisObjektBearbeiten hauptModul) {
        name = "Suev-Kan Verfahren";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout (
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
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 7);
        builder.nextLine();

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(
                getSelectObjektButton(), getSaveSuevButton());


        //JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveSuevButton());
        builder.append(buttonBar, 7);
    }

    public void fetchFormData() throws RuntimeException {
        fachdaten = AnhSuevFachdaten.getSuevByObjekt(hauptModul.getObjekt());
        log.debug("(SuevPanel.fetchFormData) " + "SuevKan-Verfahren aus DB geholt: " + fachdaten);
    }


    public void updateForm() throws RuntimeException {

        if (fachdaten != null) {
            if (fachdaten.getVersFlaeche() != null) {
                getVersFlaecheFeld().setText(fachdaten.getVersFlaeche().toString());
            }
            if (fachdaten.getDatAnschreiben() != null) {
                getDatAnschreibenDatum().setDate(fachdaten.getDatAnschreiben());
            }
            if (fachdaten.getDatAnzeige58() != null) {
                getDatAnzeige58Datum().setDate(fachdaten.getDatAnzeige58());
            }
            if (fachdaten.getDatumAnzeige58() != null) {
                if (fachdaten.getDatumAnzeige58() == true) {
                    getDatumAnzeige58Check().setSelected(true);
                }
                else {
                    getDatumAnzeige58Check().setSelected(false);
                }
            }
            if (fachdaten.getDirektrw() != null) {
                if (fachdaten.getDirektrw() == true) {
                    getDirektrwCheck().setSelected(true);
                }
                else {
                    getDirektrwCheck().setSelected(false);
                }
            }
            if (fachdaten.getDirektsw() != null) {
                if (fachdaten.getDirektsw() == true) {
                    getDirektswCheck().setSelected(true);
                }
                else {
                    getDirektswCheck().setSelected(false);
                }
            }
            if (fachdaten.getGroesser3ha() != null) {
                if (fachdaten.getGroesser3ha() == true) {
                    getGroesser3haCheck().setSelected(true);
                }
                else {
                    getGroesser3haCheck().setSelected(false);
                }
            }
            if (fachdaten.getIndirektrw() != null) {
                if (fachdaten.getIndirektrw() == true) {
                    getIndirektrwCheck().setSelected(true);
                }
                else {
                    getIndirektrwCheck().setSelected(false);
                }
            }
            if (fachdaten.getIndirektsw() != null) {
                if (fachdaten.getIndirektsw() == true) {
                    getIndirektswCheck().setSelected(true);
                }
                else {
                    getIndirektswCheck().setSelected(false);
                }
            }
            if (fachdaten.getKeineAngaben() != null) {
                if (fachdaten.getKeineAngaben() == true) {
                    getKeineAngabenCheck().setSelected(true);
                }
                else {
                    getKeineAngabenCheck().setSelected(false);
                }
            }
            if (fachdaten.getSanierungErfolgt() != null) {
                if (fachdaten.getSanierungErfolgt() == true) {
                    getSanierungErfolgtCheck().setSelected(true);
                }
                else {
                    getSanierungErfolgtCheck().setSelected(false);
                }
            }
            if (fachdaten.getSanierungskonzept() != null) {
                if (fachdaten.getSanierungskonzept() == true) {
                    getSanierungskonzeptCheck().setSelected(true);
                }
                else {
                    getSanierungskonzeptCheck().setSelected(false);
                }
            }
            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());
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

    }


    public String getName() {
        return name;
    }

    private boolean saveSuevDaten() {
        boolean success;

        Integer versfl = ((IntegerField)versFlaecheFeld).getIntValue();
        fachdaten.setVersFlaeche(versfl);

        Date anschreiben = datAnschreibenDatum.getDate();
        fachdaten.setDatAnschreiben(anschreiben);

        Date anzeigedatum = datAnzeige58Datum.getDate();
        fachdaten.setDatAnzeige58(anzeigedatum);

        if (getDatumAnzeige58Check().isSelected())  {
            fachdaten.setDatumAnzeige58(true);
        } else {
            fachdaten.setDatumAnzeige58(false);
        }

        if (getDirektrwCheck().isSelected())  {
            fachdaten.setDirektrw(true);
        } else {
            fachdaten.setDirektrw(false);
        }

        if (getDirektswCheck().isSelected())  {
            fachdaten.setDirektsw(true);
        } else {
            fachdaten.setDirektsw(false);
        }

        if (getGroesser3haCheck().isSelected())  {
            fachdaten.setGroesser3ha(true);
        } else {
            fachdaten.setGroesser3ha(false);
        }

        if (getIndirektrwCheck().isSelected())  {
            fachdaten.setIndirektrw(true);
        } else {
            fachdaten.setIndirektrw(false);
        }

        if (getIndirektswCheck().isSelected())  {
            fachdaten.setIndirektsw(true);
        } else {
            fachdaten.setIndirektsw(false);
        }

        if (getKeineAngabenCheck().isSelected())  {
            fachdaten.setKeineAngaben(true);
        } else {
            fachdaten.setKeineAngaben(false);
        }

        if (getSanierungErfolgtCheck().isSelected())  {
            fachdaten.setSanierungErfolgt(true);
        } else {
            fachdaten.setSanierungErfolgt(false);
        }

        if (getSanierungskonzeptCheck().isSelected())  {
            fachdaten.setSanierungskonzept(true);
        } else {
            fachdaten.setSanierungskonzept(false);
        }

        if (getSuevkanPflichtCheck().isSelected())  {
            fachdaten.setSuevkanPflicht(true);
        } else {
            fachdaten.setSuevkanPflicht(false);
        }

        success = AnhSuevFachdaten.saveFachdaten(fachdaten);
        if (success) {
            log.debug("(SuevFachdaten.saveFachdaten) "
            		+ "SuevKan Verfahren " + fachdaten.getObjektid()
            		+ " gespeichert.");
        } else {
            log.debug("(SuevFachdaten.saveFachdaten) "
            		+ "SuevKan Verfahren " + fachdaten
                    + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (hauptModul.isNew() || fachdaten == null) {
            // Neues SuevKan Verfahren erzeugen
            fachdaten = new AnhSuevFachdaten();
            // Objekt_Id setzen
            fachdaten.setBasisObjekt(hauptModul.getObjekt());

            // SuevKan speichern
            AnhSuevFachdaten.saveFachdaten(fachdaten);
            log.debug("(BasisObjektBearbeiten.completeObjekt) "
            		+ "Neues SuevKan Verfahren "+fachdaten+" gespeichert.");
        }
    }



    private TextFieldDateChooser getDatAnschreibenDatum() {
        if (datAnschreibenDatum == null) {
            datAnschreibenDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return datAnschreibenDatum;
    }
    private TextFieldDateChooser getDatAnzeige58Datum() {
        if (datAnzeige58Datum == null) {
            datAnzeige58Datum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return datAnzeige58Datum;
    }
    private JCheckBox getDatumAnzeige58Check() {
        if (datumAnzeige58Check == null) {
            datumAnzeige58Check = new JCheckBox("Datum Anzeige");
        }
        return datumAnzeige58Check;
    }
    private JCheckBox getDirektrwCheck() {
        if (direktrwCheck == null) {
            direktrwCheck = new JCheckBox("Direkt RW");
        }
        return direktrwCheck;
    }
    private JCheckBox getDirektswCheck() {
        if (direktswCheck == null) {
            direktswCheck = new JCheckBox("Direkt SW");
        }
        return direktswCheck;
    }
    private JCheckBox getGroesser3haCheck() {
        if (groesser3haCheck == null) {
            groesser3haCheck = new JCheckBox("Größer 3 ha");
        }
        return groesser3haCheck;
    }
    private JCheckBox getIndirektrwCheck() {
        if (indirektrwCheck == null) {
            indirektrwCheck = new JCheckBox("Indirekt RW");
        }
        return indirektrwCheck;
    }
    private JCheckBox getIndirektswCheck() {
        if (indirektswCheck == null) {
            indirektswCheck = new JCheckBox("Indirekt SW");
        }
        return indirektswCheck;
    }
    private JCheckBox getKeineAngabenCheck() {
        if (keineAngabenCheck == null) {
            keineAngabenCheck = new JCheckBox("Keine Angaben");
        }
        return keineAngabenCheck;
    }
    private JCheckBox getSanierungErfolgtCheck() {
        if (sanierungErfolgtCheck == null) {
            sanierungErfolgtCheck = new JCheckBox("Sanierung erfolgt");
        }
        return sanierungErfolgtCheck;
    }
    private JCheckBox getSanierungskonzeptCheck() {
        if (sanierungskonzeptCheck == null) {
            sanierungskonzeptCheck = new JCheckBox("Sanierungskonzept");
        }
        return sanierungskonzeptCheck;
    }
    private JCheckBox getSuevkanPflichtCheck() {
        if (suevkanPflichtCheck == null) {
            suevkanPflichtCheck = new JCheckBox("SuevKan pflichtig");
        }
        return suevkanPflichtCheck;
    }
    private JFormattedTextField getVersFlaecheFeld() {
        if (versFlaecheFeld == null) {
            versFlaecheFeld = new IntegerField();
        }
        return versFlaecheFeld;
    }
    private JButton getSaveSuevButton() {
        if (saveSuevButton == null) {
            saveSuevButton = new JButton("Speichern");

            saveSuevButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveSuevDaten()) {
                        hauptModul.getFrame().changeStatus("SuevKan Verfahren "+fachdaten.getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
                    } else {
                        hauptModul.getFrame().changeStatus("Fehler beim Speichern des SuevKan Verfahrens!", HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }
        return saveSuevButton;
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
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            if ((e.getClickCount() == 2)
                                    && (e.getButton() == 1)) {
                                Point origin = e.getPoint();
                                int row = getObjektverknuepungTabelle()
                                        .rowAtPoint(origin);

                                if (row != -1) {
                                    BasisObjektverknuepfung obj = objektVerknuepfungModel
                                            .getRow(row);
                                    if (obj.getBasisObjektByIstVerknuepftMit().getObjektid().intValue() != hauptModul
                                            .getObjekt().getObjektid().intValue())
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

                        public void mousePressed(MouseEvent e) {
                            showVerknuepfungPopup(e);
                        }

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
                                log.debug("(BasisBetreiberSuchen.removeAction) "
                                		+ "Objekt "
                                        + verknuepfung.getId()
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

