/*
 * Datei:
 * $Id: KlaerschlammAuswertung.java,v 1.1.2.1 2010-11-23 10:25:54 u633d Exp $
 * 
 * Erstellt am 12.05.2005 von David Klotz (u633z)
 * 
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2009/03/24 12:35:20  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.5  2008/09/01 07:03:46  u633d
 * *** empty log message ***
 *
 * Revision 1.3  2008/06/24 11:24:08  u633d
 * Version 0.3
 *
 * Revision 1.2  2008/06/12 10:21:42  u633d
 * diverse Bugfixes
 *
 * Revision 1.1  2008/06/05 11:38:33  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.14  2005/10/13 13:00:26  u633d
 * Version vom 13.10.
 *
 * Revision 1.13  2005/06/06 15:28:21  u633z
 * - Kontextmenü für Export-Tabelle hinzugefügt
 *
 * Revision 1.12  2005/06/02 15:19:14  u633z
 * - Layout-Verbesserungen am Auswertungs-Dialog
 *
 * Revision 1.11  2005/06/02 09:19:04  u633z
 * - Auswertungs-Tabelle fertig, kann gespeichert werden
 *
 * Revision 1.9  2005/05/31 15:52:18  u633z
 * - Charts und ChartDataSets nach utils.charts verschoben
 * - Neue Klasse APosDataItem um eine Analyseposition als Punkt einer
 *   TimeSeries Datenreihe zu repräsentieren
 * - Bessere Tooltips im Auswertungs-Diagramm
 *
 * Revision 1.7  2005/05/24 11:02:53  u633z
 * Fehler in der Diagramm-Legende behoben (es wurde als Kläranlage immer "Heepen" angezeigt)
 *
 * Revision 1.6  2005/05/23 10:13:56  u633z
 * - SwingWorker und besseres Feedback (Statusmeldungen) eingebaut
 *
 * Revision 1.5  2005/05/19 11:30:21  u633z
 * - Filter nach "Analyse von" ermöglicht
 * - Restliche Funktionalität verbessert
 *
 * Revision 1.4  2005/05/18 15:31:59  u633z
 * Diagramm-Erzeugung verbessert, Funktionalität der Auswertung hinzugefügt
 *
 * Revision 1.1  2005/05/13 11:27:05  u633z
 * Neues Modul: KS-Auswertung
 *
 */
package de.bielefeld.umweltamt.aui.module;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlAnalyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlEinheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlKlaeranlagen;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlParameter;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbeart;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.SearchBox;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.charts.APosDataItem;
import de.bielefeld.umweltamt.aui.utils.charts.ChartDataSets;
import de.bielefeld.umweltamt.aui.utils.charts.Charts;

/**
 * Ein Modul um Analysedaten der Klärschlämme auszuwerten.
 * @author David Klotz
 */
