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
package de.bielefeld.umweltamt.aui.module.importer;

import java.io.File;

public class AbwasserImporter extends AbstractImporter {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * Creates instance with default columns
     */
    public AbwasserImporter() {
        this(new String[] {"Kennnummer", "Ordnungsbegriff", "Parameter",
                    "GrKl", "Wert", "Einheit","Analysemethode", "Plausibel"}, false);
    }

    public AbwasserImporter(String[] columns, boolean updateAtInit) {
        super(columns, updateAtInit);
    }

    @Override
    public void updateList() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void parseFile(File file) {
        // TODO Auto-generated method stub

    }

    @Override
    public void doImport(File file) {
        // TODO Auto-generated method stub

    }

}
