package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsKontrollen.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsKontrollen
 * @author Hibernate Tools
 */
public class VawsKontrollen extends AbstractVawsKontrollen {

    private static final Log log = LogFactory.getLog(VawsKontrollen.class);

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
    
    public void persist(VawsKontrollen transientInstance) {
        log.debug("persisting VawsKontrollen instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsKontrollen instance) {
        log.debug("attaching dirty VawsKontrollen instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsKontrollen instance) {
        log.debug("attaching clean VawsKontrollen instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsKontrollen persistentInstance) {
        log.debug("deleting VawsKontrollen instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsKontrollen merge(VawsKontrollen detachedInstance) {
        log.debug("merging VawsKontrollen instance");
        try {
            VawsKontrollen result = (VawsKontrollen) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsKontrollen findById( java.lang.Integer id) {
        log.debug("getting VawsKontrollen instance with id: " + id);
        try {
            VawsKontrollen instance = (VawsKontrollen) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsKontrollen", id);
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

