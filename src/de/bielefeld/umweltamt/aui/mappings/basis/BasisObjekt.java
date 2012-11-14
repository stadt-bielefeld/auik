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
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;

/**
 * A class that represents a row in the 'BASIS_OBJEKT' table. This class may be
 * customized as it is never re-generated after being created.
 */
public class BasisObjekt extends AbstractBasisObjekt implements Serializable {
    private static final long serialVersionUID = -5770125513608713721L;

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

    public static List<?> getObjekteMitPrioritaet() {
        String query = "SELECT distinct bo.basisStandort, bo.basisBetreiber, "
            + "bp.prioritaet, bo.basisSachbearbeiter "
            + "FROM BasisObjekt as bo, BasisPrioritaet as bp  "
            + "WHERE bo.basisStandort = bp.basisStandort and "
    		+ "bo.basisBetreiber = bp.basisBetreiber "
            + "ORDER BY bo.basisStandort.strasse";

        return new DatabaseAccess().createQuery(query).list();
    }

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
     * Gibt den Wert für die Priorität des Objektes zurück.
     * @return java.lang.Integer
     */
    public java.lang.Integer getPrioritaet() {
        Integer prioritaet = null;

        if (BasisPrioritaet.getPrioritaet(this) != null) {
            prioritaet = BasisPrioritaet.getPrioritaet(this).getPrioritaet();
        }

        return prioritaet;
    }

    /**
     * Speichert ein Objekt in der Datenbank.
     * @param obj Das zu speichernde Objekt.
     * @return Das gespeicherte Objekt.
     */
    public static BasisObjekt merge(BasisObjekt obj) {
        return (BasisObjekt) new DatabaseAccess().merge(obj);
    }

    /**
     * Speichert ein Objekt in der Datenbank.
     * @param obj Das zu speichernde Objekt.
     * @param prio Die zu speichernde Priorität
     * @return Das gespeicherte Objekt.
     */
    public static BasisObjekt saveBasisObjekt(BasisObjekt obj, Integer prio) {
        BasisPrioritaet.saveBasisPrioritaet(obj, prio);
        return BasisObjekt.merge(obj);
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
