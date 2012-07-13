package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Anh49Verwaltungsverf.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Verwaltungsverf
 * @author Hibernate Tools
 */
public class Anh49Verwaltungsverf extends AbstractAnh49Verwaltungsverf {

    private static final Log log = LogFactory.getLog(Anh49Verwaltungsverf.class);

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
    
    public void persist(Anh49Verwaltungsverf transientInstance) {
        log.debug("persisting Anh49Verwaltungsverf instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Anh49Verwaltungsverf instance) {
        log.debug("attaching dirty Anh49Verwaltungsverf instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Anh49Verwaltungsverf instance) {
        log.debug("attaching clean Anh49Verwaltungsverf instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Anh49Verwaltungsverf persistentInstance) {
        log.debug("deleting Anh49Verwaltungsverf instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Anh49Verwaltungsverf merge(Anh49Verwaltungsverf detachedInstance) {
        log.debug("merging Anh49Verwaltungsverf instance");
        try {
            Anh49Verwaltungsverf result = (Anh49Verwaltungsverf) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Anh49Verwaltungsverf findById( int id) {
        log.debug("getting Anh49Verwaltungsverf instance with id: " + id);
        try {
            Anh49Verwaltungsverf instance = (Anh49Verwaltungsverf) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Verwaltungsverf", id);
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

