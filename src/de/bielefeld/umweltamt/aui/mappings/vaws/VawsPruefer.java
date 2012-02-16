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

package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'VAWS_PRUEFER' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class VawsPruefer extends AbstractVawsPruefer implements Serializable {
    private static final long serialVersionUID = -8186136740692550007L;

    /**
     * Simple constructor of VawsPruefer instances.
     */
    public VawsPruefer() {
    }

    /* Add customized code below */

    /**
     * Liefert alle Prüfer für die Sachverständigenprüfung.
     * @return Ein Array mit den Namen aller Prüfer.
     */
    public static String[] getAllPruefer() {
        return (String[]) new DatabaseAccess()
            .createQuery(
                "SELECT prf.pruefer "
                + "FROM VawsPruefer prf "
                + "ORDER BY prf.pruefer")
            .setCacheable(true)
            .setCacheRegion("vawsprfliste")
            .array(new String[0]);
    }
}
