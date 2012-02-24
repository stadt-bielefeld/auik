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
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.nrw.lds.tipi.inka.Dea_Adresse;
import de.nrw.lds.tipi.inka.Inka_Betrieb;
import de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung;
import de.nrw.lds.tipi.inka.Inka_Genehmigung;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.AbstractModul;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.tipi.ServiceManager;
import de.bielefeld.umweltamt.aui.tipi.CredentialsDialog;
import de.bielefeld.umweltamt.aui.mappings.tipi.DeaAdresse;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetrieb;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaBetriebseinrichtung;
import de.bielefeld.umweltamt.aui.mappings.tipi.InkaGenehmigung;

import de.bielefeld.umweltamt.aui.module.common.tablemodels.DeaAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaBetriebModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaBetriebseinrichtungModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.InkaGenehmigungModel;

/**
 * Modul zum Synchronisieren der lokalen Datenbank mit einem entfernten Service.
 */
public class DBSyncModul extends AbstractModul {

    /** Logging */
    private static final AuikLogger logger = AuikLogger.getLogger();

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
        dbTable = new JTable();
        rowCount = new JLabel("0");
        addrDBModel = new DeaAdresseModel();
        addrServiceModel = new DeaAdresseModel();

        betriebDBModel = new InkaBetriebModel();
        betriebServiceModel = new InkaBetriebModel();

        betriebseinrDBModel = new InkaBetriebseinrichtungModel();
        betriebseinrServiceModel = new InkaBetriebseinrichtungModel();

        genehmigungDBModel = new InkaGenehmigungModel();
        genehmigungServiceModel = new InkaGenehmigungModel();

        sendAddr = new ArrayList<Dea_Adresse>();
        sendBetrieb = new ArrayList<Inka_Betrieb>();
        sendBetriebseinr = new ArrayList<Inka_Betriebseinrichtung>();
        sendGenehm = new ArrayList<Inka_Genehmigung>();

