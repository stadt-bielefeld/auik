package de.bielefeld.umweltamt.aui.mappings.generated.view;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ViewSielhautqbKreuztabelle.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.view.ViewSielhautqbKreuztabelle
 * @author Hibernate Tools
 */
public class ViewSielhautqbKreuztabelle extends AbstractViewSielhautqbKreuztabelle {

    private static final Log log = LogFactory.getLog(ViewSielhautqbKreuztabelle.class);

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
    
    public void persist(ViewSielhautqbKreuztabelle transientInstance) {
        log.debug("persisting ViewSielhautqbKreuztabelle instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ViewSielhautqbKreuztabelle instance) {
        log.debug("attaching dirty ViewSielhautqbKreuztabelle instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ViewSielhautqbKreuztabelle instance) {
        log.debug("attaching clean ViewSielhautqbKreuztabelle instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ViewSielhautqbKreuztabelle persistentInstance) {
        log.debug("deleting ViewSielhautqbKreuztabelle instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ViewSielhautqbKreuztabelle merge(ViewSielhautqbKreuztabelle detachedInstance) {
        log.debug("merging ViewSielhautqbKreuztabelle instance");
        try {
            ViewSielhautqbKreuztabelle result = (ViewSielhautqbKreuztabelle) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ViewSielhautqbKreuztabelle findById( de.bielefeld.umweltamt.aui.mappings.generated.view.ViewSielhautqbKreuztabelleId id) {
        log.debug("getting ViewSielhautqbKreuztabelle instance with id: " + id);
        try {
            ViewSielhautqbKreuztabelle instance = (ViewSielhautqbKreuztabelle) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.view.ViewSielhautqbKreuztabelle", id);
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

