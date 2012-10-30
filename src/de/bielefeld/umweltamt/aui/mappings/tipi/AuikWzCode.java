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

package de.bielefeld.umweltamt.aui.mappings.tipi;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the AuikWzCode database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class AuikWzCode  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAuikWzCode;

    /* Primary key, foreign keys (relations) and table columns */
    private String bezeichnung;
    private DeaWzCode deaWzCode;
    private boolean inKurzAuswahl;
    private int ebene;
    private Set<BasisBetreiber> basisBetreibers = new HashSet<BasisBetreiber>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public AuikWzCode() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public AuikWzCode(
        String bezeichnung, boolean inKurzAuswahl, int ebene) {
        this.bezeichnung = bezeichnung;
        this.inKurzAuswahl = inKurzAuswahl;
        this.ebene = ebene;
    }

    /** Full constructor */
    public AuikWzCode(
        String bezeichnung, DeaWzCode deaWzCode, boolean inKurzAuswahl, int ebene, Set<BasisBetreiber> basisBetreibers) {
        this.bezeichnung = bezeichnung;
        this.deaWzCode = deaWzCode;
        this.inKurzAuswahl = inKurzAuswahl;
        this.ebene = ebene;
        this.basisBetreibers = basisBetreibers;
    }

    /* Setter and getter methods */
    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public DeaWzCode getDeaWzCode() {
        return this.deaWzCode;
    }

    public void setDeaWzCode(DeaWzCode deaWzCode) {
        this.deaWzCode = deaWzCode;
    }

    public boolean isInKurzAuswahl() {
        return this.inKurzAuswahl;
    }

    public void setInKurzAuswahl(boolean inKurzAuswahl) {
        this.inKurzAuswahl = inKurzAuswahl;
    }

    public int getEbene() {
        return this.ebene;
    }

    public void setEbene(int ebene) {
        this.ebene = ebene;
    }

    public Set<BasisBetreiber> getBasisBetreibers() {
        return this.basisBetreibers;
    }

    public void setBasisBetreibers(Set<BasisBetreiber> basisBetreibers) {
        this.basisBetreibers = basisBetreibers;
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
        return getBezeichnung();
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("bezeichnung").append("='").append(getBezeichnung()).append("' ");
        buffer.append("deaWzCode").append("='").append(getDeaWzCode()).append("' ");
        buffer.append("inKurzAuswahl").append("='").append(isInKurzAuswahl()).append("' ");
        buffer.append("ebene").append("='").append(getEbene()).append("' ");
        buffer.append("basisBetreibers").append("='").append(getBasisBetreibers()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>AuikWzCode</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static AuikWzCode merge(AuikWzCode detachedInstance) {
        log.debug("Merging AuikWzCode instance " + detachedInstance);
        return (AuikWzCode) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        return AuikWzCode.merge(this).equals(this);
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(AuikWzCode detachedInstance) {
        log.debug("Deleting AuikWzCode instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return AuikWzCode.delete(this);
    }

    /**
     * Find an <code>AuikWzCode</code> instance by its primary key
     * @param id the primary key value
     * @return <code>AuikWzCode</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static AuikWzCode findById(java.lang.String id) {
        log.debug("Getting AuikWzCode instance with id: " + id);
        return (AuikWzCode)
            new DatabaseAccess().get(AuikWzCode.class, id);
    }

    /**
     * Get a list of all <code>AuikWzCode</code>
     * @return <code>List&lt;AuikWzCode&gt;</code>
     *         all <code>AuikWzCode</code>
     */
    public static List<AuikWzCode> getAll() {
        return DatabaseQuery.getAll(new AuikWzCode());
    }

    /* Custom code goes below here! */

}
