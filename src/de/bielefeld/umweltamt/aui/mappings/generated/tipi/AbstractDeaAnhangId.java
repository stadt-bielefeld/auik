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
 * {@link DeaAnhangId}.
 */
public abstract class AbstractDeaAnhangId  implements java.io.Serializable {

     private String anhId;
     private int anhVersion;

    public AbstractDeaAnhangId() {
    }


    public AbstractDeaAnhangId(
    	String anhId, int anhVersion) {
        this.anhId = anhId;
        this.anhVersion = anhVersion;
    }

    public String getAnhId() {
        return this.anhId;
    }
    public void setAnhId(String anhId) {
        this.anhId = anhId;
    }

    public int getAnhVersion() {
        return this.anhVersion;
    }
    public void setAnhVersion(int anhVersion) {
        this.anhVersion = anhVersion;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaAnhangId) ) return false;
		 DeaAnhangId castOther = ( DeaAnhangId ) other; 
         
		 return ( (this.getAnhId()==castOther.getAnhId()) || ( this.getAnhId()!=null && castOther.getAnhId()!=null && this.getAnhId().equals(castOther.getAnhId()) ) )
 && (this.getAnhVersion()==castOther.getAnhVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAnhId() == null ? 0 : this.getAnhId().hashCode() );
         result = 37 * result + this.getAnhVersion();
         return result;
   }   
}

