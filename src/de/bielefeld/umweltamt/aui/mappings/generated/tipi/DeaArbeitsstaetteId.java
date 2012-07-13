package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaArbeitsstaetteId.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaArbeitsstaetteId
 * @author Hibernate Tools
 */
public class DeaArbeitsstaetteId extends AbstractDeaArbeitsstaetteId {

    private static final Log log = LogFactory.getLog(DeaArbeitsstaetteId.class);

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
    
    public void persist(DeaArbeitsstaetteId transientInstance) {
        log.debug("persisting DeaArbeitsstaetteId instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaArbeitsstaetteId instance) {
        log.debug("attaching dirty DeaArbeitsstaetteId instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaArbeitsstaetteId instance) {
        log.debug("attaching clean DeaArbeitsstaetteId instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaArbeitsstaetteId persistentInstance) {
        log.debug("deleting DeaArbeitsstaetteId instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaArbeitsstaetteId merge(DeaArbeitsstaetteId detachedInstance) {
        log.debug("merging DeaArbeitsstaetteId instance");
        try {
            DeaArbeitsstaetteId result = (DeaArbeitsstaetteId) sessionFactory.getCurrentSession()
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

