package de.bielefeld.umweltamt.aui.mappings.generated.basis;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class BasisObjektarten.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjektarten
 * @author Hibernate Tools
 */
public class BasisObjektarten extends AbstractBasisObjektarten {

    private static final Log log = LogFactory.getLog(BasisObjektarten.class);

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
    
    public void persist(BasisObjektarten transientInstance) {
        log.debug("persisting BasisObjektarten instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(BasisObjektarten instance) {
        log.debug("attaching dirty BasisObjektarten instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(BasisObjektarten instance) {
        log.debug("attaching clean BasisObjektarten instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(BasisObjektarten persistentInstance) {
        log.debug("deleting BasisObjektarten instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public BasisObjektarten merge(BasisObjektarten detachedInstance) {
        log.debug("merging BasisObjektarten instance");
        try {
            BasisObjektarten result = (BasisObjektarten) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public BasisObjektarten findById( int id) {
        log.debug("getting BasisObjektarten instance with id: " + id);
        try {
            BasisObjektarten instance = (BasisObjektarten) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjektarten", id);
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

