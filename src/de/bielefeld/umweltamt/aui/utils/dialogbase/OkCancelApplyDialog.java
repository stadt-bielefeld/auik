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
 * $Id: OkCancelApplyDialog.java,v 1.1.2.1 2010-11-23 10:25:59 u633d Exp $
 *
 * Erstellt am 06.06.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2009/04/28 06:59:43  u633d
 * Anh 50 und Standort Tabelle bearbeitet
 *
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.2  2005/06/08 06:46:15  u633z
 * - Neuer Basisklasse für Editoren
 *
 * Revision 1.1  2005/06/06 15:30:13  u633z
 * - Neue Basis-Klassen für einfache Dialoge (werden mal die Grundlage für einfachere Editoren sein)
 *
 */
package de.bielefeld.umweltamt.aui.utils.dialogbase;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.bielefeld.umweltamt.aui.HauptFrame;

/**
 * Eine Grundlage für einen einfachen Dialog mit einem
 * "Ok"- und einem "Abbrechen"-Button.
 * @author David Klotz
 */
public abstract class OkCancelApplyDialog extends SimpleDialog {
    public OkCancelApplyDialog(HauptFrame frame) {
        this(null, frame);
    }

    public OkCancelApplyDialog(String title, HauptFrame frame) {
        super(title, frame);
    }

    public Action getFirstButtonAction() {
        return new AbstractAction(getOkButtonText()) {
            public void actionPerformed(ActionEvent e) {
                doOk();
            }
        };
    }

    public Action getSecondButtonAction() {
        return new AbstractAction("Abbrechen") {
            public void actionPerformed(ActionEvent e) {
                doCancel();
            }
        };
    }

    public Action getThirdButtonAction() {
        return new AbstractAction("Parameter auswählen") {
            public void actionPerformed(ActionEvent e) {
                doApply();
            }
        };
    }

    protected String getOkButtonText() {
        return "OK";
    }

    protected abstract void doOk();

    protected void doCancel() {
        close();
    }

    protected abstract void doApply();
}
