package de.bielefeld.umweltamt.aui.mappings.generated.view;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ViewSiehautqbProbenahmen.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.view.ViewSiehautqbProbenahmen
 * @author Hibernate Tools
 */
public class ViewSiehautqbProbenahmen extends AbstractViewSiehautqbProbenahmen {

    private static final Log log = LogFactory.getLog(ViewSiehautqbProbenahmen.class);

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
    
    public void persist(ViewSiehautqbProbenahmen transientInstance) {
        log.debug("persisting ViewSiehautqbProbenahmen instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ViewSiehautqbProbenahmen instance) {
        log.debug("attaching dirty ViewSiehautqbProbenahmen instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ViewSiehautqbProbenahmen instance) {
        log.debug("attaching clean ViewSiehautqbProbenahmen instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ViewSiehautqbProbenahmen persistentInstance) {
        log.debug("deleting ViewSiehautqbProbenahmen instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ViewSiehautqbProbenahmen merge(ViewSiehautqbProbenahmen detachedInstance) {
        log.debug("merging ViewSiehautqbProbenahmen instance");
        try {
            ViewSiehautqbProbenahmen result = (ViewSiehautqbProbenahmen) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ViewSiehautqbProbenahmen findById( de.bielefeld.umweltamt.aui.mappings.generated.view.ViewSiehautqbProbenahmenId id) {
        log.debug("getting ViewSiehautqbProbenahmen instance with id: " + id);
        try {
            ViewSiehautqbProbenahmen instance = (ViewSiehautqbProbenahmen) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.view.ViewSiehautqbProbenahmen", id);
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

