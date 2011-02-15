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
 * Created Wed Feb 16 15:12:01 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'ATL_KLAERANLAGEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AtlKlaeranlagen
    extends AbstractAtlKlaeranlagen
    implements Serializable
{
    /** Die ID der KA Brake */
    final public static Integer BRAKE = new Integer(1);
    /** Die ID der KA Heepen */
    final public static Integer HEEPEN = new Integer(2);
    /** Die ID der KA Sennestadt */
    final public static Integer SENNESTADT = new Integer(3);
    /** Die ID der KA O.Lutter */
    final public static Integer OBERE_LUTTER = new Integer(4);
    /** Die ID der KA Verl-Sende */
    final public static Integer VERL_SENDE = new Integer(5);

    /**
     * Simple constructor of AtlKlaeranlagen instances.
     */
    public AtlKlaeranlagen()
    {
    }

    /**
     * Constructor of AtlKlaeranlagen instances given a simple primary key.
     * @param kaId
     */
    public AtlKlaeranlagen(java.lang.Integer kaId)
    {
        super(kaId);
    }

    /* Add customized code below */

    /**
     * Liefert den Namen der Kläranlage.
     */
    public String toString() {
        return getAnlage();
    }

    /**
     * Liefert eine bestimmte Kläranlage.
     * @param id Die Id der Kläranlage
     * @param session Eine Hibernate-Session
     * @return Die Kläranlage
     */
    public static AtlKlaeranlagen getKlaeranlage(Integer id, Session session) {
        AtlKlaeranlagen ka;
        try {
            ka = (AtlKlaeranlagen) session.get(AtlKlaeranlagen.class, id);
        } catch (HibernateException e) {
            //e.printStackTrace();
            ka = null;
        }
        return ka;
    }

    /**
     * Liefert eine bestimmte Kläranlage.
     * @param id Die Id der Kläranlage
     * @return Die Kläranlage
     */
    public static AtlKlaeranlagen getKlaeranlage(Integer id) {
        AtlKlaeranlagen ka;
        Session session;
        try {
            session = HibernateSessionFactory.currentSession();
            ka = getKlaeranlage(id, session);
        } catch (HibernateException e) {
            //e.printStackTrace();
            ka = null;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return ka;
    }

    /**
     * Liefert alle vorhandenen Kläranlagen.
     * @param session Die Hibernate-Session, die benutzt werden soll
     * @return Alle vorhandenen Kläranlagen
     * @throws HibernateException Falls ein Datenbank-Fehler auftritt
     */
    public static AtlKlaeranlagen[] getKlaeranlagen(Session session) throws HibernateException {
        List list = null;

        String suchString = "from AtlKlaeranlagen ka order by ka.kaId";

        Query query = session.createQuery(suchString);
        query.setCacheable(true);
        query.setCacheRegion("kaliste");
        list = query.list();

        AtlKlaeranlagen[] tmp = new AtlKlaeranlagen[list.size()];
        tmp = (AtlKlaeranlagen[]) list.toArray(tmp);

        return tmp;
    }

    /**
     * Liefert alle vorhandenen Kläranlagen.
     * @return Alle vorhandenen Kläranlagen
     * @throws HibernateException Falls ein Datenbank-Fehler auftritt
     */
    public static AtlKlaeranlagen[] getKlaeranlagen() {
        AtlKlaeranlagen[] tmp;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tmp = getKlaeranlagen(session);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return tmp;
    }
}
