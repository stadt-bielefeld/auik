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

package de.bielefeld.umweltamt.aui;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern.
 * @see <a href="http://hibernate.org/42.html">hibernate.org/42.html</a>
 */
public class HibernateSessionFactory {

    /** Database manager */
    private static final DatabaseManager dbManager = DatabaseManager.getInstance();
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Location of hibernate.cfg.xml file.
     * NOTICE: Location should be on the classpath as Hibernate uses
     * #resourceAsStream style lookup for its configuration file. That
     * is place the config file in a Java package - the default location
     * is the default Java package.<br><br>
     * Examples: <br>
     * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml".
     * CONFIG_FILE_LOCATION = "/com/foo/bar/myhiberstuff.conf.xml".</code>
     */
    private static String CONFIG_FILE_LOCATION =
        System.getProperty("auik.hibernate.config",
            "resources/config/hibernate.cfg.xml");

    /** Holds a single instance of Session */
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    /** The single instance of hibernate configuration */
    private static /*final*/ Configuration cfg = new Configuration();

    /** The single instance of hibernate SessionFactory */
    private static org.hibernate.SessionFactory sessionFactory;

    /** The Database-User */
    private static String DB_USER = "";
    /** The Database-Password */
    private static String DB_PASS = "";
    /** Der Datenbank-Name */
    private static String DB_URL = "";
    /** Der Datenbank-Treiber */
    private static String DB_Driver = "";
    /** Der Datenbank-Dialekt */
    private static String DB_Dialect = "";

    /**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     * NIEMALS die Session über sess.close() selbst wieder schließen,
     * immer HibernateSessionFactory.closeSession() benutzen.
     * Diese Session-Factory verwaltet nur EINE gleichzeitig offene
     * Session, also aufpassen was man wann wo öffnet und schließt.
     *
     *  @return Session
     *  @throws HibernateException
     */
    public static Session currentSession() throws HibernateException {
        Session session = threadLocal.get();

        if (session == null) {
            if (sessionFactory == null) {
                try {
                    // First load the config file
                    cfg.configure(
                        AUIKataster.class.getResource(CONFIG_FILE_LOCATION));
                    // Then overwrite the user / password property
                    cfg.setProperty("hibernate.connection.username", DB_USER);
                    cfg.setProperty("hibernate.connection.password", DB_PASS);
					if (DB_URL != null && !DB_URL.equals("")) {
						cfg.setProperty("hibernate.connection.url", DB_URL);
					}
                    sessionFactory = cfg.buildSessionFactory();
                }
                catch (Exception e) {
                    log.error("%%%% Error Creating SessionFactory %%%%");
                    e.printStackTrace();
                }
            }
            session = sessionFactory.openSession();
            threadLocal.set(session);
            log.debug("Neue Session begonnen!");
        }

        return session;
    }

    /**
     *  Close the single hibernate session instance.
     *
     */
    public static void closeSession() {//throws HibernateException {
        Session session = threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            try {
                session.close();
                log.debug("Session geschlossen!");
            } catch (HibernateException e) {
            	dbManager.handleDBException(e, "HibernateSessionFactory.closeSession", false);
            }
        }
    }

    /**
     * Legt fest, welche Datenbank benutzt wird.
     * @param name Der Name der Datenbank
     */
    public static void setDBUrl(String url) {
        DB_URL = url;
    }

    /**
     * Stellt fest, welche Datenbank benutzt wird.
     * @return Der Name der Datenbank
     */
    public static String getDBUrl() {
        return DB_URL;
    }

    public static String getDBDriver() {
        return DB_Driver;
    }

    public static void setDBDriver(String driver) {
        DB_Driver = driver;
    }

    public static String getDBDialect() {
        return DB_Dialect;
    }

    public static void setDBDialect(String dialect) {
        DB_Dialect = dialect;
    }

    /**
     * Setzt die Benutzerdaten für die Datenbank.
     * @param user Der Datenbank-Benutzer
     * @param pass Das Passwort des Datenbank-Benutzers
     */
    public static void setDBData(String user, String pass) {
        DB_USER = user;
        DB_PASS = pass;
        closeSession();
        sessionFactory = null;
        cfg = new Configuration();
        //AUIKataster.debugOutput("User: " + DB_USER + ", Pass: " + DB_PASS, "HSF.setDBData");
    }

    /**
     * überprüft die Benutzerdaten für die Datenbank.
     * Wenn sie richtig sind, werden die Daten auch automatisch
     * für weitere Sessions gespeichert.
     * @param user Der Datenbank-Benutzer
     * @param pass Das Passwort des Datenbank-Benutzers
     * @return <code>true</code>, wenn die Benutzerdaten korrekt sind, sonst <code>false</code>
     */
    public static boolean checkCredentials(String user, String pass) throws HibernateException {
        setDBData(user, pass);
        //AUIKataster.debugOutput("User: " + DB_USER + ", Pass: " + DB_PASS, "HSF.checkCredentials");

        boolean tmp = false;

        try {
            Session session = currentSession();
            List<?> test = session.createSQLQuery(
                    "select count(*) from tab_streets_alkis"
            ).list();

            tmp = true;
            log.debug(test.toString());
        } catch (HibernateException e) {
            if (e.getClass().equals(org.hibernate.exception.JDBCConnectionException.class)) {
                tmp = false;
                setDBData("", "");
            } else {
                throw e;
            }
        } finally {
            closeSession();
        }

        return tmp;
    }

    /**
     * Default constructor.
     */
    private HibernateSessionFactory() {
    	// This place is intentionally left blank.
    }

}
