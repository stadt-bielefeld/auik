/*
 * Copyright (c) 2010 by Intevation GmbH
 *
 * This program is free software under the TODO LICENSE
 * Read the LICENSE file coming with the software for details
 * or visit http://www.gnu.org/licenses/ if it does not exist.
 */
package de.bielefeld.umweltamt.aui.utils;

import java.util.Date;


/**
 * Diese Klasse stellt einige Funktionen bereit, die die Arbeit mit Zeit- und
 * Datumswerten erleichtern.
 *
 * @author <a href="mailto:ingo.weinzierl@intevation.de">Ingo Weinzierl</a>
 */
public class DateUtils {

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
}
// vim:set ts=4 sw=4 si et sta sts=4 fenc=utf8:
