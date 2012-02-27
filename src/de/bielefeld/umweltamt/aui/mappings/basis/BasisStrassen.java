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

package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'BASIS_STRASSEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class BasisStrassen extends AbstractBasisStrassen implements
    Serializable {
    private static final long serialVersionUID = -3812115749085297644L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of BasisStrassen instances.
     */
    public BasisStrassen() {
    }

    /**
     * Constructor of BasisStrassen instances given a simple primary key.
     * @param strasseid
     */
    public BasisStrassen(java.lang.Integer strasseid) {
        super(strasseid);
    }

    /* Add customized code below */

    /**
     * Liefert den Strassennamen.
     */
    @Override
    public String toString() {
        return super.getStrasse();
    }

    // Nur nötig, weil wir Strassen so komisch handhaben...
    /**
     * Liefert das passende BasisStrassen-Objekt zu einem Strassennamen.
     * @return Das passende BasisStrassen-Objekt oder <code>null</code>, falls
     *         keins diesen Namens gefunden wird.
     */
    public static BasisStrassen getStrasseByName(String name) {
        if (name == null) {
            return null;
        }

        String name2 = name.toLowerCase().trim() + "%";
        log.debug("Suche nach: " + name);
        BasisStrassen result = (BasisStrassen) new DatabaseAccess()
            .createQuery(
                "FROM BasisStrassen str WHERE lower(str.strasse) like :name")
            .setString("name", name2)
            .setMaxResults(1).uniqueResult();
        log.debug("Ergebnis: " + result);

        return result;
    }

    /**
     * Liefert alle vorhandenen Straßennamen als Strings.
     * @return Alle vorhandenen Straßennamen
     */
    public static String[] getStrassen() {
        return (String[]) new DatabaseAccess().createQuery(
            "SELECT strassen.strasse "
                + "FROM BasisStrassen strassen "
                + "ORDER BY strassen.strasse")
            .setCacheable(true)
            .setCacheRegion("strassenliste")
            .array(new String[0]);
    }
}
