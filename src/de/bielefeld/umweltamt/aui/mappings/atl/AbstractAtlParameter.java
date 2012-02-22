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

package de.bielefeld.umweltamt.aui.mappings.atl;

import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;

public class AbstractAtlParameter extends
    AbstractVirtuallyDeletableDatabaseTable implements java.io.Serializable {

    private static final long serialVersionUID = 2105646109272626998L;
    private String ordnungsbegriff;
    private String analyseverfahren;
    private String bezeichnung;
    private Integer wirdgemessenineinheit;
    private Double grenzwert;
    private String grenzwertname;
    private Double sielhautGw;
    private Double klaerschlammGw;
    private Double preisfueranalyse;
    private Boolean einzelnBeauftragbar;
    private AtlParameterGruppen atlParameterGruppe;
    private String kennzeichnung;
    private String konservierung;
    private Integer reihenfolge;

    public AbstractAtlParameter() {
    }

    public AbstractAtlParameter(String ordnungsbegriff) {
        this.ordnungsbegriff = ordnungsbegriff;
    }

    public AbstractAtlParameter(String ordnungsbegriff,
            String analyseverfahren, String bezeichnung,
            Integer wirdgemessenineinheit, Double grenzwert,
            String grenzwertname, Double sielhautGw, Double klaerschlammGw,
            Double preisfueranalyse, Boolean einzelnBeauftragbar,
            String kennzeichnung, String konservierung, Integer reihenfolge,
            AtlParameterGruppen atlParameterGruppe) {
        this.ordnungsbegriff = ordnungsbegriff;
        this.analyseverfahren = analyseverfahren;
        this.bezeichnung = bezeichnung;
        this.wirdgemessenineinheit = wirdgemessenineinheit;
        this.grenzwert = grenzwert;
        this.grenzwertname = grenzwertname;
        this.sielhautGw = sielhautGw;
        this.klaerschlammGw = klaerschlammGw;
        this.preisfueranalyse = preisfueranalyse;
        this.einzelnBeauftragbar = einzelnBeauftragbar;
        this.atlParameterGruppe = atlParameterGruppe;
        this.kennzeichnung = kennzeichnung;
        this.konservierung = konservierung;
        this.reihenfolge = reihenfolge;
    }

    public String getOrdnungsbegriff() {
        return this.ordnungsbegriff;
    }

    public void setOrdnungsbegriff(String ordnungsbegriff) {
        this.ordnungsbegriff = ordnungsbegriff;
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

    public Boolean getEinzelnBeauftragbar() {
        return this.einzelnBeauftragbar;
    }

    public void setEinzelnBeauftragbar(Boolean einzelnBeauftragbar) {
        this.einzelnBeauftragbar = einzelnBeauftragbar;
    }

    public AtlParameterGruppen getAtlParameterGruppe() {
        return this.atlParameterGruppe;
    }

    public void setAtlParameterGruppe(AtlParameterGruppen atlParameterGruppe) {
        this.atlParameterGruppe = atlParameterGruppe;
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
}
