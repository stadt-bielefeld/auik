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


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisBetreiber;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link VawsWirtschaftszweige}.
 */
public abstract class AbstractVawsWirtschaftszweige  implements java.io.Serializable {

     private int id;
     private String wirtschaftszweig;
     private Set<BasisBetreiber> basisBetreibers = new HashSet<BasisBetreiber>(0);

    public AbstractVawsWirtschaftszweige() {
    }

    public AbstractVawsWirtschaftszweige(
    	int id) {
        this.id = id;
    }

    public AbstractVawsWirtschaftszweige(
    	int id, String wirtschaftszweig, Set<BasisBetreiber> basisBetreibers) {
        this.id = id;
        this.wirtschaftszweig = wirtschaftszweig;
        this.basisBetreibers = basisBetreibers;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getWirtschaftszweig() {
        return this.wirtschaftszweig;
    }
    public void setWirtschaftszweig(String wirtschaftszweig) {
        this.wirtschaftszweig = wirtschaftszweig;
    }

    public Set<BasisBetreiber> getBasisBetreibers() {
        return this.basisBetreibers;
    }
    public void setBasisBetreibers(Set<BasisBetreiber> basisBetreibers) {
        this.basisBetreibers = basisBetreibers;
    }

}

