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
 * Created Tue Sep 06 14:46:36 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_GEBUEHRENARTEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class VawsGebuehrenarten
    extends AbstractVawsGebuehrenarten
    implements Serializable
{
    /**
     * Simple constructor of VawsGebuehrenarten instances.
     */
    public VawsGebuehrenarten()
    {
    }

    /**
     * Constructor of VawsGebuehrenarten instances given a simple primary key.
     * @param id
     */
    public VawsGebuehrenarten(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */

    public String toString() {
        return getGebuehrenart();
    }

    /**
     * Liefert alle Gebührenarten für die Verwaltungsgebühren.
     * @return Ein Array mit den Namen aller möglichen Gebührenarten.
     */
    public static VawsGebuehrenarten[] getAllGebuehrenarten() {
        List list;
        String suchString = "from VawsGebuehrenarten vga " +
                "order by vga.id";
        VawsGebuehrenarten[] tmp;

        try {
            Session session = HibernateSessionFactory.currentSession();
            Query query = session.createQuery(suchString);
            query.setCacheable(true);
            query.setCacheRegion("vawsvgaliste");
            list = query.list();
            tmp = new VawsGebuehrenarten[list.size()];
            tmp = (VawsGebuehrenarten[]) list.toArray(tmp);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return tmp;
    }
}
