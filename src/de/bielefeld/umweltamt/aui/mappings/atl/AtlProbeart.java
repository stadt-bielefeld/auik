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

import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_PROBEART' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AtlProbeart extends AbstractAtlProbeart implements Serializable {
    private static final long serialVersionUID = -4946349358783685742L;

    /**
     * Simple constructor of AtlProbeart instances.
     */
    public AtlProbeart() {
        // This place is intentionally left blank.
    }

    /**
     * Constructor of AtlProbeart instances given a simple primary key.
     * @param artId
     */
    public AtlProbeart(java.lang.Integer artId) {
        super(artId);
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
     * Get a string representation for the gui
     * @return String
     */
    public String toGuiString() {
        return getArt();
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("id").append("='").append(getArtId()).append("' ");
        buffer.append("art").append("='").append(getArt()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    /* Add customized code below */

    /**
     * Liefert alle vorhandenen Probearten.
     * @return Alle vorhandenen Probearten
     */
    public static AtlProbeart[] getProbearten() {
        String suchString = "FROM AtlProbeart art ORDER BY art.artId";

        return (AtlProbeart[]) new DatabaseAccess().createQuery(suchString)
                .setCacheable(true)
                .setCacheRegion("probeartliste")
                .array(new AtlProbeart[0]);
    }

    /**
     * Liefert eine bestimmte Probeart.
     * @param id Die ID der Probeart
     * @return Die Probeart mit der gegebenen ID oder <code>null</code> falls
     *         diese nicht existiert
     */
    public static AtlProbeart getProbeart(Integer id) {
        return (AtlProbeart) new DatabaseAccess().get(AtlProbeart.class, id);
    }
}
