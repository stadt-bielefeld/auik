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

// Generated 17.07.2012 18:33:28 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;



/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link Anh55Fachdaten}.
 */
public abstract class AbstractAnh55Fachdaten  implements java.io.Serializable {

     private int objektid;
     private Boolean abgemeldet;
     private Boolean putztuecher;
     private Boolean teppich;
     private Boolean matten;
     private Boolean haushaltstex;
     private Boolean berufskl;
     private Boolean gaststhotel;
     private Boolean krankenhaus;
     private Boolean heimwaesche;
     private Integer anteilwaschgut;
     private Boolean vlies;
     private Boolean fischfleisch;
     private Integer anteilgesamtgut;
     private Boolean betrwasseraufber;
     private Boolean chlor;
     private Boolean aktivchlor;
     private String sachbearbeiter;
     private String entgebId;
     private String bemerkungen;
     private String mengewaesche;
     private String sonsttex;
     private String monatwasserverb;
     private String waschsituation;
     private String ansprechpartner;
     private String branche;
     private Boolean loesungsmittel;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnh55Fachdaten() {
    }

    public AbstractAnh55Fachdaten(
    	int objektid, boolean enabled, boolean deleted) {
        this.objektid = objektid;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh55Fachdaten(
    	int objektid, Boolean abgemeldet, Boolean putztuecher, Boolean teppich, Boolean matten, Boolean haushaltstex, Boolean berufskl, Boolean gaststhotel, Boolean krankenhaus, Boolean heimwaesche, Integer anteilwaschgut, Boolean vlies, Boolean fischfleisch, Integer anteilgesamtgut, Boolean betrwasseraufber, Boolean chlor, Boolean aktivchlor, String sachbearbeiter, String entgebId, String bemerkungen, String mengewaesche, String sonsttex, String monatwasserverb, String waschsituation, String ansprechpartner, String branche, Boolean loesungsmittel, boolean enabled, boolean deleted) {
        this.objektid = objektid;
        this.abgemeldet = abgemeldet;
        this.putztuecher = putztuecher;
        this.teppich = teppich;
        this.matten = matten;
        this.haushaltstex = haushaltstex;
        this.berufskl = berufskl;
        this.gaststhotel = gaststhotel;
        this.krankenhaus = krankenhaus;
        this.heimwaesche = heimwaesche;
        this.anteilwaschgut = anteilwaschgut;
        this.vlies = vlies;
        this.fischfleisch = fischfleisch;
        this.anteilgesamtgut = anteilgesamtgut;
        this.betrwasseraufber = betrwasseraufber;
        this.chlor = chlor;
        this.aktivchlor = aktivchlor;
        this.sachbearbeiter = sachbearbeiter;
        this.entgebId = entgebId;
        this.bemerkungen = bemerkungen;
        this.mengewaesche = mengewaesche;
        this.sonsttex = sonsttex;
        this.monatwasserverb = monatwasserverb;
        this.waschsituation = waschsituation;
        this.ansprechpartner = ansprechpartner;
        this.branche = branche;
        this.loesungsmittel = loesungsmittel;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public int getObjektid() {
        return this.objektid;
    }
    public void setObjektid(int objektid) {
        this.objektid = objektid;
    }

    public Boolean getAbgemeldet() {
        return this.abgemeldet;
    }
    public void setAbgemeldet(Boolean abgemeldet) {
        this.abgemeldet = abgemeldet;
    }

    public Boolean getPutztuecher() {
        return this.putztuecher;
    }
    public void setPutztuecher(Boolean putztuecher) {
        this.putztuecher = putztuecher;
    }

    public Boolean getTeppich() {
        return this.teppich;
    }
    public void setTeppich(Boolean teppich) {
        this.teppich = teppich;
    }

    public Boolean getMatten() {
        return this.matten;
    }
    public void setMatten(Boolean matten) {
        this.matten = matten;
    }

    public Boolean getHaushaltstex() {
        return this.haushaltstex;
    }
    public void setHaushaltstex(Boolean haushaltstex) {
        this.haushaltstex = haushaltstex;
    }

    public Boolean getBerufskl() {
        return this.berufskl;
    }
    public void setBerufskl(Boolean berufskl) {
        this.berufskl = berufskl;
    }

    public Boolean getGaststhotel() {
        return this.gaststhotel;
    }
    public void setGaststhotel(Boolean gaststhotel) {
        this.gaststhotel = gaststhotel;
    }

    public Boolean getKrankenhaus() {
        return this.krankenhaus;
    }
    public void setKrankenhaus(Boolean krankenhaus) {
        this.krankenhaus = krankenhaus;
    }

    public Boolean getHeimwaesche() {
        return this.heimwaesche;
    }
    public void setHeimwaesche(Boolean heimwaesche) {
        this.heimwaesche = heimwaesche;
    }

    public Integer getAnteilwaschgut() {
        return this.anteilwaschgut;
    }
    public void setAnteilwaschgut(Integer anteilwaschgut) {
        this.anteilwaschgut = anteilwaschgut;
    }

    public Boolean getVlies() {
        return this.vlies;
    }
    public void setVlies(Boolean vlies) {
        this.vlies = vlies;
    }

    public Boolean getFischfleisch() {
        return this.fischfleisch;
    }
    public void setFischfleisch(Boolean fischfleisch) {
        this.fischfleisch = fischfleisch;
    }

    public Integer getAnteilgesamtgut() {
        return this.anteilgesamtgut;
    }
    public void setAnteilgesamtgut(Integer anteilgesamtgut) {
        this.anteilgesamtgut = anteilgesamtgut;
    }

    public Boolean getBetrwasseraufber() {
        return this.betrwasseraufber;
    }
    public void setBetrwasseraufber(Boolean betrwasseraufber) {
        this.betrwasseraufber = betrwasseraufber;
    }

    public Boolean getChlor() {
        return this.chlor;
    }
    public void setChlor(Boolean chlor) {
        this.chlor = chlor;
    }

    public Boolean getAktivchlor() {
        return this.aktivchlor;
    }
    public void setAktivchlor(Boolean aktivchlor) {
        this.aktivchlor = aktivchlor;
    }

    public String getSachbearbeiter() {
        return this.sachbearbeiter;
    }
    public void setSachbearbeiter(String sachbearbeiter) {
        this.sachbearbeiter = sachbearbeiter;
    }

    public String getEntgebId() {
        return this.entgebId;
    }
    public void setEntgebId(String entgebId) {
        this.entgebId = entgebId;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getMengewaesche() {
        return this.mengewaesche;
    }
    public void setMengewaesche(String mengewaesche) {
        this.mengewaesche = mengewaesche;
    }

    public String getSonsttex() {
        return this.sonsttex;
    }
    public void setSonsttex(String sonsttex) {
        this.sonsttex = sonsttex;
    }

    public String getMonatwasserverb() {
        return this.monatwasserverb;
    }
    public void setMonatwasserverb(String monatwasserverb) {
        this.monatwasserverb = monatwasserverb;
    }

    public String getWaschsituation() {
        return this.waschsituation;
    }
    public void setWaschsituation(String waschsituation) {
        this.waschsituation = waschsituation;
    }

    public String getAnsprechpartner() {
        return this.ansprechpartner;
    }
    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public String getBranche() {
        return this.branche;
    }
    public void setBranche(String branche) {
        this.branche = branche;
    }

    public Boolean getLoesungsmittel() {
        return this.loesungsmittel;
    }
    public void setLoesungsmittel(Boolean loesungsmittel) {
        this.loesungsmittel = loesungsmittel;
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

