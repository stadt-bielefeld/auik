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

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ZuordnungChooser;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

public class RBFPanel extends AbstractSonderbauwerkTypPanel {
    private static final long serialVersionUID = 4242458251785488488L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    //Fields
    private JTextField stauvolumenField;
    private JTextField volumenField;
    private JTextField filterField;
    private JTextField drossField;
    private JTextField filterGeschField;
    private JTextField beschickungsHoheField;
    private JTextField hydWirkungsgradField;
    private JTextField filterStaerkeField;
    private JTextField ueberlaufHaeufField;
    private JTextField filterVolumenField;

    //Labels
    private JLabel stauvolumenLabel;
    private JLabel volumenLabel;
    private JLabel filterLabel;
    private JLabel drossLabel;
    private JLabel filterGeschLabel;
    private JLabel beschickungsHoheLabel;
    private JLabel hydWirkungsgradLabel;
    private JLabel filterStaerkeLabel;
    private JLabel ueberlaufHaeufLabel;
    private JLabel filterVolumenLabel;

    private ZuordnungChooser<String> schutzgueterChooser;

    private BasisObjektBearbeiten parentModule;

    public RBFPanel (BasisObjektBearbeiten parentModule) {
        this.name = "RBF";
        this.parentModule = parentModule;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createFields();

        FormLayout layout = new FormLayout(
                "r:180dlu, 5dlu, 180dlu, 5dlu, r:35dlu, 5dlu, 80dlu", // Spalten
            "");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout);
        builder.append(stauvolumenLabel, stauvolumenField);
        builder.nextLine();
        builder.append(volumenLabel, volumenField);
        builder.nextLine();
        builder.append(filterLabel, filterField);
        builder.nextLine();
        builder.append(drossLabel, drossField);
        builder.nextLine();
        builder.append(filterGeschLabel, filterGeschField);
        builder.nextLine();
        builder.append(beschickungsHoheLabel, beschickungsHoheField);
        builder.nextLine();
        builder.append(hydWirkungsgradLabel, hydWirkungsgradField);
        builder.nextLine();
        builder.append(filterStaerkeLabel, filterStaerkeField);
        builder.nextLine();
        builder.append(ueberlaufHaeufLabel, ueberlaufHaeufField);
        builder.nextLine();
        builder.append(filterVolumenLabel, filterVolumenField);
        builder.nextLine();
        
        this.add(builder.build());
        this.add(schutzgueterChooser);
    }

    private void createFields() {
        stauvolumenField = new JTextField();
        volumenField = new JTextField();
        filterField = new JTextField();
        drossField = new JTextField();
        filterGeschField = new JTextField();
        beschickungsHoheField = new JTextField();
        hydWirkungsgradField = new JTextField();
        filterStaerkeField = new JTextField();
        ueberlaufHaeufField = new JTextField();
        filterVolumenField = new JTextField();

        stauvolumenLabel = new JLabel("Stauvolumen über dem Filterkörper");
        volumenLabel = new JLabel("Volumen der Speicherlamelle");
        filterLabel = new JLabel("Filterfäche");
        drossLabel = new JLabel("Rechnerischer Drosseldurchfluss (bei halber Volumenfüllung");
        filterGeschLabel = new JLabel("Filtergeschwindigkeit");
        beschickungsHoheLabel = new JLabel("Beschickungshöhe/mittlere Filterbelastung");
        hydWirkungsgradLabel = new JLabel("Hydraulischer Wirkungsgrad");
        filterStaerkeLabel = new JLabel("Stärke des Filtersubstrats");
        ueberlaufHaeufLabel = new JLabel("Überlaufhäufigkeit");
        filterVolumenLabel = new JLabel("Spetifisches Filtervolumen");

        schutzgueterChooser = new ZuordnungChooser<String>("Schutzgüter");

        createMappings();
    }

    private void createMappings() {
        this.fieldMapping = new HashMap<String, RecordMap>();
    }

    public Object getFieldValue(String fieldName) {
        return null;
    }

    public void save() {
        super.save();
        //TODO: save schutgueter
    }
}