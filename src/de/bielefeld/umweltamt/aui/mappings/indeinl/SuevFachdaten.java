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
 * A class that represents a row in the SuevFachdaten database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class SuevFachdaten  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forSuevFachdaten;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Objekt objekt;
    private Boolean groesser3ha;
    private Integer versFlaeche;
    private Boolean suevkanPflicht;
    private Boolean indirektsw;
    private Boolean indirektrw;
    private Boolean direktsw;
    private Boolean direktrw;
    private Integer anzeige58;
    private Boolean sanierungErfolgt;
    private Boolean sanierungskonzept;
    private Boolean keineAngaben;
    private Date datAnzeige58;
    private Date datAnschreiben;
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public SuevFachdaten() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public SuevFachdaten(
        Integer id, Objekt objekt, boolean enabled, boolean deleted) {
        this.id = id;
        this.objekt = objekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public SuevFachdaten(
        Integer id, Objekt objekt, Boolean groesser3ha, Integer versFlaeche, Boolean suevkanPflicht, Boolean indirektsw, Boolean indirektrw, Boolean direktsw, Boolean direktrw, Integer anzeige58, Boolean sanierungErfolgt, Boolean sanierungskonzept, Boolean keineAngaben, Date datAnzeige58, Date datAnschreiben, boolean enabled, boolean deleted) {
        this.id = id;
        this.objekt = objekt;
        this.groesser3ha = groesser3ha;
        this.versFlaeche = versFlaeche;
        this.suevkanPflicht = suevkanPflicht;
        this.indirektsw = indirektsw;
        this.indirektrw = indirektrw;
        this.direktsw = direktsw;
        this.direktrw = direktrw;
        this.anzeige58 = anzeige58;
        this.sanierungErfolgt = sanierungErfolgt;
        this.sanierungskonzept = sanierungskonzept;
        this.keineAngaben = keineAngaben;
        this.datAnzeige58 = datAnzeige58;
        this.datAnschreiben = datAnschreiben;
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

    public Boolean getGroesser3ha() {
        return this.groesser3ha;
    }

    public void setGroesser3ha(Boolean groesser3ha) {
        this.groesser3ha = groesser3ha;
    }

    public Integer getVersFlaeche() {
        return this.versFlaeche;
    }

    public void setVersFlaeche(Integer versFlaeche) {
        this.versFlaeche = versFlaeche;
    }

    public Boolean getSuevkanPflicht() {
        return this.suevkanPflicht;
    }

    public void setSuevkanPflicht(Boolean suevkanPflicht) {
        this.suevkanPflicht = suevkanPflicht;
    }

    public Boolean getIndirektsw() {
        return this.indirektsw;
    }

    public void setIndirektsw(Boolean indirektsw) {
        this.indirektsw = indirektsw;
    }

    public Boolean getIndirektrw() {
        return this.indirektrw;
    }

    public void setIndirektrw(Boolean indirektrw) {
        this.indirektrw = indirektrw;
    }

    public Boolean getDirektsw() {
        return this.direktsw;
    }

    public void setDirektsw(Boolean direktsw) {
        this.direktsw = direktsw;
    }

    public Boolean getDirektrw() {
        return this.direktrw;
    }

    public void setDirektrw(Boolean direktrw) {
        this.direktrw = direktrw;
    }

    public Integer getAnzeige58() {
        return this.anzeige58;
    }

    public void setAnzeige58(Integer anzeige58) {
        this.anzeige58 = anzeige58;
    }

    public Boolean getSanierungErfolgt() {
        return this.sanierungErfolgt;
    }

    public void setSanierungErfolgt(Boolean sanierungErfolgt) {
        this.sanierungErfolgt = sanierungErfolgt;
    }

    public Boolean getSanierungskonzept() {
        return this.sanierungskonzept;
    }

    public void setSanierungskonzept(Boolean sanierungskonzept) {
        this.sanierungskonzept = sanierungskonzept;
    }

    public Boolean getKeineAngaben() {
        return this.keineAngaben;
    }

    public void setKeineAngaben(Boolean keineAngaben) {
        this.keineAngaben = keineAngaben;
    }

    public Date getDatAnzeige58() {
        return this.datAnzeige58;
    }

    public void setDatAnzeige58(Date datAnzeige58) {
        this.datAnzeige58 = datAnzeige58;
    }

    public Date getDatAnschreiben() {
        return this.datAnschreiben;
    }

    public void setDatAnschreiben(Date datAnschreiben) {
        this.datAnschreiben = datAnschreiben;
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
        buffer.append("groesser3ha").append("='").append(getGroesser3ha()).append("' ");
        buffer.append("versFlaeche").append("='").append(getVersFlaeche()).append("' ");
        buffer.append("suevkanPflicht").append("='").append(getSuevkanPflicht()).append("' ");
        buffer.append("indirektsw").append("='").append(getIndirektsw()).append("' ");
        buffer.append("indirektrw").append("='").append(getIndirektrw()).append("' ");
        buffer.append("direktsw").append("='").append(getDirektsw()).append("' ");
        buffer.append("direktrw").append("='").append(getDirektrw()).append("' ");
        buffer.append("anzeige58").append("='").append(getAnzeige58()).append("' ");
        buffer.append("sanierungErfolgt").append("='").append(getSanierungErfolgt()).append("' ");
        buffer.append("sanierungskonzept").append("='").append(getSanierungskonzept()).append("' ");
        buffer.append("keineAngaben").append("='").append(getKeineAngaben()).append("' ");
        buffer.append("datAnzeige58").append("='").append(getDatAnzeige58()).append("' ");
        buffer.append("datAnschreiben").append("='").append(getDatAnschreiben()).append("' ");
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
        if (!(other instanceof SuevFachdaten)) return false;
        return (this.getId().equals(
            ((SuevFachdaten) other).getId()));
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
     * @return <code>SuevFachdaten</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static SuevFachdaten merge(SuevFachdaten detachedInstance) {
        log.debug("Merging SuevFachdaten instance " + detachedInstance);
        return (SuevFachdaten) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        SuevFachdaten saved = SuevFachdaten.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this SuevFachdaten with its new values.<br>
     * This is meant to be used after merging!
     * @param copy SuevFachdaten
     */
    private void copy(SuevFachdaten copy) {
        this.id = copy.getId();
        this.objekt = copy.getObjekt();
        this.groesser3ha = copy.getGroesser3ha();
        this.versFlaeche = copy.getVersFlaeche();
        this.suevkanPflicht = copy.getSuevkanPflicht();
        this.indirektsw = copy.getIndirektsw();
        this.indirektrw = copy.getIndirektrw();
        this.direktsw = copy.getDirektsw();
        this.direktrw = copy.getDirektrw();
        this.anzeige58 = copy.getAnzeige58();
        this.sanierungErfolgt = copy.getSanierungErfolgt();
        this.sanierungskonzept = copy.getSanierungskonzept();
        this.keineAngaben = copy.getKeineAngaben();
        this.datAnzeige58 = copy.getDatAnzeige58();
        this.datAnschreiben = copy.getDatAnschreiben();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(SuevFachdaten detachedInstance) {
        log.debug("Deleting SuevFachdaten instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return SuevFachdaten.delete(this);
    }

    /**
     * Find an <code>SuevFachdaten</code> instance by its primary key
     * @param id the primary key value
     * @return <code>SuevFachdaten</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static SuevFachdaten findById(java.lang.Integer id) {
        log.debug("Getting SuevFachdaten instance with id: " + id);
        return (SuevFachdaten)
            new DatabaseAccess().get(SuevFachdaten.class, id);
    }

    /**
     * Get a list of all <code>SuevFachdaten</code>
     * @return <code>List&lt;SuevFachdaten&gt;</code>
     *         all <code>SuevFachdaten</code>
     */
    public static List<SuevFachdaten> getAll() {
        return DatabaseQuery.getAll(new SuevFachdaten());
    }

    /* Custom code goes below here! */

    public static SuevFachdaten findByObjektId(java.lang.Integer id){
        log.debug("Getting AnhSuevFachdaten instance with objektid: " + id);
        List<SuevFachdaten> all = SuevFachdaten.getAll();
        for(SuevFachdaten i : all){
            if(i.getObjekt().getId().equals(id)){
                return (SuevFachdaten) new DatabaseAccess().get(SuevFachdaten.class, i.getId());
            }
        }
        return null;
    }
}
