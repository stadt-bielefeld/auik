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

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
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
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.elka.Referenz;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAbwasserbehandlungsanlage;
import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAdresse;
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
import de.bielefeld.umweltamt.aui.mappings.oberflgw.AfsNiederschlagswasser;
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
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
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

    //Entity options
    private static final String ENTITY_ADRESSE = "Adressen";
    private static final String ENTITY_STANDORTE = "Standorte";
    private static final String ENTITY_BETRIEBE = "Betriebe";
    private static final String ENTITY_ABA = "Abwasserbehandlungsanlagen";
    private static final String ENTITY_ANFALLSTELLE = "Anfallstellen";
    private static final String ENTITY_EINL = "Einleitungsstellen";
    private static final String ENTITY_ENTWG = "Entwässerungsgrundstücke";
    private static final String ENTITY_MST = "Messstellen";
    private static final String ENTITY_SONDERBAUWERKE = "Sonderbauwerke";

    private String[] entities;

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
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
        this.dbTable.setDefaultRenderer(Integer.class, renderer);
        this.rowCount = new JLabel("0");
        this.progressCounter = new JLabel("-/-");
    }

    @SuppressWarnings("deprecation")
    private JPanel createPanel() {
        init();
        if (this.panel == null) {
            this.panel = new JPanel();
            final JComboBox<String> selection = new JComboBox<String>();
            final JProgressBar progress = new JProgressBar();
            entities = new String[]{
                ENTITY_ADRESSE, ENTITY_STANDORTE, ENTITY_BETRIEBE, ENTITY_ABA,
                ENTITY_ANFALLSTELLE, ENTITY_EINL, ENTITY_ENTWG, ENTITY_MST,
                ENTITY_SONDERBAUWERKE
            };
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
                            ListTableModel model = getModelByEntity(item);
                            ELKASync.this.dbTable.setModel(model);
                            model.setList(getListByEntity(item));
                            model.fireTableDataChanged();;
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
                            List<Integer> indexList = new ArrayList<Integer>();
                            int[] rows = ELKASync.this.dbTable.getSelectedRows();
                            referenceUrl = url + "/referenz";
                            url += getUrlByEntity(sel);
                            for (int i = 0; i < rows.length; i++) {
                                Object objAtRow = getModelByEntity(sel)
                                    .getObjectAtRow(rows[i]);
                                dbList.add(objAtRow);
                                entityList.add(Entity.entity(
                                    objAtRow,
                                    MediaType.APPLICATION_JSON + ";charset=UTF-8"));
                                indexList.add(rows[i] + 1);
                            }
                            List<EntityListEntry> entities = indexList.stream()
                                .sorted()
                                .map((indexEntry) -> {
                                    int listIndex = indexList.indexOf(indexEntry);
                                    return new EntityListEntry(
                                        indexEntry,
                                        entityList.get(listIndex));
                                })
                                .collect(Collectors.toList());
                            JerseyWebTarget target =
                                    client.target(url)
                                    .queryParam("username", user)
                                    .queryParam("password", password);
                            progress.setValue(0);
                            progress.setMaximum(dbList.size());
                            ELKASync.this.progressCounter.setText("0/" + dbList.size());
                            ELKASync.this.sendData(entities, target, progress, sel);
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
                            List<Integer> indexList = new ArrayList<Integer>();
                            int[] rows = ELKASync.this.dbTable.getSelectedRows();
                            referenceUrl = url + "/referenz";
                            url += getUrlByEntity(sel);
                            for (int i = 0; i < rows.length; i++) {
                                int row = rows[i];
                                Object objAtRow = getModelByEntity(sel).getObjectAtRow(row);
                                dbList.add(objAtRow);
                                idList.add(getEntityId(sel, objAtRow));
                                indexList.add(row + 1);
                                entityList.add(Entity.entity(
                                    objAtRow,
                                    MediaType.APPLICATION_JSON + ";charset=UTF-8"));
                            }
                            List<EntityListEntry> entities = indexList.stream()
                                .sorted()
                                .map((indexEntry) -> {
                                    int listIndex = indexList.indexOf(indexEntry);
                                    return new EntityListEntry(
                                        indexEntry, idList.get(listIndex),
                                        entityList.get(listIndex));
                                })
                                .collect(Collectors.toList());

                            progress.setValue(0);
                            progress.setMaximum(dbList.size());
                            ELKASync.this.progressCounter.setText("0/" + dbList.size());
                            ELKASync.this.deleteData(entities, url, user, password, progress, sel);
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
                            url += getUrlByEntity(sel);
                            dbList = getModelByEntity(sel).getList();
                            JerseyWebTarget target =
                                    client.target(url)
                                    .queryParam("username", user)
                                    .queryParam("password", password);

                            RowSorter sorter = ELKASync.this.dbTable.getRowSorter();
                            List<Integer> indexList = new ArrayList<Integer>();
                            for (int i = 0; i < dbList.size(); i++) {
                                entityList.add(Entity.entity(
                                        dbList.get(i),
                                        MediaType.APPLICATION_JSON + ";charset=UTF-8"));
                                indexList.add(sorter.convertRowIndexToModel(i) + 1);
                            }

                            progress.setValue(0);
                            progress.setMaximum(dbList.size());
                            List<EntityListEntry> entities = indexList.stream()
                                .sorted()
                                .map((indexEntry) -> {
                                    int listIndex = indexList.indexOf(indexEntry);
                                    return new EntityListEntry(
                                        indexEntry,
                                        entityList.get(listIndex));
                                })
                                .collect(Collectors.toList());
                            ELKASync.this.sendData(entities, target, progress, sel);
                        }
                        @Override
                        protected void doUIUpdateLogic() {
                        }
                    };
                    worker.start();
                }
            });

            final JButton deleteAllButton = new JButton("Alle Tabellen löschen");
            deleteAllButton.addActionListener(new ActionListener() {
                CredentialsDialog dialog = new CredentialsDialog(
                        ELKASync.this);
                @Override
                public void actionPerformed(ActionEvent ae) {
                    SwingWorkerVariant worker = new SwingWorkerVariant(deleteAllButton) {
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
                            int selSize = selection.getModel().getSize();
                            //Load data and prepare progress counter
                            int elementsToRemove = 0;
                            for(String entitity: entities) {
                                ListTableModel model = getModelByEntity(entitity);
                                List<?> data = getListByEntity(entitity);
                                model.setList(data);
                                model.fireTableDataChanged();
                                elementsToRemove += model.getRowCount();
                            }
                            progress.setValue(0);
                            progress.setMaximum(elementsToRemove);
                            ELKASync.this.progressCounter.setText("0/" + elementsToRemove);
                            JFileChooser chooser = new JFileChooser();
                            File protocolFile;
                            if (chooser.showSaveDialog(ELKASync.this.panel) == JFileChooser.APPROVE_OPTION) {
                                protocolFile = chooser.getSelectedFile();
                                if (protocolFile.exists()) {
                                    protocolFile.delete();
                                }
                            } else {
                                return;
                            }
                            for (int j = 0; j < selSize; j++) {
                                String currentUrl = url;
                                String sel = selection.getModel().getElementAt(j);
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
                                currentUrl += getUrlByEntity(sel);
                                dbList = getListByEntity(sel);

                                List<Integer> indexList = new ArrayList<Integer>();
                                List<Object> idList = new ArrayList<Object>();
                                for (int i = 0; i < dbList.size(); i++) {
                                    Object obj = dbList.get(i);
                                    idList.add(getEntityId(sel, obj));
                                    entityList.add(Entity.entity(
                                            obj,
                                            MediaType.APPLICATION_JSON + ";charset=UTF-8"));
                                    indexList.add(i + 1);
                                }
                                List<EntityListEntry> entities = indexList.stream()
                                    .sorted()
                                    .map((indexEntry) -> {
                                        int listIndex = indexList.indexOf(indexEntry);
                                        return new EntityListEntry(
                                            indexEntry, idList.get(listIndex),
                                            entityList.get(listIndex));
                                    })
                                    .collect(Collectors.toList());
                                ELKASync.this.deleteData(
                                    entities, currentUrl, user, password,
                                    progress, sel, protocolFile, true);
                            }
                        }
                        @Override
                        protected void doUIUpdateLogic() {
                        }
                    };
                    worker.start();
                }
            });

            FormLayout layout = new FormLayout(
                "pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu:grow(1.0)",
                "pref, 3dlu, 150dlu:grow(2.0), 3dlu, pref");
            PanelBuilder builder = new PanelBuilder(layout);
            CellConstraints cc = new CellConstraints();
            JScrollPane dbScroller = new JScrollPane(this.dbTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //Add row numbers
            ListModel rowList = new AbstractListModel() {
                @Override
                public Object getElementAt(int index) {
                    return index + 1;
                }
                @Override
                public int getSize() {
                    return dbTable.getRowCount();
                }
            };
            JList rowHeader = new JList(rowList);
            dbScroller.setRowHeaderView(rowHeader);
            rowHeader.setFixedCellWidth(25);
            rowHeader.setFixedCellHeight(this.dbTable.getRowHeight());
            rowHeader.setVisible(true);
            rowHeader.setBackground(dbScroller.getBackground());
            rowHeader.setCellRenderer(new RowNumberCellRenderer(dbTable));

            //Create panel
            builder.addLabel("Tabelle", cc.xy(1, 1));
            builder.add(selection, cc.xy(3, 1));
            builder.add(commitTable, cc.xy(5, 1));
            builder.add(commitEntries, cc.xy(7, 1));
            builder.add(deleteEntries, cc.xy(9, 1));
            builder.add(deleteAllButton, cc.xy(11, 1));
            builder.add(dbScroller, cc.xyw(1, 3, 12));
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
        List<EntityListEntry> entities,
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
                deleteData(entities, url, user, password, progress, type, protocolFile, false);
            }
        }
    }

    /**
     * Delete data and write protocol to the given file
     * @param <T> Entitiy type
     * @param entities Entities
     * @param url Url
     * @param user username
     * @param password password
     * @param progress Progress bar
     * @param type Entitiy type String
     * @param protcolFile protocol file
     * @param append True if protocol should be appended to file
     */
    private <T> void deleteData(
        List<EntityListEntry> entities,
        String url,
        String user,
        String password,
        JProgressBar progress,
        String type,
        File protocolFile,
        boolean append
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
            try {
                JerseyClient client = new JerseyClientBuilder().build();
                FileOutputStream fileStream = new FileOutputStream(protocolFile, append);
                PrintStream printStream = new PrintStream(fileStream);
                this.progressCounter.setText(
                    String.format("%s/%s", progress.getValue(), progress.getMaximum()));
                printStream.append("Lösche " + type + "\n");
                printStream.append("--------------------------------------\n");
                for (int i = 0; i < entities.size(); i++) {
                    EntityListEntry entry = entities.get(i);
                    Object id = entry.getId();
                    Integer index = entry.getTableIndex();
                    String deleteUrl = url + "/" + id;
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
                    this.progressCounter.setText(
                        String.format("%s/%s", progress.getValue(), progress.getMaximum()));
                    String objectName = index.toString();
                    switch (response.getStatus()) {
                        case 204:
                            printStream.append(objectName + ":" + " erfolgreich Gelöscht\n");
                            break;
                        case 404:
                            printStream.append("Fehler in Objekt: " + objectName + "\n");
                            printStream.append("Fehlerbeschreibung: Entität nicht existent");
                            printStream.append("\n");
                            break;
                        default:
                            printStream.append("Fehler in Objekt: " + objectName + "\n");
                            printStream.append("Fehlerbeschreibung: " + responseEntity);
                            printStream.append("\n");
                    }
                }
                fileStream.close();
                openFile(protocolFile);
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
    private <T> void sendData(
        List<EntityListEntry> entities,
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
                        EntityListEntry entry = entities.get(i);
                        Entity<?> entity = entry.getEntity();
                        Integer index = entry.getTableIndex();
                        JerseyInvocation inv =
                            target.request(
                                    MediaType.APPLICATION_JSON +
                                    ";charset=UTF-8")
                                    .buildPost(entity);
                        Response response = inv.invoke();
                        progress.setValue(progress.getValue() + 1);
                        String responseEntity = response.readEntity(String.class);
                        this.progressCounter.setText((i + 1)+ "/" + entities.size());
                        String objectName = index.toString();
                        if (response.getStatus() != 200 &&
                            response.getStatus() != 201
                        ) {
                            printStream.append("Fehler in Objekt: " + objectName + "\n");
                            printStream.append("Fehlerbeschreibung: " + responseEntity);
                            printStream.append("\n");
                        }
                        else {
                            printStream.append(objectName + ":" + " erfolgreich übertragen\n");
                            sendReferences(entities.get(i), printStream);
                        }
                        log.debug(responseEntity);
                    }
                    fileStream.close();
                    openFile(protocolFile);
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
            prependIdentifierToNr(ewg.getStandort().getAdresse());
            EWasserrecht recht = ewg.getWasserrecht();
            if (recht != null) {
                prependIdentifierToNr(recht);
                prependIdentifierToNr(recht.getAdresse());
            }
            for (Abaverfahren verfahren: ewg.getAbwasserbehandlungsverfahrens()) {
                prependIdentifierToNr(verfahren);
            }
            for (AfsNiederschlagswasser afsn: ewg.getAfsNiederschlagswassers()) {
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
            prependIdentifierToNr(stelle.getStandort().getAdresse());
            for (AfsStoffe afsStoff: stelle.getAfsStoffes()) {
                prependIdentifierToProperty(afsStoff, "anfallstellenNr");
            }
            for (AfsNiederschlagswasser afsNiederschlagwasser: stelle.getAfsNiederschlagswassers()) {
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
            prependIdentifierToNr(stelle.getStandort().getAdresse());
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

    private List<EStandort> prependIdentifierStandort(
              List<EStandort> objects
    ) {
        for (EStandort standort : objects) {
               prependIdentifierToNr(standort);
               prependIdentifierToNr(standort.getAdresse());
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
            Method mSetter = object.getClass().getMethod("setNr", BigInteger.class);
            BigInteger nr = (BigInteger) mGetter.invoke(object);
            if(nr == null) {
                mGetter = object.getClass().getMethod("getNr");
                nr = (BigInteger) mGetter.invoke(object);
                object.getClass().getMethod("setOrigNr", BigInteger.class).invoke(object, nr);
            }
            String newNr = IDENTIFIER + nr.toString();
            BigInteger newBI = new BigInteger(newNr);
            mSetter.invoke(object, newBI);
        } catch (NoSuchMethodException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException
            | SecurityException e) {
            log.error("Error prepending identifier", e);
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

    /**
     * Get the url path for the given entity name.
     * @param entity Entity name
     * @return Url path including leading slash or null if entity is unkown
     */
    private String getUrlByEntity(String entity) {
        switch(entity) {
            case ENTITY_ABA: return "/abwasserbehandlungsanlage";
            case ENTITY_ANFALLSTELLE: return "/anfallstelle";
            case ENTITY_BETRIEBE: return "/betrieb";
            case ENTITY_EINL: return "/einleitungsstelle";
            case ENTITY_MST: return "/messstelle";
            case ENTITY_ADRESSE: return "/adresse";
            case ENTITY_STANDORTE: return "/standort";
            case ENTITY_ENTWG: return "/entwaesserungsgrundstueck";
            case ENTITY_SONDERBAUWERKE: return "/sonderbauwerk";
            default: return null;
        }
    }

    /**
     * Get the tablemodel for the given entity
     * @param entity Entity name
     * @return Tablemodel or null if entity is unkown
     */
    private ListTableModel getModelByEntity(String entity) {
        switch(entity) {
            case ENTITY_ABA: return this.abwasserbehandlungModel;
            case ENTITY_ANFALLSTELLE: return this.anfallstelleModel;
            case ENTITY_BETRIEBE: return this.betriebModel;
            case ENTITY_EINL: return this.einleitungsstelleModel;
            case ENTITY_MST: return this.messstelleModel;
            case ENTITY_ADRESSE: return this.adresseModel;
            case ENTITY_STANDORTE: return this.standortModel;
            case ENTITY_ENTWG: return this.entwgrundModel;
            case ENTITY_SONDERBAUWERKE: return this.sbModel;
            default: return null;
        }
    }

    /**
     * Get entity list of the given entity type
     * @param entity Type as String
     * @return Entity list
     */
    private List<?> getListByEntity(String entity) {
        switch(entity) {
            case ENTITY_ABA:
                return prependIdentifierAbwasserbehandlungsanlage(
                    EAbwasserbehandlungsanlage.getAll());
            case ENTITY_ANFALLSTELLE:
                return prependIdentifierAnfallstelle(EAnfallstelle.getAll());
            case ENTITY_BETRIEBE:
                return prependIdentifierBetrieb(EBetrieb.getAll());
            case ENTITY_EINL:
                return prependIdentifierEinleitungsstelle(
                    EEinleitungsstelle.getAll());
            case ENTITY_MST:
                return prependIdentifierMessstelle(EMessstelle.getAll());
            case ENTITY_ADRESSE:
                return prependIdentifierAdresse(EAdresse.getAll());
            case ENTITY_STANDORTE:
                List<EStandort> items = EStandort.getAll().stream()
                    .filter(standort -> standort.getAdresse().getOrtZst()
                            .equals("Bielefeld"))
                    .collect(Collectors.toList());
                return prependIdentifierStandort(items);
            case ENTITY_ENTWG:
                return prependIdentifierEEntwaesserungsgrundstueck(
                    EEntwaesserungsgrundstueck.getAll());
            case ENTITY_SONDERBAUWERKE:
                return prependIdentifierSonderbauwerk(
                    ESonderbauwerk.getAll());
            default: return null;
        }
    }

    /**
     * Get the given objects id.
     * @param type Object type
     * @param entity Object
     * @return Id as Integer or null
     */
    private BigInteger getEntityId(String type, Object entity) {
        switch(type) {
            case ENTITY_ABA:
                return ((EAbwasserbehandlungsanlage) entity).getNr();
            case ENTITY_ANFALLSTELLE:
                return ((EAnfallstelle) entity).getNr();
            case ENTITY_BETRIEBE:
                return ((EBetrieb) entity).getNr();
            case ENTITY_EINL:
                return ((EEinleitungsstelle) entity).getNr();
            case ENTITY_MST:
                return ((EMessstelle) entity).getNr();
            case ENTITY_ADRESSE:
                return ((EAdresse) entity).getNr();
            case ENTITY_STANDORTE:
                return ((EStandort) entity).getNr();
            case ENTITY_ENTWG:
                return ((EEntwaesserungsgrundstueck) entity).getNr();
            case ENTITY_SONDERBAUWERKE:
                return ((ESonderbauwerk) entity).getNr();
            default: return null;
        }
    }

    /**
     * Open the given file with the default program.
     * @param file File to open
     * @return True if opened successfully, else false
     */
    private boolean openFile(File file) {
        Desktop d = Desktop.getDesktop();
        if (!Desktop.isDesktopSupported() || !d.isSupported(Action.OPEN)) {
            log.warn("Opening file action is not supported");
            return false;
        }
        try {
            d.open(file);
        } catch (IOException e) {
            log.warn(
                String.format("Opening log file %s failed: %s",
                    file.getAbsolutePath(), e.getMessage()),
                e);
            return false;
        }
        return true;
    }

    //Cell renderer used for row number cells
    private class RowNumberCellRenderer extends JLabel implements ListCellRenderer<Object> {
        private static final long serialVersionUID = 2991892569337891137L;
        public RowNumberCellRenderer(JTable table) {
            //Adopt header style from parent table
            JTableHeader header = table.getTableHeader();
            setOpaque(true);
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setHorizontalAlignment(CENTER);
            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
        }
        public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class EntityListEntry {
        private final Integer tableIndex;
        private final Object id;
        private final Entity<?> entity;
        public EntityListEntry(Integer tableIndex, Entity<?> entity) {
            this.tableIndex = tableIndex;
            this.entity = entity;
            this.id = null;
        }
        public EntityListEntry(Integer tableIndex, Object id, Entity<?> entity) {
            this.tableIndex = tableIndex;
            this.id = id;
            this.entity = entity;
        }
        public Integer getTableIndex() {
            return tableIndex;
        }
        public Object getId() {
            return id;
        }
        public Entity<?> getEntity() {
            return entity;
        }
    }
}
