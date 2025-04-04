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

package de.bielefeld.umweltamt.aui.mappings.awsv;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.awsv.Jgs;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a row in the Jgs database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Jgs  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forJgs;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Fachdaten fachdaten;
    private Integer lagerflaeche;
    private Integer gewaesserAbstand;
    private String gewaesserName;
    private Integer brunnenAbstand;
    private String tierhaltung;
    private Boolean seitenwaende;
    private Double wandhoehe;
    private String bodenplatte;
    private Boolean ueberdachung;
    private String auffangbeh;
    private Double volumenAuffangbeh;
    private String rohrleitung;
    private Date dichtheitspruefung;
    private Boolean drainage;
    private Boolean fuellanzeiger;
    private Boolean schieber;
    private Boolean abdeckung;
    private Boolean leitungGeprueft;
    private Boolean enabled;
    private Boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Jgs() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Jgs(
        Integer id) {
        this.id = id;
    }

    /** Full constructor */
    public Jgs(
        Integer id, Fachdaten fachdaten, Integer lagerflaeche, Integer gewaesserAbstand, String gewaesserName, Integer brunnenAbstand, String tierhaltung, Boolean seitenwaende, Double wandhoehe, String bodenplatte, Boolean ueberdachung, String auffangbeh, Double volumenAuffangbeh, String rohrleitung, Date dichtheitspruefung, Boolean drainage, Boolean fuellanzeiger, Boolean schieber, Boolean abdeckung, Boolean leitungGeprueft, Boolean enabled, Boolean deleted) {
        this.id = id;
        this.fachdaten = fachdaten;
        this.lagerflaeche = lagerflaeche;
        this.gewaesserAbstand = gewaesserAbstand;
        this.gewaesserName = gewaesserName;
        this.brunnenAbstand = brunnenAbstand;
        this.tierhaltung = tierhaltung;
        this.seitenwaende = seitenwaende;
        this.wandhoehe = wandhoehe;
        this.bodenplatte = bodenplatte;
        this.ueberdachung = ueberdachung;
        this.auffangbeh = auffangbeh;
        this.volumenAuffangbeh = volumenAuffangbeh;
        this.rohrleitung = rohrleitung;
        this.dichtheitspruefung = dichtheitspruefung;
        this.drainage = drainage;
        this.fuellanzeiger = fuellanzeiger;
        this.schieber = schieber;
        this.abdeckung = abdeckung;
        this.leitungGeprueft = leitungGeprueft;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fachdaten getFachdaten() {
        return this.fachdaten;
    }

    public void setFachdaten(Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
    }

    public Integer getLagerflaeche() {
        return this.lagerflaeche;
    }

    public void setLagerflaeche(Integer lagerflaeche) {
        this.lagerflaeche = lagerflaeche;
    }

    public Integer getGewaesserAbstand() {
        return this.gewaesserAbstand;
    }

    public void setGewaesserAbstand(Integer gewaesserAbstand) {
        this.gewaesserAbstand = gewaesserAbstand;
    }

    public String getGewaesserName() {
        return this.gewaesserName;
    }

    public void setGewaesserName(String gewaesserName) {
        this.gewaesserName = gewaesserName;
    }

    public Integer getBrunnenAbstand() {
        return this.brunnenAbstand;
    }

    public void setBrunnenAbstand(Integer brunnenAbstand) {
        this.brunnenAbstand = brunnenAbstand;
    }

    public String getTierhaltung() {
        return this.tierhaltung;
    }

    public void setTierhaltung(String tierhaltung) {
        this.tierhaltung = tierhaltung;
    }

    public Boolean getSeitenwaende() {
        return this.seitenwaende;
    }

    public void setSeitenwaende(Boolean seitenwaende) {
        this.seitenwaende = seitenwaende;
    }

    public Double getWandhoehe() {
        return this.wandhoehe;
    }

    public void setWandhoehe(Double wandhoehe) {
        this.wandhoehe = wandhoehe;
    }

    public String getBodenplatte() {
        return this.bodenplatte;
    }

    public void setBodenplatte(String bodenplatte) {
        this.bodenplatte = bodenplatte;
    }

    public Boolean getUeberdachung() {
        return this.ueberdachung;
    }

    public void setUeberdachung(Boolean ueberdachung) {
        this.ueberdachung = ueberdachung;
    }

    public String getAuffangbeh() {
        return this.auffangbeh;
    }

    public void setAuffangbeh(String auffangbeh) {
        this.auffangbeh = auffangbeh;
    }

    public Double getVolumenAuffangbeh() {
        return this.volumenAuffangbeh;
    }

    public void setVolumenAuffangbeh(Double volumenAuffangbeh) {
        this.volumenAuffangbeh = volumenAuffangbeh;
    }

    public String getRohrleitung() {
        return this.rohrleitung;
    }

    public void setRohrleitung(String rohrleitung) {
        this.rohrleitung = rohrleitung;
    }

    public Date getDichtheitspruefung() {
        return this.dichtheitspruefung;
    }

    public void setDichtheitspruefung(Date dichtheitspruefung) {
        this.dichtheitspruefung = dichtheitspruefung;
    }

    public Boolean getDrainage() {
        return this.drainage;
    }

    public void setDrainage(Boolean drainage) {
        this.drainage = drainage;
    }

    public Boolean getFuellanzeiger() {
        return this.fuellanzeiger;
    }

    public void setFuellanzeiger(Boolean fuellanzeiger) {
        this.fuellanzeiger = fuellanzeiger;
    }

    public Boolean getSchieber() {
        return this.schieber;
    }

    public void setSchieber(Boolean schieber) {
        this.schieber = schieber;
    }

    public Boolean getAbdeckung() {
        return this.abdeckung;
    }

    public void setAbdeckung(Boolean abdeckung) {
        this.abdeckung = abdeckung;
    }

    public Boolean getLeitungGeprueft() {
        return this.leitungGeprueft;
    }

    public void setLeitungGeprueft(Boolean leitungGeprueft) {
        this.leitungGeprueft = leitungGeprueft;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        buffer.append("id").append("='").append(getId()).append("' ");
        buffer.append("fachdaten").append("='").append(getFachdaten()).append("' ");
        buffer.append("lagerflaeche").append("='").append(getLagerflaeche()).append("' ");
        buffer.append("gewaesserAbstand").append("='").append(getGewaesserAbstand()).append("' ");
        buffer.append("gewaesserName").append("='").append(getGewaesserName()).append("' ");
        buffer.append("brunnenAbstand").append("='").append(getBrunnenAbstand()).append("' ");
        buffer.append("tierhaltung").append("='").append(getTierhaltung()).append("' ");
        buffer.append("seitenwaende").append("='").append(getSeitenwaende()).append("' ");
        buffer.append("wandhoehe").append("='").append(getWandhoehe()).append("' ");
        buffer.append("bodenplatte").append("='").append(getBodenplatte()).append("' ");
        buffer.append("ueberdachung").append("='").append(getUeberdachung()).append("' ");
        buffer.append("auffangbeh").append("='").append(getAuffangbeh()).append("' ");
        buffer.append("volumenAuffangbeh").append("='").append(getVolumenAuffangbeh()).append("' ");
        buffer.append("rohrleitung").append("='").append(getRohrleitung()).append("' ");
        buffer.append("dichtheitspruefung").append("='").append(getDichtheitspruefung()).append("' ");
        buffer.append("drainage").append("='").append(getDrainage()).append("' ");
        buffer.append("fuellanzeiger").append("='").append(getFuellanzeiger()).append("' ");
        buffer.append("schieber").append("='").append(getSchieber()).append("' ");
        buffer.append("abdeckung").append("='").append(getAbdeckung()).append("' ");
        buffer.append("leitungGeprueft").append("='").append(getLeitungGeprueft()).append("' ");
        buffer.append("enabled").append("='").append(getEnabled()).append("' ");
        buffer.append("deleted").append("='").append(getDeleted()).append("' ");
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
        if (!(other instanceof Jgs)) return false;
        return (this.getId().equals(
            ((Jgs) other).getId()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getId() == null ?
            0 : this.getId().hashCode();
        result = result * 37 + idValue;
        return result;
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>Jgs</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Jgs merge(Jgs detachedInstance) {
        log.debug("Merging Jgs instance " + detachedInstance);
        return (Jgs) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Jgs saved = Jgs.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Jgs with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Jgs
     */
    private void copy(Jgs copy) {
        this.id = copy.getId();
        this.fachdaten = copy.getFachdaten();
        this.lagerflaeche = copy.getLagerflaeche();
        this.gewaesserAbstand = copy.getGewaesserAbstand();
        this.gewaesserName = copy.getGewaesserName();
        this.brunnenAbstand = copy.getBrunnenAbstand();
        this.tierhaltung = copy.getTierhaltung();
        this.seitenwaende = copy.getSeitenwaende();
        this.wandhoehe = copy.getWandhoehe();
        this.bodenplatte = copy.getBodenplatte();
        this.ueberdachung = copy.getUeberdachung();
        this.auffangbeh = copy.getAuffangbeh();
        this.volumenAuffangbeh = copy.getVolumenAuffangbeh();
        this.rohrleitung = copy.getRohrleitung();
        this.dichtheitspruefung = copy.getDichtheitspruefung();
        this.drainage = copy.getDrainage();
        this.fuellanzeiger = copy.getFuellanzeiger();
        this.schieber = copy.getSchieber();
        this.abdeckung = copy.getAbdeckung();
        this.leitungGeprueft = copy.getLeitungGeprueft();
        this.enabled = copy.getEnabled();
        this.deleted = copy.getDeleted();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Jgs detachedInstance) {
        log.debug("Deleting Jgs instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Jgs.delete(this);
    }

    /**
     * Find an <code>Jgs</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Jgs</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Jgs findById(java.lang.Integer id) {
        log.debug("Getting Jgs instance with id: " + id);
        return (Jgs)
            new DatabaseAccess().get(Jgs.class, id);
    }

    /**
     * Get a list of all <code>Jgs</code>
     * @return <code>List&lt;Jgs&gt;</code>
     *         all <code>Jgs</code>
     */
    public static List<Jgs> getAll() {
        return DatabaseQuery.getAll(new Jgs());
    }

    /* Custom code goes below here! */

    public static Jgs findByBehaelterid(Integer id){
        Fachdaten fd = (Fachdaten) HibernateSessionFactory.currentSession().createQuery("from Fachdaten where behaelterid= " + id).list().get(0);
        Set<Jgs> list = fd.getJgses();
		if (list.size() != 0) {
			return list.iterator().next();
		}
		return null;
    }
}
