/**
 * Copyright 2005-2011, Stadt Bielefeld
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

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.StupidHelperClassWhichWillBeGoneSoon;

/**
 * A class that represents a row in the ATL_KLAERANLAGEN table. You can
 * customize the behavior of this class by editing the class,
 * {@link AtlKlaeranlagen}.
 */
public abstract class AbstractAtlKlaeranlagen extends
StupidHelperClassWhichWillBeGoneSoon implements Serializable {
    private static final long serialVersionUID = -5840147815006399671L;

    /**
     * The cached hash code value for this instance. Settting to 0 triggers
     * re-calculation.
     */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Integer kaId;

    /** The value of the simple anlage property. */
    private java.lang.String anlage;

    /**
     * Simple constructor of AbstractAtlKlaeranlagen instances.
     */
    public AbstractAtlKlaeranlagen() {
    }

    /**
     * Constructor of AbstractAtlKlaeranlagen instances given a simple primary
     * key.
     * @param kaId
     */
    public AbstractAtlKlaeranlagen(java.lang.Integer kaId) {
        this.setKaId(kaId);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Integer
     */
    public java.lang.Integer getKaId() {
        return kaId;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param kaId
     */
    public void setKaId(java.lang.Integer kaId) {
        this.hashValue = 0;
        this.kaId = kaId;
    }

    /**
     * Return the value of the ANLAGE column.
     * @return java.lang.String
     */
    public java.lang.String getAnlage() {
        return this.anlage;
    }

    /**
     * Set the value of the ANLAGE column.
     * @param anlage
     */
    public void setAnlage(java.lang.String anlage) {
        this.anlage = anlage;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the
     * primary key values.
     * @param rhs
     * @return boolean
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs == null)
            return false;
        if (!(rhs instanceof AtlKlaeranlagen))
            return false;
        AtlKlaeranlagen that = (AtlKlaeranlagen) rhs;
        if (this.getKaId() != null && that.getKaId() != null) {
            if (!this.getKaId().equals(that.getKaId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation of the hashCode method conforming to the Bloch pattern
     * with the exception of array properties (these are very unlikely primary
     * key types).
     * @return int
     */
    @Override
    public int hashCode() {
        if (this.hashValue == 0) {
            int result = 17;
            int kaIdValue = this.getKaId() == null ? 0 : this.getKaId()
                    .hashCode();
            result = result * 37 + kaIdValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
