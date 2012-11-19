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

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisGemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektchrono;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * This is a service class for all custom queries from the basis package.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseBasisQuery extends DatabaseIndeinlQuery {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /* ********************************************************************** */
    /* Queries for package BASIS                                              */
    /* ********************************************************************** */

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisBetreiber                       */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get all BasisBetreiber with a given search string in the selected
     * property. <br>
     * If property is <code>null</code>, we search in all three properties.
     * @param property Name of the property
     * @param search Search string
     * @return <code>List&lt;BasisBetreiber&gt;</code> List of BasisBetreiber
     *         with the given search string in the given property
     */
    public static List<BasisBetreiber> getBasisBetreiber(
        String property, String search) {

        String modSearch = search.toLowerCase().trim() + "%";
        log.debug("Suche nach '" + modSearch + "' (" + property + ").");

        DetachedCriteria criteria =
            DetachedCriteria.forClass(BasisBetreiber.class)
                .addOrder(Order.asc("betrname"))
                .addOrder(Order.asc("betrnamezus"));

        if (property == null) {
            criteria.add(Restrictions.or(
                Restrictions.ilike("betrname", modSearch),
                Restrictions.or(
                    Restrictions.ilike("betranrede", modSearch),
                    Restrictions.ilike("betrnamezus", modSearch))));
        } else if (property.equals("name")) {
            criteria.add(Restrictions.ilike("betrname", modSearch));
        } else if (property.equals("anrede")) {
            criteria.add(Restrictions.ilike("betranrede", modSearch));
        } else if (property.equals("zusatz")) {
            criteria.add(Restrictions.ilike("betrnamezus", modSearch));
        } else {
            log.debug("Something went really wrong here...");
        }

        return new DatabaseAccess().executeCriteriaToList(
            criteria, new BasisBetreiber());
    }

    /**
     * Get a nicely formatted street and house number for a BasisBetreiber
     * @param betreiber BasisBetreiber
     * @return String
     */
    public static String getBetriebsgrundstueck(BasisBetreiber betreiber) {
        String strasse = betreiber.getStrasse();
        Integer hausnr = betreiber.getHausnr();
        String zusatz = betreiber.getHausnrzus();
        return (strasse != null ? strasse + " " : "")
            + (hausnr != null ? hausnr.toString() : "")
            + (zusatz != null ? zusatz : "");
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisGemarkung                       */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static BasisGemarkung[] gemarkungen = null;
    /**
     * Get an array with all <code>BasisGemarkung</code>en
     * @return <code>BasisGemarkung[]</code>
     */
    public static BasisGemarkung[] getBasisGemarkungen() {
        if (DatabaseBasisQuery.gemarkungen == null) {
            DatabaseBasisQuery.gemarkungen = DatabaseQuery.getOrderedAll(
                new BasisGemarkung()).toArray(new BasisGemarkung[0]);
        }
        return DatabaseBasisQuery.gemarkungen;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisObjekt                          */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Betreiber
     * zugeordnet sind.
     * @param betreiber Der Betreiber.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll,
     *            sonst <code>null</code>.
     * @return Eine Liste von BasisObjekten dieses Betreibers.
     */
    public static List<BasisObjekt> getObjekteByBetreiber(
            BasisBetreiber betreiber, String abteilung) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(BasisObjekt.class)
            .createAlias("basisStandort", "standort")
            .createAlias("basisObjektarten", "art")
            .add(Restrictions.eq("basisBetreiber", betreiber))
            .addOrder(Order.asc("inaktiv"))
            .addOrder(Order.asc("standort.strasse"))
            .addOrder(Order.asc("standort.hausnr"))
            .addOrder(Order.asc("art.objektart"));
        if (abteilung != null) {
            detachedCriteria.add(Restrictions.eq("art.abteilung", abteilung));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new BasisObjekt());
    }

    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Standort zugeordnet
     * sind.
     * @param betr Der Standort.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll,
     *            sonst <code>null</code>.
     * @param artid Die Objektart, die (nicht) dargestellt werden soll.
     * @return Eine Liste von BasisObjekten an diesem Standort.
     */
    public static List<BasisObjekt> getObjekteByStandort(
            BasisStandort standort, String abteilung, Integer artid,
            Boolean matchArtId) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(BasisObjekt.class)
            .createAlias("basisBetreiber", "betreiber")
            .createAlias("basisObjektarten", "art")
            .add(Restrictions.eq("basisStandort", standort))
            .addOrder(Order.asc("inaktiv"))
            .addOrder(Order.asc("betreiber.betrname"))
            .addOrder(Order.asc("art.objektart"));
        if (abteilung != null) {
            detachedCriteria.add(Restrictions.eq("art.abteilung", abteilung));
        }
        if (artid != null) {
            if (matchArtId) {
                detachedCriteria.add(Restrictions.eq("art.id", artid));
            } else {
                detachedCriteria.add(Restrictions.ne("art.id", artid));
            }
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new BasisObjekt());
    }

    /**
     * Cascade a priority to all objects from the same BasisBetreiber and
     * BasisStandort
     * @param prioritaet String
     * @param basisObjekt BasisObjekt
     * @return <code>true</code> if every merge was successful,
     *         <code>false</code> otherwise
     */
    public static Boolean cascadePriority(
        String prioritaet, BasisObjekt basisObjekt) {
        Boolean result = true;
        List<BasisObjekt> list = new DatabaseAccess()
            .executeCriteriaToList(
                DetachedCriteria.forClass(BasisObjekt.class)
                    .add(Restrictions.eq(
                        "basisBetreiber", basisObjekt.getBasisBetreiber()))
                    .add(Restrictions.eq(
                        "basisStandort", basisObjekt.getBasisStandort())),
                new BasisObjekt());
        for (BasisObjekt objekt : list) {
            objekt.setPrioritaet(prioritaet);
            result = result && objekt.merge();
        }
        return result;
    }

    /**
     * Get a list of all priorities. The list contains an array with
     * <code>BasisStandort</code>, <code>BasisBetreiber</code>,
     * <code>String</code> (priority) and <code>BasisSachbearbeiter</code>
     * @return <code>List&lt;?&gt;</code>
     */
    public static List<?> getObjektsWithPriority() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(BasisObjekt.class)
                .add(Restrictions.isNotNull("prioritaet"))
                .add(Restrictions.isNotNull("basisSachbearbeiter"))
                .setProjection(Projections.distinct(
                    Projections.projectionList()
                        .add(Projections.property("basisStandort"))
                        .add(Projections.property("basisBetreiber"))
                        .add(Projections.property("prioritaet"))
                        .add(Projections.property("basisSachbearbeiter"))))
                .addOrder(Order.asc("prioritaet"))
                .addOrder(Order.asc("basisStandort"))
                .addOrder(Order.asc("basisBetreiber"))
                ,
            new BasisObjekt());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisObjektchrono                    */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    // TODO: Add foreign key Objekt to Objektchrono, then fix the query and this
    // will no longer be needed...
    public static Timestamp getLastChronoDateForObjekt(BasisObjekt objekt) {
//        Calendar cal = Calendar.getInstance();
        return new DatabaseAccess().executeCriteriaToUniqueResult(
            DetachedCriteria.forClass(BasisObjektchrono.class)
                .add(Restrictions.eq("basisObjekt", objekt))
                .setProjection(Projections.distinct(Projections.max("datum"))),
            new Timestamp(0));
//            cal);
    }

    // TODO: This may be replaced with objekt.getBasisObjektchonos() if we find
    // an easy way to load them as they are lazy loaded.
    public static List<BasisObjektchrono> getChronos(BasisObjekt objekt) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(BasisObjektchrono.class)
                .add(Restrictions.eq("basisObjekt", objekt))
                .addOrder(Order.asc("datum")),
            new BasisObjektchrono());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisObjektarten                     */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static BasisObjektarten[] objektarten = null;
    /**
     * Get all BasisObjektarten and sort them by their name
     * @return <code>BasisObjektarten[]</code>
     */
    public static BasisObjektarten[] getObjektarten() {
        if (DatabaseBasisQuery.objektarten == null) {
            DatabaseBasisQuery.objektarten = DatabaseQuery.getOrderedAll(
                new BasisObjektarten(), "objektart")
                .toArray(new BasisObjektarten[0]);
        }
        return DatabaseBasisQuery.objektarten;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisSachbearbeiter                  */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static BasisSachbearbeiter[] sachbearbeiter = null;
    /**
     * Get a sorted array of all enabled BasisSachbearbeiter
     * @return <code>BasisSachbearbeiter[]</code>
     */
    public static BasisSachbearbeiter[] getEnabledSachbearbeiter() {
        if (DatabaseBasisQuery.sachbearbeiter == null) {
            DatabaseBasisQuery.sachbearbeiter = new DatabaseAccess()
                .executeCriteriaToArray(
                    DetachedCriteria.forClass(BasisSachbearbeiter.class)
                        .add(Restrictions.eq("enabled", true))
                        .addOrder(Order.asc("name")),
                    new BasisSachbearbeiter[0]);
        }
        return DatabaseBasisQuery.sachbearbeiter;
    }

    /**
     * Get the current BasisSachbearbeiter.
     * @return <code>BasisSachbearbeiter</code>
     */
    public static BasisSachbearbeiter getCurrentSachbearbeiter() {
        return BasisSachbearbeiter.findById(
            SettingsManager.getInstance().getSetting("auik.prefs.lastuser"));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisStandort                        */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Check if a location already exists
     * @param strasse The street
     * @param hausnr The house number
     * @param zusatz Addition to the house number
     * @return <code>true</code>, if the given location exists,
     *         <code>false</code> otherwise
     */
    public static boolean basisStandortExists(
        String strasse, Integer hausnr, String zusatz) {
        return (!(new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(BasisStandort.class)
                .add(Restrictions.eq("strasse", strasse))
                .add(Restrictions.eq("hausnr", hausnr))
                .add(DatabaseAccess.getRestrictionsEqualOrNull(
                    "hausnrzus", zusatz)),
            new BasisStandort())
            .isEmpty()));
    }

    /**
     * Find BasisStandorte that match the parameters.
     * @param strasse String
     * @param hausnr Integer (-1: all)
     * @return <code>List&lt;BasisStandort&gt;</code>
     */
    public static List<BasisStandort> findStandorte(
        String strasse, Integer hausnr) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(BasisStandort.class)
                .add(Restrictions.ilike("strasse", strasse, MatchMode.START))
                .addOrder(Order.asc("strasse"))
                .addOrder(Order.asc("hausnr"));
        if (hausnr != -1) {
            detachedCriteria.add(Restrictions.eq("hausnr", hausnr));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new BasisStandort());
    }

    private static String[] entwaesserungsgebiete = null;
    /**
     * Get a list of all Entwässerungsgebiet Ids.
     * @return <code>String[]</code>
     */
    public static String[] getEntwaesserungsgebiete() {
        if (DatabaseBasisQuery.entwaesserungsgebiete == null) {
            DatabaseBasisQuery.entwaesserungsgebiete = new DatabaseAccess()
                .executeCriteriaToArray(
                    DetachedCriteria.forClass(BasisStandort.class)
                        .setProjection(Projections.distinct(
                            Projections.property("entgebid"))),
                    new String[0]);
        }
        return DatabaseBasisQuery.entwaesserungsgebiete;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisStrassen                        */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    // TODO: Could we change this from String to BasisStrassen?
    // SearchBox may be a problem here...
    private static String[] strassen = null;
    /**
     * Get all BasisStrassen sorted by strasse
     * @return <code>BasisStrassen[]</code>
     */
    public static String[] getStrassen() {
        if (DatabaseBasisQuery.strassen == null) {
            BasisStrassen[] basisStrassen = DatabaseQuery.getOrderedAll(
                new BasisStrassen(), "strasse").toArray(new BasisStrassen[0]);
            strassen = new String[basisStrassen.length];
            for (int i = 0; i < basisStrassen.length; i++) {
                strassen[i] = basisStrassen[i].getStrasse();
            }
        }
        return DatabaseBasisQuery.strassen;
    }

    /**
     * Get the first matching BasisStrasse for the serach String
     * @param search String
     * @return <code>BasisStrassen</code>
     */
    public static BasisStrassen findStrasse(String search) {
        List<BasisStrassen> list = new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(BasisStrassen.class)
                .add(Restrictions.ilike("strasse", search, MatchMode.START))
                .addOrder(Order.asc("strasse")),
            new BasisStrassen());
        // If we got something, just return the first result
        return (list.isEmpty() ? null : list.get(0));
    }
}
