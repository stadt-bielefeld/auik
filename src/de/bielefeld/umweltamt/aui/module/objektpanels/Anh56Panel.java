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
 * Datei:
 * $Id: Anh56Panel.java,v 1.1.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2010/02/04 13:34:38  u633d
 * Verkn�pfte Objekte
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/08/17 05:45:31  u633d
 * - Anhang 56 erstellt
 *
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
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das Panel zum Bearbeiten von Druckereien
 * @author u633d
 */
public class Anh56Panel extends JPanel{
    private static final long serialVersionUID = -6981678796941528077L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;

    // Widgets
    private JTextField druckverfahrenFeld = null;
    private JTextField verbrauchFeld = null;
    private JTextField entsorgungFeld = null;
    private TextFieldDateChooser gen59Datum = null;
    private TextFieldDateChooser gen58Datum = null;
    private JCheckBox abaCheck = null;
    private JCheckBox genpflichtCheck = null;
    private JCheckBox abwasseranfallCheck = null;
    private JTextArea BemerkungenArea = null;

    private JButton saveAnh56Button = null;

    // Daten
    private Anh56Fachdaten fachdaten = null;

    // Objektverknuepfer
    private ObjektVerknuepfungModel objektVerknuepfungModel;
    private JTable objektverknuepfungTabelle = null;
    private JButton selectObjektButton = null;
    private Action verknuepfungLoeschAction;
    private JPopupMenu verknuepfungPopup;

