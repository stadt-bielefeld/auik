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

package de.bielefeld.umweltamt.aui.module.common;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.basis.MapAdresseLage;
import de.bielefeld.umweltamt.aui.mappings.basis.Objekt;
import de.bielefeld.umweltamt.aui.mappings.basis.Objektverknuepfung;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisObjektModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.ObjektVerknuepfungModel;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog;

/**
 * Stellt einem Modul die Funtionalitaet zum Verknuepfen von Objekten zur Verfuegung.
 *
 * @author Gerhard Genuit
 */
public class ObjektChooser extends OkCancelDialog {
    private static final long serialVersionUID = -6670451852911477811L;

    private JTable ergebnisTabelle;

    private BasisObjektModel objektModel;
    private Objekt chosenObjekt = null;
    private Objekt objekt = null;
    private ObjektVerknuepfungModel objektVerknuepfungModel;

    public ObjektChooser(HauptFrame owner, Objekt objekt, ObjektVerknuepfungModel objektVerknuepfungModel) {
        super("Objekt auswählen", owner);

        objektModel = new BasisObjektModel("Betreiber", null);
        this.objekt = objekt;
        this.objektVerknuepfungModel = objektVerknuepfungModel;
        getErgebnisTabelle().setModel(objektModel);

        ergebnisTabelle.getColumnModel().getColumn(0).setPreferredWidth(10);
        ergebnisTabelle.getColumnModel().getColumn(1).setPreferredWidth(100);
        ergebnisTabelle.getColumnModel().getColumn(2).setPreferredWidth(100);
        ergebnisTabelle.getColumnModel().getColumn(3).setPreferredWidth(100);

        setResizable(true);

        objektModel.searchByStandort(objekt.getAdresseByStandortid());
        // Remove this BasisObjekt from the list as we do not want to connect the
        // BasisObjekt with itself
        objektModel.removeFromList(objekt);
        objektModel.fireTableDataChanged();
    }

    /*
     * (non-Javadoc)
     *
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#doOk()
     */
    @Override
    protected void doOk() {
        int row = getErgebnisTabelle().getSelectedRow();
        choose(row, objekt, objektVerknuepfungModel);
    }

    private void choose(int row, Objekt objekt, ObjektVerknuepfungModel objektVerknuepfungModel) {
        if (row != -1) {
            chosenObjekt = (Objekt) objektModel.getObjectAtRow(row);
            Objektverknuepfung neueov = new Objektverknuepfung();
            neueov.setObjektByObjekt(objekt);
            neueov.setObjektByIstVerknuepftMit(chosenObjekt);
            Objektverknuepfung.merge(neueov);
            objektVerknuepfungModel.updateList();
            dispose();
        }
    }

    public Objekt getChosenObjekt() {
        return chosenObjekt;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea()
     */
    @Override
    protected JComponent buildContentArea() {
        JScrollPane tabellenScroller = new JScrollPane(getErgebnisTabelle(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        TabAction ta = new TabAction();
        ta.addComp(ergebnisTabelle);

        FormLayout layout = new FormLayout("180dlu:g, 3dlu, min(16dlu;p)", // spalten
                "300dlu:g"); // zeilen
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        builder.add(tabellenScroller, cc.xyw(1, 1, 3));

        return builder.getPanel();
    }

    private JTable getErgebnisTabelle() {
        if (ergebnisTabelle == null) {
            ergebnisTabelle = new JTable();

            Action submitAction = new AbstractAction() {
                private static final long serialVersionUID = -3180857351247221404L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    doOk();
                }
            };
            submitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke
                    .getKeyStroke(KeyEvent.VK_ENTER, 0, false));

            ergebnisTabelle.getInputMap().put(
                    (KeyStroke) submitAction.getValue(Action.ACCELERATOR_KEY),
                    submitAction.getValue(Action.NAME));
            ergebnisTabelle.getActionMap().put(
                    submitAction.getValue(Action.NAME), submitAction);

            ergebnisTabelle.addFocusListener(TableFocusListener.getInstance());
//            ergebnisTabelle.addMouseListener(new MouseAdapter() {
//                public void mouseClicked(java.awt.event.MouseEvent e) {
//                    if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
//                        Point origin = e.getPoint();
//                        int row = ergebnisTabelle.rowAtPoint(origin);
//                        choose(row, objekt);
//                    }
//                }
//            });
        }

        return ergebnisTabelle;
    }
}

