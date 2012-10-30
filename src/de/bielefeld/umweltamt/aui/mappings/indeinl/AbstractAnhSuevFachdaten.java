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
 * A class that represents a row in the SUEV_FACHDATEN table. You can customize
 * the behavior of this class by editing the class, {@link SuevFachdaten()}.
 */
public abstract class AbstractAnhSuevFachdaten extends
    StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = -582476656188881588L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektid;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

    /** The value of the simple groesser3ha property. */
    private java.lang.Boolean groesser3ha;

    /** The value of the simple versFlaeche property. */
    private java.lang.Integer versFlaeche;

    /** The value of the simple suevkanPflicht property. */
    private java.lang.Boolean suevkanPflicht;

    /** The value of the simple indirektsw property. */
    private java.lang.Boolean indirektsw;

    /** The value of the simple indirektrw property. */
    private java.lang.Boolean indirektrw;

    /** The value of the simple direktsw property. */
    private java.lang.Boolean direktsw;

    /** The value of the simple direktrw property. */
    private java.lang.Boolean direktrw;

    /** The value of the simple anzeige58 property. */
    private java.lang.Integer anzeige58;

    /** The value of the simple datumAnzeige58 property. */
    private java.lang.Boolean datumAnzeige58;

    /** The value of the simple sanierungErfolgt property. */
    private java.lang.Boolean sanierungErfolgt;

    /** The value of the simple sanierungskonzept property. */
    private java.lang.Boolean sanierungskonzept;

    /** The value of the simple keineAngaben property. */
    private java.lang.Boolean keineAngaben;

    /** The value of the simple datAnzeige58 property. */
    private java.util.Date datAnzeige58;

    /** The value of the simple datAnschreiben property. */
    private java.util.Date datAnschreiben;

    /**
     * Simple constructor of AbstractSuevFachdaten instances.
     */
    public AbstractAnhSuevFachdaten() {
    }

    /**
     * Constructor of AbstractSuevFachdaten instances given a simple primary
     * key.
     * @param suevid
     */
    public AbstractAnhSuevFachdaten(java.lang.Integer suevid) {
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
     * @param suevid
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
     * Return the value of the GROESSER_3HA column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getGroesser3ha() {
        return this.groesser3ha;
    }

    /**
     * Set the value of the GROESSER_3HA column.
     * @param groesser3ha
     */
    public void setGroesser3ha(java.lang.Boolean groesser3ha) {
        this.groesser3ha = groesser3ha;
    }

    /**
     * Return the value of the VERS_FLAECHE column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getVersFlaeche() {
        return this.versFlaeche;
    }

    /**
     * Set the value of the VERS_FLAECHE column.
     * @param versFlaeche
     */
    public void setVersFlaeche(java.lang.Integer versFlaeche) {
        this.versFlaeche = versFlaeche;
    }

    /**
     * Return the value of the SUEVKAN_PFLICHT column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getSuevkanPflicht() {
        return this.suevkanPflicht;
    }

    /**
     * Set the value of the SUEVKAN_PFLICHT column.
     * @param suevkanPflicht
     */
    public void setSuevkanPflicht(java.lang.Boolean suevkanPflicht) {
        this.suevkanPflicht = suevkanPflicht;
    }

    /**
     * Return the value of the INDIREKTSW column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getIndirektsw() {
        return this.indirektsw;
    }

    /**
     * Set the value of the INDIREKTSW column.
     * @param indirektsw
     */
    public void setIndirektsw(java.lang.Boolean indirektsw) {
        this.indirektsw = indirektsw;
    }

    /**
     * Return the value of the INDIREKTRW column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getIndirektrw() {
        return this.indirektrw;
    }

    /**
     * Set the value of the INDIREKTRW column.
     * @param indirektrw
     */
    public void setIndirektrw(java.lang.Boolean indirektrw) {
        this.indirektrw = indirektrw;
    }

    /**
     * Return the value of the DIREKTSW column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getDirektsw() {
        return this.direktsw;
    }

    /**
     * Set the value of the DIREKTSW column.
     * @param direktsw
     */
    public void setDirektsw(java.lang.Boolean direktsw) {
        this.direktsw = direktsw;
    }

    /**
     * Return the value of the DIREKTRW column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getDirektrw() {
        return this.direktrw;
    }

    /**
     * Set the value of the DIREKTRW column.
     * @param direktrw
     */
    public void setDirektrw(java.lang.Boolean direktrw) {
        this.direktrw = direktrw;
    }

    /**
     * Return the value of the ANZEIGE58 column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getAnzeige58() {
        return this.anzeige58;
    }

    /**
     * Set the value of the ANZEIGE58 column.
     * @param anzeige58
     */
    public void setAnzeige58(java.lang.Integer anzeige58) {
        this.anzeige58 = anzeige58;
    }

    /**
     * Return the value of the DATUM_ANZEIGE58 column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getDatumAnzeige58() {
        return this.datumAnzeige58;
    }

    /**
     * Set the value of the DATUM_ANZEIGE58 column.
     * @param datumAnzeige58
     */
    public void setDatumAnzeige58(java.lang.Boolean datumAnzeige58) {
        this.datumAnzeige58 = datumAnzeige58;
    }

    /**
     * Return the value of the SANIERUNG_ERFOLGT column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getSanierungErfolgt() {
        return this.sanierungErfolgt;
    }

    /**
     * Set the value of the SANIERUNG_ERFOLGT column.
     * @param sanierungErfolgt
     */
    public void setSanierungErfolgt(java.lang.Boolean sanierungErfolgt) {
        this.sanierungErfolgt = sanierungErfolgt;
    }

    /**
     * Return the value of the SANIERUNGSKONZEPT column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getSanierungskonzept() {
        return this.sanierungskonzept;
    }

    /**
     * Set the value of the SANIERUNGSKONZEPT column.
     * @param sanierungskonzept
     */
    public void setSanierungskonzept(java.lang.Boolean sanierungskonzept) {
        this.sanierungskonzept = sanierungskonzept;
    }

    /**
     * Return the value of the KEINE_ANGABEN column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean getKeineAngaben() {
        return this.keineAngaben;
    }

    /**
     * Set the value of the KEINE_ANGABEN column.
     * @param keineAngaben
     */
    public void setKeineAngaben(java.lang.Boolean keineAngaben) {
        this.keineAngaben = keineAngaben;
    }

    /**
     * Return the value of the DAT_ANZEIGE58 column.
     * @return java.util.Date
     */
    public java.util.Date getDatAnzeige58() {
        return this.datAnzeige58;
    }

    /**
     * Set the value of the DAT_ANZEIGE58 column.
     * @param datAnzeige58
     */
    public void setDatAnzeige58(java.util.Date datAnzeige58) {
        this.datAnzeige58 = datAnzeige58;
    }

    /**
     * Return the value of the DAT_ANSCHREIBEN column.
     * @return java.util.Date
     */
    public java.util.Date getDatAnschreiben() {
        return this.datAnschreiben;
    }

    /**
     * Set the value of the DAT_ANSCHREIBEN column.
     * @param datAnschreiben
     */
    public void setDatAnschreiben(java.util.Date datAnschreiben) {
        this.datAnschreiben = datAnschreiben;
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
        if (!(rhs instanceof AnhSuevFachdaten))
            return false;
        AnhSuevFachdaten that = (AnhSuevFachdaten) rhs;
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
            int suevidValue = this.getObjektid() == null ? 0 : this
                .getObjektid().hashCode();
            result = result * 37 + suevidValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
