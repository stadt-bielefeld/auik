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

import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;

/**
 * A class that represents a row in the VAWS_OBJEKTCHRONO table. You can
 * customize the behavior of this class by editing the class, {@link
 * VawsObjektchrono()}.
 */
public abstract class AbstractBasisObjektchrono extends
    AbstractVirtuallyDeletableDatabaseTable implements Serializable {
    private static final long serialVersionUID = 3677261760395223211L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer id;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

    /** The value of the simple datum property. */
    private java.util.Date datum;

    /** The value of the simple sachverhalt property. */
    private java.lang.String sachverhalt;

    /** The value of the simple wv property. */
    private java.util.Date wv;

    /** The value of the simple sachverhalt property. */
    private java.lang.String sachbearbeiter;

    /**
     * Simple constructor of AbstractVawsObjektchrono instances.
     */
    public AbstractBasisObjektchrono() {
    }

    /**
     * Constructor of AbstractVawsObjektchrono instances given a simple primary
     * key.
     * @param id
     */
    public AbstractBasisObjektchrono(java.lang.Integer id) {
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
     * Return the value of the OBJEKTID column.
     * @return BasisObjekt
     */
    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }

    /**
     * Set the value of the OBJEKTID column.
     * @param basisObjekt
     */
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    /**
     * Return the value of the DATUM column.
     * @return java.util.Date
     */
    public java.util.Date getDatum() {
        return this.datum;
    }

    /**
     * Set the value of the DATUM column.
     * @param datum
     */
    public void setDatum(java.util.Date datum) {
        this.datum = datum;
    }

    /**
     * Return the value of the SACHVERHALT column.
     * @return java.lang.String
     */
    public java.lang.String getSachverhalt() {
        return this.sachverhalt;
    }

    /**
     * Set the value of the SACHVERHALT column.
     * @param sachverhalt
     */
    public void setSachverhalt(java.lang.String sachverhalt) {
        this.sachverhalt = sachverhalt;
    }

    /**
     * Return the value of the WV column.
     * @return java.util.Date
     */
    public java.util.Date getWv() {
        return this.wv;
    }

    /**
     * Set the value of the WV column.
     * @param wv
     */
    public void setWv(java.util.Date wv) {
        this.wv = wv;
    }

    /**
     * Return the value of the SACHVERHALT column.
     * @return java.lang.String
     */
    public java.lang.String getSachbearbeiter() {
        return this.sachbearbeiter;
    }

    /**
     * Set the value of the SACHVERHALT column.
     * @param sachverhalt
     */
    public void setSachbearbeiter(java.lang.String sachbearbeiter) {
        this.sachbearbeiter = sachbearbeiter;
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
        if (!(rhs instanceof BasisObjektchrono))
            return false;
        BasisObjektchrono that = (BasisObjektchrono) rhs;
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
