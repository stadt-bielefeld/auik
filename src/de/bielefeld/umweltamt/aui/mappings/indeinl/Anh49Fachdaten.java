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

/*
 * Created Thu May 19 12:48:18 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'ANH_49_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh49Fachdaten extends AbstractAnh49Fachdaten implements
		Serializable {
	/**
	 * Simple constructor of Anh49Fachdaten instances.
	 */
	public Anh49Fachdaten() {
	}

	public String toString() {
		String tmp = "[Anh49:" + getBasisObjekt() + "]";
		return tmp;
	}

	// es werden alle Datensätze aus Anh49Fachdaten ausser Fettabscheidern
	// ausgewählt
	public static List findAlle() {

		List anhang49;

		String query = "from Anh49Fachdaten anh49 where anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
				+ "and anh49.basisObjekt.inaktiv = false "
				+ "order by anh49.sachbearbeiterIn";

		Session session = HibernateSessionFactory.currentSession();
		anhang49 = session.createQuery(query).list();
		HibernateSessionFactory.closeSession();

		return anhang49;

	}

	// es werden alle Datensätze aus Anh49Fachdaten ausser Fettabscheidern
	// ausgewählt
	public static List findInaktive() {

		List anhang49;

		String query = "from Anh49Fachdaten anh49 where anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
				+ "and anh49.basisObjekt.inaktiv = true "
				+ "order by anh49.sachbearbeiterIn";

		Session session = HibernateSessionFactory.currentSession();
		anhang49 = session.createQuery(query).list();
		HibernateSessionFactory.closeSession();

		return anhang49;

	}

	/**
	 * Sucht Anhang49-Fachdatensätze nach bestimmten Kriterien.
	 * 
	 * @param sachbearbeiter
	 *            Welche(r) SachbearbeiterIn (oder "")?
	 * @param abgemeldet
	 *            param abgerissen
	 * @param abwasserfrei
	 * @param dekratuev
	 * @param nurWiedervorlageAbgelaufen
	 *            Sollen nur Datensätze angezeigt werden, deren Wiedervorlage in
	 *            der Vergangenheit liegt?
	 * @return Eine Liste mit den entstprechenden Anh49Fachdaten.
	 */
	public static List findAuswertung(Boolean abgemeldet, String abgerissen,
			Boolean abwasserfrei, boolean nurWiedervorlageAbgelaufen) {
		List anhang49;

		Date today = new Date();

		String abgr = abgerissen.toLowerCase().trim() + "%";

		String query = "from Anh49Fachdaten ah49 where "
				+ "ah49.abgemeldet = ? and "
				+ "lower(ah49.sonstigestechnik) like ? and "
				+ "ah49.abwasserfrei = ? "
				+ "and ah49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' ";

		if (nurWiedervorlageAbgelaufen) {

			query += "and ah49.wiedervorlage <= ? "
					+ "and ah49.basisObjekt.inaktiv = false "
					+ "order by ah49.sachbearbeiterIn, "
					+ "ah49.basisObjekt.basisBetreiber.betrname ";

			Session session = HibernateSessionFactory.currentSession();
			anhang49 = session.createQuery(query).setBoolean(0, abgemeldet)
					.setString(1, abgr).setBoolean(2, abwasserfrei)
					.setDate(3, today).list();
			HibernateSessionFactory.closeSession();

		} else {
			query += "and ah49.basisObjekt.inaktiv = false "
					+ "order by ah49.sachbearbeiterIn, "
					+ "ah49.basisObjekt.basisBetreiber.betrname";

			Session session = HibernateSessionFactory.currentSession();
			anhang49 = session.createQuery(query).setBoolean(0, abgemeldet)
					.setString(1, abgr).setBoolean(2, abwasserfrei).list();
			HibernateSessionFactory.closeSession();
		}

		return anhang49;
	}

	public static Anh49Fachdaten getAnh49ByObjekt(BasisObjekt objekt,
			Session session) {
		Anh49Fachdaten fachdaten = null;
		Integer objID = objekt.getObjektid();
		if (objekt.getBasisObjektarten().isAnh49()) {
			try {
				List anhang49 = session
						.createQuery(
								"from Anh49Fachdaten as ah49 where "
										+ "ah49.basisObjekt = ?")
						.setEntity(0, objekt).list();

				if (anhang49.size() > 0) {
					fachdaten = (Anh49Fachdaten) anhang49.get(0);
				}
			} catch (HibernateException e) {
				throw new RuntimeException("Datenbank-Fehler", e);
			} finally {
				HibernateSessionFactory.closeSession();
			}
		}

		return fachdaten;
	}

	public static Anh49Fachdaten getAnh49ByObjekt(BasisObjekt objekt) {
		Anh49Fachdaten fachdaten;
		try {
			Session session = HibernateSessionFactory.currentSession();
			fachdaten = getAnh49ByObjekt(objekt, session);
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			fachdaten = null;
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return fachdaten;
	}

	public static boolean saveFachdaten(Anh49Fachdaten fachdaten) {
		boolean saved;

		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(fachdaten);
			tx.commit();
			saved = true;
		} catch (HibernateException e) {
			saved = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "Anh49Fachdaten.save",
							false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return saved;
	}

	public static List findTuev(Integer tuev) {

		List anhang49;

		String query = "from Anh49Fachdaten anh49 "
				+ "where anh49.dekraTuevTermin = ? "
				+ "and anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
				+ "and anh49.basisObjekt.inaktiv = false "
				+ "order by anh49.sachbearbeiterIn";

		Session session = HibernateSessionFactory.currentSession();
		anhang49 = session.createQuery(query).setInteger(0, tuev).list();
		HibernateSessionFactory.closeSession();

		return anhang49;

	}

	public static List findSachbearbeiter(String sachbearb) {

		List anhang49;

		String query = "from Anh49Fachdaten anh49 "
				+ "where anh49.sachbearbeiterIn = ? "
				+ "and anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
				+ "and anh49.basisObjekt.inaktiv = false";

		Session session = HibernateSessionFactory.currentSession();
		anhang49 = session.createQuery(query).setString(0, sachbearb).list();
		HibernateSessionFactory.closeSession();

		return anhang49;

	}

	public static String[] getSachbearbeiter() {

		List list = null;

		String suchString = "SELECT DISTINCT fd.sachbearbeiterIn FROM de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten fd "
				+ "where fd.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider'";

		Session session = HibernateSessionFactory.currentSession();
		Query query = session.createQuery(suchString);
		query.setCacheable(true);
		query.setCacheRegion("sachbearbeiter");
		list = query.list();
		String[] tmp = new String[list.size()];
		tmp = (String[]) list.toArray(tmp);

		return tmp;

	}

	public Date getLetzteAnalyse(Anh49Fachdaten fd) {

		Date la = null;
		Session session = HibernateSessionFactory.currentSession();
		List query = new ArrayList();

		String suchstring = "select max(fd.anh49Analysen.datum) from de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten fd "
				+ "where fd = ?";
		query = session.createQuery(suchstring).setEntity(0, fd).list();
		Timestamp ta = (Timestamp) query.get(0);
		if (ta == null) {
		} else
			la = ta;
		return la;
	}
}
