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
public abstract class AbstractAnh49Kontrollen extends
    StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = -6940070023872922402L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer id;

    /** The value of the anh49Fachdaten association. */
    private Anh49Fachdaten anh49Fachdaten;

    /** The value of the simple pruefdatumdatum property. */
    private java.util.Date pruefdatum;

    /** The value of the simple naechstepruefung property. */
    private java.util.Date naechstepruefung;

    /** The value of the simple pruefergebnis property. */
    private java.lang.String pruefergebnis;

    /**
     * Simple constructor of AbstractAnh49Ortstermine instances.
     */
    public AbstractAnh49Kontrollen() {
    }

    /**
     * Constructor of AbstractAnh49Ortstermine instances given a simple primary
     * key.
     * @param ortsterminid
     */
    public AbstractAnh49Kontrollen(java.lang.Integer id) {
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
     * @param ortsterminid
     */
    public void setId(java.lang.Integer id) {
        this.hashValue = 0;
        this.id = id;
    }

    /**
     * Return the value of the ANH49ID column.
     * @return Anh49Fachdaten
     */
    public Anh49Fachdaten getAnh49Fachdaten() {
        return this.anh49Fachdaten;
    }

    /**
     * Set the value of the ANH49ID column.
     * @param anh49Fachdaten
     */
    public void setAnh49Fachdaten(Anh49Fachdaten anh49Fachdaten) {
        this.anh49Fachdaten = anh49Fachdaten;
    }

    /**
     * Return the value of the pruefdatum column.
     * @return java.util.Date
     */
    public java.util.Date getPruefdatum() {
        return this.pruefdatum;
    }

    /**
     * Set the value of the pruefdatum column.
     * @param pruefdatumdatum
     */
    public void setPruefdatum(java.util.Date pruefdatum) {
        this.pruefdatum = pruefdatum;
    }

    /**
     * Return the value of the naechstepruefung column.
     * @return java.util.Date
     */
    public java.util.Date getNaechstepruefung() {
        return this.naechstepruefung;
    }

    /**
     * Set the value of the naechstepruefung column.
     * @param pruefdatumdatum
     */
    public void setNaechstepruefung(java.util.Date naechstepruefung) {
        this.naechstepruefung = naechstepruefung;
    }

    /**
     * Return the value of the pruefergebnis column.
     * @return java.lang.String
     */
    public java.lang.String getPruefergebnis() {
        return this.pruefergebnis;
    }

    /**
     * Set the value of the pruefergebnis column.
     * @param pruefergebnis
     */
    public void setPruefergebnis(java.lang.String pruefergebnis) {
        this.pruefergebnis = pruefergebnis;
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
        if (!(rhs instanceof Anh49Kontrollen))
            return false;
        Anh49Kontrollen that = (Anh49Kontrollen) rhs;
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
            int ortsterminidValue = this.getId() == null ? 0 : this.getId()
                .hashCode();
            result = result * 37 + ortsterminidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
