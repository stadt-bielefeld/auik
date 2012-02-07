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

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

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
     * Liefert einen String der Form "[Genehmigung Verfahren:ID]"
     */
    @Override
    public String toString() {
        return "[Genehmigung Verfahren:" + getObjektid() + "]";
    }

    public static IndeinlGenehmigung getGenByObjekt(BasisObjekt objekt) {
        IndeinlGenehmigung fachdaten = null;
        if (objekt.getBasisObjektarten().isGenehmigung()) {
            List<?> gen = new DatabaseAccess()
                .createQuery(
                    "from IndeinlGenehmigung as gen where "
                        + "gen.basisObjekt = :objekt")
                .setEntity("objekt", objekt)
                .list();

            if (gen.size() > 0) {
                fachdaten = (IndeinlGenehmigung) gen.get(0);
            }
        }
        return fachdaten;
    }

    /**
     * Speichert ein IndeinlGenehmigung Fachdaten-Objekt in der Datenbank.
     * @param fachdaten Das zu speichernde Fachdaten-Objekt.
     * @return <code>true</code>, wenn das Objekt gespeichert wurde, sonst
     *         <code>false</code>.
     */
    public static boolean saveFachdaten(IndeinlGenehmigung fachdaten) {
        boolean saved = false;
        saved = new DatabaseAccess().saveOrUpdate(fachdaten);
        return saved;
    }

    /**
     * Liefert eine Liste mit allen IndeinlGenehmigung Objekten.
     * @return Eine Liste aus IndeinlGenehmigungen.
     */
    public static List<?> getAuswertungsListe(Boolean gen58, Boolean gen59) {
        List<?> liste = null;

        String query = "from IndeinlGenehmigung as gen "
            // TODO: AND has a higher priority than OR. I do not think we wanted
            // what we had here before, but this should be checked!
//            + "where gen.gen58 = :gen58 or gen.gen59 = :gen59 and gen.basisObjekt.inaktiv = 'false'"
            + "where (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
    		+ "and gen.basisObjekt.inaktiv = 'false' "
            + "order by gen.basisObjekt.basisBetreiber.betrname";

        liste = new DatabaseAccess().createQuery(query)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 40 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getAnh40Liste(Boolean gen58, Boolean gen59) {
        List<?> liste = null;

        String query = "from IndeinlGenehmigung as gen "
            + "where gen.anhang = 40 "
            + "and (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
            + "order by gen.basisObjekt.inaktiv, "
            + "gen.basisObjekt.basisBetreiber.betrname";

        liste = new DatabaseAccess().createQuery(query)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 49 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getAnh49Liste(Boolean gen58, Boolean gen59) {
        List<?> liste = null;

        String query = "from IndeinlGenehmigung as gen "
            + "where gen.anhang = 49 "
            + "and (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
            + "order by gen.basisObjekt.inaktiv, "
            + "gen.basisObjekt.basisBetreiber.betrname";

        liste = new DatabaseAccess().createQuery(query)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 50 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getAnh50Liste(Boolean gen58, Boolean gen59) {
        List<?> liste = null;

        String query = "from IndeinlGenehmigung as gen "
            + "where gen.anhang = 50 "
            + "and (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
            + "order by gen.basisObjekt.inaktiv, "
            + "gen.basisObjekt.basisBetreiber.betrname";

        liste = new DatabaseAccess().createQuery(query)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 53 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getAnh53Liste(Boolean gen58, Boolean gen59) {
        List<?> liste = null;

        String query = "from IndeinlGenehmigung as gen "
            + "where gen.anhang = 53 "
            + "and (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
            + "order by gen.basisObjekt.inaktiv, "
            + "gen.basisObjekt.basisBetreiber.betrname";

        liste = new DatabaseAccess().createQuery(query)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 40 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List<?> getBwkListe(Boolean gen58, Boolean gen59) {
        List<?> liste;

        String query = "from IndeinlGenehmigung as gen "
            + "where gen.anhang Is Null "
            + "and (gen.gen58 = :gen58 or gen.gen59 = :gen59) "
            + "order by gen.basisObjekt.inaktiv, "
            + "gen.basisObjekt.basisStandort.strasse, "
            + "gen.basisObjekt.basisStandort.hausnr";

        liste = new DatabaseAccess().createQuery(query)
            .setBoolean("gen58", gen58)
            .setBoolean("gen59", gen59)
            .list();

        return liste;
    }
}
