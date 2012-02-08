/**
 * Copyright 2005-2042, Stadt Bielefeld
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

package de.bielefeld.umweltamt.aui.mappings;

/**
 * Abstract class for all virutally deletable database table classes.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public abstract class AbstractVirtuallyDeletableDatabaseTable {

    /**
     * The value of the simple _active property.<br>
     * If a data record is _active, it can come up in the views, but will be
     * marked as being not active.
     */
    private java.lang.Boolean _active = true;

    /**
     * The value of the simple _deleted property.<br>
     * If a data record is _deleted, it will not show up anywhere but in the
     * database itself.
     */
    private java.lang.Boolean _deleted = false;

    /**
     * Return the value of the _ACTIVE column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean is_active() {
        return _active;
    }

    /**
     * Set the value of the _ACTIVE column.
     * @param _active the _active to set
     */
    public void set_active(java.lang.Boolean _active) {
        this._active = _active;
    }

    /**
     * Return the value of the _DELETED column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean is_deleted() {
        return _deleted;
    }

    /**
     * Set the value of the _DELETED column.
     * @param _deleted the _deleted to set
     */
    public void set_deleted(java.lang.Boolean _deleted) {
        this._deleted = _deleted;
    }
}
