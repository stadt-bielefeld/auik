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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhEntsorger;
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
    private static final long serialVersionUID = 7997458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

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

    // Listener
    private ActionListener editButtonListener;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public Anh50Panel(BasisObjektBearbeiten hauptModul) {
        this.name = "Zahnarzt";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
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
        JScrollPane bemerkungsScroller = new JScrollPane(
            getAnh50BemerkungArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 6);

        builder.appendSeparator("Verknüpfte Objekte");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane objektverknuepfungScroller = new JScrollPane(
            getObjektverknuepungTabelle(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:100dlu");
        builder.append(objektverknuepfungScroller, 6);
        builder.nextLine();

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(
            getSelectObjektButton(), getSaveAnh50Button());
        builder.append(buttonBar, 6);
    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = this.hauptModul.getObjekt().getAnh50Fachdaten();
        log.debug("Zahnarzt aus DB geholt: " + this.fachdaten);

        if (this.entsorg == null || this.entsorg.length == 0) {
            this.entsorg = DatabaseQuery.getEntsorger();
        }
    }

    public void updateForm() throws RuntimeException {
        if (this.entsorg != null) {
            getEntsorgerBox().setModel(new DefaultComboBoxModel(this.entsorg));
        }

        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null) {
                getAnh50BemerkungArea()
                    .setText(this.fachdaten.getBemerkungen());
            }
            if (this.fachdaten.getDatumantrag() != null) {
                getAntragDatum().setDate(this.fachdaten.getDatumantrag());
            }
            if (this.fachdaten.getGenehmigung() != null) {
                getGenehmigungDatum().setDate(this.fachdaten.getGenehmigung());
            }
            if (this.fachdaten.getWiedervorlage() != null) {
                getWiedervorlageDatum().setDate(
                    this.fachdaten.getWiedervorlage());
            }
            if (this.fachdaten.getGefaehrdungsklasse() != null) {
                getGefaehrdungsklasseFeld().setText(
                    this.fachdaten.getGefaehrdungsklasse().toString());
            }
            if (this.fachdaten.getAnhEntsorger() != null) {
                getEntsorgerBox().setSelectedItem(
                    this.fachdaten.getAnhEntsorger());
            }
            if (this.fachdaten.getErloschen() != null) {
                if (this.fachdaten.getErloschen() == true) {
                    getErloschenCheck().setSelected(true);
                } else {
                    getErloschenCheck().setSelected(false);
                }
            }
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
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
        // This is intentionally left blank
    }

    @Override
    public String getName() {
        return this.name;
    }

    private boolean saveAnh50Daten() {
        boolean success;

        String bemerkungen = this.anh50BemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            this.fachdaten.setBemerkungen(null);
        } else {
            this.fachdaten.setBemerkungen(bemerkungen);
        }

        Date antrag = this.antragDatum.getDate();
        this.fachdaten.setDatumantrag(antrag);

        if (getErloschenCheck().isSelected()) {
            this.fachdaten.setErloschen(true);
        } else {
            this.fachdaten.setErloschen(false);
        }

        String gefaehrdungsklasse = this.gefaehrdungsklasseFeld.getText();
        if ("".equals(gefaehrdungsklasse)) {
            this.fachdaten.setGefaehrdungsklasse(null);
        } else {
            this.fachdaten.setGefaehrdungsklasse(gefaehrdungsklasse);
        }

        Date genehmigung = this.genehmigungDatum.getDate();
        this.fachdaten.setGenehmigung(genehmigung);

        Date wiedervorlage = this.wiedervorlageDatum.getDate();
        this.fachdaten.setWiedervorlage(wiedervorlage);

        if (getEntsorgerBox().getSelectedItem() != null) {
            this.fachdaten.setAnhEntsorger((AnhEntsorger) getEntsorgerBox()
                .getSelectedItem());
            log.debug("Entsorger " + this.fachdaten.getAnhEntsorger()
                + " zugeordnet.");
        } else
            getEntsorgerBox().setSelectedIndex(1);
        this.fachdaten.setAnhEntsorger((AnhEntsorger) getEntsorgerBox()
            .getSelectedItem());

        success = this.fachdaten.merge();
        if (success) {
            log.debug("Zahnarzt "
                + this.fachdaten.getBasisObjekt().getBasisBetreiber()
                    .getBetrname() + " gespeichert.");
        } else {
            log.debug("Zahnarzt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neuen Zahnarzt erzeugen
            this.fachdaten = new Anh50Fachdaten();
            // Objekt_Id setzen
            this.fachdaten.setBasisObjekt(this.hauptModul.getObjekt());
            // Entsorger auf "unbekannt" setzen
            AnhEntsorger entsorg = AnhEntsorger.findById(
                DatabaseConstants.ANH_ENTSORGER_ID_UNBEKANNT);
            this.fachdaten.setAnhEntsorger(entsorg);

            // Zahnarzt speichern
            this.fachdaten.merge();
            log.debug("Neuer Zahnarzt " + this.fachdaten + " gespeichert.");
        }
    }

    private JTextArea getAnh50BemerkungArea() {
        if (this.anh50BemerkungArea == null) {
            this.anh50BemerkungArea = new LimitedTextArea(255);
            this.anh50BemerkungArea.setLineWrap(true);
            this.anh50BemerkungArea.setWrapStyleWord(true);
        }
        return this.anh50BemerkungArea;
    }

    private TextFieldDateChooser getAntragDatum() {
        if (this.antragDatum == null) {
            this.antragDatum = new TextFieldDateChooser();
        }
        return this.antragDatum;
    }

    private JCheckBox getErloschenCheck() {
        if (this.erloschenCheck == null) {
            this.erloschenCheck = new JCheckBox("Erloschen");
        }
        return this.erloschenCheck;
    }

    private JTextField getGefaehrdungsklasseFeld() {
        if (this.gefaehrdungsklasseFeld == null) {
            this.gefaehrdungsklasseFeld = new LimitedTextField(50);
        }
        return this.gefaehrdungsklasseFeld;
    }

    private TextFieldDateChooser getGenehmigungDatum() {
        if (this.genehmigungDatum == null) {
            this.genehmigungDatum = new TextFieldDateChooser();
        }
        return this.genehmigungDatum;
    }

    private TextFieldDateChooser getWiedervorlageDatum() {
        if (this.wiedervorlageDatum == null) {
            this.wiedervorlageDatum = new TextFieldDateChooser();
        }
        return this.wiedervorlageDatum;
    }

    private JComboBox getEntsorgerBox() {
        if (this.entsorgerBox == null) {
            this.entsorgerBox = new JComboBox();
        }
        return this.entsorgerBox;
    }

    private JButton getSaveAnh50Button() {
        if (this.saveAnh50Button == null) {
            this.saveAnh50Button = new JButton("Speichern");

            this.saveAnh50Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh50Daten()) {
                        Anh50Panel.this.hauptModul.getFrame().changeStatus(
                            "Zahnarzt "
                                + Anh50Panel.this.fachdaten.getObjektid()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh50Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Zahnarztes!",
                            HauptFrame.ERROR_COLOR);
                    }

                    Anh50Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh50Button;
    }

    private JButton getEntsorgBearbButton() {
        if (this.entsorgBearbButton == null) {
            this.entsorgBearbButton = new JButton(AuikUtils.getIcon(16,
                "edit.png", ""));
            this.entsorgBearbButton
                .setHorizontalAlignment(SwingConstants.CENTER);
            this.entsorgBearbButton.setToolTipText("Entsorger bearbeiten");
            this.entsorgBearbButton.setActionCommand("entsorger_edit");

            this.entsorgBearbButton.addActionListener(getEditButtonListener());
        }
        return this.entsorgBearbButton;
    }

    private JToolBar getEntsorBearbToolBar() {
        if (this.entsorBearbToolBar == null) {
            this.entsorBearbToolBar = new JToolBar();
            this.entsorBearbToolBar.setFloatable(false);
            this.entsorBearbToolBar.putClientProperty("JToolBar.isRollover",
                Boolean.TRUE);

            this.entsorBearbToolBar.add(getEntsorgBearbButton());
        }
        return this.entsorBearbToolBar;
    }

    private ActionListener getEditButtonListener() {
        if (this.editButtonListener == null) {
            this.editButtonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String action = e.getActionCommand();

                    AnhEntsorger entsorg = (AnhEntsorger) getEntsorgerBox()
                        .getSelectedItem();

                    if ("entsorger_edit".equals(action) && entsorg != null) {
                        EntsorgerEditor editDialog = new EntsorgerEditor(
                            entsorg, Anh50Panel.this.hauptModul.getFrame());
                        editDialog
                            .setLocationRelativeTo(Anh50Panel.this.hauptModul
                                .getFrame());

                        editDialog.setVisible(true);
                        editDialog.setEnabled(true);

                        entsorg = editDialog.getEntsorger();
                    }
                }
            };
        }

        return this.editButtonListener;
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
                                BasisObjektverknuepfung obj =
                                    Anh50Panel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getBasisObjektByIstVerknuepftMit()
                                    .getObjektid().intValue() != Anh50Panel.this.hauptModul
                                    .getObjekt().getObjektid().intValue())
                                    Anh50Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getBasisObjektByIstVerknuepftMit()
                                                .getObjektid().intValue(),
                                            false);
                                else
                                    Anh50Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getBasisObjektByObjekt()
                                                .getObjektid().intValue(),
                                            false);
                                Anh50Panel.this.hauptModul.getManager()
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
                private static final long serialVersionUID = 2886609709202711593L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        BasisObjektverknuepfung verknuepfung =
                            Anh50Panel.this.objektVerknuepfungModel.getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (Anh50Panel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                Anh50Panel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                Anh50Panel.this.hauptModul.getFrame()
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
                        Anh50Panel.this.hauptModul.getFrame(),
                        Anh50Panel.this.fachdaten.getBasisObjekt(),
                        Anh50Panel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }
}
