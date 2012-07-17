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

package de.bielefeld.umweltamt.aui.mappings.generated.vaws;



/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link VawsAbfuellflaeche}.
 */
public abstract class AbstractVawsAbfuellflaeche  implements java.io.Serializable {

     private int behaelterid;
     private Boolean eoh;
     private Boolean ef;
     private Boolean abfsaniert;
     private Boolean abfneuerstellt;
     private String bodenflaechenausf;
     private String beschbodenfl;
     private Float dicke;
     private String guete;
     private String fugenmaterial;
     private String beschfugenmat;
     private String niederschlagschutz;
     private Boolean abscheidervorh;
     private String beschableitung;
     private boolean enabled;
     private boolean deleted;

    public AbstractVawsAbfuellflaeche() {
    }

    public AbstractVawsAbfuellflaeche(
    	int behaelterid, boolean enabled, boolean deleted) {
        this.behaelterid = behaelterid;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractVawsAbfuellflaeche(
    	int behaelterid, Boolean eoh, Boolean ef, Boolean abfsaniert, Boolean abfneuerstellt, String bodenflaechenausf, String beschbodenfl, Float dicke, String guete, String fugenmaterial, String beschfugenmat, String niederschlagschutz, Boolean abscheidervorh, String beschableitung, boolean enabled, boolean deleted) {
        this.behaelterid = behaelterid;
        this.eoh = eoh;
        this.ef = ef;
        this.abfsaniert = abfsaniert;
        this.abfneuerstellt = abfneuerstellt;
        this.bodenflaechenausf = bodenflaechenausf;
        this.beschbodenfl = beschbodenfl;
        this.dicke = dicke;
        this.guete = guete;
        this.fugenmaterial = fugenmaterial;
        this.beschfugenmat = beschfugenmat;
        this.niederschlagschutz = niederschlagschutz;
        this.abscheidervorh = abscheidervorh;
        this.beschableitung = beschableitung;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public int getBehaelterid() {
        return this.behaelterid;
    }
    public void setBehaelterid(int behaelterid) {
        this.behaelterid = behaelterid;
    }

    public Boolean getEoh() {
        return this.eoh;
    }
    public void setEoh(Boolean eoh) {
        this.eoh = eoh;
    }

    public Boolean getEf() {
        return this.ef;
    }
    public void setEf(Boolean ef) {
        this.ef = ef;
    }

    public Boolean getAbfsaniert() {
        return this.abfsaniert;
    }
    public void setAbfsaniert(Boolean abfsaniert) {
        this.abfsaniert = abfsaniert;
    }

    public Boolean getAbfneuerstellt() {
        return this.abfneuerstellt;
    }
    public void setAbfneuerstellt(Boolean abfneuerstellt) {
        this.abfneuerstellt = abfneuerstellt;
    }

    public String getBodenflaechenausf() {
        return this.bodenflaechenausf;
    }
    public void setBodenflaechenausf(String bodenflaechenausf) {
        this.bodenflaechenausf = bodenflaechenausf;
    }

    public String getBeschbodenfl() {
        return this.beschbodenfl;
    }
    public void setBeschbodenfl(String beschbodenfl) {
        this.beschbodenfl = beschbodenfl;
    }

    public Float getDicke() {
        return this.dicke;
    }
    public void setDicke(Float dicke) {
        this.dicke = dicke;
    }

    public String getGuete() {
        return this.guete;
    }
    public void setGuete(String guete) {
        this.guete = guete;
    }

    public String getFugenmaterial() {
        return this.fugenmaterial;
    }
    public void setFugenmaterial(String fugenmaterial) {
        this.fugenmaterial = fugenmaterial;
    }

    public String getBeschfugenmat() {
        return this.beschfugenmat;
    }
    public void setBeschfugenmat(String beschfugenmat) {
        this.beschfugenmat = beschfugenmat;
    }

    public String getNiederschlagschutz() {
        return this.niederschlagschutz;
    }
    public void setNiederschlagschutz(String niederschlagschutz) {
        this.niederschlagschutz = niederschlagschutz;
    }

    public Boolean getAbscheidervorh() {
        return this.abscheidervorh;
    }
    public void setAbscheidervorh(Boolean abscheidervorh) {
        this.abscheidervorh = abscheidervorh;
    }

    public String getBeschableitung() {
        return this.beschableitung;
    }
    public void setBeschableitung(String beschableitung) {
        this.beschableitung = beschableitung;
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

