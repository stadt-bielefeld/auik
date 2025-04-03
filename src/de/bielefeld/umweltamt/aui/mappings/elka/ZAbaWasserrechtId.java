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

// Generated by Hibernate Tools 5.0.0.Final

package de.bielefeld.umweltamt.aui.mappings.elka;

import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;

/**
 * A class that represents a row in the ZAbaWasserrechtId database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class ZAbaWasserrechtId  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forZAbaWasserrechtId;

    /* Primary key, foreign keys (relations) and table columns */
    private long wasserrechtNr;
    private long anlageNr;

    /** Default constructor */
    public ZAbaWasserrechtId() {
        // This place is intentionally left blank.
    }


    /** Full constructor */
    public ZAbaWasserrechtId(
        long wasserrechtNr, long anlageNr) {
        this.wasserrechtNr = wasserrechtNr;
        this.anlageNr = anlageNr;
    }

    /* Setter and getter methods */
    public long getWasserrechtNr() {
        return this.wasserrechtNr;
    }

    public void setWasserrechtNr(long wasserrechtNr) {
        this.wasserrechtNr = wasserrechtNr;
    }

    public long getAnlageNr() {
        return this.anlageNr;
    }

    public void setAnlageNr(long anlageNr) {
        this.anlageNr = anlageNr;
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
        //TODO
        return "";
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("wasserrechtNr").append("='").append(getWasserrechtNr()).append("' ");
        buffer.append("anlageNr").append("='").append(getAnlageNr()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    /* Custom code goes below here! */

}
