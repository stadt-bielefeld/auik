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
 * $Id: AbstractBaseEditor.java,v 1.2 2009-03-24 12:35:23 u633d Exp $
 *
 * Erstellt am 07.06.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.7  2005/11/02 13:52:40  u633d
 * - Version vom 2.11.
 *
 * Revision 1.6  2005/10/13 13:00:25  u633d
 * Version vom 13.10.
 *
 * Revision 1.5  2005/09/28 11:11:12  u633d
 * - Version vom 28.9.
 *
 * Revision 1.4  2005/09/07 10:48:22  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.3  2005/06/09 09:16:07  u633z
 * - setEditedObject hinzugefügt
 *
 * Revision 1.2  2005/06/08 08:36:15  u633z
 * - Kommentare verbessert
 *
 * Revision 1.1  2005/06/08 06:46:15  u633z
 * - Neuer Basisklasse für Editoren
 *
 */
package de.bielefeld.umweltamt.aui.module.common.editors;



import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ModulManager;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog;

/**
 * Die Grundlage für verschiedene Editoren.
 * @author David Klotz
 */
public abstract class AbstractBaseEditor extends OkCancelDialog {
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    protected Object editedObject;
    protected String objektName;
    protected ModulManager manager;
    protected boolean saved;

    /**
     * Erzeugt einen neuen Editor zum Bearbeiten von Objekten.
     * @param objektName Welche Art von Objekt wird bearbeitet (bspw. "Betreiber")
     * @param editedObject Das zu bearbeitende Objekt.
     * @param frame Das Hauptfenster.
     */
    public AbstractBaseEditor(String objektName, Object editedObject, HauptFrame frame) {
        super(objektName + " bearbeiten", frame);
        this.objektName = objektName;
        this.editedObject = editedObject;
        this.manager = frame.getManager();
        saved = false;

        fillForm();

        // TODO: Eine Property mit "x,y" statt zwei benutzen!
        if (manager.getSettingsManager().getBoolSetting("auik.prefs.save_size")) {
            int x = manager.getSettingsManager().getIntSetting("auik.prefs.size_x.dialog_" + getEditedClassName());
            int y = manager.getSettingsManager().getIntSetting("auik.prefs.size_y.dialog_" + getEditedClassName());

            // Sicherheit einerseits für bisher ungespeicherte Dialoge,
            // andererseits damit man das Fenster nie zu klein macht.
            if (x > 10 && y > 10) {
                log.debug("Setze Größe auf " + x + "x" + y);
                // Ändere die Größe dieses Dialogs.
                this.setSize(x, y);
            }
        }
    }

    /**
     * Liefert eine Kurzform des Klassennamens der bearbeiteten Klasse.
     * Ab Java 1.5 können wir das auch einfacher mit Class.getSimpleName() haben.
     * @return Der Klassenname der bearbeiteten Klasse, ohne Package.
     */
    protected String getEditedClassName() {
        // Den langen Klassennamen holen:
        String longName = editedObject.getClass().getName();

        // Das Package aus diesem entfernen:
        String className = longName.substring(longName.lastIndexOf(".")+1);

        //AUIKataster.debugOutput("editedObject.class: " + className), "ABEditor: '" + objektName + "'");
        return className;
    }

    /**
     * Speichert die Größe dieses Dialogs in den Properties.
     */
    private void saveDialogSize() {
        if (manager.getSettingsManager().getBoolSetting("auik.prefs.save_size")) {
            manager.getSettingsManager().setSetting("auik.prefs.size_x.dialog_" + getEditedClassName(), this.getWidth(), true);
            manager.getSettingsManager().setSetting("auik.prefs.size_y.dialog_" + getEditedClassName(), this.getHeight(), true);
        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#getOkButtonText()
     */
    protected String getOkButtonText() {
        return "Speichern";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#doOk()
     */
    protected void doOk() {
        if (canSave()) {
            if (doSave()) {
                saved = true;
                frame.changeStatus(objektName + " erfolgreich gespeichert.", HauptFrame.SUCCESS_COLOR);
            } else {
                saved = false;
                frame.changeStatus("Konnte "+objektName + " nicht speichern!", HauptFrame.ERROR_COLOR);
            }

            saveDialogSize();

            // Hier natürlich nicht die close() dieser Klasse aufrufen, da dort wieder saved=false gesetzt wird etc.
            super.close();
        }
    }

    /**
     * Wird aufgerufen, wenn der Benutzen auf "Abbrechen" geklickt hat.
     * Oder wenn das Fenster geschlossen wurde.
     */
    public void close() {
        saved = false;
        frame.changeStatus("Bearbeiten abgebrochen.");
        saveDialogSize();
        super.close();
    }

    /**
     * Liefert das bearbeitete Objekt.
     * <b>ACHTUNG:</b> Erst nach dem super()-Konstruktor aufrufen!
     */
    public Object getEditedObject() {
        return editedObject;
    }

    /**
     * Setzt ein neues zu bearbeitendes Objekt.
     * @param newObject Das neue Objekt.
     */
    public void setEditedObject(Object newObject) {
        this.editedObject = newObject;
    }

    /**
     * Überprüft, ob das bearbeitete Objekt gespeichert wurde.
     * @return <code>true</code>, wenn das Objekt erfolgreich gespeichert wurde, sonst <code>false</code>.
     */
    public boolean wasSaved() {
        return saved;
    }

    // Abstrakter Teil:

    /**
     * Füllt das Formular mit Daten aus dem bearbeiteten Objekt.
     */
    protected abstract void fillForm();

    /**
     * Überprüft, ob gespeichert werden kann/darf.
     * @return <code>true</code>, wenn alle nötigen Eingaben da sind, also gespeichert werden kann. Sonst <code>false</code>.
     */
    protected abstract boolean canSave();

    /**
     * Speichert das bearbeitete Objekt.
     * @return <code>true</code>, wenn das Speichern geklappt hat, sonst <code>false</code>.
     */
    protected abstract boolean doSave();
}
