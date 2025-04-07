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
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Wasserrecht;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abfuhr;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Analysen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Kontrollen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.BwkFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Entsorger;
import de.bielefeld.umweltamt.aui.mappings.indeinl.SuevFachdaten;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.AfsNiederschlagswasser;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.Entwaesserungsgrundstueck;

/**
 * This is a service class for all custom queries from the indeinl package.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseDirekteinlQuery {

	private static Entsorger[] entsorger = null;

	private static Abaverfahren[] verfahren = null;

	private static Integer[] anhaenge = null;

	/* ********************************************************************** */
	/* Queries for package DirektEinl */
	/* ********************************************************************** */

	/**
	 * Get a list of all Anfallstelle
	 * 
	 * @return <code>List&lt;Anfallstelle&gt;</code>
	 */
	public static List<Anfallstelle> getAnfallstelle(String anh, String art, Sachbearbeiter sachbe) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Anfallstelle.class)
				.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY).createAlias("objekt", "objekt")
				.add(Restrictions.eq("objekt.deleted", false)).add(Restrictions.eq("objekt.inaktiv", false));

		if (!anh.isEmpty() && !anh.equals("99")) {
			criteria.add(Restrictions.eq("anhangId", anh));
		} else if (!art.equals("-")) {
			criteria.add(Restrictions.eq("anlagenart", art));
		}
		if (sachbe != null) {
			criteria.add(Restrictions.eq("objekt.sachbearbeiter", sachbe));
		}

		return new DatabaseAccess().executeCriteriaToList(criteria, new Anfallstelle());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package DIREKTEINL: class AfsNiederschalgswasser */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Liefert alle Probenahme eines bestimmten Probepunktes.
	 *
	 * @param anfallstelle Die Anfallstelle.
	 * @return List&lt;AfsNiederschlagswasser&gt;
	 */

	public static List<AfsNiederschlagswasser> findAfsNw(Anfallstelle anfallstelle) {
		return new DatabaseAccess().executeCriteriaToList(DetachedCriteria.forClass(AfsNiederschlagswasser.class)
				.add(Restrictions.eq("anfallstelle", anfallstelle)), new AfsNiederschlagswasser());
	}

	public static List<AfsNiederschlagswasser> findAfsNw(Entwaesserungsgrundstueck grundstueck) {
		return new DatabaseAccess().executeCriteriaToList(DetachedCriteria.forClass(AfsNiederschlagswasser.class)
				.add(Restrictions.eq("entwaesserungsgrundstueck", grundstueck)), new AfsNiederschlagswasser());
	}

	/**
	 * Get an array with all <code>MapElkaGewkennz</code>en
	 *
	 * @return <code>Integer[]</code>
	 */

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

	/**
	 * Get all AfsNiederschlagswasser
	 * 
	 * @return <code>Eine Liste aller AfsNiederschlagswasser</code>
	 */
	public static List<AfsNiederschlagswasser> getAfsNwList(Entwaesserungsgrundstueck grundstueck) {
		return new DatabaseAccess().executeCriteriaToList(DetachedCriteria.forClass(AfsNiederschlagswasser.class)
				.add(Restrictions.eq("entwaesserungsgrundstueck", grundstueck)), new AfsNiederschlagswasser());
	}

	public static List<AfsNiederschlagswasser> getAfsNwList(Anfallstelle anfallstelle) {
		return new DatabaseAccess().executeCriteriaToList(DetachedCriteria.forClass(AfsNiederschlagswasser.class)
				.add(Restrictions.eq("anfallstelle", anfallstelle)), new AfsNiederschlagswasser());
	}
}
