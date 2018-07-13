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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.Date;
import java.util.List;

/**
 * A class that represents a row in the Anh49Kontrollen database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Anh49Kontrollen  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnh49Kontrollen;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Date pruefdatum;
    private Date naechstepruefung;
    private String pruefergebnis;
    private Integer anh49id;
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Anh49Kontrollen() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Anh49Kontrollen(
        boolean enabled, boolean deleted) {
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Anh49Kontrollen(
        Date pruefdatum, Date naechstepruefung, String pruefergebnis, Integer anh49id, boolean enabled, boolean deleted) {
        this.pruefdatum = pruefdatum;
        this.naechstepruefung = naechstepruefung;
        this.pruefergebnis = pruefergebnis;
        this.anh49id = anh49id;
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

    public Date getPruefdatum() {
        return this.pruefdatum;
    }

    public void setPruefdatum(Date pruefdatum) {
        this.pruefdatum = pruefdatum;
    }

    public Date getNaechstepruefung() {
        return this.naechstepruefung;
    }

    public void setNaechstepruefung(Date naechstepruefung) {
        this.naechstepruefung = naechstepruefung;
    }

    public String getPruefergebnis() {
        return this.pruefergebnis;
    }

    public void setPruefergebnis(String pruefergebnis) {
        this.pruefergebnis = pruefergebnis;
    }

    public Integer getAnh49id() {
        return this.anh49id;
    }

    public void setAnh49id(Integer anh49id) {
        this.anh49id = anh49id;
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
        buffer.append("pruefdatum").append("='").append(getPruefdatum()).append("' ");			
        buffer.append("naechstepruefung").append("='").append(getNaechstepruefung()).append("' ");			
        buffer.append("pruefergebnis").append("='").append(getPruefergebnis()).append("' ");			
        buffer.append("anh49id").append("='").append(getAnh49id()).append("' ");			
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
        if (!(other instanceof Anh49Kontrollen)) return false;
        return (this.getId().equals(
            ((Anh49Kontrollen) other).getId()));
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
     * @return <code>Anh49Kontrollen</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Anh49Kontrollen merge(Anh49Kontrollen detachedInstance) {
        log.debug("Merging Anh49Kontrollen instance " + detachedInstance);
        return (Anh49Kontrollen) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Anh49Kontrollen saved = Anh49Kontrollen.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Anh49Kontrollen with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Anh49Kontrollen
     */
    private void copy(Anh49Kontrollen copy) {
        this.pruefdatum = copy.getPruefdatum();            
        this.naechstepruefung = copy.getNaechstepruefung();            
        this.pruefergebnis = copy.getPruefergebnis();            
        this.anh49id = copy.getAnh49id();            
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Anh49Kontrollen detachedInstance) {
        log.debug("Deleting Anh49Kontrollen instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Anh49Kontrollen.delete(this);
    }

    /**
     * Find an <code>Anh49Kontrollen</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anh49Kontrollen</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anh49Kontrollen findById(java.lang.Integer id) {
        log.debug("Getting Anh49Kontrollen instance with id: " + id);
        return (Anh49Kontrollen)
            new DatabaseAccess().get(Anh49Kontrollen.class, id);
    }

    /**
     * Get a list of all <code>Anh49Kontrollen</code>
     * @return <code>List&lt;Anh49Kontrollen&gt;</code>
     *         all <code>Anh49Kontrollen</code>
     */
    public static List<Anh49Kontrollen> getAll() {
        return DatabaseQuery.getAll(new Anh49Kontrollen());
    }

    /* Custom code goes below here! */

}
