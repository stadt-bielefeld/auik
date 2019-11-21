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

package de.bielefeld.umweltamt.aui.module.objektpanels;

import javax.swing.JPanel;

import de.bielefeld.umweltamt.aui.mappings.oberflgw.Sonderbauwerk;

/**
 * Abstract class used as base for every Sonderbauwerk type panel
 */
public abstract class AbstractSonderbauwerkTypPanel extends JPanel{

    private static final long serialVersionUID = 0L;

    /**
     * Panel name
     */
    protected String name;

    /**
     * Save data to model
     */
    public abstract void save();

    /**
     * Get the panel name
     * @return Panel name as String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the record for this panel
     * @param data Sonderbauwerk record
     */
    public abstract void setData(Sonderbauwerk data);
}