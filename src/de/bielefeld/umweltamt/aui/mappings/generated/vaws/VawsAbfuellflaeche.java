package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsAbfuellflaeche.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsAbfuellflaeche
 * @author Hibernate Tools
 */
public class VawsAbfuellflaeche extends AbstractVawsAbfuellflaeche {

    private static final Log log = LogFactory.getLog(VawsAbfuellflaeche.class);

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
    
    public void persist(VawsAbfuellflaeche transientInstance) {
        log.debug("persisting VawsAbfuellflaeche instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsAbfuellflaeche instance) {
        log.debug("attaching dirty VawsAbfuellflaeche instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsAbfuellflaeche instance) {
        log.debug("attaching clean VawsAbfuellflaeche instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsAbfuellflaeche persistentInstance) {
        log.debug("deleting VawsAbfuellflaeche instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsAbfuellflaeche merge(VawsAbfuellflaeche detachedInstance) {
        log.debug("merging VawsAbfuellflaeche instance");
        try {
            VawsAbfuellflaeche result = (VawsAbfuellflaeche) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsAbfuellflaeche findById( int id) {
        log.debug("getting VawsAbfuellflaeche instance with id: " + id);
        try {
            VawsAbfuellflaeche instance = (VawsAbfuellflaeche) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsAbfuellflaeche", id);
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

