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
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWassereinzugsgebiete;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsStandortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisLage;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisAdresse;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisGemarkung;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;

import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

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
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public BasisMapAdresseLage() {
        // This place is intentionally left blank.
    }

    /** Full constructor */
    public BasisMapAdresseLage(
        BasisAdresse basisAdresse, BasisLage basisLage, boolean enabled, boolean deleted) {
        this.basisAdresse = basisAdresse;
        this.basisLage = basisLage;
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
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();          
            
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
    
    public static BasisMapAdresseLage findByAdresseId(Integer id){

                return (BasisMapAdresseLage) new DatabaseAccess().get(BasisMapAdresseLage.class, id);
                    
//        return null;
    }
    
    /* Setter and getter for lage and adresse fields*/
    //Getter

    public String getStrasse(){
        return basisAdresse.getStrasse();
    }

    public Integer getHausnr(){
        return basisAdresse.getHausnr();
    }

    public String getHausnrzus(){
        return basisAdresse.getHausnrzus();
    }

    public String getPlz(){
        return basisAdresse.getPlz();
    }

    public String getOrt(){
        return basisAdresse.getOrt();
    }

    public BasisGemarkung getBasisGemarkung(){
        return basisLage.getBasisGemarkung();
    }

    public String getEntgebid(){
        return basisLage.getEntgebid();
    }

    public Float getE32(){
        return basisLage.getE32();
    }

    public Float getN32(){
        return basisLage.getN32();
    }

    public VawsStandortgghwsg getVawsStandortgghwsg(){
        return basisLage.getVawsStandortgghwsg();
    }

    public VawsWassereinzugsgebiete getVawsWassereinzugsgebiete(){
        return basisLage.getVawsWassereinzugsgebiete();
    }

    public String getFlur(){
        return basisLage.getFlur();
    }

    public String getFlurstueck(){
        return basisLage.getFlurstueck();
    }

    public String getRevihandz(){
        if(basisLage.getRevihandz() != null){
            return basisLage.getRevihandz();
        }
        else{
            return basisAdresse.getRevihandz();
        }
    }

    public Date getRevidatum(){
        if(basisLage.getRevidatum() != null){
            return basisLage.getRevidatum();
        }
        else{
            return basisAdresse.getRevidatum();
        }
    }

    public String getSachbe33rav(){
        return basisLage.getSachbe33rav();
    }

    public Integer getWassermenge(){
        return basisLage.getWassermenge();
    }    

    //Setter

    public void setHausnr(Integer hausnr){
        this.basisAdresse.setHausnr(hausnr);
    }

    public void setHausnrzus(String hausnrzus){
        this.basisAdresse.setHausnrzus(hausnrzus);
    }

    public void setStrasse(String strasse){
        this.basisAdresse.setStrasse(strasse);
    }

    public void setPlz(String plz){
        this.basisAdresse.setPlz(plz);
    }

    public void setOrt(String ort){
        this.basisAdresse.setOrt(ort);
    }

    public void setVawsStandortgghwsg(VawsStandortgghwsg vaws){
        this.basisLage.setVawsStandortgghwsg(vaws);
    }

    public void setEntgebid(String entgebid){
        this.basisLage.setEntgebid(entgebid);
    }

    public void setVawsWassereinzugsgebiete(VawsWassereinzugsgebiete geb){
        this.basisLage.setVawsWassereinzugsgebiete(geb);
    }

    public void setBasisGemarkung(BasisGemarkung gemarkung){
        this.basisLage.setBasisGemarkung(gemarkung);
    }

    public void setFlur(String flur){
        this.basisLage.setFlur(flur);
    }

    public void setFlurstueck(String flurstueck){
        this.basisLage.setFlurstueck(flurstueck);
    }

    public void setE32(Float e32){
        this.basisLage.setE32(e32);
    }

    public void setN32(Float n32){
        this.basisLage.setN32(n32);
    }

    public void setRevidatum(Date date){
        this.basisLage.setRevidatum(date);
        this.basisAdresse.setRevidatum(date);
    }

    public void setRevihandz(String handz){
        this.basisLage.setRevihandz(handz);
        this.basisAdresse.setRevihandz(handz);
    }

    public void setSachbe33rav(String sachbe33rav){
        basisLage.setSachbe33rav(sachbe33rav);
    }

    public void setWassermenge(Integer wassermenge){
        basisLage.setWassermenge(wassermenge);
    }

	public static BasisMapAdresseLage findByAdresse(BasisAdresse basisStandort) {
		Integer id = basisStandort.getId();
		BasisMapAdresseLage map = (BasisMapAdresseLage) HibernateSessionFactory.currentSession().createQuery("from BasisMapAdresseLage where adresseid= " + id).list().get(0);
        return map;
	}
}
