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


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjekt;
import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link Anh50Fachdaten}.
 */
public abstract class AbstractAnh50Fachdaten  implements java.io.Serializable {

     private int objektid;
     private BasisObjekt basisObjekt;
     private AnhEntsorger anhEntsorger;
     private String telefon;
     private Boolean erloschen;
     private Date datumantrag;
     private String bemerkungen;
     private Date genehmigung;
     private Date wiedervorlage;
     private String gefaehrdungsklasse;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnh50Fachdaten() {
    }

    public AbstractAnh50Fachdaten(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh50Fachdaten(
    	BasisObjekt basisObjekt, AnhEntsorger anhEntsorger, String telefon, Boolean erloschen, Date datumantrag, String bemerkungen, Date genehmigung, Date wiedervorlage, String gefaehrdungsklasse, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.anhEntsorger = anhEntsorger;
        this.telefon = telefon;
        this.erloschen = erloschen;
        this.datumantrag = datumantrag;
        this.bemerkungen = bemerkungen;
        this.genehmigung = genehmigung;
        this.wiedervorlage = wiedervorlage;
        this.gefaehrdungsklasse = gefaehrdungsklasse;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public int getObjektid() {
        return this.objektid;
    }
    public void setObjektid(int objektid) {
        this.objektid = objektid;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public AnhEntsorger getAnhEntsorger() {
        return this.anhEntsorger;
    }
    public void setAnhEntsorger(AnhEntsorger anhEntsorger) {
        this.anhEntsorger = anhEntsorger;
    }

    public String getTelefon() {
        return this.telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Boolean getErloschen() {
        return this.erloschen;
    }
    public void setErloschen(Boolean erloschen) {
        this.erloschen = erloschen;
    }

    public Date getDatumantrag() {
        return this.datumantrag;
    }
    public void setDatumantrag(Date datumantrag) {
        this.datumantrag = datumantrag;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public Date getGenehmigung() {
        return this.genehmigung;
    }
    public void setGenehmigung(Date genehmigung) {
        this.genehmigung = genehmigung;
    }

    public Date getWiedervorlage() {
        return this.wiedervorlage;
    }
    public void setWiedervorlage(Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    public String getGefaehrdungsklasse() {
        return this.gefaehrdungsklasse;
    }
    public void setGefaehrdungsklasse(String gefaehrdungsklasse) {
        this.gefaehrdungsklasse = gefaehrdungsklasse;
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

