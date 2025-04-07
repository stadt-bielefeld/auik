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
 * $Id: Anh52Panel.java,v 1.1.2.1 2010-11-23 10:25:57 u633d Exp $
 *
 * Erstellt am 10.08.2005 von Gerhard Genuit (u633d)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:39  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.4  2006/10/17 09:10:59  u633d
 * small changeZ!
 *
 * Revision 1.3  2006/10/17 09:09:13  u633d
 * small changeZ!
 *
 * Revision 1.2  2006/10/17 09:00:36  u633d
 * Anhang 52
 *
 * Revision 1.1  2006/10/17 07:54:29  u633d
 * Anhang 52 (Chemische Wäschereien) haben nun einen eigenen Tab.
 *
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.AfsNiederschlagswasser;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Entwaesserungsgrundstueck;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Das Panel zum Bearbeiten von Druckereien* @author u633d
 */
public class AfsNwPanel extends ObjectPanel {
	private static final long serialVersionUID = -1240254202213465142L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private String name;
	private BasisObjektBearbeiten hauptModul;
	private Anfallstelle anfallstelle;
	private Entwaesserungsgrundstueck grundstueck;

	// Widgets
	private JTable afsNwTabelle = null;
	private JButton anlegenButton = null;
	private JButton saveAfsButton = null;

	// Daten
	private AfsNwModel afsModel = new AfsNwModel();
	private JComboBox herkunftBox;
	private String[] herkunft = { "nicht definiert", "Kategorie I: Unbelastetes NW",
			"Kategorie II: Schwach belastetes NW", "Kategorie III: Stark belastetes NW" };

