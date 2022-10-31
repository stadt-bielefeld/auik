package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Base class for object panels that have their own models that can be saved to
 * the database.
 */
public abstract class ObjectPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    protected boolean dirty;

    /**
     * Add change listeners to the given components.
     *
     * Fields updated by this function will the dirty attribute to be updated
     * if the fields are edited.
     * @param components Components to add listeners to
     */
    protected void addChangeListeners(JComponent... components) {
        for(JComponent component: components) {
            if (component instanceof JCheckBox) {
                ((JCheckBox)component).addItemListener(new CheckBoxDirtyListener(this));
            }
            if (component instanceof JComboBox) {
                ((JComboBox<?>)component).addActionListener(new ComboBoxDirtyListener(this));
            }
            if (component instanceof JTextArea) {
                ((JTextArea) component).getDocument().addDocumentListener(new TextDirtyListener(this));
            }
            if (component instanceof JTextField) {
                ((JTextField) component).getDocument().addDocumentListener(new TextDirtyListener(this));
            }
            if (component instanceof TextFieldDateChooser) {
                ((TextFieldDateChooser) component).addChangeListener(new TextDirtyListener(this));
            }
        }
    }

    /**
     * Get panel name/title
     * @return Name as String
     */
    public abstract String getName();

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    /**
     * Check if model data has been changed.
     * @return True if dirty, else false
     */
    public boolean isDirty() {
        return this.dirty;
    }

    public final boolean savePanelData() {
        setDirty(false);
        return doSavePanelData();
    }

    /**
     * Save the panel data to the database.
     * @return True if saved successfully, else false
     */
    protected abstract boolean doSavePanelData();
    private class CheckBoxDirtyListener implements ItemListener {
        ObjectPanel parent;
        public CheckBoxDirtyListener (ObjectPanel parent) {
            this.parent = parent;
        }
        @Override
        public void itemStateChanged(ItemEvent arg0) {
            parent.setDirty(true);
        }
    }
    private class ComboBoxDirtyListener implements ActionListener {
        ObjectPanel parent;
        public ComboBoxDirtyListener (ObjectPanel parent) {
            this.parent = parent;
        }
        @Override
        public void actionPerformed(ActionEvent arg0) {
            parent.setDirty(true);
        }
    }
    private class TextDirtyListener implements DocumentListener {
        ObjectPanel parent;
        public TextDirtyListener(ObjectPanel parent) {
            this.parent = parent;
        }
        @Override
        public void changedUpdate(DocumentEvent arg0) {
        }
        @Override
        public void insertUpdate(DocumentEvent arg0) {
            parent.setDirty(true);
        }
        @Override
        public void removeUpdate(DocumentEvent arg0) {
            parent.setDirty(true);
        }
    }
}
