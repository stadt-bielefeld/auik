package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein TableModel für eine Tabelle mit Probenahmen zu einem Probepunkt.
 * @author David Klotz
 */
public class ProbenahmenModel extends ListTableModel {
    private AtlProbepkt probepkt = null;
    private String secondColumn = null;

    public ProbenahmenModel(String secondColumn) {
        super(new String[]{
                "Kennnummer",
                secondColumn,
                "Datum",
                "Bemerkung"}, false, true);
        this.secondColumn = secondColumn;
    }

    public ProbenahmenModel() {
        super(new String[]{
                "Kennnummer",
                "Datum",
                "Status",
                "Bemerkung"}, false, true);
    }

    public void setProbepunkt(AtlProbepkt probepkt) {
        this.probepkt = probepkt;
    }

    public void updateList() {
        if (probepkt != null) {
            setList(AtlProbenahmen.getProbenahmen(probepkt, false, -1));

            fireTableDataChanged();
        }
    }

    public void findByProperty(String suche, String property) {
        setList(AtlProbenahmen.findProbenahmen(suche, property));
    }

    public void findByKA(AtlProbeart art, AtlKlaeranlagen ka) {
        setList(AtlProbenahmen.getKSProbenahmen(art, ka));
    }

    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;
        AtlProbenahmen probe = (AtlProbenahmen) objectAtRow;
        if (secondColumn == null) {
            switch(columnIndex) {
                case 0:
                    value = probe.getKennummer();
                    break;
                case 1:
                    value = AuikUtils.getStringFromDate(probe.getDatumDerEntnahme());
                    break;
                case 2:
                	if (probe.getAtlStatus() != null)
                		value = probe.getAtlStatus().getBezeichnung();
                	else
                		value = "";
                    break;
                case 3:
                    value = probe.getBemerkung();
                    break;
                default:
                    value = null;
            }
        } else {
            switch(columnIndex) {
                case 0:
                    value = probe.getKennummer();
                    break;
                case 1:
                    if (secondColumn.equals("Art")) {
                        value = probe.getAtlProbepkt().getAtlProbeart();
                    } else if (secondColumn.equals("Pkt-ID")) {
                        value = probe.getAtlProbepkt().getObjektid();
                    } else {
                        value = "";
                    }
                    break;
                case 2:
                    value = AuikUtils.getStringFromDate(probe.getDatumDerEntnahme());
                    break;
                case 3:
                    value = probe.getBemerkung();
                    break;
                default:
                    value = null;
            }
        }
        return value;
    }

    public boolean objectRemoved(Object objectAtRow) {
        AtlProbenahmen removedProbe = (AtlProbenahmen) objectAtRow;
        boolean removed;

        if (removedProbe.getKennummer() != null) {
            removed = AtlProbenahmen.removeProbenahme(removedProbe);
        } else {
            removed = true;
        }

        return removed;
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex oder <code>null</code>, falls die Zeile nicht existiert
     */
    public AtlProbenahmen getRow(int rowIndex) {
        return (AtlProbenahmen) getObjectAtRow(rowIndex);
    }
}