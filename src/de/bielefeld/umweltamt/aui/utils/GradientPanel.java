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

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * A panel with a horizontal gradient background.
 * Based on the uif_lite GradientPanel, see https://github.com/JabRef/com.jgoodies.uif_lite/
 */
public class GradientPanel extends JPanel {

    private static final long serialVersionUID = -3777558294138807738L;

    /**
     * Default gradient start color
     */
    private static final Color DEFAULT_START_COLOR = new Color(186, 211, 237);

    /**
     * Default gradient end color
     */
    private static final Color DEFAULT_END_COLOR = new JPanel().getBackground();

    /**
     * Defines the anchor of the second color in relation to the panel width
     */
    private static final float SECOND_COLOR_ANCHOR_RATIO = 0.9f;

    /**
     * Gradient start color
     */
    private Color startColor;

    /**
     * Gradient end color
     */
    private Color endColor;

    /**
     * Constructor with default Color
     * @param lm Panel layout manager
     */
    public GradientPanel(LayoutManager lm) {
        this(lm, DEFAULT_START_COLOR , DEFAULT_END_COLOR);
    }

    /**
     * Constructor with start color. Endcolor will be set to default panel background
     * @param lm Panel layout manager
     * @param startColor Color to start width
     */
    public GradientPanel(LayoutManager lm, Color startColor) {
        this(lm, startColor, DEFAULT_END_COLOR);
    }

    /**
     * Constructor
     * @param lm Panel layout manager
     * @param startColor Color to start with
     * @param endColor Color to end with
     */
    public GradientPanel(LayoutManager lm, Color startColor, Color endColor) {
        super(lm);
        this.startColor = startColor;
        this.endColor = endColor;
    }

    @Override protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        GradientPaint gradientPaint = new GradientPaint(
                0, panelHeight, startColor,
                panelWidth * SECOND_COLOR_ANCHOR_RATIO, panelHeight, endColor );
        if( g instanceof Graphics2D ) {
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.setPaint( gradientPaint );
            graphics2D.fillRect( 0 , 0 , panelWidth , panelHeight );
        }
    }
}