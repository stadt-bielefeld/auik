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

package de.bielefeld.umweltamt.aui.mappings.generated.basis;


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link BasisObjektchrono}.
 */
public abstract class AbstractBasisObjektchrono  implements java.io.Serializable {

     private Integer id;
     private BasisObjekt basisObjekt;
     private Date datum;
     private String sachverhalt;
     private Date wv;
     private String sachbearbeiter;
     private boolean enabled;
     private boolean deleted;

    public AbstractBasisObjektchrono() {
    }

    public AbstractBasisObjektchrono(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractBasisObjektchrono(
    	BasisObjekt basisObjekt, Date datum, String sachverhalt, Date wv, String sachbearbeiter, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.datum = datum;
        this.sachverhalt = sachverhalt;
        this.wv = wv;
        this.sachbearbeiter = sachbearbeiter;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public Date getDatum() {
        return this.datum;
    }
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getSachverhalt() {
        return this.sachverhalt;
    }
    public void setSachverhalt(String sachverhalt) {
        this.sachverhalt = sachverhalt;
    }

    public Date getWv() {
        return this.wv;
    }
    public void setWv(Date wv) {
        this.wv = wv;
    }

    public String getSachbearbeiter() {
        return this.sachbearbeiter;
    }
    public void setSachbearbeiter(String sachbearbeiter) {
        this.sachbearbeiter = sachbearbeiter;
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

