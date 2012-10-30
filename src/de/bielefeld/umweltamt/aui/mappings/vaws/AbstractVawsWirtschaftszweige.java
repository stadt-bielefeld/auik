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

import de.bielefeld.umweltamt.aui.mappings.StupidHelperClassWhichWillBeGoneSoon;

/**
 * A class that represents a row in the VAWS_WIRTSCHAFTSZWEIGE table. You can
 * customize the behavior of this class by editing the class,
 * {@link VawsWirtschaftszweige}.
 */
public abstract class AbstractVawsWirtschaftszweige extends
StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = -5847405335334619968L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer wirtschaftszweigid;

    /** The value of the simple wirtschaftszweig property. */
    private java.lang.String wirtschaftszweig;

    /**
     * Simple constructor of AbstractVawsWirtschaftszweige instances.
     */
    public AbstractVawsWirtschaftszweige() {
    }

    /**
     * Constructor of AbstractVawsWirtschaftszweige instances given a simple
     * primary key.
     * @param wirtschaftszweigid
     */
    public AbstractVawsWirtschaftszweige(java.lang.Integer wirtschaftszweigid) {
        this.setWirtschaftszweigid(wirtschaftszweigid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getWirtschaftszweigid() {
        return wirtschaftszweigid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param wirtschaftszweigid
     */
    public void setWirtschaftszweigid(java.lang.Integer wirtschaftszweigid) {
        this.hashValue = 0;
        this.wirtschaftszweigid = wirtschaftszweigid;
    }

    /**
     * Return the value of the WIRTSCHAFTSZWEIG column.
     * @return java.lang.String
     */
    public java.lang.String getWirtschaftszweig() {
        return this.wirtschaftszweig;
    }

    /**
     * Set the value of the WIRTSCHAFTSZWEIG column.
     * @param wirtschaftszweig
     */
    public void setWirtschaftszweig(java.lang.String wirtschaftszweig) {
        this.wirtschaftszweig = wirtschaftszweig;
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
        if (!(rhs instanceof VawsWirtschaftszweige))
            return false;
        VawsWirtschaftszweige that = (VawsWirtschaftszweige) rhs;
        if (this.getWirtschaftszweigid() != null
            && that.getWirtschaftszweigid() != null) {
            if (!this.getWirtschaftszweigid().equals(
                that.getWirtschaftszweigid())) {
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
            int wirtschaftszweigidValue = this.getWirtschaftszweigid() == null ? 0
                : this.getWirtschaftszweigid().hashCode();
            result = result * 37 + wirtschaftszweigidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
