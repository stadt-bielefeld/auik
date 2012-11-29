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
import java.util.Collection;
import java.util.Vector;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;

/**
 * A class that represents a row in the 'ATL_PROBENAHMEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class AtlProbenahmen extends AbstractAtlProbenahmen implements
    Serializable {
    private static final long serialVersionUID = 950596109574293371L;

    /**
     * Simple constructor of AtlProbenahmen instances.
     */
    public AtlProbenahmen() {
        // This is intentionally left blank.
    }

    /**
     * Constructor of AtlProbenahmen instances given a simple primary key.
     * @param kennummer
     */
    public AtlProbenahmen(java.lang.Integer id) {
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

    /**
     * Liefert eine bestimmte Probenahme.
     * @param id Die ID der Probenahme
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
     *         nicht existiert
     */
    public static AtlProbenahmen findById(Integer id) {
        return getProbenahme(id, false);
    }

    /**
     * Liefert eine bestimmte Probenahme.
     * @param id Die ID der Probenahme
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese
     *         nicht existiert
     */
    public static AtlProbenahmen getProbenahme(Integer id, boolean loadPos) {
        if (loadPos) {
            return (AtlProbenahmen) new DatabaseAccess()
                .getAndInitCollections(AtlProbenahmen.class, id);
        } else {
            return (AtlProbenahmen) new DatabaseAccess()
                .get(AtlProbenahmen.class, id);
        }
    }

    public boolean merge() {
        return (new DatabaseAccess().merge(this) != null);
    }

    public boolean delete() {
        return new DatabaseAccess().delete(this);
    }

    /**
     * Return all Collections which need to be initialized.
     * @return An array of all Collections
     */
    @Override
    public Vector<Collection<?>> getToInitCollections() {
        Vector<Collection<?>> collections = new Vector<Collection<?>>();
        collections.add(getAtlAnalysepositionen());
        return collections;
    }

    @Override
    public int hashCode() {
        int result = 17;
        int kennummerValue = this.getKennummer() == null ? 0 : this
                .getKennummer().hashCode();
        return result * 37 + kennummerValue;
    }
}
