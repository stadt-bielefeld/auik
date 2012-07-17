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


import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link AtlEinheiten}.
 */
public abstract class AbstractAtlEinheiten  implements java.io.Serializable {

     private int id;
     private String bezeichnung;
     private boolean enabled;
     private boolean deleted;
     private int deaEinheitenMasseinheitenNr;
     private Set<AtlAnalyseposition> atlAnalysepositions = new HashSet<AtlAnalyseposition>(0);

    public AbstractAtlEinheiten() {
    }

    public AbstractAtlEinheiten(
    	int id, boolean enabled, boolean deleted, int deaEinheitenMasseinheitenNr) {
        this.id = id;
        this.enabled = enabled;
        this.deleted = deleted;
        this.deaEinheitenMasseinheitenNr = deaEinheitenMasseinheitenNr;
    }

    public AbstractAtlEinheiten(
    	int id, String bezeichnung, boolean enabled, boolean deleted, int deaEinheitenMasseinheitenNr, Set<AtlAnalyseposition> atlAnalysepositions) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.enabled = enabled;
        this.deleted = deleted;
        this.deaEinheitenMasseinheitenNr = deaEinheitenMasseinheitenNr;
        this.atlAnalysepositions = atlAnalysepositions;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
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

    public int getDeaEinheitenMasseinheitenNr() {
        return this.deaEinheitenMasseinheitenNr;
    }
    public void setDeaEinheitenMasseinheitenNr(int deaEinheitenMasseinheitenNr) {
        this.deaEinheitenMasseinheitenNr = deaEinheitenMasseinheitenNr;
    }

    public Set<AtlAnalyseposition> getAtlAnalysepositions() {
        return this.atlAnalysepositions;
    }
    public void setAtlAnalysepositions(Set<AtlAnalyseposition> atlAnalysepositions) {
        this.atlAnalysepositions = atlAnalysepositions;
    }

}

