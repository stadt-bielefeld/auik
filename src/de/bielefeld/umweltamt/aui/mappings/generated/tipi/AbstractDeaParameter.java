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
 * {@link DeaParameter}.
 */
public abstract class AbstractDeaParameter  implements java.io.Serializable {

     private DeaParameterId id;
     private Date inkaGueltigVon;
     private Date inkaGueltigBis;
     private short istAktuellTog;
     private Date erfassungsDatum;
     private Date aenderungsDatum;
     private Integer stoffNr;
     private Integer stoffVersion;
     private String regelwerkId;
     private String gruppeDevId;
     private Character variantenId;
     private Integer analyseVersion;
     private Integer masseinheitenNr;
     private Integer masseinheitenVersion;
     private int trennNrOpt;
     private Short abgaberelevantTog;
     private String berechnungPara;
     private Short frachtparTog;
     private Short umrechTog;
     private Integer stoffNr1;
     private Integer trennNr1;
     private Integer masseinheitenNr1;
     private String analyseverfPara;
     private String analyseverf1Para;
     private Short parBedingungTog;
     private Short parBereichTog;
     private Date veranDatAb;
     private Date veranDatBis;
     private Character parVerwendung;
     private int zustandsNr;
     private Date gueltigVon;
     private Date igStichtag;
     private Date gueltigBis;
     private Date erstellDat;
     private Date aktualDat;
     private int versionsnr;
     private Date zeitstempel;

    public AbstractDeaParameter() {
    }

    public AbstractDeaParameter(
    	DeaParameterId id, Date inkaGueltigVon, short istAktuellTog, Date erfassungsDatum, int trennNrOpt, int zustandsNr, Date gueltigVon, Date erstellDat, Date aktualDat, int versionsnr) {
        this.id = id;
        this.inkaGueltigVon = inkaGueltigVon;
        this.istAktuellTog = istAktuellTog;
        this.erfassungsDatum = erfassungsDatum;
        this.trennNrOpt = trennNrOpt;
        this.zustandsNr = zustandsNr;
        this.gueltigVon = gueltigVon;
        this.erstellDat = erstellDat;
        this.aktualDat = aktualDat;
        this.versionsnr = versionsnr;
    }

    public AbstractDeaParameter(
    	DeaParameterId id, Date inkaGueltigVon, Date inkaGueltigBis, short istAktuellTog, Date erfassungsDatum, Date aenderungsDatum, Integer stoffNr, Integer stoffVersion, String regelwerkId, String gruppeDevId, Character variantenId, Integer analyseVersion, Integer masseinheitenNr, Integer masseinheitenVersion, int trennNrOpt, Short abgaberelevantTog, String berechnungPara, Short frachtparTog, Short umrechTog, Integer stoffNr1, Integer trennNr1, Integer masseinheitenNr1, String analyseverfPara, String analyseverf1Para, Short parBedingungTog, Short parBereichTog, Date veranDatAb, Date veranDatBis, Character parVerwendung, int zustandsNr, Date gueltigVon, Date igStichtag, Date gueltigBis, Date erstellDat, Date aktualDat, int versionsnr, Date zeitstempel) {
        this.id = id;
        this.inkaGueltigVon = inkaGueltigVon;
        this.inkaGueltigBis = inkaGueltigBis;
        this.istAktuellTog = istAktuellTog;
        this.erfassungsDatum = erfassungsDatum;
        this.aenderungsDatum = aenderungsDatum;
        this.stoffNr = stoffNr;
        this.stoffVersion = stoffVersion;
        this.regelwerkId = regelwerkId;
        this.gruppeDevId = gruppeDevId;
        this.variantenId = variantenId;
        this.analyseVersion = analyseVersion;
        this.masseinheitenNr = masseinheitenNr;
        this.masseinheitenVersion = masseinheitenVersion;
        this.trennNrOpt = trennNrOpt;
        this.abgaberelevantTog = abgaberelevantTog;
        this.berechnungPara = berechnungPara;
        this.frachtparTog = frachtparTog;
        this.umrechTog = umrechTog;
        this.stoffNr1 = stoffNr1;
        this.trennNr1 = trennNr1;
        this.masseinheitenNr1 = masseinheitenNr1;
        this.analyseverfPara = analyseverfPara;
        this.analyseverf1Para = analyseverf1Para;
        this.parBedingungTog = parBedingungTog;
        this.parBereichTog = parBereichTog;
        this.veranDatAb = veranDatAb;
        this.veranDatBis = veranDatBis;
        this.parVerwendung = parVerwendung;
        this.zustandsNr = zustandsNr;
        this.gueltigVon = gueltigVon;
        this.igStichtag = igStichtag;
        this.gueltigBis = gueltigBis;
        this.erstellDat = erstellDat;
        this.aktualDat = aktualDat;
        this.versionsnr = versionsnr;
        this.zeitstempel = zeitstempel;
    }

