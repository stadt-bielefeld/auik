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

package de.bielefeld.umweltamt.aui.mappings.elka;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.Date;
import java.util.List;

/**
 * A class that represents a row in the Anhang database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Anhang  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnhang;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer slNr;
    private Integer anhMaSlNr;
    private String anhangId;
    private Date anhGueltigVon;
    private Date anhGueltigBis;
    private String anhRegelwerk;
    private String anhText;
    private String herkunft;
    private Date aktualDat;
    private Date erstellDat;
    private Date zeitstempel;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Anhang() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Anhang(
        Integer slNr) {
        this.slNr = slNr;
    }

    /** Full constructor */
    public Anhang(
        Integer slNr, Integer anhMaSlNr, Date anhGueltigVon, Date anhGueltigBis, String anhRegelwerk, String anhText, Date aktualDat, Date erstellDat, String anhangId, String herkunft, Date zeitstempel) {
        this.slNr = slNr;
        this.anhangId = anhangId;
        this.anhMaSlNr = anhMaSlNr;
        this.anhGueltigVon = anhGueltigVon;
        this.anhGueltigBis = anhGueltigBis;
        this.anhRegelwerk= anhRegelwerk;
        this.anhText = anhText;
        this.herkunft = herkunft;
        this.aktualDat = aktualDat;
        this.erstellDat = erstellDat;
        this.zeitstempel = zeitstempel;
    }

    /* Setter and getter methods */
    public Integer getSlNr() {
        return this.slNr;
    }

    public void setSlNr(Integer slNr) {
        this.slNr = slNr;
    }

    public Integer getAnhMaSlNr() {
        return this.anhMaSlNr;
    }

    public void setAnhMaSlNr(Integer anhMaSlNr) {
        this.anhMaSlNr = anhMaSlNr;
    }

    public String getAnhangId() {
        return this.anhangId;
    }

    public void setAnhangId(String anhangId) {
        this.anhangId = anhangId;
    }

    public Date getAnhGueltigVon() {
        return this.anhGueltigVon;
    }

    public void setAnhGueltigVon(Date anhGueltigVon) {
        this.anhGueltigVon = anhGueltigVon;
    }


    public Date getAnhGueltigBis() {
        return this.anhGueltigBis;
    }

    public void setAnhGueltigBis(Date anhGueltigBis) {
        this.anhGueltigBis = anhGueltigBis;
    }


    public Date getAktualDat() {
        return this.aktualDat;
    }

    public void setAktualDat(Date aktualDat) {
        this.aktualDat = aktualDat;
    }

    public Date getErstellDat() {
        return this.erstellDat;
    }

    public void setErstellDat(Date erstellDat) {
        this.erstellDat = erstellDat;
    }

    public String getAnhRegelwerk() {
        return this.anhRegelwerk;
    }

    public void setAnhRegelwerk(String anhRegelwerk) {
        this.anhRegelwerk = anhRegelwerk;
    }

    public String getAnhText() {
        return this.anhText;
    }

    public void setAnhText(String anhText) {
        this.anhText = anhText;
    }

    public String getHerkunft() {
        return this.herkunft;
    }

    public void setHerkunft(String herkunft) {
        this.herkunft = herkunft;
    }

    public Date getZeitstempel() {
        return this.zeitstempel;
    }

    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
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
        buffer.append("slNr").append("='").append(getSlNr()).append("' ");				
        buffer.append("anhangId").append("='").append(getAnhangId()).append("' ");			
        buffer.append("anhGueltigVon").append("='").append(getAnhGueltigVon()).append("' ");			
        buffer.append("anhGueltigBis").append("='").append(getAnhGueltigBis()).append("' ");			
        buffer.append("anhRegelwerk").append("='").append(getAnhRegelwerk()).append("' ");			
        buffer.append("anhText").append("='").append(getAnhText()).append("' ");			
        buffer.append("herkunft").append("='").append(getHerkunft()).append("' ");				
        buffer.append("anhMaSlNr").append("='").append(getAnhMaSlNr()).append("' ");			
        buffer.append("aktualDat").append("='").append(getAktualDat()).append("' ");			
        buffer.append("erstellDat").append("='").append(getErstellDat()).append("' ");			
        buffer.append("zeitstempel").append("='").append(getZeitstempel()).append("' ");			
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
        if (!(other instanceof Anhang)) return false;
        return (this.getSlNr().equals(
            ((Anhang) other).getSlNr()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getSlNr() == null ?
            0 : this.getSlNr().hashCode();
        result = result * 37 + idValue;
        return result;
    }
    
    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>Anhang</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Anhang merge(Anhang detachedInstance) {
        log.debug("Merging Anhang instance " + detachedInstance);
        return (Anhang) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Anhang saved = Anhang.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Anhang with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Anhang
     */
    private void copy(Anhang copy) {
        this.slNr = copy.getSlNr();                       
        this.anhMaSlNr = copy.getAnhMaSlNr();            
        this.anhangId = copy.getAnhangId();               
        this.anhGueltigVon = copy.getAnhGueltigVon();                
        this.anhGueltigBis = copy.getAnhGueltigBis();                
        this.anhRegelwerk = copy.getAnhRegelwerk();                
        this.anhText = copy.getAnhText();                
        this.herkunft = copy.getHerkunft();           
        this.aktualDat = copy.getAktualDat();            
        this.erstellDat = copy.getErstellDat();               
        this.zeitstempel = copy.getZeitstempel(); 
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Anhang detachedInstance) {
        log.debug("Deleting Anhang instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Anhang.delete(this);
    }

    /**
     * Find an <code>Anhang</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anhang</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anhang findById(java.lang.Integer id) {
        log.debug("Getting Anhang instance with id: " + id);
        return (Anhang)
            new DatabaseAccess().get(Anhang.class, id);
    }

    /**
     * Find an <code>Anhang</code> instance by its anhang_id
     * @param anhang_id
     * @return <code>Anhang</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anhang findByAnhangId(java.lang.String anhangid) {

		String query = "FROM Anhang WHERE anhang_id = '" + anhangid + "' " + 
				"AND anh_gueltig_bis IS NULL";

		Anhang anhg = (Anhang) HibernateSessionFactory.currentSession().createQuery(query).list().get(0);
		
		return anhg;

    }

    /**
     * Get a list of all <code>Anhang</code>
     * @return <code>List&lt;Anhang&gt;</code>
     *         all <code>Anhang</code>
     */
    public static List<Anhang> getAll() {
        return DatabaseQuery.allActiveAnhangs();
    }

	public String toGuiString() {
		return getAnhangId() + " - " + getAnhText();
	}

    /* Custom code goes below here! */

}
