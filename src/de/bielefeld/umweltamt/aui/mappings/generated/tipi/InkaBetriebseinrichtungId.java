package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class InkaBetriebseinrichtungId.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaBetriebseinrichtungId
 * @author Hibernate Tools
 */
public class InkaBetriebseinrichtungId extends AbstractInkaBetriebseinrichtungId {

    private static final Log log = LogFactory.getLog(InkaBetriebseinrichtungId.class);

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
    
    public void persist(InkaBetriebseinrichtungId transientInstance) {
        log.debug("persisting InkaBetriebseinrichtungId instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(InkaBetriebseinrichtungId instance) {
        log.debug("attaching dirty InkaBetriebseinrichtungId instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(InkaBetriebseinrichtungId instance) {
        log.debug("attaching clean InkaBetriebseinrichtungId instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(InkaBetriebseinrichtungId persistentInstance) {
        log.debug("deleting InkaBetriebseinrichtungId instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public InkaBetriebseinrichtungId merge(InkaBetriebseinrichtungId detachedInstance) {
        log.debug("merging InkaBetriebseinrichtungId instance");
        try {
            InkaBetriebseinrichtungId result = (InkaBetriebseinrichtungId) sessionFactory.getCurrentSession()
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

