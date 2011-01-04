/*
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.utils;

import java.util.Calendar;
import java.util.Date;


/**
 * Diese Klasse stellt einige Funktionen bereit, die die Arbeit mit Zeit- und
 * Datumswerten erleichtern.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class DateUtils {

    /**
     * Die Anzahl der Tage, nachdem der Geb&uuml;hrenbescheid bezahlt werden
     * muss: 31
     */
    public static final int BILLING_DEADLINE = 31;

    /**
     * Diese Funktion liefert die Dauer zwischen <i>start</i> und <i>end</i> als
     * String in Form von HH:MM zur&uuml;ck. Wenn einer der Parameter
     * ung&uuml;tig oder <i>null</i> ist, wird "--:--" geliefert.
     *
     * @param start Die Startzeit
     * @param end Die Endzeit
     *
     * @return die Stunden und Minuten zwischen <i>start</i> und <i>end</i>.
     */
    public static String getDuration(Date start, Date end) {
        if (start == null || end == null) {
            return "--:--";
        }

        long duration = (end.getTime() - start.getTime()) / 1000;

        int seconds = getSeconds(duration);
        int minutes = getMinutes(duration);
        int hours   = getHours(duration);

        String h = hours <= 9 ? "0" + hours : "" + hours;
        String m = minutes <= 9 ? "0" + minutes : "" + minutes;

        return h + ":" + m;
    }


    public static double getDurationHours(Date start, Date end) {
        if (start == null || end == null) {
            return 0;
        }

        long duration = (end.getTime() - start.getTime()) / 1000;

        int hours   = getHours(duration);
        int minutes = getMinutes(duration);

        return hours + (minutes / 60.0);
    }


    protected static int getSeconds(long seconds) {
        return (int) seconds % 60;
    }

    protected static int getMinutes(long seconds) {
        seconds /= 60;
        return (int) seconds % 60;
    }

    protected static int getHours(long seconds) {
        seconds /= (60*60);
        return (int) seconds % 60;
    }


    /**
     * Diese Methode berechnet ein basierend auf einem Datum ein neues Datum.
     * Dabei werden auf <i>notification</i> 31 Werktage addiert. Sollte dieses
     * neue Datum an einem Wochenende liegen, wird der kommende Montag
     * zur&uuml;ckgeliefert.
     *
     * @param notification Datum, an dem ein Geb&uuml;hrenbescheid verschickt
     * wird.
     *
     * @return das Datum, an dem die Rechnungsfrist abl&auml;uft.
     */
    public static Date getDateOfBill(Date notification)
    throws NullPointerException
    {
        if (notification == null) {
            throw new NullPointerException("Empty Date object not permitted.");
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(notification);

        int index = 0;
        do {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            int day = cal.get(Calendar.DAY_OF_WEEK);

            if (day != Calendar.SATURDAY && day != Calendar.SUNDAY) {
                index++;
            }

        } while (index != 31);

        return cal.getTime();
    }
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
