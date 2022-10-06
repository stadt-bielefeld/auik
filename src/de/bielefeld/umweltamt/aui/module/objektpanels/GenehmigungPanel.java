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
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Wasserrecht;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Genehmigung"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class GenehmigungPanel extends JPanel implements ObjectPanel{
    private static final long serialVersionUID = -1132786436313164359L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets

    private JTextField aktenzeichenFeld = null;
    private JTextArea genBemerkungArea = null;
    private JTextArea nebenbestArea =null;
    private TextFieldDateChooser antragsDatum = null;
    private TextFieldDateChooser genehmigungsDatum = null;
    private TextFieldDateChooser aenderungsDatum = null;
    private TextFieldDateChooser befristetbisDatum = null;
    private JFormattedTextField anhangFeld = null;
    private JFormattedTextField genMengeFeld = null;
    private JCheckBox befristetCheck = null;
    private JCheckBox gen58Check = null;
    private JCheckBox gen59Check = null;
    private JCheckBox gen8Check = null;
    private JCheckBox selbstueberwCheck = null;
    private JCheckBox eSatzungCheck = null;

    private JButton saveGenehmigungButton = null;

    // Daten

    private Wasserrecht fachdaten = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public GenehmigungPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Genehmigung";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:80dlu, 5dlu, 90dlu, 5dlu, r:20dlu, 5dlu, 100dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.appendSeparator("Fachdaten");
        builder.append("Aktenzeichen:", getAktenzeichenFeld());
        builder.nextLine();
        builder.append("Antragsdatum:", getAntragsDatum());
        builder.append("", getGen58CheckBox());
        builder.nextLine();
        builder.append("Genehmigungsdatum:", getGenehmigungsDatum());
        builder.append("", getGen59CheckBox());
        builder.nextLine();
        builder.append("Änderungsdatum:", getAenderungsDatum());
        builder.append("", getGen8CheckBox());
        builder.nextLine();
        builder.append("Anhang:", getAnhangFeld());
        builder.append("", getSelbCheckBox());
        builder.nextLine();
        builder.append("Genehmigte Menge [m³]:", getGenMengeFeld());
        builder.append("", getEsaCheckBox());
        builder.nextLine();
        builder.append("Genehmigung befristet:", getBefCheckBox());
        builder.append("bis:", getBefristetDatum());
        
        builder.nextLine();
        builder.appendSeparator("Nebenbestimmungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane nebenbestScroller = new JScrollPane(getNebenbestArea(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(nebenbestScroller, 7);
        
        builder.nextLine();      
        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getGenBemerkungArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);
        
        builder.nextLine();
        builder.appendSeparator("Verknüpfte Objekte");
        builder.appendRow("3dlu");
        builder.nextLine(2);
		JScrollPane objektverknuepfungScroller = new JScrollPane(getObjektverknuepungTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		builder.appendRow("fill:100dlu");
		builder.append(objektverknuepfungScroller, 7);
		builder.nextLine();

        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
            getSelectObjektButton(), getsaveGenehmigungButton());

        builder.append(buttonBar, 7);
    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = Wasserrecht.findByObjektId(
            this.hauptModul.getObjekt().getId());
        log.debug("Genehmigung Objekt aus DB geholt: ID" + this.fachdaten);
    }

    public void updateForm() throws RuntimeException {

        if (this.fachdaten != null) {

            if (this.fachdaten.getAktenzeichen() != null) {
                getAktenzeichenFeld().setText(this.fachdaten.getAktenzeichen());
            }
            if (this.fachdaten.getBemerkungen() != null) {
                getGenBemerkungArea().setText(this.fachdaten.getBemerkungen());
            }
            
            if (this.fachdaten.getNebenbest() != null) {
            	getNebenbestArea().setText(this.fachdaten.getNebenbest());
            }
            
            if (this.fachdaten.getAnhang() != null) {
                getAnhangFeld().setText(this.fachdaten.getAnhang().toString());
            }
            if (this.fachdaten.getGenMenge() != null) {
                getGenMengeFeld().setText(
                    this.fachdaten.getGenMenge().toString());
            }
            if (this.fachdaten.getAntragDatum() != null) {
                getAntragsDatum().setDate(this.fachdaten.getAntragDatum());
            }
            if (this.fachdaten.getErstellungsDatum() != null) {
                getGenehmigungsDatum().setDate(
                    this.fachdaten.getErstellungsDatum());
            }
            if (this.fachdaten.getAenderungsDatum() != null) {
                getAenderungsDatum().setDate(
                    this.fachdaten.getAenderungsDatum());
            }
            if (this.fachdaten.getBefristetBis() != null) {
                getBefristetDatum().setDate(this.fachdaten.getBefristetBis());
            }
            if (this.fachdaten.getBefristet() != null) {
                if (this.fachdaten.getBefristet() == true) {
                    getBefCheckBox().setSelected(true);
                } else {
                    getBefCheckBox().setSelected(false);
                }
            }
            if (this.fachdaten.getGen58() != null) {
                if (this.fachdaten.getGen58() == true) {
                    getGen58CheckBox().setSelected(true);
                } else {
                    getGen58CheckBox().setSelected(false);
                }
            }
            if (this.fachdaten.getGen59() != null) {
                if (this.fachdaten.getGen59() == true) {
                    getGen59CheckBox().setSelected(true);
                } else {
                    getGen59CheckBox().setSelected(false);
                }
            }
            if (this.fachdaten.getGen8() != null) {
                if (this.fachdaten.getGen8() == true) {
                    getGen8CheckBox().setSelected(true);
                } else {
                    getGen8CheckBox().setSelected(false);
                }
            }
            if (this.fachdaten.getSelbstueberw() != null) {
                if (this.fachdaten.getSelbstueberw() == true) {
                    getSelbCheckBox().setSelected(true);
                } else {
                    getSelbCheckBox().setSelected(false);
                }
            }
            if (this.fachdaten.getESatzung() != null) {
                if (this.fachdaten.getESatzung() == true) {
                    getEsaCheckBox().setSelected(true);
                } else {
                    getEsaCheckBox().setSelected(false);
                }
            }
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());

        }

    }

    public void clearForm() {
    	getAktenzeichenFeld().setText(null);
        getGenBemerkungArea().setText(null);
        getNebenbestArea().setText(null);
        getAnhangFeld().setText(null);
        getGenMengeFeld().setText(null);
        getAntragsDatum().setDate(null);
        getAenderungsDatum().setDate(null);
        getGenehmigungsDatum().setDate(null);
        getBefristetDatum().setDate(null);
        getBefCheckBox().setSelected(false);
        getGen58CheckBox().setSelected(false);
        getGen59CheckBox().setSelected(false);
        getGen8CheckBox().setSelected(false);
        getSelbCheckBox().setSelected(false);
        getEsaCheckBox().setSelected(false);
    }

    public void enableAll(boolean enabled) {
    	getAktenzeichenFeld().setEnabled(enabled);
        getGenBemerkungArea().setEnabled(enabled);
        getNebenbestArea().setEnabled(enabled);
        getAnhangFeld().setEnabled(enabled);
        getGenMengeFeld().setEnabled(enabled);
        getAntragsDatum().setEnabled(enabled);
        getAenderungsDatum().setEnabled(enabled);
        getGenehmigungsDatum().setEnabled(enabled);
        getBefristetDatum().setEnabled(enabled);
        getBefCheckBox().setEnabled(enabled);
        getGen58CheckBox().setEnabled(enabled);
        getGen59CheckBox().setEnabled(enabled);
        getGen8CheckBox().setEnabled(enabled);
        getSelbCheckBox().setEnabled(enabled);
        getEsaCheckBox().setEnabled(enabled);
    }

    public boolean savePanelData() {
        boolean success;

        String aktenzeichen = this.aktenzeichenFeld.getText();
        if ("".equals(aktenzeichen)) {
            this.fachdaten.setAktenzeichen(null);
        } else {
            this.fachdaten.setAktenzeichen(aktenzeichen);
        }
        String bemerkungen = this.genBemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            this.fachdaten.setBemerkungen(null);
        } else {
            this.fachdaten.setBemerkungen(bemerkungen);
        }
        
        String nebenbest = this.nebenbestArea.getText();
        if ("".equals(nebenbest)) {
            this.fachdaten.setNebenbest(null);
        } else {
            this.fachdaten.setNebenbest(nebenbest);
        }
        
        Date antrag = this.antragsDatum.getDate();
        this.fachdaten.setAntragDatum(antrag);

        Date aend = this.aenderungsDatum.getDate();
        this.fachdaten.setAenderungsDatum(aend);

        Date erst = this.genehmigungsDatum.getDate();
        this.fachdaten.setErstellungsDatum(erst);

        Date befr = this.befristetbisDatum.getDate();
        this.fachdaten.setBefristetBis(befr);

        Integer anhang = ((IntegerField) this.anhangFeld).getIntValue();
        this.fachdaten.setAnhang(anhang);

        Integer menge = ((IntegerField) this.genMengeFeld).getIntValue();
        this.fachdaten.setGenMenge(menge);

        if (getBefCheckBox().isSelected()) {
            this.fachdaten.setBefristet(true);
        } else {
            this.fachdaten.setBefristet(false);
        }

        if (getGen58CheckBox().isSelected()) {
            this.fachdaten.setGen58(true);
        } else {
            this.fachdaten.setGen58(false);
        }

        if (getGen59CheckBox().isSelected()) {
            this.fachdaten.setGen59(true);
        } else {
            this.fachdaten.setGen59(false);
        }

        if (getGen8CheckBox().isSelected()) {
            this.fachdaten.setGen8(true);
        } else {
            this.fachdaten.setGen8(false);
        }

        if (getSelbCheckBox().isSelected()) {
            this.fachdaten.setSelbstueberw(true);
        } else {
            this.fachdaten.setSelbstueberw(false);
        }

        if (getEsaCheckBox().isSelected()) {
            this.fachdaten.setESatzung(true);
        } else {
            this.fachdaten.setESatzung(false);
        }

        success = this.fachdaten.merge();
        if (success) {
            log.debug("Uebergabestelle Objekt " + this.fachdaten.getId()
                + " gespeichert.");
        } else {
            log.debug("Uebergabestelle Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neues Genehmigung Objekt erzeugen
            this.fachdaten = new Wasserrecht();
            // Objekt_Id setzen
            this.fachdaten.setObjekt(this.hauptModul.getObjekt());

            // Uebergabestelle speichern
            this.fachdaten = Wasserrecht.merge(this.fachdaten);
            log.debug("Neues Genehmigung Objekt " + this.fachdaten
                + " gespeichert.");
        }
    }

    private JButton getsaveGenehmigungButton() {
        if (this.saveGenehmigungButton == null) {
            this.saveGenehmigungButton = new JButton("Speichern");

            this.saveGenehmigungButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (hauptModul.saveAllTabs()) {
                        GenehmigungPanel.this.hauptModul.getFrame()
                            .changeStatus(
                                "Genehmigung "
                                    + GenehmigungPanel.this.fachdaten
                                        .getId()
                                    + " erfolgreich gespeichert.",
                                HauptFrame.SUCCESS_COLOR);
                    } else {
                        GenehmigungPanel.this.hauptModul
                            .getFrame()
                            .changeStatus(
                                "Fehler beim Speichern des Uebergabestelle Objekt!",
                                HauptFrame.ERROR_COLOR);
                    }

                    GenehmigungPanel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveGenehmigungButton;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private JTextField getAktenzeichenFeld() {
        if (this.aktenzeichenFeld == null) {
            this.aktenzeichenFeld = new JTextField();
        }
        return this.aktenzeichenFeld;
    }

    private TextFieldDateChooser getAntragsDatum() {
        if (this.antragsDatum == null) {
            this.antragsDatum = new TextFieldDateChooser();
        }
        return this.antragsDatum;
    }

    private TextFieldDateChooser getAenderungsDatum() {
        if (this.aenderungsDatum == null) {
            this.aenderungsDatum = new TextFieldDateChooser();
        }
        return this.aenderungsDatum;
    }

    private TextFieldDateChooser getGenehmigungsDatum() {
        if (this.genehmigungsDatum == null) {
            this.genehmigungsDatum = new TextFieldDateChooser();
        }
        return this.genehmigungsDatum;
    }

    private TextFieldDateChooser getBefristetDatum() {
        if (this.befristetbisDatum == null) {
            this.befristetbisDatum = new TextFieldDateChooser();
        }
        return this.befristetbisDatum;
    }

    private JFormattedTextField getAnhangFeld() {
        if (this.anhangFeld == null) {
            this.anhangFeld = new IntegerField();
        }
        return this.anhangFeld;
    }

    private JFormattedTextField getGenMengeFeld() {
        if (this.genMengeFeld == null) {
            this.genMengeFeld = new IntegerField();
        }
        return this.genMengeFeld;
    }

    private JCheckBox getBefCheckBox() {
        if (this.befristetCheck == null) {
            this.befristetCheck = new JCheckBox();
        }
        return this.befristetCheck;
    }

    private JCheckBox getGen58CheckBox() {
        if (this.gen58Check == null) {
            this.gen58Check = new JCheckBox("58er Genehmigung");
        }
        return this.gen58Check;
    }

    private JCheckBox getGen59CheckBox() {
        if (this.gen59Check == null) {
            this.gen59Check = new JCheckBox("59er Genehmigung");
        }
        return this.gen59Check;
    }

    private JCheckBox getGen8CheckBox() {
        if (this.gen8Check == null) {
            this.gen8Check = new JCheckBox("8er Erlaubnis");
        }
        return this.gen8Check;
    }

    private JCheckBox getSelbCheckBox() {
        if (this.selbstueberwCheck == null) {
            this.selbstueberwCheck = new JCheckBox("Selbstüberwachung");
        }
        return this.selbstueberwCheck;
    }

    private JCheckBox getEsaCheckBox() {
        if (this.eSatzungCheck == null) {
            this.eSatzungCheck = new JCheckBox("E-Satzungsüberwachung");
        }
        return this.eSatzungCheck;
    }

    private JTextArea getGenBemerkungArea() {
        if (this.genBemerkungArea == null) {
            this.genBemerkungArea = new LimitedTextArea(150);
            this.genBemerkungArea.setLineWrap(true);
            this.genBemerkungArea.setWrapStyleWord(true);
        }
        return this.genBemerkungArea;
    }
    
    private JTextArea getNebenbestArea() {
        if (this.nebenbestArea == null) {
            this.nebenbestArea = new LimitedTextArea(150);
            this.nebenbestArea.setLineWrap(true);
            this.nebenbestArea.setWrapStyleWord(true);
        }
        return this.nebenbestArea;
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
                                Objektverknuepfung obj = GenehmigungPanel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != GenehmigungPanel.this.hauptModul
                                    .getObjekt().getId().intValue())
                                    GenehmigungPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                else
                                    GenehmigungPanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                GenehmigungPanel.this.hauptModul.getManager()
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
                private static final long serialVersionUID = -3396930685747369346L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung = GenehmigungPanel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (GenehmigungPanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                GenehmigungPanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                GenehmigungPanel.this.hauptModul.getFrame()
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
                        GenehmigungPanel.this.hauptModul.getFrame(),
                        GenehmigungPanel.this.fachdaten.getObjekt(),
                        GenehmigungPanel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }
}
