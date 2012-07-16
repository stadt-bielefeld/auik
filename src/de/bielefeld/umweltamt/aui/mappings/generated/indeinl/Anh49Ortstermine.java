package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Anh49Ortstermine.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Ortstermine
 * @author Hibernate Tools
 */
public class Anh49Ortstermine extends AbstractAnh49Ortstermine {

    private static final Log log = LogFactory.getLog(Anh49Ortstermine.class);

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
    
    public void persist(Anh49Ortstermine transientInstance) {
        log.debug("persisting Anh49Ortstermine instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Anh49Ortstermine instance) {
        log.debug("attaching dirty Anh49Ortstermine instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Anh49Ortstermine instance) {
        log.debug("attaching clean Anh49Ortstermine instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Anh49Ortstermine persistentInstance) {
        log.debug("deleting Anh49Ortstermine instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Anh49Ortstermine merge(Anh49Ortstermine detachedInstance) {
        log.debug("merging Anh49Ortstermine instance");
        try {
            Anh49Ortstermine result = (Anh49Ortstermine) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Anh49Ortstermine findById( java.lang.Integer id) {
        log.debug("getting Anh49Ortstermine instance with id: " + id);
        try {
            Anh49Ortstermine instance = (Anh49Ortstermine) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Ortstermine", id);
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

