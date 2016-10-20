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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abfuhr;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Analysen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Kontrollen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Ortstermine;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Verwaltungsverf;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh53Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh55Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhBwkFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhEntsorger;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AnhSuevFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.IndeinlGenehmigung;

/**
 * This is a service class for all custom queries from the indeinl package.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseIndeinlQuery extends DatabaseVawsQuery {

    /* ********************************************************************** */
    /* Queries for package INDEINL                                            */
    /* ********************************************************************** */


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class BasisObjekt                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Sucht alle Objekte einer Objektart,  die nicht erloschen sind.
     * @param Objektart Es sollen nur Datensätze dieser Artangezeigt werden.
     * @return <code>List&lt;BasisObjekt&gt;</code>
     *         Eine Liste mit den entstprechenden Objekte.
     */
    public static List<BasisObjekt> getBasisObjektByArt(
    		BasisObjektarten art) {
    	return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(BasisObjekt.class)
            	.add(Restrictions.eq("basisObjektarten", art))
                .add(Restrictions.eq("inaktiv", false)),
            new BasisObjekt());
                
        
    }
    /**
     * Sucht alle Objekte des angemeldeten Sachbearbeiters, die ein
     * Wiedervorlagedatum haben.
     * @param nurWiedervorlageAbgelaufen Sollen nur Datensätze angezeigt werden,
     *            deren Wiedervorlage in der Vergangenheit liegt?
     * @return <code>List&lt;BasisObjekt&gt;</code>
     *         Eine Liste mit den entstprechenden Objekten.
     */
	public static List<BasisObjekt> getBasisObjektByWiedervorlage(
			boolean nurWiedervorlageAbgelaufen) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(BasisObjekt.class)
				.add(Restrictions.eq("basisSachbearbeiter",
						DatabaseQuery.getCurrentSachbearbeiter()))
				.add(Restrictions.isNotNull("wiedervorlage"))
                .addOrder(Order.asc("basisObjektarten"))
                .addOrder(Order.asc("wiedervorlage"));
        if (nurWiedervorlageAbgelaufen) {
            detachedCriteria.add(Restrictions.le("wiedervorlage", new Date()));
        }

		return new DatabaseAccess().executeCriteriaToList(detachedCriteria,
				new BasisObjekt());

	}
    
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
//        Set<Anh49Abscheiderdetails> set = fachdaten.getAnh49Abscheiderdetailses();
//        List<Anh49Abscheiderdetails> list = new ArrayList<Anh49Abscheiderdetails>();
//        list.addAll(set);
//        return list;
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Abscheiderdetails.class)
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .addOrder(Order.asc("abscheidernr")),
            new Anh49Abscheiderdetails());
    }

    /**
     * Get all Anh49Fachdaten that are Fettabscheider
     * @return <code>List&lt;Anh49Fachdaten&gt;</code>
     */
    public static List<Anh49Fachdaten> getFettabscheider() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Fachdaten.class)
                .createAlias("basisObjekt", "objekt")
                .createAlias("objekt.basisObjektarten", "art")
                .createAlias("objekt.basisAdresse", "adresse")
                .add(Restrictions.eq("art.id",
                    DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER))
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("adresse.betrname")),
            new Anh49Fachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh49Analysen                       */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get all Anh49Analysen for an Anh49Fachdaten and sort them by date
     * @param fachdaten Anh49Fachdaten
     * @return <code>List&lt;Anh49Analysen&gt;</code>
     */
    public static List<Anh49Analysen> getAnalysen(Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Analysen.class)
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .addOrder(Order.asc("datum")),
            new Anh49Analysen());
    }

    // TODO: Maybe work with the set directly?
    public static Timestamp getLetzteAnalyse(Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().executeCriteriaToUniqueResult(
            DetachedCriteria.forClass(Anh49Analysen.class)
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .setProjection(Projections.max("datum")),
            new Timestamp(0));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh49Fachdaten                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get a selection of Anhang49 objects which meet the given parameters<br>
     * Global restriction: no BasisObjektart "Fettabscheider"
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
        Boolean inaktiv, Boolean abgemeldet, Boolean abwasserfrei,
        Boolean abgelWdrVorlage,
        BasisSachbearbeiter sachbearbeiter) {

        DetachedCriteria criteria =
            DetachedCriteria.forClass(Anh49Fachdaten.class)
                .createAlias("basisObjekt", "obj")
                .createAlias("basisObjekt.basisObjektarten", "art");

        if (inaktiv == true) {
            criteria.add(Restrictions.eq("obj.inaktiv", inaktiv));
        }
        if (abgemeldet == true) {
            criteria.add(Restrictions.eq("abgemeldet", abgemeldet));
        }
        if (abwasserfrei == true) {
            criteria.add(Restrictions.eq("abwasserfrei", abwasserfrei));
        }
        if (abgelWdrVorlage == true && abgelWdrVorlage) {
            criteria.add(Restrictions.le("wiedervorlage", new Date()));
        }
//        if (tuev != null) {
//            Calendar start = Calendar.getInstance();
//            start.set(tuev, 1, 1, 0, 0, 0); // start = 'tuev-01-01 00:00:00'
//            Calendar end = Calendar.getInstance();
//            end.set(tuev, 12, 31, 23, 59, 59); // end = 'tuev-12-31 23:59:59'
//            criteria.add(Restrictions.between(
//                "dekraTuevDatum", start.getTime(), end.getTime()));
//        }
        if (sachbearbeiter != null) {
            criteria.add(
                Restrictions.eq("obj.basisSachbearbeiter", sachbearbeiter));
        }

        criteria.add(Restrictions.ne("art.id",
                DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER));

        criteria.addOrder(Order.asc("obj.inaktiv"));

        return new DatabaseAccess().executeCriteriaToList(
            criteria, new Anh49Fachdaten());
    }

    // This is used for a drop down box. When the time comes and we are not
    // using the dekra_tuev_datum anymore, this should be replaced by the last
    // analyse datum
