/*
 * Created on 21.04.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;

/**
 * Eine JTextArea, in die nur eine begrenzte Anzahl von Zeichen
 * eingegeben werden können.
 * @author David Klotz
 */
public class LimitedTextArea extends JTextArea {
	public LimitedTextArea(int maxLength) {
		this(maxLength, null);
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
