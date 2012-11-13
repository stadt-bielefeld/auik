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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'ANH_56_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh55Fachdaten extends AbstractAnh55Fachdaten implements
    Serializable {
    private static final long serialVersionUID = -3460688685883517525L;

    /**
     * Simple constructor of Anh55Fachdaten instances.
     */
    public Anh55Fachdaten() {
    }

    /**
     * Constructor of Anh55Fachdaten instances given a simple primary key.
     * @param id
     */
    public Anh55Fachdaten(java.lang.Integer id) {
        super(id);
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

    public static Anh55Fachdaten getAnh55ByObjekt(BasisObjekt objekt) {
        return (Anh55Fachdaten) new DatabaseAccess()
            .createQuery(
                "FROM Anh55Fachdaten as anhang55 WHERE "
                    + "anhang55.basisObjekt = :objekt")
            .setEntity("objekt", objekt)
            .uniqueResult();
    }

    public static boolean saveFachdaten(Anh55Fachdaten fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }

    /**
     * Liefert eine Liste mit allen Anhang55 Objekten.
     * @return Eine Liste aus Anh55Fachdaten.
     */
    public static List<?> getAuswertungsListe() {
        return new DatabaseAccess()
            .createQuery(
                "FROM Anh55Fachdaten as anh55 "
                    + "ORDER BY anh55.basisObjekt.inaktiv, anh55.id")
            .list();
    }
}
