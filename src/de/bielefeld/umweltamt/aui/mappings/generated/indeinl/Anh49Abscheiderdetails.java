package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Anh49Abscheiderdetails.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Abscheiderdetails
 * @author Hibernate Tools
 */
public class Anh49Abscheiderdetails extends AbstractAnh49Abscheiderdetails {

    private static final Log log = LogFactory.getLog(Anh49Abscheiderdetails.class);

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
    
    public void persist(Anh49Abscheiderdetails transientInstance) {
        log.debug("persisting Anh49Abscheiderdetails instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Anh49Abscheiderdetails instance) {
        log.debug("attaching dirty Anh49Abscheiderdetails instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Anh49Abscheiderdetails instance) {
        log.debug("attaching clean Anh49Abscheiderdetails instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Anh49Abscheiderdetails persistentInstance) {
        log.debug("deleting Anh49Abscheiderdetails instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Anh49Abscheiderdetails merge(Anh49Abscheiderdetails detachedInstance) {
        log.debug("merging Anh49Abscheiderdetails instance");
        try {
            Anh49Abscheiderdetails result = (Anh49Abscheiderdetails) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Anh49Abscheiderdetails findById( java.lang.Integer id) {
        log.debug("getting Anh49Abscheiderdetails instance with id: " + id);
        try {
            Anh49Abscheiderdetails instance = (Anh49Abscheiderdetails) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Abscheiderdetails", id);
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

