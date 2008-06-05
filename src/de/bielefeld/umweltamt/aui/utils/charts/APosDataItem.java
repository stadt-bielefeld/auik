/*
 * Datei:
 * $Id: APosDataItem.java,v 1.1 2008-06-05 11:38:38 u633d Exp $
 * 
 * Erstellt am 31.05.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/05/31 15:52:18  u633z
 * - Charts und ChartDataSets nach utils.charts verschoben
 * - Neue Klasse APosDataItem um eine Analyseposition als Punkt einer
 *   TimeSeries Datenreihe zu repräsentieren
 * - Bessere Tooltips im Auswertungs-Diagramm
 *
 */
package de.bielefeld.umweltamt.aui.utils.charts;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeriesDataItem;

import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;

/**
 * Eine Klasse um eine Analysposition als Datenpunkt in einer TimeSeries-Datenreihe 
 * darstellen zu können.
 * @author David Klotz
 */
public class APosDataItem extends TimeSeriesDataItem {
	private AtlAnalyseposition pos;
	
	public APosDataItem(AtlAnalyseposition pos) {
		this(pos, createMinuteFromDate(pos.getAtlProbenahmen().getDatumDerEntnahme(), pos.getAtlProbenahmen().getZeitDerEntnahmen()));
	}
	
	public APosDataItem(AtlAnalyseposition pos, Minute minute) {
		super(minute, pos.getWert());
		this.pos = pos;
	}
	
	public Minute getMinute() {
		return (Minute) getPeriod();
	}
	
	public AtlAnalyseposition getAnalysePosition() {
		return pos;
	}
	
	public static Minute createMinuteFromDate(Date date, String zeit) {
		int hour = 0;
		int minute = 0;
		
		Calendar cal = GregorianCalendar.getInstance();
		
		cal.setTime(date);
		if (zeit != null) {
			//Zeit ist im Format: "15:30:00"
			hour = Integer.parseInt(zeit.substring(0,2));
			minute = Integer.parseInt(zeit.substring(3,5));
		}
		
		return new Minute(minute, hour, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
	}
}
