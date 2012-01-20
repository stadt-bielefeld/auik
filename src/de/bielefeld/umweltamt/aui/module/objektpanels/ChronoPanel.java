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
 * $Id: ChronoPanel.java,v 1.1.2.1 2010-11-23 10:25:57 u633d Exp $
 *
 * Erstellt am 07.10.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2010/03/08 08:49:08  u633d
 * basis_chrono plus sachbearbeiter
 *
 * Revision 1.6  2010/03/05 06:02:47  u633d
 * Sachbearbeiter-Chrono
 *
 * Revision 1.5  2010/02/25 13:09:08  u633d
 * basis_chrono plus sachbearbeiter
 *
 * Revision 1.4  2010/02/23 13:27:01  u633d
 * basis_chrono plus sachbearbeiter
 *
 * Revision 1.3  2010/02/23 12:45:14  u633d
 * Sachbearbeiter
 *
 * Revision 1.2  2009/03/24 12:35:22  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2006/09/25 12:27:50  u633d
 * Wäscherei funzt
 *
 * Revision 1.4  2006/09/11 06:40:51  u633d
 * Objektchronologie PDF ist erstellbar
 *
 * Revision 1.3  2006/05/30 12:15:00  u633d
 * kleine Ergaenzungen
 *
 * Revision 1.2  2006/05/23 05:29:42  u633d
 * Objektchronologie für alle Objekte verfügbar gemacht
 *
 * Revision 1.1  2005/10/13 13:00:27  u633d
 * Version vom 13.10.
 *
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;



import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ReportManager;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektchrono;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Das "Objekt-Chronologie"-Panel des Objekt-Bearbeiten-Moduls.
 * @author Gerd Genuit
 */
public class ChronoPanel extends JPanel {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    private static final long serialVersionUID = 5763325969928267241L;
	private String name;
    private BasisObjektBearbeiten hauptModul;

    private ChronoModel chronoModel;

    private Action chronoItemLoeschAction;
    private Action chronoSaveAction;

    private JPopupMenu chronoPopup;

    private Integer objektid;
    private String betreiber;
    private String art;
    private String standort;

    private JTable chronoTable;
    private JButton saveButton;
    private JButton reportListeButton;

