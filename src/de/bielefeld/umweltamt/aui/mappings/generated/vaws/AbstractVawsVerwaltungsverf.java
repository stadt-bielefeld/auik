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

package de.bielefeld.umweltamt.aui.mappings.generated.vaws;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link VawsVerwaltungsverf}.
 */
public abstract class AbstractVawsVerwaltungsverf  implements java.io.Serializable {

     private Integer id;
     private VawsFachdaten vawsFachdaten;
     private Date datum;
     private String massnahme;
     private Date wiedervorlage;
     private Boolean wvverwverf;
     private boolean enabled;
     private boolean deleted;

    public AbstractVawsVerwaltungsverf() {
    }

    public AbstractVawsVerwaltungsverf(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractVawsVerwaltungsverf(
    	VawsFachdaten vawsFachdaten, Date datum, String massnahme, Date wiedervorlage, Boolean wvverwverf, boolean enabled, boolean deleted) {
        this.vawsFachdaten = vawsFachdaten;
        this.datum = datum;
        this.massnahme = massnahme;
        this.wiedervorlage = wiedervorlage;
        this.wvverwverf = wvverwverf;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public VawsFachdaten getVawsFachdaten() {
        return this.vawsFachdaten;
    }
    public void setVawsFachdaten(VawsFachdaten vawsFachdaten) {
        this.vawsFachdaten = vawsFachdaten;
    }

    public Date getDatum() {
        return this.datum;
    }
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getMassnahme() {
        return this.massnahme;
    }
    public void setMassnahme(String massnahme) {
        this.massnahme = massnahme;
    }

    public Date getWiedervorlage() {
        return this.wiedervorlage;
    }
    public void setWiedervorlage(Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    public Boolean getWvverwverf() {
        return this.wvverwverf;
    }
    public void setWvverwverf(Boolean wvverwverf) {
        this.wvverwverf = wvverwverf;
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

