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

package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'BASIS_GEMARKUNG' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class BasisGemarkung extends AbstractBasisGemarkung implements
    Serializable {
    private static final long serialVersionUID = -230613228647257429L;

    /**
     * Simple constructor of BasisGemarkung instances.
     */
    public BasisGemarkung() {
    }

    /**
     * Constructor of BasisGemarkung instances given a simple primary key.
     * @param gemarkungid
     */
    public BasisGemarkung(java.lang.Integer gemarkungid) {
        super(gemarkungid);
    }

    /* Add customized code below */

    /**
     * Liefert den Gemarkungsnamen.
     */
    @Override
    public String toString() {
        return super.getGemarkung();
    }

    /**
     * Liefert alle vorhandenen Gemarkungen.
     * @return Alle vorhandenen Gemarkungen
     */
    public static BasisGemarkung[] getGemarkungen() {
        return (BasisGemarkung[]) new DatabaseAccess().createQuery(
            "FROM BasisGemarkung bg ORDER BY bg.gemarkungid")
            .setCacheable(true)
            .setCacheRegion("gemarkungsliste")
            .array(new BasisGemarkung[0]);
    }
}
