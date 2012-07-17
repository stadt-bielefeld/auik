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


import de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsStandortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsWassereinzugsgebiete;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link BasisStandort}.
 */
public abstract class AbstractBasisStandort  implements java.io.Serializable {

     private Integer id;
     private BasisGemarkung basisGemarkung;
     private VawsWassereinzugsgebiete vawsWassereinzugsgebiete;
     private VawsStandortgghwsg vawsStandortgghwsg;
     private String strasse;
     private Integer hausnr;
     private String hausnrzus;
     private String plz;
     private Float rechtswert;
     private Float hochwert;
     private String flur;
     private String flurstueck;
     private String entgebid;
     private String vornameeigent;
     private String nameeigent;
     private String strasseeigent;
     private Integer hausnreigent;
     private String hausnrzuseigent;
     private Date revidatum;
     private String revihandz;
     private Integer wassermenge;
     private String sachbe33rav;
     private String sachbe33hee;
     private Serializable theGeom;
     private boolean enabled;
     private boolean deleted;
     private Set<BasisPrioritaet> basisPrioritaets = new HashSet<BasisPrioritaet>(0);
     private Set<BasisObjekt> basisObjekts = new HashSet<BasisObjekt>(0);

    public AbstractBasisStandort() {
    }

    public AbstractBasisStandort(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractBasisStandort(
    	BasisGemarkung basisGemarkung, VawsWassereinzugsgebiete vawsWassereinzugsgebiete, VawsStandortgghwsg vawsStandortgghwsg, String strasse, Integer hausnr, String hausnrzus, String plz, Float rechtswert, Float hochwert, String flur, String flurstueck, String entgebid, String vornameeigent, String nameeigent, String strasseeigent, Integer hausnreigent, String hausnrzuseigent, Date revidatum, String revihandz, Integer wassermenge, String sachbe33rav, String sachbe33hee, Serializable theGeom, boolean enabled, boolean deleted, Set<BasisPrioritaet> basisPrioritaets, Set<BasisObjekt> basisObjekts) {
        this.basisGemarkung = basisGemarkung;
        this.vawsWassereinzugsgebiete = vawsWassereinzugsgebiete;
        this.vawsStandortgghwsg = vawsStandortgghwsg;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.hausnrzus = hausnrzus;
        this.plz = plz;
        this.rechtswert = rechtswert;
        this.hochwert = hochwert;
        this.flur = flur;
        this.flurstueck = flurstueck;
        this.entgebid = entgebid;
        this.vornameeigent = vornameeigent;
        this.nameeigent = nameeigent;
        this.strasseeigent = strasseeigent;
        this.hausnreigent = hausnreigent;
        this.hausnrzuseigent = hausnrzuseigent;
        this.revidatum = revidatum;
        this.revihandz = revihandz;
        this.wassermenge = wassermenge;
        this.sachbe33rav = sachbe33rav;
        this.sachbe33hee = sachbe33hee;
        this.theGeom = theGeom;
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

    public BasisGemarkung getBasisGemarkung() {
        return this.basisGemarkung;
    }
    public void setBasisGemarkung(BasisGemarkung basisGemarkung) {
        this.basisGemarkung = basisGemarkung;
    }

    public VawsWassereinzugsgebiete getVawsWassereinzugsgebiete() {
        return this.vawsWassereinzugsgebiete;
    }
    public void setVawsWassereinzugsgebiete(VawsWassereinzugsgebiete vawsWassereinzugsgebiete) {
        this.vawsWassereinzugsgebiete = vawsWassereinzugsgebiete;
    }

    public VawsStandortgghwsg getVawsStandortgghwsg() {
        return this.vawsStandortgghwsg;
    }
    public void setVawsStandortgghwsg(VawsStandortgghwsg vawsStandortgghwsg) {
        this.vawsStandortgghwsg = vawsStandortgghwsg;
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

    public String getPlz() {
        return this.plz;
    }
    public void setPlz(String plz) {
        this.plz = plz;
    }

    public Float getRechtswert() {
        return this.rechtswert;
    }
    public void setRechtswert(Float rechtswert) {
        this.rechtswert = rechtswert;
    }

    public Float getHochwert() {
        return this.hochwert;
    }
    public void setHochwert(Float hochwert) {
        this.hochwert = hochwert;
    }

    public String getFlur() {
        return this.flur;
    }
    public void setFlur(String flur) {
        this.flur = flur;
    }

    public String getFlurstueck() {
        return this.flurstueck;
    }
    public void setFlurstueck(String flurstueck) {
        this.flurstueck = flurstueck;
    }

    public String getEntgebid() {
        return this.entgebid;
    }
    public void setEntgebid(String entgebid) {
        this.entgebid = entgebid;
    }

    public String getVornameeigent() {
        return this.vornameeigent;
    }
    public void setVornameeigent(String vornameeigent) {
        this.vornameeigent = vornameeigent;
    }

    public String getNameeigent() {
        return this.nameeigent;
    }
    public void setNameeigent(String nameeigent) {
        this.nameeigent = nameeigent;
    }

    public String getStrasseeigent() {
        return this.strasseeigent;
    }
    public void setStrasseeigent(String strasseeigent) {
        this.strasseeigent = strasseeigent;
    }

    public Integer getHausnreigent() {
        return this.hausnreigent;
    }
    public void setHausnreigent(Integer hausnreigent) {
        this.hausnreigent = hausnreigent;
    }

    public String getHausnrzuseigent() {
        return this.hausnrzuseigent;
    }
    public void setHausnrzuseigent(String hausnrzuseigent) {
        this.hausnrzuseigent = hausnrzuseigent;
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

    public Integer getWassermenge() {
        return this.wassermenge;
    }
    public void setWassermenge(Integer wassermenge) {
        this.wassermenge = wassermenge;
    }

    public String getSachbe33rav() {
        return this.sachbe33rav;
    }
    public void setSachbe33rav(String sachbe33rav) {
        this.sachbe33rav = sachbe33rav;
    }

    public String getSachbe33hee() {
        return this.sachbe33hee;
    }
    public void setSachbe33hee(String sachbe33hee) {
        this.sachbe33hee = sachbe33hee;
    }

    public Serializable getTheGeom() {
        return this.theGeom;
    }
    public void setTheGeom(Serializable theGeom) {
        this.theGeom = theGeom;
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

