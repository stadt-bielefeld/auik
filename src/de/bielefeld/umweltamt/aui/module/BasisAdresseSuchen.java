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
 * Datei:
 * $Id: BasisAdresseSuchen.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 12.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.23  2005/11/02 13:49:16  u633d
 * - Version vom 2.11.
 *
 * Revision 1.22  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.21  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.component.Factory;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Strassen;
import de.bielefeld.umweltamt.aui.module.common.editors.BetreiberEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisObjektModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.BasicEntryField;
import de.bielefeld.umweltamt.aui.utils.NamedObject;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Ein Modul zum Suchen und Bearbeiten eines Betreibers.
 * @author David Klotz
 */
public class BasisAdresseSuchen extends AbstractModul {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String iconPath = "filefind32.png";

    private JComboBox suchBox;
    private JTextField suchFeld;
    
	private JTextField strassenFeld;
	private JTextField hausnrFeld;
	private JTextField ortFeld;
    private JButton submitButton;
    private JButton submitButtonStandort;
    private JButton submitButtonBetreiber;
    private JSplitPane tabellenSplit;
    private JTable betreiberTabelle;
    private JTable objektTabelle;

    private Action betreiberEditAction;
    private Action betreiberLoeschAction;
    private Action objektNeuAction;
    private JPopupMenu betreiberPopup;

    private Action objektEditAction;
    private Action objektBetreiberEditAction;
    private Action objektStandortEditAction;
    private Action objektLoeschAction;
    private JPopupMenu objektPopup;

    private BasisAdresseModel betreiberModel;
    private BasisObjektModel objektModel;
    public Adresse adresse;

	private Timer suchTimer;

