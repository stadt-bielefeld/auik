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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class DateFormatter {

    public static void main(String[] args){
        SimpleDateFormat formatter = new SimpleDateFormat (" HH:mm:ss ");
        Date currentTime = new Date();
        System.out.println("Zeit und Datum : " + formatter.format(currentTime));
        System.out.println( "Default-Zeitzone = " + TimeZone.getDefault().getID() );       // z.B. 'Europe/Berlin'
        System.out.println( "user.timezone = " + System.getProperty( "user.timezone" ) );
        Timestamp t = Timestamp.valueOf("1999-05-31 18:30:00");
        System.out.println("OUT: " + t.toString().subSequence(0, 10));
        Timestamp n = Timestamp.valueOf(t.toString());
        System.out.println("OUT: " + n.toString().subSequence(0, 16));
    }

}
