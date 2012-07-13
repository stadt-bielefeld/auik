package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Anh53Fachdaten.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh53Fachdaten
 * @author Hibernate Tools
 */
public class Anh53Fachdaten extends AbstractAnh53Fachdaten {

    private static final Log log = LogFactory.getLog(Anh53Fachdaten.class);

    private final SessionFactory sessionFactory = getSessionFactory();
    
    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(Anh53Fachdaten transientInstance) {
        log.debug("persisting Anh53Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Anh53Fachdaten instance) {
        log.debug("attaching dirty Anh53Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Anh53Fachdaten instance) {
        log.debug("attaching clean Anh53Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Anh53Fachdaten persistentInstance) {
        log.debug("deleting Anh53Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Anh53Fachdaten merge(Anh53Fachdaten detachedInstance) {
        log.debug("merging Anh53Fachdaten instance");
        try {
            Anh53Fachdaten result = (Anh53Fachdaten) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Anh53Fachdaten findById( int id) {
        log.debug("getting Anh53Fachdaten instance with id: " + id);
        try {
            Anh53Fachdaten instance = (Anh53Fachdaten) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh53Fachdaten", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
}

