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
package de.bielefeld.umweltamt.aui.tipi;

import javax.swing.JDialog;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.KeyEventDispatcher;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;

import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.module.DBSyncModul;
/**
 *
 */
public class CredentialsDialog extends JDialog {

        private SettingsManager settings;
        private DBSyncModul modul;

        private JLabel textLabel;
        private JTextField benutzerFeld;
        private JTextField urlFeld;
        private JPasswordField passwortFeld;
        private JButton loginButton;

        private String user, pw, url;

        private KeyEventDispatcher escListener;

        public CredentialsDialog(DBSyncModul modul) throws HeadlessException {
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
            this.benutzerFeld = new JTextField(10);
            this.urlFeld = new JTextField();
            if (settings.getSetting("auik.tipi.lasturl") == null) {
                urlFeld.setText("");
            } else {
                urlFeld.setText(settings.getSetting("auik.tipi.lasturl"));
            }
            if (settings.getSetting("auik.tipi.lastuser") == null) {
                benutzerFeld.setText("");
            } else {
                benutzerFeld.setText(settings.getSetting("auik.tipi.lastuser"));
            }
            this.benutzerFeld.selectAll();
            this.passwortFeld = new JPasswordField(10);
            this.loginButton = new JButton("Ok");


            this.benutzerFeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    passwortFeld.requestFocus();
                }
            });
            this.urlFeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    benutzerFeld.requestFocus();
                }
            });

            this.passwortFeld.addActionListener(new ActionListener() {
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
                    String user = benutzerFeld.getText();
                    String pw = passwortFeld.getText();
                    modul.setServiceUrl(url);
                    modul.setServiceUser(user);
                    modul.setServicePassword(pw);
                    settings.setSetting("auik.tipi.lastuser", user, true);
                    settings.setSetting("auik.tipi.lasturl", url, true);
                    CredentialsDialog.this.close();
                    modul.requestData();
                }
            });

            FormLayout layout = new FormLayout(
                    "right:pref, 4dlu, pref:grow, 4dlu, pref", // Spalten
                    "pref:grow, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref:grow" // Zeilen
            );

            layout.setRowGroups(new int[][]{{3, 5}});

            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            builder.add(textLabel, cc.xyw(1, 1, 5));
            builder.addLabel("Url:", cc.xy(1, 3));
            builder.add(urlFeld, cc.xy(3, 3));
            builder.addLabel("Benutzer:", cc.xy(1, 5));
            builder.add(benutzerFeld, cc.xy(3, 5));
            builder.addLabel("Passwort:", cc.xy(1, 7));
            builder.add(passwortFeld, cc.xy(3, 7));
            builder.add(loginButton, cc.xy(1, 9));

            this.setContentPane(builder.getPanel());
            this.pack();

            /* Set the focus to the password field */
            this.passwortFeld.grabFocus();
        }

        public void close() {
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
                    .removeKeyEventDispatcher(escListener);
            this.dispose();

        }
    }


