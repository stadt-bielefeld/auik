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


/**
 * Diese Klasse stellt ein {@link JRDataSource} dar. Die Werte, die von
 * {@link getFieldValue(JRField)} geliefert werden, kommen aus einer {@link
 * Map}, die beim Erstellen des Objekts integriert wird.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class JRMapDataSource implements JRDataSource {

    protected int index;
    protected Object[][] values =
    {
        {"", "Berne", "Heidrun Fill", "277 Seventh Av."},
        {"", "Boston", "Holger Hoff", "339 College Av."},
        {"", "Chicago", "Julia White", "412 Upland Pl."},
    };


    /**
     * Der default Konstruktor f&uuml;r ein {@link JRMapDataSource} objekt.
     *
     * @param values Die Werte, die in den JasperReport eingef&uuml;llt werden
     * sollen.
     */
    public JRMapDataSource() {
        this.index  = 0;
    }


    /**
     * Diese Methode liefert nur f&uuml;r einen Durchgang <i>true</i>.
     *
     * @return beim ersten Aufruf <i>true</i>, bei allen weiteren <i>false</i>.
     */
    public boolean next() {
        System.out.println("Step forward.");
        index++;
        return (index < values.length);
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
        System.out.println("Search for field: " + col);

        if (col.equals("auswahl")) {
            return "x";
        }
        else if (col.equals("Parameter")) {
            return values[index][1];
        }
        else if (col.equals("Kennzeichnung")) {
            return values[index][2];
        }
        else if (col.equals("Konservierung")) {
            return values[index][3];
        }

        return "Name '" + col + "' nicht gefunden.";
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
