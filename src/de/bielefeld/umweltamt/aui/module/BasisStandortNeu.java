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
 * $Id: BasisStandortNeu.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 12.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.15  2005/09/14 11:25:37  u633d
 * - Version vom 14.9.
 *
 * Revision 1.14  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
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
 * Ein Modul zum neuen Anlegen eines Standorts.
 * @author David Klotz
 */
public class BasisStandortNeu extends AbstractModul {
	/** Logging */
    private static final Logger log = AuikLogger.getLogger();

    private JButton speichernButton;

    private JFormattedTextField hausnrEditFeld;
    private JTextField hausnrZusFeld;
    private JTextField plzFeld;
    private JTextField flurFeld;
    private JTextField flurStkFeld;
    private JFormattedTextField rechtsWFeld;
    private JFormattedTextField hochWFeld;
    private JTextField datumFeld;
    private JLabel handzeichenLabel;
    private JTextField handzeichenNeuFeld;
    private JTextField sachbe33ravFeld;
    private JTextField sachbe33heeFeld;

    private JComboBox strassenBox;
    private JComboBox gemarkungBox;
    private JComboBox standortGgBox;
    private JComboBox entwGebBox;
    private JComboBox wEinzugsGebBox;

    private String[] strassen = null;
    private BasisGemarkung[] gemarkungen = null;
    private VawsStandortgghwsg[] standortggs = null;
    private String[] entwgebiete = null;
    private VawsWassereinzugsgebiete[] wEinzugsgebiete = null;

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    public String getName() {
        return "Neuer Standort";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     */
    public String getIdentifier() {
        return "m_standort_neu";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    public String getCategory() {
        return "Betriebe";
    }

    /**
     * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
     */
    public Icon getIcon() {
        return super.getIcon("filenew32.png");
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    public JPanel getPanel() {
        if (panel == null) {
            speichernButton = new JButton("Speichern");

            JPanel buttonBar = ButtonBarFactory.buildOKBar(speichernButton);

            strassenBox = new SearchBox();
            //strassenBox.setKeySelectionManager(new MyKeySelectionManager());

            hausnrEditFeld = new IntegerField();
            hausnrZusFeld = new JTextField();
            plzFeld = new LimitedTextField(10);
            flurFeld = new LimitedTextField(50);
            flurStkFeld = new LimitedTextField(50);
            sachbe33ravFeld = new LimitedTextField(50);
            sachbe33heeFeld = new LimitedTextField(50);

            rechtsWFeld = new DoubleField(1);
            rechtsWFeld.setValue(new Float(0.0f));
            hochWFeld = new DoubleField(1);
            hochWFeld.setValue(new Float(0.0f));

            datumFeld = new JTextField();
            datumFeld.setEditable(false);
            datumFeld.setFocusable(false);

            datumFeld.setToolTipText("Wird automatisch gesetzt.");

            handzeichenLabel = new JLabel("Handzeichen:");
            handzeichenNeuFeld = new LimitedTextField(10);
            handzeichenNeuFeld.setToolTipText("Handzeichen obligatorisch!");

            gemarkungBox = new JComboBox();
            standortGgBox = new JComboBox();
            entwGebBox = new JComboBox();
            entwGebBox.setEditable(true);
            wEinzugsGebBox = new JComboBox();


            // Der folgende KeyListener wird benutzt um mit
            // Enter im Handzeichen-Feld (wenn das Feld nicht
            // leer ist) zum Speichern-Button zu springen.
            handzeichenNeuFeld.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (handzeichenNeuFeld.getText().equals("")) {
                            handzeichenLabel.setForeground(Color.RED);
                            handzeichenNeuFeld.requestFocus();
                        } else {
                            speichernButton.requestFocus();
                        }
                    }
                }
            });


            FormLayout layout = new FormLayout(
                    "right:pref, 3dlu, 50dlu:grow(0.5), 3dlu, 50dlu:grow(1.0), 5dlu, 20dlu:grow(0.2), 3dlu, 15dlu:grow(0.2), 20dlu:grow(2.5)",    // Spalten
                    "pref, " +    //1
                    "3dlu, " +    //2
                    "pref, " +    //3
                    "3dlu, " +    //4
                    "pref, " +    //5
                    "10dlu, " +    //6
                    "pref, " +    //7
                    "3dlu, " +    //8
                    "pref, " +    //9
                    "10dlu, " +    //10
                    "pref, " +    //11
                    "3dlu, " +    //12
                    "pref, " +    //13
                    "10dlu, " +    //14
                    "pref, " +    //15
                    "3dlu, " +    //16
                    "pref, " +    //17
                    "10dlu, " +    //18
                    "pref, " +    // 19
                    "3dlu, " +    // 20
                    "pref, " +    //21
                    "3dlu, " +    //22
                    "pref, " +    //23
                    "10dlu, " +    //24
                    "pref, " +    // 25
                    "3dlu, " +    // 26
                    "pref, " +    //27
                    "3dlu, " +    //28
                    "pref, " +    //29
                    "10dlu, " +    //30
            "bottom:pref:grow");    //31
            //layout.setRowGroups(new int[][]{{1,3,5,7,9,11,13,15,17,19,21,23}});

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
            builder.add(rechtsWFeld,            cc.xyw( 3, 7, 3 ));
            builder.addLabel("Hochwert:",        cc.xy(  1, 9 ));
            builder.add(hochWFeld,                cc.xyw( 3, 9, 3 ));

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
            builder.addSeparator("VAWS",         cc.xyw( 1, 19, 9));
            builder.addLabel("Standortgegebenheit:",    cc.xy( 1, 21));
            builder.add(standortGgBox,            cc.xyw( 3, 21, 3));
            builder.addLabel("W.Einzugsgebiet:",cc.xy(  1, 23));
            builder.add(wEinzugsGebBox,            cc.xyw( 3, 23, 3));

            // Revision
            builder.addSeparator("Revision",    cc.xyw(1, 25, 9));
            builder.addLabel("Datum:",            cc.xy(  1, 27 ));
            builder.add(datumFeld,                cc.xyw( 3, 27, 3 ));
            builder.add(handzeichenLabel,         cc.xy(  1, 29));
            builder.add(handzeichenNeuFeld,        cc.xyw( 3, 29, 3 ));

            builder.add(buttonBar,                 cc.xyw( 1, 31, 9));

            StandortNeuListener listener = new StandortNeuListener();

            speichernButton.addActionListener(listener);

            strassenBox.addActionListener(listener);

            panel = builder.getPanel();
        }
        return panel;
    }

    public void show() {
        super.show();
        clearForm();
    }
    /**
     * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
     * Speichern die Werte des Formulars in einen neuen Standort.
     * @throws HibernateException Wenn beim Speichern ein Fehler auftritt
     */
    private void doSave() {
        // Eingaben überprüfen:
        // Das Handzeichen darf nicht leer sein
        if (handzeichenNeuFeld.getText().equals("")) {
            handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
            handzeichenNeuFeld.requestFocus();
            log.debug("(doSave) " + "Neues Handzeichen erforderlich!");
        } else {
            // Wenn die Eingaben korrekt sind

            setAllEnabled(false);

            // Neues Standortobjekt erzeugen
            BasisStandort bsta = new BasisStandort();

            // Straße:
            String stra = (String) strassenBox.getSelectedItem();
            bsta.setStrasse(stra);

            // Hausnummer:
            Integer hausnr = ((IntegerField)hausnrEditFeld).getIntValue();
            bsta.setHausnr(hausnr);

            // Hausnummer-Zusatz:
            String hausnrZus = hausnrZusFeld.getText();
            if (hausnrZus.equals("")) {
                bsta.setHausnrzus(null);
            } else {
                bsta.setHausnrzus(hausnrZus);
            }

            // PLZ:
            String plz = plzFeld.getText().trim();
            if (plz.equals("")) {
                bsta.setPlz(null);
            } else {
                bsta.setPlz(plz);
            }

            // Gemarkung
            BasisGemarkung bgem = (BasisGemarkung) gemarkungBox.getSelectedItem();
            bsta.setBasisGemarkung(bgem);

            // Standortgg
            VawsStandortgghwsg stgg = (VawsStandortgghwsg) standortGgBox.getSelectedItem();
            bsta.setVawsStandortgghwsg(stgg);

            // Einzugsgebiet
            String ezgb = (String) entwGebBox.getSelectedItem();
            // Nötig, weil getSelectedItem bei editierbarer ComboBox auch NULL liefern kann
            if (ezgb != null) {
                // Weil ich bis jetzt noch keine LimitedComboBox oder so habe...
                if (ezgb.length() > 10) {
                    // ... kürze ich hier den String auf 10 Zeichen
                    ezgb = ezgb.substring(0, 10);
                }
                ezgb = ezgb.trim();
            }
            bsta.setEntgebid(ezgb);

            // VAWS-Einzugsgebiet
            VawsWassereinzugsgebiete wezg = (VawsWassereinzugsgebiete) wEinzugsGebBox.getSelectedItem();
            bsta.setVawsWassereinzugsgebiete(wezg);

            // Flur
            String flur = flurFeld.getText().trim();
            if (flur.equals("")) {
                bsta.setFlur(null);
            } else {
                bsta.setFlur(flur);
            }

            // Flurstueck
            String flurstk = flurStkFeld.getText().trim();
            if (flurstk.equals("")) {
                bsta.setFlurstueck(null);
            } else {
                bsta.setFlurstueck(flurstk);
            }

            // Rechtswert
            Float rechtsWert = ((DoubleField)rechtsWFeld).getFloatValue();
            bsta.setRechtswert(rechtsWert);

            // Hochwert
            Float hochWert = ((DoubleField)hochWFeld).getFloatValue();
            bsta.setHochwert(hochWert);

            bsta.setRevidatum(new Date());
            bsta.setRevihandz(handzeichenNeuFeld.getText().trim());

            bsta = BasisStandort.saveStandort(bsta);

            if (bsta != null) {
                frame.changeStatus("Neuer Standort "+bsta.getStandortid()+" erfolgreich gespeichert!", HauptFrame.SUCCESS_COLOR);

                // Wenn wir vom Objekt anlegen kommen,
                if (manager.getSettingsManager().getBoolSetting("auik.imc.return_to_objekt")) {
                    manager.getSettingsManager().setSetting("auik.imc.use_standort", bsta.getStandortid().intValue(), false);
                    manager.getSettingsManager().removeSetting("auik.imc.return_to_objekt");
                    // ... kehren wir direkt dorthin zurück:
                    manager.switchModul("m_objekt_bearbeiten");
                } else {
                    // Sonst einfach das Formular zurücksetzen
                    clearForm();
                }
            } else {
                frame.changeStatus("Konnte Standort nicht speichern!", Color.RED);
                log.debug("(BasisStandortNeu.doSave) " + "Konnte nicht speichern");
            }
        }
    }

    private void clearForm() {
        setAllEnabled(false);
        //frame.changeStatus("Beschäftigt...");

        SwingWorkerVariant worker = new SwingWorkerVariant(strassenBox) {

            protected void doNonUILogic() throws RuntimeException {
                try {
                    if (strassen == null) {
                        strassen = BasisStrassen.getStrassen();
                    }
                    if (gemarkungen == null) {
                        gemarkungen = BasisGemarkung.getGemarkungen();
                    }
                    if (standortggs == null) {
                        standortggs = VawsStandortgghwsg.getStandortGg();
                    }
                    if (entwgebiete == null) {
                        entwgebiete = BasisStandort.getEntwGebiete();
                    }
                    if (wEinzugsgebiete == null) {
                        wEinzugsgebiete = VawsWassereinzugsgebiete.getWEinzugsgebiete();
                    }
                } catch (HibernateException e) {
                    throw new RuntimeException(e);
                }
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                if (strassen != null) {
                    //strassenBox = new SearchBox(strassen);
                    strassenBox.setModel(new DefaultComboBoxModel(strassen));
                }
                if (gemarkungen != null) {
                    gemarkungBox.setModel(new DefaultComboBoxModel(gemarkungen));
                }
                if (standortggs != null) {
                    standortGgBox.setModel(new DefaultComboBoxModel(standortggs));
                }

                if (entwgebiete != null) {
                    entwGebBox.setModel(new DefaultComboBoxModel(entwgebiete));
                }

                if (wEinzugsgebiete != null) {
                    wEinzugsGebBox.setModel(new DefaultComboBoxModel(wEinzugsgebiete));
                }

                hausnrEditFeld.setValue(new Integer(0));
                hausnrZusFeld.setText("");
                plzFeld.setText("");
                flurFeld.setText("");
                flurStkFeld.setText("");
                rechtsWFeld.setValue(new Float(0.0f));
                hochWFeld.setValue(new Float(0.0f));
                datumFeld.setText(AuikUtils.getStringFromCurrentDate());
                handzeichenNeuFeld.setText("");
                handzeichenLabel.setForeground(panel.getForeground());

                setAllEnabled(true);
            }
        };
        worker.start();

        log.debug("(" + this.getIdentifier()+".clearForm )" + "Formular zurückgesetzt");
    }

    /**
     * Aktiviert oder deaktiviert alle Felder im Formular.
     * @param enabled <code>true</true>, wenn die Felder aktiviert werden sollen, sonst <code>false</code>
     */
    private void setAllEnabled(boolean enabled) {
        speichernButton.setEnabled(enabled);
        strassenBox.setEnabled(enabled);
        hausnrEditFeld.setEditable(enabled);
        hausnrZusFeld.setEditable(enabled);
        plzFeld.setEditable(enabled);
        gemarkungBox.setEnabled(enabled);
        standortGgBox.setEnabled(enabled);
        entwGebBox.setEnabled(enabled);
        entwGebBox.setEditable(enabled);
        wEinzugsGebBox.setEnabled(enabled);
        flurFeld.setEditable(enabled);
        flurStkFeld.setEditable(enabled);
        rechtsWFeld.setEditable(enabled);
        hochWFeld.setEditable(enabled);
        handzeichenNeuFeld.setEditable(enabled);
    }

    /**
     * Ein Listener für die Events des "Neuer Standort"-Moduls.
     * @author David Klotz
     */
    private final class StandortNeuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == speichernButton) {
                log.debug("(" + BasisStandortNeu.this.getIdentifier() + ") "
                		+ "Speichern gedrückt!");
                doSave();
            } else if (e.getSource() == strassenBox) {
                // Wenn wir eine Straße auswählen, wird die PLZ upgedatet
                BasisStrassen stra = BasisStrassen.getStrasseByName((String) strassenBox.getSelectedItem());
                if (stra != null) {
                    // Natürlich nur, wenn die Straße eine eindeutige PLZ hat
                    if (stra.getPlz() != null) {
                        frame.clearStatus();
                        plzFeld.setText(stra.getPlz().toString());
                    } else {
                        frame.changeStatus("Die Straße '"+stra+"' hat keine eindeutige PLZ, bitte selbst eintragen!");
                        plzFeld.setText("");
                    }
                }
            }
        }
    }
}
