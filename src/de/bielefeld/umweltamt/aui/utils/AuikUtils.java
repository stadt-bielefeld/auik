/*
 * Created on 08.02.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;

/**
 * Diverse häufiger benötigte Utility-Methoden, die keiner anderen
 * Klasse zugeordnet werden können.
 * @author David Klotz
 */
public class AuikUtils {
	/** Das Default-Datumsformat für JDateChooser */
	public static final String DATUMSFORMAT = "dd.MM.yy";
	/** Die Datumsformate für TextFieldDateChooser */
	public static final String[] DATUMSFORMATE = new String[]{DATUMSFORMAT, "dd.MM.yyyy"};
	
    // Für die Datums-Methoden
	private static Calendar cal = null;
	
	/**
	 * Startet den Standard-Betrachter / -Editor, der vom Betriebssystem
	 * mit der Datei <code>f</code> verknüpft ist. 
	 * @param f Die zu öffnende Datei.
	 */
	public static void spawnFileProg(File f) {
//		final File ff = f;
		
//		Thread spawnThread = new Thread(new Runnable() {
//			public void run() {
				//System.out.println("Spawning default Editor for: '" +f+ "'");
				if (f.exists() && f.isFile() && f.canRead()) {
					//String comspec = System.getenv("COMSPEC");
					String comspec = "cmd";
					if (comspec == null) {
						comspec = "cmd";
					}

					try {
//						Thread.sleep(500);
						Runtime.getRuntime().exec(comspec + " /c start \"Bitte warten...\" " + f.getName(), null, f.getParentFile());
					} catch (IOException e) {
						throw new RuntimeException("Konnte den Betrachter für " + f + " nicht starten!", e);
					}
				} else {
					AUIKataster.debugOutput("Fehler beim spawnFileProg für " + f, "AuikUtils");
				}
//			}
//		});
		
//		spawnThread.start();
	}
	
	/**
     * Gets the extension of a file. If the file doesn't have one, returns null;
     * @param f The file.
     */
    public static String getExtension(File f) {
        String ext = null;
        if (f != null) {
        	String s = f.getName();
        	int i = s.lastIndexOf('.');
        	
        	if (i > 0 &&  i < s.length() - 1) {
        		ext = s.substring(i+1).toLowerCase();
        	}
        }
        return ext;
    }
    
    /**
     * Liefert einen FileFilter für einen FileChooser, der nur Dateien
     * mit einer bestimmten Erweiterung und Verzeichnisse anzeigt.
     * @param extension Die Erweiterung (bspw. "txt").
     * @return Einen FileFilter, der nur Dateien mit einer bestimmten Erweiterung anzeigt.
     */
    public static FileFilter getExtensionFilter(final String extension) {
    	FileFilter tmp = new FileFilter() {
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				
				String fExtension = AuikUtils.getExtension(f);
				if (fExtension != null) {
					if (fExtension.equals(extension)) {
						return true;
					} else {
						return false;
					}
				}
				
				return false;
			}

			public String getDescription() {
				return getFileDescription(extension);
			}
		};
		
