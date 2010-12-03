/*
 * Created Wed Apr 27 12:06:50 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'ANH_ENTSORGER' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AnhEntsorger
    extends AbstractAnhEntsorger
    implements Serializable
{
    /**
     * Simple constructor of Anh50Entsorger instances.
     */
    public AnhEntsorger()
    {
    }

    /**
     * Constructor of Anh50Entsorger instances given a simple primary key.
     * @param entsorgerid
     */
    public AnhEntsorger(java.lang.Integer entsorgerid)
    {
        super(entsorgerid);
    }

    public String toString() {
        return getEntsorger();
    }

    /**
     * Liefert alle vorhandenen Entsorger.
     * @param session Eine Hibernate-Session
     * @return Alle vorhandenen Entsorger
     * @throws HibernateException Wenn ein Datenbank-Fehler auftritt
     */
    private static AnhEntsorger[] getEntsorg(Session session) throws HibernateException {
        List list = null;

        String suchString = "from AnhEntsorger ahe order by ahe.entsorgerid";

        Query query = session.createQuery(suchString);
        query.setCacheable(true);
        query.setCacheRegion("entsorgerliste");
        list = query.list();

        AnhEntsorger[] tmp = new AnhEntsorger[list.size()];
        tmp = (AnhEntsorger[]) list.toArray(tmp);

        return tmp;
    }

    /**
     * Liefert alle vorhandenen Entsorger.
     * öffnet eine neue Hibernate-Session und schließt sie wieder.
     * @return Alle vorhandenen Entsorger
     */

    public static AnhEntsorger[] getEntsorg() {
        AnhEntsorger[] tmp;

        try {
        Session session = HibernateSessionFactory.currentSession();
        tmp = getEntsorg(session);
        } catch (HibernateException e) {
            tmp = null;
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return tmp;
    }

    public static AnhEntsorger saveEntsorger(AnhEntsorger ents) {
        AnhEntsorger entsRet;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            entsRet = (AnhEntsorger) session.merge(ents);
            tx.commit();
        } catch (HibernateException e) {
            entsRet = null;
            // Falls während der Änderungen ein Hibernate Fehler auftritt
            if (tx != null) {
                // Alle Änderungen rückgängig machen
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    e1.printStackTrace();
                    AUIKataster.handleDBException(e1, "EntsorgerEditor.doSpeichern", false);
                }
            }
        } finally {
            // Am Ende (egal ob erfolgreich oder nicht) die Session schließen
            HibernateSessionFactory.closeSession();
        }

        return entsRet;
    }
}
