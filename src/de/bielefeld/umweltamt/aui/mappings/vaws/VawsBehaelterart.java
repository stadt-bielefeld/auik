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
 * A class that represents a row in the 'VAWS_BEHAELTERART' table. This class
 * may be customized as it is never re-generated after being created.
 */
public class VawsBehaelterart extends AbstractVawsBehaelterart implements
    Serializable {
    private static final long serialVersionUID = 600389197074444085L;

    /**
     * Simple constructor of VawsBehaelterart instances.
     */
    public VawsBehaelterart() {
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
     * Get a string representation for the gui
     * @return String
     */
    public String toGuiString() {
        return super.getBehaelterart();
    }

    /* Add customized code below */

    /**
     * Liefer alle VAWS-Behälterarten.
     * @return Ein Array mit den Namen aller Behälterarten.
     */
    public static String[] getBehaelterarten() {
        return (String[]) new DatabaseAccess()
            .createQuery(
                "SELECT bha.behaelterart "
                + "FROM VawsBehaelterart bha "
                + "ORDER BY bha.id")
            .setCacheable(true)
            .setCacheRegion("vawsbhaliste")
            .array(new String[0]);
    }
}
