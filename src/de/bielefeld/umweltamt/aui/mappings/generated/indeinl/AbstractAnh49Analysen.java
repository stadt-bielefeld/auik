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

package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link Anh49Analysen}.
 */
public abstract class AbstractAnh49Analysen  implements java.io.Serializable {

     private Integer id;
     private Anh49Fachdaten anh49Fachdaten;
     private Date datum;
     private String institut;
     private String csbWert;
     private String csbGrenzwert;
     private String aoxWert;
     private String aoxGrenzwert;
     private String kwWert;
     private String kwGrenzwert;
     private String zinkWert;
     private String zinkGrenzwert;
     private String bsb5Wert;
     private String bsb5CsbRelation;
     private String bemerkungen;
     private String bikGrenzwert;
     private String bikWert;
     private String phWert;
     private String phGrenzwert;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnh49Analysen() {
    }

    public AbstractAnh49Analysen(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh49Analysen(
    	Anh49Fachdaten anh49Fachdaten, Date datum, String institut, String csbWert, String csbGrenzwert, String aoxWert, String aoxGrenzwert, String kwWert, String kwGrenzwert, String zinkWert, String zinkGrenzwert, String bsb5Wert, String bsb5CsbRelation, String bemerkungen, String bikGrenzwert, String bikWert, String phWert, String phGrenzwert, boolean enabled, boolean deleted) {
        this.anh49Fachdaten = anh49Fachdaten;
        this.datum = datum;
        this.institut = institut;
        this.csbWert = csbWert;
        this.csbGrenzwert = csbGrenzwert;
        this.aoxWert = aoxWert;
        this.aoxGrenzwert = aoxGrenzwert;
        this.kwWert = kwWert;
        this.kwGrenzwert = kwGrenzwert;
        this.zinkWert = zinkWert;
        this.zinkGrenzwert = zinkGrenzwert;
        this.bsb5Wert = bsb5Wert;
        this.bsb5CsbRelation = bsb5CsbRelation;
        this.bemerkungen = bemerkungen;
        this.bikGrenzwert = bikGrenzwert;
        this.bikWert = bikWert;
        this.phWert = phWert;
        this.phGrenzwert = phGrenzwert;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Anh49Fachdaten getAnh49Fachdaten() {
        return this.anh49Fachdaten;
    }
    public void setAnh49Fachdaten(Anh49Fachdaten anh49Fachdaten) {
        this.anh49Fachdaten = anh49Fachdaten;
    }

    public Date getDatum() {
        return this.datum;
    }
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getInstitut() {
        return this.institut;
    }
    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public String getCsbWert() {
        return this.csbWert;
    }
    public void setCsbWert(String csbWert) {
        this.csbWert = csbWert;
    }

    public String getCsbGrenzwert() {
        return this.csbGrenzwert;
    }
    public void setCsbGrenzwert(String csbGrenzwert) {
        this.csbGrenzwert = csbGrenzwert;
    }

    public String getAoxWert() {
        return this.aoxWert;
    }
    public void setAoxWert(String aoxWert) {
        this.aoxWert = aoxWert;
    }

    public String getAoxGrenzwert() {
        return this.aoxGrenzwert;
    }
    public void setAoxGrenzwert(String aoxGrenzwert) {
        this.aoxGrenzwert = aoxGrenzwert;
    }

    public String getKwWert() {
        return this.kwWert;
    }
    public void setKwWert(String kwWert) {
        this.kwWert = kwWert;
    }

    public String getKwGrenzwert() {
        return this.kwGrenzwert;
    }
    public void setKwGrenzwert(String kwGrenzwert) {
        this.kwGrenzwert = kwGrenzwert;
    }

    public String getZinkWert() {
        return this.zinkWert;
    }
    public void setZinkWert(String zinkWert) {
        this.zinkWert = zinkWert;
    }

    public String getZinkGrenzwert() {
        return this.zinkGrenzwert;
    }
    public void setZinkGrenzwert(String zinkGrenzwert) {
        this.zinkGrenzwert = zinkGrenzwert;
    }

    public String getBsb5Wert() {
        return this.bsb5Wert;
    }
    public void setBsb5Wert(String bsb5Wert) {
        this.bsb5Wert = bsb5Wert;
    }

    public String getBsb5CsbRelation() {
        return this.bsb5CsbRelation;
    }
    public void setBsb5CsbRelation(String bsb5CsbRelation) {
        this.bsb5CsbRelation = bsb5CsbRelation;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getBikGrenzwert() {
        return this.bikGrenzwert;
    }
    public void setBikGrenzwert(String bikGrenzwert) {
        this.bikGrenzwert = bikGrenzwert;
    }

    public String getBikWert() {
        return this.bikWert;
    }
    public void setBikWert(String bikWert) {
        this.bikWert = bikWert;
    }

    public String getPhWert() {
        return this.phWert;
    }
    public void setPhWert(String phWert) {
        this.phWert = phWert;
    }

    public String getPhGrenzwert() {
        return this.phGrenzwert;
    }
    public void setPhGrenzwert(String phGrenzwert) {
        this.phGrenzwert = phGrenzwert;
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

}

