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

import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

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

    /* Add customized code below */

    /**
     * Liefert einen String, der dieses BasisObjekt beschreibt.
     * @return Einen String in der Form
     *         "[ID:Objekt-ID, Betr.:BasisBetreiber, Stdort:BasisStandort, Art:BasisObjektart]"
     */
    @Override
    public String toString() {
        return "[ID:" + this.getObjektid() + ", Betr.:"
            + this.getBasisBetreiber() + ", Stdort:" + this.getBasisStandort()
            + ", Art:" + this.getBasisObjektarten() + "]";
    }

    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Betreiber
     * zugeordnet sind.
     * @param betr Der Betreiber.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll,
     *            sonst <code>null</code>.
     * @return Eine Liste von BasisObjekten dieses Betreibers.
     */
    public static List<?> getObjekteByBetreiber(
            BasisBetreiber betr, String abteilung) {
        String query = "FROM BasisObjekt as bo "
            + "WHERE bo.basisBetreiber = :betreiber ";

        if (abteilung != null) {
            query += "and bo.basisObjektarten.abteilung = '" + abteilung + "' ";
        }

        query += "ORDER BY bo.inaktiv, bo.basisStandort.strasse, " +
        		"bo.basisStandort.hausnr, bo.basisObjektarten.objektart";

        return new DatabaseAccess().createQuery(query)
            .setEntity("betreiber", betr)
            .list();
    }

    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Standort zugeordnet
     * sind.
     * @param betr Der Standort.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll,
     *            sonst <code>null</code>.
     * @param nichtartid Die Objektart, die nicht dargestellt werden soll.
     * @return Eine Liste von BasisObjekten an diesem Standort.
     */
    public static List<?> getObjekteByStandort(
            BasisStandort standort, String abteilung, Integer nichtartid) {
        String query = "FROM BasisObjekt as bo "
            + "WHERE bo.basisStandort = :standort "; // +
//            "and bo.inaktiv = :f ";

        if (abteilung != null) {
            query += "and bo.basisObjektarten.abteilung = '" + abteilung + "' ";
        }

        if (nichtartid != null) {
            query += "and bo.basisObjektarten.objektartid != " + nichtartid;
        }

        query += "ORDER BY bo.inaktiv, bo.basisBetreiber.betrname, " +
        		"bo.basisObjektarten.objektart";

        return new DatabaseAccess().createQuery(query)
            .setEntity("standort", standort)
//            .setString("f", "f")
            .list();
    }

    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Standort zugeordnet
     * sind.
     * @param betr Der Standort.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll,
     *            sonst <code>null</code>.
     * @param nichtartid Die Objektart, die nicht dargestellt werden soll.
     * @return Eine Liste von BasisObjekten an diesem Standort.
     */
    public static List<?> getObjekteByStandort(
            BasisStandort standort, Integer istartid) {
        String query = "FROM BasisObjekt as bo "
            + "WHERE bo.basisStandort = :standort ";

        if (istartid != null) {
            query += "and bo.basisObjektarten.objektartid = :objektartid ";
        }

        query += "ORDER BY bo.inaktiv, bo.basisBetreiber.betrname, " +
        		"bo.basisObjektarten.objektart";

        return new DatabaseAccess().createQuery(query)
            .setEntity("standort", standort)
            .setInteger("objektartid", 32)
            .list();
    }

    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Standort zugeordnet
     * sind.
     * @param betr Der Standort.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll,
     *            sonst <code>null</code>.
     * @param nichtartid Die Objektart, die nicht dargestellt werden soll.
     * @return Eine Liste von BasisObjekten an diesem Standort.
     */
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
    public static BasisObjekt getObjekt(Integer id) {
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
    public static BasisObjekt saveBasisObjekt(BasisObjekt obj) {
        boolean success = false;
        BasisObjekt saved = null;

        success = new DatabaseAccess().saveOrUpdate(obj);
        if (success) {
            saved = obj; // TODO: Check if this is really nessessary
        }
        return saved;
    }

    /**
     * Speichert ein Objekt in der Datenbank.
     * @param obj Das zu speichernde Objekt.
     * @param prio Die zu speichernde Priorität
     * @return Das gespeicherte Objekt.
     */
    public static BasisObjekt saveBasisObjekt(BasisObjekt obj, Integer prio) {
        BasisPrioritaet.saveBasisPrioritaet(obj, prio);
        return BasisObjekt.saveBasisObjekt(obj);
    }

    /**
     * Löscht ein Objekt aus der Datenbank.
     * @param obj Das zu löschende Objekt.
     * @return <code>true</code>, wenn das Objekt gelöscht wurde, sonst
     *         <code>false</code>.
     */
    public static boolean removeBasisObjekt(BasisObjekt obj) {
        return new DatabaseAccess().delete(obj);
    }
}
