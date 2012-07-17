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
 * {@link InkaMessstelle}.
 */
public abstract class AbstractInkaMessstelle  implements java.io.Serializable {

     private Integer messstelleLfdNr;
     private String gemeindekennzahl;
     private Integer gemeindeVer;
     private Integer uebergabestelleLfdNr;
     private Integer uebergabestelleVer;
     private Integer messstelleVer;
     private Date gueltigVon;
     private Date gueltigBis;
     private Date aenderungsDatum;
     private Date erfassungsDatum;
     private Integer historienNr;
     private Boolean istAktuellJn;
     private Integer genehmigungNr;
     private Integer genehmigungVer;
     private Integer messstelleTyp;
     private String beschrMesspunkt;
     private Boolean relevantSumFrachtJn;

    public AbstractInkaMessstelle() {
    }

    public AbstractInkaMessstelle(
    	Integer messstelleLfdNr) {
        this.messstelleLfdNr = messstelleLfdNr;
    }

    public AbstractInkaMessstelle(
    	Integer messstelleLfdNr, String gemeindekennzahl, Integer gemeindeVer, Integer uebergabestelleLfdNr, Integer uebergabestelleVer, Integer messstelleVer, Date gueltigVon, Date gueltigBis, Date aenderungsDatum, Date erfassungsDatum, Integer historienNr, Boolean istAktuellJn, Integer genehmigungNr, Integer genehmigungVer, Integer messstelleTyp, String beschrMesspunkt, Boolean relevantSumFrachtJn) {
        this.messstelleLfdNr = messstelleLfdNr;
        this.gemeindekennzahl = gemeindekennzahl;
        this.gemeindeVer = gemeindeVer;
        this.uebergabestelleLfdNr = uebergabestelleLfdNr;
        this.uebergabestelleVer = uebergabestelleVer;
        this.messstelleVer = messstelleVer;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.historienNr = historienNr;
        this.istAktuellJn = istAktuellJn;
        this.genehmigungNr = genehmigungNr;
        this.genehmigungVer = genehmigungVer;
        this.messstelleTyp = messstelleTyp;
        this.beschrMesspunkt = beschrMesspunkt;
        this.relevantSumFrachtJn = relevantSumFrachtJn;
    }

    public Integer getMessstelleLfdNr() {
        return this.messstelleLfdNr;
    }
    public void setMessstelleLfdNr(Integer messstelleLfdNr) {
        this.messstelleLfdNr = messstelleLfdNr;
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

    public Integer getMessstelleVer() {
        return this.messstelleVer;
    }
    public void setMessstelleVer(Integer messstelleVer) {
        this.messstelleVer = messstelleVer;
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

    public Integer getMessstelleTyp() {
        return this.messstelleTyp;
    }
    public void setMessstelleTyp(Integer messstelleTyp) {
        this.messstelleTyp = messstelleTyp;
    }

    public String getBeschrMesspunkt() {
        return this.beschrMesspunkt;
    }
    public void setBeschrMesspunkt(String beschrMesspunkt) {
        this.beschrMesspunkt = beschrMesspunkt;
    }

    public Boolean getRelevantSumFrachtJn() {
        return this.relevantSumFrachtJn;
    }
    public void setRelevantSumFrachtJn(Boolean relevantSumFrachtJn) {
        this.relevantSumFrachtJn = relevantSumFrachtJn;
    }

}

