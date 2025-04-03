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

package de.bielefeld.umweltamt.aui.mappings.oberflgw;

import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;

/**
 * A class that represents a row in the ZRbfSchutzgueterId database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class ZRbfSchutzgueterId  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forZRbfSchutzgueterId;

    /* Primary key, foreign keys (relations) and table columns */
    private long sbNr;
    private Integer schutzgueterOpt;

    /** Default constructor */
    public ZRbfSchutzgueterId() {
        // This place is intentionally left blank.
    }


    /** Full constructor */
    public ZRbfSchutzgueterId(
        long sbNr, Integer schutzgueterOpt) {
        this.sbNr = sbNr;
        this.schutzgueterOpt = schutzgueterOpt;
    }

    /* Setter and getter methods */
    public long getSbNr() {
        return this.sbNr;
    }

    public void setSbNr(long sbNr) {
        this.sbNr = sbNr;
    }

    public Integer getSchutzgueterOpt() {
        return this.schutzgueterOpt;
    }

    public void setSchutzgueterOpt(Integer schutzgueterOpt) {
        this.schutzgueterOpt = schutzgueterOpt;
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
        buffer.append("sbNr").append("='").append(getSbNr()).append("' ");
        buffer.append("schutzgueterOpt").append("='").append(getSchutzgueterOpt()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    /* Custom code goes below here! */

}
