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

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.IndeinlUebergabestelle;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.RetractablePanel;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Uebergabestelle"-Tab des BasisObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class UebergabePanel extends JPanel {
    private static final long serialVersionUID = -3459571889270154870L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

//    private IndeinlUebergabestelle upunkt;
    private RetractablePanel fotoRtPanel;
    // Widgets

    private JTextArea ueberstBemerkungArea = null;
    private TextFieldDateChooser erfassungsDatum = null;
    private TextFieldDateChooser aenderungsDatum = null;
    private JComboBox klaeranlageBox = null;
    private JComboBox kanalartBox = null;
    private JFormattedTextField rechtswertFeld = null;
    private JFormattedTextField hochwertFeld = null;

    private JButton saveUebergabeButton = null;

    // Daten

    private IndeinlUebergabestelle fachdaten = null;
//    private AtlKlaeranlagen[] klaeranlagen = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    // Widgets für Fotopanel
    private JLabel fotoLabel;

    public UebergabePanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Übergabestelle";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout(
            "r:80dlu, 5dlu, 80dlu, 5dlu, r:65dlu, 5dlu, 80dlu, 100dlu:g", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.appendSeparator("Fachdaten");
        builder.append("Erfasst:", getErfassungsDatum());
        builder.append("Kanalart:", getKanalartBox());
        builder.nextLine();
        builder.append("Geändert:", getAenderungsDatum());
        builder.append("Kläranlage:", getKlaeranlageBox());
        builder.nextLine();
        builder.append("Rechtswert:", getRechtswertFeld());
        builder.append("Hochwert:", getHochwertFeld());
        builder.nextLine();

        builder.append(getFotoRtPanel(), 8);
        builder.nextLine();

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);

        JScrollPane bemerkungsScroller = new JScrollPane(
            getUeberstBemerkungArea(),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        builder.appendRow("fill:30dlu");
        builder.append(bemerkungsScroller, 7);
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
            getSelectObjektButton(), getsaveUebergabeButton());

        builder.append(buttonBar, 7);
    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = IndeinlUebergabestelle
            .getUebergabeByObjekt(this.hauptModul.getObjekt());
        log.debug("Uebergabestelle Objekt aus DB geholt: ID" + this.fachdaten);
    }

    public void updateForm() throws RuntimeException {

        if (this.fachdaten != null) {

            if (this.fachdaten.getRechtswert() != null) {
                getRechtswertFeld().setText(
                    this.fachdaten.getRechtswert().toString());
            }
            if (this.fachdaten.getHochwert() != null) {
                getHochwertFeld().setText(
                    this.fachdaten.getHochwert().toString());
            }
            if (this.fachdaten.getErfassungsDatum() != null) {
                getErfassungsDatum().setDate(
                    this.fachdaten.getErfassungsDatum());

            }
            if (this.fachdaten.getAenderungsDatum() != null) {
                getAenderungsDatum().setDate(
                    this.fachdaten.getAenderungsDatum());
            }
            if (this.fachdaten.getErfassungsDatum() == null) {
                getErfassungsDatum().setDate(null);

            }
            if (this.fachdaten.getAtlKlaeranlagen() != null) {
                getKlaeranlageBox().setSelectedItem(
                    this.fachdaten.getAtlKlaeranlagen());
            }

            if (this.fachdaten.getKanalart() != null) {
                if (this.fachdaten.getKanalart() == 1)
                    getKanalartBox().setSelectedItem("Schmutzwasser");
                else if (this.fachdaten.getKanalart() == 2)
                    getKanalartBox().setSelectedItem("Mischwasser");
                else if (this.fachdaten.getKanalart() == 3)
                    getKanalartBox().setSelectedItem("Regenwasser");
            }

            Integer rechts = ((IntegerField) this.rechtswertFeld).getIntValue();
            this.fachdaten.setRechtswert(rechts);

            Integer hoch = ((IntegerField) this.hochwertFeld).getIntValue();
            this.fachdaten.setHochwert(hoch);

            if (this.fachdaten.getBemerkungen() != null) {
                getUeberstBemerkungArea().setText(
                    this.fachdaten.getBemerkungen());
            }
            this.objektVerknuepfungModel.setObjekt(this.hauptModul.getObjekt());

            if (getFotoRtPanel().isOpen()) {
                getFotoRtPanel().setOpen(true);
            }
        }
    }

    public void clearForm() {
        getRechtswertFeld().setText(null);
        getHochwertFeld().setText(null);
        getErfassungsDatum().setDate(null);
        getAenderungsDatum().setDate(null);
        getKlaeranlageBox().setSelectedItem(null);
        getKanalartBox().setSelectedItem(null);
        getUeberstBemerkungArea().setText(null);
    }

    public void enableAll(boolean enabled) {
        getRechtswertFeld().setEnabled(enabled);
        getHochwertFeld().setEnabled(enabled);
        getErfassungsDatum().setEnabled(enabled);
        getAenderungsDatum().setEnabled(enabled);
        getKanalartBox().setEnabled(enabled);
        getKlaeranlageBox().setEnabled(enabled);
        getUeberstBemerkungArea().setEnabled(enabled);
    }

    private boolean saveUebergabestelleDaten() {
        boolean success;

        String bemerkungen = this.ueberstBemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            this.fachdaten.setBemerkungen(null);
        } else {
            this.fachdaten.setBemerkungen(bemerkungen);
        }

        Date erf = this.erfassungsDatum.getDate();
        this.fachdaten.setErfassungsDatum(erf);

        Date aend = this.aenderungsDatum.getDate();
        this.fachdaten.setAenderungsDatum(aend);

        Integer rechts = ((IntegerField) this.rechtswertFeld).getIntValue();
        this.fachdaten.setRechtswert(rechts);

        Integer hoch = ((IntegerField) this.hochwertFeld).getIntValue();
        this.fachdaten.setHochwert(hoch);

        if (getKlaeranlageBox().getSelectedItem() != null) {
            this.fachdaten
                .setAtlKlaeranlagen((AtlKlaeranlagen) getKlaeranlageBox()
                    .getSelectedItem());

        }

        if (getKanalartBox().getSelectedItem() != null) {
            if (getKanalartBox().getSelectedItem() == "Schmutzwasser")
                this.fachdaten.setKanalart(1);
            else if (getKanalartBox().getSelectedItem() == "Mischwasser")
                this.fachdaten.setKanalart(2);
            else if (getKanalartBox().getSelectedItem() == "Regenwasser")
                this.fachdaten.setKanalart(3);
        }

        success = IndeinlUebergabestelle.saveFachdaten(this.fachdaten);
        if (success) {
            log.debug("Uebergabestelle Objekt " + this.fachdaten.getObjektid()
                + " gespeichert.");
        } else {
            log.debug("Uebergabestelle Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neues Uebergabestelle Objekt erzeugen
            this.fachdaten = new IndeinlUebergabestelle();
            // Objekt_Id setzen
            this.fachdaten.setBasisObjekt(this.hauptModul.getObjekt());

            // Uebergabestelle speichern
            IndeinlUebergabestelle.saveFachdaten(this.fachdaten);
            log.debug("Neues Uebergabestelle Objekt " + this.fachdaten
                + " gespeichert.");
        }
    }

    private JButton getsaveUebergabeButton() {
        if (this.saveUebergabeButton == null) {
            this.saveUebergabeButton = new JButton("Speichern");

            this.saveUebergabeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveUebergabestelleDaten()) {
                        UebergabePanel.this.hauptModul.getFrame().changeStatus(
                            "Uebergabestelle "
                                + UebergabePanel.this.fachdaten.getObjektid()
                                + " erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        UebergabePanel.this.hauptModul
                            .getFrame()
                            .changeStatus(
                                "Fehler beim Speichern des Uebergabestelle Objekt!",
                                HauptFrame.ERROR_COLOR);
                    }

                    UebergabePanel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveUebergabeButton;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private TextFieldDateChooser getErfassungsDatum() {
        if (this.erfassungsDatum == null) {
            this.erfassungsDatum = new TextFieldDateChooser();
        }
        return this.erfassungsDatum;
    }

    private TextFieldDateChooser getAenderungsDatum() {
        if (this.aenderungsDatum == null) {
            this.aenderungsDatum = new TextFieldDateChooser();
        }
        return this.aenderungsDatum;
    }

    private JFormattedTextField getRechtswertFeld() {
        if (this.rechtswertFeld == null) {
            this.rechtswertFeld = new IntegerField();
        }
        return this.rechtswertFeld;
    }

    private JFormattedTextField getHochwertFeld() {
        if (this.hochwertFeld == null) {
            this.hochwertFeld = new IntegerField();
        }
        return this.hochwertFeld;
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
                                BasisObjektverknuepfung obj = UebergabePanel.this.objektVerknuepfungModel
                                    .getRow(row);
                                if (obj.getBasisObjektByIstVerknuepftMit()
                                    .getObjektid().intValue() != UebergabePanel.this.hauptModul
                                    .getObjekt().getObjektid().intValue())
                                    UebergabePanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getBasisObjektByIstVerknuepftMit()
                                                .getObjektid().intValue(),
                                            false);
                                else
                                    UebergabePanel.this.hauptModul
                                        .getManager()
                                        .getSettingsManager()
                                        .setSetting(
                                            "auik.imc.edit_object",
                                            obj.getBasisObjektByObjekt()
                                                .getObjektid().intValue(),
                                            false);
                                UebergabePanel.this.hauptModul.getManager()
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
                private static final long serialVersionUID = 2126946472021932438L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektverknuepungTabelle().getSelectedRow();
                    if (row != -1
                        && getObjektverknuepungTabelle().getEditingRow() == -1) {
                        BasisObjektverknuepfung verknuepfung = UebergabePanel.this.objektVerknuepfungModel
                            .getRow(row);
                        if (GUIManager.getInstance().showQuestion(
                            "Soll die Verknüpfung wirklich gelöscht werden?\n"
                                + "Hinweis: Die Aktion betrifft nur die "
                                + "Verknüpfung, die Objekte bleiben erhalten "
                                + "und können jederzeit neu verknüpft werden.",
                            "Löschen bestätigen")) {
                            if (UebergabePanel.this.objektVerknuepfungModel
                                .removeRow(row)) {
                                UebergabePanel.this.hauptModul.getFrame()
                                    .changeStatus("Objekt gelöscht.",
                                        HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + verknuepfung.getId()
                                    + " wurde gelöscht!");
                            } else {
                                UebergabePanel.this.hauptModul.getFrame()
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
                        UebergabePanel.this.hauptModul.getFrame(),
                        UebergabePanel.this.fachdaten.getBasisObjekt(),
                        UebergabePanel.this.objektVerknuepfungModel);
                    chooser.setVisible(true);
                }
            });
        }
        return this.selectObjektButton;
    }

    private JComboBox getKlaeranlageBox() {
        if (this.klaeranlageBox == null) {
            this.klaeranlageBox = new JComboBox(
                AtlKlaeranlagen.getKlaeranlagen());
        }
        return this.klaeranlageBox;
    }

    private JComboBox getKanalartBox() {
        if (this.kanalartBox == null) {
            this.kanalartBox = new JComboBox();
            this.kanalartBox.addItem("Schmutzwasser");
            this.kanalartBox.addItem("Mischwasser");
            this.kanalartBox.addItem("Regenwasser");
        }
        return this.kanalartBox;
    }

    private JTextArea getUeberstBemerkungArea() {

        if (this.ueberstBemerkungArea == null) {
            this.ueberstBemerkungArea = new LimitedTextArea(150);
            this.ueberstBemerkungArea.setLineWrap(true);
            this.ueberstBemerkungArea.setWrapStyleWord(true);
        }
        return this.ueberstBemerkungArea;
    }

    // Foto
    private RetractablePanel getFotoRtPanel() {

        if (this.fotoRtPanel == null) {
            JPanel fotoPanel = new JPanel();

            fotoPanel.add(getFotoLabel());
            fotoPanel.setBackground(Color.WHITE);
            fotoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.fotoRtPanel = new RetractablePanel(DefaultComponentFactory
                .getInstance().createSeparator("Foto"), fotoPanel, false, null) {
                private static final long serialVersionUID = 8307814053644083855L;

                @Override
                public void opening() {
                    // Aufruf wenn FotoPanel geöffnet wird
                    // Die Bezeichnung einer verknüpften Sielhautmesstelle wird
                    // ausgelesen
                    // um das passende Foto anzuzeigen
                    BasisObjektverknuepfung obj = null;
                    BasisObjekt bo = UebergabePanel.this.hauptModul.getObjekt();
                    String messstelleBez;
                    int id;

                    List<?> messstelle = BasisObjektverknuepfung
                        .getVerknuepfungSielhaut(bo);

                    if (messstelle.size() == 1) {
                        for (int j = 0; j < messstelle.size(); j++) {
                            obj = (BasisObjektverknuepfung) messstelle.get(j);
                        }

                        id = obj.getBasisObjektByIstVerknuepftMit()
                            .getObjektid();
                        messstelleBez = AtlProbepkt.getProbepunkt(id)
                            .getAtlSielhaut().getBezeichnung();
                    } else if (messstelle.size() > 1) {
                        messstelleBez = "Nur eine verknüpfte Sielhautmessstelle zulässig";
                    } else {
                        messstelleBez = "Keine Sielhautmessstelle verknüpft";
                    }

                    // Foto wird geladen
                    if (messstelleBez != null) {
                        String imgPath = "X:/Applikationen/Anlagenkataster/SielhautBearbeiten/fotos/"
                            + messstelleBez + ".jpg";
                        File imgFile = new File(imgPath);

                        if (imgFile.canRead()) {
                            ImageIcon imgIcon = new ImageIcon(
                                imgFile.getAbsolutePath());

                            if (imgIcon.getIconWidth() > 1000) {
                                imgIcon.setImage(imgIcon.getImage()
                                    .getScaledInstance(1000, -1,
                                        Image.SCALE_FAST));
                            }

                            getFotoLabel().setIcon(null);
                            getFotoLabel().setIcon(imgIcon);
                            getFotoLabel().setText(null);
                        } else {
                            if (messstelleBez == "Keine Sielhautmessstelle verknüpft"
                                || messstelleBez == "Nur eine verknüpfte Sielhautmessstelle zulässig") {
                                getFotoLabel().setIcon(null);
                                getFotoLabel().setText(
                                    "<html><b>-  Fehler: " + messstelleBez
                                        + " -</b></html>");
                            } else {
                                getFotoLabel().setIcon(null);
                                getFotoLabel().setText(
                                    "<html><b>-  Foto " + messstelleBez
                                        + ".jpg nicht gefunden!  -</b></html>");
                            }
                        }
                    }
                }
            };
        }
        return this.fotoRtPanel;
    }

    private JLabel getFotoLabel() {
        if (this.fotoLabel == null) {
            this.fotoLabel = new JLabel(
                "<html><b>- Kein Foto verfügbar! -</b></html>");
        }

        return this.fotoLabel;
    }
}
