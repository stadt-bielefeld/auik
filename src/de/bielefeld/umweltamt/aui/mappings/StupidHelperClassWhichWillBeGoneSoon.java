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
public abstract class StupidHelperClassWhichWillBeGoneSoon {

    /**
     * The value of the simple _enabled property.<br>
     * If a data record is _enabled, it can come up in the views, but will be
     * marked as being not active.
     */
    private java.lang.Boolean enabled = true;

    /**
     * The value of the simple _deleted property.<br>
     * If a data record is _deleted, it will not show up anywhere but in the
     * database itself.
     */
    private java.lang.Boolean deleted = false;

    /**
     * Return the value of the _ENABLED column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the value of the _ENABLED column.
     * @param _enabled the _enabled to set
     */
    public void setEnabled(java.lang.Boolean _enabled) {
        this.enabled = _enabled;
    }

    /**
     * Return the value of the _DELETED column.
     * @return java.lang.Boolean
     */
    public java.lang.Boolean isDeleted() {
        return deleted;
    }

    /**
     * Set the value of the _DELETED column.
     * @param _deleted the _deleted to set
     */
    public void setDeleted(java.lang.Boolean _deleted) {
        this.deleted = _deleted;
    }
}
