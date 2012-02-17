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
 * Created on 20.04.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 * Ein JFormattedTextField zum Bearbeiten von Fließkomma-Zahlen.
 * @author David Klotz
 */
public class DoubleField extends BasicFormattedField {
    private static final long serialVersionUID = 5923571677035538028L;

    /**
     * Erzeugt ein neues formatiertes Textfeld zum Bearbeiten von Doubles.
     * Zum Abfragen des aktuellen Wertes getDoubleValue() benutzen.
     */
    public DoubleField(int minNachKommaStellen) {
        this(minNachKommaStellen, -1);
    }

    /**
     * Erzeugt ein neues formatiertes Textfeld zum Bearbeiten von Doubles.
     * Zum Abfragen des aktuellen Wertes getDoubleValue() benutzen.
     */
    public DoubleField(int minNachKommaStellen, int maxNachKommaStellen) {
        NumberFormat doubleFormat = NumberFormat.getNumberInstance();
        doubleFormat.setGroupingUsed(false);
        doubleFormat.setMinimumFractionDigits(minNachKommaStellen);
        if ((maxNachKommaStellen >= minNachKommaStellen) && (maxNachKommaStellen != -1)) {
            doubleFormat.setMaximumFractionDigits(maxNachKommaStellen);
        } else {
            doubleFormat.setMaximumFractionDigits(5);
        }
        setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(doubleFormat)));
    }

    /**
     * Liefert den Wert dieses Textfeldes als Double oder <code>null</code>, falls
     * das Feld leer ist oder der Inhalt nicht als Double wiedergegeben werden kann.
     * @return Den Wert dieses Textfeldes als Double.
     */
    public Double getDoubleValue() {
        Double nummer;
        try {
            commitEdit();
            if (getValue() instanceof Long) {
                nummer = new Double(((Long) getValue()).doubleValue());
            } else {
                nummer = (Double) getValue();
            }
        } catch (ParseException e) {
            nummer = null;
        }

        return nummer;
    }

    /**
     * Liefert den Wert dieses Textfeldes als Float oder <code>null</code>, falls
     * das Feld leer ist oder der Inhalt nicht als Float wiedergegeben werden kann.
     * @return Den Wert dieses Textfeldes als Float.
     */
    public Float getFloatValue() {
        Double val = getDoubleValue();
        if (val != null) {
            return new Float(val.floatValue());
        } else {
            return null;
        }
    }
}
