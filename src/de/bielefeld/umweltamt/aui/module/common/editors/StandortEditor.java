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

package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisGemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsStandortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWassereinzugsgebiete;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.SearchBox;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Dialog zum Bearbeiten eines Standorts.
 * @author David Klotz
 */
public class StandortEditor extends AbstractBaseEditor {
    private static final long serialVersionUID = 2023212804506559226L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    // Für die Comboboxen beim Bearbeiten
    private static String[] strassen = null;
    private static BasisGemarkung[] gemarkungen = null;
    private static VawsStandortgghwsg[] standortggs = null;
    private static String[] entwgebiete = null;
    private static VawsWassereinzugsgebiete[] wEinzugsgebiete = null;

    private JFormattedTextField hausnrEditFeld;
    private JTextField hausnrZusFeld;
    private JTextField plzFeld;
    private JTextField flurFeld;
    private JTextField flurStkFeld;
    private JFormattedTextField rechtsWFeld;
    private JFormattedTextField hochWFeld;
    private JButton ausAblageButton;
    private JTextField datumFeld;
    private JLabel handzeichenLabel;
    private JTextField handzeichenAltFeld;
    private JTextField handzeichenNeuFeld;
    private JTextField sachbe33ravFeld;
    private JTextField sachbe33heeFeld;
    private JFormattedTextField wassermengeFeld;

    private JComboBox strassenBox;
    private JComboBox gemarkungBox;
    private JComboBox standortGgBox;
    private JComboBox entwGebBox;
    private JComboBox wEinzugsGebBox;

    /**
     * Erzeugt einen neuen Dialog zum Bearbeiten eines Standorts.
     */
    public StandortEditor(BasisStandort bsta, HauptFrame owner) {
        super("Standort ("+ bsta.getId() +")", bsta, owner);
    }

