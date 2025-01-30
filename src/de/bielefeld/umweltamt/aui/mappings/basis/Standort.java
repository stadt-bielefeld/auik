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

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.oberflgw.ZBetriebMassnahme;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the BasisObjekt database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Standort  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forObjekt;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Inhaber inhaber;
    private Float e32;
    private Float n32;
    private Date revidatum;
    private String revihandz;
    private String bezeichnung;
    private Serializable theGeom;
    private boolean ueberschgeb;
    private boolean enabled;
    private boolean deleted;
    private Set<Objekt> objektsForStandortid = new HashSet<Objekt>(0);
    private Set<ZBetriebMassnahme> ZBetriebMassnahmes = new HashSet<ZBetriebMassnahme>(0);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Standort() {
        // This place is intentionally left blank.
    }

    /** Full constructor */
    public Standort(
    		Inhaber inhaber, Float e32, Float n32, Date revidatum, String revihandz, String bezeichnung, boolean enabled, boolean deleted, Set<Objekt> objektsForStandortid, Set<ZBetriebMassnahme> zBetriebMassnahmes) {
    	this.inhaber = inhaber;
    	this.e32 = e32;
    	this.n32 = n32;
    	this.revihandz = revihandz;
    	this.bezeichnung = bezeichnung;
        this.enabled = enabled;
        this.deleted = deleted;
        this.objektsForStandortid = objektsForStandortid;
        this.ZBetriebMassnahmes = zBetriebMassnahmes;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Inhaber getInhaber() {
        return this.inhaber;
    }

    public void setInhaber(Inhaber inhaber) {
        this.inhaber = inhaber;
    }
	
	public String getStrasse(){
	    return inhaber.getAdresse().getStrasse();
	}
	
	public void setStrasse(String strasse){
	    this.inhaber.getAdresse().setStrasse(strasse);
	}

	public Integer getHausnr(){
	    return inhaber.getAdresse().getHausnr();
	}
	
	public void setHausnr(Integer hausnr){
	    this.inhaber.getAdresse().setHausnr(hausnr);
	}

	public String getHausnrzus(){
	    return inhaber.getAdresse().getHausnrzus();
	}
	
	public void setHausnrzus(String hausnrzus){
	    this.inhaber.getAdresse().setHausnrzus(hausnrzus);
	}

	public String getOrt(){
	    return inhaber.getAdresse().getOrt();
	}
	
	public void setOrt(String ort){
	    this.inhaber.getAdresse().setOrt(ort);
	}

	public Float getE32(){
	    return this.e32;
	}

	public void setE32(Float e32){
	    this.e32 = e32;
	}

	public Float getN32(){
	    return this.n32;
	}

	public void setN32(Float n32){
	    this.n32 = n32;
	}

	public String getRevihandz(){
	    if(this.revihandz != null){
	        return this.revihandz;
	    }
	    else{
	        return null;
	    }
	}

	public void setRevihandz(String revihandz){
	    this.revihandz = revihandz;
	}

	public Date getRevidatum(){
	    if(this.revidatum != null){
	        return this.revidatum;
	    }
	    else{
	        return null;
	    }
	}

	public void setRevidatum(Date revidatum){
	    this.revidatum = revidatum;
	}

	public Serializable getTheGeom() {
		return theGeom;
	}

	public void setTheGeom(Serializable theGeom) {
		this.theGeom = theGeom;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Boolean isUeberschgeb() {
	    return this.ueberschgeb;
	}

	public void setUeberschgeb(boolean ueberschgeb) {
	    this.ueberschgeb = ueberschgeb;
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

    public Set<Objekt> getObjektsForStandortid() {
        return this.objektsForStandortid;
    }

    public void setObjektsForStandortid(Set<Objekt> objektsForStandortid) {
        this.objektsForStandortid = objektsForStandortid;
    }

    public Set<ZBetriebMassnahme> getZBetriebMassnahmes() {
        return this.ZBetriebMassnahmes;
    }

    public void setZBetriebMassnahmes(Set<ZBetriebMassnahme> ZBetriebMassnahmes) {
        this.ZBetriebMassnahmes = ZBetriebMassnahmes;
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
        buffer.append("Inhaber").append("='").append(getInhaber()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
        buffer.append("objektsForStandortid").append("='").append(getObjektsForStandortid()).append("' ");
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
        if (!(other instanceof Standort)) return false;
        return (this.getId().equals(
            ((Standort) other).getId()));
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
    public static Standort merge(Standort detachedInstance) {
        log.debug("Merging Objekt instance " + detachedInstance);
        return (Standort) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Standort saved = Standort.merge(this);
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
    private void copy(Standort copy) {
        this.inhaber = copy.getInhaber();
        this.e32 = copy.getE32();
        this.n32 = copy.getN32();
        this.revidatum = copy.getRevidatum();
        this.revihandz = copy.getRevihandz();
        this.bezeichnung = copy.getBezeichnung();
        this.ueberschgeb = copy.isUeberschgeb();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
        this.objektsForStandortid = copy.getObjektsForStandortid();
        this.ZBetriebMassnahmes = copy.getZBetriebMassnahmes();


    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Standort detachedInstance) {
        log.debug("Deleting Objekt instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Standort.delete(this);
    }

    /**
     * Find an <code>BasisObjekt</code> instance by its primary key
     * @param id the primary key value
     * @return <code>BasisObjekt</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Standort findById(java.lang.Integer id) {
        log.debug("Getting Objekt instance with id: " + id);
        return (Standort)
            new DatabaseAccess().get(Standort.class, id);
    }

    /**
     * Get a list of all <code>BasisObjekt</code>
     * @return <code>List&lt;BasisObjekt&gt;</code>
     *         all <code>BasisObjekt</code>
     */
    public static List<Standort> getAll() {
        return DatabaseQuery.getAll(new Standort());
    }

    /* Setter and getter for lage and adresse fields*/
    //Getter

    

    //Setter

    /* Custom code goes below here! */
	public Integer getObjektid(){
		return getId();
	}

    public static Standort findByAdresseId(Integer id){

         return (Standort) new DatabaseAccess().get(Standort.class, id);

    }


	public static List <Standort> findByAdresse(Inhaber inhaber) {
		Standort standort = new Standort();
		Integer id = inhaber.getId();
		List standorte = HibernateSessionFactory
				.currentSession()
				.createQuery(
						"from Standort where inhaberid= " + id)
				.list();

		return standorte;
	}
}
