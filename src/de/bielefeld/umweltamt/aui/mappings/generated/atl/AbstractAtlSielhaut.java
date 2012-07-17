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

package de.bielefeld.umweltamt.aui.mappings.generated.atl;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link AtlSielhaut}.
 */
public abstract class AbstractAtlSielhaut  implements java.io.Serializable {

     private Integer id;
     private String bezeichnung;
     private String haltungsnr;
     private String alarmplannr;
     private String entgeb;
     private Double rechtswert;
     private Double hochwert;
     private String lage;
     private String bemerkungen;
     private String twabfluss;
     private String bsb;
     private String ew;
     private String gebiet;
     private Boolean PSielhaut;
     private Boolean PAlarmplan;
     private Boolean PNachprobe;
     private Boolean schlammprobe;
     private Boolean PFirmenprobe;
     private Serializable theGeom;
     private boolean enabled;
     private boolean deleted;
     private Set<AtlProbepkt> atlProbepkts = new HashSet<AtlProbepkt>(0);

    public AbstractAtlSielhaut() {
    }

    public AbstractAtlSielhaut(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAtlSielhaut(
    	String bezeichnung, String haltungsnr, String alarmplannr, String entgeb, Double rechtswert, Double hochwert, String lage, String bemerkungen, String twabfluss, String bsb, String ew, String gebiet, Boolean PSielhaut, Boolean PAlarmplan, Boolean PNachprobe, Boolean schlammprobe, Boolean PFirmenprobe, Serializable theGeom, boolean enabled, boolean deleted, Set<AtlProbepkt> atlProbepkts) {
        this.bezeichnung = bezeichnung;
        this.haltungsnr = haltungsnr;
        this.alarmplannr = alarmplannr;
        this.entgeb = entgeb;
        this.rechtswert = rechtswert;
        this.hochwert = hochwert;
        this.lage = lage;
        this.bemerkungen = bemerkungen;
        this.twabfluss = twabfluss;
        this.bsb = bsb;
        this.ew = ew;
        this.gebiet = gebiet;
        this.PSielhaut = PSielhaut;
        this.PAlarmplan = PAlarmplan;
        this.PNachprobe = PNachprobe;
        this.schlammprobe = schlammprobe;
        this.PFirmenprobe = PFirmenprobe;
        this.theGeom = theGeom;
        this.enabled = enabled;
        this.deleted = deleted;
        this.atlProbepkts = atlProbepkts;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getHaltungsnr() {
        return this.haltungsnr;
    }
    public void setHaltungsnr(String haltungsnr) {
        this.haltungsnr = haltungsnr;
    }

    public String getAlarmplannr() {
        return this.alarmplannr;
    }
    public void setAlarmplannr(String alarmplannr) {
        this.alarmplannr = alarmplannr;
    }

    public String getEntgeb() {
        return this.entgeb;
    }
    public void setEntgeb(String entgeb) {
        this.entgeb = entgeb;
    }

    public Double getRechtswert() {
        return this.rechtswert;
    }
    public void setRechtswert(Double rechtswert) {
        this.rechtswert = rechtswert;
    }

    public Double getHochwert() {
        return this.hochwert;
    }
    public void setHochwert(Double hochwert) {
        this.hochwert = hochwert;
    }

    public String getLage() {
        return this.lage;
    }
    public void setLage(String lage) {
        this.lage = lage;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getTwabfluss() {
        return this.twabfluss;
    }
    public void setTwabfluss(String twabfluss) {
        this.twabfluss = twabfluss;
    }

    public String getBsb() {
        return this.bsb;
    }
    public void setBsb(String bsb) {
        this.bsb = bsb;
    }

    public String getEw() {
        return this.ew;
    }
    public void setEw(String ew) {
        this.ew = ew;
    }

    public String getGebiet() {
        return this.gebiet;
    }
    public void setGebiet(String gebiet) {
        this.gebiet = gebiet;
    }

    public Boolean getPSielhaut() {
        return this.PSielhaut;
    }
    public void setPSielhaut(Boolean PSielhaut) {
        this.PSielhaut = PSielhaut;
    }

    public Boolean getPAlarmplan() {
        return this.PAlarmplan;
    }
    public void setPAlarmplan(Boolean PAlarmplan) {
        this.PAlarmplan = PAlarmplan;
    }

    public Boolean getPNachprobe() {
        return this.PNachprobe;
    }
    public void setPNachprobe(Boolean PNachprobe) {
        this.PNachprobe = PNachprobe;
    }

    public Boolean getSchlammprobe() {
        return this.schlammprobe;
    }
    public void setSchlammprobe(Boolean schlammprobe) {
        this.schlammprobe = schlammprobe;
    }

    public Boolean getPFirmenprobe() {
        return this.PFirmenprobe;
    }
    public void setPFirmenprobe(Boolean PFirmenprobe) {
        this.PFirmenprobe = PFirmenprobe;
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

    public Set<AtlProbepkt> getAtlProbepkts() {
        return this.atlProbepkts;
    }
    public void setAtlProbepkts(Set<AtlProbepkt> atlProbepkts) {
        this.atlProbepkts = atlProbepkts;
    }

}

