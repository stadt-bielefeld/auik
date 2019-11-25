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

public class RSTPanel extends AbstractSonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private BasisObjektBearbeiten parentModule;

    //Fields and labels
    private JTextField speicherVolumenField;

    private JLabel speicherVolumenLabel;

    public RSTPanel (BasisObjektBearbeiten parentModule) {
        this.name = "RST";
        this.parentModule = parentModule;

        createFields();

        FormLayout layout = new FormLayout(
                "r:80dlu, 5dlu, 180dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.append(speicherVolumenLabel, speicherVolumenField);
    }

    public void createFields() {
        speicherVolumenField = new JTextField();
        speicherVolumenLabel = new JLabel("Vorhandenes Speichervolumen");
    }

    private void createMappings() {
        this.fieldMapping = new HashMap<String, RecordMap>();
        this.fieldMapping.put("speicherVolumenField", new RecordMap("speichervolumen", "java.lang.Integer"));
    }

    public void fetchFormData() {
        
    }

    public Object getFieldValue(String fieldName) {
                switch (fieldName) {
            case "speicherVolumenField":
                return parseIntegerFromString(speicherVolumenField.getText());
            default: throw new IllegalArgumentException("Unkown field name: " + fieldName);
        }

    }
}