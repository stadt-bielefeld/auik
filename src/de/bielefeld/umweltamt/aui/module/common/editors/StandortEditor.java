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

package de.bielefeld.umweltamt.aui.module.common.editors;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.criterion.MatchMode;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Gemarkung;
import de.bielefeld.umweltamt.aui.mappings.basis.Orte;
import de.bielefeld.umweltamt.aui.mappings.awsv.Standortgghwsg;
import de.bielefeld.umweltamt.aui.mappings.awsv.Wassereinzugsgebiet;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.SearchBox;
import de.bielefeld.umweltamt.aui.utils.StringUtils;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;

/**
 * Ein Dialog zum Bearbeiten eines Standorts.
 * 
 * @author David Klotz
 */
public class StandortEditor extends AbstractBaseEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3106924002637463142L;

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private JTextField bezeichnungFeld;
	private JFormattedTextField e32Feld;
	private JFormattedTextField n32Feld;
	private JButton ausAblageButton;
	private Standort bsta;

	/**
	 * Erzeugt einen neuen Dialog zum Bearbeiten eines Standorts.
	 */
	public StandortEditor(Standort bsta, HauptFrame frame)
	{
		super("Standort (" + bsta.getId() + ")", bsta, frame);
		this.frame = frame;
		this.bsta = bsta;
	}

	@Override
	protected JComponent buildContentArea()
	{

		bezeichnungFeld = new LimitedTextField(50);
		e32Feld = new DoubleField(1);
		n32Feld = new DoubleField(1);


		
		String linkeSpalten = "r:p, 3dlu, 50dlu:g, 3dlu, 50dlu:g, 5dlu, 20dlu:g(0.2), 3dlu, 15dlu:g(0.2)";
		String rechteSpalten = "r:p, 3dlu, 50dlu:g, 3dlu, 50dlu:g";
		int rS = 10;

		FormLayout layout = new FormLayout(
				linkeSpalten + ", 10dlu, " + rechteSpalten, // Spalten
				"pref, " + //1
						"3dlu, " + //2
						"pref, " + //3
						"3dlu, " + //4
						"pref, " + //5
						"3dlu, " + //6
						"pref, " + //7
						"3dlu, " + //8

						"bottom:pref:grow"); //9

		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addSeparator("Standort", cc.xyw(1, 1, 9));

		builder.addLabel("Bezeichnung:", cc.xy(1, 3));
		builder.add(bezeichnungFeld, cc.xyw(3, 3, 3));
		
		// Koordinaten
		builder.addLabel("E32:", cc.xy(1, 5));
		builder.add(e32Feld, cc.xy(3, 5));
		builder.addLabel("N32:", cc.xy(1, 7));
		builder.add(n32Feld, cc.xy(3, 7));
		builder.add(getAusAblageButton(), cc.xywh(5, 5, 1, 3));



		JPanel panel = builder.getPanel();
		panel.setBorder(Paddings.DIALOG);
		return panel;
	}

	
	@Override
	protected void fillForm()
	{
		frame.changeStatus("Beschäftigt...");

		SwingWorkerVariant worker = new SwingWorkerVariant(this)
		{

			@Override
			protected void doNonUILogic() throws RuntimeException {
				
			}


			@Override
			protected void doUIUpdateLogic() throws RuntimeException
			{


				bezeichnungFeld.setText(getStandort().getBezeichnung());
				e32Feld.setValue(getStandort().getE32());
				n32Feld.setValue(getStandort().getN32());

				frame.clearStatus();
			}
		};
		worker.start();
	}

	@Override
	protected boolean canSave()
	{
		return true;
		// Eingaben überprüfen:

	}

	/**
	 * Wird aufgerufen, wenn der Benutzen auf "Speichern" geklickt hat.
	 */
	@Override
	protected boolean doSave()
	{

		//Bezeichnung
		String bezeichnung = bezeichnungFeld.getText();
		if (bezeichnung != null)
		{
			bezeichnung = bezeichnung.trim();
			if (bezeichnung.equals(""))
			{
				getStandort().setBezeichnung(null);
			}
			else
			{
				getStandort().setBezeichnung(bezeichnung);
			}
		}
		
		// E32
		Float e32Wert = ((DoubleField) e32Feld).getFloatValue();
		getStandort().setE32(e32Wert);

		// N32
		Float n32Wert = ((DoubleField) n32Feld).getFloatValue();
		getStandort().setN32(n32Wert);

		if (bsta != null)
		{
			setEditedObject(bsta);
			Standort.merge(bsta);
			log.debug("Änderungen gespeichert!");
			return true;
		}
		else
		{
			return false;
		}
	}

	public Standort getStandort()
	{
		return (Standort) getEditedObject();
	}

	private void readClipboard()
	{

		Clipboard systemClipboard;
		systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferData = systemClipboard.getContents(null);
		for (DataFlavor dataFlavor : transferData.getTransferDataFlavors())
		{
			Object content = null;
			try
			{
				content = transferData.getTransferData(dataFlavor);
			}
			catch (UnsupportedFlavorException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (content instanceof String)
			{

				String[] tmp = content.toString().split(",");
				if (tmp.length == 4)
				{
					String e32AusZeile = tmp[2];
					String n32AusZeile = tmp[3];
					e32Feld.setText(e32AusZeile.substring(0, 7));
					n32Feld.setText(n32AusZeile.substring(0, 7));
					frame.changeStatus("E32- und N32 eingetragen",
										HauptFrame.SUCCESS_COLOR);
				}
				else
				{
					frame.changeStatus(
										"Zwischenablage enthält keine verwertbaren Daten",
										HauptFrame.ERROR_COLOR);
				}
				break;
			}
		}
	}

	public JButton getAusAblageButton()
	{
		if (ausAblageButton == null)
		{

			ausAblageButton = new JButton("aus QGis");
			ausAblageButton.setToolTipText("Rechts- und Hochwert aus Zwischenablage einfügen");
			ausAblageButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					readClipboard();
				}
			});
		}

		return ausAblageButton;
	}
}
