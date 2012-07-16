package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Anh52Fachdaten.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh52Fachdaten
 * @author Hibernate Tools
 */
public class Anh52Fachdaten extends AbstractAnh52Fachdaten {

    private static final Log log = LogFactory.getLog(Anh52Fachdaten.class);

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
    
    public void persist(Anh52Fachdaten transientInstance) {
        log.debug("persisting Anh52Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Anh52Fachdaten instance) {
        log.debug("attaching dirty Anh52Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Anh52Fachdaten instance) {
        log.debug("attaching clean Anh52Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Anh52Fachdaten persistentInstance) {
        log.debug("deleting Anh52Fachdaten instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Anh52Fachdaten merge(Anh52Fachdaten detachedInstance) {
        log.debug("merging Anh52Fachdaten instance");
        try {
            Anh52Fachdaten result = (Anh52Fachdaten) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Anh52Fachdaten findById( int id) {
        log.debug("getting Anh52Fachdaten instance with id: " + id);
        try {
            Anh52Fachdaten instance = (Anh52Fachdaten) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh52Fachdaten", id);
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

