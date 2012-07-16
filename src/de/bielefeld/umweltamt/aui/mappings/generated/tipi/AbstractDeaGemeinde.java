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

package de.bielefeld.umweltamt.aui.mappings.generated.tipi;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link DeaGemeinde}.
 */
public abstract class AbstractDeaGemeinde  implements java.io.Serializable {

     private DeaGemeindeId id;
     private Date inkaGueltigVon;
     private Date inkaGueltigBis;
     private short istAktuellTog;
     private Date erfassungsDatum;
     private Date aenderungsDatum;
     private Integer stuaNr;
     private Integer stuaVersion;
     private String name;
     private Integer gkkMinRechts2;
     private Integer gkkMinHoch2;
     private Integer gkkMaxRechts2;
     private Integer gkkMaxHoch2;
     private Integer gkkMinRechts3;
     private Integer gkkMinHoch3;
     private Integer gkkMaxRechts3;
     private Integer gkkMaxHoch3;
     private Integer n32Max;
     private Integer e32Max;
     private Integer n32Min;
     private Integer e32Min;
     private int zustandsNr;
     private Date gueltigVon;
     private Date igStichtag;
     private Date gueltigBis;
     private Date erstellDat;
     private Date aktualDat;
     private int versionsnr;
     private Date zeitstempel;

    public AbstractDeaGemeinde() {
    }

    public AbstractDeaGemeinde(
    	DeaGemeindeId id, Date inkaGueltigVon, short istAktuellTog, Date erfassungsDatum, int zustandsNr, Date gueltigVon, Date erstellDat, Date aktualDat, int versionsnr) {
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

    public AbstractDeaGemeinde(
    	DeaGemeindeId id, Date inkaGueltigVon, Date inkaGueltigBis, short istAktuellTog, Date erfassungsDatum, Date aenderungsDatum, Integer stuaNr, Integer stuaVersion, String name, Integer gkkMinRechts2, Integer gkkMinHoch2, Integer gkkMaxRechts2, Integer gkkMaxHoch2, Integer gkkMinRechts3, Integer gkkMinHoch3, Integer gkkMaxRechts3, Integer gkkMaxHoch3, Integer n32Max, Integer e32Max, Integer n32Min, Integer e32Min, int zustandsNr, Date gueltigVon, Date igStichtag, Date gueltigBis, Date erstellDat, Date aktualDat, int versionsnr, Date zeitstempel) {
        this.id = id;
        this.inkaGueltigVon = inkaGueltigVon;
        this.inkaGueltigBis = inkaGueltigBis;
        this.istAktuellTog = istAktuellTog;
        this.erfassungsDatum = erfassungsDatum;
        this.aenderungsDatum = aenderungsDatum;
        this.stuaNr = stuaNr;
        this.stuaVersion = stuaVersion;
        this.name = name;
        this.gkkMinRechts2 = gkkMinRechts2;
        this.gkkMinHoch2 = gkkMinHoch2;
        this.gkkMaxRechts2 = gkkMaxRechts2;
        this.gkkMaxHoch2 = gkkMaxHoch2;
        this.gkkMinRechts3 = gkkMinRechts3;
        this.gkkMinHoch3 = gkkMinHoch3;
        this.gkkMaxRechts3 = gkkMaxRechts3;
        this.gkkMaxHoch3 = gkkMaxHoch3;
        this.n32Max = n32Max;
        this.e32Max = e32Max;
        this.n32Min = n32Min;
        this.e32Min = e32Min;
        this.zustandsNr = zustandsNr;
        this.gueltigVon = gueltigVon;
        this.igStichtag = igStichtag;
        this.gueltigBis = gueltigBis;
        this.erstellDat = erstellDat;
        this.aktualDat = aktualDat;
        this.versionsnr = versionsnr;
        this.zeitstempel = zeitstempel;
    }

    public DeaGemeindeId getId() {
        return this.id;
    }
    public void setId(DeaGemeindeId id) {
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

    public Integer getStuaNr() {
        return this.stuaNr;
    }
    public void setStuaNr(Integer stuaNr) {
        this.stuaNr = stuaNr;
    }

    public Integer getStuaVersion() {
        return this.stuaVersion;
    }
    public void setStuaVersion(Integer stuaVersion) {
        this.stuaVersion = stuaVersion;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getGkkMinRechts2() {
        return this.gkkMinRechts2;
    }
    public void setGkkMinRechts2(Integer gkkMinRechts2) {
        this.gkkMinRechts2 = gkkMinRechts2;
    }

    public Integer getGkkMinHoch2() {
        return this.gkkMinHoch2;
    }
    public void setGkkMinHoch2(Integer gkkMinHoch2) {
        this.gkkMinHoch2 = gkkMinHoch2;
    }

    public Integer getGkkMaxRechts2() {
        return this.gkkMaxRechts2;
    }
    public void setGkkMaxRechts2(Integer gkkMaxRechts2) {
        this.gkkMaxRechts2 = gkkMaxRechts2;
    }

    public Integer getGkkMaxHoch2() {
        return this.gkkMaxHoch2;
    }
    public void setGkkMaxHoch2(Integer gkkMaxHoch2) {
        this.gkkMaxHoch2 = gkkMaxHoch2;
    }

    public Integer getGkkMinRechts3() {
        return this.gkkMinRechts3;
    }
    public void setGkkMinRechts3(Integer gkkMinRechts3) {
        this.gkkMinRechts3 = gkkMinRechts3;
    }

    public Integer getGkkMinHoch3() {
        return this.gkkMinHoch3;
    }
    public void setGkkMinHoch3(Integer gkkMinHoch3) {
        this.gkkMinHoch3 = gkkMinHoch3;
    }

    public Integer getGkkMaxRechts3() {
        return this.gkkMaxRechts3;
    }
    public void setGkkMaxRechts3(Integer gkkMaxRechts3) {
        this.gkkMaxRechts3 = gkkMaxRechts3;
    }

    public Integer getGkkMaxHoch3() {
        return this.gkkMaxHoch3;
    }
    public void setGkkMaxHoch3(Integer gkkMaxHoch3) {
        this.gkkMaxHoch3 = gkkMaxHoch3;
    }

    public Integer getN32Max() {
        return this.n32Max;
    }
    public void setN32Max(Integer n32Max) {
        this.n32Max = n32Max;
    }

    public Integer getE32Max() {
        return this.e32Max;
    }
    public void setE32Max(Integer e32Max) {
        this.e32Max = e32Max;
    }

    public Integer getN32Min() {
        return this.n32Min;
    }
    public void setN32Min(Integer n32Min) {
        this.n32Min = n32Min;
    }

    public Integer getE32Min() {
        return this.e32Min;
    }
    public void setE32Min(Integer e32Min) {
        this.e32Min = e32Min;
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

