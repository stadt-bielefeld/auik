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

import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;

/**
 * A class that represents a row in the VAWS_KONTROLLEN table. You can customize
 * the behavior of this class by editing the class, {@link VawsKontrollen()}.
 */
public abstract class AbstractVawsKontrollen extends
    AbstractVirtuallyDeletableDatabaseTable implements Serializable {
    private static final long serialVersionUID = 641389056004553929L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer id;

    /** The value of the vawsFachdaten association. */
    private VawsFachdaten vawsFachdaten;

    /** The value of the simple grundderpruefung property. */
    private java.lang.String grundderpruefung;

    /** The value of the simple pruefturnus property. */
    private java.lang.Float pruefturnus;

    /** The value of the simple pruefdatum property. */
    private java.util.Date pruefdatum;

    /** The value of the simple pruefer property. */
    private java.lang.String pruefer;

    /** The value of the simple pruefungabgeschlossen property. */
    private java.lang.Boolean pruefungabgeschlossen;

    /** The value of the simple nachpruefung property. */
    private java.lang.Boolean nachpruefung;

    /** The value of the simple nachpruefdatum property. */
    private java.util.Date nachpruefdatum;

    /** The value of the simple nachpruefer property. */
    private java.lang.String nachpruefer;

    /** The value of the simple naechstepruefung property. */
    private java.util.Date naechstepruefung;

    /** The value of the simple pruefergebnis property. */
    private java.lang.String pruefergebnis;

    /**
     * Simple constructor of AbstractVawsKontrollen instances.
     */
    public AbstractVawsKontrollen() {
    }

    /**
     * Constructor of AbstractVawsKontrollen instances given a simple primary
     * key.
     * @param kontrollid
     */
    public AbstractVawsKontrollen(java.lang.Integer id) {
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
     * @param kontrollid
     */
    public void setId(java.lang.Integer id) {
        this.hashValue = 0;
        this.id = id;
    }

    /**
     * Return the value of the BEHAELTERID column.
     * @return VawsFachdaten
     */
    public VawsFachdaten getVawsFachdaten() {
        return this.vawsFachdaten;
    }

    /**
     * Set the value of the BEHAELTERID column.
     * @param vawsFachdaten
     */
    public void setVawsFachdaten(VawsFachdaten vawsFachdaten) {
        this.vawsFachdaten = vawsFachdaten;
    }

    /**
     * Return the value of the GRUNDDERPRUEFUNG column.
     * @return java.lang.String
     */
    public java.lang.String getGrundderpruefung() {
        return this.grundderpruefung;
    }

    /**
     * Set the value of the GRUNDDERPRUEFUNG column.
     * @param grundderpruefung
     */
    public void setGrundderpruefung(java.lang.String grundderpruefung) {
        this.grundderpruefung = grundderpruefung;
    }

    /**
     * Return the value of the PRUEFTURNUS column.
     * @return java.lang.Float
     */
    public java.lang.Float getPruefturnus() {
        return this.pruefturnus;
    }

    /**
     * Set the value of the PRUEFTURNUS column.
     * @param pruefturnus
     */
    public void setPruefturnus(java.lang.Float pruefturnus) {
        this.pruefturnus = pruefturnus;
    }

    /**
     * Return the value of the PRUEFDATUM column.
     * @return java.util.Date
     */
    public java.util.Date getPruefdatum() {
        return this.pruefdatum;
    }

    /**
     * Set the value of the PRUEFDATUM column.
     * @param pruefdatum
     */
    public void setPruefdatum(java.util.Date pruefdatum) {
        this.pruefdatum = pruefdatum;
    }

    /**
     * Return the value of the PRUEFER column.
     * @return java.lang.String
     */
    public java.lang.String getPruefer() {
        return this.pruefer;
    }

    /**
     * Set the value of the PRUEFER column.
     * @param pruefer
     */
    public void setPruefer(java.lang.String pruefer) {
        this.pruefer = pruefer;
    }

    /**
     * Return the value of the PRUEFUNGABGESCHLOSSEN column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getPruefungabgeschlossen() {
        return this.pruefungabgeschlossen;
    }

    /**
     * Set the value of the PRUEFUNGABGESCHLOSSEN column.
     * @param pruefungabgeschlossen
     */
    public void setPruefungabgeschlossen(java.lang.Boolean pruefungabgeschlossen) {
        this.pruefungabgeschlossen = pruefungabgeschlossen;
    }

    /**
     * Return the value of the NACHPRUEFUNG column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getNachpruefung() {
        return this.nachpruefung;
    }

    /**
     * Set the value of the NACHPRUEFUNG column.
     * @param nachpruefung
     */
    public void setNachpruefung(java.lang.Boolean nachpruefung) {
        this.nachpruefung = nachpruefung;
    }

    /**
     * Return the value of the NACHPRUEFDATUM column.
     * @return java.util.Date
     */
    public java.util.Date getNachpruefdatum() {
        return this.nachpruefdatum;
    }

    /**
     * Set the value of the NACHPRUEFDATUM column.
     * @param nachpruefdatum
     */
    public void setNachpruefdatum(java.util.Date nachpruefdatum) {
        this.nachpruefdatum = nachpruefdatum;
    }

    /**
     * Return the value of the NACHPRUEFER column.
     * @return java.lang.String
     */
    public java.lang.String getNachpruefer() {
        return this.nachpruefer;
    }

    /**
     * Set the value of the NACHPRUEFER column.
     * @param nachpruefer
     */
    public void setNachpruefer(java.lang.String nachpruefer) {
        this.nachpruefer = nachpruefer;
    }

    /**
     * Return the value of the NAECHSTEPRUEFUNG column.
     * @return java.util.Date
     */
    public java.util.Date getNaechstepruefung() {
        return this.naechstepruefung;
    }

    /**
     * Set the value of the NAECHSTEPRUEFUNG column.
     * @param naechstepruefung
     */
    public void setNaechstepruefung(java.util.Date naechstepruefung) {
        this.naechstepruefung = naechstepruefung;
    }

    /**
     * Return the value of the PRUEFERGEBNIS column.
     * @return java.lang.String
     */
    public java.lang.String getPruefergebnis() {
        return this.pruefergebnis;
    }

    /**
     * Set the value of the PRUEFERGEBNIS column.
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
