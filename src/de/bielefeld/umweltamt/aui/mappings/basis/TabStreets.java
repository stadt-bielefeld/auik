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

import java.util.List;

/**
 * A class that represents a row in the BasisStrassen database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */
public class TabStreets  implements java.io.Serializable {

    /**
	 *
	 */
	/** Generated serialVersionUID for Serializable interface */
    private static final long serialVersionUID =
        DatabaseSerialVersionUID.forTabStreets;

    /* Primary key, foreign keys (relations) and table columns */
    private String name;
    private String nr;
    private Float x;
    private Float y;
    private String gemeinde;
    private String schl;
    private Integer hausnr;
    private String hausnrZusatz;
    private String abgleich;
    private String plz;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Default constructor */
    public TabStreets() {
        // This place is intentionally left blank.
    }

    /** Minimal constructor */
    public TabStreets(
    		String abgleich) {
        this.abgleich = abgleich;
    }

    /** Full constructor */
    public TabStreets(
        String name, String nr, Float x, Float y, String gemeinde, String schl, Integer hausnr, String hausnrZusatz, String abgleich, String plz) {
        this.name = name;
        this.nr = nr;
        this.x = x;
        this.y = y;
        this.gemeinde = gemeinde;
        this.schl = schl;
        this.hausnr = hausnr;
        this.hausnrZusatz = hausnrZusatz;
        this.abgleich = abgleich;
        this.plz = plz;
    }

    /* Setter and getter methods */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNr() {
        return this.nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public Float getX() {
        return this.x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return this.y;
    }

    public void setY(Float y) {
        this.y = y;
    }
    public String getGemeinde() {
        return this.gemeinde;
    }

    public void setGemeinde(String gemeinde) {
        this.gemeinde = gemeinde;
    }

    public String getSchl() {
        return this.schl;
    }

    public void setSchl(String schl) {
        this.schl = schl;
    }

    public Integer getHausnr() {
        return this.hausnr;
    }

    public void setHausnr(Integer hausnr) {
        this.hausnr = hausnr;
    }
    public String getHausnrZusatz() {
        return this.hausnrZusatz;
    }

    public void setHausnrZusatz(String hausnrZusatz) {
        this.hausnrZusatz = hausnrZusatz;
    }

    public String getAbgleich() {
        return this.abgleich;
    }

    public void setAbgleich(String abgleich) {
        this.abgleich = abgleich;
    }

    public String getPlz() {
        return this.plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
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
     * Get a string representation for the gui
     * @return String
     */
    public String toGuiString() {
        return getName();
    }

    /**
     * Get a string representation for debugging
     * @return String
     */
    public String toDebugString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("name").append("='").append(getName()).append("' ");
        buffer.append("nr").append("='").append(getNr()).append("' ");
        buffer.append("x").append("='").append(getX()).append("' ");
        buffer.append("y").append("='").append(getY()).append("' ");
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
        if (!(other instanceof TabStreets)) return false;
        return (this.getAbgleich().equals(
            ((TabStreets) other).getAbgleich()));
    }

    /**
     * Calculate a unique hashCode
     * @return <code>int</code>
     */
    @Override
    public int hashCode() {
        int result = 17;
        int idValue = this.getAbgleich() == null ?
            0 : this.getAbgleich().hashCode();
        result = result * 37 + idValue;
        return result;
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>BasisStrassen</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static TabStreets merge(TabStreets detachedInstance) {
        log.debug("Merging BasisTabStreets instance " + detachedInstance);
        return (TabStreets) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        TabStreets saved = TabStreets.merge(this);
        if (saved == null) {
            return false;
        } else {
            this.copy(saved);
            return true;
        }
    }

    /**
     * Update this BasisStrassen with its new values.<br>
     * This is meant to be used after merging!
     * @param copy BasisStrassen
     */
    private void copy(TabStreets copy) {
        this.name = copy.getName();
        this.nr = copy.getNr();
        this.x = copy.getX();
        this.y = copy.getY();
    }

    /**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(TabStreets detachedInstance) {
        log.debug("Deleting BasisTabStreets instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Delete (mark as deleted) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean delete() {
        return TabStreets.delete(this);
    }

    /**
     * Find an <code>BasisStrassen</code> instance by its primary key
     * @param id the primary key value
     * @return <code>BasisStrassen</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static TabStreets findById(java.lang.Integer id) {
        log.debug("Getting BasisStrassen instance with id: " + id);
        return (TabStreets)
            new DatabaseAccess().get(TabStreets.class, id);
    }

    /**
     * Get a list of all <code>BasisStrassen</code>
     * @return <code>List&lt;BasisStrassen&gt;</code>
     *         all <code>BasisStrassen</code>
     */
    public static List<TabStreets> getAll() {
        return DatabaseQuery.getAll(new TabStreets());
    }

	public String getStrasse() {
		// TODO Auto-generated method stub
		return null;
	}

//    /* Custom code goes below here! */
//	public BasisTabStreets(String strasse){
//		this.strasse = strasse;
//		this.enabled = true;
//		this.deleted = true;
//	}
}
