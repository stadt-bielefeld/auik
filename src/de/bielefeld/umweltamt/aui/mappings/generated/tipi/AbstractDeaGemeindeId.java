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
 * {@link DeaGemeindeId}.
 */
public abstract class AbstractDeaGemeindeId  implements java.io.Serializable {

     private String gemeindeId;
     private int gemeindeVersion;

    public AbstractDeaGemeindeId() {
    }


    public AbstractDeaGemeindeId(
    	String gemeindeId, int gemeindeVersion) {
        this.gemeindeId = gemeindeId;
        this.gemeindeVersion = gemeindeVersion;
    }

    public String getGemeindeId() {
        return this.gemeindeId;
    }
    public void setGemeindeId(String gemeindeId) {
        this.gemeindeId = gemeindeId;
    }

    public int getGemeindeVersion() {
        return this.gemeindeVersion;
    }
    public void setGemeindeVersion(int gemeindeVersion) {
        this.gemeindeVersion = gemeindeVersion;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaGemeindeId) ) return false;
		 DeaGemeindeId castOther = ( DeaGemeindeId ) other; 
         
		 return ( (this.getGemeindeId()==castOther.getGemeindeId()) || ( this.getGemeindeId()!=null && castOther.getGemeindeId()!=null && this.getGemeindeId().equals(castOther.getGemeindeId()) ) )
 && (this.getGemeindeVersion()==castOther.getGemeindeVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getGemeindeId() == null ? 0 : this.getGemeindeId().hashCode() );
         result = 37 * result + this.getGemeindeVersion();
         return result;
   }   
}

