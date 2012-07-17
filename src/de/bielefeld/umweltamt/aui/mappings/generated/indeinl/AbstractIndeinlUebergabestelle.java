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

package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;


import de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjekt;
import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link IndeinlUebergabestelle}.
 */
public abstract class AbstractIndeinlUebergabestelle  implements java.io.Serializable {

     private int objektid;
     private AtlKlaeranlagen atlKlaeranlagen;
     private BasisObjekt basisObjekt;
     private String bemerkungen;
     private Date aenderungsDatum;
     private Date erfassungsDatum;
     private Integer kanalart;
     private Integer rechtswert;
     private Integer hochwert;
     private boolean enabled;
     private boolean deleted;

    public AbstractIndeinlUebergabestelle() {
    }

    public AbstractIndeinlUebergabestelle(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractIndeinlUebergabestelle(
    	AtlKlaeranlagen atlKlaeranlagen, BasisObjekt basisObjekt, String bemerkungen, Date aenderungsDatum, Date erfassungsDatum, Integer kanalart, Integer rechtswert, Integer hochwert, boolean enabled, boolean deleted) {
        this.atlKlaeranlagen = atlKlaeranlagen;
        this.basisObjekt = basisObjekt;
        this.bemerkungen = bemerkungen;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.kanalart = kanalart;
        this.rechtswert = rechtswert;
        this.hochwert = hochwert;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public int getObjektid() {
        return this.objektid;
    }
    public void setObjektid(int objektid) {
        this.objektid = objektid;
    }

    public AtlKlaeranlagen getAtlKlaeranlagen() {
        return this.atlKlaeranlagen;
    }
    public void setAtlKlaeranlagen(AtlKlaeranlagen atlKlaeranlagen) {
        this.atlKlaeranlagen = atlKlaeranlagen;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public Date getAenderungsDatum() {
        return this.aenderungsDatum;
    }
    public void setAenderungsDatum(Date aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }

    public Date getErfassungsDatum() {
        return this.erfassungsDatum;
    }
    public void setErfassungsDatum(Date erfassungsDatum) {
        this.erfassungsDatum = erfassungsDatum;
    }

    public Integer getKanalart() {
        return this.kanalart;
    }
    public void setKanalart(Integer kanalart) {
        this.kanalart = kanalart;
    }

    public Integer getRechtswert() {
        return this.rechtswert;
    }
    public void setRechtswert(Integer rechtswert) {
        this.rechtswert = rechtswert;
    }

    public Integer getHochwert() {
        return this.hochwert;
    }
    public void setHochwert(Integer hochwert) {
        this.hochwert = hochwert;
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

