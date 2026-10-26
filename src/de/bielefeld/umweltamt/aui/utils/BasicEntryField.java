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

import javax.swing.JTextField;

/**
 * Ein JTextField, dass seinen Inhalt auswählt, wenn es
 * den Keyboard-Fokus bekommt.
 * @author David Klotz
 */
public class BasicEntryField extends JTextField {
    private static final long serialVersionUID = 4428876583427331046L;

    public BasicEntryField() {
        super();
    }
    public BasicEntryField(String text) {
        super(text);
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
