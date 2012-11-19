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

/*
 * Created Thu Jan 13 16:52:31 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Eine Klasse, die eine Zeile der 'BASIS_STANDORT'-Tabelle repräsentiert.
 */
public class BasisStandort extends AbstractBasisStandort implements
    Serializable {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    private static final long serialVersionUID = 2774552431508434460L;

    /**
     * Simple constructor of BasisStandort instances.
     */
    public BasisStandort() {
        // This is intentionally left blank.
    }

    /**
     * Constructor of BasisStandort instances given a simple primary key.
     * @param standortid
     */
    public BasisStandort(java.lang.Integer standortid) {
        super(standortid);
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
     * Liefert einen Standort mit einer bestimmten ID.
     * @param id Die ID (der Primärschlüssel) des Standorts.
     * @return Den gesuchten Standort oder <code>null</code>, falls kein
     *         Standort mit dieser ID existiert.
     */
    public static BasisStandort findById(Integer id) {
        BasisStandort standort = null;
        standort = (BasisStandort) new DatabaseAccess()
            .get(BasisStandort.class, id);
        return standort;
    }

    /**
     * Speichert einen Standort in die Datenbank, bzw. updatet einen schon
     * vorhandenen Standort mit neuen Werten.
     * @param bsta Der Standort, der gespeichert werden soll.
     * @return Der gespeicherte Standort, oder <code>null</code>, falls beim
     *         Speichern ein Fehler auftrat.
     */
    public static BasisStandort merge(BasisStandort bsta) {
        BasisStandort bstaRet = null;
        bstaRet = (BasisStandort) new DatabaseAccess().merge(bsta);
        if (bstaRet != null) {
            log.debug("Neuer Standort " + bstaRet + " gespeichert!");
        }
        return bstaRet;
    }

    /**
     * Löscht einen vorhandenen Standort aus der Datenbank.
     * @param standort Der Standort, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Betreiber gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. der
     *         Standort nicht in der Datenbank existiert).
     */
    public static boolean delete(BasisStandort standort) {
        boolean removed = false;
        removed = new DatabaseAccess().delete(standort);
        return removed;
    }
}
