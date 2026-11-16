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

import java.awt.event.FocusEvent;
import java.text.Format;

import javax.swing.JFormattedTextField;

/**
 * Ein JFormattedTextField, dass seinen Inhalt auswählt, wenn
 * es den Keyboard-Fokus bekommt.
 * @author David Klotz
 */
public class BasicFormattedField extends JFormattedTextField {
    private static final long serialVersionUID = 4569009650348515402L;

    public BasicFormattedField() {
        super();
    }

    public BasicFormattedField(Object value) {
        super(value);
    }

    public BasicFormattedField(Format format) {
        super(format);
    }

    public BasicFormattedField(AbstractFormatter formatter) {
        super(formatter);
    }

    public BasicFormattedField(AbstractFormatterFactory factory) {
        super(factory);
    }

    public BasicFormattedField(AbstractFormatterFactory factory,
            Object currentValue) {
        super(factory, currentValue);
    }

    @Override
    protected void processFocusEvent(FocusEvent e)
    {
        super.processFocusEvent(e);
        if (e.getID() == FocusEvent.FOCUS_GAINED) {
            selectAll() ;
        }
    }
}
