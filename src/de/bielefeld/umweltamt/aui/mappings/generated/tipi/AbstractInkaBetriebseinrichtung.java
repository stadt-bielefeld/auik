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

package de.bielefeld.umweltamt.aui.mappings.generated.tipi;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link InkaBetriebseinrichtung}.
 */
public abstract class AbstractInkaBetriebseinrichtung  implements java.io.Serializable {

     private Integer betriebseinrichtungNr;
     private Integer betriebseinrichtungVer;
     private Date gueltigVon;
     private Date gueltigBis;
     private Date aenderungsDatum;
     private Date erfassungsDatum;
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
     private String gaaNr;
     private String astnr;
     private String zusatz1;
     private String zusatz2;
     private Integer arbeitsstaetteVer;
     private String wzCode;
     private Integer wzCodeVer;
     private Boolean stilllegungJn;
     private Date stilllegungDatum;

    public AbstractInkaBetriebseinrichtung() {
    }

    public AbstractInkaBetriebseinrichtung(
    	Integer betriebseinrichtungNr) {
        this.betriebseinrichtungNr = betriebseinrichtungNr;
    }

    public AbstractInkaBetriebseinrichtung(
    	Integer betriebseinrichtungNr, Integer betriebseinrichtungVer, Date gueltigVon, Date gueltigBis, Date aenderungsDatum, Date erfassungsDatum, Integer historienNr, Boolean istAktuellJn, Integer betriebNr, Integer betriebVer, Integer adresseBetreibNr, Integer adresseBetreibVer, Integer adresseAnsprNr, Integer adresseAnsprVer, Integer genehmigungNr, Integer genehmigungVer, String gaaNr, String astnr, String zusatz1, String zusatz2, Integer arbeitsstaetteVer, String wzCode, Integer wzCodeVer, Boolean stilllegungJn, Date stilllegungDatum) {
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
        this.gaaNr = gaaNr;
        this.astnr = astnr;
        this.zusatz1 = zusatz1;
        this.zusatz2 = zusatz2;
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

    public Date getGueltigVon() {
        return this.gueltigVon;
    }
    public void setGueltigVon(Date gueltigVon) {
        this.gueltigVon = gueltigVon;
    }

    public Date getGueltigBis() {
        return this.gueltigBis;
    }
    public void setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    public Date getAenderungsDatum() {
        return this.aenderungsDatum;
    }
    public void setAenderungsDatum(Date aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }

    public Date getErfassungsDatum() {
        return this.erfassungsDatum;
    }
    public void setErfassungsDatum(Date erfassungsDatum) {
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

    public String getGaaNr() {
        return this.gaaNr;
    }
    public void setGaaNr(String gaaNr) {
        this.gaaNr = gaaNr;
    }

    public String getAstnr() {
        return this.astnr;
    }
    public void setAstnr(String astnr) {
        this.astnr = astnr;
    }

    public String getZusatz1() {
        return this.zusatz1;
    }
    public void setZusatz1(String zusatz1) {
        this.zusatz1 = zusatz1;
    }

    public String getZusatz2() {
        return this.zusatz2;
    }
    public void setZusatz2(String zusatz2) {
        this.zusatz2 = zusatz2;
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

    public Date getStilllegungDatum() {
        return this.stilllegungDatum;
    }
    public void setStilllegungDatum(Date stilllegungDatum) {
        this.stilllegungDatum = stilllegungDatum;
    }

}

