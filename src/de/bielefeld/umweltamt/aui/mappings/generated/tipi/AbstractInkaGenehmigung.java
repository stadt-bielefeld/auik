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
 * {@link InkaGenehmigung}.
 */
public abstract class AbstractInkaGenehmigung  implements java.io.Serializable {

     private Integer genehmigungNr;
     private Integer genehmigungVer;
     private Date gueltigVon;
     private Date gueltigBis;
     private Date aenderungsDatum;
     private Date erfassungsDatum;
     private Integer historienNr;
     private Boolean istAktuellJn;
     private Integer betriebNr;
     private Integer betriebVer;
     private String behoerdenId;
     private Integer behoerdenVer;
     private Date genehmigungDatum;
     private Boolean befristetJn;
     private Date befristetBis;

    public AbstractInkaGenehmigung() {
    }

    public AbstractInkaGenehmigung(
    	Integer genehmigungNr) {
        this.genehmigungNr = genehmigungNr;
    }

    public AbstractInkaGenehmigung(
    	Integer genehmigungNr, Integer genehmigungVer, Date gueltigVon, Date gueltigBis, Date aenderungsDatum, Date erfassungsDatum, Integer historienNr, Boolean istAktuellJn, Integer betriebNr, Integer betriebVer, String behoerdenId, Integer behoerdenVer, Date genehmigungDatum, Boolean befristetJn, Date befristetBis) {
        this.genehmigungNr = genehmigungNr;
        this.genehmigungVer = genehmigungVer;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.historienNr = historienNr;
        this.istAktuellJn = istAktuellJn;
        this.betriebNr = betriebNr;
        this.betriebVer = betriebVer;
        this.behoerdenId = behoerdenId;
        this.behoerdenVer = behoerdenVer;
        this.genehmigungDatum = genehmigungDatum;
        this.befristetJn = befristetJn;
        this.befristetBis = befristetBis;
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

    public String getBehoerdenId() {
        return this.behoerdenId;
    }
    public void setBehoerdenId(String behoerdenId) {
        this.behoerdenId = behoerdenId;
    }

    public Integer getBehoerdenVer() {
        return this.behoerdenVer;
    }
    public void setBehoerdenVer(Integer behoerdenVer) {
        this.behoerdenVer = behoerdenVer;
    }

    public Date getGenehmigungDatum() {
        return this.genehmigungDatum;
    }
    public void setGenehmigungDatum(Date genehmigungDatum) {
        this.genehmigungDatum = genehmigungDatum;
    }

    public Boolean getBefristetJn() {
        return this.befristetJn;
    }
    public void setBefristetJn(Boolean befristetJn) {
        this.befristetJn = befristetJn;
    }

    public Date getBefristetBis() {
        return this.befristetBis;
    }
    public void setBefristetBis(Date befristetBis) {
        this.befristetBis = befristetBis;
    }

}

