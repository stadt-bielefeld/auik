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

package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;

/**
 * A class that represents a row in the VAWS_BEHAELTERART table. You can
 * customize the behavior of this class by editing the class, {@link
 * VawsBehaelterart()}.
 */
public abstract class AbstractVawsBehaelterart implements Serializable {
    private static final long serialVersionUID = 3980143487505626240L;

    /** The value of the simple behaelterart property. */
    private java.lang.String behaelterart;

    /** The value of the simple id property. */
    private java.lang.Integer id;

    /**
     * Simple constructor of AbstractVawsBehaelterart instances.
     */
    public AbstractVawsBehaelterart() {
    }

    /**
     * Return the value of the BEHAELTERART column.
     * @return java.lang.String
     */
    public java.lang.String getBehaelterart() {
        return this.behaelterart;
    }

    /**
     * Set the value of the BEHAELTERART column.
     * @param behaelterart
     */
    public void setBehaelterart(java.lang.String behaelterart) {
        this.behaelterart = behaelterart;
    }

    /**
     * Return the value of the ID column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    /**
     * Set the value of the ID column.
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    // TODO: Why is there no equals and hashCode implementation here?
}
