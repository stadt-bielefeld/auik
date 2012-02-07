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

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the ANH_56_FACHDATEN table. You can
 * customize the behavior of this class by editing the class, {@link
 * Anh56Fachdaten()}.
 */
public abstract class AbstractAnh56Fachdaten implements Serializable {
    private static final long serialVersionUID = -2056946198452995655L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektid;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

    /** The value of the simple druckverfahren property. */
    private java.lang.String druckverfahren;

    /** The value of the simple sachbearbeiterrav property. */
    private java.lang.String sachbearbeiterrav;

    /** The value of the simple sachbearbeiterheepen property. */
    private java.lang.String sachbearbeiterheepen;

    /** The value of the simple verbrauch property. */
    private java.lang.String verbrauch;

    /** The value of the simple entsorgung property. */
    private java.lang.String entsorgung;

    /** The value of the simple abwasseranfall property. */
    private java.lang.Boolean abwasseranfall;

    /** The value of the simple genpflicht property. */
    private java.lang.Boolean genpflicht;

    /** The value of the simple aba property. */
    private java.lang.Boolean aba;

    /** The value of the simple gen58 property. */
    private java.util.Date gen58;

    /** The value of the simple gen59 property. */
    private java.util.Date gen59;

    /** The value of the simple bemerkungen property. */
    private java.lang.String bemerkungen;

    /** The value of the simple aba property. */
    private java.lang.Boolean abfallrechtlentsorg;

    /** The value of the simple aba property. */
    private java.lang.Boolean spuelwasser;

    /** The value of the simple aba property. */
    private java.lang.Boolean leimabwasser;

    /** The value of the simple gen58 property. */
    private java.util.Date erfasstam;

    /**
     * Simple constructor of AbstractAnh56Fachdaten instances.
     */
    public AbstractAnh56Fachdaten() {
    }

    /**
     * Constructor of AbstractAnh56Fachdaten instances given a simple primary
     * key.
     * @param id
     */
    public AbstractAnh56Fachdaten(java.lang.Integer objektid) {
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
     * @param id
     */
    public void setObjektid(java.lang.Integer objektid) {
        this.hashValue = 0;
        this.objektid = objektid;
    }

    /**
     * Return the value of the OBJID column.
     * @return BasisObjekt
     */
    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }

    /**
     * Set the value of the OBJID column.
     * @param basisObjekt
     */
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    /**
     * Return the value of the DRUCKVERFAHREN column.
     * @return java.lang.String
     */
    public java.lang.String getDruckverfahren() {
        return this.druckverfahren;
    }

    /**
     * Set the value of the DRUCKVERFAHREN column.
     * @param druckverfahren
     */
    public void setDruckverfahren(java.lang.String druckverfahren) {
        this.druckverfahren = druckverfahren;
    }

    /**
     * Return the value of the VERBRAUCH column.
     * @return java.lang.String
     */
    public java.lang.String getVerbrauch() {
        return this.verbrauch;
    }

    /**
     * Set the value of the VERBRAUCH column.
     * @param verbrauch
     */
    public void setVerbrauch(java.lang.String verbrauch) {
        this.verbrauch = verbrauch;
    }

    /**
     * Return the value of the ENTSORGUNG column.
     * @return java.lang.String
     */
    public java.lang.String getEntsorgung() {
        return this.entsorgung;
    }

    /**
     * Set the value of the ENTSORGUNG column.
     * @param entsorgung
     */
    public void setEntsorgung(java.lang.String entsorgung) {
        this.entsorgung = entsorgung;
    }

    /**
     * Return the value of the ABWASSERANFALL column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getAbwasseranfall() {
        return this.abwasseranfall;
    }

    /**
     * Set the value of the ABWASSERANFALL column.
     * @param abwasseranfall
     */
    public void setAbwasseranfall(java.lang.Boolean abwasseranfall) {
        this.abwasseranfall = abwasseranfall;
    }

    /**
     * Return the value of the GENPFLICHT column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getGenpflicht() {
        return this.genpflicht;
    }

    /**
     * Set the value of the GENPFLICHT column.
     * @param genpflicht
     */
    public void setGenpflicht(java.lang.Boolean genpflicht) {
        this.genpflicht = genpflicht;
    }

    /**
     * Return the value of the ABA column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getAba() {
        return this.aba;
    }

    /**
     * Set the value of the ABA column.
     * @param aba
     */
    public void setAba(java.lang.Boolean aba) {
        this.aba = aba;
    }

    /**
     * Return the value of the GEN_58 column.
     * @return java.util.Date
     */
    public java.util.Date getGen58() {
        return this.gen58;
    }

    /**
     * Set the value of the GEN_58 column.
     * @param gen58
     */
    public void setGen58(java.util.Date gen58) {
        this.gen58 = gen58;
    }

    /**
     * Return the value of the GEN_59 column.
     * @return java.util.Date
     */
    public java.util.Date getGen59() {
        return this.gen59;
    }

    /**
     * Set the value of the GEN_59 column.
     * @param gen59
     */
    public void setGen59(java.util.Date gen59) {
        this.gen59 = gen59;
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
        if (!(rhs instanceof Anh56Fachdaten))
            return false;
        Anh56Fachdaten that = (Anh56Fachdaten) rhs;
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
            int idValue = this.getObjektid() == null ? 0 : this.getObjektid()
                .hashCode();
            result = result * 37 + idValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }

    public java.lang.String getSachbearbeiterrav() {
        return sachbearbeiterrav;
    }

    public void setSachbearbeiterrav(java.lang.String sachbearbeiterrav) {
        this.sachbearbeiterrav = sachbearbeiterrav;
    }

    public java.lang.String getSachbearbeiterheepen() {
        return sachbearbeiterheepen;
    }

    public void setSachbearbeiterheepen(java.lang.String sachbearbeiterheepen) {
        this.sachbearbeiterheepen = sachbearbeiterheepen;
    }

    public java.lang.Boolean getAbfallrechtlentsorg() {
        return abfallrechtlentsorg;
    }

    public void setAbfallrechtlentsorg(java.lang.Boolean abfallrechtlentsorg) {
        this.abfallrechtlentsorg = abfallrechtlentsorg;
    }

    public java.lang.Boolean getSpuelwasser() {
        return spuelwasser;
    }

    public void setSpuelwasser(java.lang.Boolean spuelwasser) {
        this.spuelwasser = spuelwasser;
    }

    public java.lang.Boolean getLeimabwasser() {
        return leimabwasser;
    }

    public void setLeimabwasser(java.lang.Boolean leimabwasser) {
        this.leimabwasser = leimabwasser;
    }

    public java.util.Date getErfasstam() {
        return erfasstam;
    }

    public void setErfasstam(java.util.Date erfasstam) {
        this.erfasstam = erfasstam;
    }
}
