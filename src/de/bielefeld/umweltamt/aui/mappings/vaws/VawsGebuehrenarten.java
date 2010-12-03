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
