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

package de.bielefeld.umweltamt.aui.mappings.generated.tipi;



/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link DeaStoffeId}.
 */
public abstract class AbstractDeaStoffeId  implements java.io.Serializable {

     private int stoffNr;
     private int stoffVersion;

    public AbstractDeaStoffeId() {
    }


    public AbstractDeaStoffeId(
    	int stoffNr, int stoffVersion) {
        this.stoffNr = stoffNr;
        this.stoffVersion = stoffVersion;
    }

    public int getStoffNr() {
        return this.stoffNr;
    }
    public void setStoffNr(int stoffNr) {
        this.stoffNr = stoffNr;
    }

    public int getStoffVersion() {
        return this.stoffVersion;
    }
    public void setStoffVersion(int stoffVersion) {
        this.stoffVersion = stoffVersion;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaStoffeId) ) return false;
		 DeaStoffeId castOther = ( DeaStoffeId ) other; 
         
		 return (this.getStoffNr()==castOther.getStoffNr())
 && (this.getStoffVersion()==castOther.getStoffVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getStoffNr();
         result = 37 * result + this.getStoffVersion();
         return result;
   }   
}

