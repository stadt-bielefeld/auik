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



/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link AtlAnalyseposition}.
 */
public abstract class AbstractAtlAnalyseposition  implements java.io.Serializable {

     private Integer id;
     private AtlParameter atlParameter;
     private AtlProbenahmen atlProbenahmen;
     private AtlEinheiten atlEinheiten;
     private String grkl;
     private float wert;
     private String analyseVon;
     private String bericht;
     private Double normwert;
     private boolean enabled;
     private boolean deleted;

    public AbstractAtlAnalyseposition() {
    }

    public AbstractAtlAnalyseposition(
    	AtlParameter atlParameter, AtlProbenahmen atlProbenahmen, float wert, boolean enabled, boolean deleted) {
        this.atlParameter = atlParameter;
        this.atlProbenahmen = atlProbenahmen;
        this.wert = wert;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAtlAnalyseposition(
    	AtlParameter atlParameter, AtlProbenahmen atlProbenahmen, AtlEinheiten atlEinheiten, String grkl, float wert, String analyseVon, String bericht, Double normwert, boolean enabled, boolean deleted) {
        this.atlParameter = atlParameter;
        this.atlProbenahmen = atlProbenahmen;
        this.atlEinheiten = atlEinheiten;
        this.grkl = grkl;
        this.wert = wert;
        this.analyseVon = analyseVon;
        this.bericht = bericht;
        this.normwert = normwert;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public AtlParameter getAtlParameter() {
        return this.atlParameter;
    }
    public void setAtlParameter(AtlParameter atlParameter) {
        this.atlParameter = atlParameter;
    }

    public AtlProbenahmen getAtlProbenahmen() {
        return this.atlProbenahmen;
    }
    public void setAtlProbenahmen(AtlProbenahmen atlProbenahmen) {
        this.atlProbenahmen = atlProbenahmen;
    }

    public AtlEinheiten getAtlEinheiten() {
        return this.atlEinheiten;
    }
    public void setAtlEinheiten(AtlEinheiten atlEinheiten) {
        this.atlEinheiten = atlEinheiten;
    }

    public String getGrkl() {
        return this.grkl;
    }
    public void setGrkl(String grkl) {
        this.grkl = grkl;
    }

    public float getWert() {
        return this.wert;
    }
    public void setWert(float wert) {
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

