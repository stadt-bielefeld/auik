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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JCalendar;

/**
 * A better DateChooser, that uses a TextField and parses Dates in
 * different Formats.
 */
public class TextFieldDateChooser extends JPanel
implements ActionListener, FocusListener, PropertyChangeListener {

    protected String[] patterns = null;
    protected SimpleDateFormat dateFormat = null;
    protected JTextField textField = null;
    protected JCalendar jcalendar = null;
    protected JButton calendarButton = null;
    protected JPopupMenu popup = null;
    protected boolean dateSelected = false;
    protected Date lastSelectedDate = null;
    protected Calendar workingCalendar = null;
    protected Date todaysDate = null;
    protected boolean ignorePropertyChangeEvent = false;

    /**
     * <p>
     * Creates an instance whose text field will accept a set of
     * default patterns.
     * </p>
     *
     * <p>
     * The initial value of the text field is empty, but can be
     * changed by simply calling {@link #setDate(Date)}.
     * </p>
     */
    public TextFieldDateChooser() {
        this(null);
    }

    /**
     * <p>
     * Creates an instance whose text field will accept the specified
     * DateFormatter patterns. The pattern displayed by the text field will be
     * the first pattern of the <code>patterns</code> array. If
     * <code>patterns</code> is <code>null</code> a default array
     * of patterns will be used.
     * </p>
     *
     * <p>
     * The initial value of the text field is empty, but can be
     * changed by simply calling {@link #setDate(Date)}.
     * </p>
     */
    public TextFieldDateChooser(String[] patterns) {
        super(new BorderLayout());
        this.patterns = patterns;

        if (this.patterns == null) {
            this.patterns = new String[] { "dd-MMM-yyyy", "MM/dd/yyyy",
                    "MM-dd-yyyy", "MMddyyyy", "ddMMMyyyy", "yyyyMMdd",
                    "yyyy/MM/dd", "yyyy-mm-dd" };
        }

        dateFormat = new SimpleDateFormat(this.patterns[0]);

        // textField is initially empty.
        textField = new JTextField(10);
        textField.addFocusListener(this);
        super.add(textField, BorderLayout.CENTER);

        todaysDate = new Date();
        jcalendar = new JCalendar(todaysDate);
        jcalendar.getDayChooser().addPropertyChangeListener(this);
        // always fire "day" property even if the user selects the already
        // selected day again
        jcalendar.getDayChooser().setAlwaysFireDayProperty(true);

        URL iconURL =
            jcalendar.getClass().getResource("images/JDateChooserIcon.gif");
        calendarButton = new JButton(new ImageIcon(iconURL));
        calendarButton.setMargin(new Insets(0, 0, 0, 0));
        calendarButton.addActionListener(this);
        super.add(calendarButton, BorderLayout.LINE_END);

        popup = new JPopupMenu() {
            public void setVisible(boolean b) {
                Boolean isCanceled = (Boolean)
                getClientProperty("JPopupMenu.firePopupMenuCanceled");

                if (b || (!b && dateSelected)
                        || ((isCanceled != null)
                                && !b && isCanceled.booleanValue())) {
                    super.setVisible(b);
                }
            }
        };

        popup.setLightWeightPopupEnabled(true);
        popup.add(jcalendar);
    }

    /**
     * Sets the DateFormatter displayed by the text field and returned by
     * {@link #getDate()}.
     *
     * @param DateFormatter The new DateFormatter.
     */
    public void setDate(Date date) {
        lastSelectedDate = date;
        // Ignore the property change event broadcasted by JCalendar.
        ignorePropertyChangeEvent = true;

        if (date == null) {
            textField.setText("");
            jcalendar.setDate(todaysDate);
        } else {
            textField.setText(dateFormat.format(date));
            jcalendar.setDate(date);
        }

        if (super.getParent() != null) {
            super.getParent().validate();
        }
    }

    /**
     * Returns the DateFormatter.
     *
     * @return The currently selected DateFormatter (may be NULL)
     */
    public Date getDate() {
        return lastSelectedDate;
    }

    /**
     * Returns the text field in use.
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Returns the calendar button in use.
     */
    public JButton getCalendarButton() {
        return calendarButton;
    }

    /**
     * Called when the calendar button is pressed.
     */
    public void actionPerformed(ActionEvent e) {
        int x = calendarButton.getWidth()
        - (int) popup.getPreferredSize().getWidth();
        int y = calendarButton.getY() + calendarButton.getHeight();

        popup.show(calendarButton, x, y);
        dateSelected = false;
    }

    /**
     * Called when JCalendar's DateFormatter is changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (!ignorePropertyChangeEvent && evt.getPropertyName().equals("day")) {
            dateSelected = true;
            popup.setVisible(false);
            lastSelectedDate = jcalendar.getDate();
            textField.setText(dateFormat.format(lastSelectedDate));
        }

        ignorePropertyChangeEvent = false;
    }

    /**
     * Formats the text of the text field or displays an error if the
     * text can't be formatted.
     */
    public void focusLost(FocusEvent evt) {
        // Ignore temporary events.
        if (evt.isTemporary()) {
            return;
        }

        String enteredDate = textField.getText().trim();

        // If the text field was blanked then set the selected DateFormatter to null.
        if ((enteredDate == null) || (enteredDate.length() == 0)) {
            setDate(null);
            return;
        }

        Date date = null;

        // Loop through the accepted patterns and try to parse the DateFormatter.
        for (int i = 0; i < patterns.length; i++) {
            dateFormat.applyPattern(patterns[i]);

            try {
                date = dateFormat.parse(enteredDate);
            } catch (ParseException e) {
                continue;
            }

            break;
        }

        // Reset the pattern of the format to the default.
        dateFormat.applyPattern(patterns[0]);

        // If the entered DateFormatter could not be parsed, then display an error.
        if (date == null) {
            displayErrorMessage();
            textField.selectAll();
            textField.requestFocus();
        }

        // Set the text field with the default pattern and
        // set JCalendar's DateFormatter.
        setDate(date);
    }

    /**
     * Displays and error message notifying the user that they
     * entered invalid data in the text field.
     */
    protected void displayErrorMessage() {
        StringBuffer message = new StringBuffer();
        message.append("Bitte eins der folgenden Formate benutzen: ");
        message.append("\n");

        for (int i = 0; i < patterns.length; i++) {
            message.append(patterns[i]);

            if (i < (patterns.length - 1)) {
                message.append(", ");
            }
        }

        JOptionPane.showMessageDialog(textField, message,
                "Falsches Datumsformat", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Updates the UI of itself and the popup.
     */
    public void updateUI() {
        super.updateUI();

        if (jcalendar != null) {
            SwingUtilities.updateComponentTreeUI(popup);
        }
    }

    public void setEnabled(boolean enabled) {
        getTextField().setEnabled(enabled);
        getCalendarButton().setEnabled(enabled);
        super.setEnabled(enabled);
    }

    /** Not used. */
    public void focusGained(FocusEvent e) {
    }
}