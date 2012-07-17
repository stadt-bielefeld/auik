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
 * {@link BasisPrioritaet}.
 */
public abstract class AbstractBasisPrioritaet  implements java.io.Serializable {

     private BasisPrioritaetId id;
     private BasisStandort basisStandort;
     private BasisBetreiber basisBetreiber;
     private Integer prioritaet;
     private boolean enabled;
     private boolean deleted;

    public AbstractBasisPrioritaet() {
    }

    public AbstractBasisPrioritaet(
    	BasisPrioritaetId id, BasisStandort basisStandort, BasisBetreiber basisBetreiber, boolean enabled, boolean deleted) {
        this.id = id;
        this.basisStandort = basisStandort;
        this.basisBetreiber = basisBetreiber;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractBasisPrioritaet(
    	BasisPrioritaetId id, BasisStandort basisStandort, BasisBetreiber basisBetreiber, Integer prioritaet, boolean enabled, boolean deleted) {
        this.id = id;
        this.basisStandort = basisStandort;
        this.basisBetreiber = basisBetreiber;
        this.prioritaet = prioritaet;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public BasisPrioritaetId getId() {
        return this.id;
    }
    public void setId(BasisPrioritaetId id) {
        this.id = id;
    }

    public BasisStandort getBasisStandort() {
        return this.basisStandort;
    }
    public void setBasisStandort(BasisStandort basisStandort) {
        this.basisStandort = basisStandort;
    }

    public BasisBetreiber getBasisBetreiber() {
        return this.basisBetreiber;
    }
    public void setBasisBetreiber(BasisBetreiber basisBetreiber) {
        this.basisBetreiber = basisBetreiber;
    }

    public Integer getPrioritaet() {
        return this.prioritaet;
    }
    public void setPrioritaet(Integer prioritaet) {
        this.prioritaet = prioritaet;
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

