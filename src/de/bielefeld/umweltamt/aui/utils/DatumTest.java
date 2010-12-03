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
