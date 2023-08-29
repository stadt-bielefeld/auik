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
 * $Id: Anh49Panel.java,v 1.1.2.1 2010-11-23 10:25:56 u633d Exp $
 *
 * Erstellt am 27.04.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2009/07/30 05:31:22  u633d
 * GIS, Entsorger vereinheitlicht, Objekte inaktivierbar und andere Erg�nzungen
 *
 * Revision 1.3  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/07/23 06:55:22  u633d
 * Anh49 Auswertung und Sielhautimport neu
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 * Revision 1.4  2005/06/13 14:02:16  u633z
 * - Nicht vorhandenes Fachdaten-Objekt wird besser behandelt
 *
 * Revision 1.3  2005/06/10 11:06:52  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.2  2005/06/03 11:05:05  u633z
 * - Datenbank-Zugriffe (speziell Speichern-Methoden) in Mappings verlagert.
 *
 * Revision 1.1  2005/05/30 08:35:42  u633z
 * - Aufgeräumt, mehrere neue Packages, Klassen sortiert
 *
 * Revision 1.6  2005/05/20 09:00:54  u633z
 * Anhang 49 und Anh49-Abscheidetail-Tabs verbessert und verschönert
 *
 * Revision 1.5  2005/05/19 15:37:51  u633d
 * Abscheiderdetails-Zwischenstand
 *
 * Revision 1.4  2005/05/19 12:53:44  u633d
 * Anhang 49 neue Mapings
 *
 * Revision 1.3  2005/05/17 07:53:43  u633d
 * Anhang 49 Ergänzungen
 *
 * Revision 1.2  2005/05/12 06:39:18  u633z
 * JavaDoc Kommentar verbessert, Header angepasst
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.editors.AbscheiderEditor;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Das "Anhang 49"-Tab des BasisObjektBearbeiten-Moduls.
 * @author Gerd Genuit
 */
public class Anh49Panel extends AbstractAnhangPanel {
    private static final long serialVersionUID = 2262140075740338093L;

    private class Anh49AbscheiderModel extends ListTableModel {
        private static final long serialVersionUID = 6154019963876247085L;
        private Anh49Fachdaten fachdaten;

        /**
         * Erzeugt ein neues Abscheider-TableModel. Dieses hat die Spalten
         * "Abscheider", "Von", "Lage" und "Bemerkung".
         */
        public Anh49AbscheiderModel() {
            super(new String[] {"Abscheider", "Von", "Lage", "Nenngröße", "Hersteller", "Bemerkung"},
                false, true);
        }

        /**
         * Setzt das Fachdatenobjekt, nach dessen Abscheider-Details gesucht
         * werden soll.
         * @param fachdaten Das Anhang49-Fachdatenobjekt
         */
        public void setFachdaten(Anh49Fachdaten fachdaten) {
            this.fachdaten = fachdaten;
            updateList();
        }

        @Override
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Anh49Abscheiderdetails details = (Anh49Abscheiderdetails) objectAtRow;

            Object tmp;

            switch (columnIndex) {
                case 0:
                    tmp = details.getAbscheidernr();
                    break;
                case 1:
                    tmp = details.getVon();
                    break;
                case 2:
                    tmp = details.getLage();
                    break;
                case 3:
                    tmp = details.getNenngroesse();
                    break;
                case 4:
                    tmp = details.getHersteller();
                    break;
                case 5:
                    tmp = details.getBemerkung();
                    break;
                default:
                    tmp = null;
            }

            return tmp;
        }

        @Override
        public boolean objectRemoved(Object objectAtRow) {
            Anh49Abscheiderdetails removedAbsch = (Anh49Abscheiderdetails) objectAtRow;
            boolean removed;

            if (removedAbsch.getId() != null) {
                removed = Anh49Abscheiderdetails.delete(removedAbsch);
            } else {
                removed = true;
            }

            return removed;
        }

        @Override
        public void updateList() {
            if (fachdaten != null) {
                setList(DatabaseQuery.getAbscheiderDetails(fachdaten));
            }
            fireTableDataChanged();
        }

