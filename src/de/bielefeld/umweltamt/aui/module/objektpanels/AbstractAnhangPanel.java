/**
 * Copyright 2005-2042, Stadt Bielefeld
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

import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * An abstract class for the "Anhang" panels which holds all components in a
 * map and is used to handle things which have to be done to all components.
 * 
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public abstract class AbstractAnhangPanel extends JPanel {
	private static final long serialVersionUID = 783029677516911773L;

	/** Logging */
    protected final AuikLogger log = AuikLogger.getLogger();

    /** The name of the Anhang panel */
    protected String name;
    /** The main view module */
    protected BasisObjektBearbeiten hauptModul;
    
    /** HashMap for all the components */
    private HashMap<String, JComponent> componentMap;

    /**
     * Simple constructor
     * @param name The name of the "Anhang"
     * @param hauptModul The main view module
     */
    public AbstractAnhangPanel(String name, BasisObjektBearbeiten hauptModul) {
    	this.name = name;
    	this.hauptModul = hauptModul;
    	this.componentMap = new HashMap<String, JComponent>();
    }

    /**
     * Add a new component to the map.<br>
     * If we did not have a component with the given name before, true is
     * returned, otherwise the old component is replaced with the new component
     * and false is returned. 
     * 
     * @param name The name of the new component
     * @param component The new component
     * @return if the component was new
     */
    public boolean addComponent(String name, JComponent component) {
    	/* This is was the code below does in long ;-)
    	boolean wasNewComponent = false;
    	JComponent oldComponent = null;
    	oldComponent = this.componentMap.put(name, component);
    	if (oldComponent == null) {
    		wasNewComponent = true;
    	}
    	return wasNewComponent;
    	*/
    	return (this.componentMap.put(name, component) == null ? true : false);	
    }
    
    /**
     * Clear all components in the map, that is set them to null or equivalent
     * @return True if all component were known
     */
    protected boolean clearAllComponents() {
    	/* Go through all components */
    	for (JComponent component : this.componentMap.values()) {
    		/* "Switch" on the class and set to null or equivalent */
    		if (component instanceof JTextField) {
    			((JTextField) component).setText(null);
    		} else if (component instanceof JCheckBox) {
    			((JCheckBox) component).setSelected(false);
    		} else if (component instanceof TextFieldDateChooser) {
    			((TextFieldDateChooser) component).setDate(null);
    		} else if (component instanceof JTextArea) {
    			((JTextArea) component).setText(null);
    		} else if (component instanceof JButton) {
    			/* This place is intentionally left blank. */
    		} else {
    			log.warn("Unknown JComponent [" + component.getClass().getSimpleName() + "] used.");
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Set all components of the map to {@link enabled}
     * @param enabled true if the components of the map should be enabled
     */
    protected void setAllComponentsEnabled(boolean enabled) {
    	/* Go through all components */
    	for (JComponent component : this.componentMap.values()) {
    		component.setEnabled(enabled);
    	}
    }
    
    /**
     * Get the value of a component.
     * @param name The name of the component
     * @return The value of the component
     */
    protected Object getComponentValue(String name) {
    	JComponent component = this.componentMap.get(name);
		/* "Switch" on the class and return the value */
		if (component instanceof JTextField) {
			return setBlankToNull(((JTextField) component).getText());
		} else if (component instanceof JCheckBox) {
			return ((JCheckBox) component).isSelected();
		} else if (component instanceof TextFieldDateChooser) {
			return ((TextFieldDateChooser) component).getDate();
		} else if (component instanceof JTextArea) {
			return setBlankToNull(((JTextArea) component).getText());
		} else if (component instanceof JButton) {
			log.warn("Tried to get the value of a JButton.");
		} else {
			log.warn("Unknown JComponent [" + component.getClass() + "] used.");
		}
		return null;
    }
    
    /**
     * Set the value of a component
     * @param name The name of the component to modify
     * @param value The new value
     * @return If the given value was null or the component is unknown
     */
    protected boolean setComponentValue(String name, Object value) {
    	if (value == null) {
    		return false;
    	}
    	JComponent component = this.componentMap.get(name);
		/* "Switch" on the class and set to the value */
		if (component instanceof JTextField) {
			((JTextField) component).setText((String)value);
		} else if (component instanceof JCheckBox) {
			((JCheckBox) component).setSelected((Boolean)value);
		} else if (component instanceof TextFieldDateChooser) {
			((TextFieldDateChooser) component).setDate((Date)value);
		} else if (component instanceof JTextArea) {
			((JTextArea) component).setText((String)value);
		} else if (component instanceof JButton) {
			log.warn("Tried to set the value of a JButton.");
			return false;
		} else {
			log.warn("Unknown JComponent [" + component.getClass() + "] used.");
			return false;
		}
		return true;
    }

    /**
     * Get a component out of the map
     * @param name The name of the component
     * @return The requested component
     */
    protected JComponent getComponent(String name) {
		return this.componentMap.get(name);
    }

    /**
     * If the given string is "" null is returned otherwise the string itself
     * 
     * TODO: This handling of "" values should be shorted out in the database
     * part and not the GUI (MVC and so...)
     * 
     * @param string The input string
     * @return (string == "") ? null : string
     */
    private String setBlankToNull(String string) {
    	if ("".equals(string)) {
    		return null;
    	}
    	return string;
    }

    /* Getter */
    public String getName() {
        return name;
    }
}
