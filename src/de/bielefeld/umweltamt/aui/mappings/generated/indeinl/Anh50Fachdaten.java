package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Anh50Fachdaten.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh50Fachdaten
 * @author Hibernate Tools
 */
public class Anh50Fachdaten extends AbstractAnh50Fachdaten {

    private static final Log log = LogFactory.getLog(Anh50Fachdaten.class);

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
    
    public void persist(Anh50Fachdaten transientInstance) {
        log.debug("persisting Anh50Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Anh50Fachdaten instance) {
        log.debug("attaching dirty Anh50Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Anh50Fachdaten instance) {
        log.debug("attaching clean Anh50Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Anh50Fachdaten persistentInstance) {
        log.debug("deleting Anh50Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Anh50Fachdaten merge(Anh50Fachdaten detachedInstance) {
        log.debug("merging Anh50Fachdaten instance");
        try {
            Anh50Fachdaten result = (Anh50Fachdaten) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Anh50Fachdaten findById( int id) {
        log.debug("getting Anh50Fachdaten instance with id: " + id);
        try {
            Anh50Fachdaten instance = (Anh50Fachdaten) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh50Fachdaten", id);
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

