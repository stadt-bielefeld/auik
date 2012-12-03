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

import org.apache.log4j.Logger;

/**
 * Specialized logging class which has different logging levels for different
 * users and offers a method for getting the class logger directly and
 * create log entries with an added source field (only for level debug so far).
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public class AuikLogger extends Logger {

	/** Constructor */
	protected AuikLogger(String name) {
		super(name);
	}

	/** Get a class specific logger */
	public static AuikLogger getLogger() {
		return (AuikLogger) Logger.getLogger(AuikLogger.getCallingClassName(),
				new AuikLoggerFactory());
	}

	/**
	 * Imitate the old logging with message and source.
	 * TODO: Do we really still need this?
	 * @param message The log message
	 * @param src The source of the log message
	 */
	public void debug(Object message, String src) {
		super.debug("(" + src + ")" + message);
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
