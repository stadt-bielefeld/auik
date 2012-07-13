package de.bielefeld.umweltamt.aui.mappings.generated.basis;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class BasisObjektverknuepfung.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjektverknuepfung
 * @author Hibernate Tools
 */
public class BasisObjektverknuepfung extends AbstractBasisObjektverknuepfung {

    private static final Log log = LogFactory.getLog(BasisObjektverknuepfung.class);

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
    
    public void persist(BasisObjektverknuepfung transientInstance) {
        log.debug("persisting BasisObjektverknuepfung instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(BasisObjektverknuepfung instance) {
        log.debug("attaching dirty BasisObjektverknuepfung instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(BasisObjektverknuepfung instance) {
        log.debug("attaching clean BasisObjektverknuepfung instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(BasisObjektverknuepfung persistentInstance) {
        log.debug("deleting BasisObjektverknuepfung instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public BasisObjektverknuepfung merge(BasisObjektverknuepfung detachedInstance) {
        log.debug("merging BasisObjektverknuepfung instance");
        try {
            BasisObjektverknuepfung result = (BasisObjektverknuepfung) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public BasisObjektverknuepfung findById( int id) {
        log.debug("getting BasisObjektverknuepfung instance with id: " + id);
        try {
            BasisObjektverknuepfung instance = (BasisObjektverknuepfung) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisObjektverknuepfung", id);
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

