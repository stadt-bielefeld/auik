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
 * $Id: EntsorgerEditor.java,v 1.3 2009-07-30 05:31:22 u633d Exp $
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.4  2005/06/10 11:07:09  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.3  2005/06/09 10:17:00  u633z
 * - doSave verbessert
 *
 * Revision 1.2  2005/06/08 08:36:59  u633z
 * - überflüssigen ModulManager und Exception aus Editoren entfernt
 *
 * Revision 1.1  2005/05/30 08:35:41  u633z
 * - Aufgeräumt, mehrere neue Packages, Klassen sortiert
 *
 * Revision 1.2  2005/05/24 15:19:21  u633z
 * - Unnötiges neu laden des Betreiber-Caches entfernt
 * - Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.SelectTable;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein Dialog zum Bearbeiten der Einstellungen.
 * 
 * @author Gerhard Genuit
 */
public class EinstellungenEditor extends AbstractApplyEditor {
    private static final long serialVersionUID = -225497689116093559L;
    private static SettingsManager _instance = SettingsManager.getInstance();
    private JTable einstellungenTabelle;
    private JLabel titel;
    private EinstellungenModel einstModel;
    private List settinglist;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private class EinstellungenModel extends EditableListTableModel {
        private static final long serialVersionUID = 6042681141925302970L;

        public EinstellungenModel(SettingsManager instance) {
            super(new String[] { "Parameter", "Wert" }, true);

            settinglist = Arrays.asList(_instance.getSettingList());

            updateList();
        }

        @Override
        public void updateList() {

            setList(settinglist);
            fireTableDataChanged();
        }

        @Override
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Object value;
            Object[] row = (Object[]) objectAtRow;
            switch (columnIndex) {
            // Parameter
            case 0:
                value = row[0];
                break;
            // Wert
            case 1:
                value = row[1];
                break;
            default:
                value = null;
            }

            return value;
        }

        @Override
        public void editObject(Object objectAtRow, int columnIndex,
                Object newValue) {
            Object[] row = (Object[]) objectAtRow;

            switch (columnIndex) {
            case 0:
                String tmpPara = (String) newValue;
                row[0] = tmpPara;
                break;

            case 1:
                String tmpWert = (String) newValue;
                row[1] = tmpWert;
                _instance.setSetting((String)row[0], tmpWert, true);
                break;
                
            default:
                break;
            }
            
        }

        @Override
        public Object newObject() {
            return _instance;
        }

        @Override
        public boolean objectRemoved(Object objectAtRow) {

            return true;
        }
    }

    /**
     * Erzeugt einen neuen Dialog zum Bearbeiten der Einstellungen.
     */
    public EinstellungenEditor(Object[] settinglist, HauptFrame owner) {
        super("Einstellung ", settinglist, owner);
    }

    @Override
    protected JComponent buildContentArea() {

        // Der folgende KeyListener wird benutzt um mit Escape
        // das Bearbeiten abzubrechen.
        KeyListener escListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    doCancel();
                }
            }
        };
        getEinstellungenTabelle().setModel(getEinstellungenModel());
        JScrollPane tabellenScroller = new JScrollPane(
                getEinstellungenTabelle(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        TabAction ta = new TabAction();
        ta.addComp(this.einstellungenTabelle);
        

        Action einstRemoveAction = new AbstractAction("Einstellung löschen") {
            private static final long serialVersionUID = -5755536713201543469L;

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = EinstellungenEditor.this.einstellungenTabelle.getSelectedRow();
                if (row != -1
                    && EinstellungenEditor.this.einstellungenTabelle.getEditingRow() == -1) {
                    EinstellungenEditor.this.einstModel.removeRow(row);
                }
            }
        };

        KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
                0, false);

        einstRemoveAction.putValue(Action.ACCELERATOR_KEY, deleteKeyStroke);

        this.einstellungenTabelle.getInputMap().put(deleteKeyStroke,
                einstRemoveAction.getValue(Action.NAME));

        this.einstellungenTabelle.getActionMap().put(
                einstRemoveAction.getValue(Action.NAME), einstRemoveAction);

        titel = new JLabel("<html><body>An dieser Stelle können die Einstellungen des AUIK verändert werden.<br>"
                +"Einige Einstellungen werden erst nach einem Neustart des AUIK wirksam (172.20.70.24)</body></html>");

        PanelBuilder builder = new PanelBuilder();
        builder.setAnchor(GridBagConstraints.NORTHWEST);
        builder.setFill(true, true);
        builder.setBorder(new EmptyBorder(5, 5, 5, 5));
        builder.setInsets(0, 0, 10, 0);
        builder.setWeight(1, 0);
        builder.addComponent(titel, true);
        builder.setWeight(1, 1);
        builder.addComponent(tabellenScroller, true);
        button3.setVisible(false);

        return builder.getPanel();
    }

    @Override
    protected void fillForm() {

    }

    @Override
    protected boolean canSave() {
        return true;
    }

    /**
     * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
     */
    @Override
    protected boolean doSave() {
        if(einstellungenTabelle.getCellEditor() != null){
            einstellungenTabelle.getCellEditor().stopCellEditing();
        }
        SettingsManager.setInstance(_instance);
        if(SettingsManager.setInstance(_instance)){
            saved = true;
        }
        return saved;
        
    }

    @Override
    protected void doApply() {
        // TODO Auto-generated method stub

    }

    private JTable getEinstellungenTabelle() {
        if (this.einstellungenTabelle == null) {
            this.einstellungenTabelle = new SelectTable();

            Action submitAction = new AbstractAction("Einstellungen") {
                private static final long serialVersionUID = -6645922378885851686L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    doOk();
                }
            };
            submitAction.putValue(Action.ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));

            this.einstellungenTabelle.getInputMap().put(
                    (KeyStroke) submitAction.getValue(Action.ACCELERATOR_KEY),
                    submitAction.getValue(Action.NAME));
            this.einstellungenTabelle.getActionMap().put(
                    submitAction.getValue(Action.NAME), submitAction);

            this.einstellungenTabelle.addFocusListener(TableFocusListener
                    .getInstance());
            this.einstellungenTabelle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        // TODO: Check this: Nothing happens here
                        // Point origin = e.getPoint();
                        // int row = ergebnisTabelle.rowAtPoint(origin);
                    }
                }
            });

        }

        return this.einstellungenTabelle;
    }

    private EinstellungenModel getEinstellungenModel() {
        this.einstModel = new EinstellungenModel(SettingsManager.getInstance());
        return this.einstModel;
    }

}