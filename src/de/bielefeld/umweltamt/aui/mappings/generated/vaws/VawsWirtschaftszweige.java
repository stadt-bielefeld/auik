package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsWirtschaftszweige.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsWirtschaftszweige
 * @author Hibernate Tools
 */
public class VawsWirtschaftszweige extends AbstractVawsWirtschaftszweige {

    private static final Log log = LogFactory.getLog(VawsWirtschaftszweige.class);

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
    
    public void persist(VawsWirtschaftszweige transientInstance) {
        log.debug("persisting VawsWirtschaftszweige instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsWirtschaftszweige instance) {
        log.debug("attaching dirty VawsWirtschaftszweige instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsWirtschaftszweige instance) {
        log.debug("attaching clean VawsWirtschaftszweige instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsWirtschaftszweige persistentInstance) {
        log.debug("deleting VawsWirtschaftszweige instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsWirtschaftszweige merge(VawsWirtschaftszweige detachedInstance) {
        log.debug("merging VawsWirtschaftszweige instance");
        try {
            VawsWirtschaftszweige result = (VawsWirtschaftszweige) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsWirtschaftszweige findById( int id) {
        log.debug("getting VawsWirtschaftszweige instance with id: " + id);
        try {
            VawsWirtschaftszweige instance = (VawsWirtschaftszweige) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsWirtschaftszweige", id);
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

