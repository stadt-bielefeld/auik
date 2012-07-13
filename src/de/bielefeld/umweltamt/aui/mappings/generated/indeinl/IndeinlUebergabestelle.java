package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class IndeinlUebergabestelle.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.IndeinlUebergabestelle
 * @author Hibernate Tools
 */
public class IndeinlUebergabestelle extends AbstractIndeinlUebergabestelle {

    private static final Log log = LogFactory.getLog(IndeinlUebergabestelle.class);

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
    
    public void persist(IndeinlUebergabestelle transientInstance) {
        log.debug("persisting IndeinlUebergabestelle instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(IndeinlUebergabestelle instance) {
        log.debug("attaching dirty IndeinlUebergabestelle instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(IndeinlUebergabestelle instance) {
        log.debug("attaching clean IndeinlUebergabestelle instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(IndeinlUebergabestelle persistentInstance) {
        log.debug("deleting IndeinlUebergabestelle instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public IndeinlUebergabestelle merge(IndeinlUebergabestelle detachedInstance) {
        log.debug("merging IndeinlUebergabestelle instance");
        try {
            IndeinlUebergabestelle result = (IndeinlUebergabestelle) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public IndeinlUebergabestelle findById( int id) {
        log.debug("getting IndeinlUebergabestelle instance with id: " + id);
        try {
            IndeinlUebergabestelle instance = (IndeinlUebergabestelle) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.IndeinlUebergabestelle", id);
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

