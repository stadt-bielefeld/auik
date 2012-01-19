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

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the 'ATL_PROBEPKT' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AtlProbepkt
    extends AbstractAtlProbepkt
    implements Serializable
{
	/** Logging */
    private static final Logger log = AuikLogger.getLogger();

    /**
     * Simple constructor of AtlProbepkt instances.
     */
    public AtlProbepkt()
    {
    }

    /**
     * Constructor of AtlProbepkt instances given a simple primary key.
     * @param objektid
     */
    public AtlProbepkt(java.lang.Integer objektid)
    {
        super(objektid);
    }

    /* Add customized code below */

    public String toString() {
        return "[Probepunkt:"+getObjektid()+", Art:"+getAtlProbeart()+", Nr:"+getNummer()+"]";
    }

    /**
     * Liefert den ersten Probepunkt einer bestimmten Art und Kläranlage.
     * @return Den Probepunkt oder <code>null</code>, falls kein Probepunkt dieser Art mit dieser Kläranlage existiert.
     */
    public static AtlProbepkt getKlaerschlammProbepunkt(AtlProbeart art, AtlKlaeranlagen ka) {
        List pkte;

        try {
            Session session = HibernateSessionFactory.currentSession();
            pkte = session.createQuery(
                    "from AtlProbepkt as probepkt where " +
                    "probepkt.atlProbeart = ? " +
                    "and probepkt.atlKlaeranlagen = ? " +
                    "order by probepkt.objektid asc")
                    .setEntity(0, art)
                    .setEntity(1, ka)
                    .list();
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (AtlProbepkt)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        AtlProbepkt tmp;
        if (pkte.size() > 0) {
            tmp = (AtlProbepkt) pkte.get(0);
        } else {
            tmp = null;
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

    private static AtlProbepkt getProbepunkt(Session session, Integer id) {
        AtlProbepkt punkt = null;

        try {
            List pkte = session.createQuery(
                    "from AtlProbepkt as probepkt where " +
                    "probepkt.objektid = ?")
                    .setInteger(0, id)
                    .list();

            if (pkte.size() > 0) {
                punkt = (AtlProbepkt) pkte.get(0);
            }
        } catch (HibernateException e) {
        }

        return punkt;
    }

    public static AtlProbepkt getProbepunkt(Integer id) {
        AtlProbepkt pkt = null;

        try {
            Session session = HibernateSessionFactory.currentSession();
            pkt = getProbepunkt(session, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return pkt;
    }

    private static AtlProbepkt getProbepunktByObjekt(BasisObjekt objekt, Session session) {
        AtlProbepkt punkt = null;

        if (objekt != null) {
            try {
                List pkte = session.createQuery(
                        "from AtlProbepkt as probepkt where " +
                        "probepkt.basisObjekt = ?")
                        .setEntity(0, objekt)
                        .list();

                if (pkte.size() > 0) {
                    punkt = (AtlProbepkt) pkte.get(0);
                }
            } catch (HibernateException e) {
                throw new RuntimeException("Datenbank-Fehler (AtlProbepkt)", e);
            }
        }

        return punkt;
    }

    public static AtlProbepkt getProbepunktByObjekt(BasisObjekt objekt) {
        AtlProbepkt punkt;
        try {
            Session session = HibernateSessionFactory.currentSession();
            punkt = getProbepunktByObjekt(objekt, session);
        } catch (HibernateException e) {
            punkt = null;
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return punkt;
    }

    public static AtlProbepkt getSielhautProbepunkt(AtlSielhaut siel) {
        AtlProbepkt punkt;

        try {
            Session session = HibernateSessionFactory.currentSession();
            List pkte = session.createQuery(
                    "from AtlProbepkt as probepkt where " +
                    "probepkt.atlSielhaut = ?")
                    .setEntity(0, siel)
                    .list();

            if (pkte.size() > 0) {
                punkt = (AtlProbepkt) pkte.get(0);
                log.debug("SielhautBearbeiten-Probepunkt " + punkt
                		+ " aus DB geholt.");
            } else {
                punkt = null;
            }
        } catch (HibernateException e) {
            //punkt = null;
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return punkt;
    }

    public static List getProbenehmerpunkte() {
        List pkte;

        try {
            Session session = HibernateSessionFactory.currentSession();
            pkte = session.createQuery(
                    "select distinct pk from AtlProbepkt as pk " +
                    "inner join pk.atlProbenahmen as pn " +
                    "where pn.kennummer like '3%' " +
                    "and pk.basisObjekt.inaktiv = false ")
                    .list();

        } catch (HibernateException e) {
            //punkt = null;
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return pkte;
    }

    public static boolean saveProbepunkt(AtlProbepkt punkt) {
        boolean saved;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(punkt);
            tx.commit();
            saved = true;
            log.debug("Probepunkt " + punkt + " gespeichert.");
        } catch (HibernateException e) {
            saved = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "AtlProbepkt.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    public static boolean removeProbepunkt(AtlProbepkt punkt) {
        boolean removed;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(punkt);
            tx.commit();
            removed = true;
            log.debug("(ProbepunktPanel.saveProbepunktDaten) "
            		+ "Probepunkt " + punkt + " gespeichert.");
        } catch (HibernateException e) {
            removed = false;
            log.debug("(ProbepunktPanel.saveProbepunktDaten) "
            		+ "Probepunkt " + punkt
            		+ " konnte nicht gespeichert werden!");
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "ProbepunktPanel.saveProbepunktDaten", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return removed;
    }


    public BasisBetreiber getBasisBetreiber() {
        BasisObjekt basisObj = getBasisObjekt();

        return basisObj != null ? basisObj.getBasisBetreiber() : null;
    }

	public static List getESatzung() {
        List pkt;
        try {
            Session session = HibernateSessionFactory.currentSession();
            pkt = session.createQuery(
                    "from AtlProbepkt as pk where " +
                    "pk.atlProbeart.id = 3 " +
                    "and pk.basisObjekt.inaktiv = false " +
                    "order by pk.basisObjekt.basisStandort")
                    .list();
            HibernateSessionFactory.closeSession();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
		return pkt;
	}

	public static List getUWB() {
        List pkt;
        try {
            Session session = HibernateSessionFactory.currentSession();
            pkt = session.createQuery(
                    "from AtlProbepkt as pk where " +
                    "pk.atlProbeart.id = 2 " +
                    "and pk.basisObjekt.inaktiv = false " +
                    "order by pk.basisObjekt.basisStandort")
                    .list();
            HibernateSessionFactory.closeSession();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
		return pkt;
	}

	public static List getInaktiv() {
        List pkt;
        try {
            Session session = HibernateSessionFactory.currentSession();
            pkt = session.createQuery(
                    "from AtlProbepkt as pk where " +
                    "pk.basisObjekt.inaktiv = true " +
                    "order by pk.basisObjekt.basisStandort")
                    .list();
            HibernateSessionFactory.closeSession();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        }
		return pkt;
	}
}