    /**
     * Wird benutzt, um nach dem Bearbeiten etc. wieder den selben Betreiber in
     * der Liste auszuwählen.
     */
    private Adresse lastAdresse;

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Adresse suchen";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     * @return "m_betreiber_suchen"
     */
    @Override
    public String getIdentifier() {
        return "m_adresse_suchen";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory() {
        return "Betriebe";
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getIcon()
     * @see de.bielefeld.umweltamt.aui.AbstractModul#getIcon(String)
     */
    @Override
    public Icon getIcon() {
        return super.getIcon(this.iconPath);
    }

    /*
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
    public JPanel getPanel() {
        if (this.panel == null) {
            this.betreiberModel = new BasisAdresseModel();
            this.objektModel = new BasisObjektModel("Standort", this.manager
                .getSettingsManager().getSetting("auik.prefs.abteilungsfilter"));

            TableFocusListener tfl = TableFocusListener.getInstance();
            getBetreiberTabelle().addFocusListener(tfl);
            getObjektTabelle().addFocusListener(tfl);

            JScrollPane betreiberScroller = new JScrollPane(
                getBetreiberTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            JScrollPane objektScroller = new JScrollPane(getObjektTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            JToolBar submitToolBar = new JToolBar();
            submitToolBar.setFloatable(false);
            submitToolBar.setRollover(true);
            submitToolBar.add(getSubmitButton());

            // Die Tab-Action ist in eine neue Klasse ausgelagert,
            // weil man sie evtl. öfters brauchen wird.
            TabAction ta = new TabAction();
			ta.addComp(getSuchFeld());
			ta.addComp(getHausnrFeld());
            ta.addComp(getBetreiberTabelle());
            ta.addComp(getObjektTabelle());

            this.tabellenSplit = Factory.createStrippedSplitPane(
                JSplitPane.VERTICAL_SPLIT, betreiberScroller, objektScroller,
                0.7);

            FormLayout layout = new FormLayout(
					"l:p, max(4dlu;p), p:g, 3dlu, p, 3dlu, 40dlu, 3dlu, p, 3dlu,  p:g, 3dlu, 70dlu, 3dlu, 70dlu", // spalten
					"pref, 3dlu, pref, 3dlu, 150dlu:grow"); // zeilen

            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            builder.add(getSuchBox(), cc.xy(1, 1));
            builder.add(getSuchFeld(), cc.xy(3, 1));
            builder.add(getSubmitButton(), cc.xyw(13, 1, 3));
			builder.addLabel("Straße:", cc.xy(1, 3));
			builder.add(getStrassenFeld(), cc.xy(3, 3));
			builder.addLabel("Haus-Nr.:", cc.xy(5, 3));
			builder.add(getHausnrFeld(), cc.xy(7, 3));
			builder.addLabel("Ort:", cc.xy(9, 3));
			builder.add(getOrtFeld(), cc.xy(11, 3));
            builder.add(getSubmitButtonBetreiber(), cc.xy(13, 3));
            builder.add(getSubmitButtonStandort(), cc.xy(15, 3));
            builder.add(this.tabellenSplit, cc.xyw(1, 5, 15));

            this.panel = builder.getPanel();
        }
        return this.panel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#show()
     */
    @Override
    public void show() {
        super.show();

        // Gespeicherte Position des Dividers setzen
        if (SettingsManager.getInstance().getSetting(
            "auik.prefs.divloc_betreiber") != null) {
            double divloc = Double.parseDouble(SettingsManager.getInstance()
                .getSetting("auik.prefs.divloc_betreiber"));
            this.tabellenSplit.setDividerLocation(divloc);
        }
        
        this.lastAdresse = null;
        filterBetreiberListe(suchFeld);
        
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#hide()
     */
    @Override
    public void hide() {
        super.hide();

        // Position des Dividers des SplitPanes speichern
        if ((this.tabellenSplit != null)
            && (this.tabellenSplit.getDividerLocation() != -1)) {
            double divloc = (float) (this.tabellenSplit.getDividerLocation())
                / (this.tabellenSplit.getHeight());
            if (divloc >= 0.0 && divloc <= 1.0) {
                SettingsManager.getInstance().setSetting(
                    "auik.prefs.divloc_betreiber", Double.toString(divloc),
                    true);
            }
        }
    }

    private void doSearch() {
        final String name = getSuchFeld().getText();
        final String strasse = getStrassenFeld().getText();
		int hausnr;
		try
		{
			hausnr = Integer.parseInt(getHausnrFeld().getText());
		}
		catch (NumberFormatException e1)
		{
			hausnr = -1;
		}
		final int fhausnr = hausnr;
        final String ort = getOrtFeld().getText();
        final String property = (String) ((NamedObject) getSuchBox().getSelectedItem()).getValue();;


            SwingWorkerVariant worker = new SwingWorkerVariant(
                getBetreiberTabelle()) {
                @Override
                
			protected void doNonUILogic() throws RuntimeException {
//				if (name != "" || strasse != ""
//						|| fhausnr != -1 || ort != "") {
					BasisAdresseSuchen.this.betreiberModel.filterAllList(name,
							strasse, fhausnr, ort, property);
				}
//			}

                @Override
                protected void doUIUpdateLogic() throws RuntimeException {
    				getBetreiberTabelle().clearSelection();
    				
    				BasisAdresseSuchen.this.betreiberModel.fireTableDataChanged();
    				String statusMsg = "Suche: "
    						+ BasisAdresseSuchen.this.betreiberModel.getRowCount()
    						+ " Ergebnis";
    				if (BasisAdresseSuchen.this.betreiberModel.getRowCount() != 1)
    				{
    					statusMsg += "se";
    				}
    				statusMsg += ".";
    				BasisAdresseSuchen.this.frame.changeStatus(statusMsg);
                	BasisAdresseSuchen.this.betreiberModel.fireTableDataChanged();
                }
            };
            worker.start();

			
    } 
    



    /**
	 * Filtert die Adressen-Liste nach Betreibern.
	 * 
	 * @param focusComp
	 *            Welche Komponente soll nach der Suche den Fokus bekommen.
	 */
	public void filterBetreiberListe(Component focusComp)
	{
	    log.debug("Start filterStandortListe()");
		int hausnr;
		try
		{
			hausnr = Integer.parseInt(getHausnrFeld().getText());
		}
		catch (NumberFormatException e1)
		{
			hausnr = -1;
		}
		final int fhausnr = hausnr;
	
		SwingWorkerVariant worker = new SwingWorkerVariant(focusComp)
		{
	
			@Override
			protected void doNonUILogic()
			{
				if (SettingsManager.getInstance().getStandort() != null)
				{					
					BasisAdresseSuchen.this.betreiberModel.filterBetreiber(
																		getSuchFeld().getText(),
																		getStrassenFeld().getText(),
																		fhausnr,
																		getOrtFeld()
																				.getText());
				}
//				getSuchFeld().setText("");
			}
	
			@Override
			protected void doUIUpdateLogic()
			{
				getBetreiberTabelle().clearSelection();
	
				BasisAdresseSuchen.this.betreiberModel.fireTableDataChanged();
				String statusMsg = "Suche: "
						+ BasisAdresseSuchen.this.betreiberModel.getRowCount()
						+ " Ergebnis";
				if (BasisAdresseSuchen.this.betreiberModel.getRowCount() != 1)
				{
					statusMsg += "se";
				}
				statusMsg += ".";
				BasisAdresseSuchen.this.frame.changeStatus(statusMsg);
			}
		};
	
		this.frame.changeStatus("Suche...");
		worker.start();
	    log.debug("End filterStandortListe()");
	}

    /**
	 * Filtert die Adressen-Liste nach Standorten.
	 * 
	 * @param focusComp
	 *            Welche Komponente soll nach der Suche den Fokus bekommen.
	 */
	public void filterStandortListe(Component focusComp)
	{
	    log.debug("Start filterStandortListe()");
		int hausnr;
		try
		{
			hausnr = Integer.parseInt(getHausnrFeld().getText());
		}
		catch (NumberFormatException e1)
		{
			hausnr = -1;
		}
		final int fhausnr = hausnr;
	
		SwingWorkerVariant worker = new SwingWorkerVariant(focusComp)
		{
	
			@Override
			protected void doNonUILogic()
			{
				if (SettingsManager.getInstance().getStandort() != null)
				{
					BasisAdresseSuchen.this.betreiberModel.filterStandort(
																		getSuchFeld().getText(),
																		getStrassenFeld().getText(),
																		fhausnr,
																		getOrtFeld()
																				.getText());
				}
			}
	
			@Override
			protected void doUIUpdateLogic()
			{
				getBetreiberTabelle().clearSelection();
	
				BasisAdresseSuchen.this.betreiberModel.fireTableDataChanged();
				String statusMsg = "Suche: "
						+ BasisAdresseSuchen.this.betreiberModel.getRowCount()
						+ " Ergebnis";
				if (BasisAdresseSuchen.this.betreiberModel.getRowCount() != 1)
				{
					statusMsg += "se";
				}
				statusMsg += ".";
				BasisAdresseSuchen.this.frame.changeStatus(statusMsg);
			}
		};
	
		this.frame.changeStatus("Suche...");
		worker.start();
	    log.debug("End filterStandortListe()");
	}


	public void updateObjekte() {
        log.debug("Start updateObjekte()");
        ListSelectionModel lsm = getBetreiberTabelle().getSelectionModel();
        if (!lsm.isSelectionEmpty()) {
            int selectedRow = lsm.getMinSelectionIndex();
            Adresse betr = this.betreiberModel.getRow(selectedRow);
            log.debug("Betreiber " + betr.getBetrname() + " (ID"
                + betr.getId() + ") angewählt.");
            searchObjekteByBetreiber(betr);
            this.adresse = betr;
        }
        
        log.debug("End updateObjekte()");
    }

    /**
     * öffnet einen Dialog um einen Betreiber-Datensatz zu bearbeiten.
     * @param betr Der Betreiber
     */
    public void editBetreiber(Adresse betr) {
        BetreiberEditor editDialog = new BetreiberEditor(betr, this.frame);
        editDialog.setLocationRelativeTo(this.frame);

        editDialog.setVisible(true);

        this.lastAdresse = betr;

        // Nach dem Bearbeiten die Liste updaten, damit unsere Änderungen auch
        // angezeigt werden.
//        updateBetreiberListe();
    }

    /**
     * Setzt den Tabelleninhalt der Objekt-Tabelle auf alle Objekte eines
     * Betreibers.
     * @param betreiberid Die Betreiber-Id
     */
    public void searchObjekteByBetreiber(final Adresse betreiber) {
        // ... siehe show()
        SwingWorkerVariant worker = new SwingWorkerVariant(
            getBetreiberTabelle()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                BasisAdresseSuchen.this.objektModel
                    .searchByStandort(betreiber);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                BasisAdresseSuchen.this.objektModel.fireTableDataChanged();
            }
        };
        worker.start();
    }

    private Action getBetreiberEditAction() {
        if (this.betreiberEditAction == null) {
            this.betreiberEditAction = new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID = 5689189314194296978L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getBetreiberTabelle().getSelectedRow();

                    // Natürlich nur editieren, wenn wirklich eine Zeile
                    // ausgewählt ist
                    if (row != -1) {
                        Adresse betr = BasisAdresseSuchen.this.betreiberModel
                            .getRow(row);
                        editBetreiber(betr);
                    }
                }
            };
            this.betreiberEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_B));
            this.betreiberEditAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
        }

        return this.betreiberEditAction;
    }

    private Action getBetreiberLoeschAction() {
        if (this.betreiberLoeschAction == null) {
            this.betreiberLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 6709934716520847123L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getBetreiberTabelle().getSelectedRow();
                    if (row != -1
                        && getBetreiberTabelle().getEditingRow() == -1) {
                        if (BasisAdresseSuchen.this.objektModel.getRowCount() != 0) {
                            BasisAdresseSuchen.this.frame
                                .changeStatus(
                                    "Kann Betreiber nicht löschen: Zu erst alle zugehörigen Objekte löschen!",
                                    HauptFrame.ERROR_COLOR);
                        } else {
                            Adresse betr = BasisAdresseSuchen.this.betreiberModel
                                .getRow(row);

                            if (GUIManager.getInstance().showQuestion(
                                "Soll der Betreiber '" + betr
                                    + "' wirklich gelöscht werden?",
                                "Löschen bestätigen")) {
                                if (BasisAdresseSuchen.this.betreiberModel
                                    .removeRow(row)) {
                                    BasisAdresseSuchen.this.frame
                                        .changeStatus("Betreiber gelöscht.",
                                            HauptFrame.SUCCESS_COLOR);
                                    log.debug("Betreiber "
                                        + betr.getId()
                                        + " wurde gelöscht!");
                                } else {
                                    BasisAdresseSuchen.this.frame
                                        .changeStatus(
                                            "Konnte den Betreiber nicht löschen!",
                                            HauptFrame.ERROR_COLOR);
                                }
                            }
                        }
                    }
                }
            };
            this.betreiberLoeschAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_L));
            this.betreiberLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.betreiberLoeschAction;
    }

    private Action getObjektNeuAction() {
        if (this.objektNeuAction == null) {
            this.objektNeuAction = new AbstractAction("Neues Objekt") {
                private static final long serialVersionUID = 1922038365500278302L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = BasisAdresseSuchen.this.betreiberTabelle
                        .getSelectedRow();

                    if (row != -1) {
                        Adresse betr = BasisAdresseSuchen.this.betreiberModel
                            .getRow(row);
                        BasisAdresseSuchen.this.manager.getSettingsManager()
                            .setSetting("auik.imc.use_standort",
                                betr.getId().intValue(), false);
                        BasisAdresseSuchen.this.manager.getSettingsManager()
                        .setSetting("auik.imc.use_betreiber",
                            betr.getId().intValue(), false);
                        BasisAdresseSuchen.this.manager
                            .switchModul("m_objekt_bearbeiten");
                    }
                }
            };
            this.objektNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_O));
        }

        return this.objektNeuAction;
    }

    private Action getObjektEditAction() {
        if (this.objektEditAction == null) {
            this.objektEditAction = new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID = 374432667200396085L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = BasisAdresseSuchen.this.objektTabelle
                        .getSelectedRow();
                    Objekt obj = BasisAdresseSuchen.this.objektModel
                        .getRow(row);
                    if (row != -1
                        || (!(obj.getObjektarten().getId().equals(
                            DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)))) {
                        BasisAdresseSuchen.this.manager.getSettingsManager()
                            .setSetting("auik.imc.edit_object",
                                obj.getId().intValue(), false);
                        BasisAdresseSuchen.this.manager
                            .switchModul("m_objekt_bearbeiten");
                    } else if (row != -1
                        || obj.getObjektarten().getId().equals(
                            DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)) {
                        BasisAdresseSuchen.this.manager.getSettingsManager()
                            .setSetting("auik.imc.edit_object",
                                obj.getId().intValue(), false);
                        BasisAdresseSuchen.this.manager
                            .switchModul("m_sielhaut1");
                    }
                }
            };
            this.objektEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_B));
            this.objektEditAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
        }

        return this.objektEditAction;
    }

    private Action getObjektBetreiberEditAction() {
        if (this.objektBetreiberEditAction == null) {
            this.objektBetreiberEditAction = new AbstractAction("Betreiber") {

				private static final long serialVersionUID = 9162213813094661474L;

				@Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle().getSelectedRow();

                    if (row != -1) {
                        Objekt obj = BasisAdresseSuchen.this.objektModel
                            .getRow(row);
                        editBetreiber(obj.getBetreiberid());
                    }
                }
            };
            this.objektBetreiberEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_F1));
            this.objektBetreiberEditAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false));
        }

        return this.objektBetreiberEditAction;
    }

    private Action getObjektStandortEditAction() {
        if (this.objektStandortEditAction == null) {
            this.objektStandortEditAction = new AbstractAction("Standort") {

				private static final long serialVersionUID = 8864140472500015190L;

				@Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle().getSelectedRow();

                    // Natürlich nur editieren, wenn wirklich eine Zeile
                    // ausgewählt ist
                    if (row != -1) {
                        Objekt obj = BasisAdresseSuchen.this.objektModel
                            .getRow(row);
                        editBetreiber(obj.getBetreiberid());
                    }
                }
            };
            this.objektStandortEditAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_F2));
            this.objektStandortEditAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false));
        }

        return this.objektStandortEditAction;
    }

    private Action getObjektLoeschAction() {
        if (this.objektLoeschAction == null) {
            this.objektLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = 5285618973743780113L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getObjektTabelle().getSelectedRow();
                    if (row != -1 && getObjektTabelle().getEditingRow() == -1) {
                        Objekt objekt = BasisAdresseSuchen.this.objektModel
                            .getRow(row);

                        if (GUIManager.getInstance().showQuestion(
                            "Soll das Objekt " + objekt.getId()
                                + " und alle seine Fachdaten wirklich "
                                + "gelöscht werden?\n"
                                + "Hinweis: Manche Objekte können auch erst"
                                + " gelöscht werden, wenn für sie\n"
                                + "keine Fachdaten mehr existieren.",
                            "Löschen bestätigen")) {
                            if (BasisAdresseSuchen.this.objektModel
                                .removeRow(row)) {
                                BasisAdresseSuchen.this.frame.changeStatus(
                                    "Objekt gelöscht.",
                                    HauptFrame.SUCCESS_COLOR);
                                log.debug("Objekt " + objekt.getId()
                                    + " wurde gelöscht!");
                            } else {
                                BasisAdresseSuchen.this.frame.changeStatus(
                                    "Konnte das Objekt nicht löschen!",
                                    HauptFrame.ERROR_COLOR);
                            }
                        }
                    }
                }
            };
            this.objektLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_L));
            this.objektLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.objektLoeschAction;
    }

    private void showBetreiberPopup(MouseEvent e) {
        if (this.betreiberPopup == null) {
            this.betreiberPopup = new JPopupMenu("Betreiber");
            JMenuItem bearbItem = new JMenuItem(getBetreiberEditAction());
            JMenuItem loeschItem = new JMenuItem(getBetreiberLoeschAction());
            JMenuItem neuItem = new JMenuItem(getObjektNeuAction());
            this.betreiberPopup.add(bearbItem);
            this.betreiberPopup.add(loeschItem);
            this.betreiberPopup.add(neuItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.betreiberTabelle.rowAtPoint(origin);

            if (row != -1) {
                this.betreiberTabelle.setRowSelectionInterval(row, row);
                this.betreiberPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private void showObjektPopup(MouseEvent e) {
        if (this.objektPopup == null) {
            this.objektPopup = new JPopupMenu("Objekt");
            JMenuItem bearbItem = new JMenuItem(getObjektEditAction());
            JMenuItem objbtrItem = new JMenuItem(getObjektBetreiberEditAction());
            JMenuItem objstdItem = new JMenuItem(getObjektStandortEditAction());
            JMenuItem loeschItem = new JMenuItem(getObjektLoeschAction());
            this.objektPopup.add(bearbItem);
            this.objektPopup.add(objbtrItem);
            this.objektPopup.add(objstdItem);
            this.objektPopup.add(loeschItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = this.objektTabelle.rowAtPoint(origin);

            if (row != -1) {
                this.objektTabelle.setRowSelectionInterval(row, row);
                this.objektPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private JTable getBetreiberTabelle() {
        if (this.betreiberTabelle == null) {
            this.betreiberTabelle = new JTable(this.betreiberModel);

            // Wir wollen wissen, wenn eine andere Zeile ausgewählt wurde
            ListSelectionModel rowSM = this.betreiberTabelle
                .getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    // überzählige Events ignorieren
                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    updateObjekte();
                }
            });

            this.betreiberTabelle.getColumnModel().getColumn(0)
                .setPreferredWidth(200);
            this.betreiberTabelle.getColumnModel().getColumn(1)
                .setPreferredWidth(60);
            this.betreiberTabelle.getColumnModel().getColumn(2)
                .setPreferredWidth(70);
            this.betreiberTabelle.getColumnModel().getColumn(3)
            	.setPreferredWidth(70);
            this.betreiberTabelle.getColumnModel().getColumn(4)
            	.setPreferredWidth(70);
            this.betreiberTabelle.getColumnModel().getColumn(5)
        		.setPreferredWidth(70);
            this.betreiberTabelle.getColumnModel().getColumn(6)
            	.setPreferredWidth(30);

            this.betreiberTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.betreiberTabelle.setColumnSelectionAllowed(false);
            this.betreiberTabelle.setRowSelectionAllowed(true);

            this.betreiberTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    private Object submitButton;

					@Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getBetreiberTabelle().rowAtPoint(origin);

                            Adresse betr = BasisAdresseSuchen.this.betreiberModel
                                .getRow(row);
                            log.debug("Doppelklick auf Zeile " + row);
                            editBetreiber(betr);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        showBetreiberPopup(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showBetreiberPopup(e);
                    }
                });

            // Bei Enter in der Tabelle wird der Betreiber auch bearbeitet
            // und mit TAB soll man zwischen den Tabellen springen können.
            // Dafür benutzen wir das (seit 1.3) neue Swing-Konzept
            // der Action- und InputMaps mit dem bestimmte Aktionen ("Actions")
            // bestimmten Tastendrücken ("Inputs")zugewiesen werden können.

            // Den "Enter"-KeyStroke in die InputMap der Tabelle einfügen
            this.betreiberTabelle.getInputMap().put(
                (KeyStroke) getBetreiberEditAction().getValue(
                    Action.ACCELERATOR_KEY),
                getBetreiberEditAction().getValue(Action.NAME));
            // Die Action dem "Enter"-KeyStroke zuweisen
            this.betreiberTabelle.getActionMap().put(
                getBetreiberEditAction().getValue(Action.NAME),
                getBetreiberEditAction());

            this.betreiberTabelle.getInputMap().put(
                (KeyStroke) getBetreiberLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getBetreiberLoeschAction().getValue(Action.NAME));
            this.betreiberTabelle.getActionMap().put(
                getBetreiberLoeschAction().getValue(Action.NAME),
                getBetreiberLoeschAction());
        }
        return this.betreiberTabelle;
    }

    private JTable getObjektTabelle() {
    	Adresse adr = this.adresse;
        if (this.objektTabelle == null) {
        	
            this.objektTabelle = new JTable(this.objektModel){

				public Component prepareRenderer(TableCellRenderer renderer,
						int row, int column) {
					Component c = super.prepareRenderer(renderer, row, column);
					Objekt obj = BasisAdresseSuchen.this.objektModel
							.getRow(row);
					if (!isRowSelected(row)) {

						if (obj.getBetreiberid() == obj.getStandortid().getAdresse()) {
							c.setBackground(new Color(153, 255, 153));
						}						
						
						else if (obj.getBetreiberid() != obj.getStandortid().getAdresse()) {
							c.setBackground(new Color(255, 255, 153));
						}
						else {
							c.setBackground(Color.WHITE);
						}
					}
					return c;
				}
			};
              
			this.objektTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.objektTabelle.getColumnModel().getColumn(0).setMaxWidth(60);
            this.objektTabelle
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                    this.objektTabelle.getColumnModel().getColumn(0)
                        .getMaxWidth() - 10);
            
            this.objektTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = getObjektTabelle().rowAtPoint(origin);
                            Objekt obj =
                                BasisAdresseSuchen.this.objektModel.getRow(row);
                            if (row != -1
                                && (!(obj.getObjektarten().getId().equals(
                                    DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)))) {
                                BasisAdresseSuchen.this.manager
                                    .getSettingsManager().setSetting(
                                        "auik.imc.edit_object",
                                        obj.getId().intValue(), false);
                                BasisAdresseSuchen.this.manager
                                    .switchModul("m_objekt_bearbeiten");
                            } else if (row != -1
                                && obj.getObjektarten().getId().equals(
                                    DatabaseConstants.BASIS_OBJEKTART_ID_SIELHAUTMESSSTELLE)) {
                                BasisAdresseSuchen.this.manager
                                    .getSettingsManager().setSetting(
                                        "auik.imc.edit_object",
                                        obj.getId().intValue(), false);
                                BasisAdresseSuchen.this.manager
                                    .switchModul("m_sielhaut1");
                            }                            	
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        showObjektPopup(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showObjektPopup(e);
                    }
                });

			this.objektTabelle.getInputMap().put(
					(KeyStroke) getObjektEditAction().getValue(
							Action.ACCELERATOR_KEY),
					getObjektEditAction().getValue(Action.NAME));
			this.objektTabelle.getActionMap().put(
					getObjektEditAction().getValue(Action.NAME),
					getObjektEditAction());

			this.objektTabelle.getInputMap().put(
					(KeyStroke) getObjektBetreiberEditAction().getValue(
							Action.ACCELERATOR_KEY),
					getObjektBetreiberEditAction().getValue(Action.NAME));
			this.objektTabelle.getActionMap().put(
					getObjektBetreiberEditAction().getValue(Action.NAME),
					getObjektBetreiberEditAction());

			this.objektTabelle.getInputMap().put(
					(KeyStroke) getObjektStandortEditAction().getValue(
							Action.ACCELERATOR_KEY),
					getObjektStandortEditAction().getValue(Action.NAME));
			this.objektTabelle.getActionMap().put(
					getObjektStandortEditAction().getValue(Action.NAME),
					getObjektStandortEditAction());

			this.objektTabelle.getInputMap().put(
					(KeyStroke) getObjektLoeschAction().getValue(
							Action.ACCELERATOR_KEY),
					getObjektLoeschAction().getValue(Action.NAME));
			this.objektTabelle.getActionMap().put(
					getObjektLoeschAction().getValue(Action.NAME),
					getObjektLoeschAction());
        }
        return this.objektTabelle;
    }

    private JButton getSubmitButton() {
        if (this.submitButton == null) {
            this.submitButton = new JButton("Alle Adressen", AuikUtils.getIcon(16,
                "key_enter.png"));
            this.submitButton.setToolTipText("Suche starten");
            this.submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					getSuchTimer().stop();
					doSearch();
                }
            });
        }

        return this.submitButton;
    }

    private JButton getSubmitButtonBetreiber() {
        if (this.submitButtonBetreiber == null) {
            this.submitButtonBetreiber = new JButton("nur Betreiber");
            this.submitButtonBetreiber.setToolTipText("Suche starten");
            this.submitButtonBetreiber.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					getSuchTimer().stop();
					filterBetreiberListe(getBetreiberTabelle());
                }
            });
        }

        return this.submitButtonBetreiber;
    }

    private JButton getSubmitButtonStandort() {
        if (this.submitButtonStandort == null) {
            this.submitButtonStandort = new JButton("nur Standorte");
            this.submitButtonStandort.setToolTipText("Suche starten");
            this.submitButtonStandort.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
					getSuchTimer().stop();
					filterStandortListe(getBetreiberTabelle());
                }
            });
        }

        return this.submitButtonStandort;
    }

	private JTextField getSuchFeld() {
	    if (this.suchFeld == null) {
	        this.suchFeld = new JTextField();
	        this.suchFeld.requestFocusInWindow();
			this.suchFeld.setFocusTraversalKeys(
					KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
					Collections.EMPTY_SET);
	
	        this.suchFeld.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String suche = getSuchFeld().getText();
	                String spalte = (String) ((NamedObject) getSuchBox()
	                    .getSelectedItem()).getValue();
	                doSearch();
	            }
	        });
