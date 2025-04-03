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

// Generated by Hibernate Tools 5.0.0.Final

package de.bielefeld.umweltamt.aui.mappings.oberflgw;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.elka.Wasserrecht;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A class that represents a row in the Entwaesserungsgrundstueck database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Entwaesserungsgrundstueck  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forEntwaesserungsgrundstueck;

    /* Primary key, foreign keys (relations) and table columns */
    private Long nr;
    private Objekt objekt;
    private boolean erlFreiElTog;
    private BigDecimal regenspende;
    private String bemerkung;
    private BigDecimal regenhaeufigkeit;
    private Integer regendauer;
    private Integer grEntwGebiet;
    private BigDecimal dtvWert;
    private Integer wasserableitungsstreckeOpt;
    private String nameEtwGebiet;
    private Date erstellDat;
    private Integer einlBereichOpt;
    private String abwbeskonNr;
    private Integer einbauartOpt;
    private Date aktualDat;
    private Integer adrNr;
    private String externalNr;
    private Set<AfsNiederschlagswasser> afsNiederschlagswassers = new HashSet<AfsNiederschlagswasser>(0);
    private Boolean woTog;
    private Boolean miTog;
    private Boolean geTog;
    private Boolean giTog;
    private Boolean gemTog;
    private Boolean strTog;
    private Boolean parkplatzTog;
    private Set<Abaverfahren> Abaverfahrens = new HashSet<Abaverfahren>(0);
    private Set<Wasserrecht> Wasserrechts = new HashSet<Wasserrecht>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Entwaesserungsgrundstueck() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Entwaesserungsgrundstueck(
        long nr, Objekt objekt, boolean erlFreiElTog, Date erstellDat, Integer einlBereichOpt, Date aktualDat, Integer adrNr) {
        this.nr = nr;
        this.objekt = objekt;
        this.erlFreiElTog = erlFreiElTog;
        this.erstellDat = erstellDat;
        this.einlBereichOpt = einlBereichOpt;
        this.aktualDat = aktualDat;
        this.adrNr = adrNr;
    }

    /** Full constructor */
    public Entwaesserungsgrundstueck(
        long nr, Objekt objekt, Wasserrecht wasserrecht, boolean erlFreiElTog, BigDecimal regenspende, String bemerkung, BigDecimal regenhaeufigkeit, Integer regendauer, Integer grEntwGebiet, BigDecimal dtvWert, Integer wasserableitungsstreckeOpt, String nameEtwGebiet, Date erstellDat, Integer einlBereichOpt, String abwbeskonNr, Integer einbauartOpt, Date aktualDat, Integer adrNr, String externalNr, Boolean woTog, Boolean miTog, Boolean geTog, Boolean giTog, Boolean gemTog, Boolean strTog, Boolean parkplatzTog, Set<Abaverfahren> Abaverfahrens, Set<Wasserrecht> Wasserrechts, Set<AfsNiederschlagswasser> afsNiederschlagswassers) {
        this.nr = nr;
        this.objekt = objekt;
        this.Wasserrechts = Wasserrechts;
        this.erlFreiElTog = erlFreiElTog;
        this.regenspende = regenspende;
        this.bemerkung = bemerkung;
        this.regenhaeufigkeit = regenhaeufigkeit;
        this.regendauer = regendauer;
        this.grEntwGebiet = grEntwGebiet;
        this.dtvWert = dtvWert;
        this.wasserableitungsstreckeOpt = wasserableitungsstreckeOpt;
        this.nameEtwGebiet = nameEtwGebiet;
        this.erstellDat = erstellDat;
        this.einlBereichOpt = einlBereichOpt;
        this.abwbeskonNr = abwbeskonNr;
        this.einbauartOpt = einbauartOpt;
        this.aktualDat = aktualDat;
        this.adrNr = adrNr;
        this.externalNr = externalNr;
        this.afsNiederschlagswassers = afsNiederschlagswassers;
        this.Abaverfahrens = Abaverfahrens;
        this.woTog = woTog;
        this.miTog = miTog;
        this.geTog = geTog;
        this.giTog = giTog;
        this.gemTog = gemTog;
        this.strTog = strTog;
        this.parkplatzTog  = parkplatzTog;
    }

    /* Setter and getter methods */
    public Long getNr() {
        return this.nr;
    }

    public void setNr(long nr) {
        this.nr = nr;
    }

    public Objekt getObjekt() {
        return this.objekt;
    }

    public void setObjekt(Objekt objekt) {
        this.objekt = objekt;
    }

    public Set<Wasserrecht> getWasserrechts() {
        return this.Wasserrechts;
    }

    public void setWasserrechts(Set<Wasserrecht> Wasserrechts) {
        this.Wasserrechts = Wasserrechts;
    }

    public Boolean isErlFreiElTog() {
        return this.erlFreiElTog;
    }

    public void setErlFreiElTog(boolean erlFreiElTog) {
        this.erlFreiElTog = erlFreiElTog;
    }

    public BigDecimal getRegenspende() {
        return this.regenspende;
    }

    public void setRegenspende(BigDecimal regenspende) {
        this.regenspende = regenspende;
    }

    public String getBemerkung() {
        return this.bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public BigDecimal getRegenhaeufigkeit() {
        return this.regenhaeufigkeit;
    }

    public void setRegenhaeufigkeit(BigDecimal regenhaeufigkeit) {
        this.regenhaeufigkeit = regenhaeufigkeit;
    }

    public Integer getRegendauer() {
        return this.regendauer;
    }

    public void setRegendauer(Integer regendauer) {
        this.regendauer = regendauer;
    }

    public Integer getGrEntwGebiet() {
        return this.grEntwGebiet;
    }

    public void setGrEntwGebiet(Integer grEntwGebiet) {
        this.grEntwGebiet = grEntwGebiet;
    }

    public BigDecimal getDtvWert() {
        return this.dtvWert;
    }

    public void setDtvWert(BigDecimal dtvWert) {
        this.dtvWert = dtvWert;
    }

    public Integer getWasserableitungsstreckeOpt() {
        return this.wasserableitungsstreckeOpt;
    }

    public void setWasserableitungsstreckeOpt(Integer wasserableitungsstreckeOpt) {
        this.wasserableitungsstreckeOpt = wasserableitungsstreckeOpt;
    }

    public String getNameEtwGebiet() {
        return this.nameEtwGebiet;
    }

    public void setNameEtwGebiet(String nameEtwGebiet) {
        this.nameEtwGebiet = nameEtwGebiet;
    }

    public Date getErstellDat() {
        return this.erstellDat;
    }

    public void setErstellDat(Date erstellDat) {
        this.erstellDat = erstellDat;
    }

    public Integer getEinlBereichOpt() {
        return this.einlBereichOpt;
    }

    public void setEinlBereichOpt(Integer einlBereichOpt) {
        this.einlBereichOpt = einlBereichOpt;
    }

    public String getAbwbeskonNr() {
        return this.abwbeskonNr;
    }

    public void setAbwbeskonNr(String abwbeskonNr) {
        this.abwbeskonNr = abwbeskonNr;
    }

    public Integer getEinbauartOpt() {
        return this.einbauartOpt;
    }

    public void setEinbauartOpt(Integer einbauartOpt) {
        this.einbauartOpt = einbauartOpt;
    }

    public Date getAktualDat() {
        return this.aktualDat;
    }

    public void setAktualDat(Date aktualDat) {
        this.aktualDat = aktualDat;
    }

    public Integer getAdrNr() {
        return this.adrNr;
    }

    public void setAdrNr(Integer adrNr) {
        this.adrNr = adrNr;
    }

    public String getExternalNr() {
        return this.externalNr;
    }

    public void setExternalNr(String externalNr) {
        this.externalNr = externalNr;
    }

    public Boolean getWoTog() {
        return this.woTog;
    }
    public void setWoTog(Boolean woTog) {
        this.woTog = woTog;
    }

    public Boolean getMiTog() {
        return this.miTog;
    }
    public void setMiTog(Boolean miTog) {
        this.miTog = miTog;
    }

    public Boolean getGeTog() {
        return this.geTog;
    }
    public void setGeTog(Boolean geTog) {
        this.geTog = geTog;
    }
    public Boolean getGiTog() {
        return this.giTog;
    }
    public void setGiTog(Boolean giTog) {
        this.giTog = giTog;
    }

    public Boolean getGemTog() {
        return this.gemTog;
    }
    public void setGemTog(Boolean gemTog) {
        this.gemTog = gemTog;
    }

    public Boolean getStrTog() {
        return this.strTog;
    }
    public void setStrTog(Boolean strTog) {
        this.strTog = strTog;
    }

    public Boolean getParkplatzTog() {
        return this.parkplatzTog;
    }
    public void setParkplatzTog(Boolean parkplatzTog) {
        this.parkplatzTog = parkplatzTog;
    }

    public Set<AfsNiederschlagswasser> getAfsNiederschlagswassers() {
        return this.afsNiederschlagswassers;
    }

    public void setAfsNiederschlagswassers(Set<AfsNiederschlagswasser> afsNiederschlagswassers) {
        this.afsNiederschlagswassers = afsNiederschlagswassers;
    }

    public Set<Abaverfahren> getAbaverfahrens() {
        return this.Abaverfahrens;
    }

    public void setAbaverfahrens(Set<Abaverfahren> Abaverfahrens) {
        this.Abaverfahrens = Abaverfahrens;
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
        buffer.append("nr").append("='").append(getNr()).append("' ");
        buffer.append("objekt").append("='").append(getObjekt()).append("' ");
        buffer.append("wasserrecht").append("='").append(getWasserrechts()).append("' ");
        buffer.append("erlFreiElTog").append("='").append(isErlFreiElTog()).append("' ");
        buffer.append("regenspende").append("='").append(getRegenspende()).append("' ");
        buffer.append("bemerkung").append("='").append(getBemerkung()).append("' ");
        buffer.append("regenhaeufigkeit").append("='").append(getRegenhaeufigkeit()).append("' ");
        buffer.append("regendauer").append("='").append(getRegendauer()).append("' ");
        buffer.append("grEntwGebiet").append("='").append(getGrEntwGebiet()).append("' ");
        buffer.append("dtvWert").append("='").append(getDtvWert()).append("' ");
        buffer.append("wasserableitungsstreckeOpt").append("='").append(getWasserableitungsstreckeOpt()).append("' ");
        buffer.append("nameEtwGebiet").append("='").append(getNameEtwGebiet()).append("' ");
        buffer.append("erstellDat").append("='").append(getErstellDat()).append("' ");
        buffer.append("einlBereichOpt").append("='").append(getEinlBereichOpt()).append("' ");
        buffer.append("abwbeskonNr").append("='").append(getAbwbeskonNr()).append("' ");
        buffer.append("einbauartOpt").append("='").append(getEinbauartOpt()).append("' ");
        buffer.append("aktualDat").append("='").append(getAktualDat()).append("' ");
        buffer.append("adrNr").append("='").append(getAdrNr()).append("' ");
        buffer.append("externalNr").append("='").append(getExternalNr()).append("' ");
        buffer.append("woTog").append("='").append(getWoTog()).append("' ");
        buffer.append("miTog").append("='").append(getMiTog()).append("' ");
        buffer.append("geTog").append("='").append(getGeTog()).append("' ");
        buffer.append("giTog").append("='").append(getGiTog()).append("' ");
        buffer.append("gemTog").append("='").append(getGemTog()).append("' ");
        buffer.append("strTog").append("='").append(getStrTog()).append("' ");
        buffer.append("parkplatzTog").append("='").append(getParkplatzTog()).append("' ");
        buffer.append("afsNiederschlagswassers").append("='").append(getAfsNiederschlagswassers()).append("' ");
        buffer.append("Abaverfahrens").append("='").append(getAbaverfahrens()).append("' ");
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
        if (!(other instanceof Entwaesserungsgrundstueck)) return false;
        return (this.getNr().equals(
            ((Entwaesserungsgrundstueck) other).getNr()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getNr() == null ?
            0 : this.getNr().hashCode();
        result = result * 37 + idValue;
        return result;
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>Entwaesserungsgrundstueck</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Entwaesserungsgrundstueck merge(Entwaesserungsgrundstueck detachedInstance) {
        log.debug("Merging Entwaesserungsgrundstueck instance " + detachedInstance);
        return (Entwaesserungsgrundstueck) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Entwaesserungsgrundstueck saved = Entwaesserungsgrundstueck.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Entwaesserungsgrundstueck with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Entwaesserungsgrundstueck
     */
    private void copy(Entwaesserungsgrundstueck copy) {
        this.nr = copy.getNr();
        this.objekt = copy.getObjekt();
        this.Wasserrechts = copy.getWasserrechts();
        this.erlFreiElTog = copy.isErlFreiElTog();
        this.regenspende = copy.getRegenspende();
        this.bemerkung = copy.getBemerkung();
        this.regenhaeufigkeit = copy.getRegenhaeufigkeit();
        this.regendauer = copy.getRegendauer();
        this.grEntwGebiet = copy.getGrEntwGebiet();
        this.dtvWert = copy.getDtvWert();
        this.wasserableitungsstreckeOpt = copy.getWasserableitungsstreckeOpt();
        this.nameEtwGebiet = copy.getNameEtwGebiet();
        this.erstellDat = copy.getErstellDat();
        this.einlBereichOpt = copy.getEinlBereichOpt();
        this.abwbeskonNr = copy.getAbwbeskonNr();
        this.einbauartOpt = copy.getEinbauartOpt();
        this.aktualDat = copy.getAktualDat();
        this.adrNr = copy.getAdrNr();
        this.externalNr = copy.getExternalNr();
        this.afsNiederschlagswassers = copy.getAfsNiederschlagswassers();
        this.Abaverfahrens = copy.getAbaverfahrens();
        this.woTog = copy.getWoTog();
        this.miTog = copy.getMiTog();
        this.geTog = copy.getGeTog();
        this.giTog = copy.getGiTog();
        this.gemTog = copy.getGemTog();
        this.strTog = copy.getStrTog();
        this.parkplatzTog = copy.getParkplatzTog();

    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Entwaesserungsgrundstueck detachedInstance) {
        log.debug("Deleting Entwaesserungsgrundstueck instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Entwaesserungsgrundstueck.delete(this);
    }

    /**
     * Find an <code>Entwaesserungsgrundstueck</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Entwaesserungsgrundstueck</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Entwaesserungsgrundstueck findById(long id) {
        log.debug("Getting Entwaesserungsgrundstueck instance with id: " + id);
        return (Entwaesserungsgrundstueck)
            new DatabaseAccess().get(Entwaesserungsgrundstueck.class, id);
    }

    /**
     * Get a list of all <code>Entwaesserungsgrundstueck</code>
     * @return <code>List&lt;Entwaesserungsgrundstueck&gt;</code>
     *         all <code>Entwaesserungsgrundstueck</code>
     */
    public static List<Entwaesserungsgrundstueck> getAll() {
        return DatabaseQuery.getAll(new Entwaesserungsgrundstueck());
    }

    /* Custom code goes below here! */
    public static Entwaesserungsgrundstueck findByObjektId(java.lang.Integer id) {
        Objekt objekt = (Objekt) HibernateSessionFactory.currentSession().createQuery("from Objekt where id= " + id).list().get(0);
        Set<Entwaesserungsgrundstueck> list = objekt.getEntwaesserungsgrundstuecks();
        return list.iterator().next();
    }

	public List<Object> getSortedVerfahren() {
		Set<Abaverfahren> items = getAbaverfahrens();
		List<Object> sortedVerfahren = items.stream().collect(Collectors.toList());
		return sortedVerfahren;

	}
}
