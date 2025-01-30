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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a row in the Gebuehrenarten database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Gebuehrenarten  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forGebuehrenarten;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private String gebuehrenart;
    private boolean enabled;
    private boolean deleted;
    private Set<Verwaltungsgebuehren> verwaltungsgebuehrens = new HashSet<Verwaltungsgebuehren>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Gebuehrenarten() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Gebuehrenarten(
        Integer id, boolean enabled, boolean deleted) {
        this.id = id;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Gebuehrenarten(
        Integer id, String gebuehrenart, boolean enabled, boolean deleted, Set<Verwaltungsgebuehren> verwaltungsgebuehrens) {
        this.id = id;
        this.gebuehrenart = gebuehrenart;
        this.enabled = enabled;
        this.deleted = deleted;
        this.verwaltungsgebuehrens = verwaltungsgebuehrens;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGebuehrenart() {
        return this.gebuehrenart;
    }

    public void setGebuehrenart(String gebuehrenart) {
        this.gebuehrenart = gebuehrenart;
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

    public Set<Verwaltungsgebuehren> getVerwaltungsgebuehrens() {
        return this.verwaltungsgebuehrens;
    }

    public void setVerwaltungsgebuehrens(Set<Verwaltungsgebuehren> verwaltungsgebuehrens) {
        this.verwaltungsgebuehrens = verwaltungsgebuehrens;
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
        buffer.append("gebuehrenart").append("='").append(getGebuehrenart()).append("' ");			
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");			
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");			
        buffer.append("verwaltungsgebuehrens").append("='").append(getVerwaltungsgebuehrens()).append("' ");			
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
        if (!(other instanceof Gebuehrenarten)) return false;
        return (this.getId().equals(
            ((Gebuehrenarten) other).getId()));
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
     * @return <code>Gebuehrenarten</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Gebuehrenarten merge(Gebuehrenarten detachedInstance) {
        log.debug("Merging Gebuehrenarten instance " + detachedInstance);
        return (Gebuehrenarten) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Gebuehrenarten saved = Gebuehrenarten.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Gebuehrenarten with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Gebuehrenarten
     */
    private void copy(Gebuehrenarten copy) {
        this.id = copy.getId();            
        this.gebuehrenart = copy.getGebuehrenart();            
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();            
        this.verwaltungsgebuehrens = copy.getVerwaltungsgebuehrens();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Gebuehrenarten detachedInstance) {
        log.debug("Deleting Gebuehrenarten instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Gebuehrenarten.delete(this);
    }

    /**
     * Find an <code>Gebuehrenarten</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Gebuehrenarten</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Gebuehrenarten findById(java.lang.Integer id) {
        log.debug("Getting Gebuehrenarten instance with id: " + id);
        return (Gebuehrenarten)
            new DatabaseAccess().get(Gebuehrenarten.class, id);
    }

    /**
     * Get a list of all <code>Gebuehrenarten</code>
     * @return <code>List&lt;Gebuehrenarten&gt;</code>
     *         all <code>Gebuehrenarten</code>
     */
    public static List<Gebuehrenarten> getAll() {
        return DatabaseQuery.getAll(new Gebuehrenarten());
    }

	public String toGuiString() {
		// TODO Auto-generated method stub
		return null;
	}

    /* Custom code goes below here! */

}
