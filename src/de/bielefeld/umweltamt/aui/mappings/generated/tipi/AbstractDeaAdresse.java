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
 * {@link DeaAdresse}.
 */
public abstract class AbstractDeaAdresse  implements java.io.Serializable {

     private Integer adresseNr;
     private Integer adresseVer;
     private Date gueltigVon;
     private Date gueltigBis;
     private Date aenderungsDatum;
     private Date erfassungsDatum;
     private Integer historienNr;
     private Boolean istAktuellJn;
     private String name1;
     private String name2;
     private String strasse;
     private String hausnr;
     private String plz;
     private String ort;
     private String staatskennung;
     private String telefon;
     private String telefonMobil;
     private String fax;
     private String email;

    public AbstractDeaAdresse() {
    }

    public AbstractDeaAdresse(
    	Integer adresseNr) {
        this.adresseNr = adresseNr;
    }

    public AbstractDeaAdresse(
    	Integer adresseNr, Integer adresseVer, Date gueltigVon, Date gueltigBis, Date aenderungsDatum, Date erfassungsDatum, Integer historienNr, Boolean istAktuellJn, String name1, String name2, String strasse, String hausnr, String plz, String ort, String staatskennung, String telefon, String telefonMobil, String fax, String email) {
        this.adresseNr = adresseNr;
        this.adresseVer = adresseVer;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.historienNr = historienNr;
        this.istAktuellJn = istAktuellJn;
        this.name1 = name1;
        this.name2 = name2;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.plz = plz;
        this.ort = ort;
        this.staatskennung = staatskennung;
        this.telefon = telefon;
        this.telefonMobil = telefonMobil;
        this.fax = fax;
        this.email = email;
    }

    public Integer getAdresseNr() {
        return this.adresseNr;
    }
    public void setAdresseNr(Integer adresseNr) {
        this.adresseNr = adresseNr;
    }

    public Integer getAdresseVer() {
        return this.adresseVer;
    }
    public void setAdresseVer(Integer adresseVer) {
        this.adresseVer = adresseVer;
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

    public String getName1() {
        return this.name1;
    }
    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return this.name2;
    }
    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getStrasse() {
        return this.strasse;
    }
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnr() {
        return this.hausnr;
    }
    public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }

    public String getPlz() {
        return this.plz;
    }
    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return this.ort;
    }
    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStaatskennung() {
        return this.staatskennung;
    }
    public void setStaatskennung(String staatskennung) {
        this.staatskennung = staatskennung;
    }

    public String getTelefon() {
        return this.telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefonMobil() {
        return this.telefonMobil;
    }
    public void setTelefonMobil(String telefonMobil) {
        this.telefonMobil = telefonMobil;
    }

    public String getFax() {
        return this.fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}

