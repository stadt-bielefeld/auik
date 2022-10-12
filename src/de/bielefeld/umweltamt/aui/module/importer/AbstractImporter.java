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

import javax.swing.JTable;

import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public abstract class AbstractImporter extends ListTableModel {

    /**
     * File to import
     */
    protected File file;

    protected JTable parentTable;

    public AbstractImporter(String[] columns, boolean updateAtInit) {
        super(columns, updateAtInit);
    }

    private static final long serialVersionUID = 1L;


    /**
     * Save data to database.
     */
    public abstract void doImport();

    /**
     * Check if a row is selectable
     * @param rowIndex Row index
     * @return True if selectable, else false
     */
    public abstract boolean isRowSelectable(int rowIndex);

    /**
     * Parse import file and fill table.
     * @param file File to import
     */
    public abstract void parseFile(File file);

    public void setParentTable(JTable parentTable) {
        this.parentTable = parentTable;
    }

    public class ImporterParseException extends Exception {
        private static final long serialVersionUID = 1L;
    }
}
