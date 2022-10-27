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
 * $Id: ModulManager.java,v 1.2.2.1 2010-11-23 10:25:58 u633d Exp $
 *
 * Erstellt am 13.01.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:19  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.16  2005/11/02 13:45:45  u633d
 * - Version vom 2.11.
 *
 * Revision 1.15  2005/06/06 15:27:52  u633z
 * - Beschriftung der Modul-Buttons verbessert (Zeilenumbruch wird jetzt automatisch unterstützt)
 *
 * Revision 1.14  2005/05/30 15:41:50  u633z
 * - jsddevelop.jar entfernt, überflüssig, da StringFunc.explode() durch String.split() ersetzt.
 *
 * Revision 1.13  2005/05/30 08:17:15  u633z
 * - DBCache entfernt
 *
 */
package de.bielefeld.umweltamt.aui;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;



import com.jgoodies.forms.factories.Paddings;
import com.l2fprod.common.swing.JButtonBar;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Der ModulManager verwaltet die verschiedenen Programm-Module
 * und Kategorien.
 * @author David Klotz
 */
public class ModulManager {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private class ModulKategorie {
        private String name;
        private JButtonBar buttonBar;
        private List<Modul> module;

        public ModulKategorie(String name) {
            this.name = name;
            this.module = new ArrayList<Modul>();
            this.buttonBar = new JButtonBar(JButtonBar.VERTICAL);

            buttonBar.setBorder(Paddings.EMPTY);
            buttonBar.setBackground(frame.getLeftFrame().getBackground());
        }

        public String getName() {
            return name;
        }

        public JButtonBar getButtonBar() {
            return buttonBar;
        }

        // This was not used.
//        public List<Modul> getModule() {
//            return module;
//        }

        public Modul getFirstModul() {
            if (module.size() > 0) {
                return (Modul) module.get(0);
            } else {
                return null;
            }
        }

        public void addModul(Modul m) {
            if (m.getCategory().equals(name)) {
                module.add(m);
            } else {
                throw new IllegalArgumentException("Dieses Modul gehört nicht in diese Kategorie!");
            }
        }
    }
    private HauptFrame frame;
    private SettingsManager settings;

    private SortedMap<String,ModulKategorie> categories = null;
    private Hashtable<String,Modul> module = null;
    private Hashtable<String,JToggleButton> modulButtons = null;

    private String currentCategory = null;
    private String currentModul = null;
    private List<String> modulHistory = null;
    private int currentIndex = 0;

    /**
     * Erzeugt einen neuen ModulManager für das HauptFrame.
     * @param frame Das HauptFrame
     */
    public ModulManager(HauptFrame frame, SettingsManager settings) {
        this.categories = new TreeMap<String,ModulKategorie>();
        this.module = new Hashtable<String,Modul>();
        this.modulButtons = new Hashtable<String,JToggleButton>();
        this.modulHistory = new ArrayList<String>(15);
        this.frame = frame;
        this.settings = settings;
    }

    /**
     * Lädt alle Module. Diese Methode wird dazu verwendet, die Module der
     * Anwemdung zu laden. Der {@link SettingsManager} liefert die Beschreibung
     * aller Module als String. Diese werden dann mittels Aufruf von {@link
     * loadModul(String)} instanziert und dieser Instanz bekannt gemacht.
     */
    public void loadModule() {

        String tmp = settings.getSetting("auik.system.module");
        if (tmp != null && tmp.length() != 0) {
            String[] modules = tmp.split(",");
            for (int i = 0; i < modules.length; i++) {
                String modul = modules[i].trim();
                loadModul(modul);
            }
        }
    }

