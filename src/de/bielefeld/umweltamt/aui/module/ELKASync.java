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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.logging.LoggingFeature;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.gui.CredentialsDialog;
import de.bielefeld.umweltamt.aui.mappings.elka.Referenz;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAbwasserbehandlungsanlage;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAdresse;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAfsNiederschlagswasser;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAnfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EBetrieb;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EEinleitungsstelle;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EEntwaesserungsgrundstueck;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EMessstelle;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EProbenahme;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EProbenahmeUeberwachungsergeb;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.ESonderbauwerk;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EStandort;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EWasserrecht;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.AfsStoffe;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Massnahme;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.MsstBerichtspflicht;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.SbEntlastung;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.ZRbfSchutzgueter;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.ZSbRegeln;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EAbwasserbehandlungsanlageModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EAnfallstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EBetriebModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EEinleitungsstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EEntwaesserungsgrundstueckModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EMessstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EStandortModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ESonderbauwerkModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

public class ELKASync extends AbstractModul {

    private static final AuikLogger log = AuikLogger.getLogger();

    private static String IDENTIFIER =
        SettingsManager.getInstance().getSetting("auik.elka.identifier");
    private static String PROXY_HOST =
        SettingsManager.getInstance().getSetting("auik.elka.proxyhost");
    private static String PROXY_PORT =
        SettingsManager.getInstance().getSetting("auik.elka.proxyport");

    private JPanel panel;

    private EAbwasserbehandlungsanlageModel abwasserbehandlungModel;
    private EAnfallstelleModel anfallstelleModel;
    private EBetriebModel betriebModel;
    private EEinleitungsstelleModel einleitungsstelleModel;
    private EMessstelleModel messstelleModel;
    private EAdresseModel adresseModel;
    private EStandortModel standortModel;
    private EEntwaesserungsgrundstueckModel entwgrundModel;
    private ESonderbauwerkModel sbModel;

    private List<?> currentTableMappings;

    private JTable dbTable;
    private JLabel rowCount;
    private JLabel progressCounter;

