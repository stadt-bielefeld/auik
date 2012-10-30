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
 * A class that represents a row in the BASIS_OBJEKTARTEN table. You can
 * customize the behavior of this class by editing the class,
 * {@link BasisObjektarten}.
 */
public abstract class AbstractBasisObjektarten extends
    StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = 4925553783726079062L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektartid;

    /** The value of the simple objektart property. */
    private java.lang.String objektart;

    /** The value of the simple abteilung property. */
    private java.lang.String abteilung;

    /** The value of the simple kategorie property. */
    private java.lang.String kategorie;

    /**
     * Simple constructor of AbstractBasisObjektarten instances.
     */
    public AbstractBasisObjektarten() {
    }

    /**
     * Constructor of AbstractBasisObjektarten instances given a simple primary
     * key.
     * @param objektartid
     */
    public AbstractBasisObjektarten(java.lang.Integer objektartid) {
        this.setObjektartid(objektartid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getObjektartid() {
        return objektartid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param objektartid
     */
    public void setObjektartid(java.lang.Integer objektartid) {
        this.hashValue = 0;
        this.objektartid = objektartid;
    }

    /**
     * Return the value of the OBJEKTART column.
     * @return java.lang.String
     */
    public java.lang.String getObjektart() {
        return this.objektart;
    }

    /**
     * Set the value of the OBJEKTART column.
     * @param objektart
     */
    public void setObjektart(java.lang.String objektart) {
        this.objektart = objektart;
    }

    /**
     * Return the value of the ABTEILUNG column.
     * @return java.lang.String
     */
    public java.lang.String getAbteilung() {
        return this.abteilung;
    }

    /**
     * Set the value of the ABTEILUNG column.
     * @param abteilung
     */
    public void setAbteilung(java.lang.String abteilung) {
        this.abteilung = abteilung;
    }

    /**
     * Return the value of the KATEGORIE column.
     * @return java.lang.String
     */
    public java.lang.String getKategorie() {
        return this.kategorie;
    }

    /**
     * Set the value of the KATEGORIE column.
     * @param kategorie
     */
    public void setKategorie(java.lang.String kategorie) {
        this.kategorie = kategorie;
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
        if (!(rhs instanceof BasisObjektarten))
            return false;
        BasisObjektarten that = (BasisObjektarten) rhs;
        if (this.getObjektartid() != null && that.getObjektartid() != null) {
            if (!this.getObjektartid().equals(that.getObjektartid())) {
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
            int objektartidValue = this.getObjektartid() == null ? 0 : this
                .getObjektartid().hashCode();
            result = result * 37 + objektartidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
