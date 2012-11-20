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

// Generated by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.util.Date;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the Anh40Fachdaten database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Anh40Fachdaten  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAnh40Fachdaten;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer objektid;
    private BasisObjekt basisObjekt;
    private String bemerkungen;
    private String ansprechpartner;
    private String sachbearbeiterrav;
    private String sachbearbeiterheepen;
    private String klaeranlage;
    private String herkunftsbereich;
    private Boolean wsg;
    private Short prioritaet;
    private Boolean genehmigungspflicht;
    private Boolean nachtrag;
    private Boolean bimsch;
    private Integer abwmengegenehmigt;
    private Integer abwmengeprodspez;
    private Integer abwmengegesamt;
    private Date gen58;
    private Date gen59;
    private boolean enabled;
    private boolean deleted;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Anh40Fachdaten() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Anh40Fachdaten(
        BasisObjekt basisObjekt, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Anh40Fachdaten(
        BasisObjekt basisObjekt, String bemerkungen, String ansprechpartner, String sachbearbeiterrav, String sachbearbeiterheepen, String klaeranlage, String herkunftsbereich, Boolean wsg, Short prioritaet, Boolean genehmigungspflicht, Boolean nachtrag, Boolean bimsch, Integer abwmengegenehmigt, Integer abwmengeprodspez, Integer abwmengegesamt, Date gen58, Date gen59, boolean enabled, boolean deleted) {
        this.basisObjekt = basisObjekt;
        this.bemerkungen = bemerkungen;
        this.ansprechpartner = ansprechpartner;
        this.sachbearbeiterrav = sachbearbeiterrav;
        this.sachbearbeiterheepen = sachbearbeiterheepen;
        this.klaeranlage = klaeranlage;
        this.herkunftsbereich = herkunftsbereich;
        this.wsg = wsg;
        this.prioritaet = prioritaet;
        this.genehmigungspflicht = genehmigungspflicht;
        this.nachtrag = nachtrag;
        this.bimsch = bimsch;
        this.abwmengegenehmigt = abwmengegenehmigt;
        this.abwmengeprodspez = abwmengeprodspez;
        this.abwmengegesamt = abwmengegesamt;
        this.gen58 = gen58;
        this.gen59 = gen59;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /* Setter and getter methods */
    public Integer getObjektid() {
        return this.objektid;
    }

    public void setObjektid(Integer objektid) {
        this.objektid = objektid;
    }

    public BasisObjekt getBasisObjekt() {
        return this.basisObjekt;
    }

    public void setBasisObjekt(BasisObjekt basisObjekt) {
        this.basisObjekt = basisObjekt;
    }

    public String getBemerkungen() {
        return this.bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getAnsprechpartner() {
        return this.ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public String getSachbearbeiterrav() {
        return this.sachbearbeiterrav;
    }

    public void setSachbearbeiterrav(String sachbearbeiterrav) {
        this.sachbearbeiterrav = sachbearbeiterrav;
    }

    public String getSachbearbeiterheepen() {
        return this.sachbearbeiterheepen;
    }

    public void setSachbearbeiterheepen(String sachbearbeiterheepen) {
        this.sachbearbeiterheepen = sachbearbeiterheepen;
    }

    public String getKlaeranlage() {
        return this.klaeranlage;
    }

    public void setKlaeranlage(String klaeranlage) {
        this.klaeranlage = klaeranlage;
    }

    public String getHerkunftsbereich() {
        return this.herkunftsbereich;
    }

    public void setHerkunftsbereich(String herkunftsbereich) {
        this.herkunftsbereich = herkunftsbereich;
    }

    public Boolean getWsg() {
        return this.wsg;
    }

    public void setWsg(Boolean wsg) {
        this.wsg = wsg;
    }

    public Short getPrioritaet() {
        return this.prioritaet;
    }

    public void setPrioritaet(Short prioritaet) {
        this.prioritaet = prioritaet;
    }

    public Boolean getGenehmigungspflicht() {
        return this.genehmigungspflicht;
    }

    public void setGenehmigungspflicht(Boolean genehmigungspflicht) {
        this.genehmigungspflicht = genehmigungspflicht;
    }

    public Boolean getNachtrag() {
        return this.nachtrag;
    }

    public void setNachtrag(Boolean nachtrag) {
        this.nachtrag = nachtrag;
    }

    public Boolean getBimsch() {
        return this.bimsch;
    }

    public void setBimsch(Boolean bimsch) {
        this.bimsch = bimsch;
    }

    public Integer getAbwmengegenehmigt() {
        return this.abwmengegenehmigt;
    }

    public void setAbwmengegenehmigt(Integer abwmengegenehmigt) {
        this.abwmengegenehmigt = abwmengegenehmigt;
    }

    public Integer getAbwmengeprodspez() {
        return this.abwmengeprodspez;
    }

    public void setAbwmengeprodspez(Integer abwmengeprodspez) {
        this.abwmengeprodspez = abwmengeprodspez;
    }

    public Integer getAbwmengegesamt() {
        return this.abwmengegesamt;
    }

    public void setAbwmengegesamt(Integer abwmengegesamt) {
        this.abwmengegesamt = abwmengegesamt;
    }

    public Date getGen58() {
        return this.gen58;
    }

    public void setGen58(Date gen58) {
        this.gen58 = gen58;
    }

    public Date getGen59() {
        return this.gen59;
    }

    public void setGen59(Date gen59) {
        this.gen59 = gen59;
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
        buffer.append("basisObjekt").append("='").append(getBasisObjekt()).append("' ");
        buffer.append("bemerkungen").append("='").append(getBemerkungen()).append("' ");
        buffer.append("ansprechpartner").append("='").append(getAnsprechpartner()).append("' ");
        buffer.append("sachbearbeiterrav").append("='").append(getSachbearbeiterrav()).append("' ");
        buffer.append("sachbearbeiterheepen").append("='").append(getSachbearbeiterheepen()).append("' ");
        buffer.append("klaeranlage").append("='").append(getKlaeranlage()).append("' ");
        buffer.append("herkunftsbereich").append("='").append(getHerkunftsbereich()).append("' ");
        buffer.append("wsg").append("='").append(getWsg()).append("' ");
        buffer.append("prioritaet").append("='").append(getPrioritaet()).append("' ");
        buffer.append("genehmigungspflicht").append("='").append(getGenehmigungspflicht()).append("' ");
        buffer.append("nachtrag").append("='").append(getNachtrag()).append("' ");
        buffer.append("bimsch").append("='").append(getBimsch()).append("' ");
        buffer.append("abwmengegenehmigt").append("='").append(getAbwmengegenehmigt()).append("' ");
        buffer.append("abwmengeprodspez").append("='").append(getAbwmengeprodspez()).append("' ");
        buffer.append("abwmengegesamt").append("='").append(getAbwmengegesamt()).append("' ");
        buffer.append("gen58").append("='").append(getGen58()).append("' ");
        buffer.append("gen59").append("='").append(getGen59()).append("' ");
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
        if (!(other instanceof Anh40Fachdaten)) return false;
        return (this.getObjektid().equals(
            ((Anh40Fachdaten) other).getObjektid()));
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>Anh40Fachdaten</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Anh40Fachdaten merge(Anh40Fachdaten detachedInstance) {
        log.debug("Merging Anh40Fachdaten instance " + detachedInstance);
        return (Anh40Fachdaten) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        return (Anh40Fachdaten.merge(this) != null);
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Anh40Fachdaten detachedInstance) {
        log.debug("Deleting Anh40Fachdaten instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Anh40Fachdaten.delete(this);
    }

    /**
     * Find an <code>Anh40Fachdaten</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Anh40Fachdaten</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Anh40Fachdaten findById(java.lang.Integer id) {
        log.debug("Getting Anh40Fachdaten instance with id: " + id);
        return (Anh40Fachdaten)
            new DatabaseAccess().get(Anh40Fachdaten.class, id);
    }

    /**
     * Get a list of all <code>Anh40Fachdaten</code>
     * @return <code>List&lt;Anh40Fachdaten&gt;</code>
     *         all <code>Anh40Fachdaten</code>
     */
    public static List<Anh40Fachdaten> getAll() {
        return DatabaseQuery.getAll(new Anh40Fachdaten());
    }

    /* Custom code goes below here! */

}
