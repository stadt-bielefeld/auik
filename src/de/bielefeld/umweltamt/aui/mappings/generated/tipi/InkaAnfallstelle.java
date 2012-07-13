package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class InkaAnfallstelle.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaAnfallstelle
 * @author Hibernate Tools
 */
public class InkaAnfallstelle extends AbstractInkaAnfallstelle {

    private static final Log log = LogFactory.getLog(InkaAnfallstelle.class);

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
    
    public void persist(InkaAnfallstelle transientInstance) {
        log.debug("persisting InkaAnfallstelle instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(InkaAnfallstelle instance) {
        log.debug("attaching dirty InkaAnfallstelle instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(InkaAnfallstelle instance) {
        log.debug("attaching clean InkaAnfallstelle instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(InkaAnfallstelle persistentInstance) {
        log.debug("deleting InkaAnfallstelle instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public InkaAnfallstelle merge(InkaAnfallstelle detachedInstance) {
        log.debug("merging InkaAnfallstelle instance");
        try {
            InkaAnfallstelle result = (InkaAnfallstelle) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public InkaAnfallstelle findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaAnfallstelleId id) {
        log.debug("getting InkaAnfallstelle instance with id: " + id);
        try {
            InkaAnfallstelle instance = (InkaAnfallstelle) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaAnfallstelle", id);
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

