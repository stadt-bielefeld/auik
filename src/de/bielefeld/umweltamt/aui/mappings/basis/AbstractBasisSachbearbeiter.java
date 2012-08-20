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

public abstract class AbstractBasisSachbearbeiter extends
AbstractVirtuallyDeletableDatabaseTable implements Serializable {
    private static final long serialVersionUID = 5350350541608539779L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.String kennummer;

    /** The value of the simple name property. */
    private java.lang.String name;

    /** The value of the simple telefon property. */
    private java.lang.String telefon;

    /** The value of the simple gehoertzuarbeitsgr property. */
    private java.lang.String gehoertzuarbeitsgr;

    /**
     * Simple constructor of AbstractBasisSachbearbeiter instances.
     */
    public AbstractBasisSachbearbeiter() {
    }

    /**
     * Constructor of AbstractBasisSachbearbeiter instances given a simple
     * primary key.
     * @param kennummer
     */
    public AbstractBasisSachbearbeiter(java.lang.String kennummer) {
        this.setKennummer(kennummer);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.String getKennummer() {
        return kennummer;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param kennummer
     */
    public void setKennummer(java.lang.String kennummer) {
        this.hashValue = 0;
        this.kennummer = kennummer;
    }

    /**
     * Return the value of the Name column.
     * @return java.lang.String
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * Set the value of the Name column.
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * Return the value of the Telefon column.
     * @return java.lang.String
     */
    public java.lang.String getTelefon() {
        return this.telefon;
    }

    /**
     * Set the value of the Telefon column.
     * @param telefon
     */
    private void setTelefon(java.lang.String telefon) {
        this.telefon = telefon;
    }

    /**
     * Return the value of the Gehoertzuarbeitsgr column.
     * @return java.lang.Integer
     */
    public java.lang.String getGehoertzuarbeitsgr() {
        return this.gehoertzuarbeitsgr;
    }

    /**
     * Set the value of the Istadministrator column.
     * @param gehoertzuarbeitsgr
     */
    private void setGehoertzuarbeitsgr(java.lang.String gehoertzuarbeitsgr) {
        this.gehoertzuarbeitsgr = gehoertzuarbeitsgr;
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
        if (!(rhs instanceof BasisSachbearbeiter))
            return false;
        BasisSachbearbeiter that = (BasisSachbearbeiter) rhs;
        if (this.getKennummer() != null && that.getKennummer() != null) {
            if (!this.getKennummer().equals(that.getKennummer())) {
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
            int kennummerValue = this.getKennummer() == null ? 0 : this
                .getKennummer().hashCode();
            result = result * 37 + kennummerValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
