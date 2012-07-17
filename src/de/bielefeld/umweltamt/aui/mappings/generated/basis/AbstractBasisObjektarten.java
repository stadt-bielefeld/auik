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

package de.bielefeld.umweltamt.aui.mappings.generated.basis;


import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link BasisObjektarten}.
 */
public abstract class AbstractBasisObjektarten  implements java.io.Serializable {

     private int id;
     private String objektart;
     private String abteilung;
     private String kategorie;
     private Set<BasisObjekt> basisObjekts = new HashSet<BasisObjekt>(0);

    public AbstractBasisObjektarten() {
    }

    public AbstractBasisObjektarten(
    	int id) {
        this.id = id;
    }

    public AbstractBasisObjektarten(
    	int id, String objektart, String abteilung, String kategorie, Set<BasisObjekt> basisObjekts) {
        this.id = id;
        this.objektart = objektart;
        this.abteilung = abteilung;
        this.kategorie = kategorie;
        this.basisObjekts = basisObjekts;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getObjektart() {
        return this.objektart;
    }
    public void setObjektart(String objektart) {
        this.objektart = objektart;
    }

    public String getAbteilung() {
        return this.abteilung;
    }
    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String getKategorie() {
        return this.kategorie;
    }
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Set<BasisObjekt> getBasisObjekts() {
        return this.basisObjekts;
    }
    public void setBasisObjekts(Set<BasisObjekt> basisObjekts) {
        this.basisObjekts = basisObjekts;
    }

}

