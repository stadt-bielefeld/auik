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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
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

import com.toedter.calendar.JDateChooser;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.Analyseposition;
import de.bielefeld.umweltamt.aui.mappings.atl.Einheiten;
import de.bielefeld.umweltamt.aui.mappings.atl.Klaeranlage;
import de.bielefeld.umweltamt.aui.mappings.atl.Messstelle;
import de.bielefeld.umweltamt.aui.mappings.atl.Parameter;
import de.bielefeld.umweltamt.aui.mappings.atl.Probeart;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.DateUtils;
import de.bielefeld.umweltamt.aui.utils.FormHelper;
import de.bielefeld.umweltamt.aui.utils.SearchBox;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.charts.APosDataItem;
import de.bielefeld.umweltamt.aui.utils.charts.ChartDataSets;
import de.bielefeld.umweltamt.aui.utils.charts.Charts;

/**
 * Ein Modul um Analysedaten der Klärschlämme auszuwerten.
 *
 * @author David Klotz
 */
public class KlaerschlammAuswertung extends AbstractModul
{
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private static final String LEFT = "left";
	private static final String RIGHT = "right";

	/**
	 * Ein Dialog um die Auswertung der Klärschlamm-Parameter zu betrachten.
	 *
	 * @author David Klotz
	 */
	private class AuswertungsDialog extends JDialog
	{
		private static final long serialVersionUID = -4324618249094497981L;

