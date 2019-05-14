package de.bielefeld.umweltamt.aui.module;

import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.mappings.atl.Sielhaut;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.SielhautModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.PanelBuilder;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;
import de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog;

class SielhautChooser extends OkCancelDialog {
    private static final long serialVersionUID = -8611205076943773598L;

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    private JTextField suchFeld;
    private JButton submitButton;
    private JTable ergebnisTabelle;

    private SielhautModel sielhautModel;
    private Sielhaut chosenSielhaut = null;

    public SielhautChooser(HauptFrame owner) {
        super("Sielhautpunkt auswählen", owner);

        this.sielhautModel = new SielhautModel();
        getErgebnisTabelle().setModel(this.sielhautModel);

        this.ergebnisTabelle.getColumnModel().getColumn(0)
            .setPreferredWidth(80);
        this.ergebnisTabelle.getColumnModel().getColumn(1)
            .setPreferredWidth(230);
        this.ergebnisTabelle.getColumnModel().getColumn(2).setPreferredWidth(8);
        this.ergebnisTabelle.getColumnModel().getColumn(3).setPreferredWidth(8);
        this.ergebnisTabelle.getColumnModel().getColumn(4).setPreferredWidth(8);
        this.ergebnisTabelle.getColumnModel().getColumn(5).setPreferredWidth(8);

        setResizable(true);

        this.sielhautModel.filterList("");
        this.sielhautModel.fireTableDataChanged();
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.OkCancelDialog#doOk()
     */
    @Override
    protected void doOk() {
        int row = getErgebnisTabelle().getSelectedRow();
        choose(row);
    }

    protected void doSearch() {
        final String suche = getSuchFeld().getText();

        SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
            @Override
            protected void doNonUILogic() throws RuntimeException {
                SielhautChooser.this.sielhautModel.filterList(suche);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                SielhautChooser.this.sielhautModel.fireTableDataChanged();
            }
        };

        worker.start();
    }

    private void choose(int row) {
        if (row != -1) {
            this.chosenSielhaut = (Sielhaut) this.sielhautModel
                .getObjectAtRow(row);
            dispose();
        }
    }

    public Sielhaut getChosenSielhaut() {
        return this.chosenSielhaut;
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.dialogbase.SimpleDialog#buildContentArea()
     */
    @Override
    protected JComponent buildContentArea() {
        JScrollPane tabellenScroller = new JScrollPane(getErgebnisTabelle(),
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        TabAction ta = new TabAction();
        ta.addComp(this.ergebnisTabelle);
        ta.addComp(this.button1);
        // ta.addComp(button2);
        JToolBar submitToolBar = new JToolBar();
        submitToolBar.setFloatable(false);
        submitToolBar.setRollover(true);
        submitToolBar.add(getSubmitButton());

        PanelBuilder builder = new PanelBuilder();
        builder.setBorder(new EmptyBorder(5, 5, 5, 5));
        builder.setInsets(5, 0, 0, 5);
        builder.setAnchor(GridBagConstraints.WEST);
        builder.setWeightX(1);
        builder.setFill(GridBagConstraints.HORIZONTAL);
        builder.addComponent(getSuchFeld());
        builder.setWeightX(0);
        builder.addComponent(submitToolBar, true);
        builder.setWeight(1,1);
        builder.setFill(GridBagConstraints.BOTH);
        builder.addComponent(tabellenScroller, true);
        /*
        FormLayout layout = new FormLayout("180dlu:g, 3dlu, min(16dlu;p)", // spalten
            "20dlu, 3dlu, 300dlu:g"); // zeilen
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();

        builder.add(getSuchFeld(), cc.xy(1, 1));
        builder.add(submitToolBar, cc.xy(3, 1));
        builder.add(tabellenScroller, cc.xyw(1, 3, 3));
        */
        return builder.getPanel();
    }

    private JTextField getSuchFeld() {
        if (this.suchFeld == null) {
            this.suchFeld = new JTextField();

            this.suchFeld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doSearch();
                }
            });

            this.suchFeld.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    String text = SielhautChooser.this.suchFeld.getText();
                    log.debug("(SielhautChooser) " + "keyChar: "
                        + e.getKeyChar() + ", Text: " + text);
                    if (Character.isLetterOrDigit(e.getKeyChar())) {
                        text = text + e.getKeyChar();
                    }
                    SielhautChooser.this.sielhautModel.filterList(text);
                    SielhautChooser.this.sielhautModel.fireTableDataChanged();
                }
            });
        }

        return this.suchFeld;
    }

    private JButton getSubmitButton() {
        if (this.submitButton == null) {
            this.submitButton = new JButton(AuikUtils.getIcon(16,
                "key_enter.png"));
            this.submitButton.setToolTipText("Suche starten");
            this.submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doSearch();
                }
            });
        }

        return this.submitButton;
    }

    private JTable getErgebnisTabelle() {
        if (this.ergebnisTabelle == null) {
            this.ergebnisTabelle = new JTable();

            Action submitAction = new AbstractAction("Auswählen") {
                private static final long serialVersionUID = 5609569229635452436L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    doOk();
                }
            };
            submitAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false));

            this.ergebnisTabelle.getInputMap().put(
                (KeyStroke) submitAction.getValue(Action.ACCELERATOR_KEY),
                submitAction.getValue(Action.NAME));
            this.ergebnisTabelle.getActionMap().put(
                submitAction.getValue(Action.NAME), submitAction);

            this.ergebnisTabelle.addFocusListener(TableFocusListener
                .getInstance());
            this.ergebnisTabelle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
                        Point origin = e.getPoint();
                        int row = SielhautChooser.this.ergebnisTabelle
                            .rowAtPoint(origin);
                        choose(row);
                    }
                }
            });
        }

        return this.ergebnisTabelle;
    }

}
