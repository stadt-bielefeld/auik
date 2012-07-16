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
 * {@link DeaWzCodeId}.
 */
public abstract class AbstractDeaWzCodeId  implements java.io.Serializable {

     private String wzcdId;
     private int wzcdVersion;

    public AbstractDeaWzCodeId() {
    }


    public AbstractDeaWzCodeId(
    	String wzcdId, int wzcdVersion) {
        this.wzcdId = wzcdId;
        this.wzcdVersion = wzcdVersion;
    }

    public String getWzcdId() {
        return this.wzcdId;
    }
    public void setWzcdId(String wzcdId) {
        this.wzcdId = wzcdId;
    }

    public int getWzcdVersion() {
        return this.wzcdVersion;
    }
    public void setWzcdVersion(int wzcdVersion) {
        this.wzcdVersion = wzcdVersion;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaWzCodeId) ) return false;
		 DeaWzCodeId castOther = ( DeaWzCodeId ) other; 
         
		 return ( (this.getWzcdId()==castOther.getWzcdId()) || ( this.getWzcdId()!=null && castOther.getWzcdId()!=null && this.getWzcdId().equals(castOther.getWzcdId()) ) )
 && (this.getWzcdVersion()==castOther.getWzcdVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getWzcdId() == null ? 0 : this.getWzcdId().hashCode() );
         result = 37 * result + this.getWzcdVersion();
         return result;
   }   
}

