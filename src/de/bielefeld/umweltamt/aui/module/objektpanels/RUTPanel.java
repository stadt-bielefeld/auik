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

import java.awt.Graphics;
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

public class RUTPanel extends AbstractSonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private BasisObjektBearbeiten parentModule;

    //Fields and labels
    private JTextField behandlungsFlaecheField;
    private JTextField nichtBehandlungsFlaecheField;
    private JTextField kritRegenspendeField;
    private JTextField drossabflussTatField;
    private JTextField minDrosselabflussField;

    private JLabel behandlungsFlaecheLabel;
    private JLabel nichtBehandlungsFlaecheLabel;
    private JLabel kritRegenspendeLabel;
    private JLabel drossabflussTatLabel;
    private JLabel minDrosselabflussLabel;

    public RUTPanel (BasisObjektBearbeiten parentModule) {
        this.name = "RÜT";
        this.parentModule = parentModule;

        createFields();

        FormLayout layout = new FormLayout(
                "r:80dlu, 5dlu, 180dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.append(behandlungsFlaecheLabel, behandlungsFlaecheField);
        builder.nextLine();
        builder.append(nichtBehandlungsFlaecheLabel, nichtBehandlungsFlaecheField);
        builder.nextLine();
        builder.append(kritRegenspendeLabel, kritRegenspendeField);
        builder.nextLine();
        builder.append(drossabflussTatLabel, drossabflussTatField);
        builder.nextLine();
        builder.append(minDrosselabflussLabel, minDrosselabflussField);
    }

    private void createFields() {
        behandlungsFlaecheField = new JTextField();
        behandlungsFlaecheLabel = new JLabel("Behandlungsbedürftige Fläche");
        nichtBehandlungsFlaecheField = new JTextField();
        nichtBehandlungsFlaecheLabel = new JLabel("Nicht behandlungsbedürftige Fläche");
        kritRegenspendeField = new JTextField();
        kritRegenspendeLabel = new JLabel("Kritische Regenspende");
        drossabflussTatField = new JTextField();
        drossabflussTatLabel = new JLabel("Drosselabfluss tatsächlich");
        minDrosselabflussField = new JTextField();
        minDrosselabflussLabel = new JLabel("Mindestdrosselabfluss");
    }

    public Object getFieldValue(String fieldName) {
        return null;
    }
}