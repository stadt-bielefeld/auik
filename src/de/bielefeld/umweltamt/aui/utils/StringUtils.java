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

/**
 * A little utility class for String operations.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public class StringUtils {

    /**
     * Little helper method to set a strike through the text via HTML.<br>
     * @param text
     * @return String The text with HTML formatting for a strike
     */
    public static String setStrike(String text) {
        return ("<html><strike>" + text + "</strike></html>");
    }
}
