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

/*
 * Datei:
 * $Id: HauptFrame.java,v 1.6 2010-03-19 06:28:03 u633d Exp $
 *
 * Erstellt am 07.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2010/03/09 09:24:42  u633d
 * Verkn�pfung GIS/AUIK
 *
 * Revision 1.4  2010/02/22 07:49:45  u633d
 * Doku
 *
 * Revision 1.3  2009/03/24 12:35:19  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/06/24 11:24:07  u633d
 * Version 0.3
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.72.6.1  2006/07/26 06:46:03  u633d
 * Neuer Zweig
 *
 * Revision 1.72  2005/11/02 13:45:44  u633d
 * - Version vom 2.11.
 *
 * Revision 1.71  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.70  2005/09/28 11:11:13  u633d
 * - Version vom 28.9.
 *
 * Revision 1.69  2005/06/30 11:21:28  u633z
 * - Datenbank-URL
 *
 * Revision 1.68  2005/06/02 15:16:08  u633z
 * - Neue showQuestion-Methoden
 *
 * Revision 1.67  2005/06/02 09:18:17  u633z
 * - Methoden für Datei-Speichern-Chooser hinzugefügt
 *
 * Revision 1.66  2005/05/25 15:43:51  u633z
 * - Header hinzugefügt
 * - Messagebox-Methoden umbenannt
 *
 */
package de.bielefeld.umweltamt.aui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import org.hibernate.HibernateException;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.plaf.FontSizeHints;
import com.jgoodies.plaf.HeaderStyle;
import com.jgoodies.plaf.Options;
import com.jgoodies.uif_lite.component.Factory;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.GradientPanel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Das Hauptfenster des AUI-K. Dieses Fenster beinhaltet alle weiteren
 * GUI-Komponenten der Anwendung.
 *
 * @author David Klotz
 */
public class HauptFrame extends JFrame {

    /** GUI Manager */
    private static final GUIManager guiManager = GUIManager.getInstance();

    /** Die Standard-Größe für die Kategorie-Buttons */
    // static final Dimension BUTTON_SIZE = new Dimension(70,70);
    /**
     * Panel-Größe, um ein Layout-Problem zu umgehen, nur in Ausnahmefällen
     * benutzen
     */
    // public static final Dimension PANEL_SIZE = new Dimension(515, 320);

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    private static final long serialVersionUID = 4233252729652695263L;

    /** Die Farbe für Fehlermeldungen */
    public static final Color ERROR_COLOR = new Color(200, 0, 0);
    /** Die Farbe für Erfolgsmeldungen */
    public static final Color SUCCESS_COLOR = new Color(0, 128, 0);
    /**
     * Die Farbe für normale Status-Meldungen. Nicht public, für normale
     * Meldungen einfach changeStatus ohne Farb-Argument benutzen.
     */
    protected static final Color NORMAL_COLOR = Color.BLACK;

    protected static final int FILE_OPEN = 1;
    protected static final int FILE_SAVE = 2;

    private JPanel jContentPane = null;
    private ActionListener buttonListener = null;
    private Action closeAction = null;

    private JFileChooser fileChooser = null;

    private JMenuBar hauptMenue = null;
    private JMenu fileMenu = null;
    private JMenuItem settingsMenuItem = null;
    private JMenuItem exitMenuItem = null;
    private JMenu helpMenu = null;
    private JMenuItem aboutMenuItem = null;
    private JMenuItem DokuItem = null;

    private JPanel titlePanel = null;

    private JPanel statusPanel = null;
    private JLabel statusLabel = null;

    private JSplitPane splitPane = null;

    private SimpleInternalFrame leftFrame = null;
    private JScrollPane leftScroller = null;
    private JPanel leftCardPanel = null;

    private SimpleInternalFrame rightFrame = null;
    private JPanel rightCardPanel = null;

    // Das Kategorien-Menü
    private JPopupMenu viewMenu = null;
    private JToolBar viewMenuBar = null;
    private JButton viewMenuButton = null;