    public DeaParameterId getId() {
        return this.id;
    }
    public void setId(DeaParameterId id) {
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

    public Integer getStoffNr() {
        return this.stoffNr;
    }
    public void setStoffNr(Integer stoffNr) {
        this.stoffNr = stoffNr;
    }

    public Integer getStoffVersion() {
        return this.stoffVersion;
    }
    public void setStoffVersion(Integer stoffVersion) {
        this.stoffVersion = stoffVersion;
    }

    public String getRegelwerkId() {
        return this.regelwerkId;
    }
    public void setRegelwerkId(String regelwerkId) {
        this.regelwerkId = regelwerkId;
    }

    public String getGruppeDevId() {
        return this.gruppeDevId;
    }
    public void setGruppeDevId(String gruppeDevId) {
        this.gruppeDevId = gruppeDevId;
    }

    public Character getVariantenId() {
        return this.variantenId;
    }
    public void setVariantenId(Character variantenId) {
        this.variantenId = variantenId;
    }

    public Integer getAnalyseVersion() {
        return this.analyseVersion;
    }
    public void setAnalyseVersion(Integer analyseVersion) {
        this.analyseVersion = analyseVersion;
    }

    public Integer getMasseinheitenNr() {
        return this.masseinheitenNr;
    }
    public void setMasseinheitenNr(Integer masseinheitenNr) {
        this.masseinheitenNr = masseinheitenNr;
    }

    public Integer getMasseinheitenVersion() {
        return this.masseinheitenVersion;
    }
    public void setMasseinheitenVersion(Integer masseinheitenVersion) {
        this.masseinheitenVersion = masseinheitenVersion;
    }

    public int getTrennNrOpt() {
        return this.trennNrOpt;
    }
    public void setTrennNrOpt(int trennNrOpt) {
        this.trennNrOpt = trennNrOpt;
    }

    public Short getAbgaberelevantTog() {
        return this.abgaberelevantTog;
    }
    public void setAbgaberelevantTog(Short abgaberelevantTog) {
        this.abgaberelevantTog = abgaberelevantTog;
    }

    public String getBerechnungPara() {
        return this.berechnungPara;
    }
    public void setBerechnungPara(String berechnungPara) {
        this.berechnungPara = berechnungPara;
    }

    public Short getFrachtparTog() {
        return this.frachtparTog;
    }
    public void setFrachtparTog(Short frachtparTog) {
        this.frachtparTog = frachtparTog;
    }

    public Short getUmrechTog() {
        return this.umrechTog;
    }
    public void setUmrechTog(Short umrechTog) {
        this.umrechTog = umrechTog;
    }

    public Integer getStoffNr1() {
        return this.stoffNr1;
    }
    public void setStoffNr1(Integer stoffNr1) {
        this.stoffNr1 = stoffNr1;
    }

    public Integer getTrennNr1() {
        return this.trennNr1;
    }
    public void setTrennNr1(Integer trennNr1) {
        this.trennNr1 = trennNr1;
    }

    public Integer getMasseinheitenNr1() {
        return this.masseinheitenNr1;
    }
    public void setMasseinheitenNr1(Integer masseinheitenNr1) {
        this.masseinheitenNr1 = masseinheitenNr1;
    }

    public String getAnalyseverfPara() {
        return this.analyseverfPara;
    }
    public void setAnalyseverfPara(String analyseverfPara) {
        this.analyseverfPara = analyseverfPara;
    }

    public String getAnalyseverf1Para() {
        return this.analyseverf1Para;
    }
    public void setAnalyseverf1Para(String analyseverf1Para) {
        this.analyseverf1Para = analyseverf1Para;
    }

    public Short getParBedingungTog() {
        return this.parBedingungTog;
    }
    public void setParBedingungTog(Short parBedingungTog) {
        this.parBedingungTog = parBedingungTog;
    }

    public Short getParBereichTog() {
        return this.parBereichTog;
    }
    public void setParBereichTog(Short parBereichTog) {
        this.parBereichTog = parBereichTog;
    }

    public Date getVeranDatAb() {
        return this.veranDatAb;
    }
    public void setVeranDatAb(Date veranDatAb) {
        this.veranDatAb = veranDatAb;
    }

    public Date getVeranDatBis() {
        return this.veranDatBis;
    }
    public void setVeranDatBis(Date veranDatBis) {
        this.veranDatBis = veranDatBis;
    }

    public Character getParVerwendung() {
        return this.parVerwendung;
    }
    public void setParVerwendung(Character parVerwendung) {
        this.parVerwendung = parVerwendung;
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

