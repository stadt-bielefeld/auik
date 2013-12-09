package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein TableModel für eine Tabelle mit Probenahmen zu einem Sielhautpunkt.
 * @author David Klotz
 */
public class SielhautProbeModel extends ListTableModel {
    private static final long serialVersionUID = -7308141358160583962L;
    private AtlProbepkt probepkt;
    private Map<AtlProbenahmen, List<AtlAnalyseposition>> wertMap;
    private AtlParameter[] params;

    public SielhautProbeModel() {
        super(new String[] {"Kennnummer", "Datum"}, false, true);

        String[] paramIDs = new String[] {
            DatabaseConstants.ATL_PARAMETER_ID_BLEI,
            DatabaseConstants.ATL_PARAMETER_ID_CADMIUM,
            DatabaseConstants.ATL_PARAMETER_ID_CHROM,
            DatabaseConstants.ATL_PARAMETER_ID_KUPFER,
            DatabaseConstants.ATL_PARAMETER_ID_NICKEL,
            DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER,
            DatabaseConstants.ATL_PARAMETER_ID_ZINK
        };
        this.params = new AtlParameter[paramIDs.length];
        for (int i = 0; i < paramIDs.length; i++) {
            this.params[i] = AtlParameter.findById(paramIDs[i]);
        }

        this.columns = new String[this.params.length + 2];
        this.columns[0] = "Kennnummer";
        this.columns[1] = "Datum";
        for (int i = 0; i < this.params.length; i++) {
            if (this.params[i] != null) {
                this.columns[i + 2] = this.params[i].getBezeichnung();
            }
        }

        this.wertMap = new HashMap<AtlProbenahmen, List<AtlAnalyseposition>>();
    }

    public void setProbepunkt(AtlProbepkt probepkt) {
        this.probepkt = probepkt;
    }

    @Override
    public void updateList() {
        if (this.probepkt != null) {
            List<AtlProbenahmen> proben =
                DatabaseQuery.findProbenahmen(this.probepkt);
            setList(proben);

            // Do all the database stuff first...
            Map<AtlProbenahmen, Map<AtlParameter, AtlAnalyseposition>> bigMap =
                DatabaseQuery.getAnalysepositionen(this.probepkt);

            this.wertMap.clear();
            for (int i = 0; i < getList().size(); i++) {
                AtlProbenahmen probe = getRow(i);
                List<AtlAnalyseposition> wertList =
                    new ArrayList<AtlAnalyseposition>(this.params.length);

                for (int j = 0; j < this.params.length; j++) {
                    wertList.add(j, bigMap.get(probe).get(this.params[j]));
                }

                this.wertMap.put((AtlProbenahmen) getList().get(i), wertList);
            }

            // fireTableDataChanged();
        }
    }

    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;
        AtlProbenahmen probe = (AtlProbenahmen) objectAtRow;

        if (columnIndex == 0) {
            value = probe.getKennummer();
        } else if (columnIndex == 1) {
            value = AuikUtils.getStringFromDate(probe.getDatumDerEntnahme());
        } else {
            List<AtlAnalyseposition> wertList = this.wertMap.get(probe);
            AtlAnalyseposition pos = wertList.get(columnIndex - 2);
            if (pos != null) {
                String tmp = pos.getWert().toString().replace(".", ",");
                value = tmp;
            } else {
                value = "-";
            }
        }

        return value;
    }

    @Override
    public boolean objectRemoved(Object objectAtRow) {
        AtlProbenahmen removedProbe = (AtlProbenahmen) objectAtRow;
        boolean removed;

        if (removedProbe.getKennummer() != null) {
            removed = removedProbe.delete();
        } else {
            removed = true;
        }

        return removed;
    }

    /**
     * Liefert das Objekt aus einer bestimmten Zeile.
     * @param rowIndex Die Zeile
     * @return Das Objekt bei rowIndex oder <code>null</code>, falls die Zeile
     *         nicht existiert
     */
    public AtlProbenahmen getRow(int rowIndex) {
        return (AtlProbenahmen) getObjectAtRow(rowIndex);
    }
}