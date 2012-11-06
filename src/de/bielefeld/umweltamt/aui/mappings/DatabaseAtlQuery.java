/**
 * Copyright 2005-2042, Stadt Bielefeld
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

package de.bielefeld.umweltamt.aui.mappings;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * This is a service class for all custom queries from the atl package.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseAtlQuery extends DatabaseBasisQuery {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /* ********************************************************************** */
    /* Queries for package ATL                                                */
    /* ********************************************************************** */

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlAnalyseposition                     */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * This is used to load the AtlAnalysepositions for the set within the
     * Probenahme. I am not sure if they will be auto-loaded with the new
     * generated classes. TODO: Test that!
     * @param probe
     * @return Set&lt;AtlAnalyseposition&gt;
     */
    public static Set<AtlAnalyseposition> getAnalysepositionen(
        AtlProbenahmen probe) {
        Set<AtlAnalyseposition> resultSet = new HashSet<AtlAnalyseposition>();
        List<AtlAnalyseposition> resultList = new DatabaseAccess()
            .executeCriteriaToList(
                DetachedCriteria.forClass(AtlAnalyseposition.class)
                    .add(Restrictions.eq("atlProbenahmen", probe)),
                new AtlAnalyseposition());
        resultSet.addAll(resultList);
        return resultSet;
    }

    /**
     * Liefert eine Liste der Analyseinstitute.
     * @return String[]
     */
    public static String[] getAnalysierer() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AtlAnalyseposition.class)
                .setProjection(Projections.distinct(
                    Projections.property("analyseVon"))),
            new String[0]);
    }

    /**
     * Liefert eine Analyseposition mit einem gegebenen Parameter
     * aus einer gegebenen Probenahme.
     * @param probe Die Probenahme
     * @param param Der Parameter
     * @return AtlAnalyseposition
     */
    public static AtlAnalyseposition getAnalyseposition(
            AtlProbenahmen probe, AtlParameter param) {
        Set<AtlAnalyseposition> set = probe.loadAtlAnalysepositionen();
        for (AtlAnalyseposition pos : set) {
            if (pos.getAtlParameter().getOrdnungsbegriff().equals(
                    param.getOrdnungsbegriff())) {
                return pos;
            }
        }
        return null;
    }

    /**
     * Get all <code>AtlAnalyseposition</code>s for a given
     * <code>AtlProbepkt</code> and <code>AtlParameter</code> in a given
     * time interval
     * @param param AtlParameter
     * @param pkt AtlProbepkt
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<AtlAnalyseposition> getAnalysepositionen(
            AtlParameter param, AtlProbepkt pkt, Date beginDate, Date endDate) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(AtlAnalyseposition.class)
                .createAlias("atlProbenahmen", "probe")
                .add(Restrictions.eq("probe.atlProbepkt", pkt))
                .add(Restrictions.eq("atlParameter", param))
                .add(Restrictions.between(
                    "probe.datumDerEntnahme", beginDate, endDate))
                .addOrder(Order.asc("probe.datumDerEntnahme"));
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new AtlAnalyseposition());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlEinheiten                           */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static AtlEinheiten[] atlEinheiten = null;
    /**
     * Liefert alle in der Einheiten-Tabelle gespeicherten Einheiten.
     * @return Ein Array mit allen Einheiten
     */
    public static AtlEinheiten[] getAtlEinheiten() {
        if (DatabaseAtlQuery.atlEinheiten == null) {
            DatabaseAtlQuery.atlEinheiten =
                DatabaseQuery.getOrderedAll(new AtlEinheiten(), "bezeichnung")
                    .toArray(new AtlEinheiten[0]);
        }
        return DatabaseAtlQuery.atlEinheiten;
    }

    /**
     * Get an unit by its description
     * @param description The description of the unit (e.g. "mg/l")
     * @return <code>AtlEinheiten</code>, if an unit was found,
     *         <code>null</code> otherwise
     */
    public static AtlEinheiten getEinheitByDescription(String description) {
        List<AtlEinheiten> list = new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlEinheiten.class)
                .add(Restrictions.eq("bezeichnung", description)),
