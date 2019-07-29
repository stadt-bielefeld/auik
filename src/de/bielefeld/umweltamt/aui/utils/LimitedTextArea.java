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
 * Created on 21.04.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;

/**
 * Eine JTextArea, in die nur eine begrenzte Anzahl von Zeichen
 * eingegeben werden können.
 * @author David Klotz
 */
public class LimitedTextArea extends JTextArea {
    private static final long serialVersionUID = 618412051183740489L;

    public LimitedTextArea(int maxLength) {
        this(maxLength, null);
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        this.setFont(font);
    }

    public LimitedTextArea(int maxLength, String text) {
        AbstractDocument doc = (AbstractDocument)this.getDocument();
        doc.setDocumentFilter(new FixedSizeFilter(maxLength));

        setLineWrap(true);
        setWrapStyleWord(true);

        if (text != null) {
            setText(text);
        }
    }
}
