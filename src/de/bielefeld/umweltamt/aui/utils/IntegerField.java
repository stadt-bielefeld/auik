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
	/**
	 * Erzeugt ein neues formatiertes Textfeld zum Bearbeiten von Integern.
	 * Zum Abfragen des aktuellen Wertes getIntValue() benutzen.
	 */
	public IntegerField() {
		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		intFormat.setGroupingUsed(false);
		setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(intFormat)));
	}
	
	public Integer getIntValue() {
		Integer nummer;
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
