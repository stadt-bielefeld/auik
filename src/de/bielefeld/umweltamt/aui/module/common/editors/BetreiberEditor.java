package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.HibernateException;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStrassen;
import de.bielefeld.umweltamt.aui.mappings.vaws.VawsWirtschaftszweige;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.LongNameComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
/**
 * Ein Dialog zum Bearbeiten eines Betreibers.
 * @author David Klotz
 */
public class BetreiberEditor extends AbstractBaseEditor {
	// Für die Comboboxen beim Bearbeiten
	private static String[] strassen = null;
	private static VawsWirtschaftszweige[] wirtschaftszweige = null;
	
	private JLabel handzeichenLabel;
	private JLabel namenLabel;
	
	private JTextField anredeFeld;
	private JTextField namenFeld;
	private JTextField nameZusFeld;
	private JFormattedTextField hausnrFeld;
	private JTextField hausnrZusFeld;
	private JTextField plzZsFeld;
	private JTextField plzFeld;
	private JTextField ortsFeld;
	private JTextField telefonFeld;
	private JTextField telefaxFeld;
	private JTextField emailFeld;
	private JTextField betrBeaufVornameFeld;
	private JTextField betrBeaufNachnameFeld;
	private JTextField revdatumsFeld;
	private JTextField handzeichenAltFeld;
	private JTextField handzeichenNeuFeld;
	
	private JTextArea bemerkungsArea;
	
	private JComboBox strassenBox;
	private JComboBox wirtschaftszweigBox;
	
	
	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten eines Betreibers.
	 */
	public BetreiberEditor(BasisBetreiber betr, HauptFrame owner) {
		super("Betreiber ("+ betr.getBetreiberid()+")", betr, owner);
	}
	
