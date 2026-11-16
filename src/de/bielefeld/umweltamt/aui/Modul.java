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
