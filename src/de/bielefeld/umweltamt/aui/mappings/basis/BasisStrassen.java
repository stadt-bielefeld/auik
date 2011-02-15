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
 * Created Mon Jan 17 11:40:20 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'BASIS_STRASSEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class BasisStrassen
    extends AbstractBasisStrassen
    implements Serializable
{

    /**
     * Simple constructor of BasisStrassen instances.
     */
    public BasisStrassen()
    {
    }

    /**
     * Constructor of BasisStrassen instances given a simple primary key.
     * @param strasseid
     */
    public BasisStrassen(java.lang.Integer strasseid)
    {
        super(strasseid);
    }

    /* Add customized code below */

    /**
     * Liefert den Strassennamen.
     */
    public String toString() {
        return super.getStrasse();
    }

    // Nur nötig, weil wir Strassen so komisch handhaben...
    /**
     * Liefert das passende BasisStrassen-Objekt zu einem Strassennamen.
     */
    private static BasisStrassen getStrasseByName(String name, Session session) {
        BasisStrassen tmp = null;
        if (name != null) {
            String name2 = name.toLowerCase().trim() + "%";
            AUIKataster.debugOutput("Suche nach: " + name, "getStrasseByName");
            try {

                List list = session.createQuery(
                        "from BasisStrassen str where lower(str.strasse) like ?")
                        .setString(0, name2)
                        .list();

                tmp = (BasisStrassen) ((list.size() > 0) ? list.get(0) : null);
                AUIKataster.debugOutput("Ergebnis: " + tmp, "BasisStrassen.getStrasseByName");
            } catch (HibernateException e) {
                tmp = null;
                //AUIKataster.debugOutput("Strasse nicht gefunden!", "BasisStrassen.getStrasseByName");
                throw new RuntimeException("Datenbank-Fehler (BasisStrassen)", e);
            }
        }
        return tmp;
    }

    /**
     * Liefert das passende BasisStrassen-Objekt zu einem Strassennamen.
     * @return Das passende BasisStrassen-Objekt oder <code>null</code>, falls keins diesen Namens gefunden wird.
     */
    public static BasisStrassen getStrasseByName(String name) {
        BasisStrassen tmp;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tmp = getStrasseByName(name, session);
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            tmp = null;
        }
        return tmp;
    }

    /**
     * Liefert alle vorhandenen Straßennamen als Strings.
     * @param session Eine Hibernate-Session
     * @return Alle vorhandenen Straßennamen
     * @throws HibernateException Wenn ein Datenbank-Fehler auftritt
     */
    public static String[] getStrassen(Session session) throws HibernateException {
        List list = null;
        String suchString = "SELECT strassen.strasse FROM BasisStrassen strassen ORDER BY strassen.strasse";

        Query query = session.createQuery(suchString);
        query.setCacheable(true);
        query.setCacheRegion("strassenliste");
        list = query.list();
        String[] tmp = new String[list.size()];
        tmp = (String[]) list.toArray(tmp);
        return tmp;
    }

    /**
     * Liefert alle vorhandenen Straßennamen als Strings.
     * Erzeugt für die Abfrage eine neue Hibernate-Session und
     * schließt sie dann wieder. Diese Methode nur an Stellen benutzen,
     * wo gerade keine offene Session zur Verfügung steht.
     * @return Alle vorhandenen Straßennamen
     * @throws HibernateException Wenn ein Datenbank-Fehler auftritt
     */
    public static String[] getStrassen() throws HibernateException {
        Session session = HibernateSessionFactory.currentSession();
        String[] tmp = getStrassen(session);
        HibernateSessionFactory.closeSession();
        return tmp;
    }
}
