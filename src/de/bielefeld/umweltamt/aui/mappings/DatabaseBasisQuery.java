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
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.hibernate.Query;
import org.hibernate.Session;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisAdresse;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisGemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektchrono;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisOrte;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisLage;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abfuhr;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWassereinzugsgebiete;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * This is a service class for all custom queries from the basis package.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseBasisQuery extends DatabaseIndeinlQuery
{

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	/* ********************************************************************** */
	/* Queries for package BASIS */
	/* ********************************************************************** */

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisAdresse */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all BasisAdresse with a given search string in the selected
	 * property. <br>
	 * If property is <code>null</code>, we search in all three properties.
	 * 
	 * @param property
	 *            Name of the property
	 * @param search
	 *            Search string
	 * @return <code>List&lt;BasisAdresse&gt;</code> List of BasisAdresse
	 *         with the given search string in the given property
	 */
	public static List<BasisAdresse> getBasisAdresse(String property,
		String search)
	{

		String modSearch = search.toLowerCase().trim() + "%";
		log.debug("Suche nach '" + modSearch + "' (" + property + ").");

		DetachedCriteria criteria = DetachedCriteria
				.forClass(BasisAdresse.class).addOrder(Order.asc("betrname"))
				.addOrder(Order.asc("betrnamezus"));

		if (property == null)
		{
			criteria.add(Restrictions.or(Restrictions.ilike("betrname",
															modSearch),
											Restrictions.or(
															Restrictions.ilike("betranrede", modSearch),
															Restrictions.ilike("betrnamezus", modSearch))));
		}
		else if (property.equals("name"))
		{
			criteria.add(Restrictions.ilike("betrname", modSearch));
		}
		else if (property.equals("anrede"))
		{
			criteria.add(Restrictions.ilike("betranrede", modSearch));
		}
		else if (property.equals("zusatz"))
		{
			criteria.add(Restrictions.ilike("betrnamezus", modSearch));
		}
		else
		{
			log.debug("Something went really wrong here...");
		}

		return new DatabaseAccess().executeCriteriaToList(criteria,
															new BasisAdresse());
	}

	/**
	 * Get a nicely formatted street and house number for a BasisAdresse
	 * 
	 * @param betreiber
	 *            BasisAdresse
	 * @return String
	 */
	public static String getBetriebsgrundstueck(BasisAdresse betreiber)
	{
		String strasse = betreiber.getStrasse();
		Integer hausnr = betreiber.getHausnr();
		String zusatz = betreiber.getHausnrzus();
		return (strasse != null ? strasse + " " : "")
				+ (hausnr != null ? hausnr.toString() : "")
				+ (zusatz != null ? zusatz : "");
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisGemarkung */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static BasisGemarkung[] gemarkungen = null;

	/**
	 * Get an array with all <code>BasisGemarkung</code>en
	 * 
	 * @return <code>BasisGemarkung[]</code>
	 */
	public static BasisGemarkung[] getBasisGemarkungen()
	{
		if (DatabaseBasisQuery.gemarkungen == null)
		{
			DatabaseBasisQuery.gemarkungen = DatabaseQuery.getOrderedAll(
																			new BasisGemarkung())
					.toArray(new BasisGemarkung[0]);
		}
		return DatabaseBasisQuery.gemarkungen;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisGemarkung */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all BasisGemarkungen and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Gemarkungen</code>
	 */
	public static List<BasisGemarkung> getGemarkungenlist()
	{
		List<BasisGemarkung> gemarkungenlist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(BasisGemarkung.class)
												.addOrder(Order.asc("gemarkung")),
										new BasisGemarkung());
		return gemarkungenlist;
	}

	/**
	 * Get next id for new BasisGemarkung
	 * 
	 * @return <code>BasisGemarkung</code>
	 */
	public static Integer newGemarkungID()
	{
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
																		DetachedCriteria.forClass(BasisGemarkung.class)
																				.setProjection(
																								Property.forName("id")
																										.max()),
																		new Integer(0));
		return id + 1;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisObjekt */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Liefert eine Liste von Objekten, die einem bestimmten Betreiber
	 * zugeordnet sind.
	 * 
	 * @param betreiber
	 *            Der Betreiber.
	 * @param abteilung
	 *            Die Abteilung, wenn nach ihr gefiltert werden soll, sonst
	 *            <code>null</code>.
	 * @return Eine Liste von BasisObjekten dieses Betreibers.
	 */
	public static List<BasisObjekt> getObjekteByBetreiber(
		BasisAdresse betreiber, String abteilung)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(BasisObjekt.class)
				.createAlias("basisStandort", "lage")
				.createAlias("basisObjektarten", "art")
				.add(Restrictions.eq("basisAdresse", betreiber))
				.addOrder(Order.asc("inaktiv"))
				.addOrder(Order.asc("lage.strasse"))
				.addOrder(Order.asc("lage.hausnr"))
				.addOrder(Order.asc("art.objektart"));
		if (abteilung != null)
		{
			detachedCriteria.add(Restrictions.eq("art.abteilung", abteilung));
		}
		return new DatabaseAccess().executeCriteriaToList(detachedCriteria,
															new BasisObjekt());
	}

	/**
	 * Liefert eine Liste von Objekten, die einem bestimmten Standort zugeordnet
	 * sind.
	 * 
	 * @param betr
	 *            Der Standort.
	 * @param abteilung
	 *            Die Abteilung, wenn nach ihr gefiltert werden soll, sonst
	 *            <code>null</code>.
	 * @param artid
	 *            Die Objektart, die (nicht) dargestellt werden soll.
	 * @return Eine Liste von BasisObjekten an diesem Standort.
	 */
	public static List<BasisObjekt> getObjekteByStandort(
		BasisAdresse standort, String abteilung, Integer artid,
		Boolean matchArtId)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(BasisObjekt.class)
				.createAlias("basisAdresse", "betreiber")
				.createAlias("basisObjektarten", "art")
				.add(Restrictions.eq("basisStandort", standort))
				.addOrder(Order.asc("inaktiv"))
				.addOrder(Order.asc("betreiber.betrname"))
				.addOrder(Order.asc("art.objektart"))
				.addOrder(Order.asc("beschreibung"));
		if (abteilung != null)
		{
			detachedCriteria.add(Restrictions.eq("art.abteilung", abteilung));
		}
		if (artid != null)
		{
			if (matchArtId)
			{
				detachedCriteria.add(Restrictions.eq("art.id", artid));
			}
			else
			{
				detachedCriteria.add(Restrictions.ne("art.id", artid));
			}
		}
		return new DatabaseAccess().executeCriteriaToList(detachedCriteria,
															new BasisObjekt());
	}

	/**
	 * Liefert eine Liste von Objekten einer Objektart sind.
	 * 
	 * @param artid
	 *            Die Objektart, die (nicht) dargestellt werden soll.
	 * @return Eine Liste von BasisObjekten an diesem Standort.
	 */
	public static List<BasisObjekt> getObjekteByArt(Integer artid)
	{
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(BasisObjekt.class)
				.createAlias("basisAdresse", "betreiber")
				.createAlias("basisObjektarten", "art")
				.add(Restrictions.eq("art.id", artid))
				.addOrder(Order.asc("inaktiv"))
				.addOrder(Order.asc("betreiber.betrname"))
				.addOrder(Order.asc("art.objektart"));

		return new DatabaseAccess().executeCriteriaToList(detachedCriteria,
															new BasisObjekt());
	}

	/**
	 * Cascade a priority to all objects from the same BasisAdresse and
	 * BasisLage
	 * 
	 * @param prioritaet
	 *            String
	 * @param basisObjekt
	 *            BasisObjekt
	 * @return <code>true</code> if every merge was successful,
	 *         <code>false</code> otherwise
	 */
	public static Boolean cascadePriority(String prioritaet,
		BasisObjekt basisObjekt)
	{
		Boolean result = true;
		List<BasisObjekt> list = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria
												.forClass(BasisObjekt.class)
												.add(Restrictions.eq("basisAdresse",
																		basisObjekt.getBasisAdresse()))
												.add(Restrictions.eq("basisLage",
																		basisObjekt.getBasisLage())),
										new BasisObjekt());
		for (BasisObjekt objekt : list)
		{
			objekt.setPrioritaet(prioritaet);
			result = result && objekt.merge();
		}
		return result;
	}

	/**
	 * Get a list of all priorities. The list contains an array with
	 * <code>BasisLage</code>, <code>BasisAdresse</code>,
	 * <code>String</code> (priority) and <code>BasisSachbearbeiter</code>
	 * 
	 * @return <code>List&lt;?&gt;</code>
	 */
	public static List<?> getObjektsWithPriority()
	{
		return new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria
												.forClass(BasisObjekt.class)
												.add(Restrictions.eq("inaktiv", false))
												.add(Restrictions.eq("deleted", false))
												.add(Restrictions.isNotNull("prioritaet"))
												.add(Restrictions
														.isNotNull("basisSachbearbeiter"))
												.setProjection(
																Projections.distinct(Projections
																		.projectionList()
																		.add(Projections
																				.property("basisLage"))
																		.add(Projections
																				.property("basisAdresse"))
																		.add(Projections
																				.property("prioritaet"))
																		.add(Projections
																				.property("basisSachbearbeiter"))))
												.addOrder(Order.asc("prioritaet"))
												.addOrder(Order.asc("basisLage"))
												.addOrder(Order.asc("basisAdresse")),
										new BasisObjekt());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisObjektarten */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static BasisObjektarten[] objektarten = null;

	/**
	 * Get all BasisObjektarten and sort them by their name
	 * 
	 * @return <code>BasisObjektarten[]</code>
	 */
	public static BasisObjektarten[] getObjektarten()
	{
		if (DatabaseBasisQuery.objektarten == null)
		{
			DatabaseBasisQuery.objektarten = DatabaseQuery.getOrderedAll(
																			new BasisObjektarten(), "objektart")
					.toArray(
								new BasisObjektarten[0]);
		}
		return DatabaseBasisQuery.objektarten;
	}

	/**
	 * Get all BasisObjektarten and sort them by their name and kind
	 * 
	 * @return <code>BasisObjektarten[]</code>
	 */
	public static List<BasisObjektarten> getObjektartenlist()
	{
		List<BasisObjektarten> objektartenlist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(BasisObjektarten.class)
												.addOrder(Order.asc("abteilung"))
												.addOrder(Order.asc("objektart")),
										new BasisObjektarten());
		return objektartenlist;
	}

	/**
	 * Get next id for new BasisObjektarten
	 * 
	 * @return <code>BasisObjektartenID</code>
	 */
	public static Integer newObjektartenID()
	{
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
																		DetachedCriteria
																				.forClass(BasisObjektarten.class)
																				.setProjection(Property.forName("id")
																						.max()),
																		new Integer(0));
		return id + 1;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisObjektchrono */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Go throw the BasisObjektchrono Set and grep the earliest chrono.
	 * 
	 * @param objekt
	 *            BasisObjekt
	 * @return BasisObjektchrono
	 */
	public static Timestamp getLastChronoDateForObjekt(BasisObjekt objekt)
	{
		// // TODO: When we can load the lazy initialized stuff properly, do
		// this:
		// Set<BasisObjektchrono> chronos = objekt.getBasisObjektchronos();
		// if (!(DatabaseAccess.isInitialized(chronos))) {
		// // TODO: Load the Set
		// }
		// BasisObjektchrono max = null;
		// for (BasisObjektchrono chrono : chronos) {
		// if ((max == null) || max.getDatum().before(chrono.getDatum())) {
		// max = chrono;
		// }
		// }
		// return max;
		return new DatabaseAccess()
				.executeCriteriaToUniqueResult(
												DetachedCriteria
														.forClass(BasisObjektchrono.class)
														.add(Restrictions.eq("basisObjekt", objekt))
														.setProjection(
																		Projections.distinct(Projections
																				.max("datum"))), new Timestamp(
														0));
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
	public static Timestamp getLastAbfuhrDateForObjekt(Anh49Fachdaten objekt)
	{

		return new DatabaseAccess()
				.executeCriteriaToUniqueResult(
												DetachedCriteria
														.forClass(Anh49Abfuhr.class)
														.add(Restrictions.eq("anh49Fachdaten", objekt))
														.setProjection(
																		Projections.distinct(Projections
																				.max("abfuhrdatum"))),
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
	public static Timestamp getNextAbfuhrDateForObjekt(Anh49Fachdaten objekt)
	{

		return new DatabaseAccess()
				.executeCriteriaToUniqueResult(
												DetachedCriteria
														.forClass(Anh49Abfuhr.class)
														.add(Restrictions.eq("anh49Fachdaten", objekt))
														.setProjection(
																		Projections.distinct(Projections
																				.max("naechsteabfuhr"))),
												new Timestamp(0));
	}

	// TODO: This may be replaced with objekt.getBasisObjektchonos() if we find
	// an easy way to load them as they are lazy loaded.
	public static List<BasisObjektchrono> getChronos(BasisObjekt objekt)
	{
		return new DatabaseAccess().executeCriteriaToList(
															DetachedCriteria.forClass(BasisObjektchrono.class)
																	.add(Restrictions.eq("basisObjekt", objekt))
																	.addOrder(Order.asc("datum")),
															new BasisObjektchrono());
	}

	public static List<BasisObjektchrono> getAllChronos(BasisObjekt objekt)
	{
		return new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria
												.forClass(BasisObjektchrono.class)
												.createAlias("basisObjekt", "objekt")
												.add(Restrictions.eq("objekt.basisAdresse",
																		objekt.getBasisAdresse()))
												.add(Restrictions.eq("objekt.basisLage",
																		objekt.getBasisLage()))
												.addOrder(Order.asc("datum")), new BasisObjektchrono());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisObjektverknuepfung */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Liefert alle verknuepften Objekte zu einem bestimmten BasisObjekt.
	 * 
	 * @param objekt
	 *            Das BasisObjekt.
	 * @return Eine Liste mit Objekten.
	 */
	public static List<BasisObjektverknuepfung> getLinkedObjekts(
		BasisObjekt objekt)
	{
		return new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria
												.forClass(BasisObjektverknuepfung.class)
												.add(Restrictions.or(Restrictions
														.eq("basisObjektByIstVerknuepftMit",
															objekt), Restrictions.eq(
																						"basisObjektByObjekt", objekt))),
										new BasisObjektverknuepfung());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisSachbearbeiter */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static BasisSachbearbeiter[] sachbearbeiter = null;

	/**
	 * Get a sorted array of all enabled BasisSachbearbeiter
	 * 
	 * @return <code>BasisSachbearbeiter[]</code>
	 */
	public static BasisSachbearbeiter[] getEnabledSachbearbeiter()
	{
		if (DatabaseBasisQuery.sachbearbeiter == null)
		{
			DatabaseBasisQuery.sachbearbeiter = new DatabaseAccess()
					.executeCriteriaToArray(
											DetachedCriteria
													.forClass(BasisSachbearbeiter.class)
													.add(Restrictions.eq("enabled", true))
													.addOrder(Order.asc("name")),
											new BasisSachbearbeiter[0]);
		}
		return DatabaseBasisQuery.sachbearbeiter;
	}

	/**
	 * Get the current BasisSachbearbeiter.
	 * 
	 * @return <code>BasisSachbearbeiter</code>
	 */
	public static BasisSachbearbeiter getCurrentSachbearbeiter()
	{
		return BasisSachbearbeiter.findByKennummer(SettingsManager.getInstance()
				.getSetting("auik.prefs.lastuser"));
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisSachbearbeiter */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all BasisSachbearbeiter and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Parameter</code>
	 */
	public static List<BasisSachbearbeiter> getSachbearbeiterlist()
	{
		List<BasisSachbearbeiter> sachbearbeiterlist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(BasisSachbearbeiter.class)
												.addOrder(Order.asc("name")),
										new BasisSachbearbeiter());
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
	 * @return <code>true</code>, if the given location exists,
	 *         <code>false</code> otherwise
	 */
	public static boolean basisStandortExists(String strasse, Integer hausnr,
		String zusatz)
	{
		return (!(new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria
												.forClass(BasisAdresse.class)
												.add(Restrictions.eq("strasse", strasse))
												.add(Restrictions.eq("hausnr", hausnr))
												.add(DatabaseAccess.getRestrictionsEqualOrNull(
																								"hausnrzus", zusatz)),
										new BasisAdresse())
				.isEmpty()));
	}

	/**
	 * Find BasisLagee that match the parameters.
	 * 
	 * @param strasse
	 *            String
	 * @param hausnr
	 *            Integer (-1: all)
	 * @param ort
	 *            String
	 * @return <code>List&lt;BasisAdresse&gt;</code>
	 */
	public static List<BasisAdresse> findStandorte(String strasse,
		Integer hausnr, String ort)
	{
        /*
        String qString = "FROM BasisAdresse A WHERE A.betrname is null";
        Query query = HibernateSessionFactory.currentSession().createQuery(qString);
        List<BasisAdresse> result = (List<BasisAdresse>) query.list();
        return result;*/
        
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(BasisAdresse.class).addOrder(Order.asc("strasse"))
				.addOrder(Order.asc("hausnr"))
                .add(Restrictions.isNull("betrname"));
                
		if (strasse != null)
		{
			if (strasse.length() > 0)
			{
				detachedCriteria.add(Restrictions.ilike("strasse", strasse,
														MatchMode.START));
			}
		}

		if (ort != null)
		{
			if (ort.length() > 0)
			{
				detachedCriteria.add(Restrictions.ilike("ort", ort,
														MatchMode.START));
			}
		}

		if (hausnr != -1)
		{
			detachedCriteria.add(Restrictions.eq("hausnr", hausnr));
		}
		return new DatabaseAccess().executeCriteriaToList(detachedCriteria,
															new BasisAdresse());
        
	}

	private static String[] entwaesserungsgebiete = null;

	/**
	 * Get a list of all Entwässerungsgebiet Ids.
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getEntwaesserungsgebiete()
	{
		if (DatabaseBasisQuery.entwaesserungsgebiete == null)
		{
			DatabaseBasisQuery.entwaesserungsgebiete = new DatabaseAccess()
					.executeCriteriaToArray(
											DetachedCriteria.forClass(BasisLage.class)
													.setProjection(
																	Projections.distinct(Projections
																			.property("entgebid"))),
											new String[0]);
		}
		return DatabaseBasisQuery.entwaesserungsgebiete;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package BASIS : class BasisStrassen */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	
    private static String[] strassen = null;
    /**
     * Get all BasisStrassen sorted by strasse
     * @return <code>BasisStrassen[]</code>
     */
    public static BasisStrassen[] getAllStrassen() {

    	List<BasisStrassen> basisAllStrassen = getAllStrassenlist();
    	
        return basisAllStrassen.toArray(new BasisStrassen[0]);
    }
	/**
	 * Get all BasisStrassen and sort them by their name
	 * @return <code>Eine Liste aller Stassen</code>
	 */
	public static List<BasisStrassen> getAllStrassenlist() {
	    List<BasisStrassen> strassenlist = new DatabaseAccess().executeCriteriaToList(
	            DetachedCriteria.forClass(BasisStrassen.class)
	                .addOrder(Order.asc("strasse")),
	            new BasisStrassen());
		return strassenlist;
	
	}
	/**
	 * Get BasisStrassen sorted by strasse
	 * 
	 * @return <code>BasisStrassen[]</code>
	 */
	public static BasisStrassen[] getStrassen(String ort, MatchMode mm)
	{

		List<BasisStrassen> basisStrassen = getStrassenlist(ort, mm);

		return basisStrassen.toArray(new BasisStrassen[0]);
	}

	/**
	 * Get BasisStrassen filtered by plzort and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Stassen</code>
	 */
	public static List<BasisStrassen> getStrassenlist(String ort, MatchMode mm)
	{

		DetachedCriteria dc = DetachedCriteria.forClass(BasisStrassen.class)
				.addOrder(Order.asc("strasse"));

//		if (!StringUtils.isNullOrEmpty(plz))
//		{
//			dc = dc.add(Restrictions.like("plz", plz, mm));
//		}

		if (!StringUtils.isNullOrEmpty(ort))
		{
			dc = dc.add(Restrictions.like("ort", ort, mm));
		}

		List<BasisStrassen> strassenlist = new DatabaseAccess()
				.executeCriteriaToList(dc, new BasisStrassen());

		return strassenlist;

	}

	/**
	 * Get the first matching BasisStrasse for the search String
	 * 
	 * @param search
	 *            String
	 * @return <code>BasisStrassen</code>
	 */
	public static BasisStrassen findStrasse(String search)
	{
		List<BasisStrassen> list = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria
												.forClass(BasisStrassen.class)
												.add(Restrictions.ilike("strasse", search,
																		MatchMode.START))
												.addOrder(Order.asc("strasse")), new BasisStrassen());
		// If we got something, just return the first result
		return (list.isEmpty() ? null : list.get(0));
	}

	/**
	 * Get next id for new BasisStrassen
	 * 
	 * @return <code>BasisStrassenID</code>
	 */
	public static Integer newStrassenID()
	{
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
																		DetachedCriteria.forClass(BasisStrassen.class)
																				.setProjection(
																								Property.forName("id")
																										.max()),
																		new Integer(0));
		return id + 1;
	}

	private static List<BasisOrte> orte = null;

	/**
	 * Get cities sorted by zip code
	 * 
	 * @return <code>BasisStrassen[]</code>
	 */
	public static BasisOrte[] getOrte()
	{

		if (orte == null)
		{
			orte = getOrtelist();
		}

		return orte.toArray(new BasisOrte[0]);
	}

	/**
	 * Get cities sorted by zip code
	 * 
	 * @return <code>Liste aller Orte</code>
	 */
	public static List<BasisOrte> getOrtelist()
	{

		if (orte == null)
		{
			DetachedCriteria dc = DetachedCriteria
					.forClass(BasisStrassen.class)
					.setProjection(Projections.distinct(Projections.projectionList()
							.add(Projections.property("ort"), "ort")
//							.add(Projections.property("plz"), "plz")
							))
					.setResultTransformer(Transformers.aliasToBean(BasisOrte.class))
					.addOrder(Order.asc("ort"))
//					.addOrder(Order.asc("plz"))
					;

			orte = new DatabaseAccess().executeCriteriaToList(dc, new BasisOrte());
		}

		return orte;

	}
	
	private static BasisOrte ort = null;

	/**
	 * Get city for a given street
	 * 
	 * @return <code>Einen Ort</code>
	 */
	public static BasisOrte getOrt(String street)
	{

//		if (ort == null)
		{
			DetachedCriteria dc = DetachedCriteria
					.forClass(BasisStrassen.class)
					.setProjection(Projections.distinct(Projections.projectionList()
							.add(Projections.property("ort"), "ort")
//							.add(Projections.property("plz"), "plz")
							))
					.setResultTransformer(Transformers.aliasToBean(BasisOrte.class))
					.add(Restrictions.ilike("strasse", street,
							MatchMode.START))
					.addOrder(Order.asc("ort"))
//					.addOrder(Order.asc("plz"))
					;

			ort = (BasisOrte) new DatabaseAccess().executeCriteriaToUniqueResult(dc, new BasisOrte());
		}

		return ort;

	}

	/* ********************************************************************** */
	/* Queries for package ATL */
	/* ********************************************************************** */

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class AtlEinheiten */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	

	/**
	 * Get all AtlEinheiten and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Einheiten</code>
	 */
	public static List<AtlEinheiten> getEinheitenlist()
	{
		List<AtlEinheiten> strassenlist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(AtlEinheiten.class)
												.addOrder(
															Order.asc("id")),
										new AtlEinheiten());
		return strassenlist;

	}
	
	/**
	 * Get next id for new AtlEinheiten
	 * 
	 * @return <code>AtlEinheitenID</code>
	 */
	public static Integer newEinheitenID()
	{
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
																		DetachedCriteria.forClass(AtlEinheiten.class)
																				.setProjection(
																								Property.forName("id")
																										.max()),
																		new Integer(0));
		return id + 1;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class AtlParameter */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all AtlParameter and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Parameter</code>
	 */
	public static List<AtlParameter> getParameterlist()
	{
		List<AtlParameter> parameterlist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(AtlParameter.class)
												.addOrder(
															Order.asc("id")),
										new AtlParameter());
		return parameterlist;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class AtlKlaeranlage */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all AtlKlaeranlagen and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Parameter</code>
	 */
	public static List<AtlKlaeranlagen> getKlaeranlagenlist()
	{
		List<AtlKlaeranlagen> klaeranlagenlist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(AtlKlaeranlagen.class)
												.addOrder(Order.asc("id")),
										new AtlKlaeranlagen());
		return klaeranlagenlist;
	}

	/**
	 * Get next id for new AtlKlaeranlagen
	 * 
	 * @return <code>AtlKlaeranlagenID</code>
	 */
	public static Integer newKlaeranlagenID()
	{
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
																		DetachedCriteria.forClass(AtlKlaeranlagen.class)
																				.setProjection(
																								Property.forName("id")
																										.max()),
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
	public static List<VawsWassereinzugsgebiete> getWasserschutzgebietelist()
	{
		List<VawsWassereinzugsgebiete> wsglist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(
																	VawsWassereinzugsgebiete.class)
												.addOrder(
															Order.asc("id")),
										new VawsWassereinzugsgebiete());
		return wsglist;

	}

	/**
	 * Get next id for new VawsWassereinzugsgebiete
	 * 
	 * @return <code>VawsWassereinzugsgebieteID</code>
	 */
	public static Integer newWSGID()
	{
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
																		DetachedCriteria.forClass(VawsWassereinzugsgebiete.class)
																				.setProjection(
																								Property.forName("id")
																										.max()),
																		new Integer(0));
		return id + 1;
	}

}
