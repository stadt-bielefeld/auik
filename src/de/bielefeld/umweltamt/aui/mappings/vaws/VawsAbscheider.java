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
 * A class that represents a row in the 'VAWS_Abscheider' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class VawsAbscheider extends AbstractVawsAbscheider implements
    Serializable {
    private static final long serialVersionUID = -8362004833711418556L;

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

    /**
     * Speichert einen VAWS-Abfüllflächen-Datensatz in der Datenbank.
     * @param flaeche Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
     *         sonst <code>false</code>.
     */
    public boolean merge()
        throws IllegalArgumentException {

        if (this.getVawsFachdaten() == null) {
            throw new IllegalArgumentException(
                "Die VawsAbscheider muss einem VawsFachdaten-Objekt zugeordnet sein!");
        }

        return new DatabaseAccess().saveOrUpdate(this);
    }
}
