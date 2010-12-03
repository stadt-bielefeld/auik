/*
 * Datei:
 * $Id: NamedObject.java,v 1.2 2009-03-24 12:35:21 u633d Exp $
 *
 * Erstellt am 03.02.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.2  2005/05/25 15:46:27  u633z
 * - Header hinzugefügt, Javadoc verbessert
 *
 */
package de.bielefeld.umweltamt.aui.utils;

/**
 * Ein benanntes Objekt.
 * Ein dünner Wrapper um ein Object, der ihm zusätzlich einen
 * Namen gibt.
 * Objekte dieser Klasse besitzt einen Namen und ein zweites
 * Objekt ('value') als Wert. Da die toString()-Methode von
 * Objekten dieser Klasse nur den Namen zurückliefert, ist sie
 * ideal dafür geeignet um z.B. eine Combobox mit Objekten von
 * beliebigem Typ (die selber keine geeignete toString-Methode
 * haben)zu füllen.
 * @author David Klotz
 */
public class NamedObject {
    private String name;
    private Object value;

    /**
     * Erzeugt ein neues benanntes Objekt mit diesem Namen.
     * @param name Der Name des benannten Objekts
     * @param value Das benannte Objekt
     */
    public NamedObject(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return Returns the name of this named Object.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the named Object.
     */
    public Object getValue() {
        return value;
    }
    /**
     * @param value The named Object to set.
     */
    public void setValue(Object value) {
        this.value = value;
    }


    /**
     * @return Returns the name of this named Object.
     * @see #getName()
     */
    public String toString() {
        return getName();
    }
}
