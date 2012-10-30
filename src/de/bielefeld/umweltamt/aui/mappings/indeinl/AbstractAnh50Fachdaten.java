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
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the ANH_50_FACHDATEN table. You can
 * customize the behavior of this class by editing the class,
 * {@link Anh50Fachdaten}.
 */
public abstract class AbstractAnh50Fachdaten extends
    StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = 2707557881532673898L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektid;

    /** The value of the anhEntsorger association. */
    private AnhEntsorger anhEntsorger;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

    /** The value of the simple telefon property. */
    private java.lang.String telefon;

    /** The value of the simple erloschen property. */
    private java.lang.Boolean erloschen;

    /** The value of the simple datumantrag property. */
    private java.util.Date datumantrag;

    /** The value of the simple bemerkungen property. */
    private java.lang.String bemerkungen;

    /** The value of the simple genehmigung property. */
    private java.util.Date genehmigung;

    /** The value of the simple wiedervorlage property. */
    private java.util.Date wiedervorlage;

    /** The value of the simple gefaehrdungsklasse property. */
    private java.lang.String gefaehrdungsklasse;

    /**
     * Simple constructor of AbstractAnh50Fachdaten instances.
     */
    public AbstractAnh50Fachdaten() {
    }

    /**
     * Constructor of AbstractAnh50Fachdaten instances given a simple primary
     * key.
     * @param firmenid
     */
    public AbstractAnh50Fachdaten(java.lang.Integer objektid) {
        this.setObjektid(objektid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getObjektid() {
        return objektid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param firmenid
     */
    public void setObjektid(java.lang.Integer objektid) {
        this.hashValue = 0;
        this.objektid = objektid;
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
     * Return the value of the TELEFON column.
     * @return java.lang.String
     */
    public java.lang.String getTelefon() {
        return this.telefon;
    }

    /**
     * Set the value of the TELEFON column.
     * @param telefon
     */
    public void setTelefon(java.lang.String telefon) {
        this.telefon = telefon;
    }

    /**
     * Return the value of the ERLOSCHEN column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getErloschen() {
        return this.erloschen;
    }

    /**
     * Set the value of the ERLOSCHEN column.
     * @param erloschen
     */
    public void setErloschen(java.lang.Boolean erloschen) {
        this.erloschen = erloschen;
    }

    /**
     * Return the value of the DATUMANTRAG column.
     * @return java.util.Date
     */
    public java.util.Date getDatumantrag() {
        return this.datumantrag;
    }

    /**
     * Set the value of the DATUMANTRAG column.
     * @param datumantrag
     */
    public void setDatumantrag(java.util.Date datumantrag) {
        this.datumantrag = datumantrag;
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
     * Return the value of the GENEHMIGUNG column.
     * @return java.util.Date
     */
    public java.util.Date getGenehmigung() {
        return this.genehmigung;
    }

    /**
     * Set the value of the GENEHMIGUNG column.
     * @param genehmigung
     */
    public void setGenehmigung(java.util.Date genehmigung) {
        this.genehmigung = genehmigung;
    }

    /**
     * Return the value of the WIEDERVORLAGE column.
     * @return java.util.Date
     */
    public java.util.Date getWiedervorlage() {
        return this.wiedervorlage;
    }

    /**
     * Set the value of the WIEDERVORLAGE column.
     * @param wiedervorlage
     */
    public void setWiedervorlage(java.util.Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    /**
     * Return the value of the GEFAEHRDUNGSKLASSE column.
     * @return java.lang.String
     */
    public java.lang.String getGefaehrdungsklasse() {
        return this.gefaehrdungsklasse;
    }

    /**
     * Set the value of the GEFAEHRDUNGSKLASSE column.
     * @param gefaehrdungsklasse
     */
    public void setGefaehrdungsklasse(java.lang.String gefaehrdungsklasse) {
        this.gefaehrdungsklasse = gefaehrdungsklasse;
    }

    /**
     * Return the value of the ENTSORGERID column.
     * @return Anh50Entsorger
     */
    public AnhEntsorger getAnhEntsorger() {
        return this.anhEntsorger;
    }

    /**
     * Set the value of the ENTSORGERID column.
     * @param anh50Entsorger
     */
    public void setAnhEntsorger(AnhEntsorger anh50Entsorger) {
        this.anhEntsorger = anh50Entsorger;
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
        if (!(rhs instanceof Anh50Fachdaten))
            return false;
        Anh50Fachdaten that = (Anh50Fachdaten) rhs;
        if (this.getObjektid() != null && that.getObjektid() != null) {
            if (!this.getObjektid().equals(that.getObjektid())) {
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
            int firmenidValue = this.getObjektid() == null ? 0 : this
                .getObjektid().hashCode();
            result = result * 37 + firmenidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
