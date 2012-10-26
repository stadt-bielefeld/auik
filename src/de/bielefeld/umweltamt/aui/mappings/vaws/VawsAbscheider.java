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

import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'VAWS_Abscheider' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class VawsAbscheider extends AbstractVawsAbscheider implements
    Serializable {
    private static final long serialVersionUID = -8362004833711418556L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of VawsAbscheider instances.
     */
    public VawsAbscheider() {
    }

    /**
     * Constructor of VawsAbscheider instances given a simple primary key.
     * @param id
     */
    public VawsAbscheider(java.lang.Integer id) {
        super(id);
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

    /* Add customized code below */

    // Statischer Teil:

    public static VawsAbscheider getAbscheider(VawsFachdaten fachdaten)
        throws IllegalArgumentException {
        VawsAbscheider abscheider;
        List<?> list;

        if (fachdaten == null
            || !fachdaten.getAnlagenart().equals("VAwS-Abscheider")) {
            throw new IllegalArgumentException(
                "Fachdaten-Objekt ist kein VAwS-Abscheider!");
        }

        if (fachdaten.getBehaelterId() == null) {
            list = new ArrayList<VawsAbscheider>();
        } else {
            list = new DatabaseAccess()
                .createQuery(
                    "FROM VawsAbscheider abff "
                        + "WHERE abff.vawsFachdaten = :fachdaten ")
                .setEntity("fachdaten", fachdaten)
                .list();
        }

        // TODO: Here we also could use uniqueResult instead of list, but I am
        // not to sure about the logic here...

        if (list.size() > 0) {
            abscheider = (VawsAbscheider) list.get(0);
            log.debug("Fläche '" + abscheider + "' geladen!");
        } else {
            // Bei so ziemlich 95% aller Tankstellen gibts ein VawsFachdaten-
            // Objekt, aber kein VawsAbscheidern-Objekt.
            // Seems like it's not a bug, it's a feature...

            // Also legen wir in diesen Füllen einfach ein neues
            // VawsAbscheidern-Objekt an.

            // Das selbe tun wir bei einem noch ungespeicherten
            // neuen VawsFachdaten-Objekt.

            abscheider = new VawsAbscheider();
            abscheider.setVawsFachdaten(fachdaten);
            log.debug("Neuer Abscheider für '" + fachdaten + "' erzeugt!");
        }

        return abscheider;
    }

    /**
     * Speichert einen VAWS-Abfüllflächen-Datensatz in der Datenbank.
     * @param flaeche Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
     *         sonst <code>false</code>.
     */
    public static boolean saveAbscheider(VawsAbscheider abscheider)
        throws IllegalArgumentException {

        if (abscheider.getVawsFachdaten() == null) {
            throw new IllegalArgumentException(
                "Die VawsAbscheider muss einem VawsFachdaten-Objekt zugeordnet sein!");
        }

        return new DatabaseAccess().saveOrUpdate(abscheider);
    }
}
