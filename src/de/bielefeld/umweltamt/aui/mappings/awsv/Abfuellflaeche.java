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

package de.bielefeld.umweltamt.aui.mappings.awsv;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.mappings.awsv.Abfuellflaeche;
import de.bielefeld.umweltamt.aui.mappings.awsv.Fachdaten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import java.util.List;
import java.util.Set;

/**
 * A class that represents a row in the Abfuellflaeche database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class Abfuellflaeche  implements java.io.Serializable {

    /** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forAbfuellflaeche;

    /* Primary key, foreign keys (relations) and table columns */
    private Integer behaelterid;
    private Fachdaten fachdaten;
    private Boolean eoh;
    private Boolean ef;
    private Boolean abfsaniert;
    private Boolean abfneuerstellt;
    private String bodenflaechenausf;
    private String beschbodenfl;
    private Float dicke;
    private String guete;
    private String fugenmaterial;
    private String beschfugenmat;
    private String niederschlagschutz;
    private Boolean abscheidervorh;
    private String beschableitung;
    private boolean enabled;
    private boolean deleted;
    private Integer id;
    private Integer groesse;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public Abfuellflaeche() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public Abfuellflaeche(
        Fachdaten fachdaten, boolean enabled, boolean deleted, Integer id) {
        this.fachdaten = fachdaten;
        this.enabled = enabled;
        this.deleted = deleted;
        this.id = id;
    }

    /** Full constructor */
    public Abfuellflaeche(
        Fachdaten fachdaten, Boolean eoh, Boolean ef, Boolean abfsaniert, Boolean abfneuerstellt, String bodenflaechenausf, String beschbodenfl, Float dicke, String guete, String fugenmaterial, String beschfugenmat, String niederschlagschutz, Boolean abscheidervorh, String beschableitung, boolean enabled, boolean deleted, Integer id, Integer groesse) {
        this.fachdaten = fachdaten;
        this.eoh = eoh;
        this.ef = ef;
        this.abfsaniert = abfsaniert;
        this.abfneuerstellt = abfneuerstellt;
        this.bodenflaechenausf = bodenflaechenausf;
        this.beschbodenfl = beschbodenfl;
        this.dicke = dicke;
        this.guete = guete;
        this.fugenmaterial = fugenmaterial;
        this.beschfugenmat = beschfugenmat;
        this.niederschlagschutz = niederschlagschutz;
        this.abscheidervorh = abscheidervorh;
        this.beschableitung = beschableitung;
        this.enabled = enabled;
        this.deleted = deleted;
        this.id = id;
        this.groesse = groesse;
    }

    /* Setter and getter methods */
    public Integer getBehaelterid() {
        return this.behaelterid;
    }

    public void setBehaelterid(Integer behaelterid) {
        this.behaelterid = behaelterid;
    }

    public Fachdaten getFachdaten() {
        return this.fachdaten;
    }

    public void setFachdaten(Fachdaten fachdaten) {
        this.fachdaten = fachdaten;
    }

    public Boolean getEoh() {
        return this.eoh;
    }

    public void setEoh(Boolean eoh) {
        this.eoh = eoh;
    }

    public Boolean getEf() {
        return this.ef;
    }

    public void setEf(Boolean ef) {
        this.ef = ef;
    }

    public Boolean getAbfsaniert() {
        return this.abfsaniert;
    }

    public void setAbfsaniert(Boolean abfsaniert) {
        this.abfsaniert = abfsaniert;
    }

    public Boolean getAbfneuerstellt() {
        return this.abfneuerstellt;
    }

    public void setAbfneuerstellt(Boolean abfneuerstellt) {
        this.abfneuerstellt = abfneuerstellt;
    }

    public String getBodenflaechenausf() {
        return this.bodenflaechenausf;
    }

    public void setBodenflaechenausf(String bodenflaechenausf) {
        this.bodenflaechenausf = bodenflaechenausf;
    }

    public String getBeschbodenfl() {
        return this.beschbodenfl;
    }

    public void setBeschbodenfl(String beschbodenfl) {
        this.beschbodenfl = beschbodenfl;
    }

    public Float getDicke() {
        return this.dicke;
    }

    public void setDicke(Float dicke) {
        this.dicke = dicke;
    }

    public String getGuete() {
        return this.guete;
    }

    public void setGuete(String guete) {
        this.guete = guete;
    }

    public String getFugenmaterial() {
        return this.fugenmaterial;
    }

    public void setFugenmaterial(String fugenmaterial) {
        this.fugenmaterial = fugenmaterial;
    }

    public String getBeschfugenmat() {
        return this.beschfugenmat;
    }

    public void setBeschfugenmat(String beschfugenmat) {
        this.beschfugenmat = beschfugenmat;
    }

    public String getNiederschlagschutz() {
        return this.niederschlagschutz;
    }

    public void setNiederschlagschutz(String niederschlagschutz) {
        this.niederschlagschutz = niederschlagschutz;
    }

    public Boolean getAbscheidervorh() {
        return this.abscheidervorh;
    }

    public void setAbscheidervorh(Boolean abscheidervorh) {
        this.abscheidervorh = abscheidervorh;
    }

    public String getBeschableitung() {
        return this.beschableitung;
    }

    public void setBeschableitung(String beschableitung) {
        this.beschableitung = beschableitung;
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroesse() {
        return this.groesse;
    }

    public void setGroesse(Integer groesse) {
        this.groesse = groesse;
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
        buffer.append("fachdaten").append("='").append(getFachdaten()).append("' ");
        buffer.append("eoh").append("='").append(getEoh()).append("' ");
        buffer.append("ef").append("='").append(getEf()).append("' ");
        buffer.append("abfsaniert").append("='").append(getAbfsaniert()).append("' ");
        buffer.append("abfneuerstellt").append("='").append(getAbfneuerstellt()).append("' ");
        buffer.append("bodenflaechenausf").append("='").append(getBodenflaechenausf()).append("' ");
        buffer.append("beschbodenfl").append("='").append(getBeschbodenfl()).append("' ");
        buffer.append("dicke").append("='").append(getDicke()).append("' ");
        buffer.append("guete").append("='").append(getGuete()).append("' ");
        buffer.append("fugenmaterial").append("='").append(getFugenmaterial()).append("' ");
        buffer.append("beschfugenmat").append("='").append(getBeschfugenmat()).append("' ");
        buffer.append("niederschlagschutz").append("='").append(getNiederschlagschutz()).append("' ");
        buffer.append("abscheidervorh").append("='").append(getAbscheidervorh()).append("' ");
        buffer.append("beschableitung").append("='").append(getBeschableitung()).append("' ");
        buffer.append("enabled").append("='").append(isEnabled()).append("' ");
        buffer.append("deleted").append("='").append(isDeleted()).append("' ");
        buffer.append("id").append("='").append(getId()).append("' ");
        buffer.append("groesse").append("='").append(getGroesse()).append("' ");
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
        if (!(other instanceof Abfuellflaeche)) return false;
        return (this.getBehaelterid().equals(
            ((Abfuellflaeche) other).getBehaelterid()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getBehaelterid() == null ?
            0 : this.getBehaelterid().hashCode();
        result = result * 37 + idValue;
        return result;
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>Abfuellflaeche</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static Abfuellflaeche merge(Abfuellflaeche detachedInstance) {
        log.debug("Merging Abfuellflaeche instance " + detachedInstance);
        return (Abfuellflaeche) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        Abfuellflaeche saved = Abfuellflaeche.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this Abfuellflaeche with its new values.<br>
     * This is meant to be used after merging!
     * @param copy Abfuellflaeche
     */
    private void copy(Abfuellflaeche copy) {
        this.fachdaten = copy.getFachdaten();
        this.eoh = copy.getEoh();
        this.ef = copy.getEf();
        this.abfsaniert = copy.getAbfsaniert();
        this.abfneuerstellt = copy.getAbfneuerstellt();
        this.bodenflaechenausf = copy.getBodenflaechenausf();
        this.beschbodenfl = copy.getBeschbodenfl();
        this.dicke = copy.getDicke();
        this.guete = copy.getGuete();
        this.fugenmaterial = copy.getFugenmaterial();
        this.beschfugenmat = copy.getBeschfugenmat();
        this.niederschlagschutz = copy.getNiederschlagschutz();
        this.abscheidervorh = copy.getAbscheidervorh();
        this.beschableitung = copy.getBeschableitung();
        this.enabled = copy.isEnabled();
        this.deleted = copy.isDeleted();
        this.id = copy.getId();
        this.groesse = copy.getGroesse();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(Abfuellflaeche detachedInstance) {
        log.debug("Deleting Abfuellflaeche instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return Abfuellflaeche.delete(this);
    }

    /**
     * Find an <code>Abfuellflaeche</code> instance by its primary key
     * @param id the primary key value
     * @return <code>Abfuellflaeche</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static Abfuellflaeche findById(java.lang.Integer id) {
        log.debug("Getting Abfuellflaeche instance with id: " + id);
        return (Abfuellflaeche)
            new DatabaseAccess().get(Abfuellflaeche.class, id);
    }

    /**
     * Get a list of all <code>Abfuellflaeche</code>
     * @return <code>List&lt;Abfuellflaeche&gt;</code>
     *         all <code>Abfuellflaeche</code>
     */
    public static List<Abfuellflaeche> getAll() {
        return DatabaseQuery.getAll(new Abfuellflaeche());
    }

    /* Custom code goes below here! */

	public static Abfuellflaeche findByBehaelterid(Integer id) {
        Fachdaten fd = (Fachdaten) HibernateSessionFactory.currentSession().createQuery("from Fachdaten where behaelterid= " + id).list().get(0);
        Set<Abfuellflaeche> list = fd.getAbfuellflaeches();
		if (list.size() != 0) {
			return list.iterator().next();
		}
		return null;
	}
}
