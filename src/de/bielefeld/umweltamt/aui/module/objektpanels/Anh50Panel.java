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
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhEntsorger;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.editors.EntsorgerEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Zahnarzt"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class Anh50Panel extends JPanel {
	/** Logging */
    private static final Logger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JTextField gefaehrdungsklasseFeld = null;
    private JCheckBox erloschenCheck = null;
    private JTextArea anh50BemerkungArea = null;
    private TextFieldDateChooser antragDatum = null;
    private TextFieldDateChooser genehmigungDatum = null;
    private TextFieldDateChooser wiedervorlageDatum = null;
    private JToolBar entsorBearbToolBar = null;
    private JButton entsorgBearbButton = null;
    private JButton saveAnh50Button = null;
    private JComboBox entsorgerBox = null;



    // Daten
    private Anh50Fachdaten fachdaten = null;
    private AnhEntsorger[] entsorg = null;

    //Listener
    private ActionListener editButtonListener;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;


    public Anh50Panel(BasisObjektBearbeiten hauptModul) {
        name = "Zahnarzt";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout (
                "r:70dlu, 5dlu, 90dlu, r:90dlu, 5dlu, 20dlu", // Spalten
                "");


        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.appendSeparator("Fachdaten");
        builder.append("Antrag:", getAntragDatum());
        builder.nextLine();
        builder.append("Genehmigung:", getGenehmigungDatum());
        builder.nextLine();
        builder.append("Wiedervorlagen:", getWiedervorlageDatum());
        builder.nextLine();
        builder.append("Gefährdungsklasse:", getGefaehrdungsklasseFeld());
        builder.nextLine();
        builder.append("", getErloschenCheck());
        builder.nextLine();

        builder.appendSeparator("Entsorger");
        builder.append("Entsorger:", getEntsorgerBox(), 2);
        builder.append(getEntsorBearbToolBar());

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getAnh50BemerkungArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 6);

        builder.appendSeparator("Verknüpfte Objekte");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane objektverknuepfungScroller = new JScrollPane(
                getObjektverknuepungTabelle(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 6);
        builder.nextLine();

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(getSelectObjektButton(), getSaveAnh50Button());
        builder.append(buttonBar, 6);
    }

    public void fetchFormData() throws RuntimeException {
        fachdaten = Anh50Fachdaten.getAnh50ByObjekt(hauptModul.getObjekt());
        log.debug("(Anh50Panel.fetchFormData) "
        		+ "Zahnarzt aus DB geholt: " + fachdaten);

        if (entsorg == null || entsorg.length == 0) {
            entsorg = AnhEntsorger.getEntsorg();
        }
    }


    public void updateForm() throws RuntimeException {
        if (entsorg != null) {
            getEntsorgerBox().setModel(new DefaultComboBoxModel(entsorg));
        }

        if (fachdaten != null) {
            if (fachdaten.getBemerkungen() != null) {
                getAnh50BemerkungArea().setText(fachdaten.getBemerkungen());
            }
            if (fachdaten.getDatumantrag() != null) {
                getAntragDatum().setDate(fachdaten.getDatumantrag());
            }
            if (fachdaten.getGenehmigung() != null) {
                getGenehmigungDatum().setDate(fachdaten.getGenehmigung());
            }
            if (fachdaten.getWiedervorlage() != null) {
                getWiedervorlageDatum().setDate(fachdaten.getWiedervorlage());
            }
            if (fachdaten.getGefaehrdungsklasse() != null) {
                getGefaehrdungsklasseFeld().setText(fachdaten.getGefaehrdungsklasse().toString());
            }
            if (fachdaten.getAnhEntsorger() != null) {
                getEntsorgerBox().setSelectedItem(fachdaten.getAnhEntsorger());
            }
            if (fachdaten.getErloschen() != null) {
                if (fachdaten.getErloschen() == true) {
                    getErloschenCheck().setSelected(true);
                }
                else {
                    getErloschenCheck().setSelected(false);
                }
            }
            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());
        }

        }


    public void clearForm() {

        getGefaehrdungsklasseFeld().setText(null);
        getAnh50BemerkungArea().setText(null);
        getAntragDatum().setDate(null);
        getGenehmigungDatum().setDate(null);
        getWiedervorlageDatum().setDate(null);
        getErloschenCheck().setSelected(false);

    }

    public void enableAll(boolean enabled) {

    }


    public String getName() {
        return name;
    }

    private boolean saveAnh50Daten() {
        boolean success;

        String bemerkungen = anh50BemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            fachdaten.setBemerkungen(null);
        } else {
            fachdaten.setBemerkungen(bemerkungen);
        }

        Date antrag = antragDatum.getDate();
        fachdaten.setDatumantrag(antrag);

        if (getErloschenCheck().isSelected())  {
            fachdaten.setErloschen(true);
        } else {
            fachdaten.setErloschen(false);
        }

        String gefaehrdungsklasse = gefaehrdungsklasseFeld.getText();
        if ("".equals(gefaehrdungsklasse)) {
            fachdaten.setGefaehrdungsklasse(null);
        } else {
            fachdaten.setGefaehrdungsklasse(gefaehrdungsklasse);
        }

        Date genehmigung = genehmigungDatum.getDate();
        fachdaten.setGenehmigung(genehmigung);

        Date wiedervorlage = wiedervorlageDatum.getDate();
        fachdaten.setWiedervorlage(wiedervorlage);

        if (getEntsorgerBox().getSelectedItem() != null) {
            fachdaten.setAnhEntsorger((AnhEntsorger) getEntsorgerBox()
                    .getSelectedItem());
            log.debug("(Anh50Panel.saveAnh50Daten) " + "Entsorger "
                    + fachdaten.getAnhEntsorger() + " zugeordnet.");
        } else
            getEntsorgerBox().setSelectedIndex(1);
            fachdaten.setAnhEntsorger((AnhEntsorger) getEntsorgerBox()
                    .getSelectedItem());

        success = Anh50Fachdaten.saveFachdaten(fachdaten);
        if (success) {
            log.debug("(Anh50Panel.saveAnh50Daten) " + "Zahnarzt "
            		+ fachdaten.getBasisObjekt().getBasisBetreiber().getBetrname()
            		+ " gespeichert.");
        } else {
            log.debug("(Anh50Panel.saveAnh50Daten) " + "Zahnarzt " + fachdaten
                    + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (hauptModul.isNew() || fachdaten == null) {
            // Neuen Zahnarzt erzeugen
            fachdaten = new Anh50Fachdaten();
            // Objekt_Id setzen
            fachdaten.setBasisObjekt(hauptModul.getObjekt());
            // Entsorger auf "unbekannt" setzen
            AnhEntsorger entsorg = new AnhEntsorger();
            entsorg.setEntsorgerid(1);
            fachdaten.setAnhEntsorger(entsorg);

            // Zahnarzt speichern
            Anh50Fachdaten.saveFachdaten(fachdaten);
            log.debug("(BasisObjektBearbeiten.completeObjekt) "
            		+ "Neuer Zahnarzt " + fachdaten + " gespeichert.");
        }
    }



    private JTextArea getAnh50BemerkungArea() {
        if (anh50BemerkungArea == null) {
            anh50BemerkungArea = new LimitedTextArea(255);
            anh50BemerkungArea.setLineWrap(true);
            anh50BemerkungArea.setWrapStyleWord(true);
        }
        return anh50BemerkungArea;
    }
    private TextFieldDateChooser getAntragDatum() {
        if (antragDatum == null) {
            antragDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return antragDatum;
    }
    private JCheckBox getErloschenCheck() {
        if (erloschenCheck == null) {
            erloschenCheck = new JCheckBox("Erloschen");
        }
        return erloschenCheck;
    }
    private JTextField getGefaehrdungsklasseFeld() {
        if (gefaehrdungsklasseFeld == null) {
            gefaehrdungsklasseFeld = new LimitedTextField(50);
        }
        return gefaehrdungsklasseFeld;
    }
    private TextFieldDateChooser getGenehmigungDatum() {
        if (genehmigungDatum == null) {
            genehmigungDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return genehmigungDatum;
    }
    private TextFieldDateChooser getWiedervorlageDatum() {
        if (wiedervorlageDatum == null) {
            wiedervorlageDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return wiedervorlageDatum;
    }
    private JComboBox getEntsorgerBox() {
        if (entsorgerBox == null) {
            entsorgerBox = new JComboBox();
        }
        return entsorgerBox;
    }
private JButton getSaveAnh50Button() {
        if (saveAnh50Button == null) {
            saveAnh50Button = new JButton("Speichern");

            saveAnh50Button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh50Daten()) {
                        hauptModul.getFrame().changeStatus("Zahnarzt "+fachdaten.getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
                    } else {
                        hauptModul.getFrame().changeStatus("Fehler beim Speichern des Zahnarztes!", HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }
        return saveAnh50Button;
    }
    private JButton getEntsorgBearbButton() {
        if (entsorgBearbButton == null) {
            entsorgBearbButton = new JButton(AuikUtils.getIcon(16, "edit.png", ""));
            entsorgBearbButton.setHorizontalAlignment(JButton.CENTER);
            entsorgBearbButton.setToolTipText("Entsorger bearbeiten");
            entsorgBearbButton.setActionCommand("entsorger_edit");

            entsorgBearbButton.addActionListener(getEditButtonListener());
        }
        return entsorgBearbButton;
    }

    private JToolBar getEntsorBearbToolBar() {
        if (entsorBearbToolBar == null) {
            entsorBearbToolBar = new JToolBar();
            entsorBearbToolBar.setFloatable(false);
            entsorBearbToolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);;

            entsorBearbToolBar.add(getEntsorgBearbButton());
            }
        return entsorBearbToolBar;
    }

    private ActionListener getEditButtonListener() {
        if (editButtonListener == null) {
            editButtonListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String action = e.getActionCommand();

                    AnhEntsorger entsorg = (AnhEntsorger) getEntsorgerBox().getSelectedItem();

                    if ("entsorger_edit".equals(action) && entsorg != null) {
                        EntsorgerEditor editDialog = new EntsorgerEditor(entsorg, hauptModul.getFrame());
                        editDialog.setLocationRelativeTo(hauptModul.getFrame());

                        editDialog.setVisible(true);
                        editDialog.setEnabled(true);

                        entsorg = editDialog.getEntsorger();
                    }
                }
            };
        }

        return editButtonListener;
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
                                		+ "Objekt " + verknuepfung.getId()
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

