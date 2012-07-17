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


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjekt;
import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link IndeinlGenehmigung}.
 */
public abstract class AbstractIndeinlGenehmigung  implements java.io.Serializable {

     private int objektid;
     private BasisObjekt basisObjekt;
     private String bemerkungen;
     private Date erstellungsDatum;
     private Date aenderungsDatum;
     private Date antragDatum;
     private Boolean befristet;
     private Date befristetBis;
     private Integer anhang;
     private Integer genMenge;
     private Boolean gen58;
     private Boolean gen59;
     private Boolean selbstueberw;
     private Boolean ESatzung;
     private boolean enabled;
     private boolean deleted;

    public AbstractIndeinlGenehmigung() {
    }

    public AbstractIndeinlGenehmigung(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractIndeinlGenehmigung(
    	BasisObjekt basisObjekt, String bemerkungen, Date erstellungsDatum, Date aenderungsDatum, Date antragDatum, Boolean befristet, Date befristetBis, Integer anhang, Integer genMenge, Boolean gen58, Boolean gen59, Boolean selbstueberw, Boolean ESatzung, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.bemerkungen = bemerkungen;
        this.erstellungsDatum = erstellungsDatum;
        this.aenderungsDatum = aenderungsDatum;
        this.antragDatum = antragDatum;
        this.befristet = befristet;
        this.befristetBis = befristetBis;
        this.anhang = anhang;
        this.genMenge = genMenge;
        this.gen58 = gen58;
        this.gen59 = gen59;
        this.selbstueberw = selbstueberw;
        this.ESatzung = ESatzung;
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

    public Date getErstellungsDatum() {
        return this.erstellungsDatum;
    }
    public void setErstellungsDatum(Date erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

    public Date getAenderungsDatum() {
        return this.aenderungsDatum;
    }
    public void setAenderungsDatum(Date aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }

    public Date getAntragDatum() {
        return this.antragDatum;
    }
    public void setAntragDatum(Date antragDatum) {
        this.antragDatum = antragDatum;
    }

    public Boolean getBefristet() {
        return this.befristet;
    }
    public void setBefristet(Boolean befristet) {
        this.befristet = befristet;
    }

    public Date getBefristetBis() {
        return this.befristetBis;
    }
    public void setBefristetBis(Date befristetBis) {
        this.befristetBis = befristetBis;
    }

    public Integer getAnhang() {
        return this.anhang;
    }
    public void setAnhang(Integer anhang) {
        this.anhang = anhang;
    }

    public Integer getGenMenge() {
        return this.genMenge;
    }
    public void setGenMenge(Integer genMenge) {
        this.genMenge = genMenge;
    }

    public Boolean getGen58() {
        return this.gen58;
    }
    public void setGen58(Boolean gen58) {
        this.gen58 = gen58;
    }

    public Boolean getGen59() {
        return this.gen59;
    }
    public void setGen59(Boolean gen59) {
        this.gen59 = gen59;
    }

    public Boolean getSelbstueberw() {
        return this.selbstueberw;
    }
    public void setSelbstueberw(Boolean selbstueberw) {
        this.selbstueberw = selbstueberw;
    }

    public Boolean getESatzung() {
        return this.ESatzung;
    }
    public void setESatzung(Boolean ESatzung) {
        this.ESatzung = ESatzung;
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

