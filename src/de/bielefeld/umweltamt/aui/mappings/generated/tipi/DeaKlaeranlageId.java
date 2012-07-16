package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaKlaeranlageId.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaKlaeranlageId
 * @author Hibernate Tools
 */
public class DeaKlaeranlageId extends AbstractDeaKlaeranlageId {

    private static final Log log = LogFactory.getLog(DeaKlaeranlageId.class);

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
    
    public void persist(DeaKlaeranlageId transientInstance) {
        log.debug("persisting DeaKlaeranlageId instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaKlaeranlageId instance) {
        log.debug("attaching dirty DeaKlaeranlageId instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaKlaeranlageId instance) {
        log.debug("attaching clean DeaKlaeranlageId instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaKlaeranlageId persistentInstance) {
        log.debug("deleting DeaKlaeranlageId instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaKlaeranlageId merge(DeaKlaeranlageId detachedInstance) {
        log.debug("merging DeaKlaeranlageId instance");
        try {
            DeaKlaeranlageId result = (DeaKlaeranlageId) sessionFactory.getCurrentSession()
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

