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
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstAnlage;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstMessst;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstStoffe;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnfallstelle;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaAnlage;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetrieb;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetriebseinrichtung;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaGenehmigung;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaMessstAnlage;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaMessstelle;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaUebergabestelle;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.DeaAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaAnfallstAnlageModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaAnfallstMessstModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaAnfallstStoffeModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaAnfallstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaAnlageModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaBetriebModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaBetriebseinrichtungModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaGenehmigungModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaMessstAnlageModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaMessstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaUebergabestelleModel;
import de.bielefeld.umweltamt.aui.tipi.CredentialsDialog;
import de.bielefeld.umweltamt.aui.tipi.ServiceManager;
import de.nrw.lds.tipi.inka.Dea_Adresse;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Anlage;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Messst;
import de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe;
import de.nrw.lds.tipi.inka.Inka_Anfallstelle;
import de.nrw.lds.tipi.inka.Inka_Anlage;
import de.nrw.lds.tipi.inka.Inka_Betrieb;
import de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung;
import de.nrw.lds.tipi.inka.Inka_Genehmigung;
import de.nrw.lds.tipi.inka.Inka_Messst_Anlage;
import de.nrw.lds.tipi.inka.Inka_Messstelle;
import de.nrw.lds.tipi.inka.Inka_Uebergabestelle;

/**
 * Modul zum Synchronisieren der lokalen Datenbank mit einem entfernten Service.
 */
public class DBSyncModul extends AbstractModul {

    private JPanel panel;

    // Model für Datensätze aus der Datenbank
    private DeaAdresseModel              addrDBModel;
    private InkaBetriebModel             betriebDBModel;
    private InkaGenehmigungModel         genehmigungDBModel;
    private InkaBetriebseinrichtungModel betriebseinrDBModel;
    private InkaUebergabestelleModel     uebergabestelleDBModel;
    private InkaMessstelleModel          messstelleDBModel;
    private InkaAnfallstelleModel        anfallstelleDBModel;
    private InkaAnlageModel              anlageDBModel;
    private InkaMessstAnlageModel        messstAnlageDBModel;
    private InkaAnfallstMessstModel      anfallstMessstDBModel;
    private InkaAnfallstAnlageModel      anfallstAnlageDBModel;
    private InkaAnfallstStoffeModel      anfallstStoffeDBModel;

    // Model für Datensätze des entferneten Service.
    private DeaAdresseModel              addrServiceModel;
    private InkaBetriebModel             betriebServiceModel;
    private InkaGenehmigungModel         genehmigungServiceModel;
    private InkaBetriebseinrichtungModel betriebseinrServiceModel;
    private InkaUebergabestelleModel     uebergabestelleServiceModel;
    private InkaMessstelleModel          messstelleServiceModel;
    private InkaAnfallstelleModel        anfallstelleServiceModel;
    private InkaAnlageModel              anlageServiceModel;
    private InkaMessstAnlageModel        messstAnlageServiceModel;
    private InkaAnfallstMessstModel      anfallstMessstServiceModel;
    private InkaAnfallstAnlageModel      anfallstAnlageServiceModel;
    private InkaAnfallstStoffeModel      anfallstStoffeServiceModel;

    private JTable dbTable;
    private JLabel rowCount;

    // Listen mit zu übertragenden Daten.
    private List<Dea_Adresse>              sendAddr;
    private List<Inka_Betrieb>             sendBetrieb;
    private List<Inka_Genehmigung>         sendGenehmigung;
    private List<Inka_Betriebseinrichtung> sendBetriebseinrichtung;
    private List<Inka_Uebergabestelle>     sendUebergabestelle;
    private List<Inka_Messstelle>          sendMessstelle;
    private List<Inka_Anfallstelle>        sendAnfallstelle;
    private List<Inka_Anlage>              sendAnlage;
    private List<Inka_Messst_Anlage>       sendMessstAnlage;
    private List<Inka_Anfallst_Messst>     sendAnfallstMessst;
    private List<Inka_Anfallst_Anlage>     sendAnfallstAnlage;
    private List<Inka_Anfallst_Stoffe>     sendAnfallstStoffe;

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
        this.genehmigungDBModel = new InkaGenehmigungModel();
        this.genehmigungServiceModel = new InkaGenehmigungModel();
        this.betriebseinrDBModel = new InkaBetriebseinrichtungModel();
        this.betriebseinrServiceModel = new InkaBetriebseinrichtungModel();
        this.uebergabestelleDBModel = new InkaUebergabestelleModel();
        this.uebergabestelleServiceModel = new InkaUebergabestelleModel();
        this.messstelleDBModel = new InkaMessstelleModel();
        this.messstelleServiceModel = new InkaMessstelleModel();
        this.anfallstelleDBModel = new InkaAnfallstelleModel();
        this.anfallstelleServiceModel = new InkaAnfallstelleModel();
        this.anlageDBModel = new InkaAnlageModel();
        this.anlageServiceModel = new InkaAnlageModel();
        this.messstAnlageDBModel = new InkaMessstAnlageModel();
        this.messstAnlageServiceModel = new InkaMessstAnlageModel();
        this.anfallstMessstDBModel = new InkaAnfallstMessstModel();
        this.anfallstMessstServiceModel = new InkaAnfallstMessstModel();
        this.anfallstAnlageDBModel = new InkaAnfallstAnlageModel();
        this.anfallstAnlageServiceModel = new InkaAnfallstAnlageModel();
        this.anfallstStoffeDBModel = new InkaAnfallstStoffeModel();
        this.anfallstStoffeServiceModel = new InkaAnfallstStoffeModel();