    // Die Modul-Toolbar (vorwärts, zurück...)
    private JToolBar modulBar = null;
    private JButton modulBackButton = null;
    private JButton modulFwdButton = null;
    // private JButton qgis = null;

    private ModulManager manager;
    private SettingsManager settings;
    private Timer clearStatusTimer;

    /**
     * Erzeugt ein neues HauptFrame
     */
    public HauptFrame(SettingsManager settings) {
        super();
        this.settings = settings;
        initialize();
    }

    /**
     * Diese Meethode initialisiert das Fenster sowie dessen beinhalteten
     * Komponenten. <br>
     * <b>Hinweis:</b> Das Look and Feel der Anwendung wird ebenfalls an dieser
     * Stelle gesetzt.<br>
     * <b>Achtung:</b> Au&szlig;erdem werden in dieser Methode die Parameter für
     * Datenbankverbindung abgefragt und an Hibernate &uuml;bergeben. Dies
     * sollte optimalerweise nicht an dieser Stelle geschehen!
     */
    private void initialize() {
        try {
            // Systemschriftarten benutzen
            UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
            Options.setGlobalFontSizeHints(FontSizeHints.MIXED);
            Options.setUseSystemFonts(true);

            // Look & Feel umschalten
            UIManager
                    .setLookAndFeel("com.jgoodies.plaf.windows.ExtWindowsLookAndFeel");

            /*
             * Falls mal ein Wechsel auf Linux anstehen sollte, wird der
             * Windows-L'n'F vermutlich nicht mehr funktionieren. Auch kann es
             * sein, dass man auf allen NT- und XP-Rechner den selben L'n'F
             * haben möchte. Dann einfach statt der Zeile oben die folgende
             * nehmen:
             */
            // UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");

            // Erleichert das Finden von manchen Layout-Fehlern
            // ClearLookManager.setMode(ClearLookMode.DEBUG);
        } catch (Exception e) {
            log.debug("Konnte Look & Feel nicht ändern!");
        }

        this.setJMenuBar(getHauptMenue());

        // Fenstergröße setzen
        this.setSize(settings.getIntSetting("auik.prefs.res_x"),
                settings.getIntSetting("auik.prefs.res_y"));
        if (settings.getBoolSetting("auik.prefs.maximized")) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        this.setContentPane(getJContentPane());
        ImageIcon icon =
            (ImageIcon) AuikUtils.getIcon(16, "uaicon16.png", "Umweltamt");
        this.setIconImage(icon.getImage());
        this.setTitle(GUIManager.SHORT_NAME);

        // Wird benötigt, um das Fenster mit dem Schließen-Icon zu schließen
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                HauptFrame.this.close();
            }
        });

        // Zentriert das Fenster
        locateOnScreen(this);

        clearStatusTimer = new Timer(
                settings.getIntSetting("auik.prefs.status_time") * 1000,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clearStatus();
                    }
                });
        clearStatusTimer.setRepeats(false);

        // Setzt die Datenbank aus einem Property
        String db = settings.getSetting("auik.system.dburl");
        if (db != null && !db.equals("")) {
            HibernateSessionFactory.setDBUrl(db);
        }

        // Fragt die Benutzerdaten ab, wenn die Abfrage erfolgreich war...
        if (askForDBCredentials()) {
            // ... Erzeuge den Manager und ...
            this.manager = new ModulManager(this, settings);
            // ... füge Module hinzu
            manager.loadModule();

            // Zeige dieses Fenster an
            this.setVisible(true);
        } else {
            // Wenn das Eingeben der Benutzerdaten abgebrochen wurde,
            // beenden wir das Programm.
            this.close();
        }
    }

    private boolean askForDBCredentials() {
        BenutzerDatenDialog benutzerDialog = new BenutzerDatenDialog(this);
        locateOnScreen(benutzerDialog);
        return benutzerDialog.loginSuccessful();
    }

    public Action getCloseAction() {
        if (closeAction == null) {
            closeAction = new AbstractAction("Beenden",
                    AuikUtils.getIcon(32, "exit.png")) {
                private static final long serialVersionUID = 2119355762465447730L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    HauptFrame.this.close();
                }
            };
            closeAction.putValue(
                    Action.SHORT_DESCRIPTION, "Beendet das Programm");
            closeAction.putValue(
                    Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
            closeAction.putValue(
                    Action.SMALL_ICON, AuikUtils.getIcon(16, "exit.png"));
        }
        return closeAction;
    }

    /**
     * @return Returns the buttonListener.
     */
    protected ActionListener getButtonListener() {
        if (buttonListener == null) {
            buttonListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    manager.switchModul(e.getActionCommand());
                }
            };
        }
        return buttonListener;
    }

    /**
     * @return Returns the viewMenu.
     */
    public JPopupMenu getViewMenu() {
        if (viewMenu == null) {
            viewMenu = new JPopupMenu();
        }
        return viewMenu;
    }

    /**
     * @return Returns the viewMenuBar.
     */
    private JToolBar getViewMenuBar() {
        if (viewMenuBar == null) {
            viewMenuBar = new JToolBar();
            viewMenuBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
            viewMenuBar.setFloatable(false);
            viewMenuBar.setOpaque(false);
            viewMenuBar.add(getViewMenuButton());
        }
        return viewMenuBar;
    }

    /**
     * @return Returns the viewMenuButton.
     */
    public JButton getViewMenuButton() {
        if (viewMenuButton == null) {
            viewMenuButton = new JButton("",
                    AuikUtils.getIcon(16, "view_menu.gif", "Kategorien"));

            viewMenuButton
                    .addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            Point np = SwingUtilities.convertPoint(
                                    getLeftFrame(), 0, 0, viewMenuButton);
                            getViewMenu().show(viewMenuButton, np.x,
                                    viewMenuButton.getHeight());
                        }
                    });

            viewMenuButton
                    .setForeground(getLeftFrame().getTextForeground(true));
            viewMenuButton.setOpaque(false);
            viewMenuButton.setHorizontalTextPosition(JButton.LEADING);
            viewMenuButton
                    .setToolTipText("Hier klicken, um die Kategorie zu wechseln");
        }
        return viewMenuButton;
    }

    /**
     * @return Returns the modulBar.
     */
    private JToolBar getModulBar() {
        if (modulBar == null) {
            modulBar = new JToolBar();
            modulBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
            modulBar.setFloatable(false);
            modulBar.setOpaque(false);
            // modulBar.add(getQgis());
            modulBar.add(getModulBackButton());
            modulBar.add(getModulFwdButton());
        }
        return modulBar;
    }

    /**
     * @return Returns the modulBackButton.
     */
    protected JButton getModulBackButton() {
        if (modulBackButton == null) {
            String desc = "Zurück";
            modulBackButton =
                new JButton(AuikUtils.getIcon(16, "back.png", desc));

            modulBackButton.setToolTipText(desc);
            modulBackButton.setForeground(
                    getRightFrame().getTextForeground(true));
            modulBackButton.setOpaque(false);

            modulBackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manager.back();
                }
            });

            modulBackButton.setEnabled(false);
        }
        return modulBackButton;
    }

    /**
     * @return Returns the modulFwdButton.
     */
    protected JButton getModulFwdButton() {
        if (modulFwdButton == null) {
            String desc = "Vor";
            modulFwdButton =
                new JButton(AuikUtils.getIcon(16, "forward.png", desc));

            modulFwdButton.setToolTipText(desc);
            modulFwdButton.setForeground(
                    getRightFrame().getTextForeground(true));
            modulFwdButton.setOpaque(false);

            modulFwdButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manager.forward();
                }
            });

            modulFwdButton.setEnabled(false);
        }
        return modulFwdButton;
    }

    /**
     * Zentriert ein Fenster auf dem Desktop.
     */
    public void locateOnScreen(Window win) {
        Dimension paneSize   = win.getSize();
        Dimension screenSize = this.getToolkit().getScreenSize();
        win.setLocation(
                (screenSize.width  - paneSize.width)  / 2,
                (screenSize.height - paneSize.height) / 2);
    }

    /**
     * Schließt das Fenster und beendet die Applikation.
     */
    public void close() {
        // Dieses Fenster zerstören
        this.dispose();

        if (manager != null && manager.getCurrentModul() != null) {
            // Dem momentanen Modul mitteilen, dass es nicht mehr angezeigt wird
            manager.getCurrentModul().hide();
        }

        // Wenn die Session noch offen sein sollte, schließen wir sie hier
        HibernateSessionFactory.closeSession();

        // Fenster-Status speichern
        if ((this.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
            settings.setSetting("auik.prefs.maximized", true, true);
        } else {
            settings.setSetting("auik.prefs.maximized", false, true);
            if (settings.getBoolSetting("auik.prefs.save_size")) {
                settings.setSetting("auik.prefs.res_x", this.getWidth(), true);
                settings.setSetting("auik.prefs.res_y", this.getHeight(), true);
            }
        }
        // Alle persistenten Settings speichern
        settings.saveSettings();

        log.debug("Programm beendet!");
        System.exit(0);
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new java.awt.BorderLayout());

            jContentPane.add(getTitlePanel(), java.awt.BorderLayout.NORTH);
            jContentPane.add(getSplitPane(), java.awt.BorderLayout.CENTER);
            jContentPane.add(getStatusPanel(), java.awt.BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    /**
     * This method initializes hauptMenue
     *
     * @return javax.swing.JMenuBar
     */
    private JMenuBar getHauptMenue() {
        if (hauptMenue == null) {
            hauptMenue = new JMenuBar();
            hauptMenue.add(getFileMenu());
            // hauptMenue.add(Box.createHorizontalGlue());
            hauptMenue.add(getHelpMenu());
            hauptMenue.putClientProperty(
                    Options.HEADER_STYLE_KEY, HeaderStyle.SINGLE);
        }
        return hauptMenue;
    }

    /**
     * This method initializes fileMenu
     *
     * @return javax.swing.JMenu
     */
    private JMenu getFileMenu() {
        if (fileMenu == null) {
            fileMenu = new JMenu();
            fileMenu.setText("Datei");
            fileMenu.setMnemonic(KeyEvent.VK_D);
            // fileMenu.putClientProperty(Options.NO_ICONS_KEY, Boolean.TRUE);
            fileMenu.add(getSettingsMenuItem());
            fileMenu.addSeparator();
            fileMenu.add(getExitMenuItem());
        }
        return fileMenu;
    }

    /**
     * This method initializes helpMenu
     *
     * @return javax.swing.JMenu
     */
    private JMenu getHelpMenu() {
        if (helpMenu == null) {
            helpMenu = new JMenu();
            helpMenu.setText("Hilfe");
            helpMenu.setMnemonic(KeyEvent.VK_H);
            helpMenu.putClientProperty(Options.NO_ICONS_KEY, Boolean.TRUE);
            helpMenu.add(getAboutMenuItem());
            helpMenu.add(getDoku());
        }
        return helpMenu;
    }

    /**
     * This method initializes settingsMenuItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getSettingsMenuItem() {
        if (settingsMenuItem == null) {
            settingsMenuItem = new JMenuItem();
            settingsMenuItem.setText("Einstellungen");
            settingsMenuItem.setMnemonic(KeyEvent.VK_E);
            settingsMenuItem.setEnabled(false);
            // TODO: Einstellungs-Dialog
        }
        return settingsMenuItem;
    }

    /**
     * This method initializes exitMenuItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getExitMenuItem() {
        if (exitMenuItem == null) {
            exitMenuItem = new JMenuItem(getCloseAction());
            // exitMenuItem.setIcon(AuikUtils.getIcon(16, "exit.png"));
        }
        return exitMenuItem;
    }

    /**
     * This method initializes aboutMenuItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getAboutMenuItem() {
        if (aboutMenuItem == null) {
            aboutMenuItem = new JMenuItem();
            aboutMenuItem.setText("Info");
            aboutMenuItem.setMnemonic(KeyEvent.VK_O);
            aboutMenuItem
                    .addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            guiManager.showInfoMessage(
                                    "<html><table valign='top'>"
                                    + "<tr><th align='left'>Version:</th><td>"
                                    + guiManager.getVersion() + "</td>"
                                    + "<tr><th align='left'>Copyright:</th>"
                                    + "<td>2005 - 2011 Umweltamt Bielefeld</td></tr>"
                                    + "<tr><th align='left'>Projekthomepage:</th>"
// TODO: Get the URL (and other stuff) from the Manifest
                                    + "<td><a href='http://auik.wald.intevation.org'>http://auik.wald.intevation.org</a></td></tr>"
                                    + "<tr><th align='left'>Datenbank: </th><td>"
                                    + HibernateSessionFactory.getDBUrl()
                                    + "</td></tr>"
// There is no kind of debug mode - just different levels of verbosity which is
// not influenced by this variable any more.
                                    // + "<tr><th align='left'>Debug-Modus: </th><td>"
                                    // + ((AUIKataster.DEBUG) ? "An" : "Aus")
                                    // + "</td></tr>"
// TODO: Maybe add log level here -  but which? We can have different levels all
// over the place...
                                    + "<tr><th align='left'>Autoren:</th><td>"
                                    + guiManager.getAuthorsAsHTML()
                                    + "</td></tr>"
                                    + "<tr><th align='left'>Weiterentwicklung seit 2011:</th><td>"
                                    + "<table valign='top'><tr><td>Intevation GmbH</td><td><img src='"
                                    + HauptFrame.class.getResource("gui/images/icons/intevation-logo-50ppi-nontrans.png")
                                    + "'></td></tr></table>"
                                    + "</td></tr>" + "</table></html>",
                                    "Info über's " + GUIManager.SHORT_NAME);
                        }
                    });
        }
        return aboutMenuItem;
    }

    private JMenuItem getDoku() {
        if (DokuItem == null) {
            DokuItem = new JMenuItem();
            DokuItem.setText("Handbuch");
            DokuItem.setMnemonic(KeyEvent.VK_B);
            DokuItem.setEnabled(true);
            DokuItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String cmdstart[] = { "cmd", "/c", "explorer",
                                "X:\\Orga\\360\\360-3\\360-3-3\\Alle\\Datenbanken\\Bedienungsanleitung AUIK" };
                        Runtime.getRuntime().exec(cmdstart);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return DokuItem;
    }

    /**
     * @return Returns the titlePanel.
     */
    private JPanel getTitlePanel() {
        if (titlePanel == null) {
            titlePanel = new GradientPanel(new BorderLayout(), Color.WHITE);
            titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            titlePanel.add(
                    new JLabel(AuikUtils.getIcon(32, "uaicon32.png",
                            GUIManager.SHORT_NAME), JLabel.LEADING),
                    BorderLayout.WEST);
            JLabel titleLabel = new JLabel(GUIManager.LONG_NAME,
                    JLabel.TRAILING);
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            titlePanel.add(titleLabel, BorderLayout.CENTER);
        }
        return titlePanel;
    }

    /**
     * This method initializes statusPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getStatusPanel() {
        if (statusPanel == null) {
            statusLabel = new JLabel();
            statusPanel = new JPanel();
            statusPanel.setLayout(new BorderLayout());

            statusLabel.setText("Willkommen im AUI-K");
            statusLabel.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 1));

            statusPanel.add(statusLabel, BorderLayout.CENTER);
        }
        return statusPanel;
    }

    /**
     * This method initializes splitPane
     *
     * @return javax.swing.JSplitPane
     */
    private JSplitPane getSplitPane() {
        if (splitPane == null) {
            splitPane = Factory.createStrippedSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT, getLeftFrame(),
                    getRightFrame(), 0.08f);
        }
        return splitPane;
    }

    /**
     * This method initializes leftFrame
     *
     * @return com.jgoodies.uif_life.panel.SimpleInternalFrame
     */
    protected SimpleInternalFrame getLeftFrame() {
        if (leftFrame == null) {
            leftFrame = new SimpleInternalFrame(" ");

            leftFrame.setMinimumSize(new Dimension(100, 0));
            leftFrame.setPreferredSize(leftFrame.getMinimumSize());

            leftFrame.setToolBar(getViewMenuBar());
            leftFrame.setContent(getLeftScroller());
        }
        return leftFrame;
    }

    /**
     * This method initializes rightFrame
     *
     * @return com.jgoodies.uif_life.panel.SimpleInternalFrame
     */
    protected SimpleInternalFrame getRightFrame() {
        if (rightFrame == null) {
            rightFrame = new SimpleInternalFrame(" ");

            rightFrame.setMinimumSize(new Dimension(250, 0));

            rightFrame.setToolBar(getModulBar());
            rightFrame.setContent(getRightCardPanel());
        }
        return rightFrame;
    }

    /**
     * @return Returns the rightCardPanel.
     */
    protected JPanel getRightCardPanel() {
        if (rightCardPanel == null) {
            rightCardPanel = new JPanel(new CardLayout());
            // rightCardPanel.setBorder(BorderFactory.createEmptyBorder(5,2,5,2));
        }
        return rightCardPanel;
    }

    /**
     * This method initializes leftScroller
     *
     * @return Returns the leftScroller.
     */
    private JScrollPane getLeftScroller() {
        if (leftScroller == null) {
            leftScroller = new JScrollPane(
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            leftScroller.setBorder(BorderFactory.createEmptyBorder());
            leftScroller.setViewportView(getLeftCardPanel());
        }
        return leftScroller;
    }

    /**
     * @return Returns the leftCardPanel.
     */
    protected JPanel getLeftCardPanel() {
        if (leftCardPanel == null) {
            leftCardPanel = new JPanel(new CardLayout());
            // leftCardPanel.setBorder(BorderFactory.createEmptyBorder(5,2,5,2));
        }
        return leftCardPanel;
    }

    /**
     * Liefert den aktuellen ModulManager.
     *
     * @return Den aktuellen ModulManager.
     */
    public ModulManager getManager() {
        return manager;
    }

    /**
     * Zeigt Text in der Statuszeile an.
     *
     * @param txt Der anzuzeigende Text
     */
    public void changeStatus(String txt) {
        if (txt.equals("")) {
            clearStatus();
        } else {
            changeStatus(txt, NORMAL_COLOR);
        }
    }

    /**
     * Zeigt Text in einer bestimmten Farbe in der Statuszeile an.<br>
     * Für Fehler/Erfolgsmeldungen die statischen Konstanten
     * ERROR_COLOR/SUCCESS_COLOR dieser Klasse benutzen.
     *
     * @param txt Der anzuzeigende Text
     * @param color Die Textfarbe
     */
    public void changeStatus(String txt, Color color) {
        statusLabel.setForeground(color);
        statusLabel.setText(txt);

        if (clearStatusTimer.isRunning()) {
            clearStatusTimer.restart();
        } else {
            clearStatusTimer.start();
        }
    }

    /**
     * Löscht den in der Statuszeile angezeigten Text.
     */
    public void clearStatus() {
        statusLabel.setForeground(NORMAL_COLOR);
        statusLabel.setText(" ");

        clearStatusTimer.stop();
    }

    public File openFile() {
        return openOrSaveFile(null, null, FILE_OPEN);
    }

    public File openFile(String[] extensions) {
        return openOrSaveFile(null, extensions, FILE_OPEN);
    }

    public File saveFile() {
        return openOrSaveFile(null, null, FILE_SAVE);
    }

    public File saveFile(String[] extensions) {
        return openOrSaveFile(null, extensions, FILE_SAVE);
    }

    private File openOrSaveFile(
            File directory, String[] extensions, int openOrSave) {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }

        if (directory == null) {
            String lastDir =
                manager.getSettingsManager().getSetting("auik.prefs.last_dir");
            if (lastDir != null) {
                File tmp = new File(lastDir);
                if (tmp.isDirectory()) {
                    directory = tmp;
                }
            }
        }

        fileChooser.setCurrentDirectory(directory);

        fileChooser.resetChoosableFileFilters();

        if (extensions != null) {
            for (int i = 0; i < extensions.length; i++) {
                fileChooser.addChoosableFileFilter(
                        AuikUtils.getExtensionFilter(extensions[i]));
            }

            if (extensions.length > 1) {
                fileChooser.addChoosableFileFilter(
                        AuikUtils.getExtensionsFilter(extensions));
            }
        }

        int retVal = JFileChooser.CANCEL_OPTION;

        if (openOrSave == FILE_OPEN) {
            retVal = fileChooser.showOpenDialog(getRightFrame());
        } else if (openOrSave == FILE_SAVE) {
            retVal = fileChooser.showSaveDialog(getRightFrame());
        }

        if (retVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            manager.getSettingsManager().setSetting(
                    "auik.prefs.last_dir", file.getParent(), true);
            return file;
        } else {
            return null;
        }
    }

    private final class BenutzerDatenDialog extends JDialog {
        private static final long serialVersionUID = 4180635601687537049L;
        private JLabel textLabel;
        private JTextField benutzerFeld;
        private JPasswordField passwortFeld;
        private JButton loginButton;

        private String user, pw;

        private KeyEventDispatcher escListener;

        private boolean success = false;
        private boolean busy = false;

        public BenutzerDatenDialog(Frame owner) throws HeadlessException {
            super(owner, "Willkommen im " + GUIManager.SHORT_NAME, true);
            this.initialize();
        }

        private void initialize() {
            escListener = new KeyEventDispatcher() {
                @Override
                public boolean dispatchKeyEvent(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        if (!busy) {
                            success = false;
                            BenutzerDatenDialog.this.close();
                        }
                    }
                    return false;
                }
            };
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
                    .addKeyEventDispatcher(escListener);
            this.textLabel = new JLabel(
                    "<html>Bitte geben Sie ihren Benutzernamen und <br>"
                    + "ihr Passwort ein und klicken Sie auf <br>"
                    + "den Wullspuffel \"Login\".</html>");
            this.benutzerFeld = new JTextField(10);
            if (settings.getSetting("auik.prefs.lastuser") == null) {
                benutzerFeld.setText(System.getProperty("user.name"));
            } else {
                benutzerFeld
                        .setText(settings.getSetting("auik.prefs.lastuser"));
            }
            this.benutzerFeld.selectAll();
            this.passwortFeld = new JPasswordField(10);
            this.loginButton = new JButton("Login");

            this.benutzerFeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    passwortFeld.requestFocus();
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
                    if (!busy) {
                        success = false;
                        BenutzerDatenDialog.this.close();
                    }
                }
            });

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginButton.setEnabled(false);
                    benutzerFeld.setEnabled(false);
                    passwortFeld.setEnabled(false);
                    busy = true;

                    user = benutzerFeld.getText();
                    pw = new String(passwortFeld.getPassword());
                    SwingWorkerVariant worker =
                        new SwingWorkerVariant(benutzerFeld) {
                            boolean tmp = false;

                            @Override
                            protected void doNonUILogic() throws RuntimeException {
                                try {
                                    tmp = HibernateSessionFactory.checkCredentials(
                                            user, pw);
                                } catch (HibernateException e) {
                                    fatalError = true;
                                    // throw new RuntimeException(e);
                                }
                            }

                            @Override
                            protected void doUIUpdateLogic()
                                    throws RuntimeException {
                                if (tmp) {
                                    success = true;
                                    settings.setSetting(
                                            "auik.prefs.lastuser", user, true);
                                    BenutzerDatenDialog.this.close();
                                } else {
                                    GUIManager.getInstance().showErrorMessage(
                                        "Der eingegebene Benutzername oder das Passwort war falsch (oder es ist aus anderen\n"
                                        + "Gründen keine Verbindung mit der Datenbank möglich), bitte versuchen Sie es erneut!",
                                        "Fehler");
                                    benutzerFeld.setEnabled(true);
                                    passwortFeld.setEnabled(true);
                                    loginButton.setEnabled(true);
                                    busy = false;
                                    passwortFeld.setText("");
                                    benutzerFeld.setSelectionStart(0);
                                    benutzerFeld.setSelectionEnd(
                                            benutzerFeld.getText().length());
                                }
                            }
                        };
                    worker.start();
                }
            });

            FormLayout layout = new FormLayout(
                    "right:pref, 4dlu, pref:grow, 4dlu, pref", // Spalten
                    "pref:grow, 3dlu, pref, 3dlu, pref, 5dlu:grow" // Zeilen
            );

            layout.setRowGroups(new int[][]{{3, 5}});

            PanelBuilder builder = new PanelBuilder(layout);
            builder.setDefaultDialogBorder();
            CellConstraints cc = new CellConstraints();

            builder.add(textLabel, cc.xyw(1, 1, 5));
            builder.addLabel("Benutzer:", cc.xy(1, 3));
            builder.add(benutzerFeld, cc.xy(3, 3));
            builder.addLabel("Passwort:", cc.xy(1, 5));
            builder.add(passwortFeld, cc.xy(3, 5));
            builder.add(loginButton, cc.xy(5, 5));

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

        public boolean loginSuccessful() {
            super.setVisible(true);
            return success;
        }
    }

    // This was never used locally.
