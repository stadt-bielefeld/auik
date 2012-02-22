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
 * Ein JFormattedTextField zum Bearbeiten von Integern.
 * @author David Klotz
 */
public class IntegerField extends BasicFormattedField {
    private static final long serialVersionUID = -3196396811481389378L;

    /**
     * Erzeugt ein neues formatiertes Textfeld zum Bearbeiten von Integern.
     * Zum Abfragen des aktuellen Wertes getIntValue() benutzen.
     */
    public IntegerField() {
        NumberFormat intFormat = NumberFormat.getIntegerInstance();
        intFormat.setGroupingUsed(false);
        setFormatterFactory(
            new DefaultFormatterFactory(new NumberFormatter(intFormat)));
    }

    public Integer getIntValue() {
        Integer nummer = null;
        try {
            commitEdit();
            if (getValue() instanceof Long) {
                nummer = new Integer(((Long) getValue()).intValue());
            } else {
                nummer = (Integer) getValue();
            }
        } catch (ParseException e1) {
            nummer = null;
        }

        return nummer;
    }
}
