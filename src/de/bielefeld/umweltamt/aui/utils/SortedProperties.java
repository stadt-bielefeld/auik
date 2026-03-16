package de.bielefeld.umweltamt.aui.utils;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class SortedProperties extends Properties {
	/**
	 * Overrides, called by the store method.
	 */
    @Override
    public synchronized Enumeration<Object> keys() {
		Enumeration<Object> keysEnum = super.keys();
		Vector<Object> keyList = new Vector<>();
		while (keysEnum.hasMoreElements()) {
			keyList.add(keysEnum.nextElement());
		}
		keyList.sort(null);
		return keyList.elements();
	}
}
