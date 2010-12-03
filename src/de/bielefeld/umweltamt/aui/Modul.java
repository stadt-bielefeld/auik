/*
 * Created on 12.01.2005
 */
package de.bielefeld.umweltamt.aui;

import javax.swing.Icon;
import javax.swing.JPanel;

/**
 * Ein Interface, dass ein Modul zum Bearbeiten von
 * Daten spezifiziert.
 * @author David Klotz
 */
public interface Modul {
    /**
     * Liefert den anzuzeigenden Namen dieses Moduls.
     * @return Der Name dieses Moduls
     */
    public String getName();

    /**
     * Liefert einen eindeutigen Bezeichner für dieses Modul.
     * <b>Er muss eindeutig sein!</b>
     * @return Der Bezeichner dieses Moduls
     */
    public String getIdentifier();

    /**
     * Liefert die Kategorie, zu der dieses Modul gehört.
     * @return Der Name der Kategorie
     */
    public String getCategory();


    /**
     * Liefert ein Icon für dieses Modul.
     * @return Das Icon dieses Moduls
     */
    public Icon getIcon();

    /**
     * Liefert das Panel mit dem Inhalt dieses Moduls.
     * @return Ein JPanel gefüllt mit dem Inhalt dieses Moduls
     */
    public JPanel getPanel();


    /**
     * Assoziiert dieses Modul mit dem aktuellen ModulManager.
     * @param m Der ModulManager
     */
    public void setManager(ModulManager m);

    /**
     * Assoziiert dieses Modul mit dem Hauptfenster.
     * @param f Das Haupt-Fenster
     */
    public void setFrame(HauptFrame f);

    /**
     * Sagt diesem Modul, dass es aktiv ist (angezeigt wird).
     */
    public void show();

    /**
     * Sagt diesem Modul, dass es inaktiv ist (nicht angezeigt wird).
     */
    public void hide();

    /**
     * Fragt ab, ob dieses Modul angezeigt wird.
     * @return <b>true</b>, wenn dieses Modul angezeigt wird, sonst <b>false</b>
     */
    public boolean isShown();
}