    @Override
    protected JComponent buildContentArea() {
        strassenBox = new SearchBox();

        hausnrEditFeld = new IntegerField();
        hausnrZusFeld = new JTextField();
        plzFeld = new LimitedTextField(10, "");

        flurFeld = new LimitedTextField(50);
        flurStkFeld = new LimitedTextField(50);
        sachbe33ravFeld = new LimitedTextField(50);
        sachbe33heeFeld = new LimitedTextField(50);
        wassermengeFeld = new IntegerField();

        rechtsWFeld = new DoubleField(1);
        hochWFeld = new DoubleField(1);

        datumFeld = new JTextField();
        datumFeld.setEditable(false);
        datumFeld.setFocusable(false);
        datumFeld.setToolTipText("Wird bei Änderungen automatisch aktualisiert.");

        handzeichenLabel = new JLabel("Handzeichen:");
        handzeichenAltFeld = new JTextField();
        handzeichenAltFeld.setEditable(false);
        handzeichenAltFeld.setFocusable(false);
        handzeichenAltFeld.setToolTipText("Handzeichen der letzten Revision");
        handzeichenNeuFeld = new LimitedTextField(10);
        handzeichenNeuFeld.setToolTipText("Neues Handzeichen bei Änderungen obligatorisch!");

        gemarkungBox = new JComboBox();
        standortGgBox = new JComboBox();
        entwGebBox = new JComboBox();
        entwGebBox.setEditable(true);
        wEinzugsGebBox = new JComboBox();

        // Der folgende KeyListener wird benutzt um mit Escape
        // das Bearbeiten abzubrechen und bei Enter im
        // Handzeichen-Feld (wenn das Feld nicht leer ist) zum
        // Speichern-Button zu springen.
        KeyListener escEnterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getSource().equals(handzeichenNeuFeld)) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (handzeichenNeuFeld.getText().equals("")) {
                            handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
                            handzeichenNeuFeld.requestFocus();
                        } else {
                            button1.requestFocus();
                        }
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    doCancel();
                }
            }
        };
        strassenBox.addKeyListener(escEnterListener);
        hausnrEditFeld.addKeyListener(escEnterListener);
        hausnrZusFeld.addKeyListener(escEnterListener);
        plzFeld.addKeyListener(escEnterListener);
        gemarkungBox.addKeyListener(escEnterListener);
        standortGgBox.addKeyListener(escEnterListener);
        entwGebBox.addKeyListener(escEnterListener);
        wEinzugsGebBox.addKeyListener(escEnterListener);
        flurFeld.addKeyListener(escEnterListener);
        flurStkFeld.addKeyListener(escEnterListener);
        rechtsWFeld.addKeyListener(escEnterListener);
        hochWFeld.addKeyListener(escEnterListener);
        handzeichenNeuFeld.addKeyListener(escEnterListener);
        sachbe33ravFeld.addKeyListener(escEnterListener);
        sachbe33heeFeld.addKeyListener(escEnterListener);
        wassermengeFeld.addKeyListener(escEnterListener);

        String linkeSpalten = "r:p, 3dlu, 50dlu:g, 3dlu, 50dlu:g, 5dlu, 20dlu:g(0.2), 3dlu, 15dlu:g(0.2)";
        String rechteSpalten = "r:p, 3dlu, 50dlu:g, 3dlu, 50dlu:g";
        int rS = 10;

        FormLayout layout = new FormLayout(
                linkeSpalten + ", 10dlu, " + rechteSpalten,    // Spalten
                "pref, " +    //1
                "3dlu, " +    //2
                "pref, " +    //3
                "3dlu, " +    //4
                "pref, " +    //5
                "10dlu, " +    //6
                "pref, " +    //7
                "3dlu, " +    //8
                "pref, " +    //9
                "3dlu, " +    //10
                "pref, " +    //11
                "3dlu, " +    //12
                "pref, " +    //13
                "10dlu, " +    //14
                "pref, " +    //15
                "3dlu, " +    //16
                "pref, " +    //17
                "3dlu, " +    //18
                "pref, " +    //19
                "10dlu, " +    //20
                "pref, " +    //21
                "3dlu, " +    //22
                "pref, " +    //23
                "10dlu, " +    //24
        "bottom:pref:grow");    //25

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        // Adresse
        builder.addSeparator("Stammdaten",     cc.xyw( 1, 1, 9));
        builder.addLabel("Straße:",            cc.xy(  1, 3 ));
        builder.add(strassenBox,            cc.xyw( 3, 3 , 3 ));
        builder.add(hausnrEditFeld,            cc.xy(  7, 3 ));
        builder.add(hausnrZusFeld,            cc.xy(  9, 3 ));
        builder.addLabel("PLZ:",            cc.xy(  1, 5 ));
        builder.add(plzFeld,                cc.xy(  3, 5 ));

        // Koordinaten
        builder.addLabel("Rechtswert:",        cc.xy(  1, 7 ));
        builder.add(rechtsWFeld,            cc.xy( 3, 7 ));
        builder.addLabel("Hochwert:",        cc.xy(  1, 9 ));
        builder.add(hochWFeld,                cc.xy( 3, 9 ));
        builder.add(getAusAblageButton(),    cc.xywh( 5, 7, 1, 3 ));

        //
        builder.addLabel("Gemarkung:",        cc.xy(  1, 11 ));
        builder.add(gemarkungBox,            cc.xyw( 3, 11, 3 ));
        builder.addLabel("Entwässerungsgebiet:",    cc.xy(  1, 13 ));
        builder.add(entwGebBox,                cc.xyw( 3, 13, 3 ));

        // Flur
        builder.addLabel("Flur:",            cc.xy(  1, 15 ));
        builder.add(flurFeld,                cc.xy(  3, 15 ));
        builder.addLabel("Flurstück:",        cc.xy(  1, 17 ));
        builder.add(flurStkFeld,            cc.xy(  3, 17 ));

        // VAWS
        builder.addSeparator("VAWS",         cc.xyw(1+rS, 1, 5));
        builder.addLabel("Standortgegebenheit:",    cc.xy( 1+rS, 3));
        builder.add(standortGgBox,            cc.xyw( 3+rS, 3, 3));
        builder.addLabel("W.Einzugsgebiet:",cc.xy(  1+rS, 5 ));
        builder.add(wEinzugsGebBox,            cc.xyw( 3+rS, 5, 3));

        // Indirekteinleiter
        builder.addSeparator("Indirekteinleiter",         cc.xyw(1+rS, 9, 5));
        builder.addLabel("Sachbearbeiter Rav.:",    cc.xy( 1+rS, 11));
        builder.add(sachbe33ravFeld,            cc.xyw( 3+rS, 11, 3));
        builder.addLabel("Sachbearbeiter Heepen:",cc.xy(  1+rS, 13 ));
        builder.add(sachbe33heeFeld,            cc.xyw( 3+rS, 13, 3));
        builder.addLabel("Wasserverbrauch:",cc.xy(  1+rS, 15 ));
        builder.add(wassermengeFeld,            cc.xyw( 3+rS, 15, 3));

        // Letzte Revision
        builder.addSeparator("Letzte Revision",    cc.xyw(1, 19, 5));
        builder.addLabel("Datum:",            cc.xy(  1, 21 ));
        builder.add(datumFeld,                cc.xyw( 3, 21, 3 ));
        builder.addLabel("Handzeichen:",    cc.xy(  1, 23 ));
        builder.add(handzeichenAltFeld,        cc.xyw( 3, 23, 3 ));

        // Neue Revision
        builder.addSeparator("Neue Revision",cc.xyw(1+rS, 19, 5));
        builder.add(handzeichenLabel,         cc.xy(  1+rS, 21));
        builder.add(handzeichenNeuFeld,        cc.xyw( 3+rS, 21, 3 ));

        strassenBox.addActionListener(new ActionListener() {
            private int strassenCounter = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == strassenBox) {
                    if (plzFeld.getText().equals("") || strassenCounter > 0) {
                        // Wenn wir eine Straße auswählen, wird die PLZ upgedatet.
                        BasisStrassen stra = DatabaseQuery.findStrasse(
                            (String) strassenBox.getSelectedItem());
                        if (stra != null) {
                            if (stra.getPlz() != null) {
                                frame.clearStatus();
                                plzFeld.setText(stra.getPlz().toString());
                                //AUIKataster.debugOutput("Die Straße '"+stra+"' hat die eindeutige PLZ: " + stra.getPlz(), "strassenBox.focusLost("+e.getActionCommand()+", Nr: "+strassenCounter+")");
                            } else {
                                frame.changeStatus("Die Straße '"+stra+"' hat keine eindeutige PLZ, bitte selbst eintragen!");
                                //AUIKataster.debugOutput("Die Straße '"+stra+"' hat keine eindeutige PLZ, bitte selbst eintragen!", "strassenBox.focusLost("+e.getActionCommand()+", Nr: "+strassenCounter+")");
                                plzFeld.setText("");
                            }
                        }
                    }
                    strassenCounter++;
                }
            }
        });

        return builder.getPanel();
    }

    @Override
    protected void fillForm() {
        frame.changeStatus("Beschäftigt...");

        SwingWorkerVariant worker = new SwingWorkerVariant(this) {

            @Override
            protected void doNonUILogic() throws RuntimeException {
                if (strassen == null) {
                    strassen = DatabaseQuery.getStrassen();
                }
                if (gemarkungen == null) {
                    gemarkungen = DatabaseQuery.getBasisGemarkungen();
                }
                if (standortggs == null) {
                    standortggs = DatabaseQuery.getVawsStandortgghwsg();
                }
                if (entwgebiete == null) {
                    entwgebiete = DatabaseQuery.getEntwaesserungsgebiete();
                }
                if (wEinzugsgebiete == null) {
                    wEinzugsgebiete = VawsWassereinzugsgebiete.getWEinzugsgebiete();
                }
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                if (strassen != null) {
                    strassenBox.setModel(new DefaultComboBoxModel(strassen));
                    strassenBox.setSelectedItem(getStandort().getStrasse());
                }
                if (gemarkungen != null) {
                    gemarkungBox.setModel(new DefaultComboBoxModel(gemarkungen));
                    gemarkungBox.setSelectedItem(getStandort().getBasisGemarkung());
                }
                if (standortggs != null) {
                    standortGgBox.setModel(new DefaultComboBoxModel(standortggs));
                    standortGgBox.setSelectedItem(getStandort().getVawsStandortgghwsg());
                }

                if (entwgebiete != null) {
                    entwGebBox.setModel(new DefaultComboBoxModel(entwgebiete));
                    entwGebBox.setSelectedItem(getStandort().getEntgebid());
                }

                if (wEinzugsgebiete != null) {
                    wEinzugsGebBox.setModel(new DefaultComboBoxModel(wEinzugsgebiete));
                    wEinzugsGebBox.setSelectedItem(getStandort().getVawsWassereinzugsgebiete());
                }

                hausnrEditFeld.setValue(getStandort().getHausnr());
                hausnrZusFeld.setText(getStandort().getHausnrzus());
                if (getStandort().getPlz() != null) {
                    plzFeld.setText(getStandort().getPlz());
                }
                flurFeld.setText(getStandort().getFlur());
                flurStkFeld.setText(getStandort().getFlurstueck());
                rechtsWFeld.setValue(getStandort().getRechtswert());
                hochWFeld.setValue(getStandort().getHochwert());
                Date datum = getStandort().getRevidatum();
                datumFeld.setText(AuikUtils.getStringFromDate(datum));
                handzeichenAltFeld.setText(getStandort().getRevihandz());
                sachbe33ravFeld.setText(getStandort().getSachbe33rav());
                sachbe33heeFeld.setText(getStandort().getSachbe33hee());
                wassermengeFeld.setValue(getStandort().getWassermenge());

                frame.clearStatus();
            }
        };
        worker.start();
    }

    @Override
    protected boolean canSave() {
        // Eingaben überprüfen:
        // Das Handzeichen darf nicht leer sein
        if (handzeichenNeuFeld.getText() == null || handzeichenNeuFeld.getText().equals("")) {
            handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
            handzeichenNeuFeld.requestFocus();
            frame.changeStatus("Neues Handzeichen erforderlich!", HauptFrame.ERROR_COLOR);
            //AUIKataster.debugOutput("Neues Handzeichen erforderlich!", "doSave");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
     */
    @Override
    protected boolean doSave() {
        // Straße:
        String stra = (String) strassenBox.getSelectedItem();
        getStandort().setStrasse(stra);

        // Hausnummer:
        Integer hausnr = ((IntegerField)hausnrEditFeld).getIntValue();
        getStandort().setHausnr(hausnr);

        // Hausnummer-Zusatz:
        String hausnrZus = hausnrZusFeld.getText();
        if ("".equals(hausnrZus)) {
            getStandort().setHausnrzus(null);
        } else {
            getStandort().setHausnrzus(hausnrZus);
        }
        // PLZ:
        String plz = plzFeld.getText().trim();
        if ("".equals(plz)) {
            getStandort().setPlz(null);
        } else {
            getStandort().setPlz(plz);
        }
        // Gemarkung
        BasisGemarkung bgem = (BasisGemarkung) gemarkungBox.getSelectedItem();
        getStandort().setBasisGemarkung(bgem);

        // Standortgg
        VawsStandortgghwsg stgg = (VawsStandortgghwsg) standortGgBox.getSelectedItem();
        getStandort().setVawsStandortgghwsg(stgg);

        // Entwässerungsgebiet
        String entgb = (String) entwGebBox.getSelectedItem();

        // Nötig, weil getSelectedItem bei editierbarer ComboBox auch NULL liefern kann
        if (entgb != null) {
            entgb = entgb.trim();
        }
        if ("".equals(entgb)) {
            getStandort().setEntgebid(null);
        } else {
            getStandort().setEntgebid(entgb);
        }

        // VAWS-Einzugsgebiet
        VawsWassereinzugsgebiete wezg = (VawsWassereinzugsgebiete) wEinzugsGebBox.getSelectedItem();
        getStandort().setVawsWassereinzugsgebiete(wezg);

        // Flur
        String flur = flurFeld.getText();
        if (flur != null) {
            flur = flur.trim();
            if (flur.equals("")) {
                getStandort().setFlur(null);
            } else {
                getStandort().setFlur(flur);
            }
        }

        // Flurstueck
        String flurstk = flurStkFeld.getText();
        if (flurstk != null) {
            if (flurstk.equals("")) {
                getStandort().setFlurstueck(null);
            } else {
                getStandort().setFlurstueck(flurstk);
            }
        }

        // Rechtswert
        Float rechtsWert = ((DoubleField)rechtsWFeld).getFloatValue();
        getStandort().setRechtswert(rechtsWert);

        // Hochwert
        Float hochWert= ((DoubleField)hochWFeld).getFloatValue();
        getStandort().setHochwert(hochWert);

        // Handzeichen
        String handzeichen = handzeichenNeuFeld.getText().trim();
        getStandort().setRevihandz(handzeichen);

        getStandort().setRevidatum(new Date());

        // Indirekteinleiter
        String sachrav = sachbe33ravFeld.getText();
        if (sachrav != null) {
            if (sachrav.equals("")) {
                getStandort().setSachbe33rav(null);
            } else {
                getStandort().setSachbe33rav(sachrav);
            }
        }

        String sachhee = sachbe33heeFeld.getText();
        if (sachhee != null) {
            if (sachhee.equals("")) {
                getStandort().setSachbe33hee(null);
            } else {
                getStandort().setSachbe33hee(sachhee);
            }
        }

        // Wassermenge:
        Integer wassermng = ((IntegerField)wassermengeFeld).getIntValue();
        getStandort().setWassermenge(wassermng);

        BasisStandort bsta = BasisStandort.merge(getStandort());
        if (bsta != null) {
            setEditedObject(bsta);
            log.debug("Änderungen gespeichert!");
            return true;
        } else {
            return false;
        }
    }

    public BasisStandort getStandort() {
        return (BasisStandort) getEditedObject();
    }

    private void readClipboard() {

        Clipboard systemClipboard;
        systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferData = systemClipboard.getContents(null);
        for (DataFlavor dataFlavor : transferData.getTransferDataFlavors()) {
            Object content = null;
            try {
                content = transferData.getTransferData(dataFlavor);
            } catch (UnsupportedFlavorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (content instanceof String) {

                String[] tmp = content.toString().split(",");
                if (tmp.length == 4) {
                    String rechtswertAusZeile = tmp[2];
                    String hochwertAusZeile = tmp[3];
                    rechtsWFeld.setText(rechtswertAusZeile.substring(0, 7));
                    hochWFeld.setText(hochwertAusZeile.substring(0, 7));
                    frame.changeStatus("Rechts- und Hochwert eingetragen",
                            HauptFrame.SUCCESS_COLOR);
                } else {
                    frame.changeStatus(
                            "Zwischenablage enthält keine verwertbaren Daten",
                            HauptFrame.ERROR_COLOR);
                }
                break;
            }
        }
    }

    public JButton getAusAblageButton() {
        if (ausAblageButton == null) {

            ausAblageButton = new JButton("aus QGis");
            ausAblageButton.setToolTipText("Rechts- und Hochwert aus Zwischenablage einfügen");
            ausAblageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    readClipboard();
                }
            });
        }

        return ausAblageButton;
    }
}
