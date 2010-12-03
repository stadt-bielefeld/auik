/*
 * Created Wed Jan 19 10:44:46 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * Eine Klasse, die eine Zeile der 'BASIS_BETREIBER'-Tabelle
 * repräsentiert.
 */
public class BasisBetreiber
    extends AbstractBasisBetreiber
    implements Serializable
{
    /** Durchsucht die Tabelle nach dem Betreiber-Namen */
    public static final String PROPERTY_NAME = "name";
    /** Durchsucht die Tabelle nach der Betreiber-Anrede */
    public static final String PROPERTY_ANREDE = "anrede";
    /** Durchsucht die Tabelle nach dem Betreiber-Namenszusatz */
    public static final String PROPERTY_ZUSATZ = "zusatz";

    /**
     * Simple constructor of BasisBetreiber instances.
     */
    public BasisBetreiber()
    {
    }

    /**
     * Constructor of BasisBetreiber instances given a simple primary key.
     * @param betreiberid
     */
    public BasisBetreiber(java.lang.Integer betreiberid)
    {
        super(betreiberid);
    }

    /* Add customized code below */

    /**
     * Liefert einen String der Form "Name, Zusatz" falls ein Zusatz
     * vorhanden ist, sonst nur den Namen.
     */
    public String toString() {
        String name = this.getBetrname();
        String zusatz = "";
        if (this.getBetrnamezus() != null) {
            zusatz = ", " + this.getBetrnamezus();
        }
        return name + zusatz;
    }

    /**
     * Liefert einen Betreiber mit einer bestimmten ID.
     * @param id Die ID (der Primärschlüssel) des Betreibers.
     * @return Den gesuchten Betreiber oder <code>null</code>,
     * falls kein Betreiber mit dieser ID existiert.
     */
    public static BasisBetreiber getBetreiber(Integer id) {
        BasisBetreiber betreiber;
        try {
            Session session = HibernateSessionFactory.currentSession();
            betreiber = (BasisBetreiber) session.get(BasisBetreiber.class, id);
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            betreiber = null;
        }

        return betreiber;
    }

    /**
     * Durchsucht die Betreiber-Tabelle. Mit <code>property</code> wird
     * festgelegt, welche Eigenschaft (im Endeffekt also welche
     * Tabellen-Spalte) der Betreiber nach dem Suchwort durchsucht wird.
     * Wenn <code>property</code> <code>null</code> ist, werden alle drei
     * möglichen Spalten (Name, Anrede und Namens-Zusatz) durchsucht.
     * Beim Suchwort wird Groß-/Kleinschreibung ignoriert und automatisch ein
     * '%' angehängt.
     * @param suche Wonach soll gesucht werden?
     * @param property PROPERTY_NAME, PROPERTY_ANREDE, PROPERTY_ZUSATZ oder <code>null</code> um in allen dreien zu suchen.
     * @return Eine Liste mit allen gefundenen Betreibern.
     */
    public static List findBetreiber(String suche, String property) {
        String suche2 = suche.toLowerCase().trim() + "%";
        AUIKataster.debugOutput("Suche nach '" + suche2 + "' (" + property + ").", "BasisBetreiber.findBetreiber");

        String queryString;
        if(PROPERTY_NAME.equals(property)) {
            queryString =
                "from BasisBetreiber as betr where " +
                "lower(betr.betrname) like ?" +
                "order by betr.betrname, betr.betrnamezus";
        } else if (PROPERTY_ANREDE.equals(property)) {
            queryString =
                "from BasisBetreiber as betr where " +
                "lower(betr.betranrede) like ?" +
                "order by betr.betrname, betr.betrnamezus";
        } else if (PROPERTY_ZUSATZ.equals(property)) {
            queryString =
                "from BasisBetreiber as betr where " +
                "lower(betr.betrnamezus) like ?" +
                "order by betr.betrname, betr.betrnamezus";
        } else {
            queryString =
                "from BasisBetreiber as betr where " +
                "lower(betr.betrname) like ?" +
                "or lower(betr.betranrede) like ?" +
                "or lower(betr.betrnamezus) like ?" +
                "order by betr.betrname, betr.betrnamezus";
        }

        List betreiber;
        try {
            Session session = HibernateSessionFactory.currentSession();
            if (property != null) {
                betreiber = session.createQuery(
                        queryString)
                        .setString(0, suche2)
                        .list();

            } else {
                betreiber = session.createQuery(
                        queryString)
                        .setString(0, suche2)
                        .setString(1, suche2)
                        .setString(2, suche2)
                        .list();
            }
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return betreiber;
    }

    /**
     * Speichert einen Betreiber in die Datenbank, bzw. updatet einen
     * schon vorhandenen Betreiber mit neuen Werten.
     * @param betr Der Betreiber, der gespeichert werden soll.
     * @return Der gespeicherte Betreiber, oder <code>null</code>, falls beim Speichern ein Fehler auftrat.
     */
    public static BasisBetreiber saveBetreiber(BasisBetreiber betr) {
        BasisBetreiber betrRet;

        Transaction tx = null;
        try {
            // Neue Session beginnen
            Session session = HibernateSessionFactory.currentSession();

            // Alle Änderungen in einer Transaktion zusammenfassen
            tx = session.beginTransaction();

            // Speichern / Transaktion durchführen
            betrRet = (BasisBetreiber) session.merge(betr);
            tx.commit();

            AUIKataster.debugOutput("Neuer Betr "+ betr +" gespeichert!", "BasisStandort.saveStandort");
        } catch (HibernateException e) {
            betrRet = null;
            e.printStackTrace();
            // Falls während der Änderungen ein Hibernate Fehler auftritt
            if (tx != null) {
                try {
                    // Alle Änderungen rückgängig machen
                    tx.rollback();
                } catch (HibernateException e1) {
                    throw new RuntimeException("Datenbank-Fehler (BasisBetreiber)", e);
                }
            }
        } finally {
            // Am Ende (egal ob erfolgreich oder nicht) die Session schließen
            HibernateSessionFactory.closeSession();
        }

        return betrRet;
    }

    /**
     * Löscht einen vorhandenen Betreiber aus der Datenbank.
     * @param betreiber Der Betreiber, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Betreiber gelöscht wurde oder
     * <code>false</code> falls dabei ein Fehler auftrat (z.B. der Betreiber
     * nicht in der Datenbank existiert).
     */
    public static boolean removeBetreiber(BasisBetreiber betreiber) {
        boolean removed;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(betreiber);
            tx.commit();
            removed = true;
        } catch (HibernateException e) {
            removed = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "BasisBetreiber.removeBetreiber", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return removed;
    }
}
