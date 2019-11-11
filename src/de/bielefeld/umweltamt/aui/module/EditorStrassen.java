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
 * $Id: KlaerschlammFaulschlammproben.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 17.02.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.6  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import org.hibernate.criterion.MatchMode;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Strassen;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.EditorStrassenModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Das Modul um die Straßentabelle zu bearbeiten.
 * 
 * @author Gerd Genuit
 */
public class EditorStrassen extends AbstractModul
{
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private JScrollPane tableScroller;
	private JTable resultTable;

	// Widgets für die Abfrage
	private JButton submitButton;

	/** Das TableModel für die Ergebnis-Tabelle */
	private EditorStrassenModel tmodel;

	private Action resultLoeschAction;
	private JPopupMenu resultPopup;

	@Override
	public Icon getIcon()
	{
		return super.getIcon("edit32.png");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	@Override
	public String getName()
	{
		return "Straßentabelle";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	@Override
	public String getIdentifier()
	{
		return "m_strasse_edit";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	@Override
	public String getCategory()
	{
		return "Editoren";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	@Override
	public JPanel getPanel()
	{
		if (panel == null)
		{
			// Die Widgets initialisieren
			submitButton = new JButton("Alle Straßen anzeigen");

			// Ein ActionListener für den Button,
			// der die eigentliche Suche auslöst:
			submitButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable())
					{
						@Override
						protected void doNonUILogic()
						{
							((EditorStrassenModel) getTableModel()).setList(
									DatabaseQuery.getStrassenlist(null, MatchMode.EXACT));
						}

						@Override
						protected void doUIUpdateLogic()
						{
							((EditorStrassenModel) getTableModel()).fireTableDataChanged();
							frame.changeStatus(+getTableModel().getRowCount() + " Objekte gefunden");
						}
					};
					worker.start();
				}
			});

			// Noch etwas Layout...
			FormLayout layout = new FormLayout("pref, 4dlu, pref:grow", "pref, 3dlu, f:150dlu:grow");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);

			CellConstraints cc = new CellConstraints();

			builder.add(submitButton, cc.xy(1, 1));
			builder.add(getTableScroller(), cc.xyw(1, 3, 3));

			panel = builder.getPanel();
			panel.setBorder(Paddings.DIALOG);
		}

		return panel;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel
	 * ()
	 */
	public ListTableModel getTableModel()
	{
		if (tmodel == null)
		{
			tmodel = new EditorStrassenModel();
		}
		return tmodel;
	}

	/**
	 * @return Eine Tabelle für die Ergebnisse der Abfrage.
	 */
	protected JTable getResultTable()
	{
		if (resultTable == null)
		{
			resultTable = new JTable(getTableModel());
            resultTable.setAutoCreateRowSorter(true);
			resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			this.resultTable
					.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			TableColumn column = null;
			// DefaultTableCellRenderer centerRenderer = new
			// DefaultTableCellRenderer();
			// centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < this.tmodel.getColumnCount(); i++)
			{
				column = this.resultTable.getColumnModel().getColumn(i);
				/*
				 * if (i == 0) { column.setMaxWidth(60);
				 * column.setPreferredWidth(column.getMaxWidth()-10); } else
				 */
				if (i == 0)
				{
					column.setMaxWidth(50);
					// column.setCellRenderer(centerRenderer);
				}
				else if (i == 1)
				{
					// column.setMaxWidth(70);
					column.setPreferredWidth(200);
				}
			}
			
			resultTable.getInputMap().put(
											(KeyStroke) getResultLoeschAction().getValue(
																							Action.ACCELERATOR_KEY),
											getResultLoeschAction().getValue(Action.NAME));
			resultTable.getActionMap().put(
											getResultLoeschAction().getValue(Action.NAME),
											getResultLoeschAction());
			resultTable
					.addMouseListener(new java.awt.event.MouseAdapter()
					{

						@Override
						public void mousePressed(MouseEvent e)
						{
							showResultPopup(e);
						}

						@Override
						public void mouseReleased(MouseEvent e)
						{
							showResultPopup(e);
						}
					});

		}
		return resultTable;
	}

	/**
	 * @return Das ScrollPane für die Ergebnis-Tabelle.
	 */
	private JScrollPane getTableScroller()
	{
		if (tableScroller == null)
		{
			tableScroller = new JScrollPane(getResultTable());
		}
		return tableScroller;
	}

	private void showResultPopup(MouseEvent e)
	{
		if (this.resultPopup == null)
		{
			this.resultPopup = new JPopupMenu("Objekt");
			JMenuItem loeschItem = new JMenuItem(getResultLoeschAction());
			this.resultPopup.add(loeschItem);
		}

		if (e.isPopupTrigger())
		{
			Point origin = e.getPoint();
			int row = this.resultTable.rowAtPoint(origin);

			if (row != -1)
			{
				this.resultTable.setRowSelectionInterval(row, row);
				this.resultPopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	private Action getResultLoeschAction()
	{
		if (this.resultLoeschAction == null)
		{
			this.resultLoeschAction = new AbstractAction("Löschen")
			{
				private static final long serialVersionUID = 5285618973743780113L;

				@Override
				public void actionPerformed(ActionEvent e)
				{
					int row = getResultTable().getSelectedRow();
					if (row != -1 && getResultTable().getEditingRow() == -1)
					{
						Strassen strasse = EditorStrassen.this.tmodel
								.getRow(row);

						if (GUIManager.getInstance().showQuestion(
																	"Soll die Straße " + strasse.getStrasse()
																			+ " und alle seine Fachdaten "
																			+ "wirklich gelöscht werden?\n",
																	"Löschen bestätigen"))
						{
							if (EditorStrassen.this.tmodel
									.removeRow(row))
							{
								EditorStrassen.this.frame.changeStatus(
																		"Objekt gelöscht.",
																		HauptFrame.SUCCESS_COLOR);
								log.debug("Objekt " + strasse.getStrasse()
										+ " wurde gelöscht!");
							}
							else
							{
								EditorStrassen.this.frame.changeStatus(
																		"Konnte das Objekt nicht löschen!",
																		HauptFrame.ERROR_COLOR);
							}
						}
					}
				}
			};
			this.resultLoeschAction.putValue(Action.MNEMONIC_KEY, new Integer(
					KeyEvent.VK_L));
			this.resultLoeschAction.putValue(Action.ACCELERATOR_KEY,
												KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false));
		}

		return this.resultLoeschAction;
	}
}
