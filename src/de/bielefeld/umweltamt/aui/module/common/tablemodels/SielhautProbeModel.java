package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein TableModel für eine Tabelle mit Probenahmen zu einem Sielhautpunkt.
 * @author David Klotz
 */
public class SielhautProbeModel extends ListTableModel {
    private static final long serialVersionUID = -7308141358160583962L;
    private Messstelle probepkt;
    private Map<Probenahme, List<Analyseposition>> wertMap;
    private Parameter[] params;

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
        this.params = new Parameter[paramIDs.length];
        for (int i = 0; i < paramIDs.length; i++) {
            this.params[i] = Parameter.findById(paramIDs[i]);
        }

        this.columns = new String[this.params.length + 2];
        this.columns[0] = "Kennnummer";
        this.columns[1] = "Datum";
        for (int i = 0; i < this.params.length; i++) {
            if (this.params[i] != null) {
                this.columns[i + 2] = this.params[i].getBezeichnung();
            }
        }

        this.wertMap = new HashMap<Probenahme, List<Analyseposition>>();
    }

    public void setMessstelle( Messstelle probepkt) {
        this.probepkt = probepkt;
    }

    @Override
    public void updateList() {
        if (this.probepkt != null) {
            List<Probenahme> proben =
                DatabaseQuery.findProbenahme(this.probepkt);
            setList(proben);

            // Do all the database stuff first...
            Map<Probenahme, Map<Parameter, Analyseposition>> bigMap =
                DatabaseQuery.getAnalysepositionen(this.probepkt);

            this.wertMap.clear();
            for (int i = 0; i < getList().size(); i++) {
                Probenahme probe = getRow(i);
                List<Analyseposition> wertList =
                    new ArrayList<Analyseposition>(this.params.length);

                for (int j = 0; j < this.params.length; j++) {
                    wertList.add(j, bigMap.get(probe).get(this.params[j]));
                }

                this.wertMap.put((Probenahme) getList().get(i), wertList);
            }

            // fireTableDataChanged();
        }
    }

    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;
        Probenahme probe = (Probenahme) objectAtRow;

        if (columnIndex == 0) {
            value = probe.getKennummer();
        } else if (columnIndex == 1) {
            value = AuikUtils.getStringFromDate(probe.getDatumDerEntnahme());
        } else {
            List<Analyseposition> wertList = this.wertMap.get(probe);
            Analyseposition pos = wertList.get(columnIndex - 2);
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
        Probenahme removedProbe = (Probenahme) objectAtRow;
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
    public Probenahme getRow(int rowIndex) {
        return (Probenahme) getObjectAtRow(rowIndex);
    }
}