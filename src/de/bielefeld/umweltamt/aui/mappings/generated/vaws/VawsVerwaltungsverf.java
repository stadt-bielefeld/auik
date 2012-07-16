package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsVerwaltungsverf.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsVerwaltungsverf
 * @author Hibernate Tools
 */
public class VawsVerwaltungsverf extends AbstractVawsVerwaltungsverf {

    private static final Log log = LogFactory.getLog(VawsVerwaltungsverf.class);

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
    
    public void persist(VawsVerwaltungsverf transientInstance) {
        log.debug("persisting VawsVerwaltungsverf instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsVerwaltungsverf instance) {
        log.debug("attaching dirty VawsVerwaltungsverf instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsVerwaltungsverf instance) {
        log.debug("attaching clean VawsVerwaltungsverf instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsVerwaltungsverf persistentInstance) {
        log.debug("deleting VawsVerwaltungsverf instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsVerwaltungsverf merge(VawsVerwaltungsverf detachedInstance) {
        log.debug("merging VawsVerwaltungsverf instance");
        try {
            VawsVerwaltungsverf result = (VawsVerwaltungsverf) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsVerwaltungsverf findById( java.lang.Integer id) {
        log.debug("getting VawsVerwaltungsverf instance with id: " + id);
        try {
            VawsVerwaltungsverf instance = (VawsVerwaltungsverf) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsVerwaltungsverf", id);
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

