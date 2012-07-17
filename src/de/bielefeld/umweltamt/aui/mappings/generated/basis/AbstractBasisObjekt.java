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


import de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh40Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh52Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh53Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh56Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.AnhSuevFachdaten;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.IndeinlGenehmigung;
import de.bielefeld.umweltamt.aui.mappings.generated.indeinl.IndeinlUebergabestelle;
import de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsFachdaten;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A class that represents a row in a database table.
 * You can customize the behavior of this class by editing the class,
 * {@link BasisObjekt}.
 */
public abstract class AbstractBasisObjekt  implements java.io.Serializable {

     private Integer objektid;
     private BasisBetreiber basisBetreiber;
     private BasisStandort basisStandort;
     private BasisSachbearbeiter basisSachbearbeiter;
     private BasisObjektarten basisObjektarten;
     private Integer uschistdid;
     private String beschreibung;
     private Date wiedervorlage;
     private Date erfassungsdatum;
     private Date gueltigVon;
     private Date aenderungsdatum;
     private Date gueltigBis;
     private Boolean inaktiv;
     private String prioritaet;
     private boolean enabled;
     private boolean deleted;
     private IndeinlGenehmigung indeinlGenehmigung;
     private Anh53Fachdaten anh53Fachdaten;
     private AnhSuevFachdaten anhSuevFachdaten;
     private AtlProbepkt atlProbepkt;
     private Set<BasisObjektverknuepfung> basisObjektverknuepfungsForObjekt = new HashSet<BasisObjektverknuepfung>(0);
     private IndeinlUebergabestelle indeinlUebergabestelle;
     private Set<VawsFachdaten> vawsFachdatens = new HashSet<VawsFachdaten>(0);
     private Anh52Fachdaten anh52Fachdaten;
     private Anh56Fachdaten anh56Fachdaten;
     private Set<BasisObjektverknuepfung> basisObjektverknuepfungsForIstVerknuepftMit = new HashSet<BasisObjektverknuepfung>(0);
     private Anh40Fachdaten anh40Fachdaten;
     private Set<BasisObjektchrono> basisObjektchronos = new HashSet<BasisObjektchrono>(0);
     private Anh49Fachdaten anh49Fachdaten;
     private Anh50Fachdaten anh50Fachdaten;

    public AbstractBasisObjekt() {
    }

