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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.NullPrecedence;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektchrono;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.TabStreets;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.elka.Anhang;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaGewkennz;
import de.bielefeld.umweltamt.aui.mappings.elka.Wasserrecht;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abfuhr;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * This is a service class for all custom queries from the basis package.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseBasisQuery extends DatabaseIndeinlQuery {

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	/* ********************************************************************** */
	/* Queries for package BASIS */
	/* ********************************************************************** */

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Adresse */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Adresse with a given search string in the selected property that
	 * contain a Betreiber. <br>
	 * If property is <code>null</code>, we search in all three properties.
	 * 
	 * @param property
	 *            Name of the property
	 * @param search
	 *            Search string
	 * @return <code>List&lt;Adresse&gt;</code> List of Adresse with the given
	 *         search string in the given property
	 */
	public static List<Adresse> getAdresse(String property, String search) {

		String modSearch = search.trim().toLowerCase() + "%";
		log.debug("Suche nach '" + modSearch + "' (" + property + ").");

		String query = "SELECT DISTINCT Adresse " + "FROM adresse JOIN adresse.objektsForBetreiberid objekt ";
		if (property == null) {
			query += "WHERE LOWER(adresse.betrname) like '" + modSearch + "' OR LOWER(betranrede) like '" + modSearch
					+ "' OR LOWER(betrnamezus) like '" + modSearch + "'";
		} else if (property.equals("name")) {
			query += "WHERE LOWER(adresse.betrname) like '" + modSearch + "'";
		} else if (property.equals("anrede")) {
			query += "WHERE LOWER(adresse.betranrede) like '" + modSearch + "'";
		} else if (property.equals("zusatz")) {
			query += "WHERE LOWER(adresse.betrzus) like '" + modSearch + "'";
		} else {
			log.debug("Something went really wrong here...");
		}
		query += " AND adresse.deleted = false";
		query += " ORDER BY adresse.betrname ASC, adresse.betrnamezus ASC";

		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	/**
	 * Get a nicely formatted street and house number for a Adresse
	 * 
	 * @param betreiber
	 *            Adresse
	 * @return String
	 */
	public static String getBetriebsgrundstueck(Adresse betreiber) {
		String strasse = betreiber.getStrasse();
		Integer hausnr = betreiber.getHausnr();
		String zusatz = betreiber.getHausnrzus();
		return (strasse != null ? strasse + " " : "") + (hausnr != null ? hausnr.toString() : "")
				+ (zusatz != null ? zusatz : "");
	}

	/**
	 * Get a formatted string for a StandortAdresse
	 * 
	 * @param standort
	 *            Standort
	 * @return String
	 */
	public static String getStandortString(Standort standort) {
		String std = new String("");
		if (standort.getInhaber() != null) {
			String strasse = standort.getInhaber().getAdresse().getStrasse();
			Integer hausnr = standort.getInhaber().getAdresse().getHausnr();
			String zusatz = standort.getInhaber().getAdresse().getHausnrzus();
			std = (strasse != null ? strasse + " " : "") + (hausnr != null ? hausnr.toString() : "")
					+ (zusatz != null ? zusatz : "");
		} else {
			if (standort.getE32() != null && standort.getN32() != null) {

				Float e32 = standort.getE32();
				Float n32 = standort.getN32();
				std = "Standort: " + (e32 != null ? e32 + " " : "") + (n32 != null ? n32.toString() : "");
				
			}else {
				std = "keine Standortdaten vorhanden";
			}
		}

		return std;
	}

	/**
	 * Get a formatted string for a StandortAdresse
	 * 
	 * @param standort
	 *            Standort
	 * @return String
	 */
	public static String getStandortString1(Standort standort) {
		Float n32 = standort.getN32();
		Float e32 = standort.getE32();
		return (n32.toString() + ", " + e32.toString());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Gemarkung */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Gemarkung[] gemarkungen = null;

	/**
	 * Get an array with all <code>Gemarkung</code>en
	 * 
	 * @return <code>Gemarkung[]</code>
	 */
	public static Gemarkung[] getGemarkungen() {
		if (DatabaseBasisQuery.gemarkungen == null) {
			DatabaseBasisQuery.gemarkungen = DatabaseQuery.getOrderedAll(new Gemarkung()).toArray(new Gemarkung[0]);
		}
		return DatabaseBasisQuery.gemarkungen;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Gemarkung */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Gemarkungen and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Gemarkungen</code>
	 */
	public static List<Gemarkung> getGemarkungenlist() {
		List<Gemarkung> gemarkungenlist = new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Gemarkung.class).addOrder(Order.asc("gemarkung")), new Gemarkung());
		return gemarkungenlist;
	}

	/**
	 * Get next id for new Gemarkung
	 * 
	 * @return <code>Gemarkung</code>
	 */
	public static Integer newGemarkungID() {
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
				DetachedCriteria.forClass(Gemarkung.class).setProjection(Property.forName("id").max()), new Integer(0));
		return id + 1;
	}

	/**
	 * Liefert eine Liste von Objekten, die einem bestimmten Standort zugeordnet
	 * sind.
	 * 
	 * @param betreiber
	 *            Die Standortadresse.
	 * @param abteilung
	 *            Die Abteilung, wenn nach ihr gefiltert werden soll, sonst
	 *            <code>null</code>.
	 * @param artid
	 *            Die Objektart, die (nicht) dargestellt werden soll.
	 * @return Eine Liste von Objekten an diesem Standort.
	 */


	public static List<Objekt> getObjekteByInhaber(Inhaber inh, String abteilung, Integer artid,
			Boolean matchArtId) {
		log.debug("Fetching objects at " + inh);
		// Find objects witch matching standortid
		String query = "SELECT o.* from basis.objekt o, basis.standort s, basis.inhaber i " 
				+ " WHERE o.standortid = s.id "
				+ " AND (s.inhaberid = i.id AND i.id = " + inh.getId()
				+ " OR o.betreiberid = i.id AND i.id = " + inh.getId() + " ) "
				+ " AND o._deleted = false ORDER BY o.inaktiv, o.objektartid";

		String filter = " ";
		if (abteilung != null) {
			filter += " AND art.abteilung = '" + abteilung + "' ";
		}
		if (artid != null) {
			if (matchArtId) {
				filter += "AND art.id = " + artid + " ";
			} else {
				filter += "AND art.id != " + artid + " ";

			}
		}

		SQLQuery q = HibernateSessionFactory.currentSession().createSQLQuery(query);

		q.addEntity("o", Objekt.class);
		return q.list();
	}

	public static List<Objekt> getObjekteByStandort(Adresse adr, String abteilung, Integer artid,
			Boolean matchArtId) {
		Integer id = adr.getId();
		String strasse = adr.getStrasse();
		strasse = strasse.replace("'", "''");
		Integer hausnr = adr.getHausnr();
		String hausnrzus = adr.getHausnrzus();
		log.debug("Fetching objects at " + adr);
		// Find objects with standortid of adresse with matching fields
		String query = "SELECT o.* from basis.objekt o, basis.standort s, basis.inhaber i, basis.adresse a,basis.objektarten art "
				+ " WHERE o.standortid = s.id AND s.inhaberid = i.id AND i.adresseid = a.id AND o.objektartid = art.id"
				+ " AND a.id = '" + id + "'"
				+ " AND o._deleted = false";

		if (abteilung != null) {
			query += " AND art.abteilung = '" + abteilung + "' ";
		}
		if (artid != null) {
			if (matchArtId) {
				query += " AND art.id = " + artid + " ";
			} else {
				query += " AND art.id != " + artid + " ";

			}
		}
		query += " ORDER BY o.inaktiv, o.objektartid";
		SQLQuery q = HibernateSessionFactory.currentSession().createSQLQuery(query);
		q.addEntity("o", Objekt.class);
		return q.list();
	}

	/**
	 * Liefert eine Liste von Objekten, die einer bestimmten Strasse, Hausnummer und
	 * Hausnummernzusatz zugeordnet sind.
	 * 
	 * @param adr
	 *            Die Standortadresse.
	 * @param abteilung
	 *            Die Abteilung, wenn nach ihr gefiltert werden soll, sonst
	 *            <code>null</code>.
	 * @param artid
	 *            Die Objektart, die (nicht) dargestellt werden soll.
	 * @return Eine Liste von Objekten an diesem Standort.
	 */

	public static List<Objekt> getObjekteByStrasse(Standort std, String abteilung, Integer artid, Boolean matchArtId) {

		String strasse = std.getStrasse().replaceAll("'", "''");
		Integer nr = std.getHausnr();
		String zus = std.getHausnrzus();

		log.debug("Fetching objects at " + std);
		// Find objects witch matching fields
		String query = "SELECT o.*, a.* from basis.objekt o, basis.adresse a, basis.objektarten art "
				+ " WHERE o.standortid = a.id AND o.objektartid = art.id " + "AND a.strasse = '" + strasse
				+ "' AND a.hausnr = " + nr;
		if (zus != null) {
			query += " AND a.hausnrzus = '" + zus + "' ";
		} else {
			query += " AND a.hausnrzus IS NULL ";
		}

		query += " AND o._deleted = false ";

		String filter = " ";
		if (abteilung != null) {
			filter += " AND art.abteilung = '" + abteilung + "' ";
		}
		if (artid != null) {
			if (matchArtId) {
				filter += "AND art.id = " + artid + " ";
			} else {
				filter += "AND art.id != " + artid + " ";

			}
		}
		query += filter + "ORDER BY o.inaktiv, a.betrname, art.objektart, o.beschreibung;";
		SQLQuery q = HibernateSessionFactory.currentSession().createSQLQuery(query);
		// log.debug(query1 + query2);
		q.addEntity("o", Objekt.class);
		return q.list();

	}

	/**
	 * Liefert eine Liste von Objekten einer Objektart sind.
	 * 
	 * @param artid
	 *            Die Objektart, die (nicht) dargestellt werden soll.
	 * @return Eine Liste von Objekten an diesem Standort.
	 */
	public static List<Objekt> getObjekteByArt(Integer artid) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Objekt.class).createAlias("adresse", "betreiber")
				.createAlias("objektarten", "art").add(Restrictions.eq("art.id", artid)).addOrder(Order.asc("inaktiv"))
				.addOrder(Order.asc("betreiber.betrname")).addOrder(Order.asc("art.objektart"));

		return new DatabaseAccess().executeCriteriaToList(detachedCriteria, new Objekt());
	}

	/**
	 * Cascade a priority to all objects from the same Adresse and Lage
	 * 
	 * @param prioritaet
	 *            String
	 * @param basisObjekt
	 *            Objekt
	 * @return <code>true</code> if every merge was successful, <code>false</code>
	 *         otherwise
	 */
	public static Boolean cascadePriority(String prioritaet, Objekt basisObjekt) {
		Boolean result = true;
		List<Objekt> list = new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Objekt.class)
					.add(Restrictions.eq("betreiberid", basisObjekt.getBetreiberid()))
					.add(Restrictions.eq("standortid", basisObjekt.getStandortid())),
				new Objekt());
		for (Objekt objekt : list) {
			objekt.setPrioritaet(prioritaet);
			result = result && objekt.merge();
		}
		return result;
	}

	/**
	 * Get a list of all priorities. The list contains an array with
	 * <code>Standort</code>, <code>Adresse</code>, <code>String</code> (priority) and
	 * <code>Sachbearbeiter</code>
	 * 
	 * @return <code>List&lt;?&gt;</code>
	 */
	public static List<?> getObjektsWithPriority() {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Objekt.class)
						.add(Restrictions.eq("inaktiv", false))
						.add(Restrictions.eq("deleted", false))
						.add(Restrictions.isNotNull("prioritaet"))
						.add(Restrictions.isNotNull("sachbearbeiter"))
						.setProjection(Projections.distinct(Projections.projectionList()
								.add(Projections.property("standortid"))
								.add(Projections.property("prioritaet"))
								.add(Projections.property("sachbearbeiter"))))
						.addOrder(Order.asc("prioritaet"))
						.addOrder(Order.asc("standortid")),
				new Objekt());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Objektarten */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Objektarten[] objektarten = null;

	/**
	 * Get all Objektarten and sort them by their name
	 * 
	 * @return <code>Objektarten[]</code>
	 */
	public static Objektarten[] getObjektarten() {
		if (DatabaseBasisQuery.objektarten == null) {
			DatabaseBasisQuery.objektarten = DatabaseQuery.getOrderedAll(new Objektarten(), "objektart")
					.toArray(new Objektarten[0]);
		}
		return DatabaseBasisQuery.objektarten;
	}

	/**
	 * Get all Objektarten and sort them by their name and kind
	 * 
	 * @return <code>Objektarten[]</code>
	 */
	public static List<Objektarten> getObjektartenlist() {
		List<Objektarten> objektartenlist = new DatabaseAccess().executeCriteriaToList(DetachedCriteria
				.forClass(Objektarten.class).addOrder(Order.asc("abteilung")).addOrder(Order.asc("objektart")),
				new Objektarten());
		return objektartenlist;
	}

	/**
	 * Get next id for new Objektarten
	 * 
	 * @return <code>ObjektartenID</code>
	 */
	public static Integer newObjektartenID() {
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
				DetachedCriteria.forClass(Objektarten.class).setProjection(Property.forName("id").max()),
				new Integer(0));
		return id + 1;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Objektchrono */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Go throw the Objektchrono Set and grep the earliest chrono.
	 * 
	 * @param objekt
	 *            Objekt
	 * @return Objektchrono
	 */
	public static Timestamp getLastChronoDateForObjekt(Objekt objekt) {

		return new DatabaseAccess().executeCriteriaToUniqueResult(DetachedCriteria.forClass(Objektchrono.class)
				.add(Restrictions.eq("objekt", objekt)).setProjection(Projections.distinct(Projections.max("datum"))),
				new Timestamp(0));
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package indeinl : class Anh49Abfuhr */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Go throw the Anh49Abfuhr Set and grep the earliest abfuhrdatum.
	 * 
	 * @param objekt
	 *            Anh49Fachdaten
	 * @return Anh49Abfuhr
	 */
	public static Timestamp getLastAbfuhrDateForObjekt(Anh49Fachdaten objekt) {

		return new DatabaseAccess().executeCriteriaToUniqueResult(
				DetachedCriteria.forClass(Anh49Abfuhr.class).add(Restrictions.eq("anh49Fachdaten", objekt))
						.setProjection(Projections.distinct(Projections.max("abfuhrdatum"))),
				new Timestamp(0));
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package indeinl : class Anh49Abfuhr */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Go throw the Anh49Abfuhr Set and grep the earliest naechsteabfuhr.
	 * 
	 * @param objekt
	 *            Anh49Fachdaten
	 * @return Anh49Abfuhr
	 */
	public static Timestamp getNextAbfuhrDateForObjekt(Anh49Fachdaten objekt) {

		return new DatabaseAccess()
				.executeCriteriaToUniqueResult(
						DetachedCriteria.forClass(Anh49Abfuhr.class).add(Restrictions.eq("anh49Fachdaten", objekt))
								.setProjection(Projections.distinct(Projections.max("naechsteabfuhr"))),
						new Timestamp(0));
	}

	// TODO: This may be replaced with objekt.getObjektchonos() if we find
	// an easy way to load them as they are lazy loaded.
	public static List<Objektchrono> getChronos(Objekt objekt) {
		return new DatabaseAccess().executeCriteriaToList(DetachedCriteria.forClass(Objektchrono.class)
				.add(Restrictions.eq("objekt", objekt)).addOrder(Order.asc("datum")), new Objektchrono());
	}

	public static List<Objektchrono> getAllChronos(Objekt objekt) {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Objektchrono.class)
						.createAlias("objekt", "objekt")
						.add(Restrictions.eq("objekt.betreiberid", objekt.getBetreiberid()))
						.add(Restrictions.eq("objekt.standortid", objekt.getStandortid()))
						.addOrder(Order.asc("datum")),
				new Objektchrono());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Objektverknuepfung */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Liefert alle verknuepften Objekte zu einem bestimmten Objekt.
	 * 
	 * @param objekt
	 *            Das Objekt.
	 * @return Eine Liste mit Objekten.
	 */
	public static List<Objektverknuepfung> getLinkedObjekts(Objekt objekt) {
		return new DatabaseAccess().executeCriteriaToList(DetachedCriteria.forClass(Objektverknuepfung.class)
				.add(Restrictions.or(Restrictions.eq("objektByIstVerknuepftMit", objekt),
						Restrictions.eq("objektByObjekt", objekt))),
				new Objektverknuepfung());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Sachbearbeiter */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Sachbearbeiter[] sachbearbeiter = null;

	/**
	 * Get a sorted array of all enabled Sachbearbeiter
	 * 
	 * @return <code>Sachbearbeiter[]</code>
	 */
	public static Sachbearbeiter[] getEnabledSachbearbeiter() {
		if (DatabaseBasisQuery.sachbearbeiter == null) {
			DatabaseBasisQuery.sachbearbeiter = new DatabaseAccess().executeCriteriaToArray(DetachedCriteria
					.forClass(Sachbearbeiter.class).add(Restrictions.eq("enabled", true)).addOrder(Order.asc("name")),
					new Sachbearbeiter[0]);
		}
		return DatabaseBasisQuery.sachbearbeiter;
	}

	/**
	 * Get the current Sachbearbeiter.
	 * 
	 * @return <code>Sachbearbeiter</code>
	 */
	public static Sachbearbeiter getCurrentSachbearbeiter() {
		return Sachbearbeiter.findByKennummer(SettingsManager.getInstance().getSetting("auik.prefs.lastuser"));
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Sachbearbeiter */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Sachbearbeiter and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Parameter</code>
	 */
	public static List<Sachbearbeiter> getSachbearbeiterlist() {
		List<Sachbearbeiter> sachbearbeiterlist = new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Sachbearbeiter.class).addOrder(Order.asc("name")), new Sachbearbeiter());
		return sachbearbeiterlist;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisLage */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Check if a location already exists
	 * 
	 * @param strasse
	 *            The street
	 * @param hausnr
	 *            The house number
	 * @param zusatz
	 *            Addition to the house number
	 * @return <code>true</code>, if the given location exists, <code>false</code>
	 *         otherwise
	 */
	public static boolean basisStandortExists(String strasse, Integer hausnr, String zusatz) {
		return (!(new DatabaseAccess()
				.executeCriteriaToList(DetachedCriteria.forClass(Adresse.class).add(Restrictions.eq("strasse", strasse))
						.add(Restrictions.eq("hausnr", hausnr))
						.add(DatabaseAccess.getRestrictionsEqualOrNull("hausnrzus", zusatz)), new Adresse())
				.isEmpty()));
	}

	/**
	 * Returns a List of Adresse objects for the given parameters Output format is
	 * List<[Adresse]>
	 * 
	 * @param Name
	 *            String
	 * @param strasse
	 *            String
	 * @param hausnr
	 *            Integer (-1: all)
	 * @param ort
	 *            String
	 * @return <code>List&lt;Adresse[]&gt;</code>
	 */

	public static List<Adresse> findBetreiber(String name, String strasse, Integer hausnr, String ort) {
		// Check which parameters are set
		boolean bName = (name != null && name.length() > 0);
		boolean bStrasse = (strasse != null && strasse.length() > 0);
		boolean bHausnr = (hausnr != null && hausnr != -1);
		boolean bOrt = (ort != null && ort.length() > 0);
		String str = strasse.toLowerCase();
		str = str.replace("'", "''");

		String query = "SELECT DISTINCT adresse " + "FROM objekt as obj JOIN obj.adresseByBetreiberid adresse";
		if (bName || bStrasse || bHausnr || bOrt) {
			query += " WHERE ";
			if (bName) {
				query += "LOWER(adresse.betrname) like '" + name.toLowerCase() + "%' AND ";
			}
			if (bStrasse) {
				query += "LOWER(adresse.strasse) like '" + strasse.toLowerCase() + "%' AND ";
			}
			if (bHausnr) {
				query += "adresse.hausnr = " + hausnr + " AND ";
			}
			if (bOrt) {
				query += "LOWER(adresse.ort) like '" + ort.toLowerCase() + "%' AND ";
			}

			query += "adresse.deleted = false ";

			query += "ORDER BY adresse.strasse ASC, adresse.hausnr ASC, adresse.hausnrzus ASC, adresse.betrname ASC";
		}
		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	/**
	 * Returns a List of Adresse objects for the given parameters Output format is
	 * List<[Adresse]>
	 * 
	 * @param Name
	 *            String
	 * @param strasse
	 *            String
	 * @param hausnr
	 *            Integer (-1: all)
	 * @param ort
	 *            String
	 * @return <code>List&lt;Adresse[]&gt;</code>
	 */

	public static List<Adresse> findAdressen(String name, String strasse, Integer hausnr, String ort) {
		// Check which parameters are set
		boolean bName = (name != null && name.length() > 0);
		boolean bStrasse = (strasse != null && strasse.length() > 0);
		boolean bHausnr = (hausnr != null && hausnr != -1);
		boolean bOrt = (ort != null && ort.length() > 0);
		String str = strasse.toLowerCase();
		str = str.replace("'", "''");

		String query = "SELECT DISTINCT adresse " + "FROM Objekt as obj JOIN obj.adresseByStandortid adresse";
		if (bName || bStrasse || bHausnr || bOrt) {
			query += " WHERE ";
			if (bName) {
				query += "LOWER(adresse.betrname) like '" + name.toLowerCase() + "%' AND ";
			}
			if (bStrasse) {
				query += "LOWER(adresse.strasse) like '" + strasse.toLowerCase() + "%' AND ";
			}
			if (bHausnr) {
				query += "adresse.hausnr = " + hausnr + " AND ";
			}
			if (bOrt) {
				query += "LOWER(adresse.ort) like '" + ort.toLowerCase() + "%' AND ";
			}

			query += "adresse.deleted = false ";

			query += "ORDER BY adresse.strasse ASC, adresse.hausnr ASC, adresse.hausnrzus ASC, adresse.betrname ASC";
		}
		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	/**
	 * Returns a List of all Adresse and Standort objects that are 
	 * connected Output format is
	 * List<[Adresse][Standort]>
	 * 
	 * @param strasse
	 *            String
	 * @param hausnr
	 *            Integer (-1: all)
	 * @param ort
	 *            String
	 * @return <code>List&lt;Object[]&gt;</code>
	 */
	public static List<Object[]> findInhaber(String strasse, Integer hausnr, String ort) {
		// Check which parameters are set
		boolean bStrasse = (strasse != null && strasse.length() > 0);
		boolean bHausnr = (hausnr != null && hausnr != -1);
		boolean bOrt = (ort != null && ort.length() > 0);
		String str = strasse.toLowerCase();
		str = str.replace("'", "''");
	
		String query = "SELECT i.* "
				+ "FROM basis.inhaber i, basis.adresse a "
				+ "WHERE i.adresseid = a.id";
		if (bStrasse || bHausnr || bOrt) {
			query += " AND ";
			if (bStrasse) {
				query += " lower(a.strasse) like '" + str + "%' ";
			}
			if (hausnr != null && hausnr != -1) {
				if (bStrasse) {
					query += " AND ";
				}
				query += " a.hausnr = " + hausnr;
			}
			if (bOrt) {
				if (bStrasse || bHausnr) {
					query += " AND ";
				}
				query += " lower(a.ort) like '" + ort.toLowerCase() + "%' ";
			}
			query += " AND a._deleted = false";
		}
		query += " ORDER BY a.strasse ASC, a.hausnr ASC, a.hausnrzus ASC NULLS FIRST;";
		SQLQuery q = HibernateSessionFactory.currentSession().createSQLQuery(query);
		q.addEntity("i", Inhaber.class);
		return q.list();
	}

	public static List<Adresse> findStandorte(String strasse, Integer hausnr, String ort) {
		// Check which parameters are set
		boolean bStrasse = (strasse != null && strasse.length() > 0);
		boolean bHausnr = (hausnr != null && hausnr != -1);
		boolean bOrt = (ort != null && ort.length() > 0);
		String str = strasse.toLowerCase();
		str = str.replace("'", "''");

		String query = "SELECT DISTINCT a "
				+ "FROM Adresse a, Inhaber i, Standort s "
				+ "WHERE a.id = i.adresse AND i.id = s.inhaber";
		if (bStrasse || bHausnr || bOrt) {
			query += " AND ";
			if (bStrasse) {
				query += "LOWER(a.strasse) like '" + str + "%' ";
			}
			if (hausnr != null && hausnr != -1) {
				if (bStrasse) {
					query += " AND ";
				}
				query += " a.hausnr = " + hausnr;
			}
			if (bOrt) {
				if (bStrasse || bHausnr) {
					query += " AND ";
				}
				query += " LOWER(a.ort) like '" + ort.toLowerCase() + "%' ";
			}
			query += " AND a.deleted = false";
		}
		query += " ORDER BY a.strasse ASC, a.hausnr ASC, a.hausnrzus ASC";
		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	public static List<Standort> chooseStandort(String strasse, Integer hausnr, String ort) {
		// Check which parameters are set
		boolean bStrasse = (strasse != null && strasse.length() > 0);
		boolean bHausnr = (hausnr != null && hausnr != -1);
		boolean bOrt = (ort != null && ort.length() > 0);
		String str = strasse.toLowerCase();
		str = str.replace("'", "''");

		String query = "SELECT s "
				+ "FROM Adresse a, Inhaber i, Standort s "
				+ "WHERE a.id = i.adresse AND i.id = s.inhaber";
		if (bStrasse || bHausnr || bOrt) {
			query += " AND ";
			if (bStrasse) {
				query += "LOWER(a.strasse) like '" + str + "%' ";
			}
			if (hausnr != null && hausnr != -1) {
				if (bStrasse) {
					query += " AND ";
				}
				query += " a.hausnr = " + hausnr;
			}
			if (bOrt) {
				if (bStrasse || bHausnr) {
					query += " AND ";
				}
				query += " LOWER(a.ort) like '" + ort.toLowerCase() + "%' ";
			}
			query += " AND a.deleted = false";
		}
		query += " ORDER BY a.strasse ASC, a.hausnr ASC, a.hausnrzus ASC";
		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	/**
	 * Returns a List of Adresse objects matching the given parameters and having a
	 * connection to a BasisMapAdresseLage object
	 * 
	 * Output format is List<[Adresse]>
	 * 
	 * @param search
	 *            String Betreibername
	 * @param property
	 *            String
	 * @return <code>List&lt;Adresse[]&gt;</code>
	 */

	public static List<Standort> findStandorte(String search, String property) {

		String query = "SELECT s "
				+ "FROM Adresse a, Inhaber i, Standort s "
				+ "WHERE i.adresse = a.id AND s.inhaber = i.id";

		query += " AND ";

		query += "LOWER(i.name) like '" + search.toLowerCase() + "%' ";

		query += " AND ";

		query += "a.deleted = false";

		query += " ORDER BY a.strasse ASC, a.hausnr ASC, a.hausnrzus ASC, i.name ASC";
		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	/**
	 * Returns a List of all Adresse objects matching the given parameters
	 * 
	 * Output format is List<[Adresse]>
	 * 
	 * @param search
	 *            String Betreibername
	 * @param property
	 *            String
	 * @return <code>List&lt;Adresse[]&gt;</code>
	 */

	public static List<Adresse> findAdressen(String search, String property) {

		String query = "SELECT inh " + "FROM Adresse adresse, Inhaber inh";

		query += " WHERE inh.adresse = adresse AND ";

		query += "LOWER(inh.name) like '" + search.toLowerCase() + "%' AND adresse.deleted = false";

		query += " ORDER BY adresse.strasse ASC, adresse.hausnr ASC, adresse.hausnrzus ASC, inh.name ASC";
		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	/**
	 * Returns a List of all Adresse objects matching the given parameters
	 * 
	 * Output format is List<[Adresse]>
	 * 
	 * @param search
	 *            String Betreibername
	 * @param property
	 *            String
	 * @return <code>List&lt;Adresse[]&gt;</code>
	 */

	public static List<Inhaber> findAdressen(String name, String strasse, Integer hausnr, String ort, String property) {

		boolean bName = (name != null && name.length() > 0);
		boolean bStrasse = (strasse != null && strasse.length() > 0);
		boolean bHausnr = (hausnr != null && hausnr != -1);
		boolean bOrt = (ort != null && ort.length() > 0);

		String query = "SELECT i FROM Adresse a, Inhaber i WHERE i.adresse = a";
		if (bName || bStrasse || bHausnr || bOrt) {
			query += " AND ";
			if (bName && property == null) {
				query += "(LOWER(i.betrname) like '" + name.toLowerCase() + "%' OR LOWER(i.vorname) like '"
						+ name.toLowerCase() + "%' OR LOWER(inh.anrede) like '" + name.toLowerCase()
						+ "%' OR LOWER(i.namezus) like '" + name.toLowerCase() + "') AND ";
			} else if (bName && property.equals("anrede")) {
				query += "LOWER(i.anrede) like '" + name.toLowerCase() + "%' AND ";
			} else if (bName && property.equals("vorname")) {
				query += "LOWER(i.vorname) like '" + name.toLowerCase() + "%' AND ";
			} else if (bName && property.equals("name")) {
				query += "LOWER(i.name) like '" + name.toLowerCase() + "%' AND ";
			}

			else if (bName && property.equals("zusatz")) {
				query += "LOWER(i.namezus) like '" + name.toLowerCase() + "%' AND ";
			}
			if (bStrasse) {
				query += "LOWER(a.strasse) like '" + strasse.toLowerCase() + "%' AND ";
			}
			if (bHausnr) {
				query += "a.hausnr = " + hausnr + " AND ";
			}
			if (bOrt) {
				query += "LOWER(a.ort) like '" + ort.toLowerCase() + "%' AND ";
			}

			query += "i.deleted = false ";

			query += "ORDER BY a.strasse ASC, a.hausnr ASC, a.hausnrzus ASC, i.name ASC";
		}
		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	/**
	 * Returns a List of all Adresse objects matching the given parameters
	 * 
	 * Output format is List<[Adresse]>
	 * 
	 * @param search
	 *            String Strasse, Hausnummer, Hausnummerzusatz
	 * @param property
	 *            String
	 * @return <code>List&lt;Adresse[]&gt;</code>
	 */
	
	public static List<Adresse> findAdressen(String strasse, Integer hausnr, String zusatz, String plz) {

		String query = "SELECT adresse FROM Adresse adresse WHERE ";
		query += "LOWER(adresse.strasse) like '" + strasse.toLowerCase() + "%' AND ";
		query += "adresse.hausnr = " + hausnr + " AND ";
		query += "adresse.hausnrzus = '" + zusatz + "' AND ";
		query += "adresse.plz = '" + plz + "' AND ";
		query += "adresse.deleted = false ";

		List<Adresse> list = HibernateSessionFactory.currentSession().createQuery(query).list();
		return list;
	}
	
	public static List<Adresse> findAdressen(String strasse, Integer hausnr) {
	
		boolean bStrasse = (strasse != null && strasse.length() > 0);
		boolean bHausnr = (hausnr != null && hausnr != -1);
	
		String query = "SELECT adresse " + "FROM Adresse adresse";
		if (bStrasse || bHausnr) {
			query += " WHERE ";
			if (bStrasse) {
				query += "LOWER(adresse.strasse) like '" + strasse.toLowerCase() + "%' AND ";
			}
			if (bHausnr) {
				query += "adresse.hausnr = " + hausnr + " AND ";
			}
			
			query += "adresse.deleted = false ";
					
			query += "ORDER BY adresse.strasse ASC, adresse.hausnr ASC, adresse.hausnrzus ASC ";
		}
		List<Adresse> list = HibernateSessionFactory.currentSession().createQuery(query).list();
		return list;
	}

	private static String[] entwaesserungsgebiete = null;

	/**
	 * Get a list of all Entwässerungsgebiet Ids.
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getEntwaesserungsgebiete() {
		if (DatabaseBasisQuery.entwaesserungsgebiete == null) {
			DatabaseBasisQuery.entwaesserungsgebiete = new DatabaseAccess().executeCriteriaToArray(DetachedCriteria
					.forClass(Adresse.class).setProjection(Projections.distinct(Projections.property("entgebid")))
					.addOrder(Order.asc("entgebid")), new String[0]);
		}
		return DatabaseBasisQuery.entwaesserungsgebiete;
	}

	public static List<TabStreets> findStandorte(String strasse) {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(TabStreets.class).add(Restrictions.eq("name", strasse))
						.addOrder(Order.asc("hausnr")).addOrder(Order.asc("hausnrZusatz").nulls(NullPrecedence.FIRST)),
				new TabStreets());
	}

	public static Standort findStandort(Inhaber inh) {
		return new DatabaseAccess().executeCriteriaToUniqueResult(
				DetachedCriteria.forClass(Standort.class).add(Restrictions.eq("inhaber", inh))
						.add(Restrictions.eq("bezeichnung", "Adresse")),
				new Standort());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisTabStreets */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static List<TabStreets> getTabStreetslist(MatchMode mm) {

		DetachedCriteria dc = DetachedCriteria.forClass(TabStreets.class).addOrder(Order.asc("name"));

		List<TabStreets> tabstreetslist = new DatabaseAccess().executeCriteriaToList(dc, new TabStreets());

		return tabstreetslist;

	}

	/**
	 * Get BasisTabStreets
	 * 
	 * @return <code>Eine Liste aller Strassennamen</code>
	 */
	public static String[] getTabStreets() {

		List<String> basisAllTabStreets = getAllTabStreetslist();

		String[] tabStreets = new String[basisAllTabStreets.size()];
		return tabStreets = basisAllTabStreets.toArray(tabStreets);

	}

	/**
	 * Get all BasisTabStreets and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Stassen</code>
	 */
	public static List<String> getAllTabStreetslist() {

		String query = "SELECT DISTINCT name " + "FROM TabStreets ORDER BY name";

		return HibernateSessionFactory.currentSession().createQuery(query).list();

	}

	/**
	 * Get all BasisTabStreets and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Stassen</code>
	 */
	public static String getTabStreet(String search) {

		String query = "SELECT DISTINCT name "
				+ "FROM TabStreets "
				+ "WHERE LOWER(name) like '" + search.toLowerCase() + "%'";
		
		List strasse = HibernateSessionFactory.currentSession().createQuery(query).list();

		return (strasse.isEmpty() ? null : strasse.get(0).toString());

	}

	/**
	 * Get all BasisTabStreets and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Stassen</code>
	 */
	public static TabStreets getSingleTabStreet(String name, Integer hausnr, String hausnrzus) {

		String query = "SELECT t "
				+ "FROM TabStreets t "
				+ "WHERE name like '" + name + "' ";
		query += "AND hausnr = " + hausnr;
		if (hausnrzus != null) {
			query += "AND hausnr_zusatz = '" + hausnrzus + "' ";
		} else {
			query += "AND hausnr_zusatz IS NULL";
		}
		
		List tabStreet = HibernateSessionFactory.currentSession().createQuery(query).list();

		return (TabStreets) tabStreet.iterator().next();

	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class Strassen */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Strassen sorted by strasse
	 * 
	 * @return <code>Strassen[]</code>
	 */
	public static String[] getAllStrassen() {

		return getAllTabStreetslist().toArray(new String[0]);
	}

	/**
	 * Get the first matching BasisStrasse for the search String
	 * 
	 * @param search
	 *            String
	 * @return <code>Strassen</code>
	 */
//	public static String findStrasse(String search) {
//
//		strasse = 
//		
//		return (list.isEmpty() ? null : list.get(0));
//	}

	/* ********************************************************************** */
	/* Queries for package ATL */
	/* ********************************************************************** */

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Einheiten */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Einheiten and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Einheiten</code>
	 */
	public static List<Einheiten> getEinheitenlist() {
		List<Einheiten> strassenlist = new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Einheiten.class).addOrder(Order.asc("id")), new Einheiten());
		return strassenlist;

	}

	/**
	 * Get next id for new Einheiten
	 * 
	 * @return <code>EinheitenID</code>
	 */
	public static Integer newEinheitenID() {
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
				DetachedCriteria.forClass(Einheiten.class).setProjection(Property.forName("id").max()), new Integer(0));
		return id + 1;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Parameter */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Parameter and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Parameter</code>
	 */
	public static List<Parameter> getParameterlist() {
		List<Parameter> parameterlist = new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Parameter.class).addOrder(Order.asc("id")), new Parameter());
		return parameterlist;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Klaeranlage */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Klaeranlage and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Parameter</code>
	 */
	public static List<Klaeranlage> getKlaeranlagelist() {
		List<Klaeranlage> klaeranlagenlist = new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Klaeranlage.class).addOrder(Order.asc("id")), new Klaeranlage());
		return klaeranlagenlist;
	}

	/**
	 * Get next id for new Klaeranlage
	 * 
	 * @return <code>KlaeranlageID</code>
	 */
	public static Integer newKlaeranlageID() {
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
				DetachedCriteria.forClass(Klaeranlage.class).setProjection(Property.forName("id").max()),
				new Integer(0));
		return id + 1;
	}

	/* ********************************************************************** */
	/* Queries for package VAWS */
	/* ********************************************************************** */

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS : class VawsWasserschutzgebiete */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all VawsWasserschutzgebiete and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Wasserschutzgebiete</code>
	 */
	public static List<Wassereinzugsgebiet> getWasserschutzgebietelist() {
		List<Wassereinzugsgebiet> wsglist = new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Wassereinzugsgebiet.class).addOrder(Order.asc("id")),
				new Wassereinzugsgebiet());
		return wsglist;

	}

	/**
	 * Get next id for new Wassereinzugsgebiet
	 * 
	 * @return <code>WassereinzugsgebietID</code>
	 */
	public static Integer newWSGID() {
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
				DetachedCriteria.forClass(Wassereinzugsgebiet.class).setProjection(Property.forName("id").max()),
				new Integer(0));
		return id + 1;
	}
	
	/**
	 * Get all Anhangs and sort them by their anhang_id as integer
	 * 
	 * @return <code>Eine Liste aller Anhangs</code>
	 */

	public static List<Anhang> allActiveAnhangs() {

		String query = "FROM Anhang WHERE anh_gueltig_bis IS NULL " + 
				"ORDER BY CAST (NULLIF(regexp_replace(anhang_id, '\\D', '', 'g'), '') as int) asc";

		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}
	
	/**
	 * Get all Anhangs and sort them by their anhang_id as integer
	 * 
	 * @return <code>Eine Liste aller Anhangs</code>
	 */

	public static List<Abaverfahren> getNwAbaVerfahrens() {

		String query = "FROM Abaverfahren WHERE bezeichnung LIKE '34.%' " + 
				"ORDER BY bezeichnung asc";

		return HibernateSessionFactory.currentSession().createQuery(query).list();
	}

	/* ********************************************************************** */
	/* Queries for package Direkteinleiter */
	/* ********************************************************************** */

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS : class VawsWasserschutzgebiete */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Durchsucht Fachdaten nach einem bestimmten Aktenzeichen und gibt
	 * das Ergebnis als List zurück
	 * 
	 * @param search
	 *            String
	 * @return <code>List&lt;Fachdaten&gt;</code>
	 */
	public static List<Wasserrecht> findAktenzeichen(String search) {

		if (search == null || search == "") {
			return new DatabaseAccess().executeCriteriaToList(
					DetachedCriteria.forClass(Wasserrecht.class),
					new Wasserrecht());
		} else {
			return new DatabaseAccess().executeCriteriaToList(
					DetachedCriteria.forClass(Wasserrecht.class).add(
							Restrictions.like("aktenzeichen", "%" + search + "%")),
					new Wasserrecht());
		}
	}


	private static MapElkaGewkennz[] mapElkaGewkennz = null;

	/**
	 * Get an array with all <code>MapElkaGewkennz</code>en
	 * 
	 * @return <code>MapElkaGewkennz[]</code>
	 */
	public static MapElkaGewkennz[] getMapElkaGewkennz() {
		if (DatabaseBasisQuery.mapElkaGewkennz == null) {
			DatabaseBasisQuery.mapElkaGewkennz = DatabaseQuery.getOrderedAll(new MapElkaGewkennz()).toArray(new MapElkaGewkennz[0]);
		}
		return DatabaseBasisQuery.mapElkaGewkennz;
	}

	public static Integer[] getMapElkaGewkennzArray() {
		
		List<Integer> mapElkaGewkennzList = getMapElkaGewkennzList();

		Integer[] mapElkaGew = new Integer[mapElkaGewkennzList.size()];
		return mapElkaGew = mapElkaGewkennzList.toArray(mapElkaGew);
		
	}

	/**
	 * Get all MapElkaGewkennz and sort them by their gewkz
	 * 
	 * @return <code>Eine Liste aller Gewaesser</code>
	 */
	public static List<Integer> getMapElkaGewkennzList() {

		String query = "SELECT gewkz " + "FROM MapElkaGewkennz ORDER BY gewkz";

		return HibernateSessionFactory.currentSession().createQuery(query).list();

	}
}
