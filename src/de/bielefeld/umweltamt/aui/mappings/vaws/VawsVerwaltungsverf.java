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

package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'VAWS_VERWALTUNGSVERF' table. This class
 * may be customized as it is never re-generated after being created.
 */
public class VawsVerwaltungsverf extends AbstractVawsVerwaltungsverf implements
    Serializable {
    private static final long serialVersionUID = 1795859154833912562L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of VawsVerwaltungsverf instances.
     */
    public VawsVerwaltungsverf() {
    }

    /* Add customized code below */

    /**
     * Liefert alle Verfahrens-Einträge deren Wiedervorlage in der Vergangenheit
     * liegt und die nicht abgeschlossen sind.
     * @return Eine Liste mit VawsVerwaltungsverf-Objekten.
     */
    public static List<?> getAuswertung() {
        List<?> kontrollen;
        String query = "from VawsVerwaltungsverf vf where "
            + "vf.wiedervorlage < :today " + "and "
            + "(vf.wvverwverf = FALSE or vf.wvverwverf is NULL) "
            + "order by vf.wiedervorlage, vf.vawsFachdaten";

        kontrollen = new DatabaseAccess().createQuery(query)
            .setDate("today", new Date())
            .list();

        return kontrollen;
    }

    /**
     * Liefert alle Verwaltungsverfahren-Einträge zu einem bestimmten
     * VawsFachdatensatz.
     * @param fachdaten Der Fachdatensatz.
     * @return Eine Liste mit VawsVerwaltungsverf-Objekten.
     */
    public static List<?> getVerwaltungsverf(VawsFachdaten fachdaten) {
        List<?> verfahren;

        if (fachdaten.getBehaelterId() == null) {
            verfahren = new ArrayList<VawsVerwaltungsverf>();
        } else {
            verfahren = new DatabaseAccess()
                .createQuery(
                    "from VawsVerwaltungsverf vvf where "
                        + "vvf.vawsFachdaten = :fachdaten "
                        + "order by vvf.wvverwverf desc, vvf.datum, "
                        + "vvf.wiedervorlage")
                .setEntity("fachdaten", fachdaten)
                .list();

            log.debug(verfahren.size() + " Verfahrens-Einträge für FD "
                + fachdaten + " gefunden!");
        }

        return verfahren;
    }

    /**
     * Speichert einen VAWS-Verwaltungsverfahren-Eintrag in der Datenbank.
     * @param verfahren Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
     *         sonst <code>false</code>.
     */
    public static boolean saveVerfahren(VawsVerwaltungsverf verfahren) {
        boolean saved = false;
        saved = new DatabaseAccess().saveOrUpdate(verfahren);
        return saved;
    }

    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param verfahren Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Datensatz nicht in der Datenbank existiert).
     */
    public static boolean removeVerfahren(VawsVerwaltungsverf verfahren) {
        boolean removed = false;
        removed = new DatabaseAccess().delete(verfahren);
        return removed;
    }
}
