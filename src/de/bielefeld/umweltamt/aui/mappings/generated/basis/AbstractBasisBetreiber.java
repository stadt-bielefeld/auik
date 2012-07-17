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

package de.bielefeld.umweltamt.aui.mappings.generated.basis;


import de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsWirtschaftszweige;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link BasisBetreiber}.
 */
public abstract class AbstractBasisBetreiber  implements java.io.Serializable {

     private Integer id;
     private VawsWirtschaftszweige vawsWirtschaftszweige;
     private String betranrede;
     private String betrname;
     private String betrnamezus;
     private String namebetrbeauf;
     private String vornamebetrbeauf;
     private String strasse;
     private Integer hausnr;
     private String hausnrzus;
     private String plzzs;
     private String plz;
     private String ort;
     private String telefon;
     private String telefax;
     private String email;
     private String bemerkungen;
     private Date revidatum;
     private String revihandz;
     private String kassenzeichen;
     private String betrvorname;
     private boolean enabled;
     private boolean deleted;
     private Set<BasisPrioritaet> basisPrioritaets = new HashSet<BasisPrioritaet>(0);
     private Set<BasisObjekt> basisObjekts = new HashSet<BasisObjekt>(0);

    public AbstractBasisBetreiber() {
    }

    public AbstractBasisBetreiber(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractBasisBetreiber(
    	VawsWirtschaftszweige vawsWirtschaftszweige, String betranrede, String betrname, String betrnamezus, String namebetrbeauf, String vornamebetrbeauf, String strasse, Integer hausnr, String hausnrzus, String plzzs, String plz, String ort, String telefon, String telefax, String email, String bemerkungen, Date revidatum, String revihandz, String kassenzeichen, String betrvorname, boolean enabled, boolean deleted, Set<BasisPrioritaet> basisPrioritaets, Set<BasisObjekt> basisObjekts) {
        this.vawsWirtschaftszweige = vawsWirtschaftszweige;
        this.betranrede = betranrede;
        this.betrname = betrname;
        this.betrnamezus = betrnamezus;
        this.namebetrbeauf = namebetrbeauf;
        this.vornamebetrbeauf = vornamebetrbeauf;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.hausnrzus = hausnrzus;
        this.plzzs = plzzs;
        this.plz = plz;
        this.ort = ort;
        this.telefon = telefon;
        this.telefax = telefax;
        this.email = email;
        this.bemerkungen = bemerkungen;
        this.revidatum = revidatum;
        this.revihandz = revihandz;
        this.kassenzeichen = kassenzeichen;
        this.betrvorname = betrvorname;
        this.enabled = enabled;
        this.deleted = deleted;
        this.basisPrioritaets = basisPrioritaets;
        this.basisObjekts = basisObjekts;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public VawsWirtschaftszweige getVawsWirtschaftszweige() {
        return this.vawsWirtschaftszweige;
    }
    public void setVawsWirtschaftszweige(VawsWirtschaftszweige vawsWirtschaftszweige) {
        this.vawsWirtschaftszweige = vawsWirtschaftszweige;
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

    public String getNamebetrbeauf() {
        return this.namebetrbeauf;
    }
    public void setNamebetrbeauf(String namebetrbeauf) {
        this.namebetrbeauf = namebetrbeauf;
    }

    public String getVornamebetrbeauf() {
        return this.vornamebetrbeauf;
    }
    public void setVornamebetrbeauf(String vornamebetrbeauf) {
        this.vornamebetrbeauf = vornamebetrbeauf;
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

    public String getPlzzs() {
        return this.plzzs;
    }
    public void setPlzzs(String plzzs) {
        this.plzzs = plzzs;
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

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public Date getRevidatum() {
        return this.revidatum;
    }
    public void setRevidatum(Date revidatum) {
        this.revidatum = revidatum;
    }

    public String getRevihandz() {
        return this.revihandz;
    }
    public void setRevihandz(String revihandz) {
        this.revihandz = revihandz;
    }

    public String getKassenzeichen() {
        return this.kassenzeichen;
    }
    public void setKassenzeichen(String kassenzeichen) {
        this.kassenzeichen = kassenzeichen;
    }

    public String getBetrvorname() {
        return this.betrvorname;
    }
    public void setBetrvorname(String betrvorname) {
        this.betrvorname = betrvorname;
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

    public Set<BasisPrioritaet> getBasisPrioritaets() {
        return this.basisPrioritaets;
    }
    public void setBasisPrioritaets(Set<BasisPrioritaet> basisPrioritaets) {
        this.basisPrioritaets = basisPrioritaets;
    }

    public Set<BasisObjekt> getBasisObjekts() {
        return this.basisObjekts;
    }
    public void setBasisObjekts(Set<BasisObjekt> basisObjekts) {
        this.basisObjekts = basisObjekts;
    }

}