    public Anh56Panel(BasisObjektBearbeiten hauptModul) {
        name = "Druckerei";
        this.hauptModul = hauptModul;

        FormLayout layout = new FormLayout (
                "r:90dlu, 5dlu, 95dlu, 5dlu, r:0dlu, 0dlu, 90dlu", // Spalten
                "");


        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.appendSeparator("Fachdaten");
        builder.append("Druckverfahren:", getDruckverfahrenFeld());
        builder.append("", getAbwasseranfallCheck());
        builder.nextLine();
        builder.append("Wasserverbrauch:", getVerbrauchFeld());
        builder.append("", getAbaCheck());
        builder.nextLine();
        builder.append("Entsorgung:", getEntsorgungFeld());
        builder.append("", getGenpflichtCheck());
        builder.nextLine();
        builder.append("Datum 58er Genehmigung:", getGen58Datum());
        builder.nextLine();
        builder.append("Datum 59er Genehmigung:", getGen59Datum());
        builder.nextLine();

        builder.appendSeparator("Bemerkungen");
        builder.appendRow("3dlu");
        builder.nextLine(2);
        JScrollPane bemerkungsScroller = new JScrollPane(getBemerkungenArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

        JPanel buttonBar = ButtonBarFactory.buildRightAlignedBar(
                getSelectObjektButton(), getSaveAnh56Button());


        //JPanel buttonBar = ButtonBarFactory.buildOKBar(getSaveAnh56Button());
        builder.append(buttonBar, 7);

    }

    public void completeObjekt() {
        if (hauptModul.isNew() || fachdaten == null) {
            // Neues Anhang 56 Objekt erzeugen
            fachdaten = new Anh56Fachdaten();
            // Objekt_Id setzen
            fachdaten.setBasisObjekt(hauptModul.getObjekt());

            // Anhang 56 Objekt speichern
            Anh56Fachdaten.saveFachdaten(fachdaten);
            log.debug("Neues Anh 56 Objekt " + fachdaten + " gespeichert.");
        }
    }

    private boolean saveAnh56Daten() {
        boolean success;

        String bemerkungen = BemerkungenArea.getText();
        if ("".equals(bemerkungen)) {
            fachdaten.setBemerkungen(null);
        } else {
            fachdaten.setBemerkungen(bemerkungen);
        }

        String druckverf = druckverfahrenFeld.getText();
        if ("".equals(druckverf)) {
            fachdaten.setDruckverfahren(null);
        } else {
            fachdaten.setDruckverfahren(druckverf);
        }

        String verbrauch = verbrauchFeld.getText();
        if ("".equals(verbrauch)) {
            fachdaten.setVerbrauch(null);
        } else {
            fachdaten.setVerbrauch(verbrauch);
        }

        String entsorgung = entsorgungFeld.getText();
        if ("".equals(entsorgung)) {
            fachdaten.setEntsorgung(null);
        } else {
            fachdaten.setEntsorgung(entsorgung);
        }

        Date gen58 = gen58Datum.getDate();
        fachdaten.setGen58(gen58);

        Date gen59 = gen59Datum.getDate();
        fachdaten.setGen59(gen59);


        if (getAbaCheck().isSelected())  {
            fachdaten.setAba(true);
        } else {
            fachdaten.setAba(false);
        }

        if (getGenpflichtCheck().isSelected())  {
            fachdaten.setGenpflicht(true);
        } else {
            fachdaten.setGenpflicht(false);
        }

        if (getAbwasseranfallCheck().isSelected())  {
            fachdaten.setAbwasseranfall(true);
        } else {
            fachdaten.setAbwasseranfall(false);
        }




        success = Anh56Fachdaten.saveFachdaten(fachdaten);
        if (success) {
            log.debug("Anh 56 Objekt " + fachdaten.getObjektid()
            		+ " gespeichert.");
        } else {
            log.debug("Anh 56 Objekt " + fachdaten
            		+ " konnte nicht gespeichert werden!");
        }
        return success;
    }

    public void enableAll(boolean enabled) {

        getBemerkungenArea().setEnabled(enabled);
        getDruckverfahrenFeld().setEnabled(enabled);
        getVerbrauchFeld().setEnabled(enabled);
        getEntsorgungFeld().setEnabled(enabled);

        getGen58Datum().setEnabled(enabled);
        getGen59Datum().setEnabled(enabled);

        getAbaCheck().setEnabled(enabled);
        getGenpflichtCheck().setEnabled(enabled);
        getAbwasseranfallCheck().setEnabled(enabled);

    }

    public void clearForm() {

        getBemerkungenArea().setText(null);
        getDruckverfahrenFeld().setText(null);
        getVerbrauchFeld().setText(null);
        getEntsorgungFeld().setText(null);

        getGen58Datum().setDate(null);
        getGen59Datum().setDate(null);

        getAbaCheck().setSelected(false);
        getGenpflichtCheck().setSelected(false);
        getAbwasseranfallCheck().setSelected(false);

    }

    public void updateForm() throws RuntimeException {

    	if (fachdaten != null) {
	        if (fachdaten.getBemerkungen() != null) {
	            getBemerkungenArea().setText(fachdaten.getBemerkungen());
	        }
	        if (fachdaten.getDruckverfahren() != null) {
	            getDruckverfahrenFeld().setText(fachdaten.getDruckverfahren());
	        }
	        if (fachdaten.getVerbrauch() != null) {
	            getVerbrauchFeld().setText(fachdaten.getVerbrauch());
	        }
	        if (fachdaten.getEntsorgung() != null) {
	            getEntsorgungFeld().setText(fachdaten.getEntsorgung());
	        }

	        if (fachdaten.getGen58() != null) {
	            getGen58Datum().setDate(fachdaten.getGen58());
	        }
	        if (fachdaten.getGen59() != null) {
	            getGen59Datum().setDate(fachdaten.getGen59());
	        }

	        if (fachdaten.getAba() != null) {
	            if (fachdaten.getAba() == true) {
	                getAbaCheck().setSelected(true);
	            }
	            else {
	                getAbaCheck().setSelected(false);
	            }
	        }
	        if (fachdaten.getGenpflicht() != null) {
	            if (fachdaten.getGenpflicht() == true) {
	                getGenpflichtCheck().setSelected(true);
	            }
	            else {
	                getGenpflichtCheck().setSelected(false);
	            }
	        }
	        if (fachdaten.getAbwasseranfall() != null) {
	            if (fachdaten.getAbwasseranfall() == true) {
	                getAbwasseranfallCheck().setSelected(true);
	            }
	            else {
	                getAbwasseranfallCheck().setSelected(false);
	            }
	            objektVerknuepfungModel.setObjekt(hauptModul.getObjekt());
	        }
	    }
    }

    public void fetchFormData() throws RuntimeException {
        fachdaten = Anh56Fachdaten.getAnh56ByObjekt(hauptModul.getObjekt());
        log.debug("Anhang 56 Objekt aus DB geholt: ID" + fachdaten);
    }

    private JButton getSaveAnh56Button() {
        if (saveAnh56Button == null) {
            saveAnh56Button = new JButton("Speichern");

            saveAnh56Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enableAll(false);
                    if (saveAnh56Daten()) {
                        hauptModul.getFrame().changeStatus("Anh 56 Objekt "+fachdaten.getObjektid()+" erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
                    } else {
                        hauptModul.getFrame().changeStatus("Fehler beim Speichern des Anh 56 Objekt!", HauptFrame.ERROR_COLOR);
                    }

                    hauptModul.fillForm();
                }
            });
        }
        return saveAnh56Button;
    }

    @Override
    public String getName() {
        return name;
    }

    private JCheckBox getAbaCheck() {
        if (abaCheck == null) {
            abaCheck = new JCheckBox("Abwasserbehandlung");
        }
        return abaCheck;
    }

    private JCheckBox getAbwasseranfallCheck() {
        if (abwasseranfallCheck == null) {
            abwasseranfallCheck = new JCheckBox("Abwasseranfall");
        }
        return abwasseranfallCheck;
    }
    private JTextField getDruckverfahrenFeld() {
        if (druckverfahrenFeld == null) {
            druckverfahrenFeld = new LimitedTextField(150);
        }
        return druckverfahrenFeld;
    }
    private JTextField getEntsorgungFeld() {
        if (entsorgungFeld == null) {
            entsorgungFeld = new LimitedTextField(150);
        }
        return entsorgungFeld;
    }
    private TextFieldDateChooser getGen58Datum() {
        if (gen58Datum == null) {
            gen58Datum = new TextFieldDateChooser();
        }
        return gen58Datum;
    }
    private TextFieldDateChooser getGen59Datum() {
        if (gen59Datum == null) {
            gen59Datum = new TextFieldDateChooser();
        }
        return gen59Datum;
    }
    private JCheckBox getGenpflichtCheck() {
        if (genpflichtCheck == null) {
            genpflichtCheck = new JCheckBox("Genehmigungspflicht");
        }
        return genpflichtCheck;
    }
    private JTextField getVerbrauchFeld() {
        if (verbrauchFeld == null) {
            verbrauchFeld = new LimitedTextField(150);
        }
        return verbrauchFeld;
    }
    private JTextArea getBemerkungenArea() {
        if (BemerkungenArea == null) {
            BemerkungenArea = new LimitedTextArea(255);
            BemerkungenArea.setLineWrap(true);
            BemerkungenArea.setWrapStyleWord(true);
        }
        return BemerkungenArea;
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
                private static final long serialVersionUID = -7858293832318327844L;

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

