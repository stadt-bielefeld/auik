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
 * Created on 16.03.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.text.NumberFormat;


/**
 * Ein Wrapper um Doubles aus Strings mit Komma zu erzeugen.
 * @author David Klotz
 */
public class KommaDouble {
    protected Double value;
    protected NumberFormat formatter;

    public KommaDouble() {
        this.value = 0.0;
    }

    public KommaDouble(Double value) {
        if (value == null) {
            this.value = 0.0;
        } else {
            this.value = value.doubleValue();
        }
    }

    public KommaDouble(Float value) {
        if (value == null) {
            this.value = 0.0;
        } else {
            this.value = value.doubleValue();
        }
    }

    public KommaDouble(double value) {
        this.value = new Double(value);
    }

    public KommaDouble(String str) throws NumberFormatException {
        String tmp = str.replaceAll(",", ".");
        value = new Double(tmp);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (formatter == null) {
            formatter = NumberFormat.getNumberInstance();
            formatter.setGroupingUsed(false);
            formatter.setMaximumFractionDigits(4);
        }

        return formatter.format(value);
    }
}
