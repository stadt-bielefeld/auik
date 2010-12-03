/*
 * Created on 23.02.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils.tablemodelbase;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine TableModel-Grundlage, basierend auf einer <code>java.util.List</code>e .
 * Jede Zeile der Tabelle ist ein Objekt in der Liste.
 * @author David Klotz
 */
public abstract class ListTableModel extends BasicTableModel {
    private List dataList = null;
    private boolean removeAllowed;

    /**
     * Erzeugt eine neue TableModel-Grundlage.
     * @param columns Ein String-Array mit den Namen der Spalten der Tabelle
     * @param updateAtInit Soll die Liste gleich geupdatet werden?
     */
    public ListTableModel(String[] columns, boolean updateAtInit) {
        this(columns, updateAtInit, false);
    }
    /**
     * Erzeugt eine neue TableModel-Grundlage, bei der auch Zeilen gelöscht
     * werden können.
     * @param columns Ein String-Array mit den Namen der Spalten der Tabelle
     * @param updateAtInit Soll die Liste gleich geupdatet werden?
     * @param removeAllowed Dürfen Zeilen gelöscht werden?
     */
    public ListTableModel(String[] columns, boolean updateAtInit, boolean removeAllowed) {
        super(columns);
        this.removeAllowed = removeAllowed;

        dataList = new ArrayList();

        if (updateAtInit) {
            try {
                updateList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Erzeugt eine neue TableModel-Grundlage mit einer gegebenen Liste.
     * @param columns Ein String-Array mit den Namen der Spalten der Tabelle
     * @param liste Die anfängliche Liste
     */
    public ListTableModel(String[] columns, List liste, boolean removeAllowed) {
        super(columns);

        this.removeAllowed = removeAllowed;
        this.dataList = liste;

        try {
            updateList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Liefert die Anzahl der Zeilen in dieser Tabelle.
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    /**
     * Liefert das Objekt in einer bestimmten Zeile dieser Tabelle.
     * @param rowIndex Die Tabellen-Zeile
     * @return Das Objekt an Stelle rowIndex der Liste
     */
    public Object getObjectAtRow(int rowIndex) {
        if (rowExists(rowIndex)) {
            return dataList.get(rowIndex);
        } else {
            return null;
        }
    }

    /**
     * Liefert das Objekt in einer bestimmten Zelle dieser Tabelle.
     * @param rowIndex Die Tabellen-Zeile
     * @param columnIndex Die Tabelle-Spalte
     * @return Das Objekt in der Zelle (rowIndex, columnIndex) der Liste
     * oder <code>null</code>, falls es die Zeile oder Spalte nicht gibt
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        if (getList() != null && rowIndex < getList().size()) {
            if (columnIndex < columns.length) {
                Object objectAtRow = getObjectAtRow(rowIndex);
                value = getColumnValue(objectAtRow, columnIndex);
            }
        }
        return value;
    }

    /**
     * überprüft, ob eine bestimmte Zeile noch innerhalb der bestehenden
     * Liste liegt.
     * @param rowIndex Die Zeile
     * @return <code>true</code>, wenn rowIndex > 0 und < Liste.size() ist, sonst <code>false</code>
     */
    public boolean rowExists(int rowIndex) {
        if ((rowIndex >= 0) && (rowIndex < getList().size())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Löscht eine Zeile der Tabelle, falls das erlaubt und implementiert ist.
     * @param rowIndex Die Zeile
     * @return <code>true</code>, wenn wirklich gelöscht wurde,
     * <code>false</code>, falls dabei ein Fehler auftrat oder es aus anderen Gründen
     * nicht möglich war
     */
    public boolean removeRow(int rowIndex) {
        boolean wasRemoved = false;
        if (removeAllowed && rowExists(rowIndex)) {
            Object removed = getList().get(rowIndex);
            wasRemoved = objectRemoved(removed);
            if (wasRemoved) {
                getList().remove(rowIndex);
                fireTableRowsDeleted(rowIndex, rowIndex);
            }
        }

        return wasRemoved;
    }

    /**
     * @return Die Liste mit dem Tabellen-Inhalt.
     */
    public List getList() {
        return dataList;
    }

    /**
     * Setzt die Liste mit dem Tabellen-Inhalt.
     * @param newList Die neue Liste
     */
    public void setList(List newList) {
        this.dataList = newList;
    }

    /**
     * Wird aufgerufen, wenn <code>objectAtRow</code> aus der Tabelle gelöscht wurde.
     * Wenn in einem abgeleiteten TableModel Zeilen gelöscht werden können sollen,
     * sollte diese Methode überschrieben werden und das entsprechende Objekt auch
     * in der zu Grunde liegenden Datenbank o.Ä. gelöscht werden.
     * @param objectAtRow Das entfernte Objekt
     * @return <code>true</code>, wenn wirklich gelöscht wurde,
     * <code>false</code>, falls dabei ein Fehler auftrat oder es aus anderen Gründen
     * nicht möglich war.
     */
    public boolean objectRemoved(Object objectAtRow) {
        return false;
    }

    /**
     * Füllt die Liste mit (neuem) Inhalt.
     * Beim implementieren unbedingt überprüfen, ob die Liste
     * <code>null</code> ist und entsprechende Events feuern,
     * wenn sich der Inhalt der Tabelle geändert hat.
     * Falls beim Updaten der Liste aus einer anderen Datenquelle
     * (bspw. einer Datenbank) ein Fehler auftritt, sollte eine
     * entsprechende Exception geworfen werden.
     */
    public abstract void updateList() throws Exception;

    /**
     * Liefert den Inhalt einer Spalte zu einem Objekt aus einer Zeile.
     * @param objectAtRow Das Objekt in dieser Zeile
     * @param columnIndex Die Spalte der Tabelle
     * @return Was soll in der Tabelle an dieser Stelle angezeigt werden?
     */
    public abstract Object getColumnValue(Object objectAtRow, int columnIndex);
}