	@SuppressWarnings("deprecation")
	public AfsNwPanel(BasisObjektBearbeiten hauptModul) {
		name = "Afs Niederschlagswasser";
		this.hauptModul = hauptModul;

		FormLayout layout = new FormLayout("r:150dlu, 5dlu, 150dlu", // Spalten
				"pref, " + // 1
						"3dlu, " + // 2
						"pref, " + // 3
						"3dlu, " + // 4
						"pref");

		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		CellConstraints cc = new CellConstraints();

		builder.addSeparator("Anfallstelle Niederschlagswasser", cc.xyw(1, 1, 3));
		JScrollPane tabellenScroller = new JScrollPane(getAfsNwTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		builder.add(tabellenScroller, cc.xyw(1, 3, 3));

	}

	public class AfsNwModel extends EditableListTableModel {

		private Entwaesserungsgrundstueck grundstueck;
		private Anfallstelle anfallstelle;

		/**
		 * Erzeugt ein einfaches TableModel für die Chronologie.
		 * @param obj
		 */
		public AfsNwModel() {
			super(new String[] { "Bezeichnung", "Befestigte Fläche", "Herkunftsbereich", "Abflussmenge"

			}, false, true);
		}

		/**
		 * Setzt die Anfallstelle und aktualisiert die Tabelle.
		 * @param obj Die Anfallstelle
		 */
		public void setAnfallstelle(Anfallstelle anfallstelle) {
			this.anfallstelle = anfallstelle;

			if (anfallstelle != null) {
				setList(DatabaseQuery.getAfsNwList(this.anfallstelle));
				fireTableDataChanged();
			}
		}

		/**
		 * Setzt das Grundstück und aktualisiert die Tabelle.
		 * @param obj Das Grundstück
		 */
		public void setGrundstueck(Entwaesserungsgrundstueck grundstueck) {
			this.grundstueck = grundstueck;

			if (grundstueck != null) {
				setList(DatabaseQuery.getAfsNwList(this.grundstueck));
				fireTableDataChanged();
			}
		}

		/**
		 * Liefert das Objekt aus einer bestimmten Zeile.
		 *
		 * @param rowIndex Die Zeile
		 * @return Das Objekt bei rowIndex
		 */
		public AfsNiederschlagswasser getRow(int rowIndex) {
			return (AfsNiederschlagswasser) getObjectAtRow(rowIndex);
		}

		@Override
		public void editObject(Object objectAtRow, int columnIndex, Object newValue) {

			AfsNiederschlagswasser tmp = (AfsNiederschlagswasser) objectAtRow;
			switch (columnIndex) {
			case 0:
				String tmpBez = (String) newValue;
				tmp.setBezeichnung(tmpBez);
				break;

			case 1:
				Integer tmpBef = Integer.valueOf((String) newValue);
				tmp.setBefFlaeche(tmpBef);
				break;

			case 2:
				if (newValue == "nicht definiert") {
					newValue = 0;
				}
				if (newValue == "Kategorie I: Unbelastetes NW") {
					newValue = 1;
				}
				if (newValue == "Kategorie II: Schwach belastetes NW") {
					newValue = 2;
				}
				if (newValue == "Kategorie III: Stark belastetes NW") {
					newValue = 3;
				}
				Integer tmpHer = (Integer) newValue;
				tmp.setNwHerBereichOpt(tmpHer);
				break;

			case 3:
				String newStringValue = newValue.toString();
				newStringValue.replaceAll(",", ".");
				BigDecimal tmpAbf = BigDecimal.valueOf(Double.valueOf((String) newStringValue));
				tmp.setAbflussmenge(tmpAbf);
				break;

			default:
				break;

			}
		}

		@Override
		public Object newObject() {
			AfsNiederschlagswasser afs = new AfsNiederschlagswasser();
			afs.setEntwaesserungsgrundstueck(grundstueck);
			setDirty(true);
			return afs;
		}

		@Override
		public boolean objectRemoved(Object objectAtRow) {
			AfsNiederschlagswasser removedcafs = (AfsNiederschlagswasser) objectAtRow;
			boolean removed;

			if (removedcafs.getNr() != null) {
				removed = AfsNiederschlagswasser.delete(removedcafs);
			} else {
				removed = true;
			}
			setDirty(true);
			return removed;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue
		 * (java.lang.Object, int)
		 */
		@Override
		public Object getColumnValue(Object objectAtRow, int columnIndex) {
			AfsNiederschlagswasser afs = (AfsNiederschlagswasser) objectAtRow;
			Object tmp;

			switch (columnIndex) {
			case 0:
				tmp = afs.getBezeichnung();
				break;
			case 1:
				tmp = afs.getBefFlaeche();
				break;
			case 2:
				tmp = "nicht definiert";
				if (afs.getNwHerBereichOpt() == null) {
					tmp = "nicht definiert";
				} else if (afs.getNwHerBereichOpt() == 1) {
					tmp = "Kategorie I: Unbelastetes NW";
				} else if (afs.getNwHerBereichOpt() == 2) {
					tmp = "Kategorie II: Schwach belastetes NW";
				} else if (afs.getNwHerBereichOpt() == 3) {
					tmp = "Kategorie III: Stark belastetes NW";
				}
				break;
			case 3:
				tmp = afs.getAbflussmenge();
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
		public AfsNiederschlagswasser getDatenSatz(int row) {
			return (AfsNiederschlagswasser) getObjectAtRow(row);
		}

		/*
		 * Leer, da kein Updaten der Liste nötig/möglich. Die Liste wird direkt mittels
		 * setList "befüllt".
		 */
		@Override
		public void updateList() {
			// This is intentionally left blank.
		}
	}

	protected boolean doSavePanelData() {
		if (this.afsNwTabelle.getCellEditor() != null) {
			this.afsNwTabelle.getCellEditor().stopCellEditing();
		}
		List<?> afsListe = this.afsModel.getList();
		boolean gespeichert = false;
		for (int i = 0; i < afsListe.size(); i++) {

			AfsNiederschlagswasser afs = (AfsNiederschlagswasser) afsListe.get(i);
			if (anfallstelle != null) {
				afs.setAnfallstelle(anfallstelle);
			}
			if (grundstueck != null) {
				afs.setEntwaesserungsgrundstueck(grundstueck);
			}
			this.hauptModul.getObjekt().merge();
			afs.merge();
			gespeichert = afs.merge();
			this.afsModel.fireTableDataChanged();

		}
		this.setDirty(false);
		this.hauptModul.getFrame().changeStatus("Speichern erfolgreich", HauptFrame.SUCCESS_COLOR);
		return true;
	}

	public void enableAll(boolean enabled) {

	}

	public void clearForm() {

	}

	public void updateForm() throws RuntimeException {
		if (this.afsNwTabelle.getCellEditor() != null) {
			// Cancel cell editing and discard changes
			this.afsNwTabelle.getCellEditor().cancelCellEditing();
		}
		// Reset dirty state
		setDirty(false);
		this.afsModel.fireTableDataChanged();
	}

	public void fetchFormData() throws RuntimeException {

		if (this.hauptModul.getObjekt().getEntwaesserungsgrundstuecks().iterator().hasNext()) {
			this.grundstueck = this.hauptModul.getObjekt().getEntwaesserungsgrundstuecks().iterator().next();
			this.afsModel.setList(DatabaseQuery.getAfsNwList(grundstueck));
		}
		if (this.hauptModul.getObjekt().getAnfallstelles().iterator().hasNext()) {
			this.anfallstelle = this.hauptModul.getObjekt().getAnfallstelles().iterator().next();
			this.afsModel.setList(DatabaseQuery.getAfsNwList(anfallstelle));
		}

	}

	public void completeObjekt() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return name;
	}

	private JButton getSaveAfsNw() {
		if (saveAfsButton == null) {
			saveAfsButton = new JButton("Speichern");

			this.saveAfsButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					hauptModul.saveAllTabs();
				}
			});
		}
		return saveAfsButton;
	}

