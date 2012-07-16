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
 * {@link DeaTk25Id}.
 */
public abstract class AbstractDeaTk25Id  implements java.io.Serializable {

     private String kartenId;
     private int tk25Version;

    public AbstractDeaTk25Id() {
    }


    public AbstractDeaTk25Id(
    	String kartenId, int tk25Version) {
        this.kartenId = kartenId;
        this.tk25Version = tk25Version;
    }

    public String getKartenId() {
        return this.kartenId;
    }
    public void setKartenId(String kartenId) {
        this.kartenId = kartenId;
    }

    public int getTk25Version() {
        return this.tk25Version;
    }
    public void setTk25Version(int tk25Version) {
        this.tk25Version = tk25Version;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaTk25Id) ) return false;
		 DeaTk25Id castOther = ( DeaTk25Id ) other; 
         
		 return ( (this.getKartenId()==castOther.getKartenId()) || ( this.getKartenId()!=null && castOther.getKartenId()!=null && this.getKartenId().equals(castOther.getKartenId()) ) )
 && (this.getTk25Version()==castOther.getTk25Version());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getKartenId() == null ? 0 : this.getKartenId().hashCode() );
         result = 37 * result + this.getTk25Version();
         return result;
   }   
}

