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

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;

/**
 * A class that represents a row in the 'VAWS_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class VawsFachdaten extends AbstractVawsFachdaten implements
    Serializable {
    private static final long serialVersionUID = -1602704238970146965L;

    /**
     * Simple constructor of VawsFachdaten instances.
     */
    public VawsFachdaten() {
    }

    /**
     * Constructor of VawsFachdaten instances given a simple primary key.
     * @param behaelterId
     */
    public VawsFachdaten(Integer behaelterId) {
        super(behaelterId);
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

    /**
     * Liefert alle VAWS-Fachdatensätze zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit VawsFachdaten.
     */
    public static VawsFachdaten findById(Integer id) {
        VawsFachdaten fachdaten = null;
        fachdaten = (VawsFachdaten) new DatabaseAccess()
            .get(VawsFachdaten.class, id);
        return fachdaten;
    }

    /**
     * Speichert einen VAWS-Fachdatensatz in der Datenbank.
     * @param fachdaten Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
     *         sonst <code>false</code>.
     */
    public static boolean merge(VawsFachdaten fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }

    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param fachdaten Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Datensatz nicht in der Datenbank existiert).
     */
    public static boolean delete(VawsFachdaten fachdaten) {
        return new DatabaseAccess().delete(fachdaten);
    }
}
