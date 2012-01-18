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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
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
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.RetractablePanel;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Uebergabestelle"-Tab des BasisObjektBearbeiten-Moduls
 *
 * @author Gerd Genuit
 */
public class UebergabePanel extends JPanel {
	/** Logging */
    private static final Logger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    private IndeinlUebergabestelle upunkt;
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
    private AtlKlaeranlagen[] klaeranlagen = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    // Widgets für Fotopanel
    private JLabel fotoLabel;



    public UebergabePanel(BasisObjektBearbeiten hauptModul) {
        name = "Übergabestelle";
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

        builder.append(getFotoRtPanel(),8);
        builder.nextLine();

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);

        JScrollPane bemerkungsScroller = new JScrollPane(
                getUeberstBemerkungArea(),
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
                getSelectObjektButton(), getsaveUebergabeButton());

        builder.append(buttonBar, 7);
    }

    public void fetchFormData() throws RuntimeException {
        fachdaten = IndeinlUebergabestelle.getUebergabeByObjekt(hauptModul.getObjekt());
        log.debug("(UebergabePanel.fetchFormData) "
        		+ "Uebergabestelle Objekt aus DB geholt: ID" + fachdaten);
    }

    public void updateForm() throws RuntimeException {

        if (fachdaten != null) {

            if (fachdaten.getRechtswert() != null) {
                getRechtswertFeld().setText(
                        fachdaten.getRechtswert().toString());
            }
            if (fachdaten.getHochwert() != null) {
                getHochwertFeld().setText(
                        fachdaten.getHochwert().toString());
            }
            if (fachdaten.getErfassungsDatum() != null) {
                getErfassungsDatum().setDate(fachdaten.getErfassungsDatum());

            }
            if (fachdaten.getAenderungsDatum() != null) {
                getAenderungsDatum().setDate(fachdaten.getAenderungsDatum());
            }
            if (fachdaten.getErfassungsDatum() == null) {
                getErfassungsDatum().setDate(null);

            }
              if (fachdaten.getAtlKlaeranlagen() != null)
              {
                getKlaeranlageBox().setSelectedItem(fachdaten.getAtlKlaeranlagen());
            }



            if (fachdaten.getKanalart() != null) {
                if (fachdaten.getKanalart() == 1)
                    getKanalartBox().setSelectedItem("Schmutzwasser");
                else if (fachdaten.getKanalart() == 2)
                    getKanalartBox().setSelectedItem("Mischwasser");
                else if (fachdaten.getKanalart() == 3)
                    getKanalartBox().setSelectedItem("Regenwasser");
            }

            Integer rechts = ((IntegerField) rechtswertFeld).getIntValue();
            fachdaten.setRechtswert(rechts);

            Integer hoch = ((IntegerField) hochwertFeld).getIntValue();
            fachdaten.setHochwert(hoch);

            if (fachdaten.getBemerkungen() != null) {
                getUeberstBemerkungArea().setText(fachdaten.getBemerkungen());
            }
            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());

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


        String bemerkungen = ueberstBemerkungArea.getText();
        if ("".equals(bemerkungen)) {
            fachdaten.setBemerkungen(null);
        } else {
            fachdaten.setBemerkungen(bemerkungen);
        }

        Date erf = erfassungsDatum.getDate();
        fachdaten.setErfassungsDatum(erf);

        Date aend = aenderungsDatum.getDate();
        fachdaten.setAenderungsDatum(aend);

        Integer rechts = ((IntegerField) rechtswertFeld).getIntValue();
        fachdaten.setRechtswert(rechts);

        Integer hoch = ((IntegerField) hochwertFeld).getIntValue();
        fachdaten.setHochwert(hoch);

        if (getKlaeranlageBox().getSelectedItem() != null) {
            fachdaten.setAtlKlaeranlagen((AtlKlaeranlagen) getKlaeranlageBox().getSelectedItem());

        }

        if (getKanalartBox().getSelectedItem() != null) {
            if (getKanalartBox().getSelectedItem() == "Schmutzwasser")
                fachdaten.setKanalart(1);
            else if (getKanalartBox().getSelectedItem() == "Mischwasser")
                fachdaten.setKanalart(2);
            else if (getKanalartBox().getSelectedItem() == "Regenwasser")
                fachdaten.setKanalart(3);
        }

        success = IndeinlUebergabestelle.saveFachdaten(fachdaten);
        if (success) {
            log.debug("(Anh40Panel.saveAnh40Daten) "
            		+ "Uebergabestelle Objekt " + fachdaten.getObjektid()
                    + " gespeichert.");
        } else {
            log.debug("(Anh40Panel.saveAnh40Daten) "
            		+ "Uebergabestelle Objekt " + fachdaten
                    + " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void completeObjekt() {
        if (hauptModul.isNew() || fachdaten == null) {
            // Neues Uebergabestelle Objekt erzeugen
            fachdaten = new IndeinlUebergabestelle();
            // Objekt_Id setzen
            fachdaten.setBasisObjekt(hauptModul.getObjekt());

            // Uebergabestelle speichern
            IndeinlUebergabestelle.saveFachdaten(fachdaten);
            log.debug("(BasisObjektBearbeiten.completeObjekt) "
            		+ "Neues Uebergabestelle Objekt " + fachdaten
                    + " gespeichert.");
        }
    }

    private JButton getsaveUebergabeButton() {
        if (saveUebergabeButton == null) {
            saveUebergabeButton = new JButton("Speichern");

            saveUebergabeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveUebergabestelleDaten()) {
                        hauptModul.getFrame().changeStatus(
                                "Uebergabestelle " + fachdaten.getObjektid()
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
        return saveUebergabeButton;
    }

    public String getName() {
        return name;
    }

    private TextFieldDateChooser getErfassungsDatum() {
        if (erfassungsDatum == null) {
            erfassungsDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return erfassungsDatum;
    }

    private TextFieldDateChooser getAenderungsDatum() {
        if (aenderungsDatum == null) {
            aenderungsDatum = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
        }
        return aenderungsDatum;
    }

    private JFormattedTextField getRechtswertFeld() {
        if (rechtswertFeld == null) {
            rechtswertFeld = new IntegerField();
        }
        return rechtswertFeld;
    }

    private JFormattedTextField getHochwertFeld() {
        if (hochwertFeld == null) {
            hochwertFeld = new IntegerField();
        }
        return hochwertFeld;
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

    private JComboBox getKlaeranlageBox() {
        if (klaeranlageBox == null) {
            klaeranlageBox = new JComboBox(AtlKlaeranlagen.getKlaeranlagen());
        }
        return klaeranlageBox;
    }

    private JComboBox getKanalartBox() {
        if (kanalartBox == null) {
            kanalartBox = new JComboBox();
            kanalartBox.addItem("Schmutzwasser");
            kanalartBox.addItem("Mischwasser");
            kanalartBox.addItem("Regenwasser");
        }
        return kanalartBox;
    }

    private JTextArea getUeberstBemerkungArea() {

        if (ueberstBemerkungArea == null) {
            ueberstBemerkungArea = new LimitedTextArea(150);
            ueberstBemerkungArea.setLineWrap(true);
            ueberstBemerkungArea.setWrapStyleWord(true);
            }
        return ueberstBemerkungArea;
    }

    // Foto
    private RetractablePanel getFotoRtPanel() {

        if (fotoRtPanel == null) {
            JPanel fotoPanel = new JPanel();

            fotoPanel.add(getFotoLabel());
            fotoPanel.setBackground(Color.WHITE);
            fotoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            fotoRtPanel = new RetractablePanel(DefaultComponentFactory.getInstance()
                    .createSeparator("Foto"),
                    fotoPanel, false, null) {
                public void opening()  {
                // Aufruf wenn FotoPanel geöffnet wird
                // Die Bezeichnung einer verknüpften Sielhautmesstelle wird ausgelesen
               //  um das passende Foto anzuzeigen
                    BasisObjektverknuepfung obj = null;
                    BasisObjekt bo = hauptModul.getObjekt();
                    String messstelleBez;
                    int id;

                    List messstelle = BasisObjektverknuepfung.getVerknuepfungSielhaut(bo);

                    if (messstelle.size()  == 1)
                    {
                        for (int j = 0; j < messstelle.size(); j++) {
                            obj = (BasisObjektverknuepfung) messstelle.get(j);
                        }

                        id  = obj.getBasisObjektByIstVerknuepftMit().getObjektid();
                        messstelleBez= AtlProbepkt.getProbepunkt(id).getAtlSielhaut().getBezeichnung();
                    }
                    else if (messstelle.size() > 1)
                    {
                        messstelleBez = "Nur eine verknüpfte Sielhautmessstelle zulässig";
                    }
                    else
                    {
                        messstelleBez = "Keine Sielhautmessstelle verknüpft";
                    }


                    // Foto wird geladen
                    if (messstelleBez != null) {
                        String imgPath ="X:/Applikationen/Anlagenkataster/SielhautBearbeiten/fotos/" + messstelleBez + ".jpg";
                        File imgFile = new File(imgPath);

                        if (imgFile.canRead()) {
                            ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());

                            if (imgIcon.getIconWidth() > 1000) {
                                imgIcon.setImage(imgIcon.getImage().getScaledInstance(1000,-1,Image.SCALE_FAST));
                            }

                            getFotoLabel().setIcon(null);
                            getFotoLabel().setIcon(imgIcon);
                            getFotoLabel().setText(null);
                        } else {
                            if (messstelleBez == "Keine Sielhautmessstelle verknüpft" || messstelleBez == "Nur eine verknüpfte Sielhautmessstelle zulässig")
                            {
                                getFotoLabel().setIcon(null);
                                getFotoLabel().setText("<html><b>-  Fehler: "+ messstelleBez +" -</b></html>");
                            }
                            else
                            {
                                getFotoLabel().setIcon(null);
                                getFotoLabel().setText("<html><b>-  Foto "+ messstelleBez +".jpg nicht gefunden!  -</b></html>");
                            }
                        }
                    }
                }
            };
        }
        return fotoRtPanel;
    }


    private JLabel getFotoLabel() {
        if (fotoLabel == null) {
            fotoLabel = new JLabel("<html><b>- Kein Foto verfügbar! -</b></html>");
        }

        return fotoLabel;
    }


}
