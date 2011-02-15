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

import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.MD5Password;

/**
 * Eine abstrakte Basis-Klasse für alle Module.
 * Alle konkreten Module sollten von dieser Klasse erben. Ein {@link
 * AbstractModul} enth&auml;lt eine {@link HauptFrame} und eine {@link
 * ModulManager}Instanz. Diese werden mit entsprechenden Setter-Methoden
 * bekanntgemacht. Dies sollte direkt nach der Initialisierung des konkreten
 * Moduls geschehen um potentiellen Fehlern vorzubeugen.
 *
 * @author David Klotz
 */
public abstract class AbstractModul implements Modul {
    private String id;
    protected JPanel panel = null;
    protected Icon icon = null;
    protected HauptFrame frame;
    protected ModulManager manager;
    protected boolean shown = false;

    public void setFrame(HauptFrame f) {
        this.frame = f;
    }

    public void setManager(ModulManager m) {
        this.manager = m;
    }

    public Icon getIcon() {
        return getIcon(null);
    }

    /**
     * Erzeugt ein 32x32 Icon aus einem gegebenen Dateinamen.
     * @param iconPath Der Name der Icon-Datei
     * @return Ein neues Icon
     */
    protected Icon getIcon(String iconPath) {
        return getIcon(32, iconPath);
    }

    /**
     * Erzeugt ein Icon aus einem gegebenen Dateinamen.
     * @param size Die Icon-Größe
     * @param iconPath Der Name der Icon-Datei
     * @return Ein neues Icon
     */
    protected Icon getIcon(int size, String iconPath) {
        if (icon == null) {
            icon = AuikUtils.getIcon(size, iconPath, getName());
        }
        return icon;
    }

    /*
     * Berechnet eine zufällige und (hoffentlich) eindeutige
     * ID aus dem Modul-Namen, der Kategorie und der Zeit,
     * zu der es geladen wird.
     * Wenn zu einem Modul explizit aus einem anderen
     * Modul gewechselt werden soll, MUSS diese Methode
     * überschrieben werden!
     */
    public String getIdentifier() {
        if (id == null) {
            id = MD5Password.getEncodedPassword(
                    getName()
                    + getCategory()
                    + System.currentTimeMillis()
            );
            AUIKataster.debugOutput("ID von " + getName() + ": '" + id + "'", "AbstractModul.getIdentifier()");
        }
        return id;
    }

    /**
     * Sagt diesem Modul, dass es aktiv ist (angezeigt wird).
     * Wenn diese Methode überschrieben wird, unbedingt super.show() aufrufen.
     */
    public void show() {
        AUIKataster.debugOutput("shown", this.getIdentifier());
        shown = true;
    }

    /**
     * Sagt diesem Modul, dass es inaktiv ist (nicht angezeigt wird).
     * Wenn diese Methode überschrieben wird, unbedingt super.hide() aufrufen.
     */
    public void hide() {
        AUIKataster.debugOutput("hidden", this.getIdentifier());
        shown = false;
    }

    // Nicht überschreibbar
    public final boolean isShown() {
        return shown;
    }
}
