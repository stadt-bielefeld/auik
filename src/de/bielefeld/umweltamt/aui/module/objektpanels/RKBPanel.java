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

package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.CBoxItem;

public class RKBPanel extends AbstractSonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private BasisObjektBearbeiten parentModule;

    //Boxes
    private JComboBox<CBoxItem> betriebsartBox;
    private DefaultComboBoxModel<CBoxItem> betriebsartModel;
    private CBoxItem[] betriebsart;

    private JComboBox<CBoxItem> beckenartBox;
    private DefaultComboBoxModel<CBoxItem> beckenartModel;
    private CBoxItem[] beckenart;

    private JComboBox<CBoxItem> entlastungsartBox;
    private DefaultComboBoxModel<CBoxItem> entlastungsartModel;
    private CBoxItem[] entlastungsart;

    private JComboBox<CBoxItem> anordnungBox;
    private DefaultComboBoxModel<CBoxItem> anordnungModel;
    private CBoxItem[] anordnung;

    private JComboBox<CBoxItem> drosselabflussBox;
    private DefaultComboBoxModel<CBoxItem> drosselabflussModel;
    private CBoxItem[] drosselabfluss;

    //CheckBox
    private JCheckBox drosselschlussCheck;

    //Fields
    private JTextField behandFlaeche;
    private JTextField nBehandFlaeche;
    private JTextField kritRegenspende;
    private JTextField vorhandVolumen;

    //If betriebsart is "nicht ständig gefüllt"
    private JTextField spezVolumen;
    private JLabel spezVolumenLabel;
    private JTextField minVolumen;
    private JLabel minVolumenLabel;

    //If betriebsart is "ständig gefüllt"
    private JTextField beckentiefe;
    private JLabel beckentiefeLabel;
    private JTextField beckenoberfl;
    private JLabel beckenoberflLabel;
    private JTextField drosselabflussTat;
    private JLabel drosselabflussTatLabel;
    private JTextField beckenvolumen;
    private JLabel beckenvolumenLabel;
    private JTextField flaechenbeschickung;
    private JLabel flaechenbeschickungLabel;
    private JTextField minDrosselabfluss;
    private JLabel minDrosselabflussLabel;



