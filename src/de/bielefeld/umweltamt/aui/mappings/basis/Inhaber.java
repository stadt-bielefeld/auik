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
public class Inhaber  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAdresse;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer id;
    private Adresse adresse;
    private Wirtschaftszweig wirtschaftszweig;
    private String anrede;
    private String name;
    private String vorname;
    private String namezus;
    private String namebetrbeauf;
    private String vornamebetrbeauf;
    private String telefon;
    private String telefax;
    private String email;
    private Integer wassermenge;
    private String bemerkungen;
    private Date revidatum;
    private String revihandz;
    private String kassenzeichen;
    private boolean enabled;
    private boolean deleted;
    private boolean datenschutzAwsv;
    private boolean datenschutzEsatzung;
    private boolean datenschutzWhg;
    private String auikWzCode;
    private Date erstellDat;
    private Set<Objekt> objekts = new HashSet<Objekt>(0);
    private Set<Standort> standorts = new HashSet<Standort>(0);
    private Integer iglId;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Inhaber() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Inhaber(
        Integer id, boolean enabled, boolean deleted) {
        this.id = id;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    /** Full constructor */
    public Inhaber(
    		Adresse adresse, Integer id, Objekt objekt, Wirtschaftszweig wirtschaftszweig, String anrede,
            String name, String vorname, String namezus, String namebetrbeauf, String vornamebetrbeauf,
            String strasse, Integer hausnr, String hausnrzus, String plzzs, String plz, String ort,
            String telefon, String telefax, String email, Integer wassermenge, String bemerkungen,
            Date revidatum, String revihandz, String kassenzeichen, boolean enabled,
            boolean deleted, String auikWzCode, Date erstellDat, Integer iglId, Set<Standort> standorts, Set<Objekt> objekts) {
        this.id = id;
        this.adresse = adresse;
        this.wirtschaftszweig = wirtschaftszweig;
        this.anrede = anrede;
        this.name = name;
        this.vorname = vorname;
        this.namezus = namezus;
        this.namebetrbeauf = namebetrbeauf;
        this.vornamebetrbeauf = vornamebetrbeauf;
        this.telefon = telefon;
        this.telefax = telefax;
        this.email = email;
    	this.wassermenge = wassermenge;
        this.bemerkungen = bemerkungen;
        this.revidatum = revidatum;
        this.revihandz = revihandz;
        this.kassenzeichen = kassenzeichen;
        this.enabled = enabled;
        this.deleted = deleted;
        this.auikWzCode = auikWzCode;
        this.erstellDat = erstellDat;
        this.standorts = standorts;
        this.iglId = iglId;
        this.objekts = objekts;
    }

    /* Setter and getter methods */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adresse getAdresse() {
        return this.adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Wirtschaftszweig getWirtschaftszweig() {
        return this.wirtschaftszweig;
    }

    public void setWirtschaftszweig(Wirtschaftszweig wirtschaftszweig) {
        this.wirtschaftszweig = wirtschaftszweig;
    }

    public String getAnrede() {
        return this.anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return this.vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNamezus() {
        return this.namezus;
    }

    public void setNamezus(String namezus) {
        this.namezus = namezus;
    }

    public String getNamebetrbeauf() {
        return this.namebetrbeauf;
    }

    public void setNamebetrbeauf(String namebetrbeauf) {
        this.namebetrbeauf = namebetrbeauf;
    }

    public String getVornamebetrbeauf() {
        return this.vornamebetrbeauf;
    }

    public void setVornamebetrbeauf(String vornamebetrbeauf) {
        this.vornamebetrbeauf = vornamebetrbeauf;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefax() {
        return this.telefax;
    }

    public void setTelefax(String telefax) {
        this.telefax = telefax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Integer getWassermenge(){
	    return this.wassermenge;
	}

	public void setWassermenge(Integer wassermenge){
		this.wassermenge = wassermenge;
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

    public String getKassenzeichen() {
        return this.kassenzeichen;
    }

    public void setKassenzeichen(String kassenzeichen) {
        this.kassenzeichen = kassenzeichen;
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

    public Boolean getDatenschutzAwsv() {
	    return this.datenschutzAwsv;
	}

	public void setDatenschutzAwsv(boolean datenschutzAwsv) {
	    this.datenschutzAwsv = datenschutzAwsv;
	}

	public Boolean getDatenschutzEsatzung() {
	    return this.datenschutzEsatzung;
	}

	public void setDatenschutzEsatzung(boolean datenschutzEsatzung) {
	    this.datenschutzEsatzung = datenschutzEsatzung;
	}

	public Boolean getDatenschutzWhg() {
	    return this.datenschutzWhg;
	}

	public void setDatenschutzWhg(boolean datenschutzWhg) {
	    this.datenschutzWhg = datenschutzWhg;
	}

	public String getAuikWzCode() {
        return this.auikWzCode;
    }

    public void setAuikWzCode(String auikWzCode) {
        this.auikWzCode = auikWzCode;
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

    @JsonBackReference
    public Set<Objekt> getObjekts() {
        return this.objekts;
    }

    public void setObjekts(Set<Objekt> objekts) {
        this.objekts = objekts;
    }

    @JsonBackReference
    public Set<Standort> getStandorts() {
		return standorts;
	}

	public void setStandorts(Set<Standort> standorts) {
		this.standorts = standorts;
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
        buffer.append("wirtschaftszweig").append("='").append(getWirtschaftszweig()).append("' ");
        buffer.append("anrede").append("='").append(getAnrede()).append("' ");
        buffer.append("name").append("='").append(getName()).append("' ");
        buffer.append("vorname").append("='").append(getVorname()).append("' ");
        buffer.append("namezus").append("='").append(getNamezus()).append("' ");
        buffer.append("namebetrbeauf").append("='").append(getNamebetrbeauf()).append("' ");
        buffer.append("vornamebetrbeauf").append("='").append(getVornamebetrbeauf()).append("' ");
        buffer.append("telefon").append("='").append(getTelefon()).append("' ");
        buffer.append("telefax").append("='").append(getTelefax()).append("' ");
        buffer.append("email").append("='").append(getEmail()).append("' ");
        buffer.append("bemerkungen").append("='").append(getBemerkungen()).append("' ");
        buffer.append("revidatum").append("='").append(getRevidatum()).append("' ");
        buffer.append("revihandz").append("='").append(getRevihandz()).append("' ");
        buffer.append("kassenzeichen").append("='").append(getKassenzeichen()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
        buffer.append("auikWzCode").append("='").append(getAuikWzCode()).append("' ");
        buffer.append("erstellDat").append("='").append(getErstellDat()).append("' ");
        buffer.append("Standorts").append("='").append(getStandorts()).append("' ");
        buffer.append("objektsForBetreiberid").append("='").append(getObjekts()).append("' ");
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
        if (!(other instanceof Inhaber)) return false;
        return (this.getId().equals(
            ((Inhaber) other).getId()));
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
    public static Inhaber merge(Inhaber detachedInstance) {
        log.debug("Merging Inhaber instance " + detachedInstance);
        return (Inhaber) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Inhaber saved = Inhaber.merge(this);
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
    private void copy(Inhaber copy) {
        this.id = copy.getId();
        this.adresse = copy.getAdresse();
        this.wirtschaftszweig = copy.getWirtschaftszweig();
        this.anrede = copy.getAnrede();
        this.name = copy.getName();
        this.vorname = copy.getVorname();
        this.namezus = copy.getNamezus();
        this.namebetrbeauf = copy.getNamebetrbeauf();
        this.vornamebetrbeauf = copy.getVornamebetrbeauf();
        this.telefon = copy.getTelefon();
        this.telefax = copy.getTelefax();
        this.email = copy.getEmail();
        this.wassermenge = copy.getWassermenge();
        this.bemerkungen = copy.getBemerkungen();
        this.revidatum = copy.getRevidatum();
        this.revihandz = copy.getRevihandz();
        this.kassenzeichen = copy.getKassenzeichen();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
        this.auikWzCode = copy.getAuikWzCode();
        this.erstellDat = copy.getErstellDat();
        this.standorts = copy.getStandorts();
        this.iglId = copy.getIglId();
        this.objekts = copy.getObjekts();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Inhaber detachedInstance) {
        log.debug("Deleting Adresse instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Inhaber.delete(this);
    }

    /**
     * Find an <code>Adresse</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Adresse</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Inhaber findById(java.lang.Integer id) {
        log.debug("Getting Adresse instance with id: " + id);
        return (Inhaber)
            new DatabaseAccess().get(Inhaber.class, id);
    }

    /**
     * Get a list of all <code>Adresse</code>
     * @return <code>List&lt;Adresse&gt;</code>
     *         all <code>Adresse</code>
     */
    public static List<Inhaber> getAll() {
        return DatabaseQuery.getAll(new Inhaber());
    }

	public Standort getStandort() {
		Standort std = (Standort) getStandorts().toArray()[0];
		return std;
	}

    /* Custom code goes below here! */

}
