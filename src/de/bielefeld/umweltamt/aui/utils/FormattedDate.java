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

package de.bielefeld.umweltamt.aui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An extension to Date which makes sure we use a valid format for our date.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class FormattedDate extends Date {
    private static final long serialVersionUID = 5494074447025794462L;

    /* Valid date formats */
    /** The date as in "dd.MM.yyyy" */
    public static final String DATE = "dd.MM.yyyy";
    /** The date and time as in "dd.MM.yyyy HH:mm" */
    public static final String DATETIME = "dd.MM.yyyy HH:mm";
    /** The time as in "HH:mm" */
    public static final String TIME = "HH:mm";
    /** The date as in "ddMMyyyy" */
    public static final String KASSE = "ddMMyyyy";
    /** The default date which is like DATE */
    public static final String DEFAULT = DATE;

    /** Enumeration with the valid date formats */
    public static enum Format {
        DEFAULT, DATE, DATETIME, TIME, KASSE
    }

    /** The format of this date. */
    private String format = FormattedDate.DEFAULT;

    /** A utility class which does the formating. */
    private SimpleDateFormat sdf = null;

    /** Simple constructor which uses the default format */
    public FormattedDate() {
        super();
        this.setFormat(Format.DEFAULT);
    }

    /**
     * Constructor which uses the given format
     * @param format The format for the date.
     */
    public FormattedDate(Format format) {
        super();
        this.setFormat(format);
    }

    /**
     * Set the format which should be used
     * @param format The format to use
     */
    public void setFormat(Format format) {
        switch (format) {
            case DEFAULT:  this.format = FormattedDate.DEFAULT;  break;
            case DATE:     this.format = FormattedDate.DATE;     break;
            case DATETIME: this.format = FormattedDate.DATETIME; break;
            case TIME:     this.format = FormattedDate.TIME;     break;
            case KASSE:    this.format = FormattedDate.KASSE;    break;
            default:       this.format = FormattedDate.DEFAULT;
        }
        sdf = new SimpleDateFormat(this.format);
    }

    /**
     * Set the date with a given String in the specified format
     * @param date The new date as String
     * @return <code>true</code>, if we could parse the String,
     *         <code>false</code> otherwise
     */
    public boolean setDate(String date) {
        try {
            super.setTime(sdf.parse(date).getTime());
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * A toString method which uses the format to print the date
     * @return String Nicely formatted date
     */
    @Override
    public String toString() {
        return sdf.format(this);
    }

    // TODO: Move this into a more global scope
    public String toStrikedString() {
        return "<html><strike>" + sdf.format(this) + "</strike></html>";
    }
}
