/*
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.utils;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class CurrencyDouble extends KommaDouble {

    protected Locale locale;


    public CurrencyDouble(Double value, Locale locale) {
        super(value);

        this.locale = locale;
    }


    public CurrencyDouble(double value, Locale locale) {
        super(value);

        this.locale = locale;
    }


    public String toString() {
        if (formatter == null) {
            formatter = NumberFormat.getCurrencyInstance(locale);
        }

        return formatter.format(value);
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
