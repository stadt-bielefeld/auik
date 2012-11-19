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
import java.util.Date;

import de.bielefeld.umweltamt.aui.mappings.StupidHelperClassWhichWillBeGoneSoon;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the ANH_40_FACHDATEN table. You can
 * customize the behavior of this class by editing the class, {@link
 * Anh40Fachdaten()}.
 */
public abstract class AbstractAnh40Fachdaten extends
    StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = -94897277363475975L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer objektid;

    /** The value of the basisObjekt association. */
    private BasisObjekt basisObjekt;

    /** The value of the simple bemerkungen property. */
    private java.lang.String bemerkungen;

    /** The value of the simple ansprechpartner property. */
    private java.lang.String ansprechpartner;

    /** The value of the simple sachbearbeiter property. */
    private java.lang.String sachbearbeiterrav;

    /** The value of the simple sachbearbeiter property. */
    private java.lang.String sachbearbeiterheepen;

    /** The value of the simple sachbearbeiter property. */
    private java.lang.String klaeranlage;

    /** The value of the simple herkunftsbereich property. */
    private java.lang.String herkunftsbereich;

    /** The value of the simple wsg property. */
    private java.lang.Boolean wsg;

    /** The value of the simple relevant property. */
    private java.lang.Short prioritaet;

    /** The value of the simple relevant property. */
    private java.lang.Boolean genehmigungspflicht;

    /** The value of the simple relevant property. */
    private java.lang.Boolean nachtrag;

    /** The value of the simple relevant property. */
    private java.lang.Boolean bimsch;

    /** The value of the simple hausnr property. */
    private java.lang.Integer abwmengegenehmigt;

    /** The value of the simple hausnr property. */
    private java.lang.Integer abwmengeprodspez;

    /** The value of the simple hausnr property. */
    private java.lang.Integer abwmengegesamt;

    /** The value of the simple gen58 property. */
    private java.util.Date gen58;

    /** The value of the simple gen59 property. */
    private java.util.Date gen59;

    public java.lang.Integer getAbwmengegenehmigt() {
        return abwmengegenehmigt;
    }

    public void setAbwmengegenehmigt(java.lang.Integer abwmengegenehmigt) {
        this.abwmengegenehmigt = abwmengegenehmigt;
    }

    public java.lang.Integer getAbwmengegesamt() {
        return abwmengegesamt;
    }

    public void setAbwmengegesamt(java.lang.Integer abwmengegesamt) {
        this.abwmengegesamt = abwmengegesamt;
    }

    public java.lang.Integer getAbwmengeprodspez() {
        return abwmengeprodspez;
    }

    public void setAbwmengeprodspez(java.lang.Integer abwmengeprodspez) {
        this.abwmengeprodspez = abwmengeprodspez;
    }

    public java.lang.Boolean isBimsch() {
        return bimsch;
    }

    public void setBimsch(java.lang.Boolean bimsch) {
        this.bimsch = bimsch;
    }

    public java.lang.Boolean isNachtrag() {
        return nachtrag;
    }

    public void setNachtrag(java.lang.Boolean nachtrag) {
        this.nachtrag = nachtrag;
    }

    /**
     * Simple constructor of AbstractAnh40Fachdaten instances.
     */
    public AbstractAnh40Fachdaten() {
    }

    /**
     * Constructor of AbstractAnh40Fachdaten instances given a simple primary
     * key.
     * @param id
     */
    public AbstractAnh40Fachdaten(java.lang.Integer objektid) {
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
     * Return the value of the ANSPRECHPARTNER column.
     * @return java.lang.String
     */
    public java.lang.String getAnsprechpartner() {
        return this.ansprechpartner;
    }

    /**
     * Set the value of the ANSPRECHPARTNER column.
     * @param ansprechpartner
     */
    public void setAnsprechpartner(java.lang.String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    /**
     * Return the value of the SACHBEARBEITERRAV column.
     * @return java.lang.String
     */
    public java.lang.String getSachbearbeiterrav() {
        return this.sachbearbeiterrav;
    }

    /**
     * Set the value of the SACHBEARBEITERRAV column.
     * @param sachbearbeiter
     */
    public void setSachbearbeiterrav(java.lang.String sachbearbeiterrav) {
        this.sachbearbeiterrav = sachbearbeiterrav;
    }

    /**
     * Return the value of the SACHBEARBEITERHEEPEN column.
     * @return java.lang.String
     */
    public java.lang.String getSachbearbeiterheepen() {
        return this.sachbearbeiterheepen;
    }

    /**
     * Set the value of the SACHBEARBEITERHEEPEN column.
     * @param sachbearbeiter
     */
    public void setSachbearbeiterheepen(java.lang.String sachbearbeiterheepen) {
        this.sachbearbeiterheepen = sachbearbeiterheepen;
    }

    /**
     * Return the value of the KLAERANLAGE column.
     * @return java.lang.String
     */
    public java.lang.String getKlaeranlage() {
        return this.klaeranlage;
    }

    /**
     * Set the value of the HERKUNFTSBEREICH column.
     * @param herkunftsbereich
     */
    public void setKlaeranlage(java.lang.String klaeranlage) {
        this.klaeranlage = klaeranlage;
    }

    /**
     * Return the value of the HERKUNFTSBEREICH column.
     * @return java.lang.String
     */
    public java.lang.String getHerkunftsbereich() {
        return this.herkunftsbereich;
    }

    /**
     * Set the value of the HERKUNFTSBEREICH column.
     * @param herkunftsbereich
     */
    public void setHerkunftsbereich(java.lang.String herkunftsbereich) {
        this.herkunftsbereich = herkunftsbereich;
    }

    /**
     * <<<<<<< AbstractAnh40Fachdaten.java Return the value of the WSG column.
     * @return java.lang.Boolean ======= Return the value of the WSG column.
     * @return java.lang.Short >>>>>>> 1.2
     */

    public java.lang.Boolean isWsg() {
        return this.wsg;
    }

    /**
     * Set the value of the WSG column.
     * @param wsg
     */
    public void setGenehmigungspflicht(java.lang.Boolean genehmigungspflicht) {
        this.genehmigungspflicht = genehmigungspflicht;
    }

    /**
     * Return the value of the WSG column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean isGenehmigungspflicht() {
        return this.genehmigungspflicht;
    }

    /**
     * Set the value of the WSG column.
     * @param wsg
     */
    public void setWsg(java.lang.Boolean wsg) {
        this.wsg = wsg;
    }

    /**
     * Return the value of the RELEVANT column.
     * @return java.lang.Short
     */
    public java.lang.Short getPrioritaet() {
        return this.prioritaet;
    }

    /**
     * Set the value of the RELEVANT column.
     * @param relevant
     */
    public void setPrioritaet(java.lang.Short prioritaet) {
        this.prioritaet = prioritaet;
    }

    /**
     * Return the value of the GEN58 column.
     * @return java.lang.Boolean
     */
    public Date getGen58() {
        return this.gen58;
    }

    /**
     * Set the value of the GEN58 column.
     * @param gen58
     */
    public void setGen58(Date gen58) {
        this.gen58 = gen58;
    }

    /**
     * Return the value of the GEN59 column.
     * @return java.lang.Boolean
     */
    public Date getGen59() {
        return this.gen59;
    }

    /**
     * Set the value of the GEN59 column.
     * @param gen59
     */
    public void setGen59(Date gen59) {
        this.gen59 = gen59;
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
        if (!(rhs instanceof Anh40Fachdaten))
            return false;
        Anh40Fachdaten that = (Anh40Fachdaten) rhs;
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
}
