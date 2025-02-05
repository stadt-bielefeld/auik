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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Parametergruppen;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.mappings.atl.Probenahme;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.atl.Sielhaut;
import de.bielefeld.umweltamt.aui.mappings.atl.Status;
import de.bielefeld.umweltamt.aui.mappings.atl.ViewAtlAnalysepositionAll;
import de.bielefeld.umweltamt.aui.mappings.basis.Sachbearbeiter;
import de.bielefeld.umweltamt.aui.mappings.elka.MapElkaAnalysemethode;
import de.bielefeld.umweltamt.aui.utils.GermanDouble;
import de.bielefeld.umweltamt.aui.utils.JRMapDataSource;

/**
 * This is a service class for all custom queries from the atl package.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseAtlQuery extends DatabaseBasisQuery
{

	/** Logging */
	// private static final AuikLogger log = AuikLogger.getLogger();

	/* ********************************************************************** */
	/* Queries for package ATL */
	/* ********************************************************************** */

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Analyseposition */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get Probenahme their Analyseposition for an Messstelle
	 *
	 * @param probepunkt
	 *            AtlProbpkt
	 * @return <code>Map&lt;Probenahme,
	 *         Map&lt;Parameter, Analyseposition&gt;&gt;</code>
	 */
	public static Map<Probenahme, Map<Parameter, Analyseposition>>
			getAnalysepositionen(Messstelle probepkt)
	{
		Map<Probenahme, Map<Parameter, Analyseposition>> probeMap =
				new HashMap<Probenahme,
				Map<Parameter, Analyseposition>>();
		Map<Parameter, Analyseposition> parameterMap;
		// For each Probe add an empty parameterMap to the probeMap
		List<Probenahme> proben = DatabaseAtlQuery.findProbenahme(probepkt);
		for (Probenahme probe : proben)
		{
			parameterMap = new HashMap<Parameter, Analyseposition>();
			probeMap.put(probe, parameterMap);
		}
		// Get all Analysepositions (really all!)
		List<Analyseposition> positions = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(Analyseposition.class)
												.createAlias("probenahme", "probe")
												.add(Restrictions.eq("probe.messstelle", probepkt))
												.addOrder(Order.asc("probe.kennummer"))
										,
										new Analyseposition());
		// Sort the Analysepositions into the maps
		for (Analyseposition position : positions)
		{
			// Get the parameterMap for the Probe
			probeMap.get(position.getProbenahme())
					// Add this position to it with the parameter as key
					.put(position.getParameter(), position);
		}
		return probeMap;
	}

	/**
	 * Liefert eine Liste der Analyseinstitute.
	 *
	 * @return String[]
	 */
	public static String[] getAnalysierer()
	{
		return new DatabaseAccess().executeCriteriaToArray(
															DetachedCriteria.forClass(Analyseposition.class)
																	.setProjection(Projections.distinct(
																			Projections.property("analyseVon"))),
															new String[0]);
	}

	/**
	 * Get all <code>Analyseposition</code>s for a given
	 * <code>Messstelle</code> and <code>Parameter</code> in a given
	 * time interval
	 *
	 * @param param
	 *            Parameter
	 * @param pkt
	 *            Messstelle
	 * @param beginDate
	 * @param endDate
	 * @return List&lt;Analyseposition&gt;
	 */
	public static List<Analyseposition> getAnalysepositionen(
		Parameter param, Messstelle pkt, Date beginDate, Date endDate)
	{
		return new DatabaseAccess().executeCriteriaToList(
															DetachedCriteria
																	.forClass(Analyseposition.class)
																	.createAlias("probenahme", "probe")
																	.add(Restrictions.eq("probe.messstelle", pkt))
																	.add(Restrictions.eq("parameter", param))
																	.add(Restrictions
																			.between(
																						"probe.datumDerEntnahme",
																						beginDate,
																						endDate))
																	.addOrder(Order.asc("probe.datumDerEntnahme")),
															new Analyseposition());
	}

	/**
	 * Get all <code>Analyseposition</code>s for a given
	 * <code>Probenahme</code> and sort them
	 *
	 * @param probe
	 *            Probenahme
	 * @return List&lt;Analyseposition&gt;
	 */
	public static List<Analyseposition> getSortedAnalysepositionen(
		Probenahme probe)
	{
		return new DatabaseAccess().executeCriteriaToList(
															DetachedCriteria.forClass(Analyseposition.class)
																	.createAlias("parameter", "parameter")
																	.add(Restrictions.eq("probenahme", probe))
																	.addOrder(Order.asc("parameter.reihenfolge")),
															new Analyseposition());
	}

	/**
	 * Find an Analyseposition from a given Probenahme with the given
	 * Parameter.
	 * If there is none and createNew is true, create a new one.
	 *
	 * @param probe
	 *            Probenahme
	 * @param parameter
	 *            Parameter
	 * @param einheit
	 *            AtlEinheit
	 * @param createNew
	 *            boolean
	 * @return Analyseposition
	 */
	public static Analyseposition findAnalyseposition(
		Probenahme probe, Parameter parameter, Einheiten einheit,
		boolean createNew)
	{
		Analyseposition position = new DatabaseAccess()
				.executeCriteriaToUniqueResult(
												DetachedCriteria.forClass(Analyseposition.class)
														.add(Restrictions.eq("probenahme", probe))
														.add(Restrictions.eq("parameter", parameter)),
												new Analyseposition());
		if (position == null && createNew)
		{
			position = new Analyseposition();
			position.setProbenahme(probe);
			position.setParameter(parameter);
			position.setEinheiten(einheit);
		}
		return position;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Einheiten */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Einheiten[] einheiten = null;

	/**
	 * Liefert alle in der Einheiten-Tabelle gespeicherten Einheiten.
	 *
	 * @return Ein Array mit allen Einheiten
	 */
	public static Einheiten[] getEinheiten()
	{
		if (DatabaseAtlQuery.einheiten == null)
		{
			DatabaseAtlQuery.einheiten =
					DatabaseQuery.getOrderedAll(new Einheiten(), "bezeichnung")
							.toArray(new Einheiten[0]);
		}
		return DatabaseAtlQuery.einheiten;
	}

	/**
	 * Get an unit by its description
	 *
	 * @param description
	 *            The description of the unit (e.g. "mg/l")
	 * @return <code>Einheiten</code>, if an unit was found,
	 *         <code>null</code> otherwise
	 */
	public static Einheiten getEinheitByDescription(String description)
	{
		return new DatabaseAccess().executeCriteriaToUniqueResult(
																	DetachedCriteria.forClass(Einheiten.class)
																			.add(Restrictions.eq("bezeichnung",
																									description)),
																	new Einheiten());
	}

	/**
	 * Check if an Einheiten with <code>description</code> exists.<br>
	 * This is mainly used for the import.
	 *
	 * @param description
	 *            String
	 * @return <code>true</code>, if an Einheiten exists, <code>false</code>
	 *         otherwise
	 */
	public static boolean einheitExists(String description)
	{
		for (Einheiten einheit : DatabaseAtlQuery.getEinheiten())
		{
			if (einheit.getBezeichnung().equals(description))
			{
				return true;
			}
		}
		return false;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class MapElkaAnalysemethode */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static MapElkaAnalysemethode[] methode = null;

	/**
	 * Liefert alle in der MapElkaAnalysemethode-Tabelle gespeicherten Analysemethode.
	 *
	 * @return Ein Array mit allen MapElkaAnalysemethode
	 */
	public static MapElkaAnalysemethode[] getMapElkaAnalysemethode()
	{
		if (DatabaseAtlQuery.methode == null)
		{
			DatabaseAtlQuery.methode =
					DatabaseQuery.getAll(new MapElkaAnalysemethode())
							.toArray(new MapElkaAnalysemethode[0]);
		}
		return DatabaseAtlQuery.methode;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Klaeranlage */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Klaeranlage[] klaeranlage = null;

	/**
	 * Get an array of all Klaeranlage,
	 * omit id 7 because this is an intended duplicate
	 *
	 * @return Klaeranlage[]
	 */
	public static Klaeranlage[] getKlaeranlage()
	{
		if (DatabaseAtlQuery.klaeranlage == null)
		{
			DatabaseAtlQuery.klaeranlage =
					new DatabaseAccess().executeCriteriaToArray(
																DetachedCriteria.forClass(Klaeranlage.class)
																		.add(Restrictions.ne("id", 7))
																		.addOrder(Order.asc("id")),
																new Klaeranlage[0]);
		}
		return DatabaseAtlQuery.klaeranlage;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Parameter */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Liefert alle Parameter, die für Klärschlamm-Probenahme relevant sind.
	 * D.h. alle, deren Klärschlamm-Grenzwert nicht <code>NULL</code> ist.
	 *
	 * @return Ein Array mit allen für Klärschlamm-Probenahme relevanten
	 *         Parametern
	 */
	public static Parameter[] getKlaerschlammParameter()
	{
		return new DatabaseAccess().executeCriteriaToArray(
															DetachedCriteria.forClass(Parameter.class)
																	.add(Restrictions.isNotNull("klaerschlammGw"))
																	.addOrder(Order.asc("bezeichnung")),
															new Parameter[0]);
	}

	/**
	 * Liefert alle Parameter, die für SielhautBearbeiten-Probenahme relevant
	 * sind. D.h. alle, deren SielhautBearbeiten-Grenzwert nicht
	 * <code>NULL</code> ist.
	 *
	 * @return Ein Array mit allen für SielhautBearbeiten-Probenahme relevanten
	 *         Parametern
	 */
	public static List<Parameter> getGroupedParameterAsList()
	{
		return new DatabaseAccess().executeCriteriaToList(
															DetachedCriteria.forClass(Parameter.class)
																	.add(Restrictions.isNotNull("parametergruppen"))
																	.addOrder(Order.asc("reihenfolge")),
															new Parameter());
	}

	/**
	 * Parameter.getGroupedParameterAsList als Array
	 *
	 * @return Ein Array mit allen für Probenahme relevanten Parametern
	 */
	public static Parameter[] getGroupedParameter()
	{
		return new DatabaseAccess().executeCriteriaToArray(
															DetachedCriteria.forClass(Parameter.class)
																	.add(Restrictions.isNotNull("parametergruppen"))
																	.addOrder(Order.asc("reihenfolge")),
															new Parameter[0]);
	}

	public static List<Parameter> getAllParameterAsList()
	{
		return DatabaseQuery.getOrderedAll(new Parameter(), "bezeichnung");
	}

	private static Parameter[] parameter = null;

	public static Parameter[] getAllParameterAsArray()
	{
		if (DatabaseAtlQuery.parameter == null)
		{
			DatabaseAtlQuery.parameter =
					DatabaseQuery.getOrderedAll(new Parameter(), "bezeichnung")
							.toArray(new Parameter[0]);
		}
		return DatabaseAtlQuery.parameter;
	}

	public static List<Parameter> getParameterInGroup(int id)
	{
		return new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(Parameter.class)
												.add(Restrictions.eq("parametergruppen.id", id)),
										new Parameter());
	}

	/**
	 * Get a parameter by its description
	 *
	 * @param description
	 *            The description of the parameter
	 *            (e.g. "Palladium (Pd)")
	 * @return <code>Parameter</code>, if a parameter was found,
	 *         <code>null</code> otherwise
	 */
	// TODO: Maybe like or ilike is a better solution?
	public static Parameter getParameterByDescription(String description)
	{
		return new DatabaseAccess().executeCriteriaToUniqueResult(
																	DetachedCriteria.forClass(Parameter.class)
																			.add(Restrictions.eq("bezeichnung",
																									description)),
																	new Parameter());
	}

	/**
	 * Check if an Parameter with <code>description</code> exists.<br>
	 * This is mainly used for the import.
	 *
	 * @param description
	 *            String
	 * @return <code>true</code>, if an Parameter exists, <code>false</code>
	 *         otherwise
	 */
	public static boolean parameterExists(String description)
	{
		for (Parameter para : DatabaseAtlQuery.getAllParameterAsArray())
		{
			if (para.getBezeichnung().equals(description))
			{
				return true;
			}
		}
		return false;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Parametergruppen */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Diese Funktion pr&uuml;ft, ob die {@link Parameter}, die in
	 * <i>group</i> enthalten sind, vollst&auml;ndig sind.
	 *
	 * @param id
	 *            Die ID der Parametergruppe.
	 * @param group
	 *            Die Liste mit den Parametern.
	 *
	 * @return true, wenn alle Parameter der Gruppen enthalten sind, sonst
	 *         false.
	 */
	public static boolean isCompleteParameterGroup(
		int id, List<Parameter> group)
	{
		List<Parameter> complete = getParameterInGroup(id);
		// First simply check the size
		// As we use List and not Set the size is not a good criteria...
		//        if (group.size() != complete.size()) {
		//            return false;
		//        }
		// Check if all parameters from the complete group are in the group
		if (!(group.containsAll(complete)))
		{
			return false;
		}
		// Be really restrictive and check if there are other parameters
		//        if (!(complete.containsAll(group))) {
		//            return false;
		//        }
		return true;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Probeart */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Probeart[] probearten = null;

	/**
	 * Liefert alle vorhandenen Probearten.
	 *
	 * @return Alle vorhandenen Probearten
	 */
	public static Probeart[] getProbearten()
	{
		if (DatabaseAtlQuery.probearten == null)
		{
			DatabaseAtlQuery.probearten = DatabaseQuery.getOrderedAll(
																			new Probeart())
					.toArray(new Probeart[0]);
		}
		return DatabaseAtlQuery.probearten;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Probenahme */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Liefert alle Probenahme eines bestimmten Probepunktes.
	 *
	 * @param punkt
	 *            Der Probepunkt.
	 * @return List&lt;Probenahme&gt;
	 */
	public static List<Probenahme> findProbenahme(Messstelle punkt)
	{
		return new DatabaseAccess().executeCriteriaToList(
															DetachedCriteria.forClass(Probenahme.class)
																	.add(Restrictions.eq("messstelle", punkt))
																	.addOrder(Order.desc("datumDerEntnahme"))
																	.addOrder(Order.asc("kennummer")),
															new Probenahme());
	}

	/**
	 * Liefert eine bestimmte Probenahme.
	 *
	 * @param kennummer
	 *            Die Kennummer der Probenahme
	 * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
	 *         nicht existiert
	 */
	public static Probenahme findProbenahme(String kennnummer)
	{
		return new DatabaseAccess().executeCriteriaToUniqueResult(
																	DetachedCriteria.forClass(Probenahme.class)
																			.add(Restrictions.eq("kennummer",
																									kennnummer)),
																	new Probenahme());
	}

	/**
	 * Liefert alle Probenahme einer bestimmten Art von einer bestimmten
	 * Kläranlage.
	 *
	 * @param art
	 *            Probeart
	 * @param ka
	 *            Klaeranlage
	 * @return List&lt;Probenahme&gt;
	 */
	public static List<Probenahme> findProbenahme(
		Probeart art, Klaeranlage ka)
	{
		return new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(Probenahme.class)
												.createAlias("messstelle", "probepunkt")
												.add(Restrictions.eq("probepunkt.probeart", art))
												.add(Restrictions.eq("probepunkt.klaeranlage", ka))
												.addOrder(Order.desc("datumDerEntnahme"))
												.addOrder(Order.desc("kennummer")),
										new Probenahme());
	}

	/**
	 * Find Probenahme with <code>value</code> somewhere in
	 * <code>propertyName</code> (search case insensitive).
	 *
	 * @param propertyName
	 *            String
	 * @param value
	 *            String
	 * @return List&lt;Probenahme&gt;
	 */
	public static List<Probenahme> findProbenahme(
		String propertyName, String value)
	{
		return new DatabaseAccess().executeCriteriaToList(
															DetachedCriteria
																	.forClass(Probenahme.class)
																	.add(Restrictions.ilike(
																							propertyName,
																							value,
																							MatchMode.ANYWHERE))
																	.addOrder(Order.desc("datumDerEntnahme"))
																	.addOrder(Order.desc("kennummer")),
															new Probenahme());
	}

	/**
	 * Find Probenahme with <code>status</code>
	 *
	 * @param status
	 *            Status
	 * @return List&lt;Probenahme&gt;
	 */
	public static List<Probenahme> findProbenahme(Status status)
	{
		return new DatabaseAccess().executeCriteriaToList(
															DetachedCriteria.forClass(Probenahme.class)
															.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY)
																	.add(Restrictions.eq("status", status))
																	.addOrder(Order.desc("datumDerEntnahme"))
																	.addOrder(Order.desc("kennummer")),
															new Probenahme());
	}

	/**
	 * Check if there is an Probenahme with <code>kennnummer</code>
	 *
	 * @param kennnummer
	 *            String
	 * @return <code>true</code>, if an Probenahme exists,
	 *         <code>false</code> otherwise
	 */
	public static Boolean probenahmeExists(String kennnummer)
	{
		return (!(new DatabaseAccess().executeCriteriaToList(
																DetachedCriteria.forClass(Probenahme.class)
																		.add(Restrictions.eq("kennummer", kennnummer)),
																new Probenahme()).isEmpty()));
	}

	/**
	 * @param probe
	 *            Probenahme
	 * @return <code>true</code>, wenn die Probeart des Probepunktes dieser
	 *         Probe Rohschlamm oder Faulschlamm ist, sonst <code>false</code>
	 */
	public static Boolean isKlaerschlammProbe(Probenahme probe)
	{
		if (probe.getMessstelle().getProbeart() != null) {
			return (probe.getMessstelle().getProbeart().getId().equals(DatabaseConstants.ATL_PROBEART_ID_FAULSCHLAMM)
					|| probe.getMessstelle().getProbeart().getId()
							.equals(DatabaseConstants.ATL_PROBEART_ID_ROHSCHLAMM));
		}
		else return false;
	}

	public static JRMapDataSource getAuftragDataSource(Probenahme probe)
	{
		List<Analyseposition> sorted = new DatabaseAccess()
				.executeCriteriaToList(
										DetachedCriteria.forClass(Analyseposition.class)
												.createAlias("parameter", "parameter")
												.add(Restrictions.eq("probenahme", probe))
												.add(Restrictions.not(
														Restrictions.ilike("parameter.bezeichnung",
																			"bei Probenahme", MatchMode.ANYWHERE)))
												.addOrder(Order.asc("parameter.reihenfolge")),
										new Analyseposition());

		int elements = sorted.size();

		Object[][] values = new Object[elements][];
		Object[] columns;
		Parameter parameter = null;

		String[] columnsAuftrag = { "auswahl", "Parameter",
				"Kennzeichnung", "Konservierung", "Zusatz" };

		for (int i = 0; i < elements; i++)
		{
			columns = new Object[5];

			parameter = sorted.get(i).getParameter();

			columns[0] = true; // this value is always true
			columns[1] = parameter.getBezeichnung();
			columns[2] = parameter.getKennzeichnung();
			columns[3] = parameter.getKonservierung();
			columns[4] = parameter.getAnalyseverfahren();

			values[i] = columns;
		}

		return new JRMapDataSource(columnsAuftrag, values);
	}

	public static JRMapDataSource getBescheidDataSource(Probenahme probe)
	{
		List<Analyseposition> sorted =
				getSortedAnalysepositionen(probe);
		List<Parameter> params = new ArrayList<Parameter>();
		for (Analyseposition pos : sorted)
		{
			params.add(pos.getParameter());
		}

		int elements = sorted.size();
		String[] columnsBescheid = { "Pos", "Parameter",
				"Grenzwert_Wert", "Grenzwert_Einheit", "Ergebnis_Wert",
				"Ergebnis_Einheit", "Gebühr", "Gr_Kl", "Fett", "inGruppe" };

		Object[][] values = new Object[elements][];
		Object[] columns;
		Analyseposition pos = null;
		Parameter parameter = null;

		for (int i = 0; i < elements; i++)
		{
			columns = new Object[columnsBescheid.length];
			pos = sorted.get(i);

			parameter = pos.getParameter();
			String grenzwert = "";
			if (parameter.getGrenzwert() != null
					&& parameter.getGrenzwert() <= 10)
			{
				grenzwert = parameter.getGrenzwert().toString()
						.replace(".", ",");
			}
			else if (parameter.getGrenzwert() != null
					&& parameter.getGrenzwert() > 10)
			{
				grenzwert = parameter.getGrenzwert().toString()
						.replace(".0", "");
			}
			String wert = "";
			if (pos.getWert() == 0.0)
			{
				wert = pos.getWert().toString().replace(".", ",");
			}
			else if (pos.getWert() < 0.001)
			{
				wert = String.format("%.4f",pos.getWert());
				wert = wert.replace(".", ",");
			}
			else if (pos.getWert() < 0.01)
			{
				wert = String.format("%.3f",pos.getWert());
				wert = wert.replace(".", ",");
			}
			else if (pos.getWert() < 100)
			{
				wert = pos.getWert().toString().replace(".", ",");
			}
			else
			{
				wert = pos.getWert().toString().replace(".0", "");
			}
			Boolean fett = false;
			if (pos.getWert() != null && parameter.getGrenzwert() != null
					&& pos.getWert() > parameter.getGrenzwert())
			{
				fett = true;
			}
			String gebuehr = "0,00 €";
			if (parameter.getPreisfueranalyse() != 0)
				gebuehr = new GermanDouble(parameter.getPreisfueranalyse())
						.toString() + " €";

			Parametergruppen gr = parameter.getParametergruppen();
			int groupId = gr != null ? gr.getId() : -1;

			boolean inGroup = isCompleteParameterGroup(
																		groupId, params);

			//            if (inGroup) {
			//                groups.put(groupId, gr);
			//            }
			String einh = "";
			if (!pos.getEinheiten().getBezeichnung().equals("ohne"))
			{
				einh = pos.getEinheiten().getBezeichnung();
			}

			columns[0] = i + 1;
			columns[1] = parameter.getBezeichnung();
			columns[2] = grenzwert;
			columns[3] = einh;
			columns[4] = wert;
			columns[5] = einh;
			columns[6] = inGroup ? "0,00 €" : gebuehr;
			columns[7] = pos.getGrkl();
			columns[8] = fett;

			values[i] = columns;
		}

		Map<Integer, Parametergruppen> groups =
				new HashMap<Integer, Parametergruppen>(1);

		int numGroups = groups.size();

		if (numGroups == 0)
		{
			return new JRMapDataSource(columnsBescheid, values);
		}

		Object[][] newValues = new Object[elements + numGroups][];
		int i;

		for (i = 0; i < elements; i++)
		{
			newValues[i] = values[i];
		}

		Collection<Parametergruppen> theGroups = groups.values();
		for (Parametergruppen apg : theGroups)
		{

			String gebuehr = new GermanDouble(apg.getPreisfueranalyse())
					.toString() + " €";

			columns = new Object[columnsBescheid.length];
			columns[0] = i + 1;
			columns[1] = apg.getName();
			columns[2] = "";
			columns[3] = "";
			columns[4] = "";
			columns[5] = "";
			columns[6] = gebuehr;
			columns[7] = "";
			columns[8] = "";

			newValues[i] = columns;
			i++;
		}

		return new JRMapDataSource(columnsBescheid, newValues);
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Messstelle */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Get all Messstelle with Probeart(ID)
	 *
	 * @return <code>List&lt;Messstelle&gt;</code>
	 */
	public static List<Messstelle> getProbepktByArtID(Integer atlProbeartID)
	{
		return new DatabaseAccess().executeCriteriaToList(
															DetachedCriteria.forClass(Messstelle.class)
																	.createAlias("objekt", "objekt")
																	.createAlias("objekt.standort", "standort")
																	.createAlias("probeart", "art")
																	.add(Restrictions.eq("objekt.inaktiv", false))
																	.add(Restrictions.eq("art.id", atlProbeartID))
																	.addOrder(Order.asc("standort.strasse"))
																	.addOrder(Order.asc("standort.hausnr")),
															new Messstelle());
	}

	/**
	 * Get all inactive Messstelle
	 *
	 * @return <code>List&lt;Messstelle&gt;</code>
	 */
	public static List<Messstelle> getInaktivProbepkt(Sachbearbeiter sachbearbeiter)
	{

		DetachedCriteria crit = DetachedCriteria.forClass(Messstelle.class)
			.createAlias("objekt", "objekt")
			.createAlias("probenahmes", "probe")
			.add(Restrictions.eq("objekt.inaktiv", true))
			.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		if (sachbearbeiter != null) {
			crit.add(Restrictions.eq("sachbearbeiter", sachbearbeiter));
		}
		return new DatabaseAccess().executeCriteriaToList(crit, new Messstelle());
	}

	/**
	 * Get all Messstelle.
	 *
	 * @return <code>List&lt;Messstelle&gt;</code>
	 */
	public static List<Messstelle> getProbePunkte(Sachbearbeiter sachbearbeiter)
	{
		DetachedCriteria crit =
				DetachedCriteria.forClass(Messstelle.class)
						.createAlias("objekt", "objekt")
						.createAlias("probenahmes", "probe")
						.add(Restrictions.eq("objekt.inaktiv", false))
						.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		if (sachbearbeiter != null) {
			crit.add(Restrictions.eq("sachbearbeiter", sachbearbeiter));
		}
		return new DatabaseAccess().executeCriteriaToList(crit, new Messstelle());
	}

	/**
	 * Get all Messstelle which have Probenahme from Probenehmer
	 * (kennummer starts with "3").
	 *
	 * @return <code>List&lt;Messstelle&gt;</code>
	 */
	public static List<Messstelle> getProbenehmerPunkte(Sachbearbeiter sachbearbeiter)
	{
		DetachedCriteria crit =
				DetachedCriteria.forClass(Messstelle.class)
						.createAlias("objekt", "objekt")
						.createAlias("probenahmes", "probe")
						.add(Restrictions.eq("objekt.inaktiv", false))
						.add(Restrictions.like(
												"probe.kennummer", "3", MatchMode.START))
						// YAY! Finally found the right way to do distinct and get
						// the complete objects! :D
						.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		if (sachbearbeiter != null) {
			crit.add(Restrictions.eq("sachbearbeiter", sachbearbeiter));
		}
		return new DatabaseAccess().executeCriteriaToList(crit, new Messstelle());
	}

	/**
	 * Get all Messstelle which have Probenahme from ESatzung
	 * (kennummer starts with "E").
	 *
	 * @return <code>List&lt;Messstelle&gt;</code>
	 */
	public static List<Messstelle> getESatzungsPunkte(Sachbearbeiter sachbearbeiter)
	{
		DetachedCriteria crit =
		DetachedCriteria.forClass(Messstelle.class)
				.createAlias("objekt", "objekt")
				.createAlias("probenahmes", "probe")
				.add(Restrictions.eq("objekt.inaktiv", false))
				.add(Restrictions.like(
										"probe.kennummer", "E", MatchMode.START))
				.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		if (sachbearbeiter != null) {
			crit.add(Restrictions.eq("sachbearbeiter", sachbearbeiter));
		}
		return new DatabaseAccess().executeCriteriaToList(crit, new Messstelle());
	}

	/**
	 * Get all Messstelle which have Probenahme from UWB
	 * (kennummer starts with "2").
	 *
	 * @return <code>List&lt;Messstelle&gt;</code>
	 */
	public static List<Messstelle> getUWBPunkte(Sachbearbeiter sachbearbeiter)
	{
		DetachedCriteria crit =
		DetachedCriteria.forClass(Messstelle.class)
				.createAlias("objekt", "objekt")
				.createAlias("probenahmes", "probe")
				.add(Restrictions.eq("objekt.inaktiv", false))
				.add(Restrictions.like(
										"probe.kennummer", "2", MatchMode.START))
				.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		if (sachbearbeiter != null) {
			crit.add(Restrictions.eq("sachbearbeiter", sachbearbeiter));
		}
		return new DatabaseAccess().executeCriteriaToList(crit, new Messstelle());
	}

	/**
	 * Get all Messstelle which have Probenahme from Selbstueberwachung
	 * (kennummer starts with "7").
	 *
	 * @return <code>List&lt;Messstelle&gt;</code>
	 */
	public static List<Messstelle> getSelbstueberwPunkte(Sachbearbeiter sachbearbeiter)
	{
		DetachedCriteria crit =
		DetachedCriteria.forClass(Messstelle.class)
				.createAlias("objekt", "objekt")
				.createAlias("probenahmes", "probe")
				.add(Restrictions.eq("objekt.inaktiv", false))
				.add(Restrictions.like(
										"probe.kennummer", "7", MatchMode.START))
				.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		if (sachbearbeiter != null) {
			crit.add(Restrictions.eq("sachbearbeiter", sachbearbeiter));
		}
		return new DatabaseAccess().executeCriteriaToList(crit, new Messstelle());
	}

	/**
	 * Get the one(!) Messstelle for the Klärschlamm
	 *
	 * @param art
	 *            AtlProbe(punkt)art
	 * @param ka
	 *            AtlKlaeranlage
	 * @return Messstelle
	 */
	// TODO: Add DatabaseConstants for these Probepunkte
	// These values here are what we got with the original query which also
	// matched art and ka, but then sorted by objektid and took the first result
	public static Messstelle getKlaerschlammProbepunkt(
		Probeart art, Klaeranlage ka)
	{
//		Integer objektIDs[] =
//		// Probe(punkt)art:
//		{ // Anlieferung | Faulschlamm | Rohschlamm | Zulauf // Kläranlage:
//		24546, 24603, 17796, // Heepen
//				24602, 17797, // Brake
//				42904, 24605, // Obere Lutter
//				24504, 24547, 24604, 24584 // Sennestadt
//											// Verl-Sende
//		};
		return new DatabaseAccess()
				.executeCriteriaToUniqueResult(
												DetachedCriteria.forClass(Messstelle.class)
														.add(Restrictions.eq("probeart", art))
														.add(Restrictions.eq("klaeranlage", ka)),
//														.add(Restrictions.in("id", objektIDs)),
												new Messstelle());
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Sielhaut */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Find Sielhaut starting with the <code>search</code> String
	 *
	 * @return <code>List&lt;Sielhaut&gt;</code>
	 */


	public static Object[] findSielhaut(String search)
	{

		boolean bSearch = (search != null && search.length() > 0);

		String query = "SELECT s.id, s.bezeichnung, s.lage, s.PSielhaut, s.PFirmenprobe, s.PNachprobe, o.inaktiv "
				+ "FROM Sielhaut s, Messstelle m, Objekt o "
				+ "WHERE s.messstelle = m AND m.objekt = o AND s.deleted = false ";
				if (bSearch) {
					query += "AND s.bezeichnung like '" + search + "%' ";
				}
				query +=  "ORDER BY s.PSielhaut DESC, s.PFirmenprobe DESC, o.inaktiv ASC, s.bezeichnung ASC" ;


		List sielhaut = HibernateSessionFactory.currentSession().createQuery(query).list();

		return (Object[]) sielhaut.toArray();
	}


	/**
	 * Find Sielhaut starting with the <code>search</code> String
	 *
	 * @return <code>List&lt;Sielhaut&gt;</code>
	 */


	 public static Object[] findActiveSielhaut(String search) {

		boolean bSearch = (search != null && search.length() > 0);

		 String query = "SELECT s.id, s.bezeichnung, s.lage, s.PSielhaut, s.PFirmenprobe, s.PNachprobe, o.inaktiv "
				+ "FROM Sielhaut s, Messstelle m, Objekt o "
				+ "WHERE s.messstelle = m AND m.objekt = o AND s.deleted = false AND o.inaktiv = false";
				if (bSearch) {
					query += "AND s.bezeichnung like '" + search + "%' ";
				}
				query += " ORDER BY s.bezeichnung ASC, s.PSielhaut DESC, s.PFirmenprobe DESC, o.inaktiv ASC" ;


		List sielhaut = HibernateSessionFactory.currentSession().createQuery(query).list();

		return (Object[]) sielhaut.toArray();
	}


	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class Status */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static Status[] atlStatus = null;

	/**
	 * Get all Status
	 *
	 * @return <code>Status[]</code>
	 */
	public static Status[] getStatus()
	{
		if (DatabaseAtlQuery.atlStatus == null)
		{
			DatabaseAtlQuery.atlStatus =
					DatabaseQuery.getOrderedAll(new Status())
							.toArray(new Status[0]);
		}
		return DatabaseAtlQuery.atlStatus;
	}

	/**
	 * Hole den nächsten Status - für den Wullspuffel ;-)
	 *
	 * @param aktuellerStatus
	 * @return <code>Status</code> nächster Status
	 */
	public static Status getNextStatus(Status aktuellerStatus)
	{
		if (aktuellerStatus.equals(
				DatabaseConstants.ATL_STATUS_PROBENAHMEAUFTRAG_GEDRUCKT))
		{
			return DatabaseConstants.ATL_STATUS_ERGAENZT_UND_FREIGEGEBEN;
		}
		if (aktuellerStatus.equals(
				DatabaseConstants.ATL_STATUS_DATEN_EINGETRAGEN))
		{
			return DatabaseConstants.ATL_STATUS_FREIGEGEBEN_FUER_BESCHEIDDRUCK;
		}
		return aktuellerStatus;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* Queries for package ATL : class ViewAnalyseposition */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	/**
	 * Liefert eine Liste der Analysepositionen mit einem gegebenen Parameter,
	 * einer bestimmten Einheit, an einem bestimmten Probepunkt, die zwischen
	 * <code>beginDate</code> und <code>endDate</code> (inklusive) genommen
	 * wurden. Wenn <code>analyseVon</code> nicht <code>null</code> oder "" ist,
	 * werden nur Analysepositionen geliefert, die von einer bestimmten Stelle
	 * analysiert wurden.
	 *
	 * @param param
	 *            Der Parameter
	 * @param einheit
	 *            Die Einheit
	 * @param punkt
	 *            Der Probepunkt
	 * @param beginDate
	 *            Das Anfangs-Datum
	 * @param endDate
	 *            Das End-Datum
	 * @param analyseVon
	 *            Wo wurde analysiert?
	 * @return Eine Liste mit <code>Analyseposition</code>en
	 */
	public static List<Analyseposition> getAnalysepositionFromView(
		Parameter param, Einheiten einheit, Messstelle punkt,
		Date beginDate, Date endDate, String analyseVon)
	{
		DetachedCriteria detachedCriteria =
				DetachedCriteria.forClass(ViewAtlAnalysepositionAll.class)
						.add(Restrictions.eq("parameterId", param.getOrdnungsbegriff()))
						.add(Restrictions.eq("einheitenId", einheit.getId()))
						.add(Restrictions.eq("probepktId", punkt.getId()))
//						.add(Restrictions.gt("wert", new Float(0)))
						.add(Restrictions.between("datumDerEntnahme", beginDate, endDate))
						.addOrder(Order.asc("datumDerEntnahme"));
		if (analyseVon.equals("Selbstüberwachung"))
		{
			detachedCriteria.add(Restrictions.like(
													"kennummer", "7", MatchMode.START));
		}
		else if (analyseVon != null && !analyseVon.equals(""))
		{
			detachedCriteria.add(Restrictions.like("analyseVon", analyseVon));
		}
		List<ViewAtlAnalysepositionAll> viewResult =
				new DatabaseAccess().executeCriteriaToList(
															detachedCriteria, new ViewAtlAnalysepositionAll());
		List<Analyseposition> result = new ArrayList<Analyseposition>();
		for (ViewAtlAnalysepositionAll viewPos : viewResult)
		{
			result.add(getAnalysepositionFromView(viewPos));
		}
		return result;
	}

	// Dirty cast/copy...
	public static Analyseposition getAnalysepositionFromView(
			ViewAtlAnalysepositionAll viewPos)
	{
		Analyseposition pos = new Analyseposition();
		pos.setId(viewPos.getId());
		pos.setGrkl(viewPos.getGrkl());
		pos.setWert(viewPos.getWert());
		pos.setAnalyseVon(viewPos.getAnalyseVon());
		pos.setBericht(viewPos.getBericht());
		pos.setNormwert(viewPos.getNormwert());
		pos.setEinheiten(Einheiten.findById(viewPos.getEinheitenId()));
		pos.setParameter(Parameter.findById(viewPos.getParameterId()));
		pos.setProbenahme(
				Probenahme.findById(viewPos.getProbenahmeId()));
		pos.setEnabled(viewPos.getEnabled());
		pos.setDeleted(viewPos.getDeleted());
		return pos;
	}
}
