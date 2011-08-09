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
 * Created Wed Feb 16 15:12:00 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'ATL_ANALYSEPOSITION' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AtlAnalyseposition
    extends AbstractAtlAnalyseposition
    implements Serializable
{
    /**
     * Simple constructor of AtlAnalyseposition instances.
     */
    public AtlAnalyseposition()
    {
        this(new Float(0.0));
    }

    /**
     * Constructor of AtlAnalyseposition instances given a value.
     * @param wert
     */
    public AtlAnalyseposition(Float wert)
    {
        setWert(wert);
    }

    /**
     * Constructor of AtlAnalyseposition instances given a AtlProbenahme.
     * @param probe
     */
    public AtlAnalyseposition(AtlProbenahmen probe)
    {
        this(new Float(0.0));
        setAtlProbenahmen(probe);
    }

    /**
     * Constructor of AtlAnalyseposition instances given a simple primary key.
     * @param id
     */
    public AtlAnalyseposition(java.lang.Integer id)
    {
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
    public boolean equals(Object rhs)
    {
        // Wenn das andere Objekt null ist, sind wir nicht gleich
        if (rhs == null) {
            return false;
        }
        // Wenn das andere Objekt auf uns verweist, sind wir natürlich gleich
        if (this == rhs) {
            return true;
        }
        // Wenn das andere Objekt keine AtlAnalyseposition ist, können wir nicht gleich sein
        if (! (rhs instanceof AtlAnalyseposition)) {
            return false;
        }

        // Das andere Objekt ist also eine AtlAnalyseposition.
        AtlAnalyseposition that = (AtlAnalyseposition) rhs;

        // Wenn beide einen Primary-Key haben...
        if (this.getId() != null && that.getId() != null)
        {
            // ... und diese nicht gleich sind ...
            if (!this.getId().equals(that.getId()))
            {
                // ... sind die Objekte auch nicht gleich.
                return false;
            }  else {
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
     * Hashcode auf Basis des Primary-Keys, wenn dieser existiert, sonst
     * auf Basis von von Wert, Parameter, Einheit und "Analyse von".
     * @return int
     */
    public int hashCode()
    {
        if (this.hashValue == 0)
        {
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
     * @return Einen String der Form "[Position: Parameter: Wert Einheit, Analyse_Von, [Probenahme], ID:Id]"
     */
    public String toString() {
        String tmp = "[Position: "+ getAtlParameter() +": "+ getWert() +" "+ getAtlEinheiten() +", "+ getAnalyseVon() +", ";
        if (getAtlProbenahmen() != null) {
            tmp += getAtlProbenahmen() + ", ";
        }
        if (getId() != null) {
            tmp += "ID:"+ getId();
        } else {
            tmp += "UNSAVED";
        }
        tmp +=  "]";
        return tmp;
    }

    // Liefert einObjekt vom Typ Analysepositionen mit einer gegebenen ID
    public static AtlAnalyseposition getAnalysepositionObjekt (Integer id) {
        AtlAnalyseposition objekt;
        try {
            Session session = HibernateSessionFactory.currentSession();
            objekt = (AtlAnalyseposition) session.get(AtlAnalyseposition.class, id);
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            objekt = null;
        }

        return objekt;
    }


    /**
     * Liefert eine Liste der Analysepositionen mit einem gegebenen
     * Parameter aus einer gegebenen Probenahme.
     * @param probe Die Probenahme.
     * @param param Der Parameter.
     * @return Eine Liste mit <code>AtlAnalyseposition</code>en.
     */
    public static List getAnalysepositionen(AtlProbenahmen probe, AtlParameter param) {
        // TODO: Evtl. mit komplizierterem HQL gleich beim Laden der Proben lösen?
        // select probe.name, probe.pos....
//        String query =
//            "from AtlAnalyseposition pos " +
//            "where pos.atlParameter = ? " +
//            "and pos.atlProbenahmen = ? " +
//            "order by pos.atlProbenahmen.datumDerEntnahme";

        if (!Hibernate.isInitialized(probe.getAtlAnalysepositionen())) {
        }

        List proben = new ArrayList();

        for (Iterator it = probe.getAtlAnalysepositionen().iterator(); it.hasNext();) {
            AtlAnalyseposition pos = (AtlAnalyseposition) it.next();
            if ((pos.getAtlParameter().getOrdnungsbegriff().equals(param.getOrdnungsbegriff()))) {
                proben.add(pos);
            }
        }

//        try {
//            Session session = HibernateSessionFactory.currentSession();
//
//            proben = session.find(
//                    query,
//                    new Object[]{param, probe},
//                    new Type[]{
//                            Hibernate.entity(AtlParameter.class),
//                            Hibernate.entity(AtlProbenahmen.class)}
//            );
//        } catch (HibernateException e) {
//            throw new RuntimeException("Datenbank-Fehler (AtlAnalysepositionen)", e);
//        } finally {
//            HibernateSessionFactory.closeSession();
//        }

        //AUIKataster.debugOutput(proben.size() + " Analysepos. gefunden.", "AtlAnalyseposition");

        return proben;
    }

    /**
     * Liefert eine Liste der Analysepositionen mit einem gegebenen
     * Parameter, einer bestimmten Einheit, an einem bestimmten Probepunkt,
     * die zwischen <code>beginDate</code> und <code>endDate</code> (inklusive) genommen wurden.
     * Wenn <code>analyseVon</code> nicht <code>null</code> oder "" ist, werden nur Analysepositionen
     * geliefert, die von einer bestimmten Stelle analysiert wurden.
     * @param param Der Parameter
     * @param einh Die Einheit
     * @param pkt Der Probepunkt
     * @param beginDate Das Anfangs-Datum
     * @param endDate Das End-Datum
     * @param analyseVon Wo wurde analysiert?
     * @return Eine Liste mit <code>AtlAnalyseposition</code>en
     */
    public static List getAnalysepositionen(AtlParameter param, AtlEinheiten einh, AtlProbepkt pkt, Date beginDate, Date endDate, String analyseVon) {
        AUIKataster.debugOutput("Suche (HQL): p:" + param+ ", e:" + einh + ", pkt:" + pkt + ", bD:"+beginDate+", eD:"+endDate+", aV:'"+analyseVon+"'", "AtlAnalyseposition");

        String paramID = param.getOrdnungsbegriff();
        Integer einhID = einh.getId();
        Integer pktID = pkt.getObjektid();




        String query =
            "from AtlAnalyseposition pos " +
            "where pos.atlParameter = ? " +
            "and pos.atlEinheiten = ? " +
            "and pos.atlProbenahmen.atlProbepkt = ? " +
            "and pos.atlProbenahmen.datumDerEntnahme >= ? " +
            "and pos.atlProbenahmen.datumDerEntnahme <= ? ";

        if (analyseVon != null && !analyseVon.equals("")) {
            query += "and pos.analyseVon like ? ";
        }

        query += "order by pos.atlProbenahmen.datumDerEntnahme";

        List proben;
        try {
            Session session = HibernateSessionFactory.currentSession();

            if (analyseVon != null && !analyseVon.equals("")) {
            proben = session.createQuery(
                    query)
                    .setEntity(0, param)
                    .setEntity(1, einh)
                    .setEntity(2, pkt)
                    .setDate(3, beginDate)
                    .setDate(4, endDate)
                    .setString(5, analyseVon)
                    .list();

            } else {
                proben = session.createQuery(
                    query)
                    .setEntity(0, param)
                    .setEntity(1, einh)
                    .setEntity(2, pkt)
                    .setDate(3, beginDate)
                    .setDate(4, endDate)
                    .list();

            }


        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (AtlAnalysepositionen)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return proben;
    }

    // Liefert eine Liste der Analysepositionen mit einem gegebenen
    // Parameter, an einem bestimmten Probepunkt.
    public static List getAnalysepos(AtlParameter param,  Integer pkt) {
        AUIKataster.debugOutput("Suche (HQL): p:" + param+ " pkt:" + pkt ,  "AtlAnalyseposition");


        String query =
            "from AtlAnalyseposition as pos " +
            "where pos.atlParameter = ? " ;


        if (pkt != null) {
            query += "and pos.atlProbenahmen.atlProbepkt.objektid = '" + pkt + "' ";
        }
        else {

            AUIKataster.debugOutput("objektid = null","getAnalysepos" );
        }

        query += "order by pos.atlProbenahmen.datumDerEntnahme";

            List proben;
         try {
            Session session = HibernateSessionFactory.currentSession();



            proben = session.createQuery(
                    query)
                    .setEntity(0, param)
                    .list();



        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (AtlAnalysepositionen)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return proben;
    }


    // Liefert eine Liste der Parameter, die immer bestimmt werden.
	public static List getVorOrtParameter() {
	
	    String query =
	        "from AtlAnalyseposition as pos " +
	        "where pos.atlParameter.ordnungsbegriff = 'L10821' " ;
	
	        List param;
	     try {
	        Session session = HibernateSessionFactory.currentSession();
	
	
	
	        param = session.createQuery(
	                query)
	                .list();
	
	
	
	    } catch (HibernateException e) {
	        throw new RuntimeException("Datenbank-Fehler (AtlAnalysepositionen)", e);
	    } finally {
	        HibernateSessionFactory.closeSession();
	    }
	
	    return param;
	}

	public static List getSielhautpos(String param,  Integer pkt, Date anfang, Date ende) {
        AUIKataster.debugOutput("Suche (HQL): p:" + param+ ", pkt:" + pkt + ", bD:"+anfang+", eD:"+ende+", aV:'", "AtlAnalyseposition");
        List proben;
        proben = null;

        String query =
            "from AtlAnalyseposition pos " +
            "where pos.atlProbenahmen.datumDerEntnahme >= ? " +
            "and pos.atlProbenahmen.datumDerEntnahme <= ? ";


        if (pkt != null)
        {
            query += "and pos.atlProbenahmen.atlProbepkt.objektid = '" + pkt + "' ";
        }
        else
        {

            AUIKataster.debugOutput("objektid = null","getSielhautpos" );
        }

        if (param != null)
        {
            query += "and pos.atlParameter.bezeichnung = '" + param + "' ";

        }
        else
        {

            AUIKataster.debugOutput("param = null","getSielhautpos" );
        }



        query += "order by pos.atlProbenahmen.datumDerEntnahme";



        try {
            Session session = HibernateSessionFactory.currentSession();


            proben = session.createQuery(
                    query)
                    .setDate(0, anfang)
                    .setDate(1, ende)
                    .list();




    } catch (HibernateException e) {
        throw new RuntimeException("Datenbank-Fehler (AtlAnalysepositionen)", e);
    } finally {
        HibernateSessionFactory.closeSession();
    }


        return proben;
    }

    // Liefert eine Liste der Analyseinstitute.
    public static String[]  getAnalysierer() {

        List proben = null;
        String query =
            "select distinct analyseVon from AtlAnalyseposition as ap " + 
            "order by ap.analyseVon";

         try {
            Session session = HibernateSessionFactory.currentSession();

            proben = session.createQuery(
                    query)
                    .list();
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (AtlAnalysepositionen)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }
        String[] tmp = new String[proben.size()];
        tmp = (String[]) proben.toArray(tmp);

        return tmp;
    }



    public static boolean saveAnalyseposition(AtlAnalyseposition pos) {
        boolean saved;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(pos);
//            session.update(pos.getAtlProbenahmen());
            tx.commit();
            saved = true;
        } catch (HibernateException e) {
            saved = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "AtlAnalyseposition.saveAnalyseposition", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    public static boolean removeAnalyseposition(AtlAnalyseposition pos) {
        boolean success;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(pos);
            tx.commit();
            success = true;
        } catch (HibernateException e) {
            success = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "Analyseposition.objectRemoved", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return success;
    }
}
