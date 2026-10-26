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
package de.bielefeld.umweltamt.aui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import org.hibernate.HibernateException;

import de.bielefeld.umweltamt.aui.gui.PasswordChangeDialog;
import de.bielefeld.umweltamt.aui.module.GebuehrenEditor;
import de.bielefeld.umweltamt.aui.module.common.editors.EinstellungenEditor;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.GradientPanel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TitledPanel;

/**
 * Das Hauptfenster des AUI-K.
 * @author David Klotz
 */
public class HauptFrame extends JFrame {
    /** GUI Manager */
    private static final GUIManager guiManager =
        GUIManager.getInstance();
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    private static final long serialVersionUID =
        4233252729652695263L;

    public static final Color ERROR_COLOR =
        new Color(200, 0, 0);
    public static final Color SUCCESS_COLOR =
        new Color(0, 128, 0);
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
    private JMenu editMenu = null;
    private JMenuItem feeMenuItem = null;
    private JMenuItem passwordChangeMenuItem = null;
    private JPanel titlePanel = null;
    private JPanel statusPanel = null;
    private JLabel statusLabel = null;
    private JSplitPane splitPane = null;
    private TitledPanel leftFrame = null;
    private JScrollPane leftScroller = null;
    private JPanel leftCardPanel = null;
    private TitledPanel rightFrame = null;
    private JPanel rightCardPanel = null;
    private JPopupMenu viewMenu = null;
    private JToolBar viewMenuBar = null;
    private JButton viewMenuButton = null;
    private JToolBar modulBar = null;
    private JButton modulBackButton = null;
    private JButton modulFwdButton = null;
    private ModulManager manager;
    private SettingsManager settings;
    private Timer clearStatusTimer;

    public HauptFrame(SettingsManager settings) {
        super();
        this.settings = settings;
        initialize();
    }

    private void initialize() {
        try {

            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());


            // Options.setUseSystemFonts(true)
            Font defaultFont =
                new Font("SansSerif", Font.PLAIN, 12);
            UIManager.put("Button.font", defaultFont);
            UIManager.put("Label.font", defaultFont);
            UIManager.put("TextField.font", defaultFont);
            UIManager.put(
                "FormattedTextField.font", defaultFont);
            UIManager.put("ComboBox.font", defaultFont);
            UIManager.put("Table.font", defaultFont);
            UIManager.put("TableHeader.font", defaultFont);
            UIManager.put("TextArea.font", defaultFont);
            UIManager.put("CheckBox.font", defaultFont);
            UIManager.put("RadioButton.font", defaultFont);
            UIManager.put("TitledBorder.font", defaultFont);
            UIManager.put("Menu.font", defaultFont);
            UIManager.put("MenuItem.font", defaultFont);
            UIManager.put("PopupMenu.font", defaultFont);
            UIManager.put("ToolTip.font", defaultFont);
            UIManager.put("List.font", defaultFont);
            UIManager.put("Panel.font", defaultFont);
            UIManager.put("ScrollPane.font", defaultFont);
            UIManager.put("Viewport.font", defaultFont);
            UIManager.put("TabbedPane.font", defaultFont);
            UIManager.put("ToolBar.font", defaultFont);
            UIManager.put("ToggleButton.font", defaultFont);
            UIManager.put("Tree.font", defaultFont);
            UIManager.put("PasswordField.font", defaultFont);
            UIManager.put("Spinner.font", defaultFont);
         // Helle Linienfarbe wie im Original
         UIManager.put("Table.gridColor",
             new Color(220, 220, 220));
         // Hintergrundfarbe der Tabelle
         UIManager.put("Table.background",
             new Color(255, 255, 255));
         // Abwechselnde Zeilenfarbe (optional)
         UIManager.put("Table.alternateRowColor",
             new Color(245, 245, 245));
         // Selektionsfarbe
         UIManager.put("Table.selectionBackground",
             new Color(173, 216, 230));
         UIManager.put("Table.selectionForeground",
             new Color(0, 0, 0));
         // Zeilenhöhe
         UIManager.put("Table.rowHeight", 18);

        } catch (Exception e) {
            log.debug("Konnte Look & Feel nicht ändern!");
        }

