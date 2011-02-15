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
 * Created on 21.04.2005 by u633d
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhBwkFachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "BWK"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class BWKPanel extends JPanel {
    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JTextField herstellerFeld = null;
    private JTextField typFeld = null;
    private JTextField brennmittelFeld = null;
    private JFormattedTextField leistungFeld = null;
    private JTextField brennerFeld = null;
    private JTextField waermetauscherFeld = null;
    private JTextField abgasleitungFeld = null;
    private JTextField kondensatltgFeld = null;
    private JFormattedTextField jahrgangFeld = null;
    private JTextField abnahmeFeld = null;
    private TextFieldDateChooser anschreibenFeld = null;
    private TextFieldDateChooser genehmigungDatum = null;
    private JCheckBox abaCheck = null;
    private JCheckBox genehmpflichtCheck = null;
    private JTextArea bwkBeschreibungsArea = null;
    private JButton saveBwkButton = null;


    // Daten
    private AnhBwkFachdaten bwk = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;


    public BWKPanel(BasisObjektBearbeiten hauptModul) {
        name = "Brennwertkessel";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout (
                "r:50dlu, 5dlu, 90dlu, 10dlu, r:50dlu, 5dlu, 70dlu, , 70dlu, 70dlu", // Spalten
                "pref, " +    //1
                "3dlu, " +    //2
                "pref, " +    //3
                "3dlu, " +    //4
                "pref, " +    //5
                "3dlu, " +    //6
                "pref, " +    //7
                "3dlu, " +    //8
                "pref, " +    //9
                "3dlu, " +    //10
                "pref, " +  //11
                "3dlu, " +    //12
                "pref, " +    //13
                "3dlu, " +    //14
                "pref, " +  //15
                "3dlu, " +  //16
                "pref, " +  //17
                "3dlu, " +  //18
                "pref, " +  //19
                "3dlu, " +  //20
                "pref, " +  //21
                "3dlu, " +  //22
                "fill:100dlu, " +  //23
                "3dlu, " +  //24
                "pref");     //25

        PanelBuilder builder = new PanelBuilder(layout, this);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        //linke Seite
        builder.addSeparator("Kessel", cc.xyw( 1, 1, 3));
        builder.addLabel("Hersteller:", cc.xy( 1, 3));
        builder.add(getHerstellerFeld(), cc.xy( 3, 3));
        builder.addLabel("Typ:", cc.xy( 1, 5));
        builder.add(getTypFeld(), cc.xy( 3, 5));
        builder.addLabel("Brennmittel:", cc.xy( 1, 7));
        builder.add(getBrennmittelFeld(), cc.xy( 3, 7));
        builder.addLabel("Leistung:", cc.xy( 1, 9));
        builder.add(getLeistungFeld(), cc.xy( 3, 9));
        builder.addSeparator("Werkstoffe", cc.xyw( 1, 11, 3));
        builder.addLabel("Brenner:", cc.xy( 1, 13));
        builder.add(getBrennerFeld(), cc.xy( 3, 13));
        builder.addLabel("Tauscher:", cc.xy( 1, 15));
        builder.add(getWaermetauscherFeld(), cc.xy( 3, 15));
        builder.addLabel("Abgasleitung:", cc.xy( 1, 17));
        builder.add(getAbgasleitungFeld(), cc.xy( 3, 17));
        builder.addLabel("Kondensatabl.:", cc.xy( 1, 19));
        builder.add(getKondensatltgFeld(), cc.xy( 3, 19));

        //rechte Seite
        builder.addSeparator("Erfassung", cc.xyw( 5, 1, 3));
        builder.addLabel("Jahrgang:", cc.xy( 5, 3));
        builder.add(getJahrgangFeld(), cc.xy( 7, 3));
        builder.addLabel("Abnahme:", cc.xy( 5, 5));
        builder.add(getAbnahmeFeld(), cc.xy( 7, 5));
        builder.addLabel("Anschreiben:", cc.xy( 5, 7));
        builder.add(getAnschreibenFeld(), cc.xy( 7, 7));
        builder.addLabel("Genehmigung:", cc.xy( 5, 9));
        builder.add(getGenehmigungDatum(), cc.xy( 7, 9));
        //builder.addLabel("ABA:", cc.xy( 5, 11));
        builder.add(getgenehmpflichtCheck(), cc.xyw( 5, 11, 3));
        builder.add(getAbaCheck(), cc.xy( 8, 11));
        builder.addSeparator("Bemerkungen", cc.xyw( 5, 13, 3));
        builder.add(new JScrollPane(getBwkBeschreibungsArea(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), cc.xywh( 5, 15, 3, 5));

        builder.addSeparator("Verknüpfte Objekte", cc.xyw( 1, 21, 7));
        JScrollPane objektverknuepfungScroller = new JScrollPane(
                getObjektverknuepungTabelle(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.add(objektverknuepfungScroller, cc.xyw( 1, 23, 7));
        builder.nextLine();

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(getSelectObjektButton(), getSaveBwkButton());
        builder.add(buttonBar, cc.xyw( 1, 25, 7));


    }

    public void fetchFormData() throws RuntimeException {
        bwk = AnhBwkFachdaten.getAnhBwkByObjekt(hauptModul.getObjekt());
        AUIKataster.debugOutput("Brennwertkessel aus DB geholt: " + bwk, "BWKPanel.fetchFormData");
    }


    public void updateForm() throws RuntimeException {

        if (bwk != null) {
            if (bwk.getKHersteller() != null) {
                getHerstellerFeld().setText(bwk.getKHersteller());
            }
            if (bwk.getKTyp() != null) {
                getTypFeld().setText(bwk.getKTyp());
            }
            if (bwk.getKBrennmittel() != null) {
                getBrennmittelFeld().setText(bwk.getKBrennmittel());
            }
            if (bwk.getKLeistung() != null) {
                getLeistungFeld().setText(bwk.getKLeistung().toString());
            }
            if (bwk.getWBrenner() != null) {
                getBrennerFeld().setText(bwk.getWBrenner());
            }
            if (bwk.getWWaermetauscher() != null) {
                getWaermetauscherFeld().setText(bwk.getWWaermetauscher());
            }
            if (bwk.getWAbgasleitung() != null) {
                getAbgasleitungFeld().setText(bwk.getWAbgasleitung());
            }
            if (bwk.getWKondensableitung() != null) {
                getKondensatltgFeld().setText(bwk.getWKondensableitung());
            }
            if (bwk.getErfassung() != null) {
                getJahrgangFeld().setText(bwk.getErfassung().toString());
            }
            if (bwk.getBemerkungen() != null) {
                getBwkBeschreibungsArea().setText(bwk.getBemerkungen());
            }
            if (bwk.getAbnahme() != null) {
                getAbnahmeFeld().setText(bwk.getAbnahme());
            }
            if (bwk.getAnschreiben() != null) {
                getAnschreibenFeld().setDate(bwk.getAnschreiben());
            }
            if (bwk.getDatumG() != null) {
                getGenehmigungDatum().setDate(bwk.getDatumG());
            }
            if (bwk.getAba() != null) {
                if (bwk.getAba().booleanValue() == true) {
                    getAbaCheck().setSelected(true);
                }
                else {
                    getAbaCheck().setSelected(false);
                }
            }
            if (bwk.getGenehmigungspflicht() != null) {
                if (bwk.getGenehmigungspflicht().booleanValue() == true) {
                    getgenehmpflichtCheck().setSelected(true);
                }
                else {
                    getgenehmpflichtCheck().setSelected(false);
                }
            }
            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());
        }

        }


    public void clearForm() {
        getHerstellerFeld().setText(null);
        getTypFeld().setText(null);
        getBrennmittelFeld().setText(null);
        getLeistungFeld().setText(null);
        getBrennerFeld().setText(null);
        getWaermetauscherFeld().setText(null);
        getAbgasleitungFeld().setText(null);
        getKondensatltgFeld().setText(null);
        getJahrgangFeld().setText(null);
        getBwkBeschreibungsArea().setText(null);
        getAbnahmeFeld().setText(null);
        getAnschreibenFeld().setDate(null);
        getGenehmigungDatum().setDate(null);
        getAbaCheck().setSelected(false);
        getgenehmpflichtCheck().setSelected(false);

    }

    public void enableAll(boolean enabled) {
        getAbaCheck().setEnabled(enabled);
        getgenehmpflichtCheck().setEnabled(enabled);
        getAbgasleitungFeld().setEnabled(enabled);
        getAbnahmeFeld().setEnabled(enabled);
        getAnschreibenFeld().setEnabled(enabled);
        getBrennerFeld().setEnabled(enabled);
        getBrennmittelFeld().setEnabled(enabled);
        getBwkBeschreibungsArea().setEnabled(enabled);
        getGenehmigungDatum().setEnabled(enabled);
        getHerstellerFeld().setEnabled(enabled);
        getJahrgangFeld().setEnabled(enabled);
        getKondensatltgFeld().setEnabled(enabled);
        getLeistungFeld().setEnabled(enabled);
        getTypFeld().setEnabled(enabled);
        getWaermetauscherFeld().setEnabled(enabled);
        getSaveBwkButton().setEnabled(enabled);
    }


    public String getName() {
        return name;
    }

    private boolean saveBwkDaten() {
        boolean success;

        String hersteller = herstellerFeld.getText();
        if ("".equals(hersteller)) {
            bwk.setKHersteller(null);
        } else {
            bwk.setKHersteller(hersteller);
        }

        String typ = typFeld.getText();
        if ("".equals(typ)) {
            bwk.setKTyp(null);
        } else {
            bwk.setKTyp(typ);
        }

        String brennmittel = brennmittelFeld.getText();
        if ("".equals(brennmittel)) {
            bwk.setKBrennmittel(null);
        } else {
            bwk.setKBrennmittel(brennmittel);
        }

        Integer leistung = ((IntegerField)leistungFeld).getIntValue();
        bwk.setKLeistung(leistung);

        String brenner = brennerFeld.getText();
        if ("".equals(brenner)) {
            bwk.setWBrenner(null);
        } else {
            bwk.setWBrenner(brenner);
        }

        String tauscher = waermetauscherFeld.getText();
        if ("".equals(tauscher)) {
            bwk.setWWaermetauscher(null);
        } else {
            bwk.setWWaermetauscher(tauscher);
        }

        String abgasleitung = abgasleitungFeld.getText();
        if ("".equals(abgasleitung)) {
            bwk.setWAbgasleitung(null);
        } else {
            bwk.setWAbgasleitung(abgasleitung);
        }

        String kondensatabl = kondensatltgFeld.getText();
        if ("".equals(kondensatabl)) {
            bwk.setWKondensableitung(null);
        } else {
            bwk.setWKondensableitung(kondensatabl);
        }

        Integer jahrgang = ((IntegerField)jahrgangFeld).getIntValue();
        bwk.setErfassung(jahrgang);

        String abnahme = abnahmeFeld.getText();
        if ("".equals(abnahme)) {
            bwk.setAbnahme(null);
        } else {
            bwk.setAbnahme(abnahme);
        }

        Date anschreiben = anschreibenFeld.getDate();
        if ("".equals(anschreiben)) {
            bwk.setAnschreiben(null);
        } else {
            bwk.setAnschreiben(anschreiben);
        }

        Date genehmigung = genehmigungDatum.getDate();
        bwk.setDatumG(genehmigung);


        Boolean aba;
        if (getAbaCheck().isSelected())  {
            aba = true;
        } else {
            aba = false;
        }
        bwk.setAba(new Boolean(aba));

        Boolean genehmpflicht;
        if (getgenehmpflichtCheck().isSelected())  {
            genehmpflicht = true;
        } else {
            genehmpflicht = false;
        }
        bwk.setGenehmigungspflicht(new Boolean(genehmpflicht));

        String beschreibung = bwkBeschreibungsArea.getText();
        if ("".equals(beschreibung)) {
            bwk.setBemerkungen(null);
        } else {
            bwk.setBemerkungen(beschreibung);
        }


        success = AnhBwkFachdaten.saveBwk(bwk);

        if (success) {
            AUIKataster.debugOutput("Brennwertkessel " + bwk + " gespeichert.",
                    "BWKPanel.saveBwkDaten");
        } else {
            AUIKataster.debugOutput("Brennwertkessel " + bwk
                    + " konnte nicht gespeichert werden!",
            "BWKPanel.saveBwkDaten");
        }

        return success;
    }

    public void completeObjekt() {
        if (hauptModul.isNew() || bwk == null) {
            // Neuen Brennwertkessel erzeugen
            bwk = new AnhBwkFachdaten();
            // Objekt_Id setzen
            bwk.setBasisObjekt(hauptModul.getObjekt());

            // Brennwertkessel speichern
            if (AnhBwkFachdaten.saveBwk(bwk)) {
                AUIKataster.debugOutput("Neuer Brennwertkessel "+bwk+" gespeichert.", "BasisObjektBearbeiten.completeObjekt");
            }
        }
    }


    private JCheckBox getAbaCheck() {
        if (abaCheck == null) {
            abaCheck = new JCheckBox("ABA");
        }
        return abaCheck;
    }
    private JCheckBox getgenehmpflichtCheck() {
        if (genehmpflichtCheck == null) {
            genehmpflichtCheck = new JCheckBox("Genehmigungspflicht");
        }
        return genehmpflichtCheck;
    }
    private JTextField getAbgasleitungFeld() {
        if (abgasleitungFeld == null) {
            abgasleitungFeld = new LimitedTextField(50);
        }
        return abgasleitungFeld;
    }
    private JTextField getAbnahmeFeld() {
        if (abnahmeFeld == null) {
            abnahmeFeld = new LimitedTextField(50);
        }
        return abnahmeFeld;
    }
    private TextFieldDateChooser getAnschreibenFeld() {
        if (anschreibenFeld == null) {
            anschreibenFeld = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return anschreibenFeld;
    }
    private JTextField getBrennerFeld() {
        if (brennerFeld == null) {
            brennerFeld = new LimitedTextField(50);
        }
        return brennerFeld;
    }
    private JTextField getBrennmittelFeld() {
        if (brennmittelFeld == null) {
            brennmittelFeld = new LimitedTextField(50);
        }
        return brennmittelFeld;
    }
    private JTextArea getBwkBeschreibungsArea() {
        if (bwkBeschreibungsArea == null) {
            bwkBeschreibungsArea = new LimitedTextArea(150);
            bwkBeschreibungsArea.setLineWrap(true);
            bwkBeschreibungsArea.setWrapStyleWord(true);
        }
        return bwkBeschreibungsArea;
    }
    private TextFieldDateChooser getGenehmigungDatum() {
        if (genehmigungDatum == null) {
            genehmigungDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return genehmigungDatum;
    }
    private JTextField getHerstellerFeld() {
        if (herstellerFeld == null) {
            herstellerFeld = new LimitedTextField(50);
        }
        return herstellerFeld;
    }
    private JTextField getJahrgangFeld() {
        if (jahrgangFeld == null) {
            jahrgangFeld = new IntegerField();
        }
        return jahrgangFeld;
    }
    private JTextField getKondensatltgFeld() {
        if (kondensatltgFeld == null) {
            kondensatltgFeld = new LimitedTextField(50);
        }
        return kondensatltgFeld;
    }
    private JTextField getLeistungFeld() {
        if (leistungFeld == null) {
            leistungFeld = new IntegerField();
        }
        return leistungFeld;
    }
    private JTextField getTypFeld() {
        if (typFeld == null) {
            typFeld = new LimitedTextField(50);
        }
        return typFeld;
    }
    private JTextField getWaermetauscherFeld() {
        if (waermetauscherFeld == null) {
            waermetauscherFeld = new LimitedTextField(50);
        }
        return waermetauscherFeld;
    }
    private JButton getSaveBwkButton() {
        if (saveBwkButton == null) {
            saveBwkButton = new JButton("Speichern");

            saveBwkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveBwkDaten()) {
                        hauptModul.getFrame().changeStatus("Brennwertkessel "+bwk.getBwkId()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
                    } else {
                        hauptModul.getFrame().changeStatus("Fehler beim Speichern des Brennwertkessels!", HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }
        return saveBwkButton;
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
                                AUIKataster.debugOutput("Objekt "
                                        + verknuepfung.getId()
                                        + " wurde gelöscht!",
                                        "BasisBetreiberSuchen.removeAction");
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
                            .getFrame(), bwk.getBasisObjekt(),
                            objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return selectObjektButton;
    }
}
