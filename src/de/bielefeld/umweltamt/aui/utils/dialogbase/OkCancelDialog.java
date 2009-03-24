/*
 * Datei:
 * $Id: OkCancelDialog.java,v 1.2 2009-03-24 12:35:23 u633d Exp $
 * 
 * Erstellt am 06.06.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.2  2005/06/08 06:46:15  u633z
 * - Neuer Basisklasse für Editoren
 *
 * Revision 1.1  2005/06/06 15:30:13  u633z
 * - Neue Basis-Klassen für einfache Dialoge (werden mal die Grundlage f�r einfachere Editoren sein)
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
public abstract class OkCancelDialog extends SimpleDialog {
	public OkCancelDialog(HauptFrame frame) {
		this(null, frame);
	}
	
	public OkCancelDialog(String title, HauptFrame frame) {
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
	
	protected String getOkButtonText() {
		return "OK";
	}

	protected abstract void doOk();
	
	protected void doCancel() {
		close();
	}
}