	protected JComponent buildContentArea() {
		anredeFeld = new LimitedTextField(100);
		namenFeld = new LimitedTextField(100);
		nameZusFeld = new LimitedTextField(50);
		hausnrFeld = new IntegerField();
		hausnrZusFeld = new LimitedTextField(10);
		plzZsFeld = new LimitedTextField(3);
		
		plzFeld = new LimitedTextField(10);
		ortsFeld = new LimitedTextField(50);
		telefonFeld = new LimitedTextField(50);
		telefaxFeld = new LimitedTextField(50);
		emailFeld = new LimitedTextField(50);
		betrBeaufVornameFeld = new LimitedTextField(50);
		betrBeaufNachnameFeld = new LimitedTextField(50);
		
		revdatumsFeld = new JTextField();
		revdatumsFeld.setEditable(false);
		revdatumsFeld.setFocusable(false);
		revdatumsFeld.setToolTipText("Wird bei Änderungen automatisch aktualisiert.");
		
		handzeichenAltFeld = new JTextField();
		handzeichenAltFeld.setEditable(false);
		handzeichenAltFeld.setFocusable(false);
		handzeichenAltFeld.setToolTipText("Handzeichen der letzten Revision");
		handzeichenNeuFeld = new LimitedTextField(10, "");
		handzeichenNeuFeld.setToolTipText("Neues Handzeichen bei Änderungen obligatorisch!");
		
		bemerkungsArea = new LimitedTextArea(2000);
		bemerkungsArea.setLineWrap(true);
		bemerkungsArea.setWrapStyleWord(true);
		JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		strassenBox = new JComboBox();
		strassenBox.setEditable(true);
		wirtschaftszweigBox = new JComboBox();
		wirtschaftszweigBox.setRenderer(new LongNameComboBoxRenderer());
		
		// Der folgende KeyListener wird benutzt um mit Escape 
		// das Bearbeiten abzubrechen und bei Enter im
		// Handzeichen-Feld (wenn das Feld nicht leer ist) zum
		// Speichern-Button zu springen.
		KeyListener escEnterListener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getSource().equals(handzeichenNeuFeld)) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (handzeichenNeuFeld.getText().equals("")) {
							handzeichenLabel.setForeground(Color.RED);
							handzeichenNeuFeld.requestFocus();
						} else {
							button1.requestFocus();
						}
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					doCancel();
				}
			}
		};
		anredeFeld.addKeyListener(escEnterListener);
		namenFeld.addKeyListener(escEnterListener);
		nameZusFeld.addKeyListener(escEnterListener);
		hausnrFeld.addKeyListener(escEnterListener);
		hausnrZusFeld.addKeyListener(escEnterListener);
		plzZsFeld.addKeyListener(escEnterListener);
		plzFeld.addKeyListener(escEnterListener);
		ortsFeld.addKeyListener(escEnterListener);
		telefonFeld.addKeyListener(escEnterListener);
		telefaxFeld.addKeyListener(escEnterListener);
		emailFeld.addKeyListener(escEnterListener);
		betrBeaufVornameFeld.addKeyListener(escEnterListener);
		betrBeaufNachnameFeld.addKeyListener(escEnterListener);
		handzeichenNeuFeld.addKeyListener(escEnterListener);
		bemerkungsArea.addKeyListener(escEnterListener);
		strassenBox.addKeyListener(escEnterListener);
		//strassenFeld.addKeyListener(escEnterListener);
		wirtschaftszweigBox.addKeyListener(escEnterListener);
		
		// Ermögliche TAB aus dem Bemerkungs-Feld zu springen
		bemerkungsScroller.getVerticalScrollBar().setFocusable(false);
		bemerkungsScroller.getHorizontalScrollBar().setFocusable(false);
		TabAction tac = new TabAction(bemerkungsArea, handzeichenNeuFeld);
		
		String columnString = "right:pref, 3dlu, 20dlu, 40dlu:grow(1.0), 5dlu, right:pref, 3dlu, 27dlu:grow(1.0), 3dlu, 30dlu:grow(1.0)";
		FormLayout layout = new FormLayout(
				// Spalten
				columnString +		// Links
				", 10dlu, " +		// Abstand
				columnString,		// Rechts
				
				// Zeilen:
				"pref, 3dlu, " +	//1 Stammdaten	- Betr.beauft.
				"pref, 3dlu, " +	//3
				"pref, 3dlu, " +	//5 			- Bemerkungen
				"pref, 3dlu, " +	//7
				"pref, 3dlu, " +	//9
				
				"pref, 3dlu, " +	//11 Adresse	- Alte Revision
				"pref, 3dlu, " +	//13
				"pref, 3dlu, " +	//15
				"pref, 3dlu, " +	//17			- Neue Revision
				"pref, 3dlu, " +	//19
		"pref");			//21
		layout.setRowGroups(new int[][]{{1,3,5,7,9, 11,13,15,17,19,21}});
		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();
		
		// Stamdaten ------------------------------------
		builder.addSeparator("Stammdaten", cc.xyw(1,1,10));
		// Anrede
		builder.addLabel("Anrede:",	cc.xy( 1, 3));
		builder.add(anredeFeld,		cc.xyw(3, 3,8));
		// Name
		namenLabel = builder.addLabel("Name:",	cc.xy( 1, 5));
		builder.add(namenFeld,					cc.xyw(3, 5,8));
		// Zusatz
		builder.addLabel("Vorname/Zusatz:", 	cc.xy( 1, 7));
		builder.add(nameZusFeld, 		cc.xyw(3, 7,8));
		// Wirtschaftszweig
		builder.addLabel("Wirtschaftszweig:",	cc.xy( 1,9));
		builder.add(wirtschaftszweigBox, 		cc.xyw(3,9,8));
		
		// Adresse --------------------------------------
		builder.addSeparator("Adresse", cc.xyw(1,11,10));
		// Straße
		builder.addLabel("Straße:",	cc.xy( 1,13));
		builder.add(strassenBox, 	cc.xyw(3,13,4));
		builder.add(hausnrFeld, 	cc.xy( 8,13));
		builder.add(hausnrZusFeld, 	cc.xy(10,13));
		// Ort
		builder.addLabel("Ort:", 	cc.xy( 1,15));
		builder.add(plzZsFeld, 		cc.xy( 3,15));
		builder.add(plzFeld, 		cc.xy( 4,15));
		builder.add(ortsFeld, 		cc.xyw(6,15,5));
		// Telefon
		builder.addLabel("Telefon:",cc.xy( 1,17));
		builder.add(telefonFeld, 	cc.xyw(3,17,4));
		// Telefax
		builder.addLabel("Telefax:",cc.xy( 1,19));
		builder.add(telefaxFeld, 	cc.xyw(3,19,4));
		// eMail
		builder.addLabel("E-Mail:", cc.xy(1,21));
		builder.add(emailFeld, 		cc.xyw(3,21,4));
		
		// Betriebsbeauftragter -------------------------
		builder.addSeparator("Betriebsbeauftragter", cc.xyw( 1+11,1,10));
		// Vorname
		builder.addLabel("Vorname:",		cc.xy(1+11,3));
		builder.add(betrBeaufVornameFeld, 	cc.xyw(3+11,3,2));
		// Nachname
		builder.addLabel("Nachname:",		cc.xy(6+11,3));
		builder.add(betrBeaufNachnameFeld, 	cc.xyw(8+11,3,3));
		
		// Bemerkungen ----------------------------------
		builder.addSeparator("Bemerkungen", cc.xyw( 1+11,5,10));
		builder.add(bemerkungsScroller,		cc.xywh(1+11,7,10,3));
		
		// Alte Revision --------------------------------
		builder.addSeparator("Letzte Revision", cc.xyw( 1+11,11,10));
		// Datum
		builder.addLabel("Datum:", 		cc.xy( 1+11,13));
		builder.add(revdatumsFeld, 		cc.xyw(3+11,13,4));
		// Handzeichen (alt)
		builder.addLabel("Handzeichen:",cc.xy( 1+11,15));
		builder.add(handzeichenAltFeld, cc.xyw(3+11,15,4));
		
		// Neue Revision --------------------------------
		builder.addSeparator("Neue Revision", cc.xyw( 1+11,17,10));
		// Handzeichen (neu)
		handzeichenLabel = builder.addLabel("Handzeichen:", cc.xy( 1+11,19));
		builder.add(handzeichenNeuFeld, 					cc.xyw(3+11,19,4));
		
		return builder.getPanel();
	}
	
	protected void fillForm() {
		SwingWorkerVariant worker = new SwingWorkerVariant(this) {
			
			protected void doNonUILogic() throws RuntimeException {
				try {
					if (strassen == null) {
						strassen = BasisStrassen.getStrassen();
					}
					if (wirtschaftszweige == null) {
						wirtschaftszweige = VawsWirtschaftszweige.getWirtschaftszweige();
					}
				} catch (HibernateException e) {
					throw new RuntimeException(e);
				}
			}
			
			protected void doUIUpdateLogic() throws RuntimeException {
				if (strassen != null) {
					strassenBox.setModel(new DefaultComboBoxModel(strassen));
					strassenBox.setSelectedItem(getBetreiber().getStrasse());
				}
				if (wirtschaftszweige != null) {
					wirtschaftszweigBox.setModel(new DefaultComboBoxModel(wirtschaftszweige));
					wirtschaftszweigBox.setSelectedItem(getBetreiber().getVawsWirtschaftszweige());
				}
				
				anredeFeld.setText(getBetreiber().getBetranrede());
				namenFeld.setText(getBetreiber().getBetrname());
				nameZusFeld.setText(getBetreiber().getBetrnamezus());
				hausnrFeld.setValue(getBetreiber().getHausnr());
				hausnrZusFeld.setText(getBetreiber().getHausnrzus());
				String plzZs = getBetreiber().getPlzzs();
				if (plzZs != null) {
					plzZs = plzZs.trim();
				}
				plzZsFeld.setText(plzZs);
				plzFeld.setText(getBetreiber().getPlz());
				ortsFeld.setText(getBetreiber().getOrt());
				telefonFeld.setText(getBetreiber().getTelefon());
				telefaxFeld.setText(getBetreiber().getTelefax());
				emailFeld.setText(getBetreiber().getEmail());
				
				betrBeaufVornameFeld.setText(getBetreiber().getVornamebetrbeauf());
				betrBeaufNachnameFeld.setText(getBetreiber().getNamebetrbeauf());
				
				handzeichenAltFeld.setText(getBetreiber().getRevihandz());
				bemerkungsArea.setText(getBetreiber().getBemerkungen());
				
				Date datum = getBetreiber().getRevidatum();
				revdatumsFeld.setText(AuikUtils.getStringFromDate(datum));
				
				frame.clearStatus();
			}
		};
		frame.changeStatus("Beschäftigt...");
		worker.start();
	}
	
	protected boolean canSave() {
		// Eingaben überprüfen:
		if (namenFeld.getText().equals("")) {
			// Der Name darf nicht leer sein
			namenLabel.setForeground(HauptFrame.ERROR_COLOR);
			namenFeld.requestFocus();
			String nameErr = "Der Name darf nicht leer sein!";
			frame.changeStatus(nameErr, HauptFrame.ERROR_COLOR);
			AUIKataster.debugOutput(nameErr, "BetreiberEdit.doSave");
			return false;
		} else if (handzeichenNeuFeld.getText().equals("")) {
			// Das Handzeichen darf nicht leer sein
			handzeichenLabel.setForeground(HauptFrame.ERROR_COLOR);
			handzeichenNeuFeld.requestFocus();
			String handzErr = "Neues Handzeichen erforderlich!";
			frame.changeStatus(handzErr, HauptFrame.ERROR_COLOR);
			AUIKataster.debugOutput(handzErr, "BetreiberEdit.doSave");
			return false;
		} else {
			// Wenn die Eingaben korrekt sind
			return true;
		}
	}
	
	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
	 * @throws HibernateException Wenn beim Speichern ein Fehler auftritt
	 */
	protected boolean doSave() {
		// Anrede
		String anrede = anredeFeld.getText();
		if ("".equals(anrede)) {
			getBetreiber().setBetranrede(null);
		} else {
			getBetreiber().setBetranrede(anrede);
		}
		// Name
		String name = namenFeld.getText();
		if ("".equals(name)) {
			getBetreiber().setBetrname(null);
		} else {
			getBetreiber().setBetrname(name);
		}
		// Zusatz
		String nameZusatz = nameZusFeld.getText();
		if ("".equals(nameZusatz)) {
			getBetreiber().setBetrnamezus(null);
		} else {
			getBetreiber().setBetrnamezus(nameZusatz);
		}
		// Straße
		String stra = (String) strassenBox.getSelectedItem();
		if (stra != null) {
			stra = stra.trim();
			
			// Weil ich bis jetzt noch keine LimitedComboBox oder so habe...
			if (stra.length() > 50) {
				// ... kürze ich hier den String auf 50 Zeichen
				stra = stra.substring(0, 50);
			}
			
			if ("".equals(stra)) {
				getBetreiber().setStrasse(null);
			} else {
				getBetreiber().setStrasse(stra);
			}
		}
		// Hausnummer:
		Integer hausnr = ((IntegerField) hausnrFeld).getIntValue();
		getBetreiber().setHausnr(hausnr);
		
		// Hausnummer-Zusatz:
		String hausnrZus = hausnrZusFeld.getText();
		if ("".equals(hausnrZus)) {
			getBetreiber().setHausnrzus(null);
		} else {
			getBetreiber().setHausnrzus(hausnrZus);
		}
		
		// PLZ-Zusatz
		String plzZs = plzZsFeld.getText();
		if ("".equals(plzZs)) {
			getBetreiber().setPlzzs(null);
		} else {
			getBetreiber().setPlzzs(plzZs.toUpperCase().trim());
		}
		
		// PLZ:
		String plz = plzFeld.getText();
		if (plz != null) {
			plz = plz.trim();
			if (plz.equals("")) {
				getBetreiber().setPlz(null);
			} else {
				getBetreiber().setPlz(plz);
			}
		}
		// Ort
		String ort = ortsFeld.getText();
		if (ort != null) {
			ort = ort.trim();
			if (ort.equals("")) {
				getBetreiber().setOrt(null);
			} else {
				getBetreiber().setOrt(ort);
			}
		}
		// Telefon
		String telefon = telefonFeld.getText();
		if (telefon != null) {
			telefon = telefon.trim();
			if (telefon.equals("")) {
				getBetreiber().setTelefon(null);
			} else {
				getBetreiber().setTelefon(telefon);
			}
		}
		// Telefax
		String telefax = telefaxFeld.getText();
		if (telefax != null) {
			telefax = telefax.trim();
			if (telefax.equals("")) {
				getBetreiber().setTelefax(null);
			} else {
				getBetreiber().setTelefax(telefax);
			}
		}
		// eMail
		String email = emailFeld.getText();
		if (email != null) {
			email = email.trim();
			if (email.equals("")) {
				getBetreiber().setEmail(null);
			} else {
				getBetreiber().setEmail(email);
			}
		}
		// Betriebsbeauftragter-Vorname
		String betrBeaufVorname = betrBeaufVornameFeld.getText();
		if (betrBeaufVorname != null) {
			betrBeaufVorname = betrBeaufVorname.trim();
			if (betrBeaufVorname.equals("")) {
				getBetreiber().setVornamebetrbeauf(null);
			} else {
				getBetreiber().setVornamebetrbeauf(betrBeaufVorname);
			}
		}
		// Betriebsbeauftragter-Nachname
		String betrBeaufNachname = betrBeaufNachnameFeld.getText();
		if (betrBeaufNachname != null) {
			betrBeaufNachname = betrBeaufNachname.trim();
			if (betrBeaufNachname.equals("")) {
				getBetreiber().setNamebetrbeauf(null);
			} else {
				getBetreiber().setNamebetrbeauf(betrBeaufNachname);
			}
		}
		// Wirtschaftszweig
		VawsWirtschaftszweige wizw = (VawsWirtschaftszweige) wirtschaftszweigBox.getSelectedItem();
		getBetreiber().setVawsWirtschaftszweige(wizw);
		
		// Bemerkungen
		String bemerkungen = bemerkungsArea.getText();
		if (bemerkungen != null) {
			bemerkungen = bemerkungen.trim();
			if (bemerkungen.equals("")) {
				getBetreiber().setBemerkungen(null);
			} else {
				getBetreiber().setBemerkungen(bemerkungen);
			}
		}
		// Handzeichen
		String handzeichen = handzeichenNeuFeld.getText().trim();
		getBetreiber().setRevihandz(handzeichen);
		
		getBetreiber().setRevidatum(Calendar.getInstance().getTime());
		
		//frame.changeStatus("Keine Änderungen an Betreiber "+betr.getBetreiberid()+" vorgenommen.");
		
		BasisBetreiber betr = BasisBetreiber.saveBetreiber(getBetreiber());
		
		if (betr != null) {
			setEditedObject(betr);
			AUIKataster.debugOutput("Änderungen gespeichert!", "editStandort");
			return true;
		} else {
			return false;
		}
	}
	
	public BasisBetreiber getBetreiber() {
		return (BasisBetreiber) getEditedObject();
	}
}