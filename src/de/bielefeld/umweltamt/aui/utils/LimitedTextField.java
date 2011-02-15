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
 * Datei:
 * $Id: LimitedTextField.java,v 1.2 2009-03-24 12:35:21 u633d Exp $
 *
 * Erstellt am 21.04.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:34  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.2  2005/06/03 11:07:36  u633z
 * - LimitedTextField erbt jetzt korrekt von BasicEntryField
 * - Richtigen Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.utils;

import javax.swing.text.AbstractDocument;

/**
 * Ein JTextField, in dass nur eine begrenzte Anzahl von Zeichen
 * eingegeben werden können.
 * @author David Klotz
 */
public class LimitedTextField extends BasicEntryField {
    public LimitedTextField(int maxLength) {
        this(maxLength, null);
    }

    public LimitedTextField(int maxLength, String text) {
        AbstractDocument doc = (AbstractDocument)this.getDocument();
        doc.setDocumentFilter(new FixedSizeFilter(maxLength));

        if (text != null) {
            setText(text);
        }
    }
}
