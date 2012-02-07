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
 * A class that represents a row in the ANH_BWK table. You can customize the
 * behavior of this class by editing the class, {@link AnhBwkFachdaten}.
 */
public abstract class AbstractAnhBwkFachdaten implements Serializable {
    private static final long serialVersionUID = -2823984723872643513L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer bwkId;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

    /** The value of the simple branche property. */
    private java.lang.String branche;

    /** The value of the simple kHersteller property. */
    private java.lang.String kHersteller;

    /** The value of the simple kTyp property. */
    private java.lang.String kTyp;

    /** The value of the simple kBrennmittel property. */
    private java.lang.String kBrennmittel;

    /** The value of the simple kLeistung property. */
    private java.lang.Integer kLeistung;

    /** The value of the simple datumG property. */
    private java.util.Date datumG;

    /** The value of the simple aba property. */
    private java.lang.Boolean aba;

    /** The value of the simple wBrenner property. */
    private java.lang.String wBrenner;

    /** The value of the simple wWaermetauscher property. */
    private java.lang.String wWaermetauscher;

    /** The value of the simple wAbgasleitung property. */
    private java.lang.String wAbgasleitung;

    /** The value of the simple wKondensableitung property. */
    private java.lang.String wKondensableitung;

    /** The value of the simple abnahme property. */
    private java.lang.String abnahme;

    /** The value of the simple bemerkungen property. */
    private java.lang.String bemerkungen;

    /** The value of the simple anschreiben property. */
    private java.util.Date anschreiben;

    /** The value of the simple erfassung property. */
    private java.lang.Integer erfassung;

    /** The value of the simple genehmigung property. */
    private java.lang.Boolean genehmigung;

    /** The value of the simple genehmigungspflicht property. */
    private java.lang.Boolean genehmigungspflicht;

    /**
     * Simple constructor of AbstractAnhBwk instances.
     */
    public AbstractAnhBwkFachdaten() {
    }

