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

package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjekt;
import java.util.Date;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link AnhSuevFachdaten}.
 */
public abstract class AbstractAnhSuevFachdaten  implements java.io.Serializable {

     private int objektid;
     private BasisObjekt basisObjekt;
     private Boolean groesser3ha;
     private Integer versFlaeche;
     private Boolean suevkanPflicht;
     private Boolean indirektsw;
     private Boolean indirektrw;
     private Boolean direktsw;
     private Boolean direktrw;
     private Integer anzeige58;
     private Boolean sanierungErfolgt;
     private Boolean sanierungskonzept;
     private Boolean keineAngaben;
     private Date datAnzeige58;
     private Date datAnschreiben;
     private boolean enabled;
     private boolean deleted;

    public AbstractAnhSuevFachdaten() {
    }

    public AbstractAnhSuevFachdaten(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAnhSuevFachdaten(
    	BasisObjekt basisObjekt, Boolean groesser3ha, Integer versFlaeche, Boolean suevkanPflicht, Boolean indirektsw, Boolean indirektrw, Boolean direktsw, Boolean direktrw, Integer anzeige58, Boolean sanierungErfolgt, Boolean sanierungskonzept, Boolean keineAngaben, Date datAnzeige58, Date datAnschreiben, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.groesser3ha = groesser3ha;
        this.versFlaeche = versFlaeche;
        this.suevkanPflicht = suevkanPflicht;
        this.indirektsw = indirektsw;
        this.indirektrw = indirektrw;
        this.direktsw = direktsw;
        this.direktrw = direktrw;
        this.anzeige58 = anzeige58;
        this.sanierungErfolgt = sanierungErfolgt;
        this.sanierungskonzept = sanierungskonzept;
        this.keineAngaben = keineAngaben;
        this.datAnzeige58 = datAnzeige58;
        this.datAnschreiben = datAnschreiben;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public int getObjektid() {
        return this.objektid;
    }
    public void setObjektid(int objektid) {
        this.objektid = objektid;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public Boolean getGroesser3ha() {
        return this.groesser3ha;
    }
    public void setGroesser3ha(Boolean groesser3ha) {
        this.groesser3ha = groesser3ha;
    }

    public Integer getVersFlaeche() {
        return this.versFlaeche;
    }
    public void setVersFlaeche(Integer versFlaeche) {
        this.versFlaeche = versFlaeche;
    }

    public Boolean getSuevkanPflicht() {
        return this.suevkanPflicht;
    }
    public void setSuevkanPflicht(Boolean suevkanPflicht) {
        this.suevkanPflicht = suevkanPflicht;
    }

    public Boolean getIndirektsw() {
        return this.indirektsw;
    }
    public void setIndirektsw(Boolean indirektsw) {
        this.indirektsw = indirektsw;
    }

    public Boolean getIndirektrw() {
        return this.indirektrw;
    }
    public void setIndirektrw(Boolean indirektrw) {
        this.indirektrw = indirektrw;
    }

    public Boolean getDirektsw() {
        return this.direktsw;
    }
    public void setDirektsw(Boolean direktsw) {
        this.direktsw = direktsw;
    }

    public Boolean getDirektrw() {
        return this.direktrw;
    }
    public void setDirektrw(Boolean direktrw) {
        this.direktrw = direktrw;
    }

    public Integer getAnzeige58() {
        return this.anzeige58;
    }
    public void setAnzeige58(Integer anzeige58) {
        this.anzeige58 = anzeige58;
    }

    public Boolean getSanierungErfolgt() {
        return this.sanierungErfolgt;
    }
    public void setSanierungErfolgt(Boolean sanierungErfolgt) {
        this.sanierungErfolgt = sanierungErfolgt;
    }

    public Boolean getSanierungskonzept() {
        return this.sanierungskonzept;
    }
    public void setSanierungskonzept(Boolean sanierungskonzept) {
        this.sanierungskonzept = sanierungskonzept;
    }

    public Boolean getKeineAngaben() {
        return this.keineAngaben;
    }
    public void setKeineAngaben(Boolean keineAngaben) {
        this.keineAngaben = keineAngaben;
    }

    public Date getDatAnzeige58() {
        return this.datAnzeige58;
    }
    public void setDatAnzeige58(Date datAnzeige58) {
        this.datAnzeige58 = datAnzeige58;
    }

    public Date getDatAnschreiben() {
        return this.datAnschreiben;
    }
    public void setDatAnschreiben(Date datAnschreiben) {
        this.datAnschreiben = datAnschreiben;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}

