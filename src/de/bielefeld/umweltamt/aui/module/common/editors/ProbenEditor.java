/*
 * Datei:
 * $Id: ProbenEditor.java,v 1.1 2008-06-05 11:38:41 u633d Exp $
 * 
 * Erstellt am 25.5.05 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/09/14 11:25:36  u633d
 * - Version vom 14.9.
 *
 * Revision 1.10  2005/09/07 10:48:22  u633d
 * - Version vom 7.9.05
 *
 * Revision 1.9  2005/07/06 09:40:13  u633z
 * - Grenzwerte auch für Sielhaut-Proben
 *
 * Revision 1.8  2005/06/30 11:46:33  u633z
 * *** empty log message ***
 *
 * Revision 1.7  2005/06/14 13:33:39  u633z
 * - Layout korrigiert
 *
 * Revision 1.6  2005/06/10 11:07:21  u633z
 * - Datenbank-Zugriffe komplett in DB-Klassen ausgelagert
 * - Einige DB-Klassen vervollständigt
 *
 * Revision 1.5  2005/06/09 13:45:33  u633z
 * - ProbenEditor auf AbstractBaseEditor umgestellt
 *
 */
package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbenahmen;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.ComboBoxRenderer;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.DoubleRenderer;
import de.bielefeld.umweltamt.aui.utils.KommaDouble;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.SelectTable;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.EditableListTableModel;

/**
 * Ein Dialog um eine Probenahme mit ihren Analysepositionen zu bearbeiten.
 * (Momentan ist vieles noch auf Klärschlamm-Proben ausgerichtet)
 * @author David Klotz
 */
public class ProbenEditor extends AbstractBaseEditor {
	private class ParameterModel extends EditableListTableModel {
		private AtlProbenahmen probe;
		private boolean isNew;
		
		public ParameterModel(AtlProbenahmen probe, boolean isNew) {
			super(new String[]{
					"Parameter",
					"GrKl",
					"Messwert",
					"Einheit",
					"Analyse von",
					"Grenzwert",
					"% Normwert"}, false, true);
			this.probe = probe;
			this.isNew = isNew;
			
			updateList();
		}
		
		public void updateList() {
			//Set positionen;
			if (!isNew) {
				//AUIKataster.debugOutput("Bearbeite alte Probe: " + probe);
				//if (!probe.isAnalysepositionenInitialized()) {
					//probe = AtlProbenahmen.getProbenahme(probe.getKennummer(), true);
					//AUIKataster.debugOutput("Analysepositionen gefetcht! Probe neu geholt: " + probe);
				//}
				//positionen = probe.getAtlAnalysepositionen();
				probe = AtlProbenahmen.getProbenahme(probe.getId(), true);
				setList(AtlProbenahmen.sortAnalysepositionen(probe));
			} else {
				//AUIKataster.debugOutput("Bearbeite neue Probe! Positionen leer.");
				//positionen = new HashSet();
				setList(new ArrayList());
			}
			
			fireTableDataChanged();
		}
		
