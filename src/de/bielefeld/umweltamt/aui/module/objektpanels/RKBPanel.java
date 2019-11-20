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

import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class RKBPanel extends SonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private BasisObjektBearbeiten parentModule;

    //Fields
    JComboBox betriebsartBox;
    JComboBox beckenartBox;
    JComboBox entlastungsartBox;
    JComboBox anordungBox;
    JComboBox drosselabflussBox;
    JCheckBox drosselschluss;

    JTextField behandFlaeche;
    JTextField nBehandFlaeche;
    JTextField kritRegenspende;
    JTextField vorhandVolumen;
    JTextField spezVolumen;
    JTextField minVolumen;

    public RKBPanel (BasisObjektBearbeiten parentModule) {
        this.name = "RKB";
        this.parentModule = parentModule;

        //Initialize fields
        betriebsartBox = new JComboBox<>();
        beckenartBox = new JComboBox<>();
        entlastungsartBox = new JComboBox<>();
        anordungBox = new JComboBox<>();
        drosselabflussBox = new JComboBox<>();
        drosselschluss = new JCheckBox();

        behandFlaeche = new JTextField();
        nBehandFlaeche = new JTextField();
        kritRegenspende = new JTextField();
        vorhandVolumen = new JTextField();
        spezVolumen = new JTextField();
        minVolumen = new JTextField();

        FormLayout layout = new FormLayout(
                "r:80dlu, 5dlu, 180dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
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
        builder.append("Anordnung des Regenklärbeckens", anordungBox);
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
        builder.append("Spezifisches Speichervolumen", spezVolumen);
        builder.nextLine();
        builder.append("Mindestspeichervolumen", minVolumen);
    }

    public List<JComponent> getFields() {
        return null;
    }

    public void save() {

    }

    public void setData(Sonderbauwerk sonderbauwerk) {

    }
}