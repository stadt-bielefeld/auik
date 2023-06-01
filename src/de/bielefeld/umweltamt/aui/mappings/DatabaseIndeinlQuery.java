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

import javax.swing.JOptionPane;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.elka.Aba;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Wasserrecht;
import de.bielefeld.umweltamt.aui.mappings.elka.ZAbaVerfahren;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abfuhr;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Analysen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Kontrollen;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh55Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.BwkFachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Entsorger;
import de.bielefeld.umweltamt.aui.mappings.indeinl.SuevFachdaten;
import de.bielefeld.umweltamt.aui.module.objektpanels.AbaPanel;

/**
 * This is a service class for all custom queries from the indeinl package.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseIndeinlQuery extends DatabaseAwSVQuery {
	
    private static Entsorger[] entsorger = null;

    private static Abaverfahren[] verfahren = null;
    
    private static Integer[] anhaenge = null;
    
    
    /* ********************************************************************** */
    /* Queries for package INDEINL                                            */
    /* ********************************************************************** */


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Objekt                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Sucht alle Objekte einer Objektart,  die nicht erloschen sind.
     * @param Objektart Es sollen nur Datensätze dieser Artangezeigt werden.
     * @return <code>List&lt;Objekt&gt;</code>
     *         Eine Liste mit den entstprechenden Objekte.
     */
    public static List<Objekt> getObjektByArt(
    		Objektarten art) {
    	return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(Objekt.class)
            	.add(Restrictions.eq("objektarten", art))
                .add(Restrictions.eq("inaktiv", false)),
            new Objekt());
                
        
    }
    /**
     * Sucht alle Objekte des angemeldeten Sachbearbeiters, die ein
     * Wiedervorlagedatum haben.
     * @param nurWiedervorlageAbgelaufen Sollen nur Datensätze angezeigt werden,
     *            deren Wiedervorlage in der Vergangenheit liegt?
     * @return <code>List&lt;Objekt&gt;</code>
     *         Eine Liste mit den entstprechenden Objekten.
     */
	public static List<Objekt> getObjektByWiedervorlage(
			boolean nurWiedervorlageAbgelaufen) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Objekt.class)
				.add(Restrictions.eq("sachbearbeiter",
						DatabaseBasisQuery.getCurrentSachbearbeiter()))
				.add(Restrictions.isNotNull("wiedervorlage"))
                .addOrder(Order.asc("objektarten"))
                .addOrder(Order.asc("wiedervorlage"));
        if (nurWiedervorlageAbgelaufen) {
            detachedCriteria.add(Restrictions.le("wiedervorlage", new Date()));
        }

		return new DatabaseAccess().executeCriteriaToList(detachedCriteria,
				new Objekt());

	}
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Anfallstelle                      */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get a list of all Anfallstelle
     * @return <code>List&lt;Anfallstelle&gt;</code>
     */
	public static List<Anfallstelle> getAnfallstelle(String anh, String art, Sachbearbeiter sachbe) {

		DetachedCriteria criteria = 
				DetachedCriteria.forClass(Anfallstelle.class)
						.createAlias("objekt", "objekt")
						.add(Restrictions.eq("objekt.deleted", false))
						.add(Restrictions.eq("objekt.inaktiv", false));

		        if (!anh.isEmpty() && !anh.equals("99")) {
		            criteria.add(Restrictions.eq("anhangId", anh));
		        } else if (!art.equals("-")) {
		            criteria.add(Restrictions.eq("anlagenart", art));
		        }
		        if (sachbe != null) {
		            criteria.add(Restrictions.eq("objekt.sachbearbeiter", sachbe));
		        }
		        
		        
        return new DatabaseAccess().executeCriteriaToList(
                criteria, new Anfallstelle());
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
                .add(Restrictions.eq("anh49Fachdaten", fachdaten))
                .addOrder(Order.asc("abscheidernr")),
            new Anh49Abscheiderdetails());
    }

    /**
     * Get all Anfallstelle that are Fettabscheider
     * @return <code>List&lt;Anfallstelle;</code>
     */
    public static List<Anfallstelle> getFettabscheider(Sachbearbeiter sachbearbeiter) {
        DetachedCriteria crit = DetachedCriteria.forClass(Anfallstelle.class)
            .createAlias("objekt", "objekt")
            .createAlias("objekt.objektarten", "art")
            .createAlias("objekt.betreiberid", "adresse")
            .add(Restrictions.eq("objekt.deleted", false))
            .add(Restrictions.eq("anlagenart", "Fettabscheider"))
            .add(Restrictions.eq("objekt.inaktiv", false))
            .addOrder(Order.asc("adresse.name"));
        if (sachbearbeiter != null) {
            crit.add(Restrictions.eq("objekt.sachbearbeiter", sachbearbeiter));
        }
        return new DatabaseAccess().executeCriteriaToList(crit, new Anfallstelle());
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
     * Global restriction: no Objektart "Fettabscheider"
     * @param aktiv (optional) The status of the underlying Objekt
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
        Sachbearbeiter sachbearbeiter) {

        DetachedCriteria criteria =
            DetachedCriteria.forClass(Anh49Fachdaten.class)
            	.createAlias("anfallstelle", "anf")
                .createAlias("anf.objekt", "obj")
                .createAlias("obj.objektarten", "art");

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
        if (sachbearbeiter != null) {
            criteria.add(
                Restrictions.eq("obj.sachbearbeiter", sachbearbeiter));
        }

        criteria.addOrder(Order.asc("obj.inaktiv"));

        return new DatabaseAccess().executeCriteriaToList(
            criteria, new Anh49Fachdaten());
    }

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
                .createAlias("anfallstelle", "anf")
                .createAlias("anf.objekt", "objekt")
                .createAlias("objekt.betreiberid", "adresse")
                .add(Restrictions.eq("erloschen", false))
                .add(Restrictions.eq("objekt.inaktiv", false))
                .add(Restrictions.eq("objekt.deleted", false))
                .addOrder(Order.asc("wiedervorlage"))
                .addOrder(Order.asc("adresse.name"));
        if (nurWiedervorlageAbgelaufen) {
            detachedCriteria.add(Restrictions.le("wiedervorlage", new Date()));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new Anh50Fachdaten());
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
            .createAlias("anfallstelle", "anf")
                .createAlias("anf.objekt", "objekt")
                .createAlias("objekt.standortid", "standort")
                .createAlias("standort.inhaber", "inhaber")
                .createAlias("inhaber.adresse", "adresse")
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("adresse.strasse"))
                .addOrder(Order.asc("adresse.hausnr"));
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
    /* Queries for package INDEINL: class BwkFachdaten                     */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get BwkFachdaten for a given year.
     * @param year Integer (may be null)
     * @return <code>List&lt;BwkFachdaten&gt;</code>
     */
    public static List<BwkFachdaten> getBwkByYear(Integer year) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(BwkFachdaten.class)
            .createAlias("anfallstelle", "anf")
            .createAlias("anf.objekt", "obj")
            .createAlias("obj.standortid", "standort")
            .createAlias("standort.inhaber", "inhaber")
            .createAlias("inhaber.adresse", "adresse")
            .add(Restrictions.eq("obj.deleted", false));
        detachedCriteria.addOrder(Order.asc("adresse.strasse"));
        detachedCriteria.addOrder(Order.asc("adresse.hausnr"));
        /* year == -1 => alle Jahre */
        if (year == null || year != -1) {
            detachedCriteria.add(
                DatabaseAccess.getRestrictionsEqualOrNull("erfassung", year));
        }
        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new BwkFachdaten());
    }
    /**
     * Get BwkFachdaten for Objektart BHKW.
     * @return <code>List&lt;BwkFachdaten&gt;</code>
     */
    public static List<BwkFachdaten> getBHKW() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				BwkFachdaten.class)
	            .createAlias("anfallstelle", "anf")
	            .createAlias("anf.objekt", "obj")
				.createAlias("objekt.standortid", "standort")
                .createAlias("objekt.objektarten", "art")
                .createAlias("standort.adresse", "adresse")
				.addOrder(Order.asc("adresse.strasse"))
				.addOrder(Order.asc("adresse.hausnr"))
				.add(Restrictions.eq("art.id", 36))
	            .add(Restrictions.eq("obj.deleted", false));

        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new BwkFachdaten());
    }
    /**
     * Get BwkFachdaten for ABA = true.
     * @return <code>List&lt;BwkFachdaten&gt;</code>
     */
    public static List<BwkFachdaten> getABA() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				BwkFachdaten.class)
	            .createAlias("anfallstelle", "anf")
	            .createAlias("anf.objekt", "obj")
				.createAlias("objekt.standortid", "standort")
                .createAlias("standort.adresse", "adresse")
				.addOrder(Order.asc("adresse.strasse"))
				.addOrder(Order.asc("adresse.hausnr"))
				.add(Restrictions.eq("aba", true))
	            .add(Restrictions.eq("obj.deleted", false));

        return new DatabaseAccess().executeCriteriaToList(
            detachedCriteria, new BwkFachdaten());
    }

    /**
     * Get a list of all recording years
     * @return Integer[]
     */
    
    public static Integer[] getBwkErfassungsjahre() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				(BwkFachdaten.class))
                .setProjection(Projections.distinct(
                    Projections.property("erfassung")))
				.addOrder(Order.asc("erfassung"));

        return new DatabaseAccess().executeCriteriaToArray(
            detachedCriteria, new Integer[0]);
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class Entsorger                        */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get all Entsorger
     * @return <code>Entsorger[]</code>
     */
    public static Entsorger[] getEntsorger() {
        if (DatabaseIndeinlQuery.entsorger == null) {
            DatabaseIndeinlQuery.entsorger =
                DatabaseQuery.getOrderedAll(new Entsorger())
                    .toArray(new Entsorger[0]);
        }
        return DatabaseIndeinlQuery.entsorger;
    }
	

	/**
	 * Get all AtlEinheiten and sort them by their name
	 * 
	 * @return <code>Eine Liste aller Einheiten</code>
	 */
	public static List<Entsorger> getEntsorgerlist()
	{
		List<Entsorger> entsorgerlist = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(Entsorger.class)
												.addOrder(
															Order.asc("id")),
										new Entsorger());
		return entsorgerlist;

	}

	/**
	 * Get next id for new Entsorger
	 * 
	 * @return <code>Entsorger</code>
	 */
	public static Integer newEntsorgerID()
	{
		Integer id = new DatabaseAccess().executeCriteriaToUniqueResult(
																		DetachedCriteria.forClass(Entsorger.class)
																				.setProjection(
																								Property.forName("id")
																										.max()),
																		new Integer(0));
		return id + 1;
	}

    
    
    /**
     * Get all AbaVerfahren
     * @return <code>AbaVerfahren[]</code>
     */
    public static Abaverfahren[] getVerfahren() {
        if (DatabaseIndeinlQuery.verfahren == null) {
            DatabaseIndeinlQuery.verfahren =
                DatabaseQuery.getOrderedAll(new Abaverfahren())
                    .toArray(new Abaverfahren[0]);
        }
        return DatabaseIndeinlQuery.verfahren;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class SuevFachdaten                    */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Liefert eine Liste mit allen SuevFachdaten Objekten.
     * @return <code>List&lt;SuevFachdaten&gt;</code>
     */
    public static List<SuevFachdaten> getAnhangSuev() {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(SuevFachdaten.class)
                .createAlias("objekt", "objekt")
                .addOrder(Order.asc("objekt.inaktiv"))
                .addOrder(Order.asc("id")),
            new SuevFachdaten());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package INDEINL: class IndeinlGenehmigung                  */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */


    public static Integer[] getAnhangFromGenehmigung() {
        if (DatabaseIndeinlQuery.anhaenge == null) {
            DatabaseIndeinlQuery.anhaenge = new DatabaseAccess()
                .executeCriteriaToArray(
                    DetachedCriteria.forClass(Wasserrecht.class)
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
    public static List<Wasserrecht> getGenehmigungen(
        Integer anhang, Boolean inaktiv, Boolean gen58, Boolean gen59) {
        DetachedCriteria detachedCriteria =
            DetachedCriteria.forClass(Wasserrecht.class)
                .createAlias("objekt", "objekt")
                .createAlias("objekt.betreiberid", "adresse")
                .createAlias("objekt.standortid", "standort")
                .createAlias("standort.adresse", "standortadr")
                .add(Restrictions.eq("objekt.inaktiv", inaktiv))
                .add(Restrictions.eq("objekt.deleted", false))
                .addOrder(Order.asc("adresse.betrname"))
                .addOrder(Order.asc("standortadr.strasse"))
                .addOrder(Order.asc("standortadr.hausnr"));
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
            detachedCriteria, new Wasserrecht());
    }
}
