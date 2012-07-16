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

package de.bielefeld.umweltamt.aui.mappings.generated.atl;



/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link ViewAtlAnalysepositionAll}.
 */
public abstract class AbstractViewAtlAnalysepositionAll  implements java.io.Serializable {

     private Integer id;
     private String grkl;
     private Float wert;
     private String analyseVon;
     private String bericht;
     private Double normwert;
     private Integer einheitenId;
     private String parameterId;
     private Integer probenahmeId;
     private Boolean enabled;
     private Boolean deleted;

    public AbstractViewAtlAnalysepositionAll() {
    }

    public AbstractViewAtlAnalysepositionAll(
    	Integer id) {
        this.id = id;
    }

    public AbstractViewAtlAnalysepositionAll(
    	Integer id, String grkl, Float wert, String analyseVon, String bericht, Double normwert, Integer einheitenId, String parameterId, Integer probenahmeId, Boolean enabled, Boolean deleted) {
        this.id = id;
        this.grkl = grkl;
        this.wert = wert;
        this.analyseVon = analyseVon;
        this.bericht = bericht;
        this.normwert = normwert;
        this.einheitenId = einheitenId;
        this.parameterId = parameterId;
        this.probenahmeId = probenahmeId;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrkl() {
        return this.grkl;
    }
    public void setGrkl(String grkl) {
        this.grkl = grkl;
    }

    public Float getWert() {
        return this.wert;
    }
    public void setWert(Float wert) {
        this.wert = wert;
    }

    public String getAnalyseVon() {
        return this.analyseVon;
    }
    public void setAnalyseVon(String analyseVon) {
        this.analyseVon = analyseVon;
    }

    public String getBericht() {
        return this.bericht;
    }
    public void setBericht(String bericht) {
        this.bericht = bericht;
    }

    public Double getNormwert() {
        return this.normwert;
    }
    public void setNormwert(Double normwert) {
        this.normwert = normwert;
    }

    public Integer getEinheitenId() {
        return this.einheitenId;
    }
    public void setEinheitenId(Integer einheitenId) {
        this.einheitenId = einheitenId;
    }

    public String getParameterId() {
        return this.parameterId;
    }
    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public Integer getProbenahmeId() {
        return this.probenahmeId;
    }
    public void setProbenahmeId(Integer probenahmeId) {
        this.probenahmeId = probenahmeId;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}

