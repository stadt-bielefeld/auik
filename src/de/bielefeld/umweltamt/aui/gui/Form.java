/**
 * Copyright 2025, Stadt Bielefeld
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
package de.bielefeld.umweltamt.aui.gui;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

/**
 * Container for labels and fields.
 *
 * Fields and labels are displayed in a two-column grid with labels in the
 * first column. The column is sized to fit the largest label.
 */
public class Form extends JPanel {

    private SpringLayout springLayout = new SpringLayout();

    private static final Spring INIT = Spring.constant(0);
    private static final Spring PAD = Spring.constant(5);

    public Form() {
        super();
        this.setLayout(springLayout);
    }

    /**
     * Append label and field to form.
     * @param label String to put into label
     * @param field The field to be added to the form
     */
    public void appendField(String label, JComponent field) {
        JLabel jLabel = new JLabel(label, JLabel.TRAILING);
        jLabel.setLabelFor(field);
        this.add(jLabel);
        this.add(field);

        // Calculate grid cell dimensions
        Spring height = INIT, labelWidth = INIT;
        for (Component c : this.getComponents()) {
            height = Spring.max(height, Spring.height(c));
            if (c instanceof JLabel) {
                labelWidth = Spring.max(labelWidth, Spring.width(c));
            }
        }

        // Position label-field pairs in in two-column grid
        Spring fieldX = Spring.sum(PAD, labelWidth);
        Spring y = INIT;
        Spring yOffset = Spring.sum(height, PAD);
        for (Component c : this.getComponents()) {
            SpringLayout.Constraints cons = springLayout.getConstraints(c);
            cons.setHeight(height);
            cons.setY(y);
            if (c instanceof JLabel) {
                // Set width for label
                cons.setWidth(labelWidth);
            } else {
                // Position field next to label
                cons.setX(fieldX);

                // Next row
                y = Spring.sum(y, yOffset);
            }
        }
    }
}
