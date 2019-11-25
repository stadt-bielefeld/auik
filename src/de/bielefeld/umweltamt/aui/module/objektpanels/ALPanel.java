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

import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class ALPanel extends AbstractSonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private BasisObjektBearbeiten parentModule;

    //Fields and labels
    private JTextField drossabflussField;
    private JTextField volumenField;
    private JTextField ueberlaufhaeufigkeitField;
    private JTextField entleerungsZeitField;

    private JLabel drosselabflussLabel;
    private JLabel volumenLabel;
    private JLabel ueberlaufHaeufigkeitLabel;
    private JLabel entleerungsZeitLabel;

    public ALPanel (BasisObjektBearbeiten parentModule) {
        this.name = "AL";
        this.parentModule = parentModule;

        createFields();

        FormLayout layout = new FormLayout(
                "r:80dlu, 5dlu, 180dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.append(drosselabflussLabel, drossabflussField);
        builder.nextLine();
        builder.append(volumenLabel, volumenField);
        builder.nextLine();
        builder.append(ueberlaufHaeufigkeitLabel, ueberlaufhaeufigkeitField);
        builder.nextLine();
        builder.append(entleerungsZeitLabel, entleerungsZeitField);
    }

    public void createFields() {
        drossabflussField = new JTextField();
        drosselabflussLabel = new JLabel("Drossabfluss tatsächlich");
        volumenField = new JTextField();
        volumenLabel = new JLabel("Becken- bzw. Speichervolumen");
        ueberlaufhaeufigkeitField = new JTextField();
        ueberlaufHaeufigkeitLabel = new JLabel("Jährliche Überlaufhäufigkeit");
        entleerungsZeitField = new JTextField();
        entleerungsZeitLabel = new JLabel("Entleerungszeit");
        createMappings();
    }

    /**
     * Create a mapping for field values and record fields
     */
    private void createMappings() {
        this.fieldMapping = new HashMap<String, RecordMap>();
        this.fieldMapping.put("drossabflussField", new RecordMap("drosselabfluss", "java.math.BigDecimal"));
        this.fieldMapping.put("volumenField", new RecordMap("speichervolumen", "java.lang.Integer"));
        this.fieldMapping.put("ueberlaufhaeufigkeitField", new RecordMap("rjahrUeh", "java.math.BigDecimal"));
        this.fieldMapping.put("entleerungsZeitField", new RecordMap("entleerungszeit", "java.math.BigDecimal"));
    }

    /**
     * Fetch record data an fill form
     */
    public void fetchFormData() {
        this.drossabflussField.setText(this.record.getDrosselabfluss().toString());
        this.volumenField.setText(this.record.getSpeichervolumen().toString());
        this.ueberlaufhaeufigkeitField.setText(this.record.getRjahrUeh().toString());
        this.entleerungsZeitField.setText(this.record.getEntleerungszeit().toString());
    }

    /**
     * Get value for a field by name
     * @param fieldName Field name as String
     */
    public Object getFieldValue(String fieldName) {
        switch(fieldName) {
            case "drossabflussField":
                return parseBigDecimalFromString(drossabflussField.getText());
            case "volumenField":
                return parseIntegerFromString(volumenField.getText());
            case "ueberlaufhaeufigkeitField":
                return parseBigDecimalFromString(ueberlaufhaeufigkeitField.getText());
            case "entleerungsZeitField":
                return parseBigDecimalFromString(entleerungsZeitField.getText());
            default: throw new IllegalArgumentException("Unkown field name: " + fieldName);
        }
    }
}