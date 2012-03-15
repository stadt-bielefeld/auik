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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_ANALYSEPOSITION' table. This class
 * may be customized as it is never re-generated after being created.
 */
public class AtlAnalyseposition extends AbstractAtlAnalyseposition implements
        Serializable {
    private static final long serialVersionUID = 8611630449814009888L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Simple constructor of AtlAnalyseposition instances. */
    public AtlAnalyseposition() {
        this(new Float(0.0));
    }

    /**
     * Constructor of AtlAnalyseposition instances given a value.
     * @param wert
     */
    public AtlAnalyseposition(Float wert) {
        setWert(wert);
    }

    /**
     * Constructor of AtlAnalyseposition instances given a AtlProbenahme.
     * @param probe
     */
    public AtlAnalyseposition(AtlProbenahmen probe) {
        this(new Float(0.0));
        setAtlProbenahmen(probe);
    }

    /**
     * Constructor of AtlAnalyseposition instances given a simple primary key.
     * @param id
     */
    public AtlAnalyseposition(java.lang.Integer id) {
        super(id);
        setWert(new Float(0.0));
    }

    /* Add customized code below */

    /**
     * Vergleich auf Basis der Primary-Keys, wenn diese vorhanden sind, sonst
     * Vergleich von Wert, Parameter, Einheit und "Analyse von".
     * @param rhs
     * @return boolean
     */
    @Override
    public boolean equals(Object rhs) {
        // Wenn das andere Objekt null ist, sind wir nicht gleich
        if (rhs == null) {
            return false;
        }
        // Wenn das andere Objekt auf uns verweist, sind wir natürlich gleich
        if (this == rhs) {
            return true;
        }
        // Wenn das andere Objekt keine AtlAnalyseposition ist, können wir nicht
        // gleich sein
        if (!(rhs instanceof AtlAnalyseposition)) {
            return false;
        }

        // Das andere Objekt ist also eine AtlAnalyseposition.
        AtlAnalyseposition that = (AtlAnalyseposition) rhs;

        // Wenn beide einen Primary-Key haben...
        if (this.getId() != null && that.getId() != null) {
            // ... und diese nicht gleich sind ...
            if (!this.getId().equals(that.getId())) {
                // ... sind die Objekte auch nicht gleich.
                return false;
            } else {
                // ... wenn sie gleich sind, sind auch beide Objekte gleich.
                return true;
            }
        } else if (this.getId() != null || that.getId() != null) {
            // Wenn NUR einer von beiden einen Key hat,
            // können beide auch nicht gleich sein.
            return false;
        }

        // Da mindestens eins der zu vergleichenden Objekte keinen Primary-Key
        // hat müssen wir an Hand von anderen Kriterien entscheiden, ob beide
        // Objekte gleich sind.

        // Vergleich von Wert
        if (this.getWert() != null) {
            if (!this.getWert().equals(that.getWert())) {
                return false;
            }
        } else {
            if (that.getWert() != null) {
                return false;
            }
        }

        // Vergleich von AtlParameter
        if (this.getAtlParameter() != null) {
            if (!this.getAtlParameter().equals(that.getAtlParameter())) {
                return false;
            }
        } else {
            if (that.getAtlParameter() != null) {
                return false;
            }
        }

        // Vergleich von AtlEinheit
        if (this.getAtlEinheiten() != null) {
            if (!this.getAtlEinheiten().equals(that.getAtlEinheiten())) {
                return false;
            }
        } else {
            if (that.getAtlEinheiten() != null) {
                return false;
            }
        }

        // Vergleich von AnalyseVon
        if (this.getAnalyseVon() != null) {
            if (!this.getAnalyseVon().equals(that.getAnalyseVon())) {
                return false;
            }
        } else {
            if (that.getAnalyseVon() != null) {
                return false;
            }
        }

        // Wenn uns bis hier keine "Ungleichheit" aufgefallen ist, sind wir
        // wohl gleich.
        return true;
    }

    /**
     * Hashcode auf Basis des Primary-Keys, wenn dieser existiert, sonst auf
     * Basis von von Wert, Parameter, Einheit und "Analyse von".
     * @return int
     */
    @Override
    public int hashCode() {
        if (this.hashValue == 0) {
            int result = 17;
            int idValue = 0;
            if (getId() != null) {
                idValue = getId().hashCode();
            } else if (getWert() != null) {
                idValue = this.getWert().hashCode();
                if (getAtlParameter() != null) {
                    result += getAtlParameter().hashCode();
                }
                if (getAtlEinheiten() != null) {
                    result += getAtlEinheiten().hashCode();
                }
                if (getAnalyseVon() != null) {
                    result += getAnalyseVon().hashCode();
                }
            }
            result = 37 * result + idValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }

    /**
     * @return Einen String der Form
     *         "[Position: Parameter: Wert Einheit, Analyse_Von, [Probenahme], ID:Id]"
     */
    @Override
    public String toString() {
        String tmp = "[Position: " + getAtlParameter() + ": " + getWert() + " "
                + getAtlEinheiten() + ", " + getAnalyseVon() + ", ";
        if (getAtlProbenahmen() != null) {
            tmp += getAtlProbenahmen() + ", ";
        }
        if (getId() != null) {
            tmp += "ID:" + getId();
        } else {
            tmp += "UNSAVED";
        }
        tmp += "]";
        return tmp;
    }

    // Liefert einObjekt vom Typ Analysepositionen mit einer gegebenen ID
    public static AtlAnalyseposition getAnalysepositionObjekt(Integer id) {
        AtlAnalyseposition objekt = null;

        objekt = (AtlAnalyseposition)
                new DatabaseAccess().get(AtlAnalyseposition.class, id);

        return objekt;
    }

    /**
     * Liefert eine Liste der Analysepositionen mit einem gegebenen Parameter
     * aus einer gegebenen Probenahme.
     * @param probe Die Probenahme.
     * @param param Der Parameter.
     * @return Eine Liste mit <code>AtlAnalyseposition</code>en.
     */
    public static List<?> getAnalysepositionen(
            AtlProbenahmen probe, AtlParameter param) {
        // TODO: Evtl. mit komplizierterem HQL gleich beim Laden der Proben
        // lösen?
        // SELECT probe.name, probe.pos....
//        String query =
//            "FROM AtlAnalyseposition pos " +
//            "WHERE pos.atlParameter = ? " +
//            "and pos.atlProbenahmen = ? " +
//            "ORDER BY pos.atlProbenahmen.datumDerEntnahme";

        // Nothing was done here...
//        if (!Hibernate.isInitialized(probe.getAtlAnalysepositionen())) {
//        }

        List<AtlAnalyseposition> proben = new ArrayList<AtlAnalyseposition>();

        Iterator<AtlAnalyseposition> it =
            probe.loadAtlAnalysepositionen().iterator();
        for (; it.hasNext();) {
            AtlAnalyseposition pos = it.next();
            if ((pos.getAtlParameter().getOrdnungsbegriff().equals(param
                    .getOrdnungsbegriff()))) {
                proben.add(pos);
            }
        }

        return proben;

        // TODO: Maybe better use something like this?
//        List<?> result = null;
//        result = new DatabaseAccess()
//            .createQuery(
//                "FROM AtlAnalyseposition "
//                    + "WHERE atlProbenahmen = :probe "
//                    + "AND atlParameter.ordnungsbegriff = :param")
//            .setEntity("probe", probe)
//            .setString("param", param.getOrdnungsbegriff())
//            .list();
//
//        return result;
    }

    /**
     * Liefert eine Liste der Analysepositionen mit einem gegebenen Parameter,
     * einer bestimmten Einheit, an einem bestimmten Probepunkt, die zwischen
     * <code>beginDate</code> und <code>endDate</code> (inklusive) genommen
     * wurden. Wenn <code>analyseVon</code> nicht <code>null</code> oder "" ist,
     * werden nur Analysepositionen geliefert, die von einer bestimmten Stelle
     * analysiert wurden.
     * @param param Der Parameter
     * @param einh Die Einheit
     * @param pkt Der Probepunkt
     * @param beginDate Das Anfangs-Datum
     * @param endDate Das End-Datum
     * @param analyseVon Wo wurde analysiert?
     * @return Eine Liste mit <code>AtlAnalyseposition</code>en
     */
    public static List<?> getAnalysepositionen(
            AtlParameter param, AtlEinheiten einh, AtlProbepkt pkt,
            Date beginDate, Date endDate, String analyseVon) {
        log.debug("Suche (HQL): p:" + param + ", e:" + einh + ", pkt:" + pkt
                + ", bD:" + beginDate + ", eD:" + endDate + ", aV:'"
                + analyseVon + "'");

//        String paramID = param.getOrdnungsbegriff();
//        Integer einhID = einh.getId();
//        Integer pktID = pkt.getObjektid();

        String query = "FROM AtlAnalyseposition pos "
                + "WHERE pos.atlParameter = :param "
                + "and pos.atlEinheiten = :einh "
                + "and pos.atlProbenahmen.atlProbepkt = :pkt "
                + "and pos.atlProbenahmen.datumDerEntnahme >= :beginDate "
                + "and pos.atlProbenahmen.datumDerEntnahme <= :endDate ";

        if (analyseVon != null && !analyseVon.equals("")) {
            query += "and pos.analyseVon like :analyseVon ";
        }

        query += "ORDER BY pos.atlProbenahmen.datumDerEntnahme";

        List<?> proben = null;

        if (analyseVon != null && !analyseVon.equals("")) {
            proben = new DatabaseAccess().createQuery(query)
                    .setEntity("param", param)
                    .setEntity("einh", einh)
                    .setEntity("pkt", pkt)
                    .setDate("beginDate", beginDate)
                    .setDate("endDate", endDate)
                    .setString("analyseVon", analyseVon)
                    .list();
        } else {
            proben = new DatabaseAccess().createQuery(query)
                    .setEntity("param", param)
                    .setEntity("einh", einh)
                    .setEntity("pkt", pkt)
                    .setDate("beginDate", beginDate)
                    .setDate("endDate", endDate)
                    .list();
        }

        return proben;
    }

    // Liefert eine Liste der Analysepositionen mit einem gegebenen
    // Parameter, an einem bestimmten Probepunkt.
    public static List<?> getAnalysepos(AtlParameter param, Integer pkt) {
        log.debug("Suche (HQL): p:" + param + " pkt:" + pkt);

        String query = "FROM AtlAnalyseposition as pos "
                + "WHERE pos.atlParameter = :param ";

        if (pkt != null) {
            query += "and pos.atlProbenahmen.atlProbepkt.objektid = '" + pkt
                    + "' ";
        } else {
            log.debug("objektid = null");
        }

        query += "ORDER BY pos.atlProbenahmen.datumDerEntnahme";

        return new DatabaseAccess().createQuery(query)
            .setEntity("param", param)
            .list();
    }

    // Liefert eine Liste der Parameter, die immer bestimmt werden.
    public static List<?> getVorOrtParameter() {
        String query = "FROM AtlAnalyseposition as pos "
                + "WHERE pos.atlParameter.ordnungsbegriff = 'L10821' ";
        return new DatabaseAccess().createQuery(query).list();
    }

    public static List<?> getSielhautpos(
            String param, Integer pkt, Date anfang, Date ende) {
        log.debug("Suche (HQL): p:" + param + ", pkt:" + pkt + ", bD:" + anfang
                + ", eD:" + ende + ", aV:'");
        String query = "FROM AtlAnalyseposition pos "
                + "WHERE pos.atlProbenahmen.datumDerEntnahme >= :anfang "
                + "and pos.atlProbenahmen.datumDerEntnahme <= :ende ";

        if (pkt != null) {
            query += "and pos.atlProbenahmen.atlProbepkt.objektid = '" + pkt
                    + "' ";
        } else {
            log.debug("objektid = null");
        }

        if (param != null) {
            query += "and pos.atlParameter.bezeichnung = '" + param + "' ";
        } else {
            log.debug("param = null");
        }

        query += "ORDER BY pos.atlProbenahmen.datumDerEntnahme";

        return new DatabaseAccess().createQuery(query)
            .setDate("anfang", anfang)
            .setDate("ende", ende)
            .list();
    }

    // Liefert eine Liste der Analyseinstitute.
    public static String[] getAnalysierer() {
        return (String[]) new DatabaseAccess()
            .createQuery(
                "SELECT distinct analyseVon "
                    + "FROM AtlAnalyseposition as ap "
                    + "ORDER BY ap.analyseVon")
            .array(new String[0]);
    }

    public static Set<AtlAnalyseposition> getAnalysepositionen(
        AtlProbenahmen probe) {
        Set<AtlAnalyseposition> resultSet = new HashSet<AtlAnalyseposition>();
        List<?> result = null;
        result = new DatabaseAccess()
            .createQuery(
                "FROM AtlAnalyseposition "
                    + "WHERE atlProbenahmen = :probe")
            .setEntity("probe", probe)
            .list();
        AtlAnalyseposition position = null;
        for (Object object : result) {
            position = (AtlAnalyseposition) object;
            resultSet.add(position);
        }
        return resultSet;
    }

    public static boolean saveAnalyseposition(AtlAnalyseposition pos) {
        return new DatabaseAccess().saveOrUpdate(pos);
    }

    public static boolean removeAnalyseposition(AtlAnalyseposition pos) {
        return new DatabaseAccess().delete(pos);
    }
}
