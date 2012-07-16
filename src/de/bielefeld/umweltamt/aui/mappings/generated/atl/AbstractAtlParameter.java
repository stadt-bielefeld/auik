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


import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link AtlParameter}.
 */
public abstract class AbstractAtlParameter  implements java.io.Serializable {

     private String ordnungsbegriff;
     private AtlParametergruppen atlParametergruppen;
     private String analyseverfahren;
     private String bezeichnung;
     private Integer wirdgemessenineinheit;
     private Double grenzwert;
     private String grenzwertname;
     private Double sielhautGw;
     private Double klaerschlammGw;
     private Double preisfueranalyse;
     private Boolean einzelnbeauftragbar;
     private String kennzeichnung;
     private String konservierung;
     private Integer reihenfolge;
     private boolean enabled;
     private boolean deleted;
     private int deaStoffeStoffNr;
     private Set<AtlAnalyseposition> atlAnalysepositions = new HashSet<AtlAnalyseposition>(0);

    public AbstractAtlParameter() {
    }

    public AbstractAtlParameter(
    	String ordnungsbegriff, boolean enabled, boolean deleted, int deaStoffeStoffNr) {
        this.ordnungsbegriff = ordnungsbegriff;
        this.enabled = enabled;
        this.deleted = deleted;
        this.deaStoffeStoffNr = deaStoffeStoffNr;
    }

    public AbstractAtlParameter(
    	String ordnungsbegriff, AtlParametergruppen atlParametergruppen, String analyseverfahren, String bezeichnung, Integer wirdgemessenineinheit, Double grenzwert, String grenzwertname, Double sielhautGw, Double klaerschlammGw, Double preisfueranalyse, Boolean einzelnbeauftragbar, String kennzeichnung, String konservierung, Integer reihenfolge, boolean enabled, boolean deleted, int deaStoffeStoffNr, Set<AtlAnalyseposition> atlAnalysepositions) {
        this.ordnungsbegriff = ordnungsbegriff;
        this.atlParametergruppen = atlParametergruppen;
        this.analyseverfahren = analyseverfahren;
        this.bezeichnung = bezeichnung;
        this.wirdgemessenineinheit = wirdgemessenineinheit;
        this.grenzwert = grenzwert;
        this.grenzwertname = grenzwertname;
        this.sielhautGw = sielhautGw;
        this.klaerschlammGw = klaerschlammGw;
        this.preisfueranalyse = preisfueranalyse;
        this.einzelnbeauftragbar = einzelnbeauftragbar;
        this.kennzeichnung = kennzeichnung;
        this.konservierung = konservierung;
        this.reihenfolge = reihenfolge;
        this.enabled = enabled;
        this.deleted = deleted;
        this.deaStoffeStoffNr = deaStoffeStoffNr;
        this.atlAnalysepositions = atlAnalysepositions;
    }

    public String getOrdnungsbegriff() {
        return this.ordnungsbegriff;
    }
    public void setOrdnungsbegriff(String ordnungsbegriff) {
        this.ordnungsbegriff = ordnungsbegriff;
    }

    public AtlParametergruppen getAtlParametergruppen() {
        return this.atlParametergruppen;
    }
    public void setAtlParametergruppen(AtlParametergruppen atlParametergruppen) {
        this.atlParametergruppen = atlParametergruppen;
    }

    public String getAnalyseverfahren() {
        return this.analyseverfahren;
    }
    public void setAnalyseverfahren(String analyseverfahren) {
        this.analyseverfahren = analyseverfahren;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Integer getWirdgemessenineinheit() {
        return this.wirdgemessenineinheit;
    }
    public void setWirdgemessenineinheit(Integer wirdgemessenineinheit) {
        this.wirdgemessenineinheit = wirdgemessenineinheit;
    }

    public Double getGrenzwert() {
        return this.grenzwert;
    }
    public void setGrenzwert(Double grenzwert) {
        this.grenzwert = grenzwert;
    }

    public String getGrenzwertname() {
        return this.grenzwertname;
    }
    public void setGrenzwertname(String grenzwertname) {
        this.grenzwertname = grenzwertname;
    }

    public Double getSielhautGw() {
        return this.sielhautGw;
    }
    public void setSielhautGw(Double sielhautGw) {
        this.sielhautGw = sielhautGw;
    }

    public Double getKlaerschlammGw() {
        return this.klaerschlammGw;
    }
    public void setKlaerschlammGw(Double klaerschlammGw) {
        this.klaerschlammGw = klaerschlammGw;
    }

    public Double getPreisfueranalyse() {
        return this.preisfueranalyse;
    }
    public void setPreisfueranalyse(Double preisfueranalyse) {
        this.preisfueranalyse = preisfueranalyse;
    }

    public Boolean getEinzelnbeauftragbar() {
        return this.einzelnbeauftragbar;
    }
    public void setEinzelnbeauftragbar(Boolean einzelnbeauftragbar) {
        this.einzelnbeauftragbar = einzelnbeauftragbar;
    }

    public String getKennzeichnung() {
        return this.kennzeichnung;
    }
    public void setKennzeichnung(String kennzeichnung) {
        this.kennzeichnung = kennzeichnung;
    }

    public String getKonservierung() {
        return this.konservierung;
    }
    public void setKonservierung(String konservierung) {
        this.konservierung = konservierung;
    }

    public Integer getReihenfolge() {
        return this.reihenfolge;
    }
    public void setReihenfolge(Integer reihenfolge) {
        this.reihenfolge = reihenfolge;
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

    public int getDeaStoffeStoffNr() {
        return this.deaStoffeStoffNr;
    }
    public void setDeaStoffeStoffNr(int deaStoffeStoffNr) {
        this.deaStoffeStoffNr = deaStoffeStoffNr;
    }

    public Set<AtlAnalyseposition> getAtlAnalysepositions() {
        return this.atlAnalysepositions;
    }
    public void setAtlAnalysepositions(Set<AtlAnalyseposition> atlAnalysepositions) {
        this.atlAnalysepositions = atlAnalysepositions;
    }

}

