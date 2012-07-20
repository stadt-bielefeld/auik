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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.tipi.DeaAdresse;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetrieb;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetriebseinrichtung;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaGenehmigung;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.DeaAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaBetriebModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaBetriebseinrichtungModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaGenehmigungModel;
import de.bielefeld.umweltamt.aui.tipi.CredentialsDialog;
import de.bielefeld.umweltamt.aui.tipi.ServiceManager;
import de.nrw.lds.tipi.inka.Dea_Adresse;
import de.nrw.lds.tipi.inka.Inka_Betrieb;
import de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung;
import de.nrw.lds.tipi.inka.Inka_Genehmigung;

/**
 * Modul zum Synchronisieren der lokalen Datenbank mit einem entfernten Service.
 */
public class DBSyncModul extends AbstractModul {

    private JPanel panel;

    // Model fürDatensätze aus der Datenbank
    private DeaAdresseModel addrDBModel;
    private InkaBetriebModel betriebDBModel;
    private InkaBetriebseinrichtungModel betriebseinrDBModel;
    private InkaGenehmigungModel genehmigungDBModel;

    // Model für Datensätze des entferneten Service.
    private DeaAdresseModel addrServiceModel;
    private InkaBetriebModel betriebServiceModel;
    private InkaBetriebseinrichtungModel betriebseinrServiceModel;
    private InkaGenehmigungModel genehmigungServiceModel;

    private JTable dbTable;
    private JLabel rowCount;

    // Listen mit zu übertragenden Daten.
    private List<Dea_Adresse> sendAddr;
    private List<Inka_Betrieb> sendBetrieb;
    private List<Inka_Betriebseinrichtung> sendBetriebseinr;
    private List<Inka_Genehmigung> sendGenehm;

    private ServiceManager service;

    // Logindaten für den entfernten Service.
    private static String url;
    private static String user;
    private static String password;

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
        url = null;
        user = null;
        password = null;
        this.dbTable = new JTable();
        this.rowCount = new JLabel("0");
        this.addrDBModel = new DeaAdresseModel();
        this.addrServiceModel = new DeaAdresseModel();

        this.betriebDBModel = new InkaBetriebModel();
        this.betriebServiceModel = new InkaBetriebModel();

        this.betriebseinrDBModel = new InkaBetriebseinrichtungModel();
        this.betriebseinrServiceModel = new InkaBetriebseinrichtungModel();

        this.genehmigungDBModel = new InkaGenehmigungModel();
        this.genehmigungServiceModel = new InkaGenehmigungModel();

        this.sendAddr = new ArrayList<Dea_Adresse>();
        this.sendBetrieb = new ArrayList<Inka_Betrieb>();
        this.sendBetriebseinr = new ArrayList<Inka_Betriebseinrichtung>();
        this.sendGenehm = new ArrayList<Inka_Genehmigung>();

