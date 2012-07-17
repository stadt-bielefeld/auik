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

package de.bielefeld.umweltamt.aui.mappings.generated.basis;



/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link BasisObjektverknuepfung}.
 */
public abstract class AbstractBasisObjektverknuepfung  implements java.io.Serializable {

     private Integer id;
     private BasisObjekt basisObjektByIstVerknuepftMit;
     private BasisObjekt basisObjektByObjekt;
     private boolean enabled;
     private boolean deleted;

    public AbstractBasisObjektverknuepfung() {
    }

    public AbstractBasisObjektverknuepfung(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractBasisObjektverknuepfung(
    	BasisObjekt basisObjektByIstVerknuepftMit, BasisObjekt basisObjektByObjekt, boolean enabled, boolean deleted) {
        this.basisObjektByIstVerknuepftMit = basisObjektByIstVerknuepftMit;
        this.basisObjektByObjekt = basisObjektByObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public BasisObjekt getBasisObjektByIstVerknuepftMit() {
        return this.basisObjektByIstVerknuepftMit;
    }
    public void setBasisObjektByIstVerknuepftMit(BasisObjekt basisObjektByIstVerknuepftMit) {
        this.basisObjektByIstVerknuepftMit = basisObjektByIstVerknuepftMit;
    }

    public BasisObjekt getBasisObjektByObjekt() {
        return this.basisObjektByObjekt;
    }
    public void setBasisObjektByObjekt(BasisObjekt basisObjektByObjekt) {
        this.basisObjektByObjekt = basisObjektByObjekt;
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