		/**
		 * Ein Listener für die Events des Dialogs.
		 *
		 * @author David Klotz
		 */
		private class DialogListener extends WindowAdapter implements
				ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == AuswertungsDialog.this.abbrechenButton)
				{
					doAbbrechen();
				}
				else if (e.getSource() == AuswertungsDialog.this.speichernButton)
				{
					doSpeichern();
				}
			}

			@Override
			public void windowClosing(WindowEvent e)
			{
				// Wenn der Dialog geschlossen wird, wird das Bearbeiten
				// abgebrochen
				doAbbrechen();
			}
		}

		/**
		 * Ein Tablemodel für die
		 *
		 * @author David Klotz
		 */
		private class ExportTableModel extends AbstractTableModel
		{
			private static final long serialVersionUID = 4165282695596298199L;
			private TimeSeriesCollection col1, col2;
			private List<Minute> dateList;

			public ExportTableModel(TimeSeriesCollection col1,
				TimeSeriesCollection col2)
			{
				this.col1 = col1;
				this.col2 = col2;
				this.dateList = new ArrayList<Minute>();

				initializeData();
			}

			private void initializeData()
			{
				TimeSeries series;
				APosDataItem item;
				// int count = 0;
				for (int i = 0; i < this.col1.getSeriesCount(); i++) {
					series = this.col1.getSeries(i);
					for (int j = 0; j < series.getItemCount(); j++) {
						item = (APosDataItem) series.getDataItem(j);

						int n = this.dateList.size();
						boolean add = true;
						if (n == 0) {
							this.dateList.add(item.getMinute());
						} else
							for (int k = 0; k < n; k++) {
								if (this.dateList.get(k).toString().equals(item
										.getMinute().toString())) {
									add = false;
								}
							}
						if(add == true && n > 0){
							this.dateList.add(item.getMinute());
						}
					}
				}

                if (this.col2 != null) {
                    for (int i = 0; i < this.col2.getSeriesCount(); i++) {
                        series = this.col2.getSeries(i);
                        for (int j = 0; j < series.getItemCount(); j++) {
                            item = (APosDataItem) series.getDataItem(j);
                            // count++;
                            int n = this.dateList.size();
    						boolean add = true;
    						if (n == 0) {
    							this.dateList.add(item.getMinute());
    						} else
    							for (int k = 0; k < n; k++) {
    								if (this.dateList.get(k).toString().equals(item
    										.getMinute().toString())) {
    									add = false;
    								}
    							}
    						if(add == true && n > 0){
    							this.dateList.add(item.getMinute());
    						}
                        }
                    }
                }

				this.dateList.sort(null);
			}

			@Override
			public int getColumnCount()
			{
				return this.col1.getSeriesCount()
						+ ((this.col2 != null) ? this.col2.getSeriesCount() : 0)
						+ 1;// 2;
			}

			@Override
			public int getRowCount()
			{
				return this.dateList.size();// + 1;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex)
			{
				String tmp = "!OOB!";

				NumberFormat kommaFormat = NumberFormat.getNumberInstance();
				kommaFormat.setGroupingUsed(false);
				kommaFormat.setMinimumFractionDigits(1);

				int seriesIndex = columnIndex - 1;
				int series2Index = seriesIndex - this.col1.getSeriesCount();
				int itemIndex = rowIndex;// - 1;

				Minute min = (Minute) this.dateList.get(itemIndex);
				/*
				 * if (columnIndex == 0) {
				 * APosDataItem item;
				 * String probe = null;
				 * for (int i = 0; i < col1.getSeriesCount(); i++) {
				 * item = (APosDataItem) col1.getSeries(i).getDataItem(min);
				 * if (item != null) {
				 * probe =
				 * item.getAnalysePosition().getAtlProbenahmen().getKennummer();
				 * break;
				 * }
				 * }
				 *
				 * if (probe == null && col2 != null) {
				 * for (int i = 0; i < col2.getSeriesCount(); i++) {
				 * item = (APosDataItem) col2.getSeries(i).getDataItem(min);
				 * if (item != null) {
				 * probe =
				 * item.getAnalysePosition().getAtlProbenahmen().getKennummer();
				 * break;
				 * }
				 * }
				 * }
				 * tmp = probe;
				 * } else
				 */if (columnIndex == 0)
				{
					Date date = new Date(min.getFirstMillisecond());
					tmp = AuikUtils.getStringFromDate(date);
				}
				else
				{
					APosDataItem item = null;
					if (seriesIndex < this.col1.getSeriesCount())
					{
						item = (APosDataItem) this.col1.getSeries(seriesIndex)
								.getDataItem(min);
					}
					else if (this.col2 != null)
					{
						item = (APosDataItem) this.col2.getSeries(series2Index)
								.getDataItem(min);
					}
					if (item != null)
					{
						tmp = kommaFormat.format(item.getValue());
					}
					else
					{
						tmp = "";
					}
				}

				return tmp;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex)
			{
				return String.class;
			}

			@Override
			public String getColumnName(int column)
			{
				String tmp = "!OOB!";

				int seriesIndex = column - 1;
				int series2Index = seriesIndex - this.col1.getSeriesCount();

				/*
				 * if (column == 0) {
				 * tmp = "Probe";
				 * } else
				 */if (column == 0)
				{
					tmp = "Datum";
				}
				else
				{
					if (seriesIndex < this.col1.getSeriesCount())
					{
						//TODO: key == seriesName?
						//tmp = this.col1.getSeriesName(seriesIndex)
						tmp = this.col1.getSeries(seriesIndex).getKey().toString()
								+ ", "
								+ this.col1.getSeries(seriesIndex)
										.getRangeDescription();
					}
					else if (this.col2 != null)
					{
						//tmp = this.col2.getSeriesName(series2Index)
						tmp = this.col2.getSeries(series2Index).getKey().toString()
								+ ", "
								+ this.col2.getSeries(series2Index)
										.getRangeDescription();
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

		public AuswertungsDialog(String title,
			TimeSeriesCollection leftDataset,
			TimeSeriesCollection rightDataset, HauptFrame owner)
		{
			super(owner, title + "-Auswertung", true);
			this.owner = owner;
			this.title = title;

			this.leftDataset = leftDataset;
			this.rightDataset = rightDataset;

			this.listener = new DialogListener();

			this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(this.listener);

			this.speichernButton = new JButton("Speichern");
			this.speichernButton.addActionListener(this.listener);
			this.abbrechenButton = new JButton("Schließen");
			this.abbrechenButton.addActionListener(this.listener);

			JPanel tmp = new JPanel(new BorderLayout(0, 7));

			tmp.add(initializeContent(), BorderLayout.CENTER);
			JComponent buttonBar = ComponentFactory.buildOKCancelBar(
					this.speichernButton, this.abbrechenButton);
			tmp.add(buttonBar, BorderLayout.SOUTH);
			tmp.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

			this.setContentPane(tmp);
			this.pack();
			this.setLocationRelativeTo(owner);
		}

		private JComponent initializeContent()
		{
			this.tabbedPane = new JTabbedPane();

			this.tabbedPane.addTab("Diagramm", createDiagrammPanel());
			this.tabbedPane.addTab("Tabelle", createTabellenPanel());

			return this.tabbedPane;
		}

		private JPanel createDiagrammPanel()
		{
			JFreeChart chart;
			if (this.rightDataset == null)
			{
				chart = Charts.createDefaultTimeSeriesChart(this.title,
															this.leftDataset);
			}
			else
			{
				chart = Charts.createDefaultTimeSeriesChart(this.title,
															this.leftDataset, this.rightDataset);
			}

			this.chartPanel = new ChartPanel(chart, false);
			FormHelper.setDialogBorder(this.chartPanel);

			return this.chartPanel;
		}

		private JComponent createTabellenPanel()
		{
			this.exportTable = new JTable(new ExportTableModel(
					this.leftDataset, this.rightDataset));
			this.exportTable.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			this.exportTable.setColumnSelectionAllowed(true);
			this.exportTable.setRowSelectionAllowed(true);
			this.exportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			this.exportTable.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent e)
				{
					showTabellenPopup(e);
				}

				@Override
				public void mouseReleased(MouseEvent e)
				{
					showTabellenPopup(e);
				}
			});

			DefaultTableCellRenderer zentrierterRenderer = new DefaultTableCellRenderer();
			zentrierterRenderer
					.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

			DefaultTableCellRenderer rechtsBuendigRenderer = new DefaultTableCellRenderer();
			rechtsBuendigRenderer
					.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

			TableColumn column = null;
			for (int i = 0; i < this.exportTable.getColumnCount(); i++)
			{
				column = this.exportTable.getColumnModel().getColumn(i);
				if (i == 0)
				{// || i == 1) {
					column.setCellRenderer(zentrierterRenderer);
					column.setPreferredWidth(75);
				}
				else
				{
					column.setCellRenderer(rechtsBuendigRenderer);
					column.setPreferredWidth(90);
				}
			}

			JScrollPane tabellenScroller = new JScrollPane(this.exportTable,
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			FormHelper.setDialogBorder(tabellenScroller);
			return tabellenScroller;
		}

		public void saveTabelle()
		{
			File exportDatei = this.owner.saveFile(new String[] { "csv" });
			if (exportDatei != null)
			{
				String ext = AuikUtils.getExtension(exportDatei);

				if (ext == null)
				{
					String newExt;
					if (exportDatei.getName().endsWith("."))
					{
						newExt = "csv";
					}
					else
					{
						newExt = ".csv";
					}
					exportDatei = new File(exportDatei.getParent(),
							exportDatei.getName() + newExt);
				}

				boolean doIt = false;
				if (exportDatei.exists())
				{
					boolean answer = GUIManager.getInstance()
							.showQuestion(
											"Soll die vorhandene Datei " + exportDatei.getName()
													+ " wirklich überschrieben werden?",
											"Datei bereits vorhanden!");
					if (answer && exportDatei.canWrite())
					{
						doIt = true;
					}
				}
				else if (exportDatei.getParentFile().canWrite())
				{
					doIt = true;
				}

				if (doIt)
				{
					log.debug("Speichere nach '" + exportDatei.getName()
							+ "' (Ext: '" + ext + "') in '"
							+ exportDatei.getParent() + "' !");
					if (AuikUtils.exportTableDataToCVS(this.exportTable,
														exportDatei))
					{
						log.debug("Speichern erfolgreich!");
					}
					else
					{
						log.debug("Fehler beim Speichern!");
						GUIManager.getInstance().showErrorMessage(
																	"Beim Speichern der Datei '" + exportDatei
																			+ "' trat ein Fehler auf!");
					}
				}
			}
		}

		private void showTabellenPopup(MouseEvent e)
		{
			if (this.tabellenMenu == null)
			{
				this.tabellenMenu = new JPopupMenu("Tabelle");
				JMenuItem speichernItem = new JMenuItem(new AbstractAction(
						"Speichern")
				{
					private static final long serialVersionUID = 3729886517838248066L;

					@Override
					public void actionPerformed(ActionEvent e)
					{
						saveTabelle();
					}
				});
				this.tabellenMenu.add(speichernItem);
			}

			if (e.isPopupTrigger())
			{
				Point origin = e.getPoint();
				int row = this.exportTable.rowAtPoint(origin);
				int col = this.exportTable.columnAtPoint(origin);

				if (row != -1)
				{
					this.exportTable.setRowSelectionInterval(row, row);
					this.exportTable.setColumnSelectionInterval(col, col);
					this.tabellenMenu
							.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}

		public void doAbbrechen()
		{
			this.dispose();
		}

		public void doSpeichern()
		{
			if (this.tabbedPane.getSelectedIndex() == 0)
			{
				try
				{
					this.chartPanel.doSaveAs();
				}
				catch (IOException e)
				{
					GUIManager.getInstance().showErrorMessage(
																"Konnte Datei nicht speichern!");
				}
			}
			else if (this.tabbedPane.getSelectedIndex() == 1)
			{
				saveTabelle();
			}
		}
	}

	private JList<Klaeranlage> klaeranlagen;
	private JComboBox<Probeart> artBox;
	private JDateChooser vonDateChooser;
	private JDateChooser bisDateChooser;

	private JPanel parameterPanel;
	private JList<Parameter> rightList;
	private JList<Parameter> leftList;
	private JButton submitButton;

	private JComboBox<Parameter> parameterBox;
	private JButton leftDeleteButton;
	private JButton rightDeleteButton;
	private JComboBox<Einheiten> leftEinheitenBox;
	private JComboBox<Einheiten> rightEinheitenBox;
	private JTextField leftAnalyseFeld;
	private JTextField rightAnalyseFeld;

	private ActionListener rlButtonListener;
	private Einheiten[] einheiten;

	private TimeSeriesCollection dataSet1;
	private TimeSeriesCollection dataSet2;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	@Override
	public String getName()
	{
		return "Auswertung Klärschlamm";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 */
	@Override
	public String getIdentifier()
	{
		return "m_schlaemme_auswertung";
	}

	@Override
	public Icon getIcon()
	{
		return super.getIcon("log.png");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.bielefeld.umweltamt.aui.Modul#getCategory()
	 */
	@Override
	public String getCategory()
	{
		return "Klärschlamm";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.bielefeld.umweltamt.aui.Modul#getPanel()
	 */
	@Override
	public JPanel getPanel()
	{
		if (this.panel == null)
		{
			this.einheiten = DatabaseQuery.getEinheiten();

			JPanel mainPanel = FormHelper.createDialogPanel();

			// ── Kläranlagen / Art (links) ─────────────────────
			FormHelper.addSeparator(mainPanel, 0, 0, 3, "Kläranlagen / Art");
			JScrollPane klaeranlagenScroller = new JScrollPane(getKlaeranlagen());
			klaeranlagenScroller.setPreferredSize(new Dimension(250, 90));
			FormHelper.addSpan(mainPanel, klaeranlagenScroller, 0, 1, 2, 3);
			FormHelper.add(mainPanel, getArtBox(), 2, 3, 1);

			// ── Zeitraum (rechts) ─────────────────────────────
			FormHelper.addSeparator(mainPanel, 3, 0, 2, "Zeitraum");
			FormHelper.addLabel(mainPanel, 3, 1, "Von");
			FormHelper.add(mainPanel, getVonDateChooser(), 4, 1, 1);
			FormHelper.addLabel(mainPanel, 3, 2, "Bis");
			FormHelper.add(mainPanel, getBisDateChooser(), 4, 2, 1);

			// ── Parameter ─────────────────────────────────────
			FormHelper.addSeparator(mainPanel, 0, 4, GridBagConstraints.REMAINDER,
					"Parameter");
			FormHelper.add(mainPanel, getParameterPanel(), 0, 5,
					GridBagConstraints.REMAINDER);

			// ── Button direkt darunter ────────────────────────
			GridBagConstraints gbc = FormHelper.constraints(0, 6,
					GridBagConstraints.REMAINDER);
			gbc.insets = new Insets(10, 5, 3, 5);
			mainPanel.add(getSubmitButton(), gbc);

			// alles oben links, kein Strecken über die volle Breite
			this.panel = new JPanel(new BorderLayout());
			this.panel.add(FormHelper.wrapNorthWest(mainPanel), BorderLayout.NORTH);
		}
		return this.panel;
	}

	public void showResultOneAxis(final String axis)
	{
		SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton())
		{
			@Override
			protected void doNonUILogic() throws RuntimeException
			{
				KlaerschlammAuswertung.this.dataSet1 = createDataset(axis);
			}

			@Override
			protected void doUIUpdateLogic() throws RuntimeException
			{
				if (KlaerschlammAuswertung.this.dataSet1.getSeriesCount() > 0)
				{
					KlaerschlammAuswertung.this.frame.clearStatus();
					AuswertungsDialog dialog = new AuswertungsDialog(
							getArtBox().getSelectedItem().toString(),
							KlaerschlammAuswertung.this.dataSet1, null,
							KlaerschlammAuswertung.this.frame);
					dialog.setVisible(true);
				}
				else
				{
					KlaerschlammAuswertung.this.frame
							.changeStatus("Keine Parameter ausgewählt!");
				}
			}
		};

		this.frame.changeStatus("Bereite Auswertung vor...");
		worker.start();
	}

	public void showResultDualAxis()
	{
		SwingWorkerVariant worker = new SwingWorkerVariant(getSubmitButton())
		{
			private int seriesCount = 0;

			@Override
			protected void doNonUILogic() throws RuntimeException
			{
				KlaerschlammAuswertung.this.dataSet1 = createDataset(LEFT);
				KlaerschlammAuswertung.this.dataSet2 = createDataset(RIGHT);

				this.seriesCount = KlaerschlammAuswertung.this.dataSet1
						.getSeriesCount()
						+ KlaerschlammAuswertung.this.dataSet2.getSeriesCount();
			}

			@Override
			protected void doUIUpdateLogic() throws RuntimeException
			{
				if (this.seriesCount > 0)
				{
					KlaerschlammAuswertung.this.frame.clearStatus();
					AuswertungsDialog dialog = new AuswertungsDialog(
							getArtBox().getSelectedItem().toString(),
							KlaerschlammAuswertung.this.dataSet1,
							KlaerschlammAuswertung.this.dataSet2,
							KlaerschlammAuswertung.this.frame);
					dialog.setVisible(true);
				}
				else
				{
					KlaerschlammAuswertung.this.frame
							.changeStatus("Keine Parameter ausgewählt!");
				}
			}
		};

		this.frame.changeStatus("Bereite Auswertung vor...");
		worker.start();
	}

	private TimeSeriesCollection createDataset(String axis)
	{
		TimeSeriesCollection col = new TimeSeriesCollection();

		//        int parameterAnzahl;
		Einheiten einheit;
		JList<Parameter> paramList;
		String analyseVon;
		if (axis.equals(LEFT))
		{
			//            parameterAnzahl = getLeftList().getModel().getSize();
			einheit = (Einheiten) getLeftEinheitenBox().getSelectedItem();
			paramList = getLeftList();
			analyseVon = getLeftAnalyseFeld().getText().toLowerCase().trim();
		}
		else
		{
			//            parameterAnzahl = getRightList().getModel().getSize();
			einheit = (Einheiten) getRightEinheitenBox().getSelectedItem();
			paramList = getRightList();
			analyseVon = getRightAnalyseFeld().getText().toLowerCase().trim();
		}

		Probeart art = (Probeart) getArtBox().getSelectedItem();

		Date vonDate = getVonDateChooser().getDate();
		Date bisDate = getBisDateChooser().getDate();

		if (this.klaeranlagen.getSelectedIndices().length > 0)
		{
			for (int ndx : this.klaeranlagen.getSelectedIndices())
			{
				Klaeranlage anlage =
						(Klaeranlage)
						this.klaeranlagen.getModel().getElementAt(ndx);
				createSeries(art,
								anlage,
								einheit,
								paramList,
								analyseVon,
								vonDate,
								bisDate,
								col);
			}
		}

		return col;
	}

	private void createSeries(Probeart art, Klaeranlage ka,
		Einheiten einheit, JList<Parameter> paramList, String analyseVon,
		Date vonDate, Date bisDate, TimeSeriesCollection col)
	{

		Messstelle pkt = DatabaseQuery.getKlaerschlammProbepunkt(art, ka);

		if (pkt != null)
		{
			for (int i = 0; i < paramList.getModel().getSize(); i++)
			{
				Parameter param = paramList.getModel().getElementAt(i);

				this.frame.changeStatus("Erzeuge Datenreihe für " + param + ", "
						+ ka);

				List<Analyseposition> list =
						DatabaseQuery.getAnalysepositionFromView(
																	param, einheit, pkt, vonDate, bisDate, analyseVon);
				TimeSeries series = ChartDataSets
						.createAnalysePositionenSeries(list, param + ", " + ka,
														einheit.toString());
				col.addSeries(series);
			}
		}
	}

	private JDateChooser getVonDateChooser()
	{
		if (this.vonDateChooser == null)
		{
			this.vonDateChooser = new JDateChooser(null, DateUtils.FORMAT_DEFAULT);
		}

		return this.vonDateChooser;
	}

	private JDateChooser getBisDateChooser()
	{
		if (this.bisDateChooser == null)
		{
			this.bisDateChooser = new JDateChooser(null, DateUtils.FORMAT_DEFAULT);
		}

		return this.bisDateChooser;
	}

	private JList<Klaeranlage> getKlaeranlagen()
	{
		if (this.klaeranlagen == null)
		{
			List<Klaeranlage> all = Klaeranlage.getAll();
			DefaultListModel<Klaeranlage> model = new DefaultListModel<>();
			this.klaeranlagen = new JList<>(model);
			this.klaeranlagen.setSelectionMode(
					ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			for (Klaeranlage element : all)
			{
				model.addElement(element);
			}
		}
		return this.klaeranlagen;
	}

	private JComboBox<Probeart> getArtBox()
	{
		if (this.artBox == null)
		{
			Integer[] artIDs = new Integer[] {
					DatabaseConstants.ATL_PROBEART_ID_ROHSCHLAMM,
					DatabaseConstants.ATL_PROBEART_ID_FAULSCHLAMM,
					DatabaseConstants.ATL_PROBEART_ID_ANLIEFERUNG,
					DatabaseConstants.ATL_PROBEART_ID_ZULAUF
			};
			Probeart[] arten = new Probeart[artIDs.length];
			for (int i = 0; i < artIDs.length; i++)
			{
				arten[i] = Probeart.findById(artIDs[i]);
			}
			this.artBox = new JComboBox<>(arten);
		}

		return this.artBox;
	}

	private JButton getSubmitButton()
	{
		if (this.submitButton == null)
		{
			this.submitButton = new JButton("Abschicken");

			this.submitButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (getRightList().getModel().getSize() == 0)
					{
						showResultOneAxis(LEFT);
					}
					else if (getLeftList().getModel().getSize() == 0)
					{
						showResultOneAxis(RIGHT);
					}
					else
					{
						showResultDualAxis();
					}
				}
			});
		}

		return this.submitButton;
	}

	private JPanel getParameterPanel()
	{
		if (this.parameterPanel == null)
		{

			// Liste < Params > Liste – kompakt, alles linksbündig
			JPanel paramPanel = FormHelper.createFormPanel();

			FormHelper.add(paramPanel, new JLabel("Erste Y-Achse"), 0, 0, 1);
			FormHelper.add(paramPanel, new JLabel("Zweite Y-Achse"), 4, 0, 1);

			JList<Parameter> lList = getLeftList();
			JList<Parameter> rList = getRightList();
			JScrollPane lScroller = new JScrollPane(lList);
			JScrollPane rScroller = new JScrollPane(rList);
			lScroller.setPreferredSize(new Dimension(180, 220));
			rScroller.setPreferredSize(new Dimension(180, 220));
			FormHelper.addSpan(paramPanel, lScroller, 0, 1, 1, 8);
			FormHelper.addSpan(paramPanel, rScroller, 4, 1, 1, 8);

			String[] paramIDs = {
					DatabaseConstants.ATL_PARAMETER_ID_CADMIUM,
					DatabaseConstants.ATL_PARAMETER_ID_CHROM,
					DatabaseConstants.ATL_PARAMETER_ID_KUPFER,
					DatabaseConstants.ATL_PARAMETER_ID_QUECKSILBER,
					DatabaseConstants.ATL_PARAMETER_ID_NICKEL,
					DatabaseConstants.ATL_PARAMETER_ID_BLEI,
					DatabaseConstants.ATL_PARAMETER_ID_ZINK
			};
			int y = 1;
			for (String paramID : paramIDs)
			{
				FormHelper.add(paramPanel, createRLButton(true, paramID), 1, y, 1);
				addCentered(paramPanel,
						new JLabel(Parameter.findById(paramID).getBezeichnung()),
						2, y);
				FormHelper.add(paramPanel, createRLButton(false, paramID), 3, y, 1);
				y++;
			}

			// Zeile 8: freie Parameterauswahl (feste Breite = Breite der Mittelspalte)
			FormHelper.fixWidth(getParameterBox(), 180);
			FormHelper.add(paramPanel, createRLButton(true, "box"), 1, 8, 1);
			addCentered(paramPanel, getParameterBox(), 2, 8);
			FormHelper.add(paramPanel, createRLButton(false, "box"), 3, 8, 1);

			// Zeile 9: Löschen
			addCentered(paramPanel, getLeftDeleteButton(), 0, 9);
			addCentered(paramPanel, getRightDeleteButton(), 4, 9);

			// Zeile 10: Einheit
			FormHelper.fixWidth(getLeftEinheitenBox(), 140);
			FormHelper.fixWidth(getRightEinheitenBox(), 140);
			addCentered(paramPanel, getLeftEinheitenBox(), 0, 10);
			addCentered(paramPanel, new JLabel("<  Einheit  >"), 2, 10);
			addCentered(paramPanel, getRightEinheitenBox(), 4, 10);

			// Zeile 11: Analyse von
			FormHelper.fixWidth(getLeftAnalyseFeld(), 140);
			FormHelper.fixWidth(getRightAnalyseFeld(), 140);
			addCentered(paramPanel, getLeftAnalyseFeld(), 0, 11);
			addCentered(paramPanel, new JLabel("<  Analyse von  >"), 2, 11);
			addCentered(paramPanel, getRightAnalyseFeld(), 4, 11);

			this.parameterPanel = paramPanel;
		}

		return this.parameterPanel;
	}

	/** Komponente in der Zelle zentriert, ohne Strecken. */
	private void addCentered(JPanel p, java.awt.Component comp, int x, int y)
	{
		GridBagConstraints gbc = FormHelper.constraints(x, y, 1);
		gbc.anchor = GridBagConstraints.CENTER;
		p.add(comp, gbc);
	}

	private JList<Parameter> getRightList()
	{
		if (this.rightList == null)
		{
			DefaultListModel<Parameter> listModel = new DefaultListModel<>();
			this.rightList = new JList<>(listModel);
			this.rightList
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		return this.rightList;
	}

	private JList<Parameter> getLeftList()
	{
		if (this.leftList == null)
		{
			DefaultListModel<Parameter> listModel = new DefaultListModel<>();
			this.leftList = new JList<>(listModel);

			this.leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		return this.leftList;
	}

	private JComboBox<Parameter> getParameterBox()
	{
		if (this.parameterBox == null)
		{
			this.parameterBox = new SearchBox<>(
					DatabaseQuery.getKlaerschlammParameter());
		}

		return this.parameterBox;
	}

	private JButton getLeftDeleteButton()
	{
		if (this.leftDeleteButton == null)
		{
			this.leftDeleteButton = new JButton("Löschen");
			this.leftDeleteButton.setEnabled(false);

			this.leftDeleteButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					int index = getLeftList().getSelectedIndex();
					DefaultListModel<Parameter> leftModel
                        = (DefaultListModel<Parameter>) getLeftList()
                            .getModel();

					if (index != -1)
					{
						leftModel.remove(index);

						int size = leftModel.getSize();

						if (size == 0)
						{
							KlaerschlammAuswertung.this.leftDeleteButton
									.setEnabled(false);
						}
						else
						{
							if (index == size)
							{
								index--;
							}

							getLeftList().setSelectedIndex(index);
							getLeftList().ensureIndexIsVisible(index);
						}
					}
				}
			});
		}

		return this.leftDeleteButton;
	}

	private JButton getRightDeleteButton()
	{
		if (this.rightDeleteButton == null)
		{
			this.rightDeleteButton = new JButton("Löschen");
			this.rightDeleteButton.setEnabled(false);

			this.rightDeleteButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					int index = getRightList().getSelectedIndex();
					DefaultListModel<Parameter> rightModel
                        = (DefaultListModel<Parameter>) getRightList()
                            .getModel();

					if (index != -1)
					{
						rightModel.remove(index);

						int size = rightModel.getSize();

						if (size == 0)
						{
							KlaerschlammAuswertung.this.rightDeleteButton
									.setEnabled(false);
						}
						else
						{
							if (index == size)
							{
								index--;
							}

							getRightList().setSelectedIndex(index);
							getRightList().ensureIndexIsVisible(index);
						}
					}
				}
			});
		}

		return this.rightDeleteButton;
	}

	private JComboBox<Einheiten> getLeftEinheitenBox()
	{
		if (this.leftEinheitenBox == null)
		{
			this.leftEinheitenBox = new SearchBox<>(this.einheiten);
			this.leftEinheitenBox.setSelectedItem(
					DatabaseConstants.ATL_EINHEIT_MG_KG);
		}

		return this.leftEinheitenBox;
	}

	private JComboBox<Einheiten> getRightEinheitenBox()
	{
		if (this.rightEinheitenBox == null)
		{
			this.rightEinheitenBox = new SearchBox<>(this.einheiten);
			this.rightEinheitenBox.setSelectedItem(
					DatabaseConstants.ATL_EINHEIT_MG_KG);
		}

		return this.rightEinheitenBox;
	}

	private JTextField getLeftAnalyseFeld()
	{
		if (this.leftAnalyseFeld == null)
		{
			this.leftAnalyseFeld = new JTextField("");
			this.leftAnalyseFeld.setPreferredSize(getLeftEinheitenBox()
					.getPreferredSize());
		}

		return this.leftAnalyseFeld;
	}

	private JTextField getRightAnalyseFeld()
	{
		if (this.rightAnalyseFeld == null)
		{
			this.rightAnalyseFeld = new JTextField("");
			this.rightAnalyseFeld.setPreferredSize(getRightEinheitenBox()
					.getPreferredSize());
		}

		return this.rightAnalyseFeld;
	}

	private JButton createRLButton(boolean left, String paramId)
	{
		Icon icon;
		Icon rIcon;
		String direction;
		if (left)
		{
			icon = AuikUtils.getIcon(16, "left.png");
			rIcon = AuikUtils.getIcon(16, "left_a.png");
			direction = "left";
		}
		else
		{
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

	private ActionListener getRLButtonListener()
	{
		if (this.rlButtonListener == null)
		{
			this.rlButtonListener = new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					String direction = e.getActionCommand().replaceFirst("_.*",
																			"");
					String paramId = e.getActionCommand().replaceFirst(".*_",
																		"");
					Parameter param = null;

					if (!paramId.equals(""))
					{
						if (paramId.equals("box"))
						{
							param = (Parameter) getParameterBox()
									.getSelectedItem();
						}
						else
						{
							param = Parameter.findById(paramId);
						}
					}

					if (param != null)
					{
						DefaultListModel<Parameter> leftModel
                            = (DefaultListModel<Parameter>) getLeftList()
								.getModel();
						DefaultListModel<Parameter> rightModel
                            = (DefaultListModel<Parameter>) getRightList()
								.getModel();

						if (direction.equals(LEFT))
						{
							if (!leftModel.contains(param))
							{
								if (rightModel.contains(param))
								{
									rightModel.removeElement(param);
									if (rightModel.getSize() == 0)
									{
										getRightDeleteButton()
												.setEnabled(false);
									}
								}
								leftModel.addElement(param);
								getLeftDeleteButton().setEnabled(true);
							}
						}
						else if (direction.equals(RIGHT))
						{
							if (!rightModel.contains(param))
							{
								if (leftModel.contains(param))
								{
									leftModel.removeElement(param);
									if (leftModel.getSize() == 0)
									{
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

		return this.rlButtonListener;
	}
}
