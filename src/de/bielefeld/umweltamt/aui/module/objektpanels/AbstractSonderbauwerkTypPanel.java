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

package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

import javax.swing.JPanel;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Abstract class used as base for every Sonderbauwerk type panel
 */
public abstract class AbstractSonderbauwerkTypPanel extends JPanel{

    private static final long serialVersionUID = 0L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Map instance, mapping fields and model records
     * String key: variable name of the form field
     * RecordMap: 
     *   name: Variable name of the record field to map
     *   type: Fully qualified class name of the field to map
     */
    protected Map<String, RecordMap> fieldMapping;

    /**
     * Panel name
     */
    protected String name;

    /**
     * Underlying sonderbauwerk record
     */
    protected Sonderbauwerk record;

    /**
     * Get value of a field by field name
     * @param fieldName Field name as String
     * @return Object containing field name
     */
    public abstract Object getFieldValue(String fieldName);

    /**
     * Parses a string as a BigDecimal value
     * @param value BigDecimal as String
     * @return Value as String or null if empty String is given
     */
    public BigDecimal parseBigDecimalFromString(String value) {
        if (value == null || value.equals("")) {
            return null;
        } else {
            return new BigDecimal(value);
        }
    }

    /**
     * Parses a string as an integer value
     * @param value Integer as String
     * @return Value as String or null if empty String is given
     */
    public Integer parseIntegerFromString(String value) {
        if (value == null || value.equals("")) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }

    /**
     * Save data to model.
     * Note: No database interactions are performed during this function.
     */
    public void save() {
        fieldMapping.forEach((formField, recordMap) -> {
            String recordName = recordMap.getName();
            String recordType = recordMap.getType();
            try {
                //Guess setter name and get method instance
                String methodName = "set" 
                        + recordName.substring(0, 1).toUpperCase()
                        + recordName.substring(1);
                Class setParam = Class.forName(recordType);
                Method recordMethod = record.getClass().getMethod(methodName, setParam);
                recordMethod.invoke(record, getFieldValue(formField));
            } catch (ClassNotFoundException cnf) {
                log.error("Could not find datatype: " + recordType);
            }
            catch (NoSuchMethodException nsm) {
                log.error("Unkown record field: " + recordName);
            }
            catch (InvocationTargetException it) {
                log.error("Invalid method: " + recordName);
            }
            catch (IllegalAccessException ia) {
                log.error("Illegal access");
            }
        });
    }

    /**
     * Get the panel name
     * @return Panel name as String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the record for this panel
     * @param record Sonderbauwerk record
     */
    public void setRecord(Sonderbauwerk record) {
        this.record = record;
    }

    /**
     * Utility class containing name and datatype of a record
     */
    protected class RecordMap {

        /**
         * Record field name
         */
        private String name;

        /**
         * Record datatype
         * Should be fully qualified class name.
         */
        private String type;

        public RecordMap(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * Item class containing key and displayValue for a combobox
     */
    protected class CBoxItem {

        private Integer id;

        private String value;

        public CBoxItem(Integer id, String value) {
            this.id = id;
            this.value = value;
        }

        public Integer getId() {
            return this.id;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return this.value;
        }
    }
}