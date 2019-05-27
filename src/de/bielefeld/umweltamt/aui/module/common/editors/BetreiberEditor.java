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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EmptyBorder;

import org.ehcache.core.statistics.LowerCachingTierOperationsOutcome.GetAndRemoveOutcome;
import org.hibernate.criterion.MatchMode;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.Lage;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.Orte;
import de.bielefeld.umweltamt.aui.mappings.basis.Strassen;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.mappings.basis.Wirtschaftszweig;
import de.bielefeld.umweltamt.aui.module.BasisAdresseNeu;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStandorteModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.LongNameComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Ein Dialog zum Bearbeiten eines Betreibers.
 * 
 * @author David Klotz
 */
public class BetreiberEditor extends AbstractBaseEditor {
    private static final long serialVersionUID = -7058333439142990179L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    // Für die Comboboxen beim Bearbeiten

    private JLabel handzeichenLabel;
    private JLabel namenLabel;

    private JTextField anredeFeld;
    private JTextField vornamenFeld;
    private JTextField namenFeld;
    private JTextField nameZusFeld;
    private JTextField kassenzeichenFeld;
    private JTextField strasseFeld;
    private JFormattedTextField hausnrFeld;
    private JTextField hausnrZusFeld;
    private JTextField plzZsFeld;
    private JTextField plzFeld;
    private JTextField ortFeld;
    private JTextField telefonFeld;
    private JTextField telefaxFeld;
    private JTextField emailFeld;
    private JTextField betrBeaufVornameFeld;
    private JTextField betrBeaufNachnameFeld;
    private JTextField revdatumsFeld;
    private JTextField handzeichenAltFeld;
    private JTextField handzeichenNeuFeld;
    private JTextField flurFeld;
    private JTextField flurStkFeld;
    private DoubleField e32Feld;
    private DoubleField n32Feld;
    private JButton ausAblageButton;
    private JComboBox gemarkungBox;
    private JComboBox entwGebBox;
    private JComboBox standortGgBox;
    private JComboBox wEinzugsGebBox;
    private JCheckBox daten_awsvCheck;
    private JCheckBox daten_esatzungCheck;
    private JCheckBox daten_whgCheck;

    private Lage lage = null;
    private Standort standort = null;
    private Gemarkung[] gemarkungen = null;
    private String[] entwgebiete = null;
    private Standortgghwsg[] standortggs = null;
    private Wassereinzugsgebiet[] wEinzugsgebiete = null;

    private JTextArea bemerkungsArea;

    private JComboBox strassenBox;
    private JTable standorteTabelle;
    private BasisStandorteModel standorteModel;
    private JComboBox wirtschaftszweigBox;

    private Orte[] orte = null;
    private Wirtschaftszweig[] wirtschaftszweige = null;
    private String[] tabstreets = null;
    private String street = null;

    /**
     * Erzeugt einen neuen Dialog zum Bearbeiten eines Betreibers.
     */
    public BetreiberEditor(Adresse betr, HauptFrame owner) {
        super("Betreiber (" + betr.toString() + ")", betr, owner);
    }

