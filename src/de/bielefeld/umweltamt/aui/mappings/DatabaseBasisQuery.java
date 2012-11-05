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
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * This is a service class for all custom queries from the basis package.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 * @see de.bielefeld.umweltamt.aui.mappings.DatabaseQuery
 */
abstract class DatabaseBasisQuery extends DatabaseIndeinlQuery {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

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
     * @return <code>List&lt;BasisBetreiber&gt;</code> List of BasisBetreiber
     *         with the given search string in the given property
     */
    public static List<BasisBetreiber> getBasisBetreiber(
        String property, String search) {

        String modSearch = search.toLowerCase().trim() + "%";
        log.debug("Suche nach '" + modSearch + "' (" + property + ").");

        DetachedCriteria criteria =
            DetachedCriteria.forClass(BasisBetreiber.class)
                .addOrder(Order.asc("betrname"))
                .addOrder(Order.asc("betrnamezus"));

        if (property == null) {
            criteria.add(Restrictions.or(
                Restrictions.ilike("betrname", modSearch),
                Restrictions.or(
                    Restrictions.ilike("betranrede", modSearch),
                    Restrictions.ilike("betrnamezus", modSearch))));
        } else if (property.equals("name")) {
            criteria.add(Restrictions.ilike("betrname", modSearch));
        } else if (property.equals("anrede")) {
            criteria.add(Restrictions.ilike("betranrede", modSearch));
        } else if (property.equals("zusatz")) {
            criteria.add(Restrictions.ilike("betrnamezus", modSearch));
        } else {
            log.debug("Something went really wrong here...");
        }

        return new DatabaseAccess().executeCriteriaToList(
            criteria, new BasisBetreiber());
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
        return (!(new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(BasisStandort.class)
                .add(Restrictions.eq("strasse", strasse))
                .add(Restrictions.eq("hausnr", hausnr))
                .add(DatabaseAccess.getRestrictionsEqualOrNull(
                    "hausnrzus", zusatz)),
            new BasisStandort())
            .isEmpty()));
    }
}