//    public static Integer[] getOldDekraTuevYears() {
//        Integer years[] = null;
//        Timestamp times[] = new DatabaseAccess().executeCriteriaToArray(
//            DetachedCriteria.forClass(Anh49Fachdaten.class)
//                .createAlias("basisObjekt", "objekt")
//                .createAlias("basisObjekt.basisObjektarten", "art")
//                .add(Restrictions.ne("art.id",
//                    DatabaseConstants.BASIS_OBJEKTART_ID_FETTABSCHEIDER))
//                .setProjection(Projections.distinct(
//                    Projections.property("dekraTuevDatum")))
//                .addOrder(Order.asc("dekraTuevDatum")),
//            new Timestamp[0]);
//        years = new Integer[times.length];
//        Calendar cal = Calendar.getInstance();
//        for (int i = 0; i < times.length; i++) {
//            if (times[i] != null) {
//                cal.setTime(times[i]);
//                years[i] = cal.get(Calendar.YEAR);
//            } else {
//                years[i] = null;
//            }
//        }
//        return years;
//    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh49Kontrollen                     */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get all Anh49Kontrollen for an Anh49Fachdaten and sort them by date
     * @param fachdaten Anh49Fachdaten
     * @return <code>List&lt;Anh49Kontrollen&gt;</code>
     */
    public static List<Anh49Kontrollen> getKontrollen(Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Kontrollen.class)
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .addOrder(Order.asc("pruefdatum")),
            new Anh49Kontrollen());
    }

    // TODO: Maybe work with the set directly?
    public static Timestamp getNaechsteKontrolle(Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().executeCriteriaToUniqueResult(
            DetachedCriteria.forClass(Anh49Kontrollen.class)
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .setProjection(Projections.max("naechstepruefung")),
            new Timestamp(0));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh49Abfuhr                    */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get all Anh49Abfuhr for an Anh49Fachdaten and sort them by date
     * @param fachdaten Anh49Fachdaten
     * @return <code>List&lt;Anh49Kontrollen&gt;</code>
     */
    public static List<Anh49Abfuhr> getAbfuhr(Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Abfuhr.class)
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .addOrder(Order.asc("abfuhrdatum")),
            new Anh49Abfuhr());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh49Ortstermine                    */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get all Anh49Ortstermine for an Anh49Fachdaten and sort them by date
     * @param fachdaten Anh49Fachdaten
     * @return <code>List&lt;Anh49Ortstermine&gt;</code>
     */
    public static List<Anh49Ortstermine> getOrtstermine(
        Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Ortstermine.class)
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .addOrder(Order.asc("datum")),
            new Anh49Ortstermine());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh49Verwaltungsverfahren           */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get all Anh49Verwaltungsverfahren for an Anh49Fachdaten and sort them by date
     * @param fachdaten Anh49Fachdaten
     * @return <code>List&lt;Anh49Verwaltungsverfahren&gt;</code>
     */
    public static List<Anh49Verwaltungsverf> getVerwaltungsverfahren(
        Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh49Verwaltungsverf.class)
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .addOrder(Order.asc("datum")),
            new Anh49Verwaltungsverf());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh50Fachdaten                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Sucht alle Anhang50-Fachdatensätze,  die nicht erloschen sind.
     * @param nurWiedervorlageAbgelaufen Sollen nur Datensätze angezeigt werden,
     *            deren Wiedervorlage in der Vergangenheit liegt?
     * @return <code>List&lt;Anh50Fachdaten&gt;</code>
     *         Eine Liste mit den entstprechenden Anh50Fachdaten.
     */
    public static List<Anh50Fachdaten> getAnhang50ByWiedervorlage(
        boolean nurWiedervorlageAbgelaufen) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(Anh50Fachdaten.class)
                .createAlias("basisObjekt", "objekt")
                .createAlias("basisObjekt.basisAdresse", "adresse")
                .add(Restrictions.eq("erloschen", false))
                .add(Restrictions.eq("objekt.inaktiv", false))
                .addOrder(Order.asc("wiedervorlage"))
                .addOrder(Order.asc("adresse.betrname"));
        if (nurWiedervorlageAbgelaufen) {
            detachedCriteria.add(Restrictions.le("wiedervorlage", new Date()));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new Anh50Fachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh52Fachdaten                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert eine Liste mit allen Anhang52 Objekten.
     * @return <code>List&lt;Anh52Fachdaten&gt;</code>
     */
    public static List<Anh52Fachdaten> getAnhang52() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh52Fachdaten.class)
                .createAlias("basisObjekt", "objekt")
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("id")),
            new Anh52Fachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh53Fachdaten                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert eine Liste mit allen Anhang53 Objekten.
     * @return <code>List&lt;Anh53Fachdaten&gt;</code>
     */
    public static List<Anh53Fachdaten> getAnhang53() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh53Fachdaten.class)
                .createAlias("basisObjekt", "objekt")
                .createAlias("basisObjekt.basisObjektarten", "art")
                .createAlias("basisObjekt.basisStandort", "standort")
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("art.id"))
                .addOrder(Order.asc("standort.strasse"))
                .addOrder(Order.asc("standort.hausnr")),
            new Anh53Fachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh55Fachdaten                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert eine Liste mit allen Anhang55 Objekten.
     * @return <code>List&lt;Anh55Fachdaten&gt;</code>
     */
    public static List<Anh55Fachdaten> getAnhang55() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Anh55Fachdaten.class)
                .createAlias("basisObjekt", "objekt")
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("id")),
            new Anh55Fachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anh56Fachdaten                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert eine Liste mit allen Anhang53 Objekten.
     * @return <code>List&lt;Anh56Fachdaten&gt;</code>
     */
    public static List<Anh56Fachdaten> getAnhang56(
        Boolean abwasseranfall, Boolean genpflicht) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(Anh56Fachdaten.class)
                .createAlias("basisObjekt", "objekt")
                .createAlias("basisObjekt.basisStandort", "standort")
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("standort.strasse"))
                .addOrder(Order.asc("standort.hausnr"));
        if (abwasseranfall != null) {
            detachedCriteria.add(
                Restrictions.eq("abwasseranfall", abwasseranfall));
        }
        if (genpflicht != null) {
            detachedCriteria.add(
                Restrictions.eq("genpflicht", genpflicht));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new Anh56Fachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class AnhBwkFachdaten                     */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get AnhBwkFachdaten for a given year.
     * @param year Integer (may be null)
     * @return <code>List&lt;AnhBwkFachdaten&gt;</code>
     */
    public static List<AnhBwkFachdaten> getBwkByYear(Integer year) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(AnhBwkFachdaten.class)
            .createAlias("basisObjekt", "obj")
            .createAlias("basisObjekt.basisStandort", "standort")
            .add(Restrictions.eq("obj.deleted", false));
        detachedCriteria.addOrder(Order.asc("standort.strasse"));
        detachedCriteria.addOrder(Order.asc("standort.hausnr"));
        /* year == -1 => alle Jahre */
        if (year == null || year != -1) {
            detachedCriteria.add(
                DatabaseAccess.getRestrictionsEqualOrNull("erfassung", year));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new AnhBwkFachdaten());
    }
    /**
     * Get AnhBwkFachdaten for Objektart BHKW.
     * @return <code>List&lt;AnhBwkFachdaten&gt;</code>
     */
    public static List<AnhBwkFachdaten> getBHKW() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				AnhBwkFachdaten.class)
	            .createAlias("basisObjekt", "obj")
				.createAlias("basisObjekt.basisStandort", "standort")
                .createAlias("basisObjekt.basisObjektarten", "art")
				.addOrder(Order.asc("standort.strasse"))
				.addOrder(Order.asc("standort.hausnr"))
				.add(Restrictions.eq("art.id", 36))
	            .add(Restrictions.eq("obj.deleted", false));

        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new AnhBwkFachdaten());
    }
    /**
     * Get AnhBwkFachdaten for ABA = true.
     * @return <code>List&lt;AnhBwkFachdaten&gt;</code>
     */
    public static List<AnhBwkFachdaten> getABA() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				AnhBwkFachdaten.class)
	            .createAlias("basisObjekt", "obj")
				.createAlias("basisObjekt.basisStandort", "standort")
				.addOrder(Order.asc("standort.strasse"))
				.addOrder(Order.asc("standort.hausnr"))
				.add(Restrictions.eq("aba", true))
	            .add(Restrictions.eq("obj.deleted", false));

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
    /* Queries for package INDEINL: class AnhEntsorger                        */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    private static AnhEntsorger[] entsorger = null;
    /**
     * Get all AnhEntsorger
     * @return <code>AnhEntsorger[]</code>
     */
    public static AnhEntsorger[] getEntsorger() {
        if (DatabaseIndeinlQuery.entsorger == null) {
            DatabaseIndeinlQuery.entsorger =
                DatabaseQuery.getOrderedAll(new AnhEntsorger())
                    .toArray(new AnhEntsorger[0]);
        }
        return DatabaseIndeinlQuery.entsorger;
    }
	

	/**
	 * Get all AtlEinheiten and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Einheiten</code>
	 */
	public static List<AnhEntsorger> getEntsorgerlist()
	{
		List<AnhEntsorger> entsorgerlist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(AnhEntsorger.class)
												.addOrder(
															Order.asc("id")),
										new AnhEntsorger());
		return entsorgerlist;

	}

	/**
	 * Get next id for new AnhEntsorger
	 * 
	 * @return <code>AnhEntsorger</code>
	 */
	public static Integer newEntsorgerID()
	{
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
																		DetachedCriteria.forClass(AnhEntsorger.class)
																				.setProjection(
																								Property.forName("id")
																										.max()),
																		new Integer(0));
		return id + 1;
	}

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class AnhSuevFachdaten                    */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert eine Liste mit allen AnhSuevFachdaten Objekten.
     * @return <code>List&lt;AnhSuevFachdaten&gt;</code>
     */
    public static List<AnhSuevFachdaten> getAnhangSuev() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(AnhSuevFachdaten.class)
                .createAlias("basisObjekt", "objekt")
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("id")),
            new AnhSuevFachdaten());
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
                .createAlias("basisObjekt.basisAdresse", "adresse")
                .createAlias("basisObjekt.basisStandort", "standort")
                .add(Restrictions.eq("objekt.inaktiv", inaktiv))
                .addOrder(Order.asc("adresse.betrname"))
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