	private JTable getAfsNwTabelle() {
		if (this.afsNwTabelle == null) {
			this.afsNwTabelle = new JTable(this.afsModel);
			this.afsNwTabelle.getColumnModel().getColumn(0).setMaxWidth(200);
			this.afsNwTabelle.getColumnModel().getColumn(1).setMaxWidth(120);
			this.afsNwTabelle.getColumnModel().getColumn(2).setMaxWidth(250);
			this.afsNwTabelle.getColumnModel().getColumn(3).setMaxWidth(120);
			this.afsNwTabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.afsNwTabelle.setColumnSelectionAllowed(false);
			this.afsNwTabelle.setRowSelectionAllowed(true);

			// Für die ComboBox bei "Herkunftsbereich"

			herkunftBox = new JComboBox(herkunft);
			herkunftBox.setEditable(false);
			herkunftBox.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					herkunftBox.showPopup();
				}
			});
			herkunftBox.setBorder(BorderFactory.createEmptyBorder());
			afsNwTabelle.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(herkunftBox));

			this.afsNwTabelle.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = AfsNwPanel.this.afsNwTabelle.rowAtPoint(origin);

						AfsNiederschlagswasser afs = AfsNwPanel.this.afsModel.getRow(row);
					}
				}
			});

			// Den KeyStroke holen, der "Enter" repräsentiert
			KeyStroke enterKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
			// Den "Enter"-KeyStroke in die InputMap der Tabelle einfügen
			this.afsNwTabelle.getInputMap().put(enterKeyStroke, "ENTER");
			// Eine neue Action fürs editieren erzeugen
			Action editAction = new AbstractAction() {
				private static final long serialVersionUID = -7537228135751378632L;

				@Override
				public void actionPerformed(ActionEvent e) {
					int row = AfsNwPanel.this.afsNwTabelle.getSelectedRow();
					// Natürlich nur editieren, wenn wirklich eine Zeile
					// ausgewählt ist
					if (row != -1) {
						AfsNiederschlagswasser afsNw = AfsNwPanel.this.afsModel.getRow(row);
					}
				}
			};
			// Diese Action dem "Enter"-KeyStroke zuweisen
			this.afsNwTabelle.getActionMap().put("ENTER", editAction);

			KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false);
			this.afsNwTabelle.getInputMap().put(deleteKeyStroke, "DEL");
			Action removeAction = new AbstractAction() {
				private static final long serialVersionUID = -4910733866626541511L;

				@Override
				public void actionPerformed(ActionEvent e) {
					int row = AfsNwPanel.this.afsNwTabelle.getSelectedRow();
					if (row != -1 && AfsNwPanel.this.afsNwTabelle.getEditingRow() == -1) {
						AfsNiederschlagswasser afs = AfsNwPanel.this.afsModel.getRow(row);
						if (GUIManager.getInstance().showQuestion(
								"Soll die Probenahme " + afs.getLfdNr() + " wirklich gelöscht werden?",
								"Löschen bestätigen")) {
							AfsNwPanel.this.afsModel.removeRow(row);
							log.debug("Probe " + afs.getLfdNr() + " wurde gelöscht!");
						}
					}
				}
			};
			this.afsNwTabelle.getActionMap().put("DEL", removeAction);
		}
		return this.afsNwTabelle;
	}
}
