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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.elka.Aba;
import de.bielefeld.umweltamt.aui.mappings.elka.Abaverfahren;
import de.bielefeld.umweltamt.aui.mappings.elka.ZAbaVerfahren;
import de.bielefeld.umweltamt.aui.module.BasisObjektBearbeiten;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * Das "Abwasserbehandlungsverfahren"-Tab des ObjektBearbeiten-Moduls
 * @author Gerd Genuit
 */
public class AbaVerfahrenPanel extends JPanel {

	private static final long serialVersionUID = 3548243605243275016L;

	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private String name;
    private BasisObjektBearbeiten hauptModul;
    
    // Widgets

	private JList leftList;
    private JList rightList;
	private JButton uebernehmenButton;
	private JButton entfernenButton;
    private JButton saveAbaverfButton;

    // Daten
    private Aba fachdaten = null;
    private Abaverfahren[] verfahrens = null;
    private List<Abaverfahren> abaverflist = null;
    private DefaultListModel<Abaverfahren> rightListModel = new DefaultListModel<Abaverfahren>();

    // Listener 

    public AbaVerfahrenPanel(BasisObjektBearbeiten hauptModul) {
        this.name = "Behandlungsverfahren";
        this.hauptModul = hauptModul;
        this.uebernehmenButton = new JButton("Übernehmen >");
        this.entfernenButton = new JButton("< Entfernen");
        this.saveAbaverfButton = getSaveAbaverfButton();
        this.rightList = new JList<Abaverfahren>(rightListModel);

        
        
		FormLayout verfahrenLayout = new FormLayout("150dlu, 5dlu, 90dlu, 5dlu, 150dlu", // Spalten
				"20dlu, 3dlu, 20dlu, 3dlu, 20dlu, 3dlu, 20dlu, 3dlu, 20dlu, 3dlu, 300dlu, 3dlu, 20dlu"); // zeilen
		
        DefaultFormBuilder builder = new DefaultFormBuilder(verfahrenLayout, this);
        builder.setDefaultDialogBorder();
        
        CellConstraints cc = new CellConstraints();
		
        builder.add(new JScrollPane(getLeftList()), cc.xywh(1, 3, 1,9));
        builder.add(new JScrollPane(rightList), cc.xywh(5, 3, 1, 9));
        builder.add(uebernehmenButton, cc.xy(3, 5));
        builder.add(entfernenButton, cc.xy(3, 7));
        builder.add(saveAbaverfButton, cc.xy(3, 13));
        builder.nextLine();
        


        builder.setDefaultDialogBorder();


    }

    public void fetchFormData() throws RuntimeException {
    	        
        this.fachdaten = Aba.findByObjektId(
                this.hauptModul.getObjekt().getId());
            log.debug("Abwasserbehandlungsanlage aus DB geholt: " + this.fachdaten);

        this.abaverflist = new ArrayList<Abaverfahren>(fachdaten.getAbaverfahrens());
                
    }

    public void updateForm() throws RuntimeException {
               
    	rightListModel.clear();
        for (int i = 0; i < abaverflist.size(); i++) {
        	rightListModel.addElement((Abaverfahren) abaverflist.toArray()[i]);
        }
		
		this.rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

	private JList getLeftList()
	{
		if (this.leftList == null)
		{
			DefaultListModel listModel = new DefaultListModel();
	    	
	        if (this.verfahrens == null || this.verfahrens.length == 0) {
	            this.verfahrens = DatabaseQuery.getVerfahren();
	        }
			this.leftList = new JList(listModel);
			this.leftList.setListData(verfahrens);
	
			this.leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
	
		return this.leftList;
	}

	private JButton getSaveAbaverfButton() {
	    if (this.saveAbaverfButton == null) {
	        this.saveAbaverfButton = new JButton("Speichern");
	
	        this.saveAbaverfButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                enableAll(false);
	                if (saveAbaverf()) {
	                	AbaVerfahrenPanel.this.hauptModul.getFrame().changeStatus(
	                        "List der Abwasserbehandklungsverfahren "
	                            + AbaVerfahrenPanel.this.fachdaten.getId()
	                            + " erfolgreich gespeichert.",
	                        HauptFrame.SUCCESS_COLOR);
	                } else {
	                	AbaVerfahrenPanel.this.hauptModul.getFrame().changeStatus(
	                        "Fehler beim Speichern des Zahnarztes!",
	                        HauptFrame.ERROR_COLOR);
	                }
	
	                AbaVerfahrenPanel.this.hauptModul.fillForm();
	            }

				private boolean saveAbaverf() {
					// TODO Auto-generated method stub
					return false;
				}
	        });
	    }
	    return this.saveAbaverfButton;
	}


		
	

}
