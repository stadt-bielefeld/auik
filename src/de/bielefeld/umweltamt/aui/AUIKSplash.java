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
 * Created on 14.01.2005
 */
package de.bielefeld.umweltamt.aui;

import java.awt.Frame;
import java.awt.Toolkit;
import java.net.URL;



import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Ein Splashscreen, der ein Bild anzeigt und w&auml;hrenddessen das
 * eigentliche Hauptprogramm l&auml;dt.
*/
public class AUIKSplash {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Zeigt einen Splashscreen an und l&auml;dt das eigentlich Hauptprogramm.
     * @param args
     */
    public static void main(String[] args) {
        // Read the image data and display the splash screen

        // -------------------------------------------------
        Frame splashFrame = null;
        URL imageURL = AUIKSplash.class.getResource("splash.png");
        if (imageURL != null) {
            splashFrame = SplashWindow.splash(
                Toolkit.getDefaultToolkit().createImage(imageURL)
            );
        } else {
            log.error("Splash image not found");
        }

        // Call the main method of the application using Reflection
        // --------------------------------------------------------
        try {
            Class.forName("de.bielefeld.umweltamt.aui.AUIKataster")
            .getMethod("main", new Class[] {String[].class})
            .invoke(null, new Object[] {args});
        } catch (Throwable e) {
            e.printStackTrace();

            System.err.flush();
            System.exit(10);
        }

        // Dispose the splash screen
        // -------------------------
        if (splashFrame != null) splashFrame.dispose();
    }
}