    public AbstractBasisObjekt(
    	boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public AbstractBasisObjekt(
    	BasisBetreiber basisBetreiber, BasisStandort basisStandort, BasisSachbearbeiter basisSachbearbeiter, BasisObjektarten basisObjektarten, Integer uschistdid, String beschreibung, Date wiedervorlage, Date erfassungsdatum, Date gueltigVon, Date aenderungsdatum, Date gueltigBis, Boolean inaktiv, String prioritaet, boolean enabled, boolean deleted, IndeinlGenehmigung indeinlGenehmigung, Anh53Fachdaten anh53Fachdaten, AnhSuevFachdaten anhSuevFachdaten, AtlProbepkt atlProbepkt, Set<BasisObjektverknuepfung> basisObjektverknuepfungsForObjekt, IndeinlUebergabestelle indeinlUebergabestelle, Set<VawsFachdaten> vawsFachdatens, Anh52Fachdaten anh52Fachdaten, Anh56Fachdaten anh56Fachdaten, Set<BasisObjektverknuepfung> basisObjektverknuepfungsForIstVerknuepftMit, Anh40Fachdaten anh40Fachdaten, Set<BasisObjektchrono> basisObjektchronos, Anh49Fachdaten anh49Fachdaten, Anh50Fachdaten anh50Fachdaten) {
        this.basisBetreiber = basisBetreiber;
        this.basisStandort = basisStandort;
        this.basisSachbearbeiter = basisSachbearbeiter;
        this.basisObjektarten = basisObjektarten;
        this.uschistdid = uschistdid;
        this.beschreibung = beschreibung;
        this.wiedervorlage = wiedervorlage;
        this.erfassungsdatum = erfassungsdatum;
        this.gueltigVon = gueltigVon;
        this.aenderungsdatum = aenderungsdatum;
        this.gueltigBis = gueltigBis;
        this.inaktiv = inaktiv;
        this.prioritaet = prioritaet;
        this.enabled = enabled;
        this.deleted = deleted;
        this.indeinlGenehmigung = indeinlGenehmigung;
        this.anh53Fachdaten = anh53Fachdaten;
        this.anhSuevFachdaten = anhSuevFachdaten;
        this.atlProbepkt = atlProbepkt;
        this.basisObjektverknuepfungsForObjekt = basisObjektverknuepfungsForObjekt;
        this.indeinlUebergabestelle = indeinlUebergabestelle;
        this.vawsFachdatens = vawsFachdatens;
        this.anh52Fachdaten = anh52Fachdaten;
        this.anh56Fachdaten = anh56Fachdaten;
        this.basisObjektverknuepfungsForIstVerknuepftMit = basisObjektverknuepfungsForIstVerknuepftMit;
        this.anh40Fachdaten = anh40Fachdaten;
        this.basisObjektchronos = basisObjektchronos;
        this.anh49Fachdaten = anh49Fachdaten;
        this.anh50Fachdaten = anh50Fachdaten;
    }

    public Integer getObjektid() {
        return this.objektid;
    }
    public void setObjektid(Integer objektid) {
        this.objektid = objektid;
    }

    public BasisBetreiber getBasisBetreiber() {
        return this.basisBetreiber;
    }
    public void setBasisBetreiber(BasisBetreiber basisBetreiber) {
        this.basisBetreiber = basisBetreiber;
    }

    public BasisStandort getBasisStandort() {
        return this.basisStandort;
    }
    public void setBasisStandort(BasisStandort basisStandort) {
        this.basisStandort = basisStandort;
    }

    public BasisSachbearbeiter getBasisSachbearbeiter() {
        return this.basisSachbearbeiter;
    }
    public void setBasisSachbearbeiter(BasisSachbearbeiter basisSachbearbeiter) {
        this.basisSachbearbeiter = basisSachbearbeiter;
    }

    public BasisObjektarten getBasisObjektarten() {
        return this.basisObjektarten;
    }
    public void setBasisObjektarten(BasisObjektarten basisObjektarten) {
        this.basisObjektarten = basisObjektarten;
    }

    public Integer getUschistdid() {
        return this.uschistdid;
    }
    public void setUschistdid(Integer uschistdid) {
        this.uschistdid = uschistdid;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Date getWiedervorlage() {
        return this.wiedervorlage;
    }
    public void setWiedervorlage(Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    public Date getErfassungsdatum() {
        return this.erfassungsdatum;
    }
    public void setErfassungsdatum(Date erfassungsdatum) {
        this.erfassungsdatum = erfassungsdatum;
    }

    public Date getGueltigVon() {
        return this.gueltigVon;
    }
    public void setGueltigVon(Date gueltigVon) {
        this.gueltigVon = gueltigVon;
    }

    public Date getAenderungsdatum() {
        return this.aenderungsdatum;
    }
    public void setAenderungsdatum(Date aenderungsdatum) {
        this.aenderungsdatum = aenderungsdatum;
    }

    public Date getGueltigBis() {
        return this.gueltigBis;
    }
    public void setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    public Boolean getInaktiv() {
        return this.inaktiv;
    }
    public void setInaktiv(Boolean inaktiv) {
        this.inaktiv = inaktiv;
    }

    public String getPrioritaet() {
        return this.prioritaet;
    }
    public void setPrioritaet(String prioritaet) {
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

    public IndeinlGenehmigung getIndeinlGenehmigung() {
        return this.indeinlGenehmigung;
    }
    public void setIndeinlGenehmigung(IndeinlGenehmigung indeinlGenehmigung) {
        this.indeinlGenehmigung = indeinlGenehmigung;
    }

    public Anh53Fachdaten getAnh53Fachdaten() {
        return this.anh53Fachdaten;
    }
    public void setAnh53Fachdaten(Anh53Fachdaten anh53Fachdaten) {
        this.anh53Fachdaten = anh53Fachdaten;
    }

    public AnhSuevFachdaten getAnhSuevFachdaten() {
        return this.anhSuevFachdaten;
    }
    public void setAnhSuevFachdaten(AnhSuevFachdaten anhSuevFachdaten) {
        this.anhSuevFachdaten = anhSuevFachdaten;
    }

    public AtlProbepkt getAtlProbepkt() {
        return this.atlProbepkt;
    }
    public void setAtlProbepkt(AtlProbepkt atlProbepkt) {
        this.atlProbepkt = atlProbepkt;
    }

    public Set<BasisObjektverknuepfung> getBasisObjektverknuepfungsForObjekt() {
        return this.basisObjektverknuepfungsForObjekt;
    }
    public void setBasisObjektverknuepfungsForObjekt(Set<BasisObjektverknuepfung> basisObjektverknuepfungsForObjekt) {
        this.basisObjektverknuepfungsForObjekt = basisObjektverknuepfungsForObjekt;
    }

    public IndeinlUebergabestelle getIndeinlUebergabestelle() {
        return this.indeinlUebergabestelle;
    }
    public void setIndeinlUebergabestelle(IndeinlUebergabestelle indeinlUebergabestelle) {
        this.indeinlUebergabestelle = indeinlUebergabestelle;
    }

    public Set<VawsFachdaten> getVawsFachdatens() {
        return this.vawsFachdatens;
    }
    public void setVawsFachdatens(Set<VawsFachdaten> vawsFachdatens) {
        this.vawsFachdatens = vawsFachdatens;
    }

    public Anh52Fachdaten getAnh52Fachdaten() {
        return this.anh52Fachdaten;
    }
    public void setAnh52Fachdaten(Anh52Fachdaten anh52Fachdaten) {
        this.anh52Fachdaten = anh52Fachdaten;
    }

    public Anh56Fachdaten getAnh56Fachdaten() {
        return this.anh56Fachdaten;
    }
    public void setAnh56Fachdaten(Anh56Fachdaten anh56Fachdaten) {
        this.anh56Fachdaten = anh56Fachdaten;
    }

    public Set<BasisObjektverknuepfung> getBasisObjektverknuepfungsForIstVerknuepftMit() {
        return this.basisObjektverknuepfungsForIstVerknuepftMit;
    }
    public void setBasisObjektverknuepfungsForIstVerknuepftMit(Set<BasisObjektverknuepfung> basisObjektverknuepfungsForIstVerknuepftMit) {
        this.basisObjektverknuepfungsForIstVerknuepftMit = basisObjektverknuepfungsForIstVerknuepftMit;
    }

    public Anh40Fachdaten getAnh40Fachdaten() {
        return this.anh40Fachdaten;
    }
    public void setAnh40Fachdaten(Anh40Fachdaten anh40Fachdaten) {
        this.anh40Fachdaten = anh40Fachdaten;
    }

    public Set<BasisObjektchrono> getBasisObjektchronos() {
        return this.basisObjektchronos;
    }
    public void setBasisObjektchronos(Set<BasisObjektchrono> basisObjektchronos) {
        this.basisObjektchronos = basisObjektchronos;
    }

    public Anh49Fachdaten getAnh49Fachdaten() {
        return this.anh49Fachdaten;
    }
    public void setAnh49Fachdaten(Anh49Fachdaten anh49Fachdaten) {
        this.anh49Fachdaten = anh49Fachdaten;
    }

    public Anh50Fachdaten getAnh50Fachdaten() {
        return this.anh50Fachdaten;
    }
    public void setAnh50Fachdaten(Anh50Fachdaten anh50Fachdaten) {
        this.anh50Fachdaten = anh50Fachdaten;
    }

}

