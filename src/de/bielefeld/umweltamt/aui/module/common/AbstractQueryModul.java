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
 * $Id: AbstractQueryModul.java,v 1.3 2010-01-12 09:08:38 u633d Exp $
 *
 * Erstellt am 28.07.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2005/10/13 13:00:27  u633d
 * Version vom 13.10.
 *
 * Revision 1.4  2005/09/28 11:11:14  u633d
 * - Version vom 28.9.
 *
 * Revision 1.3  2005/09/14 11:25:38  u633d
 * - Version vom 14.9.
 *
 * Revision 1.2  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 * Revision 1.1  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.indeinl.BwkFachdaten;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Eine Grundlage für Module mit verschiedenen
 * Auswertungs-Abfragen.
 * @author David Klotz
 */
public abstract class AbstractQueryModul extends AbstractModul {
//    private JSplitPane contentSplit;
    private JScrollPane tableScroller;
    private JTable resultTable;

    private Action objektEditAction;
    private Action saveAction;
    private JPopupMenu resultPopup;
//    private RowSorter<?> sorter;



    /**
     * Liefert die Kategorie des Moduls.
     * Falls eine andere Kategorie als "Auswertung" gewünscht
     * ist, muss diese Methode in implementierenden Klassen
     * überschrieben werden.
     * @return "Auswertung"
     */
    @Override
    public String getCategory() {
        return "Auswertung";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
    public JPanel getPanel() {
        if (panel == null) {
            FormLayout layout = new FormLayout(
                    "100dlu:g",
                    "pref, 3dlu, f:150dlu:grow"
            );
            PanelBuilder builder = new PanelBuilder(layout);
            CellConstraints cc = new CellConstraints();

            builder.add(getQueryOptionsPanel(),    cc.xy(1,1));
            builder.add(getTableScroller(),        cc.xy(1,3));

            panel = builder.getPanel();
            panel.setBorder(Paddings.DIALOG);
        }

        return panel;
    }

    /**
     * @return Ein Panel, in dem Optionen für die Abfrage festgelegt werden können.
     */
    public abstract JPanel getQueryOptionsPanel();

    /**
     * @return Ein TableModel für die Ergebnis-Tabelle.
     */
    public abstract ListTableModel getTableModel();

    /**
     * Liefert das BasisObjekt zu einem Fachdaten-Objekt.
     * @param objectAtRow Das Fachdaten-Objekt.
     * @return Das zugehörige BasisObjekt (oder <code>null</code>, falls keins existiert).
     */
    protected Objekt getBasisObjektFromFachdaten(Object fachdaten) {
        Objekt tmp = null;

        // Die "getBasisObjekt" Methode des jeweiligen
        // Fachdaten-Objekts wird jetzt, unabhängig von
        // seiner Klasse, mit Hilfe der Reflection-Methoden
        // nach ihrem Namen gesucht. Sollte keine Methode
        // diesen Namens existieren, wird null zurück geliefert.
		try {
			if (fachdaten instanceof Objekt) {
				tmp = (Objekt) fachdaten;
			} else if (fachdaten instanceof BwkFachdaten){
				tmp = ((BwkFachdaten) fachdaten).getAnfallstelle().getObjekt();
			} else {
				Method getBO = fachdaten.getClass().getMethod("getObjekt");
				tmp = (Objekt) getBO.invoke(fachdaten);
			}
        } catch (Exception e) {
            //e.printStackTrace();
            tmp = null;
        }

//        if (fachdaten instanceof Anh50Fachdaten) {
//            tmp = ((Anh50Fachdaten) fachdaten).getBasisObjekt();
//        } else if (fachdaten instanceof Anh49Fachdaten) {
//            tmp = ((Anh49Fachdaten) fachdaten).getBasisObjekt();
//        } else {
//            tmp = null;
//        }

        return tmp;
    }

	public class CellRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = -8050656734831160106L;

        @Override
        public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			super.getTableCellRendererComponent(table, value, isSelected,
					hasFocus, row, column);
			if (value instanceof Date) {
				// Use SimpleDateFormat class to get a formatted String from
				// Date object.
				String strDate = new SimpleDateFormat("dd.MM.yyyy")
						.format((Date) value);
				// Sorting algorithm will work with model value. So you dont
				// need to worry
				// about the renderer's display value.
				this.setText(strDate);
			}

			return this;
		}
	}

    /**
     * @return Eine Tabelle für die Ergebnisse der Abfrage.
     */
    protected JTable getResultTable() {
        if (resultTable == null) {
            resultTable = new JTable(getTableModel());
            resultTable.setAutoCreateRowSorter(true);
            resultTable.setDefaultRenderer(Object.class, new CellRenderer());

            resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = resultTable.convertRowIndexToModel(resultTable.rowAtPoint(origin));
                        editObject(row);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    showResultPopup(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    showResultPopup(e);
                }
            });

            resultTable.getInputMap().put((KeyStroke)getObjektEditAction().getValue(Action.ACCELERATOR_KEY), getObjektEditAction().getValue(Action.NAME));
            resultTable.getActionMap().put(getObjektEditAction().getValue(Action.NAME), getObjektEditAction());
        }
        return resultTable;
    }


    /**
     * @return Das ScrollPane für die Ergebnis-Tabelle.
     */
    private JScrollPane getTableScroller() {
        if (tableScroller == null) {
            tableScroller = new JScrollPane(getResultTable());
        }
        return tableScroller;
    }

    /**
     * @return Eine Action, um editObject() aufzurufen.
     */
    protected Action getObjektEditAction() {
        if (objektEditAction == null) {
            objektEditAction = new AbstractAction("Bearbeiten") {
                private static final long serialVersionUID = 8024855364512340721L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = getResultTable().getSelectedRow();
                    editObject(row);
                }
            };
            objektEditAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
            objektEditAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));
        }

        return objektEditAction;
    }

    /**
     * @return Eine Action, um saveTabelle() aufzurufen.
     */
    protected Action getSaveAction() {
        if (saveAction == null) {
            saveAction = new AbstractAction("Tabelle exportieren") {
                private static final long serialVersionUID = 6018882173470934635L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    AuikUtils.saveTabelle(getResultTable(), frame);
                }
            };
            saveAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_E));
            saveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK, false));
        }

        return saveAction;
    }

    /**
     * Zeigt das Kontextmenü an, wenn entsprechende Events auftreten.
     * @param e Das auslösende MouseEvent.
     */
    private void showResultPopup(MouseEvent e) {
        if (resultPopup == null) {
            resultPopup = new JPopupMenu("Objekt");
            JMenuItem bearbItem = new JMenuItem(getObjektEditAction());
            JMenuItem saveItem = new JMenuItem(getSaveAction());
            resultPopup.add(bearbItem);
            resultPopup.add(saveItem);
        }

        if (e.isPopupTrigger()) {
            Point origin = e.getPoint();
            int row = getResultTable().rowAtPoint(origin);

            if (row != -1) {
                getResultTable().setRowSelectionInterval(row, row);
                resultPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    /**
     * Schaltet zum "Objekt Bearbeiten"-Modul um, wenn zu
     * einem Objekt in der Ergebnis-Tabelle ein BasisObjekt
     * vorhanden ist.
     * @param row Die Zeile der Tabelle.
     */
    protected void editObject(int row) {

        if (row != -1) {
            Objekt obj = getBasisObjektFromFachdaten(getTableModel().getObjectAtRow(row));

            if (obj != null) {
//                AUIKataster.debugOutput("Bearbeite BO: " + obj, "AQM");
                manager.getSettingsManager().setSetting("auik.imc.edit_object", obj.getId().intValue(), false);
                manager.switchModul("m_objekt_bearbeiten");
            }

        }
    }
}
