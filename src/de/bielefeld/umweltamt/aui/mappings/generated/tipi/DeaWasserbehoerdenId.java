package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaWasserbehoerdenId.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaWasserbehoerdenId
 * @author Hibernate Tools
 */
public class DeaWasserbehoerdenId extends AbstractDeaWasserbehoerdenId {

    private static final Log log = LogFactory.getLog(DeaWasserbehoerdenId.class);

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
    
    public void persist(DeaWasserbehoerdenId transientInstance) {
        log.debug("persisting DeaWasserbehoerdenId instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaWasserbehoerdenId instance) {
        log.debug("attaching dirty DeaWasserbehoerdenId instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaWasserbehoerdenId instance) {
        log.debug("attaching clean DeaWasserbehoerdenId instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaWasserbehoerdenId persistentInstance) {
        log.debug("deleting DeaWasserbehoerdenId instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaWasserbehoerdenId merge(DeaWasserbehoerdenId detachedInstance) {
        log.debug("merging DeaWasserbehoerdenId instance");
        try {
            DeaWasserbehoerdenId result = (DeaWasserbehoerdenId) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    
}

