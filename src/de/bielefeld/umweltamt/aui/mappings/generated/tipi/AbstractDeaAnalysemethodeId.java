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
 * {@link DeaAnalysemethodeId}.
 */
public abstract class AbstractDeaAnalysemethodeId  implements java.io.Serializable {

     private String regelwerkId;
     private String gruppeDevId;
     private char variantenId;
     private int analyseVersion;

    public AbstractDeaAnalysemethodeId() {
    }


    public AbstractDeaAnalysemethodeId(
    	String regelwerkId, String gruppeDevId, char variantenId, int analyseVersion) {
        this.regelwerkId = regelwerkId;
        this.gruppeDevId = gruppeDevId;
        this.variantenId = variantenId;
        this.analyseVersion = analyseVersion;
    }

    public String getRegelwerkId() {
        return this.regelwerkId;
    }
    public void setRegelwerkId(String regelwerkId) {
        this.regelwerkId = regelwerkId;
    }

    public String getGruppeDevId() {
        return this.gruppeDevId;
    }
    public void setGruppeDevId(String gruppeDevId) {
        this.gruppeDevId = gruppeDevId;
    }

    public char getVariantenId() {
        return this.variantenId;
    }
    public void setVariantenId(char variantenId) {
        this.variantenId = variantenId;
    }

    public int getAnalyseVersion() {
        return this.analyseVersion;
    }
    public void setAnalyseVersion(int analyseVersion) {
        this.analyseVersion = analyseVersion;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeaAnalysemethodeId) ) return false;
		 DeaAnalysemethodeId castOther = ( DeaAnalysemethodeId ) other; 
         
		 return ( (this.getRegelwerkId()==castOther.getRegelwerkId()) || ( this.getRegelwerkId()!=null && castOther.getRegelwerkId()!=null && this.getRegelwerkId().equals(castOther.getRegelwerkId()) ) )
 && ( (this.getGruppeDevId()==castOther.getGruppeDevId()) || ( this.getGruppeDevId()!=null && castOther.getGruppeDevId()!=null && this.getGruppeDevId().equals(castOther.getGruppeDevId()) ) )
 && (this.getVariantenId()==castOther.getVariantenId())
 && (this.getAnalyseVersion()==castOther.getAnalyseVersion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRegelwerkId() == null ? 0 : this.getRegelwerkId().hashCode() );
         result = 37 * result + ( getGruppeDevId() == null ? 0 : this.getGruppeDevId().hashCode() );
         result = 37 * result + this.getVariantenId();
         result = 37 * result + this.getAnalyseVersion();
         return result;
   }   
}

