/**
 * Copyright 2005-2011, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */
package de.bielefeld.umweltamt.aui.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Diese Klasse stellt ein {@link JRDataSource} dar. Die Werte, die von
 * {@link getFieldValue(JRField)} geliefert werden, kommen aus einem Object[][],
 * das beim Erstellen des Objekts bef&uuml;llt wird.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class JRMapDataSource implements JRDataSource {

    protected int index;

    protected String[]            columns;
    protected Map<String,Integer> indexBuffer;

    protected Object[]            current;
    protected Object[][]          values;

    /**
     * Dieser Konstruktor erstellt ein leeres JRMapDataSource ohne Inhalt und
     * sollte nicht direkt benutzt werden, jedoch immer von anderen
     * Konstruktoren aufgerufen werden!
     */
    protected JRMapDataSource() {
        this.index       = 0;
        this.indexBuffer = new HashMap<String,Integer>();
        this.current     = null;
    }


    /**
     * Der default Konstruktor f&uuml;r ein {@link JRMapDataSource} objekt.
     *
     * @param values Die Werte, die in den JasperReport eingef&uuml;llt werden
     * sollen.
     */
    public JRMapDataSource(String[] columns, Object[][] values) {
        super();

        this.columns = columns;
        this.values  = values;
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
    @Override
    public boolean next() {
        index++;

        if (index <= size()) {
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
    @Override
    public Object getFieldValue(JRField field) throws JRException {
        String col = field.getName();
        int idx    = getIndexOf(col);

        return idx >= 0 ? current[idx] : "";
    }


    /**
     * Diese Methode liefert den Index einer Spalte anhand ihres Namen.
     *
     * @param col Der Name der Spalte.
     *
     * @return den Index der Spalte <i>col</i>.
     */
    public int getIndexOf(String col) {
        if (indexBuffer == null) {
            indexBuffer = new HashMap<String,Integer>();
        }

        Object ib = indexBuffer.get(col);

        if (ib != null) {
            return ((Integer) ib).intValue();
        }

        int idx = 0;

        for (String column: columns) {
            if (column.equals(col)) {
                indexBuffer.put(col, new Integer(idx));
                return idx;
            }
            idx++;
        }

        return -1;
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