        this.setJMenuBar(getHauptMenue());
        this.setSize(
            settings.getIntSetting("auik.prefs.res_x"),
            settings.getIntSetting("auik.prefs.res_y"));
        if (settings.getBoolSetting("auik.prefs.maximized")) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        this.setContentPane(getJContentPane());
        ImageIcon icon = (ImageIcon) AuikUtils.getIcon(
            16, "uaicon16.png", "Umweltamt");
        this.setIconImage(icon.getImage());
        this.setTitle(GUIManager.SHORT_NAME);
        this.addWindowListener(
            new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(
                    java.awt.event.WindowEvent e) {
                    HauptFrame.this.close();
                }
            });
        locateOnScreen(this);
        clearStatusTimer = new Timer(
            settings.getIntSetting("auik.prefs.status_time")
                * 1000,
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearStatus();
                }
            });
        clearStatusTimer.setRepeats(false);
        String db = settings.getSetting("auik.system.dburl");
        if (db != null && !db.equals("")) {
            HibernateSessionFactory.setDBUrl(db);
            String dialectName = db.split(":")[1];
            try {
                log.info(String.format(
                    "Database dialect: %s", dialectName));
                String dialect = HibernateSessionFactory
                    .getDialectClassByDialectName(dialectName);
                String driver = HibernateSessionFactory
                    .getDriverByDialectClass(dialect);
                HibernateSessionFactory.setDBDialect(dialect);
                HibernateSessionFactory.setDBDriver(driver);
            } catch (IllegalArgumentException iae) {
                log.error(
                    "Error setting up db driver: " + iae);
                this.close();
            }
        }
        if (askForDBCredentials()) {
            this.manager = new ModulManager(this, settings);
            manager.loadModule();
            this.setVisible(true);
        } else {
            this.close();
        }
    }

    private boolean askForDBCredentials() {
        if (!HibernateSessionFactory
            .doesDialectNeedCredentials()) {
            return true;
        }
        BenutzerDatenDialog benutzerDialog =
            new BenutzerDatenDialog(this);
        locateOnScreen(benutzerDialog);
        return benutzerDialog.loginSuccessful();
    }

    public Action getCloseAction() {
        if (closeAction == null) {
            closeAction = new AbstractAction("Beenden",
                AuikUtils.getIcon(32, "exit.png")) {
                private static final long serialVersionUID =
                    2119355762465447730L;
                @Override
                public void actionPerformed(ActionEvent e) {
                    HauptFrame.this.close();
                }
            };
            closeAction.putValue(
                Action.SHORT_DESCRIPTION,
                "Beendet das Programm");
            closeAction.putValue(
                Action.MNEMONIC_KEY,
                Integer.valueOf(KeyEvent.VK_B));
            closeAction.putValue(
                Action.SMALL_ICON,
                AuikUtils.getIcon(16, "exit.png"));
        }
        return closeAction;
    }

    protected ActionListener getButtonListener() {
        if (buttonListener == null) {
            buttonListener = new ActionListener() {
                @Override
                public void actionPerformed(
                    java.awt.event.ActionEvent e) {
                    manager.switchModul(
                        e.getActionCommand());
                }
            };
        }
        return buttonListener;
    }

    public JPopupMenu getViewMenu() {
        if (viewMenu == null) {
            viewMenu = new JPopupMenu();
        }
        return viewMenu;
    }

    private JToolBar getViewMenuBar() {
        if (viewMenuBar == null) {
            viewMenuBar = new JToolBar();
            viewMenuBar.putClientProperty(
                "JToolBar.isRollover", Boolean.TRUE);
            viewMenuBar.setFloatable(false);
            viewMenuBar.setOpaque(false);
            viewMenuBar.add(getViewMenuButton());
        }
        return viewMenuBar;
    }

    public JButton getViewMenuButton() {
        if (viewMenuButton == null) {
            viewMenuButton = new JButton("",
                AuikUtils.getIcon(
                    16, "view_menu.gif", "Kategorien"));
            viewMenuButton.addActionListener(
                new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(
                        java.awt.event.ActionEvent e) {
                        Point np = SwingUtilities.convertPoint(
                            getLeftFrame(), 0, 0,
                            viewMenuButton);
                        getViewMenu().show(
                            viewMenuButton, np.x,
                            viewMenuButton.getHeight());
                    }
                });
            viewMenuButton.setOpaque(false);
            viewMenuButton.setHorizontalTextPosition(
                JButton.LEADING);
            viewMenuButton.setToolTipText(
                "Hier klicken, um die Kategorie zu wechseln");
        }
        return viewMenuButton;
    }


    private JToolBar getModulBar() {
        if (modulBar == null) {
            modulBar = new JToolBar();
            modulBar.putClientProperty(
                "JToolBar.isRollover", Boolean.TRUE);
            modulBar.setFloatable(false);
            modulBar.setOpaque(false);
            modulBar.add(getModulBackButton());
            modulBar.add(getModulFwdButton());
        }
        return modulBar;
    }

    protected JButton getModulBackButton() {
        if (modulBackButton == null) {
            String desc = "Zurück";
            modulBackButton = new JButton(
                AuikUtils.getIcon(16, "back.png", desc));
            modulBackButton.setToolTipText(desc);
            modulBackButton.setOpaque(false);
            modulBackButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        manager.back();
                    }
                });
            modulBackButton.setEnabled(false);
        }
        return modulBackButton;
    }

    protected JButton getModulFwdButton() {
        if (modulFwdButton == null) {
            String desc = "Vor";
            modulFwdButton = new JButton(
                AuikUtils.getIcon(16, "forward.png", desc));
            modulFwdButton.setToolTipText(desc);
            modulFwdButton.setOpaque(false);
            modulFwdButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        manager.forward();
                    }
                });
            modulFwdButton.setEnabled(false);
        }
        return modulFwdButton;
    }

    public void locateOnScreen(Window win) {
        Dimension paneSize = win.getSize();
        Dimension screenSize =
            this.getToolkit().getScreenSize();
        win.setLocation(
            (screenSize.width - paneSize.width) / 2,
            (screenSize.height - paneSize.height) / 2);
    }

    public void close() {
        this.dispose();
        if (manager != null
            && manager.getCurrentModul() != null) {
            manager.getCurrentModul().hide();
        }
        HibernateSessionFactory.closeSession();
        if ((this.getExtendedState()
            & JFrame.MAXIMIZED_BOTH)
            == JFrame.MAXIMIZED_BOTH) {
            settings.setSetting(
                "auik.prefs.maximized", true, true);
        } else {
            settings.setSetting(
                "auik.prefs.maximized", false, true);
            if (settings.getBoolSetting(
                "auik.prefs.save_size")) {
                settings.setSetting(
                    "auik.prefs.res_x",
                    this.getWidth(), true);
                settings.setSetting(
                    "auik.prefs.res_y",
                    this.getHeight(), true);
            }
        }
        settings.saveSettings();
        log.debug("Programm beendet!");
        System.exit(0);
    }

    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(
                new java.awt.BorderLayout());
            jContentPane.add(getTitlePanel(),
                java.awt.BorderLayout.NORTH);
            jContentPane.add(getSplitPane(),
                java.awt.BorderLayout.CENTER);
            jContentPane.add(getStatusPanel(),
                java.awt.BorderLayout.SOUTH);
        }
        return jContentPane;
    }


    private JMenuBar getHauptMenue() {
        if (hauptMenue == null) {
            hauptMenue = new JMenuBar();
            hauptMenue.add(getFileMenu());
            hauptMenue.add(getEditMenu());
            hauptMenue.add(getHelpMenu());
        }
        return hauptMenue;
    }


    private JMenu getFileMenu() {
        if (fileMenu == null) {
            fileMenu = new JMenu();
            fileMenu.setText("Datei");
            fileMenu.setMnemonic(KeyEvent.VK_D);
            fileMenu.add(getPasswordChangeItem());
            fileMenu.add(getSettingsMenuItem());
            fileMenu.addSeparator();
            fileMenu.add(getExitMenuItem());
        }
        return fileMenu;
    }

    private JMenu getEditMenu() {
        if (editMenu == null) {
            editMenu = new JMenu();
            editMenu.setText("Bearbeiten");
            editMenu.setMnemonic(KeyEvent.VK_B);
            editMenu.add(getFeeMenuItem());
        }
        return editMenu;
    }

    private JMenu getHelpMenu() {
        if (helpMenu == null) {
            helpMenu = new JMenu();
            helpMenu.setText("Hilfe");
            helpMenu.setMnemonic(KeyEvent.VK_H);
            helpMenu.add(getAboutMenuItem());
            helpMenu.add(getDoku());
        }
        return helpMenu;
    }

    private JMenuItem getFeeMenuItem() {
        if (feeMenuItem == null) {
            feeMenuItem = new JMenuItem();
            feeMenuItem.setText("Gebühren");
            feeMenuItem.setMnemonic(KeyEvent.VK_G);
            feeMenuItem.setEnabled(true);
            final JFrame owner = this;
            feeMenuItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        java.awt.event.ActionEvent e) {
                        GebuehrenEditor gebuehrenPanel =
                            new GebuehrenEditor(owner);
                        gebuehrenPanel
                            .setLocationRelativeTo(
                                getRightFrame());
                        gebuehrenPanel.setVisible(true);
                    }
                });
        }
        return feeMenuItem;
    }

    private JMenuItem getPasswordChangeItem() {
        if (passwordChangeMenuItem == null) {
            passwordChangeMenuItem = new JMenuItem();
            passwordChangeMenuItem
                .setText("Password ändern");
            passwordChangeMenuItem
                .setMnemonic(KeyEvent.VK_P);
            passwordChangeMenuItem.setEnabled(true);
            final HauptFrame owner = this;
            passwordChangeMenuItem.addActionListener(
                new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(
                        java.awt.event.ActionEvent e) {
                        PasswordChangeDialog pwChangeDialog =
                            new PasswordChangeDialog(owner);
                        pwChangeDialog
                            .setLocationRelativeTo(
                                getRightFrame());
                        pwChangeDialog.setVisible(true);
                    }
                });
        }
        return passwordChangeMenuItem;
    }

    private JMenuItem getSettingsMenuItem() {
        if (settingsMenuItem == null) {
            settingsMenuItem = new JMenuItem();
            settingsMenuItem.setText("Einstellungen");
            settingsMenuItem.setMnemonic(KeyEvent.VK_E);
            settingsMenuItem.setEnabled(true);
            settingsMenuItem.addActionListener(
                new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(
                        java.awt.event.ActionEvent e) {
                        EinstellungenEditor editor =
                            new EinstellungenEditor(
                                settings.getSettingList(),
                                GUIManager.getInstance()
                                    .getRunningFrame());
                        editor.setVisible(true);
                        editor.setEnabled(true);
                    }
                });
        }
        return settingsMenuItem;
    }

    private JMenuItem getExitMenuItem() {
        if (exitMenuItem == null) {
            exitMenuItem =
                new JMenuItem(getCloseAction());
        }
        return exitMenuItem;
    }

    private JMenuItem getAboutMenuItem() {
        if (aboutMenuItem == null) {
            aboutMenuItem = new JMenuItem();
            aboutMenuItem.setText("Info");
            aboutMenuItem.setMnemonic(KeyEvent.VK_O);
            aboutMenuItem.addActionListener(
                new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(
                        java.awt.event.ActionEvent e) {
                        guiManager.showInfoMessage(
                            "<html><table valign='top'>"
                            + "<tr><th align='left'>"
                            + "Version:</th><td>"
                            + guiManager.getVersion()
                            + "</td>"
                            + "<tr><th align='left'>"
                            + "Copyright:</th>"
                            + "<td>2005 - 2011 Umweltamt"
                            + " Bielefeld</td></tr>"
                            + "<tr><th align='left'>"
                            + "Projekthomepage:</th>"
                            + "<td><a href='http://auik"
                            + ".wald.intevation.org'>"
                            + "http://auik.wald"
                            + ".intevation.org</a></td></tr>"
                            + "<tr><th align='left'>"
                            + "Datenbank: </th><td>"
                            + HibernateSessionFactory
                                .getDBUrl()
                            + "</td></tr>"
                            + "<tr><th align='left'>"
                            + "Autoren:</th><td>"
                            + guiManager.getAuthorsAsHTML()
                            + "</td></tr>"
                            + "<tr><th align='left'>"
                            + "Weiterentwicklung seit"
                            + " 2011:</th><td>"
                            + "<table valign='top'><tr><td>"
                            + "Intevation GmbH</td><td>"
                            + "<img src='"
                            + HauptFrame.class.getResource(
                                "gui/images/icons/"
                                + "intevation-logo-50ppi"
                                + "-nontrans.png")
                            + "'></td></tr></table>"
                            + "</td></tr>"
                            + "</table></html>",
                            "Info über's "
                            + GUIManager.SHORT_NAME);
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
            DokuItem.addActionListener(
                new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(
                        java.awt.event.ActionEvent e) {
                        try {
                            String cmdstart[] = {
                                "cmd", "/c", "explorer",
                                "X:\\Orga\\360\\360-3\\"
                                + "360-3-3\\Alle\\"
                                + "Datenbanken\\"
                                + "Bedienungsanleitung AUIK"
                            };
                            Runtime.getRuntime()
                                .exec(cmdstart);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
        }
        return DokuItem;
    }

    private JPanel getTitlePanel() {
        if (titlePanel == null) {
            titlePanel = new GradientPanel(
                new BorderLayout(), Color.WHITE);
            titlePanel.setBorder(
                BorderFactory.createEmptyBorder(
                    5, 5, 5, 5));
            titlePanel.add(
                new JLabel(AuikUtils.getIcon(
                    32, "uaicon32.png",
                    GUIManager.SHORT_NAME),
                    JLabel.LEADING),
                BorderLayout.WEST);
            JLabel titleLabel = new JLabel(
                GUIManager.LONG_NAME, JLabel.TRAILING);
            titleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 18));
            titlePanel.add(titleLabel, BorderLayout.CENTER);
        }
        return titlePanel;
    }

    private JPanel getStatusPanel() {
        if (statusPanel == null) {
            statusLabel = new JLabel();
            statusPanel = new JPanel();
            statusPanel.setLayout(new BorderLayout());
            statusLabel.setText("Willkommen im AUI-K");
            statusLabel.setBorder(
                BorderFactory.createEmptyBorder(
                    1, 5, 1, 1));
            statusPanel.add(
                statusLabel, BorderLayout.CENTER);
        }
        return statusPanel;
    }


    private JSplitPane getSplitPane() {
        if (splitPane == null) {
            splitPane =
                ComponentFactory.createStrippedSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT,
                    getLeftFrame(),
                    getRightFrame(), 0.08f);
        }
        return splitPane;
    }


    protected TitledPanel getLeftFrame() {
        if (leftFrame == null) {
            leftFrame = new TitledPanel(" ");
            leftFrame.setMinimumSize(
                new Dimension(100, 100));
            leftFrame.setPreferredSize(
                leftFrame.getMinimumSize());
            leftFrame.setToolBar(getViewMenuBar());
            leftFrame.setContent(getLeftScroller());
        }
        return leftFrame;
    }


    protected TitledPanel getRightFrame() {
        if (rightFrame == null) {
            rightFrame = new TitledPanel(" ");
            rightFrame.setMinimumSize(
                new Dimension(250, 100));
            rightFrame.setToolBar(getModulBar());
            rightFrame.setContent(getRightCardPanel());
        }
        return rightFrame;
    }

    protected JPanel getRightCardPanel() {
        if (rightCardPanel == null) {
            rightCardPanel = new JPanel(new CardLayout());
        }
        return rightCardPanel;
    }


    private JScrollPane getLeftScroller() {
        if (leftScroller == null) {
            leftScroller = new JScrollPane(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            leftScroller.setBorder(
                BorderFactory.createEmptyBorder());
            leftScroller.setViewportView(
                getLeftCardPanel());
        }
        return leftScroller;
    }


    protected JPanel getLeftCardPanel() {
        if (leftCardPanel == null) {
            leftCardPanel = new JPanel(new CardLayout());
        }
        return leftCardPanel;
    }

    public ModulManager getManager() {
        return manager;
    }

    public void changeStatus(String txt) {
        if (txt.equals("")) {
            clearStatus();
        } else {
            changeStatus(txt, NORMAL_COLOR);
        }
    }

    public void changeStatus(String txt, Color color) {
        statusLabel.setForeground(color);
        statusLabel.setText(txt);
        if (clearStatusTimer.isRunning()) {
            clearStatusTimer.restart();
        } else {
            clearStatusTimer.start();
        }
    }

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
        File directory,
        String[] extensions,
        int openOrSave) {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }
        if (directory == null) {
            String lastDir = manager.getSettingsManager()
                .getSetting("auik.prefs.last_dir");
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
                    AuikUtils.getExtensionFilter(
                        extensions[i]));
            }
            if (extensions.length > 1) {
                fileChooser.addChoosableFileFilter(
                    AuikUtils.getExtensionsFilter(
                        extensions));
            }
        }
        int retVal = JFileChooser.CANCEL_OPTION;
        if (openOrSave == FILE_OPEN) {
            retVal = fileChooser.showOpenDialog(
                getRightFrame());
        } else if (openOrSave == FILE_SAVE) {
            retVal = fileChooser.showSaveDialog(
                getRightFrame());
        }
        if (retVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            manager.getSettingsManager().setSetting(
                "auik.prefs.last_dir",
                file.getParent(), true);
            return file;
        } else {
            return null;
        }
    }

    // FormLayout → GridBagLayout
    private final class BenutzerDatenDialog
        extends JDialog {
        private static final long serialVersionUID =
            4180635601687537049L;
        private JLabel textLabel;
        private JTextField benutzerFeld;
        private JPasswordField passwortFeld;
        private JButton loginButton;
        private String user, pw;
        private KeyEventDispatcher escListener;
        private boolean success = false;
        private boolean busy = false;

        public BenutzerDatenDialog(Frame owner)
            throws HeadlessException {
            super(owner, "Willkommen im "
                + GUIManager.SHORT_NAME, true);
            this.initialize();
        }

        private void initialize() {
            escListener = new KeyEventDispatcher() {
                @Override
                public boolean dispatchKeyEvent(
                    KeyEvent e) {
                    if (e.getKeyCode()
                        == KeyEvent.VK_ESCAPE) {
                        if (!busy) {
                            success = false;
                            BenutzerDatenDialog
                                .this.close();
                        }
                    }
                    return false;
                }
            };
            KeyboardFocusManager
                .getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(escListener);

            this.textLabel = new JLabel(
                "<html>Bitte geben Sie ihre"
                + " Kennung und <br>"
                + "ihr Passwort ein und klicken"
                + " Sie auf <br>"
                + "den Button \"Login\".</html>");
            this.benutzerFeld = new JTextField(10);
            if (settings.getSetting(
                "auik.prefs.lastuser") == null) {
                benutzerFeld.setText(
                    System.getProperty("user.name"));
            } else {
                benutzerFeld.setText(
                    settings.getSetting(
                        "auik.prefs.lastuser"));
            }
            this.benutzerFeld.selectAll();
            this.passwortFeld = new JPasswordField(10);
            this.loginButton = new JButton("Login");

            this.benutzerFeld.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        passwortFeld.requestFocus();
                    }
                });
            this.passwortFeld.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        loginButton.requestFocus();
                        loginButton.doClick();
                    }
                });
            this.setDefaultCloseOperation(
                JDialog.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(
                    WindowEvent e) {
                    if (!busy) {
                        success = false;
                        BenutzerDatenDialog.this.close();
                    }
                }
            });

            loginButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                        ActionEvent e) {
                        loginButton.setEnabled(false);
                        benutzerFeld.setEnabled(false);
                        passwortFeld.setEnabled(false);
                        busy = true;
                        user = benutzerFeld.getText();
                        pw = new String(
                            passwortFeld.getPassword());
                        SwingWorkerVariant worker =
                            new SwingWorkerVariant(
                                benutzerFeld) {
                            boolean tmp = false;
                            @Override
                            protected void doNonUILogic()
                                throws RuntimeException {
                                try {
                                    tmp =
                                        HibernateSessionFactory
                                        .checkCredentials(
                                            user, pw);
                                } catch (
                                    HibernateException e) {
                                    fatalError = true;
                                }
                            }
                            @Override
                            protected void doUIUpdateLogic()
                                throws RuntimeException {
                                if (tmp) {
                                    success = true;
                                    settings.setSetting(
                                        "auik.prefs.lastuser",
                                        user, true);
                                    BenutzerDatenDialog
                                        .this.close();
                                } else {
                                    GUIManager.getInstance()
                                        .showErrorMessage(
                                        "Der eingegebene"
                                        + " Kennung oder"
                                        + " das Passwort war"
                                        + " falsch (oder es"
                                        + " ist aus anderen\n"
                                        + "Gründen keine"
                                        + " Verbindung mit der"
                                        + " Datenbank möglich)"
                                        + ", bitte versuchen"
                                        + " Sie es erneut!",
                                        "Fehler");
                                    benutzerFeld
                                        .setEnabled(true);
                                    passwortFeld
                                        .setEnabled(true);
                                    loginButton
                                        .setEnabled(true);
                                    busy = false;
                                    passwortFeld.setText("");
                                    benutzerFeld
                                        .setSelectionStart(0);
                                    benutzerFeld
                                        .setSelectionEnd(
                                        benutzerFeld
                                            .getText()
                                            .length());
                                }
                            }
                        };
                        worker.start();
                    }
                });


            JPanel panel = new JPanel(
                new GridBagLayout());
            GridBagConstraints gbc =
                new GridBagConstraints();
            gbc.insets = new Insets(3, 4, 3, 4);

            // Text Label
            gbc.gridx = 0; gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(textLabel, gbc);

            // Label Benutzer
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(new JLabel("Kennung"), gbc);

            // BenutzerFeld
            gbc.gridx = 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(benutzerFeld, gbc);

            // Label Passwort
            gbc.gridx = 0; gbc.gridy = 2;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(new JLabel("Passwort"), gbc);

            // PasswortFeld
            gbc.gridx = 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(passwortFeld, gbc);

            // Login Button
            gbc.gridx = 2;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            panel.add(loginButton, gbc);

            panel.setBorder(
                BorderFactory.createEmptyBorder(
                    7, 7, 7, 7));

            this.setContentPane(panel);
            this.pack();
            this.passwortFeld.grabFocus();
        }

        public void close() {
            KeyboardFocusManager
                .getCurrentKeyboardFocusManager()
                .removeKeyEventDispatcher(escListener);
            this.dispose();
        }

        public boolean loginSuccessful() {
            super.setVisible(true);
            return success;
        }
    }

    public void resetLeftScroller() {
        JScrollBar vertSB =
            leftScroller.getVerticalScrollBar();
        vertSB.setValue(vertSB.getMinimum());
    }
}