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

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.Date;
import java.util.List;

/**
 * A class that represents a row in the Anlagenchrono database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Anlagenchrono  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnlagenchrono;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Fachdaten fachdaten;
    private Date datum;
    private String sachverhalt;
    private Date wv;
    private boolean enabled;
    private boolean deleted;
    private Boolean abgeschlossen;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Anlagenchrono() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Anlagenchrono(
        Fachdaten fachdaten, boolean enabled, boolean deleted) {
        this.fachdaten = fachdaten;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Anlagenchrono(
        Fachdaten fachdaten, Date datum, String sachverhalt, Date wv, boolean enabled, boolean deleted, Boolean abgeschlossen) {
        this.fachdaten = fachdaten;
        this.datum = datum;
        this.sachverhalt = sachverhalt;
        this.wv = wv;
        this.enabled = enabled;
        this.deleted = deleted;
        this.abgeschlossen = abgeschlossen;
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

    public Date getDatum() {
        return this.datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getSachverhalt() {
        return this.sachverhalt;
    }

    public void setSachverhalt(String sachverhalt) {
        this.sachverhalt = sachverhalt;
    }

    public Date getWv() {
        return this.wv;
    }

    public void setWv(Date wv) {
        this.wv = wv;
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

    public Boolean getAbgeschlossen() {
        return this.abgeschlossen;
    }

    public void setAbgeschlossen(Boolean abgeschlossen) {
        this.abgeschlossen = abgeschlossen;
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
        buffer.append("fachdaten").append("='").append(getFachdaten()).append("' ");
        buffer.append("datum").append("='").append(getDatum()).append("' ");
        buffer.append("sachverhalt").append("='").append(getSachverhalt()).append("' ");
        buffer.append("wv").append("='").append(getWv()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
        buffer.append("abgeschlossen").append("='").append(getAbgeschlossen()).append("' ");
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
        if (!(other instanceof Anlagenchrono)) return false;
        return (this.getId().equals(
            ((Anlagenchrono) other).getId()));
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
     * @return <code>Anlagenchrono</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Anlagenchrono merge(Anlagenchrono detachedInstance) {
        log.debug("Merging Anlagenchrono instance " + detachedInstance);
        return (Anlagenchrono) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Anlagenchrono saved = Anlagenchrono.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Anlagenchrono with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Anlagenchrono
     */
    private void copy(Anlagenchrono copy) {
        this.fachdaten = copy.getFachdaten();
        this.datum = copy.getDatum();
        this.sachverhalt = copy.getSachverhalt();
        this.wv = copy.getWv();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
        this.abgeschlossen = copy.getAbgeschlossen();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Anlagenchrono detachedInstance) {
        log.debug("Deleting Anlagenchrono instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Anlagenchrono.delete(this);
    }

    /**
     * Find an <code>Anlagenchrono</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anlagenchrono</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anlagenchrono findById(java.lang.Integer id) {
        log.debug("Getting Anlagenchrono instance with id: " + id);
        return (Anlagenchrono)
            new DatabaseAccess().get(Anlagenchrono.class, id);
    }

    /**
     * Get a list of all <code>Anlagenchrono</code>
     * @return <code>List&lt;Anlagenchrono&gt;</code>
     *         all <code>Anlagenchrono</code>
     */
    public static List<Anlagenchrono> getAll() {
        return DatabaseQuery.getAll(new Anlagenchrono());
    }

    /* Custom code goes below here! */

}