public class KlaerschlammAuswertung extends AbstractModul {
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	
	/**
	 * Ein Dialog um die Auswertung der Klärschlamm-Parameter zu betrachten.
	 * @author David Klotz
	 */
	private class AuswertungsDialog extends JDialog {
		/**
		 * Ein Listener für die Events des Dialogs.
		 * @author David Klotz
		 */
		private class DialogListener extends WindowAdapter implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == abbrechenButton) {
					doAbbrechen();
				} else if (e.getSource() == speichernButton) {
					doSpeichern();
				}
			}
			
			public void windowClosing(WindowEvent e) {
				// Wenn der Dialog geschlossen wird, wird das Bearbeiten abgebrochen 
				doAbbrechen();
			}
		}
		/**
		 * Ein Tablemodel für die 
		 * @author David Klotz
		 */
		private class ExportTableModel extends AbstractTableModel {
			private TimeSeriesCollection col1, col2;
			private List dateList;
			
			public ExportTableModel(TimeSeriesCollection col1, TimeSeriesCollection col2) {
				this.col1 = col1;
				this.col2 = col2;
				dateList = new ArrayList();
				
				initializeData();
			}
			
			private void initializeData() {
				TimeSeries series;
				APosDataItem item;
				//int count = 0;
				for (int i = 0; i < col1.getSeriesCount(); i++) {
					series = col1.getSeries(i);
					for (int j = 0; j < series.getItemCount(); j++) {
						item = (APosDataItem) series.getDataItem(j);
						//count++;
						if (!dateList.contains(item.getMinute())) {
							dateList.add(item.getMinute());
						}
					}
				}
				
				if (col2 != null) {
					for (int i = 0; i < col2.getSeriesCount(); i++) {
						series = col2.getSeries(i);
						for (int j = 0; j < series.getItemCount(); j++) {
							item = (APosDataItem) series.getDataItem(j);
							//count++;
							if (!dateList.contains(item.getMinute())) {
								dateList.add(item.getMinute());
							}
						}
					}
				}
				
				Collections.sort(dateList);
			}
			
			public int getColumnCount() {
				return col1.getSeriesCount() + ((col2 != null) ? col2.getSeriesCount() : 0) + 1;//2;
			}
			
			public int getRowCount() {
				return dateList.size();// + 1;
			}
			
			public Object getValueAt(int rowIndex, int columnIndex) {
				String tmp = "!OOB!";
				
				NumberFormat kommaFormat = NumberFormat.getNumberInstance();
				kommaFormat.setGroupingUsed(false);
				kommaFormat.setMinimumFractionDigits(1);
				
				int seriesIndex = columnIndex - 1;
				int series2Index = seriesIndex - col1.getSeriesCount();
				int itemIndex = rowIndex;// - 1;
				
				Minute min = (Minute) dateList.get(itemIndex);
				/*if (columnIndex == 0) {
					APosDataItem item;
					String probe = null;
					for (int i = 0; i < col1.getSeriesCount(); i++) {
						item = (APosDataItem) col1.getSeries(i).getDataItem(min);
						if (item != null) {
							probe = item.getAnalysePosition().getAtlProbenahmen().getKennummer();
							break;
						}
					}
					
					if (probe == null && col2 != null) {
						for (int i = 0; i < col2.getSeriesCount(); i++) {
							item = (APosDataItem) col2.getSeries(i).getDataItem(min);
							if (item != null) {
								probe = item.getAnalysePosition().getAtlProbenahmen().getKennummer();
								break;
							}
						}
					}
					tmp = probe;
				} else*/ if (columnIndex == 0) {
					Date date = new Date(min.getFirstMillisecond());
					tmp = AuikUtils.getStringFromDate(date);
				} else {
					APosDataItem item = null;
					if (seriesIndex < col1.getSeriesCount()) {
						item = (APosDataItem) col1.getSeries(seriesIndex).getDataItem(min);
					} else if (col2 != null) {
						item = (APosDataItem) col2.getSeries(series2Index).getDataItem(min);
					}
					if (item != null) {
						tmp = kommaFormat.format(item.getValue());
					} else {
						tmp = "";
					}
				}
				
				return tmp;
			}
			
			public Class getColumnClass(int columnIndex) {
				return String.class;
			}
			
			public String getColumnName(int column) {
				String tmp = "!OOB!";
				
				int seriesIndex = column - 1;
				int series2Index = seriesIndex - col1.getSeriesCount();
				
				/*if (column == 0) {
					tmp = "Probe";
				} else*/ if (column == 0) {
					tmp = "Datum";
				} else {
					if (seriesIndex < col1.getSeriesCount()) {
						tmp = col1.getSeriesName(seriesIndex) + ", " + col1.getSeries(seriesIndex).getRangeDescription();
					} else if (col2 != null) {
						tmp = col2.getSeriesName(series2Index) + ", " + col2.getSeries(series2Index).getRangeDescription();
					}
				}
				
				return tmp;
			}
		}
		
		private JButton speichernButton;
		private JButton abbrechenButton;
		
		private JTable exportTable;
		private JPopupMenu tabellenMenu;
		
		private JTabbedPane tabbedPane;
		private ChartPanel chartPanel;
		
		private HauptFrame owner;
		private DialogListener listener;
		private String title;
		
		private TimeSeriesCollection leftDataset;
		private TimeSeriesCollection rightDataset;
		
		public AuswertungsDialog(String title, TimeSeriesCollection leftDataset, TimeSeriesCollection rightDataset, HauptFrame owner) {
			super(owner, title + "-Auswertung", true);
			this.owner = owner;
			this.title = title;
			
			this.leftDataset = leftDataset;
			this.rightDataset = rightDataset;
			
			listener = new DialogListener();
			
			this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(listener);
			
			speichernButton = new JButton("Speichern");
			speichernButton.addActionListener(listener);
			abbrechenButton = new JButton("Schließen");
			abbrechenButton.addActionListener(listener);
			
			JPanel tmp = new JPanel(new BorderLayout(0,7));
			
			tmp.add(initializeContent(), BorderLayout.CENTER);
			JPanel buttonBar = ButtonBarFactory.buildOKCancelBar(speichernButton, abbrechenButton);
			tmp.add(buttonBar, BorderLayout.SOUTH);
			tmp.setBorder(Borders.TABBED_DIALOG_BORDER);
			
			this.setContentPane(tmp);
			this.pack();
			this.setLocationRelativeTo(owner);
		}
		
		private JComponent initializeContent() {
			tabbedPane = new JTabbedPane();
			
			tabbedPane.addTab("Diagramm", createDiagrammPanel());
			tabbedPane.addTab("Tabelle", createTabellenPanel());
			
			return tabbedPane;
		}
		
		private JPanel createDiagrammPanel() {
			JFreeChart chart;
			if (rightDataset == null) {
				chart = Charts.createDefaultTimeSeriesChart(title, leftDataset);
			} else {
				chart = Charts.createDefaultTimeSeriesChart(title, leftDataset, rightDataset);
			}
			
			chartPanel = new ChartPanel(chart, false);
			chartPanel.setBorder(Borders.DIALOG_BORDER);
			
			return chartPanel;
		}
		
		private JComponent createTabellenPanel() {
			exportTable = new JTable(new ExportTableModel(leftDataset, rightDataset));
			exportTable.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			exportTable.setColumnSelectionAllowed(true);
			exportTable.setRowSelectionAllowed(true);
			exportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			exportTable.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					showTabellenPopup(e);
				}
				
				public void mouseReleased(MouseEvent e) {
					showTabellenPopup(e);
				}
			});
			
			DefaultTableCellRenderer zentrierterRenderer = new DefaultTableCellRenderer();
			zentrierterRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			
			DefaultTableCellRenderer rechtsBuendigRenderer = new DefaultTableCellRenderer();
			rechtsBuendigRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
			
			TableColumn column = null;
			for (int i = 0; i < exportTable.getColumnCount(); i++) {
				column = exportTable.getColumnModel().getColumn(i);
				if (i == 0 ) {//|| i == 1) {
					column.setCellRenderer(zentrierterRenderer);
					column.setPreferredWidth(75);
				} else {
					column.setCellRenderer(rechtsBuendigRenderer);
					column.setPreferredWidth(90);
				}
			}
			
			JScrollPane tabellenScroller = new JScrollPane(exportTable,
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			tabellenScroller.setBorder(Borders.DIALOG_BORDER);
			/*FormLayout layout = new FormLayout(
					"pref:grow", 
					"pref:grow"
			);
			
			PanelBuilder builder = new PanelBuilder(layout);
			builder.setDefaultDialogBorder();
			
			CellConstraints cc = new CellConstraints();
			
			builder.add(tabellenScroller,	cc.xy( 1, 1));*/
			
			//return builder.getPanel();
			return tabellenScroller;
		}
		
		public void saveTabelle() {
			File exportDatei = owner.saveFile(new String[]{"csv"});
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
					boolean answer = owner.showQuestion( 
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
					if (AuikUtils.exportTableDataToCVS(exportTable, exportDatei)) {
						AUIKataster.debugOutput("Speichern erfolgreich!");	
					} else {
						AUIKataster.debugOutput("Fehler beim Speichern!");
						owner.showErrorMessage("Beim Speichern der Datei '"+exportDatei+"' trat ein Fehler auf!");
					}
				}
			}
		}
		
		private void showTabellenPopup(MouseEvent e) {
			if (tabellenMenu == null) {
				tabellenMenu = new JPopupMenu("Tabelle");
				JMenuItem speichernItem = new JMenuItem(new AbstractAction("Speichern") {
					public void actionPerformed(ActionEvent e) {
						saveTabelle();
					}
				});
				tabellenMenu.add(speichernItem);
			}
			
			if (e.isPopupTrigger()) {
				Point origin = e.getPoint();
				int row = exportTable.rowAtPoint(origin);
				int col = exportTable.columnAtPoint(origin);
				
				if (row != -1) {
					exportTable.setRowSelectionInterval(row, row);
					exportTable.setColumnSelectionInterval(col, col);
					tabellenMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}
		
		public void doAbbrechen() {
			this.dispose();
		}
		
		public void doSpeichern() {
			if (tabbedPane.getSelectedIndex() == 0) {
				try {
					chartPanel.doSaveAs();
				} catch (IOException e) {
					owner.showErrorMessage("Konnte Datei nicht speichern!");
				}
			} else if (tabbedPane.getSelectedIndex() == 1) {
				saveTabelle();
			}
		}
	}
	
	private JCheckBox heepenCheck;
	private JCheckBox brakeCheck;
	private JCheckBox sennestCheck;
	private JCheckBox olutterCheck;
	private JCheckBox verlCheck;
	private JComboBox artBox;
	private JDateChooser vonDateChooser;
	private JDateChooser bisDateChooser;
	
	private JPanel parameterPanel;
	private JList rightList;
	private JList leftList;
	private JButton submitButton;
	
	private JComboBox parameterBox;
	private JButton leftDeleteButton;
	private JButton rightDeleteButton;
	private JComboBox leftEinheitenBox;
	private JComboBox rightEinheitenBox;
	private JTextField leftAnalyseFeld;
	private JTextField rightAnalyseFeld;
	
	private ActionListener rlButtonListener;
	private AtlEinheiten[] einheiten;
	
	private TimeSeriesCollection dataSet1;
	private TimeSeriesCollection dataSet2;

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Auswertung Klärschlamm";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	public String getIdentifier() {
		return "m_schlaemme_auswertung";
	}
	
	public Icon getIcon() {
		return super.getIcon("log.png");
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	public String getCategory() {
		return "Klärschlamm";
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	public JPanel getPanel() {
		if (panel == null) {
			einheiten = AtlEinheiten.getEinheiten();
			
			String spaltenTeil = "pref, 5dlu, pref:g";
			String zeileLuecke = "pref, 3dlu";
			
			FormLayout gesamtLayout = new FormLayout(
					"pref, 5dlu, "+ spaltenTeil +", 10dlu:g(0.2), "+ spaltenTeil,
					zeileLuecke +", "+		//1,2	Kläranlagen----  Zeitraum----
					zeileLuecke +", "+		//3,4
					zeileLuecke +", "+		//5,6
					zeileLuecke +", "+		//7,8	Parameter -------------------
					"pref, 10dlu, "+		//9,10
							"pref");		//11
			
			gesamtLayout.setColumnGroups(new int[][]{{1,3,5,9}});
			gesamtLayout.setRowGroups(new int[][]{{3,5}});
			
			PanelBuilder builder = new PanelBuilder(gesamtLayout);
			builder.setDefaultDialogBorder();
			CellConstraints cc = new CellConstraints();
			CellConstraints cc2 = (CellConstraints) cc.clone();
			
			builder.addSeparator("Kläranlagen / Art",	cc.xyw( 1, 1, 5));
			builder.add(getHeepenCheck(),		cc.xy(  1, 3));
			builder.add(getBrakeCheck(),		cc.xy(  3, 3));
			builder.add(getSennestCheck(),		cc.xy(  5, 3));
			builder.add(getOlutterCheck(),		cc.xy(  1, 5));
			builder.add(getVerlCheck(),			cc.xy(  3, 5));
			builder.add(getArtBox(),			cc.xy(  5, 5, "l,d"));
			
			builder.addSeparator("Zeitraum",	cc.xyw( 7, 1, 3));
			builder.add(new JLabel("Von:"),		cc.xy(  7, 3, "r,d"), 
					getVonDateChooser(),		cc2.xy( 9, 3, "l,d"));
			builder.add(new JLabel("Bis:"),		cc.xy(  7, 5, "r,d"),
					getBisDateChooser(),		cc2.xy( 9, 5, "l,d"));
			
			builder.addSeparator("Parameter",	cc.xyw( 1, 7, 9));
			builder.add(getParameterPanel(),	cc.xyw( 1, 9, 9, "fill, fill"));
			JPanel buttonPanel = ButtonBarFactory.buildOKBar(getSubmitButton());
			builder.add(buttonPanel, 			cc.xyw( 1,11, 9, "fill, fill"));
			
			panel = builder.getPanel();
		}
		return panel;
	}
	
	public void showResultOneAxis(final String axis) {
		SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton()) {
			protected void doNonUILogic() throws RuntimeException {
				dataSet1 = createDataset(axis);
			}

			protected void doUIUpdateLogic() throws RuntimeException {
				if (dataSet1.getSeriesCount() > 0) {
					frame.clearStatus();
					AuswertungsDialog dialog = new AuswertungsDialog(getArtBox().getSelectedItem().toString(), dataSet1, null, frame);
					dialog.setVisible(true);
				} else {
					frame.changeStatus("Keine Parameter ausgewählt!");
				}
			}
		};
		
		frame.changeStatus("Bereite Auswertung vor...");
		worker.start();
	}
	
	public void showResultDualAxis() {
		SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton()) {
			private int seriesCount = 0;
			
			protected void doNonUILogic() throws RuntimeException {
				dataSet1 = createDataset(LEFT);
				dataSet2 = createDataset(RIGHT);
				
				seriesCount = dataSet1.getSeriesCount() + dataSet2.getSeriesCount();
			}

			protected void doUIUpdateLogic() throws RuntimeException {
				if (seriesCount > 0) {
					frame.clearStatus();
					AuswertungsDialog dialog = new AuswertungsDialog(getArtBox().getSelectedItem().toString(), dataSet1, dataSet2, frame);
					dialog.setVisible(true);
				} else {
					frame.changeStatus("Keine Parameter ausgewählt!");
				}
			}
		};
		
		frame.changeStatus("Bereite Auswertung vor...");
		worker.start();
	}
	
	private TimeSeriesCollection createDataset(String axis) {
		TimeSeriesCollection col = new TimeSeriesCollection();
		
		int parameterAnzahl;
		AtlEinheiten einheit;
		JList paramList;
		String analyseVon;
		if (axis.equals(LEFT)) {
			parameterAnzahl = getLeftList().getModel().getSize();
			einheit = (AtlEinheiten) getLeftEinheitenBox().getSelectedItem();
			paramList = getLeftList();
			analyseVon = getLeftAnalyseFeld().getText().toLowerCase().trim();
		} else {
			parameterAnzahl = getRightList().getModel().getSize();
			einheit = (AtlEinheiten) getRightEinheitenBox().getSelectedItem();
			paramList = getRightList();
			analyseVon = getRightAnalyseFeld().getText().toLowerCase().trim();
		}
		
		AtlProbeart art = (AtlProbeart) getArtBox().getSelectedItem();
		Date vonDate = getVonDateChooser().getDate();
		Date bisDate = getBisDateChooser().getDate();
		
		if (getHeepenCheck().isSelected()) {
			createSeries(art, AtlKlaeranlagen.getKlaeranlage(AtlKlaeranlagen.HEEPEN), einheit, paramList, analyseVon, vonDate, bisDate, col);
		}
		if (getBrakeCheck().isSelected()) {
			createSeries(art, AtlKlaeranlagen.getKlaeranlage(AtlKlaeranlagen.BRAKE), einheit, paramList, analyseVon, vonDate, bisDate, col);
		}
		if (getSennestCheck().isSelected()) {
			createSeries(art, AtlKlaeranlagen.getKlaeranlage(AtlKlaeranlagen.SENNESTADT), einheit, paramList, analyseVon, vonDate, bisDate, col);
		}
		if (getOlutterCheck().isSelected()) {
			createSeries(art, AtlKlaeranlagen.getKlaeranlage(AtlKlaeranlagen.OBERE_LUTTER), einheit, paramList, analyseVon, vonDate, bisDate, col);
		}
		if (getVerlCheck().isSelected()) {
			createSeries(art, AtlKlaeranlagen.getKlaeranlage(AtlKlaeranlagen.VERL_SENDE), einheit, paramList, analyseVon, vonDate, bisDate, col);
		}
		
		return col;
	}
	
	private void createSeries(AtlProbeart art, AtlKlaeranlagen ka, 
			AtlEinheiten einheit, JList paramList, String analyseVon,
			Date vonDate, Date bisDate,
			TimeSeriesCollection col) {
		
		AtlProbepkt pkt = AtlProbepkt.getKlaerschlammProbepunkt(art, ka);
		
		if (pkt != null) {

			for (int i = 0; i < paramList.getModel().getSize(); i++) {
				AtlParameter p = (AtlParameter) paramList.getModel()
						.getElementAt(i);

				frame.changeStatus("Erzeuge Datenreihe für " + p + ", " + ka);

				List list = AtlAnalyseposition.getAnalysepositionen(p, einheit,
						pkt, vonDate, bisDate, analyseVon);
				TimeSeries series = ChartDataSets
						.createAnalysePositionenSeries(list, p + ", " + ka,
								einheit.toString());
				col.addSeries(series);
			}
		}
	}
	
	private JDateChooser getVonDateChooser() {
		if (vonDateChooser == null) {
			vonDateChooser = new JDateChooser(AuikUtils.DATUMSFORMAT, false);
		}
		
		return vonDateChooser;
	}
	
	private JDateChooser getBisDateChooser() {
		if (bisDateChooser == null) {
			bisDateChooser = new JDateChooser(AuikUtils.DATUMSFORMAT, false);
		}
		
		return bisDateChooser;
	}
	
	private JCheckBox getBrakeCheck() {
		if (brakeCheck == null) {
			brakeCheck = new JCheckBox("Brake");
		}
		return brakeCheck;
	}
	private JCheckBox getHeepenCheck() {
		if (heepenCheck == null) {
			heepenCheck = new JCheckBox("Heepen", true);
		}
		return heepenCheck;
	}
	private JCheckBox getOlutterCheck() {
		if (olutterCheck == null) {
			olutterCheck = new JCheckBox("Obere Lutter");
		}
		return olutterCheck;
	}
	private JCheckBox getSennestCheck() {
		if (sennestCheck == null) {
			sennestCheck = new JCheckBox("Sennestadt");
		}
		return sennestCheck;
	}
	private JCheckBox getVerlCheck() {
		if (verlCheck == null) {
			verlCheck = new JCheckBox("Verl-Sende");
		}
		return verlCheck;
	}
	
	private JComboBox getArtBox() {
		if (artBox == null) {
			AtlProbeart[] arten = new AtlProbeart[]{
					AtlProbeart.getProbeart(AtlProbeart.ROHSCHLAMM), 
					AtlProbeart.getProbeart(AtlProbeart.FAULSCHLAMM), 
					AtlProbeart.getProbeart(AtlProbeart.ANLIEFERUNG), 
					AtlProbeart.getProbeart(AtlProbeart.ZULAUF)
					};
			artBox = new JComboBox(arten);
			artBox.setPrototypeDisplayValue("Faulschlamm   abc");
		}
		
		return artBox;
	}
	
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton("Abschicken");
			
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getRightList().getModel().getSize() == 0) {
						showResultOneAxis(LEFT);
					} else if (getLeftList().getModel().getSize() == 0) {
						showResultOneAxis(RIGHT);
					} else {
						showResultDualAxis();
					}
				}
			});
		}
		
		return submitButton;
	}

	private JPanel getParameterPanel() {
		if (parameterPanel == null) {
			
			String zeileLuecke = "pref, 3dlu";
			
			FormLayout parameterLayout = new FormLayout(
					/* Liste                 <            Params              >                 Liste */
					/*  1      2             3      4       5          6      7        8          9 */
					 "l:p:g, 10dlu:g(0.3), r:16px, 5dlu, c:70dlu:g(0.1), 5dlu, l:16px, 10dlu:g(0.3), r:p:g",
					 
					zeileLuecke +", " +		//1,2	Linke Achse		    	 Rechte Achse
					zeileLuecke +", " +		//3,4	|			|	Par1	|			|
					zeileLuecke +", " +		//5,6	|			|	Par2	|			|
					zeileLuecke +", " +		//7,8	|			|	Par3	|			|
					zeileLuecke +", " +		//9,10	|			|	Par4	|			|
					zeileLuecke +", " +		//11,12	|			|	Par5	|			|
					zeileLuecke +", " +		//13,14	|			|	Par6	|			|
					zeileLuecke +", " +		//15,16	|			|	Par7	|			|
					zeileLuecke +", " +		//17,18	|			|	[Par]	|			|
					zeileLuecke +", " +		//19,20	( Löschen )				( Löschen )
					zeileLuecke +", " +		//21,22	[ Einheit ]				[ Einheit ]
					"pref");				//23	[ AnalyVo ]				[ AnalyVo ]
			
			parameterLayout.setColumnGroups(new int[][]{{1,9}, {3,7}});
			parameterLayout.setRowGroups(new int[][]{{3,5}});
			
			PanelBuilder builder = new PanelBuilder(parameterLayout);
			CellConstraints cc = new CellConstraints();
			
			builder.add(new JLabel("Erste Y-Achse"),	cc.xy  ( 1, 1));
			builder.add(new JLabel("Zweite Y-Achse"),	cc.xy  ( 9, 1));
			
			JList lList = getLeftList();
			JList rList = getRightList();
			builder.add(new JScrollPane(lList),		cc.xywh( 1, 3, 1, 15, "fill, fill"));
			builder.add(new JScrollPane(rList),		cc.xywh( 9, 3, 1, 15, "fill, fill"));
			
			builder.add(createRLButton(true, AtlParameter.CADMIUM_ID),	cc.xy( 3, 3));
			builder.add(new JLabel("Cadmium (Cd)", JLabel.CENTER), 		cc.xy( 5, 3, "f,d"));
			builder.add(createRLButton(false, AtlParameter.CADMIUM_ID),	cc.xy( 7, 3));
			
			builder.add(createRLButton(true, AtlParameter.CHROM_ID),	cc.xy( 3, 5));
			builder.add(new JLabel("Chrom (Cr)", JLabel.CENTER), 		cc.xy( 5, 5, "f,d"));
			builder.add(createRLButton(false, AtlParameter.CHROM_ID),	cc.xy( 7, 5));
			
			builder.add(createRLButton(true, AtlParameter.KUPFER_ID),	cc.xy( 3, 7));
			builder.add(new JLabel("Kupfer (Cu)", JLabel.CENTER), 	cc.xy( 5, 7, "f,d"));
			builder.add(createRLButton(false, AtlParameter.KUPFER_ID),	cc.xy( 7, 7));
			
			builder.add(createRLButton(true, AtlParameter.QUECKSILBER_ID),	cc.xy( 3, 9));
			builder.add(new JLabel("Quecksilber (Hg)", JLabel.CENTER), 	cc.xy( 5, 9, "f,d"));
			builder.add(createRLButton(false, AtlParameter.QUECKSILBER_ID),	cc.xy( 7, 9));
			
			builder.add(createRLButton(true, AtlParameter.NICKEL_ID),	cc.xy( 3, 11));
			builder.add(new JLabel("Nickel (Ni)", JLabel.CENTER), 	cc.xy( 5, 11, "f,d"));
			builder.add(createRLButton(false, AtlParameter.NICKEL_ID),	cc.xy( 7, 11));
			
			builder.add(createRLButton(true, AtlParameter.BLEI_ID),	cc.xy( 3,13));
			builder.add(new JLabel("Blei (Pb)", JLabel.CENTER), 	cc.xy( 5,13, "f,d"));
			builder.add(createRLButton(false, AtlParameter.BLEI_ID),	cc.xy( 7,13));
			
			builder.add(createRLButton(true, AtlParameter.ZINK_ID),	cc.xy( 3,15));
			builder.add(new JLabel("Zink (Zn)", JLabel.CENTER), 	cc.xy( 5,15, "f,d"));
			builder.add(createRLButton(false, AtlParameter.ZINK_ID),	cc.xy( 7,15));
			
			builder.add(createRLButton(true, "box"),	cc.xy( 3,17));
			builder.add(getParameterBox(), 				cc.xy( 5,17, "f,d"));
			builder.add(createRLButton(false, "box"),	cc.xy( 7,17));
			
			builder.add(getLeftDeleteButton(),		cc.xy( 1, 19, "c,d"));
			builder.add(getRightDeleteButton(),		cc.xy( 9, 19, "c,d"));
			
			builder.add(getLeftEinheitenBox(),		cc.xy( 1, 21, "c,d"));
			builder.add(new JLabel("<  Einheit  >", JLabel.CENTER),	cc.xy( 5, 21, "f,d"));
			builder.add(getRightEinheitenBox(),		cc.xy( 9, 21, "c,d"));
			
			builder.add(getLeftAnalyseFeld(),		cc.xy( 1, 23, "c,d"));
			builder.add(new JLabel("<  Analyse von  >", JLabel.CENTER),	cc.xy( 5, 23, "f,d"));
			builder.add(getRightAnalyseFeld(),		cc.xy( 9, 23, "c,d"));
			
			parameterPanel = builder.getPanel();
		}
		
		return parameterPanel;
	}
	
	private JList getRightList() {
		if (rightList == null) {
			DefaultListModel listModel = new DefaultListModel();
			rightList = new JList(listModel);
			rightList.setPrototypeCellValue("Abcdefghij (Ab)");
			
			rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		
		return rightList;
	}
	
	private JList getLeftList() {
		if (leftList == null) {
			DefaultListModel listModel = new DefaultListModel();
			leftList = new JList(listModel);
			leftList.setPrototypeCellValue("Abcdefghij (Ab)");
			
			leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		
		return leftList;
	}
	
	private JComboBox getParameterBox() {
		if (parameterBox == null) {
			parameterBox = new SearchBox(AtlParameter.getRelevanteParameter());
		}
		
		return parameterBox;
	}
	
	private JButton getLeftDeleteButton() {
		if (leftDeleteButton == null) {
			leftDeleteButton = new JButton("Löschen");
			leftDeleteButton.setEnabled(false);
			
			leftDeleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = getLeftList().getSelectedIndex();
					DefaultListModel leftModel = ((DefaultListModel)getLeftList().getModel());
					
					if (index != -1) {
						leftModel.remove(index);
						
						int size = leftModel.getSize();
						
						if (size == 0) {
							leftDeleteButton.setEnabled(false);
						} else {
							if (index == size) {
								index--;
							}
							
							getLeftList().setSelectedIndex(index);
							getLeftList().ensureIndexIsVisible(index);
						}
					}
				}
			});
		}
		
		return leftDeleteButton;
	}
	
	private JButton getRightDeleteButton() {
		if (rightDeleteButton == null) {
			rightDeleteButton = new JButton("Löschen");
			rightDeleteButton.setEnabled(false);
			
			rightDeleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = getRightList().getSelectedIndex();
					DefaultListModel rightModel = ((DefaultListModel)getRightList().getModel());
					
					if (index != -1) {
						rightModel.remove(index);
						
						int size = rightModel.getSize();
						
						if (size == 0) {
							rightDeleteButton.setEnabled(false);
						} else {
							if (index == size) {
								index--;
							}
							
							getRightList().setSelectedIndex(index);
							getRightList().ensureIndexIsVisible(index);
						}
					}
				}
			});
		}
		
		return rightDeleteButton;
	}
	
	private JComboBox getLeftEinheitenBox() {
		if (leftEinheitenBox == null) {
			leftEinheitenBox = new SearchBox(einheiten);
			leftEinheitenBox.setSelectedItem(AtlEinheiten.getEinheit(AtlEinheiten.MG_KG_ID));
		}
		
		return leftEinheitenBox;
	}
	
	private JComboBox getRightEinheitenBox() {
		if (rightEinheitenBox == null) {
			rightEinheitenBox = new SearchBox(einheiten);
			rightEinheitenBox.setSelectedItem(AtlEinheiten.getEinheit(AtlEinheiten.MG_KG_ID));
		}
		
		return rightEinheitenBox;
	}
	
	private JTextField getLeftAnalyseFeld() {
		if (leftAnalyseFeld == null) {
			leftAnalyseFeld = new JTextField("");
			leftAnalyseFeld.setPreferredSize(getLeftEinheitenBox().getPreferredSize());
		}
		
		return leftAnalyseFeld;
	}
	
	private JTextField getRightAnalyseFeld() {
		if (rightAnalyseFeld == null) {
			rightAnalyseFeld = new JTextField("");
			rightAnalyseFeld.setPreferredSize(getRightEinheitenBox().getPreferredSize());
		}
		
		return rightAnalyseFeld;
	}
	
	private JButton createRLButton(boolean left, String paramId) {
		Icon icon;
		Icon rIcon;
		String direction;
		if (left) {
			icon = AuikUtils.getIcon(16, "left.png");
			rIcon = AuikUtils.getIcon(16, "left_a.png");
			direction = "left";
		} else {
			icon = AuikUtils.getIcon(16, "right.png");
			rIcon = AuikUtils.getIcon(16, "right_a.png");
			direction = "right";
		}
		
		JButton button = new JButton(icon);
		button.setRolloverIcon(rIcon);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		
		button.setActionCommand(direction + "_" + paramId);
		button.addActionListener(getRLButtonListener());
		
		return button;
	}
	
	private ActionListener getRLButtonListener() {
		if (rlButtonListener == null) {
			rlButtonListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String direction = e.getActionCommand().replaceFirst("_.*", "");
					String paramId = e.getActionCommand().replaceFirst(".*_", "");
					AtlParameter param = null;
					
					if (!paramId.equals("")) {
						if (paramId.equals("box")) {
							param = (AtlParameter) getParameterBox().getSelectedItem();
						} else {
							param = AtlParameter.getParameter(paramId);
						}
					}
					
					if (param != null) {
						DefaultListModel leftModel = (DefaultListModel) getLeftList().getModel();
						DefaultListModel rightModel = (DefaultListModel) getRightList().getModel();
						
						if (direction.equals(LEFT)) {
							if (!leftModel.contains(param)) {
								if (rightModel.contains(param)) {
									rightModel.removeElement(param);
									if (rightModel.getSize() == 0) {
										getRightDeleteButton().setEnabled(false);
									}
								}
								leftModel.addElement(param);
								getLeftDeleteButton().setEnabled(true);
							}
						} else if (direction.equals(RIGHT)) {
							if (!rightModel.contains(param)) {
								if (leftModel.contains(param)) {
									leftModel.removeElement(param);
									if (leftModel.getSize() == 0) {
										getLeftDeleteButton().setEnabled(false);
									}
								}
								rightModel.addElement(param);
								getRightDeleteButton().setEnabled(true);
							}
						}
					}
				}
			};
		}
		
		return rlButtonListener;
	}
}
