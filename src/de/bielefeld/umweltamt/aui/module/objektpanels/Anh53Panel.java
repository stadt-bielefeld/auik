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
 * Created on 27.04.2005 by u633d
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh53Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Fotografische Prozesse"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class Anh53Panel extends JPanel {
    private static final long serialVersionUID = 1236417083478221057L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JTextField brancheFeld = null;
    private IntegerField durchsatzFeld = null;
    private IntegerField gesamtmengeFeld = null;
    private IntegerField spuelwasserFeld = null;
    private JCheckBox onlinesilberCheck = null;
    private JCheckBox abwasserCheck = null;
    private JCheckBox abgemeldetCheck = null;
    private JCheckBox tagebuchCheck = null;
    private JCheckBox wasseruhrCheck = null;
    private JCheckBox wartungsvertragCheck = null;
    private JCheckBox grgenCheck = null;
    private JCheckBox bagatellCheck = null;
    private TextFieldDateChooser bagatellDatum = null;

    private JTextArea anh53BemerkungArea = null;

    private JButton saveAnh53Button = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;



    // Daten
    private Anh53Fachdaten fachdaten = null;


    public Anh53Panel(BasisObjektBearbeiten hauptModul) {
        name = "Fotografische Prozesse";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout (
                "r:100dlu, 5dlu, 80dlu, 5dlu, r:65dlu, 5dlu, 100dlu", // Spalten
                "");


        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.appendSeparator("Fachdaten");
        builder.append("Branche:", getBrancheFeld());
        builder.append("Durchsatz:", getDurchsatzFeld());
        builder.nextLine();
        builder.append("Gesamtmenge:", getGesamtmengeFeld());
        builder.append("Spülwasser:", getSpuelwasserFeld());
        builder.nextLine();
        builder.append("Bagatelle seit:", getBagatellDatum());
        builder.append("", getBagatellCheck());
        builder.nextLine();
        builder.append("", getAbwasserCheck());
        builder.append("", getOnlinesilberCheck());
        builder.nextLine();
        builder.append("", getAbgemeldetCheck());
        builder.append("", getTagebuchCheck());
        builder.nextLine();
        builder.append("", getWasseruhrCheck());
        builder.append("", getWartungCheck());
        builder.nextLine();
        builder.append("", getGrgenCheck());
        builder.nextLine();

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getAnh53BemerkungArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);

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

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(getSelectObjektButton(), getSaveAnh53Button());
        builder.append(buttonBar, 7);
    }

    public void fetchFormData() throws RuntimeException {
        fachdaten = Anh53Fachdaten.getAnh53ByObjekt(hauptModul.getObjekt());
        log.debug("Objekt aus DB geholt: " + fachdaten);
    }


    public void updateForm() throws RuntimeException {

        if (fachdaten != null) {
            if (fachdaten.getBemerkungen() != null) {
                getAnh53BemerkungArea().setText(fachdaten.getBemerkungen());
            }
            if (fachdaten.getBranche() != null) {
                getBrancheFeld().setText(fachdaten.getBranche());
            }
            if (fachdaten.getDurchsatz() != null) {
                getDurchsatzFeld().setValue(fachdaten.getDurchsatz());
            }
            if (fachdaten.getGesamtmengeEb() != null) {
                getGesamtmengeFeld().setValue(fachdaten.getGesamtmengeEb());
            }
            if (fachdaten.getSpuelwassermenge() != null) {
                getSpuelwasserFeld().setValue(fachdaten.getSpuelwassermenge());
            }
            if (fachdaten.getBagatell() != null) {
                if (fachdaten.getBagatell() == true) {
                    getBagatellCheck().setSelected(true);
                } else {
                    getBagatellCheck().setSelected(false);
                }
            }
            if (fachdaten.getAbwasser() != null) {
                if (fachdaten.getAbwasser() == true) {
                    getAbwasserCheck().setSelected(true);
                } else {
                    getAbwasserCheck().setSelected(false);
                }
            }
            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());
        }

    }


    public void clearForm() {

        getBrancheFeld().setText(null);
        getDurchsatzFeld().setText(null);
        getGesamtmengeFeld().setText(null);
        getSpuelwasserFeld().setText(null);
        getAnh53BemerkungArea().setText(null);
        getBagatellDatum().setDate(null);
        getBagatellCheck().setSelected(false);
        getAbwasserCheck().setSelected(false);
        getOnlinesilberCheck().setSelected(false);
        getAbgemeldetCheck().setSelected(false);
        getTagebuchCheck().setSelected(false);
        getWasseruhrCheck().setSelected(false);
        getWartungCheck().setSelected(false);
        getGrgenCheck().setSelected(false);

    }

    public void enableAll(boolean enabled) {

    }


    @Override
    public String getName() {
        return name;
    }

    private boolean saveAnh53Daten() {
        boolean success;

        String bemerkungen = anh53BemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            fachdaten.setBemerkungen(null);
        } else {
            fachdaten.setBemerkungen(bemerkungen);
        }

        success = Anh53Fachdaten.saveFachdaten(fachdaten);
        if (success) {
            log.debug("Objekt "
            		+ fachdaten.getBasisObjekt().getBasisBetreiber().getBetrname()
            		+ " gespeichert.");
        } else {
            log.debug("Objekt " + fachdaten
                    + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (hauptModul.isNew() || fachdaten == null) {
            // Neues Objekt erzeugen
            fachdaten = new Anh53Fachdaten();
            // Objekt_Id setzen
            fachdaten.setBasisObjekt(hauptModul.getObjekt());

            // Objekt speichern
            Anh53Fachdaten.saveFachdaten(fachdaten);
            log.debug("Neues Objekt " + fachdaten + " gespeichert.");
        }
    }

    private JTextArea getAnh53BemerkungArea() {
        if (anh53BemerkungArea == null) {
            anh53BemerkungArea = new LimitedTextArea(255);
            anh53BemerkungArea.setLineWrap(true);
            anh53BemerkungArea.setWrapStyleWord(true);
        }
        return anh53BemerkungArea;
    }
    private JCheckBox getBagatellCheck() {
        if (bagatellCheck == null) {
            bagatellCheck = new JCheckBox("Bagatelle");
        }
        return bagatellCheck;
    }
    private JCheckBox getOnlinesilberCheck() {
        if (onlinesilberCheck == null) {
            onlinesilberCheck = new JCheckBox("Online Silber");
        }
        return onlinesilberCheck;
    }
    private JCheckBox getAbwasserCheck() {
        if (abwasserCheck == null) {
            abwasserCheck = new JCheckBox("Abwasser");
        }
        return abwasserCheck;
    }
    private JCheckBox getAbgemeldetCheck() {
        if (abgemeldetCheck == null) {
            abgemeldetCheck = new JCheckBox("Abgemeldet");
        }
        return abgemeldetCheck;
    }
    private JCheckBox getTagebuchCheck() {
        if (tagebuchCheck == null) {
            tagebuchCheck = new JCheckBox("Tagebuch");
        }
        return tagebuchCheck;
    }
    private JCheckBox getWasseruhrCheck() {
        if (wasseruhrCheck == null) {
            wasseruhrCheck = new JCheckBox("Wasseruhr");
        }
        return wasseruhrCheck;
    }
    private JCheckBox getWartungCheck() {
        if (wartungsvertragCheck == null) {
            wartungsvertragCheck = new JCheckBox("Wartungsvertrag");
        }
        return wartungsvertragCheck;
    }
    private JCheckBox getGrgenCheck() {
        if (grgenCheck == null) {
            grgenCheck = new JCheckBox("große Genehmigung");
        }
        return grgenCheck;
    }
    private TextFieldDateChooser getBagatellDatum() {
        if (bagatellDatum == null) {
            bagatellDatum = new TextFieldDateChooser();
        }
        return bagatellDatum;
    }
    private JTextField getBrancheFeld() {
        if (brancheFeld == null) {
            brancheFeld = new LimitedTextField(50);
        }
        return brancheFeld;
    }
    private IntegerField getDurchsatzFeld() {
        if (durchsatzFeld == null) {
            durchsatzFeld = new IntegerField();
        }
        return durchsatzFeld;
    }

    private IntegerField getGesamtmengeFeld() {
        if (gesamtmengeFeld == null) {
            gesamtmengeFeld = new IntegerField();
        }
        return gesamtmengeFeld;
    }

    private IntegerField getSpuelwasserFeld() {
        if (spuelwasserFeld == null) {
            spuelwasserFeld = new IntegerField();
        }
        return spuelwasserFeld;
    }

    private JButton getSaveAnh53Button() {
        if (saveAnh53Button == null) {
            saveAnh53Button = new JButton("Speichern");

            saveAnh53Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh53Daten()) {
                        hauptModul.getFrame().changeStatus(
                                "Objekt " + fachdaten.getObjektid()
                                        + " erfolgreich gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                    } else {
                        hauptModul.getFrame().changeStatus(
                                "Fehler beim Speichern des Objektes!",
                                HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }
        return saveAnh53Button;
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
                private static final long serialVersionUID = -4593617315019377189L;

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

