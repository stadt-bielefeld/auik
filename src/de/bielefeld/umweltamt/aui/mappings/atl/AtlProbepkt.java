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
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
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

    /**
     * Liefert den ersten Probepunkt einer bestimmten Art und Kläranlage.
     * @return Den Probepunkt oder <code>null</code>, falls kein Probepunkt
     *         dieser Art mit dieser Kläranlage existiert.
     */
    // TODO: This does not seem to be what we really want here???
    public static AtlProbepkt getKlaerschlammProbepunkt(
            AtlProbeart art, AtlKlaeranlagen ka) {
        return (AtlProbepkt) new DatabaseAccess()
            .createQuery(
                "FROM AtlProbepkt as probepkt WHERE "
                    + "probepkt.atlProbeart = :art "
                    + "and probepkt.atlKlaeranlagen = :ka "
                    + "ORDER BY probepkt.objektid asc")
                .setEntity("art", art)
                .setEntity("ka", ka)
                .setMaxResults(1)
                .uniqueResult();
    }

    public static AtlProbepkt findById(Integer id) {
        return (AtlProbepkt) new DatabaseAccess().get(AtlProbepkt.class, id);
    }

    public static AtlProbepkt getSielhautProbepunkt(AtlSielhaut siel) {
        AtlProbepkt punkt = null;
        punkt = (AtlProbepkt) new DatabaseAccess()
            .createQuery(
                "FROM AtlProbepkt as probepkt WHERE "
                    + "probepkt.atlSielhaut = :siel")
                .setEntity("siel", siel)
                .uniqueResult();
        log.debug("SielhautBearbeiten-Probepunkt " + punkt
                + " aus DB geholt.");
        return punkt;
    }

    public static List<?> getProbenehmerpunkte() {
        return new DatabaseAccess().createQuery(
            "SELECT distinct pk FROM AtlProbepkt as pk "
                + "inner join pk.atlProbenahmen as pn "
                + "WHERE pn.kennummer like '3%' "
                + "and pk.basisObjekt.inaktiv = false ")
            .list();
    }

    public static boolean saveProbepunkt(AtlProbepkt punkt) {
        boolean saved = false;
        saved = new DatabaseAccess().saveOrUpdate(punkt);
        if (saved) {
            log.debug("Probepunkt " + punkt + " gespeichert.");
        } else {
            log.debug("Probepunkt " + punkt
                    + " konnte nicht gespeichert werden!");
        }
        return saved;
    }

    public static boolean removeProbepunkt(AtlProbepkt punkt) {
        boolean removed = false;
        removed = new DatabaseAccess().delete(punkt);
        if (removed) {
            log.debug("Probepunkt " + punkt + " gelöscht.");
        } else {
            log.debug("Probepunkt " + punkt + " konnte nicht gelöscht werden!");
        }
        return removed;
    }

    public BasisBetreiber getBasisBetreiber() {
        BasisObjekt basisObj = getBasisObjekt();

        return basisObj != null ? basisObj.getBasisBetreiber() : null;
    }

    public static List<?> getESatzung() {
        return new DatabaseAccess().createQuery(
            "FROM AtlProbepkt as pk WHERE " + "pk.atlProbeart.id = 3 "
                + "and pk.basisObjekt.inaktiv = false "
                + "ORDER BY pk.basisObjekt.basisStandort")
            .list();
    }

    public static List<?> getUWB() {
        return new DatabaseAccess().createQuery(
            "FROM AtlProbepkt as pk WHERE " + "pk.atlProbeart.id = 2 "
                + "and pk.basisObjekt.inaktiv = false "
                + "ORDER BY pk.basisObjekt.basisStandort")
            .list();
    }

    public static List<?> getInaktiv() {
        return new DatabaseAccess().createQuery(
            "FROM AtlProbepkt as pk WHERE "
                + "pk.basisObjekt.inaktiv = true "
                + "ORDER BY pk.basisObjekt.basisStandort")
            .list();
    }
}
