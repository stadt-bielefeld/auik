package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Anh49Kontrollen.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Kontrollen
 * @author Hibernate Tools
 */
public class Anh49Kontrollen extends AbstractAnh49Kontrollen {

    private static final Log log = LogFactory.getLog(Anh49Kontrollen.class);

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
    
    public void persist(Anh49Kontrollen transientInstance) {
        log.debug("persisting Anh49Kontrollen instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Anh49Kontrollen instance) {
        log.debug("attaching dirty Anh49Kontrollen instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Anh49Kontrollen instance) {
        log.debug("attaching clean Anh49Kontrollen instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(Anh49Kontrollen persistentInstance) {
        log.debug("deleting Anh49Kontrollen instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Anh49Kontrollen merge(Anh49Kontrollen detachedInstance) {
        log.debug("merging Anh49Kontrollen instance");
        try {
            Anh49Kontrollen result = (Anh49Kontrollen) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Anh49Kontrollen findById( int id) {
        log.debug("getting Anh49Kontrollen instance with id: " + id);
        try {
            Anh49Kontrollen instance = (Anh49Kontrollen) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.Anh49Kontrollen", id);
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

