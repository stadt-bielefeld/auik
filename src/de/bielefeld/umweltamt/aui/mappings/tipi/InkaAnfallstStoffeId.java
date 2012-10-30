/**
 * Copyright 2005-2042, Stadt Bielefeld
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

// Generated by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.tipi;

import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;

/**
 * A class that represents a row in the InkaAnfallstStoffeId database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class InkaAnfallstStoffeId  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forInkaAnfallstStoffeId;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer anfallstelleNr;
    private Integer stoffNr;

    /** Default constructor */
    public InkaAnfallstStoffeId() {
        // This place is intentionally left blank.
    }


    /** Full constructor */
    public InkaAnfallstStoffeId(
        Integer anfallstelleNr, Integer stoffNr) {
        this.anfallstelleNr = anfallstelleNr;
        this.stoffNr = stoffNr;
    }

    /* Setter and getter methods */
    public Integer getAnfallstelleNr() {
        return this.anfallstelleNr;
    }

    public void setAnfallstelleNr(Integer anfallstelleNr) {
        this.anfallstelleNr = anfallstelleNr;
    }

    public Integer getStoffNr() {
        return this.stoffNr;
    }

    public void setStoffNr(Integer stoffNr) {
        this.stoffNr = stoffNr;
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("anfallstelleNr").append("='").append(getAnfallstelleNr()).append("' ");			
        buffer.append("stoffNr").append("='").append(getStoffNr()).append("' ");			
        buffer.append("]");

        return buffer.toString();
    }

    public boolean equals(Object other) {
        if ((this == other )) return true;
        if ((other == null )) return false;
        if (!(other instanceof InkaAnfallstStoffeId) ) return false;
        InkaAnfallstStoffeId castOther = ( InkaAnfallstStoffeId ) other; 
        return ( (this.getAnfallstelleNr()==castOther.getAnfallstelleNr()) || ( this.getAnfallstelleNr()!=null && castOther.getAnfallstelleNr()!=null && this.getAnfallstelleNr().equals(castOther.getAnfallstelleNr()) ) )
 && ( (this.getStoffNr()==castOther.getStoffNr()) || ( this.getStoffNr()!=null && castOther.getStoffNr()!=null && this.getStoffNr().equals(castOther.getStoffNr()) ) );
   }

    public int hashCode() {
        int result = 17;
        result = 37 * result + ( getAnfallstelleNr() == null ? 0 : this.getAnfallstelleNr().hashCode() );
        result = 37 * result + ( getStoffNr() == null ? 0 : this.getStoffNr().hashCode() );
        return result;
    }
    /* Custom code goes below here! */

}
