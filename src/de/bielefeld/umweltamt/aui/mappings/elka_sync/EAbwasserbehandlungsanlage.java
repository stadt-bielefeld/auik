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
package de.bielefeld.umweltamt.aui.mappings.elka_sync;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;

// Generated 22.10.2015 16:17:13 by Hibernate Tools 3.4.0.CR1

/**
 * EAbwasserbehandlungsanlage generated by hbm2java
 */
public class EAbwasserbehandlungsanlage implements java.io.Serializable {

    private Integer nr;
    private Integer origNr;
    private EStandort standort;
    private EAdresse adresseByStoAdrNr;
    private EAdresse adresseByBetreibAdrNr;
    private Set<EWasserrecht> Wasserrechts = new HashSet<EWasserrecht>(0);
    private Set<Abaverfahren> Abwasserbehandlungsverfahrens = new HashSet<Abaverfahren>(0);
    private String bezeichnung;
    private Float e32;
    private Float n32;
    private Boolean wartungsvertragTog;
    private Boolean genehmpflichtigTog;
    private Boolean einzelabnahmeTog;
    private String bemerkung;
    private Date aktualDat;
    private Date erstellDat;
    private String herkunft;

    public EAbwasserbehandlungsanlage() {
    }

    public EAbwasserbehandlungsanlage(Integer nr) {
        this.nr = nr;
    }

    public EAbwasserbehandlungsanlage(Integer nr, Integer origNr,
            EStandort standort, EAdresse adresseByStoAdrNr,
            EAdresse adresseByBetreibAdrNr, String bezeichnung, Float e32,
            Float n32, Boolean wartungsvertragTog,
            Boolean genehmpflichtigTog, Boolean einzelabnahmeTog,
            String bemerkung, Date aktualDat, Date erstellDat,
            String herkunft, Set<EWasserrecht> Wasserrechts,
            Set<Abaverfahren> Abwasserbehandlungsverfahrens) {
        this.nr = nr;
        this.origNr = origNr;
        this.standort = standort;
        this.adresseByStoAdrNr = adresseByStoAdrNr;
        this.adresseByBetreibAdrNr = adresseByBetreibAdrNr;
        this.bezeichnung = bezeichnung;
        this.e32 = e32;
        this.n32 = n32;
        this.wartungsvertragTog = wartungsvertragTog;
        this.genehmpflichtigTog = genehmpflichtigTog;
        this.einzelabnahmeTog = einzelabnahmeTog;
        this.bemerkung = bemerkung;
        this.aktualDat = aktualDat;
        this.erstellDat = erstellDat;
        this.herkunft = herkunft;
        this.setWasserrechts(Wasserrechts);
        this.setAbwasserbehandlungsverfahrens(Abwasserbehandlungsverfahrens);
    }

