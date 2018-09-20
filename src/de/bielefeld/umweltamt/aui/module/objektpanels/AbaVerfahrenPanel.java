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
 * Created on 27.04.2005 by u633d
 */
package de.bielefeld.umweltamt.aui.module.objektpanels;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseConstants;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.mappings.elka.Aba;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Entsorger;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.module.common.ObjektChooser;
import de.bielefeld.umweltamt.aui.module.common.editors.EntsorgerEditor;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.IntegerField;
import de.bielefeld.umweltamt.aui.utils.LimitedTextArea;
import de.bielefeld.umweltamt.aui.utils.LimitedTextField;
import de.bielefeld.umweltamt.aui.utils.TextFieldDateChooser;

/**
 * Das "Abwasserbehandlungsanlage"-Tab des ObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class AbaVerfahrenPanel extends JPanel {

private static final long serialVersionUID = -4030805403749508467L;

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    private JList rightList;
	private JList leftList;
    // Widgets

    // Daten
    private Aba fachdaten = null;
    private Abaverfahren[] verfahren = null;

    // Listener
    private ActionListener editButtonListener;
 

    public AbaVerfahrenPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Behandlungsverfahren";
        this.hauptModul = hauptModul;

		String zeileLuecke = "pref, 3dlu";
        
		FormLayout parameterLayout = new FormLayout(
				/* Liste < Params > Liste */
				/* 1 2 3 4 5 6 7 8 9 */
				"l:p:g, 10dlu:g(0.3), r:16px, 5dlu, c:70dlu:g(0.1), 5dlu, l:16px, 10dlu:g(0.3), r:p:g",

				zeileLuecke + ", " + // 1,2 Linke Achse Rechte Achse
						zeileLuecke + ", " + // 3,4 | | Par1 | |
						zeileLuecke + ", " + // 5,6 | | Par2 | |
						zeileLuecke + ", " + // 7,8 | | Par3 | |
						zeileLuecke + ", " + // 9,10 | | Par4 | |
						zeileLuecke + ", " + // 11,12 | | Par5 | |
						zeileLuecke + ", " + // 13,14 | | Par6 | |
						zeileLuecke + ", " + // 15,16 | | Par7 | |
						zeileLuecke + ", " + // 17,18 | | [Par] | |
						zeileLuecke + ", " + // 19,20 ( Löschen ) ( Löschen )
						zeileLuecke + ", " + // 21,22 [ Einheit ] [ Einheit ]
						"pref"); // 23 [ AnalyVo ] [ AnalyVo ]

		parameterLayout.setColumnGroups(new int[][] { { 1, 9 }, { 3, 7 } });
		parameterLayout.setRowGroups(new int[][] { { 3, 5 } });

		PanelBuilder builder = new PanelBuilder(parameterLayout);
		CellConstraints cc = new CellConstraints();
        
		JList lList = getLeftList();
		JList rList = getRightList();
		builder.add(new JScrollPane(lList),
					cc.xywh(1, 3, 1, 15, "fill, fill"));
		builder.add(new JScrollPane(rList),
					cc.xywh(9, 3, 1, 15, "fill, fill"));

        builder.setDefaultDialogBorder();


    }

    public void fetchFormData() throws RuntimeException {
        this.fachdaten = Aba.findByObjektId(
            this.hauptModul.getObjekt().getId());
        log.debug("Abwasserbehandlungsanlage aus DB geholt: " + this.fachdaten);

        if (this.verfahren == null || this.verfahren.length == 0) {
            this.verfahren = DatabaseQuery.getVerfahren();
        }
    }

    public void updateForm() throws RuntimeException {


    }

    public void clearForm() {

    }

    public void enableAll(boolean enabled) {
        // This is intentionally left blank
    }

    @Override
    public String getName() {
        return this.name;
    }

    

    public void completeObjekt() {

    }

	private JList getRightList()
	{
		if (this.rightList == null)
		{
			DefaultListModel listModel = new DefaultListModel();
			this.rightList = new JList(listModel);
			this.rightList.setPrototypeCellValue("Abcdefghij (Ab)");
	
			this.rightList
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	
		return this.rightList;
	}

	private JList getLeftList()
	{
		if (this.leftList == null)
		{
			DefaultListModel listModel = new DefaultListModel();
			this.leftList = new JList(listModel);
			this.leftList.setPrototypeCellValue("Abcdefghij (Ab)");
	
			this.leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	
		return this.leftList;
	}

}
