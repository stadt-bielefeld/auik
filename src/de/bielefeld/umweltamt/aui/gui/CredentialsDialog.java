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
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.module.ELKASync;

public class CredentialsDialog extends JDialog {

    private static final long serialVersionUID = 5675992723796806940L;

    private SettingsManager settings;
    private ELKASync modul;
    private JLabel textLabel;
    private JTextField benutzerFeld;
    private JTextField urlFeld;
    private JPasswordField passwortFeld;
    private JButton loginButton;
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

        // ESC-Listener registrieren
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

        // Widgets initialisieren
        this.textLabel = new JLabel(
            "<html>Bitte geben Sie die URL zu dem Dienst,"
                + " Ihren Benutzernamen und <br>"
                + "ihr Passwort ein und klicken Sie auf \"Ok\".</html>");

        this.benutzerFeld = new JTextField(10);
        this.urlFeld = new JTextField();

        if (settings.getSetting("auik.elka.lasturl") == null) {
            urlFeld.setText("");
        } else {
            urlFeld.setText(settings.getSetting("auik.elka.lasturl"));
        }

        if (settings.getSetting("auik.elka.lastuser") == null) {
            benutzerFeld.setText("");
        } else {
            benutzerFeld.setText(settings.getSetting("auik.elka.lastuser"));
        }

        this.benutzerFeld.selectAll();
        this.passwortFeld = new JPasswordField(10);
        this.loginButton = new JButton("Ok");

        // ActionListener: URL-Feld → Benutzer-Feld
        this.urlFeld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                benutzerFeld.requestFocus();
            }
        });

        // ActionListener: Benutzer-Feld → Passwort-Feld
        this.benutzerFeld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwortFeld.requestFocus();
            }
        });

        // ActionListener: Passwort-Feld → Login-Button
        this.passwortFeld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.requestFocus();
                loginButton.doClick();
            }
        });

        // Fenster-Close-Verhalten
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CredentialsDialog.this.close();
            }
        });

        // Login-Button ActionListener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url  = urlFeld.getText();
                String user = benutzerFeld.getText();
                String pw   = new String(passwortFeld.getPassword());
                modul.setServiceUrl(url);
                modul.setServiceUser(user);
                modul.setServicePassword(pw);
                settings.setSetting("auik.elka.lastuser", user, true);
                settings.setSetting("auik.elka.lasturl",  url,  true);
                CredentialsDialog.this.close();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(BorderFactory.createEmptyBorder(11, 12, 11, 12));

        GridBagConstraints gbc = new GridBagConstraints();

        // ----------------------------------------------------------
        // Zeile 1: textLabel über alle 3 Spalten (Label + Feld + Lücke)
        // ----------------------------------------------------------
        gbc.gridx     = 0;
        gbc.gridy     = 0;
        gbc.gridwidth = 3;
        gbc.fill      = GridBagConstraints.HORIZONTAL;
        gbc.weightx   = 1.0;
        gbc.weighty   = 0;
        gbc.insets    = new Insets(0, 0, 6, 0);
        gbc.anchor    = GridBagConstraints.WEST;
        panel.add(textLabel, gbc);

        // ----------------------------------------------------------
        // Zeile 2: "Url" Label | urlFeld
        // ----------------------------------------------------------
        gbc.gridwidth = 1;
        gbc.fill      = GridBagConstraints.NONE;
        gbc.weightx   = 0;
        gbc.insets    = new Insets(3, 0, 3, 4);
        gbc.anchor    = GridBagConstraints.EAST;
        gbc.gridx     = 0;
        gbc.gridy     = 1;
        panel.add(new JLabel("Url"), gbc);

        gbc.gridx   = 1;
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets  = new Insets(3, 0, 3, 0);
        gbc.anchor  = GridBagConstraints.WEST;
        panel.add(urlFeld, gbc);

        // ----------------------------------------------------------
        // Zeile 3: "Benutzer" Label | benutzerFeld
        // ----------------------------------------------------------
        gbc.gridx   = 0;
        gbc.gridy   = 2;
        gbc.fill    = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets  = new Insets(3, 0, 3, 4);
        gbc.anchor  = GridBagConstraints.EAST;
        panel.add(new JLabel("Benutzer"), gbc);

        gbc.gridx   = 1;
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets  = new Insets(3, 0, 3, 0);
        gbc.anchor  = GridBagConstraints.WEST;
        panel.add(benutzerFeld, gbc);

        // ----------------------------------------------------------
        // Zeile 4: "Passwort" Label | passwortFeld
        // ----------------------------------------------------------
        gbc.gridx   = 0;
        gbc.gridy   = 3;
        gbc.fill    = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.insets  = new Insets(3, 0, 3, 4);
        gbc.anchor  = GridBagConstraints.EAST;
        panel.add(new JLabel("Passwort"), gbc);

        gbc.gridx   = 1;
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets  = new Insets(3, 0, 3, 0);
        gbc.anchor  = GridBagConstraints.WEST;
        panel.add(passwortFeld, gbc);

        // ----------------------------------------------------------
        // Zeile 5: loginButton (links, unter den Labels)
        // ----------------------------------------------------------
        gbc.gridx     = 0;
        gbc.gridy     = 4;
        gbc.gridwidth = 2;
        gbc.fill      = GridBagConstraints.NONE;
        gbc.weightx   = 0;
        gbc.weighty   = 0;
        gbc.insets    = new Insets(8, 0, 0, 0);
        gbc.anchor    = GridBagConstraints.WEST;
        panel.add(loginButton, gbc);

        // Panel als ContentPane setzen
        this.setContentPane(panel);
        this.pack();

        // Fokus auf das Passwort-Feld setzen
        this.passwortFeld.grabFocus();
    }

    public void close() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .removeKeyEventDispatcher(escListener);
        this.dispose();
    }
}