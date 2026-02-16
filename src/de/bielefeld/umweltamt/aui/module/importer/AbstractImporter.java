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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public abstract class AbstractImporter extends ListTableModel<String[]> {

    private static final long serialVersionUID = 1L;

    protected static final String COLOR_RED = "FF1027";
    protected static final String COLOR_ORANGE = "ff6600";
    protected static final String COLOR_GREEN = "009900";

    /**
     * File to import.
     */
    protected File importFile;

    /**
     * Parent table of this table model.
     */
    protected JTable parentTable;

    /**
     * Map of id numbers - import Results.
     */
    protected Map<String, ImportResult> importResults;

    /**
     * Number of rows imported successfully.
     */
    protected int resultCount;

    public AbstractImporter(String[] columns, boolean updateAtInit) {
        super(columns, updateAtInit);
    }

    /**
     * Save data to database.
     * @throws ImporterException Thrown if an error occured during saving
     */
    public abstract void doImport() throws ImporterException;

    /**
     * Get the string describing the different row colors.
     * @return Description string
     */
    public abstract String getDescriptionString();

    /**
     * Check if a row is selectable
     * @param rowIndex Row index
     * @return True if selectable, else false
     */
    public abstract boolean isRowSelectable(int rowIndex);

    /**
     * Parse import file and fill table.
     * @param file File to import
     * @throws ImporterException Thrown if parsing fails
     */
    public abstract void parseFile(File file) throws ImporterException;

    /**
     * Reset the importer table.
     */
    public abstract void reset();

    /**
     * Set the parent table
     * @param parenTable Parent table
     */
    public void setParentTable(JTable parentTable) {
        this.parentTable = parentTable;
    }

    /**
     * Create a result html string from the stored result objects.
     * @return Html string
     */
    public String createResultHtml() {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("<html>");
        importResults.forEach((kennnummer, result) -> {
            resultBuilder.append(
                String.format("<b>Kennnummer: %s </b><br>", result.getKennnummer()))
            .append("<ul>")
            .append(String.format(
                    "<li>erfolgreich importiert: %d</li>",
                    result.getSuccess().size()));
            if(result.getSkipped().size() > 0) {
                String skipped = String.join(", ", result.getSkipped());
                resultBuilder.append(String.format("<li>übersprungen: %s</li>", skipped));
            }
            if(result.getErrors().size() > 0) {
                resultBuilder.append("<li>Fehler:<br><ul>");
                result.getErrors().forEach((param, error) -> {
                    resultBuilder.append(String.format("<li>%s - %s</li>", param, error));
                });
                resultBuilder.append("</ul></li>");
            }
            resultBuilder.append("</ul>");

        });
        resultBuilder.append("</html>");
        return resultBuilder.toString();
    }

    /**
     * Get the number of rows imported successfully
     * @return
     */
    public int getResultCount() {
        return this.resultCount;
    }

    public class ImporterException extends Exception {
        private String msg;
        private static final long serialVersionUID = 1L;
        public ImporterException(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return this.msg;
        }
    }

    /**
     * Class used to store import results;
     */
    public class ImportResult {
        private String kennnummer;
        private List<String> success;
        private List<String> skipped;
        private Map<String, String> errors;
        public ImportResult(String kennnummer) {
            this.kennnummer = kennnummer;
            success = new ArrayList<String>();
            skipped = new ArrayList<String>();
            errors = new HashMap<String, String>();
        }
        public void addError(String param, String error) {
            this.errors.put(param, error);
        }
        public String getKennnummer() {
            return kennnummer;
        }
        public void addSuccess(String param) {
            this.success.add(param);
        }
        public void addSkipped(String param) {
            this.skipped.add(param);
        }
        public List<String> getSuccess() {
            return success;
        }
        public List<String> getSkipped() {
            return skipped;
        }
        public Map<String, String> getErrors() {
            return errors;
        }
    }

}
