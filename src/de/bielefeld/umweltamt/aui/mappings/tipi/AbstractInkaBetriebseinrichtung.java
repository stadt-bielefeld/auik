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

// Generated 19.07.2012 11:17:37 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.tipi;

import java.util.Calendar;

/**
 * A class that represents a row in a database table. You can customize the
 * behavior of this class by editing the class, {@link InkaBetriebseinrichtung}.
 */
public abstract class AbstractInkaBetriebseinrichtung implements
    java.io.Serializable {

    private static final long serialVersionUID = -7342242681380768162L;
    private Integer betriebseinrichtungNr;
    private Integer betriebseinrichtungVer;
    private Calendar gueltigVon;
    private Calendar gueltigBis;
    private Calendar aenderungsDatum;
    private Calendar erfassungsDatum;
    private Integer historienNr;
    private Boolean istAktuellJn;
    private Integer betriebNr;
    private Integer betriebVer;
    private Integer adresseBetreibNr;
    private Integer adresseBetreibVer;
    private Integer adresseAnsprNr;
    private Integer adresseAnsprVer;
    private Integer genehmigungNr;
    private Integer genehmigungVer;
    private Integer arbeitsstaetteSeqNr;
    private Integer arbeitsstaetteVer;
    private String wzCode;
    private Integer wzCodeVer;
    private Boolean stilllegungJn;
    private Calendar stilllegungDatum;

    public AbstractInkaBetriebseinrichtung() {
    }

    public AbstractInkaBetriebseinrichtung(Integer betriebseinrichtungNr) {
        this.betriebseinrichtungNr = betriebseinrichtungNr;
    }

    public AbstractInkaBetriebseinrichtung(Integer betriebseinrichtungNr,
        Integer betriebseinrichtungVer, Calendar gueltigVon,
        Calendar gueltigBis, Calendar aenderungsDatum,
        Calendar erfassungsDatum, Integer historienNr, Boolean istAktuellJn,
        Integer betriebNr, Integer betriebVer, Integer adresseBetreibNr,
        Integer adresseBetreibVer, Integer adresseAnsprNr,
        Integer adresseAnsprVer, Integer genehmigungNr, Integer genehmigungVer,
        Integer arbeitsstaetteSeqNr, Integer arbeitsstaetteVer, String wzCode,
        Integer wzCodeVer, Boolean stilllegungJn, Calendar stilllegungDatum) {
        this.betriebseinrichtungNr = betriebseinrichtungNr;
        this.betriebseinrichtungVer = betriebseinrichtungVer;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.historienNr = historienNr;
        this.istAktuellJn = istAktuellJn;
        this.betriebNr = betriebNr;
        this.betriebVer = betriebVer;
        this.adresseBetreibNr = adresseBetreibNr;
        this.adresseBetreibVer = adresseBetreibVer;
        this.adresseAnsprNr = adresseAnsprNr;
        this.adresseAnsprVer = adresseAnsprVer;
        this.genehmigungNr = genehmigungNr;
        this.genehmigungVer = genehmigungVer;
        this.arbeitsstaetteSeqNr = arbeitsstaetteSeqNr;
        this.arbeitsstaetteVer = arbeitsstaetteVer;
        this.wzCode = wzCode;
        this.wzCodeVer = wzCodeVer;
        this.stilllegungJn = stilllegungJn;
        this.stilllegungDatum = stilllegungDatum;
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

    public Integer getAdresseBetreibNr() {
        return this.adresseBetreibNr;
    }

    public void setAdresseBetreibNr(Integer adresseBetreibNr) {
        this.adresseBetreibNr = adresseBetreibNr;
    }

    public Integer getAdresseBetreibVer() {
        return this.adresseBetreibVer;
    }

    public void setAdresseBetreibVer(Integer adresseBetreibVer) {
        this.adresseBetreibVer = adresseBetreibVer;
    }

    public Integer getAdresseAnsprNr() {
        return this.adresseAnsprNr;
    }

    public void setAdresseAnsprNr(Integer adresseAnsprNr) {
        this.adresseAnsprNr = adresseAnsprNr;
    }

    public Integer getAdresseAnsprVer() {
        return this.adresseAnsprVer;
    }

    public void setAdresseAnsprVer(Integer adresseAnsprVer) {
        this.adresseAnsprVer = adresseAnsprVer;
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

    public Integer getArbeitsstaetteSeqNr() {
        return this.arbeitsstaetteSeqNr;
    }

    public void setArbeitsstaetteSeqNr(Integer arbeitsstaetteSeqNr) {
        this.arbeitsstaetteSeqNr = arbeitsstaetteSeqNr;
    }

    public Integer getArbeitsstaetteVer() {
        return this.arbeitsstaetteVer;
    }

    public void setArbeitsstaetteVer(Integer arbeitsstaetteVer) {
        this.arbeitsstaetteVer = arbeitsstaetteVer;
    }

    public String getWzCode() {
        return this.wzCode;
    }

    public void setWzCode(String wzCode) {
        this.wzCode = wzCode;
    }

    public Integer getWzCodeVer() {
        return this.wzCodeVer;
    }

    public void setWzCodeVer(Integer wzCodeVer) {
        this.wzCodeVer = wzCodeVer;
    }

    public Boolean getStilllegungJn() {
        return this.stilllegungJn;
    }

    public void setStilllegungJn(Boolean stilllegungJn) {
        this.stilllegungJn = stilllegungJn;
    }

    public Calendar getStilllegungDatum() {
        return this.stilllegungDatum;
    }

    public void setStilllegungDatum(Calendar stilllegungDatum) {
        this.stilllegungDatum = stilllegungDatum;
    }

}
