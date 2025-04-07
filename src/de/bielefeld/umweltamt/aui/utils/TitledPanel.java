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

package de.bielefeld.umweltamt.aui.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;

/**
 * JPanel that contains a header panel which can include a title, an icon or a toolbar.
 * Based on the uif_lite SimpleInternalFrame. See https://github.com/JabRef/com.jgoodies.uif_lite
 */
public class TitledPanel extends JPanel {

    private static final long serialVersionUID = 420042L;

    /**
     * The actual panel content
     */
    private JComponent content;

    /**
     * Title label
     */
    private JLabel title;

    /**
     * Header panel
     */
    private JPanel header;

    /**
     * Toolbar
     */
    private JToolBar toolbar;

    /**
     * Create the panel without title and content
     */
    public TitledPanel() {
        this("", null, null, null);
    }

    /**
     * Create the panel with a title
     * @param title Title
     */
    public TitledPanel(String title) {
        this(title, null, null, null);
    }

    /**
     * Create the panel with title and icon
     * @param title Title
     * @param icon Icon
     */
    public TitledPanel(String title, Icon icon) {
        this(title, icon, null, null);
    }

    /**
     * Create the panel with title, icon and toolbar.
     * @param title Title
     * @param icon Icon
     * @param toolbar Toolbar
     */
    public TitledPanel(String title, Icon icon, JToolBar toolbar) {
        this(title, icon, toolbar, null);
    }

    /**
     * Create the panel with title, icon, toolbar and content
     * @param title Title
     * @param icon Icon
     * @param toolbar Toolbar
     * @param component Content component
     */
    public TitledPanel(String title, Icon icon, JToolBar toolbar, JComponent component) {
        super(new BorderLayout());
        this.header = new JPanel(new BorderLayout());

        if (icon != null) {
            this.title = new JLabel(title, icon, SwingConstants.LEADING);
        } else {
            this.title = new JLabel(title, SwingConstants.LEADING);
        }

        GradientPanel gradientPanel = new GradientPanel(new BorderLayout());
        gradientPanel.add(this.title, BorderLayout.WEST);
        this.header.add(gradientPanel, BorderLayout.CENTER);
        if (toolbar != null) {
            this.setToolBar(toolbar);
        }
        if (content != null) {
            this.setContent(content);
        }
        this.add(this.header, BorderLayout.NORTH);
        this.header.setBorder(new RaisedHeaderBorder());
        this.setBorder(new ShadowBorder());
    }

    /**
     * Get the content component
     * @return Content component
     */
    public JComponent getContent() {
        return this.content;
    }

    /**
     * Set the panel content
     * @param content New content
     */
    public void setContent(JComponent content) {
        if (this.content != null) {
            this.remove(this.content);
        }
        this.content = content;
        this.add(this.content, BorderLayout.CENTER);
    }

    /**
     * Get the current icon
     * @return Icon
     */
    public Icon getIcon() {
        return this.title.getIcon();
    }

    /**
     * Set the current icon
     * @param icon New icon
     */
    public void setIcon(Icon icon) {
        this.title.setIcon(icon);
    }

    /**
     * Get the current panel title
     * @return Title as string
     */
    public String getTitle() {
        return this.title.getText();
    }

    /**
     * Set the panel title
     * @param title Title as string
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }

    /**
     * Get the panel toolbar
     * @return The toolbar
     */
    public JToolBar getToolBar() {
        return this.toolbar;
    }

    /**
     * Set the panel toolbar
     * @param toolbar New toolbar
     */
    public void setToolBar(JToolBar toolbar) {
        if (this.toolbar != null) {
            this.header.remove(this.toolbar);
        }
        this.toolbar = toolbar;
        this.header.add(this.toolbar, BorderLayout.EAST);
    }

    /* Two border classes taken from the SimpleInternalFrame class to replicate
     * the previous design
     */

    // A custom border for the raised header pseudo 3D effect.
    private static class RaisedHeaderBorder extends AbstractBorder {

        private static final long serialVersionUID = 420043L;

        private static final Insets INSETS = new Insets(1, 1, 1, 0);

        public Insets getBorderInsets(Component c) { return INSETS; }

        public void paintBorder(Component c, Graphics g,
            int x, int y, int w, int h) {

            g.translate(x, y);
            g.setColor(UIManager.getColor("controlLtHighlight"));
            g.fillRect(0, 0,   w, 1);
            g.fillRect(0, 1,   1, h-1);
            g.setColor(UIManager.getColor("controlShadow"));
            g.fillRect(0, h-1, w, 1);
            g.translate(-x, -y);
        }
    }

    // A custom border that has a shadow on the right and lower sides.
    private static class ShadowBorder extends AbstractBorder {

        private static final long serialVersionUID = 420044L;

        private static final Insets INSETS = new Insets(1, 1, 3, 3);

        public Insets getBorderInsets(Component c) { return INSETS; }

        public void paintBorder(Component c, Graphics g,
            int x, int y, int w, int h) {

            Color shadow        = UIManager.getColor("controlShadow");
            if (shadow == null) {
                shadow = Color.GRAY;
            }
            Color lightShadow   = new Color(shadow.getRed(),
                                            shadow.getGreen(),
                                            shadow.getBlue(),
                                            170);
            Color lighterShadow = new Color(shadow.getRed(),
                                            shadow.getGreen(),
                                            shadow.getBlue(),
                                            70);
            g.translate(x, y);

            g.setColor(shadow);
            g.fillRect(0, 0, w - 3, 1);
            g.fillRect(0, 0, 1, h - 3);
            g.fillRect(w - 3, 1, 1, h - 3);
            g.fillRect(1, h - 3, w - 3, 1);
            // Shadow line 1
            g.setColor(lightShadow);
            g.fillRect(w - 3, 0, 1, 1);
            g.fillRect(0, h - 3, 1, 1);
            g.fillRect(w - 2, 1, 1, h - 3);
            g.fillRect(1, h - 2, w - 3, 1);
            // Shadow line2
            g.setColor(lighterShadow);
            g.fillRect(w - 2, 0, 1, 1);
            g.fillRect(0, h - 2, 1, 1);
            g.fillRect(w-2, h-2, 1, 1);
            g.fillRect(w - 1, 1, 1, h - 2);
            g.fillRect(1, h - 1, w - 2, 1);
            g.translate(-x, -y);
        }
    }
}