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


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjekt;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link VawsFachdaten}.
 */
public abstract class AbstractVawsFachdaten  implements java.io.Serializable {

     private Integer behaelterid;
     private BasisObjekt basisObjekt;
     private String herstellnr;
     private String hersteller;
     private Date datuminbetriebnahme;
     private Date datumerfassung;
     private Date datumaenderung;
     private Date datumgenehmigung;
     private String anlagenart;
     private String behaelterart;
     private String material;
     private String fluessigkeit;
     private String vbfeinstufung;
     private Double menge;
     private Integer wgk;
     private String gefaehrdungsstufe;
     private Integer baujahr;
     private Boolean doppelwandig;
     private Boolean leckanzeige;
     private Boolean auffangraum;
     private Boolean grenzwertgeber;
     private Boolean leckschutzauskleidung;
     private Boolean kellerlagerung;
     private Boolean innenbeschichtung;
     private String beschreibungA;
     private String beschreibungS;
     private Boolean oberirdisch;
     private Boolean unterirdisch;
     private Boolean saugleitung;
     private Boolean rohrKathodenschutz;
     private Boolean ausKupfer;
     private Boolean ausStahl;
     private Boolean mitSchutzrohr;
     private String beschreibungR;
     private Double pruefturnus;
     private Boolean angemahntkz;
     private Date mahnfrist;
     private Date wiedervorlage;
     private Date stillegungsdatum;
     private String bemerkungen;
     private String ausfuehrung;
     private String pruefumfang;
     private String verwendung;
     private boolean enabled;
     private boolean deleted;
     private Boolean ausHdpe;
     private Boolean druckleitung;
     private Boolean schutzSensor;
     private Boolean schutzFolie;
     private Boolean schutzAntiheber;
     private Set<VawsVerwaltungsverf> vawsVerwaltungsverfs = new HashSet<VawsVerwaltungsverf>(0);
     private Set<VawsKontrollen> vawsKontrollens = new HashSet<VawsKontrollen>(0);
     private Set<VawsAnlagenchrono> vawsAnlagenchronos = new HashSet<VawsAnlagenchrono>(0);
     private Set<VawsVerwaltungsgebuehren> vawsVerwaltungsgebuehrens = new HashSet<VawsVerwaltungsgebuehren>(0);

    public AbstractVawsFachdaten() {
    }

