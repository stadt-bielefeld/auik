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

/**
 * A class that represents a row in the ATL_PROBEART table. You can customize
 * the behavior of this class by editing the class, {@link AtlProbeart}.
 */
public abstract class AbstractAtlProbeart implements Serializable {
    private static final long serialVersionUID = 1728274429317812721L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer artId;

    /** The value of the simple art property. */
    private java.lang.String art;

    /**
     * Simple constructor of AbstractAtlProbeart instances.
     */
    public AbstractAtlProbeart() {
    }

    /**
     * Constructor of AbstractAtlProbeart instances given a simple primary key.
     * @param artId
     */
    public AbstractAtlProbeart(java.lang.Integer artId) {
        this.setArtId(artId);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getArtId() {
        return artId;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param artId
     */
    public void setArtId(java.lang.Integer artId) {
        this.hashValue = 0;
        this.artId = artId;
    }

    /**
     * Return the value of the ART column.
     * @return java.lang.String
     */
    public java.lang.String getArt() {
        return this.art;
    }

    /**
     * Set the value of the ART column.
     * @param art
     */
    public void setArt(java.lang.String art) {
        this.art = art;
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
        if (!(rhs instanceof AtlProbeart))
            return false;
        AtlProbeart that = (AtlProbeart) rhs;
        if (this.getArtId() != null && that.getArtId() != null) {
            if (!this.getArtId().equals(that.getArtId())) {
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
            int artIdValue = this.getArtId() == null ? 0 : this.getArtId()
                    .hashCode();
            result = result * 37 + artIdValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
