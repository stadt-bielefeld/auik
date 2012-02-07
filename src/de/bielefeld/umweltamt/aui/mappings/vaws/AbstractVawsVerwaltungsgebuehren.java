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

/**
 * A class that represents a row in the VAWS_VERWALTUNGSGEBUEHREN table. You can
 * customize the behavior of this class by editing the class, {@link
 * VawsVerwaltungsgebuehren()}.
 */
public abstract class AbstractVawsVerwaltungsgebuehren implements Serializable {
    private static final long serialVersionUID = 4394169597947069630L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer id;

    /** The value of the simple behaelterid property. */
    private VawsFachdaten vawsFachdaten;

    /** The value of the simple datum property. */
    private java.util.Date datum;

    /** The value of the simple gebuehrenart property. */
    private VawsGebuehrenarten vawsGebuehrenart;

    /** The value of the simple betrag property. */
    private java.lang.Float betrag;

    /** The value of the simple abschnitt property. */
    private java.lang.String abschnitt;

    /** The value of the simple kassenzeichen property. */
    private java.lang.String kassenzeichen;

    /**
     * Simple constructor of AbstractVawsVerwaltungsgebuehren instances.
     */
    public AbstractVawsVerwaltungsgebuehren() {
    }

    /**
     * Constructor of AbstractVawsVerwaltungsgebuehren instances given a simple
     * primary key.
     * @param id
     */
    public AbstractVawsVerwaltungsgebuehren(java.lang.Integer id) {
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
     * Return the value of the BEHAELTERID column.
     * @return java.lang.Integer
     */
    public VawsFachdaten getVawsFachdaten() {
        return this.vawsFachdaten;
    }

    /**
     * Set the value of the BEHAELTERID column.
     * @param behaelterid
     */
    public void setVawsFachdaten(VawsFachdaten vawsFachdaten) {
        this.vawsFachdaten = vawsFachdaten;
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
     * Return the value of the GEBUEHRENART column.
     * @return java.lang.String
     */
    public VawsGebuehrenarten getVawsGebuehrenart() {
        return this.vawsGebuehrenart;
    }

    /**
     * Set the value of the GEBUEHRENART column.
     * @param gebuehrenart
     */
    public void setVawsGebuehrenart(VawsGebuehrenarten vawsGebuehrenart) {
        this.vawsGebuehrenart = vawsGebuehrenart;
    }

    /**
     * Return the value of the BETRAG column.
     * @return java.lang.Float
     */
    public java.lang.Float getBetrag() {
        return this.betrag;
    }

    /**
     * Set the value of the BETRAG column.
     * @param betrag
     */
    public void setBetrag(java.lang.Float betrag) {
        this.betrag = betrag;
    }

    /**
     * Return the value of the ABSCHNITT column.
     * @return java.lang.String
     */
    public java.lang.String getAbschnitt() {
        return this.abschnitt;
    }

    /**
     * Set the value of the ABSCHNITT column.
     * @param abschnitt
     */
    public void setAbschnitt(java.lang.String abschnitt) {
        this.abschnitt = abschnitt;
    }

    /**
     * Return the value of the KASSENZEICHEN column.
     * @return java.lang.String
     */
    public java.lang.String getKassenzeichen() {
        return this.kassenzeichen;
    }

    /**
     * Set the value of the KASSENZEICHEN column.
     * @param kassenzeichen
     */
    public void setKassenzeichen(java.lang.String kassenzeichen) {
        this.kassenzeichen = kassenzeichen;
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
        if (!(rhs instanceof VawsVerwaltungsgebuehren))
            return false;
        VawsVerwaltungsgebuehren that = (VawsVerwaltungsgebuehren) rhs;
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
