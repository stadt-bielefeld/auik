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
 * Datei:
<<<<<<< BasisObjektarten.java
 * $Id: BasisObjektarten.java,v 1.5.2.1 2010-11-23 10:25:58 u633d Exp $
=======
 * $Id: BasisObjektarten.java,v 1.5.2.1 2010-11-23 10:25:58 u633d Exp $
>>>>>>> 1.20.6.1
 *
 * Erstellt am 19.01.05 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2010/01/26 09:20:43  u633d
 * Fettabscheider-Analysen
 *
 * Revision 1.4  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.3  2008/09/01 07:00:29  u633d
 * Anlegen von Sielhautproben ueberabeitet
 *
 * Revision 1.2  2008/06/12 10:21:41  u633d
 * diverse Bugfixes
 *
 * Revision 1.1  2008/06/05 11:38:34  u633d
 * Start AUIK auf Informix und Postgresql
 *
<<<<<<< BasisObjektarten.java
 * Revision 1.24  2006/10/17 07:54:29  u633d
 * Anhang 52 (Chemische Wäschereien) haben nun einen eigenen Tab.
 *
 * Revision 1.23  2006/09/26 09:48:38  u633d
 * Anh 55 bereinigt; settings wieder auf UTEDB2 gesetzt
 *
 * Revision 1.22  2006/09/26 05:46:16  u633d
 * BHKW ergaenzt
 *
 * Revision 1.21  2006/09/13 09:18:17  u633d
 * Datenquellen für Anh55 hinzugefügt
 *
=======
 * Revision 1.20.6.1  2006/07/26 06:46:01  u633d
 * Neuer Zweig
 *
>>>>>>> 1.20.6.1
 * Revision 1.20  2005/09/07 10:48:23  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.19  2005/08/31 06:22:56  u633d
 * - kleine Ergänzungen
 *
 * Revision 1.18  2005/08/25 14:47:00  u633d
 * - zu viel ;)
 *
 * Revision 1.17  2005/08/17 05:46:36  u633d
 * - Anhang 56 erstellt
 *
 * Revision 1.16  2005/07/05 08:11:09  u633d
 * Hochkomma in Mappings
 *
 * Revision 1.15  2005/06/30 11:39:16  u633z
 * Datenbank-Umstellung
 *
 * Revision 1.14  2005/06/13 15:30:36  u633z
 * - Anhang 53 IDs und Methoden hinzugefügt
 *
 * Revision 1.13  2005/06/09 13:38:22  u633z
 * - Sortierung wieder auf Namen (alphabetisch) geändert
 * - toString zeigt jetzt die Abteilung mit an
 * - Methoden zur Abfrage der Abteilung hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;

import de.bielefeld.umweltamt.aui.mappings.DatabaseClassToString;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * A class that represents a row in the 'BASIS_OBJEKTARTEN' table. This class
 * may be customized as it is never re-generated after being created.
 */
