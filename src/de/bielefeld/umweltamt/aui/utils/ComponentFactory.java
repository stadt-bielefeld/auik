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

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

// ── ENTFERNT: JGoodies Import ─────────────────────────
// import com.jgoodies.forms.factories.Forms;

/**
 * Factory class containing some convenience functions to build
 * frequently used UI components
 */
public class ComponentFactory {

    /**
     * Builds a button bar containing an ok button
     * @param okButton Button to add to the bar
     * @return Buttonbar
     */
    public static JComponent buildOKBar(JComponent okButton) {
        return buildButtonBar(okButton);
    }

    /**
     * Builds a button bar containing an ok and a cancel button
     * @param okButton Button to use as ok button
     * @param cancelButton Button to use a cancel button
     * @return Button bar containing the two buttons
     */
    public static JComponent buildOKCancelBar(
            JComponent okButton,
            JComponent cancelButton) {
        return buildButtonBar(okButton, cancelButton);
    }

    /**
     * Builds a button bar containing an ok, a cancel and an apply button
     * @param okButton Button to use as ok button
     * @param cancelButton Button to use as cancel button
     * @param applyButton Button to use as apply button
     * @return Button bar containing the three buttons
     */
    public static JComponent buildOKCancelApplyBar(
            JComponent okButton,
            JComponent cancelButton,
            JComponent applyButton) {
        return buildButtonBar(okButton, cancelButton, applyButton);
    }

    /**
     * Builds a button bar containing a variable number of buttons
     * GEÄNDERT: Forms.buttonBar() → JPanel mit FlowLayout
     * @param buttons Button list
     * @return Button bar
     */
    public static JComponent buildButtonBar(JComponent... buttons) {
        // GEÄNDERT: Forms.buttonBar() → BoxLayout mit Buttons
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(
            new BoxLayout(buttonBar, BoxLayout.X_AXIS));

        for (int i = 0; i < buttons.length; i++) {
            if (i > 0) {
                // Abstand zwischen Buttons (entspricht JGoodies Standard)
                buttonBar.add(Box.createHorizontalStrut(6));
            }
            buttonBar.add(buttons[i]);
        }
        return buttonBar;
    }

    /**
     * Builds a left aligned button bar
     * GEÄNDERT: Forms.buttonBar() → FlowLayout(LEFT)
     * @param buttons Buttons to include into the button bar
     * @return Button bar
     */
    public static JComponent buildLeftAlignedBar(
            JComponent... buttons) {
        // GEÄNDERT: Forms.buttonBar() → JPanel mit FlowLayout
        JPanel result = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 5, 0));
        for (JComponent button : buttons) {
            result.add(button);
        }
        return result;
    }

    /**
     * Builds a right aligned button bar
     * GEÄNDERT: Forms.buttonBar() → FlowLayout(RIGHT)
     * @param buttons Buttons to include into the button bar
     * @return Button bar
     */
    public static JComponent buildRightAlignedBar(
            JComponent... buttons) {
        // GEÄNDERT: Forms.buttonBar() → JPanel mit FlowLayout
        JPanel result = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 5, 0));
        for (JComponent button : buttons) {
            result.add(button);
        }
        return result;
    }

    /**
     * Creates a borderless JSplitPane with the given config.
     * UNVERÄNDERTER CODE [1][2]
     * @param orientation JSplitPane.HORIZONTAL_SPLIT or
     *                    JSplitPane.VERTICAL_SPLIT
     * @param leftComponent Component that will be shown left of a
     *                      horizontally split plane or at the top of
     *                      a vertically split pane
     * @param rightComponent Component that will be shown right of
     *                       horizontally split pane or at the bottom
     *                       of a vertically split pane
     * @param resizeWeight The resize weight of the resulting JSplitPane.
     * @return Created split pane
     */
    public static JSplitPane createStrippedSplitPane(
            int orientation,
            JComponent leftComponent,
            JComponent rightComponent,
            double resizeWeight) {
        if (orientation != JSplitPane.HORIZONTAL_SPLIT
            && orientation != JSplitPane.VERTICAL_SPLIT) {
            throw new IllegalArgumentException(
                "Invalid orientation value");
        }
        if (resizeWeight < 0 || resizeWeight > 1) {
            throw new IllegalArgumentException(
                "Resize weight must be between 0 and 1");
        }
        JSplitPane pane = new JSplitPane(
            orientation, leftComponent, rightComponent);
        pane.setBorder(null);
        pane.setResizeWeight(resizeWeight);
        return pane;
    }
}