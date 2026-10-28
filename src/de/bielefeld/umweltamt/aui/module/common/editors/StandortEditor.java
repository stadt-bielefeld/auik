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
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.DoubleField;
import de.bielefeld.umweltamt.aui.utils.FormHelper;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

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


		JPanel panel = FormHelper.createFormPanel();
		FormHelper.addSeparator(panel, 0, 0, 3, "Standort");
		FormHelper.addLabel(panel, 0, 1, "Bezeichnung");
		FormHelper.addField(panel, 1, 1, 2, bezeichnungFeld);
		// Koordinaten
		FormHelper.addLabel(panel, 0, 2, "E32");
		FormHelper.fixWidth(e32Feld, 88);
		FormHelper.addField(panel, 1, 2, 1, e32Feld);
		FormHelper.addLabel(panel, 0, 3, "N32");
		FormHelper.fixWidth(n32Feld, 88);
		FormHelper.addField(panel, 1, 3, 1, n32Feld);
		FormHelper.addSpan(panel, getAusAblageButton(), 2, 2, 1, 2);


		FormHelper.setDialogBorder(panel);
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
