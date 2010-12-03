package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein TableModel für eine Tabelle mit den verknuepften Objekten.
 *
 * @author Gerhard Genuit
 */
public class ObjektVerknuepfungModel extends ListTableModel {
    private BasisObjekt objekt;

    /**
     * Erzeugt ein neues ObjektverknuepfungModel. Dieses hat die Spalten "ID",
     * "Objektart" und "Beschreibung".
     */
    public ObjektVerknuepfungModel(BasisObjekt objekt) {
        super(new String[] { "Id", "Objektart", "Beschreibung" }, false, true);
    }

    /**
     * Setzt das Basisobjekt, nach dessen Verknuepfungen gesucht werden soll.
     *
     * @param objekt
     *            Das Basis-Objekt
     */
    public void setObjekt(BasisObjekt objekt) {
        this.objekt = objekt;
        updateList();
    }

    /**
     * Setzt den Wert der gewuenschten Spalte eines Objektes.
     *
     * @param objectAtRow
     *            Das Verknuepfungsobjekt
     * @param columnIndex
     *            Index der Spalte
     */
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        BasisObjektverknuepfung objektverk = (BasisObjektverknuepfung) objectAtRow;

        Object tmp;

        switch (columnIndex) {

        case 0:
            if (objektverk.getBasisObjektByObjekt().getObjektid().intValue() == objekt
                    .getObjektid().intValue())
                tmp = objektverk.getBasisObjektByIstVerknuepftMit()
                        .getObjektid();
            else
                tmp = objektverk.getBasisObjektByObjekt().getObjektid();
            break;

        case 1:
            if (objektverk.getBasisObjektByObjekt().getObjektid().intValue() == objekt
                    .getObjektid().intValue())
                tmp = objektverk.getBasisObjektByIstVerknuepftMit()
                        .getBasisObjektarten();
            else
                tmp = objektverk.getBasisObjektByObjekt().getBasisObjektarten();
            break;

        case 2:
            if (objektverk.getBasisObjektByObjekt().getObjektid().intValue() == objekt
                    .getObjektid().intValue())
                tmp = objektverk.getBasisObjektByIstVerknuepftMit()
                        .getBeschreibung();
            else
                tmp = objektverk.getBasisObjektByObjekt().getBeschreibung();
            break;

        default:
            tmp = null;
            break;
        }

        return tmp;
    }

    public boolean objectRemoved(Object objectAtRow) {
        BasisObjektverknuepfung removedObjekt = (BasisObjektverknuepfung) objectAtRow;
        boolean removed;

        if (removedObjekt.getClass() != null) {
            removed = BasisObjektverknuepfung
                    .removeObjektVerknuepfung(removedObjekt);
        } else {
            removed = true;
        }

        return removed;
    }

    public void updateList() {
        setList(BasisObjektverknuepfung.getVerknuepfungByObjekt(objekt));
        fireTableDataChanged();
    }

    public BasisObjektverknuepfung getRow(int rowIndex) {
        return (BasisObjektverknuepfung) getObjectAtRow(rowIndex);
    }

    public void clearList() {
        setList(null);
        fireTableDataChanged();
    }
}
