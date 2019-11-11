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
import javax.swing.JComponent;
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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh53Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
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
        this.name = "Fotografische Prozesse";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:100dlu, 5dlu, 80dlu, 5dlu, r:65dlu, 5dlu, 100dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

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
        JScrollPane bemerkungsScroller = new JScrollPane(
            getAnh53BemerkungArea(),
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

        JComponent buttonBar = ComponentFactory.buildRightAlignedBar(
            getSelectObjektButton(), getSaveAnh53Button());
        builder.append(buttonBar, 7);
    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = Anh53Fachdaten.findByObjektId(
            this.hauptModul.getObjekt().getId());
        log.debug("Objekt aus DB geholt: " + this.fachdaten);
    }

    public void updateForm() throws RuntimeException {

        if (this.fachdaten != null) {
            if (this.fachdaten.getBemerkungen() != null) {
                getAnh53BemerkungArea()
                    .setText(this.fachdaten.getBemerkungen());
            }
            if (this.fachdaten.getBranche() != null) {
                getBrancheFeld().setText(this.fachdaten.getBranche());
            }
            if (this.fachdaten.getDurchsatz() != null) {
                getDurchsatzFeld().setValue(this.fachdaten.getDurchsatz());
            }
            if (this.fachdaten.getGesamtmengeEb() != null) {
                getGesamtmengeFeld()
                    .setValue(this.fachdaten.getGesamtmengeEb());
            }
            if (this.fachdaten.getSpuelwassermenge() != null) {
                getSpuelwasserFeld().setValue(
                    this.fachdaten.getSpuelwassermenge());
            }
            if (this.fachdaten.getBagatell() != null) {
                if (this.fachdaten.getBagatell() == true) {
                    getBagatellCheck().setSelected(true);
                } else {
                    getBagatellCheck().setSelected(false);
                }
            }
            if (this.fachdaten.getAbwasser() != null) {
                if (this.fachdaten.getAbwasser() == true) {
                    getAbwasserCheck().setSelected(true);
                } else {
                    getAbwasserCheck().setSelected(false);
                }
            }
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());
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
        // This is intentionally left blank
    }

    @Override
    public String getName() {
        return this.name;
    }

    private boolean saveAnh53Daten() {
        boolean success;

        String bemerkungen = this.anh53BemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            this.fachdaten.setBemerkungen(null);
        } else {
            this.fachdaten.setBemerkungen(bemerkungen);
        }

        success = this.fachdaten.merge();
        if (success) {
            log.debug("Objekt "
                + this.fachdaten.getObjekt().getBetreiberid()
                    .getBetrname() + " gespeichert.");
        } else {
            log.debug("Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neues Objekt erzeugen
            this.fachdaten = new Anh53Fachdaten();
            // Objekt_Id setzen
            this.fachdaten.setObjekt(this.hauptModul.getObjekt());

            // Objekt speichern
            Anh53Fachdaten.merge(this.fachdaten);
            log.debug("Neues Objekt " + this.fachdaten + " gespeichert.");
        }
    }

    private JTextArea getAnh53BemerkungArea() {
        if (this.anh53BemerkungArea == null) {
            this.anh53BemerkungArea = new LimitedTextArea(255);
            this.anh53BemerkungArea.setLineWrap(true);
            this.anh53BemerkungArea.setWrapStyleWord(true);
        }
        return this.anh53BemerkungArea;
    }

    private JCheckBox getBagatellCheck() {
        if (this.bagatellCheck == null) {
            this.bagatellCheck = new JCheckBox("Bagatelle");
        }
        return this.bagatellCheck;
    }

    private JCheckBox getOnlinesilberCheck() {
        if (this.onlinesilberCheck == null) {
            this.onlinesilberCheck = new JCheckBox("Online Silber");
        }
        return this.onlinesilberCheck;
    }

    private JCheckBox getAbwasserCheck() {
        if (this.abwasserCheck == null) {
            this.abwasserCheck = new JCheckBox("Abwasser");
        }
        return this.abwasserCheck;
    }

    private JCheckBox getAbgemeldetCheck() {
        if (this.abgemeldetCheck == null) {
            this.abgemeldetCheck = new JCheckBox("Abgemeldet");
        }
        return this.abgemeldetCheck;
    }

    private JCheckBox getTagebuchCheck() {
        if (this.tagebuchCheck == null) {
            this.tagebuchCheck = new JCheckBox("Tagebuch");
        }
        return this.tagebuchCheck;
    }

    private JCheckBox getWasseruhrCheck() {
        if (this.wasseruhrCheck == null) {
            this.wasseruhrCheck = new JCheckBox("Wasseruhr");
        }
        return this.wasseruhrCheck;
    }

    private JCheckBox getWartungCheck() {
        if (this.wartungsvertragCheck == null) {
            this.wartungsvertragCheck = new JCheckBox("Wartungsvertrag");
        }
        return this.wartungsvertragCheck;
    }

    private JCheckBox getGrgenCheck() {
        if (this.grgenCheck == null) {
            this.grgenCheck = new JCheckBox("große Genehmigung");
        }
        return this.grgenCheck;
    }

    private TextFieldDateChooser getBagatellDatum() {
        if (this.bagatellDatum == null) {
            this.bagatellDatum = new TextFieldDateChooser();
        }
        return this.bagatellDatum;
    }

    private JTextField getBrancheFeld() {
        if (this.brancheFeld == null) {
            this.brancheFeld = new LimitedTextField(50);
        }
        return this.brancheFeld;
    }

    private IntegerField getDurchsatzFeld() {
        if (this.durchsatzFeld == null) {
            this.durchsatzFeld = new IntegerField();
        }
        return this.durchsatzFeld;
    }

    private IntegerField getGesamtmengeFeld() {
        if (this.gesamtmengeFeld == null) {
            this.gesamtmengeFeld = new IntegerField();
        }
        return this.gesamtmengeFeld;
    }

    private IntegerField getSpuelwasserFeld() {
        if (this.spuelwasserFeld == null) {
            this.spuelwasserFeld = new IntegerField();
        }
        return this.spuelwasserFeld;
    }

    private JButton getSaveAnh53Button() {
        if (this.saveAnh53Button == null) {
            this.saveAnh53Button = new JButton("Speichern");

            this.saveAnh53Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh53Daten()) {
                        Anh53Panel.this.hauptModul.getFrame().changeStatus(
                            "Objekt " + Anh53Panel.this.fachdaten.getId()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh53Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Objektes!",
                            HauptFrame.ERROR_COLOR);
                    }

                    Anh53Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh53Button;
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
                                Objektverknuepfung obj = Anh53Panel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getObjektByIstVerknuepftMit()
                                    .getId().intValue() != Anh53Panel.this.hauptModul
                                    .getObjekt().getId().intValue())
                                    Anh53Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByIstVerknuepftMit()
                                                .getId().intValue(),
                                            false);
                                else
                                    Anh53Panel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getObjektByObjekt()
                                                .getId().intValue(),
                                            false);
                                Anh53Panel.this.hauptModul.getManager()
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
                private static final long serialVersionUID = -4593617315019377189L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        Objektverknuepfung verknuepfung = Anh53Panel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (Anh53Panel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                Anh53Panel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                Anh53Panel.this.hauptModul.getFrame()
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
                        Anh53Panel.this.hauptModul.getFrame(),
                        Anh53Panel.this.fachdaten.getObjekt(),
                        Anh53Panel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }
}
