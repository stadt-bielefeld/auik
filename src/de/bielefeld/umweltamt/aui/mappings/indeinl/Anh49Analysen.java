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
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_49_ANALYSEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh49Analysen extends AbstractAnh49Analysen implements
    Serializable {
    private static final long serialVersionUID = 2452591869709833506L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of Anh49Analysen instances.
     */
    public Anh49Analysen() {
    }

    /**
     * Constructor of Anh49Analysen instances given a simple primary key.
     * @param analysenid
     */
    public Anh49Analysen(java.lang.Integer analysenid) {
        super(analysenid);
    }

    /* Add customized code below */
    /**
     * Liefert einen String der Form "[Datum: DATUM und ANALYSENID]".
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[Datum:" + getDatum() + ", " + getAnalysenid() + "]";
    }

    /**
     * Liefert alle Analysen eines bestimmten Fachdatenobjekts.
     */
    public static List<?> getAnalyse(Anh49Fachdaten fd) {
        List<?> analyse = null;
        analyse = new DatabaseAccess().createQuery(
            "from Anh49Analysen as analyse where "
                + "analyse.anh49Fachdaten = :fd "
                + "order by analyse.datum")
            .setEntity("fd", fd)
            .list();
        log.debug("Analysen für " + fd + ", Anzahl: " + analyse.size());
        return analyse;
    }

    public static boolean saveOrUpdateAnalyse(Anh49Analysen analyse) {
        boolean success = false;
        success = new DatabaseAccess().saveOrUpdate(analyse);
        return success;
    }

    public static boolean removeAnalyse(Anh49Analysen analyse) {
        boolean removed = false;
        removed = new DatabaseAccess().delete(analyse);
        return removed;
    }
}
