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

package de.bielefeld.umweltamt.aui.mappings.generated.tipi;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link DeaAnalysemethode}.
 */
public abstract class AbstractDeaAnalysemethode  implements java.io.Serializable {

     private DeaAnalysemethodeId id;
     private Date inkaGueltigVon;
     private Date inkaGueltigBis;
     private short istAktuellTog;
     private Date erfassungsDatum;
     private Date aenderungsDatum;
     private String methodenNr;
     private String bezeichnungMethode;
     private Integer phaseOpt;
     private String datumNorm;
     private Integer ergebnistypOpt;
     private String bezeichnungVariante;
     private String beschreibungAnalysemet;
     private int zustandsNr;
     private Date gueltigVon;
     private Date igStichtag;
     private Date gueltigBis;
     private Date erstellDat;
     private Date aktualDat;
     private int versionsnr;
     private Date zeitstempel;

    public AbstractDeaAnalysemethode() {
    }

    public AbstractDeaAnalysemethode(
    	DeaAnalysemethodeId id, Date inkaGueltigVon, short istAktuellTog, Date erfassungsDatum, int zustandsNr, Date gueltigVon, Date erstellDat, Date aktualDat, int versionsnr) {
        this.id = id;
        this.inkaGueltigVon = inkaGueltigVon;
        this.istAktuellTog = istAktuellTog;
        this.erfassungsDatum = erfassungsDatum;
        this.zustandsNr = zustandsNr;
        this.gueltigVon = gueltigVon;
        this.erstellDat = erstellDat;
        this.aktualDat = aktualDat;
        this.versionsnr = versionsnr;
    }

    public AbstractDeaAnalysemethode(
    	DeaAnalysemethodeId id, Date inkaGueltigVon, Date inkaGueltigBis, short istAktuellTog, Date erfassungsDatum, Date aenderungsDatum, String methodenNr, String bezeichnungMethode, Integer phaseOpt, String datumNorm, Integer ergebnistypOpt, String bezeichnungVariante, String beschreibungAnalysemet, int zustandsNr, Date gueltigVon, Date igStichtag, Date gueltigBis, Date erstellDat, Date aktualDat, int versionsnr, Date zeitstempel) {
        this.id = id;
        this.inkaGueltigVon = inkaGueltigVon;
        this.inkaGueltigBis = inkaGueltigBis;
        this.istAktuellTog = istAktuellTog;
        this.erfassungsDatum = erfassungsDatum;
        this.aenderungsDatum = aenderungsDatum;
        this.methodenNr = methodenNr;
        this.bezeichnungMethode = bezeichnungMethode;
        this.phaseOpt = phaseOpt;
        this.datumNorm = datumNorm;
        this.ergebnistypOpt = ergebnistypOpt;
        this.bezeichnungVariante = bezeichnungVariante;
        this.beschreibungAnalysemet = beschreibungAnalysemet;
        this.zustandsNr = zustandsNr;
        this.gueltigVon = gueltigVon;
        this.igStichtag = igStichtag;
        this.gueltigBis = gueltigBis;
        this.erstellDat = erstellDat;
        this.aktualDat = aktualDat;
        this.versionsnr = versionsnr;
        this.zeitstempel = zeitstempel;
    }

    public DeaAnalysemethodeId getId() {
        return this.id;
    }
    public void setId(DeaAnalysemethodeId id) {
        this.id = id;
    }

    public Date getInkaGueltigVon() {
        return this.inkaGueltigVon;
    }
    public void setInkaGueltigVon(Date inkaGueltigVon) {
        this.inkaGueltigVon = inkaGueltigVon;
    }

    public Date getInkaGueltigBis() {
        return this.inkaGueltigBis;
    }
    public void setInkaGueltigBis(Date inkaGueltigBis) {
        this.inkaGueltigBis = inkaGueltigBis;
    }

    public short getIstAktuellTog() {
        return this.istAktuellTog;
    }
    public void setIstAktuellTog(short istAktuellTog) {
        this.istAktuellTog = istAktuellTog;
    }

    public Date getErfassungsDatum() {
        return this.erfassungsDatum;
    }
    public void setErfassungsDatum(Date erfassungsDatum) {
        this.erfassungsDatum = erfassungsDatum;
    }

    public Date getAenderungsDatum() {
        return this.aenderungsDatum;
    }
    public void setAenderungsDatum(Date aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }

    public String getMethodenNr() {
        return this.methodenNr;
    }
    public void setMethodenNr(String methodenNr) {
        this.methodenNr = methodenNr;
    }

    public String getBezeichnungMethode() {
        return this.bezeichnungMethode;
    }
    public void setBezeichnungMethode(String bezeichnungMethode) {
        this.bezeichnungMethode = bezeichnungMethode;
    }

    public Integer getPhaseOpt() {
        return this.phaseOpt;
    }
    public void setPhaseOpt(Integer phaseOpt) {
        this.phaseOpt = phaseOpt;
    }

    public String getDatumNorm() {
        return this.datumNorm;
    }
    public void setDatumNorm(String datumNorm) {
        this.datumNorm = datumNorm;
    }

    public Integer getErgebnistypOpt() {
        return this.ergebnistypOpt;
    }
    public void setErgebnistypOpt(Integer ergebnistypOpt) {
        this.ergebnistypOpt = ergebnistypOpt;
    }

    public String getBezeichnungVariante() {
        return this.bezeichnungVariante;
    }
    public void setBezeichnungVariante(String bezeichnungVariante) {
        this.bezeichnungVariante = bezeichnungVariante;
    }

    public String getBeschreibungAnalysemet() {
        return this.beschreibungAnalysemet;
    }
    public void setBeschreibungAnalysemet(String beschreibungAnalysemet) {
        this.beschreibungAnalysemet = beschreibungAnalysemet;
    }

    public int getZustandsNr() {
        return this.zustandsNr;
    }
    public void setZustandsNr(int zustandsNr) {
        this.zustandsNr = zustandsNr;
    }

    public Date getGueltigVon() {
        return this.gueltigVon;
    }
    public void setGueltigVon(Date gueltigVon) {
        this.gueltigVon = gueltigVon;
    }

    public Date getIgStichtag() {
        return this.igStichtag;
    }
    public void setIgStichtag(Date igStichtag) {
        this.igStichtag = igStichtag;
    }

    public Date getGueltigBis() {
        return this.gueltigBis;
    }
    public void setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    public Date getErstellDat() {
        return this.erstellDat;
    }
    public void setErstellDat(Date erstellDat) {
        this.erstellDat = erstellDat;
    }

    public Date getAktualDat() {
        return this.aktualDat;
    }
    public void setAktualDat(Date aktualDat) {
        this.aktualDat = aktualDat;
    }

    public int getVersionsnr() {
        return this.versionsnr;
    }
    public void setVersionsnr(int versionsnr) {
        this.versionsnr = versionsnr;
    }

    public Date getZeitstempel() {
        return this.zeitstempel;
    }
    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

}

