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
 * A class that represents a row in the 'SUEV_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class AnhSuevFachdaten extends AbstractAnhSuevFachdaten implements
    Serializable {
    private static final long serialVersionUID = -7219885732827244977L;

    /**
     * Simple constructor of SuevFachdaten instances.
     */
    public AnhSuevFachdaten() {
    }

    /**
     * Constructor of SuevFachdaten instances given a simple primary key.
     * @param suevid
     */
    public AnhSuevFachdaten(java.lang.Integer suevid) {
        super(suevid);
    }

    /**
     * Liefert einen String der Form "[SuevKan Verfahren:ID]"
     */
    @Override
    public String toString() {
        return "[SuevKan Verfahren:" + getObjektid() + "]";
    }

    public static AnhSuevFachdaten getSuevByObjekt(BasisObjekt objekt) {
        AnhSuevFachdaten fachdaten = null;
        if (objekt.getBasisObjektarten().isSuev()) {
            List<?> suev = new DatabaseAccess()
                .createQuery(
                    "from AnhSuevFachdaten as suev "
                        + "where suev.basisObjekt = :objekt")
                .setEntity("objekt", objekt)
                .list();

            if (suev.size() > 0) {
                fachdaten = (AnhSuevFachdaten) suev.get(0);
            }
        }
        return fachdaten;
    }

    /**
     * Speichert ein SUEV-KAN Fachdaten-Objekt in der Datenbank.
     * @param fachdaten Das zu speichernde Fachdaten-Objekt.
     * @return <code>true</code>, wenn das Objekt gespeichert wurde, sonst
     *         <code>false</code>.
     */
    public static boolean saveFachdaten(AnhSuevFachdaten fachdaten) {
        boolean saved = false;
        saved = new DatabaseAccess().saveOrUpdate(fachdaten);
        return saved;
    }

    /**
     * Liefert eine Liste mit allen SUEV-KAN Objekten.
     * @return Eine Liste aus SuevFachdaten.
     */
    public static List<?> getAuswertungsListe() {
        List<?> liste;
        String query = "from AnhSuevFachdaten as sv "
            + "order by sv.basisObjekt.inaktiv, sv.objektid";
        liste = new DatabaseAccess().createQuery(query).list();
        return liste;
    }
}
