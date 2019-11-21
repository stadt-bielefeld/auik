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

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class RRBPanel extends AbstractSonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private BasisObjektBearbeiten parentModule;

    private JComboBox<String> funktionenBox;
    private DefaultComboBoxModel<String> funktionenModel;
    private JTextField drosselabflussField;
    private JTextField volumenField;
    private JTextField ueberlaufhaeufigkeitField;
    private JTextField entleerungszeit;

    public RRBPanel (BasisObjektBearbeiten parentModule) {
        this.name = "RRB";
        this.parentModule = parentModule;

        createFields();

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

    private void createFields() {

        funktionenModel = new DefaultComboBoxModel<String>(new String[]{
                "-", "Rückhaltung vor Einleitung",
                "Rückhalt im Kanalnetz", "Rücknahme für Brauchwasser im Betrieb",
                "nur für Störfälle"});
        funktionenBox = new JComboBox<String>(funktionenModel);
        drosselabflussField = new JTextField();
        volumenField = new JTextField();
        ueberlaufhaeufigkeitField = new JTextField();
        entleerungszeit = new JTextField();
    }

    public void save() {
        
    }

    public List<JComponent> getFields() {
        List<JComponent> list = new ArrayList<JComponent>();
        list.add(funktionenBox);
        list.add(drosselabflussField);
        list.add(volumenField);
        list.add(ueberlaufhaeufigkeitField);
        list.add(entleerungszeit);
        return list;
    }

    public void setData(Sonderbauwerk sonderbauwerk) {

    }
}