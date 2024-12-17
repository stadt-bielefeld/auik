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

package de.bielefeld.umweltamt.aui.mappings.oberflgw;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.elka.Anfallstelle;
import de.bielefeld.umweltamt.aui.mappings.elka.Referenz;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a row in the AfsNiederschlagswasser database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class AfsNiederschlagswasser  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAfsNiederschlagswasser;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Long nr;
    private Integer origNr;
    private Anfallstelle anfallstelle;
    private Entwaesserungsgrundstueck entwaesserungsgrundstueck;
    private Integer lfdNr;
    private String bezeichnung;
    private Integer befFlaeche;
    private Integer nwHerBereichOpt;
    private BigDecimal abflussmenge;
    private boolean enabled;
    private boolean deleted;
    private Set<Referenz> referenzsForQNwAfsNr = new HashSet<Referenz>(0);
    private Set<Referenz> referenzsForZNwAfsNr = new HashSet<Referenz>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public AfsNiederschlagswasser() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public AfsNiederschlagswasser(
        long nr) {
        this.nr = nr;
    }

    /** Full constructor */
    public AfsNiederschlagswasser(
        long nr, Anfallstelle anfallstelle, Entwaesserungsgrundstueck entwaesserungsgrundstueck, Integer lfdNr, String bezeichnung, Integer befFlaeche, Integer nwHerBereichOpt, BigDecimal abflussmenge, Set<Referenz> referenzsForQNwAfsNr, Set<Referenz> referenzsForZNwAfsNr) {
        this.nr = nr;
        this.anfallstelle = anfallstelle;
        this.entwaesserungsgrundstueck = entwaesserungsgrundstueck;
        this.lfdNr = lfdNr;
        this.bezeichnung = bezeichnung;
        this.befFlaeche = befFlaeche;
        this.nwHerBereichOpt = nwHerBereichOpt;
        this.abflussmenge = abflussmenge;
        this.referenzsForQNwAfsNr = referenzsForQNwAfsNr;
        this.referenzsForZNwAfsNr = referenzsForZNwAfsNr;
    }

    /* Setter and getter methods */
    public Long getNr() {
        return this.nr;
    }

    public void setNr(Long nr) {
        this.nr = nr;
    }

    @JsonBackReference
    public Anfallstelle getAnfallstelle() {
        return this.anfallstelle;
    }

    public void setAnfallstelle(Anfallstelle anfallstelle) {
        this.anfallstelle = anfallstelle;
    }

    @JsonBackReference
    public Entwaesserungsgrundstueck getEntwaesserungsgrundstueck() {
        return this.entwaesserungsgrundstueck;
    }

    public void setEntwaesserungsgrundstueck(Entwaesserungsgrundstueck entwaesserungsgrundstueck) {
        this.entwaesserungsgrundstueck = entwaesserungsgrundstueck;
    }

    public Integer getLfdNr() {
        return this.lfdNr;
    }

    public void setLfdNr(Integer lfdNr) {
        this.lfdNr = lfdNr;
    }

    public String getBezeichnung() {
        return this.bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Integer getBefFlaeche() {
        return this.befFlaeche;
    }

    public void setBefFlaeche(Integer befFlaeche) {
        this.befFlaeche = befFlaeche;
    }

    public Integer getNwHerBereichOpt() {
        return this.nwHerBereichOpt;
    }

    public void setNwHerBereichOpt(Integer nwHerBereichOpt) {
        this.nwHerBereichOpt = nwHerBereichOpt;
    }

    public BigDecimal getAbflussmenge() {
        return this.abflussmenge;
    }

    public void setAbflussmenge(BigDecimal abflussmenge) {
        this.abflussmenge = abflussmenge;
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

    @JsonIgnore
    public Set<Referenz> getReferenzsForQNwAfsNr() {
        return this.referenzsForQNwAfsNr;
    }

    public void setReferenzsForQNwAfsNr(Set<Referenz> referenzsForQNwAfsNr) {
        this.referenzsForQNwAfsNr = referenzsForQNwAfsNr;
    }

    @JsonIgnore
    public Set<Referenz> getReferenzsForZNwAfsNr() {
        return this.referenzsForZNwAfsNr;
    }

    public void setReferenzsForZNwAfsNr(Set<Referenz> referenzsForZNwAfsNr) {
        this.referenzsForZNwAfsNr = referenzsForZNwAfsNr;
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
        buffer.append("nr").append("='").append(getNr()).append("' ");			
        buffer.append("anfallstelle").append("='").append(getAnfallstelle()).append("' ");			
        buffer.append("entwaesserungsgrundstueck").append("='").append(getEntwaesserungsgrundstueck()).append("' ");			
        buffer.append("lfdNr").append("='").append(getLfdNr()).append("' ");			
        buffer.append("bezeichnung").append("='").append(getBezeichnung()).append("' ");			
        buffer.append("befFlaeche").append("='").append(getBefFlaeche()).append("' ");			
        buffer.append("nwHerBereichOpt").append("='").append(getNwHerBereichOpt()).append("' ");			
        buffer.append("abflussmenge").append("='").append(getAbflussmenge()).append("' ");			
        buffer.append("referenzsForQNwAfsNr").append("='").append(getReferenzsForQNwAfsNr()).append("' ");			
        buffer.append("referenzsForZNwAfsNr").append("='").append(getReferenzsForZNwAfsNr()).append("' ");			
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
        if (!(other instanceof AfsNiederschlagswasser)) return false;
        return (this.getNr().equals(
            ((AfsNiederschlagswasser) other).getNr()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getNr() == null ?
            0 : this.getNr().hashCode();
        result = result * 37 + idValue;
        return result;
    }
    
    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>AfsNiederschlagswasser</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static AfsNiederschlagswasser merge(AfsNiederschlagswasser detachedInstance) {
        log.debug("Merging AfsNiederschlagswasser instance " + detachedInstance);
        return (AfsNiederschlagswasser) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        AfsNiederschlagswasser saved = AfsNiederschlagswasser.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this AfsNiederschlagswasser with its new values.<br>
     * This is meant to be used after merging!
     * @param copy AfsNiederschlagswasser
     */
    private void copy(AfsNiederschlagswasser copy) {
        this.nr = copy.getNr();            
        this.anfallstelle = copy.getAnfallstelle();            
        this.entwaesserungsgrundstueck = copy.getEntwaesserungsgrundstueck();            
        this.lfdNr = copy.getLfdNr();            
        this.bezeichnung = copy.getBezeichnung();            
        this.befFlaeche = copy.getBefFlaeche();            
        this.nwHerBereichOpt = copy.getNwHerBereichOpt();            
        this.abflussmenge = copy.getAbflussmenge();            
        this.referenzsForQNwAfsNr = copy.getReferenzsForQNwAfsNr();            
        this.referenzsForZNwAfsNr = copy.getReferenzsForZNwAfsNr();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(AfsNiederschlagswasser detachedInstance) {
        log.debug("Deleting AfsNiederschlagswasser instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return AfsNiederschlagswasser.delete(this);
    }

    /**
     * Find an <code>AfsNiederschlagswasser</code> instance by its primary key
     * @param id the primary key value
     * @return <code>AfsNiederschlagswasser</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static AfsNiederschlagswasser findById(Integer id) {
        log.debug("Getting AfsNiederschlagswasser instance with id: " + id);
        return (AfsNiederschlagswasser)
            new DatabaseAccess().get(AfsNiederschlagswasser.class, id);
    }

    /**
     * Get a list of all <code>AfsNiederschlagswasser</code>
     * @return <code>List&lt;AfsNiederschlagswasser&gt;</code>
     *         all <code>AfsNiederschlagswasser</code>
     */
    public static List<AfsNiederschlagswasser> getAll() {
        return DatabaseQuery.getAll(new AfsNiederschlagswasser());
    }

    /* Custom code goes below here! */

    @JsonIgnore
    public Integer getOrigNr() {
        return origNr;
    }

    public void setOrigNr(Integer origNr) {
        this.origNr = origNr;
    }

}
