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
 * Created Thu Jan 20 17:02:28 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A class that represents a row in the 'VAWS_WASSEREINZUGSGEBIETE' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class VawsWassereinzugsgebiete
    extends AbstractVawsWassereinzugsgebiete
    implements Serializable
{
    /**
     * Simple constructor of VawsWassereinzugsgebiete instances.
     */
    public VawsWassereinzugsgebiete()
    {
    }

    /**
     * Constructor of VawsWassereinzugsgebiete instances given a simple primary key.
     * @param wasserezgbid
     */
    public VawsWassereinzugsgebiete(java.lang.Integer wasserezgbid)
    {
        super(wasserezgbid);
    }

    /* Add customized code below */

    /**
     * Liefert den Namen des Einzugsgebietes.
     */
    public String toString() {
        return super.getEzgbname();
    }

    /**
     * Liefert alle vorhandenen VAWS-Wassereinzugsgebiete.
     * @param session Eine Hibernate-Session
     * @return Alle vorhandenen Wassereinzugsgebiete
     * @throws HibernateException Falls ein Datenbank-Fehler auftritt
     */
    public static VawsWassereinzugsgebiete[] getWEinzugsgebiete(Session session) throws HibernateException {
        List list = null;
        String suchString = "FROM VawsWassereinzugsgebiete vwezg ORDER BY vwezg.wasserezgbid";
        VawsWassereinzugsgebiete[] tmp;
        Query query = session.createQuery(suchString);
        query.setCacheable(true);
        query.setCacheRegion("wezgbliste");
        list = query.list();
        tmp = new VawsWassereinzugsgebiete[list.size()];
        tmp = (VawsWassereinzugsgebiete[]) list.toArray(tmp);
        return tmp;
    }

    public static VawsWassereinzugsgebiete[] getWEinzugsgebiete() throws HibernateException {
        Session session = HibernateSessionFactory.currentSession();
        VawsWassereinzugsgebiete[] tmp = getWEinzugsgebiete(session);
        HibernateSessionFactory.closeSession();
        return tmp;
    }
}
