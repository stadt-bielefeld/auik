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
 * Created Wed Feb 16 15:12:03 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'ATL_PROBEPKT' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AtlProbepkt
    extends AbstractAtlProbepkt
    implements Serializable
{
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

    /* Add customized code below */

    @Override
    public String toString() {
        return "[Probepunkt:"+getObjektid()+", Art:"+getAtlProbeart()+", Nr:"+getNummer()+"]";
    }

    /**
     * Liefert den ersten Probepunkt einer bestimmten Art und Kläranlage.
     * @return Den Probepunkt oder <code>null</code>, falls kein Probepunkt dieser Art mit dieser Kläranlage existiert.
     */
    public static AtlProbepkt getKlaerschlammProbepunkt(AtlProbeart art, AtlKlaeranlagen ka) {
        List<?> pkte = null;
        pkte = new DatabaseAccess().createQuery(
                "from AtlProbepkt as probepkt where " +
                "probepkt.atlProbeart = :art " +
                "and probepkt.atlKlaeranlagen = :ka " +
                "order by probepkt.objektid asc")
                .setEntity("art", art)
                .setEntity("ka", ka)
                .list();
        AtlProbepkt tmp = null;
        if (pkte.size() > 0) {
            tmp = (AtlProbepkt) pkte.get(0);
        }
        return tmp;
    }

    /*public static List getFirmenProbepunkte() throws HibernateException {
        Session session = HibernateSessionFactory.currentSession();
        List pkte = session.find(
                "from AtlProbepkt as probepkt where " +
                "probepkt.atlProbeart.artId = ? " +
                "or probepkt.atlProbeart.artId = ? " +
                "order by probepkt.pktId asc",
                new Object[]{    AtlProbeart.ABWASSER_ES,
                                AtlProbeart.ABWASSER_UWB},
                new Type[]{    Hibernate.INTEGER,
                            Hibernate.INTEGER}
            );
        HibernateSessionFactory.closeSession();

        return pkte;
    }

    public static List getFirmenProbepunkte(AtlFirmen firma) throws HibernateException {
        Session session = HibernateSessionFactory.currentSession();
        List pkte = session.find(
                "from AtlProbepkt as probepkt where " +
                "probepkt.atlFirmen = ? " +
                "order by probepkt.pktId asc",
                new Object[]{firma},
                new Type[]{    Hibernate.entity(AtlFirmen.class)}
            );
        HibernateSessionFactory.closeSession();

        return pkte;
    }*/

    public static AtlProbepkt getProbepunkt(Integer id) {
        List<?> pkte = null;
        AtlProbepkt pkt = null;
        pkte = new DatabaseAccess().createQuery(
                "from AtlProbepkt as probepkt where " +
                "probepkt.objektid = :id")
                .setInteger("id", id)
                .list();
        if (pkte.size() > 0) {
            pkt = (AtlProbepkt) pkte.get(0);
        }
        return pkt;
    }

    public static AtlProbepkt getProbepunktByObjekt(BasisObjekt objekt) {
        List<?> pkte = null;
        AtlProbepkt punkt = null;
        if (objekt != null) {
            pkte = new DatabaseAccess().createQuery(
                    "from AtlProbepkt as probepkt where " +
                    "probepkt.basisObjekt = :objekt")
                    .setEntity("objekt", objekt)
                    .list();
            if (pkte.size() > 0) {
                punkt = (AtlProbepkt) pkte.get(0);
            }
        }
        return punkt;
    }

    public static AtlProbepkt getSielhautProbepunkt(AtlSielhaut siel) {
        List<?> pkte = null;
        AtlProbepkt punkt = null;
        pkte = new DatabaseAccess().createQuery(
                "from AtlProbepkt as probepkt where " +
                "probepkt.atlSielhaut = :siel")
                .setEntity("siel", siel)
                .list();
        if (pkte.size() > 0) {
            punkt = (AtlProbepkt) pkte.get(0);
            log.debug("SielhautBearbeiten-Probepunkt " + punkt
                    + " aus DB geholt.");
        }
        return punkt;
    }

    public static List<?> getProbenehmerpunkte() {
        List<?> pkte = null;
        pkte = new DatabaseAccess().createQuery(
                "select distinct pk from AtlProbepkt as pk " +
                "inner join pk.atlProbenahmen as pn " +
                "where pn.kennummer like '3%' " +
                "and pk.basisObjekt.inaktiv = false ")
                .list();
        return pkte;
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
            log.debug("Probepunkt " + punkt
                    + " konnte nicht gelöscht werden!");
        }
        return removed;
    }


    public BasisBetreiber getBasisBetreiber() {
        BasisObjekt basisObj = getBasisObjekt();

        return basisObj != null ? basisObj.getBasisBetreiber() : null;
    }

	public static List<?> getESatzung() {
        List<?> pkt = null;
        pkt = new DatabaseAccess().createQuery(
                "from AtlProbepkt as pk where " +
                "pk.atlProbeart.id = 3 " +
                "and pk.basisObjekt.inaktiv = false " +
                "order by pk.basisObjekt.basisStandort")
                .list();
		return pkt;
	}

	public static List<?> getUWB() {
        List<?> pkt = null;
        pkt = new DatabaseAccess().createQuery(
                "from AtlProbepkt as pk where " +
                "pk.atlProbeart.id = 2 " +
                "and pk.basisObjekt.inaktiv = false " +
                "order by pk.basisObjekt.basisStandort")
                .list();
		return pkt;
	}

	public static List<?> getInaktiv() {
        List<?> pkt = null;
        pkt = new DatabaseAccess().createQuery(
                "from AtlProbepkt as pk where " +
                "pk.basisObjekt.inaktiv = true " +
                "order by pk.basisObjekt.basisStandort")
                .list();
		return pkt;
	}
}