    /**
     * Constructor of AbstractAnhBwk instances given a simple primary key.
     * @param bwkId
     */
    public AbstractAnhBwkFachdaten(java.lang.Integer bwkId) {
        this.setBwkId(bwkId);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getBwkId() {
        return bwkId;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param bwkId
     */
    public void setBwkId(java.lang.Integer bwkId) {
        this.hashValue = 0;
        this.bwkId = bwkId;
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
     * Return the value of the BRANCHE column.
     * @return java.lang.String
     */
    public java.lang.String getBranche() {
        return this.branche;
    }

    /**
     * Set the value of the BRANCHE column.
     * @param branche
     */
    public void setBranche(java.lang.String branche) {
        this.branche = branche;
    }

    /**
     * Return the value of the K_HERSTELLER column.
     * @return java.lang.String
     */
    public java.lang.String getKHersteller() {
        return this.kHersteller;
    }

    /**
     * Set the value of the K_HERSTELLER column.
     * @param kHersteller
     */
    public void setKHersteller(java.lang.String kHersteller) {
        this.kHersteller = kHersteller;
    }

    /**
     * Return the value of the K_TYP column.
     * @return java.lang.String
     */
    public java.lang.String getKTyp() {
        return this.kTyp;
    }

    /**
     * Set the value of the K_TYP column.
     * @param kTyp
     */
    public void setKTyp(java.lang.String kTyp) {
        this.kTyp = kTyp;
    }

    /**
     * Return the value of the K_BRENNMITTEL column.
     * @return java.lang.String
     */
    public java.lang.String getKBrennmittel() {
        return this.kBrennmittel;
    }

    /**
     * Set the value of the K_BRENNMITTEL column.
     * @param kBrennmittel
     */
    public void setKBrennmittel(java.lang.String kBrennmittel) {
        this.kBrennmittel = kBrennmittel;
    }

    /**
     * Return the value of the K_LEISTUNG column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getKLeistung() {
        return this.kLeistung;
    }

    /**
     * Set the value of the K_LEISTUNG column.
     * @param kLeistung
     */
    public void setKLeistung(java.lang.Integer kLeistung) {
        this.kLeistung = kLeistung;
    }

    /**
     * Return the value of the DATUM_G column.
     * @return java.util.Date
     */
    public java.util.Date getDatumG() {
        return this.datumG;
    }

    /**
     * Set the value of the DATUM_G column.
     * @param datumG
     */
    public void setDatumG(java.util.Date datumG) {
        this.datumG = datumG;
    }

    /**
     * Return the value of the ABA column.
     * @return java.lang.Short
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
     * Return the value of the W_BRENNER column.
     * @return java.lang.String
     */
    public java.lang.String getWBrenner() {
        return this.wBrenner;
    }

    /**
     * Set the value of the W_BRENNER column.
     * @param wBrenner
     */
    public void setWBrenner(java.lang.String wBrenner) {
        this.wBrenner = wBrenner;
    }

    /**
     * Return the value of the W_WAERMETAUSCHER column.
     * @return java.lang.String
     */
    public java.lang.String getWWaermetauscher() {
        return this.wWaermetauscher;
    }

    /**
     * Set the value of the W_WAERMETAUSCHER column.
     * @param wWaermetauscher
     */
    public void setWWaermetauscher(java.lang.String wWaermetauscher) {
        this.wWaermetauscher = wWaermetauscher;
    }

    /**
     * Return the value of the W_ABGASLEITUNG column.
     * @return java.lang.String
     */
    public java.lang.String getWAbgasleitung() {
        return this.wAbgasleitung;
    }

    /**
     * Set the value of the W_ABGASLEITUNG column.
     * @param wAbgasleitung
     */
    public void setWAbgasleitung(java.lang.String wAbgasleitung) {
        this.wAbgasleitung = wAbgasleitung;
    }

    /**
     * Return the value of the W_KONDENSABLEITUNG column.
     * @return java.lang.String
     */
    public java.lang.String getWKondensableitung() {
        return this.wKondensableitung;
    }

    /**
     * Set the value of the W_KONDENSABLEITUNG column.
     * @param wKondensableitung
     */
    public void setWKondensableitung(java.lang.String wKondensableitung) {
        this.wKondensableitung = wKondensableitung;
    }

    /**
     * Return the value of the ABNAHME column.
     * @return java.lang.String
     */
    public java.lang.String getAbnahme() {
        return this.abnahme;
    }

    /**
     * Set the value of the ABNAHME column.
     * @param abnahme
     */
    public void setAbnahme(java.lang.String abnahme) {
        this.abnahme = abnahme;
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
     * Return the value of the ANSCHREIBEN column.
     * @return java.lang.String
     */
    public java.util.Date getAnschreiben() {
        return this.anschreiben;
    }

    /**
     * Set the value of the ANSCHREIBEN column.
     * @param anschreiben
     */
    public void setAnschreiben(java.util.Date anschreiben) {
        this.anschreiben = anschreiben;
    }

    /**
     * Return the value of the ERFASSUNG column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getErfassung() {
        return this.erfassung;
    }

    /**
     * Set the value of the ERFASSUNG column.
     * @param erfassung
     */
    public void setErfassung(java.lang.Integer erfassung) {
        this.erfassung = erfassung;
    }

    /**
     * Return the value of the GENEHMIGUNG column.
     * @return java.lang.Short
     */
    public java.lang.Boolean getGenehmigung() {
        return this.genehmigung;
    }

    /**
     * Set the value of the GENEHMIGUNG column.
     * @param genehmigung
     */
    public void setGenehmigung(java.lang.Boolean genehmigung) {
        this.genehmigung = genehmigung;
    }

    /**
     * Return the value of the GENEHMIGUNGSPFLICHT column.
     * @return java.lang.Short
     */
    public java.lang.Boolean getGenehmigungspflicht() {
        return this.genehmigungspflicht;
    }

    /**
     * Set the value of the GENEHMIGUNGSPFLICHT column.
     * @param genehmigungspflicht
     */
    public void setGenehmigungspflicht(java.lang.Boolean genehmigungspflicht) {
        this.genehmigungspflicht = genehmigungspflicht;
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
        if (!(rhs instanceof AnhBwkFachdaten))
            return false;
        AnhBwkFachdaten that = (AnhBwkFachdaten) rhs;
        if (this.getBwkId() != null && that.getBwkId() != null) {
            if (!this.getBwkId().equals(that.getBwkId())) {
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
            int bwkIdValue = this.getBwkId() == null ? 0 : this.getBwkId()
                .hashCode();
            result = result * 37 + bwkIdValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
