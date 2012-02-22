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

package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;

/**
 * A class that represents a row in the VAWS_ABSCHEIDER table. You can customize
 * the behavior of this class by editing the class, {@link VawsAbfuellflaeche()}
 * .
 */
public abstract class AbstractVawsAbscheider extends
    AbstractVirtuallyDeletableDatabaseTable implements Serializable {
    private static final long serialVersionUID = 8044072297323387680L;

    /**
     * The cached hash code value for this instance. Setting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer behaelterid;

    /** The value of the vawsFachdaten property. */
    private VawsFachdaten vawsFachdaten;

    private java.lang.String sfhersteller;
    private java.lang.String sftyp;
    private java.lang.String sfvolumen;
    private java.lang.String sfmaterial;
    private java.lang.String sfbeschichtung;
    private java.lang.String abhersteller;
    private java.lang.String abtyp;
    private java.lang.String abpruefz;
    private java.lang.String abmaterial;
    private java.lang.String abbeschichtung;
    private java.lang.String abnenngr;
    private java.lang.String zuldn;
    private java.lang.String zulmaterial;
    private java.lang.String zullaenge;
    private java.lang.String verbdn;
    private java.lang.String verbmaterial;
    private java.lang.String verblaenge;
    private java.lang.String sondn;
    private java.lang.String sonmaterial;
    private java.lang.String sonlaenge;
    private java.lang.String oelspeichervol;
    private java.lang.Boolean kompaktanlage;
    private java.lang.Boolean sf;
    private java.lang.Boolean kkl1; // Sprechende Namen und so... ^^
    private java.lang.Boolean lfkl2;
    private java.lang.Boolean ps;
    private java.lang.Boolean ueberhausr;
    private java.lang.Boolean waschanlvorh;
    private java.lang.Boolean abgabe;
    private java.lang.Boolean hlzapfanl;
    private java.lang.Boolean belvonlagerbh;
    private java.lang.Boolean rueckhalteausr;

    /**
     * @return the abbeschichtung
     */
    public java.lang.String getAbbeschichtung() {
        return abbeschichtung;
    }

    /**
     * @param abbeschichtung the abbeschichtung to set
     */
    public void setAbbeschichtung(java.lang.String abbeschichtung) {
        this.abbeschichtung = abbeschichtung;
    }

    /**
     * @return the abgabe
     */
    public java.lang.Boolean getAbgabe() {
        return abgabe;
    }

    /**
     * @param abgabe the abgabe to set
     */
    public void setAbgabe(java.lang.Boolean abgabe) {
        this.abgabe = abgabe;
    }

    /**
     * @return the abhersteller
     */
    public java.lang.String getAbhersteller() {
        return abhersteller;
    }

    /**
     * @param abhersteller the abhersteller to set
     */
    public void setAbhersteller(java.lang.String abhersteller) {
        this.abhersteller = abhersteller;
    }

    /**
     * @return the abmaterial
     */
    public java.lang.String getAbmaterial() {
        return abmaterial;
    }

    /**
     * @param abmaterial the abmaterial to set
     */
    public void setAbmaterial(java.lang.String abmaterial) {
        this.abmaterial = abmaterial;
    }

    /**
     * @return the abnenngr
     */
    public java.lang.String getAbnenngr() {
        return abnenngr;
    }

    /**
     * @param abnenngr the abnenngr to set
     */
    public void setAbnenngr(java.lang.String abnenngr) {
        this.abnenngr = abnenngr;
    }

    /**
     * @return the abpruefz
     */
    public java.lang.String getAbpruefz() {
        return abpruefz;
    }

    /**
     * @param abpruefz the abpruefz to set
     */
    public void setAbpruefz(java.lang.String abpruefz) {
        this.abpruefz = abpruefz;
    }

    /**
     * @return the abtyp
     */
    public java.lang.String getAbtyp() {
        return abtyp;
    }

    /**
     * @param abtyp the abtyp to set
     */
    public void setAbtyp(java.lang.String abtyp) {
        this.abtyp = abtyp;
    }

    /**
     * @return the belvonlagerbh
     */
    public java.lang.Boolean getBelvonlagerbh() {
        return belvonlagerbh;
    }

    /**
     * @param belvonlagerbh the belvonlagerbh to set
     */
    public void setBelvonlagerbh(java.lang.Boolean belvonlagerbh) {
        this.belvonlagerbh = belvonlagerbh;
    }

    /**
     * @return the hlzapfanl
     */
    public java.lang.Boolean getHlzapfanl() {
        return hlzapfanl;
    }

    /**
     * @param hlzapfanl the hlzapfanl to set
     */
    public void setHlzapfanl(java.lang.Boolean hlzapfanl) {
        this.hlzapfanl = hlzapfanl;
    }

    /**
     * @return the kkl1
     */
    public java.lang.Boolean getKkl1() {
        return kkl1;
    }

    /**
     * @param kkl1 the kkl1 to set
     */
    public void setKkl1(java.lang.Boolean kkl1) {
        this.kkl1 = kkl1;
    }

    /**
     * @return the lfkl2
     */
    public java.lang.Boolean getLfkl2() {
        return lfkl2;
    }

    /**
     * @param lfkl2 the lfkl2 to set
     */
    public void setLfkl2(java.lang.Boolean lfkl2) {
        this.lfkl2 = lfkl2;
    }

    /**
     * @return the ps
     */
    public java.lang.Boolean getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(java.lang.Boolean ps) {
        this.ps = ps;
    }

    /**
     * @return the rueckhalteausr
     */
    public java.lang.Boolean getRueckhalteausr() {
        return rueckhalteausr;
    }

    /**
     * @param rueckhalteausr the rueckhalteausr to set
     */
    public void setRueckhalteausr(java.lang.Boolean rueckhalteausr) {
        this.rueckhalteausr = rueckhalteausr;
    }

    /**
     * @return the sf
     */
    public java.lang.Boolean getSf() {
        return sf;
    }

    /**
     * @param sf the sf to set
     */
    public void setSf(java.lang.Boolean sf) {
        this.sf = sf;
    }

    /**
     * @return the sfbeschichtung
     */
    public java.lang.String getSfbeschichtung() {
        return sfbeschichtung;
    }

    /**
     * @param sfbeschichtung the sfbeschichtung to set
     */
    public void setSfbeschichtung(java.lang.String sfbeschichtung) {
        this.sfbeschichtung = sfbeschichtung;
    }

    /**
     * @return the sfhersteller
     */
    public java.lang.String getSfhersteller() {
        return sfhersteller;
    }

    /**
     * @param sfhersteller the sfhersteller to set
     */
    public void setSfhersteller(java.lang.String sfhersteller) {
        this.sfhersteller = sfhersteller;
    }

    /**
     * @return the sfmaterial
     */
    public java.lang.String getSfmaterial() {
        return sfmaterial;
    }

    /**
     * @param sfmaterial the sfmaterial to set
     */
    public void setSfmaterial(java.lang.String sfmaterial) {
        this.sfmaterial = sfmaterial;
    }

    /**
     * @return the sftyp
     */
    public java.lang.String getSftyp() {
        return sftyp;
    }

    /**
     * @param sftyp the sftyp to set
     */
    public void setSftyp(java.lang.String sftyp) {
        this.sftyp = sftyp;
    }

    /**
     * @return the sfvolumen
     */
    public java.lang.String getSfvolumen() {
        return sfvolumen;
    }

    /**
     * @param sfvolumen the sfvolumen to set
     */
    public void setSfvolumen(java.lang.String sfvolumen) {
        this.sfvolumen = sfvolumen;
    }

    /**
     * @return the sondn
     */
    public java.lang.String getSondn() {
        return sondn;
    }

    /**
     * @param sondn the sondn to set
     */
    public void setSondn(java.lang.String sondn) {
        this.sondn = sondn;
    }

    /**
     * @return the sonlaenge
     */
    public java.lang.String getSonlaenge() {
        return sonlaenge;
    }

    /**
     * @param sonlaenge the sonlaenge to set
     */
    public void setSonlaenge(java.lang.String sonlaenge) {
        this.sonlaenge = sonlaenge;
    }

    /**
     * @return the sonmaterial
     */
    public java.lang.String getSonmaterial() {
        return sonmaterial;
    }

    /**
     * @param sonmaterial the sonmaterial to set
     */
    public void setSonmaterial(java.lang.String sonmaterial) {
        this.sonmaterial = sonmaterial;
    }

    /**
     * @return the ueberhausr
     */
    public java.lang.Boolean getUeberhausr() {
        return ueberhausr;
    }

    /**
     * @param ueberhausr the ueberhausr to set
     */
    public void setUeberhausr(java.lang.Boolean ueberhausr) {
        this.ueberhausr = ueberhausr;
    }

    /**
     * @return the verbdn
     */
    public java.lang.String getVerbdn() {
        return verbdn;
    }

    /**
     * @param verbdn the verbdn to set
     */
    public void setVerbdn(java.lang.String verbdn) {
        this.verbdn = verbdn;
    }

    /**
     * @return the verblaenge
     */
    public java.lang.String getVerblaenge() {
        return verblaenge;
    }

    /**
     * @param verblaenge the verblaenge to set
     */
    public void setVerblaenge(java.lang.String verblaenge) {
        this.verblaenge = verblaenge;
    }

    /**
     * @return the verbmaterial
     */
    public java.lang.String getVerbmaterial() {
        return verbmaterial;
    }

    /**
     * @param verbmaterial the verbmaterial to set
     */
    public void setVerbmaterial(java.lang.String verbmaterial) {
        this.verbmaterial = verbmaterial;
    }

    /**
     * @return the waschanlvorh
     */
    public java.lang.Boolean getWaschanlvorh() {
        return waschanlvorh;
    }

    /**
     * @param waschanlvorh the waschanlvorh to set
     */
    public void setWaschanlvorh(java.lang.Boolean waschanlvorh) {
        this.waschanlvorh = waschanlvorh;
    }

    /**
     * @return the zuldn
     */
    public java.lang.String getZuldn() {
        return zuldn;
    }

    /**
     * @param zuldn the zuldn to set
     */
    public void setZuldn(java.lang.String zuldn) {
        this.zuldn = zuldn;
    }

    /**
     * @return the zullaenge
     */
    public java.lang.String getZullaenge() {
        return zullaenge;
    }

    /**
     * @param zullaenge the zullaenge to set
     */
    public void setZullaenge(java.lang.String zullaenge) {
        this.zullaenge = zullaenge;
    }

    /**
     * @return the zulmaterial
     */
    public java.lang.String getZulmaterial() {
        return zulmaterial;
    }

    /**
     * @param zulmaterial the zulmaterial to set
     */
    public void setZulmaterial(java.lang.String zulmaterial) {
        this.zulmaterial = zulmaterial;
    }

    /**
     * Simple constructor of AbstractVawsAbfuellflaeche instances.
     */
    public AbstractVawsAbscheider() {
    }

    /**
     * Constructor of AbstractVawsAbfuellflaeche instances given a simple
     * primary key.
     * @param id
     */
    public AbstractVawsAbscheider(java.lang.Integer id) {
        this.setBehaelterid(id);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getBehaelterid() {
        return behaelterid;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param id
     */
    public void setBehaelterid(java.lang.Integer behaelterid) {
        this.hashValue = 0;
        this.behaelterid = behaelterid;
    }

    /**
     * Return the value of the BEHAELTERID column.
     * @return VawsFachdaten
     */
    public VawsFachdaten getVawsFachdaten() {
        return this.vawsFachdaten;
    }

    /**
     * Set the value of the BEHAELTERID column.
     * @param vawsFachdaten
     */
    public void setVawsFachdaten(VawsFachdaten vawsFachdaten) {
        this.vawsFachdaten = vawsFachdaten;
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
        if (!(rhs instanceof VawsAbscheider))
            return false;
        VawsAbscheider that = (VawsAbscheider) rhs;
        if (this.getBehaelterid() != null && that.getBehaelterid() != null) {
            if (!this.getBehaelterid().equals(that.getBehaelterid())) {
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
            int idValue = this.getBehaelterid() == null ? 0 : this
                .getBehaelterid().hashCode();
            result = result * 37 + idValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }

    /**
     * @return the kompaktanlage
     */
    public java.lang.Boolean getKompaktanlage() {
        return kompaktanlage;
    }

    /**
     * @param kompaktanlage the kompaktanlage to set
     */
    public void setKompaktanlage(java.lang.Boolean kompaktanlage) {
        this.kompaktanlage = kompaktanlage;
    }

    /**
     * @return the oelspeichervol
     */
    public java.lang.String getOelspeichervol() {
        return oelspeichervol;
    }

    /**
     * @param oelspeichervol the oelspeichervol to set
     */
    public void setOelspeichervol(java.lang.String oelspeichervol) {
        this.oelspeichervol = oelspeichervol;
    }
}