//                .uniqueResult(),
            new AtlEinheiten());
        // TODO: UniqueResult for Criteria
        switch (list.size()) {
            case 1: return list.get(0);
            case 0: return null;
            default:
                log.error("More than one result in unique request!");
                return null;
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlKlaeranlagen                        */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static AtlKlaeranlagen[] atlKlaeranlagen = null;
    /**
     * Get an array of all AtlKlaeranlagen,
     * omit id 7 because this is an intended duplicate
     * @return AtlKlaeranlagen[]
     */
    public static AtlKlaeranlagen[] getKlaeranlagen() {
        if (DatabaseAtlQuery.atlKlaeranlagen == null) {
            DatabaseAtlQuery.atlKlaeranlagen =
                new DatabaseAccess().executeCriteriaToArray(
                    DetachedCriteria.forClass(AtlKlaeranlagen.class)
                        .add(Restrictions.ne("id", 7))
                        .addOrder(Order.asc("id")),
                    new AtlKlaeranlagen[0]);
        }
        return DatabaseAtlQuery.atlKlaeranlagen;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlParameter                           */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert alle Parameter, die für Klärschlamm-Probenahmen relevant sind.
     * D.h. alle, deren Klärschlamm-Grenzwert nicht <code>NULL</code> ist.
     * @return Ein Array mit allen für Klärschlamm-Probenahmen relevanten
     * Parametern
     */
    public static AtlParameter[] getKlaerschlammParameter() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AtlParameter.class)
                .add(Restrictions.isNotNull("klaerschlammGw"))
                .addOrder(Order.asc("bezeichnung")),
            new AtlParameter[0]);
    }

    /**
     * Liefert alle Parameter, die für SielhautBearbeiten-Probenahmen relevant
     * sind. D.h. alle, deren SielhautBearbeiten-Grenzwert nicht
     * <code>NULL</code> ist.
     *
     * @return Ein Array mit allen für SielhautBearbeiten-Probenahmen relevanten
     * Parametern
     */
    public static List<AtlParameter> getGroupedParameterAsList() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlParameter.class)
                .add(Restrictions.isNotNull("atlParametergruppen"))
                .addOrder(Order.asc("reihenfolge")),
            new AtlParameter());
    }

    /**
     * AtlParameter.getGroupedParameterAsList als Array
     *
     * @return Ein Array mit allen für Probenahmen relevanten Parametern
     */
    public static AtlParameter[] getGroupedParameter() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AtlParameter.class)
                .add(Restrictions.isNotNull("atlParametergruppen"))
                .addOrder(Order.asc("reihenfolge")),
            new AtlParameter[0]);
    }

    public static List<AtlParameter> getAllParameterAsList() {
        return DatabaseQuery.getOrderedAll(new AtlParameter(), "bezeichnung");
    }

    public static AtlParameter[] getAllParameterAsArray() {
        return DatabaseQuery.getOrderedAll(new AtlParameter(), "bezeichnung")
            .toArray(new AtlParameter[0]);
    }

    public static List<AtlParameter> getParameterInGroup(int id) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AtlParameter.class)
                .add(Restrictions.eq("atlParametergruppen.id", id)),
            new AtlParameter());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlParametergruppen                    */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Diese Funktion pr&uuml;ft, ob die {@link AtlParameter}, die in
     * <i>group</i> enthalten sind, vollst&auml;ndig sind.
     *
     * @param id Die ID der Parametergruppe.
     * @param group Die Liste mit den Parametern.
     *
     * @return true, wenn alle Parameter der Gruppen enthalten sind, sonst
     * false.
     */
    public static boolean isCompleteParameterGroup(
        int id, List<AtlParameter> group) {
        List<AtlParameter> complete = DatabaseQuery.getParameterInGroup(id);
        // First simply check the size
        // As we use List and not Set the size is not a good criteria...
//        if (group.size() != complete.size()) {
//            return false;
//        }
        // Check if all parameters from the complete group are in the group
        if (!(group.containsAll(complete))) {
            return false;
        }
        // Be really restrictive and check if there are other parameters
//        if (!(complete.containsAll(group))) {
//            return false;
//        }
        return true;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package ATL : class AtlProbeart                            */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static AtlProbeart[] atlProbearten = null;
    /**
     * Liefert alle vorhandenen Probearten.
     * @return Alle vorhandenen Probearten
     */
    public static AtlProbeart[] getProbearten() {
        if (DatabaseAtlQuery.atlProbearten == null) {
            DatabaseAtlQuery.atlProbearten = DatabaseQuery.getOrderedAll(
                new AtlProbeart()).toArray(new AtlProbeart[0]);
        }
        return DatabaseAtlQuery.atlProbearten;
    }
}
