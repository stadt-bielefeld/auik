package de.bielefeld.umweltamt.aui.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.mappings.indeinl.IndeinlGenehmigung;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.GenehmigungModel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class GenehmigungAuswertung extends AbstractQueryModul {
	/** Das obere Panel mit den Abfrage-Optionen */
	private JPanel queryPanel;
	
	// Widgets für die Abfrage
	private JButton submitButton;
	private JButton anh40Button;
	private JButton anh49Button;
	private JButton anh50Button;
	private JButton anh53Button;
	private JButton BwkButton;
	private JRadioButton alleButton;
	private JRadioButton gen58Button;
	private JRadioButton gen59Button;
	private ButtonGroup group;
	
	/** Das TableModel für die Ergebnis-Tabelle */
	private GenehmigungModel tmodel;

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.Modul#getName()
	 */
	public String getName() {
		return "Genehmigung";
	}

	/*
	 * @see de.bielefeld.umweltamt.aui.Modul#getIdentifier()
	 * @return "m_auswertung_genehmigung"
	 */
	public String getIdentifier() {
		return "m_auswertung_genehmigung";
	}
	
	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getQueryOptionsPanel()
	 */
	public JPanel getQueryOptionsPanel() {
		if (queryPanel == null) {
			// Die Widgets initialisieren
			submitButton = new JButton("Alle Objekte anzeigen");
			anh40Button = new JButton("40");
			anh49Button = new JButton("49");
			anh50Button = new JButton("50");
			anh53Button = new JButton("53");
			BwkButton = new JButton("BWK");
			group = new ButtonGroup();
			alleButton = new JRadioButton("alle", true);
			group.add(alleButton);
			gen58Button = new JRadioButton("nur 58er", false);
			group.add(gen58Button);
			gen59Button = new JRadioButton("nur 59er", false);
			group.add(gen59Button);
			
			// Ein ActionListener für den Button, 
			// der die eigentliche Suche auslöst:

			
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					SwingWorkerVariant worker = new SwingWorkerVariant(getResultTable()) {
						protected void doNonUILogic() {
							String gen58 = new String();
							String gen59 = new String();
							if (alleButton.isSelected() == true){
								gen58 = "t";
								gen59 = "t";
							}
							else if (gen58Button.isSelected() == true){
								gen58 = "t";
								gen59 = "f";
							}
							else if (gen59Button.isSelected() == true){
								gen58 = "f";
								gen59 = "t";
							}
							((GenehmigungModel)getTableModel()).setList(IndeinlGenehmigung.getAuswertungsListe(gen58, gen59));
						}

						protected void doUIUpdateLogic(){
							((GenehmigungModel)getTableModel()).fireTableDataChanged();
							frame.changeStatus(+ getTableModel().getRowCount() + " Objekte gefunden");
						}
					};
					worker.start();
				}
			});
			
			anh40Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingWorkerVariant worker = new SwingWorkerVariant(
							getResultTable()) {
						protected void doNonUILogic() {
							String gen58 = new String();
							String gen59 = new String();
							if (alleButton.isSelected() == true){
								gen58 = "t";
								gen59 = "t";
							}
							else if (gen58Button.isSelected() == true){
								gen58 = "t";
								gen59 = "f";
							}
							else if (gen59Button.isSelected() == true){
								gen58 = "f";
								gen59 = "t";
							}
							((GenehmigungModel) getTableModel())
									.setList(IndeinlGenehmigung.getAnh40Liste(gen58, gen59));
						}

						protected void doUIUpdateLogic() {
							((GenehmigungModel) getTableModel())
									.fireTableDataChanged();
							frame.changeStatus(""
									+ getTableModel().getRowCount()
									+ " Objekte gefunden");
						}
					};
					worker.start();
				}
			});
			
			anh49Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingWorkerVariant worker = new SwingWorkerVariant(
							getResultTable()) {
						protected void doNonUILogic() {
							String gen58 = new String();
							String gen59 = new String();
							if (alleButton.isSelected() == true){
								gen58 = "t";
								gen59 = "t";
							}
							else if (gen58Button.isSelected() == true){
								gen58 = "t";
								gen59 = "f";
							}
							else if (gen59Button.isSelected() == true){
								gen58 = "f";
								gen59 = "t";
							}
							((GenehmigungModel) getTableModel())
									.setList(IndeinlGenehmigung.getAnh49Liste(gen58, gen59));
						}

						protected void doUIUpdateLogic() {
							((GenehmigungModel) getTableModel())
									.fireTableDataChanged();
							frame.changeStatus(""
									+ getTableModel().getRowCount()
									+ " Objekte gefunden");
						}
					};
					worker.start();
				}
			});
			
			anh50Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingWorkerVariant worker = new SwingWorkerVariant(
							getResultTable()) {
						protected void doNonUILogic() {
							String gen58 = new String();
							String gen59 = new String();
							if (alleButton.isSelected() == true){
								gen58 = "t";
								gen59 = "t";
							}
							else if (gen58Button.isSelected() == true){
								gen58 = "t";
								gen59 = "f";
							}
							else if (gen59Button.isSelected() == true){
								gen58 = "f";
								gen59 = "t";
							}
							((GenehmigungModel) getTableModel())
									.setList(IndeinlGenehmigung.getAnh50Liste(gen58, gen59));
						}

						protected void doUIUpdateLogic() {
							((GenehmigungModel) getTableModel())
									.fireTableDataChanged();
							frame.changeStatus(""
									+ getTableModel().getRowCount()
									+ " Objekte gefunden");
						}
					};
					worker.start();
				}
			});
			
			anh53Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingWorkerVariant worker = new SwingWorkerVariant(
							getResultTable()) {
						protected void doNonUILogic() {
							String gen58 = new String();
							String gen59 = new String();
							if (alleButton.isSelected() == true){
								gen58 = "t";
								gen59 = "t";
							}
							else if (gen58Button.isSelected() == true){
								gen58 = "t";
								gen59 = "f";
							}
							else if (gen59Button.isSelected() == true){
								gen58 = "f";
								gen59 = "t";
							}
							((GenehmigungModel) getTableModel())
									.setList(IndeinlGenehmigung.getAnh53Liste(gen58, gen59));
						}

						protected void doUIUpdateLogic() {
							((GenehmigungModel) getTableModel())
									.fireTableDataChanged();
							frame.changeStatus(""
									+ getTableModel().getRowCount()
									+ " Objekte gefunden");
						}
					};
					worker.start();
				}
			});
			
			BwkButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingWorkerVariant worker = new SwingWorkerVariant(
							getResultTable()) {
						protected void doNonUILogic() {
							String gen58 = new String();
							String gen59 = new String();
							if (alleButton.isSelected() == true){
								gen58 = "t";
								gen59 = "t";
							}
							else if (gen58Button.isSelected() == true){
								gen58 = "t";
								gen59 = "f";
							}
							else if (gen59Button.isSelected() == true){
								gen58 = "f";
								gen59 = "t";
							}
							((GenehmigungModel) getTableModel())
									.setList(IndeinlGenehmigung.getBwkListe(gen58, gen59));
						}

						protected void doUIUpdateLogic() {
							((GenehmigungModel) getTableModel())
									.fireTableDataChanged();
							frame.changeStatus(""
									+ getTableModel().getRowCount()
									+ " Objekte gefunden");
						}
					};
					worker.start();
				}
			});
			
			// Noch etwas Layout...
			FormLayout layout = new FormLayout("pref, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu, 3dlu, 40dlu");
			DefaultFormBuilder builder = new DefaultFormBuilder(layout);
			
			builder.append(submitButton);
			builder.append(anh40Button);
			builder.append(anh49Button);
			builder.append(anh50Button);
			builder.append(anh53Button);
			builder.append(BwkButton);
			builder.append("");
			builder.append(alleButton);
			builder.append(gen58Button);
			builder.append(gen59Button);
			
			queryPanel = builder.getPanel();
		}
		
		return queryPanel;
	}

	/* (non-Javadoc)
	 * @see de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul#getTableModel()
	 */
	public ListTableModel getTableModel() {
		if (tmodel == null) {
			tmodel = new GenehmigungModel();
		}
		return tmodel;
	}
}
