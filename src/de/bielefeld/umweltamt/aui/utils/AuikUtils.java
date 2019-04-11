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
 * Created on 08.02.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;

/**
 * Diverse häufiger benötigte Utility-Methoden, die keiner anderen Klasse
 * zugeordnet werden können.
 * @author David Klotz
 */
public class AuikUtils {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Startet den Standard-Betrachter / -Editor, der vom Betriebssystem mit der
     * Datei <code>f</code> verknüpft ist.
     * @param f Die zu öffnende Datei.
     */
    public static void spawnFileProg(File f) {
//        final File ff = f;

//        Thread spawnThread = new Thread(new Runnable() {
//            public void run() {
        // log.debug("Spawning default Editor for: '" +f+ "'");
        if (f.exists() && f.isFile() && f.canRead()) {
            // String comspec = System.getenv("COMSPEC");
            String comspec = "cmd";
//                    if (comspec == null) {
//                        comspec = "cmd";
//                    }

            try {
//                        Thread.sleep(500);
                /*Runtime.getRuntime().exec(
                    comspec + " /c start \"Bitte warten...\" " + f.getName(),
                    null, f.getParentFile());*/

                //java.awt.Desktop should be platform independent
                Desktop d = Desktop.getDesktop();
                d.open(f);
            } catch (IOException e) {
                throw new RuntimeException("Konnte den Betrachter für " + f
                    + " nicht starten!", e);
            }
        } else {
            log.debug("Fehler beim spawnFileProg für " + f);
        }
//            }
//        });

//        spawnThread.start();
    }

    /**
     * Gets the extension of a file. If the file doesn't have one, returns null;
     * @param f The file.
     */
    public static String getExtension(File f) {
        String ext = null;
        if (f != null) {
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
        }
        return ext;
    }

    /**
     * Liefert einen FileFilter für einen FileChooser, der nur Dateien mit einer
     * bestimmten Erweiterung und Verzeichnisse anzeigt.
     * @param extension Die Erweiterung (bspw. "txt").
     * @return Einen FileFilter, der nur Dateien mit einer bestimmten
     *         Erweiterung anzeigt.
     */
    public static FileFilter getExtensionFilter(final String extension) {
        FileFilter tmp = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }

                String fExtension = AuikUtils.getExtension(f);
                if (fExtension != null) {
                    if (fExtension.equals(extension)) {
                        return true;
                    } else {
                        return false;
                    }
                }

