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
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the Anh49Kontrollen database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Anh49Abfuhr  implements java.io.Serializable {

    /**
	 * 
	 */
	/** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnh49Abfuhren;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Anh49Fachdaten anh49Fachdaten;
    private Date abfuhrdatum;
    private Date naechsteabfuhr;
    private String entsorger;
    private Double menge;
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Anh49Abfuhr() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Anh49Abfuhr(
        boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Anh49Abfuhr(
        Anh49Fachdaten anh49Fachdaten, Date abfuhrdatum, Date naechsteabfuhr, String entsorger, Double menge, boolean enabled, boolean deleted) {
        this.anh49Fachdaten = anh49Fachdaten;
        this.abfuhrdatum = abfuhrdatum;
        this.naechsteabfuhr = naechsteabfuhr;
        this.entsorger = entsorger;
        this.menge = menge;
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

    public Anh49Fachdaten getAnh49Fachdaten() {
        return this.anh49Fachdaten;
    }

    public void setAnh49Fachdaten(Anh49Fachdaten anh49Fachdaten) {
        this.anh49Fachdaten = anh49Fachdaten;
    }

    public Date getAbfuhrdatum() {
        return this.abfuhrdatum;
    }

    public void setAbfuhrdatum(Date abfuhrdatum) {
        this.abfuhrdatum = abfuhrdatum;
    }

    public Date getNaechsteabfuhr() {
        return this.naechsteabfuhr;
    }

    public void setNaechsteabfuhr(Date naechsteabfuhr) {
        this.naechsteabfuhr = naechsteabfuhr;
    }

    public String getEntsorger() {
        return this.entsorger;
    }

    public void setEntsorger(String entsorger) {
        this.entsorger = entsorger;
    }

    public Double getMenge() {
        return this.menge;
    }

    public void setMenge(Double menge) {
        this.menge = menge;
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
        buffer.append("anh49Fachdaten").append("='").append(getAnh49Fachdaten()).append("' ");
        buffer.append("abfuhrdatum").append("='").append(getAbfuhrdatum()).append("' ");
        buffer.append("naechsteabfuhr").append("='").append(getNaechsteabfuhr()).append("' ");
        buffer.append("entsorger").append("='").append(getEntsorger()).append("' ");
        buffer.append("menge").append("='").append(getMenge()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
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
        if (!(other instanceof Anh49Abfuhr)) return false;
        return (this.getId().equals(
            ((Anh49Abfuhr) other).getId()));
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
     * @return <code>Anh49Abfuhr</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Anh49Abfuhr merge(Anh49Abfuhr detachedInstance) {
        log.debug("Merging Anh49Abfuhren instance " + detachedInstance);
        return (Anh49Abfuhr) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Anh49Abfuhr saved = Anh49Abfuhr.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Anh49Abfuhr with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Anh49Abfuhr
     */
    private void copy(Anh49Abfuhr copy) {
        this.anh49Fachdaten = copy.getAnh49Fachdaten();
        this.abfuhrdatum = copy.getAbfuhrdatum();
        this.naechsteabfuhr = copy.getNaechsteabfuhr();
        this.entsorger = copy.getEntsorger();
        this.menge = copy.getMenge();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Anh49Abfuhr detachedInstance) {
        log.debug("Deleting Anh49Abfuhr instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Anh49Abfuhr.delete(this);
    }

    /**
     * Find an <code>Anh49Abfuhr</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anh49Kontrollen</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anh49Abfuhr findById(java.lang.Integer id) {
        log.debug("Getting Anh49Abfuhr instance with id: " + id);
        return (Anh49Abfuhr)
            new DatabaseAccess().get(Anh49Abfuhr.class, id);
    }

    /**
     * Get a list of all <code>Anh49Abfuhr</code>
     * @return <code>List&lt;Anh49Abfuhr&gt;</code>
     *         all <code>Anh49Abfuhr</code>
     */
    public static List<Anh49Abfuhr> getAll() {
        return DatabaseQuery.getAll(new Anh49Abfuhr());
    }

    /* Custom code goes below here! */

}
