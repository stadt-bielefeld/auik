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
 * behavior of this class by editing the class, {@link InkaUebergabestelle}.
 */
public abstract class AbstractInkaUebergabestelle implements
    java.io.Serializable {

    private static final long serialVersionUID = 4252037837353049284L;
    private Integer uebergabestelleLfdNr;
    private String gemeindekennzahl;
    private Integer gemeindeVer;
    private Integer uebergabestelleVer;
    private Calendar gueltigVon;
    private Calendar gueltigBis;
    private Calendar aenderungsDatum;
    private Calendar erfassungsDatum;
    private Integer historienNr;
    private Boolean istAktuellJn;
    private Integer betriebNr;
    private Integer betriebVer;
    private Integer genehmigungNr;
    private Integer genehmigungVer;
    private Integer anlagenNr;
    private Integer klaeranlagenVer;
    private Integer kartennummer;
    private Integer tk25Ver;
    private Integer kanalArt;
    private Integer rechtswert;
    private Integer hochwert;

    public AbstractInkaUebergabestelle() {
    }

    public AbstractInkaUebergabestelle(Integer uebergabestelleLfdNr) {
        this.uebergabestelleLfdNr = uebergabestelleLfdNr;
    }

    public AbstractInkaUebergabestelle(Integer uebergabestelleLfdNr,
        String gemeindekennzahl, Integer gemeindeVer,
        Integer uebergabestelleVer, Calendar gueltigVon, Calendar gueltigBis,
        Calendar aenderungsDatum, Calendar erfassungsDatum,
        Integer historienNr, Boolean istAktuellJn, Integer betriebNr,
        Integer betriebVer, Integer genehmigungNr, Integer genehmigungVer,
        Integer anlagenNr, Integer klaeranlagenVer, Integer kartennummer,
        Integer tk25Ver, Integer kanalArt, Integer rechtswert, Integer hochwert) {
        this.uebergabestelleLfdNr = uebergabestelleLfdNr;
        this.gemeindekennzahl = gemeindekennzahl;
        this.gemeindeVer = gemeindeVer;
        this.uebergabestelleVer = uebergabestelleVer;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.historienNr = historienNr;
        this.istAktuellJn = istAktuellJn;
        this.betriebNr = betriebNr;
        this.betriebVer = betriebVer;
        this.genehmigungNr = genehmigungNr;
        this.genehmigungVer = genehmigungVer;
        this.anlagenNr = anlagenNr;
        this.klaeranlagenVer = klaeranlagenVer;
        this.kartennummer = kartennummer;
        this.tk25Ver = tk25Ver;
        this.kanalArt = kanalArt;
        this.rechtswert = rechtswert;
        this.hochwert = hochwert;
    }

    public Integer getUebergabestelleLfdNr() {
        return this.uebergabestelleLfdNr;
    }

    public void setUebergabestelleLfdNr(Integer uebergabestelleLfdNr) {
        this.uebergabestelleLfdNr = uebergabestelleLfdNr;
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

    public Integer getUebergabestelleVer() {
        return this.uebergabestelleVer;
    }

    public void setUebergabestelleVer(Integer uebergabestelleVer) {
        this.uebergabestelleVer = uebergabestelleVer;
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

    public Integer getBetriebNr() {
        return this.betriebNr;
    }

    public void setBetriebNr(Integer betriebNr) {
        this.betriebNr = betriebNr;
    }

    public Integer getBetriebVer() {
        return this.betriebVer;
    }

    public void setBetriebVer(Integer betriebVer) {
        this.betriebVer = betriebVer;
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

    public Integer getAnlagenNr() {
        return this.anlagenNr;
    }

    public void setAnlagenNr(Integer anlagenNr) {
        this.anlagenNr = anlagenNr;
    }

    public Integer getKlaeranlagenVer() {
        return this.klaeranlagenVer;
    }

    public void setKlaeranlagenVer(Integer klaeranlagenVer) {
        this.klaeranlagenVer = klaeranlagenVer;
    }

    public Integer getKartennummer() {
        return this.kartennummer;
    }

    public void setKartennummer(Integer kartennummer) {
        this.kartennummer = kartennummer;
    }

    public Integer getTk25Ver() {
        return this.tk25Ver;
    }

    public void setTk25Ver(Integer tk25Ver) {
        this.tk25Ver = tk25Ver;
    }

    public Integer getKanalArt() {
        return this.kanalArt;
    }

    public void setKanalArt(Integer kanalArt) {
        this.kanalArt = kanalArt;
    }

    public Integer getRechtswert() {
        return this.rechtswert;
    }

    public void setRechtswert(Integer rechtswert) {
        this.rechtswert = rechtswert;
    }

    public Integer getHochwert() {
        return this.hochwert;
    }

    public void setHochwert(Integer hochwert) {
        this.hochwert = hochwert;
    }

}
