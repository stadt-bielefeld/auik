package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class AnhEntsorger.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.AnhEntsorger
 * @author Hibernate Tools
 */
public class AnhEntsorger extends AbstractAnhEntsorger {

    private static final Log log = LogFactory.getLog(AnhEntsorger.class);

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
    
    public void persist(AnhEntsorger transientInstance) {
        log.debug("persisting AnhEntsorger instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(AnhEntsorger instance) {
        log.debug("attaching dirty AnhEntsorger instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AnhEntsorger instance) {
        log.debug("attaching clean AnhEntsorger instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(AnhEntsorger persistentInstance) {
        log.debug("deleting AnhEntsorger instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AnhEntsorger merge(AnhEntsorger detachedInstance) {
        log.debug("merging AnhEntsorger instance");
        try {
            AnhEntsorger result = (AnhEntsorger) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public AnhEntsorger findById( int id) {
        log.debug("getting AnhEntsorger instance with id: " + id);
        try {
            AnhEntsorger instance = (AnhEntsorger) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.AnhEntsorger", id);
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

