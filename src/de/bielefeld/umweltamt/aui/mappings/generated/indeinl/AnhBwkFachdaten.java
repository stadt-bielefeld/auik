package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class AnhBwkFachdaten.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.AnhBwkFachdaten
 * @author Hibernate Tools
 */
public class AnhBwkFachdaten extends AbstractAnhBwkFachdaten {

    private static final Log log = LogFactory.getLog(AnhBwkFachdaten.class);

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
    
    public void persist(AnhBwkFachdaten transientInstance) {
        log.debug("persisting AnhBwkFachdaten instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(AnhBwkFachdaten instance) {
        log.debug("attaching dirty AnhBwkFachdaten instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AnhBwkFachdaten instance) {
        log.debug("attaching clean AnhBwkFachdaten instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(AnhBwkFachdaten persistentInstance) {
        log.debug("deleting AnhBwkFachdaten instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AnhBwkFachdaten merge(AnhBwkFachdaten detachedInstance) {
        log.debug("merging AnhBwkFachdaten instance");
        try {
            AnhBwkFachdaten result = (AnhBwkFachdaten) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public AnhBwkFachdaten findById( int id) {
        log.debug("getting AnhBwkFachdaten instance with id: " + id);
        try {
            AnhBwkFachdaten instance = (AnhBwkFachdaten) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.AnhBwkFachdaten", id);
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

