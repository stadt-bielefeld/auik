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

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_49_KONTROLLEN' table. This class
 * may be customized as it is never re-generated after being created.
 */
public class Anh49Kontrollen extends AbstractAnh49Kontrollen implements
    Serializable {
    private static final long serialVersionUID = 6385012584069870464L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of Anh49Ortstermine instances.
     */
    public Anh49Kontrollen() {
    }

    /**
     * Constructor of Anh49Ortstermine instances given a simple primary key.
     * @param ortsterminid
     */
    public Anh49Kontrollen(java.lang.Integer Id) {
        super(Id);
    }

    /* Add customized code below */

    /**
     * Liefert einen String der Form "[Datum: Prüfdatum und Pruefergebnis]".
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[Prüfdatum: " + AuikUtils.getStringFromDate(getPruefdatum())
            + ", Ergebnis: " + getPruefergebnis() + "]";
    }

    /**
     * Liefert alle Kontrollen eines bestimmten Fachdatenobjekts.
     */
    public static List<?> getKontrollen(Anh49Fachdaten fd) {
        List<?> list = null;
        list = new DatabaseAccess()
            .createQuery(
                "FROM Anh49Kontrollen as kt "
                    + "WHERE kt.anh49Fachdaten = :fd "
                    + "ORDER BY kt.pruefdatum")
            .setEntity("fd", fd)
            .list();
        log.debug("Kontrollen für " + fd + ", Anzahl: " + list.size());
        return list;
    }

    public static boolean saveOrUpdateOrtstermin(Anh49Kontrollen kt) {
        return new DatabaseAccess().saveOrUpdate(kt);
    }

    public static boolean removeOrtstermin(Anh49Kontrollen kt) {
        return new DatabaseAccess().delete(kt);
    }

    public static boolean saveOrUpdateAnalyse(Anh49Kontrollen kontrolle) {
        return new DatabaseAccess().saveOrUpdate(kontrolle);
    }
}
