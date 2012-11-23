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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the AnhEntsorger database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class AnhEntsorger  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnhEntsorger;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private String entsorger;
    private String strasse;
    private Integer hausnr;
    private String plz;
    private String ort;
    private String ansprechpartner;
    private String telefon;
    private boolean enabled;
    private boolean deleted;
    private Set<Anh50Fachdaten> anh50Fachdatens = new HashSet<Anh50Fachdaten>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public AnhEntsorger() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public AnhEntsorger(
        Integer id, boolean enabled, boolean deleted) {
        this.id = id;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public AnhEntsorger(
        Integer id, String entsorger, String strasse, Integer hausnr, String plz, String ort, String ansprechpartner, String telefon, boolean enabled, boolean deleted, Set<Anh50Fachdaten> anh50Fachdatens) {
        this.id = id;
        this.entsorger = entsorger;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.plz = plz;
        this.ort = ort;
        this.ansprechpartner = ansprechpartner;
        this.telefon = telefon;
        this.enabled = enabled;
        this.deleted = deleted;
        this.anh50Fachdatens = anh50Fachdatens;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntsorger() {
        return this.entsorger;
    }

    public void setEntsorger(String entsorger) {
        this.entsorger = entsorger;
    }

    public String getStrasse() {
        return this.strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public Integer getHausnr() {
        return this.hausnr;
    }

    public void setHausnr(Integer hausnr) {
        this.hausnr = hausnr;
    }

    public String getPlz() {
        return this.plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return this.ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getAnsprechpartner() {
        return this.ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

    public Set<Anh50Fachdaten> getAnh50Fachdatens() {
        return this.anh50Fachdatens;
    }

    public void setAnh50Fachdatens(Set<Anh50Fachdaten> anh50Fachdatens) {
        this.anh50Fachdatens = anh50Fachdatens;
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
     * Get a string representation for the gui
     * @return String
     */
    public String toGuiString() {
        return getEntsorger();
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("id").append("='").append(getId()).append("' ");
        buffer.append("entsorger").append("='").append(getEntsorger()).append("' ");
        buffer.append("strasse").append("='").append(getStrasse()).append("' ");
        buffer.append("hausnr").append("='").append(getHausnr()).append("' ");
        buffer.append("plz").append("='").append(getPlz()).append("' ");
        buffer.append("ort").append("='").append(getOrt()).append("' ");
        buffer.append("ansprechpartner").append("='").append(getAnsprechpartner()).append("' ");
        buffer.append("telefon").append("='").append(getTelefon()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
        buffer.append("anh50Fachdatens").append("='").append(getAnh50Fachdatens()).append("' ");
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
        if (!(other instanceof AnhEntsorger)) return false;
        return (this.getId().equals(
            ((AnhEntsorger) other).getId()));
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>AnhEntsorger</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static AnhEntsorger merge(AnhEntsorger detachedInstance) {
        log.debug("Merging AnhEntsorger instance " + detachedInstance);
        return (AnhEntsorger) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        AnhEntsorger saved = AnhEntsorger.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this AnhEntsorger with its new values.<br>
     * This is meant to be used after merging!
     * @param copy AnhEntsorger
     */
    private void copy(AnhEntsorger copy) {
        this.id = copy.getId();
        this.entsorger = copy.getEntsorger();
        this.strasse = copy.getStrasse();
        this.hausnr = copy.getHausnr();
        this.plz = copy.getPlz();
        this.ort = copy.getOrt();
        this.ansprechpartner = copy.getAnsprechpartner();
        this.telefon = copy.getTelefon();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
        this.anh50Fachdatens = copy.getAnh50Fachdatens();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(AnhEntsorger detachedInstance) {
        log.debug("Deleting AnhEntsorger instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return AnhEntsorger.delete(this);
    }

    /**
     * Find an <code>AnhEntsorger</code> instance by its primary key
     * @param id the primary key value
     * @return <code>AnhEntsorger</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static AnhEntsorger findById(java.lang.Integer id) {
        log.debug("Getting AnhEntsorger instance with id: " + id);
        return (AnhEntsorger)
            new DatabaseAccess().get(AnhEntsorger.class, id);
    }

    /**
     * Get a list of all <code>AnhEntsorger</code>
     * @return <code>List&lt;AnhEntsorger&gt;</code>
     *         all <code>AnhEntsorger</code>
     */
    public static List<AnhEntsorger> getAll() {
        return DatabaseQuery.getAll(new AnhEntsorger());
    }

    /* Custom code goes below here! */

}
