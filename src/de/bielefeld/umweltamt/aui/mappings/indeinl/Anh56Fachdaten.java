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
public class Anh56Fachdaten extends AbstractAnh56Fachdaten implements
    Serializable {
    private static final long serialVersionUID = 8446447432730678482L;

    /**
     * Simple constructor of Anh56Fachdaten instances.
     */
    public Anh56Fachdaten() {
    }

    /**
     * Constructor of Anh56Fachdaten instances given a simple primary key.
     * @param id
     */
    public Anh56Fachdaten(java.lang.Integer id) {
        super(id);
    }

    @Override
    public String toString() {
        return "[ID:" + this.getObjektid() + "]";
    }

    /**
     * Liefert eine Liste mit allen Anhang56 Objekten.
     * @return Eine Liste aus Anh56Fachdaten.
     */
    public static List<?> getAuswertungsListe() {
        String query = "FROM Anh56Fachdaten as anh56 "
            + "ORDER BY anh56.basisObjekt.inaktiv, "
            + "anh56.basisObjekt.basisStandort.strasse, "
            + "anh56.basisObjekt.basisStandort.hausnr";
        return new DatabaseAccess().createQuery(query).list();
    }

    /**
     * Liefert eine Liste mit allen abwasserrelevanten Objekten.
     * @return Eine Liste aus Anh56Fachdaten.
     */
    public static List<?> getAbwasserListe() {
        String query = "FROM Anh56Fachdaten as anh56 "
            + "WHERE anh56.abwasseranfall = TRUE "
            + "ORDER BY anh56.basisObjekt.inaktiv, "
            + "anh56.basisObjekt.basisStandort.strasse, "
            + "anh56.basisObjekt.basisStandort.hausnr";
        return new DatabaseAccess().createQuery(query).list();
    }

    /**
     * Liefert eine Liste mit allen genehmigungspflichtigen Objekten.
     * @return Eine Liste aus Anh56Fachdaten.
     */
    public static List<?> getGenehmigungListe() {
        String query = "FROM Anh56Fachdaten as anh56 "
            + "WHERE anh56.genpflicht = TRUE "
            + "ORDER BY anh56.basisObjekt.inaktiv, "
            + "anh56.basisObjekt.basisStandort.strasse, "
            + "anh56.basisObjekt.basisStandort.hausnr";
        return new DatabaseAccess().createQuery(query).list();
    }

    public static Anh56Fachdaten getAnh56ByObjekt(BasisObjekt objekt) {
        if (!objekt.getBasisObjektarten().isAnh56()) {
            return null;
        }
        return (Anh56Fachdaten) new DatabaseAccess()
            .createQuery(
                "FROM Anh56Fachdaten as anhang56 WHERE "
                    + "anhang56 = :objekt")
            .setEntity("objekt", objekt)
            .uniqueResult();
    }

    public static boolean saveFachdaten(Anh56Fachdaten fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }
}
