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

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_SIELHAUT' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AtlSielhaut extends AbstractAtlSielhaut implements Serializable {
    private static final long serialVersionUID = 7832720588085682797L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of AtlSielhaut instances.
     */
    public AtlSielhaut() {
        // AUIKataster.debugOutput("Neues SielhautBearbeiten-Objekt",
        // "AtlSielhaut");

//        setSielhaut(true);
//        setAlarmplan(false);
//        setNachprobe(false);
//        setBSchlammprobe(false);

        Double zero = new Double(0.0);
        setRechtswert(zero);
        setHochwert(zero);
    }

    /**
     * Constructor of AtlSielhaut instances given a simple primary key.
     * @param id
     */
    public AtlSielhaut(java.lang.Integer id) {
        super(id);
    }

    /* Add customized code below */

    @Override
    public String toString() {
        return "[SielhautBearbeiten:" + getId() + ", " + getBezeichnung() + "]";
    }

    public static List<?> findPunkte(String suche) {
        String sucheF = suche.toLowerCase().trim() + "%";
        return new DatabaseAccess()
            .createQuery(
                "FROM AtlSielhaut as sp WHERE "
                    + "lower(sp.bezeichnung) like :suche "
                    + "ORDER BY sp.psielhaut desc, "
                    + "sp.pfirmenprobe desc, sp.bezeichnung")
            .setString("suche", sucheF)
            .list();
    }

    public static AtlSielhaut getSielhautByBez(String bezeichnung) {
        String sucheF = bezeichnung.toLowerCase().trim() + "%";
        return (AtlSielhaut) new DatabaseAccess()
            .createQuery(
                "FROM AtlSielhaut as sp WHERE "
                    + "lower(sp.bezeichnung) like :suche "
                    + "ORDER BY sp.psielhaut desc, "
                    + "sp.pfirmenprobe desc, sp.bezeichnung")
            .setString("suche", sucheF)
            .uniqueResult();
    }

    /**
     * Lädt ein Objekt aus der Datenbank.
     * @param id Der Primärschlüssel des zu ladenden Objekts.
     * @return Das BasisObjekt mit dem Primärschlüssel oder <code>null</code>,
     *         falls ein solches nicht gefunden wurde.
     */
    public static AtlSielhaut getSielhaut(Integer id) {
        AtlSielhaut sielhaut = null;
        sielhaut = (AtlSielhaut) new DatabaseAccess()
                .get(AtlSielhaut.class, id);
        return sielhaut;
    }

    public static boolean saveSielhautPunkt(AtlSielhaut spunkt) {
        boolean success = false;
        success = new DatabaseAccess().saveOrUpdate(spunkt);
        if (success) {
            log.debug("Sielhautpunkt " + spunkt + " erfolgreich gespeichert!");
        }
        return success;
    }
}