        this.service = ServiceManager.getInstance();
    }

    private JPanel createPanel() {
        init();
        if (this.panel == null) {
            String[] entities = readSyncableEntities();
            this.panel = new JPanel();
            final JComboBox compare = new JComboBox();
            final JComboBox selection = new JComboBox();
            if (entities != null && entities.length > 0) {
                for (String entity : entities) {
                    selection.addItem(entity);
                }
            }
            selection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String item = (String) selection.getSelectedItem();
                    if (item.equals("dea_adresse")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.addrDBModel);
                    }
                    if (item.equals("inka_betrieb")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.betriebDBModel);
                    }
                    if (item.equals("inka_betriebseinrichtung")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.betriebseinrDBModel);
                    }
                    if (item.equals("inka_genehmigung")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.genehmigungDBModel);
                    }
                    compare.setSelectedItem(compare.getItemAt(0));
                    DBSyncModul.this.rowCount.setText(String
                        .valueOf(DBSyncModul.this.dbTable.getRowCount()));
                }
            });

            compare.addItem("lokale Datenbank");
            compare.addItem("entferner Dienst");
            compare.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String item = (String) compare.getSelectedItem();
                    String sel = (String) selection.getSelectedItem();
                    if (item.equals("lokale Datenbank")) {
                        if (sel.equals("dea_adresse")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.addrDBModel);
                        }
                        if (sel.equals("inka_betrieb")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.betriebDBModel);
                        }
                        if (sel.equals("inka_betriebseinrichtung")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.betriebseinrDBModel);
                        }
                        if (sel.equals("inka_genehmigung")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.genehmigungDBModel);
                        }
                    }
                    if (item.equals("entferner Dienst")) {
                        if (sel.equals("dea_adresse")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.addrServiceModel);
                        }
                        if (sel.equals("inka_betrieb")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.betriebServiceModel);
                        }
                        if (sel.equals("inka_betriebseinrichtung")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.betriebseinrServiceModel);
                        }
                        if (sel.equals("inka_genehmigung")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.genehmigungServiceModel);
                        }
                    }
                    DBSyncModul.this.rowCount.setText(String
                        .valueOf(DBSyncModul.this.dbTable.getRowCount()));
                }
            });

            JButton get = new JButton("Daten von ext. Dienst holen");
            get.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    CredentialsDialog dialog = new CredentialsDialog(
                        DBSyncModul.this);
                    dialog.setVisible(true);
                }
            });

            JButton set = new JButton("Alle Daten übertragen");
            set.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (url != null && user != null && password != null) {
                        dbToService();
                        Dea_Adresse[] addr = new Dea_Adresse[DBSyncModul.this.sendAddr
                            .size()];
                        addr = DBSyncModul.this.sendAddr.toArray(addr);
                        Inka_Betrieb[] betr = new Inka_Betrieb[DBSyncModul.this.sendBetrieb
                            .size()];
                        betr = DBSyncModul.this.sendBetrieb.toArray(betr);
                        Inka_Betriebseinrichtung[] betreinr = new Inka_Betriebseinrichtung[DBSyncModul.this.sendBetriebseinr
                            .size()];
                        betreinr = DBSyncModul.this.sendBetriebseinr
                            .toArray(betreinr);
                        Inka_Genehmigung[] genehm = new Inka_Genehmigung[DBSyncModul.this.sendGenehm
                            .size()];
                        genehm = DBSyncModul.this.sendGenehm.toArray(genehm);
                        DBSyncModul.this.service.setDea_Adressen(user,
                            password, addr);
                        DBSyncModul.this.service.setInka_Betriebe(user,
                            password, betr);
                        DBSyncModul.this.service.setInka_Genehmigungen(user,
                            password, genehm);
                        DBSyncModul.this.service.setInka_Betriebseinrichtungen(
                            user, password, betreinr);
                    } else {
                        GUIManager.getInstance().showInfoMessage(
                            "Bitte holen Sie zuerst Daten ab!", "Info");
                    }
                }
            });

            FormLayout layout = new FormLayout(
                "pref, 3dlu, 90dlu, 3dlu, 90dlu, 3dlu:grow(1.0)",
                "pref, 3dlu, pref, 3dlu, pref, 3dlu, 150dlu:grow(1.0), 3dlu, pref");
            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            this.dbTable.setModel(this.addrDBModel);
            JScrollPane dbScroller = new JScrollPane(this.dbTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            builder.addLabel("Tabelle", cc.xy(1, 1));
            builder.add(selection, cc.xy(3, 1));
            builder.add(compare, cc.xy(5, 1));
            builder.add(get, cc.xy(1, 3));
            builder.add(set, cc.xy(1, 5));
            builder.add(dbScroller, cc.xyw(1, 7, 6));
            builder.addLabel("Anzahl der Elemente: ", cc.xy(1, 9));
            builder.add(this.rowCount, cc.xy(3, 9));

            this.panel = builder.getPanel();

        }

        return this.panel;
    }

    /**
     * Liest die synchronisierbaren Tabellen aus den Settings und liest die
     * Datensätzeaus.
     */
    private String[] readSyncableEntities() {
        SettingsManager sm = this.manager.getSettingsManager();
        String setting = sm.getSetting("auik.system.db.syncable");
        if (setting != null && setting.length() != 0) {
            String[] entities = setting.split(",");
            for (int i = 0; i < entities.length; i++) {
                entities[i] = entities[i].trim();
                if (entities[i].equals("dea_adresse")) {
                    this.addrDBModel.setList(DeaAdresse.getAll());
                }
                if (entities[i].equals("inka_betrieb")) {
                    this.betriebDBModel.setList(InkaBetrieb.getAll());
                }
                if (entities[i].equals("inka_betriebseinrichtung")) {
                    this.betriebseinrDBModel.setList(InkaBetriebseinrichtung
                        .getAll());
                }
                if (entities[i].equals("inka_genehmigung")) {
                    this.genehmigungDBModel.setList(InkaGenehmigung.getAll());
                }
            }
            return entities;
        }
        return null;
    }

    public static void setServiceUrl(String url) {
        DBSyncModul.url = url;
    }

    public static void setServiceUser(String user) {
        DBSyncModul.user = user;
    }

    public static void setServicePassword(String password) {
        DBSyncModul.password = password;
    }

    /**
     * Holt die Daten von dem Dienst und speichert diese in den dafür vorgesehen
     * Models.
     */
    public void requestData() {
        // TODO validation, exception handling etc.
        if (user != null && password != null && url != null) {
            this.service.setInkaEndpointAdress(url);
            Dea_Adresse[] adr = this.service.getDea_Adressen(user, password);
            List<Dea_Adresse> adressen = new ArrayList<Dea_Adresse>();
            for (int i = 0; i < adr.length; i++) {
                adressen.add(adr[i]);
            }
            Inka_Betrieb[] betr = this.service.getInka_Betriebe(user, password);
            List<Inka_Betrieb> betriebe = new ArrayList<Inka_Betrieb>();
            for (int i = 0; i < betr.length; i++) {
                betriebe.add(betr[i]);
            }
            Inka_Betriebseinrichtung[] betrein = this.service
                .getInka_Betriebseinrichtungen(user, password);
            List<Inka_Betriebseinrichtung> betriebseinrichtungen = new ArrayList<Inka_Betriebseinrichtung>();
            for (int i = 0; i < betrein.length; i++) {
                betriebseinrichtungen.add(betrein[i]);
            }
            Inka_Genehmigung[] genehm = this.service.getInka_Genehmigungen(
                user, password);
            List<Inka_Genehmigung> genehmigungen = new ArrayList<Inka_Genehmigung>();
            for (int i = 0; i < genehm.length; i++) {
                genehmigungen.add(genehm[i]);
            }
            this.addrServiceModel.setList(adressen);
            this.betriebServiceModel.setList(betriebe);
            this.betriebseinrServiceModel.setList(betriebseinrichtungen);
            this.genehmigungServiceModel.setList(genehmigungen);
        }
    }

    /**
     * Konvertiert die Datesätze aus der lokalen Datenbank in die Datentypen des
     * Dienstes.
     */
    private void dbToService() {
        this.sendAddr.clear();
        List<?> list1 = this.addrDBModel.getList();
        for (int i = 0; i < list1.size(); i++) {
            this.sendAddr.add(((DeaAdresse) list1.get(i)).toServiceType());
        }
        List<?> list2 = this.betriebDBModel.getList();
        for (int i = 0; i < list2.size(); i++) {
            this.sendBetrieb.add(((InkaBetrieb) list2.get(i)).toServiceType());
        }
        List<?> list3 = this.betriebseinrDBModel.getList();
        for (int i = 0; i < list3.size(); i++) {
            this.sendBetriebseinr.add(((InkaBetriebseinrichtung) list3.get(i))
                .toServiceType());
        }
        List<?> list4 = this.genehmigungDBModel.getList();
        for (int i = 0; i < list4.size(); i++) {
            this.sendGenehm.add(((InkaGenehmigung) list4.get(i))
                .toServiceType());
        }
    }
}
