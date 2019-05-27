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
 * $Id: SimpleDialog.java,v 1.2.2.1 2010-11-23 10:25:59 u633d Exp $
 *
 * Erstellt am 06.06.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:41  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.5  2005/11/02 13:56:54  u633d
 * - Version vom 2.11.
 *
 * Revision 1.4  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.3  2005/09/07 10:48:23  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.2  2005/06/08 06:46:15  u633z
 * - Neuer Basisklasse für Editoren
 *
 * Revision 1.1  2005/06/06 15:30:13  u633z
 * - Neue Basis-Klassen für einfache Dialoge (werden mal die Grundlage für einfachere Editoren sein)
 *
 */
package de.bielefeld.umweltamt.aui.utils.dialogbase;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;

/**
 * Eine Grundlage für einen einfachen Dialog mit einem, zwei oder drei Buttons.
 * @author David Klotz
 */
public abstract class SimpleDialog extends JDialog {
    private static final long serialVersionUID = 3545077484554473729L;
    private class SimpleDialogListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            close();
        }
    }

    protected HauptFrame frame;

    protected boolean twoButtons = false;
    protected boolean threeButtons = false;

    protected JPanel buttonBar;
    protected JButton button1, button2, button3;

    public SimpleDialog(HauptFrame frame) {
        this(null, frame);
    }

    public SimpleDialog(String title, HauptFrame frame) {
        super(frame, title, true);
        this.frame = frame;

        button1 = new JButton(getFirstButtonAction());
        if (getSecondButtonAction() != null) {
            button2 = new JButton(getSecondButtonAction());
            twoButtons = true;
        }
        if (getThirdButtonAction() != null) {
            button3 = new JButton(getThirdButtonAction());
            threeButtons = true;
        }

        JPanel tmp = new JPanel(new BorderLayout());

        buttonBar = createButtonBar();
        buttonBar.setBorder(new EmptyBorder(new Insets(0, 0, 15, 30)));

        JComponent content = buildContentArea();
        tmp.add(content, BorderLayout.CENTER);

        tmp.add(buttonBar, BorderLayout.SOUTH);

        this.addWindowListener(new SimpleDialogListener());
        this.setContentPane(tmp);
        this.pack();
        this.setLocationRelativeTo(frame);
    }


    /**
     * Diese Methode wird vom Construktor aufgerufen. Sie erstellt das Panel,
     * das s&auml;mtliche Kn&ouml;pfe der Oberfl&auml;che enth&auml;lt.
     *
     * @return ein {@link javax.swing.JPanel} mit {@link javax.swing.JButton}s.
     */
    protected JPanel createButtonBar() {
        if (threeButtons) {
            return PanelBuilder.buildRightAlignedButtonToolbar(button1, button2, button3);
        }
        else if (twoButtons) {
            return PanelBuilder.buildRightAlignedButtonToolbar(button1, button2);
        }
        else {
           return PanelBuilder.buildRightAlignedButtonToolbar(button1);
        }
    }


    // super.close() aufrufen, falls diese Methode überschrieben wird!
    public void close() {
        dispose();
    }

    /**
     * Erzeugt den Bereich des Dialogs, in dem die eigentlichen Daten
     * angezeigt werden.
     * @return Eine JComponent (bswp. ein JPanel).
     */
    protected abstract JComponent buildContentArea();

    protected abstract Action getFirstButtonAction();
    protected Action getSecondButtonAction() {
        return null;
    }
    protected Action getThirdButtonAction() {
        return null;
    }
}
