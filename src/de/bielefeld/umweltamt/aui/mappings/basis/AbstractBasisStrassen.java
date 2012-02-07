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

/**
 * A class that represents a row in the BASIS_STRASSEN table. You can customize
 * the behavior of this class by editing the class, {@link BasisStrassen}.
 */
public abstract class AbstractBasisStrassen implements Serializable {
    private static final long serialVersionUID = -1425674265183003048L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer strasseid;

    /** The value of the simple strasse property. */
    private java.lang.String strasse;

    /** The value of the simple plz property. */
    private java.lang.String plz;

    /**
     * Simple constructor of AbstractBasisStrassen instances.
     */
    public AbstractBasisStrassen() {
    }

    /**
     * Constructor of AbstractBasisStrassen instances given a simple primary
     * key.
     * @param strasseid
     */
    public AbstractBasisStrassen(java.lang.Integer strasseid) {
        this.setStrasseid(strasseid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getStrasseid() {
        return strasseid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param strasseid
     */
    public void setStrasseid(java.lang.Integer strasseid) {
        this.hashValue = 0;
        this.strasseid = strasseid;
    }

    /**
     * Return the value of the STRASSE column.
     * @return java.lang.String
     */
    public java.lang.String getStrasse() {
        return this.strasse;
    }

    /**
     * Set the value of the STRASSE column.
     * @param strasse
     */
    public void setStrasse(java.lang.String strasse) {
        this.strasse = strasse;
    }

    /**
     * Return the value of the PLZ column.
     * @return java.lang.Integer
     */
    public java.lang.String getPlz() {
        return this.plz;
    }

    /**
     * Set the value of the PLZ column.
     * @param plz
     */
    public void setPlz(java.lang.String plz) {
        this.plz = plz;
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
        if (!(rhs instanceof BasisStrassen))
            return false;
        BasisStrassen that = (BasisStrassen) rhs;
        if (this.getStrasseid() != null && that.getStrasseid() != null) {
            if (!this.getStrasseid().equals(that.getStrasseid())) {
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
            int strasseidValue = this.getStrasseid() == null ? 0 : this
                .getStrasseid().hashCode();
            result = result * 37 + strasseidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
