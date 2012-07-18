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

// Generated 18.07.2012 17:02:12 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.tipi;

import java.util.Calendar;

/**
 * A class that represents a row in a database table. You can customize the
 * behavior of this class by editing the class, {@link InkaAnfallstelle}.
 */
public abstract class AbstractInkaAnfallstelle implements java.io.Serializable {

    private static final long serialVersionUID = 3353684441163807848L;
    private Integer anfallstelleNr;
    private Integer anfallstelleVer;
    private Calendar gueltigVon;
    private Calendar gueltigBis;
    private Calendar aenderungsDatum;
    private Calendar erfassungsDatum;
    private Integer historienNr;
    private Boolean istAktuellJn;
    private Integer betriebseinrichtungNr;
    private Integer betriebseinrichtungVer;
    private String gemeindekennzahl;
    private Integer gemeindeVer;
    private Integer uebergabestelleLfdNr;
    private Integer uebergabestelleVer;
    private Integer genehmigungNr;
    private Integer genehmigungVer;
    private String anhId;
    private Integer anhVer;
    private String beschreibung;
    private Double maxVolTag;
    private Integer volJahr;
    private Boolean dauerbetriebJn;
    private Boolean chargenbetriebJn;

    public AbstractInkaAnfallstelle() {
    }

    public AbstractInkaAnfallstelle(Integer anfallstelleNr) {
        this.anfallstelleNr = anfallstelleNr;
    }

    public AbstractInkaAnfallstelle(Integer anfallstelleNr,
        Integer anfallstelleVer, Calendar gueltigVon, Calendar gueltigBis,
        Calendar aenderungsDatum, Calendar erfassungsDatum,
        Integer historienNr, Boolean istAktuellJn,
        Integer betriebseinrichtungNr, Integer betriebseinrichtungVer,
        String gemeindekennzahl, Integer gemeindeVer,
        Integer uebergabestelleLfdNr, Integer uebergabestelleVer,
        Integer genehmigungNr, Integer genehmigungVer, String anhId,
        Integer anhVer, String beschreibung, Double maxVolTag, Integer volJahr,
        Boolean dauerbetriebJn, Boolean chargenbetriebJn) {
        this.anfallstelleNr = anfallstelleNr;
        this.anfallstelleVer = anfallstelleVer;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.historienNr = historienNr;
        this.istAktuellJn = istAktuellJn;
        this.betriebseinrichtungNr = betriebseinrichtungNr;
        this.betriebseinrichtungVer = betriebseinrichtungVer;
        this.gemeindekennzahl = gemeindekennzahl;
        this.gemeindeVer = gemeindeVer;
        this.uebergabestelleLfdNr = uebergabestelleLfdNr;
        this.uebergabestelleVer = uebergabestelleVer;
        this.genehmigungNr = genehmigungNr;
        this.genehmigungVer = genehmigungVer;
        this.anhId = anhId;
        this.anhVer = anhVer;
        this.beschreibung = beschreibung;
        this.maxVolTag = maxVolTag;
        this.volJahr = volJahr;
        this.dauerbetriebJn = dauerbetriebJn;
        this.chargenbetriebJn = chargenbetriebJn;
    }

    public Integer getAnfallstelleNr() {
        return this.anfallstelleNr;
    }

    public void setAnfallstelleNr(Integer anfallstelleNr) {
        this.anfallstelleNr = anfallstelleNr;
    }

    public Integer getAnfallstelleVer() {
        return this.anfallstelleVer;
    }

    public void setAnfallstelleVer(Integer anfallstelleVer) {
        this.anfallstelleVer = anfallstelleVer;
    }

    public Calendar getGueltigVon() {
        return this.gueltigVon;
    }

    public void setGueltigVon(Calendar gueltigVon) {
        this.gueltigVon = gueltigVon;
    }

    public Calendar getGueltigBis() {
        return this.gueltigBis;
    }

    public void setGueltigBis(Calendar gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    public Calendar getAenderungsDatum() {
        return this.aenderungsDatum;
    }

    public void setAenderungsDatum(Calendar aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }

    public Calendar getErfassungsDatum() {
        return this.erfassungsDatum;
    }

    public void setErfassungsDatum(Calendar erfassungsDatum) {
        this.erfassungsDatum = erfassungsDatum;
    }

    public Integer getHistorienNr() {
        return this.historienNr;
    }

    public void setHistorienNr(Integer historienNr) {
        this.historienNr = historienNr;
    }

    public Boolean getIstAktuellJn() {
        return this.istAktuellJn;
    }

    public void setIstAktuellJn(Boolean istAktuellJn) {
        this.istAktuellJn = istAktuellJn;
    }

    public Integer getBetriebseinrichtungNr() {
        return this.betriebseinrichtungNr;
    }

    public void setBetriebseinrichtungNr(Integer betriebseinrichtungNr) {
        this.betriebseinrichtungNr = betriebseinrichtungNr;
    }

    public Integer getBetriebseinrichtungVer() {
        return this.betriebseinrichtungVer;
    }

    public void setBetriebseinrichtungVer(Integer betriebseinrichtungVer) {
        this.betriebseinrichtungVer = betriebseinrichtungVer;
    }

    public String getGemeindekennzahl() {
        return this.gemeindekennzahl;
    }

    public void setGemeindekennzahl(String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }

    public Integer getGemeindeVer() {
        return this.gemeindeVer;
    }

    public void setGemeindeVer(Integer gemeindeVer) {
        this.gemeindeVer = gemeindeVer;
    }

    public Integer getUebergabestelleLfdNr() {
        return this.uebergabestelleLfdNr;
    }

    public void setUebergabestelleLfdNr(Integer uebergabestelleLfdNr) {
        this.uebergabestelleLfdNr = uebergabestelleLfdNr;
    }

    public Integer getUebergabestelleVer() {
        return this.uebergabestelleVer;
    }

    public void setUebergabestelleVer(Integer uebergabestelleVer) {
        this.uebergabestelleVer = uebergabestelleVer;
    }

    public Integer getGenehmigungNr() {
        return this.genehmigungNr;
    }

    public void setGenehmigungNr(Integer genehmigungNr) {
        this.genehmigungNr = genehmigungNr;
    }

    public Integer getGenehmigungVer() {
        return this.genehmigungVer;
    }

    public void setGenehmigungVer(Integer genehmigungVer) {
        this.genehmigungVer = genehmigungVer;
    }

    public String getAnhId() {
        return this.anhId;
    }

    public void setAnhId(String anhId) {
        this.anhId = anhId;
    }

    public Integer getAnhVer() {
        return this.anhVer;
    }

    public void setAnhVer(Integer anhVer) {
        this.anhVer = anhVer;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Double getMaxVolTag() {
        return this.maxVolTag;
    }

    public void setMaxVolTag(Double maxVolTag) {
        this.maxVolTag = maxVolTag;
    }

    public Integer getVolJahr() {
        return this.volJahr;
    }

    public void setVolJahr(Integer volJahr) {
        this.volJahr = volJahr;
    }

    public Boolean getDauerbetriebJn() {
        return this.dauerbetriebJn;
    }

    public void setDauerbetriebJn(Boolean dauerbetriebJn) {
        this.dauerbetriebJn = dauerbetriebJn;
    }

    public Boolean getChargenbetriebJn() {
        return this.chargenbetriebJn;
    }

    public void setChargenbetriebJn(Boolean chargenbetriebJn) {
        this.chargenbetriebJn = chargenbetriebJn;
    }

}
