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
 * A class that represents a row in the 'ANH_BWK' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class ViewBwk extends AbstractViewBwk implements Serializable {
    private static final long serialVersionUID = 4609805662436961510L;

    /**
     * Simple constructor of AnhBwk instances.
     */
    public ViewBwk() {
    }

    /**
     * Constructor of AnhBwk instances given a simple primary key.
     * @param bwkId
     */
    public ViewBwk(java.lang.Integer bwkId) {
        super(bwkId);
    }

    /**
     * Liefert einen String der Form "[BWK:ID,Hersteller Typ]"
     */
    @Override
    public String toString() {
        return "[BWK:" + getBwkId() + "," + getKHersteller() + " " + getKTyp()
            + "]";
    }

    public static ViewBwk getAnhBwkByObjekt(BasisObjekt objekt) {
        ViewBwk bwk = null;
        if (objekt.getBasisObjektarten().isBWK()) {
            List<?> brennwert = new DatabaseAccess()
                .createQuery(
                    "from ViewBwk as brennwert where "
                        + "brennwert.basisObjekt = :objekt")
                .setEntity("objekt", objekt)
                .list();

            if (brennwert.size() > 0) {
                bwk = (ViewBwk) brennwert.get(0);
            }
        }
        return bwk;
    }

    /**
     * Speichert ein BWK Fachdaten-Objekt in der Datenbank.
     * @param fachdaten Das zu speichernde Fachdaten-Objekt.
     * @return <code>true</code>, wenn das Objekt gespeichert wurde, sonst
     *         <code>false</code>.
     */
    public static boolean saveBwk(ViewBwk bwk) {
        boolean saved = false;
        saved = new DatabaseAccess().saveOrUpdate(bwk);
        return saved;
    }

    /**
     * Erzeugt eine Liste mit allen Brennwerkesseln eines bestimmten
     * Erfassungsjahrs.
     * @param jahr Das Erfassungsjahr (oder -1 wenn alle Kessel ausgegeben
     *            werden sollen).
     * @return Eine Liste aus AnhBwk-Objekten.
     */
    public static List<?> findByErfassungsjahr(int jahr) {
        List<?> liste;

        String query = "from ViewBwk as bwk ";

        // TODO: No more comment here ;-)
        if (jahr != -1) {
            if (jahr > 0 && jahr < 100) {
                if (jahr <= 30) {
                    jahr = jahr + 2000;
                } else {
                    jahr = jahr + 1900;
                }
            }
            query += "where bwk.erfassung = :jahr ";
        }

        if (jahr != -1) {
            liste = new DatabaseAccess().createQuery(query)
                .setInteger("jahr", jahr)
                .list();
        } else {
            liste = new DatabaseAccess().createQuery(query).list();
        }

        return liste;
    }
}