    @Override
    protected JComponent buildContentArea() {

        anredeFeld = new LimitedTextField(100);
        vornamenFeld = new LimitedTextField(100);
        namenFeld = new LimitedTextField(100);
        nameZusFeld = new LimitedTextField(50);
        kassenzeichenFeld = new LimitedTextField(50);
        hausnrFeld = new IntegerField();
        hausnrZusFeld = new LimitedTextField(10);
        plzZsFeld = new LimitedTextField(3, "");
        plzFeld = new JTextField();
        strasseFeld = new JTextField();
        ortFeld = new JTextField();
        telefonFeld = new LimitedTextField(50);
        telefaxFeld = new LimitedTextField(50);
        emailFeld = new LimitedTextField(50);
        betrBeaufVornameFeld = new LimitedTextField(50);
        betrBeaufNachnameFeld = new LimitedTextField(50);
        flurFeld = new LimitedTextField(50);
        flurStkFeld = new LimitedTextField(50);

        e32Feld = new DoubleField(1);
        e32Feld.setValue(new Float(0.0f));
        n32Feld = new DoubleField(1);
        n32Feld.setValue(new Float(0.0f));
        gemarkungBox = new JComboBox();
        entwGebBox = new JComboBox();
        entwGebBox.setEditable(true);
        standortGgBox = new JComboBox();
        wEinzugsGebBox = new JComboBox();

        daten_awsvCheck = new JCheckBox("AwSV");
        daten_esatzungCheck = new JCheckBox("E-Satzung");
        daten_whgCheck = new JCheckBox("WHG");

        revdatumsFeld = new JTextField();
        revdatumsFeld.setEditable(false);
        revdatumsFeld.setFocusable(false);
        revdatumsFeld.setToolTipText("Wird automatisch gesetzt.");
        handzeichenAltFeld = new LimitedTextField(10, "");
        handzeichenAltFeld.setEditable(false);
        handzeichenAltFeld.setFocusable(false);

        handzeichenNeuFeld = new LimitedTextField(10, "");
        handzeichenNeuFeld.setToolTipText("Handzeichen obligatorisch!");

        bemerkungsArea = new LimitedTextArea(2000);
        bemerkungsArea.setLineWrap(true);
        bemerkungsArea.setWrapStyleWord(true);
        JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        wirtschaftszweigBox = new JComboBox();
        wirtschaftszweigBox.setRenderer(new LongNameComboBoxRenderer());

        // Der folgende KeyListener wird benutzt um bei Enter
        // im Handzeichen-Feld (wenn das Feld nicht leer ist)
        // zum Speichern-Button zu springen.
        handzeichenNeuFeld.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getSource().equals(handzeichenNeuFeld)) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (handzeichenNeuFeld.getText().equals("")) {
                            handzeichenLabel.setForeground(Color.RED);
                            handzeichenNeuFeld.requestFocus();
                        } else {
                            // speichernButton.requestFocus();
                        }
                    }
                }
            }
        });

        // Ermögliche TAB aus dem Bemerkungs-Feld zu springen
        bemerkungsScroller.getVerticalScrollBar().setFocusable(false);
        bemerkungsScroller.getHorizontalScrollBar().setFocusable(false);

        namenLabel = new JLabel("Name:");
        handzeichenLabel = new JLabel("Handzeichen:");

        //Stamdaten - Ansprechpartner
        PanelBuilder stammdaten = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                0, 0, 5, 5);
        stammdaten.addComponent(namenFeld, namenLabel);
        stammdaten.addComponent(telefonFeld, "Telefon:", true);
        stammdaten.addComponent(anredeFeld, "Anrede:");
        stammdaten.addComponent(telefaxFeld, "Telefax:", true);
        stammdaten.addComponent(vornamenFeld, "Vorname:");
        stammdaten.addComponent(emailFeld, "E-Mail:", true);
        stammdaten.addComponent(nameZusFeld, "Zusatz:");
        stammdaten.addSeparator("Ansprechpartner", true);
        stammdaten.addComponent(kassenzeichenFeld, "Kassenzeichen");
        stammdaten.addComponent(betrBeaufVornameFeld, "Vorname:", true);
        stammdaten.addComponent(wirtschaftszweigBox, "Wirtschaftszweig:");
        stammdaten.addComponent(betrBeaufNachnameFeld, "Nachname:", true);

        PanelBuilder coordPanel = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                0, 0, 5, 5);
        coordPanel.addComponent(e32Feld, "E32:", true);
        coordPanel.addComponent(n32Feld, "N32:");

        PanelBuilder qgisButton = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                0, 0, 5, 5);
        qgisButton.setWeight(0, 1);
        qgisButton.setFill(true, true);
        qgisButton.addComponent(getAusAblageButton());
        qgisButton.setWeight(0, 0);
        qgisButton.fillRow();

        //Adresse - Lage
        PanelBuilder adresseLage = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                0, 0, 5, 5);
        adresseLage.addSeparator("Adresse", true);
        adresseLage.addComponents(true, new JLabel("Ort:"), plzZsFeld, plzFeld, ortFeld);
        adresseLage.addComponents(true, new JLabel("Straße:"), strasseFeld, hausnrFeld, hausnrZusFeld);
        adresseLage.addSeparator("Lage", true);
        adresseLage.setWeightX(0.75);
        adresseLage.setInsets(5, 0, 0, 5);
        adresseLage.addComponent(coordPanel.getPanel());
        adresseLage.setFill(true, true);
        adresseLage.setWeight(0.25, 1);
        adresseLage.setInsets(5, 0, 0, 0);
        adresseLage.addComponent(qgisButton.getPanel(), true);
        adresseLage.setWeight(1, 0);
        adresseLage.setFill(true, false);

        adresseLage.addComponent(entwGebBox, "Entwässerungsgebiet:", true);
        adresseLage.addComponent(gemarkungBox, "Gemarkung:", true);
        adresseLage.addComponent(standortGgBox, "Standortgegebenheit:", true);
        adresseLage.addComponent(wEinzugsGebBox, "W.Einzugsgebiet", true);

        adresseLage.addSeparator("Bemerkungen", true);
        adresseLage.setFill(true, true); 
        adresseLage.setWeight(1, 10);
        adresseLage.addComponent(bemerkungsScroller, true);
        adresseLage.setGridHeight(1);
        adresseLage.setWeight(1, 0);
        adresseLage.setFill(true, false);

        PanelBuilder sePanel = new PanelBuilder(PanelBuilder.NORTHWEST, true, false, 1, 0, 1, 1,
                0, 0, 5, 5);
        sePanel.addSeparator("auswählen", true);
        sePanel.addComponent(getStrassenBox(), true);
        sePanel.setWeight(1, 10);
        sePanel.setGridHeight(5);
        sePanel.setFill(true, true);
        sePanel.addComponent(getStandorteScroller(), true);
        sePanel.setWeight(0, 1);
        sePanel.setGridHeight(1);
        sePanel.setFill(true, false);
        sePanel.addSeparator("Datenschutzhinweis erhalten:", true);
        sePanel.addComponent(daten_awsvCheck, true);
        sePanel.addComponent(daten_esatzungCheck, true);
        sePanel.addComponent(daten_whgCheck, true);
        sePanel.addSeparator("Letzte Revision", true);
        sePanel.addComponent(revdatumsFeld, "Datum:", true);
        sePanel.addComponent(handzeichenAltFeld, "Handzeichen:", true);
        sePanel.addSeparator("Neue Revision", true);
        sePanel.addComponent(handzeichenNeuFeld, "Handzeichen", true);

        PanelBuilder builder = new PanelBuilder(PanelBuilder.NORTHWEST, false, true, 0, 0, 1, 1,
                0, 0, 5, 10);
        builder.setEmptyBorder(15);
        builder.setPreferedSize(550, 250);
        builder.addSeparator("Stammdaten", true);
        builder.addComponent(stammdaten.getPanel(), true);
        builder.setFill(true, true);
        builder.setWeight(1,1);
        builder.addComponent(adresseLage.getPanel());
        builder.addComponent(sePanel.getPanel(), true);
        builder.fillColumn();


        BetreiberListener dialogListener = new BetreiberListener();

        strasseFeld.addActionListener(dialogListener);
        strassenBox.addActionListener(dialogListener);

        return builder.getPanel();
    }

    @Override
    protected void fillForm() {
        SwingWorkerVariant worker = new SwingWorkerVariant(this) {

            @Override
            protected void doNonUILogic() throws RuntimeException {
                if (wirtschaftszweige == null) {
                    wirtschaftszweige = DatabaseQuery.getWirtschaftszweig();
                }
                if (tabstreets == null) {
                    tabstreets = DatabaseQuery.getTabStreets();
                }
                if (gemarkungen == null) {
                    gemarkungen = DatabaseQuery.getGemarkungen();
                }
                if (standortggs == null) {
                    standortggs = DatabaseQuery.getStandortgghwsg();
                }
                if (entwgebiete == null) {
                    entwgebiete = DatabaseQuery.getEntwaesserungsgebiete();
                }
                if (wEinzugsgebiete == null) {
                    wEinzugsgebiete = DatabaseQuery.getWassereinzugsgebiet();
                }
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {

                if (tabstreets != null) {
                    strassenBox.setModel(new DefaultComboBoxModel(tabstreets));
                }
                if (standorteTabelle != null) {

                    standorteModel.setStrasse(null);
                    standorteModel.updateList();
                    standorteTabelle.setModel(standorteModel);

                    standorteTabelle.getColumnModel().getColumn(0).setPreferredWidth(10);
                    standorteTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
                    standorteTabelle.getColumnModel().getColumn(2).setPreferredWidth(10);
                    standorteTabelle.getColumnModel().getColumn(3).setPreferredWidth(7);

                }

                if (wirtschaftszweige != null) {
                    wirtschaftszweigBox.setModel(new DefaultComboBoxModel(wirtschaftszweige));
                    wirtschaftszweigBox.setSelectedItem(getBetreiber().getWirtschaftszweig());
                }

                anredeFeld.setText(getBetreiber().getBetranrede());
                vornamenFeld.setText(getBetreiber().getBetrvorname());
                namenFeld.setText(getBetreiber().getBetrname());
                namenLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
                namenFeld.setFont(new Font("SansSerif", Font.BOLD, 12));
                nameZusFeld.setText(getBetreiber().getBetrnamezus());
                kassenzeichenFeld.setText(getBetreiber().getKassenzeichen());
                strasseFeld.setText(getBetreiber().getStrasse());
                hausnrFeld.setValue(getBetreiber().getHausnr());
                hausnrZusFeld.setText(getBetreiber().getHausnrzus());
                String plzZs = getBetreiber().getPlzzs();
                if (plzZs != null) {
                    plzZs = plzZs.trim();
                }
                plzZsFeld.setText(plzZs);
                plzFeld.setText(getBetreiber().getPlz());
                ortFeld.setText(getBetreiber().getOrt());
                telefonFeld.setText(getBetreiber().getTelefon());
                telefaxFeld.setText(getBetreiber().getTelefax());
                emailFeld.setText(getBetreiber().getEmail());
                strassenBox.setSelectedItem(getBetreiber().getStrasse());

                if (getBetreiber().getDatenschutzAwsv() != null)
                    daten_awsvCheck.setSelected(getBetreiber().getDatenschutzAwsv());
                else
                    daten_awsvCheck.setSelected(false);

                if (getBetreiber().getDatenschutzEsatzung() != null)
                    daten_esatzungCheck.setSelected(getBetreiber().getDatenschutzEsatzung());
                else
                    daten_esatzungCheck.setSelected(false);

                if (getBetreiber().getDatenschutzWhg() != null)
                    daten_whgCheck.setSelected(getBetreiber().getDatenschutzWhg());
                else
                    daten_whgCheck.setSelected(false);

                betrBeaufVornameFeld.setText(getBetreiber().getVornamebetrbeauf());
                betrBeaufNachnameFeld.setText(getBetreiber().getNamebetrbeauf());

                handzeichenAltFeld.setText(getBetreiber().getRevihandz());
                bemerkungsArea.setText(getBetreiber().getBemerkungen());
                
                gemarkungBox.setModel(new DefaultComboBoxModel(gemarkungen));
                standortGgBox.setModel(new DefaultComboBoxModel(standortggs));
                entwGebBox.setModel(new DefaultComboBoxModel(entwgebiete));
                wEinzugsGebBox.setModel(new DefaultComboBoxModel(wEinzugsgebiete));

                Date datum = getBetreiber().getRevidatum();
                revdatumsFeld.setText(AuikUtils.getStringFromDate(datum));

                if (Standort.findByAdresse(getBetreiber()) != null) {
                    standort = Standort.findByAdresse(getBetreiber());
                }

                if (standort != null) {
                    lage = standort.getLage();
                    e32Feld.setValue(standort.getLage().getE32());
                    n32Feld.setValue(standort.getLage().getN32());

                    if (standort.getLage().getGemarkung() != null) {
                        gemarkungBox.setSelectedItem(lage.getGemarkung());
                    }
                    if (standort.getLage().getStandortgghwsg() != null) {
                        standortGgBox.setSelectedItem(lage.getStandortgghwsg());
                    }

                    if (standort.getLage().getEntgebid() != null) {
                        entwGebBox.setSelectedItem(lage.getEntgebid());
                    }

                    if (standort.getLage().getWassereinzugsgebiet() != null) {
                        wEinzugsGebBox.setSelectedItem(lage.getWassereinzugsgebiet());
                    }
                }

                frame.clearStatus();
            }
        };
        frame.changeStatus("Beschäftigt...");
        worker.start();
    }

    @Override
    protected boolean canSave() {
        // Eingaben überprüfen:
        if (namenFeld.getText().equals("")) {
            // Der Name darf nicht leer sein
            namenLabel.setForeground(HauptFrame.ERROR_COLOR);
            namenFeld.requestFocus();
            String nameErr = "Der Name darf nicht leer sein!";
            frame.changeStatus(nameErr, HauptFrame.ERROR_COLOR);
            log.debug(nameErr);
            return false;
        } else if (handzeichenNeuFeld.getText().equals("")) {
            // Das Handzeichen darf nicht leer sein
            handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
            handzeichenNeuFeld.requestFocus();
            String handzErr = "Neues Handzeichen erforderlich!";
            frame.changeStatus(handzErr, HauptFrame.ERROR_COLOR);
            log.debug(handzErr);
            return false;
        } else {
            // Wenn die Eingaben korrekt sind
            return true;
        }
    }

    /**
     * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
     */
    @Override
    protected boolean doSave() {
        // Anrede
        String anrede = anredeFeld.getText();
        if ("".equals(anrede)) {
            getBetreiber().setBetranrede(null);
        } else {
            getBetreiber().setBetranrede(anrede);
        }
        // Vorname
        String vorname = vornamenFeld.getText();
        if ("".equals(vorname)) {
            getBetreiber().setBetrvorname(null);
        } else {
            getBetreiber().setBetrvorname(vorname);
        }
        // Name
        String name = namenFeld.getText();
        if ("".equals(name)) {
            getBetreiber().setBetrname(null);
        } else {
            getBetreiber().setBetrname(name);
        }
        // Zusatz
        String nameZusatz = nameZusFeld.getText();
        if ("".equals(nameZusatz)) {
            getBetreiber().setBetrnamezus(null);
        } else {
            getBetreiber().setBetrnamezus(nameZusatz);
        }
        // kassenzeichen
        String kasse = kassenzeichenFeld.getText();
        if ("".equals(kasse)) {
            getBetreiber().setKassenzeichen(null);
        } else {
            getBetreiber().setKassenzeichen(kasse);
        }

        // Strasse:
        String strasse = strasseFeld.getText();
        if ("".equals(strasse)) {
            getBetreiber().setStrasse(null);
        } else {
            getBetreiber().setStrasse(strasse);
        }

        // Hausnummer:
        Integer hausnr = ((IntegerField) hausnrFeld).getIntValue();
        getBetreiber().setHausnr(hausnr);

        // Hausnummer-Zusatz:
        String hausnrZus = hausnrZusFeld.getText();
        if ("".equals(hausnrZus)) {
            getBetreiber().setHausnrzus(null);
        } else {
            getBetreiber().setHausnrzus(hausnrZus);
        }

        // PLZ-Zusatz
        String plzZs = plzZsFeld.getText();
        if ("".equals(plzZs)) {
            getBetreiber().setPlzzs(null);
        } else {
            getBetreiber().setPlzzs(plzZs.toUpperCase().trim());
        }

        // PLZ:
        String plz = plzFeld.getText();
        if (plz != null) {
            plz = plz.trim();
            if (plz.equals("")) {
                getBetreiber().setPlz(null);
            } else {
                getBetreiber().setPlz(plz);
            }
        }
        // Ort
        String ort = ortFeld.getText();
        if (ort != null) {
            ort = ort.trim();
            if (ort.equals("")) {
                getBetreiber().setOrt(null);
            } else {
                getBetreiber().setOrt(ort);
            }
        }
        // Telefon
        String telefon = telefonFeld.getText();
        if (telefon != null) {
            telefon = telefon.trim();
            if (telefon.equals("")) {
                getBetreiber().setTelefon(null);
            } else {
                getBetreiber().setTelefon(telefon);
            }
        }
        // Telefax
        String telefax = telefaxFeld.getText();
        if (telefax != null) {
            telefax = telefax.trim();
            if (telefax.equals("")) {
                getBetreiber().setTelefax(null);
            } else {
                getBetreiber().setTelefax(telefax);
            }
        }
        // eMail
        String email = emailFeld.getText();
        if (email != null) {
            email = email.trim();
            if (email.equals("")) {
                getBetreiber().setEmail(null);
            } else {
                getBetreiber().setEmail(email);
            }
        }
        // Betriebsbeauftragter-Vorname
        String betrBeaufVorname = betrBeaufVornameFeld.getText();
        if (betrBeaufVorname != null) {
            betrBeaufVorname = betrBeaufVorname.trim();
            if (betrBeaufVorname.equals("")) {
                getBetreiber().setVornamebetrbeauf(null);
            } else {
                getBetreiber().setVornamebetrbeauf(betrBeaufVorname);
            }
        }
        // Betriebsbeauftragter-Nachname
        String betrBeaufNachname = betrBeaufNachnameFeld.getText();
        if (betrBeaufNachname != null) {
            betrBeaufNachname = betrBeaufNachname.trim();
            if (betrBeaufNachname.equals("")) {
                getBetreiber().setNamebetrbeauf(null);
            } else {
                getBetreiber().setNamebetrbeauf(betrBeaufNachname);
            }
        }
        // Datenschutzhinweise
        getBetreiber().setDatenschutzAwsv(daten_awsvCheck.isSelected());
        getBetreiber().setDatenschutzEsatzung(daten_esatzungCheck.isSelected());
        getBetreiber().setDatenschutzWhg(daten_whgCheck.isSelected());
        // Wirtschaftszweig
        Wirtschaftszweig wizw = (Wirtschaftszweig) wirtschaftszweigBox.getSelectedItem();
        getBetreiber().setWirtschaftszweig(wizw);

        if (Double.parseDouble(e32Feld.getValue().toString()) != 0.0 && Double.parseDouble(n32Feld.getValue().toString()) != 0.0) {
            
            if (standort == null) {
            standort = new Standort();
            lage = new Lage();
            standort.setAdresse(getBetreiber());
            standort.setLage(lage);
            }
            // Gemarkung
            Gemarkung bgem = (Gemarkung) gemarkungBox.getSelectedItem();
            lage.setGemarkung(bgem);

            // Standortgg
            Standortgghwsg stgg = (Standortgghwsg) standortGgBox.getSelectedItem();
            lage.setStandortgghwsg(stgg);

            // Einzugsgebiet
            String ezgb = (String) entwGebBox.getSelectedItem();
            // Nötig, weil getSelectedItem bei editierbarer ComboBox auch
            // NULL
            // liefern kann
            if (ezgb != null) {
                // Weil ich bis jetzt noch keine LimitedComboBox oder so
                // habe...
                if (ezgb.length() > 10) {
                    // ... kürze ich hier den String auf 10 Zeichen
                    ezgb = ezgb.substring(0, 10);
                }
                ezgb = ezgb.trim();
            }
            lage.setEntgebid(ezgb);

            // VAWS-Einzugsgebiet
            Wassereinzugsgebiet wezg = (Wassereinzugsgebiet) wEinzugsGebBox.getSelectedItem();
            lage.setWassereinzugsgebiet(wezg);

            // Flur
            String flur = flurFeld.getText().trim();
            if (flur.equals("")) {
                lage.setFlur(null);
            } else {
                lage.setFlur(flur);
            }

            // Flurstueck
            String flurstk = flurStkFeld.getText().trim();
            if (flurstk.equals("")) {
                lage.setFlurstueck(null);
            } else {
                lage.setFlurstueck(flurstk);
            }

            // Rechtswert
            Float e32Wert = ((DoubleField) e32Feld).getFloatValue();
            lage.setE32(e32Wert);

            // Hochwert
            Float n32Wert = ((DoubleField) n32Feld).getFloatValue();
            lage.setN32(n32Wert);
        }

        // Bemerkungen
        String bemerkungen = bemerkungsArea.getText();
        if (bemerkungen != null) {
            bemerkungen = bemerkungen.trim();
            if (bemerkungen.equals("")) {
                getBetreiber().setBemerkungen(null);
            } else {
                getBetreiber().setBemerkungen(bemerkungen);
            }
        }
        // Handzeichen
        String handzeichen = handzeichenNeuFeld.getText().trim();
        getBetreiber().setRevihandz(handzeichen);

        getBetreiber().setRevidatum(Calendar.getInstance().getTime());

        // frame.changeStatus("Keine Änderungen an Betreiber "+betr.getBetreiberid()+"
        // vorgenommen.");

        if (standort != null) {
            Standort persistentAL = null;
            standort.setAdresse(getBetreiber());
            persistentAL = Standort.merge(standort);

            if (persistentAL != null) {
                // setEditedObject(persistentAL);
                log.debug("Änderungen gespeichert!");
                return true;
            } else {
                return false;
            }
        } else {
            getBetreiber().merge();
        }
        return true;
    }

    /**
     * Methode liefert die eingegebene oder ausgewählte Straße
     * 
     * @return
     */
    private String getStrasse() {
        String str = "";

        if (strassenBox.getSelectedItem() != null) {
            if (strassenBox.getSelectedItem().getClass() == Strassen.class) {
                Strassen selstrasse = (Strassen) strassenBox.getSelectedItem();
                if (selstrasse != null) {
                    str = selstrasse.getStrasse();
                }
            } else if (strassenBox.getSelectedItem().getClass() == String.class) {
                str = (String) strassenBox.getSelectedItem();
            }
        }
        str = str.trim();

        // Weil ich bis jetzt noch keine LimitedComboBox oder so habe...
        if (str.length() > 50) {
            // ... kürze ich hier den String auf 50 Zeichen
            str = str.substring(0, 50);
        }

        return str;
    }

    public Adresse getBetreiber() {
        return (Adresse) getEditedObject();
    }

    private JTable getStandorteTabelle() {

        if (this.standorteModel == null) {
            this.standorteModel = new BasisStandorteModel();

            if (this.standorteTabelle == null) {
                this.standorteTabelle = new JTable(this.standorteModel);

                this.standorteTabelle.getColumnModel().getColumn(0).setPreferredWidth(100);
                this.standorteTabelle.getColumnModel().getColumn(1).setPreferredWidth(10);
                this.standorteTabelle.getColumnModel().getColumn(2).setPreferredWidth(7);

                this.standorteTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                this.standorteTabelle.setColumnSelectionAllowed(false);
                this.standorteTabelle.setRowSelectionAllowed(true);

                this.standorteTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 1) && (e.getButton() == 1)) {
                            updateAdresse();
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }
                });

            }
        }
        return this.standorteTabelle;
    }

    private JScrollPane getStandorteScroller() {

        JScrollPane standorteScroller = new JScrollPane(getStandorteTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return standorteScroller;
    }

    public void updateAdresse() {

        log.debug("Start updateAdresse()");
        ListSelectionModel lsm = getStandorteTabelle().getSelectionModel();
        if (!lsm.isSelectionEmpty()) {
            if (lage == null) {
                lage = new Lage();
                standort = new Standort();
                standort.setAdresse(getBetreiber());
                standort.setLage(lage);

                gemarkungBox.setModel(new DefaultComboBoxModel(gemarkungen));
                standortGgBox.setModel(new DefaultComboBoxModel(standortggs));
                entwGebBox.setModel(new DefaultComboBoxModel(entwgebiete));
                wEinzugsGebBox.setModel(new DefaultComboBoxModel(wEinzugsgebiete));
            }

            int selectedRow = lsm.getMinSelectionIndex();
            TabStreets bts = this.standorteModel.getRow(selectedRow);
            log.debug("Standort " + bts.getName() + " (ID" + bts.getAbgleich() + ") angewählt.");
            strasseFeld.setText(bts.getName());
            hausnrFeld.setValue(bts.getHausnr());
            hausnrZusFeld.setText(bts.getHausnrZusatz());
            e32Feld.setValue(bts.getX());
            n32Feld.setValue(bts.getY());
            Strassen stra = DatabaseQuery.findStrasse(strassenBox.getSelectedItem().toString());
            if (stra.getPlz() != null) {
                plzFeld.setText(stra.getPlz());
            }
        }
        log.debug("End updateAdresse()");
    }

    private JComponent getStrassenBox() {

        strassenBox = new JComboBox();
        strassenBox.setRenderer(new LongNameComboBoxRenderer());

        return strassenBox;
    }

    /**
     * Ein Listener für die Events des "Betreiber Editor"-Moduls.
     * 
     * @author Gerhard Genuit
     */
    private final class BetreiberListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == strassenBox) {
                standorteModel.setStrasse(strassenBox.getSelectedItem().toString());
                standorteModel.updateList();

            }
        }
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
                    String e32AusZeile = tmp[2];
                    String n32AusZeile = tmp[3];
                    this.e32Feld.setValue(new Float(e32AusZeile.substring(0, 6)));
                    this.n32Feld.setValue(new Float(n32AusZeile.substring(0, 7)));
                    this.frame.changeStatus("Rechts- und Hochwert eingetragen", HauptFrame.SUCCESS_COLOR);
                } else {
                    this.frame.changeStatus("Zwischenablage enthält keine verwertbaren Daten", HauptFrame.ERROR_COLOR);
                }
                break;
            }
        }
    }

    public JButton getAusAblageButton() {
        if (this.ausAblageButton == null) {

            this.ausAblageButton = new JButton("aus QGis");
            this.ausAblageButton.setToolTipText("Rechts- und Hochwert aus Zwischenablage einfügen");
            this.ausAblageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    readClipboard();
                }
            });
        }

        return this.ausAblageButton;
    }
}