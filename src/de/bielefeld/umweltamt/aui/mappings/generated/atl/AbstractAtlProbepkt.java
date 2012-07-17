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

package de.bielefeld.umweltamt.aui.mappings.generated.atl;


import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisSachbearbeiter;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link AtlProbepkt}.
 */
public abstract class AbstractAtlProbepkt  implements java.io.Serializable {

     private int objektid;
     private AtlProbeart atlProbeart;
     private AtlKlaeranlagen atlKlaeranlagen;
     private BasisObjekt basisObjekt;
     private BasisSachbearbeiter basisSachbearbeiter;
     private AtlSielhaut atlSielhaut;
     private String beschreibung;
     private Integer nrProbepkt;
     private Integer firmenId;
     private String branche;
     private boolean enabled;
     private boolean deleted;
     private Set<AtlProbenahmen> atlProbenahmens = new HashSet<AtlProbenahmen>(0);

    public AbstractAtlProbepkt() {
    }

    public AbstractAtlProbepkt(
    	BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractAtlProbepkt(
    	AtlProbeart atlProbeart, AtlKlaeranlagen atlKlaeranlagen, BasisObjekt basisObjekt, BasisSachbearbeiter basisSachbearbeiter, AtlSielhaut atlSielhaut, String beschreibung, Integer nrProbepkt, Integer firmenId, String branche, boolean enabled, boolean deleted, Set<AtlProbenahmen> atlProbenahmens) {
        this.atlProbeart = atlProbeart;
        this.atlKlaeranlagen = atlKlaeranlagen;
        this.basisObjekt = basisObjekt;
        this.basisSachbearbeiter = basisSachbearbeiter;
        this.atlSielhaut = atlSielhaut;
        this.beschreibung = beschreibung;
        this.nrProbepkt = nrProbepkt;
        this.firmenId = firmenId;
        this.branche = branche;
        this.enabled = enabled;
        this.deleted = deleted;
        this.atlProbenahmens = atlProbenahmens;
    }

    public int getObjektid() {
        return this.objektid;
    }
    public void setObjektid(int objektid) {
        this.objektid = objektid;
    }

    public AtlProbeart getAtlProbeart() {
        return this.atlProbeart;
    }
    public void setAtlProbeart(AtlProbeart atlProbeart) {
        this.atlProbeart = atlProbeart;
    }

    public AtlKlaeranlagen getAtlKlaeranlagen() {
        return this.atlKlaeranlagen;
    }
    public void setAtlKlaeranlagen(AtlKlaeranlagen atlKlaeranlagen) {
        this.atlKlaeranlagen = atlKlaeranlagen;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }
    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public BasisSachbearbeiter getBasisSachbearbeiter() {
        return this.basisSachbearbeiter;
    }
    public void setBasisSachbearbeiter(BasisSachbearbeiter basisSachbearbeiter) {
        this.basisSachbearbeiter = basisSachbearbeiter;
    }

    public AtlSielhaut getAtlSielhaut() {
        return this.atlSielhaut;
    }
    public void setAtlSielhaut(AtlSielhaut atlSielhaut) {
        this.atlSielhaut = atlSielhaut;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Integer getNrProbepkt() {
        return this.nrProbepkt;
    }
    public void setNrProbepkt(Integer nrProbepkt) {
        this.nrProbepkt = nrProbepkt;
    }

    public Integer getFirmenId() {
        return this.firmenId;
    }
    public void setFirmenId(Integer firmenId) {
        this.firmenId = firmenId;
    }

    public String getBranche() {
        return this.branche;
    }
    public void setBranche(String branche) {
        this.branche = branche;
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

    public Set<AtlProbenahmen> getAtlProbenahmens() {
        return this.atlProbenahmens;
    }
    public void setAtlProbenahmens(Set<AtlProbenahmen> atlProbenahmens) {
        this.atlProbenahmens = atlProbenahmens;
    }

}

