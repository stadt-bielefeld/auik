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
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'VAWS_BasisObjektverknuepfung' table.
 * This class may be customized as it is never re-generated after being created.
 */
public class BasisObjektverknuepfung extends AbstractBasisObjektverknuepfung
    implements Serializable {
    private static final long serialVersionUID = 6989843733536180737L;

    /**
     * Simple constructor of VawsObjektchrono instances.
     */
    public BasisObjektverknuepfung() {
    }

    /**
     * Constructor of VawsObjektchrono instances given a simple primary key.
     * @param id
     */
    public BasisObjektverknuepfung(java.lang.Integer id) {
        super(id);
    }

    /* Add customized code below */

    // Statischer Teil:

    /**
     * Liefert alle verknuepften Objekte zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit Objekten.
     */
    public static List<?> getVerknuepfungByObjekt(BasisObjekt objekt) {
        return new DatabaseAccess()
            .createQuery(
                "FROM BasisObjektverknuepfung ov WHERE "
                    + "ov.basisObjektByObjekt = :objekt "
                    + "or ov.basisObjektByIstVerknuepftMit = :objekt ")
            .setEntity("objekt", objekt)
            .list();
    }

    /**
     * Liefert alle verknuepften Sielhautmessstellen zu einem bestimmten
     * BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit Objekten.
     */
    public static List<?> getVerknuepfungSielhaut(BasisObjekt objekt) {
        return new DatabaseAccess()
            .createQuery(
                "FROM BasisObjektverknuepfung ov WHERE "
                    + "ov.basisObjektByObjekt = :objekt "
                    + "and ov.basisObjektByIstVerknuepftMit.basisObjektarten.objektart like 'Sielhautmessstelle' ")
            .setEntity("objekt", objekt)
            .list();
    }

    /**
     * Speichert einen Objektverknuepfungs-Eintrag in der Datenbank.
     * @param verknuepf Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
     *         sonst <code>false</code>.
     */
    public static boolean saveObjektVerknuepfung(
        BasisObjektverknuepfung verknuepf) {
        return new DatabaseAccess().saveOrUpdate(verknuepf);
    }

    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param verknuepf Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Datensatz nicht in der Datenbank existiert).
     */
    public static boolean removeObjektVerknuepfung(
        BasisObjektverknuepfung verknuepf) {
        return new DatabaseAccess().delete(verknuepf);
    }

    /**
     * Liefert einen String mit der ID.
     */
    @Override
    public String toString() {
        String tmp = getId() + ": ";
        return tmp;
    }
}
