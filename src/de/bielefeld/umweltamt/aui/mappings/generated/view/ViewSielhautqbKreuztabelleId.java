package de.bielefeld.umweltamt.aui.mappings.generated.view;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ViewSielhautqbKreuztabelleId.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.view.ViewSielhautqbKreuztabelleId
 * @author Hibernate Tools
 */
public class ViewSielhautqbKreuztabelleId extends AbstractViewSielhautqbKreuztabelleId {

    private static final Log log = LogFactory.getLog(ViewSielhautqbKreuztabelleId.class);

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
    
    public void persist(ViewSielhautqbKreuztabelleId transientInstance) {
        log.debug("persisting ViewSielhautqbKreuztabelleId instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ViewSielhautqbKreuztabelleId instance) {
        log.debug("attaching dirty ViewSielhautqbKreuztabelleId instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ViewSielhautqbKreuztabelleId instance) {
        log.debug("attaching clean ViewSielhautqbKreuztabelleId instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ViewSielhautqbKreuztabelleId persistentInstance) {
        log.debug("deleting ViewSielhautqbKreuztabelleId instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ViewSielhautqbKreuztabelleId merge(ViewSielhautqbKreuztabelleId detachedInstance) {
        log.debug("merging ViewSielhautqbKreuztabelleId instance");
        try {
            ViewSielhautqbKreuztabelleId result = (ViewSielhautqbKreuztabelleId) sessionFactory.getCurrentSession()
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

