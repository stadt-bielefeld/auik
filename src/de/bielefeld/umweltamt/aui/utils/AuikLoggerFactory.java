/**
 * Copyright 2005-2042, Stadt Bielefeld
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

package de.bielefeld.umweltamt.aui.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggerFactory;

/**
 * AuikLogger factory which sets different logging levels for different users.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class AuikLoggerFactory implements LoggerFactory {

	/** We only need to run {@link AuikLoggerFactory.init} once */
	private static boolean needsToRunInit = true;

	/**
	 * Make sure we run {@link AuikLoggerFactory.init} and
	 * create a new AuikLogger instance
	 *
	 * @return The new AuikLogger instance
	 */
	@Override
	public Logger makeNewLoggerInstance(String name) {
		if (AuikLoggerFactory.needsToRunInit) init();
		return new AuikLogger(name);
	}

	/** Initialize the Logger with user specific log levels */
	private static void init() {
		AuikLoggerFactory.setSpecialLogLevelsByUser();
		// If we want to do some global formatting, it would go here.
		needsToRunInit = false;
		return;
	}

    /**
     * This method sets the log level according to the user. It is meant to be
     * used by programmers to set their log level individually while
     * programming. For all other users the log level from the file
     * log4j.properties is used.
     *
     * TODO: Load the settings from AUIK.properties.
     */
    private static void setSpecialLogLevelsByUser() {
    	String user = System.getProperty("user.name");

		// Set to custom settings
    	if (user.matches("u633d")) { // Gerd
    		Logger.getRootLogger().setLevel(Level.FATAL);
    		Logger.getLogger("org.hibernate").setLevel(Level.FATAL);
    		Logger.getLogger("de.bielefeld.umweltamt.aui").setLevel(Level.FATAL);
    	} else if (user.matches("u633z")) { // Connz
    		Logger rootLogger = Logger.getRootLogger();
    		rootLogger.setLevel(Level.ALL);
    		/* Set the output pattern:
    		 * %C - complete class name
    		 * %C{1} - class name
    		 * %d{dd MMM yyyy HH:mm:ss,SSS}, %d{ABSOLUTE} - the date
    		 * %l - location (slow)
    		 * %L - line number (slow)
    		 * %m - message
    		 * %M - method (slow)
    		 * %n - \n
    		 * %p - priority
    		 * %r - milliseconds from layout creation
    		 * %t - thread name
    		 */
    		((PatternLayout) rootLogger.getAppender("stdout").getLayout())
    				// milliseconds [thread] LEVEL class.method - message
//    				.setConversionPattern("%6r [%16.-16t] %-5p %C{1}.%M - %m%n");
    				// milliseconds LEVEL class.method - message
    				.setConversionPattern("%6r %-5p %C{1}.%M - %m%n");
    				// Buuh! Farbe geht nicht unter Windows...
//    				.setConversionPattern("\u001b[2;31m%6r %-5p - %m%n\u001b[m");
					// milliseconds LEVEL - message
//    				.setConversionPattern("%6r %-5p - %m%n");
    		Level level = Level.DEBUG;
    		Logger.getLogger("de.bielefeld.umweltamt.aui").setLevel(level);
    		Logger.getLogger("de.bielefeld.umweltamt.aui.mappings").setLevel(level);
    		Logger.getLogger("de.bielefeld.umweltamt.aui.module").setLevel(level);
    		Logger.getLogger("de.bielefeld.umweltamt.aui.utils").setLevel(level);
    		Logger.getLogger("org.hibernate").setLevel(Level.WARN);
            Logger.getLogger("de.bielefeld.umweltamt.aui.mappings.indeinl.ViewBwk").setLevel(Level.ALL);
    	}
    	return;
    }
}
