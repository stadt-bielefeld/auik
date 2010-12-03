/*
 * Created Tue Sep 06 14:48:01 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_VBFEINSTUFUNG' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class VawsVbfeinstufung
    extends AbstractVawsVbfeinstufung
    implements Serializable
{
    /**
     * Simple constructor of VawsVbfeinstufung instances.
     */
    public VawsVbfeinstufung()
    {
    }

    /* Add customized code below */

    public String toString() {
        return super.getVbfeinstufung();
    }

    /**
     * Liefer alle VBF-Einstufungen.
     * @return Ein Array mit den Namen aller VBF-Einstufungen.
     */
    public static String[] getVbfeinstufungen() {
        List list;
        String suchString = "select vbf.vbfeinstufung " +
                "from VawsVbfeinstufung vbf " +
                "order by vbf.vbfeinstufung";
        String[] tmp;

        try {
            Session session = HibernateSessionFactory.currentSession();
            Query query = session.createQuery(suchString);
            query.setCacheable(true);
            query.setCacheRegion("vawsvbfliste");
            list = query.list();
            tmp = new String[list.size()];
            tmp = (String[]) list.toArray(tmp);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (VawsVbfeinstufung)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return tmp;
    }
}
