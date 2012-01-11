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
 * Created on 21.03.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * Eine ComboBox mit automatischer Suche und Vervollständigung.
 * @author David Klotz
 */
public class SearchBox extends JComboBox {
	
	private static final long serialVersionUID = 9149548665485817298L;

	/**
	 * Sind neue Werte in der SearchBox erlaubt.
	 */
	private boolean allowsNewValues = false;
	
	/**
     * Erleichterungs-Konstruktor, der eine leere JComboBox erzeugt.
     */
    public SearchBox() {
        this(new Object[]{});
    }

    /**
     * Erleichterungs-Konstruktor, der die gegebene Liste automatisch
     * in einen Array konvertiert.
     * @param list Eine Liste mit Objekten, die in dieser ComboBox angezeigt werden sollen
     */
    public SearchBox(final List<Object> list) {
        this(list.toArray());
    }

    /**
     * Erzeugt eine neue ComboBox mit automatischer Suche und Vervollständigung.
     * Das Popup dieser ComboBox kann dabei breiter sein, als die Box selbst.
     * @param items Ein Array mit Objekten, die in dieser ComboBox angezeigt werden sollen.
     */
    public SearchBox(Object[] items) {
        super(items);

        this.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(PopupMenuEvent e) {}

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                Object comp = box.getUI().getAccessibleChild(box, 0);
                if (!(comp instanceof JPopupMenu)) return;
                JComponent scrollPane = (JComponent) ((JPopupMenu) comp).getComponent(0);

                // Variante Eins
                Dimension size = new Dimension();
                size.width = box.getPreferredSize().width;
                size.height = scrollPane.getPreferredSize().height;

                // Variante Zwei
                //Dimension size = scrollPane.getPreferredSize();
                //size.width = size.width * 2;

                scrollPane.setPreferredSize(size);

                //  following line for Tiger
                scrollPane.setMaximumSize(size);
            }
        });

        AutoCompletion.enable(this);
    }
    
    /**
     * Getter für die Option neue Werte zuzulassen.
     * 
     * @return Ob neue Werte in der SearchBox erlaubt sind.
     */
    public boolean allowsNewValues() {
    	return this.allowsNewValues;
    }

    /**
     * Setter für die Option neue Werte zuzulassen.
     * 
     * @param pAllowsNewValues Neuer Wert für diese Eigenschaft
     * @return
     */
    public boolean allowsNewValues(boolean pAllowsNewValues) {
    	this.allowsNewValues = pAllowsNewValues;
    	return allowsNewValues();
    }
}
