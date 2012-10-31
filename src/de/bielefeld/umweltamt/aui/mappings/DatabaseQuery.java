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

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhBwkFachdaten;
import de.bielefeld.umweltamt.aui.mappings.tipi.AuikWzCode;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * This is a service class for all custom queries.<br>
 * <br>
 * These queries are not in their respective classes because we want to keep
 * them as clean of not-generated code as possible. As most of the custom
 * queries were static anyway, they can just as fine live here.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class DatabaseQuery {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /* ********************************************************************** */
    /* General Queries for all packages                                       */
    /* ********************************************************************** */

    /**
     * Get a list of all <code>T</code>
     * @return <code>List&lt;T&gt;</code>
     *         all <code>T</code>
     */
    public static <T> List<T> getAll(T type) {
        log.debug("Getting all " + type.getClass().getSimpleName()
            +  " instances");
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(type.getClass()), type);
    }

    /**
     * Get an ordered list of all <code>T</code> ordered by the id
     * @return <code>List&lt;T&gt;</code>
     *         all <code>T</code>
     */
    public static <T> List<T> getOrderedAll(T type) {
        log.debug("Getting all " + type.getClass().getSimpleName()
            +  " instances");
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(type.getClass())
                .addOrder(new DatabaseAccess().getIdOrder(type.getClass())),
            type);
    }

    /**
     * Get an ordered list of all <code>T</code> ordered by a given property
     * @return <code>List&lt;T&gt;</code>
     *         all <code>T</code>
     */
    public static <T> List<T> getOrderedAll(T type, String propertyName) {
        log.debug("Getting all " + type.getClass().getSimpleName()
            +  " instances");
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(type.getClass())
                .addOrder(Order.asc(propertyName)),
            type);
    }

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
     * @return
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

    /**
     * Liefert alle in der Einheiten-Tabelle gespeicherten Einheiten.
     * @return Ein Array mit allen Einheiten
     */
    public static AtlEinheiten[] getEinheiten() {
        return DatabaseQuery.getOrderedAll(new AtlEinheiten(), "bezeichnung")
            .toArray(new AtlEinheiten[0]);
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

    /* ********************************************************************** */
    /* Queries for package INDEINL                                            */
    /* ********************************************************************** */

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh49Fachdaten                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get a selection of Anhang49 objects which meet the given parameters<br>
     * Global restriction: no "Fettabscheider"
     * @param aktiv (optional) The status of the underlying BasisObjekt
     * @param abgemeldet (optional)
     * @param abwasserfrei (optional)
     * @param abgelWdrVorlage (optional)
     *        Is the date of the WdrVorlage in the past?
     * @param tuev (optional) Year of next TÜV date
     * @param sachbearbeiter
     * @returns <code>List&lt;Anh49Fachdaten&gt;</code>
     */
    public static List<Anh49Fachdaten> getAnh49FachdatenAuswahl(
        Boolean aktiv, Boolean abgemeldet, Boolean abwasserfrei,
        Boolean abgelWdrVorlage, Integer tuev,
        BasisSachbearbeiter sachbearbeiter) {

        DetachedCriteria criteria =
            DetachedCriteria.forClass(Anh49Fachdaten.class)
                .createAlias("basisObjekt", "obj")
                .createAlias("basisObjekt.basisObjektarten", "art");

        if (aktiv != null) {
            criteria.add(Restrictions.eq("obj.inaktiv", !aktiv));
        }
        if (abgemeldet != null) {
            criteria.add(Restrictions.eq("abgemeldet", abgemeldet));
        }
        if (abwasserfrei != null) {
            criteria.add(Restrictions.eq("abwasserfrei", abwasserfrei));
        }
        if (abgelWdrVorlage != null && abgelWdrVorlage) {
            criteria.add(Restrictions.le("wiedervorlage", new Date()));
        }
        if (tuev != null) {
            criteria.add(
                Restrictions.sqlRestriction(
                    "date_part('year', dekra_Tuev_Datum) = ?",
                    tuev, Hibernate.INTEGER));
        }
        if (sachbearbeiter != null) {
            criteria.add(
                Restrictions.eq("obj.basisSachbearbeiter", sachbearbeiter));
        }

        criteria.add(Restrictions.not(
            Restrictions.like("art.objektart", "Fettabscheider")));

        criteria.addOrder(Order.asc("dekraTuevDatum"));

        return new DatabaseAccess().executeCriteriaToList(
            criteria, new Anh49Fachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class AnhBwkFachdaten                     */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get AnhBwkFachdaten for a given year.
     * @param year Integer
     * @return <code>List&lt;AnhBwkFachdaten&gt;</code>
     */
    public static List<AnhBwkFachdaten> getBwkByYear(Integer year) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(AnhBwkFachdaten.class)
                .createAlias("basisObjekt.basisStandort", "standort");
        detachedCriteria.addOrder(Order.asc("standort.strasse"));
        detachedCriteria.addOrder(Order.asc("standort.hausnr"));
        /* year == -1 => alle Jahre */
        if (year != -1) {
            detachedCriteria.add(
                DatabaseAccess.getRestrictionsEqualOrNull("erfassung", year));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new AnhBwkFachdaten());
    }

    /**
     * Get a list of all recording years
     * @return Integer[]
     */
    public static Integer[] getBwkErfassungsjahre() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AnhBwkFachdaten.class)
                .setProjection(Projections.distinct(
                    Projections.property("erfassung"))),
            new Integer[0]);
    }

    /* ********************************************************************** */
    /* Queries for package VAWS                                               */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* Queries for package TIPI                                               */
    /* ********************************************************************** */

    /**
     * Get an array of the WZ-Codes which are marked for the Kurzauswahl
     * @return <code>AuikWzCode[]</code>
     */
    public static AuikWzCode[] getAuikWzCodesInKurzAuswahl() {
        return new DatabaseAccess().executeCriteriaToArray(
            DetachedCriteria.forClass(AuikWzCode.class)
                .add(Restrictions.eq("inKurzAuswahl", true))
                .addOrder(Order.asc("bezeichnung")),
            new AuikWzCode[0]);
    }
}
