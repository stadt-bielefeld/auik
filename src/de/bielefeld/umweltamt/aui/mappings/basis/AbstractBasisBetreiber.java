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
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWirtschaftszweige;

/**
 * A class that represents a row in the BASIS_BETREIBER table. You can customize
 * the behavior of this class by editing the class, {@link BasisBetreiber}.
 */
public abstract class AbstractBasisBetreiber extends
    AbstractVirtuallyDeletableDatabaseTable implements Serializable {
    private static final long serialVersionUID = 7619136552719221885L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer betreiberid;

    /** The value of the vawsWirtschaftszweige association. */
    private VawsWirtschaftszweige vawsWirtschaftszweige;

    /** The value of the simple betranrede property. */
    private java.lang.String betranrede;

    /** The value of the simple betrvorname property. */
    private java.lang.String betrvorname;

    /** The value of the simple betrname property. */
    private java.lang.String betrname;

    /** The value of the simple betrnamezus property. */
    private java.lang.String betrnamezus;

    /** The value of the simple namebetrbeauf property. */
    private java.lang.String namebetrbeauf;

    /** The value of the simple vornamebetrbeauf property. */
    private java.lang.String vornamebetrbeauf;

    /** The value of the simple strasse property. */
    private java.lang.String strasse;

    /** The value of the simple hausnr property. */
    private java.lang.Integer hausnr;

    /** The value of the simple hausnrzus property. */
    private java.lang.String hausnrzus;

    /** The value of the simple plzzs property. */
    private java.lang.String plzzs;

    /** The value of the simple plz property. */
    private java.lang.String plz;

    /** The value of the simple ort property. */
    private java.lang.String ort;

    /** The value of the simple telefon property. */
    private java.lang.String telefon;

    /** The value of the simple telefax property. */
    private java.lang.String telefax;

    /** The value of the simple email property. */
    private java.lang.String email;

    /** The value of the simple bemerkungen property. */
    private java.lang.String bemerkungen;

    /** The value of the simple revidatum property. */
    private java.util.Date revidatum;

    /** The value of the simple revihandz property. */
    private java.lang.String revihandz;

    /** The value of the simple kassenzeichen property. */
    private java.lang.String kassenzeichen;

    /**
     * Simple constructor of AbstractBasisBetreiber instances.
     */
    public AbstractBasisBetreiber() {
    }

    /**
     * Constructor of AbstractBasisBetreiber instances given a simple primary
     * key.
     * @param betreiberid
     */
    public AbstractBasisBetreiber(java.lang.Integer betreiberid) {
        this.setBetreiberid(betreiberid);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getBetreiberid() {
        return betreiberid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param betreiberid
     */
    public void setBetreiberid(java.lang.Integer betreiberid) {
        this.hashValue = 0;
        this.betreiberid = betreiberid;
    }

    /**
     * Return the value of the BETRANREDE column.
     * @return java.lang.String
     */
    public java.lang.String getBetranrede() {
        return this.betranrede;
    }

    /**
     * Set the value of the BETRANREDE column.
     * @param betranrede
     */
    public void setBetranrede(java.lang.String betranrede) {
        this.betranrede = betranrede;
    }

    /**
     * Return the value of the BETRVORNAME column.
     * @return java.lang.String
     */
    public java.lang.String getBetrvorname() {
        return this.betrvorname;
    }

    /**
     * Set the value of the BETRVORNAME column.
     * @param betrvorname
     */
    public void setBetrvorname(java.lang.String betrvorname) {
        this.betrvorname = betrvorname;
    }

    /**
     * Return the value of the BETRNAME column.
     * @return java.lang.String
     */
    public java.lang.String getBetrname() {
        return this.betrname;
    }

    /**
     * Set the value of the BETRNAME column.
     * @param betrname
     */
    public void setBetrname(java.lang.String betrname) {
        this.betrname = betrname;
    }

    /**
     * Return the value of the BETRNAMEZUS column.
     * @return java.lang.String
     */
    public java.lang.String getBetrnamezus() {
        return this.betrnamezus;
    }

    /**
     * Set the value of the BETRNAMEZUS column.
     * @param betrnamezus
     */
    public void setBetrnamezus(java.lang.String betrnamezus) {
        this.betrnamezus = betrnamezus;
    }

    /**
     * Return the value of the NAMEBETRBEAUF column.
     * @return java.lang.String
     */
    public java.lang.String getNamebetrbeauf() {
        return this.namebetrbeauf;
    }

    /**
     * Set the value of the NAMEBETRBEAUF column.
     * @param namebetrbeauf
     */
    public void setNamebetrbeauf(java.lang.String namebetrbeauf) {
        this.namebetrbeauf = namebetrbeauf;
    }

    /**
     * Return the value of the VORNAMEBETRBEAUF column.
     * @return java.lang.String
     */
    public java.lang.String getVornamebetrbeauf() {
        return this.vornamebetrbeauf;
    }

    /**
     * Set the value of the VORNAMEBETRBEAUF column.
     * @param vornamebetrbeauf
     */
    public void setVornamebetrbeauf(java.lang.String vornamebetrbeauf) {
        this.vornamebetrbeauf = vornamebetrbeauf;
    }

    /**
     * Return the value of the STRASSE column.
     * @return java.lang.String
     */
    public java.lang.String getStrasse() {
        return this.strasse;
    }

    /**
     * Set the value of the STRASSE column.
     * @param strasse
     */
    public void setStrasse(java.lang.String strasse) {
        this.strasse = strasse;
    }

    /**
     * Return the value of the HAUSNR column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getHausnr() {
        return this.hausnr;
    }

    /**
     * Set the value of the HAUSNR column.
     * @param hausnr
     */
    public void setHausnr(java.lang.Integer hausnr) {
        this.hausnr = hausnr;
    }

    /**
     * Return the value of the HAUSNRZUS column.
     * @return java.lang.String
     */
    public java.lang.String getHausnrzus() {
        return this.hausnrzus;
    }

    /**
     * Set the value of the HAUSNRZUS column.
     * @param hausnrzus
     */
    public void setHausnrzus(java.lang.String hausnrzus) {
        this.hausnrzus = hausnrzus;
    }

    /**
     * Return the value of the PLZZS column.
     * @return java.lang.String
     */
    public java.lang.String getPlzzs() {
        return this.plzzs;
    }

    /**
     * Set the value of the PLZZS column.
     * @param plzzs
     */
    public void setPlzzs(java.lang.String plzzs) {
        this.plzzs = plzzs;
    }

    /**
     * Return the value of the PLZ column.
     * @return java.lang.String
     */
    public java.lang.String getPlz() {
        return this.plz;
    }

    /**
     * Set the value of the PLZ column.
     * @param plz
     */
    public void setPlz(java.lang.String plz) {
        this.plz = plz;
    }

    /**
     * Return the value of the ORT column.
     * @return java.lang.String
     */
    public java.lang.String getOrt() {
        return this.ort;
    }

    /**
     * Set the value of the ORT column.
     * @param ort
     */
    public void setOrt(java.lang.String ort) {
        this.ort = ort;
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
     * Return the value of the TELEFAX column.
     * @return java.lang.String
     */
    public java.lang.String getTelefax() {
        return this.telefax;
    }

    /**
     * Set the value of the TELEFAX column.
     * @param telefax
     */
    public void setTelefax(java.lang.String telefax) {
        this.telefax = telefax;
    }

    /**
     * Return the value of the EMAIL column.
     * @return java.lang.String
     */
    public java.lang.String getEmail() {
        return this.email;
    }

    /**
     * Set the value of the EMAIL column.
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    /**
     * Return the value of the WIRTSCHAFTSZWEIGID column.
     * @return VawsWirtschaftszweige
     */
    public VawsWirtschaftszweige getVawsWirtschaftszweige() {
        return this.vawsWirtschaftszweige;
    }

    /**
     * Set the value of the WIRTSCHAFTSZWEIGID column.
     * @param vawsWirtschaftszweige
     */
    public void setVawsWirtschaftszweige(
        VawsWirtschaftszweige vawsWirtschaftszweige) {
        this.vawsWirtschaftszweige = vawsWirtschaftszweige;
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
     * Return the value of the REVIDATUM column.
     * @return java.util.Date
     */
    public java.util.Date getRevidatum() {
        return this.revidatum;
    }

    /**
     * Set the value of the REVIDATUM column.
     * @param revidatum
     */
    public void setRevidatum(java.util.Date revidatum) {
        this.revidatum = revidatum;
    }

    /**
     * Return the value of the REVIHANDZ column.
     * @return java.lang.String
     */
    public java.lang.String getRevihandz() {
        return this.revihandz;
    }

    /**
     * Set the value of the REVIHANDZ column.
     * @param revihandz
     */
    public void setRevihandz(java.lang.String revihandz) {
        this.revihandz = revihandz;
    }

    /**
     * Return the value of the Kassenzeichen column.
     * @return java.lang.String
     */
    public java.lang.String getKassenzeichen() {
        return this.kassenzeichen;
    }

    /**
     * Set the value of the Kassenzeichen column.
     * @param revihandz
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
        if (!(rhs instanceof BasisBetreiber))
            return false;
        BasisBetreiber that = (BasisBetreiber) rhs;
        if (this.getBetreiberid() != null && that.getBetreiberid() != null) {
            if (!this.getBetreiberid().equals(that.getBetreiberid())) {
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
            int betreiberidValue = this.getBetreiberid() == null ? 0 : this
                .getBetreiberid().hashCode();
            result = result * 37 + betreiberidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
