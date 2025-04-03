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
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import org.apache.commons.collections.iterators.ArrayListIterator;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.ZRbfSchutzgueter;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.ZRbfSchutzgueterId;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ZuordnungChooser;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.CBoxItem;

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

    private ZuordnungChooser<CBoxItem> schutzgueterChooser;

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

        this.add(builder.getPanel());
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
        filterLabel = new JLabel("Filterfläche");
        drossLabel = new JLabel("Rechnerischer Drosseldurchfluss (bei halber Volumenfüllung");
        filterGeschLabel = new JLabel("Filtergeschwindigkeit");
        beschickungsHoheLabel = new JLabel("Beschickungshöhe/mittlere Filterbelastung");
        hydWirkungsgradLabel = new JLabel("Hydraulischer Wirkungsgrad");
        filterStaerkeLabel = new JLabel("Stärke des Filtersubstrats");
        ueberlaufHaeufLabel = new JLabel("Überlaufhäufigkeit");
        filterVolumenLabel = new JLabel("Spezifisches Filtervolumen");

        schutzgueterChooser = new ZuordnungChooser<CBoxItem>("Schutzgüter");

        createMappings();
    }

    private void createMappings() {
        this.fieldMapping = new HashMap<String, RecordMap>();
        this.addMapping("stauvolumenField", "rstauvolumen", "java.lang.Integer");
        this.addMapping("volumenField", "rvolSlamelle", "java.lang.Integer");
        this.addMapping("filterField", "rfilterflaeche", "java.lang.Integer");
        this.addMapping("drossField", "rdrosseldurchfluss", "java.math.BigDecimal");
        this.addMapping("filterGeschField", "rfiltergeschwin","java.math.BigDecimal");
        this.addMapping("beschickungsHoheField", "rmFilterbelastung", "java.math.BigDecimal");
        this.addMapping("hydWirkungsgradField", "rhydWirkungsgrad", "java.lang.Integer");
        this.addMapping("filterStaerkeField", "rfiltersubstratH", "java.math.BigDecimal");
        this.addMapping("ueberlaufHaeufField", "rjahrUeh", "java.math.BigDecimal");
        this.addMapping("filterVolumenField", "rspezFiltervol", "java.math.BigDecimal");

    }

    public void fetchFormData() {
        List<CBoxItem> schutzgueter = new ArrayList<CBoxItem>();
        schutzgueter.add(new CBoxItem(1, "Trinkwassergewinnung"));
        schutzgueter.add(new CBoxItem(2, "EU-Badegewässer"));
        schutzgueter.add(new CBoxItem(3, "Laichhabitate"));
        schutzgueter.add(new CBoxItem(4, "Stillgewässer"));
        schutzgueter.add(new CBoxItem(5, "Quellbereich"));
        schutzgueter.add(new CBoxItem(6, "sonstiges"));
        List<CBoxItem> selected = new ArrayList<CBoxItem>();
        Set<ZRbfSchutzgueter> zuordnungen = this.record.getZRbfSchutzgueters();
        if (zuordnungen != null) {
            zuordnungen.forEach(item -> {
                selected.add(schutzgueter.get(item.getId().getSchutzgueterOpt() - 1));
            });
        }
        this.schutzgueterChooser.setData(schutzgueter);
        this.schutzgueterChooser.applyEntries(selected);

        setTextFieldContent(stauvolumenField, this.record.getRstauvolumen());
        setTextFieldContent(volumenField, this.record.getRvolSlamelle());
        setTextFieldContent(filterField, this.record.getRfilterflaeche());
        setTextFieldContent(drossField, this.record.getRdrosseldurchfluss());
        setTextFieldContent(filterGeschField, this.record.getRfiltergeschwin());
        setTextFieldContent(beschickungsHoheField, this.record.getRmFilterbelastung());
        setTextFieldContent(hydWirkungsgradField, this.record.getRhydWirkungsgrad());
        setTextFieldContent(filterStaerkeField, this.record.getRfiltersubstratH());
        setTextFieldContent(ueberlaufHaeufField, this.record.getRjahrUeh());
        setTextFieldContent(filterVolumenField, this.record.getRspezFiltervol());

    }

    /**
     * Get field value by field name.
     * @param fieldName Field name
     * @return Field value
     */
    public Object getFieldValue(String fieldName) {
        return getFieldValue(fieldName, this);
    }

    public JTextField getStauvolumenField() {
        return this.stauvolumenField;
    }

    public JTextField getVolumenField() {
        return this.volumenField;
    }

    public JTextField getFilterField() {
        return this.filterField;
    }

    public JTextField getDrossField() {
        return this.drossField;
    }

    public JTextField getFilterGeschField() {
        return this.filterGeschField;
    }

    public JTextField getBeschickungsHoheField() {
        return this.beschickungsHoheField;
    }

    public JTextField getHydWirkungsgradField() {
        return this.hydWirkungsgradField;
    }

    public JTextField getFilterStaerkeField() {
        return this.filterStaerkeField;
    }

    public JTextField getUeberlaufHaeufField() {
        return this.ueberlaufHaeufField;
    }

    public JTextField getFilterVolumenField() {
        return this.filterVolumenField;
    }


    public void save() {
        schutzgueterChooser.getSelected().forEach(item -> {
            AtomicBoolean found = new AtomicBoolean(false);
            //Check if chosen schutzgut is already saved
            this.record.getZRbfSchutzgueters().forEach(z -> {
                if (z.getId().getSchutzgueterOpt().equals(item.getId())) {
                    found.set(true);
                }
            });
            //If not, add
            if (!found.get()) {
                ZRbfSchutzgueter newRec = new ZRbfSchutzgueter(
                        new ZRbfSchutzgueterId(this.record.getNr(), item.getId()), this.record);
                newRec.merge();
                this.record.getZRbfSchutzgueters().add(newRec);
            }
        });
        List<ZRbfSchutzgueter> removed = new ArrayList<>();
        //Remove unselected schutzgueter from record
        schutzgueterChooser.getUnselected().forEach(item -> {
            this.record.getZRbfSchutzgueters().forEach(z -> {
                if (item.getId().equals(z.getId().getSchutzgueterOpt())) {
                    removed.add(z);
                    z.delete();
                }
            });
        });
        this.record.getZRbfSchutzgueters().removeAll(removed);
        this.record.merge();
        super.save();
    }
}