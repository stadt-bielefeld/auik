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
 * A class that represents a row in the 'VAWS_STANDORTGGHWSG' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class VawsStandortgghwsg
    extends AbstractVawsStandortgghwsg
    implements Serializable
{
    /**
     * Simple constructor of VawsStandortgghwsg instances.
     */
    public VawsStandortgghwsg()
    {
    }

    /**
     * Constructor of VawsStandortgghwsg instances given a simple primary key.
     * @param standortggid
     */
    public VawsStandortgghwsg(java.lang.Integer standortggid)
    {
        super(standortggid);
    }

    /* Add customized code below */

    public String toString() {
        return super.getStandortgg();
    }

    /**
     * Liefert alle vorhandenen VAWS-Standortgeg.
     * @return Alle vorhandenen VawsStandortgghwsg
     */
    public static VawsStandortgghwsg[] getStandortGg() {
        List list;
        String suchString = "FROM VawsStandortgghwsg vsgg ORDER BY vsgg.standortggid";
        VawsStandortgghwsg[] tmp;

        try {
            Session session = HibernateSessionFactory.currentSession();
            Query query = session.createQuery(suchString);
            query.setCacheable(true);
            query.setCacheRegion("standortggliste");
            list = query.list();
            tmp = new VawsStandortgghwsg[list.size()];
            tmp = (VawsStandortgghwsg[]) list.toArray(tmp);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (VawsStandortgghwsg)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return tmp;
    }
}
