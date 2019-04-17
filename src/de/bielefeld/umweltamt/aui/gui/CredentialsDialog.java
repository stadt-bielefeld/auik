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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.module.ELKASync;

public class CredentialsDialog extends JDialog {
    private static final long serialVersionUID = 5675992723796806940L;

    private SettingsManager settings;
    private ELKASync modul;
    private JLabel textLabel;
    private JLabel userLabel;
    private JTextField userField;
    private JLabel urlLabel;
    private JTextField urlFeld;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

    private JPanel contentPanel;
    private GridBagLayout layout;
    private GridBagConstraints layoutConstraints;

    private KeyEventDispatcher escListener;

    public CredentialsDialog(ELKASync modul) throws HeadlessException {
        super();
        this.setTitle("Eingabedialog für externen Dienst");
        this.setModal(true);
        this.modul = modul;
        this.settings = SettingsManager.getInstance();
        this.initialize();
    }

    private void initialize() {
        this.setLocationByPlatform(true);
        escListener = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    CredentialsDialog.this.close();
                }
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher(escListener);
        this.textLabel = new JLabel(
            "<html>Bitte geben Sie die URL zu dem Dienst,"
                + " Ihren Benutzernamen und <br>"
                + "ihr Passwort ein und klicken Sie auf \"Ok\".</html>");
        this.userField = new JTextField(10);
        this.urlFeld = new JTextField();
        if (settings.getSetting("auik.elka.lasturl") == null) {
            urlFeld.setText("");
        } else {
            urlFeld.setText(settings.getSetting("auik.elka.lasturl"));
        }
        if (settings.getSetting("auik.elka.lastuser") == null) {
            userField.setText("");
        } else {
            userField.setText(settings.getSetting("auik.elka.lastuser"));
        }
        this.userField.selectAll();
        this.passwordField = new JPasswordField(10);
        this.loginButton = new JButton("Ok");

        this.userField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocus();
            }
        });
        this.urlFeld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userField.requestFocus();
            }
        });

        this.passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.requestFocus();
                loginButton.doClick();
            }
        });

        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CredentialsDialog.this.close();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlFeld.getText();
                String user = userField.getText();
                String pw = new String(passwordField.getPassword());
                modul.setServiceUrl(url);
                modul.setServiceUser(user);
                modul.setServicePassword(pw);
                settings.setSetting("auik.elka.lastuser", user, true);
                settings.setSetting("auik.elka.lasturl", url, true);
                CredentialsDialog.this.close();
            }
        });

        urlLabel = new JLabel("URL:");
        userLabel = new JLabel("Benutzer:");
        passwordLabel = new JLabel("Passwort:");
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        layout = new GridBagLayout();
        layoutConstraints = new GridBagConstraints();

        layoutConstraints.insets = new Insets(2, 4, 2, 4);
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.anchor = GridBagConstraints.WEST;

        contentPanel.setLayout(layout);

        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        addGridCmp(textLabel);
        layoutConstraints.gridwidth = GridBagConstraints.RELATIVE;
        addGridCmp(urlLabel);
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        addGridCmp(urlFeld);
        layoutConstraints.gridwidth = GridBagConstraints.RELATIVE;
        addGridCmp(userLabel);
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        addGridCmp(userField);
        layoutConstraints.gridwidth = GridBagConstraints.RELATIVE;
        addGridCmp(passwordLabel);
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        addGridCmp(passwordField);
        layoutConstraints.fill = GridBagConstraints.NONE;
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        addGridCmp(loginButton);


        this.setContentPane(contentPanel);
        this.pack();

        /* Set the focus to the password field */
        this.passwordField.grabFocus();
    }

    public void addGridCmp(JComponent comp) {
        layout.setConstraints(comp, layoutConstraints);
        contentPanel.add(comp);
    }

    public void close() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .removeKeyEventDispatcher(escListener);
        this.dispose();

    }
}
