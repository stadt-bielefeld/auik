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

package de.bielefeld.umweltamt.aui.mappings.elka;

import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.DatabaseSerialVersionUID;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the MapElkaEinheit database table.<br>
 * This class is meant to serve as a model and should be copied into the
 * appropriate package and may be customized below the given mark.
 */

public class MapElkaGewkennz implements java.io.Serializable {
	/** Generated serialVersionUID for Serializable interface */
	private static final long serialVersionUID = DatabaseSerialVersionUID.forMapElkaGewkennz;



	/* Primary key, foreign keys (relations) and table columns */
	private Integer gewkz;
	private String gewname;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	/** Default constructor */
	public MapElkaGewkennz() {
		// This place is intentionally left blank.
	}

	/** Minimal constructor */
	public MapElkaGewkennz(Integer gewkz) {
		this.gewkz = gewkz;
	}

	/** Full constructor */
	public MapElkaGewkennz(Integer gewkz, String gewname) {
		this.gewkz = gewkz;
		this.gewname = gewname;

	}

	/* Setter and getter methods */
	public Integer getGewkz() {
		return this.gewkz;
	}

	public void setGewkz(Integer gewkz) {
		this.gewkz = gewkz;
	}

	public String getGewname() {
		return this.gewname;
	}

	public void setGewname(String gewname) {
		this.gewname = gewname;
	}

	/**
	 * To implement custom toString methods, jump to not generated code.<br>
	 * Basically we either call on <code>toDebugString</code> for a debug string,
	 * call on <code>toGuiString</code> for a gui representation or do something
	 * completely different.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return DatabaseClassToString.toStringForClass(this);
	}

	public String toDebugString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("gewkz").append("='").append(getGewkz()).append("' ");
		buffer.append("gewname").append("='").append(getGewname()).append("' ");

		return buffer.toString();
	}

	/**
	 * @param other
	 * @return <code>true</code>, if this and other are equal, <code>false</code>
	 *         otherwise
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof MapElkaGewkennz))
			return false;
		return (this.getGewkz().equals(((MapElkaGewkennz) other).getGewkz()));
	}

	/**
	 * Calculate a unique hashCode
	 *
	 * @return <code>int</code>
	 */
	@Override
	public int hashCode() {
		int result = 17;
		int idValue = this.getGewkz() == null ? 0 : this.getGewkz().hashCode();
		result = result * 37 + idValue;
		return result;
	}

	/**
	 * Merge (save or update) a detached instance
	 *
	 * @param detachedInstance the instance to merge
	 * @return <code>MapElkaAnhang</code> the merged instance, if everything went
	 *         okay, <code>null</code> otherwise
	 */
	public static MapElkaGewkennz merge(MapElkaGewkennz detachedInstance) {
		log.debug("Merging MapElkaGewkennz instance " + detachedInstance);
		return (MapElkaGewkennz) new DatabaseAccess().merge(detachedInstance);
	}

	/**
	 * Merge (save or update) this instance
	 *
	 * @return <code>true</code>, if everything went okay, <code>false</code>
	 *         otherwise
	 */
	public boolean merge() {
		MapElkaGewkennz saved = MapElkaGewkennz.merge(this);
		if (saved == null) {
			return false;
		} else {
			this.copy(saved);
			return true;
		}
	}

	/**
	 * Update this MapElkaEinheit with its new values.<br>
	 * This is meant to be used after merging!
	 *
	 * @param copy MapElkaEinheit
	 */
	private void copy(MapElkaGewkennz copy) {
		this.gewkz = copy.getGewkz();
		this.gewname = copy.getGewname();

	}


	/**
     * Delete (mark as deleted) a detached instance
     * @param detachedInstance the instance to delete
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public static boolean delete(MapElkaGewkennz detachedInstance) {
        log.debug("Deleting MapElkaGewkennz instance " + detachedInstance);
        return new DatabaseAccess().delete(detachedInstance);
    }

    /**
     * Find an <code>MapElkaGewkennz</code> instance by its primary key
     * @param id the primary key value
     * @return <code>MapElkaGewkennz</code> the requested instance,
     *         if one exists,
     *         <code>null</code> otherwise
     */
    public static MapElkaGewkennz findByGewkz(java.lang.Integer gewkz) {
        log.debug("Getting MapElkaGewkennz instance with id: " + gewkz);
        return (MapElkaGewkennz)
            new DatabaseAccess().get(MapElkaGewkennz.class, gewkz);
    }

    /**
     * Get a list of all <code>MapElkaGewkennz</code>
     * @return <code>List&lt;MapElkaGewkennz&gt;</code>
     *         all <code>MapElkaGewkennz</code>
     */
    public static List<MapElkaGewkennz> getAll() {
        return DatabaseQuery.getAll(new MapElkaGewkennz());
    }


    /* Custom code goes below here! */


}