package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsStandortgghwsg.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsStandortgghwsg
 * @author Hibernate Tools
 */
public class VawsStandortgghwsg extends AbstractVawsStandortgghwsg {

    private static final Log log = LogFactory.getLog(VawsStandortgghwsg.class);

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
    
    public void persist(VawsStandortgghwsg transientInstance) {
        log.debug("persisting VawsStandortgghwsg instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsStandortgghwsg instance) {
        log.debug("attaching dirty VawsStandortgghwsg instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsStandortgghwsg instance) {
        log.debug("attaching clean VawsStandortgghwsg instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsStandortgghwsg persistentInstance) {
        log.debug("deleting VawsStandortgghwsg instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsStandortgghwsg merge(VawsStandortgghwsg detachedInstance) {
        log.debug("merging VawsStandortgghwsg instance");
        try {
            VawsStandortgghwsg result = (VawsStandortgghwsg) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsStandortgghwsg findById( int id) {
        log.debug("getting VawsStandortgghwsg instance with id: " + id);
        try {
            VawsStandortgghwsg instance = (VawsStandortgghwsg) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsStandortgghwsg", id);
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