        public Anh49Abscheiderdetails getRow(int rowIndex) {
            return (Anh49Abscheiderdetails) getObjectAtRow(rowIndex);
        }
    }
    private Anfallstelle anfallstelle;
    /* Note: As these strings are used as keys in the underlying HashMap,     *
     * they should be unique.                                                 */
    /* Widgets - left */
    private final String ANSPRECHPARTNER = "Ansprechpartner:";
    private final String SACHKUNDE_LFA = "Sachkunde LFA:";
    private final String ANALYSEMONAT = "Analysemonat:";
    private final String BEMERKUNGEN = "Bemerkungen";
    /* Widgets - right */
    private final String GENEHMIGUNGSDATUM = "Genehmigungsdatum:";
    private final String AENDERUNGSGENEHMIGUNGSDATUM = "Änderungsgen.-datum:";
    private final String ABGEMELDET = "abgemeldet";
    private final String ABWASSERFREI = "abwasserfrei";
    private final String E_SATZUNG = "E-Satzung";
    private final String SICHERHEITSABSCHEIDER = "Sicherheitsabscheider";
    private final String WIEDERVORLAGEDATUM = "Wiedervorlagedatum:";
    /* Widgets - bottom */
    private final String SPEICHERN = "Speichern";

    private JButton saveAnh49Button = null;

    // Daten
    private Anh49Fachdaten fachdaten = null;
    private Anh49AbscheiderModel abscheiderModel;

    private JTable abscheiderTabelle;
    private Action abscheiderLoeschAction;
    private Action abscheiderNeuAction;
    private JPopupMenu abscheiderPopup;



    public Anh49Panel(BasisObjektBearbeiten hauptModul, Anfallstelle anfallstelle) {
    	this(hauptModul);
    	this.anfallstelle = anfallstelle;
    }

    public Anh49Panel(BasisObjektBearbeiten hauptModul) {
        super("Abscheider", hauptModul);
        
        abscheiderModel = new Anh49AbscheiderModel();
        abscheiderModel.setFachdaten(fachdaten);

        TableFocusListener tfl = TableFocusListener.getInstance();
        getAbscheiderTabelle().addFocusListener(tfl);
        
        JScrollPane abscheiderScroller = new JScrollPane(
                getAbscheiderTabelle(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            abscheiderScroller.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    showAbscheiderPopup(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    showAbscheiderPopup(e);
                }
            });

        /* Add components to the "Anhang" panel */
        /* Left column */
        super.addComponent(this.ANSPRECHPARTNER, new LimitedTextField(50));
        super.addComponent(this.SACHKUNDE_LFA, new LimitedTextField(50));
        super.addComponent(this.ANALYSEMONAT, new LimitedTextField(50));
        JTextArea textArea = new LimitedTextArea(150);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        super.addComponent(this.BEMERKUNGEN, textArea);

        /* Right column */
        super.addComponent(this.GENEHMIGUNGSDATUM, new TextFieldDateChooser());
        super.addComponent(this.AENDERUNGSGENEHMIGUNGSDATUM,
            new TextFieldDateChooser());
        super.addComponent(this.ABGEMELDET, new JCheckBox(this.ABGEMELDET));
        super.addComponent(this.ABWASSERFREI, new JCheckBox(this.ABWASSERFREI));
        super.addComponent(this.E_SATZUNG, new JCheckBox(this.E_SATZUNG));
        super.addComponent(this.SICHERHEITSABSCHEIDER, new JCheckBox(this.SICHERHEITSABSCHEIDER));
        super.addComponent(this.WIEDERVORLAGEDATUM, new TextFieldDateChooser());
        super.addComponent(this.SPEICHERN, getSaveAnh49Button());

        FormLayout layout = new FormLayout(
            "pref, 5dlu, 100dlu, 10dlu, pref, 5dlu, 100dlu", // Spalten
            "pref, " + // Bearbeitung | Erfassung
                "3dlu, " + //
                "pref, " + // Sachbearbeiter | Genehmigungsdatum
                "3dlu, " + //
                "pref, " + // Ansprechpartner | Änderungsgenehmigungsdatum
                "3dlu, " + //
                "pref, " + // Sachkunde LFA | abgemeldet
                "3dlu, " + //
                "pref, " + // Analyse | abwasserfrei
                "3dlu, " + //
                "pref, " + // Analysemonat | E-Satzung
                "3dlu, " + //
                "pref, " + // Bemerkung | Kontrolle
                "3dlu, " + //
                "pref, " + // Bemerkung | Wiedervorlage
                "30dlu, " + //
                "3dlu, " + //
                "pref, " + // Abscheiderdetails
                "5dlu, " + //
                "fill:50dlu, " + // Tabelle
                "5dlu, " + //
                "pref"); // Buttons

        PanelBuilder builder = new PanelBuilder(layout, this);
        CellConstraints cc = new CellConstraints();

        /* Left column */
        Integer row = 1;
        Integer labelCol = 1;
        Integer fieldCol = 3;
        Integer colWidth = 3;
        Integer cols = 7;

        builder.addSeparator("Bearbeitung", cc.xyw(labelCol, row, colWidth));
        row += 2;
        builder.addLabel(this.ANSPRECHPARTNER, cc.xy(labelCol, row));
        builder.add(super.getComponent(this.ANSPRECHPARTNER),
            cc.xy(fieldCol, row));
        row += 2;
        builder.addLabel(this.SACHKUNDE_LFA, cc.xy(labelCol, row));
        builder.add(super.getComponent(this.SACHKUNDE_LFA),
            cc.xy(fieldCol, row));
        row += 2;

        builder.addSeparator("Analyse", cc.xyw(labelCol, row, colWidth));
        row += 2;
        builder.addLabel(this.ANALYSEMONAT, cc.xy(labelCol, row));
        builder
            .add(super.getComponent(this.ANALYSEMONAT), cc.xy(fieldCol, row));
        row += 2;

        builder.addSeparator("Bemerkungen", cc.xyw(labelCol, row, cols));
        row += 2;
        builder.add(new JScrollPane(super.getComponent(this.BEMERKUNGEN),
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), cc.xywh(labelCol,
            row, cols, 4));
        row += 5;

        builder.addSeparator("Abscheiderdetails", cc.xyw(labelCol, row, cols));
        row += 2;
        
        builder.add(abscheiderScroller, cc.xyw(labelCol, row, cols));
        row += 2;

        /* Bottom */
        builder.add(ComponentFactory.buildRightAlignedBar(
            getSaveAnh49Button()), cc.xyw(labelCol,
            row, cols));

        /* Right column */
        row = 1;
        labelCol = 5;
        fieldCol = 7;

        builder.appendRow("fill:100dlu");

        builder.addSeparator("Erfassung", cc.xyw(labelCol, row, colWidth));
        row += 2;
        builder.add(super.getComponent(this.ABGEMELDET),
            cc.xy(labelCol, row, "l,d"));
        builder.add(super.getComponent(this.E_SATZUNG),
            cc.xy(fieldCol, row, "l,d"));
        row += 2;
        builder.add(super.getComponent(this.ABWASSERFREI),
            cc.xy(labelCol, row, "l,d"));
        builder.add(super.getComponent(this.SICHERHEITSABSCHEIDER),
                cc.xy(fieldCol, row, "l,d"));
        row += 2;

       // builder.addSeparator("Wiedervorlage", cc.xyw(labelCol, row, colWidth));
       // row += 2;
       // builder.addLabel(this.WIEDERVORLAGEDATUM, cc.xy(labelCol, row));
       // builder.add(super.getComponent(this.WIEDERVORLAGEDATUM),
       //    cc.xy(fieldCol, row));

        builder.nextLine();
    }

    private Action getAbscheiderLoeschAction() {
        if (abscheiderLoeschAction == null) {
            abscheiderLoeschAction = new AbstractAction("Löschen") {
                private static final long serialVersionUID = -4757595254932715764L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getAbscheiderTabelle().getSelectedRow();
                    if (row != -1
                        && getAbscheiderTabelle().getEditingRow() == -1) {
                        Anh49Abscheiderdetails abscheider = abscheiderModel
                            .getRow(row);

                        if (GUIManager
                            .getInstance()
                            .showQuestion(
                                "Soll der Abscheider "
                                    + abscheider
                                    + " wirklich inkl. aller Detailinformationen gelöscht werden?",
                                "Löschen bestätigen")) {
                            abscheiderModel.removeRow(row);
                            log.debug("Abscheider " + abscheider.getLage()
                                + " wurde gelöscht!");
                        } else {
                            log.debug("Löschen von " + abscheider.getLage()
                                + " wurde abgebrochen!");
                        }
                    }
                }
            };
            abscheiderLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_L));
            abscheiderLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return abscheiderLoeschAction;
    }

    private Action getAbscheiderNeuAction() {
        if (abscheiderNeuAction == null) {
            abscheiderNeuAction = new AbstractAction("Neuer Abscheider") {
                private static final long serialVersionUID = 4388335905488653435L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    Anh49Abscheiderdetails neuerAbscheider = new Anh49Abscheiderdetails();
                    neuerAbscheider.setAnh49Fachdaten(fachdaten);
                    editAbscheider(neuerAbscheider);
                }
            };
            abscheiderNeuAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_N));
            // abscheiderNeuAction.putValue(Action.ACCELERATOR_KEY,
            // KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return abscheiderNeuAction;
    }

    /**
     * öffnet einen Dialog um einen Abscheider-Datensatz zu bearbeiten.
     * @param absch Der Abscheider
     */
    public void editAbscheider(Anh49Abscheiderdetails absch) {
        
    	if (fachdaten.getId() == null) {
			JOptionPane.showMessageDialog(hauptModul.getFrame(),
				    "Bitte zuerst das Anhangobjekt speichern",
				    "Warnung",
				    JOptionPane.WARNING_MESSAGE);
		} else {
			
	        AbscheiderEditor editDialog = new AbscheiderEditor(absch,
	                hauptModul.getFrame());

	            editDialog.setLocationRelativeTo(hauptModul.getFrame());

	            editDialog.setVisible(true);

	            if (editDialog.wasSaved() && (editDialog.getDetails() != null)) {
	                // Die Liste updaten, damit unsere Änderungen auch angezeigt werden
	                abscheiderModel.updateList();

	                // Den bearbeiteten Abscheider wieder in der Tabelle auswählen
	                Anh49Abscheiderdetails details = editDialog.getDetails();
	                int row = abscheiderModel.getList().indexOf(details);
	                if (row != -1) {
	                    getAbscheiderTabelle().setRowSelectionInterval(row, row);
	                    getAbscheiderTabelle().scrollRectToVisible(
	                        getAbscheiderTabelle().getCellRect(row, 0, true));
	                }
	            }
			}


    }

    private void showAbscheiderPopup(MouseEvent e) {
        if (abscheiderPopup == null) {
            abscheiderPopup = new JPopupMenu("Abscheider");
            JMenuItem loeschItem = new JMenuItem(getAbscheiderLoeschAction());
            abscheiderPopup.add(loeschItem);
            JMenuItem neuItem = new JMenuItem(getAbscheiderNeuAction());
            abscheiderPopup.add(neuItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = abscheiderTabelle.rowAtPoint(origin);

            // Löschen ist natürlich nur möglich,
            // wenn wirklich eine Zeile ausgewählt ist:
            if (row != -1) {
                getAbscheiderLoeschAction().setEnabled(true);
                abscheiderTabelle.setRowSelectionInterval(row, row);
            } else {
                getAbscheiderLoeschAction().setEnabled(false);
            }

            // Das Menü zeigen wir aber immer an, zum neu Anlegen eines
            // Abscheiders
            abscheiderPopup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private JTable getAbscheiderTabelle() {
        if (abscheiderTabelle == null) {
            abscheiderTabelle = new JTable(abscheiderModel);

            // Wenn die Spaltengröße sich verändert, verändert sich nur die
            // Nachbarspalte mit
            abscheiderTabelle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

            // Es darf immer nur eine Zeile ausgewählt werden
            abscheiderTabelle
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            abscheiderTabelle.setColumnSelectionAllowed(false);

            // Mal als Beispiel, wie der Text in einigen Spalten zentriert
            // werden kann
            DefaultTableCellRenderer centeredRenderer = new DefaultTableCellRenderer();
            centeredRenderer
                .setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            // Die Tabellen-Spalten werden angepasst
            TableColumn column = null;
            for (int i = 0; i < abscheiderTabelle.getColumnCount(); i++) {
                column = abscheiderTabelle.getColumnModel().getColumn(i);
                if (i == 0) {
                    // Die erste Spalte soll zentriert sein...
                    column.setCellRenderer(centeredRenderer);

                    column.setMaxWidth(100);
                    column.setPreferredWidth(75);
                } else if (i == 1) {
                    // ... die zweite auch
                    column.setCellRenderer(centeredRenderer);

                    column.setMaxWidth(80);
                    column.setPreferredWidth(60);
                }
            }

            // MouseListener für Doppelklick und Rechtsklick
            abscheiderTabelle
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                            Point origin = e.getPoint();
                            int row = abscheiderTabelle.rowAtPoint(origin);

                            if (row != -1) {
                                Anh49Abscheiderdetails absch = (Anh49Abscheiderdetails) abscheiderModel
                                    .getObjectAtRow(row);
                                log.debug("(Anh49DetailsPanel.abscheiderTabelle) "
                                    + "Doppelklick auf: " + absch);
                                editAbscheider(absch);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        showAbscheiderPopup(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showAbscheiderPopup(e);
                    }
                });

            abscheiderTabelle.getInputMap().put(
                (KeyStroke) getAbscheiderLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getAbscheiderLoeschAction().getValue(Action.NAME));
            abscheiderTabelle.getActionMap().put(
                getAbscheiderLoeschAction().getValue(Action.NAME),
                getAbscheiderLoeschAction());
        }
        return abscheiderTabelle;
    }

    public void fetchFormData() {
    	Set<Anfallstelle> list = this.hauptModul.getObjekt().getAnfallstelles();
		this.fachdaten = Anh49Fachdaten.findByAnfallstelleId(
				list.iterator().next().getId());
        log.debug("Anhang 40 Objekt aus DB geholt: ID" + this.fachdaten);
    }

    public void updateForm() {
        if (this.fachdaten != null) {
            super.setComponentValue(this.ANSPRECHPARTNER,
                this.fachdaten.getAnsprechpartnerIn());
            super.setComponentValue(this.SACHKUNDE_LFA,
                this.fachdaten.getSachkundelfa());
            super.setComponentValue(this.ANALYSEMONAT,
                this.fachdaten.getAnalysemonat());
            super.setComponentValue(this.BEMERKUNGEN,
                this.fachdaten.getBemerkungen());

            super.setComponentValue(this.GENEHMIGUNGSDATUM,
                this.fachdaten.getGenehmigung());
            super.setComponentValue(this.AENDERUNGSGENEHMIGUNGSDATUM,
                this.fachdaten.getAenderungsgenehmigung());
            super.setComponentValue(this.ABGEMELDET,
                this.fachdaten.getAbgemeldet());
            super.setComponentValue(this.ABWASSERFREI,
                this.fachdaten.getAbwasserfrei());
            super.setComponentValue(this.E_SATZUNG,
                this.fachdaten.getESatzung());
            super.setComponentValue(this.SICHERHEITSABSCHEIDER,
                    this.fachdaten.getSicherheitsabscheider());
            super.setComponentValue(this.WIEDERVORLAGEDATUM,
                this.fachdaten.getWiedervorlage());

            abscheiderModel.setFachdaten(fachdaten);

        } else {
            enableAll(false);
            this.hauptModul.getFrame().changeStatus(
                "FEHLER: Kein Anhang 49 Objekt gefunden!",
                HauptFrame.ERROR_COLOR);
        }
        setDirty(false);
    }

    public void updateForm(Anfallstelle anfallstelle) {
    	this.fachdaten = anfallstelle.getAnh49Fachdatens().iterator().next();
        if (this.fachdaten != null) {
            super.setComponentValue(this.ANSPRECHPARTNER,
                this.fachdaten.getAnsprechpartnerIn());
            super.setComponentValue(this.SACHKUNDE_LFA,
                this.fachdaten.getSachkundelfa());
            super.setComponentValue(this.ANALYSEMONAT,
                this.fachdaten.getAnalysemonat());
            super.setComponentValue(this.BEMERKUNGEN,
                this.fachdaten.getBemerkungen());

            super.setComponentValue(this.GENEHMIGUNGSDATUM,
                this.fachdaten.getGenehmigung());
            super.setComponentValue(this.AENDERUNGSGENEHMIGUNGSDATUM,
                this.fachdaten.getAenderungsgenehmigung());
            super.setComponentValue(this.ABGEMELDET,
                this.fachdaten.getAbgemeldet());
            super.setComponentValue(this.ABWASSERFREI,
                this.fachdaten.getAbwasserfrei());
            super.setComponentValue(this.E_SATZUNG,
                this.fachdaten.getESatzung());
            super.setComponentValue(this.SICHERHEITSABSCHEIDER,
                    this.fachdaten.getSicherheitsabscheider());
            super.setComponentValue(this.WIEDERVORLAGEDATUM,
                this.fachdaten.getWiedervorlage());

            abscheiderModel.setFachdaten(fachdaten);

        } else {
            enableAll(false);
            this.hauptModul.getFrame().changeStatus(
                "FEHLER: Kein Anhang 49 Objekt gefunden!",
                HauptFrame.ERROR_COLOR);
        }
        setDirty(false);
    }

	public void clearForm() {
		super.clearAllComponents();
		abscheiderModel.setList(new ArrayList<Anh49Abscheiderdetails>());
	}

    public void enableAll(boolean enabled) {
        // Wenn das Fachdaten-Objekt null ist,
        // können die Elemente nicht wieder aktiviert werden:
        if (!(enabled && (this.fachdaten == null))) {
            super.setAllComponentsEnabled(enabled);
        }
    }

    public Anh49Fachdaten getFachdaten() {
        return this.fachdaten;
    }

    protected boolean doSavePanelData() {
        boolean success;

        this.fachdaten.setAnsprechpartnerIn((String) super
            .getComponentValue(this.ANSPRECHPARTNER));
        this.fachdaten.setSachkundelfa((String) super
            .getComponentValue(this.SACHKUNDE_LFA));
        this.fachdaten.setAnalysemonat((String) super
            .getComponentValue(this.ANALYSEMONAT));
        this.fachdaten.setBemerkungen((String) super
            .getComponentValue(this.BEMERKUNGEN));

        this.fachdaten.setGenehmigung((Date) super
            .getComponentValue(this.GENEHMIGUNGSDATUM));
        this.fachdaten.setAenderungsgenehmigung((Date) super
            .getComponentValue(this.AENDERUNGSGENEHMIGUNGSDATUM));
        this.fachdaten.setAbgemeldet((Boolean) super
            .getComponentValue(this.ABGEMELDET));
        this.fachdaten.setAbwasserfrei((Boolean) super
            .getComponentValue(this.ABWASSERFREI));
        this.fachdaten.setESatzung((Boolean) super
            .getComponentValue(this.E_SATZUNG));
        this.fachdaten.setWiedervorlage((Date) super
            .getComponentValue(this.WIEDERVORLAGEDATUM));
        this.fachdaten.setSicherheitsabscheider((Boolean) super
                .getComponentValue(this.SICHERHEITSABSCHEIDER));
        this.fachdaten.getAnfallstelle().setObjekt(this.hauptModul.getObjekt());

        success = this.fachdaten.merge();

        if (!success) {
            this.log.debug("Anh49 Objekt " + this.fachdaten
                + " konnte nicht gespeichert werden!");
        }

        return success;
    }

    public void completeObjekt(Anfallstelle anfallstelle) {
        if (anfallstelle.getAnh49Fachdatens().size() == 0) {
            // Neues Anhang49-Objekt erzeugen
            this.fachdaten = new Anh49Fachdaten();

            // Anfallstelle setzen
            this.anfallstelle = anfallstelle;
            this.fachdaten.setAnfallstelle(anfallstelle);
            this.fachdaten.merge();

        }
    }

    public void completeObjekt() {
        if (this.hauptModul.isNew() || this.fachdaten == null) {
            // Neues Anhang49-Objekt erzeugen
            this.fachdaten = new Anh49Fachdaten();


        }
    }

    private JButton getSaveAnh49Button() {
        if (this.saveAnh49Button == null) {
            this.saveAnh49Button = new JButton("Speichern");

            this.saveAnh49Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    enableAll(false);
                    if (hauptModul.saveAllTabs()) {
                        Anh49Panel.this.hauptModul.getFrame().changeStatus(
                            "Anhang 49-Objekt erfolgreich gespeichert.",
                            HauptFrame.SUCCESS_COLOR);
                    } else {
                        Anh49Panel.this.hauptModul.getFrame().changeStatus(
                            "Fehler beim Speichern des Objekts!",
                            HauptFrame.ERROR_COLOR);
                    }

                    Anh49Panel.this.hauptModul.fillForm();
                }
            });
        }
        return this.saveAnh49Button;
    }
}
