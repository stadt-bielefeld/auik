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

package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the 'BASIS_OBJEKT' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class BasisObjekt extends AbstractBasisObjekt implements Serializable {
    private static final long serialVersionUID = -5770125513608713721L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of BasisObjekt instances.
     */
    public BasisObjekt() {
    }

    /**
     * Constructor of BasisObjekt instances given a simple primary key.
     * @param objektid
     */
    public BasisObjekt(java.lang.Integer objektid) {
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

    /**
     * Lädt ein Objekt aus der Datenbank.
     * @param id Der Primärschlüssel des zu ladenden Objekts.
     * @return Das BasisObjekt mit dem Primärschlüssel oder <code>null</code>,
     *         falls ein solches nicht gefunden wurde.
     */
    public static BasisObjekt findById(Integer id) {
        BasisObjekt objekt = null;
        objekt = (BasisObjekt) new DatabaseAccess().get(BasisObjekt.class, id);
        return objekt;
    }

    /**
     * Merge (save or update) a detached instance
     * @param detachedInstance the instance to merge
     * @return <code>BasisObjekt</code> the merged instance,
     *         if everything went okay,
     *         <code>null</code> otherwise
     */
    public static BasisObjekt merge(BasisObjekt detachedInstance) {
        log.debug("Merging BasisObjekt instance " + detachedInstance);
        return (BasisObjekt) new DatabaseAccess().merge(detachedInstance);
    }

    /**
     * Merge (save or update) this instance
     * @return <code>true</code>, if everything went okay,
     *         <code>false</code> otherwise
     */
    public boolean merge() {
        return BasisObjekt.merge(this).equals(this);
    }

    /**
     * Löscht ein Objekt aus der Datenbank.
     * @param obj Das zu löschende Objekt.
     * @return <code>true</code>, wenn das Objekt gelöscht wurde, sonst
     *         <code>false</code>.
     */
    public static boolean delete(BasisObjekt obj) {
        return new DatabaseAccess().delete(obj);
    }
}
