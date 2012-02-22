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

import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;

/**
 * A class that represents a row in the ATL_ANALYSEPOSITION table. You can
 * customize the behavior of this class by editing the class,
 * {@link AtlAnalyseposition}.
 */
public abstract class AbstractAtlAnalyseposition extends
    AbstractVirtuallyDeletableDatabaseTable implements Serializable {
    private static final long serialVersionUID = 6961908351671157496L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    protected int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer id;

    /** The value of the atlEinheiten association. */
    private AtlEinheiten atlEinheiten;

    /** The value of the atlParameter association. */
    private AtlParameter atlParameter;

    /** The value of the atlProbenahmen association. */
    private AtlProbenahmen atlProbenahmen;

    /** The value of the simple grkl property. */
    private java.lang.String grkl;

    /** The value of the simple wert property. */
    private java.lang.Float wert;

    /** The value of the simple analyseVon property. */
    private java.lang.String analyseVon;

    /** The value of the simple bericht property. */
    private java.lang.String bericht;

    /** The value of the simple normwert property. */
    private java.lang.Double normwert;

    /** Simple constructor of AbstractAtlAnalyseposition instances. */
    public AbstractAtlAnalyseposition() {
    }

    /**
     * Constructor of AbstractAtlAnalyseposition instances given a simple
     * primary key.
     * @param id
     */
    public AbstractAtlAnalyseposition(java.lang.Integer id) {
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
     * Return the value of the PROBE_ID column.
     * @return AtlProbenahmen
     */
    public AtlProbenahmen getAtlProbenahmen() {
        return this.atlProbenahmen;
    }

    /**
     * Set the value of the PROBE_ID column.
     * @param atlProbenahmen
     */
    public void setAtlProbenahmen(AtlProbenahmen atlProbenahmen) {
        this.atlProbenahmen = atlProbenahmen;
    }

    /**
     * Return the value of the PARAMETER_ID column.
     * @return AtlParameter
     */
    public AtlParameter getAtlParameter() {
        return this.atlParameter;
    }

    /**
     * Set the value of the PARAMETER_ID column.
     * @param atlParameter
     */
    public void setAtlParameter(AtlParameter atlParameter) {
        this.atlParameter = atlParameter;
    }

    /**
     * Return the value of the GRKL column.
     * @return java.lang.String
     */
    public java.lang.String getGrkl() {
        return this.grkl;
    }

    /**
     * Set the value of the GRKL column.
     * @param grkl
     */
    public void setGrkl(java.lang.String grkl) {
        this.grkl = grkl;
    }

    /**
     * Return the value of the WERT column.
     * @return java.lang.Double
     */
    public java.lang.Float getWert() {
        return this.wert;
    }

    /**
     * Set the value of the WERT column.
     * @param wert
     */
    public void setWert(java.lang.Float wert) {
        this.wert = wert;
    }

    /**
     * Return the value of the EINHEITEN_ID column.
     * @return AtlEinheiten
     */
    public AtlEinheiten getAtlEinheiten() {
        return this.atlEinheiten;
    }

    /**
     * Set the value of the EINHEITEN_ID column.
     * @param atlEinheiten
     */
    public void setAtlEinheiten(AtlEinheiten atlEinheiten) {
        this.atlEinheiten = atlEinheiten;
    }

    /**
     * Return the value of the ANALYSE_VON column.
     * @return java.lang.String
     */
    public java.lang.String getAnalyseVon() {
        return this.analyseVon;
    }

    /**
     * Set the value of the ANALYSE_VON column.
     * @param analyseVon
     */
    public void setAnalyseVon(java.lang.String analyseVon) {
        this.analyseVon = analyseVon;
    }

    /**
     * Return the value of the BERICHT column.
     * @return java.lang.String
     */
    public java.lang.String getBericht() {
        return this.bericht;
    }

    /**
     * Set the value of the BERICHT column.
     * @param bericht
     */
    public void setBericht(java.lang.String bericht) {
        this.bericht = bericht;
    }

    /**
     * Return the value of the NORMWERT column.
     * @return java.lang.Double
     */
    public java.lang.Double getNormwert() {
        return this.normwert;
    }

    /**
     * Set the value of the NORMWERT column.
     * @param normwert
     */
    public void setNormwert(java.lang.Double normwert) {
        this.normwert = normwert;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the
     * primary key values.
     * @param rhs
     * @return boolean
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs == null) {
            return false;
        }
        if (this == rhs) {
            return true;
        }
        if (!(rhs instanceof AtlAnalyseposition)) {
            return false;
        }

        AtlAnalyseposition that = (AtlAnalyseposition) rhs;

        if (this.getId() != null && that.getId() != null) {
            if (this.getId().equals(that.getId())) {
                return true;
            }
        }

        if (!this.getAtlParameter().equals(that.getAtlParameter())) {
            return false;
        }
        if (!this.getWert().equals(that.getWert())) {
            return false;
        }

        if (!this.getAtlEinheiten().equals(that.getAtlEinheiten())) {
            return false;
        }

        if (this.getAnalyseVon() != null) {
            if (!this.getAnalyseVon().equals(that.getAnalyseVon())) {
                return false;
            }
        } else {
            if (!(that.getAnalyseVon() == null)) {
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
