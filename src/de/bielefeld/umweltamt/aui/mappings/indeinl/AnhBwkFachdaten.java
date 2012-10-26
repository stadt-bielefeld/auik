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

import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_BWK' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AnhBwkFachdaten extends AbstractAnhBwkFachdaten implements
    Serializable {
    private static final long serialVersionUID = 3943318248066874613L;

    /**
     * Simple constructor of AnhBwk instances.
     */
    public AnhBwkFachdaten() {
    }

    /**
     * Constructor of AnhBwk instances given a simple primary key.
     * @param bwkId
     */
    public AnhBwkFachdaten(java.lang.Integer bwkId) {
        super(bwkId);
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

    public static AnhBwkFachdaten getAnhBwkByObjekt(BasisObjekt objekt) {
        if (!objekt.getBasisObjektarten().isBWK()) {
            return null;
        }
        return (AnhBwkFachdaten) new DatabaseAccess()
            .createQuery(
                "FROM AnhBwkFachdaten as brennwert "
                    + "WHERE brennwert.basisObjekt = :objekt")
            .setEntity("objekt", objekt)
            .uniqueResult();
    }

    /**
     * Speichert ein BWK Fachdaten-Objekt in der Datenbank.
     * @param fachdaten Das zu speichernde Fachdaten-Objekt.
     * @return <code>true</code>, wenn das Objekt gespeichert wurde, sonst
     *         <code>false</code>.
     */
    public static boolean saveBwk(AnhBwkFachdaten bwk) {
        return new DatabaseAccess().saveOrUpdate(bwk);
    }

    /**
     * Erzeugt eine Liste mit allen Brennwerkesseln eines bestimmten
     * Erfassungsjahrs.
     * @param jahr Das Erfassungsjahr (oder -1 wenn alle Kessel ausgegeben
     *            werden sollen).
     * @return Eine Liste aus AnhBwk-Objekten.
     */
    /* This was not used anywhere... */
//    private static List<?> findByErfassungsjahr(int jahr) {
//        String query = "FROM AnhBwkFachdaten as bwk ";
//
//        // TODO: LOL Year 2030 Bug? ...
//        if (jahr != -1) {
//            if (jahr > 0 && jahr < 100) {
//                if (jahr <= 30) {
//                    jahr = jahr + 2000;
//                } else {
//                    jahr = jahr + 1900;
//                }
//            }
//            query += "WHERE bwk.erfassung = :jahr ";
//        }
//
//        query += "ORDER BY bwk.basisObjekt.inaktiv, bwk.erfassung, "
//            + "bwk.basisObjekt.basisBetreiber.betrname, "
//            + "bwk.basisObjekt.basisBetreiber.betrnamezus, "
//            + "bwk.basisObjekt.basisStandort.strasse, "
//            + "bwk.basisObjekt.basisStandort.hausnr";
//
//        if (jahr != -1) {
//            return new DatabaseAccess().createQuery(query)
//                .setInteger("jahr", jahr)
//                .list();
//        } else {
//            return new DatabaseAccess().createQuery(query).list();
//        }
//    }
}
