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