        service = ServiceManager.getInstance();
    }


    private JPanel createPanel() {
        init();
        if(panel == null) {
            String[] entities = readSyncableEntities();
            this.panel = new JPanel();
            final JComboBox compare = new JComboBox();
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
                        dbTable.setModel(addrDBModel);
                    }
                    if (item.equals("inka_betrieb")) {
                        dbTable.setModel(betriebDBModel);
                    }
                    if (item.equals("inka_betriebseinrichtung")) {
                        dbTable.setModel(betriebseinrDBModel);
                    }
                    if (item.equals("inka_genehmigung")) {
                        dbTable.setModel(genehmigungDBModel);
                    }
                    compare.setSelectedItem(compare.getItemAt(0));
                    rowCount.setText(String.valueOf(dbTable.getRowCount()));
                }
            });

            compare.addItem("lokale Datenbank");
            compare.addItem("entferner Dienst");
            compare.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String item = (String) compare.getSelectedItem();
                    String sel = (String) selection.getSelectedItem();
                    if (item.equals("lokale Datenbank")) {
                        if (sel.equals("dea_adresse")) {
                            dbTable.setModel(addrDBModel);
                        }
                        if (sel.equals("inka_betrieb")) {
                            dbTable.setModel(betriebDBModel);
                        }
                        if (sel.equals("inka_betriebseinrichtung")) {
                            dbTable.setModel(betriebseinrDBModel);
                        }
                        if (sel.equals("inka_genehmigung")) {
                            dbTable.setModel(genehmigungDBModel);
                        }
                    }
                    if (item.equals("entferner Dienst")) {
                        if (sel.equals("dea_adresse")) {
                            dbTable.setModel(addrServiceModel);
                        }
                        if (sel.equals("inka_betrieb")) {
                            dbTable.setModel(betriebServiceModel);
                        }
                        if (sel.equals("inka_betriebseinrichtung")) {
                            dbTable.setModel(betriebseinrServiceModel);
                        }
                        if (sel.equals("inka_genehmigung")) {
                            dbTable.setModel(genehmigungServiceModel);
                        }
                    }
                    rowCount.setText(String.valueOf(dbTable.getRowCount()));
                }
            });


            JButton get = new JButton("Daten von ext. Dienst holen");
            get.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    CredentialsDialog dialog =
                        new CredentialsDialog(DBSyncModul.this);
                    dialog.show();
                }
            });

            JButton set = new JButton("Alle Daten übertragen");
            set.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if(url != null && user != null && password != null) {
                        dbToService();
                        Dea_Adresse[] addr = new Dea_Adresse[sendAddr.size()];
                        addr = sendAddr.toArray(addr);
                        Inka_Betrieb[] betr =
                            new Inka_Betrieb[sendBetrieb.size()];
                        betr = sendBetrieb.toArray(betr);
                        Inka_Betriebseinrichtung[] betreinr =
                            new Inka_Betriebseinrichtung[
                                sendBetriebseinr.size()];
                        betreinr = sendBetriebseinr.toArray(betreinr);
                        Inka_Genehmigung[] genehm =
                            new Inka_Genehmigung[sendGenehm.size()];
                        genehm = sendGenehm.toArray(genehm);
                        service.setDea_Adressen(user, password, addr);
                        service.setInka_Betriebe(user, password, betr);
                        service.setInka_Genehmigungen(user, password, genehm);
// Elements cannot be send to the server since there is a missing field: 'wz_code'.
//                        service.setInka_Betriebseinrichtungen(
//                            user,
//                            password,
//                            betreinr);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Bitte holen Sie zuerst Daten ab!");
                    }
                }
            });

            FormLayout layout = new FormLayout("pref, 3dlu, 90dlu, 3dlu, 90dlu, 3dlu:grow(1.0)",
                                               "pref, 3dlu, pref, 3dlu, pref, 3dlu, 150dlu:grow(1.0), 3dlu, pref");
            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            dbTable.setModel(addrDBModel);
            JScrollPane dbScroller = new JScrollPane(dbTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            builder.addLabel("Tabelle", cc.xy(1, 1));
            builder.add(selection, cc.xy(3,1));
            builder.add(compare, cc.xy(5,1));
            builder.add(get, cc.xy(1, 3));
            builder.add(set, cc.xy(1, 5));
            builder.add(dbScroller, cc.xyw(1, 7, 6));
            builder.addLabel("Anzahl der Elemente: ", cc.xy(1, 9));
            builder.add(rowCount, cc.xy(3, 9));

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
                    addrDBModel.setList(DeaAdresse.getDeaAdressen());
                }
                if (entities[i].equals("inka_betrieb")) {
                    betriebDBModel.setList(InkaBetrieb.getInkaBetriebe());
                }
                if (entities[i].equals("inka_betriebseinrichtung")) {
                    betriebseinrDBModel.setList
                        (InkaBetriebseinrichtung.getInkaBetriebseinrichtungen());
                }
                if (entities[i].equals("inka_genehmigung")) {
                    genehmigungDBModel.setList
                        (InkaGenehmigung.getInkaGenehmigungen());
                }
            }
            return entities;
        }
        return null;
    }

    public static void setServiceUrl (String url) {
        DBSyncModul.url = url;
    }

    public static void setServiceUser (String user) {
        DBSyncModul.user = user;
    }

    public static void setServicePassword(String password) {
        DBSyncModul.password = password;
    }


    /**
     * Holt die Daten von dem Dienst und speichert diese in den dafür
     * vorgesehen Models.
     */
    public void requestData() {
        // TODO validation, exception handling etc.
        if(user != null && password != null && url != null) {
            service.setInkaEndpointAdress(url);
            Dea_Adresse[] adr =
                service.getDea_Adressen(user, password);
            List<Dea_Adresse> adressen = new ArrayList<Dea_Adresse>();
            for (int i = 0; i < adr.length; i++) {
                adressen.add(adr[i]);
            }
            Inka_Betrieb[] betr = service.getInka_Betriebe(user, password);
            List<Inka_Betrieb> betriebe = new ArrayList<Inka_Betrieb>();
            for (int i = 0; i < betr.length; i++) {
                betriebe.add(betr[i]);
            }
            Inka_Betriebseinrichtung[] betrein =
                service.getInka_Betriebseinrichtungen(user, password);
            List<Inka_Betriebseinrichtung> betriebseinrichtungen =
                new ArrayList<Inka_Betriebseinrichtung>();
            for (int i = 0; i < betrein.length; i++) {
                betriebseinrichtungen.add(betrein[i]);
            }
            Inka_Genehmigung[] genehm =
                service.getInka_Genehmigungen(user, password);
            List<Inka_Genehmigung> genehmigungen =
                new ArrayList<Inka_Genehmigung>();
            for (int i = 0; i < genehm.length; i++) {
                genehmigungen.add(genehm[i]);
            }
            addrServiceModel.setList(adressen);
            betriebServiceModel.setList(betriebe);
            betriebseinrServiceModel.setList(betriebseinrichtungen);
            genehmigungServiceModel.setList(genehmigungen);
        }
    }


    /**
     * Konvertiert die Datesätze aus der lokalen Datenbank in die Datentypen
     * des Dienstes.
     */
    private void dbToService() {
        sendAddr.clear();
        List<DeaAdresse> list1 = addrDBModel.getList();
        for (int i = 0; i < list1.size(); i++) {
            sendAddr.add(list1.get(i).toServiceType());
        }
        List<InkaBetrieb> list2 = betriebDBModel.getList();
        for (int i = 0; i < list2.size(); i++) {
            sendBetrieb.add(list2.get(i).toServiceType());
        }
// Elements cannot be send to the server, we do not have to convert them.
//        List<InkaBetriebseinrichtung> list3 = betriebseinrDBModel.getList();
//        for (int i = 0; i < list3.size(); i++) {
//            sendBetriebseinr.add(list3.get(i).toServiceType());
//        }
        List<InkaGenehmigung> list4 = genehmigungDBModel.getList();
        for (int i = 0; i < list4.size(); i++) {
            sendGenehm.add(list4.get(i).toServiceType());
        }
    }
}
