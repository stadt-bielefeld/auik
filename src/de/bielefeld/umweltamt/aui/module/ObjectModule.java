package de.bielefeld.umweltamt.aui.module;

import de.bielefeld.umweltamt.aui.AbstractModul;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
/**
 * Abstract module class that have their own models that can be saved to
 * the database.
 */
public abstract class ObjectModule extends AbstractModul {

    protected boolean dirty = false;

    protected abstract void doSave();

    public void save() {
        doSave();
        setDirty(false);
    }

    /**
     * Get dirty state
     * @return True if module contains unsaved changes, else false.
     */
    public boolean isDirty() {
        return this.dirty;
    }

    protected void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    /**
     * Add change listeners to the given components.
     *
     * Fields updated by this function will set the dirty attribute
     * if the fields are edited.
     * @param components Components to add listeners to
     */
    protected void addChangeListeners(JComponent... components) {
        for(JComponent component: components) {
            if (component instanceof JCheckBox) {
                ((JCheckBox)component)
                    .addItemListener(new ModuleCheckBoxDirtyListener(this));
            }
            if (component instanceof JComboBox) {
                ((JComboBox<?>)component)
                    .addActionListener(new ModuleComboBoxDirtyListener(this));
            }
            if (component instanceof JTextArea) {
                ((JTextArea) component).getDocument()
                    .addDocumentListener(new ModuleTextDirtyListener(this));
            }
            if (component instanceof JTextField) {
                ((JTextField) component).getDocument()
                    .addDocumentListener(new ModuleTextDirtyListener(this));
            }
            if (component instanceof TextFieldDateChooser) {
                ((TextFieldDateChooser) component)
                    .addChangeListener(new ModuleTextDirtyListener(this));
            }
        }
    }

    protected class ModuleCheckBoxDirtyListener implements ItemListener {
        ObjectModule parent;
        public ModuleCheckBoxDirtyListener (ObjectModule parent) {
            this.parent = parent;
        }
        @Override
        public void itemStateChanged(ItemEvent arg0) {
            parent.setDirty(true);
        }
    }
    protected class ModuleComboBoxDirtyListener implements ActionListener {
        ObjectModule parent;
        public ModuleComboBoxDirtyListener (ObjectModule parent) {
            this.parent = parent;
        }
        @Override
        public void actionPerformed(ActionEvent arg0) {
            parent.setDirty(true);
        }
    }
    protected class ModuleTextDirtyListener implements DocumentListener {
        ObjectModule parent;
        public ModuleTextDirtyListener(ObjectModule parent) {
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