    /**
     * Erzeugt das Vaws-Panel für das BasisObjektBearbeiten-Modul.
     * @param hauptModul Das BasisObjektBearbeiten-Hauptmodul.
     */
    public ChronoPanel(BasisObjektBearbeiten hauptModul) {
        name = "Chronologie";
        this.hauptModul = hauptModul;
        log.debug(hauptModul.getObjekt().toString());
        chronoModel = new ChronoModel();


        reportListeButton = new JButton("PDF-Liste generieren");
        reportListeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReportListe();
            }
        });

        JScrollPane chronoScroller = new JScrollPane(getChronoTable());

        FormLayout layout = new FormLayout(
                "pref 3dlu, pref, 3dlu, pref:g",
                "f:100dlu:g, 3dlu, pref"
        );
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();

        builder.append(chronoScroller, 5);
        builder.nextLine(2);
        builder.append(reportListeButton, getSaveButton());
    }
    public class ChronoModel extends EditableListTableModel {
		private static final long serialVersionUID = 1268693292182383330L;
		private BasisObjekt obj;

        /**
         * Erzeugt ein einfaches TableModel für die Chronologie.
         * @param obj
         */
        public ChronoModel() {
            super(new String[]{
                    "Datum",
                    "Sachbearbeiter",
                    "Sachverhalt",

            },
            false, true);
        }


        /**
         * Setzt das Basis-Objekt und aktualisiert die Tabelle.
         * @param obj Das Basis-Objekt
         */
        public void setBasisObjekt(BasisObjekt obj) {
            this.obj = obj;

            if (obj != null) {
                setList(BasisObjektchrono.getChronoByObjekt(this.obj));
                fireTableDataChanged();
            }
        }

        public void editObject(Object objectAtRow, int columnIndex, Object newValue) {
            BasisObjektchrono chrono = (BasisObjektchrono) objectAtRow;
            String tmp = "";
            if (newValue instanceof String) {
                tmp = (String) newValue;

            }
            DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

            switch (columnIndex) {
            case 0:
                try {
                    Date tmpDate = format.parse(tmp);
                    chrono.setDatum(tmpDate);
                } catch (ParseException e) {
                    //.changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!", HauptFrame.ERROR_COLOR);
                }
                break;
            case 1:
                // Auf 10 Zeichen kürzen, da die Datenbank-Spalte nur 255 Zeichen breit ist
                if (tmp.length() > 10) {
                    tmp = tmp.substring(0,10);
                }
                chrono.setSachbearbeiter(tmp);
                break;
            case 2:
                // Auf 255 Zeichen kürzen, da die Datenbank-Spalte nur 255 Zeichen breit ist
                if (tmp.length() > 255) {
                    tmp = tmp.substring(0,255);
                }

                chrono.setSachverhalt(tmp);
                break;
            default:
                break;
            }
        }

        public Object newObject() {
            BasisObjektchrono chr = new BasisObjektchrono();
            chr.setBasisObjekt(hauptModul.getObjekt());
            chr.setDatum(new Date());
            return chr;
        }

        public boolean objectRemoved(Object objectAtRow) {
            BasisObjektchrono removedchr = (BasisObjektchrono) objectAtRow;
            boolean removed;

            if (removedchr.getId() != null) {
                removed = BasisObjektchrono.removeObjektChrono(removedchr);
            } else {
                removed = true;
            }

            return removed;
        }

        /* (non-Javadoc)
         * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
         */
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            BasisObjektchrono oc = (BasisObjektchrono) objectAtRow;
            Object tmp;

            switch (columnIndex) {
            // Datum:
            case 0:
                tmp = AuikUtils.getStringFromDate(oc.getDatum());
                break;
            // Sachverhalt:
            case 1:
                tmp = oc.getSachbearbeiter();
                break;
        // Sachbearbeiter
            case 2:
                tmp = oc.getSachverhalt();
                break;
            // Andere Spalten sollten nicht vorkommen, deshalb "Fehler":
            default:
                tmp = "ERROR";
                break;
            }

            return tmp;
        }

        /**
         * Liefert einen Datensatz in einer bestimmten Zeile.
         * @param row Die Zeile der Tabelle.
         * @return Den Datensatz, der in dieser Zeile angezeigt wird.
         */
        public BasisObjektchrono getDatenSatz(int row) {
            return (BasisObjektchrono) getObjectAtRow(row);
        }

        /*
         * Leer, da kein Updaten der Liste nötig/möglich.
         * Die Liste wird direkt mittels setList "befüllt".
         */
        public void updateList() {
        	// This is intentionally left blank.
        }
    }

    /**
     * Liefert den Namen dieses Panels.
     * @return "Chronologie"
     */
    public String getName() {
        return name;
    }

    // Funktionalität:

    /**
     * Holt die Liste mit Fachdatensätzen aus der Datenbank.
     */
    public void fetchFormData() {
        chronoModel.setList(
                BasisObjektchrono.getChronoByObjekt(hauptModul.getObjekt()));
    }

    /**
     * Erneuert die Anzeige der Tabelle.
     */
    public void updateForm() {
        chronoModel.fireTableDataChanged();
    }

    /**
     * Speichert die Objekt-Chronologie-Einträge und löscht gelöschte Datensätze
     * aus der Datenbank.
     */


    public void speichernChronologie() {
        if (chronoTable.getCellEditor() != null) {
            chronoTable.getCellEditor().stopCellEditing();
        }
        List<?> chronoListe = chronoModel.getList();
        boolean sachbear = true;
        boolean gespeichert = true;
        for (int i = 0; i < chronoListe.size(); i++) {


                    BasisObjektchrono chrono = (BasisObjektchrono) chronoListe.get(i);
// Wenn ein Eintrag neu ist ( id = null) wird überprüft ob ein Sachbearbeiter angegeben ist.
// Ein Eintrag wird nur gespeichert, wenn ein Sachbearbeiter eingetragen ist.
                    if (chrono.getId() == null)
                    {
                        String sachbearbeiter = chrono.getSachbearbeiter();

                      if (sachbearbeiter == null || sachbearbeiter.length() == 0)
                      {
                           sachbear = false;
                      }
                      else
                      {
                          BasisObjektchrono.saveObjektChrono(chrono);
                          chronoModel.fireTableDataChanged();

                          if (BasisObjektchrono.saveObjektChrono(chrono) == false)
                          {
                              gespeichert = false;
                          }

                      }
                    }
                    else
                    {
                        BasisObjektchrono.saveObjektChrono(chrono);
                        chronoModel.fireTableDataChanged();

                         if (BasisObjektchrono.saveObjektChrono(chrono) == false)
                         {
                             gespeichert = false;
                         }

                    }

        }

        if (sachbear == false & gespeichert == true )
        {
            hauptModul.getFrame().showErrorMessage("Es muss ein Sachbearbeiter angegeben werden!", "Sachbearbeiter fehlt");
        }
        else if (sachbear == true & gespeichert == true )
        {
           hauptModul.getFrame().changeStatus("Speichern erfolgreich", HauptFrame.SUCCESS_COLOR);
        }
        else if (gespeichert == false)
        {
            hauptModul.getFrame().changeStatus("Chronoligie konnte nicht gespeichert werden", HauptFrame.ERROR_COLOR);
        }
    }


    public void showReportListe() {
        objektid = hauptModul.getObjekt().getObjektid();
        betreiber = hauptModul.getObjekt().getBasisBetreiber().toString();
        standort = hauptModul.getObjekt().getBasisStandort().getFormatierteStrasse();
        art = hauptModul.getObjekt().getBasisObjektarten().getObjektart();

        if (objektid != null && betreiber != null && standort != null && art != null)
        {
            log.debug("Starte Objekt-Chronologie Report für ObjektId = "
            		+ objektid);
            ReportManager.getInstance().startReportWorker("Objekt-Chronologie", objektid, betreiber, standort, art, reportListeButton);
        }
        else
        {
            log.debug("ObjektID, Betreiber, Standort oder Art == NULL");
        }
    }

    /**
     * Liefert die Action um einen Datensatz zu löschen.
     */
    private Action getChronoItemLoeschAction() {
        if (chronoItemLoeschAction == null) {
            chronoItemLoeschAction = new AbstractAction("Eintrag löschen") {
				private static final long serialVersionUID = 2467112578637006165L;

				public void actionPerformed(ActionEvent e) {
                    int row = getChronoTable().getSelectedRow();

                    // Natürlich nur, wenn wirklich eine Zeile ausgewählt ist
                    if (row != -1) {
                        chronoModel.removeRow(row);
                    }
                }
            };
            chronoItemLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_L));
            chronoItemLoeschAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return chronoItemLoeschAction;
    }

    /**
     * Liefert die Action um die ganze Chronologie zu speichern.
     */
    private Action getChronoSaveAction() {
        if (chronoSaveAction == null) {
            chronoSaveAction = new AbstractAction("Chronologie speichern") {
				private static final long serialVersionUID = 1835366305260553709L;

				public void actionPerformed(ActionEvent e) {
                    speichernChronologie();
                }
            };
            chronoSaveAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
            chronoSaveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK, false));

        }
        return chronoSaveAction;
    }

    /**
     * Zeigt ein Kontextmenü an, wenn ein entsprechendes
     * MouseEvent vorliegt.
     * @param e Das MouseEvent.
     */
    private void showChronoPopup(MouseEvent e) {
        if (chronoPopup == null) {
            chronoPopup = new JPopupMenu("Chronologie");
            JMenuItem loeschItem = new JMenuItem(getChronoItemLoeschAction());
            JMenuItem saveItem = new JMenuItem(getChronoSaveAction());

            chronoPopup.add(loeschItem);
            chronoPopup.add(saveItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = getChronoTable().rowAtPoint(origin);

            if (row != -1) {
                getChronoTable().setRowSelectionInterval(row, row);
                chronoPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    // Widget-Getter:

    private JTable getChronoTable() {
        if (chronoTable == null) {
            chronoTable = new JTable(chronoModel);
            chronoTable.getColumnModel().getColumn(0).setMaxWidth(80);
            chronoTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            chronoTable.getColumnModel().getColumn(1).setMaxWidth(100);
            chronoTable.getColumnModel().getColumn(2).setPreferredWidth(300);
            chronoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            chronoTable.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    showChronoPopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    showChronoPopup(e);
                }
            });

            chronoTable.getInputMap().put((KeyStroke)getChronoItemLoeschAction().getValue(Action.ACCELERATOR_KEY), getChronoItemLoeschAction().getValue(Action.NAME));
            chronoTable.getActionMap().put(getChronoItemLoeschAction().getValue(Action.NAME), getChronoItemLoeschAction());
        }
        return chronoTable;
    }

    private JButton getSaveButton() {
        if (saveButton == null) {
            saveButton = new JButton("Objekt-Chronologie speichern");
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    speichernChronologie();
                }
            });
        }

        return saveButton;
    }
}
