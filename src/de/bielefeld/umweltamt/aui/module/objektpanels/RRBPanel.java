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

import java.math.BigDecimal;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.CBoxItem;

/**
 * Panel containing a form to edit RRB infos.
 */
public class RRBPanel extends AbstractSonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private BasisObjektBearbeiten parentModule;

    private JComboBox<CBoxItem> funktionenBox;
    private DefaultComboBoxModel<CBoxItem> funktionenModel;
    private CBoxItem[] funktionen;
    private JTextField drosselabflussField;
    private JTextField volumenField;
    private JTextField ueberlaufhaeufigkeitField;
    private JTextField entleerungszeit;

    public RRBPanel (BasisObjektBearbeiten parentModule) {
        this.name = "RRB";
        this.parentModule = parentModule;

        createFields();
        createMappings();

        FormLayout layout = new FormLayout(
                "r:130dlu, 5dlu, 180dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.append("Funktion des Regenrückhaltebeckens", funktionenBox);
        builder.nextLine();
        builder.append("Drosselabfluss tatsächlich", drosselabflussField);
        builder.nextLine();
        builder.append("Becken- bzw. Speichervolumen", volumenField);
        builder.nextLine();
        builder.append("Jährlicher Überlaufhäufigkeit", ueberlaufhaeufigkeitField);
        builder.nextLine();
        builder.append("Entleerungszeit", entleerungszeit);
    }

    /**
     * Create fields for this panel
     */
    private void createFields() {
        this.funktionen = new CBoxItem[]{
            new CBoxItem(null, "-"),
            new CBoxItem(4, "Rückhaltung vor Einleitung"),
            new CBoxItem(5, "Rückhalt im Kanalnetz"),
            new CBoxItem(3, "Rücknahme für Brauchwasser im Betrieb"),
            new CBoxItem(2, "nur für Störfälle")
        };
        funktionenModel = new DefaultComboBoxModel<CBoxItem>(this.funktionen);
        funktionenBox = new JComboBox<CBoxItem>(funktionenModel);
        drosselabflussField = new JTextField();
        volumenField = new JTextField();
        ueberlaufhaeufigkeitField = new JTextField();
        entleerungszeit = new JTextField();
        createMappings();
    }

    /**
     * Create a mapping for field values and record fields
     */
    private void createMappings() {
        this.fieldMapping = new HashMap<String, RecordMap>();
        this.fieldMapping.put("funktionenBox", new RecordMap("funktionOpt", "java.lang.Integer"));
        this.fieldMapping.put("drosselabflussField", new RecordMap("drossAbflussOpt", "java.lang.Integer"));
        this.fieldMapping.put("volumenField", new RecordMap("speichervolumen", "java.lang.Integer"));
        this.fieldMapping.put("ueberlaufhaeufigkeitField", new RecordMap("rjahrUeh", "java.math.BigDecimal"));
        this.fieldMapping.put("entleerungszeit", new RecordMap("entleerungszeit", "java.math.BigDecimal"));
    }

    public void fetchFormData() {
        //Select funktionenBox value
        Integer funktionenOpt = this.record.getFunktionOpt();
        for (CBoxItem item : this.funktionen) {
            if (item.getId() != null && item.getId().equals(funktionenOpt)) {
                this.funktionenBox.setSelectedItem(item);
                break;
            }
        }
        setTextFieldContent(drosselabflussField, this.record.getDrossAbflussOpt());
        setTextFieldContent(volumenField, this.record.getSpeichervolumen());
        setTextFieldContent(ueberlaufhaeufigkeitField, this.record.getRjahrUeh());
        setTextFieldContent(entleerungszeit, this.record.getEntleerungszeit());
    }

    /**
     * Get value for a field by name
     * @param fieldName Field name as String
     */
    public Object getFieldValue(String fieldName) {
        switch (fieldName) {
            case "funktionenBox":
                return ((CBoxItem)funktionenBox.getSelectedItem()).getId();
            case "drosselabflussField":
                return parseIntegerFromString(drosselabflussField.getText());
            case "volumenField":
                return parseIntegerFromString(volumenField.getText());
            case "ueberlaufhaeufigkeitField":
                return parseBigDecimalFromString(ueberlaufhaeufigkeitField.getText());
            case "entleerungszeit":
                return parseBigDecimalFromString(entleerungszeit.getText());
            default: throw new IllegalArgumentException("Unkown field name: " + fieldName);
        }
    }
}