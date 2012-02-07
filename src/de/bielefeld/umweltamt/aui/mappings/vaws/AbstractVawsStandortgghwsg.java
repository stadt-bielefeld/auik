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

/**
 * A class that represents a row in the VAWS_STANDORTGGHWSG table. You can
 * customize the behavior of this class by editing the class,
 * {@link VawsStandortgghwsg}.
 */
public abstract class AbstractVawsStandortgghwsg implements Serializable {
    private static final long serialVersionUID = 8529649260862228179L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer standortggid;

    /** The value of the simple standortgg property. */
    private java.lang.String standortgg;

    /**
     * Simple constructor of AbstractVawsStandortgghwsg instances.
     */
    public AbstractVawsStandortgghwsg() {
    }

    /**
     * Constructor of AbstractVawsStandortgghwsg instances given a simple
     * primary key.
     * @param standortggid
     */
    public AbstractVawsStandortgghwsg(java.lang.Integer standortggid) {
        this.setStandortggid(standortggid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getStandortggid() {
        return standortggid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param standortggid
     */
    public void setStandortggid(java.lang.Integer standortggid) {
        this.hashValue = 0;
        this.standortggid = standortggid;
    }

    /**
     * Return the value of the STANDORTGG column.
     * @return java.lang.String
     */
    public java.lang.String getStandortgg() {
        return this.standortgg;
    }

    /**
     * Set the value of the STANDORTGG column.
     * @param standortgg
     */
    public void setStandortgg(java.lang.String standortgg) {
        this.standortgg = standortgg;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the
     * primary key values.
     * @param rhs
     * @return boolean
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs == null)
            return false;
        if (!(rhs instanceof VawsStandortgghwsg))
            return false;
        VawsStandortgghwsg that = (VawsStandortgghwsg) rhs;
        if (this.getStandortggid() != null && that.getStandortggid() != null) {
            if (!this.getStandortggid().equals(that.getStandortggid())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation of the hashCode method conforming to the Bloch pattern
     * with the exception of array properties (these are very unlikely primary
     * key types).
     * @return int
     */
    @Override
    public int hashCode() {
        if (this.hashValue == 0) {
            int result = 17;
            int standortggidValue = this.getStandortggid() == null ? 0 : this
                .getStandortggid().hashCode();
            result = result * 37 + standortggidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
