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

    protected void processFocusEvent(FocusEvent e)
    {
        super.processFocusEvent(e);
        if (e.getID() == FocusEvent.FOCUS_GAINED) {
            selectAll() ;
        }
    }
}