public RKBPanel (BasisObjektBearbeiten parentModule) {
        this.name = "RKB";
        this.parentModule = parentModule;

        createFields();
        createMappings();

        FormLayout layout = new FormLayout(
                "r:pref, 5dlu, 180dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.appendSeparator("Beckenart");
        builder.nextLine();
        builder.append("Betriebsart", betriebsartBox);
        builder.nextLine();
        builder.append("Beckenart", beckenartBox);
        builder.nextLine();
        builder.append("Art der Entlastung des Stauraumkanals", entlastungsartBox);
        builder.nextLine();
        builder.append("Anordnung des Regenklärbeckens", anordnungBox);
        builder.nextLine();
        builder.append("Drosselabfluss zur Behandlung", drosselabflussBox);
        builder.nextLine();
        builder.append("Drosselschluss bei Überschreiten des Füllstandes oder eines Maximalzuflusses",
        		drosselschlussCheck);
        builder.nextLine();

        builder.appendSeparator("Art und Kenndaten des Sonderbauwerks");
        builder.append("Behandlungsbedürftige Fläche", behandFlaeche);
        builder.nextLine();
        builder.append("Nicht behandlungsbedürftige Fläche", nBehandFlaeche);
        builder.nextLine();
        builder.append("Kritische Regenspende", kritRegenspende);
        builder.nextLine();
        builder.append("Vorhandenes Speichervolumen", vorhandVolumen);
        builder.nextLine();

        builder.append(spezVolumenLabel, spezVolumen);
        builder.nextLine();
        builder.append(minVolumenLabel, minVolumen);
        builder.nextLine();

        builder.append(beckentiefeLabel, beckentiefe);
        builder.nextLine();
        builder.append(beckenoberflLabel, beckenoberfl);
        builder.nextLine();
        builder.append(drosselabflussTatLabel, drosselabflussTat);
        builder.nextLine();
        builder.append(beckenvolumenLabel, beckenvolumen);
        builder.nextLine();
        builder.append(flaechenbeschickungLabel, flaechenbeschickung);
        builder.nextLine();
        builder.append(minDrosselabflussLabel, minDrosselabfluss);

        setBetriebsArt("-");
    }

    private void createFields() {

        this.betriebsart = new CBoxItem []{
            new CBoxItem(null, "-"),
            new CBoxItem(1, "ständig gefüllt"),
            new CBoxItem(0, "nicht ständig gefüllt")
        };

        this.beckenart = new CBoxItem []{
            new CBoxItem(null, "-"),
            new CBoxItem(0, "Fangbecken"),
            new CBoxItem(1, "Durchlaufbecken"),
            new CBoxItem(2, "Verbundbecken")
        };
        this.entlastungsart = new CBoxItem []{
            new CBoxItem(null, "-"),
            new CBoxItem(0, "Entlastung oben"),
            new CBoxItem(1, "Entlastung unten"),
            new CBoxItem(2, "Entlastung mittig")
        };
        this.anordnung = new CBoxItem []{
            new CBoxItem(null, "-"),
            new CBoxItem(0, "im Hauptschluss"),
            new CBoxItem(1, "im Nebenschluss")
        };
        this.drosselabfluss = new CBoxItem []{
            new CBoxItem(null, "-"),
            new CBoxItem(0, "mit ständigem Drosselabfluss"),
            new CBoxItem(1, "mit zweiteiligem Drosselabfluss")
        };

        betriebsartModel = new DefaultComboBoxModel<CBoxItem>(this.betriebsart);
        beckenartModel = new DefaultComboBoxModel<CBoxItem>(this.beckenart);
        entlastungsartModel = new DefaultComboBoxModel<CBoxItem>(this.entlastungsart);
        anordnungModel = new DefaultComboBoxModel<CBoxItem>(this.anordnung);
        drosselabflussModel = new DefaultComboBoxModel<CBoxItem>(this.drosselabfluss);

        betriebsartBox = new JComboBox<CBoxItem>(betriebsartModel);
        beckenartBox = new JComboBox<CBoxItem>(beckenartModel);
        entlastungsartBox = new JComboBox<CBoxItem>(entlastungsartModel);
        anordnungBox = new JComboBox<CBoxItem>(anordnungModel);
        drosselabflussBox = new JComboBox<CBoxItem>(drosselabflussModel);

        drosselschlussCheck =new JCheckBox();

        behandFlaeche = new JTextField();
        nBehandFlaeche = new JTextField();
        kritRegenspende = new JTextField();
        vorhandVolumen = new JTextField();
        spezVolumen = new JTextField();
        minVolumen = new JTextField();
        beckentiefe = new JTextField();
        beckenoberfl  = new JTextField();
        drosselabflussTat  = new JTextField();
        beckenvolumen = new JTextField();
        flaechenbeschickung = new JTextField();
        minDrosselabfluss = new JTextField();

        spezVolumenLabel = new JLabel("Spezifisches Speichervolumen");
        minVolumenLabel = new JLabel("Mindestspeichervolumen");
        beckentiefeLabel = new JLabel("Beckentiefe");
        beckenoberflLabel = new JLabel("Wirksame Beckenoberfläche");
        drosselabflussTatLabel = new JLabel("Drosselabfluss tatsächlich");
        beckenvolumenLabel = new JLabel("Spezifisches Beckenvolumen");
        flaechenbeschickungLabel = new JLabel("Fächenbeschickung");
        minDrosselabflussLabel = new JLabel("Mindestdrosselabfluss");

        //Add action listeners
        betriebsartBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                CBoxItem selectedItem = (CBoxItem) betriebsartBox.getSelectedItem();
                setBetriebsArt((String) selectedItem.getValue());
            }
        });
    }

    private void createMappings() {

        this.fieldMapping = new HashMap<String, RecordMap>();
        this.addMapping("betriebsartBox", "betriebsartOpt", "java.lang.Integer");
        this.addMapping("beckenartBox","beckenartOpt", "java.lang.Integer");
        this.addMapping("entlastungsartBox", "entlastungsartOpt", "java.lang.Integer");
        this.addMapping("anordnungBox", "anordnungOpt", "java.lang.Integer");
        this.addMapping("drosselabflussBox", "drossAbflussOpt", "java.lang.Integer");
        this.addMapping("kritRegenspende", "qrkrit", "java.math.BigDecimal");
        this.addMapping("beckentiefe", "beckentiefe", "java.math.BigDecimal");
        this.addMapping("beckenoberfl", "WOberflaeche", "java.lang.Integer");
        this.addMapping("drosselabflussTat", "drosselabfluss", "java.math.BigDecimal");
        this.addMapping("drosselschlussCheck", "drosselTog", "java.lang.Boolean");
        this.addMapping("behandFlaeche", "behFlaeche2u3", "java.math.BigDecimal");
        this.addMapping("nBehandFlaeche","behFlaeche1u2", "java.math.BigDecimal");
        this.addMapping("vorhandVolumen", "speichervolumen", "java.lang.Integer");
        this.addMapping("minDrosselabfluss", "minDrAbfluss", "java.math.BigDecimal");
        this.addMapping("flaechenbeschickung", "flaechenbeschickung", "java.math.BigDecimal");
        this.addMapping("beckenvolumen", "spezBeckenvol", "java.lang.Integer");
        this.addMapping("minVolumen","nMindestV", "java.lang.Integer");


    }

    public void fetchFormData() {
    	 Integer betriebsart = this.record.getBetriebsartOpt();
         for (CBoxItem item : this.betriebsart) {
             if (item.getId() != null && item.getId().equals(betriebsart)) {
                 this.betriebsartBox.setSelectedItem(item);
                 break;
             }}

         Integer beckenart = this.record.getBeckenartOpt();
         for (CBoxItem item : this.beckenart) {
             if (item.getId() != null && item.getId().equals(beckenart)) {
                 this.beckenartBox.setSelectedItem(item);
                 break;
            }}


         Integer entlastungsart = this.record.getEntlastungsartOpt();
         for (CBoxItem item : this.entlastungsart) {
             if (item.getId() != null && item.getId().equals(entlastungsart)) {
                 this.entlastungsartBox.setSelectedItem(item);
                 break;
            }}


         Integer anordnung = this.record.getAnordnungOpt();
         for (CBoxItem item : this.anordnung) {
             if (item.getId() != null && item.getId().equals(anordnung)) {
                 this.anordnungBox.setSelectedItem(item);
                 break;
            }}

         Integer drosselabfluss = this.record.getDrossAbflussOpt();
         for (CBoxItem item : this.drosselabfluss) {
             if (item.getId() != null && item.getId().equals(drosselabfluss)) {
                 this.drosselabflussBox.setSelectedItem(item);
                 break;
            }}

         if (this.record.isDrosselTog()) {
             this.drosselschlussCheck.setSelected(true);
             } else {
             this.drosselschlussCheck.setSelected(false);
             }

         setTextFieldContent(kritRegenspende, this.record.getRkrit());
         setTextFieldContent(beckentiefe, this.record.getBeckentiefe());
         setTextFieldContent(beckenoberfl, this.record.getWOberflaeche());
         setTextFieldContent(drosselabflussTat, this.record.getDrosselabfluss());
         setTextFieldContent(behandFlaeche, this.record.getBehFlaeche2u3());
         setTextFieldContent(nBehandFlaeche, this.record.getBehFlaeche1u2());
         setTextFieldContent(vorhandVolumen, this.record.getSpeichervolumen());
         setTextFieldContent(minDrosselabfluss, this.record.getMinDrAbfluss());
         setTextFieldContent(flaechenbeschickung, this.record.getFlaechenbeschickung());
         setTextFieldContent(beckenvolumen, this.record.getSpezBeckenvol());
         setTextFieldContent(minVolumen, this.record.getNMindestV());




    }

    /**
     * Get field value by field name.
     * @param fieldName Field name
     * @return Field value
     */


    private void setBetriebsArt(String betriebsart) {
        if (betriebsart == null) {
            return;
        }
        switch (betriebsart) {
            case "-":
                spezVolumen.setVisible(false);
                minVolumen.setVisible(false);
                beckentiefe.setVisible(false);
                beckenoberfl.setVisible(false);
                drosselabflussTat.setVisible(false);
                beckenvolumen.setVisible(false);
                flaechenbeschickung.setVisible(false);
                minDrosselabfluss.setVisible(false);

                spezVolumenLabel.setVisible(false);
                minVolumenLabel.setVisible(false);
                beckentiefeLabel.setVisible(false);
                beckenoberflLabel.setVisible(false);
                drosselabflussTatLabel.setVisible(false);
                beckenvolumenLabel.setVisible(false);
                flaechenbeschickungLabel.setVisible(false);
                minDrosselabflussLabel.setVisible(false);
                break;
            case "nicht ständig gefüllt":
                spezVolumen.setVisible(true);
                minVolumen.setVisible(true);
                beckentiefe.setVisible(false);
                beckenoberfl.setVisible(false);
                drosselabflussTat.setVisible(false);
                beckenvolumen.setVisible(false);
                flaechenbeschickung.setVisible(false);
                minDrosselabfluss.setVisible(false);

                spezVolumenLabel.setVisible(true);
                minVolumenLabel.setVisible(true);
                beckentiefeLabel.setVisible(false);
                beckenoberflLabel.setVisible(false);
                drosselabflussTatLabel.setVisible(false);
                beckenvolumenLabel.setVisible(false);
                flaechenbeschickungLabel.setVisible(false);
                minDrosselabflussLabel.setVisible(false);

                beckenartBox.setEnabled(true);
                entlastungsartBox.setEnabled(true);
                drosselabflussBox.setEnabled(true);
                drosselschlussCheck.setEnabled(true);
                break;
            case "ständig gefüllt":
                spezVolumen.setVisible(false);
                minVolumen.setVisible(false);
                beckentiefe.setVisible(true);
                beckenoberfl.setVisible(true);
                drosselabflussTat.setVisible(true);
                beckenvolumen.setVisible(true);
                flaechenbeschickung.setVisible(true);
                minDrosselabfluss.setVisible(true);

                spezVolumenLabel.setVisible(false);
                minVolumenLabel.setVisible(false);
                beckentiefeLabel.setVisible(true);
                beckenoberflLabel.setVisible(true);
                drosselabflussTatLabel.setVisible(true);
                beckenvolumenLabel.setVisible(true);
                flaechenbeschickungLabel.setVisible(true);
                minDrosselabflussLabel.setVisible(true);

                beckenartBox.setEnabled(false);
                entlastungsartBox.setEnabled(false);
                drosselabflussBox.setEnabled(false);
                drosselschlussCheck.setEnabled(false);
                break;
        }
    }

    /**
     * Get field value by field name.
     * @param fieldName Field name
     * @return Field value
     */
    public Object getFieldValue(String fieldName) {
        return getFieldValue(fieldName, this);
    }

    public JComboBox<CBoxItem> getBetriebsartBox() {
        return this.betriebsartBox;
    }

    public JComboBox<CBoxItem> getBeckenartBox() {
        return this.beckenartBox;
    }

    public JComboBox<CBoxItem> getEntlastungsartBox() {
        return this.entlastungsartBox;
    }

    public JComboBox<CBoxItem> getAnordnungBox() {
        return this.anordnungBox;
    }

    public JComboBox<CBoxItem> getDrosselabflussBox() {
        return this.drosselabflussBox;
    }

    public JTextField getKritRegenspende() {
        return this.kritRegenspende;
    }

    public JTextField getBeckentiefe() {
        return this.beckentiefe;
    }

    public JTextField getBeckenoberfl() {
        return this.beckenoberfl;
    }

    public JTextField getDrosselabflussTat() {
        return this.drosselabflussTat;
    }

    public JCheckBox getDrosselschlussCheck() {
        return this.drosselschlussCheck;
    }

    public JTextField getBehandFlaeche() {
        return this.behandFlaeche;
    }

    public JTextField getNBehandFlaeche() {
        return this.nBehandFlaeche;
    }

    public JTextField getVorhandVolumen() {
        return this.vorhandVolumen;
    }

    public JTextField getMinDrosselabfluss() {
        return this.minDrosselabfluss;
    }

    public JTextField getFlaechenbeschickung() {
        return this.flaechenbeschickung;
    }

    public JTextField getBeckenvolumen() {
        return this.beckenvolumen;
    }

    public JTextField getMinVolumen() {
        return this.minVolumen;
    }













}