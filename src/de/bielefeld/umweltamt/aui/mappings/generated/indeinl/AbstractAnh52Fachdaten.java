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
 * {@link Anh52Fachdaten}.
 */
public abstract class AbstractAnh52Fachdaten  implements java.io.Serializable {

     private int objektid;
     private BasisObjekt basisObjekt;
     private Integer nrbetriebsstaette;
     private String firmenname;
     private String telefon;
     private String telefax;
     private String ansprechpartner;
     private Date datumgenehmigung;
     private String bemerkungen;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnh52Fachdaten() {
    }

    public AbstractAnh52Fachdaten(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh52Fachdaten(
    	BasisObjekt basisObjekt, Integer nrbetriebsstaette, String firmenname, String telefon, String telefax, String ansprechpartner, Date datumgenehmigung, String bemerkungen, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.nrbetriebsstaette = nrbetriebsstaette;
        this.firmenname = firmenname;
        this.telefon = telefon;
        this.telefax = telefax;
        this.ansprechpartner = ansprechpartner;
        this.datumgenehmigung = datumgenehmigung;
        this.bemerkungen = bemerkungen;
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

    public Integer getNrbetriebsstaette() {
        return this.nrbetriebsstaette;
    }
    public void setNrbetriebsstaette(Integer nrbetriebsstaette) {
        this.nrbetriebsstaette = nrbetriebsstaette;
    }

    public String getFirmenname() {
        return this.firmenname;
    }
    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    public String getTelefon() {
        return this.telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefax() {
        return this.telefax;
    }
    public void setTelefax(String telefax) {
        this.telefax = telefax;
    }

    public String getAnsprechpartner() {
        return this.ansprechpartner;
    }
    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public Date getDatumgenehmigung() {
        return this.datumgenehmigung;
    }
    public void setDatumgenehmigung(Date datumgenehmigung) {
        this.datumgenehmigung = datumgenehmigung;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
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

