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
package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.elka.Anhang;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisAbfrageModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Module providing queries for several base object types.
 * @author Alexander Woestmann
 */
public class BasisAbfrage extends AbstractQueryModul {

    private JPanel queryOptionsPanel;
    private BasisAbfrageModel tableModel;
    private JButton searchButton;

    //Main filters
    private JLabel typeLabel;
    private JComboBox<Objektarten> typeBox;
    private JLabel anhangLabel;
    private JComboBox<Anhang> anhangBox;
    private JLabel anlagenartenLabel;
    private JComboBox<String> anlagenartBox;

    //Additional filters
    private JLabel sachbearbeiterLabel;
    private JComboBox<Sachbearbeiter> sachbearbeiterBox;
    private JLabel entwGebieteLabel;
    private JComboBox<EinzGebBoxModel> einzGebietBox;
    private JLabel prioritaetLabel;
    private JComboBox<String> prioritaetBox;
    private JLabel wiedervorlagenLabel;
    private JComboBox<String> wiedervorlageBox;

    public static final String VALUE_WIEDERVORLAGE_AKTIV = "Aktiv";
    public static final String VALUE_WIEDERVORLAGE_ABGELAUFEN = "Abgelaufen";
    private final String GROUP_VALUE = "360.33";

    public BasisAbfrage () {
        //Init widgets
        List<Objektarten> types = Objektarten.getAll()
            .stream()
            .sorted((o1, o2) -> o1.getObjektart().compareTo(o2.getObjektart()))
            .collect(Collectors.toList());
        DefaultComboBoxModel<Objektarten> typeModel = new DefaultComboBoxModel<>(
            types.toArray(new Objektarten[types.size()]));
        typeBox = new JComboBox<>(typeModel);
        typeBox.insertItemAt(null, 0);
        typeBox.setSelectedIndex(0);
        typeLabel = new JLabel("Objektart:");

        List<Anhang> anhangs = Anhang.getAll();
        DefaultComboBoxModel<Anhang> anhangModel = new DefaultComboBoxModel<>(
            anhangs.toArray(new Anhang[anhangs.size()])
        );
        anhangBox = new JComboBox<>(anhangModel);
        anhangBox.insertItemAt(null, 0);
        anhangBox.setSelectedIndex(0);
        anhangBox.setEnabled(false);
        anhangLabel = new JLabel("Anhang:");

        String[] arten = {null, "Aufbereitung Medizinprodukte", "Blockheizkraftwerk",
            "Fettabscheider", "Gentechnikanlage", "Kompressorenanlage", "KWK Anlage", "Labor",
            "RLT Anlagen", "Schrottplatz", "Wärmetauscher"};
        anlagenartBox = new JComboBox<>(new DefaultComboBoxModel<>(arten));
        anlagenartBox.setEnabled(false);
        anlagenartenLabel = new JLabel("Anlagenart:");

        sachbearbeiterBox = new JComboBox<>();
        List<Sachbearbeiter> sachbearbeiter = Sachbearbeiter.getOrderedAll();
        sachbearbeiterBox.setModel(new DefaultComboBoxModel(sachbearbeiter.toArray()));
        //Insert dummy Sachbearbeiter used for group filtering
        Sachbearbeiter group = new Sachbearbeiter();
        group.setId(-1);
        group.setGehoertzuarbeitsgr(GROUP_VALUE);
        group.setName(GROUP_VALUE);
        sachbearbeiterBox.insertItemAt(group, 0);
        sachbearbeiterBox.insertItemAt(null, 0);
        sachbearbeiterBox.setSelectedIndex(0);
        sachbearbeiterLabel = new JLabel("Sachbearbeiter:");

        einzGebietBox = new JComboBox<EinzGebBoxModel>(
            new DefaultComboBoxModel<>(createEinzGebBoxModels()));
        einzGebietBox.insertItemAt(null, 0);
        einzGebietBox.setSelectedIndex(0);
        entwGebieteLabel = new JLabel("Entwässerungsgebiet:");

        String[] prios = {null, "1", "2", "3", "4"};
        prioritaetBox = new JComboBox<>(
            new DefaultComboBoxModel<>(prios));
        prioritaetLabel = new JLabel("Priorität");

        String[] wiedervorlageValues = {null, VALUE_WIEDERVORLAGE_ABGELAUFEN, VALUE_WIEDERVORLAGE_AKTIV};
        wiedervorlageBox = new JComboBox<>(
            new DefaultComboBoxModel<>(wiedervorlageValues)
        );
        wiedervorlagenLabel = new JLabel("Wiedervorlage:");

        searchButton = new JButton("Suchen");

        //Add listeners
        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Objektarten selected = (Objektarten) typeBox.getSelectedItem();
                boolean enabled = selected != null ? selected.getObjektart().equals("Anfallstelle") : false;
                anhangBox.setEnabled(enabled);
                anhangBox.setSelectedItem(null);
            }
        });
        anhangBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Anhang selected = (Anhang) anhangBox.getSelectedItem();
                boolean enabled = selected != null ? selected.getAnhangId().equals("99") : false;
                anlagenartBox.setEnabled(enabled);
                anlagenartBox.setSelectedItem(null);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeQuery();
            }
        });

        //Layout
        FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout);

        builder.append(typeLabel, typeBox);
        builder.append(sachbearbeiterLabel, sachbearbeiterBox);
        builder.append(wiedervorlagenLabel, wiedervorlageBox);
        builder.nextRow();

        builder.append(anhangLabel, anhangBox);
        builder.append(entwGebieteLabel, einzGebietBox);
        builder.nextRow();

        builder.append(anlagenartenLabel, anlagenartBox);
        builder.append(prioritaetLabel, prioritaetBox);
        builder.append(new JPanel(), searchButton, createExportButton());

        queryOptionsPanel = builder.build();
    }

    private void executeQuery() {
        SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
            @Override
            protected void doNonUILogic() {
                Objektarten art = (Objektarten) typeBox.getSelectedItem();
                Anhang anhang = (Anhang) anhangBox.getSelectedItem();
                String anlagenart = (String) anlagenartBox.getSelectedItem();
                EinzGebBoxModel einzGebModel = (EinzGebBoxModel) einzGebietBox.getSelectedItem();
                String[] einzGeb = einzGebModel != null ? einzGebModel.getValues(): null;
                String prior = (String) prioritaetBox.getSelectedItem();
                String wiedervorlage = (String) wiedervorlageBox.getSelectedItem();
                Sachbearbeiter sb = null;
                Sachbearbeiter group = null;
                Sachbearbeiter selectedSb = (Sachbearbeiter) sachbearbeiterBox.getSelectedItem();
                if (selectedSb != null && selectedSb.getId() == -1) {
                    group = selectedSb;
                } else {
                    sb = selectedSb;
                }
                List<Object> result = DatabaseQuery.executeBaseQuery(
                    art, anhang, anlagenart, sb, einzGeb, prior,wiedervorlage, group);
                ((BasisAbfrageModel)getTableModel()).setList(result);
            }
            @Override
            protected void doUIUpdateLogic(){
                ((BasisAbfrageModel)getTableModel()).fireTableDataChanged();
                frame.changeStatus("" +
                    getTableModel()
                    .getRowCount() + " Objekte gefunden");
            }
        };
        worker.start();
    }

    @Override
    public String getName() {
        return "Basis-Abfrage";
    }

    @Override
    public JPanel getQueryOptionsPanel() {
        return queryOptionsPanel;
    }

    @Override
    public ListTableModel getTableModel() {
        if (tableModel == null) {
            tableModel = new BasisAbfrageModel();
        }
        return tableModel;
    }

    @Override
    protected void editObject(int row) {
        if (row != -1) {
            Object[] rowObj = (Object[]) getTableModel().getObjectAtRow(row);
            Objekt obj = Objekt.findById((Integer) rowObj[11]);

            if (obj != null) {
                manager.getSettingsManager().setSetting("auik.imc.edit_object", obj.getId().intValue(), false);
                manager.switchModul("m_objekt_bearbeiten");
            }

        }
    }

    private EinzGebBoxModel[] createEinzGebBoxModels() {
        return new EinzGebBoxModel[]{
            new EinzGebBoxModel("Heepen (1.01-1.05)",
                new String[]{"1.01", "1.02", "1.03", "1.04", "1.05"}),
            new EinzGebBoxModel("Brake Ost (4.20-4-24)",
                new String[]{"4.20", "4.21", "4.22", "4.23", "4.24"}),
            new EinzGebBoxModel("Brake West (4.00-4.19)",
                new String[]{"4.00", "4.01", "4.02", "4.03", "4.04", "4.05",
                "4.06","4.07", "4.08", "4.09", "4.10", "4.11", "4.12",
                "4.13", "4.14", "4.15", "4.16", "4.17", "4.18", "4.19"}),
            new EinzGebBoxModel("AOL (5.28-5.31 und 7.32, 7.33.& 7.34)",
                new String[]{"5.28", "5.29", "5.30", "5.31",
                    "7.32", "7.33", "7.44"}),
            new EinzGebBoxModel("Sennestadt (8.35 & 8.36)",
                new String[]{"8.35", "8.36"}),
            new EinzGebBoxModel("Verl Sende (9.37)",
                new String[]{"9.37"})
        };
    }

    private class EinzGebBoxModel {
        private String displayField;
        private String[] values;
        public EinzGebBoxModel(String displayField, String[] values) {
            this.displayField = displayField;
            this.values = values;
        }
        public String[] getValues() {
            return values;
        }
        public String toString() {
            return this.displayField;
        }
    }
}
