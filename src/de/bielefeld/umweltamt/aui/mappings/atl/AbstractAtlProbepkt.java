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

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.Set;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;

/**
 * A class that represents a row in the ATL_PROBEPKT table. You can customize
 * the behavior of this class by editing the class, {@link AtlProbepkt}.
 */
public abstract class AbstractAtlProbepkt implements Serializable {
    private static final long serialVersionUID = -4603102088962913875L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektid;

    /** The value of the simple atlFirmen property. */
    private Integer atlFirmen;

    /** The value of the atlKlaeranlagen association. */
    private AtlKlaeranlagen atlKlaeranlagen;

    /** The value of the atlProbeart association. */
    private AtlProbeart atlProbeart;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

    /** The value of the atlSielhaut association. */
    private AtlSielhaut atlSielhaut;

    /** The value of the basisSachbearbeiter association. */
    private BasisSachbearbeiter basisSachbearbeiter;

    /** The value of the simple beschreibung property. */
    private java.lang.String beschreibung;

    /** The value of the simple branche property. */
    private java.lang.String branche;

    /** The value of the simple nummer property. */
    private java.lang.Integer nummer;

    private Set<AtlProbenahmen> atlProbenahmen;

    /**
     * Simple constructor of AbstractAtlProbepkt instances.
     */
    public AbstractAtlProbepkt() {
    }

    /**
     * Constructor of AbstractAtlProbepkt instances given a simple primary key.
     * @param pktId
     */
    public AbstractAtlProbepkt(java.lang.Integer objektid) {
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
     * @param pktId
     */
    public void setObjektid(java.lang.Integer objektid) {
        this.hashValue = 0;
        this.objektid = objektid;
    }

    /**
     * Return the value of the FIRMEN_ID column.
     * @return AtlFirmen
     */
    public Integer getAtlFirmen() {
        return this.atlFirmen;
    }

    /**
     * Set the value of the FIRMEN_ID column.
     * @param atlFirmen
     */
    public void setAtlFirmen(Integer atlFirmen) {
        this.atlFirmen = atlFirmen;
    }

    /**
     * Return the value of the SIELHAUT_ID column.
     * @return AtlSielhaut
     */
    public AtlSielhaut getAtlSielhaut() {
        return this.atlSielhaut;
    }

    /**
     * Set the value of the SIELHAUT_ID column.
     * @param atlSielhaut
     */
    public void setAtlSielhaut(AtlSielhaut atlSielhaut) {
        this.atlSielhaut = atlSielhaut;
    }

    /**
     * Return the value of the KA_ID column.
     * @return AtlKlaeranlagen
     */
    public AtlKlaeranlagen getAtlKlaeranlagen() {
        return this.atlKlaeranlagen;
    }

    /**
     * Set the value of the KA_ID column.
     * @param atlKlaeranlagen
     */
    public void setAtlKlaeranlagen(AtlKlaeranlagen atlKlaeranlagen) {
        this.atlKlaeranlagen = atlKlaeranlagen;
    }

    /**
     * Return the value of the ART_ID column.
     * @return AtlProbeart
     */
    public AtlProbeart getAtlProbeart() {
        return this.atlProbeart;
    }

    /**
     * Set the value of the ART_ID column.
     * @param atlProbeart
     */
    public void setAtlProbeart(AtlProbeart atlProbeart) {
        this.atlProbeart = atlProbeart;
    }

    /**
     * Return the value of the SACHBEARBEITER column.
     * @return AtlProbeart
     */
    public BasisSachbearbeiter getBasisSachbearbeiter() {
        return this.basisSachbearbeiter;
    }

    /**
     * Set the value of the SACHBEARBEITER column.
     * @param atlProbeart
     */
    public void setBasisSachbearbeiter(BasisSachbearbeiter basisSachbearbeiter) {
        this.basisSachbearbeiter = basisSachbearbeiter;
    }

    /**
     * Return the value of the OBJEKT_ID column.
     * @return BasisObjekt
     */
    public BasisObjekt getBasisObjekt() {
        return basisObjekt;
    }

    /**
     * Set the value of the OBJEKT_ID column.
     * @param basisObjekt
     */
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
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
     * Return the value of the Branche column.
     * @return java.lang.String
     */
    public java.lang.String getBranche() {
        return this.branche;
    }

    /**
     * Set the value of the BESCHRBrancheEIBUNG column.
     * @param beschreibung
     */
    public void setBranche(java.lang.String branche) {
        this.branche = branche;
    }

    /**
     * Return the value of the NR_PROBEPKT column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getNummer() {
        return this.nummer;
    }

    /**
     * Set the value of the NR_PROBEPKT column.
     * @param nummer
     */
    public void setNummer(java.lang.Integer nummer) {
        this.nummer = nummer;
    }

    /**
     * Return the set of the AtlProbenahmen.
     * @return AtlProbenahmen
     */
    public Set<AtlProbenahmen> getAtlProbenahmen() {
        return this.atlProbenahmen;
    }

    /**
     * Set the set of the AtlProbenahmen.
     * @param atlProbenahmen
     */
    public void setAtlProbenahmen(Set<AtlProbenahmen> atlProbenahmen) {
        this.atlProbenahmen = atlProbenahmen;
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
        if (!(rhs instanceof AtlProbepkt))
            return false;
        AtlProbepkt that = (AtlProbepkt) rhs;
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
            int pktIdValue = this.getObjektid() == null ? 0 : this
                    .getObjektid().hashCode();
            result = result * 37 + pktIdValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
