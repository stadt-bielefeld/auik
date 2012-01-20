/**
 * Copyright 2005-2011, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */

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
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the 'VAWS_Abscheider' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class VawsAbscheider
    extends AbstractVawsAbscheider
    implements Serializable
{
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of VawsAbscheider instances.
     */
    public VawsAbscheider()
    {
    }

    /**
     * Constructor of VawsAbscheider instances given a simple primary key.
     * @param id
     */
    public VawsAbscheider(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */

    public String toString() {
        return "[VawsAbscheider: " + getBehaelterid() + ", FD:"+getVawsFachdaten()+"]";
    }

    // Statischer Teil:

    public static VawsAbscheider getAbscheider(VawsFachdaten fachdaten) {
        VawsAbscheider abscheider;
        List tmp;

        if (fachdaten == null || !fachdaten.getAnlagenart().equals("VAwS-Abscheider")) {
            throw new IllegalArgumentException("Fachdaten-Objekt ist kein VAwS-Abscheider!");
        }

        if (fachdaten.getBehaelterId() == null) {
            tmp = new ArrayList();
        } else {
            try {
                Session session = HibernateSessionFactory.currentSession();

                tmp = session.createQuery(
                        "from VawsAbscheider abff " +
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
            abscheider = (VawsAbscheider) tmp.get(0);
            log.debug("Fläche '" + abscheider + "' geladen!");
        } else {
            // Bei so ziemlich 95% aller Tankstellen gibts ein VawsFachdaten-
            // Objekt, aber kein VawsAbscheidern-Objekt.
            // Seems like it's not a bug, it's a feature...

            // Also legen wir in diesen Füllen einfach ein neues
            // VawsAbscheidern-Objekt an.

            // Das selbe tun wir bei einem noch ungespeicherten
            // neuen VawsFachdaten-Objekt.

            abscheider = new VawsAbscheider();
            abscheider.setVawsFachdaten(fachdaten);
            log.debug("Neuer Abscheider für '" + fachdaten + "' erzeugt!");
        }

        return abscheider;
    }

    /**
     * Speichert einen VAWS-Abfüllflächen-Datensatz in der Datenbank.
     * @param flaeche Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt, sonst <code>false</code>.
     */
    public static boolean saveAbscheider(VawsAbscheider abscheider) {
        boolean saved;

        if (abscheider.getVawsFachdaten() == null) {
            throw new IllegalArgumentException("Die VawsAbscheider muss einem VawsFachdaten-Objekt zugeordnet sein!");
        }

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(abscheider);
            tx.commit();
            saved = true;
        } catch (HibernateException e) {
            saved = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "VawsAbscheider.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

//    /**
//     * Liefert alle Bodenflächen-Ausführungen.
//     * <br><b>ACHTUNG:</b> Liefert nicht alle VawsAbscheidern,
//     * sondern alle in der Spalte "BODENFLAECHENAUSF" benutzten Werte!
//     * @return Ein Array mit den Namen aller Ausführungen.
//     */
//    public static String[] getBodenflaechenausfArray() {
//        //FIXME: select distinct nicht die beste Lösung
//        List list;
//        String suchString = "select distinct vabf.bodenflaechenausf " +
//                "from VawsAbscheider vabf " +
//                "order by vabf.bodenflaechenausf";
//        String[] tmp;
//
//        try {
//            Session session = HibernateSessionFactory.currentSession();
//            Query query = session.createQuery(suchString);
//            query.setCacheable(true);
//            query.setCacheRegion("vawsabausfliste");
//            list = query.list();
//            tmp = new String[list.size()];
//            tmp = (String[]) list.toArray(tmp);
//        } catch (HibernateException e) {
//            throw new RuntimeException("Datenbank-Fehler", e);
//        } finally {
//            HibernateSessionFactory.closeSession();
//        }
//
//        return tmp;
//    }

//    /**
//     * Liefert alle Bodenflächen-Ausführungen.
//     * <br><b>ACHTUNG:</b> Liefert nicht alle VawsAbscheidern,
//     * sondern alle in der Spalte "BODENFLAECHENAUSF" benutzten Werte!
//     * @return Ein Array mit den Namen aller Ausführungen.
//     */
//    public static String[] getNiederschlagschutzArray() {
//        //FIXME: select distinct nicht die beste Lösung
//        List list;
//        String suchString = "select distinct vabf.niederschlagschutz " +
//                "from VawsAbscheider vabf " +
//                "order by vabf.niederschlagschutz";
//        String[] tmp;
//
//        try {
//            Session session = HibernateSessionFactory.currentSession();
//            Query query = session.createQuery(suchString);
//            query.setCacheable(true);
//            query.setCacheRegion("vawsabnieliste");
//            list = query.list();
//            tmp = new String[list.size()];
//            tmp = (String[]) list.toArray(tmp);
//        } catch (HibernateException e) {
//            throw new RuntimeException("Datenbank-Fehler", e);
//        } finally {
//            HibernateSessionFactory.closeSession();
//        }
//
//        return tmp;
//    }
}
