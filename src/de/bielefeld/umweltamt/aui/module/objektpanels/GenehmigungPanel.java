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
 * Created on 29.06.2005 by u633d
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
import javax.swing.KeyStroke;



import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.IndeinlGenehmigung;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Genehmigung"-Tab des BasisObjektBearbeiten-Moduls
 *
 * @author Gerd Genuit
 */
public class GenehmigungPanel extends JPanel {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets

    private JTextArea genBemerkungArea = null;
    private TextFieldDateChooser antragsDatum = null;
    private TextFieldDateChooser genehmigungsDatum = null;
    private TextFieldDateChooser aenderungsDatum = null;
    private TextFieldDateChooser befristetbisDatum = null;
    private JFormattedTextField anhangFeld = null;
    private JFormattedTextField genMengeFeld = null;
    private JCheckBox befristetCheck = null;
    private JCheckBox gen58Check = null;
    private JCheckBox gen59Check = null;
    private JCheckBox selbstueberwCheck = null;
    private JCheckBox eSatzungCheck = null;

    private JButton saveGenehmigungButton = null;

    // Daten

    private IndeinlGenehmigung fachdaten = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;


    public GenehmigungPanel(BasisObjektBearbeiten hauptModul) {
        name = "Genehmigung";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
                "r:80dlu, 5dlu, 80dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
                "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.appendSeparator("Fachdaten");
        builder.append("Antragsdatum:", getAntragsDatum());
        builder.append("", getgen58CheckBox());
        builder.nextLine();
        builder.append("Genehmigungsdatum:", getGenehmigungsDatum());
        builder.append("", getgen59CheckBox());
        builder.nextLine();
        builder.append("Änderungsdatum:", getAenderungsDatum());
        builder.append("", getselbCheckBox());
        builder.nextLine();
        builder.append("Anhang:", getAnhangFeld());
        builder.append("", getesaCheckBox());
        builder.nextLine();
        builder.append("Genehmigte Menge [m³]:", getGenMengeFeld());
        builder.append("", getbefCheckBox());
        builder.nextLine();
        builder.append("");
        builder.append("");
        builder.append("bis:", getBefristetDatum());
        builder.nextLine();
        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(
                getGenBemerkungArea(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);
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
                getSelectObjektButton(), getsaveGenehmigungButton());

        builder.append(buttonBar, 7);
    }

    public void fetchFormData() throws RuntimeException {
        fachdaten = IndeinlGenehmigung.getGenByObjekt(hauptModul.getObjekt());
        log.debug("Genehmigung Objekt aus DB geholt: ID" + fachdaten);
    }

