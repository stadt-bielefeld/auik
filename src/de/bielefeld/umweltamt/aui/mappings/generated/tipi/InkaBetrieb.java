package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class InkaBetrieb.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaBetrieb
 * @author Hibernate Tools
 */
public class InkaBetrieb extends AbstractInkaBetrieb {

    private static final Log log = LogFactory.getLog(InkaBetrieb.class);

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
    
    public void persist(InkaBetrieb transientInstance) {
        log.debug("persisting InkaBetrieb instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(InkaBetrieb instance) {
        log.debug("attaching dirty InkaBetrieb instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(InkaBetrieb instance) {
        log.debug("attaching clean InkaBetrieb instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(InkaBetrieb persistentInstance) {
        log.debug("deleting InkaBetrieb instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public InkaBetrieb merge(InkaBetrieb detachedInstance) {
        log.debug("merging InkaBetrieb instance");
        try {
            InkaBetrieb result = (InkaBetrieb) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public InkaBetrieb findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaBetriebId id) {
        log.debug("getting InkaBetrieb instance with id: " + id);
        try {
            InkaBetrieb instance = (InkaBetrieb) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaBetrieb", id);
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

