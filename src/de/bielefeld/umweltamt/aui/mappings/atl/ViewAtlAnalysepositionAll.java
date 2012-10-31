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

// Generated 18.07.2012 12:46:09 by Hibernate Tools 3.3.0.GA

package de.bielefeld.umweltamt.aui.mappings.atl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.DatabaseAccess;
import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Home object for domain model class ViewAtlAnalysepositionAll.
 * @see de.bielefeld.umweltamt.aui.mappings.atl.ViewAtlAnalysepositionAll
 * @author Hibernate Tools
 */
public class ViewAtlAnalysepositionAll extends
    AbstractViewAtlAnalysepositionAll {

    private static final long serialVersionUID = 7628595838089115005L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

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

    public static boolean saveOrUpdate(
        ViewAtlAnalysepositionAll detachedInstance) {
        return new DatabaseAccess().saveOrUpdate(detachedInstance);
    }

    public static ViewAtlAnalysepositionAll merge(
        ViewAtlAnalysepositionAll detachedInstance) {
        return (ViewAtlAnalysepositionAll) new DatabaseAccess()
            .merge(detachedInstance);
    }

    public static boolean delete(ViewAtlAnalysepositionAll detachedInstance) {
        return new DatabaseAccess().delete(detachedInstance);
    }

    public ViewAtlAnalysepositionAll findById(java.lang.Integer id) {
        log.debug("getting ViewAtlAnalysepositionAll instance with id: " + id);
        ViewAtlAnalysepositionAll instance = (ViewAtlAnalysepositionAll) new DatabaseAccess()
            .get(ViewAtlAnalysepositionAll.class, id);
        if (instance == null) {
            log.debug("get successful, no instance found");
        } else {
            log.debug("get successful, instance found");
        }
        return instance;
    }

    public static List<?> getAll() {
        String query = "FROM ViewAtlAnalysepositionAll";
        return new DatabaseAccess().createQuery(query).list();
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
    public static List<?> get(
            AtlParameter param, AtlEinheiten einh, AtlProbepkt pkt,
            Date beginDate, Date endDate, String analyseVon) {

        String query = "FROM ViewAtlAnalysepositionAll pos "
                + "WHERE pos.parameterId = :param "
                + "and pos.einheitenId = :einh "
                + "and pos.probepktId = :pkt "
                + "and pos.datumDerEntnahme >= :beginDate "
                + "and pos.datumDerEntnahme <= :endDate ";

        if (analyseVon != null && !analyseVon.equals("")) {
            query += "and pos.analyseVon like :analyseVon ";
        }

        query += "ORDER BY pos.datumDerEntnahme";

        List<?> viewResult = null;

        if (analyseVon != null && !analyseVon.equals("")) {
            viewResult = new DatabaseAccess().createQuery(query)
                    .setString("param", param.getOrdnungsbegriff())
                    .setInteger("einh", einh.getId())
                    .setInteger("pkt", pkt.getObjektid())
                    .setDate("beginDate", beginDate)
                    .setDate("endDate", endDate)
                    .setString("analyseVon", analyseVon)
                    .list();
        } else {
            viewResult = new DatabaseAccess().createQuery(query)
                    .setString("param", param.getOrdnungsbegriff())
                    .setInteger("einh", einh.getId())
                    .setInteger("pkt", pkt.getObjektid())
                    .setDate("beginDate", beginDate)
                    .setDate("endDate", endDate)
                    .list();
        }

        List<AtlAnalyseposition> result = new ArrayList<AtlAnalyseposition>();
        ViewAtlAnalysepositionAll viewPos = null;
        AtlAnalyseposition pos = null;
        for (Object obj : viewResult) {
            viewPos = (ViewAtlAnalysepositionAll) obj;
            pos = viewPos.getAtlAnalyseposition();
            result.add(pos);
        }
        return result;
    }

    // Dirty cast/copy...
    public AtlAnalyseposition getAtlAnalyseposition() {
        AtlAnalyseposition pos = new AtlAnalyseposition();
        pos.setId(this.getId());
        pos.setGrkl(this.getGrkl());
        pos.setWert(this.getWert());
        pos.setAnalyseVon(this.getAnalyseVon());
        pos.setBericht(this.getBericht());
        pos.setNormwert(this.getNormwert());
        pos.setAtlEinheiten(AtlEinheiten.findById(this.getEinheitenId()));
        pos.setAtlParameter(AtlParameter.getParameter(this.getParameterId()));
        pos.setAtlProbenahmen(
            AtlProbenahmen.getProbenahme(this.getProbenahmeId()));
        pos.setEnabled(this.isEnabled());
        pos.setDeleted(this.isDeleted());
        return pos;
    }
}
