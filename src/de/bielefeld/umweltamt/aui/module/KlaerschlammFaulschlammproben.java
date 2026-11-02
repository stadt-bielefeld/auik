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
 * $Id: KlaerschlammFaulschlammproben.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 *
 * Erstellt am 17.02.2005 von David Klotz (u633z)
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:32  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.6  2005/06/09 15:27:03  u633z
 * - (CVS-)Header hinzugefügt
 *
 */
package de.bielefeld.umweltamt.aui.module;

import javax.swing.Icon;
import javax.swing.JPanel;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.module.common.SchlammPanel;

/**
 * Das Modul um Faulschlamm-Probenahmen zu bearbeiten.
 * @author David Klotz
 */
public class KlaerschlammFaulschlammproben extends AbstractModul {

    @Override
    public Icon getIcon() {
        return super.getIcon("recycled_braun32.png");
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getName()
     */
    @Override
    public String getName() {
        return "Faulschlamm Probenahmen";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "m_schlaemme_faul";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
     */
    @Override
    public String getCategory() {
        return "Klärschlamm";
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
     */
    @Override
    public JPanel getPanel() {
        if (panel == null) {
            panel = new SchlammPanel(
                Probeart.findById(
                    DatabaseConstants.ATL_PROBEART_ID_FAULSCHLAMM),
                frame);
        }
        return panel;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.Modul#show()
     */
    @Override
    public void show() {
        super.show();
        if (panel != null && panel instanceof SchlammPanel) {
            ((SchlammPanel)panel).showContent();
        }
    }
}
