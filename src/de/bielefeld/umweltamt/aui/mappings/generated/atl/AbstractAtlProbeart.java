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
 * {@link AtlProbeart}.
 */
public abstract class AbstractAtlProbeart  implements java.io.Serializable {

     private int id;
     private String art;
     private Set<AtlProbepkt> atlProbepkts = new HashSet<AtlProbepkt>(0);

    public AbstractAtlProbeart() {
    }

    public AbstractAtlProbeart(
    	int id, String art) {
        this.id = id;
        this.art = art;
    }

    public AbstractAtlProbeart(
    	int id, String art, Set<AtlProbepkt> atlProbepkts) {
        this.id = id;
        this.art = art;
        this.atlProbepkts = atlProbepkts;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getArt() {
        return this.art;
    }
    public void setArt(String art) {
        this.art = art;
    }

    public Set<AtlProbepkt> getAtlProbepkts() {
        return this.atlProbepkts;
    }
    public void setAtlProbepkts(Set<AtlProbepkt> atlProbepkts) {
        this.atlProbepkts = atlProbepkts;
    }

}

