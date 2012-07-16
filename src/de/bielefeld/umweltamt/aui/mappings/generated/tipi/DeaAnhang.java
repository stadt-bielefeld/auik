package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaAnhang.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaAnhang
 * @author Hibernate Tools
 */
public class DeaAnhang extends AbstractDeaAnhang {

    private static final Log log = LogFactory.getLog(DeaAnhang.class);

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
    
    public void persist(DeaAnhang transientInstance) {
        log.debug("persisting DeaAnhang instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaAnhang instance) {
        log.debug("attaching dirty DeaAnhang instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaAnhang instance) {
        log.debug("attaching clean DeaAnhang instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaAnhang persistentInstance) {
        log.debug("deleting DeaAnhang instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaAnhang merge(DeaAnhang detachedInstance) {
        log.debug("merging DeaAnhang instance");
        try {
            DeaAnhang result = (DeaAnhang) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DeaAnhang findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaAnhangId id) {
        log.debug("getting DeaAnhang instance with id: " + id);
        try {
            DeaAnhang instance = (DeaAnhang) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaAnhang", id);
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

