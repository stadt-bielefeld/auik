package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsVerwmassnahmen.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsVerwmassnahmen
 * @author Hibernate Tools
 */
public class VawsVerwmassnahmen extends AbstractVawsVerwmassnahmen {

    private static final Log log = LogFactory.getLog(VawsVerwmassnahmen.class);

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
    
    public void persist(VawsVerwmassnahmen transientInstance) {
        log.debug("persisting VawsVerwmassnahmen instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsVerwmassnahmen instance) {
        log.debug("attaching dirty VawsVerwmassnahmen instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsVerwmassnahmen instance) {
        log.debug("attaching clean VawsVerwmassnahmen instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsVerwmassnahmen persistentInstance) {
        log.debug("deleting VawsVerwmassnahmen instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsVerwmassnahmen merge(VawsVerwmassnahmen detachedInstance) {
        log.debug("merging VawsVerwmassnahmen instance");
        try {
            VawsVerwmassnahmen result = (VawsVerwmassnahmen) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsVerwmassnahmen findById( int id) {
        log.debug("getting VawsVerwmassnahmen instance with id: " + id);
        try {
            VawsVerwmassnahmen instance = (VawsVerwmassnahmen) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsVerwmassnahmen", id);
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

