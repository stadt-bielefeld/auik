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

public class BFPanel extends AbstractSonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private BasisObjektBearbeiten parentModule;

    //Fields and Labels
    private JTextField stauvolumenField;
    private JTextField filterflaecheField;
    private JTextField drosseldurchflussField;
    private JTextField filterGeschField;
    private JTextField beschickungshoeheField;
    private JTextField hydraulWirkungsgradField;
    private JTextField staerkeFiltersubstratField;
    private JTextField ueberlaufhaufigkeitField;

    private JLabel stauvolumenLabel;
    private JLabel filterflaecheLabel;
    private JLabel drosseldurchflussLabel;
    private JLabel filterGeschLabel;
    private JLabel beschickungshoeheLabel;
    private JLabel hydraulWirkungsgradLabel;
    private JLabel staerkeFiltersubstratLabel;
    private JLabel ueberlaufhaufigkeitLabel;

    public BFPanel (BasisObjektBearbeiten parentModule) {
        this.name = "BF";
        this.parentModule = parentModule;
        createFields();

        FormLayout layout = new FormLayout(
                "r:80dlu, 5dlu, 180dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.append(stauvolumenLabel, stauvolumenField);
        builder.nextLine();
        builder.append(filterflaecheLabel, filterflaecheField);
        builder.nextLine();
        builder.append(drosseldurchflussLabel, drosseldurchflussField);
        builder.nextLine();
        builder.append(filterGeschLabel, filterGeschField);
        builder.nextLine();
        builder.append(beschickungshoeheLabel, beschickungshoeheField);
        builder.nextLine();
        builder.append(hydraulWirkungsgradLabel, hydraulWirkungsgradField);
        builder.nextLine();
        builder.append(staerkeFiltersubstratLabel, staerkeFiltersubstratField);
        builder.nextLine();
        builder.append(ueberlaufhaufigkeitLabel, ueberlaufhaufigkeitField);
    }

    public void createFields() {
        stauvolumenField = new JTextField();
        stauvolumenLabel = new JLabel("Stauvolumen über dem Filterkörper");
        filterflaecheField = new JTextField();
        filterflaecheLabel = new JLabel("Filterfläche");
        drosseldurchflussField = new JTextField();
        drosseldurchflussLabel = new JLabel("Rechnerischer Drosseldurchfluss");
        filterGeschField = new JTextField();
        filterGeschLabel = new JLabel("Filtergeschwindigkeit");
        beschickungshoeheField = new JTextField();
        beschickungshoeheLabel = new JLabel("Beschickungshöhe/mittlere Filterbelastung");
        hydraulWirkungsgradField = new JTextField();
        hydraulWirkungsgradLabel = new JLabel("Hydraulischer Wirkungsgrad");
        staerkeFiltersubstratField = new JTextField();
        staerkeFiltersubstratLabel = new JLabel("Stärke des Filtersubtrats");
        ueberlaufhaufigkeitField = new JTextField();
        ueberlaufhaufigkeitLabel = new JLabel("Überlaufhäufigkeit");
    }

    public void createMappings() {
        this.fieldMapping = new HashMap<String, RecordMap>();
    }

    public void fetchFormData() {

    }

    /**
     * Get field value by field name.
     * @param fieldName Field name
     * @return Field value
     */
    public Object getFieldValue(String fieldName) {
        return getFieldValue(fieldName, this);
    }
}