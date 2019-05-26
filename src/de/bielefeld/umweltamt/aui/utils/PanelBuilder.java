package de.bielefeld.umweltamt.aui.utils;

import java.awt.Color;
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
    /**
     * Panel to be constructed
     */
    private JPanel panel;

    /**
     * Layout constraints
     */
    private GridBagConstraints c;

    /**
     * Layout object
     */
    private GridBagLayout layout;

    /**
     * Border to be applied to components
     */
    private Border componentBorder;

    /**
     * Integer to set the containing panel type
     */
    private int panelType;

    /**
     * Panel type with a gradient background
     */
    public static final int GRADIENT_PANEL = 0;

    /**
     * Default Panel with solid background
     */
    public static final int DEFAULT_PANEL = 1;

    /**
     * Anchor values
     */
    public static final int NORTH = GridBagConstraints.NORTH;
    public static final int NORTHWEST = GridBagConstraints.NORTHWEST;
    public static final int WEST = GridBagConstraints.WEST;
    public static final int SOUTHWEST = GridBagConstraints.SOUTHWEST;
    public static final int SOUTH = GridBagConstraints.SOUTH;
    public static final int SOUTHEAST = GridBagConstraints.SOUTHEAST;
    public static final int EAST = GridBagConstraints.EAST;
    public static final int NORTHEAST = GridBagConstraints.NORTHEAST;
    public static final int CENTER = GridBagConstraints.CENTER;

    /**
     * Copy constructor, copies the given builders setttings.
     * @param builder PanelBuilder to copy settings from
     */
    public PanelBuilder(PanelBuilder builder) {
        this(builder.getPanelType());
        this.setAnchor(builder.getAnchor());
        this.setBorder(builder.getBorder());
        this.setComponentBorder(builder.getComponentBorder());
        this.setFill(builder.getFill());
        this.setGridHeight(builder.getGridHeight());
        this.setGridWidth(builder.getGridWidth());
        this.setInsets(builder.getInsets());
        this.setWeight(builder.getWeightX(), builder.getWeightY());
    }

    /**
     * Full constructor
     * @param panelType Set to GRADIENT_PANEL to use a gradient background color
     * @param anchor Anchor location, see setAnchor
     * @param hfill Fill horizontal if true
     * @param vfill Fill vertical if true
     * @param weightX Horizontal weight
     * @param weightY Vertical weight
     * @param width GridWidth, see setGridWith
     * @param height GridHeight, see setGridHeight
     * @param insetTop Top component padding
     * @param insetLeft Left component padding
     * @param insetBottom Bottom component padding
     * @param insetRight Right component padding
     */
    public PanelBuilder(int panelType, int anchor, boolean hfill, boolean vfill,
            double weightX, double weightY, int width, int height,
            int insetTop, int insetLeft, int insetBottom, int insetRight) {
        this(panelType);
        setAnchor(anchor);
        setFill(hfill, vfill);
        setWeight(weightX, weightY);
        setGridWidth(width);
        setGridHeight(height);
        setInsets(insetTop, insetLeft, insetBottom, insetRight);
    }

    /**
     * Full constructor with default panel
     * @param anchor Anchor location, see setAnchor
     * @param hfill Fill horizontal if true
     * @param vfill Fill vertical if true
     * @param weightX Horizontal weight
     * @param weightY Vertical weight
     * @param width GridWidth, see setGridWith
     * @param height GridHeight, see setGridHeight
     * @param insetTop Top component padding
     * @param insetLeft Left component padding
     * @param insetBottom Bottom component padding
     * @param insetRight Right component padding
     */
    public PanelBuilder(int anchor, boolean hfill, boolean vfill,
            double weightX, double weightY, int width, int height,
            int insetTop, int insetLeft, int insetBottom, int insetRight) {
        this(DEFAULT_PANEL, anchor, hfill, vfill, weightX, weightY, width, height,
                insetTop, insetLeft, insetBottom, insetRight);
    }

    /**
     * Constructor
     * @param panelType Set to GRADIENT_PANEL to use a gradient background color
     */
    public PanelBuilder(int panelType) {
        this.panelType = panelType;
        this.c = new GridBagConstraints();
        this.layout = new GridBagLayout();
        switch (panelType) {
            case GRADIENT_PANEL:
                this.panel = new GradientPanel(this.layout, new Color(186, 211, 237), new JPanel().getBackground());
            break;
            default: 
                this.panel = new JPanel();
        }

        panel.setLayout(this.layout);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.componentBorder = null;
    }

    public PanelBuilder() {
        this(DEFAULT_PANEL);
    }

    /**
     * Return the type of the built panel
     * @return Panel type
     */
    public int getPanelType() {
        return this.panelType;
    }

    /**
     * Sets the next components external padding
     * @param insets Padding to use in every direction
     */
    public void setInsets(int insets) {
        this.setInsets(new Insets(insets, insets, insets, insets));
    }

    /**
     * Sets the next components external padding
     * @param top Top padding
     * @param left Left padding
     * @param bottom bottom padding
     * @param right right padding
     */
    public void setInsets(int top, int left, int bottom, int right) {
        this.setInsets(new Insets(top, left, bottom, right));
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
     * Returns the component border
     * @return The component border
     */
    public Border getComponentBorder() {
        return this.componentBorder;
    }

    /**
     * Set the components border
     * @param border New border
     */
    public void setComponentBorder(Border border) {
        this.componentBorder = border;
    }

    /**
     * Return components internal vertical padding.
     * @return vertical padding as int
     */
    public int getIpadY() {
        return this.c.ipady;
    }

    /**
     * Set the components internal vertical padding.
     * @param ipady
     */
    public void setIpadY(int ipady) {
        this.c.ipady = ipady;
    }

    /**
     * Return the panels border
     * @return The border instance
     */
    public Border getBorder() {
        return this.panel.getBorder();
    }

    /**
     * Adds an empty border with given thickness to the panel.
     * @param border Border thickness
     */
    public void setEmptyBorder(int border) {
        this.setBorder(new EmptyBorder(border, border, border, border));
    }

    /**
     * Set the result panel border.
     * @param border
     */
    public void setBorder(Border border) {
        this.panel.setBorder(border);
    }

    /**
     * Get the grid with
     * @return The gridwidth as int
     */
    public int getGridWidth() {
        return this.c.gridwidth;
    }

    /**
     * Set the components number of cells in grid row
     * @param gridWidth Next gridwidth
     */
    public void setGridWidth(int gridWidth) {
        this.c.gridwidth = gridWidth;
    }

    /**
     * Get the grid height
     * @return Grid height as int
     */
    public int getGridHeight() {
        return this.c.gridheight;
    }

    /**
     * Set the components number of cell in a grid column
     * @param gridHeight Next gridheight
     */
    public void setGridHeight(int gridHeight) {
        this.c.gridheight = gridHeight;
    }

    /**
     * Get the current Anchor
     * @return The anchor as int
     */
    public int getAnchor() {
        return this.c.anchor;
    }

    /**
     * Set the anchor for the next component
     * @param anchor Anchor diretion. Use GridBagConstraints.NORTH or PanelBuilder.NORTH etc.
     */
    public void setAnchor(int anchor) {
        this.c.anchor = anchor;
    }

    /**
     * Get the current fill.
     * @return The fill as int
     */
    public int getFill() {
        return this.c.fill;
    }

    /**
     * Set the fill for the next component
     * @param fill Fill direction. Use GridBagConstraints.HORIZONTAl etc.
     */
    public void setFill(int fill) {
        this.c.fill = fill;
    }

    /**
     * Set the fill.
     * @param horizontal If true, activate horizontal fill
     * @param vertical if true activate vertical fill
     */
    public void setFill(boolean horizontal, boolean vertical) {
        if (horizontal && vertical) {
            this.setFill(GridBagConstraints.BOTH);
        } else {
            if (horizontal) {
                this.setFill(GridBagConstraints.HORIZONTAL);
            }
            if (vertical) {
                this.setFill(GridBagConstraints.VERTICAL);
            }
            if (!vertical && !horizontal) {
                this.setFill(GridBagConstraints.NONE);
            }
        }
    }

    public void disableFill() {
        this.setFill(GridBagConstraints.NONE);
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
     * Get the current horizontal weight.
     * @return The weight as double
     */
    public double getWeightX() {
        return this.c.weightx;
    }

    /**
     * Set the weightX for the next component
     * @param weightx weight as double
     */
    public void setWeightX(double weightx) {
        this.c.weightx = weightx;
    }

    /**
     * Get the current vertical weight.
     * @return The weight as double
     */
    public double getWeightY() {
        return this.c.weighty;
    }

    /**
     * Set the vertical weight for the next component
     * @param weighty Next weighty
     */
    public void setWeightY(double weighty) {
        this.c.weighty = weighty;
    }


    /**
     * Adds an empty JPanel that fills up the rest of the column.
     * Use this to prevent vertical component centering.
     */
    public void fillColumn() {
        fillColumn(false);
    }

    /**
     * Adds an empty JPanel that fills up the rest of the column.
     * Use this to prevent vertical component centering.
     * @param endRow If true, end the row after adding this component
     */
    public void fillColumn(boolean endRow) {
        double weightY =this.c.weighty;
        setWeightY(1);
        addComponent(new JPanel(), endRow);
        setWeightY(weightY);
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
        JPanel p = new JPanel();
        p.setOpaque(false);
        addComponent(p, endRow);
        setWeightX(weightX);
    }

    /**
     * Adds a horizontal separator.
     */
    public void addSeparator() {
        addSeparator(JSeparator.HORIZONTAL);
    }

    /**
     * Add a separator with the given orienation
     * @param orientation Orientation, use JSeparator.HORIZONTAl etc.
     */
    public void addSeparator(int orientation) {
        addSeparator(orientation, false);
    }

    /**
     * Adds a labeled, horizontal separator
     * @param label Label text
     */
    public void addSeparator(String label) {
        addSeparator(JSeparator.HORIZONTAL, label);
    }

    /**
     * Adds a horizontal separator
     * @param endRow If true, row will be ended after add the separator
     */
    public void addSeparator(boolean endRow) {
        addSeparator(JSeparator.HORIZONTAL, endRow);
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
     * Add a labeled, horizontal separator with the given orienation
     * @param label Label text
     * @param endRow If true, row will be ended after add the separator
     */
    public void addSeparator(String label, boolean endRow) {
        addSeparator(JSeparator.HORIZONTAL, label, endRow);
    }

    /**
     * Add a separator with the given orienation
     * @param orientation Orientation, use JSeparator.HORIZONTAl etc.
     * @param endRow If true, row will be ended after add the separator
     */
    public void addSeparator(int orientation, boolean endRow) {
        addSeparator(orientation, null, endRow);
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
        int width = this.getGridWidth();
        int anchor = this.c.anchor;
        Insets insets = getInsets();
        PanelBuilder sepPanel = new PanelBuilder();
        if (label != null) {
            sepPanel.setWeightX(0);
            sepPanel.setFill(GridBagConstraints.NONE);
            sepPanel.addComponent(new JLabel(label));
        }

        sepPanel.setFill(true, false);
        sepPanel.setWeightX(1);
        sepPanel.setGridWidth(1);
        sepPanel.setInsets(0, 5, 0, 0);
        sepPanel.setAnchor(GridBagConstraints.WEST);
        sepPanel.addComponent(new JSeparator(orientation), endRow);
        sepPanel.setAnchor(anchor);

        this.setFill(true, false);
        this.setWeightX(1);
        this.addComponent(sepPanel.getPanel(), endRow);

        this.setInsets(insets.top, insets.left, insets.bottom, insets.right);
        this.setGridWidth(width);
        this.setWeightX(weightx);
        this.setFill(fill);

    }

    /**
     * Adds a labeled component
     * @param comp Component to add
     * @param label Label text
     */
    public void addComponent(JComponent comp, String label) {
        addComponent(comp, label, false, false);
    }

    /**
     * Adds a labeled component.
     * @param comp Component to add
     * @param label Label to add
     * @param endRow Ends the row, if true
     */
    public void addComponent(JComponent comp, String label, boolean endRow) {
        addComponent(comp, label, endRow, false);
    }

    /**
     * Adds a labeled component.
     * @param comp Component to add
     * @param label Label to add
     * @param endRow Ends the row, if true
     * @param fill If true, the row is filled with an empty panel
     */
    public void addComponent(JComponent comp, String label, boolean endRow, boolean fill) {
        double weightx = this.c.weightx;
        int gridFill = this.c.fill;
        Insets insets = this.c.insets;
        this.setWeightX(0);
        this.setFill(GridBagConstraints.NONE);
        this.setInsets(new Insets(insets.top, 0, insets.bottom, 5));
        JLabel labelObj = new JLabel(label);
        addComponent(labelObj);
        this.setInsets(insets);
        this.setWeightX(weightx);
        this.setFill(gridFill);
        addComponent(comp, endRow, fill);
    }

    /**
     * Adds a labeled component
     * @param comp Component to add
     * @param label Label Object
     */
    public void addComponent(JComponent comp, JLabel label) {
        addComponent(comp, label, false, false);
    }

    /**
     * Adds a labeled component.
     * @param comp Component to add
     * @param label Label to add
     * @param endRow Ends the row, if true
     */
    public void addComponent(JComponent comp, JLabel label, boolean endRow) {
        addComponent(comp, label, endRow, false);
    }

    /**
     * Adds a labeled component.
     * @param comp Component to add
     * @param label Label to add
     * @param endRow Ends the row, if true
     * @param fill If true, the row is filled with an empty panel
     */
    public void addComponent(JComponent comp, JLabel label, boolean endRow, boolean fill) {
        double weightx = this.c.weightx;
        int gridFill = this.c.fill;
        Insets insets = this.c.insets;
        this.setWeightX(0);
        this.setFill(GridBagConstraints.NONE);
        this.setInsets(new Insets(insets.top, 0, insets.bottom, 5));
        addComponent(label);
        this.setInsets(insets);
        this.setWeightX(weightx);
        this.setFill(gridFill);
        addComponent(comp, endRow, fill);
    }


    /**
     * Adds a Component
     * @param comp Component to add
     */
    public void addComponent(JComponent comp) {
        addComponent(comp, false, false);
    }

    /**
     * Adds a Component.
     * @param comp Component to add.
     * @param endRow Ends the row, if true.
     */
    public void addComponent(JComponent comp, boolean endRow) {
        addComponent(comp, endRow, false);
    }

    /**
     * Adds a Component.
     * @param comp Component to add.
     * @param endRow Ends the row, if true.
     * @param fill If true, the row is filled with an empty panel
     */
    public void addComponent(JComponent comp, boolean endRow, boolean fill) {
        int gridwidth = this.c.gridwidth;

        if (this.componentBorder != null) {
            comp.setBorder(this.componentBorder);
        }
        if (fill == false) {
            if (endRow == true) {
                this.c.gridwidth = GridBagConstraints.REMAINDER;
            }
            layout.setConstraints(comp, this.c);
            this.panel.add(comp);
        } else {
            layout.setConstraints(comp, this.c);
            this.panel.add(comp);
            this.fillRow(endRow);
        }
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