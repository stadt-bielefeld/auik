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

/*
 * Created on 12.01.2005
 */
package de.bielefeld.umweltamt.aui.module;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.nrw.lds.tipi.inka.Dea_Adresse;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.AbstractModul;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.tipi.ServiceManager;
import de.bielefeld.umweltamt.aui.mappings.tipi.DeaAdresse;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetrieb;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetriebseinrichtung;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaGenehmigung;

import de.bielefeld.umweltamt.aui.module.common.tablemodels.DeaAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaBetriebModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaBetriebseinrichtungModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaGenehmigungModel;

public class DBSyncModul extends AbstractModul {

    /** Logging */
    private static final AuikLogger logger = AuikLogger.getLogger();

    private JPanel panel;
    private DeaAdresseModel addrModel;
    private InkaBetriebModel betriebModel;
    private InkaBetriebseinrichtungModel betriebseinrModel;
    private InkaGenehmigungModel genehmigungModel;
    private JTable dbTable;
    private JTable serviceTable;

    private ServiceManager service;

    @Override
    public JPanel getPanel() {
        return createPanel();
    }

    @Override
    public String getCategory() {
        return "Betriebe";
    }

    @Override
    public String getName() {
        return "Inka Datenbank - abgleich";
    }

    public void init() {
        dbTable = new JTable();
        serviceTable = new JTable();
        service = ServiceManager.getInstance();
        service.setInkaEndpointAdress("http://ip231.hq.intevation.de/inka_1_60utm/services/InkaInterface?wdsl");
    }

    private JPanel createPanel() {
        init();
        if(panel == null) {
            String[] entities = readSyncableEntities();
            this.panel = new JPanel();
            final JComboBox selection = new JComboBox();
            if (entities != null && entities.length > 0) {
                for (String entity: entities) {
                    selection.addItem(entity);
                }
            }
            selection.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String item = (String) selection.getSelectedItem();
                    if (item.equals("dea_adresse")) {
                        dbTable.setModel(addrModel);
                    }
                    if (item.equals("inka_betrieb")) {
                        dbTable.setModel(betriebModel);
                    }
                    if (item.equals("inka_betriebseinrichtung")) {
                        dbTable.setModel(betriebseinrModel);
                    }
                    if (item.equals("inka_genehmigung")) {
                        dbTable.setModel(genehmigungModel);
                    }
                }
            });

            JButton get = new JButton("GET");
            get.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    //TODO Read the entities using inka service.
                    service.getDea_Adressen();
                }
            });

            JButton set = new JButton("SET");
            set.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    //TODO Read the entities using inka service.
                    Dea_Adresse[] addr = new Dea_Adresse[1];
                    addr[0] = new Dea_Adresse();
                    addr[0].setName1("testuser");
                    service.setDea_Adressen(addr);
                }
            });

            FormLayout layout = new FormLayout("pref, 3dlu, 70dlu, 150dlu:grow(1.0), 150dlu:grow(1.0)",
                                               "pref, 3dlu, pref, 150dlu:grow(1.0), pref");
            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            dbTable.setModel(addrModel);
            JScrollPane dbScroller = new JScrollPane(dbTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            JScrollPane serviceScroller = new JScrollPane(serviceTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            builder.addLabel("Tabelle", cc.xy(1, 1));
            builder.add(selection, cc.xy(3,1));
        //    builder.add(get, cc.xy(1, 3));
        //    builder.add(set, cc.xy(3, 3));
            builder.addLabel("Datenbank", cc.xy(1, 3));

            builder.add(dbScroller, cc.xyw(1,4,4));

            builder.addLabel("entfernter Service", cc.xy(5, 3));
            builder.add(serviceScroller, cc.xyw(5,4,1));

            this.panel = builder.getPanel();

        }


        return this.panel;
    }


    private String[] readSyncableEntities() {
        SettingsManager sm = this.manager.getSettingsManager();
        String setting = sm.getSetting("auik.system.db.syncable");
        if (setting != null && setting.length() != 0) {
            String[] entities = setting.split(",");
            for (int i = 0; i < entities.length; i++) {
                entities[i] = entities[i].trim();
                if (entities[i].equals("dea_adresse")) {
                    addrModel = new DeaAdresseModel();
                    addrModel.setList(DeaAdresse.getDeaAdressen());
                }
                if (entities[i].equals("inka_betrieb")) {
                    betriebModel = new InkaBetriebModel();
                    betriebModel.setList(InkaBetrieb.getInkaBetriebe());
                }
                if (entities[i].equals("inka_betriebseinrichtung")) {
                    betriebseinrModel = new InkaBetriebseinrichtungModel();
                    betriebseinrModel.setList(InkaBetriebseinrichtung.getInkaBetriebseinrichtungen());
                }
                if (entities[i].equals("inka_genehmigung")) {
                    genehmigungModel = new InkaGenehmigungModel();
                    genehmigungModel.setList(InkaGenehmigung.getInkaGenehmigungen());
                }
            }
            return entities;
        }
        return null;
    }
}
