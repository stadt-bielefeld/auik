package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
/**
 * Ein TableModel für die Basis-Betreiberdaten.
 * @author David Klotz
 */
public class BasisBetreiberModel extends ListTableModel {
    private String lastSuchWort = null;
    private String lastProperty = null;

    private boolean zeigeAdresse;

    public BasisBetreiberModel() {
        this(true);
    }

    public BasisBetreiberModel(boolean zeigeAdresse) {
        super(new String[]{
                "Name",
                "Anrede",
                "Zusatz"}, false, true);

        this.zeigeAdresse = zeigeAdresse;

        if (zeigeAdresse) {
            columns = new String[]{
                    "Name",
                    "Anrede",
                    "Zusatz",
                    "Straße",
                    "Nr."
            };
        }
    }

    /**
     * Aktualisiert die aktuell angezeigte Liste in dem die letzte Suche wiederholt wird.
     */
    public void updateList() {
        if (lastSuchWort != null) {
            filterList(lastSuchWort, lastProperty);
        }
    }

    /**
     * Liefert den Inhalt der Zelle mit den gegebenen Koordinaten.
     * @param objectAtRow Das Objekt in dieser Zeile der Tabelle
     * @param columnIndex Die Spalte der Tabelle
     * @return Den Wert der Zelle oder null (falls die Zelle nicht existiert)
     */
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;

        BasisBetreiber betr = (BasisBetreiber) objectAtRow;
        switch(columnIndex) {
            case 0:
                value = betr.getBetrname();
                break;
            case 1:
                value = betr.getBetranrede();
                break;
            case 2:
                value = betr.getBetrnamezus();
                break;
            case 3:
                value = betr.getStrasse();
                break;
            case 4:
                if (betr.getHausnrzus() != null) {
                    String tmp = betr.getHausnr() + betr.getHausnrzus();
                    value = tmp;
                } else {
                    value = betr.getHausnr();
                }
                break;
            default:
                value = null;
        }

        return value;
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex
     */
    public BasisBetreiber getRow(int rowIndex) {
        return (BasisBetreiber) super.getObjectAtRow(rowIndex);
    }

    public boolean objectRemoved(Object objectAtRow) {
        BasisBetreiber removedBetreiber = (BasisBetreiber) objectAtRow;
        return BasisBetreiber.removeBetreiber(removedBetreiber);
    }

    /**
     * Filtert den Tabelleninhalt nach Anrede, Name oder Zusatz.
     * Zu den möglichen Werten von <code>property</code>, siehe {@link BasisBetreiber#findBetreiber(String, String)}.
     * @param suche Der Such-String
     * @param property Die Eigenschaft, nach der Gesucht werden soll, oder <code>null</code>.
     */
    public void filterList(String suche, String property) {
        setList(BasisBetreiber.findBetreiber(suche, property));
        lastSuchWort = suche;
        lastProperty = property;
    }
}