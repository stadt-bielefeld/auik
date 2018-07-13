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

package de.bielefeld.umweltamt.aui.mappings.atl;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.List;

/**
 * A class that represents a row in the ViewSielhautqbProbenahmenRoutine database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class ViewSielhautqbProbenahmenRoutine  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forViewSielhautqbProbenahmenRoutine;
    
    /* Primary key, foreign keys (relations) and table columns */
    private ViewSielhautqbProbenahmenRoutineId id;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public ViewSielhautqbProbenahmenRoutine() {
        // This place is intentionally left blank.
    }


    /** Full constructor */
    public ViewSielhautqbProbenahmenRoutine(
        ViewSielhautqbProbenahmenRoutineId id) {
        this.id = id;
    }

    /* Setter and getter methods */
    public ViewSielhautqbProbenahmenRoutineId getId() {
        return this.id;
    }

    public void setId(ViewSielhautqbProbenahmenRoutineId id) {
        this.id = id;
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
        if (!(other instanceof ViewSielhautqbProbenahmenRoutine)) return false;
        return (this.getId().equals(
            ((ViewSielhautqbProbenahmenRoutine) other).getId()));
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
     * @return <code>ViewSielhautqbProbenahmenRoutine</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static ViewSielhautqbProbenahmenRoutine merge(ViewSielhautqbProbenahmenRoutine detachedInstance) {
        log.debug("Merging ViewSielhautqbProbenahmenRoutine instance " + detachedInstance);
        return (ViewSielhautqbProbenahmenRoutine) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        ViewSielhautqbProbenahmenRoutine saved = ViewSielhautqbProbenahmenRoutine.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this ViewSielhautqbProbenahmenRoutine with its new values.<br>
     * This is meant to be used after merging!
     * @param copy ViewSielhautqbProbenahmenRoutine
     */
    private void copy(ViewSielhautqbProbenahmenRoutine copy) {
        this.id = copy.getId();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(ViewSielhautqbProbenahmenRoutine detachedInstance) {
        log.debug("Deleting ViewSielhautqbProbenahmenRoutine instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return ViewSielhautqbProbenahmenRoutine.delete(this);
    }

    /**
     * Find an <code>ViewSielhautqbProbenahmenRoutine</code> instance by its primary key
     * @param id the primary key value
     * @return <code>ViewSielhautqbProbenahmenRoutine</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static ViewSielhautqbProbenahmenRoutine findById(de.bielefeld.umweltamt.aui.mappings.atl.ViewSielhautqbProbenahmenRoutineId id) {
        log.debug("Getting ViewSielhautqbProbenahmenRoutine instance with id: " + id);
        return (ViewSielhautqbProbenahmenRoutine)
            new DatabaseAccess().get(ViewSielhautqbProbenahmenRoutine.class, id);
    }

    /**
     * Get a list of all <code>ViewSielhautqbProbenahmenRoutine</code>
     * @return <code>List&lt;ViewSielhautqbProbenahmenRoutine&gt;</code>
     *         all <code>ViewSielhautqbProbenahmenRoutine</code>
     */
    public static List<ViewSielhautqbProbenahmenRoutine> getAll() {
        return DatabaseQuery.getAll(new ViewSielhautqbProbenahmenRoutine());
    }

    /* Custom code goes below here! */

}
