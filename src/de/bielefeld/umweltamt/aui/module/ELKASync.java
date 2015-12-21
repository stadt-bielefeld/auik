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

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;

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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.gui.CredentialsDialog;
import de.bielefeld.umweltamt.aui.mappings.elka.EAbwasserbehandlungsanlage;
import de.bielefeld.umweltamt.aui.mappings.elka.EAdresse;
import de.bielefeld.umweltamt.aui.mappings.elka.EAnfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.EBetrieb;
import de.bielefeld.umweltamt.aui.mappings.elka.EEinleitungsstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.EMessstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.EProbenahme;
import de.bielefeld.umweltamt.aui.mappings.elka.EProbenahmeUeberwachungsergeb;
import de.bielefeld.umweltamt.aui.mappings.elka.EStandort;
import de.bielefeld.umweltamt.aui.mappings.elka.EWasserrecht;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EAbwasserbehandlungsanlageModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EAnfallstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EBetriebModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EEinleitungsstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EMessstelleModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EStandortModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

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

        
    private JTable dbTable;
    private JLabel rowCount;
    private JLabel progressCounter;
    
    // Logindaten für den entfernten Service.
    private String url;
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
		    	ELKASync.this.messstelleModel.setList(
		    		prependIdentifierMessstelle(EMessstelle.getAll()));
		    	ELKASync.this.einleitungsstelleModel.setList(
		    		prependIdentifierEinleitungsstelle(
		    			EEinleitungsstelle.getAll()));
		    	ELKASync.this.anfallstelleModel.setList(
		    		prependIdentifierAnfallstelle(EAnfallstelle.getAll()));
		    	ELKASync.this.betriebModel.setList(
		    		prependIdentifierBetrieb(EBetrieb.getAll()));
		    	ELKASync.this.abwasserbehandlungModel.setList(
		    		prependIdentifierAbwasserbehandlungsanlage(
		    			EAbwasserbehandlungsanlage.getAll()));
		    	ELKASync.this.adresseModel.setList(
		    		prependIdentifierAdresse(EAdresse.getAll()));
		    	ELKASync.this.standortModel.setList(
		    		prependIdentifierStandort(EStandort.getAll()));
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
        
        this.dbTable = new JTable();
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
        			"Abwasserbehandlungsanlagen",
            		"Anfallstellen",
            		"Betriebe",
            		"Einleitungsstellen",
            		"Messstellen"};
            if (entities != null && entities.length > 0) {
                for (String entity : entities) {
                    selection.addItem(entity);
                }
            }
            selection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String item = (String) selection.getSelectedItem();
                    if (item.equals("Abwasserbehandlungsanlagen")) {
                    	ELKASync.this.dbTable.setModel(
                    		ELKASync.this.abwasserbehandlungModel);
                    }
                    else if (item.equals("Anfallstellen")) {
                    	ELKASync.this.dbTable.setModel(
                        		ELKASync.this.anfallstelleModel);
                    }
                    else if (item.equals("Betriebe")) {
                    	ELKASync.this.dbTable.setModel(
                        		ELKASync.this.betriebModel);
                    }
                    else if (item.equals("Einleitungsstellen")) {
                    	ELKASync.this.dbTable.setModel(
                        		ELKASync.this.einleitungsstelleModel);
                    }
                    else if (item.equals("Messstellen")) {
                    	ELKASync.this.dbTable.setModel(
                        		ELKASync.this.messstelleModel);
                    }
                    else if (item.equals("Adressen")) {
                    	ELKASync.this.dbTable.setModel(
                        		ELKASync.this.adresseModel);
                    }
                    else if (item.equals("Standorte")) {
                    	ELKASync.this.dbTable.setModel(
                        		ELKASync.this.standortModel);
                    }
                    ELKASync.this.rowCount.setText(String
                        .valueOf(ELKASync.this.dbTable.getRowCount()));
                    progress.setValue(0);
                    ELKASync.this.progressCounter.setText("-/-");
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
							List<Entity<?>> entityList = 
								new ArrayList<Entity<?>>();
							List<Object> dbList = new ArrayList<Object>();
							int[] rows = ELKASync.this.dbTable.getSelectedRows();
							
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
							List<Entity<?>> entityList = 
								new ArrayList<Entity<?>>();
							List<?> dbList = null;

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
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();
            JScrollPane dbScroller = new JScrollPane(this.dbTable,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            builder.addLabel("Tabelle", cc.xy(1, 1));
            builder.add(selection, cc.xy(3, 1));
            builder.add(commitTable, cc.xy(5, 1));
            builder.add(commitEntries, cc.xy(7, 1));
            builder.add(dbScroller, cc.xyw(1, 3, 10));
            builder.addLabel("Anzahl der Elemente: ", cc.xy(1, 5));
            builder.add(this.rowCount, cc.xy(3, 5));
            builder.add(new JLabel("Fortschritt: "), cc.xy(5, 5, CellConstraints.RIGHT, CellConstraints.CENTER));
            builder.add(progress, cc.xy(7, 5));
            builder.add(this.progressCounter, cc.xy(9, 5));
            this.panel = builder.getPanel();
        }
        return this.panel;
    }
    
    private <T> void sendData(
    	List<Entity<?>> entities,
    	JerseyWebTarget target,
    	JProgressBar progress,
    	String type
    ) {
		if (entities.size() > 0) {
			System.setProperty("http.proxyHost", PROXY_HOST);
			System.setProperty("http.proxyPort", PROXY_PORT);
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
						Invocation inv =
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

    public void setServiceUrl(String url) {
    	this.url = url;
    }

    public void setServiceUser(String user) {
    	this.user = user;
    }

    public void setServicePassword(String password) {
    	this.password = password;
    }
    
    private List<EAbwasserbehandlungsanlage> prependIdentifierAbwasserbehandlungsanlage(
   		List<EAbwasserbehandlungsanlage> objects
    ) {
    	for (EAbwasserbehandlungsanlage anlage : objects) {
    		log.debug("identifier for " + anlage.getNr());
    		prependIdentifier(anlage);
    		prependIdentifier(anlage.getAdresseByBetreibAdrNr());
    		prependIdentifier(anlage.getAdresseByStoAdrNr());
    		prependIdentifier(anlage.getStandort());
    		prependIdentifier(anlage.getStandort().getAdresse());
    		for (EWasserrecht recht : anlage.getWasserrechts()) {
    			prependIdentifier(recht);
    			prependIdentifier(recht.getAdresse());
    		}
    	}
    	return objects;
    }
    
    private List<EAnfallstelle> prependIdentifierAnfallstelle (
    	List<EAnfallstelle> objects
    ) {
    	for (EAnfallstelle stelle : objects) {
    		prependIdentifier(stelle);
    		prependIdentifier(stelle.getAdresse());
    		prependIdentifier(stelle.getStandort());
    		prependIdentifier(stelle.getStandort().getAdresse());
    	}
    	return objects;
    }
    
    private List<EBetrieb> prependIdentifierBetrieb(List<EBetrieb> objects) {
    	for (EBetrieb betrieb : objects) {
    		prependIdentifier(betrieb);
    		prependIdentifier(betrieb.getAdresseByWrAdrNr());
    		prependIdentifier(betrieb.getStandort());
    		prependIdentifier(betrieb.getStandort().getAdresse());
    	}
    	return objects;
    }
    
    private List<EEinleitungsstelle> prependIdentifierEinleitungsstelle(
    	List<EEinleitungsstelle> objects	
    ) {
    	for (EEinleitungsstelle stelle : objects) {
    		prependIdentifier(stelle);
    		prependIdentifier(stelle.getStandort());
    		prependIdentifier(stelle.getStandort().getAdresse());
    		for (EWasserrecht recht : stelle.getWasserrechts()) {
    			prependIdentifier(recht);
    			prependIdentifier(recht.getAdresse());
    		}
    	}
    	return objects;
    }
    
    private List<EMessstelle> prependIdentifierMessstelle(
    	List<EMessstelle> objects
    ) {
    	for (EMessstelle stelle : objects) {
    		prependIdentifier(stelle);
    		prependIdentifier(stelle.getStandort());
    		prependIdentifier(stelle.getStandort().getAdresse());
    		for (EProbenahme nahme : stelle.getProbenahmes()) {
    			prependIdentifier(nahme);
    			for (EProbenahmeUeberwachungsergeb ergeb : 
    				nahme.getProbenahmeUeberwachungsergebs()) {
    				prependIdentifier(ergeb);
    			}
    		}
    	}
    	return objects;
    }

    private List<EAdresse> prependIdentifierAdresse(
      	List<EAdresse> objects
    ) {
       	for (EAdresse adresse : objects) {
       		prependIdentifier(adresse);
       	}
       	return objects;
    }

    private List<EStandort> prependIdentifierStandort(
          	List<EStandort> objects
    ) {
    	for (EStandort standort : objects) {
       		prependIdentifier(standort);
       		prependIdentifier(standort.getAdresse());
       	}
       	return objects;
    }
    
    private <T> T prependIdentifier(T object) {
    	try {
			Method mGetter = object.getClass().getMethod("getOrigNr");
			Method mSetter = object.getClass().getMethod("setNr", Integer.class);
			Integer nr = (Integer)mGetter.invoke(object);
			String newNr = IDENTIFIER + nr.toString();
			mSetter.invoke(object, Integer.valueOf(newNr));
		} catch (NoSuchMethodException | SecurityException e) {
			// Do nothing since the object has no Nr field.
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