//	        this.suchFeld.setFocusTraversalKeys(
//	            KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
//	            Collections.EMPTY_SET);
//	
//	        this.suchFeld.addKeyListener(new KeyAdapter() {
//	            @Override
//	            public void keyPressed(KeyEvent e) {
//	                if (e.getKeyCode() == KeyEvent.VK_TAB) {
//	                    String suche = getSuchFeld().getText();
//	                    String spalte = (String) ((NamedObject) getSuchBox()
//	                        .getSelectedItem()).getValue();
//	                    doSearch();
//	                }
//	            }
//	        });
	    }
	    return this.suchFeld;
	}

	private JTextField getStrassenFeld()
	{
		if (this.strassenFeld == null)
		{
			this.strassenFeld = new JTextField("");
			this.strassenFeld.setFocusTraversalKeys(
													KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
													Collections.EMPTY_SET);
	
			this.strassenFeld.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					getSuchTimer().stop();
					doSearch();
				}
			});
	
			this.strassenFeld.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_TAB)
					{
						getSuchTimer().stop();
						doSearch();
					}
				}
	
				@Override
				public void keyTyped(KeyEvent e)
				{
					if (Character.isLetterOrDigit(e.getKeyChar()))
					{
						if (getSuchTimer().isRunning())
						{
							getSuchTimer().restart();
						}
						else
						{
							getSuchTimer().start();
						}
					}
				}
			});
		}
		return this.strassenFeld;
	}

	private JTextField getOrtFeld()
	{
		if (this.ortFeld == null)
		{
			this.ortFeld = new JTextField("");
			this.ortFeld.setFocusTraversalKeys(
												KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
												Collections.EMPTY_SET);
	
			this.ortFeld.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					getSuchTimer().stop();
					doSearch();
				}
			});
	
			this.ortFeld.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_TAB)
					{
						getSuchTimer().stop();
						doSearch();
					}
				}
	
				@Override
				public void keyTyped(KeyEvent e)
				{
					if (Character.isLetterOrDigit(e.getKeyChar()))
					{
						if (getSuchTimer().isRunning())
						{
							getSuchTimer().restart();
						}
						else
						{
							getSuchTimer().start();
						}
					}
				}
			});
		}
		return this.ortFeld;
	}

	private JTextField getHausnrFeld()
	{
		if (this.hausnrFeld == null)
		{
			this.hausnrFeld = new BasicEntryField();
	
			this.hausnrFeld.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					doSearch();
				}
			});
		}
		return this.hausnrFeld;
	}

	private Timer getSuchTimer()
	{
		if (this.suchTimer == null)
		{
			this.suchTimer = new Timer(900, new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
	
					// Was diese ganze "SwingWorkerVariant"-Geschichte
					// soll, steht unter
					// http://www.javaworld.com/javaworld/jw-06-2003/jw-0606-swingworker.html
					// Ist auch ausgedruckt im Ordner im Regal. -DK
					SwingWorkerVariant worker = new SwingWorkerVariant(
							getStrassenFeld())
					{
						protected String oldText = "";
						private String newText = "";
	
						@Override
						protected void doNonUILogic()
						{
							this.oldText = getStrassenFeld().getText();
							if (this.oldText.equals(""))
							{
								this.newText = "";
							}
							else
							{
								String suchText = AuikUtils
										.sanitizeQueryInput(this.oldText);
								Strassen str = DatabaseQuery
										.findStrasse(suchText);
	
								if (str != null)
								{
									this.newText = str.getStrasse();
								}
								else
								{
									this.newText = this.oldText;
								}
							}
						}
	
						@Override
						protected void doUIUpdateLogic()
						{
							getStrassenFeld().setText(this.newText);
							getStrassenFeld().setSelectionStart(
																this.oldText.length());
							getStrassenFeld().setSelectionEnd(
																this.newText.length());
						}
					};
					worker.start();
				}
			});
			this.suchTimer.setRepeats(false);
		}
	
		return this.suchTimer;
	}

	private JComboBox getSuchBox() {
	    if (this.suchBox == null) {
	        this.suchBox = new JComboBox(new NamedObject[] {
	                new NamedObject("Anrede:", "anrede"),
	                new NamedObject("Vorname:", "vorname"),
	                new NamedObject("Name:", "name"),
	                new NamedObject("Zusatz:", "zusatz"),
	                new NamedObject("Irgendwo", null)});
	        this.suchBox.setSelectedIndex(2);
	    }
	    return this.suchBox;
	}
}
