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
 * {@link DeaProbedauerId}.
 */
public abstract class AbstractDeaProbedauerId  implements java.io.Serializable {

     private String probSchluesselId;
     private int probVersion;

    public AbstractDeaProbedauerId() {
    }


    public AbstractDeaProbedauerId(
    	String probSchluesselId, int probVersion) {
        this.probSchluesselId = probSchluesselId;
        this.probVersion = probVersion;
    }

    public String getProbSchluesselId() {
        return this.probSchluesselId;
    }
    public void setProbSchluesselId(String probSchluesselId) {
        this.probSchluesselId = probSchluesselId;
    }

    public int getProbVersion() {
        return this.probVersion;
    }
    public void setProbVersion(int probVersion) {
        this.probVersion = probVersion;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaProbedauerId) ) return false;
		 DeaProbedauerId castOther = ( DeaProbedauerId ) other; 
         
		 return ( (this.getProbSchluesselId()==castOther.getProbSchluesselId()) || ( this.getProbSchluesselId()!=null && castOther.getProbSchluesselId()!=null && this.getProbSchluesselId().equals(castOther.getProbSchluesselId()) ) )
 && (this.getProbVersion()==castOther.getProbVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProbSchluesselId() == null ? 0 : this.getProbSchluesselId().hashCode() );
         result = 37 * result + this.getProbVersion();
         return result;
   }   
}