		public Object getColumnValue(Object objectAtRow, int columnIndex) {
			Object value;
			AtlAnalyseposition pos = (AtlAnalyseposition) objectAtRow;
			switch(columnIndex) {
				// Parameter
				case 0:
					value = pos.getAtlParameter();
					break;
				// GrKl
				case 1:
					value = pos.getGrkl();
					break;
				// Wert
				case 2:
					value = new KommaDouble(pos.getWert());
					break;
				// Einheit
				case 3:
					value = pos.getAtlEinheiten();
					break;
				// Analyse von
				case 4:
					value = pos.getAnalyseVon();
					break;
				// Grenzwert
				case 5:
					value = null;
					Double grenzWert = null;
					if (probe.isKlaerschlammProbe()) {
						grenzWert = pos.getAtlParameter().getKlaerschlammGw();
					} else if (probe.getProbeArt().isSielhaut()) {
						grenzWert = pos.getAtlParameter().getSielhautGw();
					}
					
					if (grenzWert != null && !grenzWert.equals(new Double(0.0))) {
						value = grenzWert;
					}
					break;
				// % Normwert
				case 6:
					value = "-";
					if (pos.getAtlEinheiten().getId().equals(AtlEinheiten.MG_KG_ID)) {
						double tmpVal = -1;
						if (probe.isKlaerschlammProbe()) {
							if (pos.getAtlParameter().getKlaerschlammGw() != null) {
								if (!pos.getAtlParameter().getKlaerschlammGw().equals(new Double(0.0))) {	
									tmpVal = pos.getWert().doubleValue() / pos.getAtlParameter().getKlaerschlammGw().doubleValue();
								}
							}
						} else if (probe.getProbeArt().isSielhaut()) {
							if (pos.getAtlParameter().getSielhautGw() != null) {
								if (!pos.getAtlParameter().getSielhautGw().equals(new Double(0.0))) {	
									tmpVal = pos.getWert().doubleValue() / pos.getAtlParameter().getSielhautGw().doubleValue();
								}
							}
						}
						if (tmpVal != -1) {
							NumberFormat percentFormat = NumberFormat.getPercentInstance();
							percentFormat.setMinimumFractionDigits(1);
							if (tmpVal > 1.0) {
								value = "<html><font color=#ff0000>" + percentFormat.format(tmpVal) + "</font></html>";
							} else {
								value = percentFormat.format(tmpVal);
							}
						}
					}
					break;
				default:
					value = null;
			}
			
			return value;
		}
		
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex == 5 || columnIndex == 6) {
				return false;
			} else {
				return true;
			}
		}
		public void editObject(Object objectAtRow, int columnIndex,
				Object newValue) {
			AtlAnalyseposition tmp = (AtlAnalyseposition) objectAtRow;
			
			switch (columnIndex) {
				case 0:
					if (newValue instanceof AtlParameter) {
						AtlParameter tmpPara = (AtlParameter) newValue;
						tmp.setAtlParameter(tmpPara);
					}
					break;
				
				case 1:
					String tmpGrKl = (String) newValue;
					if (tmpGrKl != null) {
						tmpGrKl = tmpGrKl.trim();
						if (tmpGrKl.equals("")) {
							tmp.setGrkl(null);
						} else {
							tmpGrKl = tmpGrKl.substring(0,1);
							if (tmpGrKl.equals("<") || tmpGrKl.equals(">")) {
								tmp.setGrkl(tmpGrKl);
							}
						}
					}
					break;
					
				case 2:
					Double tmpWert = null;
					if (newValue instanceof Double) {
						tmpWert = (Double) newValue;
					} else if (newValue instanceof KommaDouble) {
						tmpWert = ((KommaDouble)newValue).getValue();
					}
					tmp.setWert(tmpWert);
					break;
					
				case 3:
					AtlEinheiten tmpEinheit = (AtlEinheiten) newValue;
					tmp.setAtlEinheiten(tmpEinheit);
					break;
					
				case 4:
					String tmpAnalyse = (String) newValue;
					if (tmpAnalyse != null) {
						tmpAnalyse = tmpAnalyse.trim();
						if (tmpAnalyse.equals("")) {
							tmp.setAnalyseVon(null);
						} else {
							tmp.setAnalyseVon(tmpAnalyse);
						}
					}
					break;

				default:
					break;
			}
			
			AUIKataster.debugOutput("EDIT: " + tmp, "ParameterModel.editObject");
		}
		
		public Object newObject() {
			AtlAnalyseposition tmp = new AtlAnalyseposition(probe);
			if (probe.isKlaerschlammProbe() || probe.getProbeArt().isSielhaut()) {
				tmp.setAtlEinheiten(AtlEinheiten.getEinheit(AtlEinheiten.MG_KG_ID));
			} else {
				tmp.setAtlEinheiten(AtlEinheiten.getEinheit(AtlEinheiten.MG_L_ID));
			}
			tmp.setAtlParameter(AtlParameter.getParameter(AtlParameter.DEFAULT_ID));
			//tmp.setAnalyseVon("");
			return tmp;
		}
		
		public boolean objectRemoved(Object objectAtRow) {
			//AtlAnalyseposition tmp = (AtlAnalyseposition) objectAtRow;
			//getList().remove(tmp);
			
			return true;//probe.getAtlAnalysepositionen().remove(tmp);
		}
		
		public Class getColumnClass(int columnIndex) {
			Class tmp;
			switch (columnIndex) {
				case 0:
					tmp = AtlParameter.class;
					break;
					
				case 2:
					tmp = KommaDouble.class;
					break;
				
				case 3:
					tmp = AtlEinheiten.class;
					break;
				
				case 5:
					tmp = Double.class;
					break;
					
				case 6:
					tmp = String.class;
					break;

				default:
					tmp = super.getColumnClass(columnIndex);
					break;
			}
			return tmp;
		}
	}
	
	private Dimension minimumSize;
	
	private TextFieldDateChooser datumFeld;
	private JFormattedTextField icpEinwaageFeld;
	private TextFieldDateChooser icpDatumFeld;
	private JTextArea bemerkungsArea;
	private JTable parameterTabelle;
	
	private JComboBox parameterBox;
	private JComboBox einheitenBox;
	
	private ParameterModel parameterModel;
	private boolean isNew;
	
	public ProbenEditor(AtlProbenahmen probe, HauptFrame owner, boolean isNew) {
		super("Probenahme " + probe.getKennummer(), probe, owner);
		this.isNew = isNew;
		
		if (!isNew /*probe.isAnalysepositionenInitialized()*/) {
			setEditedObject(AtlProbenahmen.getProbenahme(probe.getId(), true));
		}
		
		parameterModel = new ParameterModel(getProbe(), isNew);
		parameterTabelle.setModel(parameterModel);
		
		initColumns();
	}
	
	protected JComponent buildContentArea() {
		datumFeld = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		
		//if (probe.isKlaerschlammProbe()) {
			icpEinwaageFeld = new DoubleField(0);
		//}
		
		icpDatumFeld = new TextFieldDateChooser(AuikUtils.DATUMSFORMATE);
		
		
		bemerkungsArea = new LimitedTextArea(255);
		bemerkungsArea.setLineWrap(true);
		bemerkungsArea.setWrapStyleWord(true);
		JScrollPane bemerkungsScroller = new JScrollPane(bemerkungsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		bemerkungsScroller.setPreferredSize(new Dimension(0, 50));
		
		parameterTabelle = new SelectTable();
		parameterTabelle.setRowHeight(20);
		
		Action aposRemoveAction = new AbstractAction("Analyseposition löschen") {
			public void actionPerformed(ActionEvent e) {
				int row = parameterTabelle.getSelectedRow();
				if (row != -1 && parameterTabelle.getEditingRow() == -1) {
					parameterModel.removeRow(row);
				}
			}
		};
		KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false);
		aposRemoveAction.putValue(Action.ACCELERATOR_KEY, deleteKeyStroke);
        parameterTabelle.getInputMap().put(deleteKeyStroke, aposRemoveAction.getValue(Action.NAME));
		parameterTabelle.getActionMap().put(aposRemoveAction.getValue(Action.NAME), aposRemoveAction);
		
		JScrollPane parameterScroller = new JScrollPane(parameterTabelle, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//parameterScroller.setPreferredSize(new Dimension(600, 150));

		FormLayout layout = new FormLayout(
				"r:p, 3dlu, max(70dlu;p), 5dlu, r:p, 3dlu, max(70dlu;p), 0dlu:grow");	// Spalten, Zeilen werden dynamisch erzeugt
		layout.setColumnGroups(new int[][]{{1,5}, {3,7}});
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		
		builder.appendSeparator("Probe");//+ probe.getKennummer() +" - "+probe.getAtlProbepkt().getAtlProbeart());
		builder.append("Datum:", datumFeld);
		builder.nextLine();
		
		builder.appendSeparator("ICP");
		//if (probe.isKlaerschlammProbe()) {
			builder.append("Einwaage:", icpEinwaageFeld);
		//}
		builder.append("Datum:", icpDatumFeld);
		builder.nextLine();
		
		builder.appendSeparator("Bemerkung");
		builder.append(bemerkungsScroller, 7);
		builder.nextLine();
		
		builder.appendSeparator("Parameter");
		builder.appendRow("3dlu");
		builder.appendRow("fill:60dlu:grow");
		builder.nextLine(2);
		builder.append(parameterScroller, 8);
		
		return builder.getPanel();
	}
	
	protected void fillForm() {
		this.minimumSize = new Dimension(this.getSize());
		
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent event) {
				ProbenEditor.this.setSize(
						Math.max(minimumSize.width, ProbenEditor.this.getWidth()),
						Math.max(minimumSize.height, ProbenEditor.this.getHeight()));
			}
		});
		
		//parameterModel = new ParameterModel(getProbe(), isNew);
		//parameterTabelle.setModel(parameterModel);
		
		//initColumns();
		
		Date entnahmeDatum = getProbe().getDatumDerEntnahme();
		datumFeld.setDate(entnahmeDatum);
		
		if (getProbe().isKlaerschlammProbe()) {
        	icpEinwaageFeld.setValue(getProbe().getEinwaage());
		} else {
			icpEinwaageFeld.setEditable(false);
			icpEinwaageFeld.setEnabled(false);
		}
		Date icpDatum = getProbe().getDatumIcp();
		icpDatumFeld.setDate(icpDatum);
		
		bemerkungsArea.setText(getProbe().getBemerkung());
	}
	
	/** Initialisiert die Spalten der Analysepositionen-Tabelle */
	private void initColumns() {
		// Parameter
		TableColumn parameterColumn = parameterTabelle.getColumnModel().getColumn(0);
		parameterColumn.setPreferredWidth(150);
		
		AtlParameter[] parameter = AtlParameter.getRelevanteParameter();
		
		parameterBox = new JComboBox(parameter);
		parameterBox.setEditable(false);
		// Funktioniert leider beides nicht, geht evtl. 
		// mit ComboBoxCelleditor und Java 1.5
		//parameterBox.setKeySelectionManager(new MyKeySelectionManager());
		//AutoCompletion.enable(parameterBox);
		parameterBox.setBorder(BorderFactory.createEmptyBorder());
		parameterBox.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				parameterBox.showPopup();
			}
		});
		
		parameterColumn.setCellEditor(new DefaultCellEditor(parameterBox));
		parameterColumn.setCellRenderer(new ComboBoxRenderer());
		
		// Messwert... (alle Doubles)
		parameterTabelle.setDefaultRenderer(Double.class, new DoubleRenderer());
		parameterTabelle.setDefaultRenderer(KommaDouble.class, new DoubleRenderer());
		
		// Einheit
		TableColumn einheitenColumn = parameterTabelle.getColumnModel().getColumn(3);
		einheitenColumn.setPreferredWidth(100);
		
		AtlEinheiten[] einheiten = AtlEinheiten.getEinheiten();
		
		einheitenBox = new JComboBox(einheiten);
		einheitenBox.setEditable(false);
		einheitenBox.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				einheitenBox.showPopup();
			}
		});
		einheitenBox.setBorder(BorderFactory.createEmptyBorder());
		
		einheitenColumn.setCellEditor(new DefaultCellEditor(einheitenBox));
		//einheitenColumn.setCellEditor(new ComboBoxCellEditor(einheitenBox));
		einheitenColumn.setCellRenderer(new ComboBoxRenderer());
		
		// Analyse von
		TableColumn analyseColumn = parameterTabelle.getColumnModel().getColumn(4);
		analyseColumn.setPreferredWidth(100);
		
		// Normwert
		TableColumn normwertColumn = parameterTabelle.getColumnModel().getColumn(6);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.TRAILING);
		normwertColumn.setCellRenderer(renderer);
	}
	
	protected boolean canSave() {
		return true;
	}
	
	protected boolean doSave() {
		// Datum der Entnahme
		Date newDate = datumFeld.getDate();
		if (newDate != null) {
			getProbe().setDatumDerEntnahme(newDate);
		}
		
		if (getProbe().isKlaerschlammProbe()) {
			// ICP-Einwaage
			if (icpEinwaageFeld.getValue() != null) {	
				Float newEinwaage = ((DoubleField)icpEinwaageFeld).getFloatValue();
				getProbe().setEinwaage(newEinwaage);
			}
		}
		
		// ICP-Datum
		Date newIcpDate = icpDatumFeld.getDate();
		if (newIcpDate != null) {
			getProbe().setDatumIcp(newIcpDate);
		}
		
		// Bemerkung
		String newBemerkung = bemerkungsArea.getText();
		if (newBemerkung != null) {
			newBemerkung = newBemerkung.trim();
		}
		
		if (!"".equals(newBemerkung)) {
			getProbe().setBemerkung(newBemerkung);
		} else {
			getProbe().setBemerkung(null);
		}
		
		// Analysepositionen
		if (parameterModel.hasChanged()) {
			Set newPositionen = new HashSet(parameterModel.getList());
			getProbe().getAtlAnalysepositionen().clear();
			getProbe().getAtlAnalysepositionen().addAll(newPositionen);
			//getProbe().setAtlAnalysepositionen(newPositionen);
			AUIKataster.debugOutput("Analysepositionen geändert: " + getProbe().getAtlAnalysepositionen(), "ProbenEditor.doSave");
		}
		
		boolean success;
		if (isNew) {
			success = AtlProbenahmen.saveProbenahme(getProbe());
		} else {
			success = AtlProbenahmen.updateProbenahme(getProbe());
		}
		
		return success;
	}
	
	public AtlProbenahmen getProbe() {
		return (AtlProbenahmen) getEditedObject();
	}
}