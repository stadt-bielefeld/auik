/*
 * Created Tue Sep 06 14:45:58 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_BEHAELTERART' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class VawsBehaelterart
    extends AbstractVawsBehaelterart
    implements Serializable
{
    /**
     * Simple constructor of VawsBehaelterart instances.
     */
    public VawsBehaelterart()
    {
    }

    /* Add customized code below */

    public String toString() {
        return super.getBehaelterart();
    }

    /**
     * Liefer alle VAWS-Behälterarten.
     * @return Ein Array mit den Namen aller Behälterarten.
     */
    public static String[] getBehaelterarten() {
        List list;
        String suchString = "select bha.behaelterart " +
                "from VawsBehaelterart bha " +
                "order by bha.id";
        String[] tmp;

        try {
            Session session = HibernateSessionFactory.currentSession();
            Query query = session.createQuery(suchString);
            query.setCacheable(true);
            query.setCacheRegion("vawsbhaliste");
            list = query.list();
            tmp = new String[list.size()];
            tmp = (String[]) list.toArray(tmp);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler (VawsBehaelterart)", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return tmp;
    }
}
