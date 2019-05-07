package de.bielefeld.umweltamt.aui.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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

/**
 * Wrapper class for some GridBagLayout functions for building a panel.
 * See GridBagLayout JavaDoc for further details.
 */
public class PanelBuilder {
    private JPanel panel;
    private GridBagConstraints c;
    private GridBagLayout layout;
    private Border componentBorder;

    public PanelBuilder() {
        this.panel = new JPanel();
        this.c = new GridBagConstraints();
        this.layout = new GridBagLayout();
        panel.setLayout(this.layout);
        this.componentBorder = null;
    }

    /**
     * Set the next components external padding.
     * @param insets New Insets
     */
    public void setInsets(Insets insets) {
        this.c.insets = insets;
    }

    public Insets getInsets() {
        return this.c.insets;
    }

    /**
     * Set the components border
     * @param border New border
     */
    public void setComponentBorder(Border border) {
        this.componentBorder = border;
    }

    /**
     * Set the components internal vertical padding.
     * @param ipady
     */
    public void setIpadY(int ipady) {
        this.c.ipady = ipady;
    }

    /**
     * Set the result panel border.
     * @param border
     */
    public void setBorder(Border border) {
        this.panel.setBorder(border);
    }

    /**
     * Set the components number of cells in grid row
     * @param gridWidth Next gridwidth
     */
    public void setGridWidth(int gridWidth) {
        this.c.gridwidth = gridWidth;
    }

    /**
     * Set the components number of cell in a grid column
     * @param gridHeight Next gridheight
     */
    public void setGridHeight(int gridHeight) {
        this.c.gridheight = gridHeight;
    }

    /**
     * Set the anchor for the next component
     * @param anchor Anchor diretion. Use GridBagConstraints.NORTH etc.
     */
    public void setAnchor(int anchor) {
        this.c.anchor = anchor;
    }

    /**
     * Set the fill for the next component
     * @param fill Fill direction. Use GridBagConstraints.HORIZONTAl etc.
     */
    public void setFill(int fill) {
        this.c.fill = fill;
    }

    /**
     * Set the weightX and weightY for the next component
     * @param weightX Horizontal weight of the next component
     * @param weightY Vertical weight of the next component
     */
    public void setWeight(double weightX, double weightY) {
        this.c.weightx = weightX;
        this.c.weighty = weightY;
    }

    /**
     * Set the weightX for the next component
     * @param weightx weight as double
     */
    public void setWeightX(double weightx) {
        this.c.weightx = weightx;
    }

    /**
     * Set the vertical weight for the next component
     * @param weighty Next weighty
     */
    public void setWeightY(double weighty) {
        this.c.weighty = weighty;
    }

    /**
     * Add a separator with the given orienation
     * @param orientation Orientation, use JSeparator.HORIZONTAl etc.
     */
    public void addSeparator(int orientation) {
        addSeparator(orientation, false);
    }

    /**
     * Adds a label separator
     * @param orienation Orientation, use JSeparator.HORIZONTAl etc.
     * @param label Label text
     */
    public void addSeparator(int orientation, String label) {
        addSeparator(orientation, label, false);
    }

    /**
     * Add a separator with the given orienation
     * @param orientation Orientation, use JSeparator.HORIZONTAl etc.
     * @param endRow If true, row will be ended after add the separator
     */
    public void addSeparator(int orientation, boolean endRow) {
        addComponent(new JSeparator(orientation), endRow);
    }

    /**
     * Adds an empty JPanel that fills up the rest of the row.
     * Use this to prevent the component centering.
     */
    public void fillRow() {
        fillRow(false);
    }

    /**
     * Adds an empty JPanel that fills up the rest of the row.
     * Use this to prevent the component centering.
     * @param endRow If true, end the row
     */
    public void fillRow(boolean endRow) {
        double weightX = this.c.weightx;
        setWeightX(1);
        addComponent(new JPanel(), endRow);
        setWeightX(weightX);
    }

    /**
     * Add a labeled separator with the given orienation
     * @param orientation Orientation, use JSeparator.HORIZONTAl etc.
     * @param label Label text
     * @param endRow If true, row will be ended after add the separator
     */
    public void addSeparator(int orientation, String label, boolean endRow) {
        double weightx = this.c.weightx;
        int fill = this.c.fill;
        Insets insets = this.c.insets;
        this.setWeightX(0);
        this.setFill(GridBagConstraints.NONE);
        this.setInsets(new Insets(0, insets.top, insets.bottom, 5));
        addComponent(new JLabel(label));
        this.setInsets(insets);
        this.setWeightX(weightx);
        this.setFill(fill);
        addSeparator(orientation, endRow);
    }

    /**
     * Adds a labeled component
     * @param comp Component to add
     * @param label Label text
     */
    public void addComponent(JComponent comp, String label) {
        double weightx = this.c.weightx;
        int fill = this.c.fill;
        Insets insets = this.c.insets;
        this.setWeightX(0);
        this.setFill(GridBagConstraints.NONE);
        this.setInsets(new Insets(0, insets.top, insets.bottom, 5));
        addComponent(new JLabel(label));
        this.setInsets(insets);
        this.setWeightX(weightx);
        this.setFill(fill);
        addComponent(comp);
    }

    /**
     * Adds a labeled component.
     * @param comp Component to add
     * @param label Label to add
     * @param endRow Ends the row, if true
     */
    public void addComponent(JComponent comp, String label, boolean endRow) {
        double weightx = this.c.weightx;
        int fill = this.c.fill;
        Insets insets = this.c.insets;
        this.setWeightX(0);
        this.setFill(GridBagConstraints.NONE);
        this.setInsets(new Insets(0, insets.top, insets.bottom, 5));
        addComponent(new JLabel(label));
        this.setInsets(insets);
        this.setWeightX(weightx);
        this.setFill(fill);
        addComponent(comp, endRow);
    }

    /**
     * Adds a Component
     * @param comp Component to add
     */
    public void addComponent(JComponent comp) {
        addComponent(comp, false);
    }

    /**
     * Adds a Component.
     * @param comp Component to add.
     * @param endRow Ends the row, if true.
     */
    public void addComponent(JComponent comp, boolean endRow) {
        int gridwidth = this.c.gridwidth;
        if (endRow == true) {
            this.c.gridwidth = GridBagConstraints.REMAINDER;
        }

        layout.setConstraints(comp, this.c);
        if (this.componentBorder != null) {
            comp.setBorder(this.componentBorder);
        }
        this.panel.add(comp);
        this.setGridWidth(gridwidth);
    }

    /**
     * Adds multiple components.
     * @param comps List of components.
     */
    public void addComponents(JComponent... comps) {
        for (JComponent comp: comps) {
            addComponent(comp);
        }
    }

    /**
     * Adds multiple Components.
     * @param endRow If true, end the row after the last component
     * @param comps List of components
     */
    public void addComponents(boolean endRow, JComponent... comps) {
        for (int i = 0; i < comps.length; i++) {
            if (i == comps.length - 1 && endRow == true) {
                addComponent(comps[i], true);
            } else {
                addComponent(comps[i]);
            }
        }
    }

    /**
     * Return the panel.
     * @return The panel
     */
    public JPanel getPanel() {
        return this.panel;
    }
 }