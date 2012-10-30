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
 * A class that represents a row in the VAWS_GEBUEHRENARTEN table. You can
 * customize the behavior of this class by editing the class, {@link
 * VawsGebuehrenarten()}.
 */
public abstract class AbstractVawsGebuehrenarten extends
StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = 5780046754751531630L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer id;

    /** The value of the simple gebuehrenart property. */
    private java.lang.String gebuehrenart;

    /**
     * Simple constructor of AbstractVawsGebuehrenarten instances.
     */
    public AbstractVawsGebuehrenarten() {
    }

    /**
     * Constructor of AbstractVawsGebuehrenarten instances given a simple
     * primary key.
     * @param id
     */
    public AbstractVawsGebuehrenarten(java.lang.Integer id) {
        this.setId(id);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getId() {
        return id;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.hashValue = 0;
        this.id = id;
    }

    /**
     * Return the value of the GEBUEHRENART column.
     * @return java.lang.String
     */
    public java.lang.String getGebuehrenart() {
        return this.gebuehrenart;
    }

    /**
     * Set the value of the GEBUEHRENART column.
     * @param gebuehrenart
     */
    public void setGebuehrenart(java.lang.String gebuehrenart) {
        this.gebuehrenart = gebuehrenart;
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
        if (!(rhs instanceof VawsGebuehrenarten))
            return false;
        VawsGebuehrenarten that = (VawsGebuehrenarten) rhs;
        if (this.getId() != null && that.getId() != null) {
            if (!this.getId().equals(that.getId())) {
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
            int idValue = this.getId() == null ? 0 : this.getId().hashCode();
            result = result * 37 + idValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
