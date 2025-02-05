package de.bielefeld.umweltamt.aui.module.common;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.gui.PasswordChangeDialog;
import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.basis.Adresse;
import de.bielefeld.umweltamt.aui.mappings.basis.Inhaber;
import de.bielefeld.umweltamt.aui.mappings.basis.Standort;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisAdresseModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisInhaberModel;
import de.bielefeld.umweltamt.aui.module.common.tablemodels.BasisStandortModel;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.BasicEntryField;
import de.bielefeld.umweltamt.aui.utils.ComponentFactory;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;
import de.bielefeld.umweltamt.aui.utils.TabAction;
import de.bielefeld.umweltamt.aui.utils.TableFocusListener;

/**
 * Mit dieser Klasse werden Objekten Inhaber oder Standorte und Inhabern
 * neue Adessen zugeordnet
 * @author Gerd Genuit
 */

public class AdresseChooser extends JDialog {
	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	private HauptFrame frame;
	private Adresse adresse;
	private Inhaber inhaber;
	private Standort standort;
	private String caller;

	private BasisAdresseModel adresseModel;
	private BasisInhaberModel inhaberModel;
	private BasisStandortModel standortModel;

	private JTextField nameFeld;
	private JTextField strasseFeld;
	private JTextField hausnrFeld;
	private JTextField standortFeld;
	private JButton submitButtonAdresse;
	private JButton submitButtonInhaber;
	private JButton submitButtonStandort;
	private JTable ergebnisTabelle;

	private JButton okButton;
	private JButton abbrechenButton;

	private Timer suchTimer;

