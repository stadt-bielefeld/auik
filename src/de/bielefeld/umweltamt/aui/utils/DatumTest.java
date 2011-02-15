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

package de.bielefeld.umweltamt.aui.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatumTest {

    /**
     * @param args
     */
    public static void main(String args[])
    throws Exception
{
    String date = "13.07.2010 10:00:00";
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    Date convertedDate_ende = df.parse(date);

    System.out.println("Ende     : " + df.format(convertedDate_ende));
    Date aktDat = new Date();
    System.out.println("Anfang   : " + df.format(aktDat));
    long aktDatlong = aktDat.getTime();
    long aukEnde = convertedDate_ende.getTime();

    long restTime = aukEnde - aktDatlong;

    // String bldfa = dateFormat.format(new Date(restTime));

    // restTime = restTime * 1000;
    Date dateTime = new Date(restTime);
    System.out.println("Differenz: " + df.format(dateTime));
    Calendar cal = Calendar.getInstance();
    cal.setTime(dateTime);

    int day = cal.get(Calendar.DAY_OF_WEEK);
    int hour = cal.get(Calendar.HOUR);
    int sec = cal.get(Calendar.SECOND);
    int min = cal.get(Calendar.MINUTE);

    System.out.println(day+"___"+hour+":"+min+":"+sec);
}


}
