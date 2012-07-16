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

package de.bielefeld.umweltamt.aui.mappings.generated.tipi;



/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link DeaStuaId}.
 */
public abstract class AbstractDeaStuaId  implements java.io.Serializable {

     private int stuaNr;
     private int stuaVersion;

    public AbstractDeaStuaId() {
    }


    public AbstractDeaStuaId(
    	int stuaNr, int stuaVersion) {
        this.stuaNr = stuaNr;
        this.stuaVersion = stuaVersion;
    }

    public int getStuaNr() {
        return this.stuaNr;
    }
    public void setStuaNr(int stuaNr) {
        this.stuaNr = stuaNr;
    }

    public int getStuaVersion() {
        return this.stuaVersion;
    }
    public void setStuaVersion(int stuaVersion) {
        this.stuaVersion = stuaVersion;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaStuaId) ) return false;
		 DeaStuaId castOther = ( DeaStuaId ) other; 
         
		 return (this.getStuaNr()==castOther.getStuaNr())
 && (this.getStuaVersion()==castOther.getStuaVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getStuaNr();
         result = 37 * result + this.getStuaVersion();
         return result;
   }   
}

