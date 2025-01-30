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

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * This is a service class for all custom queries from all package.<br>
 * <br>
 * These queries are not in their respective classes because we want to keep
 * them as clean of not-generated code as possible. As most of the custom
 * queries were static anyway, they can just as fine live here.<br>
 * <br>
 * This class has been split up in six separate classes (general methods [here]
 * and methods for the classes in our five packages).<br>
 * I did this with an inheritance chain. Do forgive me...
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public abstract class DatabaseQuery extends DatabaseAtlQuery {
// Essentially this is somewhat like this:
// DatabaseQuery extends DatabaseAtlQuery, DatabaseBasisQuery,
//                       DatabaseIndeinlQuery, DatabaseVawsQuery,
//                       DatabaseTipiQuery

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

	//Separator char used to split address strings
	public static final String ADDRESS_SEPARATOR = "%";
    //String used as placeholder for empty house numbers
    public static final String HOUSE_NUMBER_PLACEHOLDER = "-1";

    /* ********************************************************************** */
    /* General Queries for all packages                                       */
    /* ********************************************************************** */

    /**
     * Get a list of all <code>T</code>
     * @return <code>List&lt;T&gt;</code>
     *         all <code>T</code>
     */
    public static <T> List<T> getAll(T type) {
        log.debug("Getting all " + type.getClass().getSimpleName()
            +  " instances");
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(type.getClass()), type);
    }

    /**
     * Get an ordered list of all <code>T</code> ordered by the id
     * @return <code>List&lt;T&gt;</code>
     *         all <code>T</code>
     */
    public static <T> List<T> getOrderedAll(T type) {
        log.debug("Getting all " + type.getClass().getSimpleName()
            +  " instances");
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(type.getClass())
                .addOrder(new DatabaseAccess().getIdOrder(type.getClass())),
            type);
    }

    /**
     * Get an ordered list of all <code>T</code> ordered by a given property
     * @return <code>List&lt;T&gt;</code>
     *         all <code>T</code>
     */
    public static <T> List<T> getOrderedAll(T type, String propertyName) {
        log.debug("Getting all " + type.getClass().getSimpleName()
            +  " instances");
        return new DatabaseAccess().executeCriteriaToList(
            DetachedCriteria.forClass(type.getClass())
                .addOrder(Order.asc(propertyName)),
            type);
    }


}
