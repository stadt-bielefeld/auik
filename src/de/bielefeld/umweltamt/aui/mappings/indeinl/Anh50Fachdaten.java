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
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.Date;
import java.util.List;

/**
 * A class that represents a row in the Anh50Fachdaten database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Anh50Fachdaten  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnh50Fachdaten;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Objekt objekt;
    private Entsorger entsorger;
    private String telefon;
    private Boolean erloschen;
    private Date datumantrag;
    private String bemerkungen;
    private Date genehmigung;
    private Date wiedervorlage;
    private String gefaehrdungsklasse;
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Anh50Fachdaten() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Anh50Fachdaten(
        Integer id, Objekt objekt, boolean enabled, boolean deleted) {
        this.id = id;
        this.objekt = objekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Anh50Fachdaten(
        Integer id, Objekt objekt, Entsorger entsorger, String telefon, Boolean erloschen, Date datumantrag, String bemerkungen, Date genehmigung, Date wiedervorlage, String gefaehrdungsklasse, boolean enabled, boolean deleted) {
        this.id = id;
        this.objekt = objekt;
        this.entsorger = entsorger;
        this.telefon = telefon;
        this.erloschen = erloschen;
        this.datumantrag = datumantrag;
        this.bemerkungen = bemerkungen;
        this.genehmigung = genehmigung;
        this.wiedervorlage = wiedervorlage;
        this.gefaehrdungsklasse = gefaehrdungsklasse;
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

    public Objekt getObjekt() {
        return this.objekt;
    }

    public void setObjekt(Objekt objekt) {
        this.objekt = objekt;
    }

    public Entsorger getEntsorger() {
        return this.entsorger;
    }

    public void setEntsorger(Entsorger entsorger) {
        this.entsorger = entsorger;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Boolean getErloschen() {
        return this.erloschen;
    }

    public void setErloschen(Boolean erloschen) {
        this.erloschen = erloschen;
    }

    public Date getDatumantrag() {
        return this.datumantrag;
    }

    public void setDatumantrag(Date datumantrag) {
        this.datumantrag = datumantrag;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public Date getGenehmigung() {
        return this.genehmigung;
    }

    public void setGenehmigung(Date genehmigung) {
        this.genehmigung = genehmigung;
    }

    public Date getWiedervorlage() {
        return this.wiedervorlage;
    }

    public void setWiedervorlage(Date wiedervorlage) {
        this.wiedervorlage = wiedervorlage;
    }

    public String getGefaehrdungsklasse() {
        return this.gefaehrdungsklasse;
    }

    public void setGefaehrdungsklasse(String gefaehrdungsklasse) {
        this.gefaehrdungsklasse = gefaehrdungsklasse;
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
        buffer.append("id").append("='").append(getId()).append("' ");			
        buffer.append("objekt").append("='").append(getObjekt()).append("' ");			
        buffer.append("entsorger").append("='").append(getEntsorger()).append("' ");			
        buffer.append("telefon").append("='").append(getTelefon()).append("' ");			
        buffer.append("erloschen").append("='").append(getErloschen()).append("' ");			
        buffer.append("datumantrag").append("='").append(getDatumantrag()).append("' ");			
        buffer.append("bemerkungen").append("='").append(getBemerkungen()).append("' ");			
        buffer.append("genehmigung").append("='").append(getGenehmigung()).append("' ");			
        buffer.append("wiedervorlage").append("='").append(getWiedervorlage()).append("' ");			
        buffer.append("gefaehrdungsklasse").append("='").append(getGefaehrdungsklasse()).append("' ");			
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
        if (!(other instanceof Anh50Fachdaten)) return false;
        return (this.getId().equals(
            ((Anh50Fachdaten) other).getId()));
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
     * @return <code>Anh50Fachdaten</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Anh50Fachdaten merge(Anh50Fachdaten detachedInstance) {
        log.debug("Merging Anh50Fachdaten instance " + detachedInstance);
        return (Anh50Fachdaten) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Anh50Fachdaten saved = Anh50Fachdaten.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Anh50Fachdaten with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Anh50Fachdaten
     */
    private void copy(Anh50Fachdaten copy) {
        this.id = copy.getId();            
        this.objekt = copy.getObjekt();            
        this.entsorger = copy.getEntsorger();            
        this.telefon = copy.getTelefon();            
        this.erloschen = copy.getErloschen();            
        this.datumantrag = copy.getDatumantrag();            
        this.bemerkungen = copy.getBemerkungen();            
        this.genehmigung = copy.getGenehmigung();            
        this.wiedervorlage = copy.getWiedervorlage();            
        this.gefaehrdungsklasse = copy.getGefaehrdungsklasse();            
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Anh50Fachdaten detachedInstance) {
        log.debug("Deleting Anh50Fachdaten instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Anh50Fachdaten.delete(this);
    }

    /**
     * Find an <code>Anh50Fachdaten</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anh50Fachdaten</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anh50Fachdaten findById(java.lang.Integer id) {
        log.debug("Getting Anh50Fachdaten instance with id: " + id);
        return (Anh50Fachdaten)
            new DatabaseAccess().get(Anh50Fachdaten.class, id);
    }

    /**
     * Get a list of all <code>Anh50Fachdaten</code>
     * @return <code>List&lt;Anh50Fachdaten&gt;</code>
     *         all <code>Anh50Fachdaten</code>
     */
    public static List<Anh50Fachdaten> getAll() {
        return DatabaseQuery.getAll(new Anh50Fachdaten());
    }

    /* Custom code goes below here! */

}
