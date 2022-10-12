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

import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public abstract class AbstractImporter extends ListTableModel {

    public AbstractImporter(String[] columns, boolean updateAtInit) {
        super(columns, updateAtInit);
        //TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    public abstract void parseFile(File file);

    public abstract void doImport(File file);
}