//    private void readClipboard() {
//        String tmp = null;
//        Clipboard systemClipboard;
//        systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        Transferable transferData = systemClipboard.getContents(null);
//        for (DataFlavor dataFlavor : transferData.getTransferDataFlavors()) {
//            Object content = null;
//            try {
//                content = transferData.getTransferData(dataFlavor);
//            } catch (UnsupportedFlavorException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            if (content instanceof String) {
//
//                tmp = content.toString();
//            }
//
//                if (tmp != null){
//                    int index = tmp.indexOf("bezeichnung");
//
//                    if (index != -1)
//                    {
//                        char leer = 0;
//                        char ersetzen = '"';
//                        String tmp2 = tmp.replace(ersetzen, leer);
//
//                        int indexBez = tmp2.indexOf("bsb");
//
//                        if (indexBez == -1)
//                        {
//                            indexBez = tmp2.indexOf("entgeb");
//                        }
//
//                        String bezeichnung = tmp2.substring(tmp2.indexOf("bezeichnung")+ 13, indexBez-1 );
//
//
//
//                        AtlSielhaut sielhaut = AtlSielhaut.getSielhautByBez(bezeichnung);
//
//                        if ( sielhaut != null)
//                        {
//                            AtlProbepkt probepunkt = AtlProbepkt.getSielhautProbepunkt(sielhaut);
//                            manager.getSettingsManager().setSetting("auik.imc.edit_object", probepunkt.getBasisObjekt().getObjektid(), false);
//
//
//                            manager.switchModul("m_sielhaut1");
//
//
//                            changeStatus("Daten aus Zwischenablage ausgelesen",
//                            HauptFrame.SUCCESS_COLOR);
//                        }
//                        else
//                        {
//                            changeStatus(
//                            "Zwischenablage enthält keine verwertbaren Daten",
//                            HauptFrame.ERROR_COLOR);
//                        }
//
//
//
//
//                }
//                    else
//                    {
//                        changeStatus(
//                        "Zwischenablage enthält keine verwertbaren Daten",
//                        HauptFrame.ERROR_COLOR);
//                    }
//
//                break;
//                }
//
//
//        }
//
//        if (tmp == null)
//        {
//            changeStatus(
//            "Zwischenablage enthält keine verwertbaren Daten",
//            HauptFrame.ERROR_COLOR);
//        }
//
//
//
//    }

} // @jve:decl-index=0:visual-constraint="10,10"
