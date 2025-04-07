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

package de.bielefeld.umweltamt.aui.mappings.basis;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a row in the Adresse database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Adresse  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAdresse;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Standortgghwsg standortgghwsg;
    private Wassereinzugsgebiet wassereinzugsgebiet;
    private Gemarkung gemarkung;
    private String strasse;
    private Integer hausnr;
    private String hausnrzus;
    private String plzzs;
    private String plz;
    private String ort;
    private String flur;
    private String flurstueck;
    private String entgebid;
    private String strasseeigent;
    private String sachbe33rav;
    private String sachbe33hee;
    private Boolean ueberschgeb;
    private String bemerkungen;
    private Date revidatum;
    private String revihandz;
    private boolean enabled;
    private boolean deleted;
    private Date erstellDat;
    private Integer iglId;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Adresse() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Adresse(
        Integer id, boolean enabled, boolean deleted) {
        this.id = id;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Adresse(
            Integer id, Standortgghwsg standortgghwsg, Wassereinzugsgebiet wassereinzugsgebiet, Gemarkung gemarkung,
            String strasse, Integer hausnr, String hausnrzus, String plzzs, String plz, String ort,
            String flur, String flurstueck, String entgebid, String strasseeigent,
            String sachbe33rav, String sachbe33hee, Boolean ueberschgeb,
            String bemerkungen, Date revidatum,
            String revihandz, boolean enabled,
            boolean deleted, Date erstellDat, Integer iglId,
            Set<Inhaber> inhabers) {
        this.id = id;
        this.strasse = strasse;
        this.hausnr = hausnr;
        this.hausnrzus = hausnrzus;
        this.plzzs = plzzs;
        this.plz = plz;
        this.ort = ort;
    	this.standortgghwsg = standortgghwsg;
    	this.wassereinzugsgebiet = wassereinzugsgebiet;
    	this.gemarkung = gemarkung;
        this.bemerkungen = bemerkungen;
    	this.flur = flur;
    	this.flurstueck = flurstueck;
    	this.entgebid = entgebid;
    	this.strasseeigent = strasseeigent;
    	this.sachbe33rav = sachbe33rav;
    	this.sachbe33hee = sachbe33hee;
    	this.ueberschgeb = ueberschgeb;
        this.revidatum = revidatum;
        this.revihandz = revihandz;
        this.enabled = enabled;
        this.deleted = deleted;
        this.erstellDat = erstellDat;
        this.iglId = iglId;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Gemarkung getGemarkung(){
	    return this.gemarkung;
	}

	public void setGemarkung(Gemarkung gemarkung){
	    this.gemarkung = gemarkung;
	}

	public String getEntgebid(){
	    return this.entgebid;
	}

	public void setEntgebid(String entgebid){
	    this.entgebid = entgebid;
	}

	public Standortgghwsg getStandortgghwsg(){
	    return this.standortgghwsg;
	}

	public void setStandortgghwsg(Standortgghwsg standortgghwsg){
	    this.standortgghwsg  = standortgghwsg;
	}

	public Wassereinzugsgebiet getWassereinzugsgebiet(){
	    return this.wassereinzugsgebiet;
	}

	public void setWassereinzugsgebiet(Wassereinzugsgebiet wassereinzugsgebiet){
	    this.wassereinzugsgebiet = wassereinzugsgebiet;
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

    public String getHausnrzus() {
        return this.hausnrzus;
    }

    public void setHausnrzus(String hausnrzus) {
        this.hausnrzus = hausnrzus;
    }

    public String getPlzzs() {
        return this.plzzs;
    }

    public void setPlzzs(String plzzs) {
        this.plzzs = plzzs;
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

	public String getFlur(){
	    return this.flur;
	}

	public void setFlur(String flur){
	    this.flur = flur;
	}

	public String getFlurstueck(){
	    return this.flurstueck;
	}

	public void setFlurstueck(String flurstueck){
	    this.flurstueck = flurstueck;
	}

	public String getSachbe33rav(){
	    return this.sachbe33rav;
	}

	public void setSachbe33rav(String sachbe33rav){
		this.sachbe33rav = sachbe33rav;
	}

	public String getSachbe33hee() {
		return sachbe33hee;
	}

	public void setSachbe33hee(String sachbe33hee) {
		this.sachbe33hee = sachbe33hee;
	}

	public String getStrasseeigent() {
	    return this.strasseeigent;
	}

	public void setStrasseeigent(String strasseeigent) {
	    this.strasseeigent = strasseeigent;
	}

	public Boolean isUeberschgeb() {
	    return this.ueberschgeb;
	}

	public void setUeberschgeb(Boolean ueberschgeb) {
	    this.ueberschgeb = ueberschgeb;
	}


    public String getBemerkungen() {
        return this.bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public Date getRevidatum() {
        return this.revidatum;
    }

    public void setRevidatum(Date revidatum) {
        this.revidatum = revidatum;
    }

    public String getRevihandz() {
        return this.revihandz;
    }

    public void setRevihandz(String revihandz) {
        this.revihandz = revihandz;
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

    public Date getErstellDat() {
        return this.erstellDat;
    }

    public void setErstellDat(Date erstellDat) {
        this.erstellDat = erstellDat;
    }

    public Integer getIglId() {
        return this.iglId;
    }

    public void setIglId(Integer iglId) {
        this.iglId = iglId;
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
        buffer.append("hausnr").append("='").append(getHausnr()).append("' ");			
        buffer.append("hausnrzus").append("='").append(getHausnrzus()).append("' ");			
        buffer.append("plzzs").append("='").append(getPlzzs()).append("' ");			
        buffer.append("plz").append("='").append(getPlz()).append("' ");			
        buffer.append("ort").append("='").append(getOrt()).append("' ");					
        buffer.append("bemerkungen").append("='").append(getBemerkungen()).append("' ");			
        buffer.append("revidatum").append("='").append(getRevidatum()).append("' ");			
        buffer.append("revihandz").append("='").append(getRevihandz()).append("' ");				
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");			
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");					
        buffer.append("erstellDat").append("='").append(getErstellDat()).append("' ");				
        buffer.append("iglId").append("='").append(getIglId()).append("' ");			
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
        if (!(other instanceof Adresse)) return false;
        return (this.getId().equals(
            ((Adresse) other).getId()));
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
     * @return <code>Adresse</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Adresse merge(Adresse detachedInstance) {
        log.debug("Merging Adresse instance " + detachedInstance);
        return (Adresse) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Adresse saved = Adresse.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Adresse with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Adresse
     */
    private void copy(Adresse copy) {
        this.id = copy.getId();
        this.standortgghwsg = copy.getStandortgghwsg();
        this.wassereinzugsgebiet = copy.getWassereinzugsgebiet();
        this.gemarkung = copy.getGemarkung();
        this.strasse = copy.getStrasse();
        this.hausnr = copy.getHausnr();
        this.hausnrzus = copy.getHausnrzus();
        this.plzzs = copy.getPlzzs();
        this.plz = copy.getPlz();
        this.ort = copy.getOrt();
        this.flur = copy.getFlur();
        this.flurstueck = copy.getFlurstueck();
        this.entgebid = copy.getEntgebid();
        this.strasseeigent = copy.getStrasseeigent();
        this.revidatum = copy.getRevidatum();
        this.revihandz = copy.getRevihandz();
        this.sachbe33rav = copy.getSachbe33rav();
        this.sachbe33hee = copy.getSachbe33hee();
        this.ueberschgeb = copy.isUeberschgeb();                  
        this.bemerkungen = copy.getBemerkungen();            
        this.revidatum = copy.getRevidatum();            
        this.revihandz = copy.getRevihandz();                   
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();                     
        this.erstellDat = copy.getErstellDat();           
        this.iglId = copy.getIglId();                     
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Adresse detachedInstance) {
        log.debug("Deleting Adresse instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Adresse.delete(this);
    }

    /**
     * Find an <code>Adresse</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Adresse</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Adresse findById(java.lang.Integer id) {
        log.debug("Getting Adresse instance with id: " + id);
        return (Adresse)
            new DatabaseAccess().get(Adresse.class, id);
    }

    /**
     * Get a list of all <code>Adresse</code>
     * @return <code>List&lt;Adresse&gt;</code>
     *         all <code>Adresse</code>
     */
    public static List<Adresse> getAll() {
        return DatabaseQuery.getAll(new Adresse());
    }

    /* Custom code goes below here! */

    public String getE32(Adresse adr) {
    	String e32 = null;

		return e32;
    }

}
