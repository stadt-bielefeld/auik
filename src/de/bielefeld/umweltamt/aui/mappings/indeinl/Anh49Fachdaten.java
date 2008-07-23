/*
 * Created Thu May 19 12:48:18 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
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
	
	public static List findAlle() {

		List anhang49;

		String query = "from Anh49Fachdaten anh49 order by anh49.sachbearbeiterIn";

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
	public static List findAuswertung(
			Boolean abgemeldet, String abgerissen, Boolean abwasserfrei,
			boolean nurWiedervorlageAbgelaufen) {
		List anhang49;

		Date today = new Date();

		String abgr = abgerissen.toLowerCase().trim() + "%";

		String query = "from Anh49Fachdaten ah49 where "
				+ "ah49.abgemeldet = ? and "
				+ "lower(ah49.sonstigestechnik) like ? and "
				+ "ah49.abwasserfrei = ?";

		if (nurWiedervorlageAbgelaufen) {

			query += "and ah49.wiedervorlage <= ? "
					+ "order by ah49.wiedervorlage, "
					+ "ah49.basisObjekt.basisBetreiber.betrname";

			Session session = HibernateSessionFactory.currentSession();
			anhang49 = session.createQuery(query)
					.setString(0, abgemeldet.booleanValue() ? "t" : "f")
					.setString(1, abgr)
					.setString(2, abwasserfrei.booleanValue() ? "t" : "f")
					.setDate(3, today).list();
			HibernateSessionFactory.closeSession();

		} else {
			query += "order by ah49.wiedervorlage, "
					+ "ah49.basisObjekt.basisBetreiber.betrname";

			Session session = HibernateSessionFactory.currentSession();
			anhang49 = session.createQuery(query)
					.setString(0, abgemeldet.booleanValue() ? "t" : "f")
					.setString(1, abgr)
					.setString(2, abwasserfrei.booleanValue() ? "t" : "f")
					.list();
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
				List anhang49 = session.createQuery(
						"from Anh49Fachdaten as ah49 where "
								+ "ah49.basisObjekt = ?").setEntity(0, objekt)
						.list();

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

		String query = 	"from Anh49Fachdaten anh49 "+
						"where anh49.dekraTuevTermin = ?" +
						"order by anh49.sachbearbeiterIn";

		Session session = HibernateSessionFactory.currentSession();
		anhang49 = session.createQuery(query)
			.setInteger(0, tuev)
			.list();
		HibernateSessionFactory.closeSession();

		return anhang49;

	}

	public static List findSachbearbeiter(String sachbearb) {

		List anhang49;

		String query = 	"from Anh49Fachdaten anh49 "+
						"where anh49.sachbearbeiterIn = ?";

		Session session = HibernateSessionFactory.currentSession();
		anhang49 = session.createQuery(query)
			.setString(0, sachbearb)
			.list();
		HibernateSessionFactory.closeSession();

		return anhang49;

	}
}
