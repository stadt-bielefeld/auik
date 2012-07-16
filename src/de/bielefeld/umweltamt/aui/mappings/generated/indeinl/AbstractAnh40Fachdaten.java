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
 * {@link Anh40Fachdaten}.
 */
public abstract class AbstractAnh40Fachdaten  implements java.io.Serializable {

     private int objektid;
     private BasisObjekt basisObjekt;
     private String bemerkungen;
     private String ansprechpartner;
     private String sachbearbeiterrav;
     private String sachbearbeiterheepen;
     private String klaeranlage;
     private String herkunftsbereich;
     private Boolean wsg;
     private Short prioritaet;
     private Boolean genehmigungspflicht;
     private Boolean nachtrag;
     private Boolean bimsch;
     private Integer abwmengegenehmigt;
     private Integer abwmengeprodspez;
     private Integer abwmengegesamt;
     private Date gen58;
     private Date gen59;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnh40Fachdaten() {
    }

    public AbstractAnh40Fachdaten(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh40Fachdaten(
    	BasisObjekt basisObjekt, String bemerkungen, String ansprechpartner, String sachbearbeiterrav, String sachbearbeiterheepen, String klaeranlage, String herkunftsbereich, Boolean wsg, Short prioritaet, Boolean genehmigungspflicht, Boolean nachtrag, Boolean bimsch, Integer abwmengegenehmigt, Integer abwmengeprodspez, Integer abwmengegesamt, Date gen58, Date gen59, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.bemerkungen = bemerkungen;
        this.ansprechpartner = ansprechpartner;
        this.sachbearbeiterrav = sachbearbeiterrav;
        this.sachbearbeiterheepen = sachbearbeiterheepen;
        this.klaeranlage = klaeranlage;
        this.herkunftsbereich = herkunftsbereich;
        this.wsg = wsg;
        this.prioritaet = prioritaet;
        this.genehmigungspflicht = genehmigungspflicht;
        this.nachtrag = nachtrag;
        this.bimsch = bimsch;
        this.abwmengegenehmigt = abwmengegenehmigt;
        this.abwmengeprodspez = abwmengeprodspez;
        this.abwmengegesamt = abwmengegesamt;
        this.gen58 = gen58;
        this.gen59 = gen59;
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

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getAnsprechpartner() {
        return this.ansprechpartner;
    }
    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public String getSachbearbeiterrav() {
        return this.sachbearbeiterrav;
    }
    public void setSachbearbeiterrav(String sachbearbeiterrav) {
        this.sachbearbeiterrav = sachbearbeiterrav;
    }

    public String getSachbearbeiterheepen() {
        return this.sachbearbeiterheepen;
    }
    public void setSachbearbeiterheepen(String sachbearbeiterheepen) {
        this.sachbearbeiterheepen = sachbearbeiterheepen;
    }

    public String getKlaeranlage() {
        return this.klaeranlage;
    }
    public void setKlaeranlage(String klaeranlage) {
        this.klaeranlage = klaeranlage;
    }

    public String getHerkunftsbereich() {
        return this.herkunftsbereich;
    }
    public void setHerkunftsbereich(String herkunftsbereich) {
        this.herkunftsbereich = herkunftsbereich;
    }

    public Boolean getWsg() {
        return this.wsg;
    }
    public void setWsg(Boolean wsg) {
        this.wsg = wsg;
    }

    public Short getPrioritaet() {
        return this.prioritaet;
    }
    public void setPrioritaet(Short prioritaet) {
        this.prioritaet = prioritaet;
    }

    public Boolean getGenehmigungspflicht() {
        return this.genehmigungspflicht;
    }
    public void setGenehmigungspflicht(Boolean genehmigungspflicht) {
        this.genehmigungspflicht = genehmigungspflicht;
    }

    public Boolean getNachtrag() {
        return this.nachtrag;
    }
    public void setNachtrag(Boolean nachtrag) {
        this.nachtrag = nachtrag;
    }

    public Boolean getBimsch() {
        return this.bimsch;
    }
    public void setBimsch(Boolean bimsch) {
        this.bimsch = bimsch;
    }

    public Integer getAbwmengegenehmigt() {
        return this.abwmengegenehmigt;
    }
    public void setAbwmengegenehmigt(Integer abwmengegenehmigt) {
        this.abwmengegenehmigt = abwmengegenehmigt;
    }

    public Integer getAbwmengeprodspez() {
        return this.abwmengeprodspez;
    }
    public void setAbwmengeprodspez(Integer abwmengeprodspez) {
        this.abwmengeprodspez = abwmengeprodspez;
    }

    public Integer getAbwmengegesamt() {
        return this.abwmengegesamt;
    }
    public void setAbwmengegesamt(Integer abwmengegesamt) {
        this.abwmengegesamt = abwmengegesamt;
    }

    public Date getGen58() {
        return this.gen58;
    }
    public void setGen58(Date gen58) {
        this.gen58 = gen58;
    }

    public Date getGen59() {
        return this.gen59;
    }
    public void setGen59(Date gen59) {
        this.gen59 = gen59;
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

