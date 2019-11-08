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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektchrono;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;
import de.bielefeld.umweltamt.aui.utils.PDFExporter;

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
	private Action openDocAction;
	private Action selectAction;

    private JPopupMenu chronoPopup;

    private Integer objektid;
    private String betreiber;
    private String art;
    private String standort;

    private JTable chronoTable;
    private JButton saveButton;
    private JButton reportListeButton;
    private JButton allButton;

    /**
     * Erzeugt das Vaws-Panel für das BasisObjektBearbeiten-Modul.
     * @param hauptModul Das BasisObjektBearbeiten-Hauptmodul.
     */
    public ChronoPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Chronologie";
        this.hauptModul = hauptModul;
        log.debug(hauptModul.getObjekt().toString());
        this.chronoModel = new ChronoModel();

        this.reportListeButton = new JButton("PDF-Liste generieren");
        this.reportListeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReportListe();
            }
        });

        JScrollPane chronoScroller = new JScrollPane(getChronoTable());

        FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu, pref, 3dlu, pref:g",
            "f:80dlu:g, 3dlu, pref");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);

        builder.append(chronoScroller, 7);
        builder.nextLine(2);
        builder.append(this.reportListeButton, getSaveButton(), getAllButton());
    }

    public class ChronoModel extends EditableListTableModel {
        private static final long serialVersionUID = 1268693292182383330L;
        private Objekt obj;

        /**
         * Erzeugt ein einfaches TableModel für die Chronologie.
         * @param obj
         */
        public ChronoModel() {
            super(new String[] {"Datum", "Sachbearbeiter", "Sachverhalt", "Pfad"

            }, false, true);
        }

        /**
         * Setzt das Basis-Objekt und aktualisiert die Tabelle.
         * @param obj Das Basis-Objekt
         */
        public void setObjekt(Objekt obj) {
            this.obj = obj;

            if (obj != null) {
                setList(DatabaseQuery.getChronos(this.obj));
                fireTableDataChanged();
            }
        }

    	/**
    	 * Liefert das Objekt aus einer bestimmten Zeile.
    	 * 
    	 * @param rowIndex
    	 *            Die Zeile
    	 * @return Das Objekt bei rowIndex
    	 */
    	public Objektchrono getRow(int rowIndex)
    	{
    		return (Objektchrono) getObjectAtRow(rowIndex);
    	}

        @Override
        public void editObject(Object objectAtRow, int columnIndex,
            Object newValue) {
            Objektchrono chrono = (Objektchrono) objectAtRow;
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
                        // .changeStatus("Bitte geben Sie das Datum in der Form MM.TT.JJJJ ein!",
                        // HauptFrame.ERROR_COLOR);
                    }
                    break;
                case 1:
                    // Auf 10 Zeichen kürzen, da die Datenbank-Spalte nur 10
                    // Zeichen breit ist
                    if (tmp.length() > 10) {
                        tmp = tmp.substring(0, 10);
                    }
                    chrono.setSachbearbeiter(tmp);
                    break;
                case 2:
                    // Auf 255 Zeichen kürzen, da die Datenbank-Spalte nur 255
                    // Zeichen breit ist
                    if (tmp.length() > 255) {
                        tmp = tmp.substring(0, 255);
                    }

                    chrono.setSachverhalt(tmp);
                    break;
                case 3:
                    // Auf 255 Zeichen kürzen, da die Datenbank-Spalte nur 255
                    // Zeichen breit ist
                    if (tmp.length() > 255) {
                        tmp = tmp.substring(0, 255);
                    }
                    chrono.setPfad(tmp);
                    break;
                default:
                    break;
            }
        }

        @Override
        public Object newObject() {
            Objektchrono chr = new Objektchrono();
            chr.setObjekt(ChronoPanel.this.hauptModul.getObjekt());
            chr.setDatum(new Date());
            return chr;
        }

        @Override
        public boolean objectRemoved(Object objectAtRow) {
            Objektchrono removedchr = (Objektchrono) objectAtRow;
            boolean removed;

            if (removedchr.getId() != null) {
                removed = Objektchrono.delete(removedchr);
            } else {
                removed = true;
            }

            return removed;
        }

        /* (non-Javadoc)
         * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
         */
        @Override
        public Object getColumnValue(Object objectAtRow, int columnIndex) {
            Objektchrono oc = (Objektchrono) objectAtRow;
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
                // Pfad
                case 3:
                    tmp = oc.getPfad();
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
        public Objektchrono getDatenSatz(int row) {
            return (Objektchrono) getObjectAtRow(row);
        }

        /*
         * Leer, da kein Updaten der Liste nötig/möglich.
         * Die Liste wird direkt mittels setList "befüllt".
         */
        @Override
        public void updateList() {
            // This is intentionally left blank.
        }
    }

    /**
     * Liefert den Namen dieses Panels.
     * @return "Chronologie"
     */
    @Override
    public String getName() {
        return this.name;
    }

    // Funktionalität:

    /**
     * Holt die Liste mit Fachdatensätzen aus der Datenbank.
     */
    public void fetchFormData() {
        this.chronoModel.setList(
            DatabaseQuery.getChronos(this.hauptModul.getObjekt()));
    }
    /**
     * Holt all Chronologien aus der Datenbank.
     */
    public void fetchAllFormData() {
        this.chronoModel.setList(
            DatabaseQuery.getAllChronos(this.hauptModul.getObjekt()));
    }

    /**
     * Erneuert die Anzeige der Tabelle.
     */
    public void updateForm() {
        this.chronoModel.fireTableDataChanged();
    }
    
    public void clearForm() {
        // Hier füllen wir das Abscheider-TableModel mit einer leeren Liste
    	chronoModel.setList(new ArrayList<Objektchrono>());
    }
    /**
     * Speichert die Objekt-Chronologie-Einträge und löscht gelöschte Datensätze
     * aus der Datenbank.
     */

    public void speichernChronologie() {
        if (this.chronoTable.getCellEditor() != null) {
            this.chronoTable.getCellEditor().stopCellEditing();
        }
        List<?> chronoListe = this.chronoModel.getList();
        boolean sachbear = true;
        boolean gespeichert = true;
        for (int i = 0; i < chronoListe.size(); i++) {

            Objektchrono chrono = (Objektchrono) chronoListe.get(i);
// Wenn ein Eintrag neu ist ( id = null) wird überprüft ob ein Sachbearbeiter angegeben ist.
// Ein Eintrag wird nur gespeichert, wenn ein Sachbearbeiter eingetragen ist.
            if (chrono.getId() == null) {
                String sachbearbeiter = chrono.getSachbearbeiter();

                if (sachbearbeiter == null || sachbearbeiter.length() == 0) {
                    sachbear = false;
                } else {
                    gespeichert = chrono.merge();
                    this.chronoModel.fireTableDataChanged();
                }
            } else {
                gespeichert = chrono.merge();
                this.chronoModel.fireTableDataChanged();
            }

        }

        if (!sachbear && gespeichert) {
            GUIManager.getInstance().showErrorMessage(
                "Es muss ein Sachbearbeiter angegeben werden!",
                "Sachbearbeiter fehlt");
        } else if (sachbear && gespeichert) {
            this.hauptModul.getFrame().changeStatus("Speichern erfolgreich",
                HauptFrame.SUCCESS_COLOR);
        } else if (!gespeichert) {
            this.hauptModul.getFrame().changeStatus(
                "Chronologie konnte nicht gespeichert werden",
                HauptFrame.ERROR_COLOR);
        }
    }

    public void showReportListe() {
        this.objektid = this.hauptModul.getObjekt().getId();
        this.betreiber = this.hauptModul.getObjekt().getBetreiberid()
            .toString();
        this.standort = this.hauptModul.getObjekt().getStandortid()
            .toString();
        this.art = this.hauptModul.getObjekt().getObjektarten()
            .getObjektart();
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("ObjektId", objektid);
        params.put("Betreiber", betreiber);
        params.put("Standort", standort);
        params.put("Art", art);
        if (objektid != null && betreiber != null
            && standort != null && art != null) {
            try {
                File pdfFile = File.createTempFile("objekt_chronologie", ".pdf");
                pdfFile.deleteOnExit();
                PDFExporter.getInstance().exportReport(params,
                        PDFExporter.OBJEKTCHRONOLOGIE, pdfFile.getAbsolutePath());
            } catch (Exception ex) {
                GUIManager.getInstance().showErrorMessage(
                    "PDF-Liste generieren fehlgeschlagen."
                        + "\n" + ex.getLocalizedMessage(),
                    "PDF-Liste generieren fehlgeschlagen");
            }
        } else {
            log.debug("ObjektID, Betreiber, Standort oder Art == NULL");
        }
    }

    /**
     * Liefert die Action um einen Datensatz zu löschen.
     */
    private Action getChronoItemLoeschAction() {
        if (this.chronoItemLoeschAction == null) {
            this.chronoItemLoeschAction = new AbstractAction("Eintrag löschen") {
                private static final long serialVersionUID = 2467112578637006165L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getChronoTable().getSelectedRow();

                    // Natürlich nur, wenn wirklich eine Zeile ausgewählt ist
                    if (row != -1) {
                        ChronoPanel.this.chronoModel.removeRow(row);
                    }
                }
            };
            this.chronoItemLoeschAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_L));
            this.chronoItemLoeschAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.chronoItemLoeschAction;
    }

    /**
     * Liefert die Action um ein Dokument zu öffnen.
     */
    private Action getChronoItemOpenDocAction() {
        if (this.openDocAction == null) {
            this.openDocAction = new AbstractAction("Dokument öffnen") {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getChronoTable().getSelectedRow();

                    // Natürlich nur, wenn wirklich eine Zeile ausgewählt ist
                    if (row != -1) {
                        ChronoPanel.this.chronoModel.getObjectAtRow(row);
                    }
                }
            };
            this.openDocAction.putValue(Action.MNEMONIC_KEY,
                new Integer(KeyEvent.VK_L));
            this.openDocAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
        }

        return this.openDocAction;
    }

    /**
     * Liefert die Action um die ganze Chronologie zu speichern.
     */
    private Action getChronoSaveAction() {
        if (this.chronoSaveAction == null) {
            this.chronoSaveAction = new AbstractAction("Chronologie speichern") {
                private static final long serialVersionUID = 1835366305260553709L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    speichernChronologie();
                }
            };
            this.chronoSaveAction.putValue(Action.MNEMONIC_KEY, new Integer(
                KeyEvent.VK_S));
            this.chronoSaveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke
                .getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK, false));

        }
        return this.chronoSaveAction;
    }

    /**
     * Zeigt ein Kontextmenü an, wenn ein entsprechendes MouseEvent vorliegt.
     * @param e Das MouseEvent.
     */
    private void showChronoPopup(MouseEvent e) {
        if (this.chronoPopup == null) {
            this.chronoPopup = new JPopupMenu("Chronologie");
            JMenuItem loeschItem = new JMenuItem(getChronoItemLoeschAction());
            JMenuItem saveItem = new JMenuItem(getChronoSaveAction());
            JMenuItem openDocItem = new JMenuItem(getOpenDocAction());
            JMenuItem selectItem = new JMenuItem(getSelectAction());

            this.chronoPopup.add(loeschItem);
            this.chronoPopup.add(saveItem);
            this.chronoPopup.add(openDocItem);
            this.chronoPopup.add(selectItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = getChronoTable().rowAtPoint(origin);

            if (row != -1) {
                getChronoTable().setRowSelectionInterval(row, row);
                this.chronoPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    // Widget-Getter:

    private JTable getChronoTable() {
        if (this.chronoTable == null) {
            this.chronoTable = new JTable(this.chronoModel);
            this.chronoTable.getColumnModel().getColumn(0).setMaxWidth(80);
            this.chronoTable.getColumnModel().getColumn(1)
                .setPreferredWidth(100);
            this.chronoTable.getColumnModel().getColumn(1).setMaxWidth(100);
            this.chronoTable.getColumnModel().getColumn(2)
                .setPreferredWidth(300);
            this.chronoTable
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            this.chronoTable
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        showChronoPopup(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        showChronoPopup(e);
                    }
                });

            this.chronoTable.getInputMap().put(
                (KeyStroke) getChronoItemLoeschAction().getValue(
                    Action.ACCELERATOR_KEY),
                getChronoItemLoeschAction().getValue(Action.NAME));
            this.chronoTable.getActionMap().put(
                getChronoItemLoeschAction().getValue(Action.NAME),
                getChronoItemLoeschAction());
        }
        return this.chronoTable;
    }

    private JButton getSaveButton() {
        if (this.saveButton == null) {
            this.saveButton = new JButton("Objekt-Chronologie speichern");
            this.saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    speichernChronologie();
                }
            });
        }

        return this.saveButton;
    }

    private JButton getAllButton() {
        if (this.allButton == null) {
            this.allButton = new JButton("Alle Chronologieeinträge");
            this.allButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fetchAllFormData();
                    chronoModel.fireTableDataChanged();
                }
            });
        }

        return this.allButton;
    }
    
    private Action getOpenDocAction()
	{

		if (this.openDocAction == null)
		{

			this.openDocAction = new AbstractAction("Dokument öffnen")
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{


					int row = ChronoPanel.this.chronoTable
							.getSelectedRow();
					Objektchrono bchro = ChronoPanel.this.chronoModel
							.getRow(row);

					ProcessBuilder pb = new ProcessBuilder("cmd", "/C", bchro.getPfad());

					try
					{

						Process process = pb.start();
						StreamGobbler errorGobbler = new StreamGobbler(
								process.getErrorStream());
						StreamGobbler outputGobbler = new StreamGobbler(
								process.getInputStream());
						errorGobbler.start();
						outputGobbler.start();

					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			};
		}

		return this.openDocAction;
	}
    
    private Action getSelectAction()
	{

		if (this.selectAction == null)
		{

			this.selectAction = new AbstractAction("Dokument auswählen")
			{

				@Override
				  public void actionPerformed(ActionEvent e) {            
			        
					JFileChooser f = new JFileChooser();
					f.setCurrentDirectory(new File("X:\\Orga\\360\\360-3\\360-3-3\\Alle\\Standorte"));
			        f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
			        f.showOpenDialog(null);

			        System.out.println(f.getSelectedFile());
			        System.out.println(chronoTable.getSelectedRow());
			        
			        Objektchrono oc = chronoModel.getRow(chronoTable.getSelectedRow());
			        oc.setPfad(f.getSelectedFile().toString());
			        chronoModel.fireTableDataChanged();
				    			        
				}
			};
		}

		return this.selectAction;
	}


	class StreamGobbler extends Thread
	{
		InputStream is;

		// reads everything from is until empty.
		StreamGobbler(InputStream is)
		{
			this.is = is;
		}

		@Override
		public void run()
		{
			try
			{
				InputStreamReader isr = new InputStreamReader(this.is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null)
					log.debug(line);
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
}
