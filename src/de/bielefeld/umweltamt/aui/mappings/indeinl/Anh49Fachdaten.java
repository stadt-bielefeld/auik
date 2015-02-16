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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the Anh49Fachdaten database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Anh49Fachdaten  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnh49Fachdaten;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer objektid;
    private BasisObjekt basisObjekt;
    private String klaeranlage;
    private String name;
    private String bemerkungen;
    private String planquadrat;
    private Boolean abgemeldet;
    private String technikAnh49;
    private String technikAnh49Nr;
    private String sachkundelfa;
    private Boolean werkstatt;
    private Boolean bodeneinlaeufe;
    private Boolean waschanlagen;
    private String sonstiges;
    private String analysemonat;
    private Boolean abwasserfrei;
    private String anredeantragst;
    private String nameantragst;
    private String zusantragst;
    private String strasseantragst;
    private Integer hausnrantragst;
    private String hausnrzusantragst;
    private String plzantragst;
    private String ortantragst;
    private String sachbearbeiterIn;
    private String ansprechpartnerIn;
    private Date antragvom;
    private Date genehmigung;
    private Date wiedervorlage;
    private Date aenderungsgenehmigung;
    private Date letztesAnschreiben;
    private String anschreiben;
    private Boolean waschanlage;
    private Boolean ESatzung;
    private Date seitwann;
    private String sonstigestechnik;
    private Boolean maengel;
    private Boolean behoben;
    private Date frist;
    private Integer durchgefuehrt;
    private Date dekraTuevDatum;
    private boolean enabled;
    private boolean deleted;
    private Set<Anh49Kontrollen> anh49Kontrollens = new HashSet<Anh49Kontrollen>(0);
    private Set<Anh49Abscheiderdetails> anh49Abscheiderdetailses = new HashSet<Anh49Abscheiderdetails>(0);
    private Set<Anh49Analysen> anh49Analysens = new HashSet<Anh49Analysen>(0);
    private Set<Anh49Verwaltungsverf> anh49Verwaltungsverfs = new HashSet<Anh49Verwaltungsverf>(0);
    private Set<Anh49Ortstermine> anh49Ortstermines = new HashSet<Anh49Ortstermine>(0);
    private Set<Anh49Abfuhr> anh49Abfuhr = new HashSet<Anh49Abfuhr>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Anh49Fachdaten() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Anh49Fachdaten(
        BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Anh49Fachdaten(
        BasisObjekt basisObjekt, String klaeranlage, String name, String bemerkungen, String planquadrat, Boolean abgemeldet, String technikAnh49, String technikAnh49Nr, String sachkundelfa, Boolean werkstatt, Boolean bodeneinlaeufe, Boolean waschanlagen, String sonstiges, String analysemonat, Boolean abwasserfrei, String anredeantragst, String nameantragst, String zusantragst, String strasseantragst, Integer hausnrantragst, String hausnrzusantragst, String plzantragst, String ortantragst, String sachbearbeiterIn, String ansprechpartnerIn, Date antragvom, Date genehmigung, Date wiedervorlage, Date aenderungsgenehmigung, Date letztesAnschreiben, String anschreiben, Boolean waschanlage, Boolean ESatzung, Date seitwann, String sonstigestechnik, Boolean maengel, Boolean behoben, Date frist, Integer durchgefuehrt, Date dekraTuevDatum, boolean enabled, boolean deleted, Set<Anh49Kontrollen> anh49Kontrollens, Set<Anh49Abscheiderdetails> anh49Abscheiderdetailses, Set<Anh49Analysen> anh49Analysens, Set<Anh49Verwaltungsverf> anh49Verwaltungsverfs, Set<Anh49Ortstermine> anh49Ortstermines, Set<Anh49Abfuhr> anh49Abfuhr) {
        this.basisObjekt = basisObjekt;
        this.klaeranlage = klaeranlage;
        this.name = name;
        this.bemerkungen = bemerkungen;
        this.planquadrat = planquadrat;
        this.abgemeldet = abgemeldet;
        this.technikAnh49 = technikAnh49;
        this.technikAnh49Nr = technikAnh49Nr;
        this.sachkundelfa = sachkundelfa;
        this.werkstatt = werkstatt;
        this.bodeneinlaeufe = bodeneinlaeufe;
        this.waschanlagen = waschanlagen;
        this.sonstiges = sonstiges;
        this.analysemonat = analysemonat;
        this.abwasserfrei = abwasserfrei;
        this.anredeantragst = anredeantragst;
        this.nameantragst = nameantragst;
        this.zusantragst = zusantragst;
        this.strasseantragst = strasseantragst;
        this.hausnrantragst = hausnrantragst;
        this.hausnrzusantragst = hausnrzusantragst;
        this.plzantragst = plzantragst;
        this.ortantragst = ortantragst;
        this.sachbearbeiterIn = sachbearbeiterIn;
        this.ansprechpartnerIn = ansprechpartnerIn;
        this.antragvom = antragvom;
        this.genehmigung = genehmigung;
        this.wiedervorlage = wiedervorlage;
        this.aenderungsgenehmigung = aenderungsgenehmigung;
        this.letztesAnschreiben = letztesAnschreiben;
        this.anschreiben = anschreiben;
        this.waschanlage = waschanlage;
        this.ESatzung = ESatzung;
        this.seitwann = seitwann;
        this.sonstigestechnik = sonstigestechnik;
        this.maengel = maengel;
        this.behoben = behoben;
        this.frist = frist;
        this.durchgefuehrt = durchgefuehrt;
        this.dekraTuevDatum = dekraTuevDatum;
        this.enabled = enabled;
        this.deleted = deleted;
        this.anh49Kontrollens = anh49Kontrollens;
        this.anh49Abscheiderdetailses = anh49Abscheiderdetailses;
        this.anh49Analysens = anh49Analysens;
        this.anh49Verwaltungsverfs = anh49Verwaltungsverfs;
        this.anh49Ortstermines = anh49Ortstermines;
        this.anh49Abfuhr = anh49Abfuhr;
    }

    /* Setter and getter methods */
    public Integer getObjektid() {
        return this.objektid;
    }

    public void setObjektid(Integer objektid) {
        this.objektid = objektid;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }

    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public String getKlaeranlage() {
        return this.klaeranlage;
    }

    public void setKlaeranlage(String klaeranlage) {
        this.klaeranlage = klaeranlage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getPlanquadrat() {
        return this.planquadrat;
    }

    public void setPlanquadrat(String planquadrat) {
        this.planquadrat = planquadrat;
    }

    public Boolean getAbgemeldet() {
        return this.abgemeldet;
    }

    public void setAbgemeldet(Boolean abgemeldet) {
        this.abgemeldet = abgemeldet;
    }

    public String getTechnikAnh49() {
        return this.technikAnh49;
    }

    public void setTechnikAnh49(String technikAnh49) {
        this.technikAnh49 = technikAnh49;
    }

    public String getTechnikAnh49Nr() {
        return this.technikAnh49Nr;
    }

    public void setTechnikAnh49Nr(String technikAnh49Nr) {
        this.technikAnh49Nr = technikAnh49Nr;
    }

    public String getSachkundelfa() {
        return this.sachkundelfa;
    }

    public void setSachkundelfa(String sachkundelfa) {
        this.sachkundelfa = sachkundelfa;
    }

    public Boolean getWerkstatt() {
        return this.werkstatt;
    }

    public void setWerkstatt(Boolean werkstatt) {
        this.werkstatt = werkstatt;
    }

    public Boolean getBodeneinlaeufe() {
        return this.bodeneinlaeufe;
    }

    public void setBodeneinlaeufe(Boolean bodeneinlaeufe) {
        this.bodeneinlaeufe = bodeneinlaeufe;
    }

    public Boolean getWaschanlagen() {
        return this.waschanlagen;
    }

    public void setWaschanlagen(Boolean waschanlagen) {
        this.waschanlagen = waschanlagen;
    }

    public String getSonstiges() {
        return this.sonstiges;
    }

    public void setSonstiges(String sonstiges) {
        this.sonstiges = sonstiges;
    }

    public String getAnalysemonat() {
        return this.analysemonat;
    }

    public void setAnalysemonat(String analysemonat) {
        this.analysemonat = analysemonat;
    }

    public Boolean getAbwasserfrei() {
        return this.abwasserfrei;
    }

    public void setAbwasserfrei(Boolean abwasserfrei) {
        this.abwasserfrei = abwasserfrei;
    }

    public String getAnredeantragst() {
        return this.anredeantragst;
    }

    public void setAnredeantragst(String anredeantragst) {
        this.anredeantragst = anredeantragst;
    }

    public String getNameantragst() {
        return this.nameantragst;
    }

    public void setNameantragst(String nameantragst) {
        this.nameantragst = nameantragst;
    }

    public String getZusantragst() {
        return this.zusantragst;
    }

    public void setZusantragst(String zusantragst) {
        this.zusantragst = zusantragst;
    }

    public String getStrasseantragst() {
        return this.strasseantragst;
    }

    public void setStrasseantragst(String strasseantragst) {
        this.strasseantragst = strasseantragst;
    }

    public Integer getHausnrantragst() {
        return this.hausnrantragst;
    }

    public void setHausnrantragst(Integer hausnrantragst) {
        this.hausnrantragst = hausnrantragst;
    }

    public String getHausnrzusantragst() {
        return this.hausnrzusantragst;
    }

    public void setHausnrzusantragst(String hausnrzusantragst) {
        this.hausnrzusantragst = hausnrzusantragst;
    }

    public String getPlzantragst() {
        return this.plzantragst;
    }

    public void setPlzantragst(String plzantragst) {
        this.plzantragst = plzantragst;
    }

    public String getOrtantragst() {
        return this.ortantragst;
    }

    public void setOrtantragst(String ortantragst) {
        this.ortantragst = ortantragst;
    }

    public String getSachbearbeiterIn() {
        return this.sachbearbeiterIn;
    }

    public void setSachbearbeiterIn(String sachbearbeiterIn) {
        this.sachbearbeiterIn = sachbearbeiterIn;
    }

    public String getAnsprechpartnerIn() {
        return this.ansprechpartnerIn;
    }

    public void setAnsprechpartnerIn(String ansprechpartnerIn) {
        this.ansprechpartnerIn = ansprechpartnerIn;
    }

    public Date getAntragvom() {
        return this.antragvom;
    }

    public void setAntragvom(Date antragvom) {
        this.antragvom = antragvom;
    }

    public Date getGenehmigung() {
        return this.genehmigung;
    }

    public void setGenehmigung(Date genehmigung) {
        this.genehmigung = genehmigung;
    }

    public Date getWiedervorlage() {
        return this.wiedervorlage;
    }

    public void setWiedervorlage(Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    public Date getAenderungsgenehmigung() {
        return this.aenderungsgenehmigung;
    }

    public void setAenderungsgenehmigung(Date aenderungsgenehmigung) {
        this.aenderungsgenehmigung = aenderungsgenehmigung;
    }

    public Date getLetztesAnschreiben() {
        return this.letztesAnschreiben;
    }

    public void setLetztesAnschreiben(Date letztesAnschreiben) {
        this.letztesAnschreiben = letztesAnschreiben;
    }

    public String getAnschreiben() {
        return this.anschreiben;
    }

    public void setAnschreiben(String anschreiben) {
        this.anschreiben = anschreiben;
    }

    public Boolean getWaschanlage() {
        return this.waschanlage;
    }

    public void setWaschanlage(Boolean waschanlage) {
        this.waschanlage = waschanlage;
    }

    public Boolean getESatzung() {
        return this.ESatzung;
    }

    public void setESatzung(Boolean ESatzung) {
        this.ESatzung = ESatzung;
    }

    public Date getSeitwann() {
        return this.seitwann;
    }

    public void setSeitwann(Date seitwann) {
        this.seitwann = seitwann;
    }

    public String getSonstigestechnik() {
        return this.sonstigestechnik;
    }

    public void setSonstigestechnik(String sonstigestechnik) {
        this.sonstigestechnik = sonstigestechnik;
    }

    public Boolean getMaengel() {
        return this.maengel;
    }

    public void setMaengel(Boolean maengel) {
        this.maengel = maengel;
    }

    public Boolean getBehoben() {
        return this.behoben;
    }

    public void setBehoben(Boolean behoben) {
        this.behoben = behoben;
    }

    public Date getFrist() {
        return this.frist;
    }

    public void setFrist(Date frist) {
        this.frist = frist;
    }

    public Integer getDurchgefuehrt() {
        return this.durchgefuehrt;
    }

    public void setDurchgefuehrt(Integer durchgefuehrt) {
        this.durchgefuehrt = durchgefuehrt;
    }

    public Date getDekraTuevDatum() {
        return this.dekraTuevDatum;
    }

    public void setDekraTuevDatum(Date dekraTuevDatum) {
        this.dekraTuevDatum = dekraTuevDatum;
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

    public Set<Anh49Kontrollen> getAnh49Kontrollens() {
        return this.anh49Kontrollens;
    }

    public void setAnh49Kontrollens(Set<Anh49Kontrollen> anh49Kontrollens) {
        this.anh49Kontrollens = anh49Kontrollens;
    }

    public Set<Anh49Abscheiderdetails> getAnh49Abscheiderdetailses() {
        return this.anh49Abscheiderdetailses;
    }

    public void setAnh49Abscheiderdetailses(Set<Anh49Abscheiderdetails> anh49Abscheiderdetailses) {
        this.anh49Abscheiderdetailses = anh49Abscheiderdetailses;
    }

    public Set<Anh49Analysen> getAnh49Analysens() {
        return this.anh49Analysens;
    }

    public void setAnh49Analysens(Set<Anh49Analysen> anh49Analysens) {
        this.anh49Analysens = anh49Analysens;
    }

    public Set<Anh49Verwaltungsverf> getAnh49Verwaltungsverfs() {
        return this.anh49Verwaltungsverfs;
    }

    public void setAnh49Verwaltungsverfs(Set<Anh49Verwaltungsverf> anh49Verwaltungsverfs) {
        this.anh49Verwaltungsverfs = anh49Verwaltungsverfs;
    }

    public Set<Anh49Ortstermine> getAnh49Ortstermines() {
        return this.anh49Ortstermines;
    }

    public void setAnh49Ortstermines(Set<Anh49Ortstermine> anh49Ortstermines) {
        this.anh49Ortstermines = anh49Ortstermines;
    }

    public Set<Anh49Abfuhr> getAnh49Abfuhr() {
        return this.anh49Abfuhr;
    }

    public void setAnh49Abfuhr(Set<Anh49Abfuhr> anh49Abfuhr) {
        this.anh49Abfuhr = anh49Abfuhr;
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("basisObjekt").append("='").append(getBasisObjekt()).append("' ");
        buffer.append("klaeranlage").append("='").append(getKlaeranlage()).append("' ");
        buffer.append("name").append("='").append(getName()).append("' ");
        buffer.append("bemerkungen").append("='").append(getBemerkungen()).append("' ");
        buffer.append("planquadrat").append("='").append(getPlanquadrat()).append("' ");
        buffer.append("abgemeldet").append("='").append(getAbgemeldet()).append("' ");
        buffer.append("technikAnh49").append("='").append(getTechnikAnh49()).append("' ");
        buffer.append("technikAnh49Nr").append("='").append(getTechnikAnh49Nr()).append("' ");
        buffer.append("sachkundelfa").append("='").append(getSachkundelfa()).append("' ");
        buffer.append("werkstatt").append("='").append(getWerkstatt()).append("' ");
        buffer.append("bodeneinlaeufe").append("='").append(getBodeneinlaeufe()).append("' ");
        buffer.append("waschanlagen").append("='").append(getWaschanlagen()).append("' ");
        buffer.append("sonstiges").append("='").append(getSonstiges()).append("' ");
        buffer.append("analysemonat").append("='").append(getAnalysemonat()).append("' ");
        buffer.append("abwasserfrei").append("='").append(getAbwasserfrei()).append("' ");
        buffer.append("anredeantragst").append("='").append(getAnredeantragst()).append("' ");
        buffer.append("nameantragst").append("='").append(getNameantragst()).append("' ");
        buffer.append("zusantragst").append("='").append(getZusantragst()).append("' ");
        buffer.append("strasseantragst").append("='").append(getStrasseantragst()).append("' ");
        buffer.append("hausnrantragst").append("='").append(getHausnrantragst()).append("' ");
        buffer.append("hausnrzusantragst").append("='").append(getHausnrzusantragst()).append("' ");
        buffer.append("plzantragst").append("='").append(getPlzantragst()).append("' ");
        buffer.append("ortantragst").append("='").append(getOrtantragst()).append("' ");
        buffer.append("sachbearbeiterIn").append("='").append(getSachbearbeiterIn()).append("' ");
        buffer.append("ansprechpartnerIn").append("='").append(getAnsprechpartnerIn()).append("' ");
        buffer.append("antragvom").append("='").append(getAntragvom()).append("' ");
        buffer.append("genehmigung").append("='").append(getGenehmigung()).append("' ");
        buffer.append("wiedervorlage").append("='").append(getWiedervorlage()).append("' ");
        buffer.append("aenderungsgenehmigung").append("='").append(getAenderungsgenehmigung()).append("' ");
        buffer.append("letztesAnschreiben").append("='").append(getLetztesAnschreiben()).append("' ");
        buffer.append("anschreiben").append("='").append(getAnschreiben()).append("' ");
        buffer.append("waschanlage").append("='").append(getWaschanlage()).append("' ");
        buffer.append("ESatzung").append("='").append(getESatzung()).append("' ");
        buffer.append("seitwann").append("='").append(getSeitwann()).append("' ");
        buffer.append("sonstigestechnik").append("='").append(getSonstigestechnik()).append("' ");
        buffer.append("maengel").append("='").append(getMaengel()).append("' ");
        buffer.append("behoben").append("='").append(getBehoben()).append("' ");
        buffer.append("frist").append("='").append(getFrist()).append("' ");
        buffer.append("durchgefuehrt").append("='").append(getDurchgefuehrt()).append("' ");
        buffer.append("dekraTuevDatum").append("='").append(getDekraTuevDatum()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
        buffer.append("anh49Kontrollens").append("='").append(getAnh49Kontrollens()).append("' ");
        buffer.append("anh49Abscheiderdetailses").append("='").append(getAnh49Abscheiderdetailses()).append("' ");
        buffer.append("anh49Analysens").append("='").append(getAnh49Analysens()).append("' ");
        buffer.append("anh49Verwaltungsverfs").append("='").append(getAnh49Verwaltungsverfs()).append("' ");
        buffer.append("anh49Ortstermines").append("='").append(getAnh49Ortstermines()).append("' ");
        buffer.append("anh49Abfuhr").append("='").append(getAnh49Abfuhr()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    /**
     * @param other
     * @return <code>true</code>, if this and other are equal,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof Anh49Fachdaten)) return false;
        return (this.getObjektid().equals(
            ((Anh49Fachdaten) other).getObjektid()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getObjektid() == null ?
            0 : this.getObjektid().hashCode();
        result = result * 37 + idValue;
        return result;
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>Anh49Fachdaten</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Anh49Fachdaten merge(Anh49Fachdaten detachedInstance) {
        log.debug("Merging Anh49Fachdaten instance " + detachedInstance);
        return (Anh49Fachdaten) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Anh49Fachdaten saved = Anh49Fachdaten.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Anh49Fachdaten with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Anh49Fachdaten
     */
    private void copy(Anh49Fachdaten copy) {
        this.basisObjekt = copy.getBasisObjekt();
        this.klaeranlage = copy.getKlaeranlage();
        this.name = copy.getName();
        this.bemerkungen = copy.getBemerkungen();
        this.planquadrat = copy.getPlanquadrat();
        this.abgemeldet = copy.getAbgemeldet();
        this.technikAnh49 = copy.getTechnikAnh49();
        this.technikAnh49Nr = copy.getTechnikAnh49Nr();
        this.sachkundelfa = copy.getSachkundelfa();
        this.werkstatt = copy.getWerkstatt();
        this.bodeneinlaeufe = copy.getBodeneinlaeufe();
        this.waschanlagen = copy.getWaschanlagen();
        this.sonstiges = copy.getSonstiges();
        this.analysemonat = copy.getAnalysemonat();
        this.abwasserfrei = copy.getAbwasserfrei();
        this.anredeantragst = copy.getAnredeantragst();
        this.nameantragst = copy.getNameantragst();
        this.zusantragst = copy.getZusantragst();
        this.strasseantragst = copy.getStrasseantragst();
        this.hausnrantragst = copy.getHausnrantragst();
        this.hausnrzusantragst = copy.getHausnrzusantragst();
        this.plzantragst = copy.getPlzantragst();
        this.ortantragst = copy.getOrtantragst();
        this.sachbearbeiterIn = copy.getSachbearbeiterIn();
        this.ansprechpartnerIn = copy.getAnsprechpartnerIn();
        this.antragvom = copy.getAntragvom();
        this.genehmigung = copy.getGenehmigung();
        this.wiedervorlage = copy.getWiedervorlage();
        this.aenderungsgenehmigung = copy.getAenderungsgenehmigung();
        this.letztesAnschreiben = copy.getLetztesAnschreiben();
        this.anschreiben = copy.getAnschreiben();
        this.waschanlage = copy.getWaschanlage();
        this.ESatzung = copy.getESatzung();
        this.seitwann = copy.getSeitwann();
        this.sonstigestechnik = copy.getSonstigestechnik();
        this.maengel = copy.getMaengel();
        this.behoben = copy.getBehoben();
        this.frist = copy.getFrist();
        this.durchgefuehrt = copy.getDurchgefuehrt();
        this.dekraTuevDatum = copy.getDekraTuevDatum();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
        this.anh49Kontrollens = copy.getAnh49Kontrollens();
        this.anh49Abscheiderdetailses = copy.getAnh49Abscheiderdetailses();
        this.anh49Analysens = copy.getAnh49Analysens();
        this.anh49Verwaltungsverfs = copy.getAnh49Verwaltungsverfs();
        this.anh49Ortstermines = copy.getAnh49Ortstermines();
        this.anh49Abfuhr = copy.getAnh49Abfuhr();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Anh49Fachdaten detachedInstance) {
        log.debug("Deleting Anh49Fachdaten instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Anh49Fachdaten.delete(this);
    }

    /**
     * Find an <code>Anh49Fachdaten</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anh49Fachdaten</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anh49Fachdaten findById(java.lang.Integer id) {
        log.debug("Getting Anh49Fachdaten instance with id: " + id);
        return (Anh49Fachdaten)
            new DatabaseAccess().get(Anh49Fachdaten.class, id);
    }

    /**
     * Get a list of all <code>Anh49Fachdaten</code>
     * @return <code>List&lt;Anh49Fachdaten&gt;</code>
     *         all <code>Anh49Fachdaten</code>
     */
    public static List<Anh49Fachdaten> getAll() {
        return DatabaseQuery.getAll(new Anh49Fachdaten());
    }

    /* Custom code goes below here! */

}