    /**
     * Lädt ein Modul anhand seines Klassennamens mittels Reflection. Alle
     * Module m&uuml;ssen im Paket {@linkPlain de.bielefeld.umweltamt.aui.module}
     * enthalten sein - andernfalls kann das entsprechende Modul nicht geladen
     * werden.<br><b>Achtung</b>: Es wird kein Hinweis gegeben, falls ein Modul
     * nicht geladen werden konnte.
     *
     * @param modulName Der Name der Klasse (bspw. "BasisBetreiberNeu")
     */
    private void loadModul(String modulName) {
        Modul m = null;
        try {
            Object mOb = Class.forName("de.bielefeld.umweltamt.aui.module." + modulName).newInstance();
            if (mOb instanceof Modul) {
                m = (Modul) mOb;
            }
        } catch (Exception e) {
            m = null;
            log.error(String.format("Fehler beim Laden des Moduls \"%s\"", modulName));
            e.printStackTrace();
        }

        if (m != null) {
            addModul(m);
        }
    }

    /**
     * Fügt ein neues Modul hinzu.
     * @param m Das neue Modul
     */
    private void addModul(Modul m) {
        log.debug("Füge Modul '" + m.getName() + "' (" + m.getIdentifier()
        		+ ") hinzu...");
        boolean isFirst = false;

        m.setManager(this);
        m.setFrame(frame);

        if (module.isEmpty()) {
            isFirst = true;
        }

        String cat = m.getCategory();

        if (getCategory(cat) == null) {    // Falls diese Kategorie noch nicht existiert
            addCategory(cat);        // ... erstellen wir sie einfach
        }

        ModulKategorie mk = getCategory(cat);
        mk.addModul(m);

        JToggleButton button = new JToggleButton("<html><center>"+m.getName()+"</center></html>", m.getIcon());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setSelected(isFirst);
        button.setActionCommand(m.getIdentifier());
        button.setToolTipText(m.getName());
        button.addActionListener(frame.getButtonListener());

        JButtonBar catToolBar = mk.getButtonBar();
        catToolBar.add(button);

        JScrollPane mScroller = new JScrollPane(m.getPanel());
        mScroller.setBorder(BorderFactory.createEmptyBorder());
        mScroller.getVerticalScrollBar().setUnitIncrement(20);
        frame.getRightCardPanel().add(mScroller, m.getIdentifier());

        module.put(m.getIdentifier(), m);
        modulButtons.put(m.getIdentifier(), button);

        if (isFirst) {
            switchModul(m.getIdentifier(), true);
        }
    }

    /**
     * Fügt eine neue Kategorie hinzu.
     * @param title Der Name der Kategorie
     */
    private void addCategory(String title) {
        if (categories.isEmpty()) {
            frame.getViewMenuButton().setText(title);
            currentCategory = title;
        }

        ModulKategorie mk = new ModulKategorie(title);

        categories.put(mk.getName(), mk);

        frame.getLeftCardPanel().add(mk.getButtonBar(), mk.getName());
        rebuildMenu();
    }

