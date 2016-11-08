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


package de.bielefeld.umweltamt.aui.mappings.basis;

import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the BasisObjekt database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class BasisMapAdresseLage  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forBasisObjekt;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private BasisAdresse basisAdresse;
    private BasisLage basisLage;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public BasisMapAdresseLage() {
        // This place is intentionally left blank.
    }

    /** Full constructor */
    public BasisMapAdresseLage(
        BasisAdresse basisAdresse, BasisLage basisLage) {
        this.basisAdresse = basisAdresse;
        this.basisLage = basisLage;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasisAdresse getBasisAdresse() {
        return this.basisAdresse;
    }

    public void setBasisAdresse(BasisAdresse basisAdresse) {
        this.basisAdresse = basisAdresse;
    }

    public BasisLage getBasisLage() {
        return this.basisLage;
    }

    public void setBasisLage(BasisLage basisLage) {
        this.basisLage = basisLage;
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
//    @Override
//    public String toString() {
//        return DatabaseClassToString.toStringForClass(this); 
//    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("basisAdresse").append("='").append(getBasisAdresse()).append("' ");			
        buffer.append("basisLage").append("='").append(getBasisLage()).append("' ");			
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
        if (!(other instanceof BasisMapAdresseLage)) return false;
        return (this.getId().equals(
            ((BasisMapAdresseLage) other).getId()));
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
     * @return <code>BasisObjekt</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static BasisMapAdresseLage merge(BasisMapAdresseLage detachedInstance) {
        log.debug("Merging BasisObjekt instance " + detachedInstance);
        return (BasisMapAdresseLage) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        BasisMapAdresseLage saved = BasisMapAdresseLage.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this BasisObjekt with its new values.<br>
     * This is meant to be used after merging!
     * @param copy BasisObjekt
     */
    private void copy(BasisMapAdresseLage copy) {
        this.basisAdresse = copy.getBasisAdresse();            
        this.basisLage = copy.getBasisLage();            
            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(BasisMapAdresseLage detachedInstance) {
        log.debug("Deleting BasisObjekt instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return BasisMapAdresseLage.delete(this);
    }

    /**
     * Find an <code>BasisObjekt</code> instance by its primary key
     * @param id the primary key value
     * @return <code>BasisObjekt</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static BasisMapAdresseLage findById(java.lang.Integer id) {
        log.debug("Getting BasisObjekt instance with id: " + id);
        return (BasisMapAdresseLage)
            new DatabaseAccess().get(BasisMapAdresseLage.class, id);
    }

    /**
     * Get a list of all <code>BasisObjekt</code>
     * @return <code>List&lt;BasisObjekt&gt;</code>
     *         all <code>BasisObjekt</code>
     */
    public static List<BasisMapAdresseLage> getAll() {
        return DatabaseQuery.getAll(new BasisMapAdresseLage());
    }

    /* Custom code goes below here! */
	public Integer getObjektid(){
		return getId();
	}

    public static BasisMapAdresseLage findByLageId(Integer id){
        List<BasisMapAdresseLage> all = BasisMapAdresseLage.getAll();
        for(BasisMapAdresseLage i : all){
            log.debug("Comparing " + i.getBasisLage().getId() + " " + id);
            if(i.getBasisLage().getId().equals(id)){
                log.debug("Returning Objekt: " + i.getId());
                return (BasisMapAdresseLage) new DatabaseAccess().get(BasisMapAdresseLage.class, i.getId());
            }
        }
        return null;
    }
}
