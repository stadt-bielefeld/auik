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

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_56_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh52Fachdaten extends AbstractAnh52Fachdaten implements
    Serializable {
    private static final long serialVersionUID = -284019428699023860L;

    /**
     * Simple constructor of Anh52Fachdaten instances.
     */
    public Anh52Fachdaten() {
    }

    /**
     * Constructor of Anh52Fachdaten instances given a simple primary key.
     * @param id
     */
    public Anh52Fachdaten(java.lang.Integer id) {
        super(id);
    }

    @Override
    public String toString() {
        return "[ID:" + this.getObjektid() + "]";
    }

    public static Anh52Fachdaten getAnh52ByObjekt(BasisObjekt objekt) {
        if (!objekt.getBasisObjektarten().isAnh52()) {
            return null;
        }
        return (Anh52Fachdaten) new DatabaseAccess()
            .createQuery(
                "FROM Anh52Fachdaten as anhang52 "
                    + "WHERE anhang52.basisObjekt = :objekt")
            .setEntity("objekt", objekt)
            .uniqueResult();
    }

    public static boolean saveFachdaten(Anh52Fachdaten fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }

    /**
     * Liefert eine Liste mit allen Anhang52 Objekten.
     * @return Eine Liste aus Anh52Fachdaten.
     */
    public static List<?> getAuswertungsListe() {
        return new DatabaseAccess()
            .createQuery(
                "FROM Anh52Fachdaten as anh52 "
                    + "ORDER BY anh52.basisObjekt.inaktiv, anh52.id")
            .list();
    }
}
