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

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Ein DocumentFilter um die maximale Zahl von Zeichen in einer
 * JTextComponent (JTextField, JTextArea etc.) zu limitieren.
 * @see <a href="http://javaalmanac.com/egs/javax.swing.text/LimitText.html">Java Almanac - LimitText</a>
 * @author David Klotz
 */
public class FixedSizeFilter extends DocumentFilter {
    int maxSize;

    // limit is the maximum number of characters allowed.
    public FixedSizeFilter(int limit) {
        maxSize = limit;
    }

    // This method is called when characters are inserted into the document
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String str,
            AttributeSet attr) throws BadLocationException {
        replace(fb, offset, 0, str, attr);
    }

    // This method is called when characters in the document are replace with other characters
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
            String str, AttributeSet attrs) throws BadLocationException {

        int strLength = (str != null) ? str.length() : 0;
        int newLength = fb.getDocument().getLength()-length+strLength;

        if (newLength <= maxSize) {
            fb.replace(offset, length, str, attrs);
        } else {
            throw new BadLocationException("New characters exceeds max size of document", offset);
        }
    }
}