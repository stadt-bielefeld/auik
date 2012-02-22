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

import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the ANH_52_FACHDATEN table. You can
 * customize the behavior of this class by editing the class, {@link
 * Anh52Fachdaten()}.
 */
public abstract class AbstractAnh52Fachdaten extends
    AbstractVirtuallyDeletableDatabaseTable implements Serializable {
    private static final long serialVersionUID = 5476172214727919450L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektid;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

//    private java.lang.Integer objektId;
    private java.lang.Integer nrbetriebsstaette;
    private java.lang.String firmenname;
    private java.lang.String telefon;
    private java.lang.String telefax;
    private java.lang.String ansprechpartner;
    private java.lang.String bemerkungen;
    private java.util.Date datumGen;

    /**
     * Simple constructor of AbstractAnh52Fachdaten instances.
     */
    public AbstractAnh52Fachdaten() {
    }

    /**
     * Constructor of AbstractAnh52Fachdaten instances given a simple primary
     * key.
     * @param id
     */
    public AbstractAnh52Fachdaten(java.lang.Integer objektid) {
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

    public java.lang.String getAnsprechpartner() {
        return ansprechpartner;
    }

    public void setAnsprechpartner(java.lang.String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public java.lang.String getBemerkungen() {
        return bemerkungen;
    }

    public void setBemerkungen(java.lang.String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public java.lang.String getFirmenname() {
        return firmenname;
    }

    public void setFirmenname(java.lang.String firmenname) {
        this.firmenname = firmenname;
    }

    public java.lang.Integer getNrbetriebsstaette() {
        return nrbetriebsstaette;
    }

    public void setNrbetriebsstaette(java.lang.Integer nrbetriebsstaette) {
        this.nrbetriebsstaette = nrbetriebsstaette;
    }

    public java.lang.String getTelefax() {
        return telefax;
    }

    public void setTelefax(java.lang.String telefax) {
        this.telefax = telefax;
    }

    public java.lang.String getTelefon() {
        return telefon;
    }

    public void setTelefon(java.lang.String telefon) {
        this.telefon = telefon;
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
        if (!(rhs instanceof Anh52Fachdaten))
            return false;
        Anh52Fachdaten that = (Anh52Fachdaten) rhs;
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

    public java.util.Date getDatumGen() {
        return datumGen;
    }

    public void setDatumGen(java.util.Date datumGen) {
        this.datumGen = datumGen;
    }
}
