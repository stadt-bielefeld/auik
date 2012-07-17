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


import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link Anh49Ortstermine}.
 */
public abstract class AbstractAnh49Ortstermine  implements java.io.Serializable {

     private Integer id;
     private Anh49Fachdaten anh49Fachdaten;
     private Date datum;
     private String sachbearbeiterIn;
     private String bemerkungen;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnh49Ortstermine() {
    }

    public AbstractAnh49Ortstermine(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnh49Ortstermine(
    	Anh49Fachdaten anh49Fachdaten, Date datum, String sachbearbeiterIn, String bemerkungen, boolean enabled, boolean deleted) {
        this.anh49Fachdaten = anh49Fachdaten;
        this.datum = datum;
        this.sachbearbeiterIn = sachbearbeiterIn;
        this.bemerkungen = bemerkungen;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Anh49Fachdaten getAnh49Fachdaten() {
        return this.anh49Fachdaten;
    }
    public void setAnh49Fachdaten(Anh49Fachdaten anh49Fachdaten) {
        this.anh49Fachdaten = anh49Fachdaten;
    }

    public Date getDatum() {
        return this.datum;
    }
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getSachbearbeiterIn() {
        return this.sachbearbeiterIn;
    }
    public void setSachbearbeiterIn(String sachbearbeiterIn) {
        this.sachbearbeiterIn = sachbearbeiterIn;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
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

