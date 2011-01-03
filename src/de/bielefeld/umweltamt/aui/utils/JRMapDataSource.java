/*
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.utils;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import de.bielefeld.umweltamt.aui.AUIKataster;


/**
 * Diese Klasse stellt ein {@link JRDataSource} dar. Die Werte, die von
 * {@link getFieldValue(JRField)} geliefert werden, kommen aus einem Object[][],
 * das beim Erstellen des Objekts bef&uuml;llt wird.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class JRMapDataSource implements JRDataSource {

    protected int index;

    protected Object[]   current;
    protected Object[][] values;

    /**
     * Dieser Konstruktor erstellt ein leeres JRMapDataSource ohne Inhalt und
     * sollte nicht benutzt werden.
     */
    protected JRMapDataSource() {
        this.index   = 0;
        this.current = null;
    }


    /**
     * Der default Konstruktor f&uuml;r ein {@link JRMapDataSource} objekt.
     *
     * @param values Die Werte, die in den JasperReport eingef&uuml;llt werden
     * sollen.
     */
    public JRMapDataSource(Object[][] values) {
        super();

        this.values = values;
    }


    /**
     * Liefert die Anzahl der Elemente in diesem Datensatz.
     *
     * @return die Anzahl der Elemente dieses Datensatzes oder -1 falls keine
     * Datens&auml;tze vorhanden sind.
     */
    public int size() {
        return values != null ? values.length : -1;
    }


    /**
     * Diese Methode liefert nur f&uuml;r einen Durchgang <i>true</i>.
     *
     * @return beim ersten Aufruf <i>true</i>, bei allen weiteren <i>false</i>.
     *
     * @throws NullPointerException if no values have been filled in.
     */
    public boolean next() {
        System.out.println("BEGIN STEPPING BY INDEX = " + index);
        index++;
        System.out.println("AFTER STEPPING INDEX = " + index);

        if (index <= size()) {
            AUIKataster.debugOutput(
                getClass().getName(),
                "Step forward to index " + index);

            current = values[index-1];
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Diese Methode liefert ein Element aus {@link values} welches zum
     * Schl&uuml;ssel <code>field.getName()</code> geh&ouml;rt.
     *
     * @param field Ein JasperReports {@link JRField}.
     *
     * @return ein Objekt aus {@link values} falls existent, falls kein Element
     * existiert, wird <i>null</i> zur&uuml;ckgegeben.
     */
    public Object getFieldValue(JRField field) throws JRException {
        String col = field.getName();

        AUIKataster.debugOutput(
            getClass().getName(),
            "Search for field: " + col + " | Index = " + index);

        if (col.equals("auswahl")) {
            return current[0];
        }
        else if (col.equals("Parameter")) {
            return current[1];
        }
        else if (col.equals("Kennzeichnung")) {
            return current[2];
        }
        else if (col.equals("Konservierung")) {
            return current[3];
        }

        return "Name '" + col + "' nicht gefunden.";
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
