/*
 * Created on 28.02.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 * Implements a cell editor that uses a formatted text field
 * to edit Double values.
 * Basiert auf dem IntegerEditor aus dem Java-Tutorial "How to use Tables"
 * @see http://java.sun.com/docs/books/tutorial/uiswing/components/table.html#validtext
 */
public class DoubleEditor extends DefaultCellEditor {
    JFormattedTextField ftf;
    NumberFormat doubleFormat;
    private boolean DEBUG = false;

    public DoubleEditor() {
        super(new JFormattedTextField());
        ftf = (JFormattedTextField)getComponent();

        //Set up the editor for the double cells.
        doubleFormat = NumberFormat.getNumberInstance();
        NumberFormatter doubleFormatter = new NumberFormatter(doubleFormat);
        doubleFormatter.setFormat(doubleFormat);

        ftf.setFormatterFactory(
                new DefaultFormatterFactory(doubleFormatter));
        ftf.setValue(new Double(0.0));
        ftf.setHorizontalAlignment(JTextField.TRAILING);
        ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);
        setBorderNormal();

        //React when the user presses Enter while the editor is
        //active.  (Tab is handled as specified by
        //JFormattedTextField's focusLostBehavior property.)
        ftf.getInputMap().put(KeyStroke.getKeyStroke(
                                        KeyEvent.VK_ENTER, 0),
                                        "check");
        ftf.getActionMap().put("check", new AbstractAction() {
        	public void actionPerformed(ActionEvent e) {
        		if (!ftf.isEditValid()) { //The text is invalid.
        			setBorderRed();
        			if (DEBUG) {
                        System.out.println("check: '" + ftf.getText() + "' is invalid!");
                    }
        		} else try {              //The text is valid,
        			ftf.commitEdit();     //so use it.
        			setBorderNormal();
        			ftf.postActionEvent(); //stop editing
        		} catch (java.text.ParseException exc) { }
        	}
        });
    }

    //Override to invoke setValue on the formatted text field.
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected,
            int row, int column) {
        JFormattedTextField ftf =
            (JFormattedTextField)super.getTableCellEditorComponent(
                table, value, isSelected, row, column);
        ftf.setValue(value);
        if (DEBUG) {
        	System.out.println("getTableCellEditorComp: " + isSelected + ", " + row + ", " + column);
        }
        return ftf;
    }

    //Override to ensure that the value remains a Double.
    public Object getCellEditorValue() {
        JFormattedTextField ftf = (JFormattedTextField)getComponent();
        Object o = ftf.getValue();
        if (o instanceof Double) {
            return o;
        } else if (o instanceof Number) {
            return new Double(((Number)o).doubleValue());
        } else {
            if (DEBUG) {
                System.out.println("getCellEditorValue: o isn't a Number");
            }
            try {
                return doubleFormat.parseObject(o.toString());
            } catch (ParseException exc) {
                System.err.println("getCellEditorValue: can't parse o: " + o);
                return null;
            }
        }
    }

    //Override to check whether the edit is valid,
    //setting the value if it is and complaining if
    //it isn't.  If it's OK for the editor to go
    //away, we need to invoke the superclass's version 
    //of this method so that everything gets cleaned up.
    public boolean stopCellEditing() {
    	JFormattedTextField ftf = (JFormattedTextField)getComponent();
    	if (ftf.isEditValid()) {
    		setBorderNormal();
    		try {
    			ftf.commitEdit();
    		} catch (java.text.ParseException exc) { }
    		
    	} else { //text is invalid
    		setBorderRed();
    		if (DEBUG) {
                System.out.println("stopCellEditing: '" + ftf.getText() + "' is invalid!");
            }
    		return false; //don't let the editor go away
    	}
    	return super.stopCellEditing();
    }
    
    public void setBorderNormal() {
    	ftf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public void setBorderRed() {
    	ftf.setBorder(BorderFactory.createLineBorder(Color.RED));
    }
}
