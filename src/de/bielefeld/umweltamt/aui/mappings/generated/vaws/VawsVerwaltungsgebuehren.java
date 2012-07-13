package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsVerwaltungsgebuehren.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsVerwaltungsgebuehren
 * @author Hibernate Tools
 */
public class VawsVerwaltungsgebuehren extends AbstractVawsVerwaltungsgebuehren {

    private static final Log log = LogFactory.getLog(VawsVerwaltungsgebuehren.class);

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
    
    public void persist(VawsVerwaltungsgebuehren transientInstance) {
        log.debug("persisting VawsVerwaltungsgebuehren instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsVerwaltungsgebuehren instance) {
        log.debug("attaching dirty VawsVerwaltungsgebuehren instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsVerwaltungsgebuehren instance) {
        log.debug("attaching clean VawsVerwaltungsgebuehren instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsVerwaltungsgebuehren persistentInstance) {
        log.debug("deleting VawsVerwaltungsgebuehren instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsVerwaltungsgebuehren merge(VawsVerwaltungsgebuehren detachedInstance) {
        log.debug("merging VawsVerwaltungsgebuehren instance");
        try {
            VawsVerwaltungsgebuehren result = (VawsVerwaltungsgebuehren) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsVerwaltungsgebuehren findById( int id) {
        log.debug("getting VawsVerwaltungsgebuehren instance with id: " + id);
        try {
            VawsVerwaltungsgebuehren instance = (VawsVerwaltungsgebuehren) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsVerwaltungsgebuehren", id);
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

