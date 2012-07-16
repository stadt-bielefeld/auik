package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class InkaUebergabestelle.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaUebergabestelle
 * @author Hibernate Tools
 */
public class InkaUebergabestelle extends AbstractInkaUebergabestelle {

    private static final Log log = LogFactory.getLog(InkaUebergabestelle.class);

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
    
    public void persist(InkaUebergabestelle transientInstance) {
        log.debug("persisting InkaUebergabestelle instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(InkaUebergabestelle instance) {
        log.debug("attaching dirty InkaUebergabestelle instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(InkaUebergabestelle instance) {
        log.debug("attaching clean InkaUebergabestelle instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(InkaUebergabestelle persistentInstance) {
        log.debug("deleting InkaUebergabestelle instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public InkaUebergabestelle merge(InkaUebergabestelle detachedInstance) {
        log.debug("merging InkaUebergabestelle instance");
        try {
            InkaUebergabestelle result = (InkaUebergabestelle) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public InkaUebergabestelle findById( java.lang.Integer id) {
        log.debug("getting InkaUebergabestelle instance with id: " + id);
        try {
            InkaUebergabestelle instance = (InkaUebergabestelle) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaUebergabestelle", id);
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

