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

import java.awt.Font;
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
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Aba;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Entsorger;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.editors.EntsorgerEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Abwasserbehandlungsanlage"-Tab des ObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class AbaPanel extends JPanel {

private static final long serialVersionUID = -4030805403749508467L;

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private TextFieldDateChooser erstellDatum = null;
    private TextFieldDateChooser aktualDatum = null;
    private JCheckBox genehmigungspflichtCheck = null;
    private JCheckBox wartungCheck = null;
    private JCheckBox einzelabnahmeCheck = null;
    private JTextArea abaBezeichnungArea = null;
    private IntegerField abaE32Field = null;
    private IntegerField abaN32Field = null;
    private JButton saveAbaButton = null;

    // Daten
    private Aba fachdaten = null;
    private Abaverfahren[] verfahren = null;

    // Listener
    private ActionListener editButtonListener;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;
    

    public AbaPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Abwasserbehandlungsanlage";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:70dlu, 5dlu, 90dlu, r:90dlu, 5dlu, 20dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.appendSeparator("Fachdaten");
        builder.append("Erstellt am:", getErstellDat());
        builder.nextLine();
        builder.append("Aktualisiert am:", getAktualDat());
        builder.nextLine();
        builder.append("", getGenehmigungdpflichtCheck());
        builder.nextLine();
        builder.append("", getWartungsvertragCheck());
        builder.nextLine();
        builder.append("", getEinzelabnahmeCheck());
        builder.nextLine();

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(
            getBezeichnungArea(),
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

        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
            getSelectObjektButton(), getSaveAbaButton());
        builder.append(buttonBar, 6);
    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = Aba.findByObjektId(
            this.hauptModul.getObjekt().getId());
        log.debug("Abwasserbehandlungsanlage aus DB geholt: " + this.fachdaten);

        if (this.verfahren == null || this.verfahren.length == 0) {
            this.verfahren = DatabaseQuery.getVerfahren();
        }
    }

    public void updateForm() throws RuntimeException {

        if (this.fachdaten != null) {
            if (this.fachdaten.getAktualDat() != null) {
            	getAktualDat().setDate(this.fachdaten.getAktualDat());
            }
            if (this.fachdaten.getErstellDat() != null) {
            	getErstellDat().setDate(this.fachdaten.getErstellDat());
            }
            if (this.fachdaten.getGenehmpflichtigToc() != null) {
                if (this.fachdaten.getGenehmpflichtigToc() == true) {
                    getGenehmigungdpflichtCheck().setSelected(true);
                } else {
                	getGenehmigungdpflichtCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getWartungsvertragToc() != null) {
                if (this.fachdaten.getWartungsvertragToc() == true) {
                    getWartungsvertragCheck().setSelected(true);
                } else {
                	getWartungsvertragCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getEinzelabnahmeToc() != null) {
                if (this.fachdaten.getEinzelabnahmeToc() == true) {
                    getEinzelabnahmeCheck().setSelected(true);
                } else {
                	getEinzelabnahmeCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getBezeichnung() != null) {
                getBezeichnungArea()
                    .setText(this.fachdaten.getBezeichnung());
            }
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
        }

    }

    public void clearForm() {
        getBezeichnungArea().setText(null);
        getErstellDat().setDate(null);
        getAktualDat().setDate(null);
        getGenehmigungdpflichtCheck().setSelected(false);
    }

    public void enableAll(boolean enabled) {
        // This is intentionally left blank
    }

    @Override
    public String getName() {
        return this.name;
    }

    private boolean saveAbaDaten() {
        boolean success;

        String bezeichnungn = this.abaBezeichnungArea.getText();
        if ("".equals(bezeichnungn)) {
            this.fachdaten.setBezeichnung(null);
        } else {
            this.fachdaten.setBezeichnung(bezeichnungn);
        }

        Date erstell = this.erstellDatum.getDate();
        this.fachdaten.setErstellDat(erstell);

        Date aktual = this.aktualDatum.getDate();
        this.fachdaten.setAktualDat(aktual);

        if (getGenehmigungdpflichtCheck().isSelected()) {
            this.fachdaten.setGenehmpflichtigToc(true);
        } else {
            this.fachdaten.setGenehmpflichtigToc(false);
        }

        if (getWartungsvertragCheck().isSelected()) {
            this.fachdaten.setWartungsvertragToc(true);
        } else {
            this.fachdaten.setWartungsvertragToc(false);
        }

        if (getEinzelabnahmeCheck().isSelected()) {
            this.fachdaten.setEinzelabnahmeToc(true);
        } else {
            this.fachdaten.setEinzelabnahmeToc(false);
        }
        
        success = this.fachdaten.merge();
        if (success) {
            log.debug("Abwasserbehandlungsanlage "
                + this.fachdaten.getObjekt().getBetreiberid()
                    .getBetrname() + " gespeichert.");
        } else {
            log.debug("Abwasserbehandlungsanlage " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neuen Abwasserbehandlungsanlage erzeugen
            this.fachdaten = new Aba();
            // Objekt_Id setzen
            this.fachdaten.setObjekt(this.hauptModul.getObjekt());
            // Verfahren auf "unbekannt" setzen
            Abaverfahren verfahren = Abaverfahren.findById(1);

            // Abwasserbehandlungsanlage speichern
            this.fachdaten.merge();
            log.debug("Neuer Zahnarzt " + this.fachdaten + " gespeichert.");
        }
    }

    private JTextArea getBezeichnungArea() {
        if (this.abaBezeichnungArea == null) {
            this.abaBezeichnungArea = new LimitedTextArea(255);
            this.abaBezeichnungArea.setLineWrap(true);
            this.abaBezeichnungArea.setWrapStyleWord(true);
        }
        return this.abaBezeichnungArea;
    }

    private TextFieldDateChooser getErstellDat() {
        if (this.erstellDatum == null) {
            this.erstellDatum = new TextFieldDateChooser();
        }
        return this.erstellDatum;
    }

    private TextFieldDateChooser getAktualDat() {
        if (this.aktualDatum == null) {
            this.aktualDatum = new TextFieldDateChooser();
        }
        return this.aktualDatum;
    }

    private JCheckBox getGenehmigungdpflichtCheck() {
        if (this.genehmigungspflichtCheck == null) {
            this.genehmigungspflichtCheck = new JCheckBox("Genehmigungspflicht");
        }
        return this.genehmigungspflichtCheck;
    }

    private JCheckBox getWartungsvertragCheck() {
        if (this.wartungCheck == null) {
            this.wartungCheck = new JCheckBox("Wartungsvertrag");
        }
        return this.wartungCheck;
    }

    private JCheckBox getEinzelabnahmeCheck() {
        if (this.einzelabnahmeCheck == null) {
            this.einzelabnahmeCheck = new JCheckBox("Einzelabnahme");
        }
        return this.einzelabnahmeCheck;
    }

    private JButton getSaveAbaButton() {
        if (this.saveAbaButton == null) {
            this.saveAbaButton = new JButton("Speichern");

            this.saveAbaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAbaDaten()) {
                        AbaPanel.this.hauptModul.getFrame().changeStatus(
                            "Zahnarzt "
                                + AbaPanel.this.fachdaten.getId()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        AbaPanel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Zahnarztes!",
                            HauptFrame.ERROR_COLOR);
                    }

                    AbaPanel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAbaButton;
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
                                Objektverknuepfung obj =
                                    AbaPanel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != AbaPanel.this.hauptModul
                                    .getObjekt().getId().intValue())
                                    AbaPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                else
                                    AbaPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                AbaPanel.this.hauptModul.getManager()
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
                        Objektverknuepfung verknuepfung =
                            AbaPanel.this.objektVerknuepfungModel.getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (AbaPanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                AbaPanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                AbaPanel.this.hauptModul.getFrame()
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
                        AbaPanel.this.hauptModul.getFrame(),
                        AbaPanel.this.fachdaten.getObjekt(),
                        AbaPanel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }
}