    public Integer getNr() {
        return this.nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    @JsonIgnore
    public Integer getOrigNr() {
        return this.origNr;
    }

    @JsonIgnore
    public void setOrigNr(Integer origNr) {
        this.origNr = origNr;
    }

    public EStandort getStandort() {
        return this.standort;
    }

    public void setStandort(EStandort standort) {
        this.standort = standort;
    }

    public EAdresse getAdresseByStoAdrNr() {
        return this.adresseByStoAdrNr;
    }

    public void setAdresseByStoAdrNr(EAdresse adresseByStoAdrNr) {
        this.adresseByStoAdrNr = adresseByStoAdrNr;
    }

    public EAdresse getAdresseByBetreibAdrNr() {
        return this.adresseByBetreibAdrNr;
    }

    public void setAdresseByBetreibAdrNr(EAdresse adresseByBetreibAdrNr) {
        this.adresseByBetreibAdrNr = adresseByBetreibAdrNr;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Integer getE32() {
        return Math.round(this.e32);
    }

    public void setE32(Float e32) {
        this.e32 = e32;
    }

    public Integer getN32() {
        return Math.round(this.n32);
    }

    public void setN32(Float n32) {
        this.n32 = n32;
    }

    public Boolean getWartungsvertragTog() {
        return this.wartungsvertragTog;
    }

    public void setWartungsvertragTog(Boolean wartungsvertragTog) {
        this.wartungsvertragTog = wartungsvertragTog;
    }

    public Boolean getGenehmpflichtigTog() {
        return this.genehmpflichtigTog;
    }

    public void setGenehmpflichtigTog(Boolean genehmpflichtigTog) {
        this.genehmpflichtigTog = genehmpflichtigTog;
    }

    public Boolean getEinzelabnahmeTog() {
        return this.einzelabnahmeTog;
    }

    public void setEinzelabnahmeTog(Boolean einzelabnahmeTog) {
        this.einzelabnahmeTog = einzelabnahmeTog;
    }

    public String getBemerkung() {
        return this.bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public Date getAktualDat() {
        return this.aktualDat;
    }

    public void setAktualDat(Date aktualDat) {
        this.aktualDat = aktualDat;
    }

    public Date getErstellDat() {
        return this.erstellDat;
    }

    public void setErstellDat(Date erstellDat) {
        this.erstellDat = erstellDat;
    }

    public String getHerkunft() {
        return this.herkunft;
    }

    public void setHerkunft(String herkunft) {
        this.herkunft = herkunft;
    }

    /**
     * Update this EAbwasserbehandlungsanlage with its new values.<br>
     * This is meant to be used after merging!
     * @param copy AtlKlaeranlagen
     */
    private void copy(EAbwasserbehandlungsanlage copy) {
        this.bemerkung = copy.getBemerkung();
        this.adresseByBetreibAdrNr = copy.getAdresseByBetreibAdrNr();
        this.bezeichnung = copy.getBezeichnung();
        this.e32 = copy.getE32().floatValue();
        this.genehmpflichtigTog = copy.getGenehmpflichtigTog();
        this.n32 = copy.getN32().floatValue();
        this.standort = copy.getStandort();
        this.adresseByStoAdrNr = copy.getAdresseByStoAdrNr();
        this.aktualDat = copy.getAktualDat();
        this.einzelabnahmeTog = copy.getEinzelabnahmeTog();
        this.erstellDat = copy.getErstellDat();
        this.herkunft = copy.getHerkunft();
        this.nr = copy.getNr();
        this.wartungsvertragTog = copy.getWartungsvertragTog();
    }

    /**
     * Find an <code>EAbwasserbehandlungsanlage</code> instance by its primary key
     * @param id the primary key value
     * @return <code>EAbwasserbehandlungsanlage</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static EAbwasserbehandlungsanlage findById(java.lang.Integer id) {
        return (EAbwasserbehandlungsanlage)
            new DatabaseAccess().get(EAbwasserbehandlungsanlage.class, id);
    }

    /**
     * Get a list of all <code>EAbwasserbehandlungsanlage</code>
     * @return <code>List&lt;EAbwasserbehandlungsanlage&gt;</code>
     *         all <code>EAbwasserbehandlungsanlage</code>
     */
    public static List<EAbwasserbehandlungsanlage> getAll() {
        return DatabaseQuery.getAll(new EAbwasserbehandlungsanlage());
    }

    public Set<EWasserrecht> getWasserrechts() {
        return Wasserrechts;
    }

    public void setWasserrechts(Set<EWasserrecht> Wasserrechts) {
        this.Wasserrechts = Wasserrechts;
    }
    public Set<Abaverfahren> getAbwasserbehandlungsverfahrens() {
        return Abwasserbehandlungsverfahrens;
    }

    public void setAbwasserbehandlungsverfahrens(Set<Abaverfahren> Abwasserbehandlungsverfahrens) {
	    this.Abwasserbehandlungsverfahrens = Abwasserbehandlungsverfahrens;
	}

    /* Custom code goes below here! */


}
