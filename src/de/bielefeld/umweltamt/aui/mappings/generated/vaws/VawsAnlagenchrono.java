package de.bielefeld.umweltamt.aui.mappings.generated.vaws;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class VawsAnlagenchrono.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsAnlagenchrono
 * @author Hibernate Tools
 */
public class VawsAnlagenchrono extends AbstractVawsAnlagenchrono {

    private static final Log log = LogFactory.getLog(VawsAnlagenchrono.class);

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
    
    public void persist(VawsAnlagenchrono transientInstance) {
        log.debug("persisting VawsAnlagenchrono instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(VawsAnlagenchrono instance) {
        log.debug("attaching dirty VawsAnlagenchrono instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VawsAnlagenchrono instance) {
        log.debug("attaching clean VawsAnlagenchrono instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(VawsAnlagenchrono persistentInstance) {
        log.debug("deleting VawsAnlagenchrono instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VawsAnlagenchrono merge(VawsAnlagenchrono detachedInstance) {
        log.debug("merging VawsAnlagenchrono instance");
        try {
            VawsAnlagenchrono result = (VawsAnlagenchrono) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public VawsAnlagenchrono findById( java.lang.Integer id) {
        log.debug("getting VawsAnlagenchrono instance with id: " + id);
        try {
            VawsAnlagenchrono instance = (VawsAnlagenchrono) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.vaws.VawsAnlagenchrono", id);
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

