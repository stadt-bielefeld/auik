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
 * {@link VawsVerwaltungsgebuehren}.
 */
public abstract class AbstractVawsVerwaltungsgebuehren  implements java.io.Serializable {

     private Integer id;
     private VawsGebuehrenarten vawsGebuehrenarten;
     private VawsFachdaten vawsFachdaten;
     private Date datum;
     private Float betrag;
     private String abschnitt;
     private String kassenzeichen;
     private boolean enabled;
     private boolean deleted;

    public AbstractVawsVerwaltungsgebuehren() {
    }

    public AbstractVawsVerwaltungsgebuehren(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractVawsVerwaltungsgebuehren(
    	VawsGebuehrenarten vawsGebuehrenarten, VawsFachdaten vawsFachdaten, Date datum, Float betrag, String abschnitt, String kassenzeichen, boolean enabled, boolean deleted) {
        this.vawsGebuehrenarten = vawsGebuehrenarten;
        this.vawsFachdaten = vawsFachdaten;
        this.datum = datum;
        this.betrag = betrag;
        this.abschnitt = abschnitt;
        this.kassenzeichen = kassenzeichen;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public VawsGebuehrenarten getVawsGebuehrenarten() {
        return this.vawsGebuehrenarten;
    }
    public void setVawsGebuehrenarten(VawsGebuehrenarten vawsGebuehrenarten) {
        this.vawsGebuehrenarten = vawsGebuehrenarten;
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

    public Float getBetrag() {
        return this.betrag;
    }
    public void setBetrag(Float betrag) {
        this.betrag = betrag;
    }

    public String getAbschnitt() {
        return this.abschnitt;
    }
    public void setAbschnitt(String abschnitt) {
        this.abschnitt = abschnitt;
    }

    public String getKassenzeichen() {
        return this.kassenzeichen;
    }
    public void setKassenzeichen(String kassenzeichen) {
        this.kassenzeichen = kassenzeichen;
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