    // Logindaten für den entfernten Service.
    private String url;
    private String referenceUrl;
    private String user;
    private String password;

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
        return "ELKA Datenbankabgleich";
    }

    @Override
    public void show() {
        super.show();
        SwingWorkerVariant worker = new SwingWorkerVariant(this.panel) {

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                // TODO Auto-generated method stub
            }
            @Override
            protected void doNonUILogic() throws RuntimeException {
                // TODO Auto-generated method stub

                ELKASync.this.adresseModel.setList(
                        prependIdentifierAdresse(EAdresse.getAll()));
                ELKASync.this.rowCount.setText(String.valueOf(
                    ELKASync.this.adresseModel.getRowCount()));
                ELKASync.this.dbTable.setModel(
                        ELKASync.this.adresseModel);
            }
        };
        worker.start();
    }

    public void init() {
        url = null;
        user = null;
        password = null;
        this.abwasserbehandlungModel = new EAbwasserbehandlungsanlageModel();
        this.anfallstelleModel = new EAnfallstelleModel();
        this.betriebModel = new EBetriebModel();
        this.einleitungsstelleModel = new EEinleitungsstelleModel();
        this.messstelleModel = new EMessstelleModel();
        this.adresseModel = new EAdresseModel();
        this.standortModel = new EStandortModel();
        this.entwgrundModel = new EEntwaesserungsgrundstueckModel();
        this.sbModel = new ESonderbauwerkModel();

        this.dbTable = new JTable();
        this.dbTable.setAutoCreateRowSorter(true);
        this.rowCount = new JLabel("0");
        this.progressCounter = new JLabel("-/-");
    }

    private JPanel createPanel() {
        init();
        if (this.panel == null) {
            this.panel = new JPanel();
            final JComboBox<String> selection = new JComboBox<String>();
            final JProgressBar progress = new JProgressBar();
            String[] entities =  new String[]{
                    "Adressen",
                    "Standorte",
                    "Betriebe",
                    "Abwasserbehandlungsanlagen",
                    "Anfallstellen",
                    "Einleitungsstellen",
                    "Entwässerungsgrundstücke",
                    "Messstellen",
                    "Sonderbauwerke"};
            if (entities != null && entities.length > 0) {
                for (String entity : entities) {
                    selection.addItem(entity);
                }
            }
            selection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(
                            selection) {
                        @Override
                        protected void doUIUpdateLogic()
                                throws RuntimeException {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        protected void doNonUILogic() throws RuntimeException {
                            String item = (String) selection.getSelectedItem();
                            if (item.equals("Abwasserbehandlungsanlagen")) {
                                ELKASync.this.dbTable
                                        .setModel(ELKASync.this.abwasserbehandlungModel);
                                ELKASync.this.abwasserbehandlungModel.setList(
                                        prependIdentifierAbwasserbehandlungsanlage(
                                            EAbwasserbehandlungsanlage.getAll()));
                                ELKASync.this.abwasserbehandlungModel.fireTableDataChanged();
                            } else if (item.equals("Anfallstellen")) {
                                ELKASync.this.dbTable
                                        .setModel(ELKASync.this.anfallstelleModel);
                                ELKASync.this.anfallstelleModel.setList(
                                        prependIdentifierAnfallstelle(EAnfallstelle.getAll()));
                                ELKASync.this.anfallstelleModel.fireTableDataChanged();
                            } else if (item.equals("Betriebe")) {
                                ELKASync.this.dbTable
                                        .setModel(ELKASync.this.betriebModel);
                                ELKASync.this.betriebModel.setList(
                                        prependIdentifierBetrieb(EBetrieb.getAll()));
                                ELKASync.this.betriebModel.fireTableDataChanged();
                            } else if (item.equals("Einleitungsstellen")) {
                                ELKASync.this.dbTable
                                        .setModel(ELKASync.this.einleitungsstelleModel);
                                ELKASync.this.einleitungsstelleModel.setList(
                                        prependIdentifierEinleitungsstelle(
                                            EEinleitungsstelle.getAll()));
                                currentTableMappings = EEinleitungsstelle.getAll();
                                ELKASync.this.einleitungsstelleModel.fireTableDataChanged();
                            } else if (item.equals("Messstellen")) {
                                ELKASync.this.dbTable
                                        .setModel(ELKASync.this.messstelleModel);
                                ELKASync.this.messstelleModel.setList(
                                        prependIdentifierMessstelle(EMessstelle.getAll()));
                                ELKASync.this.messstelleModel.fireTableDataChanged();
                            } else if (item.equals("Adressen")) {
                                ELKASync.this.dbTable
                                        .setModel(ELKASync.this.adresseModel);
                                ELKASync.this.adresseModel.setList(
                                        prependIdentifierAdresse(EAdresse.getAll()));
                                ELKASync.this.adresseModel.fireTableDataChanged();
                            } else if (item.equals("Standorte")) {
                                ELKASync.this.dbTable
                                        .setModel(ELKASync.this.standortModel);
                                ELKASync.this.standortModel.setList(
                                        prependIdentifierStandort(EStandort.getAll()));
                                ELKASync.this.standortModel.fireTableDataChanged();
                            } else if (item.equals("Entwässerungsgrundstücke")) {
                                ELKASync.this.dbTable
                                    .setModel(ELKASync.this.entwgrundModel);
                                ELKASync.this.entwgrundModel.setList(
                                        prependIdentifierEEntwaesserungsgrundstueck(EEntwaesserungsgrundstueck.getAll()));
                                ELKASync.this.entwgrundModel.fireTableDataChanged();
                            } else if (item.equals("Sonderbauwerke")) {
                                ELKASync.this.dbTable
                                    .setModel(ELKASync.this.sbModel);
                                ELKASync.this.sbModel.setList(
                                        prependIdentifierSonderbauwerk(ESonderbauwerk.getAll()));
                                ELKASync.this.sbModel.fireTableDataChanged();
                            }
                            ELKASync.this.rowCount.setText(String
                                    .valueOf(ELKASync.this.dbTable
                                            .getRowCount()));
                            progress.setValue(0);
                            ELKASync.this.progressCounter.setText("-/-");
                        }
                    };
                    worker.start();
                }
            });

            final JButton commitEntries = new JButton("gewählte Einträge übertragen");
            commitEntries.addActionListener(new ActionListener() {
                CredentialsDialog dialog = new CredentialsDialog(
                        ELKASync.this);
                @Override
                public void actionPerformed(ActionEvent ae) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(commitEntries) {
                        @Override
                        protected void doNonUILogic() {
                            dialog.setVisible(true);
                            if (url == null || url.equals("")) {
                                JOptionPane.showMessageDialog(
                                    ELKASync.this.panel,
                                    "Bitte geben Sie eine Url an!",
                                    "Verbindungsdaten",
                                    JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            if (user == null || user.equals("") ||
                                password == null || password.equals("")) {
                                JOptionPane.showMessageDialog(
                                    ELKASync.this.panel,
                                    "Bitte geben Sie Benutzername und Passwort an!",
                                    "Verbindungsdaten",
                                    JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            String sel = (String) selection.getSelectedItem();
                            JerseyClient client = new JerseyClientBuilder().build();
                            Logger l = Logger.getAnonymousLogger();
                            try{
                                l.addHandler(new FileHandler(sel+"-network.log"));
                            }
                            catch(IOException e){
                                log.debug(e);
                            }
                            client.register(new LoggingFeature(l));

                            List<Entity<?>> entityList =
                                new ArrayList<Entity<?>>();
                            List<Object> dbList = new ArrayList<Object>();
                            int[] rows = ELKASync.this.dbTable.getSelectedRows();
                            referenceUrl = url + "/referenz";
                            if (sel.equals("Abwasserbehandlungsanlagen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    dbList.add(ELKASync.this.abwasserbehandlungModel.getObjectAtRow(rows[i]));
                                }
                                url += "/abwasserbehandlungsanlage";
                            }
                            else if (sel.equals("Anfallstellen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    dbList.add(ELKASync.this.anfallstelleModel.getObjectAtRow(rows[i]));
                                }
                                url += "/anfallstelle";
                            }
                            else if (sel.equals("Betriebe")) {
                                for (int i = 0; i < rows.length; i++) {
                                    dbList.add(ELKASync.this.betriebModel.getObjectAtRow(rows[i]));
                                }
                                url += "/betrieb";
                            }
                            else if (sel.equals("Einleitungsstellen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    dbList.add(ELKASync.this.einleitungsstelleModel.getObjectAtRow(rows[i]));
                                }
                                url += "/einleitungsstelle";
                            }
                            else if (sel.equals("Messstellen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    dbList.add(ELKASync.this.messstelleModel.getObjectAtRow(rows[i]));
                                }
                                url += "/messstelle";
                            }
                            else if (sel.equals("Adressen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    dbList.add(ELKASync.this.adresseModel.getObjectAtRow(rows[i]));
                                }
                                url += "/adresse";
                            }
                            else if (sel.equals("Standorte")) {
                                for (int i = 0; i < rows.length; i++) {
                                    dbList.add(ELKASync.this.standortModel.getObjectAtRow(rows[i]));
                                }
                                url += "/standort";
                            }
                            else if (sel.equals("Entwässerungsgrundstücke")) {
                                for (int i = 0; i< rows.length; i++) {
                                    dbList.add(ELKASync.this.entwgrundModel.getObjectAtRow(rows[i]));
                                }
                                url += "/entwaesserungsgrundstueck";
                            } else if (sel.equals("Sonderbauwerke")) {
                                for (int i = 0; i< rows.length; i++) {
                                    dbList.add(ELKASync.this.sbModel.getObjectAtRow(rows[i]));
                                }
                                url += "/sonderbauwerk";
                            }
                            else {
                                return;
                            }
                            for (int i = 0; i < dbList.size(); i++) {
                                entityList.add(Entity.entity(
                                    dbList.get(i),
                                    MediaType.APPLICATION_JSON + ";charset=UTF-8"));
                            }
                            JerseyWebTarget target =
                                    client.target(url)
                                    .queryParam("username", user)
                                    .queryParam("password", password);
                            progress.setValue(0);
                            progress.setMaximum(dbList.size());
                            ELKASync.this.progressCounter.setText("0/" + dbList.size());
                            ELKASync.this.sendData(entityList, target, progress, sel);
                        }
                        @Override
                        protected void doUIUpdateLogic() {
                        }
                    };
                    worker.start();
                }
            });

            final JButton deleteEntries = new JButton("gewählte Einträge löschen");
            deleteEntries.addActionListener(new ActionListener() {
                CredentialsDialog dialog = new CredentialsDialog(
                        ELKASync.this);
                @Override
                public void actionPerformed(ActionEvent ae) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(commitEntries) {
                        @Override
                        protected void doNonUILogic() {
                            dialog.setVisible(true);
                            if (url == null || url.equals("")) {
                                JOptionPane.showMessageDialog(
                                    ELKASync.this.panel,
                                    "Bitte geben Sie eine Url an!",
                                    "Verbindungsdaten",
                                    JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            if (user == null || user.equals("") ||
                                password == null || password.equals("")) {
                                JOptionPane.showMessageDialog(
                                    ELKASync.this.panel,
                                    "Bitte geben Sie Benutzername und Passwort an!",
                                    "Verbindungsdaten",
                                    JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            String sel = (String) selection.getSelectedItem();
                            JerseyClient client = new JerseyClientBuilder().build();
                            Logger l = Logger.getAnonymousLogger();
                            try{
                                l.addHandler(new FileHandler(sel+"-network.log"));
                            }
                            catch(IOException e){
                                log.debug(e);
                            }
                            client.register(new LoggingFeature(l));

                            List<Entity<?>> entityList =
                                new ArrayList<Entity<?>>();
                            List<Object> dbList = new ArrayList<Object>();
                            List<Object> idList = new ArrayList<Object>();
                            int[] rows = ELKASync.this.dbTable.getSelectedRows();
                            referenceUrl = url + "/referenz";
                            if (sel.equals("Abwasserbehandlungsanlagen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    EAbwasserbehandlungsanlage objectAtRow =
                                        (EAbwasserbehandlungsanlage) ELKASync.this.abwasserbehandlungModel.
                                        getObjectAtRow(rows[i]);
                                    dbList.add(objectAtRow);
                                    idList.add(objectAtRow.getNr());
                                }
                                url += "/abwasserbehandlungsanlage";
                            }
                            else if (sel.equals("Anfallstellen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    EAnfallstelle object =
                                        (EAnfallstelle) ELKASync.this.anfallstelleModel.getObjectAtRow(rows[i]);
                                    idList.add(object.getNr());
                                    dbList.add(object);
                                }
                                url += "/anfallstelle";
                            }
                            else if (sel.equals("Betriebe")) {
                                for (int i = 0; i < rows.length; i++) {
                                    EBetrieb object =
                                        (EBetrieb) ELKASync.this.betriebModel.getObjectAtRow(rows[i]);
                                    idList.add(object.getNr());
                                    dbList.add(object);
                                }
                                url += "/betrieb";
                            }
                            else if (sel.equals("Einleitungsstellen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    EEinleitungsstelle object =
                                        (EEinleitungsstelle) ELKASync.this.einleitungsstelleModel.getObjectAtRow(rows[i]);
                                    idList.add(object.getNr());
                                    dbList.add(object);
                                }
                                url += "/einleitungsstelle";
                            }
                            else if (sel.equals("Messstellen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    EMessstelle object =
                                        (EMessstelle) ELKASync.this.messstelleModel.getObjectAtRow(rows[i]);
                                    idList.add(object.getNr());
                                    dbList.add(object);
                                }
                                url += "/messstelle";
                            }
                            else if (sel.equals("Entwässerungsgrundstücke")) {
                                for (int i = 0; i< rows.length; i++) {
                                    EEntwaesserungsgrundstueck object =
                                        (EEntwaesserungsgrundstueck) ELKASync.this.entwgrundModel.getObjectAtRow(rows[i]);
                                    idList.add(object.getNr());
                                    dbList.add(object);
                                }
                                url += "/entwaesserungsgrundstueck";
                            }
                            else if (sel.equals("Sonderbauwerke")) {
                                for (int i = 0; i< rows.length; i++) {
                                    ESonderbauwerk object =
                                        (ESonderbauwerk) ELKASync.this.sbModel.getObjectAtRow(rows[i]);
                                    idList.add(object.getNr());
                                    dbList.add(object);
                                }
                                url += "/sonderbauwerk";
                            }
                            else if (sel.equals("Adressen")) {
                                for (int i = 0; i < rows.length; i++) {
                                    EAdresse objectAtRow =
                                    (EAdresse) ELKASync.this.adresseModel.
                                    getObjectAtRow(rows[i]);
                                    idList.add(objectAtRow.getNr());
                                    dbList.add(objectAtRow);
                                }
                                url += "/adresse";
                            }
                            else if (sel.equals("Standorte")) {
                                for (int i = 0; i < rows.length; i++) {
                                    EStandort objectAtRow =
                                    (EStandort) ELKASync.this.standortModel.
                                    getObjectAtRow(rows[i]);
                                    idList.add(objectAtRow.getNr());
                                    dbList.add(objectAtRow);
                                }
                                url += "/standort";
                            }
                            else {
                                return;
                            }
                            for (int i = 0; i < dbList.size(); i++) {
                                entityList.add(Entity.entity(
                                    dbList.get(i),
                                    MediaType.APPLICATION_JSON + ";charset=UTF-8"));
                            }

                            progress.setValue(0);
                            progress.setMaximum(dbList.size());
                            ELKASync.this.progressCounter.setText("0/" + dbList.size());
                            ELKASync.this.deleteData(entityList, idList, url, user, password, progress, sel);
                        }
                        @Override
                        protected void doUIUpdateLogic() {
                        }
                    };
                    worker.start();
                }
            });


            final JButton commitTable = new JButton("gewählte Tabelle übertragen");
            commitTable.addActionListener(new ActionListener() {
                CredentialsDialog dialog = new CredentialsDialog(
                        ELKASync.this);
                @Override
                public void actionPerformed(ActionEvent ae) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(commitTable) {
                        @Override
                        protected void doNonUILogic() {
                            dialog.setVisible(true);
                            if (url == null || url.equals("")) {
                                JOptionPane.showMessageDialog(
                                    ELKASync.this.panel,
                                    "Bitte geben Sie eine Url an!",
                                    "Verbindungsdaten",
                                    JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            if (user == null || user.equals("") ||
                                password == null || password.equals("")) {
                                JOptionPane.showMessageDialog(
                                    ELKASync.this.panel,
                                    "Bitte geben Sie Benutzername und Passwort an!",
                                    "Verbindungsdaten",
                                    JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            String sel = (String) selection.getSelectedItem();
                            JerseyClient client = new JerseyClientBuilder().build();
                            Logger l = Logger.getAnonymousLogger();
                            try{
                                l.addHandler(new FileHandler(sel + "-network.log"));
                            }
                            catch(IOException e){
                                log.debug(e);
                            }
                            client.register(new LoggingFeature(l));

                            List<Entity<?>> entityList =
                                new ArrayList<Entity<?>>();
                            List<?> dbList = null;
                            referenceUrl = url + "/referenz";
                            if (sel.equals("Abwasserbehandlungsanlagen")) {
                                dbList = ELKASync.this.abwasserbehandlungModel.getList();
                                url += "/abwasserbehandlungsanlage";
                            }
                            else if (sel.equals("Anfallstellen")) {
                                dbList = ELKASync.this.anfallstelleModel.getList();
                                url += "/anfallstelle";
                            }
                            else if (sel.equals("Betriebe")) {
                                dbList = ELKASync.this.betriebModel.getList();
                                url += "/betrieb";
                            }
                            else if (sel.equals("Einleitungsstellen")) {
                                dbList = ELKASync.this.einleitungsstelleModel.getList();
                                url += "/einleitungsstelle";
                            }
                            else if (sel.equals("Messstellen")) {
                                dbList = ELKASync.this.messstelleModel.getList();
                                url += "/messstelle";
                            }
                            else if (sel.equals("Adressen")) {
                                dbList = ELKASync.this.adresseModel.getList();
                                url += "/adresse";
                            }
                            else if (sel.equals("Standorte")) {
                                dbList = ELKASync.this.standortModel.getList();
                                url += "/standort";
                            } else if (sel.equals("Entwässerungsgrundstücke")) {
                                dbList = ELKASync.this.entwgrundModel.getList();
                                url += "/entwaesserungsgrundstueck";
                            } else if (sel.equals("Sonderbauwerke")) {
                                dbList = ELKASync.this.sbModel.getList();
                                url += "/sonderbauwerk";
                            }
                            JerseyWebTarget target =
                                    client.target(url)
                                    .queryParam("username", user)
                                    .queryParam("password", password);
                            for (int i = 0; i < dbList.size(); i++) {
                                entityList.add(Entity.entity(
                                        dbList.get(i),
                                        MediaType.APPLICATION_JSON + ";charset=UTF-8"));
                            }

                            progress.setValue(0);
                            progress.setMaximum(dbList.size());
                            ELKASync.this.sendData(entityList, target, progress, sel);
                        }
                        @Override
                        protected void doUIUpdateLogic() {
                        }
                    };
                    worker.start();
                }
            });
            FormLayout layout = new FormLayout(
                "pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu:grow(1.0)",
                "pref, 3dlu, 150dlu:grow(2.0), 3dlu, pref");
            PanelBuilder builder = new PanelBuilder(layout);
            CellConstraints cc = new CellConstraints();
            JScrollPane dbScroller = new JScrollPane(this.dbTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            builder.addLabel("Tabelle", cc.xy(1, 1));
            builder.add(selection, cc.xy(3, 1));
            builder.add(commitTable, cc.xy(5, 1));
            builder.add(commitEntries, cc.xy(7, 1));
            builder.add(deleteEntries, cc.xy(9, 1));
            builder.add(dbScroller, cc.xyw(1, 3, 10));
            builder.addLabel("Anzahl der Elemente: ", cc.xy(1, 5));
            builder.add(this.rowCount, cc.xy(3, 5));
            builder.add(new JLabel("Fortschritt: "), cc.xy(5, 5, CellConstraints.RIGHT, CellConstraints.CENTER));
            builder.add(progress, cc.xy(7, 5));
            builder.add(this.progressCounter, cc.xy(9, 5));
            this.panel = builder.getPanel();
            this.panel.setBorder(Paddings.DIALOG);
        }
        return this.panel;
    }

    private <T> void deleteData(
        List<Entity<?>> entities,
        List<Object> ids,
        String url,
        String user,
        String password,
        JProgressBar progress,
        String type
    ) {
        if (entities.size() > 0) {
            if(PROXY_HOST != null){
                System.setProperty("https.proxyHost", PROXY_HOST);
                System.setProperty("http.proxyHost", PROXY_HOST);
            }
            if(PROXY_PORT != null){
                System.setProperty("https.proxyPort", PROXY_PORT);
                System.setProperty("http.proxyPort", PROXY_PORT);
            }
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this.panel) == JFileChooser.APPROVE_OPTION) {
                File protocolFile = chooser.getSelectedFile();
                try {
                    JerseyClient client = new JerseyClientBuilder().build();
                    FileOutputStream fileStream = new FileOutputStream(protocolFile);
                    PrintStream printStream = new PrintStream(fileStream);
                    this.progressCounter.setText("0/" + entities.size());
                    printStream.append("Lösche " + type + "\n");
                    printStream.append("--------------------------------------\n");
                    for (int i = 0; i < entities.size(); i++) {
                        String deleteUrl = url + "/" + ids.get(i);
                        log.debug("Deleting " + deleteUrl);
                        JerseyWebTarget target =
                            client.target(deleteUrl)
                            .queryParam("username", user)
                            .queryParam("password", password);
                        JerseyInvocation inv =
                            target.request(
                                    MediaType.APPLICATION_JSON +
                                    ";charset=UTF-8")
                                    .buildDelete();
                        Response response = inv.invoke();
                        progress.setValue(progress.getValue() + 1);
                        String responseEntity = response.readEntity(String.class);
                        this.progressCounter.setText((i + 1)+ "/" + entities.size());
                        switch (response.getStatus()) {
                            case 204:
                                printStream.append((i + 1) + ":" + " erfolgreich Gelöscht\n");
                                break;
                            case 404:
                                printStream.append("Fehler in Objekt: " + (i+1) + "\n");
                                printStream.append("Fehlerbeschreibung: Entität nicht existent");
                                printStream.append("\n");
                                break;
                            default:
                                printStream.append("Fehler in Objekt: " + (i+1) + "\n");
                                printStream.append("Fehlerbeschreibung: " + responseEntity);
                                printStream.append("\n");
                        }
                    }
                    fileStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ProcessingException pe) {
                    pe.printStackTrace();
                    JOptionPane.showMessageDialog(
                        this.panel,
                        "Der Server unter der angegeben Url ist nicht erreichbar.",
                        "Verbindungsfehler",
                        JOptionPane.WARNING_MESSAGE);
                }
            }
            System.setProperty("http.proxyHost", "");
            System.setProperty("http.proxyPort", "");
        }

    }
    private <T> void sendData(
        List<Entity<?>> entities,
        JerseyWebTarget target,
        JProgressBar progress,
        String type
    ) {
        if (entities.size() > 0) {
            if(PROXY_HOST != null){
                System.setProperty("https.proxyHost", PROXY_HOST);
                System.setProperty("http.proxyHost", PROXY_HOST);
            }
            if(PROXY_PORT != null){
                System.setProperty("https.proxyPort", PROXY_PORT);
                System.setProperty("http.proxyPort", PROXY_PORT);
            }
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this.panel) == JFileChooser.APPROVE_OPTION) {
                File protocolFile = chooser.getSelectedFile();
                try {
                    FileOutputStream fileStream = new FileOutputStream(protocolFile);
                    PrintStream printStream = new PrintStream(fileStream);
                    this.progressCounter.setText("0/" + entities.size());
                    printStream.append("Sende " + type + "\n");
                    printStream.append("--------------------------------------\n");
                    for (int i = 0; i < entities.size(); i++) {
                            JerseyInvocation inv =
                            target.request(
                                    MediaType.APPLICATION_JSON +
                                    ";charset=UTF-8")
                                    .buildPost(entities.get(i));
                        Response response = inv.invoke();
                        progress.setValue(progress.getValue() + 1);
                        String responseEntity = response.readEntity(String.class);
                        this.progressCounter.setText((i + 1)+ "/" + entities.size());
                        if (response.getStatus() != 200 &&
                            response.getStatus() != 201
                        ) {
                            printStream.append("Fehler in Objekt: " + (i+1) + "\n");
                            printStream.append("Fehlerbeschreibung: " + responseEntity);
                            printStream.append("\n");
                        }
                        else {
                            printStream.append((i + 1) + ":" + " erfolgreich übertragen\n");
                            sendReferences(entities.get(i), printStream);
                        }
                        log.debug(responseEntity);
                    }
                    fileStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ProcessingException pe) {
                    pe.printStackTrace();
                    JOptionPane.showMessageDialog(
                        this.panel,
                        "Der Server unter der angegeben Url ist nicht erreichbar.",
                        "Verbindungsfehler",
                        JOptionPane.WARNING_MESSAGE);
                }
            }
            System.setProperty("http.proxyHost", "");
            System.setProperty("http.proxyPort", "");
        }
    }

    /**
     * Checks an entity for connected reference entities and sends them to the given Server
     * @param Entity
     */
    public void sendReferences(Object entity, PrintStream log) {
        try {
            //Obtain original mapping instance from entity
            Method getEntity = entity.getClass().getMethod("getEntity");
            Object mappingInstance = getEntity.invoke(entity);
            //Get attached references
            Method getRefrenzs = mappingInstance.getClass().getMethod("getReferenzs");
            List<Referenz> referenzs = (List<Referenz>) getRefrenzs.invoke(mappingInstance);
            if (referenzs.size() > 0) {
                JerseyClient client = new JerseyClientBuilder().build();
                Logger l = Logger.getAnonymousLogger();
                client.register(new LoggingFeature(l));
                JerseyWebTarget target =
                    client.target(referenceUrl)
                        .queryParam("username", user)
                        .queryParam("password", password);
                prependIdentifierReferenz(referenzs);
                for (int i = 0; i < referenzs.size(); i++) {
                    Referenz ref = referenzs.get(i);

                    Entity<Referenz> refEntity = Entity.entity(ref,
                    MediaType.APPLICATION_JSON +
                        ";charset=UTF-8");
                    JerseyInvocation inv = target.request(
                        MediaType.APPLICATION_JSON +
                        ";charset=UTF-8")
                        .buildPost(refEntity);
                    Response response= inv.invoke();
                    String responseEntity = response.readEntity(String.class);
                    System.out.println(responseEntity);
                }
            }

        } catch (NoSuchMethodException e){
        } catch (SecurityException e) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setServiceUrl(String url) {
        this.url = url;
    }

    public void setServiceUser(String user) {
        this.user = user;
    }

    public void setServicePassword(String password) {
        this.password = password;
    }

    /**
     * Converts references in a Referenz entitiy into ELKA compatible references
     */
    private Referenz convertReferenzReferences(Referenz ref) {
        //Set source EEinleitungsstelle
        if (ref.getqEl() != null) {
            ref.setEinleitungsstelleByQElsNr(
                EEinleitungsstelle.findById(
                    ref.getqEl().getId()));
        }
        //Set target EEInleitungsstelle
        if (ref.getzEl() != null) {
            ref.setEinleitungsstelleByZElsNr(
                EEinleitungsstelle.findById(
                    ref.getzEl().getId()));
        }
        return ref;
    }

    private List<EAbwasserbehandlungsanlage> prependIdentifierAbwasserbehandlungsanlage(
           List<EAbwasserbehandlungsanlage> objects
    ) {
        for (EAbwasserbehandlungsanlage anlage : objects) {
            prependIdentifierToNr(anlage);
            prependIdentifierToNr(anlage.getAdresseByBetreibAdrNr());
            prependIdentifierToNr(anlage.getAdresseByStoAdrNr());
            prependIdentifierToNr(anlage.getStandort());
            prependIdentifierToNr(anlage.getStandort().getAdresse());
            for (EWasserrecht recht : anlage.getWasserrechts()) {
                prependIdentifierToNr(recht);
                prependIdentifierToNr(recht.getAdresse());
            }
            prependIdentifierToNr(anlage.getAbwasserbehandlungsverfahrens());
        }
        return objects;
    }

    private List<ESonderbauwerk> prependIdentifierSonderbauwerk (
        List<ESonderbauwerk> objects
    ) {
        for (ESonderbauwerk sb: objects) {
            prependIdentifierToNr(sb);
            if (sb.getAdresseByBetreibAdrNr() != null) {
                prependIdentifierToNr(sb.getAdresseByBetreibAdrNr());
            }
            if (sb.getAdresseByAnsprAdrNr() != null) {
                prependIdentifierToNr(sb.getAdresseByAnsprAdrNr());
            }
            if (sb.getStandort() != null) {
                prependIdentifierToNr(sb.getStandort());
                prependIdentifierToNr(sb.getStandort().getAdresse());
            }
            EWasserrecht recht = sb.getWasserrechtByWasserrechtGenehmigungNr();
            if (recht != null) {
                prependIdentifierToNr(recht);
                prependIdentifierToNr(recht.getAdresse());
            }
            Set<SbEntlastung> sbes = sb.getSbEntlastungs();
            for (SbEntlastung sbe : sbes) {
                prependIdentifierToNr(sbe);
            }

            Set<ZRbfSchutzgueter> zrfbs = sb.getZuordnungRbfSchutzguts();
            for (ZRbfSchutzgueter schutzg : zrfbs) {
                prependIdentifierToNr(schutzg);
            }

            Set<ZSbRegeln> zsbrs = sb.getZuordnungSbRegels();
            for (ZSbRegeln sbr: zsbrs) {
                prependIdentifierToNr(sbr);
            }
            //TODO zuordnungSbVerfahrensVorgabens
        }
        return objects;
    }

    private List<EEntwaesserungsgrundstueck> prependIdentifierEEntwaesserungsgrundstueck (
        List<EEntwaesserungsgrundstueck> objects
    ) {
        for (EEntwaesserungsgrundstueck ewg: objects) {
            prependIdentifierToNr(ewg);
            prependIdentifierToNr(ewg.getAdresse());
            prependIdentifierToNr(ewg.getStandort());
//            prependIdentifierToNr(ewg.getStandort().getAdresse());
            EWasserrecht recht = ewg.getWasserrecht();
            if (recht != null) {
                prependIdentifierToNr(recht);
                prependIdentifierToNr(recht.getAdresse());
            }
//            for (Abaverfahren verfahren: ewg.getAbwasserbehandlungsverfahrens()) {
//                prependIdentifierToNr(verfahren);
//            }
            for (EAfsNiederschlagswasser afsn: ewg.getAfsNiederschlagswassers()) {
                prependIdentifierToNr(afsn);
            }
        }
        return objects;
    }

    private List<EAnfallstelle> prependIdentifierAnfallstelle (
        List<EAnfallstelle> objects
    ) {
        for (EAnfallstelle stelle : objects) {
            prependIdentifierToNr(stelle);
            prependIdentifierToNr(stelle.getAdresse());
            prependIdentifierToNr(stelle.getStandort());
			if (stelle.getStandort().getAdresse() != null) {
				prependIdentifierToNr(stelle.getStandort().getAdresse());
			}
            for (AfsStoffe afsStoff: stelle.getAfsStoffes()) {
                prependIdentifierToProperty(afsStoff, "anfallstellenNr");
            }
            for (EAfsNiederschlagswasser afsNiederschlagwasser: stelle.getAfsNiederschlagswassers()) {
                prependIdentifierToNr(afsNiederschlagwasser);
            }
        }
        return objects;
    }

    private List<EBetrieb> prependIdentifierBetrieb(List<EBetrieb> objects) {
        for (EBetrieb betrieb : objects) {
            prependIdentifierToNr(betrieb);
            prependIdentifierToNr(betrieb.getAdresseByWrAdrNr());
            prependIdentifierToNr(betrieb.getStandort());
            prependIdentifierToNr(betrieb.getStandort().getAdresse());
            for (Massnahme m : betrieb.getMassnahmes()) {
                prependIdentifierToNr(m);
            }
        }
        return objects;
    }

    private List<EEinleitungsstelle> prependIdentifierEinleitungsstelle(
        List<EEinleitungsstelle> objects
    ) {
        for (EEinleitungsstelle stelle : objects) {
            prependIdentifierToNr(stelle);
            prependIdentifierToNr(stelle.getStandort());
            for (EWasserrecht recht : stelle.getWasserrechts()) {
                prependIdentifierToNr(recht);
                prependIdentifierToNr(recht.getAdresse());
            }
        }
        return objects;
    }

    private List<EMessstelle> prependIdentifierMessstelle(
        List<EMessstelle> objects
    ) {
        for (EMessstelle stelle : objects) {
            log.debug("Stelle: " + stelle.getNr());
            prependIdentifierToNr(stelle);
            prependIdentifierToNr(stelle.getStandort());
            prependIdentifierToNr(stelle.getStandort().getAdresse());
            for (EProbenahme nahme : stelle.getProbenahmes()) {
                log.debug("Probenahme: " + nahme.getNr());
                prependIdentifierToNr(nahme);
                for (EProbenahmeUeberwachungsergeb ergeb :
                    nahme.getProbenahmeUeberwachungsergebs()) {
                        log.debug("Ergeb: " + ergeb.getNr());
                    prependIdentifierToNr(ergeb);
                }
            }
            for (MsstBerichtspflicht mstb: stelle.getZuordnungMsstBerichtspflichts()) {
                prependIdentifierToProperty(mstb, "msstNr");
            }
        }
        return objects;
    }

    private List<EAdresse> prependIdentifierAdresse(
          List<EAdresse> objects
    ) {
           for (EAdresse adresse : objects) {
               prependIdentifierToNr(adresse);
           }
           return objects;
    }

	private List<EStandort> prependIdentifierStandort(List<EStandort> objects) {
		for (EStandort standort : objects) {
			prependIdentifierToNr(standort);
			if (standort.getAdresse() != null) {
				prependIdentifierToNr(standort.getAdresse());
			}
		}
		return objects;
	}

    private List<Referenz> prependIdentifierReferenz(List<Referenz> objects) {
        for (Referenz ref: objects) {
            convertReferenzReferences(ref);
            prependIdentifierToNr(ref);
            //Fetch Standort and Einleitungsstelle
            ref.setStandort(EStandort.findById(ref.getStandort().getNr()));
            ref.setEinleitungsstelleByQElsNr(EEinleitungsstelle.findById(ref.getqEl().getId()));
            prependIdentifierToNr(ref.getStandort());
            prependIdentifierToNr(ref.getStandort().getAdresse());
            if (ref.getEinleitungsstelleByQElsNr() != null) {
                prependIdentifierToNr(ref.getEinleitungsstelleByQElsNr());
                prependIdentifierToNr(ref.getEinleitungsstelleByQElsNr().getStandort());
                prependIdentifierToNr(ref.getEinleitungsstelleByQElsNr().getStandort().getAdresse());
            }
            if (ref.getEinleitungsstelleByZElsNr() != null) {
                prependIdentifierToNr(ref.getEinleitungsstelleByZElsNr());
                prependIdentifierToNr(ref.getEinleitungsstelleByZElsNr().getStandort());
                prependIdentifierToNr(ref.getEinleitungsstelleByZElsNr().getStandort().getAdresse());

            }
        }
        return objects;
    }

    /**
     * Prepends an identifier to the value of the objects nr field
     * @param object Object to modify
     * @return The modified object
     */
    private <T> T prependIdentifierToNr(T object) {
        try {
            Method mGetter = object.getClass().getMethod("getOrigNr");
            Method mSetter = object.getClass().getMethod("setNr", Integer.class);
            Integer nr = (Integer)mGetter.invoke(object);
            if(nr == null) {
                mGetter = object.getClass().getMethod("getNr");
                nr = (Integer)mGetter.invoke(object);
                object.getClass().getMethod("setOrigNr", Integer.class).invoke(object, nr);
            }
            String newNr = IDENTIFIER + nr.toString();
            Integer newBI = new Integer(newNr);
            mSetter.invoke(object, newBI);
        } catch (NoSuchMethodException e){
        } catch (SecurityException e) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    private <T> T prependIdentifierToProperty(T object, String propertyName) {
        try {
            String capitalizedPropertyName = propertyName.substring(0, 1).toUpperCase()
                    + propertyName.substring(1);
            String getterName = "get" + capitalizedPropertyName;
            String setterName = "set" + capitalizedPropertyName;
            String origGetterName = "getOrig" + capitalizedPropertyName;
            String origSetterName = "setOrig" + capitalizedPropertyName;
            Method mGetter = object.getClass().getMethod(origGetterName);
            Method mSetter = object.getClass().getMethod(setterName, Integer.class);
            Integer nr = (Integer)mGetter.invoke(object);
            if(nr == null) {
                mGetter = object.getClass().getMethod(getterName);
                nr = (Integer)mGetter.invoke(object);
                object.getClass().getMethod(origSetterName, Integer.class).invoke(object, nr);
            }
            String newNr = IDENTIFIER + nr.toString();
            mSetter.invoke(object, Integer.valueOf(newNr));
        } catch (NoSuchMethodException e){
        } catch (SecurityException e) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
