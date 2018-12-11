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
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Abfuellflaeche;
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenarten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Anlagenchrono;
import de.bielefeld.umweltamt.aui.mappings.awsv.Behaelterart;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fluessigkeit;
import de.bielefeld.umweltamt.aui.mappings.awsv.Gebuehrenarten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Gefaehrdungsstufen;
import de.bielefeld.umweltamt.aui.mappings.awsv.Kontrollen;
import de.bielefeld.umweltamt.aui.mappings.awsv.Material;
import de.bielefeld.umweltamt.aui.mappings.awsv.Pruefer;
import de.bielefeld.umweltamt.aui.mappings.awsv.Pruefergebniss;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Vbfeinstufung;
import de.bielefeld.umweltamt.aui.mappings.awsv.Verwaltungsgebuehren;
import de.bielefeld.umweltamt.aui.mappings.awsv.Verwaltungsverf;
import de.bielefeld.umweltamt.aui.mappings.awsv.Verwmassnahmen;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.mappings.basis.Wirtschaftszweig;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * This is a service class for all custom queries from the vaws package.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseVawsQuery {

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	/* ********************************************************************** */
	/* Queries for package VAWS */
	/* ********************************************************************** */

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Abfuellflaeche */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get a VawsAbfluellflaeche (create a new one if needed)
	 * 
	 * @param fachdaten
	 *            Fachdaten
	 * @return Abfuellflaeche
	 */
	// TODO: I replaced this hole method by fachdaten.getAbfuellflaeche()
	// BUT: Someone should still find out why this does not work... :(
	private static Abfuellflaeche getAbfuellflaeche(Fachdaten fachdaten) {
		if (fachdaten == null
				|| !fachdaten.getAnlagenart().equals(
						DatabaseConstants.VAWS_ANLAGENART_ABFUELLFLAECHE)) {
			return null;
		}
		Abfuellflaeche flaeche = new DatabaseAccess()
				.executeCriteriaToUniqueResult(
						DetachedCriteria.forClass(Abfuellflaeche.class)
						// TODO: For some reason this throws strange exceptions.
						// Using the id directly is only a workaround and
						// somebody should take a longer
						// look at this at some point in time... :(
						// And it seems to be working with the Kontrollen...
								.add(Restrictions
										.eq("fachdaten", fachdaten)),
						// .add(Restrictions.eq(
						// "behaelterid", fachdaten.getBehaelterid())),
						new Abfuellflaeche());
		if (flaeche == null) {
			// Bei so ziemlich 95% aller Tankstellen gibts ein Fachdaten-
			// Objekt, aber kein Abfuellflaechen-Objekt.
			// Seems like it's not a bug, it's a feature...

			// Also legen wir in diesen Füllen einfach ein neues
			// Abfuellflaechen-Objekt an.

			// Das selbe tun wir bei einem noch ungespeicherten
			// neuen Fachdaten-Objekt.
			flaeche = new Abfuellflaeche();
			flaeche.setFachdaten(fachdaten);
			log.debug("Neue Fläche für '" + fachdaten + "' erzeugt!");
		}
		return flaeche;
	}

	private static String[] bodenflaechenausf = null;

	/**
	 * Get all used Bodenflaechenausf from Abfuellflaeche
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getBodenflaechenausf() {
		if (DatabaseVawsQuery.bodenflaechenausf == null) {
			DatabaseVawsQuery.bodenflaechenausf = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Abfuellflaeche.class)
									.setProjection(
											Projections.distinct(Projections
													.property("bodenflaechenausf")))
									.addOrder(Order.asc("bodenflaechenausf")),
							new String[0]);
		}
		return DatabaseVawsQuery.bodenflaechenausf;
	}

	private static String[] niederschlagschutz = null;

	/**
	 * Get all used Niederschlagschutz from Abfuellflaeche
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getNiederschlagschutz() {
		if (DatabaseVawsQuery.niederschlagschutz == null) {
			DatabaseVawsQuery.niederschlagschutz = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Abfuellflaeche.class)
									.setProjection(
											Projections.distinct(Projections
													.property("niederschlagschutz")))
									.addOrder(Order.asc("niederschlagschutz")),
							new String[0]);
		}
		return DatabaseVawsQuery.niederschlagschutz;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Anlagenarten */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Anlagenarten[] anlagenarten = null;

	/**
	 * Get all Anlagenarten
	 * 
	 * @return <code>Anlagenarten[]</code>
	 */
	public static Anlagenarten[] getAnlagenarten() {
		if (DatabaseVawsQuery.anlagenarten == null) {
			DatabaseVawsQuery.anlagenarten = DatabaseQuery.getOrderedAll(
					new Anlagenarten()).toArray(new Anlagenarten[0]);
		}
		return DatabaseVawsQuery.anlagenarten;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Anlagenchrono */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get Anlagenchrono for Fachdaten
	 * 
	 * @param fachdaten
	 *            Fachdaten
	 * @return <code>List&lt;Anlagenchrono&gt;</code>
	 */
	public static List<Anlagenchrono> getAnlagenChronos(
			Fachdaten fachdaten) {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Anlagenchrono.class)
						.add(Restrictions.eq("fachdaten", fachdaten))
						.addOrder(Order.desc("datum"))
						.addOrder(Order.asc("wv")), new Anlagenchrono());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Behaelterart */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static String[] behaelterart = null;

	/**
	 * Get all Behaelterart
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getBehaelterarten() {
		if (DatabaseVawsQuery.behaelterart == null) {
			DatabaseVawsQuery.behaelterart = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Behaelterart.class)
									.setProjection(
											Projections
													.property("behaelterart"))
									.addOrder(Order.asc("behaelterart")),
							new String[0]);
		}
		return DatabaseVawsQuery.behaelterart;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Fachdaten */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get Fachdaten for a given Objekt. TODO: We could use
	 * objekt.getFachdatens()...
	 * 
	 * @param objekt
	 *            Objekt
	 * @return <code>List&lt;Fachdaten&gt;</code>
	 */
	public static List<Fachdaten> getFachdatenByObjekt(
			Objekt objekt) {
		return new DatabaseAccess()
				.executeCriteriaToList(
						DetachedCriteria.forClass(Fachdaten.class)
								.add(Restrictions.eq("objekt", objekt))
								.addOrder(Order.desc("stillegungsdatum"))
								.addOrder(Order.asc("anlagenart"))
								.addOrder(Order.asc("herstellnr")),
						new Fachdaten());
	}

	private static String[] ausfuehrung = null;

	/**
	 * Get all used Ausführungen from Fachdaten
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getAusfuehrungen() {
		if (DatabaseVawsQuery.ausfuehrung == null) {
			DatabaseVawsQuery.ausfuehrung = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Fachdaten.class)
									.setProjection(
											Projections.distinct(Projections
													.property("ausfuehrung")))
									.addOrder(Order.asc("ausfuehrung")),
							new String[0]);
		}
		return DatabaseVawsQuery.ausfuehrung;
	}

	/**
	 * Everything that is NOT (Abfüllfläche OR Rohrleitung OR Fahrsilo OR Güllekeller OR Güllehochbehälter) is
	 * Lageranlage. TODO: Check this. We have a Anlagenart Lageranlage, but also
	 * some others
	 * 
	 * @param fachdaten
	 *            Fachdaten
	 * @return <code>true</code>, if Anlagenart is NOT (Abfüllfläche OR
	 *         Rohrleitung OR Fahrsilo OR Güllekeller OR Güllehochbehälter), <code>false</code>, otherwise
	 */
	public static boolean isLageranlage(Fachdaten fachdaten) {
		return (!(fachdaten.getAnlagenart().equals(
				DatabaseConstants.VAWS_ANLAGENART_ABFUELLFLAECHE)
				|| fachdaten.getAnlagenart().equals(
						DatabaseConstants.VAWS_ANLAGENART_ROHRLEITUNG) 
				|| fachdaten.getAnlagenart().equals(
						DatabaseConstants.VAWS_ANLAGENART_FS) 
				|| fachdaten.getAnlagenart().equals(
						DatabaseConstants.VAWS_ANLAGENART_GK) 
				|| fachdaten.getAnlagenart().equals(
						DatabaseConstants.VAWS_ANLAGENART_GHB)));
	}

	/**
	 * Durchsucht Fachdaten nach einer bestimmten Herstellnummer und gibt
	 * das Ergebnis als List zurück
	 * 
	 * @param search
	 *            String
	 * @return <code>List&lt;Fachdaten&gt;</code>
	 */
	public static List<Fachdaten> findHerstellNr(String search) {

		if (search == null || search == "") {
			return new DatabaseAccess().executeCriteriaToList(
					DetachedCriteria.forClass(Fachdaten.class),
					new Fachdaten());
		} else {
			return new DatabaseAccess().executeCriteriaToList(
					DetachedCriteria.forClass(Fachdaten.class).add(
							Restrictions.like("herstellnr", search)),
					new Fachdaten());
		}
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Fluessigkeit */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static String[] fluessigkeit = null;

	/**
	 * Get all Fluessigkeit
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getFluessigkeiten() {
		if (DatabaseVawsQuery.fluessigkeit == null) {
			DatabaseVawsQuery.fluessigkeit = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Fluessigkeit.class)
									.setProjection(
											Projections
													.property("fluessigkeit"))
									.addOrder(Order.asc("fluessigkeit")),
							new String[0]);
		}
		return DatabaseVawsQuery.fluessigkeit;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Gebuehrenarten */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Gebuehrenarten[] gebuehrenarten = null;

	/**
	 * Get all Gebuehrenarten
	 * 
	 * @return <code>Gebuehrenarten[]</code>
	 */
	public static Gebuehrenarten[] getGebuehrenarten() {
		if (DatabaseVawsQuery.gebuehrenarten == null) {
			DatabaseVawsQuery.gebuehrenarten = DatabaseQuery.getOrderedAll(
					new Gebuehrenarten())
					.toArray(new Gebuehrenarten[0]);
		}
		return DatabaseVawsQuery.gebuehrenarten;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Gefaehrdungsstufen */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static String[] gefaehrdungsstufen = null;

	/**
	 * Get all Gefaehrdungsstufen
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getGefaehrdungsstufen() {
		if (DatabaseVawsQuery.gefaehrdungsstufen == null) {
			DatabaseVawsQuery.gefaehrdungsstufen = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Gefaehrdungsstufen.class)
									.setProjection(
											Projections
													.property("gefaehrdungsstufen"))
									.addOrder(Order.asc("gefaehrdungsstufen")),
							new String[0]);
		}
		return DatabaseVawsQuery.gefaehrdungsstufen;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Kontrollen */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Kontrollen with "naechstepruefung" in the past and not
	 * "pruefungabgeschlossen"
	 * 
	 * @return <code>List&lt;Kontrollen&gt;</code>
	 */
	public static List<Kontrollen> getWiedervorlageKontrollen() {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Kontrollen.class)
						.add(Restrictions.lt("naechstepruefung", new Date()))
						.add(Restrictions.eq("pruefungabgeschlossen", false))
						.addOrder(Order.asc("naechstepruefung"))
						.addOrder(Order.asc("fachdaten")),
				new Kontrollen());
	}

	/**
	 * Get Kontrollen for a Fachdaten object
	 * 
	 * @param fachdaten
	 *            Fachdaten
	 * @return <code>List&lt;Kontrollen&gt;</code>
	 */
	public static List<Kontrollen> getKontrollen(Fachdaten fachdaten) {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Kontrollen.class)
						.add(Restrictions.eq("fachdaten", fachdaten))
						.addOrder(Order.desc("pruefungabgeschlossen"))
						.addOrder(Order.asc("pruefdatum"))
						.addOrder(Order.asc("naechstepruefung")),
				new Kontrollen());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Material */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static String[] material = null;

	/**
	 * Get all Material
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getMaterialien() {
		if (DatabaseVawsQuery.material == null) {
			DatabaseVawsQuery.material = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Material.class)
									.setProjection(
											Projections.property("material"))
									.addOrder(Order.asc("material")),
							new String[0]);
		}
		return DatabaseVawsQuery.material;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Pruefer */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static String[] pruefer = null;

	/**
	 * Get all Pruefer
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getPruefer() {
		if (DatabaseVawsQuery.pruefer == null) {
			DatabaseVawsQuery.pruefer = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Pruefer.class)
									.setProjection(
											Projections.property("pruefer"))
									.addOrder(Order.asc("pruefer")),
							new String[0]);
		}
		return DatabaseVawsQuery.pruefer;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Pruefergebniss */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static String[] pruefergebniss = null;

	/**
	 * Get all Pruefergebniss
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getPruefergebniss() {
		if (DatabaseVawsQuery.pruefergebniss == null) {
			DatabaseVawsQuery.pruefergebniss = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Pruefergebniss.class)
									.setProjection(
											Projections
													.property("pruefergebnis"))
									.addOrder(Order.asc("pruefergebnis")),
							new String[0]);
		}
		return DatabaseVawsQuery.pruefergebniss;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Standortgghwsg */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Standortgghwsg[] standortgghwsg = null;

	/**
	 * Get all Standortgghwsg
	 * 
	 * @return <code>Standortgghwsg[]</code>
	 */
	public static Standortgghwsg[] getStandortgghwsg() {
		if (DatabaseVawsQuery.standortgghwsg == null) {
			DatabaseVawsQuery.standortgghwsg = DatabaseQuery.getOrderedAll(
					new Standortgghwsg())
					.toArray(new Standortgghwsg[0]);
		}
		return DatabaseVawsQuery.standortgghwsg;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Vbfeinstufung */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static String[] vbfeinstufung = null;

	/**
	 * Get all Vbfeinstufung
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getVawsVbfEinstufungen() {
		if (DatabaseVawsQuery.vbfeinstufung == null) {
			DatabaseVawsQuery.vbfeinstufung = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Vbfeinstufung.class)
									.setProjection(
											Projections
													.property("vbfeinstufung"))
									.addOrder(Order.asc("vbfeinstufung")),
							new String[0]);
		}
		return DatabaseVawsQuery.vbfeinstufung;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Verwaltungsgebuehren */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Verwaltungsgebuehren
	 * 
	 * @return <code>List&lt;Verwaltungsgebuehren&gt;</code>
	 */
	public static List<Verwaltungsgebuehren> getVerwaltungsgebuehren(
			Fachdaten fachdaten) {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Verwaltungsgebuehren.class)
						.add(Restrictions.eq("fachdaten", fachdaten))
						.addOrder(Order.asc("datum"))
						.addOrder(Order.asc("abschnitt"))
						.addOrder(Order.asc("betrag")),
				new Verwaltungsgebuehren());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Verwaltungsverf */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Verwaltungsverf
	 * 
	 * @return <code>List&lt;Verwaltungsverf&gt;</code>
	 */
	public static List<Verwaltungsverf> getVerwaltungsverf(
			Fachdaten fachdaten) {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria.forClass(Verwaltungsverf.class)
						.add(Restrictions.eq("fachdaten", fachdaten))
						.addOrder(Order.desc("wvverwverf"))
						.addOrder(Order.asc("datum"))
						.addOrder(Order.asc("wiedervorlage")),
				new Verwaltungsverf());
	}

	/**
	 * Get all Verwaltungsverf with "wiedervorlage" in the past and
	 * "wvverwverf" either <code>false</code> or <code>null</code>
	 * 
	 * @return <code>List&lt;Verwaltungsverf&gt;</code>
	 */
	public static List<Verwaltungsverf> getWiedervorlageVerwaltungsverf() {
		return new DatabaseAccess().executeCriteriaToList(
				DetachedCriteria
						.forClass(Verwaltungsverf.class)
						.add(Restrictions.lt("wiedervorlage", new Date()))
						.add(Restrictions.or(Restrictions.isNull("wvverwverf"),
								Restrictions.eq("wvverwverf", false)))
						.addOrder(Order.asc("wiedervorlage"))
						.addOrder(Order.asc("fachdaten")),
				new Verwaltungsverf());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Verwmassnahmen */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static String[] verwaltungsMassnahmen = null;

	/**
	 * Get all Verwmassnahmen
	 * 
	 * @return <code>String[]</code>
	 */
	public static String[] getVerwaltungsMassnahmen() {
		if (DatabaseVawsQuery.verwaltungsMassnahmen == null) {
			DatabaseVawsQuery.verwaltungsMassnahmen = new DatabaseAccess()
					.executeCriteriaToArray(
							DetachedCriteria
									.forClass(Verwmassnahmen.class)
									.setProjection(
											Projections.property("massnahmen"))
									.addOrder(Order.asc("massnahmen")),
							new String[0]);
		}
		return DatabaseVawsQuery.verwaltungsMassnahmen;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Wassereinzugsgebiet */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Wassereinzugsgebiet[] wassereinzugsgebiet = null;

	/**
	 * Get all Wassereinzugsgebiet
	 * 
	 * @return <code>Wassereinzugsgebiet[]</code>
	 */
	public static Wassereinzugsgebiet[] getWassereinzugsgebiet() {
		if (DatabaseVawsQuery.wassereinzugsgebiet == null) {
			DatabaseVawsQuery.wassereinzugsgebiet = DatabaseQuery
					.getOrderedAll(new Wassereinzugsgebiet()).toArray(
							new Wassereinzugsgebiet[0]);
		}
		return DatabaseVawsQuery.wassereinzugsgebiet;
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Wirtschaftszweig */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Wirtschaftszweig[] wirtschaftszweige = null;

	/**
	 * Get all Wirtschaftszweig
	 * 
	 * @return <code>Wirtschaftszweig[]</code>
	 */
	public static Wirtschaftszweig[] getWirtschaftszweig() {
		if (DatabaseVawsQuery.wirtschaftszweige == null) {
			DatabaseVawsQuery.wirtschaftszweige = DatabaseQuery
					.getOrderedAll(new Wirtschaftszweig()).toArray(
							new Wirtschaftszweig[0]);
		}
		return DatabaseVawsQuery.wirtschaftszweige;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package VAWS: class Anlagenchronologie */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get Anlagenchronologie
	 * 
	 * @return <code>List&lt;Anlagenchronologie;</code>
	 */
	public static List<Anlagenchrono> getAnlagenchrono(
			boolean nurWiedervorlageAbgelaufen, boolean nurNichtAbgeschlossen) {
        DetachedCriteria detachedCriteria =
				DetachedCriteria.forClass(Anlagenchrono.class)
						.addOrder(Order.desc("wv"))
						.addOrder(Order.asc("fachdaten"));
        if (nurWiedervorlageAbgelaufen && !nurNichtAbgeschlossen) {
            detachedCriteria.add(Restrictions.le("wv", new Date()));
        }
        else if (!nurWiedervorlageAbgelaufen && nurNichtAbgeschlossen) {
            detachedCriteria.add(Restrictions.le("abgeschlossen", false));
        }
        else if (nurWiedervorlageAbgelaufen && nurNichtAbgeschlossen) {
            detachedCriteria.add(Restrictions.le("wv", new Date()));
            detachedCriteria.add(Restrictions.le("abgeschlossen", false));
        }
        return new DatabaseAccess().executeCriteriaToList(
                detachedCriteria, new Anlagenchrono());
	}
}
