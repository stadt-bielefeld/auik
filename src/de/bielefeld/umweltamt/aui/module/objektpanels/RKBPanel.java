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

    //Fields
    JComboBox<CBoxItem> betriebsartBox;
    JComboBox<CBoxItem> beckenartBox;
    JComboBox<CBoxItem> entlastungsartBox;
    JComboBox<CBoxItem> anordnungBox;
    JComboBox<CBoxItem> drosselabflussBox;
    JCheckBox drosselschluss;

    JTextField behandFlaeche;
    JTextField nBehandFlaeche;
    JTextField kritRegenspende;
    JTextField vorhandVolumen;

    //If betriebsart is "nicht ständig gefüllt"
    JTextField spezVolumen;
    JLabel spezVolumenLabel;
    JTextField minVolumen;
    JLabel minVolumenLabel;

    //If betriebsart is "ständig gefüllt"
    JTextField beckentiefe;
    JLabel beckentiefeLabel;
    JTextField beckenoberfl;
    JLabel beckenoberflLabel;
    JTextField drosselabflussTat;
    JLabel drosselabflussTatLabel;
    JTextField beckenvolumen;
    JLabel beckenvolumenLabel;
    JTextField flaechenbeschickung;
    JLabel flaechenbeschickungLabel;
    JTextField minDrosselabfluss;
    JLabel minDrosselabflussLabel;

    //Models
    DefaultComboBoxModel<CBoxItem> betriebsartModel;
    DefaultComboBoxModel<CBoxItem> beckenartModel;
    DefaultComboBoxModel<CBoxItem> entlastungsartModel;
    DefaultComboBoxModel<CBoxItem> anordnungModel;
    DefaultComboBoxModel<CBoxItem> drosselabflussModel;

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
        builder.append("Drosselschluss bei Überschreiten dess Füllstandes oder eines Maximalzuflusses",
                drosselschluss);
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
        //Initialize Models
        betriebsartModel = new DefaultComboBoxModel<CBoxItem>(new CBoxItem[]{
            new CBoxItem(null, "-"),
            new CBoxItem(1, "ständig gefüllt"),
            new CBoxItem(0, "nicht ständig gefüllt")
        });
        beckenartModel = new DefaultComboBoxModel<CBoxItem>(new CBoxItem[]{
            new CBoxItem(null, "-"),
            new CBoxItem(0, "Fangbecken"),
            new CBoxItem(1, "Durchlaufbecken"),
            new CBoxItem(2, "Verbundbecken")
        });
        entlastungsartModel = new DefaultComboBoxModel<CBoxItem>(new CBoxItem[]{
            new CBoxItem(null, "-"),
            new CBoxItem(0, "Entlastung oben"),
            new CBoxItem(1, "Entlastung unten"),
            new CBoxItem(2, "Entlastung mittig")
        });
        anordnungModel = new DefaultComboBoxModel<CBoxItem>(new CBoxItem[]{
            new CBoxItem(null, "-"),
            new CBoxItem(0, "im Hauptschluss"),
            new CBoxItem(1, "im Nebenschluss")
        });
        drosselabflussModel = new DefaultComboBoxModel<>(new CBoxItem[]{
            new CBoxItem(null, "-"),
            new CBoxItem(0, "mit ständigem Drosselabfluss"),
            new CBoxItem(1, "mit zweiteiligem Drosselabfluss")
        });
        //Initialize fields
        betriebsartBox = new JComboBox<CBoxItem>(betriebsartModel);
        beckenartBox = new JComboBox<CBoxItem>(beckenartModel);
        entlastungsartBox = new JComboBox<CBoxItem>(entlastungsartModel);
        anordnungBox = new JComboBox<CBoxItem>(anordnungModel);
        drosselabflussBox = new JComboBox<CBoxItem>(drosselabflussModel);
        drosselschluss = new JCheckBox();

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
        //TODO: create Mappings for
        //drosselschluss
        //nBehandFlaeche
        //vorhandVolumen
        //minDrosselabfluss
        //flaechenbeschickung
        //beckenvolumen
        //minVolumen
        //spezVolumen
        this.fieldMapping = new HashMap<String, RecordMap>();
        this.fieldMapping.put("betriebsartBox", new RecordMap("betriebsartOpt", "java.lang.Integer"));
        this.fieldMapping.put("beckenartBox", new RecordMap("beckenartOpt", "java.lang.Integer"));
        this.fieldMapping.put("entlastungsartBox", new RecordMap("entlastungsartOpt", "java.lang.Integer"));
        this.fieldMapping.put("anordnungBox", new RecordMap("anordnungOpt", "java.lang.Integer"));
        this.fieldMapping.put("drosselabflussBox", new RecordMap("drossAbflussOpt", "java.lang.Integer"));
        this.fieldMapping.put("kritRegenspende", new RecordMap("qrkrit", "java.math.BigDecimal"));
        this.fieldMapping.put("beckentiefe", new RecordMap("beckentiefe", "java.math.BigDecimal"));
        this.fieldMapping.put("beckenoberfl", new RecordMap("WOberflaeche", "java.lang.Integer"));
        this.fieldMapping.put("drosselabflussTat", new RecordMap("drosselabfluss", "java.math.BigDecimal"));
    }

    public void fetchFormData() {

    }

    /**
     * Get field value by field name.
     * @param fieldName Field name
     * @return Field value
     */
    public Object getFieldValue(String fieldName) {
        //TODO: add missing field return values
        switch(fieldName) {
            case "betriebsartBox":
                return ((CBoxItem) betriebsartBox.getSelectedItem()).getId();
            case "beckenartBox":
                return ((CBoxItem) beckenartBox.getSelectedItem()).getId();
            case "entlastungsartBox":
                return ((CBoxItem) entlastungsartBox.getSelectedItem()).getId();
            case "anordungBox":
                return ((CBoxItem) anordnungBox.getSelectedItem()).getId();
            case "drosselabflussBox":
                return ((CBoxItem) drosselabflussBox.getSelectedItem()).getId();
            case "drosselschluss":
                return drosselschluss.isSelected();
            case "behandFlaeche":
                return parseBigDecimalFromString(behandFlaeche.getText());
            case "nBehandFlaeche":
                //TODO: Field
                return null;
            case "kritRegenspende":
                return parseBigDecimalFromString(kritRegenspende.getText());
            case "vorhandVolumen":
                //TODO: Field
                return null;
            case "beckentiefe":
                return parseBigDecimalFromString(beckentiefe.getText());
            case "beckenoberfl":
                return parseIntegerFromString(beckenoberfl.getText());
            case "drosselabflussTat":
                return parseBigDecimalFromString(drosselabflussTat.getText());
            case "spezVolumen":
                return null;
            case "minVolumen":
                return null;
            case "beckenvolumen":
                return null;
            case "flaechenbeschickung":
                return null;
            case "minDrosselabfluss":
                return null;
            default: throw new IllegalArgumentException("Unkown field name: " + fieldName);
        }
    }

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
                drosselschluss.setEnabled(true);
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
                drosselschluss.setEnabled(false);
                break;
        }
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
}