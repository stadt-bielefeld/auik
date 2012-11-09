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

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;

/**
 * A class that represents a row in the 'ATL_PROBENAHMEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class AtlProbenahmen extends AbstractAtlProbenahmen implements
    Serializable {
    private static final long serialVersionUID = 950596109574293371L;

    private boolean loadedAtlAnalysepositionen = false;

    /**
     * Simple constructor of AtlProbenahmen instances.
     */
    public AtlProbenahmen() {
        // This is intentionally left blank.
    }

    /**
     * Constructor of AtlProbenahmen instances given a simple primary key.
     * @param kennummer
     */
    public AtlProbenahmen(java.lang.Integer id) {
        super(id);
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    /* Add customized code below */

    /**
     * Liefert alle Probenahmen eines bestimmten Probepunktes.
     * @param pkt Der Probepunkt.
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     */
    public static List<?> getProbenahmen(AtlProbepkt pkt, boolean loadPos) {
        if (pkt.getObjektid() == null) {
            return new ArrayList<AtlProbenahmen>();
        }
        List<?> proben = null;
        proben = new DatabaseAccess()
            .createQuery(
                "FROM AtlProbenahmen as probenahme "
                    + "WHERE probenahme.atlProbepkt = :pkt "
                    + "ORDER BY probenahme.datumDerEntnahme desc, "
                    + "probenahme.kennummer desc")
            .setEntity("pkt", pkt)
            .list();

        return proben;
    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param id Die ID der Probenahme
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
     *         nicht existiert
     */
    public static AtlProbenahmen findById(Integer id) {
        return getProbenahme(id, false);
    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param id Die ID der Probenahme
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
     *         nicht existiert
     */
    public static AtlProbenahmen getProbenahme(Integer id, boolean loadPos) {
        if (loadPos) {
            return (AtlProbenahmen) new DatabaseAccess()
                .getAndInitCollections(AtlProbenahmen.class, id);
        } else {
            return (AtlProbenahmen) new DatabaseAccess()
                .get(AtlProbenahmen.class, id);
        }
    }

    public static boolean merge(AtlProbenahmen probe) {
        return new DatabaseAccess().saveOrUpdate(probe);
    }

    public static boolean delete(AtlProbenahmen probe) {
        return new DatabaseAccess().delete(probe);
    }

    /**
     * Fügt dieser Probenahme eine Analyseposition hinzu (und sorgt für die
     * richtigen Fremdschlüssel in den Tabellen).
     * @param pos Die neue Analyseposition
     */
    public void addAnalyseposition(AtlAnalyseposition pos) {
        pos.setAtlProbenahmen(this);
        this.loadAtlAnalysepositionen().add(pos);
    }

    /**
     * Suche eine bestimmte AtlAnalyseposition anhand des Ordnungsbegriffs eines
     * Parameters.
     * @param ordnungsbegriff Der Ordnungsbegriff eines AtlParameter der zu der
     *            AtlAnalyseposition geh&ouml;rt.
     * @return eine bereits existente {@link AtlAnalyseposition} oder eine neue
     *         {@link AtlAnalyseposition}.
     */
    public AtlAnalyseposition findAtlAnalyseposition(AtlParameter parameter,
        AtlEinheiten einheit, boolean createNew) {
        String ordnungsbegriff = parameter.getOrdnungsbegriff();
        Set<AtlAnalyseposition> positionen =
            (Set<AtlAnalyseposition>) loadAtlAnalysepositionen();
        Iterator<AtlAnalyseposition> iter = positionen.iterator();

        // TODO: Why don't we use a query here?
        while (iter.hasNext()) {
            AtlAnalyseposition tmp = (AtlAnalyseposition) iter.next();
            AtlParameter param = tmp.getAtlParameter();

            if (ordnungsbegriff.equals(param.getOrdnungsbegriff())) {
                return tmp;
            }
        }

        if (!createNew) {
            return null;
        }

        AtlAnalyseposition neu = new AtlAnalyseposition();
        neu.setAtlProbenahmen(this);
        neu.setAtlParameter(parameter);
        neu.setAtlEinheiten(einheit);

        addAnalyseposition(neu);

        return neu;
    }

    /**
     * Diese Methode ruft {@link findAtlAnalyseposition(AtlParameter,
     * AtlEinheiten, boolean)} mit einem gesetzten <code>createNew</code>
     * Parameter auf.
     */
    public AtlAnalyseposition findAtlAnalyseposition(AtlParameter parameter,
        AtlEinheiten einheit) {
        return findAtlAnalyseposition(parameter, einheit, true);
    }

    /**
     * Load and return AtlAnalysepositionen<br>
     * Will only work on a saved AtlProbenahme.
     * @return
     */
    public Set<AtlAnalyseposition> loadAtlAnalysepositionen() {
      // TODO: This is not an optimal solution...
      if (!loadedAtlAnalysepositionen) {
          super.setAtlAnalysepositionen(
              DatabaseQuery.getAnalysepositionen(this));
          loadedAtlAnalysepositionen = true;
      }
//      super.setAtlAnalysepositionen(
//          AtlAnalyseposition.getAnalysepositionen(this));
      return super.getAtlAnalysepositionen();
    }

    /**
     * Return all Collections which need to be initialized.
     * @return An array of all Collections
     */
    @Override
    public Vector<Collection<?>> getToInitCollections() {
        Vector<Collection<?>> collections = new Vector<Collection<?>>();
        collections.add(getAtlAnalysepositionen());
        return collections;
    }
}
