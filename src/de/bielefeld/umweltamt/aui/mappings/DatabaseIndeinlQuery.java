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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhBwkFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.IndeinlGenehmigung;

/**
 * This is a service class for all custom queries from the indeinl package.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseIndeinlQuery extends DatabaseVawsQuery {

    /* ********************************************************************** */
    /* Queries for package INDEINL                                            */
    /* ********************************************************************** */

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh40Fachdaten                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get a list of all Anh40Fachdaten
     * @return <code>List&lt;Anh40Fachdaten&gt;</code>
     */
    public static List<Anh40Fachdaten> getAnhang40Auswertung() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh40Fachdaten.class)
                .createAlias("basisObjekt", "objekt")
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("objektid")),
            new Anh40Fachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh49Abscheiderdetails              */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert alle Abscheiderdetails eines bestimmten Fachdatenobjekts.
     * @param fachdaten Anh49Fachdaten
     * @return <code>List&lt;Anh49Abscheiderdetails&gt;</code>
     */
    // TODO: Maybe we can use the Set within the Anh49Fachdaten directly?
    public static List<Anh49Abscheiderdetails> getAbscheiderDetails(
        Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Abscheiderdetails.class)
                .add(Restrictions.eq("Anh49Fachdaten", fachdaten))
                .addOrder(Order.asc("abscheidernr")),
            new Anh49Abscheiderdetails());
    }

    /**
     * Get all Anh49Abscheiderdetails that are Fettabscheider
     * @return <code>List&lt;Anh49Abscheiderdetails&gt;</code>
     */
    public static List<Anh49Abscheiderdetails> getFettabscheider() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Abscheiderdetails.class)
                .createAlias("Anh49Fachdaten", "anhang")
                .createAlias("Anh49Fachdaten.basisObjekt", "objekt")
                .createAlias("Anh49Fachdaten.basisObjekt.basisObjektarten",
                    "art")
                .createAlias("Anh49Fachdaten.basisObjekt.basisBetreiber",
                    "betreiber")
                .add(Restrictions.eq("art.id",
                    DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER))
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("betreiber.betrname")),
            new Anh49Abscheiderdetails());
    }

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
            Calendar start = Calendar.getInstance();
            start.set(tuev, 1, 1, 0, 0, 0); // start = 'tuev-01-01 00:00:00'
            Calendar end = Calendar.getInstance();
            end.set(tuev, 12, 31, 23, 59, 59); // end = 'tuev-12-31 23:59:59'
            criteria.add(Restrictions.between(
                "dekraTuevDatum", start.getTime(), end.getTime()));
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

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class IndeinlGenehmigung                  */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static Integer[] anhaenge = null;
    public static Integer[] getAnhangFromGenehmigung() {
        if (DatabaseIndeinlQuery.anhaenge == null) {
            DatabaseIndeinlQuery.anhaenge = new DatabaseAccess()
                .executeCriteriaToArray(
                    DetachedCriteria.forClass(IndeinlGenehmigung.class)
                        .addOrder(Order.asc("anhang"))
                        .setProjection(Projections.distinct(
                            Projections.property("anhang"))),
                    new Integer[0]);
        }
        return DatabaseIndeinlQuery.anhaenge;
    }

    /**
     * Get a selection of IndeinlGenehmigungen
     * @param anhang Integer -1: all, other: IndeinlGenehmigung.anhang
     * @param inaktiv Boolean IndeinlGenehmigung.basisObjekt.inaktiv
     * @param gen58 Boolean false: whatever, true: IndeinlGenehmigung.gen58
     * @param gen59 Boolean false: whatever, true: IndeinlGenehmigung.gen59
     * @return List&lt;IndeinlGenehmigung&gt;
     */
    public static List<IndeinlGenehmigung> getGenehmigungen(
        Integer anhang, Boolean inaktiv, Boolean gen58, Boolean gen59) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(IndeinlGenehmigung.class)
                .createAlias("basisObjekt", "objekt")
                .createAlias("basisObjekt.basisBetreiber", "betreiber")
                .createAlias("basisObjekt.basisStandort", "standort")
                .add(Restrictions.eq("objekt.inaktiv", inaktiv))
                .addOrder(Order.asc("betreiber.betrname"))
                .addOrder(Order.asc("standort.strasse"))
                .addOrder(Order.asc("standort.hausnr"));
        if (anhang == null || anhang != -1) {
            detachedCriteria.add(
                DatabaseAccess.getRestrictionsEqualOrNull("anhang", anhang));
        }
        if (gen58) {
            detachedCriteria.add(Restrictions.eq("gen58", gen58));
        }
        if (gen59) {
            detachedCriteria.add(Restrictions.eq("gen59", gen59));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new IndeinlGenehmigung());
    }
}
