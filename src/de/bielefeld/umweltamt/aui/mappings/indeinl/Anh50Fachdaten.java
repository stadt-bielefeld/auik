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
import java.util.Date;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_50_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh50Fachdaten extends AbstractAnh50Fachdaten implements
    Serializable {
    private static final long serialVersionUID = -6104395668554659237L;

    /**
     * Simple constructor of Anh50Fachdaten instances.
     */
    public Anh50Fachdaten() {
    }

    /**
     * Constructor of Anh50Fachdaten instances given a simple primary key.
     * @param firmenid
     */
    public Anh50Fachdaten(java.lang.Integer firmenid) {
        super(firmenid);
    }

    @Override
    public String toString() {
        return "[ID:" + this.getObjektid() + "]";
    }

    /**
     * Sucht alle Anhang50-Fachdatensätze, bei denen Amalgam selektiert ist und
     * die nicht erloschen sind.
     * @param nurWiedervorlageAbgelaufen Sollen nur Datensätze angezeigt werden,
     *            deren Wiedervorlage in der Vergangenheit liegt?
     * @return Eine Liste mit den entstprechenden Anh50Fachdaten.
     */
    public static List<?> findByWiedervorlage(boolean nurWiedervorlageAbgelaufen) {
        if (nurWiedervorlageAbgelaufen) {
            return new DatabaseAccess()
                .createQuery(
                    "FROM Anh50Fachdaten as anh50 "
                        + "WHERE anh50.wiedervorlage <= :today "
                        + "and anh50.erloschen = 'f' "
                        + "and anh50.basisObjekt.inaktiv = 'f' "
                        + "ORDER BY anh50.wiedervorlage, "
                        + "anh50.basisObjekt.basisBetreiber.betrname")
                .setDate("today", new Date())
                .list();

        } else {
            return new DatabaseAccess()
                .createQuery(
                    "FROM Anh50Fachdaten as anh50 WHERE "
                        + "anh50.erloschen = 'f' "
                        + "and anh50.basisObjekt.inaktiv = 'f' "
                        + "ORDER BY anh50.wiedervorlage, "
                        + "anh50.basisObjekt.basisBetreiber.betrname")
                .list();
        }
    }

    public static Anh50Fachdaten getAnh50ByObjekt(BasisObjekt objekt) {
        if (!objekt.getBasisObjektarten().isAnh50()) {
            return null;
        }
        return (Anh50Fachdaten) new DatabaseAccess()
            .createQuery(
                "FROM Anh50Fachdaten as anhang50 WHERE "
                    + "anhang50.objektid = :objekt ")
            .setEntity("objekt", objekt)
            .uniqueResult();
    }

    public static boolean saveFachdaten(Anh50Fachdaten fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }
}
