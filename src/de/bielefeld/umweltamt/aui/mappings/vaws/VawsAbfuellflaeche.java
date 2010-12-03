/*
 * Created Tue Sep 06 14:44:16 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_ABFUELLFLAECHE' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class VawsAbfuellflaeche
    extends AbstractVawsAbfuellflaeche
    implements Serializable
{
    // Für die Umwandlung von Bool'schen Werten zu Shorts
    private static short fW = 0;
    private static short tW = -1;

    /** Für die Umwandlung von Bool'schen Werten zu Shorts */
    public static Short FALSE = new Short(fW);
    /** Für die Umwandlung von Bool'schen Werten zu Shorts */
    public static Short TRUE = new Short(tW);

    /**
     * Simple constructor of VawsAbfuellflaeche instances.
     */
    public VawsAbfuellflaeche()
    {
    }

    /**
     * Constructor of VawsAbfuellflaeche instances given a simple primary key.
     * @param id
     */
    public VawsAbfuellflaeche(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */

    public String toString() {
        return "[VawsAbfuellflaeche: " + getBehaelterid() + ", FD:"+getVawsFachdaten()+"]";
    }

    // Boolean <-> Short:

    // TRUE und FALSE sind in dieser Klasse definierte
    // Short-Konstanten.

    // Der Grund, warum ich nicht einfach TRUE.equals(getXyz()) o.Ä.
    // zurück liefere ist, dass eigentlich nur festgelegt ist,
    // dass 0 == false ist. Deshalb ist alles ausser 0 und NULL
    // bei mir true.

    public static VawsAbfuellflaeche getAbfuellflaeche(VawsFachdaten fachdaten) {
        VawsAbfuellflaeche flaeche;
        List tmp;

        if (fachdaten == null || !fachdaten.getAnlagenart().equals("Abfüllfläche")) {
            throw new IllegalArgumentException("Fachdaten-Objekt ist keine Abfüllfläche!");
        }

        if (fachdaten.getBehaelterId() == null) {
            tmp = new ArrayList();
        } else {
            try {
                Session session = HibernateSessionFactory.currentSession();

                tmp = session.createQuery(
                        "from VawsAbfuellflaeche abff " +
                        "where abff.vawsFachdaten = ? ")
                        .setEntity(0, fachdaten)
                        .list();

            } catch (HibernateException e) {
                throw new RuntimeException("Datenbank-Fehler", e);
            } finally {
                HibernateSessionFactory.closeSession();
            }
        }

        if (tmp.size() > 0) {
            flaeche = (VawsAbfuellflaeche) tmp.get(0);
            AUIKataster.debugOutput("Fläche '" + flaeche + "' geladen!", "VawsAbfuellflaeche.getAbfuellflaeche()");
        } else {
            // Bei so ziemlich 95% aller Tankstellen gibts ein VawsFachdaten-
            // Objekt, aber kein VawsAbfuellflaechen-Objekt.
            // Seems like it's not a bug, it's a feature...

            // Also legen wir in diesen Füllen einfach ein neues
            // VawsAbfuellflaechen-Objekt an.

            // Das selbe tun wir bei einem noch ungespeicherten
            // neuen VawsFachdaten-Objekt.

            flaeche = new VawsAbfuellflaeche();
            flaeche.setVawsFachdaten(fachdaten);
            AUIKataster.debugOutput("Neue Fläche für '" + fachdaten + "' erzeugt!", "VawsAbfuellflaeche.getAbfuellflaeche()");
        }

        return flaeche;
    }

    /**
     * Speichert einen VAWS-Abfüllflächen-Datensatz in der Datenbank.
     * @param flaeche Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt, sonst <code>false</code>.
     */
    public static boolean saveAbfuellflaeche(VawsAbfuellflaeche flaeche) {
        boolean saved;

        if (flaeche.getVawsFachdaten() == null) {
            throw new IllegalArgumentException("Die VawsAbfuellflaeche muss einem VawsFachdaten-Objekt zugeordnet sein!");
        }

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(flaeche);
            tx.commit();
            saved = true;
        } catch (HibernateException e) {
            saved = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "VawsAbfuellflaeche.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    /**
     * Liefert alle Bodenflächen-Ausführungen.
     * <br><b>ACHTUNG:</b> Liefert nicht alle VawsAbfuellflaechen,
     * sondern alle in der Spalte "BODENFLAECHENAUSF" benutzten Werte!
     * @return Ein Array mit den Namen aller Ausführungen.
     */
    public static String[] getBodenflaechenausfArray() {
        //FIXME: select distinct nicht die beste Lösung
        List list;
        String suchString = "select distinct vabf.bodenflaechenausf " +
                "from VawsAbfuellflaeche vabf " +
                "order by vabf.bodenflaechenausf";
        String[] tmp;

        try {
            Session session = HibernateSessionFactory.currentSession();
            Query query = session.createQuery(suchString);
            query.setCacheable(true);
            query.setCacheRegion("vawsabausfliste");
            list = query.list();
            tmp = new String[list.size()];
            tmp = (String[]) list.toArray(tmp);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return tmp;
    }

    /**
     * Liefert alle Bodenflächen-Ausführungen.
     * <br><b>ACHTUNG:</b> Liefert nicht alle VawsAbfuellflaechen,
     * sondern alle in der Spalte "BODENFLAECHENAUSF" benutzten Werte!
     * @return Ein Array mit den Namen aller Ausführungen.
     */
    public static String[] getNiederschlagschutzArray() {
        //FIXME: select distinct nicht die beste Lösung
        List list;
        String suchString = "select distinct vabf.niederschlagschutz " +
                "from VawsAbfuellflaeche vabf " +
                "order by vabf.niederschlagschutz";
        String[] tmp;

        try {
            Session session = HibernateSessionFactory.currentSession();
            Query query = session.createQuery(suchString);
            query.setCacheable(true);
            query.setCacheRegion("vawsabnieliste");
            list = query.list();
            tmp = new String[list.size()];
            tmp = (String[]) list.toArray(tmp);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return tmp;
    }
}