public class BasisObjektarten extends AbstractBasisObjektarten implements
    Serializable {
    private static final long serialVersionUID = -6919909308275509008L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Die ID der Objektart "Probenahmepunkt" */
    private static final int PROBEPUNKT = 32;
    /** Die ID der Objektart "Brennwertkessel" */
    private static final int BWK = 16;
    /** Die ID der Objektart "Blockheizkraftwerk" */
    private static final int BHKW = 36;
    /** Die ID der Objektart "Anhang 40" */
    private static final int ANHANG_40 = 25;
    /** Die ID der Objektart "Anhang 49" */
    private static final int ANHANG_49 = 14;
    /** Die ID der Objektart "Abscheider" */
    private static final int ABSCHEIDER = 19;
    /** Die ID der Objektart "Abscheider34" */
    private static final int ABSCHEIDER34 = 58;
    /** Die ID der Objektart "Fettabscheider" */
    private static final int FETTABSCHEIDER = 15;
    /** Die ID der Objektart "Zahnarzt" */
    private static final int ANHANG_50 = 20;
    /** Die ID der Objektart "Chemische Wäscherei" */
    private static final int ANHANG_52 = 35;
    /** Die ID der Objektart "Anhang 53 (< 3000)" */
    private static final int ANHANG_53_KLEIN = 17;
    /** Die ID der Objektart "Anhang 53 (> 3000)" */
    private static final int ANHANG_53_GROSS = 18;
    /** Die ID der Objektart "Suev-Kan" */
    private static final int SUEV = 13;
    /** Die ID der Objektart "Wäscherei" */
    private static final int ANHANG_55 = 29;
    /** Die ID der Objektart "Duckerei" */
    private static final int ANHANG_56 = 30;
    /** Die ID der Objektart "Genehmigung" */
    private static final int GENEHMIGUNG = 42;

    /**
     * Simple constructor of BasisObjektarten instances.
     */
    public BasisObjektarten() {
    }

    /**
     * Constructor of BasisObjektarten instances given a simple primary key.
     * @param objektartid
     */
    public BasisObjektarten(java.lang.Integer objektartid) {
        super(objektartid);
    }

    /**
     * To implement custom toString methods, jump to not generated code.<br>
     * Basically we either call on <code>toDebugString</code> for a debug
     * string, call on <code>toGuiString</code> for a gui representation or do
     * something completely different.
     * @return String
     */
    @Override
    public String toString() {
        return DatabaseClassToString.toStringForClass(this);
    }

    /* Add customized code below */

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart "Probepunkt" ist
     */
    public boolean isProbepunkt() {
        return (PROBEPUNKT == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart "Anhang 40" ist
     */
    public boolean isAnh40() {
        return (ANHANG_40 == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart "Brennwertkessel"
     *         ist
     */
    public boolean isBWK() {
        return ((BWK == this.getObjektartid().intValue())
            || (BHKW == this.getObjektartid().intValue()));
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart "Zahnarzt" ist
     */
    public boolean isAnh50() {
        return (ANHANG_50 == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart "Anhang 49",
     *         "Abscheider" oder "Fettabscheider" oder "Abscheider34" ist
     */
    public boolean isAnh49() {
        return ((ANHANG_49 == this.getObjektartid().intValue())
            || (ABSCHEIDER == this.getObjektartid().intValue())
            || (FETTABSCHEIDER == this.getObjektartid().intValue())
            || (ABSCHEIDER34 == this.getObjektartid().intValue()));
    }

    public boolean isFettabscheider() {
        return (FETTABSCHEIDER == this.getObjektartid().intValue());
    }

    public boolean isAbscheider() {
        return (ABSCHEIDER == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart
     *         "Chemische Wäscherei" ist
     */
    public boolean isAnh52() {
        return (ANHANG_52 == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart
     *         "Anhang 53 (< 3000)" ist
     */
    public boolean isAnh53Kl() {
        return (ANHANG_53_KLEIN == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart
     *         "Anhang 53 (< 3000)" ist
     */
    public boolean isAnh53Gr() {
        return (ANHANG_53_GROSS == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart "Wäscherei" ist
     */
    public boolean isAnh55() {
        return (ANHANG_55 == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart "Druckerei" ist
     */
    public boolean isAnh56() {
        return (ANHANG_56 == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart
     *         "Suev-Kan Verfahren" ist
     */
    public boolean isSuev() {
        return (SUEV == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart
     *         "Suev-Kan Verfahren" ist
     */
    public boolean isGenehmigung() {
        return (GENEHMIGUNG == this.getObjektartid().intValue());
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart vom Abschnitt
     *         360.33 bearbeitet wird
     */
    /* Deleted the .trim(), because we do not seam to need it. */
    public boolean isAbteilung33() {
        return ("360.33".equals(getAbteilung()));
    }

    /**
     * @return Liefert <code>true</code>, wenn diese Objektart vom Abschnitt
     *         360.34 bearbeitet wird
     */
    /* Deleted the .trim(), because we do not seam to need it. */
    public boolean isAbteilung34() {
        return ("360.34".equals(getAbteilung()));
    }

    /**
     * Liefert alle vorhandenen Objektarten.
     * @return Alle vorhandenen Objektarten
     */
    public static BasisObjektarten[] getObjektarten() {
        return (BasisObjektarten[]) new DatabaseAccess().createQuery(
            "FROM BasisObjektarten bo ORDER BY bo.objektart")
            .setCacheable(true)
            .setCacheRegion("objartliste")
            .array(new BasisObjektarten[0]);
    }

    /**
     * Liefert eine Objektart mit einer bestimmten ID.
     * @param id Die ID (der Primärschlüssel) des Standorts.
     * @return Den gesuchten Standort oder <code>null</code>, falls kein
     *         Standort mit dieser ID existiert.
     */
    public static BasisObjektarten getObjektart(Integer id) {
        BasisObjektarten art = null;
        art = (BasisObjektarten) new DatabaseAccess()
            .get(BasisObjektarten.class, id);
        return art;
    }

    /**
     * Speichert eine Objektart in die Datenbank, bzw. updatet eine schon
     * vorhandene Objektart mit neuen Werten.
     * @param art Die Objektart, die gespeichert werden soll.
     * @return Die gespeicherte Objektart, oder <code>null</code>, falls beim
     *         Speichern ein Fehler auftrat.
     */
    public static BasisObjektarten saveObjektart(BasisObjektarten art) {
        BasisObjektarten artRet = null;
        artRet = (BasisObjektarten) new DatabaseAccess().merge(art);
        if (artRet != null) {
            log.debug("Neue Objektart " + artRet + " gespeichert!");
        }
        return artRet;
    }

    /**
     * Löscht eine vorhandene Objektart aus der Datenbank.
     * @param standort Die Objektart, der gelöscht werden soll.
     * @return <code>true</code>, wenn die Objektart gelöscht wurde oder
     *         <code>false</code> falls dabei ein Fehler auftrat (z.B. die
     *         Objektart nicht in der Datenbank existiert).
     */
    public static boolean removeObjektart(BasisObjektarten objektart) {
        return new DatabaseAccess().delete(objektart);
    }
}
