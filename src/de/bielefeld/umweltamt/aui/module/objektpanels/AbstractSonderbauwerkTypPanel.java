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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.CBoxItem;

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
     * Add a new mapping for formfields and model attributes
     * @param fieldName Form field name
     * @param modelAttributeName Model attribute field name
     * @param modelAttributeDatatype Model attribute field data type
     */
    protected void addMapping(String fieldName, String modelAttributeName, String modelAttributeDatatype) {
        this.fieldMapping.put(fieldName, new RecordMap(modelAttributeName, modelAttributeDatatype));
    }

    public abstract void fetchFormData();

    /**
     * Get value of a field by field name
     * @param fieldName Field name as String
     * @return Object containing field name
     */
    public abstract Object getFieldValue(String fieldName);

    /**
     * Get value of a field by field name using the given class.
     * Note: This method relies on the existence of a getter method for the
     * field.
     * @param fieldName Field name as String
     * @param context Panel subclass containing the fields.
     * @return Object containing field value
     */
    protected Object getFieldValue(String fieldName, AbstractSonderbauwerkTypPanel context) {
        Object returnValue = null;
        String methodName = "";
        try {
            log.debug(context);
            methodName = "get"
                    + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1);
            Method getMethod = context.getClass().getMethod(methodName);
            Object field = getMethod.invoke(context);
            if (field.getClass() == JComboBox.class) {
                JComboBox<CBoxItem> combo = (JComboBox<CBoxItem>) field;
                returnValue = ((CBoxItem) combo.getSelectedItem()).getId();
            } else if (field.getClass() == JCheckBox.class) {
                JCheckBox check = (JCheckBox) field;
                returnValue = check.isSelected();
            } else {
                String type = this.fieldMapping.get(fieldName).getType();
                String stringValue = (String) ((JTextField)field).getText();
                switch (type) {
                    case "java.lang.Integer":
                    returnValue = parseIntegerFromString(stringValue);
                        break;
                    case "java.math.BigDecimal":
                    returnValue = parseBigDecimalFromString(stringValue);
                        break;
                    default:
                    returnValue = stringValue;
                }
            }
        } catch (NoSuchMethodException nsme) {
            throw new IllegalArgumentException("Can not find getter method for field " + fieldName
                                               + ". Expected method name: " + methodName);
        } catch (InvocationTargetException ite) {
            log.error(ite.getStackTrace());
        } catch (IllegalAccessException iae) {
            log.error(iae.getStackTrace());
        }
        return returnValue;
    }

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
     * Set the value of a textfield
     * @param textfield Textfield to set
     * @param value Object which will be set as value
     */
    public void setTextFieldContent(JTextField textfield, Object value) {
        if (value == null) {
            return;
        }
        textfield.setText(value.toString());
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
}