    private void rebuildMenu() {
        frame.getViewMenu().removeAll();
        Iterator<String> it = categories.keySet().iterator();
        while (it.hasNext()) {
            String title = it.next();
            JMenuItem catItem = new JMenuItem(title);
            catItem.setActionCommand(title);
            catItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    switchCategory(e.getActionCommand(), true);
                }
            });
            frame.getViewMenu().add(catItem);
        }
    }

    /**
     * Liefert eine bestimmte Kategorie.
     * @param title Der Name der Kategorie
     * @return Die Kategorie oder <code>null</code> falls keine Kategorie dieses Namens existiert
     */
    private ModulKategorie getCategory(String title) {
        if (categories.containsKey(title)){
            return categories.get(title);
        } else {
            return null;
        }
    }

    /**
     * Wechselt das aktuell angezeigte Modul.
     * @param identifier Der identifier des anzuzeigenden Moduls
     */
    public void switchModul(String identifier) {
        switchModul(identifier, true);
    }

    /**
     * Wechselt das aktuell angezeigte Modul.
     * @param identifier Der identifier des anzuzeigenden Moduls
     * @param addToHistory Soll dieser Wechsel einen neuen Eintrag in der History erzeugen?
     */
    private void switchModul(String identifier, boolean addToHistory) {
        // Alle vorhandenen Module durchgehen ...
        for (Enumeration<Modul> e1 = module.elements(); e1.hasMoreElements(); ) {
            Modul m = e1.nextElement();

            // ... bis wir das finden, das angezeigt werden soll
            if (m.getIdentifier().equals(identifier)) {
                // ... den Button dieses Moduls als selektiert markieren
                JToggleButton button = modulButtons.get(identifier);
                button.setSelected(true);

                // ... die rechte Überschrift auf den Titel dieses Moduls setzen
                frame.getRightFrame().setTitle(m.getName());
                // ... speichern, welches das aktuelle Modul ist
                currentModul = identifier;

                if (addToHistory) {
                    if ((modulHistory.size() > 0) && (currentIndex < (modulHistory.size()-1))) {
                        modulHistory.subList(currentIndex+1, modulHistory.size()).clear();
                    }

                    String lastModul = "";
                    if (modulHistory.size() > 0) {
                        lastModul = modulHistory.get(modulHistory.size()-1);
                    }

                    if (!lastModul.equals(currentModul)) {
                        modulHistory.add(currentModul);
                    }

                    currentIndex = modulHistory.size() - 1;
                }

                // ... das Modul anzeigen
                m.show();
                CardLayout cl = (CardLayout) frame.getRightCardPanel().getLayout();
                cl.show(frame.getRightCardPanel(), identifier);

                switchCategory(m.getCategory(), false);
            } else {    // ... bei allen anderen Modulen
                // ... Button als nicht selektiert markieren
                JToggleButton button = modulButtons.get(m.getIdentifier());
                button.setSelected(false);
                // ... Modulen mitteilen, dass sie nicht (mehr) angezeigt werden
                m.hide();
            }
        }

        if (currentIndex > 0) {
            frame.getModulBackButton().setEnabled(true);
        } else {
            frame.getModulBackButton().setEnabled(false);
        }

        if (currentIndex < (modulHistory.size() - 1)) {
            frame.getModulFwdButton().setEnabled(true);
        } else {
            frame.getModulFwdButton().setEnabled(false);
        }

        log.debug("Switched to: " + currentModul + ", Index: " + currentIndex);

        String tmpHist = "History: ";
        for (int i = 0; i < modulHistory.size(); i++) {
            if (i != 0) {
                tmpHist += ", ";
            }
            String mod = modulHistory.get(i);
            if (i == currentIndex) {
                mod = ">" + mod + "<";
            }
            tmpHist += mod;
        }

        log.debug(tmpHist);

        frame.clearStatus();
    }

    public Modul getCurrentModul() {
        return (Modul) module.get(currentModul);
    }

    public void back() {
        if (currentIndex > 0) {
            int lastIndex = currentIndex -1;
            String lastModul = modulHistory.get(lastIndex);
            currentIndex = lastIndex;
            switchModul(lastModul, false);
        }
    }

    public void forward() {
        if (currentIndex < (modulHistory.size() - 1)) {
            int nextIndex = currentIndex + 1;
            String nextModul = modulHistory.get(nextIndex);
            currentIndex = nextIndex;
            switchModul(nextModul, false);
        }
    }

    /**
     * Wechselt die aktuell angezeigte Kategorie.
     * @param title Der Titel der anzuzeigenden Kategorie
     */
    private void switchCategory(String title, boolean switchToFirstMask) {
        if (!title.equals(currentCategory)) {
            CardLayout cl = (CardLayout) frame.getLeftCardPanel().getLayout();
            cl.show(frame.getLeftCardPanel(), title);
            frame.getViewMenuButton().setText(title);
            currentCategory = title;
            frame.resetLeftScroller();
            
            if (switchToFirstMask) {
                ModulKategorie mk = getCategory(title);
                switchModul(mk.getFirstModul().getIdentifier());
            }
        }
    }

    /**
     * Liefert den SettingsManager des aktuellen Programms.
     * TODO: Alle Aufrufe in SettingsManager.getInstance() umwandeln.
     * @return Der SettingsManager
     */
    public SettingsManager getSettingsManager() {
        return settings;
    }
}
