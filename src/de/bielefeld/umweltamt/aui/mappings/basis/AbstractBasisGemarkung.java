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

import de.bielefeld.umweltamt.aui.mappings.StupidHelperClassWhichWillBeGoneSoon;

/**
 * A class that represents a row in the BASIS_GEMARKUNG table. You can customize
 * the behavior of this class by editing the class, {@link BasisGemarkung}.
 */
public abstract class AbstractBasisGemarkung extends
StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = -8519031645797143745L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer gemarkungid;

    /** The value of the simple gemarkung property. */
    private java.lang.String gemarkung;

    /**
     * Simple constructor of AbstractBasisGemarkung instances.
     */
    public AbstractBasisGemarkung() {
    }

    /**
     * Constructor of AbstractBasisGemarkung instances given a simple primary
     * key.
     * @param gemarkungid
     */
    public AbstractBasisGemarkung(java.lang.Integer gemarkungid) {
        this.setGemarkungid(gemarkungid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getGemarkungid() {
        return gemarkungid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param gemarkungid
     */
    public void setGemarkungid(java.lang.Integer gemarkungid) {
        this.hashValue = 0;
        this.gemarkungid = gemarkungid;
    }

    /**
     * Return the value of the GEMARKUNG column.
     * @return java.lang.String
     */
    public java.lang.String getGemarkung() {
        return this.gemarkung;
    }

    /**
     * Set the value of the GEMARKUNG column.
     * @param gemarkung
     */
    public void setGemarkung(java.lang.String gemarkung) {
        this.gemarkung = gemarkung;
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
        if (!(rhs instanceof BasisGemarkung))
            return false;
        BasisGemarkung that = (BasisGemarkung) rhs;
        if (this.getGemarkungid() != null && that.getGemarkungid() != null) {
            if (!this.getGemarkungid().equals(that.getGemarkungid())) {
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
            int gemarkungidValue = this.getGemarkungid() == null ? 0 : this
                .getGemarkungid().hashCode();
            result = result * 37 + gemarkungidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
