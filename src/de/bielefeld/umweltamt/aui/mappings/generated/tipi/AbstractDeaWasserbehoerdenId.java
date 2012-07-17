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
 * {@link DeaWasserbehoerdenId}.
 */
public abstract class AbstractDeaWasserbehoerdenId  implements java.io.Serializable {

     private String behoerdenId;
     private int behoerdenVersion;

    public AbstractDeaWasserbehoerdenId() {
    }


    public AbstractDeaWasserbehoerdenId(
    	String behoerdenId, int behoerdenVersion) {
        this.behoerdenId = behoerdenId;
        this.behoerdenVersion = behoerdenVersion;
    }

    public String getBehoerdenId() {
        return this.behoerdenId;
    }
    public void setBehoerdenId(String behoerdenId) {
        this.behoerdenId = behoerdenId;
    }

    public int getBehoerdenVersion() {
        return this.behoerdenVersion;
    }
    public void setBehoerdenVersion(int behoerdenVersion) {
        this.behoerdenVersion = behoerdenVersion;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaWasserbehoerdenId) ) return false;
		 DeaWasserbehoerdenId castOther = ( DeaWasserbehoerdenId ) other; 
         
		 return ( (this.getBehoerdenId()==castOther.getBehoerdenId()) || ( this.getBehoerdenId()!=null && castOther.getBehoerdenId()!=null && this.getBehoerdenId().equals(castOther.getBehoerdenId()) ) )
 && (this.getBehoerdenVersion()==castOther.getBehoerdenVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBehoerdenId() == null ? 0 : this.getBehoerdenId().hashCode() );
         result = 37 * result + this.getBehoerdenVersion();
         return result;
   }   
}

