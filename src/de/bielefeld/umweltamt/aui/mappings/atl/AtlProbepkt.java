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

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the 'ATL_PROBEPKT' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class AtlProbepkt extends AbstractAtlProbepkt implements Serializable {
    private static final long serialVersionUID = -3901033252297978991L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of AtlProbepkt instances.
     */
    public AtlProbepkt() {
    }

    /**
     * Constructor of AtlProbepkt instances given a simple primary key.
     * @param objektid
     */
    public AtlProbepkt(java.lang.Integer objektid) {
        super(objektid);
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

    public static AtlProbepkt findById(Integer id) {
        return (AtlProbepkt) new DatabaseAccess().get(AtlProbepkt.class, id);
    }

    public boolean merge() {
        boolean saved = false;
        saved = new DatabaseAccess().saveOrUpdate(this);
        if (saved) {
            log.debug("Probepunkt " + this + " gespeichert.");
        } else {
            log.debug("Probepunkt " + this
                    + " konnte nicht gespeichert werden!");
        }
        return saved;
    }

    public boolean delete() {
        boolean removed = false;
        removed = new DatabaseAccess().delete(this);
        if (removed) {
            log.debug("Probepunkt " + this + " gelöscht.");
        } else {
            log.debug("Probepunkt " + this + " konnte nicht gelöscht werden!");
        }
        return removed;
    }
}
