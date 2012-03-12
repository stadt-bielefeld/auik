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
import java.util.List;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'VAWS_VERWALTUNGSGEBUEHREN' table. This
 * class may be customized as it is never re-generated after being created.
 */
public class VawsVerwaltungsgebuehren extends AbstractVawsVerwaltungsgebuehren
    implements Serializable {
    private static final long serialVersionUID = 2246682815656123749L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of VawsVerwaltungsgebuehren instances.
     */
    public VawsVerwaltungsgebuehren() {
        setBetrag(new Float(0.0));
        setAbschnitt("360.12 LW");
    }

    /**
     * Constructor of VawsVerwaltungsgebuehren instances given a simple primary
     * key.
     * @param id
     */
    public VawsVerwaltungsgebuehren(java.lang.Integer id) {
        super(id);
    }

    /* Add customized code below */

    /**
     * Liefert alle Verwaltungsgebühren-Einträge zu einem bestimmten
     * VawsFachdatensatz.
     * @param fachdaten Der Fachdatensatz.
     * @return Eine Liste mit VawsVerwaltungsgebuehren-Objekten.
     */
    public static List<?> getVerwaltungsgebuehren(VawsFachdaten fachdaten) {
        List<?> gebuehren;

        if (fachdaten.getBehaelterId() == null) {
            gebuehren = new ArrayList<VawsVerwaltungsgebuehren>();
        } else {
            gebuehren = new DatabaseAccess()
                .createQuery(
                    "FROM VawsVerwaltungsgebuehren vgb WHERE "
                        + "vgb.vawsFachdaten = :fachdaten "
                        + "ORDER BY vgb.datum, vgb.abschnitt, vgb.betrag")
                .setEntity("fachdaten", fachdaten)
                .list();

            log.debug(gebuehren.size() + " Gebühren-Einträge für FD "
                + fachdaten + " gefunden!");
        }

        return gebuehren;
    }

    /**
     * Speichert einen VAWS-Verwaltungsgebühren-Eintrag in der Datenbank.
     * @param gebuehr Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
     *         sonst <code>false</code>.
     */
    public static boolean saveGebuehr(VawsVerwaltungsgebuehren gebuehr) {
        return new DatabaseAccess().saveOrUpdate(gebuehr);
    }

    // TODO: Typische DB-Operationen (Löschen...) kapseln, evtl. Vererbung
    // ^^^^ I agree! :)

    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param gebuehr Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Datensatz nicht in der Datenbank existiert).
     */
    public static boolean removeGebuehr(VawsVerwaltungsgebuehren gebuehr) {
        return new DatabaseAccess().delete(gebuehr);
    }
}
