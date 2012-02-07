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

import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;

/**
 * A class that represents a row in the BASIS_OBJEKT table. You can customize
 * the behavior of this class by editing the class, {@link BasisObjekt}.
 */
public abstract class AbstractBasisObjekt implements Serializable {
    private static final long serialVersionUID = -7681546015613114788L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektid;

    /** The value of the basisBetreiber association. */
    private BasisBetreiber basisBetreiber;

    /** The value of the basisObjektarten association. */
    private BasisObjektarten basisObjektarten;

    /** The value of the basisStandort association. */
    private BasisStandort basisStandort;

    /** The value of the basisSachbearbeiter association. */
    private BasisSachbearbeiter basisSachbearbeiter;

    /** The value of the atlProbepkt association. */
    private AtlProbepkt atlProbepkt;

    /** The value of the simple uschistdid property. */
    private java.lang.Integer uschistdid;

    /** The value of the simple beschreibung property. */
    private java.lang.String beschreibung;

    /** The value of the simple revidatum property. */
    private java.util.Date wiedervorlage;

    /** The value of the simple revidatum property. */
    private java.util.Date erfassungsdatum;

    /** The value of the simple revidatum property. */
    private java.util.Date gueltig_von;

    /** The value of the simple revidatum property. */
    private java.util.Date aenderungsdatum;

    /** The value of the simple revidatum property. */
    private java.util.Date gueltig_bis;

    /** The value of the simple inaktiv property. */
    private java.lang.Boolean inaktiv;

    /**
     * Simple constructor of AbstractBasisObjekt instances.
     */
    public AbstractBasisObjekt() {
    }

    /**
     * Constructor of AbstractBasisObjekt instances given a simple primary key.
     * @param objektid
     */
    public AbstractBasisObjekt(java.lang.Integer objektid) {
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
     * @param objektid
     */
    public void setObjektid(java.lang.Integer objektid) {
        this.hashValue = 0;
        this.objektid = objektid;
    }

    /**
     * Return the value of the STANDORTID column.
     * @return BasisStandort
     */
    public BasisStandort getBasisStandort() {
        return this.basisStandort;
    }

    /**
     * Set the value of the STANDORTID column.
     * @param basisStandort
     */
    public void setBasisStandort(BasisStandort basisStandort) {
        this.basisStandort = basisStandort;
    }

    /**
     * Return the value of the BETREIBERID column.
     * @return BasisBetreiber
     */
    public BasisBetreiber getBasisBetreiber() {
        return this.basisBetreiber;
    }

    /**
     * Set the value of the BETREIBERID column.
     * @param basisBetreiber
     */
    public void setBasisBetreiber(BasisBetreiber basisBetreiber) {
        this.basisBetreiber = basisBetreiber;
    }

    /**
     * Return the value of the OBJEKTARTID column.
     * @return BasisObjektarten
     */
    public BasisObjektarten getBasisObjektarten() {
        return this.basisObjektarten;
    }

    /**
     * Set the value of the OBJEKTARTID column.
     * @param basisObjektarten
     */
    public void setBasisObjektarten(BasisObjektarten basisObjektarten) {
        this.basisObjektarten = basisObjektarten;
    }

    /**
     * Return the value of the BasisSachbearbeiter column.
     * @return BasisSachbearbeiter
     */
    public BasisSachbearbeiter getBasisSachbearbeiter() {
        return this.basisSachbearbeiter;
    }

    /**
     * Set the value of the BasisSachbearbeiter column.
     * @param BasisSachbearbeiter
     */
    public void setBasisSachbearbeiter(BasisSachbearbeiter basisSachbearbeiter) {
        this.basisSachbearbeiter = basisSachbearbeiter;
    }

    /**
     * Return the value of the STANDORTID column.
     * @return BasisStandort
     */
    public AtlProbepkt getAtlProbepkt() {
        return this.atlProbepkt;
    }

    /**
     * Set the value of the STANDORTID column.
     * @param atlProbepkt
     */
    public void setAtlProbepkt(AtlProbepkt atlProbepkt) {
        this.atlProbepkt = atlProbepkt;
    }

    /**
     * Return the value of the USCHISTDID column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getUschistdid() {
        return this.uschistdid;
    }

    /**
     * Set the value of the USCHISTDID column.
     * @param uschistdid
     */
    public void setUschistdid(java.lang.Integer uschistdid) {
        this.uschistdid = uschistdid;
    }

    /**
     * Return the value of the BESCHREIBUNG column.
     * @return java.lang.String
     */
    public java.lang.String getBeschreibung() {
        return this.beschreibung;
    }

    /**
     * Set the value of the BESCHREIBUNG column.
     * @param beschreibung
     */
    public void setBeschreibung(java.lang.String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Return the value of the wiedervorlage column.
     * @return java.util.Date
     */
    public java.util.Date getWiedervorlage() {
        return this.wiedervorlage;
    }

    /**
     * Set the value of the wiedervorlage column.
     * @param revidatum
     */
    public void setWiedervorlage(java.util.Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    /**
     * Return the value of the erfassungsdatum column.
     * @return java.util.Date
     */
    public java.util.Date getErfassungsdatum() {
        return this.erfassungsdatum;
    }

    /**
     * Set the value of the erfassungsdatum column.
     * @param revidatum
     */
    public void setErfassungsdatum(java.util.Date erfassungsdatum) {
        this.erfassungsdatum = erfassungsdatum;
    }

    /**
     * Return the value of the gueltig_von column.
     * @return java.util.Date
     */
    public java.util.Date getGueltigVon() {
        return this.gueltig_von;
    }

    /**
     * Set the value of the gueltig_von column.
     * @param revidatum
     */
    public void setGueltigVon(java.util.Date gueltig_von) {
        this.gueltig_von = gueltig_von;
    }

    /**
     * Return the value of the aenderungsdatum column.
     * @return java.util.Date
     */
    public java.util.Date getAenderungsdatum() {
        return this.aenderungsdatum;
    }

    /**
     * Set the value of the aenderungsdatum column.
     * @param revidatum
     */
    public void setAenderungsdatum(java.util.Date aenderungsdatum) {
        this.aenderungsdatum = aenderungsdatum;
    }

    /**
     * Return the value of the aenderungsdatum column.
     * @return java.util.Date
     */
    public java.lang.Boolean getInaktiv() {
        return this.inaktiv;
    }

    /**
     * Set the value of the aenderungsdatum column.
     * @param revidatum
     */
    public void setInaktiv(java.lang.Boolean inaktiv) {
        this.inaktiv = inaktiv;
    }

    /**
     * Return the value of the gueltig_bis column.
     * @return java.util.Date
     */
    public java.util.Date getGueltigBis() {
        return this.gueltig_bis;
    }

    /**
     * Set the value of the gueltig_bis column.
     * @param revidatum
     */
    public void setGueltigBis(java.util.Date gueltig_bis) {
        this.gueltig_bis = gueltig_bis;
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
        if (!(rhs instanceof BasisObjekt))
            return false;
        BasisObjekt that = (BasisObjekt) rhs;
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
            int objektidValue = this.getObjektid() == null ? 0 : this
                .getObjektid().hashCode();
            result = result * 37 + objektidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
