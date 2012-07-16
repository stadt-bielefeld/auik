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

// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.generated.vaws;



/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link VawsAbscheider}.
 */
public abstract class AbstractVawsAbscheider  implements java.io.Serializable {

     private int behaelterid;
     private String sfhersteller;
     private String sftyp;
     private String sfvolumen;
     private String sfmaterial;
     private String sfbeschichtung;
     private String abhersteller;
     private String abtyp;
     private String abpruefz;
     private String abmaterial;
     private String abbeschichtung;
     private String abnenngr;
     private String zuldn;
     private String zulmaterial;
     private String zullaenge;
     private String verbdn;
     private String verbmaterial;
     private String verblaenge;
     private String sondn;
     private String sonmaterial;
     private String sonlaenge;
     private String oelspeichervol;
     private Boolean kompaktanlage;
     private Boolean sf;
     private Boolean kkl1;
     private Boolean lfkl2;
     private Boolean ps;
     private Boolean ueberhausr;
     private Boolean waschanlvorh;
     private Boolean abgabe;
     private Boolean hlzapfanl;
     private Boolean belvonlagerbh;
     private Boolean rueckhalteausr;
     private boolean enabled;
     private boolean deleted;

    public AbstractVawsAbscheider() {
    }

    public AbstractVawsAbscheider(
    	int behaelterid, boolean enabled, boolean deleted) {
        this.behaelterid = behaelterid;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractVawsAbscheider(
    	int behaelterid, String sfhersteller, String sftyp, String sfvolumen, String sfmaterial, String sfbeschichtung, String abhersteller, String abtyp, String abpruefz, String abmaterial, String abbeschichtung, String abnenngr, String zuldn, String zulmaterial, String zullaenge, String verbdn, String verbmaterial, String verblaenge, String sondn, String sonmaterial, String sonlaenge, String oelspeichervol, Boolean kompaktanlage, Boolean sf, Boolean kkl1, Boolean lfkl2, Boolean ps, Boolean ueberhausr, Boolean waschanlvorh, Boolean abgabe, Boolean hlzapfanl, Boolean belvonlagerbh, Boolean rueckhalteausr, boolean enabled, boolean deleted) {
        this.behaelterid = behaelterid;
        this.sfhersteller = sfhersteller;
        this.sftyp = sftyp;
        this.sfvolumen = sfvolumen;
        this.sfmaterial = sfmaterial;
        this.sfbeschichtung = sfbeschichtung;
        this.abhersteller = abhersteller;
        this.abtyp = abtyp;
        this.abpruefz = abpruefz;
        this.abmaterial = abmaterial;
        this.abbeschichtung = abbeschichtung;
        this.abnenngr = abnenngr;
        this.zuldn = zuldn;
        this.zulmaterial = zulmaterial;
        this.zullaenge = zullaenge;
        this.verbdn = verbdn;
        this.verbmaterial = verbmaterial;
        this.verblaenge = verblaenge;
        this.sondn = sondn;
        this.sonmaterial = sonmaterial;
        this.sonlaenge = sonlaenge;
        this.oelspeichervol = oelspeichervol;
        this.kompaktanlage = kompaktanlage;
        this.sf = sf;
        this.kkl1 = kkl1;
        this.lfkl2 = lfkl2;
        this.ps = ps;
        this.ueberhausr = ueberhausr;
        this.waschanlvorh = waschanlvorh;
        this.abgabe = abgabe;
        this.hlzapfanl = hlzapfanl;
        this.belvonlagerbh = belvonlagerbh;
        this.rueckhalteausr = rueckhalteausr;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public int getBehaelterid() {
        return this.behaelterid;
    }
    public void setBehaelterid(int behaelterid) {
        this.behaelterid = behaelterid;
    }

    public String getSfhersteller() {
        return this.sfhersteller;
    }
    public void setSfhersteller(String sfhersteller) {
        this.sfhersteller = sfhersteller;
    }

    public String getSftyp() {
        return this.sftyp;
    }
    public void setSftyp(String sftyp) {
        this.sftyp = sftyp;
    }

    public String getSfvolumen() {
        return this.sfvolumen;
    }
    public void setSfvolumen(String sfvolumen) {
        this.sfvolumen = sfvolumen;
    }

    public String getSfmaterial() {
        return this.sfmaterial;
    }
    public void setSfmaterial(String sfmaterial) {
        this.sfmaterial = sfmaterial;
    }

    public String getSfbeschichtung() {
        return this.sfbeschichtung;
    }
    public void setSfbeschichtung(String sfbeschichtung) {
        this.sfbeschichtung = sfbeschichtung;
    }

    public String getAbhersteller() {
        return this.abhersteller;
    }
    public void setAbhersteller(String abhersteller) {
        this.abhersteller = abhersteller;
    }

    public String getAbtyp() {
        return this.abtyp;
    }
    public void setAbtyp(String abtyp) {
        this.abtyp = abtyp;
    }

    public String getAbpruefz() {
        return this.abpruefz;
    }
    public void setAbpruefz(String abpruefz) {
        this.abpruefz = abpruefz;
    }

    public String getAbmaterial() {
        return this.abmaterial;
    }
    public void setAbmaterial(String abmaterial) {
        this.abmaterial = abmaterial;
    }

    public String getAbbeschichtung() {
        return this.abbeschichtung;
    }
    public void setAbbeschichtung(String abbeschichtung) {
        this.abbeschichtung = abbeschichtung;
    }

    public String getAbnenngr() {
        return this.abnenngr;
    }
    public void setAbnenngr(String abnenngr) {
        this.abnenngr = abnenngr;
    }

    public String getZuldn() {
        return this.zuldn;
    }
    public void setZuldn(String zuldn) {
        this.zuldn = zuldn;
    }

    public String getZulmaterial() {
        return this.zulmaterial;
    }
    public void setZulmaterial(String zulmaterial) {
        this.zulmaterial = zulmaterial;
    }

    public String getZullaenge() {
        return this.zullaenge;
    }
    public void setZullaenge(String zullaenge) {
        this.zullaenge = zullaenge;
    }

    public String getVerbdn() {
        return this.verbdn;
    }
    public void setVerbdn(String verbdn) {
        this.verbdn = verbdn;
    }

    public String getVerbmaterial() {
        return this.verbmaterial;
    }
    public void setVerbmaterial(String verbmaterial) {
        this.verbmaterial = verbmaterial;
    }

    public String getVerblaenge() {
        return this.verblaenge;
    }
    public void setVerblaenge(String verblaenge) {
        this.verblaenge = verblaenge;
    }

    public String getSondn() {
        return this.sondn;
    }
    public void setSondn(String sondn) {
        this.sondn = sondn;
    }

    public String getSonmaterial() {
        return this.sonmaterial;
    }
    public void setSonmaterial(String sonmaterial) {
        this.sonmaterial = sonmaterial;
    }

    public String getSonlaenge() {
        return this.sonlaenge;
    }
    public void setSonlaenge(String sonlaenge) {
        this.sonlaenge = sonlaenge;
    }

    public String getOelspeichervol() {
        return this.oelspeichervol;
    }
    public void setOelspeichervol(String oelspeichervol) {
        this.oelspeichervol = oelspeichervol;
    }

    public Boolean getKompaktanlage() {
        return this.kompaktanlage;
    }
    public void setKompaktanlage(Boolean kompaktanlage) {
        this.kompaktanlage = kompaktanlage;
    }

    public Boolean getSf() {
        return this.sf;
    }
    public void setSf(Boolean sf) {
        this.sf = sf;
    }

    public Boolean getKkl1() {
        return this.kkl1;
    }
    public void setKkl1(Boolean kkl1) {
        this.kkl1 = kkl1;
    }

    public Boolean getLfkl2() {
        return this.lfkl2;
    }
    public void setLfkl2(Boolean lfkl2) {
        this.lfkl2 = lfkl2;
    }

    public Boolean getPs() {
        return this.ps;
    }
    public void setPs(Boolean ps) {
        this.ps = ps;
    }

    public Boolean getUeberhausr() {
        return this.ueberhausr;
    }
    public void setUeberhausr(Boolean ueberhausr) {
        this.ueberhausr = ueberhausr;
    }

    public Boolean getWaschanlvorh() {
        return this.waschanlvorh;
    }
    public void setWaschanlvorh(Boolean waschanlvorh) {
        this.waschanlvorh = waschanlvorh;
    }

    public Boolean getAbgabe() {
        return this.abgabe;
    }
    public void setAbgabe(Boolean abgabe) {
        this.abgabe = abgabe;
    }

    public Boolean getHlzapfanl() {
        return this.hlzapfanl;
    }
    public void setHlzapfanl(Boolean hlzapfanl) {
        this.hlzapfanl = hlzapfanl;
    }

    public Boolean getBelvonlagerbh() {
        return this.belvonlagerbh;
    }
    public void setBelvonlagerbh(Boolean belvonlagerbh) {
        this.belvonlagerbh = belvonlagerbh;
    }

    public Boolean getRueckhalteausr() {
        return this.rueckhalteausr;
    }
    public void setRueckhalteausr(Boolean rueckhalteausr) {
        this.rueckhalteausr = rueckhalteausr;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}

