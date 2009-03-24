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