    public void updateForm() throws RuntimeException {

        if (fachdaten != null) {

            if (fachdaten.getBemerkungen() != null) {
                getGenBemerkungArea().setText(fachdaten.getBemerkungen());
            }
            if (fachdaten.getAnhang() != null) {
                getAnhangFeld().setText(
                        fachdaten.getAnhang().toString());
            }
            if (fachdaten.getGenMenge() != null) {
                getGenMengeFeld().setText(
                        fachdaten.getGenMenge().toString());
            }
            if (fachdaten.getAntragDatum() != null) {
                getAntragsDatum().setDate(fachdaten.getAntragDatum());
            }
            if (fachdaten.getErstellungsDatum() != null) {
                getGenehmigungsDatum().setDate(fachdaten.getErstellungsDatum());
            }
            if (fachdaten.getAenderungsDatum() != null) {
                getAenderungsDatum().setDate(fachdaten.getAenderungsDatum());
            }
            if (fachdaten.getBefristetBis() != null) {
                getBefristetDatum().setDate(fachdaten.getBefristetBis());
            }
            if (fachdaten.getBefristet() != null) {
                if (fachdaten.getBefristet() == true) {
                    getbefCheckBox().setSelected(true);
                } else {
                    getbefCheckBox().setSelected(false);
                }
            }
            if (fachdaten.getGen58() != null) {
                if (fachdaten.getGen58() == true) {
                    getgen58CheckBox().setSelected(true);
                } else {
                    getgen58CheckBox().setSelected(false);
                }
            }
            if (fachdaten.getGen59() != null) {
                if (fachdaten.getGen59() == true) {
                    getgen59CheckBox().setSelected(true);
                } else {
                    getgen59CheckBox().setSelected(false);
                }
            }
            if (fachdaten.getSelbstueberw() != null) {
                if (fachdaten.getSelbstueberw() == true) {
                    getselbCheckBox().setSelected(true);
                } else {
                    getselbCheckBox().setSelected(false);
                }
            }
            if (fachdaten.getEsatzung() != null) {
                if (fachdaten.getEsatzung() == true) {
                    getesaCheckBox().setSelected(true);
                } else {
                    getesaCheckBox().setSelected(false);
                }
            }
            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());

        }

    }

    public void clearForm() {

        getGenBemerkungArea().setText(null);
        getAnhangFeld().setText(null);
        getGenMengeFeld().setText(null);
        getAntragsDatum().setDate(null);
        getAenderungsDatum().setDate(null);
        getGenehmigungsDatum().setDate(null);
        getBefristetDatum().setDate(null);
        getbefCheckBox().setSelected(false);
        getgen58CheckBox().setSelected(false);
        getgen59CheckBox().setSelected(false);
        getselbCheckBox().setSelected(false);
        getesaCheckBox().setSelected(false);
    }

    public void enableAll(boolean enabled) {

        getGenBemerkungArea().setEnabled(enabled);
        getAnhangFeld().setEnabled(enabled);
        getGenMengeFeld().setEnabled(enabled);
        getAntragsDatum().setEnabled(enabled);
        getAenderungsDatum().setEnabled(enabled);
        getGenehmigungsDatum().setEnabled(enabled);
        getBefristetDatum().setEnabled(enabled);
        getbefCheckBox().setEnabled(enabled);
        getgen58CheckBox().setEnabled(enabled);
        getgen59CheckBox().setEnabled(enabled);
        getselbCheckBox().setEnabled(enabled);
        getesaCheckBox().setEnabled(enabled);

    }

    private boolean saveGenehmigungDaten() {
        boolean success;


        String bemerkungen = genBemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            fachdaten.setBemerkungen(null);
        } else {
            fachdaten.setBemerkungen(bemerkungen);
        }

        Date antrag = antragsDatum.getDate();
        fachdaten.setAntragDatum(antrag);

        Date aend = aenderungsDatum.getDate();
        fachdaten.setAenderungsDatum(aend);

        Date erst = genehmigungsDatum.getDate();
        fachdaten.setErstellungsDatum(erst);

        Date befr = befristetbisDatum.getDate();
        fachdaten.setBefristetBis(befr);

        Integer anhang = ((IntegerField) anhangFeld).getIntValue();
        fachdaten.setAnhang(anhang);

        Integer menge = ((IntegerField) genMengeFeld).getIntValue();
        fachdaten.setGenMenge(menge);

        if (getbefCheckBox().isSelected()) {
            fachdaten.setBefristet(true);
        } else {
            fachdaten.setBefristet(false);
        }

        if (getgen58CheckBox().isSelected()) {
            fachdaten.setGen58(true);
        } else {
            fachdaten.setGen58(false);
        }

        if (getgen59CheckBox().isSelected()) {
            fachdaten.setGen59(true);
        } else {
            fachdaten.setGen59(false);
        }

        if (getselbCheckBox().isSelected()) {
            fachdaten.setSelbstueberw(true);
        } else {
            fachdaten.setSelbstueberw(false);
        }

        if (getesaCheckBox().isSelected()) {
            fachdaten.setEsatzung(true);
        } else {
            fachdaten.setEsatzung(false);
        }

        success = IndeinlGenehmigung.saveFachdaten(fachdaten);
        if (success) {
            log.debug("Uebergabestelle Objekt " + fachdaten.getObjektid()
                    + " gespeichert.");
        } else {
            log.debug("Uebergabestelle Objekt " + fachdaten
                    + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (hauptModul.isNew() || fachdaten == null) {
            // Neues Genehmigung Objekt erzeugen
            fachdaten = new IndeinlGenehmigung();
            // Objekt_Id setzen
            fachdaten.setBasisObjekt(hauptModul.getObjekt());

            // Uebergabestelle speichern
            IndeinlGenehmigung.saveFachdaten(fachdaten);
            log.debug("Neues Genehmigung Objekt " + fachdaten
            		+ " gespeichert.");
        }
    }

    private JButton getsaveGenehmigungButton() {
        if (saveGenehmigungButton == null) {
            saveGenehmigungButton = new JButton("Speichern");

            saveGenehmigungButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveGenehmigungDaten()) {
                        hauptModul.getFrame().changeStatus(
                                "Genehmigung " + fachdaten.getObjektid()
                                        + " erfolgreich gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                    } else {
                        hauptModul.getFrame().changeStatus(
                                "Fehler beim Speichern des Uebergabestelle Objekt!",
                                HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }
        return saveGenehmigungButton;
    }

    public String getName() {
        return name;
    }

    private TextFieldDateChooser getAntragsDatum() {
        if (antragsDatum == null) {
            antragsDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return antragsDatum;
    }

    private TextFieldDateChooser getAenderungsDatum() {
        if (aenderungsDatum == null) {
            aenderungsDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return aenderungsDatum;
    }

    private TextFieldDateChooser getGenehmigungsDatum() {
        if (genehmigungsDatum == null) {
            genehmigungsDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return genehmigungsDatum;
    }

    private TextFieldDateChooser getBefristetDatum() {
        if (befristetbisDatum == null) {
            befristetbisDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return befristetbisDatum;
    }

    private JFormattedTextField getAnhangFeld() {
        if (anhangFeld == null) {
            anhangFeld = new IntegerField();
        }
        return anhangFeld;
    }

    private JFormattedTextField getGenMengeFeld() {
        if (genMengeFeld == null) {
            genMengeFeld = new IntegerField();
        }
        return genMengeFeld;
    }

    private JCheckBox getbefCheckBox() {
        if (befristetCheck == null) {
            befristetCheck = new JCheckBox("Genehmigung befristet");
        }
        return befristetCheck;
    }

    private JCheckBox getgen58CheckBox() {
        if (gen58Check == null) {
            gen58Check = new JCheckBox("58er Genehmigung");
        }
        return gen58Check;
    }

    private JCheckBox getgen59CheckBox() {
        if (gen59Check == null) {
            gen59Check = new JCheckBox("59er Genehmigung");
        }
        return gen59Check;
    }

    private JCheckBox getselbCheckBox() {
        if (selbstueberwCheck == null) {
            selbstueberwCheck = new JCheckBox("Selbstüberwachung");
        }
        return selbstueberwCheck;
    }

    private JCheckBox getesaCheckBox() {
        if (eSatzungCheck == null) {
            eSatzungCheck = new JCheckBox("E-Satzungsüberwachung");
        }
        return eSatzungCheck;
    }

    private JTextArea getGenBemerkungArea() {
        if (genBemerkungArea == null) {
            genBemerkungArea = new LimitedTextArea(150);
            genBemerkungArea.setLineWrap(true);
            genBemerkungArea.setWrapStyleWord(true);
        }
        return genBemerkungArea;
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
