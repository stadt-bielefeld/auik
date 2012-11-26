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

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsAbfuellflaeche;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsFachdaten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * This is a service class for all custom queries from the vaws package.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseVawsQuery extends DatabaseTipiQuery {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /* ********************************************************************** */
    /* Queries for package VAWS                                               */
    /* ********************************************************************** */

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package VAWS: class VawsAbfuellflaeche                     */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get a VawsAbfluellflaeche (create a new one if needed)
     * @param fachdaten VawsFachdaten
     * @return VawsAbfuellflaeche
     */
    public static VawsAbfuellflaeche getAbfuellflaeche(
        VawsFachdaten fachdaten) {
        if (fachdaten == null
            || !fachdaten.getAnlagenart().equals(
                DatabaseConstants.VAWS_ANLAGENART_ABFUELLFLAECHE)) {
            return null;
        }
        VawsAbfuellflaeche flaeche = new DatabaseAccess()
            .executeCriteriaToUniqueResult(
                DetachedCriteria.forClass(VawsAbfuellflaeche.class)
// TODO: For some reason this throws strange exceptions.
// Using the id directly is only a workaround and somebody should take a longer
// look at this at some point in time... :(
//                    .add(Restrictions.eq("vawsFachdaten", fachdaten)),
                    .add(Restrictions.eq(
                        "behaelterid", fachdaten.getBehaelterid())),
                new VawsAbfuellflaeche());
        if (flaeche == null) {
            // Bei so ziemlich 95% aller Tankstellen gibts ein VawsFachdaten-
            // Objekt, aber kein VawsAbfuellflaechen-Objekt.
            // Seems like it's not a bug, it's a feature...

            // Also legen wir in diesen Füllen einfach ein neues
            // VawsAbfuellflaechen-Objekt an.

            // Das selbe tun wir bei einem noch ungespeicherten
            // neuen VawsFachdaten-Objekt.
            flaeche = new VawsAbfuellflaeche();
            flaeche.setVawsFachdaten(fachdaten);
            log.debug("Neue Fläche für '" + fachdaten + "' erzeugt!");
        }
        return flaeche;
    }

    private static String[] vawsBodenflaechenausf = null;
    /**
     * Get all used Bodenflaechenausf from VawsAbfuellflaeche
     * @return <code>String[]</code>
     */
    public static String[] getVawsBodenflaechenausf() {
        if (DatabaseVawsQuery.vawsBodenflaechenausf == null) {
            DatabaseVawsQuery.vawsBodenflaechenausf = new DatabaseAccess()
                .executeCriteriaToArray(
                    DetachedCriteria.forClass(VawsAbfuellflaeche.class)
                        .setProjection(Projections.distinct(
                            Projections.property("bodenflaechenausf")))
                        .addOrder(Order.asc("bodenflaechenausf")),
                    new String[0]);
        }
        return DatabaseVawsQuery.vawsBodenflaechenausf;
    }

    private static String[] vawsNiederschlagschutz = null;
    /**
     * Get all used Niederschlagschutz from VawsAbfuellflaeche
     * @return <code>String[]</code>
     */
    public static String[] getVawsNiederschlagschutz() {
        if (DatabaseVawsQuery.vawsNiederschlagschutz == null) {
            DatabaseVawsQuery.vawsNiederschlagschutz = new DatabaseAccess()
                .executeCriteriaToArray(
                    DetachedCriteria.forClass(VawsAbfuellflaeche.class)
                        .setProjection(Projections.distinct(
                            Projections.property("niederschlagschutz")))
                        .addOrder(Order.asc("niederschlagschutz")),
                    new String[0]);
        }
        return DatabaseVawsQuery.vawsNiederschlagschutz;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package VAWS: class VawsFachdaten                          */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get VawsFachdaten for a given BasisObjekt.
     * TODO: We could use objekt.getVawsFachdatens()...
     * @param objekt BasisObjekt
     * @return <code>List&lt;VawsFachdaten&gt;</code>
     */
    public static List<VawsFachdaten> getVawsFachdatenByObjekt(
        BasisObjekt objekt) {
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(VawsFachdaten.class)
                .add(Restrictions.eq("basisObjekt", objekt))
                .addOrder(Order.desc("stillegungsdatum"))
                .addOrder(Order.asc("anlagenart"))
                .addOrder(Order.asc("herstellnr")),
            new VawsFachdaten());
    }

    private static String[] vawsAusfuehrung = null;
    /**
     * Get all used Ausführungen from VawsFachdaten
     * @return <code>String[]</code>
     */
    public static String[] getVawsAusfuehrungen() {
        if (DatabaseVawsQuery.vawsAusfuehrung == null) {
            DatabaseVawsQuery.vawsAusfuehrung = new DatabaseAccess()
                .executeCriteriaToArray(
                    DetachedCriteria.forClass(VawsFachdaten.class)
                        .setProjection(Projections.distinct(
                            Projections.property("ausfuehrung")))
                        .addOrder(Order.asc("ausfuehrung")),
                    new String[0]);
        }
        return DatabaseVawsQuery.vawsAusfuehrung;
    }

    /**
     * Everything that is NOT (Abfüllfläche OR Rohrleitung) is Lageranlage.
     * TODO: Check this. We have a Anlagenart Lageranlage, but also some others
     * @param fachdaten VawsFachdaten
     * @return <code>true</code>, if Anlagenart is NOT
     *         (Abfüllfläche OR Rohrleitung),
     *         <code>false</code>, otherwise
     */
    public static boolean isLageranlage(VawsFachdaten fachdaten) {
        return (!(
            fachdaten.getAnlagenart().equals(
                DatabaseConstants.VAWS_ANLAGENART_ABFUELLFLAECHE) ||
            fachdaten.getAnlagenart().equals(
                DatabaseConstants.VAWS_ANLAGENART_ROHRLEITUNG)));
    }
}
