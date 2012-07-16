package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsGebuehrenarten.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsGebuehrenarten
 * @author Hibernate Tools
 */
public class VawsGebuehrenarten extends AbstractVawsGebuehrenarten {

    private static final Log log = LogFactory.getLog(VawsGebuehrenarten.class);

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
    
    public void persist(VawsGebuehrenarten transientInstance) {
        log.debug("persisting VawsGebuehrenarten instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsGebuehrenarten instance) {
        log.debug("attaching dirty VawsGebuehrenarten instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsGebuehrenarten instance) {
        log.debug("attaching clean VawsGebuehrenarten instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsGebuehrenarten persistentInstance) {
        log.debug("deleting VawsGebuehrenarten instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsGebuehrenarten merge(VawsGebuehrenarten detachedInstance) {
        log.debug("merging VawsGebuehrenarten instance");
        try {
            VawsGebuehrenarten result = (VawsGebuehrenarten) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsGebuehrenarten findById( int id) {
        log.debug("getting VawsGebuehrenarten instance with id: " + id);
        try {
            VawsGebuehrenarten instance = (VawsGebuehrenarten) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsGebuehrenarten", id);
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

