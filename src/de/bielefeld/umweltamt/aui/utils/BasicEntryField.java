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
	public BasicEntryField() {
		super();
	}
	public BasicEntryField(String text) {
		super(text);
	}
	
	protected void processFocusEvent(FocusEvent e) 
	{
		super.processFocusEvent(e);
		if (e.getID() == FocusEvent.FOCUS_GAINED) {
			selectAll() ;
		}
	}
}
