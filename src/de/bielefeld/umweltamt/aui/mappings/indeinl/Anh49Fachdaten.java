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
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ANH_49_FACHDATEN' table. This class may
 * be customized as it is never re-generated after being created.
 */
public class Anh49Fachdaten extends AbstractAnh49Fachdaten implements
    Serializable {
    private static final long serialVersionUID = 1996484061739446871L;

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

    // es werden alle Datensätze aus Anh49Fachdaten ausser Fettabscheidern
    // ausgewählt
    public static List<?> findAlle() {
        String query = "FROM Anh49Fachdaten anh49 "
            + "WHERE anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
            + "and anh49.basisObjekt.inaktiv = false "
            + "ORDER BY anh49.sachbearbeiterIn";
        return new DatabaseAccess().createQuery(query).list();
    }

    // es werden alle Datensätze aus Anh49Fachdaten ausser Fettabscheidern
    // ausgewählt
    public static List<?> findInaktive() {
        String query = "FROM Anh49Fachdaten anh49 "
            + "WHERE anh49.basisObjekt.basisObjektarten.objektart not like 'Fettabscheider' "
            + "and anh49.basisObjekt.inaktiv = true "
            + "ORDER BY anh49.sachbearbeiterIn";
        return new DatabaseAccess().createQuery(query).list();
    }

    /**
     * Sucht Anhang49-Fachdatensätze nach bestimmten Kriterien.
     * @param sachbearbeiter Welche(r) SachbearbeiterIn (oder "")?
     * @param abgemeldet param abgerissen
     * @param abwasserfrei
     * @param dekratuev
     * @param nurWiedervorlageAbgelaufen Sollen nur Datensätze angezeigt werden,
     *            deren Wiedervorlage in der Vergangenheit liegt?
     * @return Eine Liste mit den entstprechenden Anh49Fachdaten.
     */
    public static List<?> findAuswertung(Boolean abgemeldet, String abgerissen,
        Boolean abwasserfrei, boolean nurWiedervorlageAbgelaufen) {
        String abgr = abgerissen.toLowerCase().trim() + "%";

        String query = "FROM Anh49Fachdaten ah49 WHERE "
            + "ah49.abgemeldet = :abgemeldet and "
            + "lower(ah49.sonstigestechnik) like :abgr and "
            + "ah49.abwasserfrei = :abwasserfrei and "
            + "ah49.basisObjekt.basisObjektarten.objektart "
            + "not like 'Fettabscheider' ";

        if (nurWiedervorlageAbgelaufen) {
            query += "and ah49.wiedervorlage <= :today "
                + "and ah49.basisObjekt.inaktiv = false "
                + "ORDER BY ah49.sachbearbeiterIn, "
                + "ah49.basisObjekt.basisBetreiber.betrname ";
            return new DatabaseAccess().createQuery(query)
                .setBoolean("abgemeldet", abgemeldet)
                .setString("abgr", abgr)
                .setBoolean("abwasserfrei", abwasserfrei)
                .setDate("today", new Date())
                .list();
        } else {
            query += "and ah49.basisObjekt.inaktiv = false "
                + "ORDER BY ah49.sachbearbeiterIn, "
                + "ah49.basisObjekt.basisBetreiber.betrname";
            return new DatabaseAccess().createQuery(query)
                .setBoolean("abgemeldet", abgemeldet)
                .setString("abgr", abgr)
                .setBoolean("abwasserfrei", abwasserfrei)
                .list();
        }
    }

    public static Anh49Fachdaten getAnh49ByObjekt(BasisObjekt objekt) {
        if (!objekt.getBasisObjektarten().isAnh49()) {
            return null;
        }
        return (Anh49Fachdaten) new DatabaseAccess()
            .createQuery(
                "FROM Anh49Fachdaten as ah49 WHERE "
                    + "ah49.basisObjekt = :objekt")
            .setEntity("objekt", objekt)
            .uniqueResult();
    }

    public static boolean saveFachdaten(Anh49Fachdaten fachdaten) {
        return new DatabaseAccess().saveOrUpdate(fachdaten);
    }

    public static List<?> findTuev(Integer tuev) {
        String query = "FROM Anh49Fachdaten anh49 "
            + "WHERE anh49.dekraTuevTermin = :tuev "
            + "and anh49.basisObjekt.basisObjektarten.objektart "
            + "not like 'Fettabscheider' "
            + "and anh49.basisObjekt.inaktiv = false "
            + "ORDER BY anh49.sachbearbeiterIn";
        return new DatabaseAccess().createQuery(query)
            .setInteger("tuev", tuev)
            .list();
    }

    public static List<?> findSachbearbeiter(String sachbearb) {
        String query = "FROM Anh49Fachdaten anh49 "
            + "WHERE anh49.sachbearbeiterIn = :sachbearb "
            + "and anh49.basisObjekt.basisObjektarten.objektart "
            + "not like 'Fettabscheider' "
            + "and anh49.basisObjekt.inaktiv = false";
        return new DatabaseAccess().createQuery(query)
            .setString("sachbearb", sachbearb)
            .list();
    }

    public static String[] getSachbearbeiter() {
        return (String[]) new DatabaseAccess()
            .createQuery(
                "SELECT DISTINCT fd.sachbearbeiterIn "
                + "FROM Anh49Fachdaten fd "
                + "WHERE fd.basisObjekt.basisObjektarten.objektart "
                + "not like 'Fettabscheider'")
            .setCacheable(true)
            .setCacheRegion("sachbearbeiter")
            .array(new String[0]);
    }

    public Date getLetzteAnalyse(Anh49Fachdaten fd) {
        return (Date) new DatabaseAccess()
            .createQuery(
                "SELECT max(fd.anh49Analysen.datum) "
                    + "FROM Anh49Fachdaten fd "
                    + "WHERE fd = :fd")
            .setEntity("fd", fd)
            .uniqueResult();
    }
}
