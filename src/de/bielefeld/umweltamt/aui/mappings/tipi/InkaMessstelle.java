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

package de.bielefeld.umweltamt.aui.mappings.tipi;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.tipi.DatabaseTipi;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.nrw.lds.tipi.general.HistoryObject;
import java.util.Calendar;
import java.util.List;

/**
 * A class that represents a row in the InkaMessstelle database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class InkaMessstelle  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forInkaMessstelle;
    
    /* Primary key, foreign keys (relations) and table columns */
    private Integer messstelleLfdNr;
    private String gemeindekennzahl;
    private Integer gemeindeVer;
    private Integer uebergabestelleLfdNr;
    private Integer uebergabestelleVer;
    private Integer messstelleVer;
    private Calendar gueltigVon;
    private Calendar gueltigBis;
    private Calendar aenderungsDatum;
    private Calendar erfassungsDatum;
    private Integer historienNr;
    private Boolean istAktuellJn;
    private Integer genehmigungNr;
    private Integer genehmigungVer;
    private Integer messstelleTyp;
    private String beschrMesspunkt;
    private Boolean relevantSumFrachtJn;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public InkaMessstelle() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public InkaMessstelle(
        Integer messstelleLfdNr) {
        this.messstelleLfdNr = messstelleLfdNr;
    }

    /** Full constructor */
    public InkaMessstelle(
        Integer messstelleLfdNr, String gemeindekennzahl, Integer gemeindeVer, Integer uebergabestelleLfdNr, Integer uebergabestelleVer, Integer messstelleVer, Calendar gueltigVon, Calendar gueltigBis, Calendar aenderungsDatum, Calendar erfassungsDatum, Integer historienNr, Boolean istAktuellJn, Integer genehmigungNr, Integer genehmigungVer, Integer messstelleTyp, String beschrMesspunkt, Boolean relevantSumFrachtJn) {
        this.messstelleLfdNr = messstelleLfdNr;
        this.gemeindekennzahl = gemeindekennzahl;
        this.gemeindeVer = gemeindeVer;
        this.uebergabestelleLfdNr = uebergabestelleLfdNr;
        this.uebergabestelleVer = uebergabestelleVer;
        this.messstelleVer = messstelleVer;
        this.gueltigVon = gueltigVon;
        this.gueltigBis = gueltigBis;
        this.aenderungsDatum = aenderungsDatum;
        this.erfassungsDatum = erfassungsDatum;
        this.historienNr = historienNr;
        this.istAktuellJn = istAktuellJn;
        this.genehmigungNr = genehmigungNr;
        this.genehmigungVer = genehmigungVer;
        this.messstelleTyp = messstelleTyp;
        this.beschrMesspunkt = beschrMesspunkt;
        this.relevantSumFrachtJn = relevantSumFrachtJn;
    }

    /* Setter and getter methods */
    public Integer getMessstelleLfdNr() {
        return this.messstelleLfdNr;
    }

    public void setMessstelleLfdNr(Integer messstelleLfdNr) {
        this.messstelleLfdNr = messstelleLfdNr;
    }

    public String getGemeindekennzahl() {
        return this.gemeindekennzahl;
    }

    public void setGemeindekennzahl(String gemeindekennzahl) {
        this.gemeindekennzahl = gemeindekennzahl;
    }

    public Integer getGemeindeVer() {
        return this.gemeindeVer;
    }

    public void setGemeindeVer(Integer gemeindeVer) {
        this.gemeindeVer = gemeindeVer;
    }

    public Integer getUebergabestelleLfdNr() {
        return this.uebergabestelleLfdNr;
    }

    public void setUebergabestelleLfdNr(Integer uebergabestelleLfdNr) {
        this.uebergabestelleLfdNr = uebergabestelleLfdNr;
    }

    public Integer getUebergabestelleVer() {
        return this.uebergabestelleVer;
    }

    public void setUebergabestelleVer(Integer uebergabestelleVer) {
        this.uebergabestelleVer = uebergabestelleVer;
    }

    public Integer getMessstelleVer() {
        return this.messstelleVer;
    }

    public void setMessstelleVer(Integer messstelleVer) {
        this.messstelleVer = messstelleVer;
    }

    public Calendar getGueltigVon() {
        return this.gueltigVon;
    }

    public void setGueltigVon(Calendar gueltigVon) {
        this.gueltigVon = gueltigVon;
    }

    public Calendar getGueltigBis() {
        return this.gueltigBis;
    }

    public void setGueltigBis(Calendar gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    public Calendar getAenderungsDatum() {
        return this.aenderungsDatum;
    }

    public void setAenderungsDatum(Calendar aenderungsDatum) {
        this.aenderungsDatum = aenderungsDatum;
    }

    public Calendar getErfassungsDatum() {
        return this.erfassungsDatum;
    }

    public void setErfassungsDatum(Calendar erfassungsDatum) {
        this.erfassungsDatum = erfassungsDatum;
    }

    public Integer getHistorienNr() {
        return this.historienNr;
    }

    public void setHistorienNr(Integer historienNr) {
        this.historienNr = historienNr;
    }

    public Boolean getIstAktuellJn() {
        return this.istAktuellJn;
    }

    public void setIstAktuellJn(Boolean istAktuellJn) {
        this.istAktuellJn = istAktuellJn;
    }

    public Integer getGenehmigungNr() {
        return this.genehmigungNr;
    }

    public void setGenehmigungNr(Integer genehmigungNr) {
        this.genehmigungNr = genehmigungNr;
    }

    public Integer getGenehmigungVer() {
        return this.genehmigungVer;
    }

    public void setGenehmigungVer(Integer genehmigungVer) {
        this.genehmigungVer = genehmigungVer;
    }

    public Integer getMessstelleTyp() {
        return this.messstelleTyp;
    }

    public void setMessstelleTyp(Integer messstelleTyp) {
        this.messstelleTyp = messstelleTyp;
    }

    public String getBeschrMesspunkt() {
        return this.beschrMesspunkt;
    }

    public void setBeschrMesspunkt(String beschrMesspunkt) {
        this.beschrMesspunkt = beschrMesspunkt;
    }

    public Boolean getRelevantSumFrachtJn() {
        return this.relevantSumFrachtJn;
    }

    public void setRelevantSumFrachtJn(Boolean relevantSumFrachtJn) {
        this.relevantSumFrachtJn = relevantSumFrachtJn;
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("messstelleLfdNr").append("='").append(getMessstelleLfdNr()).append("' ");			
        buffer.append("gemeindekennzahl").append("='").append(getGemeindekennzahl()).append("' ");			
        buffer.append("gemeindeVer").append("='").append(getGemeindeVer()).append("' ");			
        buffer.append("uebergabestelleLfdNr").append("='").append(getUebergabestelleLfdNr()).append("' ");			
        buffer.append("uebergabestelleVer").append("='").append(getUebergabestelleVer()).append("' ");			
        buffer.append("messstelleVer").append("='").append(getMessstelleVer()).append("' ");			
        buffer.append("gueltigVon").append("='").append(getGueltigVon()).append("' ");			
        buffer.append("gueltigBis").append("='").append(getGueltigBis()).append("' ");			
        buffer.append("aenderungsDatum").append("='").append(getAenderungsDatum()).append("' ");			
        buffer.append("erfassungsDatum").append("='").append(getErfassungsDatum()).append("' ");			
        buffer.append("historienNr").append("='").append(getHistorienNr()).append("' ");			
        buffer.append("istAktuellJn").append("='").append(getIstAktuellJn()).append("' ");			
        buffer.append("genehmigungNr").append("='").append(getGenehmigungNr()).append("' ");			
        buffer.append("genehmigungVer").append("='").append(getGenehmigungVer()).append("' ");			
        buffer.append("messstelleTyp").append("='").append(getMessstelleTyp()).append("' ");			
        buffer.append("beschrMesspunkt").append("='").append(getBeschrMesspunkt()).append("' ");			
        buffer.append("relevantSumFrachtJn").append("='").append(getRelevantSumFrachtJn()).append("' ");			
        buffer.append("]");

        return buffer.toString();
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>InkaMessstelle</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static InkaMessstelle merge(InkaMessstelle detachedInstance) {
        log.debug("Merging InkaMessstelle instance " + detachedInstance);
        return (InkaMessstelle) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        return InkaMessstelle.merge(this).equals(this);
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(InkaMessstelle detachedInstance) {
        log.debug("Deleting InkaMessstelle instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return InkaMessstelle.delete(this);
    }

    /**
     * Find an <code>InkaMessstelle</code> instance by its primary key
     * @param id the primary key value
     * @return <code>InkaMessstelle</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static InkaMessstelle findById(java.lang.Integer id) {
        log.debug("Getting InkaMessstelle instance with id: " + id);
        return (InkaMessstelle)
            new DatabaseAccess().get(InkaMessstelle.class, id);
    }

    /**
     * Get a list of all <code>InkaMessstelle</code>
     * @return <code>List&lt;InkaMessstelle&gt;</code>
     *         all <code>InkaMessstelle</code>
     */
    public static List<InkaMessstelle> getAll() {
        return DatabaseQuery.getAll(new InkaMessstelle());
    }

    /**
     * As we can not generate this bit of code completely
     * (ordering of the parameters is the main problem),
     * we jump to not generated code.
     * @return HistoryObject (the corresponding service type to InkaMessstelle)
     */
    public HistoryObject toServiceType() {
        return DatabaseTipi.toServiceTypeForClass(this);
    }

    /* Custom code goes below here! */

}
