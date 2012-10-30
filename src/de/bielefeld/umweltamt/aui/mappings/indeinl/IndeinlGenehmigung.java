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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'IndeinlGenehmigung' table. This class
 * may be customized as it is never re-generated after being created.
 */
public class IndeinlGenehmigung extends AbstractIndeinlGenehmigung implements
    Serializable {
    private static final long serialVersionUID = 7284884873813663429L;

    /**
     * Simple constructor of IndeinlGenehmigung instances.
     */
    public IndeinlGenehmigung() {
    }

    /**
     * Constructor of IndeinlGenehmigung instances given a simple primary key.
     * @param objektid
     */
    public IndeinlGenehmigung(java.lang.Integer objektid) {
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

    public static IndeinlGenehmigung getGenByObjekt(BasisObjekt objekt) {
        if (objekt.getBasisObjektarten().isGenehmigung()) {
            return (IndeinlGenehmigung) new DatabaseAccess()
                .createQuery(
                    "FROM IndeinlGenehmigung as gen WHERE "
                        + "gen.basisObjekt = :objekt")
                .setEntity("objekt", objekt)
                .uniqueResult();
        }
        return null;
    }

    /**
     * Speichert ein IndeinlGenehmigung Fachdaten-Objekt in der Datenbank.
     * @param fachdaten Das zu speichernde Fachdaten-Objekt.
     * @return <code>true</code>, wenn das Objekt gespeichert wurde, sonst
     *         <code>false</code>.
     */
    public static boolean saveFachdaten(IndeinlGenehmigung fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }

    /**
     * Liefert eine Liste mit allen IndeinlGenehmigung Objekten.
     * @return Eine Liste aus IndeinlGenehmigungen.
     */
    public static List<?> getAuswertungsListe(Boolean gen58, Boolean gen59) {
        String query = "FROM IndeinlGenehmigung as gen "
            // TODO: AND has a higher priority than OR. I do not think we wanted
            // what we had here before, but this should be checked!
//            + "WHERE gen.gen58 = :gen58 or gen.gen59 = :gen59 and gen.basisObjekt.inaktiv = 'false'"
            + "WHERE (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
    		+ "and gen.basisObjekt.inaktiv = 'false' "
            + "ORDER BY gen.basisObjekt.basisBetreiber.betrname";

        return new DatabaseAccess().createQuery(query)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();
    }

    /**
     * Liefert eine Liste mit allen Anhang 40 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getAnh40Liste(Boolean gen58, Boolean gen59) {
        return IndeinlGenehmigung.getAnhListe(40, gen58, gen59);
    }

    /**
     * Liefert eine Liste mit allen Anhang 49 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getAnh49Liste(Boolean gen58, Boolean gen59) {
        return IndeinlGenehmigung.getAnhListe(49, gen58, gen59);
    }

    /**
     * Liefert eine Liste mit allen Anhang 50 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getAnh50Liste(Boolean gen58, Boolean gen59) {
        return IndeinlGenehmigung.getAnhListe(50, gen58, gen59);
    }

    /**
     * Liefert eine Liste mit allen Anhang 53 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getAnh53Liste(Boolean gen58, Boolean gen59) {
        return IndeinlGenehmigung.getAnhListe(53, gen58, gen59);
    }

    /**
     * Little helper method for the four methods above
     */
    private static List<?> getAnhListe(int anhang, Boolean gen58, Boolean gen59) {
        String query = "FROM IndeinlGenehmigung as gen "
            + "WHERE gen.anhang = :anhang "
            + "and (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
            + "ORDER BY gen.basisObjekt.inaktiv, "
            + "gen.basisObjekt.basisBetreiber.betrname";

        return new DatabaseAccess().createQuery(query)
            .setInteger("anhang", anhang)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();
    }

    /**
     * Liefert eine Liste mit allen Anhang 40 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getBwkListe(Boolean gen58, Boolean gen59) {
        String query = "FROM IndeinlGenehmigung as gen "
            + "WHERE gen.anhang Is Null "
            + "and (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
            + "ORDER BY gen.basisObjekt.inaktiv, "
            + "gen.basisObjekt.basisStandort.strasse, "
            + "gen.basisObjekt.basisStandort.hausnr";

        return new DatabaseAccess().createQuery(query)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();
    }
}