		return tmp;
    }
    
    /**
     * Liefert einen FileFilter für einen FileChooser, der nur Dateien
     * mit bestimmten Erweiterungen und Verzeichnisse anzeigt.
     * @param extensions Die Erweiterungen (bspw. {"txt", "csv"}).
     * @return Einen FileFilter, der nur Dateien mit bestimmten Erweiterungen anzeigt.
     */
    public static FileFilter getExtensionsFilter(final String[] extensions) {
    	FileFilter tmp = new FileFilter() {
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				
				String fExtension = AuikUtils.getExtension(f);
				if (fExtension != null) {
					for (int i = 0; i < extensions.length; i++) {
						if (fExtension.equals(extensions[i])) {
							return true;
						}
					}
				}
				
				return false;
			}

			public String getDescription() {
				String desc = "Alle unterstützten Dateitypen";
				
				if (extensions.length < 6) {
					desc += " (";
					
					for (int i = 0; i < extensions.length; i++) {
						if (i != 0) {
							desc += ", ";
						}
						desc += "*." + extensions[i];
					}
					
					desc += ")";
				}
				return desc;
			}
		};
		
		return tmp;
    }
    
    /**
     * Liefert eine kurze Beschreibung für Dateien eines bestimmten Typs. 
     * @param extension Die Erweiterung (bspw. "txt").
     * @return Die Beschreibung (bspw. "Textdatei (*.txt)").
     */
    public static String getFileDescription(String extension) {
    	String ext = extension.toLowerCase();
    	
    	String desc;
    	
    	if (ext.equals("txt")) {
    		desc =  "Textdatei";
    	} else if (ext.equals("csv")) {
    		desc = "CSV (Trennzeichen getrennt)";
    	} else if (ext.equals("png")) {
    		desc = "PNG-Datei (Portable Network Graphics)";
    	} else {
    		desc = extension.toUpperCase() + "-Datei";
    	}
    	
    	return desc + " (*." + ext + ")";
    }
    
	/**
	 * Zeigt einen FileChooser, um eine Tabelle in eine CSV-Datei zu exportieren.
	 * Fragt vor dem überschreiben von bereits vorhandenen Dateien nach.
	 * @param tabelle Die Tabelle.
	 * @param frame Das HauptFrame um den Chooser anzuzeigen und eventuelle Meldungen auszugeben. 
	 */
	public static void saveTabelle(JTable tabelle, HauptFrame frame) {
		File exportDatei = frame.saveFile(new String[]{"csv"});
		if (exportDatei != null) {
			String ext = AuikUtils.getExtension(exportDatei);
			
			if (ext == null) {
				String newExt;
				if (exportDatei.getName().endsWith(".")) {
					newExt = "csv";
				} else {
					newExt = ".csv";
				}
				exportDatei = new File(exportDatei.getParent(), exportDatei.getName()+newExt);
			}
			
			boolean doIt = false;
			if (exportDatei.exists()) {
				boolean answer = frame.showQuestion( 
						"Soll die vorhandene Datei "+exportDatei.getName()+" wirklich überschrieben werden?", 
						"Datei bereits vorhanden!");
				if (answer && exportDatei.canWrite()) {
					doIt = true;
				}
			} else if (exportDatei.getParentFile().canWrite()) {
				doIt = true;
			}
			
			if (doIt) {
				AUIKataster.debugOutput("Speichere nach '" + exportDatei.getName() + "' (Ext: '"+ext+"') in '" + exportDatei.getParent() + "' !");
				if (exportTableDataToCVS(tabelle, exportDatei)) {
					AUIKataster.debugOutput("Speichern erfolgreich!");	
				} else {
					AUIKataster.debugOutput("Fehler beim Speichern!");
					frame.showErrorMessage("Beim Speichern der Datei '"+exportDatei+"' trat ein Fehler auf!");
				}
			}
		}
	}
    
    /**
     * Speichert den Inhalt einer Tabelle (mit samt überschriften) in 
     * eine CSV-Datei (mit Semikolons getrennt).
     * @param table Die Tabelle.
     * @param file Die Datei in die geschrieben werden soll.
     * @return <code>true</code>, wenn alles geklappt hat, sonst <code>false</code>.
     */
    public static boolean exportTableDataToCVS(JTable table, File file) {
    	boolean success;
    	
    	TableModel model = table.getModel();
    	BufferedWriter bw = null;
    	
    	try {
    		file.createNewFile();
    		
    		bw = new BufferedWriter(new FileWriter(file));
    		
    		for (int h = 0; h < model.getColumnCount(); h++) {
    			bw.write(model.getColumnName(h));
    			
    			if (h+1 != model.getColumnCount()) {
    				bw.write(";");
    			}
    		}
    		
    		bw.newLine();
    		
    		int clmCnt = model.getColumnCount();
    		int rowCnt = model.getRowCount();
    		
    		for (int i = 0; i < rowCnt; i++) {
    			for (int j = 0; j < clmCnt; j++) {
    				if (model.getValueAt(i, j) != null) {
    					String value = model.getValueAt(i, j).toString();
    					value = value.replace('\n', ' ');
    					bw.write(value);
    				}
    				
    				if(j+1 != clmCnt) {
    					bw.write(";");
    				}
    			}
    			
    			bw.newLine();
    		}
    		
    		bw.flush();
    		
    		success = true;
    		
    	} catch (IOException e) {
    		success = false;
    		e.printStackTrace();
    	} finally {
    		if (bw != null) {
    			try {
					bw.close();
				} catch (IOException e1) {
					throw new RuntimeException("Datei-Fehler (AuIKUtils)", e1);
				}
    		}
    	}
    	
    	return success;
    }
	
	/**
	 * Erzeugt einen neuen MaskFormatter für ein FormattedTextField
	 * @param s The formatting mask
	 * @return The new MaskFormatter created using the mask or <code>null</code> if the mask was bad 
	 * @see <a href="http://java.sun.com/docs/books/tutorial/uiswing/components/formattedtextfield.html#maskformatter">Swing Tutorial (MaskFormatter)</a>
	 */
	public static MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        AUIKataster.debugOutput("formatter is bad: " + exc.getMessage(), "AuikUtils.createFormatter");
	    }
	    return formatter;
	}
	
	/**
	 * Entfernt SQL/HQL Sonderzeichen (konkret sind das ;, ', ( und ) ) aus einem String.
	 * Sollte auf alle Strings angewandt werden, die direkt und NICHT 
	 * als =? Parameter in einer SQL/HQL-Abfrage benutzt werden.
	 * @param input Der String aus dem die Sonderzeichen entfernt werden sollen.
	 * @return Ein String ohne die oben genannten Zeichen und ohne Whitespace am Anfang und am Ende.
	 */
	public static String sanitizeQueryInput(String input) {
		return input.replaceAll(";","").replaceAll("'","").replaceAll("\\(","").replaceAll("\\)","").trim();
	}
	
	/**
	 * Liefert einen String der Form "dd.mm.JJJJ" für ein 
	 * gegebenes Datums-Objekt.
	 * @param date Das Datum
	 * @return Einen String der Form "dd.mm.JJJJ" oder <code>null</code>, falls date <code>null</code> ist
	 */
	public static String getStringFromDate(Date date) {
		if (date != null) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			
			int day = cal.get(Calendar.DAY_OF_MONTH);
			String dayString = (day < 10) ? ("0"+day) : (""+day);
			
			int month = cal.get(Calendar.MONTH) + 1;
			String monthString = (month < 10) ? ("0"+month) : (""+month);
			
			return  dayString + "." + monthString + "." + cal.get(Calendar.YEAR);
		} else {
			return null;
		}
	}
	
	/**
	 * Liefert einen String der Form "dd.mm.JJJJ" für das 
	 * aktuelle Datum.
	 * @return Einen String der Form "dd.mm.JJJJ"
	 */
	public static String getStringFromCurrentDate() {
		return getStringFromDate(getCurrentDate());
	}
	
	/**
	 * Liefert das aktuelle Datum / die aktuelle Uhrzeit.
	 * @return Das aktuelle Date-Objekt
	 */
	public static Date getCurrentDate() {
		if (cal == null) {
			cal = GregorianCalendar.getInstance();
		} else {
			cal.setTimeInMillis(System.currentTimeMillis());
		}
		return cal.getTime();
	}
	
	/**
	 * Erzeugt ein Icon aus einem Bild aus dem Icons-Package. 
	 * Der Dateiname muss ohne Pfad o.Ä. (also einfach "bild.png")
	 * angegeben werden.
	 * @param filename Der Name der Bilddatei (ohne Pfad)
	 * @return Ein Icon (oder <code>null</code>, falls kein Icon dieses Namens gefunden wurde)
	 */
	public static Icon getIcon(String filename) {
		return getIcon(filename, null);
	}
	
	/**
	 * Erzeugt ein Icon aus einem Bild aus dem Icons-Package. 
	 * Der Dateiname muss ohne Pfad o.Ä. (also einfach "bild.png")
	 * angegeben werden. Zusätzlich muss die Größe (32 für 32x32 etc.)
	 * angegeben werden.
	 * @param size Die Größe des Icons
	 * @param filename Der Name der Bilddatei (ohne Pfad)
	 * @return Ein Icon (oder <code>null</code>, falls kein Icon dieses Namens gefunden wurde)
	 */
	public static Icon getIcon(int size, String filename) {
		return getIcon(size, filename, null);
	}
	
	/**
	 * Erzeugt ein Icon aus einem Bild aus dem Icons-Package. 
	 * Der Dateiname muss ohne Pfad o.Ä. (also einfach "bild.png")
	 * angegeben werden.
	 * @param filename Der Name der Bilddatei (ohne Pfad)
	 * @param description Eine kurze textuelle Beschreibung
	 * @return Ein Icon (oder <code>null</code>, falls kein Icon dieses Namens gefunden wurde)
	 */
	public static Icon getIcon(String filename, String description) {
		return getIcon(-1, filename, description);
	}
	
	/**
	 * Erzeugt ein Icon aus einem Bild aus dem Icons-Package. 
	 * Der Dateiname muss ohne Pfad o.Ä. (also einfach "bild.png")
	 * angegeben werden. Zusätzlich muss die Größe (32 für 32x32 etc.)
	 * angegeben werden.
	 * @param size Die Größe des Icons
	 * @param filename Der Name der Bilddatei (ohne Pfad)
	 * @param description Eine kurze textuelle Beschreibung
	 * @return Ein Icon (oder <code>null</code>, falls kein Icon dieses Namens gefunden wurde)
	 */
	public static Icon getIcon(int size, String filename, String description) {
		if (filename == null) {
			filename = "default.png";
		}
		
		if (description == null) {
			description = "Icon: " + filename;
		}
		
		String sSize;
		if (size == -1) {
			sSize = "";
		} else {
			sSize = size + "/";
		}
		String iconPath = "icons/" + sSize + filename;
		URL iconURL = AUIKataster.class.getResource(iconPath);
		if (iconURL != null) {
			return new ImageIcon(iconURL, description);
		} else {
			AUIKataster.debugOutput("Konnte Icon "+ iconPath +" nicht finden!", "AuikUtils.getIcon");
			return null;
		}
	}
	
	/**
	 * überprüft, ob wir unter XP und mit dem XP-Stil angezeigt werden.
	 * @return <code>true</code>, wenn der XP-Stil aktiv ist, sonst <code>false</code>
	 */
	public static boolean isUsingXpStyle() {
		boolean tmp;
		
		LookAndFeel laf = UIManager.getLookAndFeel();
		if (laf instanceof WindowsLookAndFeel && !(laf.getClass().getName().endsWith("WindowsClassicLookAndFeel"))) {
			if (System.getProperty("swing.noxp") != null) {
				//System.out.println("Using Classic style (\"swing.noxp\" defined)");
				tmp = false;
			} else {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				boolean themeActive =
					Boolean.TRUE.equals(toolkit.getDesktopProperty("win.xpstyle.themeActive"));
				if (!themeActive) {
					//System.out.println("Using Classic style (XP style not enabled on desktop)");
					tmp = false;
				} else {
					/*String dllName   = (String)toolkit.getDesktopProperty("win.xpstyle.dllName");
					String colorName = (String)toolkit.getDesktopProperty("win.xpstyle.colorName");
					String sizeName  = (String)toolkit.getDesktopProperty("win.xpstyle.sizeName");
					System.out.println("XP Style: " + dllName);
					System.out.println("Color scheme: " + colorName);
					System.out.println("Font size: " + sizeName);*/
					tmp = true;
				}
			}
		} else {
			tmp = false;
		}
		
		return tmp;
	}
}