    public AbstractVawsFachdaten(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractVawsFachdaten(
    	BasisObjekt basisObjekt, String herstellnr, String hersteller, Date datuminbetriebnahme, Date datumerfassung, Date datumaenderung, Date datumgenehmigung, String anlagenart, String behaelterart, String material, String fluessigkeit, String vbfeinstufung, Double menge, Integer wgk, String gefaehrdungsstufe, Integer baujahr, Boolean doppelwandig, Boolean leckanzeige, Boolean auffangraum, Boolean grenzwertgeber, Boolean leckschutzauskleidung, Boolean kellerlagerung, Boolean innenbeschichtung, String beschreibungA, String beschreibungS, Boolean oberirdisch, Boolean unterirdisch, Boolean saugleitung, Boolean rohrKathodenschutz, Boolean ausKupfer, Boolean ausStahl, Boolean mitSchutzrohr, String beschreibungR, Double pruefturnus, Boolean angemahntkz, Date mahnfrist, Date wiedervorlage, Date stillegungsdatum, String bemerkungen, String ausfuehrung, String pruefumfang, String verwendung, boolean enabled, boolean deleted, Boolean ausHdpe, Boolean druckleitung, Boolean schutzSensor, Boolean schutzFolie, Boolean schutzAntiheber, Set<VawsVerwaltungsverf> vawsVerwaltungsverfs, Set<VawsKontrollen> vawsKontrollens, Set<VawsAnlagenchrono> vawsAnlagenchronos, Set<VawsVerwaltungsgebuehren> vawsVerwaltungsgebuehrens) {
        this.basisObjekt = basisObjekt;
        this.herstellnr = herstellnr;
        this.hersteller = hersteller;
        this.datuminbetriebnahme = datuminbetriebnahme;
        this.datumerfassung = datumerfassung;
        this.datumaenderung = datumaenderung;
        this.datumgenehmigung = datumgenehmigung;
        this.anlagenart = anlagenart;
        this.behaelterart = behaelterart;
        this.material = material;
        this.fluessigkeit = fluessigkeit;
        this.vbfeinstufung = vbfeinstufung;
        this.menge = menge;
        this.wgk = wgk;
        this.gefaehrdungsstufe = gefaehrdungsstufe;
        this.baujahr = baujahr;
        this.doppelwandig = doppelwandig;
        this.leckanzeige = leckanzeige;
        this.auffangraum = auffangraum;
        this.grenzwertgeber = grenzwertgeber;
        this.leckschutzauskleidung = leckschutzauskleidung;
        this.kellerlagerung = kellerlagerung;
        this.innenbeschichtung = innenbeschichtung;
        this.beschreibungA = beschreibungA;
        this.beschreibungS = beschreibungS;
        this.oberirdisch = oberirdisch;
        this.unterirdisch = unterirdisch;
        this.saugleitung = saugleitung;
        this.rohrKathodenschutz = rohrKathodenschutz;
        this.ausKupfer = ausKupfer;
        this.ausStahl = ausStahl;
        this.mitSchutzrohr = mitSchutzrohr;
        this.beschreibungR = beschreibungR;
        this.pruefturnus = pruefturnus;
        this.angemahntkz = angemahntkz;
        this.mahnfrist = mahnfrist;
        this.wiedervorlage = wiedervorlage;
        this.stillegungsdatum = stillegungsdatum;
        this.bemerkungen = bemerkungen;
        this.ausfuehrung = ausfuehrung;
        this.pruefumfang = pruefumfang;
        this.verwendung = verwendung;
        this.enabled = enabled;
        this.deleted = deleted;
        this.ausHdpe = ausHdpe;
        this.druckleitung = druckleitung;
        this.schutzSensor = schutzSensor;
        this.schutzFolie = schutzFolie;
        this.schutzAntiheber = schutzAntiheber;
        this.vawsVerwaltungsverfs = vawsVerwaltungsverfs;
        this.vawsKontrollens = vawsKontrollens;
        this.vawsAnlagenchronos = vawsAnlagenchronos;
        this.vawsVerwaltungsgebuehrens = vawsVerwaltungsgebuehrens;
    }

    public Integer getBehaelterid() {
        return this.behaelterid;
    }
    public void setBehaelterid(Integer behaelterid) {
        this.behaelterid = behaelterid;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public String getHerstellnr() {
        return this.herstellnr;
    }
    public void setHerstellnr(String herstellnr) {
        this.herstellnr = herstellnr;
    }

    public String getHersteller() {
        return this.hersteller;
    }
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public Date getDatuminbetriebnahme() {
        return this.datuminbetriebnahme;
    }
    public void setDatuminbetriebnahme(Date datuminbetriebnahme) {
        this.datuminbetriebnahme = datuminbetriebnahme;
    }

    public Date getDatumerfassung() {
        return this.datumerfassung;
    }
    public void setDatumerfassung(Date datumerfassung) {
        this.datumerfassung = datumerfassung;
    }

    public Date getDatumaenderung() {
        return this.datumaenderung;
    }
    public void setDatumaenderung(Date datumaenderung) {
        this.datumaenderung = datumaenderung;
    }

    public Date getDatumgenehmigung() {
        return this.datumgenehmigung;
    }
    public void setDatumgenehmigung(Date datumgenehmigung) {
        this.datumgenehmigung = datumgenehmigung;
    }

    public String getAnlagenart() {
        return this.anlagenart;
    }
    public void setAnlagenart(String anlagenart) {
        this.anlagenart = anlagenart;
    }

    public String getBehaelterart() {
        return this.behaelterart;
    }
    public void setBehaelterart(String behaelterart) {
        this.behaelterart = behaelterart;
    }

    public String getMaterial() {
        return this.material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFluessigkeit() {
        return this.fluessigkeit;
    }
    public void setFluessigkeit(String fluessigkeit) {
        this.fluessigkeit = fluessigkeit;
    }

    public String getVbfeinstufung() {
        return this.vbfeinstufung;
    }
    public void setVbfeinstufung(String vbfeinstufung) {
        this.vbfeinstufung = vbfeinstufung;
    }

    public Double getMenge() {
        return this.menge;
    }
    public void setMenge(Double menge) {
        this.menge = menge;
    }

    public Integer getWgk() {
        return this.wgk;
    }
    public void setWgk(Integer wgk) {
        this.wgk = wgk;
    }

    public String getGefaehrdungsstufe() {
        return this.gefaehrdungsstufe;
    }
    public void setGefaehrdungsstufe(String gefaehrdungsstufe) {
        this.gefaehrdungsstufe = gefaehrdungsstufe;
    }

    public Integer getBaujahr() {
        return this.baujahr;
    }
    public void setBaujahr(Integer baujahr) {
        this.baujahr = baujahr;
    }

    public Boolean getDoppelwandig() {
        return this.doppelwandig;
    }
    public void setDoppelwandig(Boolean doppelwandig) {
        this.doppelwandig = doppelwandig;
    }

    public Boolean getLeckanzeige() {
        return this.leckanzeige;
    }
    public void setLeckanzeige(Boolean leckanzeige) {
        this.leckanzeige = leckanzeige;
    }

    public Boolean getAuffangraum() {
        return this.auffangraum;
    }
    public void setAuffangraum(Boolean auffangraum) {
        this.auffangraum = auffangraum;
    }

    public Boolean getGrenzwertgeber() {
        return this.grenzwertgeber;
    }
    public void setGrenzwertgeber(Boolean grenzwertgeber) {
        this.grenzwertgeber = grenzwertgeber;
    }

    public Boolean getLeckschutzauskleidung() {
        return this.leckschutzauskleidung;
    }
    public void setLeckschutzauskleidung(Boolean leckschutzauskleidung) {
        this.leckschutzauskleidung = leckschutzauskleidung;
    }

    public Boolean getKellerlagerung() {
        return this.kellerlagerung;
    }
    public void setKellerlagerung(Boolean kellerlagerung) {
        this.kellerlagerung = kellerlagerung;
    }

    public Boolean getInnenbeschichtung() {
        return this.innenbeschichtung;
    }
    public void setInnenbeschichtung(Boolean innenbeschichtung) {
        this.innenbeschichtung = innenbeschichtung;
    }

    public String getBeschreibungA() {
        return this.beschreibungA;
    }
    public void setBeschreibungA(String beschreibungA) {
        this.beschreibungA = beschreibungA;
    }

    public String getBeschreibungS() {
        return this.beschreibungS;
    }
    public void setBeschreibungS(String beschreibungS) {
        this.beschreibungS = beschreibungS;
    }

    public Boolean getOberirdisch() {
        return this.oberirdisch;
    }
    public void setOberirdisch(Boolean oberirdisch) {
        this.oberirdisch = oberirdisch;
    }

    public Boolean getUnterirdisch() {
        return this.unterirdisch;
    }
    public void setUnterirdisch(Boolean unterirdisch) {
        this.unterirdisch = unterirdisch;
    }

    public Boolean getSaugleitung() {
        return this.saugleitung;
    }
    public void setSaugleitung(Boolean saugleitung) {
        this.saugleitung = saugleitung;
    }

    public Boolean getRohrKathodenschutz() {
        return this.rohrKathodenschutz;
    }
    public void setRohrKathodenschutz(Boolean rohrKathodenschutz) {
        this.rohrKathodenschutz = rohrKathodenschutz;
    }

    public Boolean getAusKupfer() {
        return this.ausKupfer;
    }
    public void setAusKupfer(Boolean ausKupfer) {
        this.ausKupfer = ausKupfer;
    }

    public Boolean getAusStahl() {
        return this.ausStahl;
    }
    public void setAusStahl(Boolean ausStahl) {
        this.ausStahl = ausStahl;
    }

    public Boolean getMitSchutzrohr() {
        return this.mitSchutzrohr;
    }
    public void setMitSchutzrohr(Boolean mitSchutzrohr) {
        this.mitSchutzrohr = mitSchutzrohr;
    }

    public String getBeschreibungR() {
        return this.beschreibungR;
    }
    public void setBeschreibungR(String beschreibungR) {
        this.beschreibungR = beschreibungR;
    }

    public Double getPruefturnus() {
        return this.pruefturnus;
    }
    public void setPruefturnus(Double pruefturnus) {
        this.pruefturnus = pruefturnus;
    }

    public Boolean getAngemahntkz() {
        return this.angemahntkz;
    }
    public void setAngemahntkz(Boolean angemahntkz) {
        this.angemahntkz = angemahntkz;
    }

    public Date getMahnfrist() {
        return this.mahnfrist;
    }
    public void setMahnfrist(Date mahnfrist) {
        this.mahnfrist = mahnfrist;
    }

    public Date getWiedervorlage() {
        return this.wiedervorlage;
    }
    public void setWiedervorlage(Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    public Date getStillegungsdatum() {
        return this.stillegungsdatum;
    }
    public void setStillegungsdatum(Date stillegungsdatum) {
        this.stillegungsdatum = stillegungsdatum;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getAusfuehrung() {
        return this.ausfuehrung;
    }
    public void setAusfuehrung(String ausfuehrung) {
        this.ausfuehrung = ausfuehrung;
    }

    public String getPruefumfang() {
        return this.pruefumfang;
    }
    public void setPruefumfang(String pruefumfang) {
        this.pruefumfang = pruefumfang;
    }

    public String getVerwendung() {
        return this.verwendung;
    }
    public void setVerwendung(String verwendung) {
        this.verwendung = verwendung;
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

    public Boolean getAusHdpe() {
        return this.ausHdpe;
    }
    public void setAusHdpe(Boolean ausHdpe) {
        this.ausHdpe = ausHdpe;
    }

    public Boolean getDruckleitung() {
        return this.druckleitung;
    }
    public void setDruckleitung(Boolean druckleitung) {
        this.druckleitung = druckleitung;
    }

    public Boolean getSchutzSensor() {
        return this.schutzSensor;
    }
    public void setSchutzSensor(Boolean schutzSensor) {
        this.schutzSensor = schutzSensor;
    }

    public Boolean getSchutzFolie() {
        return this.schutzFolie;
    }
    public void setSchutzFolie(Boolean schutzFolie) {
        this.schutzFolie = schutzFolie;
    }

    public Boolean getSchutzAntiheber() {
        return this.schutzAntiheber;
    }
    public void setSchutzAntiheber(Boolean schutzAntiheber) {
        this.schutzAntiheber = schutzAntiheber;
    }

    public Set<VawsVerwaltungsverf> getVawsVerwaltungsverfs() {
        return this.vawsVerwaltungsverfs;
    }
    public void setVawsVerwaltungsverfs(Set<VawsVerwaltungsverf> vawsVerwaltungsverfs) {
        this.vawsVerwaltungsverfs = vawsVerwaltungsverfs;
    }

    public Set<VawsKontrollen> getVawsKontrollens() {
        return this.vawsKontrollens;
    }
    public void setVawsKontrollens(Set<VawsKontrollen> vawsKontrollens) {
        this.vawsKontrollens = vawsKontrollens;
    }

    public Set<VawsAnlagenchrono> getVawsAnlagenchronos() {
        return this.vawsAnlagenchronos;
    }
    public void setVawsAnlagenchronos(Set<VawsAnlagenchrono> vawsAnlagenchronos) {
        this.vawsAnlagenchronos = vawsAnlagenchronos;
    }

    public Set<VawsVerwaltungsgebuehren> getVawsVerwaltungsgebuehrens() {
        return this.vawsVerwaltungsgebuehrens;
    }
    public void setVawsVerwaltungsgebuehrens(Set<VawsVerwaltungsgebuehren> vawsVerwaltungsgebuehrens) {
        this.vawsVerwaltungsgebuehrens = vawsVerwaltungsgebuehrens;
    }

}

