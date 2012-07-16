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


import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.IndeinlUebergabestelle;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link AtlKlaeranlagen}.
 */
public abstract class AbstractAtlKlaeranlagen  implements java.io.Serializable {

     private int id;
     private String anlage;
     private int deaKlaeranlageKlaeranlageNr;
     private Set<IndeinlUebergabestelle> indeinlUebergabestelles = new HashSet<IndeinlUebergabestelle>(0);
     private Set<AtlProbepkt> atlProbepkts = new HashSet<AtlProbepkt>(0);

    public AbstractAtlKlaeranlagen() {
    }

    public AbstractAtlKlaeranlagen(
    	int id, String anlage, int deaKlaeranlageKlaeranlageNr) {
        this.id = id;
        this.anlage = anlage;
        this.deaKlaeranlageKlaeranlageNr = deaKlaeranlageKlaeranlageNr;
    }

    public AbstractAtlKlaeranlagen(
    	int id, String anlage, int deaKlaeranlageKlaeranlageNr, Set<IndeinlUebergabestelle> indeinlUebergabestelles, Set<AtlProbepkt> atlProbepkts) {
        this.id = id;
        this.anlage = anlage;
        this.deaKlaeranlageKlaeranlageNr = deaKlaeranlageKlaeranlageNr;
        this.indeinlUebergabestelles = indeinlUebergabestelles;
        this.atlProbepkts = atlProbepkts;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAnlage() {
        return this.anlage;
    }
    public void setAnlage(String anlage) {
        this.anlage = anlage;
    }

    public int getDeaKlaeranlageKlaeranlageNr() {
        return this.deaKlaeranlageKlaeranlageNr;
    }
    public void setDeaKlaeranlageKlaeranlageNr(int deaKlaeranlageKlaeranlageNr) {
        this.deaKlaeranlageKlaeranlageNr = deaKlaeranlageKlaeranlageNr;
    }

    public Set<IndeinlUebergabestelle> getIndeinlUebergabestelles() {
        return this.indeinlUebergabestelles;
    }
    public void setIndeinlUebergabestelles(Set<IndeinlUebergabestelle> indeinlUebergabestelles) {
        this.indeinlUebergabestelles = indeinlUebergabestelles;
    }

    public Set<AtlProbepkt> getAtlProbepkts() {
        return this.atlProbepkts;
    }
    public void setAtlProbepkts(Set<AtlProbepkt> atlProbepkts) {
        this.atlProbepkts = atlProbepkts;
    }

}

