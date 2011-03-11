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
package de.bielefeld.umweltamt.aui.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Dies ist ein Wrapper um ein Double Objekt. Es wird eine Methode toString()
 * angeboten, die eine deutsch formatierte Flie&szilg;kommazahl mit
 * Tausender-Trenner liefert.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class GermanDouble {

    /** The formatter that is used to adjust the output format.*/
    protected static NumberFormat format;

    /** The double value.*/
    protected Double value;


    public GermanDouble(Double value) {
        this.value = value;
    }


    public GermanDouble(double value) {
        this.value = new Double(value);
    }


    public Double getValue() {
        return value;
    }


    public void setValue(Double value) {
        this.value = value;
    }


    public String toString() {
        if (format == null) {
            format = NumberFormat.getNumberInstance(Locale.GERMAN);
            format.setGroupingUsed(true);
        }

        return format.format(value);
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
