/*
 * Created on 08.02.2005
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Ein ListCellRenderer um den einzelnen Items in Listen (besonders 
 * in ComboBoxen) einen Tooltip zuzuweisen, wenn ihr Name besonders
 * lang ist.
 * @author David Klotz
 */
public class LongNameComboBoxRenderer extends JLabel implements
		ListCellRenderer {

	/**
	 * Erzeugt einen ListCellRenderer, der den einzelnen Items in Listen 
	 * (besonders in ComboBoxen) einen Tooltip zuweist, wenn ihr Name 
	 * besonders lang ist.
	 */
	public LongNameComboBoxRenderer() {
		setOpaque(true);
	}
	
	/**
	 * Liefert das Label zum Rendern der ListCell
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		String tmp = null;
		if (value != null) {
			tmp = value.toString();
		}
		
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
		String toolTip = null;
		
		if ((tmp != null) && (tmp.length() > 15)) {
			toolTip = tmp;
		}
		
		setText(tmp);
		setToolTipText(toolTip);
		
		return this;
	}

}