        this.sendAddr = new ArrayList<Dea_Adresse>();
        this.sendBetrieb = new ArrayList<Inka_Betrieb>();
        this.sendGenehmigung = new ArrayList<Inka_Genehmigung>();
        this.sendBetriebseinrichtung = new ArrayList<Inka_Betriebseinrichtung>();
        this.sendUebergabestelle = new ArrayList<Inka_Uebergabestelle>();
        this.sendMessstelle = new ArrayList<Inka_Messstelle>();
        this.sendAnfallstelle = new ArrayList<Inka_Anfallstelle>();
        this.sendAnlage = new ArrayList<Inka_Anlage>();
        this.sendMessstAnlage = new ArrayList<Inka_Messst_Anlage>();
        this.sendAnfallstMessst = new ArrayList<Inka_Anfallst_Messst>();
        this.sendAnfallstAnlage = new ArrayList<Inka_Anfallst_Anlage>();
        this.sendAnfallstStoffe = new ArrayList<Inka_Anfallst_Stoffe>();

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
                    if (item.equals("inka_genehmigung")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.genehmigungDBModel);
                    }
                    if (item.equals("inka_betriebseinrichtung")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.betriebseinrDBModel);
                    }
                    if (item.equals("inka_uebergabestelle")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.uebergabestelleDBModel);
                    }
                    if (item.equals("inka_messstelle")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.messstelleDBModel);
                    }
                    if (item.equals("inka_anfallstelle")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.anfallstelleDBModel);
                    }
                    if (item.equals("inka_anlage")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.anlageDBModel);
                    }
                    if (item.equals("inka_messst_anlage")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.messstAnlageDBModel);
                    }
                    if (item.equals("inka_anfallst_messst")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.anfallstMessstDBModel);
                    }
                    if (item.equals("inka_anfallst_anlage")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.anfallstAnlageDBModel);
                    }
                    if (item.equals("inka_anfallst_stoffe")) {
                        DBSyncModul.this.dbTable
                            .setModel(DBSyncModul.this.anfallstStoffeDBModel);
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
                        if (sel.equals("inka_genehmigung")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.genehmigungDBModel);
                        }
                        if (sel.equals("inka_betriebseinrichtung")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.betriebseinrDBModel);
                        }
                        if (sel.equals("inka_uebergabestelle")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.uebergabestelleDBModel);
                        }
                        if (sel.equals("inka_messstelle")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.messstelleDBModel);
                        }
                        if (sel.equals("inka_anfallstelle")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.anfallstelleDBModel);
                        }
                        if (sel.equals("inka_anlage")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.anlageDBModel);
                        }
                        if (sel.equals("inka_messst_anlage")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.messstAnlageDBModel);
                        }
                        if (sel.equals("inka_anfallst_messst")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.anfallstMessstDBModel);
                        }
                        if (sel.equals("inka_anfallst_anlage")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.anfallstAnlageDBModel);
                        }
                        if (sel.equals("inka_anfallst_stoffe")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.anfallstStoffeDBModel);
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
                        if (sel.equals("inka_genehmigung")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.genehmigungServiceModel);
                        }
                        if (sel.equals("inka_betriebseinrichtung")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.betriebseinrServiceModel);
                        }
                        if (sel.equals("inka_uebergabestelle")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.uebergabestelleServiceModel);
                        }
                        if (sel.equals("inka_messstelle")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.messstelleServiceModel);
                        }
                        if (sel.equals("inka_anfallstelle")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.anfallstelleServiceModel);
                        }
                        if (sel.equals("inka_anlage")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.anlageServiceModel);
                        }
                        if (sel.equals("inka_messst_anlage")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.messstAnlageServiceModel);
                        }
                        if (sel.equals("inka_anfallst_messst")) {
                            DBSyncModul.this.dbTable
                                .setModel(DBSyncModul.this.anfallstMessstServiceModel);
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
                        // Note: Take care to transmit in the right order!
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendAddr);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendBetrieb);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendGenehmigung);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendBetriebseinrichtung);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendUebergabestelle);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendMessstelle);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendAnfallstelle);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendAnlage);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendMessstAnlage);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendAnfallstMessst);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendAnfallstAnlage);
                        DBSyncModul.this.service.setInkaDataRecords(
                            user, password, DBSyncModul.this.sendAnfallstStoffe);
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
                if (entities[i].equals("inka_genehmigung")) {
                    this.genehmigungDBModel.setList(InkaGenehmigung.getAll());
                }
                if (entities[i].equals("inka_betriebseinrichtung")) {
                    this.betriebseinrDBModel.setList(
                        InkaBetriebseinrichtung.getAll());
                }
                if (entities[i].equals("inka_uebergabestelle")) {
                    this.uebergabestelleDBModel.setList(
                        InkaUebergabestelle.getAll());
                }
                if (entities[i].equals("inka_messstelle")) {
                    this.messstelleDBModel.setList(InkaMessstelle.getAll());
                }
                if (entities[i].equals("inka_anfallstelle")) {
                    this.anfallstelleDBModel.setList(InkaAnfallstelle.getAll());
                }
                if (entities[i].equals("inka_anlage")) {
                    this.anlageDBModel.setList(InkaAnlage.getAll());
                }
                if (entities[i].equals("inka_messst_anlage")) {
                    this.messstAnlageDBModel.setList(InkaMessstAnlage.getAll());
                }
                if (entities[i].equals("inka_anfallst_messst")) {
                    this.anfallstMessstDBModel.setList(
                        InkaAnfallstMessst.getAll());
                }
                if (entities[i].equals("inka_anfallst_anlage")) {
                    this.anfallstAnlageDBModel.setList(
                        InkaAnfallstAnlage.getAll());
                }
                if (entities[i].equals("inka_anfallst_stoffe")) {
                    this.anfallstStoffeDBModel.setList(
                        InkaAnfallstStoffe.getAll());
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
            this.addrServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Dea_Adresse()));
            this.betriebServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Betrieb()));
            this.genehmigungServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Genehmigung()));
            this.betriebseinrServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Betriebseinrichtung()));
            this.uebergabestelleServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Uebergabestelle()));
            this.messstelleServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Messstelle()));
            this.anfallstelleServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Anfallstelle()));
            this.anlageServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Anlage()));
            this.messstAnlageServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Messst_Anlage()));
            this.anfallstMessstServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Anfallst_Messst()));
            this.anfallstAnlageServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Anfallst_Anlage()));
            this.anfallstStoffeServiceModel.setList(
                this.service.getInkaDataRecords(
                    user, password, new Inka_Anfallst_Stoffe()));
            // Add new DEA/INKA-Table here
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
            this.sendBetriebseinrichtung.add(((InkaBetriebseinrichtung) list3.get(i))
                .toServiceType());
        }
        List<?> list4 = this.genehmigungDBModel.getList();
        for (int i = 0; i < list4.size(); i++) {
            this.sendGenehmigung.add(((InkaGenehmigung) list4.get(i))
                .toServiceType());
        }
        List<?> list5 = this.anfallstelleDBModel.getList();
        for (int i = 0; i < list5.size(); i++) {
            this.sendAnfallstelle.add(((InkaAnfallstelle) list5.get(i))
                .toServiceType());
        }
        List<?> list6 = this.messstelleDBModel.getList();
        for (int i = 0; i < list6.size(); i++) {
            this.sendMessstelle.add(((InkaMessstelle) list6.get(i))
                .toServiceType());
        }
        List<?> list7 = this.uebergabestelleDBModel.getList();
        for (int i = 0; i < list7.size(); i++) {
            this.sendUebergabestelle.add(((InkaUebergabestelle) list7.get(i))
                .toServiceType());
        }
        List<?> list8 = this.anlageDBModel.getList();
        for (int i = 0; i < list8.size(); i++) {
            this.sendAnlage.add(((InkaAnlage) list8.get(i))
                .toServiceType());
        }
        List<?> tempList = this.messstAnlageDBModel.getList();
        for (Object obj : tempList) {
            this.sendMessstAnlage.add(
                ((InkaMessstAnlage) obj).toServiceType());
        }
        tempList = this.anfallstMessstDBModel.getList();
        for (Object obj : tempList) {
            this.sendAnfallstMessst.add(
                ((InkaAnfallstMessst) obj).toServiceType());
        }
        tempList = this.anfallstAnlageDBModel.getList();
        for (Object obj : tempList) {
            this.sendAnfallstAnlage.add(
                ((InkaAnfallstAnlage) obj).toServiceType());
        }
        tempList = this.anfallstStoffeDBModel.getList();
        for (Object obj : tempList) {
            this.sendAnfallstStoffe.add(
                ((InkaAnfallstStoffe) obj).toServiceType());
        }
    }
}
