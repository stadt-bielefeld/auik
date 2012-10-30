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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.StupidHelperClassWhichWillBeGoneSoon;

/**
 * A class that represents a row in the ANH_49_ORTSTERMINE table. You can
 * customize the behavior of this class by editing the class, {@link
 * Anh49Ortstermine()}.
 */
public abstract class AbstractAnh49Ortstermine extends
    StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = 1533446851683277791L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer ortsterminid;

    /** The value of the anh49Fachdaten association. */
    private Anh49Fachdaten anh49Fachdaten;
    // private BasisObjekt basisObjekt;

    /** The value of the simple datum property. */
    private java.util.Date datum;

    /** The value of the simple sachbearbeiterIn property. */
    private java.lang.String sachbearbeiterIn;

    /** The value of the simple bemerkungen property. */
    private java.lang.String bemerkungen;

    /**
     * Simple constructor of AbstractAnh49Ortstermine instances.
     */
    public AbstractAnh49Ortstermine() {
    }

    /**
     * Constructor of AbstractAnh49Ortstermine instances given a simple primary
     * key.
     * @param ortsterminid
     */
    public AbstractAnh49Ortstermine(java.lang.Integer ortsterminid) {
        this.setOrtsterminid(ortsterminid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getOrtsterminid() {
        return ortsterminid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param ortsterminid
     */
    public void setOrtsterminid(java.lang.Integer ortsterminid) {
        this.hashValue = 0;
        this.ortsterminid = ortsterminid;
    }

    /**
     * Return the value of the ANH49ID column.
     * @return Anh49Fachdaten
     */
    public Anh49Fachdaten getAnh49Fachdaten() {
        return this.anh49Fachdaten;
    }

    /*public BasisObjekt getBasisObjekt()
    {
        return this.basisObjekt;
    }*/

    /**
     * Set the value of the ANH49ID column.
     * @param anh49Fachdaten
     */
    public void setAnh49Fachdaten(Anh49Fachdaten anh49Fachdaten) {
        this.anh49Fachdaten = anh49Fachdaten;
    }

    /*public void setBasisObjekt(BasisObjekt basisObjekt)
    {
        this.basisObjekt = basisObjekt;
    }*/

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
     * Return the value of the SACHBEARBEITER_IN column.
     * @return java.lang.String
     */
    public java.lang.String getSachbearbeiterIn() {
        return this.sachbearbeiterIn;
    }

    /**
     * Set the value of the SACHBEARBEITER_IN column.
     * @param sachbearbeiterIn
     */
    public void setSachbearbeiterIn(java.lang.String sachbearbeiterIn) {
        this.sachbearbeiterIn = sachbearbeiterIn;
    }

    /**
     * Return the value of the BEMERKUNGEN column.
     * @return java.lang.String
     */
    public java.lang.String getBemerkungen() {
        return this.bemerkungen;
    }

    /**
     * Set the value of the BEMERKUNGEN column.
     * @param bemerkungen
     */
    public void setBemerkungen(java.lang.String bemerkungen) {
        this.bemerkungen = bemerkungen;
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
        if (!(rhs instanceof Anh49Ortstermine))
            return false;
        Anh49Ortstermine that = (Anh49Ortstermine) rhs;
        if (this.getOrtsterminid() != null && that.getOrtsterminid() != null) {
            if (!this.getOrtsterminid().equals(that.getOrtsterminid())) {
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
            int ortsterminidValue = this.getOrtsterminid() == null ? 0 : this
                .getOrtsterminid().hashCode();
            result = result * 37 + ortsterminidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
