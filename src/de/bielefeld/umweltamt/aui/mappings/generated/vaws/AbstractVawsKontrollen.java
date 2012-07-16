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
 * {@link VawsKontrollen}.
 */
public abstract class AbstractVawsKontrollen  implements java.io.Serializable {

     private Integer id;
     private VawsFachdaten vawsFachdaten;
     private String grundderpruefung;
     private Float pruefturnus;
     private Date pruefdatum;
     private String pruefer;
     private Boolean pruefungabgeschlossen;
     private Boolean nachpruefung;
     private Date nachpruefdatum;
     private String nachpruefer;
     private Date naechstepruefung;
     private String pruefergebnis;
     private boolean enabled;
     private boolean deleted;

    public AbstractVawsKontrollen() {
    }

    public AbstractVawsKontrollen(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractVawsKontrollen(
    	VawsFachdaten vawsFachdaten, String grundderpruefung, Float pruefturnus, Date pruefdatum, String pruefer, Boolean pruefungabgeschlossen, Boolean nachpruefung, Date nachpruefdatum, String nachpruefer, Date naechstepruefung, String pruefergebnis, boolean enabled, boolean deleted) {
        this.vawsFachdaten = vawsFachdaten;
        this.grundderpruefung = grundderpruefung;
        this.pruefturnus = pruefturnus;
        this.pruefdatum = pruefdatum;
        this.pruefer = pruefer;
        this.pruefungabgeschlossen = pruefungabgeschlossen;
        this.nachpruefung = nachpruefung;
        this.nachpruefdatum = nachpruefdatum;
        this.nachpruefer = nachpruefer;
        this.naechstepruefung = naechstepruefung;
        this.pruefergebnis = pruefergebnis;
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

    public String getGrundderpruefung() {
        return this.grundderpruefung;
    }
    public void setGrundderpruefung(String grundderpruefung) {
        this.grundderpruefung = grundderpruefung;
    }

    public Float getPruefturnus() {
        return this.pruefturnus;
    }
    public void setPruefturnus(Float pruefturnus) {
        this.pruefturnus = pruefturnus;
    }

    public Date getPruefdatum() {
        return this.pruefdatum;
    }
    public void setPruefdatum(Date pruefdatum) {
        this.pruefdatum = pruefdatum;
    }

    public String getPruefer() {
        return this.pruefer;
    }
    public void setPruefer(String pruefer) {
        this.pruefer = pruefer;
    }

    public Boolean getPruefungabgeschlossen() {
        return this.pruefungabgeschlossen;
    }
    public void setPruefungabgeschlossen(Boolean pruefungabgeschlossen) {
        this.pruefungabgeschlossen = pruefungabgeschlossen;
    }

    public Boolean getNachpruefung() {
        return this.nachpruefung;
    }
    public void setNachpruefung(Boolean nachpruefung) {
        this.nachpruefung = nachpruefung;
    }

    public Date getNachpruefdatum() {
        return this.nachpruefdatum;
    }
    public void setNachpruefdatum(Date nachpruefdatum) {
        this.nachpruefdatum = nachpruefdatum;
    }

    public String getNachpruefer() {
        return this.nachpruefer;
    }
    public void setNachpruefer(String nachpruefer) {
        this.nachpruefer = nachpruefer;
    }

    public Date getNaechstepruefung() {
        return this.naechstepruefung;
    }
    public void setNaechstepruefung(Date naechstepruefung) {
        this.naechstepruefung = naechstepruefung;
    }

    public String getPruefergebnis() {
        return this.pruefergebnis;
    }
    public void setPruefergebnis(String pruefergebnis) {
        this.pruefergebnis = pruefergebnis;
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

