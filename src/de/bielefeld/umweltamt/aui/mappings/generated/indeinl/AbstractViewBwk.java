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

package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link ViewBwk}.
 */
public abstract class AbstractViewBwk  implements java.io.Serializable {

     private Integer objektid;
     private String betranrede;
     private String betrname;
     private String betrnamezus;
     private String strasse;
     private Integer hausnr;
     private String hausnrzus;
     private String branche;
     private String KHersteller;
     private String KTyp;
     private String KBrennmittel;
     private Integer KLeistung;
     private Date datumG;
     private Boolean aba;
     private String WBrenner;
     private String WWaermetauscher;
     private String WAbgasleitung;
     private String WKondensableitung;
     private String abnahme;
     private String bemerkungen;
     private Date anschreiben;
     private Integer erfassung;
     private Boolean genehmigung;

    public AbstractViewBwk() {
    }

    public AbstractViewBwk(
    	Integer objektid) {
        this.objektid = objektid;
    }

    public AbstractViewBwk(
    	Integer objektid, String betranrede, String betrname, String betrnamezus, String strasse, Integer hausnr, String hausnrzus, String branche, String KHersteller, String KTyp, String KBrennmittel, Integer KLeistung, Date datumG, Boolean aba, String WBrenner, String WWaermetauscher, String WAbgasleitung, String WKondensableitung, String abnahme, String bemerkungen, Date anschreiben, Integer erfassung, Boolean genehmigung) {
        this.objektid = objektid;
        this.betranrede = betranrede;
        this.betrname = betrname;
        this.betrnamezus = betrnamezus;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.hausnrzus = hausnrzus;
        this.branche = branche;
        this.KHersteller = KHersteller;
        this.KTyp = KTyp;
        this.KBrennmittel = KBrennmittel;
        this.KLeistung = KLeistung;
        this.datumG = datumG;
        this.aba = aba;
        this.WBrenner = WBrenner;
        this.WWaermetauscher = WWaermetauscher;
        this.WAbgasleitung = WAbgasleitung;
        this.WKondensableitung = WKondensableitung;
        this.abnahme = abnahme;
        this.bemerkungen = bemerkungen;
        this.anschreiben = anschreiben;
        this.erfassung = erfassung;
        this.genehmigung = genehmigung;
    }

    public Integer getObjektid() {
        return this.objektid;
    }
    public void setObjektid(Integer objektid) {
        this.objektid = objektid;
    }

    public String getBetranrede() {
        return this.betranrede;
    }
    public void setBetranrede(String betranrede) {
        this.betranrede = betranrede;
    }

    public String getBetrname() {
        return this.betrname;
    }
    public void setBetrname(String betrname) {
        this.betrname = betrname;
    }

    public String getBetrnamezus() {
        return this.betrnamezus;
    }
    public void setBetrnamezus(String betrnamezus) {
        this.betrnamezus = betrnamezus;
    }

    public String getStrasse() {
        return this.strasse;
    }
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public Integer getHausnr() {
        return this.hausnr;
    }
    public void setHausnr(Integer hausnr) {
        this.hausnr = hausnr;
    }

    public String getHausnrzus() {
        return this.hausnrzus;
    }
    public void setHausnrzus(String hausnrzus) {
        this.hausnrzus = hausnrzus;
    }

    public String getBranche() {
        return this.branche;
    }
    public void setBranche(String branche) {
        this.branche = branche;
    }

    public String getKHersteller() {
        return this.KHersteller;
    }
    public void setKHersteller(String KHersteller) {
        this.KHersteller = KHersteller;
    }

    public String getKTyp() {
        return this.KTyp;
    }
    public void setKTyp(String KTyp) {
        this.KTyp = KTyp;
    }

    public String getKBrennmittel() {
        return this.KBrennmittel;
    }
    public void setKBrennmittel(String KBrennmittel) {
        this.KBrennmittel = KBrennmittel;
    }

    public Integer getKLeistung() {
        return this.KLeistung;
    }
    public void setKLeistung(Integer KLeistung) {
        this.KLeistung = KLeistung;
    }

    public Date getDatumG() {
        return this.datumG;
    }
    public void setDatumG(Date datumG) {
        this.datumG = datumG;
    }

    public Boolean getAba() {
        return this.aba;
    }
    public void setAba(Boolean aba) {
        this.aba = aba;
    }

    public String getWBrenner() {
        return this.WBrenner;
    }
    public void setWBrenner(String WBrenner) {
        this.WBrenner = WBrenner;
    }

    public String getWWaermetauscher() {
        return this.WWaermetauscher;
    }
    public void setWWaermetauscher(String WWaermetauscher) {
        this.WWaermetauscher = WWaermetauscher;
    }

    public String getWAbgasleitung() {
        return this.WAbgasleitung;
    }
    public void setWAbgasleitung(String WAbgasleitung) {
        this.WAbgasleitung = WAbgasleitung;
    }

    public String getWKondensableitung() {
        return this.WKondensableitung;
    }
    public void setWKondensableitung(String WKondensableitung) {
        this.WKondensableitung = WKondensableitung;
    }

    public String getAbnahme() {
        return this.abnahme;
    }
    public void setAbnahme(String abnahme) {
        this.abnahme = abnahme;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public Date getAnschreiben() {
        return this.anschreiben;
    }
    public void setAnschreiben(Date anschreiben) {
        this.anschreiben = anschreiben;
    }

    public Integer getErfassung() {
        return this.erfassung;
    }
    public void setErfassung(Integer erfassung) {
        this.erfassung = erfassung;
    }

    public Boolean getGenehmigung() {
        return this.genehmigung;
    }
    public void setGenehmigung(Boolean genehmigung) {
        this.genehmigung = genehmigung;
    }

}

