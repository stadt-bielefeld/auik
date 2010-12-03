/*
 * Created on 16.03.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.text.NumberFormat;


/**
 * Ein Wrapper um Doubles aus Strings mit Komma zu erzeugen.
 * @author David Klotz
 */
public class KommaDouble {
    private Double value;
    private NumberFormat formatter;

    public KommaDouble(Double value) {
        this.value = value;
    }

    public KommaDouble(double value) {
        this.value = new Double(value);
    }

    public KommaDouble(String str) throws NumberFormatException {
        String tmp = str.replaceAll(",", ".");
        value = new Double(tmp);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String toString() {
        if (formatter == null) {
            formatter = NumberFormat.getNumberInstance();
            formatter.setGroupingUsed(false);
        }

        return formatter.format(value);
    }
}
