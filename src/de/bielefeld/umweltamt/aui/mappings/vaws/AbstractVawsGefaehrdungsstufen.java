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
 * A class that represents a row in the VAWS_GEFAEHRDUNGSSTUFEN table. You can
 * customize the behavior of this class by editing the class, {@link
 * VawsGefaehrdungsstufen()}.
 */
public abstract class AbstractVawsGefaehrdungsstufen extends
StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = 7747510303820611962L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer id;

    /** The value of the simple gefaehrdungsstufen property. */
    private java.lang.String gefaehrdungsstufen;

    /**
     * Simple constructor of AbstractVawsGefaehrdungsstufen instances.
     */
    public AbstractVawsGefaehrdungsstufen() {
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
     * @param kontrollid
     */
    public void setId(java.lang.Integer id) {
        this.hashValue = 0;
        this.id = id;
    }

    /**
     * Return the value of the GEFAEHRDUNGSSTUFEN column.
     * @return java.lang.String
     */
    public java.lang.String getGefaehrdungsstufen() {
        return this.gefaehrdungsstufen;
    }

    /**
     * Set the value of the GEFAEHRDUNGSSTUFEN column.
     * @param gefaehrdungsstufen
     */
    public void setGefaehrdungsstufen(java.lang.String gefaehrdungsstufen) {
        this.gefaehrdungsstufen = gefaehrdungsstufen;
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
        if (!(rhs instanceof VawsKontrollen))
            return false;
        VawsKontrollen that = (VawsKontrollen) rhs;
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
            int kontrollidValue = this.getId() == null ? 0 : this.getId()
                .hashCode();
            result = result * 37 + kontrollidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
