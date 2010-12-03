package de.bielefeld.umweltamt.aui.module.common;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

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
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektverknuepfung;
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

    private JTable ergebnisTabelle;

    private BasisObjektModel objektModel;
    private BasisObjekt chosenObjekt = null;
    private BasisObjekt objekt = null;
    private ObjektVerknuepfungModel objektVerknuepfungModel;

    public ObjektChooser(HauptFrame owner, BasisObjekt objekt, ObjektVerknuepfungModel objektVerknuepfungModel) {
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

        objektModel.searchByStandort(objekt.getBasisStandort());
        objektModel.fireTableDataChanged();

    }

    /*
     * (non-Javadoc)
     *
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#doOk()
     */
    protected void doOk() {
        int row = getErgebnisTabelle().getSelectedRow();
        choose(row, objekt, objektVerknuepfungModel);
    }

    private void choose(int row, BasisObjekt objekt, ObjektVerknuepfungModel objektVerknuepfungModel) {
        if (row != -1) {
            chosenObjekt = (BasisObjekt) objektModel.getObjectAtRow(row);
            BasisObjektverknuepfung neueov = new BasisObjektverknuepfung();
            neueov.setBasisObjektByObjekt(objekt);
            neueov.setBasisObjektByIstVerknuepftMit(chosenObjekt);
            BasisObjektverknuepfung.saveObjektVerknuepfung(neueov);
            objektVerknuepfungModel.updateList();
            dispose();
        }
    }

    public BasisObjekt getChosenObjekt() {
        return chosenObjekt;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea()
     */
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

