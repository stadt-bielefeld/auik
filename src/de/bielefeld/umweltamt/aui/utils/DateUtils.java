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
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Diese Klasse stellt einige Funktionen bereit, die die Arbeit mit Zeit- und
 * Datumswerten erleichtern.
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class DateUtils {

    /**
     * Ein String, der das Format eines Zeitstempels beschreibt. Das Format
     * beinhaltet das Datum und die Zeit: dd.MM.yyyy hh:mm
     */
    public static final String FORMAT_DATETIME = "dd.MM.yyyy hh:mm";

    /**
     * Ein String, der das Format eines Zeitstempels beschreibt. Das Format
     * beinhaltet lediglich das Datum: dd.MM.yyyy
     */
    public static final String FORMAT_DATE = "dd.MM.yyyy";

    /**
     * Ein String, der das Format eines Zeitstempels beschreibt. Das Format
     * beinhaltet lediglich das Datum: hh:mm
     */
    public static final String FORMAT_TIME = "HH:mm";

    /**
     * Ein String, der das Format eines Zeitstempels beschreibt. Das Format
     * beinhaltet das Datum des Zeitstempels für die Datei kasse.txt: ddMMyyyy
     */
    public static final String FORMAT_KASSE = "ddMMyyyy";

    /** The default format to use */
    public static final String FORMAT_DEFAULT = FORMAT_DATE;

    /**
     * Die Anzahl der Tage, nachdem der Geb&uuml;hrenbescheid bezahlt werden
     * muss: 31
     */
    public static final int BILLING_DEADLINE = 31;

    /**
     * Get the current date as string in the default format
     * @return String now
     */
    public static String getCurrentDateString() {
        return new SimpleDateFormat(DateUtils.FORMAT_DEFAULT)
            .format(new GregorianCalendar().getTime());
    }

    /**
     * Diese Funktion formatiert ein Datum <i>date</i> mittels eines String, der
     * das Format beschreibt.
     * @param date Das zu formatierende Datum
     * @param format Ein String, der das Format beschreibt.
     * @return ein formatiertes Format oder null, falls das Format ung&uuml;ltig
     *         ist.
     */
    public static String format(Date date, String format) {
        if (format == null || date == null || format.equals("")) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date parse(String date, String format) throws ParseException {
        if (format == null || date == null || format.equals("")) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }

    /**
     * Try to parse a given string with a given format to a <code>Date</code>.
     * If there is a <code>ParseException</code>, it is catched and the returned
     * <code>Date</code> is <code>null</code>.
     * @param date The String with the to parse date
     * @param format The format for the parsing
     * @return <code>Date</code>, if the date was successfully parsed,
     *         <code>null</code> otherwise.
     */
    public static Date tryParse(String date, String format) {
        try {
            return DateUtils.parse(date, format);
        } catch (ParseException pe) {
            return null;
        }
    }

    /**
     * Diese Funktion liefert die Dauer zwischen <i>start</i> und <i>end</i> als
     * String in Form von HH:MM zur&uuml;ck. Wenn einer der Parameter
     * ung&uuml;tig oder <i>null</i> ist, wird "--:--" geliefert.
     * @param start Die Startzeit
     * @param end Die Endzeit
     * @return die Stunden und Minuten zwischen <i>start</i> und <i>end</i>.
     */
    public static String getDuration(Date start, Date end) {
        if (start == null || end == null) {
            return "--:--";
        }

        long duration = (end.getTime() - start.getTime()) / 1000;

//        int seconds = getSeconds(duration);
        int minutes = getMinutes(duration);
        int hours = getHours(duration);

        String h = hours <= 9 ? "0" + hours : "" + hours;
        String m = minutes <= 9 ? "0" + minutes : "" + minutes;

        return h + ":" + m;
    }

    /**
     * Diese Funktion liefert die Addition zweier Zeiten <i>one</i> und
     * <i>two</i> als String in Form von HH:MM zur&uuml;ck. Wenn einer der
     * Parameter ung&uuml;tig oder <i>null</i> ist, wird "--:--" geliefert.
     * @param one Die erste Zeit
     * @param two Die zweite Zeit
     * @return die Stunden und Minuten von <i>one</i> plus <i>two</i>.
     */
    public static String getAddition(Date one, Date two) {
        if (one == null || two == null) {
            return "--:--";
        }

        long duration = ((one.getTime() + 3600000) + (two.getTime() + 3600000)) / 1000;

//        int seconds = getSeconds(duration);
        int minutes = getMinutes(duration);
        int hours = getHours(duration);

        String h = hours <= 9 ? "0" + hours : "" + hours;
        String m = minutes <= 9 ? "0" + minutes : "" + minutes;

        return h + ":" + m;
    }

    public static double getDurationHours(Date start, Date end) {
        if (start == null || end == null) {
            return 0;
        }

        long duration = (end.getTime() - start.getTime()) / 1000;

        int hours = getHours(duration);
        int minutes = 0;
        if (getMinutes(duration) % 15 == 0) {
            minutes = getMinutes(duration) / 15;
        } else {
            minutes = getMinutes(duration) / 15 + 1;
        }

        return hours + (minutes / 4.0);
    }

    protected static int getSeconds(long seconds) {
        return (int) seconds % 60;
    }

    protected static int getMinutes(long seconds) {
        seconds /= 60;
        return (int) seconds % 60;
    }

    protected static int getHours(long seconds) {
        seconds /= (60 * 60);
        return (int) seconds % 60;
    }

    /**
     * Diese Methode berechnet ein basierend auf einem Datum ein neues Datum.
     * Dabei werden auf <i>notification</i> 31 Werktage addiert. Sollte dieses
     * neue Datum an einem Wochenende liegen, wird der kommende Montag
     * zur&uuml;ckgeliefert.
     * @param notification Datum, an dem ein Geb&uuml;hrenbescheid verschickt
     *            wird.
     * @return das Datum, an dem die Rechnungsfrist abl&auml;uft.
     */
    public static Date getDateOfBill(Date notification)
        throws NullPointerException {
        if (notification == null) {
            throw new NullPointerException("Empty Date object not permitted.");
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(notification);

        cal.add(Calendar.DAY_OF_MONTH, 30);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        if (day == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 2);
        }

        else if (day == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return cal.getTime();
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
