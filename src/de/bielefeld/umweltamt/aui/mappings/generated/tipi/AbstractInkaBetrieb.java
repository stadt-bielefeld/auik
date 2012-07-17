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

package de.bielefeld.umweltamt.aui.mappings.generated.tipi;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link InkaBetrieb}.
 */
public abstract class AbstractInkaBetrieb  implements java.io.Serializable {

     private Integer betriebNr;
     private Integer betriebVer;
     private Date gueltigVon;
     private Date gueltigBis;
     private Date aenderungsDatum;
     private Date erfassungsDatum;
     private Integer historienNr;
     private Boolean istAktuellJn;
     private Integer adresseStandNr;
     private Integer adresseStandVer;
     private Integer adresseEinleitNr;
     private Integer adresseEinleitVer;
     private Integer adresseAnsprNr;
     private Integer adresseAnsprVer;
     private String gemeindekennzahl;
     private Integer gemeindeVer;

    public AbstractInkaBetrieb() {
    }

    public AbstractInkaBetrieb(
    	Integer betriebNr) {
        this.betriebNr = betriebNr;
    }

    public AbstractInkaBetrieb(
    	Integer betriebNr, Integer betriebVer, Date gueltigVon, Date gueltigBis, Date aenderungsDatum, Date erfassungsDatum, Integer historienNr, Boolean istAktuellJn, Integer adresseStandNr, Integer adresseStandVer, Integer adresseEinleitNr, Integer adresseEinleitVer, Integer adresseAnsprNr, Integer adresseAnsprVer, String gemeindekennzahl, Integer gemeindeVer) {
        this.betriebNr = betriebNr;
        this.betriebVer = betriebVer;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.historienNr = historienNr;
        this.istAktuellJn = istAktuellJn;
        this.adresseStandNr = adresseStandNr;
        this.adresseStandVer = adresseStandVer;
        this.adresseEinleitNr = adresseEinleitNr;
        this.adresseEinleitVer = adresseEinleitVer;
        this.adresseAnsprNr = adresseAnsprNr;
        this.adresseAnsprVer = adresseAnsprVer;
        this.gemeindekennzahl = gemeindekennzahl;
        this.gemeindeVer = gemeindeVer;
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

    public Integer getAdresseStandNr() {
        return this.adresseStandNr;
    }
    public void setAdresseStandNr(Integer adresseStandNr) {
        this.adresseStandNr = adresseStandNr;
    }

    public Integer getAdresseStandVer() {
        return this.adresseStandVer;
    }
    public void setAdresseStandVer(Integer adresseStandVer) {
        this.adresseStandVer = adresseStandVer;
    }

    public Integer getAdresseEinleitNr() {
        return this.adresseEinleitNr;
    }
    public void setAdresseEinleitNr(Integer adresseEinleitNr) {
        this.adresseEinleitNr = adresseEinleitNr;
    }

    public Integer getAdresseEinleitVer() {
        return this.adresseEinleitVer;
    }
    public void setAdresseEinleitVer(Integer adresseEinleitVer) {
        this.adresseEinleitVer = adresseEinleitVer;
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

}

