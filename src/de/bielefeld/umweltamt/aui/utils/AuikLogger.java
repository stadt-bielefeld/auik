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

/**
 * Specialized logging class which sets different logging levels for different
 * users and offers a method for getting the class logger directly. 
 * 
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public final class AuikLogger extends Logger {
	
	/** We only need to run the init method once */
	private static boolean needsToRunInit = true;

	/** Constructor */
	protected AuikLogger(String name) {
		super(name);
		if (AuikLogger.needsToRunInit) init();
	}
	
	/** Get a class specific logger */
	public static Logger getLogger() {
		if (AuikLogger.needsToRunInit) init();
		return AuikLogger.getLogger(AuikLogger.getCallingClassName());
	}
	
	/** Initialize the Logger with user specific log levels */
	private static void init() {
		AuikLogger.setSpecialLogLevelsByUser();
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
    	if (user.matches("u633l")) { // Gerd
    		Logger.getRootLogger().setLevel(Level.FATAL);
    		Logger.getLogger("org.hibernate").setLevel(Level.FATAL);
    		Logger.getLogger("de.bielefeld.umweltamt.aui").setLevel(Level.FATAL);
    	} else if (user.matches("u633z")) { // Connz
    		Logger.getRootLogger().setLevel(Level.INFO);
    		Logger.getLogger("de.bielefeld.umweltamt.aui").setLevel(Level.DEBUG);
    		Logger.getLogger("de.bielefeld.umweltamt.aui.mappings").setLevel(Level.DEBUG);
    		Logger.getLogger("de.bielefeld.umweltamt.aui.module").setLevel(Level.DEBUG);
    		Logger.getLogger("de.bielefeld.umweltamt.aui.utils").setLevel(Level.DEBUG);
    		Logger.getLogger("org.hibernate").setLevel(Level.INFO);
    	}
    	return;
    }
    
    /**
     * Get the class name of the class which called the method / logger<br>
     * And... well yes, this is kind of a bit of rather dirty code...
     * @return class name of calling class
     */
    private static String getCallingClassName() {
    	return new Throwable().fillInStackTrace().getStackTrace()[2].getClassName();
    }
}