	public AdresseChooser(Object initial, HauptFrame frame, String caller) {
		super(frame, true);
		this.frame = frame;
		this.caller = caller;

		List<Object> initialList = new ArrayList<Object>();
		initialList.add(initial);

		if (initial instanceof Adresse && caller == "adresse") {
            setTitle("Adresse auswählen");
            this.adresse = (Adresse) initial;
            this.adresseModel = new BasisAdresseModel();
            if (this.adresse.getId() != null) {
                this.adresseModel.setList(initialList);
            }
        } else if (initial instanceof Inhaber && caller == "betreiber") {
            setTitle("Inhaber auswählen");
            this.inhaber = (Inhaber) initial;
            this.inhaberModel = new BasisInhaberModel(true);
            if (this.inhaber.getId() != null) {
                this.inhaberModel.setList(initialList);
            }
        } else if (initial instanceof Standort && caller == "standort") {
			setTitle("Standort auswählen");
			this.standort = (Standort) initial;
			this.standortModel = new BasisStandortModel();
			if (this.standort.getId() != null) {
				this.standortModel.setList(initialList);
			}
		} else {
			throw new IllegalArgumentException("intial muss eine Adresse, ein Inhaber oder ein Standort sein!");
		}

		setContentPane(initializeContentPane());

        pack();
		setResizable(true);
		setLocationRelativeTo(this.frame);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public Adresse getChosenAdresse() {
		if (this.adresse != null) {
			return this.adresse;
		} else {
			return null;
		}
	}

	public Inhaber getChosenBetreiber() {
		if (this.inhaber.getId() != null) {
			return this.inhaber;
		} else {
			return null;
		}
	}


    public Standort getChosenStandort() {
        if (this.standort.getId() != null) {
            return this.standort;
        } else {
            return null;
        }
    }

	private JPanel initializeContentPane() {
		JScrollPane tabellenScroller = new JScrollPane(getErgebnisTabelle(),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		TabAction ta = new TabAction();
		ta.addComp(getErgebnisTabelle());
		ta.addComp(getOkButton());
		ta.addComp(getAbbrechenButton());

		JComponent buttonBar = ComponentFactory.buildOKCancelBar(getOkButton(), getAbbrechenButton());

		JToolBar submitToolBar = new JToolBar();
		submitToolBar.setFloatable(false);
		submitToolBar.setRollover(true);
		submitToolBar.add(getSubmitButton());

		FormLayout layout = new FormLayout("pref, 3dlu, 400dlu:g, 3dlu, pref, 3dlu, 30dlu:g, 3dlu, pref", // spalten
				"pref, 3dlu, pref, 3dlu, pref, 3dlu, 320dlu:g, 3dlu, 3dlu, pref"); // zeilen
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		//nach Namen kann nur bei Inhabern und Standorten gesucht werden
		if (caller.equals("standort") || caller.equals("betreiber")) {
			builder.addLabel("Name:", cc.xy(1, 1));
			builder.add(getNameFeld(), cc.xyw(3, 1, 5));
			builder.add(getSubmitButton(), cc.xy(9, 1));
		}

		//nach Strasse und Hausnummer kann in jedem Fall gesucht werden
		builder.addLabel("Straße:", cc.xy(1, 3));
		builder.add(getStrassenFeld(), cc.xy(3, 3));
		builder.addLabel("Hausnr.:", cc.xy(5, 3));
		builder.add(getHausnrFeld(), cc.xy(7, 3));
		builder.add(getSubmitButtonAdresse(), cc.xy(9, 3));

		//eine Standortbezeichnung kann natürlich nur ein Standort haben
		if (caller.equals("standort")) {
			builder.addLabel("Standort:", cc.xy(1, 5));
			builder.add(getStandortFeld(), cc.xyw(3, 5, 5));
			builder.add(getSubmitButtonStandort(), cc.xy(9, 5));
		}
		builder.add(tabellenScroller, cc.xywh(1, 7, 9, 2));
		builder.add(buttonBar, cc.xyw(3, 10, 3));

		JPanel panel = builder.getPanel();
		panel.setBorder(Paddings.DIALOG);

		return (panel);
	}

	private void choose(int row) {
		if (row != -1) {
			if (this.inhaber != null && caller == "adresse") {
				this.adresse = this.adresseModel.getRow(row);
			} else if (this.adresse != null && caller == "adresse") {
				this.adresse = this.adresseModel.getRow(row);
			} else if (this.inhaber != null && caller == "betreiber") {
				this.inhaber = this.inhaberModel.getRow(row);
			} else if (this.standort != null) {
				this.standort = this.standortModel.getRow(row);
			}
			dispose();
		}
	}

	private void doSearchName() {
		final String name = getNameFeld().getText();

		if (caller == "betreiber") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					if (!name.equals("")) {
						AdresseChooser.this.inhaberModel.filterAllList(name, null);
					}
					else {
						JOptionPane.showMessageDialog(AdresseChooser.this, "Bitte geben Sie einen Namen ein.");
					}
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.inhaberModel.fireTableDataChanged();
				}
			};
			worker.start();

			getNameFeld().setText("");
			getStrassenFeld().setText("");
			getHausnrFeld().setText("");
			getStandortFeld().setText("");

		} else if (caller == "standort") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					if (!name.equals("")) {
					AdresseChooser.this.standortModel.filterStandortList(name, null);
					}
					else {
						JOptionPane.showMessageDialog(AdresseChooser.this, "Bitte geben Sie einen Namen ein.");
					}
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.standortModel.fireTableDataChanged();
				}
			};
			worker.start();

			getNameFeld().setText("");
			getStrassenFeld().setText("");
			getHausnrFeld().setText("");
			getStandortFeld().setText("");
		}
	}

	private void doSearchStrasse() {
		final String strasse = getStrassenFeld().getText();
		Integer nr = null;
		if (!this.hausnrFeld.getText().equals("")) {
			nr = Integer.parseInt(getHausnrFeld().getText());
		} else {
			nr = -1;
		}
		final Integer hausnr = nr;

		if (caller == "adresse") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					if (!strasse.equals("")) {
						AdresseChooser.this.adresseModel.filterStandort(strasse, hausnr);
					}
					else {
						JOptionPane.showMessageDialog(AdresseChooser.this, "Bitte geben Sie einen Straßennamen ein.");
					}
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.adresseModel.fireTableDataChanged();
				}
			};
			worker.start();

			getNameFeld().setText("");
			getStrassenFeld().setText("");
			getHausnrFeld().setText("");
			getStandortFeld().setText("");

		} else if (caller == "betreiber") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					if (!strasse.equals("")) {
						AdresseChooser.this.inhaberModel.filterStandort(strasse, hausnr, null);
					}
					else {
						JOptionPane.showMessageDialog(AdresseChooser.this, "Bitte geben Sie einen Straßennamen ein.");
					}
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.inhaberModel.fireTableDataChanged();
				}
			};
			worker.start();

			getStrassenFeld().setText("");
			getHausnrFeld().setText("");

		} else if (caller == "standort") {
			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					if (!strasse.equals("")) {
						AdresseChooser.this.standortModel.filterStandortList(strasse, hausnr, null);
					}
					else {
						JOptionPane.showMessageDialog(AdresseChooser.this, "Bitte geben Sie einen Straßennamen ein.");
					}
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.standortModel.fireTableDataChanged();
				}
			};
			worker.start();

			getNameFeld().setText("");
			getStrassenFeld().setText("");
			getHausnrFeld().setText("");
			getStandortFeld().setText("");
		}
	}

	private void doSearchStandort() {
		final String standort = getStandortFeld().getText();

			SwingWorkerVariant worker = new SwingWorkerVariant(getErgebnisTabelle()) {
				@Override
				protected void doNonUILogic() throws RuntimeException {
					if (!standort.equals("")) {
						AdresseChooser.this.standortModel.filterAllList(standort);
					}
					else {
						JOptionPane.showMessageDialog(AdresseChooser.this, "Das Suchfeld darf nicht leer sein oder 'Adresse' lauten.");
					}
				}

				@Override
				protected void doUIUpdateLogic() throws RuntimeException {
					AdresseChooser.this.standortModel.fireTableDataChanged();
				}
			};
			worker.start();

			getNameFeld().setText("");
			getStrassenFeld().setText("");
			getHausnrFeld().setText("");
			getStandortFeld().setText("");


	}

	/**
	 * Filtert die Standort-Liste nach Straße und Hausnummer.
	 *
	 * @param focusComp Welche Komponente soll nach der Suche den Fokus bekommen.
	 */
	public void filterBetreiberListe(Component focusComp) {
		log.debug("Start filterStandortListe()");
		int hausnr;
		try {
			hausnr = Integer.parseInt(getHausnrFeld().getText());
		} catch (NumberFormatException e1) {
			hausnr = -1;
		}
		final int fhausnr = hausnr;

		SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {

			@Override
			protected void doNonUILogic() {
				if (SettingsManager.getInstance().getStandort() == null) {
					AdresseChooser.this.inhaberModel.filterStandort(getStrassenFeld().getText(), fhausnr, null);
				}
				getNameFeld().setText("");
			}

			@Override
			protected void doUIUpdateLogic() {
				getErgebnisTabelle().clearSelection();

				AdresseChooser.this.inhaberModel.fireTableDataChanged();
				String statusMsg = "Suche: " + AdresseChooser.this.inhaberModel.getRowCount() + " Ergebnis";
				if (AdresseChooser.this.inhaberModel.getRowCount() != 1) {
					statusMsg += "se";
				}
				statusMsg += ".";
				AdresseChooser.this.frame.changeStatus(statusMsg);
			}
		};

		this.frame.changeStatus("Suche...");
		worker.start();
		log.debug("End filterStandortListe()");
	}

	private JTextField getNameFeld() {
		if (this.nameFeld == null) {
			this.nameFeld = new JTextField();
			this.nameFeld.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!getNameFeld().equals("")) {
						doSearchName();
					}
				}
			});
		}

		return this.nameFeld;
	}

	private JTextField getStandortFeld() {
		if (this.standortFeld == null) {
			this.standortFeld = new JTextField();
			this.standortFeld.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!getStandortFeld().getText().equals("") && !getStandortFeld().getText().equals("Adresse")) {
						doSearchStandort();
					}
				}
			});
		}

		return this.standortFeld;
	}

	private JTextField getStrassenFeld() {

		if (this.strasseFeld == null) {

			this.strasseFeld = new JTextField();
			this.strasseFeld.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getSuchTimer().stop();
					if (!getStrassenFeld().equals("")) {
						doSearchStrasse();
					}
				}
			});

			this.strasseFeld.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_TAB) {
						getSuchTimer().stop();
						filterBetreiberListe(getHausnrFeld());
					}
				}

				@Override
				public void keyTyped(KeyEvent e) {
					if (Character.isLetterOrDigit(e.getKeyChar())) {
						if (getSuchTimer().isRunning()) {
							getSuchTimer().restart();
						} else {
							getSuchTimer().start();
						}
					}
				}
			});
		}

		return this.strasseFeld;
	}

	private JTextField getHausnrFeld() {
		if (this.hausnrFeld == null) {
			this.hausnrFeld = new BasicEntryField();

			this.hausnrFeld.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterBetreiberListe(getErgebnisTabelle());
				}
			});
		}
		return this.hausnrFeld;
	}

	private JButton getSubmitButton() {
		if (this.submitButtonInhaber == null) {
			this.submitButtonInhaber = new JButton("Name suchen", AuikUtils.getIcon(16, "key_enter.png"));
			this.submitButtonInhaber.setToolTipText("Suche starten");
			this.submitButtonInhaber.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					doSearchName();
				}
			});
		}

		return this.submitButtonInhaber;
	}

	private JButton getSubmitButtonAdresse() {
		if (this.submitButtonAdresse == null) {
			this.submitButtonAdresse = new JButton("Adresse suchen", AuikUtils.getIcon(16, "key_enter.png"));
			this.submitButtonAdresse.setToolTipText("Suche starten");
			this.submitButtonAdresse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					doSearchStrasse();
				}
			});
		}

		return this.submitButtonAdresse;
	}

	private JButton getSubmitButtonStandort() {
		if (this.submitButtonStandort == null) {
			this.submitButtonStandort = new JButton("Standort suchen", AuikUtils.getIcon(16, "key_enter.png"));
			this.submitButtonStandort.setToolTipText("Suche starten");
			this.submitButtonStandort.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					doSearchStandort();
				}
			});
		}

		return this.submitButtonStandort;
	}

	private JTable getErgebnisTabelle() {
		if (this.ergebnisTabelle == null) {
			if (this.adresse != null && caller == "adresse") {
				this.ergebnisTabelle = new JTable(this.adresseModel);
			} else if (this.inhaber != null && caller == "betreiber") {
				this.ergebnisTabelle = new JTable(this.inhaberModel);
			} else if (this.standort != null) {
				this.ergebnisTabelle = new JTable(this.standortModel);
			}

			this.ergebnisTabelle.addFocusListener(TableFocusListener.getInstance());
			this.ergebnisTabelle.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if ((e.getClickCount() == 2) && (e.getButton() == 1)) {
						Point origin = e.getPoint();
						int row = AdresseChooser.this.ergebnisTabelle.rowAtPoint(origin);

						choose(row);
					}
				}
			});

			this.ergebnisTabelle.getColumnModel().getColumn(0).setPreferredWidth(20);
			this.ergebnisTabelle.getColumnModel().getColumn(1).setPreferredWidth(150);
			this.ergebnisTabelle.getColumnModel().getColumn(2).setPreferredWidth(80);
			this.ergebnisTabelle.getColumnModel().getColumn(3).setPreferredWidth(80);
			this.ergebnisTabelle.getColumnModel().getColumn(4).setPreferredWidth(80);
			this.ergebnisTabelle.getColumnModel().getColumn(5).setPreferredWidth(80);
		}

		return this.ergebnisTabelle;
	}

	private JButton getOkButton() {
		if (this.okButton == null) {
			this.okButton = new JButton("Ok");
			this.okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = getErgebnisTabelle().getSelectedRow();

					choose(row);
				}
			});
		}

		return this.okButton;
	}

	private JButton getAbbrechenButton() {
		if (this.abbrechenButton == null) {
			this.abbrechenButton = new JButton("Abbrechen");
			this.abbrechenButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}

		return this.abbrechenButton;
	}

	private Timer getSuchTimer() {
		if (this.suchTimer == null) {
			this.suchTimer = new Timer(900, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					// Was diese ganze "SwingWorkerVariant"-Geschichte
					// soll, steht unter
					// http://www.javaworld.com/javaworld/jw-06-2003/jw-0606-swingworker.html
					// Ist auch ausgedruckt im Ordner im Regal. -DK
					SwingWorkerVariant worker = new SwingWorkerVariant(getStrassenFeld()) {
						protected String oldText = "";
						private String newText = "";

						@Override
						protected void doNonUILogic() {
                            this.oldText = getStrassenFeld().getText();
                            if (this.oldText.equals(""))
                            {
                                this.newText = "";
                            }
                            else
                            {
                                String suchText = AuikUtils
                                        .sanitizeQueryInput(this.oldText);
								String str = DatabaseQuery
										.getTabStreet(suchText);

                                if (str != null)
                                {
                                    this.newText = str;
                                }
                                else
                                {
                                    this.newText = this.oldText;
                                }
                            }
						}

						@Override
						protected void doUIUpdateLogic() {
							getStrassenFeld().setText(this.newText);
							getStrassenFeld().setSelectionStart(this.oldText.length());
							getStrassenFeld().setSelectionEnd(this.newText.length());
						}
					};
					worker.start();
				}
			});
			this.suchTimer.setRepeats(false);
		}

		return this.suchTimer;
	}
}
