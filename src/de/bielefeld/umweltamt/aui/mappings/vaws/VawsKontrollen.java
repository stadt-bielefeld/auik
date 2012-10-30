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

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the 'VAWS_KONTROLLEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class VawsKontrollen extends AbstractVawsKontrollen implements
    Serializable {
    private static final long serialVersionUID = 7151680384864528912L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of VawsKontrollen instances.
     */
    public VawsKontrollen() {
    }

    /**
     * Constructor of VawsKontrollen instances given a simple primary key.
     * @param kontrollid
     */
    public VawsKontrollen(java.lang.Integer kontrollid) {
        super(kontrollid);
    }

    // Statischer Teil:

    /**
     * Liefert alle Kontroll-Einträge deren "Nächste Prüfung" in der
     * Vergangenheit liegt und die nicht abgeschlossen sind.
     * @return Eine Liste mit VawsKontrollen-Objekten.
     */
    public static List<?> getAuswertung() {
        String query = "FROM VawsKontrollen vk "
            + "WHERE vk.naechstepruefung < :today "
            + "and vk.pruefungabgeschlossen = FALSE "
            + "ORDER BY vk.naechstepruefung, vk.vawsFachdaten";

        return new DatabaseAccess().createQuery(query)
            .setDate("today", new Date())
//            .setCacheable(true)
            .list();
    }

    /**
     * Liefert alle Kontroll-Einträge zu einem bestimmten VawsFachdatensatz.
     * @param fachdaten Der Fachdatensatz.
     * @return Eine Liste mit VawsKontrollen-Objekten.
     */
    public static List<?> getKontrollen(VawsFachdaten fachdaten) {
        List<?> kontrollen;

        if (fachdaten.getBehaelterId() == null) {
            kontrollen = new ArrayList<VawsKontrollen>();
        } else {
            kontrollen = new DatabaseAccess()
                .createQuery(
                    "FROM VawsKontrollen vk WHERE "
                        + "vk.vawsFachdaten = :fachdaten "
                        + "ORDER BY vk.pruefungabgeschlossen desc, "
                        + "vk.pruefdatum, vk.naechstepruefung")
                .setEntity("fachdaten", fachdaten)
                .list();

            log.debug(kontrollen.size() + " Kontrollen-Einträge für FD "
                + fachdaten + " gefunden!");
        }

        return kontrollen;
    }

    /**
     * Speichert einen VAWS-Kontrollen-Eintrag in der Datenbank.
     * @param kontrolle Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
     *         sonst <code>false</code>.
     */
    public static boolean saveKontrolle(VawsKontrollen kontrolle) {
        return new DatabaseAccess().saveOrUpdate(kontrolle);
    }

    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param kontrolle Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Datensatz nicht in der Datenbank existiert).
     */
    public static boolean removeKontrolle(VawsKontrollen kontrolle) {
        return new DatabaseAccess().delete(kontrolle);
    }
}
