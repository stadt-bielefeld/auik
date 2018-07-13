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
import java.util.List;

/**
 * A class that represents a row in the Anh55Fachdaten database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Anh55Fachdaten  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnh55Fachdaten;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Objekt objekt;
    private Boolean abgemeldet;
    private Boolean putztuecher;
    private Boolean teppich;
    private Boolean matten;
    private Boolean haushaltstex;
    private Boolean berufskl;
    private Boolean gaststhotel;
    private Boolean krankenhaus;
    private Boolean heimwaesche;
    private Integer anteilwaschgut;
    private Boolean vlies;
    private Boolean fischfleisch;
    private Integer anteilgesamtgut;
    private Boolean betrwasseraufber;
    private Boolean chlor;
    private Boolean aktivchlor;
    private String sachbearbeiter;
    private String entgebId;
    private String bemerkungen;
    private String mengewaesche;
    private String sonsttex;
    private String monatwasserverb;
    private String waschsituation;
    private String ansprechpartner;
    private String branche;
    private Boolean loesungsmittel;
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Anh55Fachdaten() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Anh55Fachdaten(
        Integer id, Objekt objekt, boolean enabled, boolean deleted) {
        this.id = id;
        this.objekt = objekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Anh55Fachdaten(
        Integer id, Objekt objekt, Boolean abgemeldet, Boolean putztuecher, Boolean teppich, Boolean matten, Boolean haushaltstex, Boolean berufskl, Boolean gaststhotel, Boolean krankenhaus, Boolean heimwaesche, Integer anteilwaschgut, Boolean vlies, Boolean fischfleisch, Integer anteilgesamtgut, Boolean betrwasseraufber, Boolean chlor, Boolean aktivchlor, String sachbearbeiter, String entgebId, String bemerkungen, String mengewaesche, String sonsttex, String monatwasserverb, String waschsituation, String ansprechpartner, String branche, Boolean loesungsmittel, boolean enabled, boolean deleted) {
        this.id = id;
        this.objekt = objekt;
        this.abgemeldet = abgemeldet;
        this.putztuecher = putztuecher;
        this.teppich = teppich;
        this.matten = matten;
        this.haushaltstex = haushaltstex;
        this.berufskl = berufskl;
        this.gaststhotel = gaststhotel;
        this.krankenhaus = krankenhaus;
        this.heimwaesche = heimwaesche;
        this.anteilwaschgut = anteilwaschgut;
        this.vlies = vlies;
        this.fischfleisch = fischfleisch;
        this.anteilgesamtgut = anteilgesamtgut;
        this.betrwasseraufber = betrwasseraufber;
        this.chlor = chlor;
        this.aktivchlor = aktivchlor;
        this.sachbearbeiter = sachbearbeiter;
        this.entgebId = entgebId;
        this.bemerkungen = bemerkungen;
        this.mengewaesche = mengewaesche;
        this.sonsttex = sonsttex;
        this.monatwasserverb = monatwasserverb;
        this.waschsituation = waschsituation;
        this.ansprechpartner = ansprechpartner;
        this.branche = branche;
        this.loesungsmittel = loesungsmittel;
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

    public Boolean getAbgemeldet() {
        return this.abgemeldet;
    }

    public void setAbgemeldet(Boolean abgemeldet) {
        this.abgemeldet = abgemeldet;
    }

    public Boolean getPutztuecher() {
        return this.putztuecher;
    }

    public void setPutztuecher(Boolean putztuecher) {
        this.putztuecher = putztuecher;
    }

    public Boolean getTeppich() {
        return this.teppich;
    }

    public void setTeppich(Boolean teppich) {
        this.teppich = teppich;
    }

    public Boolean getMatten() {
        return this.matten;
    }

    public void setMatten(Boolean matten) {
        this.matten = matten;
    }

    public Boolean getHaushaltstex() {
        return this.haushaltstex;
    }

    public void setHaushaltstex(Boolean haushaltstex) {
        this.haushaltstex = haushaltstex;
    }

    public Boolean getBerufskl() {
        return this.berufskl;
    }

    public void setBerufskl(Boolean berufskl) {
        this.berufskl = berufskl;
    }

    public Boolean getGaststhotel() {
        return this.gaststhotel;
    }

    public void setGaststhotel(Boolean gaststhotel) {
        this.gaststhotel = gaststhotel;
    }

    public Boolean getKrankenhaus() {
        return this.krankenhaus;
    }

    public void setKrankenhaus(Boolean krankenhaus) {
        this.krankenhaus = krankenhaus;
    }

    public Boolean getHeimwaesche() {
        return this.heimwaesche;
    }

    public void setHeimwaesche(Boolean heimwaesche) {
        this.heimwaesche = heimwaesche;
    }

    public Integer getAnteilwaschgut() {
        return this.anteilwaschgut;
    }

    public void setAnteilwaschgut(Integer anteilwaschgut) {
        this.anteilwaschgut = anteilwaschgut;
    }

    public Boolean getVlies() {
        return this.vlies;
    }

    public void setVlies(Boolean vlies) {
        this.vlies = vlies;
    }

    public Boolean getFischfleisch() {
        return this.fischfleisch;
    }

    public void setFischfleisch(Boolean fischfleisch) {
        this.fischfleisch = fischfleisch;
    }

    public Integer getAnteilgesamtgut() {
        return this.anteilgesamtgut;
    }

    public void setAnteilgesamtgut(Integer anteilgesamtgut) {
        this.anteilgesamtgut = anteilgesamtgut;
    }

    public Boolean getBetrwasseraufber() {
        return this.betrwasseraufber;
    }

    public void setBetrwasseraufber(Boolean betrwasseraufber) {
        this.betrwasseraufber = betrwasseraufber;
    }

    public Boolean getChlor() {
        return this.chlor;
    }

    public void setChlor(Boolean chlor) {
        this.chlor = chlor;
    }

    public Boolean getAktivchlor() {
        return this.aktivchlor;
    }

    public void setAktivchlor(Boolean aktivchlor) {
        this.aktivchlor = aktivchlor;
    }

    public String getSachbearbeiter() {
        return this.sachbearbeiter;
    }

    public void setSachbearbeiter(String sachbearbeiter) {
        this.sachbearbeiter = sachbearbeiter;
    }

    public String getEntgebId() {
        return this.entgebId;
    }

    public void setEntgebId(String entgebId) {
        this.entgebId = entgebId;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getMengewaesche() {
        return this.mengewaesche;
    }

    public void setMengewaesche(String mengewaesche) {
        this.mengewaesche = mengewaesche;
    }

    public String getSonsttex() {
        return this.sonsttex;
    }

    public void setSonsttex(String sonsttex) {
        this.sonsttex = sonsttex;
    }

    public String getMonatwasserverb() {
        return this.monatwasserverb;
    }

    public void setMonatwasserverb(String monatwasserverb) {
        this.monatwasserverb = monatwasserverb;
    }

    public String getWaschsituation() {
        return this.waschsituation;
    }

    public void setWaschsituation(String waschsituation) {
        this.waschsituation = waschsituation;
    }

    public String getAnsprechpartner() {
        return this.ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public String getBranche() {
        return this.branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

    public Boolean getLoesungsmittel() {
        return this.loesungsmittel;
    }

    public void setLoesungsmittel(Boolean loesungsmittel) {
        this.loesungsmittel = loesungsmittel;
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
        buffer.append("abgemeldet").append("='").append(getAbgemeldet()).append("' ");			
        buffer.append("putztuecher").append("='").append(getPutztuecher()).append("' ");			
        buffer.append("teppich").append("='").append(getTeppich()).append("' ");			
        buffer.append("matten").append("='").append(getMatten()).append("' ");			
        buffer.append("haushaltstex").append("='").append(getHaushaltstex()).append("' ");			
        buffer.append("berufskl").append("='").append(getBerufskl()).append("' ");			
        buffer.append("gaststhotel").append("='").append(getGaststhotel()).append("' ");			
        buffer.append("krankenhaus").append("='").append(getKrankenhaus()).append("' ");			
        buffer.append("heimwaesche").append("='").append(getHeimwaesche()).append("' ");			
        buffer.append("anteilwaschgut").append("='").append(getAnteilwaschgut()).append("' ");			
        buffer.append("vlies").append("='").append(getVlies()).append("' ");			
        buffer.append("fischfleisch").append("='").append(getFischfleisch()).append("' ");			
        buffer.append("anteilgesamtgut").append("='").append(getAnteilgesamtgut()).append("' ");			
        buffer.append("betrwasseraufber").append("='").append(getBetrwasseraufber()).append("' ");			
        buffer.append("chlor").append("='").append(getChlor()).append("' ");			
        buffer.append("aktivchlor").append("='").append(getAktivchlor()).append("' ");			
        buffer.append("sachbearbeiter").append("='").append(getSachbearbeiter()).append("' ");			
        buffer.append("entgebId").append("='").append(getEntgebId()).append("' ");			
        buffer.append("bemerkungen").append("='").append(getBemerkungen()).append("' ");			
        buffer.append("mengewaesche").append("='").append(getMengewaesche()).append("' ");			
        buffer.append("sonsttex").append("='").append(getSonsttex()).append("' ");			
        buffer.append("monatwasserverb").append("='").append(getMonatwasserverb()).append("' ");			
        buffer.append("waschsituation").append("='").append(getWaschsituation()).append("' ");			
        buffer.append("ansprechpartner").append("='").append(getAnsprechpartner()).append("' ");			
        buffer.append("branche").append("='").append(getBranche()).append("' ");			
        buffer.append("loesungsmittel").append("='").append(getLoesungsmittel()).append("' ");			
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
        if (!(other instanceof Anh55Fachdaten)) return false;
        return (this.getId().equals(
            ((Anh55Fachdaten) other).getId()));
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
     * @return <code>Anh55Fachdaten</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Anh55Fachdaten merge(Anh55Fachdaten detachedInstance) {
        log.debug("Merging Anh55Fachdaten instance " + detachedInstance);
        return (Anh55Fachdaten) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Anh55Fachdaten saved = Anh55Fachdaten.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Anh55Fachdaten with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Anh55Fachdaten
     */
    private void copy(Anh55Fachdaten copy) {
        this.id = copy.getId();            
        this.objekt = copy.getObjekt();            
        this.abgemeldet = copy.getAbgemeldet();            
        this.putztuecher = copy.getPutztuecher();            
        this.teppich = copy.getTeppich();            
        this.matten = copy.getMatten();            
        this.haushaltstex = copy.getHaushaltstex();            
        this.berufskl = copy.getBerufskl();            
        this.gaststhotel = copy.getGaststhotel();            
        this.krankenhaus = copy.getKrankenhaus();            
        this.heimwaesche = copy.getHeimwaesche();            
        this.anteilwaschgut = copy.getAnteilwaschgut();            
        this.vlies = copy.getVlies();            
        this.fischfleisch = copy.getFischfleisch();            
        this.anteilgesamtgut = copy.getAnteilgesamtgut();            
        this.betrwasseraufber = copy.getBetrwasseraufber();            
        this.chlor = copy.getChlor();            
        this.aktivchlor = copy.getAktivchlor();            
        this.sachbearbeiter = copy.getSachbearbeiter();            
        this.entgebId = copy.getEntgebId();            
        this.bemerkungen = copy.getBemerkungen();            
        this.mengewaesche = copy.getMengewaesche();            
        this.sonsttex = copy.getSonsttex();            
        this.monatwasserverb = copy.getMonatwasserverb();            
        this.waschsituation = copy.getWaschsituation();            
        this.ansprechpartner = copy.getAnsprechpartner();            
        this.branche = copy.getBranche();            
        this.loesungsmittel = copy.getLoesungsmittel();            
        this.enabled = copy.isEnabled();            
        this.deleted = copy.isDeleted();            
    }    

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Anh55Fachdaten detachedInstance) {
        log.debug("Deleting Anh55Fachdaten instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Anh55Fachdaten.delete(this);
    }

    /**
     * Find an <code>Anh55Fachdaten</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anh55Fachdaten</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anh55Fachdaten findById(java.lang.Integer id) {
        log.debug("Getting Anh55Fachdaten instance with id: " + id);
        return (Anh55Fachdaten)
            new DatabaseAccess().get(Anh55Fachdaten.class, id);
    }

    /**
     * Get a list of all <code>Anh55Fachdaten</code>
     * @return <code>List&lt;Anh55Fachdaten&gt;</code>
     *         all <code>Anh55Fachdaten</code>
     */
    public static List<Anh55Fachdaten> getAll() {
        return DatabaseQuery.getAll(new Anh55Fachdaten());
    }

    /* Custom code goes below here! */

}