                return false;
            }

            @Override
            public String getDescription() {
                return getFileDescription(extension);
            }
        };

        return tmp;
    }

    /**
     * Liefert einen FileFilter für einen FileChooser, der nur Dateien mit
     * bestimmten Erweiterungen und Verzeichnisse anzeigt.
     * @param extensions Die Erweiterungen (bspw. {"txt", "csv"}).
     * @return Einen FileFilter, der nur Dateien mit bestimmten Erweiterungen
     *         anzeigt.
     */
    public static FileFilter getExtensionsFilter(final String[] extensions) {
        FileFilter tmp = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }

                String fExtension = AuikUtils.getExtension(f);
                if (fExtension != null) {
                    for (int i = 0; i < extensions.length; i++) {
                        if (fExtension.equals(extensions[i])) {
                            return true;
                        }
                    }
                }

                return false;
            }

            @Override
            public String getDescription() {
                String desc = "Alle unterstützten Dateitypen";

                if (extensions.length < 6) {
                    desc += " (";

                    for (int i = 0; i < extensions.length; i++) {
                        if (i != 0) {
                            desc += ", ";
                        }
                        desc += "*." + extensions[i];
                    }

                    desc += ")";
                }
                return desc;
            }
        };

        return tmp;
    }

    /**
     * Liefert eine kurze Beschreibung für Dateien eines bestimmten Typs.
     * @param extension Die Erweiterung (bspw. "txt").
     * @return Die Beschreibung (bspw. "Textdatei (*.txt)").
     */
    public static String getFileDescription(String extension) {
        String ext = extension.toLowerCase();

        String desc;

        if (ext.equals("txt")) {
            desc = "Textdatei";
        } else if (ext.equals("csv")) {
            desc = "CSV (Trennzeichen getrennt)";
        } else if (ext.equals("png")) {
            desc = "PNG-Datei (Portable Network Graphics)";
        } else {
            desc = extension.toUpperCase() + "-Datei";
        }

        return desc + " (*." + ext + ")";
    }

    /**
     * Zeigt einen FileChooser, um eine Tabelle in eine CSV-Datei zu
     * exportieren. Fragt vor dem überschreiben von bereits vorhandenen Dateien
     * nach.
     * @param tabelle Die Tabelle.
     * @param frame Das HauptFrame um den Chooser anzuzeigen und eventuelle
     *            Meldungen auszugeben.
     */
    public static void saveTabelle(JTable tabelle, HauptFrame frame) {
        File exportDatei = frame.saveFile(new String[] {"csv"});
        if (exportDatei != null) {
            String ext = AuikUtils.getExtension(exportDatei);

            if (ext == null) {
                String newExt;
                if (exportDatei.getName().endsWith(".")) {
                    newExt = "csv";
                } else {
                    newExt = ".csv";
                }
                exportDatei = new File(exportDatei.getParent(),
                    exportDatei.getName() + newExt);
            }

            boolean doIt = false;
            if (exportDatei.exists()) {
                boolean answer = GUIManager.getInstance().showQuestion(
                    "Soll die vorhandene Datei " + exportDatei.getName()
                        + " wirklich überschrieben werden?",
                    "Datei bereits vorhanden!");
                if (answer && exportDatei.canWrite()) {
                    doIt = true;
                }
            } else if (exportDatei.getParentFile().canWrite()) {
                doIt = true;
            }

            if (doIt) {
                log.debug("Speichere nach '" + exportDatei.getName()
                    + "' (Ext: '" + ext + "') in '" + exportDatei.getParent()
                    + "' !");
                if (exportTableDataToCVS(tabelle, exportDatei)) {
                    log.debug("Speichern erfolgreich!");
                } else {
                    log.debug("Fehler beim Speichern!");
                    GUIManager.getInstance().showErrorMessage(
                        "Beim Speichern der Datei '" + exportDatei
                            + "' trat ein Fehler auf!");
                }
            }
        }
    }

    /**
     * Speichert den Inhalt einer Tabelle (mit samt überschriften) in eine
     * CSV-Datei (mit Semikolons getrennt).
     * @param table Die Tabelle.
     * @param file Die Datei in die geschrieben werden soll.
     * @return <code>true</code>, wenn alles geklappt hat, sonst
     *         <code>false</code>.
     */
    public static boolean exportTableDataToCVS(JTable table, File file) {
        boolean success;

        TableModel model = table.getModel();
        BufferedWriter bw = null;

        try {
            file.createNewFile();

            bw = new BufferedWriter(new FileWriter(file));

            for (int h = 0; h < model.getColumnCount(); h++) {
                bw.write(model.getColumnName(h));

                if (h + 1 != model.getColumnCount()) {
                    bw.write(";");
                }
            }

            bw.newLine();

            int clmCnt = model.getColumnCount();
            int rowCnt = model.getRowCount();

            for (int i = 0; i < rowCnt; i++) {
                for (int j = 0; j < clmCnt; j++) {
                    if (model.getValueAt(i, j) != null) {
                        String value = model.getValueAt(i, j).toString();
                        value = value.replace('\n', ' ');
                        bw.write(value);
                    }

                    if (j + 1 != clmCnt) {
                        bw.write(";");
                    }
                }

                bw.newLine();
            }

            bw.flush();

            success = true;

        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e1) {
                    throw new RuntimeException("Datei-Fehler (AuIKUtils)", e1);
                }
            }
        }

        return success;
    }

    /**
     * Erzeugt einen neuen MaskFormatter für ein FormattedTextField
     * @param s The formatting mask
     * @return The new MaskFormatter created using the mask or <code>null</code>
     *         if the mask was bad
     * @see <a
     *      href="http://java.sun.com/docs/books/tutorial/uiswing/components/formattedtextfield.html#maskformatter">Swing
     *      Tutorial (MaskFormatter)</a>
     */
    public static MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            log.debug("formatter is bad: " + exc.getMessage());
        }
        return formatter;
    }

    /**
     * Entfernt SQL/HQL Sonderzeichen (konkret sind das ;, ', ( und ) ) aus
     * einem String. Sollte auf alle Strings angewandt werden, die direkt und
     * NICHT als =? Parameter in einer SQL/HQL-Abfrage benutzt werden.
     * @param input Der String aus dem die Sonderzeichen entfernt werden sollen.
     * @return Ein String ohne die oben genannten Zeichen und ohne Whitespace am
     *         Anfang und am Ende.
     */
    public static String sanitizeQueryInput(String input) {
        return input.replaceAll(";", "").replaceAll("'", "")
            .replaceAll("\\(", "").replaceAll("\\)", "").trim();
    }

    /**
     * TODO: WHY are we doing this by hand??? Switch to DateFormat... Looks like
     * copied from somewhere anyway ("@param DateFormatter")... Liefert einen
     * String der Form "dd.mm.JJJJ" für ein gegebenes Datums-Objekt.
     * @param DateFormatter Das Datum
     * @return Einen String der Form "dd.mm.JJJJ" oder <code>null</code>, falls
     *         DateFormatter <code>null</code> ist
     */
    public static String getStringFromDate(Date date) {
        if (date != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);

            int day = cal.get(Calendar.DAY_OF_MONTH);
            String dayString = (day < 10) ? ("0" + day) : ("" + day);

            int month = cal.get(Calendar.MONTH) + 1;
            String monthString = (month < 10) ? ("0" + month) : ("" + month);

            return dayString + "." + monthString + "." + cal.get(Calendar.YEAR);
        } else {
            return null;
        }
    }

    /**
     * Liefert einen String der Form "dd.mm.JJJJ hh:mm" für ein gegebenes
     * Datums-Objekt.
     * @param DateFormatter Das Datum
     * @return Einen String der Form "dd.mm.JJJJ hh:mm" oder <code>null</code>,
     *         falls DateFormatter <code>null</code> ist
     */
    public static String getDayTimeStringFromDate(Date date) {
        if (date != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);

            int day = cal.get(Calendar.DAY_OF_MONTH);
            String dayString = (day < 10) ? ("0" + day) : ("" + day);

            int month = cal.get(Calendar.MONTH) + 1;
            String monthString = (month < 10) ? ("0" + month) : ("" + month);

            int hour = cal.get(Calendar.HOUR_OF_DAY);
            String hourString = (hour < 10) ? ("0" + hour) : ("" + hour);

            int minute = cal.get(Calendar.MINUTE);
            String minuteString = (minute < 10) ? ("0" + minute)
                : ("" + minute);

            return dayString + "." + monthString + "." + cal.get(Calendar.YEAR)
                + " " + hourString + ":" + minuteString;
        } else {
            return null;
        }
    }

    /**
     * Liefert den Wochentag eines gegebenen Datum-Objektes.
     * @param Das Datum
     * @return Den Wochentag als String
     */
    public static String getDayOfWeekFromDate(Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "Sonntag";
            case 2:
                return "Montag";
            case 3:
                return "Dienstag";
            case 4:
                return "Mittwoch";
            case 5:
                return "Donnerstag";
            case 6:
                return "Freitag";
            case 7:
                return "Samstag";
            default:
                return null;
        }

        /* Old version:
        int day = cal.get(Calendar.DAY_OF_WEEK);
        String dayString = null;
        switch (day) {
            case 1:
                dayString = "Sonntag";
                break;
            case 2:
                dayString = "Montag";
                break;
            case 3:
                dayString = "Dienstag";
                break;
            case 4:
                dayString = "Mittwoch";
                break;
            case 5:
                dayString = "Donnerstag";
                break;
            case 6:
                dayString = "Freitag";
                break;
            case 7:
                dayString = "Samstag";
                break;
            default:
                dayString = "Strange things happend here...";
        }
        return  dayString;
        */
    }

    /**
     * Erzeugt ein Icon aus einem Bild aus dem Icons-Package. Der Dateiname muss
     * ohne Pfad o.Ä. (also einfach "bild.png") angegeben werden.
     * @param filename Der Name der Bilddatei (ohne Pfad)
     * @return Ein Icon (oder <code>null</code>, falls kein Icon dieses Namens
     *         gefunden wurde)
     */
    public static Icon getIcon(String filename) {
        return getIcon(filename, null);
    }

    /**
     * Erzeugt ein Icon aus einem Bild aus dem Icons-Package. Der Dateiname muss
     * ohne Pfad o.Ä. (also einfach "bild.png") angegeben werden. Zusätzlich
     * muss die Größe (32 für 32x32 etc.) angegeben werden.
     * @param size Die Größe des Icons
     * @param filename Der Name der Bilddatei (ohne Pfad)
     * @return Ein Icon (oder <code>null</code>, falls kein Icon dieses Namens
     *         gefunden wurde)
     */
    public static Icon getIcon(int size, String filename) {
        return getIcon(size, filename, null);
    }

    /**
     * Erzeugt ein Icon aus einem Bild aus dem Icons-Package. Der Dateiname muss
     * ohne Pfad o.Ä. (also einfach "bild.png") angegeben werden.
     * @param filename Der Name der Bilddatei (ohne Pfad)
     * @param description Eine kurze textuelle Beschreibung
     * @return Ein Icon (oder <code>null</code>, falls kein Icon dieses Namens
     *         gefunden wurde)
     */
    public static Icon getIcon(String filename, String description) {
        return getIcon(-1, filename, description);
    }

    /**
     * Erzeugt ein Icon aus einem Bild aus dem Icons-Package. Der Dateiname muss
     * ohne Pfad o.Ä. (also einfach "bild.png") angegeben werden. Zusätzlich
     * muss die Größe (32 für 32x32 etc.) angegeben werden.
     * @param size Die Größe des Icons
     * @param filename Der Name der Bilddatei (ohne Pfad)
     * @param description Eine kurze textuelle Beschreibung
     * @return Ein Icon (oder <code>null</code>, falls kein Icon dieses Namens
     *         gefunden wurde)
     */
    public static Icon getIcon(int size, String filename, String description) {
        if (filename == null) {
            filename = "default.png";
        }

        if (description == null) {
            description = "Icon: " + filename;
        }

        String sSize;
        if (size == -1) {
            sSize = "";
        } else {
            sSize = size + "/";
        }
        String iconPath = "gui/images/icons/" + sSize + filename;
        URL iconURL = AUIKataster.class.getResource(iconPath);
        if (iconURL != null) {
            return new ImageIcon(iconURL, description);
        } else {
            log.debug("Konnte Icon " + iconPath + " nicht finden!");
            return null;
        }
    }

    /**
     * überprüft, ob wir unter XP und mit dem XP-Stil angezeigt werden.
     * @return <code>true</code>, wenn der XP-Stil aktiv ist, sonst
     *         <code>false</code>
     */
    public static boolean isUsingXpStyle() {
        /**TODO: Is this needed anymore? */
        return false;
    }
}
