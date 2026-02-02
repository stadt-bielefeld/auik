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
package de.bielefeld.umweltamt.aui.gui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.ui.tabbedui.VerticalLayout;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Dialog, allowing the user to change the current password
 */
public class PasswordChangeDialog extends JDialog {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private JLabel currentPasswordLabel;
    private JLabel newPasswordLabel;
    private JLabel newPasswordConfirmLabel;

    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField newPasswordConfirmField;

    private JButton okButton;
    private JButton cancelButton;

    private KeyEventDispatcher keyEventDispatcher;

    /**
     * Constructor
     * @param owner Owning frame this dialog will be placed above.
     * @throws HeadlessException
     */
    public PasswordChangeDialog(JFrame owner){
        super(owner, "Passwort ändern", true);
        this.setSize(450, 150);
        initialize();
    }

    /**
     * Initialize UI
     */
    private void initialize() {
        currentPasswordLabel = new JLabel("Aktuelles Passwort:");
        newPasswordLabel = new JLabel("Neues Passwort:");
        newPasswordConfirmLabel = new JLabel("Passwort bestätigen:");
        currentPasswordField = new JPasswordField();
        newPasswordField = new JPasswordField();
        newPasswordConfirmField = new JPasswordField();
        okButton = new JButton("OK");
        cancelButton = new JButton("Abbrechen");

        currentPasswordField.setPreferredSize(new Dimension(150, 22));

        //Button handlers
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        //Key handler
        keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() != KeyEvent.KEY_PRESSED) {
                    return false;
                }
                switch (e.getKeyCode()) {
                    //Use ESC to cancel
                    case KeyEvent.VK_ESCAPE:
                        close();
                        break;
                    //Use enter to submit if focus is on new password confirmation field
                    case KeyEvent.VK_ENTER:
                        if (newPasswordConfirmField.isFocusOwner()) {
                            submit();
                        }
                        break;
                }
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(keyEventDispatcher);

        //Create form
        FormLayout layout = new FormLayout(
            "right:pref, 4dlu, pref:grow, 4dlu, pref", // Spalten
            "pref:grow, 3dlu, pref, 3dlu, pref, 3dlu, p" // Zeilen
        );

        JPanel contentPanel = new JPanel(new VerticalLayout());

        layout.setRowGroups(new int[][]{{1, 3, 5}});

        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();
        builder.add(currentPasswordLabel, cc.xy(1, 1));
        builder.add(currentPasswordField, cc.xy(3, 1));
        builder.add(newPasswordLabel, cc.xy(1, 3));
        builder.add(newPasswordField, cc.xy(3, 3));
        builder.add(newPasswordConfirmLabel, cc.xy(1, 5));
        builder.add(newPasswordConfirmField, cc.xy(3, 5));
        JPanel formPanel = builder.getPanel();
        formPanel.setBorder(Paddings.DIALOG);
        contentPanel.add(formPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        contentPanel.add(buttonPanel);

        this.setContentPane(contentPanel);
        this.pack();
    }

    /**
     * Check input and submit password change
     */
    private void submit() {
        if (!checkInput()) {
            return;
        }
        if (!changePassword(newPasswordField.getPassword())) {
            JOptionPane.showMessageDialog(PasswordChangeDialog.this, "Passwortänderung fehlgeschlagen. \nBitte versuchen Sie es erneut.");
        } else {
            close();
        }
    }

    /**
     * Cancel action and dispose this dialog
     */
    private void close() {
        PasswordChangeDialog.this.dispose();
    }

    private boolean checkInput() {
        if (checkPasswordEmpty()) {
             JOptionPane.showMessageDialog(PasswordChangeDialog.this, "Bitte geben Sie ein neues Passwort ein");
             return false;
        }
        if (!checkPasswordConfirmation()) {
            JOptionPane.showMessageDialog(PasswordChangeDialog.this, "Die Passwörter stimmen nicht überein");
            return false;
        }
        if (!checkCurrentPassword()) {
            JOptionPane.showMessageDialog(PasswordChangeDialog.this, "Das eingegebene Passwort ist nicht korrekt");
            return false;
        }
        return true;
    }

    /**
     * Check if the new password is empty.
     * @return True if empty, else false
     */
    private boolean checkPasswordEmpty() {
        return newPasswordField.getText().isEmpty();
    }

    /**
     * Check if password was correctly repeated in the second input field
     * @return True if password is confirmed, else false
     */
    private boolean checkPasswordConfirmation() {
        return newPasswordField.getText().equals(newPasswordConfirmField.getText());
    }

    /**
     * Check if current password is correct
     * @return True if password is correct, else false
     */
    private boolean checkCurrentPassword() {
        boolean success = false;
        try {
            success = HibernateSessionFactory.checkCredentials(
                    HibernateSessionFactory.getDBUser(), String.valueOf(currentPasswordField.getPassword()), false);
        } catch (HibernateException he) {
            log.debug("Exception occured during password check: " + he.getStackTrace());
        }
        return success;
    }

    /**
     * Change the current password
     * @return True if successful
     */
    private boolean changePassword(char[] newPw) {
        if (newPw == null || newPw.length == 0) {
            throw new IllegalArgumentException("Empty password not allowed");
        }
        Session session = HibernateSessionFactory.currentSession();
        if (session == null) {
            return false;
        }
        String user = HibernateSessionFactory.getDBUser();
        String queryString = String.format("ALTER USER \"%s\" WITH PASSWORD '%s'",
                escapeUserString(user),
                escapePasswordString(String.valueOf(newPw)));
        boolean success = false;
        SQLQuery query = session.createSQLQuery(queryString);
        try {
			Transaction tx = null;
		    tx = session.beginTransaction();
		    query.executeUpdate();
		    tx.commit();
        	success = true;
            HibernateSessionFactory.setDBData(HibernateSessionFactory.getDBUser(), String.valueOf(newPw));
        } catch (Exception e) {
            log.error("Error changing password: " + e);
        }
        return success;
    }

    /**
     * Returns an escaped username String
     * @param user Username
     * @return Escaped String
     */
    private String escapeUserString(String user) {
        String returnString = user.replaceAll("\"", "\\\"");
        return returnString;
    }

    /**
     * Get an escaped password String
     * @param pw Password
     * @return Escaped String
     */
    private String escapePasswordString(String pw) {
        String returnString = pw.replaceAll("'", "''");
        return returnString;
    }
}
