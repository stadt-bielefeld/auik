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
import java.util.Date;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisSachbearbeiter;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_49_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh49Fachdaten extends AbstractAnh49Fachdaten implements
    Serializable {
    private static final long serialVersionUID = 1996484061739446871L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of Anh49Fachdaten instances.
     */
    public Anh49Fachdaten() {
    }

    @Override
    public String toString() {
        String tmp = "[Anh49:" + getBasisObjekt() + "]";
        return tmp;
    }

    public static Anh49Fachdaten getAnh49ByObjekt(BasisObjekt objekt) {
        if (!objekt.getBasisObjektarten().isAnh49()) {
            return null;
        }
        return (Anh49Fachdaten) new DatabaseAccess()
            .createQuery(
                "FROM Anh49Fachdaten as ah49 "
                + "WHERE ah49.basisObjekt = :objekt")
            .setEntity("objekt", objekt)
            .uniqueResult();
    }

    public static boolean saveFachdaten(Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }

    public static BasisSachbearbeiter[] getAllSachbearbeiter() {
        return (BasisSachbearbeiter[]) new DatabaseAccess()
            .createQuery(
                "SELECT DISTINCT fd.basisObjekt.basisSachbearbeiter "
                + "FROM Anh49Fachdaten AS fd "
                + "WHERE fd.basisObjekt.basisObjektarten.objektart "
                    + "NOT LIKE 'Fettabscheider'")
            .setCacheable(true)
            .setCacheRegion("sachbearbeiter")
            .array(new BasisSachbearbeiter[0]);
    }

    public static Integer[] getAllDekraTuevYears() {
        return (Integer[]) new DatabaseAccess()
            .createQuery(
                "SELECT DISTINCT year(fd.dekraTuevDatum) "
                + "FROM Anh49Fachdaten AS fd "
                + "WHERE fd.basisObjekt.basisObjektarten.objektart "
                    + "NOT LIKE 'Fettabscheider'"
                // TODO: This is rather tricky and should not be here at all...
                + "AND fd._deleted = false")
            .array(new Integer[0]);
    }

    public Date getLetzteAnalyse() {
        return (Date) new DatabaseAccess()
            .createQuery(
                "SELECT max(fd.anh49Analysen.datum) "
                    + "FROM Anh49Fachdaten fd "
                    + "WHERE fd = :fd")
            .setEntity("fd", this)
            .uniqueResult();
    }

    public Date getNaechsteKontrolle() {
        return (Date) new DatabaseAccess()
            .createQuery(
                "SELECT max(kontrolle.naechstepruefung) "
                    + "FROM Anh49Kontrollen kontrolle "
                    + "WHERE kontrolle.anh49Fachdaten = :fd")
            .setEntity("fd", this)
            .uniqueResult();
    }

    /**
     * Anhang 49 Fachdaten nach Kriterien auswählen
     * TODO: Eine Variante einbauen, die für die ganzen Booleans auch "egal"
     * erlaubt.
     * @param abgelWdrVorlage Liegt das Wiedervorlagedatum hinter dem aktuellen
     * @param sachbearbeiter
     * @param tuev
     * @param aktiv
     * @return List<?>
     */
    public static List<?> getAuswahlList (
        Boolean abgelWdrVorlage, BasisSachbearbeiter sachbearbeiter,
        Integer tuev, Boolean aktiv) {

        String query = "FROM Anh49Fachdaten AS anh49 "
            + "WHERE "
            + "anh49.basisObjekt.basisSachbearbeiter = :sachbearbeiter "
            + "AND anh49.basisObjekt.inaktiv = :inaktiv ";

        if (abgelWdrVorlage) {
            query += "AND anh49.wiedervorlage <= :today ";
        }

        if (tuev == null) {
            // This place is intentionally left blank.
//            query += "AND anh49.dekraTuevDatum IS NULL ";
        } else {
//            query += "AND date_part('year', anh49.dekraTuevDatum) = :tuev ";
            query += "AND year(anh49.dekraTuevDatum) = :tuev ";
        }

        /* For a strange reason we do not want the Fettabscheider... */
        query += "AND anh49.basisObjekt.basisObjektarten.objektart "
            + "NOT LIKE 'Fettabscheider' "
            + "ORDER BY anh49.basisObjekt.basisSachbearbeiter";

        if (tuev == null) {
            query += ", anh49.dekraTuevDatum";
        }

        log.debug(query);

        DatabaseAccess da = new DatabaseAccess()
            .createQuery(query)
            .setEntity("sachbearbeiter", sachbearbeiter)
//                ((sachbearbeiter != null) ? sachbearbeiter : "%"))
            .setBoolean("inaktiv", !aktiv);

        if (abgelWdrVorlage) {
            da.setDate("today", new Date());
        }
        if (tuev != null) {
            da.setInteger("tuev", tuev);
        }

        return da.list();
    }
}
