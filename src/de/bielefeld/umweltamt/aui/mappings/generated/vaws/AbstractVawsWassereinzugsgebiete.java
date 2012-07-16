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

package de.bielefeld.umweltamt.aui.mappings.generated.vaws;


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisStandort;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link VawsWassereinzugsgebiete}.
 */
public abstract class AbstractVawsWassereinzugsgebiete  implements java.io.Serializable {

     private int id;
     private String ezgbname;
     private Set<BasisStandort> basisStandorts = new HashSet<BasisStandort>(0);

    public AbstractVawsWassereinzugsgebiete() {
    }

    public AbstractVawsWassereinzugsgebiete(
    	int id) {
        this.id = id;
    }

    public AbstractVawsWassereinzugsgebiete(
    	int id, String ezgbname, Set<BasisStandort> basisStandorts) {
        this.id = id;
        this.ezgbname = ezgbname;
        this.basisStandorts = basisStandorts;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEzgbname() {
        return this.ezgbname;
    }
    public void setEzgbname(String ezgbname) {
        this.ezgbname = ezgbname;
    }

    public Set<BasisStandort> getBasisStandorts() {
        return this.basisStandorts;
    }
    public void setBasisStandorts(Set<BasisStandort> basisStandorts) {
        this.basisStandorts = basisStandorts;
    }

}

