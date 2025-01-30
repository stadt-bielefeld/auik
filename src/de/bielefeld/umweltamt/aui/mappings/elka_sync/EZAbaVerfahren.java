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

package de.bielefeld.umweltamt.aui.mappings.elka_sync;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.elka.Aba;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.elka.Wasserrecht;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.List;

/**
 * A class that represents a row in the ZAbaWasserrecht database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class EZAbaVerfahren  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forZAbaWasserrecht;
    
    /* Primary key, foreign keys (relations) and table columns */
    private EZAbaVerfahrenId id;
    private Aba aba;
    private Abaverfahren abwasbehverf;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public EZAbaVerfahren() {
        // This place is intentionally left blank.
    }


    /** Full constructor */
    public EZAbaVerfahren(
        EZAbaVerfahrenId id, Aba aba, Abaverfahren abwasbehverf) {
        this.id = id;
        this.aba = aba;
        this.abwasbehverf = abwasbehverf;
    }

    /* Setter and getter methods */
    public EZAbaVerfahrenId getId() {
        return this.id;
    }

    public void setId(EZAbaVerfahrenId id) {
        this.id = id;
    }

    public Aba getAba() {
        return this.aba;
    }

    public void setAba(Aba aba) {
        this.aba = aba;
    }

    public Abaverfahren getAbwasbehverf() {
        return this.abwasbehverf;
    }

    public void setAbwasbehverf(Abaverfahren abwasbehverf) {
        this.abwasbehverf = abwasbehverf;
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
        //TODO
        return "";
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("id").append("='").append(getId()).append("' ");			
        buffer.append("aba").append("='").append(getAba()).append("' ");			
        buffer.append("wasserrecht").append("='").append(getAbwasbehverf()).append("' ");			
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
        if (!(other instanceof EZAbaVerfahren)) return false;
        return (this.getId().equals(
            ((EZAbaVerfahren) other).getId()));
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
     * @return <code>ZAbaWasserrecht</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static EZAbaVerfahren merge(EZAbaVerfahren detachedInstance) {
        log.debug("Merging ZAbaWasserrecht instance " + detachedInstance);
        return (EZAbaVerfahren) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        EZAbaVerfahren saved = EZAbaVerfahren.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this ZAbaWasserrecht with its new values.<br>
     * This is meant to be used after merging!
     * @param copy ZAbaWasserrecht
     */
    private void copy(EZAbaVerfahren copy) {
        this.id = copy.getId();            
        this.aba = copy.getAba();            
        this.abwasbehverf = copy.getAbwasbehverf();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(EZAbaVerfahren detachedInstance) {
        log.debug("Deleting ZAbaWasserrecht instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return EZAbaVerfahren.delete(this);
    }

    /**
     * Find an <code>ZAbaWasserrecht</code> instance by its primary key
     * @param id the primary key value
     * @return <code>ZAbaWasserrecht</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static EZAbaVerfahren findById(de.bielefeld.umweltamt.aui.mappings.elka.ZAbaWasserrechtId id) {
        log.debug("Getting ZAbaWasserrecht instance with id: " + id);
        return (EZAbaVerfahren)
            new DatabaseAccess().get(EZAbaVerfahren.class, id);
    }

    /**
     * Get a list of all <code>ZAbaWasserrecht</code>
     * @return <code>List&lt;ZAbaWasserrecht&gt;</code>
     *         all <code>ZAbaWasserrecht</code>
     */
    public static List<EZAbaVerfahren> getAll() {
        return DatabaseQuery.getAll(new EZAbaVerfahren());
    }

    /* Custom code goes below here! */

}
