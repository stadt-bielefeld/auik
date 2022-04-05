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
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.postgresql.util.PSQLException;

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
     * Map supported dialects to dialect classes
     */
    private static final Map<String, String> DIALECTS = Map.of(
        "postgresql", "org.hibernate.dialect.PostgreSQLDialect",
        "sqlite", "org.hibernate.dialect.SQLiteDialect",
        "h2", "org.hibernate.dialect.H2Dialect"
    );

    /**
     * Map dialect to driver class
     */
    private static final Map<String, String> DIALECT_TO_DRIVER = Map.of(
        "org.hibernate.dialect.PostgreSQLDialect", "org.postgresql.Driver",
        "org.hibernate.dialect.SQLiteDialect", "org.sqlite.JDBC",
        "org.hibernate.dialect.H2Dialect", "org.h2.Driver"
    );

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
                    if (doesDialectNeedCredentials()) {
                        // If needed overwrite the user / password property
                        cfg.setProperty("hibernate.connection.username", DB_USER);
                        cfg.setProperty("hibernate.connection.password", DB_PASS);
                    }

                    if (DB_URL != null && !DB_URL.equals("")) {
                        cfg.setProperty("hibernate.connection.url", DB_URL);
                        cfg.setProperty("hibernate.connection.driver_class", DB_Driver);
                        cfg.setProperty("hibernate.dialect", DB_Dialect);
                    }
                    sessionFactory = cfg.buildSessionFactory();
                }
                catch (Exception e) {
                    log.error("%%%% Error Creating SessionFactory %%%%");
                    e.printStackTrace();
                    return null;
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
    public static void closeSession() {
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

    /**
     * Set the db dialect name.
     * Support dialects are:
     *   - org.hibernate.dialect.PostgreSQLDialect
     *   - org.hibernate.dialect.SQLiteDialect
     *   - org.hibernate.dialect.H2Dialect
     * @param dialect Dialect class name
     */
    public static void setDBDialect(String dialect) {
        DB_Dialect = dialect;
    }

    public static String getDBUser() {
        return DB_USER;
    }

    /**
     * Get the dialect class by dialect name
     * Supported dialect name are:
     *   - postgresql
     *   - sqlite
     * @param dialectName Dialect name String
     * @throws IllegalArgumentException Thrown if a unsupported dialect is set
     * @return Dialect class name as String
     */
    public static String getDialectClassByDialectName(String dialectName) throws IllegalArgumentException {
        String dialect = DIALECTS.get(dialectName);
        if (dialect == null) {
            throw new IllegalArgumentException(String.format(String.format("Unsupported dialect name: %s", dialectName)));
        }
        return dialect;
    }

    /**
     * Get the jdbc driver class by dialect name
     * Supported dialect name are:
     *   - postgresql
     *   - sqlite
     * @param dialectName Dialect name String
     * @throws IllegalArgumentException Thrown if a unsupported dialect is set
     * @return Driver class name as String
     */
    public static String getDriverByDialectClass(String dialectName) throws IllegalArgumentException {
        String driver = DIALECT_TO_DRIVER.get(dialectName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(String.format("Unsupported dialect class: %s", dialectName)));
        }
        return driver;
    }

    /**
     * Check if the current db dialect needs credentials for authorization
     * @return True if credentials are needed, else false
     */
    public static boolean doesDialectNeedCredentials() {
        switch (DB_Dialect) {
            case "org.hibernate.dialect.SQLiteDialect": return false;
            default: return true;
        }
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
        return checkCredentials(user, pass, true);
    }

    /**
     * überprüft die Benutzerdaten für die Datenbank.
     * @param user Der Datenbank-Benutzer
     * @param pass Das Passwort des Datenbank-Benutzers
     * @param save True wenn korrekte Benuterdaten gespeichert werden sollen
     * @return <code>true</code>, wenn die Benutzerdaten korrekt sind, sonst <code>false</code>
     */
    public static boolean checkCredentials(String user, String pass, boolean save) throws HibernateException {
        String currentUser = DB_USER;
        String currentPw = DB_PASS;
        setDBData(user, pass);
        //AUIKataster.debugOutput("User: " + DB_USER + ", Pass: " + DB_PASS, "HSF.checkCredentials");

        boolean tmp = false;

        try {
            Session session = currentSession();
            List<?> test = session.createSQLQuery(
                    "select count(*) from basis.adresse"
            ).list();
            tmp = true;
            //If credentials are not save, reset
            if (!save) {
                setDBData(currentUser, currentPw);
            }
        } catch (Exception e) {
            if (e.getClass().equals(org.hibernate.exception.JDBCConnectionException.class)) {
                tmp = false;
                if (save) {
                    setDBData("", "");
                }
            }
        } finally {
            if (!save) {
                setDBData(currentUser, currentPw);
            }
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
