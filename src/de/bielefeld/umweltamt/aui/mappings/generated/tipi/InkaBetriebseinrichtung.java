package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class InkaBetriebseinrichtung.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaBetriebseinrichtung
 * @author Hibernate Tools
 */
public class InkaBetriebseinrichtung extends AbstractInkaBetriebseinrichtung {

    private static final Log log = LogFactory.getLog(InkaBetriebseinrichtung.class);

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
    
    public void persist(InkaBetriebseinrichtung transientInstance) {
        log.debug("persisting InkaBetriebseinrichtung instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(InkaBetriebseinrichtung instance) {
        log.debug("attaching dirty InkaBetriebseinrichtung instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(InkaBetriebseinrichtung instance) {
        log.debug("attaching clean InkaBetriebseinrichtung instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(InkaBetriebseinrichtung persistentInstance) {
        log.debug("deleting InkaBetriebseinrichtung instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public InkaBetriebseinrichtung merge(InkaBetriebseinrichtung detachedInstance) {
        log.debug("merging InkaBetriebseinrichtung instance");
        try {
            InkaBetriebseinrichtung result = (InkaBetriebseinrichtung) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public InkaBetriebseinrichtung findById( java.lang.Integer id) {
        log.debug("getting InkaBetriebseinrichtung instance with id: " + id);
        try {
            InkaBetriebseinrichtung instance = (InkaBetriebseinrichtung) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.InkaBetriebseinrichtung", id);
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

