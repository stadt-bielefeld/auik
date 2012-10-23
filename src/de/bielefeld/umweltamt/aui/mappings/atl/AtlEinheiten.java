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

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_EINHEITEN' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AtlEinheiten extends AbstractAtlEinheiten implements Serializable {
    private static final long serialVersionUID = 866939599579322354L;

    /** Simple constructor of AtlEinheiten instances. */
    public AtlEinheiten() {
    }

    /**
     * Constructor of AtlEinheiten instances given a simple primary key.
     * @param id
     */
    public AtlEinheiten(java.lang.Integer id) {
        super(id);
    }

    /* Add customized code below */

    /**
     * @return Der Name der Einheit.
     */
    @Override
    public String toString() {
        String tmp = getBezeichnung();
        if (tmp != null) {
            tmp = tmp.trim();
        }
        return tmp;
    }

    /**
     * Liefert alle in der Einheiten-Tabelle gespeicherten Einheiten.
     * @return Ein Array mit allen Einheiten
     */
    public static AtlEinheiten[] getEinheiten() {
        return (AtlEinheiten[]) new DatabaseAccess()
            .createQuery(
                "FROM AtlEinheiten as einheit " +
                "ORDER BY einheit.bezeichnung")
            .array(new AtlEinheiten[0]);
    }

    /**
     * Liefert eine bestimmte Einheit.
     * @param id Die ID der Einheit
     * @return Die Einheit mit der gegebenen ID oder <code>null</code> falls
     *         diese nicht existiert
     */
    public static AtlEinheiten getEinheit(Integer id) {
        AtlEinheiten einheit = null;

        einheit = (AtlEinheiten) new DatabaseAccess()
                .get(AtlEinheiten.class, id);

        return einheit;
    }
}
