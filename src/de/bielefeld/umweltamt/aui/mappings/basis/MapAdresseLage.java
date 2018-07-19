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

import java.util.Date;
import java.util.List;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the BasisObjekt database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class MapAdresseLage  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forObjekt;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Adresse Adresse;
    private Lage Lage;
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public MapAdresseLage() {
        // This place is intentionally left blank.
    }

    /** Full constructor */
    public MapAdresseLage(
        Adresse Adresse, Lage Lage, boolean enabled, boolean deleted) {
        this.Adresse = Adresse;
        this.Lage = Lage;
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

    public Adresse getAdresse() {
        return this.Adresse;
    }

    public void setAdresse(Adresse Adresse) {
        this.Adresse = Adresse;
    }

    public Lage getLage() {
        return this.Lage;
    }

    public void setLage(Lage Lage) {
        this.Lage = Lage;
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
        buffer.append("Adresse").append("='").append(getAdresse()).append("' ");			
        buffer.append("Lage").append("='").append(getLage()).append("' ");			
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
        if (!(other instanceof MapAdresseLage)) return false;
        return (this.getId().equals(
            ((MapAdresseLage) other).getId()));
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
    public static MapAdresseLage merge(MapAdresseLage detachedInstance) {
        log.debug("Merging Objekt instance " + detachedInstance);
        return (MapAdresseLage) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        MapAdresseLage saved = MapAdresseLage.merge(this);
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
    private void copy(MapAdresseLage copy) {
        this.Adresse = copy.getAdresse();            
        this.Lage = copy.getLage();          
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();          
            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(MapAdresseLage detachedInstance) {
        log.debug("Deleting Objekt instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return MapAdresseLage.delete(this);
    }

    /**
     * Find an <code>BasisObjekt</code> instance by its primary key
     * @param id the primary key value
     * @return <code>BasisObjekt</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static MapAdresseLage findById(java.lang.Integer id) {
        log.debug("Getting Objekt instance with id: " + id);
        return (MapAdresseLage)
            new DatabaseAccess().get(MapAdresseLage.class, id);
    }

    /**
     * Get a list of all <code>BasisObjekt</code>
     * @return <code>List&lt;BasisObjekt&gt;</code>
     *         all <code>BasisObjekt</code>
     */
    public static List<MapAdresseLage> getAll() {
        return DatabaseQuery.getAll(new MapAdresseLage());
    }

    /* Custom code goes below here! */
	public Integer getObjektid(){
		return getId();
	}

    public static MapAdresseLage findByLageId(Integer id){
        List<MapAdresseLage> all = MapAdresseLage.getAll();
        for(MapAdresseLage i : all){
            log.debug("Comparing " + i.getLage().getId() + " " + id);
            if(i.getLage().getId().equals(id)){
                log.debug("Returning Objekt: " + i.getId());
                return (MapAdresseLage) new DatabaseAccess().get(MapAdresseLage.class, i.getId());
            }
        }
        return null;
    }
    
    public static MapAdresseLage findByAdresseId(Integer id){

                return (MapAdresseLage) new DatabaseAccess().get(MapAdresseLage.class, id);
                    
//        return null;
    }
    
    /* Setter and getter for lage and adresse fields*/
    //Getter

    public String getStrasse(){
        return Adresse.getStrasse();
    }

    public Integer getHausnr(){
        return Adresse.getHausnr();
    }

    public String getHausnrzus(){
        return Adresse.getHausnrzus();
    }

    public String getPlz(){
        return Adresse.getPlz();
    }

    public String getOrt(){
        return Adresse.getOrt();
    }

    public Gemarkung getGemarkung(){
        return Lage.getGemarkung();
    }

    public String getEntgebid(){
        return Lage.getEntgebid();
    }

    public Float getE32(){
        return Lage.getE32();
    }

    public Float getN32(){
        return Lage.getN32();
    }

    public Standortgghwsg getStandortgghwsg(){
        return Lage.getStandortgghwsg();
    }

    public Wassereinzugsgebiet getWassereinzugsgebiete(){
        return Lage.getWassereinzugsgebiet();
    }

    public String getFlur(){
        return Lage.getFlur();
    }

    public String getFlurstueck(){
        return Lage.getFlurstueck();
    }

    public String getRevihandz(){
        if(Lage.getRevihandz() != null){
            return Lage.getRevihandz();
        }
        else{
            return Adresse.getRevihandz();
        }
    }

    public Date getRevidatum(){
        if(Lage.getRevidatum() != null){
            return Lage.getRevidatum();
        }
        else{
            return Adresse.getRevidatum();
        }
    }

    public String getSachbe33rav(){
        return Lage.getSachbe33rav();
    }

    public Integer getWassermenge(){
        return Lage.getWassermenge();
    }    

    //Setter

    public void setHausnr(Integer hausnr){
        this.Adresse.setHausnr(hausnr);
    }

    public void setHausnrzus(String hausnrzus){
        this.Adresse.setHausnrzus(hausnrzus);
    }

    public void setStrasse(String strasse){
        this.Adresse.setStrasse(strasse);
    }

    public void setPlz(String plz){
        this.Adresse.setPlz(plz);
    }

    public void setOrt(String ort){
        this.Adresse.setOrt(ort);
    }

    public void setStandortgghwsg(Standortgghwsg vaws){
        this.Lage.setStandortgghwsg(vaws);
    }

    public void setEntgebid(String entgebid){
        this.Lage.setEntgebid(entgebid);
    }

    public void setWassereinzugsgebiet(Wassereinzugsgebiet geb){
        this.Lage.setWassereinzugsgebiet(geb);
    }

    public void setGemarkung(Gemarkung gemarkung){
        this.Lage.setGemarkung(gemarkung);
    }

    public void setFlur(String flur){
        this.Lage.setFlur(flur);
    }

    public void setFlurstueck(String flurstueck){
        this.Lage.setFlurstueck(flurstueck);
    }

    public void setE32(Float e32){
        this.Lage.setE32(e32);
    }

    public void setN32(Float n32){
        this.Lage.setN32(n32);
    }

    public void setRevidatum(Date date){
        this.Lage.setRevidatum(date);
        this.Adresse.setRevidatum(date);
    }

    public void setRevihandz(String handz){
        this.Lage.setRevihandz(handz);
        this.Adresse.setRevihandz(handz);
    }

    public void setSachbe33rav(String sachbe33rav){
        Lage.setSachbe33rav(sachbe33rav);
    }

    public void setWassermenge(Integer wassermenge){
        Lage.setWassermenge(wassermenge);
    }

	public static MapAdresseLage findByAdresse(Adresse Standort) {
		MapAdresseLage map = new MapAdresseLage();
		Integer id = Standort.getId();
		List mapAdressen = HibernateSessionFactory
				.currentSession()
				.createQuery(
						"from MapAdresseLage where adresseid= " + id)
				.list();
		if (mapAdressen.size() != 0) {
			map = (MapAdresseLage) mapAdressen
					.get(0);
			return map;
		}
		return null;
	}
}
