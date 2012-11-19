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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;

/**
 * A class that represents a row in the 'ANH_40_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh40Fachdaten extends AbstractAnh40Fachdaten implements
    Serializable {
    private static final long serialVersionUID = 8959882532257266750L;

    /**
     * Simple constructor of Anh40Fachdaten instances.
     */
    public Anh40Fachdaten() {
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    /**
     * Find an <code>Anh40Fachdaten</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anh40Fachdaten</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anh40Fachdaten findById(java.lang.Integer id) {
        return (Anh40Fachdaten)
            new DatabaseAccess().get(Anh40Fachdaten.class, id);
    }

    public static boolean merge(Anh40Fachdaten anh40) {
        return new DatabaseAccess().saveOrUpdate(anh40);
    }
}
