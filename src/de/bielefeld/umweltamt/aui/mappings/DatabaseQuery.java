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

import de.bielefeld.umweltamt.aui.mappings.tipi.AuikWzCode;
import de.bielefeld.umweltamt.aui.utils.DatabaseAccess;

/**
 * This is a service class for all custom queries
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class DatabaseQuery {

    /* ********************************************************************** */
    /* Queries for package BASIS                                              */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* Queries for package ATL                                                */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* Queries for package INDEINL                                            */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* Queries for package VAWS                                               */
    /* ********************************************************************** */

    /* ********************************************************************** */
    /* Queries for package TIPI                                               */
    /* ********************************************************************** */

    /**
     * Get a list of the WZ-Codes which are marked for the Kurzauswahl
     * @return <code>AuikWzCode[]</code>
     */
    public static AuikWzCode[] getAuikWzCodesInKurzAuswahl() {
        return new DatabaseAccess().createQuery(
            "FROM AuikWzCode WHERE inKurzAuswahl = TRUE ORDER BY bezeichnung")
            .array(new AuikWzCode[0]);
    }
}
