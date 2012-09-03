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

import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.mappings.tipi.AuikWzCode;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * This is a service class for all custom queries.<br>
 * <br>
 * These queries are not in their respective classes because we want to keep
 * them as clean of not-generated code as possible. As most of the custom
 * queries were static anyway, they can just as fine live here.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class DatabaseQuery {

    /* ********************************************************************** */
    /* Queries for package BASIS                                              */
    /* ********************************************************************** */

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisBetreiber                       */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Get all BasisBetreiber with a given search string in the selected
     * property. <br>
     * If property is <code>null</code>, we search in all three properties.
     * @param property Name of the property
     * @param search Search string
     * @return <code>List&lt;BasisBetreiber&gt;<code> List of BasisBetreiber
     *         with the given search string in the given property
     */
    public static List<BasisBetreiber> getBasisBetreiber(
        String property, String search) {

        String modSearch = search.toLowerCase().trim() + "%";
        Log.debug("Suche nach '" + modSearch + "' (" + property + ").");

        DatabaseAccess criteria = new DatabaseAccess()
            .createCriteria(BasisBetreiber.class)
            .addAscOrder("betrname")
            .addAscOrder("betrnamezus");

        if (property == null) {
            // TODO: Uhuh, we need to somehow model this in the DatabaseAccess
            // Or just use the Restrictions everywhere.
            criteria.add(Restrictions.or(
                Restrictions.ilike("betrname", modSearch),
                Restrictions.or(
                    Restrictions.ilike("betranrede", modSearch),
                    Restrictions.ilike("betrnamezus", modSearch))));
        } else if (property.equals("name")) {
            criteria.addRestrictionILike("betrname", modSearch);
        } else if (property.equals("anrede")) {
            criteria.addRestrictionILike("betranrede", modSearch);
        } else if (property.equals("zusatz")) {
            criteria.addRestrictionILike("betrnamezus", modSearch);
        } else {
            Log.debug("Something went really wrong here...");
        }

        return criteria.listCriteriaCastToType(new BasisBetreiber());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
    /* Queries for package BASIS : class BasisStandort                        */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */

    /**
     * Check if a location already exists
     * @param strasse The street
     * @param hausnr The house number
     * @param zusatz Addition to the house number
     * @return <code>true</code>, if the given location exists,
     *         <code>false</code> otherwise
     */
    public static boolean basisStandortExists(
        String strasse, Integer hausnr, String zusatz) {
        return (!(new DatabaseAccess()
            .createCriteria(BasisStandort.class)
            .addRestrictionEqual("strasse", strasse)
            .addRestrictionEqual("hausnr", hausnr)
            .addRestrictionEqual("hausnrzus", zusatz)
            .listCriteria()
            .isEmpty()));
    }

    /* ********************************************************************** */
    /* Queries for package ATL                                                */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* Queries for package INDEINL                                            */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* Queries for package VAWS                                               */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* Queries for package TIPI                                               */
    /* ********************************************************************** */

    /**
     * Get an array of the WZ-Codes which are marked for the Kurzauswahl
     * @return <code>AuikWzCode[]</code>
     */
    public static AuikWzCode[] getAuikWzCodesInKurzAuswahl() {
        return new DatabaseAccess()
            .createCriteria(AuikWzCode.class)
            .addRestrictionEqual("inKurzAuswahl", true)
            .addAscOrder("bezeichnung")
            .arrayCriteria(new AuikWzCode[0]);
    }
}
